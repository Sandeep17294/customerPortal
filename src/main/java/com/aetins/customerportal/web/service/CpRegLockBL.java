package com.aetins.customerportal.web.service;

import java.util.List;

import com.aetins.customerportal.web.entity.CpRegLock;

public interface CpRegLockBL {

    public boolean inserttable(CpRegLock cpRegLock);
	
	public List<CpRegLock> fetchtable();
	
	public CpRegLock fetchpolicy(String idenno);
	
	public boolean updatettable(CpRegLock cpRegLock);
	
}
