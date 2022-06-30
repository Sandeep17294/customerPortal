package com.aetins.customerportal.web.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Entity responsible for data persist for OTP audit trails<br>
 * <b>NOTE: createdDate, lastUpdatedDate should not assign values to these fields, AS MYSQL triggers will update.</b>
 * @author avinash
 *
 */
@Entity
@Table(name="cp_otp_audit_trail")
public class CpOTPAuditTrails implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4659882176872777965L;

	@Id
	@GenericGenerator(name = "gen", strategy = "increment")
	@GeneratedValue(generator = "gen")
	@Column(name = "id", unique = true, nullable = false, precision = 15, scale = 0)
	private long id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="cust_ref_no")
	private long custRefNum;
	
	@Column(name="otp_max_limit_reached")
	private long noOfOTPMaxLimitReached;
	
	@Column(name="otp_expried_limit_reached")
	private long noOfOTPExpriedLimitReached;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="last_updated_date")
	private Date lastUpdatedDate;
	
	
	
	/* GETTERS && SETTERS */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public long getNoOfOTPMaxLimitReached() {
		return noOfOTPMaxLimitReached;
	}

	public void setNoOfOTPMaxLimitReached(long noOfOTPMaxLimitReached) {
		this.noOfOTPMaxLimitReached = noOfOTPMaxLimitReached;
	}

	public long getNoOfOTPExpriedLimitReached() {
		return noOfOTPExpriedLimitReached;
	}

	public void setNoOfOTPExpriedLimitReached(long noOfOTPExpriedLimitReached) {
		this.noOfOTPExpriedLimitReached = noOfOTPExpriedLimitReached;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	@Override
	public String toString() {
		return "CpOTPAuditTrails [id=" + id + ", username=" + username + ", custRefNum=" + custRefNum
				+ ", noOfOTPMaxLimitReached=" + noOfOTPMaxLimitReached + ", noOfOTPExpriedLimitReached="
				+ noOfOTPExpriedLimitReached + ", createdDate=" + createdDate + ", lastUpdatedDate=" + lastUpdatedDate
				+ "]";
	}

}
