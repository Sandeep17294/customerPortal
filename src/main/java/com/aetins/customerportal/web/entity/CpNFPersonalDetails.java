package com.aetins.customerportal.web.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * This Entity class for Non financial alteration for address contact details
 * @author Viswakarthick
 * 28/03/2017
 */
@Entity
@Table(name="CP_NF_PERSONAL_DETAILS")
public class CpNFPersonalDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3283013299568707947L;
	private int nSerialNo;
	private CpRequestMaster nServiceReqNo;		
	private String vChangeField;
	private String vOldValue;
	private String vNewValue;
	
	//getter
	
	@Id
	@GeneratedValue
	@Column(name = "N_SERIAL_NO", unique = true, nullable = false, precision = 50, scale = 0)
	public int getnSerialNo() {
		return nSerialNo;
	}
	
	@ManyToOne
	@JoinColumn(name="N_SERVICE_REQ_NO",referencedColumnName="SERVICE_REQUEST_NO",nullable=false)
	public CpRequestMaster getnServiceReqNo() {
		return nServiceReqNo;
	}
		
	@Column(name="V_CHANGE_FIELD")
	public String getvChangeField() {
		return vChangeField;
	}

	@Column(name="V_OLD_VALUE")
	public String getvOldValue() {
		return vOldValue;
	}
	
	@Column(name="V_NEW_VALUE")
	public String getvNewValue() {
		return vNewValue;
	}

	//setter
	public void setnSerialNo(int nSerialNo) {
		this.nSerialNo = nSerialNo;
	}

	public void setnServiceReqNo(CpRequestMaster nServiceReqNo) {
		this.nServiceReqNo = nServiceReqNo;
	}

	public void setvChangeField(String vChangeField) {
		this.vChangeField = vChangeField;
	}

	public void setvOldValue(String vOldValue) {
		this.vOldValue = vOldValue;
	}

	public void setvNewValue(String vNewValue) {
		this.vNewValue = vNewValue;
	}
	
	
	
}
