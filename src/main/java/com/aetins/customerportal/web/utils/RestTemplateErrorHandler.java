package com.aetins.customerportal.web.utils;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

/**
 * custom implementation of Rest Template to error response
 * @author avinash
 *
 */
public class RestTemplateErrorHandler implements ResponseErrorHandler{

	private static final Logger logger = Logger.getLogger(RestTemplateErrorHandler.class); 
	
	@Override
	public void handleError(ClientHttpResponse clienthttpresponse) throws IOException {
		
		if (clienthttpresponse.getStatusCode() == HttpStatus.FORBIDDEN) {
            logger.debug(HttpStatus.FORBIDDEN + " response. Throwing authentication exception");
            throw new RuntimeException("RestTemplate Exception: "+clienthttpresponse.getStatusCode());
        }
	}

	@Override
	public boolean hasError(ClientHttpResponse clienthttpresponse) throws IOException {
		
		if (clienthttpresponse.getStatusCode() != HttpStatus.OK) {
            logger.debug("Status code: " + clienthttpresponse.getStatusCode());
            logger.debug("Response" + clienthttpresponse.getStatusText());
            logger.debug(clienthttpresponse.getBody());

            if (clienthttpresponse.getStatusCode() == HttpStatus.FORBIDDEN) {
                logger.debug("Call returned a error 403 forbidden resposne ");
                return true;
            }
        }
		return false;
	}

	
	
}
