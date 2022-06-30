package com.aetins.customerportal.web.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.dao.ContributionHolidayDAO;
import com.aetins.customerportal.web.dao.CpRequestMasterDAO;
import com.aetins.customerportal.web.dao.CustomerLoginDAO;
import com.aetins.customerportal.web.dao.DownTimeDAO;
import com.aetins.customerportal.web.dao.PasswordRulesDAO;
import com.aetins.customerportal.web.dao.SessionSummaryDAO;
import com.aetins.customerportal.web.dao.impl.CustomerLoginDAOImpl;
import com.aetins.customerportal.web.dao.impl.DownTimeDAOImpl;
import com.aetins.customerportal.web.dao.impl.PasswordRulesDAOImpl;
import com.aetins.customerportal.web.dao.impl.SessionSummaryDAOImpl;
import com.aetins.customerportal.web.dto.CpServerSettingDTO;
import com.aetins.customerportal.web.dto.CpSessionSummaryDTO;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.dto.PasswordRulesDTO;
import com.aetins.customerportal.web.dto.ServiceRequestMasterDTO;
import com.aetins.customerportal.web.dto.UserDTO;
import com.aetins.customerportal.web.entity.CpRequestMaster;
import com.aetins.customerportal.web.entity.CpServerstatusSetting;
import com.aetins.customerportal.web.entity.CpSessionSummary;
import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.entity.PasswordRules;
import com.aetins.customerportal.web.service.CustomerLoginBL;
import com.aetins.customerportal.web.utils.BSLUtils;
import com.aetins.customerportal.web.common.Constants;


@Service
public class CustomerLoginBLImpl implements CustomerLoginBL {
	
	
	private static final Logger logger = Logger.getLogger(CustomerLoginBLImpl.class);

	@Autowired
	SessionSummaryDAO sessionDao;

	@Autowired
	CustomerLoginDAO customerLoginDao;
	
	@Autowired
	DownTimeDAO downTimeDAO;
	
	@Autowired
	PasswordRulesDAO passwordRulesDAO;
	
	@Autowired
	CpRequestMasterDAO cpRequestMasterDAO;
	
	@Autowired
	ContributionHolidayDAO contriNew;
	
	@Override
	public boolean saveUserDetails(CpUserInfoDTO userLogindto) {
		CpUserInfo cpUserInfo = new CpUserInfo();
		cpUserInfo.setNcustRefNo(userLogindto.getNcustRefNo());
		cpUserInfo.setVgroup(userLogindto.getVgroup());
		cpUserInfo.setVtitle(userLogindto.getVtitle());
		cpUserInfo.setVuserName(userLogindto.getVuserName());
		cpUserInfo.setVprefName(userLogindto.getVprefName());
		cpUserInfo.setDdob(userLogindto.getDdob());
		cpUserInfo.setVpassword(userLogindto.getVpassword());
		cpUserInfo.setVpwMustChange(userLogindto.getVpwMustChange());
		/* cpUserInfo.setVimage(userLogindto.getVimage()); */
		cpUserInfo.setVemail(userLogindto.getVemail());
		cpUserInfo.setVquestion1(userLogindto.getVquestion1());
		cpUserInfo.setVanswer1(userLogindto.getVanswer1());
		cpUserInfo.setVquestion2(userLogindto.getVquestion2());
		cpUserInfo.setVanswer2(userLogindto.getVanswer2());
		cpUserInfo.setVpreflang(userLogindto.getVpreflang());
		cpUserInfo.setVcontactNo(userLogindto.getVcontactNo());
		cpUserInfo.setVactive(userLogindto.getVactive());
		cpUserInfo.setVlocked(userLogindto.getVlocked());
		cpUserInfo.setVuserActivationDate(userLogindto.getVuserActivationDate());
		cpUserInfo.setVactiveLogin(userLogindto.getVactiveLogin());
		cpUserInfo.setVloginSession(userLogindto.getVloginSession());
		cpUserInfo.setVlastupdUser(userLogindto.getVlastupdUser());
		cpUserInfo.setVlastupdDate(userLogindto.getVlastupdDate());

		customerLoginDao.saveUserDetails(cpUserInfo);
		return false;
	}

	// added by harmain for verfifation of customer login
	@Override
	public List<CpUserInfoDTO> fetchUserDetails(UserDTO verifyUserdto) {

		List<CpUserInfoDTO> cpuserInfoDtoList = new ArrayList<CpUserInfoDTO>();
		CpUserInfo cpUserInfo = new CpUserInfo();
		CpUserInfoDTO cpUserInfoDTO = new CpUserInfoDTO();
		List<CpUserInfo> cpUserList = new ArrayList<CpUserInfo>();
		cpUserInfo.setVuserName(verifyUserdto.getUserName());
		cpUserInfo.setVpassword(verifyUserdto.getPassword());
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
			cpUserInfoDTO.setvLockedTime(cpuserInfo.getvLockedTime());
			cpUserInfoDTO.setRoles(cpuserInfo.getRoles());
			cpuserInfoDtoList.add(cpUserInfoDTO);
		}
		return cpuserInfoDtoList;
	}

	// end
	
	// addded by vinod kumar for Print form
	/*public List<CpUserInfoDTO> fetchUserDetail(UserDTO verifyUser) {
		List<CpUserInfoDTO> cpuserInfoDtoList = new ArrayList<CpUserInfoDTO>();
		CpUserInfo cpUserInfo = new CpUserInfo();
		CpUserInfoDTO cpUserInfoDTO = new CpUserInfoDTO();
		List<CpUserInfo> cpUserList = new ArrayList<CpUserInfo>();
		cpUserInfo.setVemail(verifyUser.getEmailId());
		cpUserInfo.setVcontactNo(verifyUser.getContactNo());
		cpUserList = customerLoginDao.fetchUserDetail(cpUserInfo);
		Iterator<CpUserInfo> it = cpUserList.iterator();
		while (it.hasNext()) {
			CpUserInfo cpuserInfo = it.next();
			cpUserInfoDTO.setVemail(cpuserInfo.getVemail());
			cpUserInfoDTO.setVcontactNo(cpuserInfo.getVcontactNo());

			cpuserInfoDtoList.add(cpUserInfoDTO);
		}
		return cpuserInfoDtoList;

	}
*/
	

	// added by harmain to fetch status of Down Time

	public CpServerSettingDTO fetchDownTime(CpServerSettingDTO downTimeStatus) {
		List<CpServerstatusSetting> downTimeInfoList = new ArrayList<CpServerstatusSetting>();
		CpServerSettingDTO downTimeInfoDTO = new CpServerSettingDTO();
		CpServerstatusSetting serverstatusSetting = new CpServerstatusSetting();
		List<CpServerSettingDTO> downTimeList = new ArrayList<CpServerSettingDTO>();
		serverstatusSetting.setdEffectiveFrom(downTimeStatus.getvEffectiveFrom());
		serverstatusSetting.setdEffectiveTo(downTimeStatus.getvEffectTill());
		serverstatusSetting.setdStartTime(downTimeStatus.getdStartTime());
		serverstatusSetting.setdEndTime(downTimeStatus.getdEndTime());
		downTimeInfoList = downTimeDAO.fetchDownTime(serverstatusSetting);
		Iterator<CpServerstatusSetting> it = downTimeInfoList.iterator();
		while (it.hasNext()) {
			CpServerstatusSetting downTmeInfo = it.next();
			downTimeInfoDTO.setdEndTime(downTmeInfo.getdEndTime());
			downTimeInfoDTO.setdStartTime(downTmeInfo.getdStartTime());
			downTimeInfoDTO.setnId(downTmeInfo.getNid());
			downTimeInfoDTO.setvApplicationDownType(downTmeInfo.getvApplicationDownType());
			downTimeInfoDTO.setvEffectiveFrom(downTmeInfo.getdEffectiveFrom());
			downTimeInfoDTO.setvEffectTill(downTmeInfo.getdEffectiveTo());

		}
		return downTimeInfoDTO;
	}
	// end

	@Override
	public List<CpUserInfoDTO> listUserDetailsForgetPassword(UserDTO verifyUserdto) {
		CpUserInfo cpUserInfos = new CpUserInfo();
		List<CpUserInfo> userLists = new ArrayList<CpUserInfo>();
		List<CpUserInfoDTO> userAllLists = new ArrayList<CpUserInfoDTO>();
		CpUserInfoDTO cpUserInfoDTO = new CpUserInfoDTO();

		cpUserInfos.setVuserName(verifyUserdto.getUserName());
		userLists = customerLoginDao.listUserDetailsForgetPassword(cpUserInfos);

		Iterator<CpUserInfo> it = userLists.iterator();
		while (it.hasNext()) {
			CpUserInfo cpUserInfo = it.next();
			cpUserInfoDTO.setNid(cpUserInfo.getNid());
			cpUserInfoDTO.setNcustRefNo(cpUserInfo.getNcustRefNo());
			cpUserInfoDTO.setVuserName(cpUserInfo.getVuserName());
			cpUserInfoDTO.setVanswer1(cpUserInfo.getVanswer1());
			cpUserInfoDTO.setVanswer2(cpUserInfo.getVanswer2());
			cpUserInfoDTO.setnImageId(cpUserInfo.getnImageId());
			cpUserInfoDTO.setVpassword(cpUserInfo.getVpassword());
			cpUserInfoDTO.setVemail(cpUserInfo.getVemail());
			cpUserInfoDTO.setVpwMustChange(cpUserInfo.getVpwMustChange());
			cpUserInfoDTO.setVactive(cpUserInfo.getVactive());

			userAllLists.add(cpUserInfoDTO);
		}
		return userAllLists;

	}

	@Override
	public List<CpUserInfoDTO> fetchForgetPasswordQuestion(String userName) {
		CpUserInfo cpUserInfos = new CpUserInfo();
		List<CpUserInfo> userLists = new ArrayList<CpUserInfo>();
		List<CpUserInfoDTO> userAllLists = new ArrayList<CpUserInfoDTO>();
		CpUserInfoDTO cpUserInfoDTO = new CpUserInfoDTO();

		cpUserInfos.setVuserName(userName);
		userLists = customerLoginDao.listUserDetailsForgetPassword(cpUserInfos);

		Iterator<CpUserInfo> it = userLists.iterator();
		while (it.hasNext()) {
			CpUserInfo cpUserInfo = it.next();
			cpUserInfoDTO.setVuserName(cpUserInfo.getVuserName());
			cpUserInfoDTO.setNcustRefNo(cpUserInfo.getNcustRefNo());
			cpUserInfoDTO.setVquestion1(cpUserInfo.getVquestion1());
			cpUserInfoDTO.setVquestion2(cpUserInfo.getVquestion2());
			cpUserInfoDTO.setVanswer1(cpUserInfo.getVanswer1());
			cpUserInfoDTO.setVanswer2(cpUserInfo.getVanswer2());
			cpUserInfoDTO.setVcontactNo(cpUserInfo.getVcontactNo());
			cpUserInfoDTO.setVemail(cpUserInfo.getVemail());
			userAllLists.add(cpUserInfoDTO);
		}
		return userAllLists;

	}
	
	
	@Override
	public boolean updateChangePassWord(CpUserInfoDTO cpUserInfoDTO) {
		CpUserInfo cpUserInfo = new CpUserInfo();
		cpUserInfo.setNid(cpUserInfoDTO.getNid());
		cpUserInfo.setVpassword(cpUserInfoDTO.getVpassword());
		cpUserInfo.setVpwMustChange("N");
		if(BSLUtils.isNotNull(cpUserInfoDTO.getVactive())){
			if(cpUserInfoDTO.getVactive().equalsIgnoreCase(Constants.CHANGE_STATUS)){
				cpUserInfoDTO.setVactive(Constants.ACTIVE_STATUS);
			}
		}
		cpUserInfo.setVactive(cpUserInfoDTO.getVactive());
		
		return customerLoginDao.updateColumns(cpUserInfo);
	}
	
	@Override
	public boolean updateForgetPassWord(CpUserInfoDTO cpUserInfoDTO) {
		CpUserInfo cpUserInfo = new CpUserInfo();
		cpUserInfo.setNid(cpUserInfoDTO.getNid());
		cpUserInfo.setVpassword(cpUserInfoDTO.getVpassword());
		if(BSLUtils.isNotNull(cpUserInfoDTO.getVactive())){
			if(cpUserInfoDTO.getVactive().equalsIgnoreCase(Constants.ACTIVE_STATUS)){
				cpUserInfoDTO.setVactive(Constants.CHANGE_STATUS);
			}
		}
		cpUserInfo.setVactive(cpUserInfoDTO.getVactive());
		
		return customerLoginDao.updateColumns(cpUserInfo);
	}


	// added by Harmain for tracking customer login session
	@Override
	public void saveSessionDetails(CpSessionSummaryDTO nsessionsinfo) {

		Calendar cal = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date currentDate = new Date();

		cal = Calendar.getInstance();
		cal.setTime(currentDate);

		CpSessionSummary sessionInfo = new CpSessionSummary();

		sessionInfo.setVclientIp(nsessionsinfo.getVclientIp());
		sessionInfo.setDsessionEnd(nsessionsinfo.getDsessionEnd());
		sessionInfo.setDsessionStart(nsessionsinfo.getDsessionStart());
		sessionInfo.setnActive(nsessionsinfo.getnActive());
		sessionInfo.setVlastUpdInftim(nsessionsinfo.getVlastUpdInftim());
		sessionInfo.setVlastUpdProg(nsessionsinfo.getVlastUpdProg());
		sessionInfo.setdLastSessionTransaction(nsessionsinfo.getdLastSessionTransaction());
		sessionInfo.setVlastUpdUser(nsessionsinfo.getVlastUpdUser());
		sessionInfo.setvLogOff(nsessionsinfo.getvLogOff());
		sessionInfo.setvPhpSessionId(nsessionsinfo.getvPhpSessionId());
		sessionInfo.setdLastSessionTransaction(currentDate);
		sessionInfo.setVuserName(nsessionsinfo.getVuserName());

		sessionDao.saveSessionDetails(sessionInfo);

	}
/*added by vishal*/
	@Override
	public boolean updateUserDetails(CpUserInfoDTO cpUserInfoDTO) {
		
		CpUserInfo cpUserInfo = new CpUserInfo();
		cpUserInfo.setNid(cpUserInfoDTO.getNid());
		cpUserInfo.setNcustRefNo(cpUserInfoDTO.getNcustRefNo());
		cpUserInfo.setVgroup(cpUserInfoDTO.getVgroup());
		cpUserInfo.setVtitle(cpUserInfoDTO.getVtitle());
		cpUserInfo.setVpassword(cpUserInfoDTO.getVpassword());

		cpUserInfo.setVuserName(cpUserInfoDTO.getVuserName());
		cpUserInfo.setVprefName(cpUserInfoDTO.getVprefName());
		cpUserInfo.setDdob(cpUserInfoDTO.getDdob());
		cpUserInfo.setVpwMustChange(cpUserInfoDTO.getVpwMustChange());
		cpUserInfo.setVemail(cpUserInfoDTO.getVemail());
		cpUserInfo.setVquestion1(cpUserInfoDTO.getVquestion1());
		cpUserInfo.setVanswer1(cpUserInfoDTO.getVanswer1());
		cpUserInfo.setVquestion2(cpUserInfoDTO.getVquestion2());
		cpUserInfo.setVanswer2(cpUserInfoDTO.getVanswer2());
		cpUserInfo.setVpreflang(cpUserInfoDTO.getVpreflang());
		cpUserInfo.setVcontactNo(cpUserInfoDTO.getVcontactNo());
		cpUserInfo.setVactive(cpUserInfoDTO.getVactive());
		cpUserInfo.setVlocked(cpUserInfoDTO.getVlocked());
		cpUserInfo.setVuserActivationDate(cpUserInfoDTO.getVuserActivationDate());
		cpUserInfo.setVactiveLogin(cpUserInfoDTO.getVactiveLogin());
		cpUserInfo.setVloginSession(cpUserInfoDTO.getVloginSession());
		cpUserInfo.setVlastupdUser(cpUserInfoDTO.getVlastupdUser());
		cpUserInfo.setVlastupdDate(cpUserInfoDTO.getVlastupdDate());
		cpUserInfo.setnImageId(cpUserInfoDTO.getnImageId());
		cpUserInfo.setRoles(cpUserInfoDTO.getRoles());
		// cpUserInfo.setvBusrDept(cpUserInfoDTO.ge)
		return customerLoginDao.updateUserDetails(cpUserInfo);
		
		
	}

	@Override
	public boolean updatePassWord(CpUserInfoDTO cpUserInfoDTO) {
		CpUserInfo cpUserInfo = new CpUserInfo();
		cpUserInfo.setNid(cpUserInfoDTO.getNid());
		cpUserInfo.setNcustRefNo(cpUserInfoDTO.getNcustRefNo());
		cpUserInfo.setVgroup(cpUserInfoDTO.getVgroup());
		cpUserInfo.setVtitle(cpUserInfoDTO.getVtitle());
		cpUserInfo.setVpassword(cpUserInfoDTO.getVpassword());

		cpUserInfo.setVuserName(cpUserInfoDTO.getVuserName());
		cpUserInfo.setVprefName(cpUserInfoDTO.getVprefName());
		cpUserInfo.setDdob(cpUserInfoDTO.getDdob());
		cpUserInfo.setVpwMustChange(cpUserInfoDTO.getVpwMustChange());
		cpUserInfo.setVemail(cpUserInfoDTO.getVemail());
		cpUserInfo.setVquestion1(cpUserInfoDTO.getVquestion1());
		cpUserInfo.setVanswer1(cpUserInfoDTO.getVanswer1());
		cpUserInfo.setVquestion2(cpUserInfoDTO.getVquestion2());
		cpUserInfo.setVanswer2(cpUserInfoDTO.getVanswer2());
		cpUserInfo.setVpreflang(cpUserInfoDTO.getVpreflang());
		cpUserInfo.setVcontactNo(cpUserInfoDTO.getVcontactNo());
		cpUserInfo.setVactive(cpUserInfoDTO.getVactive());
		cpUserInfo.setVlocked(cpUserInfoDTO.getVlocked());
		cpUserInfo.setVuserActivationDate(cpUserInfoDTO.getVuserActivationDate());
		cpUserInfo.setVactiveLogin(cpUserInfoDTO.getVactiveLogin());
		cpUserInfo.setVloginSession(cpUserInfoDTO.getVloginSession());
		cpUserInfo.setVlastupdUser(cpUserInfoDTO.getVlastupdUser());
		cpUserInfo.setVlastupdDate(cpUserInfoDTO.getVlastupdDate());
		cpUserInfo.setnImageId(cpUserInfoDTO.getnImageId());
		// cpUserInfo.setvBusrDept(cpUserInfoDTO.ge)
		return customerLoginDao.updatePassword(cpUserInfo);
	}

	@Override
	public boolean lockUser(CpUserInfoDTO cpUserInfoDTO) {
		CpUserInfo cpUserInfo = new CpUserInfo();
        
		cpUserInfo.setVuserName(cpUserInfoDTO.getVuserName());
		cpUserInfo.setVpassword(cpUserInfoDTO.getVpassword());
		cpUserInfo.setvLockedTime(cpUserInfoDTO.getvLockedTime());
		return customerLoginDao.lockUser(cpUserInfo);
		}
	
	 @Override
	 public PasswordRulesDTO fetchCpSettings() {
	  List<PasswordRules> entity =  passwordRulesDAO.listPasswordRules();
	  
	  System.out.println("Entity Size :"+entity.size());
	  PasswordRulesDTO rulesDTO = new PasswordRulesDTO();
	  rulesDTO.setnSettingId(entity.get(0).getnSettingId());  
	  rulesDTO.setvCsdEmail(entity.get(0).getvCsdEmail());
	  rulesDTO.setvCompPrefix(entity.get(0).getvCompPrefix());
	  rulesDTO.setnRequiredCaptcha(entity.get(0).getnRequiredCaptcha());
	  rulesDTO.setnAccntLockout(entity.get(0).getnAccntLockout());
	  rulesDTO.setvSmtpHost(entity.get(0).getvSmtpHost());
	  rulesDTO.setvSmtpPort(entity.get(0).getvSmtpPort());
	  rulesDTO.setvSmtpUser(entity.get(0).getvSmtpUser());
	  rulesDTO.setvSmtpPassword(entity.get(0).getvSmtpPassword());
	  rulesDTO.setvFromEmail(entity.get(0).getvFromEmail());
	  rulesDTO.setnTimeReleaseAccntLock(entity.get(0).getnTimeReleaseAccntLock());
	  return rulesDTO;
	 }
	 
	 
	@Override
	public List<ServiceRequestMasterDTO> fetchTransactionRequestList(String userid) {
		// TODO Auto-generated method stub
		logger.info("Entering into fetchTransactionRequestList():::::::::::::" +userid);
		List<CpRequestMaster> cpRequestMasterList = contriNew.getTransactionCount(userid);
		logger.info("size of master list into fetchTransactionRequestList()::::::::::" +cpRequestMasterList.size());
		List<ServiceRequestMasterDTO> ServiceRequestMasterDTOList = new ArrayList<ServiceRequestMasterDTO>();
		for (CpRequestMaster cpRequestMaster : cpRequestMasterList) {
			ServiceRequestMasterDTO serviceRequestMasterDTO = new ServiceRequestMasterDTO();
			serviceRequestMasterDTO.setServiceRequestNo(cpRequestMaster.getServiceRequestNo());
			serviceRequestMasterDTO.setServiceRequestType(cpRequestMaster.getServiceRequestType());
			serviceRequestMasterDTO.setRequestStatusDesc(cpRequestMaster.getRequestStatusDesc());
			serviceRequestMasterDTO.setFatcaFlag(cpRequestMaster.getFatcaFlag());
			
			SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
			if (cpRequestMaster.getServiceRequestDate() != null) {
				String strDate = sm.format(cpRequestMaster.getServiceRequestDate());
				System.out.println(strDate);
				if (BSLUtils.isNotNull(strDate)) {
					serviceRequestMasterDTO.setRequestDate(strDate);
				}
			}
			serviceRequestMasterDTO.setPolicyNo(cpRequestMaster.getPolicyNo());
			if (cpRequestMaster.getRequestStatus().equalsIgnoreCase("AWIT")
					|| cpRequestMaster.getRequestStatus().equalsIgnoreCase("AWAP")) {
				serviceRequestMasterDTO.setRequestStatus("In-Process");
			}  else if(cpRequestMaster.getRequestStatus().equalsIgnoreCase("SUCC")){
				serviceRequestMasterDTO.setRequestStatus("Completed");
			} else if(cpRequestMaster.getRequestStatus().equalsIgnoreCase("REJ")){
				serviceRequestMasterDTO.setRequestStatus("Rejected");
			} 
			//serviceRequestMasterDTO.setUserId(cpRequestMaster.getUserId());
			
            /*if(cpRequestMaster.getRequestStatus().equalsIgnoreCase("AWIT")
					|| cpRequestMaster.getRequestStatus().equalsIgnoreCase("AWAP")
					|| cpRequestMaster.getRequestStatus().equalsIgnoreCase("SUCC")
					|| cpRequestMaster.getRequestStatus().equalsIgnoreCase("REJ")){
			   ServiceRequestMasterDTOList.add(serviceRequestMasterDTO);
            }*/
			 ServiceRequestMasterDTOList.add(serviceRequestMasterDTO);
		}
		logger.info("return from fetchTransactionRequestList()::::::::::" +ServiceRequestMasterDTOList.size());
		return ServiceRequestMasterDTOList;
	}
	
	
	
	
}
// end