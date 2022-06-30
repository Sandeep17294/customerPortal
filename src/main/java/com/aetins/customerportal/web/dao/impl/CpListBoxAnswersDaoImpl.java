package com.aetins.customerportal.web.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.CpListBoxAnswersDao;
import com.aetins.customerportal.web.entity.CpListBoxAnswers;

@Repository
public class CpListBoxAnswersDaoImpl extends GenericDaoImpl<CpListBoxAnswers, Long> implements CpListBoxAnswersDao{

	private static final Logger logger = Logger.getLogger(CpListBoxAnswersDaoImpl.class);
	
	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	protected Session getSession() {		
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void saveListBoxAnswers(CpListBoxAnswers answers) {
		  logger.info("save Documents Start");
		  try{		   
		     saveOrUpdate(answers);			   
		  }catch (Exception e) {
		   logger.error("Error occured "+e.getMessage());
		   e.printStackTrace();
		  }			
	}

	@Override
	protected Class<CpListBoxAnswers> getEntityClass() {
		// TODO Auto-generated method stub
		return CpListBoxAnswers.class;
	}

	@Override
	public List<CpListBoxAnswers> getAnswers(Long serviceId) {
		String[] propertyName = {"serviceId"};
		Object[] value = {serviceId};
		try{
			List<CpListBoxAnswers> questionnaireList = findAllByPropertiesEqual(propertyName, value);
			return questionnaireList;
		}catch (Exception e){
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
