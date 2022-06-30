package com.aetins.customerportal.web.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.aetins.customerportal.web.dto.ListItem;
import com.aetins.customerportal.web.dto.RegistrationCustomerDTO;




public interface RegistrationCustomerService<T> {

    public T getServiceProxy() throws Exception;

    public RegistrationCustomerDTO getVerifyDetails(RegistrationCustomerDTO registrationCustomerDTO);
    
    public RegistrationCustomerDTO getCustDetails(String language, String idenType , String idenNO);

    public Map<String, String> getIdentificationDetails(String pListType, String pLanguage);

    public Map getMasterLOVs(String pListType, String pLanguage);
    
    public Map getCountryCodeLOVs(String pListType, String pLanguage);
    
    public List<ListItem> getpostcode(String lang, String type, String policyNo, String keyTwo);
    
    public BigDecimal getServiceRequestNo() throws Exception;
}
