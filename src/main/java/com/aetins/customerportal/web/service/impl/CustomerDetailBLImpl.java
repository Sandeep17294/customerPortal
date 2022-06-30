package com.aetins.customerportal.web.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aetins.customerportal.web.dao.RegistrationCustomerDAO;
import com.aetins.customerportal.web.dto.RegistrationCustomerDTO;
import com.aetins.customerportal.web.entity.CpCustomerDetail;
import com.aetins.customerportal.web.service.CustomerDetailBL;

@Service
public class CustomerDetailBLImpl implements CustomerDetailBL {

	@Autowired
    private RegistrationCustomerDAO registrationCustomerDAO;
	
    CpCustomerDetail cpCustomerDetail = new CpCustomerDetail();

    @Override
    @Transactional
    public void saveCustomerDetails(RegistrationCustomerDTO registrationCustomerDTO) {
	
	cpCustomerDetail.setdDob(new Date());
	cpCustomerDetail.setdDobHijri(new Date());
	//cpCustomerDetail.setNcustRefNo(registrationCustomerDTO.getCustomerReferenceNumber().toString());
	cpCustomerDetail.setVcustName(registrationCustomerDTO.getFirstName() + " " + registrationCustomerDTO.getLastName());
	cpCustomerDetail.setVgroup(registrationCustomerDTO.getGroup());
	cpCustomerDetail.setVidNo(registrationCustomerDTO.getIdenNo());
	cpCustomerDetail.setVidType(registrationCustomerDTO.getIdenCode());
	cpCustomerDetail.setVemail(registrationCustomerDTO.getEmailId());

	//registrationCustomerDAO.save(cpCustomerDetail);
    }

	/*
	 * public RegistrationCustomerDAO<CpCustomerDetail, Integer>
	 * getRegistrationCustomerDAO() { return registrationCustomerDAO; }
	 * 
	 * public void
	 * setRegistrationCustomerDAO(RegistrationCustomerDAO<CpCustomerDetail, Integer>
	 * registrationCustomerDAO) { this.registrationCustomerDAO =
	 * registrationCustomerDAO; }
	 */

}
