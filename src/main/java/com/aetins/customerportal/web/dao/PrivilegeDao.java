package com.aetins.customerportal.web.dao;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.Privilege;

/**
 * @author avinash
 *
 */
public interface PrivilegeDao extends IGenericDao<Privilege, Long>{

	/**
	 * save privilege
	 * @param privilege
	 * @return
	 */
	public Privilege savePrivilege(Privilege privilege);
	
	/**
	 * find privilege by name
	 * @param privilege
	 * @return
	 */
	Privilege findByName(String privilege);
}
