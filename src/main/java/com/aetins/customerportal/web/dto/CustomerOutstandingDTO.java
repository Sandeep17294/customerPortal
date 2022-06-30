package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;
import java.util.Calendar;

public class CustomerOutstandingDTO {

	private Calendar dueDate;
	private BigDecimal dueAmount;
	private Calendar receiptingDate;
	private String status;
	private String outstandingCurrencyCode;
	
	//added for the excel
	private String policyno;
	
	
	 
	public String getPolicyno() {
		return policyno;
	}

	public void setPolicyno(String policyno) {
		this.policyno = policyno;
	}

	public Calendar getDueDate() {
		return dueDate;
	}

	public void setDueDate(Calendar dueDate) {
		this.dueDate = dueDate;
	}

	public BigDecimal getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(BigDecimal dueAmount) {
		this.dueAmount = dueAmount;
	}

	public Calendar getReceiptingDate() {
		return receiptingDate;
	}

	public void setReceiptingDate(Calendar receiptingDate) {
		this.receiptingDate = receiptingDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public String getOutstandingCurrencyCode() {
		return outstandingCurrencyCode;
	}

	public void setOutstandingCurrencyCode(String outstandingCurrencyCode) {
		this.outstandingCurrencyCode = outstandingCurrencyCode;
	}

	@Override
	public String toString() {
		return "CustomerOutstandingDTO [dueDate=" + dueDate + ", dueAmount=" + dueAmount + ", receiptingDate=" + receiptingDate
				+ ", status=" + status +"]";
	}
}
