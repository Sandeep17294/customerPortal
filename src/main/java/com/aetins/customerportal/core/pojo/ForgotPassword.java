package com.aetins.customerportal.core.pojo;

/**
 * @author avinash
 *
 */
public class ForgotPassword {

	private String username;

	private String dob;

	private String secuirtyQues;

	private String email;

	private String errorMsg;

	private String pswd;

	private String confPswd;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getSecuirtyQues() {
		return secuirtyQues;
	}

	public void setSecuirtyQues(String secuirtyQues) {
		this.secuirtyQues = secuirtyQues;
	}


	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public String getConfPswd() {
		return confPswd;
	}

	public void setConfPswd(String confPswd) {
		this.confPswd = confPswd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "ForgotPassword [username=" + username + ", dob=" + dob + ", secuirtyQues=" + secuirtyQues + ", email="
				+ email + ", errorMsg=" + errorMsg + ", pswd=" + pswd + ", confPswd=" + confPswd + "]";
	}

}
