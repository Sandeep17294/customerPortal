package com.aetins.customerportal.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author avinash
 *
 */
@Entity
@Table(name = "cp_transactions_dept_master")
public class CPTransactionDept {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	@Column(name = "dept_name")
	private String deptName;
	
	@Column(name = "transaction_name")
	private String transactionName;
	
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "last_updated_date")
	private Date lastUpdateDate;
	
	@Column(name = "dept_code")
	private String deptCode;
	
	public CPTransactionDept(){
		super();
	}
	
	public CPTransactionDept(String deptName, String transactionName, String deptCode){
		super();
		this.deptName = deptName;
		this.deptCode = deptCode;
		this.transactionName = transactionName;
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getTransactionName() {
		return transactionName;
	}

	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	@Override
	public String toString() {
		return "CPTransactionDept [id=" + id + ", deptName=" + deptName + ", transactionName=" + transactionName
				+ ", createdDate=" + createdDate + ", lastUpdateDate=" + lastUpdateDate + ", deptCode=" + deptCode
				+ "]";
	}

	
	
	
}
