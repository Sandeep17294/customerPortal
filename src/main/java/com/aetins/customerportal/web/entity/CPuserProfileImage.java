package com.aetins.customerportal.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "cp_profile_image")
public class CPuserProfileImage {

	@Id
	@GenericGenerator(name = "gen", strategy = "increment")
	@GeneratedValue(generator = "gen")
	private Long ID;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "image_name")
	private String fileName;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "last_update_date")
	private Date lastUpdateDate;
	

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	@Override
	public String toString() {
		return "CPuserProfileImage [ID=" + ID + ", username=" + username + ", fileName=" + fileName + ", createdDate="
				+ createdDate + ", lastUpdateDate=" + lastUpdateDate + "]";
	}
	
	
}
