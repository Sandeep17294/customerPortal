package com.aetins.customerportal.web.controllers;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aetins.customerportal.web.dto.CustomerOutstandingDTO;
import com.aetins.customerportal.web.service.CustomerPortalServices;
import com.aetins.customerportal.web.service.impl.CustomerPortalServicesImpl;

@Controller
@Scope("session")
public class OutstandingDetailsAction {
	//extends BaseAction

	private List<CustomerOutstandingDTO> outstandingList;
	@Autowired
	CustomerPortalServices customerPortalServicesImpl;
	@PostConstruct
	public void init() {
		System.out.println("---------------init()--------------------");
		outstandingList=new ArrayList<CustomerOutstandingDTO>();
		customerPortalServicesImpl=new CustomerPortalServicesImpl();
		outstandingList=customerPortalServicesImpl.getCustOutstanding("TLLTP120012260");
		System.out.println("---------------init()--------------------");
		System.out.println("OutstandingDetailsActionOutstandingDetailsActionOutstandingDetailsActionOutstandingDetailsAction"+outstandingList);
	}
	
	public OutstandingDetailsAction(){
		
	}
	public List<CustomerOutstandingDTO> getOutstandingList() {
		return outstandingList;
	}
	public void setOutstandingList(List<CustomerOutstandingDTO> outstandingList) {
		this.outstandingList = outstandingList;
	}
}