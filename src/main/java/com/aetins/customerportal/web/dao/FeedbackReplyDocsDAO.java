package com.aetins.customerportal.web.dao;

import com.aetins.customerportal.web.entity.CPFeedbackReplyUploadDocs;
import com.aetins.customerportal.web.common.IGenericDao;

public interface FeedbackReplyDocsDAO extends IGenericDao<CPFeedbackReplyUploadDocs, Integer>{
	
	
	

	/**
	 * To get {@link CPFeedbackReplyUploadDocs} by ReplyDocId
	 * @param replyId
	 * @return CPFeedbackReplyUploadDocs
	 */
	public CPFeedbackReplyUploadDocs getReplyDocsByRoleId(int replyId);
}
