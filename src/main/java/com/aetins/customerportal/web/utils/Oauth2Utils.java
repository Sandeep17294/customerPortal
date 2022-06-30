package com.aetins.customerportal.web.utils;

import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Oauth2Utils helper class is to provide utility methods for Oauth2
 * authentication
 * 
 * @author avinash
 *
 */
public class Oauth2Utils {

	private static final Logger logger = LoggerFactory.getLogger(Oauth2Utils.class);

	/**
	 * <i>To get the access token </i>
	 * 
	 * @param event
	 */
	public static String getAccessToken() {

		String jsonResponse = null;

		// Oauth2 token details
		final String clientId = AppSettingURL.OAUTH2_CLIENT_ID;
		final String clientSecret = AppSettingURL.OAUTH2_CLIENT_SECRET;
		final String tokenUrl = AppSettingURL.OAUTH2_TOKEN_URL;

		// final String tokenUrl = "http://localhost:8383/cpapi/oauth/token";
		final String grantType = AppSettingURL.OAUTH2_GRANT_TYPE;
		final String username = AppSettingURL.OAUTH2_USERNAME;
		final String password = AppSettingURL.OAUTH2_PASSWORD;
		final String weblogicUname = AppSettingURL.WEBLOGIC_USERNAME;
		final String weblogicpswd = AppSettingURL.WEBLOGIC_PASSWORD;

		final String tokenDetails = "grant_type=" + grantType + "&username=" + username + "&password=" + password + "";

		// Oauth2 token url
		final String authTokenUrl = String.join("?", tokenUrl, tokenDetails);

		// oauth2 api credentials
		final String oauthauth = clientId + ":" + clientSecret;
		final String oauthAuthentication = Base64.getEncoder().encodeToString(oauthauth.getBytes());

		// weblogic credentials
		final String weblogicauth = weblogicUname + ":" + weblogicpswd;
		final String weblogicAuthentication = Base64.getEncoder().encodeToString(weblogicauth.getBytes());

		// Rest template
		RestTemplate restTemplate = RestTemplateUtils.restTemplate();
		restTemplate.setErrorHandler(new RestTemplateErrorHandler());
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setOutputStreaming(false);
		restTemplate.setRequestFactory(requestFactory);

		// Request Headers with weblogic Authorization
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Authorization", "Basic " + weblogicAuthentication);

		// Request Entity with headers
		//HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestHeaders);
		HttpEntity<MultiValueMap<String, String>> requestEntity = null;

		logger.info("Oauth2 access_token details");
		logger.info("token url:{}", authTokenUrl);

		//1st trigger with weblogic credentials 
		ResponseEntity<String> response = restTemplate.exchange(authTokenUrl, HttpMethod.POST, requestEntity,String.class);
		
		logger.info("1 st trigger response: response body: " + response.getBody() + ", response status : "+ response.getStatusCode());

		jsonResponse = response.getBody();

		// 2 nd trigger by appending cookie for access token with oauth

		if (response.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {

			HttpHeaders responseHeaders = response.getHeaders();

			String set_cookie = responseHeaders.get("Set-Cookie").get(0);

			restTemplate = RestTemplateUtils.restTemplate();
			restTemplate.setErrorHandler(new RestTemplateErrorHandler());
			requestHeaders = new HttpHeaders();
			requestHeaders.add("Authorization", "Basic " + oauthAuthentication);
			requestHeaders.add("Cookie", set_cookie);

			//requestEntity = new HttpEntity<>(requestHeaders);

			response = restTemplate.exchange(authTokenUrl, HttpMethod.POST, requestEntity, String.class);

			logger.info("2 nd trigger response: response body:{}, response code: {}", response.getBody(),response.getStatusCode());

			jsonResponse = response.getBody();
		}

		return jsonResponse;

	}

}
