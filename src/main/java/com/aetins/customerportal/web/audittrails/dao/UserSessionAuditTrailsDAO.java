package com.aetins.customerportal.web.audittrails.dao;

import java.io.Serializable;
import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpLoginAuditTrails;
import com.aetins.customerportal.web.entity.CpOTPAuditTrails;

/**
 * @author avinash
 *
 */
public interface UserSessionAuditTrailsDAO extends IGenericDao<CpLoginAuditTrails, Serializable>{

	public void saveAuditTrails(CpLoginAuditTrails auditTrails);
	
	public void updateAuditTrails(CpLoginAuditTrails auditTrails);
	
	public List<CpLoginAuditTrails> fetchAll();
	
	public void saveORUpdateAuditTrails(CpLoginAuditTrails auditTrails);
	
	public void saveLoginAuditInAnotherSession(CpLoginAuditTrails loginAuditTrail);
	
	public List<CpLoginAuditTrails> findByUserName(String username);
	
	public void updateLoginAuditInAnotherSession(CpLoginAuditTrails loginAuditTrail);
	
	/**
	 * fetch user audit trails by present date
	 * @param username
	 * @return
	 */
	public CpLoginAuditTrails fetchAuditTrailsByDate(String username);
}
