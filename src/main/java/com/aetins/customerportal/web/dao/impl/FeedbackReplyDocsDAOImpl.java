package com.aetins.customerportal.web.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.FeedbackReplyDocsDAO;
import com.aetins.customerportal.web.entity.CPFeedbackReplyUploadDocs;



@Repository
public class FeedbackReplyDocsDAOImpl extends GenericDaoImpl<CPFeedbackReplyUploadDocs, Integer>implements FeedbackReplyDocsDAO {

	@Resource
	private SessionFactory session;
	
	@Override
	protected Session getSession() {
		return session.getCurrentSession();
	}
	
	@Override
	protected Class<CPFeedbackReplyUploadDocs> getEntityClass() {
		return CPFeedbackReplyUploadDocs.class;
	}

	@Override
	public CPFeedbackReplyUploadDocs getReplyDocsByRoleId(int replyId){
		String queryString = "select * from cp_feedback_reply_docs where N_REPLY_ID =?";
		Query query = getSession().createSQLQuery(queryString).addEntity(CPFeedbackReplyUploadDocs.class);
		query.setString(0, replyId+"");
		return (CPFeedbackReplyUploadDocs) query.uniqueResult();
	}
	
}
