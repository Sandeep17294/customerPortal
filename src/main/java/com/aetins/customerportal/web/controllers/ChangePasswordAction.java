package com.aetins.customerportal.web.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import com.aetins.customerportal.core.faceletstaglib.SecurityLibrary;
import com.aetins.customerportal.core.services.IMailService;
import com.aetins.customerportal.web.common.Constants;
import com.aetins.customerportal.web.common.LoginSession;
import com.aetins.customerportal.web.dto.CpCustomerDetailDTO;
import com.aetins.customerportal.web.dto.CpServiceTypeDTO;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.dto.PasswordRulesDTO;
import com.aetins.customerportal.web.dto.UserDTO;
import com.aetins.customerportal.web.service.CustomerLoginBL;
import com.aetins.customerportal.web.service.ForgetPasswordBL;
import com.aetins.customerportal.web.service.impl.AdminBLImpl;
import com.aetins.customerportal.web.service.impl.CustomerLoginBLImpl;
import com.aetins.customerportal.web.service.impl.ForgetPasswordBLImpl;
import com.aetins.customerportal.web.utils.ApplicationMailer;
import com.aetins.customerportal.web.utils.BSLUtils;
import com.aetins.customerportal.web.utils.ConfigurationConstants;
import com.aetins.customerportal.web.utils.Credential;
import com.aetins.customerportal.web.utils.PasswordRules;

@Controller
@Scope("session")
public class ChangePasswordAction extends BaseAction {

	@Autowired
	private CustomerLoginBL customerLoginBL;

	@Autowired
	private ForgetPasswordBL forgetPasswordBL;

	@Autowired
	LoginSession loginSession;

	private Logger logger = Logger.getLogger(ChangePasswordAction.class);
	private List<CpUserInfoDTO> cpUserList = new ArrayList();

	private UserDTO userDetails = new UserDTO();
	private CpUserInfoDTO cpUserInfoDTO = new CpUserInfoDTO();
	private List<PasswordRulesDTO> rulesList = new ArrayList();
	private PasswordRules passwordRules = new PasswordRules();
	List<CpCustomerDetailDTO> cpCustomerDetailList = new ArrayList();
	@Autowired
	ForgetPasswordBLImpl forgetPasswordBLImpl;

	@Autowired
	AdminBLImpl adminImpl;

	private List<CpServiceTypeDTO> ccMailList = new ArrayList<>();
	private List<String> ccMail = new ArrayList<>();
	private int custRefNo;

	@Autowired
	private ApplicationMailer appMailer;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private IMailService mailService;

	private String userName;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	private String to;
	private String subject;
	private String body;
	private String bodyMsg;

	public ChangePasswordAction() {
	}

	public void init() {
	}
	
	public boolean validatePasswordold(List<CpUserInfoDTO> obj) {
		boolean validateDetail = true;
		if(passwordEncoder.matches(oldPassword, obj.get(0).getVpassword())) {
			validateDetail = true;
		}else {
			validateDetail = false;
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
					"Old Password is not Matching.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
		return validateDetail;
	}


	public void btnSubmit() {

		try {

			logger.info("on click of btnSubmit() Start");
			userName = SecurityLibrary.getFullLoggedInUser().getVuserName();
			userDetails.setUserName(userName);
			cpUserList = customerLoginBL.listUserDetailsForgetPassword(userDetails);
			custRefNo = ((CpUserInfoDTO) cpUserList.get(0)).getNcustRefNo();
			userDetails.setCustRefNo(custRefNo);
			cpCustomerDetailList = forgetPasswordBLImpl.secureDetailsForgetPassword(userDetails);
			rulesList = forgetPasswordBL.listPasswordRules();

			// Not required for new flow
			/*
			 * if (((PasswordRulesDTO)
			 * rulesList.get(0)).getvPasswordSameasUser().equalsIgnoreCase("N")) { if
			 * (userName.equalsIgnoreCase(newPassword)) { FacesMessage message = new
			 * FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
			 * "User name and password should not be same..");
			 * PrimeFaces.current().dialog().showMessageDynamic(message); } }
			 */

			// Password rules need to integrate for registration module
			/*
			 * if (!validateOldPassword()) {
			 * logger.info("on click of btnSubmit() user details failed"); }
			 */
			
			if (!validatePasswordold(cpUserList)) {
				throw new RuntimeException("Old Password do not match.");
			}

			if (!validatePassword()) {
				throw new RuntimeException("Password and Confirmmation Password do not match.");
			}

			if (!validateDetails()) {
				logger.error("Validate customer details while change password");
				throw new RuntimeException("Validate customer details while change password");
			}

			passwordRules.rules(newPassword);

			if (!validatePasswordRule()) {
				logger.error("Validating Password rules ");
				throw new RuntimeException("Validating Password rules failed");
			}

			if (!specialCharValidation()) {
				logger.error("Validating passowrd special characters failed");
				throw new RuntimeException("Validating Password rules failed");
			}
			
			cpUserInfoDTO.setNid(((CpUserInfoDTO) cpUserList.get(0)).getNid());
			cpUserInfoDTO.setVpwMustChange("N");
			cpUserInfoDTO.setVpassword(passwordEncoder.encode(confirmPassword));
			cpUserInfoDTO.setVactive(((CpUserInfoDTO) cpUserList.get(0)).getVactive());
			boolean status = customerLoginBL.updateChangePassWord(cpUserInfoDTO);
			
			body = ("Dear" + " " + userName + "," + "\n" + "\n"
					+ "Your password has been changed successfully. Kindly sign out and log in again." + "\n" + "\n"
					+ "Yours Sincerely," + "\n" + "Salama Customer Portal.");
			
			mailService.sendMail(((CpUserInfoDTO) cpUserList.get(0)).getVemail(), "Password Reset Successful", body);
			if(status) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
						"Password is changed successfully, kindly logout and login again.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Message","Password is changed successfully, kindly logout and login again"));
			//sentEmail();
		} catch (Exception e) {
			logger.debug("Error found in btnSubmit() " + e);
			e.printStackTrace();
			logger.error(e.getMessage());
		}

	}

	public boolean validateOldPassword() {
		boolean validateDetail = true;
		if (oldPassword != null) {
			String password = Credential.crypt(oldPassword);
			cpUserInfoDTO.setVpassword(((CpUserInfoDTO) cpUserList.get(0)).getVpassword());
			if ((password == cpUserInfoDTO.getVpassword()) || (cpUserInfoDTO.getVpassword().equals(password))
					|| (cpUserInfoDTO.getVpassword().equalsIgnoreCase(password))) {
				validateDetail = true;
			} else if ((oldPassword == cpUserInfoDTO.getVpassword())
					|| (cpUserInfoDTO.getVpassword().equals(oldPassword))
					|| (cpUserInfoDTO.getVpassword().equalsIgnoreCase(oldPassword))) {
				validateDetail = true;
			} else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Invalid Old Password.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				validateDetail = false;
			}
		}
		return validateDetail;
	}

	public boolean validatePassword() {
		boolean validateDetail = true;
		if ((newPassword == confirmPassword) || (newPassword.equals(confirmPassword))
				|| (newPassword.equalsIgnoreCase(confirmPassword))) {
			validateDetail = true;
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
					"Password and Confirmmation Password do not match.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
			validateDetail = false;
		}

		return validateDetail;
	}

	public boolean validateDetails() {

		// Portal config map
		Map<String, String> portalConfig = SecurityLibrary._getPortalConfiguration();

		boolean validateDetail = true;
		cpUserInfoDTO.setVpassword(((CpUserInfoDTO) cpUserList.get(0)).getVpassword());
		if (passwordEncoder.matches(newPassword, cpUserInfoDTO.getVpassword())) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
					"New Password seems too similar to previous passwords.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
			validateDetail = false;
		} else if ((newPassword == userName) || (userName.equals(newPassword))
				|| (userName.equalsIgnoreCase(newPassword))) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
					"Password should not be same as User ID.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
			validateDetail = false;
		} else if (newPassword.length() < Integer.parseInt(portalConfig.get(ConfigurationConstants._PORTAL_PASSWORD_MIN_LENGTH))) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
					"Minimum Password length is" + Integer.parseInt(portalConfig.get(ConfigurationConstants._PORTAL_PASSWORD_MIN_LENGTH)));
			PrimeFaces.current().dialog().showMessageDynamic(message);
			validateDetail = false;
		} else {
			validateDetail = true;
		}
		return validateDetail;
	}

	public boolean validatePasswordRule() {
		boolean validateDetail = true;

		// Portal config map
		Map<String, String> portalConfig = SecurityLibrary._getPortalConfiguration();

		int PASSWORD_LENGTH = Integer
				.parseInt(portalConfig.get(ConfigurationConstants._PORTAL_PASSWORD_UPPER_CASE_ALLOWED));
		int PASSOWRD_UPPER_CASE_LENGTH = Integer
				.parseInt(portalConfig.get(ConfigurationConstants._PORTAL_PASSWORD_UPPER_CASE_ALLOWED));
		int PASSWORD_LOWER_CASE_LENGTH = Integer
				.parseInt(portalConfig.get(ConfigurationConstants._PORRATL_PASSOWRD_LOWER_CASE_ALLOWED));
		int PASSWORD_MIN_ALPHABETS = Integer
				.parseInt(portalConfig.get(ConfigurationConstants._PORTAL_PASSWORD_MIN_ALPHA));
		int PASSWORD_MIN_DIGITS = Integer
				.parseInt(portalConfig.get(ConfigurationConstants._PORTAL_PASSWORD_MIN_NUMBERS));

		if (passwordRules.getUpperCase() < PASSOWRD_UPPER_CASE_LENGTH) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
					"Minimum number of upper case alphabets is" + PASSOWRD_UPPER_CASE_LENGTH);
			PrimeFaces.current().dialog().showMessageDynamic(message);
			validateDetail = false;
		} else if (passwordRules.getLowerCase() < PASSWORD_LOWER_CASE_LENGTH) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
					"Minimum number of lower case alphabets is" + PASSWORD_LOWER_CASE_LENGTH);
			PrimeFaces.current().dialog().showMessageDynamic(message);
			validateDetail = false;
		} else if (passwordRules.getAlpha() < PASSWORD_MIN_ALPHABETS) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
					"Minimum number of alphabets is" + PASSWORD_MIN_ALPHABETS);
			PrimeFaces.current().dialog().showMessageDynamic(message);
			validateDetail = false;
		} else if (passwordRules.getDigit() < PASSWORD_MIN_DIGITS) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
					"Minimum number of numeric digit is" + PASSWORD_MIN_DIGITS);
			PrimeFaces.current().dialog().showMessageDynamic(message);
			validateDetail = false;
		} else {
			validateDetail = true;
		}
		return validateDetail;
	}

	public boolean specialCharValidation() {
		boolean validateDetail = true;
		// Portal config map
		Map<String, String> portalConfig = SecurityLibrary._getPortalConfiguration();

		String PORTAL_PASSWORD_SPECIAL_CHARACTER_ALLOWED = portalConfig
				.get(ConfigurationConstants._PORTAL_PASSWORD_ALLOW_SPECIAL_CHARACTER);
		int PORTAL_PASSWORD_MIN_SPECIAL_CHARACTER_ALLOWED = Integer
				.parseInt(portalConfig.get(ConfigurationConstants._PORTAL_PASSWORD_MIN_SPECIAL_CHARACTER));

		if (((PORTAL_PASSWORD_SPECIAL_CHARACTER_ALLOWED == "Y")
				|| ((PORTAL_PASSWORD_SPECIAL_CHARACTER_ALLOWED.equals("Y"))
						|| ((PORTAL_PASSWORD_SPECIAL_CHARACTER_ALLOWED.equalsIgnoreCase("Y"))))))
			if (passwordRules.getSpecialChar() < PORTAL_PASSWORD_MIN_SPECIAL_CHARACTER_ALLOWED) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Minimum Number of Special Characters is" + PORTAL_PASSWORD_MIN_SPECIAL_CHARACTER_ALLOWED);
				PrimeFaces.current().dialog().showMessageDynamic(message);
				validateDetail = false;
			} else {
				validateDetail = true;
			}
		else if (passwordRules.getSpecialChar() == 0) {
			validateDetail = true;
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
					"Special characters are not allowed");
			PrimeFaces.current().dialog().showMessageDynamic(message);
			validateDetail = false;
		}
		return validateDetail;
	}

	public void sentEmail() {
		boolean success = true;
		try {
			logger.info("on click of btnSubmit() updating password");
			cpUserInfoDTO.setNid(((CpUserInfoDTO) cpUserList.get(0)).getNid());
			cpUserInfoDTO.setVpwMustChange("N");
			cpUserInfoDTO.setVpassword(passwordEncoder.encode(confirmPassword));
			cpUserInfoDTO.setVactive(((CpUserInfoDTO) cpUserList.get(0)).getVactive());
			boolean status = customerLoginBL.updateChangePassWord(cpUserInfoDTO);

			if (!status) {
				logger.info("on click of btnSubmit() sending Email failed");
			}

			String custName = SecurityLibrary.getFullLoggedInUser().getVuserName();;
			/*
			 * if (BSLUtils.isNotNull(((CpCustomerDetailDTO)
			 * cpCustomerDetailList.get(0)).getVcustName())) { custName =
			 * ((CpCustomerDetailDTO) cpCustomerDetailList.get(0)).getVcustName(); } else {
			 * custName = "Customer"; }
			 */

			ccMailList = adminImpl.listcCEmail(Constants.CHANGE_PASSWORD);
			logger.info(":::::::::Size of CC Email List is:::::::" + ccMailList.size());

			for (CpServiceTypeDTO dto : ccMailList) {
				ccMail.add(dto.getCcEmail());
			}

			to = ((CpUserInfoDTO) cpUserList.get(0)).getVemail();
			subject = "Password Reset Successful";
			body = ("Dear" + " " + custName + "," + "\n" + "\n"
					+ "Your password has been changed successfully. Kindly sign out and log in again." + "\n" + "\n"
					+ "Yours Sincerely," + "\n" + "Team ALRAJIHI.");

			appMailer.sendMail(to, ccMail, subject, body);

		} catch (Exception e) {
			logger.debug("Error found in btnSubmit() while sending Email or updating password " + e);
			e.printStackTrace();
			success = false;
			logger.error(e.getMessage());
		}
		if (success) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Message","Password is changed successfully, kindly logout and login again"));

		}
	}

	public List<CpUserInfoDTO> getCpUserList() {
		return cpUserList;
	}

	public void setCpUserList(List<CpUserInfoDTO> cpUserList) {
		this.cpUserList = cpUserList;
	}

	public void setCustomerLoginBL(CustomerLoginBLImpl customerLoginBL) {
		this.customerLoginBL = customerLoginBL;
	}

	public UserDTO getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDTO userDetails) {
		this.userDetails = userDetails;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public CpUserInfoDTO getCpUserInfoDTO() {
		return cpUserInfoDTO;
	}

	public void setCpUserInfoDTO(CpUserInfoDTO cpUserInfoDTO) {
		this.cpUserInfoDTO = cpUserInfoDTO;
	}

	public ApplicationMailer getAppMailer() {
		return appMailer;
	}

	public void setAppMailer(ApplicationMailer appMailer) {
		this.appMailer = appMailer;
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

	public void setForgetPasswordBL(ForgetPasswordBLImpl forgetPasswordBL) {
		this.forgetPasswordBL = forgetPasswordBL;
	}

	public List<PasswordRulesDTO> getRulesList() {
		return rulesList;
	}

	public void setRulesList(List<PasswordRulesDTO> rulesList) {
		this.rulesList = rulesList;
	}

	public PasswordRules getPasswordRules() {
		return passwordRules;
	}

	public void setPasswordRules(PasswordRules passwordRules) {
		this.passwordRules = passwordRules;
	}

	public String getBodyMsg() {
		return bodyMsg;
	}

	public void setBodyMsg(String bodyMsg) {
		this.bodyMsg = bodyMsg;
	}

//  
//  public static void main (String args []){
//	  
//	  String a = "89c9703ab98a78d781355cdc9c5ad2c2";
//	  
//	  String password = Credential.crypt(a);
//	  
//	  System.out.println();
//	  
//  }

}