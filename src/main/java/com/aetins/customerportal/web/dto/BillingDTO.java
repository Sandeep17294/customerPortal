package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;

public class BillingDTO {

	private BigDecimal serialNo;
	private String coverType;
	private BigDecimal amount;
	private String chargesName;
	
	public String getCoverType() {
		return coverType;
	}
	public void setCoverType(String coverType) {
		this.coverType = coverType;
	}
	
	public String getChargesName() {
		return chargesName;
	}
	public void setChargesName(String chargesName) {
		this.chargesName = chargesName;
	}
	public BigDecimal getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(BigDecimal serialNo) {
		this.serialNo = serialNo;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "BillingDTO [serialNo=" + serialNo + ", coverType=" + coverType + ", amount=" + amount + ", chargesName="
				+ chargesName + "]";
	}
	
	
}
