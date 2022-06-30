package com.aetins.customerportal.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.CpLogsErrosDAO;
import com.aetins.customerportal.web.dao.ReinStatementBenefitDAO;
import com.aetins.customerportal.web.entity.CpLogsErros;
import com.aetins.customerportal.web.entity.CpReinStatementBenefit;

@Repository
public class ReinStatementBenefitDAOImpl extends GenericDaoImpl<CpReinStatementBenefit,Long>implements ReinStatementBenefitDAO {
	
  private static final Logger logger = Logger.getLogger(ReinStatementBenefitDAOImpl.class);
	
	@Resource
	private SessionFactory session;
	
	@Override
	protected Session getSession() {
		return session.getCurrentSession();
	}
	
//	@Override
//	public void insertbenefitDetails(List<CpReinStatementBenefit> cpReinStatementBenefit) {
//		boolean status = Boolean.TRUE;
//		logger.info("save Documents Start");
//		try {
//			for (CpReinStatementBenefit details : cpReinStatementBenefit) {
//				save(details);
//			}
//		} catch (Exception e) {
//			status = Boolean.FALSE;
//			logger.error("Error occured " + e.getMessage());
//			e.printStackTrace();
//		}
//		return;
//	}

	@Override
	protected Class<CpReinStatementBenefit> getEntityClass() {
		// TODO Auto-generated method stub
		return CpReinStatementBenefit.class;
	}

	
	@Override
	public List<CpReinStatementBenefit> fetchReinstatementBenefit(int serialNo) {

		logger.info(":::: Entering inside fetchAllReinstatement() Method ::::::");
		List<CpReinStatementBenefit> cpReinStatementBenefit = new ArrayList<CpReinStatementBenefit>();
		String[] propNames = { "serialNo.serialNo" };
		Object[] values = { serialNo };

		try {
			cpReinStatementBenefit = findAllByPropertiesEqual(propNames, values);
			logger.info(":::: Size of Reinstatement list is ::::::" + cpReinStatementBenefit.size());
		} catch (Exception e) {
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return cpReinStatementBenefit;

	}

	@Override
	public boolean insertreinstatement(List<CpReinStatementBenefit> cpReinStatementBenefit) {
		boolean status=Boolean.TRUE;
		  logger.info("save Documents Start");
		try{
			for (CpReinStatementBenefit details : cpReinStatementBenefit) {
				save(details);
			}
		}catch (Exception e) {
			 status=Boolean.FALSE;
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
}
