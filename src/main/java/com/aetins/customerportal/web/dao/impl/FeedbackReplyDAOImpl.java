package com.aetins.customerportal.web.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.FeedbackReplyDAO;
import com.aetins.customerportal.web.entity.CpFeedbackReply;

@Repository
public class FeedbackReplyDAOImpl extends GenericDaoImpl<CpFeedbackReply, Long> implements FeedbackReplyDAO {
	
	private static final Logger logger = Logger.getLogger(FeedbackReplyDAOImpl.class);
	
	List<CpFeedbackReply> cpFeedbackReplies;
	
	CpFeedbackReply cpFeedbackReply;
	
	@Resource
	private SessionFactory session;
	
	@Override
	protected Session getSession() {
		return session.getCurrentSession();
	}
	
	
	@Override
	public List<CpFeedbackReply> listAllReplys(int feedId) {
		String[] propNames = {"nMapFeedbackId.nFeedbackId"};
		/*Object[] values = {feedId,userId};*/
		Object[] values = {feedId};
		logger.info("List all user feedback or complaint retreiving");		
		try {			
			/*String query ="from CpFeedbackReply as cp where cp.nMapFeedbackId.nFeedbackId = ? and cp.vUserId =?";*/					
			/*cpFeedbackReplies = findAllCustomQuery(query,values);*/
			cpFeedbackReplies  = findAllByPropertiesEqual(propNames, values);
		} catch (Exception e) {
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return cpFeedbackReplies;
	}

	@Override
	protected Class<CpFeedbackReply> getEntityClass() {
		return CpFeedbackReply.class;
	}

	@Override
	public boolean saveReply(CpFeedbackReply cpFeedbackReply) {
		boolean status=Boolean.TRUE;
		logger.info("save reply Start");
		try{
			save(cpFeedbackReply);
		}catch (Exception e) {
			status=Boolean.FALSE;
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	

}
