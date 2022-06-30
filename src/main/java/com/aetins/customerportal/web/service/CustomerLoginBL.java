package com.aetins.customerportal.web.service;

import java.util.List;

import com.aetins.customerportal.web.dto.CpServerSettingDTO;
import com.aetins.customerportal.web.dto.CpSessionSummaryDTO;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.dto.PasswordRulesDTO;
import com.aetins.customerportal.web.dto.ServiceRequestMasterDTO;
import com.aetins.customerportal.web.dto.UserDTO;



public interface CustomerLoginBL  {

	boolean saveUserDetails(CpUserInfoDTO userLogindto);

	List<CpUserInfoDTO> fetchUserDetails(UserDTO verifyUserdto);

	public CpServerSettingDTO fetchDownTime(CpServerSettingDTO downTimeStatus);

	void saveSessionDetails(CpSessionSummaryDTO cpSessionSummary);

	List<CpUserInfoDTO> listUserDetailsForgetPassword(UserDTO verifyUserdto);

	List<CpUserInfoDTO> fetchForgetPasswordQuestion(String userName);

	boolean updatePassWord(CpUserInfoDTO cpUserInfoDTO);

	boolean lockUser(CpUserInfoDTO cpUserInfoDTO);
	
	boolean updateForgetPassWord(CpUserInfoDTO cpUserInfoDTO);

	boolean updateUserDetails(CpUserInfoDTO cpUserInfoDTO);
	public boolean updateChangePassWord(CpUserInfoDTO cpUserInfoDTO);
	 public PasswordRulesDTO fetchCpSettings();
	 
	public  List<ServiceRequestMasterDTO> fetchTransactionRequestList(String userid); 

}