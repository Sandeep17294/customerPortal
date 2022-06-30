package com.aetins.customerportal.web.service;

import java.util.List;

import com.aetins.customerportal.web.dto.ContributionAlterationDTO;
import com.aetins.customerportal.web.dto.CpOtpSettingsDTO;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.dto.PasswordRulesDTO;
import com.aetins.customerportal.web.dto.ServiceRequestMasterDTO;
import com.aetins.customerportal.web.dto.UserDTO;
import com.aetins.customerportal.web.transaction.dto.TyChangeInContributionDTO;



public interface ContributionAlterBL {
	public boolean changeInContribution(
			ContributionAlterationDTO contributionAlterationDTO);

	 ServiceRequestMasterDTO saveNewRequest(ServiceRequestMasterDTO requestDTO);
	public boolean updateDetails(ServiceRequestMasterDTO serviceRequestMasterDTO);
	//public List<CpUserInfoDTO> fetchUserDetails(CpUserInfoDTO verifyUserLogin);
	public List<CpUserInfoDTO> getUserDetails(UserDTO verifyUserdto);
	
	public List<CpOtpSettingsDTO> fetchOtpSettings(String serviceType);
	public PasswordRulesDTO getCpSettingDto();
	
	public TyChangeInContributionDTO fetchRequestAndAlterations(ServiceRequestMasterDTO serviceRequestMasterDTO);

}
