package com.aetins.customerportal.web.dto;

import java.util.Date;
public class CpLoginAuditTrailsDTO {

	private long id;
	private String username;
	private long custRefNo;
	private Date loggedInTime;
	private Date logoutTime;
	private long unsuccessfullAttempts;
	private long noOfUnsuccessfullAttemptsExceed;
	private boolean userLocked;
	private Date createdDate;
	private Date lastUpdatedDate;
	private Date userlockedtime;
	
	
	
	
	public Date getUserlockedtime() {
		return userlockedtime;
	}

	public void setUserlockedtime(Date userlockedtime) {
		this.userlockedtime = userlockedtime;
	}

	public CpLoginAuditTrailsDTO() {
		
	}
	
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
	public Date getLoggedInTime() {
		return loggedInTime;
	}
	public void setLoggedInTime(Date loggedInTime) {
		this.loggedInTime = loggedInTime;
	}
	public Date getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
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

	@Override
	public String toString() {
		return "CpLoginAuditTrailsDTO [id=" + id + ", username=" + username + ", custRefNo=" + custRefNo
				+ ", loggedInTime=" + loggedInTime + ", logoutTime=" + logoutTime + ", unsuccessfullAttempts="
				+ unsuccessfullAttempts + ", noOfUnsuccessfullAttemptsExceed=" + noOfUnsuccessfullAttemptsExceed
				+ ", userLocked=" + userLocked + ", createdDate=" + createdDate + ", lastUpdatedDate=" + lastUpdatedDate
				+ "]";
	}

	

	
}
