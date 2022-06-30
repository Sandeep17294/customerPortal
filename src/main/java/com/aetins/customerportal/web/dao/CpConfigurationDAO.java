package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.entity.CpConfiguration;

public interface CpConfigurationDAO {

	public boolean insertdata(List<CpConfiguration> cpConfiguration);
	public List<CpConfiguration> fetchcontent();
	public boolean updatedata(List<CpConfiguration> cpConfiguration);
	
}
