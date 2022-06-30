package com.aetins.customerportal.web.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.dao.CpUserSecurityQuestionDAO;
import com.aetins.customerportal.web.entity.CpUserSecurityQuestion;
import com.aetins.customerportal.web.service.CpUserSecurityQuestionBL;

@Service
public class CpUserSecurityQuestionBLImpl implements CpUserSecurityQuestionBL{

	private static final Logger logger = Logger.getLogger(CpUserSecurityQuestionBLImpl.class);
	
	@Autowired
	CpUserSecurityQuestionDAO cpUserSecurityQuestionDAO;
	
	@Override
	public boolean inserting(CpUserSecurityQuestion UserSecurityQuestion) {
		// TODO Auto-generated method stub
		return cpUserSecurityQuestionDAO.inserting(UserSecurityQuestion);
	}

	@Override
	public boolean updating(List<CpUserSecurityQuestion> UserSecurityQuestion) {
		// TODO Auto-generated method stub
		return cpUserSecurityQuestionDAO.updating(UserSecurityQuestion);
	}

	@Override
	public List<CpUserSecurityQuestion> fetching(CpUserSecurityQuestion cpUserSecurityQuestion) {
		// TODO Auto-generated method stub
		return cpUserSecurityQuestionDAO.fetching(cpUserSecurityQuestion);
	}

	@Override
	public boolean delete(CpUserSecurityQuestion UserSecurityQuestion) {
		// TODO Auto-generated method stub
		return cpUserSecurityQuestionDAO.delete(UserSecurityQuestion);
	}

}
