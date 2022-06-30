package com.aetins.customerportal.web.dto;

public class CpPwPaymentDTO {
	
	private int nSerialNo;
	private ServiceRequestMasterDTO nServicRequestNo;
	private String vPaymentMethod;
	private String vBankCode;
	private String vBankBranch;
	private String vBankName;
	private String vAccountNo;
	private String vAccountType;
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
	public String getvPaymentMethod() {
		return vPaymentMethod;
	}
	public void setvPaymentMethod(String vPaymentMethod) {
		this.vPaymentMethod = vPaymentMethod;
	}
	public String getvBankCode() {
		return vBankCode;
	}
	public void setvBankCode(String vBankCode) {
		this.vBankCode = vBankCode;
	}
	public String getvBankBranch() {
		return vBankBranch;
	}
	public void setvBankBranch(String vBankBranch) {
		this.vBankBranch = vBankBranch;
	}
	public String getvBankName() {
		return vBankName;
	}
	public void setvBankName(String vBankName) {
		this.vBankName = vBankName;
	}
	public String getvAccountNo() {
		return vAccountNo;
	}
	public void setvAccountNo(String vAccountNo) {
		this.vAccountNo = vAccountNo;
	}
	public String getvAccountType() {
		return vAccountType;
	}
	public void setvAccountType(String vAccountType) {
		this.vAccountType = vAccountType;
	}

}
