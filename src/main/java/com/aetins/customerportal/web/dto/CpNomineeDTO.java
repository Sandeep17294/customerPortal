package com.aetins.customerportal.web.dto;


import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;


public class CpNomineeDTO {
	private String policyNo;
	private String country;
	private String address;
	private String addressopt;
	private String countryCode;
	private String city;
	private String email;
	private String idenDesc;
	private String beneficiaryName;
	private String mobileN;
	private String phone;
	private String phnOpt;
	private String poBox;
	private String posatlCode;
	private String stateCode;
	
	private String relDescription;
	
	private int siNo;
	private BigDecimal custrefNo;
	private String relCode;
	private Date dateOfBirth;
	private String arabicName;
	private String indenDesc;
	private String arbindenDesc;
	private String arbrelDesc;
	private BigDecimal benifitShare;
	
	private ServiceRequestMasterDTO serviceRequest;
	
	private String idenCode;
	private String idenNo;
	private String gender;
	private String street;
	private String mobileNo2;
	private String transactionType;

	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	
	public String getRelDescription() {
		return relDescription;
	}
	public void setRelDescription(String relDescription) {
		this.relDescription = relDescription;
	}
	public String getIdenCode() {
		return idenCode;
	}
	public void setIdenCode(String idenCode) {
		this.idenCode = idenCode;
	}
	public String getIdenNo() {
		return idenNo;
	}
	public void setIdenNo(String idenNo) {
		this.idenNo = idenNo;
	}
	public int getSiNo() {
		return siNo;
	}
	public void setSiNo(int siNo) {
		this.siNo = siNo;
	}
	
	public String getRelCode() {
		return relCode;
	}
	public void setRelCode(String relCode) {
		this.relCode = relCode;
	}

	public String getArabicName() {
		return arabicName;
	}
	public void setArabicName(String arabicName) {
		this.arabicName = arabicName;
	}
	public String getIndenDesc() {
		return indenDesc;
	}
	public void setIndenDesc(String indenDesc) {
		this.indenDesc = indenDesc;
	}
	public String getArbindenDesc() {
		return arbindenDesc;
	}
	public void setArbindenDesc(String arbindenDesc) {
		this.arbindenDesc = arbindenDesc;
	}
	public String getArbrelDesc() {
		return arbrelDesc;
	}
	public void setArbrelDesc(String arbrelDesc) {
		this.arbrelDesc = arbrelDesc;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIdenDesc() {
		return idenDesc;
	}
	public void setIdenDesc(String idenDesc) {
		this.idenDesc = idenDesc;
	}
	public String getMobileN() {
		return mobileN;
	}
	public void setMobileN(String mobileN) {
		this.mobileN = mobileN;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhnOpt() {
		return phnOpt;
	}
	public void setPhnOpt(String phnOpt) {
		this.phnOpt = phnOpt;
	}
	public String getPoBox() {
		return poBox;
	}
	public void setPoBox(String poBox) {
		this.poBox = poBox;
	}
	public String getPosatlCode() {
		return posatlCode;
	}
	public void setPosatlCode(String posatlCode) {
		this.posatlCode = posatlCode;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	public BigDecimal getCustrefNo() {
		return custrefNo;
	}
	public void setCustrefNo(BigDecimal custrefNo) {
		this.custrefNo = custrefNo;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public BigDecimal getBenifitShare() {
		return benifitShare;
	}
	public void setBenifitShare(BigDecimal benifitShare) {
		this.benifitShare = benifitShare;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddressopt() {
		return addressopt;
	}
	public void setAddressopt(String addressopt) {
		this.addressopt = addressopt;
	}
	public ServiceRequestMasterDTO getServiceRequest() {
		return serviceRequest;
	}
	public void setServiceRequest(ServiceRequestMasterDTO serviceRequest) {
		this.serviceRequest = serviceRequest;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMobileNo2() {
		return mobileNo2;
	}
	public void setMobileNo2(String mobileNo2) {
		this.mobileNo2 = mobileNo2;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getBeneficiaryName() {
		return beneficiaryName;
	}
	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}
	
	
	}