
package com.aetins.customerportal.web.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="cp_service_request_docs")
public class CPServiceRequestDocuments {

	
	@Id
	@GenericGenerator(name = "gen", strategy = "increment")
	@GeneratedValue(generator = "gen")
	@Column(name="id")
	private Long id;

	@Column(name = "FILE_NAME")
	private String fileName;
	
	@Column(name = "FILE_SIZE")
	private long fileSize;
	
	
//	@OneToOne(fetch =FetchType.EAGER)
//	@JoinColumn(name = "SERVICE_REQUEST_NO")

	
	@OneToOne
	@JoinColumn(name = "SERVICE_REQUEST_NO", referencedColumnName = "SERVICE_REQUEST_NO")
	private CpRequestMaster serviceRequestNo;

	@Column(name = "lastupdate_date")
	private Date lastupdateDate;

	@Column(name = "creation_date")
	private Date createdDate;

	private String docreq;
	private int sno;
	
	
	public CPServiceRequestDocuments() {
		super();
	}



	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public long getFileSize() {
		return fileSize;
	}


	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}


	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Date getLastupdateDate() {
		return lastupdateDate;
	}



	public void setLastupdateDate(Date lastupdateDate) {
		this.lastupdateDate = lastupdateDate;
	}



	public CpRequestMaster getServiceRequestNo() {
		return serviceRequestNo;
	}



	public void setServiceRequestNo(CpRequestMaster serviceRequestNo) {
		this.serviceRequestNo = serviceRequestNo;
	}



	public String getDocreq() {
		return docreq;
	}



	public void setDocreq(String docreq) {
		this.docreq = docreq;
	}



	public int getSno() {
		return sno;
	}



	public void setSno(int sno) {
		this.sno = sno;
	}



	@Override
	public String toString() {
		return "CPServiceRequestDocuments [id=" + id + ", fileName=" + fileName + ", fileSize=" + fileSize
				+ ", request=" + serviceRequestNo + ", lastupdateDate=" + lastupdateDate + ", createdDate=" + createdDate + "]";
	}

}
