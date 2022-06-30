package com.aetins.customerportal.web.controllers;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aetins.customerportal.core.pojo.UserLoginFailurePojo;
import com.aetins.customerportal.web.audittrails.service.UserSessionAuditTrailsService;
import com.aetins.customerportal.web.dao.CpUserInfoDAO;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Controller
@RequestMapping(value = "/Aunthentication")
public class AuthentictionFailureController {

	private static Logger LOGGER = LoggerFactory.getLogger(AuthentictionFailureController.class);

	@Autowired
	UserSessionAuditTrailsService userLoginAuditTrails;

	@Autowired
	CpUserInfoDAO cpUserInfoDAO;

	private static String _PATTERN = "yyyy-MM-dd";
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(_PATTERN);
	
	private LoadingCache<String, Long> authFailureCache;

	private static final Integer EXPIRE_MINS = 180;
	
	private static final Long LIMIT_LOGIN = (long) 3;
	
	public AuthentictionFailureController() {

		// Cache to holds registered user details
		authFailureCache = CacheBuilder.newBuilder().expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES)
				.build(new CacheLoader<String, Long>() {
					public Long load(String key) {
						return null ;
					}
				});
	}

	@GetMapping(value = "/failed")
	private ModelAndView redirectToFailurePage(@RequestParam("username") String username,@RequestParam("loginFailure") String noofloginfailure,@RequestParam("loginFailureExceed") String noofloginfailureExceed) {
		
		UserLoginFailurePojo user = new UserLoginFailurePojo();
		ModelAndView view = new ModelAndView("authenticationFailure", "command", user);
		view.addObject("failureAttempts", noofloginfailure);
		
		if(Long.parseLong(noofloginfailureExceed)>1) {
			view.addObject("failureAttempts", noofloginfailureExceed);
			view.addObject("failureAttemptsExceed", "Login exceed");
		}
		/*
		 * try { LOGGER.error("Login Failure received username: {}", username);
		 * 
		 * // Fetch user login audit trails. List<CpLoginAuditTrails> loginAudit =
		 * userLoginAuditTrails.getLoginAuditTrailsByUsername(username);
		 * 
		 * if (loginAudit.size() > 0) { for (CpLoginAuditTrails auditTrail : loginAudit)
		 * { if (DateUtil.toDateString(auditTrail.getCreatedDate()).equals(DateUtil.
		 * toDateString(new Date()))) {
		 * auditTrail.setUnsuccessfullAttempts(auditTrail.getUnsuccessfullAttempts() +
		 * 1); authFailureCache.put(username,auditTrail.getUnsuccessfullAttempts());
		 * 
		 * //Limit login exceed. Long loginLimit = authFailureCache.get(username);
		 * view.addObject("failureAttempts", loginLimit);
		 * 
		 * if(loginLimit!=null) {
		 * LOGGER.error("Username : {} No.of failure login attempts: {}",username,
		 * loginLimit);
		 * 
		 * if(loginLimit == LIMIT_LOGIN) {
		 * auditTrail.setNoOfUnsuccessfullAttemptsExceed(auditTrail.
		 * getUnsuccessfullAttempts()+1); view.addObject("failureAttemptsExceed",
		 * auditTrail.getNoOfUnsuccessfullAttemptsExceed()); view.addObject("exceedMsg",
		 * "Failure attempts exceed"); } }
		 * userLoginAuditTrails.updateAuditTrail(auditTrail); LOGGER.
		 * info("Login Audit Trail was successfully updated or failure login : {}",
		 * auditTrail); break; } } }
		 * 
		 * 
		 * 
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */

		return view;
	}
}
