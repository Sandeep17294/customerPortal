package com.aetins.customerportal.web.transaction.dto;

import java.util.List;

import com.aetins.customerportal.web.entity.CpFatca;
import com.aetins.customerportal.web.entity.CpFatcaCountryDet;
import com.aetins.customerportal.web.entity.CpPartialWithdrawalFunds;
import com.aetins.customerportal.web.entity.CpRequestMaster;

public class TyPartialWithdrawDTO {

	private List<CpRequestMaster> cpRequestMasters;

	private List<CpPartialWithdrawalFunds> cpPartialWithdrawalFunds;

	private List<CpFatca> cpFatca;

	private List<CpFatcaCountryDet> cpFatcaCountryDet;

	public List<CpRequestMaster> getCpRequestMasters() {
		return cpRequestMasters;
	}

	public void setCpRequestMasters(List<CpRequestMaster> cpRequestMasters) {
		this.cpRequestMasters = cpRequestMasters;
	}

	public List<CpPartialWithdrawalFunds> getCpPartialWithdrawalFunds() {
		return cpPartialWithdrawalFunds;
	}

	public void setCpPartialWithdrawalFunds(List<CpPartialWithdrawalFunds> cpPartialWithdrawalFunds) {
		this.cpPartialWithdrawalFunds = cpPartialWithdrawalFunds;
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

