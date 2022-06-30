package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;
import java.util.Calendar;;

public class PolicyDetailsDTO {

	private String policyName;
	private String policyNo;
	private String policyStatus;
	private String premiumStatus;
	private Calendar premiumDueDate;
	private BigDecimal outstandingAmt;//due amt
	private String outstandingCurrencyCode;
	private BigDecimal seqno;
	private boolean status;
	
	
	
	
	public BigDecimal getSeqno() {
		return seqno;
	}
	public void setSeqno(BigDecimal seqno) {
		this.seqno = seqno;
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getPolicyStatus() {
		return policyStatus;
	}
	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}	
	public BigDecimal getOutstandingAmt() {
		return outstandingAmt;
	}
	public void setOutstandingAmt(BigDecimal outstandingAmt) {
		this.outstandingAmt = outstandingAmt;
	}
	public String getPremiumStatus() {
		return premiumStatus;
	}
	public void setPremiumStatus(String premiumStatus) {
		this.premiumStatus = premiumStatus;
	}
	public Calendar getPremiumDueDate() {
		return premiumDueDate;
	}
	public void setPremiumDueDate(Calendar premiumDueDate) {
		this.premiumDueDate = premiumDueDate;
	}
	public String getOutstandingCurrencyCode() {
		return outstandingCurrencyCode;
	}
	public void setOutstandingCurrencyCode(String outstandingCurrencyCode) {
		this.outstandingCurrencyCode = outstandingCurrencyCode;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}