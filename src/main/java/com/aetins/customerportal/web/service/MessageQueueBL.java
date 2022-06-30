package com.aetins.customerportal.web.service;

import java.util.List;

import com.aetins.customerportal.web.entity.CpUserInfo;

/**
 * To post messages to restapi for JMS Queue
 * @author avinash
 *
 */
public interface MessageQueueBL {
	
	/**
	 * To send bulk email's to JMS queue
	 * @author avinash
	 * @param userInfo
	 * @return response
	 */
	public String sendMailTOQueue(List<CpUserInfo> userInfo,String emailNotify,String attempts);
	
	/**
	 * To send bulk sms to JMS queue
	 * @author avinash
	 * @param userInfo
	 * @return response
	 */
	public String sendSMSTOQueue(List<CpUserInfo> userInfo,String emailNotify,String attempts);

}
