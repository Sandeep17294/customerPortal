package com.aetins.customerportal.web.dto;

import java.util.Date;

public class CpFeedbackDTO {

	private int feedbackId;
	private String complaintNo;
	private String userId;
	private Date compDate;
	private String feedbackType;
	private String policyNo;
	private String servType;
	private String feedbackNote;
	private String agentCall;
	private String csdCall;
	private String callTime;
	private String feedRate;
	private String feedStatus;
	private String lastupdUser;
	private Date lastupdDate;
	private String lastupdProg;
	private Date closedDate;
	private String calltimeDay;
	private String remark;
	private boolean checkbox;
	
	public CpFeedbackDTO() {
		
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}
	public String getRemark() {
		return remark;
	}
	public boolean isCheckbox() {
		return checkbox;
	}
	// getter and setter
	public int getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}
	public String getComplaintNo() {
		return complaintNo;
	}
	public void setComplaintNo(String complaintNo) {
		this.complaintNo = complaintNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getCompDate() {
		return compDate;
	}
	public void setCompDate(Date compDate) {
		this.compDate = compDate;
	}
	public String getFeedbackType() {
		return feedbackType;
	}
	public void setFeedbackType(String feedbackType) {
		this.feedbackType = feedbackType;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getServType() {
		return servType;
	}
	public void setServType(String servType) {
		this.servType = servType;
	}
	public String getFeedbackNote() {
		return feedbackNote;
	}
	public void setFeedbackNote(String feedbackNote) {
		this.feedbackNote = feedbackNote;
	}
	public String getCsdCall() {
		return csdCall;
	}
	public void setCsdCall(String csdCall) {
		this.csdCall = csdCall;
	}
	public String getCallTime() {
		return callTime;
	}
	public void setCallTime(String callTime) {
		this.callTime = callTime;
	}
	public String getFeedRate() {
		return feedRate;
	}
	public void setFeedRate(String feedRate) {
		this.feedRate = feedRate;
	}
	public String getFeedStatus() {
		return feedStatus;
	}
	public void setFeedStatus(String feedStatus) {
		this.feedStatus = feedStatus;
	}
	public String getLastupdUser() {
		return lastupdUser;
	}
	public void setLastupdUser(String lastupdUser) {
		this.lastupdUser = lastupdUser;
	}
	public Date getLastupdDate() {
		return lastupdDate;
	}
	public void setLastupdDate(Date lastupdDate) {
		this.lastupdDate = lastupdDate;
	}
	public String getLastupdProg() {
		return lastupdProg;
	}
	public void setLastupdProg(String lastupdProg) {
		this.lastupdProg = lastupdProg;
	}
	public String getAgentCall() {
		return agentCall;
	}
	public void setAgentCall(String agentCall) {
		this.agentCall = agentCall;
	}
	public Date getClosedDate() {
		return closedDate;
	}
	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}
	public String getCalltimeDay() {
		return calltimeDay;
	}
	public void setCalltimeDay(String calltimeDay) {
		this.calltimeDay = calltimeDay;
	}
	
	
	
}
