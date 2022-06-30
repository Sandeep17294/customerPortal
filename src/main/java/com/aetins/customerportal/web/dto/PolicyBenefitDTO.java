package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;
import java.util.Calendar;

public class PolicyBenefitDTO {
	private String otherBenefit;
	private BigDecimal coverAmount;
	private  Calendar effectiveDate;
	private Calendar effectiveUntil;
	private String otherBenefitstatus;
	 
	
	 
	public String getOtherBenefit() {
		return otherBenefit;
	}
	public void setOtherBenefit(String otherBenefit) {
		this.otherBenefit = otherBenefit;
	}
	public BigDecimal getCoverAmount() {
		return coverAmount;
	}
	public void setCoverAmount(BigDecimal coverAmount) {
		this.coverAmount = coverAmount;
	}
	public Calendar getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Calendar effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public Calendar getEffectiveUntil() {
		return effectiveUntil;
	}
	public void setEffectiveUntil(Calendar effectiveUntil) {
		this.effectiveUntil = effectiveUntil;
	}
	
	public String getOtherBenefitstatus() {
		return otherBenefitstatus;
	}
	public void setOtherBenefitstatus(String otherBenefitstatus) {
		this.otherBenefitstatus = otherBenefitstatus;
	}
	public String toString() {
		return "PolicyBenefitDTO [otherBenefit=" + otherBenefit
				+ ", coverAmount=" + coverAmount 
				+ ", effectiveDate=" + effectiveDate
				+ ", effectiveUntil="+ effectiveUntil + "]";
	}	
}
