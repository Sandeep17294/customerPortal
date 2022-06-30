package com.aetins.customerportal.web.dao;


import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpFatca;

public interface FatcaDAO extends IGenericDao<CpFatca, Long>{
	
	public CpFatca insertFatcaDetails(CpFatca cpFatca) throws Exception ;	
	
	public List<CpFatca> fetchFatcaDetails(int serviceReqNo); 

}
