package com.aetins.customerportal.web.service;

import java.util.List;
import java.util.Map;

import com.aetins.customerportal.web.entity.CpConfiguration;

public interface CpConfigurationBL {

	public boolean insertdata(List<CpConfiguration> cpConfiguration);
	public List<CpConfiguration> fetchcontent();
	public boolean updatedata(List<CpConfiguration> cpConfiguration);
    public Map<String, String> getConfigMap();

}
