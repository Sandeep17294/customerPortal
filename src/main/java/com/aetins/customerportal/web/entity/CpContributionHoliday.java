package com.aetins.customerportal.web.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CP_CONTRIBUTION_HOLIDAY")
public class CpContributionHoliday implements java.io.Serializable{
	private static final long serialVersionUID = -8160952900135226001L;

	private int nSerialNo;
	private CpRequestMaster nServicRequestNo;
	private String vPolicyNo;
	private String vProduct;
	private Date dContributionDueDate;
	private float nOutstandingAmount;
	private float nContribution;
	private String vPaymentFrequency;
	private Date dHolidayFrom;
	private Date dHolidayTo;
	private Date dcommenceDate;
	private BigDecimal npaidContribution;
	private BigDecimal ncontractYear;
	private BigDecimal totReceivedContr;

	
	//getter
	@Id
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator="gen")
	@Column(name = "N_SERIAL_NO", unique = true, nullable = false, precision = 15, scale = 0)
	public int getnSerialNo() {
		return nSerialNo;
	}
	
	@ManyToOne
	@JoinColumn(name="N_SERVICE_REQUEST_NO",referencedColumnName="SERVICE_REQUEST_NO")
	public CpRequestMaster getnServicRequestNo() {
		return nServicRequestNo;
	}
	
	
	@Column(name = "V_POLICY_NO")
	public String getvPolicyNo() {
		return vPolicyNo;
	}
	
	@Column(name = "V_PRODUCT")
	public String getvProduct() {
		return vProduct;
	}
	
	@Column(name = "D_CONTRIBUTION_DUE_DATE")
	public Date getdContributionDueDate() {
		return dContributionDueDate;
	}

	@Column(name = "N_OUTSTANDING_AMOUNT")
	public float getnOutstandingAmount() {
		return nOutstandingAmount;
	}
	
	@Column(name = "N_CONTRIBUTION")
	public float getnContribution() {
		return nContribution;
	}
	
	@Column(name = "V_PAYMENT_FREQUENCY")
	public String getvPaymentFrequency() {
		return vPaymentFrequency;
	}
	
	@Column(name = "D_HOLIDAY_FROM")
	public Date getdHolidayFrom() {
		return dHolidayFrom;
	}
	
	@Column(name = "D_HOLIDAY_TO")
	public Date getdHolidayTo() {
		return dHolidayTo;
	}
	
	@Column(name = "D_COMMENCE_DATE")
	public Date getDcommenceDate() {
		return dcommenceDate;
	}
    
	@Column(name = "N_PAID_CONTRIBUTION")
	public BigDecimal getNpaidContribution() {
		return npaidContribution;
	}

	@Column(name = "N_PLAN_CONTRACT_YEAR")
	public BigDecimal getNcontractYear() {
		return ncontractYear;
	}
	
	@Column(name = "N_TOTAL_RECEIVED_CONTR")
	public BigDecimal getTotReceivedContr() {
		return totReceivedContr;
	}

	//setter
	public void setTotReceivedContr(BigDecimal totReceivedContr) {
		this.totReceivedContr = totReceivedContr;
	}

	public void setDcommenceDate(Date dcommenceDate) {
		this.dcommenceDate = dcommenceDate;
	}

	public void setNpaidContribution(BigDecimal npaidContribution) {
		this.npaidContribution = npaidContribution;
	}

	public void setNcontractYear(BigDecimal ncontractYear) {
		this.ncontractYear = ncontractYear;
	}

	public void setnSerialNo(int nSerialNo) {
		this.nSerialNo = nSerialNo;
	}

	public void setnServicRequestNo(CpRequestMaster nServicRequestNo) {
		this.nServicRequestNo = nServicRequestNo;
	}

	public void setvPolicyNo(String vPolicyNo) {
		this.vPolicyNo = vPolicyNo;
	}

	public void setvProduct(String vProduct) {
		this.vProduct = vProduct;
	}

	public void setdContributionDueDate(Date dContributionDueDate) {
		this.dContributionDueDate = dContributionDueDate;
	}	

	public void setnOutstandingAmount(float nOutstandingAmount) {
		this.nOutstandingAmount = nOutstandingAmount;
	}

	public void setnContribution(float nContribution) {
		this.nContribution = nContribution;
	}

	public void setvPaymentFrequency(String vPaymentFrequency) {
		this.vPaymentFrequency = vPaymentFrequency;
	}

	public void setdHolidayFrom(Date dHolidayFrom) {
		this.dHolidayFrom = dHolidayFrom;
	}

	public void setdHolidayTo(Date dHolidayTo) {
		this.dHolidayTo = dHolidayTo;
	}

	
}