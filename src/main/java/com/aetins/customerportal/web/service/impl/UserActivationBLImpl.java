package com.aetins.customerportal.web.service.impl;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.dao.UserActivationDAO;
import com.aetins.customerportal.web.dao.impl.UserActivationDAOImpl;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.service.UserActivationBL;

@Service
public class UserActivationBLImpl implements UserActivationBL{

	@Autowired
	UserActivationDAO userActive;
	
	@Override
	public List<CpUserInfoDTO> fetchUserDetails(String tokenNo) {
		// TODO Auto-generated method stub
		
		List<CpUserInfoDTO> cpuserInfoDtoList = new ArrayList<CpUserInfoDTO>();
		CpUserInfo cpUserInfo = new CpUserInfo();
		CpUserInfoDTO cpUserInfoDTO = new CpUserInfoDTO();
		List<CpUserInfo> cpUserList = new ArrayList<CpUserInfo>();
		//cpUserInfo.setVuserName(verifyUserdto.getUserName());
		//cpUserInfo.setVpassword(verifyUserdto.getPassword());
		cpUserList = userActive.fetchUserDetails(tokenNo);
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


	public boolean updateStatus(CpUserInfoDTO cpUserInfoDTOs) {
		// TODO Auto-generated method stub
			CpUserInfo details=new CpUserInfo();
			details.setNid(cpUserInfoDTOs.getNid());
			details.setNcustRefNo(cpUserInfoDTOs.getNcustRefNo());
			details.setVprefName(cpUserInfoDTOs.getVprefName());
			details.setVactive(cpUserInfoDTOs.getVactive());
			details.setVuserName(cpUserInfoDTOs.getVuserName());
			details.setVemail(cpUserInfoDTOs.getVemail());
		return userActive.updateUserStatus(details);
	}
	
	
	

}