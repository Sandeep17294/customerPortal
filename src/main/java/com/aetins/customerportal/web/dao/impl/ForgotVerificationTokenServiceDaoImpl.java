package com.aetins.customerportal.web.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.ForgotVerificationTokenServiceDao;
import com.aetins.customerportal.web.dao.RegistrationVerificationTokenServiceDao;
import com.aetins.customerportal.web.entity.ForgotVerificationToken;
import com.aetins.customerportal.web.entity.VerificationToken;

@Repository
public class ForgotVerificationTokenServiceDaoImpl extends GenericDaoImpl<ForgotVerificationToken, Long> implements ForgotVerificationTokenServiceDao{

	
	@Resource
	private SessionFactory sessionFactory;

	@Override
	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	@Override
	protected Class<ForgotVerificationToken> getEntityClass() {
		return ForgotVerificationToken.class;
	}

	@Override
	public List<ForgotVerificationToken> fetchTokenByUserId(Long id) {
		
		String[] propertyValues = {"id"};
		Object[] value = {id};
		
		List<ForgotVerificationToken> findAllByPropertiesEqual = findAllByPropertiesEqual(propertyValues, value);
		return findAllByPropertiesEqual;
	}
	
	@Override
	public void createRegistration(ForgotVerificationToken token) {
		
		save(token);
	}

	@Override
	public ForgotVerificationToken findByToken(String token) {
		
		String[] propertyValues = {"token"};
		Object[] value = {token};
		
		List<ForgotVerificationToken> findAllByPropertiesEqual = findAllByPropertiesEqual(propertyValues, value);
		return findAllByPropertiesEqual.get(0);
	}

	@Override
	public void deleteVerificationToken(ForgotVerificationToken token) {

		remove(token);
	}

	
	
}
