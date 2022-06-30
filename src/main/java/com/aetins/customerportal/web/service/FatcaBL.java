package com.aetins.customerportal.web.service;

import java.util.List;

import com.aetins.customerportal.web.dto.FatcaAddCounDTO;
import com.aetins.customerportal.web.dto.FatcaDTO;


public interface FatcaBL {
	
	public FatcaDTO saveFatcaDetails(FatcaDTO fatcadto) throws Exception ;
	public boolean saveCountryDet(List<FatcaAddCounDTO> fatcaAddCounDTO);
	

}
