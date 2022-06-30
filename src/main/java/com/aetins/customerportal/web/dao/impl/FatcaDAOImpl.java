package com.aetins.customerportal.web.dao.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.FatcaDAO;
import com.aetins.customerportal.web.entity.CpFatca;


@Repository
public class FatcaDAOImpl extends GenericDaoImpl<CpFatca, Long> implements FatcaDAO {

	
	private static final Logger logger = Logger.getLogger(DocumentDAOImpl.class);
	
	@Resource
	private SessionFactory session;
	
	@Override
	protected Session getSession() {
		return session.getCurrentSession();
	}
	
	
	@Override
	public CpFatca insertFatcaDetails(CpFatca cpFatca) throws Exception {

		logger.info("saveUserDetails Start");
		save(cpFatca);
		return cpFatca;
	}
	

	@Override
	public List<CpFatca> fetchFatcaDetails(int serviceReqNo) {
		
		List<CpFatca> cpFatca = new ArrayList<CpFatca>();
		String[] propNames = { "serviceRequestNo.serviceRequestNo" };
		Object[] values = { serviceReqNo };
		logger.info("List all fatca details retreiving");
		try {
			cpFatca = findAllByPropertiesEqual(propNames, values);
		} catch (Exception e) {
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return cpFatca;
	}
	
	@Override
	protected Class<CpFatca> getEntityClass() {
		// TODO Auto-generated method stub
		return CpFatca.class;
	}


	

}
