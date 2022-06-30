package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;

public class DepositDTO {
	private String policyNo;
	private String depositType;
	private BigDecimal depositAmount;
	private String depositCurrancyCode;
	
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getDepositType() {
		return depositType;
	}
	public void setDepositType(String depositType) {
		this.depositType = depositType;
	}
	public BigDecimal getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(BigDecimal depositAmount) {
		this.depositAmount = depositAmount;
	}
	
	
	public String getDepositCurrancyCode() {
		return depositCurrancyCode;
	}
	public void setDepositCurrancyCode(String depositCurrancyCode) {
		this.depositCurrancyCode = depositCurrancyCode;
	}
	@Override
	public String toString() {
		return "GetMyDepositDTO [policyNo=" + policyNo + ", depositType=" + depositType + ", depositAmount=" + depositAmount + "]";
	}

}