package com.aetins.customerportal.core.events.pswdManagement;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.entity.ForgotVerificationToken;

/**
 * On success forgot pass event
 * @author avinash
 *
 */
@SuppressWarnings("serial")
public class OnForgotPasswordCompleteEvent extends ApplicationEvent {

    private final String appUrl;
    private final Locale locale;
    private final CpUserInfo user;
    private final ForgotVerificationToken verificationToken;

    public OnForgotPasswordCompleteEvent(final CpUserInfo  user,final ForgotVerificationToken verifyToken,  final Locale locale, final String appUrl) {
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

	public ForgotVerificationToken getVerificationToken() {
		return verificationToken;
	}

    
    
}
