package com.aetins.customerportal.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.CpConfigurationDAO;
import com.aetins.customerportal.web.entity.CpConfiguration;

@Repository
public class CpConfigurationDAOImpl extends GenericDaoImpl<CpConfiguration, Long> implements CpConfigurationDAO {

private Logger logger = LoggerFactory.getLogger(CpConfigurationDAOImpl.class);
	
	@Resource
	private SessionFactory session;

	@Override
	public boolean insertdata(List<CpConfiguration> cpConfiguration) {
		boolean status=Boolean.TRUE;
		  logger.info("save Documents Start");
		try{
			for(int i=0;i<cpConfiguration.size();i++) {
				CpConfiguration cp = new CpConfiguration();
				cp=cpConfiguration.get(i);
				save(cp);
				cp=null;
			}	
		}catch (Exception e) {
			 status=Boolean.FALSE;
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	
	@Override
	public List<CpConfiguration> fetchcontent() {
		  logger.info("Fetch Documents Start");
		  List<CpConfiguration> cpConfiguration = new ArrayList<CpConfiguration>();
		try{
			cpConfiguration = findAll();
			
		}catch (Exception e) {
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		return cpConfiguration;
	}
	
	@Override
	public boolean updatedata(List<CpConfiguration> cpConfiguration) {
		boolean status=Boolean.TRUE; 
		logger.info("Update Documents Start");
		try{
			for(int i=0;i<cpConfiguration.size();i++) {
				CpConfiguration cp = new CpConfiguration();
				cp=cpConfiguration.get(i);
				update(cp);
				cp=null;
			}
		}catch (Exception e) {
			status=Boolean.FALSE;
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	@Override
	protected Class<CpConfiguration> getEntityClass() {
		// TODO Auto-generated method stub
		return CpConfiguration.class;
	}

	

	
}
