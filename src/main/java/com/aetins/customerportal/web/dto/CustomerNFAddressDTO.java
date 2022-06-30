package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class CustomerNFAddressDTO {
	private BigDecimal custRefNo;
	private String addressCode;
	private String addresCodeDesc;
	private BigDecimal addressSeqNo;
	private String langCode;
	private String address1;
	private String address2;
	private String address3;
	private String postalCode;
	private String town;
	private String townAR;
	private String stateCode;
	private String stateENG;
	private String stateAR;
	private String countryCode;
	private String countryENG;
	private String countryAR;
	private String corresAddress;
	private BigDecimal unitNo;
	private BigDecimal additionalNo;
	private BigDecimal buildingNo;	
	private String poBoxNo;
	private List<CustomerNFAddressContactDTO> addressContactList=new ArrayList<CustomerNFAddressContactDTO>();
	//render allvalue
	private boolean enableAllValue;
	private boolean validAddressState;

	
	//added by malik
	private String buildingno;
	private String unitno;
	private String additionalno;
	private String citys;
	//ended
	
	
	
	
	//getter and setter
	public BigDecimal getCustRefNo() {
		return custRefNo;
	}
	public String getCitys() {
		return citys;
	}
	public void setCitys(String citys) {
		this.citys = citys;
	}
	public String getBuildingno() {
		return buildingno;
	}
	public void setBuildingno(String buildingno) {
		this.buildingno = buildingno;
	}
	public String getUnitno() {
		return unitno;
	}
	public void setUnitno(String unitno) {
		this.unitno = unitno;
	}
	public String getAdditionalno() {
		return additionalno;
	}
	public void setAdditionalno(String additionalno) {
		this.additionalno = additionalno;
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
	public String getAddresCodeDesc() {
		return addresCodeDesc;
	}
	public void setAddresCodeDesc(String addresCodeDesc) {
		this.addresCodeDesc = addresCodeDesc;
	}
	public BigDecimal getAddressSeqNo() {
		return addressSeqNo;
	}
	public void setAddressSeqNo(BigDecimal addressSeqNo) {
		this.addressSeqNo = addressSeqNo;
	}
	public String getLangCode() {
		return langCode;
	}
	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getTownAR() {
		return townAR;
	}
	public void setTownAR(String townAR) {
		this.townAR = townAR;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getStateENG() {
		return stateENG;
	}
	public void setStateENG(String stateENG) {
		this.stateENG = stateENG;
	}
	public String getStateAR() {
		return stateAR;
	}
	public void setStateAR(String stateAR) {
		this.stateAR = stateAR;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountryENG() {
		return countryENG;
	}
	public void setCountryENG(String countryENG) {
		this.countryENG = countryENG;
	}
	public String getCountryAR() {
		return countryAR;
	}
	public void setCountryAR(String countryAR) {
		this.countryAR = countryAR;
	}
	public String getCorresAddress() {
		return corresAddress;
	}
	public void setCorresAddress(String corresAddress) {
		this.corresAddress = corresAddress;
	}
	public List<CustomerNFAddressContactDTO> getAddressContactList() {
		return addressContactList;
	}
	public void setAddressContactList(List<CustomerNFAddressContactDTO> addressContactList) {
		this.addressContactList = addressContactList;
	}
	public BigDecimal getUnitNo() {
		return unitNo;
	}
	public void setUnitNo(BigDecimal unitNo) {
		this.unitNo = unitNo;
	}
	public BigDecimal getAdditionalNo() {
		return additionalNo;
	}
	public void setAdditionalNo(BigDecimal additionalNo) {
		this.additionalNo = additionalNo;
	}
	public String getPoBoxNo() {
		return poBoxNo;
	}
	public void setPoBoxNo(String poBoxNo) {
		this.poBoxNo = poBoxNo;
	}	
	public BigDecimal getBuildingNo() {
		return buildingNo;
	}
	public void setBuildingNo(BigDecimal buildingNo) {
		this.buildingNo = buildingNo;
	}
	public boolean isEnableAllValue() {
		return enableAllValue;
	}
	public void setEnableAllValue(boolean enableAllValue) {
		this.enableAllValue = enableAllValue;
	}
	

	
	public boolean isValidAddressState() {
		return validAddressState;
	}
	public void setValidAddressState(boolean validAddressState) {
		this.validAddressState = validAddressState;
	}
	//constructor
	public CustomerNFAddressDTO(CustomerNFAddressDTO another) {
		
		this.custRefNo = another.custRefNo;
		this.addressCode = another.addressCode;
		this.addresCodeDesc = another.addresCodeDesc;
		this.addressSeqNo = another.addressSeqNo;
		this.langCode = another.langCode;
		this.address1 = another.address1;
		this.address2 = another.address2;
		this.address3 = another.address3;
		this.postalCode = another.postalCode;
		this.town = another.town;
		this.townAR = another.townAR;
		this.stateCode = another.stateCode;
		this.stateENG = another.stateENG;
		this.stateAR = another.stateAR;
		this.countryCode = another.countryCode;
		this.countryENG = another.countryENG;
		this.countryAR = another.countryAR;
		this.corresAddress = another.corresAddress;
		this.unitNo = another.unitNo;
		this.additionalNo = another.additionalNo;
		this.buildingNo = another.buildingNo;
		this.poBoxNo = another.poBoxNo;
		
		for (CustomerNFAddressContactDTO CustomerNFAddressContactDTO : another.addressContactList) {
			CustomerNFAddressContactDTO custNFAddressContDTO = new CustomerNFAddressContactDTO(CustomerNFAddressContactDTO);
			this.addressContactList.add(custNFAddressContDTO);
		}		
		this.enableAllValue = another.enableAllValue;
		this.validAddressState =  another.validAddressState;
	}
	
	public CustomerNFAddressDTO(){
		
	}
	
}
