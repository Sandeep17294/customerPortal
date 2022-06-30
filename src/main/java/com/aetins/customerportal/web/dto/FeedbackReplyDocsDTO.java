package com.aetins.customerportal.web.dto;


import java.util.Arrays;
import java.util.Date;

public class FeedbackReplyDocsDTO {
	
	
	private int feedBackId;
	private String fileName;
	private String fileType;
	private byte[] data;
	private String uploadedUserType;
	private String uploadedUserId;
	private long fileSize;
	private CpFeedbackDTO cpFeedbackReply;
	private Date uploadDate;
	
	
	public int getFeedBackId() {
		return feedBackId;
	}
	public void setFeedBackId(int feedBackId) {
		this.feedBackId = feedBackId;
	}
	public CpFeedbackDTO getCpFeedbackReply() {
		return cpFeedbackReply;
	}
	public void setCpFeedbackReply(CpFeedbackDTO cpFeedbackReply) {
		this.cpFeedbackReply = cpFeedbackReply;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
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
		return "FeedbackReplyDocsDTO [feedBackId=" + feedBackId + ", fileName=" + fileName + ", fileType=" + fileType
				+ ", data=" + Arrays.toString(data) + ", uploadedUserType=" + uploadedUserType + ", uploadedUserId="
				+ uploadedUserId + ", fileSize=" + fileSize + ", cpFeedbackReply=" + cpFeedbackReply + ", uploadDate="
				+ uploadDate + "]";
	}
	

}
