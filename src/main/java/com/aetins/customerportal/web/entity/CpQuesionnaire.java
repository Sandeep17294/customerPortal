package com.aetins.customerportal.web.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="cp_questionnaire")
public class CpQuesionnaire implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="N_SERVICE_ID")
	private Long serviceId;
	
	@Column(name="V_SERVICE_NAME")
	private String serviceName;
	
	@Column(name="N_SUB_SERVICE_ID")
	private Long subServiceId;
	
	@Column(name="V_SUB_SERVICE_NAME")
	private String subServiceName;
	
	@Column(name="V_DOMAIN_NAME")
	private String domainName;
	
	@Column(name="N_QUESTION_ID")
	private Long questionId;
	
	@Column(name="V_QUESTION_NAME_EN")
	private String questionNameEng;
	
	@Column(name="V_QUESTION_NAME_AR")
	private String questionNameArb;
	
	@Column(name="V_ANSWER_NAME_EN")
	private String answerNameEng;
	
	@Column(name="V_ANSWER_NAME_AR")
	private String answerNameArb;	

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

	public Long getSubServiceId() {
		return subServiceId;
	}

	public void setSubServiceId(Long subServiceId) {
		this.subServiceId = subServiceId;
	}

	public String getSubServiceName() {
		return subServiceName;
	}

	public void setSubServiceName(String subServiceName) {
		this.subServiceName = subServiceName;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
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

	public String getAnswerNameEng() {
		return answerNameEng;
	}

	public void setAnswerNameEng(String answerNameEng) {
		this.answerNameEng = answerNameEng;
	}

	public String getAnswerNameArb() {
		return answerNameArb;
	}

	public void setAnswerNameArb(String answerNameArb) {
		this.answerNameArb = answerNameArb;
	}	

}
