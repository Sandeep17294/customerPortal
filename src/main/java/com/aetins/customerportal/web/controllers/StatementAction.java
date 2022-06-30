package com.aetins.customerportal.web.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.acls.jdbc.BasicLookupStrategy;
import org.springframework.stereotype.Controller;

import com.aetins.customerportal.core.faceletstaglib.SecurityLibrary;
import com.aetins.customerportal.core.services.IMailService;
import com.aetins.customerportal.web.common.LoginSession;
import com.aetins.customerportal.web.dto.CpCustomerDetailDTO;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.dto.PasswordRulesDTO;
import com.aetins.customerportal.web.dto.PolicyDetailsDTO;
import com.aetins.customerportal.web.dto.StatementDTO;
import com.aetins.customerportal.web.dto.UserDTO;
import com.aetins.customerportal.web.service.CpConfigurationBL;
import com.aetins.customerportal.web.service.CustomerLoginBL;
import com.aetins.customerportal.web.service.CustomerPortalServices;
import com.aetins.customerportal.web.service.ForgetPasswordBL;
import com.aetins.customerportal.web.service.impl.CustomerPortalServicesImpl;
import com.aetins.customerportal.web.utils.AppSettingURL;
import com.aetins.customerportal.web.utils.ApplicationMailer;
import com.aetins.customerportal.web.utils.BSLUtils;
import com.aetins.customerportal.web.utils.ConfigurationConstants;
import com.aetins.customerportal.web.utils.FileUtils;

@Controller
@Scope("request")
public class StatementAction extends BaseAction {
	
	private static final Logger logger = Logger.getLogger(StatementAction.class);
	
	@Autowired
	CustomerPortalServices customerPortalServices;
	
	@Autowired
	ForgetPasswordBL forgetPasswordBLImpl;
	
	@Autowired
	CustomerLoginBL customerLoginBL;	
	
	@Autowired
	LoginSession loginSession;
	
	@Autowired
	ApplicationMailer appMailer;
	
	@Autowired
	CpConfigurationBL configurationService;
	
	@Autowired
	IMailService mailService;

	private List<StatementDTO> statementDetailsList;
	private StatementDTO statementDetails;
	private String to;
	private String subject;
	private String body;
	private String successMsg;
	private boolean successRenderMsg;
	private StreamedContent file;
	private String bodyMsg;	
	private UserDTO userDetails = new UserDTO();
	private int custRefNo;
	private String userName;
	List<CpCustomerDetailDTO> cpCustomerDetailList = new ArrayList<CpCustomerDetailDTO>();	
	private List<CpUserInfoDTO> cpUserList = new ArrayList<CpUserInfoDTO>();	
	public String policyNo = "";
	private String SMTPHost = null;
	private String SMTPPort = null;
	private String SMTPUser = null;
	private String userPassword = null;
	private String from = null;
	

	public StatementAction() {
	}

	public String convert(Date date) {
		String convertedDate = "";
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			String dd = null;
			System.out.println("DOB IS" + date);
			dd = format.format(date);
			System.out.println("date dd" + dd);
			String day = dd.substring(0, 2);
			String month = dd.substring(3, 5);
			String year = dd.substring(6, dd.length());
			System.out.println(day + "," + month + "," + year);

			convertedDate = getMonthForInt(Integer.parseInt(month)) + " " + day + ", " + year;
			System.out.println(convertedDate);
			return convertedDate;
		}
		return "";
	}

	public static String getMonthForInt(int num) {
		String month = "wrong";
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();
		if ((num >= 0) && (num <= 11)) {
			month = months[num];
		}
		return month;
	}

	public void setCustomerPortalServices(CustomerPortalServicesImpl customerPortalServices) {
		this.customerPortalServices = customerPortalServices;
	}

	public void init() {
		
		PasswordRulesDTO rulesDTO = customerLoginBL.fetchCpSettings();
		SMTPHost = rulesDTO.getvSmtpHost();
		SMTPPort = rulesDTO.getvSmtpPort();
		SMTPUser = rulesDTO.getvSmtpUser();
		userPassword = rulesDTO.getvSmtpPassword();
		from = rulesDTO.getvFromEmail();
		
		statementDetailsList = new ArrayList<StatementDTO>();
		statementDetails = new StatementDTO();
		userName = SecurityLibrary.getFullLoggedInUser().getVuserName();
		userDetails.setUserName(userName);
		cpUserList = customerLoginBL.listUserDetailsForgetPassword(userDetails);
		custRefNo = ((CpUserInfoDTO) cpUserList.get(0)).getNcustRefNo();
		userDetails.setCustRefNo(custRefNo);
		cpCustomerDetailList = forgetPasswordBLImpl.secureDetailsForgetPassword(userDetails);

		statementDetailsList = customerPortalServices.getEstatementPolicys(BigDecimal.valueOf(SecurityLibrary.getFullLoggedInUser().getNcustRefNo()), "EN", "NB010");
		if ((BSLUtils.isNotNull(statementDetailsList)) && (statementDetailsList.size() > 1)) {
			for (StatementDTO statementDTO : statementDetailsList) {
				statementDTO.setStatementName("Unit Statement");
			}
		}
	
	}
	
	public String getstatementlist() {
		statementDetailsList = customerPortalServices.getEstatementPolicys(BigDecimal.valueOf(SecurityLibrary.getFullLoggedInUser().getNcustRefNo()), "EN", "NB010");
		return "/user/e-statment?faces-redirect=true";
	}
	
	

	public List<StatementDTO> getStatementDetailsList() {
		return statementDetailsList;
	}

	public void setStatementDetailsList(List<StatementDTO> statementDetailsList) {
		this.statementDetailsList = statementDetailsList;
	}

	public StatementDTO getStatementDetails() {
		return statementDetails;
	}

	public void setStatementDetails(StatementDTO statementDetails) {
		this.statementDetails = statementDetails;
	}

	private static String FILE = "";

	public static String getFILE() {
		return FILE;
	}

	public static void setFILE(String fILE) {
		FILE = fILE;
	}

	public void downloadPdFromPath() {

		try {
			FileInputStream inputstream = new FileInputStream(new File(AppSettingURL.ESTATEMENT_LOCATION+policyNo+".pdf"));

			logger.info("Policy No is @@@@@@@@@" + policyNo);
			for (StatementDTO dto : statementDetailsList) {
				if (dto.isSelected()) {
					policyNo = dto.getPolicyNo();
				}
			}
			file = new DefaultStreamedContent(inputstream, "pdf", policyNo + ".pdf");
		} catch (Exception e) {
			logger.info("Inside catch block for downloadFromPath >>>>>>>>>>" + e);
		}
	}

	/*
	 * public void downloadPdFromPath() { String filePath =
	 * "/resources/pdfDoc/"+policyNo+".pdf";
	 * 
	 * logger.info("Entered into DownLoad@@@@@@@@");
	 * 
	 * InputStream stream =
	 * FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(
	 * filePath); logger.info("Policy No is @@@@@@@@@" + policyNo); for
	 * (StatementDTO dto : statementDetailsList) { if (dto.isSelected()) { policyNo
	 * = dto.getPolicyNo(); } } file = new DefaultStreamedContent(stream, "pdf",
	 * policyNo + ".pdf");
	 * 
	 * }
	 */

	public void downloadPdf() {
		boolean clicked = false;
		int i = 0;
		try {
			for (StatementDTO dto : statementDetailsList) {
				if (dto.isSelected()) {
					i++;
					clicked = true;
					policyNo = dto.getPolicyNo();
				}
			}
			if ((clicked) && (i == 1)) {
				List<PolicyDetailsDTO> policyDetailsList = customerPortalServices.getPolicyLists(BigDecimal.valueOf(SecurityLibrary.getFullLoggedInUser().getNcustRefNo()));
				for(PolicyDetailsDTO policyDetail : policyDetailsList) {
					if(BSLUtils.isNotNull(policyDetail.getPolicyNo())&&policyDetail.getPolicyNo().trim().equals(policyNo)) {	
						String reportOutputFileName = "e-statement.pdf";
						String reportUrl = AppSettingURL.FINAL_Report_URL + "UnitStatement.rptdesign&language=EN&policyNo="
								+ policyNo +""+"&__format=pdf";
						logger.info("Report URL is " + reportUrl);
						FileUtils.saveURLToFile(reportUrl, AppSettingURL.ESTATEMENT_LOCATION + policyNo + ".pdf");
						downloadPdFromPath();
						break;
					}
				}
			} else if ((clicked) && (i > 1)) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message","Please select policy inorder to generate report");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			} else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message","Please select policy inorder to generate report");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendEmailWithAttachments(String host, String port, final String userName, final String password,
			String toAddress, String subject, String message, String[] attachFiles)
			throws AddressException, MessagingException {
		
		try {
			Properties properties = new Properties();
			properties.put("mail.transport.protocol", "smtp");
			properties.put("mail.smtp.auth", "false");
			properties.put("mail.smtp.ssl.trust", host);
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.debug", "true");
			properties.put("mail.smtp.host", host);
			properties.put("mail.smtp.port", port);
			Session session = Session.getInstance(properties, null);

			Message msg = new MimeMessage(session);
			
			
			msg.setFrom(new InternetAddress(userName));
			
			InternetAddress[] toAddresses = {new InternetAddress(toAddress)};
			msg.setRecipients(Message.RecipientType.TO, toAddresses);
			msg.setSubject(subject);
			msg.setSentDate(new Date());

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(message, "text/plain;charset=UTF-8;format=flowed");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			MimeBodyPart attachPart = new MimeBodyPart();
			logger.info("E-statement generated file location :" + attachFiles[0]);			
			attachPart.attachFile(attachFiles[0]);		
			multipart.addBodyPart(attachPart);
			msg.setContent(multipart);
			Transport.send(msg);
			logger.info("E-statement was generated success");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception caught while processing e-statement: "+e.getCause()+"");
		} 
	}

	boolean renderButtons = false;

	public boolean isRenderButtons() {
		return renderButtons;
	}

	public void setRenderButtons(boolean renderButtons) {
		this.renderButtons = renderButtons;
	}

	public void selectedStatement(StatementDTO statement) {
		statementDetails = new StatementDTO();
		if (statement != null) {
			statementDetails = statement;
			System.out.println(statement.getPolicyNo());
		}
	}

	public ApplicationMailer getAppMailer() {
		return appMailer;
	}

	public void setAppMailer(ApplicationMailer appMailer) {
		this.appMailer = appMailer;
	}

	public String sentEmail() {
		
		Map<String, String> configMap = configurationService.getConfigMap();
		String FROM_MAIL = configMap.get(ConfigurationConstants._PORTAL_SMTP_FROM_MAIL);
		String host = configMap.get(ConfigurationConstants._PORTAL_SMTP_HOST);
		String port = configMap.get(ConfigurationConstants._PORTAL_SMTP_PORT);
		String userName = SMTPUser;
		String password = userPassword;

		boolean clicked = false;
		for (StatementDTO dto : statementDetailsList) {
			if (dto.isSelected()) {
				clicked = true;
			}
		}
		if (clicked) {
			try {
				downloadPdf();
				String custName;

				if (BSLUtils.isNotNull(SecurityLibrary.getFullLoggedInUser().getVuserName())) {
					//custName = ((CpCustomerDetailDTO) cpCustomerDetailList.get(0)).getVcustName();
					custName = SecurityLibrary.getFullLoggedInUser().getVuserName().trim();
				} else {
					custName = "Customer";
				}
				for (StatementDTO dto : statementDetailsList) {
					if (dto.isSelected()) {
						policyNo = dto.getPolicyNo();
					}
				}

				String[] attachFiles = new String[3];

				attachFiles[0] = (AppSettingURL.UNITSTMT_PATH.trim() + policyNo + ".pdf");

				if (BSLUtils.isNotNull(SecurityLibrary.getFullLoggedInUser().getVuserName())) {
					//custName = ((CpCustomerDetailDTO) cpCustomerDetailList.get(0)).getVcustName();
					custName = SecurityLibrary.getFullLoggedInUser().getVuserName().trim();
				} else {
					String namenull = "Customer";
					custName = namenull;
				}

				String toAddress = SecurityLibrary.getFullLoggedInUser().getVemail().trim();
				subject = AppSettingURL.EMAIL_NOTIFY_SUBJECT_E_STATEMENT;
				
				/*
				 * body =
				 * 
				 * (text.getString("registrationAction.login.confirmationEmailSal") + " " +
				 * custName + "," + "\n" + "\n" + text.getString("unitStatement.email.subject1")
				 * + "\n" + "\n" + text.getString("registrationAction.forget.line4") + "\n" +
				 * text.getString("registrationAction.forget.line5"));
				 */
				body = AppSettingURL.EMAIL_NOTIFY_BODY_E_STATEMENT;
				Map<String, String> succMap = new HashMap<String, String>();
				succMap.put("custName", custName);
				String emailBody = StrSubstitutor.replace(body, succMap);
				mailService.sendMailWithAttachments(toAddress, subject, emailBody, attachFiles[0]);
				//sendEmailWithAttachments(host, port, FROM_MAIL, password, toAddress, subject,body, attachFiles); 
				setSuccessMsg("e-statement sent via email");
				 
				
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message","E-Statement was sent registered mail successfully");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				//RequestContext requestContext = RequestContext.getCurrentInstance();
				//PrimeFaces.current().executeScript("jQuery('#services-1').modal('show');");
				//setBodyMsg("Email with E-statement was sent successfully");

				setSuccessRenderMsg(true);
			} catch (Exception e) {
				e.printStackTrace();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message","E-Statement was sent registered mail failed");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				logger.error("Exception caught while processing the e-statement through mail: "+e.getCause()+"");
			}
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message","Please select policy inorder to generate report");
			PrimeFaces.current().dialog().showMessageDynamic(message);
			//displayErrorMessage("customer.home.estatement.validation");
		}

		return "e-statementSent";
	}

	public String getTo() {
		return to;
	}

	public String getSubject() {
		return subject;
	}

	public String getBody() {
		return body;
	}

	public String getSuccessMsg() {
		return successMsg;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}

	public boolean isSuccessRenderMsg() {
		return successRenderMsg;
	}

	public void setSuccessRenderMsg(boolean successRenderMsg) {
		this.successRenderMsg = successRenderMsg;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public String getBodyMsg() {
		return bodyMsg;
	}

	public void setBodyMsg(String bodyMsg) {
		this.bodyMsg = bodyMsg;
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

	public String toString() {
		return getSMTPHost();
	}
}
