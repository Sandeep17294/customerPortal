package com.aetins.customerportal.core.listeners.otp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.ViewExpiredException;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.aetins.customerportal.core.events.otp.OnOTPGenerationCompleteEvent;
import com.aetins.customerportal.core.faceletstaglib.SecurityLibrary;
import com.aetins.customerportal.core.services.IMailService;
import com.aetins.customerportal.core.services.IOTPService;
import com.aetins.customerportal.core.utils.EmailTemplate;
import com.aetins.customerportal.web.dto.CPPortalSMSDto;
import com.aetins.customerportal.web.dto.CpServerSettingDTO;
import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.service.AdminBL;
import com.aetins.customerportal.web.service.CustomerPortalServices;
import com.aetins.customerportal.web.utils.AppSettingURL;
import com.aetins.customerportal.web.utils.ConfigurationConstants;
import com.aetins.customerportal.web.utils.SMSEmailGenerater;

import synapse.service.SynapseWsService;
import synapse.serviceImpl.SynapseWsServiceImpl;

@Component
public class OTPGeneratorListener implements ApplicationListener<OnOTPGenerationCompleteEvent> {

	private static Logger logger = LoggerFactory.getLogger(OTPGeneratorListener.class);

	@Autowired
	private IMailService mailService;

	@Autowired
	private IOTPService otpService;
	
	@Autowired
	private CustomerPortalServices customerPortalServices;

	private SynapseWsService synapseWsService = new SynapseWsServiceImpl();
	
	@Override
	public void onApplicationEvent(final OnOTPGenerationCompleteEvent event) {

		logger.info("OTP Application event published");
		this.sendOTP(event);
	}

	private List<CpServerSettingDTO> cpServerSettingDTOList;
	
	@Autowired
	AdminBL adminImpl;
	
	private void sendOTP(final OnOTPGenerationCompleteEvent event) {
		try {			

			cpServerSettingDTOList = null;
			cpServerSettingDTOList = new ArrayList<CpServerSettingDTO>();
			cpServerSettingDTOList = adminImpl.fetchDownTime();
			if(event.getUserInfo().getVgroup().equalsIgnoreCase("U")) {
				if(cpServerSettingDTOList.get(0).getStatus().equalsIgnoreCase("A")) {
					
				}else {
					int otp = otpService.generateOTP(event.getUserInfo().getVuserName());
					logger.info("Username: {} OTP: {}",event.getUserInfo().getVuserName(),otp);
					String emailSubject = AppSettingURL.EMAIL_NOTIFY_SUBJECT_LOGIN_OTP;
					String emailBody = AppSettingURL.EMAIL_NOTIFY_BODY_LOGIN_OTP;
					Map<String, String> succMap = new HashMap<String, String>();
					succMap.put("custName", event.getUserInfo().getVuserName());
					succMap.put("otp", otp+"");
					String email = StrSubstitutor.replace(emailBody, succMap);
					Map<String, String> replacements = new HashMap<String, String>();
					replacements.put("user", event.getUserInfo().getVuserName());
					replacements.put("otpnum", String.valueOf(otp));
					mailService.sendMail(event.getUserInfo().getVemail(), emailSubject, email);
					
					String userName = AppSettingURL.Mobile_UserName;
					String password = AppSettingURL.Mobile_Password;
					String message =  "Dear Customer \n\n"
			      		              + "Please use the OTP: " +otp+" " +" "+"to continue the Login process at Salama Portal \n\n"
			            		      + "OTP is valid for 5 minutes, please do not share with anyone. \n\n"
			      		              + "Yours Sincerely,\n"
			      		              + "Customer Service Dept\n"
			      		              + "Contact : +9714079999\n"
			      		              + "Web : salama.ae";	
					String senderID = AppSettingURL.Mobile_SenderID;
					String dlrEnable = AppSettingURL.Mobile_DlrEnable;
					String oMesgType = AppSettingURL.Mobile_OMesgType;
					synapseWsService.submitMessage(userName, password, message, oMesgType, senderID, event.getUserInfo().getVcontactNo(),dlrEnable);
				}
			}else {
				int otp = otpService.generateOTP(event.getUserInfo().getVuserName());
				logger.info("Username: {} OTP: {}",event.getUserInfo().getVuserName(),otp);
				String emailSubject = AppSettingURL.EMAIL_NOTIFY_SUBJECT_LOGIN_OTP;
				String emailBody = AppSettingURL.EMAIL_NOTIFY_BODY_LOGIN_OTP;
				Map<String, String> succMap = new HashMap<String, String>();
				succMap.put("custName", event.getUserInfo().getVuserName());
				succMap.put("otp", otp+"");
				String email = StrSubstitutor.replace(emailBody, succMap);
				Map<String, String> replacements = new HashMap<String, String>();
				replacements.put("user", event.getUserInfo().getVuserName());
				replacements.put("otpnum", String.valueOf(otp));
				mailService.sendMail(event.getUserInfo().getVemail(), emailSubject, email);
				
				String userName = AppSettingURL.Mobile_UserName;
				String password = AppSettingURL.Mobile_Password;
				String message =  "Dear Customer \n\n"
		      		              + "Please use the OTP: " +otp+" " +" "+"to continue the Login process at Salama Portal \n\n"
		            		      + "OTP is valid for 5 minutes, please do not share with anyone. \n\n"
		      		              + "Yours Sincerely,\n"
		      		              + "Customer Service Dept\n"
		      		              + "Contact : +9714079999\n"
		      		              + "Web : salama.ae";	
				String senderID = AppSettingURL.Mobile_SenderID;
				String dlrEnable = AppSettingURL.Mobile_DlrEnable;
				String oMesgType = AppSettingURL.Mobile_OMesgType;
				synapseWsService.submitMessage(userName, password, message, oMesgType, senderID, event.getUserInfo().getVcontactNo(),dlrEnable);
			}
			
		  } catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
