package com.aetins.customerportal.web.dao.impl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.CpLogsErrosDAO;
import com.aetins.customerportal.web.entity.CpLogsErros;

@Repository
public class CpLogsErrosDAOImpl extends GenericDaoImpl<CpLogsErros, String> implements CpLogsErrosDAO {

	private Logger logger = LoggerFactory.getLogger(CpLogsErrosDAOImpl.class);
	
	@Resource
	private SessionFactory session;
	
	@Override
	protected Class<CpLogsErros> getEntityClass() {
		// TODO Auto-generated method stub
		return CpLogsErros.class;
	}

	@Override
	public boolean inserterror(CpLogsErros cpLogsErros) {
		boolean status=Boolean.TRUE;
		  logger.info("save Documents Start");
		try{
			save(cpLogsErros);
		}catch (Exception e) {
			 status=Boolean.FALSE;
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	
	

}
