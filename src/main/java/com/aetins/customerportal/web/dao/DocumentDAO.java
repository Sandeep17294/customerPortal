package com.aetins.customerportal.web.dao;

import java.util.List;

import com.aetins.customerportal.web.common.IGenericDao;
import com.aetins.customerportal.web.entity.CpDocument;
/**
 * This DAO interface for all Document management 
 * 05/02/2017
 * @author Viswakarthick
 */

public interface DocumentDAO extends IGenericDao<CpDocument, Long>{
	//business user 
	public List<CpDocument> findAllDocuments();
	
	public boolean saveAllDocuments(List<CpDocument> documents);
	
	public boolean deleteDocument(CpDocument document);
	
	//customer
}
