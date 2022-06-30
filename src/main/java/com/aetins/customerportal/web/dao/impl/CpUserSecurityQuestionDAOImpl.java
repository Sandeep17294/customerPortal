package com.aetins.customerportal.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.CpUserSecurityQuestionDAO;
import com.aetins.customerportal.web.entity.CpUserSecurityQuestion;

@Repository
public class CpUserSecurityQuestionDAOImpl extends GenericDaoImpl<CpUserSecurityQuestion, Integer> implements CpUserSecurityQuestionDAO {

   private Logger logger = LoggerFactory.getLogger(CpUserSecurityQuestionDAOImpl.class);
	
	@Resource
	private SessionFactory session;

	
	@Override
	protected Class<CpUserSecurityQuestion> getEntityClass() {
		// TODO Auto-generated method stub
		return CpUserSecurityQuestion.class;
	}

	@Override
	public boolean inserting(CpUserSecurityQuestion UserSecurityQuestion) {
		boolean status=Boolean.TRUE; 
		logger.info("Update Documents Start");
		try{
			save(UserSecurityQuestion);
		}catch (Exception e) {
			status=Boolean.FALSE;
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		return status;

	}

	@Override
	public boolean updating(List<CpUserSecurityQuestion> UserSecurityQuestion) {
		boolean status=Boolean.TRUE; 
		logger.info("Update Documents Start");
		try{
			for(int i=0;i<UserSecurityQuestion.size();i++) {
				CpUserSecurityQuestion cp = new CpUserSecurityQuestion();
				cp=UserSecurityQuestion.get(i);
				update(cp);
				cp=null;
			}
		}catch (Exception e) {
			status=Boolean.FALSE;
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public List<CpUserSecurityQuestion> fetching(CpUserSecurityQuestion cpUserSecurityQuestion) {
		String userid=cpUserSecurityQuestion.getUserid();
		List<CpUserSecurityQuestion> cpuserSecurityQuestion=new  ArrayList<CpUserSecurityQuestion>();
		String[] propertyName = {"userid"};
		Object[] value={userid};
		try{
			cpuserSecurityQuestion=findAllByPropertiesEqual(propertyName,value);
		}catch (Exception e) {
			
			logger.error("Error occured fetchtransactionDAOImpl class ::::::"+e.getMessage());
			e.printStackTrace();
		}
		return cpuserSecurityQuestion;
	}

	@Override
	public boolean delete(CpUserSecurityQuestion UserSecurityQuestion) {
		boolean status=Boolean.TRUE; 
		logger.info("Update Documents Start");
		try{
			remove(UserSecurityQuestion);
		}catch (Exception e) {
			status=Boolean.FALSE;
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	
}
