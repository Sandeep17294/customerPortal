package com.aetins.customerportal.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CP_USER_SECURITY_QUESTION")
public class CpResetSecurityAnswer implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 651260149645720355L;

	private int id;
	private String userName;
	private int custRefNo;
	private String securityQues;
	private String securityAns;
	private String quesStatus;
	private Date processDate;
	private Date recentModifiedDate;
	private String userOtp;

	// getter
	@Id
	@GenericGenerator(name = "gen", strategy = "increment")
	@GeneratedValue(generator = "gen")
	@Column(name = "N_ID", unique = true, nullable = false, precision = 15, scale = 0)
	public int getId() {
		return id;
	}

	@Column(name = "V_USER_NAME")
	public String getUserName() {
		return userName;
	}

	@Column(name = "N_CUST_REF_NO")
	public int getCustRefNo() {
		return custRefNo;
	}

	@Column(name = "V_SECURITY_QUES")
	public String getSecurityQues() {
		return securityQues;
	}

	@Column(name = "V_SECURITY_ANS")
	public String getSecurityAns() {
		return securityAns;
	}

	@Column(name = "V_QUESTION_STATUS")
	public String getQuesStatus() {
		return quesStatus;
	}

	@Column(name = "D_PROCESS_DATE")
	public Date getProcessDate() {
		return processDate;
	}

	@Column(name = "D_RECENT_MODIFIED_ANS")
	public Date getRecentModifiedDate() {
		return recentModifiedDate;
	}

	@Column(name = "V_USER_OTP")
	public String getUserOtp() {
		return userOtp;
	}

	//setters
	public void setId(int id) {
		this.id = id;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setCustRefNo(int custRefNo) {
		this.custRefNo = custRefNo;
	}

	public void setSecurityQues(String securityQues) {
		this.securityQues = securityQues;
	}

	public void setSecurityAns(String securityAns) {
		this.securityAns = securityAns;
	}

	public void setQuesStatus(String quesStatus) {
		this.quesStatus = quesStatus;
	}

	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}

	public void setRecentModifiedDate(Date recentModifiedDate) {
		this.recentModifiedDate = recentModifiedDate;
	}

	public void setUserOtp(String userOtp) {
		this.userOtp = userOtp;
	}

}
