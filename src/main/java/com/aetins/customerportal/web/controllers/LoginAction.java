package com.aetins.customerportal.web.controllers;


import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aetins.customerportal.core.primefaces.view.GuestPreferences;
import com.aetins.customerportal.web.dao.CustomerLoginDAO;
import com.aetins.customerportal.web.dao.SessionSummaryDAO;
import com.aetins.customerportal.web.dto.CpCustomerDetailDTO;
import com.aetins.customerportal.web.dto.CpServerSettingDTO;
import com.aetins.customerportal.web.dto.CpServiceTypeDTO;
import com.aetins.customerportal.web.dto.CpSessionSummaryDTO;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.dto.PasswordRulesDTO;
import com.aetins.customerportal.web.dto.UserDTO;
import com.aetins.customerportal.web.entity.CpServerstatusSetting;
import com.aetins.customerportal.web.entity.CpSessionSummary;
import com.aetins.customerportal.web.entity.CpThemeCss;
import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.service.AdminBL;
import com.aetins.customerportal.web.service.CpSessionSummarybl;
import com.aetins.customerportal.web.service.CpThemeCssBL;
import com.aetins.customerportal.web.service.CustomerLoginBL;
import com.aetins.customerportal.web.service.ForgetPasswordBL;
import com.aetins.customerportal.web.service.MessageQueueBL;
import com.aetins.customerportal.web.service.impl.CustomerPortalServicesImpl;
import com.aetins.customerportal.web.utils.AppSettingURL;
import com.aetins.customerportal.web.utils.ApplicationMailer;
import com.aetins.customerportal.web.utils.BSLUtils;
import com.aetins.customerportal.web.utils.Credential;
import com.aetins.customerportal.web.utils.SMSEmailGenerater;

@Controller
@Scope("session")
public class LoginAction extends BaseAction {
	
	
	@Autowired
	SessionSummaryDAO sessionDao;
	
	@Autowired
	ForgetPasswordBL forgetPasswordBLImpl;
	
	@Autowired
	AdminBL adminImpl;
	
	@Autowired
	CustomerLoginBL loginDetails;
	
	@Autowired
	CpSessionSummarybl cpsessionBl;
	
	@Autowired
	CustomerLoginDAO custDao;

	
	@Autowired
	CustomerPortalServicesImpl customerPortalServices;
	
	@Autowired
	ApplicationMailer appMailer;
	
	@Autowired
	MessageQueueBL messageQueue;
	
	@Autowired
	SMSEmailGenerater smsGenerator;	
	
	CpServerSettingDTO cpServerSettingDTO;
	
	
	CpServerSettingDTO serverSettingList;
	
	
	
	private String userList;
	private String userName;
	private String userPassword;
	
	public int noOfAttempts = 3;
	public int noOfCapchaAttempts = 6;
	public int noOfSessions = 0;
	public String noOfCaptcha;
	public int noOfCaptchaAttempts = 3;
	private int accntTimeRelease;
	private String to;
	private String subject;
	private String body;
	private boolean exceededPassAtempts = false;
	private List<CpServerSettingDTO> downTimeStatus;
	
	private CpServerstatusSetting sysDate;
	private String loginErrorMsg;
	UserDTO userDetails = new UserDTO();
	CpUserInfoDTO cpInfoDTO = new CpUserInfoDTO();
	CpUserInfo cpUserInfo = new CpUserInfo();
	CpSessionSummary sessionSummary = new CpSessionSummary();
	CpSessionSummary sessionInfo = new CpSessionSummary();
	List<CpUserInfoDTO> cpUserList = new ArrayList();
	private boolean captcha = false;
	private boolean passwordAtemptMsg = false;
	private boolean validateUser = false;
	private boolean validCaptcha;
	private boolean activateUser = false;
	private boolean downTimeStateUser = false;
	private boolean sucessLogger = false;
	
	private Date currDate;
	HttpServletRequest request = getRequest();
	private Date tillDate;
	private Date tillTime;
	private String language;
	private String clientIPTemp;
	List<CpSessionSummary> nsessionsummary = new ArrayList();
	private static final Logger logger = Logger.getLogger(LoginAction.class);
	
	List<CpCustomerDetailDTO> cpCustomerDetailList = new ArrayList();
	private List<CpServiceTypeDTO> ccMailList = new ArrayList<>();
	private List<String> ccMail = new ArrayList<>();
	
	boolean sessionExist;
	String name;
	private String userAdmin;
	private String userBuss;
	private boolean hideReply=true;

	@Autowired
	GuestPreferences guest;			
	
	
	public LoginAction() {
	}

	public void init() {
		try {
			sessionExist = false;
			boolean checkLoop = false;
			if ((checkLoop) && (BSLUtils.isNotNull(getSession()))) {
				checkLoop = true;
				updateSessionLogoutDetails();
				FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(getRequest().getContextPath() + "/login.jsf");
			}
			
			PasswordRulesDTO rulesDTO = loginDetails.fetchCpSettings();
			noOfAttempts = rulesDTO.getnAccntLockout();
			accntTimeRelease = rulesDTO.getnTimeReleaseAccntLock();
			noOfCaptchaAttempts = noOfAttempts;
			noOfCapchaAttempts = (rulesDTO.getnRequiredCaptcha() + noOfAttempts);
			noOfCaptchaAttempts = rulesDTO.getnRequiredCaptcha();
			// ccMailList = adminImpl.listcCEmail(Constants.LOGIN);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String displayName;
	private String vusername;
	
	private int referneno;
	private String useriding;
	
	 @Autowired
	  CpThemeCssBL cpThemeCssBL;
	    
	    CpThemeCss themecss = new CpThemeCss();
	    List<CpThemeCss> themeinfo = new ArrayList<CpThemeCss>();
	    List<CpThemeCss> listingtheme = new ArrayList<CpThemeCss>();
	    
	    private String themecsss;
	    private String layout;

	public String btnLogin() {
		
		//Email notification for both successfully & Failure logins
		List<CpUserInfo> userList = new ArrayList<CpUserInfo>();
		CpUserInfo cpUser = new CpUserInfo();
		
		CpUserInfoDTO cpUserInfo = new CpUserInfoDTO();

		try {
			userDetails.setUserName(userName);
			userDetails.setPassword(userPassword);
			
			//CustomerDetailDTO instance = session.getInstance();

			cpUserList = loginDetails.fetchUserDetails(userDetails);
			getSession().setAttribute("USERNAME", userName);
			Iterator<CpUserInfoDTO> it = cpUserList.iterator();
			while (it.hasNext()) {
				cpUserInfo = (CpUserInfoDTO) it.next();
			}
			if ((cpUserList.isEmpty()) || (!userName.equalsIgnoreCase(cpUserInfo.getVuserName()))) {
				displayErrorMessage("login.userList.empty");
				return "fail";
			}
			int custRefNo = ((CpUserInfoDTO) cpUserList.get(0)).getNcustRefNo();
			displayName=  ((CpUserInfoDTO) cpUserList.get(0)).getVprefName();
			vusername=  ((CpUserInfoDTO) cpUserList.get(0)).getVuserName();
			 
			referneno = ((CpUserInfoDTO) cpUserList.get(0)).getNcustRefNo();
			useriding = ((CpUserInfoDTO) cpUserList.get(0)).getVuserName();
			
			/*
			 * themecss.setCustrefno(referneno); listingtheme =
			 * cpThemeCssBL.fetch(themecss); if(listingtheme.size()>0) {
			 * themecsss=listingtheme.get(0).getThemecss();
			 * layout=listingtheme.get(0).getLayout(); }
			 */
			
			userDetails.setCustRefNo(custRefNo);
			cpCustomerDetailList = forgetPasswordBLImpl.secureDetailsForgetPassword(userDetails);
			
			
			if (cpCustomerDetailList.size() > 0) {
				String gender = cpCustomerDetailList.get(0).getGender();
				
				logger.info("Gender is ::::::::::::::::::::::" +gender);
				getSession().setAttribute("GENDER", gender);
				
				name = BSLUtils.isNotNull(((CpCustomerDetailDTO) cpCustomerDetailList.get(0)).getVcustName())
						? upperCaseWords(((CpCustomerDetailDTO) cpCustomerDetailList.get(0)).getVcustName())
						: "Customer";

				getSession().setAttribute("CUSTOMERNAME", name);

			}
			if ((BSLUtils.isNotNull(cpUserInfo.getVlocked())) && (!cpUserInfo.getVlocked().equalsIgnoreCase("N"))) {
				//displayErrorMessageWithParamString("login.lock.account", accntTimeRelease, "login.lock.account1");

				return "fail";
			}

			if ((BSLUtils.isNotNull(cpUserInfo.getVactive())) && (!cpUserInfo.getVactive().equals("A"))
					&& (!cpUserInfo.getVactive().equals("C"))) {
				displayErrorMessage("login.userActivationError");
				return "fail";
			}

			String tmpPassword = userPassword;
			String encryptPassword = tmpPassword;
			//Credential.crypt(userPassword);
			
			//Email notification
			cpUser.setVuserName(cpUserInfo.getVuserName());
			cpUser.setVprefName(cpUserInfo.getVprefName());
			cpUser.setVcontactNo(cpUserInfo.getVcontactNo());
			cpUser.setVemail(cpUserInfo.getVemail());
			userList.add(cpUser);
			
			if (BSLUtils.isNull(tmpPassword)) {
				tmpPassword = userPassword;
			}
			//Validating user credentials  && (!cpUserInfo.getVpassword().equalsIgnoreCase(tmpPassword))
			//			if ((BSLUtils.isNotNull(cpUserInfo.getVpassword())) && (!cpUserInfo.getVpassword().equalsIgnoreCase(encryptPassword))) {

			if ((BSLUtils.isNotNull(cpUserInfo.getVpassword())) && (!cpUserInfo.getVpassword().equalsIgnoreCase(encryptPassword))) {

				noOfAttempts -= 1;
				logger.info("No of Attempt @@@@@@@@@@@@@@@@@@@ :" + noOfAttempts);

				if (noOfAttempts == 0) {
					logger.info("Login Attempt block entering here::::");
					cpInfoDTO.setVuserName(userName);
					loginDetails.lockUser(cpInfoDTO);
					logger.info("After locking account in DB::::");

					/*
					 * for(CpServiceTypeDTO dto : ccMailList){
					 * ccMail.add(dto.getCcEmail()); }
					 */

					displayErrorMessageWithParamString("login.lock.account", accntTimeRelease, "login.lock.account1");
					setCaptcha(true);

					to = ((CpUserInfoDTO) cpUserList.get(0)).getVemail();
					subject = text.getString("login.lock.account.email.subject");
					body = (text.getString("registrationAction.login.confirmationEmailSal") + " " + name + "," + "\n"
							+ "\n" + text.getString("login.lock.account.email.body") + "\n" + "\n"
							+ text.getString("login.lock.account.email.body1") + accntTimeRelease + " "
							+ text.getString("login.lock.account.email.body2") + "\n" + "\n"
							+ text.getString("login.lock.account.email.body3") + "\n"
							+ text.getString("login.lock.account.email.body4"));
					// appMailer.sendMail(to,ccMail,subject, body);
					appMailer.sendMail(to, subject, body);

					FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
					return "fail";
				}
				if (noOfAttempts > 0) {
					//displayErrorMessageWithParamString("login.noOfCounts.pre", noOfAttempts, "login.noOfCounts.nex");
					
					//SMS Trigger 
					String smsResponse = messageQueue.sendSMSTOQueue(userList,"FAILURE LOGIN",noOfAttempts+"");
					logger.info("Response form SMS queue while login failure : "+smsResponse+"");
					 
					//Email-Trigger
					String response = messageQueue.sendMailTOQueue(userList,"FAILURE LOGIN",noOfAttempts+"");
					
					logger.info("Message logged response forn JMS Queue: Username: "+cpUser.getVuserName()+", Response: "+response+"");
				}
				return "fail";
			}

			CpServerSettingDTO cpServerSettingDTO = new CpServerSettingDTO();

			Calendar cal = null;
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date currDate = new Date();
			cal = Calendar.getInstance();
			cal.setTime(currDate);

			cpServerSettingDTO.setvEffectiveFrom(currDate);
			cpServerSettingDTO.setvEffectTill(currDate);
			cpServerSettingDTO.setdStartTime(currDate);
			cpServerSettingDTO.setdEndTime(currDate);

			if (cpUserInfo.getVgroup().equalsIgnoreCase("U")) {
				serverSettingList = loginDetails.fetchDownTime(cpServerSettingDTO);
				tillDate = serverSettingList.getvEffectTill();
				tillTime = serverSettingList.getdEndTime();

				if (BSLUtils.isNotNull(serverSettingList.getvEffectiveFrom())) {
					Date today = new Date();
					Date startDate = new Date();
					Date endDate = new Date();
					startDate.setDate(serverSettingList.getvEffectiveFrom().getDate());
					startDate.setYear(serverSettingList.getvEffectiveFrom().getYear());
					startDate.setMonth(serverSettingList.getvEffectiveFrom().getMonth());
					startDate.setHours(serverSettingList.getdStartTime().getHours());
					startDate.setMinutes(serverSettingList.getdStartTime().getMinutes());
					startDate.setSeconds(serverSettingList.getdStartTime().getSeconds());
					endDate.setDate(serverSettingList.getvEffectTill().getDate());
					endDate.setYear(serverSettingList.getvEffectTill().getYear());
					endDate.setMonth(serverSettingList.getvEffectTill().getMonth());
					endDate.setHours(serverSettingList.getdEndTime().getHours());
					endDate.setMinutes(serverSettingList.getdEndTime().getMinutes());
					endDate.setSeconds(serverSettingList.getdEndTime().getSeconds());
					System.out.println(today + " *-* " + startDate + " *-* " + endDate);
					System.out.println(isLesserorEqualDates(today, startDate));
					System.out.println(isGreaterorEqualDates(today, endDate));

					boolean condition1 = isLesserorEqualDates(today, startDate);
					boolean condition2 = isGreaterorEqualDates(today, endDate);
					if ((!condition1) && (!condition2)) {
						tillDate.setHours(serverSettingList.getdEndTime().getHours());
						tillDate.setMinutes(serverSettingList.getdEndTime().getMinutes());
						tillDate.setSeconds(serverSettingList.getdEndTime().getSeconds());
						displayErrorMessageWithParamStringDate("login.userDowntimeStatus.pre", tillDate,
								"login.userDowntimeStatus.nex");
						return "fail";
					}
					saveSessionDetails();
				}
			}
			
			guest.fetchtheme();
			
		} catch (Exception e) {
			logger.info("error at Login:@@@@@@@" + e.getMessage());
			e.printStackTrace();
			return "fail";
		}

		if (cpUserInfo.getVgroup().equalsIgnoreCase("A")) {
			userAdmin=cpUserInfo.getVgroup();
			hideReply=true;
			sessionExist = true;
			
			//SMS Trigger 
			//String smsResponse = messageQueue.sendSMSTOQueue(userList,"SUCCESSFUL LOGIN",null);
		    //logger.info("Response form SMS queue while Admin login : "+smsResponse+"");
			
			//Email-Trigger
			String response = messageQueue.sendMailTOQueue(userList, "SUCCESSFUL LOGIN",null);
			logger.info("Message logged response from  JMS Queue for Admin login: Userneme: "+cpUser.getVuserName()+", Response: "+response+"");
			return "/dashboard?faces-redirect=true";
			
		}
		if (cpUserInfo.getVgroup().equalsIgnoreCase("B")) {
			userBuss=cpUserInfo.getVgroup();
			hideReply=false;
			
			//SMS Trigger 
			//String smsResponse = messageQueue.sendSMSTOQueue(userList,"SUCCESSFUL LOGIN",null);
			//logger.info("Response form SMS queue while Business login : "+smsResponse+"");
			
			//Email-Trigger
			String response = messageQueue.sendMailTOQueue(userList, "SUCCESSFUL LOGIN", null);
			logger.info("Message logged response forn JMS Queue: Userneme: "+cpUser.getVuserName()+", Response: "+response+"");
			return "/dashboard?faces-redirect=true";
		}

		//SMS Trigger 
	    //String smsResponse = messageQueue.sendSMSTOQueue(userList,"SUCCESSFUL LOGIN",null);
		//logger.info("Response form SMS queue while Customer login : "+smsResponse+"");
		
		//Email-Trigger
		//String response = messageQueue.sendMailTOQueue(userList, "SUCCESSFUL LOGIN", null);
		//logger.info("Message logged response forn JMS Queue: Userneme: " + cpUser.getVuserName() + ", Response: "+ response + "");
		
		
		return "/dashboard?faces-redirect=true";
	}

	public void engLangButton(ActionEvent engEvent) {
		changeToEnglish();
	}

	public void arbLangButton(ActionEvent arbEvent) {
		changeToArabic();
	}

	public static boolean isGreaterorEqualDates(Date date1, Date date2) {
		boolean status = false;
		if (date1.compareTo(date2) > 0) {
			System.out.println("todayDate is after endDate");
			status = true;
		} else if (date1.compareTo(date2) < 0) {
			System.out.println("todayDate is before endDate");
			status = false;
		} else if (date1.compareTo(date2) == 0) {
			System.out.println("todayDate is equal to endDate");
			if (date1.getHours() < date2.getHours()) {
				status = false;
			} else if (date1.getMinutes() < date2.getMinutes()) {
				status = false;
			} else if (date1.getSeconds() < date2.getSeconds()) {
				status = false;
			} else {
				status = true;
			}
		} else {
			System.out.println("How to get here?");
		}
		return status;
	}

	public static boolean isLesserorEqualDates(Date date1, Date date2) {
		boolean status = false;
		if (date1.compareTo(date2) > 0) {
			System.out.println("todayDate is after startDate");
			status = false;
		} else if (date1.compareTo(date2) < 0) {
			System.out.println("todayDate is before startDate");
			status = true;
		} else if (date1.compareTo(date2) == 0) {
			System.out.println("todayDate is equal to startDate");
			if (date1.getHours() < date2.getHours()) {
				status = false;
			} else if (date1.getMinutes() < date2.getMinutes()) {
				status = false;
			} else if (date1.getSeconds() < date2.getSeconds()) {
				status = false;
			} else {
				status = true;
			}
		} else {
			System.out.println("How to get here?");
		}
		return status;
	}

	public void logout() {
		try {
			updateSessionLogoutDetails();
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(getRequest().getContextPath() + "/login.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveSessionDetails() {
		logger.info("Entering into Session Details Method:::::::::");

		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();

		Cookie[] cookies = httpServletRequest.getCookies();
		
		for (Cookie each : cookies) {
			if ((each != null) && (each.getName().equalsIgnoreCase("ClientIPTemp"))) {
				setClientIPTemp(each.getValue());
			}
		}
		
		String remoteAddr = httpServletRequest.getRemoteAddr();
		String remoteName = httpServletRequest.getRemoteHost();
		String scheme = httpServletRequest.getScheme();
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
		    ipAddress = request.getRemoteAddr();
		}

		noOfSessions = (noOfSessions++);
		Calendar cal = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date currentDate = new Date();

		cal = Calendar.getInstance();
		cal.setTime(currentDate);

		try {
			CpSessionSummaryDTO sessionInfo = new CpSessionSummaryDTO();
			CpSessionSummary nsessionsinfo = new CpSessionSummary();

			if (BSLUtils.isNotNull(clientIPTemp)) {
				sessionInfo.setVclientIp(clientIPTemp);
			}
			sessionInfo.setDsessionEnd(null);
			sessionInfo.setDsessionStart(currentDate);
			sessionInfo.setnActive(nsessionsinfo.getnActive());
			sessionInfo.setVlastUpdInftim(currentDate);
			sessionInfo.setVlastUpdProg("CP");
			sessionInfo.setdLastSessionTransaction(null);
			sessionInfo.setVlastUpdUser("CustomerPortal");
			sessionInfo.setvLogOff("N");
			sessionInfo.setvPhpSessionId(getSession().getId());
			sessionInfo.setdLastSessionTransaction(currentDate);
			sessionInfo.setVuserName(userName);
			sessionInfo.setNsessionId(noOfSessions);

			loginDetails.saveSessionDetails(sessionInfo);
			logger.info("Exit into Session Details Method:::::::::");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void saveSessionLogoutDetails() {
		noOfSessions = (noOfSessions++);
		Calendar cal = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date currentDate = new Date();
		cal = Calendar.getInstance();
		cal.setTime(currentDate);
		try {
			InetAddress ip = InetAddress.getLocalHost();
			CpSessionSummaryDTO sessionInfo = new CpSessionSummaryDTO();
			CpSessionSummary nsessionsinfo = new CpSessionSummary();

			// sessionInfo.setVclientIp(ip);
			sessionInfo.setDsessionEnd(null);
			sessionInfo.setDsessionStart(currentDate);
			sessionInfo.setnActive(nsessionsinfo.getnActive());
			sessionInfo.setVlastUpdInftim(currentDate);
			sessionInfo.setVlastUpdProg("CP");
			sessionInfo.setdLastSessionTransaction(null);
			sessionInfo.setVlastUpdUser("CustomerPortal");
			sessionInfo.setvLogOff("N");
			sessionInfo.setvPhpSessionId(getSession().getId());
			sessionInfo.setdLastSessionTransaction(currentDate);
			sessionInfo.setVuserName(userName);
			sessionInfo.setNsessionId(noOfSessions);

			loginDetails.saveSessionDetails(sessionInfo);

		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void updateSessionLogoutDetails() {
		noOfSessions = (noOfSessions++);
		Calendar cal = null;
		Date currentDate = new Date();
		try {
			CpSessionSummaryDTO sessionInfo = new CpSessionSummaryDTO();
			sessionInfo.setDsessionEnd(currentDate);
			sessionInfo.setvLogOff("Y");
			sessionInfo.setvPhpSessionId(getSession().getId());
			cpsessionBl.updateSessionDetails(sessionInfo);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public String upperCaseWords(String sentence) {
		String[] words = sentence.replaceAll("\\s+", " ").trim().split(" ");
		String newSentence = "";
		for (String word : words) {
			for (int i = 0; i < word.length(); i++) {
				newSentence = newSentence + (i != word.length() - 1 ? word.substring(i, i + 1).toLowerCase()
						: i == 0 ? word.substring(i, i + 1).toUpperCase()
								: new StringBuilder(
										String.valueOf(word.substring(i, i + 1).toLowerCase().toLowerCase()))
												.append(" ").toString());
			}
		}
		return newSentence.trim();
	}

	public UserDTO getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDTO userDetails) {
		this.userDetails = userDetails;
	}

	public String getUserList() {
		return userList;
	}

	public void setUserList(String userList) {
		this.userList = userList;
	}

	public CpUserInfo getCpUserInfo() {
		return cpUserInfo;
	}

	public void setCpUserInfo(CpUserInfo cpUserInfo) {
		this.cpUserInfo = cpUserInfo;
	}

	public List<CpUserInfoDTO> getCpUserList() {
		return cpUserList;
	}

	public void setCpUserList(List<CpUserInfoDTO> cpUserList) {
		this.cpUserList = cpUserList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public int getNoOfAttempts() {
		return noOfAttempts;
	}

	public void setNoOfAttempts(int noOfAttempts) {
		this.noOfAttempts = noOfAttempts;
	}

	public String getLoginErrorMsg() {
		return loginErrorMsg;
	}

	public void setLoginErrorMsg(String loginErrorMsg) {
		this.loginErrorMsg = loginErrorMsg;
	}

	public CustomerPortalServicesImpl getCustomerPortalServices() {
		return customerPortalServices;
	}

	public void setCustomerPortalServices(CustomerPortalServicesImpl customerPortalServices) {
		this.customerPortalServices = customerPortalServices;
	}

	public List<CpServerSettingDTO> getDownTimeStatus() {
		return downTimeStatus;
	}

	public void setDownTimeStatus(List<CpServerSettingDTO> downTimeStatus) {
		this.downTimeStatus = downTimeStatus;
	}

	public boolean isCaptcha() {
		return captcha;
	}

	public void setCaptcha(boolean captcha) {
		this.captcha = captcha;
	}

	public boolean isPasswordAtemptMsg() {
		return passwordAtemptMsg;
	}

	public void setPasswordAtemptMsg(boolean passwordAtemptMsg) {
		this.passwordAtemptMsg = passwordAtemptMsg;
	}

	public boolean isValidateUser() {
		return validateUser;
	}

	public void setValidateUser(boolean validateUser) {
		this.validateUser = validateUser;
	}

	public boolean isExceededPassAtempts() {
		return exceededPassAtempts;
	}

	public void setExceededPassAtempts(boolean exceededPassAtempts) {
		this.exceededPassAtempts = exceededPassAtempts;
	}

	public boolean isActivateUser() {
		return activateUser;
	}

	public void setActivateUser(boolean activateUser) {
		this.activateUser = activateUser;
	}

	public boolean isDownTimeStateUser() {
		return downTimeStateUser;
	}

	public void setDownTimeStateUser(boolean downTimeStateUser) {
		this.downTimeStateUser = downTimeStateUser;
	}

	public CpServerstatusSetting getSysDate() {
		return sysDate;
	}

	public void setSysDate(CpServerstatusSetting sysDate) {
		this.sysDate = sysDate;
	}

	public boolean isSucessLogger() {
		return sucessLogger;
	}

	public void setSucessLogger(boolean sucessLogger) {
		this.sucessLogger = sucessLogger;
	}

	public Date getCurrDate() {
		return currDate;
	}

	public void setCurrDate(Date currDate) {
		this.currDate = currDate;
	}

	public CpSessionSummary getSessionInfo() {
		return sessionInfo;
	}

	public void setSessionInfo(CpSessionSummary sessionInfo) {
		this.sessionInfo = sessionInfo;
	}

	public CpSessionSummary getSessionSummary() {
		return sessionSummary;
	}

	public void setSessionSummary(CpSessionSummary sessionSummary) {
		this.sessionSummary = sessionSummary;
	}

	public Date getTillDate() {
		return tillDate;
	}

	public void setTillDate(Date tillDate) {
		this.tillDate = tillDate;
	}

	public Date getTillTime() {
		return tillTime;
	}

	public void setTillTime(Date tillTime) {
		this.tillTime = tillTime;
	}

	public String getNoOfCaptcha() {
		return noOfCaptcha;
	}

	public void setNoOfCaptcha(String noOfCaptcha) {
		this.noOfCaptcha = noOfCaptcha;
	}

	public boolean isValidCaptcha() {
		return validCaptcha;
	}

	public void setValidCaptcha(boolean validCaptcha) {
		this.validCaptcha = validCaptcha;
	}

	public int getNoOfCaptchaAttempts() {
		return noOfCaptchaAttempts;
	}

	public void setNoOfCaptchaAttempts(int noOfCaptchaAttempts) {
		this.noOfCaptchaAttempts = noOfCaptchaAttempts;
	}

	public CpUserInfoDTO getCpInfoDTO() {
		return cpInfoDTO;
	}

	public void setCpInfoDTO(CpUserInfoDTO cpInfoDTO) {
		this.cpInfoDTO = cpInfoDTO;
	}

	public List<CpSessionSummary> getNsessionsummary() {
		return nsessionsummary;
	}

	public void setNsessionsummary(List<CpSessionSummary> nsessionsummary) {
		this.nsessionsummary = nsessionsummary;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public ApplicationMailer getAppMailer() {
		return appMailer;
	}

	public void setAppMailer(ApplicationMailer appMailer) {
		this.appMailer = appMailer;
	}

	public String getClientIPTemp() {
		return clientIPTemp;
	}

	public void setClientIPTemp(String clientIPTemp) {
		this.clientIPTemp = clientIPTemp;
	}

	public boolean isSessionExist() {
		return sessionExist;
	}

	public void setSessionExist(boolean sessionExist) {
		this.sessionExist = sessionExist;
	}

	public boolean isHideReply() {
		return hideReply;
	}

	public void setHideReply(boolean hideReply) {
		this.hideReply = hideReply;
	}

	public String getUserAdmin() {
		return userAdmin;
	}

	public void setUserAdmin(String userAdmin) {
		this.userAdmin = userAdmin;
	}

	public String getUserBuss() {
		return userBuss;
	}

	public void setUserBuss(String userBuss) {
		this.userBuss = userBuss;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getVusername() {
		return vusername;
	}

	public void setVusername(String vusername) {
		this.vusername = vusername;
	}

	public int getReferneno() {
		return referneno;
	}

	public void setReferneno(int referneno) {
		this.referneno = referneno;
	}

	public String getUseriding() {
		return useriding;
	}

	public void setUseriding(String useriding) {
		this.useriding = useriding;
	}

	public CpThemeCss getThemecss() {
		return themecss;
	}

	public void setThemecss(CpThemeCss themecss) {
		this.themecss = themecss;
	}

	public List<CpThemeCss> getThemeinfo() {
		return themeinfo;
	}

	public void setThemeinfo(List<CpThemeCss> themeinfo) {
		this.themeinfo = themeinfo;
	}

	public String getThemecsss() {
		return themecsss;
	}

	public void setThemecsss(String themecsss) {
		this.themecsss = themecsss;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public List<CpThemeCss> getListingtheme() {
		return listingtheme;
	}

	public void setListingtheme(List<CpThemeCss> listingtheme) {
		this.listingtheme = listingtheme;
	}
	
	
	
	
	
	
	
	
	
	
	
}