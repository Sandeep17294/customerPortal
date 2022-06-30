/*
 * package com.aetins.customerportal.web.validators;
 * 
 * import java.util.ResourceBundle;
 * 
 * import javax.faces.context.FacesContext;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * 
 * import com.aetins.customerportal.web.controllers.BaseAction; import
 * com.aetins.customerportal.web.utils.AppSettingURL; import
 * com.aetins.customerportal.web.utils.ApplicationMailer; import
 * com.aetins.customerportal.web.utils.BSLUtils; import
 * com.aetins.synapse.serviceImpl.SynapseWsServiceImpl;
 * 
 *//**
	 * @author nithiya
	 * @since 07/03/2017
	 *//*
		 * public class SMSEmailGenerater extends BaseAction {
		 * 
		 * private SynapseWsServiceImpl synapseWsService = new SynapseWsServiceImpl();
		 * 
		 * @Autowired private ApplicationMailer appMailer;
		 * 
		 * @Override public void init() { // TODO Auto-generated method stub
		 * FacesContext context = FacesContext.getCurrentInstance(); if
		 * (BSLUtils.isNotNull(context)) { text =
		 * context.getApplication().evaluateExpressionGet(context, "#{txt}",
		 * ResourceBundle.class); } }
		 * 
		 * public void smsGenerater(String otpGenenater, String msgDestinations) { try {
		 * // System.out.println(text.getString("customer.home.sms.userName")); String
		 * userName = AppSettingURL.Mobile_UserName; String password =
		 * AppSettingURL.Mobile_Password; String message =
		 * "Dear Customer, Your OTP is : " + otpGenenater + ", valid for " +
		 * (Integer.parseInt(AppSettingURL.Mobile_Timelimit)) / 60 +
		 * " minutes. Please enter this online to continue your transaction on the AL HILAL portal."
		 * ; String senderID = AppSettingURL.Mobile_SenderID; String dlrEnable =
		 * AppSettingURL.Mobile_DlrEnable; String oMesgType =
		 * AppSettingURL.Mobile_OMesgType; synapseWsService.submitMessage(userName,
		 * password, message, oMesgType, senderID, msgDestinations, dlrEnable);
		 * 
		 * } catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } }
		 * 
		 * 
		 * 
		 * 
		 * public String maskMobileNumber(String mobNo) { int total = mobNo.length();
		 * int startlen = 3, endlen = 3; int masklen = total - (startlen + endlen);
		 * StringBuffer maskedbuf = new StringBuffer(mobNo.substring(0, startlen)); for
		 * (int i = 0; i < masklen; i++) { maskedbuf.append('X'); }
		 * maskedbuf.append(mobNo.substring(startlen + masklen, total)); String masked =
		 * maskedbuf.toString(); return masked; }
		 * 
		 * public void emailGenerater(String to, String maskMobNo) { String subject =
		 * text.getString("customer.home.email.subject.transactions"); String body =
		 * text.getString("customer.policydetails.OTP") + " " + maskMobNo;
		 * appMailer.sendMail(to, subject, body); }
		 * 
		 * 
		 * public void emailForOtpGenerater(String to, String otpGenenater) { String
		 * subject = text.getString("smsgetway.email.subject"); String body =
		 * text.getString("customer.policydetails.OTP.message") +"\n"+"\n" +
		 * text.getString("customer.policydetails.OTP.message1") + " "+ otpGenenater
		 * +"\n" + text.getString("customer.policydetails.OTP.message2") + " "+
		 * otpGenenater + "," + (Integer.parseInt(AppSettingURL.Mobile_Timelimit)) / 60
		 * + text.getString("customer.policydetails.OTP.message3"); // +
		 * text.getString("customer.policydetails.OTP.message3"); appMailer.sendMail(to,
		 * subject, body); }
		 * 
		 * 
		 * public void smsContHolidayTransaction(String msgDestinations , String
		 * requestNo, String transType ) { try { //
		 * System.out.println(text.getString("customer.home.sms.userName")); String
		 * userName = AppSettingURL.Mobile_UserName; String password =
		 * AppSettingURL.Mobile_Password; String message =
		 * "AL HILAL is pleased to confirm that your Contribution Holiday request is activated on your Plan"
		 * +requestNo+ ".Please amend your payment method accordingly (if required).";
		 * String senderID = AppSettingURL.Mobile_SenderID; String dlrEnable =
		 * AppSettingURL.Mobile_DlrEnable; String oMesgType =
		 * AppSettingURL.Mobile_OMesgType; synapseWsService.submitMessage(userName,
		 * password, message, oMesgType, senderID, msgDestinations, dlrEnable);
		 * 
		 * } catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } }
		 * 
		 * public void emailContHolidayTransaction(String to, String policyNo, String
		 * transType) {
		 * 
		 * String subject = policyNo + "-" + transType; String body =
		 * text.getString("transaction.email.contHoliday.subject") +policyNo +
		 * text.getString("transaction.email.contHoliday.subject1");
		 * 
		 * appMailer.sendMail(to, subject, body); }
		 * 
		 * public SynapseWsServiceImpl getSynapseWsService() { return synapseWsService;
		 * }
		 * 
		 * public void setSynapseWsService(SynapseWsServiceImpl synapseWsService) {
		 * this.synapseWsService = synapseWsService; }
		 * 
		 * public ApplicationMailer getAppMailer() { return appMailer; }
		 * 
		 * public void setAppMailer(ApplicationMailer appMailer) { this.appMailer =
		 * appMailer; }
		 * 
		 * }
		 */