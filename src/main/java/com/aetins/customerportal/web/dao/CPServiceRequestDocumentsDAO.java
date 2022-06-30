package com.aetins.customerportal.web.dao;

import com.aetins.customerportal.web.entity.CPServiceRequestDocuments;

public interface CPServiceRequestDocumentsDAO {

	public boolean insert(CPServiceRequestDocuments cPServiceRequestDocuments);
	
	public CPServiceRequestDocuments fetchdata(int requestno); 
	
}
