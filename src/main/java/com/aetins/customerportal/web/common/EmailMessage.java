package com.aetins.customerportal.web.common;

import java.util.ArrayList;
import java.util.List;

public class EmailMessage {
	private String subject;
	private String body;
	private String from;
	private String[] to;
	private String[] cc;
	private String[] bcc;
	private String url;
	private String addEditflag;
	private String tempType;
	private List<String> attachments = new ArrayList<String>();
	
	public List<String> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<String> attachments) {
		this.attachments = attachments;
	}
	public void addAttachment(String fileName){
		this.attachments.add(fileName);
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String[] getTo() {
		return to;
	}
	public void setTo(String[] to) {
		this.to = to;
	}
	public String[] getCc() {
		return cc;
	}
	public void setCc(String[] cc) {
		this.cc = cc;
	}
	public String[] getBcc() {
		return bcc;
	}
	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}
	public String getAddEditflag() {
		return addEditflag;
	}
	public void setAddEditflag(String addEditflag) {
		this.addEditflag = addEditflag;
	}
	public String getTempType() {
		return tempType;
	}
	public void setTempType(String tempType) {
		this.tempType = tempType;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("\nTo = " + getTo());
		sb.append("\nSubject = " + getSubject());
		sb.append("\nBody = " + getBody());
		sb.append("\nUrl = " + getUrl());
		
		return sb.toString();
	}
}
