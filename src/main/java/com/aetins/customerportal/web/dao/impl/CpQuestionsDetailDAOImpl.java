package com.aetins.customerportal.web.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.CpQuestionsDetailDAO;
import com.aetins.customerportal.web.entity.CpQuestionDetail;

@Repository
public class CpQuestionsDetailDAOImpl extends GenericDaoImpl<CpQuestionDetail, Long> implements CpQuestionsDetailDAO {

	private static final Logger logger = Logger.getLogger(CpQuestionsDetailDAOImpl.class);
	
	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	protected Session getSession() {
		
		return sessionFactory.getCurrentSession();
	}

	@Override
	protected Class<CpQuestionDetail> getEntityClass() {
		
		return CpQuestionDetail.class;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<CpQuestionDetail> findAllQuestions() {
		return getSession().createQuery("from CpQuestionDetail").list();

	}

}
