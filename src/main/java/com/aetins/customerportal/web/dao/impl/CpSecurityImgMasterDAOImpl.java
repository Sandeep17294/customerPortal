package com.aetins.customerportal.web.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.CpSecurityImgMasterDAO;
import com.aetins.customerportal.web.entity.CpSecurityImgMaster;


@Repository
public class CpSecurityImgMasterDAOImpl extends GenericDaoImpl<CpSecurityImgMaster, Long>
		implements CpSecurityImgMasterDAO {

	private static final Logger logger = Logger.getLogger(CpSecurityImgMasterDAOImpl.class);

	@Resource
	private SessionFactory sessionFactory;

	@Override
	protected Session getSession() {

		return sessionFactory.getCurrentSession();
	}

	@Override
	protected Class<CpSecurityImgMaster> getEntityClass() {
		return CpSecurityImgMaster.class;
	}

	/*
	 * Retrving listof images path from data base (non-Javadoc)
	 * 
	 * @see
	 * com.aetins.webservices.dao.CpSecurityImgMasterDAO#listSecurityImgMaster()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CpSecurityImgMaster> listSecurityImgMaster() {

		List<CpSecurityImgMaster> cpSecurityImgMasterList = getSession().createQuery("from CpSecurityImgMaster").list();
		for (CpSecurityImgMaster image : cpSecurityImgMasterList) {
			logger.info("Person List::" + image);
		}
		return cpSecurityImgMasterList;

	}

}
