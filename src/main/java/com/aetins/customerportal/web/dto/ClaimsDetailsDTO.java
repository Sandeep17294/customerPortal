package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;
import java.util.Calendar;

public class ClaimsDetailsDTO {
	
	private String claimNo;
	private String subClaimNo;
	private String policyNo;
	private Calendar incidentDate;
	private Calendar notificationDate;
	private String benifitDescription;
	private BigDecimal claimAmount;
	private String claimStatus;
	private String dueCurrency;
	private Calendar settermentDate;
	
	public String getClaimNo() {
		return claimNo;
	}
	public void setClaimNo(String claimNo) {
		this.claimNo = claimNo;
	}
	
	public String getSubClaimNo() {
		return subClaimNo;
	}
	public void setSubClaimNo(String subClaimNo) {
		this.subClaimNo = subClaimNo;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public Calendar getIncidentDate() {
		return incidentDate;
	}
	public void setIncidentDate(Calendar incidentDate) {
		this.incidentDate = incidentDate;
	}
	public Calendar getNotificationDate() {
		return notificationDate;
	}
	public void setNotificationDate(Calendar notificationDate) {
		this.notificationDate = notificationDate;
	}
	public String getBenifitDescription() {
		return benifitDescription;
	}
	public void setBenifitDescription(String benifitDescription) {
		this.benifitDescription = benifitDescription;
	}
	public BigDecimal getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(BigDecimal claimAmount) {
		this.claimAmount = claimAmount;
	}
	public String getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}
	
	
	public String getDueCurrency() {
		return dueCurrency;
	}
	public void setDueCurrency(String dueCurrency) {
		this.dueCurrency = dueCurrency;
	}
	
	
	
	public Calendar getSettermentDate() {
		return settermentDate;
	}
	public void setSettermentDate(Calendar settermentDate) {
		this.settermentDate = settermentDate;
	}
	@Override
	public String toString() {
		return "ClaimsDetailsDTO [claimNo=" + claimNo + ", SubClaimNo=" + subClaimNo + ", policyNo=" + policyNo
				+ ", incidentDate=" + incidentDate + ", notificationDate=" + notificationDate + ", benifitDescription="
				+ benifitDescription + ", claimAmount=" + claimAmount + ", claimStatus=" + claimStatus + "]";
	}
	
	
	
	
}