package com.aetins.customerportal.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.CpRequestMasterDAO;
import com.aetins.customerportal.web.entity.CpRequestMaster;


@Repository
public class CpRequestMasterDAOImpl extends GenericDaoImpl<CpRequestMaster, Integer> implements  CpRequestMasterDAO {
	
	private static final Logger logger = Logger.getLogger(CpRequestMasterDAOImpl.class);
	
	@Resource
	private SessionFactory session;
	
	@Override
	protected Session getSession() {
		return session.getCurrentSession();
	}
	
	
	@Override
	public CpRequestMaster saveNewUser(CpRequestMaster cprequestMaster) {
		
		// TODO Auto-generated method stub		
		try{
			save(cprequestMaster);			
		}catch (Exception e) {			
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		return cprequestMaster;
	}
	
	@Override
	public boolean updateColumns(CpRequestMaster cprequestMaster) {
		// TODO Auto-generated method stub
		boolean status=Boolean.TRUE;
		logger.info("Update Start");
		try{
			CpRequestMaster entity = findById(cprequestMaster.getServiceRequestNo());			
			entity.setRecentUpdate(cprequestMaster.getRecentUpdate());
			entity.setRequestStatus(cprequestMaster.getRequestStatus());
			entity.setFatcaFlag(cprequestMaster.getFatcaFlag());
			update(entity);
		}catch (Exception e) {
			status=Boolean.FALSE;
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}	
		return status;
	}
	
	@Override
	public boolean saveOTP(CpRequestMaster cprequestMaster) {
		// TODO Auto-generated method stub
		boolean status=Boolean.TRUE;
		logger.info("Update Start");
		try{
			CpRequestMaster entity = findById(cprequestMaster.getServiceRequestNo());			
			entity.setUserOtp(cprequestMaster.getUserOtp());
			update(entity);
		}catch (Exception e) {
			status=Boolean.FALSE;
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}	
		return status;
	}
	
	@Override
	protected Class<CpRequestMaster> getEntityClass() {
		// TODO Auto-generated method stub
		return CpRequestMaster.class;
	}

	@Override
	public CpRequestMaster fetchRequestMasters(CpRequestMaster cprequestMaster) {

		try {
			logger.info("fetch Start");
			cprequestMaster = findById(cprequestMaster.getServiceRequestNo());
		} catch (Exception e) {
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return cprequestMaster;
	}
	
	
	@Override
	public List<CpRequestMaster> fetchTransactionRequestList(String userid) {
		// TODO Auto-generated method stub
		logger.info("Entering into fetchtransactionDAOImpl class ::::::::::::::::::::");
		List<CpRequestMaster> CpRequestMasterList=new ArrayList<CpRequestMaster>();
		String[] propertyName = {"userId"};
		Object[] value={userid};
		try{
			CpRequestMasterList=findAllByPropertiesEqual(propertyName, value);
			logger.info("size of master list  fetchtransactionDAOImpl class ::::::::::::::::::::" +CpRequestMasterList.size());
		}catch (Exception e) {
			
			logger.error("Error occured fetchtransactionDAOImpl class ::::::"+e.getMessage());
			e.printStackTrace();
		}
		return CpRequestMasterList;
	}

	/*
	 * public List<CpRequestMaster> getTransactionCount(String userid) {
	 * openSession(); beginTransaction(); String queryString =
	 * "select  CpRequestMaster  where master.userId=? and master.requestStatus in('AWIT','AWAP','SUCC','REJ')"
	 * ; Query query = session.createQuery(queryString); query.setString(0, userid);
	 * List<CpRequestMaster> trabList = query.list();
	 * 
	 * return trabList; }
	 */



	@Override
	public List<CpRequestMaster> getTransactionCount(String userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deletetransaction(List<CpRequestMaster> CpRequestMaster) {
		// TODO Auto-generated method stub
		for(int i=0;i<CpRequestMaster.size();i++) {
			int reqno = CpRequestMaster.get(i).getServiceRequestNo();
			String propertyValues = "serviceRequestNo";
			deleteByProperty(propertyValues,reqno);
		}
		return true;
	}


	@Override
	public boolean deletetransaction(CpRequestMaster CpRequestMaster) {
		int reqno = CpRequestMaster.getServiceRequestNo();
		String propertyValues = "serviceRequestNo";
		deleteByProperty(propertyValues,reqno);
		return false;
	}


	@Override
	public List<CpRequestMaster> servicerequestfetch() {
		List<CpRequestMaster> totalfetch = new ArrayList<CpRequestMaster>();
             totalfetch = findAll();
		// TODO Auto-generated method stub
		return totalfetch;
	}
	
	

}
