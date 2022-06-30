package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;
import java.util.Date;

public class CpContributionHolidayDTO {
	private int nSerialNo;
	private ServiceRequestMasterDTO nServicRequestNo;
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
	private boolean status;
	private String securityAns;

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

	public String getvPolicyNo() {
		return vPolicyNo;
	}

	public void setvPolicyNo(String vPolicyNo) {
		this.vPolicyNo = vPolicyNo;
	}

	public String getvProduct() {
		return vProduct;
	}

	public void setvProduct(String vProduct) {
		this.vProduct = vProduct;
	}

	public Date getdContributionDueDate() {
		return dContributionDueDate;
	}

	public void setdContributionDueDate(Date dContributionDueDate) {
		this.dContributionDueDate = dContributionDueDate;
	}

	public float getnOutstandingAmount() {
		return nOutstandingAmount;
	}

	public void setnOutstandingAmount(float nOutstandingAmount) {
		this.nOutstandingAmount = nOutstandingAmount;
	}

	public float getnContribution() {
		return nContribution;
	}

	public void setnContribution(float nContribution) {
		this.nContribution = nContribution;
	}

	public String getvPaymentFrequency() {
		return vPaymentFrequency;
	}

	public void setvPaymentFrequency(String vPaymentFrequency) {
		this.vPaymentFrequency = vPaymentFrequency;
	}

	public Date getdHolidayFrom() {
		return dHolidayFrom;
	}

	public void setdHolidayFrom(Date dHolidayFrom) {
		this.dHolidayFrom = dHolidayFrom;
	}

	public Date getdHolidayTo() {
		return dHolidayTo;
	}

	public void setdHolidayTo(Date dHolidayTo) {
		this.dHolidayTo = dHolidayTo;
	}

	public Date getDcommenceDate() {
		return dcommenceDate;
	}

	public void setDcommenceDate(Date dcommenceDate) {
		this.dcommenceDate = dcommenceDate;
	}

	public BigDecimal getNpaidContribution() {
		return npaidContribution;
	}

	public BigDecimal getNcontractYear() {
		return ncontractYear;
	}

	public void setNpaidContribution(BigDecimal npaidContribution) {
		this.npaidContribution = npaidContribution;
	}

	public void setNcontractYear(BigDecimal ncontractYear) {
		this.ncontractYear = ncontractYear;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getSecurityAns() {
		return securityAns;
	}

	public void setSecurityAns(String securityAns) {
		this.securityAns = securityAns;
	}

	public BigDecimal getTotReceivedContr() {
		return totReceivedContr;
	}

	public void setTotReceivedContr(BigDecimal totReceivedContr) {
		this.totReceivedContr = totReceivedContr;
	}

}
