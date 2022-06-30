package com.aetins.customerportal.web.audittrails.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.audittrails.dao.PasswordManagementAudiTrailsDao;
import com.aetins.customerportal.web.audittrails.service.PasswordManagementService;
import com.aetins.customerportal.web.entity.CpPasswordAuditTrails;

/**
 * @author avinash
 *
 */
@Service
public class PasswordManagementServiceImpl implements PasswordManagementService{
	
	@Autowired
	PasswordManagementAudiTrailsDao passwordDao;

	@Override
	public void savePasswordAuditTrail(CpPasswordAuditTrails pswdAuditTrails) {
		passwordDao.save(pswdAuditTrails);
		
	}

	@Override
	public List<CpPasswordAuditTrails> fetchAll() {
		
		return passwordDao.findAll();
	}

	@Override
	public void updatePasswordAuditTrail(CpPasswordAuditTrails pswd) {
		passwordDao.update(pswd);
		
	}

	@Override
	public CpPasswordAuditTrails finbByUserName(String username) {
		
		return passwordDao.findByUsername(username);
	}

}
