package com.aetins.customerportal.web.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "CP_QUESTION_DETAIL")
public class CpQuestionDetail implements Serializable{
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7471528422560549563L;
	private int nQuesId;
	private String vQuesEN;	
	private String vQuesAR;	
	private String vLastupduser;
	private Date vLastUpdDate;
	
	@Id	
	@GeneratedValue
	@Column(name = "N_QUESTION_ID")
	public int getnQuesId() {
		return nQuesId;
	}
	
	@Column(name = "V_QUESTION_EN")
	public String getvQuesEN() {
		return vQuesEN;
	}
	
	
	
	@Column(name = "V_QUESTION_AR")
	public String getvQuesAR() {
		return vQuesAR;
	}
	
	
	
	@Column(name = "V_LASTUPD_USER")
	public String getvLastupduser() {
		return vLastupduser;
	}
	
	@Column(name = "V_LASTUPD_DATE")
	public Date getvLastUpdDate() {
		return vLastUpdDate;
	}
	
	public void setnQuesId(int nQuesId) {
		this.nQuesId = nQuesId;
	}
	public void setvQuesEN(String vQuesEN) {
		this.vQuesEN = vQuesEN;
	}
	
	public void setvQuesAR(String vQuesAR) {
		this.vQuesAR = vQuesAR;
	}
	
	public void setvLastupduser(String vLastupduser) {
		this.vLastupduser = vLastupduser;
	}
	public void setvLastUpdDate(Date vLastUpdDate) {
		this.vLastUpdDate = vLastUpdDate;
	}

	public List<CpQuestionDetail> findAllQuestions() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
