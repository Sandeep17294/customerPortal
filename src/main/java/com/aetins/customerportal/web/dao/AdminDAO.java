package com.aetins.customerportal.web.dao;
import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpUserInfo;


public interface AdminDAO extends IGenericDao<CpUserInfo, Long> {

	List<CpUserInfo> listAllUsers();
	public boolean updateUserStatus(CpUserInfo userInfo);
	public boolean updateUserLockStatus(CpUserInfo userInfo);
}
