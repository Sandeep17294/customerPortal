package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CustomerInfoNonFinDTO {
	private BigDecimal custRefNo;
	private String titleCode;
	private String givenName;
	private String firstName;
	private String lastName;
	private String middleName;
	private String langCode;
	private String langENG;
	private String langAR;
	private String raceCode;
	private String raceENG;
	private String raceAR;
	private String religionCode;
	private String religionENG;
	private String religionAR;
	private String nationalityCode;
	private String nationalityENG;
	private String nationalityAR;
	private String maritalStatus;
	private String maritalENG;
	private String maritalAR;
	private String birthPlace;
	private String birthCountry;
	private String brCountryENG;
	private String brCountryAR;
	private String propIdentityCode;
	private String propIdentityDesc;
	private String propIdentityNo;
	private String policyNo;
	private Date idenExpiryDate;
	private List<CustomerNFContactDTO> customerContactList=new ArrayList<CustomerNFContactDTO>();
	private List<CustomerNFAddressDTO> customerAddressList=new ArrayList<CustomerNFAddressDTO>();
	
	
	
	
	// Modified on 0305
	private String address1;
	private String address2;
	private String address3;
	private String countryENG;
	private String postalCode;
	private String town;
	private String contactNumber;
	private String email;
	private String userDialCode;
	private String corresAddress;
	private String countryCode;
	private String stateCode;
	private String addressCode;
	private String addresCodeDesc;
	private BigDecimal addressSeqNo;
	
	//added by malik
	private BigDecimal buildingno;
	private BigDecimal unitno;
	private BigDecimal additionalno;
	
	private String poboxn;
	//ended
	
	
	
	
	
	
	
	public String getPoboxn() {
		return poboxn;
	}
	public void setPoboxn(String poboxn) {
		this.poboxn = poboxn;
	}
	
	
	
	public BigDecimal getBuildingno() {
		return buildingno;
	}
	public void setBuildingno(BigDecimal buildingno) {
		this.buildingno = buildingno;
	}
	public BigDecimal getUnitno() {
		return unitno;
	}
	public void setUnitno(BigDecimal unitno) {
		this.unitno = unitno;
	}
	public BigDecimal getAdditionalno() {
		return additionalno;
	}
	public void setAdditionalno(BigDecimal additionalno) {
		this.additionalno = additionalno;
	}
	//getter and setter
	public BigDecimal getCustRefNo() {
		return custRefNo;
	}
	public void setCustRefNo(BigDecimal custRefNo) {
		this.custRefNo = custRefNo;
	}
	public String getTitleCode() {
		return titleCode;
	}
	public void setTitleCode(String titleCode) {
		this.titleCode = titleCode;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLangCode() {
		return langCode;
	}
	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}
	public String getLangENG() {
		return langENG;
	}
	public void setLangENG(String langENG) {
		this.langENG = langENG;
	}
	public String getLangAR() {
		return langAR;
	}
	public void setLangAR(String langAR) {
		this.langAR = langAR;
	}
	public String getRaceCode() {
		return raceCode;
	}
	public void setRaceCode(String raceCode) {
		this.raceCode = raceCode;
	}
	public String getRaceENG() {
		return raceENG;
	}
	public void setRaceENG(String raceENG) {
		this.raceENG = raceENG;
	}
	public String getRaceAR() {
		return raceAR;
	}
	public void setRaceAR(String raceAR) {
		this.raceAR = raceAR;
	}
	public String getReligionCode() {
		return religionCode;
	}
	public void setReligionCode(String religionCode) {
		this.religionCode = religionCode;
	}
	public String getReligionENG() {
		return religionENG;
	}
	public void setReligionENG(String religionENG) {
		this.religionENG = religionENG;
	}
	public String getReligionAR() {
		return religionAR;
	}
	public void setReligionAR(String religionAR) {
		this.religionAR = religionAR;
	}
	public String getNationalityCode() {
		return nationalityCode;
	}
	public void setNationalityCode(String nationalityCode) {
		this.nationalityCode = nationalityCode;
	}
	public String getNationalityENG() {
		return nationalityENG;
	}
	public void setNationalityENG(String nationalityENG) {
		this.nationalityENG = nationalityENG;
	}
	public String getNationalityAR() {
		return nationalityAR;
	}
	public void setNationalityAR(String nationalityAR) {
		this.nationalityAR = nationalityAR;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getMaritalENG() {
		return maritalENG;
	}
	public void setMaritalENG(String maritalENG) {
		this.maritalENG = maritalENG;
	}
	public String getMaritalAR() {
		return maritalAR;
	}
	public void setMaritalAR(String maritalAR) {
		this.maritalAR = maritalAR;
	}
	public String getBirthPlace() {
		return birthPlace;
	}
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}
	public String getBirthCountry() {
		return birthCountry;
	}
	public void setBirthCountry(String birthCountry) {
		this.birthCountry = birthCountry;
	}
	public String getBrCountryENG() {
		return brCountryENG;
	}
	public void setBrCountryENG(String brCountryENG) {
		this.brCountryENG = brCountryENG;
	}
	public String getBrCountryAR() {
		return brCountryAR;
	}
	public void setBrCountryAR(String brCountryAR) {
		this.brCountryAR = brCountryAR;
	}
	public List<CustomerNFContactDTO> getCustomerContactList() {
		return customerContactList;
	}
	public void setCustomerContactList(List<CustomerNFContactDTO> customerContactList) {
		this.customerContactList = customerContactList;
	}
	public List<CustomerNFAddressDTO> getCustomerAddressList() {
		return customerAddressList;
	}
	public void setCustomerAddressList(List<CustomerNFAddressDTO> customerAddressList) {
		this.customerAddressList = customerAddressList;
	}
	public String getPropIdentityCode() {
		return propIdentityCode;
	}
	public void setPropIdentityCode(String propIdentityCode) {
		this.propIdentityCode = propIdentityCode;
	}
	public String getPropIdentityNo() {
		return propIdentityNo;
	}
	public void setPropIdentityNo(String propIdentityNo) {
		this.propIdentityNo = propIdentityNo;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
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
	public Date getIdenExpiryDate() {
		return idenExpiryDate;
	}
	public void setIdenExpiryDate(Date idenExpiryDate) {
		this.idenExpiryDate = idenExpiryDate;
	}
	public String getPropIdentityDesc() {
		return propIdentityDesc;
	}
	public void setPropIdentityDesc(String propIdentityDesc) {
		this.propIdentityDesc = propIdentityDesc;
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
	public String getCountryENG() {
		return countryENG;
	}
	public void setCountryENG(String countryENG) {
		this.countryENG = countryENG;
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
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

	public String getCorresAddress() {
		return corresAddress;
	}
	public void setCorresAddress(String corresAddress) {
		this.corresAddress = corresAddress;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getUserDialCode() {
		return userDialCode;
	}
	public void setUserDialCode(String userDialCode) {
		this.userDialCode = userDialCode;
	}
	//constructor
	public CustomerInfoNonFinDTO(CustomerInfoNonFinDTO another) {
		
		this.custRefNo = another.custRefNo;
		this.titleCode = another.titleCode;
		this.givenName = another.givenName;
		this.firstName = another.firstName;
		this.lastName = another.lastName;
		this.middleName = another.middleName;
		this.langCode = another.langCode;
		this.langENG = another.langENG;
		this.langAR = another.langAR;
		this.raceCode = another.raceCode;
		this.raceENG = another.raceENG;
		this.raceAR = another.raceAR;
		this.religionCode = another.religionCode;
		this.religionENG = another.religionENG;
		this.religionAR = another.religionAR;
		this.nationalityCode = another.nationalityCode;
		this.nationalityENG = another.nationalityENG;
		this.nationalityAR = another.nationalityAR;
		this.maritalStatus = another.maritalStatus;
		this.maritalENG = another.maritalENG;
		this.maritalAR = another.maritalAR;
		this.birthPlace = another.birthPlace;
		this.birthCountry = another.birthCountry;
		this.brCountryENG = another.brCountryENG;
		this.brCountryAR = another.brCountryAR;
		this.propIdentityCode = another.propIdentityCode;
		this.propIdentityNo = another.propIdentityNo;
		this.propIdentityDesc=another.propIdentityDesc;
		this.policyNo = another.policyNo;
		this.address1 = another.address1;
		this.address2 = another.address2;
		this.address3 = another.address3;
		this.town = another.town;
		this.postalCode = another.postalCode;
		this.contactNumber = another.contactNumber;
		this.email = another.email;
		this.userDialCode = another.userDialCode;
		this.corresAddress = another.corresAddress;
		this.countryCode = another.countryCode;
		this.stateCode = another.stateCode;
		this.addressCode = another.addressCode;
		this.addresCodeDesc = another.addresCodeDesc;
		this.addressSeqNo = another.addressSeqNo;
		
		
		for (CustomerNFContactDTO customerNFContactDTO : another.customerContactList) {
			CustomerNFContactDTO custNFContactDTO = new CustomerNFContactDTO(customerNFContactDTO);
			this.customerContactList.add(custNFContactDTO);
		}
		for (CustomerNFAddressDTO customerNFAddressDTO : another.customerAddressList) {
			CustomerNFAddressDTO custNFAddressDTO = new CustomerNFAddressDTO(customerNFAddressDTO);
			this.customerAddressList.add(custNFAddressDTO);
		}		
	}
	
	
	public CustomerInfoNonFinDTO(){		
	}
	
	
	
}
