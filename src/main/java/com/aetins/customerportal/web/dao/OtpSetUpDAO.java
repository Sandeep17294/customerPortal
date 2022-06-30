package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpOtpSettings;


public interface OtpSetUpDAO extends IGenericDao<CpOtpSettings, Long>{
	
	List<CpOtpSettings> listOtpSettings(String serviceType);
	public boolean updateOtpSettings(CpOtpSettings cpOtpSettings);

}
