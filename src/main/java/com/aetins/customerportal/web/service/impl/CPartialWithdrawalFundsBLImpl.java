package com.aetins.customerportal.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.dao.CpPartialWithdrawalFundsDAO;
import com.aetins.customerportal.web.dao.CpRequestMasterDAO;
import com.aetins.customerportal.web.dao.FatcaCountryDAO;
import com.aetins.customerportal.web.dao.FatcaDAO;
import com.aetins.customerportal.web.dto.CpPartialWithdrawalFundsDTO;
import com.aetins.customerportal.web.dto.ProtectionBenifitDTO;
import com.aetins.customerportal.web.dto.ServiceRequestMasterDTO;
import com.aetins.customerportal.web.entity.CpFatca;
import com.aetins.customerportal.web.entity.CpFatcaCountryDet;
import com.aetins.customerportal.web.entity.CpPartialWithdrawalFunds;
import com.aetins.customerportal.web.entity.CpProtectionBenifit;
import com.aetins.customerportal.web.entity.CpRedirectionSwitching;
import com.aetins.customerportal.web.entity.CpRequestMaster;
import com.aetins.customerportal.web.service.CPartialWithdrawalFundsBL;
import com.aetins.customerportal.web.transaction.dto.TyPartialWithdrawDTO;
import com.aetins.customerportal.web.transaction.dto.TyRedirectionSwitchingDTO;

@Service
public class CPartialWithdrawalFundsBLImpl implements CPartialWithdrawalFundsBL{

	private static final Logger logger = Logger.getLogger(CPartialWithdrawalFundsBLImpl.class);

	@Autowired
	CpPartialWithdrawalFundsDAO cpPartialWithdrawalFundsDAO;
	
	@Autowired
	CpRequestMasterDAO requestMasterDAO;
	
	@Autowired
	FatcaDAO fatcaDAO;

	@Autowired
	FatcaCountryDAO fatcaCountryDAO;


	
	@Override
	public boolean insert(List<CpPartialWithdrawalFundsDTO> cpPartialWithdrawalFundsdto) {
		List<CpPartialWithdrawalFunds> cpPartialWithdrawalFunds = new ArrayList<CpPartialWithdrawalFunds>();
		try {
			CpRequestMaster requestMaster = new CpRequestMaster();    
			for (CpPartialWithdrawalFundsDTO each : cpPartialWithdrawalFundsdto) {
				requestMaster.setServiceRequestNo(each.getnServicRequestNo().getServiceRequestNo());
				CpPartialWithdrawalFunds PartialWithdrawalFunds = new CpPartialWithdrawalFunds();
				PartialWithdrawalFunds.setnServicRequestNo(requestMaster);
				PartialWithdrawalFunds.setvFundType(each.getvFundType());
				PartialWithdrawalFunds.setvFundCode(each.getvFundCode());
				PartialWithdrawalFunds.setvFundName(each.getvFundName());
				PartialWithdrawalFunds.setvFundCurrency(each.getvFundCurrency());
				PartialWithdrawalFunds.setnFundValue(each.getnFundValue());
				PartialWithdrawalFunds.setvCriteria(each.getvCriteria());
				PartialWithdrawalFunds.setnCriteriaValue(each.getnCriteriaValue());
				PartialWithdrawalFunds.setSeqno(each.getSeqno());
				PartialWithdrawalFunds.setVinvestementsource(each.getInvestsourcode());
				PartialWithdrawalFunds.setRemarks(each.getRemarks());
				cpPartialWithdrawalFunds.add(PartialWithdrawalFunds);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return cpPartialWithdrawalFundsDAO.insert(cpPartialWithdrawalFunds);
	}

	@Override
	public TyPartialWithdrawDTO fetchRequestPartialwithdraw(ServiceRequestMasterDTO paramServiceRequestMasterDTO) {

		CpRequestMaster requestMaster = new CpRequestMaster();

		requestMaster.setServiceRequestNo(paramServiceRequestMasterDTO.getServiceRequestNo());
		requestMaster = requestMasterDAO.fetchRequestMasters(requestMaster);
		List<CpRequestMaster> requestMasters = new ArrayList();
		requestMasters.add(requestMaster);

		List<CpPartialWithdrawalFunds> cpPartialWithdrawalFunds = new ArrayList();
		cpPartialWithdrawalFunds = cpPartialWithdrawalFundsDAO.fetchAllPartialwithdraw(paramServiceRequestMasterDTO.getServiceRequestNo());

		List<CpFatca> cpFatca = new ArrayList();
		cpFatca = fatcaDAO.fetchFatcaDetails(paramServiceRequestMasterDTO.getServiceRequestNo());

		List<CpFatcaCountryDet> cpFatcaCountryDet = new ArrayList();
		if (cpFatca.size() > 0) {
			cpFatcaCountryDet = fatcaCountryDAO.fetchFatcaCountryDetails(((CpFatca) cpFatca.get(0)).getSerialNo());
		}
		TyPartialWithdrawDTO tyPartialWithdrawDTO = new TyPartialWithdrawDTO();
		tyPartialWithdrawDTO.setCpRequestMasters(requestMasters);
		tyPartialWithdrawDTO.setCpPartialWithdrawalFunds(cpPartialWithdrawalFunds);
		tyPartialWithdrawDTO.setCpFatca(cpFatca);
		tyPartialWithdrawDTO.setCpFatcaCountryDet(cpFatcaCountryDet);

		return tyPartialWithdrawDTO;
	}

}
