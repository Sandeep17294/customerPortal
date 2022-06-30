package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpContributionHoliday;
import com.aetins.customerportal.web.entity.CpRequestMaster;



public interface ContributionHolidayDAO extends IGenericDao<CpContributionHoliday, Long>{

	public boolean insertHolidayDetails(CpContributionHoliday cpContributionHoliday); 
	
	public List<CpContributionHoliday> fetchAllContrHoliday(int serviceReqNo);
	
	public List getTransactionCount(String userName, String fatcaFlag);
	
	public List<CpRequestMaster> getTransactionCount(String userid);
	
	public List getLastLoginDetails(String userName);
	
}