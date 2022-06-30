package com.aetins.customerportal.web.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.OtpSetUpDAO;
import com.aetins.customerportal.web.entity.CpOtpSettings;


@Repository
public class OtpSetUpDAOImpl extends GenericDaoImpl<CpOtpSettings, Long> implements OtpSetUpDAO{

	
	private Logger logger = LoggerFactory.getLogger(OtpSetUpDAOImpl.class);
	
	@Resource
	private SessionFactory session;
	
	@Override
	protected Session getSession() {
		return session.getCurrentSession();
	}
	
	@Override
	public List<CpOtpSettings> listOtpSettings(String serviceType) {
		
		List<CpOtpSettings> otpRules=new ArrayList<CpOtpSettings>();
		String[] propertyName = {"vServiceType"};
		Object[] value={serviceType};
		try{
			otpRules=findAllByPropertiesEqual(propertyName, value);
		}catch (Exception e) {
			
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		return otpRules;
	}

	@Override
	public boolean updateOtpSettings(CpOtpSettings cpOtpSettings) {
		
		boolean status=Boolean.TRUE;
		logger.info("Update Password Rules Start");
		try{			
			update(cpOtpSettings);
		}catch (Exception e) {
			status=Boolean.FALSE;
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}	
		return status;
		
	}

	@Override
	protected Class<CpOtpSettings> getEntityClass() {
		return CpOtpSettings.class;
	}

	

}
