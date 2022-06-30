package com.aetins.customerportal.web.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.dao.SessionSummaryDAO;
import com.aetins.customerportal.web.dao.impl.SessionSummaryDAOImpl;
import com.aetins.customerportal.web.dto.CpSessionSummaryDTO;
import com.aetins.customerportal.web.entity.CpSessionSummary;
import com.aetins.customerportal.web.service.CpSessionSummarybl;

@Service
public class CpSessionSummaryblImpl implements CpSessionSummarybl {

	@Autowired
	SessionSummaryDAO SessionSummaryDAO;

	@Override
	public List<CpSessionSummaryDTO> fetchsessionDetails(CpSessionSummaryDTO verifysessiondto) {

		List<CpSessionSummaryDTO> cpuserInfoDtoList = new ArrayList<CpSessionSummaryDTO>();
		CpSessionSummary cpsession = new CpSessionSummary();
		CpSessionSummaryDTO cpUserInfoDTO = new CpSessionSummaryDTO();
		List<CpSessionSummary> cpUserList = new ArrayList<CpSessionSummary>();
		cpsession.setvPhpSessionId(verifysessiondto.getvPhpSessionId());
		cpsession.setVuserName(verifysessiondto.getVuserName());
		cpUserList = SessionSummaryDAO.fetchCustomerDetails(cpsession);
		Iterator<CpSessionSummary> it = cpUserList.iterator();
		while (it.hasNext()) {
			CpSessionSummary cpuserInfo = it.next();
			cpUserInfoDTO.setdLastSessionTransaction(cpsession.getdLastSessionTransaction());
			cpUserInfoDTO.setDsessionStart(cpsession.getDsessionEnd());
			cpUserInfoDTO.setNsessionId(cpsession.getNsessionId());
			cpUserInfoDTO.setVclientIp(cpsession.getVclientIp());
			cpUserInfoDTO.setvPhpSessionId(cpsession.getvPhpSessionId());
			cpUserInfoDTO.setDsessionEnd(cpsession.getDsessionEnd());
			cpUserInfoDTO.setnActive(cpsession.getnActive());
			cpUserInfoDTO.setVuserName(cpsession.getVuserName());
			cpUserInfoDTO.setVlastUpdInftim(cpsession.getVlastUpdInftim());
			cpUserInfoDTO.setvLogOff(cpsession.getvLogOff());
			cpUserInfoDTO.setVlastUpdProg(cpsession.getVlastUpdProg());
			cpUserInfoDTO.setVlastUpdUser(cpsession.getVlastUpdUser());

			cpuserInfoDtoList.add(cpUserInfoDTO);

		}

		return cpuserInfoDtoList;
	}

	@Override
	public boolean updatelogindetails(CpSessionSummaryDTO cpUserInfoDTO) {
		CpSessionSummary cpUserInfo = new CpSessionSummary();
		cpUserInfo.setvPhpSessionId(cpUserInfoDTO.getvPhpSessionId());
		return SessionSummaryDAO.updatePassword(cpUserInfo);
	}

	
	public void setSessionSummaryDAO(SessionSummaryDAOImpl sessionSummaryDAO) {
		SessionSummaryDAO = sessionSummaryDAO;
	}

	@Override
	public boolean updateSessionDetails(CpSessionSummaryDTO cpSessionSummaryDTO) {
		CpSessionSummary cpSessionSummary = new CpSessionSummary();
		cpSessionSummary.setvPhpSessionId(cpSessionSummaryDTO.getvPhpSessionId());
		cpSessionSummary.setvLogOff(cpSessionSummaryDTO.getvLogOff());
		cpSessionSummary.setDsessionEnd(cpSessionSummaryDTO.getDsessionEnd());
		return SessionSummaryDAO.updateSessionSummary(cpSessionSummary);
	}

}
