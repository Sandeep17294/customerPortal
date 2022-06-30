package com.aetins.customerportal.core.events.registration;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.entity.VerificationToken;

/**
 * On success registration event
 * @author avinash
 *
 */
@SuppressWarnings("serial")
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private final String appUrl;
    private final Locale locale;
    private final CpUserInfo user;
    private final VerificationToken verificationToken;

    public OnRegistrationCompleteEvent(final CpUserInfo  user,final VerificationToken verifyToken,  final Locale locale, final String appUrl) {
        super(user);
        this.user = user;
        this.verificationToken = verifyToken;
        this.locale = locale;
        this.appUrl = appUrl;
    }

    
    public String getAppUrl() {
        return appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

	public CpUserInfo getUser() {
		return user;
	}

	public VerificationToken getVerificationToken() {
		return verificationToken;
	}

	
}
