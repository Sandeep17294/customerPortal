package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ReinStatementsDTO {
 
	private int seqNo;
	

	private int serialNo;
	private ServiceRequestMasterDTO nserviceRequestNo;
	private String policyNo;
	private String productName;
	private int outstandingAmount;
	private Date issueDate;
	private Date lapseDate;
	private Date prumDate;
	private boolean status1;
	private String reinStatementFlag;
	// Attributes after modification

	private String selectedPlanName;
	private String selectedPlanNo;
	private String firstCover;
	private String secondCover;
	private Date firstCoverDob;
	private Date secondCoverDob;
	private String planCurr;
	private BigDecimal planTerm;
	private BigDecimal contribTerm;
	private String benifitType;
	private String firstCoverbenifitAmt;
	private BigDecimal firstCoverbenifitTerm;
	private String secondCoverbenifitAmt;
	private BigDecimal secondCoverbenifitTerm;
	private BigDecimal regulatContrib;
	private String contribFreq;
	private Date dueFirstContrib;
	private Date dueFinalContrib;
	private String firstCoverBankName;
	private String firstCoverIBAN;
	private String firstCoverSourceFund;
	private String secondCoverBankName;
	private String secondCoverIBAN;
	private String secondCoverSourceFund;
	private String planHoldBankName;
	private String planHoldIBAN;
	private String planHoldSourceFund;
	private BigDecimal firstCoverIncomeLast;
	private BigDecimal firstCoverIncomeSecondLast;
	private BigDecimal firstCoveredIncomeThirdLast;
	private BigDecimal secondCoverIncomeLast;
	private BigDecimal secondCoverIncomeSecondLast;
	private BigDecimal secondCoveredIncomeThirdLast;
	private BigDecimal planHoldIncomeLast;
	private BigDecimal planHoldIncomeSecondLast;
	private BigDecimal planHoldIncomeThirdLast;

	private BigDecimal firstCoverCash;
	private BigDecimal firstCoverShares;
	private BigDecimal firstCoverRealEstate;
	private BigDecimal firstCoverAssetsOthers;
	private BigDecimal firstCoverLoan;
	private BigDecimal firstCoverPayable;
	private BigDecimal firstCoverMortgage;
	private BigDecimal firstCoverOther;

	private BigDecimal secondCoverCash;
	private BigDecimal secondCoverShares;
	private BigDecimal secondCoverRealEstate;
	private BigDecimal secondCoverAssetsOthers;
	private BigDecimal secondCoverLoan;
	private BigDecimal secondCoverPayable;
	private BigDecimal secondCoverMortgage;
	private BigDecimal secondCoverOther;

	private BigDecimal planHoldCash;
	private BigDecimal planHoldShares;
	private BigDecimal planHoldRealEstate;
	private BigDecimal planHoldAssetsOthers;
	private BigDecimal planHoldLoan;
	private BigDecimal planHoldPayable;
	private BigDecimal planHoldMortgage;
	private BigDecimal planHoldOther;

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

	// getters and setters

	public int getSerialNo() {
		return serialNo;
	}

	public Date getPrumDate() {
		return prumDate;
	}

	public void setPrumDate(Date prumDate) {
		this.prumDate = prumDate;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public ServiceRequestMasterDTO getNserviceRequestNo() {
		return nserviceRequestNo;
	}

	public void setNserviceRequestNo(ServiceRequestMasterDTO nserviceRequestNo) {
		this.nserviceRequestNo = nserviceRequestNo;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getOutstandingAmount() {
		return outstandingAmount;
	}

	public void setOutstandingAmount(int outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getLapseDate() {
		return lapseDate;
	}

	public void setLapseDate(Date lapseDate) {
		this.lapseDate = lapseDate;
	}

	public boolean isStatus1() {
		return status1;
	}

	public void setStatus1(boolean status1) {
		this.status1 = status1;
	}

	// getters and setters After Modification

	public String getSelectedPlanName() {
		return selectedPlanName;
	}

	public String getSelectedPlanNo() {
		return selectedPlanNo;
	}

	public String getFirstCover() {
		return firstCover;
	}

	public String getSecondCover() {
		return secondCover;
	}

	public Date getFirstCoverDob() {
		return firstCoverDob;
	}

	public Date getSecondCoverDob() {
		return secondCoverDob;
	}

	public String getPlanCurr() {
		return planCurr;
	}

	public BigDecimal getPlanTerm() {
		return planTerm;
	}

	public BigDecimal getContribTerm() {
		return contribTerm;
	}

	public String getBenifitType() {
		return benifitType;
	}

	public BigDecimal getFirstCoverbenifitTerm() {
		return firstCoverbenifitTerm;
	}

	public BigDecimal getSecondCoverbenifitTerm() {
		return secondCoverbenifitTerm;
	}

	public BigDecimal getRegulatContrib() {
		return regulatContrib;
	}

	public String getContribFreq() {
		return contribFreq;
	}

	public Date getDueFirstContrib() {
		return dueFirstContrib;
	}

	public Date getDueFinalContrib() {
		return dueFinalContrib;
	}

	public String getFirstCoverBankName() {
		return firstCoverBankName;
	}

	public String getFirstCoverIBAN() {
		return firstCoverIBAN;
	}

	public String getFirstCoverSourceFund() {
		return firstCoverSourceFund;
	}

	public String getSecondCoverBankName() {
		return secondCoverBankName;
	}

	public String getSecondCoverIBAN() {
		return secondCoverIBAN;
	}

	public String getSecondCoverSourceFund() {
		return secondCoverSourceFund;
	}

	public String getPlanHoldBankName() {
		return planHoldBankName;
	}

	public String getPlanHoldIBAN() {
		return planHoldIBAN;
	}

	public String getPlanHoldSourceFund() {
		return planHoldSourceFund;
	}

	public BigDecimal getFirstCoverIncomeLast() {
		return firstCoverIncomeLast;
	}

	public BigDecimal getFirstCoverIncomeSecondLast() {
		return firstCoverIncomeSecondLast;
	}

	public BigDecimal getFirstCoveredIncomeThirdLast() {
		return firstCoveredIncomeThirdLast;
	}

	public BigDecimal getSecondCoverIncomeLast() {
		return secondCoverIncomeLast;
	}

	public BigDecimal getSecondCoverIncomeSecondLast() {
		return secondCoverIncomeSecondLast;
	}

	public BigDecimal getSecondCoveredIncomeThirdLast() {
		return secondCoveredIncomeThirdLast;
	}

	public BigDecimal getPlanHoldIncomeLast() {
		return planHoldIncomeLast;
	}

	public BigDecimal getPlanHoldIncomeSecondLast() {
		return planHoldIncomeSecondLast;
	}

	public BigDecimal getPlanHoldIncomeThirdLast() {
		return planHoldIncomeThirdLast;
	}

	public BigDecimal getFirstCoverCash() {
		return firstCoverCash;
	}

	public BigDecimal getFirstCoverShares() {
		return firstCoverShares;
	}

	public BigDecimal getFirstCoverRealEstate() {
		return firstCoverRealEstate;
	}

	public BigDecimal getFirstCoverLoan() {
		return firstCoverLoan;
	}

	public BigDecimal getFirstCoverPayable() {
		return firstCoverPayable;
	}

	public BigDecimal getFirstCoverMortgage() {
		return firstCoverMortgage;
	}

	public BigDecimal getFirstCoverOther() {
		return firstCoverOther;
	}

	public BigDecimal getSecondCoverCash() {
		return secondCoverCash;
	}

	public BigDecimal getSecondCoverShares() {
		return secondCoverShares;
	}

	public BigDecimal getSecondCoverRealEstate() {
		return secondCoverRealEstate;
	}

	public BigDecimal getSecondCoverLoan() {
		return secondCoverLoan;
	}

	public BigDecimal getSecondCoverPayable() {
		return secondCoverPayable;
	}

	public BigDecimal getSecondCoverMortgage() {
		return secondCoverMortgage;
	}

	public BigDecimal getSecondCoverOther() {
		return secondCoverOther;
	}

	public String getFirstCoverHeight() {
		return firstCoverHeight;
	}

	public String getFirstCoverWeight() {
		return firstCoverWeight;
	}

	public String getSecondCoverHeight() {
		return secondCoverHeight;
	}

	public String getSecondCoverWeight() {
		return secondCoverWeight;
	}

	public void setSelectedPlanName(String selectedPlanName) {
		this.selectedPlanName = selectedPlanName;
	}

	public void setSelectedPlanNo(String selectedPlanNo) {
		this.selectedPlanNo = selectedPlanNo;
	}

	public void setFirstCover(String firstCover) {
		this.firstCover = firstCover;
	}

	public void setSecondCover(String secondCover) {
		this.secondCover = secondCover;
	}

	public void setFirstCoverDob(Date firstCoverDob) {
		this.firstCoverDob = firstCoverDob;
	}

	public void setSecondCoverDob(Date secondCoverDob) {
		this.secondCoverDob = secondCoverDob;
	}

	public void setPlanCurr(String planCurr) {
		this.planCurr = planCurr;
	}

	public void setPlanTerm(BigDecimal planTerm) {
		this.planTerm = planTerm;
	}

	public void setContribTerm(BigDecimal contribTerm) {
		this.contribTerm = contribTerm;
	}

	public void setBenifitType(String benifitType) {
		this.benifitType = benifitType;
	}

	public void setFirstCoverbenifitTerm(BigDecimal firstCoverbenifitTerm) {
		this.firstCoverbenifitTerm = firstCoverbenifitTerm;
	}

	public void setSecondCoverbenifitTerm(BigDecimal secondCoverbenifitTerm) {
		this.secondCoverbenifitTerm = secondCoverbenifitTerm;
	}

	public void setRegulatContrib(BigDecimal regulatContrib) {
		this.regulatContrib = regulatContrib;
	}

	public void setContribFreq(String contribFreq) {
		this.contribFreq = contribFreq;
	}

	public void setDueFirstContrib(Date dueFirstContrib) {
		this.dueFirstContrib = dueFirstContrib;
	}

	public void setDueFinalContrib(Date dueFinalContrib) {
		this.dueFinalContrib = dueFinalContrib;
	}

	public void setFirstCoverBankName(String firstCoverBankName) {
		this.firstCoverBankName = firstCoverBankName;
	}

	public void setFirstCoverIBAN(String firstCoverIBAN) {
		this.firstCoverIBAN = firstCoverIBAN;
	}

	public void setFirstCoverSourceFund(String firstCoverSourceFund) {
		this.firstCoverSourceFund = firstCoverSourceFund;
	}

	public void setSecondCoverBankName(String secondCoverBankName) {
		this.secondCoverBankName = secondCoverBankName;
	}

	public void setSecondCoverIBAN(String secondCoverIBAN) {
		this.secondCoverIBAN = secondCoverIBAN;
	}

	public void setSecondCoverSourceFund(String secondCoverSourceFund) {
		this.secondCoverSourceFund = secondCoverSourceFund;
	}

	public void setPlanHoldBankName(String planHoldBankName) {
		this.planHoldBankName = planHoldBankName;
	}

	public void setPlanHoldIBAN(String planHoldIBAN) {
		this.planHoldIBAN = planHoldIBAN;
	}

	public void setPlanHoldSourceFund(String planHoldSourceFund) {
		this.planHoldSourceFund = planHoldSourceFund;
	}

	public void setFirstCoverIncomeLast(BigDecimal firstCoverIncomeLast) {
		this.firstCoverIncomeLast = firstCoverIncomeLast;
	}

	public void setFirstCoverIncomeSecondLast(BigDecimal firstCoverIncomeSecondLast) {
		this.firstCoverIncomeSecondLast = firstCoverIncomeSecondLast;
	}

	public void setFirstCoveredIncomeThirdLast(BigDecimal firstCoveredIncomeThirdLast) {
		this.firstCoveredIncomeThirdLast = firstCoveredIncomeThirdLast;
	}

	public void setSecondCoverIncomeLast(BigDecimal secondCoverIncomeLast) {
		this.secondCoverIncomeLast = secondCoverIncomeLast;
	}

	public void setSecondCoverIncomeSecondLast(BigDecimal secondCoverIncomeSecondLast) {
		this.secondCoverIncomeSecondLast = secondCoverIncomeSecondLast;
	}

	public void setSecondCoveredIncomeThirdLast(BigDecimal secondCoveredIncomeThirdLast) {
		this.secondCoveredIncomeThirdLast = secondCoveredIncomeThirdLast;
	}

	public void setPlanHoldIncomeLast(BigDecimal planHoldIncomeLast) {
		this.planHoldIncomeLast = planHoldIncomeLast;
	}

	public void setPlanHoldIncomeSecondLast(BigDecimal planHoldIncomeSecondLast) {
		this.planHoldIncomeSecondLast = planHoldIncomeSecondLast;
	}

	public void setPlanHoldIncomeThirdLast(BigDecimal planHoldIncomeThirdLast) {
		this.planHoldIncomeThirdLast = planHoldIncomeThirdLast;
	}

	public void setFirstCoverCash(BigDecimal firstCoverCash) {
		this.firstCoverCash = firstCoverCash;
	}

	public void setFirstCoverShares(BigDecimal firstCoverShares) {
		this.firstCoverShares = firstCoverShares;
	}

	public void setFirstCoverRealEstate(BigDecimal firstCoverRealEstate) {
		this.firstCoverRealEstate = firstCoverRealEstate;
	}

	public void setFirstCoverLoan(BigDecimal firstCoverLoan) {
		this.firstCoverLoan = firstCoverLoan;
	}

	public void setFirstCoverPayable(BigDecimal firstCoverPayable) {
		this.firstCoverPayable = firstCoverPayable;
	}

	public void setFirstCoverMortgage(BigDecimal firstCoverMortgage) {
		this.firstCoverMortgage = firstCoverMortgage;
	}

	public void setFirstCoverOther(BigDecimal firstCoverOther) {
		this.firstCoverOther = firstCoverOther;
	}

	public void setSecondCoverCash(BigDecimal secondCoverCash) {
		this.secondCoverCash = secondCoverCash;
	}

	public void setSecondCoverShares(BigDecimal secondCoverShares) {
		this.secondCoverShares = secondCoverShares;
	}

	public void setSecondCoverRealEstate(BigDecimal secondCoverRealEstate) {
		this.secondCoverRealEstate = secondCoverRealEstate;
	}

	public void setSecondCoverLoan(BigDecimal secondCoverLoan) {
		this.secondCoverLoan = secondCoverLoan;
	}

	public void setSecondCoverPayable(BigDecimal secondCoverPayable) {
		this.secondCoverPayable = secondCoverPayable;
	}

	public void setSecondCoverMortgage(BigDecimal secondCoverMortgage) {
		this.secondCoverMortgage = secondCoverMortgage;
	}

	public void setSecondCoverOther(BigDecimal secondCoverOther) {
		this.secondCoverOther = secondCoverOther;
	}

	public void setFirstCoverHeight(String firstCoverHeight) {
		this.firstCoverHeight = firstCoverHeight;
	}

	public void setFirstCoverWeight(String firstCoverWeight) {
		this.firstCoverWeight = firstCoverWeight;
	}

	public void setSecondCoverHeight(String secondCoverHeight) {
		this.secondCoverHeight = secondCoverHeight;
	}

	public void setSecondCoverWeight(String secondCoverWeight) {
		this.secondCoverWeight = secondCoverWeight;
	}

	public BigDecimal getFirstCoverAssetsOthers() {
		return firstCoverAssetsOthers;
	}

	public void setFirstCoverAssetsOthers(BigDecimal firstCoverAssetsOthers) {
		this.firstCoverAssetsOthers = firstCoverAssetsOthers;
	}

	public BigDecimal getSecondCoverAssetsOthers() {
		return secondCoverAssetsOthers;
	}

	public void setSecondCoverAssetsOthers(BigDecimal secondCoverAssetsOthers) {
		this.secondCoverAssetsOthers = secondCoverAssetsOthers;
	}

	public BigDecimal getPlanHoldCash() {
		return planHoldCash;
	}

	public BigDecimal getPlanHoldShares() {
		return planHoldShares;
	}

	public BigDecimal getPlanHoldRealEstate() {
		return planHoldRealEstate;
	}

	public BigDecimal getPlanHoldAssetsOthers() {
		return planHoldAssetsOthers;
	}

	public BigDecimal getPlanHoldLoan() {
		return planHoldLoan;
	}

	public BigDecimal getPlanHoldPayable() {
		return planHoldPayable;
	}

	public BigDecimal getPlanHoldMortgage() {
		return planHoldMortgage;
	}

	public BigDecimal getPlanHoldOther() {
		return planHoldOther;
	}

	public void setPlanHoldCash(BigDecimal planHoldCash) {
		this.planHoldCash = planHoldCash;
	}

	public void setPlanHoldShares(BigDecimal planHoldShares) {
		this.planHoldShares = planHoldShares;
	}

	public void setPlanHoldRealEstate(BigDecimal planHoldRealEstate) {
		this.planHoldRealEstate = planHoldRealEstate;
	}

	public void setPlanHoldAssetsOthers(BigDecimal planHoldAssetsOthers) {
		this.planHoldAssetsOthers = planHoldAssetsOthers;
	}

	public void setPlanHoldLoan(BigDecimal planHoldLoan) {
		this.planHoldLoan = planHoldLoan;
	}

	public void setPlanHoldPayable(BigDecimal planHoldPayable) {
		this.planHoldPayable = planHoldPayable;
	}

	public void setPlanHoldMortgage(BigDecimal planHoldMortgage) {
		this.planHoldMortgage = planHoldMortgage;
	}

	public void setPlanHoldOther(BigDecimal planHoldOther) {
		this.planHoldOther = planHoldOther;
	}

	public String getFirstCoverbenifitAmt() {
		return firstCoverbenifitAmt;
	}

	public String getSecondCoverbenifitAmt() {
		return secondCoverbenifitAmt;
	}

	public void setFirstCoverbenifitAmt(String firstCoverbenifitAmt) {
		this.firstCoverbenifitAmt = firstCoverbenifitAmt;
	}

	public void setSecondCoverbenifitAmt(String secondCoverbenifitAmt) {
		this.secondCoverbenifitAmt = secondCoverbenifitAmt;
	}

	public String getQuesTwoAFirst() {
		return quesTwoAFirst;
	}

	public String getQuesTwoBFirst() {
		return quesTwoBFirst;
	}

	public String getQuesTwoCFirst() {
		return quesTwoCFirst;
	}

	public String getQuesTwoDFirst() {
		return quesTwoDFirst;
	}

	public String getQuesTwoEFirst() {
		return quesTwoEFirst;
	}

	public String getQuesTwoFFirst() {
		return quesTwoFFirst;
	}

	public String getQuesTwoGFirst() {
		return quesTwoGFirst;
	}

	public String getQuesTwoHFirst() {
		return quesTwoHFirst;
	}

	public String getQuesThreeFirst() {
		return quesThreeFirst;
	}

	public String getQuesFourFirst() {
		return quesFourFirst;
	}

	public String getQuesFiveFirst() {
		return quesFiveFirst;
	}

	public String getQuesSixFirst() {
		return quesSixFirst;
	}

	public String getQuesSevenFirst() {
		return quesSevenFirst;
	}

	public String getQuesEightFirst() {
		return quesEightFirst;
	}

	public String getQuesNineFirst() {
		return quesNineFirst;
	}

	public String getQuesTenFirst() {
		return quesTenFirst;
	}

	public String getQuesElevenFirst() {
		return quesElevenFirst;
	}

	public String getQuesElevenAFirst() {
		return quesElevenAFirst;
	}

	public String getQuesTwoASecond() {
		return quesTwoASecond;
	}

	public String getQuesTwoBSecond() {
		return quesTwoBSecond;
	}

	public String getQuesTwoCSecond() {
		return quesTwoCSecond;
	}

	public String getQuesTwoDSecond() {
		return quesTwoDSecond;
	}

	public String getQuesTwoESecond() {
		return quesTwoESecond;
	}

	public String getQuesTwoFSecond() {
		return quesTwoFSecond;
	}

	public String getQuesTwoGSecond() {
		return quesTwoGSecond;
	}

	public String getQuesTwoHSecond() {
		return quesTwoHSecond;
	}

	public String getQuesThreeSecond() {
		return quesThreeSecond;
	}

	public String getQuesFourSecond() {
		return quesFourSecond;
	}

	public String getQuesFiveSecond() {
		return quesFiveSecond;
	}

	public String getQuesSixSecond() {
		return quesSixSecond;
	}

	public String getQuesSevenSecond() {
		return quesSevenSecond;
	}

	public String getQuesEightSecond() {
		return quesEightSecond;
	}

	public String getQuesNineSecond() {
		return quesNineSecond;
	}

	public String getQuesTenSecond() {
		return quesTenSecond;
	}

	public String getQuesElevenSecond() {
		return quesElevenSecond;
	}

	public String getQuesElevenASecond() {
		return quesElevenASecond;
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

	public void setQuesTwoASecond(String quesTwoASecond) {
		this.quesTwoASecond = quesTwoASecond;
	}

	public void setQuesTwoBSecond(String quesTwoBSecond) {
		this.quesTwoBSecond = quesTwoBSecond;
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

	public String getQuesOneFirst() {
		return quesOneFirst;
	}

	public String getQuesOneSecond() {
		return quesOneSecond;
	}

	public void setQuesOneFirst(String quesOneFirst) {
		this.quesOneFirst = quesOneFirst;
	}

	public void setQuesOneSecond(String quesOneSecond) {
		this.quesOneSecond = quesOneSecond;
	}

	public String getFirstCoverMedDetails() {
		return firstCoverMedDetails;
	}

	public String getSecondCoverMedDetails() {
		return secondCoverMedDetails;
	}

	public void setFirstCoverMedDetails(String firstCoverMedDetails) {
		this.firstCoverMedDetails = firstCoverMedDetails;
	}

	public void setSecondCoverMedDetails(String secondCoverMedDetails) {
		this.secondCoverMedDetails = secondCoverMedDetails;
	}

	public String getReinStatementFlag() {
		return reinStatementFlag;
	}

	public void setReinStatementFlag(String reinStatementFlag) {
		this.reinStatementFlag = reinStatementFlag;
	}
	public int getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}

}
