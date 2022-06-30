package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpNFPersonalContacts;

/**
 * This DAO interface for all Personal detail Contact management 
 * 01/04/2017
 * @author Viswakarthick
 */
public interface CpNFPersonalContactsDAO extends IGenericDao<CpNFPersonalContacts, Long>{

	public boolean savePersonalContacts(List<CpNFPersonalContacts>  cpNFPersonalContactList);
}
