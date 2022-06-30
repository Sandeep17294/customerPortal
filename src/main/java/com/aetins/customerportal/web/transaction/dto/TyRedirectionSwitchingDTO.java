package com.aetins.customerportal.web.transaction.dto;

import java.util.List;

import com.aetins.customerportal.web.entity.CpFatca;
import com.aetins.customerportal.web.entity.CpFatcaCountryDet;
import com.aetins.customerportal.web.entity.CpRedirectionSwitching;
import com.aetins.customerportal.web.entity.CpRequestMaster;

public class TyRedirectionSwitchingDTO {
	private List<CpRequestMaster> cpRequestMasters;

	private List<CpRedirectionSwitching> cpRedirectionSwitchings;

	private List<CpFatca> cpFatca;

	private List<CpFatcaCountryDet> cpFatcaCountryDet;

	public List<CpRequestMaster> getCpRequestMasters() {
		return cpRequestMasters;
	}

	public List<CpRedirectionSwitching> getCpRedirectionSwitchings() {
		return cpRedirectionSwitchings;
	}

	public void setCpRequestMasters(List<CpRequestMaster> cpRequestMasters) {
		this.cpRequestMasters = cpRequestMasters;
	}

	public void setCpRedirectionSwitchings(List<CpRedirectionSwitching> cpRedirectionSwitchings) {
		this.cpRedirectionSwitchings = cpRedirectionSwitchings;
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

	
	@Override
	public String toString() {
		return "TyRedirectionSwitchingDTO [cpRequestMasters=" + cpRequestMasters + ", cpRedirectionSwitchings="
				+ cpRedirectionSwitchings + ", cpFatca=" + cpFatca + ", cpFatcaCountryDet=" + cpFatcaCountryDet + "]";
	}
	
	
}
