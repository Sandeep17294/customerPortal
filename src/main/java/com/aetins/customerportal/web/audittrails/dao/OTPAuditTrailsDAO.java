package com.aetins.customerportal.web.audittrails.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpOTPAuditTrails;

/**
 * Interface responsible to crater user OTP Audit trails
 * @author avinash
 *
 */
public interface OTPAuditTrailsDAO extends IGenericDao<CpOTPAuditTrails, Long>{

	/**
	 * To save user otp audit trail
	 * @param otp
	 */
	public void saveOTPAudit(CpOTPAuditTrails otp);
	
	/**
	 * To fetch all OTP Audit trails.
	 * @return
	 */
	public List<CpOTPAuditTrails> fetchAllOTPAuditTrails();
	
	/**
	 * To fetch particular otp Audit trail for repective user
	 * @param otp
	 * @return
	 */
	public List<CpOTPAuditTrails> fetchUserOTPAuditTrail(String username);
	
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
	
	public void saveOTPAuditInAnotherSession(CpOTPAuditTrails otp);
}
