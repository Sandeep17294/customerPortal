package com.aetins.customerportal.web.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.faces.application.FacesMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aetins.customerportal.core.enums.ROLES;
import com.aetins.customerportal.core.events.registration.OnRegistrationCompleteEvent;
import com.aetins.customerportal.core.faceletstaglib.SecurityLibrary;
import com.aetins.customerportal.core.pojo.UserRegistration;
import com.aetins.customerportal.web.audittrails.service.RegistrationAuditTrailService;
import com.aetins.customerportal.web.dao.CpCustomerDetailDAO;
import com.aetins.customerportal.web.dao.CpQuestionDetailDAO;
import com.aetins.customerportal.web.dao.CustomerLoginDAO;
import com.aetins.customerportal.web.dao.ResetSecurityAnswerDAO;
import com.aetins.customerportal.web.dao.RolesDao;
import com.aetins.customerportal.web.dto.CpServerSettingDTO;
import com.aetins.customerportal.web.dto.RegistrationCustomerDTO;
import com.aetins.customerportal.web.entity.CpCustomerDetail;
import com.aetins.customerportal.web.entity.CpQuestionDetail;
import com.aetins.customerportal.web.entity.CpRegLock;
import com.aetins.customerportal.web.entity.CpRegistrationAuditTrail;
import com.aetins.customerportal.web.entity.CpResetSecurityAnswer;
import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.entity.VerificationToken;
import com.aetins.customerportal.web.service.AdminBL;
import com.aetins.customerportal.web.service.CpConfigurationBL;
import com.aetins.customerportal.web.service.CpRegLockBL;
import com.aetins.customerportal.web.service.RegistrationCustomerService;
import com.aetins.customerportal.web.service.RegistrationVerifyToken;
import com.aetins.customerportal.web.utils.BSLUtils;
import com.aetins.customerportal.web.utils.ConfigurationConstants;
import com.aetins.customerportal.web.utils.DateTimeUtil;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * @author avinash
 *
 */
@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

	private static Logger logger = LoggerFactory.getLogger(RegistrationController.class);

	@SuppressWarnings("rawtypes")
	@Autowired
	private RegistrationCustomerService registrationCustomerService;

	@Autowired
	private CustomerLoginDAO customerDao;

	@Autowired
	private CpQuestionDetailDAO secQUestions;

	@Autowired
	private RolesDao roleDao;

	@Autowired
	private ResetSecurityAnswerDAO resetDAO;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	private RegistrationAuditTrailService registrationAuditService;

	@Autowired
	private RegistrationVerifyToken registrationToken;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CpConfigurationBL configService;
	
	@Autowired
	private CpCustomerDetailDAO cpCustomerDetailDAO;
	
	@Autowired
	CpRegLockBL cpRegLockBL;
	
	int invalidattempts = 0;
	
	private String errormessage;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	private static final Integer EXPIRE_MINS = 5;

	private static final String IDENTYPE = "POLICYNO";

	//private static int NUMBER_OF_SEC_QUES = 2;

	private LoadingCache<String, Object> registrationCache;
	
	//Registration questions label 
	private static String NUMBER_OF_REGISTRATION_SEC_QUES = "nmuberofsecques";

	/**
	 * 
	 */
	public RegistrationController() {
		// Cache to holds registered user details
		registrationCache = CacheBuilder.newBuilder().expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES)
				.build(new CacheLoader<String, Object>() {
					public String load(String key) {
						return null;
					}
				});
	}

    private List<CpServerSettingDTO> cpServerSettingDTOList;
	
	@Autowired
	AdminBL adminImpl;
	
	/**
	 * Initial verification 
	 * @param policyNo
	 * @param mobileNo
	 * @param email
	 * @param request
	 * @param response
	 */
	@PostMapping(value = "/policy")
	public void verifyCustomer(@QueryParam("idenCode") String idenCode,@QueryParam("idenNum") String idenNum, @QueryParam("mobileNo") String mobileNo,
			@QueryParam("email") String email, final HttpServletRequest request, final HttpServletResponse response) {

		UserRegistration user = new UserRegistration();
		ModelAndView view = null;
		invalidattempts = 0;
		String policystatus = null;
		int serialno = 0;
		idenCode = "POLICYNO";
		
		try {
			
			cpServerSettingDTOList = null;
			cpServerSettingDTOList = new ArrayList<CpServerSettingDTO>();
			cpServerSettingDTOList = adminImpl.fetchDownTime();
		    if(cpServerSettingDTOList.get(0).getStatus().equalsIgnoreCase("A")) {
		    	logger.error("Indentification Num Details is not not found");
		    	DateFormat df = new SimpleDateFormat("dd/MM/yy");
			     System.out.println(df.format(cpServerSettingDTOList.get(0).getvEffectTill()));
			     String errorMsg = null;
			     errorMsg = "Dear User portal is under maintaince from"+" "+(df.format(cpServerSettingDTOList.get(0).getvEffectiveFrom()))+" "+cpServerSettingDTOList.get(0).getdStartTime()+" "+"to"+" "+(df.format(cpServerSettingDTOList.get(0).getvEffectTill()))+" "+cpServerSettingDTOList.get(0).getdEndTime()+".";
			     redirectStrategy.sendRedirect(request, response, "/registration/downsetting?errorMsg=" + errorMsg + "");
			}else {
				CpRegLock list = new CpRegLock();
				list=cpRegLockBL.fetchpolicy(idenNum);
				if(list.getIdenno()!=null) {
					invalidattempts=list.getNoofattempts();
					policystatus=list.getStatus();
					serialno=list.getSno();
				}
				
				if(policystatus==null || policystatus.equalsIgnoreCase("NORMAL") || policystatus.equalsIgnoreCase("UNLOCKED")) {
					if (BSLUtils.isNotNull(idenNum)) {
						RegistrationCustomerDTO custDetails = getCustomerDetails("EN",idenCode, idenNum);
						logger.info("Customer details for Indetification: {}, details: {}", idenNum, custDetails);
						if (BSLUtils.isNotNull(custDetails.getEmailID()) && BSLUtils.isNotNull(custDetails.getMobileNumber())) {
							if (custDetails.getEmailID().equals(email) && custDetails.getMobileNumber().equals(mobileNo)) {
								logger.info("Given Indentification Num: {}, Email , Mobile: {} redirecting to registration page", idenNum,
										email + mobileNo);
										redirectStrategy.sendRedirect(request, response,
										"/registration/regpage?policyNo=" + idenNum + "&idenCode="+idenCode+"");
							} else {
								if(invalidattempts==0) {
									invalidattempts++;
									CpRegLock dto = new CpRegLock();
									boolean status = false;
									dto.setIdentype(idenCode);
					 				dto.setIdenno(idenNum);
									dto.setNoofattempts(invalidattempts);
									dto.setStatus("NORMAL");
									if(list.getModel()!=null) {
										dto.setSno(serialno);
										if(list.getModel().equalsIgnoreCase("OLD")) {
		                                    dto.setModel("OLD");
											status=cpRegLockBL.updatettable(dto);
										}else {
											dto.setModel("NEW");
											status=cpRegLockBL.updatettable(dto);
										}
									}else {
										dto.setModel("NEW");
										status=cpRegLockBL.inserttable(dto);
									}
									logger.info("Given Indentification Num: {}, Email , Mobile: {} is not matched redircting to login page",idenNum, email + mobileNo);
									redirectStrategy.sendRedirect(request, response, "/registration/email_phone_notfound?policyNo="+idenNum+"&email="+email+"&mobileNo="+mobileNo+"&invalidattempts="+invalidattempts+"");
								}else {
									invalidattempts++;	
									if(invalidattempts==4) {
										CpRegLock dto = new CpRegLock();
										boolean status = false;
										dto.setIdentype(idenCode);
						 				dto.setIdenno(idenNum);
										dto.setNoofattempts(invalidattempts);
										dto.setStatus("LOCKED");
										dto.setSno(serialno);
										if(list.getModel()!=null) {
											if(list.getModel().equalsIgnoreCase("OLD")) {
			                                    dto.setModel("OLD");
												status=cpRegLockBL.updatettable(dto);
											}else {
												dto.setModel("NEW");
												status=cpRegLockBL.updatettable(dto);
											}
										}
										logger.info("Given Indentification Num: {}, Email , Mobile: {} is not matched redircting to login page",idenNum, email + mobileNo);
										redirectStrategy.sendRedirect(request, response, "/registration/email_phone_notfound?policyNo="+idenNum+"&email="+email+"&mobileNo="+mobileNo+"&invalidattempts="+invalidattempts+"");
									}else {
										CpRegLock dto = new CpRegLock();
										boolean status = false;
										dto.setIdentype(idenCode);
						 				dto.setIdenno(idenNum);
										dto.setNoofattempts(invalidattempts);
										dto.setStatus("NORMAL");
										dto.setSno(serialno);
										if(list.getModel()!=null) {
											if(list.getModel().equalsIgnoreCase("OLD")) {
			                                    dto.setModel("OLD");
			                                    status=cpRegLockBL.updatettable(dto);
											}else {
												dto.setModel("NEW");
												status=cpRegLockBL.updatettable(dto);
											}
										}
										logger.info("Given Indentification Num: {}, Email , Mobile: {} is not matched redircting to login page",idenNum, email + mobileNo);
										redirectStrategy.sendRedirect(request, response, "/registration/email_phone_notfound?policyNo="+idenNum+"&email="+email+"&mobileNo="+mobileNo+"&invalidattempts="+invalidattempts+"");
									}	
								}
							//	logger.info("Given Indentification Num: {}, Email , Mobile: {} is not matched redircting to login page",idenNum, email + mobileNo);
							//	redirectStrategy.sendRedirect(request, response, "/registration/detailsNotMatch?policyNo="+idenNum+"&email="+email+"&mobileNo="+mobileNo+"");

							}
						}else {
							logger.error("Indentification Num Details is not not found");
							redirectStrategy.sendRedirect(request, response, "/registration/policyNotFound?policyNo="+ idenNum +"");
						}
					}
				}else if(policystatus.equalsIgnoreCase("LOCKED")){
				//new screen
					logger.error("Indentification Num Details is not not found");
					redirectStrategy.sendRedirect(request, response, "/registration/locked?policyNo="+idenNum+"&email="+email+"&mobileNo="+mobileNo+"&invalidattempts="+invalidattempts+"");
				}

			}	
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception caught while verifying customer details with cause : {}", e.getCause());
		}
	}

	/**
	 * registration page navigation
	 * @param policyNo
	 * @return
	 */
	@GetMapping(value = "/regpage")
	public ModelAndView getRegistrationView(@QueryParam("policyNo") String policyNo,@QueryParam("idenCode") String idenCode) {

		UserRegistration user = new UserRegistration();
		ModelAndView modal = new ModelAndView("loginRegistration", "command", user);
		
		//Load portal configuration map
		Map<String, String> configMap = configService.getConfigMap();
		
		int NUMBER_OF_SEC_QUES = 0;
		
		if(configMap!=null&&configMap.containsKey(ConfigurationConstants._PORTAL_MANAGEACCOUNT_QUES_COUNT)) {
			
			NUMBER_OF_SEC_QUES = Integer.parseInt(configMap.get(ConfigurationConstants._PORTAL_MANAGEACCOUNT_QUES_COUNT));
			logger.info("Number of registration security questions: {} ",NUMBER_OF_SEC_QUES);
			//NUMBER_OF_SEC_QUES = NUMBER_OF_SEC_QUES-1;
			registrationCache.put(NUMBER_OF_REGISTRATION_SEC_QUES, NUMBER_OF_SEC_QUES);	
			logger.info("Number of registration questions loaded in registration cache: {}",NUMBER_OF_SEC_QUES);
		}
		
		if(configMap!=null&&configMap.containsKey(ConfigurationConstants._PORTAL_TERMSCONDITIONS_ENGLISH)) {
			modal.addObject("termsConditions", configMap.get(ConfigurationConstants._PORTAL_TERMSCONDITIONS_ENGLISH));
		}
		modal.addObject("secquesno", NUMBER_OF_SEC_QUES);
		modal.addObject("policyNo", policyNo);
		modal.addObject("idenCode", idenCode);
		logger.info("Hidden field policyNo: {} idenCode: {} has forwarded to view ", policyNo,idenCode);
		logger.info("Rendering registration page with number of : {}  security questions", NUMBER_OF_SEC_QUES);
		return modal;
	}
	/**
	 * Policy not found page
	 * @param policyNo
	 * @return
	 */
	@GetMapping(value = "/policyNotFound")
	public ModelAndView getPolicyNotFoundView(@QueryParam("policyNo") String policyNo) {
		
		UserRegistration user = new UserRegistration();
		ModelAndView modal = new ModelAndView("policyNotFoundPage", "command", user);
		modal.addObject("policyNo", policyNo);
		logger.error("Policy Not found policyNo: {}  forwarding to error page", policyNo);
		return modal;
	}
	/**
	 * navigation for details not matched caution page
	 * @param policyNo
	 * @param email
	 * @param mobileNo
	 * @return
	 */
	@GetMapping(value = "/detailsNotMatch")
	public ModelAndView getDetailsNotMatchView(@QueryParam("policyNo") String policyNo,@QueryParam("email") String email,@QueryParam("mobileNo") String mobileNo) {

		UserRegistration user = new UserRegistration();
		ModelAndView modal = new ModelAndView("detailsNotMatch", "command", user);
		modal.addObject("policyNo", policyNo);
		modal.addObject("email", email.replaceAll("(?<=.{4}).(?=[^@]*?@)", "*"));
		modal.addObject("mobileNo", mobileNo.replaceAll("(?<=.{4}).(?=[^@]*?@)", "*"));
		logger.error("Given Policy: {}, Email , Mobile: {} is not matched redirecting to details not found page",policyNo, email + mobileNo);
		return modal;
	}


	
//malik	
	@GetMapping(value = "/email_phone_notfound")
	public ModelAndView getDetailsNotMatchView1(@QueryParam("policyNo") String policyNo,@QueryParam("email") String email,@QueryParam("mobileNo") String mobileNo,@QueryParam("invalidattempts") int invalidattempts) {

		UserRegistration user = new UserRegistration();
		ModelAndView modal = new ModelAndView("email_phone_notfound", "command", user);
		modal.addObject("policyNo", policyNo);
		modal.addObject("email", email.replaceAll("(?<=.{4}).(?=[^@]*?@)", "*"));
		modal.addObject("mobileNo", mobileNo.replaceAll("(?<=.{4}).(?=[^@]*?@)", "*"));
		modal.addObject("invalidattempts", invalidattempts);
		logger.error("Given Policy: {}, Email , Mobile: {} is not matched redirecting to details not found page",policyNo, email + mobileNo);
		return modal;
	}


	@GetMapping(value = "/locked")
	public ModelAndView getDetailsNotMatchView2(@QueryParam("policyNo") String policyNo,@QueryParam("email") String email,@QueryParam("mobileNo") String mobileNo,@QueryParam("invalidattempts") int invalidattempts) {

		UserRegistration user = new UserRegistration();
		ModelAndView modal = new ModelAndView("locked", "command", user);
		modal.addObject("policyNo", policyNo);
		modal.addObject("email", email.replaceAll("(?<=.{4}).(?=[^@]*?@)", "*"));
		modal.addObject("mobileNo", mobileNo.replaceAll("(?<=.{4}).(?=[^@]*?@)", "*"));
		modal.addObject("invalidattempts", invalidattempts);
		logger.error("Given Policy: {}, Email , Mobile: {} is not matched redirecting to details not found page",policyNo, email + mobileNo);
		return modal;
	}
	
	@GetMapping(value = "/downsetting")
	public ModelAndView getDetailsNotMatchView3(@QueryParam("errorMsg") String errorMsg) {
		UserRegistration user = new UserRegistration();
		ModelAndView modal = new ModelAndView("downsetting", "command", user);
		modal.addObject("errorMsg", errorMsg);
		return modal;
	}


	
	/**
	 * In order to carter incoming values for successfully registration
	 * @param user
	 * @param result
	 * @param request
	 * @param response
	 */
	@PostMapping(value = "/success")
	public void registerCustomer(UserRegistration user, BindingResult result,final HttpServletRequest request, final HttpServletResponse response) {

		try {
			
			logger.info("User Registration from front end : {}", user);

			CpUserInfo userInfo = new CpUserInfo();
			
			int NUMBER_OF_SEC_QUES = Integer.parseInt(registrationCache.get(NUMBER_OF_REGISTRATION_SEC_QUES).toString());
			
			List<CpResetSecurityAnswer> secQuesAnsList = new ArrayList<CpResetSecurityAnswer>();

			CpResetSecurityAnswer securityQues = null;

			CpRegistrationAuditTrail registrationTrail = new CpRegistrationAuditTrail();

			RegistrationCustomerDTO customerDetails = getCustomerDetails("EN",user.getIdenCode(), user.getPolicyNo());

			if (user != null) {

				userInfo.setVuserName(user.getUsername());
				userInfo.setVprefName(user.getDisplayName());
				userInfo.setVemail(customerDetails.getEmailID());
				userInfo.setVcontactNo(customerDetails.getMobileNumber());
				userInfo.setVtitle(customerDetails.getTitle());
				userInfo.setDdob(DateTimeUtil.dateFormat(customerDetails.getEnglishDOB()));
				userInfo.setVgroup("U");
				userInfo.setVlocked("N");
				userInfo.setRoles(Arrays.asList(roleDao.findByName(ROLES.ROLE_USER.name())));
				userInfo.setNcustRefNo(customerDetails.getCustomerReferenceNumber().intValue());

				// Fetch all questions
				List<CpQuestionDetail> resetQuesAns = getSecurityQuesList();

				for (int secQuesCount = 0; secQuesCount < NUMBER_OF_SEC_QUES; secQuesCount++) {

					securityQues = new CpResetSecurityAnswer();
					securityQues.setCustRefNo(customerDetails.getCustomerReferenceNumber().intValue());
					securityQues.setUserName(user.getUsername());
					securityQues.setProcessDate(new Date());
					securityQues.setQuesStatus("W");

					if (secQuesCount == 0) {
						securityQues.setSecurityQues(resetQuesAns.get(secQuesCount).getvQuesEN());
						securityQues.setSecurityAns(user.getAns1());
						secQuesAnsList.add(securityQues);
						continue;
					}
					if (secQuesCount == 1) {
						securityQues.setSecurityQues(resetQuesAns.get(secQuesCount).getvQuesEN());
						securityQues.setSecurityAns(user.getAns2());
						secQuesAnsList.add(securityQues);
						continue;
					}
					if (secQuesCount == 2) {
						securityQues.setSecurityQues(resetQuesAns.get(secQuesCount).getvQuesEN());
						securityQues.setSecurityAns(user.getAns3());
						secQuesAnsList.add(securityQues);
						continue;
					}
					if (secQuesCount == 3) {
						securityQues.setSecurityQues(resetQuesAns.get(secQuesCount).getvQuesEN());
						securityQues.setSecurityAns(user.getAns4());
						secQuesAnsList.add(securityQues);
						continue;
					}
					if (secQuesCount == 4) {
						securityQues.setSecurityQues(resetQuesAns.get(secQuesCount).getvQuesEN());
						securityQues.setSecurityAns(user.getAns5());
						secQuesAnsList.add(securityQues);
						continue;
					}

					if (secQuesCount == 5) {
						securityQues.setSecurityQues(resetQuesAns.get(secQuesCount).getvQuesEN());
						securityQues.setSecurityAns(user.getAns6());
						secQuesAnsList.add(securityQues);
						continue;
					}
					if (secQuesCount == 6) {
						securityQues.setSecurityQues(resetQuesAns.get(secQuesCount).getvQuesEN());
						securityQues.setSecurityAns(user.getAns7());
						secQuesAnsList.add(securityQues);
						continue;
					}
					if (secQuesCount == 7) {
						securityQues.setSecurityQues(resetQuesAns.get(secQuesCount).getvQuesEN());
						securityQues.setSecurityAns(user.getAns8());
						secQuesAnsList.add(securityQues);
						continue;
					}
					if (secQuesCount == 8) {
						securityQues.setSecurityQues(resetQuesAns.get(secQuesCount).getvQuesEN());
						securityQues.setSecurityAns(user.getAns9());
						secQuesAnsList.add(securityQues);
						continue;
					}
					if (secQuesCount == 9) {
						securityQues.setSecurityQues(resetQuesAns.get(secQuesCount).getvQuesEN());
						securityQues.setSecurityAns(user.getAns10());
						secQuesAnsList.add(securityQues);
						continue;
					}

				}
			
				// save user registration audit trail
				registrationTrail.setPolicyNo(user.getPolicyNo());
				registrationTrail.setIdentificationType(user.getIdenCode());
				registrationTrail.setCustRefNo(customerDetails.getCustomerReferenceNumber().intValue());
				registrationTrail.setRegistrationStatus("PENDING");
				registrationTrail.setRemarks("Verification sent to registered email");
				registrationTrail.setUsername(user.getUsername());
				registrationTrail.setUserType("CUSTOMER");
				registrationTrail.setRegistrationDate(new java.sql.Date(new Date().getTime()));
				
				logger.info("Registration Event Publishing");
				
				//Registration token
				final String token = UUID.randomUUID().toString();
				VerificationToken regToken = new VerificationToken(token);
				regToken.setUser(userInfo);
				userInfo.setVerificationToken(regToken);
				
				//Save user details.
			    customerDao.save(userInfo);
				String username = userInfo.getVuserName();
				logger.info("New:  {} user saved successfuly",username);
				
				// save user security questions
				resetDAO.saveRegistrationQuestions(secQuesAnsList);
				logger.info("User: {} secuirty questions successfully saved",username);
				
				//Save registration audit trails
				registrationAuditService.saveRegistrationAuditTrail(registrationTrail);
				logger.info("User: {} registration audit trails successfully saved",registrationTrail);
				
				//Save Customer details
				CpCustomerDetail cpCustomerDetail = new CpCustomerDetail();
				cpCustomerDetail.setdDob(DateTimeUtil.dateFormat(customerDetails.getEnglishDOB()));
				cpCustomerDetail.setdDobHijri(BSLUtils.isNotNull(customerDetails.getHijriDOB())?DateTimeUtil.dateFormat(customerDetails.getHijriDOB()):null);
				cpCustomerDetail.setGender(customerDetails.getGender());
				cpCustomerDetail.setNcustRefNo(customerDetails.getCustomerReferenceNumber().intValue());
				cpCustomerDetail.setTitle(customerDetails.getTitle());
				cpCustomerDetail.setVemail(customerDetails.getEmailID());
				cpCustomerDetail.setVidType(user.getIdenCode());
				cpCustomerDetail.setVidNo(user.getPolicyNo());
				cpCustomerDetail.setVcustName(customerDetails.getFirstName()+" "+customerDetails.getLastName());
				cpCustomerDetailDAO.saveCustomerDetail(cpCustomerDetail);
				logger.info("User: {} customer detail successfully saved",cpCustomerDetail);
				
				//Publish registration event
				eventPublisher.publishEvent(new OnRegistrationCompleteEvent(userInfo,regToken, request.getLocale(), SecurityLibrary.getAppUrl()));
				redirectStrategy.sendRedirect(request, response, "/registration/email?username=" + user.getUsername() + "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception caught while registration process: {}", e.getCause());
			try {
				redirectStrategy.sendRedirect(request, response, "/registration/failureRegistration?exception=" + e.getCause().getCause().getMessage().replaceAll("''", "") + "");
			} catch (IOException e1) {
				e1.printStackTrace();
				logger.error("Exception caught while forwarding to error page for failure registration process: {}", e1.getCause());
			}
		}

	}

	/**
	 * In order to re-directs to error page
	 * @param exception
	 * @return
	 */
	@GetMapping(value = "/failureRegistration")
	public ModelAndView getFailureRegistrationProcess(@QueryParam("exception") String exception) {
		
		UserRegistration user = new UserRegistration();
		ModelAndView modal = new ModelAndView("registrationfailure", "command", user);
		modal.addObject("exception", exception);
		logger.error("Registration failued due to : {}  forwarding to error page", exception);
		return modal;
	}
	/**
	 * fetch all security questions list
	 * @return
	 */
	@ModelAttribute("secques")
	private List<CpQuestionDetail> getSecurityQuesList() {

		return secQUestions.findAllQuestions();
	}

	/**
	 * @param IDENTYPE
	 * @param policyNo
	 * @return
	 */
	private RegistrationCustomerDTO getCustomerDetails(String language, String idenType, String policyNo) {

		return registrationCustomerService.getCustDetails(language, idenType, policyNo);
	}

	/**
	 * Redirects to registration confirmation page
	 * @param token
	 * @return
	 */
	@GetMapping(value = "/confirmation")
	public ModelAndView registrationConfirmationPage(@QueryParam("token") String token) {
		
		ModelAndView modal = null;
		
		VerificationToken fetchByToken = registrationToken.fetchByToken(token);
		
		CpUserInfo user = fetchByToken.getUser();
		
		UserRegistration registrationUser = new UserRegistration();
		
		String validateVerificationToken = registrationToken.validateVerificationToken(token);
		
		if(validateVerificationToken.equals("valid")) {
			
			logger.info("Verifying registration token: {}",Boolean.TRUE);
			modal = new ModelAndView("registrationconfirmation", "command", registrationUser);
			logger.info("Registration verification received with token: {} : user: {} ",token,user.getVuserName());
			
			registrationCache.put(user.getVuserName(), user);			
			logger.info("User: {} loaded in registartion cache for further validation",user.getVuserName());
			
			modal.addObject("username", user.getVuserName());
			logger.info("Hidden field username: {} has forwarded to view: {} ", user.getVuserName(),"registrationconfirmation");
		}
		
		return modal;
	}
	
	
	@GetMapping(value = "/email")
	public ModelAndView registrationConfirmationEmailPage(@QueryParam("username") String username) {
		
		UserRegistration registrationUser = new UserRegistration();
		ModelAndView modal = new ModelAndView("registrationemailconfirmation", "command", registrationUser);
		modal.addObject("username",username);
		logger.info("Hidden field username: {} has forwarded to view ", username);
		return modal;
	}
	
	@GetMapping(value = "/accountActivationPage")
	public ModelAndView accountActivationPage(@QueryParam("username") String username) {
		
		UserRegistration registrationUser = new UserRegistration();
		ModelAndView modal = new ModelAndView("accountActivationPage", "command", registrationUser);
		modal.addObject("username",username);
		logger.info("Hidden field username: {} has forwarded to view ", username);
		return modal;
	}
	
	/**
	 * @param user
	 * @param result
	 * @param request
	 * @param response
	 */
	@PostMapping(value = "/completeRegistartion")
	public void passwordConfirmation(UserRegistration user, BindingResult result,final HttpServletRequest request, final HttpServletResponse response) {
		
		
		try {
			
			if(user!=null) {
				
				CpUserInfo object = (CpUserInfo) registrationCache.get(user.getUsername());
				object.setVactive("A");
				object.setVpassword(passwordEncoder.encode(user.getPassword()));
				
				List<CpResetSecurityAnswer> fetchUserSecurityQuestion = resetDAO.fetchUserSecurityQuestion(user.getUsername(), "W");
				
				//List<CpResetSecurityAnswer> updatedUserQuesList = new ArrayList<CpResetSecurityAnswer>();
				//Update user security questions
				for(CpResetSecurityAnswer sec :fetchUserSecurityQuestion) {
					sec.setQuesStatus("A");
					resetDAO.update(sec);
					logger.info("User Security questions successfully updated: {}",sec);
				}
				
				 CpRegistrationAuditTrail userRegistrationAuditTrail = registrationAuditService.getUserRegistrationAuditTrail(user.getUsername());
				 userRegistrationAuditTrail.setActivationDate(new Date());
				 userRegistrationAuditTrail.setRegistrationStatus("SUCCESSFUL");
				 userRegistrationAuditTrail.setRemarks("Registration process completed");
				 registrationAuditService.saveORUpdate(userRegistrationAuditTrail);
				 logger.info("Registration audit trail updated: {}",userRegistrationAuditTrail);
				 
				 customerDao.updateUserDetails(object);
				 logger.info("User details updated after successfully registration porcess completed: {}",object);
				 redirectStrategy.sendRedirect(request, response, "/registration/accountActivationPage?username=" + user.getUsername() + "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception caught while updating registration porocess: {}",e.getCause());
		}
	}
	
	@GetMapping(value = "/getSerNo")
	public void getServiceNo() {
		
		try {
			registrationCustomerService.getServiceRequestNo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
