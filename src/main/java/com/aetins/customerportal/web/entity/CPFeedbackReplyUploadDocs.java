package com.aetins.customerportal.web.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "cp_feedback_reply_docs")
public class CPFeedbackReplyUploadDocs implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2138225184631697844L;

	@Id
	@GeneratedValue
	@Column(name = "REPLY_DOC_ID",unique = true, nullable = false, precision = 10, scale = 0)
	private int feedBackId;
	
	@OneToOne(fetch =FetchType.LAZY)
	@JoinColumn(name = "N_REPLY_ID")
	private CpFeedbackReply cpFeedbackReply;
	
	@Column(name = "FILE_SIZE")
	private long fileSize;
	
	@Column(name = "UPLOAD_DATE")
	private Date uploadDate;
	
	@Column(name = "FILE_NAME")
	private String fileName;
	
	@Column(name = "V_USER_TYPE")
	private String uploadedUserType;
	
	@Column(name = "V_USER_ID")
	private String uploadedUserId;
	
	
	/* GETTERS & SETTERS */

	public int getFeedBackId() {
		return feedBackId;
	}

	public void setFeedBackId(int feedBackId) {
		this.feedBackId = feedBackId;
	}

	public CpFeedbackReply getCpFeedbackReply() {
		return cpFeedbackReply;
	}

	public void setCpFeedbackReply(CpFeedbackReply cpFeedbackReply) {
		this.cpFeedbackReply = cpFeedbackReply;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUploadedUserType() {
		return uploadedUserType;
	}

	public void setUploadedUserType(String uploadedUserType) {
		this.uploadedUserType = uploadedUserType;
	}

	public String getUploadedUserId() {
		return uploadedUserId;
	}

	public void setUploadedUserId(String uploadedUserId) {
		this.uploadedUserId = uploadedUserId;
	}

	@Override
	public String toString() {
		return "CPFeedbackReplyUploadDocs [feedBackId=" + feedBackId + ", cpFeedbackReply=" + cpFeedbackReply
				+ ", fileSize=" + fileSize + ", uploadDate=" + uploadDate + ", fileName=" + fileName
				+ ", uploadedUserType=" + uploadedUserType + ", uploadedUserId=" + uploadedUserId + "]";
	}
}
