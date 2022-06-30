package com.aetins.customerportal.web.dao;


import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpContributionAlteration;



public interface ContributionAlterDAO  extends IGenericDao<CpContributionAlteration, Long>{
	
	public boolean saveChangeDetails(CpContributionAlteration cpContributionAlteration);

	
	public List<CpContributionAlteration> fetchAllContrAlter(int serviceReqNo);
}
