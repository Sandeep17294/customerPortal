package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpResetSecurityAnswer;

public interface ResetSecurityAnswerDAO  extends IGenericDao<CpResetSecurityAnswer, Long>{

	List<CpResetSecurityAnswer> fetchUserSecurityQuestion(String userName , String questionStatus);
	
	public boolean updateSecurityQuestion(List<CpResetSecurityAnswer> cpQuestionDetail);
	
	public void saveRegistrationQuestions(List<CpResetSecurityAnswer> securityQuestionsDAO);
	
	
}
