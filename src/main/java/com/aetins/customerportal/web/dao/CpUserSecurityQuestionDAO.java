package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.entity.CpUserSecurityQuestion;

public interface CpUserSecurityQuestionDAO {

	public boolean inserting(CpUserSecurityQuestion UserSecurityQuestion);
	
	public boolean updating(List<CpUserSecurityQuestion> UserSecurityQuestion);
	
	public boolean delete(CpUserSecurityQuestion UserSecurityQuestion);
	
	public List<CpUserSecurityQuestion> fetching(CpUserSecurityQuestion cpUserSecurityQuestion);
	
	
}
