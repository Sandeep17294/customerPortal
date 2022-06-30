package com.aetins.customerportal.web.audittrails.service;

import java.util.List;

import com.aetins.customerportal.web.entity.CpRegistrationAuditTrail;

/**
 * Interface responsible business logic for Registration Audit trails.
 * @author avinash
 *
 */
public interface RegistrationAuditTrailService {
	
	
	/**
	 * To save OTP audit trail
	 * @param otp
	 */
	public void saveRegistrationAuditTrail(CpRegistrationAuditTrail otp);
	
	/**
	 * To fetch all OTP Audit trails.
	 * @return
	 */
	public List<CpRegistrationAuditTrail> getAllRegistrationAuditTrails();
	
	/**
	 * To fetch particular otp Audit trail for repective user
	 * @param otp
	 * @return
	 */
	public CpRegistrationAuditTrail getUserRegistrationAuditTrail(String username);
	
	/**
	 * To update particular otp audit trail
	 * @param otp
	 */
	public void updateRegistrationAuditTrail(CpRegistrationAuditTrail otp);
	

	/**
	 * To save or update OTP audit trails
	 * @param otp
	 */
	public void saveORUpdate(CpRegistrationAuditTrail otp);

}
