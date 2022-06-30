package com.aetins.customerportal.web.entity;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name="CP_NF_ADDRESS_CONTACTS")
public class CpNFAddressContacts implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6735270337673744468L;	
	private int nSerialNo;
	private CpRequestMaster nServiceReqNo;
	private BigDecimal nAddrSeqNo;
	private String vContactCode;
	private String vContactDesc;
	private String vOldValue;
	private String vNewValue;
	
	//getter
	@Id
	@GeneratedValue
	@Column(name = "N_SERIAL_NO", unique = true, nullable = false)
	public int getnSerialNo() {
		return nSerialNo;
	}
	
	@ManyToOne
	@JoinColumn(name="N_SERVICE_REQ_NO",referencedColumnName="SERVICE_REQUEST_NO",nullable=false)
	public CpRequestMaster getnServiceReqNo() {
		return nServiceReqNo;
	}
	

	@Column(name="N_ADDR_SEQ_NO")
	public BigDecimal getnAddrSeqNo() {
		return nAddrSeqNo;
	}
	
	@Column(name="V_CONTACT_CODE")
	public String getvContactCode() {
		return vContactCode;
	}
	
	@Column(name="V_CONTACT_DESC")
	public String getvContactDesc() {
		return vContactDesc;
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
	public void setnServiceReqNo(CpRequestMaster nServiceReqNo) {
		this.nServiceReqNo = nServiceReqNo;
	}

	public void setnAddrSeqNo(BigDecimal nAddrSeqNo) {
		this.nAddrSeqNo = nAddrSeqNo;
	}

	public void setvContactCode(String vContactCode) {
		this.vContactCode = vContactCode;
	}

	public void setvContactDesc(String vContactDesc) {
		this.vContactDesc = vContactDesc;
	}

	public void setvOldValue(String vOldValue) {
		this.vOldValue = vOldValue;
	}

	public void setvNewValue(String vNewValue) {
		this.vNewValue = vNewValue;
	}

	public void setnSerialNo(int nSerialNo) {
		this.nSerialNo = nSerialNo;
	}
	
	
	
	
}
