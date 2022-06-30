package com.aetins.customerportal.web.controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;

import com.aetins.customerportal.core.enums.ROLES;
import com.aetins.customerportal.core.events.otp.OnOTPGenerationCompleteEvent;
import com.aetins.customerportal.core.faceletstaglib.SecurityLibrary;
import com.aetins.customerportal.core.pojo.ActiveUserStore;
import com.aetins.customerportal.core.primefaces.view.GuestPreferences;
import com.aetins.customerportal.core.services.IOTPService;
import com.aetins.customerportal.web.audittrails.service.OTPAuditTrailService;
import com.aetins.customerportal.web.entity.CpOTPAuditTrails;
import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.utils.DateUtil;

@Controller(value = "oTPValidationController")
@Scope("session")
public class OTPValidationController extends BaseAction {

	private static Logger LOGGER = LoggerFactory.getLogger(OTPValidationController.class);

	@Autowired
	private IOTPService otpService;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private OTPAuditTrailService otpAuditTrail;

	@Autowired
	ActiveUserStore activeUserStore;

	// User details
	private String email;
	private String userRole;
	private CpUserInfo user;
	private String username;

	// OTP attempts
	private int noofOTPAttempts = 5;
	private int otpfailedmsgcount = 1;
	private int otpWarnMsgCount = 0;

	// OTP details
	private String otpnumber;
	private int otpnum;
	private boolean renderStartTime;
	private boolean renderStopTime;
	private boolean enableResendOTP;
	private boolean enablevalidateotp;
	private boolean enableresendotp = Boolean.TRUE;
	private int otpTimelimit;
	private int otpTimeInterval;
	private boolean resendOTPFailValidation;
	
	private GrantedAuthority[] userAuthorities;

	public OTPValidationController() {

	}

	@PostConstruct
	public void init() {

		try {
			LOGGER.info("ÖTPValidator init method");

			// Currently logged in user authorities
			userAuthorities = SecurityLibrary.getUserAuthorities();
			// User role
			userRole = SecurityLibrary.getLoggedInUserRoles(userAuthorities);

			otpWarnMsgCount = noofOTPAttempts;
			user = SecurityLibrary.getFullLoggedInUser();
			username = user.getVuserName();
			email = user.getVemail().replaceAll("(?<=.{4}).(?=[^@]*?@)", "*");
			LOGGER.info("OTP validation init loading Username: {}, email: {}",user.getVuserName(),user.getVemail());

			otpTimeInterval = 300;
			otpTimelimit = otpTimeInterval / 60;
			
		} catch (ViewExpiredException e) {
			e.printStackTrace();
			LOGGER.error("Exception caught while loading otpvalidation controller: {}", e.getCause().getCause().getLocalizedMessage());
		}

	}

	@Autowired
	GuestPreferences guest;
	
	/**
	 * Validate the login otp
	 * 
	 * @return
	 */
	public void validateOtp() {

		FacesContext context = FacesContext.getCurrentInstance();
       	
		// Validate the Otp
		otpnum = Integer.parseInt(otpnumber.replaceAll("\\s", ""));

		try {

			if (otpnum >= 0) {
				int serverOtp = otpService.getOtp(username);
				LOGGER.info("OTP Invoked Username: {}, OTP:{} ",username,serverOtp);

				if (serverOtp > 0) {
					if (otpnum == serverOtp) {
						otpService.clearOTP(username);
						guest.fetchtheme();
						if (userRole.equals(ROLES.ROLE_USER.name()))
							context.getExternalContext().redirect(SecurityLibrary.getAppContextPath() + "/userDashboard.app");
						else if (userRole.equals(ROLES.ROLE_ADMIN.name()))
							context.getExternalContext().redirect(SecurityLibrary.getAppContextPath() + "/adminDashboard.app");
						else if (userRole.equals(ROLES.ROLE_BUSINESS_USER.name()))
							context.getExternalContext().redirect(SecurityLibrary.getAppContextPath() + "/businessDashboard.app");

					} else {

						noofOTPAttempts -= 1;
						otpfailedmsgcount += 1;

						if (noofOTPAttempts == 0) {

							List<String> users = activeUserStore.getUsers();

							System.out.println(users.size());

							// Tracking OTP Audit trails
							List<CpOTPAuditTrails> userOTPAuditTrailList = otpAuditTrail.getUserOTPAuditTrail(username);

							if (userOTPAuditTrailList.size() > 0) {
								for (CpOTPAuditTrails otpAudit : userOTPAuditTrailList) {

									// Update if user reached max no of failure OTP attempts with in same date
									if ((DateUtil.toDateString(new java.util.Date(otpAudit.getCreatedDate().getTime()))).equals(DateUtil.toDateString(new java.util.Date()))) {
										otpAudit.setNoOfOTPMaxLimitReached(otpAudit.getNoOfOTPMaxLimitReached() + 1);
										otpAuditTrail.updateOTPAuditTrail(otpAudit);
										LOGGER.info("OTP Audit trail persisted with user: {}, No.of max limits exceeded: {}", username, otpAudit.getNoOfOTPMaxLimitReached());
										 
									} else {
										// Create new OTP audit trail if user exceed max no of failure OTP attempts in next date
										if (new java.util.Date().after((new java.util.Date(otpAudit.getCreatedDate().getTime())))&& otpAudit.getLastUpdatedDate() != null) {
											otpAudit.setNoOfOTPMaxLimitReached(1);
											otpAudit.setNoOfOTPExpriedLimitReached(0);
											otpAuditTrail.saveOTPAuditInAnotherSession(otpAudit);
											LOGGER.info("OTP Audit trail persisted with user: {}, No.of max limits exceeded: {}",username, otpAudit.getNoOfOTPMaxLimitReached());
										}
									}
								}
							} else {

								// Create new OTP Audit trail if doesn't exists
								CpOTPAuditTrails otpAudit = new CpOTPAuditTrails();
								otpAudit.setUsername(username);
								otpAudit.setCustRefNum(user.getNcustRefNo());
								otpAudit.setNoOfOTPMaxLimitReached(1);
								otpAudit.setNoOfOTPExpriedLimitReached(0);
								otpAuditTrail.saveOTPAuditTrail(otpAudit);
							}

							// Invalidate session
							context.getExternalContext().redirect(SecurityLibrary.getAppContextPath() + "/logout");
						}

						if (noofOTPAttempts > 0) {
							resendOTPFailValidation =  Boolean.TRUE;
							if ((noofOTPAttempts != (otpWarnMsgCount - otpfailedmsgcount))&& (otpWarnMsgCount - otpfailedmsgcount) != 0)
								FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!","You have remaining " + noofOTPAttempts + " OTP attempts"));
							else
								FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!","You have remaining " + noofOTPAttempts + " OTP attempts"));
						}
					}
				}
				  else {
					  FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!","Please Enter OTP"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Exception caught while processing OTP validation : {}",e.getCause());
		}finally {
			if(!resendOTPFailValidation) {
				username = null;
				email =null;
				LOGGER.info("OTP validation username: {} email: {} after clearing",username,email);
				
			}
			resendOTPFailValidation=Boolean.FALSE;
		}
	}

	/**
	 * To check whether OTP expires or not
	 */
	public void checkTime() {
		setRenderStopTime(true);
		setRenderStartTime(false);
		setEnableResendOTP(false);
		setEnablevalidateotp(true);

		// Tracking OTP Audit trails
		List<CpOTPAuditTrails> userOTPAuditTrailList = otpAuditTrail.getUserOTPAuditTrail(username);

		if (userOTPAuditTrailList.size() > 0) {
			for (CpOTPAuditTrails otpAudit : userOTPAuditTrailList) {
				// Update if user reached max no of expiry OTP attempts with in same date
				if ((DateUtil.toDateString(new java.util.Date(otpAudit.getCreatedDate().getTime())))
						.equals(DateUtil.toDateString(new java.util.Date()))) {
					otpAudit.setNoOfOTPExpriedLimitReached(otpAudit.getNoOfOTPExpriedLimitReached() + 1);
					otpAuditTrail.updateOTPAuditTrail(otpAudit);
					
					  LOGGER.info("OTP Audit trail persisted with user: {}, No.of expiry limits exceeded: {}", username, otpAudit.getNoOfOTPExpriedLimitReached());
					 
				}

				else {
					// Create new OTP audit trail if user exceed max no of expiry OTP attempts in
					// next date
					if (new java.util.Date().after((new java.util.Date(otpAudit.getCreatedDate().getTime())))
							&& otpAudit.getLastUpdatedDate() != null) {
						otpAudit.setNoOfOTPMaxLimitReached(0);
						otpAudit.setNoOfOTPExpriedLimitReached(1);
						otpAuditTrail.saveOTPAuditInAnotherSession(otpAudit);
						//LOGGER.info("OTP Audit trail persisted with user: {}, No.of expiry limits exceeded: {}, On: {}",username, otpAudit.getNoOfOTPExpriedLimitReached(), otpAudit.getCreatedDate());
					}
				}
			}

		} else {
			// Create new OTP audit trail if doesn't exists
			CpOTPAuditTrails otpAudit = new CpOTPAuditTrails();
			otpAudit.setUsername(username);
			otpAudit.setCustRefNum(user.getNcustRefNo());
			otpAudit.setNoOfOTPExpriedLimitReached(1);
			otpAudit.setNoOfOTPMaxLimitReached(0);
			otpAuditTrail.saveOTPAuditTrail(otpAudit);
			//LOGGER.info("New OTP Audit trail persisted with user: {}, No.of expiry limits exceeded: {}, On: {}",username, otpAudit.getNoOfOTPExpriedLimitReached(), otpAudit.getCreatedDate());
		}

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Your OTP has been expired click Resend"));
	}

	/**
	 * To resend the OTP 
	 */
	public void resendOtp() {
		otpService.clearOTP(username);
		setEnableResendOTP(true);
		setEnablevalidateotp(false);
		eventPublisher.publishEvent(new OnOTPGenerationCompleteEvent(user, request.getLocale()));
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null,"Your new OTP sent which is valid for " + otpTimelimit + " minutes"));
	}
	
	public void cancelOTPValidation() {
		try {
			otpService.clearOTP(username);
			FacesContext context = FacesContext.getCurrentInstance();
			// Invalidate session
			context.getExternalContext().redirect(SecurityLibrary.getAppContextPath() + "/logout");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getOtpnumber() {
		return otpnumber;
	}

	public void setOtpnumber(String otpnumber) {
		this.otpnumber = otpnumber;
	}

	public int getOtpnum() {
		return otpnum;
	}

	public void setOtpnum(int otpnum) {
		this.otpnum = otpnum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public CpUserInfo getUser() {
		return user;
	}

	public void setUser(CpUserInfo user) {
		this.user = user;
	}

	public int getNoofOTPAttempts() {
		return noofOTPAttempts;
	}

	public void setNoofOTPAttempts(int noofOTPAttempts) {
		this.noofOTPAttempts = noofOTPAttempts;
	}

	public int getOtpfailedmsgcount() {
		return otpfailedmsgcount;
	}

	public void setOtpfailedmsgcount(int otpfailedmsgcount) {
		this.otpfailedmsgcount = otpfailedmsgcount;
	}

	public boolean isRenderStartTime() {
		return renderStartTime;
	}

	public void setRenderStartTime(boolean renderStartTime) {
		this.renderStartTime = renderStartTime;
	}

	public boolean isRenderStopTime() {
		return renderStopTime;
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

	public boolean isEnablevalidateotp() {
		return enablevalidateotp;
	}

	public void setEnablevalidateotp(boolean enablevalidateotp) {
		this.enablevalidateotp = enablevalidateotp;
	}

	public boolean isEnableresendotp() {
		return enableresendotp;
	}

	public void setEnableresendotp(boolean enableresendotp) {
		this.enableresendotp = enableresendotp;
	}

	public int getOtpTimelimit() {
		return otpTimelimit;
	}

	public void setOtpTimelimit(int otpTimelimit) {
		this.otpTimelimit = otpTimelimit;
	}

	public int getOtpTimeInterval() {
		return otpTimeInterval;
	}

	public void setOtpTimeInterval(int otpTimeInterval) {
		this.otpTimeInterval = otpTimeInterval;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getOtpWarnMsgCount() {
		return otpWarnMsgCount;
	}

	public void setOtpWarnMsgCount(int otpWarnMsgCount) {
		this.otpWarnMsgCount = otpWarnMsgCount;
	}

	public IOTPService getOtpService() {
		return otpService;
	}

	public void setOtpService(IOTPService otpService) {
		this.otpService = otpService;
	}

	public GrantedAuthority[] getUserAuthorities() {
		return userAuthorities;
	}

	public void setUserAuthorities(GrantedAuthority[] userAuthorities) {
		this.userAuthorities = userAuthorities;
	}

	public boolean isResendOTPFailValidation() {
		return resendOTPFailValidation;
	}

	public void setResendOTPFailValidation(boolean resendOTPFailValidation) {
		this.resendOTPFailValidation = resendOTPFailValidation;
	}

	
	
}
