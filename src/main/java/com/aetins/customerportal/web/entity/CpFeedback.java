package com.aetins.customerportal.web.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CP_FEEDBACK")
public class CpFeedback implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7333406571552799824L;

	private int nFeedbackId;
	private String vComplaintNo;
	private String vUserId;
	private Date dCompDate;
	private String vFeedbackType;
	private String vPolicyNo;
	private String vServType;
	private String vFeedbackNote;
	private String vAgentCall;
	private String vCsdCall;
	private String vCallTime;
	private String vFeedRate;
	private String vFeedStatus;
	private String vlastupdUser;
	private Date vlastupdDate;
	private String vlastupdProg;
	private Date dClosedDate;
	private String vCalltimeDay;
	private Set<CpFeedbackReply> cpFeedbackReplys;
	private String vRemark;
	// getter
	/*@Id
	@GeneratedValue*/
	@Id
	@GeneratedValue
	@Column(name = "N_FEEDBACK_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public int getnFeedbackId() {
		return nFeedbackId;
	}

	/*@Column(name = "V_COMPLAINT_NO", unique = true, nullable = false, length = 50)*/
	
	

	@Column(name="V_COMPLAINT_NO")
	public String getvComplaintNo() {
		return vComplaintNo;
		//return "CMP435";
	}
	
	@Column(name="V_REMARK")
	public String getvRemark() {
		return vRemark;
		//return "CMP435";
	}

	@Column(name = "V_USER_ID", nullable = false, length = 50)
	public String getvUserId() {
		return vUserId;
	}

	@Column(name = "D_COMPLAINT_DATE", nullable = false)
	public Date getdCompDate() {
		return dCompDate;
	}

	@Column(name = "V_FEEDBACK_TYPE", nullable = false, length = 1)
	public String getvFeedbackType() {
		return vFeedbackType;
	}

	@Column(name = "V_POLICY_NO", nullable = false, length = 50)
	public String getvPolicyNo() {
		return vPolicyNo;
	}

	@Column(name = "V_SERVICE_TYPE", length = 100)
	public String getvServType() {
		return vServType;
	}

	@Column(name = "V_FEEDBACK_NOTE", length = 500)
	public String getvFeedbackNote() {
		return vFeedbackNote;
	}

	@Column(name = "V_AGENT_CALL", length = 1)
	public String getvAgentCall() {
		return vAgentCall;
	}

	@Column(name = "V_CSD_CALL", length = 1)
	public String getvCsdCall() {
		return vCsdCall;
	}

	@Column(name = "V_CALL_TIME", length = 50)
	public String getvCallTime() {
		return vCallTime;
	}

	@Column(name = "V_FEEDBACK_RATE", length = 50)
	public String getvFeedRate() {
		return vFeedRate;
	}

	@Column(name = "V_FEEDBACK_STATUS", length = 20)
	public String getvFeedStatus() {
		return vFeedStatus;
	}

	@Column(name = "V_LASTUPD_USER", length = 50)
	public String getVlastupdUser() {
		return vlastupdUser;
	}

	@Column(name = "D_LASTUPD_DATE")
	public Date getVlastupdDate() {
		return vlastupdDate;
	}

	@Column(name = "V_LASTUPD_PROG", length = 50)
	public String getVlastupdProg() {
		return vlastupdProg;
	}

	
	@Column(name = "D_CLOSED_DATE")
	public Date getdClosedDate() {
		return dClosedDate;
	}
	
	@Column(name = "V_CALLTIME_DAY")
	public String getvCalltimeDay() {
		return vCalltimeDay;
	}

	//setter
	public void setnFeedbackId(int nFeedbackId) {
		this.nFeedbackId = nFeedbackId;
	}

	public void setvComplaintNo(String vComplaintNo) {
		this.vComplaintNo = vComplaintNo;
	}

	public void setvUserId(String vUserId) {
		this.vUserId = vUserId;
	}

	public void setdCompDate(Date dCompDate) {
		this.dCompDate = dCompDate;
	}

	public void setvFeedbackType(String vFeedbackType) {
		this.vFeedbackType = vFeedbackType;
	}

	public void setvPolicyNo(String vPolicyNo) {
		this.vPolicyNo = vPolicyNo;
	}

	public void setvServType(String vServType) {
		this.vServType = vServType;
	}

	public void setvFeedbackNote(String vFeedbackNote) {
		this.vFeedbackNote = vFeedbackNote;
	}

	public void setvAgentCall(String vAgentCall) {
		this.vAgentCall = vAgentCall;
	}

	public void setvCsdCall(String vCsdCall) {
		this.vCsdCall = vCsdCall;
	}

	public void setvCallTime(String vCallTime) {
		this.vCallTime = vCallTime;
	}

	public void setvFeedRate(String vFeedRate) {
		this.vFeedRate = vFeedRate;
	}

	public void setvFeedStatus(String vFeedStatus) {
		this.vFeedStatus = vFeedStatus;
	}

	public void setVlastupdUser(String vlastupdUser) {
		this.vlastupdUser = vlastupdUser;
	}

	public void setVlastupdDate(Date vlastupdDate) {
		this.vlastupdDate = vlastupdDate;
	}

	public void setVlastupdProg(String vlastupdProg) {
		this.vlastupdProg = vlastupdProg;
	}

	public void setdClosedDate(Date dClosedDate) {
		this.dClosedDate = dClosedDate;
	}
	
	@OneToMany(mappedBy="nMapFeedbackId",targetEntity=CpFeedbackReply.class,cascade=CascadeType.ALL)
	public Set<CpFeedbackReply> getCpFeedbackReplys() {
		return cpFeedbackReplys;
	}

	public void setCpFeedbackReplys(Set<CpFeedbackReply> cpFeedbackReplys) {
		this.cpFeedbackReplys = cpFeedbackReplys;
	}

	public void setvCalltimeDay(String vCalltimeDay) {
		this.vCalltimeDay = vCalltimeDay;
	}
	
	public void setvRemark(String vRemark) {
		this.vRemark = vRemark;
	}

	
	
}