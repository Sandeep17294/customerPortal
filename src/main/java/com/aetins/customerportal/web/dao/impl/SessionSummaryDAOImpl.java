package com.aetins.customerportal.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.SessionSummaryDAO;
import com.aetins.customerportal.web.entity.CpSessionSummary;

@Repository
public class SessionSummaryDAOImpl extends GenericDaoImpl<CpSessionSummary, Long> implements SessionSummaryDAO{

	private Logger logger = LoggerFactory.getLogger(SessionSummaryDAOImpl.class);
	
	@Resource
	private SessionFactory session;
	
	@Override
	protected Session getSession() {
		return session.getCurrentSession();
	}
	
	@Override
	public List<CpSessionSummary> listSessionDetails() {
		// TODO Auto-generated method stub
		List<CpSessionSummary> sessionLists = new ArrayList<CpSessionSummary>();
		try{
			//Added by viswakarthick RFA 1058
			sessionLists=findAllByOrderDesc("dsessionStart");
			
		}catch (Exception e) {
			
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		return sessionLists;
	}
	
	/*
	 * test method
	 */
	@Override
	public List<CpSessionSummary> listSessionDetails(int firestValue,int lastValue) {
		// TODO Auto-generated method stub
		List<CpSessionSummary> sessionLists = new ArrayList<CpSessionSummary>();
		try{
			Query query = getSession().createQuery("from CpSessionSummary");
			query.setMaxResults(firestValue);
			query.setMaxResults(lastValue);
			sessionLists=query.list();
		}catch (Exception e) {
			
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		return sessionLists;
	}
	
	@Override
	public void saveSessionDetails(CpSessionSummary cpSessionSummary) {
		// TODO Auto-generated method stub
		List<CpSessionSummary> sessionLists = new ArrayList<CpSessionSummary>();
		try{
			save(cpSessionSummary);
		}catch (Exception e) {
			
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	protected Class<CpSessionSummary> getEntityClass() {
		// TODO Auto-generated method stub
		return CpSessionSummary.class;
	}
	
	
	public List<CpSessionSummary> fetchCustomerDetails(CpSessionSummary summerysession) {
		List<CpSessionSummary> cpUserList = new ArrayList<CpSessionSummary>();
		logger.info("verifyUserDetails Start");

		try {
			String coluName[] = { "vuserName" };
			String coluData[] = { summerysession.getvPhpSessionId() };
			cpUserList = findByColumeName(coluName, coluData);
		} catch (Exception e) {
			logger.error("Error occured " + e.getMessage());

	}
		
		
		return cpUserList;
	
	}
	
	@Override
	public boolean updatePassword(CpSessionSummary cpUserInfo) {
		// TODO Auto-generated method stub
		boolean status=Boolean.TRUE;
		logger.info("Update Start");
		try{
			update(cpUserInfo);
		}catch (Exception e) {
			status=Boolean.FALSE;
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}	
		return status;
	}

	@Override
	public boolean updateSessionSummary(CpSessionSummary cpSessionSummary) {
		// TODO Auto-generated method stub
		boolean status=Boolean.TRUE;
		logger.info("Update Start");
		String[] propertyName={"vPhpSessionId"};
		Object[] value={cpSessionSummary.getvPhpSessionId()};
		try{
			List<CpSessionSummary> cpSessionSummaryList=findAllByPropertiesEqual(propertyName, value);
			for (CpSessionSummary cpSessionSummary2 : cpSessionSummaryList) {
				cpSessionSummary2.setDsessionEnd(cpSessionSummary.getDsessionEnd());
				cpSessionSummary2.setvLogOff(cpSessionSummary.getvLogOff());
				update(cpSessionSummary2);
			}
			
		}catch (Exception e) {
			status=Boolean.FALSE;
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}	
		return status;
	}

	
	

}
