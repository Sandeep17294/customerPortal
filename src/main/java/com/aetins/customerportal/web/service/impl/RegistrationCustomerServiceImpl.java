package com.aetins.customerportal.web.service.impl;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.text.WordUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.dto.ListItem;
import com.aetins.customerportal.web.dto.RegistrationCustomerDTO;
import com.aetins.customerportal.web.service.RegistrationCustomerService;
import com.aetins.customerportal.web.utils.AppSettingURL;
import com.aetins.customerportal.web.utils.BSLUtils;

import salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub;
import salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnCustomerLov;
import salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnCustomerLovResponse;
import salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnGetMasterLov;
import salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnGetMasterLovResponse;
import salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnGetServiceNo;
import salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnGetServiceNoResponse;
import salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnVerifyCustomer;
import salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.BfnVerifyCustomerResponse;
import salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.CustomerIdenTypeUser;
import salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.CustomerIdenTypeUserArray;
import salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.CustomerRegisterInfoTypeUser;
import salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.MasterLovTypeUser;
import salama_cust_portal_oracle_.phaseoneservice_wsdl.PhaseOneServiceStub.MasterLovTypeUserArray;



/**
 * @author satendra
 * @since 21/01/2017
 *
 */
@SuppressWarnings("rawtypes")
@Service
public class RegistrationCustomerServiceImpl implements RegistrationCustomerService {

    private static final Logger logger = Logger.getLogger(RegistrationCustomerServiceImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see com.aetins.webservices.client.service.RegistrationCustomerService#
     * getServiceProxy()
     */
    @Override
    public PhaseOneServiceStub getServiceProxy() throws Exception {
	return new PhaseOneServiceStub(AppSettingURL.FINAL_URL);
    }
    
    
    
    
    /*
     * Getting Email And Mobile No to dto on Passing Identification Type and Iden NO  (non-Javadoc)
     * 
     * @see com.aetins.webservices.client.service.RegistrationCustomerService#
     * getVerifyDetails(com.aetins.webservices.DTO.RegistrationCustomerDTO)
     */
    
	public RegistrationCustomerDTO getCustDetails(String language, String idenType, String idenNO) {

		CustomerRegisterInfoTypeUser customerRegisterInfoTypeUser = new CustomerRegisterInfoTypeUser();
		RegistrationCustomerDTO registrationCustomerDTO = new RegistrationCustomerDTO();
		BfnVerifyCustomer bfnVerifyCustomer = new BfnVerifyCustomer();

		bfnVerifyCustomer.setPIdenCode(idenType);
		bfnVerifyCustomer.setPIdenNo(idenNO);
		bfnVerifyCustomer.setPLanguage(language);
		try {
			BfnVerifyCustomerResponse bfnVerifyCustomerResponse = getServiceProxy().bfnVerifyCustomer(bfnVerifyCustomer);
			customerRegisterInfoTypeUser = bfnVerifyCustomerResponse.getResult();
			registrationCustomerDTO.setCustomerReferenceNumber(customerRegisterInfoTypeUser.getCustRefNo());
			registrationCustomerDTO.setEmailID(customerRegisterInfoTypeUser.getEmailId());
			registrationCustomerDTO.setMobileNumber(customerRegisterInfoTypeUser.getMobileNo());
			registrationCustomerDTO.setEnglishDOB(customerRegisterInfoTypeUser.getEnglishDob());
			registrationCustomerDTO.setHijriDOB(BSLUtils.isNotNull(customerRegisterInfoTypeUser.getHijriDob())?customerRegisterInfoTypeUser.getHijriDob():"");
			registrationCustomerDTO.setGender(customerRegisterInfoTypeUser.getGender());
			registrationCustomerDTO.setTitle(customerRegisterInfoTypeUser.getTitle());
			registrationCustomerDTO.setFirstName(customerRegisterInfoTypeUser.getFirstName());
			registrationCustomerDTO.setLastName(customerRegisterInfoTypeUser.getLastName());
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error to get customer details" + e.getStackTrace());
			return registrationCustomerDTO;
		}
		return registrationCustomerDTO;
	}
    	
    /*
     * Getting data to dto (non-Javadoc)
     * 
     * @see com.aetins.webservices.client.service.RegistrationCustomerService#
     * getVerifyDetails(com.aetins.webservices.DTO.RegistrationCustomerDTO)
     */
    @Override
    public RegistrationCustomerDTO getVerifyDetails(RegistrationCustomerDTO registrationCustomerDTO) {

	CustomerRegisterInfoTypeUser customerRegisterInfoTypeUser = new CustomerRegisterInfoTypeUser();

	BfnVerifyCustomer bfnVerifyCustomer = new BfnVerifyCustomer();
	bfnVerifyCustomer.setPLanguage(registrationCustomerDTO.getLanguageCode());
	bfnVerifyCustomer.setPIdenCode(registrationCustomerDTO.getIdenCode());
	bfnVerifyCustomer.setPIdenNo(registrationCustomerDTO.getIdenNo());
	bfnVerifyCustomer.setPEmailId(registrationCustomerDTO.getEmailId());
	bfnVerifyCustomer.setPMobileNo(registrationCustomerDTO.getMobileNo());
	try {
	    BfnVerifyCustomerResponse bfnVerifyCustomerResponse = getServiceProxy().bfnVerifyCustomer(bfnVerifyCustomer);

	    customerRegisterInfoTypeUser = bfnVerifyCustomerResponse.getResult();

	    registrationCustomerDTO.setCustomerReferenceNumber(customerRegisterInfoTypeUser.getCustRefNo());
	    registrationCustomerDTO.setEmailID(customerRegisterInfoTypeUser.getEmailId());
	    registrationCustomerDTO.setEnglishDOB(customerRegisterInfoTypeUser.getEnglishDob());
	    registrationCustomerDTO.setFirstName(customerRegisterInfoTypeUser.getFirstName());
	    registrationCustomerDTO.setHijriDOB(customerRegisterInfoTypeUser.getHijriDob());
	    registrationCustomerDTO.setLastName(customerRegisterInfoTypeUser.getLastName());
	    registrationCustomerDTO.setMobileNumber(customerRegisterInfoTypeUser.getMobileNo());
	    registrationCustomerDTO.setTitle(customerRegisterInfoTypeUser.getTitle());
	    registrationCustomerDTO.setGender(customerRegisterInfoTypeUser.getGender());
	} catch (Exception e) {
	    logger.error("Error to get customer details" + e.getStackTrace());
	    return registrationCustomerDTO;
	}

	return registrationCustomerDTO;
    }

    /*
     * Getting identity type and code (non-Javadoc)
     * 
     * @see com.aetins.webservices.client.service.RegistrationCustomerService#
     * getIdentificationDetails(java.lang.String, java.lang.String)
     */
    @Override
    public Map getIdentificationDetails(String pListType, String pLanguage) {
	Map<String, String> identificationMap = new LinkedHashMap<String, String>();

	BfnCustomerLov bfnCustomerLov = new BfnCustomerLov();

	CustomerIdenTypeUserArray customerIdenTypeUserArray = new CustomerIdenTypeUserArray();
	CustomerIdenTypeUser[] customerIdenTypeUser;

	bfnCustomerLov.setPListType(pListType);
	bfnCustomerLov.setPLanguage(pLanguage);

	try {
	    BfnCustomerLovResponse bfnCustomerLovResponse = getServiceProxy().bfnCustomerLov(bfnCustomerLov);
	    customerIdenTypeUserArray = bfnCustomerLovResponse.getResult();
	    customerIdenTypeUser = customerIdenTypeUserArray.getCustomerIdenTypeUser();

	    for (int i = 0; i < customerIdenTypeUser.length; i++) {
		identificationMap.put(
			WordUtils.capitalize(customerIdenTypeUser[i].getIdentificationDesc().toLowerCase()),
			customerIdenTypeUser[i].getIdentificationCode());
	    }

	} catch (Exception e) {

	    logger.error("Error to get identification code" + e.getStackTrace());
	    e.printStackTrace();
	    return identificationMap;
	}

	return identificationMap;

    }
    
    
    @Override
    public Map getMasterLOVs(String pListType, String pLanguage){
    	Map<String, String> masterLOVMap = new TreeMap<String, String>();
    	
    	MasterLovTypeUserArray masterLovTypeUserArray = new MasterLovTypeUserArray();
    	MasterLovTypeUser[] masterLovTypeUser;
    	BfnGetMasterLov bfnGetMasterLov = new BfnGetMasterLov();
    	bfnGetMasterLov.setPLanguage(pLanguage);
    	bfnGetMasterLov.setPType(pListType);
    	try{
    		BfnGetMasterLovResponse bfnGetMasterLovResponse = getServiceProxy().bfnGetMasterLov(bfnGetMasterLov);    		
    		masterLovTypeUserArray = bfnGetMasterLovResponse.getResult();
    		masterLovTypeUser = masterLovTypeUserArray.getMasterLovTypeUser();
    		
			for (int i = 0; i < masterLovTypeUser.length; i++) {
				masterLOVMap.put(WordUtils.capitalize(masterLovTypeUser[i].getDescOne().toLowerCase()), masterLovTypeUser[i].getCode());
			}			
    	}catch(Exception e){
    		 logger.error("Error to get identification code" + e.getStackTrace());
    		 e.printStackTrace();
    		 return masterLOVMap;
    	}
    	return masterLOVMap;
    }
    
    
    @Override
    public Map getCountryCodeLOVs(String pListType, String pLanguage){
    	Map<String, String> masterLOVMap = new LinkedHashMap<>();
    	
    	MasterLovTypeUserArray masterLovTypeUserArray = new MasterLovTypeUserArray();
    	MasterLovTypeUser[] masterLovTypeUser;
    	BfnGetMasterLov bfnGetMasterLov = new BfnGetMasterLov();
    	bfnGetMasterLov.setPLanguage(pLanguage);
    	bfnGetMasterLov.setPType(pListType);
    	try{
    		BfnGetMasterLovResponse bfnGetMasterLovResponse = getServiceProxy().bfnGetMasterLov(bfnGetMasterLov);    		
    		masterLovTypeUserArray = bfnGetMasterLovResponse.getResult();
    		masterLovTypeUser = masterLovTypeUserArray.getMasterLovTypeUser();
    		
    		System.out.println("Lov Master : "+ bfnGetMasterLovResponse.getResult().toString());
    		System.out.println("Lov Master 2 : "+ masterLovTypeUser.length);
    		
			for (int i = 0; i < masterLovTypeUser.length; i++) {
				masterLOVMap.put(WordUtils.capitalize(masterLovTypeUser[i].getDescOne().toLowerCase()), masterLovTypeUser[i].getCode());
				//masterLOVMap.put(WordUtils.capitalize(masterLovTypeUser[i].getDescOne().toLowerCase()), masterLovTypeUser[i].getCode());
			}			
    	}catch(Exception e){
    		 logger.error("Error to get identification code" + e.getStackTrace());
    		 e.printStackTrace();
    		 return masterLOVMap;
    	}
    	return masterLOVMap;
    }




	@Override
	public List getpostcode(String lang, String type, String policyNo, String keyTwo) {

		List<ListItem> postevents = new ArrayList<ListItem>();
		MasterLovTypeUserArray valuesArray = new MasterLovTypeUserArray();
		MasterLovTypeUser[] masterLovTypeUserArray;
		MasterLovTypeUser masterLovTypeUser;
		try {
			BfnGetMasterLov customerElement = new BfnGetMasterLov();
			customerElement.setPLanguage(lang);
			customerElement.setPType(type);
			customerElement.setPSearchKeyOne(policyNo);
		    customerElement.setPSearchKeyTwo(keyTwo);
			BfnGetMasterLovResponse res = getServiceProxy().bfnGetMasterLov(customerElement);
			valuesArray = res.getResult();
			masterLovTypeUserArray = valuesArray.getMasterLovTypeUser();
			if (BSLUtils.isNotNull(masterLovTypeUserArray))
				for (int i = 0; i < masterLovTypeUserArray.length; i++) {
					masterLovTypeUser = masterLovTypeUserArray[i];
					ListItem events = new ListItem();
					events.setCode(masterLovTypeUser.getCode());
					events.setDescription(masterLovTypeUser.getDescOne());
					postevents.add(events);
				}
			logger.info(":::::Result for getpostcode");
		} catch (Exception e) {
			e.getMessage();
		}
		return postevents;
	}




	@Override
	public BigDecimal getServiceRequestNo() throws Exception {
		BfnGetServiceNo serviceNo = new BfnGetServiceNo();
		BfnGetServiceNoResponse  response = getServiceProxy().bfnGetServiceNo(serviceNo);
		BigDecimal result = response.getResult();
		return result;
	}


}
