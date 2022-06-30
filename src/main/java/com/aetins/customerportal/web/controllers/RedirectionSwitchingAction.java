package com.aetins.customerportal.web.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
import com.aetins.customerportal.web.common.LoginSession;
import com.aetins.customerportal.web.dao.ContributionHolidayDAO;
import com.aetins.customerportal.web.dao.RedirectionDAO;
import com.aetins.customerportal.web.dao.impl.ContributionHolidayDAOImpl;
import com.aetins.customerportal.web.dao.impl.RedirectionDAOImpl;
import com.aetins.customerportal.web.dto.CpTermAndConditionDTO;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.dto.FundDetailsDTO;
import com.aetins.customerportal.web.dto.ListItem;
import com.aetins.customerportal.web.dto.RedirectionDTO;
import com.aetins.customerportal.web.dto.ServiceRequestMasterDTO;
import com.aetins.customerportal.web.dto.StatementDTO;
import com.aetins.customerportal.web.dto.UserDTO;
import com.aetins.customerportal.web.entity.CPServiceRequestDocuments;
import com.aetins.customerportal.web.entity.CpListBoxAnswers;
import com.aetins.customerportal.web.entity.CpQuestionnaire;
import com.aetins.customerportal.web.entity.CpRequestMaster;
import com.aetins.customerportal.web.service.CPServiceRequestDocumentsBL;
import com.aetins.customerportal.web.service.CustomerPortalServices;
import com.aetins.customerportal.web.service.RedirectionBL;
import com.aetins.customerportal.web.service.impl.AdminBLImpl;
import com.aetins.customerportal.web.service.impl.CustomerPortalServicesImpl;
import com.aetins.customerportal.web.service.impl.RedirectionBLImpl;
import com.aetins.customerportal.web.utils.BSLUtils;

@Controller
@Scope("session")
public class RedirectionSwitchingAction extends BaseAction {

	private static final Logger logger = Logger.getLogger(RedirectionSwitchingAction.class);

	@Autowired
	RedirectionBL redirectImpl;

	@Autowired
	CustomerPortalServices customerPortalServices;

	@Autowired
	RedirectionAction redirectionAction;

	@Autowired
	TransactionServiceAction transAction;

	@Autowired
	RedirectionBL redirectionBL;

	@Autowired
	LoginSession loginSession;

	@Autowired
	ContributionHolidayDAO ContributionHolidayDAOImpl;

	@Autowired
	RedirectionDAO redirectionDAOImpl;

	@Autowired
	HomeDetailsAction homeAction;

	@Autowired
	AdminBLImpl adminImpl;

	private StatementDTO statementDetails;
	private List<RedirectionDTO> switchingList = new ArrayList<RedirectionDTO>();
	private List<ListItem> listItemList = new ArrayList<ListItem>();

	private RedirectionDTO redirectionDTO;
	private ListItem listItem;
	private boolean enableNextButton = false;
	// public static HttpSession session;
	private List<FundDetailsDTO> stratergyListLoc = new ArrayList<FundDetailsDTO>();
	private ServiceRequestMasterDTO serviceRequestMasterDTO;
	private boolean addNewQuestion = false;
	private boolean autoRenewalPopup = false;
	private FundDetailsDTO addedStretgy;
	private String policyNo;
	public boolean isEdited;
	Double sellTot = 0.0;
	Double buyTot = 0.0;
	private String sellNewTotal = String.valueOf(sellTot);
	private String buyNewTotal = String.valueOf(buyTot);
	private String total;
	private boolean fund;
	private List<CpUserInfoDTO> cpUserList = new ArrayList<CpUserInfoDTO>();
	private UserDTO userDetails = new UserDTO();
	private List<ListItem> listOfFundNames;
	private List<ListItem> listItems;
	private String selectedFund;

	private String premiumStatus;
	private String planName;
	private List<RedirectionDTO> sellingFundsList = new ArrayList<RedirectionDTO>();
	private List<RedirectionDTO> buyingFundsList = new ArrayList<RedirectionDTO>();
	List<RedirectionDTO> redirectList = new ArrayList<RedirectionDTO>();
	List<RedirectionDTO> switchList = new ArrayList<RedirectionDTO>();
	private Map<String, String> switchFundList;
	private int listCount;
	private List<CpTermAndConditionDTO> termsConditionYesYes;
	private List<CpTermAndConditionDTO> termsConditionYesNo;
	private String selectedOperation;
	private boolean enableRedirection = false;
	private boolean enableSwitching = false;
	private boolean enableRedirctSwitch = false;
	private boolean displayTin = false;
	private boolean countryPanel = false;
	// private String policyNo="ULNMA120012319";
	private List<RedirectionDTO> redirectionList = new ArrayList<RedirectionDTO>();// added // by
																					// viswakarthick // on
	boolean zeroErrorFlag = true; // 16/06/2017
	private List<ListItem> listOfRedirectFundNames;
	private double redirectTot = 0.0;
	private String newRedirectTotal = String.valueOf(redirectTot);
	private String titleMsg;

	private String fatcaFlag;
	Date serDate;
	private boolean fatcaFlagpopUp;
	private BigDecimal totalSellingVal = BigDecimal.ZERO;
	private boolean termsCondition;

	private List<CpQuestionnaire> questionnaireList = new ArrayList<>();
	private List<CpListBoxAnswers> listBoxAnswers = new ArrayList<>();
	private List<CpListBoxAnswers> selectedRecord = new ArrayList<>();

	private BigDecimal seqno;
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
//		FacesContext context = FacesContext.getCurrentInstance();
//		HomeDetailsAction homeAction = context.getApplication().evaluateExpressionGet(context, "#{homeDetailsAction}",
//				HomeDetailsAction.class);
//		policyNo = homeAction.getSearchPolicyList().getPlanNumber();
//		premiumStatus = homeAction.getSearchPolicyList().getPremiumPayStatus();
//		planName = homeAction.getSearchPolicyList().getProductName();
//				
//		if(homeAction.getPolicyDetailsList().size()>0) {
//			for(int i=0;i<homeAction.getPolicyDetailsList().size();i++) {
//				if(policyNo.equalsIgnoreCase(homeAction.getPolicyDetailsList().get(i).getPolicyNo())){
//					seqno=homeAction.getPolicyDetailsList().get(i).getSeqno();
//				}
//			}
//		}
//		
//		fetchFundDetailFromService();
//		fetchSellingFundListfromService();
//		fetchRedirectFundListfromService();
	}
	
	public void redirectswitchinginit() {
		investementsourcecode="";
		newRedirectTotal = String.valueOf(0.00);
		buyingFundsList=null;
		buyTot = 0.0;
		buyNewTotal= String.valueOf(0.00);
		buyingFundsList= new ArrayList<RedirectionDTO>();
		FacesContext context = FacesContext.getCurrentInstance();
		HomeDetailsAction homeAction = context.getApplication().evaluateExpressionGet(context, "#{homeDetailsAction}",
				HomeDetailsAction.class);
		policyNo = homeAction.getSearchPolicyList().getPlanNumber();
		premiumStatus = homeAction.getSearchPolicyList().getPremiumPayStatus();
		planName = homeAction.getSearchPolicyList().getProductName();

		if(homeAction.getPolicyDetailsList().size()>0) {
			for(int i=0;i<homeAction.getPolicyDetailsList().size();i++) {
				if(policyNo.equalsIgnoreCase(homeAction.getPolicyDetailsList().get(i).getPolicyNo())){
					seqno=homeAction.getPolicyDetailsList().get(i).getSeqno();
				}
			}
		}
		docupath=false;
		getSession().setAttribute("UPLOADFILENAME", "EMPTY");
		fetchFundDetailFromService();
		fetchSellingFundListfromService();
		fetchRedirectFundListfromService();
		getSession().setAttribute("FATCAFLAG", "");
		fatcaValidation();
	}
	
	@SuppressWarnings("unchecked")
	public void fatcaValidation() {
		serDate = null;
		int diffInDays = 0;
		fatcaFlag = null;
		fatcaFlagpopUp = false;
		List<CpRequestMaster> requestDate = ContributionHolidayDAOImpl.getTransactionCount(SecurityLibrary.getFullLoggedInUser().getVuserName(),"Y");
		logger.info("Fatca Master list size is ================" + requestDate.size());
		if (requestDate.size() > 0) {
			serDate = requestDate.get(0).getServiceRequestDate();
			diffInDays = (int) ((new Date().getTime() - serDate.getTime()) / (1000 * 60 * 60 * 24));
			System.out.println(diffInDays);
		}
		logger.info("Difference in days for fatca validation ==============" + diffInDays);
		if (diffInDays > 90 || requestDate.size() == 0) {
			fatcaFlag = "N";
			fatcaFlagpopUp = false;
			getSession().setAttribute("FATCAFLAGPOP", fatcaFlagpopUp);
			getSession().setAttribute("FATCAFLAG", fatcaFlag);
		} else {
			fatcaFlag = "Y";
			fatcaFlagpopUp = true;
			getSession().setAttribute("FATCAFLAGPOP", fatcaFlagpopUp);
			getSession().setAttribute("FATCAFLAG", fatcaFlag);
		}
		logger.info("Fatca flag Status =================" + fatcaFlag);
		logger.info("FatcaFlagpopUp flag Status ===================" + fatcaFlagpopUp);
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
	   
	   


	public void fetchSellingFundListfromService() {
		try {
			logger.info("Entering inside fetchSellingFundListfromService in RedirectionSwitchingAction ==========");
			sellingFundsList = customerPortalServices.getRedirectionDetails(policyNo, "EN",seqno);
			if (BSLUtils.isNotNull(switchingList) && switchingList.size() > 0) {
				switchingList.size();
				for (RedirectionDTO redirectionDTO : switchingList) {
					sellingFundsList.add(redirectionDTO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(
					"Inside catch block of fetchSellingFundListfromService() in RedirectionSwitchingAction ------------"
							+ e.getMessage());
		}
	}

	public void fetchRedirectFundListfromService() {
		try {
			logger.info("Entering inside fetchRedirectFundListfromService in RedirectionSwitchingAction ==========");
			redirectionList = customerPortalServices.getRedirectionDetails(policyNo, "EN",seqno);
			if (BSLUtils.isNotNull(redirectionList) && redirectionList.size() > 0) {
				for (RedirectionDTO redirectionDTO : redirectionList) {
					redirectionDTO.setMode("R");
				}
			}
//			FacesContext context = FacesContext.getCurrentInstance();
//			redirectionAction = context.getApplication().evaluateExpressionGet(context, "#{redirectionAction}",
//					RedirectionAction.class);
//			for (RedirectionDTO redirectionDTO : redirectionAction.getRedirectionList()) {
//				RedirectionDTO copiedDTO = new RedirectionDTO(redirectionDTO);
//				redirectionList.add(copiedDTO);
//			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(
					"Inside catch block of fetchRedirectFundListfromService() in RedirectionSwitchingAction ------------"
							+ e.getMessage());
		}
	}

	public void fetchFundDetailFromService() {
		try {
			logger.info("Entering inside fetchFundDetailFromService in RedirectionSwitchingAction ==========");
			listItems = customerPortalServices.getFundDetail("EN", "FUND", policyNo, "B");
			listOfRedirectFundNames = new ArrayList<ListItem>();
			for (ListItem list : listItems) {
				ListItem copiedList = new ListItem(list);
				listOfRedirectFundNames.add(copiedList);
			}
			switchFundList = new LinkedHashMap<String, String>();
			for (ListItem listItem : listItems) {
				switchFundList.put(listItem.getDescription(), listItem.getCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Inside catch block of fetchFundDetailFromService() in RedirectionSwitchingAction ------------"
					+ e.getMessage());
		}
	}

	public void getFundDetails(RedirectionDTO redirectionDTO, int index) {
		try {
			List<ListItem> tempListOfFundNames = listItems;

			for (ListItem listItem : tempListOfFundNames) {
				if (listItem.getCode().equalsIgnoreCase(redirectionDTO.getFundCode())) {
					redirectionDTO.setFundCurrency(listItem.getCurrencyCode().toUpperCase());
					redirectionDTO.setFundName(listItem.getDescription());
					redirectionDTO.setFundCode(listItem.getCode());
					redirectionDTO.setFundUnitPrice(listItem.getUnits());
					//tempListOfFundNames.remove(listItem);
					break;
				}
				listItems = tempListOfFundNames;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void btnDeleteStratergy(RedirectionDTO redirection1) {
		if (sellingFundsList != null) {
			if (sellingFundsList.size() > 0) {
				sellingFundsList.remove(redirection1);
				System.out.println("NOW THE SIZE IS:" + sellingFundsList.size());
			}
		}
		for (RedirectionDTO obj : sellingFundsList) {
			if (BSLUtils.isNotNull(obj.getValue())) {
				calculateTotal();
			} else {

			}
		}
	}

	public void btnDeleteStratergy1(RedirectionDTO redirection) {
		if (buyingFundsList != null) {
			if (buyingFundsList.size() > 0) {
				buyingFundsList.remove(redirection);
				System.out.println("NOW THE SIZE IS:" + switchingList.size());
			}
		}
		int index = 0;
		calculateTotal1(index);
	}

	public void addStretgy2() {
		//buyingFundsList = new ArrayList<RedirectionDTO>();
		int count = 0;
		for (RedirectionDTO each : buyingFundsList) {
			if (each.getValue().compareTo(BigDecimal.ZERO) > 0) {
				count++;
			}
		}
		for (RedirectionDTO each : sellingFundsList) {
			if (each.getValue().compareTo(BigDecimal.ZERO) > 0) {
				count++;
			}
		}
		if (count < 10) {
			if (switchingList != null) {
				redirectionDTO = new RedirectionDTO();
				redirectionDTO.setNewRecord(true);
				redirectionDTO.setValue(BigDecimal.ZERO);
				buyingFundsList.add(redirectionDTO);
			} else {
				switchingList = new ArrayList<RedirectionDTO>();
				redirectionDTO = new RedirectionDTO();
				redirectionDTO.setNewRecord(true);
				redirectionDTO.setValue(BigDecimal.ZERO);
				buyingFundsList.add(redirectionDTO);
			}
		} else {
			displayErrorMessageWithClientId("redirection.funds.error.exceedLength", "buyingFundErrorMessage");
			/*
			 * FacesContext.getCurrentInstance().addMessage(null, new
			 * FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
			 * "Total number of funds should not exceed 10"));
			 */
		}
	}

	public void beforeAddFund() {
		redirectionDTO = new RedirectionDTO();
		redirectionDTO.setNewRecord(true);
	}

	private boolean calculateTotal() {
		boolean errFlag = true;

		if (BSLUtils.isNotNull(sellingFundsList) && sellingFundsList.size() > 0) {
			for (RedirectionDTO obj : sellingFundsList) {
				if (obj.getValue().intValue() <= 100 && obj.getValue().intValue() > 0) {
					obj.getValue();
				} else if (obj.getValue().intValue() == 0) {
					displayErrorMessageWithClientId("customer.home.fund.sellingerrnotzero",
							"sellingFundErrorMessagePanel3");
					errFlag = false;
				} else {
					displayErrorMessageWithClientId("customer.home.fund.value", "sellingFundErrorMessagePanel3");
					errFlag = false;
				}
			}
		}
		return errFlag;
	}

	public void onChangeShare(int index) {
		if (BSLUtils.isNotNull(sellingFundsList) && sellingFundsList.size() > 0) {
			for (int i = 0; i < sellingFundsList.size(); i++) {
				if (i == index) {
					if (sellingFundsList.get(i).getValue().intValue() <= 100) {
						sellingFundsList.get(i).getValue();
						BigDecimal value = sellingFundsList.get(i).getValue().divide(BigDecimal.valueOf(100));
						BigDecimal fundPlanCurr = sellingFundsList.get(i).getFundPlanCurr();
						BigDecimal switchVal = value.multiply(fundPlanCurr);
						totalSellingVal = totalSellingVal.add(switchVal);
						System.out.println(":::::::::::::::::" + switchVal);
					} else {
						//displayErrorMessageWithClientId("customer.home.fund.value", "sellingFundErrorMessagePanel3");
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
								"Selling fund should not be greater than 100%");
						PrimeFaces.current().dialog().showMessageDynamic(message);
					}
				}
			}
		}
		isEdited = true;
	}
	
//malik
	int indexss=0;
	boolean errFlag1s=true;
	public void onChangeShare1(RedirectionDTO obj, int index) {
		indexs=0;
		errFlag1s=true;
		for(int j=0;j<buyingFundsList.size();j++){
			if(obj.getFundName().equalsIgnoreCase(buyingFundsList.get(j).getFundName())) {
				indexss++;
			}
		}
		
		if(indexs>=2) {
			errFlag1s=false;
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
					"You can select same funds multiple time for the policy. FUND NAME:->"+obj.getFundName()+"");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
		
		calculateTotal1(index);
		isEdited = true;
	}

	private void calculateTotal1(int index) {
		BigDecimal iT = BSLUtils.toBigDecimal(0);
		for (RedirectionDTO obj : getBuyingFundsList()) {
			if (BSLUtils.isNotNull(obj) && BSLUtils.isNotNull(obj.getValue()))
				iT = iT.add(obj.getValue());
			for (int i = 0; i < buyingFundsList.size(); i++) {
				if (i == index) {
					BigDecimal tmp = totalSellingVal.multiply(obj.getValue().divide(BigDecimal.valueOf(100)))
							.divide(new BigDecimal(obj.getFundUnitPrice()), 2, RoundingMode.HALF_UP);
					redirectionDTO.setFundSelling(tmp);
					System.out.println("::::::::::::::::::" + tmp);
				}
			}
		}
		buyNewTotal = iT.toPlainString();
		buyTot = iT.doubleValue();
		if (buyTot == 0.0) {
			setFund(true);
		} else {
			setFund(false);
		}
		if (buyTot >= 0.0 && buyTot < 100.0) {
			setFund(true);
		} else {
			setFund(false);
		}
		if (buyTot > 100.0) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
					"Buying fund  should equal to 100%");
			PrimeFaces.current().dialog().showMessageDynamic(message);
			setFund(true);

		} else {
			setFund(false);
		}
	}

	public void clearSwitchingValue() {
		bfnTransactionAllowedFN();
		setTermsCondition(false);
		fatcaValidation();
		setSelectedOperation(null);
		setEnableRedirection(false);
		setEnableSwitching(false);
		setEnableRedirctSwitch(false);
	}

	public void bfnTransactionAllowedFN() {

		String redirectionResult = customerPortalServices.getAllowedTransaction(policyNo, "REDIRECT");
		String switchingResult = customerPortalServices.getAllowedTransaction(policyNo, "SWITCHING");
		String bothResult = customerPortalServices.getAllowedTransaction(policyNo, "BOTH_SW_RD");

		if (redirectionResult.equalsIgnoreCase("ALLOWED") || switchingResult.equalsIgnoreCase("ALLOWED")
				|| bothResult.equalsIgnoreCase("ALLOWED")) {

		} else {
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
					"Request is Not Eligible as the Plan status is inactive."
					+ "Please contact AL HILAL at alhilal.life.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
	}

	private boolean termsandcondition=false;
	private boolean redirectbutton=false;
	private boolean switchbutton=false;
	private boolean redirectswitchbutton=false;
	
	private boolean rs=false;
	private boolean red=false;
	private boolean swic=false;

	
	@Autowired
	RedirectionAction raction;
	
	@Autowired
	SwitchingAction saction;
	
	public String settings() {
		selectedOperation=null;
		setEnableRedirection(false);
		setEnableSwitching(false);
		setEnableRedirctSwitch(false);
		setTermsandcondition(false);
		termsandcondition=false;
		redirectbutton=false;
		switchbutton=false;
		redirectswitchbutton=false;
		rs=false;
		red=false;
		swic=false;
		getSession().setAttribute("FATCAFLAG", "");
		
		return "/user/rediretswitching?faces-redirect=true";
	}
	
	
	
	String investboolean;
	private boolean investmentboolean=false;
	private boolean investmentboolean1=false;
	private String investementsourcecode;
	
	public void displaySelectedPanel() {		
		
		FacesContext context = FacesContext.getCurrentInstance();
		HomeDetailsAction homeAction = context.getApplication().evaluateExpressionGet(context, "#{homeDetailsAction}",HomeDetailsAction.class);
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		
		setEnableRedirection(false);
		setEnableSwitching(false);
		setEnableRedirctSwitch(false);
		setTermsandcondition(true);
		
		termsConditionYesYes=null;
		termsConditionYesNo=null;
		redirectbutton=false;
		switchbutton=false;
		redirectswitchbutton=false;
		rs=false;
		red=false;
		swic=false;
	
		investboolean=null;
		investmentboolean=false;
		investmentboolean1=false;

		
		if (BSLUtils.isNotNull(selectedOperation)) {
			if (selectedOperation.equalsIgnoreCase("R")) {
				raction.redirectioninit();
				String policyNo1 = homeAction.getSearchPolicyList().getPlanNumber();						
				redirectbutton=true;
				switchbutton=false;
				redirectswitchbutton=false;
				rs=false;
				red=true;
				swic=false;
				
				termsConditionYesYes = redirectImpl.listTermAndCondition("REDIRECTION",
						Constants.MANDATORY, Constants.REQUIRED);
				termsConditionYesNo = redirectImpl.listTermAndCondition1("REDIRECTION",
						Constants.MANDATORYNO, Constants.REQUIREDYES);
				setTermsandcondition(true);
				String result = customerPortalServices.getAllowedTransaction(policyNo, "REDIRECT");
				Long count = redirectionDAOImpl.getRowCount(Constants.CUSTOMER_REDIRECTION, policyNo1);
				System.out.println("count==" + count);
				long tmp = 0;
				int output = count.compareTo(tmp);
				if (output > 0) {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
							"Your old transaction request is in process.");
					PrimeFaces.current().dialog().showMessageDynamic(message);
					setTermsandcondition(false);
				}
				logger.info("Transaction fn called for Redirection  ==============" + result);
				if (BSLUtils.isNotNull(result)) {
					if (result.equalsIgnoreCase("ALLOWED")) {
					} else {
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
								"Request is Not Eligible as the Plan status is inactive."
								+ "Please contact Salama.");
						PrimeFaces.current().dialog().showMessageDynamic(message);
					}
				}
				setEnableRedirection(true);
			}

			if (selectedOperation.equalsIgnoreCase("S")) {
				saction.switchinginit();
				String policyNo2 = homeAction.getSearchPolicyList().getPlanNumber();				
				redirectbutton=false;
				switchbutton=true;
				redirectswitchbutton=false;
				rs=false;
				red=false;
				swic=true;
				
				termsConditionYesYes = redirectImpl.listTermAndCondition("SWITCHING", Constants.MANDATORY,
					     Constants.REQUIRED);
				termsConditionYesNo = redirectImpl.listTermAndCondition1("SWITCHING",
					     Constants.MANDATORYNO, Constants.REQUIREDYES);
				setTermsandcondition(true);
				
				investboolean = customerPortalServices.getcheckalu(locale.toString().equalsIgnoreCase("ar")?"AR":"EN", policyNo2);     
				if(BSLUtils.isNotNull(investboolean)&&investboolean.equalsIgnoreCase("Y")) {
					investmentboolean=false;
				}else {
					investmentboolean=false;
				}
				
				String result = customerPortalServices.getAllowedTransaction(policyNo, "SWITCHING");     
				Long count = redirectionDAOImpl.getRowCount(Constants.CUSTOMER_SWITCHING, policyNo2);
				System.out.println("count==" + count);
				long tmp = 0;
				int output = count.compareTo(tmp);
				if (output > 0) {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
							"Your old transaction request is in process.");
					PrimeFaces.current().dialog().showMessageDynamic(message);
				}
				logger.info("Transaction fn called for Switching   ==============" + result);
				if (BSLUtils.isNotNull(result)) {
					if (result.equalsIgnoreCase("ALLOWED")) {
					} else {
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
								"Request is Not Eligible as the Plan status is inactive."
								+ "Please contact Salama.");
						PrimeFaces.current().dialog().showMessageDynamic(message);

					}
				}
				setEnableSwitching(true);
			}

			if (selectedOperation.equalsIgnoreCase("RS")) {
				redirectswitchinginit();
		        String policyNo3 = homeAction.getSearchPolicyList().getPlanNumber();
				redirectbutton=false;
				switchbutton=false;
				redirectswitchbutton=true;
				rs=true;
				red=false;
				swic=false;
				
				termsConditionYesYes = redirectImpl.listTermAndCondition("REDIRECTIONANDSWITCHING",
						Constants.MANDATORY, Constants.REQUIRED);
				termsConditionYesNo = redirectImpl.listTermAndCondition1("REDIRECTIONANDSWITCHING",
						Constants.MANDATORYNO, Constants.REQUIREDYES);
				setTermsandcondition(true);
				String result = customerPortalServices.getAllowedTransaction(policyNo, "BOTH_SW_RD");     
				Long count_switch = redirectionDAOImpl.getRowCount(Constants.CUSTOMER_SWITCHING, policyNo3);
				Long count_red = redirectionDAOImpl.getRowCount(Constants.CUSTOMER_REDIRECTION, policyNo3);
				long tmp = 0;
				int output_switch = count_switch.compareTo(tmp);
				int output_red = count_red.compareTo(tmp);
				
				investboolean = customerPortalServices.getcheckalu(locale.toString().equalsIgnoreCase("ar")?"AR":"EN", policyNo3);     
				if(BSLUtils.isNotNull(investboolean)&&investboolean.equalsIgnoreCase("Y")) {
					investmentboolean1=false;
				}else {
					investmentboolean1=false;
				}
				
				
				if (output_switch > 0 || output_red > 0) {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
							"Your old transaction request is in process.");
					PrimeFaces.current().dialog().showMessageDynamic(message);
				}
				logger.info("Transaction fn called for Redirection and Switching   ==============" + result);
				if (BSLUtils.isNotNull(result)) {
					if (result.equalsIgnoreCase("ALLOWED")) {
					} else {
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
								"Request is Not Eligible as the Plan status is inactive."
								+ "Please contact Salama.");
						PrimeFaces.current().dialog().showMessageDynamic(message);
					}
				}
				setEnableRedirctSwitch(true);
			}
		}
	}

	public boolean investsetting() {
		boolean errFlag=true;
		if(investboolean == null || investboolean.equalsIgnoreCase("")) {
			
		}else {
			if(BSLUtils.isNotNull(investboolean)&&investboolean.equalsIgnoreCase("Y")) {
				investementsourcecode="IU-AU";
			}	
		}
		return errFlag;
	}
	

	public void btnContinueLater() {
		boolean errFlag = true;
		boolean flagRedirection = true;
		boolean flagBuying = true;
		boolean flagSelling = true;
		boolean status = false;
		if (!validateRedirectionList()) {
			logger.info("Validation Pass in Reditection :::::::::::::::::");
			errFlag = false;
			flagRedirection = false;
		}
		if (!validateSellingFundList()) {
			logger.info("Validation Pass in Switching --- Selling :::::::::::::::::");
			errFlag = false;
			flagSelling = false;
		}
		if (!validateBuyingFundList()) {
			logger.info("Validation Pass in Switching --- Buying :::::::::::::::::");
			errFlag = false;
			flagBuying = false;
		}
		
		if(!investsetting()) {
			errFlag=false;
		}

	
		if (errFlag) {
			getSession().setAttribute("REDIRECTION_AND_SWITHCHING", true);
			List<ServiceRequestMasterDTO> serviceRequestMasterDTOList = new ArrayList();
			serviceRequestMasterDTO = new ServiceRequestMasterDTO();
			if (flagRedirection) {
				ServiceRequestMasterDTO serviceRequestMasterDTORedirection = new ServiceRequestMasterDTO();
				serviceRequestMasterDTO.setServiceRequestDate(new Date());
				serviceRequestMasterDTO.setServiceRequestType(Constants.CUSTOMER_REDIRECTION);
				serviceRequestMasterDTO.setPolicyNo(policyNo);
				String userName = SecurityLibrary.getFullLoggedInUser().getVuserName();
				serviceRequestMasterDTO.setUserId(userName);
				serviceRequestMasterDTO.setRequestStatus(Constants.INACTIVE);
				serviceRequestMasterDTO.setRecentUpdateDate(new Date());
				serviceRequestMasterDTO.setSeqno(seqno);
				logger.info("INSERT INTO MASTER TABLE:::::::::::::::::::::");
				serviceRequestMasterDTORedirection = serviceRequestMasterDTO;
				serviceRequestMasterDTOList.add(serviceRequestMasterDTORedirection);

				logger.info("INSERTED INTO MASTER TABLE:::::::::::::::::::::EXIT");

				if (flagBuying && flagSelling) {
					// selling Fund
					ServiceRequestMasterDTO serviceRequestMasterDTOSwitching = new ServiceRequestMasterDTO();
					serviceRequestMasterDTOSwitching.setServiceRequestDate(new Date());
					serviceRequestMasterDTOSwitching.setPolicyNo(policyNo);
					serviceRequestMasterDTOSwitching.setUserId(userName);
					serviceRequestMasterDTOSwitching.setRequestStatus(Constants.INACTIVE);
					serviceRequestMasterDTOSwitching.setRecentUpdateDate(new Date());
					serviceRequestMasterDTOSwitching.setServiceRequestType(Constants.CUSTOMER_SWITCHING);
					serviceRequestMasterDTOSwitching.setSeqno(seqno);
					serviceRequestMasterDTOList.add(serviceRequestMasterDTOSwitching);
					serviceRequestMasterDTOList = transAction.insertServiceRequest(serviceRequestMasterDTOList);
					if (serviceRequestMasterDTOList != null) {
						if (serviceRequestMasterDTOList.size() > 0) {
							for (ServiceRequestMasterDTO eachRecord : serviceRequestMasterDTOList) {
								if (eachRecord.getServiceRequestType() != null) {
									if (eachRecord.getServiceRequestType()
											.equalsIgnoreCase(Constants.CUSTOMER_REDIRECTION)) {
										if (BSLUtils.isNotNull(redirectionList) && redirectionList.size() > 0) {
											for (RedirectionDTO redirectionDTO : redirectionList) {
												if (selectedOperation.equalsIgnoreCase("RS")) {
													redirectionDTO.setMode("R");
												}
												redirectionDTO.setServiceRequestNo(eachRecord);
												redirectList.add(redirectionDTO);
											}
										}
									}
									if (eachRecord.getServiceRequestType()
											.equalsIgnoreCase(Constants.CUSTOMER_SWITCHING)) {
										if (BSLUtils.isNotNull(sellingFundsList) && sellingFundsList.size() > 0) {
											for (RedirectionDTO redirectionDTO : sellingFundsList) {
												if (selectedOperation.equalsIgnoreCase("RS")) {
													redirectionDTO.setMode("S");
												}
												redirectionDTO.setServiceRequestNo(eachRecord);
												redirectionDTO.setInvestmenetsource(investementsourcecode);
												if (redirectionDTO.getValue().compareTo(BigDecimal.ZERO) > 0) {
													switchList.add(redirectionDTO);
												}
											}
										}

										// buying fund
										if (BSLUtils.isNotNull(buyingFundsList) && buyingFundsList.size() > 0) {
											for (RedirectionDTO redirectionDTO : buyingFundsList) {
												if (selectedOperation.equalsIgnoreCase("RS")) {
													redirectionDTO.setMode("B");
												}
												redirectionDTO.setServiceRequestNo(eachRecord);
												switchList.add(redirectionDTO);
											}
										}
									}
								}
							}
						}
					}

					logger.info("INSERT START INTO REDIRECTION TABLE:::::::::::::::::::::");
					status = redirectImpl.updateRedirection(redirectList);
					status = redirectImpl.updateRedirection(switchList);

					// status = adminImpl.saveAnswers(questionnaireList);
					if(status==true) {
						getSession().setAttribute("TRANSACTION", "REDIRECTIONSWITCHING");
						PrimeFaces.current().executeScript("PF('dlg3').show()");
					}
				}
			}
		}else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message", "Please re-check the process or contact oru Salama Support Team .");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
	}
	
	
	
	public void btnContinueLaterfatca() {
		boolean errFlag = true;
		boolean flagRedirection = true;
		boolean flagBuying = true;
		boolean flagSelling = true;
		boolean status = false;
		processing = null;
		processing = "No";
		if (!validateRedirectionList()) {
			logger.info("Validation Pass in Reditection :::::::::::::::::");
			errFlag = false;
			flagRedirection = false;
		}
		if (!validateSellingFundList()) {
			logger.info("Validation Pass in Switching --- Selling :::::::::::::::::");
			errFlag = false;
			flagSelling = false;
		}
		if (!validateBuyingFundList()) {
			logger.info("Validation Pass in Switching --- Buying :::::::::::::::::");
			errFlag = false;
			flagBuying = false;
		}
	
		if(!investsetting()) {
			errFlag=false;
		}

		
		if (errFlag) {
			getSession().setAttribute("REDIRECTION_AND_SWITHCHING", true);
			List<ServiceRequestMasterDTO> serviceRequestMasterDTOList = new ArrayList();
			serviceRequestMasterDTO = new ServiceRequestMasterDTO();
			if (flagRedirection) {
				ServiceRequestMasterDTO serviceRequestMasterDTORedirection = new ServiceRequestMasterDTO();
				serviceRequestMasterDTO.setServiceRequestDate(new Date());
				serviceRequestMasterDTO.setServiceRequestType(Constants.CUSTOMER_REDIRECTION);
				serviceRequestMasterDTO.setPolicyNo(policyNo);
				String userName = SecurityLibrary.getFullLoggedInUser().getVuserName();
				serviceRequestMasterDTO.setUserId(userName);
				serviceRequestMasterDTO.setRequestStatus(Constants.INACTIVE);
				serviceRequestMasterDTO.setRecentUpdateDate(new Date());
				serviceRequestMasterDTO.setSeqno(seqno);
				logger.info("INSERT INTO MASTER TABLE:::::::::::::::::::::");
				serviceRequestMasterDTORedirection = serviceRequestMasterDTO;
				serviceRequestMasterDTOList.add(serviceRequestMasterDTORedirection);

				logger.info("INSERTED INTO MASTER TABLE:::::::::::::::::::::EXIT");

				if (flagBuying && flagSelling) {
					// selling Fund
					ServiceRequestMasterDTO serviceRequestMasterDTOSwitching = new ServiceRequestMasterDTO();
					serviceRequestMasterDTOSwitching.setServiceRequestDate(new Date());
					serviceRequestMasterDTOSwitching.setPolicyNo(policyNo);
					serviceRequestMasterDTOSwitching.setUserId(userName);
					serviceRequestMasterDTOSwitching.setRequestStatus(Constants.INACTIVE);
					serviceRequestMasterDTOSwitching.setRecentUpdateDate(new Date());
					serviceRequestMasterDTOSwitching.setServiceRequestType(Constants.CUSTOMER_SWITCHING);
					serviceRequestMasterDTOSwitching.setSeqno(seqno);
					serviceRequestMasterDTOList.add(serviceRequestMasterDTOSwitching);
					serviceRequestMasterDTOList = transAction.insertServiceRequest(serviceRequestMasterDTOList);
					if (serviceRequestMasterDTOList != null) {
						if (serviceRequestMasterDTOList.size() > 0) {
							for (ServiceRequestMasterDTO eachRecord : serviceRequestMasterDTOList) {
								if (eachRecord.getServiceRequestType() != null) {
									if (eachRecord.getServiceRequestType()
											.equalsIgnoreCase(Constants.CUSTOMER_REDIRECTION)) {
										if (BSLUtils.isNotNull(redirectionList) && redirectionList.size() > 0) {
											for (RedirectionDTO redirectionDTO : redirectionList) {
												if (selectedOperation.equalsIgnoreCase("RS")) {
													redirectionDTO.setMode("R");
												}
												redirectionDTO.setServiceRequestNo(eachRecord);
												redirectList.add(redirectionDTO);
											}
										}
									}
									if (eachRecord.getServiceRequestType()
											.equalsIgnoreCase(Constants.CUSTOMER_SWITCHING)) {
										if (BSLUtils.isNotNull(sellingFundsList) && sellingFundsList.size() > 0) {
											for (RedirectionDTO redirectionDTO : sellingFundsList) {
												if (selectedOperation.equalsIgnoreCase("RS")) {
													redirectionDTO.setMode("S");
												}
												redirectionDTO.setServiceRequestNo(eachRecord);
												redirectionDTO.setInvestmenetsource(investementsourcecode);
												if (redirectionDTO.getValue().compareTo(BigDecimal.ZERO) > 0) {
													switchList.add(redirectionDTO);
												}
											}
										}

										// buying fund
										if (BSLUtils.isNotNull(buyingFundsList) && buyingFundsList.size() > 0) {
											for (RedirectionDTO redirectionDTO : buyingFundsList) {
												if (selectedOperation.equalsIgnoreCase("RS")) {
													redirectionDTO.setMode("B");
												}
												redirectionDTO.setServiceRequestNo(eachRecord);
												switchList.add(redirectionDTO);
											}
										}
									}
								}
							}
						}
					}

					logger.info("INSERT START INTO REDIRECTION TABLE:::::::::::::::::::::");
					status = redirectImpl.updateRedirection(redirectList);
					status = redirectImpl.updateRedirection(switchList);
					if(status==true) {
						processing = "YES";
						getSession().setAttribute("TRANSACTION", "REDIRECTIONSWITCHING");
					}
				}
			}
			logger.info("INSERT FINISH INTO REDIRECTION TABLE:::::::::::::::::::::");
		}else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message", "Please re-check the process or contact our Salama Support Team .");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
	}
	
	private String processing;

	public String fatcasample() {
		if(processing.equalsIgnoreCase("YES")) {
			return "/factaFrom/redirectionswithcingfatca?faces-redirect=true";
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

	public void clearValues() {

		for (RedirectionDTO each : redirectionList) {
			each.setValue(BigDecimal.ZERO);
			setNewRedirectTotal("");
		}

		for (RedirectionDTO each : sellingFundsList) {
			each.setValue(BigDecimal.ZERO);
			// setRendervalue(false);
		}

		for (RedirectionDTO each : buyingFundsList) {
			each.setFundName(null);
			each.setFundCode("");
			each.setValue(BigDecimal.ZERO);
			setBuyNewTotal("");
		}
	}

	public boolean validateSellingFundList() {
		boolean errFlag = true;
		boolean zeroFlag = true, abovehundFlag = true;
		boolean sellingValue = false;

		if (sellingFundsList.size() > 0) {
			for (int i = 0; i < sellingFundsList.size(); i++) {
				if (sellingFundsList.get(i).getValue().compareTo(BigDecimal.ZERO) > 0) {
					sellingValue = true;
				}
				if (sellingFundsList.get(i).getValue().intValue() > 100) {
					abovehundFlag = false;
					errFlag = false;
				}
				if (i == (sellingFundsList.size() - 1) && !zeroFlag) {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
							"Selling fund  should not be zero/null value.");
					PrimeFaces.current().dialog().showMessageDynamic(message);
				}
				if (i == (sellingFundsList.size() - 1) && !abovehundFlag) {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
							"Selling fund should not be greater than 100%.");
					PrimeFaces.current().dialog().showMessageDynamic(message);
				}
			}

			if (sellingValue == false) {
				errFlag = false;
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Please select fund for selling.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		}
		return errFlag;
	}

	public boolean validateBuyingFundList() {
		boolean errFlag = true;
		if (BSLUtils.isNotNull(buyingFundsList)) {

			if (buyingFundsList.size() == 0) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Please select the Buying Fund.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				errFlag = false;
			}

			if (BSLUtils.isNull(buyTot)) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Buying fund  should not be zero/null value.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				errFlag = false;
			}

			if (BSLUtils.isNotNull(buyTot)) {
				BigDecimal iT = BSLUtils.toBigDecimal(0);
				for (RedirectionDTO obj : buyingFundsList) {
					if (BSLUtils.isNotNull(obj) && BSLUtils.isNotNull(obj.getValue()))
						iT = iT.add(obj.getValue());
				}
				Double newTotal = iT.doubleValue();
				if (newTotal.equals(Double.parseDouble("0.0"))) {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
							"Buying fund  should not be zero/null value.");
					PrimeFaces.current().dialog().showMessageDynamic(message);
					errFlag = false;
				} else if (!newTotal.equals(Double.parseDouble("100.0"))) {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
							"Buying fund  should equal to 100%.");
					PrimeFaces.current().dialog().showMessageDynamic(message);
					errFlag = false;
				}
				if (buyingFundsList.size() > 10) {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
							"Total number of funds should not exceed 10.");
					PrimeFaces.current().dialog().showMessageDynamic(message);	
					errFlag = false;
				}
			}
		}
		return errFlag;
	}

	public void closeModal() {

		setTitleMsg(text.getString("customer.policydetails.NFAlt.warningheadmsg"));
		setBodyMes(text.getString("eservices.transaction.cancel"));
		// Added by viswa karthick on 08/07/2017 for clearing the session
		redirectionList = new ArrayList<RedirectionDTO>();
		for (RedirectionDTO redirectionDTO : redirectionAction.getRedirectionList()) {
			RedirectionDTO copiedDTO = new RedirectionDTO(redirectionDTO);
			redirectionList.add(copiedDTO);
		}
		fetchSellingFundListfromService();
		fetchFundDetailFromService();
		newRedirectTotal = "0.0";
		buyNewTotal = "0.0";
		buyingFundsList = new ArrayList<RedirectionDTO>();
		// RequestContext requestContext = RequestContext.getCurrentInstance();
		PrimeFaces.current().executeScript("popclose2()");
		PrimeFaces.current().executeScript("$('#successTab2').modal('show');");
	}

	
	
	// Added by viswakarthick on 16/06/2017
	public void getRedirectFundDetails(RedirectionDTO redirectionDTO, int index) {
		List<ListItem> tempListOfFundNames = listOfRedirectFundNames;
		for (ListItem listItem : tempListOfFundNames) {
			if (listItem.getCode().equalsIgnoreCase(redirectionDTO.getFundCode())) {
				redirectionDTO.setFundCurrency(listItem.getCurrencyCode().toUpperCase());
				redirectionDTO.setFundName(listItem.getDescription());
				redirectionDTO.setFundCode(listItem.getCode());
				//tempListOfFundNames.remove(listItem);
				System.out.println(tempListOfFundNames.size());
				break;
			}
			listOfRedirectFundNames = tempListOfFundNames;
		}
	}
//malik
	int indexs;
	boolean errFlag1;
	public void onChangeShare(RedirectionDTO obj, int index) {
		indexs=0;
		errFlag1=true;
		for(int j=0;j<redirectionList.size();j++){
			if(obj.getFundName().equalsIgnoreCase(redirectionList.get(j).getFundName())) {
				indexs++;
			}
		}
	
		if(indexs>=2) {
			errFlag1=false;
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
					"You can select same funds multiple time for the policy. FUND NAME:->"+obj.getFundName()+"");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
		
		calculateRedirectTotal();
		isEdited = true;
	}

	private void calculateRedirectTotal() {

		BigDecimal iT = BSLUtils.toBigDecimal(0);
		for (RedirectionDTO obj : getRedirectionList()) {

			if (BSLUtils.isNotNull(obj) && BSLUtils.isNotNull(obj.getValue()))
				iT = iT.add(obj.getValue());
		}
		newRedirectTotal = iT.toPlainString();
		sellTot = iT.doubleValue();

		if (sellTot == 0.0) {
			setFund(true);
		} else {
			setFund(false);
		}

		if (sellTot >= 0.0 && sellTot < 100.0) {
			setFund(true);
		} else {
			setFund(false);
		}

		if (sellTot > 100.0) {
			setFund(true);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
					"Redirection % should be 100%");
			PrimeFaces.current().dialog().showMessageDynamic(message);
			
		} else {
			setFund(false);
		}
	}

	public void btnRedirectDeleteStratergy(RedirectionDTO redirection) {
		if (redirectionList != null) {
			if (redirectionList.size() > 0) {
				redirectionList.remove(redirection);
				System.out.println("NOW THE SIZE IS:" + redirectionList.size());
			}
		}
		calculateRedirectTotal();
	}

	public boolean validateRedirectionList() {
		int count = 0;
		boolean errFlag = true;
		zeroErrorFlag = true;
		if (BSLUtils.isNotNull(redirectionList)) {
			if (redirectionList.size() == 0) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
						"Please Add Fund For Redirection");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				
				errFlag = false;
			}
			if (BSLUtils.isNull(newRedirectTotal)) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
						"Redirection % should not be null/zero value.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				errFlag = false;
			}
			/*
			 * for (int i = 0; i < redirectionList.size(); i++) { if
			 * (redirectionList.get(i).getValue().equals(BigDecimal.ZERO)) { errFlag =
			 * false; } if (i == (redirectionList.size() - 1) && !errFlag) {
			 * displayErrorMessageWithClientId("redirection.funds.errorNull",
			 * "redirectErrorMessage"); } }
			 */
			/*
			 * for (RedirectionDTO each : redirectionList) {
			 * 
			 * if(BSLUtils.isNotNull(each.getCriteria())){
			 * if(each.getValue().compareTo(BigDecimal.ZERO) == 0){ zeroErrorFlag = false; }
			 * } }
			 */
			if (zeroErrorFlag == false) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
						"Redirection % should not be null/zero value.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
			if (BSLUtils.isNotNull(newRedirectTotal)) {
				BigDecimal iT = BSLUtils.toBigDecimal(0);
				for (RedirectionDTO obj : redirectionList) {
					if (BSLUtils.isNotNull(obj) && BSLUtils.isNotNull(obj.getValue()))
						iT = iT.add(obj.getValue());
				}
				Double newTotal = iT.doubleValue();
				if (newTotal.equals(Double.parseDouble("0.0"))) {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
							"Redirection % should be 100%.");
					PrimeFaces.current().dialog().showMessageDynamic(message);
					errFlag = false;
				} else if (!newTotal.equals(Double.parseDouble("100.0"))) {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
							"Redirection % should be 100%.");
					PrimeFaces.current().dialog().showMessageDynamic(message);
					errFlag = false;
				}
				/*
				 * if (redirectionList.size() > 10) {
				 * displayErrorMessageWithClientId("redirection.funds.error.exceedLength",
				 * "redirectErrorMessage"); errFlag = false; }
				 */

				for (RedirectionDTO each : redirectionList) {

					if (each.getValue().compareTo(BigDecimal.ZERO) > 0) {
						count++;
					}
				}
				if (count > 10) {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
							"Total number of funds should not exceed 10.");
					PrimeFaces.current().dialog().showMessageDynamic(message);
					errFlag = false;
				}
			}
		}
		return errFlag;
	}

	public String addRedirectStretgy() {

		int count = 0;
		for (RedirectionDTO each : redirectionList) {

			if (each.getValue().compareTo(BigDecimal.ZERO) > 0) {
				count++;
			}
		}
		if (count < 10) {
			if (redirectionList != null) {
				redirectionDTO = new RedirectionDTO();
				redirectionDTO.setNewRecord(true);
				redirectionList.add(redirectionDTO);
			} else {
				redirectionList = new ArrayList<RedirectionDTO>();
				redirectionDTO = new RedirectionDTO();
				redirectionDTO.setNewRecord(true);
				redirectionList.add(redirectionDTO);
			}
		} else {
			//displayErrorMessageWithClientId("redirection.funds.error.exceedLength", "redirectErrorMessage");
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
					"Total number of funds should not exceed 10.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
			
		}
		return "true";
	}

	public List<ListItem> getListItemList() {
		return listItemList;
	}

	public void setListItemList(List<ListItem> listItemList) {
		this.listItemList = listItemList;
	}

	public ListItem getListItem() {
		return listItem;
	}

	public void setListItem(ListItem listItem) {
		this.listItem = listItem;
	}

	public List<ListItem> getListItems() {
		return listItems;
	}

	public void setListItems(List<ListItem> listItems) {
		this.listItems = listItems;
	}

	public ServiceRequestMasterDTO getServiceRequestMasterDTO() {
		return serviceRequestMasterDTO;
	}

	public void setServiceRequestMasterDTO(ServiceRequestMasterDTO serviceRequestMasterDTO) {
		this.serviceRequestMasterDTO = serviceRequestMasterDTO;
	}

	public boolean isAddNewQuestion() {
		return addNewQuestion;
	}

	public FundDetailsDTO getAddedStretgy() {
		return addedStretgy;
	}

	public boolean isAutoRenewalPopup() {
		return autoRenewalPopup;
	}

	public void setAddedStretgy(FundDetailsDTO addedStretgy) {
		this.addedStretgy = addedStretgy;
	}

	public void setAddNewQuestion(boolean addNewQuestion) {
		this.addNewQuestion = addNewQuestion;
	}

	public void setAutoRenewalPopup(boolean autoRenewalPopup) {
		this.autoRenewalPopup = autoRenewalPopup;
	}

	public List<RedirectionDTO> getSwitchingList() {
		return switchingList;
	}

	public void setSwitchingList(List<RedirectionDTO> switchingList) {
		this.switchingList = switchingList;
	}

	public RedirectionDTO getRedirectionDTO() {
		return redirectionDTO;
	}

	public void setRedirectImpl(RedirectionBLImpl redirectImpl) {
		this.redirectImpl = redirectImpl;
	}

	public void setRedirectionDTO(RedirectionDTO redirectionDTO) {
		this.redirectionDTO = redirectionDTO;
	}

	public boolean isEnableNextButton() {
		return enableNextButton;
	}

	public List<FundDetailsDTO> getStratergyListLoc() {
		return stratergyListLoc;
	}

	public void setEnableNextButton(boolean enableNextButton) {
		this.enableNextButton = enableNextButton;
	}

	public void setStratergyListLoc(List<FundDetailsDTO> stratergyListLoc) {
		this.stratergyListLoc = stratergyListLoc;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public boolean isEdited() {
		return isEdited;
	}

	public void setEdited(boolean isEdited) {
		this.isEdited = isEdited;
	}

	public String getSellNewTotal() {
		return sellNewTotal;
	}

	public void setSellNewTotal(String sellNewTotal) {
		this.sellNewTotal = sellNewTotal;
	}

	public String getBuyNewTotal() {
		return buyNewTotal;
	}

	public void setBuyNewTotal(String buyNewTotal) {
		this.buyNewTotal = buyNewTotal;
	}

	public boolean isFund() {
		return fund;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public void setFund(boolean fund) {
		this.fund = fund;
	}

	public StatementDTO getStatementDetails() {
		return statementDetails;
	}

	public void setStatementDetails(StatementDTO statementDetails) {
		this.statementDetails = statementDetails;
	}

	public void setCustomerPortalServices(CustomerPortalServicesImpl customerPortalServices) {
		this.customerPortalServices = customerPortalServices;
	}

	public Double getTot() {
		return sellTot;
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

	public void setTot(Double tot) {
		this.sellTot = tot;
	}

	public List<CpUserInfoDTO> getCpUserList() {
		return cpUserList;
	}

	public void setCpUserList(List<CpUserInfoDTO> cpUserList) {
		this.cpUserList = cpUserList;
	}

	public String getSelectedFund() {
		return selectedFund;
	}

	public void setSelectedFund(String selectedFund) {
		this.selectedFund = selectedFund;
	}

	public UserDTO getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDTO userDetails) {
		this.userDetails = userDetails;
	}

	public List<ListItem> getListOfFundNames() {
		return listOfFundNames;
	}

	public void setListOfFundNames(List<ListItem> listOfFundNames) {
		this.listOfFundNames = listOfFundNames;
	}

	public void setRedirectionBL(RedirectionBLImpl redirectionBL) {
		this.redirectionBL = redirectionBL;
	}

	public List<RedirectionDTO> getSellingFundsList() {
		return sellingFundsList;
	}

	public void setSellingFundsList(List<RedirectionDTO> sellingFundsList) {
		this.sellingFundsList = sellingFundsList;
	}

	public List<RedirectionDTO> getBuyingFundsList() {
		return buyingFundsList;
	}

	public void setBuyingFundsList(List<RedirectionDTO> buyingFundsList) {
		this.buyingFundsList = buyingFundsList;
	}

	public Double getTot1() {
		return buyTot;
	}

	public void setTot1(Double tot1) {
		this.buyTot = tot1;
	}

	public Map<String, String> getSwitchFundList() {
		return switchFundList;
	}

	public void setSwitchFundList(Map<String, String> switchFundList) {
		this.switchFundList = switchFundList;
	}

	private String bodyMes;

	public String getBodyMes() {
		return bodyMes;
	}

	public void setBodyMes(String bodyMes) {
		this.bodyMes = bodyMes;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public List<CpTermAndConditionDTO> getTermsConditionYesYes() {
		return termsConditionYesYes;
	}

	public void setTermsConditionYesYes(List<CpTermAndConditionDTO> termsConditionYesYes) {
		this.termsConditionYesYes = termsConditionYesYes;
	}

	public List<CpTermAndConditionDTO> getTermsConditionYesNo() {
		return termsConditionYesNo;
	}

	public void setTermsConditionYesNo(List<CpTermAndConditionDTO> termsConditionYesNo) {
		this.termsConditionYesNo = termsConditionYesNo;
	}

	public String getSelectedOperation() {
		return selectedOperation;
	}

	public boolean isEnableRedirection() {
		return enableRedirection;
	}

	public boolean isEnableSwitching() {
		return enableSwitching;
	}

	public boolean isEnableRedirctSwitch() {
		return enableRedirctSwitch;
	}

	public void setSelectedOperation(String selectedOperation) {
		this.selectedOperation = selectedOperation;
	}

	public void setEnableRedirection(boolean enableRedirection) {
		this.enableRedirection = enableRedirection;
	}

	public void setEnableSwitching(boolean enableSwitching) {
		this.enableSwitching = enableSwitching;
	}

	public void setEnableRedirctSwitch(boolean enableRedirctSwitch) {
		this.enableRedirctSwitch = enableRedirctSwitch;
	}

	public boolean isDisplayTin() {
		return displayTin;
	}

	public boolean isCountryPanel() {
		return countryPanel;
	}

	public void setDisplayTin(boolean displayTin) {
		this.displayTin = displayTin;
	}

	public void setCountryPanel(boolean countryPanel) {
		this.countryPanel = countryPanel;
	}

	public List<RedirectionDTO> getRedirectionList() {
		return redirectionList;
	}

	public void setRedirectionList(List<RedirectionDTO> redirectionList) {
		this.redirectionList = redirectionList;
	}

	public List<ListItem> getListOfRedirectFundNames() {
		return listOfRedirectFundNames;
	}

	public void setListOfRedirectFundNames(List<ListItem> listOfRedirectFundNames) {
		this.listOfRedirectFundNames = listOfRedirectFundNames;
	}

	public String getNewRedirectTotal() {
		return newRedirectTotal;
	}

	public void setNewRedirectTotal(String newRedirectTotal) {
		this.newRedirectTotal = newRedirectTotal;
	}

	public String getTitleMsg() {
		return titleMsg;
	}

	public void setTitleMsg(String titleMsg) {
		this.titleMsg = titleMsg;
	}

	public boolean isFatcaFlagpopUp() {
		return fatcaFlagpopUp;
	}

	public void setFatcaFlagpopUp(boolean fatcaFlagpopUp) {
		this.fatcaFlagpopUp = fatcaFlagpopUp;
	}

	public boolean isTermsCondition() {
		return termsCondition;
	}

	public void setTermsCondition(boolean termsCondition) {
		this.termsCondition = termsCondition;
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

	public boolean isTermsandcondition() {
		return termsandcondition;
	}

	public void setTermsandcondition(boolean termsandcondition) {
		this.termsandcondition = termsandcondition;
	}

	public BigDecimal getSeqno() {
		return seqno;
	}
	
	

	public void setSeqno(BigDecimal seqno) {
		this.seqno = seqno;
	}

	public boolean isRedirectbutton() {
		return redirectbutton;
	}

	public void setRedirectbutton(boolean redirectbutton) {
		this.redirectbutton = redirectbutton;
	}

	public boolean isSwitchbutton() {
		return switchbutton;
	}

	public void setSwitchbutton(boolean switchbutton) {
		this.switchbutton = switchbutton;
	}

	public boolean isRedirectswitchbutton() {
		return redirectswitchbutton;
	}

	public void setRedirectswitchbutton(boolean redirectswitchbutton) {
		this.redirectswitchbutton = redirectswitchbutton;
	}

	public String getDocFilepath() {
		return docFilepath;
	}

	public void setDocFilepath(String docFilepath) {
		this.docFilepath = docFilepath;
	}

	public String getInvestboolean() {
		return investboolean;
	}

	public void setInvestboolean(String investboolean) {
		this.investboolean = investboolean;
	}

	public boolean isInvestmentboolean() {
		return investmentboolean;
	}

	public void setInvestmentboolean(boolean investmentboolean) {
		this.investmentboolean = investmentboolean;
	}

	public boolean isInvestmentboolean1() {
		return investmentboolean1;
	}

	public void setInvestmentboolean1(boolean investmentboolean1) {
		this.investmentboolean1 = investmentboolean1;
	}

	public String getInvestementsourcecode() {
		return investementsourcecode;
	}

	public void setInvestementsourcecode(String investementsourcecode) {
		this.investementsourcecode = investementsourcecode;
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

	public boolean isRs() {
		return rs;
	}

	public void setRs(boolean rs) {
		this.rs = rs;
	}

	public boolean isRed() {
		return red;
	}

	public void setRed(boolean red) {
		this.red = red;
	}

	public boolean isSwic() {
		return swic;
	}

	public void setSwic(boolean swic) {
		this.swic = swic;
	}
	
	
	
	
	
	

}
