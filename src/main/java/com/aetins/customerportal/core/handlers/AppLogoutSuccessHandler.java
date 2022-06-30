package com.aetins.customerportal.core.handlers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.aetins.customerportal.core.faceletstaglib.SecurityLibrary;
import com.aetins.customerportal.core.pojo.LoggedUser;
import com.aetins.customerportal.core.services.IOTPService;
import com.aetins.customerportal.core.utils.SessionAttributesConstants;
import com.aetins.customerportal.web.audittrails.service.UserSessionAuditTrailsService;
import com.aetins.customerportal.web.entity.CpLoginAuditTrails;
import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.utils.BSLUtils;
import com.aetins.customerportal.web.utils.DateUtil;

/**
 * Default logout handler
 * 
 * @author avinash
 *
 */
@Component("logoutSuccessHandler")
public class AppLogoutSuccessHandler implements LogoutSuccessHandler {

	private Logger LOGGER = LoggerFactory.getLogger(AppLogoutSuccessHandler.class);

	@Autowired
	UserSessionAuditTrailsService userLoginAuditTrails;

	@Autowired
	IOTPService otpService;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		final HttpSession session = request.getSession();
		if (session != null) {

			// Logout user name
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			CpUserInfo attribute = (CpUserInfo) attributes.getRequest().getSession(true).getAttribute(SessionAttributesConstants._USER_DETAILS);

			/*
			 * if
			 * (BSLUtils.isNotNull(attribute)&&BSLUtils.isNotNull(attribute.getVuserName()))
			 * {
			 * 
			 * // Fetch user login audit trails. List<CpLoginAuditTrails> loginAudit =
			 * userLoginAuditTrails.getLoginAuditTrailsByUsername(attribute.getVuserName());
			 * 
			 * if (loginAudit.size() > 0) {
			 * 
			 * for (CpLoginAuditTrails auditTrail : loginAudit) { if
			 * (DateUtil.toDateString(auditTrail.getCreatedDate()).equals(DateUtil.
			 * toDateString(new Date()))) { auditTrail.setLogoutTime(new Date());
			 * auditTrail.setActive(Boolean.FALSE); auditTrail.setUserLocked(Boolean.FALSE);
			 * userLoginAuditTrails.updateAuditTrail(auditTrail);
			 * LOGGER.info("Login Audit Trail was successfully updated for logout time: {}",
			 * auditTrail); break;
			 * 
			 * } else {
			 * 
			 * if (new Date().after(auditTrail.getCreatedDate())&&
			 * auditTrail.getLastUpdatedDate() == null) { auditTrail.setLogoutTime(new
			 * Date()); auditTrail.setActive(Boolean.FALSE);
			 * auditTrail.setUserLocked(Boolean.FALSE);
			 * userLoginAuditTrails.updateLoginAuditTrailsinAnotherSession(auditTrail);
			 * LOGGER.info(
			 * "Login Audit Trail was successfully updated in another session for logout time: {}"
			 * , auditTrail); break; }
			 * 
			 * }
			 * 
			 * }
			 * 
			 * }
			 * 
			 * }
			 */
			// userLoginAuditTrails.updateLogOutTime(fullLoggedInUser.get);
			otpService.clearOTP(attribute.getVuserName());
			LOGGER.info("User Otp remove from cache while logout");
			session.removeAttribute(SessionAttributesConstants._USER);
			session.removeAttribute(SessionAttributesConstants._USER_DETAILS);
			LOGGER.info("User session removed: {}",session.getAttribute(SessionAttributesConstants._USER));
			LOGGER.info("User Otp remove from cache ");
			session.invalidate();

		}

		response.sendRedirect(request.getContextPath() + "/login?logSucc=true");
	}

}
