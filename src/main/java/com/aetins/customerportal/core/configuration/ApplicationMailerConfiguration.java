package com.aetins.customerportal.core.configuration;

import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.aetins.customerportal.web.service.CpConfigurationBL;
import com.aetins.customerportal.web.utils.ConfigurationConstants;

/**
 * {@link ApplicationMailerConfiguration} configuration class is to accommodate different {@link JavaMailSender} beans for different smtps
 * @author avinash
 *
 */
@Configuration
@Component
public class ApplicationMailerConfiguration {
	
	private static final Logger _LOGGER  = LoggerFactory.getLogger(ApplicationMailerConfiguration.class);
	
	@Value("${alhilal.smtp.host}")
	private String ALHILAL_SMTP_HOST;
	
	@Value("${alhilal.smtp.port}")
	private String ALHILAL_SMTP_PORT;
	
	@Value("${alhilal.smtp.username}")
	private String ALHILAL_SMTP_USERNAME;
	
	@Value("${alhilal.smtp.password}")
	private String ALHILAL_SMTP_PASSWORD;
	
	@Autowired
	CpConfigurationBL configurationService;

	@Bean
	public JavaMailSender getJavaMailSender() {
	
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost(ALHILAL_SMTP_HOST);
	    mailSender.setPort(Integer.parseInt(ALHILAL_SMTP_PORT)); 
	    mailSender.setUsername(ALHILAL_SMTP_USERNAME);
	    mailSender.setPassword(ALHILAL_SMTP_PASSWORD);
	     
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
		
//		Map<String, String> configMap = configurationService.getConfigMap();
		
//		final String ART_SMPT_HOST = configMap.get(ConfigurationConstants._PORTAL_SMTP_HOST);
//		final String ART_SMPT_PORT = configMap.get(ConfigurationConstants._PORTAL_SMTP_PORT);
//		
//	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//	    mailSender.setHost(ART_SMPT_HOST);
//	    mailSender.setPort(Integer.parseInt(ART_SMPT_PORT)); 
//	    
//	    Properties props = mailSender.getJavaMailProperties();
//	    props.put("mail.transport.protocol", "smtp");
//	    props.put("mail.smtp.auth", "false");
//	    props.put("mail.smtp.ssl.trust", ART_SMPT_HOST);
//	    props.put("mail.smtp.starttls.enable", "true");
//	    props.put("mail.debug", "true");
//	    _LOGGER.info("-----------------------------------");
//	    _LOGGER.info("---------- SMTP Registered ---------");
//	    _LOGGER.info("SMPT host: {}, SMTP port: {}", ART_SMPT_HOST,ART_SMPT_PORT);
//	    _LOGGER.info("-----------------------------------");
	    return mailSender;
	}
	
}
