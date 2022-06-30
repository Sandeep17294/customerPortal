package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpNFAddress;

/**
 * This DAO interface for all Address  management 
 * 01/04/2017
 * @author Viswakarthick
 */
public interface CpNFAddressDAO extends IGenericDao<CpNFAddress, Long>{

	public boolean saveAddressDetails(List<CpNFAddress>  cpNFAddressList);

	public List<CpNFAddress> fetchAllAddressDetails(int serviceReqNo);
	
	
}
