package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;

public class FundsDto {
	private String fundName;
	private BigDecimal balanceUnit;
	private BigDecimal navValue;
	private BigDecimal fundValue;
	private BigDecimal planValue;
	private String fundCurrency;
	private BigDecimal fundSharePer;
	private String planCurrency;
	
	//excel purpose
	private String policyno;
	
	

	public String getPolicyno() {
		return policyno;
	}
	public void setPolicyno(String policyno) {
		this.policyno = policyno;
	}
	public String getFundName() {
		return fundName;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	public BigDecimal getBalanceUnit() {
		return balanceUnit;
	}
	public void setBalanceUnit(BigDecimal balanceUnit) {
		this.balanceUnit = balanceUnit;
	}
	public BigDecimal getNavValue() {
		return navValue;
	}
	public void setNavValue(BigDecimal navValue) {
		this.navValue = navValue;
	}
	public BigDecimal getFundValue() {
		return fundValue;
	}
	public void setFundValue(BigDecimal fundValue) {
		this.fundValue = fundValue;
	}
	public BigDecimal getPlanValue() {
		return planValue;
	}
	public void setPlanValue(BigDecimal planValue) {
		this.planValue = planValue;
	}
	public String getFundCurrency() {
		return fundCurrency;
	}
	public void setFundCurrency(String fundCurrency) {
		this.fundCurrency = fundCurrency;
	}
	public BigDecimal getFundSharePer() {
		return fundSharePer;
	}
	public void setFundSharePer(BigDecimal fundSharePer) {
		this.fundSharePer = fundSharePer;
	}
	public String getPlanCurrency() {
		return planCurrency;
	}
	public void setPlanCurrency(String planCurrency) {
		this.planCurrency = planCurrency;
	}
	
}