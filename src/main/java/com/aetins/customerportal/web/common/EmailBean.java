package com.aetins.customerportal.web.common;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.dto.PasswordRulesDTO;
import com.aetins.customerportal.web.service.impl.CustomerLoginBLImpl;
import com.aetins.customerportal.web.utils.BSLUtils;

//@Service("mailService")
public class EmailBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(EmailBean.class);
	private String SMTPHost=null;
	private String SMTPPort=null;
	private String SMTPUser=null;
	private String userPassword=null;
	private String from = null;
	CustomerLoginBLImpl loginDetails = new CustomerLoginBLImpl();
	
	public EmailBean(){
		PasswordRulesDTO rulesDTO = loginDetails.fetchCpSettings();
		this.SMTPHost=rulesDTO.getvSmtpHost();
		this.SMTPPort=rulesDTO.getvSmtpPort();
		this.SMTPUser=rulesDTO.getvSmtpUser();
		this.userPassword=rulesDTO.getvSmtpPassword();
		this.from=rulesDTO.getvFromEmail();
	}
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public void setSMTPHost(String sMTPHost) {
		SMTPHost = sMTPHost;
	}
	public String getSMTPHost() {
		return SMTPHost;
	}
	public void setSMTPPort(String sMTPPort) {
		SMTPPort = sMTPPort;
	}
	public String getSMTPPort() {
		return SMTPPort;
	}
	public void setSMTPUser(String sMTPUser) {
		SMTPUser = sMTPUser;
	}
	public String getSMTPUser() {
		return SMTPUser;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserPassword() {
		return userPassword;
	}
	
	@Override	
	public String toString(){
		return getSMTPHost();
	}
	
	
	 public void sendMail(String to, String subject, String body) {
		 try{
			 String[] tmpTo={to};
				logger.info("Enter = sendEmail");
				Properties props = new Properties();		
				props.put("mail.smtp.host",this.getSMTPHost());
				props.put("mail.smtp.auth", "true");
				//props.put("mail.smtp.starttls.enable", "true");
				logger.info("SMTP Host = " + this.getSMTPHost());
				Session session = Session.getInstance(props,
						new javax.mail.Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() {
								if(getSMTPUser()==null)								
									return new PasswordAuthentication("","");
								else
									return new PasswordAuthentication(getSMTPUser(),"");
							}
				});
				
			    session.setDebug(true);	
			    Message msg = new MimeMessage(session);	
			    InternetAddress addressFrom = new InternetAddress(getFrom());
			    msg.setFrom(addressFrom);	
			    InternetAddress[] addressTo = new InternetAddress[tmpTo.length];
			    logger.info("To addres List");
			    for(int index=0;index<tmpTo.length;index++){
			    	
			    	addressTo[index]=new InternetAddress(tmpTo[index]);	
			    }
			    
			    msg.setRecipients(Message.RecipientType.TO, addressTo);
			    msg.addHeader("MyHeaderName", "MIME-Version: 1.0");
			    msg.setSubject(subject);
			  
			    	msg.setContent(body, "text/plain;charset=UTF-8;format=flowed");
			    
			    //msg.set
			    
			    Transport.send(msg);		    
			    logger.info("Email has been sent to "+addressTo.toString());
			   
			}catch(AddressException adEx){
				logger.info("An AddressException occurred = " + adEx.getMessage());
				adEx.printStackTrace();
				
			}catch(MessagingException msgEx){
				logger.info("An MessagingException occurred = " + msgEx.getMessage());
				msgEx.printStackTrace();
				
			}catch(Exception ex){
				logger.info("An Exception occurred = " + ex.getMessage());
				ex.printStackTrace();
				
			}
		    }
	
	//public boolean sendEmail(EmailMessage message){
	public boolean sendEmail(EmailMessage message){
		try{
			logger.info("Enter = sendEmail");
			Properties props = new Properties();		
			props.put("mail.smtp.host",this.getSMTPHost());
			props.put("mail.smtp.auth", "true");
			//props.put("mail.smtp.starttls.enable", "true");
			logger.info("SMTP Host = " + this.getSMTPHost());
			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							if(getSMTPUser()==null)								
								return new PasswordAuthentication("","");
							else
								return new PasswordAuthentication(getSMTPUser(),"");
						}
			});
			
		    session.setDebug(true);	
		    Message msg = new MimeMessage(session);	
		    InternetAddress addressFrom = new InternetAddress(getFrom());
		    msg.setFrom(addressFrom);	
		    InternetAddress[] addressTo = new InternetAddress[message.getTo().length];
		    logger.info("To addres List");
		    for(int index=0;index<message.getTo().length;index++){
		    	logger.info("To address = " + addressTo[index]);
		    	addressTo[index]=new InternetAddress(message.getTo()[index]);	
		    }
		    
		    msg.setRecipients(Message.RecipientType.TO, addressTo);
		    msg.addHeader("MyHeaderName", "MIME-Version: 1.0");
		    msg.setSubject(message.getSubject());
		    if(message.getAttachments().size() > 0){
		    	BodyPart messageBodyPart = new MimeBodyPart();
		    	messageBodyPart.setText(message.getBody());
		    	Multipart multipart = new MimeMultipart();
		    	multipart.addBodyPart(messageBodyPart);
		    	for(String fileName : message.getAttachments()){
		    		messageBodyPart = new MimeBodyPart();
					DataSource source = new FileDataSource(fileName);
					messageBodyPart.setDataHandler(new DataHandler(source));
					File f = new File(fileName);
					messageBodyPart.setFileName(f.getName());
					multipart.addBodyPart(messageBodyPart);
		    	}
		    	msg.setContent(multipart);
		    }else{
		    	msg.setContent(message.getBody(), "text/plain;charset=UTF-8;format=flowed");
		    }
		    //msg.set
		    
		    Transport.send(msg);		    
		    logger.info("Email has been sent to "+addressTo.toString());
		    return true;
		}catch(AddressException adEx){
			logger.info("An AddressException occurred = " + adEx.getMessage());
			adEx.printStackTrace();
			return false;
		}catch(MessagingException msgEx){
			logger.info("An MessagingException occurred = " + msgEx.getMessage());
			msgEx.printStackTrace();
			return false;
		}catch(Exception ex){
			logger.info("An Exception occurred = " + ex.getMessage());
			ex.printStackTrace();
			return false;
		}
	}
	 
	public boolean sendEmail( String from,String recipient[],String subject, String message){
		try{
		
			Properties props = new Properties();		
			props.put("mail.smtp.host",this.getSMTPHost());
			props.put("mail.smtp.auth", "true");
			
			Session session = Session.getDefaultInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							if(getSMTPUser()==null)								
								return new PasswordAuthentication("","");
							else
								return new PasswordAuthentication(getSMTPUser(),"");
						}
			});
			
		    session.setDebug(true);	
		    Message msg = new MimeMessage(session);	
		    InternetAddress addressFrom = new InternetAddress(from);
		    msg.setFrom(addressFrom);	
		    InternetAddress[] addressTo = new InternetAddress[recipient.length];
		    
		    for(int index=0;index<recipient.length;index++){
		    	addressTo[index]=new InternetAddress(recipient[index]);	
		    }

		    msg.setRecipients(Message.RecipientType.TO, addressTo);	
		    
		    logger.info("I am here ================>" + addressTo.toString());
		    // Optional Custom headers in the Email 
		    msg.addHeader("MyHeaderName", "MIME-Version: 1.0");		    
		    // Subject and Content Type
		    msg.setSubject(subject);
		    //msg.setText("Dear sir");
		    msg.setContent(message, "text/html;charset=UTF-8");
		    
		    Transport.send(msg);		    
		    logger.info("Email has been sent to "+addressTo.toString());
		    return true;

		}catch(AddressException adEx){
			adEx.printStackTrace();
			return false;
		}catch(MessagingException msgEx){
			msgEx.printStackTrace();
			return false;
		}catch(Exception ex){	
			ex.printStackTrace();
			return false;
		}		
	}
	
	
	public void sendMailAttachment(String to, String subject, String body,String file,String pdfName) throws MessagingException {
    	String[] tmpTo={to};
    	List<String> tmpFile=new ArrayList<String>();
    	if(BSLUtils.isNotNull(file) && !file.isEmpty()){
    			tmpFile.add(file);
    	}
		try{
			logger.info("Enter = sendEmail");
			Properties props = new Properties();		
			props.put("mail.smtp.host",this.getSMTPHost());
			props.put("mail.smtp.auth", "true");
			logger.info("SMTP Host = " + this.getSMTPHost());
			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							if(getSMTPUser()==null)								
								return new PasswordAuthentication("","");
							else
								return new PasswordAuthentication(getSMTPUser(),"");
						}
			});
			
		    session.setDebug(true);	
		    Message msg = new MimeMessage(session);	
		    InternetAddress addressFrom = new InternetAddress(getFrom());
		    msg.setFrom(addressFrom);	
		    InternetAddress[] addressTo = new InternetAddress[tmpTo.length];
		    logger.info("To addres List");
		    for(int index=0;index<tmpTo.length;index++){
		    	logger.info("To address = " + addressTo[index]);
		    	addressTo[index]=new InternetAddress(tmpTo[index]);	
		    }

		    msg.setRecipients(Message.RecipientType.TO, addressTo);
		    msg.addHeader("MyHeaderName", "MIME-Version: 1.0");
		    msg.setSubject(subject);
		    if(tmpFile.size() > 0){
		    	BodyPart messageBodyPart = new MimeBodyPart();
		    	messageBodyPart.setText(body);
		    	Multipart multipart = new MimeMultipart();
		    	multipart.addBodyPart(messageBodyPart);
		    	for(String fileName : tmpFile){
		    		messageBodyPart = new MimeBodyPart();
					DataSource source = new FileDataSource(fileName);
					messageBodyPart.setDataHandler(new DataHandler(source));
					File f = new File(fileName);
					messageBodyPart.setFileName(f.getName());
					multipart.addBodyPart(messageBodyPart);
		    	}
		    	msg.setContent(multipart);
		    }else{
		    	msg.setContent(body, "text/plain;charset=UTF-8;format=flowed");
		    }
		    
		    Transport.send(msg);		    
		    logger.info("Email has been sent to "+addressTo.toString());
		    
		}catch(AddressException adEx){
			logger.info("An AddressException occurred = " + adEx.getMessage());
			adEx.printStackTrace();
			
		}catch(MessagingException msgEx){
			logger.info("An MessagingException occurred = " + msgEx.getMessage());
			msgEx.printStackTrace();
			
		}catch(Exception ex){
			logger.info("An Exception occurred = " + ex.getMessage());
			ex.printStackTrace();
			
		}
        }
		 
}