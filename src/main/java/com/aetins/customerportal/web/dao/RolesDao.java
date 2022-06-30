package com.aetins.customerportal.web.dao;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.Role;

/**
 * @author avinash
 *
 */
public interface RolesDao extends IGenericDao<Role, Long>{

	/**
	 * used to persist roles for respective user
	 * @param roles
	 */
	public Role saveRoles(Role roles);
	
	/**
	 * find role by role name
	 * @param roleName
	 * @return
	 */
	public Role findByName(String roleName);
}
