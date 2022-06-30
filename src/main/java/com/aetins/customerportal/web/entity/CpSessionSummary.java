package com.aetins.customerportal.web.entity;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "CP_SESSIONSUMMARY")
public class CpSessionSummary  implements java.io.Serializable{
	
	private static final long serialVersionUID = -8160952900135226001L;
	
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
	
	
	
	@Id
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator="gen")
	@Column(name = "N_SESSIONID", unique = true, nullable = false, precision = 15, scale = 0)
	public int getNsessionId() {
		return nsessionId;
	}
	
	@Column(name = "V_USER_NAME")
	public String getVuserName() {
		return vuserName;
	}
	
	@Column(name = "D_SESSIONSTART")
	public Date getDsessionStart() {
		return dsessionStart;
	}
	
	@Column(name = "D_SESSIONEND")
	public Date getDsessionEnd() {
		return dsessionEnd;
	}
	
	@Column(name = "V_LASTUPD_USER")
	public String getVlastUpdUser() {
		return vlastUpdUser;
	}
	
	@Column(name = "V_LASTUPD_PROG")
	public String getVlastUpdProg() {
		return vlastUpdProg;
	}
	
	@Column(name = "V_LASTUPD_INFTIM")
	public Date getVlastUpdInftim() {
		return vlastUpdInftim;
	}
	
	@Column(name = "V_LOGOFF")
	public String getvLogOff() {
		return vLogOff;
	}
	
	@Column(name = "N_ACTIVE")
	public int getnActive() {
		return nActive;
	}
	
	@Column(name = "D_LASTSESSIONTRANSACTION")
	public Date getdLastSessionTransaction() {
		return dLastSessionTransaction;
	}
	
	@Column(name = "V_PHPSESSIONID")
	public String getvPhpSessionId() {
		return vPhpSessionId;
	}
	
	@Column(name = "V_CLIENTIP")
	public String getVclientIp() {
		return vclientIp;
	}
	
	//setters
	
	public void setNsessionId(int nsessionId) {
		this.nsessionId = nsessionId;
	}
	
	
	public void setVuserName(String vuserName) {
		this.vuserName = vuserName;
	}
	
	
	public void setDsessionStart(Date dsessionStart) {
		this.dsessionStart = dsessionStart;
	}
	
	
	public void setDsessionEnd(Date dsessionEnd) {
		this.dsessionEnd = dsessionEnd;
	}
	
	
	public void setVlastUpdUser(String vlastUpdUser) {
		this.vlastUpdUser = vlastUpdUser;
	}
	
	
	public void setVlastUpdProg(String vlastUpdProg) {
		this.vlastUpdProg = vlastUpdProg;
	}
	
	
	public void setVlastUpdInftim(Date vlastUpdInftim) {
		this.vlastUpdInftim = vlastUpdInftim;
	}
	
	
	public void setvLogOff(String vLogOff) {
		this.vLogOff = vLogOff;
	}
	
	
	public void setnActive(int nActive) {
		this.nActive = nActive;
	}
	
	
	public void setdLastSessionTransaction(Date dLastSessionTransaction) {
		this.dLastSessionTransaction = dLastSessionTransaction;
	}
	
	
	public void setvPhpSessionId(String vPhpSessionId) {
		this.vPhpSessionId = vPhpSessionId;
	}
	
	
	public void setVclientIp(String vclientIp) {
		this.vclientIp = vclientIp;
	}

}
