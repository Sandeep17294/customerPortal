package com.aetins.customerportal.web.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResetSecurityAnswerDTO {

	private int id;
	private String userName;
	private int custRefNo;
	private String securityQues;
	private String securityAns;
	private String quesStatus;
	private Date processDate;
	private Date recentModifiedDate;
	private String userOtp;
	private String question1;
	private String question2;
	private String answer1;
	private String answer2;
	private boolean editEnable;
	List<ListItem> secretQuestions = new ArrayList();

	public boolean isEditEnable() {
		return editEnable;
	}

	public void setEditEnable(boolean editEnable) {
		this.editEnable = editEnable;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getCustRefNo() {
		return custRefNo;
	}

	public void setCustRefNo(int custRefNo) {
		this.custRefNo = custRefNo;
	}

	public String getSecurityQues() {
		return securityQues;
	}

	public void setSecurityQues(String securityQues) {
		this.securityQues = securityQues;
	}

	public String getSecurityAns() {
		return securityAns;
	}

	public void setSecurityAns(String securityAns) {
		this.securityAns = securityAns;
	}

	public String getQuesStatus() {
		return quesStatus;
	}

	public void setQuesStatus(String quesStatus) {
		this.quesStatus = quesStatus;
	}

	public Date getProcessDate() {
		return processDate;
	}

	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}

	public Date getRecentModifiedDate() {
		return recentModifiedDate;
	}

	public void setRecentModifiedDate(Date recentModifiedDate) {
		this.recentModifiedDate = recentModifiedDate;
	}

	public String getUserOtp() {
		return userOtp;
	}

	public void setUserOtp(String userOtp) {
		this.userOtp = userOtp;
	}

	public String getQuestion1() {
		return question1;
	}

	public String getQuestion2() {
		return question2;
	}

	public void setQuestion1(String question1) {
		this.question1 = question1;
	}

	public void setQuestion2(String question2) {
		this.question2 = question2;
	}

	public String getAnswer1() {
		return answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public List<ListItem> getSecretQuestions() {
		return secretQuestions;
	}

	public void setSecretQuestions(List<ListItem> secretQuestions) {
		this.secretQuestions = secretQuestions;
	}

}
