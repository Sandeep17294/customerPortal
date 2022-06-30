package com.aetins.customerportal.web.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.common.Constants;
import com.aetins.customerportal.web.common.LoginSession;
import com.aetins.customerportal.web.dao.AdminDAO;
import com.aetins.customerportal.web.dao.CpCCEmailDAO;
import com.aetins.customerportal.web.dao.CpListBoxAnswersDao;
import com.aetins.customerportal.web.dao.CpQuestionDetailDAO;
import com.aetins.customerportal.web.dao.CpQuestionnaireDao;
import com.aetins.customerportal.web.dao.CpRegistrationTrackDao;
import com.aetins.customerportal.web.dao.CpTermAndConditionDAO;
import com.aetins.customerportal.web.dao.CustomerLoginDAO;
import com.aetins.customerportal.web.dao.DownTimeDAO;
import com.aetins.customerportal.web.dao.OtpSetUpDAO;
import com.aetins.customerportal.web.dao.PasswordRulesDAO;
import com.aetins.customerportal.web.dao.SecurityImageMasterDAO;
import com.aetins.customerportal.web.dao.SessionSummaryDAO;
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
import com.aetins.customerportal.web.entity.CpEmailSetting;
import com.aetins.customerportal.web.entity.CpListBoxAnswers;
import com.aetins.customerportal.web.entity.CpOtpSettings;
import com.aetins.customerportal.web.entity.CpQuestionDetail;
import com.aetins.customerportal.web.entity.CpQuestionnaire;
import com.aetins.customerportal.web.entity.CpRegistrationTrack;
import com.aetins.customerportal.web.entity.CpSecurityImgMaster;
import com.aetins.customerportal.web.entity.CpServerstatusSetting;
import com.aetins.customerportal.web.entity.CpSessionSummary;
import com.aetins.customerportal.web.entity.CpTermAndCondition;
import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.entity.PasswordRules;
import com.aetins.customerportal.web.service.AdminBL;
import com.aetins.customerportal.web.utils.StringUtils;

@Service
public class AdminBLImpl implements AdminBL {

	private static final Logger logger = Logger.getLogger(AdminBLImpl.class);

	@Autowired
	private AdminDAO adminLoginDAO;//

	@Autowired
	private SessionSummaryDAO sessionSummary;//

	@Autowired
	PasswordRulesDAO passwordRules;//

	@Autowired
	CpQuestionDetailDAO questionDetails;//

	@Autowired
	CustomerLoginDAO customerLoginDao;//

	@Autowired
	DownTimeDAO downTimeDAO;//

	@Autowired
	SecurityImageMasterDAO securityImageDetails;//

	@Autowired
	CpRegistrationTrackDao cpRegistrationTrackDao;//

	@Autowired
	OtpSetUpDAO otpSetUpDAO;//

	@Autowired
	CpTermAndConditionDAO ftTermsCondition;//

	@Autowired
	CpCCEmailDAO cpCCEmailDAO;
	
	@Autowired
	CpQuestionnaireDao questionnaireDAO;
	
	@Autowired
	CpListBoxAnswersDao listBoxAnswers;
	
	@Autowired
    LoginSession loginSession;

	public AdminBLImpl() {
	}

	public List<CpUserInfoDTO> listAllUsers() {
		List<CpUserInfo> userLists = adminLoginDAO.listAllUsers();
		List<CpUserInfoDTO> userAllLists = new ArrayList();
		for (CpUserInfo cpUserInfo : userLists) {
			CpUserInfoDTO cpUserInfoDTO = new CpUserInfoDTO();
			cpUserInfoDTO.setNid(cpUserInfo.getNid());
			cpUserInfoDTO.setNcustRefNo(cpUserInfo.getNcustRefNo());
			cpUserInfoDTO.setVgroup(cpUserInfo.getVgroup());
			cpUserInfoDTO.setVtitle(cpUserInfo.getVtitle());
			cpUserInfoDTO.setVuserName(cpUserInfo.getVuserName());
			cpUserInfoDTO.setVprefName(cpUserInfo.getVprefName());
			cpUserInfoDTO.setDdob(cpUserInfo.getDdob());
			cpUserInfoDTO.setVpassword(cpUserInfo.getVpassword());
			cpUserInfoDTO.setVpwMustChange(cpUserInfo.getVpwMustChange());
			cpUserInfoDTO.setVemail(cpUserInfo.getVemail());
			cpUserInfoDTO.setVquestion1(cpUserInfo.getVquestion1());
			cpUserInfoDTO.setVanswer1(cpUserInfo.getVanswer1());
			cpUserInfoDTO.setVquestion2(cpUserInfo.getVquestion2());
			cpUserInfoDTO.setVanswer2(cpUserInfo.getVanswer2());
			cpUserInfoDTO.setVpreflang(cpUserInfo.getVpreflang());
			cpUserInfoDTO.setVcontactNo(cpUserInfo.getVcontactNo());
			cpUserInfoDTO.setVactive(cpUserInfo.getVactive());
			cpUserInfoDTO.setVlocked(cpUserInfo.getVlocked());

			SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
			if (cpUserInfo.getVuserActivationDate() != null) {
				String strDate = sm.format(cpUserInfo.getVuserActivationDate());
				System.out.println(strDate);
				if (StringUtils.isNotBlank(strDate)) {
					cpUserInfoDTO.setRegistrationDate(strDate);
				}
			}
			// cpUserInfoDTO.setVuserActivationDate(cpUserInfo.getVuserActivationDate());

			cpUserInfoDTO.setVactiveLogin(cpUserInfo.getVactiveLogin());
			cpUserInfoDTO.setVloginSession(cpUserInfo.getVloginSession());
			cpUserInfoDTO.setVlastupdUser(cpUserInfo.getVlastupdUser());
			cpUserInfoDTO.setVlastupdDate(cpUserInfo.getVlastupdDate());
			userAllLists.add(cpUserInfoDTO);

		}
		logger.info("Size of USerInfo list:::::" + userAllLists.size());
		return userAllLists;
	}

	@Override
	public boolean deleteCCMail(CpServiceTypeDTO serviceTypedto) {
		CpEmailSetting cpEmailSetting = new CpEmailSetting();
		cpEmailSetting.setSerialNo(serviceTypedto.getnSerialNo());
		cpEmailSetting.setCcEmailId(serviceTypedto.getCcEmail());
		return cpCCEmailDAO.deleteCCMAil(cpEmailSetting);
	}

	public List<CpSessionSummaryDTO> listSessionDetails() {
		List<CpSessionSummary> sessionLists = sessionSummary.listSessionDetails();
		List<CpSessionSummaryDTO> sessionAllLists = new ArrayList();
		for (CpSessionSummary cpSessionSummary : sessionLists) {
			CpSessionSummaryDTO cpSessionSummaryDTO = new CpSessionSummaryDTO();
			cpSessionSummaryDTO.setNsessionId(cpSessionSummary.getNsessionId());
			cpSessionSummaryDTO.setVuserName(cpSessionSummary.getVuserName());
			cpSessionSummaryDTO.setDsessionStart(cpSessionSummary.getDsessionStart());
			cpSessionSummaryDTO.setDsessionEnd(cpSessionSummary.getDsessionEnd());
			cpSessionSummaryDTO.setVlastUpdUser(cpSessionSummary.getVlastUpdUser());
			cpSessionSummaryDTO.setVlastUpdProg(cpSessionSummary.getVlastUpdProg());
			cpSessionSummaryDTO.setVlastUpdInftim(cpSessionSummary.getVlastUpdInftim());
			cpSessionSummaryDTO.setvLogOff(cpSessionSummary.getvLogOff());
			cpSessionSummaryDTO.setnActive(cpSessionSummary.getnActive());
			cpSessionSummaryDTO.setdLastSessionTransaction(cpSessionSummary.getdLastSessionTransaction());
			cpSessionSummaryDTO.setvPhpSessionId(cpSessionSummary.getvPhpSessionId());
			cpSessionSummaryDTO.setVclientIp(cpSessionSummary.getVclientIp());
			sessionAllLists.add(cpSessionSummaryDTO);
		}

		return sessionAllLists;
	}

	public List<PasswordRulesDTO> listPasswordRules() {
		List<PasswordRules> rulesList = this.passwordRules.listPasswordRules();
		List<PasswordRulesDTO> rulesAllLists = new ArrayList();
		for (PasswordRules passwordRules : rulesList) {
			PasswordRulesDTO passwordRulesDTO = new PasswordRulesDTO();
			passwordRulesDTO.setnSettingId(passwordRules.getnSettingId());
			passwordRulesDTO.setnMinAlphabetAllow(passwordRules.getnMinAlphabetAllow());
			passwordRulesDTO.setnMinLowerAllow(passwordRules.getnMinLowerAllow());
			passwordRulesDTO.setnMinNoAllow(passwordRules.getnMinNoAllow());
			passwordRulesDTO.setnMinSpecialAllow(passwordRules.getnMinSpecialAllow());
			passwordRulesDTO.setnMinUpperAllow(passwordRules.getnMinUpperAllow());
			passwordRulesDTO.setnPasswordLength(passwordRules.getnPasswordLength());
			passwordRulesDTO.setvSpecialCharAllow(passwordRules.getvSpecialCharAllow());
			passwordRulesDTO.setvCompPrefix(passwordRules.getvCompPrefix());
			passwordRulesDTO.setvSmtpHost(passwordRules.getvSmtpHost());
			passwordRulesDTO.setvSmtpPort(passwordRules.getvSmtpPort());
			passwordRulesDTO.setvSmtpUser(passwordRules.getvSmtpUser());
			passwordRulesDTO.setvSmtpPassword(passwordRules.getvSmtpPassword());
			passwordRulesDTO.setvFromEmail(passwordRules.getvFromEmail());
			passwordRulesDTO.setvCsdEmail(passwordRules.getvCsdEmail());
			passwordRulesDTO.setvTncEng(passwordRules.getvTncEng());
			passwordRulesDTO.setvTncArb(passwordRules.getvTncArb());
			passwordRulesDTO.setnPasswordAge(passwordRules.getnPasswordAge());
			passwordRulesDTO.setnActivationPeriod(passwordRules.getnActivationPeriod());
			passwordRulesDTO.setnDormantPeriod(passwordRules.getnDormantPeriod());
			passwordRulesDTO.setvPasswordSameasUser(passwordRules.getvPasswordSameasUser());
			passwordRulesDTO.setnPasswordHis(passwordRules.getnPasswordHis());
			passwordRulesDTO.setvChangePassReq(passwordRules.getvChangePassReq());
			passwordRulesDTO.setnAccntLockout(passwordRules.getnAccntLockout());
			passwordRulesDTO.setvReleaseAccntLock(passwordRules.getvReleaseAccntLock());
			passwordRulesDTO.setnTimeReleaseAccntLock(passwordRules.getnTimeReleaseAccntLock());
			passwordRulesDTO.setnRequiredCaptcha(passwordRules.getnRequiredCaptcha());
			passwordRulesDTO.setnRequiredMobOTP(passwordRules.getnRequiredMobOTP());
			passwordRulesDTO.setnQuestionCount(passwordRules.getnQuestionCount());
			rulesAllLists.add(passwordRulesDTO);
		}
		return rulesAllLists;
	}

	public boolean updateOtpSettings(CpOtpSettingsDTO cpOtpSettingdtos) {

		CpOtpSettings cpOtpSettings = new CpOtpSettings();
		cpOtpSettings.setSequenceNo(cpOtpSettingdtos.getSequenceNo());
		cpOtpSettings.setvServiceType(cpOtpSettingdtos.getvServiceType());
		cpOtpSettings.setvOtpFlagEmail(cpOtpSettingdtos.getvOtpFlagEmail());
		cpOtpSettings.setvOtpFlagMobile(cpOtpSettingdtos.getvOtpFlagMobile());

		return otpSetUpDAO.updateOtpSettings(cpOtpSettings);
	}

	@Override
	public List<CpOtpSettingsDTO> listOtpSettings(String serviceType) {
		List<CpOtpSettings> otpRules = otpSetUpDAO.listOtpSettings(serviceType);
		List<CpOtpSettingsDTO> rulesAllLists = new ArrayList<CpOtpSettingsDTO>();
		for (CpOtpSettings cpOtpSettings : otpRules) {
			CpOtpSettingsDTO cpOtpSettingsDTOs = new CpOtpSettingsDTO();
			cpOtpSettingsDTOs.setSequenceNo(cpOtpSettings.getSequenceNo());
			cpOtpSettingsDTOs.setvServiceType(cpOtpSettings.getvServiceType());
			cpOtpSettingsDTOs.setvOtpFlagEmail(cpOtpSettings.getvOtpFlagEmail());
			cpOtpSettingsDTOs.setvOtpFlagMobile(cpOtpSettings.getvOtpFlagMobile());
			rulesAllLists.add(cpOtpSettingsDTOs);
		}
		return rulesAllLists;
	}

	public boolean updatePasswordRules(PasswordRulesDTO passwordRulesDTO) {

		PasswordRules passwordRules1 = new PasswordRules();
		passwordRules1.setnSettingId(passwordRulesDTO.getnSettingId());
		passwordRules1.setnMinAlphabetAllow(passwordRulesDTO.getnMinAlphabetAllow());
		passwordRules1.setnMinLowerAllow(passwordRulesDTO.getnMinLowerAllow());
		passwordRules1.setnMinNoAllow(passwordRulesDTO.getnMinNoAllow());
		passwordRules1.setnMinSpecialAllow(passwordRulesDTO.getnMinSpecialAllow());
		passwordRules1.setnMinUpperAllow(passwordRulesDTO.getnMinUpperAllow());
		passwordRules1.setnPasswordLength(passwordRulesDTO.getnPasswordLength());
		passwordRules1.setvSpecialCharAllow(passwordRulesDTO.getvSpecialCharAllow());
		passwordRules1.setvCompPrefix(passwordRulesDTO.getvCompPrefix());
		passwordRules1.setvSmtpHost(passwordRulesDTO.getvSmtpHost());
		passwordRules1.setvSmtpPassword(passwordRulesDTO.getvSmtpPassword());
		passwordRules1.setvSmtpPort(passwordRulesDTO.getvSmtpPort());
		passwordRules1.setvSmtpUser(passwordRulesDTO.getvSmtpUser());
		passwordRules1.setvFromEmail(passwordRulesDTO.getvFromEmail());
		passwordRules1.setvCsdEmail(passwordRulesDTO.getvCsdEmail());
		passwordRules1.setvTncEng(passwordRulesDTO.getvTncEng());
		passwordRules1.setvTncArb(passwordRulesDTO.getvTncArb());
		passwordRules1.setnPasswordAge(passwordRulesDTO.getnPasswordAge());
		passwordRules1.setnActivationPeriod(passwordRulesDTO.getnActivationPeriod());
		passwordRules1.setnDormantPeriod(passwordRulesDTO.getnDormantPeriod());
		passwordRules1.setvPasswordSameasUser(passwordRulesDTO.getvPasswordSameasUser());
		passwordRules1.setnPasswordHis(passwordRulesDTO.getnPasswordHis());
		passwordRules1.setvChangePassReq(passwordRulesDTO.getvChangePassReq());
		passwordRules1.setnAccntLockout(passwordRulesDTO.getnAccntLockout());
		passwordRules1.setvReleaseAccntLock(passwordRulesDTO.getvReleaseAccntLock());
		passwordRules1.setnTimeReleaseAccntLock(passwordRulesDTO.getnTimeReleaseAccntLock());
		passwordRules1.setnRequiredCaptcha(passwordRulesDTO.getnRequiredCaptcha());
		passwordRules1.setnRequiredMobOTP(passwordRulesDTO.getnRequiredMobOTP());
		passwordRules1.setnQuestionCount(passwordRulesDTO.getnQuestionCount());

		return passwordRules.updatePasswordRules(passwordRules1);
	}

	public boolean updateTermsCondition(List<CpTermAndConditionDTO> cpTermAndConditionDTO) {
		// TODO Auto-generated method stub
		List<CpTermAndCondition> cpTermAndCondition = new ArrayList<CpTermAndCondition>();
		for (CpTermAndConditionDTO dto : cpTermAndConditionDTO) {
			CpTermAndCondition terms = new CpTermAndCondition();
			terms.setNserialNo(dto.getSerialNo());
			terms.setNorder(dto.getOrder());
			terms.setVcondArb(dto.getConditionArb());
			terms.setVcondEng(dto.getConditionEng());
			terms.setVpageName(dto.getPage_name());
			if (dto.isRequired())
				terms.setVrequired("Y");
			else
				terms.setVrequired("N");
			if (dto.isMandatory())
				terms.setVmend("Y");
			else
				terms.setVmend("N");

			cpTermAndCondition.add(terms);
		}

		return ftTermsCondition.updateTermsCondition(cpTermAndCondition);
	}

	public boolean saveTermsCondition(List<CpTermAndConditionDTO> cpTermAndConditionDTO) {
		// TODO Auto-generated method stub
		List<CpTermAndCondition> cpTermAndCondition = new ArrayList<CpTermAndCondition>();
		for (CpTermAndConditionDTO dto : cpTermAndConditionDTO) {
			CpTermAndCondition terms = new CpTermAndCondition();
			terms.setNserialNo(dto.getSerialNo());
			terms.setNorder(dto.getOrder());
			terms.setVcondArb(dto.getConditionArb());
			terms.setVcondEng(dto.getConditionEng());
			terms.setVpageName(dto.getPage_name());
			if (dto.isRequired())
				terms.setVrequired("Y");
			else
				terms.setVrequired("N");
			if (dto.isMandatory())
				terms.setVmend("Y");
			else
				terms.setVmend("N");

			cpTermAndCondition.add(terms);
		}

		return ftTermsCondition.saveTermsCondition(cpTermAndCondition);
	}
	
	public boolean deleteTermsCondition(CpTermAndConditionDTO dto) {
		// TODO Auto-generated method stub
		CpTermAndCondition termCondition = new CpTermAndCondition();
		termCondition.setNserialNo(dto.getSerialNo());

		return ftTermsCondition.deleteTermsCondition(termCondition);

	}

	public List<CpTermAndConditionDTO> listTermsCondition(String serviceType) {

		List<CpTermAndCondition> termsCondition = ftTermsCondition.listTermsCondition(serviceType);
		List<CpTermAndConditionDTO> condition = new ArrayList<CpTermAndConditionDTO>();
		for (CpTermAndCondition cpTermAndCondition : termsCondition) {
			CpTermAndConditionDTO cpTermAndConditionDTO = new CpTermAndConditionDTO();
			cpTermAndConditionDTO.setSerialNo(cpTermAndCondition.getNserialNo());
			cpTermAndConditionDTO.setPage_name(cpTermAndCondition.getVpageName());
			cpTermAndConditionDTO.setOrder(cpTermAndCondition.getNorder());
			cpTermAndConditionDTO.setConditionArb(cpTermAndCondition.getVcondArb());
			cpTermAndConditionDTO.setConditionEng(cpTermAndCondition.getVcondEng());
			if (cpTermAndCondition.getVmend().equalsIgnoreCase("Y"))
				cpTermAndConditionDTO.setMandatory(true);
			else
				cpTermAndConditionDTO.setMandatory(false);
			if (cpTermAndCondition.getVrequired().equalsIgnoreCase("Y"))
				cpTermAndConditionDTO.setRequired(true);
			else
				cpTermAndConditionDTO.setRequired(false);
			condition.add(cpTermAndConditionDTO);
		}
		return condition;

	}

	public List<CpQuestionsDetailDTO> listSecurityQuestion() {
		List<CpQuestionDetail> questionList = questionDetails.listSecurityQuestion();
		List<CpQuestionsDetailDTO> allQuestionList = new ArrayList();
		for (CpQuestionDetail cpQuestionDetail : questionList) {
			CpQuestionsDetailDTO cpQuestionsDetailDTO = new CpQuestionsDetailDTO();
			cpQuestionsDetailDTO.setQuesId(cpQuestionDetail.getnQuesId());
			cpQuestionsDetailDTO.setQuesAR(cpQuestionDetail.getvQuesAR());
			cpQuestionsDetailDTO.setQuesEN(cpQuestionDetail.getvQuesEN());
			cpQuestionsDetailDTO.setLastUpdDate(cpQuestionDetail.getvLastUpdDate());

			allQuestionList.add(cpQuestionsDetailDTO);
		}
		return allQuestionList;
	}

	public boolean updateSecurityQuestion(List<CpQuestionsDetailDTO> cpQuestionsDetailDTOs) {
		List<CpQuestionDetail> cpQuestionDetail = new ArrayList();

		for (CpQuestionsDetailDTO dto : cpQuestionsDetailDTOs) {
			CpQuestionDetail details = new CpQuestionDetail();
			details.setnQuesId(dto.getQuesId());
			details.setvQuesEN(dto.getQuesEN());
			details.setvQuesAR(dto.getQuesAR());

			cpQuestionDetail.add(details);
		}

		return questionDetails.updateSecurityQuestion(cpQuestionDetail);
	}

	public boolean updateUserLockStatus(CpUserInfoDTO cpUserInfoDTOs) {
		CpUserInfo details = new CpUserInfo();
		details.setNid(cpUserInfoDTOs.getNid());
		details.setNcustRefNo(cpUserInfoDTOs.getNcustRefNo());
		details.setVprefName(cpUserInfoDTOs.getVprefName());
		details.setVactive(cpUserInfoDTOs.getVactive());
		details.setVlocked(cpUserInfoDTOs.getVlocked());
		details.setVuserName(cpUserInfoDTOs.getVuserName());
		details.setDdob(cpUserInfoDTOs.getDdob());
		details.setVemail(cpUserInfoDTOs.getVemail());
		details.setVcontactNo(cpUserInfoDTOs.getVcontactNo());
		details.setVpassword(cpUserInfoDTOs.getVpassword());
		details.setVuserActivationDate(cpUserInfoDTOs.getVuserActivationDate());

		return adminLoginDAO.updateUserLockStatus(details);
	}

	public List<SecurityImagesDTO> listSecurityImages() {
		int i = 1;

		List<CpSecurityImgMaster> securityImageList = securityImageDetails.listSecurityImgMaster();
		List<SecurityImagesDTO> securityImages = new ArrayList();
		for (CpSecurityImgMaster cpSecurityImage : securityImageList) {
			SecurityImagesDTO securityImageDTO = new SecurityImagesDTO();
			securityImageDTO.setNid(cpSecurityImage.getnId());
			securityImageDTO.setImgPath(cpSecurityImage.getvImgPath());
			securityImageDTO.setImgType(cpSecurityImage.getvImgType());
			securityImageDTO.setDescription(cpSecurityImage.getvDesc());
			String test = "Image " + i;
			securityImageDTO.setImageName(test.toString());
			securityImages.add(securityImageDTO);
			i++;
		}
		return securityImages;
	}

	public boolean updateSecurityImages1(SecurityImagesDTO securityImagesDTO) {
		CpSecurityImgMaster securityimages = new CpSecurityImgMaster();
		securityimages.setnId(securityImagesDTO.getNid());
		securityimages.setvDesc(securityImagesDTO.getDescription());
		securityimages.setvImgPath(securityImagesDTO.getImgPath());
		securityimages.setvImgType(securityImagesDTO.getImgType());
		securityimages.setnId(securityImagesDTO.getNid());

		return securityImageDetails.updateSecurityImages1(securityimages);
	}

	public boolean updateSecurityImages(List<SecurityImagesDTO> securityImagesDTO) {
		List<CpSecurityImgMaster> securityimages = new ArrayList();
		for (SecurityImagesDTO securityImage : securityImagesDTO) {
			CpSecurityImgMaster securityImgMaster = new CpSecurityImgMaster();
			securityImgMaster.setnId(securityImage.getNid());
			securityImgMaster.setvDesc(securityImage.getDescription());
			securityImgMaster.setvImgPath(securityImage.getImgPath());
			securityImgMaster.setvImgType(securityImage.getImgType());
			securityimages.add(securityImgMaster);
		}
		return securityImageDetails.updateSecurityImages(securityimages);
	}

	public List<CpServerSettingDTO> fetchDownTime() {
		List<CpServerstatusSetting> cpServerSettingList = downTimeDAO.listDownTime();
		List<CpServerSettingDTO> cpServerSettingDTOList = new ArrayList();
		for (CpServerstatusSetting cpServerSetting : cpServerSettingList) {
			CpServerSettingDTO cpServerSettingDTO = new CpServerSettingDTO();
			cpServerSettingDTO.setnId(cpServerSetting.getNid());
			cpServerSettingDTO.setvApplicationDownType(cpServerSetting.getvApplicationDownType());
			cpServerSettingDTO.setvEffectiveFrom(cpServerSetting.getdEffectiveFrom());
			cpServerSettingDTO.setvEffectTill(cpServerSetting.getdEffectiveTo());
			cpServerSettingDTO.setdStartTime(cpServerSetting.getdStartTime());
			cpServerSettingDTO.setdEndTime(cpServerSetting.getdEndTime());
			cpServerSettingDTO.setStatus(cpServerSetting.getDownstatus());
			cpServerSettingDTOList.add(cpServerSettingDTO);
		}
		return cpServerSettingDTOList;
	}

	public boolean setDownTime(CpServerSettingDTO cpServerSettingDTO) {
		CpServerstatusSetting cpServerstatusSetting = new CpServerstatusSetting();
		cpServerstatusSetting.setvApplicationDownType(cpServerSettingDTO.getvApplicationDownType());
		cpServerstatusSetting.setdEffectiveFrom(cpServerSettingDTO.getvEffectiveFrom());
		cpServerstatusSetting.setdEffectiveTo(cpServerSettingDTO.getvEffectTill());
		cpServerstatusSetting.setdStartTime(cpServerSettingDTO.getdStartTime());
		cpServerstatusSetting.setdEndTime(cpServerSettingDTO.getdEndTime());
		cpServerstatusSetting.setDownstatus(cpServerSettingDTO.getStatus());
		return downTimeDAO.insertDownTime(cpServerstatusSetting);
	}

	public boolean updateDownTime(CpServerSettingDTO cpServerSettingDTO) {
		CpServerstatusSetting cpServerstatusSetting = new CpServerstatusSetting();
		cpServerstatusSetting.setNid(cpServerSettingDTO.getnId());
		cpServerstatusSetting.setvApplicationDownType(cpServerSettingDTO.getvApplicationDownType());
		cpServerstatusSetting.setdEffectiveFrom(cpServerSettingDTO.getvEffectiveFrom());
		cpServerstatusSetting.setdEffectiveTo(cpServerSettingDTO.getvEffectTill());
		cpServerstatusSetting.setdStartTime(cpServerSettingDTO.getdStartTime());
		cpServerstatusSetting.setdEndTime(cpServerSettingDTO.getdEndTime());
		cpServerstatusSetting.setDownstatus(cpServerSettingDTO.getStatus());
		
		return downTimeDAO.updateDownTime(cpServerstatusSetting);
	}

	public boolean updateUserStatus(CpUserInfoDTO cpUserInfoDTOs) {

		CpUserInfo details = new CpUserInfo();
		details.setNid(cpUserInfoDTOs.getNid());
		details.setNcustRefNo(cpUserInfoDTOs.getNcustRefNo());
		details.setVprefName(cpUserInfoDTOs.getVprefName());
		details.setVactive(cpUserInfoDTOs.getVactive());
		details.setVuserName(cpUserInfoDTOs.getVuserName());
		details.setDdob(cpUserInfoDTOs.getDdob());
		details.setVemail(cpUserInfoDTOs.getVemail());
		details.setVcontactNo(cpUserInfoDTOs.getVcontactNo());
		details.setVpassword(cpUserInfoDTOs.getVpassword());
		details.setVuserActivationDate(cpUserInfoDTOs.getVuserActivationDate());

		return adminLoginDAO.updateUserStatus(details);
	}

	public List<RegistrationTrackDTO> listRegistrationTrack() {
		List<CpRegistrationTrack> regTrackList = cpRegistrationTrackDao.listAllRegistTrack();
		List<RegistrationTrackDTO> registrationTrackList = new ArrayList();
		for (CpRegistrationTrack cpRegistrationTrack : regTrackList) {
			RegistrationTrackDTO registrationTrackDTO = new RegistrationTrackDTO();
			registrationTrackDTO.setIpAddr(cpRegistrationTrack.getvIpAddr());
			registrationTrackDTO.setPlanNo(cpRegistrationTrack.getvPlanNo());
			registrationTrackDTO.setEmailId(cpRegistrationTrack.getvEmailId());
			registrationTrackDTO.setPhoneNo(cpRegistrationTrack.getvPhoneNo());
			registrationTrackDTO.setFailReason(cpRegistrationTrack.getvFailReason());

			registrationTrackDTO.setRegdate(cpRegistrationTrack.getdRegdate());

			registrationTrackDTO.setUserId(cpRegistrationTrack.getUserId());
			if (cpRegistrationTrack.getvStatus().equalsIgnoreCase("F")) {
				registrationTrackDTO.setStatus("Fail");
			} else if (cpRegistrationTrack.getvStatus().equalsIgnoreCase("S")) {
				registrationTrackDTO.setStatus("Success");
			}
			registrationTrackDTO.setLastupdDate(cpRegistrationTrack.getvLastupdDate());
			registrationTrackList.add(registrationTrackDTO);
		}
		return registrationTrackList;
	}

	public boolean deleteQuestion(CpQuestionsDetailDTO documentDTO) {
		CpQuestionDetail cpQuestionDetail = new CpQuestionDetail();
		cpQuestionDetail.setnQuesId(documentDTO.getQuesId());
		cpQuestionDetail.setvQuesEN(documentDTO.getQuesEN());
		return questionDetails.deleteQuestion(cpQuestionDetail);
	}

	@Override
	public List<CpServiceTypeDTO> listcCEmail(String screenType) {

		List<CpEmailSetting> ccEmaillisting = cpCCEmailDAO.fetchCCEmailList(screenType);

		List<CpServiceTypeDTO> ccEamillist = new ArrayList<>();

		for (CpEmailSetting list : ccEmaillisting) {
			CpServiceTypeDTO dto = new CpServiceTypeDTO();
			dto.setnSerialNo(list.getSerialNo());
			dto.setCcEmail(list.getCcEmailId());
			dto.setScreenName(list.getScreenName());
			ccEamillist.add(dto);
		}
		return ccEamillist;

	}

	public boolean saveCCEmail(List<CpServiceTypeDTO> ccEmail) {

		List<CpEmailSetting> cpEmailSetting = new ArrayList<>();

		for (CpServiceTypeDTO dto : ccEmail) {

			CpEmailSetting ccEmail1 = new CpEmailSetting();
			ccEmail1.setScreenName(dto.getScreenName());
			ccEmail1.setCcEmailId(dto.getCcEmail());

			cpEmailSetting.add(ccEmail1);
		}

		return cpCCEmailDAO.saveCCEmail(cpEmailSetting);

	}
	
	//malikfefal
	
	public List<CpQuestionnaireDTO> listquestionare(String serviceType) {

		List<CpQuestionnaire> termsCondition = questionnaireDAO.questionnaireList(serviceType);
		List<CpQuestionnaireDTO> condition = new ArrayList<CpQuestionnaireDTO>();
		for (CpQuestionnaire cpQuestionnairelist : termsCondition) {
			CpQuestionnaireDTO cpQuestionnaireDTO = new CpQuestionnaireDTO();
			cpQuestionnaireDTO.setServiceId(cpQuestionnairelist.getServiceId());
			cpQuestionnaireDTO.setServiceName(cpQuestionnairelist.getServiceName());
			cpQuestionnaireDTO.setQuestionId(cpQuestionnairelist.getQuestionId());
			cpQuestionnaireDTO.setQuestionNameEng(cpQuestionnairelist.getQuestionNameEng());
			cpQuestionnaireDTO.setQuestionNameArb(cpQuestionnairelist.getQuestionNameArb());
			
			if (cpQuestionnairelist.getMandatory().equalsIgnoreCase("Y"))
				cpQuestionnaireDTO.setMandatory(true);
			else
				cpQuestionnaireDTO.setMandatory(false);
			if (cpQuestionnairelist.getRequired().equalsIgnoreCase("Y"))
				cpQuestionnaireDTO.setRequired(true);
			else
				cpQuestionnaireDTO.setRequired(false);
			condition.add(cpQuestionnaireDTO);

			
		}
		return condition;

	}
	

	@Override
	public boolean saveQuestionnaire(List<CpQuestionnaireDTO> dto) {
		boolean status = false;
		for(CpQuestionnaireDTO each:dto){
			CpQuestionnaire question = new CpQuestionnaire();
			question.setServiceName(each.getServiceName());
			question.setDomainName(each.getDomain());
			question.setAnswerType(each.getAnswerType());
			question.setQuestionNameEng(each.getQuestionNameEng());
			question.setQuestionNameArb(each.getQuestionNameArb());
			if(each.isMandatory())
				question.setMandatory("Y");
			else
				question.setMandatory("N");
			if(each.isRequired())
				question.setRequired("Y");
			else
				question.setRequired("N");
			question.setLastUpdUser(loginSession.getInstance().getCustUserName());
			question.setLastUpdDate(new Date());
			question.setListBoxAnswers(each.getListBoxAnswers());
			status = questionnaireDAO.saveQuestionnaire(question);
			if(question.getAnswerType().equals("L")){
				String query = Constants.QUERY_LATEST_RECORD_QUES;
				CpQuestionnaire latestQuestion = questionnaireDAO.findLatestRecord(query);
				Long serviceId = latestQuestion.getServiceId();
				for(CpListBoxAnswers eachList:question.getListBoxAnswers()){
					eachList.setServiceId(serviceId);
					eachList.setDomainName(latestQuestion.getDomainName());
					eachList.setQuestionName(latestQuestion.getQuestionNameEng());
					eachList.setLastUpdUser(loginSession.getInstance().getCustUserName());
					eachList.setLastUpdDate(new Date());
					listBoxAnswers.saveListBoxAnswers(eachList);
				}
			}
		}
		return status;		
	}

	@Override
	public List<CpQuestionnaire> getQuestionnaireList(String serviceType) {		
		return questionnaireDAO.questionnaireList(serviceType);
	}

	@Override
	public List<CpListBoxAnswers> getAnswerList(Long serviceId) {		
		return listBoxAnswers.getAnswers(serviceId);
	}

	@Override
	public boolean saveAnswers(List<CpQuestionnaire> answers) {
		boolean status = false;
		StringBuilder builder = new StringBuilder();
		for(CpQuestionnaire answer:answers){
			if(answer.getAnswerType().equals("D")){				
				java.sql.Date date = new java.sql.Date(answer.getDate().getTime());
				answer.setAnswer(date.toString());
			}
			if(answer.getAnswerType().equals("L")){
				for(CpListBoxAnswers each:answer.getListBoxAnswers()){
					builder.append(each.getAnswer()).append(",");					
				}
				String listBoxAnswer = builder.deleteCharAt(builder.length()-1).toString();
				answer.setAnswer(listBoxAnswer);
			}
			answer.setLastUpdUser(loginSession.getInstance().getCustUserName());
			answer.setLastUpdDate(new Date());
			status = questionnaireDAO.saveAnswers(answer);
		}
		return status;
	}
}
