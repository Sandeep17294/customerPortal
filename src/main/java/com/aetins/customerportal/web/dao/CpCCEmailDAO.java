package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpEmailSetting;



public interface CpCCEmailDAO extends IGenericDao<CpEmailSetting, Long>{
	
	List<CpEmailSetting> fetchCCEmailList(String screenType);
	
	public boolean saveCCEmail(List<CpEmailSetting> cpEmailSetting);
	
	public boolean deleteCCMAil(CpEmailSetting cpEmailSetting);

}
