package com.aetins.customerportal.web.dto;

import java.util.Date;
import java.util.List;

import com.aetins.customerportal.web.entity.CpListBoxAnswers;

public class CpQuestionnaireDTO {

	private Long serviceId;
	private String serviceName;
	private String domain;
	private Long questionId;
	private String questionNameEng;
	private String questionNameArb;
	private boolean mandatory;
	private boolean required;
	private String answerType;
	private String lastUpdUser;
	private Date lastUpdDate;
	private List<CpListBoxAnswers> listBoxAnswers;
	private boolean listBox = false;
	
	public Long getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public String getQuestionNameEng() {
		return questionNameEng;
	}
	public void setQuestionNameEng(String questionNameEng) {
		this.questionNameEng = questionNameEng;
	}
	public String getQuestionNameArb() {
		return questionNameArb;
	}
	public void setQuestionNameArb(String questionNameArb) {
		this.questionNameArb = questionNameArb;
	}
	public boolean isMandatory() {
		return mandatory;
	}
	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public String getAnswerType() {
		return answerType;
	}
	public void setAnswerType(String answerType) {
		this.answerType = answerType;
	}
	public String getLastUpdUser() {
		return lastUpdUser;
	}
	public void setLastUpdUser(String lastUpdUser) {
		this.lastUpdUser = lastUpdUser;
	}
	public Date getLastUpdDate() {
		return lastUpdDate;
	}
	public void setLastUpdDate(Date lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
	}
	
	public List<CpListBoxAnswers> getListBoxAnswers() {
		return listBoxAnswers;
	}
	public void setListBoxAnswers(List<CpListBoxAnswers> listBoxAnswers) {
		this.listBoxAnswers = listBoxAnswers;
	}

	public boolean isListBox() {
		return listBox;
	}
	public void setListBox(boolean listBox) {
		this.listBox = listBox;
	}
	
	@Override
	public String toString() {
		return "CpQuestionnaireDTO [serviceId=" + serviceId + ", serviceName=" + serviceName + ", domain=" + domain
				+ ", questionId=" + questionId + ", questionNameEng=" + questionNameEng + ", questionNameArb="
				+ questionNameArb + ", mandatory=" + mandatory + ", required=" + required + ", answerType=" + answerType
				+ ", lastUpdUser=" + lastUpdUser + ", lastUpdDate=" + lastUpdDate + ", listBoxAnswers=" + listBoxAnswers
				+ "]";
	}

}
