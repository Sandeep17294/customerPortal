package com.aetins.customerportal.web.controllers;

import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.aetins.customerportal.web.common.Constants;
import com.aetins.customerportal.web.service.RegistrationCustomerService;
import com.aetins.customerportal.web.service.impl.RegistrationCustomerServiceImpl;
import com.aetins.customerportal.web.utils.BSLUtils;
import com.aetins.customerportal.web.utils.DateUtil;
/*
 * Written By: Manish
 * for commonly required details 
 */
public abstract class BaseAction{
	
	 public ResourceBundle text;
	 private UIComponent sucessMsg;
	 private UIComponent errorMsg;
	 private static ServletContext servletContext;
     private Map<String, String> identificationMap;
	 private String listType,language;
	 @SuppressWarnings("rawtypes")
	 private RegistrationCustomerService registrationCustomerService = new RegistrationCustomerServiceImpl();
	 
	public BaseAction(){
		FacesContext context = FacesContext.getCurrentInstance();
		listType = Constants.IDENTIFICATION;
	    language = Constants.EN;
		if(BSLUtils.isNotNull(context)){
			text = context.getApplication().evaluateExpressionGet(context, "#{txt}", ResourceBundle.class);
		}
		 
	}
	
	@PostConstruct
	public abstract void init();

 
	/*
	 *  for getting session
	 */
	public static HttpSession getSession(){
		HttpSession session=null;
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		session=request.getSession(false);
		if(BSLUtils.isNull(session)){
			session=request.getSession();
		}
		return session;
	}
	@SuppressWarnings("unchecked")
	public String idenTypeDesc(String code){
		String values="";
		identificationMap = registrationCustomerService.getIdentificationDetails(listType, language);
		Set mapSet=identificationMap.entrySet();
		
		Iterator it=mapSet.iterator();
		while (it.hasNext()) {
			Entry<String,String> map=(Entry)it.next();
			String data=map.getValue();
			if(code.equalsIgnoreCase(map.getValue())){
				values=map.getKey();
			}
		}
		  return values;
	}
	
	public static HttpServletRequest getRequest(){
		HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
		return request;
	}
	
	public void change(String lan){
		FacesContext context=FacesContext.getCurrentInstance();
		UIViewRoot root=context.getViewRoot();
		Locale loc=new Locale(lan);
		root.setLocale(loc);
		}
	
	protected String getCurrentLanguage(){
		HttpSession session=getSession();
		if(session==null){
			session=getSession();
		}
		if(BSLUtils.isNull(session.getAttribute("LANGUAGE")))
			return "en";
		else
			return session.getAttribute("LANGUAGE").toString();
	}
	
	public void changeToEnglish(){
		change("en");
	}
	
	public void changeToArabic(){
		change("ar");
	}
	
	public void displayErrorMessage(String labelName,String message){
		//register-form2:secretQuestion1
		FacesContext.getCurrentInstance().addMessage(labelName,
			    new FacesMessage(FacesMessage.SEVERITY_ERROR, text.getString(message), null));
	}
	
	public void displayErrorMessage(String message){
		FacesContext context = FacesContext.getCurrentInstance();
		text = context.getApplication().evaluateExpressionGet(context, "#{txt}", ResourceBundle.class);
	    context.addMessage(errorMsg.getClientId(), new FacesMessage(text.getString(message)));
	}
	
	public void displayErrorMessageWithParam(String message,int param){
		FacesContext context = FacesContext.getCurrentInstance();
		text = context.getApplication().evaluateExpressionGet(context, "#{txt}", ResourceBundle.class);
	    context.addMessage(errorMsg.getClientId(), new FacesMessage(text.getString(message)+" "+param));
	}
	///added by Harmain for date message 
	public void displayErrorMessageWithParamStringDate(String message1, Date endTime,String message2){
		FacesContext context = FacesContext.getCurrentInstance();
		text = context.getApplication().evaluateExpressionGet(context, "#{txt}", ResourceBundle.class);
		endTime=DateUtil.toUtilDateWFormat(endTime.toString(), "yyyy-MM-dd HH:mm:ss");
	    context.addMessage(errorMsg.getClientId(), new FacesMessage(text.getString(message1)+" "+endTime+" "+text.getString(message2)));
	}
	//added by Harmain to add two messages
	public void displayErrorMessageWithParamString(String message1,int param, String message2){
		FacesContext context = FacesContext.getCurrentInstance();
		text = context.getApplication().evaluateExpressionGet(context, "#{txt}", ResourceBundle.class);
	    context.addMessage(errorMsg.getClientId(), new FacesMessage(text.getString(message1)+" "+param+" "+text.getString(message2)));
	}
	//end
	public void displaySuccessMessage(String message){
		FacesContext context = FacesContext.getCurrentInstance();
		text = context.getApplication().evaluateExpressionGet(context, "#{txt}", ResourceBundle.class);
		context.addMessage(sucessMsg.getClientId(), new FacesMessage(text.getString(message)));
	}

	public void displaySuccessMessage(String message,int param,String message1){
		FacesContext context = FacesContext.getCurrentInstance();
		text = context.getApplication().evaluateExpressionGet(context, "#{txt}", ResourceBundle.class);
		context.addMessage(sucessMsg.getClientId(), new FacesMessage(text.getString(message)+" "+param+" "+text.getString(message1)));
	}
	
/* added by viswa for client id as parameter*/
	public void displayErrorMessageWithClientId(String message,String clientId){
		FacesContext context = FacesContext.getCurrentInstance();
		text = context.getApplication().evaluateExpressionGet(context, "#{txt}", ResourceBundle.class);
	    context.addMessage(clientId, new FacesMessage(text.getString(message)));
	}
	public void displayErrorMessageWithClientId(String message,String clientId,String param){
		FacesContext context = FacesContext.getCurrentInstance();
		text = context.getApplication().evaluateExpressionGet(context, "#{txt}", ResourceBundle.class);
	    context.addMessage(clientId, new FacesMessage(text.getString(message)+" "+param));
	}
	public ResourceBundle getText() {
		return text;
	}

	public void setText(ResourceBundle text) {
		this.text = text;
	}

	public UIComponent getSucessMsg() {
		return sucessMsg;
	}

	public void setSucessMsg(UIComponent sucessMsg) {
		this.sucessMsg = sucessMsg;
	}

	public UIComponent getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(UIComponent errorMsg) {
		this.errorMsg = errorMsg;
	}

	public static ServletContext getServletContext() {
		return servletContext;
	}

	public static void setServletContext(ServletContext servletContext) {
		BaseAction.servletContext = servletContext;
	}
public RegistrationCustomerService getRegistrationCustomerService() {
		return registrationCustomerService;
	}


	public void setRegistrationCustomerService(RegistrationCustomerService registrationCustomerService) {
		this.registrationCustomerService = registrationCustomerService;
	}

	public String getListType() {
		return listType;
	}

	public String getLanguage() {
		return language;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Map<String, String> getIdentificationMap() {
		return identificationMap;
	}

	public void setIdentificationMap(Map<String, String> identificationMap) {
		this.identificationMap = identificationMap;
	}
	
	
	
}