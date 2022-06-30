package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;

public class SumAssuredAlterationDTO {

	
	private int serialNo;
	private ServiceRequestMasterDTO serviceRequestNo;
	private String policyNo;
	private String planName;
	private BigDecimal outstandingAmount;
	private BigDecimal newSA;
	private BigDecimal currentSA;
	
	
	public int getSerialNo() {
		return serialNo;
	}
	public ServiceRequestMasterDTO getServiceRequestNo() {
		return serviceRequestNo;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public String getPlanName() {
		return planName;
	}
	public BigDecimal getOutstandingAmount() {
		return outstandingAmount;
	}
	

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}
	public void setServiceRequestNo(ServiceRequestMasterDTO serviceRequestNo) {
		this.serviceRequestNo = serviceRequestNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public void setOutstandingAmount(BigDecimal outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}

	public BigDecimal getCurrentSA() {
		return currentSA;
	}
	public void setCurrentSA(BigDecimal currentSA) {
		this.currentSA = currentSA;
	}
	public BigDecimal getNewSA() {
		return newSA;
	}
	public void setNewSA(BigDecimal newSA) {
		this.newSA = newSA;
	}

	
	
	
}
