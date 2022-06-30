package com.aetins.customerportal.web.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import com.aetins.customerportal.web.dto.CpTermAndConditionDTO;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.dto.FundDetailsDTO;
import com.aetins.customerportal.web.dto.ListItem;
import com.aetins.customerportal.web.dto.RedirectionDTO;
import com.aetins.customerportal.web.dto.ServiceRequestMasterDTO;
import com.aetins.customerportal.web.dto.StatementDTO;
import com.aetins.customerportal.web.dto.UserDTO;
import com.aetins.customerportal.web.entity.CPServiceRequestDocuments;
import com.aetins.customerportal.web.entity.CpRequestMaster;
import com.aetins.customerportal.web.service.CPServiceRequestDocumentsBL;
import com.aetins.customerportal.web.service.ContributionHolidaylBL;
import com.aetins.customerportal.web.service.CustomerPortalServices;
import com.aetins.customerportal.web.service.RedirectionBL;
import com.aetins.customerportal.web.service.impl.CustomerPortalServicesImpl;
import com.aetins.customerportal.web.service.impl.RedirectionBLImpl;
import com.aetins.customerportal.web.utils.ApplicationMailer;
import com.aetins.customerportal.web.utils.BSLUtils;

@Controller
@Scope("session")
public class SwitchingAction extends BaseAction {
	
	private static final Logger logger = Logger.getLogger(SwitchingAction.class);
	
	@Autowired
	RedirectionBL redirectImpl;
	
	@Autowired
	ApplicationMailer appMailer;
	
	@Autowired
	CustomerPortalServices customerPortalServices;
	
	@Autowired
	TransactionServiceAction transAction;
	
	@Autowired
	LoginSession loginSession;
	
	private StatementDTO statementDetails;
	private List<RedirectionDTO> redirectionList = new ArrayList<RedirectionDTO>();
	private List<ListItem> listItemList = new ArrayList<ListItem>();
	
	private RedirectionDTO redirectionDTO;
	private ListItem listItem;
	private List<FundDetailsDTO> stratergyListLoc = new ArrayList<FundDetailsDTO>();
	private ServiceRequestMasterDTO serviceRequestMasterDTO;
	private boolean addNewQuestion = false;
	private boolean autoRenewalPopup = false;
	private FundDetailsDTO addedStretgy;
	private String policyNo;
	public boolean isEdited;
	private String newTotal = "";
	private String total;
	Double tot;
	Double tot1=0.00;
	private String newTotal1 = String.valueOf(tot1);
	private UserDTO userDetails1 = new UserDTO();
	
	private String errOtp;
	private boolean fund;
	
	private List<CpUserInfoDTO> cpUserList = new ArrayList<CpUserInfoDTO>();
	private UserDTO userDetails = new UserDTO();
	private List<ListItem> listOfFundNames;
	private List<ListItem> listItems;
	private String selectedFund;
	private boolean checkBox;
	private RedirectionBLImpl redirectionBL;
	private String premiumStatus;
	private String planName;
	private List<RedirectionDTO> sellingFundsList = new ArrayList<RedirectionDTO>();
	private List<RedirectionDTO> buyingFundsList = new ArrayList<RedirectionDTO>();
	List<RedirectionDTO> continueLaterList = new ArrayList<RedirectionDTO>();
	private boolean switchenablePrint = true;
	private Map<String, String> switchFundList;
	private int listCount;
	private List<CpTermAndConditionDTO> termsConditionYesYes;
	private List<CpTermAndConditionDTO> termsConditionYesNo;
	private String selectedOperation;
	private boolean enableRedirection = false;
	private boolean enableSwitching = false;
	private boolean enableRedirctSwitch = false;
	private String titleMsg;
	
	boolean fatcaFlagpopUpSwitch;
	private BigDecimal totalSellingVal= BigDecimal.ZERO;
	
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
//		
//		if(homeAction.getPolicyDetailsList().size()>0) {
//			for(int i=0;i<homeAction.getPolicyDetailsList().size();i++) {
//				if(policyNo.equalsIgnoreCase(homeAction.getPolicyDetailsList().get(i).getPolicyNo())){
//					seqno=homeAction.getPolicyDetailsList().get(i).getSeqno();
//				}
//			}
//		}
//		
//		fetchSellingFundListfromService();
//		fetchFundDetailFromService();
	}
	
	
	private String investementsourcecode;
	
	
	public void switchinginit() {
		investementsourcecode="";
		buyingFundsList=null;
		buyingFundsList= new ArrayList<RedirectionDTO>();
		newTotal1= String.valueOf(0.0);
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
		fetchSellingFundListfromService();
		fetchFundDetailFromService();
		getSession().setAttribute("FATCAFLAG", "");
		fatcaValidation();
	}
	
	@Autowired
	ContributionHolidaylBL contributionHolidaylBL;
	   
	Date serDate;
	private String fatcaFlag;
	boolean fatcaFlagpopUp;
	
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
	
	//Added by viswa karthick on 08/07/2017
	public void fetchSellingFundListfromService() {
		try {
			logger.info("Entering inside fetchSellingFundListfromService in SwitchingAction ==========");
			sellingFundsList = customerPortalServices.getRedirectionDetails(policyNo, "EN",seqno);
			if (BSLUtils.isNotNull(redirectionList) && redirectionList.size() > 0) {
				redirectionList.size();
				for (RedirectionDTO redirectionDTO : redirectionList) {
					sellingFundsList.add(redirectionDTO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Inside catch block of fetchSellingFundListfromService() in SwitchingAction ------------"
					+ e.getMessage());
		}
	}
	
	public void fetchFundDetailFromService() {
		try {
			logger.info("Entering inside fetchFundDetailFromService in SwitchingAction ==========");
			listItems = customerPortalServices.getFundDetail("EN", "FUND", policyNo, "B");
			switchFundList = new LinkedHashMap<String, String>();
			for (ListItem listItem : listItems) {
				switchFundList.put(listItem.getDescription(), listItem.getCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Inside catch block of fetchFundDetailFromService() in SwitchingAction ------------"
					+ e.getMessage());
		}
	}

	public void getFundDetails(RedirectionDTO redirectionDTO, int index) {
		try {
			List<ListItem> tempListOfFundNames=listItems;

			for (ListItem listItem : tempListOfFundNames) {
				if (listItem.getCode().equalsIgnoreCase(redirectionDTO.getFundCode())) {
					redirectionDTO.setFundCurrency(listItem.getCurrencyCode().toUpperCase());
					redirectionDTO.setFundName(listItem.getDescription());
					redirectionDTO.setFundCode(listItem.getCode());
					redirectionDTO.setFundUnitPrice(listItem.getUnits());
					//tempListOfFundNames.remove(listItem);
					break;
				}
				listItems=tempListOfFundNames;
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
				System.out.println("NOW THE SIZE IS:" + redirectionList.size());
			}
		}
		int index = 0;
		buyingCalculateTotal(index);
	}

	public void addStretgy2() {
	//buyingFundsList = new ArrayList<RedirectionDTO>();
		int count = 0;
		for (RedirectionDTO each : buyingFundsList) {
			
			if(each.getValue().compareTo(BigDecimal.ZERO) > 0) {
				count++;
			}	 
		}
		
       for (RedirectionDTO each : sellingFundsList) {
			
			if(each.getValue().compareTo(BigDecimal.ZERO) > 0) {
				count++;
			}	 
	   }

		if (count < 10) {
			
			if (redirectionList != null) {
				redirectionDTO = new RedirectionDTO();
				redirectionDTO.setNewRecord(true);
				redirectionDTO.setValue(BigDecimal.ZERO);
				buyingFundsList.add(redirectionDTO);
			} else {
				redirectionList = new ArrayList<RedirectionDTO>();
				redirectionDTO = new RedirectionDTO();
				redirectionDTO.setNewRecord(true);
				redirectionDTO.setValue(BigDecimal.ZERO);
				buyingFundsList.add(redirectionDTO);
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
	

	private boolean calculateTotal() {
		boolean errFlag = true;

		if (BSLUtils.isNotNull(sellingFundsList) && sellingFundsList.size() > 0) {
			for (RedirectionDTO obj : sellingFundsList) {
				
				if (obj.getValue().intValue() <= 100 && obj.getValue().intValue() > 0) {
					obj.getValue();
				} else if (obj.getValue().intValue() == 0) {
					displayErrorMessageWithClientId("customer.home.fund.sellingerrnotzero", "sellingFundErrorMessage");
					errFlag = false;
				} else {
					displayErrorMessageWithClientId("customer.home.fund.value", "sellingFundErrorMessage");
					errFlag = false;
				}
			}
		}
		return errFlag;
	}

	public void onChangeShare(int index) {
		
		if (BSLUtils.isNotNull(sellingFundsList) && sellingFundsList.size() > 0) {
			for (int i = 0; i < sellingFundsList.size(); i++) {	
				if(i==index){
					if (sellingFundsList.get(i).getValue().intValue()<=100 ) {
						sellingFundsList.get(i).getValue();	
						BigDecimal value = sellingFundsList.get(i).getValue().divide(BigDecimal.valueOf(100));
						BigDecimal fundPlanCurr = sellingFundsList.get(i).getFundPlanCurr();
						BigDecimal switchVal = value.multiply(fundPlanCurr);
						totalSellingVal = totalSellingVal.add(switchVal);
						System.out.println(":::::::::::::::::" +switchVal);
					}
					else{
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
								"Selling fund should not be greater than 100%.");
						PrimeFaces.current().dialog().showMessageDynamic(message);	
					}
				}
			}
		} 
		isEdited = true;
	}
	
//malik	
	int indexs;
	boolean errFlag1;
	public void onBuyingChangeShare(RedirectionDTO obj, int index) {
		indexs=0;
		errFlag1=true;
		for(int j=0;j<buyingFundsList.size();j++){
			if(obj.getFundName().equalsIgnoreCase(buyingFundsList.get(j).getFundName())) {
				indexs++;
			}
		}
		
		if(indexs>=2) {
			errFlag1=false;
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
					"You can select same funds multiple time for the policy. FUND NAME:->"+obj.getFundName()+"");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
		

		
		buyingCalculateTotal(index);
		isEdited = true;
	}

	private void buyingCalculateTotal(int index) {

		BigDecimal iT = BSLUtils.toBigDecimal(0);
		for (RedirectionDTO obj : getBuyingFundsList()) {

			if (BSLUtils.isNotNull(obj) && BSLUtils.isNotNull(obj.getValue())) {
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
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
					"Buying fund  should equal to 100%.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
			setFund(true);

		} else {
			setFund(false);
		}

	}
	
	public void displaySelectedPanel() {

		setEnableRedirection(false);
		setEnableSwitching(false);
		setEnableRedirctSwitch(false);

		if (BSLUtils.isNotNull(selectedOperation)) {
			if (selectedOperation.equalsIgnoreCase("R")) {
				setEnableRedirection(true);
			}
			if (selectedOperation.equalsIgnoreCase("S")) {
				setEnableSwitching(true);
			}
			if (selectedOperation.equalsIgnoreCase("RS")) {
				setEnableRedirctSwitch(true);
			}
		}
	}
	
	public boolean investsetting() {
		FacesContext context = FacesContext.getCurrentInstance();
		RedirectionSwitchingAction rsaction = context.getApplication().evaluateExpressionGet(context, "#{redirectionSwitchingAction}",RedirectionSwitchingAction.class);
		String resulting=rsaction.getInvestboolean();
		boolean errFlag=true;
		if(resulting == null || resulting.equalsIgnoreCase("")) {
			
		}else {
			if(resulting.equalsIgnoreCase("Y")) {
				investementsourcecode="IU-AU";
			}
		}
		
		
		return errFlag;
	}
	
	public void btnContinueLater() {
		boolean errFlag=true;
		if(!validateSellingFundList()){
			errFlag = false;
		}
		if(!validateBuyingFundList()){
			errFlag = false;
		}
		
		if(!investsetting()) {
			errFlag=false;
		}
	
		if(errFlag1==true) {
			if(errFlag==true){
				serviceRequestMasterDTO = new ServiceRequestMasterDTO();
				serviceRequestMasterDTO.setServiceRequestDate(new Date());
				serviceRequestMasterDTO.setServiceRequestType(Constants.SWITCHING_SERVICE_REQUEST_TYPE);
				serviceRequestMasterDTO.setPolicyNo(policyNo);
				String userName = SecurityLibrary.getFullLoggedInUser().getVuserName();
				serviceRequestMasterDTO.setUserId(userName);
				serviceRequestMasterDTO.setRequestStatus(Constants.INACTIVE);
				serviceRequestMasterDTO.setRecentUpdateDate(new Date());
				serviceRequestMasterDTO.setSeqno(seqno);
				
				serviceRequestMasterDTO = transAction.insertServiceRequest(serviceRequestMasterDTO);
				
				if (BSLUtils.isNotNull(selectedOperation)) {
					if (selectedOperation.equalsIgnoreCase("R")) {
						setEnableRedirection(true);
					}
					if (selectedOperation.equalsIgnoreCase("S")) {
						setEnableSwitching(true);
					}
					if (selectedOperation.equalsIgnoreCase("RS")) {
						setEnableRedirctSwitch(true);
					}
				}
							
				// selling Fund					
				if (BSLUtils.isNotNull(sellingFundsList) && sellingFundsList.size() > 0) {
					for (RedirectionDTO redirectionDTO : sellingFundsList) {
						if (BSLUtils.isNotNull(selectedOperation)) {
							if (selectedOperation.equalsIgnoreCase("RS")) {
								redirectionDTO.setMode("RS");
							} else {
								redirectionDTO.setMode("S");
							}
						}else {
							redirectionDTO.setMode("S");
						}
						redirectionDTO.setServiceRequestNo(serviceRequestMasterDTO);
						redirectionDTO.setInvestmenetsource(investementsourcecode);
						if (redirectionDTO.getValue().compareTo(BigDecimal.ZERO)  > 0) {
							continueLaterList.add(redirectionDTO);
						}	
					}	
				}	
				 
				// buying fund
				
				if (BSLUtils.isNotNull(buyingFundsList) && buyingFundsList.size() > 0) {
					for (RedirectionDTO redirectionDTO : buyingFundsList) {
						if (BSLUtils.isNotNull(selectedOperation)) {
							if (selectedOperation.equalsIgnoreCase("RS")) {
								redirectionDTO.setMode("RB");
							} else {
								redirectionDTO.setMode("B");
							}
						}else {
							redirectionDTO.setMode("B");
						}
						redirectionDTO.setServiceRequestNo(serviceRequestMasterDTO);
						continueLaterList.add(redirectionDTO);
					}
				}			
				boolean status = redirectImpl.updateRedirection(continueLaterList);
				if(status=true) {
					
					getSession().setAttribute("TRANSACTION", "SWITCHING");
					PrimeFaces.current().executeScript("PF('dlg3').show()");
				}			
			}			
		}else{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message", "Please re-check the process or contact Salama Support Team.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}		
	}
	
	public void btnContinueLaterfatca() {
		boolean errFlag=true;
		if(!validateSellingFundList()){
			errFlag = false;
		}
		if(!validateBuyingFundList()){
			errFlag = false;
		}
		
		processing = null;
		processing = "NO";
	
		if(!investsetting()) {
			errFlag=false;
		}
		
		if(errFlag1==true) {
			if(errFlag==true){
				serviceRequestMasterDTO = new ServiceRequestMasterDTO();
				serviceRequestMasterDTO.setServiceRequestDate(new Date());
				serviceRequestMasterDTO.setServiceRequestType(Constants.SWITCHING_SERVICE_REQUEST_TYPE);
				serviceRequestMasterDTO.setPolicyNo(policyNo);
				String userName = SecurityLibrary.getFullLoggedInUser().getVuserName();
				serviceRequestMasterDTO.setUserId(userName);
				serviceRequestMasterDTO.setRequestStatus(Constants.INACTIVE);
				serviceRequestMasterDTO.setRecentUpdateDate(new Date());
				serviceRequestMasterDTO.setSeqno(seqno);
				
				serviceRequestMasterDTO = transAction.insertServiceRequest(serviceRequestMasterDTO);
				
				if (BSLUtils.isNotNull(selectedOperation)) {
					if (selectedOperation.equalsIgnoreCase("R")) {
						setEnableRedirection(true);
					}
					if (selectedOperation.equalsIgnoreCase("S")) {
						setEnableSwitching(true);
					}
					if (selectedOperation.equalsIgnoreCase("RS")) {
						setEnableRedirctSwitch(true);
					}
				}
							
				// selling Fund					
				if (BSLUtils.isNotNull(sellingFundsList) && sellingFundsList.size() > 0) {
					for (RedirectionDTO redirectionDTO : sellingFundsList) {
						if (BSLUtils.isNotNull(selectedOperation)) {
							if (selectedOperation.equalsIgnoreCase("RS")) {
								redirectionDTO.setMode("RS");
							} else {
								redirectionDTO.setMode("S");
							}
						}else {
							redirectionDTO.setMode("S");
						}
						redirectionDTO.setServiceRequestNo(serviceRequestMasterDTO);
						redirectionDTO.setInvestmenetsource(investementsourcecode);
						if (redirectionDTO.getValue().compareTo(BigDecimal.ZERO)  > 0) {
							continueLaterList.add(redirectionDTO);
						}	
					}	
				}	
				 
				// buying fund
				
				if (BSLUtils.isNotNull(buyingFundsList) && buyingFundsList.size() > 0) {
					for (RedirectionDTO redirectionDTO : buyingFundsList) {
						if (BSLUtils.isNotNull(selectedOperation)) {
							if (selectedOperation.equalsIgnoreCase("RS")) {
								redirectionDTO.setMode("RB");
							} else {
								redirectionDTO.setMode("B");
							}
						}else {
							redirectionDTO.setMode("B");
						}
						redirectionDTO.setServiceRequestNo(serviceRequestMasterDTO);
						continueLaterList.add(redirectionDTO);
					}
				}			
				boolean status = redirectImpl.updateRedirection(continueLaterList);
				if(status=true) {
					processing = "YES";
					getSession().setAttribute("TRANSACTION", "SWITCHING");
				}			
			}			
		}else{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message", "Please re-check the process or contact Salama Support Team.");
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

		for (RedirectionDTO each : sellingFundsList) {
			each.setValue(BigDecimal.ZERO);
		}
		for (RedirectionDTO each : buyingFundsList) {
			each.setFundName(null);
		    each.setFundCode("");
			each.setValue(BigDecimal.ZERO);
			setNewTotal1("");
		}
	}

	public boolean validateSellingFundList(){
		boolean errFlag = true;
		boolean zeroFlag=true,abovehundFlag=true;
		boolean sellingValue= false;
		if (sellingFundsList.size() > 0) {
			for (int i = 0; i < sellingFundsList.size(); i++) {
				
				if (sellingFundsList.get(i).getValue().compareTo(BigDecimal.ZERO)  > 0){
					sellingValue = true;
				}
			    if(sellingFundsList.get(i).getValue().intValue() > 100){
					abovehundFlag = false;errFlag=false;
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
			if(sellingValue == false){
				errFlag=false;
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Please select fund for selling.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		}
		return errFlag;
	}
	
	
	public boolean validateBuyingFundList(){
		boolean errFlag=true;
		if(BSLUtils.isNotNull(buyingFundsList)){
			if(buyingFundsList.size() == 0){
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Please select the Buying Fund.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				errFlag = false;
			}
			if(BSLUtils.isNull(newTotal1)){
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Buying fund  should not be zero/null value.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				errFlag = false;
			}
			for (int i = 0; i < buyingFundsList.size(); i++) {							
				if (buyingFundsList.get(i).getValue().equals(BigDecimal.ZERO)) {					
					errFlag = false;
				}
				if(i==(buyingFundsList.size()-1) && !errFlag){
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
							"Buying fund  should not be zero/null value.");
					PrimeFaces.current().dialog().showMessageDynamic(message);
				}				
			}
			if (BSLUtils.isNotNull(newTotal1)) {
				BigDecimal iT = BSLUtils.toBigDecimal(0);
				for (RedirectionDTO obj : buyingFundsList) {
					if (BSLUtils.isNotNull(obj) && BSLUtils.isNotNull(obj.getValue()))
						iT = iT.add(obj.getValue());
				}
				Double newTotal=iT.doubleValue();
				if(newTotal.equals(Double.parseDouble("0.0"))){
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
							"Buying fund  should not be zero/null value.");
					PrimeFaces.current().dialog().showMessageDynamic(message);					
					errFlag = false;
				}else if(!newTotal.equals(Double.parseDouble("100.0"))){
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
							"Buying fund  should equal to 100%.");
					PrimeFaces.current().dialog().showMessageDynamic(message);
					errFlag = false;
				}
				if(buyingFundsList.size() > 10){					
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
		//Added by viswa karthick on 08/07/2017 for clearing the session
		fetchSellingFundListfromService();
		fetchFundDetailFromService();
		newTotal1 = "0.0";
		buyingFundsList = new ArrayList<RedirectionDTO>();
		//RequestContext requestContext = RequestContext.getCurrentInstance();						
		PrimeFaces.current().executeScript("popclose1()");
		PrimeFaces.current().executeScript("$('#successTab1').modal('show');");
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

	public List<FundDetailsDTO> getStratergyListLoc() {
		return stratergyListLoc;
	}


	public void setStratergyListLoc(List<FundDetailsDTO> stratergyListLoc) {
		this.stratergyListLoc = stratergyListLoc;
	}

	/*public static HttpSession getSession() {
		return session;
	}

	public static void setSession(HttpSession session) {
		SwitchingAction.session = session;
	}*/

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

	public String getNewTotal1() {
		return newTotal1;
	}

	public void setNewTotal1(String newTotal1) {
		this.newTotal1 = newTotal1;
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

	public ApplicationMailer getAppMailer() {
		return appMailer;
	}


	public String getErrOtp() {
		return errOtp;
	}


	public void setTot(Double tot) {
		this.tot = tot;
	}


	public void setAppMailer(ApplicationMailer appMailer) {
		this.appMailer = appMailer;
	}


	public void setErrOtp(String errOtp) {
		this.errOtp = errOtp;
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

	public boolean isCheckBox() {
		return checkBox;
	}

	public void setCheckBox(boolean checkBox) {
		this.checkBox = checkBox;
	}

	public UserDTO getUserDetails1() {
		return userDetails1;
	}

	public void setUserDetails1(UserDTO userDetails1) {
		this.userDetails1 = userDetails1;
	}

	public RedirectionBLImpl getRedirectionBL() {
		return redirectionBL;
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

	public boolean isSwitchenablePrint() {
		return switchenablePrint;
	}

	public void setSwitchenablePrint(boolean switchenablePrint) {
		this.switchenablePrint = switchenablePrint;
	}

	public Double getTot1() {
		return tot1;
	}

	public void setTot1(Double tot1) {
		this.tot1 = tot1;
	}

	public List<RedirectionDTO> getContinueLaterList() {
		return continueLaterList;
	}

	public void setContinueLaterList(List<RedirectionDTO> continueLaterList) {
		this.continueLaterList = continueLaterList;
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

	
	public boolean isFatcaFlagpopUpSwitch() {
		return fatcaFlagpopUpSwitch;
	}

	public void setFatcaFlagpopUpSwitch(boolean fatcaFlagpopUpSwitch) {
		this.fatcaFlagpopUpSwitch = fatcaFlagpopUpSwitch;
	}

	public String getTitleMsg() {
		return titleMsg;
	}

	public void setTitleMsg(String titleMsg) {
		this.titleMsg = titleMsg;
	}


	public BigDecimal getSeqno() {
		return seqno;
	}


	public void setSeqno(BigDecimal seqno) {
		this.seqno = seqno;
	}

	public int getDocFilesize() {
		return docFilesize;
	}

	public void setDocFilesize(int docFilesize) {
		this.docFilesize = docFilesize;
	}

	public String getInvestementsourcecode() {
		return investementsourcecode;
	}

	public void setInvestementsourcecode(String investementsourcecode) {
		this.investementsourcecode = investementsourcecode;
	}

	public String getDocFilepath() {
		return docFilepath;
	}

	public void setDocFilepath(String docFilepath) {
		this.docFilepath = docFilepath;
	}

	public boolean isDocupath() {
		return docupath;
	}

	public void setDocupath(boolean docupath) {
		this.docupath = docupath;
	}

	public String getFatcaFlag() {
		return fatcaFlag;
	}

	public void setFatcaFlag(String fatcaFlag) {
		this.fatcaFlag = fatcaFlag;
	}

	public boolean isFatcaFlagpopUp() {
		return fatcaFlagpopUp;
	}

	public void setFatcaFlagpopUp(boolean fatcaFlagpopUp) {
		this.fatcaFlagpopUp = fatcaFlagpopUp;
	}

	
	
}
