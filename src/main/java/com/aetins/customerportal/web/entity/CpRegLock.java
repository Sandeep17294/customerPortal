package com.aetins.customerportal.web.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CP_REG_LOCK")
public class CpRegLock implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8660786154731378475L;
	
	private int sno;
    private String identype;
    private String idenno;
    private int noofattempts;
    private String status;
    private String model;
    
    
 //   private Date currentdate;
 //   private Date lastupdatedate;
	
    public CpRegLock() {
    	
    }
    
    
    @Id
	@GeneratedValue
	@Column(name = "S_NO", unique = true, nullable = false, precision = 10, scale = 0)
    public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	
	@Column(name = "IDEN_TYPE")
	public String getIdentype() {
		return identype;
	}
	public void setIdentype(String identype) {
		this.identype = identype;
	}
	
	@Column(name = "IDEN_NO")
	public String getIdenno() {
		return idenno;
	}
	public void setIdenno(String idenno) {
		this.idenno = idenno;
	}
	
	@Column(name = "NO_OF_ATTEMPTS")
	public int getNoofattempts() {
		return noofattempts;
	}
	public void setNoofattempts(int noofattempts) {
		this.noofattempts = noofattempts;
	}
	
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
//	@Column(name = "CURRENT_DATE")
//	public Date getCurrentdate() {
//		return currentdate;
//	}
//	public void setCurrentdate(Date currentdate) {
//		this.currentdate = currentdate;
//	}
	
//	@Column(name = "LAST_UPDATE")
//	public Date getLastupdatedate() {
//		return lastupdatedate;
//	}
//	public void setLastupdatedate(Date lastupdatedate) {
//		this.lastupdatedate = lastupdatedate;
//	}

	@Column(name = "MODEL")
	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    
    
}
