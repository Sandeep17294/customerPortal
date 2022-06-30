package com.aetins.customerportal.web.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.RegistrationCustomerDAO;
import com.aetins.customerportal.web.entity.CpUserInfo;



/**
 * @author satendra
 *
 * @param <T>
 * @param <ID>
 */

@Repository
public class RegistrationCustomerDAOImpl extends GenericDaoImpl<CpUserInfo, Long> implements RegistrationCustomerDAO{

	private static final Logger logger = Logger.getLogger(RegistrationCustomerDAOImpl.class);
	
	@Resource
	private SessionFactory sessionFactory;

	@Override
	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	protected Class<CpUserInfo> getEntityClass() {
		return CpUserInfo.class;
	}

	@Override
	public List<CpUserInfo> findAll(CpUserInfo clazz) {
		
		return findAll();
	}

	@Override
	public List<CpUserInfo> findByColumn(CpUserInfo info, String coloumn) {
		
		return findByColumn(info, coloumn);
	}

	@Override
	public CpUserInfo findById(CpUserInfo info, Long id) {
		
		return findById(info, id);
	}


}
