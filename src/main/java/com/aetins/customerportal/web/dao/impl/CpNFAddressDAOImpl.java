package com.aetins.customerportal.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.CpNFAddressDAO;
import com.aetins.customerportal.web.entity.CpNFAddress;


/**
 * This DAO interface for all Address detail management 
 * 01/04/2017
 * @author Viswakarthick
 */
@Repository
public class CpNFAddressDAOImpl extends GenericDaoImpl<CpNFAddress, Long>implements CpNFAddressDAO {

	private static final Logger logger = Logger.getLogger(CpCustomerDetailDAOImpl.class);
	
	@Resource
	private SessionFactory session;
	
	@Override
	protected Session getSession() {
		// TODO Auto-generated method stub
		return session.getCurrentSession();
	}
	
	
	@Override
	protected Class<CpNFAddress> getEntityClass() {
		return CpNFAddress.class;
	}

	@Override
	public boolean saveAddressDetails(List<CpNFAddress> cpNFAddressList) {
		boolean status = Boolean.TRUE;
		logger.info("save Personal details Start");
		try {
			for (CpNFAddress cpNFAddress : cpNFAddressList) {
				save(cpNFAddress);
			}			
		} catch (Exception e) {
			status = Boolean.FALSE;
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	
	@Override
	public List<CpNFAddress> fetchAllAddressDetails(int serviceReqNo) {
		List<CpNFAddress> cpNFAddress = new ArrayList<CpNFAddress>();
		String[] propNames = {"nServiceReqNo.serviceRequestNo"};		
		Object[] values = {serviceReqNo};
		logger.info("List all user cpContributionAlterations retreiving");		
		try {						
			cpNFAddress  = findAllByPropertiesEqual(propNames, values);
		} catch (Exception e) {
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return cpNFAddress;
	}

	

}
