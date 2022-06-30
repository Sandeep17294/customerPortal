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
import com.aetins.customerportal.web.dao.CpQuestionnaireDao;
import com.aetins.customerportal.web.entity.CpQuestionnaire;

@Repository
public class CpQuestionnaireDAOImpl extends GenericDaoImpl<CpQuestionnaire, Long> implements CpQuestionnaireDao{

	private Logger logger = LoggerFactory.getLogger(CpQuestionnaireDAOImpl.class);
	
	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	protected Session getSession() {		
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public boolean saveQuestionnaire(CpQuestionnaire details) {
		boolean status=Boolean.TRUE;
		  logger.info("save Documents Start");
		  try{		   
		     saveOrUpdate(details);			   
		  }catch (Exception e) {
		   status=Boolean.FALSE;
		   logger.error("Error occured "+e.getMessage());
		   e.printStackTrace();
		  }		 
		return status;
	}

	@Override
	protected Class<CpQuestionnaire> getEntityClass() {
		
		return CpQuestionnaire.class;
	}

	@Override
	public List<CpQuestionnaire> questionnaireList(String serviceType) {
		String[] propertyName = {"serviceName"};
		Object[] value = {serviceType};
		try{
			List<CpQuestionnaire> questionnaireList = findAllByPropertiesEqual(propertyName, value);
			return questionnaireList;
		}catch (Exception e){
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CpQuestionnaire findLatestRecord(String query) {
		List<CpQuestionnaire> questions = new ArrayList<CpQuestionnaire>();
	    questions = findWithCustomQuery(query);
		CpQuestionnaire question = new CpQuestionnaire();
		if(questions!=null&&questions.size()>0){
			for(Object eachQuestion:questions){
				Object[] row=(Object[])eachQuestion;		
				question.setServiceId(Long.valueOf(row[0].toString()));
				System.out.println(question.getServiceId());
			}	
		}
		return question;
	}

	@Override
	public boolean saveAnswers(CpQuestionnaire answer) {
		boolean status = false;
		logger.info("save Documents Start");
		  try{		   
		     update(answer);
		     status = true;
		  }catch (Exception e) {
		   status=Boolean.FALSE;
		   logger.error("Error occured "+e.getMessage());
		   e.printStackTrace();
		  }	
		return status;
	}

}
