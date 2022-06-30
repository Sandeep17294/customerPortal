package com.aetins.customerportal.web.service;

import java.util.List;
import java.util.Map;

import com.aetins.customerportal.web.dto.CpOtpSettingsDTO;
import com.aetins.customerportal.web.dto.ResetSecurityAnswerDTO;

public interface ResetSecurityAnswerBL {

	List<ResetSecurityAnswerDTO> fetchSecurityQuestion(String userName ,String questionStatus);

	public Map<String, String> listCpQuestionDetail();

	public boolean updateSecurityQuestion(List<ResetSecurityAnswerDTO> resetSecurityAnswerDTO);
	
	public List<CpOtpSettingsDTO> fetchOtpSettings(String serviceType);
	
}
