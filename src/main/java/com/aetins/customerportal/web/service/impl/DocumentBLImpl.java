package com.aetins.customerportal.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.dao.DocumentDAO;
import com.aetins.customerportal.web.dto.DocumentDTO;
import com.aetins.customerportal.web.entity.CpDocument;
import com.aetins.customerportal.web.service.DocumentBL;

/**
 * This business logic class for all Document management
 * 05/02/2017
 * @author Viswakarthick
 */
@Service
public class DocumentBLImpl implements DocumentBL {
	
	
	private static final Logger logger = Logger.getLogger(DocumentBLImpl.class);
	
	@Autowired
	DocumentDAO documentDAO;
			

	public void setDocumentDAO(DocumentDAO documentDAO) {
		this.documentDAO = documentDAO;
	}


	@Override
	public List<DocumentDTO> findAllDocuments() {
		logger.info("BLImpl:List all Documents retrieving started");
		List<DocumentDTO> documentList = new ArrayList<DocumentDTO>();
		List<CpDocument> cpDocuments = documentDAO.findAllDocuments();
		for (CpDocument cpDocument : cpDocuments) {
			DocumentDTO dto = new DocumentDTO();
			dto.setDocId(cpDocument.getNdocId());
			dto.setDocName(cpDocument.getvDocName());
			if (cpDocument.getvDocDesc() != null)
				dto.setDocDesc(cpDocument.getvDocDesc());
			dto.setDocFilepath(cpDocument.getvDocFilepath());
			if (cpDocument.getdUploadDate() != null)
				dto.setUploadDate(cpDocument.getdUploadDate());
			if (cpDocument.getvUploadUser() != null)
				dto.setUploadUser(cpDocument.getvUploadUser());
			if (cpDocument.getvLastupdDate() != null)
				dto.setLastupdDate(cpDocument.getvLastupdDate());
			if (cpDocument.getvLastupdUser() != null)
				dto.setLastupdUser(cpDocument.getvLastupdUser());
			if (cpDocument.getvLastupdProg() != null)
				dto.setLastupdProg(cpDocument.getvLastupdProg());
			documentList.add(dto);
		}
		return documentList;
	}

	@Override
	public boolean saveAllDocuments(List<DocumentDTO> documentDTOs) {
		logger.info("BLImpl:save all Documents started");
		List<CpDocument> cpDocuments = new ArrayList<CpDocument>();
		for (DocumentDTO dto : documentDTOs) {
			CpDocument cpDocument = new CpDocument();
			cpDocument.setNdocId(dto.getDocId());
			cpDocument.setvDocName(dto.getDocName());
			if (dto.getDocDesc() != null)
				cpDocument.setvDocDesc(dto.getDocDesc());
			cpDocument.setvDocFilepath(dto.getDocFilepath());
			if (dto.getUploadDate() != null)
				cpDocument.setdUploadDate(dto.getUploadDate());
			if (dto.getUploadUser() != null)
				cpDocument.setvUploadUser(dto.getUploadUser());
			if (dto.getLastupdDate() != null)
				cpDocument.setvLastupdDate(dto.getLastupdDate());
			if (dto.getLastupdUser() != null)
				cpDocument.setvLastupdUser(dto.getLastupdUser());
			if (dto.getLastupdProg() != null)
				cpDocument.setvLastupdProg(dto.getLastupdProg());
			cpDocuments.add(cpDocument);
		}
		return documentDAO.saveAllDocuments(cpDocuments);
	}

	@Override
	public boolean deleteDocument(DocumentDTO documentDTO) {
		logger.info("BLImpl:Delete Document started");
		CpDocument cpDocument = new CpDocument();
		cpDocument.setNdocId(documentDTO.getDocId());
		cpDocument.setvDocName(documentDTO.getDocName());
		if (documentDTO.getDocDesc() != null)
			cpDocument.setvDocDesc(documentDTO.getDocDesc());
		cpDocument.setvDocFilepath(documentDTO.getDocFilepath());
		if (documentDTO.getUploadDate() != null)
			cpDocument.setdUploadDate(documentDTO.getUploadDate());
		if (documentDTO.getUploadUser() != null)
			cpDocument.setvUploadUser(documentDTO.getUploadUser());
		if (documentDTO.getLastupdDate() != null)
			cpDocument.setvLastupdDate(documentDTO.getLastupdDate());
		if (documentDTO.getLastupdUser() != null)
			cpDocument.setvLastupdUser(documentDTO.getLastupdUser());
		if (documentDTO.getLastupdProg() != null)
			cpDocument.setvLastupdProg(documentDTO.getLastupdProg());

		return documentDAO.deleteDocument(cpDocument);
	}

	

}
