package com.aetins.customerportal.web.dao;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpUserInfo;


/**
 * Data access object interface for {@link CpUserInfo} entity for database operations
 * @author avinash
 *
 */
public interface IUserServiceDao extends IGenericDao<CpUserInfo, Long>{

	
	/**
	 * Queries database for username availability.
	 * @param username
	 * @return true if user exists
	 */
	boolean checkAvaliable(String username);
	
	
	
	/**
	 * Queries for user by username
	 * @param username
	 * @return User entity
	 */
	CpUserInfo loadUserByUserName(String username);
	
}
