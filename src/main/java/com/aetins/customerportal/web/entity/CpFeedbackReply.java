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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="CP_FEEDBACK_REPLY")
public class CpFeedbackReply implements Serializable {

	private static final long serialVersionUID = 6194804052114243137L;
	
	private int nReplyId;
	private CpFeedback nMapFeedbackId;
	private String vUserType;
	private String  vUserId;
	private Date dReplyDate;
	private String vReplyNote;
	private String vReplyRate;
	private String vCloseComp;
	private int nParentRepId;
	private String vlastupdUser;
	private Date vlastupdDate;
	private String vlastupdProg;
	private CPFeedbackReplyUploadDocs feedBackUploadDocs;
	
	
	//getter
	@Id
	@GeneratedValue
	@Column(name = "N_REPLY_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public int getnReplyId() {
		return nReplyId;
	}
	
	@ManyToOne
	@JoinColumn(name="N_FEEDBACK_ID",referencedColumnName="N_FEEDBACK_ID")
//	@JoinTable(name="CpFeedback",joinColumns=[nFeedbackId])
	//@JoinTable(name="CP_FEEDBACK",
  //  joinColumns={@JoinColumn(name="N_FEEDBACK_ID", referencedColumnName="N_FEEDBACK_ID")},
  //  inverseJoinColumns={@JoinColumn(name="N_FEEDBACK_ID", referencedColumnName="N_FEEDBACK_ID")})
	//@Column(name="N_FEEDBACK_ID", nullable = false,precision=10,scale=0)
	public CpFeedback getnMapFeedbackId() {
		return nMapFeedbackId;
	}
	@Column(name="V_USER_TYPE", nullable = false,length=1)
	public String getvUserType() {
		return vUserType;
	}
	@Column(name="V_USER_ID", nullable = false,length=50)
	public String getvUserId() {
		return vUserId;
	}
	@Column(name="D_REPLY_DATE", nullable = false)
	public Date getdReplyDate() {
		return dReplyDate;
	}
	@Column(name="V_REPLY_NOTE",length=500)
	public String getvReplyNote() {
		return vReplyNote;
	}
	@Column(name="V_REPLY_RATE",length=50)
	public String getvReplyRate() {
		return vReplyRate;
	}
	@Column(name="V_CLOSE_COMP",length=1)
	public String getvCloseComp() {
		return vCloseComp;
	}
	@Column(name="N_PARENT_REPID",precision=10,scale=0)
	public int getnParentRepId() {
		return nParentRepId;
	}
	@Column(name="V_LASTUPD_USER",length=50)
	public String getVlastupdUser() {
		return vlastupdUser;
	}
	@Column(name="D_LASTUPD_DATE")
	public Date getVlastupdDate() {
		return vlastupdDate;
	}
	@Column(name="V_LASTUPD_PROG",length=50)
	public String getVlastupdProg() {
		return vlastupdProg;
	}
	@OneToOne(mappedBy = "cpFeedbackReply",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public CPFeedbackReplyUploadDocs getFeedBackUploadDocs() {
		return feedBackUploadDocs;
	}
	
	//setter
	public void setnReplyId(int nReplyId) {
		this.nReplyId = nReplyId;
	}
	public void setnMapFeedbackId(CpFeedback nMapFeedbackId) {
		this.nMapFeedbackId = nMapFeedbackId;
	}
	public void setvUserType(String vUserType) {
		this.vUserType = vUserType;
	}
	public void setvUserId(String vUserId) {
		this.vUserId = vUserId;
	}
	public void setdReplyDate(Date dReplyDate) {
		this.dReplyDate = dReplyDate;
	}
	public void setvReplyNote(String vReplyNote) {
		this.vReplyNote = vReplyNote;
	}
	public void setvReplyRate(String vReplyRate) {
		this.vReplyRate = vReplyRate;
	}
	public void setvCloseComp(String vCloseComp) {
		this.vCloseComp = vCloseComp;
	}
	public void setnParentRepId(int nParentRepId) {
		this.nParentRepId = nParentRepId;
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

	public void setFeedBackUploadDocs(CPFeedbackReplyUploadDocs feedBackUploadDocs) {
		this.feedBackUploadDocs = feedBackUploadDocs;
	}


	
	
}
