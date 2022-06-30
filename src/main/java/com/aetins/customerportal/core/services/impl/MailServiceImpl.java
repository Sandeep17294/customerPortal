package com.aetins.customerportal.core.services.impl;

import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.core.services.IMailService;
import com.aetins.customerportal.web.service.CpConfigurationBL;
import com.aetins.customerportal.web.utils.AppSettingURL;
import com.aetins.customerportal.web.utils.ConfigurationConstants;

import synapse.service.SynapseWsService;
import synapse.serviceImpl.SynapseWsServiceImpl;

@Service
public class MailServiceImpl implements IMailService {

	private static final Logger _LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);

	@Autowired
	public JavaMailSender emailSender;

	@Autowired
	CpConfigurationBL configurationService;

	private SynapseWsService synapseWsService = new SynapseWsServiceImpl();
	
	@Override
	public void sendMail(String to, String subject, String body) {

		Map<String, String> configMap = configurationService.getConfigMap();
		SimpleMailMessage message = new SimpleMailMessage();
		String FROM_MAIL = configMap.get(ConfigurationConstants._PORTAL_SMTP_FROM_MAIL);
		_LOGGER.info("SMTP FROM MAIL: {} ", FROM_MAIL);
		message.setFrom(FROM_MAIL);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		emailSender.send(message);

	}
	@Override
	public void sendMailWithAttachments(String to, String subject, String body, String content) throws Exception {

		MimeMessage message = emailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			Map<String, String> configMap = configurationService.getConfigMap();
			String FROM_MAIL = configMap.get(ConfigurationConstants._PORTAL_SMTP_FROM_MAIL);
			_LOGGER.info("SMTP FROM MAIL: {} ", FROM_MAIL);
			simpleMailMessage.setFrom(FROM_MAIL);
			simpleMailMessage.setTo(to);
			simpleMailMessage.setSubject(subject);
			simpleMailMessage.setText(body);
			
			helper.setFrom(simpleMailMessage.getFrom());
			helper.setTo(simpleMailMessage.getTo());
			helper.setSubject(simpleMailMessage.getSubject());
			helper.setText(String.format(simpleMailMessage.getText()));

			FileSystemResource file = new FileSystemResource(content);
			helper.addAttachment(file.getFilename(), file);

			emailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			_LOGGER.info("Exception caught while processing the attachement: {}", e.getCause());
		}

	}
	@Override
	public void smsgenerator(int otp, String contactno, String email) {
		try {
			String userName = AppSettingURL.Mobile_UserName;
			String password = AppSettingURL.Mobile_Password;
//			String message = "Dear Customer, Your OTP is : " + otp + ", valid for "
//					+ (Integer.parseInt(AppSettingURL.Mobile_Timelimit)) / 60
//					+ " minutes. Please enter this online to continue your transaction on the Salama Customer Portal.";
			String senderID = AppSettingURL.Mobile_SenderID;
			String dlrEnable = AppSettingURL.Mobile_DlrEnable;
			String oMesgType = AppSettingURL.Mobile_OMesgType;
			synapseWsService.submitMessage(userName, password, email, oMesgType, senderID, contactno,dlrEnable);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	@Override
	public void smsgeneratortransaction(String otp, String contactno) {
		try {
			String userName = AppSettingURL.Mobile_UserName;
			String password = AppSettingURL.Mobile_Password;
			String message = "Dear Customer, Your OTP is : " + otp + ", valid for "
					+ (Integer.parseInt(AppSettingURL.Mobile_Timelimit)) / 60
					+ " minutes. Please enter this online to continue your transaction on the Salama Customer Portal.";
			String senderID = AppSettingURL.Mobile_SenderID;
			String dlrEnable = AppSettingURL.Mobile_DlrEnable;
			String oMesgType = AppSettingURL.Mobile_OMesgType;
			synapseWsService.submitMessage(userName, password, message, oMesgType, senderID, contactno,dlrEnable);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
}
