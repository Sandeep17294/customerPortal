package com.aetins.customerportal.web.service;

import java.util.List;

import com.aetins.customerportal.web.dto.CpOtpSettingsDTO;
import com.aetins.customerportal.web.dto.CpQuestionnaireDTO;
import com.aetins.customerportal.web.dto.CpQuestionsDetailDTO;
import com.aetins.customerportal.web.dto.CpServerSettingDTO;
import com.aetins.customerportal.web.dto.CpServiceTypeDTO;
import com.aetins.customerportal.web.dto.CpSessionSummaryDTO;
import com.aetins.customerportal.web.dto.CpTermAndConditionDTO;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.dto.PasswordRulesDTO;
import com.aetins.customerportal.web.dto.RegistrationTrackDTO;
import com.aetins.customerportal.web.dto.SecurityImagesDTO;
import com.aetins.customerportal.web.entity.CpListBoxAnswers;
import com.aetins.customerportal.web.entity.CpQuestionnaire;

public abstract interface AdminBL {
	
	public abstract List<CpUserInfoDTO> listAllUsers();

	public abstract List<CpSessionSummaryDTO> listSessionDetails();

	public abstract List<PasswordRulesDTO> listPasswordRules();

	public abstract List<CpOtpSettingsDTO> listOtpSettings(String serviceType);

	public abstract boolean updatePasswordRules(PasswordRulesDTO paramPasswordRulesDTO);

	public abstract List<CpQuestionsDetailDTO> listSecurityQuestion();

	public abstract List<SecurityImagesDTO> listSecurityImages();

	public abstract boolean updateSecurityImages1(SecurityImagesDTO paramSecurityImagesDTO);

	public abstract boolean updateSecurityImages(List<SecurityImagesDTO> paramList);

	public abstract boolean setDownTime(CpServerSettingDTO paramCpServerSettingDTO);

	public abstract boolean updateSecurityQuestion(List<CpQuestionsDetailDTO> paramList);

	public abstract boolean updateDownTime(CpServerSettingDTO paramCpServerSettingDTO);

	public abstract boolean updateUserStatus(CpUserInfoDTO paramCpUserInfoDTO);

	public abstract boolean deleteQuestion(CpQuestionsDetailDTO paramCpQuestionsDetailDTO);

	public abstract boolean updateUserLockStatus(CpUserInfoDTO paramCpUserInfoDTO);

	public abstract List<RegistrationTrackDTO> listRegistrationTrack();

	public abstract List<CpServiceTypeDTO> listcCEmail(String screenType);

	public abstract boolean saveCCEmail(List<CpServiceTypeDTO> ccEmail);

	public boolean deleteCCMail(CpServiceTypeDTO serviceTypedto);
	
	public boolean updateOtpSettings(CpOtpSettingsDTO cpOtpSettingdtos);
	
	public boolean updateTermsCondition(List<CpTermAndConditionDTO> cpTermAndConditionDTO);
	
	public boolean deleteTermsCondition(CpTermAndConditionDTO dto);
	
	public List<CpTermAndConditionDTO> listTermsCondition(String serviceType);
	
	public List<CpServerSettingDTO> fetchDownTime();
	
	public abstract boolean saveQuestionnaire(List<CpQuestionnaireDTO> dto);
	  
    List<CpQuestionnaire> getQuestionnaireList(String serviceType);
  
    List<CpListBoxAnswers> getAnswerList(Long serviceId);
  
    
    //malikfefal
    
    List<CpQuestionnaireDTO> listquestionare(String serviceType);
    
    
    
    public boolean saveAnswers(List<CpQuestionnaire> answers);

	public boolean saveTermsCondition(List<CpTermAndConditionDTO> termsAndCondition);
}