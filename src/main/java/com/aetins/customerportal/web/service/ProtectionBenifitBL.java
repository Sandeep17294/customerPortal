package com.aetins.customerportal.web.service;

import java.util.List;

import com.aetins.customerportal.web.dto.ProtectionBenifitDTO;
import com.aetins.customerportal.web.dto.ServiceRequestMasterDTO;
import com.aetins.customerportal.web.transaction.dto.TyProtectionBenifitDTO;

public interface ProtectionBenifitBL {

	public boolean protectionBenifitAlteration(List<ProtectionBenifitDTO> selectedBenifitList);
	
	public TyProtectionBenifitDTO fetchRequestAndProtBenifit(ServiceRequestMasterDTO serviceRequestMasterDTO);

}
