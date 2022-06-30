package com.aetins.customerportal.web.dto;

import java.util.Date;



public class DocumentDTO {
	
	private int docId;
	private String docName;
	private String docDesc;
	private String docFilepath;
	private Date uploadDate;
	private String uploadUser;
	private Date lastupdDate;
	private String lastupdUser;
	private String lastupdProg;
	
	//getter
	public int getDocId() {
		return docId;
	}
	public String getDocName() {
		return docName;
	}
	public String getDocDesc() {
		return docDesc;
	}
	public String getDocFilepath() {
		return docFilepath;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public String getUploadUser() {
		return uploadUser;
	}
	public Date getLastupdDate() {
		return lastupdDate;
	}
	public String getLastupdUser() {
		return lastupdUser;
	}
	public String getLastupdProg() {
		return lastupdProg;
	}
	
	//setter
	public void setDocId(int docId) {
		this.docId = docId;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public void setDocDesc(String docDesc) {
		this.docDesc = docDesc;
	}
	public void setDocFilepath(String docFilepath) {
		this.docFilepath = docFilepath;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}
	public void setLastupdDate(Date lastupdDate) {
		this.lastupdDate = lastupdDate;
	}
	public void setLastupdUser(String lastupdUser) {
		this.lastupdUser = lastupdUser;
	}
	public void setLastupdProg(String lastupdProg) {
		this.lastupdProg = lastupdProg;
	}
	
	
}
