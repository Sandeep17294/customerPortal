package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;

public class StrategyDTO {

	private int serialNo;
	private String strategy;
	private String strategyCode;
	private String strategyName;
	private BigDecimal criteriaValue;
	private BigDecimal criteriaValueNew;
	private BigDecimal percentage;
	private String fundCode;
	private String fundNAme;
	private String fundCurrency;
	private String mode;
	private boolean newRow;
	private ServiceRequestMasterDTO serviceRequestNo;
	
	
	public int getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}
	public String getStrategy() {
		return strategy;
	}
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}
	public String getStrategyCode() {
		return strategyCode;
	}
	public void setStrategyCode(String strategyCode) {
		this.strategyCode = strategyCode;
	}
	public String getStrategyName() {
		return strategyName;
	}
	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}
	public BigDecimal getCriteriaValue() {
		return criteriaValue;
	}
	public void setCriteriaValue(BigDecimal criteriaValue) {
		this.criteriaValue = criteriaValue;
	}
	public BigDecimal getCriteriaValueNew() {
		return criteriaValueNew;
	}
	public void setCriteriaValueNew(BigDecimal criteriaValueNew) {
		this.criteriaValueNew = criteriaValueNew;
	}
	public BigDecimal getPercentage() {
		return percentage;
	}
	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}
	public String getFundCode() {
		return fundCode;
	}
	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}
	public String getFundNAme() {
		return fundNAme;
	}
	public void setFundNAme(String fundNAme) {
		this.fundNAme = fundNAme;
	}
	public String getFundCurrency() {
		return fundCurrency;
	}
	public void setFundCurrency(String fundCurrency) {
		this.fundCurrency = fundCurrency;
	}
	public boolean isNewRow() {
		return newRow;
	}
	public void setNewRow(boolean newRow) {
		this.newRow = newRow;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public ServiceRequestMasterDTO getServiceRequestNo() {
		return serviceRequestNo;
	}
	public void setServiceRequestNo(ServiceRequestMasterDTO serviceRequestNo) {
		this.serviceRequestNo = serviceRequestNo;
	}
	
}
