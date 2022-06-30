package com.aetins.customerportal.web.dao;


import java.util.List;

import com.aetins.customerportal.web.entity.CpClaimIntimation;

public interface CpClaimIntimationDAO {

	public boolean insert(CpClaimIntimation cpClaimIntimation);
 
	public List<CpClaimIntimation> fetchall(int service);
	
}
