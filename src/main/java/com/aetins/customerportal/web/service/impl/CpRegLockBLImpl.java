package com.aetins.customerportal.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.dao.CpRegLockDAO;
import com.aetins.customerportal.web.entity.CpRegLock;
import com.aetins.customerportal.web.service.CpRegLockBL;

@Service
public class CpRegLockBLImpl implements CpRegLockBL{

	private static final Logger logger = Logger.getLogger(CpRegLockBLImpl.class);
	
	@Autowired
	CpRegLockDAO cpRegLockDAO;
	
	@Override
	public boolean inserttable(CpRegLock cpRegLock) {
		boolean status = false;
		try{
			status=cpRegLockDAO.inserttable(cpRegLock);
		}catch(Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	
	@Override
	public CpRegLock fetchpolicy(String idenno) {
		CpRegLock list = new CpRegLock();
		try{
			list=cpRegLockDAO.fetchpolicy(idenno);
		}catch(Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<CpRegLock> fetchtable() {
		List<CpRegLock> listing = new ArrayList<CpRegLock>();
		try {
			listing = cpRegLockDAO.fetchtable();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listing;
	}

	@Override
	public boolean updatettable(CpRegLock cpRegLock) {
		boolean status = false;
		try{
			status=cpRegLockDAO.updatettable(cpRegLock);
		}catch(Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	

}
