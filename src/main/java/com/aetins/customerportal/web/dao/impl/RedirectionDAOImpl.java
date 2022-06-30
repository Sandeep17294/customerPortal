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
import com.aetins.customerportal.web.dao.RedirectionDAO;
import com.aetins.customerportal.web.entity.CpRedirectionSwitching;

@Repository
public class RedirectionDAOImpl extends GenericDaoImpl<CpRedirectionSwitching, Long> implements RedirectionDAO {

	private static final Logger logger = Logger.getLogger(RedirectionDAOImpl.class);
	
	@Resource
	private SessionFactory session;
	
	@Override
	protected Session getSession() {
		return session.getCurrentSession();
	}
	
	@Override
	public boolean saveFundDetails(List<CpRedirectionSwitching> cpRedirectionSwitching) {
		// TODO Auto-generated method stub
		boolean status = Boolean.TRUE;
		logger.info("save Documents Start");
		try {
			for (CpRedirectionSwitching details : cpRedirectionSwitching) {
				save(details);
			}

		} catch (Exception e) {
			logger.info("Error at Data Saving::::::::::::::");
			status = Boolean.FALSE;
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	
	

	@Override
	protected Class<CpRedirectionSwitching> getEntityClass() {
		// TODO Auto-generated method stub
		return CpRedirectionSwitching.class;
	}

    

	@Override
	public List<CpRedirectionSwitching> fetchAllRedirectandSwitchings(int serviceReqNo) {
		List<CpRedirectionSwitching> cpRedirectionSwitchings = new ArrayList<CpRedirectionSwitching>();
		String[] propNames = {"serviceRequestNo.serviceRequestNo"};		
		Object[] values = {serviceReqNo};
		logger.info("List all user cpRedirectionSwitchings retreiving");		
		try {						
			cpRedirectionSwitchings  = findAllByPropertiesEqual(propNames, values);
		} catch (Exception e) {
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return cpRedirectionSwitchings;
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
