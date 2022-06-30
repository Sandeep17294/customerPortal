package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;
import java.util.Calendar;

public class LifeAssuredDTO {
	private String  lifeAssuredName;
	private String relationship;
	private Calendar dob;
	private String coverType;
	private BigDecimal sumCover;
	private BigDecimal grossPremium;
	private  BigDecimal loading;
	private BigDecimal discount;
	private BigDecimal netPremium;
	private String coverName;
	private BigDecimal planTerm;
	private String benefitType;
	
	public String getLifeAssuredName() {
		return lifeAssuredName;
	}
	public void setLifeAssuredName(String lifeAssuredName) {
		this.lifeAssuredName = lifeAssuredName;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	
	public String getCoverType() {
		return coverType;
	}
	public void setCoverType(String coverType) {
		this.coverType = coverType;
	}
	public BigDecimal getSumCover() {
		return sumCover;
	}
	public void setSumCover(BigDecimal sumCover) {
		this.sumCover = sumCover;
	}
	public BigDecimal getGrossPremium() {
		return grossPremium;
	}
	public void setGrossPremium(BigDecimal grossPremium) {
		this.grossPremium = grossPremium;
	}
	public BigDecimal getLoading() {
		return loading;
	}
	public void setLoading(BigDecimal loading) {
		this.loading = loading;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public BigDecimal getNetPremium() {
		return netPremium;
	}
	public void setNetPremium(BigDecimal netPremium) {
		this.netPremium = netPremium;
	}
	public String getCoverName() {
		return coverName;
	}
	public void setCoverName(String coverName) {
		this.coverName = coverName;
	}
	
	public Calendar getDob() {
		return dob;
	}
	public void setDob(Calendar dob) {
		this.dob = dob;
	}
	
	
	public BigDecimal getPlanTerm() {
		return planTerm;
	}
	public void setPlanTerm(BigDecimal planTerm) {
		this.planTerm = planTerm;
	}
	
	
	public String getBenefitType() {
		return benefitType;
	}
	public void setBenefitType(String benefitType) {
		this.benefitType = benefitType;
	}
	public String toString() {
		return "LifeAssuredDTO [lifeAssuredName=" + lifeAssuredName
				+ ", relationship=" + relationship 
				+ ", Dob="+ dob 
				+ ", coverType=" + coverType
				+ ", sumCover=" + sumCover 
				+ ", grossPremium=" + grossPremium
				+ ", loading=" + loading 
				+ ", discount="+ discount 
				+ ", netPremium="+ netPremium
				+ ", coverName=" + coverName
			    + "]";
	}

}
