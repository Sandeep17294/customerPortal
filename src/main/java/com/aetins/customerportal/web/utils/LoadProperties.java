package com.aetins.customerportal.web.utils;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author		Vidyasahari
 * @category	To read properties for Payment GateWay(MIGS) attributes
 * @version 	MiGS Virtual Payment Client Integration/Software version: MR 27.9
 * @since		19/09/2013 
 * **/

public class LoadProperties {
	
	
	Logger logger = LoggerFactory.getLogger(LoadProperties.class); 
	public static void main(String args[]) throws Exception {
		//LoadProperties dBConnection = new LoadProperties();
		try {
			//String result = dBConnection.getMigsURL();
			//logger.info("Final URL::" + result);
		} catch (Exception e) {
			//logger.info("I am @ Exception" + e);

		}
	}

	/*public static String getMigsURL() throws Exception {

		String result = null;
		String vpcURL = null;
		String vpcVersion = null;
		String vpcLocale = null;
		String vpcCommand = null;
		String vpcAccessCode = null;
		String vpcMerchant = null;
		String vpcOrderInfo = null;
		String vpcSECURESECRET = null;
		
		try {
			Properties prop = loadParams("migs");
			
			vpcURL = prop.getProperty("virtualPaymentClientURL");
			vpcVersion = prop.getProperty("vpc_Version");
			vpcLocale = prop.getProperty("vpc_Locale");
			vpcCommand = prop.getProperty("vpc_Command");
			vpcAccessCode = prop.getProperty("vpc_AccessCode");
			vpcMerchant = prop.getProperty("vpc_Merchant");
			vpcOrderInfo = prop.getProperty("vpc_OrderInfo");
			vpcSECURESECRET = prop.getProperty("SECURE_SECRET");
						
			result  = vpcURL+"?vpc_Version="+vpcVersion+"&vpc_Locale="+vpcLocale+
			"&vpc_Command="+vpcCommand+"&vpc_AccessCode="+vpcAccessCode+"&vpc_Merchant="+vpcMerchant+
			"&vpc_OrderInfo="+vpcOrderInfo;
			
			logger.info("Final URL==>"+result);
			
		} catch (Exception e) {
			throw e;
		}
		return result;
	}*/

	public static Properties loadParams(String fileName, Locale locale) {

		Properties prop = new Properties();
		//Locale locale = new Locale("en", "US");
		ResourceBundle bundle = ResourceBundle.getBundle(fileName, locale);
		Enumeration<String> venum = bundle.getKeys();
		String key = null;
		/*try{
			System.out.println("Bundle value String = " + bundle.getString("login.userIdEmpty"));
			System.out.println("Bundle value Object = " + bundle.getObject("login.userIdEmpty"));
		}catch(Exception e){
			System.out.println("Error = " + e.getMessage());
		}*/
		while (venum.hasMoreElements()) {
			key = (String) venum.nextElement();
			prop.put(key, bundle.getObject(key));
		}
		return prop;
	}
	
	public static Properties loadParams(String fileName) {
		return loadParams(fileName, Locale.getDefault());
	}
}
