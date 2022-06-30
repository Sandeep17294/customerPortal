package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;

public class SummaryDTO {
	
	private BigDecimal policyCount;
	private BigDecimal outStandingCount;
	private BigDecimal outStandingAmount;
	private BigDecimal fundCount;
	private BigDecimal suspenseAmount;
	private BigDecimal fundValue;
	

	public BigDecimal getFundValue() {
		return fundValue;
	}
	public void setFundValue(BigDecimal fundValue) {
		this.fundValue = fundValue;
	}

	public BigDecimal getFundCount() {
		return fundCount;
	}

	public void setFundCount(BigDecimal fundCount) {
		this.fundCount = fundCount;
	}

	public BigDecimal getPolicyCount() {
		return policyCount;
	}

	public void setPolicyCount(BigDecimal policyCount) {
		this.policyCount = policyCount;
	}
	
	public BigDecimal getOutStandingCount() {
		return outStandingCount;
	}

	public void setOutStandingCount(BigDecimal outStandingCount) {
		this.outStandingCount = outStandingCount;
	}

	public BigDecimal getOutStandingAmount() {
		return outStandingAmount;
	}
	public BigDecimal getSuspenseAmount() {
		return suspenseAmount;
	}
	public void setOutStandingAmount(BigDecimal outStandingAmount) {
		this.outStandingAmount = outStandingAmount;
	}
	public void setSuspenseAmount(BigDecimal suspenseAmount) {
		this.suspenseAmount = suspenseAmount;
	}	
}
