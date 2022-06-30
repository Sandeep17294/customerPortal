package com.aetins.customerportal.web.audittrails.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.audittrails.dao.RegistrationAuditTrailDao;
import com.aetins.customerportal.web.audittrails.service.RegistrationAuditTrailService;
import com.aetins.customerportal.web.entity.CpRegistrationAuditTrail;

@Service
public class RegistrationAuditTrailImpl implements RegistrationAuditTrailService {
	
	@Autowired
	private RegistrationAuditTrailDao registrationAuditTrail;

	@Override
	public void saveRegistrationAuditTrail(CpRegistrationAuditTrail regisrationAuditTrail) {
		registrationAuditTrail.save(regisrationAuditTrail);

	}

	@Override
	public List<CpRegistrationAuditTrail> getAllRegistrationAuditTrails() {
		return registrationAuditTrail.fetch();
	}

	@Override
	public CpRegistrationAuditTrail getUserRegistrationAuditTrail(String username) {
		
		return registrationAuditTrail.findByUsername(username);
	}

	@Override
	public void updateRegistrationAuditTrail(CpRegistrationAuditTrail reg) {
		registrationAuditTrail.update(reg);
	}

	@Override
	public void saveORUpdate(CpRegistrationAuditTrail reg) {
		
		registrationAuditTrail.saveOrUpdateRegistrationTrail(reg);
	}

}
