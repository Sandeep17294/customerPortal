package com.aetins.customerportal.web.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aetins.customerportal.web.controllers.BaseAction;

@Controller
@Scope("session")
public class ChangeLanguage extends BaseAction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String language;
	private String renderLng;
	private Locale locale = new Locale("en");
	private ArrayList<String> list;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public ArrayList<String> getList() {
		return list;
	}

	public void setList(ArrayList<String> list) {
		this.list = list;
	}

	@PostConstruct
	public void init(){
		list = new ArrayList<>();
		list.add("English");
		list.add("Arabic");
	}

	public String onChangeLanguage() {
		if(!language.equals("English")){
			locale = new Locale("ar");
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		}
		if(language.equals("English") || language.equals("")){
			locale = new Locale("en");
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		}
		getSession().setAttribute("lngChange", locale);
		return language;
	}
}
