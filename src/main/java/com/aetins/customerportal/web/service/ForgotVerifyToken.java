package com.aetins.customerportal.web.service;

import java.util.List;

import com.aetins.customerportal.web.entity.ForgotVerificationToken;

/**
 * Holds the business logic user registration verification token.
 * @author avinash
 *
 */
public interface ForgotVerifyToken {

	public void createRegistrationToken(ForgotVerificationToken token);
	
	public List<ForgotVerificationToken> getVerificationTokenByUserID(Long id);
	
	public ForgotVerificationToken fetchByToken(String token);
	
	public String validateVerificationToken(String token);
}
