package com.aetins.customerportal.web.service;

import java.util.List;

import com.aetins.customerportal.web.dto.CpContributionHolidayDTO;
import com.aetins.customerportal.web.dto.CpTermAndConditionDTO;
import com.aetins.customerportal.web.dto.ServiceRequestMasterDTO;
import com.aetins.customerportal.web.transaction.dto.TyContributionHolidayDTO;



public interface ContributionHolidaylBL {

	public boolean insertHolidayDetails(List<CpContributionHolidayDTO> cpContributionHolidayDTO);

	public TyContributionHolidayDTO fetchRequestAndHoliday(ServiceRequestMasterDTO serviceRequestMasterDTO);

	public List<CpTermAndConditionDTO> listTermAndCondition(String serviceType ,String mendatory ,String required);
	
	public List<CpTermAndConditionDTO> listTermAndCondition1(String serviceType, String mendatory, String required);
	
	public List getTransactionCount(String username, String fatcafalg);
}

