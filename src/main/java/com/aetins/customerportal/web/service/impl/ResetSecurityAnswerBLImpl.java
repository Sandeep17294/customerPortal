package com.aetins.customerportal.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.dao.CpQuestionsDetailDAO;
import com.aetins.customerportal.web.dao.OtpSetUpDAO;
import com.aetins.customerportal.web.dao.ResetSecurityAnswerDAO;
import com.aetins.customerportal.web.dto.CpOtpSettingsDTO;
import com.aetins.customerportal.web.dto.ResetSecurityAnswerDTO;
import com.aetins.customerportal.web.entity.CpOtpSettings;
import com.aetins.customerportal.web.entity.CpQuestionDetail;
import com.aetins.customerportal.web.entity.CpResetSecurityAnswer;
import com.aetins.customerportal.web.service.ResetSecurityAnswerBL;

@Service
public class ResetSecurityAnswerBLImpl implements ResetSecurityAnswerBL {

	@Autowired
	ResetSecurityAnswerDAO resetDAO;
	
	@Autowired
	CpQuestionsDetailDAO cpQuestionsDetailDAO;
	
	@Autowired
	OtpSetUpDAO OtpSetUpDAO;

	@Override
	public List<ResetSecurityAnswerDTO> fetchSecurityQuestion(String userName, String questionStatus) {
		// TODO Auto-generated method stub

		List<CpResetSecurityAnswer> userQuestionList = new ArrayList<CpResetSecurityAnswer>();
		List<ResetSecurityAnswerDTO> questionList = new ArrayList<ResetSecurityAnswerDTO>();

		userQuestionList = resetDAO.fetchUserSecurityQuestion(userName, questionStatus);

		for (CpResetSecurityAnswer cpResetSecurityAnswer : userQuestionList) {

			ResetSecurityAnswerDTO resetSecurityAnswerDTO = new ResetSecurityAnswerDTO();
			resetSecurityAnswerDTO.setId(cpResetSecurityAnswer.getId());
			resetSecurityAnswerDTO.setUserName(cpResetSecurityAnswer.getUserName());
			resetSecurityAnswerDTO.setCustRefNo(cpResetSecurityAnswer.getCustRefNo());
			resetSecurityAnswerDTO.setSecurityQues(cpResetSecurityAnswer.getSecurityQues());
			resetSecurityAnswerDTO.setSecurityAns(cpResetSecurityAnswer.getSecurityAns());
			// resetSecurityAnswerDTO.setQuesStatus(cpResetSecurityAnswer.getQuesStatus());//Fetching
			// time status not required
			resetSecurityAnswerDTO.setProcessDate(cpResetSecurityAnswer.getProcessDate());
			resetSecurityAnswerDTO.setRecentModifiedDate(cpResetSecurityAnswer.getRecentModifiedDate());
			resetSecurityAnswerDTO.setUserOtp(cpResetSecurityAnswer.getUserOtp());

			questionList.add(resetSecurityAnswerDTO);

		}
		return questionList;
	}

	public Map<String, String> listCpQuestionDetail() {

		Map<String, String> stringList = new LinkedHashMap<String, String>();
		for (CpQuestionDetail cpQuestionsDetail : cpQuestionsDetailDAO.findAllQuestions()) {
			stringList.put(cpQuestionsDetail.getvQuesEN(), cpQuestionsDetail.getvQuesEN());
		}
		return stringList;
	}

	@Override
	public boolean updateSecurityQuestion(List<ResetSecurityAnswerDTO> resetSecurityAnswerDTO) {
		List<CpResetSecurityAnswer> cpResetSecurityAnswer = new ArrayList();

		for (ResetSecurityAnswerDTO dto : resetSecurityAnswerDTO) {
			CpResetSecurityAnswer details = new CpResetSecurityAnswer();
			details.setUserName(dto.getUserName());
			details.setSecurityQues(dto.getSecurityQues());
			details.setSecurityAns(dto.getSecurityAns());
			details.setQuesStatus(dto.getQuesStatus());
			details.setCustRefNo(dto.getCustRefNo());
			details.setProcessDate(new Date());
			details.setRecentModifiedDate(new Date());
			details.setId(dto.getId());
			cpResetSecurityAnswer.add(details);
		}
		return resetDAO.updateSecurityQuestion(cpResetSecurityAnswer);
	}

	@Override
	public List<CpOtpSettingsDTO> fetchOtpSettings(String serviceType) {
		List<CpOtpSettings> otpRules = OtpSetUpDAO.listOtpSettings(serviceType);
		List<CpOtpSettingsDTO> settingsList = new ArrayList<CpOtpSettingsDTO>();
		
		for (CpOtpSettings cpOtpSettings : otpRules) {
			CpOtpSettingsDTO cpOtpSettingsDTOs = new CpOtpSettingsDTO();
			cpOtpSettingsDTOs.setvOtpFlagEmail(cpOtpSettings.getvOtpFlagEmail());
			cpOtpSettingsDTOs.setvOtpFlagMobile(cpOtpSettings.getvOtpFlagMobile());
			settingsList.add(cpOtpSettingsDTOs);
		}
		
		return settingsList;
	}

}
