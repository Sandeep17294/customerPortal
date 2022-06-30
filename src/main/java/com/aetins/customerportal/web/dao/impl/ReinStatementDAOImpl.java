package com.aetins.customerportal.web.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.ReinStatementDAO;
import com.aetins.customerportal.web.entity.CpLogsErros;
import com.aetins.customerportal.web.entity.CpReinStatement;

@Repository
public class ReinStatementDAOImpl extends GenericDaoImpl<CpReinStatement, Long> implements  ReinStatementDAO{

	private static final Logger logger = Logger.getLogger(ReinStatementDAOImpl.class);
	
	@Resource
	private SessionFactory session;
	
	
	
	public CpReinStatement insertReinStatementDetails(CpReinStatement  reinStatements) {
		
		logger.info(":::: Entering inside insertReinStatementDetails() in ReinStatementDAOImpl ::::");
		boolean status = Boolean.TRUE;
		logger.info("saveUserDetails Start");
		try {
			save(reinStatements);
		} catch (Exception e) {
			status = Boolean.FALSE;
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		logger.info(":::: Return of insertReinStatementDetails() is :::::" +status);
		return reinStatements;
	}
	
	
	
	
	
	@Override
	protected Class<CpReinStatement> getEntityClass() {
		// TODO Auto-generated method stub
		return CpReinStatement.class;
	}
	
	@Override
	public List<CpReinStatement> fetchAllReinstatement(int serviceReqNo) {
		
		logger.info(":::: Entering inside fetchAllReinstatement() Method ::::::");	
		List<CpReinStatement> cpReinStatements = new ArrayList<CpReinStatement>();
		String[] propNames = {"nServicRequestNo.serviceRequestNo"};		
		Object[] values = {serviceReqNo};
			
		try {						
			cpReinStatements  = findAllByPropertiesEqual(propNames, values);
			logger.info(":::: Size of Reinstatement list is ::::::" +cpReinStatements.size());
		} catch (Exception e) {
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return cpReinStatements;
	}
	
	
	public Long getRowCount(String type, String policyNo) {

		String queryString = "select count(*) from CpRequestMaster master where master.serviceRequestType=? and master.policyNo=? and master.requestStatus in('AWIT','AWAP')";
		Query query = getSession().createQuery(queryString);
		query.setString(0, type);
		query.setString(1, policyNo);
		Long count = (Long) query.uniqueResult();
		return count;

	}
	


}
