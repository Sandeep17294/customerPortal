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
import com.aetins.customerportal.web.dao.CpTermAndConditionDAO;
import com.aetins.customerportal.web.entity.CpTermAndCondition;


@Repository
public class CpTermAndConditionDAOImpl extends GenericDaoImpl<CpTermAndCondition, Long> implements CpTermAndConditionDAO {

	
	private Logger logger = LoggerFactory.getLogger(CpTermAndConditionDAOImpl.class);
	
	@Resource
	private SessionFactory session;
	

	@Override
	protected Session getSession() {
		return session.getCurrentSession();
	}
	
	@Override
	protected Class<CpTermAndCondition> getEntityClass() {
		// TODO Auto-generated method stub
		return CpTermAndCondition.class;
	}

	

	@Override
	public List<CpTermAndCondition> listTermsCondition(String serviceType) {
		List<CpTermAndCondition> termsCondition = new ArrayList<CpTermAndCondition>();
		String[] propertyName = { "vpageName" };
		Object[] value = { serviceType };
		try {
			termsCondition = findAllByPropertiesEqual(propertyName, value);
		} catch (Exception e) {

			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return termsCondition;
	}

	@Override
	public boolean updateTermsCondition(List<CpTermAndCondition> cpTermAndCondition) {
		boolean status=Boolean.TRUE;
		  logger.info("save Documents Start");
		  try{
		   for (CpTermAndCondition condition : cpTermAndCondition) {
		     update(condition);
		   }
		   
		  }catch (Exception e) {
		   status=Boolean.FALSE;
		   logger.error("Error occured "+e.getMessage());
		   e.printStackTrace();
		  }
		  return status;
		 }
	
	@Override
	public boolean saveTermsCondition(List<CpTermAndCondition> cpTermAndCondition) {
		boolean status=Boolean.TRUE;
		  logger.info("save Documents Start");
		  try{
		   for (CpTermAndCondition condition : cpTermAndCondition) {
			   saveOrUpdate(condition);
		   }
		   
		  }catch (Exception e) {
		   status=Boolean.FALSE;
		   logger.error("Error occured "+e.getMessage());
		   e.printStackTrace();
		  }
		  return status;
		 }
	
	@Override
	public boolean deleteTermsCondition(CpTermAndCondition termsCondition) {
		boolean status=Boolean.TRUE;
		logger.info("Deleting Question Start");
		try{
			remove(termsCondition);						
		}catch (Exception e) {
			status=Boolean.FALSE;
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		return status;
		
	}
	
	
	@Override
	public List<CpTermAndCondition> termAndCondition(String serviceType ,String mendatory ,String required) {
		// TODO Auto-generated method stub
		List<CpTermAndCondition> termList = new ArrayList<CpTermAndCondition>();
		String[] propertyName = {"vpageName","vmend","vrequired"};
		Object[] value={serviceType,mendatory,required};
		String pptyName="norder";
		try{
			termList=findAllWithAscending(propertyName, value , pptyName);
		}catch (Exception e) {
			
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		return termList;
	}
	
	
	@Override
	public List<CpTermAndCondition> termAndCondition1(String serviceType ,String mendatory ,String required) {
		// TODO Auto-generated method stub
		List<CpTermAndCondition> termList = new ArrayList<CpTermAndCondition>();
		String[] propertyName = {"vpageName","vmend","vrequired"};
		Object[] value={serviceType,mendatory,required};
		String pptyName="norder";
		try{
			termList=findAllWithAscending(propertyName, value , pptyName);
		}catch (Exception e) {
			
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		return termList;
	}

	
	public static void main(String[] args) {
		
		CpTermAndConditionDAOImpl dao = new CpTermAndConditionDAOImpl();
		List<CpTermAndCondition> op = dao.termAndCondition("CONTRIBUTION HOLIDAY", "Y", "Y");
	    System.out.println();
	
	}

	
	
	
	}

