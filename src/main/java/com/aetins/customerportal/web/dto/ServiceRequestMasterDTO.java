package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;
import java.util.Date;


public class ServiceRequestMasterDTO {

	private int serviceRequestNo;
	private Date serviceRequestDate;
	private String serviceRequestType;
	private String policyNo;
	private String userId;
	private String requestStatus;
	private String requestStatusDesc;
	private Date processedDate;
	private String processedBy;
	private Date recentUpdateDate;
	private String applicable;
	private String requestDate;
	private String fatcaFlag;
	private String userOtp;
	
	private BigDecimal seqno;
	
	

	
	public BigDecimal getSeqno() {
		return seqno;
	}

	public void setSeqno(BigDecimal seqno) {
		this.seqno = seqno;
	}

	// getters and setters
	public int getServiceRequestNo() {
		return serviceRequestNo;
	}

	public String getApplicable() {
		return applicable;
	}

	public void setApplicable(String applicable) {
		this.applicable = applicable;
	}

	public void setServiceRequestNo(int serviceRequestNo) {
		this.serviceRequestNo = serviceRequestNo;
	}

	public Date getServiceRequestDate() {
		return serviceRequestDate;
	}

	public void setServiceRequestDate(Date serviceRequestDate) {
		this.serviceRequestDate = serviceRequestDate;
	}

	public String getServiceRequestType() {
		return serviceRequestType;
	}

	public void setServiceRequestType(String serviceRequestType) {
		this.serviceRequestType = serviceRequestType;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public Date getProcessedDate() {
		return processedDate;
	}

	public void setProcessedDate(Date processedDate) {
		this.processedDate = processedDate;
	}

	public String getProcessedBy() {
		return processedBy;
	}

	public void setProcessedBy(String processedBy) {
		this.processedBy = processedBy;
	}

	public Date getRecentUpdateDate() {
		return recentUpdateDate;
	}

	public void setRecentUpdateDate(Date recentUpdateDate) {
		this.recentUpdateDate = recentUpdateDate;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	public String getFatcaFlag() {
		return fatcaFlag;
	}

	public void setFatcaFlag(String fatcaFlag) {
		this.fatcaFlag = fatcaFlag;
	}

	public String getUserOtp() {
		return userOtp;
	}

	public void setUserOtp(String userOtp) {
		this.userOtp = userOtp;
	}

	public String getRequestStatusDesc() {
		return requestStatusDesc;
	}

	public void setRequestStatusDesc(String requestStatusDesc) {
		this.requestStatusDesc = requestStatusDesc;
	}

}
