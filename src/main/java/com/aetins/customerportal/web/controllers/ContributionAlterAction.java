package com.aetins.customerportal.web.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aetins.customerportal.core.faceletstaglib.SecurityLibrary;
import com.aetins.customerportal.web.common.Constants;
import com.aetins.customerportal.web.common.ILoginSession;
import com.aetins.customerportal.web.common.LoginSession;
import com.aetins.customerportal.web.dao.ContributionHolidayDAO;
import com.aetins.customerportal.web.dao.CpNFPersonalDetailsDAO;
import com.aetins.customerportal.web.dao.impl.ContributionHolidayDAOImpl;
import com.aetins.customerportal.web.dto.ContributionAlterationDTO;
import com.aetins.customerportal.web.dto.CpTermAndConditionDTO;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.dto.SearchPolicyDTO;
import com.aetins.customerportal.web.dto.ServiceRequestMasterDTO;
import com.aetins.customerportal.web.dto.UserDTO;
import com.aetins.customerportal.web.entity.CPServiceRequestDocuments;
import com.aetins.customerportal.web.entity.CpListBoxAnswers;
import com.aetins.customerportal.web.entity.CpQuestionnaire;
import com.aetins.customerportal.web.entity.CpRequestMaster;
import com.aetins.customerportal.web.service.CPServiceRequestDocumentsBL;
import com.aetins.customerportal.web.service.ContributionAlterBL;
import com.aetins.customerportal.web.service.CustomerPortalServices;
import com.aetins.customerportal.web.service.RedirectionBL;
import com.aetins.customerportal.web.service.impl.AdminBLImpl;
import com.aetins.customerportal.web.service.impl.ContributionAlterBLImpl;
import com.aetins.customerportal.web.service.impl.CustomerPortalServicesImpl;
import com.aetins.customerportal.web.utils.BSLUtils;

@Controller(value = "contributionAlterationAction")
@Scope("session")
public class ContributionAlterAction extends BaseAction {

	
	private static final Logger logger = Logger.getLogger(ContributionAlterAction.class);
	
	@Autowired
	CustomerPortalServices customerPortalServicesImpl;
	
	@Autowired
	ContributionAlterBL contributionAlterBLImpl;
	
	@Autowired
	RedirectionBL redirectImpl;
	 
	@Autowired
	LoginSession loginSession;
	
	@Autowired
	ContributionHolidayDAO contributionHolidayDAOImpl;
	
	@Autowired
	AdminBLImpl adminImpl;
	
	private String policyNo;
	private boolean checkBox;
	public float outStandingAmt;
	public Calendar dueDate;
	private String paymentFrequency;
	private Date contributionDueDate;
	private BigDecimal contribution;
	private UserDTO userDetails = new UserDTO();
	private SearchPolicyDTO searchPolicyDTO = new SearchPolicyDTO();
	private ServiceRequestMasterDTO serviceRequestMasterDTO = new ServiceRequestMasterDTO();
	private List<CpUserInfoDTO> cpUserList = new ArrayList<CpUserInfoDTO>();
	
	private List<CpTermAndConditionDTO> termsConditionYesYes;
	private List<CpTermAndConditionDTO> termsConditionYesNo;
	
	
	private ContributionAlterationDTO contributionAterationDetails;
	private ServiceRequestMasterDTO requestdto = new ServiceRequestMasterDTO();
	private boolean termsCondition;
	private BigDecimal annualContibution;
	private BigDecimal regularContibution;
	private SearchPolicyDTO searchPolicyList;
	private boolean displayPanel = false;
	boolean increaseAlteration = false;
	boolean decreaseAlteration = false;
	private String assetsTotal;
	private String liabilitiesTotal;
	private boolean showIncreaseError = false;
	private boolean showDecreasError = false;
	boolean minContributuon = false;
	boolean maxContribution = false;
	private int maxContributionLength;
	private boolean enableCheckBox;
	private boolean enableCheckBox1;
	private boolean errormessage;
	private String errmsg;
	private String bodyMes;
	private String fatcaFlag;
	Date serDate;
	private boolean fatcaFlagpopUp;
	private Boolean sampleradio;
	private List<CpQuestionnaire> questionnaireList = new ArrayList<>();
	private List<CpListBoxAnswers> listBoxAnswers = new ArrayList<>();
    private List<CpListBoxAnswers> selectedRecord = new ArrayList<>();
    
	private BigDecimal seqno;
	
	
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
//		contributionAterationDetails = new ContributionAlterationDTO();
//		sampleradio=false;
//	       
//		try {
//			FacesContext context = FacesContext.getCurrentInstance();
//			HomeDetailsAction hAction = context.getApplication().evaluateExpressionGet(context, "#{homeDetailsAction}",	HomeDetailsAction.class);
//			//Need to check the functionality
//			policyNo = hAction.getSearchPolicyList().getPlanNumber();
//			policyNo = hAction.getSearchPolicyList().getPlanNumber();
//			if(hAction.getPolicyDetailsList().size()>0) {
//				for(int i=0;i<hAction.getPolicyDetailsList().size();i++) {
//					if(policyNo.equalsIgnoreCase(hAction.getPolicyDetailsList().get(i).getPolicyNo())){
//						seqno=hAction.getPolicyDetailsList().get(i).getSeqno();
//					}
//				}
//			}			
//			fetchFromCustomerPortalService();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
	}
	
	public String termaltertaionfetch() {
		clearsvalues();
		
		contributionAterationDetails = new ContributionAlterationDTO();
		sampleradio=false;
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			HomeDetailsAction hAction = context.getApplication().evaluateExpressionGet(context, "#{homeDetailsAction}",	HomeDetailsAction.class);
			//Need to check the functionality
			policyNo = hAction.getSearchPolicyList().getPlanNumber();
			policyNo = hAction.getSearchPolicyList().getPlanNumber();
			if(hAction.getPolicyDetailsList().size()>0) {
				for(int i=0;i<hAction.getPolicyDetailsList().size();i++) {
					if(policyNo.equalsIgnoreCase(hAction.getPolicyDetailsList().get(i).getPolicyNo())){
						seqno=hAction.getPolicyDetailsList().get(i).getSeqno();
					}
				}
			}			
			fetchFromCustomerPortalService();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		docupath=false;
		getSession().setAttribute("UPLOADFILENAME", "EMPTY");
		getSession().setAttribute("FATCAFLAG", "");
		onPageLoadValidation();
		return "/user/termalteration?faces-redirect=true";
	}
	 private String docFilepath;
	    private int docFilesize;
	    UploadedFile upFile;
	    private boolean docupath;
	    CPServiceRequestDocuments cPServiceRequestDocuments;
	    @Autowired
	    CPServiceRequestDocumentsBL cPServiceRequestDocumentsBL;
	    
	    public void upload(FileUploadEvent event) {
	    	logger.info("Uploading file to the location");
	    	System.out.println("reading fileName: "+event.getFile().getFileName());
	    	upFile=event.getFile();
	    	System.out.println(upFile.getFileName());
	    	if(upFile.getFileName()!=null) {
	    		docupath=true;
	    		docFilepath=upFile.getFileName();
	        	docFilesize=(int) upFile.getSize();
	        	getSession().setAttribute("UPLOADFILE", upFile);
	        	getSession().setAttribute("UPLOADFILENAME", docFilepath);
	        	getSession().setAttribute("UPLOADFILESize", docFilesize);
	    	}
	    }
	 	
	@SuppressWarnings("unchecked")
	public void onPageLoadValidation() {
		   serDate = null;
		   int diffInDays = 0;
		   fatcaFlag = null;
		   fatcaFlagpopUp = false;
		logger.info("Entering into ContributionAlteration validation =====================");
		List<CpRequestMaster> requestDate = contributionHolidayDAOImpl.getTransactionCount(SecurityLibrary.getFullLoggedInUser().getVuserName(), "Y");
		logger.info("Fatca Master list size is ================" +requestDate.size());
		if (requestDate.size() > 0) {
			serDate = requestDate.get(0).getServiceRequestDate();
			diffInDays = (int) ((new Date().getTime() - serDate.getTime()) / (1000 * 60 * 60 * 24));
			System.out.println(diffInDays);
		}
		
		logger.info("Difference in days for fatca validation ==============" +diffInDays);
		if (diffInDays > 90 || requestDate.size() == 0) {
			fatcaFlag = "N";
			fatcaFlagpopUp=false;
			getSession().setAttribute("FATCAFLAG", fatcaFlag);
		} else {
			fatcaFlag = "Y";
			fatcaFlagpopUp=true;
			getSession().setAttribute("FATCAFLAG", fatcaFlag);
		}
		logger.info("Fatca flag Status =================" +fatcaFlag);
		logger.info("FatcaFlagpopUp flag Status ===================" +fatcaFlagpopUp);
		String result = customerPortalServicesImpl.getAllowedTransaction(policyNo, "AL38");
		logger.info("Transaction fn called for Contribution Alteration ==============" + result);
	}

	public void fetchFromCustomerPortalService() {
		contributionAterationDetails = customerPortalServicesImpl.getContributionAlteration(policyNo,
				Constants.LANGUAGE_EN,seqno);
		annualContibution = contributionAterationDetails.getCurrentContribution().multiply(BigDecimal.valueOf(12));
		searchPolicyList = (SearchPolicyDTO) getSession().getAttribute("POLICYDATA");

		if (BSLUtils.isNotNull(searchPolicyList)) {
			if (BSLUtils.isNotNull(searchPolicyList.getMaxPremium())) {
				maxContributionLength = searchPolicyList.getMaxPremium().toPlainString().length();
			}
			if (BSLUtils.isNotNull(searchPolicyList.getPaymentMode())) {
				contributionAterationDetails.setNewFrequency(searchPolicyList.getPaymentMode());
			}
		}
	}

	public void assetsTotal() {

		BigDecimal assetCash = BigDecimal.valueOf(0.00);
		BigDecimal assetShares = BigDecimal.valueOf(0.00);
		BigDecimal assetOthers = BigDecimal.valueOf(0.00);
		BigDecimal assetRealEstate = BigDecimal.valueOf(0.00);

		if (BSLUtils.isNotNull(contributionAterationDetails.getAssetsCash())) {
			assetCash = contributionAterationDetails.getAssetsCash();
		}
		if (BSLUtils.isNotNull(contributionAterationDetails.getAssetsShares())) {
			assetShares = contributionAterationDetails.getAssetsShares();
		}
		if (BSLUtils.isNotNull(contributionAterationDetails.getAssetsOthers())) {
			assetOthers = contributionAterationDetails.getAssetsOthers();
		}
		if (BSLUtils.isNotNull(contributionAterationDetails.getAssetsRealEstate())) {
			assetRealEstate = contributionAterationDetails.getAssetsRealEstate();
		}
		BigDecimal total = assetCash.add(assetShares).add(assetOthers).add(assetRealEstate);
		assetsTotal = total.toPlainString();

	}

	public void liabilitiesTotal() {

		BigDecimal liabLoans = BigDecimal.valueOf(0.00);
		BigDecimal liabAccounts = BigDecimal.valueOf(0.00);
		BigDecimal liabMortgage = BigDecimal.valueOf(0.00);
		BigDecimal liabOther = BigDecimal.valueOf(0.00);

		if (BSLUtils.isNotNull(contributionAterationDetails.getLiabilitiesLoan())) {
			liabLoans = contributionAterationDetails.getLiabilitiesLoan();
		}

		if (BSLUtils.isNotNull(contributionAterationDetails.getLiabilitiesPayable())) {
			liabAccounts = contributionAterationDetails.getLiabilitiesPayable();
		}

		if (BSLUtils.isNotNull(contributionAterationDetails.getLiabilitiesMortgage())) {
			liabMortgage = contributionAterationDetails.getLiabilitiesMortgage();
		}

		if (BSLUtils.isNotNull(contributionAterationDetails.getLiabilitiesOther())) {
			liabOther = contributionAterationDetails.getLiabilitiesOther();
		}

		BigDecimal total = liabLoans.add(liabAccounts).add(liabMortgage).add(liabOther);
		liabilitiesTotal = total.toPlainString();

	}

public void sample(){
		
		if(contributionAterationDetails.getAlterationType()!= null){
			termsConditionYesYes=null;
			termsConditionYesNo=null;
			if(contributionAterationDetails.getAlterationType().equalsIgnoreCase("I")){
				termsConditionYesYes=null;
				termsConditionYesNo=null;
				termsConditionYesYes = redirectImpl.listTermAndCondition(Constants.CUSTOMER_CONTRIBUTION_ALTERATION_INC_REGULATION,
						Constants.MANDATORY, Constants.REQUIRED);
				termsConditionYesNo = redirectImpl.listTermAndCondition1(Constants.CUSTOMER_CONTRIBUTION_ALTERATION_INC_REGULATION,
						Constants.MANDATORYNO, Constants.REQUIREDYES);	
				sampleradio=true;
				System.out.println(sampleradio);
				
			}
			else if(contributionAterationDetails.getAlterationType().equalsIgnoreCase("D")){
				termsConditionYesYes=null;
				termsConditionYesNo=null;
				termsConditionYesYes = redirectImpl.listTermAndCondition(Constants. CUSTOMER_CONTRIBUTION_ALTERATION_DEC_REGULATION,
						Constants.MANDATORY, Constants.REQUIRED);
				termsConditionYesNo = redirectImpl.listTermAndCondition1(Constants. CUSTOMER_CONTRIBUTION_ALTERATION_DEC_REGULATION,
						Constants.MANDATORYNO, Constants.REQUIREDYES);		
				sampleradio=true;
				System.out.println(sampleradio);

				
			}
		}
	}
	public void displayPanel() {
		sample();
		setShowIncreaseError(false);
		setShowDecreasError(false);
		setDisplayPanel(false);

		if (BSLUtils.isNotNull(contributionAterationDetails.getNewContribution())) {

			if (BSLUtils.isNotNull(searchPolicyList.getMinPremium())
					&& BSLUtils.isNotNull(searchPolicyList.getMaxPremium())) {
				if (contributionAterationDetails.getNewContribution().compareTo(searchPolicyList.getMinPremium()) < 0) {
					FacesContext.getCurrentInstance().addMessage(null, new 
							 FacesMessage(FacesMessage.SEVERITY_WARN, "Info", "Contribution value should between"+""+searchPolicyList.getMinPremium()
							 +"and"+""+searchPolicyList.getMaxPremium()));
					minContributuon = true;

				} else if (contributionAterationDetails.getNewContribution()
						.compareTo(searchPolicyList.getMaxPremium()) > 0) {
					 FacesContext.getCurrentInstance().addMessage(null, new 
							 FacesMessage(FacesMessage.SEVERITY_WARN, "Info", "Contribution value should between"+""+searchPolicyList.getMinPremium()
							 +"and"+""+searchPolicyList.getMaxPremium()));
					maxContribution = true;
					setDisplayPanel(false);
				}
			}
			if (BSLUtils.isNotNull(searchPolicyList.getPaymentMode())) {
				if (BSLUtils.isNotNull(contributionAterationDetails.getCummulativeAmt())) {
					if (searchPolicyList.getPaymentMode().equalsIgnoreCase("Monthly")
							|| searchPolicyList.getPaymentMode().equalsIgnoreCase("Single")) {

						regularContibution = contributionAterationDetails.getCummulativeAmt().add(
								contributionAterationDetails.getNewContribution().multiply(BigDecimal.valueOf(12)));

					} else if (searchPolicyList.getPaymentMode().equalsIgnoreCase("Quarterly")) {
						regularContibution = contributionAterationDetails.getCummulativeAmt()
								.add(contributionAterationDetails.getNewContribution().multiply(BigDecimal.valueOf(4)));

					} else if (searchPolicyList.getPaymentMode().equalsIgnoreCase("Half Yearly")) {
						regularContibution = contributionAterationDetails.getCummulativeAmt()
								.add(contributionAterationDetails.getNewContribution().multiply(BigDecimal.valueOf(2)));
					}

					else if (searchPolicyList.getPaymentMode().equalsIgnoreCase("Yearly")) {
						regularContibution = contributionAterationDetails.getCummulativeAmt()
								.add(contributionAterationDetails.getNewContribution().multiply(BigDecimal.valueOf(1)));
					}
				}
			}
		}
		if (contributionAterationDetails.getAlterationType().equalsIgnoreCase("I")) {
			if (BSLUtils.isNotNull(contributionAterationDetails.getNewContribution())) {
				if (contributionAterationDetails.getNewContribution()
						.compareTo(contributionAterationDetails.getCurrentContribution()) > 0) {
					increaseAlteration = true;
					/*if (contributionAterationDetails.getNewContribution().compareTo(
							contributionAterationDetails.getCurrentContribution().add(BigDecimal.valueOf(100))) >= 0) {
						increaseAlteration = true;
					} else {
						displayErrorMessage("home.error.alterationType.increaseNotValid");
						setDisplayPanel(false);
						//setShowIncreaseError(true);
					}*/
				} else {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
							"Requested Contribution value must be greater than the present Contribution value");
					PrimeFaces.current().dialog().showMessageDynamic(message);
					setDisplayPanel(false);
					setShowIncreaseError(true);
				}
			}
		}

		if (contributionAterationDetails.getAlterationType().equalsIgnoreCase("D")) {
			if (BSLUtils.isNotNull(contributionAterationDetails.getNewContribution())) {
				if (contributionAterationDetails.getNewContribution()
						.compareTo(contributionAterationDetails.getCurrentContribution()) < 0) {
					decreaseAlteration = true;
				} else {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
							"Requested Contribution value must be less than the present Contribution value");
					PrimeFaces.current().dialog().showMessageDynamic(message);
					setDisplayPanel(false);
					setShowDecreasError(true);
				}
			}
		}
	}

	public boolean validateFinancialDetails() {
		boolean status = true;
		if (contributionAterationDetails.getAlterationType().equalsIgnoreCase("I")) {
			if (BSLUtils.isZero(contributionAterationDetails.getIncomeYearOne())) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
						"Last year income is required");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				status = false;
			}
			if (BSLUtils.isZero(contributionAterationDetails.getIncomeYearTwo())) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
						"Second year income is required");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				status = false;
			}
			if (BSLUtils.isZero(contributionAterationDetails.getIncomeYearThree())) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
						"Third year income is required");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				status = false;
			}
			if (BSLUtils.isZero(contributionAterationDetails.getAssetsCash())) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
						"Assests income is required");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				status = false;
			}
			if (BSLUtils.isZero(contributionAterationDetails.getLiabilitiesLoan())) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
						"Loan income is required");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				status = false;
			}
			if (BSLUtils.isZero(contributionAterationDetails.getAssetsShares())) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
						"Assets Share income is required");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				status = false;
			}
			if (BSLUtils.isZero(contributionAterationDetails.getLiabilitiesPayable())) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
						"Account Payble is required");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				status = false;
			}
			if (BSLUtils.isZero(contributionAterationDetails.getAssetsRealEstate())) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
						"Real Estate is required");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				status = false;
			}
			if (BSLUtils.isZero(contributionAterationDetails.getLiabilitiesMortgage())) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
						"Mortage value is required");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				status = false;
			}
			if (BSLUtils.isZero(contributionAterationDetails.getAssetsOthers())) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
						"Assets others value is required");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				status = false;
			}
			if (BSLUtils.isZero(contributionAterationDetails.getLiabilitiesOther())) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
						"Other Assets value is required");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				status = false;
			}
		}

		return status;
	}
	@Autowired
	CpNFPersonalDetailsDAO cpNFPersonalDetailsDAOImpl;
	@Autowired
	private CustomerPortalServices customerPortalServices;
	
	public int transStatus() {
		Long count = cpNFPersonalDetailsDAOImpl.getRowCount(Constants.CONTRIBUTION_ALTERATION_SERVICE_REQUEST_TYPE, policyNo);
		System.out.println("count==" + count);
		long tmp = 0;
		int output = count.compareTo(tmp);
		return output;
	}
	
	@Autowired
	TransactionServiceAction transAction;


	public void btnContinueLater() {
		logger.info("::::::: Entering inside btnContinurLater Method::::::::::");
        boolean increaseNotValid = false;
		increaseAlteration = false;
		decreaseAlteration = false;
		minContributuon = false;
		maxContribution = false;
		Date date = new Date();
		
		int transCount = transStatus();
		String result = customerPortalServices.getAllowedTransaction(policyNo, "NONFN");
		String applicaleBox = null;
		if (BSLUtils.isNotNull(result)) {
			if (result.equalsIgnoreCase("ALLOWED")) {
				if (transCount == 0) {
					applicaleBox = "Y";
					
					requestdto.setServiceRequestDate(date);
					requestdto.setServiceRequestType(Constants.CONTRIBUTION_ALTERATION_SERVICE_REQUEST_TYPE);
					requestdto.setPolicyNo(policyNo);
					String userName = SecurityLibrary.getFullLoggedInUser().getVuserName();
					requestdto.setUserId(userName);
					requestdto.setRequestStatus(Constants.INACTIVE);
					requestdto.setRecentUpdateDate(date);
					requestdto.setSeqno(seqno);
					requestdto.setApplicable(applicaleBox);

					regularContibution = contributionAterationDetails.getNewContribution().multiply(BigDecimal.valueOf(12));
					
					if (contributionAterationDetails.getAlterationType().equalsIgnoreCase("I")) {
						if (contributionAterationDetails.getNewContribution()
								.compareTo(contributionAterationDetails.getCurrentContribution()) > 0) {
							increaseAlteration = true;
						}
					}

					if (contributionAterationDetails.getAlterationType().equalsIgnoreCase("D")) {
						if (contributionAterationDetails.getNewContribution()
								.compareTo(contributionAterationDetails.getCurrentContribution()) < 0) {
							decreaseAlteration = true;
						}
					}

					if (BSLUtils.isNotNull(searchPolicyList.getMinPremium())
							&& BSLUtils.isNotNull(searchPolicyList.getMaxPremium())) {
						if (contributionAterationDetails.getNewContribution().compareTo(searchPolicyList.getMinPremium()) < 0) {
							minContributuon = true;

						} else if (contributionAterationDetails.getNewContribution()
								.compareTo(searchPolicyList.getMaxPremium()) > 0) {
							maxContribution = true;
						}
					}
					
					if (contributionAterationDetails != null
							&& (minContributuon == false) && (maxContribution == false)) {

					boolean _financialValid = validateFinancialDetails();
						if (_financialValid == true) {
						//Need to check functionality
						requestdto = transAction.insertServiceRequest(requestdto);
						contributionAterationDetails.setServiceRequestNo(requestdto);
							boolean status = contributionAlterBLImpl.changeInContribution(contributionAterationDetails);
							if(status=true) {
								getSession().setAttribute("TRANSACTION", "CONTRIBUTION");
								//clearValues();
								PrimeFaces.current().executeScript("PF('dlg3').show()");
							}
							//status = adminImpl.saveAnswers(questionnaireList);
							if (fatcaFlagpopUp == true) {
						     }
					} else if (showIncreaseError == true) {
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
								"Requested Contribution value must be greater than the present Contribution value");
						PrimeFaces.current().dialog().showMessageDynamic(message);
					}  else if (showDecreasError == true) {
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
								"Requested Contribution value must be less than the present Contribution value");
						PrimeFaces.current().dialog().showMessageDynamic(message);
					} else if (minContributuon == true || maxContribution == true) {
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Contribution value should between"+""+searchPolicyList.getMinPremium()
						 +"and"+""+searchPolicyList.getMaxPremium());
						PrimeFaces.current().dialog().showMessageDynamic(message);
					}
				  }

				}else {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
							"Your old transaction is under processing.");
					PrimeFaces.current().dialog().showMessageDynamic(message);
				}
			} else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Request is Not Eligible as the Plan status is inactive.Please contact Salama Support Team.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		}
	}
	

	public void btnContinueLaterfatca() {
		logger.info("::::::: Entering inside btnContinurLater Method::::::::::");
        boolean increaseNotValid = false;
		increaseAlteration = false;
		decreaseAlteration = false;
		minContributuon = false;
		maxContribution = false;
		Date date = new Date();
		processing = null;
		processing = "NO";
		
		int transCount = transStatus();
		String result = customerPortalServices.getAllowedTransaction(policyNo, "NONFN");
		String applicaleBox = null;
		if (BSLUtils.isNotNull(result)) {
			if (result.equalsIgnoreCase("ALLOWED")) {
				if (transCount == 0) {
					applicaleBox = "Y";
					
					requestdto.setServiceRequestDate(date);
					requestdto.setServiceRequestType(Constants.CONTRIBUTION_ALTERATION_SERVICE_REQUEST_TYPE);
					requestdto.setPolicyNo(policyNo);
					String userName = SecurityLibrary.getFullLoggedInUser().getVuserName();
					requestdto.setUserId(userName);
					requestdto.setRequestStatus(Constants.INACTIVE);
					requestdto.setRecentUpdateDate(date);
					requestdto.setSeqno(seqno);
					requestdto.setApplicable(applicaleBox);

					regularContibution = contributionAterationDetails.getNewContribution().multiply(BigDecimal.valueOf(12));
					
					if (contributionAterationDetails.getAlterationType().equalsIgnoreCase("I")) {
						if (contributionAterationDetails.getNewContribution()
								.compareTo(contributionAterationDetails.getCurrentContribution()) > 0) {
							increaseAlteration = true;
						}
					}

					if (contributionAterationDetails.getAlterationType().equalsIgnoreCase("D")) {
						if (contributionAterationDetails.getNewContribution()
								.compareTo(contributionAterationDetails.getCurrentContribution()) < 0) {
							decreaseAlteration = true;
						}
					}

					if (BSLUtils.isNotNull(searchPolicyList.getMinPremium())
							&& BSLUtils.isNotNull(searchPolicyList.getMaxPremium())) {
						if (contributionAterationDetails.getNewContribution().compareTo(searchPolicyList.getMinPremium()) < 0) {
							minContributuon = true;

						} else if (contributionAterationDetails.getNewContribution()
								.compareTo(searchPolicyList.getMaxPremium()) > 0) {
							maxContribution = true;
						}
					}
					
					if (contributionAterationDetails != null
							&& (minContributuon == false) && (maxContribution == false)) {

					boolean _financialValid = validateFinancialDetails();
						if (_financialValid == true) {
						//Need to check functionality
						requestdto = transAction.insertServiceRequest(requestdto);
						contributionAterationDetails.setServiceRequestNo(requestdto);
							boolean status = contributionAlterBLImpl.changeInContribution(contributionAterationDetails);
							if(status=true) {
								processing = "YES";
								getSession().setAttribute("TRANSACTION", "CONTRIBUTION");
							}
					} else if (showIncreaseError == true) {
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
								"Requested Contribution value must be greater than the present Contribution value");
						PrimeFaces.current().dialog().showMessageDynamic(message);
					}  else if (showDecreasError == true) {
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
								"Requested Contribution value must be less than the present Contribution value");
						PrimeFaces.current().dialog().showMessageDynamic(message);
					} else if (minContributuon == true || maxContribution == true) {
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Contribution value should between"+""+searchPolicyList.getMinPremium()
						 +"and"+""+searchPolicyList.getMaxPremium());
						PrimeFaces.current().dialog().showMessageDynamic(message);
					}
				  }

				}else {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
							"Your old transaction is under processing.");
					PrimeFaces.current().dialog().showMessageDynamic(message);
				}
			} else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Request is Not Eligible as the Plan status is inactive.Please contact Salama Support Team.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		}
	}

	
	private String processing;
	public String fatcasample() {
		if(processing.equalsIgnoreCase("YES")) {
			return "/factaFrom/contributionalterationfatca?faces-redirect=true";
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

	public void clearsvalues() {
		contributionAterationDetails=null;
		setTermsCondition(false);
        setEnableCheckBox1(false);
        regularContibution=null;
	}
	
	public void clearValues(){
		contributionAterationDetails=null;
		setTermsCondition(false);
        setEnableCheckBox1(false);
        contributionAterationDetails.setNewContribution(null);
        setRegularContibution(null);
        contributionAterationDetails.setCoveredBankName(null);
        contributionAterationDetails.setPlanHolderBankName(null);
        contributionAterationDetails.setCoveredIBAN(null);
        contributionAterationDetails.setPlanHolderIBAN(null);
        contributionAterationDetails.setCoveredSourceFund(null);
        contributionAterationDetails.setPlanHolderSourceFund(null);
        contributionAterationDetails.setIncomeYearOne(null);
        contributionAterationDetails.setIncomeYearTwo(null);
        contributionAterationDetails.setIncomeYearThree(null);
        contributionAterationDetails.setAssetsCash(null);
        contributionAterationDetails.setLiabilitiesLoan(null);
        contributionAterationDetails.setAssetsShares(null);
        contributionAterationDetails.setAssetsRealEstate(null);
        contributionAterationDetails.setLiabilitiesMortgage(null);
        contributionAterationDetails.setAssetsOthers(null);
        contributionAterationDetails.setLiabilitiesOther(null);
        setAssetsTotal(null);
        setLiabilitiesTotal(null);
	}

	public boolean isCheckBox() {
		return checkBox;
	}

	public float getOutStandingAmt() {
		return outStandingAmt;
	}

	public String getPaymentFrequency() {
		return paymentFrequency;
	}

	public Date getContributionDueDate() {
		return contributionDueDate;
	}

	public BigDecimal getContribution() {
		return contribution;
	}

	public UserDTO getUserDetails() {
		return userDetails;
	}

	

	public SearchPolicyDTO getSearchPolicyDTO() {
		return searchPolicyDTO;
	}

	public void setCheckBox(boolean checkBox) {
		this.checkBox = checkBox;
	}

	public void setOutStandingAmt(float outStandingAmt) {
		this.outStandingAmt = outStandingAmt;
	}

	public void setPaymentFrequency(String paymentFrequency) {
		this.paymentFrequency = paymentFrequency;
	}

	public void setContributionDueDate(Date contributionDueDate) {
		this.contributionDueDate = contributionDueDate;
	}

	public void setContribution(BigDecimal contribution) {
		this.contribution = contribution;
	}

	public void setUserDetails(UserDTO userDetails) {
		this.userDetails = userDetails;
	}


	public void setSearchPolicyDTO(SearchPolicyDTO searchPolicyDTO) {
		this.searchPolicyDTO = searchPolicyDTO;
	}


	public String getPolicyNo() {
		return policyNo;
	}

	public ContributionAlterationDTO getContributionAterationDetails() {
		return contributionAterationDetails;
	}

	public void setCustomerPortalServicesImpl(CustomerPortalServicesImpl customerPortalServicesImpl) {
		this.customerPortalServicesImpl = customerPortalServicesImpl;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public void setContributionAterationDetails(ContributionAlterationDTO contributionAterationDetails) {
		this.contributionAterationDetails = contributionAterationDetails;
	}

	public ServiceRequestMasterDTO getServiceRequestMasterDTO() {
		return serviceRequestMasterDTO;
	}

	public List<CpUserInfoDTO> getCpUserList() {
		return cpUserList;
	}

	public void setServiceRequestMasterDTO(ServiceRequestMasterDTO serviceRequestMasterDTO) {
		this.serviceRequestMasterDTO = serviceRequestMasterDTO;
	}

	public void setCpUserList(List<CpUserInfoDTO> cpUserList) {
		this.cpUserList = cpUserList;
	}

	public void setContributionAlterBLImpl(ContributionAlterBLImpl contributionAlterBLImpl) {
		this.contributionAlterBLImpl = contributionAlterBLImpl;
	}

	public Calendar getDueDate() {
		return dueDate;
	}

	public void setDueDate(Calendar dueDate) {
		this.dueDate = dueDate;
	}

	public ServiceRequestMasterDTO getRequestdto() {
		return requestdto;
	}

	public void setRequestdto(ServiceRequestMasterDTO requestdto) {
		this.requestdto = requestdto;
	}

	public List<CpTermAndConditionDTO> getTermsConditionYesYes() {
		return termsConditionYesYes;
	}

	public List<CpTermAndConditionDTO> getTermsConditionYesNo() {
		return termsConditionYesNo;
	}

	public void setTermsConditionYesYes(List<CpTermAndConditionDTO> termsConditionYesYes) {
		this.termsConditionYesYes = termsConditionYesYes;
	}

	public void setTermsConditionYesNo(List<CpTermAndConditionDTO> termsConditionYesNo) {
		this.termsConditionYesNo = termsConditionYesNo;
	}


	public boolean isTermsCondition() {
		return termsCondition;
	}

	public void setTermsCondition(boolean termsCondition) {
		this.termsCondition = termsCondition;
	}

	public BigDecimal getAnnualContibution() {
		return annualContibution;
	}

	public void setAnnualContibution(BigDecimal annualContibution) {
		this.annualContibution = annualContibution;
	}

	public BigDecimal getRegularContibution() {
		return regularContibution;
	}

	public void setRegularContibution(BigDecimal regularContibution) {
		this.regularContibution = regularContibution;
	}

	public boolean isDisplayPanel() {
		return displayPanel;
	}

	public void setDisplayPanel(boolean displayPanel) {
		this.displayPanel = displayPanel;
	}

	public String getAssetsTotal() {
		return assetsTotal;
	}

	public void setAssetsTotal(String assetsTotal) {
		this.assetsTotal = assetsTotal;
	}

	public String getLiabilitiesTotal() {
		return liabilitiesTotal;
	}

	public void setLiabilitiesTotal(String liabilitiesTotal) {
		this.liabilitiesTotal = liabilitiesTotal;
	}

	public boolean isShowIncreaseError() {
		return showIncreaseError;
	}

	public boolean isShowDecreasError() {
		return showDecreasError;
	}

	public void setShowIncreaseError(boolean showIncreaseError) {
		this.showIncreaseError = showIncreaseError;
	}

	public void setShowDecreasError(boolean showDecreasError) {
		this.showDecreasError = showDecreasError;
	}

	public int getMaxContributionLength() {
		return maxContributionLength;
	}

	public void setMaxContributionLength(int maxContributionLength) {
		this.maxContributionLength = maxContributionLength;
	}

	public boolean isEnableCheckBox() {
		return enableCheckBox;
	}

	public void setEnableCheckBox(boolean enableCheckBox) {
		this.enableCheckBox = enableCheckBox;
	}

	public boolean isEnableCheckBox1() {
		return enableCheckBox1;
	}

	public void setEnableCheckBox1(boolean enableCheckBox1) {
		this.enableCheckBox1 = enableCheckBox1;
	}

	public boolean isErrormessage() {
		return errormessage;
	}

	public void setErrormessage(boolean errormessage) {
		this.errormessage = errormessage;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getBodyMes() {
		return bodyMes;
	}

	public void setBodyMes(String bodyMes) {
		this.bodyMes = bodyMes;
	}

	public boolean isFatcaFlagpopUp() {
		return fatcaFlagpopUp;
	}

	public void setFatcaFlagpopUp(boolean fatcaFlagpopUp) {
		this.fatcaFlagpopUp = fatcaFlagpopUp;
	}

	public SearchPolicyDTO getSearchPolicyList() {
		return searchPolicyList;
	}

	public void setSearchPolicyList(SearchPolicyDTO searchPolicyList) {
		this.searchPolicyList = searchPolicyList;
	}

	public Boolean getSampleradio() {
		return sampleradio;
	}

	public void setSampleradio(Boolean sampleradio) {
		this.sampleradio = sampleradio;
	}

	public List<CpQuestionnaire> getQuestionnaireList() {
		return questionnaireList;
	}

	public void setQuestionnaireList(List<CpQuestionnaire> questionnaireList) {
		this.questionnaireList = questionnaireList;
	}

	public List<CpListBoxAnswers> getListBoxAnswers() {
		return listBoxAnswers;
	}

	public void setListBoxAnswers(List<CpListBoxAnswers> listBoxAnswers) {
		this.listBoxAnswers = listBoxAnswers;
	}

	public List<CpListBoxAnswers> getSelectedRecord() {
		return selectedRecord;
	}

	public void setSelectedRecord(List<CpListBoxAnswers> selectedRecord) {
		this.selectedRecord = selectedRecord;
	}


	public BigDecimal getSeqno() {
		return seqno;
	}


	public void setSeqno(BigDecimal seqno) {
		this.seqno = seqno;
	}

	public String getDocFilepath() {
		return docFilepath;
	}

	public void setDocFilepath(String docFilepath) {
		this.docFilepath = docFilepath;
	}

	public int getDocFilesize() {
		return docFilesize;
	}

	public void setDocFilesize(int docFilesize) {
		this.docFilesize = docFilesize;
	}

	public boolean isDocupath() {
		return docupath;
	}

	public void setDocupath(boolean docupath) {
		this.docupath = docupath;
	}	

	
}
