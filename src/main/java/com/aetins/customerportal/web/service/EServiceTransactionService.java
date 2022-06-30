package com.aetins.customerportal.web.service;

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

import salama_cust_portal_oracle_.phasetwoservice_wsdl.PhaseTwoServiceStub;



/**
 * @author Viswakarthick
 * @since 06/07/2017
 * @param <T>
 */
public interface EServiceTransactionService {
	
	//need to check the functionality
	public PhaseTwoServiceStub getServiceProxy() throws Exception;
	
    public String saveContributionAlteration(TyChangeInContributionDTO changeInContributionDTO) throws Exception;
	
	public String saveRedirectionAndSwitching(TyRedirectionSwitchingDTO redirectionSwitchingDTO) throws Exception;
	
	public String saveReinstatementAlteration(TyReinstatementDTO reinstatementDTO) throws Exception;
	
	public String saveContributionHoliday(TyContributionHolidayDTO contributionHolidayDTO) throws Exception;

	public String saveUpdateInformationPersonalDeatils(TyNfPersonalDetailsDTO tyNfPersonalDetailsDTO) throws Exception;

	public String saveUpdateInformationAddressContact(TyNfAddressContactsDTO tyNfAddressContactsDTO) throws Exception;

	public String saveAddressDetails(TyNfAddressDTO tyNfAddressDTO) throws Exception;
	
	public String saveProtectionBenifit(TyProtectionBenifitDTO tyProtectionBenifitDTO) throws Exception;

	public String savepartialwithdraw(TyPartialWithdrawDTO tyPartialWithdrawDTO) throws Exception;

	public String saveclaimintimation(TyClaimIntimationDTO tyClaimIntimationDTO) throws Exception;
}
