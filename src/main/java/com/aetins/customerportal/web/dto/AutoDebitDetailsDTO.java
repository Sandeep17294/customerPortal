package com.aetins.customerportal.web.dto;


import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;


public class AutoDebitDetailsDTO {

	public Calendar effectiveDate;
	public String effectiveDateValue;
	public String accountType;
	public String accountNo;
	public String bankName;
	public Calendar expiryDate;
	public String expiryDateValue;
	public String paymentMethod;
	public String bankBranch;
	public String bankCode;
	public BigDecimal contributionAmount;
	public Date dueDate;
	public String policyNo;
	public String productName;
	public int serviceRequestNo;
	public String autoDebitMode;

	

	public Date neweffectiveDate;
	public String neweffectiveDateValue;
	public String newaccountNo;
	public String newbankName;
	public Date newexpiryDate;
	public String newexpiryDateValue;
	public String newpaymentMethod;
	public String newbankBranch;
	public String newbankCode;
	public String newAutoDebitMode;
	public String newEmployeeNo;
	public String newCardNo;
	public String newAccountType;
	public String newCardType;
	
	public boolean erroronLoading;
	
	
	public boolean isErroronLoading() {
		return erroronLoading;
	}
	public void setErroronLoading(boolean erroronLoading) {
		this.erroronLoading = erroronLoading;
	}
	public Calendar getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Calendar effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public Calendar getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Calendar expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getEffectiveDateValue() {
		return effectiveDateValue;
	}
	public void setEffectiveDateValue(String effectiveDateValue) {
		this.effectiveDateValue = effectiveDateValue;
	}
	public String getExpiryDateValue() {
		return expiryDateValue;
	}
	public void setExpiryDateValue(String expiryDateValue) {
		this.expiryDateValue = expiryDateValue;
	}
	public Date getNeweffectiveDate() {
		return neweffectiveDate;
	}
	public void setNeweffectiveDate(Date neweffectiveDate) {
		this.neweffectiveDate = neweffectiveDate;
	}
	public String getNeweffectiveDateValue() {
		return neweffectiveDateValue;
	}
	public void setNeweffectiveDateValue(String neweffectiveDateValue) {
		this.neweffectiveDateValue = neweffectiveDateValue;
	}
	public String getNewaccountNo() {
		return newaccountNo;
	}
	public void setNewaccountNo(String newaccountNo) {
		this.newaccountNo = newaccountNo;
	}
	public String getNewbankName() {
		return newbankName;
	}
	public void setNewbankName(String newbankName) {
		this.newbankName = newbankName;
	}
	public Date getNewexpiryDate() {
		return newexpiryDate;
	}
	public void setNewexpiryDate(Date newexpiryDate) {
		this.newexpiryDate = newexpiryDate;
	}
	public String getNewexpiryDateValue() {
		return newexpiryDateValue;
	}
	public void setNewexpiryDateValue(String newexpiryDateValue) {
		this.newexpiryDateValue = newexpiryDateValue;
	}
	public String getNewpaymentMethod() {
		return newpaymentMethod;
	}
	public void setNewpaymentMethod(String newpaymentMethod) {
		this.newpaymentMethod = newpaymentMethod;
	}
	public String getNewbankBranch() {
		return newbankBranch;
	}
	public void setNewbankBranch(String newbankBranch) {
		this.newbankBranch = newbankBranch;
	}
	public String getNewbankCode() {
		return newbankCode;
	}
	public void setNewbankCode(String newbankCode) {
		this.newbankCode = newbankCode;
	}
	public String getNewAutoDebitMode() {
		return newAutoDebitMode;
	}
	public void setNewAutoDebitMode(String newAutoDebitMode) {
		this.newAutoDebitMode = newAutoDebitMode;
	}
	public BigDecimal getContributionAmount() {
		return contributionAmount;
	}
	public void setContributionAmount(BigDecimal contributionAmount) {
		this.contributionAmount = contributionAmount;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getServiceRequestNo() {
		return serviceRequestNo;
	}
	public void setServiceRequestNo(int serviceRequestNo) {
		this.serviceRequestNo = serviceRequestNo;
	}
	public String getNewEmployeeNo() {
		return newEmployeeNo;
	}
	public void setNewEmployeeNo(String newEmployeeNo) {
		this.newEmployeeNo = newEmployeeNo;
	}
	
	public String getNewAccountType() {
		return newAccountType;
	}
	public void setNewAccountType(String newAccountType) {
		this.newAccountType = newAccountType;
	}
	public String getNewCardType() {
		return newCardType;
	}
	public void setNewCardType(String newCardType) {
		this.newCardType = newCardType;
	}
	public String getNewCardNo() {
		return newCardNo;
	}
	public void setNewCardNo(String newCardNo) {
		this.newCardNo = newCardNo;
	}
		public String getAutoDebitMode() {
		return autoDebitMode;
	}
	public void setAutoDebitMode(String autoDebitMode) {
		this.autoDebitMode = autoDebitMode;
	}
	
	
	
}

