package com.aetins.customerportal.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aetins.customerportal.web.dao.RegistrationCustomerDAO;
import com.aetins.customerportal.web.dao.ResetSecurityAnswerDAO;
import com.aetins.customerportal.web.dto.RegistrationCustomerDTO;
import com.aetins.customerportal.web.dto.ResetSecurityAnswerDTO;
import com.aetins.customerportal.web.entity.CpResetSecurityAnswer;
import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.service.RegistrationCustomerBL;



/**
 * @author satendra
 * @since 31/01/2017
 */
@Service
public class RegistrationCustomerBLImpl implements RegistrationCustomerBL {

	@Autowired
    private RegistrationCustomerDAO registrationCustomerDAO;
	@Autowired
	private ResetSecurityAnswerDAO resetDAO;
    private CpUserInfo cpUserInfo;
   
    @Override
    @Transactional
    public void saveUserDetails(RegistrationCustomerDTO registrationCustomerDTO) {

	//cpUserInfo.setNcustRefNo(registrationCustomerDTO.getCustomerReferenceNumber().toString());
	cpUserInfo.setVgroup(registrationCustomerDTO.getGroup());
	cpUserInfo.setVtitle(registrationCustomerDTO.getUserTitle());
	cpUserInfo.setVuserName(registrationCustomerDTO.getUserId());
	cpUserInfo.setVprefName(registrationCustomerDTO.getUserDisplayName());
	//cpUserInfo.setDdob("1981-01-01");
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
	//cpUserInfo.setVuserActivationDate(registrationCustomerDTO.getUserActivationDate());
	cpUserInfo.setVactiveLogin(registrationCustomerDTO.getActiveLogin());
	cpUserInfo.setVloginSession(registrationCustomerDTO.getLoginSessionId());
	cpUserInfo.setVlastupdUser(registrationCustomerDTO.getLastUpdateUser());
	//cpUserInfo.setVlastupdDate(registrationCustomerDTO.getLastUpdateDate());
	cpUserInfo.setnImageId(registrationCustomerDTO.getImageID());

	registrationCustomerDAO.save(cpUserInfo);
    }

    @Override
    @Transactional
    public List<CpUserInfo> getAllData() {
	return registrationCustomerDAO.findAll();
    }

    @Override
    @Transactional
    public Object findByID(Integer id) {
	return registrationCustomerDAO.findById(Long.valueOf(id));

    }
    
    
    @Override
	public void saveRegistraionQuestionDetails(List<ResetSecurityAnswerDTO> registrationCustomerDTO) {
		List<CpResetSecurityAnswer> securityAnswer = new ArrayList<>();
		for (ResetSecurityAnswerDTO dto : registrationCustomerDTO) {

			CpResetSecurityAnswer cpResetSecurityAnswer = new CpResetSecurityAnswer();
			cpResetSecurityAnswer.setId(dto.getId());
			cpResetSecurityAnswer.setUserName(dto.getUserName());
			cpResetSecurityAnswer.setCustRefNo(dto.getCustRefNo());
			cpResetSecurityAnswer.setSecurityQues(dto.getSecurityQues());
			cpResetSecurityAnswer.setSecurityAns(dto.getSecurityAns());
			cpResetSecurityAnswer.setQuesStatus(dto.getQuesStatus());
			cpResetSecurityAnswer.setProcessDate(dto.getProcessDate());
			cpResetSecurityAnswer.setRecentModifiedDate(dto.getRecentModifiedDate());
			cpResetSecurityAnswer.setUserOtp(dto.getUserOtp());

			securityAnswer.add(cpResetSecurityAnswer);
		}
		resetDAO.saveRegistrationQuestions(securityAnswer);

	}

	/*
	 * public RegistrationCustomerDAO<CpUserInfo, Integer>
	 * getRegistrationCustomerDAO() { return registrationCustomerDAO; }
	 * 
	 * public void setRegistrationCustomerDAO(RegistrationCustomerDAO<CpUserInfo,
	 * Integer> registrationCustomerDAO) { this.registrationCustomerDAO =
	 * registrationCustomerDAO; }
	 */

	/*public RegistrationCustomerDAO<CpResetSecurityAnswer, Integer> getSecurityQuestionsDAO() {
		return securityQuestionsDAO;
	}

	public void setSecurityQuestionsDAO(RegistrationCustomerDAO<CpResetSecurityAnswer, Integer> securityQuestionsDAO) {
		this.securityQuestionsDAO = securityQuestionsDAO;
	}*/
    
    

}
