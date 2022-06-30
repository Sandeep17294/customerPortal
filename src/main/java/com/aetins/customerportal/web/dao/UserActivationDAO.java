package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpUserInfo;

public interface UserActivationDAO extends IGenericDao<CpUserInfo, Long>{
	List<CpUserInfo> fetchUserDetails(String tokenNo);
	public boolean updateUserStatus(CpUserInfo userInfo);
	

}
