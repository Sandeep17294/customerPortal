package com.aetins.customerportal.core.services;

/**
 * @author avinash
 *
 */
public interface IMailService {
	
	
	/**
	 * send mail 
	 * @param to
	 * @param subject
	 * @param body
	 */
	public void sendMail(String to,String subject,String body);

	
	/**
	 * send mail with attachments 
	 * @param to
	 * @param subject
	 * @param body
	 * @param files
	 */
	public void sendMailWithAttachments(String to,String subject,String body,String content) throws Exception;

    public void smsgenerator(int otp, String contactno, String email);
    
    public void smsgeneratortransaction(String otp, String contactno);

}
