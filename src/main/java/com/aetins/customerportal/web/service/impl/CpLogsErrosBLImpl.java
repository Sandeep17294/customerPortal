package com.aetins.customerportal.web.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.dao.CpLogsErrosDAO;
import com.aetins.customerportal.web.entity.CpLogsErros;
import com.aetins.customerportal.web.entity.CpThemeCss;
import com.aetins.customerportal.web.service.CpLogsErrosBL;

@Service
public class CpLogsErrosBLImpl implements CpLogsErrosBL {

	private static final Logger logger = Logger.getLogger(CpLogsErrosBLImpl.class);

	@Autowired
	CpLogsErrosDAO cpLogsErrosDAO;
	
	@Override
	public boolean insertexception(CpLogsErros cpLogsErros) {
		// TODO Auto-generated method stub
				
		return cpLogsErrosDAO.inserterror(cpLogsErros);
	}

}
