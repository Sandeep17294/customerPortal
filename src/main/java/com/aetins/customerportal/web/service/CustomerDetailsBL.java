package com.aetins.customerportal.web.service;

import java.util.List;

import com.aetins.customerportal.web.dto.CpCustomerDetailDTO;
import com.aetins.customerportal.web.entity.CpCustomerDetail;

public interface CustomerDetailsBL {
	

	List<CpCustomerDetailDTO> customerDetails(int custRefNo);
	
	List<CpCustomerDetail> getcustomerDetails(int custRefNo);

}
