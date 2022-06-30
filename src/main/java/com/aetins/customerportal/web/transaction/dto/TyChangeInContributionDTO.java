package com.aetins.customerportal.web.transaction.dto;

import java.util.List;

import com.aetins.customerportal.web.entity.CpContributionAlteration;
import com.aetins.customerportal.web.entity.CpFatca;
import com.aetins.customerportal.web.entity.CpFatcaCountryDet;
import com.aetins.customerportal.web.entity.CpRequestMaster;


public class TyChangeInContributionDTO {
	private List<CpRequestMaster> cpRequestMasters;

	private List<CpContributionAlteration> cpContributionAlterations;

	private List<CpFatca> cpFatca;

	private List<CpFatcaCountryDet> cpFatcaCountryDet;

	public List<CpRequestMaster> getCpRequestMasters() {
		return cpRequestMasters;
	}

	public List<CpContributionAlteration> getCpContributionAlterations() {
		return cpContributionAlterations;
	}

	public void setCpRequestMasters(List<CpRequestMaster> cpRequestMasters) {
		this.cpRequestMasters = cpRequestMasters;
	}

	public void setCpContributionAlterations(List<CpContributionAlteration> cpContributionAlterations) {
		this.cpContributionAlterations = cpContributionAlterations;
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
		return "TyChangeInContributionDTO [cpRequestMasters=" + cpRequestMasters + ", cpContributionAlterations="
				+ cpContributionAlterations + ", cpFatca=" + cpFatca + ", cpFatcaCountryDet=" + cpFatcaCountryDet + "]";
	}
	
	

}
