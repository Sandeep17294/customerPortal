package com.aetins.customerportal.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.dao.CpRequestMasterDAO;
import com.aetins.customerportal.web.dao.FatcaCountryDAO;
import com.aetins.customerportal.web.dao.FatcaDAO;
import com.aetins.customerportal.web.dao.ReinStatementBenefitDAO;
import com.aetins.customerportal.web.dao.ReinStatementDAO;
import com.aetins.customerportal.web.dao.impl.ReinStatementDAOImpl;
import com.aetins.customerportal.web.dto.ReinStatementsDTO;
import com.aetins.customerportal.web.dto.ServiceRequestMasterDTO;
import com.aetins.customerportal.web.entity.CpFatca;
import com.aetins.customerportal.web.entity.CpFatcaCountryDet;
import com.aetins.customerportal.web.entity.CpReinStatement;
import com.aetins.customerportal.web.entity.CpReinStatementBenefit;
import com.aetins.customerportal.web.entity.CpRequestMaster;
import com.aetins.customerportal.web.service.ReinStatementBL;
import com.aetins.customerportal.web.transaction.dto.TyReinstatementDTO;

@Service
public class ReinStatementBLImpl implements ReinStatementBL  {

	private static final Logger logger = Logger.getLogger(ReinStatementBLImpl.class);
	
	@Autowired
	CpRequestMasterDAO cpRequestMasterDAO;
	
	@Autowired
	ReinStatementDAO reinStatementDAO;
	
	@Autowired
	FatcaDAO fatcaDAO;
	
	@Autowired
    FatcaCountryDAO  fatcaCountryDAO;
	
	@Autowired
    ReinStatementBenefitDAO cpReinStatementBenefitDAO;
	
    int serialNoReinstatement;
   
	@Override
	public ReinStatementsDTO insertReinStatementDetails(ReinStatementsDTO reinStatementsDTO) {
		
		logger.info("Inside Save insertReinStatementDetails()");
		CpReinStatement cpReinStatement = new CpReinStatement();
		CpRequestMaster request = new CpRequestMaster();
		
			request.setServiceRequestNo(reinStatementsDTO.getNserviceRequestNo().getServiceRequestNo());
			cpReinStatement.setServiceRequestNo(request);
			cpReinStatement.setSerialNo(reinStatementsDTO.getSerialNo());
			cpReinStatement.setPolicyNo(reinStatementsDTO.getPolicyNo());
			cpReinStatement.setReinStatementFlag(reinStatementsDTO.getReinStatementFlag());
			cpReinStatement.setProductName(reinStatementsDTO.getProductName());
			cpReinStatement.setOutstandingAmount(reinStatementsDTO.getOutstandingAmount());
			cpReinStatement.setIssueDate(reinStatementsDTO.getIssueDate());
			cpReinStatement.setLapseDate(reinStatementsDTO.getLapseDate());
			cpReinStatement.setPrumDate(reinStatementsDTO.getPrumDate());
			cpReinStatement.setFirstCover(reinStatementsDTO.getFirstCover());
			cpReinStatement.setFirstCoverDob(reinStatementsDTO.getFirstCoverDob());
			cpReinStatement.setSecondCover(reinStatementsDTO.getSecondCover());
			cpReinStatement.setSecondCoverDob(reinStatementsDTO.getSecondCoverDob());
			cpReinStatement.setPlanCurr(reinStatementsDTO.getPlanCurr());
			cpReinStatement.setPlanTerm(reinStatementsDTO.getPlanTerm());
			cpReinStatement.setContribTerm(reinStatementsDTO.getContribTerm());
			cpReinStatement.setBenifitType(reinStatementsDTO.getBenifitType());
			cpReinStatement.setFirstCoverbenifitAmt(reinStatementsDTO.getFirstCoverbenifitAmt());
			cpReinStatement.setFirstCoverbenifitTerm(reinStatementsDTO.getFirstCoverbenifitTerm());
			cpReinStatement.setSecondCoverbenifitAmt(reinStatementsDTO.getSecondCoverbenifitAmt());
			cpReinStatement.setSecondCoverbenifitTerm(reinStatementsDTO.getSecondCoverbenifitTerm());
			cpReinStatement.setRegulatContrib(reinStatementsDTO.getRegulatContrib());
			cpReinStatement.setContribFreq(reinStatementsDTO.getContribFreq());
			cpReinStatement.setFirstCoverBankName(reinStatementsDTO.getFirstCoverBankName());
			cpReinStatement.setFirstCoverIBAN(reinStatementsDTO.getFirstCoverIBAN());
			cpReinStatement.setFirstCoverSourceFund(reinStatementsDTO.getFirstCoverSourceFund());
			cpReinStatement.setSecondCoverBankName(reinStatementsDTO.getSecondCoverBankName());
			cpReinStatement.setSecondCoverIBAN(reinStatementsDTO.getSecondCoverIBAN());
			cpReinStatement.setSecondCoverSourceFund(reinStatementsDTO.getSecondCoverSourceFund());
			cpReinStatement.setPlanHoldBankName(reinStatementsDTO.getPlanHoldBankName());
			cpReinStatement.setPlanHoldIBAN(reinStatementsDTO.getPlanHoldIBAN());
			cpReinStatement.setPlanHoldSourceFund(reinStatementsDTO.getPlanHoldSourceFund());
			cpReinStatement.setFirstCoverIncomeLast(reinStatementsDTO.getFirstCoverIncomeLast());
			cpReinStatement.setFirstCoverIncomeSecondLast(reinStatementsDTO.getFirstCoverIncomeSecondLast());
			cpReinStatement.setFirstCoveredIncomeThirdLast(reinStatementsDTO.getFirstCoveredIncomeThirdLast());
			cpReinStatement.setSecondCoverIncomeLast(reinStatementsDTO.getSecondCoverIncomeLast());
			cpReinStatement.setSecondCoverIncomeSecondLast(reinStatementsDTO.getSecondCoverIncomeSecondLast());
			cpReinStatement.setSecondCoveredIncomeThirdLast(reinStatementsDTO.getSecondCoveredIncomeThirdLast());
			cpReinStatement.setPlanHoldIncomeLast(reinStatementsDTO.getPlanHoldIncomeLast());
			cpReinStatement.setPlanHoldIncomeSecondLast(reinStatementsDTO.getPlanHoldIncomeSecondLast());
			cpReinStatement.setPlanHoldIncomeThirdLast(reinStatementsDTO.getPlanHoldIncomeThirdLast());
			cpReinStatement.setDueFirstContrib(reinStatementsDTO.getDueFirstContrib());
			cpReinStatement.setDueFinalContrib(reinStatementsDTO.getDueFinalContrib());
			cpReinStatement.setContribFreq(reinStatementsDTO.getContribFreq());
			cpReinStatement.setRegulatContrib(reinStatementsDTO.getRegulatContrib());

			cpReinStatement.setFirstCoverCash(reinStatementsDTO.getFirstCoverCash());
			cpReinStatement.setFirstCoverShares(reinStatementsDTO.getFirstCoverShares());
			cpReinStatement.setFirstCoverRealEstate(reinStatementsDTO.getFirstCoverRealEstate());
			cpReinStatement.setFirstCoverAssetsOthers(reinStatementsDTO.getFirstCoverAssetsOthers());
			cpReinStatement.setFirstCoverLoan(reinStatementsDTO.getFirstCoverLoan());
			cpReinStatement.setFirstCoverPayable(reinStatementsDTO.getFirstCoverPayable());
			cpReinStatement.setFirstCoverMortgage(reinStatementsDTO.getFirstCoverMortgage());
			cpReinStatement.setFirstCoverOther(reinStatementsDTO.getFirstCoverOther());

			cpReinStatement.setSecondCoverCash(reinStatementsDTO.getSecondCoverCash());
			cpReinStatement.setSecondCoverShares(reinStatementsDTO.getSecondCoverShares());
			cpReinStatement.setSecondCoverRealEstate(reinStatementsDTO.getSecondCoverRealEstate());
			cpReinStatement.setSecondCoverAssetsOthers(reinStatementsDTO.getSecondCoverAssetsOthers());
			cpReinStatement.setSecondCoverLoan(reinStatementsDTO.getSecondCoverLoan());
			cpReinStatement.setSecondCoverPayable(reinStatementsDTO.getSecondCoverPayable());
			cpReinStatement.setSecondCoverMortgage(reinStatementsDTO.getSecondCoverMortgage());
			cpReinStatement.setSecondCoverOther(reinStatementsDTO.getSecondCoverOther());

			cpReinStatement.setPlanHoldCash(reinStatementsDTO.getPlanHoldCash());
			cpReinStatement.setPlanHoldShares(reinStatementsDTO.getPlanHoldShares());
			cpReinStatement.setPlanHoldRealEstate(reinStatementsDTO.getPlanHoldRealEstate());
			cpReinStatement.setPlanHoldAssetsOthers(reinStatementsDTO.getPlanHoldAssetsOthers());
			cpReinStatement.setPlanHoldLoan(reinStatementsDTO.getPlanHoldLoan());
			cpReinStatement.setPlanHoldPayable(reinStatementsDTO.getPlanHoldPayable());
			cpReinStatement.setPlanHoldMortgage(reinStatementsDTO.getPlanHoldMortgage());
			cpReinStatement.setPlanHoldOther(reinStatementsDTO.getPlanHoldOther());
			cpReinStatement.setFirstCoverWeight(reinStatementsDTO.getFirstCoverWeight());
			cpReinStatement.setFirstCoverHeight(reinStatementsDTO.getFirstCoverHeight());
			cpReinStatement.setSecondCoverWeight(reinStatementsDTO.getSecondCoverWeight());
			cpReinStatement.setSecondCoverHeight(reinStatementsDTO.getSecondCoverHeight());

			cpReinStatement.setQuesOneFirst(reinStatementsDTO.getQuesOneFirst());
			cpReinStatement.setQuesTwoAFirst(reinStatementsDTO.getQuesTwoAFirst());
			cpReinStatement.setQuesTwoBFirst(reinStatementsDTO.getQuesTwoBFirst());
			cpReinStatement.setQuesTwoCFirst(reinStatementsDTO.getQuesTwoCFirst());
			cpReinStatement.setQuesTwoDFirst(reinStatementsDTO.getQuesTwoDFirst());
			cpReinStatement.setQuesTwoEFirst(reinStatementsDTO.getQuesTwoEFirst());
			cpReinStatement.setQuesTwoFFirst(reinStatementsDTO.getQuesTwoFFirst());
			cpReinStatement.setQuesTwoGFirst(reinStatementsDTO.getQuesTwoGFirst());
			cpReinStatement.setQuesTwoHFirst(reinStatementsDTO.getQuesTwoHFirst());
			cpReinStatement.setQuesThreeFirst(reinStatementsDTO.getQuesThreeFirst());
			cpReinStatement.setQuesFourFirst(reinStatementsDTO.getQuesFourFirst());
			cpReinStatement.setQuesFiveFirst(reinStatementsDTO.getQuesFiveFirst());
			cpReinStatement.setQuesSixFirst(reinStatementsDTO.getQuesSixFirst());
			cpReinStatement.setQuesSevenFirst(reinStatementsDTO.getQuesSevenFirst());
			cpReinStatement.setQuesEightFirst(reinStatementsDTO.getQuesEightFirst());
			cpReinStatement.setQuesNineFirst(reinStatementsDTO.getQuesNineFirst());
			cpReinStatement.setQuesTenFirst(reinStatementsDTO.getQuesTenFirst());
			cpReinStatement.setQuesElevenFirst(reinStatementsDTO.getQuesElevenFirst());
			cpReinStatement.setQuesElevenAFirst(reinStatementsDTO.getQuesElevenAFirst());

			cpReinStatement.setQuesOneSecond(reinStatementsDTO.getQuesOneSecond());
			cpReinStatement.setQuesTwoASecond(reinStatementsDTO.getQuesTwoASecond());
			cpReinStatement.setQuesTwoBSecond(reinStatementsDTO.getQuesTwoBSecond());
			cpReinStatement.setQuesTwoCSecond(reinStatementsDTO.getQuesTwoCSecond());
			cpReinStatement.setQuesTwoDSecond(reinStatementsDTO.getQuesTwoDSecond());
			cpReinStatement.setQuesTwoESecond(reinStatementsDTO.getQuesTwoESecond());
			cpReinStatement.setQuesTwoFSecond(reinStatementsDTO.getQuesTwoFSecond());
			cpReinStatement.setQuesTwoGSecond(reinStatementsDTO.getQuesTwoGSecond());
			cpReinStatement.setQuesTwoHSecond(reinStatementsDTO.getQuesTwoHSecond());
			cpReinStatement.setQuesThreeSecond(reinStatementsDTO.getQuesThreeSecond());
			cpReinStatement.setQuesFourSecond(reinStatementsDTO.getQuesFourSecond());
			cpReinStatement.setQuesFiveSecond(reinStatementsDTO.getQuesFiveSecond());
			cpReinStatement.setQuesSixSecond(reinStatementsDTO.getQuesSixSecond());
			cpReinStatement.setQuesSevenSecond(reinStatementsDTO.getQuesSevenSecond());
			cpReinStatement.setQuesEightSecond(reinStatementsDTO.getQuesEightSecond());
			cpReinStatement.setQuesNineSecond(reinStatementsDTO.getQuesNineSecond());
			cpReinStatement.setQuesTenSecond(reinStatementsDTO.getQuesTenSecond());
			cpReinStatement.setQuesElevenSecond(reinStatementsDTO.getQuesElevenSecond());
			cpReinStatement.setQuesElevenASecond(reinStatementsDTO.getQuesElevenASecond());
			cpReinStatement.setFirstCoverMedDetails(reinStatementsDTO.getFirstCoverMedDetails());
			cpReinStatement.setSecondCoverMedDetails(reinStatementsDTO.getSecondCoverMedDetails());

			cpReinStatement = reinStatementDAO.insertReinStatementDetails(cpReinStatement);
			
			reinStatementsDTO.setSeqNo(cpReinStatement.getSerialNo());
			
			
			serialNoReinstatement = reinStatementsDTO.getSeqNo();
			logger.info("SerialNo for Reinstatemet is "+serialNoReinstatement);
			//getSession().setAttribute("REINSERIALNO", serialNoReinstatement);
			return reinStatementsDTO;

	}
	
	
//	@Override
//	public void boolean(List<ReinStatementsDTO> reinStatementsbeneDTO) {
//		List<CpReinStatementBenefit> cpReinStatementBenefit = new ArrayList<CpReinStatementBenefit>();
//	
//		
//		for(ReinStatementsDTO dto :reinStatementsbeneDTO){
//			
//			CpReinStatementBenefit  det = new CpReinStatementBenefit();
//			det.setSerial(dto.getSerialNo());	
//			
//			CpReinStatement  cpReinStatement = new CpReinStatement();
//			cpReinStatement.setSerialNo(serialNoReinstatement);
//			
//	     	det.setSerialNo(cpReinStatement);
//	     	det.setBenifitType(dto.getBenifitType());
//	     	det.setFirstCoverbenifitAmt(dto.getFirstCoverbenifitAmt());
//	     	det.setFirstCoverbenifitTerm(dto.getFirstCoverbenifitTerm());
//	     	det.setSecondCoverbenifitAmt(dto.getSecondCoverbenifitAmt());
//	     	det.setSecondCoverbenifitTerm(dto.getSecondCoverbenifitTerm());
//	     	cpReinStatementBenefit.add(det);
//		}
//		cpReinStatementBenefitDAO.insertbenefitDetails(cpReinStatementBenefit);
//	}
	

	@Override
	public TyReinstatementDTO fetchRequestAndReinstatements(ServiceRequestMasterDTO serviceRequestMasterDTO) {
		
		logger.info("::: Entering inside fetchRequestAndReinstatements() Method ::::");
		CpRequestMaster requestMaster = new CpRequestMaster();
		requestMaster.setServiceRequestNo(serviceRequestMasterDTO.getServiceRequestNo());
		requestMaster = cpRequestMasterDAO.fetchRequestMasters(requestMaster);
		List<CpRequestMaster> requestMasters = new ArrayList<CpRequestMaster>();
		requestMasters.add(requestMaster);
        
		logger.info("::: Size of RequestMaster list for Reinstatement is::::" +requestMasters.size());
		List<CpReinStatement> cpReinStatement = new ArrayList<CpReinStatement>();

		cpReinStatement = reinStatementDAO.fetchAllReinstatement(serviceRequestMasterDTO.getServiceRequestNo());
		logger.info("::: Size of Reinstatement list for Reinstatement is::::" +cpReinStatement.size());
		
		List<CpReinStatementBenefit> cpReinStatementBenefit = new ArrayList<CpReinStatementBenefit>();

		cpReinStatementBenefit = cpReinStatementBenefitDAO.fetchReinstatementBenefit(cpReinStatement.get(0).getSerialNo());
		logger.info("::: Size of ReinstatementBenefit list for ReinstatementBenefit is::::" +cpReinStatementBenefit.size());
		
		List<CpFatca> cpFatca = new ArrayList<CpFatca>();
		cpFatca = fatcaDAO.fetchFatcaDetails(serviceRequestMasterDTO.getServiceRequestNo());
		
		List<CpFatcaCountryDet> cpFatcaCountryDet = new ArrayList<CpFatcaCountryDet>();
		if (cpFatca.size() > 0) {
			cpFatcaCountryDet = fatcaCountryDAO.fetchFatcaCountryDetails(cpFatca.get(0).getSerialNo());
		}
		TyReinstatementDTO tyReinstatementDTO = new TyReinstatementDTO();
		tyReinstatementDTO.setCpRequestMasters(requestMasters);
		tyReinstatementDTO.setCpReinStatement(cpReinStatement);
		tyReinstatementDTO.setCpReinStatementBenefit(cpReinStatementBenefit);
		tyReinstatementDTO.setCpFatca(cpFatca);
		tyReinstatementDTO.setCpFatcaCountryDet(cpFatcaCountryDet);
		
		logger.info("::: Exit from  fetchRequestAndReinstatements() Method ::::");
		return tyReinstatementDTO;
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


	public ReinStatementBenefitDAO getCpReinStatementBenefitDAO() {
		return cpReinStatementBenefitDAO;
	}



	@Override
	public boolean saveReinstatmentBenefit(List<ReinStatementsDTO> cpReinStatementBenefitDTO) {
		boolean status;
		List<CpReinStatementBenefit> cpReinStatementBenefit = new ArrayList<CpReinStatementBenefit>();	
	      CpReinStatement  cpReinStatement = new CpReinStatement();
	       for(ReinStatementsDTO dto :cpReinStatementBenefitDTO){
				CpReinStatementBenefit  det = new CpReinStatementBenefit();
				det.setSerial(dto.getSerialNo());	
				cpReinStatement.setSerialNo(serialNoReinstatement);
		     	det.setSerialNo(cpReinStatement);
		     	det.setBenifitType(dto.getBenifitType());
		     	det.setFirstCoverbenifitAmt(dto.getFirstCoverbenifitAmt());
		     	det.setFirstCoverbenifitTerm(dto.getFirstCoverbenifitTerm());
		     	det.setSecondCoverbenifitAmt(dto.getSecondCoverbenifitAmt());
		     	det.setSecondCoverbenifitTerm(dto.getSecondCoverbenifitTerm());
		     	cpReinStatementBenefit.add(det);
			}
	       status=cpReinStatementBenefitDAO.insertreinstatement(cpReinStatementBenefit);
		return status;
	}

	
}
