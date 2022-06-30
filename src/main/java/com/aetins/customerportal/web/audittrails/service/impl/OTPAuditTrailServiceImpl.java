package com.aetins.customerportal.web.audittrails.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.audittrails.dao.OTPAuditTrailsDAO;
import com.aetins.customerportal.web.audittrails.service.OTPAuditTrailService;
import com.aetins.customerportal.web.entity.CpOTPAuditTrails;

/**
 * Service layer for OTP Audit trails
 * @author avinash
 *
 */
@Service
public class OTPAuditTrailServiceImpl implements OTPAuditTrailService {
	
	private static Logger logger = LoggerFactory.getLogger(OTPAuditTrailServiceImpl.class);
	
	@Autowired
	private OTPAuditTrailsDAO otpDao;

	@Override
	public void saveOTPAuditTrail(CpOTPAuditTrails otp) {
		
		otpDao.saveOTPAudit(otp);
		logger.info("OTP Audit trail object persisted: {}",otp);
	}

	@Override
	public List<CpOTPAuditTrails> getAllOTPAuditTrails() {
		
		return otpDao.fetchAllOTPAuditTrails();
	}

	@Override
	public List<CpOTPAuditTrails> getUserOTPAuditTrail(String username) {
		
		return otpDao.fetchUserOTPAuditTrail(username);
	}

	@Override
	public void updateOTPAuditTrail(CpOTPAuditTrails otp) {
		otpDao.updateOTPAuditTrail(otp);
		logger.info("OTP Audit Trail object updated: {}",otp);
	}

	@Override
	public void saveORUpdate(CpOTPAuditTrails otp) {
		otpDao.saveORUpdate(otp);	
		logger.info("OTP Audit Trail object saev or updated: {}",otp);
	}

	@Override
	public void saveOTPAuditInAnotherSession(CpOTPAuditTrails otp) {
		
		otpDao.saveOTPAuditInAnotherSession(otp);
	}

}
