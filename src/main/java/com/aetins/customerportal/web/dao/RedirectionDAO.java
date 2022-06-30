package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpRedirectionSwitching;

public interface RedirectionDAO extends IGenericDao<CpRedirectionSwitching, Long>{
	
	public boolean saveFundDetails(List<CpRedirectionSwitching> cpRedirectionSwitching);
    
	public List<CpRedirectionSwitching> fetchAllRedirectandSwitchings(int serviceReqNo);
	
	public Long getRowCount(String type, String policyNo);
}
