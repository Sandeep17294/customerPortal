package com.aetins.customerportal.web.audittrails.service;

import java.util.List;

import com.aetins.customerportal.web.entity.CpLoginAuditTrails;

/**
 * Business logic for login audit trails
 * @author avinash
 *
 */
public interface UserSessionAuditTrailsService {

	public void saveAuditTrail(CpLoginAuditTrails auditTrails);
	
	public void updateAuditTrail(CpLoginAuditTrails auditTrails);
	
	public void saveORUpdateAuditTrail(CpLoginAuditTrails auditTrails);
	
	public List<CpLoginAuditTrails> getLoginAuditTrails();
	
	public void saveLoginAuditTrailsinAnotherSession(CpLoginAuditTrails auditTrails);
	
	public List<CpLoginAuditTrails> getLoginAuditTrailsByUsername(String username);
	
	public void updateLogOutTime(CpLoginAuditTrails auditTrails);
	
	public void updateLoginAuditTrailsinAnotherSession(CpLoginAuditTrails auditTrails);
	
	public CpLoginAuditTrails fetchAuditTrailsByDate(String username);
}
