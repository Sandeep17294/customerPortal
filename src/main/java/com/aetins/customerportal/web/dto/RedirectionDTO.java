package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;

public class RedirectionDTO {

	private int serialNo;
	private ServiceRequestMasterDTO serviceRequestNo;
	private String fundType;
	private String fundCode;
	private String fundName;
	private String fundCurrency;
	private BigDecimal fundValue;
	private BigDecimal criteria;
	private BigDecimal value = BigDecimal.ZERO;
	private String mode;
	private boolean newRecord;
	private BigDecimal units;
	private BigDecimal unitPrice;
	private BigDecimal fundPlanCurr;
	private String fundUnitPrice;
	private BigDecimal fundSelling;

	private String investmenetsource;
	
	public boolean isNewRecord() {
		return newRecord;
	}

	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}

	public int getSerialNo() {
		return serialNo;
	}

	public ServiceRequestMasterDTO getServiceRequestNo() {
		return serviceRequestNo;
	}

	public String getFundType() {
		return fundType;
	}

	public String getFundCode() {
		return fundCode;
	}

	public String getFundName() {
		return fundName;
	}

	public String getFundCurrency() {
		return fundCurrency;
	}

	public BigDecimal getFundValue() {
		return fundValue;
	}

	public BigDecimal getCriteria() {
		return criteria;
	}

	public BigDecimal getValue() {
		return value;
	}

	public String getMode() {
		return mode;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public void setServiceRequestNo(ServiceRequestMasterDTO serviceRequestNo) {
		this.serviceRequestNo = serviceRequestNo;
	}

	public void setFundType(String fundType) {
		this.fundType = fundType;
	}

	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public void setFundCurrency(String fundCurrency) {
		this.fundCurrency = fundCurrency;
	}

	public void setFundValue(BigDecimal fundValue) {
		this.fundValue = fundValue;
	}

	public void setCriteria(BigDecimal criteria) {
		this.criteria = criteria;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public BigDecimal getUnits() {
		return units;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnits(BigDecimal units) {
		this.units = units;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getFundPlanCurr() {
		return fundPlanCurr;
	}

	public void setFundPlanCurr(BigDecimal fundPlanCurr) {
		this.fundPlanCurr = fundPlanCurr;
	}

	public String getFundUnitPrice() {
		return fundUnitPrice;
	}

	public void setFundUnitPrice(String fundUnitPrice) {
		this.fundUnitPrice = fundUnitPrice;
	}

	public BigDecimal getFundSelling() {
		return fundSelling;
	}

	public void setFundSelling(BigDecimal fundSelling) {
		this.fundSelling = fundSelling;
	}

	public RedirectionDTO() {

	}

	// added by viswakarthick for copying this object to another..on 16/06/2017
	public RedirectionDTO(RedirectionDTO another) {
		this.serialNo = another.serialNo;
		this.serviceRequestNo = another.serviceRequestNo;
		this.fundType = another.fundType;
		this.fundCode = another.fundCode;
		this.fundName = another.fundName;
		this.fundCurrency = another.fundCurrency;
		this.fundValue = another.fundValue;
		this.criteria = another.criteria;
		this.value = another.value;
		this.mode = another.mode;
		this.newRecord = another.newRecord;
		this.units = another.units;
		this.unitPrice = another.unitPrice;
		this.fundPlanCurr = another.fundPlanCurr;
		this.fundUnitPrice = another.fundUnitPrice;
		this.fundSelling = another.fundSelling;
	}
	
	

	public String getInvestmenetsource() {
		return investmenetsource;
	}

	public void setInvestmenetsource(String investmenetsource) {
		this.investmenetsource = investmenetsource;
	}

	@Override
	public String toString() {
		return "RedirectionDTO [serialNo=" + serialNo + ", serviceRequestNo=" + serviceRequestNo + ", fundType="
				+ fundType + ", fundCode=" + fundCode + ", fundName=" + fundName + ", fundCurrency=" + fundCurrency
				+ ", fundValue=" + fundValue + ", criteria=" + criteria + ", value=" + value + ", mode=" + mode
				+ ", newRecord=" + newRecord + ", units=" + units + ", unitPrice=" + unitPrice + ", fundPlanCurr="
				+ fundPlanCurr + ", fundUnitPrice=" + fundUnitPrice + ", fundSelling=" + fundSelling + "]";
	}

}
