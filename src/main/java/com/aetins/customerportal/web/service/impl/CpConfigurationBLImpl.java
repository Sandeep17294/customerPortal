package com.aetins.customerportal.web.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.dao.CpConfigurationDAO;
import com.aetins.customerportal.web.entity.CpConfiguration;
import com.aetins.customerportal.web.service.CpConfigurationBL;

@Service
public class CpConfigurationBLImpl implements CpConfigurationBL {

	private static final Logger logger = LoggerFactory.getLogger(CpConfigurationBLImpl.class);

	@Autowired
	CpConfigurationDAO cpConfigurationDAO;

	@Override
	public boolean insertdata(List<CpConfiguration> cpConfiguration) {
		// TODO Auto-generated method stub
		return cpConfigurationDAO.insertdata(cpConfiguration);
	}

	@Override
	public List<CpConfiguration> fetchcontent() {
		// TODO Auto-generated method stub
		return cpConfigurationDAO.fetchcontent();
	}

	@Override
	public boolean updatedata(List<CpConfiguration> cpConfiguration) {
		// TODO Auto-generated method stub
		return cpConfigurationDAO.updatedata(cpConfiguration);
	}

	@Override
	public Map<String, String> getConfigMap() {
		
		Map<String, String> configMap = null;
		
		try {
			
			List<CpConfiguration> fetchcontent = fetchcontent();
			
			if(fetchcontent.size()>0) {
				
				configMap = new HashedMap<String, String>();
				for(int config=0;config<fetchcontent.size();config++) {
					configMap.put(fetchcontent.get(config).getKey(), fetchcontent.get(config).getValue());
					logger.info("Config map successfully: {}",configMap);
				}
			}else {
				logger.error("Loading config map failed due data fetch list size: {}",fetchcontent.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception caught while loading config map: {}",e.getCause());
		}
		
		return configMap;
	}


}
