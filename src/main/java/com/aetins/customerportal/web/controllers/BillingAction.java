package com.aetins.customerportal.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aetins.customerportal.web.dto.BillingDTO;
import com.aetins.customerportal.web.service.impl.CustomerPortalServicesImpl;

@Controller
@Scope("session")
public class BillingAction {

	private List<BillingDTO> billingDetailsList;
	/*BillingDTO[] billingDetailsList;*/
	private String messages="my name is manish";
	CustomerPortalServicesImpl services=new CustomerPortalServicesImpl();
	public BillingAction(){
		billingDetailsList=new ArrayList<BillingDTO>();
		billingDetailsList=services.getBuillingDetails("DN9603048");
		
		/*billingDetailsList=new BillingDTO[1];
		billingDetailsList=services.getBuillingDetails("DN9603048");*/
	}
	
	
	
	public List<BillingDTO> getBillingDetailsList() {
		return billingDetailsList;
	}

	public void setBillingDetailsList(List<BillingDTO> billingDetailsList) {
		this.billingDetailsList = billingDetailsList;
	}
	
	

	/*public BillingDTO[] getBillingDetailsList() {
		return billingDetailsList;
	}



	public void setBillingDetailsList(BillingDTO[] billingDetailsList) {
		this.billingDetailsList = billingDetailsList;
	}*/



	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}
	
	
}