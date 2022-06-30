package com.aetins.customerportal.web.audittrails.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.audittrails.dao.RegistrationAuditTrailDao;
import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.entity.CpConfiguration;
import com.aetins.customerportal.web.entity.CpRegistrationAuditTrail;

@Repository
public class RegistrationAuditTrailDaoImpl extends GenericDaoImpl<CpRegistrationAuditTrail, Long> implements RegistrationAuditTrailDao {
	
	
	@Resource
	private SessionFactory session;

	@Override
	protected Session getSession() {
		return session.getCurrentSession();
	}
	
	

	@Override
	protected Class<CpRegistrationAuditTrail> getEntityClass() {
		// TODO Auto-generated method stub
		return CpRegistrationAuditTrail.class;
	}
	
	@Override
	public CpRegistrationAuditTrail findByUsername(String username) {
		String[] propertyValues = {"username"};
		Object[] value = {username};
		
		List<CpRegistrationAuditTrail> findAllByPropertiesEqual = findAllByPropertiesEqual(propertyValues, value);
		
		return findAllByPropertiesEqual.get(0);
	}

	@Override
	public void save(CpRegistrationAuditTrail regadttr) {
		
		saveOrUpdate(regadttr);
	}

	public void update(CpRegistrationAuditTrail reg) {
		update(reg);
	}


	@Override
	public void saveOrUpdateRegistrationTrail(CpRegistrationAuditTrail reg) {
		saveOrUpdate(reg);
		
	}



	@Override
	public List<CpRegistrationAuditTrail> fetch() {
		 List<CpRegistrationAuditTrail> cpRegistrationAuditTrail = new ArrayList<CpRegistrationAuditTrail>();
			try{
				cpRegistrationAuditTrail = findAll();
			}catch (Exception e) {
				System.out.println("Ërror : " + e.getMessage());
				e.printStackTrace();
			}
			return cpRegistrationAuditTrail;
	}

}
