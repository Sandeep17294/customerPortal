package com.aetins.customerportal.web.transaction.dto;

import java.util.List;

import com.aetins.customerportal.web.entity.CpFatca;
import com.aetins.customerportal.web.entity.CpFatcaCountryDet;
import com.aetins.customerportal.web.entity.CpNFAddressContacts;
import com.aetins.customerportal.web.entity.CpRequestMaster;

public class TyNfAddressContactsDTO {

	private List<CpRequestMaster> cpRequestMasters;

	private List<CpNFAddressContacts> cpNFAddressContacts;

	private List<CpFatca> cpFatca;

	private List<CpFatcaCountryDet> cpFatcaCountryDet;

	public List<CpRequestMaster> getCpRequestMasters() {
		return cpRequestMasters;
	}

	public void setCpRequestMasters(List<CpRequestMaster> cpRequestMasters) {
		this.cpRequestMasters = cpRequestMasters;
	}

	public List<CpNFAddressContacts> getCpNFAddressContacts() {
		return cpNFAddressContacts;
	}

	public void setCpNFAddressContacts(List<CpNFAddressContacts> cpNFAddressContacts) {
		this.cpNFAddressContacts = cpNFAddressContacts;
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
		return "TyNfAddressContactsDTO [cpRequestMasters=" + cpRequestMasters + ", cpNFAddressContacts="
				+ cpNFAddressContacts + ", cpFatca=" + cpFatca + ", cpFatcaCountryDet=" + cpFatcaCountryDet + "]";
	}

}
