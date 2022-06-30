package com.aetins.customerportal.core.services;

/**
 * @author avinash
 *
 */
public interface IOTPService {

	
	/**
	 * @return
	 */
	public int generateOTP(String key);
	
	public int getOtp(String key);
	
	public void clearOTP(String key);
}
