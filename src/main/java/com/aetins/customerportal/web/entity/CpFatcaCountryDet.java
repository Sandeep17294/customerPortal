package com.aetins.customerportal.web.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CP_FACTA_COUNTRY_DET")
public class CpFatcaCountryDet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2683197657820611752L;

	private int serial;
	private CpFatca serialNo;
	private String country;
	private String tinOrReason;
	private String countryName;

	@Id
	@GenericGenerator(name = "gen", strategy = "increment")
	@GeneratedValue(generator = "gen")
	@Column(name = "N_SEQ_NO", unique = true, nullable = false, precision = 10, scale = 0)
	public int getSerial() {
		return serial;
	}

	@ManyToOne
	@JoinColumn(name = "N_SERIAL_NO", referencedColumnName = "N_SERIAL_NO")
	public CpFatca getSerialNo() {
		return serialNo;
	}

	@Column(name = "V_COUNTRY_JURIDIS")
	public String getCountry() {
		return country;
	}

	@Column(name = "V_TIN_REASON")
	public String getTinOrReason() {
		return tinOrReason;
	}

	@Column(name = "V_COUNTRY_NAME")
	public String getCountryName() {
		return countryName;
	}

	//setters
	public void setSerial(int serial) {
		this.serial = serial;
	}

	public void setSerialNo(CpFatca serialNo) {
		this.serialNo = serialNo;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setTinOrReason(String tinOrReason) {
		this.tinOrReason = tinOrReason;
	}
	
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

}
