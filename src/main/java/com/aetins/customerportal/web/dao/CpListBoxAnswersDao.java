package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpListBoxAnswers;

public interface CpListBoxAnswersDao extends IGenericDao<CpListBoxAnswers, Long>{
	public void saveListBoxAnswers(CpListBoxAnswers answers);
	List<CpListBoxAnswers> getAnswers(Long serviceId);
}
