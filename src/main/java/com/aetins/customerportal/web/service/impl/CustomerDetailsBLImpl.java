package com.aetins.customerportal.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.dao.CustomerDetailsDAO;
import com.aetins.customerportal.web.dao.impl.CustomerDetailsDAOImpl;
import com.aetins.customerportal.web.dto.CpCustomerDetailDTO;
import com.aetins.customerportal.web.entity.CpCustomerDetail;
import com.aetins.customerportal.web.service.CustomerDetailsBL;

@Service
public class CustomerDetailsBLImpl implements CustomerDetailsBL {

	private static final Logger logger = Logger.getLogger(CustomerDetailsDAO.class);
	
	@Autowired
	CustomerDetailsDAO adminLoginDAO;
	 
	
	@Override
	public List<CpCustomerDetailDTO> customerDetails(int custRefNo) {
		List<CpCustomerDetail> custDetailsList=adminLoginDAO.customerDetails(custRefNo);
		List<CpCustomerDetailDTO> custAllDetailsList= new ArrayList<CpCustomerDetailDTO>();
		for (CpCustomerDetail cpCustomerDetail : custDetailsList) {
			CpCustomerDetailDTO cpCustomerDetailDTO =new CpCustomerDetailDTO();
			cpCustomerDetailDTO.setNid(cpCustomerDetail.getNid());
			cpCustomerDetailDTO.setNcustRefNo(cpCustomerDetail.getNcustRefNo());
			cpCustomerDetailDTO.setVgroup(cpCustomerDetail.getVgroup());
			cpCustomerDetailDTO.setVemail(cpCustomerDetail.getVemail());
			cpCustomerDetailDTO.setNidNo(cpCustomerDetail.getVidNo());
			cpCustomerDetailDTO.setVidType(cpCustomerDetail.getVidType());
			cpCustomerDetailDTO.setVcustName(cpCustomerDetail.getVcustName());
			cpCustomerDetailDTO.setdDob(cpCustomerDetail.getdDob());
			cpCustomerDetailDTO.setdDobHijri(cpCustomerDetail.getdDobHijri());
			cpCustomerDetailDTO.setTitle(cpCustomerDetail.getTitle());
			cpCustomerDetailDTO.setGender(cpCustomerDetail.getGender());
			custAllDetailsList.add(cpCustomerDetailDTO);
		}
		logger.info("Size of Customer list:::::"+custAllDetailsList.size());
		return custAllDetailsList;
		
	
	}
	
	@Override
	public List<CpCustomerDetail> getcustomerDetails(int custRefNo) {
		List<CpCustomerDetail> listing = new ArrayList<CpCustomerDetail>();
		try {
			listing=adminLoginDAO.getcustomerDetails(custRefNo);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return listing;
	}
	
	
}
