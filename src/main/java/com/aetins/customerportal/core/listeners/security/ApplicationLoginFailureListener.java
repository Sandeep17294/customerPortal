/*
 * package com.aetins.customerportal.core.listeners.security;
 * 
 * import java.io.IOException; import java.sql.Timestamp; import java.util.Date;
 * import java.util.List;
 * 
 * import javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.ApplicationListener; import
 * org.springframework.security.authentication.event.
 * AuthenticationFailureBadCredentialsEvent; import
 * org.springframework.stereotype.Component; import
 * org.springframework.web.servlet.ModelAndView;
 * 
 * import com.aetins.customerportal.web.audittrails.service.
 * UserSessionAuditTrailsService; import
 * com.aetins.customerportal.web.dao.CpUserInfoDAO; import
 * com.aetins.customerportal.web.entity.CpLoginAuditTrails; import
 * com.aetins.customerportal.web.entity.CpUserInfo; import
 * com.aetins.customerportal.web.utils.DateUtil;
 * 
 *//**
	 * Handle failure login
	 * 
	 * @author avinash
	 *
	 *//*
		 * @Component public class ApplicationLoginFailureListener implements
		 * ApplicationListener<AuthenticationFailureBadCredentialsEvent>{
		 * 
		 * private static Logger LOGGER =
		 * LoggerFactory.getLogger(ApplicationLoginFailureListener.class);
		 * 
		 * @Autowired private UserSessionAuditTrailsService userLoginAuditTrails;
		 * 
		 * @Autowired private CpUserInfoDAO cpUserInfoDAO;
		 * 
		 * @Autowired HttpServletRequest request;
		 * 
		 * @Autowired HttpServletResponse response;
		 * 
		 * @Override public void
		 * onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
		 * 
		 * try { String userName = event.getAuthentication().getPrincipal().toString();
		 * LOGGER.error("----------- LOGIN FAILURE --------------");
		 * LOGGER.error("Login failure username: {}",userName);
		 * LOGGER.error("-----------------------------------------");
		 * response.sendRedirect(request.getContextPath()+
		 * "/Aunthentication/failed?username="+userName+""); } catch (IOException e) {
		 * e.printStackTrace(); } } }
		 */