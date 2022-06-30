package com.aetins.customerportal.web.utils;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Helper class for api calls
 * @author avinash
 *
 */
public class RestTemplateUtils {

	
public static RestTemplate restTemplate() {
		
		return new RestTemplate();
	}
	
     public static RestTemplate restTemplate(SimpleClientHttpRequestFactory requestFactory) {
    	 return new RestTemplate(requestFactory);
     }
     
}
