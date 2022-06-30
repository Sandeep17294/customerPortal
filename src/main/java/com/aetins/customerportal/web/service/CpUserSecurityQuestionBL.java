package com.aetins.customerportal.web.service;

import java.util.List;

import com.aetins.customerportal.web.entity.CpUserSecurityQuestion;

public interface CpUserSecurityQuestionBL {

    public boolean inserting(CpUserSecurityQuestion UserSecurityQuestion);
	
	public boolean updating(List<CpUserSecurityQuestion> UserSecurityQuestion);
	
	public boolean delete(CpUserSecurityQuestion UserSecurityQuestion);
	
	public List<CpUserSecurityQuestion> fetching(CpUserSecurityQuestion cpUserSecurityQuestion);
	
}
