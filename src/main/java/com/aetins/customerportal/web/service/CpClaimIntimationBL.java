package com.aetins.customerportal.web.service;


import com.aetins.customerportal.web.dto.ClaimIntimationDTO;
import com.aetins.customerportal.web.transaction.dto.TyClaimIntimationDTO;

public interface CpClaimIntimationBL {
	
	public boolean insert(ClaimIntimationDTO claimIntimationDTO);

	public abstract TyClaimIntimationDTO fetchRequestclaim(int serviceno);

	public boolean updatestatus(int serviceno);
	

}

