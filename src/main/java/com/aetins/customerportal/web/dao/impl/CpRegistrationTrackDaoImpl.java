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
import com.aetins.customerportal.web.dao.CpRegistrationTrackDao;
import com.aetins.customerportal.web.entity.CpRegistrationTrack;


@Repository
public class CpRegistrationTrackDaoImpl extends GenericDaoImpl<CpRegistrationTrack, Long> implements CpRegistrationTrackDao {

	private Logger logger = LoggerFactory.getLogger(CpRegistrationTrackDaoImpl.class);
	
	@Resource
	private SessionFactory session;
	
	@Override
	protected Session getSession() {
		return session.getCurrentSession();
	}
	@Override
	protected Class<CpRegistrationTrack> getEntityClass() {
		return CpRegistrationTrack.class;
	}
	
	@Override
	public boolean saveErrTrackDetails(CpRegistrationTrack cpRegistrationTrack) throws Exception{
		boolean status=Boolean.TRUE;
		logger.info("save reg. err track  Start");
		try{			
			save(cpRegistrationTrack);					
		}catch (Exception e) {
			status=Boolean.FALSE;
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return status;
	}

	
	@Override
	public List<CpRegistrationTrack> listAllRegistTrack() {
		List<CpRegistrationTrack> regTrackLists =new ArrayList<CpRegistrationTrack>();
		try{
			regTrackLists = findAll();
		}catch (Exception e) {
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		return regTrackLists;
	}

	

}
