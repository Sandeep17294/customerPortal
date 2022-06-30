package com.aetins.customerportal.web.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.dao.CpRegistrationTrackDao;
import com.aetins.customerportal.web.dto.RegistrationTrackDTO;
import com.aetins.customerportal.web.entity.CpRegistrationTrack;
import com.aetins.customerportal.web.service.RegistrationTrackBL;
import com.aetins.customerportal.web.utils.BSLUtils;


/**
 * This BLImpl class for Registration track information
 * @author Viswakarthick
 * 21/06/2017
 */
@Service
public class RegistrationTrackBLImpl implements RegistrationTrackBL {
	private static final Logger logger = Logger.getLogger(RegistrationTrackBLImpl.class);
	
	@Autowired
	CpRegistrationTrackDao cpRegistrationTrackDao;

	public void setCpRegistrationTrackDao(CpRegistrationTrackDao cpRegistrationTrackDao) {
		this.cpRegistrationTrackDao = cpRegistrationTrackDao;
	}

	@Override
	public boolean saveErrTrackDetails(RegistrationTrackDTO registrationTrackDTO) throws Exception {
		boolean status = false;
		try {
			CpRegistrationTrack cpRegistrationTrack = new CpRegistrationTrack();
			if (BSLUtils.isNotNull(registrationTrackDTO.getIpAddr()))
				cpRegistrationTrack.setvIpAddr(registrationTrackDTO.getIpAddr());
			if (BSLUtils.isNotNull(registrationTrackDTO.getPlanNo()))
				cpRegistrationTrack.setvPlanNo(registrationTrackDTO.getPlanNo());
			if (BSLUtils.isNotNull(registrationTrackDTO.getEmailId()))
				cpRegistrationTrack.setvEmailId(registrationTrackDTO.getEmailId());
			if (BSLUtils.isNotNull(registrationTrackDTO.getPhoneNo()))
				cpRegistrationTrack.setvPhoneNo(registrationTrackDTO.getPhoneNo());
			if (BSLUtils.isNotNull(registrationTrackDTO.getStatus()))
				cpRegistrationTrack.setvStatus(registrationTrackDTO.getStatus());
			if (BSLUtils.isNotNull(registrationTrackDTO.getFailReason()))
				cpRegistrationTrack.setvFailReason(registrationTrackDTO.getFailReason());
			if (BSLUtils.isNotNull(registrationTrackDTO.getRegdate()))
				cpRegistrationTrack.setdRegdate(registrationTrackDTO.getRegdate());
			if (BSLUtils.isNotNull(registrationTrackDTO.getLastupdUser()))
				cpRegistrationTrack.setvLastupdUser(registrationTrackDTO.getLastupdUser());
			if (BSLUtils.isNotNull(registrationTrackDTO.getLastupdProg()))
				cpRegistrationTrack.setvLastupdProg(registrationTrackDTO.getLastupdProg());
			if(BSLUtils.isNotNull(registrationTrackDTO.getUserId()))
				cpRegistrationTrack.setUserId(registrationTrackDTO.getUserId());
			if (BSLUtils.isNotNull(registrationTrackDTO.getLastupdDate()))
				cpRegistrationTrack.setvLastupdDate(registrationTrackDTO.getLastupdDate());
			logger.info("RegTrackBLImpl: errtrack details saving started");
			status = cpRegistrationTrackDao.saveErrTrackDetails(cpRegistrationTrack);
		} catch (Exception e) {
			logger.info("RegTrackBLImpl: error at saving Reg errtrack details");
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return status;
	}

}
