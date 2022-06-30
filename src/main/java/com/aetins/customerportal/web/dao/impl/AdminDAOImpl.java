package com.aetins.customerportal.web.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.AdminDAO;
import com.aetins.customerportal.web.entity.CpUserInfo;

@Repository
public class AdminDAOImpl extends GenericDaoImpl<CpUserInfo, Long> implements AdminDAO {

	private Logger logger = LoggerFactory.getLogger(AdminDAOImpl.class);

	@Resource
	SessionFactory sessionFactory;

	@Override
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	protected Class<CpUserInfo> getEntityClass() {
		return CpUserInfo.class;
	}

	@Override
	public List<CpUserInfo> listAllUsers() {
		return findAll();
	}

	@Override
	public boolean updateUserStatus(CpUserInfo userInfo) {
		// TODO Auto-generated method stub
		boolean status = Boolean.TRUE;
		logger.info("save Status Start");
		try {

			// update(userInfo);
			updateByColumnName("update CpUserInfo set vactive='" + userInfo.getVactive() + "' where vuserName='"
					+ userInfo.getVuserName() + "'");

		} catch (Exception e) {
			status = Boolean.FALSE;
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean updateUserLockStatus(CpUserInfo userInfo) {
		boolean status = Boolean.TRUE;
		logger.info("save Status Start");
		try {

			// update(userInfo);
			updateByColumnName("update CpUserInfo set vlocked='" + userInfo.getVlocked() + "' where vuserName='"
					+ userInfo.getVuserName() + "'");

		} catch (Exception e) {
			status = Boolean.FALSE;
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

}
