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
import com.aetins.customerportal.web.dao.CpNFAddressContactsDAO;
import com.aetins.customerportal.web.entity.CpNFAddressContacts;


/**
 * This DAO interface for all address detail Contact management 
 * 01/04/2017
 * @author Viswakarthick
 */
@Repository
public class CpNFAddressContactsDAOImpl extends GenericDaoImpl<CpNFAddressContacts, Long>
		implements CpNFAddressContactsDAO {

	private Logger logger = LoggerFactory.getLogger(CpNFAddressContactsDAOImpl.class);
	
	@Resource
	private SessionFactory session;
	
	@Override
	protected Session getSession() {
		return session.getCurrentSession();
	}
	
	
	@Override
	protected Class<CpNFAddressContacts> getEntityClass() {
		return CpNFAddressContacts.class;
	}

	@Override
	public boolean saveAddressContacts(List<CpNFAddressContacts> cpNFAddressContactList) {
		boolean status = Boolean.TRUE;
		logger.info("save Personal details Start");
		try {
			for (CpNFAddressContacts cpNFAddressContacts : cpNFAddressContactList) {
				save(cpNFAddressContacts);
			}			
		} catch (Exception e) {
			status = Boolean.FALSE;
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	
	@Override
	public List<CpNFAddressContacts> fetchAllAddressContactDetails(int serviceReqNo) {
		List<CpNFAddressContacts> cpNFAddressContacts = new ArrayList<CpNFAddressContacts>();
		String[] propNames = {"nServiceReqNo.serviceRequestNo"};		
		Object[] values = {serviceReqNo};
		logger.info("List all user cpContributionAlterations retreiving");		
		try {						
			cpNFAddressContacts  = findAllByPropertiesEqual(propNames, values);
		} catch (Exception e) {
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return cpNFAddressContacts;
	}

	

}
