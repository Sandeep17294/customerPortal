package com.aetins.customerportal.web.transaction.dto;

import java.util.List;

import com.aetins.customerportal.web.entity.CpFatca;
import com.aetins.customerportal.web.entity.CpFatcaCountryDet;
import com.aetins.customerportal.web.entity.CpProtectionBenifit;
import com.aetins.customerportal.web.entity.CpRequestMaster;

public class TyProtectionBenifitDTO {

	private List<CpRequestMaster> cpRequestMasters;

	private List<CpProtectionBenifit> cpProtectionBenifit;

	private List<CpFatca> cpFatca;

	private List<CpFatcaCountryDet> cpFatcaCountryDet;

	public List<CpRequestMaster> getCpRequestMasters() {
		return cpRequestMasters;
	}

	public List<CpProtectionBenifit> getCpProtectionBenifit() {
		return cpProtectionBenifit;
	}

	public void setCpRequestMasters(List<CpRequestMaster> cpRequestMasters) {
		this.cpRequestMasters = cpRequestMasters;
	}

	public void setCpProtectionBenifit(List<CpProtectionBenifit> cpProtectionBenifit) {
		this.cpProtectionBenifit = cpProtectionBenifit;
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
