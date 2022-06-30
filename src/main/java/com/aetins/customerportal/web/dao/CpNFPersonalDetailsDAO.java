package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpNFPersonalDetails;

/**
 * This DAO interface for all Personal Detail management 
 * 01/04/2017
 * @author Viswakarthick
 */
public interface CpNFPersonalDetailsDAO extends IGenericDao<CpNFPersonalDetails, Long>{

	public boolean savePersonalDetails(List<CpNFPersonalDetails>  cpNFPersonalDetailList);
	public List<CpNFPersonalDetails> fetchAllPersonalDetails(int serviceReqNo);
	public Long getRowCount(String type, String policyNo);
}
