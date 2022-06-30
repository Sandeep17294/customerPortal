package com.aetins.customerportal.web.service;

import java.util.List;

import com.aetins.customerportal.web.entity.VerificationToken;

/**
 * Holds the business logic user registration verification token.
 * @author avinash
 *
 */
public interface RegistrationVerifyToken {

	public void createRegistrationToken(VerificationToken token);
	
	public List<VerificationToken> getVerificationTokenByUserID(Long id);
	
	public VerificationToken fetchByToken(String token);
	
	public String validateVerificationToken(String token);
}
