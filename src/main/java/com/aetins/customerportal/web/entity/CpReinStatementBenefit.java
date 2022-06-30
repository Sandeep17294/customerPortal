package com.aetins.customerportal.web.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CP_REIN_STATEMENT_BENEFIT")
public class CpReinStatementBenefit {
	
	private int serial;
	private CpReinStatement serialNo;
	
	private String benifitType;
	private String firstCoverbenifitAmt;
	private BigDecimal firstCoverbenifitTerm;
	private String secondCoverbenifitAmt;
	private BigDecimal secondCoverbenifitTerm;
	
	@ManyToOne
	@JoinColumn(name = "N_SERIAL_NO", referencedColumnName = "SERIALNO")
	public CpReinStatement getSerialNo() {
		return serialNo;
	}
	
	
	@Id
	@GenericGenerator(name = "gen", strategy = "increment")
	@GeneratedValue(generator = "gen")
	@Column(name = "N_SEQ_NO", unique = true, nullable = false, precision = 10, scale = 0)
	public int getSerial() {
		return serial;
	}
	
	@Column(name = "BENEFIT_TYPE")
	public String getBenifitType() {
		return benifitType;
	}
	

	@Column(name = "PLAN_HOLDER_BENEFIT_AMOUNT")
	public String getFirstCoverbenifitAmt() {
		return firstCoverbenifitAmt;
	}
	@Column(name = "PLAN_HOLDER_BENEFIT_TERM")
	public BigDecimal getFirstCoverbenifitTerm() {
		return firstCoverbenifitTerm;
	}
	@Column(name = "SECOND_MEMBER_BENEFIT_AMOUNT")
	public String getSecondCoverbenifitAmt() {
		return secondCoverbenifitAmt;
	}
	@Column(name = "SECOND_MEMBER_BENEFIT_TERM")
	public BigDecimal getSecondCoverbenifitTerm() {
		return secondCoverbenifitTerm;
	}
	
	public void setSerial(int serial) {
		this.serial = serial;
	}

	//setters
	public void setSecondCoverbenifitTerm(BigDecimal secondCoverbenifitTerm) {
		this.secondCoverbenifitTerm = secondCoverbenifitTerm;
	}
	
	public void setSerialNo(CpReinStatement serialNo) {
		this.serialNo = serialNo;
	}
	public void setSecondCoverbenifitAmt(String secondCoverbenifitAmt) {
		this.secondCoverbenifitAmt = secondCoverbenifitAmt;
	}
	public void setFirstCoverbenifitTerm(BigDecimal firstCoverbenifitTerm) {
		this.firstCoverbenifitTerm = firstCoverbenifitTerm;
	}
	
	public void setFirstCoverbenifitAmt(String firstCoverbenifitAmt) {
		this.firstCoverbenifitAmt = firstCoverbenifitAmt;
	}
	public void setBenifitType(String benifitType) {
		this.benifitType = benifitType;
	}
	
	
	
	
	
}
