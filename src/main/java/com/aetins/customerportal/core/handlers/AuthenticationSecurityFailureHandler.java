
package com.aetins.customerportal.core.handlers;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.aetins.customerportal.web.audittrails.service.UserSessionAuditTrailsService;
import com.aetins.customerportal.web.dao.CpUserInfoDAO;
import com.aetins.customerportal.web.entity.CpLoginAuditTrails;
import com.aetins.customerportal.web.utils.DateUtil;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * Handle login failure
 * 
 * @author avinash
 *
 */
@Component("loginFailureHandler")
public class AuthenticationSecurityFailureHandler implements AuthenticationFailureHandler {

	private Logger LOGGER = LoggerFactory.getLogger(AuthenticationSecurityFailureHandler.class);

	@Autowired
	UserSessionAuditTrailsService userLoginAuditTrails;

	@Autowired
	CpUserInfoDAO cpUserInfoDAO;

	private LoadingCache<String, Long> authFailureCache;

	private static final Integer EXPIRE_MINS = 180;

	private static final Long LIMIT_LOGIN = (long) 3;
	

	public AuthenticationSecurityFailureHandler() {

		// Cache to holds registered user details
		authFailureCache = CacheBuilder.newBuilder().expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES)
				.build(new CacheLoader<String, Long>() {
					public Long load(String key) {
						return null;
					}
				});
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,AuthenticationException exception) throws IOException, ServletException {

		String username;
		long frontLoginFailure = 0;
		long frontLoginFailureExceed = 0;
		
		try {
			username = request.getParameter("username");
			// Fetch user login audit trails.
			List<CpLoginAuditTrails> loginAudit = userLoginAuditTrails.getLoginAuditTrailsByUsername(username);

			if (loginAudit.size() > 0) {
				for (CpLoginAuditTrails auditTrail : loginAudit) {
					if (DateUtil.toDateString(auditTrail.getCreatedDate()).equals(DateUtil.toDateString(new Date()))) {
						auditTrail.setUnsuccessfullAttempts(auditTrail.getUnsuccessfullAttempts() + 1);
						authFailureCache.put(username, auditTrail.getUnsuccessfullAttempts());

						// Limit login exceed.
						Long loginLimit = authFailureCache.get(username);

						if (loginLimit != null) {
							LOGGER.error("Username : {} No.of failure login attempts: {}", username, loginLimit);
							frontLoginFailure = LIMIT_LOGIN - loginLimit;
							if (loginLimit == LIMIT_LOGIN) {
								auditTrail.setNoOfUnsuccessfullAttemptsExceed(auditTrail.getNoOfUnsuccessfullAttemptsExceed());
								frontLoginFailureExceed = auditTrail.getUnsuccessfullAttempts();
								auditTrail.setUserLocked(Boolean.TRUE);
								auditTrail.setUserLockedTime(new java.sql.Date(new Date().getTime()));
								LOGGER.info("User: {} locked: {}", auditTrail);
							}
						}
						userLoginAuditTrails.updateAuditTrail(auditTrail);
						LOGGER.info("Login Audit Trail was successfully updated or failure login : {}", auditTrail);
						break;
					}
				}
			}
			response.sendRedirect(request.getContextPath() + "/Aunthentication/failed?username=" + username + "&loginFailure="+frontLoginFailure+"&loginFailureExceed="+frontLoginFailureExceed+"");
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
	}

}
