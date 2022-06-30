package com.aetins.customerportal.web.entity;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CP_CUSTOMER_DETAIL")
public class CpCustomerDetail implements java.io.Serializable{
	private static final long serialVersionUID = -8160952900135226001L;

	private int nid;
	private int ncustRefNo;
	private String vgroup;
	private String vemail;
	private String vidNo;
	private String vidType;
	private String vcustName;
	private Date dDob;
	private Date dDobHijri;
	private String title;
	private String gender;
	
	//getter
	@Id
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator="gen")
	@Column(name = "N_ID", unique = true, nullable = false, precision = 15, scale = 0)
	public int getNid() {
		return nid;
	}
	
	@Column(name = "N_CUST_REF_NO")
	public int getNcustRefNo() {
		return ncustRefNo;
	}
	
	
	@Column(name = "V_GROUP")
	public String getVgroup() {
		return vgroup;
	}

	
	@Column(name = "V_EMAIL")
	public String getVemail() {
		return vemail;
	}
		
	@Column(name = "IDEN_NO")
	public String getVidNo() {
		return vidNo;
	}

	
	@Column(name = "IDEN_TYPE")
	public String getVidType() {
		return vidType;
	}

	@Column(name = "V_CUST_NAME")
	public String getVcustName() {
		return vcustName;
	}
	@Column(name = "D_DOB", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getdDob() {
		return dDob;
	}
	@Column(name = "D_DOB_HIJRI",  columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getdDobHijri() {
		return dDobHijri;
	}
	
	@Column(name="V_CUST_GENDER")
	public String getGender() {
		return gender;
	}
	
	@Column(name="V_CUST_TITLE")
	public String getTitle() {
		return title;
	}
	//setter
	public void setNid(int nid) {
		this.nid = nid;
	}
	

	public void setNcustRefNo(int ncustRefNo) {
		this.ncustRefNo = ncustRefNo;
	}

	public void setVgroup(String vgroup) {
		this.vgroup = vgroup;
	}

	public void setVemail(String vemail) {
		this.vemail = vemail;
	}

	public void setVidNo(String vidNo) {
		this.vidNo = vidNo;
	}
	
	public void setVidType(String vidType) {
		this.vidType = vidType;
	}
	
	public void setVcustName(String vcustName) {
		this.vcustName = vcustName;
	}
	public void setdDob(Date dDob) {
		this.dDob = dDob;
	}
	public void setdDobHijri(Date dDobHijri) {
		this.dDobHijri = dDobHijri;
	}

	

	public void setTitle(String title) {
		this.title = title;
	}

	

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "CpCustomerDetail [nid=" + nid + ", ncustRefNo=" + ncustRefNo + ", vgroup=" + vgroup + ", vemail="
				+ vemail + ", vidNo=" + vidNo + ", vidType=" + vidType + ", vcustName=" + vcustName + ", dDob=" + dDob
				+ ", dDobHijri=" + dDobHijri + ", title=" + title + ", gender=" + gender + "]";
	}
	
	
	
}