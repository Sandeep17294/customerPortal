package com.aetins.customerportal.web.audittrails.service;

import java.util.List;

import com.aetins.customerportal.web.entity.CpOTPAuditTrails;

/**
 * Interface responsible business logic for OTP Audit trails.
 * @author avinash
 *
 */
public interface OTPAuditTrailService {
	
	/**
	 * To save OTP audit trail
	 * @param otp
	 */
	public void saveOTPAuditTrail(CpOTPAuditTrails otp);
	
	/**
	 * To fetch all OTP Audit trails.
	 * @return
	 */
	public List<CpOTPAuditTrails> getAllOTPAuditTrails();
	
	/**
	 * To fetch particular otp Audit trail for repective user
	 * @param otp
	 * @return
	 */
	public List<CpOTPAuditTrails> getUserOTPAuditTrail(String username);
	
	/**
	 * To update particular otp audit trail
	 * @param otp
	 */
	public void updateOTPAuditTrail(CpOTPAuditTrails otp);
	

	/**
	 * To save or update OTP audit trails
	 * @param otp
	 */
	public void saveORUpdate(CpOTPAuditTrails otp);
	
	/**
	 * To persist OTP audit trail in another session
	 * @param otp
	 */
	public void saveOTPAuditInAnotherSession(CpOTPAuditTrails otp);
}
