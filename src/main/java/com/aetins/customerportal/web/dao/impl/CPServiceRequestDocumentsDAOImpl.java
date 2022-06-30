package com.aetins.customerportal.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.CPServiceRequestDocumentsDAO;
import com.aetins.customerportal.web.entity.CPServiceRequestDocuments;
import com.aetins.customerportal.web.entity.CpRedirectionSwitching;
import com.aetins.customerportal.web.entity.CpUserInfo;

@Repository
public class CPServiceRequestDocumentsDAOImpl extends GenericDaoImpl<CPServiceRequestDocuments, Integer> implements CPServiceRequestDocumentsDAO{

	
	@Resource
	private SessionFactory session;
	
	@Override
	public boolean insert(CPServiceRequestDocuments cPServiceRequestDocuments) {
		boolean status=Boolean.TRUE;
		System.out.println("save Documents Start");
		try{
			save(cPServiceRequestDocuments);
		}catch (Exception e) {
			 status=Boolean.FALSE;
			 System.out.println("save Documents Start");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	protected Class<CPServiceRequestDocuments> getEntityClass() {
		// TODO Auto-generated method stub
		return CPServiceRequestDocuments.class;
	}

	@Override
	public CPServiceRequestDocuments fetchdata(int requestno) {
		List<CPServiceRequestDocuments> cpServiceRequestDocuments = new ArrayList<CPServiceRequestDocuments>();
		CPServiceRequestDocuments returning = new CPServiceRequestDocuments();
		String[] propNames = {"serviceRequestNo.serviceRequestNo"};		
		Object[] values = {requestno};		
		try {						
			cpServiceRequestDocuments  = findAllByPropertiesEqual(propNames, values);
			if(cpServiceRequestDocuments.size()>0) {
				returning.setId(cpServiceRequestDocuments.get(0).getId());
				returning.setFileName(cpServiceRequestDocuments.get(0).getFileName());
				returning.setFileSize(cpServiceRequestDocuments.get(0).getFileSize());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returning;
	}

}
