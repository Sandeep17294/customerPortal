package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpCustomerDetail;

public interface CustomerDetailsDAO extends IGenericDao<CpCustomerDetail, Long>{
	
	List<CpCustomerDetail> customerDetails(int custRefNo);
	
	List<CpCustomerDetail> getcustomerDetails(int custRefNo);


}
