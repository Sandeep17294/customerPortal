package com.aetins.customerportal.web.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.mail.Authenticator;
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
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aetins.customerportal.web.common.Constants;
import com.aetins.customerportal.web.common.LoginSession;
import com.aetins.customerportal.web.dto.CpCustomerDetailDTO;
import com.aetins.customerportal.web.dto.CpServiceTypeDTO;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.dto.PasswordRulesDTO;
import com.aetins.customerportal.web.dto.StatementDTO;
import com.aetins.customerportal.web.dto.UserDTO;
import com.aetins.customerportal.web.service.AdminBL;
import com.aetins.customerportal.web.service.CustomerLoginBL;
import com.aetins.customerportal.web.service.CustomerPortalServices;
import com.aetins.customerportal.web.service.ForgetPasswordBL;
import com.aetins.customerportal.web.service.impl.CustomerPortalServicesImpl;
import com.aetins.customerportal.web.utils.AppSettingURL;
import com.aetins.customerportal.web.utils.ApplicationMailer;
import com.aetins.customerportal.web.utils.BSLUtils;
import com.aetins.customerportal.web.utils.FileUtils;

@Controller
@Scope("view")
public class StatementAction1 extends BaseAction {

	@Autowired
	CustomerPortalServices customerPortalServices;
	
	@Autowired
	ApplicationMailer appMailer;
	
	@Autowired
	ForgetPasswordBL forgetPasswordBLImpl;
	
	@Autowired
	CustomerLoginBL customerLoginBL;
	
	@Autowired
	CustomerLoginBL loginDetails;
	
	@Autowired
	AdminBL adminImpl;
	
	@Autowired
	LoginSession loginSession;
	
	private static final Logger logger = Logger.getLogger(StatementAction.class);
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
	List<CpCustomerDetailDTO> cpCustomerDetailList = new ArrayList();
	
	private List<CpUserInfoDTO> cpUserList = new ArrayList();
	
	public String policyNo = "";
	private String SMTPHost = null;
	private String SMTPPort = null;
	private String SMTPUser = null;
	private String userPassword = null;
	private String from = null;
	
	private List<CpServiceTypeDTO> ccMailList = new ArrayList<>();
	private List<String> ccMail = new ArrayList<>();
	

	public StatementAction1() {

		PasswordRulesDTO rulesDTO = loginDetails.fetchCpSettings();
		this.SMTPHost = rulesDTO.getvSmtpHost();
		this.SMTPPort = rulesDTO.getvSmtpPort();
		this.SMTPUser = rulesDTO.getvSmtpUser();
		this.userPassword = rulesDTO.getvSmtpPassword();
		this.from = rulesDTO.getvFromEmail();

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
		statementDetailsList = new ArrayList();
		statementDetails = new StatementDTO();
		userName = loginSession.getInstance().getCustUserName();
		userDetails.setUserName(userName);
		cpUserList = customerLoginBL.listUserDetailsForgetPassword(userDetails);
		custRefNo = ((CpUserInfoDTO) cpUserList.get(0)).getNcustRefNo();
		userDetails.setCustRefNo(custRefNo);
		cpCustomerDetailList = forgetPasswordBLImpl.secureDetailsForgetPassword(userDetails);

		ccMailList = adminImpl.listcCEmail(Constants.UNIT_STATEMENT);

		logger.info("List of CCMAil list is : +ccMailList");
		statementDetailsList = customerPortalServices
				.getEstatementPolicys(BigDecimal.valueOf(loginSession.getInstance().getCustRefNo()), "EN", "NB010");
		if ((BSLUtils.isNotNull(statementDetailsList)) && (statementDetailsList.size() > 1)) {
			for (StatementDTO statementDTO : statementDetailsList) {
				statementDTO.setStatementName(text.getString("statement.user.unitStatement"));
			}
		}
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

	//private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 1);

	public static String getFILE() {
		return FILE;
	}

	public static void setFILE(String fILE) {
		FILE = fILE;
	}

	public void downloadPdFromPath() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//		String filePath = "/resources/pdfDoc/e-statement.pdf";
		String filePath = "/resources/pdfDoc/"+policyNo+".pdf";
//		String filePath = "E:/WebLogicHealthCheck/CustomerPortalWarFile/pdfDoc/e-statement.pdf";

		logger.info("Entered into DownLoad@@@@@@@@");

		InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(filePath);
		logger.info("Policy No is @@@@@@@@@" + policyNo);
		for (StatementDTO dto : statementDetailsList) {
			if (dto.isSelected()) {
				policyNo = dto.getPolicyNo();
			}
		}
		file = new DefaultStreamedContent(stream, "pdf", policyNo + ".pdf");

	}

	public void downloadPdf() {

		boolean clicked = false;
		int i = 0;
		String reportFileName = "UnitStatement";
		String isPreview = "Yes";
		String language = "EN";

		try {
			for (StatementDTO dto : statementDetailsList) {
				if (dto.isSelected()) {
					i++;
					clicked = true;
					policyNo = dto.getPolicyNo();
				}
			}
			if ((clicked) && (i == 1)) {
				String reportOutputFileName = "e-statement.pdf";

				String reportUrl = AppSettingURL.FINAL_Report_URL + "UnitStatement.rptdesign&language=EN&policyNo="
						+ policyNo + "&__format=pdf";
				logger.info("Report URL is " + reportUrl);
			FileUtils.saveURLToFile(reportUrl, AppSettingURL.ESTATEMENT_LOCATION + reportOutputFileName);
			FileUtils.saveURLToFile(reportUrl, AppSettingURL.ESTATEMENT_LOCATION + policyNo + ".pdf");
			downloadPdFromPath();
				
				 InputStream stream =
				 FacesContext.getCurrentInstance().getExternalContext().
				 getResourceAsStream(reportOutputFileName); file = new
				 DefaultStreamedContent(stream, "pdf", policyNo + ".pdf");
				 
			} else if ((clicked) && (i > 1)) {
				displayErrorMessage("statement.user.selectOneBox");
			} else {
				displayErrorMessage("statement.user.selectBox");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*private void addTitle(Document document) throws DocumentException {
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100.0F);

		table.addCell(getCellOnce(text.getString("statement.user.planDetails"), 0));

		document.add(table);
	}

	private void addTitle4(Document document) throws DocumentException {
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100.0F);

		table.addCell(getCellTitle(text.getString("statement.user.advisorDetails"), 2));
		table.addCell(
				getCellTitle(text.getString("statement.user.distributor") + statementDetails.getDistributor(), 2));
		table.addCell(getCellTitle(
				text.getString("statement.user.relationshipManager") + statementDetails.getRelationshipManger(), 2));
		document.add(table);
	}

	Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
	Date currentTime = localCalendar.getTime();

	private void addHeaderTitlePage(Document document) throws DocumentException {
		PdfPTable table = new PdfPTable(2);

		table.setWidthPercentage(100.0F);
		table.addCell(getCellHeader(text.getString("statement.user.contactNo"), 0));
		table.addCell(getCellHeader(text.getString("statement.user.valuationReportas") + currentTime, 2));
		document.add(table);
	}

	private void addEmptyLine(Document document) throws DocumentException {
		Paragraph preface = new Paragraph();
		addEmptyLine(preface, 1);
		document.add(preface);
	}

	private void addMetaTitlePage(Document document) throws DocumentException {

		PdfPTable table = new PdfPTable(8);

		table.setWidths(new int[] { 4, 3, 3, 2, 2, 3, 3, 3 });
		table.setWidthPercentage(100);
		table.addCell(getSecondHeaderCell(text.getString("statement.user.fundName"), 11));
		table.addCell(getSecondHeaderCell(text.getString("statement.user.allocationPercentage"), 11));
		table.addCell(getSecondHeaderCell(text.getString("statement.user.availableUnits"), 11));
		table.addCell(getSecondHeaderCell(text.getString("statement.user.unitsPrice"), 11));
		table.addCell(getSecondHeaderCell(text.getString("statement.user.fundCurr"), 11));
		table.addCell(getSecondHeaderCell(text.getString("statement.user.exchangeRate"), 11));
		table.addCell(getSecondHeaderCell(text.getString("statement.user.fundValueInFundCurr"), 11));
		table.addCell(getSecondHeaderCell(text.getString("statement.user.fundValueInPlanCurr"), 11));

		Iterator<FundDetailsDTO> it = statementDetails.getFundList().iterator();
		while (it.hasNext()) {
			FundDetailsDTO fundDetails = it.next();
			table.addCell(getSecondCell("" + fundDetails.getFundName(), 11));
			table.addCell(getSecondCell("" + fundDetails.getAllocationPercentage(), 11));
			table.addCell(getSecondCell("" + fundDetails.getAvailableUnits(), 11));
			table.addCell(getSecondCell("" + fundDetails.getUnitsPrice(), 11));
			table.addCell(getSecondCell("" + fundDetails.getFundCrr(), 11));
			table.addCell(getSecondCell("" + fundDetails.getExchangeRate(), 11));
			table.addCell(getSecondCell("" + fundDetails.getFundValueInFundCrr(), 11));
			table.addCell(getSecondCell("" + fundDetails.getFundValueInPlanCrr(), 11));
		}
		document.add(table);

	}

	private void addTitlePage(Document document) throws DocumentException {
		// statementDetails =
		// customerPortalServices.getStatementDetail(statementDetails.getPolicyNo(),
		// "EN");
		PdfPTable table = new PdfPTable(4);

		table.setWidthPercentage(100);
		table.addCell(getFirstHeaderCell(text.getString("statement.user.planHolder"), 11));
		table.addCell(getFirstCell("" + statementDetails.getName(), 11));
		table.addCell(getFirstHeaderCell(text.getString("statement.user.planNumber"), 11));
		table.addCell(getFirstCell("" + statementDetails.getPolicyNo(), 11));
		table.addCell(getFirstHeaderCell(text.getString("statement.user.planName"), 11));
		table.addCell(getFirstCell("" + statementDetails.getPlanName(), 11));
		table.addCell(getFirstHeaderCell(text.getString("statement.user.planStatus"), 11));
		table.addCell(getFirstCell("" + statementDetails.getStatusDescription(), 11));
		table.addCell(getFirstHeaderCell(text.getString("statement.user.commencementDate"), 11));
		table.addCell(getFirstCell("" + convert(statementDetails.getCommencementDate()), 11));
		table.addCell(getFirstHeaderCell(text.getString("statement.user.contributionDueDate"), 11));
		table.addCell(getFirstCell("" + convert(statementDetails.getPremiumDueDate()), 11));
		table.addCell(getFirstHeaderCell(text.getString("statement.user.planTerm"), 11));
		table.addCell(getFirstCell("" + statementDetails.getTerm(), 11));
		table.addCell(getFirstHeaderCell(text.getString("statement.user.maturityDate"), 11));
		table.addCell(getFirstCell("" + convert(statementDetails.getPolicyEndDate()), 11));
		table.addCell(getFirstHeaderCell(text.getString("statement.user.paymentFrequency"), 11));
		table.addCell(getFirstCell("" + statementDetails.getPaymentFrequency(), 11));
		table.addCell(getFirstHeaderCell(text.getString("statement.user.planCurrency"), 11));
		table.addCell(getFirstCell("" + statementDetails.getCurrencyCode(), 11));
		table.addCell(getFirstHeaderCell(text.getString("statement.user.contributionAmount"), 11));
		table.addCell(getFirstCell("" + statementDetails.getContribution(), 11));
		table.addCell(getFirstHeaderCell(text.getString("statement.user.totalReceivedContribution"), 11));
		table.addCell(getFirstCell("" + statementDetails.getTotalContribution(), 11));
		table.addCell(getFirstHeaderCell(text.getString("statement.user.totalBasicContribution"), 11));
		table.addCell(getFirstCell("" + statementDetails.getBaseCurrAmt(), 11));
		table.addCell(getFirstHeaderCell(text.getString("statement.user.totaltopUpContribution"), 11));
		table.addCell(getFirstCell("" + statementDetails.getPolCurrAmt(), 11));
		document.add(table);

	}

	private void addMetaTitlePage1(Document document) throws DocumentException {
		PdfPTable table = new PdfPTable(1);
		Iterator<FundDetailsDTO> it = statementDetails.getFundList().iterator();
		while (it.hasNext()) {
			FundDetailsDTO fundDetails = (FundDetailsDTO) it.next();

			table.setWidthPercentage(100.0F);

			table.addCell(getCellOnce(
					text.getString("statement.user.totalFundValueInPlanCurr") + fundDetails.getTotalFund(), 2));
		}
		document.add(table);
	}

	private void addMetaTitlePage2(Document document) throws DocumentException {
		PdfPTable table = new PdfPTable(1);

		table.setWidthPercentage(100.0F);

		document.add(table);
	}

	private PdfPCell getSecondHeaderCell(String text, int alignment) {
		Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 1);
		PdfPCell cell = new PdfPCell(new Phrase(text, bold));
		return cell;
	}

	private PdfPCell getSecondCell(String text, int alignment) {
		Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 11.0F);
		PdfPCell cell = new PdfPCell(new Phrase(text, bold));
		return cell;
	}

	private PdfPCell getCell2(String text, int font) {
		PdfPCell cell = new PdfPCell(new Phrase(text));
		cell.setPadding(2.0F);

		return cell;
	}

	private PdfPCell getFirstHeaderCell(String text, int alignment) {
		Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 1);
		PdfPCell cell = new PdfPCell(new Phrase(text, bold));
		cell.setBorder(0);
		return cell;
	}

	private PdfPCell getFirstCell(String text, int alignment) {
		Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 11.0F);
		PdfPCell cell = new PdfPCell(new Phrase(text, bold));
		cell.setBorder(0);
		return cell;
	}

	private PdfPCell getCellHeader(String text, int alignment) {
		Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 11.0F);
		PdfPCell cell = new PdfPCell(new Phrase(text, bold));

		cell.setPadding(2.0F);
		cell.setHorizontalAlignment(alignment);
		cell.setBorder(0);
		return cell;
	}

	private PdfPCell getCellOnce(String text, int alignment) {
		Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 1);
		PdfPCell cell = new PdfPCell(new Phrase(text, bold));

		cell.setPadding(4.0F);
		cell.setBorderWidth(12.0F);
		cell.setHorizontalAlignment(alignment);
		cell.setBorder(0);
		cell.setBackgroundColor(BaseColor.GRAY);
		return cell;
	}*/

	// For Prod checking MAil Attachment

	public void sendEmailWithAttachments(String host, String port, final String userName, final String password,
			String toAddress, List cc, String subject, String message, String[] attachFiles)
			throws AddressException, MessagingException {

		List tmpCc = cc;

		// sets SMTP server properties
		Properties properties = new Properties();

		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.user", userName);
		properties.put("mail.password", password);

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};
		Session session = Session.getInstance(properties, auth);

		InternetAddress[] ccAddress = new InternetAddress[tmpCc.size()];

		for (int i = 0; i < cc.size(); i++) {

			ccAddress[i] = new InternetAddress((String) cc.get(i));

		}

		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(userName));
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setRecipients(Message.RecipientType.CC, ccAddress);
		msg.setSubject(subject);
		msg.setSentDate(new Date());

		// creates message part
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(message, "text/plain;charset=UTF-8;format=flowed");

		// creates multi-part
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		// adds attachments

		MimeBodyPart attachPart = new MimeBodyPart();
		logger.info("Generated file location :" + attachFiles[0]);
		try {
			attachPart.attachFile(attachFiles[0]);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		multipart.addBodyPart(attachPart);
		/*
		 * if (attachFiles != null && attachFiles.length > 0) { for (String
		 * filePath : attachFiles) { MimeBodyPart attachPart = new
		 * MimeBodyPart();
		 * 
		 * try { attachPart.attachFile(filePath); } catch (IOException ex) {
		 * ex.printStackTrace(); }
		 * 
		 * multipart.addBodyPart(attachPart); } }
		 */

		// sets the multi-part as e-mail's content
		msg.setContent(multipart);

		// sends the e-mail
		Transport.send(msg);

	}

/*	private PdfPCell getCellTitle(String text, int alignment) {
		Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 11.0F);
		PdfPCell cell = new PdfPCell(new Phrase(text, bold));

		cell.setHorizontalAlignment(alignment);
		cell.setBorder(0);

		return cell;
	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
*/
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

		String host = SMTPHost;
		String port = SMTPPort;
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

				if (BSLUtils.isNotNull(((CpCustomerDetailDTO) cpCustomerDetailList.get(0)).getVcustName())) {
					custName = ((CpCustomerDetailDTO) cpCustomerDetailList.get(0)).getVcustName();
				} else {
					custName = "Customer";
				}
				for (StatementDTO dto : statementDetailsList) {
					if (dto.isSelected()) {
						policyNo = dto.getPolicyNo();
					}
				}

				String[] attachFiles = new String[3];

				attachFiles[0] = AppSettingURL.UNITSTMT_PATH.trim() + policyNo + ".pdf";

				if (BSLUtils.isNotNull(((CpCustomerDetailDTO) cpCustomerDetailList.get(0)).getVcustName())) {
					custName = ((CpCustomerDetailDTO) cpCustomerDetailList.get(0)).getVcustName();
				} else {
					String namenull = "Customer";
					custName = namenull;
				}
				for (CpServiceTypeDTO dto : ccMailList) {
					ccMail.add(dto.getCcEmail());
				}

				String toAddress = loginSession.getInstance().getCustEmail().trim();

				subject = text.getString("unitStatement.email.subject");
				body = (text.getString("registrationAction.login.confirmationEmailSal") + " " + custName + "," + "\n"
						+ "\n" + text.getString("unitStatement.email.subject1") + "\n" + "\n"
						+ text.getString("registrationAction.forget.line4") + "\n"
						+ text.getString("registrationAction.forget.line5"));
				String message = "Please find the attachment for unit statemnet";
				sendEmailWithAttachments(host, port, userName, password, toAddress, ccMail, subject, body, attachFiles);
				setSuccessMsg("e-statement sent via email");
				//RequestContext requestContext = RequestContext.getCurrentInstance();
				PrimeFaces.current().executeScript("jQuery('#services-1').modal('show');");
				setBodyMsg(text.getString("customer.home.estatement.successEmail"));

				setSuccessRenderMsg(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			displayErrorMessage("customer.home.estatement.validation");
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

	@Override
	public String toString() {
		return getSMTPHost();
	}

}