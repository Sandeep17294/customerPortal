package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;



public class TermAlterationDTO {

	private int serialNo;
	private ServiceRequestMasterDTO serviceRequestNo;
	private String policyNo;
	private String planName;
	private BigDecimal outstandingAmount;
	private String currentTerm;
	private String newTerm;
	private boolean selected;
	
	public int getSerialNo() {
		return serialNo;
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

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public void setOutstandingAmount(BigDecimal outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}
	public String getCurrentTerm() {
		return currentTerm;
	}
	public String getNewTerm() {
		return newTerm;
	}
	public void setCurrentTerm(String currentTerm) {
		this.currentTerm = currentTerm;
	}
	public void setNewTerm(String newTerm) {
		this.newTerm = newTerm;
	}

	public ServiceRequestMasterDTO getServiceRequestNo() {
		return serviceRequestNo;
	}

	public void setServiceRequestNo(ServiceRequestMasterDTO serviceRequestNo) {
		this.serviceRequestNo = serviceRequestNo;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	
	
	
	
	
	
}
