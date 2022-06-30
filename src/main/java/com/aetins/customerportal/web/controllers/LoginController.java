package com.aetins.customerportal.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aetins.customerportal.web.dto.ListItem;
import com.aetins.customerportal.web.service.CustomerPortalServices;

/**
 * @author avinash
 *
 */
@Controller
public class LoginController {
	
	@Autowired
	CustomerPortalServices customerPortalServices;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		
		//Policy Number
		ListItem policyItem = new ListItem();
		policyItem.setCode("POLICYNO");
		policyItem.setDescription("Policy Number");
		
		//Employee Id
		ListItem employeeItem = new ListItem();
		employeeItem.setCode("EMPID");
		employeeItem.setDescription("Employee ID");
		
		//Certificate ID
		ListItem certificateItem = new ListItem();
		certificateItem.setCode("CERT");
		certificateItem.setDescription("Certificate Number");

		//Loan Id
		ListItem loanItem = new ListItem();
		loanItem.setCode("LOAN");
		loanItem.setDescription("Loan Reference Number");
		
		
		//Identification Type
		List<ListItem> idenTypeList = customerPortalServices.geteventgroup("", "IDEN_TYPE", "", "");
		idenTypeList.add(policyItem);
		idenTypeList.add(employeeItem);
		idenTypeList.add(certificateItem);
		idenTypeList.add(loanItem);
		
		
		ModelAndView view = new ModelAndView("login");
		view.addObject("IdenType", idenTypeList);
		
		return view;
	}
}
