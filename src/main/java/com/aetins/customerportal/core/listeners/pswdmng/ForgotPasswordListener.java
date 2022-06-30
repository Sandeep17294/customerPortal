package com.aetins.customerportal.core.listeners.pswdmng;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.aetins.customerportal.core.events.pswdManagement.OnForgotPasswordCompleteEvent;
import com.aetins.customerportal.core.events.registration.OnRegistrationCompleteEvent;
import com.aetins.customerportal.core.services.IMailService;
import com.aetins.customerportal.web.dao.CustomerLoginDAO;
import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.entity.ForgotVerificationToken;
import com.aetins.customerportal.web.entity.VerificationToken;
import com.aetins.customerportal.web.service.RegistrationVerifyToken;

/**
 * ForgotListener responsible for user forgot password link
 * @author avinash
 *
 */
@Component
public class ForgotPasswordListener implements ApplicationListener<OnForgotPasswordCompleteEvent> {
    
	private static Logger LOGGER = LoggerFactory.getLogger(ForgotPasswordListener.class);

    @Autowired
    private MessageSource messages;

    @Autowired
    private Environment env;
    
    @Autowired
    private IMailService mailService;

	/* API */
    @Override
    public void onApplicationEvent(final OnForgotPasswordCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(final OnForgotPasswordCompleteEvent event) {
    	
			final CpUserInfo user = event.getUser();
			final ForgotVerificationToken token = event.getVerificationToken();
			final SimpleMailMessage email = constructEmailMessage(event, user, token.getToken());
			mailService.sendMail(email.getTo()[0], email.getSubject(), email.getText());
			//mailSender.send(email.);
		
    }

    private final SimpleMailMessage constructEmailMessage(final OnForgotPasswordCompleteEvent event, final CpUserInfo user, final String token) {
        final String recipientAddress = user.getVemail();
        final String subject = "Password Reset";
        final String confirmationUrl = event.getAppUrl() + "/pswd/reset?token=" + token;
        final String message = messages.getMessage("message.regSucc", null, "Kindly click on following link inorder to reset the password", event.getLocale());
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setFrom("");
        email.setSubject(subject);
        email.setText(message + " \r\n" + confirmationUrl);
        email.setFrom(env.getProperty("support.email"));
        return email;
    }

}
