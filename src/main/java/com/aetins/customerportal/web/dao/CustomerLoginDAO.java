package com.aetins.customerportal.web.dao;

import java.io.Serializable;
import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpUserInfo;

public interface CustomerLoginDAO extends IGenericDao<CpUserInfo, Serializable> {

	boolean saveUserDetails(CpUserInfo cpUserLogin);
	List<CpUserInfo> fetchUserDetails(CpUserInfo verifyUserInfo);
	List<CpUserInfo> listUserDetailsForgetPassword(CpUserInfo verifyUserLogin);
	boolean updateColumns(CpUserInfo cpUserInfo);
	List<CpUserInfo> getUserDetails(CpUserInfo verifyUserLogin);

	/*added by vishal*/
	boolean updateUserDetails(CpUserInfo cpUser);
	boolean updatePassword(CpUserInfo cpUserInfo);
	boolean lockUser(CpUserInfo lockUserLogin);
	CpUserInfo findByUserName(String username);
	
	
	
}
