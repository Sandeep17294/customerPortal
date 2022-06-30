package com.aetins.customerportal.web.transaction.dto;

import java.util.List;

import com.aetins.customerportal.web.entity.CpClaimIntimation;
import com.aetins.customerportal.web.entity.CpFatca;
import com.aetins.customerportal.web.entity.CpFatcaCountryDet;
import com.aetins.customerportal.web.entity.CpRequestMaster;

public class TyClaimIntimationDTO {

	
	private List<CpRequestMaster> cpRequestMasters;
	private List<CpClaimIntimation> cpClaimIntimation;
	private List<CpFatca> cpFatca;
	private List<CpFatcaCountryDet> cpFatcaCountryDet;
	
	
	public List<CpRequestMaster> getCpRequestMasters() {
		return cpRequestMasters;
	}
	public void setCpRequestMasters(List<CpRequestMaster> cpRequestMasters) {
		this.cpRequestMasters = cpRequestMasters;
	}
	public List<CpClaimIntimation> getCpClaimIntimation() {
		return cpClaimIntimation;
	}
	public void setCpClaimIntimation(List<CpClaimIntimation> cpClaimIntimation) {
		this.cpClaimIntimation = cpClaimIntimation;
	}
	public List<CpFatca> getCpFatca() {
		return cpFatca;
	}
	public void setCpFatca(List<CpFatca> cpFatca) {
		this.cpFatca = cpFatca;
	}
	public List<CpFatcaCountryDet> getCpFatcaCountryDet() {
		return cpFatcaCountryDet;
	}
	public void setCpFatcaCountryDet(List<CpFatcaCountryDet> cpFatcaCountryDet) {
		this.cpFatcaCountryDet = cpFatcaCountryDet;
	}

	
	
}
