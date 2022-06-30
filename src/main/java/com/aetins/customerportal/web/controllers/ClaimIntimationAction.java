package com.aetins.customerportal.web.controllers;

import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aetins.customerportal.core.enums.TRANSACTIONS;
import com.aetins.customerportal.core.faceletstaglib.SecurityLibrary;
import com.aetins.customerportal.core.services.IMailService;
import com.aetins.customerportal.web.common.Constants;
import com.aetins.customerportal.web.dao.CpNFPersonalDetailsDAO;
import com.aetins.customerportal.web.dao.CpUserInfoDAO;
import com.aetins.customerportal.web.dao.ITransactionDeptDao;
import com.aetins.customerportal.web.dto.ClaimIntimationDTO;
import com.aetins.customerportal.web.dto.CpTermAndConditionDTO;
import com.aetins.customerportal.web.dto.ListItem;
import com.aetins.customerportal.web.dto.PolicyDetailsDTO;
import com.aetins.customerportal.web.dto.ServiceRequestMasterDTO;
import com.aetins.customerportal.web.entity.CPServiceRequestDocuments;
import com.aetins.customerportal.web.entity.CPTransactionDept;
import com.aetins.customerportal.web.entity.CpCustomerDetail;
import com.aetins.customerportal.web.entity.CpRequestMaster;
import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.service.CPServiceRequestDocumentsBL;
import com.aetins.customerportal.web.service.ContributionHolidaylBL;
import com.aetins.customerportal.web.service.CpClaimIntimationBL;
import com.aetins.customerportal.web.service.CustomerDetailsBL;
import com.aetins.customerportal.web.service.CustomerPortalServices;
import com.aetins.customerportal.web.service.RedirectionBL;
import com.aetins.customerportal.web.utils.AppSettingURL;
import com.aetins.customerportal.web.utils.BSLUtils;
import com.aetins.customerportal.web.utils.FileUtils;

@Controller(value = "claimintimation")
@Scope("session")
public class ClaimIntimationAction extends BaseAction {

	private static final Logger logger = LoggerFactory.getLogger(ClaimIntimationAction.class);

	private String intimatoridtype;
	private String intimatoridno;
	private BigDecimal intimatorrefno;
	private String intimatorcustype;

	private String intimatorfstname;
	private String intimatormidname;
	private String intimatorlstname;
	private String intimatorusername;

	private Date intimatordob;
	private String intimatordob1;
	private String intimatortitle;
	private String intimatorgdner;
	private String intimatorcontactno;
	private String intimatormail;

	private String eventgrp;
	private Date createddate;
	private Date eventdate;
	private String intimationum;
	private String remarks;

	private List<ListItem> eventlist;
	private List<ListItem> idlist;
	private List<ListItem> titlist;
	private List<ListItem> casulist;

	ClaimIntimationDTO claimIntimationDTO;
	ClaimIntimationDTO claimIntimationDTO1;

	private String policyno;
	private boolean boolencheck;
	private String userId;
	private int custrefno;

	private String casueloss;
	private String eventplace;
	private String intimatetimel;

	private ServiceRequestMasterDTO requestdto;

	@Autowired
	CpClaimIntimationBL cpClaimIntimationBL;

	@Autowired
	CustomerPortalServices customerPortalServices;

	@Autowired
	TransactionServiceAction transAction;

	@Autowired
	CpUserInfoDAO cpUserInfoDAO;

	@Autowired
	IMailService mailService;

	@Autowired
	ITransactionDeptDao transactionDeptDao;

	@Autowired
	RedirectionBL redirectImpl;
	
	private boolean button1;

	private boolean button2;

	private List<PolicyDetailsDTO> policyDetailsList = new ArrayList<PolicyDetailsDTO>();

	private List<ListItem> policylist = new ArrayList<ListItem>();

	ListItem list;

	private BigDecimal seqno;

	private boolean docupath1;
	
	private CPServiceRequestDocuments dto;
	private List<CPServiceRequestDocuments> listing = new ArrayList<CPServiceRequestDocuments>();;
    private boolean adding;
    private List<CpTermAndConditionDTO> termsConditionYesYes;
    private List<CpTermAndConditionDTO> termsConditionYesNo;
    private boolean termsCondition;
    

    private boolean fatcabutton;
    private boolean actualsubmitbutton;
    
	@Override
	public void init() {
		try {
			Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
			createddate = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
			intimatetimel = dateFormat.format(createddate);
			custrefno = SecurityLibrary.getFullLoggedInUser().getNcustRefNo();
			userId = SecurityLibrary.getFullLoggedInUser().getVuserName();
			policyDetailsList = customerPortalServices.getPolicyLists(
					BigDecimal.valueOf(SecurityLibrary.getFullLoggedInUser().getNcustRefNo()));
			if (policyDetailsList.size() > 0) {
				for (int i = 0; i < policyDetailsList.size(); i++) {
					list = new ListItem();
					list.setCode(policyDetailsList.get(i).getPolicyNo());
					policylist.add(list);
				}
			}
			docupath1 = false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String clear() {
		getSession().setAttribute("UPLOADFILENAME", "EMPTY");
		docupath = false;
		docFilepath = "";
		docFilesize = 0;
		docupath1 = false;
		claimIntimationDTO = null;
		claimIntimationDTO1 = null;
		boolencheck = false;
		claimIntimationDTO = new ClaimIntimationDTO();		
		eligible = true;
		button1 = true;
		button2 = true;
		intimatoridtype = "";
		intimatoridno = "";
		intimatorrefno = null;
		intimatorcustype = null;
		intimatorfstname = null;
		intimatormidname = null;
		intimatorlstname = null;
		intimatorusername = null;
		intimatordob = null;
		intimatordob1 = null;
		intimatortitle = null;
		intimatorgdner = null;
		intimatorcontactno = null;
		intimatormail = null;
		eventgrp = "";
		eventdate = null;
		intimationum = null;
		remarks = "";
		requestdto = null;
		casueloss = "";
		eventplace = null;
		policyno="";
		dto=null;
		listing=null;
		listing = new ArrayList<CPServiceRequestDocuments>();
		adding=true;
		langClaims();
		fatcabutton = false;
		actualsubmitbutton = false;
		fatcaValidation();	
		try {
			termsConditionYesYes = redirectImpl.listTermAndCondition(Constants.CLAIM_INTIMATION, Constants.MANDATORY,Constants.REQUIRED);
			termsConditionYesNo = redirectImpl.listTermAndCondition1(Constants.CLAIM_INTIMATION,Constants.MANDATORYNO, Constants.REQUIREDYES);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		return "/user/claimintimation?faces-redirect=true";
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

	public void langClaims() {
		logger.info("CLAIMS LANG ACTION LISTENER CALLED");
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		casulist = customerPortalServices.getcasueloss(locale.toString().equalsIgnoreCase("ar") ? "AR" : "EN","CAUSE_CODE", "", "");
	}
	
	
	public void langevents() {
		logger.info("EVENTS LANG ACTION LISTENER CALLED");
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		eventlist = null;
		eventlist = new ArrayList<ListItem>();
		
		FacesContext context = FacesContext.getCurrentInstance();
		HomeDetailsAction hAction = context.getApplication().evaluateExpressionGet(context, "#{homeDetailsAction}",	HomeDetailsAction.class);
		if(hAction.getPolicyDetailsList().size()>0) {
			for(int i=0;i<hAction.getPolicyDetailsList().size();i++) {
				if(policyno.equalsIgnoreCase(hAction.getPolicyDetailsList().get(i).getPolicyNo())){
					seqno=hAction.getPolicyDetailsList().get(i).getSeqno();
				}
			}
		}
		
		if(policyno == null || policyno.equalsIgnoreCase("")) {
			passclear();
			eligible = true;
		}else {
			Optional<PolicyDetailsDTO> findFirst = policyDetailsList.stream()
					.filter(pol -> pol.getPolicyNo().equals(policyno.trim())).findFirst();
			eventlist = customerPortalServices.geteventgroup(locale.toString().equalsIgnoreCase("ar") ? "AR" : "EN",
					"CLAIM_EVENT_GROUP", policyno, "1");
			if(eventlist.size()>0) {
				eligible = false;
				if(fatcaFlag.equalsIgnoreCase("Y")) {
					fatcabutton  = true;
					actualsubmitbutton = false;
				}else {
					actualsubmitbutton = true;
					fatcabutton = false;
				}
			}else {
				passclear();
				fatcabutton  = false;
				actualsubmitbutton = false;
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
						"Dear Customer Your policy not eligible for this option");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		}
	}
	
	@Autowired
	private CustomerDetailsBL customerDetailsBL;

	List<CpCustomerDetail> cpCustomerDetail;

	public void mapping() {
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		try {
			cpCustomerDetail = null;
			custrefno = SecurityLibrary.getFullLoggedInUser().getNcustRefNo();
			if (custrefno != 0) {
				cpCustomerDetail = new ArrayList<CpCustomerDetail>();
				cpCustomerDetail = customerDetailsBL.getcustomerDetails(custrefno);
				if (cpCustomerDetail.get(0).getNcustRefNo() != 0) {
					logger.info("REF::"+cpCustomerDetail.get(0).getNcustRefNo());
					logger.info("ID-TYPE::"+cpCustomerDetail.get(0).getVidType());
					logger.info("ID-NO::"+cpCustomerDetail.get(0).getVidNo());
					logger.info("CUSTOMER NAME::"+cpCustomerDetail.get(0).getVcustName());				
					if(cpCustomerDetail.get(0).getVcustName()==null || cpCustomerDetail.get(0).getVcustName().equalsIgnoreCase("")) {
					}else {
						intimatorusername=cpCustomerDetail.get(0).getVcustName();
					}
				} 
				intimatoridtype = null;
				intimatoridno = null;
				BigDecimal bd = new BigDecimal(SecurityLibrary.getFullLoggedInUser().getNcustRefNo());
				intimatorrefno = bd;
				intimatorcustype = "OLD";
				intimatorgdner = cpCustomerDetail.get(0).getGender();
				intimatordob = SecurityLibrary.getFullLoggedInUser().getDdob();
				intimatortitle = SecurityLibrary.getFullLoggedInUser().getVtitle();
				intimatorcontactno = SecurityLibrary.getFullLoggedInUser().getVcontactNo();
				intimatormail = SecurityLibrary.getFullLoggedInUser().getVemail();
				intimatorfstname = cpCustomerDetail.get(0).getVcustName();
			} 
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Autowired
	CpNFPersonalDetailsDAO cpNFPersonalDetailsDAOImpl;

	public int transStatus() {
		Long count = cpNFPersonalDetailsDAOImpl.getRowCount(Constants.CLAIM_INTIMATION_1, policyno);
		System.out.println("count==" + count);
		long tmp = 0;
		int output = count.compareTo(tmp);
		return output;
	}

    private String processing;
	
	public String fatcasample() {
		if(processing.equalsIgnoreCase("YES")) {
			return "/factaFrom/claiminfofatca?faces-redirect=true";
		}else {
		   return null;	
		}
	}
	
	public void btnContinueLatertestfatca() {
		processing = null;
		processing = "NO";
		int transCount = transStatus();
		logger.info("Inside btnContinueLater of UpdateInformationAction Transaction pending " + "count is ======="
				+ transCount);
				if (transCount == 0) {
					try {
						String applicaleBox = "Y";
						mapping();
						if(validation() == true) {
							requestdto = new ServiceRequestMasterDTO();
							requestdto = new ServiceRequestMasterDTO();
							Date date = new Date();
							requestdto.setServiceRequestDate(date);
							requestdto.setServiceRequestType("CLAIM INTIMATION");
							requestdto.setPolicyNo(originakpolicy);
							requestdto.setUserId(userId);
							requestdto.setRequestStatus(Constants.UPDATE_INFO_CONTINUE);
							requestdto.setRecentUpdateDate(date);
							requestdto.setApplicable("Y");
							requestdto.setSeqno(seqno);
						
							logger.info("Insert and Get New Request Details started");
							requestdto = transAction.insertServiceRequest(requestdto);
							System.out.println(requestdto.getServiceRequestNo());
							
							getSession().setAttribute("TRANSACTION", "CLAIMINTIMATION");
							getSession().setAttribute("CLAIMSERVICENO", requestdto.getServiceRequestNo());

							claimIntimationDTO.setPolicyno(originakpolicy);
							claimIntimationDTO.setCustrefno(custrefno);

							claimIntimationDTO.setItidtype(intimatoridtype);
							claimIntimationDTO.setItidno(intimatoridno);
							claimIntimationDTO.setItrefno(intimatorrefno);
							claimIntimationDTO.setItcusttype(intimatorcustype);

							claimIntimationDTO.setItifstname(intimatorfstname);
							claimIntimationDTO.setItimdtname(intimatormidname);
							claimIntimationDTO.setItilstname(intimatorlstname);
							claimIntimationDTO.setItiname(intimatorusername);

							claimIntimationDTO.setItidob(intimatordob);
							claimIntimationDTO.setItitilee(intimatortitle);
							claimIntimationDTO.setItigender(intimatorgdner);
							claimIntimationDTO.setIticontno(intimatorcontactno);
							claimIntimationDTO.setItiemail(intimatormail);

							claimIntimationDTO.setEventgroup(eventgrp);
							claimIntimationDTO.setEventdate(eventdate);
							claimIntimationDTO.setCreatedate(createddate);
							claimIntimationDTO.setnServicRequestNo(requestdto);
							claimIntimationDTO.setRemarks(remarks);

							claimIntimationDTO.setCausecode(casueloss);
							claimIntimationDTO.setEventplace(eventplace);
							claimIntimationDTO.setIntimationtime(intimatetimel);
							boolean status = false;
							status = cpClaimIntimationBL.insert(claimIntimationDTO);
							System.out.println("CLAIM STATUS:" + status);
							if (status) {
								processing = "YES";
								getSession().setAttribute("OTPSUCC", "DONE");
								getSession().setAttribute("TRANSACTION", "CLAIMINTIMATION");
							}
							fatcaFlagpopUp = (boolean) getSession().getAttribute("FATCAFLAGPOP");
						}else {
							FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
									"Please check some mandatory informations are missing or contact Salama Support Team.");
							PrimeFaces.current().dialog().showMessageDynamic(message);
						}
					} catch (Exception e) {
						logger.info("Error at saving update information: " + e.getMessage());
						e.printStackTrace();
					}
				} else {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
							"Your old transaction request is in process.");
					PrimeFaces.current().dialog().showMessageDynamic(message);
					PrimeFaces.current().executeScript("PF('dlg3').hide()");
				}
			}


	
	public void btnContinueLater() {
		int transCount = transStatus();
		logger.info("Inside btnContinueLater of UpdateInformationAction Transaction pending " + "count is ======="
				+ transCount);
				if (transCount == 0) {
					try {
						String applicaleBox = "Y";
						mapping();
						if(validation() == true) {
							requestdto = new ServiceRequestMasterDTO();
							requestdto = new ServiceRequestMasterDTO();
							Date date = new Date();
							requestdto.setServiceRequestDate(date);
							requestdto.setServiceRequestType("CLAIM INTIMATION");
							requestdto.setPolicyNo(policyno);
							requestdto.setUserId(userId);
							requestdto.setRequestStatus(Constants.UPDATE_INFO_CONTINUE);
							requestdto.setRecentUpdateDate(date);
							requestdto.setApplicable("Y");
							requestdto.setSeqno(seqno);
						
							logger.info("Insert and Get New Request Details started");
							requestdto = transAction.insertServiceRequest(requestdto);
							System.out.println(requestdto.getServiceRequestNo());
							
							getSession().setAttribute("TRANSACTION", "CLAIMINTIMATION");
							getSession().setAttribute("CLAIMSERVICENO", requestdto.getServiceRequestNo());

							claimIntimationDTO.setPolicyno(policyno);
							claimIntimationDTO.setCustrefno(custrefno);

							claimIntimationDTO.setItidtype(intimatoridtype);
							claimIntimationDTO.setItidno(intimatoridno);
							claimIntimationDTO.setItrefno(intimatorrefno);
							claimIntimationDTO.setItcusttype(intimatorcustype);

							claimIntimationDTO.setItifstname(intimatorfstname);
							claimIntimationDTO.setItimdtname(intimatormidname);
							claimIntimationDTO.setItilstname(intimatorlstname);
							claimIntimationDTO.setItiname(intimatorusername);

							claimIntimationDTO.setItidob(intimatordob);
							claimIntimationDTO.setItitilee(intimatortitle);
							claimIntimationDTO.setItigender(intimatorgdner);
							claimIntimationDTO.setIticontno(intimatorcontactno);
							claimIntimationDTO.setItiemail(intimatormail);

							claimIntimationDTO.setEventgroup(eventgrp);
							claimIntimationDTO.setEventdate(eventdate);
							claimIntimationDTO.setCreatedate(createddate);
							claimIntimationDTO.setnServicRequestNo(requestdto);
							claimIntimationDTO.setRemarks(remarks);

							claimIntimationDTO.setCausecode(casueloss);
							claimIntimationDTO.setEventplace(eventplace);
							claimIntimationDTO.setIntimationtime(intimatetimel);
							boolean status = false;
							status = cpClaimIntimationBL.insert(claimIntimationDTO);
							System.out.println("CLAIM STATUS:" + status);
							if (status) {
								getSession().setAttribute("OTPSUCC", "DONE");
								getSession().setAttribute("TRANSACTION", "CLAIMINTIMATION");
								PrimeFaces.current().executeScript("PF('dlg3').show()");
							}
						}else {
							FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
									"Please check some mandatory informations are missing or contact Salama Support Team.");
							PrimeFaces.current().dialog().showMessageDynamic(message);
						}
					} catch (Exception e) {
						logger.info("Error at saving update information: " + e.getMessage());
						e.printStackTrace();
					}
				} else {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
							"Your old transaction request is in process.");
					PrimeFaces.current().dialog().showMessageDynamic(message);
					PrimeFaces.current().executeScript("PF('dlg3').hide()");
				}
			}


	public List<PolicyDetailsDTO> getPolicyDetailsList() {
		return policyDetailsList;
	}

	public void setPolicyDetailsList(List<PolicyDetailsDTO> policyDetailsList) {
		this.policyDetailsList = policyDetailsList;
	}

	public List<ListItem> getPolicylist() {
		return policylist;
	}

	public void setPolicylist(List<ListItem> policylist) {
		this.policylist = policylist;
	}

	public ListItem getList() {
		return list;
	}

	public void setList(ListItem list) {
		this.list = list;
	}

	public BigDecimal getSeqno() {
		return seqno;
	}

	public void setSeqno(BigDecimal seqno) {
		this.seqno = seqno;
	}
	
	
	public void passclear() {
		getSession().setAttribute("UPLOADFILENAME", "EMPTY");
		docupath = false;
		docFilepath = "";
		docFilesize = 0;
		docupath1 = false;

		claimIntimationDTO = null;
		claimIntimationDTO1 = null;
		boolencheck = false;

		eligible = true;
		button1 = true;
		button2 = true;

		intimatoridtype = "";
		intimatoridno = "";
		intimatorrefno = null;
		intimatorcustype = null;

		intimatorfstname = null;
		intimatormidname = null;
		intimatorlstname = null;
		intimatorusername = null;

		intimatordob = null;
		intimatordob1 = null;
		intimatortitle = null;
		intimatorgdner = null;
		intimatorcontactno = null;
		intimatormail = null;

		eventgrp = "";
		eventdate = null;
		intimationum = null;
		remarks = "";
		requestdto = null;

		casueloss = "";
		eventplace = null;
		
		policyno="";
	}
	



	
		
	
	public boolean validation() {
	  try {
		    if (eventgrp.equalsIgnoreCase("null") || eventgrp.equalsIgnoreCase("")) {
			    return false;
			}
			if (eventdate == null) {
				return false;
			}
			if (intimatordob == null) {
				return false;
			}
			if (intimatorfstname == null) {
				return false;
			}
			if (intimatortitle == null) {
				return false;
			}
			if (intimatormail == null) {
				return false;
			}
			if (intimatorgdner == null) {
				return false;
			}
			if (casueloss.equalsIgnoreCase("null") || casueloss.equalsIgnoreCase("")) {
				return false;
			}
			if (eventplace==null) {
				return false;
			}
			return true; 
	  }catch(Exception e){
		  e.printStackTrace();
	  }
	return true;
  }

	/**
	 * Fetch event groups `based on policy number selection
	 * 
	 */
	
    private boolean eligible;
	
	private String originakpolicy;
	private boolean eligible1;
	private String investmenttype; 
	private String producttype;
	
	public void policynotaking() {
		eligible1=false;
		for(int i=0;i<policyDetailsList.size();i++) {
			if(policyno.equalsIgnoreCase(policyDetailsList.get(i).getPolicyNo())) {
				originakpolicy=policyDetailsList.get(i).getPolicyNo();
				eligible1 = false;
			}
		}	
	}
	
	public void getEventGroups() {
		eligible=true;
		policynotaking();
		Locale locale1 = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		if(originakpolicy.equalsIgnoreCase("")||originakpolicy.equalsIgnoreCase(null)) {
			passclear();
//			eligible=true;
//			button2=true;
//			button1=true;
		}else if(eligible1==true) {
			eligible=true;
			button2=true;
			button1=true;
			FacesMessage message = new FacesMessage();
				if (locale1.toString().equalsIgnoreCase("ar")) {
					message = new FacesMessage(FacesMessage.SEVERITY_WARN, "رسالة", "عزيزي العميل المخصص ، سياستك غير مؤهلة لهذا الخيار ، سياستك غير مؤهلة لهذا الخيار");
				} else {
					message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message", "Dear Customer Your policy not eligible for this option");
				}
		        PrimeFaces.current().dialog().showMessageDynamic(message);
		}else if(eligible1==false){
			Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
			eventlist = new ArrayList<ListItem>();
			logger.info("Claim Intimation policy " + originakpolicy + " selected for event group");
			Optional<PolicyDetailsDTO> findFirst = policyDetailsList.stream()
					.filter(pol -> pol.getPolicyNo().equals(policyno.trim())).findFirst();
			eventlist = customerPortalServices.geteventgroup(locale.toString().equalsIgnoreCase("ar") ? "AR" : "EN",
					"CLAIM_EVENT_GROUP", originakpolicy, seqno.toString());	
			
			if(eventlist.size()>0) {
				button2=true;
				button1=false;
				eligible=false;
				ListItem dto;
				//claimvalidations();
			}else {
				eligible=true;
				button2=true;
				button1=true;
				FacesMessage message = new FacesMessage();
					if (locale1.toString().equalsIgnoreCase("ar")) {
						message = new FacesMessage(FacesMessage.SEVERITY_WARN, "رسالة", "عزيزي العميل المخصص ، سياستك غير مؤهلة لهذا الخيار ، سياستك غير مؤهلة لهذا الخيار");
					} else {
						message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message", "Dear Customer Your policy not eligible for this option");
					}
			        PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		}
	
		getSession().setAttribute("UPLOADFILENAME", "EMPTY");
		docupath = false;
		docFilepath = "";
		docFilesize = 0;
		docupath1 = false;
	}
	
	
	
	public void claimvalidations() {
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		System.out.println(":::claimvalidations:::"+investmenttype+ "---" +originakpolicy+producttype);
		System.out.println(":::claimvalidations:::"+investmenttype+ "---" +originakpolicy+producttype);
		if(investmenttype!=null || producttype!=null) {
			if((investmenttype.equalsIgnoreCase("R") || investmenttype.equalsIgnoreCase("S")) && (producttype.equalsIgnoreCase("I") || producttype.equalsIgnoreCase("G"))) {
			
		}else {
			eligible=true;
			button2=true;
			button1=true;
			FacesMessage message = new FacesMessage();
			if (locale.toString().equalsIgnoreCase("ar")) {
				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "رسالة", "عزيزي العميل المخصص ، سياستك غير مؤهلة لهذا الخيار ، سياستك غير مؤهلة لهذا الخيار");
			} else {
				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message", "Dear Customer Your policy not eligible for this option");
			}
	        PrimeFaces.current().dialog().showMessageDynamic(message);
		}
		}else {
			FacesMessage message = new FacesMessage();
			if (locale.toString().equalsIgnoreCase("ar")) {
				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "رسالة", "عزيزي العميل المخصص ، سياستك غير مؤهلة لهذا الخيار ، سياستك غير مؤهلة لهذا الخيار");
			} else {
				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message", "Dear Customer Your policy not eligible for this option");
			}
	        PrimeFaces.current().dialog().showMessageDynamic(message);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	if(intimatoridtype==null||intimatoridno.equalsIgnoreCase("")) {
//    	System.out.println("Checked");
//    }


	public void submitinh() {
		boolean status = false;
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		try {
			mapping();
			validation();
			if (button1 == true) {
				FacesMessage message = new FacesMessage();
				if (locale.toString().equalsIgnoreCase("ar")) {
					message = new FacesMessage(FacesMessage.SEVERITY_WARN, "رسالة", "يرجى ملء جميع التفاصيل");
				} else {
					message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message", "Please fill all the details");
				}
				PrimeFaces.current().dialog().showMessageDynamic(message);
				button1 = false;
			} else {
				requestdto = new ServiceRequestMasterDTO();
				requestdto = new ServiceRequestMasterDTO();
				Date date = new Date();
				requestdto.setServiceRequestDate(date);
				requestdto.setServiceRequestType("CLAIM INTIMATION");
				requestdto.setPolicyNo(originakpolicy);
				requestdto.setUserId(userId);
				requestdto.setRequestStatus(Constants.UPDATE_INFO_CONTINUE);
				requestdto.setRecentUpdateDate(date);
				requestdto.setApplicable("Y");
				requestdto.setSeqno(seqno);
				//requestdto.setCerticate(policyno);
				logger.info("Insert and Get New Request Details started");
				requestdto = transAction.insertServiceRequest(requestdto);
				System.out.println(requestdto.getServiceRequestNo());
				
				getSession().setAttribute("TRANSACTION", "CLAIMINTIMATION");
				getSession().setAttribute("CLAIMSERVICENO", requestdto.getServiceRequestNo());

				claimIntimationDTO.setPolicyno(originakpolicy);
				claimIntimationDTO.setCustrefno(custrefno);

				claimIntimationDTO.setItidtype(intimatoridtype);
				claimIntimationDTO.setItidno(intimatoridno);
				claimIntimationDTO.setItrefno(intimatorrefno);
				claimIntimationDTO.setItcusttype(intimatorcustype);

				claimIntimationDTO.setItifstname(intimatorfstname);
				claimIntimationDTO.setItimdtname(intimatormidname);
				claimIntimationDTO.setItilstname(intimatorlstname);
				claimIntimationDTO.setItiname(intimatorusername);

				claimIntimationDTO.setItidob(intimatordob);
				claimIntimationDTO.setItitilee(intimatortitle);
				claimIntimationDTO.setItigender(intimatorgdner);
				claimIntimationDTO.setIticontno(intimatorcontactno);
				claimIntimationDTO.setItiemail(intimatormail);

				claimIntimationDTO.setEventgroup(eventgrp);
				claimIntimationDTO.setEventdate(eventdate);
				claimIntimationDTO.setCreatedate(createddate);
				claimIntimationDTO.setnServicRequestNo(requestdto);
				claimIntimationDTO.setRemarks(remarks);

				claimIntimationDTO.setCausecode(casueloss);
				claimIntimationDTO.setEventplace(eventplace);
				claimIntimationDTO.setIntimationtime(intimatetimel);

				status = cpClaimIntimationBL.insert(claimIntimationDTO);
				System.out.println("CLAIM STATUS:" + status);
				if (status == true) {
					getSession().setAttribute("UPLOADFILENAME", "EMPTY");
					docupath = false;
					docFilepath = "";
					docFilesize = 0;
					docupath1 = true;
					button1 = true;
					button2 = false;
				} else {
					button1 = false;
					button2 = true;
					FacesMessage message = new FacesMessage();
					if (locale.toString().equalsIgnoreCase("ar")) {
						message = new FacesMessage(FacesMessage.SEVERITY_WARN, "رسالة",
								"يرجى الاتصال بفريق دعم الراجحي");
					} else {
						message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
								"Please contact Salama Support Team");
					}
					PrimeFaces.current().dialog().showMessageDynamic(message);
				}
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	public void sumbit() {
		boolean status = false;
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		try {
			validation();
			if (button1 == true) {
				FacesMessage message = new FacesMessage();
				if (locale.toString().equalsIgnoreCase("ar")) {
					message = new FacesMessage(FacesMessage.SEVERITY_WARN, "رسالة", "يرجى ملء جميع التفاصيل");
				} else {
					message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message", "Please fill all the details");
				}
				PrimeFaces.current().dialog().showMessageDynamic(message);
				button1 = false;
			} else {
				// intimatorusername=intimatorfstname;
				requestdto = new ServiceRequestMasterDTO();
				requestdto = new ServiceRequestMasterDTO();
				Date date = new Date();
				requestdto.setServiceRequestDate(date);
				requestdto.setServiceRequestType("CLAIM INTIMATION");
				requestdto.setPolicyNo(originakpolicy);
				requestdto.setUserId(userId);
				requestdto.setRequestStatus(Constants.UPDATE_INFO_CONTINUE);
				requestdto.setRecentUpdateDate(date);
				requestdto.setApplicable("Y");
				requestdto.setSeqno(seqno);
				logger.info("Insert and Get New Request Details started");
				requestdto = transAction.insertServiceRequest(requestdto);
				getSession().setAttribute("TRANSACTION", "CLAIMINTIMATION");
				getSession().setAttribute("CLAIMSERVICENO", requestdto.getServiceRequestNo());

				BigDecimal d = claimIntimationDTO.getItrefno();
				int nod = 0;
				if (d != null) {
					String ref = d.toString();
					nod = Integer.parseInt(ref);
				}
				if (nod > 0) {
					claimIntimationDTO.setPolicyno(originakpolicy);
					claimIntimationDTO.setCustrefno(custrefno);

					claimIntimationDTO.setItidtype(intimatoridtype);
					claimIntimationDTO.setItidno(intimatoridno);
					claimIntimationDTO.setItrefno(intimatorrefno);
					claimIntimationDTO.setItcusttype(intimatorcustype);

					claimIntimationDTO.setItifstname(intimatorfstname);
					claimIntimationDTO.setItimdtname(intimatormidname);
					claimIntimationDTO.setItilstname(intimatorlstname);
					claimIntimationDTO.setItiname(intimatorusername);

					claimIntimationDTO.setItidob(intimatordob);
					claimIntimationDTO.setItitilee(intimatortitle);
					claimIntimationDTO.setItigender(intimatorgdner);
					claimIntimationDTO.setIticontno(intimatorcontactno);
					claimIntimationDTO.setItiemail(intimatormail);

					claimIntimationDTO.setEventgroup(eventgrp);
					claimIntimationDTO.setEventdate(eventdate);
					claimIntimationDTO.setCreatedate(createddate);
					claimIntimationDTO.setnServicRequestNo(requestdto);
					claimIntimationDTO.setRemarks(remarks);

					claimIntimationDTO.setCausecode(casueloss);
					claimIntimationDTO.setEventplace(eventplace);
					claimIntimationDTO.setIntimationtime(intimatetimel);

					status = cpClaimIntimationBL.insert(claimIntimationDTO);
					if (status == true) {
						button1 = true;
						button2 = false;
					} else {
						button1 = false;
						button2 = true;
						FacesMessage message = new FacesMessage();
						if (locale.toString().equalsIgnoreCase("ar")) {
							message = new FacesMessage(FacesMessage.SEVERITY_WARN, "رسالة",
									"يرجى الاتصال بفريق دعم الراجحي");
						} else {
							message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
									"Please contact Salama Support Team");
						}
						PrimeFaces.current().dialog().showMessageDynamic(message);
					}
					System.out.println("Insert CLaim Intimation MYSQL:" + status);
				} else {
					intimatorusername = intimatorfstname + intimatormidname + intimatorlstname;
					claimIntimationDTO.setPolicyno(originakpolicy);
					claimIntimationDTO.setCustrefno(custrefno);

					claimIntimationDTO.setItidtype(intimatoridtype);
					claimIntimationDTO.setItidno(intimatoridno);
					BigDecimal dd = new BigDecimal("0");
					claimIntimationDTO.setItrefno(dd);
					claimIntimationDTO.setItcusttype(intimatorcustype);

					claimIntimationDTO.setItifstname(intimatorfstname);
					claimIntimationDTO.setItimdtname(intimatormidname);
					claimIntimationDTO.setItilstname(intimatorlstname);
					claimIntimationDTO.setItiname(intimatorusername);

					claimIntimationDTO.setItidob(intimatordob);
					claimIntimationDTO.setItitilee(intimatortitle);
					claimIntimationDTO.setItigender(intimatorgdner);
					claimIntimationDTO.setIticontno(intimatorcontactno);
					claimIntimationDTO.setItiemail(intimatormail);

					claimIntimationDTO.setEventgroup(eventgrp);
					claimIntimationDTO.setEventdate(eventdate);
					claimIntimationDTO.setCreatedate(createddate);
					claimIntimationDTO.setIntimationum(null);
					claimIntimationDTO.setnServicRequestNo(requestdto);
					claimIntimationDTO.setRemarks(remarks);

					claimIntimationDTO.setCausecode(casueloss);
					claimIntimationDTO.setEventplace(eventplace);
					claimIntimationDTO.setIntimationtime(intimatetimel);
					status = cpClaimIntimationBL.insert(claimIntimationDTO);
					System.out.println("Insert CLaim Intimation MYSQL:" + status);
					if (status == true) {
						getSession().setAttribute("TRANSACTION", "CLAIMINTIMATION");
						button1 = true;
						button2 = false;
					} else {
						button1 = false;
						button2 = true;
						FacesMessage message = new FacesMessage();
						if (locale.toString().equalsIgnoreCase("ar")) {
							message = new FacesMessage(FacesMessage.SEVERITY_WARN, "رسالة",
									"يرجى الاتصال بفريق دعم الراجحي");
						} else {
							message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
									"Please contact Salama Support Team");
						}
						PrimeFaces.current().dialog().showMessageDynamic(message);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Claim Intimation:" + e.getMessage());
		}
	}

	@Autowired
	TransactionServiceAction transactionServiceAction;

	private String docFilepath;
	private int docFilesize;
	UploadedFile upFile;
	private boolean docupath;
	CPServiceRequestDocuments cPServiceRequestDocuments;
	@Autowired
	CPServiceRequestDocumentsBL cPServiceRequestDocumentsBL;

	public void upload(FileUploadEvent event) {
		logger.info("Uploading file to the location");
		System.out.println("reading fileName: " + event.getFile().getFileName());
		upFile = event.getFile();
		System.out.println(upFile.getFileName());
		if (upFile.getFileName() != null) {
			docupath = true;
			docFilepath = upFile.getFileName();
			docFilesize = (int) upFile.getSize();
			getSession().setAttribute("UPLOADFILE", upFile);
			getSession().setAttribute("UPLOADFILENAME", docFilepath);
			getSession().setAttribute("UPLOADFILESize", docFilesize);
		}
	}
	
	public void total() {
		popmsg=null;
		button1 = false;
		submitinh();
		claiminti();
	}

	private String popmsg;
	
	public void claiminti(){
		try {
			boolean enabledFlag=false;
			Object serviceno = getSession().getAttribute("CLAIMSERVICENO");
			Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
			String status = "false";
			if(listing.size()>=7) {
				if (serviceno != null) {
					status = transactionServiceAction.transerviceclaimintimation((int) serviceno);
					if (status.equalsIgnoreCase("FAIL")) {
						button1 = false;
						button2 = true;
						FacesMessage message = new FacesMessage();
						if (locale.toString().equalsIgnoreCase("ar")) {
							message = new FacesMessage(FacesMessage.SEVERITY_WARN, "رسالة",
									"يرجى التحقق من العملية أو الاتصال بدعم عملاء الراجحي");
						} else {
							message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
									"Please check the process or Contact Salama Customer Support");
						}
						PrimeFaces.current().dialog().showMessageDynamic(message);
					} else {
						button1 = true;
						button2 = true;
						cpClaimIntimationBL.updatestatus((int) serviceno);
						if (status.equalsIgnoreCase("PASS")) {
							btnUploadFile12(requestdto);
		                  
							String transaction = TRANSACTIONS.CLAIMINTIMATION_TRANSACTION.toString();
							// Fetch dept by transactions
							CPTransactionDept findByTransaction = transactionDeptDao.findByTransaction(transaction);

							// Mail Body
							String emailBody = AppSettingURL.EMAIL_NOTIFY_BODY_TRANSACTION_TRIGGER;
							Map<String, String> succMap = new HashMap<String, String>();
							succMap.put("deptName", findByTransaction.getDeptName());
							succMap.put("srNo", serviceno + "");
							succMap.put("custname", SecurityLibrary.getLoggedInUser());
							succMap.put("transactionName", "CLAIM INTIMATION");
							succMap.put("policyNo", policyno);
							String email = StrSubstitutor.replace(emailBody, succMap);

							// Fetch user by group
							List<CpUserInfo> cpUsersByGroup = cpUserInfoDAO.getCpUsersByGroup("B");

							// Fetch dept by transactions
							// CPTransactionDept findByTransaction =
							// transactionDeptDao.findByTransaction("CLAIMINTIMATION_TRANSACTION");

							for (CpUserInfo userInfo : cpUsersByGroup) {

								if (userInfo.getvBusrDept().equals(findByTransaction.getDeptCode())) {

									mailService.sendMail(userInfo.getVemail(), "CLAIM INTIMATION", email);
									logger.info("Mail sent to business user: " + userInfo.getVemail()
											+ " for transaction: CLAIM INTIMATION");
								}
							}
						}

						FacesMessage message = new FacesMessage();
						if (locale.toString().equalsIgnoreCase("ar")) {
							message = new FacesMessage(FacesMessage.SEVERITY_WARN, "رسالة",
									"تم بدء إعلام المطالبة بنجاح من بوابة العميل");
							popmsg = "تم بدء إعلام المطالبة بنجاح من بوابة العميل";
							PrimeFaces.current().executeScript("PF('dlg4').show()");
						} else {
							message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
									"Claim Intimation Initiated Succussfully from Customer Portal");
							popmsg = "Claim Intimation Initiated Succussfully from Customer Portal";
							PrimeFaces.current().executeScript("PF('dlg4').show()");
						}
						
						//PrimeFaces.current().dialog().showMessageDynamic(message);
						eligible = true;
						passclear();
					}
				}else {
					FacesMessage message = new FacesMessage();
					if(locale.toString().equalsIgnoreCase("ar")) {
						message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "رسالة", "يرجى إعادة التحقق من العملية التي لديك "+"اختر نفس الصندوق لعدة مرات أو اتصل بفريق دعم الراجحي");
					}else {
						message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message", "Please re-check the process "	+ "or contact Salama Support Team .");
					}
					PrimeFaces.current().dialog().showMessageDynamic(message);
			   }
			}else {
				FacesMessage message = new FacesMessage();
				if(locale.toString().equalsIgnoreCase("ar")) {
					message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "رسالة", "يرجى تحميل المستندات الإلزامية");
				}else {
					message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message", "Please Upload the Mandatory Documents");
				}
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
			}

	
	
	public void btnUploadFile12(ServiceRequestMasterDTO servicerequestnumber) throws Exception {
		Object transactioname = getSession().getAttribute("TRANSACTION");
		if (transactioname.equals("CLAIMINTIMATION")) {
				Object file1 = getSession().getAttribute("UPLOADFILE1");
				Object file2 = getSession().getAttribute("UPLOADFILE2");
				Object file3 = getSession().getAttribute("UPLOADFILE3");
				Object file4 = getSession().getAttribute("UPLOADFILE4");
				Object file5 = getSession().getAttribute("UPLOADFILE5");
				Object file6 = getSession().getAttribute("UPLOADFILE6");
				Object file7 = getSession().getAttribute("UPLOADFILE7");
				Object file8 = getSession().getAttribute("UPLOADFILE8");
				String sourcePath = AppSettingURL.SERVICE_DOCUMENT_LOCATION;
				boolean status = true;
				String policynumber = servicerequestnumber.getPolicyNo();
				String policylocation = sourcePath + "\\" + policynumber;
				File creatingnewFolder = new File(policylocation);
				if (creatingnewFolder.exists()) {
					int requetno = servicerequestnumber.getServiceRequestNo();
					final String policyfolderlocation = creatingnewFolder.getPath();
					final String requestnumberfolder = policyfolderlocation + "\\" + requetno;
					File requestfolder = new File(requestnumberfolder);
					boolean created = requestfolder.mkdir();
					if (created) {
						for (int i = 0; i < listing.size(); i++) {
							Object val = null;
							if (i == 0) {
								val = file1;
							} else if (i == 1) {
								val = file2;
							} else if (i == 2) {
								val = file3;
							} else if (i == 3) {
								val = file4;
							} else if (i == 4) {
								val = file5;
							} else if (i == 5) {
								val = file6;
							} else if (i == 6) {
								val = file7;
							} else if (i == 7) {
								val = file8;
							}
							cPServiceRequestDocuments = new CPServiceRequestDocuments();
							status = FileUtils.springCopyFile(requestnumberfolder, (UploadedFile) val);
							cPServiceRequestDocuments.setFileName((String) listing.get(i).getFileName());
							cPServiceRequestDocuments.setFileSize((int) listing.get(i).getFileSize());
							CpRequestMaster requester = new CpRequestMaster();
							requester.setServiceRequestNo(servicerequestnumber.getServiceRequestNo());
							cPServiceRequestDocuments.setServiceRequestNo(requester);
							cPServiceRequestDocumentsBL.insert(cPServiceRequestDocuments);
							logger.info("Folder was created !");
						}
					} else {
						logger.error("Unable to create folder");
					}
				} else {
					boolean created = creatingnewFolder.mkdir();
					if (created) {
						logger.info("Folder was created !");
						int requetno = servicerequestnumber.getServiceRequestNo();
						final String policyfolderlocation = creatingnewFolder.getPath();
						final String requestnumberfolder = policyfolderlocation + "\\" + requetno;
						File requestfolder = new File(requestnumberfolder);
						boolean created1 = requestfolder.mkdir();
						if (created1) {
							for (int i = 0; i < listing.size(); i++) {
								Object val = null;
								if (i == 0) {
									val = file1;
								} else if (i == 1) {
									val = file2;
								} else if (i == 2) {
									val = file3;
								} else if (i == 3) {
									val = file4;
								} else if (i == 4) {
									val = file5;
								} else if (i == 5) {
									val = file6;
								} else if (i == 6) {
									val = file7;
								} else if (i == 7) {
									val = file8;
								}
								cPServiceRequestDocuments = new CPServiceRequestDocuments();
								status = FileUtils.springCopyFile(requestnumberfolder, (UploadedFile) val);
								cPServiceRequestDocuments
										.setFileName((String) listing.get(i).getFileName());
								cPServiceRequestDocuments.setFileSize((int) listing.get(i).getFileSize());
								CpRequestMaster requester = new CpRequestMaster();
								requester.setServiceRequestNo(servicerequestnumber.getServiceRequestNo());
								cPServiceRequestDocuments.setServiceRequestNo(requester);
								cPServiceRequestDocumentsBL.insert(cPServiceRequestDocuments);
								logger.info("Folder was created !");
							}
						}
					} else {
						logger.error("Unable to create folder");
					}
				}
		}
	}

	public String getIntimatoridtype() {
		return intimatoridtype;
	}

	public void setIntimatoridtype(String intimatoridtype) {
		this.intimatoridtype = intimatoridtype;
	}

	public String getIntimatoridno() {
		return intimatoridno;
	}

	public void setIntimatoridno(String intimatoridno) {
		this.intimatoridno = intimatoridno;
	}

	public BigDecimal getIntimatorrefno() {
		return intimatorrefno;
	}

	public void setIntimatorrefno(BigDecimal intimatorrefno) {
		this.intimatorrefno = intimatorrefno;
	}

	public ServiceRequestMasterDTO getRequestdto() {
		return requestdto;
	}

	public void setRequestdto(ServiceRequestMasterDTO requestdto) {
		this.requestdto = requestdto;
	}

	public String getIntimationum() {
		return intimationum;
	}

	public void setIntimationum(String intimationum) {
		this.intimationum = intimationum;
	}

	public String getIntimatorcustype() {
		return intimatorcustype;
	}

	public void setIntimatorcustype(String intimatorcustype) {
		this.intimatorcustype = intimatorcustype;
	}

	public String getIntimatorusername() {
		return intimatorusername;
	}

	public void setIntimatorusername(String intimatorusername) {
		this.intimatorusername = intimatorusername;
	}

	public String getIntimatorfstname() {
		return intimatorfstname;
	}

	public void setIntimatorfstname(String intimatorfstname) {
		this.intimatorfstname = intimatorfstname;
	}

	public String getIntimatormidname() {
		return intimatormidname;
	}

	public void setIntimatormidname(String intimatormidname) {
		this.intimatormidname = intimatormidname;
	}

	public String getIntimatorlstname() {
		return intimatorlstname;
	}

	public void setIntimatorlstname(String intimatorlstname) {
		this.intimatorlstname = intimatorlstname;
	}

	public Date getIntimatordob() {
		return intimatordob;
	}

	public void setIntimatordob(Date intimatordob) {
		this.intimatordob = intimatordob;
	}

	public String getIntimatordob1() {
		return intimatordob1;
	}

	public void setIntimatordob1(String intimatordob1) {
		this.intimatordob1 = intimatordob1;
	}

	public String getIntimatortitle() {
		return intimatortitle;
	}

	public void setIntimatortitle(String intimatortitle) {
		this.intimatortitle = intimatortitle;
	}

	public String getIntimatorcontactno() {
		return intimatorcontactno;
	}

	public void setIntimatorcontactno(String intimatorcontactno) {
		this.intimatorcontactno = intimatorcontactno;
	}

	public String getIntimatorgdner() {
		return intimatorgdner;
	}

	public void setIntimatorgdner(String intimatorgdner) {
		this.intimatorgdner = intimatorgdner;
	}

	public int getCustrefno() {
		return custrefno;
	}

	public void setCustrefno(int custrefno) {
		this.custrefno = custrefno;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public Date getEventdate() {
		return eventdate;
	}

	public void setEventdate(Date eventdate) {
		this.eventdate = eventdate;
	}

	public String getEventgrp() {
		return eventgrp;
	}

	public void setEventgrp(String eventgrp) {
		this.eventgrp = eventgrp;
	}

	public String getIntimatormail() {
		return intimatormail;
	}

	public void setIntimatormail(String intimatormail) {
		this.intimatormail = intimatormail;
	}

	public List<ListItem> getEventlist() {
		return eventlist;
	}

	public void setEventlist(List<ListItem> eventlist) {
		this.eventlist = eventlist;
	}

	public List<ListItem> getIdlist() {
		return idlist;
	}

	public void setIdlist(List<ListItem> idlist) {
		this.idlist = idlist;
	}

	public List<ListItem> getTitlist() {
		return titlist;
	}

	public void setTitlist(List<ListItem> titlist) {
		this.titlist = titlist;
	}

	public ClaimIntimationDTO getClaimIntimationDTO() {
		return claimIntimationDTO;
	}

	public void setClaimIntimationDTO(ClaimIntimationDTO claimIntimationDTO) {
		this.claimIntimationDTO = claimIntimationDTO;
	}

	public ClaimIntimationDTO getClaimIntimationDTO1() {
		return claimIntimationDTO1;
	}

	public void setClaimIntimationDTO1(ClaimIntimationDTO claimIntimationDTO1) {
		this.claimIntimationDTO1 = claimIntimationDTO1;
	}

	public String getPolicyno() {
		return policyno;
	}

	public void setPolicyno(String policyno) {
		this.policyno = policyno;
	}

	public boolean isBoolencheck() {
		return boolencheck;
	}

	public void setBoolencheck(boolean boolencheck) {
		this.boolencheck = boolencheck;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public CpClaimIntimationBL getCpClaimIntimationBL() {
		return cpClaimIntimationBL;
	}

	public void setCpClaimIntimationBL(CpClaimIntimationBL cpClaimIntimationBL) {
		this.cpClaimIntimationBL = cpClaimIntimationBL;
	}

	public CustomerPortalServices getCustomerPortalServices() {
		return customerPortalServices;
	}

	public void setCustomerPortalServices(CustomerPortalServices customerPortalServices) {
		this.customerPortalServices = customerPortalServices;
	}

	public static Logger getLogger() {
		return logger;
	}

	public boolean isButton1() {
		return button1;
	}

	public void setButton1(boolean button1) {
		this.button1 = button1;
	}

	public boolean isButton2() {
		return button2;
	}

	public void setButton2(boolean button2) {
		this.button2 = button2;
	}

	public List<ListItem> getCasulist() {
		return casulist;
	}

	public void setCasulist(List<ListItem> casulist) {
		this.casulist = casulist;
	}

	public String getCasueloss() {
		return casueloss;
	}

	public void setCasueloss(String casueloss) {
		this.casueloss = casueloss;
	}

	public String getEventplace() {
		return eventplace;
	}

	public void setEventplace(String eventplace) {
		this.eventplace = eventplace;
	}

	public String getIntimatetimel() {
		return intimatetimel;
	}

	public void setIntimatetimel(String intimatetimel) {
		this.intimatetimel = intimatetimel;
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

	public boolean isDocupath1() {
		return docupath1;
	}

	public void setDocupath1(boolean docupath1) {
		this.docupath1 = docupath1;
	}

	public boolean isEligible() {
		return eligible;
	}

	public void setEligible(boolean eligible) {
		this.eligible = eligible;
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

	public String getOriginakpolicy() {
		return originakpolicy;
	}

	public void setOriginakpolicy(String originakpolicy) {
		this.originakpolicy = originakpolicy;
	}

	public String getPopmsg() {
		return popmsg;
	}

	public void setPopmsg(String popmsg) {
		this.popmsg = popmsg;
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

	public boolean isTermsCondition() {
		return termsCondition;
	}

	public void setTermsCondition(boolean termsCondition) {
		this.termsCondition = termsCondition;
	}

	public TransactionServiceAction getTransAction() {
		return transAction;
	}

	public void setTransAction(TransactionServiceAction transAction) {
		this.transAction = transAction;
	}

	public CpUserInfoDAO getCpUserInfoDAO() {
		return cpUserInfoDAO;
	}

	public void setCpUserInfoDAO(CpUserInfoDAO cpUserInfoDAO) {
		this.cpUserInfoDAO = cpUserInfoDAO;
	}

	public IMailService getMailService() {
		return mailService;
	}

	public void setMailService(IMailService mailService) {
		this.mailService = mailService;
	}

	public ITransactionDeptDao getTransactionDeptDao() {
		return transactionDeptDao;
	}

	public void setTransactionDeptDao(ITransactionDeptDao transactionDeptDao) {
		this.transactionDeptDao = transactionDeptDao;
	}

	public RedirectionBL getRedirectImpl() {
		return redirectImpl;
	}

	public void setRedirectImpl(RedirectionBL redirectImpl) {
		this.redirectImpl = redirectImpl;
	}

	public boolean isFatcabutton() {
		return fatcabutton;
	}

	public void setFatcabutton(boolean fatcabutton) {
		this.fatcabutton = fatcabutton;
	}

	public boolean isActualsubmitbutton() {
		return actualsubmitbutton;
	}

	public void setActualsubmitbutton(boolean actualsubmitbutton) {
		this.actualsubmitbutton = actualsubmitbutton;
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

	public CustomerDetailsBL getCustomerDetailsBL() {
		return customerDetailsBL;
	}

	public void setCustomerDetailsBL(CustomerDetailsBL customerDetailsBL) {
		this.customerDetailsBL = customerDetailsBL;
	}

	public List<CpCustomerDetail> getCpCustomerDetail() {
		return cpCustomerDetail;
	}

	public void setCpCustomerDetail(List<CpCustomerDetail> cpCustomerDetail) {
		this.cpCustomerDetail = cpCustomerDetail;
	}

	public CpNFPersonalDetailsDAO getCpNFPersonalDetailsDAOImpl() {
		return cpNFPersonalDetailsDAOImpl;
	}

	public void setCpNFPersonalDetailsDAOImpl(CpNFPersonalDetailsDAO cpNFPersonalDetailsDAOImpl) {
		this.cpNFPersonalDetailsDAOImpl = cpNFPersonalDetailsDAOImpl;
	}

	public String getProcessing() {
		return processing;
	}

	public void setProcessing(String processing) {
		this.processing = processing;
	}

	public boolean isEligible1() {
		return eligible1;
	}

	public void setEligible1(boolean eligible1) {
		this.eligible1 = eligible1;
	}

	public String getInvestmenttype() {
		return investmenttype;
	}

	public void setInvestmenttype(String investmenttype) {
		this.investmenttype = investmenttype;
	}

	public String getProducttype() {
		return producttype;
	}

	public void setProducttype(String producttype) {
		this.producttype = producttype;
	}

	public TransactionServiceAction getTransactionServiceAction() {
		return transactionServiceAction;
	}

	public void setTransactionServiceAction(TransactionServiceAction transactionServiceAction) {
		this.transactionServiceAction = transactionServiceAction;
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
	
	

//	private ServiceRequestMasterDTO requestdto;
//	@Autowired
//	TransactionServiceAction transAction;
//
//	
//	public void continuesubmit() {
//		try {
//			claimIntimationDTO1=new ClaimIntimationDTO();
//			boolean status=false;
//			requestdto = new ServiceRequestMasterDTO();
//			Date date = new Date();
//			requestdto.setServiceRequestDate(date);
//			requestdto.setServiceRequestType("CLAIM INTIMATION");
//			requestdto.setPolicyNo("12211212");
//			requestdto.setUserId(userId);
//			requestdto.setRequestStatus(Constants.UPDATE_INFO_CONTINUE);
//			requestdto.setRecentUpdateDate(date);
//			requestdto.setApplicable("Y");
//			//requestdto.setSeqno);
//			logger.info("Insert and Get New Request Details started");
//			requestdto = transAction.insertServiceRequest(requestdto);
//			if(requestdto.getServiceRequestNo()!=0) {
//				intimatorusername=intimatorfstname+intimatormidname+intimatorlstname;
//				claimIntimationDTO1.setnServicRequestNo(requestdto);
//				BigDecimal no = new BigDecimal(custrefno);
//				claimIntimationDTO1.setCustrefno(no);
//				claimIntimationDTO1.setPolicyno(policyno);
//				
//				claimIntimationDTO1.setItidtype(intimatoridtype);
//				claimIntimationDTO1.setItidno(intimatoridno);
//				claimIntimationDTO1.setItrefno(intimatorrefno);
//				claimIntimationDTO1.setItcusttype(intimatorcustype);
//				
//				claimIntimationDTO1.setItiname(intimatorusername);
//				claimIntimationDTO1.setItifstname(intimatorfstname);
//				claimIntimationDTO1.setItimdtname(intimatormidname);
//				claimIntimationDTO1.setItilstname(intimatorlstname);
//				
//				claimIntimationDTO1.setItidob(intimatordob);
//				claimIntimationDTO1.setItitilee(intimatortitle);
//				claimIntimationDTO1.setItigender(intimatorgdner);
//				claimIntimationDTO1.setIticontno(intimatorcontactno);
//				claimIntimationDTO1.setItiemail(intimatormail);
//				
//				claimIntimationDTO1.setEventgroup(eventgrp);
//				claimIntimationDTO1.setEventdate(eventdate);
//				claimIntimationDTO1.setCreatedate(createddate);
//				//claimIntimationDTO1.setIntimationum(intimationum);
//				claimIntimationDTO1.setRemarks(remarks);
//				
//				status=cpClaimIntimationBL.insert(claimIntimationDTO);
//				if(status==true) {
//					//webservice call
//				}else {
//					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
//							"Please Try Again One more time or Contact Salama Support Team ");
//					PrimeFaces.current().dialog().showMessageDynamic(message);
//				}
//				
//			}			
//		}catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}
	
	

	
	
	
}
