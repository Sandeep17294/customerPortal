package com.aetins.customerportal.web.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author avinash
 *
 */
public class PremiumDefermentDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2953875350627107060L;
	
	private int transNo;
	
	private String vIdenCode;
	
	private String vIdenNo;
	
	private String vRecordStatus;
	
	private String vReqStatus;
	
	private String policyName;
	
	private String vPolicyNo;
	
	private String policyStatus;
	
	private Date commencementDate;
	
	private Date premiumDueDate;
	
	private Date outstandingDueDate;
	
	private Date deferStartDate;
	
	private String deferPeriod;
	
	private String vRemarks;
	
	private boolean isNewTransaction;
	
	
	public PremiumDefermentDTO(int transNo, String V_IDEN_CODE, String V_IDEN_NO, String V_RECORD_STATUS,
			String V_REQUEST_STATUS, String V_POLICY_NO, String policyStatus, Date commencementDate,
			Date premiumDate, Date outsDate, Date D_DEFER_START_DT, String N_DEFEMENT_PERIOD,
			String V_REMARKS,boolean isNewTransaction) {

		this.transNo = transNo;
		this.vIdenCode = V_IDEN_CODE;
		this.vIdenNo = V_IDEN_NO;
		this.vRecordStatus = V_RECORD_STATUS;
		this.vReqStatus = V_REQUEST_STATUS;
		this.vPolicyNo = V_POLICY_NO;
		this.policyStatus = policyStatus;
		this.commencementDate = commencementDate;
		this.premiumDueDate = premiumDate;
		this.outstandingDueDate = outsDate;
		this.deferStartDate = D_DEFER_START_DT;
		this.deferPeriod = N_DEFEMENT_PERIOD;
		this.vRemarks = V_REMARKS;
		this.isNewTransaction = isNewTransaction;
	}
	
	public PremiumDefermentDTO() {
		
	}

	public int getTransNo() {
		return transNo;
	}

	public void setTransNo(int transNo) {
		this.transNo = transNo;
	}

	public String getvIdenCode() {
		return vIdenCode;
	}

	public void setvIdenCode(String vIdenCode) {
		this.vIdenCode = vIdenCode;
	}

	public String getvIdenNo() {
		return vIdenNo;
	}

	public void setvIdenNo(String vIdenNo) {
		this.vIdenNo = vIdenNo;
	}

	public String getvRecordStatus() {
		return vRecordStatus;
	}

	public void setvRecordStatus(String vRecordStatus) {
		this.vRecordStatus = vRecordStatus;
	}

	public String getvReqStatus() {
		return vReqStatus;
	}

	public void setvReqStatus(String vReqStatus) {
		this.vReqStatus = vReqStatus;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getvPolicyNo() {
		return vPolicyNo;
	}

	public void setvPolicyNo(String vPolicyNo) {
		this.vPolicyNo = vPolicyNo;
	}

	public String getPolicyStatus() {
		return policyStatus;
	}

	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}

	public Date getCommencementDate() {
		return commencementDate;
	}

	public void setCommencementDate(Date commencementDate) {
		this.commencementDate = commencementDate;
	}

	public Date getPremiumDueDate() {
		return premiumDueDate;
	}

	public void setPremiumDueDate(Date premiumDueDate) {
		this.premiumDueDate = premiumDueDate;
	}

	public Date getOutstandingDueDate() {
		return outstandingDueDate;
	}

	public void setOutstandingDueDate(Date outstandingDueDate) {
		this.outstandingDueDate = outstandingDueDate;
	}

	public Date getDeferStartDate() {
		return deferStartDate;
	}

	public void setDeferStartDate(Date deferStartDate) {
		this.deferStartDate = deferStartDate;
	}

	public String getDeferPeriod() {
		return deferPeriod;
	}

	public void setDeferPeriod(String deferPeriod) {
		this.deferPeriod = deferPeriod;
	}

	public String getvRemarks() {
		return vRemarks;
	}

	public void setvRemarks(String vRemarks) {
		this.vRemarks = vRemarks;
	}

	public boolean isNewTransaction() {
		return isNewTransaction;
	}

	public void setNewTransaction(boolean isNewTransaction) {
		this.isNewTransaction = isNewTransaction;
	}

	@Override
	public String toString() {
		return "PremiumDefermentDTO [transNo=" + transNo + ", vIdenCode=" + vIdenCode + ", vIdenNo=" + vIdenNo
				+ ", vRecordStatus=" + vRecordStatus + ", vReqStatus=" + vReqStatus + ", policyName=" + policyName
				+ ", vPolicyNo=" + vPolicyNo + ", policyStatus=" + policyStatus + ", commencementDate="
				+ commencementDate + ", premiumDueDate=" + premiumDueDate + ", outstandingDueDate=" + outstandingDueDate
				+ ", deferStartDate=" + deferStartDate + ", deferPeriod=" + deferPeriod + ", vRemarks=" + vRemarks
				+ ", isNewTransaction=" + isNewTransaction + "]";
	}	
}
