package com.aetins.customerportal.web.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CP_CHANGE_IN_CONTRIBUTION")
public class CpContributionAlteration implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8573825782127797560L;

	@Id
	@GeneratedValue
	@Column(name = "SERIAL_NO")
	private int serialNo;

	@ManyToOne
	@JoinColumn(name = "SERVICE_REQUEST_NO", referencedColumnName = "SERVICE_REQUEST_NO")
	private CpRequestMaster serviceRequestNo;

	@Column(name = "POLICY_NO")
	private String policyNo;

	@Column(name = "PRODUCT_NAME")
	private String productName;

	@Column(name = "OUTSTANDING_AMOUNT")
	private BigDecimal outstandingAmount;

	@Column(name = "CURRENT_CONTRIBUTION")
	private BigDecimal currentContribution;

	@Column(name = "CURRENT_FREQUENCY")
	private String currentFrequency;

	@Column(name = "NEW_CONTRIBUTION")
	private BigDecimal newContribution;

	@Column(name = "NEW_FREQUENCY")
	private String newFrequency;

	@Column(name = "DUE_DATE")
	private Calendar dueDate;

	@Column(name = "COVERED_BANK_NAME")
	private String coveredBankName;

	@Column(name = "COVERED_IBAN")
	private String coveredIBAN;

	@Column(name = "COVERED_SOURCE_FUND")
	private String CoveredSourceFund;

	@Column(name = "PLANHOLDER_BANK_NAME")
	private String planHolderBankName;

	@Column(name = "PLANHOLDER_IBAN")
	private String planHolderIBAN;

	@Column(name = "PLANHOLDER_SOURCE_FUND")
	private String planHolderSourceFund;

	@Column(name = "INCOME_YEAR_ONE")
	private BigDecimal incomeYearOne;

	@Column(name = "INCOME_YEAR_TWO")
	private BigDecimal incomeYearTwo;

	@Column(name = "INCOME_YEAR_THREE")
	private BigDecimal incomeYearThree;

	@Column(name = "ASSETS_CASH")
	private BigDecimal assetsCash;

	@Column(name = "ASSETS_SHARES")
	private BigDecimal assetsShares;

	@Column(name = "ASSETS_REALESTATE")
	private BigDecimal assetsRealEstate;

	@Column(name = "ASSETS_OTHERS")
	private BigDecimal assetsOthers;

	@Column(name = "LIABILITY_LOAN")
	private BigDecimal liabilitiesLoan;

	@Column(name = "LIABILITY_PAYABLE")
	private BigDecimal liabilitiesPayable;

	@Column(name = "LIABILITY_MORTGAGE")
	private BigDecimal liabilitiesMortgage;

	@Column(name = "LIABILITY_OTHER")
	private BigDecimal liabilitiesOther;

	
	
	//getter
	public int getSerialNo() {
		return serialNo;
	}

	public CpRequestMaster getServiceRequestNo() {
		return serviceRequestNo;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public String getProductName() {
		return productName;
	}

	public BigDecimal getOutstandingAmount() {
		return outstandingAmount;
	}

	public BigDecimal getCurrentContribution() {
		return currentContribution;
	}

	public String getCurrentFrequency() {
		return currentFrequency;
	}

	public BigDecimal getNewContribution() {
		return newContribution;
	}

	public String getNewFrequency() {
		return newFrequency;
	}

	public Calendar getDueDate() {
		return dueDate;
	}

	public String getCoveredBankName() {
		return coveredBankName;
	}

	public String getCoveredIBAN() {
		return coveredIBAN;
	}

	public String getCoveredSourceFund() {
		return CoveredSourceFund;
	}

	public String getPlanHolderBankName() {
		return planHolderBankName;
	}

	public String getPlanHolderIBAN() {
		return planHolderIBAN;
	}

	public String getPlanHolderSourceFund() {
		return planHolderSourceFund;
	}

	public BigDecimal getIncomeYearOne() {
		return incomeYearOne;
	}

	public BigDecimal getIncomeYearTwo() {
		return incomeYearTwo;
	}

	public BigDecimal getIncomeYearThree() {
		return incomeYearThree;
	}

	public BigDecimal getAssetsCash() {
		return assetsCash;
	}

	public BigDecimal getAssetsShares() {
		return assetsShares;
	}

	public BigDecimal getAssetsRealEstate() {
		return assetsRealEstate;
	}

	public BigDecimal getAssetsOthers() {
		return assetsOthers;
	}

	public BigDecimal getLiabilitiesLoan() {
		return liabilitiesLoan;
	}

	public BigDecimal getLiabilitiesPayable() {
		return liabilitiesPayable;
	}

	public BigDecimal getLiabilitiesMortgage() {
		return liabilitiesMortgage;
	}

	public BigDecimal getLiabilitiesOther() {
		return liabilitiesOther;
	}

	//setter
	public void setCoveredBankName(String coveredBankName) {
		this.coveredBankName = coveredBankName;
	}

	public void setCoveredIBAN(String coveredIBAN) {
		this.coveredIBAN = coveredIBAN;
	}

	public void setCoveredSourceFund(String coveredSourceFund) {
		CoveredSourceFund = coveredSourceFund;
	}

	public void setPlanHolderBankName(String planHolderBankName) {
		this.planHolderBankName = planHolderBankName;
	}

	public void setPlanHolderIBAN(String planHolderIBAN) {
		this.planHolderIBAN = planHolderIBAN;
	}

	public void setPlanHolderSourceFund(String planHolderSourceFund) {
		this.planHolderSourceFund = planHolderSourceFund;
	}

	public void setIncomeYearOne(BigDecimal incomeYearOne) {
		this.incomeYearOne = incomeYearOne;
	}

	public void setIncomeYearTwo(BigDecimal incomeYearTwo) {
		this.incomeYearTwo = incomeYearTwo;
	}

	public void setIncomeYearThree(BigDecimal incomeYearThree) {
		this.incomeYearThree = incomeYearThree;
	}

	public void setAssetsCash(BigDecimal assetsCash) {
		this.assetsCash = assetsCash;
	}

	public void setAssetsShares(BigDecimal assetsShares) {
		this.assetsShares = assetsShares;
	}

	public void setAssetsRealEstate(BigDecimal assetsRealEstate) {
		this.assetsRealEstate = assetsRealEstate;
	}

	public void setAssetsOthers(BigDecimal assetsOthers) {
		this.assetsOthers = assetsOthers;
	}

	public void setLiabilitiesLoan(BigDecimal liabilitiesLoan) {
		this.liabilitiesLoan = liabilitiesLoan;
	}

	public void setLiabilitiesPayable(BigDecimal liabilitiesPayable) {
		this.liabilitiesPayable = liabilitiesPayable;
	}

	public void setLiabilitiesMortgage(BigDecimal liabilitiesMortgage) {
		this.liabilitiesMortgage = liabilitiesMortgage;
	}

	public void setLiabilitiesOther(BigDecimal liabilitiesOther) {
		this.liabilitiesOther = liabilitiesOther;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public void setDueDate(Calendar dueDate) {
		this.dueDate = dueDate;
	}

	public void setServiceRequestNo(CpRequestMaster serviceRequestNo) {
		this.serviceRequestNo = serviceRequestNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setOutstandingAmount(BigDecimal outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}

	public void setCurrentContribution(BigDecimal currentContribution) {
		this.currentContribution = currentContribution;
	}

	public void setCurrentFrequency(String currentFrequency) {
		this.currentFrequency = currentFrequency;
	}

	public void setNewContribution(BigDecimal newContribution) {
		this.newContribution = newContribution;
	}

	public void setNewFrequency(String newFrequency) {
		this.newFrequency = newFrequency;
	}

	@Override
	public String toString() {
		return "CpContributionAlteration [serialNo=" + serialNo + ", serviceRequestNo=" + serviceRequestNo
				+ ", policyNo=" + policyNo + ", productName=" + productName + ", outstandingAmount=" + outstandingAmount
				+ ", currentContribution=" + currentContribution + ", currentFrequency=" + currentFrequency
				+ ", newContribution=" + newContribution + ", newFrequency=" + newFrequency + ", dueDate=" + dueDate
				+ ", coveredBankName=" + coveredBankName + ", coveredIBAN=" + coveredIBAN + ", CoveredSourceFund="
				+ CoveredSourceFund + ", planHolderBankName=" + planHolderBankName + ", planHolderIBAN="
				+ planHolderIBAN + ", planHolderSourceFund=" + planHolderSourceFund + ", incomeYearOne=" + incomeYearOne
				+ ", incomeYearTwo=" + incomeYearTwo + ", incomeYearThree=" + incomeYearThree + ", assetsCash="
				+ assetsCash + ", assetsShares=" + assetsShares + ", assetsRealEstate=" + assetsRealEstate
				+ ", assetsOthers=" + assetsOthers + ", liabilitiesLoan=" + liabilitiesLoan + ", liabilitiesPayable="
				+ liabilitiesPayable + ", liabilitiesMortgage=" + liabilitiesMortgage + ", liabilitiesOther="
				+ liabilitiesOther + "]";
	}

	
}
