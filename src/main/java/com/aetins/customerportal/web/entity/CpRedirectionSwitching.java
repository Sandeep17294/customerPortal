package com.aetins.customerportal.web.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CP_REDIRECTION_SWITCHING")
public class CpRedirectionSwitching implements Serializable {

	private static final long serialVersionUID = 5476933241674555721L;

	private int serialNo;
	private CpRequestMaster serviceRequestNo;
	private String fundType;
	private String fundCode;
	private String fundName;
	private String fundCurrency;
	private BigDecimal fundValue;
	private BigDecimal criteria;
	private BigDecimal value;
	private String mode;
	private BigDecimal units;
	private BigDecimal unitPrice;
	private BigDecimal fundPlanCurr;
	
	private String investementsourcecode;

	@Id
	@GeneratedValue
	@Column(name = "N_SERIAL_NO", unique = true, nullable = false, precision = 10, scale = 0)
	public int getSerialNo() {
		return serialNo;
	}

	@ManyToOne
	@JoinColumn(name = "N_SERVICE_REQUEST_NO", referencedColumnName = "SERVICE_REQUEST_NO")
	public CpRequestMaster getServiceRequestNo() {
		return serviceRequestNo;
	}

	@Column(name = "V_FUND_TYPE")
	public String getFundType() {
		return fundType;
	}

	@Column(name = "V_FUND_CODE")
	public String getFundCode() {
		return fundCode;
	}

	@Column(name = "V_FUND_NAME")
	public String getFundName() {
		return fundName;
	}

	@Column(name = "V_FUND_CURRENCY")
	public String getFundCurrency() {
		return fundCurrency;
	}

	@Column(name = "N_FUND_VALUE")
	public BigDecimal getFundValue() {
		return fundValue;
	}

	@Column(name = "V_CRITERIA")
	public BigDecimal getCriteria() {
		return criteria;
	}

	@Column(name = "N_VALUE")
	public BigDecimal getValue() {
		return value;
	}

	@Column(name = "V_MODE")
	public String getMode() {
		return mode;
	}

	@Column(name = "N_UNIT")
	public BigDecimal getUnits() {
		return units;
	}

	@Column(name = "N_UNIT_PRICE")
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	
	@Column(name = "N_FUND_PLAN_CURRENCY")
	public BigDecimal getFundPlanCurr() {
		return fundPlanCurr;
	}
	
	@Column(name = "V_INVESTMENT_SOURCE")
	public String getInvestementsourcecode() {
		return investementsourcecode;
	}

	public void setFundPlanCurr(BigDecimal fundPlanCurr) {
		this.fundPlanCurr = fundPlanCurr;
	}

	public void setUnits(BigDecimal units) {
		this.units = units;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public void setServiceRequestNo(CpRequestMaster serviceRequestNo) {
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

	public void setInvestementsourcecode(String investementsourcecode) {
		this.investementsourcecode = investementsourcecode;
	}

	
	
}
