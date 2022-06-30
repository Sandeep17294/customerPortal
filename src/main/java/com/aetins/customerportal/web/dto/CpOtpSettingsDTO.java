package com.aetins.customerportal.web.dto;

public class CpOtpSettingsDTO {
	
	private int sequenceNo;
	private String vServiceType;
	private String vOtpFlagMobile;
	private String vOtpFlagEmail;
	private String otpType;
	
	public int getSequenceNo() {
		return sequenceNo;
	}
	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
	public String getvServiceType() {
		return vServiceType;
	}
	public void setvServiceType(String vServiceType) {
		this.vServiceType = vServiceType;
	}
	public String getvOtpFlagMobile() {
		return vOtpFlagMobile;
	}
	public void setvOtpFlagMobile(String vOtpFlagMobile) {
		this.vOtpFlagMobile = vOtpFlagMobile;
	}
	public String getvOtpFlagEmail() {
		return vOtpFlagEmail;
	}
	public void setvOtpFlagEmail(String vOtpFlagEmail) {
		this.vOtpFlagEmail = vOtpFlagEmail;
	}
	public String getOtpType() {
		return otpType;
	}
	public void setOtpType(String otpType) {
		this.otpType = otpType;
	}
	
	
	
	

}
