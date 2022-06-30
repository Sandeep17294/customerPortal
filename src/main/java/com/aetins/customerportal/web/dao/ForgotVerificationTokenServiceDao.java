package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.ForgotVerificationToken;
import com.aetins.customerportal.web.entity.VerificationToken;

/**
 * Handle data persistence of user registration token 
 * @author avinash
 *
 */
public interface ForgotVerificationTokenServiceDao extends IGenericDao<ForgotVerificationToken, Long>{

	
	/* (non-Javadoc)
	 * @see com.aetins.customerportal.web.common.IGenericDao#save(java.lang.Object)
	 */
	public void createRegistration(ForgotVerificationToken token);
	
	/**
	 * fetch verification token based on user id. 
	 * @param id
	 * @return
	 */
	public List<ForgotVerificationToken> fetchTokenByUserId(Long id);
	
	/**
	 * @param token
	 */
	public void saveOrUpdate(ForgotVerificationToken token);
	
	/* (non-Javadoc)
	 * @see com.aetins.customerportal.web.common.IGenericDao#update(java.lang.Object)
	 */
	public void update(ForgotVerificationToken token);
	
	
	/**
	 * findbyToken 
	 * @param token
	 * @return
	 */
	public ForgotVerificationToken findByToken(String token);
	
	/**
	 * delete verification token
	 * @param token
	 */
	public void deleteVerificationToken(ForgotVerificationToken token);
}
