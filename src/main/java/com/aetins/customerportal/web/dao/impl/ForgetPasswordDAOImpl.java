package com.aetins.customerportal.web.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.ForgetPasswordDAO;
import com.aetins.customerportal.web.entity.CpCustomerDetail;

@Repository
public class ForgetPasswordDAOImpl extends GenericDaoImpl<CpCustomerDetail,Long> implements ForgetPasswordDAO{

	
	private Logger logger = LoggerFactory.getLogger(ForgetPasswordDAOImpl.class);
	
	@Resource
	private SessionFactory session;
	
	@Override
	protected Session getSession() {
		return session.getCurrentSession();
	}
	@Override
	protected Class<CpCustomerDetail> getEntityClass() {
		return CpCustomerDetail.class;
	}
	
	
	// added by Nithiya
	@Override
	public List<CpCustomerDetail> secureDetailsForgetPassword(CpCustomerDetail verifyUserLogin) {

		String colCustRefNo[] = { "ncustRefNo" };
		int colData[] = { verifyUserLogin.getNcustRefNo() };

		return findByColumnName(colCustRefNo, colData);
	}
}
