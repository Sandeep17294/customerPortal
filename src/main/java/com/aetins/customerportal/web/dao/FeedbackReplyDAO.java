package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpFeedbackReply;

/**
 * This DAO interface  for all Feedback Reply management 
 * 01/02/2017
 * @author Viswakarthick
 */
public interface FeedbackReplyDAO extends IGenericDao<CpFeedbackReply, Long>{
	
	public List<CpFeedbackReply> listAllReplys( int feedId);
	
	public boolean saveReply(CpFeedbackReply cpFeedbackReply);
}
