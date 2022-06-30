package com.aetins.customerportal.web.dto;

public class FatcaAddCounDTO {

	private int serial;
	private FatcaDTO seqNo;
	private String country;
	private String tinOrReason;
	private boolean newRecord;
	private String countryName;

	public String getCountry() {
		return country;
	}

	public String getTinOrReason() {
		return tinOrReason;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setTinOrReason(String tinOrReason) {
		this.tinOrReason = tinOrReason;
	}

	public FatcaDTO getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(FatcaDTO seqNo) {
		this.seqNo = seqNo;
	}

	public boolean isNewRecord() {
		return newRecord;
	}

	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

}
