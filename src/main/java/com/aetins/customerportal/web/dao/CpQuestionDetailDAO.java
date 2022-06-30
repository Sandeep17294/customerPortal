package com.aetins.customerportal.web.dao;
import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpQuestionDetail;

public interface CpQuestionDetailDAO extends IGenericDao<CpQuestionDetail, Long>{
	
	List<CpQuestionDetail> listSecurityQuestion();
	//public boolean updateSecurityQuestion(CpQuestionDetail cpQuestionDetail);
	public boolean updateSecurityQuestion(List<CpQuestionDetail> cpQuestionDetail);
	public boolean deleteQuestion(CpQuestionDetail detail);
	List<CpQuestionDetail> findAllQuestions();
}
