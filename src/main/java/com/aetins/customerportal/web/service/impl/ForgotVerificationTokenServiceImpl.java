package com.aetins.customerportal.web.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.dao.ForgotVerificationTokenServiceDao;
import com.aetins.customerportal.web.dao.RegistrationVerificationTokenServiceDao;
import com.aetins.customerportal.web.entity.ForgotVerificationToken;
import com.aetins.customerportal.web.entity.VerificationToken;
import com.aetins.customerportal.web.service.ForgotVerifyToken;
import com.aetins.customerportal.web.service.RegistrationVerifyToken;

@Service
public class ForgotVerificationTokenServiceImpl  implements ForgotVerifyToken{

	@Autowired
	private ForgotVerificationTokenServiceDao tokenServiceDao;
	
	public static final String TOKEN_INVALID = "invalidToken";
    public static final String TOKEN_EXPIRED = "expired";
    public static final String TOKEN_VALID = "valid";
	
	@Override
	public void createRegistrationToken(ForgotVerificationToken token) {
		
		tokenServiceDao.save(token);
	}

	@Override
	public List<ForgotVerificationToken> getVerificationTokenByUserID(Long id) {
		
		return tokenServiceDao.fetchTokenByUserId(id);
	}

	@Override
	public ForgotVerificationToken fetchByToken(String token) {
		
		return tokenServiceDao.findByToken(token);
	}

	@Override
	public String validateVerificationToken(String token) {
		
		final ForgotVerificationToken verificationToken = tokenServiceDao.findByToken(token);
        if (verificationToken == null) {
            return TOKEN_INVALID;
        }

        //final User user = verificationToken.getUser();
        final Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate()
            .getTime()
            - cal.getTime()
                .getTime()) <= 0) {
        	tokenServiceDao.deleteVerificationToken(verificationToken);
            return TOKEN_EXPIRED;
        }

        //userRepository.save(user);
        return TOKEN_VALID;
		
	}

}
