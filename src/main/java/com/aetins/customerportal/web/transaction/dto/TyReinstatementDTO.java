package com.aetins.customerportal.web.transaction.dto;

import java.util.List;

import com.aetins.customerportal.web.entity.CpFatca;
import com.aetins.customerportal.web.entity.CpFatcaCountryDet;
import com.aetins.customerportal.web.entity.CpReinStatement;
import com.aetins.customerportal.web.entity.CpReinStatementBenefit;
import com.aetins.customerportal.web.entity.CpRequestMaster;

public class TyReinstatementDTO {

	private List<CpRequestMaster> cpRequestMasters;

	private List<CpReinStatement> cpReinStatement;

	private List<CpReinStatementBenefit> cpReinStatementBenefit;

	private List<CpFatca> cpFatca;

	private List<CpFatcaCountryDet> cpFatcaCountryDet;

	public List<CpRequestMaster> getCpRequestMasters() {
		return cpRequestMasters;
	}

	public List<CpReinStatement> getCpReinStatement() {
		return cpReinStatement;
	}

	public void setCpRequestMasters(List<CpRequestMaster> cpRequestMasters) {
		this.cpRequestMasters = cpRequestMasters;
	}

	public void setCpReinStatement(List<CpReinStatement> cpReinStatement) {
		this.cpReinStatement = cpReinStatement;
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

	public List<CpReinStatementBenefit> getCpReinStatementBenefit() {
		return cpReinStatementBenefit;
	}

	public void setCpReinStatementBenefit(List<CpReinStatementBenefit> cpReinStatementBenefit) {
		this.cpReinStatementBenefit = cpReinStatementBenefit;
	}

}
