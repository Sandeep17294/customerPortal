package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.entity.CpRegLock;

public interface CpRegLockDAO {

	public boolean inserttable(CpRegLock cpRegLock);
	
	public List<CpRegLock> fetchtable();
	
	public CpRegLock fetchpolicy(String idenno);
	
	public boolean updatettable(CpRegLock cpRegLock);
	
}
