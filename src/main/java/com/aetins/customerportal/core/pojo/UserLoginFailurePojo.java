package com.aetins.customerportal.core.pojo;

/**
 * To carter user login failure attempts. 
 * @author avinash
 *
 */
public class UserLoginFailurePojo {

	private String username;
	private long failureAttempts;
	private long failureAttemptsExceed;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public long getFailureAttempts() {
		return failureAttempts;
	}
	public void setFailureAttempts(long failureAttempts) {
		this.failureAttempts = failureAttempts;
	}
	public long getFailureAttemptsExceed() {
		return failureAttemptsExceed;
	}
	public void setFailureAttemptsExceed(long failureAttemptsExceed) {
		this.failureAttemptsExceed = failureAttemptsExceed;
	}
	
	@Override
	public String toString() {
		return "UserLoginFailurePojo [username=" + username + ", failureAttempts=" + failureAttempts
				+ ", failureAttemptsExceed=" + failureAttemptsExceed + "]";
	}
	
}
