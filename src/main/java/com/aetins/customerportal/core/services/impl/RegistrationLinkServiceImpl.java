package com.aetins.customerportal.core.services.impl;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.core.events.registration.OnRegistrationCompleteEvent;
import com.aetins.customerportal.core.services.IRegistrationLink;
import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.service.CpConfigurationBL;
import com.aetins.customerportal.web.utils.ConfigurationConstants;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class RegistrationLinkServiceImpl implements IRegistrationLink {

	
	private Logger _LOGGER = LoggerFactory.getLogger(RegistrationLinkServiceImpl.class);

	private LoadingCache<String, String> registrationCache;

	private static final Integer EXPIRE_MINS = 5;

	@Autowired
	MessageSource messages;

	@Autowired
	CpConfigurationBL configurationService;

	

	public RegistrationLinkServiceImpl() {

		registrationCache = CacheBuilder.newBuilder().expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES)
				.build(new CacheLoader<String, String>() {
					public String load(String key) {
						return null;
					}
				});
	}

	@Override
	public SimpleMailMessage constructEmailMessage(OnRegistrationCompleteEvent event, CpUserInfo user, String token) {

		Map<String, String> configMap = configurationService.getConfigMap();
		String FROM_MAIL = configMap.get(ConfigurationConstants._PORTAL_SMTP_FROM_MAIL);

		final String recipientAddress = user.getVemail();
		final String subject = "Registration Confirmation";
		final String confirmationUrl = event.getAppUrl() + "/registration/confirmation?token=" + token;
		final String message = messages.getMessage("message.regSucc", null,
				"You registered successfully. We will send you a confirmation message to your email account.",
				event.getLocale());
		final SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(recipientAddress);
		email.setFrom(FROM_MAIL);
		email.setSubject(subject);
		email.setText(message + " \r\n" + confirmationUrl);

		registrationCache.put(recipientAddress, confirmationUrl);
		_LOGGER.info("Registration cache loaded with email: {}",recipientAddress);
		return email;

	}

	@Override
	public String getConfirmationlink(String email) {

		String link = "";

		try {
			link = registrationCache.get(email);
		} catch (ExecutionException e) {
			e.printStackTrace();
			_LOGGER.error("Exception caught while fetching registration link: {}", e.getCause());
		}

		return link;
	}

}
