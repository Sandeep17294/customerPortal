package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.aetins.customerportal.web.entity.CpRequestMaster;

public class ClaimIntimationDTO {
	
	private int nSerialNo;
    private String policyno;
    private int custrefno;
          
    private String itidtype;
    private String itidno;
    private BigDecimal itrefno;
    private String itcusttype;

	private String itifstname;
	private String itimdtname;
	private String itilstname;
	private String itiname;
	
	private Date itidob;
	private String ititilee;
	private String itigender;
	private String iticontno;
	private String itiemail;
	
	private String eventgroup;
	private Date eventdate;
	private Date createdate;
	private String intimationum;
	private ServiceRequestMasterDTO nServicRequestNo;
	private String remarks;
	
	private String causecode;
	private String eventplace;
	private String intimationtime;
	
	
	
	public String getCausecode() {
		return causecode;
	}
	public void setCausecode(String causecode) {
		this.causecode = causecode;
	}
	public String getEventplace() {
		return eventplace;
	}
	public void setEventplace(String eventplace) {
		this.eventplace = eventplace;
	}
	
	public String getIntimationtime() {
		return intimationtime;
	}
	public void setIntimationtime(String intimationtime) {
		this.intimationtime = intimationtime;
	}

	private String dob;
	
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public Date getItidob() {
		return itidob;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getItiname() {
		return itiname;
	}
	public void setItiname(String itiname) {
		this.itiname = itiname;
	}
	public String getEventgroup() {
		return eventgroup;
	}
	public void setEventgroup(String eventgroup) {
		this.eventgroup = eventgroup;
	}
	
	public Date getEventdate() {
		return eventdate;
	}
	public void setEventdate(Date eventdate) {
		this.eventdate = eventdate;
	}
	public void setItidob(Date itidob) {
		this.itidob = itidob;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getIntimationum() {
		return intimationum;
	}
	public void setIntimationum(String intimationum) {
		this.intimationum = intimationum;
	}
	public ServiceRequestMasterDTO getnServicRequestNo() {
		return nServicRequestNo;
	}
	public void setnServicRequestNo(ServiceRequestMasterDTO nServicRequestNo) {
		this.nServicRequestNo = nServicRequestNo;
	}
	
	public String getItifstname() {
		return itifstname;
	}
	public void setItifstname(String itifstname) {
		this.itifstname = itifstname;
	}
	public String getItimdtname() {
		return itimdtname;
	}
	public void setItimdtname(String itimdtname) {
		this.itimdtname = itimdtname;
	}
	public String getItilstname() {
		return itilstname;
	}
	public void setItilstname(String itilstname) {
		this.itilstname = itilstname;
	}
	public String getIticontno() {
		return iticontno;
	}
	public void setIticontno(String iticontno) {
		this.iticontno = iticontno;
	}	
	public String getItigender() {
		return itigender;
	}
	public void setItigender(String itigender) {
		this.itigender = itigender;
	}
	public String getItitilee() {
		return ititilee;
	}
	public void setItitilee(String ititilee) {
		this.ititilee = ititilee;
	}
	public String getItiemail() {
		return itiemail;
	}
	public void setItiemail(String itiemail) {
		this.itiemail = itiemail;
	}
	public int getnSerialNo() {
		return nSerialNo;
	}
	public void setnSerialNo(int nSerialNo) {
		this.nSerialNo = nSerialNo;
	}
	public String getPolicyno() {
		return policyno;
	}
	public void setPolicyno(String policyno) {
		this.policyno = policyno;
	}
	
	
	
	public int getCustrefno() {
		return custrefno;
	}
	public void setCustrefno(int custrefno) {
		this.custrefno = custrefno;
	}
	public void setItrefno(BigDecimal itrefno) {
		this.itrefno = itrefno;
	}
	public String getItidtype() {
		return itidtype;
	}
	public void setItidtype(String itidtype) {
		this.itidtype = itidtype;
	}
	public String getItidno() {
		return itidno;
	}
	public void setItidno(String itidno) {
		this.itidno = itidno;
	}

	public String getItcusttype() {
		return itcusttype;
	}
	public void setItcusttype(String itcusttype) {
		this.itcusttype = itcusttype;
	}
	public BigDecimal getItrefno() {
		return itrefno;
	}
	
	
	
	
	
	
	
}
