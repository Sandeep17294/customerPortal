package com.aetins.customerportal.web.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aetins.customerportal.core.services.IMailService;
import com.aetins.customerportal.web.common.ForgetPasswordOTPGenerater;
import com.aetins.customerportal.web.dto.CpCustomerDetailDTO;
import com.aetins.customerportal.web.dto.CpServiceTypeDTO;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.dto.PasswordRulesDTO;
import com.aetins.customerportal.web.dto.UserDTO;
import com.aetins.customerportal.web.service.AdminBL;
import com.aetins.customerportal.web.service.CustomerLoginBL;
import com.aetins.customerportal.web.service.ForgetPasswordBL;
import com.aetins.customerportal.web.service.impl.AdminBLImpl;
import com.aetins.customerportal.web.service.impl.CustomerLoginBLImpl;
import com.aetins.customerportal.web.service.impl.ForgetPasswordBLImpl;
import com.aetins.customerportal.web.utils.ApplicationMailer;
import com.aetins.customerportal.web.utils.BSLUtils;
import com.aetins.customerportal.web.utils.Credential;
import com.aetins.customerportal.web.utils.DateUtil;

@Controller
@Scope("session")
public class ForgetPasswordAction extends BaseAction {
	
	Logger logger = Logger.getLogger(ForgetPasswordAction.class);

	@Autowired
	private IMailService mailService;
	
	@Autowired
	ApplicationMailer appMailer;
	@Autowired
	CustomerLoginBL customerLoginBL;
	@Autowired
	ForgetPasswordBL forgetPasswordBLImpl;
	@Autowired
	AdminBL adminbl;
	
	List<CpUserInfoDTO> cpUserList = new ArrayList();
	List<CpCustomerDetailDTO> cpCustomerDetailList = new ArrayList();
	
	UserDTO userDetails = new UserDTO();
	CpUserInfoDTO cpUserInfoDTO = new CpUserInfoDTO();
	ForgetPasswordOTPGenerater passwordOTPGenerater = new ForgetPasswordOTPGenerater();
	
	private String userName;
	private String prefName;
	private String idenType;
	private String idenNo;
	private String question1;
	private String answer1;
	private String question2;
	private String answer2;
	private int imageId;
	private int custRefNo;
	private boolean showImageBlock;
	public int displayImageCount = 3;
	private String to;
	private String subject;
	private String body;
	private String bodyMsg;
	
	@Autowired
	AdminBLImpl adminImpl;
	private PasswordRulesDTO passwordRulesDTO;
	private List<PasswordRulesDTO> rulesList = new ArrayList();
	private List<CpServiceTypeDTO> ccMailList = new ArrayList<>();
	private List<String> ccMail = new ArrayList<>();
	private String dob;

	public ForgetPasswordAction() {
	}

	public void init() {
	}

	public void fetchQuestion() {
		try {
			logger.info("on blur of fetchQuestion() Start");
			if (BSLUtils.isNotNull(userName)) {
				cpUserList = customerLoginBL.fetchForgetPasswordQuestion(userName);
				if ((cpUserList == null) || (cpUserList.isEmpty()) || (cpUserList.size() < 1)) {
					displayErrorMessageWithClientId("forgetPassword.error.userName", "allErr");
					return;
				}

				setQuestion1(((CpUserInfoDTO) cpUserList.get(0)).getVquestion1());
				setQuestion2(((CpUserInfoDTO) cpUserList.get(0)).getVquestion2());
			}
		} catch (Exception e) {
			logger.debug("Error found in fetchQuestion() " + e);
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	public String btnVerify() {
		try {
			logger.info(":::::::on click of btnVerify() Start:::::::::");
			userDetails.setUserName(userName);
			cpUserList = customerLoginBL.listUserDetailsForgetPassword(userDetails);
			custRefNo = ((CpUserInfoDTO) cpUserList.get(0)).getNcustRefNo();
			userDetails.setCustRefNo(custRefNo);
			cpCustomerDetailList = forgetPasswordBLImpl.secureDetailsForgetPassword(userDetails);

			logger.info(":::::::: Size of Customer detail list is ::::::::" + cpCustomerDetailList.size());

			if (BSLUtils.isNull(idenType)) {
				logger.info("on click of btnVerify() user details idenType failed: " + idenType);
				return "";
			}

			if ((BSLUtils.isNotNull(idenNo)) && (idenType.equals("POLICYNO")) && (!validateIdenNo())) {
				logger.info("on click of btnVerify() user details Plan No failed: " + idenNo);
				return "";
			}

			if ((BSLUtils.isNotNull(dob)) && (idenType.equals("DOB")) && (!validateDob())) {
				logger.info("on click of btnVerify() user details DOB failed: " + dob);
				return "";
			}

			if (!answerValidation()) {
				logger.info("on click of btnVerify() user details answer failed");
				return "";
			}
			sentOTP();
		} catch (Exception e) {
			logger.debug("Error found in btnVerify() " + e);
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		return "";
	}

	public void clearValues() {
		userName = null;
		idenNo = null;
		answer1 = null;
		answer2 = null;
		imageId = 0;
		displayImageCount = 3;
		idenType = null;
		question1 = null;
		question2 = null;
		showImageBlock = false;
		bodyMsg = null;
		dob = null;
	}

	public boolean validateIdenType() {
		boolean validateDetail = true;
		if ((idenType == ((CpCustomerDetailDTO) cpCustomerDetailList.get(0)).getVidType())
				|| (((CpCustomerDetailDTO) cpCustomerDetailList.get(0)).getVidType().equals(idenType))
				|| (((CpCustomerDetailDTO) cpCustomerDetailList.get(0)).getVidType().equalsIgnoreCase(idenType))) {
			validateDetail = true;
		} else {
			logger.info(":::Error occured in ValidateIdenType Method ::::::::::");
			displayErrorMessage("forgetPassword.error.idType");
			validateDetail = false;
		}
		logger.info("::::::: Validation status for Identification Type :::::::" + validateDetail);
		return validateDetail;
	}

	public boolean validateIdenNo() {
		boolean validateDetail = true;
		if ((idenNo == ((CpCustomerDetailDTO) cpCustomerDetailList.get(0)).getNidNo())
				|| (((CpCustomerDetailDTO) cpCustomerDetailList.get(0)).getNidNo().equals(idenNo))
				|| (((CpCustomerDetailDTO) cpCustomerDetailList.get(0)).getNidNo().equalsIgnoreCase(idenNo))) {
			validateDetail = true;
		} else {
			logger.info(":::Error occured in ValidateIdenNo Method ::::::::::");
			displayErrorMessageWithClientId("forgetPassword.error.planNo", "allErr");
			validateDetail = false;
		}
		logger.info("::::::: Validation status for Identification No :::::::" + validateDetail);
		return validateDetail;
	}

	public boolean validateDob() {
		boolean validateDetail = true;
		Date convertDob = DateUtil.toUtilDate(dob, "dd/MM/yyyy");
		if (DateUtil.toString(DateUtil.toSqlDate(convertDob)).equals(DateUtil.toString(((CpCustomerDetailDTO) cpCustomerDetailList.get(0)).getdDob()))) {
			validateDetail = true;
		} else {
			logger.info(":::Error occured in ValidateDOB Method ::::::::::");
			displayErrorMessageWithClientId("forgetPassword.error.dob", "allErr");
			validateDetail = false;
		}
		logger.info("::::::: Validation status for DOB :::::::" + validateDetail);
		return validateDetail;
	}

	public boolean answerValidation() {
		boolean validateDetail = true;

		if ((!((CpUserInfoDTO) cpUserList.get(0)).getVanswer1().equalsIgnoreCase(answer1))
				|| (!((CpUserInfoDTO) cpUserList.get(0)).getVanswer2().equalsIgnoreCase(answer2))) {
			displayImageCount -= 1;
			if (displayImageCount <= 0) {
				validateDetail = false;
				displayErrorMessageWithClientId("forgetPassword.answers.error", "allErr");
			} else if (displayImageCount > 0) {
				validateDetail = false;
				displayErrorMessageWithClientId("forgetPassword.answers.error", "allErr");
			}
		}
		logger.info("::::::: Validation status for Security Answer :::::::" + validateDetail);
		return validateDetail;
	}

	public String sentOTP() {
		try {
			logger.info("on click of btnVerify() updating password");
			String pass = passwordOTPGenerater.generateRandomString();
			rulesList = adminImpl.listPasswordRules();
			Iterator<PasswordRulesDTO> itPasswordRule = rulesList.iterator();
			while (itPasswordRule.hasNext()) {
				passwordRulesDTO = ((PasswordRulesDTO) itPasswordRule.next());
				if (passwordRulesDTO.getvChangePassReq().equalsIgnoreCase("y")) {
					cpUserInfoDTO.setVactive("C");
				} else {
					cpUserInfoDTO.setVactive("A");
				}
			}

			// For CC Attachment

			// ccMailList = adminbl.listcCEmail(Constants.FORGET_PASSWORD);

			/*
			 * for(CpServiceTypeDTO dto : ccMailList){ ccMail.add(dto.getCcEmail()); }
			 */

			cpUserInfoDTO.setNid(((CpUserInfoDTO) cpUserList.get(0)).getNid());
			cpUserInfoDTO.setVpassword((Credential.crypt(pass)));
			boolean status = customerLoginBL.updateForgetPassWord(cpUserInfoDTO);
			if (!status) {
				logger.info("on click of btnVerify() sending Email failed");
				return "";
			}
			String custName = ((CpCustomerDetailDTO) cpCustomerDetailList.get(0)).getVcustName();
			if (BSLUtils.isNull(custName)) {
				custName = "Customer";
			}
			to = ((CpUserInfoDTO) cpUserList.get(0)).getVemail();
			logger.info("::::::Message sent to :::::::" + to);
			subject = text.getString("forgetPassword.email.subject");
			body = (text.getString("registrationAction.login.confirmationEmailSal") + " " + custName + "," + "\n" + "\n"
					+ text.getString("registrationAction.forget.line1") + "\n" + "\n"
					+ text.getString("registrationAction.forget.line2") + pass + "\n" + "\n"
					+ text.getString("registrationAction.forget.line3") + "\n" + "\n" + "\n" + "\n"
					+ text.getString("registrationAction.forget.line4") + "\n"
					+ text.getString("registrationAction.forget.line5"));
			// appMailer.sendMail(to, ccMail ,subject , body);
			mailService.sendMail(to, subject, body+"");
			
			//appMailer.sendMail(to, subject, body);
			clearValues();
			//RequestContext requestContext = RequestContext.getCurrentInstance();
			PrimeFaces.current().executeScript("$('#services-1').modal('show');");
			setBodyMsg(text.getString("forgetPassword.succes"));

		} catch (Exception e) {
			logger.debug("Error found in btnVerify() while sending Email or updating password " + e);
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		return "";
	}

	public void setForgetPasswordBLImpl(ForgetPasswordBLImpl forgetPasswordBLImpl) {
		this.forgetPasswordBLImpl = forgetPasswordBLImpl;
	}

	public List<CpCustomerDetailDTO> getCpCustomerDetailList() {
		return cpCustomerDetailList;
	}

	public void setCpCustomerDetailList(List<CpCustomerDetailDTO> cpCustomerDetailList) {
		this.cpCustomerDetailList = cpCustomerDetailList;
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

	public String getIdenType() {
		return idenType;
	}

	public void setIdenType(String idenType) {
		this.idenType = idenType;
	}

	public String getIdenNo() {
		return idenNo;
	}

	public void setIdenNo(String idenNo) {
		this.idenNo = idenNo;
	}

	public String getQuestion1() {
		return question1;
	}

	public void setQuestion1(String question1) {
		this.question1 = question1;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getQuestion2() {
		return question2;
	}

	public void setQuestion2(String question2) {
		this.question2 = question2;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getPrefName() {
		return prefName;
	}

	public void setPrefName(String prefName) {
		this.prefName = prefName;
	}

	public int getCustRefNo() {
		return custRefNo;
	}

	public void setCustRefNo(int custRefNo) {
		this.custRefNo = custRefNo;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public boolean isShowImageBlock() {
		return showImageBlock;
	}

	public void setShowImageBlock(boolean showImageBlock) {
		this.showImageBlock = showImageBlock;
	}

	public int getDisplayImageCount() {
		return displayImageCount;
	}

	public void setDisplayImageCount(int displayImageCount) {
		this.displayImageCount = displayImageCount;
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

	public CpUserInfoDTO getCpUserInfoDTO() {
		return cpUserInfoDTO;
	}

	public void setCpUserInfoDTO(CpUserInfoDTO cpUserInfoDTO) {
		this.cpUserInfoDTO = cpUserInfoDTO;
	}

	public ForgetPasswordOTPGenerater getPasswordOTPGenerater() {
		return passwordOTPGenerater;
	}

	public void setPasswordOTPGenerater(ForgetPasswordOTPGenerater passwordOTPGenerater) {
		this.passwordOTPGenerater = passwordOTPGenerater;
	}

	public ApplicationMailer getAppMailer() {
		return appMailer;
	}

	public void setAppMailer(ApplicationMailer appMailer) {
		this.appMailer = appMailer;
	}

	public String getBodyMsg() {
		return bodyMsg;
	}

	public void setBodyMsg(String bodyMsg) {
		this.bodyMsg = bodyMsg;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}
}