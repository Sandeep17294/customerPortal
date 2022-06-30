package com.aetins.customerportal.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.dao.CpRequestMasterDAO;
import com.aetins.customerportal.web.dao.FatcaCountryDAO;
import com.aetins.customerportal.web.dao.FatcaDAO;
import com.aetins.customerportal.web.dao.ProtectionBenifitDAO;
import com.aetins.customerportal.web.dao.impl.CpRequestMasterDAOImpl;
import com.aetins.customerportal.web.dao.impl.FatcaCountryDAOImpl;
import com.aetins.customerportal.web.dao.impl.FatcaDAOImpl;
import com.aetins.customerportal.web.dao.impl.ProtectionBenifitDAOImpl;
import com.aetins.customerportal.web.dto.ProtectionBenifitDTO;
import com.aetins.customerportal.web.dto.ServiceRequestMasterDTO;
import com.aetins.customerportal.web.entity.CpFatca;
import com.aetins.customerportal.web.entity.CpFatcaCountryDet;
import com.aetins.customerportal.web.entity.CpProtectionBenifit;
import com.aetins.customerportal.web.entity.CpRequestMaster;
import com.aetins.customerportal.web.service.ProtectionBenifitBL;
import com.aetins.customerportal.web.transaction.dto.TyProtectionBenifitDTO;

@Service
public class ProtectionBenifitBLImpl implements ProtectionBenifitBL {

	private static final Logger logger = Logger.getLogger(ProtectionBenifitBLImpl.class);

	@Autowired
	ProtectionBenifitDAO protectionBenifitDAO;

	@Autowired
	CpRequestMasterDAO cpRequestMasterDAO;

	@Autowired
	FatcaDAO fatcaDAO;

	@Autowired
	FatcaCountryDAO fatcaCountryDAO;

	@Override
	public boolean protectionBenifitAlteration(List<ProtectionBenifitDTO> selectedBenifitList) {
		// TODO Auto-generated method stub
		List<CpProtectionBenifit> cpProtectionBenifitList = new ArrayList<CpProtectionBenifit>();
		try {

			CpRequestMaster requestMaster = new CpRequestMaster();    
			for (ProtectionBenifitDTO each : selectedBenifitList) {
				requestMaster.setServiceRequestNo(each.getServiceRequestNo().getServiceRequestNo());
				CpProtectionBenifit cpProtectionBenifit = new CpProtectionBenifit();
				cpProtectionBenifit.setServiceRequestNo(requestMaster);
				cpProtectionBenifit.setPolicyNo(each.getPolicyNo());
				String type = each.getAlterationType();
				logger.info("Type for benefit selected is ::::::::::" + type);
				switch (type) {
				case "I":
					cpProtectionBenifit.setAlterationType("Increase in Basic Sum Covered");
					break;
				case "D":
					cpProtectionBenifit.setAlterationType("Decrease in Basic Sum Covered");
					break;
				case "IR":
					cpProtectionBenifit.setAlterationType("Increase in Rider Sum Covered");
					break;
				case "DR":
					cpProtectionBenifit.setAlterationType("Decrease in Rider Sum Covered");
					break;
				case "ARB":
					cpProtectionBenifit.setAlterationType("Addition of Suplimentary Rider");
					break;
				case "DRB":
					cpProtectionBenifit.setAlterationType("Deletion of Suplimentary Rider");
					break;
				}
				cpProtectionBenifit.setBenifitType(each.getBenifitType());
				cpProtectionBenifit.setBenifitRiderName(each.getBenifitRiderName());
				cpProtectionBenifit.setExistBenifit(each.getExistBenifit());
				cpProtectionBenifit.setNewvalue(each.getNewvalue());
				cpProtectionBenifit.setMemberName(each.getMemberName());
				cpProtectionBenifit.setRiderCode(each.getRiderCode());
				cpProtectionBenifit.setCummulativeBenifit(each.getCummulativeBenifit());
				cpProtectionBenifit.setAssetsCash(each.getAssetsCash());
				cpProtectionBenifit.setAssetsOthers(each.getAssetsOthers());
				cpProtectionBenifit.setAssetsRealEstate(each.getAssetsRealEstate());
				cpProtectionBenifit.setAssetsShares(each.getAssetsShares());
				cpProtectionBenifit.setCoveredBankName(each.getCoveredBankName());
				cpProtectionBenifit.setCoveredIBAN(each.getCoveredIBAN());
				cpProtectionBenifit.setCoveredSourceFund(each.getCoveredSourceFund());
				cpProtectionBenifit.setPlanHolderBankName(each.getPlanHolderBankName());
				cpProtectionBenifit.setPlanHolderIBAN(each.getPlanHolderIBAN());
				cpProtectionBenifit.setPlanHolderSourceFund(each.getPlanHolderSourceFund());
				cpProtectionBenifit.setIncomeYearOne(each.getIncomeYearOne());
				cpProtectionBenifit.setIncomeYearTwo(each.getIncomeYearTwo());
				cpProtectionBenifit.setIncomeYearThree(each.getIncomeYearThree());
				cpProtectionBenifit.setLiabilitiesLoan(each.getLiabilitiesLoan());
				cpProtectionBenifit.setLiabilitiesMortgage(each.getLiabilitiesMortgage());
				cpProtectionBenifit.setLiabilitiesOther(each.getLiabilitiesOther());
				cpProtectionBenifit.setLiabilitiesPayable(each.getLiabilitiesPayable());
				
			    cpProtectionBenifit.setFirstCoverHeight(each.getFirstCoverHeight());
                cpProtectionBenifit.setFirstCoverWeight(each.getFirstCoverWeight());
                cpProtectionBenifit.setSecondCoverHeight(each.getSecondCoverHeight());
                cpProtectionBenifit.setSecondCoverWeight(each.getSecondCoverWeight());
				
				
				cpProtectionBenifit.setQuesOneFirst(each.getQuesOneFirst());
				cpProtectionBenifit.setQuesTwoAFirst(each.getQuesTwoAFirst());
				cpProtectionBenifit.setQuesTwoBFirst(each.getQuesTwoBSecond());
				cpProtectionBenifit.setQuesTwoCFirst(each.getQuesTwoCFirst());
				cpProtectionBenifit.setQuesTwoDFirst(each.getQuesTwoDFirst());
				cpProtectionBenifit.setQuesTwoEFirst(each.getQuesTwoEFirst());
				cpProtectionBenifit.setQuesTwoFFirst(each.getQuesTwoFFirst());
				cpProtectionBenifit.setQuesTwoGFirst(each.getQuesTwoFFirst());
				cpProtectionBenifit.setQuesTwoHFirst(each.getQuesTwoGFirst());
				cpProtectionBenifit.setQuesThreeFirst(each.getQuesThreeFirst());
				cpProtectionBenifit.setQuesFourFirst(each.getQuesFourFirst());
				cpProtectionBenifit.setQuesFiveFirst(each.getQuesFiveFirst());
				cpProtectionBenifit.setQuesSixFirst(each.getQuesSixFirst());
				cpProtectionBenifit.setQuesSevenFirst(each.getQuesSevenFirst());
				cpProtectionBenifit.setQuesEightFirst(each.getQuesEightFirst());
				cpProtectionBenifit.setQuesNineFirst(each.getQuesNineFirst());
				cpProtectionBenifit.setQuesTenFirst(each.getQuesTenFirst());
				cpProtectionBenifit.setQuesElevenFirst(each.getQuesElevenFirst());
				cpProtectionBenifit.setQuesElevenAFirst(each.getQuesElevenAFirst());

				cpProtectionBenifit.setQuesOneSecond(each.getQuesOneSecond());
				cpProtectionBenifit.setQuesTwoASecond(each.getQuesTwoASecond());
				cpProtectionBenifit.setQuesTwoBSecond(each.getQuesTwoBSecond());
				cpProtectionBenifit.setQuesTwoCSecond(each.getQuesTwoCSecond());
				cpProtectionBenifit.setQuesTwoDSecond(each.getQuesTwoDSecond());
				cpProtectionBenifit.setQuesTwoESecond(each.getQuesTwoESecond());
				cpProtectionBenifit.setQuesTwoFSecond(each.getQuesTwoFSecond());
				cpProtectionBenifit.setQuesTwoGSecond(each.getQuesTwoGSecond());
				cpProtectionBenifit.setQuesTwoHSecond(each.getQuesTwoHSecond());
				cpProtectionBenifit.setQuesThreeSecond(each.getQuesThreeSecond());
				cpProtectionBenifit.setQuesFourSecond(each.getQuesFourSecond());
				cpProtectionBenifit.setQuesFiveSecond(each.getQuesFiveSecond());
				cpProtectionBenifit.setQuesSixSecond(each.getQuesSixSecond());
				cpProtectionBenifit.setQuesSevenSecond(each.getQuesSevenSecond());
				cpProtectionBenifit.setQuesEightSecond(each.getQuesEightSecond());
				cpProtectionBenifit.setQuesNineSecond(each.getQuesNineSecond());
				cpProtectionBenifit.setQuesTenSecond(each.getQuesTenSecond());
				cpProtectionBenifit.setQuesElevenSecond(each.getQuesElevenSecond());
				cpProtectionBenifit.setQuesElevenASecond(each.getQuesElevenASecond());
				cpProtectionBenifit.setFirstCoverMedDetails(each.getFirstCoverMedDetails());
				cpProtectionBenifit.setSecondCoverMedDetails(each.getSecondCoverMedDetails());

				cpProtectionBenifitList.add(cpProtectionBenifit);
				logger.info("Benefit sixe inside BLIMPL is " + cpProtectionBenifitList.size());

			}

		} catch (Exception e) {
			logger.info("Inside catch block of BLIMPL protection :::::::::" + e);
		}
		return protectionBenifitDAO.saveProtectionBenifit(cpProtectionBenifitList);

	}

	@Override
	public TyProtectionBenifitDTO fetchRequestAndProtBenifit(ServiceRequestMasterDTO serviceRequestMasterDTO) {

		CpRequestMaster requestMaster = new CpRequestMaster();
		requestMaster.setServiceRequestNo(serviceRequestMasterDTO.getServiceRequestNo());
		requestMaster = cpRequestMasterDAO.fetchRequestMasters(requestMaster);
		List<CpRequestMaster> requestMasters = new ArrayList<CpRequestMaster>();
		requestMasters.add(requestMaster);
		logger.info("Service request No is :" + serviceRequestMasterDTO.getServiceRequestNo());
		logger.info("Master table size is " + requestMasters.size());
		List<CpProtectionBenifit> cpProtectionBenifit = new ArrayList<CpProtectionBenifit>();

		cpProtectionBenifit = protectionBenifitDAO
				.fetchAllProtectionBenifit(serviceRequestMasterDTO.getServiceRequestNo());
		logger.info("CpProtectionBenifit table size is " + cpProtectionBenifit.size());
		List<CpFatca> cpFatca = new ArrayList<CpFatca>();
		cpFatca = fatcaDAO.fetchFatcaDetails(serviceRequestMasterDTO.getServiceRequestNo());

		List<CpFatcaCountryDet> cpFatcaCountryDet = new ArrayList<CpFatcaCountryDet>();
		if (cpFatca.size() > 0) {
			cpFatcaCountryDet = fatcaCountryDAO.fetchFatcaCountryDetails(cpFatca.get(0).getSerialNo());
		}
		TyProtectionBenifitDTO tyProtectionBenifitDTO = new TyProtectionBenifitDTO();
		tyProtectionBenifitDTO.setCpRequestMasters(requestMasters);
		tyProtectionBenifitDTO.setCpProtectionBenifit(cpProtectionBenifit);
		tyProtectionBenifitDTO.setCpFatca(cpFatca);
		tyProtectionBenifitDTO.setCpFatcaCountryDet(cpFatcaCountryDet);

		return tyProtectionBenifitDTO;
	}

	public ProtectionBenifitDAO getProtectionBenifitDAO() {
		return protectionBenifitDAO;
	}

	public void setProtectionBenifitDAO(ProtectionBenifitDAO protectionBenifitDAO) {
		this.protectionBenifitDAO = protectionBenifitDAO;
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
