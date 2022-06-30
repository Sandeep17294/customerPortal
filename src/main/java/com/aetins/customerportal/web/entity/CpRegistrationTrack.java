package com.aetins.customerportal.web.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * This Entity class for Registration track information
 * @author Viswakarthick
 * 21/06/2017
 */
@Entity
@Table(name="cp_registration_track")
public class CpRegistrationTrack implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4832868607403932625L;
	private int nId;
	private String vIpAddr;
	private String vPlanNo;
	private String vEmailId;
	private String vPhoneNo;
	private String vStatus;
	private String vFailReason;
	private Date dRegdate;
	private String vLastupdUser;
	private String vLastupdProg;
	private Date vLastupdDate;
	private String userId;
	
	
	
	//getter
	@Id
	@GeneratedValue
	@Column(name="n_id")
	public int getnId() {
		return nId;
	}
	
	@Column(name="v_ipAddr")
	public String getvIpAddr() {
		return vIpAddr;
	}
	
	@Column(name="v_plan_no")
	public String getvPlanNo() {
		return vPlanNo;
	}
	
	@Column(name="v_email_id")
	public String getvEmailId() {
		return vEmailId;
	}
	
	@Column(name="v_phone_no")
	public String getvPhoneNo() {
		return vPhoneNo;
	}
	
	@Column(name="v_status")
	public String getvStatus() {
		return vStatus;
	}
	
	@Column(name="v_fail_reason")
	public String getvFailReason() {
		return vFailReason;
	}
	
	@Column(name="d_reg_date")
	public Date getdRegdate() {
		return dRegdate;
	}
	
	@Column(name="v_lastupd_user")
	public String getvLastupdUser() {
		return vLastupdUser;
	}
	
	@Column(name="v_lastupd_prog")
	public String getvLastupdProg() {
		return vLastupdProg;
	}
	
	@Column(name="v_lastupd_date")
	public Date getvLastupdDate() {
		return vLastupdDate;
	}
	
	@Column(name="v_user_id")
	public String getUserId() {
		return userId;
	}
	
	
	//setter
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public void setnId(int nId) {
		this.nId = nId;
	}

	public void setvIpAddr(String vIpAddr) {
		this.vIpAddr = vIpAddr;
	}

	public void setvPlanNo(String vPlanNo) {
		this.vPlanNo = vPlanNo;
	}

	public void setvEmailId(String vEmailId) {
		this.vEmailId = vEmailId;
	}

	public void setvPhoneNo(String vPhoneNo) {
		this.vPhoneNo = vPhoneNo;
	}

	public void setvStatus(String vStatus) {
		this.vStatus = vStatus;
	}

	public void setvFailReason(String vFailReason) {
		this.vFailReason = vFailReason;
	}

	public void setdRegdate(Date dRegdate) {
		this.dRegdate = dRegdate;
	}

	public void setvLastupdUser(String vLastupdUser) {
		this.vLastupdUser = vLastupdUser;
	}

	public void setvLastupdProg(String vLastupdProg) {
		this.vLastupdProg = vLastupdProg;
	}

	public void setvLastupdDate(Date vLastupdDate) {
		this.vLastupdDate = vLastupdDate;
	}
	
	
	
	
	
}
