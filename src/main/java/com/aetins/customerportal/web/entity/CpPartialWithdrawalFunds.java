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
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CP_PARTIAL_WITHDRAWAL_FUNDS")
public class CpPartialWithdrawalFunds implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 766187172363386706L;
	private int nSerialNo;
	private CpRequestMaster nServicRequestNo;
	private String vFundType;
	private String vFundCode;
	private String vFundName;
	private String vFundCurrency;
	private BigDecimal nFundValue;
	private String vCriteria;
	private int nCriteriaValue;
	
	private String vinvestementsource;
	private String remarks;
	private int seqno;
	
	

	
	//getter
	@Id
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator="gen")
	@Column(name = "N_SERIAL_NO", unique = true, nullable = false, precision = 15, scale = 0)
	public int getnSerialNo() {
		return nSerialNo;
	}
	
	@ManyToOne
	@JoinColumn(name="N_SERVICE_REQUEST_NO",referencedColumnName="SERVICE_REQUEST_NO")
	public CpRequestMaster getnServicRequestNo() {
		return nServicRequestNo;
	}
	
	public void setServiceRequestNo(CpRequestMaster request) {
		this.nServicRequestNo = request;
	}
	
	public void setnServicRequestNo(CpRequestMaster nServicRequestNo) {
		this.nServicRequestNo = nServicRequestNo;
	}
	
	
	
	@Column(name = "V_FUND_TYPE")
	public String getvFundType() {
		return vFundType;
	}

	
	@Column(name = "V_FUND_CODE")
	public String getvFundCode() {
		return vFundCode;
	}
		
	@Column(name = "V_FUND_NAME")
	public String getvFundName() {
		return vFundName;
	}

	
	@Column(name = "V_FUND_CURRENCY")
	public String getvFundCurrency() {
		return vFundCurrency;
	}
	
	@Column(name = "N_FUND_VALUE")
	public BigDecimal getnFundValue() {
		return nFundValue;
	}
	
	@Column(name = "V_CRITERIA")
	public String getvCriteria() {
		return vCriteria;
	}
	
	@Column(name = "N_CRITERIA_VALUE")
	public int getnCriteriaValue() {
		return nCriteriaValue;
	}
	
	@Column(name = "V_INVESTMENT_SOURCE")
	public String getVinvestementsource() {
		return vinvestementsource;
	}
	
	@Column(name = "V_REMARKS")
	public String getRemarks() {
		return remarks;
	}
	
	@Column(name = "SEQ_NO")
	public int getSeqno() {
		return seqno;
	}

	
	
	
	//setter

	public void setnSerialNo(int nSerialNo) {
		this.nSerialNo = nSerialNo;
	}

	

	public void setvFundType(String vFundType) {
		this.vFundType = vFundType;
	}

	public void setvFundCode(String vFundCode) {
		this.vFundCode = vFundCode;
	}

	public void setvFundName(String vFundName) {
		this.vFundName = vFundName;
	}

	public void setvFundCurrency(String vFundCurrency) {
		this.vFundCurrency = vFundCurrency;
	}

	public void setvCriteria(String vCriteria) {
		this.vCriteria = vCriteria;
	}

	public void setnFundValue(BigDecimal nFundValue) {
		this.nFundValue = nFundValue;
	}	

	public void setnCriteriaValue(int nCriteriaValue) {
		this.nCriteriaValue = nCriteriaValue;
	}

	
	public void setVinvestementsource(String vinvestementsource) {
		this.vinvestementsource = vinvestementsource;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public void setSeqno(int seqno) {
		this.seqno = seqno;
	}

	
	

	
}