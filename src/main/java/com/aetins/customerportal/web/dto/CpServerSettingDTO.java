package com.aetins.customerportal.web.dto;

import java.util.Date;

public class CpServerSettingDTO {

	private int nId;
	private String vApplicationDownType;
	private Date vEffectiveFrom;
	private Date vEffectTill;
	private Date dStartTime;
	private Date dEndTime;
	private String status;
	
	
	

	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getnId() {
		return nId;
	}

	public void setnId(int nId) {
		this.nId = nId;
	}

	public String getvApplicationDownType() {
		return vApplicationDownType;
	}

	public void setvApplicationDownType(String vApplicationDownType) {
		this.vApplicationDownType = vApplicationDownType;
	}

	public Date getvEffectiveFrom() {
		return vEffectiveFrom;
	}

	public void setvEffectiveFrom(Date vEffectiveFrom) {
		this.vEffectiveFrom = vEffectiveFrom;
	}

	public Date getvEffectTill() {
		return vEffectTill;
	}

	public void setvEffectTill(Date vEffectTill) {
		this.vEffectTill = vEffectTill;
	}

	public Date getdStartTime() {
		return dStartTime;
	}

	public void setdStartTime(Date dStartTime) {
		this.dStartTime = dStartTime;
	}

	public Date getdEndTime() {
		return dEndTime;
	}

	public void setdEndTime(Date dEndTime) {
		this.dEndTime = dEndTime;
	}

}
