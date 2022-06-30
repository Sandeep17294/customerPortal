package com.aetins.customerportal.web.dto;

import java.util.Date;

public class OutstandingDetailsDTO {

	private String billNo;
	private Date dueDate;
	private Date dueDateEnd;
	private String billingType;
	private String billingAmt;
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getDueDateEnd() {
		return dueDateEnd;
	}
	public void setDueDateEnd(Date dueDateEnd) {
		this.dueDateEnd = dueDateEnd;
	}
	public String getBillingType() {
		return billingType;
	}
	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}
	public String getBillingAmt() {
		return billingAmt;
	}
	public void setBillingAmt(String billingAmt) {
		this.billingAmt = billingAmt;
	}
	
	
	
}
