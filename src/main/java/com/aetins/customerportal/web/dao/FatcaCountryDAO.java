package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpFatcaCountryDet;

public interface FatcaCountryDAO extends IGenericDao<CpFatcaCountryDet, Long>{

	public boolean insertCountryDetails(List<CpFatcaCountryDet> cpFatcaCountryDet);

	public List<CpFatcaCountryDet> fetchFatcaCountryDetails(int serialNo);

}
