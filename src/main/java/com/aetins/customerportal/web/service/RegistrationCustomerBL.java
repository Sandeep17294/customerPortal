package com.aetins.customerportal.web.service;

import java.util.List;

import com.aetins.customerportal.web.dto.RegistrationCustomerDTO;
import com.aetins.customerportal.web.dto.ResetSecurityAnswerDTO;



/**
 * @author satendra
 * @since 2/1/2017
 */
public interface RegistrationCustomerBL {

    /**
     * method to save user details
     * 
     * @param registrationCustomerDTO
     * @return
     */
    public void saveUserDetails(RegistrationCustomerDTO registrationCustomerDTO);
    
    List <?> getAllData();
    
    Object findByID(Integer abc);
    
    public void saveRegistraionQuestionDetails(List<ResetSecurityAnswerDTO> registrationCustomerDTO);
    
    
}
