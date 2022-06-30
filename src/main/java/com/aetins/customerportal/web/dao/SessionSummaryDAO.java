package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpSessionSummary;

public interface SessionSummaryDAO extends IGenericDao<CpSessionSummary, Long>{
	
	List<CpSessionSummary> listSessionDetails();
	public void saveSessionDetails(CpSessionSummary cpSessionSummary);
	public List<CpSessionSummary> fetchCustomerDetails(CpSessionSummary summerysession);
	boolean updatePassword(CpSessionSummary cpUserInfo);
	public boolean updateSessionSummary(CpSessionSummary cpSessionSummary);
	public List<CpSessionSummary> listSessionDetails(int firestValue,int lastValue);

}
