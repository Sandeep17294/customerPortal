package com.aetins.customerportal.web.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aetins.customerportal.core.services.IMailService;
import com.aetins.customerportal.web.common.Constants;
import com.aetins.customerportal.web.common.ForgetPasswordOTPGenerater;
import com.aetins.customerportal.web.dto.BusinessUserRegisrtationDTO;
import com.aetins.customerportal.web.dto.MasterListDTO;
import com.aetins.customerportal.web.service.BusninessUserRegistrationBL;
import com.aetins.customerportal.web.service.impl.CustomerPortalServicesImpl;
import com.aetins.customerportal.web.utils.AppSettingURL;
import com.aetins.customerportal.web.utils.ApplicationMailer;
import com.aetins.customerportal.web.utils.Credential;
import com.aetins.customerportal.web.utils.Encryptor;

@Controller
@Scope("request")
public class BusinessUserRegistrationAction implements Serializable {

	@Autowired
	private IMailService mailService;
	
	private static final Logger logger = Logger.getLogger(BusinessUserRegistrationAction.class);

	private static final long serialVersionUID = 1L;

	@Autowired
	private BusninessUserRegistrationBL busninessUserRegistrationBL;

	private BusinessUserRegisrtationDTO businessUserRegisrtationDTO = new BusinessUserRegisrtationDTO();

	private ForgetPasswordOTPGenerater passwordOTPGenerater = new ForgetPasswordOTPGenerater();

	@Autowired
	private ApplicationMailer applicationMailer;

	private ResourceBundle text;

	private UIComponent component;

	private List<MasterListDTO> masterList = new ArrayList<MasterListDTO>();
	CustomerPortalServicesImpl customerPortalServices = new CustomerPortalServicesImpl();

	@PostConstruct
	public void init() {
		masterList = customerPortalServices.getMasterLov(Constants.EN, Constants.TITLE);
		FacesContext context = FacesContext.getCurrentInstance();
		text = context.getApplication().evaluateExpressionGet(context, "#{txt}", ResourceBundle.class);
	}

	/**
	 * button submit
	 */
	public void btnSubmit() {

		//RequestContext context = RequestContext.getCurrentInstance();

		String randomKey = passwordOTPGenerater.generateRandomString();
		businessUserRegisrtationDTO.setPassword(Credential.crypt(randomKey));
		
		
		boolean status = true;
		try {
			busninessUserRegistrationBL.saveBusinessUser(businessUserRegisrtationDTO);

			String emailSub = AppSettingURL.EMAIL_NOTIFY_SUBJECT_BUSINESS_USER_REGISTRATION;
			String emailBody = AppSettingURL.EMAIL_NOTIFY_BODY_BUSINESS_USER_REGISTRATION;
			
			Map<String, String> succMap = new HashMap<String, String>();
			succMap.put("custName", businessUserRegisrtationDTO.getFirstName());
			succMap.put("userId", businessUserRegisrtationDTO.getUserId());
			succMap.put("password", randomKey);
			String finalemailBody = StrSubstitutor.replace(emailBody, succMap);
			//logger.info("Mail Queue switch case :" + emailNotify);
			
			
			mailService.sendMail(businessUserRegisrtationDTO.getEmail(),emailSub,finalemailBody);
			
			//getApplicationMailer().sendMail(businessUserRegisrtationDTO.getEmail(),emailSub,finalemailBody);

			FacesContext fcontext = FacesContext.getCurrentInstance();
			AdminHomeAction aAction = fcontext.getApplication().evaluateExpressionGet(fcontext, "#{adminHomeAction}",
					AdminHomeAction.class);
			aAction.getAllUserList();
		} catch (Exception e) {
			status = false;
			e.getStackTrace();
			javax.faces.context.FacesContext contextJsf = javax.faces.context.FacesContext.getCurrentInstance();
			contextJsf.addMessage(component.getClientId(),
					new FacesMessage(text.getString("businessUser.email.error.userid")));

			logger.error("Exception reaised" + e.getMessage());
		}
		if (status) {
			PrimeFaces.current().executeScript("PF('dlg1').hide();");
			PrimeFaces.current().executeScript("$('#services-1').modal('show');");
		}

	}

	public BusninessUserRegistrationBL getBusninessUserRegistrationBL() {
		return busninessUserRegistrationBL;
	}

	public void setBusninessUserRegistrationBL(BusninessUserRegistrationBL busninessUserRegistrationBL) {
		this.busninessUserRegistrationBL = busninessUserRegistrationBL;
	}

	public BusinessUserRegisrtationDTO getBusinessUserRegisrtationDTO() {
		return businessUserRegisrtationDTO;
	}

	public void setBusinessUserRegisrtationDTO(BusinessUserRegisrtationDTO businessUserRegisrtationDTO) {
		this.businessUserRegisrtationDTO = businessUserRegisrtationDTO;
	}

	public ForgetPasswordOTPGenerater getPasswordOTPGenerater() {
		return passwordOTPGenerater;
	}

	public void setPasswordOTPGenerater(ForgetPasswordOTPGenerater passwordOTPGenerater) {
		this.passwordOTPGenerater = passwordOTPGenerater;
	}

	public ApplicationMailer getApplicationMailer() {
		return applicationMailer;
	}

	public void setApplicationMailer(ApplicationMailer applicationMailer) {
		this.applicationMailer = applicationMailer;
	}

	public UIComponent getComponent() {
		return component;
	}

	public void setComponent(UIComponent component) {
		this.component = component;
	}

	public List<MasterListDTO> getMasterList() {
		return masterList;
	}

	public void setMasterList(List<MasterListDTO> masterList) {
		this.masterList = masterList;
	}
}