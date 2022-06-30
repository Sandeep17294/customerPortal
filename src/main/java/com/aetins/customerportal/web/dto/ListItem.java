package com.aetins.customerportal.web.dto;

public class ListItem {
	private String code;
	private String description;
	private String currencyCode;
	private String units;
	
	

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public ListItem() {

	}

	// added by viswakarthick for copying this object to another..on 16/06/2017
	public ListItem(ListItem another) {
		this.code = another.code;
		this.description = another.description;
		this.currencyCode = another.currencyCode;
		this.units = another.units;
	}

	@Override
	public String toString() {
		return "ListItem [code=" + code + ", description=" + description + ", currencyCode=" + currencyCode + ", units="
				+ units + "]";
	}

}
