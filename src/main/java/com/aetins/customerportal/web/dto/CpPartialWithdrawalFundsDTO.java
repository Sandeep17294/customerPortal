package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;

public class CpPartialWithdrawalFundsDTO {
	
	private int nSerialNo;
	private ServiceRequestMasterDTO nServicRequestNo;
	private String vFundType;
	private String vFundCode;
	private String vFundName;
	private String vFundCurrency;
	private BigDecimal nFundValue;
	private String vCriteria;
	private int nCriteriaValue;
	
	private String investsourcode;
	private String remarks;
	private int seqno;
	
	public int getnSerialNo() {
		return nSerialNo;
	}
	public void setnSerialNo(int nSerialNo) {
		this.nSerialNo = nSerialNo;
	}
	
	public ServiceRequestMasterDTO getnServicRequestNo() {
		return nServicRequestNo;
	}
	public void setnServicRequestNo(ServiceRequestMasterDTO nServicRequestNo) {
		this.nServicRequestNo = nServicRequestNo;
	}
	public String getvFundType() {
		return vFundType;
	}
	public void setvFundType(String vFundType) {
		this.vFundType = vFundType;
	}
	public String getvFundCode() {
		return vFundCode;
	}
	public void setvFundCode(String vFundCode) {
		this.vFundCode = vFundCode;
	}
	public String getvFundName() {
		return vFundName;
	}
	public void setvFundName(String vFundName) {
		this.vFundName = vFundName;
	}
	public String getvFundCurrency() {
		return vFundCurrency;
	}
	public void setvFundCurrency(String vFundCurrency) {
		this.vFundCurrency = vFundCurrency;
	}
	
	public String getvCriteria() {
		return vCriteria;
	}
	public void setvCriteria(String vCriteria) {
		this.vCriteria = vCriteria;
	}
	public BigDecimal getnFundValue() {
		return nFundValue;
	}
	public void setnFundValue(BigDecimal nFundValue) {
		this.nFundValue = nFundValue;
	}
	public int getnCriteriaValue() {
		return nCriteriaValue;
	}
	public void setnCriteriaValue(int nCriteriaValue) {
		this.nCriteriaValue = nCriteriaValue;
	}
	public String getInvestsourcode() {
		return investsourcode;
	}
	public void setInvestsourcode(String investsourcode) {
		this.investsourcode = investsourcode;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getSeqno() {
		return seqno;
	}
	public void setSeqno(int seqno) {
		this.seqno = seqno;
	}

	


}
