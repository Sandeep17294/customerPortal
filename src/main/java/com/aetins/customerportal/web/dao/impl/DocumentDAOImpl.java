package com.aetins.customerportal.web.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.common.GenericDaoImpl;
import com.aetins.customerportal.web.dao.DocumentDAO;
import com.aetins.customerportal.web.entity.CpDocument;

/**
 * This Business logic impl class for all Document Management 
 * 05/02/2017
 * @author Viswakarthick
 */

@Repository
public class DocumentDAOImpl extends GenericDaoImpl<CpDocument, Long> implements DocumentDAO {

	private static final Logger logger = Logger.getLogger(DocumentDAOImpl.class);
	
	@Resource
	private SessionFactory session;
	
	@Override
	protected Session getSession() {
		return session.getCurrentSession();
	}
	
	@Override
	protected Class<CpDocument> getEntityClass() {		
		return CpDocument.class;
	}
	
	List<CpDocument> cpDocuments;
	
	@Override
	public List<CpDocument> findAllDocuments() {
		logger.info("List all Documents retreiving");		
		try {
			cpDocuments = findAll();
		} catch (Exception e) {
			logger.error("Error occured " + e.getMessage());
			e.printStackTrace();
		}
		return cpDocuments;
	}

	@Override
	public boolean saveAllDocuments(List<CpDocument> documents) {
		boolean status=Boolean.TRUE;
		logger.info("save Documents Start");
		try{
			for (CpDocument cpDocument : documents) {
					save(cpDocument);
			}
			
		}catch (Exception e) {
			status=Boolean.FALSE;
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean deleteDocument(CpDocument document) {
		boolean status=Boolean.TRUE;
		logger.info("Deleting Document Start");
		try{
			remove(document);						
		}catch (Exception e) {
			status=Boolean.FALSE;
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	

}
