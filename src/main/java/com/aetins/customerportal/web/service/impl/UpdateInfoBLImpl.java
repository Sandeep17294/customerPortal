package com.aetins.customerportal.web.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.dao.CpNFAddressContactsDAO;
import com.aetins.customerportal.web.dao.CpNFAddressDAO;
import com.aetins.customerportal.web.dao.CpNFPersonalContactsDAO;
import com.aetins.customerportal.web.dao.CpNFPersonalDetailsDAO;
import com.aetins.customerportal.web.dao.CpRequestMasterDAO;
import com.aetins.customerportal.web.dao.CpUserInfoDAO;
import com.aetins.customerportal.web.dao.FatcaCountryDAO;
import com.aetins.customerportal.web.dao.FatcaDAO;
import com.aetins.customerportal.web.dao.OtpSetUpDAO;
import com.aetins.customerportal.web.dao.PasswordRulesDAO;
import com.aetins.customerportal.web.dao.impl.CpNFAddressContactsDAOImpl;
import com.aetins.customerportal.web.dao.impl.CpNFAddressDAOImpl;
import com.aetins.customerportal.web.dao.impl.CpNFPersonalDetailsDAOImpl;
import com.aetins.customerportal.web.dao.impl.CpRequestMasterDAOImpl;
import com.aetins.customerportal.web.dao.impl.FatcaCountryDAOImpl;
import com.aetins.customerportal.web.dao.impl.FatcaDAOImpl;
import com.aetins.customerportal.web.dto.CpOtpSettingsDTO;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.dto.PasswordRulesDTO;
import com.aetins.customerportal.web.dto.ServiceRequestMasterDTO;
import com.aetins.customerportal.web.entity.CpFatca;
import com.aetins.customerportal.web.entity.CpFatcaCountryDet;
import com.aetins.customerportal.web.entity.CpNFAddress;
import com.aetins.customerportal.web.entity.CpNFAddressContacts;
import com.aetins.customerportal.web.entity.CpNFPersonalDetails;
import com.aetins.customerportal.web.entity.CpOtpSettings;
import com.aetins.customerportal.web.entity.CpRequestMaster;
import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.entity.PasswordRules;
import com.aetins.customerportal.web.service.UpdateInfoBL;
import com.aetins.customerportal.web.transaction.dto.TyNfAddressContactsDTO;
import com.aetins.customerportal.web.transaction.dto.TyNfAddressDTO;
import com.aetins.customerportal.web.transaction.dto.TyNfPersonalDetailsDTO;
import com.aetins.customerportal.web.utils.BSLUtils;
/**
 * For update information business logic implementation 
 * @author viswakarthick
 * @since 07/03/2017
 */

@Service
public class UpdateInfoBLImpl implements UpdateInfoBL {
	
	private static final Logger logger = Logger.getLogger(UpdateInfoBLImpl.class);
	
	@Autowired
	CpRequestMasterDAO requestMasterDAO;
	
	@Autowired
	CpUserInfoDAO cpUserInfoDAO;
	
	@Autowired
	PasswordRulesDAO passwordRulesDao;
	
	@Autowired
	OtpSetUpDAO otpSetUpDAO;
	
	@Autowired
	CpNFPersonalDetailsDAO cpNFPersonalDetailsDAO;
	
	@Autowired
	CpNFPersonalContactsDAO cpNFPersonalContactsDAO;
	
	@Autowired
	CpNFAddressDAO cpNFAddressDAO;
	
	@Autowired
	CpNFAddressContactsDAO cpNFAddressContactsDAO;
	
	@Autowired
	FatcaDAO fatcaDAO;
	
	@Autowired
    FatcaCountryDAO  fatcaCountryDAO;
	//setter
	public void setRequestMasterDAO(CpRequestMasterDAO requestMasterDAO) {
		this.requestMasterDAO = requestMasterDAO;
	}

	public void setCpUserInfoDAO(CpUserInfoDAO cpUserInfoDAO) {
		this.cpUserInfoDAO = cpUserInfoDAO;
	}
	
	public void setPasswordRulesDao(PasswordRulesDAO passwordRulesDao) {
		this.passwordRulesDao = passwordRulesDao;
	}	

	public void setOtpSetUpDAO(OtpSetUpDAO otpSetUpDAO) {
		this.otpSetUpDAO = otpSetUpDAO;
	}	
	
	public void setCpNFPersonalDetailsDAO(CpNFPersonalDetailsDAO cpNFPersonalDetailsDAO) {
		this.cpNFPersonalDetailsDAO = cpNFPersonalDetailsDAO;
	}

	public void setCpNFPersonalContactsDAO(CpNFPersonalContactsDAO cpNFPersonalContactsDAO) {
		this.cpNFPersonalContactsDAO = cpNFPersonalContactsDAO;
	}

	public void setCpNFAddressDAO(CpNFAddressDAO cpNFAddressDAO) {
		this.cpNFAddressDAO = cpNFAddressDAO;
	}

	public void setCpNFAddressContactsDAO(CpNFAddressContactsDAO cpNFAddressContactsDAO) {
		this.cpNFAddressContactsDAO = cpNFAddressContactsDAO;
	}

	@Override
	public ServiceRequestMasterDTO getNewRequest(ServiceRequestMasterDTO requestDTO) {
        
        CpRequestMaster requestMaster = new CpRequestMaster();
        requestMaster.setPolicyNo(requestDTO.getPolicyNo());
        requestMaster.setProcessedBy(requestDTO.getProcessedBy());
        requestMaster.setProcessedDate(requestDTO.getProcessedDate());
        requestMaster.setRecentUpdate(requestDTO.getRecentUpdateDate());
        requestMaster.setRequestStatus(requestDTO.getRequestStatus());
        requestMaster.setServiceRequestDate(requestDTO.getServiceRequestDate());
        requestMaster.setServiceRequestType(requestDTO.getServiceRequestType());
        requestMaster.setUserId(requestDTO.getUserId());
       
        logger.info("UpdateInfoBLImpl:save new request and get new request ");
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
	public CpUserInfoDTO getCpUserInfo(String userName) {
		logger.info("BLImpl:Get user information ");
		CpUserInfo cpUser = cpUserInfoDAO.getCpUserInfo(userName);
		CpUserInfoDTO cpUserInfoDto = new CpUserInfoDTO();
		if (cpUser != null) {
			cpUserInfoDto.setNid(cpUser.getNid());
			cpUserInfoDto.setNcustRefNo(cpUser.getNcustRefNo());
			if (BSLUtils.isNotNull(cpUser.getVgroup()))
				cpUserInfoDto.setVgroup(cpUser.getVgroup());
			if (BSLUtils.isNotNull(cpUser.getVtitle()))
				cpUserInfoDto.setVtitle(cpUser.getVtitle());
			cpUserInfoDto.setVuserName(cpUser.getVuserName());
			if (BSLUtils.isNotNull(cpUser.getVprefName()))
				cpUserInfoDto.setVprefName(cpUser.getVprefName());
			if (BSLUtils.isNotNull(cpUser.getVemail()))
				cpUserInfoDto.setVemail(cpUser.getVemail());
			if(BSLUtils.isNotNull(cpUser.getVcontactNo()))
				cpUserInfoDto.setVcontactNo(cpUser.getVcontactNo());
			if(BSLUtils.isNotNull(cpUser.getVquestion1()))
				cpUserInfoDto.setVquestion1(cpUser.getVquestion1());
			if(BSLUtils.isNotNull(cpUser.getVquestion2()))
				cpUserInfoDto.setVquestion2(cpUser.getVquestion2());
			if(BSLUtils.isNotNull(cpUser.getVanswer1()))
				cpUserInfoDto.setVanswer1(cpUser.getVanswer1());
			if(BSLUtils.isNotNull(cpUser.getVanswer2()))
				cpUserInfoDto.setVanswer2(cpUser.getVanswer2());
		}
		return cpUserInfoDto;
	}

	@Override
	public boolean updateColumns(ServiceRequestMasterDTO requestMasterDTO) {
		logger.info("BLImpl:update Cpservicerequestmaster information ");
		CpRequestMaster requestMaster =  new CpRequestMaster();
		requestMaster.setServiceRequestNo(requestMasterDTO.getServiceRequestNo());
		requestMaster.setRequestStatus(requestMasterDTO.getRequestStatus());
		requestMaster.setRecentUpdate(requestMasterDTO.getRecentUpdateDate());
		return requestMasterDAO.updateColumns(requestMaster);
	}
	
	@Override
	public PasswordRulesDTO getCpSettingDto() {
		
		PasswordRulesDTO dto=new PasswordRulesDTO();
		
		List<PasswordRules> passwordRules = passwordRulesDao.listPasswordRules();
			dto.setvCsdEmail(passwordRules.get(0).getvCsdEmail());
			dto.setvCompPrefix(passwordRules.get(0).getvCompPrefix());
			dto.setnRequiredMobOTP(passwordRules.get(0).getnRequiredMobOTP());
		return dto;
	}

	@Override
	public List<CpOtpSettingsDTO> fetchOtpSettings(String serviceType) {
		List<CpOtpSettings> otpRules= otpSetUpDAO.listOtpSettings(serviceType);
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
	public boolean saveUpdateInformationList(
		 List<CpNFAddress> cpNFAddressList,
		List<CpNFAddressContacts> cpNFAddressContactList) {
		boolean status = Boolean.FALSE;
		boolean status1 = true, status2 = true, status3 = true, status4 = true;
		/*if (cpNFPersonalDetailList.size() > 0) {
			logger.info("BLImpl:save CpNFPersonalDetails information started");
			status1 = cpNFPersonalDetailsDAO.savePersonalDetails(cpNFPersonalDetailList);
		}*/
	
		if (cpNFAddressList.size() > 0) {
		logger.info("BLImpl:save CpNFAddress information started");
		status3 = cpNFAddressDAO.saveAddressDetails(cpNFAddressList);
		}
		
		if (cpNFAddressContactList.size() > 0) {
			logger.info("BLImpl:save CpNFAddressContacts information started");
			status4 = cpNFAddressContactsDAO.saveAddressContacts(cpNFAddressContactList);
		}
		if (status1 && status2 && status3 && status4)
			status = Boolean.TRUE;

		return status;
	}
	
	
	@Override
	public TyNfPersonalDetailsDTO fetchRequestAndPersonal(ServiceRequestMasterDTO serviceRequestMasterDTO) {
		
		CpRequestMaster requestMaster = new CpRequestMaster();		
		requestMaster.setServiceRequestNo(serviceRequestMasterDTO.getServiceRequestNo());
		requestMaster = requestMasterDAO.fetchRequestMasters(requestMaster);
		List<CpRequestMaster> requestMasters = new ArrayList<CpRequestMaster>();
		requestMasters.add(requestMaster);
		
		List<CpNFPersonalDetails> cpNFPersonalDetails = new ArrayList<CpNFPersonalDetails>();	
		cpNFPersonalDetails = cpNFPersonalDetailsDAO.fetchAllPersonalDetails(serviceRequestMasterDTO.getServiceRequestNo());
		
		List<CpFatca> cpFatca = new ArrayList<CpFatca>();
		cpFatca = fatcaDAO.fetchFatcaDetails(serviceRequestMasterDTO.getServiceRequestNo());
		
		List<CpFatcaCountryDet> cpFatcaCountryDet = new ArrayList<CpFatcaCountryDet>();
		if (cpFatca.size() > 0) {
			cpFatcaCountryDet = fatcaCountryDAO.fetchFatcaCountryDetails(cpFatca.get(0).getSerialNo());
		}
		TyNfPersonalDetailsDTO tyUpdateInformationDTO = new TyNfPersonalDetailsDTO();
		tyUpdateInformationDTO.setCpRequestMasters(requestMasters);
		tyUpdateInformationDTO.setCpNFPersonalDetails(cpNFPersonalDetails);
		tyUpdateInformationDTO.setCpFatca(cpFatca);
		tyUpdateInformationDTO.setCpFatcaCountryDet(cpFatcaCountryDet);
		
		return tyUpdateInformationDTO;
	}
	
	@Override
	public TyNfAddressDTO fetchRequestAndAddress(ServiceRequestMasterDTO serviceRequestMasterDTO) {
		
		CpRequestMaster requestMaster = new CpRequestMaster();		
		requestMaster.setServiceRequestNo(serviceRequestMasterDTO.getServiceRequestNo());
		requestMaster = requestMasterDAO.fetchRequestMasters(requestMaster);
		List<CpRequestMaster> requestMasters = new ArrayList<CpRequestMaster>();
		requestMasters.add(requestMaster);
		
		List<CpNFAddress> cpNFAddress = new ArrayList<CpNFAddress>();		
		cpNFAddress = cpNFAddressDAO.fetchAllAddressDetails(serviceRequestMasterDTO.getServiceRequestNo());
		
		List<CpFatca> cpFatca = new ArrayList<CpFatca>();
		cpFatca = fatcaDAO.fetchFatcaDetails(serviceRequestMasterDTO.getServiceRequestNo());
		
		List<CpFatcaCountryDet> cpFatcaCountryDet = new ArrayList<CpFatcaCountryDet>();
		if (cpFatca.size() > 0) {
			cpFatcaCountryDet = fatcaCountryDAO.fetchFatcaCountryDetails(cpFatca.get(0).getSerialNo());
		}
		TyNfAddressDTO tyNfAddressDTO = new TyNfAddressDTO();
		tyNfAddressDTO.setCpRequestMasters(requestMasters);
		tyNfAddressDTO.setCpNFAddress(cpNFAddress);
		tyNfAddressDTO.setCpFatca(cpFatca);
		tyNfAddressDTO.setCpFatcaCountryDet(cpFatcaCountryDet);
		
		return tyNfAddressDTO;
	}
	
	@Override
	public TyNfAddressContactsDTO fetchRequestAndAddressContact(ServiceRequestMasterDTO serviceRequestMasterDTO) {
		
		CpRequestMaster requestMaster = new CpRequestMaster();		
		requestMaster.setServiceRequestNo(serviceRequestMasterDTO.getServiceRequestNo());
		requestMaster = requestMasterDAO.fetchRequestMasters(requestMaster);
		List<CpRequestMaster> requestMasters = new ArrayList<CpRequestMaster>();
		requestMasters.add(requestMaster);
		
		List<CpNFAddressContacts> cpNFAddressContacts = new ArrayList<CpNFAddressContacts>();
		
		cpNFAddressContacts = cpNFAddressContactsDAO.fetchAllAddressContactDetails(serviceRequestMasterDTO.getServiceRequestNo());
		
		List<CpFatca> cpFatca = new ArrayList<CpFatca>();
		cpFatca = fatcaDAO.fetchFatcaDetails(serviceRequestMasterDTO.getServiceRequestNo());
		
		List<CpFatcaCountryDet> cpFatcaCountryDet = new ArrayList<CpFatcaCountryDet>();
		if (cpFatca.size() > 0) {
			cpFatcaCountryDet = fatcaCountryDAO.fetchFatcaCountryDetails(cpFatca.get(0).getSerialNo());
		}
		TyNfAddressContactsDTO tyNfAddressContactsDTO = new TyNfAddressContactsDTO();
		tyNfAddressContactsDTO.setCpRequestMasters(requestMasters);
		tyNfAddressContactsDTO.setCpNFAddressContacts(cpNFAddressContacts);
		tyNfAddressContactsDTO.setCpFatca(cpFatca);
		tyNfAddressContactsDTO.setCpFatcaCountryDet(cpFatcaCountryDet);
		
		return tyNfAddressContactsDTO;
	}

}
