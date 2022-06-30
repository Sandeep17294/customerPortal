package com.aetins.customerportal.core.services;

import org.springframework.mail.SimpleMailMessage;

import com.aetins.customerportal.core.events.registration.OnRegistrationCompleteEvent;
import com.aetins.customerportal.web.entity.CpUserInfo;

public interface IRegistrationLink {

	public SimpleMailMessage constructEmailMessage(final OnRegistrationCompleteEvent event, final CpUserInfo user, final String token);
	
	public String getConfirmationlink(String email);
}
