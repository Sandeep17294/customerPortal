package com.aetins.customerportal.web.dto;

import java.util.Date;
import java.util.Set;



public class FeedbackReplyDTO {
	
	private int replyId;
	private CpFeedbackDTO mapFeedbackId;
	private String userType;
	private String  userId;
	private Date replyDate;
	private String replyNote;
	private String replyRate;
	private String closeComp;
	private int parentRepId;
	private String lastupdUser;
	private Date lastupdDate;
	private String lastupdProg;
	private FeedbackReplyDocsDTO feedbackReplyDocsDTO;
	private boolean fileUpload;
	
	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	public CpFeedbackDTO getMapFeedbackId() {
		return mapFeedbackId;
	}
	public void setMapFeedbackId(CpFeedbackDTO mapFeedbackId) {
		this.mapFeedbackId = mapFeedbackId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}
	public String getReplyNote() {
		return replyNote;
	}
	public void setReplyNote(String replyNote) {
		this.replyNote = replyNote;
	}
	public String getReplyRate() {
		return replyRate;
	}
	public void setReplyRate(String replyRate) {
		this.replyRate = replyRate;
	}
	public String getCloseComp() {
		return closeComp;
	}
	public void setCloseComp(String closeComp) {
		this.closeComp = closeComp;
	}
	public int getParentRepId() {
		return parentRepId;
	}
	public void setParentRepId(int parentRepId) {
		this.parentRepId = parentRepId;
	}
	public String getLastupdUser() {
		return lastupdUser;
	}
	public void setLastupdUser(String lastupdUser) {
		this.lastupdUser = lastupdUser;
	}
	public Date getLastupdDate() {
		return lastupdDate;
	}
	public void setLastupdDate(Date lastupdDate) {
		this.lastupdDate = lastupdDate;
	}
	public String getLastupdProg() {
		return lastupdProg;
	}
	public void setLastupdProg(String lastupdProg) {
		this.lastupdProg = lastupdProg;
	}
	public FeedbackReplyDocsDTO getFeedbackReplyDocsDTO() {
		return feedbackReplyDocsDTO;
	}
	public void setFeedbackReplyDocsDTO(FeedbackReplyDocsDTO feedbackReplyDocsDTO) {
		this.feedbackReplyDocsDTO = feedbackReplyDocsDTO;
	}
	public boolean isFileUpload() {
		return fileUpload;
	}
	public void setFileUpload(boolean fileUpload) {
		this.fileUpload = fileUpload;
	}
}
