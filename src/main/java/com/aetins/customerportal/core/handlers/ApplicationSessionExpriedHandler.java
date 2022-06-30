package com.aetins.customerportal.core.handlers;

import javax.faces.application.FacesMessage;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationSessionExpriedHandler {

	private Logger LOGGER = LoggerFactory.getLogger(ApplicationSessionExpriedHandler.class);
	
	@RequestMapping("/sessionexpried")
	public void handleSessionExpired() {
		LOGGER.info("Session expired");
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message","Session Expried ");
		PrimeFaces.current().dialog().showMessageDynamic(message);
	}
	
}
