package com.aetins.customerportal.web.audittrails.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpRegistrationAuditTrail;

/**
 * Interface responsible to crater user registration Audit trails
 * @author avinash
 *
 */
public interface RegistrationAuditTrailDao extends IGenericDao<CpRegistrationAuditTrail, Long>{
	
	/* (non-Javadoc)
	 * @see com.aetins.customerportal.web.common.IGenericDao#save(java.lang.Object)
	 */
	public void save(CpRegistrationAuditTrail cpregadtr);
	
	/* (non-Javadoc)
	 * @see com.aetins.customerportal.web.common.IGenericDao#findAll()
	 */
	public List<CpRegistrationAuditTrail> fetch();
	
	/**
	 * load by user name
	 * @param username
	 * @return
	 */
	public CpRegistrationAuditTrail findByUsername(String username); 
	
	/* (non-Javadoc)
	 * @see com.aetins.customerportal.web.common.IGenericDao#update(java.lang.Object)
	 */
	public void update(CpRegistrationAuditTrail reg);
	
	/**
	 * @param reg
	 */
	public void saveOrUpdateRegistrationTrail(CpRegistrationAuditTrail reg);

}
