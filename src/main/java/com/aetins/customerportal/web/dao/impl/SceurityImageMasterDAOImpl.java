package com.aetins.customerportal.web.dao.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.SecurityImageMasterDAO;
import com.aetins.customerportal.web.entity.CpSecurityImgMaster;

@Repository
public class SceurityImageMasterDAOImpl extends GenericDaoImpl<CpSecurityImgMaster, Long>  implements SecurityImageMasterDAO {

	private static final Logger logger = Logger.getLogger(CpSecurityImgMasterDAOImpl.class);

	@Resource
	private SessionFactory session;
	
	
	@Override
	protected Session getSession() {
		return session.getCurrentSession();
	}
	  
    @Override
    public List<CpSecurityImgMaster> listSecurityImgMaster() {

	List<CpSecurityImgMaster> cpSecurityImgMasterList = new ArrayList<CpSecurityImgMaster>();
	try{
		cpSecurityImgMasterList=findAll();
	}catch (Exception e) {
		
		logger.error("Error occured "+e.getMessage());
		e.printStackTrace();
	}
	return cpSecurityImgMasterList;

    }


	@Override
	protected Class<CpSecurityImgMaster> getEntityClass() {
		 	return CpSecurityImgMaster.class;
	}

	@Override
	public boolean updateSecurityImages1(CpSecurityImgMaster cpSecurityImgMaster) {
		// TODO Auto-generated method stub
		boolean status=Boolean.TRUE;
		logger.info("Update Security Question Start");
		try{
			update(cpSecurityImgMaster) ;
		}catch (Exception e) {
			status=Boolean.FALSE;
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}	
		return status;
	}
	@Override
	public boolean updateSecurityImages(List<CpSecurityImgMaster> cpSecurityImgMaster) {
		// TODO Auto-generated method stub
		boolean status=Boolean.TRUE;
		logger.info("Update Security Question Start");
		try{
			 for (CpSecurityImgMaster securityImage : cpSecurityImgMaster) {
				 	update(securityImage) ;
			 }
		}catch (Exception e) {
			status=Boolean.FALSE;
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}	
		return status;
	}


	
}
