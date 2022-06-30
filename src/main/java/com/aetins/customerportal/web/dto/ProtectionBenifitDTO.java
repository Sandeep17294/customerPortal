package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;



public class ProtectionBenifitDTO {

	private ServiceRequestMasterDTO serviceRequestNo;
	private String benifitRiderName;
	private String memberName;
	private String existBenifit;
	private BigDecimal cummulativeBenifit;
	private String premiumWaive;
	private String excelGroupId;
	private String exclOrOptn;
	private boolean enableCheckBox;
	private String benifitType;
	private boolean showCheckBox;
	private boolean showRadioButton;
	private String policyNo;
	private BigDecimal newvalue;
	private String currency;
	private String amountExist;
	private String alterationType;
	private BigDecimal memberIndex;
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
	private String riderCode;
	private String relationBenefit;

	// vinod
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
	private String index;

	private boolean wocParam;

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getBenifitRiderName() {
		return benifitRiderName;
	}

	public String getExistBenifit() {
		return existBenifit;
	}

	public BigDecimal getCummulativeBenifit() {
		return cummulativeBenifit;
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

	public boolean isEnableCheckBox() {
		return enableCheckBox;
	}

	public void setEnableCheckBox(boolean enableCheckBox) {
		this.enableCheckBox = enableCheckBox;
	}

	public String getBenifitType() {
		return benifitType;
	}

	public void setBenifitType(String benifitType) {
		this.benifitType = benifitType;
	}

	public boolean isShowCheckBox() {
		return showCheckBox;
	}

	public void setShowCheckBox(boolean showCheckBox) {
		this.showCheckBox = showCheckBox;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public BigDecimal getNewvalue() {
		return newvalue;
	}

	public void setNewvalue(BigDecimal newvalue) {
		this.newvalue = newvalue;
	}

	public String getCurrency() {
		return currency;
	}

	public String getAmountExist() {
		return amountExist;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setAmountExist(String amountExist) {
		this.amountExist = amountExist;
	}

	public String getAlterationType() {
		return alterationType;
	}

	public String getCoveredBankName() {
		return coveredBankName;
	}

	public String getCoveredIBAN() {
		return coveredIBAN;
	}

	public String getCoveredSourceFund() {
		return CoveredSourceFund;
	}

	public String getPlanHolderBankName() {
		return planHolderBankName;
	}

	public String getPlanHolderIBAN() {
		return planHolderIBAN;
	}

	public String getPlanHolderSourceFund() {
		return planHolderSourceFund;
	}

	public BigDecimal getIncomeYearOne() {
		return incomeYearOne;
	}

	public BigDecimal getIncomeYearTwo() {
		return incomeYearTwo;
	}

	public BigDecimal getIncomeYearThree() {
		return incomeYearThree;
	}

	public BigDecimal getAssetsCash() {
		return assetsCash;
	}

	public BigDecimal getAssetsShares() {
		return assetsShares;
	}

	public BigDecimal getAssetsRealEstate() {
		return assetsRealEstate;
	}

	public BigDecimal getAssetsOthers() {
		return assetsOthers;
	}

	public BigDecimal getLiabilitiesLoan() {
		return liabilitiesLoan;
	}

	public BigDecimal getLiabilitiesPayable() {
		return liabilitiesPayable;
	}

	public BigDecimal getLiabilitiesMortgage() {
		return liabilitiesMortgage;
	}

	public BigDecimal getLiabilitiesOther() {
		return liabilitiesOther;
	}

	public void setAlterationType(String alterationType) {
		this.alterationType = alterationType;
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

	public ServiceRequestMasterDTO getServiceRequestNo() {
		return serviceRequestNo;
	}

	public void setServiceRequestNo(ServiceRequestMasterDTO serviceRequestNo) {
		this.serviceRequestNo = serviceRequestNo;
	}

	public BigDecimal getMemberIndex() {
		return memberIndex;
	}

	public void setMemberIndex(BigDecimal memberIndex) {
		this.memberIndex = memberIndex;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
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

	public String getQuesOneFirst() {
		return quesOneFirst;
	}

	public void setQuesOneFirst(String quesOneFirst) {
		this.quesOneFirst = quesOneFirst;
	}

	public String getQuesTwoAFirst() {
		return quesTwoAFirst;
	}

	public void setQuesTwoAFirst(String quesTwoAFirst) {
		this.quesTwoAFirst = quesTwoAFirst;
	}

	public String getQuesTwoBFirst() {
		return quesTwoBFirst;
	}

	public void setQuesTwoBFirst(String quesTwoBFirst) {
		this.quesTwoBFirst = quesTwoBFirst;
	}

	public String getQuesTwoCFirst() {
		return quesTwoCFirst;
	}

	public void setQuesTwoCFirst(String quesTwoCFirst) {
		this.quesTwoCFirst = quesTwoCFirst;
	}

	public String getQuesTwoDFirst() {
		return quesTwoDFirst;
	}

	public void setQuesTwoDFirst(String quesTwoDFirst) {
		this.quesTwoDFirst = quesTwoDFirst;
	}

	public String getQuesTwoEFirst() {
		return quesTwoEFirst;
	}

	public void setQuesTwoEFirst(String quesTwoEFirst) {
		this.quesTwoEFirst = quesTwoEFirst;
	}

	public String getQuesTwoFFirst() {
		return quesTwoFFirst;
	}

	public void setQuesTwoFFirst(String quesTwoFFirst) {
		this.quesTwoFFirst = quesTwoFFirst;
	}

	public String getQuesTwoGFirst() {
		return quesTwoGFirst;
	}

	public void setQuesTwoGFirst(String quesTwoGFirst) {
		this.quesTwoGFirst = quesTwoGFirst;
	}

	public String getQuesTwoHFirst() {
		return quesTwoHFirst;
	}

	public void setQuesTwoHFirst(String quesTwoHFirst) {
		this.quesTwoHFirst = quesTwoHFirst;
	}

	public String getQuesThreeFirst() {
		return quesThreeFirst;
	}

	public void setQuesThreeFirst(String quesThreeFirst) {
		this.quesThreeFirst = quesThreeFirst;
	}

	public String getQuesFourFirst() {
		return quesFourFirst;
	}

	public void setQuesFourFirst(String quesFourFirst) {
		this.quesFourFirst = quesFourFirst;
	}

	public String getQuesFiveFirst() {
		return quesFiveFirst;
	}

	public void setQuesFiveFirst(String quesFiveFirst) {
		this.quesFiveFirst = quesFiveFirst;
	}

	public String getQuesSixFirst() {
		return quesSixFirst;
	}

	public void setQuesSixFirst(String quesSixFirst) {
		this.quesSixFirst = quesSixFirst;
	}

	public String getQuesSevenFirst() {
		return quesSevenFirst;
	}

	public void setQuesSevenFirst(String quesSevenFirst) {
		this.quesSevenFirst = quesSevenFirst;
	}

	public String getQuesEightFirst() {
		return quesEightFirst;
	}

	public void setQuesEightFirst(String quesEightFirst) {
		this.quesEightFirst = quesEightFirst;
	}

	public String getQuesNineFirst() {
		return quesNineFirst;
	}

	public void setQuesNineFirst(String quesNineFirst) {
		this.quesNineFirst = quesNineFirst;
	}

	public String getQuesTenFirst() {
		return quesTenFirst;
	}

	public void setQuesTenFirst(String quesTenFirst) {
		this.quesTenFirst = quesTenFirst;
	}

	public String getQuesElevenFirst() {
		return quesElevenFirst;
	}

	public void setQuesElevenFirst(String quesElevenFirst) {
		this.quesElevenFirst = quesElevenFirst;
	}

	public String getQuesElevenAFirst() {
		return quesElevenAFirst;
	}

	public void setQuesElevenAFirst(String quesElevenAFirst) {
		this.quesElevenAFirst = quesElevenAFirst;
	}

	public String getQuesOneSecond() {
		return quesOneSecond;
	}

	public void setQuesOneSecond(String quesOneSecond) {
		this.quesOneSecond = quesOneSecond;
	}

	public String getQuesTwoASecond() {
		return quesTwoASecond;
	}

	public void setQuesTwoASecond(String quesTwoASecond) {
		this.quesTwoASecond = quesTwoASecond;
	}

	public String getQuesTwoBSecond() {
		return quesTwoBSecond;
	}

	public void setQuesTwoBSecond(String quesTwoBSecond) {
		this.quesTwoBSecond = quesTwoBSecond;
	}

	public String getQuesTwoCSecond() {
		return quesTwoCSecond;
	}

	public void setQuesTwoCSecond(String quesTwoCSecond) {
		this.quesTwoCSecond = quesTwoCSecond;
	}

	public String getQuesTwoDSecond() {
		return quesTwoDSecond;
	}

	public void setQuesTwoDSecond(String quesTwoDSecond) {
		this.quesTwoDSecond = quesTwoDSecond;
	}

	public String getQuesTwoESecond() {
		return quesTwoESecond;
	}

	public void setQuesTwoESecond(String quesTwoESecond) {
		this.quesTwoESecond = quesTwoESecond;
	}

	public String getQuesTwoFSecond() {
		return quesTwoFSecond;
	}

	public void setQuesTwoFSecond(String quesTwoFSecond) {
		this.quesTwoFSecond = quesTwoFSecond;
	}

	public String getQuesTwoGSecond() {
		return quesTwoGSecond;
	}

	public void setQuesTwoGSecond(String quesTwoGSecond) {
		this.quesTwoGSecond = quesTwoGSecond;
	}

	public String getQuesTwoHSecond() {
		return quesTwoHSecond;
	}

	public void setQuesTwoHSecond(String quesTwoHSecond) {
		this.quesTwoHSecond = quesTwoHSecond;
	}

	public String getQuesThreeSecond() {
		return quesThreeSecond;
	}

	public void setQuesThreeSecond(String quesThreeSecond) {
		this.quesThreeSecond = quesThreeSecond;
	}

	public String getQuesFourSecond() {
		return quesFourSecond;
	}

	public void setQuesFourSecond(String quesFourSecond) {
		this.quesFourSecond = quesFourSecond;
	}

	public String getQuesFiveSecond() {
		return quesFiveSecond;
	}

	public void setQuesFiveSecond(String quesFiveSecond) {
		this.quesFiveSecond = quesFiveSecond;
	}

	public String getQuesSixSecond() {
		return quesSixSecond;
	}

	public void setQuesSixSecond(String quesSixSecond) {
		this.quesSixSecond = quesSixSecond;
	}

	public String getQuesSevenSecond() {
		return quesSevenSecond;
	}

	public void setQuesSevenSecond(String quesSevenSecond) {
		this.quesSevenSecond = quesSevenSecond;
	}

	public String getQuesEightSecond() {
		return quesEightSecond;
	}

	public void setQuesEightSecond(String quesEightSecond) {
		this.quesEightSecond = quesEightSecond;
	}

	public String getQuesNineSecond() {
		return quesNineSecond;
	}

	public void setQuesNineSecond(String quesNineSecond) {
		this.quesNineSecond = quesNineSecond;
	}

	public String getQuesTenSecond() {
		return quesTenSecond;
	}

	public void setQuesTenSecond(String quesTenSecond) {
		this.quesTenSecond = quesTenSecond;
	}

	public String getQuesElevenSecond() {
		return quesElevenSecond;
	}

	public void setQuesElevenSecond(String quesElevenSecond) {
		this.quesElevenSecond = quesElevenSecond;
	}

	public String getQuesElevenASecond() {
		return quesElevenASecond;
	}

	public void setQuesElevenASecond(String quesElevenASecond) {
		this.quesElevenASecond = quesElevenASecond;
	}

	public String getFirstCoverMedDetails() {
		return firstCoverMedDetails;
	}

	public void setFirstCoverMedDetails(String firstCoverMedDetails) {
		this.firstCoverMedDetails = firstCoverMedDetails;
	}

	public String getSecondCoverMedDetails() {
		return secondCoverMedDetails;
	}

	public void setSecondCoverMedDetails(String secondCoverMedDetails) {
		this.secondCoverMedDetails = secondCoverMedDetails;
	}

	public String getRiderCode() {
		return riderCode;
	}

	public void setRiderCode(String riderCode) {
		this.riderCode = riderCode;
	}

	public String getPremiumWaive() {
		return premiumWaive;
	}

	public void setPremiumWaive(String premiumWaive) {
		this.premiumWaive = premiumWaive;
	}

	public String getExcelGroupId() {
		return excelGroupId;
	}

	public void setExcelGroupId(String excelGroupId) {
		this.excelGroupId = excelGroupId;
	}

	public String getExclOrOptn() {
		return exclOrOptn;
	}

	public void setExclOrOptn(String exclOrOptn) {
		this.exclOrOptn = exclOrOptn;
	}

	public boolean isShowRadioButton() {
		return showRadioButton;
	}

	public void setShowRadioButton(boolean showRadioButton) {
		this.showRadioButton = showRadioButton;
	}

	public String getRelationBenefit() {
		return relationBenefit;
	}

	public void setRelationBenefit(String relationBenefit) {
		this.relationBenefit = relationBenefit;
	}

	public boolean isWocParam() {
		return wocParam;
	}

	public void setWocParam(boolean wocParam) {
		this.wocParam = wocParam;
	}

	public ProtectionBenifitDTO getCopy(ProtectionBenifitDTO myBean) {
		ProtectionBenifitDTO copy = new ProtectionBenifitDTO();
		copy.serviceRequestNo = myBean.serviceRequestNo;
		copy.benifitRiderName = myBean.benifitRiderName;
		copy.memberName = myBean.memberName;
		copy.showRadioButton = myBean.showRadioButton;
		copy.premiumWaive = myBean.premiumWaive;
		copy.exclOrOptn = myBean.exclOrOptn;
		copy.excelGroupId = myBean.excelGroupId;
		copy.existBenifit = myBean.existBenifit;
		copy.cummulativeBenifit = myBean.cummulativeBenifit;
		copy.enableCheckBox = myBean.enableCheckBox;
		copy.benifitType = myBean.benifitType;
		copy.showCheckBox = myBean.showCheckBox;
		copy.policyNo = myBean.policyNo;
		copy.newvalue = myBean.newvalue;
		copy.currency = myBean.currency;
		copy.amountExist = myBean.amountExist;
		copy.alterationType = myBean.alterationType;
		copy.memberIndex = myBean.memberIndex;
		copy.coveredBankName = myBean.coveredBankName;
		copy.coveredIBAN = myBean.coveredIBAN;
		copy.CoveredSourceFund = myBean.CoveredSourceFund;
		copy.planHolderBankName = myBean.planHolderBankName;
		copy.planHolderIBAN = myBean.planHolderIBAN;
		copy.planHolderSourceFund = myBean.planHolderSourceFund;
		copy.incomeYearOne = myBean.incomeYearOne;
		copy.incomeYearTwo = myBean.incomeYearTwo;
		copy.incomeYearThree = myBean.incomeYearThree;
		copy.assetsCash = myBean.assetsCash;
		copy.assetsShares = myBean.assetsShares;
		copy.assetsRealEstate = myBean.assetsRealEstate;
		copy.assetsOthers = myBean.assetsOthers;
		copy.liabilitiesLoan = myBean.liabilitiesLoan;
		copy.liabilitiesPayable = myBean.liabilitiesPayable;
		copy.liabilitiesMortgage = myBean.liabilitiesMortgage;
		copy.liabilitiesOther = myBean.liabilitiesOther;
		copy.riderCode = myBean.riderCode;
		copy.firstCoverHeight = myBean.firstCoverHeight;
		copy.firstCoverWeight = myBean.firstCoverWeight;
		copy.secondCoverHeight = myBean.secondCoverHeight;
		copy.secondCoverWeight = myBean.secondCoverWeight;
		copy.quesOneFirst = myBean.quesOneFirst;
		copy.quesTwoAFirst = myBean.quesTwoAFirst;
		copy.quesTwoBFirst = myBean.quesTwoBFirst;
		copy.quesTwoCFirst = myBean.quesTwoCFirst;
		copy.quesTwoDFirst = myBean.quesTwoDFirst;
		copy.quesTwoEFirst = myBean.quesTwoEFirst;
		copy.quesTwoFFirst = myBean.quesTwoFFirst;
		copy.quesTwoGFirst = myBean.quesTwoGFirst;
		copy.quesTwoHFirst = myBean.quesTwoHFirst;
		copy.quesThreeFirst = myBean.quesThreeFirst;
		copy.quesFourFirst = myBean.quesFourFirst;
		copy.quesFiveFirst = myBean.quesFiveFirst;
		copy.quesSixFirst = myBean.quesSixFirst;
		copy.quesSevenFirst = myBean.quesSevenFirst;
		copy.quesEightFirst = myBean.quesEightFirst;
		copy.quesNineFirst = myBean.quesNineFirst;
		copy.quesTenFirst = myBean.quesTenFirst;
		copy.quesElevenFirst = myBean.quesElevenFirst;
		copy.quesElevenAFirst = myBean.quesElevenAFirst;
		copy.quesOneSecond = myBean.quesOneSecond;
		copy.quesTwoASecond = myBean.quesTwoASecond;
		copy.quesTwoBSecond = myBean.quesTwoBSecond;
		copy.quesTwoCSecond = myBean.quesTwoCSecond;
		copy.quesTwoDSecond = myBean.quesTwoDSecond;
		copy.quesTwoESecond = myBean.quesTwoESecond;
		copy.quesTwoFSecond = myBean.quesTwoFSecond;
		copy.quesTwoGSecond = myBean.quesTwoGSecond;
		copy.quesTwoHSecond = myBean.quesTwoHSecond;
		copy.quesThreeSecond = myBean.quesThreeSecond;
		copy.quesFourSecond = myBean.quesFourSecond;
		copy.quesFiveSecond = myBean.quesFiveSecond;
		copy.quesSixSecond = myBean.quesSixSecond;
		copy.quesSevenSecond = myBean.quesSevenSecond;
		copy.quesEightSecond = myBean.quesEightSecond;
		copy.quesNineSecond = myBean.quesNineSecond;
		copy.quesTenSecond = myBean.quesTenSecond;
		copy.quesElevenSecond = myBean.quesElevenSecond;
		copy.quesElevenASecond = myBean.quesElevenASecond;
		copy.firstCoverMedDetails = myBean.firstCoverMedDetails;
		copy.secondCoverMedDetails = myBean.secondCoverMedDetails;
		copy.index = myBean.index;
		copy.relationBenefit = myBean.relationBenefit;
		copy.wocParam = myBean.wocParam;

		return copy;
	}

}
