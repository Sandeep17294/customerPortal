package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

public class StatementDTO {
	private String statementName;
	private String policyNo;
	private boolean selected;
	/*
	 * private String fundName; private BigDecimal allocationPercentage; private
	 * BigDecimal availableUnits; private BigDecimal unitsPrice; private String
	 * fundCrr; private BigDecimal exchangeRate; private BigDecimal
	 * fundValueInFundCrr; private BigDecimal fundValueInPlanCrr; private int
	 * totalFund;
	 */

	private String distributor;
	private String relationshipManger;
	private String name;
	private String address;
	private String planName;
	private Date commencementDate;
	private String term;
	private String paymentFrequency;
	private BigDecimal contribution;
	private String sPolicyNo;
	private String statusDescription;
	private Date premiumDueDate;
	private Date policyEndDate;
	private String currencyCode;
	private String arabianPlanName;
	private BigDecimal baseCurrAmt;
	private BigDecimal polCurrAmt;
	private int fundAmount;
	private int fundValue;
	private int totalContribution;
	private String policyFrequency;
	private String premiumStatusDesc;
	private Date premiumPayingEndDate;
	private String billingDay;

	private List<FundDetailsDTO> fundList;

	public int getFundValue() {
		return fundValue;
	}

	public void setFundValue(int fundValue) {
		this.fundValue = fundValue;
	}

	public String getDistributor() {
		return distributor;
	}

	public String getRelationshipManger() {
		return relationshipManger;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getPlanName() {
		return planName;
	}

	public String getPaymentFrequency() {
		return paymentFrequency;
	}

	public String getsPolicyNo() {
		return sPolicyNo;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public String getArabianPlanName() {
		return arabianPlanName;
	}

	public int getFundAmount() {
		return fundAmount;
	}

	public int getTotalContribution() {
		return totalContribution;
	}

	public String getPolicyFrequency() {
		return policyFrequency;
	}

	public String getPremiumStatusDesc() {
		return premiumStatusDesc;
	}

	public String getBillingDay() {
		return billingDay;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

	public void setRelationshipManger(String relationshipManger) {
		this.relationshipManger = relationshipManger;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public void setPaymentFrequency(String paymentFrequency) {
		this.paymentFrequency = paymentFrequency;
	}

	public BigDecimal getContribution() {
		return contribution;
	}

	public void setContribution(BigDecimal contribution) {
		this.contribution = contribution;
	}

	public void setsPolicyNo(String sPolicyNo) {
		this.sPolicyNo = sPolicyNo;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public void setArabianPlanName(String arabianPlanName) {
		this.arabianPlanName = arabianPlanName;
	}

	public BigDecimal getBaseCurrAmt() {
		return baseCurrAmt;
	}

	public void setBaseCurrAmt(BigDecimal baseCurrAmt) {
		this.baseCurrAmt = baseCurrAmt;
	}

	public BigDecimal getPolCurrAmt() {
		return polCurrAmt;
	}

	public void setPolCurrAmt(BigDecimal polCurrAmt) {
		this.polCurrAmt = polCurrAmt;
	}

	public void setFundAmount(int fundAmount) {
		this.fundAmount = fundAmount;
	}

	public void setTotalContribution(int totalContribution) {
		this.totalContribution = totalContribution;
	}

	public void setPolicyFrequency(String policyFrequency) {
		this.policyFrequency = policyFrequency;
	}

	public void setPremiumStatusDesc(String premiumStatusDesc) {
		this.premiumStatusDesc = premiumStatusDesc;
	}

	public Date getCommencementDate() {
		return commencementDate;
	}

	public Date getPremiumDueDate() {
		return premiumDueDate;
	}

	public Date getPolicyEndDate() {
		return policyEndDate;
	}

	public Date getPremiumPayingEndDate() {
		return premiumPayingEndDate;
	}

	public void setCommencementDate(Date commencementDate) {
		this.commencementDate = commencementDate;
	}

	public void setPremiumDueDate(Date premiumDueDate) {
		this.premiumDueDate = premiumDueDate;
	}

	public void setPolicyEndDate(Date policyEndDate) {
		this.policyEndDate = policyEndDate;
	}

	public void setPremiumPayingEndDate(Date premiumPayingEndDate) {
		this.premiumPayingEndDate = premiumPayingEndDate;
	}

	public void setBillingDay(String billingDay) {
		this.billingDay = billingDay;
	}

	private FundDetailsDTO fundDetailsDTOvalues = new FundDetailsDTO();

	public String getStatementName() {
		return statementName;
	}

	public void setStatementName(String statementName) {
		this.statementName = statementName;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public FundDetailsDTO getFundDetailsDTOvalues() {
		return fundDetailsDTOvalues;
	}

	public void setFundDetailsDTOvalues(FundDetailsDTO fundDetailsDTOvalues) {
		this.fundDetailsDTOvalues = fundDetailsDTOvalues;
	}

	/*
	 * public String getFundName() { return fundName; } public BigDecimal
	 * getAllocationPercentage() { return allocationPercentage; } public
	 * BigDecimal getAvailableUnits() { return availableUnits; } public
	 * BigDecimal getUnitsPrice() { return unitsPrice; } public String
	 * getFundCrr() { return fundCrr; } public BigDecimal getExchangeRate() {
	 * return exchangeRate; } public BigDecimal getFundValueInFundCrr() { return
	 * fundValueInFundCrr; } public BigDecimal getFundValueInPlanCrr() { return
	 * fundValueInPlanCrr; } public int getTotalFund() { return totalFund; }
	 * public void setFundName(String fundName) { this.fundName = fundName; }
	 * public void setAllocationPercentage(BigDecimal allocationPercentage) {
	 * this.allocationPercentage = allocationPercentage; } public void
	 * setAvailableUnits(BigDecimal availableUnits) { this.availableUnits =
	 * availableUnits; } public void setUnitsPrice(BigDecimal unitsPrice) {
	 * this.unitsPrice = unitsPrice; } public void setFundCrr(String fundCrr) {
	 * this.fundCrr = fundCrr; } public void setExchangeRate(BigDecimal
	 * exchangeRate) { this.exchangeRate = exchangeRate; } public void
	 * setFundValueInFundCrr(BigDecimal fundValueInFundCrr) {
	 * this.fundValueInFundCrr = fundValueInFundCrr; } public void
	 * setFundValueInPlanCrr(BigDecimal fundValueInPlanCrr) {
	 * this.fundValueInPlanCrr = fundValueInPlanCrr; } public void
	 * setTotalFund(int totalFund) { this.totalFund = totalFund; }
	 */
	public List<FundDetailsDTO> getFundList() {
		return fundList;
	}

	public void setFundList(List<FundDetailsDTO> fundList) {
		this.fundList = fundList;
	}

}
