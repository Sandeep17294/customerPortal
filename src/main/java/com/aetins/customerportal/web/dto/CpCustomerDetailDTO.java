package com.aetins.customerportal.web.dto;

import java.util.Date;

import javax.faces.bean.ManagedBean;

public class CpCustomerDetailDTO {
	
	private int nid;
	private int ncustRefNo;
	private String vgroup;
	private String vemail;
	private String nidNo;
	private String vidType;
	private String vcustName;
	private Date dDob;
	private Date dDobHijri;
	private String title;
	private String gender;
	
	
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}

	public int getNcustRefNo() {
		return ncustRefNo;
	}
	public void setNcustRefNo(int ncustRefNo) {
		this.ncustRefNo = ncustRefNo;
	}
	public String getVgroup() {
		return vgroup;
	}
	public void setVgroup(String vgroup) {
		this.vgroup = vgroup;
	}
	public String getVemail() {
		return vemail;
	}
	public void setVemail(String vemail) {
		this.vemail = vemail;
	}
	public String getNidNo() {
		return nidNo;
	}
	public void setNidNo(String nidNo) {
		this.nidNo = nidNo;
	}
	public String getVidType() {
		return vidType;
	}
	public void setVidType(String vidType) {
		this.vidType = vidType;
	}
	public String getVcustName() {
		return vcustName;
	}
	public void setVcustName(String vcustName) {
		this.vcustName = vcustName;
	}
	public Date getdDob() {
		return dDob;
	}
	public void setdDob(Date dDob) {
		this.dDob = dDob;
	}
	public Date getdDobHijri() {
		return dDobHijri;
	}
	public void setdDobHijri(Date dDobHijri) {
		this.dDobHijri = dDobHijri;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}
