package com.aetins.customerportal.web.controllers;

import javax.faces.bean.ManagedBean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aetins.customerportal.web.dto.SearchPolicyDTO;

@Controller
@ManagedBean(name="familyTakaful")
@Scope("request")
public class FamilyTakafulTransactionAction extends BaseAction{
	
	private String bodyMes;
	private SearchPolicyDTO searchPolicyList;
	@Override
	public void init() {
		// TODO Auto-generated method stub
		searchPolicyList =(SearchPolicyDTO) getSession().getAttribute("POLICYDATA");
	}
	
	public String addNewBene() {

		return "addbeneficiary";
	}

	public String doRedirection() {

		return "redirection";
	}

	public String doSwitching() {

		return "switching";
	}
	
	public String doPartialWithdrwal() {

		return "partialWithdrawal";
	}
	
	public String doContributionHoliday() {
       	
		return "contributionHoliday";
	}
	
	public String doReinstatement() {

		return "reinstatement";
	}
	
	public String doChangeInSumAssured() {

		return "sumassuredAlteration";
	}
	
	public String doChangeInTerm() {

		return "termAlteration";
	}
	
	public String doChangeInContribution() {

		return "contributionAlteration";
	}
	
	public String doUpdateInformation(){
		
		return "updateinformation";
	}

	public String getBodyMes() {
		return bodyMes;
	}

	public void setBodyMes(String bodyMes) {
		this.bodyMes = bodyMes;
	}
	
	
	
	
}