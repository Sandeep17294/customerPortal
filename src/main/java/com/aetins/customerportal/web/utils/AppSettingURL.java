package com.aetins.customerportal.web.utils;

import java.util.Properties;

import com.aetins.customerportal.core.faceletstaglib.SecurityLibrary;

public class AppSettingURL {
	

	
	public static final Properties reportProperties = LoadProperties.loadParams("webservices/appSettingURL");
	public static final Properties reportProperties1 = LoadProperties.loadParams("spring/dbconfig");
	
	
	//public static final String FINAL_URL = SecurityLibrary.getEnvironmentUrl()+reportProperties.getProperty("WebserviceURL");
	public static final String FINAL_URL = reportProperties.getProperty("WebserviceURL");
	public static final String DOCUMENT_LOCATION = reportProperties.getProperty("DocumentLocation");
	
	public static final String SERVICE_DOCUMENT_LOCATION = reportProperties.getProperty("ServiceDocumentLocation");
	public static final String CP_IMAGE_LOCATION = reportProperties.getProperty("CpImageLocation");

	
	
	
	/*
	 * public static final String IMAGE_LOCATION =
	 * System.getProperty(reportProperties.getProperty("userCurrentDir"))+
	 * reportProperties.getProperty("ImageLocation"); public static final String
	 * IMAGE_PATH =
	 * System.getProperty(reportProperties.getProperty("userCurrentDir")); public
	 * static final String ESTATEMENT_LOCATION =
	 * System.getProperty(reportProperties.getProperty("userCurrentDir"))+
	 * reportProperties.getProperty("EstatemetLocation");
	 */
	
	public static final String IMAGE_LOCATION = reportProperties.getProperty("ImageLocation");
	public static final String IMAGE_PATH = System.getProperty(reportProperties.getProperty("userCurrentDir"));
	public static final String ESTATEMENT_LOCATION = reportProperties.getProperty("EstatemetLocation");
	//public static final String ESTATEMENT_LOCATION = reportProperties.getProperty("EstatemetLocation");
	public static final String ESTATEMENT_REPORT_LOCATION = reportProperties.getProperty("EstatemetReportLocation");
	public static final String ACTIVATION_URL = reportProperties.getProperty("ActivationURL");
	//public static final String FINAL_Report_URL=SecurityLibrary.getEnvironmentUrl()+reportProperties.getProperty("FinalReportUrl");
	public static final String FINAL_Report_URL=reportProperties.getProperty("FinalReportUrl");
	public static final String SYNAPSE_WS = reportProperties.getProperty("SynapseWebserviceURL");
	public static final String MOBILE_COUNTRY_CODE = reportProperties.getProperty("MobileCountryCode");
	public static final String Mobile_UserName = reportProperties.getProperty("MobileUserName");
	public static final String Mobile_Password = reportProperties.getProperty("MobilePassword");
	public static final String Mobile_SenderID = reportProperties.getProperty("MobileSenderID");
	public static final String Mobile_DlrEnable = reportProperties.getProperty("MobileDlrEnable");
	public static final String Mobile_OMesgType = reportProperties.getProperty("MobileOMesgType");
	public static final String Mobile_Timelimit = reportProperties.getProperty("MobileTimelimit");
	public static final String Phone_No_Format = reportProperties.getProperty("PhoneNumberFormat");
	public static final String MOBILE_COUNTRY_CODE_MALAYSIA = reportProperties.getProperty("MobileCountryCodeMalaysia");
	//public static final String Birt_Report_URL=SecurityLibrary.getEnvironmentUrl()+reportProperties.getProperty("BirtReportUrl");
	public static final String Birt_Report_URL=reportProperties.getProperty("BirtReportUrl");
	public static final String REPORT_LOCATION = reportProperties.getProperty("ReportLocation");
	public static final String ORACLE_CONNECTION = reportProperties.getProperty("database");
	
	
	public static final String DBPROB=reportProperties.getProperty("DBPROP");
	public static final String DBPROPURL=reportProperties.getProperty("DBPROPURL");
	public static final String DBPROPUSER=reportProperties.getProperty("DBPROPUSER");
	public static final String DBPROPPASS=reportProperties.getProperty("DBPROPPASS");
	

	 public static final String DB_URL = reportProperties1.getProperty("spring.datasource.isf.jdbcUrl");
	 public static final String DB_USERNAME = reportProperties1.getProperty("spring.datasource.isf.username");
	 public static final String DB_PASSWORD = reportProperties1.getProperty("spring.datasource.isf.password");
	 public static final String DB_Driver = reportProperties1.getProperty("spring.datasource.isf.driver.class-name");
	
	 // Transaction URL's
	 //public static final String ESERV_TRANSACTION_URL = SecurityLibrary.getEnvironmentUrl()+reportProperties.getProperty("EserviceTransactionURL");
	 public static final String ESERV_TRANSACTION_URL = reportProperties.getProperty("EserviceTransactionURL");
	 public static final String UNITSTMT_PATH=reportProperties.getProperty("UnitStatementPath");
	 //public static final String TRANSACTION_Report_URL=SecurityLibrary.getEnvironmentUrl()+reportProperties.getProperty("TransactionReportUrl");
	 public static final String TRANSACTION_Report_URL=reportProperties.getProperty("TransactionReportUrl");
	 public static final String TRANSACTION_LOCATION = reportProperties.getProperty("TransactionLocation");
	 //public static final String TRANSACTION_LOCATION = reportProperties.getProperty("TransactionLocation");
	 public static final String CONTRIBUTION_HOLIDAY_FLAG = reportProperties.getProperty("ContributionHolidayFlag");
	 public static final String UPDATE_CONTACT_FLAG = reportProperties.getProperty("UpdateContactsFlag");
	 public static final String REDIRECTION_SW_FLAG = reportProperties.getProperty("RedirectionSwitchingFlag");
	 public static final String CONTRIBUTION_ALT = reportProperties.getProperty("ContributionAlterationFlag");
	 public static final String REINSTATEMENT_FLAG = reportProperties.getProperty("ReinstatementFlag");
	 public static final String PROTECTION_BENEFIT_FLAG = reportProperties.getProperty("ProtectionBenefitFlag");
	 
	 //FTP file upload details
	 public static final String OAUTH2_CLIENT_ID = reportProperties.getProperty("oauth2ClientId");
	 public static final String OAUTH2_CLIENT_SECRET = reportProperties.getProperty("oauth2ClientSecret");
	 public static final String OAUTH2_TOKEN_URL = reportProperties.getProperty("oauth2TokenUrl");
	 public static final String OAUTH2_USERNAME = reportProperties.getProperty("oauth2UserName");
	 public static final String OAUTH2_PASSWORD = reportProperties.getProperty("oauth2Password");
	 public static final String OAUTH2_GRANT_TYPE = reportProperties.getProperty("oauth2GrantType");
	 public static final String UPLOAD_FILE_URL = reportProperties.getProperty("ftpFileUploadUrl");
	 public static final String OAUTH2_ACCESS_TOKEN_LABEL = reportProperties.getProperty("accesstokenlabel");
	 public static final String FTP_FILE_DATA_LABEL = reportProperties.getProperty("fileDatalabel");
	 public static final String DOWNLOAD_FILE_URL = reportProperties.getProperty("ftpFileDownloadUrl");
	 
	 //Weblogic server details
	 public static final String WEBLOGIC_USERNAME = reportProperties.getProperty("weblogicUsername");
	 public static final String WEBLOGIC_PASSWORD = reportProperties.getProperty("weblogicPassword");
	 
	 //Message JMS url
	 public static final String MESSAGE_QUEUE_URL = reportProperties.getProperty("messagequeueurl");
	 
	 //SMS JMS URL
	 public static final String SMS_QUEUE_URL = reportProperties.getProperty("smsqueueurl");
	 
	 //SMS Notifications messages
	 public static final String SMS_NOTIFICATION_SUCCESSFUL_LOGIN = reportProperties.getProperty("smsSuccessfulLogin");
	 public static final String SMS_NOTIFICATION_FAILURE_LOGIN = reportProperties.getProperty("smsFailureLogin");
	 public static final String SMS_NOTIFICATION_BUSINESS_USER_DOC_UPLOAD = reportProperties.getProperty("smsBusUserUploadDoc");
	 
	 //Email Notification content
	 public static final String EMAIL_NOTIFY_SUBJECT_SUCCESSFUL_LOGIN = reportProperties.getProperty("emailSubjectForSuccessfulLogin");
	 public static final String EMAIL_NOTIFY_BODY_SUCCESSFUL_LOGIN = reportProperties.getProperty("emailBodyForSuccessfulLogin");
	 public static final String EMAIL_NOTIFY_SUBJECT_FAILURE_LOGIN = reportProperties.getProperty("emailSubjectForFailureLogin");
	 public static final String EMAIL_NOTIFY_BODY_FAILURE_LOGIN = reportProperties.getProperty("emailBodyForFailureLogin");
	 public static final String EMAIL_NOTIFY_SUBJECT_BUSINESS_USER_DOC_UPLOAD = reportProperties.getProperty("emailSubjectForBusinessDocUpload");
	 public static final String EMAIL_NOTIFY_BODY_BUSINESS_USER_DOC_UPLOAD = reportProperties.getProperty("emailBodyForBusinessDocUpload");
	 
	 //Email Notification for business user registration
	 public static final String EMAIL_NOTIFY_SUBJECT_BUSINESS_USER_REGISTRATION = reportProperties.getProperty("emailSubjectForBusinessUserRegistration");
	 public static final String EMAIL_NOTIFY_BODY_BUSINESS_USER_REGISTRATION = reportProperties.getProperty("emailBodyForBusinessUserRegistration");
	 
	 
	//Email Notifications for login authentication
	public static final String EMAIL_NOTIFY_SUBJECT_LOGIN_OTP = reportProperties.getProperty("emailSubjectForOtp");
	public static final String EMAIL_NOTIFY_BODY_LOGIN_OTP = reportProperties.getProperty("emailBodyForOTP");
	
	//Email Notifications for E-STATEMENT
	public static final String EMAIL_NOTIFY_SUBJECT_E_STATEMENT = reportProperties.getProperty("emailSubjectForEstatement");
	public static final String EMAIL_NOTIFY_BODY_E_STATEMENT = reportProperties.getProperty("emailBodyForEstatement");
		 
	//Email Body for Transactions Initiated
	public static final String EMAIL_NOTIFY_BODY_TRANSACTION_TRIGGER = reportProperties.getProperty("emailBodyForTransactionTrigger");
	
    //PAYMENT GATEWAY
	public static final String HYPER_PAY = reportProperties.getProperty("HyperPay");
	public static final String RETURN_URL = reportProperties.getProperty("returnURL");
}

