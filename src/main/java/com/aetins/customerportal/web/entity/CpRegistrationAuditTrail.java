package com.aetins.customerportal.web.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="cp_registration_audit_trail")
public class CpRegistrationAuditTrail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1060449468622105227L;

	@Id
	@GenericGenerator(name = "gen", strategy = "increment")
	@GeneratedValue(generator = "gen")
	@Column(name = "id", unique = true, nullable = false, precision = 15, scale = 0)
	private long id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="cust_ref_num")
	private long custRefNo;
	
	@Column(name="identification_num")
	private String policyNo;
	
	@Column(name="registration_date")
	private Date registrationDate;
	
	@Column(name="activation_date")
	private Date activationDate;
	
	@Column(name="status")
	private String registrationStatus;
	
	@Column(name="remarks")
	private String remarks;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="last_updated_date")
	private Date lastUpdatedDate;
	
	@Column(name = "user_type")
	private String userType;
	
	@Column(name="indentification_type")
	private String identificationType;
	
	/* GETTERS & SETTERS */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getCustRefNo() {
		return custRefNo;
	}

	public void setCustRefNo(long custRefNo) {
		this.custRefNo = custRefNo;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}

	public String getRegistrationStatus() {
		return registrationStatus;
	}

	public void setRegistrationStatus(String registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getIdentificationType() {
		return identificationType;
	}

	public void setIdentificationType(String identificationType) {
		this.identificationType = identificationType;
	}

	@Override
	public String toString() {
		return "CpRegistrationAuditTrail [id=" + id + ", username=" + username
				+ ", custRefNo=" + custRefNo + ", policyNo=" + policyNo
				+ ", registrationDate=" + registrationDate
				+ ", activationDate=" + activationDate
				+ ", registrationStatus=" + registrationStatus + ", remarks="
				+ remarks + ", createdDate=" + createdDate
				+ ", lastUpdatedDate=" + lastUpdatedDate + ", userType="
				+ userType + ", identificationType=" + identificationType + "]";
	}

	

	
}
