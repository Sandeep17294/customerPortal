package com.aetins.customerportal.web.controllers;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aetins.customerportal.core.faceletstaglib.SecurityLibrary;
import com.aetins.customerportal.web.common.Constants;
import com.aetins.customerportal.web.common.LoginSession;
import com.aetins.customerportal.web.dao.CpUserInfoDAO;
import com.aetins.customerportal.web.dto.DocumentDTO;
import com.aetins.customerportal.web.dto.ServiceRequestMasterDTO;
import com.aetins.customerportal.web.entity.CPServiceRequestDocuments;
import com.aetins.customerportal.web.entity.CpRequestMaster;
import com.aetins.customerportal.web.service.CPServiceRequestDocumentsBL;
import com.aetins.customerportal.web.service.DocumentBL;
import com.aetins.customerportal.web.service.MessageQueueBL;
import com.aetins.customerportal.web.service.TransactionBL;
import com.aetins.customerportal.web.service.impl.DocumentBLImpl;
import com.aetins.customerportal.web.utils.AppSettingURL;
import com.aetins.customerportal.web.utils.BSLUtils;
import com.aetins.customerportal.web.utils.FileUtils;

@Controller
@Scope("session")
public class DocumentAction extends BaseAction {
	
	
	private static final Logger logger = Logger.getLogger(DocumentAction.class);
	
	@Autowired
	DocumentBL documentBL;
	
	@Autowired
	LoginSession loginSession;
	
	@Autowired
	MessageQueueBL messageQueue;
	
	@Autowired
	CpUserInfoDAO cpUserInfoDAO;
	
	private List<DocumentDTO> documentList;	
	private boolean uploadStatus;	
	private String bodyMsg;
	
	@Autowired
	TransactionBL transactionBL;
	
	@Override
	public void init() {
		logger.info("Retreiving all Document List");
		documentList = documentBL.findAllDocuments();	
		servicelist();
	}
	
	private List<ServiceRequestMasterDTO> serviceRequestMasterDTOList = new ArrayList<ServiceRequestMasterDTO>();
	private List<CpRequestMaster> requestfetch = new ArrayList<CpRequestMaster>();
	private List<ServiceRequestMasterDTO> filteredList;
	
	public String servicelist() {
		requestfetch = transactionBL.servicerequestfetch();	
		ServiceRequestMasterDTO newa;
		if(requestfetch.size()>0) {
			for(int i=0; i<requestfetch.size();i++) {
				newa = new ServiceRequestMasterDTO();
				newa.setServiceRequestNo(requestfetch.get(i).getServiceRequestNo());
				newa.setServiceRequestType(requestfetch.get(i).getServiceRequestType());
				newa.setUserId(requestfetch.get(i).getUserId());
				newa.setPolicyNo(requestfetch.get(i).getPolicyNo());
				newa.setRequestStatus(requestfetch.get(i).getRequestStatus());
				newa.setServiceRequestDate(requestfetch.get(i).getServiceRequestDate());
				serviceRequestMasterDTOList.add(newa);
			}
		}else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
					"No Users Performed any transaction from portal yet.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
		return "/businessuser/servicetransaction.jsf?faces-redirect=true";
	}
	
	public void btnSaveDocumentList(){
		uploadStatus=false;		
		boolean docStatus=true;
		for (DocumentDTO docDto : documentList) {			
			if (docDto.getDocId() == 0) {
				if (docDto.getDocFilepath() == null) {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
							"Choose a document file. File should not be empty");
					PrimeFaces.current().dialog().showMessageDynamic(message);
					logger.info("Error at saving documents :File path empty " );
					docStatus = false;
				}
				docDto.setLastupdDate(new Date());
				docDto.setLastupdProg(Constants.DOCUMENTUPLOAD);
				docDto.setLastupdUser(SecurityLibrary.getFullLoggedInUser().getVuserName());
			}
		}
		if(docStatus){
			List<DocumentDTO> newDocList = new ArrayList<DocumentDTO>();
			for (DocumentDTO dto : documentList) {
				if(dto.getDocId() == 0){					
					newDocList.add(dto);
				}
			}
			if(BSLUtils.isNotNull(newDocList))
			if(newDocList.size()>0){
			uploadStatus = documentBL.saveAllDocuments(newDocList);	
			logger.info("Saving all documents status: "+uploadStatus );
			}
		}
		
		if(uploadStatus){   
			documentList = documentBL.findAllDocuments();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
					"Document Upload successful..");
			PrimeFaces.current().dialog().showMessageDynamic(message);

			//Find all user group by group type
			
			/*
			 * List<CpUserInfo> cpUsersByGroup = cpUserInfoDAO.getCpUsersByGroup("U");
			 * 
			 * 
			 * //Bulk SMS Trigger String smsResponse =
			 * messageQueue.sendSMSTOQueue(cpUsersByGroup,"BUSINESS USER DOC UPLOAD",null);
			 * logger.
			 * info("Response form SMS queue while uploading document by BUSINESS USER : "
			 * +smsResponse+"");
			 * 
			 * //Bulk Email Trigger String emailResponse =
			 * messageQueue.sendMailTOQueue(cpUsersByGroup,"BUSINESS USER DOC UPLOAD",null);
			 * logger.
			 * info("Response form message queue while uploading document by BUSINESS USER : "
			 * +emailResponse+"");
			 */
			 
			
			logger.info("Showing success message for document saved ");
		}
	}
	
	
	CPServiceRequestDocuments cPServiceRequestDocuments;
    @Autowired
    CPServiceRequestDocumentsBL cPServiceRequestDocumentsBL;
    
	
	public StreamedContent custDownloading(ServiceRequestMasterDTO objectrequest){
		logger.info("Download document started..");
		StreamedContent downloadFile=new DefaultStreamedContent();
		if(objectrequest.getPolicyNo()!=null) {
			cPServiceRequestDocuments = new CPServiceRequestDocuments();
			cPServiceRequestDocuments=cPServiceRequestDocumentsBL.fetchdata(objectrequest.getServiceRequestNo());
			if(cPServiceRequestDocuments.getFileName()!=null) {
				String policyNo=objectrequest.getPolicyNo();
				int requestno=objectrequest.getServiceRequestNo();
				String targetFolder=AppSettingURL.SERVICE_DOCUMENT_LOCATION;
				String policyreuqestlocation = targetFolder+"\\"+policyNo+"\\"+requestno;
				File creatingnewFolder = new File(policyreuqestlocation);
				if(creatingnewFolder.exists()) {
					File file=new File(policyreuqestlocation+"\\"+cPServiceRequestDocuments.getFileName());
					InputStream input =null;
					try {
						input =new FileInputStream(file);
						ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
						downloadFile = new DefaultStreamedContent(input, ec.getMimeType(file.getName()),file.getName());
						System.out.println("Prep :"+downloadFile.getName());
						return downloadFile;
					} catch (FileNotFoundException e) {			
						e.printStackTrace();
						logger.info("Error at download document: "+e.getMessage());
					}
				}else {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
							"No Documents Uploaded during service transactions");
					PrimeFaces.current().dialog().showMessageDynamic(message);
				}
			}else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"No Documents Uploaded during service transactions");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		}
		return null;
	}	

	
	public void check(DocumentDTO documentDTO, int index) {
		if(documentDTO.getDocName()!=null) {
			String username = SecurityLibrary.getFullLoggedInUser().getVuserName();
			documentDTO.setUploadUser(username);
			documentList.set(index, documentDTO);
		}
	}
	
	public void btnDeleteDocument(DocumentDTO documentDTO){
		System.out.println(documentDTO.getDocId());
		logger.info("Deleting document from database and location");
		boolean deleteStatus; 
			deleteStatus = documentBL.deleteDocument(documentDTO);
		String sourcePath=AppSettingURL.DOCUMENT_LOCATION;
		String targetFile=sourcePath+documentDTO.getDocFilepath();		
		FileUtils.deleteFile(targetFile);			
		if(deleteStatus){
			documentList = documentBL.findAllDocuments();
			displaySuccessMessage("documentAction.upload.deletesuccess");			 			
		}
	}
	
	
	public void btnAddRow(){
		logger.info("Add new Row");
		DocumentDTO docDto =new DocumentDTO();
		docDto.setUploadUser(SecurityLibrary.getFullLoggedInUser().getVuserName());
		docDto.setUploadDate(new Date());
		documentList.add(docDto);		
	}

	public void linkDelRow(DocumentDTO documentDTO){
		System.out.println(documentDTO);
		logger.info("Deleting document from location and delete row");
		String sourcePath=AppSettingURL.DOCUMENT_LOCATION;
		String targetFile=sourcePath+documentDTO.getDocFilepath();		
		FileUtils.deleteFile(targetFile);
		documentList.remove(documentDTO);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
				"Row deleted.Upload canceled..");
		PrimeFaces.current().dialog().showMessageDynamic(message);
	}			
	
	
	public void btnUploadFile(FileUploadEvent event){		
		logger.info("Uploading file to the location");
		System.out.println("reading fileName: "+event.getFile().getFileName());	
		String sourcePath=AppSettingURL.DOCUMENT_LOCATION;
		boolean status =true;								
		UploadedFile upFile=event.getFile();		
		try {
			status = FileUtils.springCopyFile(sourcePath, upFile);
		} catch (Exception e) {
			status = false;
			e.printStackTrace();
		}		
		if(status){
			logger.info("Set docpath name to the newly added the row");
			DocumentDTO docDto=  (DocumentDTO) event.getComponent().getAttributes().get("documentDTO");
			docDto.setDocFilepath(event.getFile().getFileName());			
		}else{
			logger.info("File name Exist error");
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
					"File name is already mentioned. Use a different file name.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
	}
	
	//Customer
	public StreamedContent custDownload(DocumentDTO docDto){
		logger.info("Download document started..");
		StreamedContent downloadFile=new DefaultStreamedContent();
		String targetFolder=AppSettingURL.DOCUMENT_LOCATION;
		File file=new File(targetFolder+docDto.getDocFilepath());
		InputStream input =null;
		try {
			input =new FileInputStream(file);
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			downloadFile = new DefaultStreamedContent(input, ec.getMimeType(file.getName()),file.getName());
			System.out.println("Prep :"+downloadFile.getName());
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
			logger.info("Error at download document: "+e.getMessage());
		}
		return downloadFile;
	}
	

	public List<DocumentDTO> getDocumentList() {
		return documentList;
	}

	public void setDocumentBL(DocumentBLImpl documentBL) {
		this.documentBL = documentBL;
	}

	public void setDocumentList(List<DocumentDTO> documentList) {
		this.documentList = documentList;
	}	

	public boolean isUploadStatus() {
		return uploadStatus;
	}

	public void setUploadStatus(boolean uploadStatus) {
		this.uploadStatus = uploadStatus;
	}

	public String getBodyMsg() {
		return bodyMsg;
	}

	public void setBodyMsg(String bodyMsg) {
		this.bodyMsg = bodyMsg;
	}




	public List<ServiceRequestMasterDTO> getServiceRequestMasterDTOList() {
		return serviceRequestMasterDTOList;
	}




	public void setServiceRequestMasterDTOList(List<ServiceRequestMasterDTO> serviceRequestMasterDTOList) {
		this.serviceRequestMasterDTOList = serviceRequestMasterDTOList;
	}




	public List<CpRequestMaster> getRequestfetch() {
		return requestfetch;
	}




	public void setRequestfetch(List<CpRequestMaster> requestfetch) {
		this.requestfetch = requestfetch;
	}




	public List<ServiceRequestMasterDTO> getFilteredList() {
		return filteredList;
	}




	public void setFilteredList(List<ServiceRequestMasterDTO> filteredList) {
		this.filteredList = filteredList;
	}

	
	
	
	

}
