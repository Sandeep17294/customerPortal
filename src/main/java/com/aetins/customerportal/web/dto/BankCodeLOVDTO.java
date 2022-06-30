package com.aetins.customerportal.web.dto;

public class BankCodeLOVDTO {

	private String bankcode;
	private String branchname;
	private String branchcode;
	public String getBankcode() {
		return bankcode;
	}
	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}
	public String getBranchname() {
		return branchname;
	}
	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}
	public String getBranchcode() {
		return branchcode;
	}
	public void setBranchcode(String branchcode) {
		this.branchcode = branchcode;
	}
	@Override
	public String toString() {
		return "BankCodeLOVDTO [bankcode=" + bankcode + ", branchname=" + branchname + ", branchcode=" + branchcode
				+ "]";
	}
	
	
}
