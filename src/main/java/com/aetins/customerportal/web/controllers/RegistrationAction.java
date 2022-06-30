package com.aetins.customerportal.web.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.el.ELException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aetins.customerportal.core.faceletstaglib.SecurityLibrary;
import com.aetins.customerportal.core.services.IMailService;
import com.aetins.customerportal.web.dto.CpServiceTypeDTO;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.dto.ListItem;
import com.aetins.customerportal.web.dto.MasterListDTO;
import com.aetins.customerportal.web.dto.PasswordRulesDTO;
import com.aetins.customerportal.web.dto.RegistrationCustomerDTO;
import com.aetins.customerportal.web.dto.RegistrationTrackDTO;
import com.aetins.customerportal.web.dto.ResetSecurityAnswerDTO;
import com.aetins.customerportal.web.entity.CpSecurityImgMaster;
import com.aetins.customerportal.web.service.AdminBL;
import com.aetins.customerportal.web.service.CusRegistrationBL;
import com.aetins.customerportal.web.service.ForgetPasswordBL;
import com.aetins.customerportal.web.service.RegistrationCustomerBL;
import com.aetins.customerportal.web.service.RegistrationCustomerService;
import com.aetins.customerportal.web.service.RegistrationTrackBL;
import com.aetins.customerportal.web.service.impl.AdminBLImpl;
import com.aetins.customerportal.web.service.impl.CustomerPortalServicesImpl;
import com.aetins.customerportal.web.service.impl.ForgetPasswordBLImpl;
import com.aetins.customerportal.web.utils.AppSettingURL;
import com.aetins.customerportal.web.utils.ApplicationMailer;
import com.aetins.customerportal.web.utils.BSLUtils;
import com.aetins.customerportal.web.utils.Credential;
import com.aetins.customerportal.web.utils.Encryptor;
import com.aetins.customerportal.web.utils.PasswordRules;

@Controller
@Scope("request")
@RequestMapping(value = "/register")
public class RegistrationAction extends BaseAction implements Serializable {

	
	@Autowired
	private IMailService mailService;
	
	private static final long serialVersionUID = 4416332496236473238L;

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(RedirectionAction.class);

	/*
	 * @Autowired private RegistrationCustomerService registrationCustomerService;
	 */

	@Autowired
	private RegistrationCustomerBL registrationCustomerBL;

	@Autowired
	private CusRegistrationBL cusRegistrationBL;

	@Autowired
	private ForgetPasswordBL forgetPasswordBL;

	@Autowired
	private AdminBL adminImpl;

	@Autowired
	private RegistrationCustomerService registrationCustomerService;

	@Autowired
	private CustomerPortalServicesImpl customerPortalServices;

	@Autowired
	private RegistrationTrackBL registrationTrackBL;

	private RegistrationCustomerDTO registrationCustomerDTO = new RegistrationCustomerDTO();

	private String listType;

	private String language;

	private Map<String, String> identification;

	private Locale locale;

	private Boolean loginFormVisibility;

	private Boolean registerFormVisibility = Boolean.FALSE;

	private Boolean loginActiveSelection;

	private Boolean registrationActiveSelection;

	private ResourceBundle text;

	private UIComponent component;
	private boolean submitButton = false;

	private Map<String, String> securityImage;

	private List<CpSecurityImgMaster> imageList;

	private UploadedFile file;

	private String clientIPTemp;

	private Map<String, String> secretQuestions;

	private String bodyMsg;
	private String termAndCondition;

	private PasswordRulesDTO passwordRulesDTO = new PasswordRulesDTO();

	private List<PasswordRulesDTO> rulesList = new ArrayList();

	private List<CpServiceTypeDTO> ccMailList = new ArrayList();
	private List<String> ccMail = new ArrayList();

	private PasswordRules passwordRules = new PasswordRules();

	@Autowired
	private ApplicationMailer appMailer;

	private boolean renderSucessMessage;
	private boolean errFlag;
	private String eventImageName;
	private boolean renderCropFlag;
	private List<MasterListDTO> masterList = new ArrayList();

	private String passwordruleToolTip = "";

	private String customerName;

	private String maskedEmail;

	private String maskedMobileNo;

	private RegistrationTrackDTO registrationTrackDTO = new RegistrationTrackDTO();

	private RegistrationCustomerDTO regVerifiedDTO = new RegistrationCustomerDTO();
	private String newImageName;
	private boolean renderCropPanel;
	private CroppedImage croppedImage;
	private String croppedImagePath;

	// vinod added for security questions
	private List<ListItem> secretQuestionsList;
	private List<CpUserInfoDTO> question = new ArrayList<CpUserInfoDTO>();
	List<ResetSecurityAnswerDTO> questions = new ArrayList();

	private boolean enablelogin = true;
	private boolean enablecontinue=false;
	private boolean enableconfirm = false;

	public void signup() {
		enablelogin = false;
		enablecontinue = true;
		enableconfirm = false;
	}
	
	public void continue1() {
		enablelogin=false;
		enablecontinue=false;
		enableconfirm=true;
	}
	
	public void cancel1() {
		enablelogin=true;
		enablecontinue=false;
		enableconfirm=false;
	}
		
	
	public String confirmbutt() {		
		return "/usercreation?faces-redirect=true";
	}
	
	public void confirmcancel() {
		enablelogin=true;
		enablecontinue=false;
		enableconfirm=false;
	}
	
	
	public String finalcancel() {
		return "/samplelogin?faces-redirect=true";
	}
	
	
	
	

	public boolean isEnablecontinue() {
		return enablecontinue;
	}

	public void setEnablecontinue(boolean enablecontinue) {
		this.enablecontinue = enablecontinue;
	}

	public boolean isEnablelogin() {
		return enablelogin;
	}

	public void setEnablelogin(boolean enablelogin) {
		this.enablelogin = enablelogin;
	}

	public boolean isEnableconfirm() {
		return enableconfirm;
	}

	public void setEnableconfirm(boolean enableconfirm) {
		this.enableconfirm = enableconfirm;
	}

	@PostConstruct
	public void init() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			text = ((ResourceBundle) context.getApplication().evaluateExpressionGet(context, "#{txt}",ResourceBundle.class));
			btnConfirm();
			rulesList = adminImpl.listPasswordRules();
			// ccMailList = adminImpl.listcCEmail("REGISTRATION");
			/*
			 * if ((BSLUtils.isNotNull(rulesList)) && (!rulesList.isEmpty())) {
			 * passwordruleToolTip =
			 * (text.getString("registrationAction.login.lowercaseAllowed") + ":" +
			 * ((PasswordRulesDTO) rulesList.get(0)).getnMinLowerAllow() + "\n" +
			 * text.getString("registrationAction.login.uppercaseAllowed") + ":" +
			 * ((PasswordRulesDTO) rulesList.get(0)).getnMinUpperAllow()); }
			 */
			
			// Added by vinod
			for (int i = 0; i < rulesList.size(); i++) {
				rulesList.get(i);
				System.out.println(i);

			}
			if (rulesList != null) {
				if (rulesList.size() > 0) {
					if (rulesList.get(0).getnQuestionCount() != 0) {
						for (int i = 0; i < rulesList.get(0).getnQuestionCount(); i++) {
							ResetSecurityAnswerDTO registrationCustomerDTO = new ResetSecurityAnswerDTO();
							if (secretQuestionsList != null) {
								if (secretQuestionsList.size() > 0) {
									registrationCustomerDTO.setSecretQuestions(secretQuestionsList);
								}
							}
							/*
							 * registrationCustomerDTO.setUserSecretQuestion1(
							 * "vinod");
							 * registrationCustomerDTO.setUserSecretQuestion1answer(
							 * "vinodh");
							 */
							questions.add(registrationCustomerDTO);
							logger.info("QuestionsCount" + questions);
						}
					}
				}

			}

			registrationCustomerDTO = new RegistrationCustomerDTO();

			Iterator<PasswordRulesDTO> itPasswordRule = rulesList.iterator();
			while (itPasswordRule.hasNext()) {
				passwordRulesDTO = ((PasswordRulesDTO) itPasswordRule.next());
			}

			if ((language == "EN") || (language.equals("EN")) || (language.equalsIgnoreCase("EN"))) {
				termAndCondition = passwordRulesDTO.getvTncEng();
			} else {
				termAndCondition = passwordRulesDTO.getvTncArb();
			}

			identification = registrationCustomerService.getIdentificationDetails(listType, language);
			locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();

			securityImage = new LinkedHashMap();
			List<CpSecurityImgMaster> cpSecurityImgMasterList = cusRegistrationBL.listCpSecurityImgMaster();
			for (CpSecurityImgMaster cpSecurityImgMaster : cpSecurityImgMasterList) {
				securityImage.put(Integer.toString(cpSecurityImgMaster.getnId()), cpSecurityImgMaster.getvImgPath());
			}
			imageList = cpSecurityImgMasterList;

			setLoginFormVisibility(Boolean.TRUE);
			setRegisterFormVisibility(Boolean.FALSE);
			setLoginActiveSelection(Boolean.TRUE);
			setRegistrationActiveSelection(Boolean.FALSE);
			getRegistrationCustomerDTO().setLanguageCode(language);
			getRegistrationCustomerDTO().setGroup("U");
			getRegistrationCustomerDTO().setPasswordMustChange("Y");
			getRegistrationCustomerDTO().setActive("R");
			getRegistrationCustomerDTO().setLocked("N");
			getRegistrationCustomerDTO().setUserActivationDate(null);
			getRegistrationCustomerDTO().setActiveLogin("N");
			getRegistrationCustomerDTO().setLoginSessionId(null);
			getRegistrationCustomerDTO().setLastUpdateUser("Customer Portal");
			getRegistrationCustomerDTO().setLastUpdateDate(new Date());
		} catch (ELException e) {
			e.printStackTrace();
			
		}
	}

	public RegistrationAction() {
		logger.info("RegistrationAction created");
		listType = "IDENTIFICATION";
		language = "EN";
	}

	public void fetchRegistrationDetails() {
		String idenType = "POLICYNO";
		String idenNo = registrationCustomerDTO.getIdenNo().toUpperCase();

		if ((BSLUtils.isNotNull(idenType)) && (BSLUtils.isNotNull(idenNo))) {
			regVerifiedDTO = registrationCustomerService.getCustDetails("EN",idenType, idenNo);
			
			
			FacesContext context = FacesContext.getCurrentInstance();

			if ((regVerifiedDTO.getEmailID() == null) && (regVerifiedDTO.getMobileNumber() == null)) {
				getRegistrationCustomerDTO().setEmailID(regVerifiedDTO.getEmailID());
				if (BSLUtils.isNull(regVerifiedDTO.getEmailID())) {
					setMaskedEmail(null);
				}
				getRegistrationCustomerDTO().setMobileNumber(regVerifiedDTO.getMobileNumber());
				if (BSLUtils.isNull(regVerifiedDTO.getMobileNumber())) {
					setMaskedMobileNo(null);
				}
				context.addMessage("register-form:identificationNo", new FacesMessage(FacesMessage.SEVERITY_ERROR,
						text.getString("registrationAction.login.invalidIdentificationDetails"), null));
			} else {
				getRegistrationCustomerDTO().setEmailID(regVerifiedDTO.getEmailID());
				String email = regVerifiedDTO.getEmailID();
				if (BSLUtils.isNotNull(email)) {
					maskedEmail = email.replaceAll("(?<=.{4}).(?=[^@]*?@)", "*");
				}
				getRegistrationCustomerDTO().setMobileNumber(regVerifiedDTO.getMobileNumber());
				String mobileNumber = regVerifiedDTO.getMobileNumber();
				if (BSLUtils.isNotNull(mobileNumber)) {
					maskedMobileNo = mobileNumber.replaceAll("\\d(?=(?:\\D*\\d){0,3}\\D*$)", "*");
				}
			}
		}
	}

	public void clearValues() {
		setErrorMsg(null);
		setMaskedMobileNo("");
		setMaskedEmail("");
		registrationCustomerDTO.setMobileNo("");
		registrationCustomerDTO.setEmailId("");
		registrationCustomerDTO.setIdenNo("");
	}
	
	
	public String btnVerify1() {
		renderCropFlag = true;
		boolean displayErrorMsg = false;
		setErrFlag(false);
		
		enablelogin=false;
		enablecontinue=false;
		enableconfirm=true;

		
		try {
			securityImage = new LinkedHashMap();
			List<CpSecurityImgMaster> cpSecurityImgMasterList = cusRegistrationBL.listCpSecurityImgMaster();
			for (CpSecurityImgMaster cpSecurityImgMaster : cpSecurityImgMasterList) {
				securityImage.put(Integer.toString(cpSecurityImgMaster.getnId()), cpSecurityImgMaster.getvImgPath());
			}

			imageList = cpSecurityImgMasterList;
			registrationCustomerDTO.setIdenCode("POLICYNO");
			registrationCustomerDTO = registrationCustomerService.getVerifyDetails(registrationCustomerDTO);
			FacesContext context = FacesContext.getCurrentInstance();

			if ((registrationCustomerDTO.getEmailID() == null) && (registrationCustomerDTO.getEnglishDOB() == null)
					&& (registrationCustomerDTO.getFirstName() == null)
					&& (registrationCustomerDTO.getHijriDOB() == null)
					&& (registrationCustomerDTO.getLastName() == null)
					&& (registrationCustomerDTO.getMobileNumber() == null)) {
				registrationCustomerDTO.setEmailId(null);
				registrationCustomerDTO.setMobileNo(null);
				context.addMessage("register-form:identificationNo", new FacesMessage(FacesMessage.SEVERITY_ERROR,
						text.getString("registrationAction.login.invalidIdentificationDetails"), null));
				return "loginPage";
			}
			if (!registrationCustomerDTO.getEmailId().equalsIgnoreCase(registrationCustomerDTO.getEmailID())) {
				displayErrorMsg = true;

				registrationTrackDTO = geRegistrationTrackDetails(registrationCustomerDTO.getIdenNo(),
						registrationCustomerDTO.getEmailId(), registrationCustomerDTO.getMobileNo(), "F",
						"Email address does not match.");
				registrationTrackBL.saveErrTrackDetails(registrationTrackDTO);
			}

			if (!registrationCustomerDTO.getMobileNo().equalsIgnoreCase(registrationCustomerDTO.getMobileNumber())) {
				displayErrorMsg = true;

				registrationTrackDTO = geRegistrationTrackDetails(registrationCustomerDTO.getIdenNo(),
						registrationCustomerDTO.getEmailId(), registrationCustomerDTO.getMobileNo(), "F",
						"Mobile number does not match.");
				registrationTrackBL.saveErrTrackDetails(registrationTrackDTO);
			}
			if (displayErrorMsg) {
				context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR,
						text.getString("registrationAction.login.invalidMobileNumber.updated"), null));
				setErrFlag(true);
			}
			if ((BSLUtils.isNotNull(registrationCustomerDTO))
					&& (BSLUtils.isNotNull(registrationCustomerDTO.getFirstName()))
					&& (!registrationCustomerDTO.getFirstName().isEmpty())) {
				customerName = (registrationCustomerDTO.getFirstName() + "\t" + registrationCustomerDTO.getLastName());
			} else {
				customerName = "User";
			}

			if (registrationCustomerDTO.getGender().equalsIgnoreCase("M")) {
				masterList = customerPortalServices.getMasterLov("EN", "MTITLE");
			} else if (registrationCustomerDTO.getGender().equalsIgnoreCase("F")) {
				masterList = customerPortalServices.getMasterLov("EN", "FTITLE");
			} else {
				masterList = customerPortalServices.getMasterLov("EN", "TITLE");
			}
			if (errFlag) {
				return "fail";
			}
		} catch (Exception e) {
			logger.info("RegAction: error at verify the account");
			e.printStackTrace();
		}
		return "true";
	}


	public String btnVerify() {
		renderCropFlag = true;
		boolean displayErrorMsg = false;
		setErrFlag(false);
		try {
			securityImage = new LinkedHashMap();
			List<CpSecurityImgMaster> cpSecurityImgMasterList = cusRegistrationBL.listCpSecurityImgMaster();
			for (CpSecurityImgMaster cpSecurityImgMaster : cpSecurityImgMasterList) {
				securityImage.put(Integer.toString(cpSecurityImgMaster.getnId()), cpSecurityImgMaster.getvImgPath());
			}

			imageList = cpSecurityImgMasterList;
			registrationCustomerDTO.setIdenCode("POLICYNO");
			registrationCustomerDTO = registrationCustomerService.getVerifyDetails(registrationCustomerDTO);
			FacesContext context = FacesContext.getCurrentInstance();

			if ((registrationCustomerDTO.getEmailID() == null) && (registrationCustomerDTO.getEnglishDOB() == null)
					&& (registrationCustomerDTO.getFirstName() == null)
					&& (registrationCustomerDTO.getHijriDOB() == null)
					&& (registrationCustomerDTO.getLastName() == null)
					&& (registrationCustomerDTO.getMobileNumber() == null)) {
				registrationCustomerDTO.setEmailId(null);
				registrationCustomerDTO.setMobileNo(null);
				context.addMessage("register-form:identificationNo", new FacesMessage(FacesMessage.SEVERITY_ERROR,
						text.getString("registrationAction.login.invalidIdentificationDetails"), null));
				return "loginPage";
			}
			if (!registrationCustomerDTO.getEmailId().equalsIgnoreCase(registrationCustomerDTO.getEmailID())) {
				displayErrorMsg = true;

				registrationTrackDTO = geRegistrationTrackDetails(registrationCustomerDTO.getIdenNo(),
						registrationCustomerDTO.getEmailId(), registrationCustomerDTO.getMobileNo(), "F",
						"Email address does not match.");
				registrationTrackBL.saveErrTrackDetails(registrationTrackDTO);
			}

			if (!registrationCustomerDTO.getMobileNo().equalsIgnoreCase(registrationCustomerDTO.getMobileNumber())) {
				displayErrorMsg = true;

				registrationTrackDTO = geRegistrationTrackDetails(registrationCustomerDTO.getIdenNo(),
						registrationCustomerDTO.getEmailId(), registrationCustomerDTO.getMobileNo(), "F",
						"Mobile number does not match.");
				registrationTrackBL.saveErrTrackDetails(registrationTrackDTO);
			}
			if (displayErrorMsg) {
				context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR,
						text.getString("registrationAction.login.invalidMobileNumber.updated"), null));
				setErrFlag(true);
			}
			if ((BSLUtils.isNotNull(registrationCustomerDTO))
					&& (BSLUtils.isNotNull(registrationCustomerDTO.getFirstName()))
					&& (!registrationCustomerDTO.getFirstName().isEmpty())) {
				customerName = (registrationCustomerDTO.getFirstName() + "\t" + registrationCustomerDTO.getLastName());
			} else {
				customerName = "User";
			}

			if (registrationCustomerDTO.getGender().equalsIgnoreCase("M")) {
				masterList = customerPortalServices.getMasterLov("EN", "MTITLE");
			} else if (registrationCustomerDTO.getGender().equalsIgnoreCase("F")) {
				masterList = customerPortalServices.getMasterLov("EN", "FTITLE");
			} else {
				masterList = customerPortalServices.getMasterLov("EN", "TITLE");
			}
			if (errFlag) {
				return "loginPage";
			}
		} catch (Exception e) {
			logger.info("RegAction: error at verify the account");
			e.printStackTrace();
		}
		return "verifySuccess";
	}

	public String btnConfirm() {
		secretQuestionsList = new ArrayList();
		secretQuestionsList = cusRegistrationBL.listCpSecurityQuestions();
		secretQuestions = cusRegistrationBL.listCpQuestionDetail();
		return "confirmSuccess";
	}

	public String btnBack() {
		setErrorMsg(null);
		setMaskedMobileNo(null);
		setMaskedEmail(null);
		registrationCustomerDTO.setMobileNo(null);
		registrationCustomerDTO.setEmailId(null);
		registrationCustomerDTO.setEnglishDOB(null);
		registrationCustomerDTO.setFirstName(null);
		registrationCustomerDTO.setHijriDOB(null);
		registrationCustomerDTO.setLastName(null);
		registrationCustomerDTO.setMobileNumber(null);
		return "loginPage";
	}

	public String btnSubmit() {

		boolean registrationStatus = false;

		FacesContext context = FacesContext.getCurrentInstance();
		boolean status;
		try {
			renderSucessMessage = false;

			String filePath = AppSettingURL.IMAGE_LOCATION;

			byte[] bytes = null;

			if (file != null) {
				if (file.getSize() < 10000L) {
					registrationTrackDTO = geRegistrationTrackDetails(registrationCustomerDTO.getIdenNo(),
							registrationCustomerDTO.getEmailId(), registrationCustomerDTO.getMobileNumber(), "F",
							"Image file size minimum uploaded");
					registrationTrackBL.saveErrTrackDetails(registrationTrackDTO);
					context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR,
							text.getString("registrationAction.userCreation.file.minlimit"), null));
					file = null;
					renderCropFlag = false;
					return "";
				}
				if (file.getSize() > 500000L) {
					registrationTrackDTO = geRegistrationTrackDetails(registrationCustomerDTO.getIdenNo(),
							registrationCustomerDTO.getEmailId(), registrationCustomerDTO.getMobileNumber(), "F",
							"Image file size maximum uploaded");
					registrationTrackBL.saveErrTrackDetails(registrationTrackDTO);
					context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR,
							text.getString("register.userCreation.fileSizeLimit"), null));
					file = null;
					renderCropFlag = false;
					return "";
				}
				bytes = file.getContents();
				String filename = FilenameUtils.getName(file.getFileName());
				try {
					if (croppedImagePath != null) {
						ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
						String finalImageActualLocation = externalContext.getRealPath("") + File.separator + "resources"
								+ File.separator + "profileImage" + File.separator
								+ String.valueOf(registrationCustomerDTO.getCustomerReferenceNumber()).trim() + ".jpg";
						copyFile(new File(croppedImagePath), new File(finalImageActualLocation));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			saveAnswer();

			status = cusRegistrationBL.validateCpUserinfo(registrationCustomerDTO);
			if (status) {
				registrationTrackDTO = geRegistrationTrackDetails(registrationCustomerDTO.getIdenNo(),
						registrationCustomerDTO.getEmailId(), registrationCustomerDTO.getMobileNumber(), "F",
						"Unique User name not provided");
				registrationTrackBL.saveErrTrackDetails(registrationTrackDTO);
				FacesContext.getCurrentInstance().addMessage("register-form2:userid", new FacesMessage(
						FacesMessage.SEVERITY_ERROR, text.getString("register.userCreation.error.userID"), null));
				return "";
			}

			if (!registrationCustomerDTO.getUserPassword().equals(registrationCustomerDTO.getUserConfirmPassword())) {
				registrationTrackDTO = geRegistrationTrackDetails(registrationCustomerDTO.getIdenNo(),
						registrationCustomerDTO.getEmailId(), registrationCustomerDTO.getMobileNumber(), "F",
						"Password and confirm password does not match.");
				registrationTrackBL.saveErrTrackDetails(registrationTrackDTO);
				FacesContext.getCurrentInstance().addMessage("register-form2:confirmPassword", new FacesMessage(
						FacesMessage.SEVERITY_ERROR, text.getString("registrationAction.userCreation.password"), null));
				return "";
			}

			rulesList = forgetPasswordBL.listPasswordRules();
			if ((((PasswordRulesDTO) rulesList.get(0)).getvPasswordSameasUser().equalsIgnoreCase("N"))
					&& (registrationCustomerDTO.getUserId()
							.equalsIgnoreCase(registrationCustomerDTO.getUserPassword()))) {
				registrationTrackDTO = geRegistrationTrackDetails(registrationCustomerDTO.getIdenNo(),
						registrationCustomerDTO.getEmailId(), registrationCustomerDTO.getMobileNumber(), "F",
						"Username and password entered same.");
				registrationTrackBL.saveErrTrackDetails(registrationTrackDTO);
				FacesContext.getCurrentInstance().addMessage("register-form2:userid",
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								text.getString("registrationAction.userCreation.userandpassword"), null));
				FacesContext.getCurrentInstance().addMessage("register-form2:enterPassword",
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								text.getString("registrationAction.userCreation.userandpassword"), null));
				FacesContext.getCurrentInstance().addMessage("register-form2:confirmPassword",
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								text.getString("registrationAction.userCreation.userandpassword"), null));
				return "";
			}

			passwordRules.rules(registrationCustomerDTO.getUserPassword());
			boolean rulesErr = false;
			if (!validatePasswordRule()) {
				rulesErr = true;
			}
			if (!specialCharValidation()) {
				rulesErr = true;
			}
			if (rulesErr) {
				registrationTrackDTO = geRegistrationTrackDetails(registrationCustomerDTO.getIdenNo(),
						registrationCustomerDTO.getEmailId(), registrationCustomerDTO.getMobileNumber(), "F",
						"Password rules not matched according to setup.");
				registrationTrackBL.saveErrTrackDetails(registrationTrackDTO);
				displayErrorMessage("changePassword.error");
				return "";
			}
			registrationCustomerDTO.setUserPassword(Credential.crypt(registrationCustomerDTO.getUserPassword()));
			registrationCustomerDTO.setUserConfirmPassword(Encryptor.encrypt("abcdefgh", "44e9942f5036dca3",
					registrationCustomerDTO.getUserConfirmPassword()));

			/*
			 * if (registrationCustomerDTO.getUserSecretQuestion1()
			 * .equalsIgnoreCase(registrationCustomerDTO.getUserSecretQuestion2( ))) {
			 * registrationTrackDTO =
			 * geRegistrationTrackDetails(registrationCustomerDTO.getIdenNo(),
			 * registrationCustomerDTO.getEmailId(),
			 * registrationCustomerDTO.getMobileNumber(), "F", "Same Questions selected.");
			 * registrationTrackBL.saveErrTrackDetails(registrationTrackDTO);
			 * FacesContext.getCurrentInstance().addMessage(
			 * "register-form2:secretQuestion1", new FacesMessage(
			 * FacesMessage.SEVERITY_ERROR,
			 * text.getString("registrationAction.userCreation.secret"), null));
			 * FacesContext.getCurrentInstance().addMessage(
			 * "register-form2:secretQuestion2", new FacesMessage(
			 * FacesMessage.SEVERITY_ERROR,
			 * text.getString("registrationAction.userCreation.secret"), null)); return "";
			 * }
			 */

			if (registrationCustomerDTO.getImageID() == 0) {
				registrationTrackDTO = geRegistrationTrackDetails(registrationCustomerDTO.getIdenNo(),
						registrationCustomerDTO.getEmailId(), registrationCustomerDTO.getMobileNumber(), "F",
						"Security Image does not selected.");
				registrationTrackBL.saveErrTrackDetails(registrationTrackDTO);
				FacesContext.getCurrentInstance().addMessage("register-form2:radioSecret",
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								text.getString("registrationAction.userCreation.securityImage"), null));
				return "";
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while saving " + e.getMessage());
			return "";
		}
		try {
			registrationCustomerDTO.setToken(UUID.randomUUID().toString());

			if (questions.size() > 0) {
				for (int i = 0; i < questions.size(); i++) {
					if (i == 0) {
						registrationCustomerDTO.setUserSecretQuestion1(questions.get(i).getSecurityQues());
						registrationCustomerDTO.setUserSecretQuestion1answer(questions.get(i).getSecurityAns());
					} else if (i == 1) {
						registrationCustomerDTO.setUserSecretQuestion2(questions.get(i).getSecurityQues());
						registrationCustomerDTO.setUserSecretQuestion2answer(questions.get(i).getSecurityAns());
					}
				}
			}

			cusRegistrationBL.addUserInfo(registrationCustomerDTO);
			cusRegistrationBL.addCustomerDetail(registrationCustomerDTO);
			registrationStatus = true;

			if (registrationStatus == true) {

				registrationCustomerDTO.setAppUrl("/activateUser.jsf?token=");
				String name = "";
				if ((BSLUtils.isNotNull(registrationCustomerDTO.getFirstName()))
						&& (BSLUtils.isNotNull(registrationCustomerDTO.getLastName()))) {
					name = registrationCustomerDTO.getFirstName() + " " + registrationCustomerDTO.getLastName();
				} else if ((BSLUtils.isNotNull(registrationCustomerDTO.getFirstName()))
						&& (!BSLUtils.isNotNull(registrationCustomerDTO.getLastName()))) {
					name = registrationCustomerDTO.getFirstName();
				} else if ((!BSLUtils.isNotNull(registrationCustomerDTO.getFirstName()))
						&& (BSLUtils.isNotNull(registrationCustomerDTO.getLastName()))) {
					name = registrationCustomerDTO.getLastName();
				} else {
					name = "Customer";
				}

				/*
				 * for (CpServiceTypeDTO dto : ccMailList) { ccMail.add(dto.getCcEmail()); }
				 */

				/*
				 * appMailer.sendMail(registrationCustomerDTO.getEmailId(), ccMail,
				 * text.getString( "registrationAction.login.confirmationEmailSubject"),
				 * getRegistrationTemplate(registrationCustomerDTO.getUserId(),
				 * registrationCustomerDTO.getIdenNo(), registrationCustomerDTO.getTitle(),
				 * name));
				 */
				mailService.sendMail(registrationCustomerDTO.getEmailId(), text.getString("registrationAction.login.confirmationEmailSubject"),
						registrationCustomerDTO.getIdenNo()+""+registrationCustomerDTO.getTitle()+""+name);
				
				
//				appMailer.sendMail(registrationCustomerDTO.getEmailId(),
//						text.getString("registrationAction.login.confirmationEmailSubject"),
//						getRegistrationTemplate(registrationCustomerDTO.getUserId(),
//								registrationCustomerDTO.getIdenNo(), registrationCustomerDTO.getTitle(), name));

				
				
				registrationTrackDTO = geRegistrationTrackDetails(registrationCustomerDTO.getIdenNo(),
						registrationCustomerDTO.getEmailId(), registrationCustomerDTO.getMobileNumber(), "S",
						"Registration Successfull. UserName is:" + registrationCustomerDTO.getUserId());
				registrationTrackBL.saveErrTrackDetails(registrationTrackDTO);
				setLoginFormVisibility(Boolean.TRUE);
				setRegisterFormVisibility(Boolean.FALSE);
				setLoginActiveSelection(Boolean.TRUE);
				setRegistrationActiveSelection(Boolean.FALSE);
				setMaskedEmail(null);
				setMaskedMobileNo(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			setRenderSucessMessage(false);
			try {
				if (!e.getMessage().equalsIgnoreCase("DUPLICATE ENTRY %")) {
					registrationTrackDTO = geRegistrationTrackDetails(registrationCustomerDTO.getIdenNo(),
							registrationCustomerDTO.getEmailId(), registrationCustomerDTO.getMobileNumber(), "F",
							"Customer is trying to attempt duplicate registration");
					registrationTrackBL.saveErrTrackDetails(registrationTrackDTO);
				} else {
					registrationTrackDTO = geRegistrationTrackDetails(registrationCustomerDTO.getIdenNo(),
							registrationCustomerDTO.getEmailId(), registrationCustomerDTO.getMobileNumber(), "F",
							e.getMessage());
					registrationTrackBL.saveErrTrackDetails(registrationTrackDTO);
				}
			} catch (Exception ex) {
				logger.error("Error at unhost exception" + ex);
				ex.printStackTrace();
			}
			// RequestContext requestContext = RequestContext.getCurrentInstance();
			PrimeFaces.current().executeScript("jQuery('#services-1').modal('show');");
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);
			session.invalidate();
			logger.error("Error while saving" + e.getMessage());
			return "";
		}

		logger.info("save success" + registrationCustomerDTO);

		setSubmitButton(true);
		setRenderSucessMessage(true);

		setBodyMsg(text.getString("registrationAction.userCreation.success"));
		registrationCustomerDTO = null;
		registrationTrackDTO = null;
		// RequestContext requestContext = RequestContext.getCurrentInstance();
		PrimeFaces.current().executeScript("jQuery('#services-1').modal('show');");
		return "";
	}

	public void saveAnswer() {

		if (questions != null) {
			if (questions.size() > 0) {
				if (registrationCustomerDTO.getUserId() != null) {
					List<ResetSecurityAnswerDTO> quesList = new ArrayList<>();
					for (ResetSecurityAnswerDTO eachquestions : questions) {

						eachquestions.setUserName(registrationCustomerDTO.getUserId());
						eachquestions.setCustRefNo(regVerifiedDTO.getCustomerReferenceNumber().intValue());
						eachquestions.setQuesStatus("A");
						Date date = new Date();
						eachquestions.setProcessDate(date);
						eachquestions.setRecentModifiedDate(date);

						// resetSecurityAnswerDTO.setId(eachquestions);
						quesList.add(eachquestions);
						logger.info("saving security questions" + registrationCustomerBL);
					}

					registrationCustomerBL.saveRegistraionQuestionDetails(quesList);
				}
			}
		}

	}

	public String clkLogin() {
		setLoginFormVisibility(Boolean.TRUE);
		setRegisterFormVisibility(Boolean.FALSE);
		registerFormVisibility = Boolean.FALSE;

		setLoginActiveSelection(Boolean.TRUE);
		setRegistrationActiveSelection(Boolean.FALSE);

		return "loginPage";
	}

	public String clkRegister() {
		setLoginFormVisibility(Boolean.FALSE);
		setRegisterFormVisibility(Boolean.TRUE);
		registerFormVisibility = Boolean.TRUE;
		setLoginActiveSelection(Boolean.FALSE);
		setRegistrationActiveSelection(Boolean.TRUE);

		return "loginPage";
	}

	public boolean validatePasswordRule() {
		boolean validateDetail = true;

		if (registrationCustomerDTO.getUserPassword().length() < ((PasswordRulesDTO) rulesList.get(0))
				.getnPasswordLength()) {
			FacesContext.getCurrentInstance().addMessage("register-form2:enterPassword",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, text.getString("changePassword.error.passwordLength")
							+ " " + ((PasswordRulesDTO) rulesList.get(0)).getnPasswordLength(), null));

			validateDetail = false;
		}

		if (passwordRules.getUpperCase() < ((PasswordRulesDTO) rulesList.get(0)).getnMinUpperAllow()) {
			FacesContext.getCurrentInstance().addMessage("register-form2:enterPassword",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, text.getString("changePassword.error.MinUppercase")
							+ " " + ((PasswordRulesDTO) rulesList.get(0)).getnMinUpperAllow(), null));

			validateDetail = false;
		}

		if (passwordRules.getLowerCase() < ((PasswordRulesDTO) rulesList.get(0)).getnMinLowerAllow()) {
			FacesContext.getCurrentInstance().addMessage("register-form2:enterPassword",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, text.getString("changePassword.error.MinLower") + " "
							+ ((PasswordRulesDTO) rulesList.get(0)).getnMinLowerAllow(), null));

			validateDetail = false;
		}

		if (passwordRules.getAlpha() < ((PasswordRulesDTO) rulesList.get(0)).getnMinAlphabetAllow()) {
			FacesContext.getCurrentInstance()
					.addMessage("register-form2:enterPassword",
							new FacesMessage(
									FacesMessage.SEVERITY_ERROR, text.getString("changePassword.error.MinAlphabet")
											+ " " + ((PasswordRulesDTO) rulesList.get(0)).getnMinAlphabetAllow(),
									null));

			validateDetail = false;
		}

		if (passwordRules.getDigit() < ((PasswordRulesDTO) rulesList.get(0)).getnMinNoAllow()) {
			FacesContext.getCurrentInstance().addMessage("register-form2:enterPassword",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, text.getString("changePassword.error.MinNo") + " "
							+ ((PasswordRulesDTO) rulesList.get(0)).getnMinNoAllow(), null));

			validateDetail = false;
		}

		return validateDetail;
	}

	public void displayErrorMessageWithParam(String message, int param) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(component.getClientId(), new FacesMessage(text.getString(message) + " " + param));
	}

	public void displayErrorMessage(String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(component.getClientId(), new FacesMessage(text.getString(message)));
	}

	public boolean specialCharValidation() {
		boolean validateDetail = true;
		if ((((PasswordRulesDTO) rulesList.get(0)).getvSpecialCharAllow() == "Y")
				|| (((PasswordRulesDTO) rulesList.get(0)).getvSpecialCharAllow().equals("Y"))
				|| (((PasswordRulesDTO) rulesList.get(0)).getvSpecialCharAllow().equalsIgnoreCase("Y"))) {
			if (passwordRules.getSpecialChar() < ((PasswordRulesDTO) rulesList.get(0)).getnMinSpecialAllow()) {
				FacesContext.getCurrentInstance().addMessage("register-form2:enterPassword",
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								text.getString("changePassword.error.MinSpecialChar") + " "
										+ ((PasswordRulesDTO) rulesList.get(0)).getnMinSpecialAllow(),
								null));

				validateDetail = false;
			} else {
				validateDetail = true;
			}
		} else if (passwordRules.getSpecialChar() == 0) {
			validateDetail = true;
		} else {
			FacesContext.getCurrentInstance().addMessage("register-form2:enterPassword", new FacesMessage(
					FacesMessage.SEVERITY_ERROR, text.getString("changePassword.error.noSpecialchar"), null));

			validateDetail = false;
		}
		return validateDetail;
	}

	public void uploadImage(FileUploadEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		if (event.getFile().getSize() < 10000L) {
			context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR,
					text.getString("registrationAction.userCreation.file.minlimit"), null));
		} else if (event.getFile().getSize() > 500000L) {
			context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR,
					text.getString("register.userCreation.fileSizeLimit"), null));
		}

		// Need to upgrade to prime faces 7.0
		// RequestContext.getCurrentInstance().reset("dialogForm:cropDialog");
		setRenderCropPanel(false);
		if (event != null) {
			file = event.getFile();
			setEventImageName(event.getFile().getFileName());
			System.out.println(file.getFileName());

			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			String filePath = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "crop"
					+ File.separator;
			File targetFolder = new File(filePath);
			byte[] bytes = null;
			if (file != null) {
				bytes = file.getContents();
				String extension = FilenameUtils.getExtension(file.getFileName());
				String filename = "temp" + String.valueOf(registrationCustomerDTO.getCustomerReferenceNumber()) + "."
						+ extension;
				String deleteCropImage = String.valueOf(registrationCustomerDTO.getCustomerReferenceNumber()) + "."
						+ extension;
				try {
					if (!targetFolder.exists()) {
						targetFolder.mkdir();
					}
					String uploadedImage = filePath + filename;

					File fileTemp = new File(uploadedImage);
					if (fileTemp.exists()) {
						fileTemp.delete();
					}
					File fileTemp1 = new File(deleteCropImage);
					if (fileTemp1.exists()) {
						fileTemp1.delete();
					}
					System.out.println("uploadedImage:" + uploadedImage);
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(new File(uploadedImage)));
					stream.write(bytes);

					stream.flush();
					stream.close();
					setRenderCropPanel(true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void copyFile(File sourceFile, File destinationFile) {
		try {
			FileInputStream fileInputStream = new FileInputStream(sourceFile);
			FileOutputStream fileOutputStream = new FileOutputStream(destinationFile);

			int bufferSize;
			byte[] bufffer = new byte[512];
			while ((bufferSize = fileInputStream.read(bufffer)) > 0) {
				fileOutputStream.write(bufffer, 0, bufferSize);
			}
			fileInputStream.close();
			fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void crop() {
		setRenderCropPanel(true);
		if (croppedImage == null) {
			return;
		}
		String custRef = "";
		if ((registrationCustomerDTO != null) && (registrationCustomerDTO != null)) {
			custRef = "temp1" + String.valueOf(registrationCustomerDTO.getCustomerReferenceNumber());
		}

		if ((custRef != null) && (!custRef.equalsIgnoreCase("")) && (!custRef.equalsIgnoreCase(null))) {
			setNewImageName(custRef);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			croppedImagePath = (externalContext.getRealPath("") + File.separator + "resources" + File.separator + "crop"
					+ File.separator + getNewImageName() + ".jpg");
			try {
				System.out.println("cropped newFileName:" + croppedImagePath);
				File fileTemp = new File(croppedImagePath);
				if (fileTemp.exists()) {
					fileTemp.delete();
				}
				FileImageOutputStream imageOutput = new FileImageOutputStream(new File(croppedImagePath));
				imageOutput.write(croppedImage.getBytes(), 0, croppedImage.getBytes().length);
				imageOutput.close();

				saveCroppedImage();
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Cropping failed."));
				return;
			}
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Cropping finished."));
		}
	}

	public void saveCroppedImage() {
		System.out.println("called saveCroppedImage method");
		setRenderCropPanel(false);
	}

	public void cancelCroppedImage() {
		System.out.println("called cancelCroppedImage method");
		setRenderCropPanel(false);
	}

	public void onShowDialogue(int sleep) {
		System.out.println("onShowDialogue method called");
		try {
			// Need to upgrade to primefaces 7.0
			// RequestContext.getCurrentInstance().reset("dialogForm:cropDialog");
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Finished");
	}

	// vinod for security questions not repeating for selected questions
	public ListItem getSelectedItem(List<ListItem> temp, String value) {
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getDescription().equalsIgnoreCase(value)) {
				return temp.get(i);
			}
		}
		return null;

	}

	public void onChangeQuestion(int index) {
		if (questions != null) {
			if (questions.size() > 0) {
				secretQuestionsList = new ArrayList();
				secretQuestionsList = cusRegistrationBL.listCpSecurityQuestions();
				Map<Integer, String> selectedQuestions = new HashMap();
				for (int i = 0; i < questions.size(); i++) {
					if (questions.get(i).getSecurityQues() != null && questions.get(i).getSecurityQues() != "") {
						selectedQuestions.put(i, questions.get(i).getSecurityQues());// Adding
																						// selected
																						// questions
					}
				}
				System.out.println("selectedQuestions size:" + selectedQuestions.size());
				logger.info("selectedQuestions size:" + selectedQuestions.size());

				if (selectedQuestions != null) {
					if (selectedQuestions.size() > 0) {
						for (int i = 0; i < questions.size(); i++) {// 3
							List<ListItem> temp = new ArrayList(secretQuestionsList);
							for (Entry<Integer, String> alreadySelectedQuestion : selectedQuestions.entrySet()) {// 3
								if (alreadySelectedQuestion.getKey() != i) {// Removing
																			// selected
																			// questions
																			// from
																			// the
																			// list
																			// except
																			// current
																			// question
									ListItem remaining = getSelectedItem(temp, alreadySelectedQuestion.getValue());
									temp.remove(remaining);
								}
							}
							questions.get(i).setSecretQuestions(temp);// After
																		// removing
																		// all
																		// selected
																		// questions
																		// except
																		// current
																		// selected
																		// question
							logger.info("After removing all selected questions except current selected question");
							logger.info(questions.toString());

						}
					}
				}
			}
		}
	}

	public RegistrationTrackDTO geRegistrationTrackDetails(String planNo, String emailId, String phoneNo, String status,
			String failReason) throws Exception {
		Date date = new Date();
		RegistrationTrackDTO registrationTrackDTO = null;
		try {
			HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
					.getExternalContext().getRequest();

			Cookie[] cookies = httpServletRequest.getCookies();
			for (Cookie each : cookies) {
				if ((each != null) && (each.getName().equalsIgnoreCase("ClientIPTemp"))) {
					setClientIPTemp(each.getValue());
				}
			}

			registrationTrackDTO = new RegistrationTrackDTO();
			registrationTrackDTO.setIpAddr(clientIPTemp);
			if (BSLUtils.isNotNull(planNo))
				registrationTrackDTO.setPlanNo(planNo);
			if (BSLUtils.isNotNull(emailId))
				registrationTrackDTO.setEmailId(emailId);
			if (BSLUtils.isNotNull(phoneNo))
				registrationTrackDTO.setPhoneNo(phoneNo);
			if (BSLUtils.isNotNull(status))
				registrationTrackDTO.setStatus(status);
			if (BSLUtils.isNotNull(failReason))
				registrationTrackDTO.setFailReason(failReason);
			if (BSLUtils.isNotNull(registrationCustomerDTO.getUserId())) {
				registrationTrackDTO.setUserId(registrationCustomerDTO.getUserId());
			}
			registrationTrackDTO.setRegdate(date);
			registrationTrackDTO.setLastupdProg("Registration".toUpperCase());
			registrationTrackDTO.setLastupdUser("Customer Portal".toUpperCase());
			registrationTrackDTO.setLastupdDate(date);
		} catch (Exception e) {
			logger.info("RegAction: error at getting ip address");
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return registrationTrackDTO;
	}

	public String userPwdValidate() {
		if (BSLUtils.isNotNull(registrationCustomerDTO.getUserPassword())) {
			if (rulesList.size() == 0)
				rulesList = forgetPasswordBL.listPasswordRules();
			if ((((PasswordRulesDTO) rulesList.get(0)).getvPasswordSameasUser().equalsIgnoreCase("N"))
					&& (BSLUtils.isNotNull(registrationCustomerDTO.getUserId())) && (registrationCustomerDTO.getUserId()
							.equalsIgnoreCase(registrationCustomerDTO.getUserPassword()))) {
				FacesContext.getCurrentInstance().addMessage("register-form2:userid",
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								text.getString("registrationAction.userCreation.userandpassword"), null));
				FacesContext.getCurrentInstance().addMessage("register-form2:enterPassword",
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								text.getString("registrationAction.userCreation.userandpassword"), null));
				FacesContext.getCurrentInstance().addMessage("register-form2:confirmPassword",
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								text.getString("registrationAction.userCreation.userandpassword"), null));
			}

			passwordRules.rules(registrationCustomerDTO.getUserPassword());
			boolean rulesErr = false;
			if (!validatePasswordRule()) {
				rulesErr = true;
			}

			if (!specialCharValidation()) {
				rulesErr = true;
			}

		} else if (registrationCustomerDTO.getUserPassword().trim().equals("")) {
			FacesContext.getCurrentInstance().addMessage("register-form2:enterPassword", new FacesMessage(
					FacesMessage.SEVERITY_ERROR, text.getString("registrationAction.userCreation.userpwdSpace"), null));
		}

		return "";
	}

	public String userCfmPwdValidate() {
		if ((BSLUtils.isNotNull(registrationCustomerDTO.getUserPassword())) && (!registrationCustomerDTO
				.getUserPassword().equals(registrationCustomerDTO.getUserConfirmPassword()))) {
			FacesContext.getCurrentInstance().addMessage("register-form2:confirmPassword", new FacesMessage(
					FacesMessage.SEVERITY_ERROR, text.getString("registrationAction.userCreation.password"), null));
		}

		return "";
	}

	public String getRegistrationTemplate(String userId, String planNo, String title, String name) {
		String bodyMessage = text.getString("registrationAction.login.confirmationEmailSal") + " " + title + " " + name
				+ "," + "\n" + "\n" + text.getString("registrationAction.login.confirmationLine1") + "\n" + "\n"
				+ text.getString("registrationAction.login.confirmationLine2")
				+ text.getString("registrationAction.login.confirmationLine4") + "\n" + "\n"
				+ text.getString("registrationAction.login.confirmationLine3") + "\n" + "\n"
				+ text.getString("registrationAction.login.confirmationusername") + " " + userId + "\n"
				+ text.getString("registrationAction.login.confirmationPlanNo") + " " + planNo + "\n" + "\n"
				+ text.getString("registrationAction.login.confirmationEmailLine3") + "\n" + "\n"
				+ AppSettingURL.ACTIVATION_URL.concat(new StringBuilder(
						String.valueOf(registrationCustomerDTO.getAppUrl().concat(registrationCustomerDTO.getToken())))
								.append("\n").append("\n")
								.append(text.getString("registrationAction.login.confirmationEmailThanks")).append("\n")
								.append("\n").append("\n")
								.append(text.getString("registrationAction.login.confirmationEmailRegards"))
								.append("\n").append(text.getString("registrationAction.login.confirmationEmailName"))
								.append("\n").append(text.getString("registrationAction.login.confirmationEmailInfo1"))
								.append("\n").append(text.getString("registrationAction.login.confirmationEmailInfo2"))
								.toString());

		return bodyMessage;
	}

	public RegistrationTrackBL getRegistrationTrackBL() {
		return registrationTrackBL;
	}

	public void setRegistrationTrackBL(RegistrationTrackBL registrationTrackBL) {
		this.registrationTrackBL = registrationTrackBL;
	}

	public RegistrationTrackDTO getRegistrationTrackDTO() {
		return registrationTrackDTO;
	}

	public void setRegistrationTrackDTO(RegistrationTrackDTO registrationTrackDTO) {
		this.registrationTrackDTO = registrationTrackDTO;
	}

	public String getClientIPTemp() {
		return clientIPTemp;
	}

	public void setClientIPTemp(String clientIPTemp) {
		this.clientIPTemp = clientIPTemp;
	}

	public Map<String, String> getIdentification() {
		return identification;
	}

	public void setIdentification(Map<String, String> identification) {
		this.identification = identification;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public Boolean getLoginFormVisibility() {
		return loginFormVisibility;
	}

	public void setLoginFormVisibility(Boolean loginFormVisibility) {
		this.loginFormVisibility = loginFormVisibility;
	}

	public Boolean getRegisterFormVisibility() {
		return registerFormVisibility;
	}

	public void setRegisterFormVisibility(Boolean registerFormVisibility) {
		this.registerFormVisibility = registerFormVisibility;
	}

	public RegistrationCustomerDTO getRegistrationCustomerDTO() {
		return registrationCustomerDTO;
	}

	public void setRegistrationCustomerDTO(RegistrationCustomerDTO registrationCustomerDTO) {
		this.registrationCustomerDTO = registrationCustomerDTO;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Boolean getLoginActiveSelection() {
		return loginActiveSelection;
	}

	public void setLoginActiveSelection(Boolean loginActiveSelection) {
		this.loginActiveSelection = loginActiveSelection;
	}

	public Boolean getRegistrationActiveSelection() {
		return registrationActiveSelection;
	}

	public void setRegistrationActiveSelection(Boolean registrationActiveSelection) {
		this.registrationActiveSelection = registrationActiveSelection;
	}

	public CusRegistrationBL getCusRegistrationBL() {
		return cusRegistrationBL;
	}

	public void setCusRegistrationBL(CusRegistrationBL cusRegistrationBL) {
		this.cusRegistrationBL = cusRegistrationBL;
	}

	public boolean isSubmitButton() {
		return submitButton;
	}

	public void setSubmitButton(boolean submitButton) {
		this.submitButton = submitButton;
	}

	public UIComponent getComponent() {
		return component;
	}

	public void setComponent(UIComponent component) {
		this.component = component;
	}

	public RegistrationCustomerBL getRegistrationCustomerBL() {
		return registrationCustomerBL;
	}

	public void setRegistrationCustomerBL(RegistrationCustomerBL registrationCustomerBL) {
		this.registrationCustomerBL = registrationCustomerBL;
	}

	public Map<String, String> getSecurityImage() {
		return securityImage;
	}

	public void setSecurityImage(Map<String, String> securityImage) {
		this.securityImage = securityImage;
	}

	public List<CpSecurityImgMaster> getImageList() {
		return imageList;
	}

	public void setImageList(List<CpSecurityImgMaster> imageList) {
		this.imageList = imageList;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public Map<String, String> getSecretQuestions() {
		return secretQuestions;
	}

	public void setSecretQuestions(Map<String, String> secretQuestions) {
		this.secretQuestions = secretQuestions;
	}

	public void setForgetPasswordBL(ForgetPasswordBLImpl forgetPasswordBL) {
		this.forgetPasswordBL = forgetPasswordBL;
	}

	public List<PasswordRulesDTO> getRulesList() {
		return rulesList;
	}

	public void setRulesList(List<PasswordRulesDTO> rulesList) {
		this.rulesList = rulesList;
	}

	public String getBodyMsg() {
		return bodyMsg;
	}

	public void setBodyMsg(String bodyMsg) {
		this.bodyMsg = bodyMsg;
	}

	public PasswordRulesDTO getPasswordRulesDTO() {
		return passwordRulesDTO;
	}

	public void setPasswordRulesDTO(PasswordRulesDTO passwordRulesDTO) {
		this.passwordRulesDTO = passwordRulesDTO;
	}

	public PasswordRules getPasswordRules() {
		return passwordRules;
	}

	public void setPasswordRules(PasswordRules passwordRules) {
		this.passwordRules = passwordRules;
	}

	public void setAdminImpl(AdminBLImpl adminImpl) {
		this.adminImpl = adminImpl;
	}

	public String getEventImageName() {
		return eventImageName;
	}

	public void setEventImageName(String eventImageName) {
		this.eventImageName = eventImageName;
	}

	public String getTermAndCondition() {
		return termAndCondition;
	}

	public void setTermAndCondition(String termAndCondition) {
		this.termAndCondition = termAndCondition;
	}

	public boolean isRenderCropFlag() {
		return renderCropFlag;
	}

	public void setRenderCropFlag(boolean renderCropFlag) {
		this.renderCropFlag = renderCropFlag;
	}

	public List<MasterListDTO> getMasterList() {
		return masterList;
	}

	public void setMasterList(List<MasterListDTO> masterList) {
		this.masterList = masterList;
	}

	public String getPasswordruleToolTip() {
		return passwordruleToolTip;
	}

	public void setPasswordruleToolTip(String passwordruleToolTip) {
		this.passwordruleToolTip = passwordruleToolTip;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMaskedEmail() {
		return maskedEmail;
	}

	public void setMaskedEmail(String maskedEmail) {
		this.maskedEmail = maskedEmail;
	}

	public String getMaskedMobileNo() {
		return maskedMobileNo;
	}

	public void setMaskedMobileNo(String maskedMobileNo) {
		this.maskedMobileNo = maskedMobileNo;
	}

	public ApplicationMailer getAppMailer() {
		return appMailer;
	}

	public void setAppMailer(ApplicationMailer appMailer) {
		this.appMailer = appMailer;
	}

	public boolean isRenderSucessMessage() {
		return renderSucessMessage;
	}

	public void setRenderSucessMessage(boolean renderSucessMessage) {
		this.renderSucessMessage = renderSucessMessage;
	}

	public String getCroppedImagePath() {
		return croppedImagePath;
	}

	public void setCroppedImagePath(String croppedImagePath) {
		this.croppedImagePath = croppedImagePath;
	}

	public CroppedImage getCroppedImage() {
		return croppedImage;
	}

	public void setCroppedImage(CroppedImage croppedImage) {
		this.croppedImage = croppedImage;
	}

	public boolean isRenderCropPanel() {
		return renderCropPanel;
	}

	public void setRenderCropPanel(boolean renderCropPanel) {
		this.renderCropPanel = renderCropPanel;
	}

	public String getNewImageName() {
		return newImageName;
	}

	public void setNewImageName(String newImageName) {
		this.newImageName = newImageName;
	}

	public boolean isErrFlag() {
		return errFlag;
	}

	public void setErrFlag(boolean errFlag) {
		this.errFlag = errFlag;
	}

	public List<ResetSecurityAnswerDTO> getQuestions() {
		return questions;
	}

	public void setQuestions(List<ResetSecurityAnswerDTO> questions) {
		this.questions = questions;
	}

}
