package com.aetins.customerportal.web.service;

import java.util.List;

import com.aetins.customerportal.web.dto.CpOtpSettingsDTO;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.dto.PasswordRulesDTO;
import com.aetins.customerportal.web.dto.ServiceRequestMasterDTO;
import com.aetins.customerportal.web.entity.CpNFAddress;
import com.aetins.customerportal.web.entity.CpNFAddressContacts;
import com.aetins.customerportal.web.transaction.dto.TyNfAddressContactsDTO;
import com.aetins.customerportal.web.transaction.dto.TyNfAddressDTO;
import com.aetins.customerportal.web.transaction.dto.TyNfPersonalDetailsDTO;

/**
 * This Bl interface for update information management
 * @author viswakarthick
 * @since 07/03/2017 
 */
public interface UpdateInfoBL {
	public ServiceRequestMasterDTO getNewRequest(ServiceRequestMasterDTO requestDTO);
	public CpUserInfoDTO getCpUserInfo(String userName);
	public boolean updateColumns(ServiceRequestMasterDTO requestMasterDTO);
	public PasswordRulesDTO getCpSettingDto();
	public List<CpOtpSettingsDTO> fetchOtpSettings(String serviceType);
	
	public boolean saveUpdateInformationList( List<CpNFAddress> cpNFAddressList,
			List<CpNFAddressContacts> cpNFAddressContactList);
	public TyNfPersonalDetailsDTO fetchRequestAndPersonal(ServiceRequestMasterDTO serviceRequestMasterDTO);
	public TyNfAddressDTO fetchRequestAndAddress(ServiceRequestMasterDTO serviceRequestMasterDTO);
	public TyNfAddressContactsDTO fetchRequestAndAddressContact(ServiceRequestMasterDTO serviceRequestMasterDTO);
}
