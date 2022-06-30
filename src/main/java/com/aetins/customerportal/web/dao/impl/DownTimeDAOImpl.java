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
import com.aetins.customerportal.web.dao.DownTimeDAO;
import com.aetins.customerportal.web.entity.CpServerstatusSetting;

@Repository
public class DownTimeDAOImpl extends GenericDaoImpl<CpServerstatusSetting, Long> implements DownTimeDAO {

	
	private Logger logger = LoggerFactory.getLogger(DownTimeDAOImpl.class);
	
	@Resource
	private SessionFactory session;
	
	@Override
	protected Session getSession() {
		return session.getCurrentSession();
	}
	
	@Override
	public boolean insertDownTime(CpServerstatusSetting cpServerstatusSetting) {
		boolean status = Boolean.TRUE;
		logger.info("saveUserDetails Start");
		try {
			save(cpServerstatusSetting);
		} catch (Exception e) {
			status = Boolean.FALSE;
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public List<CpServerstatusSetting> listDownTime() {
		// TODO Auto-generated method stub
		List<CpServerstatusSetting> cpServerstatusSettingList= new ArrayList<CpServerstatusSetting>();
		try{
			cpServerstatusSettingList=findAll();
		}catch (Exception e) {
			
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		return cpServerstatusSettingList;
	}
	
	@Override
	public boolean updateDownTime(CpServerstatusSetting cpServerstatusSetting) {
		// TODO Auto-generated method stub
		boolean status=Boolean.TRUE;
		logger.info("Update Start");
		try{
			update(cpServerstatusSetting);
		}catch (Exception e) {
			status=Boolean.FALSE;
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}	
		return status;
	}

	// added by Harmain to fetch DownTime

	@Override
	public List<CpServerstatusSetting> fetchDownTime(CpServerstatusSetting downTimeStatus) {
		List<CpServerstatusSetting> cpServerStatus = new ArrayList<CpServerstatusSetting>();
		logger.info("verifyDownTimeDetails Begin");

		try {

			cpServerStatus = findAll();
		} catch (Exception e) {
			logger.error("Error occured " + e.getMessage());

		}
		return cpServerStatus;

	}

	
	@Override
	protected Class<CpServerstatusSetting> getEntityClass() {
		return CpServerstatusSetting.class;
	}


}
