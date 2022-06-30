package com.aetins.customerportal.web.service.impl;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.dao.ContributionAlterDAO;
import com.aetins.customerportal.web.dao.CpRequestMasterDAO;
import com.aetins.customerportal.web.dao.CustomerLoginDAO;
import com.aetins.customerportal.web.dao.FatcaCountryDAO;
import com.aetins.customerportal.web.dao.FatcaDAO;
import com.aetins.customerportal.web.dao.OtpSetUpDAO;
import com.aetins.customerportal.web.dao.PasswordRulesDAO;
import com.aetins.customerportal.web.dao.impl.ContributionAlterDAOImpl;
import com.aetins.customerportal.web.dao.impl.CpRequestMasterDAOImpl;
import com.aetins.customerportal.web.dao.impl.CustomerLoginDAOImpl;
import com.aetins.customerportal.web.dao.impl.FatcaCountryDAOImpl;
import com.aetins.customerportal.web.dao.impl.FatcaDAOImpl;
import com.aetins.customerportal.web.dao.impl.OtpSetUpDAOImpl;
import com.aetins.customerportal.web.dao.impl.PasswordRulesDAOImpl;
import com.aetins.customerportal.web.dto.ContributionAlterationDTO;
import com.aetins.customerportal.web.dto.CpOtpSettingsDTO;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.dto.PasswordRulesDTO;
import com.aetins.customerportal.web.dto.ServiceRequestMasterDTO;
import com.aetins.customerportal.web.dto.UserDTO;
import com.aetins.customerportal.web.entity.CpContributionAlteration;
import com.aetins.customerportal.web.entity.CpFatca;
import com.aetins.customerportal.web.entity.CpFatcaCountryDet;
import com.aetins.customerportal.web.entity.CpOtpSettings;
import com.aetins.customerportal.web.entity.CpRequestMaster;
import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.entity.PasswordRules;
import com.aetins.customerportal.web.service.ContributionAlterBL;
import com.aetins.customerportal.web.transaction.dto.TyChangeInContributionDTO;
import com.aetins.customerportal.web.utils.BSLUtils;



@Service
public class ContributionAlterBLImpl implements ContributionAlterBL {
		
	private static final Logger logger = Logger.getLogger(ContributionAlterBLImpl.class);
	
	@Autowired
	ContributionAlterDAO contributionAlterDAO;
	
	@Autowired
	CpRequestMasterDAO requestMasterDAO;
	
	@Autowired
	CustomerLoginDAO customerLoginDao;
	
	@Autowired
	OtpSetUpDAO OtpSetUpDAO;
	
	@Autowired
	PasswordRulesDAO passwordRulesDao;
	
	@Autowired
	FatcaDAO fatcaDAO;
	
	@Autowired
    FatcaCountryDAO fatcaCountryDAO ;

	
	@Override
	public boolean changeInContribution(ContributionAlterationDTO dtos) {
		// TODO Auto-generated method stub			
		CpContributionAlteration alteration=new CpContributionAlteration();			
				CpRequestMaster requestMaster = new CpRequestMaster();
				requestMaster.setServiceRequestNo(dtos.getServiceRequestNo().getServiceRequestNo());
			alteration.setServiceRequestNo(requestMaster);
			alteration.setPolicyNo(dtos.getPolicyNo());
			alteration.setProductName(dtos.getPlanName());
			alteration.setOutstandingAmount(dtos.getOutStandingAmount());
			alteration.setCurrentContribution(dtos.getCurrentContribution());
			alteration.setCurrentFrequency(dtos.getCurrentFrequency());
			alteration.setNewContribution(dtos.getNewContribution());
			alteration.setDueDate(dtos.getDueDate());
			alteration.setNewFrequency(dtos.getNewFrequency());
			alteration.setAssetsCash(dtos.getAssetsCash());
			alteration.setAssetsOthers(dtos.getAssetsOthers());
			alteration.setAssetsRealEstate(dtos.getAssetsRealEstate());
			alteration.setAssetsShares(dtos.getAssetsShares());
			alteration.setCoveredBankName(dtos.getCoveredBankName());
			alteration.setCoveredIBAN(dtos.getCoveredIBAN());
			alteration.setCoveredSourceFund(dtos.getCoveredSourceFund());
			alteration.setPlanHolderBankName(dtos.getPlanHolderBankName());
			alteration.setPlanHolderIBAN(dtos.getPlanHolderIBAN());
			alteration.setPlanHolderSourceFund(dtos.getPlanHolderSourceFund());
			alteration.setIncomeYearOne(dtos.getIncomeYearOne());
			alteration.setIncomeYearTwo(dtos.getIncomeYearTwo());
			alteration.setIncomeYearThree(dtos.getIncomeYearThree());
			alteration.setLiabilitiesLoan(dtos.getLiabilitiesLoan());
			alteration.setLiabilitiesMortgage(dtos.getLiabilitiesMortgage());
			alteration.setLiabilitiesOther(dtos.getLiabilitiesOther());
			alteration.setLiabilitiesPayable(dtos.getLiabilitiesPayable());
		return contributionAlterDAO.saveChangeDetails(alteration);
	}
	
	@Override
	public ServiceRequestMasterDTO saveNewRequest(
			ServiceRequestMasterDTO requestDTO) {
		CpRequestMaster requestMaster = new CpRequestMaster();
        requestMaster.setPolicyNo(requestDTO.getPolicyNo());
        requestMaster.setUserId(requestDTO.getUserId());
        requestMaster.setProcessedBy(requestDTO.getProcessedBy());
        requestMaster.setProcessedDate(requestDTO.getProcessedDate());
        requestMaster.setRecentUpdate(requestDTO.getRecentUpdateDate());
        requestMaster.setRequestStatus(requestDTO.getRequestStatus());
        requestMaster.setServiceRequestDate(requestDTO.getServiceRequestDate());
        requestMaster.setServiceRequestType(requestDTO.getServiceRequestType());        
       
       // logger.info("UpdateInfoBLImpl:save new request and get new request ");
        requestMaster = requestMasterDAO.saveNewUser(requestMaster);
                
        requestDTO.setServiceRequestNo(requestMaster.getServiceRequestNo());
        if(BSLUtils.isNotNull(requestMaster.getPolicyNo()))
        	requestDTO.setPolicyNo(requestMaster.getPolicyNo());
        if(BSLUtils.isNotNull(requestMaster.getProcessedBy()))
        	requestDTO.setProcessedBy(requestMaster.getProcessedBy());
        if(BSLUtils.isNotNull(requestMaster.getProcessedDate()))
        	requestDTO.setProcessedDate(requestMaster.getProcessedDate());
        if(BSLUtils.isNotNull(requestMaster.getRecentUpdate()))
        	requestDTO.setRecentUpdateDate(requestMaster.getRecentUpdate());
        if(BSLUtils.isNotNull(requestMaster.getRequestStatus()))
        	requestDTO.setRequestStatus(requestMaster.getRequestStatus());
        if(BSLUtils.isNotNull(requestMaster.getServiceRequestDate()))
        	requestDTO.setServiceRequestDate(requestMaster.getServiceRequestDate());
        if(BSLUtils.isNotNull(requestMaster.getServiceRequestType()))
        	requestDTO.setServiceRequestType(requestMaster.getServiceRequestType());
        if(BSLUtils.isNotNull(requestMaster.getUserId()))
        	requestDTO.setUserId(requestMaster.getUserId());
                
        return requestDTO;
	}

	@Override
	public
	List<CpUserInfoDTO> getUserDetails(UserDTO verifyUserdto)  {
		CpUserInfo cpUserInfos = new CpUserInfo();
		List<CpUserInfo> userLists = new ArrayList<CpUserInfo>();
		List<CpUserInfoDTO> userAllLists = new ArrayList<CpUserInfoDTO>();
		CpUserInfoDTO cpUserInfoDTO = new CpUserInfoDTO();

		cpUserInfos.setNcustRefNo(verifyUserdto.getCustRefNo());
		userLists = customerLoginDao.getUserDetails(cpUserInfos);

		Iterator<CpUserInfo> it = userLists.iterator();
		while (it.hasNext()) {
			CpUserInfo cpUserInfo = it.next();
			cpUserInfoDTO.setVcontactNo(cpUserInfo.getVcontactNo());
			cpUserInfoDTO.setVemail(cpUserInfo.getVemail());
			cpUserInfoDTO.setVquestion1(cpUserInfo.getVquestion1());
			cpUserInfoDTO.setVanswer1(cpUserInfo.getVanswer1());
			userAllLists.add(cpUserInfoDTO);
		}
		return userAllLists;
	}

	
	@Override
	public boolean updateDetails(ServiceRequestMasterDTO serviceRequestMasterDTO) {
		CpRequestMaster cpRequestMaster = new CpRequestMaster();
		cpRequestMaster.setServiceRequestNo(serviceRequestMasterDTO.getServiceRequestNo());
		cpRequestMaster.setRecentUpdate(serviceRequestMasterDTO.getRecentUpdateDate());
		cpRequestMaster.setRequestStatus(serviceRequestMasterDTO.getRequestStatus());
		return requestMasterDAO.updateColumns(cpRequestMaster);
	}

	@Override
	public List<CpOtpSettingsDTO> fetchOtpSettings(String serviceType) {
		List<CpOtpSettings> otpRules= OtpSetUpDAO.listOtpSettings(serviceType);
		List<CpOtpSettingsDTO> settingsList = new ArrayList<CpOtpSettingsDTO>();
		for(CpOtpSettings cpOtpSettings :otpRules){
			CpOtpSettingsDTO cpOtpSettingsDTOs= new CpOtpSettingsDTO();
			cpOtpSettingsDTOs.setvOtpFlagEmail(cpOtpSettings.getvOtpFlagEmail());
			cpOtpSettingsDTOs.setvOtpFlagMobile(cpOtpSettings.getvOtpFlagMobile());
			settingsList.add(cpOtpSettingsDTOs);
		}
		
		return settingsList;
	}

	@Override
	public PasswordRulesDTO getCpSettingDto() {
		PasswordRulesDTO passwordRulesDTO = new PasswordRulesDTO();

		List<PasswordRules> passwordRules = passwordRulesDao.listPasswordRules();
		passwordRulesDTO.setnRequiredMobOTP(passwordRules.get(0).getnRequiredMobOTP());
		return passwordRulesDTO;
	}

	@Override
	public TyChangeInContributionDTO fetchRequestAndAlterations(ServiceRequestMasterDTO serviceRequestMasterDTO) {
		
		CpRequestMaster requestMaster = new CpRequestMaster();		
		requestMaster.setServiceRequestNo(serviceRequestMasterDTO.getServiceRequestNo());
		requestMaster = requestMasterDAO.fetchRequestMasters(requestMaster);
		List<CpRequestMaster> requestMasters = new ArrayList<CpRequestMaster>();
		requestMasters.add(requestMaster);
		
		List<CpContributionAlteration> cpContributionAlterations = new ArrayList<CpContributionAlteration>();
		
		cpContributionAlterations = contributionAlterDAO.fetchAllContrAlter(serviceRequestMasterDTO.getServiceRequestNo());
		
		List<CpFatca> cpFatca = new ArrayList<CpFatca>();
		cpFatca = fatcaDAO.fetchFatcaDetails(serviceRequestMasterDTO.getServiceRequestNo());
		
		List<CpFatcaCountryDet> cpFatcaCountryDet = new ArrayList<CpFatcaCountryDet>();
		if (cpFatca.size() > 0) {
			cpFatcaCountryDet = fatcaCountryDAO.fetchFatcaCountryDetails(cpFatca.get(0).getSerialNo());
		}
		TyChangeInContributionDTO tyChangeInContributionDTO = new TyChangeInContributionDTO();
		tyChangeInContributionDTO.setCpRequestMasters(requestMasters);
		tyChangeInContributionDTO.setCpContributionAlterations(cpContributionAlterations);
		tyChangeInContributionDTO.setCpFatca(cpFatca);
		tyChangeInContributionDTO.setCpFatcaCountryDet(cpFatcaCountryDet);
		
		return tyChangeInContributionDTO;
	}

	
	public void setRequestMasterDAO(CpRequestMasterDAO requestMasterDAO) {
		this.requestMasterDAO = requestMasterDAO;
	}

	public void setContributionAlterDAO(ContributionAlterDAO contributionAlterDAO) {
		this.contributionAlterDAO = contributionAlterDAO;
	}

	public void setCustomerLoginDao(CustomerLoginDAO customerLoginDao) {
		this.customerLoginDao = customerLoginDao;
	}

	public FatcaDAO getFatcaDAO() {
		return fatcaDAO;
	}

	public void setFatcaDAO(FatcaDAO fatcaDAO) {
		this.fatcaDAO = fatcaDAO;
	}

	public FatcaCountryDAO getFatcaCountryDAO() {
		return fatcaCountryDAO;
	}

	public void setFatcaCountryDAO(FatcaCountryDAO fatcaCountryDAO) {
		this.fatcaCountryDAO = fatcaCountryDAO;
	}

}
