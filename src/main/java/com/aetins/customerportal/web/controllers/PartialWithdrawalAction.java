package com.aetins.customerportal.web.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aetins.customerportal.core.faceletstaglib.SecurityLibrary;
import com.aetins.customerportal.web.common.Constants;
import com.aetins.customerportal.web.dao.CpNFPersonalDetailsDAO;
import com.aetins.customerportal.web.dto.BankCodeLOVDTO;
import com.aetins.customerportal.web.dto.CpPartialWithdrawalFundsDTO;
import com.aetins.customerportal.web.dto.CpTermAndConditionDTO;
import com.aetins.customerportal.web.dto.FundDetailsDTO;
import com.aetins.customerportal.web.dto.PartialWithdrawalDTO;
import com.aetins.customerportal.web.dto.ServiceReqNotesDTO;
import com.aetins.customerportal.web.dto.ServiceRequestMasterDTO;
import com.aetins.customerportal.web.entity.CPServiceRequestDocuments;
import com.aetins.customerportal.web.entity.CpPartialWithdrawalFunds;
import com.aetins.customerportal.web.entity.CpRequestMaster;
import com.aetins.customerportal.web.service.CPServiceRequestDocumentsBL;
import com.aetins.customerportal.web.service.CPartialWithdrawalFundsBL;
import com.aetins.customerportal.web.service.ContributionHolidaylBL;
import com.aetins.customerportal.web.service.CustomerPortalServices;
import com.aetins.customerportal.web.service.RedirectionBL;
import com.aetins.customerportal.web.utils.BSLUtils;
import com.aetins.customerportal.web.validators.OtpValidation;

@Controller(value ="partialWithdrawalAction")
@Scope("session")
public class PartialWithdrawalAction extends BaseAction {
	
	private static final Logger logger = LoggerFactory.getLogger(PartialWithdrawalAction.class);

	private List<FundDetailsDTO> fundsDetailsList;
	private List<PartialWithdrawalDTO> partialWithdrawalList;

	private List<CpTermAndConditionDTO> termsConditionYesYes;
	private List<CpTermAndConditionDTO> termsConditionYesNo;

	private String policyNo;
	private String premiumStatus;
	private String planName;
	private BigDecimal seqno;
	private String userId;
	private boolean criteriaflag=false;
	private boolean submitflag=false;
    private boolean cumaltive=false;
    private boolean termsCondition;
    
	@Autowired
	RedirectionBL redirectImpl;

	@Autowired
	CustomerPortalServices customerPortalServices;
	
	@Autowired
	CpNFPersonalDetailsDAO cpNFPersonalDetailsDAOImpl;
	
	@Autowired
	TransactionServiceAction transAction;

    @Autowired
    CPartialWithdrawalFundsBL cPartialWithdrawalFundsBL;
	
	private List<ServiceReqNotesDTO> noteslist = new ArrayList<ServiceReqNotesDTO>();
	private List<ServiceReqNotesDTO> noteslist1 = new ArrayList<ServiceReqNotesDTO>();
	ServiceReqNotesDTO notes;
	private PartialWithdrawalDTO selectedPartialWithdrawalDTO;
	private List<BankCodeLOVDTO> bankCodeLOVDTOlist = new ArrayList<BankCodeLOVDTO>();
	private ServiceRequestMasterDTO requestdto;
	private List<CpPartialWithdrawalFundsDTO> cpPartialWithdrawalFundsDTOlist;
	
	private CPServiceRequestDocuments dto;
	private List<CPServiceRequestDocuments> listing = new ArrayList<CPServiceRequestDocuments>();;
    private boolean adding;

	//Added by kathar
	
	@PostConstruct
	public void init() {
//		try {
//			FacesContext context = FacesContext.getCurrentInstance();
//			HomeDetailsAction hAction = context.getApplication().evaluateExpressionGet(context, "#{homeDetailsAction}",
//					HomeDetailsAction.class);
//			policyNo = hAction.getSearchPolicyList().getPlanNumber();
//			premiumStatus = hAction.getSearchPolicyList().getPremiumPayStatus();
//			planName = hAction.getSearchPolicyList().getProductName();
//			userId=SecurityLibrary.getFullLoggedInUser().getVuserName();
//			if (hAction.getPolicyDetailsList().size() > 0) {
//				for (int i = 0; i < hAction.getPolicyDetailsList().size(); i++) {
//					if (policyNo.equalsIgnoreCase(hAction.getPolicyDetailsList().get(i).getPolicyNo())) {
//						seqno = hAction.getPolicyDetailsList().get(i).getSeqno();
//					}
//				}
//			}
//			fundsDetailsList = customerPortalServices.getPartialFundDetails(policyNo, Constants.LANGUAGE_EN, seqno);
//			if(fundsDetailsList.size()<0) {
//				submitflag=true;
//			}else {
//				submitflag=false;
//			}
//			noteslist = customerPortalServices.getservicerequestnotes(policyNo, Constants.LANGUAGE_EN);
//			if (noteslist.size() > 0) {
//				for (int i = 0; i < noteslist.size(); i++) {
//					notes = new ServiceReqNotesDTO();
//					notes.setNotes(noteslist.get(i).getNotes());
//					noteslist1.add(notes);
//				}
//			}
//			
//			termsConditionYesYes = redirectImpl.listTermAndCondition(Constants.PARTIAL_WITHDRAWAL_SERVICE_REQUEST_TYPE,
//				Constants.MANDATORY, Constants.REQUIRED);
//			termsConditionYesNo = redirectImpl.listTermAndCondition1(Constants.PARTIAL_WITHDRAWAL_SERVICE_REQUEST_TYPE,
//					Constants.MANDATORYNO, Constants.REQUIREDYES);
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
	}
	
	private String certicatenumber;
	private String investboolean;
	private String investmentsourcode;
	private String withdrawcritaria;
	
	public String partialfetch() {
		FacesContext context = FacesContext.getCurrentInstance();
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		HomeDetailsAction hAction = context.getApplication().evaluateExpressionGet(context, "#{homeDetailsAction}",
				HomeDetailsAction.class);
		dto=null;
		listing=null;
		listing = new ArrayList<CPServiceRequestDocuments>();
		adding=true;
		withdrawcritaria=null;
		
		policyNo = hAction.getSearchPolicyList().getPlanNumber();
		premiumStatus = hAction.getSearchPolicyList().getPremiumPayStatus();
		planName = hAction.getSearchPolicyList().getProductName();
		userId=SecurityLibrary.getFullLoggedInUser().getVuserName();
		
		if (hAction.getPolicyDetailsList().size() > 0) {
			for (int i = 0; i < hAction.getPolicyDetailsList().size(); i++) {
				if (policyNo.equalsIgnoreCase(hAction.getPolicyDetailsList().get(i).getPolicyNo())) {
					seqno = hAction.getPolicyDetailsList().get(i).getSeqno();
				}
			}
		}
		
		docupath=false;
		getSession().setAttribute("UPLOADFILENAME", "EMPTY");
		
		getSession().setAttribute("UPLOADFILE1", "");
		getSession().setAttribute("UPLOADFILE2", "");
		getSession().setAttribute("UPLOADFILE3", "");
		getSession().setAttribute("UPLOADFILE4", "");
		getSession().setAttribute("OTPSUCC", "");
		
		fundsDetailsList = null; 
		fundsDetailsList = new ArrayList<FundDetailsDTO>();
		fundsDetailsList = customerPortalServices.getPartialFundDetails(policyNo, locale.toString().equalsIgnoreCase("ar")?"AR":"EN", seqno);
		if(fundsDetailsList.size()<0) {
			submitflag=true;
		}else {
			submitflag=false;
		}
		
		investboolean =  null;
		investmentsourcode =  null;
		investboolean = customerPortalServices.getcheckalu(locale.toString().equalsIgnoreCase("ar")?"AR":"EN", policyNo);     
		if(BSLUtils.isNotNull(investboolean)&&investboolean.equalsIgnoreCase("Y")) {
			investmentsourcode="IU-AU";
		}else {
			investmentsourcode="IU";
		}
		
		termsConditionYesYes = redirectImpl.listTermAndCondition(Constants.PARTIAL_WITHDRAWAL_SERVICE_REQUEST_TYPE,
			Constants.MANDATORY, Constants.REQUIRED);
		termsConditionYesNo = redirectImpl.listTermAndCondition1(Constants.PARTIAL_WITHDRAWAL_SERVICE_REQUEST_TYPE,
				Constants.MANDATORYNO, Constants.REQUIREDYES);

		fatcaValidation();
		
		return "/user/partialwithdraw?faces-redirect=true";
	}
	
	Date serDate;
	private boolean fatcaFlagpopUp;
	private String fatcaFlag;
	
	@Autowired
	ContributionHolidaylBL contributionHolidaylBL;
	
	public void fatcaValidation() {
		   serDate = null;
		   int diffInDays = 0;
		   fatcaFlag = null;
		   fatcaFlagpopUp = false;
		List<CpRequestMaster> requestDate = contributionHolidaylBL.getTransactionCount(SecurityLibrary.getFullLoggedInUser().getVuserName(), "Y");
		logger.info("Fatca Master list size is in UpdateInformationAction ================" + requestDate.size());
		if (requestDate.size() > 0) {
			serDate = requestDate.get(0).getServiceRequestDate();
			diffInDays = (int) ((new Date().getTime() - serDate.getTime()) / (1000 * 60 * 60 * 24));
			System.out.println(diffInDays);
		}
		logger.info("Difference in days for fatca validation in UpdteInformationAction ==============" + diffInDays);
		if (diffInDays > 90 || requestDate.size() == 0) {
			fatcaFlag = "N";
			fatcaFlagpopUp = false;
			getSession().setAttribute("FATCAFLAG", fatcaFlag);
		} else {
			fatcaFlag = "Y";
			fatcaFlagpopUp = true;
			getSession().setAttribute("FATCAFLAG", fatcaFlag);
		}
		logger.info("Fatca flag Status inside UpdateInformationAction =================" + fatcaFlag);
		logger.info("FatcaFlagpopUp flag Status UpdateInformationAction ===================" + fatcaFlagpopUp);
	}
	
	public void btnAddRow(){
		logger.info("Add new Row");
		CPServiceRequestDocuments docDto =new CPServiceRequestDocuments();
		if(listing.size()==0) {
			docDto.setSno(1);
			listing.add(docDto);	
		}else if(listing.size()==1) {
			docDto.setSno(2);
			listing.add(1, docDto);
		}else if(listing.size()==2) {
			docDto.setSno(3);
			listing.add(2, docDto);
		}else if(listing.size()==3) {
			docDto.setSno(4);
			listing.add(3, docDto);
		}
		System.out.println(listing.size());
	}
	
	public void delete() {
		listing.clear();
		listing=null;
		listing= new ArrayList<CPServiceRequestDocuments>();
		adding=true;
	}
		
	public void btnUploadFile(FileUploadEvent event){		
		logger.info("Uploading file to the location");
		CPServiceRequestDocuments dto = null;
		dto = new CPServiceRequestDocuments();
		System.out.println("reading fileName: "+event.getFile().getFileName());		
		upFile=event.getFile();
		
		System.out.println(upFile.getFileName());
    	if(upFile.getFileName()!=null) {
    		if(listing.get(0).getSno()==1 && listing.get(0).getFileName()==null) {
    			dto.setFileName(upFile.getFileName());
    			dto.setFileSize(upFile.getSize());
    			dto.setSno(1);
    			listing.set(0, dto);
    			adding=true;
    			getSession().setAttribute("UPLOADFILE1", upFile);
    		}else if(listing.get(1).getSno()==2 && listing.get(1).getFileName()==null) {
    			dto.setFileName(upFile.getFileName());
    			dto.setFileSize(upFile.getSize());
    			dto.setSno(2);
    			listing.set(1, dto);
    			adding=true;
    			getSession().setAttribute("UPLOADFILE2", upFile);
    		}else if(listing.get(2).getSno()==3 && listing.get(2).getFileName()==null) {
    			dto.setFileName(upFile.getFileName());
    			dto.setFileSize(upFile.getSize());
    			dto.setSno(3);
    			listing.set(2, dto);	
    			adding=true;
    			getSession().setAttribute("UPLOADFILE3", upFile);
    		}else if(listing.get(3).getSno()==4 && listing.get(3).getFileName()==null) {
    			dto.setFileName(upFile.getFileName());
    			dto.setFileSize(upFile.getSize());
    			dto.setSno(4);
    			listing.set(3, dto);	
    			adding=false;
    			getSession().setAttribute("UPLOADFILE4", upFile);
    		}
    	}
	}
	
	private String docFilepath;
    private int docFilesize;
    UploadedFile upFile;
    private boolean docupath;
    CPServiceRequestDocuments cPServiceRequestDocuments;
    @Autowired
    CPServiceRequestDocumentsBL cPServiceRequestDocumentsBL;
    
    public void upload(FileUploadEvent event) {
    	logger.info("Uploading file to the location");
    	System.out.println("reading fileName: "+event.getFile().getFileName());
    	upFile=event.getFile();
    	System.out.println(upFile.getFileName());
    	if(upFile.getFileName()!=null) {
    		docupath=true;
    		docFilepath=upFile.getFileName();
        	docFilesize=(int) upFile.getSize();
        	getSession().setAttribute("UPLOADFILE", upFile);
        	getSession().setAttribute("UPLOADFILENAME", docFilepath);
        	getSession().setAttribute("UPLOADFILESize", docFilesize);
    	}
    }

	
	
	public int transStatus() {
		Long count = cpNFPersonalDetailsDAOImpl.getRowCount(Constants.PARTIAL_WITHDRAWAL_SERVICE_REQUEST_TYPE, policyNo);
		System.out.println("count==" + count);
		long tmp = 0;
		int output = count.compareTo(tmp);
		return output;
	}

	public void drop(FundDetailsDTO obj, int index) {
		if(obj!=null) {
			fundsDetailsList.get(index).setFundCriteriaValue(0);
		}
	}
	
	
	public void dropdown() {
		cumaltive=true;
	}
	
	
	public void validation(FundDetailsDTO obj) {	
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		FacesMessage message = new FacesMessage();
		if(withdrawcritaria.equalsIgnoreCase("A")) {	
			if(obj.getFundCriteriaValue()>0) {
				BigDecimal asas = obj.getFundValueInFundCrr();
				int j=asas.intValue();
				if(obj.getFundCriteriaValue() < j) {
		        	criteriaflag=true;
		        }else {
		        	criteriaflag=false;
		        	obj.setFundCriteriaValue(0);
		        	if(locale.toString().equalsIgnoreCase("ar")) {
		        		message = new FacesMessage(FacesMessage.SEVERITY_WARN, "رسالة", "من فضلك معايير القيمة يجب أن تكون أقل من" + 
		        				"قيمة الصندوق \"\r\n" + 
		        				"");
		        	}else {
		        		message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message", "Please Criteria Value is should be lesser to Fund Value");
		        	}
					PrimeFaces.current().dialog().showMessageDynamic(message);
		        }
			}else {
				criteriaflag=false;
				if(locale.toString().equalsIgnoreCase("ar")) {
					message = new FacesMessage(FacesMessage.SEVERITY_WARN, "رسالة", "االرجاء إدخال قيمة المعايير" + 
							"");
				}else {
					message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message", "Please enter Criteria Value");
				}
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		} else if(withdrawcritaria.equalsIgnoreCase("P")) {
			if(obj.getFundCriteriaValue()>0) {
				if(obj.getFundCriteriaValue()<100) {
					criteriaflag=true;
				}else {
					criteriaflag=false;
					obj.setFundCriteriaValue(0);
					if(locale.toString().equalsIgnoreCase("ar")) {
						message = new FacesMessage(FacesMessage.SEVERITY_WARN, "رسالة", "الرجاء إدخال قيمة المعايير داخل" + 
								"100 في المائة \"\r\n" + 
								"");
					}else {
						message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message", "Please enter the Criteria Value within 100 Percentage");
					}	
					PrimeFaces.current().dialog().showMessageDynamic(message);
				}
			}else {
				criteriaflag=false;
				if(locale.toString().equalsIgnoreCase("ar")) {
					message = new FacesMessage(FacesMessage.SEVERITY_WARN, "رسالة", "الرجاء إدخال قيمة المعايير" + 
							"");
				}else {
					message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message", "Please enter the Criteria Value");
				}
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		} else if(withdrawcritaria.equalsIgnoreCase("")||withdrawcritaria==null) {
			criteriaflag=false;
			obj.setFundCriteriaValue(0);
			if(locale.toString().equalsIgnoreCase("ar")) {
				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "رسالة", "الرجاء تحديد معايير السحب إلى" + 
						"نفذ الصفقة \"\r\n" + 
						"");
			}else {
				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message", "Please Select the Withdraw Criteria to Perform the Transaction");
			}
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
	}
	
	private List<CpPartialWithdrawalFunds> cpPartialWithdrawalFunds= new ArrayList<CpPartialWithdrawalFunds>();
	
	public void checking() {
		for(FundDetailsDTO obj: fundsDetailsList) {
			obj.setFundCriteriaValue(0);
		}
	}
	
	private String output;
	private boolean allowornot;
	
	public void checkwithdrawallow(String policyNo, BigDecimal seqno) {
		allowornot = false;
		output = null;
		FacesContext context = FacesContext.getCurrentInstance();
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		output=customerPortalServices.getcheckpartialwithdraw(locale.toString().equalsIgnoreCase("ar")?"AR":"EN", policyNo, seqno, "");
	    if(output==null) {
	    	allowornot = true;
	    }else {
	    	allowornot = false;
	    }
	}
	
	public void btncontinuelater() {
		int transCount = transStatus();
		String result = customerPortalServices.getAllowedTransaction(policyNo, "PARTIALWITHDRAW");
		logger.info("Transaction fn called for Update Contacts ==============" + result);
		logger.info("Inside btnContinueLater of UpdateInformationAction Transaction pending " + "count is ======="+ transCount);	
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		FacesMessage message = new FacesMessage();
		if (BSLUtils.isNotNull(result)) {
			if (result.equalsIgnoreCase("ALLOWED")) {
				if (transCount == 0) {
					try {
							String applicaleBox = "Y";
			  				checkwithdrawallow(policyNo, seqno);
							if(allowornot == true) {
								requestdto = new ServiceRequestMasterDTO();
								Date date = new Date();
								requestdto.setServiceRequestDate(date);
								requestdto.setServiceRequestType(Constants.PARTIAL_WITHDRAWAL_SERVICE_REQUEST_TYPE);
								requestdto.setPolicyNo(policyNo);
								requestdto.setUserId(userId);
								requestdto.setRequestStatus(Constants.UPDATE_INFO_CONTINUE);
								requestdto.setRecentUpdateDate(date);
								requestdto.setApplicable(applicaleBox);
								requestdto.setSeqno(seqno);
								//requestdto.setCerticate(certicatenumber);
								logger.info("Insert and Get New Request Details started");
								requestdto = transAction.insertServiceRequest(requestdto);
								
								cpPartialWithdrawalFundsDTOlist=null;
								cpPartialWithdrawalFundsDTOlist=new ArrayList<CpPartialWithdrawalFundsDTO>();
								boolean purposechecking = false;
								CpPartialWithdrawalFundsDTO cpPartialWithdrawalFundsDTO;
								for(int i=0;i<fundsDetailsList.size();i++) {
									if(withdrawcritaria.equalsIgnoreCase("")||withdrawcritaria==null||fundsDetailsList.get(i).getFundCriteriaValue()==0) {
								
									}else {
										cpPartialWithdrawalFundsDTO = new CpPartialWithdrawalFundsDTO();
										String fundvalue = new String();
										int fundvalue1=0;
										cpPartialWithdrawalFundsDTO.setnServicRequestNo(requestdto);
										cpPartialWithdrawalFundsDTO.setvFundType("C");
										cpPartialWithdrawalFundsDTO.setvFundCode(fundsDetailsList.get(i).getFundCode());
										cpPartialWithdrawalFundsDTO.setvFundName(fundsDetailsList.get(i).getFundName());
										cpPartialWithdrawalFundsDTO.setvFundCurrency(fundsDetailsList.get(i).getFundCrr());
										cpPartialWithdrawalFundsDTO.setInvestsourcode(investmentsourcode);
										cpPartialWithdrawalFundsDTO.setRemarks("");
										int sequnceno = seqno.intValue();
										cpPartialWithdrawalFundsDTO.setSeqno(sequnceno);
										cpPartialWithdrawalFundsDTO.setnFundValue(fundsDetailsList.get(i).getFundValueInFundCrr());
										cpPartialWithdrawalFundsDTO.setvCriteria(withdrawcritaria);
										cpPartialWithdrawalFundsDTO.setnCriteriaValue(fundsDetailsList.get(i).getFundCriteriaValue());								
										cpPartialWithdrawalFundsDTOlist.add(cpPartialWithdrawalFundsDTO);
										purposechecking = true;
									}
								}
								
								if(purposechecking==true) {
									boolean statuss = cPartialWithdrawalFundsBL.insert(cpPartialWithdrawalFundsDTOlist);
									if(statuss=true) {
										getSession().setAttribute("OTPSUCC", "DONE");
										getSession().setAttribute("TRANSACTION", "PARTIAL");
										PrimeFaces.current().executeScript("PF('dlg3').show()");
									}else {
										PrimeFaces.current().executeScript("PF('dlg3').hide()");
									}
								}else {
									if(locale.toString().equalsIgnoreCase("ar")) {
			                    		  message = new FacesMessage(FacesMessage.SEVERITY_WARN, "رسالة", "تحقق من العملية وحاول مرة أخرى أو اتصل على فريق الدعم" + 
			                    		  		"");
			                    	  }else {
			                    		  message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message", "Check the process and try again or contact Salama Support Team.");
			                    	  }
			                    	  PrimeFaces.current().dialog().showMessageDynamic(message);
			                    	  PrimeFaces.current().executeScript("PF('dlg3').hide()");
								}	    	 
							}else {
								if(locale.toString().equalsIgnoreCase("ar")) {
		                    		  message = new FacesMessage(FacesMessage.SEVERITY_WARN, "رسالة", "output");
		                    	  }else {
		                    		  message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message", output);
		                    	  }
		                    	  PrimeFaces.current().dialog().showMessageDynamic(message);
							}
						
					}catch(Exception e) {
						logger.info("Error at saving update information: " + e.getMessage());
						e.printStackTrace();
					}
				}else {
					if(locale.toString().equalsIgnoreCase("ar")) {
						message = new FacesMessage(FacesMessage.SEVERITY_WARN, "رسالة", "طلب المعاملة القديم الخاص بك قيد المعالجة" + 
								"");
					}else {
						message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message", "Your old transaction request is in process");
					}
					PrimeFaces.current().dialog().showMessageDynamic(message);
				}
			}else {
				if(locale.toString().equalsIgnoreCase("ar")) {
					message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "رسالة",	"لطلب غير مؤهل لأن حالة الخطة غير نشطة ، يرجى التواصل مع الراجحي");
				}else {
					message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",	"Request is Not Eligible as the Plan status is inactive.Please contact Salama.");
				}
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		}
		
	}
	
    private String processing;
	
	public String fatcasample() {
		if(processing.equalsIgnoreCase("YES")) {
			return "/factaFrom/partialfatca?faces-redirect=true";
		}else {
		   return null;	
		}
	}

	public void btncontinuelaterfatca() {
		processing = null;
		processing = "NO";
		int transCount = transStatus();
		String result = customerPortalServices.getAllowedTransaction(policyNo, "PARTIALWITHDRAW");
		logger.info("Transaction fn called for Update Contacts ==============" + result);
		logger.info("Inside btnContinueLater of UpdateInformationAction Transaction pending " + "count is ======="+ transCount);	
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		FacesMessage message = new FacesMessage();
		if (BSLUtils.isNotNull(result)) {
			if (result.equalsIgnoreCase("ALLOWED")) {
				if (transCount == 0) {
					try {
							String applicaleBox = "Y";
			  				checkwithdrawallow(policyNo, seqno);
							if(allowornot == true) {
								requestdto = new ServiceRequestMasterDTO();
								Date date = new Date();
								requestdto.setServiceRequestDate(date);
								requestdto.setServiceRequestType(Constants.PARTIAL_WITHDRAWAL_SERVICE_REQUEST_TYPE);
								requestdto.setPolicyNo(policyNo);
								requestdto.setUserId(userId);
								requestdto.setRequestStatus(Constants.UPDATE_INFO_CONTINUE);
								requestdto.setRecentUpdateDate(date);
								requestdto.setApplicable(applicaleBox);
								requestdto.setSeqno(seqno);
								//requestdto.setCerticate(certicatenumber);
								logger.info("Insert and Get New Request Details started");
								requestdto = transAction.insertServiceRequest(requestdto);
								
								cpPartialWithdrawalFundsDTOlist=null;
								cpPartialWithdrawalFundsDTOlist=new ArrayList<CpPartialWithdrawalFundsDTO>();
								boolean purposechecking = false;
								CpPartialWithdrawalFundsDTO cpPartialWithdrawalFundsDTO;
								for(int i=0;i<fundsDetailsList.size();i++) {
									if(withdrawcritaria.equalsIgnoreCase("")||withdrawcritaria==null||fundsDetailsList.get(i).getFundCriteriaValue()==0) {
								
									}else {
										cpPartialWithdrawalFundsDTO = new CpPartialWithdrawalFundsDTO();
										String fundvalue = new String();
										int fundvalue1=0;
										cpPartialWithdrawalFundsDTO.setnServicRequestNo(requestdto);
										cpPartialWithdrawalFundsDTO.setvFundType("C");
										cpPartialWithdrawalFundsDTO.setvFundCode(fundsDetailsList.get(i).getFundCode());
										cpPartialWithdrawalFundsDTO.setvFundName(fundsDetailsList.get(i).getFundName());
										cpPartialWithdrawalFundsDTO.setvFundCurrency(fundsDetailsList.get(i).getFundCrr());
										cpPartialWithdrawalFundsDTO.setInvestsourcode(investmentsourcode);
										cpPartialWithdrawalFundsDTO.setRemarks("");
										int sequnceno = seqno.intValue();
										cpPartialWithdrawalFundsDTO.setSeqno(sequnceno);
										cpPartialWithdrawalFundsDTO.setnFundValue(fundsDetailsList.get(i).getFundValueInFundCrr());
										cpPartialWithdrawalFundsDTO.setvCriteria(withdrawcritaria);
										cpPartialWithdrawalFundsDTO.setnCriteriaValue(fundsDetailsList.get(i).getFundCriteriaValue());								
										cpPartialWithdrawalFundsDTOlist.add(cpPartialWithdrawalFundsDTO);
										purposechecking = true;
									}
								}
								
								if(purposechecking==true) {
									boolean statuss = cPartialWithdrawalFundsBL.insert(cpPartialWithdrawalFundsDTOlist);
									if(statuss=true) {
										processing = "YES";
										getSession().setAttribute("OTPSUCC", "DONE");
										getSession().setAttribute("TRANSACTION", "PARTIAL");
										PrimeFaces.current().executeScript("PF('dlg3').hide()");
										FacesContext context = FacesContext.getCurrentInstance();
										OtpValidation pAAction = context.getApplication().evaluateExpressionGet(context, "#{otpValidation}",
												OtpValidation.class);
										pAAction.fatcall();
										fatcaFlagpopUp = (boolean) getSession().getAttribute("FATCAFLAGPOP");
									}else {
										PrimeFaces.current().executeScript("PF('dlg3').hide()");
									}
								}else {
									if(locale.toString().equalsIgnoreCase("ar")) {
			                    		  message = new FacesMessage(FacesMessage.SEVERITY_WARN, "رسالة", "تحقق من العملية وحاول مرة أخرى أو اتصل على فريق الدعم" + 
			                    		  		"");
			                    	  }else {
			                    		  message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message", "Check the process and try again or contact Salama Support Team.");
			                    	  }
			                    	  PrimeFaces.current().dialog().showMessageDynamic(message);
			                    	  PrimeFaces.current().executeScript("PF('dlg3').hide()");
								}	    	 
							}else {
								if(locale.toString().equalsIgnoreCase("ar")) {
		                    		  message = new FacesMessage(FacesMessage.SEVERITY_WARN, "رسالة", "output");
		                    	  }else {
		                    		  message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message", output);
		                    	  }
		                    	  PrimeFaces.current().dialog().showMessageDynamic(message);
							}
					}catch(Exception e) {
						logger.info("Error at saving update information: " + e.getMessage());
						e.printStackTrace();
					}
				}else {
					if(locale.toString().equalsIgnoreCase("ar")) {
						message = new FacesMessage(FacesMessage.SEVERITY_WARN, "رسالة", "طلب المعاملة القديم الخاص بك قيد المعالجة" + 
								"");
					}else {
						message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message", "Your old transaction request is in process");
					}
					PrimeFaces.current().dialog().showMessageDynamic(message);
				}
			}else {
				if(locale.toString().equalsIgnoreCase("ar")) {
					message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "رسالة",	"لطلب غير مؤهل لأن حالة الخطة غير نشطة ، يرجى التواصل مع الراجحي");
				}else {
					message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",	"Request is Not Eligible as the Plan status is inactive.Please contact Salama.");
				}
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		}
		
	}
	

	public List<FundDetailsDTO> getFundsDetailsList() {
		return fundsDetailsList;
	}

	public void setFundsDetailsList(List<FundDetailsDTO> fundsDetailsList) {
		this.fundsDetailsList = fundsDetailsList;
	}


	public List<PartialWithdrawalDTO> getPartialWithdrawalList() {
		return partialWithdrawalList;
	}


	public void setPartialWithdrawalList(List<PartialWithdrawalDTO> partialWithdrawalList) {
		this.partialWithdrawalList = partialWithdrawalList;
	}


	public List<CpTermAndConditionDTO> getTermsConditionYesYes() {
		return termsConditionYesYes;
	}


	public void setTermsConditionYesYes(List<CpTermAndConditionDTO> termsConditionYesYes) {
		this.termsConditionYesYes = termsConditionYesYes;
	}


	public List<CpTermAndConditionDTO> getTermsConditionYesNo() {
		return termsConditionYesNo;
	}


	public void setTermsConditionYesNo(List<CpTermAndConditionDTO> termsConditionYesNo) {
		this.termsConditionYesNo = termsConditionYesNo;
	}


	public String getPolicyNo() {
		return policyNo;
	}


	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}


	public String getPremiumStatus() {
		return premiumStatus;
	}


	public void setPremiumStatus(String premiumStatus) {
		this.premiumStatus = premiumStatus;
	}


	public String getPlanName() {
		return planName;
	}


	public void setPlanName(String planName) {
		this.planName = planName;
	}


	public BigDecimal getSeqno() {
		return seqno;
	}


	public void setSeqno(BigDecimal seqno) {
		this.seqno = seqno;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public boolean isCriteriaflag() {
		return criteriaflag;
	}


	public void setCriteriaflag(boolean criteriaflag) {
		this.criteriaflag = criteriaflag;
	}


	public boolean isSubmitflag() {
		return submitflag;
	}


	public void setSubmitflag(boolean submitflag) {
		this.submitflag = submitflag;
	}


	public RedirectionBL getRedirectImpl() {
		return redirectImpl;
	}


	public void setRedirectImpl(RedirectionBL redirectImpl) {
		this.redirectImpl = redirectImpl;
	}


	public TransactionServiceAction getTransAction() {
		return transAction;
	}


	public void setTransAction(TransactionServiceAction transAction) {
		this.transAction = transAction;
	}


	public List<ServiceReqNotesDTO> getNoteslist() {
		return noteslist;
	}


	public void setNoteslist(List<ServiceReqNotesDTO> noteslist) {
		this.noteslist = noteslist;
	}


	public List<ServiceReqNotesDTO> getNoteslist1() {
		return noteslist1;
	}


	public void setNoteslist1(List<ServiceReqNotesDTO> noteslist1) {
		this.noteslist1 = noteslist1;
	}


	public ServiceReqNotesDTO getNotes() {
		return notes;
	}


	public void setNotes(ServiceReqNotesDTO notes) {
		this.notes = notes;
	}


	public PartialWithdrawalDTO getSelectedPartialWithdrawalDTO() {
		return selectedPartialWithdrawalDTO;
	}


	public void setSelectedPartialWithdrawalDTO(PartialWithdrawalDTO selectedPartialWithdrawalDTO) {
		this.selectedPartialWithdrawalDTO = selectedPartialWithdrawalDTO;
	}


	public List<BankCodeLOVDTO> getBankCodeLOVDTOlist() {
		return bankCodeLOVDTOlist;
	}


	public void setBankCodeLOVDTOlist(List<BankCodeLOVDTO> bankCodeLOVDTOlist) {
		this.bankCodeLOVDTOlist = bankCodeLOVDTOlist;
	}


	public ServiceRequestMasterDTO getRequestdto() {
		return requestdto;
	}


	public void setRequestdto(ServiceRequestMasterDTO requestdto) {
		this.requestdto = requestdto;
	}


	public List<CpPartialWithdrawalFunds> getCpPartialWithdrawalFunds() {
		return cpPartialWithdrawalFunds;
	}


	public void setCpPartialWithdrawalFunds(List<CpPartialWithdrawalFunds> cpPartialWithdrawalFunds) {
		this.cpPartialWithdrawalFunds = cpPartialWithdrawalFunds;
	}


	public static Logger getLogger() {
		return logger;
	}


	public boolean isCumaltive() {
		return cumaltive;
	}


	public void setCumaltive(boolean cumaltive) {
		this.cumaltive = cumaltive;
	}


	public boolean isTermsCondition() {
		return termsCondition;
	}


	public void setTermsCondition(boolean termsCondition) {
		this.termsCondition = termsCondition;
	}


	public CpNFPersonalDetailsDAO getCpNFPersonalDetailsDAOImpl() {
		return cpNFPersonalDetailsDAOImpl;
	}


	public void setCpNFPersonalDetailsDAOImpl(CpNFPersonalDetailsDAO cpNFPersonalDetailsDAOImpl) {
		this.cpNFPersonalDetailsDAOImpl = cpNFPersonalDetailsDAOImpl;
	}	

	public List<CpPartialWithdrawalFundsDTO> getCpPartialWithdrawalFundsDTOlist() {
		return cpPartialWithdrawalFundsDTOlist;
	}

	public void setCpPartialWithdrawalFundsDTOlist(List<CpPartialWithdrawalFundsDTO> cpPartialWithdrawalFundsDTOlist) {
		this.cpPartialWithdrawalFundsDTOlist = cpPartialWithdrawalFundsDTOlist;
	}

	public String getDocFilepath() {
		return docFilepath;
	}

	public void setDocFilepath(String docFilepath) {
		this.docFilepath = docFilepath;
	}

	public int getDocFilesize() {
		return docFilesize;
	}

	public void setDocFilesize(int docFilesize) {
		this.docFilesize = docFilesize;
	}

	public boolean isDocupath() {
		return docupath;
	}

	public void setDocupath(boolean docupath) {
		this.docupath = docupath;
	}

	public CPServiceRequestDocuments getDto() {
		return dto;
	}

	public void setDto(CPServiceRequestDocuments dto) {
		this.dto = dto;
	}

	public List<CPServiceRequestDocuments> getListing() {
		return listing;
	}

	public void setListing(List<CPServiceRequestDocuments> listing) {
		this.listing = listing;
	}

	public boolean isAdding() {
		return adding;
	}

	public void setAdding(boolean adding) {
		this.adding = adding;
	}

	public String getWithdrawcritaria() {
		return withdrawcritaria;
	}

	public void setWithdrawcritaria(String withdrawcritaria) {
		this.withdrawcritaria = withdrawcritaria;
	}

	public CustomerPortalServices getCustomerPortalServices() {
		return customerPortalServices;
	}

	public void setCustomerPortalServices(CustomerPortalServices customerPortalServices) {
		this.customerPortalServices = customerPortalServices;
	}

	public CPartialWithdrawalFundsBL getcPartialWithdrawalFundsBL() {
		return cPartialWithdrawalFundsBL;
	}

	public void setcPartialWithdrawalFundsBL(CPartialWithdrawalFundsBL cPartialWithdrawalFundsBL) {
		this.cPartialWithdrawalFundsBL = cPartialWithdrawalFundsBL;
	}

	public String getCerticatenumber() {
		return certicatenumber;
	}

	public void setCerticatenumber(String certicatenumber) {
		this.certicatenumber = certicatenumber;
	}

	public String getInvestboolean() {
		return investboolean;
	}

	public void setInvestboolean(String investboolean) {
		this.investboolean = investboolean;
	}

	public String getInvestmentsourcode() {
		return investmentsourcode;
	}

	public void setInvestmentsourcode(String investmentsourcode) {
		this.investmentsourcode = investmentsourcode;
	}

	public Date getSerDate() {
		return serDate;
	}

	public void setSerDate(Date serDate) {
		this.serDate = serDate;
	}

	public boolean isFatcaFlagpopUp() {
		return fatcaFlagpopUp;
	}

	public void setFatcaFlagpopUp(boolean fatcaFlagpopUp) {
		this.fatcaFlagpopUp = fatcaFlagpopUp;
	}

	public String getFatcaFlag() {
		return fatcaFlag;
	}

	public void setFatcaFlag(String fatcaFlag) {
		this.fatcaFlag = fatcaFlag;
	}

	public ContributionHolidaylBL getContributionHolidaylBL() {
		return contributionHolidaylBL;
	}

	public void setContributionHolidaylBL(ContributionHolidaylBL contributionHolidaylBL) {
		this.contributionHolidaylBL = contributionHolidaylBL;
	}

	public UploadedFile getUpFile() {
		return upFile;
	}

	public void setUpFile(UploadedFile upFile) {
		this.upFile = upFile;
	}

	public CPServiceRequestDocuments getcPServiceRequestDocuments() {
		return cPServiceRequestDocuments;
	}

	public void setcPServiceRequestDocuments(CPServiceRequestDocuments cPServiceRequestDocuments) {
		this.cPServiceRequestDocuments = cPServiceRequestDocuments;
	}

	public CPServiceRequestDocumentsBL getcPServiceRequestDocumentsBL() {
		return cPServiceRequestDocumentsBL;
	}

	public void setcPServiceRequestDocumentsBL(CPServiceRequestDocumentsBL cPServiceRequestDocumentsBL) {
		this.cPServiceRequestDocumentsBL = cPServiceRequestDocumentsBL;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public boolean isAllowornot() {
		return allowornot;
	}

	public void setAllowornot(boolean allowornot) {
		this.allowornot = allowornot;
	}

	public String getProcessing() {
		return processing;
	}

	public void setProcessing(String processing) {
		this.processing = processing;
	}

	
		
	
	
}