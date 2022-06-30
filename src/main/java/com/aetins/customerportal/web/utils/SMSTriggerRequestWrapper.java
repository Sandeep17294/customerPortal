package com.aetins.customerportal.web.utils;

import java.io.Serializable;
import java.util.List;

import com.aetins.customerportal.web.pojo.SMSTriggerPojo;

public class SMSTriggerRequestWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8731267570722290762L;
	
	
	private List<SMSTriggerPojo> smsTrigger;


	public List<SMSTriggerPojo> getSmsTrigger() {
		return smsTrigger;
	}


	public void setSmsTrigger(List<SMSTriggerPojo> smsTrigger) {
		this.smsTrigger = smsTrigger;
	}


	@Override
	public String toString() {
		return "SMSTriggerRequestWrapper [smsTrigger=" + smsTrigger + "]";
	}
	
	
	

}
