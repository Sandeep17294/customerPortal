package com.aetins.customerportal.web.service;


import java.util.List;
import java.util.Map;

import com.aetins.customerportal.web.dto.ListItem;
import com.aetins.customerportal.web.dto.RegistrationCustomerDTO;
import com.aetins.customerportal.web.entity.CpSecurityImgMaster;

public interface CusRegistrationBL {
	
	public abstract void addUserInfo(RegistrationCustomerDTO paramRegistrationCustomerDTO);

	public abstract void addCustomerDetail(RegistrationCustomerDTO paramRegistrationCustomerDTO);

	public abstract List<CpSecurityImgMaster> listCpSecurityImgMaster();

	public abstract Map<String, String> listCpQuestionDetail();

	public abstract boolean validateCpUserinfo(RegistrationCustomerDTO paramRegistrationCustomerDTO);

	public List<ListItem> listCpSecurityQuestions();
}
