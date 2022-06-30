package com.aetins.customerportal.web.dao;


import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpServerstatusSetting;

public interface DownTimeDAO extends IGenericDao<CpServerstatusSetting, Long>{

	public boolean insertDownTime(CpServerstatusSetting cpServerstatusSetting);

	List<CpServerstatusSetting> listDownTime();

	boolean updateDownTime(CpServerstatusSetting cpServerstatusSetting);
	
	//added by Harmain to fetch Down Time Status 

	List<CpServerstatusSetting> fetchDownTime(CpServerstatusSetting downTimeStatus);
	
}