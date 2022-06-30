package com.aetins.customerportal.web.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.aetins.customerportal.web.dao.ContributionHolidayDAO;
import com.aetins.customerportal.web.dao.ReinStatementDAO;
import com.aetins.customerportal.web.dao.impl.ContributionHolidayDAOImpl;
import com.aetins.customerportal.web.dto.CpCustomerDetailDTO;
import com.aetins.customerportal.web.dto.CpTermAndConditionDTO;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.dto.PasswordRulesDTO;
import com.aetins.customerportal.web.dto.ReinStatementDTO;
import com.aetins.customerportal.web.dto.ReinStatementsDTO;
import com.aetins.customerportal.web.dto.SearchPolicyDTO;
import com.aetins.customerportal.web.dto.ServiceRequestMasterDTO;
import com.aetins.customerportal.web.dto.UserDTO;
import com.aetins.customerportal.web.entity.CPServiceRequestDocuments;
import com.aetins.customerportal.web.entity.CpListBoxAnswers;
import com.aetins.customerportal.web.entity.CpQuestionnaire;
import com.aetins.customerportal.web.entity.CpRequestMaster;
import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.service.CPServiceRequestDocumentsBL;
import com.aetins.customerportal.web.service.RedirectionBL;
import com.aetins.customerportal.web.service.impl.AdminBLImpl;
import com.aetins.customerportal.web.service.impl.CustomerPortalServicesImpl;
import com.aetins.customerportal.web.service.impl.RedirectionBLImpl;
import com.aetins.customerportal.web.service.impl.ReinStatementBLImpl;
import com.aetins.customerportal.web.utils.BSLUtils;


@Controller
@Scope("session")
public class ReinStatementAction extends BaseAction {
	
	
	private static final Logger logger = Logger.getLogger(ReinStatementAction.class);
	
	@Autowired
	ReinStatementDAO reinStatementDao;
	
	@Autowired
	LoginSession loginSession;
	
	@Autowired
	RedirectionBL redirectImpl;
	
	@Autowired
	AdminBLImpl adminImpl;
	
	@Autowired
	ContributionHolidayDAO contributionHolidayDAO;
	
	private List<ReinStatementDTO> reinStatementDTOList = new ArrayList<ReinStatementDTO>();
	private List<ReinStatementsDTO> reinStatementsDTOList = new ArrayList<ReinStatementsDTO>();
	private List<CpUserInfo> cpUserInfo = new ArrayList<CpUserInfo>();
	private CustomerPortalServicesImpl customerPortalServicesImpl = new CustomerPortalServicesImpl();
	private ServiceRequestMasterDTO serviceRequestMasterDTO = new ServiceRequestMasterDTO();
	private List<ServiceRequestMasterDTO> serviceRequestMasterDTOList;
	private String policyNo;
	private String premiumStatus;
	private String planName;
	public String searchPolicyNo;
	private boolean checkBox;
//	ReinStatementBLImpl reinStatementBLImpl = new ReinStatementBLImpl();
	private UserDTO userDetails = new UserDTO();
	private PasswordRulesDTO passwordRulesDTO = new PasswordRulesDTO();
	private List<CpUserInfoDTO> cpUserList = new ArrayList<CpUserInfoDTO>();
	private List<CpTermAndConditionDTO> termsConditionYesYes;
	private List<CpTermAndConditionDTO> termsConditionYesNo;
	private int serviceRequestNo;
	//RedirectionBLImpl redirectImpl = new RedirectionBLImpl();
	private boolean reinClicked;
	private boolean status1;
	private SearchPolicyDTO searchPolicyDTO = new SearchPolicyDTO();
	private SearchPolicyDTO searchPolicyList;
	ReinStatementsDTO reinStatementsDTO = new ReinStatementsDTO();
	List<ReinStatementsDTO> reinbenifitDTOList = new ArrayList<ReinStatementsDTO>();
	private String totalAssetsFirstCover;
	private BigDecimal totalAssetsSecond;
	private BigDecimal totalAssetsPlanHold;
	private BigDecimal totalLiabFirstCover;
	private BigDecimal totalLiabPlanHold;
	private BigDecimal totalLiabSecond;
	private boolean enablePanel;
	private String titleMsg;
	CpCustomerDetailDTO cpCustomerDetailDTO = new CpCustomerDetailDTO();
//	TransactionServiceAction transAction = new TransactionServiceAction();
	boolean status = true;
	private String bodyMes;
	private String fatcaFlag;
	Date serDate;
	private boolean fatcaFlagpopUp;
	public String gender;
	public String secondMember;
	private List<CpQuestionnaire> questionnaireList = new ArrayList<>();
	private List<CpListBoxAnswers> listBoxAnswers = new ArrayList<>();
    private List<CpListBoxAnswers> selectedRecord = new ArrayList<>();

    private BigDecimal seqno;
    
    @Autowired
    TransactionServiceAction transAction;
    
    @Autowired
    ReinStatementBLImpl reinStatementBLImpl;
    
	@PostConstruct
	public void init() {
		
//		try {
//			FacesContext context = FacesContext.getCurrentInstance();
//			HomeDetailsAction hAction = context.getApplication().evaluateExpressionGet(context, "#{homeDetailsAction}",	HomeDetailsAction.class);
//			policyNo = hAction.getSearchPolicyList().getPlanNumber();
//			premiumStatus = hAction.getSearchPolicyList().getPremiumPayStatus();
//			planName = hAction.getSearchPolicyList().getProductName();
//			gender = (String) getSession().getAttribute("GENDER");
//			secondMember = hAction.getSearchPolicyList().getSecondMember();
//			if(hAction.getPolicyDetailsList().size()>0) {
//				for(int i=0;i<hAction.getPolicyDetailsList().size();i++) {
//					if(policyNo.equalsIgnoreCase(hAction.getPolicyDetailsList().get(i).getPolicyNo())){
//						seqno=hAction.getPolicyDetailsList().get(i).getSeqno();
//					}
//				}
//			}
//			
//			fetchReinstatementList();
//			
//			termsConditionYesYes = redirectImpl.listTermAndCondition(Constants.REINSTATEMENT_SERVICE_REQUEST_TYPE, Constants.MANDATORY, Constants.REQUIRED);
//			termsConditionYesNo = redirectImpl.listTermAndCondition1(Constants.REINSTATEMENT_SERVICE_REQUEST_TYPE, Constants.MANDATORYNO,Constants.REQUIREDYES);
//			searchPolicyList = (SearchPolicyDTO) getSession().getAttribute("POLICYDATA");
//			questionnaireList = hAction.getQuestionnaire(Constants.REINSTATEMENT_SERVICE_REQUEST_TYPE);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//
//		}
	}

	public String reinstatmentfetch() {
		reinStatementDTOList=null;
		reinStatementsDTO=null;
		reinbenifitDTOList=null;
		//clearReinstatementValues();
		reinStatementDTOList = new ArrayList<ReinStatementDTO>();
		reinStatementsDTO = new ReinStatementsDTO();
		reinbenifitDTOList = new ArrayList<ReinStatementsDTO>();
		enablePanel=false;
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			HomeDetailsAction hAction = context.getApplication().evaluateExpressionGet(context, "#{homeDetailsAction}",	HomeDetailsAction.class);
			policyNo = hAction.getSearchPolicyList().getPlanNumber();
			premiumStatus = hAction.getSearchPolicyList().getPremiumPayStatus();
			planName = hAction.getSearchPolicyList().getProductName();
			gender = (String) getSession().getAttribute("GENDER");
			secondMember = hAction.getSearchPolicyList().getSecondMember();
			if(hAction.getPolicyDetailsList().size()>0) {
				for(int i=0;i<hAction.getPolicyDetailsList().size();i++) {
					if(policyNo.equalsIgnoreCase(hAction.getPolicyDetailsList().get(i).getPolicyNo())){
						seqno=hAction.getPolicyDetailsList().get(i).getSeqno();
					}
				}
			}
			fetchReinstatementList();
			termsConditionYesYes = redirectImpl.listTermAndCondition(Constants.REINSTATEMENT_SERVICE_REQUEST_TYPE, Constants.MANDATORY, Constants.REQUIRED);
			termsConditionYesNo = redirectImpl.listTermAndCondition1(Constants.REINSTATEMENT_SERVICE_REQUEST_TYPE, Constants.MANDATORYNO,Constants.REQUIREDYES);
			searchPolicyList = (SearchPolicyDTO) getSession().getAttribute("POLICYDATA");
			//questionnaireList = hAction.getQuestionnaire(Constants.REINSTATEMENT_SERVICE_REQUEST_TYPE);

		} catch (Exception e) {
			e.printStackTrace();

		}
		getSession().setAttribute("UPLOADFILENAME", "EMPTY");
        return "/user/reinstatement?faces-redirect=true";
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
	public void onPageLoad() {

		logger.info("::: Entering Inside onPageLoad Method ::::::");

		logger.info("Entering into ReinstatementValidation =====================");
		int diffInDays = 0;
		List<CpRequestMaster> requestDate = contributionHolidayDAO.getTransactionCount(SecurityLibrary.getFullLoggedInUser().getVuserName(), "Y");

		logger.info("Fatca Master list size in Reinsataement ================" + requestDate.size());
		if (requestDate.size() > 0) {
			serDate = requestDate.get(0).getServiceRequestDate();
			diffInDays = (int) ((new Date().getTime() - serDate.getTime()) / (1000 * 60 * 60 * 24));
			System.out.println(diffInDays);
		}

		logger.info("Difference in days for fatca validation in Reinstatement ==============" + diffInDays);
		
		
		/*if (diffInDays > 90 || requestDate.size() == 0) {
			fatcaFlag = "Y";
			fatcaFlagpopUp = true;
			getSession().setAttribute("FATCAFLAG", fatcaFlag);
		} else {
			fatcaFlag = "N";
			fatcaFlagpopUp = false;
			getSession().setAttribute("FATCAFLAG", fatcaFlag);
		}*/
		
		
		if (diffInDays > 90 || requestDate.size() == 0) {
			fatcaFlag = "N";
			fatcaFlagpopUp = false;
			getSession().setAttribute("FATCAFLAG", fatcaFlag);
		} else {
			fatcaFlag = "Y";
			fatcaFlagpopUp = true;
			getSession().setAttribute("FATCAFLAG", fatcaFlag);
		}
		
		
		logger.info("Fatca flag Status in Reinstatement =================" + fatcaFlag);
		logger.info("FatcaFlagpopUp flag Status in Reinstatement ===================" + fatcaFlagpopUp);

	}

	public void onClickValidation(String policyNoSelected) {

		Long count = reinStatementDao.getRowCount(Constants.REINSTATEMENT_SERVICE_REQUEST_TYPE,
				policyNoSelected);
		logger.info("::: Size of Count is in Reinstatement ::::::" + count);
		System.out.println("count==" + count);
		long tmp = 0;

		int output = count.compareTo(tmp);
		if (output > 0) {
			clearReinstatementValues();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
					"Your old transaction request is in process.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
			PrimeFaces.current().executeScript("PF('dlg3').hide()");
		}

		//String result = customerPortalServicesImpl.getAllowedTransaction(policyNoSelected, "PR0015");
		String result = "ALLOWED";
		logger.info("Transaction fn called for Reinstatement ==============" + result);

		if (BSLUtils.isNotNull(result)) {
			if (result.equalsIgnoreCase("ALLOWED")) {

			} else {
				clearReinstatementValues();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Request is Not Eligible as the Plan status is inactive.Please contact Salama.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		}

	}

	/*
	 * Method is used for validating medical question detail. Added by : Vinod
	 * On : 12/01/2018
	 */
	public boolean validateFinancialDetails() {
		logger.info("::: Entering Inside validateFinancialDetails Method ::::::");
		status = true;
		String gender = (String) getSession().getAttribute("GENDER");
		logger.info("::: Gender details is ::::::" + gender);
		if (BSLUtils.isNotNull(gender)) {
			if (gender.equalsIgnoreCase("F")) {
				if (BSLUtils.isNull(reinStatementsDTO.getQuesElevenFirst())) {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
							"Please enter the Mandatory Field.");
					PrimeFaces.current().dialog().showMessageDynamic(message);
					status = false;
				}

				if (BSLUtils.isNull(reinStatementsDTO.getQuesElevenAFirst())) {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
							"Please enter the Mandatory Field.");
					PrimeFaces.current().dialog().showMessageDynamic(message);
					status = false;
				}
			}
		}
		logger.info("::: Return value for validateFinancialDetails is ::::::" + status);
		return status;
	}

	public void fetchReinstatementList() {
		BigDecimal tempCustRefNo = new BigDecimal(SecurityLibrary.getFullLoggedInUser().getNcustRefNo());
		reinStatementDTOList = customerPortalServicesImpl.getMyReinstatement(tempCustRefNo, "EN");
		logger.info("::: Size of Reinstatement List is ::::::" + reinStatementDTOList.size());
		if (reinStatementDTOList == null || reinStatementDTOList.isEmpty() || reinStatementDTOList.size() < 1) {
			return;
		}

	}
	
	private String planno;
	private String planname;

	public void showPlanDetails(int currentIndex) {
		planno=null;
		planname=null;
		logger.info("::: Entering Inside showPlanDetails Method ::::::" + currentIndex);
		System.out.println("currentIndex:" + currentIndex);
		setEnablePanel(false);
		for (int i = 0; i < reinStatementDTOList.size(); i++) {
			if (i != currentIndex) {
				reinStatementDTOList.get(i).setStatus(false);
			}
		}

		for (ReinStatementDTO reinClicked : reinStatementDTOList) {

			if (reinClicked.isStatus() == true) {

				onClickValidation(reinClicked.getPolicyNo());

				reinbenifitDTOList = customerPortalServicesImpl.getBenifitReinstate(reinClicked.getPolicyNo(), "EN");
				reinStatementsDTO.setSelectedPlanNo(reinClicked.getPolicyNo());
				reinStatementsDTO.setSelectedPlanName(reinClicked.getProductName());
				reinStatementsDTO.setRegulatContrib(reinClicked.getRegulatContrib());
				reinStatementsDTO.setContribFreq(reinClicked.getContribFreq());
				reinStatementsDTO.setDueFirstContrib(reinClicked.getIssueDate());
				reinStatementsDTO.setDueFinalContrib(reinClicked.getPremDue());
				reinStatementsDTO.setPlanTerm(reinClicked.getPlanTerm());
				reinStatementsDTO.setContribTerm(reinClicked.getContribTerm());
				reinStatementsDTO.setFirstCoverDob(reinbenifitDTOList.get(0).getFirstCoverDob());
				reinStatementsDTO.setSecondCoverDob(reinbenifitDTOList.get(0).getSecondCoverDob());
				reinStatementsDTO.setFirstCover(reinbenifitDTOList.get(0).getFirstCover());
				reinStatementsDTO.setSecondCover(reinbenifitDTOList.get(0).getSecondCover());
				reinStatementsDTO.setBenifitType(reinbenifitDTOList.get(0).getBenifitType());
				reinStatementsDTO.setFirstCoverbenifitAmt(reinbenifitDTOList.get(0).getFirstCoverbenifitAmt());
				reinStatementsDTO.setFirstCoverbenifitTerm(reinbenifitDTOList.get(0).getFirstCoverbenifitTerm());
				reinStatementsDTO.setSecondCoverbenifitAmt(reinbenifitDTOList.get(0).getSecondCoverbenifitAmt());
				reinStatementsDTO.setSecondCoverbenifitTerm(reinbenifitDTOList.get(0).getSecondCoverbenifitTerm());
				reinStatementsDTO.setPlanCurr(reinClicked.getPlanCurr());
				setEnablePanel(true);
			}
		}
		logger.info("::: Exit from showPlanDetails Method ::::::" + currentIndex);
	}

	public void assetsTotal() {

		BigDecimal assetCashFirst = BigDecimal.valueOf(0.00);
		BigDecimal assetSharesFirst = BigDecimal.valueOf(0.00);
		BigDecimal assetOthersFirst = BigDecimal.valueOf(0.00);
		BigDecimal assetRealEstateFirst = BigDecimal.valueOf(0.00);
		BigDecimal assetCashSecond = BigDecimal.valueOf(0.00);
		BigDecimal assetSharesSecond = BigDecimal.valueOf(0.00);
		BigDecimal assetOthersSecond = BigDecimal.valueOf(0.00);
		BigDecimal assetRealEstateSecond = BigDecimal.valueOf(0.00);
		BigDecimal assetCashPlanHold = BigDecimal.valueOf(0.00);
		BigDecimal assetSharesPlanHold = BigDecimal.valueOf(0.00);
		BigDecimal assetOthersPlanHold = BigDecimal.valueOf(0.00);
		BigDecimal assetRealEstatePlanHold = BigDecimal.valueOf(0.00);

		if (BSLUtils.isNotNull(reinStatementsDTO.getFirstCoverCash())) {
			assetCashFirst = reinStatementsDTO.getFirstCoverCash();
		}
		if (BSLUtils.isNotNull(reinStatementsDTO.getFirstCoverShares())) {
			assetSharesFirst = reinStatementsDTO.getFirstCoverShares();
		}
		if (BSLUtils.isNotNull(reinStatementsDTO.getFirstCoverRealEstate())) {
			assetRealEstateFirst = reinStatementsDTO.getFirstCoverRealEstate();
		}
		if (BSLUtils.isNotNull(reinStatementsDTO.getFirstCoverAssetsOthers())) {
			assetOthersFirst = reinStatementsDTO.getFirstCoverAssetsOthers();
		}
		BigDecimal total = assetCashFirst.add(assetSharesFirst).add(assetOthersFirst).add(assetRealEstateFirst);
		totalAssetsFirstCover = total.toPlainString();

		if (BSLUtils.isNotNull(reinStatementsDTO.getSecondCoverCash())) {
			assetCashSecond = reinStatementsDTO.getSecondCoverCash();
		}
		if (BSLUtils.isNotNull(reinStatementsDTO.getSecondCoverShares())) {
			assetSharesSecond = reinStatementsDTO.getSecondCoverShares();
		}
		if (BSLUtils.isNotNull(reinStatementsDTO.getSecondCoverRealEstate())) {
			assetRealEstateSecond = reinStatementsDTO.getSecondCoverRealEstate();
		}
		if (BSLUtils.isNotNull(reinStatementsDTO.getSecondCoverAssetsOthers())) {
			assetOthersSecond = reinStatementsDTO.getSecondCoverAssetsOthers();
		}
		totalAssetsSecond = assetCashSecond.add(assetSharesSecond).add(assetRealEstateSecond).add(assetOthersSecond);

		if (BSLUtils.isNotNull(reinStatementsDTO.getPlanHoldCash())) {
			assetCashPlanHold = reinStatementsDTO.getPlanHoldCash();
		}
		if (BSLUtils.isNotNull(reinStatementsDTO.getPlanHoldShares())) {
			assetSharesPlanHold = reinStatementsDTO.getPlanHoldShares();
		}
		if (BSLUtils.isNotNull(reinStatementsDTO.getPlanHoldRealEstate())) {
			assetRealEstatePlanHold = reinStatementsDTO.getPlanHoldRealEstate();
		}
		if (BSLUtils.isNotNull(reinStatementsDTO.getPlanHoldAssetsOthers())) {
			assetOthersPlanHold = reinStatementsDTO.getPlanHoldAssetsOthers();
		}
		totalAssetsPlanHold = assetCashPlanHold.add(assetSharesPlanHold).add(assetRealEstatePlanHold)
				.add(assetOthersPlanHold);

	}

	public void liabilitiesTotal() {

		BigDecimal liabLoanFirst = BigDecimal.valueOf(0.00);
		BigDecimal liabAccntFirst = BigDecimal.valueOf(0.00);
		BigDecimal liabMortgageFirst = BigDecimal.valueOf(0.00);
		BigDecimal liabOtherFirst = BigDecimal.valueOf(0.00);
		BigDecimal liabLoanSecond = BigDecimal.valueOf(0.00);
		BigDecimal liabAccntSecond = BigDecimal.valueOf(0.00);
		BigDecimal liabMortgageSecond = BigDecimal.valueOf(0.00);
		BigDecimal liabOtherSecond = BigDecimal.valueOf(0.00);
		BigDecimal liabLoanPlanHold = BigDecimal.valueOf(0.00);
		BigDecimal liabAccntPlanHold = BigDecimal.valueOf(0.00);
		BigDecimal liabMortgagePlanHold = BigDecimal.valueOf(0.00);
		BigDecimal liabOtherPlanHold = BigDecimal.valueOf(0.00);

		if (BSLUtils.isNotNull(reinStatementsDTO.getFirstCoverLoan())) {
			liabLoanFirst = reinStatementsDTO.getFirstCoverLoan();
		}
		if (BSLUtils.isNotNull(reinStatementsDTO.getFirstCoverPayable())) {
			liabAccntFirst = reinStatementsDTO.getFirstCoverPayable();
		}
		if (BSLUtils.isNotNull(reinStatementsDTO.getFirstCoverMortgage())) {
			liabMortgageFirst = reinStatementsDTO.getFirstCoverMortgage();
		}
		if (BSLUtils.isNotNull(reinStatementsDTO.getFirstCoverOther())) {
			liabOtherFirst = reinStatementsDTO.getFirstCoverOther();
		}
		totalLiabFirstCover = liabLoanFirst.add(liabAccntFirst).add(liabMortgageFirst).add(liabOtherFirst);

		if (BSLUtils.isNotNull(reinStatementsDTO.getSecondCoverLoan())) {
			liabLoanSecond = reinStatementsDTO.getSecondCoverLoan();
		}
		if (BSLUtils.isNotNull(reinStatementsDTO.getSecondCoverPayable())) {
			liabAccntSecond = reinStatementsDTO.getSecondCoverPayable();
		}
		if (BSLUtils.isNotNull(reinStatementsDTO.getSecondCoverMortgage())) {
			liabMortgageSecond = reinStatementsDTO.getSecondCoverMortgage();
		}
		if (BSLUtils.isNotNull(reinStatementsDTO.getSecondCoverOther())) {
			liabOtherSecond = reinStatementsDTO.getSecondCoverOther();
		}
		totalLiabSecond = liabLoanSecond.add(liabAccntSecond).add(liabMortgageSecond).add(liabOtherSecond);

		if (BSLUtils.isNotNull(reinStatementsDTO.getPlanHoldLoan())) {
			liabLoanPlanHold = reinStatementsDTO.getPlanHoldLoan();
		}
		if (BSLUtils.isNotNull(reinStatementsDTO.getPlanHoldPayable())) {
			liabAccntPlanHold = reinStatementsDTO.getPlanHoldPayable();
		}
		if (BSLUtils.isNotNull(reinStatementsDTO.getPlanHoldMortgage())) {
			liabMortgagePlanHold = reinStatementsDTO.getPlanHoldMortgage();
		}
		if (BSLUtils.isNotNull(reinStatementsDTO.getPlanHoldOther())) {
			liabOtherPlanHold = reinStatementsDTO.getPlanHoldOther();
		}
		totalLiabPlanHold = liabLoanPlanHold.add(liabAccntPlanHold).add(liabMortgagePlanHold).add(liabOtherPlanHold);

	}

	public void btnContinueLater() {
		boolean stta=false;
		logger.info("::: Entering Inside btnContinueLater() Method ::::::");
		boolean validate = false;
		for (ReinStatementDTO reinClicked : reinStatementDTOList) {
			if (reinClicked.isStatus() == true) {
				validate = true;
			}
		}
		if (validate) {
			Date currentDate = new Date();
			serviceRequestMasterDTO.setServiceRequestDate(currentDate);
			serviceRequestMasterDTO.setServiceRequestType(Constants.REINSTATEMENT_SERVICE_REQUEST_TYPE);
			serviceRequestMasterDTO.setPolicyNo(reinStatementsDTO.getSelectedPlanNo());
			serviceRequestMasterDTO.setUserId(SecurityLibrary.getFullLoggedInUser().getVuserName());
			serviceRequestMasterDTO.setRequestStatus(Constants.INACTIVE);
			serviceRequestMasterDTO.setRecentUpdateDate(currentDate);
			serviceRequestMasterDTO.setSeqno(seqno);
			serviceRequestMasterDTO = transAction.insertServiceRequest(serviceRequestMasterDTO);
			reinStatementsDTOList = new ArrayList<>();

			for (int i = 0; i < reinStatementDTOList.size(); i++) {
				if (reinStatementDTOList.get(i).isStatus()) {
					logger.info("Inside for loop condition  ::::::::::" + reinStatementsDTO);
					// reinStatementsDTO = new ReinStatementsDTO();
					reinStatementsDTO.setNserviceRequestNo(serviceRequestMasterDTO);
					reinStatementsDTO.setPolicyNo(reinStatementDTOList.get(i).getPolicyNo());
					reinStatementsDTO.setReinStatementFlag("Y");
					reinStatementsDTO.setProductName(reinStatementDTOList.get(i).getProductName());
					if (BSLUtils.isNotNull(reinStatementDTOList.get(i).getOutstandingAmount())) {
						reinStatementsDTO
								.setOutstandingAmount(reinStatementDTOList.get(i).getOutstandingAmount().intValue());
					}
					reinStatementsDTO.setIssueDate(reinStatementDTOList.get(i).getIssueDate());
					reinStatementsDTO.setLapseDate(reinStatementDTOList.get(i).getLapseDate());
					reinStatementsDTO.setPrumDate(reinStatementDTOList.get(i).getPremDue());
					// reinStatementsDTOList.add(reinStatementsDTO);
					logger.info("End of for Lopp for loop condition  ::::::::::" + reinStatementsDTO);
				}
			}
			logger.info("Size of Reinstatement is ::::::::::" + reinStatementsDTO);
			if (reinStatementsDTO != null) {
				logger.info("Inside REin Not NULL Reinstatement is ::::::::::" + reinStatementsDTO);
				ReinStatementsDTO asas = reinStatementBLImpl.insertReinStatementDetails(reinStatementsDTO);
				logger.info("Data saved in Reinstatement Table successfully ================");
				if (reinbenifitDTOList.size() > 0) {
					logger.info("Inside Rein statement Benefit is ::::::::::" + reinbenifitDTOList.size());
					boolean stat = reinStatementBLImpl.saveReinstatmentBenefit(reinbenifitDTOList);
					logger.info("Data saved in Reinstatement benefit Table successfully ================");
					//status = adminImpl.saveAnswers(questionnaireList);
					getSession().setAttribute("TRANSACTION", "REINSTATEMENT");
					PrimeFaces.current().executeScript("PF('dlg3').show()");	
				}
				// clearReinstatementValues();
				if (fatcaFlagpopUp == true) {
					//RequestContext requestContext = RequestContext.getCurrentInstance();
					PrimeFaces.current().executeScript("showFATCA()");
				}
			}
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
					"Please Select Reinstatement .");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
	}

	/*
	 * Method is used for clearing session values. Added by : Vishal On :
	 * 12/01/2018
	 */
	public void clearReinstatementValues() {

		setEnablePanel(false);
		for (ReinStatementDTO each : reinStatementDTOList) {
			each.setStatus(false);
		}
		reinStatementsDTO.setFirstCoverHeight("");
		reinStatementsDTO.setSecondCoverHeight("");
		reinStatementsDTO.setFirstCoverWeight("");
		reinStatementsDTO.setSecondCoverWeight("");
		reinStatementsDTO.setQuesOneFirst("");
		reinStatementsDTO.setQuesOneSecond("");
		reinStatementsDTO.setQuesTwoAFirst("");
		reinStatementsDTO.setQuesTwoASecond("");
		reinStatementsDTO.setQuesTwoBFirst("");
		reinStatementsDTO.setQuesTwoBSecond("");
		reinStatementsDTO.setQuesTwoCFirst("");
		reinStatementsDTO.setQuesTwoCSecond("");
		reinStatementsDTO.setQuesTwoDFirst("");
		reinStatementsDTO.setQuesTwoDSecond("");
		reinStatementsDTO.setQuesTwoEFirst("");
		reinStatementsDTO.setQuesTwoESecond("");
		reinStatementsDTO.setQuesTwoFFirst("");
		reinStatementsDTO.setQuesTwoFSecond("");
		reinStatementsDTO.setQuesTwoGFirst("");
		reinStatementsDTO.setQuesTwoGSecond("");
		reinStatementsDTO.setQuesTwoHFirst("");
		reinStatementsDTO.setQuesTwoHSecond("");
		reinStatementsDTO.setQuesThreeFirst("");
		reinStatementsDTO.setQuesThreeSecond("");
		reinStatementsDTO.setQuesFourFirst("");
		reinStatementsDTO.setQuesFourSecond("");
		reinStatementsDTO.setQuesFiveFirst("");
		reinStatementsDTO.setQuesFiveSecond("");
		reinStatementsDTO.setQuesSixFirst("");
		reinStatementsDTO.setQuesSixSecond("");
		reinStatementsDTO.setQuesSevenFirst("");
		reinStatementsDTO.setQuesSevenSecond("");
		reinStatementsDTO.setQuesEightFirst("");
		reinStatementsDTO.setQuesEightFirst("");
		reinStatementsDTO.setQuesEightSecond("");
		reinStatementsDTO.setQuesNineFirst("");
		reinStatementsDTO.setQuesNineSecond("");
		reinStatementsDTO.setQuesTenFirst("");
		reinStatementsDTO.setQuesTenSecond("");
		reinStatementsDTO.setQuesElevenFirst("");
		reinStatementsDTO.setQuesElevenSecond("");
		reinStatementsDTO.setQuesElevenAFirst("");
		reinStatementsDTO.setQuesElevenASecond("");
		reinStatementsDTO.setFirstCoverMedDetails("");
		reinStatementsDTO.setSecondCoverMedDetails("");
		reinStatementsDTO.setFirstCoverBankName("");
		reinStatementsDTO.setFirstCoverIBAN("");
		reinStatementsDTO.setFirstCoverSourceFund("");
		reinStatementsDTO.setSecondCoverBankName("");
		reinStatementsDTO.setSecondCoverIBAN("");
		reinStatementsDTO.setSecondCoverSourceFund("");
		reinStatementsDTO.setPlanHoldBankName("");
		reinStatementsDTO.setPlanHoldIBAN("");
		reinStatementsDTO.setPlanHoldSourceFund("");
		reinStatementsDTO.setFirstCoverIncomeLast(null);
		reinStatementsDTO.setFirstCoverIncomeSecondLast(null);
		reinStatementsDTO.setFirstCoveredIncomeThirdLast(null);
		reinStatementsDTO.setSecondCoverIncomeLast(null);
		reinStatementsDTO.setSecondCoverIncomeSecondLast(null);
		reinStatementsDTO.setSecondCoveredIncomeThirdLast(null);
		reinStatementsDTO.setPlanHoldIncomeLast(null);
		reinStatementsDTO.setPlanHoldIncomeSecondLast(null);
		reinStatementsDTO.setPlanHoldIncomeThirdLast(null);
		reinStatementsDTO.setFirstCoverCash(null);
		reinStatementsDTO.setSecondCoverCash(null);
		reinStatementsDTO.setPlanHoldCash(null);
		reinStatementsDTO.setFirstCoverShares(null);
		reinStatementsDTO.setSecondCoverShares(null);
		reinStatementsDTO.setPlanHoldShares(null);
		reinStatementsDTO.setFirstCoverRealEstate(null);
		reinStatementsDTO.setSecondCoverRealEstate(null);
		reinStatementsDTO.setPlanHoldRealEstate(null);
		reinStatementsDTO.setFirstCoverAssetsOthers(null);
		reinStatementsDTO.setSecondCoverAssetsOthers(null);
		reinStatementsDTO.setPlanHoldAssetsOthers(null);
		setTotalAssetsFirstCover(null);
		setTotalAssetsSecond(null);
		setTotalAssetsPlanHold(null);
		reinStatementsDTO.setFirstCoverLoan(null);
		reinStatementsDTO.setSecondCoverLoan(null);
		reinStatementsDTO.setPlanHoldLoan(null);
		reinStatementsDTO.setFirstCoverPayable(null);
		reinStatementsDTO.setSecondCoverPayable(null);
		reinStatementsDTO.setPlanHoldPayable(null);
		reinStatementsDTO.setFirstCoverMortgage(null);
		reinStatementsDTO.setSecondCoverMortgage(null);
		reinStatementsDTO.setPlanHoldMortgage(null);
		reinStatementsDTO.setFirstCoverOther(null);
		reinStatementsDTO.setSecondCoverOther(null);
		reinStatementsDTO.setPlanHoldOther(null);
		setTotalLiabFirstCover(null);
		setTotalLiabSecond(null);
		setTotalLiabPlanHold(null);
		setEnablePanel(false);
		setTermsCondition(false);

	}

	public List<ReinStatementsDTO> getReinStatementsDTOList() {
		return reinStatementsDTOList;
	}

	public ServiceRequestMasterDTO getServiceRequestMasterDTO() {
		return serviceRequestMasterDTO;
	}

	public String getPremiumStatus() {
		return premiumStatus;
	}

	public String getPlanName() {
		return planName;
	}

	public void setServiceRequestMasterDTO(ServiceRequestMasterDTO serviceRequestMasterDTO) {
		this.serviceRequestMasterDTO = serviceRequestMasterDTO;
	}

	public void setPremiumStatus(String premiumStatus) {
		this.premiumStatus = premiumStatus;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public UserDTO getUserDetails() {
		return userDetails;
	}

	public PasswordRulesDTO getPasswordRulesDTO() {
		return passwordRulesDTO;
	}

	public List<CpUserInfoDTO> getCpUserList() {
		return cpUserList;
	}

	public void setUserDetails(UserDTO userDetails) {
		this.userDetails = userDetails;
	}

	public void setPasswordRulesDTO(PasswordRulesDTO passwordRulesDTO) {
		this.passwordRulesDTO = passwordRulesDTO;
	}

	public void setCpUserList(List<CpUserInfoDTO> cpUserList) {
		this.cpUserList = cpUserList;
	}

	public boolean isCheckBox() {
		return checkBox;
	}

	public void setReinStatementsDTOList(List<ReinStatementsDTO> reinStatementsDTOList) {
		this.reinStatementsDTOList = reinStatementsDTOList;
	}

	public void setCheckBox(boolean checkBox) {
		this.checkBox = checkBox;
	}

	public List<CpUserInfo> getCpUserInfo() {
		return cpUserInfo;
	}

	public void setCpUserInfo(List<CpUserInfo> cpUserInfo) {
		this.cpUserInfo = cpUserInfo;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getSearchPolicyNo() {
		return searchPolicyNo;
	}

	public ReinStatementBLImpl getReinStatementBLImpl() {
		return reinStatementBLImpl;
	}

	public void setReinStatementBLImpl(ReinStatementBLImpl reinStatementBLImpl) {
		this.reinStatementBLImpl = reinStatementBLImpl;
	}

	public List<ReinStatementDTO> getReinStatementDTOList() {
		return reinStatementDTOList;
	}

	public void setReinStatementDTOList(List<ReinStatementDTO> reinStatementDTOList) {
		this.reinStatementDTOList = reinStatementDTOList;
	}

	public CustomerPortalServicesImpl getCustomerPortalServicesImpl() {
		return customerPortalServicesImpl;
	}

	public void setCustomerPortalServicesImpl(CustomerPortalServicesImpl customerPortalServicesImpl) {
		this.customerPortalServicesImpl = customerPortalServicesImpl;
	}

	public List<ServiceRequestMasterDTO> getServiceRequestMasterDTOList() {
		return serviceRequestMasterDTOList;
	}

	public int getServiceRequestNo() {
		return serviceRequestNo;
	}

	public void setServiceRequestMasterDTOList(List<ServiceRequestMasterDTO> serviceRequestMasterDTOList) {
		this.serviceRequestMasterDTOList = serviceRequestMasterDTOList;
	}

	public void setServiceRequestNo(int serviceRequestNo) {
		this.serviceRequestNo = serviceRequestNo;
	}

	public SearchPolicyDTO getSearchPolicyList() {
		return searchPolicyList;
	}

	public void setSearchPolicyList(SearchPolicyDTO searchPolicyList) {
		this.searchPolicyList = searchPolicyList;
	}

	public List<CpTermAndConditionDTO> getTermsConditionYesYes() {
		return termsConditionYesYes;
	}

	public List<CpTermAndConditionDTO> getTermsConditionYesNo() {
		return termsConditionYesNo;
	}

	/*
	 * public RedirectionBLImpl getRedirectImpl() { return redirectImpl; }
	 */

	public void setTermsConditionYesYes(List<CpTermAndConditionDTO> termsConditionYesYes) {
		this.termsConditionYesYes = termsConditionYesYes;
	}

	public void setTermsConditionYesNo(List<CpTermAndConditionDTO> termsConditionYesNo) {
		this.termsConditionYesNo = termsConditionYesNo;
	}

	public void setRedirectImpl(RedirectionBLImpl redirectImpl) {
		this.redirectImpl = redirectImpl;
	}

	private boolean termsCondition;

	public boolean isTermsCondition() {
		return termsCondition;
	}

	public void setTermsCondition(boolean termsCondition) {
		this.termsCondition = termsCondition;
	}

	public boolean isStatus1() {
		return status1;
	}

	public void setStatus1(boolean status1) {
		this.status1 = status1;
	}

	public void setSearchPolicyNo(String searchPolicyNo) {
		this.searchPolicyNo = searchPolicyNo;
	}

	public SearchPolicyDTO getSearchPolicyDTO() {
		return searchPolicyDTO;
	}

	public void setSearchPolicyDTO(SearchPolicyDTO searchPolicyDTO) {
		this.searchPolicyDTO = searchPolicyDTO;
	}

	public ReinStatementsDTO getReinStatementsDTO() {
		return reinStatementsDTO;
	}

	public void setReinStatementsDTO(ReinStatementsDTO reinStatementsDTO) {
		this.reinStatementsDTO = reinStatementsDTO;
	}

	public String getTotalAssetsFirstCover() {
		return totalAssetsFirstCover;
	}

	public void setTotalAssetsFirstCover(String totalAssetsFirstCover) {
		this.totalAssetsFirstCover = totalAssetsFirstCover;
	}

	public BigDecimal getTotalAssetsSecond() {
		return totalAssetsSecond;
	}

	public void setTotalAssetsSecond(BigDecimal totalAssetsSecond) {
		this.totalAssetsSecond = totalAssetsSecond;
	}

	public BigDecimal getTotalAssetsPlanHold() {
		return totalAssetsPlanHold;
	}

	public void setTotalAssetsPlanHold(BigDecimal totalAssetsPlanHold) {
		this.totalAssetsPlanHold = totalAssetsPlanHold;
	}

	public BigDecimal getTotalLiabFirstCover() {
		return totalLiabFirstCover;
	}

	public BigDecimal getTotalLiabPlanHold() {
		return totalLiabPlanHold;
	}

	public BigDecimal getTotalLiabSecond() {
		return totalLiabSecond;
	}

	public void setTotalLiabFirstCover(BigDecimal totalLiabFirstCover) {
		this.totalLiabFirstCover = totalLiabFirstCover;
	}

	public void setTotalLiabPlanHold(BigDecimal totalLiabPlanHold) {
		this.totalLiabPlanHold = totalLiabPlanHold;
	}

	public void setTotalLiabSecond(BigDecimal totalLiabSecond) {
		this.totalLiabSecond = totalLiabSecond;
	}

	public List<ReinStatementsDTO> getReinbenifitDTOList() {
		return reinbenifitDTOList;
	}

	public void setReinbenifitDTOList(List<ReinStatementsDTO> reinbenifitDTOList) {
		this.reinbenifitDTOList = reinbenifitDTOList;
	}

	public boolean isReinClicked() {
		return reinClicked;
	}

	public boolean isEnablePanel() {
		return enablePanel;
	}

	public void setReinClicked(boolean reinClicked) {
		this.reinClicked = reinClicked;
	}

	public void setEnablePanel(boolean enablePanel) {
		this.enablePanel = enablePanel;
	}

	public String getTitleMsg() {
		return titleMsg;
	}

	public void setTitleMsg(String titleMsg) {
		this.titleMsg = titleMsg;
	}

	public String getBodyMes() {
		return bodyMes;
	}

	public void setBodyMes(String bodyMes) {
		this.bodyMes = bodyMes;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSecondMember() {
		return secondMember;
	}

	public void setSecondMember(String secondMember) {
		this.secondMember = secondMember;
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

	public String getPlanno() {
		return planno;
	}

	public void setPlanno(String planno) {
		this.planno = planno;
	}

	public String getPlanname() {
		return planname;
	}

	public void setPlanname(String planname) {
		this.planname = planname;
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
