package com.aetins.customerportal.web.transaction.dto;

import java.math.BigDecimal;
import java.util.List;

import com.aetins.customerportal.web.entity.CpContributionHoliday;
import com.aetins.customerportal.web.entity.CpFatca;
import com.aetins.customerportal.web.entity.CpFatcaCountryDet;
import com.aetins.customerportal.web.entity.CpRequestMaster;

public class TyContributionHolidayDTO {
	
	private List<CpRequestMaster> cpRequestMasters;
	private List<CpContributionHoliday> cpContributionHoliday;
	private List<CpFatca> cpFatca;
	private List<CpFatcaCountryDet> cpFatcaCountryDet;
	private BigDecimal countrySeqNo;

	public List<CpRequestMaster> getCpRequestMasters() {
		return cpRequestMasters;
	}

	public void setCpRequestMasters(List<CpRequestMaster> cpRequestMasters) {
		this.cpRequestMasters = cpRequestMasters;
	}

	public List<CpContributionHoliday> getCpContributionHoliday() {
		return cpContributionHoliday;
	}

	public void setCpContributionHoliday(List<CpContributionHoliday> cpContributionHoliday) {
		this.cpContributionHoliday = cpContributionHoliday;
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

	public BigDecimal getCountrySeqNo() {
		return countrySeqNo;
	}

	public void setCountrySeqNo(BigDecimal countrySeqNo) {
		this.countrySeqNo = countrySeqNo;
	}

}
