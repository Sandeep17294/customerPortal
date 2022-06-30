package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpNFAddressContacts;



/**
 * This DAO interface for all Address Contact management 
 * 01/04/2017
 * @author Viswakarthick
 */
public interface CpNFAddressContactsDAO extends IGenericDao<CpNFAddressContacts, Long>{

	public boolean saveAddressContacts(List<CpNFAddressContacts>  cpNFAddressContactList);

	public List<CpNFAddressContacts> fetchAllAddressContactDetails(int serviceReqNo);
}
