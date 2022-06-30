package com.aetins.customerportal.web.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.dao.CpRequestMasterDAO;
import com.aetins.customerportal.web.dao.CpTermAndConditionDAO;
import com.aetins.customerportal.web.dao.CustomerLoginDAO;
import com.aetins.customerportal.web.dao.FatcaCountryDAO;
import com.aetins.customerportal.web.dao.FatcaDAO;
import com.aetins.customerportal.web.dao.RedirectionDAO;
import com.aetins.customerportal.web.dao.impl.CpRequestMasterDAOImpl;
import com.aetins.customerportal.web.dao.impl.CpTermAndConditionDAOImpl;
import com.aetins.customerportal.web.dao.impl.CustomerLoginDAOImpl;
import com.aetins.customerportal.web.dao.impl.FatcaCountryDAOImpl;
import com.aetins.customerportal.web.dao.impl.FatcaDAOImpl;
import com.aetins.customerportal.web.dao.impl.RedirectionDAOImpl;
import com.aetins.customerportal.web.dto.CpTermAndConditionDTO;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.dto.RedirectionDTO;
import com.aetins.customerportal.web.dto.ServiceRequestMasterDTO;
import com.aetins.customerportal.web.dto.StrategyDTO;
import com.aetins.customerportal.web.dto.UserDTO;
import com.aetins.customerportal.web.entity.CpFatca;
import com.aetins.customerportal.web.entity.CpFatcaCountryDet;
import com.aetins.customerportal.web.entity.CpRedirectionSwitching;
import com.aetins.customerportal.web.entity.CpRequestMaster;
import com.aetins.customerportal.web.entity.CpTermAndCondition;
import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.service.RedirectionBL;
import com.aetins.customerportal.web.transaction.dto.TyRedirectionSwitchingDTO;

@Service
public class RedirectionBLImpl implements RedirectionBL {

	@Autowired
	RedirectionDAO redirectionDAO;

	@Autowired
	CustomerLoginDAO customerLoginDao;

	@Autowired
	CpTermAndConditionDAO termDAO;

	@Autowired
	CpRequestMasterDAO requestMasterDAO;

	@Autowired
	FatcaDAO fatcaDAO;

	@Autowired
	FatcaCountryDAO fatcaCountryDAO;

	@Override
	public boolean updateRedirection(List<RedirectionDTO> redirectionDTO) {
		// TODO Auto-generated method stub
		List<CpRedirectionSwitching> fundDetails = new ArrayList<CpRedirectionSwitching>();
		for (RedirectionDTO dtos : redirectionDTO) {
			CpRedirectionSwitching redirection = new CpRedirectionSwitching();
			redirection.setSerialNo(dtos.getSerialNo());
			CpRequestMaster requestMaster = new CpRequestMaster();
			requestMaster.setServiceRequestNo(dtos.getServiceRequestNo().getServiceRequestNo());
			redirection.setServiceRequestNo(requestMaster);
			redirection.setCriteria(dtos.getCriteria());
			redirection.setValue(dtos.getValue());
			redirection.setMode(dtos.getMode());
			redirection.setFundName(dtos.getFundName());
			redirection.setFundCurrency(dtos.getFundCurrency());
			redirection.setFundValue(dtos.getFundValue());
			redirection.setFundCode(dtos.getFundCode());
			redirection.setUnits(dtos.getUnits());
			redirection.setUnitPrice(dtos.getUnitPrice());
			redirection.setFundPlanCurr(dtos.getFundPlanCurr());
			redirection.setInvestementsourcecode(dtos.getInvestmenetsource());
			fundDetails.add(redirection);
		}
		return redirectionDAO.saveFundDetails(fundDetails);
	}

	/* Added By Arul */
	public boolean updateRedirectionStrtegy(List<StrategyDTO> buyList) {
		// TODO Auto-generated method stub
		List<CpRedirectionSwitching> fundDetails = new ArrayList<CpRedirectionSwitching>();
		for (StrategyDTO strategyDTO : buyList) {
			CpRedirectionSwitching redirection = new CpRedirectionSwitching();
			redirection.setSerialNo(strategyDTO.getSerialNo());
			CpRequestMaster requestMaster = new CpRequestMaster();
			requestMaster.setServiceRequestNo(strategyDTO.getServiceRequestNo().getServiceRequestNo());
			redirection.setServiceRequestNo(requestMaster);
			redirection.setCriteria(strategyDTO.getCriteriaValue());
			redirection.setFundName(strategyDTO.getStrategyName());
			redirection.setFundCurrency(strategyDTO.getFundCurrency());
			redirection.setFundCode(strategyDTO.getStrategyCode());
			redirection.setMode(strategyDTO.getMode());
			fundDetails.add(redirection);
		}
		return redirectionDAO.saveFundDetails(fundDetails);
	}

	public TyRedirectionSwitchingDTO fetchRequestAndRedirections(ServiceRequestMasterDTO serviceRequestMasterDTO) {
		CpRequestMaster requestMaster = new CpRequestMaster();

		requestMaster.setServiceRequestNo(serviceRequestMasterDTO.getServiceRequestNo());
		requestMaster = requestMasterDAO.fetchRequestMasters(requestMaster);
		List<CpRequestMaster> requestMasters = new ArrayList();
		requestMasters.add(requestMaster);

		List<CpRedirectionSwitching> cpRedirectionSwitchings = new ArrayList();
		cpRedirectionSwitchings = redirectionDAO
				.fetchAllRedirectandSwitchings(serviceRequestMasterDTO.getServiceRequestNo());

		List<CpFatca> cpFatca = new ArrayList();
		cpFatca = fatcaDAO.fetchFatcaDetails(serviceRequestMasterDTO.getServiceRequestNo());

		List<CpFatcaCountryDet> cpFatcaCountryDet = new ArrayList();
		if (cpFatca.size() > 0) {
			cpFatcaCountryDet = fatcaCountryDAO.fetchFatcaCountryDetails(((CpFatca) cpFatca.get(0)).getSerialNo());
		}
		TyRedirectionSwitchingDTO tyRedirectionSwitchingDTO = new TyRedirectionSwitchingDTO();
		tyRedirectionSwitchingDTO.setCpRequestMasters(requestMasters);
		tyRedirectionSwitchingDTO.setCpRedirectionSwitchings(cpRedirectionSwitchings);
		tyRedirectionSwitchingDTO.setCpFatca(cpFatca);
		tyRedirectionSwitchingDTO.setCpFatcaCountryDet(cpFatcaCountryDet);

		return tyRedirectionSwitchingDTO;
	}

	@Override
	public List<CpUserInfoDTO> fetchUserDetails(CpUserInfoDTO verifyUserLogin) {

		List<CpUserInfoDTO> cpuserInfoDtoList = new ArrayList<CpUserInfoDTO>();
		CpUserInfo cpUserInfo = new CpUserInfo();
		cpUserInfo.setVuserName(verifyUserLogin.getVuserName());
		CpUserInfoDTO cpUserInfoDTO = new CpUserInfoDTO();
		List<CpUserInfo> cpUserList = new ArrayList<CpUserInfo>();
		cpUserList = customerLoginDao.fetchUserDetails(cpUserInfo);
		Iterator<CpUserInfo> it = cpUserList.iterator();
		while (it.hasNext()) {
			CpUserInfo cpuserInfo = it.next();
			cpUserInfoDTO.setNcustRefNo(cpuserInfo.getNcustRefNo());
			cpUserInfoDTO.setDdob(cpuserInfo.getDdob());
			cpUserInfoDTO.setNid(cpuserInfo.getNid());
			cpUserInfoDTO.setVactive(cpuserInfo.getVactive());
			cpUserInfoDTO.setVactiveLogin(cpuserInfo.getVactiveLogin());
			cpUserInfoDTO.setVanswer1(cpuserInfo.getVanswer1());
			cpUserInfoDTO.setVanswer2(cpuserInfo.getVanswer2());
			cpUserInfoDTO.setVcontactNo(cpuserInfo.getVcontactNo());
			cpUserInfoDTO.setVemail(cpuserInfo.getVemail());
			cpUserInfoDTO.setVgroup(cpuserInfo.getVgroup());
			/* cpuserInfoDTO.setVimage(cpuserInfoDTO.getVimage()); */
			cpUserInfoDTO.setVlastupdDate(cpuserInfo.getVlastupdDate());
			cpUserInfoDTO.setVlastupdUser(cpuserInfo.getVlastupdUser());
			cpUserInfoDTO.setVlocked(cpuserInfo.getVlocked());
			cpUserInfoDTO.setVloginSession(cpuserInfo.getVloginSession());
			cpUserInfoDTO.setVpassword(cpuserInfo.getVpassword());
			cpUserInfoDTO.setVpreflang(cpuserInfo.getVpreflang());
			cpUserInfoDTO.setVprefName(cpuserInfo.getVprefName());
			cpUserInfoDTO.setVpwMustChange(cpuserInfo.getVpwMustChange());
			cpUserInfoDTO.setVquestion1(cpuserInfo.getVquestion1());
			cpUserInfoDTO.setVquestion2(cpuserInfo.getVquestion2());
			cpUserInfoDTO.setVtitle(cpuserInfo.getVtitle());
			cpUserInfoDTO.setVuserActivationDate(cpuserInfo.getVuserActivationDate());
			cpUserInfoDTO.setVuserName(cpuserInfo.getVuserName());

			cpuserInfoDtoList.add(cpUserInfoDTO);

		}

		return cpuserInfoDtoList;
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
			userAllLists.add(cpUserInfoDTO);
		}
		return userAllLists;
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

	public CustomerLoginDAO getCustomerLoginDao() {
		return customerLoginDao;
	}

	public void setCustomerLoginDao(CustomerLoginDAO customerLoginDao) {
		this.customerLoginDao = customerLoginDao;
	}

	public CpTermAndConditionDAO getTermDAO() {
		return termDAO;
	}

	public void setTermDAO(CpTermAndConditionDAO termDAO) {
		this.termDAO = termDAO;
	}

	public RedirectionDAO getRedirectionDAO() {
		return redirectionDAO;
	}

	public CpRequestMasterDAO getRequestMasterDAO() {
		return requestMasterDAO;
	}

	public void setRequestMasterDAO(CpRequestMasterDAO requestMasterDAO) {
		this.requestMasterDAO = requestMasterDAO;
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

	public void setRedirectionDAO(RedirectionDAOImpl redirectionDAO) {
		this.redirectionDAO = redirectionDAO;
	}
	public static void main(String args[]) {
		RedirectionBLImpl n=new RedirectionBLImpl();
		//n.listTermAndCondition(serviceType, mendatory, required)
	}

}
