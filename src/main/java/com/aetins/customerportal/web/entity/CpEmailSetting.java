package com.aetins.customerportal.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cp_email_setting")
public class CpEmailSetting {

	private int serialNo;
	private String screenName;
	private String ccEmailId;

	@Id
	@GeneratedValue
	@Column(name = "N_SERIAL_NO", unique = true, nullable = false, precision = 10, scale = 0)
	public int getSerialNo() {
		return serialNo;
	}
    
	@Column(name = "V_SCREEN_NAME")
	public String getScreenName() {
		return screenName;
	}


	@Column(name = "V_EMAIL")
	public String getCcEmailId() {
		return ccEmailId;
	}

	public void setCcEmailId(String ccEmailId) {
		this.ccEmailId = ccEmailId;
	}
	
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}


}
