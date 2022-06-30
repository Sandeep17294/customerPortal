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
import com.aetins.customerportal.web.dao.ContributionAlterDAO;
import com.aetins.customerportal.web.entity.CpContributionAlteration;

@Repository
public class ContributionAlterDAOImpl extends GenericDaoImpl<CpContributionAlteration, Long>
		implements ContributionAlterDAO {
	
	private Logger logger = LoggerFactory.getLogger(ContributionAlterDAOImpl.class);
	
	@Resource
	private SessionFactory session;
	
	@Override
	protected Session getSession() {
		// TODO Auto-generated method stub
		return session.getCurrentSession();
	}


	@Override
	public boolean saveChangeDetails(CpContributionAlteration cpContributionAlteration) {
		boolean status = Boolean.TRUE;
		logger.info("save Documents Start");
		try {
			// CpContributionAlteration details: cpContributionAlteration
			save(cpContributionAlteration);
		} catch (Exception e) {
			status = Boolean.FALSE;
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return status;

	}

	@Override
	protected Class<CpContributionAlteration> getEntityClass() {
		// TODO Auto-generated method stub
		return CpContributionAlteration.class;
	}

	@Override
	public List<CpContributionAlteration> fetchAllContrAlter(int serviceReqNo) {
		List<CpContributionAlteration> cpContributionAlterations = new ArrayList<CpContributionAlteration>();
		String[] propNames = { "serviceRequestNo.serviceRequestNo" };
		Object[] values = { serviceReqNo };
		logger.info("List all user cpContributionAlterations retreiving");
		try {
			cpContributionAlterations = findAllByPropertiesEqual(propNames, values);
		} catch (Exception e) {
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return cpContributionAlterations;
	}

	@Override
	public CpContributionAlteration findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/*
	 * public Long getRowCount(String type, String policyNo) {
	 * 
	 * openSession(); beginTransaction(); String queryString =
	 * "select count(*) from CpRequestMaster master where master.serviceRequestType=? and master.policyNo=? and master.requestStatus in('AWIT','AWAP')"
	 * ; Query query = session.createQuery(queryString); query.setString(0, type);
	 * query.setString(1, policyNo); Long count = (Long) query.uniqueResult();
	 * logger.info("Count for Cont Alt" + count); return count;
	 * 
	 * }
	 */

}
