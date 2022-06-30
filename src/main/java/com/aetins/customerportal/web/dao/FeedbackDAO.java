package com.aetins.customerportal.web.dao;


import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpFeedback;

/**
 * This DAO interface for all Feedback management 
 * 30/01/2017
 * @author Viswakarthick
 */
public interface FeedbackDAO extends IGenericDao<CpFeedback, Long>{

	public List<CpFeedback> listAllFeedbacks(String userId);
	
	public List<CpFeedback> getFeedbackInfo(String feedbackNo);
	
	public boolean saveFeedback(CpFeedback cpFeedback);


	public boolean updateFeedbackStatus(CpFeedback cpFeedback);
	
	//business user
	public List<CpFeedback> listAllFeedbacks();
	
	public List<CpFeedback> searchFeedbackList(Object[] values);
	public int getSequenceNo();
}
