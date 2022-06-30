package com.aetins.customerportal.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.dao.CpClaimIntimationDAO;
import com.aetins.customerportal.web.dao.CpRequestMasterDAO;
import com.aetins.customerportal.web.dto.ClaimIntimationDTO;
import com.aetins.customerportal.web.entity.CpClaimIntimation;
import com.aetins.customerportal.web.entity.CpFatca;
import com.aetins.customerportal.web.entity.CpFatcaCountryDet;
import com.aetins.customerportal.web.entity.CpRequestMaster;
import com.aetins.customerportal.web.service.CpClaimIntimationBL;
import com.aetins.customerportal.web.transaction.dto.TyClaimIntimationDTO;

@Service
public class CpClaimIntimationBLImpl implements CpClaimIntimationBL{

	private static final Logger logger = Logger.getLogger(CpClaimIntimationBLImpl.class);
	
	
	@Autowired
	CpClaimIntimationDAO cpClaimIntimationDAO;
	
	@Autowired
	CpRequestMasterDAO cpRequestMasterDAO;
	
	@Override
	public boolean insert(ClaimIntimationDTO claimIntimationDTO) {
		CpClaimIntimation cpclaimIntimation = new CpClaimIntimation();
		boolean status=false;
		try {
			CpRequestMaster requestMaster = new CpRequestMaster(); 
			requestMaster.setServiceRequestNo(claimIntimationDTO.getnServicRequestNo().getServiceRequestNo());
			
			cpclaimIntimation.setPolicyno(claimIntimationDTO.getPolicyno());
			cpclaimIntimation.setCustrefno(claimIntimationDTO.getCustrefno());
			cpclaimIntimation.setItitdtype(claimIntimationDTO.getItidtype());
			cpclaimIntimation.setItiidno(claimIntimationDTO.getItidno());
			cpclaimIntimation.setIntimatorcustrefno(claimIntimationDTO.getItrefno());
			cpclaimIntimation.setIticustype(claimIntimationDTO.getItcusttype());
			
			cpclaimIntimation.setItifstname(claimIntimationDTO.getItifstname());
			cpclaimIntimation.setItimdtname(claimIntimationDTO.getItimdtname());
			cpclaimIntimation.setItilstname(claimIntimationDTO.getItilstname());
			cpclaimIntimation.setItiname(claimIntimationDTO.getItiname());
			
			cpclaimIntimation.setItidob(claimIntimationDTO.getItidob());
			cpclaimIntimation.setItitilee(claimIntimationDTO.getItitilee());
			cpclaimIntimation.setItigender(claimIntimationDTO.getItigender());
			cpclaimIntimation.setIticontno(claimIntimationDTO.getIticontno());
			cpclaimIntimation.setItiemail(claimIntimationDTO.getItiemail());
			
			cpclaimIntimation.setEventgrp(claimIntimationDTO.getEventgroup());
			cpclaimIntimation.setEventdate(claimIntimationDTO.getEventdate());
			cpclaimIntimation.setCreatedate(claimIntimationDTO.getCreatedate());
			cpclaimIntimation.setIntimationo(null);
			cpclaimIntimation.setnServicRequestNo(requestMaster);
			cpclaimIntimation.setRemarks(claimIntimationDTO.getRemarks());
			
			cpclaimIntimation.setCauseloss(claimIntimationDTO.getCausecode());
			cpclaimIntimation.setEventplace(claimIntimationDTO.getEventplace());
			cpclaimIntimation.setIntimatetime(claimIntimationDTO.getIntimationtime());
						
			status=cpClaimIntimationDAO.insert(cpclaimIntimation);

		}catch(Exception e) {
			logger.error("Claim Inti service:"+e.getMessage());
		}
		return status;
	}

	@Autowired
	CpRequestMasterDAO requestMasterDAO;
	
	@Override
	public TyClaimIntimationDTO fetchRequestclaim(int serviceno) {
		CpRequestMaster requestMaster = new CpRequestMaster();
		requestMaster.setServiceRequestNo(serviceno);
		requestMaster = requestMasterDAO.fetchRequestMasters(requestMaster);
		List<CpRequestMaster> requestMasters = new ArrayList();
		requestMasters.add(requestMaster);
		
		List<CpClaimIntimation> cClaimIntimation = new ArrayList();
		cClaimIntimation = cpClaimIntimationDAO.fetchall(serviceno);

		List<CpFatca> cpFatca = new ArrayList();
		List<CpFatcaCountryDet> cpFatcaCountryDet = new ArrayList();
		
		TyClaimIntimationDTO tyClaimIntimationDTO = new TyClaimIntimationDTO();
		tyClaimIntimationDTO.setCpRequestMasters(requestMasters);
		tyClaimIntimationDTO.setCpClaimIntimation(cClaimIntimation);
		tyClaimIntimationDTO.setCpFatca(cpFatca);
		tyClaimIntimationDTO.setCpFatcaCountryDet(cpFatcaCountryDet);

		return tyClaimIntimationDTO;
	}

	@Override
	public boolean updatestatus(int serviceno) {
			CpRequestMaster cpRequestMaster = new CpRequestMaster();
			cpRequestMaster.setServiceRequestNo(serviceno);
			cpRequestMaster.setRequestStatus("AWAP");
		return cpRequestMasterDAO.updateColumns(cpRequestMaster);
	}


}
