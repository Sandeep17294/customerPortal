package com.aetins.customerportal.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.apache.log4j.Logger;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.pojo.MessageQueuePojo;
import com.aetins.customerportal.web.pojo.SMSTriggerPojo;
import com.aetins.customerportal.web.service.MessageQueueBL;
import com.aetins.customerportal.web.utils.AppSettingURL;
import com.aetins.customerportal.web.utils.MessageQueueRequestWrapper;
import com.aetins.customerportal.web.utils.RestTemplateUtils;
import com.aetins.customerportal.web.utils.SMSTriggerRequestWrapper;

@Service
public class MessageQueueBlImpl implements MessageQueueBL {

	private static final Logger logger = Logger.getLogger(MessageQueueBlImpl.class);

	@Override
	public String sendMailTOQueue(List<CpUserInfo> usersInfoByUserGroup, String emailNotify, String attempts) {

		String response = null;

		try {
			List<MessageQueuePojo> messageQueues = new ArrayList<MessageQueuePojo>();

			// Add all messages to queue
			for (CpUserInfo userInfo : usersInfoByUserGroup) {

				String emailBody = null;

				String emailSubject = null;

				switch (emailNotify) {

				case "SUCCESSFUL LOGIN":

					String succMsg = AppSettingURL.EMAIL_NOTIFY_BODY_SUCCESSFUL_LOGIN;
					emailSubject = AppSettingURL.EMAIL_NOTIFY_SUBJECT_SUCCESSFUL_LOGIN;
					Map<String, String> succMap = new HashMap<String, String>();
					succMap.put("custName", userInfo.getVuserName());
					succMap.put("attempts", attempts);
					emailBody = StrSubstitutor.replace(succMsg, succMap);
					logger.info("Mail Queue switch case :" + emailNotify);

					break;

				case "FAILURE LOGIN":

					String failureMsg = AppSettingURL.EMAIL_NOTIFY_BODY_FAILURE_LOGIN;
					emailSubject = AppSettingURL.EMAIL_NOTIFY_SUBJECT_FAILURE_LOGIN;
					Map<String, String> failureMap = new HashMap<String, String>();
					failureMap.put("custName", userInfo.getVuserName());
					failureMap.put("attempts", attempts);
					emailBody = StrSubstitutor.replace(failureMsg, failureMap);
					logger.info("Mail Queue switch case :" + emailNotify);

					break;

				case "BUSINESS USER DOC UPLOAD":

					String emailBodyContent = AppSettingURL.EMAIL_NOTIFY_BODY_BUSINESS_USER_DOC_UPLOAD;
					Map<String, String> bussDocUploadMap = new HashMap<String, String>();
					bussDocUploadMap.put("custName", userInfo.getVuserName());

					// Email body, subject
					emailBody = StrSubstitutor.replace(emailBodyContent, bussDocUploadMap);
					emailSubject = AppSettingURL.EMAIL_NOTIFY_SUBJECT_BUSINESS_USER_DOC_UPLOAD;
					logger.info("Mail Queue switch case :" + emailNotify);

					break;

				default:
					break;
				}

				MessageQueuePojo message = new MessageQueuePojo(userInfo.getVuserName(), userInfo.getVprefName(),
						userInfo.getVemail(), userInfo.getVcontactNo(), emailSubject, emailBody);
				messageQueues.add(message);
			}

			MessageQueueRequestWrapper queueWrapper = new MessageQueueRequestWrapper();
			queueWrapper.setMessages(messageQueues);

			// Post request
			RestTemplate restTemplate = RestTemplateUtils.restTemplate();
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			String messageQueueUrl = AppSettingURL.MESSAGE_QUEUE_URL;
			response = restTemplate.postForObject(messageQueueUrl, queueWrapper, String.class);
			logger.info("Response from message queue: " + response + "");

		} catch (RestClientException e) {
			e.printStackTrace();
			logger.error("Exception caught while posting messages to JMS queue: " + e.getCause() + "");
		}

		return response;

	}

	@Override
	public String sendSMSTOQueue(List<CpUserInfo> usersInfoByUserGroup, String smsNotify, String attempts) {

		String response = null;

		try {
			List<SMSTriggerPojo> smsQueues = new ArrayList<SMSTriggerPojo>();

			String userName = AppSettingURL.Mobile_UserName;
			String password = AppSettingURL.Mobile_Password;
			String senderID = AppSettingURL.Mobile_SenderID;
			String dlrEnable = AppSettingURL.Mobile_DlrEnable;
			String oMesgType = AppSettingURL.Mobile_OMesgType;
			String countryCode = AppSettingURL.MOBILE_COUNTRY_CODE;

			// Add all SMS to queue
			for (CpUserInfo userInfo : usersInfoByUserGroup) {

				String smsMessage = null;

				String smsDestination = null;

				switch (smsNotify) {

				case "SUCCESSFUL LOGIN":

					// SMS Message
					String succMsg = AppSettingURL.SMS_NOTIFICATION_SUCCESSFUL_LOGIN;
					Map<String, String> succMap = new HashMap<String, String>();
					succMap.put("custName", userInfo.getVuserName());
					smsMessage = StrSubstitutor.replace(succMsg, succMap);
					
					// SMS Destination
					smsDestination = countryCode+userInfo.getVcontactNo();
					logger.info("SMS Queue switch case :" + smsNotify);

					break;

				case "FAILURE LOGIN":

					// SMS Message
					String failureMsg = AppSettingURL.SMS_NOTIFICATION_FAILURE_LOGIN;
					Map<String, String> failureMap = new HashMap<String, String>();
					failureMap.put("custName", userInfo.getVuserName());
					failureMap.put("attempts", attempts);
					smsMessage = StrSubstitutor.replace(failureMsg, failureMap);
					
					// SMS Destination
					smsDestination = countryCode+userInfo.getVcontactNo();
					logger.info("SMS Queue switch case :" + smsNotify);

					break;

				case "BUSINESS USER DOC UPLOAD":

					// SMS Message
					String smsMsg = AppSettingURL.SMS_NOTIFICATION_BUSINESS_USER_DOC_UPLOAD;
					Map<String, String> map = new HashMap<String, String>();
					map.put("custName", userInfo.getVuserName());
					smsMessage = StrSubstitutor.replace(smsMsg, map);

					// SMS Destination
					smsDestination = countryCode + userInfo.getVcontactNo();
					logger.info("SMS Queue switch case :" + smsNotify);

					break;

				default:
					break;
				}

				SMSTriggerPojo sms = new SMSTriggerPojo(userName, password, senderID, dlrEnable, oMesgType, smsMessage,smsDestination);
				logger.info("SMS Trigger pojo : "+sms);
				smsQueues.add(sms);
			}

			SMSTriggerRequestWrapper queueWrapper = new SMSTriggerRequestWrapper();
			queueWrapper.setSmsTrigger(smsQueues);

			// Post request
			RestTemplate restTemplate = RestTemplateUtils.restTemplate();
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			String smsQueueUrl = AppSettingURL.SMS_QUEUE_URL;
			logger.info("Response from SMS queue url: " + smsQueueUrl + "");
			response = restTemplate.postForObject(smsQueueUrl, queueWrapper, String.class);
			logger.info("Response from SMS queue: " + response + "");

		} catch (RestClientException e) {
			e.printStackTrace();
			logger.error("Exception caught while posting messages to JMS queue: " + e.getCause() + "");
		}

		return response;
	}

}
