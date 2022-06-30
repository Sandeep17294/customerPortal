package com.aetins.customerportal.web.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CP_CLAIM_INTIMATION")
public class CpClaimIntimation implements Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 3900352915561430531L;
	
	private int nserialno;
	private String policyno;
	private int custrefno;

	private String ititdtype;
	private String itiidno;
	private BigDecimal intimatorcustrefno;
	private String iticustype;

	private String itifstname;
	private String itimdtname;
	private String itilstname;
	private String itiname;
	
	private Date itidob;
	private String ititilee;
	private String itigender;
	private String iticontno;
	private String itiemail;
	
	private String eventgrp;
	private Date eventdate;
	private Date createdate;
	private BigDecimal intimationo;
	private CpRequestMaster nServicRequestNo;
	private String remarks;
	
	private String causeloss;
	private String eventplace;
	private String intimatetime;
	
	
	@Id
	@GeneratedValue
	@Column(name = "N_SERIAL_NO", unique = true, nullable = false, precision = 10, scale = 0)
	public int getNserialno() {
		return nserialno;
	}
	public void setNserialno(int nserialno) {
		this.nserialno = nserialno;
	}
	
	@Column(name = "V_POLICY_NO")
	public String getPolicyno() {
		return policyno;
	}
	public void setPolicyno(String policyno) {
		this.policyno = policyno;
	}
	
	@Column(name = "N_CUST_REF_NO")
	public int getCustrefno() {
		return custrefno;
	}
	public void setCustrefno(int custrefno) {
		this.custrefno = custrefno;
	}
	
	@Column(name = "I_INTI_IDTYPE")
	public String getItitdtype() {
		return ititdtype;
	}
	public void setItitdtype(String ititdtype) {
		this.ititdtype = ititdtype;
	}
	
	@Column(name = "I_INTI_IDNO")
	public String getItiidno() {
		return itiidno;
	}
	public void setItiidno(String itiidno) {
		this.itiidno = itiidno;
	}
	
	@Column(name = "I_INTI_CUST_NO")
	public BigDecimal getIntimatorcustrefno() {
		return intimatorcustrefno;
	}
	public void setIntimatorcustrefno(BigDecimal intimatorcustrefno) {
		this.intimatorcustrefno = intimatorcustrefno;
	}
	
	@Column(name = "I_INTI_CUST_TYPE")
	public String getIticustype() {
		return iticustype;
	}
	public void setIticustype(String iticustype) {
		this.iticustype = iticustype;
	}
	
	@Column(name = "I_INTI_FNAME")
	public String getItifstname() {
		return itifstname;
	}
	public void setItifstname(String itifstname) {
		this.itifstname = itifstname;
	}
	
	@Column(name = "I_INTI_LNAME")
	public String getItimdtname() {
		return itimdtname;
	}
	public void setItimdtname(String itimdtname) {
		this.itimdtname = itimdtname;
	}
	
	@Column(name = "I_INTI_MNAME")
	public String getItilstname() {
		return itilstname;
	}
	public void setItilstname(String itilstname) {
		this.itilstname = itilstname;
	}
	
	@Column(name = "I_INTI_NAME")
	public String getItiname() {
		return itiname;
	}
	public void setItiname(String itiname) {
		this.itiname = itiname;
	}
	
	@Column(name = "I_INTI_DOB")
	public Date getItidob() {
		return itidob;
	}
	public void setItidob(Date itidob) {
		this.itidob = itidob;
	}
	
	@Column(name = "I_INTI_TITLE")
	public String getItitilee() {
		return ititilee;
	}
	public void setItitilee(String ititilee) {
		this.ititilee = ititilee;
	}
	
	@Column(name = "I_INTI_GENDER")
	public String getItigender() {
		return itigender;
	}
	public void setItigender(String itigender) {
		this.itigender = itigender;
	}
	
	@Column(name = "I_INTI_MOBILE")
	public String getIticontno() {
		return iticontno;
	}
	public void setIticontno(String iticontno) {
		this.iticontno = iticontno;
	}
	
	@Column(name = "I_INTI_EMAIL")
	public String getItiemail() {
		return itiemail;
	}
	public void setItiemail(String itiemail) {
		this.itiemail = itiemail;
	}
	
	@Column(name = "EVENT_GRP")
	public String getEventgrp() {
		return eventgrp;
	}
	public void setEventgrp(String eventgrp) {
		this.eventgrp = eventgrp;
	}
	
	@Column(name = "EVENT_DATE")
	public Date getEventdate() {
		return eventdate;
	}
	public void setEventdate(Date eventdate) {
		this.eventdate = eventdate;
	}
	
	@Column(name = "CREATED_DATE")
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	
	@Column(name = "INTIMATION_NO")
	public BigDecimal getIntimationo() {
		return intimationo;
	}
	public void setIntimationo(BigDecimal intimationo) {
		this.intimationo = intimationo;
	}
	
	@ManyToOne
	@JoinColumn(name = "N_SERVICE_REQUEST_NO", referencedColumnName = "SERVICE_REQUEST_NO")
	public CpRequestMaster getnServicRequestNo() {
		return nServicRequestNo;
	}
	public void setnServicRequestNo(CpRequestMaster nServicRequestNo) {
		this.nServicRequestNo = nServicRequestNo;
	}
	
	@Column(name = "REMARKS")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Column(name = "CAUSE_LOSS")
	public String getCauseloss() {
		return causeloss;
	}
	public void setCauseloss(String causeloss) {
		this.causeloss = causeloss;
	}
	
	@Column(name = "EVENT_PLACE")
	public String getEventplace() {
		return eventplace;
	}
	public void setEventplace(String eventplace) {
		this.eventplace = eventplace;
	}
	
	@Column(name = "INTIMATE_TIME")
	public String getIntimatetime() {
		return intimatetime;
	}
	public void setIntimatetime(String intimatetime) {
		this.intimatetime = intimatetime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
		
	
	
	
	
}