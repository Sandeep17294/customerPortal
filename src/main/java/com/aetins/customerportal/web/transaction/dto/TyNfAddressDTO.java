package com.aetins.customerportal.web.transaction.dto;

import java.util.List;

import com.aetins.customerportal.web.entity.CpFatca;
import com.aetins.customerportal.web.entity.CpFatcaCountryDet;
import com.aetins.customerportal.web.entity.CpNFAddress;
import com.aetins.customerportal.web.entity.CpRequestMaster;

public class TyNfAddressDTO {

	private List<CpRequestMaster> cpRequestMasters;
	private List<CpNFAddress> cpNFAddress;
	private List<CpFatca> cpFatca;
	private List<CpFatcaCountryDet> cpFatcaCountryDet;

	public List<CpRequestMaster> getCpRequestMasters() {
		return cpRequestMasters;
	}

	public void setCpRequestMasters(List<CpRequestMaster> cpRequestMasters) {
		this.cpRequestMasters = cpRequestMasters;
	}

	public List<CpNFAddress> getCpNFAddress() {
		return cpNFAddress;
	}

	public void setCpNFAddress(List<CpNFAddress> cpNFAddress) {
		this.cpNFAddress = cpNFAddress;
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
		return "TyNfAddressDTO [cpRequestMasters=" + cpRequestMasters + ", cpNFAddress=" + cpNFAddress + ", cpFatca="
				+ cpFatca + ", cpFatcaCountryDet=" + cpFatcaCountryDet + "]";
	}

}
