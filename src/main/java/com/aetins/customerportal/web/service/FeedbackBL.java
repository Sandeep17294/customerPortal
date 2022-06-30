package com.aetins.customerportal.web.service;
import java.util.List;

import com.aetins.customerportal.web.dto.CpFeedbackDTO;
import com.aetins.customerportal.web.dto.CpServiceTypeDTO;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.dto.FeedbackReplyDTO;
import com.aetins.customerportal.web.dto.PasswordRulesDTO;
/**
 * This business logic interface  for all Feedback management 
 * 30/01/2017
 * @author Viswakarthick
 */
public interface FeedbackBL {

	public List<CpFeedbackDTO> listAllFeedbacks(String userId);
	
	public CpFeedbackDTO getFeedbackInfo(String feedbackNo);
	
	public boolean updateFeedbackStatus(CpFeedbackDTO CpFeedbackDTO);
	
	public List<FeedbackReplyDTO> listAllReplys(int feedId);
	
	public boolean saveFeedback(CpFeedbackDTO cpFeedbackDTO);
	
	public PasswordRulesDTO getCpSettingDto();
	
	public boolean saveReply(FeedbackReplyDTO feedbackReplyDTO);
	
	//business user
	
	public List<CpFeedbackDTO> listAllFeedbacks();
	
	public List<CpFeedbackDTO> searchFeedbackList(Object[] values);
	
	/**@author viswa karthick
     * @param userName
     * @return CpUserInfoDTO
     */	
	public CpUserInfoDTO getCpUserInfo(String userName);
	public int getSequenceNoCount() ;

	public List<CpServiceTypeDTO> listServiceType();
}
