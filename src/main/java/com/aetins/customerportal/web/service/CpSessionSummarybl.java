package com.aetins.customerportal.web.service;

import java.util.List;

import com.aetins.customerportal.web.dto.CpSessionSummaryDTO;

public interface CpSessionSummarybl {
	
	
	List<CpSessionSummaryDTO> fetchsessionDetails(CpSessionSummaryDTO verifysessiondto);
	public boolean updatelogindetails(CpSessionSummaryDTO cpUserInfoDTO);
	
	public boolean updateSessionDetails(CpSessionSummaryDTO cpSessionSummaryDTO);
	
}
