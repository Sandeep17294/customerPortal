package com.aetins.customerportal.core.services.impl;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.core.services.IOTPService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class OTPServiceImpl implements IOTPService {
	
	
	private Logger LOGGER = LoggerFactory.getLogger(OTPServiceImpl.class);

	private LoadingCache<String, Integer> otpCache;
	
	private static final Integer EXPIRE_MINS = 5;
	
	public OTPServiceImpl() {
		
		otpCache = CacheBuilder.newBuilder().expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES)
				.build(new CacheLoader<String, Integer>() {
					public Integer load(String key) {
						return 0;
						}});
	}
	
	public int generateOTP(String key) {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		otpCache.put(key, otp);
		LOGGER.info("OTP cache key value pair username: {}, Otp: {}",key,otp);
		return otp;
	}

	public int getOtp(String key) {
		try {
			return otpCache.get(key);
		} catch (Exception e) {
			return 0;
		}
	}

	public void clearOTP(String key) {
		otpCache.invalidate(key);
	}
	
}
