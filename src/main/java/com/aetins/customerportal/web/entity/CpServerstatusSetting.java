package com.aetins.customerportal.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CP_SERVERSTATUS_SETTING")
public class CpServerstatusSetting implements java.io.Serializable{
	private static final long serialVersionUID = -8160952900135226001L;

	private int nid;
	private String vApplicationDownType;
	private Date dEffectiveFrom;
	private Date dEffectiveTo;
	private Date dStartTime;
	private Date dEndTime;
	
	private String downstatus;
	
	
	//getter
	@Id
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator="gen")
	@Column(name = "N_ID", unique = true, nullable = false, precision = 15, scale = 0)
	public int getNid() {
		return nid;
	}
	
	@Column(name = "V_APPTYPE")
	public String getvApplicationDownType() {
		return vApplicationDownType;
	}

	@Column(name = "D_STARTDATE")
	public Date getdEffectiveFrom() {
		return dEffectiveFrom;
	}	

	@Column(name = "D_ENDDATE")
	public Date getdEffectiveTo() {
		return dEffectiveTo;
	}	
	
	@Column(name = "T_STARTTIME", columnDefinition="TIME")
	@Temporal(TemporalType.TIME)
	public Date getdStartTime() {
		return dStartTime;
	}

	@Column(name = "T_ENDTIME", columnDefinition="TIME")
	@Temporal(TemporalType.TIME)
	public Date getdEndTime() {
		return dEndTime;
	}
	
	@Column(name = "DOWN_STATUS")
	public String getDownstatus() {
		return downstatus;
	}
	
	//setter
	public void setNid(int nid) {
		this.nid = nid;
	}	

	public void setvApplicationDownType(String vApplicationDownType) {
		this.vApplicationDownType = vApplicationDownType;
	}

	public void setdEffectiveFrom(Date dEffectiveFrom) {
		this.dEffectiveFrom = dEffectiveFrom;
	}

	public void setdEffectiveTo(Date dEffectiveTo) {
		this.dEffectiveTo = dEffectiveTo;
	}

	public void setdStartTime(Date dStartTime) {
		this.dStartTime = dStartTime;
	}	

	public void setdEndTime(Date dEndTime) {
		this.dEndTime = dEndTime;
	}


	public void setDownstatus(String downstatus) {
		this.downstatus = downstatus;
	}
	
	

}