package com.aetins.customerportal.core.pojo;

import java.sql.Date;

/**
 * Holds logged in user activities
 * @author avinash
 *
 */
public class LoggedUserActivities {

	private long userId;
	
	private String username;
	
	private long custRefNum;
	
	private long unSuccessfullAttempts;
	
	private Date loggedInTime;
	
	private Date logOfTime;

	
	
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getCustRefNum() {
		return custRefNum;
	}

	public void setCustRefNum(long custRefNum) {
		this.custRefNum = custRefNum;
	}

	public long getUnSuccessfullAttempts() {
		return unSuccessfullAttempts;
	}

	public void setUnSuccessfullAttempts(long unSuccessfullAttempts) {
		this.unSuccessfullAttempts = unSuccessfullAttempts;
	}

	public Date getLoggedInTime() {
		return loggedInTime;
	}

	public void setLoggedInTime(Date loggedInTime) {
		this.loggedInTime = loggedInTime;
	}

	public Date getLogOfTime() {
		return logOfTime;
	}

	public void setLogOfTime(Date logOfTime) {
		this.logOfTime = logOfTime;
	}

	@Override
	public String toString() {
		return "LoggedUserActivities [userId=" + userId + ", username=" + username + ", custRefNum=" + custRefNum
				+ ", unSuccessfullAttempts=" + unSuccessfullAttempts + ", loggedInTime=" + loggedInTime + ", logOfTime="
				+ logOfTime + "]";
	}
	
}
