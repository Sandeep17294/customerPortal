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
import com.aetins.customerportal.web.dao.RolesDao;
import com.aetins.customerportal.web.entity.Role;

@Repository
public class RolesDaoImpl extends GenericDaoImpl<Role, Long> implements RolesDao {

	private Logger logger = LoggerFactory.getLogger(RolesDaoImpl.class);

	@Resource
	SessionFactory sessionFactory;

	@Override
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	protected Class<Role> getEntityClass() {
		return Role.class;
	}

	@Override
	public Role saveRoles(Role roles) {

		logger.info("Persisting Roles Entity : " + roles);
		save(roles);
		return roles;
	}

	@Override
	public Role findByName(String roleName) {
		Criteria criteria = getSession().createCriteria(Role.class);
		logger.info("Find role by name : " + roleName);
		return (Role) criteria.add(Restrictions.eq("name", roleName).ignoreCase()).uniqueResult();
	}

}
