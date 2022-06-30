package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpThemeCss;

public interface CpThemeCssDAO extends IGenericDao<CpThemeCss, Integer> {

	public boolean updatetheme(CpThemeCss themeinfo);
	public boolean inserttheme(CpThemeCss themeinfo);
	public List<CpThemeCss> fetch(CpThemeCss themeinfo);
	
}
