package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpQuestionDetail;

public interface CpQuestionsDetailDAO extends IGenericDao<CpQuestionDetail, Long>{
	List<CpQuestionDetail> findAllQuestions();
}
