
package com.aetins.customerportal.core.primefaces.view;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aetins.customerportal.core.faceletstaglib.SecurityLibrary;
import com.aetins.customerportal.web.entity.CpThemeCss;
import com.aetins.customerportal.web.service.CpThemeCssBL;

@Controller
@Scope("session")
public class GuestPreferences implements Serializable {

	private String menuMode = "layout-menu-static";

	private String theme = "absolution";

	private String layout = "absolution";

	private boolean orientationRTL;

	private String lang = "eng";
	
	private String langselected;
	
	private boolean lan;

	private

	@Autowired CpThemeCssBL cpThemeCssBL;

	CpThemeCss themecss = new CpThemeCss();

	public String getTheme() {
		return theme;
	}

	public void fetchtheme() {
		int refno = SecurityLibrary.getFullLoggedInUser().getNcustRefNo();
		String id = SecurityLibrary.getFullLoggedInUser().getVuserName();
		CpThemeCss themeinfo = new CpThemeCss();
		themeinfo.setCustrefno(refno);
		List<CpThemeCss> listingtheme = cpThemeCssBL.fetch(themeinfo);
		if (listingtheme.size() > 0) {
			themecss.setLanguageselected(listingtheme.get(0).getLanguageselected());
			themecss.setThemecss(listingtheme.get(0).getThemecss());
			themecss.setLayout(listingtheme.get(0).getLayout());
			themecss.setThemecss(listingtheme.get(0).getThemecss());
			themecss.setLayout(listingtheme.get(0).getLayout());
			themecss.setId(listingtheme.get(0).getId());
			String theming=listingtheme.get(0).getThemecss();
			if(theming==null||theming.equalsIgnoreCase("")) {
				this.theme = theme;
				this.layout = theme.split("-")[0];	
			}else {
				this.theme = listingtheme.get(0).getThemecss();
				this.layout = theme.split("-")[0];
			}
			this.lang=listingtheme.get(0).getLanguageselected();
			if(lang==null||lang.equalsIgnoreCase("")) {
				this.lang="eng";
			}
			setOrientationRTLSS(lang);
		} else {
			this.theme = "absolution";
			this.layout = theme.split("-")[0];
			this.lang = "eng";
		}
	}

	public void setTheme(String theme) {
		int refno = SecurityLibrary.getFullLoggedInUser().getNcustRefNo();
		String id = SecurityLibrary.getFullLoggedInUser().getVuserName();
		if (themecss.getThemecss() != null && themecss.getLayout() != null) {
			boolean status;
			CpThemeCss themeCsss = new CpThemeCss();
			themeCsss.setCustrefno(refno);
			themeCsss.setUserid(id);
			themeCsss.setId(themecss.getId());
			this.theme = theme;
			this.layout = theme.split("-")[0];
			themeCsss.setThemecss(theme);
			themeCsss.setLayout(layout);
			themeCsss.setLanguageselected(lang);
			status = cpThemeCssBL.insertthemecss(themeCsss);
			if (status = true) {
				CpThemeCss themeinfo = new CpThemeCss();
				themeinfo.setCustrefno(refno);
				List<CpThemeCss> listingtheme = cpThemeCssBL.fetch(themeinfo);
				if (listingtheme.size() > 0) {
					themecss.setThemecss(listingtheme.get(0).getThemecss());
					themecss.setLayout(listingtheme.get(0).getLayout());
					themecss.setId(listingtheme.get(0).getId());
					themecss.setUserid(listingtheme.get(0).getUserid());
					themecss.setCustrefno(listingtheme.get(0).getCustrefno());
					themecss.setLanguageselected(listingtheme.get(0).getLanguageselected());
				}
			}
			//status = cpThemeCssBL.updatethemecss(themeCsss);
		} else {
			this.theme = theme;
			this.layout = theme.split("-")[0];
			sample();
		}
	}

	public void sample() {
		boolean status;
		int refno = SecurityLibrary.getFullLoggedInUser().getNcustRefNo();
		String id = SecurityLibrary.getFullLoggedInUser().getVuserName();
		if (themecss.getThemecss() == null && themecss.getLayout() == null) {
			themecss.setCustrefno(refno);
			themecss.setUserid(id);
			themecss.setThemecss(theme);
			themecss.setLayout(layout);
			themecss.setLanguageselected(lang);
			status = cpThemeCssBL.insertthemecss(themecss);
			if (status = true) {
				CpThemeCss themeinfo = new CpThemeCss();
				themeinfo.setCustrefno(refno);
				List<CpThemeCss> listingtheme = cpThemeCssBL.fetch(themeinfo);
				if (listingtheme.size() > 0) {
					themecss.setThemecss(listingtheme.get(0).getThemecss());
					themecss.setLayout(listingtheme.get(0).getLayout());
					themecss.setId(listingtheme.get(0).getId());
					themecss.setUserid(listingtheme.get(0).getUserid());
					themecss.setCustrefno(listingtheme.get(0).getCustrefno());
					themecss.setLanguageselected(listingtheme.get(0).getLanguageselected());
				}
			}
		}
	}

	/**
	 * change language 
	 */
	public void changeLang() {
		if (langselected.equals("eng")) {
			setOrientationRTL(false);
			FacesContext context = FacesContext.getCurrentInstance();
			UIViewRoot root = context.getViewRoot();
			Locale loc = new Locale("eng");
			root.setLocale(loc);
			lang = "eng";
			lan = false;
		}
		if (langselected.equals("ar")) {
			setOrientationRTL(true);
			FacesContext context = FacesContext.getCurrentInstance();
			UIViewRoot root = context.getViewRoot();
			Locale loc = new Locale("ar");
			root.setLocale(loc);
			lang = "ar";
			lan = true;
		}
	}
	

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public String getMenuMode() {
		return menuMode;
	}

	public void setMenuMode(String menuMode) {
		this.menuMode = menuMode;
	}

	public boolean isOrientationRTL() {
		return orientationRTL;
	}

	
	public void setOrientationRTLSS(String lang) {
		if(lang.equalsIgnoreCase("ar")) {
			orientationRTL=true;
			if(orientationRTL) {
				FacesContext context = FacesContext.getCurrentInstance();
				UIViewRoot root = context.getViewRoot();
				Locale loc = new Locale("ar");
				root.setLocale(loc);
				lang = "ar";
			}
		}else {
			FacesContext context = FacesContext.getCurrentInstance();
			UIViewRoot root = context.getViewRoot();
			Locale loc = new Locale("eng");
			root.setLocale(loc);
			lang = "eng";		
		}
	}
	
	public void setOrientationRTL(boolean orientationRTL) {
		this.orientationRTL = orientationRTL;	
		if(orientationRTL) {
			FacesContext context = FacesContext.getCurrentInstance();
			UIViewRoot root = context.getViewRoot();
			Locale loc = new Locale("ar");
			root.setLocale(loc);
			lang = "ar";
			setlanguagemysql();
		}
		  else {
			FacesContext context = FacesContext.getCurrentInstance();
			UIViewRoot root = context.getViewRoot();
			Locale loc = new Locale("eng");
			root.setLocale(loc);
			lang = "eng";
			setlanguagemysql();
		}
	}
	
	public void setlanguagemysql() {
		if (themecss.getLanguageselected()!=null) {
			if(lang!=null) {
				boolean status;
				int refno = SecurityLibrary.getFullLoggedInUser().getNcustRefNo();
				String id = SecurityLibrary.getFullLoggedInUser().getVuserName();
				CpThemeCss themeCsss = new CpThemeCss();
				themeCsss.setCustrefno(refno);
				themeCsss.setUserid(id);
				themeCsss.setId(themecss.getId());
				themeCsss.setLanguageselected(lang);
				themeCsss.setThemecss(theme);
				themeCsss.setLayout(layout);
				status = cpThemeCssBL.insertthemecss(themeCsss);
				if (status = true) {
					CpThemeCss themeinfo = new CpThemeCss();
					themeinfo.setCustrefno(refno);
					List<CpThemeCss> listingtheme = cpThemeCssBL.fetch(themeinfo);
					if (listingtheme.size() > 0) {
						themecss.setLanguageselected(listingtheme.get(0).getLanguageselected());
						themecss.setThemecss(listingtheme.get(0).getThemecss());
						themecss.setLayout(listingtheme.get(0).getLayout());
						themecss.setId(listingtheme.get(0).getId());
						themecss.setUserid(listingtheme.get(0).getUserid());
						themecss.setCustrefno(listingtheme.get(0).getCustrefno());
					}
				}
			}
		}else {
			if(lang!=null) {
				boolean status;
				int refno = SecurityLibrary.getFullLoggedInUser().getNcustRefNo();
				String id = SecurityLibrary.getFullLoggedInUser().getVuserName();
				CpThemeCss themeCsss = new CpThemeCss();
				themeCsss.setCustrefno(refno);
				themeCsss.setUserid(id);
				themeCsss.setId(themecss.getId());
				themeCsss.setLanguageselected(lang);
				themeCsss.setThemecss(theme);
				themeCsss.setLayout(layout);
				status = cpThemeCssBL.insertthemecss(themeCsss);
				if (status = true) {
					CpThemeCss themeinfo = new CpThemeCss();
					themeinfo.setCustrefno(refno);
					List<CpThemeCss> listingtheme = cpThemeCssBL.fetch(themeinfo);
					if (listingtheme.size() > 0) {
						themecss.setLanguageselected(listingtheme.get(0).getLanguageselected());
						themecss.setThemecss(listingtheme.get(0).getThemecss());
						themecss.setLayout(listingtheme.get(0).getLayout());
						themecss.setId(listingtheme.get(0).getId());
						themecss.setUserid(listingtheme.get(0).getUserid());
						themecss.setCustrefno(listingtheme.get(0).getCustrefno());
					}
				}
			}
		}
	}
	

	public CpThemeCss getThemecss() {
		return themecss;
	}

	public void setThemecss(CpThemeCss themecss) {
		this.themecss = themecss;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getLangselected() {
		return langselected;
	}

	public void setLangselected(String langselected) {
		this.langselected = langselected;
	}

	public boolean isLan() {
		return lan;
	}

	public void setLan(boolean lan) {
		this.lan = lan;
	}
	
	

}
