package com.aetins.customerportal.web.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CP_PROTECTION_BENIFIT")
public class CpProtectionBenifit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -277600986714810965L;

	private int serialNo;
	private CpRequestMaster serviceRequestNo;
	private String policyNo;
	private String benifitRiderName;
	private String memberName;
	private String existBenifit;
	private BigDecimal cummulativeBenifit;
	private String benifitType;
	private String alterationType;
	private BigDecimal newvalue;
	private String riderCode;
	private String coveredBankName;
	private String coveredIBAN;
	private String CoveredSourceFund;
	private String planHolderBankName;
	private String planHolderIBAN;
	private String planHolderSourceFund;
	private BigDecimal incomeYearOne;
	private BigDecimal incomeYearTwo;
	private BigDecimal incomeYearThree;
	private BigDecimal assetsCash;
	private BigDecimal assetsShares;
	private BigDecimal assetsRealEstate;
	private BigDecimal assetsOthers;
	private BigDecimal liabilitiesLoan;
	private BigDecimal liabilitiesPayable;
	private BigDecimal liabilitiesMortgage;
	private BigDecimal liabilitiesOther;
	
	private String firstCoverHeight;
	private String firstCoverWeight;
	private String secondCoverHeight;
	private String secondCoverWeight;
	

	private String quesOneFirst;
	private String quesTwoAFirst;
	private String quesTwoBFirst;
	private String quesTwoCFirst;
	private String quesTwoDFirst;
	private String quesTwoEFirst;
	private String quesTwoFFirst;
	private String quesTwoGFirst;
	private String quesTwoHFirst;
	private String quesThreeFirst;
	private String quesFourFirst;
	private String quesFiveFirst;
	private String quesSixFirst;
	private String quesSevenFirst;
	private String quesEightFirst;
	private String quesNineFirst;
	private String quesTenFirst;
	private String quesElevenFirst;
	private String quesElevenAFirst;

	private String quesOneSecond;
	private String quesTwoASecond;
	private String quesTwoBSecond;
	private String quesTwoCSecond;
	private String quesTwoDSecond;
	private String quesTwoESecond;
	private String quesTwoFSecond;
	private String quesTwoGSecond;
	private String quesTwoHSecond;
	private String quesThreeSecond;
	private String quesFourSecond;
	private String quesFiveSecond;
	private String quesSixSecond;
	private String quesSevenSecond;
	private String quesEightSecond;
	private String quesNineSecond;
	private String quesTenSecond;
	private String quesElevenSecond;
	private String quesElevenASecond;
	private String firstCoverMedDetails;
	private String secondCoverMedDetails;
	
	
	
	@Column(name="FIRSTCOVER_QUES_ONE")
	public String getQuesOneFirst() {
		return quesOneFirst;
	}
	
	
	@Column(name="FIRSTCOVER_QUES_TWO_A")
	public String getQuesTwoAFirst() {
		return quesTwoAFirst;
	}
	
	
	@Column(name="FIRSTCOVER_QUES_TWO_B")
	public String getQuesTwoBFirst() {
		return quesTwoBFirst;
	}
	
	
	@Column(name="FIRSTCOVER_QUES_TWO_C")
	public String getQuesTwoCFirst() {
		return quesTwoCFirst;
	}
	
	
	@Column(name="FIRSTCOVER_QUES_TWO_D")
	public String getQuesTwoDFirst() {
		return quesTwoDFirst;
	}
	
	
	@Column(name="FIRSTCOVER_QUES_TWO_E")
	public String getQuesTwoEFirst() {
		return quesTwoEFirst;
	}
	
	
	
	
	@Column(name="FIRSTCOVER_QUES_TWO_F")
	public String getQuesTwoFFirst() {
		return quesTwoFFirst;
	}
	
	
	@Column(name="FIRSTCOVER_QUES_TWO_G")
	public String getQuesTwoGFirst() {
		return quesTwoGFirst;
	}
	
	
	@Column(name="FIRSTCOVER_QUES_TWO_H")
	public String getQuesTwoHFirst() {
		return quesTwoHFirst;
	}
	
	
	@Column(name="FIRSTCOVER_QUES_THREE")
	public String getQuesThreeFirst() {
		return quesThreeFirst;
	}
	
	
	@Column(name="FIRSTCOVER_QUES_FOUR")
	public String getQuesFourFirst() {
		return quesFourFirst;
	}
	
	
	@Column(name="FIRSTCOVER_QUES_FIVE")
	public String getQuesFiveFirst() {
		return quesFiveFirst;
	}
	
	
	@Column(name="FIRSTCOVER_QUES_SIX")
	public String getQuesSixFirst() {
		return quesSixFirst;
	}
	
	
	@Column(name="FIRSTCOVER_QUES_SEVEN")
	public String getQuesSevenFirst() {
		return quesSevenFirst;
	}
	
	
	@Column(name="FIRSTCOVER_QUES_EIGHT")
	public String getQuesEightFirst() {
		return quesEightFirst;
	}
	
	
	@Column(name="FIRSTCOVER_QUES_NINE")
	public String getQuesNineFirst() {
		return quesNineFirst;
	}
	
	
	@Column(name="FIRSTCOVER_QUES_TEN")
	public String getQuesTenFirst() {
		return quesTenFirst;
	}
	
	
	@Column(name="FIRSTCOVER_QUES_ELEVEN")
	public String getQuesElevenFirst() {
		return quesElevenFirst;
	}
	
	
	@Column(name="FIRSTCOVER_QUES_ELEVEN_A")
	public String getQuesElevenAFirst() {
		return quesElevenAFirst;
	}
	
	
	@Column(name="SECONDCOVER_QUES_ONE")
	public String getQuesOneSecond() {
		return quesOneSecond;
	}
	
	
	@Column(name="SECONDCOVER_QUES_TWO_A")
	public String getQuesTwoASecond() {
		return quesTwoASecond;
	}
	
	
	@Column(name="SECONDCOVER_QUES_TWO_B")
	public String getQuesTwoBSecond() {
		return quesTwoBSecond;
	}
	
	
	@Column(name="SECONDCOVER_QUES_TWO_C")
	public String getQuesTwoCSecond() {
		return quesTwoCSecond;
	}
	
	
	@Column(name="SECONDCOVER_QUES_TWO_D")
	public String getQuesTwoDSecond() {
		return quesTwoDSecond;
	}
	
	
	@Column(name="SECONDCOVER_QUES_TWO_E")
	public String getQuesTwoESecond() {
		return quesTwoESecond;
	}
	
	
	@Column(name="SECONDCOVER_QUES_TWO_F")
	public String getQuesTwoFSecond() {
		return quesTwoFSecond;
	}
	
	
	@Column(name="SECONDCOVER_QUES_TWO_G")
	public String getQuesTwoGSecond() {
		return quesTwoGSecond;
	}
	
	
	@Column(name="SECONDCOVER_QUES_TWO_H")
	public String getQuesTwoHSecond() {
		return quesTwoHSecond;
	}
	
	
	@Column(name="SECONDCOVER_QUES_THREE")
	public String getQuesThreeSecond() {
		return quesThreeSecond;
	}
	
	
	@Column(name="SECONDCOVER_QUES_FOUR")
	public String getQuesFourSecond() {
		return quesFourSecond;
	}
	
	
	@Column(name="SECONDCOVER_QUES_FIVE")
	public String getQuesFiveSecond() {
		return quesFiveSecond;
	}
	
	
	@Column(name="SECONDCOVER_QUES_SIX")
	public String getQuesSixSecond() {
		return quesSixSecond;
	}
	
	
	@Column(name="SECONDCOVER_QUES_SEVEN")
	public String getQuesSevenSecond() {
		return quesSevenSecond;
	}
	
	
	@Column(name="SECONDCOVER_QUES_EIGHT")
	public String getQuesEightSecond() {
		return quesEightSecond;
	}
	
	
	@Column(name="SECONDCOVER_QUES_NINE")
	public String getQuesNineSecond() {
		return quesNineSecond;
	}
	
	
	@Column(name="SECONDCOVER_QUES_TEN")
	public String getQuesTenSecond() {
		return quesTenSecond;
	}
	
	
	@Column(name="SECONDCOVER_QUES_ELEVEN")
	public String getQuesElevenSecond() {
		return quesElevenSecond;
	}
	
	
	@Column(name="SECONDCOVER_QUES_ELEVEN_A")
	public String getQuesElevenASecond() {
		return quesElevenASecond;
	}
	
	
	@Column(name="FIRSTCOVER_MEDDICAL_DET")
	public String getFirstCoverMedDetails() {
		return firstCoverMedDetails;
	}
	
	
	@Column(name="SECONDCOVER_MEDICAL_DET")
	public String getSecondCoverMedDetails() {
		return secondCoverMedDetails;
	}
	
	@Id
	@GeneratedValue
	@Column(name = "SERIAL_NO")
	public int getSerialNo() {
		return serialNo;
	}
	
	@ManyToOne
	@JoinColumn(name="SERVICE_REQUEST_NO",referencedColumnName="SERVICE_REQUEST_NO")
	public CpRequestMaster getServiceRequestNo() {
		return serviceRequestNo;
	}
	
	@Column(name = "POLICY_NO")
	public String getPolicyNo() {
		return policyNo;
	}
	
	@Column(name = "PROTECTION_BENIFIT_NAME")
	public String getBenifitRiderName() {
		return benifitRiderName;
	}
	
	@Column(name = "EXISTING_VALUE")
	public String getExistBenifit() {
		return existBenifit;
	}
	
	@Column(name = "CUMULATIVE_AMT")
	public BigDecimal getCummulativeBenifit() {
		return cummulativeBenifit;
	}
	
	@Column(name = "BENIFIT_TYPE")
	public String getBenifitType() {
		return benifitType;
	}
	
	@Column(name = "ALTERATION_TYPE")
	public String getAlterationType() {
		return alterationType;
	}
	
	@Column(name = "NEW_VALUE")
	public BigDecimal getNewvalue() {
		return newvalue;
	}
	
	@Column(name = "COVERED_BANK_NAME")
	public String getCoveredBankName() {
		return coveredBankName;
	}
	
	@Column(name = "COVERED_IBAN")
	public String getCoveredIBAN() {
		return coveredIBAN;
	}
	
	@Column(name = "COVERED_SOURCE_FUND")
	public String getCoveredSourceFund() {
		return CoveredSourceFund;
	}
	
	@Column(name = "PLANHOLDER_BANK_NAME")
	public String getPlanHolderBankName() {
		return planHolderBankName;
	}
	
	@Column(name = "PLANHOLDER_IBAN")
	public String getPlanHolderIBAN() {
		return planHolderIBAN;
	}
	
	@Column(name = "PLANHOLDER_SOURCE_FUND")
	public String getPlanHolderSourceFund() {
		return planHolderSourceFund;
	}
	
	@Column(name = "INCOME_YEAR_ONE")
	public BigDecimal getIncomeYearOne() {
		return incomeYearOne;
	}
	
	@Column(name = "INCOME_YEAR_TWO")
	public BigDecimal getIncomeYearTwo() {
		return incomeYearTwo;
	}
	
	@Column(name = "RIDER_CODE")
	public String getRiderCode() {
		return riderCode;
	}
	
	@Column(name = "INCOME_YEAR_THREE")
	public BigDecimal getIncomeYearThree() {
		return incomeYearThree;
	}
	
	@Column(name = "ASSETS_CASH")
	public BigDecimal getAssetsCash() {
		return assetsCash;
	}
	
	@Column(name = "ASSETS_SHARES")
	public BigDecimal getAssetsShares() {
		return assetsShares;
	}
	
	@Column(name = "ASSETS_REALESTATE")
	public BigDecimal getAssetsRealEstate() {
		return assetsRealEstate;
	}
	
	@Column(name = "ASSETS_OTHERS")
	public BigDecimal getAssetsOthers() {
		return assetsOthers;
	}
	
	@Column(name = "LIABILITY_LOAN")
	public BigDecimal getLiabilitiesLoan() {
		return liabilitiesLoan;
	}
	
	@Column(name = "LIABILITY_PAYABLE")
	public BigDecimal getLiabilitiesPayable() {
		return liabilitiesPayable;
	}
	
	@Column(name = "LIABILITY_MORTGAGE")
	public BigDecimal getLiabilitiesMortgage() {
		return liabilitiesMortgage;
	}
	
	@Column(name = "LIABILITY_OTHER")
	public BigDecimal getLiabilitiesOther() {
		return liabilitiesOther;
	}
	
	@Column(name = "BENIFIT_NAME")
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}
	public void setServiceRequestNo(CpRequestMaster serviceRequestNo) {
		this.serviceRequestNo = serviceRequestNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public void setBenifitRiderName(String benifitRiderName) {
		this.benifitRiderName = benifitRiderName;
	}
	public void setExistBenifit(String existBenifit) {
		this.existBenifit = existBenifit;
	}
	public void setCummulativeBenifit(BigDecimal cummulativeBenifit) {
		this.cummulativeBenifit = cummulativeBenifit;
	}
	public void setBenifitType(String benifitType) {
		this.benifitType = benifitType;
	}
	public void setAlterationType(String alterationType) {
		this.alterationType = alterationType;
	}
	public void setNewvalue(BigDecimal newvalue) {
		this.newvalue = newvalue;
	}
	public void setCoveredBankName(String coveredBankName) {
		this.coveredBankName = coveredBankName;
	}
	public void setCoveredIBAN(String coveredIBAN) {
		this.coveredIBAN = coveredIBAN;
	}
	public void setCoveredSourceFund(String coveredSourceFund) {
		CoveredSourceFund = coveredSourceFund;
	}
	public void setPlanHolderBankName(String planHolderBankName) {
		this.planHolderBankName = planHolderBankName;
	}
	public void setPlanHolderIBAN(String planHolderIBAN) {
		this.planHolderIBAN = planHolderIBAN;
	}
	public void setPlanHolderSourceFund(String planHolderSourceFund) {
		this.planHolderSourceFund = planHolderSourceFund;
	}
	public void setIncomeYearOne(BigDecimal incomeYearOne) {
		this.incomeYearOne = incomeYearOne;
	}
	public void setIncomeYearTwo(BigDecimal incomeYearTwo) {
		this.incomeYearTwo = incomeYearTwo;
	}
	public void setIncomeYearThree(BigDecimal incomeYearThree) {
		this.incomeYearThree = incomeYearThree;
	}
	public void setAssetsCash(BigDecimal assetsCash) {
		this.assetsCash = assetsCash;
	}
	public void setAssetsShares(BigDecimal assetsShares) {
		this.assetsShares = assetsShares;
	}
	public void setAssetsRealEstate(BigDecimal assetsRealEstate) {
		this.assetsRealEstate = assetsRealEstate;
	}
	public void setAssetsOthers(BigDecimal assetsOthers) {
		this.assetsOthers = assetsOthers;
	}
	public void setLiabilitiesLoan(BigDecimal liabilitiesLoan) {
		this.liabilitiesLoan = liabilitiesLoan;
	}
	public void setLiabilitiesPayable(BigDecimal liabilitiesPayable) {
		this.liabilitiesPayable = liabilitiesPayable;
	}
	public void setLiabilitiesMortgage(BigDecimal liabilitiesMortgage) {
		this.liabilitiesMortgage = liabilitiesMortgage;
	}
	public void setLiabilitiesOther(BigDecimal liabilitiesOther) {
		this.liabilitiesOther = liabilitiesOther;
	}
	
//	public void setFirstCoverWeight(String firstCoverWeight) {
//		this.firstCoverWeight = firstCoverWeight;
//	}
//	public void setSecondCoverHeight(String secondCoverHeight) {
//		this.secondCoverHeight = secondCoverHeight;
//	}
//	public void setSecondCoverWeight(String secondCoverWeight) {
//		this.secondCoverWeight = secondCoverWeight;
//	}
	public void setQuesOneFirst(String quesOneFirst) {
		this.quesOneFirst = quesOneFirst;
	}
	public void setQuesTwoAFirst(String quesTwoAFirst) {
		this.quesTwoAFirst = quesTwoAFirst;
	}
	public void setQuesTwoBFirst(String quesTwoBFirst) {
		this.quesTwoBFirst = quesTwoBFirst;
	}
	public void setQuesTwoCFirst(String quesTwoCFirst) {
		this.quesTwoCFirst = quesTwoCFirst;
	}
	public void setQuesTwoDFirst(String quesTwoDFirst) {
		this.quesTwoDFirst = quesTwoDFirst;
	}
	public void setQuesTwoEFirst(String quesTwoEFirst) {
		this.quesTwoEFirst = quesTwoEFirst;
	}
	public void setQuesTwoFFirst(String quesTwoFFirst) {
		this.quesTwoFFirst = quesTwoFFirst;
	}
	public void setQuesTwoGFirst(String quesTwoGFirst) {
		this.quesTwoGFirst = quesTwoGFirst;
	}
	public void setQuesTwoHFirst(String quesTwoHFirst) {
		this.quesTwoHFirst = quesTwoHFirst;
	}
	public void setQuesThreeFirst(String quesThreeFirst) {
		this.quesThreeFirst = quesThreeFirst;
	}
	public void setQuesFourFirst(String quesFourFirst) {
		this.quesFourFirst = quesFourFirst;
	}
	public void setQuesFiveFirst(String quesFiveFirst) {
		this.quesFiveFirst = quesFiveFirst;
	}
	public void setQuesSixFirst(String quesSixFirst) {
		this.quesSixFirst = quesSixFirst;
	}
	public void setQuesSevenFirst(String quesSevenFirst) {
		this.quesSevenFirst = quesSevenFirst;
	}
	public void setQuesEightFirst(String quesEightFirst) {
		this.quesEightFirst = quesEightFirst;
	}
	
	public void setQuesNineFirst(String quesNineFirst) {
		this.quesNineFirst = quesNineFirst;
	}
	public void setQuesTenFirst(String quesTenFirst) {
		this.quesTenFirst = quesTenFirst;
	}
	public void setQuesElevenFirst(String quesElevenFirst) {
		this.quesElevenFirst = quesElevenFirst;
	}
	public void setQuesElevenAFirst(String quesElevenAFirst) {
		this.quesElevenAFirst = quesElevenAFirst;
	}
	public void setQuesOneSecond(String quesOneSecond) {
		this.quesOneSecond = quesOneSecond;
	}
	public void setQuesTwoASecond(String quesTwoASecond) {
		this.quesTwoASecond = quesTwoASecond;
	}
	public void setQuesTwoBSecond(String quesTwoBSecond) {
		this.quesTwoBSecond = quesTwoBSecond;
	}
	
	public void setSecondCoverMedDetails(String secondCoverMedDetails) {
		this.secondCoverMedDetails = secondCoverMedDetails;
	}
	
	public void setQuesTwoCSecond(String quesTwoCSecond) {
		this.quesTwoCSecond = quesTwoCSecond;
	}
	public void setQuesTwoDSecond(String quesTwoDSecond) {
		this.quesTwoDSecond = quesTwoDSecond;
	}
	public void setQuesTwoESecond(String quesTwoESecond) {
		this.quesTwoESecond = quesTwoESecond;
	}
	public void setQuesTwoFSecond(String quesTwoFSecond) {
		this.quesTwoFSecond = quesTwoFSecond;
	}
	public void setQuesTwoGSecond(String quesTwoGSecond) {
		this.quesTwoGSecond = quesTwoGSecond;
	}
	public void setQuesTwoHSecond(String quesTwoHSecond) {
		this.quesTwoHSecond = quesTwoHSecond;
	}
	public void setQuesThreeSecond(String quesThreeSecond) {
		this.quesThreeSecond = quesThreeSecond;
	}
	public void setQuesFourSecond(String quesFourSecond) {
		this.quesFourSecond = quesFourSecond;
	}
	public void setQuesFiveSecond(String quesFiveSecond) {
		this.quesFiveSecond = quesFiveSecond;
	}
	
	public void setQuesSixSecond(String quesSixSecond) {
		this.quesSixSecond = quesSixSecond;
	}
	public void setQuesSevenSecond(String quesSevenSecond) {
		this.quesSevenSecond = quesSevenSecond;
	}
	public void setQuesEightSecond(String quesEightSecond) {
		this.quesEightSecond = quesEightSecond;
	}
	public void setQuesNineSecond(String quesNineSecond) {
		this.quesNineSecond = quesNineSecond;
	}
	public void setQuesTenSecond(String quesTenSecond) {
		this.quesTenSecond = quesTenSecond;
	}
	public void setQuesElevenSecond(String quesElevenSecond) {
		this.quesElevenSecond = quesElevenSecond;
	}
	
	public void setQuesElevenASecond(String quesElevenASecond) {
		this.quesElevenASecond = quesElevenASecond;
	}
	public void setFirstCoverMedDetails(String firstCoverMedDetails) {
		this.firstCoverMedDetails = firstCoverMedDetails;
	}
	
//	public void setFirstCoverHeight(String firstCoverHeight) {
//		this.firstCoverHeight = firstCoverHeight;
//	}
	
	public void setRiderCode(String riderCode) {
		this.riderCode = riderCode;
	}


	public String getFirstCoverHeight() {
		return firstCoverHeight;
	}


	public void setFirstCoverHeight(String firstCoverHeight) {
		this.firstCoverHeight = firstCoverHeight;
	}


	public String getFirstCoverWeight() {
		return firstCoverWeight;
	}


	public void setFirstCoverWeight(String firstCoverWeight) {
		this.firstCoverWeight = firstCoverWeight;
	}


	public String getSecondCoverHeight() {
		return secondCoverHeight;
	}


	public void setSecondCoverHeight(String secondCoverHeight) {
		this.secondCoverHeight = secondCoverHeight;
	}


	public String getSecondCoverWeight() {
		return secondCoverWeight;
	}


	public void setSecondCoverWeight(String secondCoverWeight) {
		this.secondCoverWeight = secondCoverWeight;
	}
	
	
	
	

}
