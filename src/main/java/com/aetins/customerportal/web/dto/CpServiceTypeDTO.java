package com.aetins.customerportal.web.dto;

public class CpServiceTypeDTO {
    
	private int nSerialNo;
	private String vServiceTypeEn;
	private String vServiceTypeAr;
    
	private String screenName;
	private String ccEmail;
    
	public int getnSerialNo() {
		return nSerialNo;
	}

	public void setnSerialNo(int nSerialNo) {
		this.nSerialNo = nSerialNo;
	}

	public String getvServiceTypeEn() {
		return vServiceTypeEn;
	}

	public void setvServiceTypeEn(String vServiceTypeEn) {
		this.vServiceTypeEn = vServiceTypeEn;
	}

	public String getvServiceTypeAr() {
		return vServiceTypeAr;
	}

	public void setvServiceTypeAr(String vServiceTypeAr) {
		this.vServiceTypeAr = vServiceTypeAr;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getCcEmail() {
		return ccEmail;
	}

	public void setCcEmail(String ccEmail) {
		this.ccEmail = ccEmail;
	}

}
