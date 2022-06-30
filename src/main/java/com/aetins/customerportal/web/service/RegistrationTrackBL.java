package com.aetins.customerportal.web.service;

import com.aetins.customerportal.web.dto.RegistrationTrackDTO;

public interface RegistrationTrackBL {
	
	public boolean saveErrTrackDetails(RegistrationTrackDTO registrationTrackDTO) throws Exception;

}
