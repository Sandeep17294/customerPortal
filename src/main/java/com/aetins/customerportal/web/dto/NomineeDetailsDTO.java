package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;

public class NomineeDetailsDTO {

	
	private String nomineeDetails;
	private BigDecimal sharePercentage;
	private String relationship;
	
	public String getNomineeDetails() {
		return nomineeDetails;
	}
	public void setNomineeDetails(String nomineeDetails) {
		this.nomineeDetails = nomineeDetails;
	}
	 
	 
	public BigDecimal getSharePercentage() {
		return sharePercentage;
	}
	public void setSharePercentage(BigDecimal sharePercentage) {
		this.sharePercentage = sharePercentage;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	
	public String toString() {
		return "NomineeDetailsDTO [nomineeDetails=" + nomineeDetails
				+ ", sharePercentage=" + sharePercentage 
				+ ", relationship="+ relationship 
				+ "]";
	}
	
}
