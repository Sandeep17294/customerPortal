package com.aetins.customerportal.web.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CP_OTP_SETTINGS")
public class CpOtpSettings implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5048898714851939760L;

	private int sequenceNo;
	private String vServiceType;
	private String vOtpFlagMobile;
	private String vOtpFlagEmail;

	@Id
	@GeneratedValue
	@Column(name = "N_SEQUENCE_NO")
	public int getSequenceNo() {
		return sequenceNo;
	}

	@Column(name = "V_SERVICE_TYPE")
	public String getvServiceType() {
		return vServiceType;
	}

	@Column(name = "V_OTPFLAG_MOBILE")
	public String getvOtpFlagMobile() {
		return vOtpFlagMobile;
	}

	@Column(name = "V_OTPFLAG_EMAIL")
	public String getvOtpFlagEmail() {
		return vOtpFlagEmail;
	}

	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public void setvServiceType(String vServiceType) {
		this.vServiceType = vServiceType;
	}

	public void setvOtpFlagMobile(String vOtpFlagMobile) {
		this.vOtpFlagMobile = vOtpFlagMobile;
	}

	public void setvOtpFlagEmail(String vOtpFlagEmail) {
		this.vOtpFlagEmail = vOtpFlagEmail;
	}

}
