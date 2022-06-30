package com.aetins.customerportal.web.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.dao.CpThemeCssDAO;
import com.aetins.customerportal.web.dao.CustomerDetailsDAO;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.dto.RedirectionDTO;
import com.aetins.customerportal.web.entity.CpRedirectionSwitching;
import com.aetins.customerportal.web.entity.CpRequestMaster;
import com.aetins.customerportal.web.entity.CpThemeCss;
import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.service.CpThemeCssBL;
import com.aetins.customerportal.web.service.CustomerDetailsBL;

@Service
public class CpThemeCssBLImpl implements CpThemeCssBL {

private static final Logger logger = Logger.getLogger(CpThemeCssBLImpl.class);
	
	@Autowired
	CpThemeCssDAO cpThemeCssDAO;

	
	
	
	@Override
	public boolean updatethemecss(CpThemeCss cpThemeCss) {
		// TODO Auto-generated method stub
		
		CpThemeCss themecss = new CpThemeCss();
		boolean status;
		
		themecss.setId(cpThemeCss.getId());
		themecss.setUserid(cpThemeCss.getUserid());
		themecss.setCustrefno(cpThemeCss.getCustrefno());
		themecss.setThemecss(cpThemeCss.getThemecss());
		themecss.setLayout(cpThemeCss.getLayout());
		
		status=cpThemeCssDAO.updatetheme(themecss);
		
		return status;
	}



	@Override
	public boolean insertthemecss(CpThemeCss cpThemeCss) {
		// TODO Auto-generated method stub
		CpThemeCss themecss = new CpThemeCss();
		
		themecss.setUserid(cpThemeCss.getUserid());
		themecss.setCustrefno(cpThemeCss.getCustrefno());
		themecss.setThemecss(cpThemeCss.getThemecss());
		themecss.setLayout(cpThemeCss.getLayout());
		themecss.setLanguageselected(cpThemeCss.getLanguageselected());
		
		return cpThemeCssDAO.inserttheme(themecss);
	}



	@Override
	public List<CpThemeCss> fetch(CpThemeCss themeinfo) {
		
		List<CpThemeCss> themme = cpThemeCssDAO.fetch(themeinfo);
		
	
		return themme;
	}
	
}
