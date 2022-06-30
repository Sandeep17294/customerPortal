package com.aetins.customerportal.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cp_logs_erros")
public class CpLogsErros {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	
	@Column(name="User_ID")
	private String userid;
	
	@Column(name="Cust_Ref_NO")
	private int refno;
	
	@Column(name="Exception")
	private String exception;
	
	@Column(name="Policy_No")
	private String policyno;
	
	@Column(name="Service_Type")
	private String type;
	
	@Column(name="Req_No")
	private int reqno;
	
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPolicyno() {
		return policyno;
	}

	public void setPolicyno(String policyno) {
		this.policyno = policyno;
	}


	public int getReqno() {
		return reqno;
	}

	public void setReqno(int reqno) {
		this.reqno = reqno;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}


	public int getRefno() {
		return refno;
	}

	public void setRefno(int refno) {
		this.refno = refno;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}
	
	

	
}
