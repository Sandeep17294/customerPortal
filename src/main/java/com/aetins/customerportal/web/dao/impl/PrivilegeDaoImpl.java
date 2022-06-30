package com.aetins.customerportal.web.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.PrivilegeDao;
import com.aetins.customerportal.web.entity.Privilege;

/**
 * @author avinash
 *
 */
@Repository
public class PrivilegeDaoImpl extends GenericDaoImpl<Privilege, Long> implements PrivilegeDao{

	
	private Logger logger = LoggerFactory.getLogger(PrivilegeDaoImpl.class);

	@Resource
	SessionFactory sessionFactory;

	@Override
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	protected Class<Privilege> getEntityClass() {
		return Privilege.class;
	}

	@Override
	public Privilege savePrivilege(Privilege privilege) {
		
		logger.info("Persisting privilege Entity : " + privilege);
		save(privilege);

		return privilege;
	}

	@Override
	public Privilege findByName(String privilege) {

		Criteria criteria = getSession().createCriteria(Privilege.class);
		logger.info("Find privilege by name : " + privilege);
		return (Privilege) criteria.add(Restrictions.eq("name", privilege).ignoreCase()).uniqueResult();
	}

	
}
