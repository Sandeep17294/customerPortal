package com.aetins.customerportal.web.audittrails.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.audittrails.dao.UserSessionAuditTrailsDAO;
import com.aetins.customerportal.web.audittrails.service.UserSessionAuditTrailsService;
import com.aetins.customerportal.web.entity.CpLoginAuditTrails;
import com.aetins.customerportal.web.utils.DateUtil;

/**
 * To log User logged activities
 * @author avinash
 *
 */
@Service
public class UserSessionAuditTrailsServiceImpl implements UserSessionAuditTrailsService{
	
	private static Logger logger = LoggerFactory.getLogger(UserSessionAuditTrailsServiceImpl.class);

	@Autowired
	private UserSessionAuditTrailsDAO userLoginAuditTrails;
	
	@Override
	public void saveAuditTrail(CpLoginAuditTrails auditTrails) {
		userLoginAuditTrails.saveAuditTrails(auditTrails);
	}

	@Override
	public void updateAuditTrail(CpLoginAuditTrails auditTrails) {

		userLoginAuditTrails.updateAuditTrails(auditTrails);
	}

	@Override
	public void saveORUpdateAuditTrail(CpLoginAuditTrails auditTrails) {
		userLoginAuditTrails.saveORUpdateAuditTrails(auditTrails);		
	}

	@Override
	public List<CpLoginAuditTrails> getLoginAuditTrails() {
		
		return userLoginAuditTrails.fetchAll();
	}

	@Override
	public void saveLoginAuditTrailsinAnotherSession(CpLoginAuditTrails auditTrails) {
		userLoginAuditTrails.saveLoginAuditInAnotherSession(auditTrails);
		
	}

	@Override
	public List<CpLoginAuditTrails> getLoginAuditTrailsByUsername(String username) {
		return userLoginAuditTrails.findByUserName(username);
	}

	@Override
	public void updateLogOutTime(CpLoginAuditTrails auditTrails) {

		if(auditTrails!=null?DateUtil.toDateString(auditTrails.getLoggedInTime()).equals(DateUtil.toDateString(new Date())):false) {
			
			auditTrails.setLogoutTime(new Timestamp(System.currentTimeMillis()));
			saveAuditTrail(auditTrails);
			logger.info("Logout date updated for user: {}, logout time: {}",auditTrails.getUsername(),auditTrails.getLogoutTime());
		}else {
			logger.error("While Update user logout time loginAuditTrails is empty ");
		}
	}

	@Override
	public void updateLoginAuditTrailsinAnotherSession(CpLoginAuditTrails auditTrails) {
		userLoginAuditTrails.updateLoginAuditInAnotherSession(auditTrails);
	}

	@Override
	public CpLoginAuditTrails fetchAuditTrailsByDate(String username) {
		return userLoginAuditTrails.fetchAuditTrailsByDate(username);
	}

	
}
