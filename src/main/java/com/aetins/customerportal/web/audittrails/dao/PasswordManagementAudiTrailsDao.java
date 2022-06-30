package com.aetins.customerportal.web.audittrails.dao;

import java.io.Serializable;
import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpPasswordAuditTrails;

/**
 * To crater password audit trails 
 * @author avinash
 *
 */
public interface PasswordManagementAudiTrailsDao extends IGenericDao<CpPasswordAuditTrails, Long>{

	/*
	 * To save password audit trails
	 */
	public void save(CpPasswordAuditTrails pswdAuditTrail);
	
	/*
	 * Find by username
	 * @param username
	 * @return
	 */
	public CpPasswordAuditTrails findByUsername(String username);
	
	/*
	 *Load all
	 */
	public List<CpPasswordAuditTrails> findAll();
	
	/*
	 * @param cpPswdAuditTrail
	 */
	public void updatePasswordAuditTrail(CpPasswordAuditTrails cpPswdAuditTrail);
	
	
}
