package com.aetins.customerportal.web.validators;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aetins.customerportal.core.faceletstaglib.SecurityLibrary;
import com.aetins.customerportal.core.services.IMailService;
import com.aetins.customerportal.web.common.Constants;
import com.aetins.customerportal.web.common.LoginSession;
import com.aetins.customerportal.web.controllers.BaseAction;
import com.aetins.customerportal.web.controllers.HomeDetailsAction;
import com.aetins.customerportal.web.controllers.TransactionServiceAction;
import com.aetins.customerportal.web.dao.CpUserInfoDAO;
import com.aetins.customerportal.web.dao.ITransactionDeptDao;
import com.aetins.customerportal.web.dto.CpOtpSettingsDTO;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.dto.FatcaAddCounDTO;
import com.aetins.customerportal.web.dto.FatcaDTO;
import com.aetins.customerportal.web.dto.PasswordRulesDTO;
import com.aetins.customerportal.web.dto.ResetSecurityAnswerDTO;
import com.aetins.customerportal.web.dto.ServiceRequestMasterDTO;
import com.aetins.customerportal.web.dto.UserDTO;
import com.aetins.customerportal.web.entity.CpLogsErros;
import com.aetins.customerportal.web.entity.CpRequestMaster;
import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.service.CpLogsErrosBL;
import com.aetins.customerportal.web.service.FatcaBL;
import com.aetins.customerportal.web.service.RegistrationCustomerService;
import com.aetins.customerportal.web.service.ResetSecurityAnswerBL;
import com.aetins.customerportal.web.service.TransactionBL;
import com.aetins.customerportal.web.service.impl.FatcaBLImpl;
import com.aetins.customerportal.web.utils.AppSettingURL;
import com.aetins.customerportal.web.utils.BSLUtils;
import com.aetins.customerportal.web.utils.FileUtils;
import com.aetins.customerportal.web.utils.OTPGenerater;
import com.aetins.customerportal.web.utils.SMSEmailGenerater;

@Component(value = "otpValidation")
@Scope("session")
public class OtpValidation extends BaseAction {

	private static final Logger logger = Logger.getLogger(OtpValidation.class);

	@Autowired
	TransactionBL transactionBL;
	
	@Autowired
	FatcaBL fatcaBL;
	@Autowired
	ResetSecurityAnswerBL resetSecurityBL;
	
	@Autowired
	LoginSession loginSession;
	
	@Autowired
	SMSEmailGenerater smsEmail;
	
	@Autowired
	TransactionServiceAction transactionServiceAction;
	
	@Autowired
	RegistrationCustomerService customerService;
	
	@Autowired
	CpUserInfoDAO cpUserInfoDAO;
	
	@Autowired
	IMailService mailService;
	
	@Autowired
    ITransactionDeptDao transactionDeptDao;
	
	private String policyNo;
	private String premiumStatus;
	private String planName;
	private String userOTP;
	private String otpGenenater;
	public int noOfAttempts;
	private String titleMsg;
	private String securityQues;
	private String securityAns;
	private String userSecurityAns=null;
	private boolean renderStartTime;
	private boolean renderStopTime;
	private boolean enableResendOTP;
	
	private String bodyMes;
	private PasswordRulesDTO passwordRulesDTO = new PasswordRulesDTO();
	private List<CpOtpSettingsDTO> otpSettingsList = new ArrayList<CpOtpSettingsDTO>();
	
	private String msgDestinations;
	private String to;
	private int OTPTimeLimit;
	private UserDTO userDetails = new UserDTO();
	private List<CpUserInfoDTO> cpUserList = new ArrayList<CpUserInfoDTO>();
	private FatcaDTO fatcaDto;
	
	private List<FatcaAddCounDTO> countryList = new ArrayList<FatcaAddCounDTO>();
	FatcaAddCounDTO fatcaAddCounDTO;
	private boolean displayTin = false;
	private boolean countryPanel = false;
	private boolean termsConditionFatca;
	private OTPGenerater otp = new OTPGenerater();
	private int otptimelimit;
	private int serviceRequestNo;
	List<ResetSecurityAnswerDTO> securityQuestionList = new ArrayList<>();
	ResetSecurityAnswerDTO resetSecurityAnswerDTO = new ResetSecurityAnswerDTO();
	ResetSecurityAnswerDTO resetSecurityAnswerDTO1 = new ResetSecurityAnswerDTO();
	String questionStatus = "A";
	private int wrongAnsCount = 0;
	
	private Map<String, String> countryMap;
	private String bodyMsg;
	private int redirectRequestNo;
	ServiceRequestMasterDTO serviceRequestMasterDTO;
	
	boolean statuscheck1;
	boolean statuscheck2;
	
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		fatcaDto = new FatcaDTO();
		FacesContext context = FacesContext.getCurrentInstance();
		HomeDetailsAction hAction = context.getApplication().evaluateExpressionGet(context, "#{homeDetailsAction}",
				HomeDetailsAction.class);
		policyNo = hAction.getSearchPolicyList().getPlanNumber();
		premiumStatus = hAction.getSearchPolicyList().getPremiumPayStatus();
		planName = hAction.getSearchPolicyList().getProductName();
		OTPTimeLimit = Integer.parseInt(AppSettingURL.Mobile_Timelimit);
		otptimelimit = OTPTimeLimit / 60;
		countryMap = customerService.getMasterLOVs(Constants.NF_COUNTRY, Constants.EN);
		userSecurityAns=null;

	}
	
	@Autowired
	CpLogsErrosBL cpLogsErrosBL;
	
	public void delete() {
		userSecurityAns=null;
		userOTP=null;
		PrimeFaces.current().executeScript("PF('dlg3').hide()");
		List<ServiceRequestMasterDTO> serviceRequestMasterDTO1;		
		Object mode = getSession().getAttribute("TRANSACTION");
		if(mode.equals("REDIRECTIONSWITCHING")) {
			serviceRequestMasterDTO1 = new ArrayList();
			serviceRequestMasterDTO1 = (List<ServiceRequestMasterDTO>) getSession().getAttribute("MASTERDTO_LIST");
		    if(serviceRequestMasterDTO1!=null) {
				CpLogsErros error;
				for(int i=0; i<serviceRequestMasterDTO1.size();i++) {
					error = new CpLogsErros();
					error.setPolicyno(policyNo);
					error.setReqno(serviceRequestMasterDTO1.get(i).getServiceRequestNo());
					error.setUserid(serviceRequestMasterDTO1.get(i).getUserId());
					error.setType(serviceRequestMasterDTO1.get(i).getServiceRequestType());
					error.setException("The transaction was cancelled by the user");
					cpLogsErrosBL.insertexception(error);
				}	
				transactionBL.delete(serviceRequestMasterDTO1);
		    }
		}else {
			serviceRequestMasterDTO = new ServiceRequestMasterDTO();
			serviceRequestMasterDTO = (ServiceRequestMasterDTO) getSession().getAttribute("MASTERDTO");
			 CpLogsErros error = new CpLogsErros();
			 error.setPolicyno(policyNo);
			 error.setReqno(serviceRequestMasterDTO.getServiceRequestNo());
			 error.setUserid(serviceRequestMasterDTO.getUserId());
			 error.setType(serviceRequestMasterDTO.getServiceRequestType());
			 error.setException("The transaction was cancelled by the user");
			 cpLogsErrosBL.insertexception(error);
			 transactionBL.delete(serviceRequestMasterDTO);
		}
	}

	public void delete1() {
		userSecurityAns = null;
		userOTP = null;
		PrimeFaces.current().executeScript("PF('dlg3').hide()");
		List<ServiceRequestMasterDTO> serviceRequestMasterDTO1;
		Object mode = getSession().getAttribute("TRANSACTION");
		if (mode.equals("REDIRECTIONSWITCHING")) {
			serviceRequestMasterDTO1 = new ArrayList();
			serviceRequestMasterDTO1 = (List<ServiceRequestMasterDTO>) getSession().getAttribute("MASTERDTO_LIST");
			if (serviceRequestMasterDTO1 != null) {
				CpLogsErros error;
				for (int i = 0; i < serviceRequestMasterDTO1.size(); i++) {
					error = new CpLogsErros();
					error.setPolicyno(policyNo);
					error.setReqno(serviceRequestMasterDTO1.get(i).getServiceRequestNo());
					error.setUserid(serviceRequestMasterDTO1.get(i).getUserId());
					error.setType(serviceRequestMasterDTO1.get(i).getServiceRequestType());
					error.setException("The transaction was cancelled incorrect secuirty and otp");
					cpLogsErrosBL.insertexception(error);
				}
				transactionBL.delete(serviceRequestMasterDTO1);
			}
		} else {
			serviceRequestMasterDTO = new ServiceRequestMasterDTO();
			serviceRequestMasterDTO = (ServiceRequestMasterDTO) getSession().getAttribute("MASTERDTO");
			CpLogsErros error = new CpLogsErros();
			error.setPolicyno(policyNo);
			error.setReqno(serviceRequestMasterDTO.getServiceRequestNo());
			error.setUserid(serviceRequestMasterDTO.getUserId());
			error.setType(serviceRequestMasterDTO.getServiceRequestType());
			error.setException("The transaction was cancelled incorrect secuirty and otp");
			cpLogsErrosBL.insertexception(error);
			transactionBL.delete(serviceRequestMasterDTO);
		}
	}
	
	private String popmsg;
	
	public void validateOTP() throws Exception {
		logger.info(":::::: Entering inside Validate OTP Method :::::::");
		setEnableResendOTP(false);
		List<ServiceRequestMasterDTO> serviceRequestMasterDTO1;
	    serviceRequestMasterDTO = null;
	    statuscheck1=false;
		logger.info(":::::: Number of Attempt left for OTP :::::::" + noOfAttempts);
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		FacesMessage message = new FacesMessage();
		if (noOfAttempts > 0) {
			if (userOTP.trim().equalsIgnoreCase(otpGenenater) && securityAns.equalsIgnoreCase(userSecurityAns)) {
				setRenderStopTime(true);
				setEnableResendOTP(false);
				Object mod = getSession().getAttribute("TRANSACTION");
				if(mod.equals("REDIRECTIONSWITCHING")) {
					serviceRequestMasterDTO1 = new ArrayList();
					serviceRequestMasterDTO1 = (List<ServiceRequestMasterDTO>) getSession().getAttribute("MASTERDTO_LIST");	
					for (ServiceRequestMasterDTO eachRecord : serviceRequestMasterDTO1) {
						saveValues(eachRecord);
					}
				}else {
					serviceRequestMasterDTO = new ServiceRequestMasterDTO();
					serviceRequestMasterDTO = (ServiceRequestMasterDTO) getSession().getAttribute("MASTERDTO");
					saveValues(serviceRequestMasterDTO);
				}				
				if(statuscheck1==true) {
					statuscheck2=true;
					if (locale.toString().equalsIgnoreCase("ar")) {
						message = new FacesMessage(FacesMessage.SEVERITY_INFO, "رسالة", "تم بدء الخدمة المطلوبة بنجاح");
						popmsg = "تم بدء الخدمة المطلوبة بنجاح";
					} else {
						message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
								"Service Requested Initiated Successfully.");
						popmsg = "Service Requested Initiated Successfully.";
					}
					//PrimeFaces.current().dialog().showMessageDynamic(message);
					PrimeFaces.current().executeScript("PF('dlg3').hide()");
					PrimeFaces.current().executeScript("PF('dlg4').show()");
					clearValues();
				} else {
					if (locale.toString().equalsIgnoreCase("ar")) {
						message = new FacesMessage(FacesMessage.SEVERITY_INFO, "رسالة",
								"فشلت الخدمة المطلوبة ، يرجى المحاولة مرة أخرى لاحقًا أو الاتصال بفريق دعم سلامة");
						popmsg = "فشلت الخدمة المطلوبة ، يرجى المحاولة مرة أخرى لاحقًا أو الاتصال بفريق دعم سلامة";
					} else {
						message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
								"Service Requested Failed.Please try again later or contact Salama Support Team.");
						popmsg = "Service Requested Failed.Please try again later or contact Salama Support Team.";
					}
					//PrimeFaces.current().dialog().showMessageDynamic(message);
					clearValues();
					PrimeFaces.current().executeScript("PF('dlg3').hide()");
					PrimeFaces.current().executeScript("PF('dlg4').show()");
				}
			}

			if (!securityAns.equalsIgnoreCase(userSecurityAns)) {
				wrongAnsCount++;
				generateRandomQuestion();
				if (wrongAnsCount <= 2) {
					message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
							"Security answer is wrong.");
					PrimeFaces.current().dialog().showMessageDynamic(message);
					
				} else {
					message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
							"You exceed the number of attempt for correct security answer.");
					PrimeFaces.current().dialog().showMessageDynamic(message);
				}
			}

			if (!userOTP.trim().equalsIgnoreCase(otpGenenater)) {
				--noOfAttempts;
				int noOfAttemptShow = noOfAttempts + 1;
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"You have" +""+  +noOfAttemptShow+ "" +"no of attempts remaining.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}

		} else {
			
			logger.info("::::: No of attempts left for OTP ::::::" + noOfAttempts);
			delete1();
			if (locale.toString().equalsIgnoreCase("ar")) {
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "رسالة",
						"لم يتم تجاوز عدد المحاولات ، يرجى إعادة المحاولة");
				popmsg = "لم يتم تجاوز عدد المحاولات ، يرجى إعادة المحاولة";
			} else {
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
						"No of Attempts Exceeded please try again the transaction.");
				popmsg = "No of Attempts Exceeded please try again the transaction.";
			}
			//PrimeFaces.current().dialog().showMessageDynamic(message);
			clearValues();
			PrimeFaces.current().executeScript("PF('dlg3').hide()");
			PrimeFaces.current().executeScript("PF('dlg4').show()");
			
//			logger.info("::::: No of attempts left for OTP ::::::" + noOfAttempts);
//			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
//					"No of Attempts Exceeded please click Opt Resend OTP Option");
//			PrimeFaces.current().dialog().showMessageDynamic(message);	
//			setEnableResendOTP(true);
		}

		if (!securityAns.equalsIgnoreCase(userSecurityAns)) {
			userSecurityAns=null;
		}
	}
	
	
	
	public String getPopmsg() {
		return popmsg;
	}



	public void setPopmsg(String popmsg) {
		this.popmsg = popmsg;
	}



	public void saveValues(ServiceRequestMasterDTO serviceRequestMasterDTO) throws Exception {
		// TODO Auto-generated method stub
		String succesMsg = "";
		String transaction = "";
		Date currentDate = new Date();
		serviceRequestMasterDTO.setServiceRequestNo(serviceRequestMasterDTO.getServiceRequestNo());
		serviceRequestMasterDTO.setRequestStatus(Constants.PRINTFORM);
		serviceRequestMasterDTO.setRecentUpdateDate(currentDate);
		String fatcaFlag = (String) getSession().getAttribute("FATCAFLAG");
		serviceRequestMasterDTO.setFatcaFlag(fatcaFlag);
		boolean status = transactionBL.updateDetails(serviceRequestMasterDTO);
		logger.info("::::::  UPDATE STATUS :::::::" + status);
		if (status) {
			String type = serviceRequestMasterDTO.getServiceRequestType().trim();
			logger.info("::::::  SELECTED TRANSACTION TYPE IS  :::::::" + type);
			switch (type) {
		    case "CONTRIBUTION HOLIDAY":
				succesMsg = transactionServiceAction.transContributionHoliday(serviceRequestMasterDTO);		
				break;
		    case "CONTRIBUTION ALTERATION":
				succesMsg = transactionServiceAction.transContributionAlteration(serviceRequestMasterDTO);
				break;
			case "REINSTATEMENT":
				succesMsg = transactionServiceAction.transReinStatement(serviceRequestMasterDTO);
				break;
			case "PROTECTION BENEFIT":
				succesMsg = transactionServiceAction.transProtectionBenifit(serviceRequestMasterDTO);
				break;
		    case "UPDATE INFORMATION":
				succesMsg = transactionServiceAction.transUpdateInfomation(serviceRequestMasterDTO);
				break;
			case "REDIRECTION":
				succesMsg = transactionServiceAction.transServiceRedirectionAndSwitching(serviceRequestMasterDTO);
				break;
			case "SWITCHING":
				succesMsg = transactionServiceAction.transServiceRedirectionAndSwitching(serviceRequestMasterDTO);
				break;
			case "REDIRECTIONANDSWITCHING":
				succesMsg = transactionServiceAction.transServiceRedirectionAndSwitching(serviceRequestMasterDTO);
				break;
			case "CLAIM INTIMATION":
				succesMsg = transactionServiceAction.transerviceclaimintimation(serviceRequestMasterDTO.getServiceRequestNo());
				break;	
			case "PARTIAL WITHDRAWAL":
				succesMsg = transactionServiceAction.transServicepartialwithdraw(serviceRequestMasterDTO);
				break;	
			}
			logger.info("::::: Transaction status for E - service ::::::" + succesMsg);
			serviceRequestMasterDTO.setServiceRequestNo(serviceRequestMasterDTO.getServiceRequestNo());
			serviceRequestMasterDTO.setRequestStatus(Constants.TRANSACTION_SUCCESS);
			serviceRequestMasterDTO.setRecentUpdateDate(new Date());
			serviceRequestNo = serviceRequestMasterDTO.getServiceRequestNo();
			if (succesMsg.equalsIgnoreCase("PASS")) {
				statuscheck1=true;
				transactionBL.updateDetails(serviceRequestMasterDTO);
			}
		}
	}

	

	public void smsGenerator() {
		passwordRulesDTO = transactionBL.getCpSettingDto();
		noOfAttempts = passwordRulesDTO.getnRequiredMobOTP();
		otpGenenater = otp.generateRandomString();
        String custName = (String)getSession().getAttribute("CUSTOMERNAME");
		
		if ((otpSettingsList.get(0).getvOtpFlagEmail() == "Y" || otpSettingsList.get(0).getvOtpFlagEmail().equals("Y")
				|| otpSettingsList.get(0).getvOtpFlagEmail().equalsIgnoreCase("Y"))
				&& (otpSettingsList.get(0).getvOtpFlagMobile() == "Y"
						|| otpSettingsList.get(0).getvOtpFlagMobile().equals("Y")
						|| otpSettingsList.get(0).getvOtpFlagMobile().equalsIgnoreCase("Y"))) {
			//mailService.smsgeneratortransaction(otpGenenater, msgDestinations);
			smsEmail.smsGenerater(otpGenenater, msgDestinations);
			smsEmail.emailForOtpGenerater(to, otpGenenater);

		} else if (otpSettingsList.get(0).getvOtpFlagEmail() == "Y"
				|| otpSettingsList.get(0).getvOtpFlagEmail().equals("Y")
				|| otpSettingsList.get(0).getvOtpFlagEmail().equalsIgnoreCase("Y")) {
			//mailService.smsgeneratortransaction(otpGenenater, msgDestinations);
			smsEmail.emailForOtpGenerater(to, otpGenenater);

		} else {
			String maskMobileNo = smsEmail.maskMobileNumber(msgDestinations);
			maskMobileNo = maskMobileNo.concat("otp is: "+otpGenenater);
			//mailService.smsgeneratortransaction(otpGenenater, msgDestinations);
			      //smsEmail.emailGenerater(to, maskMobileNo);
			smsEmail.smsGenerater(otpGenenater, msgDestinations);
		}
		setRenderStopTime(false);
		setEnableResendOTP(false);
		setRenderStartTime(true);
	}

	public void fetchSecurityQuestion() {
		logger.info("Inside fetchSecurityQuestion Method :::::::");
		securityQuestionList = resetSecurityBL.fetchSecurityQuestion(SecurityLibrary.getFullLoggedInUser().getVuserName(),questionStatus);
		logger.info("Size of Security question list ::::" +securityQuestionList);
		if (securityQuestionList.size() > 0) {

		} else {
			logger.info("Fetching Security question form CP_USER_INFO table ::::::::");
			resetSecurityAnswerDTO.setSecurityQues(cpUserList.get(0).getVquestion1());
			resetSecurityAnswerDTO.setSecurityAns(cpUserList.get(0).getVanswer1());
			resetSecurityAnswerDTO1.setSecurityQues(cpUserList.get(0).getVquestion2());
			resetSecurityAnswerDTO1.setSecurityAns(cpUserList.get(0).getVanswer2());
			securityQuestionList.add(resetSecurityAnswerDTO);
			securityQuestionList.add(resetSecurityAnswerDTO1);
		}
	}

	public void generateRandomQuestion() {

		Random ranNum = new Random();
		int index = ranNum.nextInt(securityQuestionList.size());
		securityQues = securityQuestionList.get(index).getSecurityQues();
		securityAns = securityQuestionList.get(index).getSecurityAns();
	}
	
	private String otpchecking;

	public void btnPrintForm() {
		 boolean stting = false;
		 otpchecking= new String();
		userSecurityAns=null;
		logger.info(":::::Entring inside btnPrintForm Method ::::::::");
	    serviceRequestMasterDTO = (ServiceRequestMasterDTO) getSession()
				.getAttribute("MASTERDTO");
	   // fatcall();
		try {
			if (serviceRequestMasterDTO != null) {
				System.out.println(serviceRequestMasterDTO.getServiceRequestNo());
				logger.info("::::::: Service request No is ::::::" + serviceRequestMasterDTO.getServiceRequestNo());
				otpSettingsList = transactionBL.fetchOtpSettings(serviceRequestMasterDTO.getServiceRequestType());
				logger.info(":::::::Size of OTPSettingList is:::::::::" + otpSettingsList);
				userOTP = null;
				userDetails.setCustRefNo(SecurityLibrary.getFullLoggedInUser().getNcustRefNo());
				cpUserList = transactionBL.getUserDetails(userDetails);
				fetchSecurityQuestion();
				generateRandomQuestion();
				logger.info("::::: Size of UserList is :::::" + cpUserList.size());
				to = cpUserList.get(0).getVemail();
				msgDestinations = cpUserList.get(0).getVcontactNo();
				smsGenerator();
				serviceRequestMasterDTO.setServiceRequestNo(serviceRequestMasterDTO.getServiceRequestNo());
		        serviceRequestMasterDTO.setUserOtp(otpGenenater);
		        stting = transactionBL.saveOTP(serviceRequestMasterDTO);
		        
				String fatcaFlag = (String) getSession().getAttribute("FATCAFLAG");
				if (BSLUtils.isNotNull(fatcaFlag)) {
					if (fatcaFlag.equalsIgnoreCase("Y")) {
						fatcaDto.setServiceRequestNo(serviceRequestMasterDTO);
						fatcaDto.setServiceName(serviceRequestMasterDTO.getServiceRequestType());
						fatcaDto = fatcaBL.saveFatcaDetails(fatcaDto);
						for (FatcaAddCounDTO dto : countryList) {
							String countryName = getValueFromMap(dto.getCountry(), countryMap);
							dto.setCountryName(countryName);
							dto.setSeqNo(fatcaDto);
						}
						boolean status1 = fatcaBL.saveCountryDet(countryList);
					}
					PrimeFaces.current().executeScript("PF('dlg3').show()");
				}
			}
		} catch (Exception e) {
			logger.info(":::::Inside Catch block of btnPrintForm Method ::::::::" + e);
			e.printStackTrace();
		}
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
	
	// Clearing Session for fatca form
	public void clearValues() {
		getSession().setAttribute("MASTERDTO_LIST", null);
	    getSession().setAttribute("MASTERDTO", null);
		setDisplayTin(false);
		fatcaDto.setIsUsPerson(null);
		fatcaDto.setTaxMoreThanOne(null);
		setTermsConditionFatca(false);
	}
	
	public void fatcall() {
		fatcaDto = null;
		fatcaDto = new FatcaDTO();
		countryList = null;
		countryList	= new ArrayList<FatcaAddCounDTO>();
		setDisplayTin(false);
//		fatcaDto.setIsUsPerson(null);
//		fatcaDto.setTaxMoreThanOne(null);
		setTermsConditionFatca(false);
	}


	public void displayTinPanel() {
		setDisplayTin(false);
		setCountryPanel(false);
		if (BSLUtils.isNotNull(fatcaDto.getTaxMoreThanOne())) {
			if (fatcaDto.getTaxMoreThanOne().equalsIgnoreCase("Y")) {
				setCountryPanel(true);
				addFatcaInfo();
			}
		}
		if (BSLUtils.isNotNull(fatcaDto.getIsUsPerson())) {
			if (fatcaDto.getIsUsPerson().equalsIgnoreCase("Y")) {
				setDisplayTin(true);
			}
		}
	}

	
	public String addFatcaInfo() {
		fatcaAddCounDTO = new FatcaAddCounDTO();
		if (countryList != null) {
			fatcaAddCounDTO.setNewRecord(true);
			countryList.add(fatcaAddCounDTO);
		} else {
			fatcaAddCounDTO.setNewRecord(true);
			countryList.add(fatcaAddCounDTO);
		}
		return "true";
	}

	public void checkTime() {
		setRenderStopTime(true);
		setRenderStartTime(false);
		setEnableResendOTP(true);
		if(statuscheck2=true) {
		}else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
					"OTP expired: Please click resend and try again.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
		
	}

	public void closeModal() {
		clearValues();
		setEnableResendOTP(false);
		setRenderStartTime(false);
		setRenderStopTime(true);
		setTitleMsg(text.getString("customer.policydetails.NFAlt.warningheadmsg"));
		setBodyMsg(text.getString("eservices.transaction.cancel"));
		//RequestContext requestContext = RequestContext.getCurrentInstance();
		PrimeFaces.current().executeScript("$('#OTP-popup').modal('hide');");
		PrimeFaces.current().executeScript("$('#successTab').modal('show');");
		
	}

	public void successModal() {
		closeModal();
		//RequestContext requestContext = RequestContext.getCurrentInstance();
		PrimeFaces.current().executeScript("showPdf()");
	}

	public OTPGenerater getOtp() {
		return otp;
	}

	public String getUserOTP() {
		return userOTP;
	}

	public String getOtpGenenater() {
		return otpGenenater;
	}

	public int getNoOfAttempts() {
		return noOfAttempts;
	}

	public String getTitleMsg() {
		return titleMsg;
	}

	public String getSecurityQues() {
		return securityQues;
	}

	public String getSecurityAns() {
		return securityAns;
	}

	public String getUserSecurityAns() {
		return userSecurityAns;
	}

	public boolean isRenderStartTime() {
		return renderStartTime;
	}

	public boolean isRenderStopTime() {
		return renderStopTime;
	}

	public void setOtp(OTPGenerater otp) {
		this.otp = otp;
	}

	public void setUserOTP(String userOTP) {
		this.userOTP = userOTP;
	}

	public void setOtpGenenater(String otpGenenater) {
		this.otpGenenater = otpGenenater;
	}

	public void setNoOfAttempts(int noOfAttempts) {
		this.noOfAttempts = noOfAttempts;
	}

	public void setTitleMsg(String titleMsg) {
		this.titleMsg = titleMsg;
	}

	public void setSecurityQues(String securityQues) {
		this.securityQues = securityQues;
	}

	public void setSecurityAns(String securityAns) {
		this.securityAns = securityAns;
	}

	public void setUserSecurityAns(String userSecurityAns) {
		this.userSecurityAns = userSecurityAns;
	}

	public void setRenderStartTime(boolean renderStartTime) {
		this.renderStartTime = renderStartTime;
	}

	public void setRenderStopTime(boolean renderStopTime) {
		this.renderStopTime = renderStopTime;
	}

	public boolean isEnableResendOTP() {
		return enableResendOTP;
	}

	public void setEnableResendOTP(boolean enableResendOTP) {
		this.enableResendOTP = enableResendOTP;
	}

	public String getBodyMes() {
		return bodyMes;
	}

	public void setBodyMes(String bodyMes) {
		this.bodyMes = bodyMes;
	}

	public TransactionBL getTransactionBL() {
		return transactionBL;
	}

	public TransactionServiceAction getTransactionServiceAction() {
		return transactionServiceAction;
	}

	public PasswordRulesDTO getPasswordRulesDTO() {
		return passwordRulesDTO;
	}

	public List<CpOtpSettingsDTO> getOtpSettingsList() {
		return otpSettingsList;
	}

	public int getOTPTimeLimit() {
		return OTPTimeLimit;
	}

	public void setTransactionBL(TransactionBL transactionBL) {
		this.transactionBL = transactionBL;
	}

	public void setTransactionServiceAction(TransactionServiceAction transactionServiceAction) {
		this.transactionServiceAction = transactionServiceAction;
	}

	public void setPasswordRulesDTO(PasswordRulesDTO passwordRulesDTO) {
		this.passwordRulesDTO = passwordRulesDTO;
	}

	public void setOtpSettingsList(List<CpOtpSettingsDTO> otpSettingsList) {
		this.otpSettingsList = otpSettingsList;
	}

	public void setOTPTimeLimit(int oTPTimeLimit) {
		OTPTimeLimit = oTPTimeLimit;
	}

	public SMSEmailGenerater getSmsEmail() {
		return smsEmail;
	}

	public String getMsgDestinations() {
		return msgDestinations;
	}

	public String getTo() {
		return to;
	}

	public UserDTO getUserDetails() {
		return userDetails;
	}

	public List<CpUserInfoDTO> getCpUserList() {
		return cpUserList;
	}

	public FatcaDTO getFatcaDto() {
		return fatcaDto;
	}

	public List<FatcaAddCounDTO> getCountryList() {
		return countryList;
	}

	public FatcaAddCounDTO getFatcaAddCounDTO() {
		return fatcaAddCounDTO;
	}

	public void setSmsEmail(SMSEmailGenerater smsEmail) {
		this.smsEmail = smsEmail;
	}

	public void setMsgDestinations(String msgDestinations) {
		this.msgDestinations = msgDestinations;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setUserDetails(UserDTO userDetails) {
		this.userDetails = userDetails;
	}

	public void setCpUserList(List<CpUserInfoDTO> cpUserList) {
		this.cpUserList = cpUserList;
	}

	public void setFatcaDto(FatcaDTO fatcaDto) {
		this.fatcaDto = fatcaDto;
	}

	public void setFatcaBL(FatcaBLImpl fatcaBL) {
		this.fatcaBL = fatcaBL;
	}

	public void setCountryList(List<FatcaAddCounDTO> countryList) {
		this.countryList = countryList;
	}

	public void setFatcaAddCounDTO(FatcaAddCounDTO fatcaAddCounDTO) {
		this.fatcaAddCounDTO = fatcaAddCounDTO;
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

	public boolean isTermsConditionFatca() {
		return termsConditionFatca;
	}

	public void setTermsConditionFatca(boolean termsConditionFatca) {
		this.termsConditionFatca = termsConditionFatca;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public String getPremiumStatus() {
		return premiumStatus;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public void setPremiumStatus(String premiumStatus) {
		this.premiumStatus = premiumStatus;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public int getOtptimelimit() {
		return otptimelimit;
	}

	public void setOtptimelimit(int otptimelimit) {
		this.otptimelimit = otptimelimit;
	}

	public Map<String, String> getCountryMap() {
		return countryMap;
	}

	public void setCountryMap(Map<String, String> countryMap) {
		this.countryMap = countryMap;
	}

	public String getBodyMsg() {
		return bodyMsg;
	}

	public void setBodyMsg(String bodyMsg) {
		this.bodyMsg = bodyMsg;
	}

	public ServiceRequestMasterDTO getServiceRequestMasterDTO() {
		return serviceRequestMasterDTO;
	}

	public void setServiceRequestMasterDTO(ServiceRequestMasterDTO serviceRequestMasterDTO) {
		this.serviceRequestMasterDTO = serviceRequestMasterDTO;
	}


	public String getOtpchecking() {
		return otpchecking;
	}


	public void setOtpchecking(String otpchecking) {
		this.otpchecking = otpchecking;
	}
	
	

}