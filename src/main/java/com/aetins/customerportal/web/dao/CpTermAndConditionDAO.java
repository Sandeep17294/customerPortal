package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpTermAndCondition;

public interface CpTermAndConditionDAO extends IGenericDao<CpTermAndCondition, Long>{
	
	
	List<CpTermAndCondition> listTermsCondition(String serviceType);
	public boolean updateTermsCondition(List<CpTermAndCondition> cpTermAndCondition);
	public boolean deleteTermsCondition(CpTermAndCondition termsCondition);
	public List<CpTermAndCondition> termAndCondition(String serviceType ,String mendatory ,String required);
	public List<CpTermAndCondition> termAndCondition1(String serviceType ,String mendatory ,String required);
	public boolean saveTermsCondition(List<CpTermAndCondition> cpTermAndCondition);
}
