package com.aetins.customerportal.web.dto;

/**
 * Portal SMS 
 * @author avinash
 *
 */
public class CPPortalSMSDto {
	
	private String msgId;
	private String transId;
	private String sourceAppId;
	private String destAppId;
	private String transDesc;
	private String docCreated;
	private String authKey;
	private String docNum;
	private String recipientNo;
	private String smsCode;
	private String nationality;
	private String name;
	private String value;
	
	
	
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getSourceAppId() {
		return sourceAppId;
	}
	public void setSourceAppId(String sourceAppId) {
		this.sourceAppId = sourceAppId;
	}
	public String getDestAppId() {
		return destAppId;
	}
	public void setDestAppId(String destAppId) {
		this.destAppId = destAppId;
	}
	public String getTransDesc() {
		return transDesc;
	}
	public void setTransDesc(String transDesc) {
		this.transDesc = transDesc;
	}
	public String getDocCreated() {
		return docCreated;
	}
	public void setDocCreated(String docCreated) {
		this.docCreated = docCreated;
	}
	public String getAuthKey() {
		return authKey;
	}
	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}
	public String getDocNum() {
		return docNum;
	}
	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}
	public String getRecipientNo() {
		return recipientNo;
	}
	public void setRecipientNo(String recipientNo) {
		this.recipientNo = recipientNo;
	}
	public String getSmsCode() {
		return smsCode;
	}
	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	

}
