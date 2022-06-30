package com.aetins.customerportal.web.audittrails.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.audittrails.dao.PasswordManagementAudiTrailsDao;
import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.entity.CpOTPAuditTrails;
import com.aetins.customerportal.web.entity.CpPasswordAuditTrails;

/**
 * @author avinash
 *
 */
@Repository
public class PasswordManagementDaoImpl extends GenericDaoImpl<CpPasswordAuditTrails, Long> implements PasswordManagementAudiTrailsDao{

private static Logger logger = LoggerFactory.getLogger(CpPasswordAuditTrails.class);
	
	
	@Resource
	private SessionFactory session;

	@Override
	protected Session getSession() {
		return session.getCurrentSession();
	}
	
	@Override
	protected Class<CpPasswordAuditTrails> getEntityClass() {
		return CpPasswordAuditTrails.class;
	}
	
	@Override
	public CpPasswordAuditTrails findByUsername(String username) {
		
		String[] propertyValues = {"username"};
		Object[] value = {username};
		
		List<CpPasswordAuditTrails> findAllByPropertiesEqual = findAllByPropertiesEqual(propertyValues, value);
		
		return findAllByPropertiesEqual.get(0);
	}

	public void savePasswordAuditTrail(CpPasswordAuditTrails cpAuditTrails) {
		
		
		save(cpAuditTrails);
	}

	@Override
	public void updatePasswordAuditTrail(CpPasswordAuditTrails cpPswdAuditTrail) {

		update(cpPswdAuditTrail);
	}
	
	
	

}
