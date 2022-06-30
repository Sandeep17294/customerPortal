package com.aetins.customerportal.web.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.RegistrationVerificationTokenServiceDao;
import com.aetins.customerportal.web.entity.VerificationToken;

@Repository
public class RegistrationVerificationTokenServiceDaoImpl extends GenericDaoImpl<VerificationToken, Long> implements RegistrationVerificationTokenServiceDao{

	
	@Resource
	private SessionFactory sessionFactory;

	@Override
	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	@Override
	protected Class<VerificationToken> getEntityClass() {
		return VerificationToken.class;
	}

	@Override
	public List<VerificationToken> fetchTokenByUserId(Long id) {
		
		String[] propertyValues = {"id"};
		Object[] value = {id};
		
		List<VerificationToken> findAllByPropertiesEqual = findAllByPropertiesEqual(propertyValues, value);
		return findAllByPropertiesEqual;
	}
	
	@Override
	public void createRegistration(VerificationToken token) {
		
		save(token);
	}

	@Override
	public VerificationToken findByToken(String token) {
		
		String[] propertyValues = {"token"};
		Object[] value = {token};
		
		List<VerificationToken> findAllByPropertiesEqual = findAllByPropertiesEqual(propertyValues, value);
		return findAllByPropertiesEqual.get(0);
	}

	@Override
	public void deleteVerificationToken(VerificationToken token) {

		remove(token);
	}

	
	
}
