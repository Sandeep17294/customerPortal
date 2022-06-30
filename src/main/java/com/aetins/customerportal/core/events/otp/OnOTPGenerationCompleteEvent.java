package com.aetins.customerportal.core.events.otp;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import com.aetins.customerportal.web.entity.CpUserInfo;

/**
 * @author avinash
 *
 */
@SuppressWarnings("serial")
public class OnOTPGenerationCompleteEvent extends ApplicationEvent{

	private CpUserInfo userInfo;
	
	private Locale locale;
	
	
	public OnOTPGenerationCompleteEvent(final CpUserInfo user,final Locale locale) {
		super(user);
		this.userInfo = user;
		this.locale = locale;
	}


	public CpUserInfo getUserInfo() {
		return userInfo;
	}


	public void setUserInfo(CpUserInfo userInfo) {
		this.userInfo = userInfo;
	}


	public Locale getLocale() {
		return locale;
	}


	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	
}
