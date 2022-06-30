package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.dto.CpPartialWithdrawalFundsDTO;
import com.aetins.customerportal.web.entity.CpPartialWithdrawalFunds;
import com.aetins.customerportal.web.entity.CpRedirectionSwitching;

public interface CpPartialWithdrawalFundsDAO {

	public boolean insert(List<CpPartialWithdrawalFunds> cpPartialWithdrawalFunds);
	
	public List<CpPartialWithdrawalFunds> fetchAllPartialwithdraw(int serviceReqNo);
		
	}
	
