package com.aetins.customerportal.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.PasswordRulesDAO;
import com.aetins.customerportal.web.entity.PasswordRules;


@Repository
public class PasswordRulesDAOImpl extends GenericDaoImpl<PasswordRules, Long> implements PasswordRulesDAO {

	
	private Logger logger = LoggerFactory.getLogger(PasswordRulesDAOImpl.class);
	
	@Resource
	private SessionFactory session;
	
	@Override
	protected Session getSession() {
		return session.getCurrentSession();
	}
	
	@Override
	protected Class<PasswordRules> getEntityClass() {
		return PasswordRules.class;
	}
	
	
	@Override
	public List<PasswordRules> listPasswordRules() {
		System.out.println(getSession());
		return findAll();
	}

	
	
	@Override
	public boolean updatePasswordRules(PasswordRules passwordRules){
		boolean status=Boolean.TRUE;
		logger.info("Update Password Rules Start");
		try{
			update(passwordRules);
		}catch (Exception e) {
			status=Boolean.FALSE;
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}	
		return status;
		
		
	}

	

}
