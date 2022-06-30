package com.aetins.customerportal.web.entity;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "cp_forget_pswd_audittrails")
public class CpPasswordAuditTrails {

	@Id
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator="gen")
	private long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "cust_ref_no")
	private long custRefNo;
	
	@Column(name = "forget_pswd")
	private long forgetPswd;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "last_updated")
	private Date lastUpdatedDate;
	
	@OneToOne(mappedBy = "user",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private ForgotVerificationToken token;
	
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

	
	public long getForgetPswd() {
		return forgetPswd;
	}

	public void setForgetPswd(long forgetPswd) {
		this.forgetPswd = forgetPswd;
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

	public ForgotVerificationToken getToken() {
		return token;
	}

	public void setToken(ForgotVerificationToken token) {
		this.token = token;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "CpPasswordAuditTrails [id=" + id + ", username=" + username + ", custRefNo=" + custRefNo
				+ ", forgetPswd=" + forgetPswd + ", status=" + status + ", remarks=" + remarks + ", createdDate="
				+ createdDate + ", lastUpdatedDate=" + lastUpdatedDate + ", token=" + token + "]";
	}

}
