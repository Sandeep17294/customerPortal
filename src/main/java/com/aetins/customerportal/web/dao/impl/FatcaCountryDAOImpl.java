package com.aetins.customerportal.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.FatcaCountryDAO;
import com.aetins.customerportal.web.entity.CpFatcaCountryDet;

@Repository
public class FatcaCountryDAOImpl extends GenericDaoImpl<CpFatcaCountryDet, Long>implements FatcaCountryDAO {
	
	private static final Logger logger = Logger.getLogger(DocumentDAOImpl.class);
	
	@Resource
	private SessionFactory session;
	
	@Override
	protected Session getSession() {
		return session.getCurrentSession();
	}

	@Override
	public boolean insertCountryDetails(List<CpFatcaCountryDet> cpFatcaCountryDet) {
		boolean status = Boolean.TRUE;
		logger.info("save Documents Start");
		try {
			for (CpFatcaCountryDet details : cpFatcaCountryDet) {
				save(details);
			}
		} catch (Exception e) {
			status = Boolean.FALSE;
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	@Override
	protected Class<CpFatcaCountryDet> getEntityClass() {
		// TODO Auto-generated method stub
		return CpFatcaCountryDet.class;
	}

	@Override
	public List<CpFatcaCountryDet> fetchFatcaCountryDetails(int serialNo) {

		List<CpFatcaCountryDet> cpFatcaCountryDet = new ArrayList<CpFatcaCountryDet>();
		String[] propNames = { "serialNo.serialNo" };
		Object[] values = { serialNo };
		logger.info("List all fatca Country details retreiving");
		try {
			cpFatcaCountryDet = findAllByPropertiesEqual(propNames, values);
		} catch (Exception e) {
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return cpFatcaCountryDet;
	}

	

}
