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
import com.aetins.customerportal.web.dao.CpNFPersonalDetailsDAO;
import com.aetins.customerportal.web.entity.CpNFPersonalDetails;


/**
 * This DAO interface for all Personal detail management 
 * 01/04/2017
 * @author Viswakarthick
 */
@Repository
public class CpNFPersonalDetailsDAOImpl extends GenericDaoImpl<CpNFPersonalDetails, Long>
		implements CpNFPersonalDetailsDAO {
	
	private static final Logger logger = Logger.getLogger(CpNFPersonalDetailsDAOImpl.class);
	
	@Resource
	private SessionFactory session;
	@Override
	protected Session getSession() {
		// TODO Auto-generated method stub
		return session.getCurrentSession();
	}
	

	@Override
	protected Class<CpNFPersonalDetails> getEntityClass() {
		return CpNFPersonalDetails.class;
	}

	@Override
	public boolean savePersonalDetails(List<CpNFPersonalDetails> cpNFPersonalDetailList) {
		boolean status = Boolean.TRUE;
		logger.info("save Personal details Start");
		try {
			for (CpNFPersonalDetails cpNFPersonalDetails : cpNFPersonalDetailList) {
				save(cpNFPersonalDetails);
			}
			
		} catch (Exception e) {
			status = Boolean.FALSE;
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public List<CpNFPersonalDetails> fetchAllPersonalDetails(int serviceReqNo) {
		List<CpNFPersonalDetails> cpNFPersonalDetails = new ArrayList<CpNFPersonalDetails>();
		String[] propNames = {"nServiceReqNo.serviceRequestNo"};		
		Object[] values = {serviceReqNo};
		logger.info("List all user cpContributionAlterations retreiving");		
		try {						
			cpNFPersonalDetails  = findAllByPropertiesEqual(propNames, values);
		} catch (Exception e) {
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return cpNFPersonalDetails;
	}

	
	
	public Long getRowCount(String type, String policyNo) {

		String queryString = "select count(*) from CpRequestMaster master where master.serviceRequestType=? and master.policyNo=? and master.requestStatus in('AWAP')";
		Query query = getSession().createQuery(queryString);
		query.setString(0, type);
		query.setString(1, policyNo);
		Long count = (Long) query.uniqueResult();
		return count;

	}
	 
}
