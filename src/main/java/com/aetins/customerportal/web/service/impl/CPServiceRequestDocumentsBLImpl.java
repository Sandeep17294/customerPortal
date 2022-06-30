package com.aetins.customerportal.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.dao.CPServiceRequestDocumentsDAO;
import com.aetins.customerportal.web.entity.CPServiceRequestDocuments;
import com.aetins.customerportal.web.service.CPServiceRequestDocumentsBL;

@Service
public class CPServiceRequestDocumentsBLImpl implements CPServiceRequestDocumentsBL{

	@Autowired
	CPServiceRequestDocumentsDAO cPServiceRequestDocumentsDAO;
	
	@Override
	public boolean insert(CPServiceRequestDocuments cPServiceRequestDocuments) {
		// TODO Auto-generated method stub
		return cPServiceRequestDocumentsDAO.insert(cPServiceRequestDocuments);
	}

	@Override
	public CPServiceRequestDocuments fetchdata(int requestno) {
		// TODO Auto-generated method stub
		return cPServiceRequestDocumentsDAO.fetchdata(requestno);
	}

}
