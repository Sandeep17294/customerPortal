package com.aetins.customerportal.web.service.impl;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aetins.customerportal.web.dao.CpCustomerDetailDAO;
import com.aetins.customerportal.web.dao.CpQuestionsDetailDAO;
import com.aetins.customerportal.web.dao.CpSecurityImgMasterDAO;
import com.aetins.customerportal.web.dao.CpUserInfoDAO;
import com.aetins.customerportal.web.dto.ListItem;
import com.aetins.customerportal.web.dto.RegistrationCustomerDTO;
import com.aetins.customerportal.web.entity.CpCustomerDetail;
import com.aetins.customerportal.web.entity.CpQuestionDetail;
import com.aetins.customerportal.web.entity.CpSecurityImgMaster;
import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.service.CusRegistrationBL;
import com.aetins.customerportal.web.utils.DateTimeUtil;
import com.aetins.customerportal.web.utils.StringUtils;

@Service
public class CusRegistrationBLImpl implements CusRegistrationBL {
	
	@Autowired
	private CpUserInfoDAO cpUserInfoDAO;
	
	@Autowired
	private CpCustomerDetailDAO cpCustomerDetailDAO;
	
	@Autowired
	private CpSecurityImgMasterDAO cpSecurityImgMasterDAO;
	
	@Autowired
	private CpQuestionsDetailDAO cpQuestionsDetailDAO;

	

	@Transactional
	public void addUserInfo(RegistrationCustomerDTO registrationCustomerDTO) {
		CpUserInfo cpUserInfo = new CpUserInfo();
		cpUserInfo.setNcustRefNo(registrationCustomerDTO.getCustomerReferenceNumber().intValue());
		cpUserInfo.setVgroup(registrationCustomerDTO.getGroup());
		cpUserInfo.setVtitle(registrationCustomerDTO.getUserTitle());
		cpUserInfo.setVuserName(registrationCustomerDTO.getUserId());
		cpUserInfo.setVprefName(registrationCustomerDTO.getUserDisplayName());
		cpUserInfo.setDdob(DateTimeUtil.dateFormat(registrationCustomerDTO.getEnglishDOB()));
		cpUserInfo.setVpassword(registrationCustomerDTO.getUserPassword());
		cpUserInfo.setVpwMustChange(registrationCustomerDTO.getPasswordMustChange());
		cpUserInfo.setVemail(registrationCustomerDTO.getEmailID());
		cpUserInfo.setVquestion1(registrationCustomerDTO.getUserSecretQuestion1());
		cpUserInfo.setVanswer1(registrationCustomerDTO.getUserSecretQuestion1answer());
		cpUserInfo.setVquestion2(registrationCustomerDTO.getUserSecretQuestion2());
		cpUserInfo.setVanswer2(registrationCustomerDTO.getUserSecretQuestion2answer());
		cpUserInfo.setVpreflang(registrationCustomerDTO.getLanguageCode());
		cpUserInfo.setVcontactNo(registrationCustomerDTO.getMobileNo());
		cpUserInfo.setVactive(registrationCustomerDTO.getActive());
		cpUserInfo.setVlocked(registrationCustomerDTO.getLocked());
		cpUserInfo.setVuserActivationDate(registrationCustomerDTO.getUserActivationDate());
		cpUserInfo.setVactiveLogin(registrationCustomerDTO.getActiveLogin());
		cpUserInfo.setVloginSession(registrationCustomerDTO.getLoginSessionId());
		cpUserInfo.setVlastupdUser(registrationCustomerDTO.getLastUpdateUser());
		cpUserInfo.setVlastupdDate(registrationCustomerDTO.getLastUpdateDate());
		cpUserInfo.setnImageId(registrationCustomerDTO.getImageID());
		cpUserInfo.setvToken(registrationCustomerDTO.getToken());

		cpUserInfoDAO.saveUserInfo(cpUserInfo);
	}

	@Transactional
	public void addCustomerDetail(RegistrationCustomerDTO registrationCustomerDTO) {

		CpCustomerDetail cpCustomerDetail = new CpCustomerDetail();
		cpCustomerDetail.setdDob(DateTimeUtil.dateFormat(registrationCustomerDTO.getEnglishDOB()));
		cpCustomerDetail.setdDobHijri(DateTimeUtil.dateFormat(registrationCustomerDTO.getHijriDOB()));
		cpCustomerDetail.setNcustRefNo(registrationCustomerDTO.getCustomerReferenceNumber().intValue());

		String customer = "";
		if ((StringUtils.isNotBlank(registrationCustomerDTO.getFirstName()))
				&& (StringUtils.isNotBlank(registrationCustomerDTO.getLastName()))) {
			customer = registrationCustomerDTO.getFirstName() + " " + registrationCustomerDTO.getLastName();
		} else if ((StringUtils.isNotBlank(registrationCustomerDTO.getFirstName()))
				&& (!StringUtils.isNotBlank(registrationCustomerDTO.getLastName()))) {
			customer = registrationCustomerDTO.getFirstName();
		} else if ((!StringUtils.isNotBlank(registrationCustomerDTO.getFirstName()))
				&& (StringUtils.isNotBlank(registrationCustomerDTO.getLastName()))) {
			customer = registrationCustomerDTO.getLastName();
		} else {
			customer = "Customer";
		}
		cpCustomerDetail.setVcustName(customer);
		cpCustomerDetail.setVgroup(registrationCustomerDTO.getGroup());
		cpCustomerDetail.setVidNo(registrationCustomerDTO.getIdenNo());
		cpCustomerDetail.setVidType(registrationCustomerDTO.getIdenCode());
		cpCustomerDetail.setVemail(registrationCustomerDTO.getEmailId());
		cpCustomerDetail.setTitle(registrationCustomerDTO.getTitle());
		cpCustomerDetail.setGender(registrationCustomerDTO.getGender());
		cpCustomerDetailDAO.saveCustomerDetail(cpCustomerDetail);
	}

	@Transactional
	public List<CpSecurityImgMaster> listCpSecurityImgMaster() {
		return cpSecurityImgMasterDAO.listSecurityImgMaster();
	}
	//Commented for demo on 04/06/2019
	@Override
	@Transactional
	public List<ListItem> listCpSecurityQuestions() {
		List<ListItem> questionsList = new ArrayList();
		
		// Map<String, String> stringList = new LinkedHashMap<String, String>();
		for (CpQuestionDetail cpQuestionsDetailDAO :  cpQuestionsDetailDAO.findAllQuestions()) {
			ListItem eachQuestion = new ListItem();
			eachQuestion.setCode(cpQuestionsDetailDAO.getvQuesEN());
			eachQuestion.setDescription(cpQuestionsDetailDAO.getvQuesEN());
			questionsList.add(eachQuestion);
			// stringList.put(cpQuestionsDetailDAO.getvQuesEN(),
			// cpQuestionsDetailDAO.getvQuesEN());
		}

		return questionsList;
	}

	@Transactional
	public Map<String, String> listCpQuestionDetail() {
		Map<String, String> stringList = new LinkedHashMap();
		for (CpQuestionDetail cpQuestionsDetailDAO : this.cpQuestionsDetailDAO.findAllQuestions()) {
			stringList.put(cpQuestionsDetailDAO.getvQuesEN(), cpQuestionsDetailDAO.getvQuesEN());
		}
		return stringList;
	}

	@Transactional
	public boolean validateCpUserinfo(RegistrationCustomerDTO registrationCustomerDTO) {
		boolean status = false;
		CpUserInfo cpUserInfo = new CpUserInfo();
		cpUserInfo.setVuserName(registrationCustomerDTO.getUserId());
		CpUserInfo cpUserInfo2 = cpUserInfoDAO.getCpUserInfo(cpUserInfo.getVuserName());

		if (cpUserInfo2!=null)
			status = true;
		return status;
	}

	public void setCpUserInfoDAO(CpUserInfoDAO cpUserInfoDAO) {
		this.cpUserInfoDAO = cpUserInfoDAO;
	}

	public void setCpCustomerDetailDAO(CpCustomerDetailDAO cpCustomerDetailDAO) {
		this.cpCustomerDetailDAO = cpCustomerDetailDAO;
	}

	public void setCpSecurityImgMasterDAO(CpSecurityImgMasterDAO cpSecurityImgMasterDAO) {
		this.cpSecurityImgMasterDAO = cpSecurityImgMasterDAO;
	}

	public void setCpQuestionsDetailDAO(CpQuestionsDetailDAO cpQuestionsDetailDAO) {
		this.cpQuestionsDetailDAO = cpQuestionsDetailDAO;
	}
//Modified for demo on 04/06/2019
/*	@Override
	public List<ListItem> listCpSecurityQuestions() {
		// TODO Auto-generated method stub
		return null;
	}*/
}
