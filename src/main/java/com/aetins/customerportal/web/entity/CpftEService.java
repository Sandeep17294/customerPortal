package com.aetins.customerportal.web.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cp_fteservice_master")
public class CpftEService implements Serializable {

	private static final long serialVersionUID = 1377409231508013201L;

	private int serialNo;
	private String serviceType;
	private String serviceCode;
	private String createdUser;
	private Date createdDate;

	@Id
	@GeneratedValue
	@Column(name = "N_SERIAL_NO", unique = true, nullable = false, precision = 10, scale = 0)
	public int getSerialNo() {
		return serialNo;
	}

	@Column(name = "V_SERVICE_TYPE")
	public String getServiceType() {
		return serviceType;
	}

	@Column(name = "V_SERVICE_CODE")
	public String getServiceCode() {
		return serviceCode;
	}

	@Column(name = "V_CREATED_USER")
	public String getCreatedUser() {
		return createdUser;
	}

	@Column(name = "D_CREATED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
