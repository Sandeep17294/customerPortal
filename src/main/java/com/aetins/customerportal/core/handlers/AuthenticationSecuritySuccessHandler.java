package com.aetins.customerportal.core.handlers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.core.events.otp.OnOTPGenerationCompleteEvent;
import com.aetins.customerportal.core.pojo.ActiveUserStore;
import com.aetins.customerportal.core.pojo.LoggedUser;
import com.aetins.customerportal.core.utils.SessionAttributesConstants;
import com.aetins.customerportal.web.audittrails.service.UserSessionAuditTrailsService;
import com.aetins.customerportal.web.dao.CpUserInfoDAO;
import com.aetins.customerportal.web.dto.CpServerSettingDTO;
import com.aetins.customerportal.web.entity.CpLoginAuditTrails;
import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.service.AdminBL;
import com.aetins.customerportal.web.service.CpConfigurationBL;
import com.aetins.customerportal.web.utils.DateUtil;

/**
 * Custom authentication success handler
 * 
 * @author avinash
 *
 */
@Service
public class AuthenticationSecuritySuccessHandler implements AuthenticationSuccessHandler {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Autowired
	ApplicationEventPublisher eventPublisher;

	@Autowired
	ActiveUserStore activeUserStore;

	@Autowired
	CpUserInfoDAO cpUserInfoDAO;

	@Autowired
	UserSessionAuditTrailsService userLoginAuditTrails;
	
	@Autowired
	CpConfigurationBL configurationService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.web.authentication.AuthenticationSuccessHandler#
	 * onAuthenticationSuccess(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
			final Authentication authentication) throws IOException {

		handle(request, response, authentication);
		
		final HttpSession session = request.getSession(true);
		
		if (session != null) {
			session.setMaxInactiveInterval(30 * 60);

			String username;
			if (authentication.getPrincipal() instanceof CpUserInfo) {
				username = ((CpUserInfo) authentication.getPrincipal()).getVemail();
			} else {
				username = authentication.getName();
			}

			//Object details = authentication.getDetails();
			// Fetch user details in order to set in session
			final CpUserInfo cpUserInfo = cpUserInfoDAO.getCpUserInfo(username);
			LoggedUser user = new LoggedUser(username, activeUserStore);
			session.setAttribute(SessionAttributesConstants._USER, user);
			session.setAttribute(SessionAttributesConstants._USER_DETAILS, cpUserInfo);
			LOGGER.info("Session Created for User: {}",username);
			//Fetch portal configuration map.
			Map<String, String> configMap = configurationService.getConfigMap();
			
			if(configMap!=null) {
				session.setAttribute(SessionAttributesConstants._PORTAL_CONFIG_MAP, configMap);
				LOGGER.info("#########################################");
				LOGGER.info("Portal Config map loaded in session configmap");
				LOGGER.info("#########################################");
			}

			/*
			 * CpLoginAuditTrails fetchAuditTrailsByDate =
			 * userLoginAuditTrails.fetchAuditTrailsByDate(username);
			 * System.out.println(fetchAuditTrailsByDate);
			 */
			// Fetch user login audit trails.
			List<CpLoginAuditTrails> loginAudit = userLoginAuditTrails.getLoginAuditTrailsByUsername(cpUserInfo.getVuserName());

			if (loginAudit.size() > 0) {

				for (CpLoginAuditTrails auditTrail : loginAudit) {
					if (DateUtil.toDateString(auditTrail.getCreatedDate()).equals(DateUtil.toDateString(new Date()))) {
						auditTrail.setLoggedInTime(new Date());
						auditTrail.setActive(Boolean.TRUE);
						auditTrail.setUserLocked(Boolean.FALSE);
						userLoginAuditTrails.updateAuditTrail(auditTrail);
						LOGGER.info("Login Audit Trail was successfully updated: {}", auditTrail);
						break;

					} else {

						if (new Date().after(auditTrail.getCreatedDate())) {
							auditTrail.setLoggedInTime(new Timestamp(System.currentTimeMillis()));
							auditTrail.setLogoutTime(null);
							auditTrail.setActive(Boolean.TRUE);
							auditTrail.setUserLocked(Boolean.FALSE);
							userLoginAuditTrails.saveLoginAuditTrailsinAnotherSession(auditTrail);
							LOGGER.info("Login Audit Trail was successfully created in another session: {}",auditTrail);
							break;
						}

					}

				}

			} else {

				// User Login activities. 
				CpLoginAuditTrails loginAuditTrails = new CpLoginAuditTrails();
				loginAuditTrails.setCustRefNo(cpUserInfo.getNcustRefNo());
				loginAuditTrails.setUsername(cpUserInfo.getVuserName());
				loginAuditTrails.setActive(Boolean.TRUE);
				loginAuditTrails.setUserLocked(Boolean.FALSE);
				loginAuditTrails.setNoOfUnsuccessfullAttemptsExceed(0);
				loginAuditTrails.setUnsuccessfullAttempts(0);
				loginAuditTrails.setLoggedInTime(new Timestamp(System.currentTimeMillis()));
				userLoginAuditTrails.saveAuditTrail(loginAuditTrails);
				LOGGER.info("New Login Audit Trail was successfully created : {}", loginAuditTrails);
			}

			eventPublisher.publishEvent(new OnOTPGenerationCompleteEvent(cpUserInfo, request.getLocale()));
			

		}
		clearAuthenticationAttributes(request);
	}

	/**
	 * @param request
	 * @param response
	 * @param authentication
	 * @throws IOException
	 */
	protected void handle(final HttpServletRequest request, final HttpServletResponse response,
			final Authentication authentication) throws IOException {
		final String targetUrl = determineTargetUrl(authentication);
		System.out.println("Context Path : "+request.getContextPath());

		try {
			if (response.isCommitted()) {
				LOGGER.debug("Response has already been committed. Unable to redirect to " + targetUrl);
				return;
			}

			redirectStrategy.sendRedirect(request, response, targetUrl);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

   private List<CpServerSettingDTO> cpServerSettingDTOList;
	
	@Autowired
	AdminBL adminImpl;
	
	/**
	 * Default navigation to otp screen
	 * 
	 * @param authentication
	 * @return
	 */
	protected String determineTargetUrl(final Authentication authentication) {

		
		/*
		 * String locakedStatus = ((CpUserInfo)
		 * authentication.getPrincipal()).getVlocked();
		 * 
		 * 
		 * if(locakedStatus.equals("Y")) return "/accountlocked.app"; else
		 */
		cpServerSettingDTOList = null;
		cpServerSettingDTOList = new ArrayList<CpServerSettingDTO>();
		cpServerSettingDTOList = adminImpl.fetchDownTime();
	
		String group = null;
		System.out.println("USERNAME::::"+authentication.getName());
		CpUserInfo cpUserInfo = null;
		cpUserInfo = new CpUserInfo();
		cpUserInfo = cpUserInfoDAO.getCpUserInfo(authentication.getName());
		System.out.println("USER GROUP:::"+cpUserInfo.getVgroup());
		group=cpUserInfo.getVgroup();
		if(group.equalsIgnoreCase("U")) {
			if(cpServerSettingDTOList.get(0).getStatus().equalsIgnoreCase("A")) {
				return "/downsetting.app";
			}else {
				 return "/otpverification.app";	
			}
		}else {
			 return "/otpverification.app";
		}
	}

	/**
	 * @param request
	 */
	protected void clearAuthenticationAttributes(final HttpServletRequest request) {
		final HttpSession session = request.getSession();
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	public void setRedirectStrategy(final RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}
}
