package com.aetins.customerportal.web.audittrails.dao.impl;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.audittrails.dao.UserSessionAuditTrailsDAO;
import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.entity.CpLoginAuditTrails;

@Repository
public class UserSessionAuditTrailsDAOImpl extends GenericDaoImpl<CpLoginAuditTrails, Serializable>
		implements UserSessionAuditTrailsDAO {

	@Resource
	private SessionFactory session;

	@Override
	protected Session getSession() {
		return session.getCurrentSession();
	}

	@Override
	protected Class<CpLoginAuditTrails> getEntityClass() {
		return CpLoginAuditTrails.class;
	}

	@Override
	public void saveAuditTrails(CpLoginAuditTrails auditTrails) {

		save(auditTrails);
	}

	@Override
	public void updateAuditTrails(CpLoginAuditTrails auditTrails) {
		update(auditTrails);
	}

	@Override
	public List<CpLoginAuditTrails> fetchAll() {
		return findAll();
	}

	@Override
	public void saveORUpdateAuditTrails(CpLoginAuditTrails auditTrails) {
		saveOrUpdate(auditTrails);
	}

	@Override
	public void saveLoginAuditInAnotherSession(CpLoginAuditTrails auditTrails) {

		try {

			Session openSession = session.openSession();
			openSession.beginTransaction();
			openSession.save(auditTrails);
			openSession.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<CpLoginAuditTrails> findByUserName(String username) {

		String[] propertyValues = { "username" };
		Object[] value = { username };

		List<CpLoginAuditTrails> findAllByPropertiesEqual = findAllByPropertiesEqual(propertyValues, value);
		return findAllByPropertiesEqual;
	}

	@Override
	public void updateLoginAuditInAnotherSession(CpLoginAuditTrails loginAuditTrail) {

		try {

			Session openSession = session.openSession();
			openSession.beginTransaction();
			openSession.update(loginAuditTrail);
			openSession.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public CpLoginAuditTrails fetchAuditTrailsByDate(String username) {
		List<CpLoginAuditTrails> findWithCustomQuery = findWithCustomQuery("SELECT *  FROM cp_login_audit_trail WHERE DATE(log_in_time) = CURDATE() AND username='"+username+"';");
		
		//CpLoginAuditTrails cpLoginAuditTrails = findWithCustomQuery.get(0);
		Iterator itr = findWithCustomQuery.iterator();

		while(itr.hasNext()) {
			
			CpLoginAuditTrails list =  (CpLoginAuditTrails)itr.next();
		}
		
		return findWithCustomQuery.size()>0?(CpLoginAuditTrails)new CpLoginAuditTrails():null;
	
	}

}
