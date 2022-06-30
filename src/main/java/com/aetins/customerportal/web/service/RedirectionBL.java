package com.aetins.customerportal.web.service;

import java.util.List;

import com.aetins.customerportal.web.dto.CpTermAndConditionDTO;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.dto.RedirectionDTO;
import com.aetins.customerportal.web.dto.ServiceRequestMasterDTO;
import com.aetins.customerportal.web.dto.StrategyDTO;
import com.aetins.customerportal.web.dto.UserDTO;
import com.aetins.customerportal.web.transaction.dto.TyRedirectionSwitchingDTO;


public interface RedirectionBL {
	public boolean updateRedirection(List<RedirectionDTO> redirectionDTO );	
	public List<CpUserInfoDTO> fetchUserDetails(CpUserInfoDTO verifyUserLogin); 
	public List<CpUserInfoDTO> getUserDetails(UserDTO verifyUserdto);
	public List<CpTermAndConditionDTO> listTermAndCondition(String serviceType ,String mendatory ,String required);
	public List<CpTermAndConditionDTO> listTermAndCondition1(String serviceType, String mendatory, String required);
	public abstract TyRedirectionSwitchingDTO fetchRequestAndRedirections(ServiceRequestMasterDTO paramServiceRequestMasterDTO);
	public boolean updateRedirectionStrtegy(List<StrategyDTO> buyList);
	
}
