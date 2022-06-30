package com.aetins.customerportal.web.service;

import java.util.List;

import com.aetins.customerportal.web.entity.CpThemeCss;

public interface CpThemeCssBL {

	public boolean updatethemecss(CpThemeCss cpThemeCss);
	public boolean insertthemecss(CpThemeCss cpThemeCss);
	public List<CpThemeCss> fetch(CpThemeCss themeinfo);
}
