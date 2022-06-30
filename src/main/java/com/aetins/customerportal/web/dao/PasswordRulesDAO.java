package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.PasswordRules;

public interface PasswordRulesDAO extends IGenericDao<PasswordRules, Long>{
	
	List<PasswordRules> listPasswordRules();
	public boolean updatePasswordRules(PasswordRules passwordRules);

}
