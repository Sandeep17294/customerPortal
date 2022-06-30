package com.aetins.customerportal.web.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.dao.CpRequestMasterDAO;
import com.aetins.customerportal.web.dao.CustomerLoginDAO;
import com.aetins.customerportal.web.dao.OtpSetUpDAO;
import com.aetins.customerportal.web.dao.PasswordRulesDAO;
import com.aetins.customerportal.web.dto.CpOtpSettingsDTO;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.dto.PasswordRulesDTO;
import com.aetins.customerportal.web.dto.ServiceRequestMasterDTO;
import com.aetins.customerportal.web.dto.UserDTO;
import com.aetins.customerportal.web.entity.CpOtpSettings;
import com.aetins.customerportal.web.entity.CpRequestMaster;
import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.entity.PasswordRules;
import com.aetins.customerportal.web.service.TransactionBL;

@Service
public class TransactionBLImpl implements TransactionBL {

	@Autowired
	CpRequestMasterDAO cpRequestMasterDAO;
	
	@Autowired
	PasswordRulesDAO passwordRulesDao;
	
	@Autowired
	OtpSetUpDAO OtpSetUpDAO;
	
	@Autowired
	CustomerLoginDAO customerLoginDao;
	
	

	@Override
	public boolean updateDetails(ServiceRequestMasterDTO serviceRequestMasterDTO) {
		CpRequestMaster cpRequestMaster = new CpRequestMaster();
		cpRequestMaster.setServiceRequestNo(serviceRequestMasterDTO.getServiceRequestNo());
		cpRequestMaster.setRecentUpdate(serviceRequestMasterDTO.getRecentUpdateDate());
		cpRequestMaster.setRequestStatus(serviceRequestMasterDTO.getRequestStatus());
		cpRequestMaster.setFatcaFlag(serviceRequestMasterDTO.getFatcaFlag());
		return cpRequestMasterDAO.updateColumns(cpRequestMaster);
	}
	
	@Override
	public boolean saveOTP(ServiceRequestMasterDTO serviceRequestMasterDTO) {
		CpRequestMaster cpRequestMaster = new CpRequestMaster();
		cpRequestMaster.setServiceRequestNo(serviceRequestMasterDTO.getServiceRequestNo());
		cpRequestMaster.setUserOtp(serviceRequestMasterDTO.getUserOtp());
		return cpRequestMasterDAO.saveOTP(cpRequestMaster);
	}

	
	@Override
	public PasswordRulesDTO getCpSettingDto() {

		PasswordRulesDTO passwordRulesDTO = new PasswordRulesDTO();

		List<PasswordRules> passwordRules = passwordRulesDao.listPasswordRules();
		passwordRulesDTO.setnRequiredMobOTP(passwordRules.get(0).getnRequiredMobOTP());
		return passwordRulesDTO;
	}

	@Override
	public List<CpOtpSettingsDTO> fetchOtpSettings(String serviceType) {
		List<CpOtpSettings> otpRules = OtpSetUpDAO.listOtpSettings(serviceType);
		List<CpOtpSettingsDTO> settingsList = new ArrayList<CpOtpSettingsDTO>();
		for (CpOtpSettings cpOtpSettings : otpRules) {
			CpOtpSettingsDTO cpOtpSettingsDTOs = new CpOtpSettingsDTO();
			cpOtpSettingsDTOs.setvOtpFlagEmail(cpOtpSettings.getvOtpFlagEmail());
			cpOtpSettingsDTOs.setvOtpFlagMobile(cpOtpSettings.getvOtpFlagMobile());
			settingsList.add(cpOtpSettingsDTOs);
		}

		return settingsList;
	}
	
	@Override
	public List<CpUserInfoDTO> getUserDetails(UserDTO verifyUserdto) {
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
			cpUserInfoDTO.setVquestion2(cpUserInfo.getVquestion2());
			cpUserInfoDTO.setVanswer2(cpUserInfo.getVanswer2());
			userAllLists.add(cpUserInfoDTO);
		}
		return userAllLists;
	}
	
	@Override
	public boolean delete(List<ServiceRequestMasterDTO> serviceRequestMasterDTO) {
	List<CpRequestMaster> request = new ArrayList<CpRequestMaster>();
	CpRequestMaster dto = new CpRequestMaster();
		for(int i=0; i<serviceRequestMasterDTO.size();i++) {
			dto.setServiceRequestNo(serviceRequestMasterDTO.get(i).getServiceRequestNo());
			request.set(i, dto);	
		}
		return	cpRequestMasterDAO.deletetransaction(request);
		
		
		
	}

	@Override
	public boolean delete(ServiceRequestMasterDTO serviceRequestMasterDTO) {
		CpRequestMaster request = new CpRequestMaster();
		request.setServiceRequestNo(serviceRequestMasterDTO.getServiceRequestNo());
		return	cpRequestMasterDAO.deletetransaction(request);
	}

	@Override
	public List<CpRequestMaster> servicerequestfetch() {
		List<CpRequestMaster> totalfetch = new ArrayList<CpRequestMaster>();
		totalfetch=cpRequestMasterDAO.servicerequestfetch();
		// TODO Auto-generated method stub
		return totalfetch;
	}

	

	public PasswordRulesDAO getPasswordRulesDao() {
		return passwordRulesDao;
	}

	public void setPasswordRulesDao(PasswordRulesDAO passwordRulesDao) {
		this.passwordRulesDao = passwordRulesDao;
	}

	public CpRequestMasterDAO getCpRequestMasterDAO() {
		return cpRequestMasterDAO;
	}

	public void setCpRequestMasterDAO(CpRequestMasterDAO cpRequestMasterDAO) {
		this.cpRequestMasterDAO = cpRequestMasterDAO;
	}

	public OtpSetUpDAO getOtpSetUpDAO() {
		return OtpSetUpDAO;
	}

	public void setOtpSetUpDAO(OtpSetUpDAO otpSetUpDAO) {
		OtpSetUpDAO = otpSetUpDAO;
	}

	public CustomerLoginDAO getCustomerLoginDao() {
		return customerLoginDao;
	}

	public void setCustomerLoginDao(CustomerLoginDAO customerLoginDao) {
		this.customerLoginDao = customerLoginDao;
	}


}
