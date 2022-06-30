package com.aetins.customerportal.web.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.core.faceletstaglib.SecurityLibrary;
import com.aetins.customerportal.web.dto.BankCodeLOVDTO;
import com.aetins.customerportal.web.dto.BillingDTO;
import com.aetins.customerportal.web.dto.CPPortalSMSDto;
import com.aetins.customerportal.web.dto.ClaimIntimationDTO;
import com.aetins.customerportal.web.dto.ClaimsDetailsDTO;
import com.aetins.customerportal.web.dto.ContributionAlterationDTO;
import com.aetins.customerportal.web.dto.CustomerInfoNonFinDTO;
import com.aetins.customerportal.web.dto.CustomerNFContactDTO;
import com.aetins.customerportal.web.dto.CustomerOutstandingDTO;
import com.aetins.customerportal.web.dto.CustomerPaymentsDetailsDTO;
import com.aetins.customerportal.web.dto.DepositDTO;
import com.aetins.customerportal.web.dto.FundDetailsDTO;
import com.aetins.customerportal.web.dto.FundDetailsSearchDTO;
import com.aetins.customerportal.web.dto.FundsDto;
import com.aetins.customerportal.web.dto.LifeAssuredDTO;
import com.aetins.customerportal.web.dto.ListItem;
import com.aetins.customerportal.web.dto.MasterListDTO;
import com.aetins.customerportal.web.dto.NomineeDetailsDTO;
import com.aetins.customerportal.web.dto.PartialWithdrawalDTO;
import com.aetins.customerportal.web.dto.PolicyBenefitDTO;
import com.aetins.customerportal.web.dto.PolicyDetailsDTO;
import com.aetins.customerportal.web.dto.ProtectionBenifitDTO;
import com.aetins.customerportal.web.dto.ReceiptSummaryDTO;
import com.aetins.customerportal.web.dto.RedirectionDTO;
import com.aetins.customerportal.web.dto.ReinStatementDTO;
import com.aetins.customerportal.web.dto.ReinStatementsDTO;
import com.aetins.customerportal.web.dto.RiderDTO;
import com.aetins.customerportal.web.dto.SearchPolicyDTO;
import com.aetins.customerportal.web.dto.ServiceReqNotesDTO;
import com.aetins.customerportal.web.dto.StatementDTO;
import com.aetins.customerportal.web.dto.StrategyDTO;
import com.aetins.customerportal.web.dto.SumAssuredAlterationDTO;
import com.aetins.customerportal.web.dto.SummaryDTO;
import com.aetins.customerportal.web.dto.TermAlterationDTO;
import com.aetins.customerportal.web.entity.CpLogsErros;
import com.aetins.customerportal.web.service.CpLogsErrosBL;
import com.aetins.customerportal.web.service.CustomerPortalServices;
import com.aetins.customerportal.web.utils.AppSettingURL;
import com.aetins.customerportal.web.utils.BSLUtils;

import salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub;
import salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.*;



@Service
public class CustomerPortalServicesImpl implements CustomerPortalServices {
	
	@Autowired
	CpLogsErrosBL cpLogsErrosBL;
	
	private static final Logger logger = Logger.getLogger(CustomerPortalServicesImpl.class);

    @Override
	public PhaseOneServiceStub getServiceProxy() throws Exception {
		System.out.println(AppSettingURL.FINAL_URL);
		return new PhaseOneServiceStub(AppSettingURL.FINAL_URL);
	}

    
    
	public ReceiptSummaryDTO getReceiptSummary(String policyNo) {
		ReceiptSummaryDTO receiptSummaryDTO = new ReceiptSummaryDTO();
		try {
			logger.info("::::Entered inside try block of getReceiptSummary():::::::"+policyNo);
			ReceiptSummaryTypeUser receiptSummaryTypeUser = new ReceiptSummaryTypeUser();
			BfnGetReceiptSummary customerElement = new BfnGetReceiptSummary();
			customerElement.setPPolicyNo(policyNo);
			BfnGetReceiptSummaryResponse res = getServiceProxy().bfnGetReceiptSummary(customerElement);
			receiptSummaryTypeUser = res.getResult();
			if (BSLUtils.isNotNull(receiptSummaryTypeUser)) {
				receiptSummaryDTO.setPolicyCurrency(receiptSummaryTypeUser.getPolicyCurrency());
				receiptSummaryDTO.setReceiptAmt(receiptSummaryTypeUser.getReceiptAmt());
				receiptSummaryDTO.setReceiptCount(receiptSummaryTypeUser.getReceiptCount());
			}
			logger.info(":::::Result for getReceiptSummary fn ::::::for policyNo :::"+policyNo);
		} catch (Exception e) {
			logger.info("Inside catch block of getReceiptSummary()" +e.getMessage());
			e.printStackTrace();
		}

		return receiptSummaryDTO;
	}

	public List<CustomerOutstandingDTO> getCustOutstanding(String policyNo) {
		List<CustomerOutstandingDTO> outstandingList = new ArrayList<CustomerOutstandingDTO>();

		CustomerPolOutsTypeUserArray valuesArray = new CustomerPolOutsTypeUserArray();
		CustomerPolOutsTypeUser[] customerPolOutsTypeUserArray;
		CustomerPolOutsTypeUser customerPolOutsTypeUser;
		try {
			logger.info("::::Entered inside try block of getCustOutstanding():::::::"+policyNo);
			BfnCustomerPolOuts customerElement = new BfnCustomerPolOuts();
			customerElement.setPPolicyNo(policyNo);
			BfnCustomerPolOutsResponse res = getServiceProxy().bfnCustomerPolOuts(customerElement);
			valuesArray = res.getResult();
			customerPolOutsTypeUserArray = valuesArray.getCustomerPolOutsTypeUser();

			if (BSLUtils.isNotNull(customerPolOutsTypeUserArray))
				for (int i = 0; i < customerPolOutsTypeUserArray.length; i++) {
					customerPolOutsTypeUser = customerPolOutsTypeUserArray[i];
					CustomerOutstandingDTO customerOutstandingDTO = new CustomerOutstandingDTO();
					customerOutstandingDTO.setDueDate(customerPolOutsTypeUser.getDueDate());
					customerOutstandingDTO.setDueAmount(customerPolOutsTypeUser.getDueAmount());
					customerOutstandingDTO.setReceiptingDate(customerPolOutsTypeUser.getReceiptDate());
					customerOutstandingDTO.setStatus(customerPolOutsTypeUser.getDueStatus());
					customerOutstandingDTO.setOutstandingCurrencyCode(customerPolOutsTypeUser.getCurrencyCode());
					outstandingList.add(customerOutstandingDTO);
				}
			logger.info(":::::Result for getCustOutstanding fn ::::::for list size() policyNo :::"+outstandingList.size()+","+policyNo);
		} catch (Exception e) {
			CpLogsErros cpLogsErros = new CpLogsErros();
			cpLogsErros.setUserid(SecurityLibrary.getFullLoggedInUser().getVuserName());
			cpLogsErros.setException(e.getMessage());
			cpLogsErros.setRefno(SecurityLibrary.getFullLoggedInUser().getNcustRefNo());
			cpLogsErros.setType("bfnCustomerPolOuts");
			cpLogsErrosBL.insertexception(cpLogsErros);
			e.printStackTrace();
		}
		System.out.println(outstandingList);
		return outstandingList;
	}

	/**
	 * Method to get Deposit details for customer
	 * @author Nithya
	 * @param customer referance no
	 * @throws Exception
	 */

	public List<DepositDTO> getMyDepositDTO(BigDecimal custNo) {
		List<DepositDTO> depositDTOList = new ArrayList<DepositDTO>();

		CustomerPolDepTypeUserArray valuesArray = new CustomerPolDepTypeUserArray();
		CustomerPolDepTypeUser[] customerPolDepTypeUserArray;
		CustomerPolDepTypeUser customerPolDepTypeUser;
		try {
			logger.info("::::Entered inside try block of getMyDepositDTO():::::::"+custNo);
			BfnCustomerPolDeposit customerElement = new BfnCustomerPolDeposit();
			customerElement.setPCustNo(custNo);
			BfnCustomerPolDepositResponse res = getServiceProxy().bfnCustomerPolDeposit(customerElement);
			valuesArray = res.getResult();
			customerPolDepTypeUserArray = valuesArray.getCustomerPolDepTypeUser();

			if (BSLUtils.isNotNull(customerPolDepTypeUserArray))
				for (int i = 0; i < customerPolDepTypeUserArray.length; i++) {
					customerPolDepTypeUser = customerPolDepTypeUserArray[i];
					DepositDTO getMyDepositDTO = new DepositDTO();
					getMyDepositDTO.setPolicyNo(customerPolDepTypeUser.getPolicyNumber());
					getMyDepositDTO.setDepositType(customerPolDepTypeUser.getDepositType());
					getMyDepositDTO.setDepositAmount(customerPolDepTypeUser.getDepositAmount());
					getMyDepositDTO.setDepositCurrancyCode(customerPolDepTypeUser.getCurrencyCode());
					depositDTOList.add(getMyDepositDTO);
				}
			logger.info(":::::Result for getMyDepositDTO fn ::::::for list size() custNo :::"+depositDTOList.size()+","+custNo);
		} catch (Exception e) {
			CpLogsErros cpLogsErros = new CpLogsErros();
			cpLogsErros.setUserid(SecurityLibrary.getFullLoggedInUser().getVuserName());
			cpLogsErros.setException(e.getMessage());
			cpLogsErros.setRefno(SecurityLibrary.getFullLoggedInUser().getNcustRefNo());
			cpLogsErros.setType("bfnCustomerPolDeposit");
			cpLogsErrosBL.insertexception(cpLogsErros);
			e.printStackTrace();
		}
		System.out.println(depositDTOList);
		return depositDTOList;
	}

	public StatementDTO getStatementDetail(String policyNo, String lang) {

		CustomerUnitDetailsColc customerUnitDetails = new CustomerUnitDetailsColc();
		StatementDTO statementDetail = new StatementDTO();
		CustomerUnitHeaderTypeUser unitHeaderTypeUser = new CustomerUnitHeaderTypeUser();
		CustomerUnitDetailsTypeUser[] unitDetailsArray;
		List<FundDetailsDTO> fundList = new ArrayList<FundDetailsDTO>();
		// CustomerPolicyDetailTypeUser
		try {
			logger.info("::::Entered inside try block of getStatementDetail():::::::"+policyNo+"@@@"+lang);
			BfnUnitStatement customerElement = new BfnUnitStatement();
			customerElement.setPPolicyNo(policyNo);
			customerElement.setPLanguage(lang);
			BfnUnitStatementResponse res = getServiceProxy().bfnUnitStatement(customerElement);
			unitHeaderTypeUser = res.getResult();

			customerUnitDetails = unitHeaderTypeUser.getFundDetails();
			if (BSLUtils.isNotNull(customerUnitDetails)) {
				unitDetailsArray = customerUnitDetails.getArray();
				for (int i = 0; i < unitDetailsArray.length; i++) {
					CustomerUnitDetailsTypeUser custUnitDet = unitDetailsArray[i];
					FundDetailsDTO fundDTO = new FundDetailsDTO();
					fundDTO.setFundName(custUnitDet.getFundName());
					fundDTO.setAllocationPercentage(custUnitDet.getSharePer());
					fundDTO.setAvailableUnits(custUnitDet.getAvailableUnits());
					fundDTO.setUnitsPrice(custUnitDet.getUnitPrice());
					fundDTO.setFundCrr(custUnitDet.getFundCurrency());
					fundDTO.setExchangeRate(custUnitDet.getExchangeRate());
					fundDTO.setFundValueInFundCrr(custUnitDet.getFundValue());
					fundDTO.setFundValueInPlanCrr(custUnitDet.getPlanValue());
					fundDTO.setTotalFund(custUnitDet.getPlanValue().intValue());
					fundList.add(fundDTO);
				}
				logger.info(":::::Result for getStatementDetail fn ::::::for list size() policyNo :::"+fundList.size()+","+policyNo+","+lang);
			}

			if (BSLUtils.isNotNull(unitHeaderTypeUser)) {
				statementDetail.setFundList(fundList);
				statementDetail.setDistributor(unitHeaderTypeUser.getDistributorName());
				statementDetail.setRelationshipManger(unitHeaderTypeUser.getRelationshipManager());
				statementDetail.setName(unitHeaderTypeUser.getCustomerName());
				statementDetail.setAddress(unitHeaderTypeUser.getCustomerAddress());
				statementDetail.setPlanName(unitHeaderTypeUser.getPlanName());
				Calendar tmpCommDateCal = unitHeaderTypeUser.getCommencementDate();
				if (BSLUtils.isNotNull(tmpCommDateCal)) {
					Date tmpCommDateDate = tmpCommDateCal.getTime();
					statementDetail.setCommencementDate(tmpCommDateDate);
				}
				statementDetail.setTerm(unitHeaderTypeUser.getPlanTerm());
				statementDetail.setPaymentFrequency(unitHeaderTypeUser.getPaymentFrequency());
				statementDetail.setContribution(unitHeaderTypeUser.getContributionAmt());
				statementDetail.setPolicyNo(unitHeaderTypeUser.getPlanNumber());
				statementDetail.setStatusDescription(unitHeaderTypeUser.getPlanStatus());

				Calendar tmpPremiumDueDateCal = unitHeaderTypeUser.getContDueDate();
				if (BSLUtils.isNotNull(tmpPremiumDueDateCal)) {
					Date tmpPremiumDueDateDate = tmpPremiumDueDateCal.getTime();
					statementDetail.setPremiumDueDate(tmpPremiumDueDateDate);
				}

				Calendar tmpPolicyEndDateCal = unitHeaderTypeUser.getMaturityDate();
				if (BSLUtils.isNotNull(tmpPolicyEndDateCal)) {
					Date tmpPolicyEndDateDate = tmpPolicyEndDateCal.getTime();
					statementDetail.setPolicyEndDate(tmpPolicyEndDateDate);
				}
				statementDetail.setCurrencyCode(unitHeaderTypeUser.getPlanCurrency());
				// statementDetail.setArabianPlanName(unitHeaderTypeUser.getArabianPlanName());
				statementDetail.setBaseCurrAmt(unitHeaderTypeUser.getTotalBasicCont());
				statementDetail.setPolCurrAmt(unitHeaderTypeUser.getTotalRecContribution());
				statementDetail.setFundValue(statementDetail.getFundValue());
				statementDetail.setPolicyFrequency(unitHeaderTypeUser.getPaymentFrequency());
			}
			logger.info(":::::Result for getStatementDetail fn ::::::for policyNo and lang::::::"+policyNo+","+lang);
		} catch (Exception e) {
			CpLogsErros cpLogsErros = new CpLogsErros();
			cpLogsErros.setUserid(SecurityLibrary.getFullLoggedInUser().getVuserName());
			cpLogsErros.setException(e.getMessage());
			cpLogsErros.setRefno(SecurityLibrary.getFullLoggedInUser().getNcustRefNo());
			cpLogsErros.setType("bfnUnitStatement");
			cpLogsErrosBL.insertexception(cpLogsErros);
			e.printStackTrace();
		}
		System.out.println(statementDetail);
		return statementDetail;
	}

	public SummaryDTO getCustomerPolicySummary(BigDecimal custNo) {

		SummaryDTO summaryDetail = new SummaryDTO();
		CustomerPolicySummaryTypeUserArray customerPolicySummaryTypeList = new CustomerPolicySummaryTypeUserArray();
		CustomerPolicySummaryTypeUser customerPolicySummaryTypeUser = new CustomerPolicySummaryTypeUser();
		CustomerPolicySummaryTypeUser[] customerpolicyArray;
		// CustomerPolicySummaryTypeUser
		try {
			logger.info("::::Entered inside try block of getCustomerPolicySummary():::::::"+custNo);
			BfnCustomerSummary customerElement = new BfnCustomerSummary();
			customerElement.setPCustNo(custNo);

			BfnCustomerSummaryResponse res = getServiceProxy().bfnCustomerSummary(customerElement);
			customerPolicySummaryTypeList = res.getResult();
			customerpolicyArray = customerPolicySummaryTypeList.getCustomerPolicySummaryTypeUser();

			if (BSLUtils.isNotNull(customerpolicyArray))
				for (int i = 0; i < customerpolicyArray.length; i++) {
					customerPolicySummaryTypeUser = customerpolicyArray[i];
					if (BSLUtils.isNotNull(customerPolicySummaryTypeUser)) {
						summaryDetail.setPolicyCount(customerPolicySummaryTypeUser.getPolicyCount());
						summaryDetail.setFundCount(customerPolicySummaryTypeUser.getFundCount());
						summaryDetail.setOutStandingCount(customerPolicySummaryTypeUser.getOsCount());
						summaryDetail.setOutStandingAmount(customerPolicySummaryTypeUser.getOsAmount());
						summaryDetail.setFundValue(customerPolicySummaryTypeUser.getFundValue());
						summaryDetail.setSuspenseAmount(customerPolicySummaryTypeUser.getSuspAmount());
					}
				}
		logger.info(":::::Result for getCustomerPolicySummary fn ::::::for custNo"+custNo);
		} catch (Exception e) {
			CpLogsErros cpLogsErros = new CpLogsErros();
			cpLogsErros.setUserid(SecurityLibrary.getFullLoggedInUser().getVuserName());
			cpLogsErros.setException(e.getMessage());
			cpLogsErros.setRefno(SecurityLibrary.getFullLoggedInUser().getNcustRefNo());
			cpLogsErros.setType("Bfn_Customer_Summary");
			cpLogsErrosBL.insertexception(cpLogsErros);
		}
		return summaryDetail;
	}

	public List<RedirectionDTO> getRedirectionDetails(String policyNo, String lang, BigDecimal seqno) {
		List<RedirectionDTO> redirectionList = new ArrayList<RedirectionDTO>();
		CustomerUnitDetailsColc customerUnitDetails = new CustomerUnitDetailsColc();
		CustomerUnitHeaderTypeUser unitHeaderTypeUser = new CustomerUnitHeaderTypeUser();
		CustomerUnitDetailsTypeUser[] unitDetailsArray = null;
		try {
			logger.info("::::Entered inside try block of getRedirectionDetails():::::::"+policyNo+"@@@"+lang);
			BfnUnitStatement customerElement = new BfnUnitStatement();
			customerElement.setPPolicyNo(policyNo);
			customerElement.setPLanguage(lang);
			//customerElement.setPSeqNo(seqno);
			BfnUnitStatementResponse res = getServiceProxy().bfnUnitStatement(customerElement);
			unitHeaderTypeUser = res.getResult();
			customerUnitDetails = unitHeaderTypeUser.getFundDetails();
			if (BSLUtils.isNotNull(customerUnitDetails)) {
				unitDetailsArray = customerUnitDetails.getArray();
			}
			for (int i = 0; i < unitDetailsArray.length; i++) {
				CustomerUnitDetailsTypeUser custUnitDet = unitDetailsArray[i];
				RedirectionDTO redirectionDTO = new RedirectionDTO();
				redirectionDTO.setFundCode(custUnitDet.getFundCode());
				redirectionDTO.setFundName(custUnitDet.getFundName());
				redirectionDTO.setFundCurrency(custUnitDet.getFundCurrency());
				redirectionDTO.setFundValue(custUnitDet.getFundValue());
				redirectionDTO.setCriteria(custUnitDet.getSharePer());
				redirectionDTO.setUnitPrice(custUnitDet.getUnitPrice());
				redirectionDTO.setUnits(custUnitDet.getAvailableUnits());
				redirectionDTO.setFundPlanCurr(custUnitDet.getPlanValue());
				redirectionDTO.setNewRecord(false);
				redirectionList.add(redirectionDTO);

			}
			logger.info(":::::Result for getRedirectionDetails fn ::::::for policyNo and lang "+redirectionList.size()+","+policyNo+","+lang);
		} catch (Exception e) {
			e.getMessage();
		}
		return redirectionList;
	}

	public List<ListItem> getFundDetail(String lang, String Ptype, String policyNo, String keyTwo) {

		List<ListItem> listOfFundNames = new ArrayList<ListItem>();
		MasterLovTypeUserArray valuesArray = new MasterLovTypeUserArray();
		MasterLovTypeUser[] masterLovTypeUserArray;
		MasterLovTypeUser masterLovTypeUser;
		try {
			logger.info("::::Entered inside try block of getFundDetail():::::::"+lang+"@@@"+Ptype+"@@@"+policyNo+"@@@"+keyTwo);
			BfnGetMasterLov customerElement = new BfnGetMasterLov();
			customerElement.setPLanguage(lang);
			customerElement.setPType(Ptype);
			customerElement.setPSearchKeyOne(policyNo);
		    customerElement.setPSearchKeyTwo(keyTwo);
			BfnGetMasterLovResponse res = getServiceProxy().bfnGetMasterLov(customerElement);
			valuesArray = res.getResult();
			masterLovTypeUserArray = valuesArray.getMasterLovTypeUser();

			if (BSLUtils.isNotNull(masterLovTypeUserArray))
				for (int i = 0; i < masterLovTypeUserArray.length; i++) {
					masterLovTypeUser = masterLovTypeUserArray[i];
					ListItem fundDto = new ListItem();
					fundDto.setCode(masterLovTypeUser.getCode());
					fundDto.setDescription(masterLovTypeUser.getDescOne());
					fundDto.setCurrencyCode(masterLovTypeUser.getDescTwo().toUpperCase());
					fundDto.setUnits(masterLovTypeUser.getDescThree());

					listOfFundNames.add(fundDto);
				}
			logger.info(":::::Result for getFundDetail fn ::::::for custRefNo"+lang+","+Ptype+","+policyNo+","+keyTwo);
		} catch (Exception e) {
			e.getMessage();
		}
		return listOfFundNames;
	}

	public List<ClaimsDetailsDTO> getCustClaims(BigDecimal custRefNo) {
		List<ClaimsDetailsDTO> claimsList = new ArrayList<ClaimsDetailsDTO>();

		CustomerClaimsTypeUserArray valuesArray = new CustomerClaimsTypeUserArray();
		CustomerClaimsTypeUser[] customerClaimsTypeUserArray;
		CustomerClaimsTypeUser customerClaimsTypeUser;
		try {
			logger.info("::::Entered inside try block of getCustClaims():::::::"+custRefNo);
			BfnCustomerPolClaims customerElement = new BfnCustomerPolClaims();
			customerElement.setPCustNo(custRefNo);
			BfnCustomerPolClaimsResponse res = getServiceProxy().bfnCustomerPolClaims(customerElement);
			valuesArray = res.getResult();
			customerClaimsTypeUserArray = valuesArray.getCustomerClaimsTypeUser();

			if (BSLUtils.isNotNull(customerClaimsTypeUserArray))
				for (int i = 0; i < customerClaimsTypeUserArray.length; i++) {
					customerClaimsTypeUser = customerClaimsTypeUserArray[i];
					ClaimsDetailsDTO claimsDetailsDTO = new ClaimsDetailsDTO();
					claimsDetailsDTO.setClaimNo(customerClaimsTypeUser.getClaimNo());
					claimsDetailsDTO.setSubClaimNo(customerClaimsTypeUser.getSubClaimNo());
					claimsDetailsDTO.setPolicyNo(customerClaimsTypeUser.getPolicyNo());
					
					claimsDetailsDTO.setIncidentDate(customerClaimsTypeUser.getIncidentDate()!=null?customerClaimsTypeUser.getIncidentDate():null);
//					claimsDetailsDTO.setIncidentDate(customerClaimsTypeUser.getIncidentDate());
					
					claimsDetailsDTO.setNotificationDate(customerClaimsTypeUser.getNotificationDate()!=null?customerClaimsTypeUser.getNotificationDate():null);
//					claimsDetailsDTO.setNotificationDate(customerClaimsTypeUser.getNotificationDate());
					
					
					claimsDetailsDTO.setBenifitDescription(customerClaimsTypeUser.getBenefitDesc());
					claimsDetailsDTO.setClaimAmount(customerClaimsTypeUser.getClaimAmount());
					claimsDetailsDTO.setClaimStatus(customerClaimsTypeUser.getClaimStatus());
					claimsDetailsDTO.setDueCurrency(customerClaimsTypeUser.getCurrencyCode());
					
					claimsDetailsDTO.setSettermentDate(customerClaimsTypeUser.getSettilmentDate()!=null?customerClaimsTypeUser.getSettilmentDate():null);
//					claimsDetailsDTO.setSettermentDate(customerClaimsTypeUser.getSettilmentDate());
					
					claimsList.add(claimsDetailsDTO);
				}
			logger.info(":::::Result for getCustClaims fn ::::::for list size() custRefNo"+claimsList.size()+","+custRefNo);
		} catch (Exception e) {
			CpLogsErros cpLogsErros = new CpLogsErros();
			cpLogsErros.setUserid(SecurityLibrary.getFullLoggedInUser().getVuserName());
			cpLogsErros.setException(e.getMessage());
			cpLogsErros.setRefno(SecurityLibrary.getFullLoggedInUser().getNcustRefNo());
			cpLogsErros.setType("bfnCustomerPolClaims");
			cpLogsErrosBL.insertexception(cpLogsErros);
			e.printStackTrace();
		}
		System.out.println(claimsList);
		return claimsList;
	}

	public SearchPolicyDTO getSearchPolicyDetails(String policyNo, String lang, BigDecimal seqno) {

		PolicyBenefitDTO policyBenefitDetails = new PolicyBenefitDTO();
		SearchPolicyDTO searchPolicyDetails = new SearchPolicyDTO();
		CustomerClaimsTypeUserArray valuesArray = new CustomerClaimsTypeUserArray();
		BfnCustomerPolicyDetailResponse customerPolicyDetailsresponse;
		CustomerPolicyDetailTypeUser customerpolicyDetailsList;

		CustomerUnitDetailsColc customerUnitDetailsColc = new CustomerUnitDetailsColc();
		CustomerPolicyNomineeColc customerPolicyNomineeColc = new CustomerPolicyNomineeColc();
		CustomerPolicyAssuredColc customerPolicyAssuredColc = new CustomerPolicyAssuredColc();
		CustomerPolicyBenefitColc customerPolicyBenefitColc = new CustomerPolicyBenefitColc();

		List<FundDetailsSearchDTO> fundDetailsList = new ArrayList<FundDetailsSearchDTO>();
		List<LifeAssuredDTO> lifeAssuredList = new ArrayList<LifeAssuredDTO>();
		List<NomineeDetailsDTO> NomineeDetailsList = new ArrayList<NomineeDetailsDTO>();

		List<PolicyBenefitDTO> policyOthersBenefitList = new ArrayList<PolicyBenefitDTO>();

		// CustomerPolicyDetailTypeUser
		try {
			logger.info("::::Entered inside try block of getSearchPolicyDetails():::::::"+policyNo+"@@@"+lang);
			BfnCustomerPolicyDetail customerElement = new BfnCustomerPolicyDetail();
			customerElement.setPPolicyNo(policyNo);
			customerElement.setPLanguage(lang);
			//customerElement.setPSeqNo(seqno);
			BfnCustomerPolicyDetailResponse res = getServiceProxy().bfnCustomerPolicyDetail(customerElement);
			customerpolicyDetailsList = res.getResult();
			customerUnitDetailsColc = customerpolicyDetailsList.getFundDetails();
			customerPolicyNomineeColc = customerpolicyDetailsList.getNomineeDetail();
			customerPolicyAssuredColc = customerpolicyDetailsList.getLifeAssured();
			customerPolicyBenefitColc = customerpolicyDetailsList.getOtherBenefit();

			//
			if (BSLUtils.isNotNull(customerPolicyBenefitColc.getArray())) {
				CustomerPolicyBenefitTypeUser[] customerPolicyBenefitArray = customerPolicyBenefitColc.getArray();
				for (int i = 0; i < customerPolicyBenefitArray.length; i++) {
					PolicyBenefitDTO otherDetails = new PolicyBenefitDTO();
					CustomerPolicyBenefitTypeUser otherBenefitArray = customerPolicyBenefitArray[i];

					otherDetails.setOtherBenefit(otherBenefitArray.getOtherBenefit());
					otherDetails.setEffectiveDate(otherBenefitArray.getEffectiveFrom());
					otherDetails.setCoverAmount(otherBenefitArray.getCoverAmount());
					otherDetails.setEffectiveUntil(otherBenefitArray.getEffectiveUntil());
					otherDetails.setOtherBenefitstatus(otherBenefitArray.getStatusDesc());
					// otherBenefitArray.getStatusDesc();
					policyOthersBenefitList.add(otherDetails);
				}
				logger.info(":::::Result for getSearchPolicyDetails fn ::::::for list size() policyNo and lang"+policyOthersBenefitList.size()+","+policyNo+","+lang);
			}
			// 1
			if (BSLUtils.isNotNull(customerUnitDetailsColc.getArray())) {
				CustomerUnitDetailsTypeUser[] customerUnitDetailsArray = customerUnitDetailsColc.getArray();
				for (int i = 0; i < customerUnitDetailsArray.length; i++) {
					FundDetailsSearchDTO fundDetails = new FundDetailsSearchDTO();
					CustomerUnitDetailsTypeUser customerUnitArray = customerUnitDetailsArray[i];

					fundDetails.setAvaliableUnits(customerUnitArray.getAvailableUnits());
					fundDetails.setFundName(customerUnitArray.getFundName());
					fundDetails.setSharePercentage(customerUnitArray.getSharePer());
					fundDetails.setUnitPrice(customerUnitArray.getUnitPrice());
					fundDetails.setFundCurrency(customerUnitArray.getFundCurrency());
					fundDetails.setExchangeRate(customerUnitArray.getExchangeRate());
					fundDetails.setFundValue(customerUnitArray.getFundValue());
					fundDetails.setPlanValue(customerUnitArray.getPlanValue());

					fundDetailsList.add(fundDetails);
				}
				logger.info(":::::Result for getSearchPolicyDetails fn ::::::for list size() policyNo and lang"+fundDetailsList.size()+","+policyNo+","+lang);
			}
			// 2
			if (BSLUtils.isNotNull(customerPolicyAssuredColc.getArray())) {
				CustomerPolicyAssuredTypeUser[] customerPolicyAssuredArray = customerPolicyAssuredColc.getArray();
				for (int i = 0; i < customerPolicyAssuredArray.length; i++) {
					LifeAssuredDTO lifeAssuredDetails = new LifeAssuredDTO();
					CustomerPolicyAssuredTypeUser customePolicyAssuredArray = customerPolicyAssuredArray[i];

					lifeAssuredDetails.setLifeAssuredName(customePolicyAssuredArray.getLifeAssuredName());
					lifeAssuredDetails.setDob(customePolicyAssuredArray.getLifeDob());
					lifeAssuredDetails.setRelationship(customePolicyAssuredArray.getRelationship());
					lifeAssuredDetails.setCoverType(customePolicyAssuredArray.getCoverType());
					lifeAssuredDetails.setSumCover(customePolicyAssuredArray.getSumCover());
					lifeAssuredDetails.setCoverName(customePolicyAssuredArray.getCoverName());
					lifeAssuredDetails.setLoading(customePolicyAssuredArray.getLoading());
					lifeAssuredDetails.setDiscount(customePolicyAssuredArray.getDiscount());
					lifeAssuredDetails.setGrossPremium(customePolicyAssuredArray.getGrossPremium());
					lifeAssuredDetails.setNetPremium(customePolicyAssuredArray.getNetPremium());
					
					lifeAssuredDetails.setPlanTerm(customePolicyAssuredArray.getPlanTerm());
				    lifeAssuredDetails.setBenefitType( customePolicyAssuredArray.getDisplaySa());
					 
					lifeAssuredList.add(lifeAssuredDetails);
				}
				logger.info(":::::Result for getSearchPolicyDetails fn ::::::for list size() policyNo and lang"+lifeAssuredList.size()+","+policyNo+","+lang);
			}
			// 3
			if (BSLUtils.isNotNull(customerPolicyNomineeColc.getArray())) {
				CustomerPolicyNomineeTypeUser[] customerPolicyNomineeArray = customerPolicyNomineeColc.getArray();
				for (int i = 0; i < customerPolicyNomineeArray.length; i++) {
					NomineeDetailsDTO nomineeDetails = new NomineeDetailsDTO();
					CustomerPolicyNomineeTypeUser nomineePolicyDetailsArray = customerPolicyNomineeArray[i];

					nomineeDetails.setNomineeDetails(nomineePolicyDetailsArray.getNomineeName());
					nomineeDetails.setRelationship(nomineePolicyDetailsArray.getRelationShip());
					nomineeDetails.setSharePercentage(nomineePolicyDetailsArray.getSharePer());
					NomineeDetailsList.add(nomineeDetails);
				}
				logger.info(":::::Result for getSearchPolicyDetails fn ::::::for list size() policyNo and lang"+NomineeDetailsList.size()+","+policyNo+","+lang);
			}
			searchPolicyDetails.setFundDetailsList(fundDetailsList);
			searchPolicyDetails.setLifeAssuredList(lifeAssuredList);
			searchPolicyDetails.setNomineeDetailsList(NomineeDetailsList);
			searchPolicyDetails.setPolicyBenefitList(policyOthersBenefitList);
			
			searchPolicyDetails.setCommencementDate(customerpolicyDetailsList.getCommencementDate()!=null?customerpolicyDetailsList.getCommencementDate():null);
			searchPolicyDetails.setIssuanceDate(customerpolicyDetailsList.getIssueDate()!=null?customerpolicyDetailsList.getIssueDate():null);
			searchPolicyDetails.setMaturityDate(customerpolicyDetailsList.getMaturityDate()!=null?customerpolicyDetailsList.getMaturityDate():null);
				
			
//			searchPolicyDetails.setCommencementDate(customerpolicyDetailsList.getCommencementDate());
//			searchPolicyDetails.setIssuanceDate(customerpolicyDetailsList.getIssueDate());
//			searchPolicyDetails.setMaturityDate(customerpolicyDetailsList.getMaturityDate());
			
			searchPolicyDetails.setFirstMember(customerpolicyDetailsList.getFirstMemeber());
			searchPolicyDetails.setSecondMember(customerpolicyDetailsList.getSecondMember());
			searchPolicyDetails.setPlanStatus(customerpolicyDetailsList.getPlanStatus());
			searchPolicyDetails.setProposalNumber(customerpolicyDetailsList.getProposalNumber());
			
			searchPolicyDetails.setProposalDate(customerpolicyDetailsList.getProposalDate()!=null?customerpolicyDetailsList.getProposalDate():null);
//		    searchPolicyDetails.setProposalDate(customerpolicyDetailsList.getProposalDate());
			
			
			searchPolicyDetails.setPlanCurrency(customerpolicyDetailsList.getPlanCurrency());
			searchPolicyDetails.setPlanTerm(customerpolicyDetailsList.getPlanTerm());
			searchPolicyDetails.setRemainingTerms(customerpolicyDetailsList.getRemainingTerms());
			searchPolicyDetails.setTotalRecContract(customerpolicyDetailsList.getTotalRecContr());
			searchPolicyDetails.setPlanNumber(customerpolicyDetailsList.getPlanNumber());
			searchPolicyDetails.setProductName(customerpolicyDetailsList.getProductName());
			searchPolicyDetails.setPaymentMode(customerpolicyDetailsList.getPaymentFrequency());
			searchPolicyDetails.setRegularContract(customerpolicyDetailsList.getRegularContr());
			searchPolicyDetails.setAnnualContract(customerpolicyDetailsList.getAnnualContr());
			searchPolicyDetails.setLastPaidContr(customerpolicyDetailsList.getLastPaidContr());
			
			searchPolicyDetails.setLastPaidDate(customerpolicyDetailsList.getLastPaidDate()!=null?customerpolicyDetailsList.getLastPaidDate():null);
			searchPolicyDetails.setPremiumDueDate(customerpolicyDetailsList.getPremDueDate()!=null?customerpolicyDetailsList.getPremDueDate():null);
			searchPolicyDetails.setTotalDue(customerpolicyDetailsList.getTotalDue()!=null?customerpolicyDetailsList.getTotalDue():null);
			
//			searchPolicyDetails.setLastPaidDate(customerpolicyDetailsList.getLastPaidDate());
//			searchPolicyDetails.setPremiumDueDate(customerpolicyDetailsList.getPremDueDate());
//			searchPolicyDetails.setTotalDue(customerpolicyDetailsList.getTotalDue());
			
			searchPolicyDetails.setAssignment(customerpolicyDetailsList.getAssignment());
			searchPolicyDetails.setContrHolidays(customerpolicyDetailsList.getContrHoliday());
			//searchPolicyDetails.setTotalRecContract(customerpolicyDetailsList.getPlanValue());
			searchPolicyDetails.setPremiumPayStatus(customerpolicyDetailsList.getPremPayStatus());
			searchPolicyDetails.setPremiumPayEndDate(customerpolicyDetailsList.getPremPayEnd());
			searchPolicyDetails.setPremiumPayTerm(customerpolicyDetailsList.getPremPayTerm());
			searchPolicyDetails.setPayMethod(customerpolicyDetailsList.getPayMethod());
			searchPolicyDetails.setPolicyYear(customerpolicyDetailsList.getPolicyYear());
			searchPolicyDetails.setPaidDueCount(customerpolicyDetailsList.getPaidDueCount());
			searchPolicyDetails.setErrorCode(customerpolicyDetailsList.getContrHolidayErr());
			searchPolicyDetails.setMaxPremium(customerpolicyDetailsList.getMaxPremium());
			searchPolicyDetails.setMinPremium(customerpolicyDetailsList.getMinPremium());
			
			searchPolicyDetails.setHolidayEndDate(customerpolicyDetailsList.getHolEndDate()!=null?customerpolicyDetailsList.getHolEndDate():null);
			searchPolicyDetails.setAutoDbDate(customerpolicyDetailsList.getAutoDbDate()!=null?customerpolicyDetailsList.getAutoDbDate():null);
			
//			searchPolicyDetails.setHolidayEndDate(customerpolicyDetailsList.getHolEndDate());
			
			
			/*
			 * if(BSLUtils.isNotNull(customerpolicyDetailsList.getPayMethod())){
			 */
			/*
			 * if(customerpolicyDetailsList.getPayMethod().equals("DIRECT")){
			 * searchPolicyDetails.setAutoDbDate(null); }else{
			 */
			
			searchPolicyDetails.setFundValue(customerpolicyDetailsList.getFundValue());
			searchPolicyDetails.setPendingRegCount(customerpolicyDetailsList.getPendingReqCount());
			searchPolicyDetails.setDistributorName(customerpolicyDetailsList.getDistributorName());
			searchPolicyDetails.setRelationManager(customerpolicyDetailsList.getRelationManager());

			searchPolicyDetails.setRecCurrencyCode(customerpolicyDetailsList.getRecCurrencyCode());
			/*DecimalFormat df = new DecimalFormat("#,###.00");
			searchPolicyDetails.setPlanValue(df.format(customerpolicyDetailsList.getTotalRecContr()));*/
			//DecimalFormat df = new DecimalFormat("#,###.00");
			searchPolicyDetails.setPlanValue(customerpolicyDetailsList.getPlanValue());
			//searchPolicyDetails.setPwamount(customerpolicyDetailsList.getPwAmount());
			
			
		} catch (Exception e) {
			CpLogsErros cpLogsErros = new CpLogsErros();
			cpLogsErros.setUserid(SecurityLibrary.getFullLoggedInUser().getVuserName());
			cpLogsErros.setException(e.getMessage());
			cpLogsErros.setRefno(SecurityLibrary.getFullLoggedInUser().getNcustRefNo());
			cpLogsErros.setType("BfnCustomerPolicyDetail");
			cpLogsErrosBL.insertexception(cpLogsErros);	
		}
		System.out.println(searchPolicyDetails);
		return searchPolicyDetails;
	}

	/*
	 * Fetching estatement policy details
	 */
	public List<StatementDTO> getEstatementPolicys(BigDecimal custReNo, String language, String policyStatus) {
		List<StatementDTO> statementDTOList = new ArrayList<StatementDTO>();
		BfnCustomerPolicyLov bfnCustomerPolicyLov = new BfnCustomerPolicyLov();
		bfnCustomerPolicyLov.setPCustNo(custReNo);
		bfnCustomerPolicyLov.setPLanguage(language);
		bfnCustomerPolicyLov.setPStatusCode(policyStatus);
		try {
			logger.info("::::Entered inside try block of getEstatementPolicys():::::::"+custReNo+"@@@"+language+"@@@"+policyStatus);
			BfnCustomerPolicyLovResponse customerPolicyLovRes = getServiceProxy()
					.bfnCustomerPolicyLov(bfnCustomerPolicyLov);
			if (BSLUtils.isNotNull(customerPolicyLovRes)) {
				PolicyListingLovTypeUserArray policyListingLovTypeUserArray = customerPolicyLovRes.getResult();
				if (BSLUtils.isNotNull(policyListingLovTypeUserArray)) {
					PolicyListingLovTypeUser[] policyListingLovTypeUserList = policyListingLovTypeUserArray
							.getPolicyListingLovTypeUser();
					for (int i = 0; i < policyListingLovTypeUserList.length; i++) {
						PolicyListingLovTypeUser policyListingLovTypeUser = policyListingLovTypeUserList[i];
						StatementDTO statementDTO = new StatementDTO();
						statementDTO.setPolicyNo(policyListingLovTypeUser.getPolicyNo());
						statementDTOList.add(statementDTO);
					}
				}
			}
			logger.info(":::::Result for getEstatementPolicys fn ::::::for list size() custReNo,policyStatus and language"+statementDTOList.size()+","+custReNo+","+language+","+policyStatus);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			logger.info("Inside catch block of getEstatementPolicys()" +e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statementDTOList;
	}


	/**
	 * Method to get Payments details for policy no
	 * 
	 * @author viswakarthick
	 * @param policyNo
	 * @throws Exception
	 */
	@Override
	public List<CustomerPaymentsDetailsDTO> getCustPayments(String policyNo) {
		List<CustomerPaymentsDetailsDTO> paymentDetailsList = new ArrayList<CustomerPaymentsDetailsDTO>();
		CustomerPolReceiptTypeUserArray valuesArray = new CustomerPolReceiptTypeUserArray();
		CustomerPolReceiptTypeUser[] customerPolReceiptTypeUserArray;
		CustomerPolReceiptTypeUser customerPolReceiptTypeUser;
		try {
			logger.info("::::Entered inside try block of getCustPayments():::::::"+policyNo );
			BfnCustomerPolReceipts customerElement = new BfnCustomerPolReceipts();
			customerElement.setPPolicyNo(policyNo);
			BfnCustomerPolReceiptsResponse res = getServiceProxy().bfnCustomerPolReceipts(customerElement);
			valuesArray = res.getResult();
			customerPolReceiptTypeUserArray = valuesArray.getCustomerPolReceiptTypeUser();

			if (BSLUtils.isNotNull(customerPolReceiptTypeUserArray))
				for (int i = 0; i < customerPolReceiptTypeUserArray.length; i++) {
					customerPolReceiptTypeUser = customerPolReceiptTypeUserArray[i];
					CustomerPaymentsDetailsDTO custPaymentDto = new CustomerPaymentsDetailsDTO();
					custPaymentDto.setReceiptNo(customerPolReceiptTypeUser.getReceiptNo());
					custPaymentDto.setReceiptDate(customerPolReceiptTypeUser.getReceiptDate());
					custPaymentDto.setReceiptType(customerPolReceiptTypeUser.getTransactionType());
					custPaymentDto.setDueAmount(customerPolReceiptTypeUser.getDueAmount());
					custPaymentDto.setReceiptAmount(customerPolReceiptTypeUser.getReceiptAmt());
					custPaymentDto.setReceiptCurrency(customerPolReceiptTypeUser.getReceiptCurrency());
					custPaymentDto.setReceiptStatus(customerPolReceiptTypeUser.getReceiptStatus());
					custPaymentDto.setReceiptMode(customerPolReceiptTypeUser.getReceiptMode());
					custPaymentDto.setDueCurrency(customerPolReceiptTypeUser.getDueCurrency());
					paymentDetailsList.add(custPaymentDto);
				}
			logger.info(":::::Result for getCustPayments fn ::::::for list size() policyNo and language"+paymentDetailsList.size()+","+policyNo);
		} catch (Exception e) {
			logger.info("Inside catch block of getCustPayments()" +e.getMessage());
			e.printStackTrace();
		}
		return paymentDetailsList;
	}

	/**
	 * Method to get policy details for customer
	 * 
	 * @author viswakarthick
	 * @throws Exception
	 */
	@Override
	public List<PolicyDetailsDTO> getPolicyLists(BigDecimal custNo) {
		List<PolicyDetailsDTO> policyList = new ArrayList<PolicyDetailsDTO>();

		CustomerPolicyListTypeUserArray valuesArray = new CustomerPolicyListTypeUserArray();
		CustomerPolicyListTypeUser[] customerPolicyListTypeUserArray;
		CustomerPolicyListTypeUser customerPolicyListTypeUser;
		try {
			logger.info("::::Entered inside try block of getPolicyLists():::::::"+custNo );
			BfnCustomerPolList customerElement = new BfnCustomerPolList();
			customerElement.setPCustNo(custNo);
			BfnCustomerPolListResponse res = getServiceProxy().bfnCustomerPolList(customerElement);
			valuesArray = res.getResult();
			customerPolicyListTypeUserArray = valuesArray.getCustomerPolicyListTypeUser();

			if (BSLUtils.isNotNull(customerPolicyListTypeUserArray))
				for (int i = 0; i < customerPolicyListTypeUserArray.length; i++) {
					customerPolicyListTypeUser = customerPolicyListTypeUserArray[i];
					PolicyDetailsDTO policyDTO = new PolicyDetailsDTO();
					policyDTO.setPolicyNo(customerPolicyListTypeUser.getPolicyNo());
					policyDTO.setPolicyName(customerPolicyListTypeUser.getPolicyName());
					policyDTO.setPolicyStatus(customerPolicyListTypeUser.getPolicyStatus());
					policyDTO.setPremiumStatus(customerPolicyListTypeUser.getPremiumStatus());
					policyDTO.setOutstandingAmt(customerPolicyListTypeUser.getDueAmount());
					policyDTO.setPremiumDueDate(customerPolicyListTypeUser.getDueDate());
					policyDTO.setOutstandingCurrencyCode(customerPolicyListTypeUser.getCurrencyCode());
					policyDTO.setSeqno(customerPolicyListTypeUser.getSeqNo());
					policyList.add(policyDTO);
				}
			logger.info(":::::Result for getMyFunds fn ::::::for list size() custNo and language"+policyList.size()+","+custNo);
		} catch (Exception e) {
			logger.info("Inside catch block of getMyFunds()" +e.getMessage());
			e.printStackTrace();

		}
		System.out.println(policyList);
		return policyList;
	}

	public List<BillingDTO> getBuillingDetails(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method to get Funds details for policy no
	 * 
	 * @author Harmain Zainab
	 * @param policyNo
	 * @throws Exception
	 */
	@Override
	public List<FundsDto> getMyFunds(String policyNo,BigDecimal Seqno) {
		List<FundsDto> myFundsList = new ArrayList<FundsDto>();
		CustomerPolFundTypeUserArray valuesArray = new CustomerPolFundTypeUserArray();
		CustomerPolFundTypeUser[] customerPolFundsTypeUserArray;
		CustomerPolFundTypeUser customerPolFundsTypeUser;
		try {
			logger.info("::::Entered inside try block of getMyFunds():::::::"+policyNo);
			BfnCustomerPolFunds customerElement = new BfnCustomerPolFunds();
			customerElement.setPPolicyNo(policyNo);
			//customerElement.setPSeqNo(Seqno);
			BfnCustomerPolFundsResponse res = getServiceProxy().bfnCustomerPolFunds(customerElement);
			valuesArray = res.getResult();
			customerPolFundsTypeUserArray = valuesArray.getCustomerPolFundTypeUser();

			if (BSLUtils.isNotNull(customerPolFundsTypeUserArray))
				for (int i = 0; i < customerPolFundsTypeUserArray.length; i++) {
					customerPolFundsTypeUser = customerPolFundsTypeUserArray[i];
					FundsDto custFundsDto = new FundsDto();
					custFundsDto.setBalanceUnit(customerPolFundsTypeUser.getUnits());
					custFundsDto.setFundName(customerPolFundsTypeUser.getFundName());
					custFundsDto.setFundValue(customerPolFundsTypeUser.getFundValue());
					custFundsDto.setNavValue(customerPolFundsTypeUser.getNavValue());
					custFundsDto.setPlanValue(customerPolFundsTypeUser.getPlanValue());
					custFundsDto.setFundCurrency(customerPolFundsTypeUser.getFundCurrency());
					custFundsDto.setFundSharePer(customerPolFundsTypeUser.getSharePer());
					custFundsDto.setPlanCurrency(customerPolFundsTypeUser.getPolicyCurrency());
					myFundsList.add(custFundsDto);
				}
			logger.info(":::::Result for getMyFunds fn ::::::for list size() policyNo and language"+myFundsList.size()+","+policyNo);
		} catch (Exception e) {
			CpLogsErros cpLogsErros = new CpLogsErros();
			cpLogsErros.setUserid(SecurityLibrary.getFullLoggedInUser().getVuserName());
			cpLogsErros.setException(e.getMessage());
			cpLogsErros.setRefno(SecurityLibrary.getFullLoggedInUser().getNcustRefNo());
			cpLogsErros.setType("bfnCustomerPolFunds");
			cpLogsErrosBL.insertexception(cpLogsErros);
			e.printStackTrace();
		}
		return myFundsList;
	}

	@Override
	public List<MasterListDTO> getMasterLov(String language, String clstatus) {
		List<MasterListDTO> masterList = new ArrayList<MasterListDTO>();
		MasterLovTypeUserArray valuesArray = new MasterLovTypeUserArray();
		MasterLovTypeUser[] masterLovTypeUserArray;

		MasterLovTypeUser masterLovTypeUser;
		try {
			logger.info("::::Entered inside try block of getMasterLov():::::::"+language +"@@@@"+clstatus);
			BfnGetMasterLov customerElement = new BfnGetMasterLov();
			customerElement.setPLanguage(language);
			customerElement.setPType(clstatus);
			BfnGetMasterLovResponse res = getServiceProxy().bfnGetMasterLov(customerElement);
			valuesArray = res.getResult();
			masterLovTypeUserArray = valuesArray.getMasterLovTypeUser();

			if (BSLUtils.isNotNull(masterLovTypeUserArray))
				for (int i = 0; i < masterLovTypeUserArray.length; i++) {
					masterLovTypeUser = masterLovTypeUserArray[i];
					MasterListDTO masterListDTO = new MasterListDTO();
					masterListDTO.setCode(masterLovTypeUser.getCode());
					masterListDTO.setDesc1(masterLovTypeUser.getDescOne());
					masterListDTO.setDesc2(masterLovTypeUser.getDescTwo());
					masterListDTO.setDesc3(masterLovTypeUser.getDescThree());
					masterList.add(masterListDTO);
				}
			logger.info(":::::Result for getMasterLov fn ::::::for policyNo and language"+language +","+clstatus);
		} catch (Exception e) {
			logger.info("Inside catch block of getFundDetails()" +e.getMessage());
			e.printStackTrace();
		}
		return masterList;
	}

	public List<FundDetailsDTO> getFundDetails(String policyNo, String lang) {

		CustomerUnitDetailsColc customerUnitDetails = new CustomerUnitDetailsColc();
		CustomerUnitHeaderTypeUser unitHeaderTypeUser = new CustomerUnitHeaderTypeUser();
		CustomerUnitDetailsTypeUser[] unitDetailsArray;
		List<FundDetailsDTO> fundList = new ArrayList<FundDetailsDTO>();
		// CustomerPolicyDetailTypeUser
		try {
			logger.info("::::Entered inside try block of getFundDetails():::::::"+policyNo +"@@@@"+lang);
			BfnUnitStatement customerElement = new BfnUnitStatement();
			customerElement.setPPolicyNo(policyNo);
			customerElement.setPLanguage(lang);
			BfnUnitStatementResponse res = getServiceProxy().bfnUnitStatement(customerElement);
			unitHeaderTypeUser = res.getResult();

			customerUnitDetails = unitHeaderTypeUser.getFundDetails();
			if (BSLUtils.isNotNull(customerUnitDetails)) {
				unitDetailsArray = customerUnitDetails.getArray();
				for (int i = 0; i < unitDetailsArray.length; i++) {
					CustomerUnitDetailsTypeUser custUnitDet = unitDetailsArray[i];
					FundDetailsDTO fundDTO = new FundDetailsDTO();
					fundDTO.setFundName(custUnitDet.getFundName());
					fundDTO.setAllocationPercentage(custUnitDet.getSharePer());
					fundDTO.setAvailableUnits(custUnitDet.getAvailableUnits());
					fundDTO.setUnitsPrice(custUnitDet.getUnitPrice());
					fundDTO.setFundCrr(custUnitDet.getFundCurrency());
					fundDTO.setExchangeRate(custUnitDet.getExchangeRate());
					fundDTO.setFundValueInFundCrr(custUnitDet.getFundValue());
					fundDTO.setFundValueInPlanCrr(custUnitDet.getPlanValue());
					// fundDTO.setFundCode(custUnitDet.getFundCode());
					fundDTO.setTotalFund(custUnitDet.getPlanValue().intValue());
					fundList.add(fundDTO);
				}
			}
			logger.info(":::::Result for getFundDetails fn ::::::for list size() policyNo and language"+fundList.size()+","+policyNo+","+lang);
		} catch (Exception e) {
			logger.info("Inside catch block of getFundDetails()" +e.getMessage());
			e.printStackTrace();
		}
		return fundList;
	}

	
	
	// vinod
	public String getAllowedTransaction(String policyNo, String tranType) {
		
		String result = null;
		try {
			logger.info("::::Entered inside try block of getAllowedTransaction():::::::"+policyNo +"@@@@"+tranType);
			BfnAllowTransaction allowTransaction = new BfnAllowTransaction();
			allowTransaction.setPPolicyNo(policyNo);
			allowTransaction.setPTranType(tranType);

			BfnAllowTransactionResponse res = getServiceProxy().bfnAllowTransaction(allowTransaction);
			result = res.getResult();
            logger.info(":::::Result for getAllowedTransaction fn ::::::"+result +"for policy No and type"+policyNo+","+tranType);
		} catch (Exception e) {
			logger.info("Inside catch block of getAllowedTransaction()" +e.getMessage());
			e.printStackTrace();
		}
		return result;

	}	
	
	

	public SearchPolicyDTO getOutstandingAmount(String policyNo, String language) {

		SearchPolicyDTO searchPolicyDTO = new SearchPolicyDTO();

		CustomerPolOutsTypeUserArray customerPolOutsTypeUserList = new CustomerPolOutsTypeUserArray();
		CustomerPolOutsTypeUser customerPolOutsType = new CustomerPolOutsTypeUser();
		CustomerPolOutsTypeUser[] customerPolOutsTypeArray;
		try {
			logger.info("::::Entered inside try block of getOutstandingAmount():::::::"+policyNo +"@@@@"+language);
			BfnGetPolOutsummary customerElement = new BfnGetPolOutsummary();
			customerElement.setPPolicyNo(policyNo);
			customerElement.setPLanguage(language);

			BfnGetPolOutsummaryResponse res = getServiceProxy().bfnGetPolOutsummary(customerElement);
			customerPolOutsTypeUserList = res.getResult();
			customerPolOutsTypeArray = customerPolOutsTypeUserList.getCustomerPolOutsTypeUser();

			if (BSLUtils.isNotNull(customerPolOutsTypeArray))
				for (int i = 0; i < customerPolOutsTypeArray.length; i++) {
					customerPolOutsType = customerPolOutsTypeArray[i];
					if (BSLUtils.isNotNull(customerPolOutsType)) {
						/*
						 * searchPolicyDTO.setDueAmount(customerPolOutsType.
						 * getDueAmount());
						 */

					}
				}
			 logger.info(":::::Result for getOutstandingAmount fn ::::::for policy No and language"+policyNo+","+language);
		} catch (Exception e) {
			CpLogsErros cpLogsErros = new CpLogsErros();
			cpLogsErros.setUserid(SecurityLibrary.getFullLoggedInUser().getVuserName());
			cpLogsErros.setException(e.getMessage());
			cpLogsErros.setRefno(SecurityLibrary.getFullLoggedInUser().getNcustRefNo());
			cpLogsErros.setType("bfnGetPolOutsummary");
			cpLogsErrosBL.insertexception(cpLogsErros);
			e.printStackTrace();
		}
		return searchPolicyDTO;
	}

	public List<ReinStatementDTO> getMyReinstatement(BigDecimal custRefNo, String language) {
		List<ReinStatementDTO> rinstatementList = new ArrayList<ReinStatementDTO>();

		PolicyReinstateTypeUserArray valuesArray = new PolicyReinstateTypeUserArray();
		PolicyReinstateTypeUser[] policyReinstateTypeUserArray;
		PolicyReinstateTypeUser policyReinstateTypeUser;
		try {
			logger.info("::::Entered inside try block of getMyReinstatement():::::::" + custRefNo + "@@@@" + language);
			BfnGetReinstate customerElement = new BfnGetReinstate();
			customerElement.setPCustNo(custRefNo);
			customerElement.setPLanguage(language);
			BfnGetReinstateResponse res = getServiceProxy().bfnGetReinstate(customerElement);
			valuesArray = res.getResult();
			policyReinstateTypeUserArray = valuesArray.getPolicyReinstateTypeUser();
			ReinStatementDTO reinStatementDTO = null;
			if (BSLUtils.isNotNull(policyReinstateTypeUserArray)) {
				for (int i = 0; i < policyReinstateTypeUserArray.length; i++) {
					policyReinstateTypeUser = policyReinstateTypeUserArray[i];
					reinStatementDTO = new ReinStatementDTO();
					reinStatementDTO.setPolicyNo(policyReinstateTypeUser.getPolicyNo());
					reinStatementDTO.setProductName(policyReinstateTypeUser.getProductName());
					reinStatementDTO.setOutstandingAmount(policyReinstateTypeUser.getOutstandingAmt());

					String[] tmp = policyReinstateTypeUser.getFrequency().split("~");

					if (tmp.length >= 1) {

						String planFrequesncy = tmp[0];
						reinStatementDTO.setContribFreq(planFrequesncy);
						reinStatementDTO.setPlanStatus("Lapse");
						/*String planStatus = tmp[1];
						if(planStatus.equalsIgnoreCase("L")){
							reinStatementDTO.setPlanStatus("Lapse");
						}else{
							reinStatementDTO.setPlanStatus("Paidup");
						}*/
						
					}
					// reinStatementDTO.setContribFreq(policyReinstateTypeUser.getFrequency());
					reinStatementDTO.setRegulatContrib(policyReinstateTypeUser.getContribution());
					reinStatementDTO.setPlanTerm(policyReinstateTypeUser.getPlanTerm());
					reinStatementDTO.setContribTerm(policyReinstateTypeUser.getPlanPremTerm());
					reinStatementDTO.setPlanCurr(policyReinstateTypeUser.getPlanCurrency());

					Calendar tmpPremiumDueDateCals = policyReinstateTypeUser.getIssueDate();
					Date tmpPremiumDueDateDat = tmpPremiumDueDateCals.getTime();
					reinStatementDTO.setIssueDate(tmpPremiumDueDateDat);

					Calendar tmpPremiumDueDateCal = policyReinstateTypeUser.getLapseDate();
					Date tmpPremiumDueDateDate = tmpPremiumDueDateCal.getTime();
					reinStatementDTO.setLapseDate(tmpPremiumDueDateDate);

					Calendar tmpPremiumDueDateCalender = policyReinstateTypeUser.getPremDueDate();
					Date tmpPremiumDueDateDa = tmpPremiumDueDateCalender.getTime();
					reinStatementDTO.setPremDue(tmpPremiumDueDateDa);

					rinstatementList.add(reinStatementDTO);
					logger.info(":::::Result for getMyReinstatement fn ::::::for list size() custRefNo and language"
							+ rinstatementList.size() + "," + custRefNo + "," + language);
				}

			}

		} catch (Exception e) {
			logger.info("Inside catch block of getOutstandingAmount()" + e.getMessage());
			e.printStackTrace();
		}
		return rinstatementList;
	}

	public List<PartialWithdrawalDTO> getBankDetails(String customerBank, String lang) {

		List<PartialWithdrawalDTO> depositDTOList = new ArrayList<PartialWithdrawalDTO>();

		CustomerBankTypeUserArray customerBankTypeUserArray = new CustomerBankTypeUserArray();
		CustomerBankTypeUser[] customerBankArray;
		CustomerBankTypeUser customerBankTypeUser;

		try {
			logger.info("::::Entered inside try block of getBankDetails():::::::"+customerBank +"@@@@"+lang);
			BfnGetCompany customerElement = new BfnGetCompany();
			customerElement.setPType(customerBank);
			customerElement.setPLanguage(lang);
			BfnGetCompanyResponse res = getServiceProxy().bfnGetCompany(customerElement);

			customerBankTypeUserArray = res.getResult();
			customerBankArray = customerBankTypeUserArray.getCustomerBankTypeUser();
			
			for (int i = 0; i < customerBankArray.length; i++) {
				customerBankTypeUser = customerBankArray[i];
				PartialWithdrawalDTO partialWithdrawalDTO = new PartialWithdrawalDTO();
				partialWithdrawalDTO.setBankCode(customerBankTypeUser.getBankCode());
				partialWithdrawalDTO.setBranchCode(customerBankTypeUser.getBranchCode());
				partialWithdrawalDTO.setBankName(customerBankTypeUser.getBranchName());
				depositDTOList.add(partialWithdrawalDTO);
			}
			
			logger.info(":::::Result for getBankDetails fn ::::::for list size() policyNo and language"+depositDTOList.size()+","+customerBank+","+lang);

		} catch (Exception e) {
			logger.info("Inside catch block of getOutstandingAmount()" +e.getMessage());
			e.printStackTrace();
		}
		return depositDTOList;
	}

	public List<PartialWithdrawalDTO> getContributionValue(String policyNo, String lang) {

		List<PartialWithdrawalDTO> depositDTOList = new ArrayList<PartialWithdrawalDTO>();

		ServiceReqNotesTypeUserArray serviceReqNotesTypeUserArray = new ServiceReqNotesTypeUserArray();
		ServiceReqNotesTypeUser[] ServiceReqNotesArray;
		ServiceReqNotesTypeUser serviceReqNotesTypeUser;

		try {
			logger.info("::::Entered inside try block of getContributionValue():::::::"+policyNo +"@@@@"+lang);
			BfnGetPolicyPlan customerElement = new BfnGetPolicyPlan();
			customerElement.setPPolicyNo(policyNo);
			customerElement.setPLanguage(lang);
			BfnGetPolicyPlanResponse res = getServiceProxy().bfnGetPolicyPlan(customerElement);

			serviceReqNotesTypeUserArray = res.getResult();
			ServiceReqNotesArray = serviceReqNotesTypeUserArray.getServiceReqNotesTypeUser();
			for (int i = 0; i < ServiceReqNotesArray.length; i++) {
				serviceReqNotesTypeUser = ServiceReqNotesArray[i];
				PartialWithdrawalDTO partialWithdrawalDTO = new PartialWithdrawalDTO();
				partialWithdrawalDTO.setNotes(serviceReqNotesTypeUser.getNotes());
				depositDTOList.add(partialWithdrawalDTO);
			}
			logger.info(":::::Result for getContributionValue fn ::::::for list size() custRefNo and language"+depositDTOList.size()+","+policyNo+","+lang);
		} catch (Exception e) {
			logger.info("Inside catch block of getContributionValue()" +e.getMessage());
			e.printStackTrace();
		}
		return depositDTOList;
	}
	
	

	
	public List<ReinStatementsDTO> getBenifitReinstate(String policyNo, String lang) {

		List<ReinStatementsDTO> reinbenifitDTOList = new ArrayList<ReinStatementsDTO>();

		ReinstateBenefitsTypeUserArray reinTypeUserArray = new ReinstateBenefitsTypeUserArray();
		ReinstateBenefitsTypeUser[] reinstateBenefitsTypeUserArray;
		ReinstateBenefitsTypeUser reinstateBenefitsTypeUser;

		try {
			logger.info("::::Entered inside try block of getBenifitReinstate():::::::"+policyNo +"@@@@"+lang);
			BfnGetReinstateBenefits customerElement = new BfnGetReinstateBenefits();
			customerElement.setPPolicyNo(policyNo);
			customerElement.setPLanguage(lang);
			BfnGetReinstateBenefitsResponse res = getServiceProxy().bfnGetReinstateBenefits(customerElement);

			reinTypeUserArray = res.getResult();
			reinstateBenefitsTypeUserArray = reinTypeUserArray.getReinstateBenefitsTypeUser();
			for (int i = 0; i < reinstateBenefitsTypeUserArray.length; i++) {
				reinstateBenefitsTypeUser = reinstateBenefitsTypeUserArray[i];
				ReinStatementsDTO reinStatementsDTO = new ReinStatementsDTO();
				reinStatementsDTO.setBenifitType(reinstateBenefitsTypeUser.getBeneiftDesc());
				reinStatementsDTO.setFirstCover(reinstateBenefitsTypeUser.getPlanHolderName());
				reinStatementsDTO.setSecondCover(reinstateBenefitsTypeUser.getSecondLifeName());

				Calendar firstDobCal = reinstateBenefitsTypeUser.getPlanHolderDob();
				Date firstDobDate = firstDobCal.getTime();
				reinStatementsDTO.setFirstCoverDob(firstDobDate);

				if (BSLUtils.isNotNull(reinstateBenefitsTypeUser.getSecondLifeDob())) {
					Calendar secondDobCal = reinstateBenefitsTypeUser.getSecondLifeDob();
					Date secondDob = secondDobCal.getTime();
					reinStatementsDTO.setSecondCoverDob(secondDob);
				}
				
				reinStatementsDTO.setFirstCoverbenifitAmt(reinstateBenefitsTypeUser.getPlanHolderBeneift());
				reinStatementsDTO.setSecondCoverbenifitAmt(reinstateBenefitsTypeUser.getSecondLifeBeneift());
				reinStatementsDTO.setFirstCoverbenifitTerm(reinstateBenefitsTypeUser.getPlanHolderTerm());
				reinStatementsDTO.setSecondCoverbenifitTerm(reinstateBenefitsTypeUser.getSecondLifeTerm());
				reinbenifitDTOList.add(reinStatementsDTO);
			}
			logger.info(":::::Result for getBenifitReinstate fn ::::::for list size() policyNo and language"+reinbenifitDTOList.size()+","+policyNo+","+lang);
		} catch (Exception e) {
			logger.info("Inside catch block of getBenifitReinstate()" +e.getMessage());
			e.printStackTrace();
		}
		return reinbenifitDTOList;
	}

	public ContributionAlterationDTO getContributionAlteration(String policyNo, String lang,BigDecimal seqno) {

		ContributionAlterationDTO contributionAterationDetails = new ContributionAlterationDTO();
		CustomerUnitHeaderTypeUser unitHeaderTypeUser = new CustomerUnitHeaderTypeUser();

		// CustomerPolicyDetailTypeUser
		try {
			logger.info("::::Entered inside try block of getContributionAlteration():::::::"+policyNo +"@@@@"+lang);
			BfnUnitStatement customerElement = new BfnUnitStatement();
			customerElement.setPPolicyNo(policyNo);
			customerElement.setPLanguage(lang);
			//customerElement.setPSeqNo(seqno);
			BfnUnitStatementResponse res = getServiceProxy().bfnUnitStatement(customerElement);
			unitHeaderTypeUser = res.getResult();

			if (BSLUtils.isNotNull(unitHeaderTypeUser)) {
				contributionAterationDetails.setPlanName(unitHeaderTypeUser.getPlanName());
				contributionAterationDetails.setCurrentFrequency(unitHeaderTypeUser.getPaymentFrequency());
				contributionAterationDetails.setCurrentContribution(unitHeaderTypeUser.getContributionAmt());
				contributionAterationDetails.setPolicyNo(unitHeaderTypeUser.getPlanNumber());
				//contributionAterationDetails.setCummulativeAmt(unitHeaderTypeUser.getCumulativeAmt());
			}
			logger.info(":::::Result for getContributionAlteration fn ::::::for policyNo and language"+policyNo+","+lang);
		} catch (Exception e) {
			logger.info("Inside catch block of getContributionAlteration()" +e.getMessage());
			e.printStackTrace();
		}
		System.out.println(contributionAterationDetails);
		return contributionAterationDetails;
	}

	public SumAssuredAlterationDTO getSumAssuredAlteration(String policyNo, String lang,BigDecimal seqno) {

		SumAssuredAlterationDTO sumAssuredAlterationDetails = new SumAssuredAlterationDTO();
		CustomerUnitHeaderTypeUser unitHeaderTypeUser = new CustomerUnitHeaderTypeUser();

		try {
			logger.info("::::Entered inside try block of getSumAssuredAlteration():::::::"+policyNo +"@@@@"+lang);
			BfnUnitStatement customerElement = new BfnUnitStatement();
			customerElement.setPPolicyNo(policyNo);
			customerElement.setPLanguage(lang);
			//customerElement.setPSeqNo(seqno);
			
			BfnUnitStatementResponse res = getServiceProxy().bfnUnitStatement(customerElement);
			unitHeaderTypeUser = res.getResult();

			if (BSLUtils.isNotNull(unitHeaderTypeUser)) {
				sumAssuredAlterationDetails.setPlanName(unitHeaderTypeUser.getPlanName());
				// sumAssuredAlterationDetails.setCurrentSA(unitHeaderTypeUser.getContributionAmt());
				sumAssuredAlterationDetails.setPolicyNo(unitHeaderTypeUser.getPlanNumber());
			}
			logger.info(":::::Result for getSumAssuredAlteration fn ::::::for policyNo and language"+policyNo+","+lang);
		} catch (Exception e) {
			logger.info("Inside catch block of getSumAssuredAlteration()" +e.getMessage());
			e.printStackTrace();
		}
		System.out.println(sumAssuredAlterationDetails);
		return sumAssuredAlterationDetails;
	}

	public List<SumAssuredAlterationDTO> getSumCoveredAlteration(String policyNo, String lang) {

		CustomerPolicyDetailTypeUser customerpolicyDetailsList;
		CustomerPolicyAssuredColc customerPolicyAssuredColc = new CustomerPolicyAssuredColc();
		List<SumAssuredAlterationDTO> sumAssuredAlterationList = new ArrayList<SumAssuredAlterationDTO>();

		// CustomerPolicyDetailTypeUser
		try {
			logger.info("::::Entered inside try block of getSumCoveredAlteration():::::::"+policyNo +"@@@@"+lang);
			BfnCustomerPolicyDetail customerElement = new BfnCustomerPolicyDetail();
			customerElement.setPPolicyNo(policyNo);
			customerElement.setPLanguage(lang);
			BfnCustomerPolicyDetailResponse res = getServiceProxy().bfnCustomerPolicyDetail(customerElement);
			customerpolicyDetailsList = res.getResult();
			customerPolicyAssuredColc = customerpolicyDetailsList.getLifeAssured();

			if (BSLUtils.isNotNull(customerPolicyAssuredColc.getArray())) {
				CustomerPolicyAssuredTypeUser[] customerPolicyAssuredArray = customerPolicyAssuredColc.getArray();
				for (int i = 0; i < customerPolicyAssuredArray.length; i++) {
					SumAssuredAlterationDTO sumAssuredAlterationDetails = new SumAssuredAlterationDTO();
					CustomerPolicyAssuredTypeUser customePolicyAssuredArray = customerPolicyAssuredArray[i];
					sumAssuredAlterationDetails.setCurrentSA(customePolicyAssuredArray.getSumCover());
					sumAssuredAlterationList.add(sumAssuredAlterationDetails);
				}
			}
			logger.info(":::::Result for getSumAssuredAlteration fn ::::::for list size() policyNo and language"+sumAssuredAlterationList.size()+","+policyNo+","+lang);
		} catch (Exception e) {
			logger.info("Inside catch block of getSumAssuredAlteration()" +e.getMessage());
			e.printStackTrace();
		}
		return sumAssuredAlterationList;
	}

	public TermAlterationDTO getTermAlteration(String policyNo, String lang) {

		TermAlterationDTO TermAlterationDetails = new TermAlterationDTO();
		CustomerUnitHeaderTypeUser unitHeaderTypeUser = new CustomerUnitHeaderTypeUser();

		// CustomerPolicyDetailTypeUser
		try {
			logger.info("::::Entered inside try block of getTermAlteration():::::::"+policyNo +"@@@@"+lang);
			BfnUnitStatement customerElement = new BfnUnitStatement();
			customerElement.setPPolicyNo(policyNo);
			customerElement.setPLanguage(lang);
			BfnUnitStatementResponse res = getServiceProxy().bfnUnitStatement(customerElement);
			unitHeaderTypeUser = res.getResult();

			if (BSLUtils.isNotNull(unitHeaderTypeUser)) {
				TermAlterationDetails.setPlanName(unitHeaderTypeUser.getPlanName());
				// TermAlterationDetails.setCurrentTerm(unitHeaderTypeUser.getPaymentFrequency());
				TermAlterationDetails.setPolicyNo(unitHeaderTypeUser.getPlanNumber());
				TermAlterationDetails.setCurrentTerm(unitHeaderTypeUser.getPlanTerm());
			}
			logger.info(":::::Result for getTermAlteration fn ::::::for policyNo and language"+policyNo+","+lang);
		} catch (Exception e) {
			logger.info("Inside catch block of getTermAlteration()" +e.getMessage());
			e.printStackTrace();
		}
		System.out.println(TermAlterationDetails);
		return TermAlterationDetails;
	}

	/*public CustomerInfoNonFinDTO getPersonalAndAddressDetails(String policyNo, String lang) {

	
		CustomerInfoNonFinDTO customerInfoNonFinDTO = new CustomerInfoNonFinDTO();
		CustomerInfoNfTypeUser customerInfoNFTypeObject;
		CustomerContNfColc customerContactsNFList;
		CustomerAddNfColc customerAddressNfList;
		CustomerAddContNfColc customerAddressContactNFList;

		try {
			BfnGetCustomerNfaInfo customerElement = new BfnGetCustomerNfaInfo();
			customerElement.setPLanguage(lang);

			// change in policy No in stub
			customerElement.setPPolicyNo(policyNo);
			BfnGetCustomerNfaInfoResponse res = getServiceProxy().bfnGetCustomerNfaInfo(customerElement);
			customerInfoNFTypeObject = res.getResult();
			logger.info("customerInfoNFTypeObject::"+customerInfoNFTypeObject);
			if (BSLUtils.isNotNull(customerInfoNFTypeObject)) {
				customerInfoNonFinDTO.setCustRefNo(customerInfoNFTypeObject.getCustRefNo());
				customerInfoNonFinDTO.setTitleCode(customerInfoNFTypeObject.getTitleCode());
				customerInfoNonFinDTO.setGivenName(customerInfoNFTypeObject.getName());
				customerInfoNonFinDTO.setFirstName(customerInfoNFTypeObject.getFirstName());
				customerInfoNonFinDTO.setMiddleName(customerInfoNFTypeObject.getMiddleName());
				customerInfoNonFinDTO.setLastName(customerInfoNFTypeObject.getLastName());
				customerInfoNonFinDTO.setLangCode(customerInfoNFTypeObject.getLanguageCode());
				customerInfoNonFinDTO.setLangENG(customerInfoNFTypeObject.getLangEng());
				customerInfoNonFinDTO.setLangAR(customerInfoNFTypeObject.getLngAr());
				customerInfoNonFinDTO.setRaceCode(customerInfoNFTypeObject.getRaceCode());
				customerInfoNonFinDTO.setRaceENG(customerInfoNFTypeObject.getRaceEng());
				customerInfoNonFinDTO.setRaceAR(customerInfoNFTypeObject.getRaceAr());
				customerInfoNonFinDTO.setReligionCode(customerInfoNFTypeObject.getReligionCode());
				customerInfoNonFinDTO.setReligionENG(customerInfoNFTypeObject.getReligionEng());
				customerInfoNonFinDTO.setReligionAR(customerInfoNFTypeObject.getReligionAr());
				customerInfoNonFinDTO.setNationalityCode(customerInfoNFTypeObject.getNationCode());
				customerInfoNonFinDTO.setNationalityENG(customerInfoNFTypeObject.getNationEng());
				customerInfoNonFinDTO.setNationalityAR(customerInfoNFTypeObject.getNationAr());
				customerInfoNonFinDTO.setMaritalStatus(customerInfoNFTypeObject.getMaritalStatus());
				customerInfoNonFinDTO.setMaritalENG(customerInfoNFTypeObject.getMaritalEng());
				customerInfoNonFinDTO.setMaritalAR(customerInfoNFTypeObject.getMaritalAr());
				customerInfoNonFinDTO.setBirthPlace(customerInfoNFTypeObject.getBirthPlace());
				customerInfoNonFinDTO.setBirthCountry(customerInfoNFTypeObject.getBirthCountry());
				customerInfoNonFinDTO.setBrCountryENG(customerInfoNFTypeObject.getBrcountryEng());
				customerInfoNonFinDTO.setBrCountryAR(customerInfoNFTypeObject.getBrcountryAr());
				customerInfoNonFinDTO.setPropIdentityCode(customerInfoNFTypeObject.getPropIdenCode());
				
				// Modified on 03052018
				customerInfoNonFinDTO.setAddress1(customerInfoNFTypeObject.getAddOne());
				customerInfoNonFinDTO.setAddress2(customerInfoNFTypeObject.getAddTwo());
				customerInfoNonFinDTO.setAddress3(customerInfoNFTypeObject.getAddThree());
				customerInfoNonFinDTO.setEmail(customerInfoNFTypeObject.getEmailId());
				customerInfoNonFinDTO.setContactNumber(customerInfoNFTypeObject.getMobileNo());
				customerInfoNonFinDTO.setTown(customerInfoNFTypeObject.getTown());
				customerInfoNonFinDTO.setPostalCode(customerInfoNFTypeObject.getPostBox());
				customerInfoNonFinDTO.setUserDialCode(customerInfoNFTypeObject.getDialCode());
				
				logger.info("customerInfoNFTypeObject.getIdenExpDate():::"+customerInfoNFTypeObject.getIdenExpDate());
				if (BSLUtils.isNotNull(customerInfoNFTypeObject.getIdenExpDate())) {
					customerInfoNonFinDTO.setIdenExpiryDate((customerInfoNFTypeObject.getIdenExpDate().getTime()));
					logger.info("customerInfoNFTypeObject.getIdenExpDate():::"+customerInfoNFTypeObject.getIdenExpDate().getTime());
				}
				
				customerInfoNonFinDTO.setPropIdentityNo(customerInfoNFTypeObject.getPropIdenNo());
				logger.info("customerInfoNFTypeObject.getPropIdenNo()::"+customerInfoNFTypeObject.getPropIdenNo());
				customerInfoNonFinDTO.setPolicyNo(customerInfoNFTypeObject.getPolicyNo());
				logger.info("customerInfoNFTypeObject.getPolicyNo()::"+customerInfoNFTypeObject.getPolicyNo());

				// Customer Contact List
				logger.info("After customerInfoNFTypeObject block ::");
				//logger.info("customerInfoNFTypeObject.getCustomerContacts()::"+customerInfoNFTypeObject.getCustomerContacts().getArray().length);
				customerContactsNFList = customerInfoNFTypeObject.getCustomerContacts();
				logger.info("customerContactsNFList length::"+customerContactsNFList.getArray().length);
				if (BSLUtils.isNotNull(customerContactsNFList.getArray())) {
					CustomerContNfTypeUser[] customerNFPersonalContactArray = customerContactsNFList.getArray();
					List<CustomerNFContactDTO> customerNFContactDTOList = new ArrayList<CustomerNFContactDTO>();
					logger.info("Before For of Contact list::");
					for (int i = 0; i < customerNFPersonalContactArray.length; i++) {
						CustomerContNfTypeUser customerContNfTypeUser = customerNFPersonalContactArray[i];

						CustomerNFContactDTO customerNFContactDTO = new CustomerNFContactDTO();
						customerNFContactDTO.setCustRefNo(customerContNfTypeUser.getCustRefNo());
						logger.info("customerContNfTypeUser.getCustRefNo()----"+customerContNfTypeUser.getCustRefNo());
						customerNFContactDTO.setContactCode(customerContNfTypeUser.getContactCode());
						logger.info("customerContNfTypeUser.getContactCode()----"+customerContNfTypeUser.getContactCode());
						customerNFContactDTO.setContactDesc(customerContNfTypeUser.getContactDesc());
						logger.info("customerContNfTypeUser.getContactDesc()----"+customerContNfTypeUser.getContactDesc());
						customerNFContactDTO.setContactNumber(customerContNfTypeUser.getContactNumber());
						logger.info("customerContNfTypeUser.getContactNumber()----"+customerContNfTypeUser.getContactNumber());
						
						customerNFContactDTOList.add(customerNFContactDTO);
					}

					customerInfoNonFinDTO.setCustomerContactList(customerNFContactDTOList);
				}

				// Customer Address List
				customerAddressNfList = customerInfoNFTypeObject.getCustomerAddress();
				if (BSLUtils.isNotNull(customerAddressNfList.getArray())) {
					CustomerAddNfTypeUser[] customerAddressArray = customerAddressNfList.getArray();
					List<CustomerNFAddressDTO> customerNFAddressDTOList = new ArrayList<CustomerNFAddressDTO>();
					for (int i = 0; i < customerAddressArray.length; i++) {
						CustomerAddNfTypeUser customerAddNfTypeUser = customerAddressArray[i];

						CustomerNFAddressDTO customerNFAddressDTO = new CustomerNFAddressDTO();
						customerNFAddressDTO.setCustRefNo(customerAddNfTypeUser.getCustRefNo());
						customerNFAddressDTO.setAddressCode(customerAddNfTypeUser.getAddCode());
						customerNFAddressDTO.setAddresCodeDesc(customerAddNfTypeUser.getAddDesc());
						customerNFAddressDTO.setAddressSeqNo(customerAddNfTypeUser.getAddSeqNo());
						customerNFAddressDTO.setLangCode(customerAddNfTypeUser.getLanguageCode());
						customerNFAddressDTO.setAddress1(customerAddNfTypeUser.getAddOne());
						customerNFAddressDTO.setTown(customerAddNfTypeUser.getTown());
						customerNFAddressDTO.setAddress2(customerAddNfTypeUser.getAddTwo());
						customerNFAddressDTO.setAddress3(customerAddNfTypeUser.getAddThree());
						customerNFAddressDTO.setPostalCode(customerAddNfTypeUser.getPostcode());
						customerNFAddressDTO.setTown(customerAddNfTypeUser.getTown());
						customerNFAddressDTO.setTownAR(customerAddNfTypeUser.getTownInArabic());
						customerNFAddressDTO.setStateCode(customerAddNfTypeUser.getStateCode());
						customerNFAddressDTO.setStateENG(customerAddNfTypeUser.getStateEng());
						customerNFAddressDTO.setStateAR(customerAddNfTypeUser.getStateAr());
						customerNFAddressDTO.setCountryCode(customerAddNfTypeUser.getCountryCode());
						customerNFAddressDTO.setCountryENG(customerAddNfTypeUser.getCountryEng());
						customerNFAddressDTO.setCountryAR(customerAddNfTypeUser.getCountryAr());
						customerNFAddressDTO.setCorresAddress(customerAddNfTypeUser.getCorresAddress());
						customerNFAddressDTO.setUnitNo(customerAddNfTypeUser.getUnitNo());
						customerNFAddressDTO.setAdditionalNo(customerAddNfTypeUser.getAdditionalNo());
						customerNFAddressDTO.setBuildingNo(customerAddNfTypeUser.getBuildingNo());
						customerNFAddressDTO.setPoBoxNo(customerAddNfTypeUser.getPostBox());
						customerNFAddressDTO.setEnableAllValue(true);
						// Customer Address Contact List
						customerAddressContactNFList = customerAddNfTypeUser.getAddContacts();
						if (BSLUtils.isNotNull(customerAddressContactNFList.getArray())) {
							CustomerAddContNfTypeUser[] contNfTypeUserArray = customerAddressContactNFList.getArray();
							List<CustomerNFAddressContactDTO> customerNFAddressContactDTOList = new ArrayList<CustomerNFAddressContactDTO>();
							for (int j = 0; j < contNfTypeUserArray.length; j++) {
								CustomerAddContNfTypeUser customerAddContNfTypeUser = contNfTypeUserArray[j];

								CustomerNFAddressContactDTO customerNFAddressContactDTO = new CustomerNFAddressContactDTO();
								customerNFAddressContactDTO.setCustRefNo(customerAddContNfTypeUser.getCustRefNo());
								customerNFAddressContactDTO.setAddressCode(customerAddContNfTypeUser.getAddCode());
								customerNFAddressContactDTO.setAddressSeqNo(customerAddContNfTypeUser.getAddSeqNo());
								customerNFAddressContactDTO.setContactCode(customerAddContNfTypeUser.getContactCode());
								customerNFAddressContactDTO.setContactDesc(customerAddContNfTypeUser.getContactDesc());
								customerNFAddressContactDTO.setUserDialCode(customerAddContNfTypeUser.getDialCode());
								
								customerNFAddressContactDTO
										.setContactNumber(customerAddContNfTypeUser.getContactNumber());
								customerNFAddressContactDTO
										.setNewContactNo(customerAddContNfTypeUser.getContactNumber());
								customerNFAddressContactDTOList.add(customerNFAddressContactDTO);
							}
							customerNFAddressDTO.setAddressContactList(customerNFAddressContactDTOList);
						}
						customerNFAddressDTOList.add(customerNFAddressDTO);
					}

					customerInfoNonFinDTO.setCustomerAddressList(customerNFAddressDTOList);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return customerInfoNonFinDTO;
	}*/
	
	
	public CustomerInfoNonFinDTO getPersonalAndAddressDetails(String policyNo, String lang) {

		CustomerInfoNonFinDTO customerInfoNonFinDTO = new CustomerInfoNonFinDTO();
		CustomerInfoNfTypeUser customerInfoNFTypeObject;
		List<CustomerNFContactDTO> listing = new ArrayList<CustomerNFContactDTO>();

		try {
			logger.info("::::Entered inside try block of getPersonalAndAddressDetails():::::::"+policyNo +"@@@@"+lang);
			BfnGetCustomerNfaInfo customerElement = new BfnGetCustomerNfaInfo();
			customerElement.setPLanguage(lang);

			// change in policy No in stub
			customerElement.setPPolicyNo(policyNo);
			BfnGetCustomerNfaInfoResponse res = getServiceProxy().bfnGetCustomerNfaInfo(customerElement);
			customerInfoNFTypeObject = res.getResult();
			if (BSLUtils.isNotNull(customerInfoNFTypeObject)) {
				customerInfoNonFinDTO.setCustRefNo(customerInfoNFTypeObject.getCustRefNo());
				customerInfoNonFinDTO.setTitleCode(customerInfoNFTypeObject.getTitleCode());
				customerInfoNonFinDTO.setGivenName(customerInfoNFTypeObject.getName());
				customerInfoNonFinDTO.setFirstName(customerInfoNFTypeObject.getFirstName());
				customerInfoNonFinDTO.setMiddleName(customerInfoNFTypeObject.getMiddleName());
				customerInfoNonFinDTO.setLastName(customerInfoNFTypeObject.getLastName());
				customerInfoNonFinDTO.setLangCode(customerInfoNFTypeObject.getLanguageCode());
				customerInfoNonFinDTO.setLangENG(customerInfoNFTypeObject.getLangEng());
				customerInfoNonFinDTO.setLangAR(customerInfoNFTypeObject.getLngAr());
				customerInfoNonFinDTO.setRaceCode(customerInfoNFTypeObject.getRaceCode());
				customerInfoNonFinDTO.setRaceENG(customerInfoNFTypeObject.getRaceEng());
				customerInfoNonFinDTO.setRaceAR(customerInfoNFTypeObject.getRaceAr());
				customerInfoNonFinDTO.setReligionCode(customerInfoNFTypeObject.getReligionCode());
				customerInfoNonFinDTO.setReligionENG(customerInfoNFTypeObject.getReligionEng());
				customerInfoNonFinDTO.setReligionAR(customerInfoNFTypeObject.getReligionAr());
				customerInfoNonFinDTO.setNationalityCode(customerInfoNFTypeObject.getNationCode());
				customerInfoNonFinDTO.setNationalityENG(customerInfoNFTypeObject.getNationEng());
				customerInfoNonFinDTO.setNationalityAR(customerInfoNFTypeObject.getNationAr());
				customerInfoNonFinDTO.setMaritalStatus(customerInfoNFTypeObject.getMaritalStatus());
				customerInfoNonFinDTO.setMaritalENG(customerInfoNFTypeObject.getMaritalEng());
				customerInfoNonFinDTO.setMaritalAR(customerInfoNFTypeObject.getMaritalAr());
				customerInfoNonFinDTO.setBirthPlace(customerInfoNFTypeObject.getBirthPlace());
				customerInfoNonFinDTO.setBirthCountry(customerInfoNFTypeObject.getBirthCountry());
				customerInfoNonFinDTO.setBrCountryENG(customerInfoNFTypeObject.getBrcountryEng());
				customerInfoNonFinDTO.setBrCountryAR(customerInfoNFTypeObject.getBrcountryAr());
				customerInfoNonFinDTO.setPropIdentityCode(customerInfoNFTypeObject.getPropIdenCode());
				//customerInfoNonFinDTO.setIdenExpiryDate((customerInfoNFTypeObject.getIdenExpDate().getTime()));
				
				if (BSLUtils.isNotNull(customerInfoNFTypeObject.getIdenExpDate())) {
					customerInfoNonFinDTO.setIdenExpiryDate((customerInfoNFTypeObject.getIdenExpDate().getTime()));
					logger.info("customerInfoNFTypeObject.getIdenExpDate():::"+customerInfoNFTypeObject.getIdenExpDate().getTime());
				}
				
				customerInfoNonFinDTO.setPropIdentityNo(customerInfoNFTypeObject.getPropIdenNo());
				customerInfoNonFinDTO.setPolicyNo(customerInfoNFTypeObject.getPolicyNo());

				// Modified on 03052018
				customerInfoNonFinDTO.setAddress1(customerInfoNFTypeObject.getAddOne());
				customerInfoNonFinDTO.setAddress2(customerInfoNFTypeObject.getAddTwo());
				customerInfoNonFinDTO.setAddress3(customerInfoNFTypeObject.getAddThree());
				customerInfoNonFinDTO.setEmail(customerInfoNFTypeObject.getEmailId());
				customerInfoNonFinDTO.setContactNumber(customerInfoNFTypeObject.getMobileNo());
				
				customerInfoNonFinDTO.setTown(customerInfoNFTypeObject.getTown());
				customerInfoNonFinDTO.setPostalCode(customerInfoNFTypeObject.getPostcode());
				customerInfoNonFinDTO.setPoboxn(customerInfoNFTypeObject.getPostBox());
				
				//customerInfoNonFinDTO.setPostalCode(customerInfoNFTypeObject.getPostBox());
				
				customerInfoNonFinDTO.setUserDialCode(customerInfoNFTypeObject.getDialCode());
				customerInfoNonFinDTO.setCountryENG(customerInfoNFTypeObject.getCountryEng());
				customerInfoNonFinDTO.setCorresAddress(customerInfoNFTypeObject.getCorresAddress());
				customerInfoNonFinDTO.setCountryCode(customerInfoNFTypeObject.getCountryCode());
				customerInfoNonFinDTO.setStateCode(customerInfoNFTypeObject.getStateCode());
				customerInfoNonFinDTO.setAddressCode(customerInfoNFTypeObject.getAddCode());
				customerInfoNonFinDTO.setAddresCodeDesc(customerInfoNFTypeObject.getAddDesc());
				customerInfoNonFinDTO.setAddressSeqNo(customerInfoNFTypeObject.getAddSeqNo());
				
				customerInfoNonFinDTO.setBuildingno(customerInfoNFTypeObject.getBuildingNo());
				customerInfoNonFinDTO.setUnitno(customerInfoNFTypeObject.getUnitNo());
				customerInfoNonFinDTO.setAdditionalno(customerInfoNFTypeObject.getAdditionalNo());

				if(customerInfoNFTypeObject.getCustomerContacts()!=null) {
					CustomerContNfColc customerContNfColc = customerInfoNFTypeObject.getCustomerContacts();	
					if (BSLUtils.isNotNull(customerContNfColc.getArray())) {
						CustomerContNfTypeUser[] customerContNfTypeUser = customerContNfColc.getArray();
						CustomerNFContactDTO customerNFContactDTO1;
						for(int k=0;k<customerContNfTypeUser.length;k++) {
							CustomerContNfTypeUser object = customerContNfTypeUser[k];
							customerNFContactDTO1 = new CustomerNFContactDTO();
							customerNFContactDTO1.setContactCode(object.getContactCode());
							customerNFContactDTO1.setContactDesc(object.getContactDesc());
							listing.add(customerNFContactDTO1);
						}
					}
				}
				
				customerInfoNonFinDTO.setCustomerContactList(listing);
			}

		} catch (Exception e) {
			logger.info("Inside catch block of getPersonalAndAddressDetails()" +e.getMessage());
			e.printStackTrace();
		}
		
		logger.info("Size of customerInfoNonFinDTO" + customerInfoNonFinDTO.hashCode());
		return customerInfoNonFinDTO;
		
	}

	/**
	 * Method to get Rider details for policy no
	 * 
	 * @author Vishal Kumar Bharat
	 * @param policyNo,langauage
	 * @throws Exception
	 */
	
	@Override
	public List<RiderDTO> getRiderDetails(String policyNo, String pLanguage) {
		// TODO Auto-generated method stub

		List<RiderDTO> riderdtoList = new ArrayList<RiderDTO>();
		PolicyRiderTypeUserArray riderArray = new PolicyRiderTypeUserArray();
		PolicyRiderTypeUser customerridertypeUser;
		PolicyRiderTypeUser[] customerPolriderUserArray;

		try {
			logger.info("::::Entered inside try block of getRiderDetails():::::::"+policyNo +"@@@@"+pLanguage);
			BfnGetRiderList riderElement = new BfnGetRiderList();
			riderElement.setPPolicyNo(policyNo);
			riderElement.setPLanguage(pLanguage);
			BfnGetRiderListResponse res = getServiceProxy().bfnGetRiderList(riderElement);
			riderArray = res.getResult();
			customerPolriderUserArray = riderArray.getPolicyRiderTypeUser();
			if (BSLUtils.isNotNull(customerPolriderUserArray))
				for (int i = 0; i < customerPolriderUserArray.length; i++) {
					customerridertypeUser = customerPolriderUserArray[i];
					RiderDTO riderdto = new RiderDTO();
					riderdto.setCurrent_SA(customerridertypeUser.getCurrentSa());
					riderdto.setCurrent_Term(customerridertypeUser.getCurrentTerm());
					riderdto.setOld_SA(customerridertypeUser.getCurrentSa());
					riderdto.setOld_Term(customerridertypeUser.getCurrentTerm());
					riderdto.setRider_Code(customerridertypeUser.getRiderCode());
					riderdto.setRider_Name(customerridertypeUser.getRiderName());
					// riderdto.setTransaction_Type(Constants.MODIFIEDRIDER);
					riderdto.setSerial_No(i + 1);
					Calendar tmpCommDateCal = customerridertypeUser.getStartDate();
					if (BSLUtils.isNotNull(tmpCommDateCal)) {
						Date tmpCommDateDate = tmpCommDateCal.getTime();
						riderdto.setStartDate(tmpCommDateDate);
					}
					Calendar tmpCommDateCal1 = customerridertypeUser.getEndDate();
					if (BSLUtils.isNotNull(tmpCommDateCal1)) {
						Date tmpCommDateDate = tmpCommDateCal1.getTime();
						riderdto.setEndDate(tmpCommDateDate);
					}

					riderdtoList.add(riderdto);
				}
			logger.info(":::::Result for getRiderDetails fn ::::::for list size() policyNo and language"+riderdtoList.size()+","+policyNo+","+pLanguage);
		} catch (Exception e) {
			logger.info("Inside catch block of getRiderDetails()" +e.getMessage());
			e.printStackTrace();
		}
		return riderdtoList;
	}

	// modified vishal
	@Override
	public List getRiderDropDown(String policyNo, String pLanguage) {
		// TODO Auto-generated method stub
		List<RiderDTO> riderdtoList = new ArrayList<RiderDTO>();
		PlanRiderTypeUserArray riderArray = new PlanRiderTypeUserArray();
		PlanRiderTypeUser customerridertypeUser;
		PlanRiderTypeUser[] customerPolriderUserArray;

		try {
			logger.info("::::Entered inside try block of getRiderDropDown():::::::"+policyNo +"@@@@"+pLanguage);
			BfnGetPlanRider riderElement = new BfnGetPlanRider();
			riderElement.setPPolicyNo(policyNo);
			riderElement.setPLanguage(pLanguage);
			BfnGetPlanRiderResponse res = getServiceProxy().bfnGetPlanRider(riderElement);
			riderArray = res.getResult();
			customerPolriderUserArray = riderArray.getPlanRiderTypeUser();
			if (BSLUtils.isNotNull(customerPolriderUserArray))
				for (int i = 0; i < customerPolriderUserArray.length; i++) {
					customerridertypeUser = customerPolriderUserArray[i];
					RiderDTO riderdto = new RiderDTO();
					riderdto.setRider_Code(customerridertypeUser.getRiderCode());
					riderdto.setRider_Name(customerridertypeUser.getRiderName());
					riderdtoList.add(riderdto);
			}
			logger.info(":::::Result for getRiderDropDown fn ::::::for list size() policyNo and language"+riderdtoList.size()+","+policyNo+","+pLanguage);
		} catch (Exception e) {
			logger.info("Inside catch block of getRiderDropDown()" +e.getMessage());
			e.printStackTrace();
		}
		return riderdtoList;
	}

	@Override
	public List<ProtectionBenifitDTO> getProtectionBenifit(String policyNo, String lang) {
		// TODO Auto-generated method stub
		List<ProtectionBenifitDTO> protectionBenifitList = new ArrayList<ProtectionBenifitDTO>();
		PolicyRidersTypeUserArray riderUserArray = new PolicyRidersTypeUserArray();
		PolicyRidersTypeUser typeUser;
		PolicyRidersTypeUser[] typeUserArray;

		try {
			logger.info("::::Entered inside try block of getProtectionBenifit():::::::"+policyNo +"@@@@"+lang);
			BfnGetPolicyRiders riderElement = new BfnGetPolicyRiders();
			riderElement.setPPolicyNo(policyNo);
			riderElement.setPLanguage(lang);
			BfnGetPolicyRidersResponse res = getServiceProxy().bfnGetPolicyRiders(riderElement);
			riderUserArray = res.getResult();
			typeUserArray = riderUserArray.getPolicyRidersTypeUser();
			if (BSLUtils.isNotNull(typeUserArray))
				for (int i = 0; i < typeUserArray.length; i++) {
					typeUser = typeUserArray[i];
					ProtectionBenifitDTO dto = new ProtectionBenifitDTO();
					dto.setBenifitRiderName(typeUser.getOvRiderName());
					dto.setCummulativeBenifit(typeUser.getOnCumulative());
					dto.setRiderCode(typeUser.getOvRiderCode());
					dto.setExistBenifit(typeUser.getOvDisplaySa());
					dto.setBenifitType(typeUser.getOvRiderFlag());
					dto.setPolicyNo(typeUser.getOvPolicyNo());
					dto.setMemberName(typeUser.getOvName());
					dto.setMemberIndex(typeUser.getOnMemberIndex());
       	            dto.setPremiumWaive(typeUser.getOvPremWaive());
					dto.setExcelGroupId(typeUser.getOvExclGroupId());
					dto.setExclOrOptn(typeUser.getOvExclOrOptn());
					dto.setRelationBenefit(typeUser.getOvRelDesc());
					protectionBenifitList.add(dto);
				}
			logger.info(":::::Result for getProtectionBenifit fn ::::::for list size() policyNo and language"+protectionBenifitList.size()+","+policyNo+","+lang);
		} catch (Exception e) {
			logger.info("Inside catch block of getProtectionBenifit()" +e.getMessage());
			e.printStackTrace();
		}
		return protectionBenifitList;
	}
	
	
	// vinod created for validation protection benefits
	public String benefitSAVlidation(String policyNo, String planCode, String relation, BigDecimal sa) {
		
		String result = null;
		try {
			logger.info("::::Entered inside try block of benefitSAVlidation():::::::" + policyNo + "@@@@" + planCode
					+ "@@@@" + relation + "@@@@" + sa);
			
			BfnSaValidation saValidation = new BfnSaValidation();
			saValidation.setPPolicyNo(policyNo);
			saValidation.setPPlanCode(planCode);
			saValidation.setPRelation(relation);
			saValidation.setPSa(sa);

			BfnSaValidationResponse res = getServiceProxy().bfnSaValidation(saValidation);
			result = res.getResult();
			logger.info(":::::Result for benefitSAVlidation fn ::::::" + result + "for policy No and planCode" + policyNo
					+ "," + planCode + "," + relation + "," + sa);
		} catch (Exception e) {
			logger.info("Inside catch block of getAllowedTransaction()" + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	
//	Added by Arul 12-09-2019
	public List<StrategyDTO> sellingStrategyForStrategyTab(String policyNo){
	List<StrategyDTO> sellingList = new ArrayList<StrategyDTO>();
//		BfnGetPolicySellStrategy sellStrategy = new BfnGetPolicySellStrategy();
//		sellStrategy.setPPolicyNo(policyNo);
//		try {
//			logger.info(" - - - - -  - - - - - - - Getting selling Strategy Data - - - -  - - - - - - - - - ");
//			BfnGetPolicySellStrategyResponse strategyResponse = getServiceProxy().bfnGetPolicySellStrategy(sellStrategy);
//			TypePolicySellStrategyUserArray result = strategyResponse.getResult();
//			if (BSLUtils.isNotNull(result)) {
//				for(TypePolicySellStrategyUser value : result.getTypePolicySellStrategyUser()){
//					StrategyDTO dto = new StrategyDTO();
//					dto.setStrategyName(value.getVStrategyName());
//					dto.setStrategyCode(value.getVStrategyCode());
//					dto.setCriteriaValue(value.getNStrategyPer());
//					sellingList.add(dto);
//				}
//			}
//		}catch (Exception e) {
//			logger.error("Error Message : "+ e.getMessage());
//			e.printStackTrace();
//		}
	return sellingList;
	}

	public List<StrategyDTO> buyingStrategyForStrategyTab(String policyNo) {
		// TODO Auto-generated method stub
		List<StrategyDTO> buyingList = new ArrayList<>();
//		BfnGetPolicyBuyStrategy buyStrategy = new BfnGetPolicyBuyStrategy();
//		buyStrategy.setPPolicyNo(policyNo);
//		try{
//			logger.info(" - - - - -  - - - - - - - Getting Buying Strategy Data - - - -  - - - - - - - - - ");
//			BfnGetPolicyBuyStrategyResponse response = getServiceProxy().bfnGetPolicyBuyStrategy(buyStrategy);
//			TypePolicySellStrategyUserArray result = response.getResult();
//			if(BSLUtils.isNotNull(result)){
//				for(TypePolicySellStrategyUser user : result.getTypePolicySellStrategyUser()){
//					StrategyDTO dto = new StrategyDTO();
//					dto.setStrategyName(user.getVStrategyName());
//					dto.setStrategyCode(user.getVStrategyCode());
//					dto.setPercentage(user.getNStrategyPer());
//					buyingList.add(dto);
//				}
//			}
//		}catch(Exception e){
//			logger.error("Error Message : "+ e.getMessage());
//			e.printStackTrace();
//		}
		return buyingList;
	}


	//Added by kathar
	public List<FundDetailsDTO> getPartialFundDetails(String policyNo, String lang, BigDecimal seqno) {

		CustomerUnitDetailsColc customerUnitDetails = new CustomerUnitDetailsColc();
		CustomerUnitHeaderTypeUser unitHeaderTypeUser = new CustomerUnitHeaderTypeUser();
		CustomerUnitDetailsTypeUser[] unitDetailsArray;
		List<FundDetailsDTO> fundList = new ArrayList<FundDetailsDTO>();
		// CustomerPolicyDetailTypeUser
		try {
			logger.info("::::Entered inside try block of getFundDetails():::::::"+policyNo +"@@@@"+lang);
			BfnUnitStatement customerElement = new BfnUnitStatement();
			customerElement.setPPolicyNo(policyNo);
			customerElement.setPLanguage(lang);
			//customerElement.customerElement.setPSeqNo(seqno);
			BfnUnitStatementResponse res = getServiceProxy().bfnUnitStatement(customerElement);
			unitHeaderTypeUser = res.getResult();
			customerUnitDetails = unitHeaderTypeUser.getFundDetails();
			if (BSLUtils.isNotNull(customerUnitDetails)) {
				unitDetailsArray = customerUnitDetails.getArray();
				for (int i = 0; i < unitDetailsArray.length; i++) {
					CustomerUnitDetailsTypeUser custUnitDet = unitDetailsArray[i];
					FundDetailsDTO fundDTO = new FundDetailsDTO();
					fundDTO.setFundName(custUnitDet.getFundName());
					fundDTO.setAllocationPercentage(custUnitDet.getSharePer());
					fundDTO.setAvailableUnits(custUnitDet.getAvailableUnits());
					fundDTO.setUnitsPrice(custUnitDet.getUnitPrice());
					fundDTO.setFundCrr(custUnitDet.getFundCurrency());
					fundDTO.setExchangeRate(custUnitDet.getExchangeRate());
					fundDTO.setFundValueInFundCrr(custUnitDet.getFundValue());
					fundDTO.setFundValueInPlanCrr(custUnitDet.getPlanValue());
					fundDTO.setFundCode(custUnitDet.getFundCode());
					fundDTO.setTotalFund(custUnitDet.getPlanValue().intValue());
					fundDTO.setOverfund(custUnitDet.getFundValue().intValue());
					fundList.add(fundDTO);
				}
			}else {
				
			}
			logger.info(":::::Result for getFundDetails fn ::::::for list size() policyNo and language"+fundList.size()+","+policyNo+","+lang);
		} catch (Exception e) {
			logger.info("Inside catch block of getFundDetails()" +e.getMessage());
			e.printStackTrace();
		}
		return fundList;
	}

	public List<ServiceReqNotesDTO> getservicerequestnotes(String policyNo, String lang) {

		List<ServiceReqNotesDTO> serviceReqNotesDT = new ArrayList<ServiceReqNotesDTO>();
		ServiceReqNotesTypeUserArray serviceReqNotesTypeUserArray = new ServiceReqNotesTypeUserArray();
		ServiceReqNotesTypeUser[] ServiceReqNotesArray;
		ServiceReqNotesTypeUser serviceReqNotesTypeUser;

		try {
			logger.info("::::Entered inside try block of getContributionValue():::::::"+policyNo +"@@@@"+lang);
			BfnGetPolicyPlan customerElement = new BfnGetPolicyPlan();
			customerElement.setPPolicyNo(policyNo);
			customerElement.setPLanguage(lang);
			BfnGetPolicyPlanResponse res = getServiceProxy().bfnGetPolicyPlan(customerElement);

			serviceReqNotesTypeUserArray = res.getResult();
			ServiceReqNotesArray = serviceReqNotesTypeUserArray.getServiceReqNotesTypeUser();
			for (int i = 0; i < ServiceReqNotesArray.length; i++) {
				serviceReqNotesTypeUser = ServiceReqNotesArray[i];
				ServiceReqNotesDTO serviceReqNotesDTO = new ServiceReqNotesDTO();
				serviceReqNotesDTO.setNotes(serviceReqNotesTypeUser.getNotes());
				serviceReqNotesDT.add(serviceReqNotesDTO);
			}
			logger.info(":::::Result for getContributionValue fn ::::::for list size() custRefNo and language"+serviceReqNotesDT.size()+","+policyNo+","+lang);
		} catch (Exception e) {
			logger.info("Inside catch block of getContributionValue()" +e.getMessage());
			e.printStackTrace();
		}
		return serviceReqNotesDT;
	}



	@Override
	public List<BankCodeLOVDTO> getbankcode(String language, String type) {
		List<BankCodeLOVDTO> serviceReqNotesDT = new ArrayList<BankCodeLOVDTO>();
		CustomerBankTypeUserArray serviceReqNotesTypeUserArray = new CustomerBankTypeUserArray();
		CustomerBankTypeUser[] ServiceReqNotesArray;
		CustomerBankTypeUser serviceReqNotesTypeUser;

		try {
			logger.info("::::Entered inside try block of getbankcode():::::::"+language +"@@@@"+type);
			
			BfnGetCompany customerElement = new BfnGetCompany();
			customerElement.setPLanguage(language);
			customerElement.setPType(type);
			BfnGetCompanyResponse res = getServiceProxy().bfnGetCompany(customerElement);

			serviceReqNotesTypeUserArray = res.getResult();
			ServiceReqNotesArray = serviceReqNotesTypeUserArray.getCustomerBankTypeUser();
			for (int i = 0; i < ServiceReqNotesArray.length; i++) {
				serviceReqNotesTypeUser = ServiceReqNotesArray[i];
				BankCodeLOVDTO serviceReqNotesDTO = new BankCodeLOVDTO();
				serviceReqNotesDTO.setBankcode(serviceReqNotesTypeUser.getBankCode());
				serviceReqNotesDTO.setBranchcode(serviceReqNotesTypeUser.getBranchCode());
				serviceReqNotesDTO.setBranchname(serviceReqNotesTypeUser.getBranchName());
				serviceReqNotesDT.add(serviceReqNotesDTO);
			}
			logger.info(":::::Result for getbankcode fn ::::::for list size() custRefNo and language"+serviceReqNotesDT.size()+","+language+","+type);
		} catch (Exception e) {
			logger.info("Inside catch block of getbankcode()" +e.getMessage());
			e.printStackTrace();
		}
		return serviceReqNotesDT;
	}


	
	//claim Intimation
	
	@Override
	public ClaimIntimationDTO getintimatordetails(String identype, String idenno) {
		CustomerRegisterInfoTypeUser customerRegisterInfoTypeUser = new CustomerRegisterInfoTypeUser();
		ClaimIntimationDTO claimIntimationDTO = new ClaimIntimationDTO();
		BfnVerifyCustomer bfnVerifyCustomer = new BfnVerifyCustomer();
		bfnVerifyCustomer.setPLanguage("EN");
		bfnVerifyCustomer.setPIdenCode(identype);
		bfnVerifyCustomer.setPIdenNo(idenno);
		try {
			BfnVerifyCustomerResponse bfnVerifyCustomerResponse = getServiceProxy().bfnVerifyCustomer(bfnVerifyCustomer);
			customerRegisterInfoTypeUser = bfnVerifyCustomerResponse.getResult();
			claimIntimationDTO.setItrefno(customerRegisterInfoTypeUser.getCustRefNo());
			claimIntimationDTO.setItiemail(customerRegisterInfoTypeUser.getEmailId());
			claimIntimationDTO.setIticontno(customerRegisterInfoTypeUser.getMobileNo());
			claimIntimationDTO.setDob(customerRegisterInfoTypeUser.getEnglishDob());
			claimIntimationDTO.setItigender(customerRegisterInfoTypeUser.getGender());
			claimIntimationDTO.setItitilee(customerRegisterInfoTypeUser.getTitle());
			claimIntimationDTO.setItifstname(customerRegisterInfoTypeUser.getFirstName());
			claimIntimationDTO.setItilstname(customerRegisterInfoTypeUser.getLastName());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error to get customer details" + e.getStackTrace());
			return claimIntimationDTO;
		}
		return claimIntimationDTO;
	}

	@Override
	public List<ListItem> geteventgroup(String lang, String type, String policyNo, String keyTwo) {
		List<ListItem> listifevents = new ArrayList<ListItem>();
		MasterLovTypeUserArray valuesArray = new MasterLovTypeUserArray();
		MasterLovTypeUser[] masterLovTypeUserArray;
		MasterLovTypeUser masterLovTypeUser;
		try {
			BfnGetMasterLov customerElement = new BfnGetMasterLov();
			customerElement.setPLanguage(lang);
			customerElement.setPType(type);
			customerElement.setPSearchKeyOne(policyNo);
		    customerElement.setPSearchKeyTwo(keyTwo);
			BfnGetMasterLovResponse res = getServiceProxy().bfnGetMasterLov(customerElement);
			valuesArray = res.getResult();
			masterLovTypeUserArray = valuesArray.getMasterLovTypeUser();
			if (BSLUtils.isNotNull(masterLovTypeUserArray))
				for (int i = 0; i < masterLovTypeUserArray.length; i++) {
					masterLovTypeUser = masterLovTypeUserArray[i];
					ListItem events = new ListItem();
					events.setCode(masterLovTypeUser.getCode());
					events.setDescription(masterLovTypeUser.getDescOne());
					listifevents.add(events);
				}
			logger.info(":::::Result for geteventgroup");
		} catch (Exception e) {
			e.getMessage();
		}
		return listifevents;
	}

	@Override
	public List<ListItem> getidlist(String lang, String type, String policyNo, String keyTwo) {
		List<ListItem> idlist = new ArrayList<ListItem>();
		MasterLovTypeUserArray valuesArray = new MasterLovTypeUserArray();
		MasterLovTypeUser[] masterLovTypeUserArray;
		MasterLovTypeUser masterLovTypeUser;
		try {
			BfnGetMasterLov customerElement = new BfnGetMasterLov();
			customerElement.setPLanguage(lang);
			customerElement.setPType(type);
			customerElement.setPSearchKeyOne(policyNo);
		    customerElement.setPSearchKeyTwo(keyTwo);
			BfnGetMasterLovResponse res = getServiceProxy().bfnGetMasterLov(customerElement);
			valuesArray = res.getResult();
			masterLovTypeUserArray = valuesArray.getMasterLovTypeUser();
			if (BSLUtils.isNotNull(masterLovTypeUserArray))
				for (int i = 0; i < masterLovTypeUserArray.length; i++) {
					masterLovTypeUser = masterLovTypeUserArray[i];
					ListItem events = new ListItem();
					events.setCode(masterLovTypeUser.getCode());
					events.setDescription(masterLovTypeUser.getDescOne());
					idlist.add(events);
				}
			logger.info(":::::Result for getidlist");
		} catch (Exception e) {
			e.getMessage();
		}
		return idlist;
	}
	
	@Override
	public List<ListItem> gettitle(String lang, String type, String policyNo, String keyTwo) {
		List<ListItem> titleevents = new ArrayList<ListItem>();
		MasterLovTypeUserArray valuesArray = new MasterLovTypeUserArray();
		MasterLovTypeUser[] masterLovTypeUserArray;
		MasterLovTypeUser masterLovTypeUser;
		try {
			BfnGetMasterLov customerElement = new BfnGetMasterLov();
			customerElement.setPLanguage(lang);
			customerElement.setPType(type);
			customerElement.setPSearchKeyOne(policyNo);
		    customerElement.setPSearchKeyTwo(keyTwo);
			BfnGetMasterLovResponse res = getServiceProxy().bfnGetMasterLov(customerElement);
			valuesArray = res.getResult();
			masterLovTypeUserArray = valuesArray.getMasterLovTypeUser();
			if (BSLUtils.isNotNull(masterLovTypeUserArray))
				for (int i = 0; i < masterLovTypeUserArray.length; i++) {
					masterLovTypeUser = masterLovTypeUserArray[i];
					ListItem events = new ListItem();
					events.setCode(masterLovTypeUser.getCode());
					events.setDescription(masterLovTypeUser.getDescOne());
					titleevents.add(events);
				}
			logger.info(":::::Result for gettitle");
		} catch (Exception e) {
			e.getMessage();
		}
		return titleevents;
	}

	@Override
	public List<ListItem> getcasueloss(String lang, String type, String policyNo, String keyTwo) {
		List<ListItem> casusevents = new ArrayList<ListItem>();
		MasterLovTypeUserArray valuesArray = new MasterLovTypeUserArray();
		MasterLovTypeUser[] masterLovTypeUserArray;
		MasterLovTypeUser masterLovTypeUser;
		try {
			BfnGetMasterLov customerElement = new BfnGetMasterLov();
			customerElement.setPLanguage(lang);
			customerElement.setPType(type);
			customerElement.setPSearchKeyOne(policyNo);
		    customerElement.setPSearchKeyTwo(keyTwo);
			BfnGetMasterLovResponse res = getServiceProxy().bfnGetMasterLov(customerElement);
			valuesArray = res.getResult();
			masterLovTypeUserArray = valuesArray.getMasterLovTypeUser();
			if (BSLUtils.isNotNull(masterLovTypeUserArray))
				for (int i = 0; i < masterLovTypeUserArray.length; i++) {
					masterLovTypeUser = masterLovTypeUserArray[i];
					ListItem events = new ListItem();
					events.setCode(masterLovTypeUser.getCode());
					events.setDescription(masterLovTypeUser.getDescOne());
					casusevents.add(events);
				}
			logger.info(":::::Result for gettitle");
		} catch (Exception e) {
			e.getMessage();
		}
		return casusevents;
	}
	
	@Override
	public String getcheckalu(String language, String policyno) {	
		String result = null;
		try {
			logger.info("::::Entered inside try block of getcheckalu():::::::"+policyno +"@@@@"+language);
			BfnChkIuAu bfnChkIuAu = new BfnChkIuAu();
			bfnChkIuAu.setPPolicyNo(policyno);
			bfnChkIuAu.setPLanguage(language);
			BfnChkIuAuResponse res = getServiceProxy().bfnChkIuAu(bfnChkIuAu);
			result = res.getResult();
			if(result.equalsIgnoreCase("")) {
				result = null;
			}
            logger.info(":::::Result for getAllowedTransaction fn ::::::"+result +"for policy No and type"+policyno+","+language);
		} catch (Exception e) {
			logger.info("Inside catch block of bfnChkIuAu()" +e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public String getcheckpartialwithdraw(String language, String policyno, BigDecimal seqno, String type) {
		String result = null;
		try {
			logger.info("::::Entered inside try block of getcheckpartialwithdraw():::::::"+policyno +"@@@@"+language +"@@@@"+seqno +"@@@@"+type);
			CpExceptionErrorTypeUserArray obj = new CpExceptionErrorTypeUserArray();
			BfnChkPwAllow bfnChkPwAllow = new BfnChkPwAllow();
			bfnChkPwAllow.setPLanguage(language);
			bfnChkPwAllow.setPPolicyNo(policyno);
			bfnChkPwAllow.setPSeqNo(seqno);
			bfnChkPwAllow.setPType(type);
			BfnChkPwAllowResponse res = getServiceProxy().bfnChkPwAllow(bfnChkPwAllow);
			obj = res.getResult();
			CpExceptionErrorTypeUser[] output = obj.getCpExceptionErrorTypeUser();
			if (BSLUtils.isNotNull(output)) {
				for (int i = 0; i < output.length; i++) {
					CpExceptionErrorTypeUser cpExceptionErrorTypeUser = output[i];
					if (cpExceptionErrorTypeUser.getErrSqlCode()!=null) {
						result = cpExceptionErrorTypeUser.getErrSqlErrDesc();	
					}
			    }
			}
            logger.info(":::::Result for getcheckpartialwithdraw fn ::::::"+result +"for policy No and type"+policyno+","+language);
		} catch (Exception e) {
			logger.info("Inside catch block of getcheckpartialwithdraw()" +e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	
}