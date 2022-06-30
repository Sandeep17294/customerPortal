package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;
import java.util.Date;



public class ReinStatementDTO {
	private BigDecimal outstandingAmount;
	private Date issueDate;
	private Date lapseDate;
	private Date premDue;
	private String policyNo;
	private String productName;
	private BigDecimal custRefNo;
	private boolean status;
	private String contribFreq;
	private BigDecimal regulatContrib;
	private int firstCoverHeight;
	private int firstCoverWeight;
	private int secondCoverHeight;
	private int secondCoverWeight;
	private BigDecimal planTerm;
	private BigDecimal contribTerm;
	private String planCurr;
	private String PlanStatus;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public BigDecimal getCustRefNo() {
		return custRefNo;
	}

	public void setCustRefNo(BigDecimal custRefNo) {
		this.custRefNo = custRefNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public BigDecimal getOutstandingAmount() {
		return outstandingAmount;
	}

	public void setOutstandingAmount(BigDecimal outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date tmpPremiumDueDateDat) {
		this.issueDate = tmpPremiumDueDateDat;
	}

	public Date getLapseDate() {
		return lapseDate;
	}

	public void setLapseDate(Date tmpPremiumDueDateDate) {
		this.lapseDate = tmpPremiumDueDateDate;
	}

	public Date getPremDue() {
		return premDue;
	}

	public void setPremDue(Date premDue) {
		this.premDue = premDue;
	}

	public String getContribFreq() {
		return contribFreq;
	}

	public BigDecimal getRegulatContrib() {
		return regulatContrib;
	}

	public void setContribFreq(String contribFreq) {
		this.contribFreq = contribFreq;
	}

	public void setRegulatContrib(BigDecimal regulatContrib) {
		this.regulatContrib = regulatContrib;
	}

	public int getFirstCoverHeight() {
		return firstCoverHeight;
	}

	public int getFirstCoverWeight() {
		return firstCoverWeight;
	}

	public int getSecondCoverHeight() {
		return secondCoverHeight;
	}

	public int getSecondCoverWeight() {
		return secondCoverWeight;
	}

	public void setFirstCoverHeight(int firstCoverHeight) {
		this.firstCoverHeight = firstCoverHeight;
	}

	public void setFirstCoverWeight(int firstCoverWeight) {
		this.firstCoverWeight = firstCoverWeight;
	}

	public void setSecondCoverHeight(int secondCoverHeight) {
		this.secondCoverHeight = secondCoverHeight;
	}

	public void setSecondCoverWeight(int secondCoverWeight) {
		this.secondCoverWeight = secondCoverWeight;
	}

	public BigDecimal getPlanTerm() {
		return planTerm;
	}

	public BigDecimal getContribTerm() {
		return contribTerm;
	}

	public void setPlanTerm(BigDecimal planTerm) {
		this.planTerm = planTerm;
	}

	public void setContribTerm(BigDecimal contribTerm) {
		this.contribTerm = contribTerm;
	}

	public String getPlanCurr() {
		return planCurr;
	}

	public void setPlanCurr(String planCurr) {
		this.planCurr = planCurr;
	}

	public String getPlanStatus() {
		return PlanStatus;
	}

	public void setPlanStatus(String planStatus) {
		PlanStatus = planStatus;
	}

	@Override
	public String toString() {
		return "ReinStatementDTO [outstandingAmount=" + outstandingAmount + ", issueDate=" + issueDate + ", lapseDate="
				+ lapseDate + ", premDue=" + premDue + ", policyNo=" + policyNo + ", productName=" + productName
				+ ", custRefNo=" + custRefNo + ", status=" + status + ", contribFreq=" + contribFreq
				+ ", regulatContrib=" + regulatContrib + ", firstCoverHeight=" + firstCoverHeight
				+ ", firstCoverWeight=" + firstCoverWeight + ", secondCoverHeight=" + secondCoverHeight
				+ ", secondCoverWeight=" + secondCoverWeight + ", planTerm=" + planTerm + ", contribTerm=" + contribTerm
				+ ", planCurr=" + planCurr + ", PlanStatus=" + PlanStatus + ", isStatus()=" + isStatus()
				+ ", getCustRefNo()=" + getCustRefNo() + ", getProductName()=" + getProductName() + ", getPolicyNo()="
				+ getPolicyNo() + ", getOutstandingAmount()=" + getOutstandingAmount() + ", getIssueDate()="
				+ getIssueDate() + ", getLapseDate()=" + getLapseDate() + ", getPremDue()=" + getPremDue()
				+ ", getContribFreq()=" + getContribFreq() + ", getRegulatContrib()=" + getRegulatContrib()
				+ ", getFirstCoverHeight()=" + getFirstCoverHeight() + ", getFirstCoverWeight()="
				+ getFirstCoverWeight() + ", getSecondCoverHeight()=" + getSecondCoverHeight()
				+ ", getSecondCoverWeight()=" + getSecondCoverWeight() + ", getPlanTerm()=" + getPlanTerm()
				+ ", getContribTerm()=" + getContribTerm() + ", getPlanCurr()=" + getPlanCurr() + ", getPlanStatus()="
				+ getPlanStatus() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
