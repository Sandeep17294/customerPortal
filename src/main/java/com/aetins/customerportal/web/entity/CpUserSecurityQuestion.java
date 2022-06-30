package com.aetins.customerportal.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cp_user_security_question")
public class CpUserSecurityQuestion {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="N_ID")
	private int nid;
	
	@Column(name="V_USER_NAME")
	private String userid;
	
	@Column(name="N_CUST_REF_NO")
	private int refno;
	
	@Column(name="V_SECURITY_QUES")
	private String secques;
	
	@Column(name="V_SECURITY_ANS")
	private String secans;
	
	@Column(name="V_QUESTION_STATUS")
	private String secstatus;
	
	@Column(name="D_PROCESS_DATE")
	private Date processeddate;
	
	@Column(name="D_RECENT_MODIFIED_ANS")
	private Date recmodifiesans;
	
	@Column(name="V_USER_OTP")
	private String userotp;
	
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getRefno() {
		return refno;
	}
	public void setRefno(int refno) {
		this.refno = refno;
	}
	public String getSecques() {
		return secques;
	}
	public void setSecques(String secques) {
		this.secques = secques;
	}
	public String getSecans() {
		return secans;
	}
	public void setSecans(String secans) {
		this.secans = secans;
	}
	public String getSecstatus() {
		return secstatus;
	}
	public void setSecstatus(String secstatus) {
		this.secstatus = secstatus;
	}
	public Date getProcesseddate() {
		return processeddate;
	}
	public void setProcesseddate(Date processeddate) {
		this.processeddate = processeddate;
	}
	public Date getRecmodifiesans() {
		return recmodifiesans;
	}
	public void setRecmodifiesans(Date recmodifiesans) {
		this.recmodifiesans = recmodifiesans;
	}
	public String getUserotp() {
		return userotp;
	}
	public void setUserotp(String userotp) {
		this.userotp = userotp;
	}
	@Override
	public String toString() {
		return "CpUserSecurityQuestion [nid=" + nid + ", userid=" + userid + ", refno=" + refno + ", secques=" + secques
				+ ", secans=" + secans + ", secstatus=" + secstatus + ", processeddate=" + processeddate
				+ ", recmodifiesans=" + recmodifiesans + ", userotp=" + userotp + "]";
	}
	
	
	
	
}
