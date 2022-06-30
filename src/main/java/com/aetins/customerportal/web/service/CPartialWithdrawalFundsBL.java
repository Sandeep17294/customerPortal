package com.aetins.customerportal.web.service;

import java.util.List;

import com.aetins.customerportal.web.dto.CpPartialWithdrawalFundsDTO;
import com.aetins.customerportal.web.dto.ServiceRequestMasterDTO;
import com.aetins.customerportal.web.transaction.dto.TyPartialWithdrawDTO;
import com.aetins.customerportal.web.transaction.dto.TyRedirectionSwitchingDTO;

public interface CPartialWithdrawalFundsBL {
	
	public boolean insert(List<CpPartialWithdrawalFundsDTO> cpPartialWithdrawalFundsDTO);
	public abstract TyPartialWithdrawDTO fetchRequestPartialwithdraw(ServiceRequestMasterDTO paramServiceRequestMasterDTO);

}
