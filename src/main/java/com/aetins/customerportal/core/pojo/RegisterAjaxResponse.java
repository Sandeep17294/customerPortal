package com.aetins.customerportal.core.pojo;

import java.io.Serializable;

/**
 * Handle ajax call response for user verification
 * @author avinash
 *
 */
public class RegisterAjaxResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6360812844040464267L;

	private String policyNo;
	
	private String mobileNo;
	
	private String email;
	
	public RegisterAjaxResponse(String policyNo,String mobileNo,String email) {
		this.policyNo = policyNo;
		this.mobileNo = mobileNo;
		this.email = email;
	}
	
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "RegisterAjaxResponse [policyNo=" + policyNo + ", mobileNo=" + mobileNo + ", email=" + email + "]";
	}
	
}
