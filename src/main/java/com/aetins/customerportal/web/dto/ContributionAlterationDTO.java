package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;
import java.util.Calendar;

public class ContributionAlterationDTO {

	/**
	 * @param args
	 */
	
	private int serialNo;
	private ServiceRequestMasterDTO serviceRequestNo;
	private String planName;
	private String policyNo;
	private BigDecimal outStandingAmount;
	private Calendar dueDate;
	private BigDecimal currentContribution;
	private BigDecimal newContribution;
	private String currentFrequency;
	private String newFrequency;
	private boolean selected;
	private String alterationType;
	private String coveredBankName;
	private String coveredIBAN;
	private String CoveredSourceFund;
	private String planHolderBankName;
	private String planHolderIBAN;
	private String planHolderSourceFund;
	private BigDecimal incomeYearOne;
	private BigDecimal incomeYearTwo;
	private BigDecimal incomeYearThree;
	private BigDecimal assetsCash;
	private BigDecimal assetsShares;
	private BigDecimal assetsRealEstate;
	private BigDecimal assetsOthers;
	private BigDecimal liabilitiesLoan;
	private BigDecimal liabilitiesPayable;
	private BigDecimal liabilitiesMortgage;
	private BigDecimal liabilitiesOther;
	private BigDecimal cummulativeAmt;

	public String getPlanName() {
		return planName;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public BigDecimal getOutStandingAmount() {
		return outStandingAmount;
	}

	public BigDecimal getCurrentContribution() {
		return currentContribution;
	}

	public BigDecimal getNewContribution() {
		return newContribution;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public void setOutStandingAmount(BigDecimal outStandingAmount) {
		this.outStandingAmount = outStandingAmount;
	}

	public void setCurrentContribution(BigDecimal currentContribution) {
		this.currentContribution = currentContribution;
	}

	public void setNewContribution(BigDecimal newContribution) {
		this.newContribution = newContribution;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getCurrentFrequency() {
		return currentFrequency;
	}

	public String getNewFrequency() {
		return newFrequency;
	}

	public void setCurrentFrequency(String currentFrequency) {
		this.currentFrequency = currentFrequency;
	}

	public void setNewFrequency(String newFrequency) {
		this.newFrequency = newFrequency;
	}

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public ServiceRequestMasterDTO getServiceRequestNo() {
		return serviceRequestNo;
	}

	public void setServiceRequestNo(ServiceRequestMasterDTO serviceRequestNo) {
		this.serviceRequestNo = serviceRequestNo;
	}

	public Calendar getDueDate() {
		return dueDate;
	}

	public void setDueDate(Calendar dueDate) {
		this.dueDate = dueDate;
	}

	public String getAlterationType() {
		return alterationType;
	}

	public void setAlterationType(String alterationType) {
		this.alterationType = alterationType;
	}

	public String getCoveredBankName() {
		return coveredBankName;
	}

	public void setCoveredBankName(String coveredBankName) {
		this.coveredBankName = coveredBankName;
	}

	public String getCoveredIBAN() {
		return coveredIBAN;
	}

	public void setCoveredIBAN(String coveredIBAN) {
		this.coveredIBAN = coveredIBAN;
	}

	public String getCoveredSourceFund() {
		return CoveredSourceFund;
	}

	public void setCoveredSourceFund(String coveredSourceFund) {
		CoveredSourceFund = coveredSourceFund;
	}

	public String getPlanHolderBankName() {
		return planHolderBankName;
	}

	public void setPlanHolderBankName(String planHolderBankName) {
		this.planHolderBankName = planHolderBankName;
	}

	public String getPlanHolderIBAN() {
		return planHolderIBAN;
	}

	public void setPlanHolderIBAN(String planHolderIBAN) {
		this.planHolderIBAN = planHolderIBAN;
	}

	public String getPlanHolderSourceFund() {
		return planHolderSourceFund;
	}

	public void setPlanHolderSourceFund(String planHolderSourceFund) {
		this.planHolderSourceFund = planHolderSourceFund;
	}

	public BigDecimal getIncomeYearOne() {
		return incomeYearOne;
	}

	public void setIncomeYearOne(BigDecimal incomeYearOne) {
		this.incomeYearOne = incomeYearOne;
	}

	public BigDecimal getIncomeYearTwo() {
		return incomeYearTwo;
	}

	public void setIncomeYearTwo(BigDecimal incomeYearTwo) {
		this.incomeYearTwo = incomeYearTwo;
	}

	public BigDecimal getIncomeYearThree() {
		return incomeYearThree;
	}

	public void setIncomeYearThree(BigDecimal incomeYearThree) {
		this.incomeYearThree = incomeYearThree;
	}

	public BigDecimal getAssetsCash() {
		return assetsCash;
	}

	public void setAssetsCash(BigDecimal assetsCash) {
		this.assetsCash = assetsCash;
	}

	public BigDecimal getAssetsShares() {
		return assetsShares;
	}

	public void setAssetsShares(BigDecimal assetsShares) {
		this.assetsShares = assetsShares;
	}

	public BigDecimal getAssetsRealEstate() {
		return assetsRealEstate;
	}

	public void setAssetsRealEstate(BigDecimal assetsRealEstate) {
		this.assetsRealEstate = assetsRealEstate;
	}

	public BigDecimal getAssetsOthers() {
		return assetsOthers;
	}

	public void setAssetsOthers(BigDecimal assetsOthers) {
		this.assetsOthers = assetsOthers;
	}

	public BigDecimal getLiabilitiesLoan() {
		return liabilitiesLoan;
	}

	public void setLiabilitiesLoan(BigDecimal liabilitiesLoan) {
		this.liabilitiesLoan = liabilitiesLoan;
	}

	public BigDecimal getLiabilitiesPayable() {
		return liabilitiesPayable;
	}

	public void setLiabilitiesPayable(BigDecimal liabilitiesPayable) {
		this.liabilitiesPayable = liabilitiesPayable;
	}

	public BigDecimal getLiabilitiesMortgage() {
		return liabilitiesMortgage;
	}

	public void setLiabilitiesMortgage(BigDecimal liabilitiesMortgage) {
		this.liabilitiesMortgage = liabilitiesMortgage;
	}

	public BigDecimal getLiabilitiesOther() {
		return liabilitiesOther;
	}

	public void setLiabilitiesOther(BigDecimal liabilitiesOther) {
		this.liabilitiesOther = liabilitiesOther;
	}

	public BigDecimal getCummulativeAmt() {
		return cummulativeAmt;
	}

	public void setCummulativeAmt(BigDecimal cummulativeAmt) {
		this.cummulativeAmt = cummulativeAmt;
	}

}
