package com.aetins.customerportal.web.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "CP_CHANGE_IN_TERM")
public class CpTermAlteration implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2972811343366492241L;
	
	
	private int serialNo;
	private CpRequestMaster serviceRequestNo;
	private String policyNo;
	private String productName;
	private BigDecimal outstandingAmount;
	private String currentTerm;
	private String newTerm;
	
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
	@Column(name="CURRENT_TERM")
	public String getCurrentTerm() {
		return currentTerm;
	}
	@Column(name="NEW_TERM")
	public String getNewTerm() {
		return newTerm;
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
	public void setCurrentTerm(String currentTerm) {
		this.currentTerm = currentTerm;
	}
	public void setNewTerm(String newTerm) {
		this.newTerm = newTerm;
	}
	
	
	
	

}
