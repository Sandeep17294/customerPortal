package com.aetins.customerportal.web.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.CpUserInfoDAO;
import com.aetins.customerportal.web.entity.CpUserInfo;

/**
 * @author satendra
 *
 */
@Repository
@Transactional
public class CpUserInfoDAOImpl extends GenericDaoImpl<CpUserInfo, Long> implements CpUserInfoDAO {
	
	
	private static final Logger logger = Logger.getLogger(CpUserInfoDAOImpl.class);
	
	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	protected Session getSession() {
		
		return sessionFactory.getCurrentSession();
	}

	@Override
	protected Class<CpUserInfo> getEntityClass() {
		
		return CpUserInfo.class;
	}

	/*
	 * Saving data to db (non-Javadoc)
	 * 
	 * @see com.aetins.webservices.dao.CpUserInfoDAO#saveUserInfo(com.aetins.
	 * webservices.entity.CpUserInfo)
	 */
	@Override
	public void saveUserInfo(CpUserInfo cpUserInfo) {
		
		getSession().persist(cpUserInfo);
		logger.info("save successfully");
	}

	
	@Override
	public CpUserInfo getCpUserInfo(String userName) {

		Criteria criteria = getSession().createCriteria(CpUserInfo.class);

		return (CpUserInfo) criteria.add(Restrictions.eq("vuserName", userName).ignoreCase()).uniqueResult();
	}
	
	@Override
	public List<CpUserInfo> getCpUsersByGroup(String userGroup) {
		
		String[] propertyValues = {"vgroup"};
		Object[] value = {userGroup};
		
		List<CpUserInfo> findAllByPropertiesEqual = findAllByPropertiesEqual(propertyValues, value);
		
		return findAllByPropertiesEqual;
	}
	

}
