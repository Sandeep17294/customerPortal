package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Registration customer DTO, that carry carry data to business layer
 * 
 * @author satendra
 *
 */
public class RegistrationCustomerDTO {

    private BigDecimal customerReferenceNumber;

    private String languageCode;

    private String idenCode;
    private String idenNo;
    private String emailId;
    private String mobileNo;

    private String emailID;
    private String englishDOB;
    private String firstName;
    private String hijriDOB;
    private String lastName;
    private String mobileNumber;

    private String userId;
    private String userTitle;
    private String userDisplayName;
    // private String fileUpload; reserved variable for to upload file
    private String userPassword;
    private String userConfirmPassword;
    private String userSecretQuestion1;
    private String userSecretQuestion1answer;
    private String userSecretQuestion2;
    private String userSecretQuestion2answer;
    private Boolean userAgreedTermCondition;

    private String group;
    private String passwordMustChange;
    private String active;
    private String locked;
    private Date userActivationDate;
    private String activeLogin;
    private String loginSessionId;
    private String lastUpdateUser;
    private Date lastUpdateDate;
    private int imageID;
    private String policyStatus;
    
    private String token;

    private String appUrl;
    
    private String title;
    private String gender;
    
    public String getAppUrl()
    {
        return appUrl;
    }

    public void setAppUrl(String appUrl)
    {
        this.appUrl = appUrl;
    }

    public String getActive() {
	return active;
    }

    public void setActive(String active) {
	this.active = active;
    }

    public String getPasswordMustChange() {
	return passwordMustChange;
    }

    public void setPasswordMustChange(String passwordMustChange) {
	this.passwordMustChange = passwordMustChange;
    }

    public String getUserId() {
	return userId;
    }

    public void setUserId(String userId) {
	this.userId = userId;
    }

    public String getUserTitle() {
	return userTitle;
    }

    public void setUserTitle(String userTitle) {
	this.userTitle = userTitle;
    }

    public String getUserDisplayName() {
	return userDisplayName;
    }

    public void setUserDisplayName(String userDisplayName) {
	this.userDisplayName = userDisplayName;
    }

    public String getUserPassword() {
	return userPassword;
    }

    public void setUserPassword(String userPassword) {
	this.userPassword = userPassword;
    }

    public String getUserConfirmPassword() {
	return userConfirmPassword;
    }

    public void setUserConfirmPassword(String userConfirmPassword) {
	this.userConfirmPassword = userConfirmPassword;
    }

    public String getUserSecretQuestion1() {
	return userSecretQuestion1;
    }

    public void setUserSecretQuestion1(String userSecretQuestion1) {
	this.userSecretQuestion1 = userSecretQuestion1;
    }

    public String getUserSecretQuestion1answer() {
	return userSecretQuestion1answer;
    }

    public void setUserSecretQuestion1answer(String userSecretQuestion1answer) {
	this.userSecretQuestion1answer = userSecretQuestion1answer;
    }

    public String getUserSecretQuestion2() {
	return userSecretQuestion2;
    }

    public void setUserSecretQuestion2(String userSecretQuestion2) {
	this.userSecretQuestion2 = userSecretQuestion2;
    }

    public String getUserSecretQuestion2answer() {
	return userSecretQuestion2answer;
    }

    public void setUserSecretQuestion2answer(String userSecretQuestion2answer) {
	this.userSecretQuestion2answer = userSecretQuestion2answer;
    }

    public Boolean getUserAgreedTermCondition() {
	return userAgreedTermCondition;
    }

    public void setUserAgreedTermCondition(Boolean userAgreedTermCondition) {
	this.userAgreedTermCondition = userAgreedTermCondition;
    }

    public BigDecimal getCustomerReferenceNumber() {
	return customerReferenceNumber;
    }

    public void setCustomerReferenceNumber(BigDecimal customerReferenceNumber) {
	this.customerReferenceNumber = customerReferenceNumber;
    }

    public String getEmailID() {
	return emailID;
    }

    public void setEmailID(String emailID) {
	this.emailID = emailID;
    }

    public String getEnglishDOB() {
	return englishDOB;
    }

    public void setEnglishDOB(String englishDOB) {
	this.englishDOB = englishDOB;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getHijriDOB() {
	return hijriDOB;
    }

    public void setHijriDOB(String hijriDOB) {
	this.hijriDOB = hijriDOB;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getMobileNumber() {
	return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
	this.mobileNumber = mobileNumber;
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

    public String getEmailId() {
	return emailId;
    }

    public void setEmailId(String emailId) {
	this.emailId = emailId;
    }

    public String getMobileNo() {
	return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
	this.mobileNo = mobileNo;
    }

    public String getLanguageCode() {
	return languageCode;
    }

    public void setLanguageCode(String languageCode) {
	this.languageCode = languageCode;
    }

    public void setLocked(String locked) {
	this.locked = locked;
    }

    public String getGroup() {
	return group;
    }

    public void setGroup(String group) {
	this.group = group;
    }

    public Date getUserActivationDate() {
	return userActivationDate;
    }

    public void setUserActivationDate(Date userActivationDate) {
	this.userActivationDate = userActivationDate;
    }

    public String getActiveLogin() {
	return activeLogin;
    }

    public void setActiveLogin(String activeLogin) {
	this.activeLogin = activeLogin;
    }

    public String getLoginSessionId() {
	return loginSessionId;
    }

    public void setLoginSessionId(String loginSessionId) {
	this.loginSessionId = loginSessionId;
    }

    public String getLastUpdateUser() {
	return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
	this.lastUpdateUser = lastUpdateUser;
    }

    public Date getLastUpdateDate() {
	return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
	this.lastUpdateDate = lastUpdateDate;
    }

    public int getImageID() {
	return imageID;
    }

    public void setImageID(int imageID) {
	this.imageID = imageID;
    }

    public String getLocked() {
	return locked;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPolicyStatus() {
		return policyStatus;
	}

	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}
	
}
