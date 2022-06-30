package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;



public class FundDetailsDTO {
	private String fundName;
	private String fundUnit;
	private String navValue;
	private String fundValue;
	private BigDecimal allocationPercentage;
	private BigDecimal availableUnits;
	private BigDecimal unitsPrice;
	private String fundCrr;
	private BigDecimal exchangeRate;
	private BigDecimal fundValueInFundCrr;
	private BigDecimal fundValueInPlanCrr;
	private int totalFund;
	private String fundCode;
	private String policyNo;
	private String fundWithdrawalCriteria;
	private int fundCriteriaValue;
	
	private int overfund;
	private BigDecimal dummypercentage;
	
	
	
	public int getOverfund() {
		return overfund;
	}
	public void setOverfund(int overfund) {
		this.overfund = overfund;
	}
	public BigDecimal getDummypercentage() {
		return dummypercentage;
	}
	public void setDummypercentage(BigDecimal dummypercentage) {
		this.dummypercentage = dummypercentage;
	}
	public String getFundName() {
		return fundName;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	public String getFundUnit() {
		return fundUnit;
	}
	public void setFundUnit(String fundUnit) {
		this.fundUnit = fundUnit;
	}
	public String getNavValue() {
		return navValue;
	}
	public void setNavValue(String navValue) {
		this.navValue = navValue;
	}
	public String getFundValue() {
		return fundValue;
	}
	public void setFundValue(String fundValue) {
		this.fundValue = fundValue;
	}

	public BigDecimal getAllocationPercentage() {
		return allocationPercentage;
	}
	public void setAllocationPercentage(BigDecimal allocationPercentage) {
		this.allocationPercentage = allocationPercentage;
	}
	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public BigDecimal getAvailableUnits() {
		return availableUnits;
	}
	public void setAvailableUnits(BigDecimal availableUnits) {
		this.availableUnits = availableUnits;
	}
	public BigDecimal getUnitsPrice() {
		return unitsPrice;
	}
	public void setUnitsPrice(BigDecimal unitsPrice) {
		this.unitsPrice = unitsPrice;
	}
	public String getFundCrr() {
		return fundCrr;
	}
	public void setFundCrr(String fundCrr) {
		this.fundCrr = fundCrr;
	}
	public BigDecimal getFundValueInFundCrr() {
		return fundValueInFundCrr;
	}
	public void setFundValueInFundCrr(BigDecimal fundValueInFundCrr) {
		this.fundValueInFundCrr = fundValueInFundCrr;
	}
	public BigDecimal getFundValueInPlanCrr() {
		return fundValueInPlanCrr;
	}
	public void setFundValueInPlanCrr(BigDecimal fundValueInPlanCrr) {
		this.fundValueInPlanCrr = fundValueInPlanCrr;
	}
	public int getTotalFund() {
		return totalFund;
	}
	public void setTotalFund(int totalFund) {
		this.totalFund = totalFund;
	}
	public String getFundCode() {
		return fundCode;
	}
	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getFundWithdrawalCriteria() {
		return fundWithdrawalCriteria;
	}
	public void setFundWithdrawalCriteria(String fundWithdrawalCriteria) {
		this.fundWithdrawalCriteria = fundWithdrawalCriteria;
	}
	public int getFundCriteriaValue() {
		return fundCriteriaValue;
	}
	public void setFundCriteriaValue(int fundCriteriaValue) {
		this.fundCriteriaValue = fundCriteriaValue;
	}
		
}