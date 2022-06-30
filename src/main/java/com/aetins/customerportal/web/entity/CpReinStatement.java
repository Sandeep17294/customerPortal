package com.aetins.customerportal.web.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CP_REIN_STATEMENT")
public class CpReinStatement implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int serialNo;
	private CpRequestMaster nServicRequestNo;
	private String policyNo;
	private String productName;
	private int outstandingAmount;
	private Date issueDate;
	private Date lapseDate;
	private Date prumDate;
	private String reinStatementFlag;

	// After modification

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
	private List<CpReinStatementBenefit> cpReinStatementBenefit;
	// getter

	@Id
	@GenericGenerator(name = "gen", strategy = "increment")
	@GeneratedValue(generator = "gen")
	@Column(name = "SERIALNO", unique = true, nullable = false, precision = 15, scale = 0)
	public int getSerialNo() {
		return serialNo;
	}
	@OneToMany( mappedBy = "serialNo",targetEntity=CpReinStatementBenefit.class,cascade=CascadeType.ALL)
	public List<CpReinStatementBenefit> getCpReinStatementBenefit() {
		return cpReinStatementBenefit;
	}

	

	@ManyToOne
	@JoinColumn(name = "N_SERVICE_REQUEST_NO", referencedColumnName = "SERVICE_REQUEST_NO")
	public CpRequestMaster getnServicRequestNo() {
		return nServicRequestNo;
	}

	public void setServiceRequestNo(CpRequestMaster request) {
		this.nServicRequestNo = request;
	}
	
	public void setnServicRequestNo(CpRequestMaster nServicRequestNo) {
		this.nServicRequestNo = nServicRequestNo;
	}
	
	
	@Column(name = "POLICY_NO")
	public String getPolicyNo() {
		return policyNo;
	}

	@Column(name = "PRODUCT_NAME")
	public String getProductName() {
		return productName;
	}

	@Column(name = "OUTSTANDING_AMOUNT")
	public int getOutstandingAmount() {
		return outstandingAmount;
	}

	@Column(name = "ISSUE_DATE")
	public Date getIssueDate() {
		return issueDate;
	}

	@Column(name = "LAPSE_DATE")
	public Date getLapseDate() {
		return lapseDate;
	}

	@Column(name = "PRUM_DATE")
	public Date getPrumDate() {
		return prumDate;
	}

	@Column(name = "FIRST_COVER_NAME")
	public String getFirstCover() {
		return firstCover;
	}

	@Column(name = "SECOND_COVER_NAME")
	public String getSecondCover() {
		return secondCover;
	}

	@Column(name = "FIRST_COVER_DOB")
	public Date getFirstCoverDob() {
		return firstCoverDob;
	}

	@Column(name = "SECOND_COVER_DOB")
	public Date getSecondCoverDob() {
		return secondCoverDob;
	}

	@Column(name = "PLAN_CUURR")
	public String getPlanCurr() {
		return planCurr;
	}

	@Column(name = "PLAN_TERM")
	public BigDecimal getPlanTerm() {
		return planTerm;
	}

	@Column(name = "CONTRIBUTION_TERM")
	public BigDecimal getContribTerm() {
		return contribTerm;
	}

	@Column(name = "BENIFIT_TYPE")
	public String getBenifitType() {
		return benifitType;
	}

	@Column(name = "FIRSTCOVER_BENIFIT_AMOUNT")
	public String getFirstCoverbenifitAmt() {
		return firstCoverbenifitAmt;
	}

	@Column(name = "FIRSTCOVER_BENIFIT_TERM")
	public BigDecimal getFirstCoverbenifitTerm() {
		return firstCoverbenifitTerm;
	}

	@Column(name = "SECONDCOVER_BENIFIT_AMOUNT")
	public String getSecondCoverbenifitAmt() {
		return secondCoverbenifitAmt;
	}

	@Column(name = "SECONDCOVER_BENIFIT_TERM")
	public BigDecimal getSecondCoverbenifitTerm() {
		return secondCoverbenifitTerm;
	}

	@Column(name = "REGULAR_CONTRIB")
	public BigDecimal getRegulatContrib() {
		return regulatContrib;
	}

	@Column(name = "CONTRIB_FREQUENCY")
	public String getContribFreq() {
		return contribFreq;
	}

	@Column(name = "FIRST_CONTRIB_DUEDATE")
	public Date getDueFirstContrib() {
		return dueFirstContrib;
	}

	@Column(name = "FINAL_CONTRIB_DUEDATE")
	public Date getDueFinalContrib() {
		return dueFinalContrib;
	}

	@Column(name = "FIRSTCOVER_BANK_NAME")
	public String getFirstCoverBankName() {
		return firstCoverBankName;
	}

	@Column(name = "FIRSTCOVER_IBAN")
	public String getFirstCoverIBAN() {
		return firstCoverIBAN;
	}

	@Column(name = "FIRSTCOVER_FUND_SOURCE")
	public String getFirstCoverSourceFund() {
		return firstCoverSourceFund;
	}

	@Column(name = "SECONDCOVER_BANK_NAME")
	public String getSecondCoverBankName() {
		return secondCoverBankName;
	}

	@Column(name = "SECONDCOVER_IBAN")
	public String getSecondCoverIBAN() {
		return secondCoverIBAN;
	}

	@Column(name = "SECONDCOVER_FUND_SOURCE")
	public String getSecondCoverSourceFund() {
		return secondCoverSourceFund;
	}

	@Column(name = "PLANHOLDER_BANK_NAME")
	public String getPlanHoldBankName() {
		return planHoldBankName;
	}

	@Column(name = "PLANHOLDER_IBAN")
	public String getPlanHoldIBAN() {
		return planHoldIBAN;
	}

	@Column(name = "PLANHOLDER_FUND_SOURCE")
	public String getPlanHoldSourceFund() {
		return planHoldSourceFund;
	}

	@Column(name = "FIRSTCOVER_INCOME_YRONE")
	public BigDecimal getFirstCoverIncomeLast() {
		return firstCoverIncomeLast;
	}

	@Column(name = "FIRSTCOVER_INCOME_YRTWO")
	public BigDecimal getFirstCoverIncomeSecondLast() {
		return firstCoverIncomeSecondLast;
	}

	@Column(name = "FIRSTCOVER_INCOME_YRTHREE")
	public BigDecimal getFirstCoveredIncomeThirdLast() {
		return firstCoveredIncomeThirdLast;
	}

	@Column(name = "SECONDCOVER_INCOME_YRONE")
	public BigDecimal getSecondCoverIncomeLast() {
		return secondCoverIncomeLast;
	}

	@Column(name = "SECONDCOVER_INCOME_YRTWO")
	public BigDecimal getSecondCoverIncomeSecondLast() {
		return secondCoverIncomeSecondLast;
	}

	@Column(name = "SECONDCOVER_INCOME_YRTHREE")
	public BigDecimal getSecondCoveredIncomeThirdLast() {
		return secondCoveredIncomeThirdLast;
	}

	@Column(name = "PLANHOLDER_INCOME_YRONE")
	public BigDecimal getPlanHoldIncomeLast() {
		return planHoldIncomeLast;
	}

	@Column(name = "PLANHOLDER_INCOME_YRTWO")
	public BigDecimal getPlanHoldIncomeSecondLast() {
		return planHoldIncomeSecondLast;
	}

	@Column(name = "PLANHOLDER_INCOME_YRTHREE")
	public BigDecimal getPlanHoldIncomeThirdLast() {
		return planHoldIncomeThirdLast;
	}

	@Column(name = "FIRSTCOVER_CASH")
	public BigDecimal getFirstCoverCash() {
		return firstCoverCash;
	}

	@Column(name = "FIRSTCOVER_SHARE")
	public BigDecimal getFirstCoverShares() {
		return firstCoverShares;
	}

	@Column(name = "FIRSTCOVER_REAL_ESTATE")
	public BigDecimal getFirstCoverRealEstate() {
		return firstCoverRealEstate;
	}

	@Column(name = "FIRSTCOVER_OTHER_ASSETS")
	public BigDecimal getFirstCoverAssetsOthers() {
		return firstCoverAssetsOthers;
	}

	@Column(name = "FIRSTCOVER_LOANS")
	public BigDecimal getFirstCoverLoan() {
		return firstCoverLoan;
	}

	@Column(name = "FIRSTCOVER_ACCNT_PAYABLE")
	public BigDecimal getFirstCoverPayable() {
		return firstCoverPayable;
	}

	@Column(name = "FIRSTCOVER_MORTGAGE")
	public BigDecimal getFirstCoverMortgage() {
		return firstCoverMortgage;
	}

	@Column(name = "FIRSTCOVER_OTHER_LOANS")
	public BigDecimal getFirstCoverOther() {
		return firstCoverOther;
	}

	@Column(name = "SECONDCOVER_CASH")
	public BigDecimal getSecondCoverCash() {
		return secondCoverCash;
	}

	@Column(name = "SECONDCOVER_SHARE")
	public BigDecimal getSecondCoverShares() {
		return secondCoverShares;
	}

	@Column(name = "SECONDCOVER_REAL_ESTATE")
	public BigDecimal getSecondCoverRealEstate() {
		return secondCoverRealEstate;
	}

	@Column(name = "SECONDCOVER_OTHER_ASSETS")
	public BigDecimal getSecondCoverAssetsOthers() {
		return secondCoverAssetsOthers;
	}

	@Column(name = "SECONDCOVER_LOANS")
	public BigDecimal getSecondCoverLoan() {
		return secondCoverLoan;
	}

	@Column(name = "SECONDCOVER_ACCNT_PAYABLE")
	public BigDecimal getSecondCoverPayable() {
		return secondCoverPayable;
	}

	@Column(name = "SECONDCOVER_MORTGAGE")
	public BigDecimal getSecondCoverMortgage() {
		return secondCoverMortgage;
	}

	@Column(name = "SECONDCOVER_OTHER_LOANS")
	public BigDecimal getSecondCoverOther() {
		return secondCoverOther;
	}

	@Column(name = "PLANHOLDER_CASH")
	public BigDecimal getPlanHoldCash() {
		return planHoldCash;
	}

	@Column(name = "PLANHOLDER_SHARE")
	public BigDecimal getPlanHoldShares() {
		return planHoldShares;
	}

	@Column(name = "PLANHOLDER_REALESTATE")
	public BigDecimal getPlanHoldRealEstate() {
		return planHoldRealEstate;
	}

	@Column(name = "PLANHOLDER_OTHER_ASSETS")
	public BigDecimal getPlanHoldAssetsOthers() {
		return planHoldAssetsOthers;
	}

	@Column(name = "PLANHOLDER_LOANS")
	public BigDecimal getPlanHoldLoan() {
		return planHoldLoan;
	}

	@Column(name = "PLANHOLDER_ACCNT_PAYABLE")
	public BigDecimal getPlanHoldPayable() {
		return planHoldPayable;
	}

	@Column(name = "PLANHOLDER_MORTGAGE")
	public BigDecimal getPlanHoldMortgage() {
		return planHoldMortgage;
	}

	@Column(name = "PLANHOLDER_OTHER_LOANS")
	public BigDecimal getPlanHoldOther() {
		return planHoldOther;
	}

	@Column(name = "FIRSTCOVER_QUES_ONE")
	public String getQuesOneFirst() {
		return quesOneFirst;
	}

	@Column(name = "FIRSTCOVER_QUES_TWO_A")
	public String getQuesTwoAFirst() {
		return quesTwoAFirst;
	}

	@Column(name = "FIRSTCOVER_QUES_TWO_B")
	public String getQuesTwoBFirst() {
		return quesTwoBFirst;
	}

	@Column(name = "FIRSTCOVER_QUES_TWO_C")
	public String getQuesTwoCFirst() {
		return quesTwoCFirst;
	}

	@Column(name = "FIRSTCOVER_QUES_TWO_D")
	public String getQuesTwoDFirst() {
		return quesTwoDFirst;
	}

	@Column(name = "FIRSTCOVER_QUES_TWO_E")
	public String getQuesTwoEFirst() {
		return quesTwoEFirst;
	}

	@Column(name = "FIRSTCOVER_QUES_TWO_F")
	public String getQuesTwoFFirst() {
		return quesTwoFFirst;
	}

	@Column(name = "FIRSTCOVER_QUES_TWO_G")
	public String getQuesTwoGFirst() {
		return quesTwoGFirst;
	}

	@Column(name = "FIRSTCOVER_QUES_TWO_H")
	public String getQuesTwoHFirst() {
		return quesTwoHFirst;
	}

	@Column(name = "FIRSTCOVER_QUES_THREE")
	public String getQuesThreeFirst() {
		return quesThreeFirst;
	}

	@Column(name = "FIRSTCOVER_QUES_FOUR")
	public String getQuesFourFirst() {
		return quesFourFirst;
	}

	@Column(name = "FIRSTCOVER_QUES_FIVE")
	public String getQuesFiveFirst() {
		return quesFiveFirst;
	}

	@Column(name = "FIRSTCOVER_QUES_SIX")
	public String getQuesSixFirst() {
		return quesSixFirst;
	}

	@Column(name = "FIRSTCOVER_QUES_SEVEN")
	public String getQuesSevenFirst() {
		return quesSevenFirst;
	}

	@Column(name = "FIRSTCOVER_QUES_EIGHT")
	public String getQuesEightFirst() {
		return quesEightFirst;
	}

	@Column(name = "FIRSTCOVER_QUES_NINE")
	public String getQuesNineFirst() {
		return quesNineFirst;
	}

	@Column(name = "FIRSTCOVER_QUES_TEN")
	public String getQuesTenFirst() {
		return quesTenFirst;
	}

	@Column(name = "FIRSTCOVER_QUES_ELEVEN")
	public String getQuesElevenFirst() {
		return quesElevenFirst;
	}

	@Column(name = "FIRSTCOVER_QUES_ELEVEN_A")
	public String getQuesElevenAFirst() {
		return quesElevenAFirst;
	}

	@Column(name = "SECONDCOVER_QUES_ONE")
	public String getQuesOneSecond() {
		return quesOneSecond;
	}

	@Column(name = "SECONDCOVER_QUES_TWO_A")
	public String getQuesTwoASecond() {
		return quesTwoASecond;
	}

	@Column(name = "SECONDCOVER_QUES_TWO_B")
	public String getQuesTwoBSecond() {
		return quesTwoBSecond;
	}

	@Column(name = "SECONDCOVER_QUES_TWO_C")
	public String getQuesTwoCSecond() {
		return quesTwoCSecond;
	}

	@Column(name = "SECONDCOVER_QUES_TWO_D")
	public String getQuesTwoDSecond() {
		return quesTwoDSecond;
	}

	@Column(name = "SECONDCOVER_QUES_TWO_E")
	public String getQuesTwoESecond() {
		return quesTwoESecond;
	}

	@Column(name = "SECONDCOVER_QUES_TWO_F")
	public String getQuesTwoFSecond() {
		return quesTwoFSecond;
	}

	@Column(name = "SECONDCOVER_QUES_TWO_G")
	public String getQuesTwoGSecond() {
		return quesTwoGSecond;
	}

	@Column(name = "SECONDCOVER_QUES_TWO_H")
	public String getQuesTwoHSecond() {
		return quesTwoHSecond;
	}

	@Column(name = "SECONDCOVER_QUES_THREE")
	public String getQuesThreeSecond() {
		return quesThreeSecond;
	}

	@Column(name = "SECONDCOVER_QUES_FOUR")
	public String getQuesFourSecond() {
		return quesFourSecond;
	}

	@Column(name = "SECONDCOVER_QUES_FIVE")
	public String getQuesFiveSecond() {
		return quesFiveSecond;
	}

	@Column(name = "SECONDCOVER_QUES_SIX")
	public String getQuesSixSecond() {
		return quesSixSecond;
	}

	@Column(name = "SECONDCOVER_QUES_SEVEN")
	public String getQuesSevenSecond() {
		return quesSevenSecond;
	}

	@Column(name = "SECONDCOVER_QUES_EIGHT")
	public String getQuesEightSecond() {
		return quesEightSecond;
	}

	@Column(name = "SECONDCOVER_QUES_NINE")
	public String getQuesNineSecond() {
		return quesNineSecond;
	}

	@Column(name = "SECONDCOVER_QUES_TEN")
	public String getQuesTenSecond() {
		return quesTenSecond;
	}

	@Column(name = "SECONDCOVER_QUES_ELEVEN")
	public String getQuesElevenSecond() {
		return quesElevenSecond;
	}

	@Column(name = "SECONDCOVER_QUES_ELEVEN_A")
	public String getQuesElevenASecond() {
		return quesElevenASecond;
	}

	@Column(name = "FIRSTCOVER_HEIGHT")
	public String getFirstCoverHeight() {
		return firstCoverHeight;
	}

	@Column(name = "FIRSTCOVER_WEIGHT")
	public String getFirstCoverWeight() {
		return firstCoverWeight;
	}

	@Column(name = "SECONDCOVER_HEIGHT")
	public String getSecondCoverHeight() {
		return secondCoverHeight;
	}

	@Column(name = "SECONDCOVER_WEIGHT")
	public String getSecondCoverWeight() {
		return secondCoverWeight;
	}

	@Column(name = "FIRSTCOVER_MEDDICAL_DET")
	public String getFirstCoverMedDetails() {
		return firstCoverMedDetails;
	}

	@Column(name = "SECONDCOVER_MEDICAL_DET")
	public String getSecondCoverMedDetails() {
		return secondCoverMedDetails;
	}
	
	@Column(name = "REINSTATEMENT_FLAG")
	public String getReinStatementFlag() {
		return reinStatementFlag;
	}

	// SETTERS

	public void setFirstCoverMedDetails(String firstCoverMedDetails) {
		this.firstCoverMedDetails = firstCoverMedDetails;
	}

	public void setSecondCoverMedDetails(String secondCoverMedDetails) {
		this.secondCoverMedDetails = secondCoverMedDetails;
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

	public void setFirstCoverCash(BigDecimal firstCoverCash) {
		this.firstCoverCash = firstCoverCash;
	}

	public void setFirstCoverShares(BigDecimal firstCoverShares) {
		this.firstCoverShares = firstCoverShares;
	}

	public void setFirstCoverRealEstate(BigDecimal firstCoverRealEstate) {
		this.firstCoverRealEstate = firstCoverRealEstate;
	}

	public void setFirstCoverAssetsOthers(BigDecimal firstCoverAssetsOthers) {
		this.firstCoverAssetsOthers = firstCoverAssetsOthers;
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

	public void setSecondCoverAssetsOthers(BigDecimal secondCoverAssetsOthers) {
		this.secondCoverAssetsOthers = secondCoverAssetsOthers;
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

	public void setFirstCoverbenifitAmt(String firstCoverbenifitAmt) {
		this.firstCoverbenifitAmt = firstCoverbenifitAmt;
	}

	public void setFirstCoverbenifitTerm(BigDecimal firstCoverbenifitTerm) {
		this.firstCoverbenifitTerm = firstCoverbenifitTerm;
	}

	public void setSecondCoverbenifitAmt(String secondCoverbenifitAmt) {
		this.secondCoverbenifitAmt = secondCoverbenifitAmt;
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

	public void setPrumDate(Date prumDate) {
		this.prumDate = prumDate;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setOutstandingAmount(int outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public void setLapseDate(Date lapseDate) {
		this.lapseDate = lapseDate;
	}

	
	
	public void setReinStatementFlag(String reinStatementFlag) {
		this.reinStatementFlag = reinStatementFlag;
	}
	public void setCpReinStatementBenefit(List<CpReinStatementBenefit> cpReinStatementBenefit) {
		this.cpReinStatementBenefit = cpReinStatementBenefit;
	}

}
