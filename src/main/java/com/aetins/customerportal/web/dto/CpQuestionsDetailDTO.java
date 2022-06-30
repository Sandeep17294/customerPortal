package com.aetins.customerportal.web.dto;

import java.util.Date;

public class CpQuestionsDetailDTO {
	
	private int quesId;
	private String quesEN;	
	private String quesAR;	
	private String lastupduser;
	private Date lastUpdDate;
	
	public int getQuesId() {
		return quesId;
	}

	public void setQuesId(int quesId) {
		this.quesId = quesId;
	}

	public String getQuesEN() {
		return quesEN;
	}

	public void setQuesEN(String quesEN) {
		this.quesEN = quesEN;
	}

	public String getQuesAR() {
		return quesAR;
	}

	public void setQuesAR(String quesAR) {
		this.quesAR = quesAR;
	}

	public String getLastupduser() {
		return lastupduser;
	}

	public void setLastupduser(String lastupduser) {
		this.lastupduser = lastupduser;
	}

	public Date getLastUpdDate() {
		return lastUpdDate;
	}

	public void setLastUpdDate(Date lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
	}

	@Override
	public String toString() {
		return "CpQuestionsDetailDTO [quesId=" + quesId + ", quesEN=" + quesEN + ", quesAR=" + quesAR + ", lastupduser="
				+ lastupduser + ", lastUpdDate=" + lastUpdDate + "]";
	}	
}