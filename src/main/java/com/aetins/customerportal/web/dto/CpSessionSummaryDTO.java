package com.aetins.customerportal.web.dto;

import java.util.Date;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "cpSessionSummaryDTO", eager = true)
public class CpSessionSummaryDTO {
	
	private int nsessionId;
	private String vuserName;
	private Date dsessionStart;
	private Date dsessionEnd;
	private String vlastUpdUser;
	private String vlastUpdProg;
	private Date vlastUpdInftim;
	private String vLogOff;
	private int nActive;
	private Date dLastSessionTransaction;
	private String vPhpSessionId;
	private String vclientIp;
	
	//setters and getters
	
	public int getNsessionId() {
		return nsessionId;
	}
	public void setNsessionId(int nsessionId) {
		this.nsessionId = nsessionId;
	}
	public String getVuserName() {
		return vuserName;
	}
	public void setVuserName(String vuserName) {
		this.vuserName = vuserName;
	}
	public Date getDsessionStart() {
		return dsessionStart;
	}
	public void setDsessionStart(Date dsessionStart) {
		this.dsessionStart = dsessionStart;
	}
	public Date getDsessionEnd() {
		return dsessionEnd;
	}
	public void setDsessionEnd(Date dsessionEnd) {
		this.dsessionEnd = dsessionEnd;
	}
	public String getVlastUpdUser() {
		return vlastUpdUser;
	}
	public void setVlastUpdUser(String vlastUpdUser) {
		this.vlastUpdUser = vlastUpdUser;
	}
	public String getVlastUpdProg() {
		return vlastUpdProg;
	}
	public void setVlastUpdProg(String vlastUpdProg) {
		this.vlastUpdProg = vlastUpdProg;
	}
	public Date getVlastUpdInftim() {
		return vlastUpdInftim;
	}
	public void setVlastUpdInftim(Date vlastUpdInftim) {
		this.vlastUpdInftim = vlastUpdInftim;
	}
	public String getvLogOff() {
		return vLogOff;
	}
	public void setvLogOff(String vLogOff) {
		this.vLogOff = vLogOff;
	}
	public int getnActive() {
		return nActive;
	}
	public void setnActive(int nActive) {
		this.nActive = nActive;
	}
	public Date getdLastSessionTransaction() {
		return dLastSessionTransaction;
	}
	public void setdLastSessionTransaction(Date dLastSessionTransaction) {
		this.dLastSessionTransaction = dLastSessionTransaction;
	}
	public String getvPhpSessionId() {
		return vPhpSessionId;
	}
	public void setvPhpSessionId(String vPhpSessionId) {
		this.vPhpSessionId = vPhpSessionId;
	}
	public String getVclientIp() {
		return vclientIp;
	}
	public void setVclientIp(String vclientIp) {
		this.vclientIp = vclientIp;
	}


}
