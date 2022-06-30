package com.aetins.customerportal.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CP_SERVICE_TYPE")
public class CpServiceType implements java.io.Serializable{
	private static final long serialVersionUID = -8160952900135226001L;

	private int nserialNo;
	private String vserviceTypeEn;
	private String vserviceTypeAr;
	
	@Id
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator="gen")
	@Column(name = "N_SERIAL_NO", unique = true, nullable = false, precision = 15, scale = 0)
	public int getNserialNo() {
		return nserialNo;
	}
	public void setNserialNo(int nserialNo) {
		this.nserialNo = nserialNo;
	}
	
	@Column(name = "V_SERVICE_TYPE_EN")
	public String getVserviceTypeEn() {
		return vserviceTypeEn;
	}
	public void setVserviceTypeEn(String vserviceTypeEn) {
		this.vserviceTypeEn = vserviceTypeEn;
	}
	@Column(name = "V_SERVICE_TYPE_AR")
	public String getVserviceTypeAr() {
		return vserviceTypeAr;
	}
	public void setVserviceTypeAr(String vserviceTypeAr) {
		this.vserviceTypeAr = vserviceTypeAr;
	}
	

	
	//getter
	/*@Id
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator="gen")
	@Column(name = "N_SERIAL_NO", unique = true, nullable = false, precision = 15, scale = 0)
	public int getnSerialNo() {
		return nserialNo;
	}
	
	
	@Column(name = "V_SERVICE_TYPE_EN")
	public String getvServiceTypeEn() {
		return vserviceTypeEn;
	}

	@Column(name = "V_SERVICE_TYPE_AR")
	public String getvServiceTypeAr() {
		return vServiceTypeAr;
	}
	
	
	//setter

	public void setnSerialNo(int nSerialNo) {
		this.nserialNo = nSerialNo;
	}

	public void setvServiceTypeEn(String vServiceTypeEn) {
		this.vserviceTypeEn = vServiceTypeEn;
	}

	public void setvServiceTypeAr(String vServiceTypeAr) {
		this.vServiceTypeAr = vServiceTypeAr;
	}*/

	
}