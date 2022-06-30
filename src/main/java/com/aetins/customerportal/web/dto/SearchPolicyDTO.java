package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SearchPolicyDTO {
	private Calendar commencementDate;
	private Calendar maturityDate;
	private Calendar issuanceDate;
	private String paymentMode;
	private String paymentTerm;
	private String policyTerm;
	private Calendar lastPaidDate;
	private String planCurrency;
	private String planNumber;
	private String productName;
	private BigDecimal regularContract;
	private BigDecimal totalDue;
	private BigDecimal annualContract;
	private BigDecimal planValue;
	private BigDecimal totalRecContract;
	private String firstMember;
	private String proposalNumber;
	private Calendar proposalDate;
	private String planStatus;
	private BigDecimal lastPaidContr;
	private Calendar premiumDueDate;
	private String assignment;
	private String contrHolidays;
	private String remainingTerms;
	private String planTerm;
	private String premiumPayStatus;
	private Calendar premiumPayEndDate;
	private String premiumPayTerm;
	private String payMethod;
	private Calendar autoDbDate;
	private BigDecimal fundValue;
	private BigDecimal dueAmount;
	private BigDecimal pendingRegCount;
	private String distributorName;
	private String relationManager;
	private String recCurrencyCode;
	private BigDecimal policyYear;
	private BigDecimal paidDueCount;
	private String errorCode;
	private BigDecimal minPremium;
	private BigDecimal maxPremium;
	private Calendar holidayEndDate;
	private String secondMember;
	private List<FundDetailsSearchDTO> fundDetailsList;
	private List<LifeAssuredDTO> lifeAssuredList;
	private List<NomineeDetailsDTO> nomineeDetailsList;
	private List<PolicyBenefitDTO> policyBenefitList;

	private BigDecimal pwamount;
	
	
	
	
	
	public BigDecimal getPwamount() {
		return pwamount;
	}

	public void setPwamount(BigDecimal pwamount) {
		this.pwamount = pwamount;
	}

	public String getPlanTerm() {
		return planTerm;
	}

	public List<PolicyBenefitDTO> getPolicyBenefitList() {
		return policyBenefitList;
	}

	public void setPolicyBenefitList(List<PolicyBenefitDTO> policyBenefitList) {
		this.policyBenefitList = policyBenefitList;
	}

	public String getPremiumPayStatus() {
		return premiumPayStatus;
	}

	public void setPremiumPayStatus(String premiumPayStatus) {
		this.premiumPayStatus = premiumPayStatus;
	}

	public Calendar getPremiumPayEndDate() {
		return premiumPayEndDate;
	}

	public void setPremiumPayEndDate(Calendar premiumPayEndDate) {
		this.premiumPayEndDate = premiumPayEndDate;
	}

	public String getPremiumPayTerm() {
		return premiumPayTerm;
	}

	public void setPremiumPayTerm(String premiumPayTerm) {
		this.premiumPayTerm = premiumPayTerm;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public Calendar getAutoDbDate() {
		return autoDbDate;
	}

	public void setAutoDbDate(Calendar autoDbDate) {
		this.autoDbDate = autoDbDate;
	}

	public BigDecimal getFundValue() {
		return fundValue;
	}

	public void setFundValue(BigDecimal fundValue) {
		this.fundValue = fundValue;
	}

	public BigDecimal getPendingRegCount() {
		return pendingRegCount;
	}

	public void setPendingRegCount(BigDecimal pendingRegCount) {
		this.pendingRegCount = pendingRegCount;
	}

	public String getDistributorName() {
		return distributorName;
	}

	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}

	public String getRelationManager() {
		return relationManager;
	}

	public void setRelationManager(String relationManager) {
		this.relationManager = relationManager;
	}

	public void setPlanTerm(String planTerm) {
		this.planTerm = planTerm;
	}

	public String getContrHolidays() {
		return contrHolidays;
	}

	public void setContrHolidays(String contrHolidays) {
		this.contrHolidays = contrHolidays;
	}

	public String getRemainingTerms() {
		return remainingTerms;
	}

	public void setRemainingTerms(String remainingTerms) {
		this.remainingTerms = remainingTerms;
	}

	public String getAssignment() {
		return assignment;
	}

	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}

	public Calendar getPremiumDueDate() {
		return premiumDueDate;
	}

	public void setPremiumDueDate(Calendar premiumDueDate) {
		this.premiumDueDate = premiumDueDate;
	}

	public BigDecimal getLastPaidContr() {
		return lastPaidContr;
	}

	public void setLastPaidContr(BigDecimal lastPaidContr) {
		this.lastPaidContr = lastPaidContr;
	}

	public String getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}

	public String getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(String proposalNumber) {
		this.proposalNumber = proposalNumber;
	}

	public Calendar getProposalDate() {
		return proposalDate;
	}

	public void setProposalDate(Calendar proposalDate) {
		this.proposalDate = proposalDate;
	}

	public String getFirstMember() {
		return firstMember;
	}

	public void setFirstMember(String firstMember) {
		this.firstMember = firstMember;
	}

	public BigDecimal getTotalRecContract() {
		return totalRecContract;
	}

	public void setTotalRecContract(BigDecimal totalRecContract) {
		this.totalRecContract = totalRecContract;
	}

	public Calendar getCommencementDate() {
		return commencementDate;
	}

	public void setCommencementDate(Calendar commencementDate) {
		this.commencementDate = commencementDate;
	}

	public Calendar getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Calendar maturityDate) {
		this.maturityDate = maturityDate;
	}

	public Calendar getIssuanceDate() {
		return issuanceDate;
	}

	public void setIssuanceDate(Calendar calendar) {
		this.issuanceDate = calendar;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

	public String getPolicyTerm() {
		return policyTerm;
	}

	public void setPolicyTerm(String policyTerm) {
		this.policyTerm = policyTerm;
	}

	public Calendar getLastPaidDate() {
		return lastPaidDate;
	}

	public void setLastPaidDate(Calendar lastPaidDate) {
		this.lastPaidDate = lastPaidDate;
	}

	public BigDecimal getRegularContract() {
		return regularContract;
	}

	public void setRegularContract(BigDecimal regularContract) {
		this.regularContract = regularContract;
	}

	public BigDecimal getTotalDue() {
		return totalDue;
	}

	public void setTotalDue(BigDecimal totalDue) {
		this.totalDue = totalDue;
	}

	public BigDecimal getAnnualContract() {
		return annualContract;
	}

	public void setAnnualContract(BigDecimal annualContract) {
		this.annualContract = annualContract;
	}

	public String getPlanCurrency() {
		return planCurrency;
	}

	public BigDecimal getPlanValue() {
		return planValue;
	}

	public void setPlanValue(BigDecimal planValue) {
		this.planValue = planValue;
	}

	public void setPlanCurrency(String planCurrency) {
		this.planCurrency = planCurrency;
	}

	public String getPlanNumber() {
		return planNumber;
	}

	public void setPlanNumber(String planNumber) {
		this.planNumber = planNumber;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(BigDecimal dueAmount) {
		this.dueAmount = dueAmount;
	}

	public List<FundDetailsSearchDTO> getFundDetailsList() {
		return fundDetailsList;
	}

	public void setFundDetailsList(List<FundDetailsSearchDTO> fundDetailsList) {
		this.fundDetailsList = fundDetailsList;
	}

	public List<LifeAssuredDTO> getLifeAssuredList() {
		return lifeAssuredList;
	}

	public void setLifeAssuredList(List<LifeAssuredDTO> lifeAssuredList) {
		this.lifeAssuredList = lifeAssuredList;
	}

	public List<NomineeDetailsDTO> getNomineeDetailsList() {
		return nomineeDetailsList;
	}

	public void setNomineeDetailsList(List<NomineeDetailsDTO> nomineeDetailsList) {
		this.nomineeDetailsList = nomineeDetailsList;
	}

	public String getRecCurrencyCode() {
		return recCurrencyCode;
	}

	public void setRecCurrencyCode(String recCurrencyCode) {
		this.recCurrencyCode = recCurrencyCode;
	}

	public BigDecimal getPolicyYear() {
		return policyYear;
	}

	public BigDecimal getPaidDueCount() {
		return paidDueCount;
	}

	public void setPolicyYear(BigDecimal policyYear) {
		this.policyYear = policyYear;
	}

	public void setPaidDueCount(BigDecimal paidDueCount) {
		this.paidDueCount = paidDueCount;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public BigDecimal getMinPremium() {
		return minPremium;
	}

	public BigDecimal getMaxPremium() {
		return maxPremium;
	}

	public void setMinPremium(BigDecimal minPremium) {
		this.minPremium = minPremium;
	}

	public void setMaxPremium(BigDecimal maxPremium) {
		this.maxPremium = maxPremium;
	}

	public Calendar getHolidayEndDate() {
		return holidayEndDate;
	}

	public void setHolidayEndDate(Calendar holidayEndDate) {
		this.holidayEndDate = holidayEndDate;
	}

	public String getSecondMember() {
		return secondMember;
	}

	public void setSecondMember(String secondMember) {
		this.secondMember = secondMember;
	}

	@Override
	public String toString() {
		return "SearchPolicyDTO [commencementDate=" + commencementDate + ", maturityDate=" + maturityDate
				+ ", issuanceDate=" + issuanceDate + ", paymentMode=" + paymentMode + ", paymentTerm=" + paymentTerm
				+ ", policyTerm=" + policyTerm + ", lastPaidDate=" + lastPaidDate + ", planCurrency=" + planCurrency
				+ ", planNumber=" + planNumber + ", productName=" + productName + ", regularContract=" + regularContract
				+ ", totalDue=" + totalDue + ", annualContract=" + annualContract + ", planValue=" + planValue
				+ ", totalRecContract=" + totalRecContract + ", firstMember=" + firstMember + ", proposalNumber="
				+ proposalNumber + ", proposalDate=" + proposalDate + ", planStatus=" + planStatus + ", lastPaidContr="
				+ lastPaidContr + ", premiumDueDate=" + premiumDueDate + ", assignment=" + assignment
				+ ", contrHolidays=" + contrHolidays + ", remainingTerms=" + remainingTerms + ", planTerm=" + planTerm
				+ ", premiumPayStatus=" + premiumPayStatus + ", premiumPayEndDate=" + premiumPayEndDate
				+ ", premiumPayTerm=" + premiumPayTerm + ", payMethod=" + payMethod + ", autoDbDate=" + autoDbDate
				+ ", fundValue=" + fundValue + ", dueAmount=" + dueAmount + ", pendingRegCount=" + pendingRegCount
				+ ", distributorName=" + distributorName + ", relationManager=" + relationManager + ", recCurrencyCode="
				+ recCurrencyCode + ", policyYear=" + policyYear + ", paidDueCount=" + paidDueCount + ", errorCode="
				+ errorCode + ", minPremium=" + minPremium + ", maxPremium=" + maxPremium + ", holidayEndDate="
				+ holidayEndDate + ", secondMember=" + secondMember + ", fundDetailsList=" + fundDetailsList
				+ ", lifeAssuredList=" + lifeAssuredList + ", nomineeDetailsList=" + nomineeDetailsList
				+ ", policyBenefitList=" + policyBenefitList + ", getPlanTerm()=" + getPlanTerm()
				+ ", getPolicyBenefitList()=" + getPolicyBenefitList() + ", getPremiumPayStatus()="
				+ getPremiumPayStatus() + ", getPremiumPayEndDate()=" + getPremiumPayEndDate()
				+ ", getPremiumPayTerm()=" + getPremiumPayTerm() + ", getPayMethod()=" + getPayMethod()
				+ ", getAutoDbDate()=" + getAutoDbDate() + ", getFundValue()=" + getFundValue()
				+ ", getPendingRegCount()=" + getPendingRegCount() + ", getDistributorName()=" + getDistributorName()
				+ ", getRelationManager()=" + getRelationManager() + ", getContrHolidays()=" + getContrHolidays()
				+ ", getRemainingTerms()=" + getRemainingTerms() + ", getAssignment()=" + getAssignment()
				+ ", getPremiumDueDate()=" + getPremiumDueDate() + ", getLastPaidContr()=" + getLastPaidContr()
				+ ", getPlanStatus()=" + getPlanStatus() + ", getProposalNumber()=" + getProposalNumber()
				+ ", getProposalDate()=" + getProposalDate() + ", getFirstMember()=" + getFirstMember()
				+ ", getTotalRecContract()=" + getTotalRecContract() + ", getCommencementDate()="
				+ getCommencementDate() + ", getMaturityDate()=" + getMaturityDate() + ", getIssuanceDate()="
				+ getIssuanceDate() + ", getPaymentMode()=" + getPaymentMode() + ", getPaymentTerm()="
				+ getPaymentTerm() + ", getPolicyTerm()=" + getPolicyTerm() + ", getLastPaidDate()=" + getLastPaidDate()
				+ ", getRegularContract()=" + getRegularContract() + ", getTotalDue()=" + getTotalDue()
				+ ", getAnnualContract()=" + getAnnualContract() + ", getPlanCurrency()=" + getPlanCurrency()
				+ ", getPlanValue()=" + getPlanValue() + ", getPlanNumber()=" + getPlanNumber() + ", getProductName()="
				+ getProductName() + ", getDueAmount()=" + getDueAmount() + ", getFundDetailsList()="
				+ getFundDetailsList() + ", getLifeAssuredList()=" + getLifeAssuredList() + ", getNomineeDetailsList()="
				+ getNomineeDetailsList() + ", getRecCurrencyCode()=" + getRecCurrencyCode() + ", getPolicyYear()="
				+ getPolicyYear() + ", getPaidDueCount()=" + getPaidDueCount() + ", getErrorCode()=" + getErrorCode()
				+ ", getMinPremium()=" + getMinPremium() + ", getMaxPremium()=" + getMaxPremium()
				+ ", getHolidayEndDate()=" + getHolidayEndDate() + ", getSecondMember()=" + getSecondMember()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}