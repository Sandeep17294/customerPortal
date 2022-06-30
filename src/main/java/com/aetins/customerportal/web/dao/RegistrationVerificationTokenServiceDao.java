package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.VerificationToken;

/**
 * Handle data persistence of user registration token 
 * @author avinash
 *
 */
public interface RegistrationVerificationTokenServiceDao extends IGenericDao<VerificationToken, Long>{

	
	/* (non-Javadoc)
	 * @see com.aetins.customerportal.web.common.IGenericDao#save(java.lang.Object)
	 */
	public void createRegistration(VerificationToken token);
	
	/**
	 * fetch verification token based on user id. 
	 * @param id
	 * @return
	 */
	public List<VerificationToken> fetchTokenByUserId(Long id);
	
	/**
	 * @param token
	 */
	public void saveOrUpdate(VerificationToken token);
	
	/* (non-Javadoc)
	 * @see com.aetins.customerportal.web.common.IGenericDao#update(java.lang.Object)
	 */
	public void update(VerificationToken token);
	
	
	/**
	 * findbyToken 
	 * @param token
	 * @return
	 */
	public VerificationToken findByToken(String token);
	
	/**
	 * delete verification token
	 * @param token
	 */
	public void deleteVerificationToken(VerificationToken token);
}
