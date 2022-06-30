package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpServiceType;

public interface ServiceTypeDAO extends IGenericDao<CpServiceType, Long>{
	
	public List<CpServiceType> listserviceType();
}
