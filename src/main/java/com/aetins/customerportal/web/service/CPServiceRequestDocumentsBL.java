package com.aetins.customerportal.web.service;

import com.aetins.customerportal.web.entity.CPServiceRequestDocuments;

public interface CPServiceRequestDocumentsBL {

	public boolean insert(CPServiceRequestDocuments cPServiceRequestDocuments);
	
	public CPServiceRequestDocuments fetchdata(int requestno);
}

