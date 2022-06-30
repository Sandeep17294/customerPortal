package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpProtectionBenifit;


public interface ProtectionBenifitDAO extends IGenericDao<CpProtectionBenifit, Long>{
	
	public boolean saveProtectionBenifit(List<CpProtectionBenifit> cpProtectionBenifit);
	
	public List<CpProtectionBenifit> fetchAllProtectionBenifit(int serviceReqNo);
	
}
