package com.aetins.customerportal.web.pojo;

import java.io.Serializable;

public class SMSTriggerPojo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -841096804576468685L;

	
	
	private String mobileUserName;
	
	private String mobilePassword;
	
	private String mobileSenderId;
	
	private String mobileDlrEnable;
	
	private String mobileOmsgType;
	
	private String mobileMessage;
	
	private String mobileDestination;
	
	
	
	
	public SMSTriggerPojo(String mobileUserName, String mobilePassword, String mobileSenderId, String mobileDlrEnable,
			String mobileOmsgType, String mobileMessage, String mobileDestination) {
		super();
		this.mobileUserName = mobileUserName;
		this.mobilePassword = mobilePassword;
		this.mobileSenderId = mobileSenderId;
		this.mobileDlrEnable = mobileDlrEnable;
		this.mobileOmsgType = mobileOmsgType;
		this.mobileMessage = mobileMessage;
		this.mobileDestination = mobileDestination;
	}

	public String getMobileUserName() {
		return mobileUserName;
	}

	public void setMobileUserName(String mobileUserName) {
		this.mobileUserName = mobileUserName;
	}

	public String getMobilePassword() {
		return mobilePassword;
	}

	public void setMobilePassword(String mobilePassword) {
		this.mobilePassword = mobilePassword;
	}

	public String getMobileSenderId() {
		return mobileSenderId;
	}

	public void setMobileSenderId(String mobileSenderId) {
		this.mobileSenderId = mobileSenderId;
	}

	public String getMobileDlrEnable() {
		return mobileDlrEnable;
	}

	public void setMobileDlrEnable(String mobileDlrEnable) {
		this.mobileDlrEnable = mobileDlrEnable;
	}

	public String getMobileOmsgType() {
		return mobileOmsgType;
	}

	public void setMobileOmsgType(String mobileOmsgType) {
		this.mobileOmsgType = mobileOmsgType;
	}

	public String getMobileMessage() {
		return mobileMessage;
	}

	public void setMobileMessage(String mobileMessage) {
		this.mobileMessage = mobileMessage;
	}

	public String getMobileDestination() {
		return mobileDestination;
	}

	public void setMobileDestination(String mobileDestination) {
		this.mobileDestination = mobileDestination;
	}

	@Override
	public String toString() {
		return "SMSTrigger [mobileUserName=" + mobileUserName + ", mobilePassword=" + mobilePassword
				+ ", mobileSenderId=" + mobileSenderId + ", mobileDlrEnable=" + mobileDlrEnable + ", mobileOmsgType="
				+ mobileOmsgType + ", mobileMessage=" + mobileMessage + ", mobileDestination=" + mobileDestination
				+ "]";
	}
	
}
