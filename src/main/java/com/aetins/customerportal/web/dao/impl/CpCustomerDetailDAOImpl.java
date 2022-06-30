package com.aetins.customerportal.web.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.CpCustomerDetailDAO;
import com.aetins.customerportal.web.entity.CpCustomerDetail;
import com.aetins.customerportal.web.entity.CpUserInfo;

/**
 * @author satendra
 *
 */
@Repository
public class CpCustomerDetailDAOImpl extends GenericDaoImpl<CpCustomerDetail, Long> implements CpCustomerDetailDAO {

	private static final Logger logger = Logger.getLogger(CpCustomerDetailDAOImpl.class);
	
	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	protected Class<CpCustomerDetail> getEntityClass() {
		return CpCustomerDetail.class;
	}

	/*
	 * saving data to db (non-Javadoc)
	 * 
	 * @see
	 * com.aetins.webservices.dao.CpCustomerDetailDAO#saveCustomerDetail(com.
	 * aetins.webservices.entity.CpCustomerDetail)
	 */
	@Override
	public void saveCustomerDetail(CpCustomerDetail cpCustomerDetail) {
		
		getSession().persist(cpCustomerDetail);
		logger.info("save successfully");
	}

	/**
	 *find by cust ref by cust ref number
	 */
	@Override
	public CpCustomerDetail findByCustRef(long custRefNo) {
		String[] propertyValues = {"N_CUST_REF_NO"};
		Object[] value = {custRefNo};
		List<CpCustomerDetail> findAllByPropertiesEqual = findAllByPropertiesEqual(propertyValues, value);
		return findAllByPropertiesEqual.get(0);
	}
}
