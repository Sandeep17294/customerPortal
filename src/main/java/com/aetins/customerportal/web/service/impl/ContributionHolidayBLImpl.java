package com.aetins.customerportal.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.dao.ContributionHolidayDAO;
import com.aetins.customerportal.web.dao.CpRequestMasterDAO;
import com.aetins.customerportal.web.dao.CpTermAndConditionDAO;
import com.aetins.customerportal.web.dao.FatcaCountryDAO;
import com.aetins.customerportal.web.dao.FatcaDAO;
import com.aetins.customerportal.web.dto.CpContributionHolidayDTO;
import com.aetins.customerportal.web.dto.CpTermAndConditionDTO;
import com.aetins.customerportal.web.dto.ServiceRequestMasterDTO;
import com.aetins.customerportal.web.entity.CpContributionHoliday;
import com.aetins.customerportal.web.entity.CpFatca;
import com.aetins.customerportal.web.entity.CpFatcaCountryDet;
import com.aetins.customerportal.web.entity.CpRequestMaster;
import com.aetins.customerportal.web.entity.CpTermAndCondition;
import com.aetins.customerportal.web.service.ContributionHolidaylBL;
import com.aetins.customerportal.web.transaction.dto.TyContributionHolidayDTO;


@Service
public class ContributionHolidayBLImpl implements ContributionHolidaylBL {
	
	private static final Logger logger = Logger.getLogger(ContributionHolidayBLImpl.class);

	@Autowired
	ContributionHolidayDAO contributionHolidayDAO;
	
	@Autowired
	CpRequestMasterDAO cpRequestMasterDAO;
	
	@Autowired
	FatcaDAO fatcaDAO;
	
	@Autowired
    FatcaCountryDAO fatcaCountryDAO;


	@Autowired
	CpTermAndConditionDAO termDAO;
    
	@Override
	public boolean insertHolidayDetails(List<CpContributionHolidayDTO> cpContributionHolidayDTO) {
		logger.info("Inside InsertHolidayDetails =========================="+cpContributionHolidayDTO.size());
		boolean status = false;
		CpContributionHoliday cpContributionHoliday = new CpContributionHoliday();
		CpRequestMaster request = new CpRequestMaster();
		for (int i = 0; i < cpContributionHolidayDTO.size(); i++) {
			request.setServiceRequestNo(cpContributionHolidayDTO.get(i).getnServicRequestNo().getServiceRequestNo());
			cpContributionHoliday.setnServicRequestNo(request);
			cpContributionHoliday.setvPolicyNo(cpContributionHolidayDTO.get(i).getvPolicyNo());
			cpContributionHoliday.setvProduct(cpContributionHolidayDTO.get(i).getvProduct());
			cpContributionHoliday.setdContributionDueDate(cpContributionHolidayDTO.get(i).getdContributionDueDate());
			cpContributionHoliday.setnOutstandingAmount(cpContributionHolidayDTO.get(i).getnOutstandingAmount());
			cpContributionHoliday.setnContribution(cpContributionHolidayDTO.get(i).getnContribution());
			cpContributionHoliday.setvPaymentFrequency(cpContributionHolidayDTO.get(i).getvPaymentFrequency());
			cpContributionHoliday.setdHolidayFrom(cpContributionHolidayDTO.get(i).getdHolidayFrom());
			cpContributionHoliday.setdHolidayTo(cpContributionHolidayDTO.get(i).getdHolidayTo());
			cpContributionHoliday.setDcommenceDate(cpContributionHolidayDTO.get(i).getDcommenceDate());
			cpContributionHoliday.setNpaidContribution(cpContributionHolidayDTO.get(i).getNpaidContribution());
			cpContributionHoliday.setNcontractYear(cpContributionHolidayDTO.get(i).getNcontractYear());
			cpContributionHoliday.setTotReceivedContr(cpContributionHolidayDTO.get(i).getTotReceivedContr());
			status=contributionHolidayDAO.insertHolidayDetails(cpContributionHoliday);
		}
		return status;
	}

	
	@Override
	public TyContributionHolidayDTO fetchRequestAndHoliday(ServiceRequestMasterDTO serviceRequestMasterDTO) {

		CpRequestMaster requestMaster = new CpRequestMaster();
		requestMaster.setServiceRequestNo(serviceRequestMasterDTO.getServiceRequestNo());
		requestMaster = cpRequestMasterDAO.fetchRequestMasters(requestMaster);
		
		List<CpRequestMaster> requestMasters = new ArrayList<CpRequestMaster>();
		requestMasters.add(requestMaster);

		List<CpContributionHoliday> cpContributionHoliday = new ArrayList<CpContributionHoliday>();

		cpContributionHoliday = contributionHolidayDAO
				.fetchAllContrHoliday(serviceRequestMasterDTO.getServiceRequestNo());

		List<CpFatca> cpFatca = new ArrayList<CpFatca>();
		cpFatca = fatcaDAO.fetchFatcaDetails(serviceRequestMasterDTO.getServiceRequestNo());
		
		List<CpFatcaCountryDet> cpFatcaCountryDet = new ArrayList<CpFatcaCountryDet>();
		if (cpFatca.size() > 0) {
			cpFatcaCountryDet = fatcaCountryDAO.fetchFatcaCountryDetails(cpFatca.get(0).getSerialNo());
		}
		TyContributionHolidayDTO tyContributionHolidayDTO = new TyContributionHolidayDTO();
		tyContributionHolidayDTO.setCpRequestMasters(requestMasters);
		tyContributionHolidayDTO.setCpContributionHoliday(cpContributionHoliday);
		tyContributionHolidayDTO.setCpFatca(cpFatca);
		tyContributionHolidayDTO.setCpFatcaCountryDet(cpFatcaCountryDet);
		
		logger.info(":::::::Request master list size for Contribution Holiday:::::" +tyContributionHolidayDTO.getCpRequestMasters().size()
				+":::::::Contribution holiday list size for Contribution Holiday:::::" +tyContributionHolidayDTO.getCpContributionHoliday().size()
				+":::::::fatca master table size for Contribution Holiday:::::" +tyContributionHolidayDTO.getCpFatca().size()
				+":::::::fatca country table size for Contribution Holiday:::::" +tyContributionHolidayDTO.getCpFatcaCountryDet().size());
		
		return tyContributionHolidayDTO;
	}
	
	@Override
	public List<CpTermAndConditionDTO> listTermAndCondition(String serviceType, String mendatory, String required) {

		List<CpTermAndCondition> eserviceList = new ArrayList<CpTermAndCondition>();
		eserviceList = termDAO.termAndCondition(serviceType, mendatory, required);
		List<CpTermAndConditionDTO> termsConditionLists = new ArrayList<CpTermAndConditionDTO>();
		for (CpTermAndCondition termConditionlist : eserviceList) {
			CpTermAndConditionDTO cpTermAndConditionDTO = new CpTermAndConditionDTO();
			cpTermAndConditionDTO.setSerialNo(termConditionlist.getNserialNo());
			cpTermAndConditionDTO.setOrder(termConditionlist.getNorder());
			cpTermAndConditionDTO.setPage_name(termConditionlist.getVpageName());
			cpTermAndConditionDTO.setConditionEng(termConditionlist.getVcondEng());
			cpTermAndConditionDTO.setMandString(termConditionlist.getVmend());
			cpTermAndConditionDTO.setConditionArb(termConditionlist.getVcondArb());
			cpTermAndConditionDTO.setRequiredString(termConditionlist.getVrequired());

			termsConditionLists.add(cpTermAndConditionDTO);
		}

		return termsConditionLists;

	}

	
	@Override
	public List<CpTermAndConditionDTO> listTermAndCondition1(String serviceType, String mendatory, String required) {

		List<CpTermAndCondition> eserviceList = new ArrayList<CpTermAndCondition>();
		eserviceList = termDAO.termAndCondition1(serviceType, mendatory, required);
		List<CpTermAndConditionDTO> termsConditionLists1 = new ArrayList<CpTermAndConditionDTO>();
		for (CpTermAndCondition termConditionlist : eserviceList) {
			CpTermAndConditionDTO cpTermAndConditionDTO = new CpTermAndConditionDTO();
			cpTermAndConditionDTO.setSerialNo(termConditionlist.getNserialNo());
			cpTermAndConditionDTO.setOrder(termConditionlist.getNorder());
			cpTermAndConditionDTO.setPage_name(termConditionlist.getVpageName());
			cpTermAndConditionDTO.setConditionEng(termConditionlist.getVcondEng());
			cpTermAndConditionDTO.setMandString(termConditionlist.getVmend());
			cpTermAndConditionDTO.setConditionArb(termConditionlist.getVcondArb());
			cpTermAndConditionDTO.setRequiredString(termConditionlist.getVrequired());

			termsConditionLists1.add(cpTermAndConditionDTO);
		}

		return termsConditionLists1;

	}
	

	public void setContributionHolidayDAO(ContributionHolidayDAO contributionHolidayDAO) {
		this.contributionHolidayDAO = contributionHolidayDAO;
	}

	public void setCpRequestMasterDAO(CpRequestMasterDAO cpRequestMasterDAO) {
		this.cpRequestMasterDAO = cpRequestMasterDAO;
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
	
	
	
	public static void main(String[] args) {
		
		ContributionHolidayBLImpl  impl = new ContributionHolidayBLImpl();
		List<CpTermAndConditionDTO> op = impl.listTermAndCondition("CONTRIBUTION HOLIDAY", "Y", "Y");
		System.out.println();
		
	}


	@Override
	public List getTransactionCount(String username, String fatcafalg) {
		List list = null;
		try {
			list=contributionHolidayDAO.getTransactionCount(username, fatcafalg);
		} catch (Exception e) {
			logger.error("Inside catch block of fatca 90 days validation fetch==============" + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}



	

}
