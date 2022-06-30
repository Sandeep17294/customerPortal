package com.aetins.customerportal.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.CpClaimIntimationDAO;
import com.aetins.customerportal.web.entity.CpClaimIntimation;
import com.aetins.customerportal.web.entity.CpPartialWithdrawalFunds;
import com.aetins.customerportal.web.entity.CpRequestMaster;

@Repository
public class CpClaimIntimationDAOImpl extends GenericDaoImpl<CpClaimIntimation, Integer> implements CpClaimIntimationDAO{
	
private Logger logger = LoggerFactory.getLogger(CpLogsErrosDAOImpl.class);
	
	@Resource
	private SessionFactory session;
	
	@Override
	public boolean insert(CpClaimIntimation cpClaimIntimation) {
		boolean status=Boolean.TRUE;
		  logger.info("save Documents Start");
		try{
			save(cpClaimIntimation);
		}catch (Exception e) {
			 status=Boolean.FALSE;
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	@Override
	protected Class<CpClaimIntimation> getEntityClass() {
		// TODO Auto-generated method stub
		return CpClaimIntimation.class;
	}

	@Override
	public List<CpClaimIntimation> fetchall(int service) {
		List<CpClaimIntimation> cpClaimIntimation = new ArrayList<CpClaimIntimation>();
		String[] propNames = {"nServicRequestNo.serviceRequestNo"};		
		Object[] values = {service};
		logger.info("List all user cpRedirectionSwitchings retreiving");		
		try {						
			cpClaimIntimation  =  findAllByPropertiesEqual(propNames, values);
		} catch (Exception e) {
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return cpClaimIntimation;
	}

}
