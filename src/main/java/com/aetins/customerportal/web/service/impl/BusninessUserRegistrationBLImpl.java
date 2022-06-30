package com.aetins.customerportal.web.service.impl;

import java.util.Date;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aetins.customerportal.web.dao.CpUserInfoDAO;
import com.aetins.customerportal.web.dto.BusinessUserRegisrtationDTO;
import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.service.BusninessUserRegistrationBL;

/**
 * @author satendra
 */
@Service
@ManagedBean(name = "busninessUserRegistrationBL")
@RequestScoped
public class BusninessUserRegistrationBLImpl implements BusninessUserRegistrationBL {

	private static final Logger logger = Logger.getLogger(BusninessUserRegistrationBLImpl.class);

	@Autowired
	private CpUserInfoDAO cpUserInfoDAO;

	private CpUserInfo cpUserInfo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aetins.webservices.bl.BusninessUserRegistrationBL#saveBusinessUser(com.
	 * aetins.webservices.DTO. BusinessUserRegisrtationDTO)
	 */
	@Override
	@Transactional
	public void saveBusinessUser(BusinessUserRegisrtationDTO businessUserRegisrtationDTO) {
		cpUserInfo = new CpUserInfo();
		cpUserInfo.setvBusrDept(businessUserRegisrtationDTO.getDepartment());
		cpUserInfo.setVemail(businessUserRegisrtationDTO.getEmail());
		cpUserInfo.setVcontactNo(businessUserRegisrtationDTO.getMobileNo());
		cpUserInfo.setVuserName(businessUserRegisrtationDTO.getUserId());
		cpUserInfo.setVprefName(
				businessUserRegisrtationDTO.getFirstName() + " " + businessUserRegisrtationDTO.getLastName());

		try {
			cpUserInfo.setNcustRefNo(Integer.parseInt(new Random().nextInt(8) + ""));
		} catch (NumberFormatException e) {
			logger.error("Number format exception" + e.getMessage());
		}

		cpUserInfo.setVgroup("B");// default to business user //need to change
		cpUserInfo.setVpassword(businessUserRegisrtationDTO.getPassword());
		cpUserInfo.setVactive("A");// default to business user
		cpUserInfo.setVtitle(businessUserRegisrtationDTO.getTitle());
		cpUserInfo.setVuserActivationDate(new Date());
		cpUserInfoDAO.saveUserInfo(cpUserInfo);
	}

	public CpUserInfoDAO getCpUserInfoDAO() {
		return cpUserInfoDAO;
	}

	public void setCpUserInfoDAO(CpUserInfoDAO cpUserInfoDAO) {
		this.cpUserInfoDAO = cpUserInfoDAO;
	}

}
