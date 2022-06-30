package com.aetins.customerportal.web.service;

import java.math.BigDecimal;
import java.util.List;

import com.aetins.customerportal.web.dto.BankCodeLOVDTO;
import com.aetins.customerportal.web.dto.CPPortalSMSDto;
import com.aetins.customerportal.web.dto.ClaimIntimationDTO;
import com.aetins.customerportal.web.dto.ClaimsDetailsDTO;
import com.aetins.customerportal.web.dto.ContributionAlterationDTO;
import com.aetins.customerportal.web.dto.CustomerInfoNonFinDTO;
import com.aetins.customerportal.web.dto.CustomerOutstandingDTO;
import com.aetins.customerportal.web.dto.CustomerPaymentsDetailsDTO;
import com.aetins.customerportal.web.dto.DepositDTO;
import com.aetins.customerportal.web.dto.FundDetailsDTO;
import com.aetins.customerportal.web.dto.FundsDto;
import com.aetins.customerportal.web.dto.ListItem;
import com.aetins.customerportal.web.dto.MasterListDTO;
import com.aetins.customerportal.web.dto.PolicyDetailsDTO;
import com.aetins.customerportal.web.dto.ProtectionBenifitDTO;
import com.aetins.customerportal.web.dto.ReceiptSummaryDTO;
import com.aetins.customerportal.web.dto.RedirectionDTO;
import com.aetins.customerportal.web.dto.ReinStatementDTO;
import com.aetins.customerportal.web.dto.RiderDTO;
import com.aetins.customerportal.web.dto.SearchPolicyDTO;
import com.aetins.customerportal.web.dto.ServiceReqNotesDTO;
import com.aetins.customerportal.web.dto.StatementDTO;
import com.aetins.customerportal.web.dto.StrategyDTO;
import com.aetins.customerportal.web.dto.SummaryDTO;

import salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub;



public interface CustomerPortalServices {

	public PhaseOneServiceStub getServiceProxy() throws Exception;

	/* public List<BillingDTO> getBuillingDetails(String billingNo); */
	public List<CustomerOutstandingDTO> getCustOutstanding(String policyNo);

	// added by Harmain

	public List<DepositDTO> getMyDepositDTO(BigDecimal custNo);

	public List<FundsDto> getMyFunds(String policyNo,BigDecimal seqno);

	// end
	/**
	 * Method to get Payments details for policy no
	 * 
	 * @author viswakarthick
	 * @param policyNo
	 * @throws Exception
	 */
	public List<CustomerPaymentsDetailsDTO> getCustPayments(String policyNo);

	/**
	 * Method to get policy details for customer
	 * 
	 * @author viswakarthick
	 * @throws Exception
	 */
	public List<PolicyDetailsDTO> getPolicyLists(BigDecimal custNo);

	public List<StatementDTO> getEstatementPolicys(BigDecimal custReNo, String language, String policyStatus);

	public List<MasterListDTO> getMasterLov(String language, String clstatus);

	//added by kathar
	public List<ServiceReqNotesDTO> getservicerequestnotes(String language, String clstatus);
	
	public List<FundDetailsDTO> getPartialFundDetails(String policyNo, String lang, BigDecimal seqno);
	
	public List<BankCodeLOVDTO> getbankcode(String language, String type);
	
	/*
	 * public List<PartialWithdrawalDTO> getContributionValue(String
	 * policyNo,String lang);
	 */
	public List<RedirectionDTO> getRedirectionDetails(String policyNo, String lang,BigDecimal seqno);

	public List<FundDetailsDTO> getFundDetails(String policyNo, String lang);

	/*
	 * public List<PartialWithdrawalDTO> getBankDetails(String
	 * customerBank,String lang);
	 */

	public SearchPolicyDTO getOutstandingAmount(String policyNo, String language);

	public List<ReinStatementDTO> getMyReinstatement(BigDecimal custRefNo, String language);

	public CustomerInfoNonFinDTO getPersonalAndAddressDetails(String policyNo, String lang);

	public List<RiderDTO> getRiderDetails(String policyNo, String pLanguage);

	// modified vishal
	public List<RiderDTO> getRiderDropDown(String policyNo, String pLanguage);
	
	public List<ProtectionBenifitDTO> getProtectionBenifit(String policyNo, String lang);
	
	public String getAllowedTransaction(String policyNo, String tranType);
	
	public List<ListItem> getFundDetail(String lang, String Ptype, String policyNo, String keyTwo);
	
	public SummaryDTO getCustomerPolicySummary(BigDecimal custNo);
	
	public ReceiptSummaryDTO getReceiptSummary(String policyNo);
	
	public SearchPolicyDTO getSearchPolicyDetails(String policyNo, String lang, BigDecimal seqno);
	
	public List<ClaimsDetailsDTO> getCustClaims(BigDecimal custRefNo);
	
	public ContributionAlterationDTO getContributionAlteration(String policyNo, String lang,BigDecimal seqno);
	
	public String benefitSAVlidation(String policyNo, String planCode, String relation, BigDecimal sa);
	
	public List<StrategyDTO> sellingStrategyForStrategyTab(String policyNo);
	
	public List<StrategyDTO> buyingStrategyForStrategyTab(String policyNo);
	
	
	//claim intimation
    public ClaimIntimationDTO getintimatordetails(String identype, String idenno);
	
	public List<ListItem> geteventgroup(String lang, String type, String policyNo, String keyTwo);
	
	public List<ListItem> getidlist(String lang, String type, String policyNo, String keyTwo);
	
	public List<ListItem> gettitle(String lang, String type, String policyNo, String keyTwo);
	
	public List<ListItem> getcasueloss(String lang, String type, String policyNo, String keyTwo);
	
	//Swithcing Investment yes or no
		public String getcheckalu(String language, String policyno);
		
		public String getcheckpartialwithdraw(String language, String policyno, BigDecimal seqno, String type);
		
	
}
