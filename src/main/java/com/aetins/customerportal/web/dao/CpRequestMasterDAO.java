package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpRequestMaster;

public interface CpRequestMasterDAO extends IGenericDao<CpRequestMaster, Integer> {

	public CpRequestMaster saveNewUser(CpRequestMaster cprequestMaster);

	public boolean updateColumns(CpRequestMaster cprequestMaster);
	
	public boolean saveOTP(CpRequestMaster cprequestMaster);

	public CpRequestMaster fetchRequestMasters(CpRequestMaster cprequestMaster);	
	
	public List<CpRequestMaster> fetchTransactionRequestList(String userid);
	
    public boolean deletetransaction(List<CpRequestMaster> CpRequestMaster);
	
	public boolean deletetransaction(CpRequestMaster CpRequestMaster);
	
	public List<CpRequestMaster> getTransactionCount(String userid); 
	
	public List<CpRequestMaster> servicerequestfetch();
}
