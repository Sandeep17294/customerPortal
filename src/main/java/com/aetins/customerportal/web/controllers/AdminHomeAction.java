package com.aetins.customerportal.web.controllers;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.faces.application.FacesMessage;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aetins.customerportal.core.enums.ROLES;
import com.aetins.customerportal.core.events.registration.OnRegistrationCompleteEvent;
import com.aetins.customerportal.core.faceletstaglib.SecurityLibrary;
import com.aetins.customerportal.core.primefaces.view.GuestPreferences;
import com.aetins.customerportal.core.services.IMailService;
import com.aetins.customerportal.web.audittrails.service.RegistrationAuditTrailService;
import com.aetins.customerportal.web.audittrails.service.UserSessionAuditTrailsService;
import com.aetins.customerportal.web.common.Constants;
import com.aetins.customerportal.web.dao.CustomerLoginDAO;
import com.aetins.customerportal.web.dao.RolesDao;
import com.aetins.customerportal.web.dto.CpCustomerDetailDTO;
import com.aetins.customerportal.web.dto.CpLoginAuditTrailsDTO;
import com.aetins.customerportal.web.dto.CpOtpSettingsDTO;
import com.aetins.customerportal.web.dto.CpQuestionnaireDTO;
import com.aetins.customerportal.web.dto.CpQuestionsDetailDTO;
import com.aetins.customerportal.web.dto.CpRegistartionAuditTrailsDTO;
import com.aetins.customerportal.web.dto.CpServerSettingDTO;
import com.aetins.customerportal.web.dto.CpServiceTypeDTO;
import com.aetins.customerportal.web.dto.CpSessionSummaryDTO;
import com.aetins.customerportal.web.dto.CpTermAndConditionDTO;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.dto.ListItem;
import com.aetins.customerportal.web.dto.MasterListDTO;
import com.aetins.customerportal.web.dto.PasswordRulesDTO;
import com.aetins.customerportal.web.dto.RegistrationTrackDTO;
import com.aetins.customerportal.web.dto.SecurityImagesDTO;
import com.aetins.customerportal.web.dto.UserDTO;
import com.aetins.customerportal.web.entity.CpConfiguration;
import com.aetins.customerportal.web.entity.CpListBoxAnswers;
import com.aetins.customerportal.web.entity.CpLoginAuditTrails;
import com.aetins.customerportal.web.entity.CpRegLock;
import com.aetins.customerportal.web.entity.CpRegistrationAuditTrail;
import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.entity.VerificationToken;
import com.aetins.customerportal.web.service.AdminBL;
import com.aetins.customerportal.web.service.CpConfigurationBL;
import com.aetins.customerportal.web.service.CpRegLockBL;
import com.aetins.customerportal.web.service.CustomerDetailsBL;
import com.aetins.customerportal.web.service.CustomerPortalServices;
import com.aetins.customerportal.web.utils.AppSettingURL;
import com.aetins.customerportal.web.utils.ApplicationMailer;
import com.aetins.customerportal.web.utils.BSLUtils;

@Controller
@Scope("session")
public class AdminHomeAction extends BaseAction {
	private static final Logger logger = Logger.getLogger(AdminHomeAction.class);
	private String vPhpSessionId;
	
	@Autowired
	AdminBL adminImpl;
	
    @Autowired
	ApplicationMailer appMailer;
	
    @Autowired
	private IMailService mailService;
    
	@Autowired
	CustomerDetailsBL customerDetailsBLImpl;
	
	private List<UserDTO> userDetailsList;
	private List<UserDTO> userDetailsListfilter;
	private List<CpServiceTypeDTO> cCEmailList;
	private List<CpServiceTypeDTO> ccUpdateEmailList;
	private List<CpUserInfoDTO> userLists;
	private UserDTO selecteduserlist = new UserDTO();
	private RegistrationTrackDTO selectregisterlist = new RegistrationTrackDTO();
	private CpCustomerDetailDTO custDetails;
	private List<CpCustomerDetailDTO> custDetailsList;
	private List<CpSessionSummaryDTO> sessionLists;
	private List<CpSessionSummaryDTO> filteresessionLists;
	private List<PasswordRulesDTO> rulesList;
	private PasswordRulesDTO passwordRulesDTO;
	private List<CpQuestionsDetailDTO> questionList;
	private CpQuestionsDetailDTO cpQuestionsDetailDTO;
	private SecurityImagesDTO securityImagesDTO;
	private List<SecurityImagesDTO> securityImages;
	private List<SecurityImagesDTO> SecurityImagesDTOEdit;
	private List<SecurityImagesDTO> securityImagesref;
	
	private boolean fileUpload;
	private String success;
	private String successUpload;
	String fileUploadImage;
	String fileaUploadImageName;
	private String errorUpload;
	private boolean successImageUpload;
	private boolean successMessage;
	private boolean errorMessage = false;
	private CpSessionSummaryDTO cpSessionSummaryDTO;
	private boolean addNewQuestion = true;
	private boolean addccEmail = false;
	private CpQuestionsDetailDTO addedQuestion = new CpQuestionsDetailDTO();
	private boolean autoRenewalPopup = false;
	SecurityImagesDTO dtos;
	private String applicationDownType;
	private Date effectiveFrom;
	private Date effectTill;
	private Date startTime;
	private Date endTime;
	private int id;
	private CpServerSettingDTO cpServerSettingDTO = new CpServerSettingDTO();
	private List<CpServerSettingDTO> cpServerSettingDTOList;
	private int index;
	private Set<Integer> changedImageIndexs = new HashSet<>();
	private String bodyMes;
	//RequestContext requestContext;
	private List<UserDTO> filteredUserDetailsList;
	private List<RegistrationTrackDTO> filterRegistrationLists;
	private List<RegistrationTrackDTO> registrationTrackList = new ArrayList<>();
	private LinkedHashMap<String, String> tableName;
	private String tableData;
	private String screenType;
	CpServiceTypeDTO cpServiceTypeDTO = new CpServiceTypeDTO();
	private List<String> ccMail = new ArrayList<>();
	private LinkedHashMap<String, String> screenName;
	private String serviceType;
	private boolean mobile;
	private boolean email;
	private List<CpOtpSettingsDTO> otpRules = new ArrayList<>();
	private CpOtpSettingsDTO cpOtpSettingdtos;
	private Map<String, String> otpTypes;
	private boolean required;
	private boolean mandatory;
	private List<CpTermAndConditionDTO> termsAndCondition = new ArrayList<>();
	private boolean addTermsCondition = false;
	private CpTermAndConditionDTO newTerms = new CpTermAndConditionDTO();
	
	private String serviceName;
	private String domain;
	private String questionNameEng;
	private String questionNameArb;
	private String answerType;
	private boolean addNewQuestionnaire = true;
	private boolean submitButton = false;
	private CpQuestionnaireDTO questionnaire = new CpQuestionnaireDTO();
	private List<CpQuestionnaireDTO> questionnaireList = new ArrayList<>();

	
	private List<CpListBoxAnswers> listBoxAnswers = new ArrayList<>();
	private List<CpListBoxAnswers> selectedAnswers = new ArrayList<>();
	private Map<String, String> serviceTypes = Constants.SERVICE_TYPES;
	private boolean listBox = false;
	private String answerCode;
	private String answer;
	private String appSettingKey;
	private boolean appOTPflag = false;
	private boolean authflag = false;	
	
	//Business user creation
	private String busUserName;
	private String busTitle;
	private String busDepartment;
	private String busEmail;
	private String busDisplayName;
	private Map<String, String> busDeptMap;
	private List<ListItem> masterList;
	private List<ListItem> titlist;
	
	@Autowired
	CpConfigurationBL cpConfigurationBL;
	
	@Autowired
	private CustomerLoginDAO customerDao;
	
	@Autowired
	private RolesDao roleDao;
	
	List<CpConfiguration> cpConfiguration = new ArrayList<CpConfiguration>();
	
	private String applicationdisplayname;
	
	@Autowired
	GuestPreferences guest;
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	@Autowired
	private RegistrationAuditTrailService registrationAuditService;
	
	@Autowired
	CustomerPortalServices customerPortalServices;
	
	public AdminHomeAction() {
		
	}

	//@PostConstruct
	public void init() {		
		try {
			passwordRulesDTO = new PasswordRulesDTO();
			successMessage = false;
			successImageUpload = false;
			sessionLists = new ArrayList<CpSessionSummaryDTO>();
			rulesList = new ArrayList<PasswordRulesDTO>();
			questionList = new ArrayList<CpQuestionsDetailDTO>();
			securityImages = new ArrayList<>();
			registrationTrackList = new ArrayList<>();
			userDetailsList = new ArrayList<>();
			cCEmailList = new ArrayList<>();
			ccUpdateEmailList = new ArrayList<>();
			screenName = new LinkedHashMap<String, String>();
			if(SecurityLibrary.getFullLoggedInUser().getVgroup().equalsIgnoreCase("A")) {
				applicationdisplayname=SecurityLibrary.getFullLoggedInUser().getVprefName();
			}
			guest.fetchtheme();
			masterList = customerPortalServices.getidlist("EN", "DEPT_CODE", "", "");
			titlist=customerPortalServices.gettitle("", "TITLE", "", "");
			
			/*busDeptMap = new HashMap<String,String>();
			for(MasterListDTO master : masterList){
				
				busDeptMap.put(master.getDesc1(), master.getCode());
			}*/
			
			
		    fetchcpConfiguration();
		    getAllSecurityQuesList();
		    //getAllDownTimeList();
		    
			logger.info("Listing Successful" + custDetailsList);
		} catch (Exception e) {
			logger.error("Error Occured in Listing" + e.getMessage());
		}		
	}

	
	
	@Autowired
	CpRegLockBL cpRegLockBL;
	
	List<CpRegLock> listingfailure = new ArrayList<CpRegLock>();
	private List<CpRegLock> listingfailurefilter;
	
	public String policyfailurefetch() {
		listingfailure = null;
		listingfailure = new ArrayList<CpRegLock>();
		listingfailure = cpRegLockBL.fetchtable();
		return "/admin/registrationfailurepolicy?faces-redirect=true";
	}
	
	
	public void updatefailure(CpRegLock cpRegLock) {
		if(cpRegLock.getIdenno()!=null) {
			boolean status = false;
			cpRegLock.setNoofattempts(0);
			status = cpRegLockBL.updatettable(cpRegLock);
			if(status == true) {
				listingfailure = null;
				listingfailure = new ArrayList<CpRegLock>();
				listingfailure = cpRegLockBL.fetchtable();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
						"Staus has been changed. Use can register in portal.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
						"Data processing failed. Please try again later.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		}else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
					"Please Change the Status.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
	}
	
	
	public List<CpRegLock> getListingfailure() {
		return listingfailure;
	}

	public void setListingfailure(List<CpRegLock> listingfailure) {
		this.listingfailure = listingfailure;
	}

	public List<CpRegLock> getListingfailurefilter() {
		return listingfailurefilter;
	}

	public void setListingfailurefilter(List<CpRegLock> listingfailurefilter) {
		this.listingfailurefilter = listingfailurefilter;
	}

	
 	
 @Autowired
 RegistrationAuditTrailService registrationAuditTrailService;
 
 private List<CpRegistrationAuditTrail> cpRegistrationAuditTrail= new ArrayList<CpRegistrationAuditTrail>();
 private List<CpRegistartionAuditTrailsDTO> cpRegistartionAuditTrailsDTO= new ArrayList<CpRegistartionAuditTrailsDTO>();
 private CpRegistartionAuditTrailsDTO filteraduitlist;
	
 public String registeraudittrail() {
	 cpRegistrationAuditTrail = null;
	 cpRegistrationAuditTrail = new ArrayList<CpRegistrationAuditTrail>(); 
	 cpRegistartionAuditTrailsDTO = null;
	 cpRegistartionAuditTrailsDTO = new ArrayList<CpRegistartionAuditTrailsDTO>(); 
	 cpRegistrationAuditTrail = registrationAuditTrailService.getAllRegistrationAuditTrails();
	  if(cpRegistrationAuditTrail.size()>0) {
		  CpRegistartionAuditTrailsDTO list;
		  for(int i=0;i<cpRegistrationAuditTrail.size();i++) {
			  list = new CpRegistartionAuditTrailsDTO();
			  list.setUsername(cpRegistrationAuditTrail.get(i).getUsername());
			  list.setPolicyNo(cpRegistrationAuditTrail.get(i).getPolicyNo());
			  list.setRegistrationStatus(cpRegistrationAuditTrail.get(i).getRegistrationStatus());
			  list.setRemarks(cpRegistrationAuditTrail.get(i).getRemarks());
			  list.setRegistrationDate(cpRegistrationAuditTrail.get(i).getRegistrationDate());
			  list.setActivationDate(cpRegistrationAuditTrail.get(i).getActivationDate());
			  cpRegistartionAuditTrailsDTO.add(list);
		  }
	  }
	  return "/admin/adminregisteraudittrail?faces-redirect=true";
 }
 
 @Autowired
 UserSessionAuditTrailsService userSessionAuditTrailsService;
 private List<CpLoginAuditTrails> cpLoginAuditTrails;
 CpLoginAuditTrailsDTO listing;
 private List<CpLoginAuditTrailsDTO> cpLoginAuditTrailsDTO;
 
 public String getloginaduit() {	 
	 cpLoginAuditTrails = null;
	 cpLoginAuditTrails = new ArrayList<CpLoginAuditTrails>();
	 cpLoginAuditTrailsDTO = null;
	 cpLoginAuditTrailsDTO= new ArrayList<CpLoginAuditTrailsDTO>();
	 cpLoginAuditTrails = userSessionAuditTrailsService.getLoginAuditTrails();
	 if(cpLoginAuditTrails.size()>0) {
		 for(int i=0;i<cpLoginAuditTrails.size();i++) {
			 listing=new CpLoginAuditTrailsDTO();
			 listing.setUsername(cpLoginAuditTrails.get(i).getUsername());
			 listing.setLoggedInTime(cpLoginAuditTrails.get(i).getLoggedInTime());
			 listing.setLogoutTime(cpLoginAuditTrails.get(i).getLogoutTime());
			 listing.setUserLocked(cpLoginAuditTrails.get(i).isUserLocked());
			 listing.setUnsuccessfullAttempts(cpLoginAuditTrails.get(i).getUnsuccessfullAttempts());
			 listing.setNoOfUnsuccessfullAttemptsExceed(cpLoginAuditTrails.get(i).getNoOfUnsuccessfullAttemptsExceed());
			 listing.setUserlockedtime(cpLoginAuditTrails.get(i).getUserLockedTime());
			 cpLoginAuditTrailsDTO.add(listing);
		 }
	 }
	 return "/admin/adminlogintrails?faces-redirect=true";
 }
	

	public String otptypes() {
		serviceType=null;
		otpTypes=null;
		setMobile(false);
		setEmail(false);
		otpTypes = new LinkedHashMap<String, String>();
		otpTypes.put("SWITCHING", "SWITCHING");
		otpTypes.put("REDIRECTION", "REDIRECTION");
		otpTypes.put("REINSTATEMENT", "REINSTATEMENT");
		otpTypes.put("UPDATE INFORMATION", "UPDATE INFORMATION");
		otpTypes.put("CONTRIBUTION HOLIDAY", "CONTRIBUTION HOLIDAY");
		otpTypes.put("CONTRIBUTION ALTERATION", "CONTRIBUTION ALTERATION");
		otpTypes.put("REDIRECTION SWITCHING", "REDIRECTIONANDSWITCHING");
		otpTypes.put("PROTECTION BENIFIT", "PROTECTION BENIFIT");
		otpTypes.put("INCREASE BASIC SUM COVERED", "INCREASE BASIC SUM COVERED");
		otpTypes.put("INCREASE RIDER SUM COVERED", "INCREASE RIDER SUM COVERED");
		otpTypes.put("DECREASE BASIC SUM COVERED", "DECREASE BASIC SUM COVERED");
		otpTypes.put("DECREASE RIDER SUM COVERED", "DECREASE RIDER SUM COVERED");
		otpTypes.put("ADDITION SUPPLEMENTARY RIDER", "ADDITION SUPPLEMENTARY RIDER");
		otpTypes.put("DELETION SUPPLEMENTARY RIDER", "DELETION SUPPLEMENTARY RIDER");
		otpTypes.put("INCREASE REGULAR CONTRIBUTION", "INCREASE REGULAR CONTRIBUTION");
		otpTypes.put("DECREASE REGULAR CONTRIBUTION", "DECREASE REGULAR CONTRIBUTION");
		otpTypes.put("PARTIAL WITHDRAWAL", "PARTIAL WITHDRAWAL");
		return "/admin/adminotpsettings?faces-redirect=true";
	}
	
	public String servicetypes() {
		serviceType=null; 
		otpTypes=null;
		termsAndCondition = null;
		otpTypes = new LinkedHashMap<String, String>();
		otpTypes.put("SWITCHING", "SWITCHING");
		otpTypes.put("REDIRECTION", "REDIRECTION");
		otpTypes.put("REINSTATEMENT", "REINSTATEMENT");
		otpTypes.put("UPDATE INFORMATION", "UPDATE INFORMATION");
		otpTypes.put("CONTRIBUTION HOLIDAY", "CONTRIBUTION HOLIDAY");
		otpTypes.put("CONTRIBUTION ALTERATION", "CONTRIBUTION ALTERATION");
		otpTypes.put("REDIRECTION SWITCHING", "REDIRECTIONANDSWITCHING");
		otpTypes.put("PROTECTION BENIFIT", "PROTECTION BENIFIT");
		otpTypes.put("INCREASE BASIC SUM COVERED", "INCREASE BASIC SUM COVERED");
		otpTypes.put("INCREASE RIDER SUM COVERED", "INCREASE RIDER SUM COVERED");
		otpTypes.put("DECREASE BASIC SUM COVERED", "DECREASE BASIC SUM COVERED");
		otpTypes.put("DECREASE RIDER SUM COVERED", "DECREASE RIDER SUM COVERED");
		otpTypes.put("ADDITION SUPPLEMENTARY RIDER", "ADDITION SUPPLEMENTARY RIDER");
		otpTypes.put("DELETION SUPPLEMENTARY RIDER", "DELETION SUPPLEMENTARY RIDER");
		otpTypes.put("INCREASE REGULAR CONTRIBUTION", "INCREASE REGULAR CONTRIBUTION");
		otpTypes.put("DECREASE REGULAR CONTRIBUTION", "DECREASE REGULAR CONTRIBUTION");
		otpTypes.put("PARTIAL WITHDRAWAL", "PARTIAL WITHDRAWAL");
		
		return "/admin/adminserviceterms?faces-redirect=true";
	}
	
	public void fetchcpConfiguration() {
		cpConfiguration=cpConfigurationBL.fetchcontent();
		if(cpConfiguration.size()>0) {
			for(int i=0;i<cpConfiguration.size();i++) {
	
				if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.Mail.Host")) {
					passwordRulesDTO.setvSmtpHost(cpConfiguration.get(i).getValue());
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.Mail.Port")) {
				    passwordRulesDTO.setvSmtpPort(cpConfiguration.get(i).getValue());
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.Mail.User")) {
				    passwordRulesDTO.setvSmtpUser(cpConfiguration.get(i).getValue());
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.Mail.Password")) {
					passwordRulesDTO.setvSmtpPassword(cpConfiguration.get(i).getValue());
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.Mail.FromMail")) {
					passwordRulesDTO.setvFromEmail(cpConfiguration.get(i).getValue());
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.Mail.CsdMail")) {
					passwordRulesDTO.setvCsdEmail(cpConfiguration.get(i).getValue());
				}
					
				else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.ManageAcount.ActivationPeriod")) {
					String s=cpConfiguration.get(i).getValue();
					int s1=Integer.parseInt(s);
					passwordRulesDTO.setnActivationPeriod(s1);
				} else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.ManageAcount.DormantPeriod")){
					String v=cpConfiguration.get(i).getValue();
					int v1=Integer.parseInt(v);
					passwordRulesDTO.setnDormantPeriod(v1);
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.ManageAcount.PasswordAge")) {
					String c=cpConfiguration.get(i).getValue();
					int c1=Integer.parseInt(c);
					passwordRulesDTO.setnPasswordAge(c1);
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.ManageAcount.PasswordSameAsUser")) {
					passwordRulesDTO.setvPasswordSameasUser(cpConfiguration.get(i).getValue());
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.ManageAcount.PasswordHistory")) {
					String b=cpConfiguration.get(i).getValue();
					int b1=Integer.parseInt(b);
					passwordRulesDTO.setnPasswordHis(b1);
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.ManageAcount.PasswordChangeRequired")) {
					passwordRulesDTO.setvChangePassReq(cpConfiguration.get(i).getValue());
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.ManageAcount.AccountLockout")){
					String n=cpConfiguration.get(i).getValue();
					int n1=Integer.parseInt(n);
					passwordRulesDTO.setnAccntLockout(n1);
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.ManageAcount.ReleaseLockout")) {
					passwordRulesDTO.setvReleaseAccntLock(cpConfiguration.get(i).getValue());
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.ManageAcount.TmeOutReleaseLockout")) {
					String m=cpConfiguration.get(i).getValue();
					int m1=Integer.parseInt(m);
					passwordRulesDTO.setnTimeReleaseAccntLock(m1);
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.ManageAcount.Captcha")) {
					String z=cpConfiguration.get(i).getValue();
					int z1=Integer.parseInt(z);
					passwordRulesDTO.setnRequiredCaptcha(z1);
				}
				
				else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.Password.MinLength")) {
					String q=cpConfiguration.get(i).getValue();
					int q1=Integer.parseInt(q);
					passwordRulesDTO.setnPasswordLength(q1);
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.Password.MinAplha")) {
					String w=cpConfiguration.get(i).getValue();
					int w1=Integer.parseInt(w);
					passwordRulesDTO.setnMinAlphabetAllow(w1);
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.Password.MinNumbers")) {
					String e=cpConfiguration.get(i).getValue();
					int e1=Integer.parseInt(e);
					passwordRulesDTO.setnMinNoAllow(e1);
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.Password.AllowSpecialCharacter")) {
					passwordRulesDTO.setvSpecialCharAllow(cpConfiguration.get(i).getValue());
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.Password.MinSpecialCharacter")) {
					String r=cpConfiguration.get(i).getValue();
					int r1=Integer.parseInt(r);
					passwordRulesDTO.setnMinSpecialAllow(r1);
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.Password.LowerCaseAllowed")) {
					String t=cpConfiguration.get(i).getValue();
					int t1=Integer.parseInt(t);
					passwordRulesDTO.setnMinLowerAllow(t1);
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.Password.UpperCaseAllowed")) {
					String y=cpConfiguration.get(i).getValue();
					int y1=Integer.parseInt(y);
					passwordRulesDTO.setnMinUpperAllow(y1);
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.Password.Prefix")) {
					passwordRulesDTO.setvCompPrefix(cpConfiguration.get(i).getValue());
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.ManageAcount.ques.count")) {
					String yz=cpConfiguration.get(i).getValue();
					int yz1=Integer.parseInt(yz);
					passwordRulesDTO.setnQuestionCount(yz1);
				}
				
				else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.TermsCondition.English")) {
					passwordRulesDTO.setvTncEng(cpConfiguration.get(i).getValue());
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.TermsCondition.Arabic")) {
					passwordRulesDTO.setvTncArb(cpConfiguration.get(i).getValue());
				}
			}
		}
	}
	
	
	
	public void mailserversaveupdate() {		
		boolean status;
		if(cpConfiguration.size()>0) {
			for(int i=0;i<cpConfiguration.size();i++) {
				if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.Mail.Host")) {
					cpConfiguration.get(i).setValue(passwordRulesDTO.getvSmtpHost());
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.Mail.Port")) {
					cpConfiguration.get(i).setValue(passwordRulesDTO.getvSmtpPort());
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.Mail.User")) {
					cpConfiguration.get(i).setValue(passwordRulesDTO.getvSmtpUser());
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.Mail.Password")) {
					cpConfiguration.get(i).setValue(passwordRulesDTO.getvSmtpPassword());
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.Mail.FromMail")) {
					cpConfiguration.get(i).setValue(passwordRulesDTO.getvFromEmail());
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.Mail.CsdMail")) {
					cpConfiguration.get(i).setValue(passwordRulesDTO.getvCsdEmail());
				}
			}	
			status=cpConfigurationBL.updatedata(cpConfiguration);
			if(status=true) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
						"Data Processed Successfully.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Data Processing failed. Kindly try agian.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		} 	
	}
		
	public void manageaccountsaveupdate() {
		boolean status;
		if(cpConfiguration.size()>0) {
			for(int i=0;i<cpConfiguration.size();i++) {
				if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.ManageAcount.ActivationPeriod")) {
					int s= passwordRulesDTO.getnActivationPeriod();
					String s1=Integer.toString(s);
					cpConfiguration.get(i).setValue(s1);
				} else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.ManageAcount.DormantPeriod")){
					int v= passwordRulesDTO.getnDormantPeriod();
					String v1=Integer.toString(v);
					cpConfiguration.get(i).setValue(v1);
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.ManageAcount.PasswordAge")) {
					int c= passwordRulesDTO.getnPasswordAge();
					String c1=Integer.toString(c);
					cpConfiguration.get(i).setValue(c1);
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.ManageAcount.PasswordSameAsUser")) {
					cpConfiguration.get(i).setValue(passwordRulesDTO.getvPasswordSameasUser());
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.ManageAcount.PasswordHistory")) {
					int b= passwordRulesDTO.getnPasswordHis();
					String b1=Integer.toString(b);
					cpConfiguration.get(i).setValue(b1);
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.ManageAcount.PasswordChangeRequired")) {
					cpConfiguration.get(i).setValue(passwordRulesDTO.getvChangePassReq());
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.ManageAcount.AccountLockout")){
					int n=passwordRulesDTO.getnAccntLockout();
					String n1=Integer.toString(n);
					cpConfiguration.get(i).setValue(n1);
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.ManageAcount.ReleaseLockout")) {
					cpConfiguration.get(i).setValue(passwordRulesDTO.getvReleaseAccntLock());
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.ManageAcount.TmeOutReleaseLockout")) {
					int m=passwordRulesDTO.getnTimeReleaseAccntLock();
					String m1=Integer.toString(m);
					cpConfiguration.get(i).setValue(m1);
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.ManageAcount.Captcha")) {
					int z=passwordRulesDTO.getnRequiredCaptcha();
					String z1=Integer.toString(z);
					cpConfiguration.get(i).setValue(z1);
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.ManageAcount.ques.count")) {
					int zzz=passwordRulesDTO.getnQuestionCount();
					String zz1=Integer.toString(zzz);
					cpConfiguration.get(i).setValue(zz1);
				}
			}
			status=cpConfigurationBL.updatedata(cpConfiguration);
			if(status=true) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
						"Data Processed Successfully.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Data Processing failed. Kindly try agian.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		}	
	}
		
	public void managepasswordsaveupdate() {
		boolean status;
		if(cpConfiguration.size()>0) {
			for(int i=0;i<cpConfiguration.size();i++) {
				if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.Password.MinLength")) {
					int q = passwordRulesDTO.getnPasswordLength();
					String q1=Integer.toString(q);
					cpConfiguration.get(i).setValue(q1);
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.Password.MinAplha")) {
					int w = passwordRulesDTO.getnMinAlphabetAllow();
					String w1= Integer.toString(w);
					cpConfiguration.get(i).setValue(w1);
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.Password.MinNumbers")) {
					int e = passwordRulesDTO.getnMinNoAllow();
					String e1= Integer.toString(e);
					cpConfiguration.get(i).setValue(e1);
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.Password.AllowSpecialCharacter")) {
					cpConfiguration.get(i).setValue(passwordRulesDTO.getvSpecialCharAllow());
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.Password.MinSpecialCharacter")) {
					int r=passwordRulesDTO.getnMinSpecialAllow();
					String r1= Integer.toString(r);
					cpConfiguration.get(i).setValue(r1);
				} else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.Password.LowerCaseAllowed")) {
					int t = passwordRulesDTO.getnMinLowerAllow();
					String t1= Integer.toString(t);
					cpConfiguration.get(i).setValue(t1);
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.Password.UpperCaseAllowed")) {
					int y = passwordRulesDTO.getnMinUpperAllow();
					String y1 = Integer.toString(y);
					cpConfiguration.get(i).setValue(y1);
				}else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.Password.Prefix")) {
					cpConfiguration.get(i).setValue(passwordRulesDTO.getvCompPrefix());
				}
			}
			status=cpConfigurationBL.updatedata(cpConfiguration);
			if(status=true) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
						"Data Processed Successfully.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Data Processing failed. Kindly try agian.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		}
	}
	
	
	public void managetermscondtionsaveupdate() {
	 boolean status;
		if(cpConfiguration.size()>0) {
			for(int i=0;i<cpConfiguration.size();i++) {
				if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.TermsCondition.English")) {
					cpConfiguration.get(i).setValue(passwordRulesDTO.getvTncEng());
				} else if(cpConfiguration.get(i).getKey().equalsIgnoreCase("Portal.TermsCondition.Arabic")) {
					cpConfiguration.get(i).setValue(passwordRulesDTO.getvTncArb());
				}
			}
			status=cpConfigurationBL.updatedata(cpConfiguration);
			if(status=true) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
						"Data Processed Successfully.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			} else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Data Processing failed. Kindly try again.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		}
	}

	

	public void btnDeleteQuestion(CpQuestionsDetailDTO questionDto) {
		if ((questionList != null) && (questionList.size() > 0)) {
			System.out.println(questionDto.getQuesId());
			questionList.remove(questionDto);
			boolean bool = adminImpl.deleteQuestion(questionDto);
			if(bool)
			  logger.info("Question Id: " + questionDto.getQuesId() +"Successfully deleted");
		}
	}
	
	public void appSettingmethod() {
		appOTPflag = false;
		authflag = false;
		if (appSettingKey.equalsIgnoreCase("OTP")) {
			appOTPflag = true;
		} else if (appSettingKey.equalsIgnoreCase("Oauth2")) {
			authflag = true;
		}

	}
	
	public void updateUserStatus(UserDTO userDto) {
		CpUserInfoDTO cpDto = new CpUserInfoDTO();
		cpDto.setNid(userDto.getUserId());
		cpDto.setVactive(userDto.getStatus());
		cpDto.setNcustRefNo(userDto.getCustRefNo());

		cpDto.setVemail(userDto.getEmailId());
		cpDto.setVuserName(userDto.getUserName());
		if (userDto.getStatus().equalsIgnoreCase("active")) {
			cpDto.setVactive("A");
		} else {
			cpDto.setVactive("I");
		}
		boolean status = adminImpl.updateUserStatus(cpDto);
		if (status) {	
			String to = userDto.getEmailId();
			String subject = null;
			String body = null;
			if (cpDto.getVactive().equalsIgnoreCase("a")) {
				subject="Activation Message";
				body="Your Account is Successfully Activated.";
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
						"User Status Successfully Changed to Active");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			} else if (cpDto.getVactive().equalsIgnoreCase("i")) {
				subject="De-Activation Message";
				body="Your Account is Deactivated. Please contact Al Rajhi.";
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
						"User Status Successfully Changed to In-Active");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
			try {
				mailService.sendMail(to, subject, body);
				//appMailer.sendMail(to, subject, body);
			} catch (Exception e) {
				logger.error("Error Occured while updating User Status" + e.getMessage());
			}
			logger.info("User Update Status Successful" + cpDto);
		}
	}
	
	public void updateUserLockStatus(UserDTO userDto) {
		
		CpUserInfoDTO cpDto = new CpUserInfoDTO();
		cpDto.setNid(userDto.getUserId());
		cpDto.setVactive(userDto.getStatus());
		cpDto.setNcustRefNo(userDto.getCustRefNo());

		cpDto.setVlocked(userDto.getLocked());
		cpDto.setVemail(userDto.getEmailId());
		cpDto.setVuserName(userDto.getUserName());
		if (userDto.getLocked().equalsIgnoreCase("no")) {
			cpDto.setVlocked("N");
		} else {
			cpDto.setVlocked("Y");
		}
		boolean status = adminImpl.updateUserLockStatus(cpDto);
		if (status) {
			String to = userDto.getEmailId();
			String subject = null;
			String body = null;
			if (cpDto.getVlocked().equalsIgnoreCase("y")) {
				subject="DeActivation Message";
				body="Your Account is Locked";
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
						"User Lock Status Successfully Activated");
				PrimeFaces.current().dialog().showMessageDynamic(message); 							   
			} else if (cpDto.getVlocked().equalsIgnoreCase("n")) {
				subject="Activation Message";
				body="Your Account is Unlocked . please Login with old Password or Use forget Password Option.";
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
						"User Lock Status Successfully De-Activated");
				PrimeFaces.current().dialog().showMessageDynamic(message);				
			}
			try {
				mailService.sendMail(to, subject, body);
				
				//appMailer.sendMail(to, subject, body);
			} catch (Exception e) {
				logger.error("Error Occured while updating User Status" + e.getMessage());
			}
			logger.info("User Update Status Successful" + cpDto);
		}
	}

	private String servcicetems;
	
	public void fetchTermsCondition() {
		if (BSLUtils.isNotNull(serviceType)) {
			setRequired(false);
			setMandatory(false);
			System.out.println(serviceType);
			termsAndCondition = null;
			termsAndCondition = new ArrayList<>();		
			termsAndCondition = adminImpl.listTermsCondition(serviceType);
			for(CpTermAndConditionDTO obj:termsAndCondition) {
				if(obj.getSerialNo()==0) {
					termsAndCondition.remove(obj);
				}
			}
			
			if(BSLUtils.isNull(serviceType.equalsIgnoreCase("Select One"))) {
				CpTermAndConditionDTO termCon = new CpTermAndConditionDTO();
				termCon.setPage_name(serviceType);
				termsAndCondition.add(termCon);
			}
			
//			if(BSLUtils.isNotNull(termsAndCondition)&!(serviceType.equals("Select One"))) {
//				CpTermAndConditionDTO termCon = new CpTermAndConditionDTO();
//				termCon.setPage_name(serviceType);
//				termsAndCondition.add(termCon);
//			}
		}
	}
	
	
	
	public String getServcicetems() {
		return servcicetems;
	}

	public void setServcicetems(String servcicetems) {
		this.servcicetems = servcicetems;
	}

	public void btnClear() {
		setAddNewQuestion(false);
		setAddccEmail(false);
		setSubmitButton(false);
		setAddNewQuestionnaire(false);
		System.out.println("Panel disabled :" + addccEmail);
		System.out.println("panel disabled:" + addNewQuestion);
	}
	
	public void clearNewQuestionnaire(){
		System.out.println("entered into clearNewQuestionnaire method");
		setAddNewQuestionnaire(false);
	}
	

	public void addQuestionnaire(){
		setAddNewQuestionnaire(true);
		questionnaire = new CpQuestionnaireDTO();
		questionnaire.setListBox(false);
	}

	public List<CpListBoxAnswers> addNewRow(){
		System.out.println("entered into addNewRow() method");
		CpListBoxAnswers listBoxAnswer = new CpListBoxAnswers();
		if(questionnaire.getListBoxAnswers()!=null){
			questionnaire.getListBoxAnswers().add(listBoxAnswer);			
		}else {
			questionnaire.setListBoxAnswers(new ArrayList<CpListBoxAnswers>());
			questionnaire.getListBoxAnswers().add(listBoxAnswer);
		}
		return questionnaire.getListBoxAnswers();
	}
	
	public void removeRecord(String answerCode){
		System.out.println("entered into removeRecord() method");
		for(int i=0; i<questionnaire.getListBoxAnswers().size(); i++){
			if(questionnaire.getListBoxAnswers().get(i).getAnswerCode().equals(answerCode)){
				questionnaire.getListBoxAnswers().remove(questionnaire.getListBoxAnswers().get(i));
			}
		}		
	}
	
	public void renderListBox() {
		if (questionnaire.getAnswerType().equals("L")) {
			questionnaire.setListBox(true);
		} else {
			questionnaire.setListBox(false);
		}
	}
	
	public void saveQuestionnaire(){
		boolean status = adminImpl.saveQuestionnaire(questionnaireList);
		if (status) {
			questionnaireList.clear();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
					"Data Processed Successfully.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
					"Data Processing failed. Kindly try again.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
	}
	

	public void upload(FileUploadEvent event) {
		index = 0;
		successImageUpload = true;
		successUpload = "";
		System.out.println("reading fileName: " + event.getFile().getFileName());
		if (event.getFile().getSize() < 256000L) {
			String SAVE_PATH = AppSettingURL.IMAGE_PATH + "resources/images/";

			String fileUploadImage = (String) event.getComponent().getAttributes().get("fileupload");
			String fileaUploadImageName = (String) event.getComponent().getAttributes().get("fileuploadImage");
			String path = event.getFile().getFileName();

			String imageType = path.substring(path.lastIndexOf(".") + 1);
			SecurityImagesDTOEdit = new ArrayList();
			if (securityImagesref != null) {
				for (int i = 0; i < securityImagesref.size(); i++) {
					SecurityImagesDTO selectedImges = (SecurityImagesDTO) securityImagesref.get(i);
					if ((selectedImges.getImgPath().equals(fileUploadImage))
							&& (selectedImges.getImageName().equals(fileaUploadImageName))) {
						index = (i + 1);
						changedImageIndexs.add(Integer.valueOf(index));
						SecurityImagesDTO selectedImgesEdit = new SecurityImagesDTO();
						selectedImgesEdit.setDescription(event.getFile().getFileName());
						selectedImgesEdit.setImgType(imageType);
						String tmpFileName = event.getFile().getFileName();
						String[] tmpFile = tmpFileName.split("\\.", -1);
						selectedImgesEdit.setImgPath("/images/" + tmpFile[0]);
						selectedImgesEdit.setNid(selectedImges.getNid());
						selectedImgesEdit.setImageName(selectedImges.getImageName());
						SecurityImagesDTOEdit.add(selectedImgesEdit);
					} else {
						SecurityImagesDTOEdit.add(selectedImges);
					}
				}
			}
			securityImages = null;
			securityImages = SecurityImagesDTOEdit;
			securityImagesref = securityImages;
			fileUpload = true;
			boolean status = true;
			try {
				copyFile(SAVE_PATH, event.getFile().getFileName(), event.getFile().getInputstream());
			} catch (IOException e1) {
				logger.error("Error Occured while uploading Security Image" + e1.getMessage());
			}
			logger.info("Security Image Upload Successful" + SecurityImagesDTOEdit);
		} else {
			successUpload = "Image 1 File Size Exceeds 250 KB, Try with lesser resolution images";
			successImageUpload = true;
		}
	}

	public void copyFile(String filepath, String fileName, InputStream in) {
		try {
			InputStream input = in;
			OutputStream output = new FileOutputStream(new File(filepath, fileName));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error Occured while Copying Image Image" + e.getMessage());
		}
		logger.info("Security Image Copied to path Successful" + SecurityImagesDTOEdit);
	}

	
	public void btnSecurityImagesSubmit() {
		boolean status;
		for (SecurityImagesDTO selectedList : securityImages) {
			status = adminImpl.updateSecurityImages1(selectedList);
		}
		if (changedImageIndexs != null) {
			if (changedImageIndexs.size() > 0) {
				if (status = true) {
					PrimeFaces.current().executeScript("$('#successTab').modal('show');");
					for (Integer forEach : changedImageIndexs) {
						setBodyMes(text.getString("securityImage.image") + " " + forEach.intValue() + " "
								+ text.getString("securityImage.storedSuccess"));
					}
					changedImageIndexs = new HashSet<Integer>();
				}
			}
		}
	}

	public void btnQuestionSubmit() {
		boolean status = adminImpl.updateSecurityQuestion(questionList);
		if (status) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
					"Data Processed Successfully.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
					"Data Processing failed.Kindly try again.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
	}
	
	public void btnCCEmailSubmit() {
		if (cCEmailList.size() > 0) {
			for (CpServiceTypeDTO dto : cCEmailList) {
				if (dto.getnSerialNo() == 0) {
					ccUpdateEmailList.add(dto);
				}
			}
			if (BSLUtils.isNotNull(ccUpdateEmailList)) {
				boolean status = adminImpl.saveCCEmail(ccUpdateEmailList);
				if (status) {
					setCcUpdateEmailList(null);
				}
			}
		}
	}

	
	public void btnSaveCCEmail() {
		cpServiceTypeDTO.setScreenName(screenType);
		cCEmailList.add(cpServiceTypeDTO);
		setAddccEmail(false);
	}

	public void btnDeleteCCMAil(CpServiceTypeDTO serviceTypeDto) {
		if ((cCEmailList != null) && (cCEmailList.size() > 0)) {
			System.out.println(serviceTypeDto.getnSerialNo());
			cCEmailList.remove(serviceTypeDto);
			boolean bool = adminImpl.deleteCCMail(serviceTypeDto);
		}
	}

	public void btnSaveQuestions() {
		questionList.add(addedQuestion);
		setAddNewQuestion(false);
	}
	
	public void btnSaveQuestionnaire(){
		questionnaire.setServiceName(serviceName);
		if(questionnaire.getAnswerType().equals("L")){
			questionnaire.setListBox(true);
		}
		questionnaireList.add(questionnaire);
		setSubmitButton(false);
		setAddNewQuestionnaire(false);
	}

	public String disableQuestionDetails() {
		setAddNewQuestion(false);
		return "false";
	}

	public String addQuestionDetails() {
		setAutoRenewalPopup(false);
		setAddNewQuestion(true);
		setAddNewQuestionnaire(true);
		setAddccEmail(true);
		setSubmitButton(true);
		addedQuestion = new CpQuestionsDetailDTO();
		questionnaire = new CpQuestionnaireDTO();
		cpServiceTypeDTO = new CpServiceTypeDTO();
		return "";
	}

	public void clearQuestionValues() {
		addedQuestion = new CpQuestionsDetailDTO();
		questionnaire = new CpQuestionnaireDTO();
		cpServiceTypeDTO = new CpServiceTypeDTO();
	}
	
	public void clearTermsCondition() {		
		newTerms = new CpTermAndConditionDTO();
	}

	public void insertDownTime() {
		try {
			if ((cpServerSettingDTOList == null) || (cpServerSettingDTOList.isEmpty())
					|| (cpServerSettingDTOList.size() < 1)) {
				cpServerSettingDTO.setvApplicationDownType(applicationDownType);
				cpServerSettingDTO.setvEffectiveFrom(effectiveFrom);
				cpServerSettingDTO.setvEffectTill(effectTill);
				cpServerSettingDTO.setdStartTime(startTime);
				cpServerSettingDTO.setdEndTime(endTime);
				cpServerSettingDTO.setStatus(downtstatus);
				boolean save = adminImpl.setDownTime(cpServerSettingDTO);
				if (!save) {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
							"Please be informed that data processing failed. Kindly try again.");
					PrimeFaces.current().dialog().showMessageDynamic(message);
					
				} else {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
							"Data Processed Successfully.");
					PrimeFaces.current().dialog().showMessageDynamic(message); 
				}
			} else {
				cpServerSettingDTO.setnId(id);
				cpServerSettingDTO.setvApplicationDownType(applicationDownType);
				cpServerSettingDTO.setvEffectiveFrom(effectiveFrom);
				cpServerSettingDTO.setvEffectTill(effectTill);
				cpServerSettingDTO.setdStartTime(startTime);
				cpServerSettingDTO.setdEndTime(endTime);
				cpServerSettingDTO.setStatus(downtstatus);
				boolean save = adminImpl.updateDownTime(cpServerSettingDTO);
				if (!save) {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
							"Please be informed that data processing failed. Kindly try again.");
					PrimeFaces.current().dialog().showMessageDynamic(message);
				}
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
						"Data Processed Successfully.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		} catch (Exception e) {
			logger.error("Error Occured while inserting DownTime" + e.getMessage());
			System.out.println(" e.printStackTrace()" + e.getMessage());
		}
		logger.info("Down Time Insertion Successful" + cpServerSettingDTO);
	}

	public void searchTableName() {

		tableName = new LinkedHashMap<String, String>();
		tableName.put("Select", "");
		tableName.put("cp_user_info", "cp_user_info");
		tableName.put("cp_customer_detail", "cp_customer_detail");
		tableName.put("cp_sessionSummary", "cp_sessionsummary");
		tableName.put("cp_registration_Track", "cp_registration_track");
		tableName.put("cp_feedback", "cp_feedback");
		tableName.put("cp_feedback_reply", "cp_feedback_reply");

	}
	
	public void searchScreenName() {
		screenName = new LinkedHashMap();

		screenName.put("Registration", "REGISTRATION");
		screenName.put("Login", "LOGIN");
		screenName.put("Forget Password", "FORGETPASSWORD");
		screenName.put("Change Password", "CHANGEPASSWORD");
		screenName.put("Unit Statement", "UNITSTATEMENT");
		screenName.put("User Account Status", "USERACCOUNTSTATUS");

		if (BSLUtils.isNotNull(screenType)) {
			System.out.println(screenType);
			cCEmailList = adminImpl.listcCEmail(screenType);
		}
	}

	public void searchServiceType() {
		if (BSLUtils.isNotNull(serviceType)) {
			setMobile(false);
			setEmail(false);
			System.out.println(serviceType);
			otpRules = adminImpl.listOtpSettings(serviceType);
			cpOtpSettingdtos = new CpOtpSettingsDTO();
			if (otpRules.size() > 0 && BSLUtils.isNotNull(otpRules)) {
				Iterator<CpOtpSettingsDTO> itOtpRule = otpRules.iterator();
				while (itOtpRule.hasNext()) {
					cpOtpSettingdtos = itOtpRule.next();
					cpOtpSettingdtos.getSequenceNo();
					cpOtpSettingdtos.getvServiceType();
					if (cpOtpSettingdtos.getvOtpFlagMobile().equalsIgnoreCase("y")) {
						setMobile(true);
					} else {
						setMobile(false);
					}
					if (cpOtpSettingdtos.getvOtpFlagEmail().equalsIgnoreCase("y")) {
						setEmail(true);
					} else {
						setEmail(false);
					}
				}
			}
		}
	}

	
	public void btnDeleteTermsCondition(CpTermAndConditionDTO dto) {
		if (termsAndCondition != null) {
			if (termsAndCondition.size() > 0) {
				System.out.println(dto.getSerialNo());
				boolean deleteStatus;
				termsAndCondition.remove(dto);
				deleteStatus = adminImpl.deleteTermsCondition(dto);
			}
		}
	}

	public void deleteQuestionnaire(CpQuestionnaireDTO questionnaire){
		if(questionnaireList!=null){
			if(questionnaireList.size()>0){
				questionnaireList.remove(questionnaire);
			}
		}
	}
	
	public void btnTermsUpdate() {
		try {
			boolean status = adminImpl.updateTermsCondition(termsAndCondition);
			if (status == true) {				
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
						"Data Processed Successfully.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			} else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
						"Data Processing Failed.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		} catch (Exception e) {
			logger.error("Error Occured while updating Mail Server Setting" + e.getMessage());
		}
		logger.info("Mail Server Setting Updated Successfully Updated Successfully" + passwordRulesDTO);
	}
	
	public void btnSaveTerms() {
		if (isRequired())
			newTerms.setRequired(true);
		else
			newTerms.setRequired(false);

		if (isMandatory())
			newTerms.setMandatory(true);
		else
			newTerms.setMandatory(false);
		termsAndCondition.add(newTerms);
		setAddTermsCondition(false);
		boolean status = adminImpl.saveTermsCondition(termsAndCondition);
		if (status == true) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
					"Data Processed Successfully.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
					"Data Processing failed. Kindly try again.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
	}

	public void btnClearTerms() {
		setAddTermsCondition(false);
	}

	public String addTermsCondition() {
		setAutoRenewalPopup(false);
		setAddTermsCondition(true);
		return "";
	}

	public void btnOtpSubmit() {
		try {
			if (isMobile())
				cpOtpSettingdtos.setvOtpFlagMobile("Y");
			else
				cpOtpSettingdtos.setvOtpFlagMobile("N");

			if (isEmail())
				cpOtpSettingdtos.setvOtpFlagEmail("Y");
			else
				cpOtpSettingdtos.setvOtpFlagEmail("N");
			if (cpOtpSettingdtos.getSequenceNo() == 0)

				cpOtpSettingdtos.setvServiceType(serviceType);
			boolean status = adminImpl.updateOtpSettings(cpOtpSettingdtos);
			if (status == true) {
				cpOtpSettingdtos = null;
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
						"Data Processed Successfully.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			} else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Data Processing failed.Kindly try again.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		} catch (Exception e) {
			logger.error("Error Occured while updating Mail Server Setting" + e.getMessage());
		}
		logger.info("Mail Server Setting Updated Successfully Updated Successfully" + passwordRulesDTO);
	}

	
	public String getAllTrackSessionList() throws Exception {
		sessionLists = adminImpl.listSessionDetails();
		Iterator<CpSessionSummaryDTO> itsession = sessionLists.iterator();
		while (itsession.hasNext()) {
			cpSessionSummaryDTO = ((CpSessionSummaryDTO) itsession.next());
			if (BSLUtils.isNotNull(cpSessionSummaryDTO.getvLogOff())) {
				if (cpSessionSummaryDTO.getvLogOff().equalsIgnoreCase("y")) {
					cpSessionSummaryDTO.setvLogOff("Yes");
				} else if (cpSessionSummaryDTO.getvLogOff().equalsIgnoreCase("n")) {
					cpSessionSummaryDTO.setvLogOff("No");
				} else {
					cpSessionSummaryDTO.setvLogOff("");
				}
			}
			if (BSLUtils.isNotNull(cpSessionSummaryDTO.getVlastUpdProg())) {
				if (cpSessionSummaryDTO.getVlastUpdProg().equalsIgnoreCase("a")) {
					cpSessionSummaryDTO.setVlastUpdProg("Active");
				} else if (cpSessionSummaryDTO.getVlastUpdProg().equalsIgnoreCase("i")) {
					cpSessionSummaryDTO.setVlastUpdProg("In Active");
				} else {
					cpSessionSummaryDTO.setVlastUpdProg("");
				}
			}
		}
		return "/admin/admintracksessions?faces-redirect=true";
	}

	public void getPasswordRulesList() throws Exception {
		rulesList = adminImpl.listPasswordRules();
		Iterator<PasswordRulesDTO> itPasswordRule = rulesList.iterator();
		while (itPasswordRule.hasNext()) {
			passwordRulesDTO = ((PasswordRulesDTO) itPasswordRule.next());
		}
	}

	public void getAllSecurityQuesList() throws Exception {
		questionList = adminImpl.listSecurityQuestion();
		Iterator<CpQuestionsDetailDTO> itcp = questionList.iterator();

		while (itcp.hasNext()) {
			cpQuestionsDetailDTO = new CpQuestionsDetailDTO();
			cpQuestionsDetailDTO = ((CpQuestionsDetailDTO) itcp.next());
			cpQuestionsDetailDTO.getQuesEN();
			cpQuestionsDetailDTO.getQuesEN();
		}
	}

	public void getAllSecurityImagesList() throws Exception {
		securityImages = adminImpl.listSecurityImages();
		securityImagesref = securityImages;
	}

	private String downtstatus;
	
	public String  getAllDownTimeList() throws Exception {
		cpServerSettingDTOList = null;
		cpServerSettingDTOList = new ArrayList<CpServerSettingDTO>();
		cpServerSettingDTOList = adminImpl.fetchDownTime();
		if ((cpServerSettingDTOList == null) || (cpServerSettingDTOList.isEmpty())
				|| (cpServerSettingDTOList.size() < 1)) {
			//return null;
		}
		id = ((CpServerSettingDTO) cpServerSettingDTOList.get(0)).getnId();
		applicationDownType = ((CpServerSettingDTO) cpServerSettingDTOList.get(0)).getvApplicationDownType();
		effectiveFrom = ((CpServerSettingDTO) cpServerSettingDTOList.get(0)).getvEffectiveFrom();
		startTime = ((CpServerSettingDTO) cpServerSettingDTOList.get(0)).getdStartTime();
		endTime = ((CpServerSettingDTO) cpServerSettingDTOList.get(0)).getdEndTime();
		effectTill = ((CpServerSettingDTO) cpServerSettingDTOList.get(0)).getvEffectTill();
		downtstatus = cpServerSettingDTOList.get(0).getStatus();
		
		return "/admin/admindowntime?faces-redirect=true";
	}

	public String getAllUserList() throws Exception {
		try {
			
			userLists = null;
			userLists = new ArrayList<CpUserInfoDTO>();
			
			userDetailsList = null;
			userDetailsList = new ArrayList<UserDTO>();
			
			userLists = adminImpl.listAllUsers();
			custDetailsList = new ArrayList();
			Iterator<CpUserInfoDTO> it = userLists.iterator();
			while (it.hasNext()) {
				UserDTO userDTO = new UserDTO();
				CpUserInfoDTO cpUserInfoDTO = (CpUserInfoDTO) it.next();

				userDTO.setUserName(cpUserInfoDTO.getVuserName());

				userDTO.setRegistrationDate(cpUserInfoDTO.getRegistrationDate());
				userDTO.setPrefName(cpUserInfoDTO.getVprefName());
				userDTO.setEmailId(cpUserInfoDTO.getVemail());
				userDTO.setActiveFrom(cpUserInfoDTO.getVlastupdDate());
				userDTO.setContactNo(cpUserInfoDTO.getVcontactNo());
				userDTO.setUserType(cpUserInfoDTO.getVgroup());
				userDTO.setStatus(cpUserInfoDTO.getVactive());
				userDTO.setLocked(cpUserInfoDTO.getVlocked());

				if (BSLUtils.isNotNull(userDTO.getStatus())) {
					if (userDTO.getStatus().equalsIgnoreCase("a")) {
						userDTO.setStatus("Active");
					} else if (cpUserInfoDTO.getVactive().equalsIgnoreCase("i")) {
						userDTO.setStatus("In Active");
					} else {
						userDTO.setStatus("");
					}
				}

				if (BSLUtils.isNotNull(userDTO.getLocked())) {
					if (userDTO.getLocked().equalsIgnoreCase("n")) {
						userDTO.setLocked("NO");
					} else if (cpUserInfoDTO.getVlocked().equalsIgnoreCase("y")) {
						userDTO.setLocked("YES");
					} else {
						userDTO.setLocked("");
					}
				}

				if (BSLUtils.isNotNull(userDTO.getUserType())) {
					if (userDTO.getUserType().equalsIgnoreCase("a")) {
						userDTO.setUserType("Admin");
					} else if (userDTO.getUserType().equalsIgnoreCase("b")) {
						userDTO.setUserType("Business User");
					} else if (userDTO.getUserType().equalsIgnoreCase("u")) {
						userDTO.setUserType("User");
					} else {
						userDTO.setUserType("");
					}
				}

				logger.info("Before Entering Customer Details:::::::::");
				custDetailsList = customerDetailsBLImpl.customerDetails(cpUserInfoDTO.getNcustRefNo());
				logger.info("Before Entering Customer Details:::::::::");

				if (custDetailsList.size() != 0) {
					Iterator<CpCustomerDetailDTO> itCustDet = custDetailsList.iterator();
					while (itCustDet.hasNext()) {
						custDetails = ((CpCustomerDetailDTO) itCustDet.next());
					}
					if (cpUserInfoDTO.getNcustRefNo() == custDetails.getNcustRefNo()) {
						userDTO.setIdenNo(custDetails.getNidNo());
						userDTO.setIdenType(idenTypeDesc(custDetails.getVidType()));
					}
				}
				userDetailsList.add(userDTO);
			}
			
			return "/admin/adminuserlist?faces-redirect=true";
			
		} catch (Exception e) {
			logger.error("Error Occured in getAllUserList()  ##########@@@@@@@@@" + e.getMessage());
		}
		
		return "/admin/adminuserlist?faces-redirect=true";
	}
	
	
	public String  getAllRegisterationList() throws Exception {
		registrationTrackList = adminImpl.listRegistrationTrack();
		return "/admin/adminregistrationtracking?faces-redirect=true";
	}
	
	public void businessUserCreation() {
		
		try {
			if(BSLUtils.isNotNull(busUserName)&&BSLUtils.isNotNull(busEmail)) {
				
				logger.info("BUSINESS USER CREATION : username: {"+busUserName+"}, email: {"+busEmail+"}");
				
				CpUserInfo userInfo = new CpUserInfo();
				userInfo.setVuserName(busUserName);
				userInfo.setVemail(busEmail);
				userInfo.setVtitle(busTitle);
				userInfo.setvBusrDept(busDepartment);
				userInfo.setVgroup("B");
				userInfo.setVlocked("N");
				userInfo.setVprefName(userInfo.getVuserName());
				userInfo.setRoles(Arrays.asList(roleDao.findByName(ROLES.ROLE_BUSINESS_USER.name())));
				Random rand = new Random();
				userInfo.setNcustRefNo(Integer.parseInt(String.format("%04d", rand.nextInt(10000))));
				
				
				//Registration token
				final String token = UUID.randomUUID().toString();
				VerificationToken regToken = new VerificationToken(token);
				regToken.setUser(userInfo);
				userInfo.setVerificationToken(regToken);
				
				CpRegistrationAuditTrail registrationTrail = new CpRegistrationAuditTrail();
				// save user registration audit trail
				registrationTrail.setUserType("BUSINESS USER");
				registrationTrail.setCustRefNo(userInfo.getNcustRefNo());
				registrationTrail.setRegistrationStatus("PENDING");
				registrationTrail.setRemarks("Verification sent to registered email");
				registrationTrail.setUsername(userInfo.getVuserName());
				registrationTrail.setRegistrationDate(new java.sql.Date(new Date().getTime()));
				
				//Save user details.
				customerDao.save(userInfo);
				logger.info("Business user creation: "+userInfo+"");
				
				//Save registration audit trails
				registrationAuditService.saveRegistrationAuditTrail(registrationTrail);
				
				eventPublisher.publishEvent(new OnRegistrationCompleteEvent(userInfo,regToken, null, SecurityLibrary.getAppUrl()));
				logger.info("BUSINESS USER REGISTRATION EVENT PUBLISHED");
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message","Business User registration link sent to email successfully");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message","Business user creation failed due to: "+e.getCause().getMessage()+"");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		} 
	}

	public List<CpUserInfoDTO> getUserLists() {
		return userLists;
	}

	public void setUserLists(List<CpUserInfoDTO> userLists) {
		this.userLists = userLists;
	}

	public String getErrorUpload() {
		return errorUpload;
	}

	public void setErrorUpload(String errorUpload) {
		this.errorUpload = errorUpload;
	}

	public boolean isErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(boolean errorMessage) {
		this.errorMessage = errorMessage;
	}


	public List<CpCustomerDetailDTO> getCustDetailsList() {
		return custDetailsList;
	}

	

	public void setCustDetailsList(List<CpCustomerDetailDTO> custDetailsList) {
		this.custDetailsList = custDetailsList;
	}

	public CpCustomerDetailDTO getCustDetails() {
		return custDetails;
	}

	public void setCustDetails(CpCustomerDetailDTO custDetails) {
		this.custDetails = custDetails;
	}

	public List<CpSessionSummaryDTO> getSessionLists() {
		return sessionLists;
	}

	public void setSessionLists(List<CpSessionSummaryDTO> sessionLists) {
		this.sessionLists = sessionLists;
	}

	public String getvPhpSessionId() {
		return vPhpSessionId;
	}

	public void setvPhpSessionId(String vPhpSessionId) {
		this.vPhpSessionId = vPhpSessionId;
	}

	public List<PasswordRulesDTO> getRulesList() {
		return rulesList;
	}

	public void setRulesList(List<PasswordRulesDTO> rulesList) {
		this.rulesList = rulesList;
	}

	public List<UserDTO> getUserDetailsList() {
		return userDetailsList;
	}

	public void setUserDetailsList(List<UserDTO> userDetailsList) {
		this.userDetailsList = userDetailsList;
	}

	public PasswordRulesDTO getPasswordRulesDTO() {
		return passwordRulesDTO;
	}

	public void setPasswordRulesDTO(PasswordRulesDTO passwordRulesDTO) {
		this.passwordRulesDTO = passwordRulesDTO;
	}

	public List<CpQuestionsDetailDTO> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<CpQuestionsDetailDTO> questionList) {
		this.questionList = questionList;
	}

	public CpQuestionsDetailDTO getCpQuestionsDetailDTO() {
		return cpQuestionsDetailDTO;
	}

	public void setCpQuestionsDetailDTO(CpQuestionsDetailDTO cpQuestionsDetailDTO) {
		this.cpQuestionsDetailDTO = cpQuestionsDetailDTO;
	}

	public List<SecurityImagesDTO> getSecurityImages() {
		return securityImages;
	}

	public void setSecurityImages(List<SecurityImagesDTO> securityImages) {
		this.securityImages = securityImages;
	}

	public boolean isFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(boolean fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public boolean isSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(boolean successMessage) {
		this.successMessage = successMessage;
	}

	public SecurityImagesDTO getSecurityImagesDTO() {
		return securityImagesDTO;
	}

	public List<SecurityImagesDTO> getSecurityImagesDTOEdit() {
		return SecurityImagesDTOEdit;
	}

	public List<SecurityImagesDTO> getSecurityImagesref() {
		return securityImagesref;
	}

	public CpSessionSummaryDTO getCpSessionSummaryDTO() {
		return cpSessionSummaryDTO;
	}

	public boolean isAddNewQuestion() {
		return addNewQuestion;
	}

	public void setSecurityImagesDTO(SecurityImagesDTO securityImagesDTO) {
		this.securityImagesDTO = securityImagesDTO;
	}

	public void setSecurityImagesDTOEdit(List<SecurityImagesDTO> securityImagesDTOEdit) {
		SecurityImagesDTOEdit = securityImagesDTOEdit;
	}

	public void setSecurityImagesref(List<SecurityImagesDTO> securityImagesref) {
		this.securityImagesref = securityImagesref;
	}

	public void setCpSessionSummaryDTO(CpSessionSummaryDTO cpSessionSummaryDTO) {
		this.cpSessionSummaryDTO = cpSessionSummaryDTO;
	}

	public void setAddNewQuestion(boolean addNewQuestion) {
		this.addNewQuestion = addNewQuestion;
	}

	public CpQuestionsDetailDTO getAddedQuestion() {
		return addedQuestion;
	}

	public boolean isAutoRenewalPopup() {
		return autoRenewalPopup;
	}

	public void setAddedQuestion(CpQuestionsDetailDTO addedQuestion) {
		this.addedQuestion = addedQuestion;
	}

	public void setAutoRenewalPopup(boolean autoRenewalPopup) {
		this.autoRenewalPopup = autoRenewalPopup;
	}

	public String getApplicationDownType() {
		return applicationDownType;
	}

	public void setApplicationDownType(String applicationDownType) {
		this.applicationDownType = applicationDownType;
	}

	public Date getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(Date effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public Date getEffectTill() {
		return effectTill;
	}

	public void setEffectTill(Date effectTill) {
		this.effectTill = effectTill;
	}

	public CpServerSettingDTO getCpServerSettingDTO() {
		return cpServerSettingDTO;
	}

	public void setCpServerSettingDTO(CpServerSettingDTO cpServerSettingDTO) {
		this.cpServerSettingDTO = cpServerSettingDTO;
	}

	public List<CpServerSettingDTO> getCpServerSettingDTOList() {
		return cpServerSettingDTOList;
	}

	public void setCpServerSettingDTOList(List<CpServerSettingDTO> cpServerSettingDTOList) {
		this.cpServerSettingDTOList = cpServerSettingDTOList;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSuccessUpload() {
		return successUpload;
	}

	public void setSuccessUpload(String successUpload) {
		this.successUpload = successUpload;
	}

	public boolean isSuccessImageUpload() {
		return successImageUpload;
	}

	public void setSuccessImageUpload(boolean successImageUpload) {
		this.successImageUpload = successImageUpload;
	}

	public ApplicationMailer getAppMailer() {
		return appMailer;
	}

	public void setAppMailer(ApplicationMailer appMailer) {
		this.appMailer = appMailer;
	}

	public Set getChangedImageIndexs() {
		return changedImageIndexs;
	}

	public void setChangedImageIndexs(Set changedImageIndexs) {
		this.changedImageIndexs = changedImageIndexs;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getBodyMes() {
		return bodyMes;
	}

	public void setBodyMes(String bodyMes) {
		this.bodyMes = bodyMes;
	}

	public List<UserDTO> getFilteredUserDetailsList() {
		return filteredUserDetailsList;
	}

	public void setFilteredUserDetailsList(List<UserDTO> filteredUserDetailsList) {
		this.filteredUserDetailsList = filteredUserDetailsList;
	}

	public List<CpSessionSummaryDTO> getFilteresessionLists() {
		return filteresessionLists;
	}

	public void setFilteresessionLists(List<CpSessionSummaryDTO> filteresessionLists) {
		this.filteresessionLists = filteresessionLists;
	}

	public List<RegistrationTrackDTO> getRegistrationTrackList() {
		return registrationTrackList;
	}

	public void setRegistrationTrackList(List<RegistrationTrackDTO> registrationTrackList) {
		this.registrationTrackList = registrationTrackList;
	}

	public List<RegistrationTrackDTO> getFilterRegistrationLists() {
		return filterRegistrationLists;
	}

	public void setFilterRegistrationLists(List<RegistrationTrackDTO> filterRegistrationLists) {
		this.filterRegistrationLists = filterRegistrationLists;
	}

	public LinkedHashMap<String, String> getTableName() {
		return tableName;
	}

	public void setTableName(LinkedHashMap<String, String> tableName) {
		this.tableName = tableName;
	}

	public String getTableData() {
		return tableData;
	}

	public void setTableData(String tableData) {
		this.tableData = tableData;
	}

	public List<CpServiceTypeDTO> getcCEmailList() {
		return cCEmailList;
	}

	public String getScreenType() {
		return screenType;
	}

	public LinkedHashMap<String, String> getScreenName() {
		return screenName;
	}

	public void setcCEmailList(List<CpServiceTypeDTO> cCEmailList) {
		this.cCEmailList = cCEmailList;
	}

	public void setScreenType(String screenType) {
		this.screenType = screenType;
	}

	public void setScreenName(LinkedHashMap<String, String> screenName) {
		this.screenName = screenName;
	}

	public boolean isAddccEmail() {
		return addccEmail;
	}

	public void setAddccEmail(boolean addccEmail) {
		this.addccEmail = addccEmail;
	}

	public CpServiceTypeDTO getCpServiceTypeDTO() {
		return cpServiceTypeDTO;
	}

	public void setCpServiceTypeDTO(CpServiceTypeDTO cpServiceTypeDTO) {
		this.cpServiceTypeDTO = cpServiceTypeDTO;
	}

	public List<CpServiceTypeDTO> getCcUpdateEmailList() {
		return ccUpdateEmailList;
	}

	public void setCcUpdateEmailList(List<CpServiceTypeDTO> ccUpdateEmailList) {
		this.ccUpdateEmailList = ccUpdateEmailList;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public boolean isMobile() {
		return mobile;
	}

	public void setMobile(boolean mobile) {
		this.mobile = mobile;
	}

	public boolean isEmail() {
		return email;
	}

	public void setEmail(boolean email) {
		this.email = email;
	}

	public Map<String, String> getOtpTypes() {
		return otpTypes;
	}

	public void setOtpTypes(Map<String, String> otpTypes) {
		this.otpTypes = otpTypes;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	public List<CpTermAndConditionDTO> getTermsAndCondition() {
		return termsAndCondition;
	}

	public void setTermsAndCondition(List<CpTermAndConditionDTO> termsAndCondition) {
		this.termsAndCondition = termsAndCondition;
	}

	public boolean isAddTermsCondition() {
		return addTermsCondition;
	}

	public void setAddTermsCondition(boolean addTermsCondition) {
		this.addTermsCondition = addTermsCondition;
	}

	public CpTermAndConditionDTO getNewTerms() {
		return newTerms;
	}

	public void setNewTerms(CpTermAndConditionDTO newTerms) {
		this.newTerms = newTerms;
	}

	public CpQuestionnaireDTO getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(CpQuestionnaireDTO questionnaire) {
		this.questionnaire = questionnaire;
	}

	public List<CpQuestionnaireDTO> getQuestionnaireList() {
		return questionnaireList;
	}

	public void setQuestionnaireList(List<CpQuestionnaireDTO> questionnaireList) {
		this.questionnaireList = questionnaireList;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getQuestionNameEng() {
		return questionNameEng;
	}

	public void setQuestionNameEng(String questionNameEng) {
		this.questionNameEng = questionNameEng;
	}

	public String getQuestionNameArb() {
		return questionNameArb;
	}

	public void setQuestionNameArb(String questionNameArb) {
		this.questionNameArb = questionNameArb;
	}

	public boolean isAddNewQuestionnaire() {
		return addNewQuestionnaire;
	}

	public void setAddNewQuestionnaire(boolean addNewQuestionnaire) {
		this.addNewQuestionnaire = addNewQuestionnaire;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getAnswerType() {
		return answerType;
	}

	public void setAnswerType(String answerType) {
		this.answerType = answerType;
	}

	public boolean isSubmitButton() {
		return submitButton;
	}

	public void setSubmitButton(boolean submitButton) {
		this.submitButton = submitButton;
	}

	public List<CpListBoxAnswers> getListBoxAnswers() {
		return listBoxAnswers;
	}

	public void setListBoxAnswers(List<CpListBoxAnswers> listBoxAnswers) {
		this.listBoxAnswers = listBoxAnswers;
	}

	public List<CpListBoxAnswers> getSelectedAnswers() {
		return selectedAnswers;
	}

	public void setSelectedAnswers(List<CpListBoxAnswers> selectedAnswers) {
		this.selectedAnswers = selectedAnswers;
	}

	public Map<String, String> getServiceTypes() {
		return serviceTypes;
	}

	public void setServiceTypes(Map<String, String> serviceTypes) {
		this.serviceTypes = serviceTypes;
	}

	public boolean isListBox() {
		return listBox;
	}

	public void setListBox(boolean listBox) {
		this.listBox = listBox;
	}

	public String getAnswerCode() {
		return answerCode;
	}

	public void setAnswerCode(String answerCode) {
		this.answerCode = answerCode;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getAppSettingKey() {
		return appSettingKey;
	}

	public void setAppSettingKey(String appSettingKey) {
		this.appSettingKey = appSettingKey;
	}

	public boolean isAppOTPflag() {
		return appOTPflag;
	}

	public void setAppOTPflag(boolean appOTPflag) {
		this.appOTPflag = appOTPflag;
	}

	public boolean isAuthflag() {
		return authflag;
	}

	public void setAuthflag(boolean authflag) {
		this.authflag = authflag;
	}

	public RegistrationTrackDTO getSelectregisterlist() {
		return selectregisterlist;
	}

	public void setSelectregisterlist(RegistrationTrackDTO selectregisterlist) {
		this.selectregisterlist = selectregisterlist;
	}

	public UserDTO getSelecteduserlist() {
		return selecteduserlist;
	}

	public void setSelecteduserlist(UserDTO selecteduserlist) {
		this.selecteduserlist = selecteduserlist;
	}

	public String getApplicationdisplayname() {
		return applicationdisplayname;
	}

	public void setApplicationdisplayname(String applicationdisplayname) {
		this.applicationdisplayname = applicationdisplayname;
	}

	public List<CpRegistrationAuditTrail> getCpRegistrationAuditTrail() {
		return cpRegistrationAuditTrail;
	}

	public void setCpRegistrationAuditTrail(List<CpRegistrationAuditTrail> cpRegistrationAuditTrail) {
		this.cpRegistrationAuditTrail = cpRegistrationAuditTrail;
	}

	public List<CpRegistartionAuditTrailsDTO> getCpRegistartionAuditTrailsDTO() {
		return cpRegistartionAuditTrailsDTO;
	}

	public void setCpRegistartionAuditTrailsDTO(List<CpRegistartionAuditTrailsDTO> cpRegistartionAuditTrailsDTO) {
		this.cpRegistartionAuditTrailsDTO = cpRegistartionAuditTrailsDTO;
	}

	public CpRegistartionAuditTrailsDTO getFilteraduitlist() {
		return filteraduitlist;
	}

	public void setFilteraduitlist(CpRegistartionAuditTrailsDTO filteraduitlist) {
		this.filteraduitlist = filteraduitlist;
	}

	public List<CpLoginAuditTrails> getCpLoginAuditTrails() {
		return cpLoginAuditTrails;
	}

	public void setCpLoginAuditTrails(List<CpLoginAuditTrails> cpLoginAuditTrails) {
		this.cpLoginAuditTrails = cpLoginAuditTrails;
	}

	public List<CpLoginAuditTrailsDTO> getCpLoginAuditTrailsDTO() {
		return cpLoginAuditTrailsDTO;
	}

	public void setCpLoginAuditTrailsDTO(List<CpLoginAuditTrailsDTO> cpLoginAuditTrailsDTO) {
		this.cpLoginAuditTrailsDTO = cpLoginAuditTrailsDTO;
	}

	public List<UserDTO> getUserDetailsListfilter() {
		return userDetailsListfilter;
	}

	public void setUserDetailsListfilter(List<UserDTO> userDetailsListfilter) {
		this.userDetailsListfilter = userDetailsListfilter;
	}

	public String getBusUserName() {
		return busUserName;
	}

	public void setBusUserName(String busUserName) {
		this.busUserName = busUserName;
	}

	public String getBusTitle() {
		return busTitle;
	}

	public void setBusTitle(String busTitle) {
		this.busTitle = busTitle;
	}

	public String getBusDepartment() {
		return busDepartment;
	}

	public void setBusDepartment(String busDepartment) {
		this.busDepartment = busDepartment;
	}

	public String getBusEmail() {
		return busEmail;
	}

	public void setBusEmail(String busEmail) {
		this.busEmail = busEmail;
	}

	public String getBusDisplayName() {
		return busDisplayName;
	}

	public void setBusDisplayName(String busDisplayName) {
		this.busDisplayName = busDisplayName;
	}

	

	public List<ListItem> getMasterList() {
		return masterList;
	}

	public void setMasterList(List<ListItem> masterList) {
		this.masterList = masterList;
	}

	public Map<String, String> getBusDeptMap() {
		return busDeptMap;
	}

	public void setBusDeptMap(Map<String, String> busDeptMap) {
		this.busDeptMap = busDeptMap;
	}

	public List<ListItem> getTitlist() {
		return titlist;
	}

	public void setTitlist(List<ListItem> titlist) {
		this.titlist = titlist;
	}

	public String getDowntstatus() {
		return downtstatus;
	}

	public void setDowntstatus(String downtstatus) {
		this.downtstatus = downtstatus;
	}
	
	
	
	

	

}