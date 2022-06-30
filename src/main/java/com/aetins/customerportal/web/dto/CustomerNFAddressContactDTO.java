package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;


public class CustomerNFAddressContactDTO {
	private BigDecimal custRefNo;
	private String addressCode;
	private BigDecimal addressSeqNo;
	private String contactCode;
	private String contactDesc;
	private String contactNumber;
	private String newContactNo;
	// render for new value
	private boolean renderNewValue;
	private String maskPattern;
	private String email;
	
	private String userDialCode;
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	//getter and setter
	public BigDecimal getCustRefNo() {
		return custRefNo;
	}
	public void setCustRefNo(BigDecimal custRefNo) {
		this.custRefNo = custRefNo;
	}
	public String getAddressCode() {
		return addressCode;
	}
	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}
	public BigDecimal getAddressSeqNo() {
		return addressSeqNo;
	}
	public void setAddressSeqNo(BigDecimal addressSeqNo) {
		this.addressSeqNo = addressSeqNo;
	}
	public String getContactCode() {
		return contactCode;
	}
	public void setContactCode(String contactCode) {
		this.contactCode = contactCode;
	}
	public String getContactDesc() {
		return contactDesc;
	}
	public void setContactDesc(String contactDesc) {
		this.contactDesc = contactDesc;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public boolean isRenderNewValue() {
		return renderNewValue;
	}
	public void setRenderNewValue(boolean renderNewValue) {
		this.renderNewValue = renderNewValue;
	}
	public String getNewContactNo() {
		return newContactNo;
	}
	public void setNewContactNo(String newContactNo) {
		this.newContactNo = newContactNo;
	}
	
	
	
	
	public String getUserDialCode() {
		return userDialCode;
	}
	public void setUserDialCode(String userDialCode) {
		this.userDialCode = userDialCode;
	}
	public String getMaskPattern() {
		return maskPattern;
	}
	public void setMaskPattern(String maskPattern) {
		this.maskPattern = maskPattern;
	}
	//constructor
	public CustomerNFAddressContactDTO(CustomerNFAddressContactDTO another) {
		
		this.custRefNo = another.custRefNo;
		this.addressCode = another.addressCode;
		this.addressSeqNo = another.addressSeqNo;
		this.contactCode = another.contactCode;
		this.contactDesc = another.contactDesc;
		this.contactNumber = another.contactNumber;
		this.newContactNo = another.newContactNo;
		this.renderNewValue = another.renderNewValue;
		this.maskPattern =  another.maskPattern;
		this.userDialCode = another.userDialCode;
	}
	
	public CustomerNFAddressContactDTO() {
		
	}
	
}
