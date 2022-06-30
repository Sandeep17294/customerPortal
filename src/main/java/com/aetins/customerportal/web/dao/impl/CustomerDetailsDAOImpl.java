package com.aetins.customerportal.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.CustomerDetailsDAO;
import com.aetins.customerportal.web.entity.CpCustomerDetail;


@Repository
public class CustomerDetailsDAOImpl extends GenericDaoImpl<CpCustomerDetail, Long> implements CustomerDetailsDAO {

	private static final Logger logger = Logger.getLogger(CustomerDetailsDAOImpl.class);
	
	@Resource
	private SessionFactory session;
	
	@Override
	protected Session getSession() {
		// TODO Auto-generated method stub
		return session.getCurrentSession();
	}
	
	@Override
	public List<CpCustomerDetail> customerDetails(int custRefNo) {
		// TODO Auto-generated method stub
		List<CpCustomerDetail> custDetailsList =new ArrayList<CpCustomerDetail>();
		String[] propNames ={"ncustRefNo"};
	    int[] values={custRefNo};	
		try{
			custDetailsList=findByColumnName(propNames, values);
		}catch (Exception e) {
			
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		logger.info("Size of Customer Details list:::::"+custDetailsList.size());
		return custDetailsList;
		
	}
	
	@Override
	public List<CpCustomerDetail> getcustomerDetails(int custRefNo) {
		List<CpCustomerDetail> custDetailsList =new ArrayList<CpCustomerDetail>();
		String[] propNames ={"ncustRefNo"};
	    int[] values={custRefNo};	
		try{
			custDetailsList=findByColumnName(propNames, values);
		}catch (Exception e) {	
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		return custDetailsList;
	}
	
	
	@Override
	protected Class<CpCustomerDetail> getEntityClass() {
		// TODO Auto-generated method stub
		return CpCustomerDetail.class;
	}
	

	
}
