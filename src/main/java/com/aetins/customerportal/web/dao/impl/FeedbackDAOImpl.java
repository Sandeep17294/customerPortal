package com.aetins.customerportal.web.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.FeedbackDAO;
import com.aetins.customerportal.web.entity.CpFeedback;


/**
 * This DAOimpl class for all Feedback management 
 * 30/01/2017
 * @author Viswakarthick
 */
@Repository
public class FeedbackDAOImpl extends GenericDaoImpl<CpFeedback, Long> implements FeedbackDAO {
	
	private static final Logger logger = Logger.getLogger(FeedbackDAOImpl.class);
	
	List<CpFeedback> cpFeedbacks;
	
	CpFeedback cpFeedback;
	
	@Resource
	private SessionFactory session;
	
	@Override
	protected Session getSession() {
		return session.getCurrentSession();
	}
	
	@Override
	protected Class<CpFeedback> getEntityClass() {
		return CpFeedback.class;
	}

	@Override
	public List<CpFeedback> listAllFeedbacks(String userId) {

		logger.info("List all user feedback or complaint retreiving");		
		String[] propertyName={"vUserId"};
		Object[] value={userId};
		try {
			cpFeedbacks = findAllByPropertiesEqual(propertyName, value);
		} catch (Exception e) {
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return cpFeedbacks;
	}

	@Override
	public List<CpFeedback> getFeedbackInfo(String feedbackNo) {
		
		logger.info("List user feedback or complaint retreiving");	
		String[] propertyName={"vComplaintNo"};
		Object[] value={feedbackNo};
		try{
			cpFeedbacks = findAllByPropertiesEqual(propertyName, value);
		}catch (Exception e) {
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return cpFeedbacks;
	}

	@Override
	public boolean saveFeedback(CpFeedback cpFeedback) {
		boolean status=Boolean.TRUE;
		logger.info("saveUserDetails Start");
		try{
			save(cpFeedback);
		}catch (Exception e) {
			status=Boolean.FALSE;
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		return status;
	}


	@Override
	public boolean updateFeedbackStatus(CpFeedback cpFeedback) {
		
		boolean status=Boolean.TRUE;
		logger.info("update Feedback Status Start");
		try{
			update(cpFeedback);
		}catch (Exception e) {
			status=Boolean.FALSE;
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public List<CpFeedback> listAllFeedbacks() {
		logger.info("List all user feedback or complaint retreiving");		
		try {
			cpFeedbacks = findAll();			
		} catch (Exception e) {
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return cpFeedbacks;
	}

	@Override
	public List<CpFeedback> searchFeedbackList(Object[] values) {
		logger.info("List all user feedback or complaint retreiving");				
		String query=null;
		if(values.length==3 && (values[0] instanceof String) && (values[1] instanceof String) && (values[2] instanceof Date)){			
			query="from CpFeedback where vUserId like ? and vFeedStatus = ? and dCompDate <= ?";			
		}else if(values.length==3 && (values[0] instanceof String) && (values[1] instanceof Date) && (values[2] instanceof Date)){
			if(values[0].equals("OPEN")||values[0].equals("CLOSED")){
				query="from CpFeedback where vFeedStatus = ? and dCompDate >= ? and dCompDate <= ?";
			}
		}
		else if(values.length==2 && (values[0] instanceof String) && (values[1] instanceof Date)){
			if(values[0].equals("OPEN")||values[0].equals("CLOSED"))
				query="from CpFeedback where vFeedStatus = ? and dCompDate <= ?";
			else
				query="from CpFeedback where vUserId like ?  and dCompDate <= ?";
		}
		else if(values.length==2 && (values[0] instanceof Date) && (values[1] instanceof Date)){
			query="from CpFeedback where dCompDate >= ? and dCompDate <= ?";			
		}		
		else if(values.length == 4){			
			query="from CpFeedback where vUserId like ? and vFeedStatus = ? and dCompDate >= ? and dCompDate <= ?";			
		}
		else if(values.length == 1){			
			query="from CpFeedback where dCompDate <= ?";			
		}
		if(values.length==3 && (values[0] instanceof String) && (values[1] instanceof Date) && (values[2] instanceof Date)){
			if(!(values[0].equals("OPEN")||values[0].equals("CLOSED"))){
				query="from CpFeedback where vUserId like ? and dCompDate >= ? and dCompDate <= ?";
			}
		}
		try {			
			cpFeedbacks = findAllCustomQuery(query, values);
			
		} catch (Exception e) {
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return cpFeedbacks;
	}
	
	@Override
	public int getSequenceNo() {
		int values=0;
		
		logger.info("getSequenceNo =====");
		try{
			values=countNoOFuniqueRecord("nFeedbackId");
		}catch (Exception e) {
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		return values;
	}

	
}
