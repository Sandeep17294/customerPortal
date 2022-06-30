package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpCustomerDetail;

public interface ForgetPasswordDAO extends IGenericDao<CpCustomerDetail, Long>{

	List<CpCustomerDetail> secureDetailsForgetPassword(CpCustomerDetail cpCustomerDetail);

}