package com.aetins.customerportal.web.pojo;

/**
 * {@link MessageQueuePojo} helper POJO to send the messages to JMS queue
 * @author avinash
 *
 */
public class MessageQueuePojo {

	private String username;

	private String userPrefName;

	private String userEmail;

	private String userContact;

	private String userEmailSubject;

	private String userEmailBody;
	
	
	public MessageQueuePojo(String uName,String uPrefName,String uEmail,String uContact,String uEmailSub,String uEmailBody){
		
		this.username = uName;
		this.userPrefName = uPrefName;
		this.userEmail = uEmail;
		this.userContact = uContact;
		this.userEmailSubject = uEmailSub;
		this.userEmailBody = uEmailBody;
	}
	
	/* GETTERS & SETTERS */

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserPrefName() {
		return userPrefName;
	}

	public void setUserPrefName(String userPrefName) {
		this.userPrefName = userPrefName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserContact() {
		return userContact;
	}

	public void setUserContact(String userContact) {
		this.userContact = userContact;
	}

	public String getUserEmailSubject() {
		return userEmailSubject;
	}

	public void setUserEmailSubject(String userEmailSubject) {
		this.userEmailSubject = userEmailSubject;
	}

	public String getUserEmailBody() {
		return userEmailBody;
	}

	public void setUserEmailBody(String userEmailBody) {
		this.userEmailBody = userEmailBody;
	}

	@Override
	public String toString() {
		return "MessageQueuePojo [username=" + username + ", userPrefName=" + userPrefName + ", userEmail=" + userEmail
				+ ", userContact=" + userContact + ", userEmailSubject=" + userEmailSubject + ", userEmailBody="
				+ userEmailBody + "]";
	}
	
	
}
