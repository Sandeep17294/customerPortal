package com.aetins.customerportal.web.dto;

public class CpTermAndConditionDTO {

	private int serialNo;
	private String page_name;
	private int order;
	private String conditionEng;
	private String conditionArb;
	private boolean mandatory;
	private boolean required;
	private String mandString;
	private String requiredString;

	public int getSerialNo() {
		return serialNo;
	}

	public String getPage_name() {
		return page_name;
	}

	public int getOrder() {
		return order;
	}

	public String getConditionEng() {
		return conditionEng;
	}

	public String getConditionArb() {
		return conditionArb;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public void setPage_name(String page_name) {
		this.page_name = page_name;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public void setConditionEng(String conditionEng) {
		this.conditionEng = conditionEng;
	}

	public void setConditionArb(String conditionArb) {
		this.conditionArb = conditionArb;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public boolean isRequired() {
		return required;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getMandString() {
		return mandString;
	}

	public String getRequiredString() {
		return requiredString;
	}

	public void setMandString(String mandString) {
		this.mandString = mandString;
	}

	public void setRequiredString(String requiredString) {
		this.requiredString = requiredString;
	}

}
