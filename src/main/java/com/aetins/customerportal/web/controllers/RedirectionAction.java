package com.aetins.customerportal.web.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
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
import com.aetins.customerportal.web.common.LoginSession;
import com.aetins.customerportal.web.dao.RedirectionDAO;
import com.aetins.customerportal.web.dao.impl.RedirectionDAOImpl;
import com.aetins.customerportal.web.dto.CpTermAndConditionDTO;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.dto.CpftEServiceDTO;
import com.aetins.customerportal.web.dto.FundDetailsDTO;
import com.aetins.customerportal.web.dto.ListItem;
import com.aetins.customerportal.web.dto.RedirectionDTO;
import com.aetins.customerportal.web.dto.ServiceRequestMasterDTO;
import com.aetins.customerportal.web.dto.StatementDTO;
import com.aetins.customerportal.web.dto.StrategyDTO;
import com.aetins.customerportal.web.entity.CPServiceRequestDocuments;
import com.aetins.customerportal.web.entity.CpRequestMaster;
import com.aetins.customerportal.web.service.CPServiceRequestDocumentsBL;
import com.aetins.customerportal.web.service.ContributionHolidaylBL;
import com.aetins.customerportal.web.service.CustomerPortalServices;
import com.aetins.customerportal.web.service.RedirectionBL;
import com.aetins.customerportal.web.service.impl.CustomerPortalServicesImpl;
import com.aetins.customerportal.web.service.impl.RedirectionBLImpl;
import com.aetins.customerportal.web.utils.BSLUtils;

@Controller
@Scope("session")
public class RedirectionAction extends BaseAction {
	
	
	private static final Logger logger = Logger.getLogger(RedirectionAction.class);
	
	@Autowired
	RedirectionBL redirectImpl;
	
	@Autowired
	CustomerPortalServices customerPortalServices;
	
	@Autowired
	TransactionServiceAction transAction;
	
	@Autowired
	LoginSession loginSession;
	
	@Autowired
	RedirectionDAO redirectionDAOImpl;
	
	private StatementDTO statementDetails;
	private List<RedirectionDTO> redirectionList = new ArrayList<RedirectionDTO>();
	
	private RedirectionDTO redirectionDTO;
	private boolean enableNextButton = false;
	// public static HttpSession session;
	private List<FundDetailsDTO> stratergyListLoc = new ArrayList<FundDetailsDTO>();
	private boolean addNewQuestion = false;
	private boolean autoRenewalPopup = false;
	private FundDetailsDTO addedStretgy;
	private String policyNo;
	public boolean isEdited;
	Double tot = 0.0;
	private String newTotal = String.valueOf(tot);
	private String total;
	private boolean fund;
	
	private List<CpUserInfoDTO> cpUserList = new ArrayList<CpUserInfoDTO>();
	CpUserInfoDTO userDetails = new CpUserInfoDTO();
	// private String policyNo;
	private List<ListItem> listOfFundNames;
	private List<ListItem> listOfFundNamesDummy;
	private Map<String, String> fundList;
	private String selectedFund;
	public boolean enablePrintButton = true;
	private String bodyMes;
	ServiceRequestMasterDTO requestdto = new ServiceRequestMasterDTO();
	int index;
	private List<CpftEServiceDTO> eserviceList;
	private String premiumStatus;
	private String planName;
	private List<CpTermAndConditionDTO> termsConditionYesYes;
	private List<CpTermAndConditionDTO> termsConditionYesNo;
	private String selectedOperation;
	private boolean enableRedirection = false;
	private boolean enableSwitching = false;
	private boolean enableRedirctSwitch = false;
	private String titleMsg;
	boolean zeroErrorFlag = true;
	
	boolean fatcaFlagpopUp;

	private List<StrategyDTO> redirectionStrategy = new ArrayList<StrategyDTO>();
	private List<StrategyDTO> strategyList = new ArrayList<StrategyDTO>();
	private List<StrategyDTO> strategyBuyList = new ArrayList<StrategyDTO>();
	private List<StrategyDTO> buyStrategyList = new ArrayList<StrategyDTO>();
	private List<StrategyDTO> strategyCodeList = new ArrayList<>();
	private List<StrategyDTO> strategyNameListArray = new ArrayList<>();
	private BigDecimal totalBuyingVal = BigDecimal.ZERO;
	Double tot1 = 0.00;
	Double rTot1 = 0.00;
	private String newTotal1 = String.valueOf(tot1);
	private String rNewTotal1 = String.valueOf(rTot1);

	
	
private BigDecimal seqno;

@Override
	public void init() {
//	FacesContext context = FacesContext.getCurrentInstance();
//	HomeDetailsAction homeAction = context.getApplication().evaluateExpressionGet(context, "#{homeDetailsAction}",
//	HomeDetailsAction.class);
//		policyNo = homeAction.getSearchPolicyList().getPlanNumber();
//		premiumStatus = homeAction.getSearchPolicyList().getPremiumPayStatus();
//		planName = homeAction.getSearchPolicyList().getProductName();
//		if(homeAction.getPolicyDetailsList().size()>0) {
//			for(int i=0;i<homeAction.getPolicyDetailsList().size();i++) {
//				if(policyNo.equalsIgnoreCase(homeAction.getPolicyDetailsList().get(i).getPolicyNo())){
//					seqno=homeAction.getPolicyDetailsList().get(i).getSeqno();
//				}
//			}
//		}
//		fetchRedirectFundListfromService();
//	    fetchFundDetailFromService();
		//redirectionStrategy();
		//strategySellingStrategy();
		//StrategyBuyingStrategy();
	}

   public void redirectioninit() {
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
			newTotal = String.valueOf(0.0);
			fetchRedirectFundListfromService();
		    fetchFundDetailFromService();
		    getSession().setAttribute("UPLOADFILENAME", "EMPTY");
		    getSession().setAttribute("FATCAFLAG", "");
		    fatcaValidation();
   }

   @Autowired
   ContributionHolidaylBL contributionHolidaylBL;
   
   Date serDate;
   private String fatcaFlag;
   
      
   public void fatcaValidation() {   
	   serDate = null;
	   int diffInDays = 0;
	   fatcaFlag = null;
	   fatcaFlagpopUp = false;
		List<CpRequestMaster> requestDate = contributionHolidaylBL.getTransactionCount(SecurityLibrary.getFullLoggedInUser().getVuserName(), "Y");
		logger.info("Fatca Master list size is in REDIRECTION ================" + requestDate.size());
		if (requestDate.size() > 0) {
			serDate = requestDate.get(0).getServiceRequestDate();
			diffInDays = (int) ((new Date().getTime() - serDate.getTime()) / (1000 * 60 * 60 * 24));
			System.out.println(diffInDays);
		}
		logger.info("Difference in days for fatca validation in REDIRECTION ==============" + diffInDays);
		if (diffInDays > 90 || requestDate.size() == 0) {
			fatcaFlag = "N";
			fatcaFlagpopUp = false;
			getSession().setAttribute("FATCAFLAG", fatcaFlag);
		} else {
			fatcaFlag = "Y";
			fatcaFlagpopUp = true;
			getSession().setAttribute("FATCAFLAG", fatcaFlag);
			Object fatcacondition = getSession().getAttribute("FATCAFLAG");
			System.out.println(fatcacondition);
		}
		logger.info("Fatca flag Status inside REDIRECTION =================" + fatcaFlag);
		logger.info("FatcaFlagpopUp flag Status REDIRECTION ===================" + fatcaFlagpopUp);
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
   
	// Added by viswa karthick on 08/07/2017
	public void fetchRedirectFundListfromService() {
		try {
			logger.info("Entering inside fetchRedirectFundListfromService in RedirectionAction ==========");
			redirectionList = customerPortalServices.getRedirectionDetails(policyNo, "EN",seqno);
			if (BSLUtils.isNotNull(redirectionList) && redirectionList.size() > 0) {
				for (RedirectionDTO redirectionDTO : redirectionList) {
					redirectionDTO.setMode("R");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Inside catch block of fetchRedirectFundListfromService() in RedirectionAction ------------"
					+ e.getMessage());
		}
	}

	public void fetchFundDetailFromService() {
		try {
			logger.info("Entering inside fetchFundDetailFromService in RedirectionAction ==========");
			listOfFundNames = customerPortalServices.getFundDetail("EN", "FUND", policyNo, "B");
			listOfFundNamesDummy = listOfFundNames;
			fundList = new LinkedHashMap<String, String>();
			for (ListItem listItem : listOfFundNames) {
				fundList.put(listItem.getDescription(), listItem.getCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Inside catch block of fetchFundDetailFromService() in RedirectionAction ------------"
					+ e.getMessage());
		}
	}
	

	public void getFundDetails(RedirectionDTO redirectionDTO, int index) {
		boolean renderSelectOne;
		List<ListItem> tempListOfFundNames = listOfFundNames;
		for (ListItem listItem : tempListOfFundNames) {
			if (listItem.getCode().equalsIgnoreCase(redirectionDTO.getFundCode())) {
				redirectionDTO.setFundCurrency(listItem.getCurrencyCode().toUpperCase());
				redirectionDTO.setFundName(listItem.getDescription());
				redirectionDTO.setFundCode(listItem.getCode());
				//tempListOfFundNames.remove(listItem);
				System.out.println(tempListOfFundNames.size());
				break;
			}
			listOfFundNames = tempListOfFundNames;
		}

	}

	public boolean totalpercentages() {
		int total = 0 ;
		for(RedirectionDTO obj: redirectionList) {
			total = obj.getValue().intValue() + total;	
			newTotal = Integer.toString(total);
		}
		if(total ==  100) {
		 return true;
		}else {
		 return false;
		}
	}
	
	public void btnContinueLatertest() {
		boolean errFlag = true;
		try {
			if(totalpercentages() == true) {
				requestdto.setServiceRequestDate(new Date());
				requestdto.setServiceRequestType(Constants.CUSTOMER_REDIRECTION);
				requestdto.setPolicyNo(policyNo);
				String userName = SecurityLibrary.getFullLoggedInUser().getVuserName();
				requestdto.setUserId(userName);
				requestdto.setRequestStatus(Constants.INACTIVE);
				requestdto.setSeqno(seqno);
				requestdto = transAction.insertServiceRequest(requestdto);
				for (int k = 0; k < redirectionList.size(); k++) {
					redirectionList.get(k).setMode("R");
					redirectionList.get(k).setServiceRequestNo(requestdto);
				}
				boolean status = redirectImpl.updateRedirection(redirectionList);
				if(status=true) {
					getSession().setAttribute("OTPSUCC", "DONE");
					getSession().setAttribute("TRANSACTION", "REDIRECTION");
					if(!fatcaFlagpopUp==true) {
						PrimeFaces.current().executeScript("PF('dlg3').show()");	
					}	
				}
				fatcaFlagpopUp = (boolean) getSession().getAttribute("FATCAFLAGPOP");
				if (status && fatcaFlagpopUp == true) {
					setEnablePrintButton(false);
				}
				
			}else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message", "Please re-check the process or contact Salama Support Team.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void btnContinueLatertest1() {
		boolean errFlag = true;
		try {
			if (!validateRedirectionList()) {
				errFlag = false;
			}
			if(errFlag1== true) {
				if (errFlag && zeroErrorFlag) {
					requestdto.setServiceRequestDate(new Date());
					requestdto.setServiceRequestType(Constants.CUSTOMER_REDIRECTION);
					requestdto.setPolicyNo(policyNo);
					String userName = SecurityLibrary.getFullLoggedInUser().getVuserName();
					requestdto.setUserId(userName);
					requestdto.setRequestStatus(Constants.INACTIVE);
					requestdto.setSeqno(seqno);
					requestdto = transAction.insertServiceRequest(requestdto);
					for (int k = 0; k < redirectionList.size(); k++) {
						redirectionList.get(k).setMode("R");
						redirectionList.get(k).setServiceRequestNo(requestdto);
					}
					boolean status = redirectImpl.updateRedirection(redirectionList);
					if(status=true) {
						getSession().setAttribute("OTPSUCC", "DONE");
						getSession().setAttribute("TRANSACTION", "REDIRECTION");
						if(!fatcaFlagpopUp==true) {
							PrimeFaces.current().executeScript("PF('dlg3').show()");	
						}	
					}
					fatcaFlagpopUp = (boolean) getSession().getAttribute("FATCAFLAGPOP");
					if (status && fatcaFlagpopUp == true) {
						setEnablePrintButton(false);
					}
				}	
			}else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message", "Please re-check the process or contact Salama Support Team.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void btnContinueLatertestfatca() {
		boolean errFlag = true;
		processing = null;
		processing = "NO";
		try {
			if(totalpercentages() == true) {
				requestdto.setServiceRequestDate(new Date());
				requestdto.setServiceRequestType(Constants.CUSTOMER_REDIRECTION);
				requestdto.setPolicyNo(policyNo);
				String userName = SecurityLibrary.getFullLoggedInUser().getVuserName();
				requestdto.setUserId(userName);
				requestdto.setRequestStatus(Constants.INACTIVE);
				requestdto.setSeqno(seqno);
				requestdto = transAction.insertServiceRequest(requestdto);
				for (int k = 0; k < redirectionList.size(); k++) {
					redirectionList.get(k).setMode("R");
					redirectionList.get(k).setServiceRequestNo(requestdto);
				}
				boolean status = redirectImpl.updateRedirection(redirectionList);
				if(status=true) {
					processing = "YES";
					getSession().setAttribute("OTPSUCC", "DONE"); 
					getSession().setAttribute("TRANSACTION", "REDIRECTION");	
				}
				fatcaFlagpopUp = (boolean) getSession().getAttribute("FATCAFLAGPOP");	
			}else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message", "Please re-check the process or contact Salama Support Team.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void btnContinueLatertestfatca1() {
		boolean errFlag = true;
		processing = null;
		processing = "NO";
		try {
			if (!validateRedirectionList()) {
				errFlag = false;
			}
			if(errFlag1== true) {
				if (errFlag && zeroErrorFlag) {
					requestdto.setServiceRequestDate(new Date());
					requestdto.setServiceRequestType(Constants.CUSTOMER_REDIRECTION);
					requestdto.setPolicyNo(policyNo);
					String userName = SecurityLibrary.getFullLoggedInUser().getVuserName();
					requestdto.setUserId(userName);
					requestdto.setRequestStatus(Constants.INACTIVE);
					requestdto.setSeqno(seqno);
					requestdto = transAction.insertServiceRequest(requestdto);
					for (int k = 0; k < redirectionList.size(); k++) {
						redirectionList.get(k).setMode("R");
						redirectionList.get(k).setServiceRequestNo(requestdto);
					}
					boolean status = redirectImpl.updateRedirection(redirectionList);
					if(status=true) {
						processing = "YES";
						getSession().setAttribute("OTPSUCC", "DONE"); 
						getSession().setAttribute("TRANSACTION", "REDIRECTION");	
					}
					fatcaFlagpopUp = (boolean) getSession().getAttribute("FATCAFLAGPOP");
				}	
			}else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message", "Please re-check the process or contact Salama Support Team.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
	
	public void clearValues() {

		fetchRedirectFundListfromService();
		for (RedirectionDTO each : redirectionList) {
			each.setValue(BigDecimal.ZERO);
			setNewTotal("");
		}
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
			
			if (BSLUtils.isNull(newTotal)) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
						"Redirection % should not be null/zero value.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				errFlag = false;
			}
						
			for (int i = 0; i < redirectionList.size(); i++) {
				/*
				 * if (redirectionList.get(i).getValue().equals(BigDecimal.ZERO)) { errFlag =
				 * false; } if(i==(redirectionList.size()-1) && !errFlag){
				 * displayErrorMessage("redirection.funds.errorNull"); }
				 */
			}

			/*
			 * for (RedirectionDTO each : redirectionList) {
			 * 
			 * if (BSLUtils.isNotNull(each.getCriteria())) { if
			 * (each.getValue().compareTo(BigDecimal.ZERO) == 0) { zeroErrorFlag = false; }
			 * } }
			 */			
			
			if (zeroErrorFlag == false) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
						"Redirection % should not be null/zero value.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
			
			

			if (BSLUtils.isNotNull(newTotal)) {
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
				
				for (RedirectionDTO each : redirectionList) {
					if (each.getValue().compareTo(BigDecimal.ZERO) > 0) {
						count++;
					}
				}

				if (count > 10) {
					displayErrorMessage("redirection.funds.error.exceedLength");
					errFlag = false;
				}

			}
		}
		return errFlag;
		
		
		
		
		
	}

	public void btnDeleteStratergy(RedirectionDTO redirection) {
		if (redirectionList != null) {
			if (redirectionList.size() > 0) {
				redirectionList.remove(redirection);
				System.out.println("NOW THE SIZE IS:" + redirectionList.size());
			}
		}
		calculateTotal();
	}

	public void addStretgy() {
    
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
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
					"Total number of funds should not exceed 10.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
	}

	public void beforeAddFund() {
		redirectionDTO = new RedirectionDTO();
		redirectionDTO.setNewRecord(true);
	}

	
	
	
//malik
	int indexs;
	boolean errFlag1;
	public void onChangeShare1(RedirectionDTO obj,int index) {
		
		indexs=0;
		errFlag1=true;
		for(int j=0;j<getRedirectionList().size();j++) {
			if(obj.getFundName().equalsIgnoreCase(getRedirectionList().get(j).getFundName())) {
				indexs++;
			}
		}
		if(indexs>=2) {
			errFlag1=false;
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
					"You can select same funds multiple time for the policy. FUND NAME:->"+obj.getFundName()+"");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
		
		for (int i = 0; i < getRedirectionList().size(); i++) {
			if (i == index) {
				getRedirectionList().get(index).setValue(obj.getValue());
			}
		}
		calculateTotal();
		isEdited = true;
	}
	
	public void onChangeShare(BigDecimal value, int index) {
		for (int i = 0; i < getRedirectionList().size(); i++) {
			if (i == index) {
				getRedirectionList().get(index).setValue(value);
			}
		}
		calculateTotal();
		isEdited = true;
	}

	private void calculateTotal() {

		BigDecimal iT = BSLUtils.toBigDecimal(0);
		for (RedirectionDTO obj : getRedirectionList()) {
			if (BSLUtils.isNotNull(obj) && BSLUtils.isNotNull(obj.getValue()))
				iT = iT.add(obj.getValue());
		}
		newTotal = iT.toPlainString();
		tot = iT.doubleValue();
		if (tot == 0.0) {
			setFund(true);
		} else {
			setFund(false);
		}
		if (tot >= 0.0 && tot < 100.0) {
			setFund(true);
		} else {
			setFund(false);
		}
		if (tot > 100.0) {
			setFund(true);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
					"Redirection % should be 100%.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		} else {
			setFund(false);
		}
	}

	/* Added By Arul 12-09-2019 */
	/* Strategy Data Processing */
	public void redirectionStrategy() {
		try {
			redirectionStrategy = customerPortalServices.sellingStrategyForStrategyTab(policyNo);
		} catch (Exception e) {
			logger.error("Error Messsage : " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void strategySellingStrategy() {
		try {
			strategyList = customerPortalServices.sellingStrategyForStrategyTab(policyNo);
		} catch (Exception e) {
			logger.error("Error Messsage : " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void StrategyBuyingStrategy() {
		try {
			strategyBuyList = customerPortalServices.buyingStrategyForStrategyTab(policyNo);
			for (StrategyDTO strategy : strategyBuyList) {
				StrategyDTO strategyList = new StrategyDTO();
				StrategyDTO strategyNameList = new StrategyDTO();
				strategyList.setStrategyCode(strategy.getStrategyCode());
				strategyNameList.setStrategyName(strategy.getStrategyName());
				strategyCodeList.add(strategyList);
				strategyNameListArray.add(strategyNameList);
			}
		} catch (Exception e) {
			logger.error("Error Messsage : " + e.getMessage());
			e.printStackTrace();
		}
	}

	public boolean redirectionAddButton() {
		int rowCount = strategyList.size();
		rowCount = strategyList.size() < 0 ? rowCount++ : 1;
		boolean setRow = rowCount < 4 ? true : false;
		if (setRow == false) {
			displayErrorMessageWithClientId("redirection.funds.error.exceedLength", "strategySellErr");
		} else {
			StrategyDTO strategyDTO = new StrategyDTO();
			strategyDTO.setNewRow(setRow);
			strategyList.add(strategyDTO);
		}
		return setRow;
	}

	public boolean StrategyRedAddButton() {
		int rowCount = redirectionStrategy.size();
		rowCount = redirectionStrategy.size() < 0 ? rowCount++ : 1;
		boolean setRow = rowCount < 4 ? true : false;
		if (setRow == false) {
			displayErrorMessageWithClientId("redirection.funds.error.exceedLength", "strategySellErr");
		} else {
			StrategyDTO strategyDTO = new StrategyDTO();
			strategyDTO.setNewRow(setRow);
			redirectionStrategy.add(strategyDTO);
		}
		return setRow;
	}

	public boolean StrategySellAddButton() {
		int rowCount = strategyList.size();
		rowCount = strategyList.size() < 0 ? rowCount++ : 1;
		boolean setRow = rowCount < 4 ? true : false;
		if (setRow == false) {
			displayErrorMessageWithClientId("redirection.funds.error.exceedLength", "strategySellErr");
		} else {
			StrategyDTO strategyDTO = new StrategyDTO();
			strategyDTO.setNewRow(setRow);
			strategyList.add(strategyDTO);
		}
		return setRow;
	}

	public boolean StrategyBuyAddButton() {
		int rowCount = buyStrategyList.size();
		rowCount = buyStrategyList.size() < 0 ? rowCount++ : rowCount;
		boolean setRow = rowCount < 4 ? true : false;
		if (setRow == false) {
			displayErrorMessageWithClientId("redirection.funds.error.exceedLength", "strategyBuyErr");
		} else {
			StrategyDTO strategyDTO = new StrategyDTO();
			strategyDTO.setNewRow(setRow);
			buyStrategyList.add(strategyDTO);
		}
		return setRow;
	}

	public void btnDeleteStratergyRed(StrategyDTO dto) {
		
		boolean delete = strategyList.size() > 0 ? true : false;
		if (delete == true) {
			for (StrategyDTO strategyDTO : redirectionStrategy) {
				if (strategyDTO.getStrategyCode().equalsIgnoreCase(dto.getStrategyCode())) {
					StrategyDTO sd = new StrategyDTO();
					sd.setStrategyName(strategyDTO.getStrategyName());
					sd.setCriteriaValue(strategyDTO.getCriteriaValue());
					strategyNameListArray.add(sd);
				}
			}
			redirectionStrategy.remove(dto);
		}
	}

	public void btnDeleteStratergySell(StrategyDTO dto) {
		boolean delete = strategyList.size() > 0 ? true : false;
		if (delete == true) {
			for (StrategyDTO strategyDTO : strategyList) {
				if (strategyDTO.getStrategyCode().equalsIgnoreCase(dto.getStrategyCode())) {
					StrategyDTO sd = new StrategyDTO();
					sd.setStrategyName(strategyDTO.getStrategyName());
					sd.setCriteriaValue(strategyDTO.getCriteriaValue());
					strategyNameListArray.add(sd);
				}
			}
			strategyList.remove(dto);
		}
	}

	public void btnDeleteStratergyBuyl(StrategyDTO dto) {
		boolean delete = buyStrategyList.size() > 0 ? true : false;
		if (delete == true) {
			for (StrategyDTO strategyDTO : buyStrategyList) {
				if (strategyDTO.getStrategyCode().equalsIgnoreCase(dto.getStrategyCode())) {
					StrategyDTO sd = new StrategyDTO();
					sd.setStrategyCode(strategyDTO.getStrategyCode());
					sd.setStrategyName(strategyDTO.getStrategyName());
					sd.setPercentage(strategyDTO.getPercentage());
					strategyCodeList.add(sd);
				}
			}
			buyStrategyList.remove(dto);
		}
	}

	public void onBuyingShare(int index) {
		buyingCalculateTotal(index);
		isEdited = true;
	}

	private void buyingCalculateTotal(int index2) {
		BigDecimal iT = BSLUtils.toBigDecimal(0);
		for (StrategyDTO obj : getBuyStrategyList()) {

			if (BSLUtils.isNotNull(obj) && BSLUtils.isNotNull(obj.getPercentage())) {
				iT = iT.add(obj.getPercentage());
			}
		}

		newTotal1 = iT.toPlainString();
		tot1 = iT.doubleValue();
		if (tot1 == 0.0) {
			setFund(true);

		} else {
			setFund(false);
		}
		if (tot1 >= 0.0 && tot1 < 100.0) {
			setFund(true);

		} else {
			setFund(false);
		}

		if (tot1 > 100.0) {
			displayErrorMessageWithClientId("customer.home.strategy.buyingerrormsg", "strategyBuyErr");
			setFund(true);

		} else {
			setFund(false);
		}
	}

	public void onRedirectionShare(int index) {
		redirectCalculateTotal(index);
		isEdited = true;
	}

	private void redirectCalculateTotal(int index2) {
		BigDecimal iT = BSLUtils.toBigDecimal(0);
		for (StrategyDTO obj : getRedirectionStrategy()) {

			if (BSLUtils.isNotNull(obj) && BSLUtils.isNotNull(obj.getCriteriaValueNew())) {
				iT = iT.add(obj.getCriteriaValueNew());
			}
		}

		rNewTotal1 = iT.toPlainString();
		rTot1 = iT.doubleValue();
		if (rTot1 == 0.0) {
			setFund(true);

		} else {
			setFund(false);
		}
		if (rTot1 >= 0.0 && rTot1 < 100.0) {
			setFund(true);

		} else {
			setFund(false);
		}

		if (rTot1 > 100.0) {
			displayErrorMessageWithClientId("customer.home.strategy.buyingerrormsg", "strategyRedErr");
			setFund(true);

		} else {
			setFund(false);
		}
	}

	/* validating submit action for startegy tab */

	public boolean validateStrRedirectionList() {
		boolean errFlag = true;
		boolean zeroFlag = true, abovehundFlag = true, sellingValue = false;
		int count = 0;
		if (BSLUtils.isNotNull(redirectionStrategy)) {
			if (redirectionStrategy.size() == 0) {
				displayErrorMessageWithClientId("redirection.strategy.sizeZero", "strategyRedErr");
				errFlag = false;
			}
			if (BSLUtils.isNull(rNewTotal1)) {
				displayErrorMessageWithClientId("redirection.strategy.errorNull", "strategyRedErr");
				errFlag = false;
			}
			if (BSLUtils.isNotNull(rNewTotal1)) {
				BigDecimal iT = BSLUtils.toBigDecimal(0);
				for (StrategyDTO obj : redirectionStrategy) {
					if (BSLUtils.isNotNull(obj) && BSLUtils.isNotNull(obj.getCriteriaValueNew()))
						iT = iT.add(obj.getCriteriaValueNew());
				}
				Double newTotal = iT.doubleValue();
				if (newTotal.equals(Double.parseDouble("0.0"))) {
					displayErrorMessageWithClientId("redirection.strategy.error", "strategyRedErr");
					errFlag = false;
				} else if (!newTotal.equals(Double.parseDouble("100.0"))) {
					displayErrorMessageWithClientId("redirection.strategy.error", "strategyRedErr");
					errFlag = false;
				}

				for (StrategyDTO each : redirectionStrategy) {
					if (each.getCriteriaValueNew().compareTo(BigDecimal.ZERO) > 0) {
						count++;
					}
				}
				if (count > 4) {
					displayErrorMessageWithClientId("redirection.strategy.error.exceedLength", "strategyRedErr");
					errFlag = false;
				}
			}
		}
		return errFlag;
	}

	public boolean validateSellingStrategy() {
		boolean errFlag = true;
		boolean zeroFlag = true, abovehundFlag = true, sellingValue = false;
		if (strategyList.size() > 0) {
			int i = 0;
			for (StrategyDTO strategyDTO : strategyList) {
				sellingValue = strategyDTO.getCriteriaValue().compareTo(BigDecimal.ZERO) > 0 ? true : false;
				abovehundFlag = errFlag = strategyDTO.getCriteriaValue().intValue() > 100 ? false : true;
				if ((++i == strategyList.size() - 1) && !zeroFlag) {
					displayErrorMessageWithClientId("customer.home.fund.sellingerrnotzero", "strategySellErr");
				} else if ((++i == strategyList.size() - 1) && !abovehundFlag) {
					displayErrorMessageWithClientId("customer.home.fund.value", "strategySellErr");
				}
			}
			if (sellingValue == false) {
				errFlag = false;
				displayErrorMessageWithClientId("customer.home.fund.sellingListNull", "strategySellErr");
			}
		}
		return errFlag;
	}

	public boolean validateBuyingStrategy() {
		boolean errFlag = true;
		boolean zeroFlag = true, abovehundFlag = true, sellingValue = false;
		if (buyStrategyList.size() > 0) {
			int i = 0;
			for (StrategyDTO strategyDTO : buyStrategyList) {
				sellingValue = strategyDTO.getPercentage().compareTo(BigDecimal.ZERO) > 0 ? true : false;
				abovehundFlag = errFlag = strategyDTO.getPercentage().intValue() > 100 ? false : true;
				if ((++i == buyStrategyList.size() - 1) && !zeroFlag) {
					displayErrorMessageWithClientId("customer.home.fund.buyingerrnotzero", "strategyBuyErr");
				} else if ((++i == buyStrategyList.size() - 1) && !abovehundFlag) {
					displayErrorMessageWithClientId("customer.home.fund.value", "strategyBuyErr");
				}
			}
			if (sellingValue == false) {
				errFlag = false;
				displayErrorMessageWithClientId("customer.home.fund.buyingListNull", "strategyBuyErr");
			}
		}
		return errFlag;
	}

	public void btnContinueRedirection() {
		boolean errFlag = true;
		try {
			if (!validateStrRedirectionList()) {
				errFlag = false;
			}
			if (errFlag && zeroErrorFlag == true) {
				requestdto.setServiceRequestDate(new Date());
				requestdto.setServiceRequestType(Constants.CUSTOMER_REDIRECTION);
				requestdto.setPolicyNo(policyNo);
				String userName = SecurityLibrary.getFullLoggedInUser().getVuserName();
				requestdto.setUserId(userName);
				requestdto.setRequestStatus(Constants.INACTIVE);
				requestdto = transAction.insertServiceRequest(requestdto);
				for (int k = 0; k < redirectionList.size(); k++) {
					redirectionList.get(k).setMode("R");
					redirectionList.get(k).setServiceRequestNo(requestdto);
				}
				boolean status = redirectImpl.updateRedirection(redirectionList);
				// clearValues();
				fatcaFlagpopUp = (boolean) getSession().getAttribute("FATCAFLAGPOP");
				if (status && fatcaFlagpopUp == true) {
					setEnablePrintButton(false);
					//RequestContext requestContext = RequestContext.getCurrentInstance();
					PrimeFaces.current().executeScript("showFATCA()");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void btnContinueSwitching() {
		boolean errFlag = true, flagRedirection = true, flagBuying = true, flagSelling = true, status = false;
		if (!validateStrRedirectionList()) {
			logger.info("Validation Pass in Reditection :::::::::::::::::");
			errFlag = false;
			flagRedirection = false;
		}
		if (!validateSellingStrategy()) {
			logger.info("- - - - - - - - - - - Validation Pass in Selling Strategy - - - - - - - - - - -");
			errFlag = flagSelling = false;
		}
		if (!validateBuyingStrategy()) {
			logger.info("- - - - - - - - - - - Validation Pass in Buying Strategy - - - - - - - - - -");
			errFlag = flagBuying = false;
		}
		List<StrategyDTO> buyList = new ArrayList<>();
		List<StrategyDTO> redirectList = new ArrayList<>();
		String userName = SecurityLibrary.getFullLoggedInUser().getVuserName();
		List<ServiceRequestMasterDTO> serviceRequestMasterDTOList = new ArrayList();
		if (flagBuying && flagSelling) {
			ServiceRequestMasterDTO serviceRequestMasterDTOSwitching = new ServiceRequestMasterDTO();
			serviceRequestMasterDTOSwitching.setServiceRequestDate(new Date());
			serviceRequestMasterDTOSwitching.setPolicyNo(policyNo);
			serviceRequestMasterDTOSwitching.setUserId(userName);
			serviceRequestMasterDTOSwitching.setRequestStatus(Constants.INACTIVE);
			serviceRequestMasterDTOSwitching.setRecentUpdateDate(new Date());
			serviceRequestMasterDTOSwitching.setServiceRequestType(Constants.CUSTOMER_SWITCHING);
			serviceRequestMasterDTOList.add(serviceRequestMasterDTOSwitching);
			serviceRequestMasterDTOList = transAction.insertServiceRequest(serviceRequestMasterDTOList);
			if (serviceRequestMasterDTOList != null) {
				if (serviceRequestMasterDTOList.size() > 0) {
					for (ServiceRequestMasterDTO eachRecord : serviceRequestMasterDTOList) {
						if (eachRecord.getServiceRequestType() != null) {
							if (eachRecord.getServiceRequestType().equalsIgnoreCase(Constants.CUSTOMER_REDIRECTION)) {
								// redirectionList
								if (BSLUtils.isNotNull(strategyList) && strategyList.size() > 0) {
									for (StrategyDTO redirectionDTO : strategyList) {
										if (selectedOperation.equalsIgnoreCase("RS")) {
											redirectionDTO.setMode("R");
										}
										redirectionDTO.setServiceRequestNo(eachRecord);
										redirectList.add(redirectionDTO);
									}
								}
							}
							if (eachRecord.getServiceRequestType().equalsIgnoreCase(Constants.CUSTOMER_SWITCHING)) {
								// selling Fund
								if (BSLUtils.isNotNull(strategyList) && strategyList.size() > 0) {
									for (StrategyDTO strategyDTO : strategyList) {
										if (selectedOperation.equalsIgnoreCase("RS")) {
											strategyDTO.setMode("S");
										}
										strategyDTO.setServiceRequestNo(eachRecord);
										if (strategyDTO.getCriteriaValue().compareTo(BigDecimal.ZERO) > 0) {
											buyList.add(strategyDTO);
										}
									}
								}
								// buying fund
								if (BSLUtils.isNotNull(buyStrategyList) && buyStrategyList.size() > 0) {
									for (StrategyDTO strategyDTO : buyStrategyList) {
										if (selectedOperation.equalsIgnoreCase("RS")) {
											strategyDTO.setMode("B");
										}
										strategyDTO.setServiceRequestNo(eachRecord);
										buyList.add(strategyDTO);
									}
								}
							}
						}
					}
				}
			}
			logger.info(
					"- - - - - - - - - - INSERT START INTO STRATEGY REDIRECTION AND SWITCHING TABLE - - - - - - - - - -");
			status = redirectImpl.updateRedirectionStrtegy(redirectList);
			status = redirectImpl.updateRedirectionStrtegy(buyList);
			logger.info(
					"- - - - - - - - - - INSERT FINISH INTO STRATEGY REDIRECTION AND SWITCHING TABLE - - - - - - - - - - ");
		}
		if (status && fatcaFlagpopUp == true) {
			///RequestContext requestContext = RequestContext.getCurrentInstance();
			PrimeFaces.current().executeScript("fatcaREDSWITCH()");
		}
	}

	public void btnContinueRedAndSwitch() {
		boolean errFlag = true, flagRedirection = true, flagBuying = true, flagSelling = true, status = false;
		if (!validateStrRedirectionList()) {
			logger.info("Validation Pass in Reditection :::::::::::::::::");
			errFlag = false;
			flagRedirection = false;
		}
		if (!validateSellingStrategy()) {
			logger.info("- - - - - - - - - - - Validation Pass in Selling Strategy - - - - - - - - - - -");
			errFlag = flagSelling = false;
		}
		if (!validateBuyingStrategy()) {
			logger.info("- - - - - - - - - - - Validation Pass in Buying Strategy - - - - - - - - - -");
			errFlag = flagBuying = false;
		}
		List<StrategyDTO> buyList = new ArrayList<>();
		List<StrategyDTO> redirectList = new ArrayList<>();
		if (errFlag) {
			getSession().setAttribute("REDIRECTION_AND_SWITHCHING", true);
			String userName = SecurityLibrary.getFullLoggedInUser().getVuserName();
			List<ServiceRequestMasterDTO> serviceRequestMasterDTOList = new ArrayList();
			if (flagRedirection) {
				ServiceRequestMasterDTO serviceRequestMasterDTORedirection = new ServiceRequestMasterDTO();
				serviceRequestMasterDTORedirection.setServiceRequestDate(new Date());
				serviceRequestMasterDTORedirection.setServiceRequestType(Constants.CUSTOMER_REDIRECTION);
				serviceRequestMasterDTORedirection.setPolicyNo(policyNo);
				serviceRequestMasterDTORedirection.setUserId(userName);
				serviceRequestMasterDTORedirection.setRequestStatus(Constants.INACTIVE);
				serviceRequestMasterDTORedirection.setRecentUpdateDate(new Date());
				logger.info("- - - - - - - - - - INSERT STRATEGY REDIRECTION INTO MASTER TABLE - - - - - - - - - -");
				serviceRequestMasterDTOList.add(serviceRequestMasterDTORedirection);
				logger.info(
						"- - - - - - - - - - INSERTED STRATEGY REDIRECTION INTO MASTER TABLE - - - - - - - - - -  EXIT");
				if (flagBuying && flagSelling) {
					ServiceRequestMasterDTO serviceRequestMasterDTOSwitching = new ServiceRequestMasterDTO();
					serviceRequestMasterDTOSwitching.setServiceRequestDate(new Date());
					serviceRequestMasterDTOSwitching.setPolicyNo(policyNo);
					serviceRequestMasterDTOSwitching.setUserId(userName);
					serviceRequestMasterDTOSwitching.setRequestStatus(Constants.INACTIVE);
					serviceRequestMasterDTOSwitching.setRecentUpdateDate(new Date());
					serviceRequestMasterDTOSwitching.setServiceRequestType(Constants.CUSTOMER_SWITCHING);
					serviceRequestMasterDTOList.add(serviceRequestMasterDTOSwitching);
					serviceRequestMasterDTOList = transAction.insertServiceRequest(serviceRequestMasterDTOList);
					if (serviceRequestMasterDTOList != null) {
						if (serviceRequestMasterDTOList.size() > 0) {
							for (ServiceRequestMasterDTO eachRecord : serviceRequestMasterDTOList) {
								if (eachRecord.getServiceRequestType() != null) {
									if (eachRecord.getServiceRequestType()
											.equalsIgnoreCase(Constants.CUSTOMER_REDIRECTION)) {
										// redirectionList
										if (BSLUtils.isNotNull(strategyList) && strategyList.size() > 0) {
											for (StrategyDTO redirectionDTO : strategyList) {
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
										// selling Fund
										if (BSLUtils.isNotNull(strategyList) && strategyList.size() > 0) {
											for (StrategyDTO strategyDTO : strategyList) {
												if (selectedOperation.equalsIgnoreCase("RS")) {
													strategyDTO.setMode("S");
												}
												strategyDTO.setServiceRequestNo(eachRecord);
												if (strategyDTO.getCriteriaValue().compareTo(BigDecimal.ZERO) > 0) {
													buyList.add(strategyDTO);
												}
											}
										}
										// buying fund
										if (BSLUtils.isNotNull(buyStrategyList) && buyStrategyList.size() > 0) {
											for (StrategyDTO strategyDTO : buyStrategyList) {
												if (selectedOperation.equalsIgnoreCase("RS")) {
													strategyDTO.setMode("B");
												}
												strategyDTO.setServiceRequestNo(eachRecord);
												buyList.add(strategyDTO);
											}
										}
									}
								}
							}
						}
					}
					logger.info(
							"- - - - - - - - - - INSERT START INTO STRATEGY REDIRECTION AND SWITCHING TABLE - - - - - - - - - -");
					status = redirectImpl.updateRedirectionStrtegy(redirectList);
					status = redirectImpl.updateRedirectionStrtegy(buyList);
					logger.info(
							"- - - - - - - - - - INSERT FINISH INTO STRATEGY REDIRECTION AND SWITCHING TABLE - - - - - - - - - - ");
				}
			}
			if (status && fatcaFlagpopUp == true) {
				//RequestContext requestContext = RequestContext.getCurrentInstance();
				PrimeFaces.current().executeScript("fatcaREDSWITCH()");
			}
		}
	}

	public void getStrategyDetails(StrategyDTO strategyDTO, int index) {
		try {
			List<StrategyDTO> tempListOfFundNames = strategyBuyList;
			for (StrategyDTO listItem : tempListOfFundNames) {
				if (strategyDTO.getStrategyCode() != null) {
					if (listItem.getStrategyCode().equalsIgnoreCase(strategyDTO.getStrategyCode())) {
						strategyDTO.setStrategyCode(listItem.getStrategyCode());
						strategyDTO.setStrategyName(listItem.getStrategyName());
						strategyDTO.setPercentage(listItem.getPercentage());
						tempListOfFundNames.remove(listItem);
						break;
					}
				}
			}
			strategyCodeList = tempListOfFundNames;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getStrategyNameDetails(StrategyDTO strategyDTO, int index) {
		try {
			List<StrategyDTO> tempListOfFundNames = strategyBuyList;
			for (StrategyDTO listItem : tempListOfFundNames) {
				if (strategyDTO.getStrategyName() != null) {
					if (listItem.getStrategyName().equalsIgnoreCase(strategyDTO.getStrategyName())) {
						strategyDTO.setStrategyCode(listItem.getStrategyCode());
						strategyDTO.setStrategyName(listItem.getStrategyName());
						strategyDTO.setPercentage(listItem.getPercentage());
						tempListOfFundNames.remove(listItem);
						break;
					}
				}
			}
			strategyNameListArray = tempListOfFundNames;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setStatus() {
		selectedOperation = null;
		enableRedirection = enableSwitching = enableRedirctSwitch = false;
	}

	/* Displaying Selected View For Strategy */
	public void displaySelectedProcess() {
		enableRedirection = enableSwitching = enableRedirctSwitch = false;
		//RequestContext requestContext = RequestContext.getCurrentInstance();
		if (BSLUtils.isNotNull(selectedOperation)) {
			/* Redirection Page */
			if (selectedOperation.equalsIgnoreCase("R")) {
				String result = customerPortalServices.getAllowedTransaction(policyNo, "REDIRECT");
				Long count = redirectionDAOImpl.getRowCount(Constants.CUSTOMER_REDIRECTION, policyNo);
				System.out.println("count==" + count);
				long tmp = 0;
				int output = count.compareTo(tmp);
				if (output > 0) {
					PrimeFaces.current().executeScript("$('#validation-TabStrategy').modal('show');");
					setBodyMes("strategy " + text.getString("home.error.contriHoliday.alreadyDone"));
				}
				logger.info("Transaction Strategy called for Redirection  ==============" + result);

				if (BSLUtils.isNotNull(result)) {
					if (!result.equalsIgnoreCase("ALLOWED")) {
						PrimeFaces.current().executeScript("$('#validation-TabStrategy').modal('show');");
						setBodyMes("strategy " + text.getString("home.error.transactionAllowedOrNot"));
					}
				}
				enableRedirection = true;
			}

			/* Switching Page */
			if (selectedOperation.equalsIgnoreCase("S")) {
				String result = customerPortalServices.getAllowedTransaction(policyNo, "SWITCHING");
				Long count = redirectionDAOImpl.getRowCount(Constants.CUSTOMER_SWITCHING, policyNo);
				System.out.println("count==" + count);
				long tmp = 0;
				int output = count.compareTo(tmp);
				if (output > 0) {
					PrimeFaces.current().executeScript("$('#validation-TabStrategy').modal('show');");
					setBodyMes("strategy " + text.getString("home.error.contriHoliday.alreadyDone"));
				}
				logger.info("Transaction Strategy called for Switching   ==============" + result);
				if (BSLUtils.isNotNull(result)) {
					if (!result.equalsIgnoreCase("ALLOWED")) {
						PrimeFaces.current().executeScript("$('#validation-TabStrategy').modal('show');");
						setBodyMes("strategy " + text.getString("home.error.transactionAllowedOrNot"));
					}
				}
				enableSwitching = true;
			}

			/* Redirection and Switching */
			if (selectedOperation.equalsIgnoreCase("RS")) {
				String result = customerPortalServices.getAllowedTransaction(policyNo, "BOTH_SW_RD");
				Long count_switch = redirectionDAOImpl.getRowCount(Constants.CUSTOMER_SWITCHING, policyNo);
				Long count_red = redirectionDAOImpl.getRowCount(Constants.CUSTOMER_REDIRECTION, policyNo);
				long tmp = 0;
				int output_switch = count_switch.compareTo(tmp);
				int output_red = count_red.compareTo(tmp);
				if (output_switch > 0 || output_red > 0) {
					PrimeFaces.current().executeScript("$('#validation-TabStrategy').modal('show');");
					setBodyMes("strategy " + text.getString("home.error.contriHoliday.alreadyDone"));
				}
				logger.info("Transaction Strategy called for Redirection and Switching   ==============" + result);
				if (BSLUtils.isNotNull(result)) {
					if (!result.equalsIgnoreCase("ALLOWED")) {
						PrimeFaces.current().executeScript("$('#validation-TabStrategy').modal('show');");
						setBodyMes("strategy " + text.getString("home.error.transactionAllowedOrNot"));
					}
				}
				enableRedirctSwitch = true;
			}
		}
	}

	/* End Of Arul Code */
	
	
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

	public List<RedirectionDTO> getRedirectionList() {
		return redirectionList;
	}

	public void setRedirectionList(List<RedirectionDTO> redirectionList) {
		this.redirectionList = redirectionList;
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

	/*
	 * public static HttpSession getSession() { return session; }
	 * 
	 * public static void setSession(HttpSession session) {
	 * RedirectionAction.session = session; }
	 */

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

	public String getNewTotal() {
		return newTotal;
	}

	public void setNewTotal(String newTotal) {
		this.newTotal = newTotal;
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
		return tot;
	}

	public List<CpUserInfoDTO> getCpUserList() {
		return cpUserList;
	}

	public void setCpUserList(List<CpUserInfoDTO> cpUserList) {
		this.cpUserList = cpUserList;
	}

	public boolean isEnablePrintButton() {
		return enablePrintButton;
	}

	public void setEnablePrintButton(boolean enablePrintButton) {
		this.enablePrintButton = enablePrintButton;
	}

	public String getSelectedFund() {
		return selectedFund;
	}

	public void setSelectedFund(String selectedFund) {
		this.selectedFund = selectedFund;
	}

	public CpUserInfoDTO getUserDetails() {
		return userDetails;
	}

	public List<ListItem> getListOfFundNames() {
		return listOfFundNames;
	}

	public void setUserDetails(CpUserInfoDTO userDetails) {
		this.userDetails = userDetails;
	}

	public void setListOfFundNames(List<ListItem> listOfFundNames) {
		this.listOfFundNames = listOfFundNames;
	}

	public String getBodyMes() {
		return bodyMes;
	}

	public void setBodyMes(String bodyMes) {
		this.bodyMes = bodyMes;
	}

	private boolean termsCondition;

	public boolean isTermsCondition() {
		return termsCondition;
	}

	public void setTermsCondition(boolean termsCondition) {
		this.termsCondition = termsCondition;
	}

	public Map<String, String> getFundList() {
		return fundList;
	}

	public void setFundList(Map<String, String> fundList) {
		this.fundList = fundList;
	}

	public List<CpftEServiceDTO> getEserviceList() {
		return eserviceList;
	}

	public void setEserviceList(List<CpftEServiceDTO> eserviceList) {
		this.eserviceList = eserviceList;
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

	public ServiceRequestMasterDTO getRequestdto() {
		return requestdto;
	}

	public void setRequestdto(ServiceRequestMasterDTO requestdto) {
		this.requestdto = requestdto;
	}

	public String getSelectedOperation() {
		return selectedOperation;
	}

	public void setSelectedOperation(String selectedOperation) {
		this.selectedOperation = selectedOperation;
	}

	public boolean isEnableRedirection() {
		return enableRedirection;
	}

	public void setEnableRedirection(boolean enableRedirection) {
		this.enableRedirection = enableRedirection;
	}

	public boolean isEnableSwitching() {
		return enableSwitching;
	}

	public boolean isEnableRedirctSwitch() {
		return enableRedirctSwitch;
	}

	public void setEnableSwitching(boolean enableSwitching) {
		this.enableSwitching = enableSwitching;
	}

	public void setEnableRedirctSwitch(boolean enableRedirctSwitch) {
		this.enableRedirctSwitch = enableRedirctSwitch;
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

	public List<StrategyDTO> getRedirectionStrategy() {
		return redirectionStrategy;
	}

	public void setRedirectionStrategy(List<StrategyDTO> redirectionStrategy) {
		this.redirectionStrategy = redirectionStrategy;
	}

	public List<StrategyDTO> getStrategyList() {
		return strategyList;
	}

	public void setStrategyList(List<StrategyDTO> strategyList) {
		this.strategyList = strategyList;
	}

	public List<StrategyDTO> getStrategyBuyList() {
		return strategyBuyList;
	}

	public void setStrategyBuyList(List<StrategyDTO> strategyBuyList) {
		this.strategyBuyList = strategyBuyList;
	}

	public List<StrategyDTO> getStrategyCodeList() {
		return strategyCodeList;
	}

	public void setStrategyCodeList(List<StrategyDTO> strategyCodeList) {
		this.strategyCodeList = strategyCodeList;
	}

	public List<StrategyDTO> getStrategyNameListArray() {
		return strategyNameListArray;
	}

	public void setStrategyNameListArray(List<StrategyDTO> strategyNameListArray) {
		this.strategyNameListArray = strategyNameListArray;
	}

	public List<StrategyDTO> getBuyStrategyList() {
		return buyStrategyList;
	}

	public void setBuyStrategyList(List<StrategyDTO> buyStrategyList) {
		this.buyStrategyList = buyStrategyList;
	}

	public BigDecimal getTotalSellingVal() {
		return totalBuyingVal;
	}

	public void setTotalSellingVal(BigDecimal totalSellingVal) {
		this.totalBuyingVal = totalSellingVal;
	}

	public Double getTot1() {
		return tot1;
	}

	public void setTot1(Double tot1) {
		this.tot1 = tot1;
	}

	public String getNewTotal1() {
		return newTotal1;
	}

	public void setNewTotal1(String newTotal1) {
		this.newTotal1 = newTotal1;
	}

	public Double getrTot1() {
		return rTot1;
	}

	public void setrTot1(Double rTot1) {
		this.rTot1 = rTot1;
	}

	public String getrNewTotal1() {
		return rNewTotal1;
	}

	public void setrNewTotal1(String rNewTotal1) {
		this.rNewTotal1 = rNewTotal1;
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
