package com.aetins.customerportal.core.listeners.registration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.aetins.customerportal.core.events.registration.OnRegistrationCompleteEvent;
import com.aetins.customerportal.core.services.IMailService;
import com.aetins.customerportal.core.services.IRegistrationLink;
import com.aetins.customerportal.web.dao.CustomerLoginDAO;
import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.entity.VerificationToken;
import com.aetins.customerportal.web.service.RegistrationVerifyToken;

/**
 * RegistrationListener responsible for user registration token 
 * @author avinash
 *
 */
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    
	private static Logger LOGGER = LoggerFactory.getLogger(RegistrationListener.class);
	
    @Autowired
    private MessageSource messages;
  
    @Autowired
    private Environment env;
    
    @Autowired
    private IMailService mailService;
    
    @Autowired
    private IRegistrationLink registrationLink;

    // API
    @Override
    public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(final OnRegistrationCompleteEvent event) {
    	
			final CpUserInfo user = event.getUser();
			final VerificationToken token = event.getVerificationToken();
			
			final SimpleMailMessage email = registrationLink.constructEmailMessage(event, user, token.getToken());
			mailService.sendMail(email.getTo()[0], email.getSubject(), email.getText());
			//mailSender.send(email.);
		
    }

    private final SimpleMailMessage constructEmailMessage(final OnRegistrationCompleteEvent event, final CpUserInfo user, final String token) {
        final String recipientAddress = user.getVemail();
        final String subject = "Registration Confirmation";
        final String confirmationUrl = event.getAppUrl() + "/registration/confirmation?token=" + token;
        final String message = messages.getMessage("message.regSucc", null, "You registered successfully. We will send you a confirmation message to your email account.", event.getLocale());
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setFrom("");
        email.setSubject(subject);
        email.setText(message + " \r\n" + confirmationUrl);
        email.setFrom(env.getProperty("support.email"));
        return email;
    }

}
