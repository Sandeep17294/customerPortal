package com.aetins.customerportal.web.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aetins.customerportal.core.events.pswdManagement.OnForgotPasswordCompleteEvent;
import com.aetins.customerportal.core.events.registration.OnRegistrationCompleteEvent;
import com.aetins.customerportal.core.faceletstaglib.SecurityLibrary;
import com.aetins.customerportal.core.pojo.ForgotPassword;
import com.aetins.customerportal.core.pojo.UserRegistration;
import com.aetins.customerportal.web.audittrails.service.PasswordManagementService;
import com.aetins.customerportal.web.dao.CpCustomerDetailDAO;
import com.aetins.customerportal.web.dao.CpUserInfoDAO;
import com.aetins.customerportal.web.dao.CustomerLoginDAO;
import com.aetins.customerportal.web.dto.CpServerSettingDTO;
import com.aetins.customerportal.web.entity.CpCustomerDetail;
import com.aetins.customerportal.web.entity.CpPasswordAuditTrails;
import com.aetins.customerportal.web.entity.CpRegistrationAuditTrail;
import com.aetins.customerportal.web.entity.CpResetSecurityAnswer;
import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.entity.ForgotVerificationToken;
import com.aetins.customerportal.web.service.AdminBL;
import com.aetins.customerportal.web.service.ForgotVerifyToken;
import com.aetins.customerportal.web.utils.BSLUtils;
import com.aetins.customerportal.web.utils.DateUtil;

/**
 * To handle all the request with respective to forgot password
 * 
 * @author avinash
 *
 */
@Controller
@RequestMapping(value = "/pswd")
public class ForgotPasswordController {

	private Logger _LOGGER = LoggerFactory.getLogger(ForgotPasswordController.class);

	@Autowired
	private CustomerLoginDAO customerDao;

	@Autowired
	CpUserInfoDAO cpUserInfoDAO;

	@Autowired
	private CpCustomerDetailDAO cpCustomerDetailDAO;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	private PasswordManagementService passwordService;

	@Autowired
	private ForgotVerifyToken forgotPasswordVerification;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	/**
	 * In order to re-directs to forgot password page
	 * 
	 * @param exception
	 * @return
	 */
	@GetMapping(value = "/forgot")
	public ModelAndView getForgotPswdView() {
		ForgotPassword forgotPswd = new ForgotPassword();
		ModelAndView modal = new ModelAndView("forgotps", "command", forgotPswd);
		_LOGGER.info("Forgot password page invoked");
		return modal;
	}

    private List<CpServerSettingDTO> cpServerSettingDTOList;
	
	@Autowired
	AdminBL adminImpl;
	
	/**
	 * To crater incoming request in order to validate respective details
	 * 
	 * @param pswd
	 * @param result
	 * @param request
	 * @param response
	 */
	@PostMapping(value = "/recover")
	public void forgotPasswordToken(ForgotPassword pswd, BindingResult result, final HttpServletRequest request,
			final HttpServletResponse response) {

		try {	
			
			if (pswd != null) {
				boolean isMatched = false;
				String errorMsg = "";
				_LOGGER.info("Forgot password dto: {}", pswd);
				cpServerSettingDTOList = null;
				cpServerSettingDTOList = new ArrayList<CpServerSettingDTO>();
				cpServerSettingDTOList = adminImpl.fetchDownTime();
				
				CpUserInfo cpUserInfo = null;
				cpUserInfo = new CpUserInfo();
				cpUserInfo = cpUserInfoDAO.getCpUserInfo(pswd.getUsername());
				if(cpUserInfo.getVgroup().equalsIgnoreCase("U")) {
					if(cpServerSettingDTOList.get(0).getStatus().equalsIgnoreCase("A")) {
						_LOGGER.info("Forgot Password failure for username: {}", cpServerSettingDTOList.get(0).getStatus().equalsIgnoreCase("A"));
						 DateFormat df = new SimpleDateFormat("dd/MM/yy");
					     System.out.println(df.format(cpServerSettingDTOList.get(0).getvEffectTill()));
					     errorMsg = "Dear User portal is under maintaince from"+" "+(df.format(cpServerSettingDTOList.get(0).getvEffectiveFrom()))+" "+cpServerSettingDTOList.get(0).getdStartTime()+" "+"to"+" "+(df.format(cpServerSettingDTOList.get(0).getvEffectTill()))+" "+cpServerSettingDTOList.get(0).getdEndTime()+".";
						redirectStrategy.sendRedirect(request, response, "/pswd/failure1?errorMsg=" + errorMsg + "");
					}else {
						if ((BSLUtils.isNotNull(pswd.getUsername()) && BSLUtils.isNotNull(pswd.getEmail()))
								|| (BSLUtils.isNotNull(pswd.getUsername()) && BSLUtils.isNotNull(pswd.getDob()))) {

							CpUserInfo userInfo = cpUserInfoDAO.getCpUserInfo(pswd.getUsername());
							_LOGGER.info("Forgot Password username:{} ", userInfo.getVuserName());

							// CpCustomerDetail customerDetail =
							// cpCustomerDetailDAO.findByCustRef(userInfo.getNcustRefNo());

							/*
							 * if(BSLUtils.isNotNull(pswd.getPolicyNumber())) { errorMsg =
							 * customerDetail.getVidNo().equals(pswd.getPolicyNumber())
							 * ?"Given details policy number matched":"Given policy number: "+pswd.
							 * getPolicyNumber()+" is not matched"; isMatched =
							 * customerDetail.getVidNo().equals(pswd.getPolicyNumber()); }
							 */
							if (BSLUtils.isNotNull(pswd.getDob())) {
								errorMsg = userInfo.getDdob().toString().split(" ")[0].equals(pswd.getDob())
										? "Given details D.O.B matched"
										: "Given D.O.B " + pswd.getDob() + " is not matched";
								isMatched = userInfo.getDdob().toString().split(" ")[0].equals(pswd.getDob());
							}
							if (BSLUtils.isNotNull(pswd.getEmail())) {
								errorMsg = userInfo.getVemail().equals(pswd.getEmail())
										? "Given details email matched"
										: "Given email" + pswd.getEmail() + " is not matched";
								isMatched = userInfo.getVemail().equals(pswd.getEmail());
							}
							if (isMatched) {
								_LOGGER.info("Forgot Password success email link forwarded to username: {}, Email: {}",
										userInfo.getVuserName(), userInfo.getVemail());

								// forgot password token
								final String token = UUID.randomUUID().toString();
								ForgotVerificationToken regToken = new ForgotVerificationToken(token);

								CpPasswordAuditTrails pswdAuditTrails = new CpPasswordAuditTrails();
								pswdAuditTrails.setCustRefNo(userInfo.getNcustRefNo());
								pswdAuditTrails.setUsername(userInfo.getVuserName());
								pswdAuditTrails.setToken(regToken);
								pswdAuditTrails.setStatus("PENDING");
								pswdAuditTrails.setRemarks("Verification sent to registered email");
								regToken.setUser(pswdAuditTrails);
								passwordService.savePasswordAuditTrail(pswdAuditTrails);

								//Publish registration event
								eventPublisher.publishEvent(new OnForgotPasswordCompleteEvent(userInfo, regToken,request.getLocale(), SecurityLibrary.getAppUrl()));
								redirectStrategy.sendRedirect(request, response,"/pswd/success?username=" + pswd.getUsername() + "");

							} else {
								_LOGGER.info("Forgot Password failure for username: {}", userInfo.getVuserName());
								redirectStrategy.sendRedirect(request, response, "/pswd/failure?errorMsg=" + errorMsg + "");
							}
						}
		
					}
				}else {
					if ((BSLUtils.isNotNull(pswd.getUsername()) && BSLUtils.isNotNull(pswd.getEmail()))
							|| (BSLUtils.isNotNull(pswd.getUsername()) && BSLUtils.isNotNull(pswd.getDob()))) {

						CpUserInfo userInfo = cpUserInfoDAO.getCpUserInfo(pswd.getUsername());
						_LOGGER.info("Forgot Password username:{} ", userInfo.getVuserName());

						// CpCustomerDetail customerDetail =
						// cpCustomerDetailDAO.findByCustRef(userInfo.getNcustRefNo());

						/*
						 * if(BSLUtils.isNotNull(pswd.getPolicyNumber())) { errorMsg =
						 * customerDetail.getVidNo().equals(pswd.getPolicyNumber())
						 * ?"Given details policy number matched":"Given policy number: "+pswd.
						 * getPolicyNumber()+" is not matched"; isMatched =
						 * customerDetail.getVidNo().equals(pswd.getPolicyNumber()); }
						 */
						if (BSLUtils.isNotNull(pswd.getDob())) {
							errorMsg = userInfo.getDdob().toString().split(" ")[0].equals(pswd.getDob())
									? "Given details D.O.B matched"
									: "Given D.O.B " + pswd.getDob() + " is not matched";
							isMatched = userInfo.getDdob().toString().split(" ")[0].equals(pswd.getDob());
						}
						if (BSLUtils.isNotNull(pswd.getEmail())) {
							errorMsg = userInfo.getVemail().equals(pswd.getEmail())
									? "Given details email matched"
									: "Given email" + pswd.getEmail() + " is not matched";
							isMatched = userInfo.getVemail().equals(pswd.getEmail());
						}
						if (isMatched) {
							_LOGGER.info("Forgot Password success email link forwarded to username: {}, Email: {}",
									userInfo.getVuserName(), userInfo.getVemail());

							// forgot password token
							final String token = UUID.randomUUID().toString();
							ForgotVerificationToken regToken = new ForgotVerificationToken(token);

							CpPasswordAuditTrails pswdAuditTrails = new CpPasswordAuditTrails();
							pswdAuditTrails.setCustRefNo(userInfo.getNcustRefNo());
							pswdAuditTrails.setUsername(userInfo.getVuserName());
							pswdAuditTrails.setToken(regToken);
							pswdAuditTrails.setStatus("PENDING");
							pswdAuditTrails.setRemarks("Verification sent to registered email");
							regToken.setUser(pswdAuditTrails);
							passwordService.savePasswordAuditTrail(pswdAuditTrails);
							//Publish registration event
							eventPublisher.publishEvent(new OnForgotPasswordCompleteEvent(userInfo, regToken,request.getLocale(), SecurityLibrary.getAppUrl()));
							redirectStrategy.sendRedirect(request, response,"/pswd/success?username=" + pswd.getUsername() + "");
						} else {
							_LOGGER.info("Forgot Password failure for username: {}", userInfo.getVuserName());
							redirectStrategy.sendRedirect(request, response, "/pswd/failure?errorMsg=" + errorMsg + "");
						}
					}				
				}
		      }
		} catch (Exception e) {
			_LOGGER.error("Exception caught while performing forgot password: {}", e.getCause());
			e.printStackTrace();
		}

	}

	/**
	 * In order to re-directs to forgot password failure page
	 * 
	 * @param exception
	 * @return
	 */
	@GetMapping(value = "/failure")
	public ModelAndView getForgotPswdFailureView(@QueryParam("errorMsg") String errorMsg) {
		ForgotPassword forgotPswd = new ForgotPassword();
		forgotPswd.setErrorMsg(errorMsg);
		ModelAndView modal = new ModelAndView("forgotPswdfailure", "command", forgotPswd);
		modal.addObject("errorMsg", errorMsg);
		_LOGGER.info("Forgot password failure page invoked");
		return modal;
	}
	
	
	/**
	 * In order to re-directs to forgot password failure page
	 * 
	 * @param exception
	 * @return
	 */
	@GetMapping(value = "/failure1")
	public ModelAndView getForgotPswdFailureView1(@QueryParam("errorMsg") String errorMsg) {
		ForgotPassword forgotPswd = new ForgotPassword();
		forgotPswd.setErrorMsg(errorMsg);
		ModelAndView modal = new ModelAndView("forgotPswdfailure", "command", forgotPswd);
		modal.addObject("errorMsg", errorMsg);
		_LOGGER.info("Forgot password failure page invoked");
		return modal;
	}


	/**
	 * In order to re-directs to forgot password failure page
	 * 
	 * @param exception
	 * @return
	 */
	@GetMapping(value = "/success")
	public ModelAndView getForgotPswdSuccessView(@QueryParam("username") String username) {
		ForgotPassword forgotPswd = new ForgotPassword();
		forgotPswd.setErrorMsg(username);
		ModelAndView modal = new ModelAndView("forgotpswdconfrim", "command", forgotPswd);
		modal.addObject("username", username);
		_LOGGER.info("Forgot password success page invoked");
		return modal;
	}

	/**
	 * Redirects to registration confirmation page
	 * 
	 * @param token
	 * @return
	 */
	@GetMapping(value = "/reset")
	public ModelAndView registrationConfirmationPage(@QueryParam("token") String token) {

		ModelAndView modal = null;

		ForgotVerificationToken fetchByToken = forgotPasswordVerification.fetchByToken(token);
		CpPasswordAuditTrails user = fetchByToken.getUser();
		ForgotPassword registrationUser = new ForgotPassword();
		String validateVerificationToken = forgotPasswordVerification.validateVerificationToken(token);

		if (validateVerificationToken.equals("valid")) {

			_LOGGER.info("Forgot password token: {}", Boolean.TRUE);
			modal = new ModelAndView("forgotpswdupdate", "command", registrationUser);
			_LOGGER.info("Forgot password verification received with token: {} : user: {} ", token, user.getUsername());
			modal.addObject("username", user.getUsername());
			_LOGGER.info("Hidden field username: {} has forwarded to view: {} ", user.getUsername(),"registrationconfirmation");
		}

		return modal;
	}

	/**
	 * @param user
	 * @param result
	 * @param request
	 * @param response
	 */
	@PostMapping(value = "/complete")
	public void passwordConfirmation(ForgotPassword user, BindingResult result, final HttpServletRequest request,
			final HttpServletResponse response) {

		try {

			if (user != null && (BSLUtils.isNotNull(user.getPswd()) && BSLUtils.isNotNull(user.getConfPswd()))) {

				if (user.getPswd().equals(user.getConfPswd())) {

					CpPasswordAuditTrails pswdAuditTrails = passwordService.finbByUserName(user.getUsername());
					pswdAuditTrails.setStatus("SUCCESSFUL");
					pswdAuditTrails.setRemarks("Forgot process completed");
					passwordService.updatePasswordAuditTrail(pswdAuditTrails);
					_LOGGER.info("Forgot password is audit trails updated user: {}",pswdAuditTrails.getUsername());
					CpUserInfo userInfo = cpUserInfoDAO.getCpUserInfo(pswdAuditTrails.getUsername());
					userInfo.setVpassword(passwordEncoder.encode(user.getConfPswd()));
					customerDao.updateUserDetails(userInfo);
					_LOGGER.info("User details updated after successfully forgot password porcess completed: {}",userInfo.getVuserName());
					redirectStrategy.sendRedirect(request, response,"/pswd/activation?username=" + user.getUsername() + "");
				} else {
					String errorMsg = "Password: " + user.getPswd() + ", Confirm Password: " + user.getConfPswd() + "";
					_LOGGER.error("Forgot Password: {}", errorMsg);
					redirectStrategy.sendRedirect(request, response, "/pswd/notmatch?errorMsg=" + errorMsg + "");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			_LOGGER.error("Exception caught while updating registration porocess: {}", e.getCause());
		}
	}

	/**
	 * In order to re-directs to forgot password failure page
	 * 
	 * @param exception
	 * @return
	 */
	@GetMapping(value = "/notmatch")
	public ModelAndView getForgotPswdActivationView(@QueryParam("errorMsg") String errorMsg) {
		ForgotPassword forgotPswd = new ForgotPassword();
		forgotPswd.setErrorMsg(errorMsg);
		ModelAndView modal = new ModelAndView("forgotpswdnotmatch", "command", forgotPswd);
		modal.addObject("errorMsg", errorMsg);
		_LOGGER.info("Forgot password success page invoked");
		return modal;
	}

	/**
	 * In order to re-directs to forgot password failure page
	 * 
	 * @param exception
	 * @return
	 */
	@GetMapping(value = "/activation")
	public ModelAndView getForgotPswdNotMatchView(@QueryParam("username") String username) {
		ForgotPassword forgotPswd = new ForgotPassword();
		forgotPswd.setErrorMsg(username);
		ModelAndView modal = new ModelAndView("forgotpswdactivation", "command", forgotPswd);
		modal.addObject("username", username);
		_LOGGER.info("Forgot password success page invoked");
		return modal;
	}
}
