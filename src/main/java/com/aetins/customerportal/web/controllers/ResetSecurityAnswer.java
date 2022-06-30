package com.aetins.customerportal.web.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aetins.customerportal.web.common.LoginSession;
import com.aetins.customerportal.web.dto.CpOtpSettingsDTO;
import com.aetins.customerportal.web.dto.CpQuestionsDetailDTO;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.dto.PasswordRulesDTO;
import com.aetins.customerportal.web.dto.ResetSecurityAnswerDTO;
import com.aetins.customerportal.web.service.CustomerLoginBL;
import com.aetins.customerportal.web.service.ResetSecurityAnswerBL;
import com.aetins.customerportal.web.service.impl.AdminBLImpl;
import com.aetins.customerportal.web.utils.AppSettingURL;
import com.aetins.customerportal.web.utils.BSLUtils;
import com.aetins.customerportal.web.utils.OTPGenerater;
import com.aetins.customerportal.web.utils.SMSEmailGenerater;



@Controller
@Scope("session")
public class ResetSecurityAnswer extends BaseAction {

	Logger logger = Logger.getLogger(ResetSecurityAnswer.class);
	
	@Autowired
	private SMSEmailGenerater smsEmail;
	
	@Autowired
	CustomerLoginBL customerLoginBL;
	
	@Autowired
	ResetSecurityAnswerBL resetSecurityBL;
	
	@Autowired
	LoginSession loginSession;

	List<CpUserInfoDTO> cpUserList = new ArrayList();
	
	private String userName;
	List<ResetSecurityAnswerDTO> securityQuestionList = new ArrayList<>();
	ResetSecurityAnswerDTO resetSecurityAnswerDTO = new ResetSecurityAnswerDTO();
	private boolean addNewQuestion = false;
	private ResetSecurityAnswerDTO addedQuestion = new ResetSecurityAnswerDTO();
	private AdminBLImpl adminImpl = new AdminBLImpl();
	private List<PasswordRulesDTO> rulesList = new ArrayList<PasswordRulesDTO>();
	private int noOfQuestion;
	private List<CpQuestionsDetailDTO> questionList = new ArrayList<>();
	Map<String, String> cpQuestionList;
	private String bodyMsg;
	//RequestContext requestContext;
	private String userOTP;
	private String msgDestinations;
	private String to;
	private boolean enableResendOTP;
	private boolean renderStartTime;
	private boolean renderStopTime;
	private int OTPTimeLimit;
	public int noOfAttempts;
	private String otpGenenater;
	private OTPGenerater otp = new OTPGenerater();
	private List<CpOtpSettingsDTO> otpSettingsList = new ArrayList<CpOtpSettingsDTO>();
	
	
	private String titleMsg;
	boolean otpValidationstatus = false;
	boolean _questionList = true;
	private String displayname;

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
		FacesContext context = FacesContext.getCurrentInstance();
		//LoginAction homeAction = context.getApplication().evaluateExpressionGet(context, "#{loginAction}", LoginAction.class);
		//userName=homeAction.getVusername();
		//displayname=homeAction.getDisplayName();
		fetchQuestion();
		try {
//			rulesList = adminImpl.listPasswordRules();
//			noOfQuestion = rulesList.get(0).getnQuestionCount();
//			questionList = adminImpl.listSecurityQuestion();
//			OTPTimeLimit = Integer.parseInt(AppSettingURL.Mobile_Timelimit);
//			cpQuestionList = new LinkedHashMap<String, String>();
//			for (CpQuestionsDetailDTO cpQuestionsDetailDAO : questionList) {
//				cpQuestionList.put(cpQuestionsDetailDAO.getQuesEN(), cpQuestionsDetailDAO.getQuesEN());
//			}
//			//userName = loginSession.getInstance().getCustUserName();
//			otpSettingsList = resetSecurityBL.fetchOtpSettings("RESET ANSWER");

		} catch (Exception e) {
			logger.info("Inside catch block of init() for ResetSecurityAnswer" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void sample() {
		String questionStatus = "A";
		try {
			securityQuestionList = resetSecurityBL.fetchSecurityQuestion(userName, questionStatus);
			logger.info("::::::Size of SecurityQuestionList is ::::::::" + securityQuestionList.size());
			if (securityQuestionList.size() > 0) {

			} else {

				ResetSecurityAnswerDTO resetSecurityAnswerDTO1 = new ResetSecurityAnswerDTO();
				resetSecurityAnswerDTO.setSecurityQues(cpUserList.get(0).getVquestion1());
				resetSecurityAnswerDTO.setQuesStatus("A");
				resetSecurityAnswerDTO.setUserName(userName);
				resetSecurityAnswerDTO.setCustRefNo(cpUserList.get(0).getNcustRefNo());
				resetSecurityAnswerDTO.setSecurityAns(cpUserList.get(0).getVanswer1());
				resetSecurityAnswerDTO.setProcessDate(new Date());
				resetSecurityAnswerDTO.setRecentModifiedDate(new Date());
				resetSecurityAnswerDTO1.setProcessDate(new Date());
				resetSecurityAnswerDTO1.setRecentModifiedDate(new Date());
				resetSecurityAnswerDTO1.setCustRefNo(cpUserList.get(0).getNcustRefNo());
				resetSecurityAnswerDTO1.setUserName(userName);
				resetSecurityAnswerDTO1.setQuesStatus("A");
				resetSecurityAnswerDTO1.setSecurityQues(cpUserList.get(0).getVquestion2());
				resetSecurityAnswerDTO1.setSecurityAns(cpUserList.get(0).getVanswer2());
				securityQuestionList.add(resetSecurityAnswerDTO);
				securityQuestionList.add(resetSecurityAnswerDTO1);
			}

		} catch (Exception e) {
			logger.info("Error found in fetchQuestion() " + e.getMessage());
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	public void fetchQuestion() {
		String questionStatus = "A";
		try {
			logger.info("on blur of fetchQuestion() Start");
			if (BSLUtils.isNotNull(userName)) {
				cpUserList = customerLoginBL.fetchForgetPasswordQuestion(userName);
				logger.info("::::::Size of CpUserList is ::::::::" + cpUserList.size());
				if ((cpUserList == null) || (cpUserList.isEmpty()) || (cpUserList.size() < 1)) {
					//displayErrorMessageWithClientId("forgetPassword.error.userName", "allErr");
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
							"Message", "Invalid User Id."));
					setSecurityQuestionList(null);
					return;
				}
			}
			to = cpUserList.get(0).getVemail();
			msgDestinations = AppSettingURL.MOBILE_COUNTRY_CODE + cpUserList.get(0).getVcontactNo();

			securityQuestionList = resetSecurityBL.fetchSecurityQuestion(userName, questionStatus);
			logger.info("::::::Size of SecurityQuestionList is ::::::::" + securityQuestionList.size());
			if (securityQuestionList.size() > 0) {

			} else {

				ResetSecurityAnswerDTO resetSecurityAnswerDTO1 = new ResetSecurityAnswerDTO();
				resetSecurityAnswerDTO.setSecurityQues(cpUserList.get(0).getVquestion1());
				resetSecurityAnswerDTO.setQuesStatus("A");
				resetSecurityAnswerDTO.setUserName(userName);
				resetSecurityAnswerDTO.setCustRefNo(cpUserList.get(0).getNcustRefNo());
				resetSecurityAnswerDTO.setSecurityAns(cpUserList.get(0).getVanswer1());
				resetSecurityAnswerDTO.setProcessDate(new Date());
				resetSecurityAnswerDTO.setRecentModifiedDate(new Date());
				resetSecurityAnswerDTO1.setProcessDate(new Date());
				resetSecurityAnswerDTO1.setRecentModifiedDate(new Date());
				resetSecurityAnswerDTO1.setCustRefNo(cpUserList.get(0).getNcustRefNo());
				resetSecurityAnswerDTO1.setUserName(userName);
				resetSecurityAnswerDTO1.setQuesStatus("A");
				resetSecurityAnswerDTO1.setSecurityQues(cpUserList.get(0).getVquestion2());
				resetSecurityAnswerDTO1.setSecurityAns(cpUserList.get(0).getVanswer2());
				securityQuestionList.add(resetSecurityAnswerDTO);
				securityQuestionList.add(resetSecurityAnswerDTO1);
			}

		} catch (Exception e) {
			logger.info("Error found in fetchQuestion() " + e.getMessage());
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	public String addQuestionDetails() {
		setAddNewQuestion(true);
		return "";
	}

	public void clearQuestionValues() {
		addedQuestion = new ResetSecurityAnswerDTO();
		addedQuestion.setQuesStatus("A");// For new record
	}

	public void deleteQuestion(int index) {
		if (securityQuestionList != null) {
			if (securityQuestionList.size() > 0) {
				securityQuestionList.get(index).setQuesStatus("D");// For delete making status as D
				System.out.println("Deleted");
			}
		}
	}

	public void editQuestion(int index) {
		if (securityQuestionList != null) {
			if (securityQuestionList.size() > 0) {
				securityQuestionList.get(index).setQuesStatus("E");// For editing purpose
				securityQuestionList.get(index).setEditEnable(true);
				System.out.println("Edited");
			}
		}
	}

	public void btnClear() {
		setAddNewQuestion(false);
		_questionList = true;
		System.out.println("panel disabled:" + addNewQuestion);
	}

	public void btnSaveQuestions() {

		addedQuestion.setUserName(userName);
		addedQuestion.setCustRefNo(cpUserList.get(0).getNcustRefNo());
		addedQuestion.setRecentModifiedDate(new Date());
		addedQuestion.setProcessDate(new Date());

		_questionList = validateSecurityQues();
		if (_questionList == true) {
			securityQuestionList.add(addedQuestion);
			setAddNewQuestion(false);
		}
	}

	public boolean validateSecurityQues() {

		for (ResetSecurityAnswerDTO dto : securityQuestionList) {
			if (dto.getSecurityQues().equalsIgnoreCase(addedQuestion.getSecurityQues())) {
				//displayErrorMessageWithClientId("resetAnswer.same.securityQues.error", "allErr");
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
						"Message", "Same question Can not be selected."));
				return false;
			}
		}
		return true;
	}

	public void btnSubmit() {
		try {
			logger.info("::::::: Entering inside btnSubmit():::::::");
			boolean answermodified = false;
			int count = 0;
			if (_questionList == true) {
				for (ResetSecurityAnswerDTO each : securityQuestionList) {
					if (BSLUtils.isNull(each.getQuesStatus())) {
						count++;
					}
					if (BSLUtils.isNotNull(each.getQuesStatus())) {
						if(each.getQuesStatus().equalsIgnoreCase("A") ||each.getQuesStatus().equalsIgnoreCase("E")){
							count++;
						}
					}
				}
				if (count == noOfQuestion) {
					for (ResetSecurityAnswerDTO dto : securityQuestionList) {
						if (BSLUtils.isNotNull(dto.getQuesStatus())) {
							answermodified = true;
						}
					}
					if (answermodified == true) {
						smsGenerator();
						//requestContext = RequestContext.getCurrentInstance();
						PrimeFaces.current().executeScript("$('#OTP-popup').modal('show');");
					} else {
						//requestContext = RequestContext.getCurrentInstance();
						setTitleMsg(text.getString("resetPassword.answer.title.warning.notModified"));
						setBodyMsg(text.getString("resetPassword.answer.bodyMsg.warning.notModified"));
						PrimeFaces.current().executeScript("$('#success-Tab').modal('show');");
					}
				} else {
					//requestContext = RequestContext.getCurrentInstance();
					PrimeFaces.current().executeScript("$('#validation-Tab').modal('show');");
					setBodyMsg(text.getString("resetPassword.minNoof.questionCount"));
				}
			}
		} catch (Exception e) {
			logger.info(":::::inside catch block in btnSubmit() method:::::::" + e.getMessage());
			e.printStackTrace();
		}
	}

	public void validateOTP() {
		if (noOfAttempts > 0) {
			if (userOTP.equalsIgnoreCase(otpGenenater)) {
				resetSecurityBL.updateSecurityQuestion(securityQuestionList); // update security ANswer in tab
				setEnableResendOTP(false);
				setRenderStartTime(false);
				setRenderStopTime(true);
				//RequestContext requestContext = RequestContext.getCurrentInstance();
				PrimeFaces.current().executeScript("$('#OTP-popup').modal('hide');");
				setTitleMsg(text.getString("resetPassword.answer.success.title"));
				setBodyMsg(text.getString("resetPassword.answer.success.body"));
				PrimeFaces.current().executeScript("$('#success-Tab').modal('show');");
				// for clearing session
				setSecurityQuestionList(null);
				setUserName(null);
			} else {
				--noOfAttempts;
				int noOfAttemptShow = noOfAttempts + 1;
				FacesContext.getCurrentInstance().addMessage("otpform:otp",
						new FacesMessage(
								FacesMessage.SEVERITY_ERROR, text.getString("customer.home.exceeded.attempts.pre") + " "
										+ noOfAttemptShow + " " + text.getString("customer.home.exceeded.attempts.nex"),
								null));
				return;
			}
		} else {
			FacesContext.getCurrentInstance().addMessage("otpform:otp", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					text.getString("customer.home.exceeded.attempts"), null));
			setEnableResendOTP(true);
		}
	}

	public void smsGenerator() {

		noOfAttempts = rulesList.get(0).getnRequiredMobOTP();
		otpGenenater = otp.generateRandomString();
		String custName = (String) getSession().getAttribute("CUSTOMERNAME");

		if ((otpSettingsList.get(0).getvOtpFlagEmail() == "Y" || otpSettingsList.get(0).getvOtpFlagEmail().equals("Y")
				|| otpSettingsList.get(0).getvOtpFlagEmail().equalsIgnoreCase("Y"))
				&& (otpSettingsList.get(0).getvOtpFlagMobile() == "Y"
						|| otpSettingsList.get(0).getvOtpFlagMobile().equals("Y")
						|| otpSettingsList.get(0).getvOtpFlagMobile().equalsIgnoreCase("Y"))) {
			//smsEmail.smsGenerater(otpGenenater, msgDestinations);
			smsEmail.emailForOtpGenerater(to, otpGenenater);

		} else if (otpSettingsList.get(0).getvOtpFlagEmail() == "Y"
				|| otpSettingsList.get(0).getvOtpFlagEmail().equals("Y")
				|| otpSettingsList.get(0).getvOtpFlagEmail().equalsIgnoreCase("Y")) {
			smsEmail.emailForOtpGenerater(to, otpGenenater);
		} else {
			String maskMobileNo = smsEmail.maskMobileNumber(msgDestinations);
			smsEmail.emailGenerater(to, maskMobileNo);
			//smsEmail.smsGenerater(otpGenenater, msgDestinations);
		}
		setRenderStopTime(false);
		setEnableResendOTP(false);
		setRenderStartTime(true);
	}

	public void checkTime() {
		setRenderStopTime(true);
		setRenderStartTime(false);
		setEnableResendOTP(true);
		FacesContext.getCurrentInstance().addMessage("otpform:otp",
				new FacesMessage(FacesMessage.SEVERITY_ERROR, text.getString("customer.home.expired.OTP"), null));
	}

	
	public void closeModal() {
		setEnableResendOTP(false);
		setRenderStartTime(false);
		setRenderStopTime(true);
		setTitleMsg(text.getString("resetPassword.answer.cancelled.closeModel"));
		setBodyMsg(text.getString("eservices.transaction.cancel"));
		//RequestContext requestContext = RequestContext.getCurrentInstance();
		PrimeFaces.current().executeScript("$('#OTP-popup').modal('hide');");
		PrimeFaces.current().executeScript("$('#success-Tab').modal('show');");
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<ResetSecurityAnswerDTO> getSecurityQuestionList() {
		return securityQuestionList;
	}

	public void setSecurityQuestionList(List<ResetSecurityAnswerDTO> securityQuestionList) {
		this.securityQuestionList = securityQuestionList;
	}

	public boolean isAddNewQuestion() {
		return addNewQuestion;
	}

	public void setAddNewQuestion(boolean addNewQuestion) {
		this.addNewQuestion = addNewQuestion;
	}

	public ResetSecurityAnswerDTO getAddedQuestion() {
		return addedQuestion;
	}

	public void setAddedQuestion(ResetSecurityAnswerDTO addedQuestion) {
		this.addedQuestion = addedQuestion;
	}

	public List<CpQuestionsDetailDTO> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<CpQuestionsDetailDTO> questionList) {
		this.questionList = questionList;
	}

	public Map<String, String> getCpQuestionList() {
		return cpQuestionList;
	}

	public void setCpQuestionList(Map<String, String> cpQuestionList) {
		this.cpQuestionList = cpQuestionList;
	}

	public String getBodyMsg() {
		return bodyMsg;
	}

	public void setBodyMsg(String bodyMsg) {
		this.bodyMsg = bodyMsg;
	}

	public int getNoOfQuestion() {
		return noOfQuestion;
	}

	public void setNoOfQuestion(int noOfQuestion) {
		this.noOfQuestion = noOfQuestion;
	}

	public String getUserOTP() {
		return userOTP;
	}

	public void setUserOTP(String userOTP) {
		this.userOTP = userOTP;
	}

	public boolean isEnableResendOTP() {
		return enableResendOTP;
	}

	public boolean isRenderStartTime() {
		return renderStartTime;
	}

	public boolean isRenderStopTime() {
		return renderStopTime;
	}

	public void setEnableResendOTP(boolean enableResendOTP) {
		this.enableResendOTP = enableResendOTP;
	}

	public void setRenderStartTime(boolean renderStartTime) {
		this.renderStartTime = renderStartTime;
	}

	public void setRenderStopTime(boolean renderStopTime) {
		this.renderStopTime = renderStopTime;
	}

	public int getOTPTimeLimit() {
		return OTPTimeLimit;
	}

	public void setOTPTimeLimit(int oTPTimeLimit) {
		OTPTimeLimit = oTPTimeLimit;
	}

	public String getTitleMsg() {
		return titleMsg;
	}

	public void setTitleMsg(String titleMsg) {
		this.titleMsg = titleMsg;
	}

	public boolean isOtpValidationstatus() {
		return otpValidationstatus;
	}

	public void setOtpValidationstatus(boolean otpValidationstatus) {
		this.otpValidationstatus = otpValidationstatus;
	}

}
