package com.aetins.customerportal.web.utils;

import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.aetins.customerportal.core.faceletstaglib.SecurityLibrary;
import com.aetins.customerportal.core.services.IMailService;
import com.aetins.customerportal.web.controllers.BaseAction;

import synapse.service.SynapseWsService;
import synapse.serviceImpl.SynapseWsServiceImpl;


/**
 * @author nithiya
 * @since 07/03/2017
 */
@Component
@Scope("session")
public class SMSEmailGenerater extends BaseAction {

	
	@Autowired
	private IMailService mailService;
	
	final Logger logger = LoggerFactory.getLogger(SMSEmailGenerater.class);
	
	private SynapseWsService synapseWsService = new SynapseWsServiceImpl();
	
	@Autowired
	private ApplicationMailer appMailer;

	@Override
	public void init() {
		// TODO Auto-generated method stub
		FacesContext context = FacesContext.getCurrentInstance();
		if (BSLUtils.isNotNull(context)) {
			text = context.getApplication().evaluateExpressionGet(context, "#{txt}", ResourceBundle.class);
		}
	}

	public void smsGenerater(String otpGenenater, String msgDestinations) {
		try {
			String userName = AppSettingURL.Mobile_UserName;
			String password = AppSettingURL.Mobile_Password;
			String message = "Dear Customer, Your OTP is : " + otpGenenater + ", valid for "
					+ (Integer.parseInt(AppSettingURL.Mobile_Timelimit)) / 60
					+ " minutes. Please enter this online to continue your transaction on the Salama Customer Portal.";
			String senderID = AppSettingURL.Mobile_SenderID;
			String dlrEnable = AppSettingURL.Mobile_DlrEnable;
			String oMesgType = AppSettingURL.Mobile_OMesgType;
			synapseWsService.submitMessage(userName, password, message, oMesgType, senderID, msgDestinations,dlrEnable);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void smsGenerater1(String otpGenenater, String msgDestinations) {
		try {
			String userName = AppSettingURL.Mobile_UserName;
			String password = AppSettingURL.Mobile_Password;
			String message =  "Dear Customer \n\n"
	      		              + "Please use the OTP: " +otpGenenater+" " +" "+"to continue the Login process at Salama Portal \n\n"
	            		      + "OTP is valid for 5 minutes, please do not share with anyone. \n\n"
	      		              + "Yours Sincerely,\n"
	      		              + "Customer Service Dept\n"
	      		              + "Contact : +9714079999\n"
	      		              + "Web : salama.ae";	
			String senderID = AppSettingURL.Mobile_SenderID;
			String dlrEnable = AppSettingURL.Mobile_DlrEnable;
			String oMesgType = AppSettingURL.Mobile_OMesgType;
			synapseWsService.submitMessage(userName, password, message, oMesgType, senderID, msgDestinations,dlrEnable);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	
	public String maskMobileNumber(String mobNo) {
		int total = mobNo.length();
		int startlen = 3, endlen = 3;
		int masklen = total - (startlen + endlen);
		StringBuffer maskedbuf = new StringBuffer(mobNo.substring(0, startlen));
		for (int i = 0; i < masklen; i++) {
			maskedbuf.append('X');
		}
		maskedbuf.append(mobNo.substring(startlen + masklen, total));
		String masked = maskedbuf.toString();
		return masked;
	}

	public void emailGenerater(String to, String maskMobNo) {
		String subject = "Mail for Transaction One time password for your AL RAJHI portal Access.";
		String body = "Your One Time Password is sent to the registered Mobile No :" + " " + maskMobNo;
		
		mailService.sendMail(to, subject, body+"");
		//appMailer.sendMail(to, subject, body);
	}
	
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Autowired
    private Environment env;

	
	public void emailForOtpGenerater(String to, String otpGenenater) {
		String subject = "Mail for Transaction One time password your Salama Customer Portal Easy Life";
		String body = "Dear Customer," +"\n"+"\n"+ "Your OTP is" + " "+ otpGenenater +"\n" + "valid for" + (Integer.parseInt(AppSettingURL.Mobile_Timelimit)) / 60+ "mins. Please enter this online to continue your transaction on the Salama Customer Portal Easy Life.";
		final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(to);
        email.setSubject(subject);
        email.setText(body);
        email.setFrom(env.getProperty("support.email"));
		mailSender.send(email);
		//appMailer.sendMail(to, subject, body);
	}
	
	
	public void smsContHolidayTransaction(String msgDestinations , String requestNo, String transType ) {
		try {
			// System.out.println(text.getString("customer.home.sms.userName"));
			String userName = AppSettingURL.Mobile_UserName;
			String password = AppSettingURL.Mobile_Password;
			String message = "AL RAJHI is pleased to confirm that your Contribution Holiday request is activated on your Plan" +requestNo+
					".Please amend your payment method accordingly (if required).";
			String senderID = AppSettingURL.Mobile_SenderID;
			String dlrEnable = AppSettingURL.Mobile_DlrEnable;
			String oMesgType = AppSettingURL.Mobile_OMesgType;
			synapseWsService.submitMessage(userName, password, message, oMesgType, senderID, msgDestinations,
					dlrEnable);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void emailContHolidayTransaction(String to, String policyNo, String transType) {
		
		String subject = policyNo + "-" + transType;
		String body = text.getString("transaction.email.contHoliday.subject") +policyNo +
				text.getString("transaction.email.contHoliday.subject1");

		mailService.sendMail(to, subject, body+"");
		
		//appMailer.sendMail(to, subject, body);
	}
	
	public void smsGeneraterForNotifications(String message, String msgDestinations) {
		try {
		
			/*
			 * String userName = AppSettingURL.Mobile_UserName; String password =
			 * AppSettingURL.Mobile_Password;
			 * 
			 * String senderID = AppSettingURL.Mobile_SenderID; String dlrEnable =
			 * AppSettingURL.Mobile_DlrEnable; String oMesgType =
			 * AppSettingURL.Mobile_OMesgType; synapseWsService.submitMessage(userName,
			 * password, message, oMesgType, senderID, msgDestinations,dlrEnable);
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ApplicationMailer getAppMailer() {
		return appMailer;
	}

	public void setAppMailer(ApplicationMailer appMailer) {
		this.appMailer = appMailer;
	}

	public SynapseWsService getSynapseWsService() {
		return synapseWsService;
	}

	public void setSynapseWsService(SynapseWsService synapseWsService) {
		this.synapseWsService = synapseWsService;
	}
	
	

}