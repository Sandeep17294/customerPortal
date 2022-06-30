package com.aetins.customerportal.web.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aetins.customerportal.core.faceletstaglib.SecurityLibrary;
import com.aetins.customerportal.core.services.IMailService;
import com.aetins.customerportal.web.dao.CpRequestMasterDAO;
import com.aetins.customerportal.web.dao.CpUserInfoDAO;
import com.aetins.customerportal.web.dao.ITransactionDeptDao;
import com.aetins.customerportal.web.dto.ServiceRequestMasterDTO;
import com.aetins.customerportal.web.entity.CPTransactionDept;
import com.aetins.customerportal.web.entity.CpRequestMaster;
import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.service.CPartialWithdrawalFundsBL;
import com.aetins.customerportal.web.service.ContributionAlterBL;
import com.aetins.customerportal.web.service.ContributionHolidaylBL;
import com.aetins.customerportal.web.service.CpClaimIntimationBL;
import com.aetins.customerportal.web.service.EServiceTransactionService;
import com.aetins.customerportal.web.service.ProtectionBenifitBL;
import com.aetins.customerportal.web.service.RedirectionBL;
import com.aetins.customerportal.web.service.RegistrationCustomerService;
import com.aetins.customerportal.web.service.ReinStatementBL;
import com.aetins.customerportal.web.service.UpdateInfoBL;
import com.aetins.customerportal.web.service.impl.ContributionAlterBLImpl;
import com.aetins.customerportal.web.service.impl.ContributionHolidayBLImpl;
import com.aetins.customerportal.web.service.impl.RedirectionBLImpl;
import com.aetins.customerportal.web.service.impl.ReinStatementBLImpl;
import com.aetins.customerportal.web.service.impl.UpdateInfoBLImpl;
import com.aetins.customerportal.web.transaction.dto.TyChangeInContributionDTO;
import com.aetins.customerportal.web.transaction.dto.TyClaimIntimationDTO;
import com.aetins.customerportal.web.transaction.dto.TyContributionHolidayDTO;
import com.aetins.customerportal.web.transaction.dto.TyNfAddressContactsDTO;
import com.aetins.customerportal.web.transaction.dto.TyNfAddressDTO;
import com.aetins.customerportal.web.transaction.dto.TyNfPersonalDetailsDTO;
import com.aetins.customerportal.web.transaction.dto.TyPartialWithdrawDTO;
import com.aetins.customerportal.web.transaction.dto.TyProtectionBenifitDTO;
import com.aetins.customerportal.web.transaction.dto.TyRedirectionSwitchingDTO;
import com.aetins.customerportal.web.transaction.dto.TyReinstatementDTO;
import com.aetins.customerportal.web.utils.AppSettingURL;


@Controller
@Scope("session")
public class TransactionServiceAction extends BaseAction {

	private static final Logger logger = Logger.getLogger(TransactionServiceAction.class);

	@Autowired
	ContributionHolidaylBL contributionHolidayBL;
	
	@Autowired
	EServiceTransactionService eServiceTransactionServiceImpl;
	
	@Autowired
	ContributionAlterBL contributionAlterBLImpl;
	
	@Autowired
	CpRequestMasterDAO cpRequestMasterDAO;
	
	@Autowired
	ReinStatementBL reinStatementBLImpl;
	
	@Autowired
	ProtectionBenifitBL protectionBenifitBL;
	
	@Autowired
	UpdateInfoBL updateInfoBL;
	
	@Autowired
	RedirectionBL redirectImpl;
	
	@Autowired
    CPartialWithdrawalFundsBL cPartialWithdrawalFundsBL;
	
	@Autowired
	IMailService mailService;
	
	@Autowired
	CpClaimIntimationBL cpClaimIntimationBL;
	
	@Autowired
	CpUserInfoDAO cpUserInfoDAO;
	
	@Autowired
    ITransactionDeptDao transactionDeptDao;
	
	@Autowired
	RegistrationCustomerService  registrationService;
	
	private int requestNo;
	
	ServiceRequestMasterDTO serviceRequestMasterDTO;

	@Override
	public void init() {
		// TODO Auto-generated method stub
	}

	// :::::::::: Generic Method to save new request to Master table
	// ::::::::::::::::

	public ServiceRequestMasterDTO insertServiceRequest(ServiceRequestMasterDTO serviceRequestMasterDTO) {
		CpRequestMaster cpRequestMaster = new CpRequestMaster();
		try {
			
			int serviceNo = registrationService.getServiceRequestNo().intValue();
			  logger.info("***********************************************");
			  logger.info("Service Request no from oracle: "+serviceNo+"");
			  logger.info("***********************************************");
			  cpRequestMaster.setServiceRequestNo(serviceNo);
			
			cpRequestMaster.setServiceRequestDate(serviceRequestMasterDTO.getServiceRequestDate());
			cpRequestMaster.setServiceRequestType(serviceRequestMasterDTO.getServiceRequestType());
			cpRequestMaster.setPolicyNo(serviceRequestMasterDTO.getPolicyNo());
			cpRequestMaster.setUserId(serviceRequestMasterDTO.getUserId());
			cpRequestMaster.setRequestStatus(serviceRequestMasterDTO.getRequestStatus());
			cpRequestMaster.setProcessedDate(serviceRequestMasterDTO.getProcessedDate());
			cpRequestMaster.setProcessedBy(serviceRequestMasterDTO.getProcessedBy());
			cpRequestMaster.setRecentUpdate(serviceRequestMasterDTO.getRecentUpdateDate());
			cpRequestMaster.setApplicable(serviceRequestMasterDTO.getApplicable());
			cpRequestMaster.setSeqno(serviceRequestMasterDTO.getSeqno());	
			cpRequestMaster = cpRequestMasterDAO.saveNewUser(cpRequestMaster);

			serviceRequestMasterDTO.setServiceRequestNo(cpRequestMaster.getServiceRequestNo());
			getSession().setAttribute("MASTERDTO", serviceRequestMasterDTO);
		} catch (Exception e) {
			
			e.printStackTrace();
			logger.error("Exception caught while service request: "+e.getMessage()+"");
		}
		return serviceRequestMasterDTO;

	}

	// :::::::::: Generic Method to save new request to Master table for
	// Redirection and switching ::::::::::::::::

	public List<ServiceRequestMasterDTO> insertServiceRequest(
			List<ServiceRequestMasterDTO> serviceRequestMasterDTOList) {
		
		try {
			if (serviceRequestMasterDTOList != null) {
				if (serviceRequestMasterDTOList.size() > 0) {
					for (ServiceRequestMasterDTO serviceRequestMasterDTO : serviceRequestMasterDTOList) {

						CpRequestMaster cpRequestMaster = new CpRequestMaster();
						/*
						 * int serviceNo = registrationService.getServiceRequestNo().intValue();
						 * logger.info("***********************************************");
						 * logger.info("Service Request no from oracle: "+serviceNo+"");
						 * logger.info("***********************************************");
						 * cpRequestMaster.setServiceRequestNo(serviceNo);
						 */
						cpRequestMaster.setServiceRequestDate(serviceRequestMasterDTO.getServiceRequestDate());
						cpRequestMaster.setServiceRequestType(serviceRequestMasterDTO.getServiceRequestType());
						cpRequestMaster.setPolicyNo(serviceRequestMasterDTO.getPolicyNo());
						cpRequestMaster.setUserId(serviceRequestMasterDTO.getUserId());
						cpRequestMaster.setRequestStatus(serviceRequestMasterDTO.getRequestStatus());
						cpRequestMaster.setProcessedDate(serviceRequestMasterDTO.getProcessedDate());
						cpRequestMaster.setProcessedBy(serviceRequestMasterDTO.getProcessedBy());
						cpRequestMaster.setRecentUpdate(serviceRequestMasterDTO.getRecentUpdateDate());
						cpRequestMaster.setApplicable(serviceRequestMasterDTO.getApplicable());
						cpRequestMaster.setSeqno(serviceRequestMasterDTO.getSeqno());
						cpRequestMaster = cpRequestMasterDAO.saveNewUser(cpRequestMaster);

						serviceRequestMasterDTO.setServiceRequestNo(cpRequestMaster.getServiceRequestNo());
						getSession().setAttribute("MASTERDTO", serviceRequestMasterDTO);
						getSession().setAttribute("MASTERDTO_LIST", serviceRequestMasterDTOList);
					}

				}
			}
			getSession().setAttribute("REQUESTNO", serviceRequestMasterDTOList.get(0).getServiceRequestNo());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception caught while service request: "+e.getMessage()+"");
		}
		return serviceRequestMasterDTOList;

	}
	// ::::::: This Method used for Redirection and Switching transaction
	// ::::::::

	public String transServiceRedirectionAndSwitching(ServiceRequestMasterDTO dto) {

		logger.info("Entering inside transServiceRedirectionAndSwitching Method ::::::::::::");
		TyRedirectionSwitchingDTO tyRedirectionSwitchingDTO = new TyRedirectionSwitchingDTO();
		tyRedirectionSwitchingDTO = redirectImpl.fetchRequestAndRedirections(dto);
		logger.info("Size of tyRedirectionSwitchingDTO is ::::::" + tyRedirectionSwitchingDTO);
		String status = "Fail";
		try {
			status = eServiceTransactionServiceImpl.saveRedirectionAndSwitching(tyRedirectionSwitchingDTO);
		} catch (Exception e) {
			status = "Fail";
			logger.info("Error at transServiceRedirectionAndSwitching::::::: " + e);
		}
		logger.info("Transaction status for Redirection and switching is ::::" + status);
		return status;
	}

	//>>>This Method used for partialithdraw transaction
	
	public String transServicepartialwithdraw(ServiceRequestMasterDTO dto) {

		logger.info("Entering inside transServiceRedirectionAndSwitching Method ::::::::::::");
		TyPartialWithdrawDTO tyPartialWithdrawDTO = new TyPartialWithdrawDTO();
		tyPartialWithdrawDTO = cPartialWithdrawalFundsBL.fetchRequestPartialwithdraw(dto);
		logger.info("Size of tyPartialWithdrawDTO is ::::::" + tyPartialWithdrawDTO);
		String status = "Fail";
		try {
			status = eServiceTransactionServiceImpl.savepartialwithdraw(tyPartialWithdrawDTO);
		} catch (Exception e) {
			status = "Fail";
			logger.info("Error at transServiceRedirectionAndSwitching::::::: " + e);
		}
		logger.info("Transaction status for Redirection and switching is ::::" + status);
		return status;
	}
	
	
	//>>>This Method is used for claim intimation
	
	public String transerviceclaimintimation(int serviceno) {
			TyClaimIntimationDTO tyClaimIntimationDTO = new TyClaimIntimationDTO();
			tyClaimIntimationDTO = cpClaimIntimationBL.fetchRequestclaim(serviceno);
			logger.info("Size of tyClaimIntimationDTO is ::::::" + tyClaimIntimationDTO);
			String status = "Fail";
			try {
				status = eServiceTransactionServiceImpl.saveclaimintimation(tyClaimIntimationDTO);
			} catch (Exception e) {
				status = "Fail";
				logger.info("Error at tyClaimIntimationDTO::::::: " + e);
			}
			logger.info("Transaction status for tyClaimIntimationDTO is ::::" + status);
			return status;
	}

	
	
	// ::::::: This Method used for Contribution holiday transaction ::::::::

	public String transContributionHoliday(ServiceRequestMasterDTO dto) {

		logger.info("Entering inside transContributionHoliday Method ::::::::::::");
		TyContributionHolidayDTO contributionHolidayDTO = new TyContributionHolidayDTO();
		contributionHolidayDTO = contributionHolidayBL.fetchRequestAndHoliday(dto);
		logger.info("Size of contributionHolidayDTO is ::::::" + contributionHolidayDTO);
		String status = "Fail";
		try {
			status = eServiceTransactionServiceImpl.saveContributionHoliday(contributionHolidayDTO);
		} catch (Exception e) {
			status = "Fail";
			logger.info("Error at transContributionHoliday::::::: " + e);
		}
		logger.info("Transaction status for Contribution Holiday is ::::" + status);
		return status;
	}

	// :::::: This Method used for Contribution Alteration transaction :::::::

	public String transContributionAlteration(ServiceRequestMasterDTO dto) {

		logger.info("Entering inside transContributionAlteration Method ::::::::::::");
		TyChangeInContributionDTO changeInContributionDTO = new TyChangeInContributionDTO();
		changeInContributionDTO = contributionAlterBLImpl.fetchRequestAndAlterations(dto);
		logger.info("Size of changeInContributionDTO is ::::::" + changeInContributionDTO);
		String status = "Fail";
		try {
			status = eServiceTransactionServiceImpl.saveContributionAlteration(changeInContributionDTO);
		} catch (Exception e) {
			status = "Fail";
			logger.info("Error at transContributionAlteration::::::::::: " + e);
			e.printStackTrace();
		}
		logger.info("Status for Cont ALT is ::::::::::::" + status);
		return status;
	}

	// :::::: This Method used for Reinstatement Alteration transaction :::::::

	public String transReinStatement(ServiceRequestMasterDTO dto) {

		logger.info("Entering inside transReinStatement Method ::::::::::::");
		TyReinstatementDTO ryReinstatementDTO = new TyReinstatementDTO();
		ryReinstatementDTO = reinStatementBLImpl.fetchRequestAndReinstatements(dto);
		logger.info("Size of ryReinstatementDTO is ::::::" + ryReinstatementDTO);
		String status = "Fail";
		try {
			status = eServiceTransactionServiceImpl.saveReinstatementAlteration(ryReinstatementDTO);
		} catch (Exception e) {
			status = "Fail";
			logger.info("Error at transReinStatement::::::::::: " + e);
			e.printStackTrace();
		}
		logger.info("Status for Reinstatement is ::::::::::::" + status);
		return status;
	}

	// :::::: This Method used for Protection Benifit Alteration transaction
	// :::::::

	public String transProtectionBenifit(ServiceRequestMasterDTO dto) {

		logger.info("Entering into transProtectionBenifit ::::::::::::");
		TyProtectionBenifitDTO tyProtectionBenifitDTO = new TyProtectionBenifitDTO();
		tyProtectionBenifitDTO = protectionBenifitBL.fetchRequestAndProtBenifit(dto);
		logger.info("Size of tyProtectionBenifitDTO is ::::::" + tyProtectionBenifitDTO);
		String status = "Fail";
		try {
			status = eServiceTransactionServiceImpl.saveProtectionBenifit(tyProtectionBenifitDTO);
		} catch (Exception e) {
			status = "Fail";
			logger.info("Error at transProtectionBenifit Method ::::::::::: " + e);
			e.printStackTrace();
		}
		logger.info("Status for Protection Benefit is ::::::::::::" + status);
		return status;
	}

	// :::::: This Method used for Update information Alteration transaction
	// :::::::

	public String transUpdateInfomation(ServiceRequestMasterDTO dto) {

		logger.info("Inside transUpdateInfomation for Transaction function :::::::::::");
		TyNfPersonalDetailsDTO tyNfPersonalDetailsDTO = updateInfoBL.fetchRequestAndPersonal(dto);
		TyNfAddressDTO tyNfAddressDTO = updateInfoBL.fetchRequestAndAddress(dto);
		TyNfAddressContactsDTO tyNfAddressContactsDTO = updateInfoBL.fetchRequestAndAddressContact(dto);

		String status3 = "fail";
		String status1 = "fail";
		String status2 = "fail";
		String status = "fail";

		try {

			logger.info("Inside 1st try  block of transUpdateInfomation :::::::::");
			if (tyNfPersonalDetailsDTO.getCpNFPersonalDetails().size() > 0) {
				status3 = eServiceTransactionServiceImpl.saveUpdateInformationPersonalDeatils(tyNfPersonalDetailsDTO);
				logger.info("Inside transUpdateInfomation for Transaction function :::::::::::");
			}
		} catch (Exception e) {
			status = "Fail";
			e.printStackTrace();
			logger.info("Inside CATCH111 block of transUpdateInfomation $$$$$$$$$$$$" + e);
		}

		
		try {
			logger.info("Inside 3rd try  block of transUpdateInfomation :::::::::");
			if (tyNfAddressContactsDTO.getCpNFAddressContacts().size() > 0) {
				status1 = eServiceTransactionServiceImpl.saveUpdateInformationAddressContact(tyNfAddressContactsDTO);
				logger.info("Inside Contact IF Condition :::::::::::" + tyNfAddressContactsDTO);
			}
		} catch (Exception e) {
			status = "Fail";
			e.printStackTrace();
			logger.info("Inside CATCH333 block of transUpdateInfomation $$$$$$$$$$$$" + e);
		}
		
		
		try {

			logger.info("Inside 2nd try  block of transUpdateInfomation :::::::::");
			if (tyNfAddressDTO.getCpNFAddress().size() > 0) {
				status2 = eServiceTransactionServiceImpl.saveAddressDetails(tyNfAddressDTO);
				logger.info("Inside Address IF condition ##############" + tyNfAddressDTO);
			}
		} catch (Exception e) {
			status = "Fail";
			e.printStackTrace();
			logger.info("Inside CATCH222 block of transUpdateInfomation $$$$$$$$$$$$" + e);
		}
		
//		if(BSLUtils.isNotNull(status1) || BSLUtils.isNotNull(status2) || BSLUtils.isNotNull(status3)) {
//			if(status1.equalsIgnoreCase("PASS") || status2.equalsIgnoreCase("PASS") || status3.equalsIgnoreCase("PASS")) {
//				status="Pass";
//			}
//		}
		
		if(status1.equalsIgnoreCase("PASS") || status2.equalsIgnoreCase("PASS") || status3.equalsIgnoreCase("PASS")) {
			status="PASS";
			
			/*//Mail Body
			String emailBody = AppSettingURL.EMAIL_NOTIFY_BODY_TRANSACTION_TRIGGER;
			Map<String, String> succMap = new HashMap<String, String>();
			succMap.put("srNo", dto.getServiceRequestNo()+"");
			succMap.put("custname", SecurityLibrary.getLoggedInUser());
			succMap.put("transactionName", dto.getServiceRequestType());
			succMap.put("policyNo", dto.getPolicyNo());
			String email = StrSubstitutor.replace(emailBody, succMap);
			
			//Fetch user by group 
			List<CpUserInfo> cpUsersByGroup = cpUserInfoDAO.getCpUsersByGroup("B");
			
			//Fetch dept by transactions
			CPTransactionDept findByTransaction = transactionDeptDao.findByTransaction(dto.getServiceRequestType());
			
			for(CpUserInfo userInfo: cpUsersByGroup){
				
				if(userInfo.getvBusrDept().equals(findByTransaction.getDeptCode())){
					
					mailService.sendMail(userInfo.getVemail(), dto.getServiceRequestType(), email);
					logger.info("Mail sent to business user: "+userInfo.getVemail()+" for transaction: "+dto.getServiceRequestType()+"");
				}
			}*/
			
			//mailService.sendMail(SecurityLibrary.getFullLoggedInUser()., subject, body);
		}

//		if (BSLUtils.isNotNull(status1) || BSLUtils.isNotNull(status2) || BSLUtils.isNotNull(status3)) {
//			status = "Pass";
//		}

		System.out.println(status);
		return status;
	}


	public void setContributionHolidayBL(ContributionHolidayBLImpl contributionHolidayBL) {
		this.contributionHolidayBL = contributionHolidayBL;
	}

	

	public CpRequestMasterDAO getCpRequestMasterDAO() {
		return cpRequestMasterDAO;
	}

	public void setCpRequestMasterDAO(CpRequestMasterDAO cpRequestMasterDAO) {
		this.cpRequestMasterDAO = cpRequestMasterDAO;
	}


	public void setContributionAlterBLImpl(ContributionAlterBLImpl contributionAlterBLImpl) {
		this.contributionAlterBLImpl = contributionAlterBLImpl;
	}

	public ServiceRequestMasterDTO getServiceRequestMasterDTO() {
		return serviceRequestMasterDTO;
	}

	public void setServiceRequestMasterDTO(ServiceRequestMasterDTO serviceRequestMasterDTO) {
		this.serviceRequestMasterDTO = serviceRequestMasterDTO;
	}


	public void setReinStatementBLImpl(ReinStatementBLImpl reinStatementBLImpl) {
		this.reinStatementBLImpl = reinStatementBLImpl;
	}

	public ProtectionBenifitBL getProtectionBenifitBL() {
		return protectionBenifitBL;
	}

	public void setProtectionBenifitBL(ProtectionBenifitBL protectionBenifitBL) {
		this.protectionBenifitBL = protectionBenifitBL;
	}

	public void setUpdateInfoBL(UpdateInfoBLImpl updateInfoBL) {
		this.updateInfoBL = updateInfoBL;
	}


	public void setRedirectImpl(RedirectionBLImpl redirectImpl) {
		this.redirectImpl = redirectImpl;
	}

	public int getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(int requestNo) {
		this.requestNo = requestNo;
	}

}