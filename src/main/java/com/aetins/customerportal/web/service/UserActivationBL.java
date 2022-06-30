package com.aetins.customerportal.web.service;

import java.util.List;

import com.aetins.customerportal.web.dto.CpUserInfoDTO;

public interface UserActivationBL {
	
	List<CpUserInfoDTO> fetchUserDetails(String tokenNo);
	public boolean updateStatus(CpUserInfoDTO cpUserInfoDTO);

}
