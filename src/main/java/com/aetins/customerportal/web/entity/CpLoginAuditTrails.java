package com.aetins.customerportal.web.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="cp_login_audit_trail")
public class CpLoginAuditTrails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5819336069264091014L;

	@Id
	@GenericGenerator(name = "gen", strategy = "increment")
	@GeneratedValue(generator = "gen")
	private long id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="cust_ref_no")
	private long custRefNo;
	
	@Column(name="log_in_time")
	private java.util.Date loggedInTime;
	
	@Column(name="log_off_time")
	private java.util.Date logoutTime;
	
	@Column(name="unsuccessfull_attempts")
	private long unsuccessfullAttempts;
	
	@Column(name="noof_unsuccessfull_attempts_exceed")
	private long noOfUnsuccessfullAttemptsExceed;
	
	@Column(name="user_locked_status")
	private boolean userLocked;
	
	@Column(name="is_active")
	private boolean active;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="last_updated_date")
	private Date lastUpdatedDate;
	
	@Column(name="user_account_locked_time")
	private Date userLockedTime;
	

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


	public long getUnsuccessfullAttempts() {
		return unsuccessfullAttempts;
	}

	public void setUnsuccessfullAttempts(long unsuccessfullAttempts) {
		this.unsuccessfullAttempts = unsuccessfullAttempts;
	}

	public long getNoOfUnsuccessfullAttemptsExceed() {
		return noOfUnsuccessfullAttemptsExceed;
	}

	public void setNoOfUnsuccessfullAttemptsExceed(long noOfUnsuccessfullAttemptsExceed) {
		this.noOfUnsuccessfullAttemptsExceed = noOfUnsuccessfullAttemptsExceed;
	}

	public boolean isUserLocked() {
		return userLocked;
	}

	public void setUserLocked(boolean userLocked) {
		this.userLocked = userLocked;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getUserLockedTime() {
		return userLockedTime;
	}

	public void setUserLockedTime(Date userLockedTime) {
		this.userLockedTime = userLockedTime;
	}

	public java.util.Date getLoggedInTime() {
		return loggedInTime;
	}

	public void setLoggedInTime(java.util.Date loggedInTime) {
		this.loggedInTime = loggedInTime;
	}

	public java.util.Date getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(java.util.Date logoutTime) {
		this.logoutTime = logoutTime;
	}

	@Override
	public String toString() {
		return "CpLoginAuditTrails [id=" + id + ", username=" + username + ", custRefNo=" + custRefNo
				+ ", loggedInTime=" + loggedInTime + ", logoutTime=" + logoutTime + ", unsuccessfullAttempts="
				+ unsuccessfullAttempts + ", noOfUnsuccessfullAttemptsExceed=" + noOfUnsuccessfullAttemptsExceed
				+ ", userLocked=" + userLocked + ", active=" + active + ", createdDate=" + createdDate
				+ ", lastUpdatedDate=" + lastUpdatedDate + ", userLockedTime=" + userLockedTime + "]";
	}
}
