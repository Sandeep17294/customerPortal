package com.aetins.customerportal.web.service;

import java.util.List;

import com.aetins.customerportal.web.dto.CpOtpSettingsDTO;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.dto.PasswordRulesDTO;
import com.aetins.customerportal.web.dto.ServiceRequestMasterDTO;
import com.aetins.customerportal.web.dto.UserDTO;
import com.aetins.customerportal.web.entity.CpRequestMaster;

public interface TransactionBL {

	public boolean updateDetails(ServiceRequestMasterDTO serviceRequestMasterDTO);
	
	public boolean saveOTP(ServiceRequestMasterDTO serviceRequestMasterDTO);
	
	public boolean delete(List<ServiceRequestMasterDTO> serviceRequestMasterDTO);

	public boolean delete(ServiceRequestMasterDTO serviceRequestMasterDTO);

	
	public PasswordRulesDTO getCpSettingDto();
	
	public List<CpOtpSettingsDTO> fetchOtpSettings(String serviceType);
	
	public List<CpUserInfoDTO> getUserDetails(UserDTO verifyUserdto);
	
	
	public List<CpRequestMaster> servicerequestfetch();
 

}
