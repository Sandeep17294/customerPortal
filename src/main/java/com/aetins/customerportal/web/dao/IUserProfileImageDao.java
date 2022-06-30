package com.aetins.customerportal.web.dao;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CPuserProfileImage;

/**
 * @author avinash
 *
 */
public interface IUserProfileImageDao extends IGenericDao<CPuserProfileImage, Long>{

	CPuserProfileImage loadUserByUserName(String username);
	
	public boolean saveProfileImage(CPuserProfileImage user);
	
	public boolean updateProfileImage(CPuserProfileImage image);
}
