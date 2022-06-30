package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;

public class ReceiptSummaryDTO {
private String policyCurrency;
private BigDecimal receiptAmt;
private BigDecimal receiptCount;
public String getPolicyCurrency() {
	return policyCurrency;
}
public void setPolicyCurrency(String policyCurrency) {
	this.policyCurrency = policyCurrency;
}
public BigDecimal getReceiptAmt() {
	return receiptAmt;
}
public void setReceiptAmt(BigDecimal receiptAmt) {
	this.receiptAmt = receiptAmt;
}
public BigDecimal getReceiptCount() {
	return receiptCount;
}
public void setReceiptCount(BigDecimal receiptCount) {
	this.receiptCount = receiptCount;
}


	
}
