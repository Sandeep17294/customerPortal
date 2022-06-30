package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;

public class FundDetailsSearchDTO {

	private String fundName;
	private BigDecimal sharePercentage;
	private BigDecimal avaliableUnits;
	private BigDecimal unitPrice;
	private String fundCurrency;
	private BigDecimal exchangeRate;
	private BigDecimal fundValue;
	private BigDecimal planValue;
	private String sortName = "fund";

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public BigDecimal getSharePercentage() {
		return sharePercentage;
	}

	public void setSharePercentage(BigDecimal sharePercentage) {
		this.sharePercentage = sharePercentage;
	}

	public BigDecimal getAvaliableUnits() {
		return avaliableUnits;
	}

	public void setAvaliableUnits(BigDecimal avaliableUnits) {
		this.avaliableUnits = avaliableUnits;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getFundCurrency() {
		return fundCurrency;
	}

	public void setFundCurrency(String fundCurrency) {
		this.fundCurrency = fundCurrency;
	}

	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public BigDecimal getFundValue() {
		return fundValue;
	}

	public void setFundValue(BigDecimal fundValue) {
		this.fundValue = fundValue;
	}

	public BigDecimal getPlanValue() {
		return planValue;
	}

	public void setPlanValue(BigDecimal planValue) {
		this.planValue = planValue;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	@Override
	public String toString() {
		return "FundDetailsSearchDTO [fundName=" + fundName + ", sharePercentage=" + sharePercentage
				+ ", avaliableUnits=" + avaliableUnits + ", unitPrice=" + unitPrice + ", fundCurrency=" + fundCurrency
				+ ", exchangeRate=" + exchangeRate + ", fundValue=" + fundValue + ", planValue=" + planValue
				+ ", sortName=" + sortName + "]";
	}

}
