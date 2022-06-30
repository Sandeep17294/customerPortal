package com.aetins.customerportal.web.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * This Entity class for Document information
 * @author Viswakarthick
 * 05/02/2017
 */
@Entity
@Table(name="CP_DOCUMENT")
public class CpDocument implements Serializable{
	
	private static final long serialVersionUID = -4022490356906058027L;
	private int ndocId;
	private String vDocName;
	private String vDocDesc;
	private String vDocFilepath;
	private Date dUploadDate;
	private String vUploadUser;
	private Date vLastupdDate;
	private String vLastupdUser;
	private String vLastupdProg;
	
	//getter
	@Id
	@GeneratedValue
	@Column(name="N_DOC_ID")
	public int getNdocId() {
		return ndocId;
	}
	@Column(name="V_DOC_NAME")
	public String getvDocName() {
		return vDocName;
	}
	@Column(name="V_DOC_DESC")
	public String getvDocDesc() {
		return vDocDesc;
	}
	@Column(name="V_DOC_FILEPATH")
	public String getvDocFilepath() {
		return vDocFilepath;
	}
	@Column(name="D_UPLOAD_DATE")
	public Date getdUploadDate() {
		return dUploadDate;
	}
	@Column(name="V_UPLOAD_USER")
	public String getvUploadUser() {
		return vUploadUser;
	}
	@Column(name="D_LASTUPD_DATE")
	public Date getvLastupdDate() {
		return vLastupdDate;
	}
	@Column(name="V_LASTUPD_USER")
	public String getvLastupdUser() {
		return vLastupdUser;
	}
	@Column(name="V_LASTUPD_PROG")
	public String getvLastupdProg() {
		return vLastupdProg;
	}
	//setter
	public void setNdocId(int ndocId) {
		this.ndocId = ndocId;
	}
	public void setvDocName(String vDocName) {
		this.vDocName = vDocName;
	}
	public void setvDocDesc(String vDocDesc) {
		this.vDocDesc = vDocDesc;
	}
	public void setvDocFilepath(String vDocFilepath) {
		this.vDocFilepath = vDocFilepath;
	}
	public void setdUploadDate(Date dUploadDate) {
		this.dUploadDate = dUploadDate;
	}
	public void setvUploadUser(String vUploadUser) {
		this.vUploadUser = vUploadUser;
	}
	public void setvLastupdDate(Date vLastupdDate) {
		this.vLastupdDate = vLastupdDate;
	}
	public void setvLastupdUser(String vLastupdUser) {
		this.vLastupdUser = vLastupdUser;
	}
	public void setvLastupdProg(String vLastupdProg) {
		this.vLastupdProg = vLastupdProg;
	}
	
	

}
