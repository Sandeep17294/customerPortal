package com.aetins.customerportal.web.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "CP_CHANGE_IN_SUM_ASSURED")
public class CpSumAssuredAlteration implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 656492908224373585L;
	
	
	private int serialNo;
	private CpRequestMaster serviceRequestNo;
	private String policyNo;
	private String productName;
	private BigDecimal outstandingAmount;
	private BigDecimal currentSA;
	private BigDecimal newSA;
	
	//getter
	
	@Id
	@GeneratedValue
	@Column(name = "SERIAL_NO")
	public int getSerialNo() {
		return serialNo;
	}
	
	@ManyToOne
	@JoinColumn(name="SERVICE_REQUEST_NO",referencedColumnName="SERVICE_REQUEST_NO")
	public CpRequestMaster getServiceRequestNo() {
		return serviceRequestNo;
	}
	@Column(name="POLICY_NO")
	public String getPolicyNo() {
		return policyNo;
	}
	@Column(name="PRODUCT_NAME")
	public String getProductName() {
		return productName;
	}
	@Column(name="OUTSTANDING_AMOUNT")
	public BigDecimal getOutstandingAmount() {
		return outstandingAmount;
	}
	@Column(name="CURRENT_SA")
	public BigDecimal getCurrentSA() {
		return currentSA;
	}
	@Column(name="NEW_SA")
	public BigDecimal getNewSA() {
		return newSA;
	}
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}
	public void setServiceRequestNo(CpRequestMaster serviceRequestNo) {
		this.serviceRequestNo = serviceRequestNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setOutstandingAmount(BigDecimal outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}
	public void setCurrentSA(BigDecimal currentSA) {
		this.currentSA = currentSA;
	}
	public void setNewSA(BigDecimal newSA) {
		this.newSA = newSA;
	}
	
	
	
	
	
}
