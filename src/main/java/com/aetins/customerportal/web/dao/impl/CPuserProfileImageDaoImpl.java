package com.aetins.customerportal.web.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.IUserProfileImageDao;
import com.aetins.customerportal.web.entity.CPuserProfileImage;

/**
 * @author avinash
 *
 */
@Repository
public class CPuserProfileImageDaoImpl extends GenericDaoImpl<CPuserProfileImage, Long> implements IUserProfileImageDao{

	private Logger logger = LoggerFactory.getLogger(CPuserProfileImageDaoImpl.class);

	@Resource
	SessionFactory sessionFactory;

	@Override
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	protected Class<CPuserProfileImage> getEntityClass() {
		return CPuserProfileImage.class;
	}
	@Override
	public CPuserProfileImage loadUserByUserName(String username) {
		Criteria criteria = getSession().createCriteria(CPuserProfileImage.class);
		logger.info("Find CPuserProfileImage by name : " + username);
		return (CPuserProfileImage) criteria.add(Restrictions.eq("username", username).ignoreCase()).uniqueResult();
	}

	@Override
	public boolean saveProfileImage(CPuserProfileImage user) {
		boolean isSaved = Boolean.TRUE;
		try {
			saveOrUpdate(user);
			logger.info("User profile image saved successfully: " + user);
		} catch (Exception e) {
			e.printStackTrace();
			isSaved = Boolean.FALSE;
			logger.error("Exception caught while saving the profile image: "+e.getCause());
		}
		return isSaved;
	}

	@Override
	public boolean updateProfileImage(CPuserProfileImage image) {
		boolean isUpdated = Boolean.TRUE;
		try {
			saveOrUpdate(image);
			logger.info("User profile image updated successfully: " + image);
		} catch (Exception e) {
			e.printStackTrace();
			isUpdated = Boolean.FALSE;
			logger.error("Exception caught while updating the profile image: "+e.getCause());
		}
		return isUpdated;
	}


}
