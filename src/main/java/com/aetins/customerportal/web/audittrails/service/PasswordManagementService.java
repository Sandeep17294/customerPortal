package com.aetins.customerportal.web.audittrails.service;

import java.util.List;

import com.aetins.customerportal.web.entity.CpPasswordAuditTrails;

/**
 * Holds business logic for PasswordManagement audit trails.
 * @author avinash
 *
 */
public interface PasswordManagementService {

	public void savePasswordAuditTrail(CpPasswordAuditTrails pswdAuditTrails);
	
	public List<CpPasswordAuditTrails> fetchAll();
	
	public void updatePasswordAuditTrail(CpPasswordAuditTrails pswd);
	
	public CpPasswordAuditTrails finbByUserName(String username);
}
