package com.aetins.customerportal.web.automation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aetins.customerportal.core.services.IOTPService;
import com.aetins.customerportal.core.services.IRegistrationLink;

@RestController
@RequestMapping(value = "/automation")
public class AutomationTestController {

	private static Logger _LOGGER = LoggerFactory.getLogger(AutomationTestController.class);
	
	@Autowired
	private IOTPService otpService;
	
	@Autowired
    private IRegistrationLink registrationLink;
	
	
	@GetMapping(value = "/otp")
	public ResponseEntity<String> getOTP(@RequestParam String username) {
		
		_LOGGER.info("OTP Request for username: {}",username);
		int serverOtp = otpService.getOtp(username);
		return new ResponseEntity<String>(serverOtp+"",HttpStatus.OK);
	}
	
	@GetMapping(value = "/registrationLink")
	public ResponseEntity<String> getRegistrationLink(@RequestParam String email) {
		
		String confirmationlink = registrationLink.getConfirmationlink(email);
		
		return new ResponseEntity<String>(confirmationlink,HttpStatus.OK);
	}
	
}
