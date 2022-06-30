package com.aetins.customerportal.web.controllers;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.text.WordUtils;
import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aetins.customerportal.core.faceletstaglib.SecurityLibrary;
import com.aetins.customerportal.web.common.Constants;
import com.aetins.customerportal.web.common.LoginSession;
import com.aetins.customerportal.web.dao.ContributionHolidayDAO;
import com.aetins.customerportal.web.dao.CpNFPersonalDetailsDAO;
import com.aetins.customerportal.web.dto.CpTermAndConditionDTO;
import com.aetins.customerportal.web.dto.CustomerInfoNonFinDTO;
import com.aetins.customerportal.web.dto.CustomerNFAddressContactDTO;
import com.aetins.customerportal.web.dto.CustomerNFAddressDTO;
import com.aetins.customerportal.web.dto.CustomerNFContactDTO;
import com.aetins.customerportal.web.dto.DocumentDTO;
import com.aetins.customerportal.web.dto.ListItem;
import com.aetins.customerportal.web.dto.ServiceRequestMasterDTO;
import com.aetins.customerportal.web.entity.CPServiceRequestDocuments;
import com.aetins.customerportal.web.entity.CpListBoxAnswers;
import com.aetins.customerportal.web.entity.CpNFAddress;
import com.aetins.customerportal.web.entity.CpNFAddressContacts;
import com.aetins.customerportal.web.entity.CpNFPersonalContacts;
import com.aetins.customerportal.web.entity.CpNFPersonalDetails;
import com.aetins.customerportal.web.entity.CpQuestionnaire;
import com.aetins.customerportal.web.entity.CpRequestMaster;
import com.aetins.customerportal.web.service.CPServiceRequestDocumentsBL;
import com.aetins.customerportal.web.service.ContributionHolidaylBL;
import com.aetins.customerportal.web.service.CustomerPortalServices;
import com.aetins.customerportal.web.service.RedirectionBL;
import com.aetins.customerportal.web.service.RegistrationCustomerService;
import com.aetins.customerportal.web.service.UpdateInfoBL;
import com.aetins.customerportal.web.service.impl.AdminBLImpl;
import com.aetins.customerportal.web.service.impl.RedirectionBLImpl;
import com.aetins.customerportal.web.utils.AppSettingURL;
import com.aetins.customerportal.web.utils.BSLUtils;
import com.aetins.customerportal.web.utils.FileUtils;
import com.aetins.customerportal.web.utils.StringUtils;

@Controller(value = "updateInfoAction")
@Scope("session")
public class UpdateInformationAction extends BaseAction {

	private static final Logger logger = Logger.getLogger(UpdateInformationAction.class);

	@SuppressWarnings("rawtypes")
	@Autowired
	private RegistrationCustomerService customerService;

	@SuppressWarnings("rawtypes")
	@Autowired
	private CustomerPortalServices customerPortalServices;

	@Autowired
	private UpdateInfoBL updateInfoBL;

	@Autowired
	RedirectionBL redirectImpl;

	@Autowired
	TransactionServiceAction transAction;

	@Autowired
	ContributionHolidayDAO contibutionHolidayDaoImpl;

	@Autowired
	LoginSession loginSession;

	@Autowired
	CpNFPersonalDetailsDAO cpNFPersonalDetailsDAOImpl;

	@Autowired
	AdminBLImpl adminImpl;

	private String userId;
	private String policyNo;
	private ServiceRequestMasterDTO requestdto;
	private String Checkbox;
	private boolean termsCondition;
	private CustomerInfoNonFinDTO customerInfoNonFinDTOMain;
	private CustomerInfoNonFinDTO customerInfoNonFinTemp;
	private Map<String, String> stateMap;
	private Map<String, String> countryMap;
	private Map<String, String> countryDialCode;
	private Map<String, String> titleMap;
	private Map<String, String> maritalMap;
	private Map<String, String> religionMap;
	private Map<String, String> raceMap;
	private Map<String, String> languageMap;
	private Map<String, String> nationMap;
	private Map<String, String> addrLangMap;
	private boolean enableTitleLabel;
	private boolean enableNameLabel;
	private boolean enableFnameLabel;
	private boolean enableMnameLabel;
	private boolean enableLnameLabel;
	private boolean enableidenNoLabel;
	private boolean enableLangLabel;
	private boolean enableRaceLabel;
	private boolean enableReligionLabel;
	private boolean enableNationLabel;
	private boolean enableMaritalLabel;
	private boolean enableBrCountryLabel;
	private boolean enableBrPlaceLabel;
	private boolean addressStateLabel;
	private boolean enableidenTypeLabel;
	private boolean enableidenExpDate;
	private List<CpNFPersonalDetails> cpNFPersonalDetailList;
	private List<CpNFPersonalContacts> cpNFPersonalContactsList;
	private List<CpNFAddress> cpNFAddressList;
	private List<CpNFAddressContacts> cpNFAddressContactsList;
	private String bodyMsg;
	private String headerMsg;
	private String maskPattern;
	private List<CpTermAndConditionDTO> termsConditionYesYes;
	private List<CpTermAndConditionDTO> termsConditionYesNo;
	private CustomerNFAddressContactDTO custNFAddressContactDTO = new CustomerNFAddressContactDTO();

	private String custName;
	private Map<String, String> identityMap;
	private Date todayDate = new Date();
	private String titleMsg;
	CustomerNFAddressContactDTO custNFAddressContactDTO1 = new CustomerNFAddressContactDTO();
	CustomerNFAddressDTO custNFAddressDTO1 = new CustomerNFAddressDTO();
	private String newMobileNumber;
	private String countryCodeSelected;
	private String newEmailId;
	private String newEmailId2;
	private String newAdd1;
	private String newAdd2;
	private String newAdd3;
	private String question1;
	private String question2;
	private String answer1;
	private String ans1;
	private String ans2;
	private String answer2;
	private boolean enableMobilePanel = true;
	private boolean enableEmailPanel = true;
	private boolean enableAddressPanel = true;
	private boolean enablePostPanel = true;
	private String poBoxNo;
	private String poBoxEmirates;

	private String postcode;
	private String town;

	private TabView messagesTab = new TabView();
	private boolean applicableAll;
	private String bodyMes;
	Date serDate;
	private boolean fatcaFlagpopUp;
	private String fatcaFlag;
	private String dialCode;
	private List<String> dialCodes = new ArrayList<String>();

	private List<CpQuestionnaire> questionnaireList = new ArrayList<>();
	private List<CpListBoxAnswers> listBoxAnswers = new ArrayList<>();
	private List<CpListBoxAnswers> selectedRecord = new ArrayList<>();
	private CpQuestionnaire questionnaire = new CpQuestionnaire();

	private BigDecimal seqno;

	private String buidlno;
	private String unitnol;
	private String addino;
	private String corredad;

	public String getCorredad() {
		return corredad;
	}

	public void setCorredad(String corredad) {
		this.corredad = corredad;
	}

	public String getBuidlno() {
		return buidlno;
	}

	public void setBuidlno(String buidlno) {
		this.buidlno = buidlno;
	}

	public String getUnitnol() {
		return unitnol;
	}

	public void setUnitnol(String unitnol) {
		this.unitnol = unitnol;
	}

	public String getAddino() {
		return addino;
	}

	public void setAddino(String addino) {
		this.addino = addino;
	}

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {

//		userId=SecurityLibrary.getFullLoggedInUser().getVuserName();
//		FacesContext context = FacesContext.getCurrentInstance();
//		HomeDetailsAction hAction = context.getApplication().evaluateExpressionGet(context, "#{homeDetailsAction}",
//				HomeDetailsAction.class);
//		policyNo = hAction.getSearchPolicyList().getPlanNumber();
//		if(hAction.getPolicyDetailsList().size()>0) {
//			for(int i=0;i<hAction.getPolicyDetailsList().size();i++) {
//				if(policyNo.equalsIgnoreCase(hAction.getPolicyDetailsList().get(i).getPolicyNo())){
//					seqno=hAction.getPolicyDetailsList().get(i).getSeqno();
//				}
//			}
//		}
//		
//		maskPattern = AppSettingURL.Phone_No_Format;
//		logger.info("List all State retreiving started");
//		stateMap = customerService.getMasterLOVs(Constants.NF_STATE, Constants.EN);
//		logger.info("List all Country retreiving started");
//		countryMap = customerService.getMasterLOVs(Constants.NF_COUNTRY, Constants.EN);
//		logger.info("List all Title retreiving started");
//		titleMap = customerService.getMasterLOVs(Constants.NF_TITLE, Constants.EN);
//		countryDialCode = customerService.getCountryCodeLOVs(Constants.NF_COUNTRY_CODE, Constants.EN);
//		identityMap = customerService.getIdentificationDetails(Constants.IDENTIFICATION, Constants.EN);
//		addrLangMap = new TreeMap<String, String>();
//		addrLangMap.put(WordUtils.capitalize("Arabic"), "ARB");
//		addrLangMap.put(WordUtils.capitalize("English"), "EN");
//
//		try {
//			termsConditionYesYes = redirectImpl.listTermAndCondition(Constants.UPDATE_INFORMATION, Constants.MANDATORY,
//					Constants.REQUIRED);
//			termsConditionYesNo = redirectImpl.listTermAndCondition1(Constants.UPDATE_INFORMATION,
//					Constants.MANDATORYNO, Constants.REQUIREDYES);
//			questionnaireList = hAction.getQuestionnaire(Constants.UPDATE_INFORMATION);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		fetchFromCustomerPortalServics();
	}

	private List<ListItem> postcodelist;

	public String fetchingupdateinformationoverall() {
		userId = SecurityLibrary.getFullLoggedInUser().getVuserName();
		FacesContext context = FacesContext.getCurrentInstance();
		HomeDetailsAction hAction = context.getApplication().evaluateExpressionGet(context, "#{homeDetailsAction}",
				HomeDetailsAction.class);
		policyNo = hAction.getSearchPolicyList().getPlanNumber();
		if (hAction.getPolicyDetailsList().size() > 0) {
			for (int i = 0; i < hAction.getPolicyDetailsList().size(); i++) {
				if (policyNo.equalsIgnoreCase(hAction.getPolicyDetailsList().get(i).getPolicyNo())) {
					seqno = hAction.getPolicyDetailsList().get(i).getSeqno();
				}
			}
		}
		postcodelist = customerService.getpostcode("EN", "POST", "", "");
		custNFAddressDTO1 = null;
		custNFAddressContactDTO1 = null;
		maskPattern = AppSettingURL.Phone_No_Format;
		logger.info("List all State retreiving started");
		stateMap = customerService.getMasterLOVs(Constants.NF_STATE, Constants.EN);
		logger.info("List all Country retreiving started");
		countryMap = customerService.getMasterLOVs(Constants.NF_COUNTRY, Constants.EN);
		logger.info("List all Title retreiving started");
		titleMap = customerService.getMasterLOVs(Constants.NF_TITLE, Constants.EN);
		countryDialCode = customerService.getCountryCodeLOVs(Constants.NF_COUNTRY_CODE, Constants.EN);

		// Dial Codes
		Set<String> keySet = countryDialCode.keySet();
		logger.info("Dail Codes keySet: " + keySet + "");
		for (String code : keySet) {
			dialCodes.add(countryDialCode.get(code));
		}
		logger.info("Dail Codes: " + keySet + "");

		identityMap = customerService.getIdentificationDetails(Constants.IDENTIFICATION, Constants.EN);
		addrLangMap = new TreeMap<String, String>();
		addrLangMap.put(WordUtils.capitalize("Arabic"), "ARB");
		addrLangMap.put(WordUtils.capitalize("English"), "EN");

		try {
			termsConditionYesYes = redirectImpl.listTermAndCondition(Constants.UPDATE_INFORMATION, Constants.MANDATORY,
					Constants.REQUIRED);
			termsConditionYesNo = redirectImpl.listTermAndCondition1(Constants.UPDATE_INFORMATION,
					Constants.MANDATORYNO, Constants.REQUIREDYES);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		custNFAddressDTO1 = null;
		custNFAddressContactDTO1 = null;
		custNFAddressContactDTO1 = new CustomerNFAddressContactDTO();
		custNFAddressDTO1 = new CustomerNFAddressDTO();
		buidlno = null;
		unitnol = null;
		addino = null;
		corredad = null;
		postcode = null;
		town = null;
		getSession().setAttribute("UPLOADFILENAME", "EMPTY");

		countryCodeSelected = null;
		newMobileNumber = null;
		newEmailId = null;
		newEmailId2 = null;

		personalnextbutton = true;
		personaldetails = true;

		contactdetails = false;
		contactnextbutton = false;

		addressdetails = false;
		addressnextbutton = false;

		confirmationdetails = false;
		confimrationnutton = false;

		oldcontactoggle = false;
		newcontactoggle = false;

		oldcontact = true;
		newcontact = false;

		oldemailtoggle = false;
		newemailtoggle = false;

		oldemail = true;
		newemail = false;

		oldaddresstoggle = false;
		oldaddress = true;

		newaddresstoggle = false;
		newaddress = false;

		steps = 0;
		errFlag1 = false;
		docupath = false;
		countryCodeSelected = "+971";
		fetchFromCustomerPortalServics();
		fatcaFlagpopUp=false;
		getSession().setAttribute("OTPSUCC", "");
		getSession().setAttribute("FATCAFLAG", "");
		
		fatcaValidation();
		return "/user/step1?faces-redirect=true";
	}
	
	@Autowired
	ContributionHolidaylBL contributionHolidaylBL;

	public void fatcaValidation() {
		   serDate = null;
		   int diffInDays = 0;
		   fatcaFlag = null;
		   fatcaFlagpopUp = false;
		List<CpRequestMaster> requestDate = contributionHolidaylBL.getTransactionCount(SecurityLibrary.getFullLoggedInUser().getVuserName(), "Y");
		logger.info("Fatca Master list size is in UpdateInformationAction ================" + requestDate.size());
		if (requestDate.size() > 0) {
			serDate = requestDate.get(0).getServiceRequestDate();
			diffInDays = (int) ((new Date().getTime() - serDate.getTime()) / (1000 * 60 * 60 * 24));
			System.out.println(diffInDays);
		}
		logger.info("Difference in days for fatca validation in UpdteInformationAction ==============" + diffInDays);
		if (diffInDays > 90 || requestDate.size() == 0) {
			fatcaFlag = "N";
			fatcaFlagpopUp = false;
			getSession().setAttribute("FATCAFLAG", fatcaFlag);
		} else {
			fatcaFlag = "Y";
			fatcaFlagpopUp = true;
			getSession().setAttribute("FATCAFLAG", fatcaFlag);
		}
		logger.info("Fatca flag Status inside UpdateInformationAction =================" + fatcaFlag);
		logger.info("FatcaFlagpopUp flag Status UpdateInformationAction ===================" + fatcaFlagpopUp);
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
		System.out.println("reading fileName: " + event.getFile().getFileName());
		upFile = event.getFile();
		System.out.println(upFile.getFileName());
		if (upFile.getFileName() != null) {
			docupath = true;
			docFilepath = upFile.getFileName();
			docFilesize = (int) upFile.getSize();
			getSession().setAttribute("UPLOADFILE", upFile);
			getSession().setAttribute("UPLOADFILENAME", docFilepath);
			getSession().setAttribute("UPLOADFILESize", docFilesize);
		}
	}

	public void btnUploadFile(ServiceRequestMasterDTO servicerequestnumber) throws Exception {
		String sourcePath = AppSettingURL.SERVICE_DOCUMENT_LOCATION;
		boolean status = true;
		String policylocation = sourcePath + "\\" + policyNo;
		File creatingnewFolder = new File(policylocation);
		if (creatingnewFolder.exists()) {
			int requetno = servicerequestnumber.getServiceRequestNo();
			final String policyfolderlocation = creatingnewFolder.getPath();
			final String requestnumberfolder = policyfolderlocation + "\\" + requetno;
			File requestfolder = new File(requestnumberfolder);
			boolean created = requestfolder.mkdir();
			if (created) {
				cPServiceRequestDocuments = new CPServiceRequestDocuments();
				status = FileUtils.springCopyFile(requestnumberfolder, upFile);
				cPServiceRequestDocuments.setFileName(upFile.getFileName());
				cPServiceRequestDocuments.setFileSize(upFile.getSize());
				CpRequestMaster requester = new CpRequestMaster();
				requester.setServiceRequestNo(servicerequestnumber.getServiceRequestNo());
				cPServiceRequestDocuments.setServiceRequestNo(requester);
				cPServiceRequestDocumentsBL.insert(cPServiceRequestDocuments);
				System.out.println("Folder was created !");
			} else {
				System.out.println("Unable to create folder");
			}
		} else {
			boolean created = creatingnewFolder.mkdir();
			if (created) {
				System.out.println("Folder was created !");
				int requetno = servicerequestnumber.getServiceRequestNo();
				final String policyfolderlocation = creatingnewFolder.getPath();
				final String requestnumberfolder = policyfolderlocation + "\\" + requetno;
				File requestfolder = new File(requestnumberfolder);
				boolean created1 = requestfolder.mkdir();
				if (created1) {
					status = FileUtils.springCopyFile(requestnumberfolder, upFile);
					cPServiceRequestDocuments = new CPServiceRequestDocuments();
					status = FileUtils.springCopyFile(requestnumberfolder, upFile);
					cPServiceRequestDocuments.setFileName(upFile.getFileName());
					cPServiceRequestDocuments.setFileSize(upFile.getSize());
					CpRequestMaster requester = new CpRequestMaster();
					requester.setServiceRequestNo(servicerequestnumber.getServiceRequestNo());
					cPServiceRequestDocuments.setServiceRequestNo(requester);
					cPServiceRequestDocumentsBL.insert(cPServiceRequestDocuments);
				}
			} else {
				System.out.println("Unable to create folder");
			}
		}
	}

	
	
	public void fetchFromCustomerPortalServics() {
		logger.info("List all non financial information retreiving started");

		logger.info("::::::Inside Else condition:::::" + policyNo);
		try {
			customerInfoNonFinDTOMain = customerPortalServices.getPersonalAndAddressDetails(policyNo, Constants.EN);

			logger.info("Size of customerInfoNonFinDTOMain" + customerInfoNonFinDTOMain);

			customerInfoNonFinDTOMain
					.setPropIdentityDesc(getValueFromMap(customerInfoNonFinDTOMain.getPropIdentityCode(), identityMap));

			logger.info(customerInfoNonFinDTOMain.getCustRefNo());
			logger.info(customerInfoNonFinDTOMain.getAddress1());
			logger.info(customerInfoNonFinDTOMain.getAddress2());
			logger.info(customerInfoNonFinDTOMain.getAddress3());
			logger.info(customerInfoNonFinDTOMain.getStateCode());
			logger.info(customerInfoNonFinDTOMain.getPostalCode());
			logger.info(customerInfoNonFinDTOMain.getCountryENG());
			logger.info(customerInfoNonFinDTOMain.getUserDialCode());
			logger.info(customerInfoNonFinDTOMain.getEmail());
			logger.info(customerInfoNonFinDTOMain.getContactNumber());

			// contact code fetching

			// Address and Adress contact Setting Started :
			if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getAddress1())) {
				custNFAddressDTO1.setAddress1(customerInfoNonFinDTOMain.getAddress1());
			}

			if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getAddress2())) {
				custNFAddressDTO1.setAddress2(customerInfoNonFinDTOMain.getAddress2());
			}

			if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getAddress3())) {
				custNFAddressDTO1.setAddress3(customerInfoNonFinDTOMain.getAddress3());
			}

			// addded by malik
//			if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getBuildingno())) {
//				custNFAddressDTO1.setBuildingno(customerInfoNonFinDTOMain.getBuildingno());
//			}
//			
//			if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getUnitno())) {
//				custNFAddressDTO1.setUnitno(customerInfoNonFinDTOMain.getUnitno());
//			}
//			
//			if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getAdditionalno())) {
//				custNFAddressDTO1.setAdditionalno(customerInfoNonFinDTOMain.getAdditionalno());
//			}

			if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getCorresAddress())) {
				custNFAddressDTO1.setCorresAddress(customerInfoNonFinDTOMain.getCorresAddress());
			}

			if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getPoboxn())) {
				custNFAddressDTO1.setPoBoxNo(customerInfoNonFinDTOMain.getPoboxn());
			}

			if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getTown())) {
				custNFAddressDTO1.setTown(customerInfoNonFinDTOMain.getTown());
			}

			if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getPostalCode())) {
				custNFAddressDTO1.setPostalCode(customerInfoNonFinDTOMain.getPostalCode());
			}

//			//ended

			if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getStateCode())) {
				String stateValue = getValueFromMap(customerInfoNonFinDTOMain.getStateCode(), stateMap);
				custNFAddressDTO1.setCitys(stateValue);
			}

//			if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getPostalCode())) {
//				custNFAddressDTO1.setPostalCode(customerInfoNonFinDTOMain.getPostalCode());
//			}

			if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getCountryENG())) {
				custNFAddressDTO1.setCountryENG(customerInfoNonFinDTOMain.getCountryENG());
			}
			if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getUserDialCode())) {
				custNFAddressContactDTO1.setUserDialCode(customerInfoNonFinDTOMain.getUserDialCode());
			}
			if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getEmail())) {
				custNFAddressContactDTO1.setEmail(customerInfoNonFinDTOMain.getEmail());
			}
			if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getContactNumber())) {
				custNFAddressContactDTO1.setContactNumber(customerInfoNonFinDTOMain.getContactNumber());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clearSession() {
		fatcaValidation();
		fetchFromCustomerPortalServics();
		setEnableMobilePanel(true);
		setEnableEmailPanel(true);
		setEnableAddressPanel(true);
		setEnablePostPanel(true);
		setTermsCondition(false);
	}

	public void updateMobileNo() {

		setEnableMobilePanel(true);
		setNewMobileNumber(null);
		setCountryCodeSelected(null);
	}

	public void saveMobileNo() {
		String counCode = null;
		setEnableMobilePanel(false);
		if (BSLUtils.isNotNull(newMobileNumber)) {
			custNFAddressContactDTO1.setContactNumber(newMobileNumber);
			custNFAddressContactDTO1.setUserDialCode(counCode);
		}
		if (BSLUtils.isNotNull(countryCodeSelected)) {
			counCode = countryCodeSelected;
		}
	}

	public void saveDetails() {
		setEnableMobilePanel(true);
		if (BSLUtils.isNull(newMobileNumber)) {
			setEnableMobilePanel(false);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
					"New mobile number can not be .");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
		if (BSLUtils.isNotNull(countryCodeSelected) && BSLUtils.isNotNull(newMobileNumber)) {
			String tmp = countryCodeSelected.replace("+", "00");
			dialCode = tmp;
			custNFAddressContactDTO1.setContactNumber(dialCode + newMobileNumber);
			custNFAddressContactDTO1.setUserDialCode(countryCodeSelected);
		}
		if (BSLUtils.isNull(countryCodeSelected)) {
			setEnableMobilePanel(false);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message", "Dial code is required.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
			dialCode = "";
		}
		System.out.println("Contact Number:" + custNFAddressContactDTO1.getContactNumber());
		System.out.println("Dial Number:" + custNFAddressContactDTO1.getUserDialCode());
	}

	public void cancelUpdateMobileNo() {
		setEnableMobilePanel(false);
	}

	public void saveEmailId() {
		boolean reconfirmEmail = true;
		if (BSLUtils.isNull(newEmailId2)) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
					"ReConfirm Email Id is Required.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
			reconfirmEmail = false;
		}
		if (BSLUtils.isNotNull(newEmailId)) {
			if (errFlag1) {
				// boolean _emailValidation = emailValidationOnTAb();
				boolean _emailValidation = true;
				if (_emailValidation == true && reconfirmEmail == true) {
					setEnableEmailPanel(true);
					if (BSLUtils.isNotNull(newEmailId)) {
						custNFAddressContactDTO1.setEmail(newEmailId);
					}
				}
			}
		} else if (BSLUtils.isNull(newEmailId)) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
					"New Email Id is Required.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
	}

	public void updateEmailId() {

		setEnableEmailPanel(true);
		setNewEmailId(null);
		setNewEmailId2(null);
	}

	public void cancelUpdateEmailId() {
		setEnableEmailPanel(false);
	}

	public void updateAddress() {
		setEnableAddressPanel(true);
	}

	public void saveAddress() {

		setEnableAddressPanel(true);

		if (BSLUtils.isNotNull(newAdd1) || BSLUtils.isNotNull(newAdd2) || BSLUtils.isNotNull(newAdd3)) {

			if (BSLUtils.isNotNull(newAdd1)) {
				custNFAddressDTO1.setAddress1(newAdd1);
			}
			if (BSLUtils.isNotNull(newAdd2)) {
				custNFAddressDTO1.setAddress2(newAdd2);
			}
			if (BSLUtils.isNotNull(newAdd3)) {
				custNFAddressDTO1.setAddress3(newAdd3);
			}

			if (BSLUtils.isNotNull(buidlno)) {
				custNFAddressDTO1.setBuildingno(buidlno);
			}
			if (BSLUtils.isNotNull(unitnol)) {
				custNFAddressDTO1.setUnitno(unitnol);
			}
			if (BSLUtils.isNotNull(addino)) {
				custNFAddressDTO1.setAdditionalno(addino);
			}
			if (BSLUtils.isNotNull(corredad)) {
				custNFAddressDTO1.setCorresAddress(corredad);
			}

		}
	}

	public void cancelUpdateAddress() {
		setEnableAddressPanel(false);
	}

	public void savePostBox() {
		setEnablePostPanel(true);
		if (BSLUtils.isNotNull(poBoxNo) && BSLUtils.isNotNull(poBoxEmirates) || BSLUtils.isNotNull(postcode)
				|| BSLUtils.isNotNull(town)) {

			// custNFAddressDTO1.setPostalCode(poBoxNo);
			custNFAddressDTO1.setPoBoxNo(poBoxNo);
			custNFAddressDTO1.setPostalCode(postcode);
			custNFAddressDTO1.setTown(town);

			String stateValue = getValueFromMap(poBoxEmirates, stateMap);
			custNFAddressDTO1.setCitys(stateValue);
			// custNFAddressDTO1.setTown(stateValue);
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
					"Please enter all the fields");
			PrimeFaces.current().dialog().showMessageDynamic(message);
			setEnablePostPanel(false);
		}

//		else if (BSLUtils.isNull(poBoxNo) && BSLUtils.isNull(poBoxEmirates)) {
//			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
//					"New P.O. Box Saudi and New P.O.Box is required");
//			PrimeFaces.current().dialog().showMessageDynamic(message);
//			setEnablePostPanel(false);
//		} else if (BSLUtils.isNull(poBoxNo) && BSLUtils.isNotNull(poBoxEmirates)) {
//			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
//					"New P.O.Box is required");
//			PrimeFaces.current().dialog().showMessageDynamic(message);
//			setEnablePostPanel(false);
//		} else if (BSLUtils.isNull(poBoxEmirates) && BSLUtils.isNotNull(poBoxNo)) {
//			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
//					"New P.O. Box Emirate is required");
//			PrimeFaces.current().dialog().showMessageDynamic(message);
//			setEnablePostPanel(false);
//		}
	}

	public void savePostBoxAzax() {
		setEnablePostPanel(false);
		if (BSLUtils.isNotNull(poBoxNo) || BSLUtils.isNotNull(poBoxEmirates)) {
			if (BSLUtils.isNotNull(poBoxNo)) {
			}
			if (BSLUtils.isNotNull(poBoxEmirates)) {
			}
		}
	}

	public void savePostBoxAzax1() {
		setEnablePostPanel(false);
		if (BSLUtils.isNotNull(poBoxNo)) {
			if (BSLUtils.isNotNull(poBoxNo)) {
			}
			if (BSLUtils.isNotNull(poBoxEmirates)) {
			}
		}
	}

	public void updatePostBox() {
		setPoBoxNo(null);
		setPoBoxEmirates(null);
		setEnablePostPanel(true);
	}

	public void cancelUpdatePostBox() {
		setEnablePostPanel(false);
	}

	public void clearUpdateInfoValue() {
		setCountryCodeSelected(null);
		setNewMobileNumber(null);
		setNewEmailId(null);
		setNewEmailId2(null);
		setNewAdd1(null);
		setNewAdd2(null);
		setNewAdd3(null);
		setPoBoxNo(null);
		setPoBoxEmirates(null);
		setAddino(null);
		setBuidlno(null);
		setUnitnol(null);
	}

	public int transStatus() {
		Long count = cpNFPersonalDetailsDAOImpl.getRowCount(Constants.UPDATE_INFORMATION, policyNo);
		System.out.println("count==" + count);

		long tmp = 0;

		int output = count.compareTo(tmp);
		return output;
	}

	public void btnContinueLater() {
		int transCount = transStatus();
		String result = customerPortalServices.getAllowedTransaction(policyNo, "NONFN");
		logger.info("Transaction fn called for Update Contacts ==============" + result);
		logger.info("Inside btnContinueLater of UpdateInformationAction Transaction pending " + "count is ======="
				+ transCount);
		if (BSLUtils.isNotNull(result)) {
			if (result.equalsIgnoreCase("ALLOWED")) {
				if (transCount == 0) {
					try {
						String applicaleBox = "Y";
						System.out.println(termsCondition);
						boolean enabledFlag = false;
						if (BSLUtils.isNotNull(newMobileNumber) && BSLUtils.isNotNull(countryCodeSelected)) {
							enabledFlag = true;
						}
						if (BSLUtils.isNotNull(newEmailId)) {
							enabledFlag = true;
						}
						if (BSLUtils.isNotNull(newAdd1) || BSLUtils.isNotNull(newAdd2) || BSLUtils.isNotNull(newAdd3)
								|| BSLUtils.isNotNull(buidlno) || BSLUtils.isNotNull(unitnol)
								|| BSLUtils.isNotNull(addino)) {
							enabledFlag = true;
						}
						if (BSLUtils.isNotNull(poBoxNo) || BSLUtils.isNotNull(poBoxEmirates)
								|| BSLUtils.isNotNull(postcode) || BSLUtils.isNotNull(town)) {
							enabledFlag = true;
						}
						if (enabledFlag) {
							boolean updateBlFlag = true;
							if (!emailValidationOnTAb())
								updateBlFlag = false;
							if (updateBlFlag) {
								requestdto = new ServiceRequestMasterDTO();
								Date date = new Date();
								requestdto.setServiceRequestDate(date);
								requestdto.setServiceRequestType(Constants.UPDATE_INFORMATION);
								requestdto.setPolicyNo(policyNo);
								requestdto.setUserId(userId);
								requestdto.setRequestStatus(Constants.UPDATE_INFO_CONTINUE);
								requestdto.setRecentUpdateDate(date);
								requestdto.setApplicable(applicaleBox);
								requestdto.setSeqno(seqno);
								logger.info("Insert and Get New Request Details started");
								requestdto = transAction.insertServiceRequest(requestdto);
								settingCpAddressDetailList();
								settingCpAddressContactList();
								boolean status = false;
								logger.info("saving update information started");
								status = updateInfoBL.saveUpdateInformationList(cpNFAddressList,
										cpNFAddressContactsList);
								clearUpdateInfoValue();
								if (status) {
									getSession().setAttribute("TRANSACTION", "UPDATEINFORMATION");
									clearAllBooleanValues();
									setEnableMobilePanel(true);
									PrimeFaces.current().executeScript("PF('dlg3').show()");
								}
							}
						} else {
							FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
									"Please whether all the mandatory fields are entered or not");
							PrimeFaces.current().dialog().showMessageDynamic(message);
						}
					} catch (Exception e) {
						logger.info("Error at saving update information: " + e.getMessage());
						e.printStackTrace();
					}
				} else {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
							"Your old transaction request is in process.");
					PrimeFaces.current().dialog().showMessageDynamic(message);
					PrimeFaces.current().executeScript("PF('dlg3').hide()");
				}
			} else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Request is Not Eligible as the Plan status is inactive.Please contact Salama.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				PrimeFaces.current().executeScript("PF('dlg3').hide()");
			}
		}
	}
	
	private String processing;
	
	public String fatcasample() {
		if(processing.equalsIgnoreCase("YES")) {
			return "/factaFrom/upadteinfofatca?faces-redirect=true";
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

	public void btnContinueLatertestfatca() {
		processing = null;
		processing = "NO";
		int transCount = transStatus();
		String result = customerPortalServices.getAllowedTransaction(policyNo, "NONFN");
		logger.info("Transaction fn called for Update Contacts ==============" + result);
		logger.info("Inside btnContinueLater of UpdateInformationAction Transaction pending " + "count is ======="
				+ transCount);
		if (BSLUtils.isNotNull(result)) {
			if (result.equalsIgnoreCase("ALLOWED")) {
				if (transCount == 0) {
					try {
						String applicaleBox = "Y";
						System.out.println(termsCondition);
						boolean enabledFlag = false;
						if (BSLUtils.isNotNull(newMobileNumber) && BSLUtils.isNotNull(countryCodeSelected)) {
							enabledFlag = true;
						}
						if (BSLUtils.isNotNull(newEmailId)) {
							enabledFlag = true;
						}
						if (BSLUtils.isNotNull(newAdd1) || BSLUtils.isNotNull(newAdd2) || BSLUtils.isNotNull(newAdd3)
								|| BSLUtils.isNotNull(buidlno) || BSLUtils.isNotNull(unitnol)
								|| BSLUtils.isNotNull(addino)) {
							enabledFlag = true;
						}
						if (BSLUtils.isNotNull(poBoxNo) || BSLUtils.isNotNull(poBoxEmirates)
								|| BSLUtils.isNotNull(postcode) || BSLUtils.isNotNull(town)) {
							enabledFlag = true;
						}
						if (enabledFlag) {
							boolean updateBlFlag = true;
							if (!emailValidationOnTAb())
								updateBlFlag = false;
							if (updateBlFlag) {
								requestdto = new ServiceRequestMasterDTO();
								Date date = new Date();
								requestdto.setServiceRequestDate(date);
								requestdto.setServiceRequestType(Constants.UPDATE_INFORMATION);
								requestdto.setPolicyNo(policyNo);
								requestdto.setUserId(userId);
								requestdto.setRequestStatus(Constants.UPDATE_INFO_CONTINUE);
								requestdto.setRecentUpdateDate(date);
								requestdto.setApplicable(applicaleBox);
								requestdto.setSeqno(seqno);
								logger.info("Insert and Get New Request Details started");
								requestdto = transAction.insertServiceRequest(requestdto);
								settingCpAddressDetailList();
								settingCpAddressContactList();
								boolean status = false;
								logger.info("saving update information started");
								status = updateInfoBL.saveUpdateInformationList(cpNFAddressList,
										cpNFAddressContactsList);
								clearUpdateInfoValue();
								if (status) {
									processing = "YES";
									getSession().setAttribute("OTPSUCC", "DONE");
									getSession().setAttribute("TRANSACTION", "UPDATEINFORMATION");
									clearAllBooleanValues();
									setEnableMobilePanel(true);
								}
								fatcaFlagpopUp = (boolean) getSession().getAttribute("FATCAFLAGPOP");
							}
						} else {
							FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
									"Please whether all the mandatory fields are entered or not");
							PrimeFaces.current().dialog().showMessageDynamic(message);
						}
					} catch (Exception e) {
						logger.info("Error at saving update information: " + e.getMessage());
						e.printStackTrace();
					}
				} else {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
							"Your old transaction request is in process.");
					PrimeFaces.current().dialog().showMessageDynamic(message);
					PrimeFaces.current().executeScript("PF('dlg3').hide()");
				}
			} else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Request is Not Eligible as the Plan status is inactive.Please contact Salama.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				PrimeFaces.current().executeScript("PF('dlg3').hide()");
			}
		}
	}
	

	public boolean checkEnabledPersDetailsPanel() {
		boolean enableFlag = Boolean.FALSE;
		if (enableNameLabel)
			enableFlag = true;
		if (enableTitleLabel)
			enableFlag = true;
		if (enableFnameLabel)
			enableFlag = true;
		if (enableMnameLabel)
			enableFlag = true;
		if (enableLnameLabel)
			enableFlag = true;
		if (enableidenNoLabel)
			enableFlag = true;
		if (enableLangLabel)
			enableFlag = true;
		if (enableRaceLabel)
			enableFlag = true;
		if (enableReligionLabel)
			enableFlag = true;
		if (enableNationLabel)
			enableFlag = true;
		if (enableMaritalLabel)
			enableFlag = true;
		if (enableBrCountryLabel)
			enableFlag = true;
		if (enableBrPlaceLabel)
			enableFlag = true;
		if (enableidenExpDate)
			enableFlag = true;
		if (enableidenTypeLabel)
			enableFlag = true;
		return enableFlag;
	}

	public boolean validatePersContactPanel() {
		boolean errFlag = Boolean.TRUE;

		for (CustomerNFContactDTO customerNFContactDTO : customerInfoNonFinTemp.getCustomerContactList()) {
			if (customerNFContactDTO.isRenderNewValue()) {
				if (customerNFContactDTO.getContactCode().equals("CONT001")
						|| customerNFContactDTO.getContactCode().equals("CONT002")
						|| customerNFContactDTO.getContactCode().equals("CONT006")
						|| customerNFContactDTO.getContactCode().equals("CONT012")) {
					if (customerNFContactDTO.getNewContactNo().trim().length() > 50) {
						displayErrorMessageWithClientId("customer.policydetails.NFAlt.ContactreslenErr",
								"customerContactmsg");
						errFlag = Boolean.FALSE;
					}
				} else if (customerNFContactDTO.getContactCode().equals("CONT003")
						|| customerNFContactDTO.getContactCode().equals("CONT005")) {
					if (customerNFContactDTO.getNewContactNo().trim().length() > 30) {
						displayErrorMessageWithClientId("customer.policydetails.NFAlt.ContactmailenErr",
								"customerContactmsg");
						errFlag = Boolean.FALSE;
					}
					boolean status = StringUtils.isValidEmail(customerNFContactDTO.getNewContactNo());
					if (!status) {
						displayErrorMessageWithClientId("customer.policydetails.NFAlt.Contactemailerr",
								"customerContactmsg");
						errFlag = Boolean.FALSE;
					}
				} else if (customerNFContactDTO.getContactCode().equals("CONT007")
						|| customerNFContactDTO.getContactCode().equals("CONT008")
						|| customerNFContactDTO.getContactCode().equals("CONT009")
						|| customerNFContactDTO.getContactCode().equals("CONT010")
						|| customerNFContactDTO.getContactCode().equals("CONT011")) {
					if (customerNFContactDTO.getNewContactNo().trim().length() > 20) {
						displayErrorMessageWithClientId("customer.policydetails.NFAlt.ContactNolenErr",
								"customerContactmsg");
						errFlag = Boolean.FALSE;
					} else if (!customerNFContactDTO.getNewContactNo().matches("^[0-9-+]*$")) {
						displayErrorMessageWithClientId("customer.policydetails.NFAlt.ContactNoerr",
								"customerContactmsg", customerNFContactDTO.getContactDesc().toLowerCase());
						errFlag = Boolean.FALSE;
					}
				}
			}
		}
		return errFlag;
	}

	public boolean validateAddrContactPanel() {
		boolean errFlag = Boolean.TRUE;
		if (!enableMobilePanel) {

			if (!newMobileNumber.matches("^[0-9-+]*$")) {
				displayErrorMessage("customer.policydetails.NFAlt.mob");
				errFlag = Boolean.FALSE;
			} else if (newMobileNumber.trim().length() > 9) {
				displayErrorMessage("customer.policydetails.NFAlt.ContactNolenErr9");
				errFlag = Boolean.FALSE;
			}

		}
		if (!enableEmailPanel) {

			logger.info("validation message for checking whether email id is matching or not.");
			String regex = "^(.+)@(.+)$";
			Pattern pattern = Pattern.compile(regex);

			Matcher matcher = pattern.matcher((CharSequence) newEmailId);

			if (!matcher.matches()) {
				displayErrorMessage("customer.policydetails.NFAlt.emailValid");
				errFlag = Boolean.FALSE;
			} else if (newEmailId != newEmailId2 && !newEmailId.equals(newEmailId2)
					&& !newEmailId.equalsIgnoreCase(newEmailId2)) {
				displayErrorMessage("customer.policydetails.NFAlt.emailMatch");
				errFlag = Boolean.FALSE;
			}
		}
		if (!enablePostPanel) {

			if (!poBoxNo.matches("^[0-9-+]*$")) {
				displayErrorMessage("customer.policydetails.NFAlt.pobox");
				errFlag = Boolean.FALSE;
			} else if (poBoxNo.trim().length() > 9) {
				displayErrorMessage("customer.policydetails.NFAlt.pobox6");
				errFlag = Boolean.FALSE;
			}
		}
		return errFlag;
	}

	private boolean errFlag1 = false;

	// Added by vishal
	public boolean emailValidationOnTAb() {
		logger.info("validation message comes after clicking the tab");
		boolean errFlag = Boolean.TRUE;
		errFlag1 = Boolean.FALSE;
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		if (BSLUtils.isNotNull(newEmailId)) {
			Matcher matcher = pattern.matcher((CharSequence) newEmailId);
			if (!matcher.matches()) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Entered Email format is not valid.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				errFlag = Boolean.FALSE;
			}
			if (BSLUtils.isNotNull(newEmailId) && BSLUtils.isNotNull(newEmailId2)) {
				if (!newEmailId.equalsIgnoreCase(newEmailId2)) {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
							"New Email and reconfirm email mismatch.");
					PrimeFaces.current().dialog().showMessageDynamic(message);
					errFlag = Boolean.FALSE;
				} else {
					errFlag1 = Boolean.TRUE;
					saveEmailId();
				}
			}
		}
		return errFlag;

	}

	public String getValueFromMap(String Key, Map mapList) {
		String value = "";

		Set mapSet = mapList.entrySet();

		Iterator it = mapSet.iterator();
		while (it.hasNext()) {
			Entry<String, String> map = (Entry) it.next();
			String data = map.getValue();
			if (Key.equalsIgnoreCase(map.getValue())) {
				value = map.getKey();
			}
		}
		return value;
	}

	public void disableStateForOthers(CustomerNFAddressDTO oldCustNFAddressDTO, int index) {
		if (BSLUtils.isNotNull(oldCustNFAddressDTO.getCountryCode())) {
			if (oldCustNFAddressDTO.getCountryCode().equals("ARE-ISO")) {
				displayErrorMessageWithClientId("customer.policydetails.NFAlt.selectState", "addressDetails" + (index));
				oldCustNFAddressDTO.setValidAddressState(true);
				if (!isAddressStateLabel())
					setAddressStateLabel(true);
			} else {
				oldCustNFAddressDTO.setValidAddressState(false);
				if (isAddressStateLabel())
					setAddressStateLabel(false);
			}
		}
	}

	public void enableAddressDetails(CustomerNFAddressDTO oldCustNFAddressDTO) {
		if (oldCustNFAddressDTO.isEnableAllValue() == true) {
			oldCustNFAddressDTO.setEnableAllValue(false);
			if (!isAddressStateLabel())
				setAddressStateLabel(true);
		} else {
			oldCustNFAddressDTO.setEnableAllValue(true);
			if (isAddressStateLabel())
				setAddressStateLabel(false);
		}
	}

	public void settingCpPersonalDetailList() {
		SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
		String expDateOld = null;
		String expDateNew = null;
		cpNFPersonalDetailList = new ArrayList<CpNFPersonalDetails>();

		CpRequestMaster cpRequestMaster = new CpRequestMaster();
		cpRequestMaster.setServiceRequestNo(requestdto.getServiceRequestNo());

		if (enableTitleLabel) {
			CpNFPersonalDetails cpNFPersonalDetails = new CpNFPersonalDetails();
			cpNFPersonalDetails.setnServiceReqNo(cpRequestMaster);
			cpNFPersonalDetails.setvChangeField(text.getString("customer.policydetails.NFAlt.Title").toUpperCase());
			if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getTitleCode()))
				cpNFPersonalDetails.setvOldValue(customerInfoNonFinDTOMain.getTitleCode());
			cpNFPersonalDetails.setvNewValue(customerInfoNonFinTemp.getTitleCode());
			cpNFPersonalDetailList.add(cpNFPersonalDetails);
		}

		if (enableNameLabel) {
			CpNFPersonalDetails cpNFPersonalDetails = new CpNFPersonalDetails();
			cpNFPersonalDetails.setnServiceReqNo(cpRequestMaster);
			cpNFPersonalDetails.setvChangeField(text.getString("customer.policydetails.NFAlt.GName").toUpperCase());
			if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getGivenName()))
				cpNFPersonalDetails.setvOldValue(customerInfoNonFinDTOMain.getGivenName().toUpperCase());
			cpNFPersonalDetails.setvNewValue(customerInfoNonFinTemp.getGivenName().toUpperCase());
			cpNFPersonalDetailList.add(cpNFPersonalDetails);
		}

		if (enableNameLabel) {
			CpNFPersonalDetails cpNFPersonalDetails = new CpNFPersonalDetails();
			cpNFPersonalDetails.setnServiceReqNo(cpRequestMaster);
			cpNFPersonalDetails
					.setvChangeField(text.getString("customer.policydetails.NFAlt.MiddleName").toUpperCase());
			if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getMiddleName()))
				cpNFPersonalDetails.setvOldValue(customerInfoNonFinDTOMain.getMiddleName().toUpperCase());
			cpNFPersonalDetails.setvNewValue(customerInfoNonFinTemp.getMiddleName().toUpperCase());
			cpNFPersonalDetailList.add(cpNFPersonalDetails);
		}

		if (enableNameLabel) {
			CpNFPersonalDetails cpNFPersonalDetails = new CpNFPersonalDetails();
			cpNFPersonalDetails.setnServiceReqNo(cpRequestMaster);
			cpNFPersonalDetails.setvChangeField(text.getString("customer.policydetails.NFAlt.SurName").toUpperCase());
			if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getLastName()))
				cpNFPersonalDetails.setvOldValue(customerInfoNonFinDTOMain.getLastName().toUpperCase());
			cpNFPersonalDetails.setvNewValue(customerInfoNonFinTemp.getLastName().toUpperCase());
			cpNFPersonalDetailList.add(cpNFPersonalDetails);
		}

		if (enableidenTypeLabel) {
			CpNFPersonalDetails cpNFPersonalDetails = new CpNFPersonalDetails();
			cpNFPersonalDetails.setnServiceReqNo(cpRequestMaster);
			cpNFPersonalDetails.setvChangeField(text.getString("customer.policydetails.NFAlt.idenCode").toUpperCase());
			if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getPropIdentityCode()))
				cpNFPersonalDetails.setvOldValue(customerInfoNonFinDTOMain.getPropIdentityCode());
			cpNFPersonalDetails.setvNewValue(customerInfoNonFinTemp.getPropIdentityCode());
			cpNFPersonalDetailList.add(cpNFPersonalDetails);
			// setting language descrition
			CpNFPersonalDetails cpNFPersonalDetails1 = new CpNFPersonalDetails();
			cpNFPersonalDetails1.setnServiceReqNo(cpRequestMaster);
			cpNFPersonalDetails1
					.setvChangeField(text.getString("customer.policydetails.NFAlt.idenValue").toUpperCase());
			if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getPropIdentityCode())) {
				String idenValue = getValueFromMap(customerInfoNonFinDTOMain.getPropIdentityCode(), identityMap);
				cpNFPersonalDetails1.setvOldValue(idenValue.toUpperCase());
			}
			String idenValue1 = getValueFromMap(customerInfoNonFinTemp.getPropIdentityCode(), identityMap);
			cpNFPersonalDetails1.setvNewValue(idenValue1.toUpperCase());
			cpNFPersonalDetailList.add(cpNFPersonalDetails1);
		}

		if (enableidenNoLabel) {
			CpNFPersonalDetails cpNFPersonalDetails = new CpNFPersonalDetails();
			cpNFPersonalDetails.setnServiceReqNo(cpRequestMaster);
			cpNFPersonalDetails.setvChangeField(text.getString("customer.policydetails.NFAlt.idenNo").toUpperCase());
			if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getPropIdentityNo()))
				cpNFPersonalDetails.setvOldValue(customerInfoNonFinDTOMain.getPropIdentityNo().toUpperCase());
			cpNFPersonalDetails.setvNewValue(customerInfoNonFinTemp.getPropIdentityNo().toUpperCase());
			cpNFPersonalDetailList.add(cpNFPersonalDetails);
		}

		if (enableidenExpDate) {
			CpNFPersonalDetails cpNFPersonalDetails = new CpNFPersonalDetails();
			cpNFPersonalDetails.setnServiceReqNo(cpRequestMaster);
			cpNFPersonalDetails
					.setvChangeField(text.getString("customer.policydetails.NFAlt.idenExpDate").toUpperCase());

			if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getIdenExpiryDate()))

				// Added by Shashi on 23-01-2018 for dd/mm/yyyy format
				expDateOld = sm.format(customerInfoNonFinDTOMain.getIdenExpiryDate());
			expDateNew = sm.format(customerInfoNonFinTemp.getIdenExpiryDate());

			cpNFPersonalDetails.setvOldValue(expDateOld);
			cpNFPersonalDetails.setvNewValue(expDateNew);
			cpNFPersonalDetailList.add(cpNFPersonalDetails);
		}
	}

	public void settingCpAddressDetailList() {
		CpRequestMaster cpRequestMaster = new CpRequestMaster();
		cpRequestMaster.setServiceRequestNo(requestdto.getServiceRequestNo());
		cpNFAddressList = new ArrayList<CpNFAddress>();
		if (BSLUtils.isNotNull(newAdd1) || BSLUtils.isNotNull(newAdd2) || BSLUtils.isNotNull(newAdd3)
				|| BSLUtils.isNotNull(poBoxNo) || BSLUtils.isNotNull(poBoxEmirates) || BSLUtils.isNotNull(postcode)
				|| BSLUtils.isNotNull(town)) {
			CpNFAddress cpNFAddress = new CpNFAddress();
			cpNFAddress.setnServiceReqNo(cpRequestMaster);

			if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getAddressCode())) {
				cpNFAddress.setvAddrTypeCode(customerInfoNonFinDTOMain.getAddressCode());
			}

			if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getAddresCodeDesc())) {
				cpNFAddress.setvAddrType(customerInfoNonFinDTOMain.getAddresCodeDesc());
			}

			if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getAddressSeqNo())) {
				cpNFAddress.setvAddrSeqNo(customerInfoNonFinDTOMain.getAddressSeqNo());
			}

			if (BSLUtils.isNotNull(buidlno) || BSLUtils.isNotNull(unitnol) || BSLUtils.isNotNull(addino)) {
				if (BSLUtils.isNotNull(buidlno)) {
					cpNFAddress.setvBuildingNo(buidlno);
				}

				if (BSLUtils.isNotNull(unitnol)) {
					cpNFAddress.setvUnitNo(unitnol);
				}

				if (BSLUtils.isNotNull(addino)) {
					cpNFAddress.setvAdditionalNo(addino);
//					if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getAdditionalno()))
//						cpNFAddress.setvAdditionalNo(customerInfoNonFinDTOMain.getAdditionalno());
				}
			}

			if (BSLUtils.isNotNull(newAdd1) || BSLUtils.isNotNull(newAdd2) || BSLUtils.isNotNull(newAdd3)) {
				if (BSLUtils.isNotNull(newAdd1)) {
					cpNFAddress.setvAddr1(newAdd1);

					if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getAddress1()))
						cpNFAddress.setvAddr1Old(customerInfoNonFinDTOMain.getAddress1());
				}
				if (BSLUtils.isNotNull(newAdd2)) {
					cpNFAddress.setvAddr2(newAdd2);

					if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getAddress2()))
						cpNFAddress.setvAddr2Old(customerInfoNonFinDTOMain.getAddress2());
				}
				if (BSLUtils.isNotNull(newAdd3)) {
					cpNFAddress.setvAddr3(newAdd3);

					if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getAddress3()))
						cpNFAddress.setvAddr3Old(customerInfoNonFinDTOMain.getAddress3());
				}
			}

			if (BSLUtils.isNotNull(poBoxNo) || BSLUtils.isNotNull(poBoxEmirates) || BSLUtils.isNotNull(postcode)
					|| BSLUtils.isNotNull(town)) {

				if (BSLUtils.isNotNull(poBoxNo)) {
					cpNFAddress.setvPOBoxNo(poBoxNo);
					if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getPostalCode()))
						cpNFAddress.setvPOBoxNoOld(customerInfoNonFinDTOMain.getPostalCode());
				}

				if (BSLUtils.isNotNull(postcode)) {
					cpNFAddress.setvPostalCode(postcode);
				}

				if (BSLUtils.isNotNull(town)) {
					cpNFAddress.setvTown(town);
				}

				if (BSLUtils.isNotNull(poBoxEmirates)) {
					cpNFAddress.setvStateCode(poBoxEmirates);
					String stateValue = getValueFromMap(poBoxEmirates, stateMap);
					cpNFAddress.setvState(stateValue.toUpperCase());

					if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getStateCode())) {
						if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getCountryCode())) {
							if (customerInfoNonFinDTOMain.getCountryCode().equalsIgnoreCase("ARE-ISO")) {
								cpNFAddress.setvStateCodeOld(customerInfoNonFinDTOMain.getStateCode());
								String stateValue1 = getValueFromMap(customerInfoNonFinDTOMain.getStateCode(),
										stateMap);
								cpNFAddress.setvStateOld(stateValue1);

							} else {
								customerInfoNonFinTemp.setStateCode("");
							}
						}
					}
				}
				if (BSLUtils.isNotNull(countryCodeSelected)) {
					cpNFAddress.setvCountryCode(countryCodeSelected);
					String countryValue = getValueFromMap(countryCodeSelected, countryDialCode);
					cpNFAddress.setvCountry(countryValue.toUpperCase());
				}
				// cpNFAddress.setvState(poBoxEmirates.toUpperCase());
			}

			if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getCountryCode())) {
				cpNFAddress.setvCountryCode(customerInfoNonFinDTOMain.getCountryCode());
				String countryValue = getValueFromMap(customerInfoNonFinDTOMain.getCountryCode(), countryMap);
				cpNFAddress.setvCountry(countryValue.toUpperCase());
			}

//			if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getCorresAddress()))
//				cpNFAddress.setvCorresAddr(customerInfoNonFinDTOMain.getCorresAddress().toUpperCase());

			if (BSLUtils.isNotNull(corredad)) {
				cpNFAddress.setvCorresAddr(corredad);
//				if (BSLUtils.isNotNull(customerInfoNonFinDTOMain.getCorresAddress()))
//					cpNFAddress.setvCorresAddr(customerInfoNonFinDTOMain.getCorresAddress());
			}

			cpNFAddressList.add(cpNFAddress);
		}
		// }
	}

	public void settingCpAddressContactList() {
		CpRequestMaster cpRequestMaster = new CpRequestMaster();
		cpRequestMaster.setServiceRequestNo(requestdto.getServiceRequestNo());
		cpNFAddressContactsList = new ArrayList<CpNFAddressContacts>();

		if (BSLUtils.isNotNull(!enableMobilePanel || !enableEmailPanel)) {

			List<CustomerNFContactDTO> listinga = new ArrayList<CustomerNFContactDTO>();
			listinga = customerInfoNonFinDTOMain.getCustomerContactList();

			if (BSLUtils.isNotNull(newMobileNumber)) {
				CpNFAddressContacts cpNFAddressContacts = new CpNFAddressContacts();
				String lovcode = "CONT010";
				String lovcode1 = "MOBILE PHONE NUMBER (1st Choice)";
				if (listinga.size() > 0) {
					for (CustomerNFContactDTO obj : listinga) {
						if (obj.getContactDesc().equalsIgnoreCase("MOBILE NUMBER (1st CHOICE)")) {
							lovcode = obj.getContactCode();
							lovcode1 = obj.getContactDesc();
						}
					}
				}
				cpNFAddressContacts.setnServiceReqNo(cpRequestMaster);
				String tmp = countryCodeSelected.replace("+", "00");
				cpNFAddressContacts.setvNewValue(tmp + "" + newMobileNumber);
				cpNFAddressContacts.setvContactCode(lovcode);
				cpNFAddressContacts.setvContactDesc(lovcode1);
				cpNFAddressContacts.setvOldValue(customerInfoNonFinDTOMain.getContactNumber());
				cpNFAddressContacts.setnAddrSeqNo(customerInfoNonFinDTOMain.getAddressSeqNo());
				cpNFAddressContactsList.add(cpNFAddressContacts);
			}

			if (BSLUtils.isNotNull(newEmailId)) {
				CpNFAddressContacts cpNFAddressContacts = new CpNFAddressContacts();
				String lovcod2 = "CONT003";
				String lovcode3 = "E-MAIL ADDRESS (1st CHOICE)";
				if (listinga.size() > 0) {
					for (CustomerNFContactDTO obj : listinga) {
						if (obj.getContactDesc().equalsIgnoreCase("E-MAIL ADDRESS (1st CHOICE)")) {
							lovcod2 = obj.getContactCode();
							lovcode3 = obj.getContactDesc();
						}
					}
				}
				cpNFAddressContacts.setnServiceReqNo(cpRequestMaster);
				cpNFAddressContacts.setvContactCode(lovcod2);
				cpNFAddressContacts.setvContactDesc(lovcode3);
				cpNFAddressContacts.setvNewValue(newEmailId);
				cpNFAddressContacts.setnAddrSeqNo(customerInfoNonFinDTOMain.getAddressSeqNo());
				cpNFAddressContacts.setvOldValue(customerInfoNonFinDTOMain.getEmail());
				cpNFAddressContactsList.add(cpNFAddressContacts);
			}

		}
	}

	public void clearAllBooleanValues() {

		setEnableTitleLabel(false);
		setEnableNameLabel(false);
		setEnableFnameLabel(false);
		setEnableMnameLabel(false);
		setEnableLnameLabel(false);
		setEnableidenNoLabel(false);
		setEnableLangLabel(false);
		setEnableRaceLabel(false);
		setEnableReligionLabel(false);
		setEnableNationLabel(false);
		setEnableMaritalLabel(false);
		setEnableBrCountryLabel(false);
		setEnableBrPlaceLabel(false);
	}

	public void closeModal() {
		setTitleMsg(text.getString("customer.policydetails.NFAlt.warningheadmsg"));
		setBodyMsg(text.getString("eservices.transaction.cancel"));
		fetchFromCustomerPortalServics();
		// RequestContext requestContext = RequestContext.getCurrentInstance();
		PrimeFaces.current().executeScript("popclose()");
		PrimeFaces.current().executeScript("$('#successModal').modal('show');");
	}

	// Getter and setter
	@SuppressWarnings("rawtypes")
	public RegistrationCustomerService getCustomerService() {
		return customerService;
	}

	@SuppressWarnings("rawtypes")
	public void setCustomerService(RegistrationCustomerService customerService) {
		this.customerService = customerService;
	}

	@SuppressWarnings("rawtypes")
	public CustomerPortalServices getCustomerPortalServices() {
		return customerPortalServices;
	}

	@SuppressWarnings("rawtypes")
	public void setCustomerPortalServices(CustomerPortalServices customerPortalServices) {
		this.customerPortalServices = customerPortalServices;
	}

	public UpdateInfoBL getUpdateInfoBL() {
		return updateInfoBL;
	}

	public void setUpdateInfoBL(UpdateInfoBL updateInfoBL) {
		this.updateInfoBL = updateInfoBL;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public ServiceRequestMasterDTO getRequestdto() {
		return requestdto;
	}

	public void setRequestdto(ServiceRequestMasterDTO requestdto) {
		this.requestdto = requestdto;
	}

	public boolean isTermsCondition() {
		return termsCondition;
	}

	public void setTermsCondition(boolean termsCondition) {
		this.termsCondition = termsCondition;
	}

	public CustomerInfoNonFinDTO getCustomerInfoNonFinDTOMain() {
		return customerInfoNonFinDTOMain;
	}

	public void setCustomerInfoNonFinDTOMain(CustomerInfoNonFinDTO customerInfoNonFinDTOMain) {
		this.customerInfoNonFinDTOMain = customerInfoNonFinDTOMain;
	}

	public CustomerInfoNonFinDTO getCustomerInfoNonFinTemp() {
		return customerInfoNonFinTemp;
	}

	public void setCustomerInfoNonFinTemp(CustomerInfoNonFinDTO customerInfoNonFinTemp) {
		this.customerInfoNonFinTemp = customerInfoNonFinTemp;
	}

	public Map<String, String> getStateMap() {
		return stateMap;
	}

	public void setStateMap(Map<String, String> stateMap) {
		this.stateMap = stateMap;
	}

	public Map<String, String> getCountryMap() {
		return countryMap;
	}

	public void setCountryMap(Map<String, String> countryMap) {
		this.countryMap = countryMap;
	}

	public Map<String, String> getTitleMap() {
		return titleMap;
	}

	public void setTitleMap(Map<String, String> titleMap) {
		this.titleMap = titleMap;
	}

	public Map<String, String> getMaritalMap() {
		return maritalMap;
	}

	public void setMaritalMap(Map<String, String> maritalMap) {
		this.maritalMap = maritalMap;
	}

	public Map<String, String> getReligionMap() {
		return religionMap;
	}

	public void setReligionMap(Map<String, String> religionMap) {
		this.religionMap = religionMap;
	}

	public Map<String, String> getRaceMap() {
		return raceMap;
	}

	public void setRaceMap(Map<String, String> raceMap) {
		this.raceMap = raceMap;
	}

	public Map<String, String> getLanguageMap() {
		return languageMap;
	}

	public void setLanguageMap(Map<String, String> languageMap) {
		this.languageMap = languageMap;
	}

	public Map<String, String> getNationMap() {
		return nationMap;
	}

	public void setNationMap(Map<String, String> nationMap) {
		this.nationMap = nationMap;
	}

	public Map<String, String> getAddrLangMap() {
		return addrLangMap;
	}

	public void setAddrLangMap(Map<String, String> addrLangMap) {
		this.addrLangMap = addrLangMap;
	}

	public boolean isEnableTitleLabel() {
		return enableTitleLabel;
	}

	public void setEnableTitleLabel(boolean enableTitleLabel) {
		this.enableTitleLabel = enableTitleLabel;
	}

	public boolean isEnableNameLabel() {
		return enableNameLabel;
	}

	public void setEnableNameLabel(boolean enableNameLabel) {
		this.enableNameLabel = enableNameLabel;
	}

	public boolean isEnableFnameLabel() {
		return enableFnameLabel;
	}

	public void setEnableFnameLabel(boolean enableFnameLabel) {
		this.enableFnameLabel = enableFnameLabel;
	}

	public boolean isEnableMnameLabel() {
		return enableMnameLabel;
	}

	public void setEnableMnameLabel(boolean enableMnameLabel) {
		this.enableMnameLabel = enableMnameLabel;
	}

	public boolean isEnableLnameLabel() {
		return enableLnameLabel;
	}

	public void setEnableLnameLabel(boolean enableLnameLabel) {
		this.enableLnameLabel = enableLnameLabel;
	}

	public boolean isEnableidenNoLabel() {
		return enableidenNoLabel;
	}

	public void setEnableidenNoLabel(boolean enableidenNoLabel) {
		this.enableidenNoLabel = enableidenNoLabel;
	}

	public boolean isEnableLangLabel() {
		return enableLangLabel;
	}

	public void setEnableLangLabel(boolean enableLangLabel) {
		this.enableLangLabel = enableLangLabel;
	}

	public boolean isEnableRaceLabel() {
		return enableRaceLabel;
	}

	public void setEnableRaceLabel(boolean enableRaceLabel) {
		this.enableRaceLabel = enableRaceLabel;
	}

	public boolean isEnableReligionLabel() {
		return enableReligionLabel;
	}

	public void setEnableReligionLabel(boolean enableReligionLabel) {
		this.enableReligionLabel = enableReligionLabel;
	}

	public boolean isEnableNationLabel() {
		return enableNationLabel;
	}

	public void setEnableNationLabel(boolean enableNationLabel) {
		this.enableNationLabel = enableNationLabel;
	}

	public boolean isEnableMaritalLabel() {
		return enableMaritalLabel;
	}

	public void setEnableMaritalLabel(boolean enableMaritalLabel) {
		this.enableMaritalLabel = enableMaritalLabel;
	}

	public boolean isEnableBrCountryLabel() {
		return enableBrCountryLabel;
	}

	public void setEnableBrCountryLabel(boolean enableBrCountryLabel) {
		this.enableBrCountryLabel = enableBrCountryLabel;
	}

	public boolean isEnableBrPlaceLabel() {
		return enableBrPlaceLabel;
	}

	public void setEnableBrPlaceLabel(boolean enableBrPlaceLabel) {
		this.enableBrPlaceLabel = enableBrPlaceLabel;
	}

	public boolean isAddressStateLabel() {
		return addressStateLabel;
	}

	public void setAddressStateLabel(boolean addressStateLabel) {
		this.addressStateLabel = addressStateLabel;
	}

	public String getBodyMsg() {
		return bodyMsg;
	}

	public void setBodyMsg(String bodyMsg) {
		this.bodyMsg = bodyMsg;
	}

	public String getHeaderMsg() {
		return headerMsg;
	}

	public void setHeaderMsg(String headerMsg) {
		this.headerMsg = headerMsg;
	}

	public String getMaskPattern() {
		return maskPattern;
	}

	public void setMaskPattern(String maskPattern) {
		this.maskPattern = maskPattern;
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

	public void setRedirectImpl(RedirectionBLImpl redirectImpl) {
		this.redirectImpl = redirectImpl;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public List<CpNFPersonalDetails> getCpNFPersonalDetailList() {
		return cpNFPersonalDetailList;
	}

	public List<CpNFPersonalContacts> getCpNFPersonalContactsList() {
		return cpNFPersonalContactsList;
	}

	public List<CpNFAddress> getCpNFAddressList() {
		return cpNFAddressList;
	}

	public List<CpNFAddressContacts> getCpNFAddressContactsList() {
		return cpNFAddressContactsList;
	}

	public void setCpNFPersonalDetailList(List<CpNFPersonalDetails> cpNFPersonalDetailList) {
		this.cpNFPersonalDetailList = cpNFPersonalDetailList;
	}

	public void setCpNFPersonalContactsList(List<CpNFPersonalContacts> cpNFPersonalContactsList) {
		this.cpNFPersonalContactsList = cpNFPersonalContactsList;
	}

	public void setCpNFAddressList(List<CpNFAddress> cpNFAddressList) {
		this.cpNFAddressList = cpNFAddressList;
	}

	public void setCpNFAddressContactsList(List<CpNFAddressContacts> cpNFAddressContactsList) {
		this.cpNFAddressContactsList = cpNFAddressContactsList;
	}

	public boolean isEnableidenTypeLabel() {
		return enableidenTypeLabel;
	}

	public Map<String, String> getIdentityMap() {
		return identityMap;
	}

	public void setEnableidenTypeLabel(boolean enableidenTypeLabel) {
		this.enableidenTypeLabel = enableidenTypeLabel;
	}

	public void setIdentityMap(Map<String, String> identityMap) {
		this.identityMap = identityMap;
	}

	public boolean isEnableidenExpDate() {
		return enableidenExpDate;
	}

	public void setEnableidenExpDate(boolean enableidenExpDate) {
		this.enableidenExpDate = enableidenExpDate;
	}

	public Date getTodayDate() {
		return todayDate;
	}

	public String getTitleMsg() {
		return titleMsg;
	}

	public void setTitleMsg(String titleMsg) {
		this.titleMsg = titleMsg;
	}

	public boolean isEnableAddressPanel() {
		return enableAddressPanel;
	}

	public void setEnableAddressPanel(boolean enableAddressPanel) {
		this.enableAddressPanel = enableAddressPanel;
	}

	public boolean isEnableMobilePanel() {
		return enableMobilePanel;
	}

	public void setEnableMobilePanel(boolean enableMobilePanel) {
		this.enableMobilePanel = enableMobilePanel;
	}

	public boolean isEnableEmailPanel() {
		return enableEmailPanel;
	}

	public void setEnableEmailPanel(boolean enableEmailPanel) {
		this.enableEmailPanel = enableEmailPanel;
	}

	public boolean isEnablePostPanel() {
		return enablePostPanel;
	}

	public void setEnablePostPanel(boolean enablePostPanel) {
		this.enablePostPanel = enablePostPanel;
	}

	public CustomerNFAddressContactDTO getCustNFAddressContactDTO() {
		return custNFAddressContactDTO;
	}

	public void setCustNFAddressContactDTO(CustomerNFAddressContactDTO custNFAddressContactDTO) {
		this.custNFAddressContactDTO = custNFAddressContactDTO;
	}

	public CustomerNFAddressContactDTO getCustNFAddressContactDTO1() {
		return custNFAddressContactDTO1;
	}

	public void setCustNFAddressContactDTO1(CustomerNFAddressContactDTO custNFAddressContactDTO1) {
		this.custNFAddressContactDTO1 = custNFAddressContactDTO1;
	}

	public CustomerNFAddressDTO getCustNFAddressDTO1() {
		return custNFAddressDTO1;
	}

	public void setCustNFAddressDTO1(CustomerNFAddressDTO custNFAddressDTO1) {
		this.custNFAddressDTO1 = custNFAddressDTO1;
	}

	public String getNewMobileNumber() {
		return newMobileNumber;
	}

	public void setNewMobileNumber(String newMobileNumber) {
		this.newMobileNumber = newMobileNumber;
	}

	public String getNewEmailId() {
		return newEmailId;
	}

	public void setNewEmailId(String newEmailId) {
		this.newEmailId = newEmailId;
	}

	public String getNewAdd1() {
		return newAdd1;
	}

	public void setNewAdd1(String newAdd1) {
		this.newAdd1 = newAdd1;
	}

	public String getNewAdd2() {
		return newAdd2;
	}

	public void setNewAdd2(String newAdd2) {
		this.newAdd2 = newAdd2;
	}

	public String getNewAdd3() {
		return newAdd3;
	}

	public void setNewAdd3(String newAdd3) {
		this.newAdd3 = newAdd3;
	}

	public String getQuestion1() {
		return question1;
	}

	public void setQuestion1(String question1) {
		this.question1 = question1;
	}

	public String getQuestion2() {
		return question2;
	}

	public void setQuestion2(String question2) {
		this.question2 = question2;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getPoBoxNo() {
		return poBoxNo;
	}

	public void setPoBoxNo(String poBoxNo) {
		this.poBoxNo = poBoxNo;
	}

	public String getPoBoxEmirates() {
		return poBoxEmirates;
	}

	public void setPoBoxEmirates(String poBoxEmirates) {
		this.poBoxEmirates = poBoxEmirates;
	}

	public TransactionServiceAction getTransAction() {
		return transAction;
	}

	public void setTransAction(TransactionServiceAction transAction) {
		this.transAction = transAction;
	}

	public String getNewEmailId2() {
		return newEmailId2;
	}

	public void setNewEmailId2(String newEmailId2) {
		this.newEmailId2 = newEmailId2;
	}

	public TabView getMessagesTab() {
		return messagesTab;
	}

	public void setMessagesTab(TabView messagesTab) {
		this.messagesTab = messagesTab;
	}

	public Map<String, String> getCountryDialCode() {
		return countryDialCode;
	}

	public void setCountryDialCode(Map<String, String> countryDialCode) {
		this.countryDialCode = countryDialCode;
	}

	public String getCountryCodeSelected() {
		return countryCodeSelected;
	}

	public void setCountryCodeSelected(String countryCodeSelected) {
		this.countryCodeSelected = countryCodeSelected;
	}

	public String getAns1() {
		return ans1;
	}

	public void setAns1(String ans1) {
		this.ans1 = ans1;
	}

	public String getAns2() {
		return ans2;
	}

	public void setAns2(String ans2) {
		this.ans2 = ans2;
	}

	public boolean isApplicableAll() {
		return applicableAll;
	}

	public void setApplicableAll(boolean applicableAll) {
		this.applicableAll = applicableAll;
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

	public String getFatcaFlag() {
		return fatcaFlag;
	}

	public void setFatcaFlagpopUp(boolean fatcaFlagpopUp) {
		this.fatcaFlagpopUp = fatcaFlagpopUp;
	}

	public void setFatcaFlag(String fatcaFlag) {
		this.fatcaFlag = fatcaFlag;
	}

	public String getDialCode() {
		return dialCode;
	}

	public void setDialCode(String dialCode) {
		this.dialCode = dialCode;
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

	public CpQuestionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(CpQuestionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public List<String> getDialCodes() {
		return dialCodes;
	}

	public void setDialCodes(List<String> dialCodes) {
		this.dialCodes = dialCodes;
	}

	public List<ListItem> getPostcodelist() {
		return postcodelist;
	}

	public void setPostcodelist(List<ListItem> postcodelist) {
		this.postcodelist = postcodelist;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getDocFilepath() {
		return docFilepath;
	}

	public void setDocFilepath(String docFilepath) {
		this.docFilepath = docFilepath;
	}

	private boolean personalnextbutton;
	private boolean personaldetails;

	private boolean contactnextbutton;
	private boolean contactdetails;

	private boolean addressdetails;
	private boolean addressnextbutton;

	private boolean confirmationdetails;
	private boolean confimrationnutton;

	private int steps;

	public void personalnext() {
		personalnextbutton = false;
		personaldetails = false;
		contactdetails = true;
		contactnextbutton = true;
		steps = 1;
	}

	public void contactback() {
		personalnextbutton = true;
		personaldetails = true;
		contactdetails = false;
		contactnextbutton = false;
		steps = 0;
	}

	public void contactnext() {
		contactdetails = false;
		contactnextbutton = false;
		steps = 2;
		addressdetails = true;
		addressnextbutton = true;
	}

	public void addressback() {
		contactdetails = true;
		contactnextbutton = true;
		steps = 1;
		addressdetails = false;
		addressnextbutton = false;
	}

	public void adderessnext() {
		confirmationdetails = true;
		confimrationnutton = true;
		steps = 3;
		addressdetails = false;
		addressnextbutton = false;
	
	}

	public void confirmayionback() {
		confirmationdetails = false;
		confimrationnutton = false;
		steps = 2;
		addressdetails = true;
		addressnextbutton = true;
	}

	private boolean oldcontactoggle;
	private boolean newcontactoggle;

	private boolean oldcontact;
	private boolean newcontact;

	private boolean oldemailtoggle;
	private boolean newemailtoggle;

	private boolean oldemail;
	private boolean newemail;

	private boolean oldaddresstoggle;
	private boolean oldaddress;

	private boolean newaddresstoggle;
	private boolean newaddress;

	public void togglecontactedit() {
		newcontactoggle = true;
		newcontact = true;
		oldcontactoggle = false;
		oldcontact = false;
		countryCodeSelected = "+971";
		newMobileNumber = null;
	}

	public void togglecontacteditno() {
		newcontactoggle = false;
		newcontact = false;
		oldcontactoggle = false;
		oldcontact = true;
		countryCodeSelected = null;
		newMobileNumber = null;
	}

	public void submitfinal() {
		boolean contactmail = true;
		if (newcontactoggle == true) {
			if (countryCodeSelected.equalsIgnoreCase(null) || countryCodeSelected.equalsIgnoreCase("")
					|| newMobileNumber.equalsIgnoreCase(null) || newMobileNumber.equalsIgnoreCase("")) {
				contactmail = false;
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Please enter dial code and mobile number.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		}
		if (newemailtoggle == true) {
			if (newEmailId.equalsIgnoreCase(null) || newEmailId.equalsIgnoreCase("")
					|| newEmailId2.equalsIgnoreCase(null) || newEmailId2.equalsIgnoreCase("")) {
				contactmail = false;
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Please enter Email ID.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		}
	}

	public void toggleemailedit() {
		newemailtoggle = true;
		newemail = true;
		oldemailtoggle = false;
		oldemail = false;
		newEmailId = null;
		newEmailId2 = null;
	}

	public void toggleemaileditno() {
		newemailtoggle = false;
		newemail = false;
		oldemailtoggle = false;
		oldemail = true;
		newEmailId = null;
		newEmailId2 = null;

	}

	public void toggleaddressedit() {
		newaddresstoggle = true;
		newaddress = true;
		oldaddresstoggle = false;
		oldaddress = false;
		newAdd1 = null;
		newAdd2 = null;
		newAdd3 = null;
		buidlno = null;
		unitnol = null;
		addino = null;
		poBoxEmirates = null;
		postcode = null;
		poBoxNo = null;
		town = null;
	}

	public void toggleaddresseditno() {
		newaddresstoggle = false;
		newaddress = false;
		oldaddresstoggle = false;
		oldaddress = true;
		newAdd1 = null;
		newAdd2 = null;
		newAdd3 = null;
		buidlno = null;
		unitnol = null;
		addino = null;
		poBoxEmirates = null;
		postcode = null;
		poBoxNo = null;
		town = null;

	}

	public boolean isAddressdetails() {
		return addressdetails;
	}

	public void setAddressdetails(boolean addressdetails) {
		this.addressdetails = addressdetails;
	}

	public boolean isAddressnextbutton() {
		return addressnextbutton;
	}

	public void setAddressnextbutton(boolean addressnextbutton) {
		this.addressnextbutton = addressnextbutton;
	}

	public boolean isOldemailtoggle() {
		return oldemailtoggle;
	}

	public void setOldemailtoggle(boolean oldemailtoggle) {
		this.oldemailtoggle = oldemailtoggle;
	}

	public boolean isNewemailtoggle() {
		return newemailtoggle;
	}

	public void setNewemailtoggle(boolean newemailtoggle) {
		this.newemailtoggle = newemailtoggle;
	}

	public boolean isOldemail() {
		return oldemail;
	}

	public void setOldemail(boolean oldemail) {
		this.oldemail = oldemail;
	}

	public boolean isNewemail() {
		return newemail;
	}

	public void setNewemail(boolean newemail) {
		this.newemail = newemail;
	}

	public boolean isOldcontactoggle() {
		return oldcontactoggle;
	}

	public void setOldcontactoggle(boolean oldcontactoggle) {
		this.oldcontactoggle = oldcontactoggle;
	}

	public boolean isNewcontactoggle() {
		return newcontactoggle;
	}

	public void setNewcontactoggle(boolean newcontactoggle) {
		this.newcontactoggle = newcontactoggle;
	}

	public boolean isOldcontact() {
		return oldcontact;
	}

	public void setOldcontact(boolean oldcontact) {
		this.oldcontact = oldcontact;
	}

	public boolean isNewcontact() {
		return newcontact;
	}

	public void setNewcontact(boolean newcontact) {
		this.newcontact = newcontact;
	}

	public boolean isPersonalnextbutton() {
		return personalnextbutton;
	}

	public void setPersonalnextbutton(boolean personalnextbutton) {
		this.personalnextbutton = personalnextbutton;
	}

	public boolean isPersonaldetails() {
		return personaldetails;
	}

	public void setPersonaldetails(boolean personaldetails) {
		this.personaldetails = personaldetails;
	}

	public boolean isContactnextbutton() {
		return contactnextbutton;
	}

	public void setContactnextbutton(boolean contactnextbutton) {
		this.contactnextbutton = contactnextbutton;
	}

	public boolean isContactdetails() {
		return contactdetails;
	}

	public void setContactdetails(boolean contactdetails) {
		this.contactdetails = contactdetails;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public boolean isOldaddresstoggle() {
		return oldaddresstoggle;
	}

	public void setOldaddresstoggle(boolean oldaddresstoggle) {
		this.oldaddresstoggle = oldaddresstoggle;
	}

	public boolean isOldaddress() {
		return oldaddress;
	}

	public void setOldaddress(boolean oldaddress) {
		this.oldaddress = oldaddress;
	}

	public boolean isNewaddresstoggle() {
		return newaddresstoggle;
	}

	public void setNewaddresstoggle(boolean newaddresstoggle) {
		this.newaddresstoggle = newaddresstoggle;
	}

	public boolean isNewaddress() {
		return newaddress;
	}

	public void setNewaddress(boolean newaddress) {
		this.newaddress = newaddress;
	}

	public boolean isConfirmationdetails() {
		return confirmationdetails;
	}

	public void setConfirmationdetails(boolean confirmationdetails) {
		this.confirmationdetails = confirmationdetails;
	}

	public boolean isConfimrationnutton() {
		return confimrationnutton;
	}

	public void setConfimrationnutton(boolean confimrationnutton) {
		this.confimrationnutton = confimrationnutton;
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
