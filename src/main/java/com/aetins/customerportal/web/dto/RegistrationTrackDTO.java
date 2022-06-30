package com.aetins.customerportal.web.dto;

import java.util.Date;


public class RegistrationTrackDTO {
	private int id;
	private String ipAddr;
	private String planNo;
	private String emailId;
	private String phoneNo;
	private String status;
	private String failReason;
	private Date regdate;
	private String lastupdUser;
	private String lastupdProg;
	private Date lastupdDate;
	private String userId;
	private String userIsfEmail;
	private String userIsfPhone;
	private String registrationDate;
	private String policyStatus;

public RegistrationTrackDTO() {
		
	}
	
	// getter and setter
	public int getId() {
		return id;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public String getPlanNo() {
		return planNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public String getStatus() {
		return status;
	}

	public String getFailReason() {
		return failReason;
	}

	public Date getRegdate() {
		return regdate;
	}

	public String getLastupdUser() {
		return lastupdUser;
	}

	public String getLastupdProg() {
		return lastupdProg;
	}

	public Date getLastupdDate() {
		return lastupdDate;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public void setLastupdUser(String lastupdUser) {
		this.lastupdUser = lastupdUser;
	}

	public void setLastupdProg(String lastupdProg) {
		this.lastupdProg = lastupdProg;
	}

	public void setLastupdDate(Date lastupdDate) {
		this.lastupdDate = lastupdDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserIsfEmail() {
		return userIsfEmail;
	}

	public String getUserIsfPhone() {
		return userIsfPhone;
	}

	public void setUserIsfEmail(String userIsfEmail) {
		this.userIsfEmail = userIsfEmail;
	}

	public void setUserIsfPhone(String userIsfPhone) {
		this.userIsfPhone = userIsfPhone;
	}
	
	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	public String getPolicyStatus() {
		return policyStatus;
	}

	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}

	@Override
	public String toString() {
		return "RegistrationTrackDTO [id=" + id + ", ipAddr=" + ipAddr + ", planNo=" + planNo + ", emailId=" + emailId
				+ ", phoneNo=" + phoneNo + ", status=" + status + ", failReason=" + failReason + ", regdate=" + regdate
				+ ", lastupdUser=" + lastupdUser + ", lastupdProg=" + lastupdProg + ", lastupdDate=" + lastupdDate
				+ ", userId=" + userId + ", userIsfEmail=" + userIsfEmail + ", userIsfPhone=" + userIsfPhone
				+ ", registrationDate=" + registrationDate + ", policyStatus=" + policyStatus + "]";
	}

	

	

	

}
