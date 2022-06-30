package com.aetins.customerportal.web.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * This Entity class for Request Master 20/02/2017
 * 
 * @author HarmainZainab
 */

@Entity
@Table(name = "CP_SERVICE_REQUEST_MASTER")
public class CpRequestMaster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7668406577455799628L;

	private int serviceRequestNo;
	private Date serviceRequestDate;
	private String serviceRequestType;
	private String PolicyNo;
	private String userId;
	private String requestStatus;
	private String requestStatusDesc;
	private Date processedDate;
	private String processedBy;
	private Date recentUpdate;
	private String applicable;
	private String userOtp;
	private String fatcaFlag;
	
	private BigDecimal seqno;
	
	private String transactionreceiptno;
	
	private String topupcheck;
	

	private List<CpRedirectionSwitching> cpRedirections;
	private List<CpReinStatement> cpReinStatement;
	private List<CpPartialWithdrawalFunds> cpPartialWithdrawalFunds;
	//private Set<CpPwPayment> cpPwPayments;
	private Set<CpContributionHoliday> cpContributionHoliday;
	private Set<CpContributionAlteration> contributionAlterations;
	//private Set<CpSumAssuredAlteration> sumAssuredAlterations;
	//private Set<CpTermAlteration> termAlterations;
	private Set<CpProtectionBenifit> cpProtectionBenifit;
	private Set<CpNFAddress> cpNFAddressList;
	private Set<CpNFAddressContacts> cpNFAddressContactList;
	private Set<CpNFPersonalContacts> cpNFPersonalContactList;
	private Set<CpNFPersonalDetails> cpNFPersonalDetailList;
	private Set<CpFatca> cpFacta;
	
	private List<CpClaimIntimation> cpClaimIntimation;
	
	private CPServiceRequestDocuments requestDocuments;

	//private List<CpRider> cpRiders;

	@Id
	@Column(name = "SERVICE_REQUEST_NO")
	public int getServiceRequestNo() {
		return serviceRequestNo;
	}

	
	
	@OneToMany(mappedBy = "nServicRequestNo", targetEntity = CpPartialWithdrawalFunds.class, cascade = CascadeType.ALL)
	public List<CpPartialWithdrawalFunds> getCpPartialWithdrawalFunds() {
		return cpPartialWithdrawalFunds;
	}




	public void setCpPartialWithdrawalFunds(List<CpPartialWithdrawalFunds> cpPartialWithdrawalFunds) {
		this.cpPartialWithdrawalFunds = cpPartialWithdrawalFunds;
	}
	
	
	



	@OneToMany(mappedBy = "nServicRequestNo", targetEntity = CpClaimIntimation.class, cascade = CascadeType.ALL)
	public List<CpClaimIntimation> getCpClaimIntimation() {
		return cpClaimIntimation;
	}



	public void setCpClaimIntimation(List<CpClaimIntimation> cpClaimIntimation) {
		this.cpClaimIntimation = cpClaimIntimation;
	}



	@OneToMany(mappedBy = "serviceRequestNo", targetEntity = CpRedirectionSwitching.class, cascade = CascadeType.ALL)
	public List<CpRedirectionSwitching> getCpRedirections() {
		return cpRedirections;
	}

	@OneToMany(mappedBy = "serviceRequestNo", targetEntity = CpFatca.class, cascade = CascadeType.ALL)
	public Set<CpFatca> getCpFacta() {
		return cpFacta;
	}

	public void setCpFacta(Set<CpFatca> cpFacta) {
		this.cpFacta = cpFacta;
	}

	@OneToMany(mappedBy = "nServicRequestNo", targetEntity = CpReinStatement.class, cascade = CascadeType.ALL)
	public List<CpReinStatement> getCpReinStatement() {
		return cpReinStatement;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "nServicRequestNo", targetEntity = CpContributionHoliday.class, cascade = CascadeType.ALL)
	public Set<CpContributionHoliday> getCpContributionHoliday() {
		return cpContributionHoliday;
	}

	public void setCpContributionHoliday(Set<CpContributionHoliday> cpContributionHoliday) {
		this.cpContributionHoliday = cpContributionHoliday;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "serviceRequestNo", targetEntity = CpContributionAlteration.class, cascade = CascadeType.ALL)
	public Set<CpContributionAlteration> getContributionAlterations() {
		return contributionAlterations;
	}

	public void setContributionAlterations(Set<CpContributionAlteration> contributionAlterations) {
		this.contributionAlterations = contributionAlterations;
	}

	@OneToMany(mappedBy = "nServiceReqNo", targetEntity = CpNFAddress.class, cascade = CascadeType.ALL)
	public Set<CpNFAddress> getCpNFAddressList() {
		return cpNFAddressList;
	}

	public void setCpNFAddressList(Set<CpNFAddress> cpNFAddressList) {
		this.cpNFAddressList = cpNFAddressList;
	}

	@OneToMany(mappedBy = "nServiceReqNo", targetEntity = CpNFAddressContacts.class, cascade = CascadeType.ALL)
	public Set<CpNFAddressContacts> getCpNFAddressContactList() {
		return cpNFAddressContactList;
	}

	public void setCpNFAddressContactList(Set<CpNFAddressContacts> cpNFAddressContactList) {
		this.cpNFAddressContactList = cpNFAddressContactList;
	}

	@OneToMany(mappedBy = "nServiceReqNo", targetEntity = CpNFPersonalContacts.class, cascade = CascadeType.ALL)
	public Set<CpNFPersonalContacts> getCpNFPersonalContactList() {
		return cpNFPersonalContactList;
	}

	public void setCpNFPersonalContactList(Set<CpNFPersonalContacts> cpNFPersonalContactList) {
		this.cpNFPersonalContactList = cpNFPersonalContactList;
	}

	@OneToMany(mappedBy = "nServiceReqNo", targetEntity = CpNFPersonalDetails.class, cascade = CascadeType.ALL)
	public Set<CpNFPersonalDetails> getCpNFPersonalDetailList() {
		return cpNFPersonalDetailList;
	}

	public void setCpNFPersonalDetailList(Set<CpNFPersonalDetails> cpNFPersonalDetailList) {
		this.cpNFPersonalDetailList = cpNFPersonalDetailList;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "serviceRequestNo", targetEntity = CpProtectionBenifit.class, cascade = CascadeType.ALL)
	public Set<CpProtectionBenifit> getCpProtectionBenifit() {
		return cpProtectionBenifit;
	}

	public void setCpProtectionBenifit(Set<CpProtectionBenifit> cpProtectionBenifit) {
		this.cpProtectionBenifit = cpProtectionBenifit;
	}

	@Column(name = "SERVICE_REQUEST_DATE")
	public Date getServiceRequestDate() {
		return serviceRequestDate;
	}

	@Column(name = "SERVICE_REQUEST_TYPE")
	public String getServiceRequestType() {
		return serviceRequestType;
	}

	@Column(name = "POLICY_NO")
	public String getPolicyNo() {
		return PolicyNo;
	}

	@Column(name = "USER_ID")
	public String getUserId() {
		return userId;
	}

	@Column(name = "REQUEST_STATUS")
	public String getRequestStatus() {
		return requestStatus;
	}

	@Column(name = "PROCESSED_DATE")
	public Date getProcessedDate() {
		return processedDate;
	}

	@Column(name = "PROCESSED_BY")
	public String getProcessedBy() {
		return processedBy;
	}

	@Column(name = "RECENT_UPDATE_DATE")
	public Date getRecentUpdate() {
		return recentUpdate;
	}

	@Column(name = "APPLICABLE")
	public String getApplicable() {
		return applicable;
	}

	@Column(name = "USER_OTP")
	public String getUserOtp() {
		return userOtp;
	}
	
	@Column(name = "FATCA_FLAG")
	public String getFatcaFlag() {
		return fatcaFlag;
	}

	@Column(name = "REQUEST_STATUS_DESC")
	public String getRequestStatusDesc() {
		return requestStatusDesc;
	}

	// setter
	public void setUserOtp(String userOtp) {
		this.userOtp = userOtp;
	}

	public void setApplicable(String applicable) {
		this.applicable = applicable;
	}

	public void setServiceRequestNo(int serviceRequestNo) {
		this.serviceRequestNo = serviceRequestNo;
	}

	public void setServiceRequestDate(Date serviceRequestDate) {
		this.serviceRequestDate = serviceRequestDate;
	}

	public void setServiceRequestType(String serviceRequestType) {
		this.serviceRequestType = serviceRequestType;
	}

	public void setPolicyNo(String policyNo) {
		this.PolicyNo = policyNo;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public void setProcessedDate(Date processedDate) {
		this.processedDate = processedDate;
	}

	public void setProcessedBy(String processedBy) {
		this.processedBy = processedBy;
	}

	public void setRecentUpdate(Date recentUpdate) {
		this.recentUpdate = recentUpdate;
	}

	public void setCpRedirections(List<CpRedirectionSwitching> cpRedirections) {
		this.cpRedirections = cpRedirections;
	}

	public void setCpReinStatement(List<CpReinStatement> cpReinStatement) {
		this.cpReinStatement = cpReinStatement;
	}

	public void setFatcaFlag(String fatcaFlag) {
		this.fatcaFlag = fatcaFlag;
	}
	public void setRequestStatusDesc(String requestStatusDesc) {
		this.requestStatusDesc = requestStatusDesc;
	}

	@Column(name = "SEQ_NO")
	public BigDecimal getSeqno() {
		return seqno;
	}

	public void setSeqno(BigDecimal seqno) {
		this.seqno = seqno;
	}


	@Column(name = "TRANS_NO")
	public String getTransactionreceiptno() {
		return transactionreceiptno;
	}
	public void setTransactionreceiptno(String transactionreceiptno) {
		this.transactionreceiptno = transactionreceiptno;
	}
	
	


	@Column(name = "TOPUP_CHECK")
	public String getTopupcheck() {
		return topupcheck;
	}
	public void setTopupcheck(String topupcheck) {
		this.topupcheck = topupcheck;
	}
	
	//@OneToOne(mappedBy = "serviceRequestNo",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@OneToOne(mappedBy = "serviceRequestNo", targetEntity = CPServiceRequestDocuments.class, cascade = CascadeType.ALL)
	public CPServiceRequestDocuments getRequestDocuments() {
		return requestDocuments;
	}

	public void setRequestDocuments(CPServiceRequestDocuments requestDocuments) {
		this.requestDocuments = requestDocuments;
	}
}
