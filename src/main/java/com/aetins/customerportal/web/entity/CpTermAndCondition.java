package com.aetins.customerportal.web.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name = "CP_FT_TERMS_CONDITION")

public class CpTermAndCondition implements Serializable {
	private static final long serialVersionUID = -8160952900135226001L;
	
private int	nserialNo;
private	 String  vpageName;
private	int   norder;
private	 String  vcondEng;
private	 String  vcondArb;
private	 String  vmend;
private	String  vrequired;
public static long getSerialversionuid() {
	return serialVersionUID;
}


@Id
@GenericGenerator(name="gen",strategy="increment")
@GeneratedValue(generator="gen")
@Column(name = "N_SERIAL_NO", unique = true, nullable = false, precision = 10, scale = 0)
public int getNserialNo() {
	return nserialNo;
}

@Column(name = "V_PAGE_NAME")
public String getVpageName() {
	return vpageName;
}

@Column(name = "N_PRIORITY_ORDER")
public int getNorder() {
	return norder;
}

@Column(name = "V_CONDITION_ENG")
public String getVcondEng() {
	return vcondEng;
}

@Column(name = "V_CONDITION_ARB")
public String getVcondArb() {
	return vcondArb;
}

@Column(name = "V_MANDATORY")
public String getVmend() {
	return vmend;
}

@Column(name = "V_REQUIRED")
public String getVrequired() {
	return vrequired;
}




public void setNserialNo(int nserialNo) {
	this.nserialNo = nserialNo;
}
public void setVpageName(String vpageName) {
	this.vpageName = vpageName;
}
public void setNorder(int norder) {
	this.norder = norder;
}
public void setVcondEng(String vcondEng) {
	this.vcondEng = vcondEng;
}
public void setVcondArb(String vcondArb) {
	this.vcondArb = vcondArb;
}
public void setVmend(String vmend) {
	this.vmend = vmend;
}
public void setVrequired(String vrequired) {
	this.vrequired = vrequired;
}










}
