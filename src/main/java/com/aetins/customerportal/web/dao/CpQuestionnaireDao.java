package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpQuestionnaire;

public interface CpQuestionnaireDao extends IGenericDao<CpQuestionnaire, Long>{
	boolean saveQuestionnaire(CpQuestionnaire dto);
	List<CpQuestionnaire> questionnaireList(String serviceType);
	CpQuestionnaire findLatestRecord(String query);
	boolean saveAnswers(CpQuestionnaire answer);
}
