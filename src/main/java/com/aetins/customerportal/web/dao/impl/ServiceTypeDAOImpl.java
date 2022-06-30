package com.aetins.customerportal.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.ServiceTypeDAO;
import com.aetins.customerportal.web.entity.CpServiceType;
@Repository
public class ServiceTypeDAOImpl extends GenericDaoImpl<CpServiceType, Long> implements ServiceTypeDAO {

	
	private static final Logger logger = Logger.getLogger(ServiceTypeDAOImpl.class);

    @Resource
	private SessionFactory session;
	
	
	@Override
	protected Session getSession() {
		return session.getCurrentSession();
	}
	
	@Override
	public List<CpServiceType> listserviceType() {
		// TODO Auto-generated method stub
		List<CpServiceType> serviceTypeList=new ArrayList<CpServiceType>();
		try{
			serviceTypeList=findAll();
		}catch (Exception e) {
			
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		return serviceTypeList;
	}

	@Override
	protected Class<CpServiceType> getEntityClass() {
		// TODO Auto-generated method stub
		return CpServiceType.class;
	}
	
}
