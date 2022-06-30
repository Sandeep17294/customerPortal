package com.aetins.customerportal.web.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CP_FATCA_TABLE")
public class CpFatca implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5291419017198184600L;

	private int serialNo;
	private CpRequestMaster serviceRequestNo;
	private String serviceName;
	private String isUsPerson;
	private String tinNo;
	private String taxMoreThanOne;
	private String residentUAE;
	
	private List<CpFatcaCountryDet> cpFatcaCountryDet;

	
	@Id
	@GeneratedValue
	@Column(name = "N_SERIAL_NO", unique = true, nullable = false, precision = 10, scale = 0)
	public int getSerialNo() {
		return serialNo;
	}

	@ManyToOne
	@JoinColumn(name = "N_SERVICE_REQUEST_NO", referencedColumnName = "SERVICE_REQUEST_NO")
	public CpRequestMaster getServiceRequestNo() {
		return serviceRequestNo;
	}

	@Column(name = "V_SERVICE_NAME")
	public String getServiceName() {
		return serviceName;
	}

	@Column(name = "V_US_PERSON")
	public String getIsUsPerson() {
		return isUsPerson;
	}

	@Column(name = "V_TIN_NO")
	public String getTinNo() {
		return tinNo;
	}

	@Column(name = "V_TAX_MORETHAN_ONECOUN")
	public String getTaxMoreThanOne() {
		return taxMoreThanOne;
	}
	
	@Column(name = "V_UAE_RESIDENT")
	public String getResidentUAE() {
		return residentUAE;
	}


	@OneToMany( mappedBy = "serialNo",targetEntity=CpFatcaCountryDet.class,cascade=CascadeType.ALL)
	public List<CpFatcaCountryDet> getCpFatcaCountryDet() {
		return cpFatcaCountryDet;
	}

	public void setCpFatcaCountryDet(List<CpFatcaCountryDet> cpFatcaCountryDet) {
		this.cpFatcaCountryDet = cpFatcaCountryDet;
	}

	//setters
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public void setServiceRequestNo(CpRequestMaster serviceRequestNo) {
		this.serviceRequestNo = serviceRequestNo;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public void setIsUsPerson(String isUsPerson) {
		this.isUsPerson = isUsPerson;
	}

	public void setTinNo(String tinNo) {
		this.tinNo = tinNo;
	}

	public void setTaxMoreThanOne(String taxMoreThanOne) {
		this.taxMoreThanOne = taxMoreThanOne;
	}

	public void setResidentUAE(String residentUAE) {
		this.residentUAE = residentUAE;
	}

	

}
