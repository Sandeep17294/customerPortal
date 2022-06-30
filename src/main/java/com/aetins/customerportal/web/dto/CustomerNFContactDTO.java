package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;

import org.apache.log4j.Logger;



public class CustomerNFContactDTO {
	
	private static final Logger logger = Logger.getLogger(CustomerNFContactDTO.class);
	
	private BigDecimal custRefNo;
	private String contactCode;
	private String contactDesc;
	private String contactNumber;
	private String newContactNo;
	// render for new value
	private boolean renderNewValue;
	private String maskPattern;
	private String dialCode;
	
	//getter and setter
	public BigDecimal getCustRefNo() {
		return custRefNo;
	}
	public void setCustRefNo(BigDecimal custRefNo) {
		this.custRefNo = custRefNo;
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
	
	public String getMaskPattern() {
		return maskPattern;
	}
	public void setMaskPattern(String maskPattern) {
		this.maskPattern = maskPattern;
	}
	
	
	public String getDialCode() {
		return dialCode;
	}
	public void setDialCode(String dialCode) {
		this.dialCode = dialCode;
	}
	//constructor
	public CustomerNFContactDTO(CustomerNFContactDTO another) {		
		this.custRefNo = another.custRefNo;
		this.contactCode = another.contactCode;
		this.contactDesc = another.contactDesc;
		this.contactNumber = another.contactNumber;
		this.newContactNo = another.newContactNo;
		this.renderNewValue = another.renderNewValue;
		this.maskPattern =  another.maskPattern;
		this.dialCode = another.dialCode;
	}	

	public CustomerNFContactDTO(){
		
	}
	@Override
	public String toString() {
		return "CustomerNFContactDTO [custRefNo=" + custRefNo + ", contactCode=" + contactCode + ", contactDesc="
				+ contactDesc + ", contactNumber=" + contactNumber + ", newContactNo=" + newContactNo
				+ ", renderNewValue=" + renderNewValue + ", maskPattern=" + maskPattern + ", dialCode=" + dialCode
				+ "]";
	}
	
	
}
