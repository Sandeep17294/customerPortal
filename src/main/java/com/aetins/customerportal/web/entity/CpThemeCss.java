package com.aetins.customerportal.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cp_theme_css")
public class CpThemeCss {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	
	@Column(name="User_ID")
	private String userid;
	
	@Column(name="Theme_CSS")
	private String themecss;
	
	@Column(name="Cust_Ref_No")
	private int custrefno;
	
	@Column(name="Theme_Layout")
	private String layout;
	
	@Column(name="Language_Selected")
	private String languageselected;
	
	
	public String getLanguageselected() {
		return languageselected;
	}

	public void setLanguageselected(String languageselected) {
		this.languageselected = languageselected;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getThemecss() {
		return themecss;
	}

	public void setThemecss(String themecss) {
		this.themecss = themecss;
	}

	public int getCustrefno() {
		return custrefno;
	}

	public void setCustrefno(int custrefno) {
		this.custrefno = custrefno;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}
	


	
	
	
}
