package com.aetins.customerportal.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.ProtectionBenifitDAO;
import com.aetins.customerportal.web.entity.CpProtectionBenifit;

@Repository
public class ProtectionBenifitDAOImpl extends GenericDaoImpl<CpProtectionBenifit, Long> implements ProtectionBenifitDAO {

	
	private static final Logger logger = Logger.getLogger(ProtectionBenifitDAOImpl.class);
	
	@Resource
	private SessionFactory session;
	
	
	@Override
	protected Session getSession() {
		return session.getCurrentSession();
	}
	
	@Override
	public boolean saveProtectionBenifit(List<CpProtectionBenifit> cpProtectionBenifit) {

		boolean status = Boolean.TRUE;
		try {
			logger.info("save Documents Start :::::::::::");
			for (CpProtectionBenifit each : cpProtectionBenifit) {
				
				save(each);
			}
		} catch (Exception e) {
			status = Boolean.FALSE;
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		logger.info("Status for save :::::::::::::" + status);
		return status;

	}

	@Override
	public List<CpProtectionBenifit> fetchAllProtectionBenifit(int serviceReqNo) {

		List<CpProtectionBenifit> cpProtectionBenifit = new ArrayList<CpProtectionBenifit>();
		String[] propNames = { "serviceRequestNo.serviceRequestNo" };
		Object[] values = { serviceReqNo };
		logger.info("List all user Reinstatement retreiving");
		try {
			cpProtectionBenifit = findAllByPropertiesEqual(propNames, values);
		} catch (Exception e) {
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return cpProtectionBenifit;
	}

	@Override
	protected Class<CpProtectionBenifit> getEntityClass() {
		// TODO Auto-generated method stub
		return CpProtectionBenifit.class;
	}

	

}
