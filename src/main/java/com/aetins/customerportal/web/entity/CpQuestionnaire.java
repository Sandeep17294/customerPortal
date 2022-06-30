package com.aetins.customerportal.web.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="CP_QUESTIONNAIRE")
public class CpQuestionnaire implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="N_SERVICE_ID")
	private Long serviceId;
	
	@Column(name="V_SERVICE_NAME")
	private String serviceName;
	
	
	@Column(name="V_DOMAIN_NAME")
	private String domainName;
	
	
	@Column(name="N_QUESTION_ID")
	private Long questionId;
	
	@Column(name="V_QUESTION_NAME_EN")
	private String questionNameEng;
	
	@Column(name="V_QUESTION_NAME_AR")
	private String questionNameArb;
	
	@Column(name="V_ANSWER")
	private String answer;
	
	@Column(name="V_MANADATORY")
	private String mandatory;
	
	@Column(name="V_REQUIRED")
	private String required;
	
	@Column(name="V_ANSWER_TYPE")
	private String answerType;
	
	@Column(name="V_LASTUPD_USER")
	private String lastUpdUser;
	
	@Column(name="D_LASTUPD_DATE")
	private Date lastUpdDate;
	
	
	
	
	
	
	
	/*@OneToMany(mappedBy = "questionnaire",fetch = FetchType.LAZY,  targetEntity=CpListBoxAnswers.class ,cascade = CascadeType.ALL)
	private Set<CpListBoxAnswers> listBoxAnswers;
*/
	@Transient
	private List<CpListBoxAnswers> listBoxAnswers;
	
	@Transient
	private Date date;
	
	
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

	public String getMandatory() {
		return mandatory;
	}

	public void setMandatory(String mandatory) {
		this.mandatory = mandatory;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
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

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
