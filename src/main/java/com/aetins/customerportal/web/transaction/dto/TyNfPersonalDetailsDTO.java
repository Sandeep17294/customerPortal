package com.aetins.customerportal.web.transaction.dto;

import java.util.List;

import com.aetins.customerportal.web.entity.CpFatca;
import com.aetins.customerportal.web.entity.CpFatcaCountryDet;
import com.aetins.customerportal.web.entity.CpNFPersonalDetails;
import com.aetins.customerportal.web.entity.CpRequestMaster;

public class TyNfPersonalDetailsDTO {
	private List<CpRequestMaster> cpRequestMasters;
	private List<CpNFPersonalDetails> cpNFPersonalDetails;
	private List<CpFatca> cpFatca;
	private List<CpFatcaCountryDet> cpFatcaCountryDet;

	public List<CpRequestMaster> getCpRequestMasters() {
		return cpRequestMasters;
	}

	public void setCpRequestMasters(List<CpRequestMaster> cpRequestMasters) {
		this.cpRequestMasters = cpRequestMasters;
	}

	public List<CpNFPersonalDetails> getCpNFPersonalDetails() {
		return cpNFPersonalDetails;
	}

	public void setCpNFPersonalDetails(List<CpNFPersonalDetails> cpNFPersonalDetails) {
		this.cpNFPersonalDetails = cpNFPersonalDetails;
	}

	public List<CpFatca> getCpFatca() {
		return cpFatca;
	}

	public List<CpFatcaCountryDet> getCpFatcaCountryDet() {
		return cpFatcaCountryDet;
	}

	public void setCpFatca(List<CpFatca> cpFatca) {
		this.cpFatca = cpFatca;
	}

	public void setCpFatcaCountryDet(List<CpFatcaCountryDet> cpFatcaCountryDet) {
		this.cpFatcaCountryDet = cpFatcaCountryDet;
	}

	@Override
	public String toString() {
		return "TyNfPersonalDetailsDTO [cpRequestMasters=" + cpRequestMasters + ", cpNFPersonalDetails="
				+ cpNFPersonalDetails + ", cpFatca=" + cpFatca + ", cpFatcaCountryDet=" + cpFatcaCountryDet + "]";
	}

}
