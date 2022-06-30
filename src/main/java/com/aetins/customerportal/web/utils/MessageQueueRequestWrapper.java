package com.aetins.customerportal.web.utils;

import java.io.Serializable;
import java.util.List;

import com.aetins.customerportal.web.pojo.MessageQueuePojo;

/**
 * @author avinash
 *
 */
public class MessageQueueRequestWrapper implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1484031789359617119L;
	
	
	private List<MessageQueuePojo> messages;

	public List<MessageQueuePojo> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageQueuePojo> messages) {
		this.messages = messages;
	}
	
	
	

}
