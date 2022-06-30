package com.aetins.customerportal.web.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.CpNFPersonalContactsDAO;
import com.aetins.customerportal.web.entity.CpNFPersonalContacts;


/**
 * This DAO interface for all Personal detail Contact management 
 * 01/04/2017
 * @author Viswakarthick
 */
@Repository
public class CpNFPersonalContactsDAOImpl extends GenericDaoImpl<CpNFPersonalContacts, Long>
		implements CpNFPersonalContactsDAO {
	
	private static final Logger logger = Logger.getLogger(CpCustomerDetailDAOImpl.class);
	
	@Resource
	private SessionFactory session;
	
	@Override
	protected Session getSession() {
		// TODO Auto-generated method stub
		return session.getCurrentSession();
	}

	@Override
	protected Class<CpNFPersonalContacts> getEntityClass() {
		return CpNFPersonalContacts.class;
	}

	@Override
	public boolean savePersonalContacts(List<CpNFPersonalContacts> cpNFPersonalContactList) {
		boolean status = Boolean.TRUE;
		logger.info("save Personal details Start");
		try {
			for (CpNFPersonalContacts cpNFPersonalContacts : cpNFPersonalContactList) {
				save(cpNFPersonalContacts);
			}
		
		} catch (Exception e) {
			status = Boolean.FALSE;
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	

}
