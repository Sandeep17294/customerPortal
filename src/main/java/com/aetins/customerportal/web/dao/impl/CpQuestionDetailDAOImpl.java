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
import com.aetins.customerportal.web.dao.CpQuestionDetailDAO;
import com.aetins.customerportal.web.entity.CpQuestionDetail;

@Repository
public class CpQuestionDetailDAOImpl  extends GenericDaoImpl<CpQuestionDetail, Long> implements CpQuestionDetailDAO {

	private Logger  logger = LoggerFactory.getLogger(CpQuestionDetailDAOImpl.class);
	
	@Resource
	private SessionFactory session;
	
	@Override
	protected Session getSession() {
		return session.getCurrentSession();
	}

	@Override
	public List<CpQuestionDetail> listSecurityQuestion() {
		// TODO Auto-generated method stub
		List<CpQuestionDetail> questionList= new ArrayList<CpQuestionDetail>();
		try{
			questionList=findAll();
		}catch (Exception e) {
			
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		return questionList;
	}

	@Override
	protected Class<CpQuestionDetail> getEntityClass() {
		// TODO Auto-generated method stub
		return CpQuestionDetail.class;
	}

	@Override
	public boolean updateSecurityQuestion(List<CpQuestionDetail> cpQuestionDetail) {
		// TODO Auto-generated method stub
		boolean status=Boolean.TRUE;
		  logger.info("save security questions");
		  try{
		   for (CpQuestionDetail details : cpQuestionDetail) {
			   saveOrUpdate(details);
		   }
		   
		  }catch (Exception e) {
		   status=Boolean.FALSE;
		   logger.error("Error occured "+e.getMessage());
		   e.printStackTrace();
		  }
		  return status;
		 }
		
	@Override
	public boolean deleteQuestion(CpQuestionDetail detail) {
		boolean status=Boolean.TRUE;
		logger.info("Deleting Question Start");
		try{
			remove(detail);						
		}catch (Exception e) {
			status=Boolean.FALSE;
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		return status;
		
	}

	@Override
	public List<CpQuestionDetail> findAllQuestions() {
		return findAll();
	}

	
	/*@Override
	public boolean updateSecurityQuestion(CpQuestionDetail cpQuestionDetail) {
		// TODO Auto-generated method stub
		boolean status = Boolean.TRUE;
		logger.info("Update Security Question Start");
		try {
			update(cpQuestionDetail);
		} catch (Exception e) {
			status = Boolean.FALSE;
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return status;
	}*/

}
