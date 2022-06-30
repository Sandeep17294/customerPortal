package com.aetins.customerportal.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.UserActivationDAO;
import com.aetins.customerportal.web.entity.CpUserInfo;

@Repository
public class UserActivationDAOImpl extends GenericDaoImpl<CpUserInfo, Long> implements UserActivationDAO {

	private static final Logger logger = Logger.getLogger(UserActivationDAOImpl.class);

	@Resource
	private SessionFactory session;

	@Override
	protected Session getSession() {
		return session.getCurrentSession();
	}

	public List<CpUserInfo> fetchUserDetails(String tokenNo) {
		List<CpUserInfo> cpUserInfoList = new ArrayList<CpUserInfo>();
		logger.info("verifyUserDetails Start");
		CpUserInfo cpuserInfp = new CpUserInfo();
		try {
			String coluName[] = { "vToken" };
			String coluData[] = { tokenNo };
			cpUserInfoList = findByColumeName(coluName, coluData);
		} catch (Exception e) {
			// logger.error("Error occured " + e.getMessage());
		}
		return cpUserInfoList;

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
	protected Class<CpUserInfo> getEntityClass() {
		// TODO Auto-generated method stub
		return CpUserInfo.class;
	}

}
