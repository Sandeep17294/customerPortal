package com.aetins.customerportal.web.audittrails.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.audittrails.dao.OTPAuditTrailsDAO;
import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.entity.CpOTPAuditTrails;
import com.aetins.customerportal.web.entity.CpUserInfo;

/**
 * @author avinash
 *
 */
@Repository
public class OTPAuditTrailDAOImpl extends GenericDaoImpl<CpOTPAuditTrails, Long> implements OTPAuditTrailsDAO {
 
	private static Logger logger = LoggerFactory.getLogger(OTPAuditTrailDAOImpl.class);
	
	
	@Resource
	private SessionFactory session;

	@Override
	protected Session getSession() {
		return session.getCurrentSession();
	}
	
	

	@Override
	protected Class<CpOTPAuditTrails> getEntityClass() {

		return CpOTPAuditTrails.class;
	}

	@Override
	public void saveOTPAudit(CpOTPAuditTrails otp) {
		getSession().persist(otp);
	}

	@Override
	public List<CpOTPAuditTrails> fetchAllOTPAuditTrails() {
		return findAll();
	}

	@Override
	public List<CpOTPAuditTrails> fetchUserOTPAuditTrail(String username) {
		
		String[] propertyValues = {"username"};
		Object[] value = {username};
		
		List<CpOTPAuditTrails> findAllByPropertiesEqual = findAllByPropertiesEqual(propertyValues, value);
		
		return findAllByPropertiesEqual;
	}

	@Override
	public void updateOTPAuditTrail(CpOTPAuditTrails otp) {
		update(otp);
	}

	@Override
	public void saveORUpdate(CpOTPAuditTrails otp) {
		
		saveORUpdate(otp);
		
	}



	@Override
	public void saveOTPAuditInAnotherSession(CpOTPAuditTrails otp) {
		
		try {
			Session openSession = session.openSession();
			openSession.beginTransaction();
			openSession.save(otp);
			openSession.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
	}

}
