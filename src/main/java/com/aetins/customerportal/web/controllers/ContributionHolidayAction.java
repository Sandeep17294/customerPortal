package com.aetins.customerportal.web.controllers;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aetins.customerportal.core.faceletstaglib.SecurityLibrary;
import com.aetins.customerportal.web.common.Constants;
import com.aetins.customerportal.web.common.ILoginSession;
import com.aetins.customerportal.web.common.LoginSession;
import com.aetins.customerportal.web.dao.CpNFPersonalDetailsDAO;
import com.aetins.customerportal.web.dao.impl.ContributionHolidayDAOImpl;
import com.aetins.customerportal.web.dto.CpContributionHolidayDTO;
import com.aetins.customerportal.web.dto.CpTermAndConditionDTO;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.dto.SearchPolicyDTO;
import com.aetins.customerportal.web.dto.ServiceRequestMasterDTO;
import com.aetins.customerportal.web.dto.UserDTO;
import com.aetins.customerportal.web.entity.CpRequestMaster;
import com.aetins.customerportal.web.service.ContributionHolidaylBL;
import com.aetins.customerportal.web.service.RedirectionBL;
import com.aetins.customerportal.web.service.impl.ContributionHolidayBLImpl;
import com.aetins.customerportal.web.service.impl.CustomerPortalServicesImpl;
import com.aetins.customerportal.web.service.impl.RedirectionBLImpl;
import com.aetins.customerportal.web.utils.BSLUtils;

@Controller(value = "contributionHolidayAction")
@Scope("session")
public class ContributionHolidayAction extends BaseAction {
	
	private static final Logger logger = Logger.getLogger(ContributionHolidayAction.class);

	private String policyNo;
	private String premiumStatus;
	private String planName;
	private boolean checkBox;
	public float outStandingAmt;
	private String bodyMes;
	private String paymentFrequency;
	private Date commenceDate;
	private Date contributionDueDate;
	private Date holidayFrom;
	private Date holidayTo;
	private BigDecimal contribution;
	private BigDecimal totalDue;
	private BigDecimal totReceivedContr;
	private String contriDueDate;
	private String commencementDate;
	private BigDecimal numberPaidContribution;
	private BigDecimal planContractYear;
	
	
	private ServiceRequestMasterDTO serviceRequestMasterDTO = new ServiceRequestMasterDTO();
	private List<CpUserInfoDTO> cpUserList = new ArrayList<CpUserInfoDTO>();
	private UserDTO userDetails = new UserDTO();
	//private List<CpContributionHolidayDTO> cpContributionHolidayList = new ArrayList<CpContributionHolidayDTO>();
	//private CpContributionHolidayDTO cpContributionHolidayDTO = new CpContributionHolidayDTO();
	
	private List<CpContributionHolidayDTO> cpContributionHolidayList;
	private CpContributionHolidayDTO cpContributionHolidayDTO;
	
	private SearchPolicyDTO searchPolicyDTO = new SearchPolicyDTO();
	private List<CpTermAndConditionDTO> termsConditionYesYes;
	private List<CpTermAndConditionDTO> termsConditionYesNo;
	
	//private ContributionHolidayBLImpl contributionHolidayBL;
	
	@Autowired
	CustomerPortalServicesImpl customerPortalServices;
	
	@Autowired
	ContributionHolidaylBL contributionHolidaylBL;
	
	@Autowired
	CpNFPersonalDetailsDAO cpNFPersonalDetailsDAOImpl;
	
	@Autowired
	RedirectionBL redirectImpl;
	

	private SearchPolicyDTO searchPolicyList;
	List masterRecord = null;
	
	private String fatcaFlag;
	
	Date serDate;
	private boolean fatcaFlagpopUp;

	private boolean allow;
	private boolean holiday;
	private boolean oldtrans;
    private boolean buttons;
	
	@Autowired
	TransactionServiceAction transAction;
	
	@PostConstruct
	public void init() {
		try {
//			logger.info("Entering inside init() for ContributionHolidayAction ========================");
//			//Method is used to load Hloday details based on policy No.
//			fetchHolidayDetails();
//			//allowContriHoloday();
//			termsConditionYesYes = redirectImpl.listTermAndCondition(
//			Constants.CONTRIBUTION_HOLIDAY_SERVICE_REQUEST_TYPE, Constants.MANDATORY, Constants.REQUIRED);
//			termsConditionYesNo = redirectImpl.listTermAndCondition1(Constants.CONTRIBUTION_HOLIDAY_SERVICE_REQUEST_TYPE, Constants.MANDATORYNO,
			//		Constants.REQUIREDYES);
			//searchPolicyDTO = customerPortalServices.getOutstandingAmount(policyNo, Constants.LANGUAGE_EN);
		} catch (Exception e) {
			logger.error("Inside catch block of init() for ContributionHolidayAction================" +e.getMessage());
			e.printStackTrace();
		}
	}


	/*
	 * Method used to Load holiday details based on policy No. 
	 * Created by : Shashi
	 * Created on : 19/12/2017
	 */
	
	private BigDecimal seqno;
	
	public String fetchHolidayDetails() {
		FacesContext context = FacesContext.getCurrentInstance();
		HomeDetailsAction hAction = context.getApplication().evaluateExpressionGet(context, "#{homeDetailsAction}",
				HomeDetailsAction.class);
		
		searchPolicyList=null;
		searchPolicyList = new SearchPolicyDTO();
		
		policyNo = hAction.getSearchPolicyList().getPlanNumber();
		premiumStatus = hAction.getSearchPolicyList().getPremiumPayStatus();
		planName = hAction.getSearchPolicyList().getProductName();
		paymentFrequency = hAction.getSearchPolicyList().getPaymentMode();
		contributionDueDate = hAction.getSearchPolicyList().getPremiumDueDate().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		contriDueDate = formatter.format(contributionDueDate);
		commenceDate = hAction.getSearchPolicyList().getCommencementDate().getTime();
		numberPaidContribution = hAction.getSearchPolicyList().getPaidDueCount();
		planContractYear = hAction.getSearchPolicyList().getPolicyYear();
		commencementDate = formatter.format(commenceDate);
		contribution = hAction.getSearchPolicyList().getRegularContract();
		totalDue = hAction.getSearchPolicyList().getTotalDue();
		totReceivedContr = hAction.getSearchPolicyList().getTotalRecContract();
		searchPolicyList = (SearchPolicyDTO) getSession().getAttribute("POLICYDATA");
		holidayTo = hAction.getSearchPolicyList().getHolidayEndDate().getTime();
	
		cpContributionHolidayDTO=null;
		cpContributionHolidayDTO=new CpContributionHolidayDTO();
		cpContributionHolidayList=null;
		cpContributionHolidayList = new ArrayList<CpContributionHolidayDTO>();
		
		
		holidayFrom = searchPolicyList.getPremiumDueDate().getTime();
		cpContributionHolidayDTO.setdHolidayTo(holidayTo);
		termsConditionYesYes = redirectImpl.listTermAndCondition(Constants.CONTRIBUTION_HOLIDAY_SERVICE_REQUEST_TYPE, Constants.MANDATORY, Constants.REQUIRED);
	    termsConditionYesNo = redirectImpl.listTermAndCondition1(Constants.CONTRIBUTION_HOLIDAY_SERVICE_REQUEST_TYPE, Constants.MANDATORYNO,
						Constants.REQUIREDYES);
	    buttons=false;
	    holiday=true;
		buttons=false;
		allow=true;
		oldtrans=true;
		
		if(hAction.getPolicyDetailsList().size()>0) {
			for(int i=0;i<hAction.getPolicyDetailsList().size();i++) {
				if(policyNo.equalsIgnoreCase(hAction.getPolicyDetailsList().get(i).getPolicyNo())){
					seqno=hAction.getPolicyDetailsList().get(i).getSeqno();
				}
			}
		}
		
	   // allowContriHoloday();
		getSession().setAttribute("FATCAFLAG", "");
		contibchecking();
	    
	    return "/user/contributionholiday?faces-redirect=true";
	}

	/*
	 * Method used to validate Holiday is allowed or not based on policy No. 
	 * Created by : Shashi
	 * Created on : 19/12/2017
	 */
	
	
	
	public void contibchecking() {
		int diffInDays = 0;
		holiday=true;
		buttons=false;
		serDate = null;
		fatcaFlag = null;
		fatcaFlagpopUp = false;
		if (BSLUtils.isNotNull(searchPolicyList.getContrHolidays())) {
		     if (searchPolicyList.getContrHolidays().equalsIgnoreCase("ELIGIBLE")) {
				buttons=true;
				holiday=true;
				List<CpRequestMaster> requestDate = contributionHolidaylBL.getTransactionCount(SecurityLibrary.getFullLoggedInUser().getVuserName(), "Y");
				logger.info("Fatca Master list size is ================" + requestDate.size());
				//allowcheck();
				if (requestDate.size() > 0) {
					serDate = requestDate.get(0).getServiceRequestDate();
					diffInDays = (int) ((new Date().getTime() - serDate.getTime()) / (1000 * 60 * 60 * 24));
					System.out.println(diffInDays);
				}
				logger.info("Difference in days for fatca validation ==============" + diffInDays);
				if (diffInDays > 90 || requestDate.size() == 0) {
					fatcaFlag = "N";
					fatcaFlagpopUp = false;
					getSession().setAttribute("FATCAFLAG", fatcaFlag);
				} else {
					fatcaFlag = "Y";
					fatcaFlagpopUp = true;
					getSession().setAttribute("FATCAFLAG", fatcaFlag);
				}
			}else {
				holiday=false;
				buttons=false;
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"As per Terms and Conditions Contribution Holiday Option is not available.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		}
	}
	
	public void allowcheck() {
		allow=true;
		oldtrans=true;
		Long count = cpNFPersonalDetailsDAOImpl.getRowCount(Constants.CONTRIBUTION_HOLIDAY_SERVICE_REQUEST_TYPE,
				policyNo);
		logger.info("Contribution Holiday Transaction Count ===========" + count);
		System.out.println("count==" + count);
		long tmp = 0;
		int output = count.compareTo(tmp);
		if (output > 0) {
			oldtrans=false;
			buttons=false;
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
					"Your old transaction request is in process.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}else {
			String result = customerPortalServices.getAllowedTransaction(policyNo, "PREM_HOL");
			logger.info("Transaction fn called for Contribution Holiday ==============" + result);
			if (BSLUtils.isNotNull(result)) {
				if (result.equalsIgnoreCase("ALLOWED")) {
				} else {
					buttons=false;
	                allow=false;
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
							"Request is Not Eligible as the Plan status is inactive.Please contact Salama Customer Portal Team.");
					PrimeFaces.current().dialog().showMessageDynamic(message);
				}
			}

		}
	}
	
	
	public int transStatus() {
		Long count = cpNFPersonalDetailsDAOImpl.getRowCount(Constants.CONTRIBUTION_HOLIDAY_SERVICE_REQUEST_TYPE, policyNo);
		System.out.println("count==" + count);
		long tmp = 0;
		int output = count.compareTo(tmp);
		return output;
	}

	
	
	
	public void btnContinueLater() {
		try {
			int transCount = transStatus();
			String result = customerPortalServices.getAllowedTransaction(policyNo, "PREM_HOL");
			logger.info("Transaction fn called for CH HOLIDAY ==============" + result);
			logger.info("Inside btnContinueLater of CH HOLIDAY Transaction pending " + "count is ======="+ transCount);
			if (BSLUtils.isNotNull(result)) {
				if (result.equalsIgnoreCase("ALLOWED")) {
					if (transCount == 0) {
						if(holiday==true) {
							logger.info("Entering inside btnContinueLater Method ===================");
							Date currentDate = new Date();
							serviceRequestMasterDTO.setServiceRequestDate(currentDate);
							serviceRequestMasterDTO.setServiceRequestType(Constants.CONTRIBUTION_HOLIDAY_SERVICE_REQUEST_TYPE);
							serviceRequestMasterDTO.setPolicyNo(policyNo);
							serviceRequestMasterDTO.setUserId(SecurityLibrary.getFullLoggedInUser().getVuserName());
							serviceRequestMasterDTO.setRequestStatus(Constants.INACTIVE);
							serviceRequestMasterDTO.setRecentUpdateDate(currentDate);
							serviceRequestMasterDTO.setSeqno(seqno);
							serviceRequestMasterDTO = transAction.insertServiceRequest(serviceRequestMasterDTO);

							cpContributionHolidayDTO.setnServicRequestNo(serviceRequestMasterDTO);
							cpContributionHolidayDTO.setvPolicyNo(policyNo);
							cpContributionHolidayDTO.setvProduct(planName);
							cpContributionHolidayDTO.setdContributionDueDate(contributionDueDate);
							cpContributionHolidayDTO.setnOutstandingAmount(totalDue.floatValue());
							cpContributionHolidayDTO.setnContribution(contribution.floatValue());
							cpContributionHolidayDTO.setvPaymentFrequency(paymentFrequency);
							cpContributionHolidayDTO.setDcommenceDate(commenceDate);
							cpContributionHolidayDTO.setNcontractYear(planContractYear);
							cpContributionHolidayDTO.setNpaidContribution(numberPaidContribution);
							cpContributionHolidayDTO.setdHolidayFrom(holidayFrom);
							cpContributionHolidayDTO.setdHolidayTo(holidayTo);
							cpContributionHolidayDTO.setTotReceivedContr(totReceivedContr);
							cpContributionHolidayList.add(cpContributionHolidayDTO);
							boolean status = contributionHolidaylBL.insertHolidayDetails(cpContributionHolidayList);			
							if(holiday==true) {
								getSession().setAttribute("TRANSACTION", "CONTRIBUTIONHOLIDAY");
							}else {
								if(status==true) {
									getSession().setAttribute("TRANSACTION", "CONTRIBUTIONHOLIDAY");
									PrimeFaces.current().executeScript("PF('dlg3').show()");
								}	
							}		
						}else {
							FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
									"As per Terms and Conditions Contribution Holiday Option is not available.");
							PrimeFaces.current().dialog().showMessageDynamic(message);	
						}
					}else {
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
								"Your old transaction request is in process.");
						PrimeFaces.current().dialog().showMessageDynamic(message);
					}	
				}else {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
							"Request is Not Eligible as the Plan status is inactive.Please contact Salama Customer Support.");
					PrimeFaces.current().dialog().showMessageDynamic(message);
				}
			}
		} catch (Exception e) {
			logger.error("Inside Catch block of btnContinueLater() for ContributionHolidayAction==========="+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	private String processing;
	
	public String fatcasample() {
		int transCount = transStatus();
		String result = customerPortalServices.getAllowedTransaction(policyNo, "PREM_HOL");
		if(transCount==0 && result.equalsIgnoreCase("ALLOWED") && holiday==true && processing.equalsIgnoreCase("YES")) {
			return "/factaFrom/contributionholidatfatcaform?faces-redirect=true";	
		}else {
			return null;
		}
	}
	
	
	

	public String getProcessing() {
		return processing;
	}


	public void setProcessing(String processing) {
		this.processing = processing;
	}


	public void btnContinueLaterfatca() {
		try {
			int transCount = transStatus();
			String result = customerPortalServices.getAllowedTransaction(policyNo, "PREM_HOL");
			logger.info("Transaction fn called for CH HOLIDAY ==============" + result);
			logger.info("Inside btnContinueLater of CH HOLIDAY Transaction pending " + "count is ======="+ transCount);
			processing = null;
			processing = "NO";
			if (BSLUtils.isNotNull(result)) {
				if (result.equalsIgnoreCase("ALLOWED")) {
					if (transCount == 0) {
						if(holiday==true) {
							logger.info("Entering inside btnContinueLater Method ===================");
							Date currentDate = new Date();
							serviceRequestMasterDTO.setServiceRequestDate(currentDate);
							serviceRequestMasterDTO.setServiceRequestType(Constants.CONTRIBUTION_HOLIDAY_SERVICE_REQUEST_TYPE);
							serviceRequestMasterDTO.setPolicyNo(policyNo);
							serviceRequestMasterDTO.setUserId(SecurityLibrary.getFullLoggedInUser().getVuserName());
							serviceRequestMasterDTO.setRequestStatus(Constants.INACTIVE);
							serviceRequestMasterDTO.setRecentUpdateDate(currentDate);
							serviceRequestMasterDTO = transAction.insertServiceRequest(serviceRequestMasterDTO);

							cpContributionHolidayDTO.setnServicRequestNo(serviceRequestMasterDTO);
							cpContributionHolidayDTO.setvPolicyNo(policyNo);
							cpContributionHolidayDTO.setvProduct(planName);
							cpContributionHolidayDTO.setdContributionDueDate(contributionDueDate);
							cpContributionHolidayDTO.setnOutstandingAmount(totalDue.floatValue());
							cpContributionHolidayDTO.setnContribution(contribution.floatValue());
							cpContributionHolidayDTO.setvPaymentFrequency(paymentFrequency);
							cpContributionHolidayDTO.setDcommenceDate(commenceDate);
							cpContributionHolidayDTO.setNcontractYear(planContractYear);
							cpContributionHolidayDTO.setNpaidContribution(numberPaidContribution);
							cpContributionHolidayDTO.setdHolidayFrom(holidayFrom);
							cpContributionHolidayDTO.setdHolidayTo(holidayTo);
							cpContributionHolidayDTO.setTotReceivedContr(totReceivedContr);
							cpContributionHolidayList.add(cpContributionHolidayDTO);
							boolean status = contributionHolidaylBL.insertHolidayDetails(cpContributionHolidayList);			
							if(status==true) {
								processing = "YES";
								getSession().setAttribute("TRANSACTION", "CONTRIBUTIONHOLIDAY");
							}
						}else {
							FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
									"As per Terms and Conditions Contribution Holiday Option is not available.");
							PrimeFaces.current().dialog().showMessageDynamic(message);	
						}
					}else {
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
								"Your old transaction request is in process.");
						PrimeFaces.current().dialog().showMessageDynamic(message);
					}	
				}else {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
							"Request is Not Eligible as the Plan status is inactive.Please contact Salama Customer Support.");
					PrimeFaces.current().dialog().showMessageDynamic(message);
				}
			}
		} catch (Exception e) {
			logger.error("Inside Catch block of btnContinueLater() for ContributionHolidayAction==========="+ e.getMessage());
			e.printStackTrace();
		}
	}

	
	
	public void sample() {
		PrimeFaces.current().executeScript("popOpen()");
	}

	
	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getPremiumStatus() {
		return premiumStatus;
	}

	public void setPremiumStatus(String premiumStatus) {
		this.premiumStatus = premiumStatus;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public boolean isCheckBox() {
		return checkBox;
	}

	public void setCheckBox(boolean checkBox) {
		this.checkBox = checkBox;
	}

	public float getOutStandingAmt() {
		return outStandingAmt;
	}

	public void setOutStandingAmt(float outStandingAmt) {
		this.outStandingAmt = outStandingAmt;
	}

	public String getPaymentFrequency() {
		return paymentFrequency;
	}

	public void setPaymentFrequency(String paymentFrequency) {
		this.paymentFrequency = paymentFrequency;
	}

	public Date getContributionDueDate() {
		return contributionDueDate;
	}

	public void setContributionDueDate(Date contributionDueDate) {
		this.contributionDueDate = contributionDueDate;
	}

	public Date getHolidayFrom() {
		return holidayFrom;
	}

	public void setHolidayFrom(Date holidayFrom) {
		this.holidayFrom = holidayFrom;
	}

	public Date getHolidayTo() {
		return holidayTo;
	}

	public void setHolidayTo(Date holidayTo) {
		this.holidayTo = holidayTo;
	}

	public BigDecimal getContribution() {
		return contribution;
	}

	public void setContribution(BigDecimal contribution) {
		this.contribution = contribution;
	}


	public ServiceRequestMasterDTO getServiceRequestMasterDTO() {
		return serviceRequestMasterDTO;
	}

	public void setServiceRequestMasterDTO(ServiceRequestMasterDTO serviceRequestMasterDTO) {
		this.serviceRequestMasterDTO = serviceRequestMasterDTO;
	}

	public List<CpUserInfoDTO> getCpUserList() {
		return cpUserList;
	}

	public void setCpUserList(List<CpUserInfoDTO> cpUserList) {
		this.cpUserList = cpUserList;
	}

	public UserDTO getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDTO userDetails) {
		this.userDetails = userDetails;
	}

	public List<CpContributionHolidayDTO> getCpContributionHolidayList() {
		return cpContributionHolidayList;
	}

	public void setCpContributionHolidayList(List<CpContributionHolidayDTO> cpContributionHolidayList) {
		this.cpContributionHolidayList = cpContributionHolidayList;
	}

	public CpContributionHolidayDTO getCpContributionHolidayDTO() {
		return cpContributionHolidayDTO;
	}

	public void setCpContributionHolidayDTO(CpContributionHolidayDTO cpContributionHolidayDTO) {
		this.cpContributionHolidayDTO = cpContributionHolidayDTO;
	}

	public SearchPolicyDTO getSearchPolicyDTO() {
		return searchPolicyDTO;
	}

	public void setSearchPolicyDTO(SearchPolicyDTO searchPolicyDTO) {
		this.searchPolicyDTO = searchPolicyDTO;
	}

	public CustomerPortalServicesImpl getCustomerPortalServices() {
		return customerPortalServices;
	}

	public void setCustomerPortalServices(CustomerPortalServicesImpl customerPortalServices) {
		this.customerPortalServices = customerPortalServices;
	}

	public String getContriDueDate() {
		return contriDueDate;
	}

	public void setContriDueDate(String contriDueDate) {
		this.contriDueDate = contriDueDate;
	}

	public List<CpTermAndConditionDTO> getTermsConditionYesYes() {
		return termsConditionYesYes;
	}

	public List<CpTermAndConditionDTO> getTermsConditionYesNo() {
		return termsConditionYesNo;
	}


	public RedirectionBL getRedirectImpl() {
		return redirectImpl;
	}


	public void setRedirectImpl(RedirectionBL redirectImpl) {
		this.redirectImpl = redirectImpl;
	}


	public void setTermsConditionYesYes(List<CpTermAndConditionDTO> termsConditionYesYes) {
		this.termsConditionYesYes = termsConditionYesYes;
	}

	public void setTermsConditionYesNo(List<CpTermAndConditionDTO> termsConditionYesNo) {
		this.termsConditionYesNo = termsConditionYesNo;
	}


	private boolean termsCondition;

	public boolean isTermsCondition() {
		return termsCondition;
	}

	public void setTermsCondition(boolean termsCondition) {
		this.termsCondition = termsCondition;
	}

	public String getCommencementDate() {
		return commencementDate;
	}

	public Date getCommenceDate() {
		return commenceDate;
	}

	public BigDecimal getNumberPaidContribution() {
		return numberPaidContribution;
	}

	public BigDecimal getPlanContractYear() {
		return planContractYear;
	}

	public void setCommenceDate(Date commenceDate) {
		this.commenceDate = commenceDate;
	}

	public void setCommencementDate(String commencementDate) {
		this.commencementDate = commencementDate;
	}

	public void setNumberPaidContribution(BigDecimal numberPaidContribution) {
		this.numberPaidContribution = numberPaidContribution;
	}

	public void setPlanContractYear(BigDecimal planContractYear) {
		this.planContractYear = planContractYear;
	}

	public BigDecimal getTotalDue() {
		return totalDue;
	}

	public void setTotalDue(BigDecimal totalDue) {
		this.totalDue = totalDue;
	}

	public SearchPolicyDTO getSearchPolicyList() {
		return searchPolicyList;
	}

	public void setSearchPolicyList(SearchPolicyDTO searchPolicyList) {
		this.searchPolicyList = searchPolicyList;
	}

	public String getBodyMes() {
		return bodyMes;
	}

	public void setBodyMes(String bodyMes) {
		this.bodyMes = bodyMes;
	}

	/*public OtpValidation getOtpValidation() {
		return otpValidation;
	}

	public void setOtpValidation(OtpValidation otpValidation) {
		this.otpValidation = otpValidation;
	}*/

	public TransactionServiceAction getTransAction() {
		return transAction;
	}

	public void setTransAction(TransactionServiceAction transAction) {
		this.transAction = transAction;
	}
	

	public String getFatcaFlag() {
		return fatcaFlag;
	}


	public void setFatcaFlag(String fatcaFlag) {
		this.fatcaFlag = fatcaFlag;
	}


	public boolean isAllow() {
		return allow;
	}


	public void setAllow(boolean allow) {
		this.allow = allow;
	}


	public boolean isHoliday() {
		return holiday;
	}


	public void setHoliday(boolean holiday) {
		this.holiday = holiday;
	}


	public boolean isOldtrans() {
		return oldtrans;
	}


	public void setOldtrans(boolean oldtrans) {
		this.oldtrans = oldtrans;
	}


	public boolean isButtons() {
		return buttons;
	}


	public void setButtons(boolean buttons) {
		this.buttons = buttons;
	}


	public boolean isFatcaFlagpopUp() {
		return fatcaFlagpopUp;
	}

	public void setFatcaFlagpopUp(boolean fatcaFlagpopUp) {
		this.fatcaFlagpopUp = fatcaFlagpopUp;
	}

	public BigDecimal getTotReceivedContr() {
		return totReceivedContr;
	}

	public void setTotReceivedContr(BigDecimal totReceivedContr) {
		this.totReceivedContr = totReceivedContr;
	}
	

}