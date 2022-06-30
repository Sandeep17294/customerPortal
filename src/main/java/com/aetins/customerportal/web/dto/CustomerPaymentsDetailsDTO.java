package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;
import java.util.Calendar;



public class CustomerPaymentsDetailsDTO {
	private String receiptNo;
	private Calendar receiptDate;
	private String receiptType;
	private BigDecimal dueAmount;
	private BigDecimal receiptAmount;
	private String receiptCurrency;
	private String receiptStatus;
	private String receiptMode;
	private String dueCurrency;
	public String getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
	public Calendar getReceiptDate() {
		return receiptDate;
	}
	public void setReceiptDate(Calendar receiptDate) {
		this.receiptDate = receiptDate;
	}
	public String getReceiptType() {
		return receiptType;
	}
	public void setReceiptType(String receiptType) {
		this.receiptType = receiptType;
	}
	public BigDecimal getDueAmount() {
		return dueAmount;
	}
	public void setDueAmount(BigDecimal dueAmount) {
		this.dueAmount = dueAmount;
	}
	public BigDecimal getReceiptAmount() {
		return receiptAmount;
	}
	public void setReceiptAmount(BigDecimal receiptAmount) {
		this.receiptAmount = receiptAmount;
	}
	public String getReceiptCurrency() {
		return receiptCurrency;
	}
	public void setReceiptCurrency(String receiptCurrency) {
		this.receiptCurrency = receiptCurrency;
	}
	public String getReceiptStatus() {
		return receiptStatus;
	}
	public void setReceiptStatus(String receiptStatus) {
		this.receiptStatus = receiptStatus;
	}
	public String getReceiptMode() {
		return receiptMode;
	}
	public void setReceiptMode(String receiptMode) {
		this.receiptMode = receiptMode;
	}
	public String getDueCurrency() {
		return dueCurrency;
	}
	public void setDueCurrency(String dueCurrency) {
		this.dueCurrency = dueCurrency;
	}
	
	

}
