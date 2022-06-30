package com.aetins.customerportal.web.dto;

import java.util.Date;


public class CpUserSecurityQuestionDTO {

	private int nid;
	private String userid;
	private int refno;
	private String secques;
	private String secans;
	private String secstatus;
	private Date processeddate;
	private Date recmodifiesans;
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
		return "CpUserSecurityQuestionDTO [nid=" + nid + ", userid=" + userid + ", refno=" + refno + ", secques="
				+ secques + ", secans=" + secans + ", secstatus=" + secstatus + ", processeddate=" + processeddate
				+ ", recmodifiesans=" + recmodifiesans + ", userotp=" + userotp + "]";
	}
	
	
	
}
