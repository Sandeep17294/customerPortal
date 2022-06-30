package com.aetins.customerportal.web.dto;

public class FatcaDTO {

	private int seqNo;
	private ServiceRequestMasterDTO serviceRequestNo;
	private String serviceName;
	private String isUsPerson;
	private String tinNo;
	private String taxMoreThanOne;
	private String country;
	private String tinOrReason;
	private String residentUAE;

	public int getSeqNo() {
		return seqNo;
	}

	public String getServiceName() {
		return serviceName;
	}

	public String getIsUsPerson() {
		return isUsPerson;
	}

	public String getTinNo() {
		return tinNo;
	}

	public String getTaxMoreThanOne() {
		return taxMoreThanOne;
	}

	public String getCountry() {
		return country;
	}

	public String getTinOrReason() {
		return tinOrReason;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public void setIsUsPerson(String isUsPerson) {
		this.isUsPerson = isUsPerson;
	}

	public void setTinNo(String tinNo) {
		this.tinNo = tinNo;
	}

	public void setTaxMoreThanOne(String taxMoreThanOne) {
		this.taxMoreThanOne = taxMoreThanOne;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setTinOrReason(String tinOrReason) {
		this.tinOrReason = tinOrReason;
	}

	public ServiceRequestMasterDTO getServiceRequestNo() {
		return serviceRequestNo;
	}

	public void setServiceRequestNo(ServiceRequestMasterDTO serviceRequestNo) {
		this.serviceRequestNo = serviceRequestNo;
	}

	public String getResidentUAE() {
		return residentUAE;
	}

	public void setResidentUAE(String residentUAE) {
		this.residentUAE = residentUAE;
	}

}