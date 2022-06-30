package com.aetins.customerportal.web.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.CpCCEmailDAO;
import com.aetins.customerportal.web.entity.CpEmailSetting;


@Repository
public class CpCCEmailDAOImpl extends GenericDaoImpl<CpEmailSetting, Long> implements CpCCEmailDAO  {

	private Logger logger = LoggerFactory.getLogger(CpCCEmailDAOImpl.class);
	
	@Resource
	private SessionFactory session;
	
	@Override
	protected Session getSession() {
		return session.getCurrentSession();
	}
	
	
	@Override
	protected Class<CpEmailSetting> getEntityClass() {
		return CpEmailSetting.class;
	}
	
	@Override
	public List<CpEmailSetting> fetchCCEmailList(String screenType) {
		
		List<CpEmailSetting> cpEmailSetting = new ArrayList<CpEmailSetting>();
		
		String[] propertyName = {"screenName"};
		Object[] value={screenType};
		try{
			cpEmailSetting=findAllByPropertiesEqual(propertyName, value);
		}catch (Exception e) {
			
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		return cpEmailSetting;
	}
	
	@Override
	public boolean deleteCCMAil(CpEmailSetting cpEmailSetting) {
		
		boolean status=Boolean.TRUE;
		logger.info("Deleting Question Start");
		try{
			remove(cpEmailSetting);						
		}catch (Exception e) {
			status=Boolean.FALSE;
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	
	@Override
	public boolean saveCCEmail(List<CpEmailSetting> cpEmailSetting) {
		// TODO Auto-generated method stub
		boolean status=Boolean.TRUE;
		  logger.info("save Documents Start");
		  try{
		   for (CpEmailSetting ccEmail : cpEmailSetting) {
		     update(ccEmail);
		   }
		  }catch (Exception e) {
		   status=Boolean.FALSE;
		   logger.error("Error occured "+e.getMessage());
		   e.printStackTrace();
		  }
		  return status;
		 }
	
	


	



}
