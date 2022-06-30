package com.aetins.customerportal.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.CpPartialWithdrawalFundsDAO;
import com.aetins.customerportal.web.dto.CpPartialWithdrawalFundsDTO;
import com.aetins.customerportal.web.entity.CpPartialWithdrawalFunds;
import com.aetins.customerportal.web.entity.CpProtectionBenifit;
import com.aetins.customerportal.web.entity.CpRedirectionSwitching;

@Repository
public class CpPartialWithdrawalFundsDAOImpl extends GenericDaoImpl<CpPartialWithdrawalFunds, Long> implements CpPartialWithdrawalFundsDAO {

private Logger logger = LoggerFactory.getLogger(CpPartialWithdrawalFundsDAOImpl.class);
	
	@Resource
	private SessionFactory session;

	@Override
	protected Class<CpPartialWithdrawalFunds> getEntityClass() {
		// TODO Auto-generated method stub
		return CpPartialWithdrawalFunds.class;
	}

	@Override
	public boolean insert(List<CpPartialWithdrawalFunds> cPartialWithdrawalFundsdto) {
		boolean status=Boolean.TRUE;
		  logger.info("save Documents Start");
		try{
            for (CpPartialWithdrawalFunds each : cPartialWithdrawalFundsdto) {	
				save(each);
			}	
		}catch (Exception e) {
			 status=Boolean.FALSE;
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public List<CpPartialWithdrawalFunds> fetchAllPartialwithdraw(int serviceReqNo) {
		List<CpPartialWithdrawalFunds> cpPartialWithdrawalFunds = new ArrayList<CpPartialWithdrawalFunds>();
		String[] propNames = {"nServicRequestNo.serviceRequestNo"};		
		Object[] values = {serviceReqNo};
		logger.info("List all user cpRedirectionSwitchings retreiving");		
		try {						
			cpPartialWithdrawalFunds  = findAllByPropertiesEqual(propNames, values);
		} catch (Exception e) {
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return cpPartialWithdrawalFunds;
	}


}
