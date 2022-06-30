package com.aetins.customerportal.web.service;

import java.util.List;

import com.aetins.customerportal.web.dto.DocumentDTO;
/**
 * This business logic interface  for all document management 
 * 05/02/2017
 * @author Viswakarthick
 */

public interface DocumentBL {
	//business user
	public List<DocumentDTO> findAllDocuments();
	
	public boolean saveAllDocuments(List<DocumentDTO> documentDTOs);
	
	public boolean deleteDocument(DocumentDTO documentDTO);
	
}
