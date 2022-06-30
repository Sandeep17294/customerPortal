package com.aetins.customerportal.web.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.dao.ForgetPasswordDAO;
import com.aetins.customerportal.web.dao.PasswordRulesDAO;
import com.aetins.customerportal.web.dto.CpCustomerDetailDTO;
import com.aetins.customerportal.web.dto.PasswordRulesDTO;
import com.aetins.customerportal.web.dto.UserDTO;
import com.aetins.customerportal.web.entity.CpCustomerDetail;
import com.aetins.customerportal.web.entity.PasswordRules;
import com.aetins.customerportal.web.service.ForgetPasswordBL;
import com.aetins.customerportal.web.utils.BSLUtils;
import com.aetins.customerportal.web.utils.StringUtils;



@Service
public class ForgetPasswordBLImpl implements ForgetPasswordBL {

	private static final Logger logger = Logger.getLogger(ForgetPasswordBLImpl.class);
	
	@Resource
	private SessionFactory session;
	
	@Autowired
	ForgetPasswordDAO forgetPasswordDAO;
	
	@Autowired
	PasswordRulesDAO passwordRules;
	
	
	@Override
	public List<CpCustomerDetailDTO> secureDetailsForgetPassword(UserDTO verifyUserdto) {
		CpCustomerDetail cpCustomerDetails = new CpCustomerDetail();
		List<CpCustomerDetail> userLists = new ArrayList<CpCustomerDetail>();
		List<CpCustomerDetailDTO> userAllLists = new ArrayList<CpCustomerDetailDTO>();
		CpCustomerDetailDTO cpCustomerDetailDTO = new CpCustomerDetailDTO();

		cpCustomerDetails.setNcustRefNo(verifyUserdto.getCustRefNo());
		cpCustomerDetails.setVidType(verifyUserdto.getIdenType());
		cpCustomerDetails.setVidNo(verifyUserdto.getIdenNo());
		cpCustomerDetails.setdDob(verifyUserdto.getDob());
		cpCustomerDetails.setVcustName(verifyUserdto.getUserName());
		userLists = forgetPasswordDAO.secureDetailsForgetPassword(cpCustomerDetails);

		Iterator<CpCustomerDetail> it = userLists.iterator();
		while (it.hasNext()) {
			CpCustomerDetail cpCustomerDetail = it.next();
			
			if (BSLUtils.isNotNull(cpCustomerDetail.getNcustRefNo())) {
				cpCustomerDetailDTO.setNcustRefNo(cpCustomerDetail.getNcustRefNo());
			}
			
			if (BSLUtils.isNotNull(cpCustomerDetail.getVidType())) {
				cpCustomerDetailDTO.setVidType(cpCustomerDetail.getVidType());
			}
			
			if (BSLUtils.isNotNull(cpCustomerDetail.getVidNo())) {
				cpCustomerDetailDTO.setNidNo(cpCustomerDetail.getVidNo());
			}
			
			if (BSLUtils.isNotNull(cpCustomerDetail.getVemail())) {
				cpCustomerDetailDTO.setVemail(cpCustomerDetail.getVemail());
			}
			
			if (BSLUtils.isNotNull(cpCustomerDetail.getdDob())) {
				cpCustomerDetailDTO.setdDob(cpCustomerDetail.getdDob());
			}
			
			if (BSLUtils.isNotNull(cpCustomerDetail.getdDobHijri())) {
				cpCustomerDetailDTO.setdDobHijri(cpCustomerDetail.getdDobHijri());
			}
			if (BSLUtils.isNotNull(cpCustomerDetail.getTitle())) {
				cpCustomerDetailDTO.setTitle(cpCustomerDetail.getTitle());
			}
			
			if (BSLUtils.isNotNull(cpCustomerDetail.getVcustName())) {
				cpCustomerDetailDTO.setVcustName(cpCustomerDetail.getVcustName());
			}
			
			if (BSLUtils.isNotNull(cpCustomerDetail.getGender())) {
				cpCustomerDetailDTO.setGender(cpCustomerDetail.getGender());
			}
			logger.info("Gender is inside Forgot password :::::::::::::::::::::::::::::" +cpCustomerDetail.getGender());
			
			userAllLists.add(cpCustomerDetailDTO);
		}

		return userAllLists;

	}

	@Override
	public List<PasswordRulesDTO> listPasswordRules() {
		// TODO Auto-generated method stub
		List<PasswordRules> rulesList = passwordRules.listPasswordRules();
		List<PasswordRulesDTO> rulesAllLists = new ArrayList<PasswordRulesDTO>();
		for (PasswordRules passwordRules : rulesList) {
			PasswordRulesDTO passwordRulesDTO = new PasswordRulesDTO();
			passwordRulesDTO.setnMinAlphabetAllow(passwordRules.getnMinAlphabetAllow());
			passwordRulesDTO.setnMinLowerAllow(passwordRules.getnMinLowerAllow());
			passwordRulesDTO.setnMinNoAllow(passwordRules.getnMinNoAllow());
			passwordRulesDTO.setnMinSpecialAllow(passwordRules.getnMinSpecialAllow());
			passwordRulesDTO.setnMinUpperAllow(passwordRules.getnMinUpperAllow());
			passwordRulesDTO.setnPasswordLength(passwordRules.getnPasswordLength());
			passwordRulesDTO.setvSpecialCharAllow(passwordRules.getvSpecialCharAllow());
			passwordRulesDTO.setvPasswordSameasUser(passwordRules.getvPasswordSameasUser());
			rulesAllLists.add(passwordRulesDTO);
		}
		return rulesAllLists;
	}

}
