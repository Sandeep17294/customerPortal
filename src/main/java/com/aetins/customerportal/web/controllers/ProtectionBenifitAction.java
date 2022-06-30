package com.aetins.customerportal.web.controllers;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aetins.customerportal.core.faceletstaglib.SecurityLibrary;
import com.aetins.customerportal.web.common.Constants;
import com.aetins.customerportal.web.common.LoginSession;
import com.aetins.customerportal.web.dao.ContributionHolidayDAO;
import com.aetins.customerportal.web.dao.CpNFPersonalDetailsDAO;
import com.aetins.customerportal.web.dao.impl.ContributionHolidayDAOImpl;
import com.aetins.customerportal.web.dto.CpTermAndConditionDTO;
import com.aetins.customerportal.web.dto.ProtectionBenifitDTO;
import com.aetins.customerportal.web.dto.ServiceRequestMasterDTO;
import com.aetins.customerportal.web.entity.CPServiceRequestDocuments;
import com.aetins.customerportal.web.entity.CpListBoxAnswers;
import com.aetins.customerportal.web.entity.CpQuestionnaire;
import com.aetins.customerportal.web.entity.CpRequestMaster;
import com.aetins.customerportal.web.service.CPServiceRequestDocumentsBL;
import com.aetins.customerportal.web.service.CustomerPortalServices;
import com.aetins.customerportal.web.service.ProtectionBenifitBL;
import com.aetins.customerportal.web.service.RedirectionBL;
import com.aetins.customerportal.web.service.impl.AdminBLImpl;
import com.aetins.customerportal.web.service.impl.CustomerPortalServicesImpl;
import com.aetins.customerportal.web.service.impl.ProtectionBenifitBLImpl;
import com.aetins.customerportal.web.service.impl.RedirectionBLImpl;
import com.aetins.customerportal.web.utils.BSLUtils;

@Controller
@Scope("session")
public class ProtectionBenifitAction extends BaseAction {

	private static final Logger logger = Logger.getLogger(ProtectionBenifitAction.class);

	private String policyNo;
	private List<ProtectionBenifitDTO> protBenifitList = new ArrayList<ProtectionBenifitDTO>();
	private List<ProtectionBenifitDTO> selectedBenifitList = new ArrayList<ProtectionBenifitDTO>();
	private List<ProtectionBenifitDTO> protectionBenifitMemberIndex = new ArrayList<ProtectionBenifitDTO>();
	
	@Autowired
	CustomerPortalServices customerPortalServices;
	
	@Autowired
	CpNFPersonalDetailsDAO  cpNFPersonalDetailsdao;
	
	@Autowired
	ContributionHolidayDAO contributionHolidayDAO;
	
	@Autowired
	TransactionServiceAction transAction;
	
	@Autowired
	ProtectionBenifitBL protectionBenifitBL;
	
	@Autowired
	LoginSession loginSession;
	
	@Autowired
	AdminBLImpl adminImpl;
	
	@Autowired
	HomeDetailsAction hAction;

	@Autowired
	RedirectionBL redirectImpl;

	private String alterationType;
	private boolean enablebox;
	private ProtectionBenifitDTO protectionBenifitDTO;
	private String assetsTotal;
	private String liabilitiesTotal;
	private ServiceRequestMasterDTO serviceRequestMasterDTO = new ServiceRequestMasterDTO();	
	//RequestContext requestContext;
	Map<BigDecimal, List<ProtectionBenifitDTO>> benfits = new HashMap();
	Date serDate;	

	// vinod
	private List<CpTermAndConditionDTO> termsConditionYesNo;
	private List<CpTermAndConditionDTO> termsConditionYesYes;
	private boolean termsCondition;
	private boolean fatcaFlagpopUp;
	private String fatcaFlag;
	private String bodyMes;
	private String secondMember;
	String gender;
	private List<CpQuestionnaire> questionnaireList = new ArrayList<>();
	private List<CpListBoxAnswers> listBoxAnswers = new ArrayList<>();
    private List<CpListBoxAnswers> selectedRecord = new ArrayList<>();

    private BigDecimal seqno;
    
	@Override
	public void init() {
		// TODO Auto-generated method stub
		FacesContext context = FacesContext.getCurrentInstance();
		HomeDetailsAction hAction = context.getApplication().evaluateExpressionGet(context, "#{homeDetailsAction}",
				HomeDetailsAction.class);
		policyNo = hAction.getSearchPolicyList().getPlanNumber();
		//secondMember = hAction.getSearchPolicyList().getSecondMember();
		secondMember = "assa";
		gender = (String) getSession().getAttribute("GENDER");
		fetchFromService();
		if(hAction.getPolicyDetailsList().size()>0) {
			for(int i=0;i<hAction.getPolicyDetailsList().size();i++) {
				if(policyNo.equalsIgnoreCase(hAction.getPolicyDetailsList().get(i).getPolicyNo())){
					seqno=hAction.getPolicyDetailsList().get(i).getSeqno();
				}
			}
		}
		getSession().setAttribute("UPLOADFILENAME", "EMPTY");
		termsConditionYesYes = redirectImpl.listTermAndCondition(Constants.PROTECTION_BENIFIT_SERVICE_REQUEST_TYPE,	Constants.MANDATORY, Constants.REQUIRED);
		termsConditionYesNo = redirectImpl.listTermAndCondition1(Constants.PROTECTION_BENIFIT_SERVICE_REQUEST_TYPE,	Constants.MANDATORYNO, Constants.REQUIREDYES);
	}
	
	public String prodtcfetch() {
		
		benfits = null;
		protBenifitList = null;
		protectionBenifitDTO=null;
		protectionBenifitDTO= new ProtectionBenifitDTO();
		termsCondition=false;
		alterationType=null;
		enablebox=false;		
		// TODO Auto-generated method stub
				FacesContext context = FacesContext.getCurrentInstance();
				HomeDetailsAction hAction = context.getApplication().evaluateExpressionGet(context, "#{homeDetailsAction}",
						HomeDetailsAction.class);
				policyNo = hAction.getSearchPolicyList().getPlanNumber();
				//secondMember = hAction.getSearchPolicyList().getSecondMember();
				secondMember = "assa";
				gender = (String) getSession().getAttribute("GENDER");
				fetchFromService();
				if(hAction.getPolicyDetailsList().size()>0) {
					for(int i=0;i<hAction.getPolicyDetailsList().size();i++) {
						if(policyNo.equalsIgnoreCase(hAction.getPolicyDetailsList().get(i).getPolicyNo())){
							seqno=hAction.getPolicyDetailsList().get(i).getSeqno();
						}
					}
				}
				docupath=false;
				getSession().setAttribute("UPLOADFILENAME", "EMPTY");
				termsConditionYesYes = redirectImpl.listTermAndCondition(Constants.PROTECTION_BENIFIT_SERVICE_REQUEST_TYPE,	Constants.MANDATORY, Constants.REQUIRED);
				termsConditionYesNo = redirectImpl.listTermAndCondition1(Constants.PROTECTION_BENIFIT_SERVICE_REQUEST_TYPE,	Constants.MANDATORYNO, Constants.REQUIREDYES);
				getSession().setAttribute("FATCAFLAG", "");
				onPageLoad();
				return "/user/productionbenefitstransaction?faces-redirect=true";
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
	

    
	public void onPageLoad() {
		   serDate = null;
		   int diffInDays = 0;
		   fatcaFlag = null;
		   fatcaFlagpopUp = false;
		List<CpRequestMaster> requestDate = contributionHolidayDAO.getTransactionCount(SecurityLibrary.getFullLoggedInUser().getVuserName(), "Y");		
		if (requestDate.size() > 0) {
			serDate = requestDate.get(0).getServiceRequestDate();
			diffInDays = (int) ((new Date().getTime() - serDate.getTime()) / (1000 * 60 * 60 * 24));
			System.out.println(diffInDays);
		}
		if (diffInDays > 90 || requestDate.size() == 0) {
			fatcaFlag = "N";
			fatcaFlagpopUp = false;
			getSession().setAttribute("FATCAFLAG", fatcaFlag);

		} else {
			fatcaFlag = "Y";
			fatcaFlagpopUp = true;
			getSession().setAttribute("FATCAFLAG", fatcaFlag);
		}
		

//		Long count = cpNFPersonalDetailsdao.getRowCount(Constants.PROTECTION_BENIFIT_SERVICE_REQUEST_TYPE,policyNo);
//		System.out.println("count==" + count);
//		long tmp = 0;
//		int output = count.compareTo(tmp);
//		if (output > 0) {
//			//RequestContext requestContext = RequestContext.getCurrentInstance();
////			PrimeFaces.current().executeScript("$('#validation-Tab').modal('show');");
////			setBodyMes(text.getString("home.error.contriHoliday.alreadyDone"));
//		}
	}

	public List<ProtectionBenifitDTO> zeroIndexValues() {
		List<ProtectionBenifitDTO> protBenifitListZero = new ArrayList<ProtectionBenifitDTO>();
		for (ProtectionBenifitDTO eachRecord : protBenifitList) {
			if (eachRecord.getMemberIndex() != null) {
				if (eachRecord.getMemberIndex().compareTo(BigDecimal.ZERO) == 0) {
					ProtectionBenifitDTO temp = new ProtectionBenifitDTO();
					temp = temp.getCopy(eachRecord);
					Random random = new Random();
					temp.setIndex(random.nextInt(123456) + "fdsfsdfdsf");
					System.out.println("temp.hashCode()" + temp.hashCode());
					protBenifitListZero.add(temp);
				}
			}
		}
		return protBenifitListZero;
	}

	public void sample(){
		if(alterationType!= null){
			termsConditionYesYes=null;
			termsConditionYesNo=null;
			if(alterationType.equalsIgnoreCase("I")){
				termsConditionYesYes=null;
				termsConditionYesNo=null;
				termsConditionYesYes = redirectImpl.listTermAndCondition(Constants.CUSTOMER_PROTECTION_BENIFIT_INC_BASIC_SUM_COVERED, Constants.MANDATORY, Constants.REQUIRED);
				termsConditionYesNo = redirectImpl.listTermAndCondition1(Constants.CUSTOMER_PROTECTION_BENIFIT_INC_BASIC_SUM_COVERED, Constants.MANDATORYNO, Constants.REQUIREDYES);
			}
			else if(alterationType.equalsIgnoreCase("D")){
				termsConditionYesYes=null;
				termsConditionYesNo=null;
				termsConditionYesYes = redirectImpl.listTermAndCondition(Constants. CUSTOMER_PROTECTION_BENIFIT_DEC_BASIC_SUM_COVERED, Constants.MANDATORY, Constants.REQUIRED);
				termsConditionYesNo = redirectImpl.listTermAndCondition1(Constants. CUSTOMER_PROTECTION_BENIFIT_DEC_BASIC_SUM_COVERED, Constants.MANDATORYNO, Constants.REQUIREDYES);
			}
			else if(alterationType.equalsIgnoreCase("IR")){
				termsConditionYesYes=null;
				termsConditionYesNo=null;
				termsConditionYesYes = redirectImpl.listTermAndCondition(Constants.CUSTOMER_PROTECTION_BENIFIT_INC_RIDER_SUM_COVERED, Constants.MANDATORY, Constants.REQUIRED);
				termsConditionYesNo = redirectImpl.listTermAndCondition1(Constants.CUSTOMER_PROTECTION_BENIFIT_INC_RIDER_SUM_COVERED, Constants.MANDATORYNO, Constants.REQUIREDYES);
			}
			else if(alterationType.equalsIgnoreCase("DR")){
				termsConditionYesYes=null;
				termsConditionYesNo=null;
				termsConditionYesYes = redirectImpl.listTermAndCondition(Constants.CUSTOMER_PROTECTION_BENIFIT_DEC_RIDER_SUM_COVERED, Constants.MANDATORY, Constants.REQUIRED);
				termsConditionYesNo = redirectImpl.listTermAndCondition1(Constants.CUSTOMER_PROTECTION_BENIFIT_DEC_RIDER_SUM_COVERED, Constants.MANDATORYNO, Constants.REQUIREDYES);
			}
			else if(alterationType.equalsIgnoreCase("ARB")){
				termsConditionYesYes=null;
				termsConditionYesNo=null;
				termsConditionYesYes = redirectImpl.listTermAndCondition(Constants.CUSTOMER_PROTECTION_BENIFIT_ADD_SUP_RIDER, Constants.MANDATORY, Constants.REQUIRED);
				termsConditionYesNo = redirectImpl.listTermAndCondition1(Constants.CUSTOMER_PROTECTION_BENIFIT_ADD_SUP_RIDER, Constants.MANDATORYNO, Constants.REQUIREDYES);
			}
			else if(alterationType.equalsIgnoreCase("DRB")){
				termsConditionYesYes=null;
				termsConditionYesNo=null;
				termsConditionYesYes = redirectImpl.listTermAndCondition(Constants.CUSTOMER_PROTECTION_BENIFIT_DELETE_SUP_RIDER, Constants.MANDATORY, Constants.REQUIRED);
				termsConditionYesNo = redirectImpl.listTermAndCondition1(Constants.CUSTOMER_PROTECTION_BENIFIT_DELETE_SUP_RIDER, Constants.MANDATORYNO, Constants.REQUIREDYES);
			}				
		}
	}
	
	public void fetchFromService() {

		benfits = new HashMap();
		protBenifitList = new ArrayList();
		protBenifitList = customerPortalServices.getProtectionBenifit(policyNo, Constants.EN);
		// protBenifitList =
		// customerPortalServices.getProtectionBenifit("ULHYP150048801",
		// Constants.EN);
		List<BigDecimal> uniqueMemberIndexs = new ArrayList<BigDecimal>();
		// Collections.sort(uniqueMemberIndexs);

		List<ProtectionBenifitDTO> protBenifitListZero = new ArrayList<ProtectionBenifitDTO>();
		for (ProtectionBenifitDTO eachRecord : protBenifitList) {
			if (eachRecord.getMemberIndex() != null) {
				uniqueMemberIndexs.add(eachRecord.getMemberIndex());
				if (eachRecord.getMemberIndex().compareTo(BigDecimal.ZERO) == 0) {
					protBenifitListZero.add(eachRecord);
				}
			}
		}
		//Collections.reverse(uniqueMemberIndexs);
		Set<BigDecimal> uniqueIndexs = new HashSet<BigDecimal>(uniqueMemberIndexs);
		uniqueMemberIndexs = new ArrayList();
		for (BigDecimal eachUniqueIndex : uniqueIndexs) {
			uniqueMemberIndexs.add(eachUniqueIndex);
		}
		if (uniqueMemberIndexs != null) {
			if (uniqueMemberIndexs.size() > 0) {
				for (BigDecimal eachIndex : uniqueMemberIndexs) {
					List<ProtectionBenifitDTO> eachIndexList = new ArrayList<ProtectionBenifitDTO>();
					for (int i = 0; i < protBenifitList.size(); i++) {
						if (protBenifitList.get(i).getMemberIndex() != null) {
							if (eachIndex.compareTo(protBenifitList.get(i).getMemberIndex()) == 0) {
								if (eachIndex.compareTo(BigDecimal.ZERO) != 0) {
									eachIndexList.add(protBenifitList.get(i));
								}
							}
						}
					}
					if (eachIndex.compareTo(BigDecimal.ZERO) != 0) {
						
						eachIndexList.addAll(zeroIndexValues());
						System.out.println("---------" + eachIndexList.hashCode());
						benfits.put(eachIndex, eachIndexList);
						//Collections.reverse(benfits);
					}
				}
			}
		}

		/*
		 * for(ProtectionBenifitDTO each : protBenifitList){
		 * if(each.getMemberIndex().compareTo(BigDecimal.ZERO) == 0){
		 * protectionBenifitMemberIndex.add(each); } }
		 */

		System.out.println("@@@@ Size of benifit having size 0 @@@@" + protectionBenifitMemberIndex.size());
		protectionBenifitDTO = new ProtectionBenifitDTO();
		for (ProtectionBenifitDTO dto : protBenifitList) {

			String[] tmp = dto.getExistBenifit().split(" ");

			if (tmp.length >= 1) {
				dto.setCurrency(tmp[0]);
			}
			if (tmp.length >= 1) {
				if (!(tmp.length == 1)) {
					dto.setAmountExist(tmp[tmp.length - 1]);
				}
			}
		}
	}

	public void enableCheckBox(int currentIndex) {
		if (!protBenifitList.get(currentIndex).isEnableCheckBox()) {
			protBenifitList.get(currentIndex).setNewvalue(null);
		}
		System.out.println("currentIndex:" + currentIndex);
		for (int i = 0; i < protBenifitList.size(); i++) {
			if (i != currentIndex) {
				// protBenifitList.get(i).setEnableCheckBox(false);
			}
		}
	}

	public void loadValues() {
		questionnaireList.clear();
		fetchFromService();// loading the new values
		benifitValidation();
		sample();
		questionnaireList = hAction.getQuestionnaire(alterationType);
	}

	@SuppressWarnings("unused")
	public void debug(int mainIndex, int subIndex) {
		System.out.println("mainIndex" + mainIndex + "subIndex" + subIndex);
		List<ProtectionBenifitDTO> selectedBenefits = benfits.get(new BigDecimal(mainIndex));

		if (selectedBenefits != null) {
			if (selectedBenefits.size() > 0) {
				System.out.println(selectedBenefits.size());
				String selectedGroupId = null;
				ProtectionBenifitDTO selectedRecord = selectedBenefits.get(subIndex);

				if (selectedRecord != null) {
					// selectedBenefits.get(subIndex).setNewvalue(null);
					if (selectedRecord.getExcelGroupId() != null) {
						if (selectedGroupId == null) {
							selectedGroupId = selectedRecord.getExcelGroupId();
							if (selectedRecord.isEnableCheckBox()) {
								selectedBenefits.get(subIndex).setEnableCheckBox(true);
								selectedBenefits.get(subIndex).setShowCheckBox(true);
							}
						}
						if (selectedGroupId != null) {
							if (selectedBenefits != null) {
								if (selectedBenefits.size() > 0) {
									for (int i = 0; i < selectedBenefits.size(); i++) {
										if (subIndex != i) {
											ProtectionBenifitDTO eachBenefit = selectedBenefits.get(i);
											if (eachBenefit.getExcelGroupId() != null) {
												if (eachBenefit.getExcelGroupId().equalsIgnoreCase(selectedGroupId)) {
													System.out.println("selectedGroupId:" + selectedGroupId);
													if (selectedRecord.isEnableCheckBox()) {
														eachBenefit.setEnableCheckBox(false);
														eachBenefit.setShowCheckBox(false);
													} else {
														eachBenefit.setEnableCheckBox(false);
														eachBenefit.setShowCheckBox(true);
													}
												}
											}
										}

									}
								}
							}
						}
					}
					System.out.println("Selected record group id:" + selectedRecord.getExcelGroupId());
					// selectedRecord.setShowRadioButton(true);
				}
			}

		} else {
			System.out.println("Records not exist");
		}
		/*
		 * Entry<BigDecimal, List<ProtectionBenifitDTO>>
		 * tempBenfits=(Entry<BigDecimal, List<ProtectionBenifitDTO>>)
		 * benfits.entrySet(); if(benfits!=null){ for (Entry<BigDecimal,
		 * List<ProtectionBenifitDTO>> entry : benfits.entrySet()) { for (int i
		 * = 0; i < entry.getValue().size() ; i++) {
		 * 
		 * System.out.println("dto.getIndex()"+index); } } }
		 */
	}

	public boolean benifitValidation() {

		boolean benifitAllowed = true;
		boolean basicCoveredIncreseBenifitAllowed = true;
		boolean minimumBenifitValue = true;
		boolean basicCoveredDecreaseBenifitAllowed = true;
		boolean riderSumCoveredIncreaseBenifitAllowed = true;
		boolean riderSumCoveredDecreaseBenifitAllowed = true;
		boolean supplementryRiderBenifitAddition = true;
		boolean supplementryRiderBenifitDeletion = true;
		String saValidationResult = null;

		if (BSLUtils.isNotNull(alterationType)) {
			if (benfits != null) {
				for (Entry<BigDecimal, List<ProtectionBenifitDTO>> entry : benfits.entrySet()) {
					for (ProtectionBenifitDTO dto : entry.getValue()) {

						dto.setShowCheckBox(false);
						dto.setWocParam(true);
						// dto.setEnableCheckBox(false);

						if (alterationType.equalsIgnoreCase("I") || alterationType.equalsIgnoreCase("D")) {
							logger.info("Inside Increase and decrease");
							if (dto.getBenifitType().equalsIgnoreCase("Base Plan")) {
								dto.setShowCheckBox(true);
								if (BSLUtils.isNotNull(dto.getNewvalue()) && BSLUtils.isNotNull(dto.getAmountExist())) {
									saValidationResult = customerPortalServices.benefitSAVlidation(policyNo,
											dto.getRiderCode(), dto.getRelationBenefit(), dto.getNewvalue());
									if (!saValidationResult.equalsIgnoreCase("VALID")) {					
										logger.info("Inside Increase and decrease error :::::::::" +saValidationResult);
										benifitAllowed = false;
										
										FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
												"Basic sum covered:"+ saValidationResult);
										PrimeFaces.current().dialog().showMessageDynamic(message);
									}
                                    logger.info("ALteration type is ::::::::::::::::" +alterationType);
									if (alterationType.equalsIgnoreCase("I")) {
										logger.info("if less than existing :::::::::" +dto.getNewvalue());
										logger.info("Existing value is ::::::::::::" +dto.getAmountExist().replace(",", "").trim());
										BigDecimal bd = new BigDecimal(dto.getAmountExist().replace(",", "").trim());
										
										if (dto.getNewvalue().compareTo(bd) < 0) {
											benifitAllowed = false;
											basicCoveredIncreseBenifitAllowed = false;
										}

										if (BSLUtils.isNotNull(dto.getCurrency())) {
											if (dto.getCurrency().equalsIgnoreCase("SR")) {
												if (dto.getNewvalue().compareTo(BigDecimal.valueOf(5000)) < 0) {
													benifitAllowed = false;
													minimumBenifitValue = false;
												}
											} else if (dto.getCurrency().equalsIgnoreCase("USD")) {
												if (dto.getNewvalue().compareTo(BigDecimal.valueOf(1362)) < 0) {
													benifitAllowed = false;
													minimumBenifitValue = false;
												}
											}
										}

									} else if (alterationType.equalsIgnoreCase("D")) {
										if (dto.getNewvalue().compareTo(
												new BigDecimal(dto.getAmountExist().replace(",", "").trim())) > 0) {
											benifitAllowed = false;
											basicCoveredDecreaseBenifitAllowed = false;
										}
									}
								}
							}
						} else if (alterationType.equalsIgnoreCase("IR") || alterationType.equalsIgnoreCase("DR")) {
							if (dto.getBenifitType().equalsIgnoreCase("Optional Rider")) {
								if (!dto.getPolicyNo().equalsIgnoreCase("NEW")) {
									dto.setShowCheckBox(true);
									if (dto.getRiderCode().startsWith("WOC")) {
										dto.setWocParam(false);
									}

									if (BSLUtils.isNotNull(dto.getNewvalue())
											&& BSLUtils.isNotNull(dto.getAmountExist())) {

										saValidationResult = customerPortalServices.benefitSAVlidation(policyNo,
												dto.getRiderCode(), dto.getRelationBenefit(), dto.getNewvalue());
										if (!saValidationResult.equalsIgnoreCase("VALID")) {
											benifitAllowed = false;
											FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
													"Basic sum covered."+saValidationResult);
											PrimeFaces.current().dialog().showMessageDynamic(message);
										}

										if (alterationType.equalsIgnoreCase("IR")) {
											if (dto.getNewvalue().compareTo(
													new BigDecimal(dto.getAmountExist().replace(",", "").trim())) < 0) {
												benifitAllowed = false;
												riderSumCoveredIncreaseBenifitAllowed = false;
											}

										} else if (alterationType.equalsIgnoreCase("DR")) {
											if (dto.getNewvalue().compareTo(
													new BigDecimal(dto.getAmountExist().replace(",", "").trim())) > 0) {
												benifitAllowed = false;
												riderSumCoveredDecreaseBenifitAllowed = false;
											}
										}
									}
								}
							}
						} else if (alterationType.equalsIgnoreCase("ARB")) {
							if (dto.getBenifitType().equalsIgnoreCase("Optional Rider")
									|| dto.getBenifitType().equalsIgnoreCase("E")) {
								if (dto.getPolicyNo().equalsIgnoreCase("NEW")) {
									dto.setShowCheckBox(true);
									dto.setWocParam(true);

									if (BSLUtils.isNotNull(dto.getNewvalue())
											&& BSLUtils.isNotNull(dto.getAmountExist())) {

										saValidationResult = customerPortalServices.benefitSAVlidation(policyNo,
												dto.getRiderCode(), dto.getRelationBenefit(), dto.getNewvalue());
										if (!saValidationResult.equalsIgnoreCase("VALID")) {
											benifitAllowed = false;
											FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
													"Basic sum covered."+saValidationResult);
											PrimeFaces.current().dialog().showMessageDynamic(message);
										}

										if (dto.getNewvalue().compareTo(BigDecimal.ZERO) != 0) {
											benifitAllowed = false;
											supplementryRiderBenifitAddition = false;
										}
									}
								}
							}
						} else if (alterationType.equalsIgnoreCase("DRB")) {
							if (dto.getBenifitType().equalsIgnoreCase("Optional Rider")) {
								if (!dto.getPolicyNo().equalsIgnoreCase("NEW")) {
									dto.setShowCheckBox(true);
									dto.setWocParam(false);

									if (BSLUtils.isNotNull(dto.getNewvalue())
											&& BSLUtils.isNotNull(dto.getAmountExist())) {

										saValidationResult = customerPortalServices.benefitSAVlidation(policyNo,
												dto.getRiderCode(), dto.getRelationBenefit(), dto.getNewvalue());
										if (!saValidationResult.equalsIgnoreCase("VALID")) {
											benifitAllowed = false;
											FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
													"Basic sum covered."+saValidationResult);
											PrimeFaces.current().dialog().showMessageDynamic(message);
										}

										if (dto.getNewvalue().compareTo(BigDecimal.ZERO) != 0) {
											benifitAllowed = false;
											supplementryRiderBenifitDeletion = false;
										}
									}
								}
							}
						}
					}
				}
			}
		}

		if (basicCoveredIncreseBenifitAllowed == false) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
					"Basic sum covered value must greater than existing value.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
		if (basicCoveredDecreaseBenifitAllowed == false) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
					"Basic sum covered value must less than existing value.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
		if (riderSumCoveredIncreaseBenifitAllowed == false) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
					"Rider sum covered value must greater than existing value.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
		if (riderSumCoveredDecreaseBenifitAllowed == false) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
					"Rider sum covered value must less than existing value.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
		if (supplementryRiderBenifitDeletion == false) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
					"New value must be 0.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
		if (minimumBenifitValue == false) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
					"Minimum Value of the 'Family Takaful Benefit' must be SR 5,000 or USD 1,362.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}

		logger.info("Status in benifitValidation Method for Benefit ::::::" + benifitAllowed);
		return benifitAllowed;
	}

	public boolean validateFinancialDetails() {

		boolean status = true;
			if (BSLUtils.isNull(protectionBenifitDTO.getQuesOneSecond())) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Mandatory Field.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				status = false;
			}
			if (BSLUtils.isNull(protectionBenifitDTO.getQuesTwoASecond())) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Mandatory Field.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				status = false;
			}
			if (BSLUtils.isNull(protectionBenifitDTO.getQuesTwoBSecond())) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Mandatory Field.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				status = false;
			}
			if (BSLUtils.isNull(protectionBenifitDTO.getQuesTwoCSecond())) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Mandatory Field.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				status = false;
			}
			if (BSLUtils.isNull(protectionBenifitDTO.getQuesTwoDSecond())) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Mandatory Field.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				status = false;
			}
			if (BSLUtils.isNull(protectionBenifitDTO.getQuesTwoESecond())) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Mandatory Field.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				status = false;
			}
			if (BSLUtils.isNull(protectionBenifitDTO.getQuesTwoFSecond())) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Mandatory Field.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				status = false;
			}
			if (BSLUtils.isNull(protectionBenifitDTO.getQuesTwoGSecond())) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Mandatory Field.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				status = false;
			}
			if (BSLUtils.isNull(protectionBenifitDTO.getQuesTwoHSecond())) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Mandatory Field.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				status = false;
			}
			if (BSLUtils.isNull(protectionBenifitDTO.getQuesThreeSecond())) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Mandatory Field.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				status = false;
			}
			if (BSLUtils.isNull(protectionBenifitDTO.getQuesFourSecond())) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Mandatory Field.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				status = false;
			}
			if (BSLUtils.isNull(protectionBenifitDTO.getQuesFiveSecond())) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Mandatory Field.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				status = false;
			}
			if (BSLUtils.isNull(protectionBenifitDTO.getQuesSixSecond())) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Mandatory Field.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				status = false;
			}
			if (BSLUtils.isNull(protectionBenifitDTO.getQuesSevenSecond())) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Mandatory Field.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				status = false;
			}
			if (BSLUtils.isNull(protectionBenifitDTO.getQuesEightSecond())) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Mandatory Field.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				status = false;
			}
			if (BSLUtils.isNull(protectionBenifitDTO.getQuesNineSecond())) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Mandatory Field.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				status = false;
			}
			if (BSLUtils.isNull(protectionBenifitDTO.getQuesTenSecond())) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Mandatory Field.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				status = false;
			}
	
		logger.info("Status in validateFinancialDetails Method for Benefit ::::::" + status);
		return status;
	}

	public void assetsTotal() {

		BigDecimal assetCash = BigDecimal.valueOf(0.00);
		BigDecimal assetShares = BigDecimal.valueOf(0.00);
		BigDecimal assetOthers = BigDecimal.valueOf(0.00);
		BigDecimal assetRealEstate = BigDecimal.valueOf(0.00);

		if (BSLUtils.isNotNull(protectionBenifitDTO.getAssetsCash())) {
			assetCash = protectionBenifitDTO.getAssetsCash();
		}
		if (BSLUtils.isNotNull(protectionBenifitDTO.getAssetsShares())) {
			assetShares = protectionBenifitDTO.getAssetsShares();
		}
		if (BSLUtils.isNotNull(protectionBenifitDTO.getAssetsOthers())) {
			assetOthers = protectionBenifitDTO.getAssetsOthers();
		}
		if (BSLUtils.isNotNull(protectionBenifitDTO.getAssetsRealEstate())) {
			assetRealEstate = protectionBenifitDTO.getAssetsRealEstate();
		}
		BigDecimal total = assetCash.add(assetShares).add(assetOthers).add(assetRealEstate);
		assetsTotal = total.toPlainString();

	}

	public void liabilitiesTotal() {

		BigDecimal liabLoans = BigDecimal.valueOf(0.00);
		BigDecimal liabAccounts = BigDecimal.valueOf(0.00);
		BigDecimal liabMortgage = BigDecimal.valueOf(0.00);
		BigDecimal liabOther = BigDecimal.valueOf(0.00);

		if (BSLUtils.isNotNull(protectionBenifitDTO.getLiabilitiesLoan())) {
			liabLoans = protectionBenifitDTO.getLiabilitiesLoan();
		}
		if (BSLUtils.isNotNull(protectionBenifitDTO.getLiabilitiesPayable())) {
			liabAccounts = protectionBenifitDTO.getLiabilitiesPayable();
		}
		if (BSLUtils.isNotNull(protectionBenifitDTO.getLiabilitiesMortgage())) {
			liabMortgage = protectionBenifitDTO.getLiabilitiesMortgage();
		}
		if (BSLUtils.isNotNull(protectionBenifitDTO.getLiabilitiesOther())) {
			liabOther = protectionBenifitDTO.getLiabilitiesOther();
		}
		BigDecimal total = liabLoans.add(liabAccounts).add(liabMortgage).add(liabOther);
		liabilitiesTotal = total.toPlainString();

	}
	
	@Autowired
	CpNFPersonalDetailsDAO cpNFPersonalDetailsDAOImpl;
	public int transStatus() {
		Long count = cpNFPersonalDetailsDAOImpl.getRowCount(Constants.PROTECTION_BENIFIT_SERVICE_REQUEST_TYPE, policyNo);
		System.out.println("count==" + count);
		long tmp = 0;
		int output = count.compareTo(tmp);
		return output;
	}
	
	public void btnContinueLater() {

		logger.info("::: Entering inside btnContinueLater Method for Protection Benefit ::::::");

		//boolean _protectionValidation = true;
		boolean _validationStatus = false;
		Date currentDate = new Date();
		serviceRequestMasterDTO.setServiceRequestDate(currentDate);
		serviceRequestMasterDTO.setServiceRequestType(Constants.PROTECTION_BENIFIT_SERVICE_REQUEST_TYPE);
		serviceRequestMasterDTO.setPolicyNo(policyNo);
		serviceRequestMasterDTO.setUserId(SecurityLibrary.getFullLoggedInUser().getVuserName());
		serviceRequestMasterDTO.setRequestStatus(Constants.INACTIVE);
		serviceRequestMasterDTO.setRecentUpdateDate(currentDate);
		serviceRequestMasterDTO.setSeqno(seqno);

		int transCount = transStatus();
		String result = customerPortalServices.getAllowedTransaction(policyNo, "NONFN");
		String applicaleBox = null;
		if (BSLUtils.isNotNull(result)) {
			if (result.equalsIgnoreCase("ALLOWED")) {
				if (transCount == 0) {
					applicaleBox = "Y";
					
					_validationStatus = benifitValidation();
					logger.info("Validation status in Continue talter ::::::" + _validationStatus);
					if (_validationStatus == true) {
						selectedBenifitList = new ArrayList();
						/*if (BSLUtils.isNotNull(secondMember)) {
							_protectionValidation = validateFinancialDetails();
						}*/
						// logger.info("_protectionValidation status in Continue talter::::::" +_protectionValidation);
						// if (_protectionValidation == true) {
						//logger.info("Inside Master table save data condition ::::::"+_protectionValidation);
						serviceRequestMasterDTO = transAction.insertServiceRequest(serviceRequestMasterDTO);
						boolean status = false;
						List<ProtectionBenifitDTO> selectedBenifit = new ArrayList<ProtectionBenifitDTO>();
						for (Entry<BigDecimal, List<ProtectionBenifitDTO>> entry : benfits.entrySet()) {
							System.out.println("Key = " + entry.getKey() + "\n, Value = " + entry.getValue());
							String memberName = null;
							selectedBenifitList = new ArrayList();
							for (ProtectionBenifitDTO dto : entry.getValue()) {
								ProtectionBenifitDTO protectionBenifitDTO1 = new ProtectionBenifitDTO();
								protectionBenifitDTO1.setAssetsCash(protectionBenifitDTO.getAssetsCash());
								protectionBenifitDTO1.setAssetsOthers((protectionBenifitDTO.getAssetsOthers()));
								protectionBenifitDTO1.setAssetsRealEstate(protectionBenifitDTO.getAssetsRealEstate());
								protectionBenifitDTO1.setAssetsShares(protectionBenifitDTO.getAssetsShares());
								protectionBenifitDTO1.setCoveredBankName(protectionBenifitDTO.getCoveredBankName());
								protectionBenifitDTO1.setCoveredIBAN(protectionBenifitDTO.getCoveredIBAN());
								protectionBenifitDTO1.setCoveredSourceFund(protectionBenifitDTO.getCoveredSourceFund());
								protectionBenifitDTO1.setPlanHolderBankName(protectionBenifitDTO.getPlanHolderBankName());
								protectionBenifitDTO1.setPlanHolderIBAN(protectionBenifitDTO.getPlanHolderIBAN());
								protectionBenifitDTO1.setPlanHolderSourceFund(protectionBenifitDTO.getPlanHolderSourceFund());
								protectionBenifitDTO1.setIncomeYearOne(protectionBenifitDTO.getIncomeYearOne());
								protectionBenifitDTO1.setIncomeYearTwo(protectionBenifitDTO.getIncomeYearTwo());
								protectionBenifitDTO1.setIncomeYearThree(protectionBenifitDTO.getIncomeYearThree());
								protectionBenifitDTO1.setLiabilitiesLoan(protectionBenifitDTO.getLiabilitiesLoan());
								protectionBenifitDTO1.setLiabilitiesMortgage(protectionBenifitDTO.getLiabilitiesMortgage());
								protectionBenifitDTO1.setLiabilitiesOther(protectionBenifitDTO.getLiabilitiesOther());
								protectionBenifitDTO1.setLiabilitiesPayable(protectionBenifitDTO.getLiabilitiesPayable());
								protectionBenifitDTO1.setFirstCoverHeight(protectionBenifitDTO.getFirstCoverHeight());
								protectionBenifitDTO1.setFirstCoverWeight(protectionBenifitDTO.getFirstCoverWeight());
								protectionBenifitDTO1.setSecondCoverHeight(protectionBenifitDTO.getSecondCoverHeight());
								protectionBenifitDTO1.setSecondCoverWeight(protectionBenifitDTO.getSecondCoverWeight());
								protectionBenifitDTO1.setQuesOneFirst(protectionBenifitDTO.getQuesOneFirst());
								protectionBenifitDTO1.setQuesTwoAFirst(protectionBenifitDTO.getQuesTwoAFirst());
								protectionBenifitDTO1.setQuesTwoBFirst(protectionBenifitDTO.getQuesTwoBSecond());
								protectionBenifitDTO1.setQuesTwoCFirst(protectionBenifitDTO.getQuesTwoCFirst());
								protectionBenifitDTO1.setQuesTwoDFirst(protectionBenifitDTO.getQuesTwoDFirst());
								protectionBenifitDTO1.setQuesTwoEFirst(protectionBenifitDTO.getQuesTwoEFirst());
								protectionBenifitDTO1.setQuesTwoFFirst(protectionBenifitDTO.getQuesTwoFFirst());
								protectionBenifitDTO1.setQuesTwoGFirst(protectionBenifitDTO.getQuesTwoFFirst());
								protectionBenifitDTO1.setQuesTwoHFirst(protectionBenifitDTO.getQuesTwoGFirst());
								protectionBenifitDTO1.setQuesThreeFirst(protectionBenifitDTO.getQuesThreeFirst());
								protectionBenifitDTO1.setQuesFourFirst(protectionBenifitDTO.getQuesFourFirst());
								protectionBenifitDTO1.setQuesFiveFirst(protectionBenifitDTO.getQuesFiveFirst());
								protectionBenifitDTO1.setQuesSixFirst(protectionBenifitDTO.getQuesSixFirst());
								protectionBenifitDTO1.setQuesSevenFirst(protectionBenifitDTO.getQuesSevenFirst());
								protectionBenifitDTO1.setQuesEightFirst(protectionBenifitDTO.getQuesEightFirst());
								protectionBenifitDTO1.setQuesNineFirst(protectionBenifitDTO.getQuesNineFirst());
								protectionBenifitDTO1.setQuesTenFirst(protectionBenifitDTO.getQuesTenFirst());
								protectionBenifitDTO1.setQuesElevenFirst(protectionBenifitDTO.getQuesElevenFirst());
								protectionBenifitDTO1.setQuesElevenAFirst(protectionBenifitDTO.getQuesElevenAFirst());

								protectionBenifitDTO1.setQuesOneSecond(protectionBenifitDTO.getQuesOneSecond());
								protectionBenifitDTO1.setQuesTwoASecond(protectionBenifitDTO.getQuesTwoASecond());
								protectionBenifitDTO1.setQuesTwoBSecond(protectionBenifitDTO.getQuesTwoBSecond());
								protectionBenifitDTO1.setQuesTwoCSecond(protectionBenifitDTO.getQuesTwoCSecond());
								protectionBenifitDTO1.setQuesTwoDSecond(protectionBenifitDTO.getQuesTwoDSecond());
								protectionBenifitDTO1.setQuesTwoESecond(protectionBenifitDTO.getQuesTwoESecond());
								protectionBenifitDTO1.setQuesTwoFSecond(protectionBenifitDTO.getQuesTwoFSecond());
								protectionBenifitDTO1.setQuesTwoGSecond(protectionBenifitDTO.getQuesTwoGSecond());
								protectionBenifitDTO1.setQuesTwoHSecond(protectionBenifitDTO.getQuesTwoHSecond());
								protectionBenifitDTO1.setQuesThreeSecond(protectionBenifitDTO.getQuesThreeSecond());
								protectionBenifitDTO1.setQuesFourSecond(protectionBenifitDTO.getQuesFourSecond());
								protectionBenifitDTO1.setQuesFiveSecond(protectionBenifitDTO.getQuesFiveSecond());
								protectionBenifitDTO1.setQuesSixSecond(protectionBenifitDTO.getQuesSixSecond());
								protectionBenifitDTO1.setQuesSevenSecond(protectionBenifitDTO.getQuesSevenSecond());
								protectionBenifitDTO1.setQuesEightSecond(protectionBenifitDTO.getQuesEightSecond());
								protectionBenifitDTO1.setQuesNineSecond(protectionBenifitDTO.getQuesNineSecond());
								protectionBenifitDTO1.setQuesTenSecond(protectionBenifitDTO.getQuesTenSecond());
								protectionBenifitDTO1.setQuesElevenSecond(protectionBenifitDTO.getQuesElevenSecond());
								protectionBenifitDTO1.setQuesElevenASecond(protectionBenifitDTO.getQuesElevenASecond());
								protectionBenifitDTO1.setFirstCoverMedDetails(protectionBenifitDTO.getFirstCoverMedDetails());
								protectionBenifitDTO1.setSecondCoverMedDetails(protectionBenifitDTO.getSecondCoverMedDetails());

								protectionBenifitDTO1.setServiceRequestNo(serviceRequestMasterDTO);
								if (memberName == null) {
									if (dto.getMemberName() != null) {
										memberName = dto.getMemberName();
									}
								}
								if (dto.isEnableCheckBox() == true) {
									logger.info("Inside benefit isEnableCHeckBox COndition ::::::::");
									protectionBenifitDTO1.setPolicyNo(dto.getPolicyNo());
									protectionBenifitDTO1.setAlterationType(alterationType);
									protectionBenifitDTO1.setBenifitRiderName(dto.getBenifitRiderName());
									protectionBenifitDTO1.setBenifitType(dto.getBenifitType());
									protectionBenifitDTO1.setMemberName(memberName);// setting
																					// Membername
									protectionBenifitDTO1.setExistBenifit(dto.getExistBenifit());
									protectionBenifitDTO1.setCummulativeBenifit(dto.getCummulativeBenifit());
									protectionBenifitDTO1.setNewvalue(dto.getNewvalue());
									protectionBenifitDTO1.setRiderCode(dto.getRiderCode());
									selectedBenifitList.add(protectionBenifitDTO1);
									logger.info("Benefit List Size is :::::::::::::::" + selectedBenifitList.size());
								}
							}
							logger.info("Inside action of Benefir :::::::" + selectedBenifitList);
							status = protectionBenifitBL.protectionBenifitAlteration(selectedBenifitList);
							//status = adminImpl.saveAnswers(questionnaireList);
							clearProtectionValues();
							System.out.println("status" + status);
							if (status) {
								getSession().setAttribute("TRANSACTION", "PRODUCTION");
								PrimeFaces.current().executeScript("PF('dlg3').show()");
								clearProtectionValues();
							}else {
								FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
										"Please try after sometime or contact our Salama Support.");
								PrimeFaces.current().dialog().showMessageDynamic(message);
							}
						}
					}

					
					
				}else {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
							"Your old transaction is under processing.");
					PrimeFaces.current().dialog().showMessageDynamic(message);
				}
			}else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Request is Not Eligible as the Plan status is inactive.Please contact Salama.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		}
			
	}
	
	private String processing;
	public String fatcasample() {
		if(processing.equalsIgnoreCase("YES")) {
			return "/factaFrom/productionbenefitfatca?faces-redirect=true";
		}else {
		   return null;	
		}
	}
	
	
	
	public String getProcessing() {
		return processing;
	}

	public void setProcessing(String processing) {
		this.processing = processing;
	}

	public void btnContinueLaterfatca() {

		logger.info("::: Entering inside btnContinueLater Method for Protection Benefit ::::::");
		processing = null;
		processing = "NO";
		//boolean _protectionValidation = true;
		boolean _validationStatus = false;
		Date currentDate = new Date();
		serviceRequestMasterDTO.setServiceRequestDate(currentDate);
		serviceRequestMasterDTO.setServiceRequestType(Constants.PROTECTION_BENIFIT_SERVICE_REQUEST_TYPE);
		serviceRequestMasterDTO.setPolicyNo(policyNo);
		serviceRequestMasterDTO.setUserId(SecurityLibrary.getFullLoggedInUser().getVuserName());
		serviceRequestMasterDTO.setRequestStatus(Constants.INACTIVE);
		serviceRequestMasterDTO.setRecentUpdateDate(currentDate);
		serviceRequestMasterDTO.setSeqno(seqno);

		int transCount = transStatus();
		String result = customerPortalServices.getAllowedTransaction(policyNo, "NONFN");
		String applicaleBox = null;
		if (BSLUtils.isNotNull(result)) {
			if (result.equalsIgnoreCase("ALLOWED")) {
				if (transCount == 0) {
					applicaleBox = "Y";
					
					_validationStatus = benifitValidation();
					logger.info("Validation status in Continue talter ::::::" + _validationStatus);
					if (_validationStatus == true) {
						selectedBenifitList = new ArrayList();
						/*if (BSLUtils.isNotNull(secondMember)) {
							_protectionValidation = validateFinancialDetails();
						}*/
						// logger.info("_protectionValidation status in Continue talter::::::" +_protectionValidation);
						// if (_protectionValidation == true) {
						//logger.info("Inside Master table save data condition ::::::"+_protectionValidation);
						serviceRequestMasterDTO = transAction.insertServiceRequest(serviceRequestMasterDTO);
						boolean status = false;
						List<ProtectionBenifitDTO> selectedBenifit = new ArrayList<ProtectionBenifitDTO>();
						for (Entry<BigDecimal, List<ProtectionBenifitDTO>> entry : benfits.entrySet()) {
							System.out.println("Key = " + entry.getKey() + "\n, Value = " + entry.getValue());
							String memberName = null;
							selectedBenifitList = new ArrayList();
							for (ProtectionBenifitDTO dto : entry.getValue()) {
								ProtectionBenifitDTO protectionBenifitDTO1 = new ProtectionBenifitDTO();
								protectionBenifitDTO1.setAssetsCash(protectionBenifitDTO.getAssetsCash());
								protectionBenifitDTO1.setAssetsOthers((protectionBenifitDTO.getAssetsOthers()));
								protectionBenifitDTO1.setAssetsRealEstate(protectionBenifitDTO.getAssetsRealEstate());
								protectionBenifitDTO1.setAssetsShares(protectionBenifitDTO.getAssetsShares());
								protectionBenifitDTO1.setCoveredBankName(protectionBenifitDTO.getCoveredBankName());
								protectionBenifitDTO1.setCoveredIBAN(protectionBenifitDTO.getCoveredIBAN());
								protectionBenifitDTO1.setCoveredSourceFund(protectionBenifitDTO.getCoveredSourceFund());
								protectionBenifitDTO1.setPlanHolderBankName(protectionBenifitDTO.getPlanHolderBankName());
								protectionBenifitDTO1.setPlanHolderIBAN(protectionBenifitDTO.getPlanHolderIBAN());
								protectionBenifitDTO1.setPlanHolderSourceFund(protectionBenifitDTO.getPlanHolderSourceFund());
								protectionBenifitDTO1.setIncomeYearOne(protectionBenifitDTO.getIncomeYearOne());
								protectionBenifitDTO1.setIncomeYearTwo(protectionBenifitDTO.getIncomeYearTwo());
								protectionBenifitDTO1.setIncomeYearThree(protectionBenifitDTO.getIncomeYearThree());
								protectionBenifitDTO1.setLiabilitiesLoan(protectionBenifitDTO.getLiabilitiesLoan());
								protectionBenifitDTO1.setLiabilitiesMortgage(protectionBenifitDTO.getLiabilitiesMortgage());
								protectionBenifitDTO1.setLiabilitiesOther(protectionBenifitDTO.getLiabilitiesOther());
								protectionBenifitDTO1.setLiabilitiesPayable(protectionBenifitDTO.getLiabilitiesPayable());
								protectionBenifitDTO1.setFirstCoverHeight(protectionBenifitDTO.getFirstCoverHeight());
								protectionBenifitDTO1.setFirstCoverWeight(protectionBenifitDTO.getFirstCoverWeight());
								protectionBenifitDTO1.setSecondCoverHeight(protectionBenifitDTO.getSecondCoverHeight());
								protectionBenifitDTO1.setSecondCoverWeight(protectionBenifitDTO.getSecondCoverWeight());
								protectionBenifitDTO1.setQuesOneFirst(protectionBenifitDTO.getQuesOneFirst());
								protectionBenifitDTO1.setQuesTwoAFirst(protectionBenifitDTO.getQuesTwoAFirst());
								protectionBenifitDTO1.setQuesTwoBFirst(protectionBenifitDTO.getQuesTwoBSecond());
								protectionBenifitDTO1.setQuesTwoCFirst(protectionBenifitDTO.getQuesTwoCFirst());
								protectionBenifitDTO1.setQuesTwoDFirst(protectionBenifitDTO.getQuesTwoDFirst());
								protectionBenifitDTO1.setQuesTwoEFirst(protectionBenifitDTO.getQuesTwoEFirst());
								protectionBenifitDTO1.setQuesTwoFFirst(protectionBenifitDTO.getQuesTwoFFirst());
								protectionBenifitDTO1.setQuesTwoGFirst(protectionBenifitDTO.getQuesTwoFFirst());
								protectionBenifitDTO1.setQuesTwoHFirst(protectionBenifitDTO.getQuesTwoGFirst());
								protectionBenifitDTO1.setQuesThreeFirst(protectionBenifitDTO.getQuesThreeFirst());
								protectionBenifitDTO1.setQuesFourFirst(protectionBenifitDTO.getQuesFourFirst());
								protectionBenifitDTO1.setQuesFiveFirst(protectionBenifitDTO.getQuesFiveFirst());
								protectionBenifitDTO1.setQuesSixFirst(protectionBenifitDTO.getQuesSixFirst());
								protectionBenifitDTO1.setQuesSevenFirst(protectionBenifitDTO.getQuesSevenFirst());
								protectionBenifitDTO1.setQuesEightFirst(protectionBenifitDTO.getQuesEightFirst());
								protectionBenifitDTO1.setQuesNineFirst(protectionBenifitDTO.getQuesNineFirst());
								protectionBenifitDTO1.setQuesTenFirst(protectionBenifitDTO.getQuesTenFirst());
								protectionBenifitDTO1.setQuesElevenFirst(protectionBenifitDTO.getQuesElevenFirst());
								protectionBenifitDTO1.setQuesElevenAFirst(protectionBenifitDTO.getQuesElevenAFirst());

								protectionBenifitDTO1.setQuesOneSecond(protectionBenifitDTO.getQuesOneSecond());
								protectionBenifitDTO1.setQuesTwoASecond(protectionBenifitDTO.getQuesTwoASecond());
								protectionBenifitDTO1.setQuesTwoBSecond(protectionBenifitDTO.getQuesTwoBSecond());
								protectionBenifitDTO1.setQuesTwoCSecond(protectionBenifitDTO.getQuesTwoCSecond());
								protectionBenifitDTO1.setQuesTwoDSecond(protectionBenifitDTO.getQuesTwoDSecond());
								protectionBenifitDTO1.setQuesTwoESecond(protectionBenifitDTO.getQuesTwoESecond());
								protectionBenifitDTO1.setQuesTwoFSecond(protectionBenifitDTO.getQuesTwoFSecond());
								protectionBenifitDTO1.setQuesTwoGSecond(protectionBenifitDTO.getQuesTwoGSecond());
								protectionBenifitDTO1.setQuesTwoHSecond(protectionBenifitDTO.getQuesTwoHSecond());
								protectionBenifitDTO1.setQuesThreeSecond(protectionBenifitDTO.getQuesThreeSecond());
								protectionBenifitDTO1.setQuesFourSecond(protectionBenifitDTO.getQuesFourSecond());
								protectionBenifitDTO1.setQuesFiveSecond(protectionBenifitDTO.getQuesFiveSecond());
								protectionBenifitDTO1.setQuesSixSecond(protectionBenifitDTO.getQuesSixSecond());
								protectionBenifitDTO1.setQuesSevenSecond(protectionBenifitDTO.getQuesSevenSecond());
								protectionBenifitDTO1.setQuesEightSecond(protectionBenifitDTO.getQuesEightSecond());
								protectionBenifitDTO1.setQuesNineSecond(protectionBenifitDTO.getQuesNineSecond());
								protectionBenifitDTO1.setQuesTenSecond(protectionBenifitDTO.getQuesTenSecond());
								protectionBenifitDTO1.setQuesElevenSecond(protectionBenifitDTO.getQuesElevenSecond());
								protectionBenifitDTO1.setQuesElevenASecond(protectionBenifitDTO.getQuesElevenASecond());
								protectionBenifitDTO1.setFirstCoverMedDetails(protectionBenifitDTO.getFirstCoverMedDetails());
								protectionBenifitDTO1.setSecondCoverMedDetails(protectionBenifitDTO.getSecondCoverMedDetails());

								protectionBenifitDTO1.setServiceRequestNo(serviceRequestMasterDTO);
								if (memberName == null) {
									if (dto.getMemberName() != null) {
										memberName = dto.getMemberName();
									}
								}
								if (dto.isEnableCheckBox() == true) {
									logger.info("Inside benefit isEnableCHeckBox COndition ::::::::");
									protectionBenifitDTO1.setPolicyNo(dto.getPolicyNo());
									protectionBenifitDTO1.setAlterationType(alterationType);
									protectionBenifitDTO1.setBenifitRiderName(dto.getBenifitRiderName());
									protectionBenifitDTO1.setBenifitType(dto.getBenifitType());
									protectionBenifitDTO1.setMemberName(memberName);// setting
																					// Membername
									protectionBenifitDTO1.setExistBenifit(dto.getExistBenifit());
									protectionBenifitDTO1.setCummulativeBenifit(dto.getCummulativeBenifit());
									protectionBenifitDTO1.setNewvalue(dto.getNewvalue());
									protectionBenifitDTO1.setRiderCode(dto.getRiderCode());
									selectedBenifitList.add(protectionBenifitDTO1);
									logger.info("Benefit List Size is :::::::::::::::" + selectedBenifitList.size());
								}
							}
							logger.info("Inside action of Benefir :::::::" + selectedBenifitList);
							status = protectionBenifitBL.protectionBenifitAlteration(selectedBenifitList);
							//status = adminImpl.saveAnswers(questionnaireList);
							clearProtectionValues();
							System.out.println("status" + status);
							if (status) {
								processing = "YES";
								getSession().setAttribute("TRANSACTION", "PRODUCTION");
								clearProtectionValues();
							}else {
								FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
										"Please try after sometime or contact our Salama Support.");
								PrimeFaces.current().dialog().showMessageDynamic(message);
							}
						}
					}

					
					
				}else {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
							"Your old transaction is under processing.");
					PrimeFaces.current().dialog().showMessageDynamic(message);
				}
			}else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Request is Not Eligible as the Plan status is inactive.Please contact Salama.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		}
			
	}

	public void clearProtectionValues() {

		for (ProtectionBenifitDTO each : protBenifitList) {
			each.setNewvalue(null);
			setEnablebox(false);
			protectionBenifitDTO.setShowCheckBox(false);
			protectionBenifitDTO.setEnableCheckBox(false);
		}
		setTermsCondition(false);
		setAlterationType(null);
		setEnablebox(false);
		protectionBenifitDTO.setShowCheckBox(false);
		protectionBenifitDTO.setShowRadioButton(false);
		protectionBenifitDTO.setNewvalue(null);
		protectionBenifitDTO.setFirstCoverHeight("");
		protectionBenifitDTO.setSecondCoverHeight("");
		protectionBenifitDTO.setFirstCoverWeight("");
		protectionBenifitDTO.setSecondCoverWeight("");
		protectionBenifitDTO.setQuesOneFirst("");
		protectionBenifitDTO.setQuesOneSecond("");
		protectionBenifitDTO.setQuesTwoAFirst("");
		protectionBenifitDTO.setQuesTwoASecond("");
		protectionBenifitDTO.setQuesTwoBFirst("");
		protectionBenifitDTO.setQuesTwoBSecond("");
		protectionBenifitDTO.setQuesTwoCFirst("");
		protectionBenifitDTO.setQuesTwoCSecond("");
		protectionBenifitDTO.setQuesTwoDFirst("");
		protectionBenifitDTO.setQuesTwoDSecond("");
		protectionBenifitDTO.setQuesTwoEFirst("");
		protectionBenifitDTO.setQuesTwoESecond("");
		protectionBenifitDTO.setQuesTwoFFirst("");
		protectionBenifitDTO.setQuesTwoFSecond("");
		protectionBenifitDTO.setQuesTwoGFirst("");
		protectionBenifitDTO.setQuesTwoGSecond("");
		protectionBenifitDTO.setQuesTwoHFirst("");
		protectionBenifitDTO.setQuesTwoHSecond("");
		protectionBenifitDTO.setQuesThreeFirst("");
		protectionBenifitDTO.setQuesThreeSecond("");
		protectionBenifitDTO.setQuesFourFirst("");
		protectionBenifitDTO.setQuesFourSecond("");
		protectionBenifitDTO.setQuesFiveFirst("");
		protectionBenifitDTO.setQuesFiveSecond("");
		protectionBenifitDTO.setQuesSixFirst("");
		protectionBenifitDTO.setQuesSixSecond("");
		protectionBenifitDTO.setQuesSevenFirst("");
		protectionBenifitDTO.setQuesSevenSecond("");
		protectionBenifitDTO.setQuesEightFirst("");
		protectionBenifitDTO.setQuesEightFirst("");
		protectionBenifitDTO.setQuesEightSecond("");
		protectionBenifitDTO.setQuesNineFirst("");
		protectionBenifitDTO.setQuesNineSecond("");
		protectionBenifitDTO.setQuesTenFirst("");
		protectionBenifitDTO.setQuesTenSecond("");
		protectionBenifitDTO.setQuesElevenFirst("");
		protectionBenifitDTO.setQuesElevenSecond("");
		protectionBenifitDTO.setQuesElevenAFirst("");
		protectionBenifitDTO.setQuesElevenASecond("");
		protectionBenifitDTO.setFirstCoverMedDetails("");
		protectionBenifitDTO.setSecondCoverMedDetails("");
		protectionBenifitDTO.setCoveredBankName("");
		protectionBenifitDTO.setCoveredIBAN("");
		protectionBenifitDTO.setCoveredSourceFund("");
		protectionBenifitDTO.setPlanHolderBankName("");
		protectionBenifitDTO.setPlanHolderIBAN("");
		protectionBenifitDTO.setPlanHolderSourceFund("");
		protectionBenifitDTO.setIncomeYearOne(null);
		protectionBenifitDTO.setIncomeYearTwo(null);
		protectionBenifitDTO.setIncomeYearThree(null);
		protectionBenifitDTO.setAssetsCash(null);
		protectionBenifitDTO.setLiabilitiesLoan(null);
		protectionBenifitDTO.setAssetsShares(null);
		protectionBenifitDTO.setLiabilitiesPayable(null);
		protectionBenifitDTO.setLiabilitiesMortgage(null);
		protectionBenifitDTO.setAssetsOthers(null);
		protectionBenifitDTO.setLiabilitiesOther(null);
		setLiabilitiesTotal(null);
		setAssetsTotal(null);
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public List<ProtectionBenifitDTO> getProtBenifitList() {
		return protBenifitList;
	}


	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public void setProtBenifitList(List<ProtectionBenifitDTO> protBenifitList) {
		this.protBenifitList = protBenifitList;
	}

	public void setCustomerPortalServices(CustomerPortalServicesImpl customerPortalServices) {
		this.customerPortalServices = customerPortalServices;
	}

	public String getAlterationType() {
		return alterationType;
	}

	public void setAlterationType(String alterationType) {
		this.alterationType = alterationType;
	}

	public boolean isEnablebox() {
		return enablebox;
	}

	public void setEnablebox(boolean enablebox) {
		this.enablebox = enablebox;
	}

	public ProtectionBenifitDTO getProtectionBenifitDTO() {
		return protectionBenifitDTO;
	}

	public void setProtectionBenifitDTO(ProtectionBenifitDTO protectionBenifitDTO) {
		this.protectionBenifitDTO = protectionBenifitDTO;
	}

	public String getAssetsTotal() {
		return assetsTotal;
	}

	public String getLiabilitiesTotal() {
		return liabilitiesTotal;
	}

	public void setAssetsTotal(String assetsTotal) {
		this.assetsTotal = assetsTotal;
	}

	public void setLiabilitiesTotal(String liabilitiesTotal) {
		this.liabilitiesTotal = liabilitiesTotal;
	}

	public ServiceRequestMasterDTO getServiceRequestMasterDTO() {
		return serviceRequestMasterDTO;
	}

	public void setServiceRequestMasterDTO(ServiceRequestMasterDTO serviceRequestMasterDTO) {
		this.serviceRequestMasterDTO = serviceRequestMasterDTO;
	}


	public void setProtectionBenifitBL(ProtectionBenifitBLImpl protectionBenifitBL) {
		this.protectionBenifitBL = protectionBenifitBL;
	}

	public List<ProtectionBenifitDTO> getProtectionBenifitMemberIndex() {
		return protectionBenifitMemberIndex;
	}

	public void setProtectionBenifitMemberIndex(List<ProtectionBenifitDTO> protectionBenifitMemberIndex) {
		this.protectionBenifitMemberIndex = protectionBenifitMemberIndex;
	}

	public static void main(String args[]) {
		ProtectionBenifitAction protectionBenifitAction = new ProtectionBenifitAction();
		protectionBenifitAction.fetchFromService();
	}

	public Map<BigDecimal, List<ProtectionBenifitDTO>> getBenfits() {
		return benfits;
	}

	public void setBenfits(Map<BigDecimal, List<ProtectionBenifitDTO>> benfits) {
		this.benfits = benfits;
	}

	public boolean isFatcaFlagpopUp() {
		return fatcaFlagpopUp;
	}

	public void setFatcaFlagpopUp(boolean fatcaFlagpopUp) {
		this.fatcaFlagpopUp = fatcaFlagpopUp;
	}

	public String getBodyMes() {
		return bodyMes;
	}

	public void setBodyMes(String bodyMes) {
		this.bodyMes = bodyMes;
	}

	public String getSecondMember() {
		return secondMember;
	}

	public void setSecondMember(String secondMember) {
		this.secondMember = secondMember;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<CpTermAndConditionDTO> getTermsConditionYesNo() {
		return termsConditionYesNo;
	}

	public void setTermsConditionYesNo(List<CpTermAndConditionDTO> termsConditionYesNo) {
		this.termsConditionYesNo = termsConditionYesNo;
	}

	public List<CpTermAndConditionDTO> getTermsConditionYesYes() {
		return termsConditionYesYes;
	}

	public void setTermsConditionYesYes(List<CpTermAndConditionDTO> termsConditionYesYes) {
		this.termsConditionYesYes = termsConditionYesYes;
	}

	public boolean isTermsCondition() {
		return termsCondition;
	}

	public void setTermsCondition(boolean termsCondition) {
		this.termsCondition = termsCondition;
	}

	public void setRedirectImpl(RedirectionBLImpl redirectImpl) {
		this.redirectImpl = redirectImpl;
	}

	public List<CpQuestionnaire> getQuestionnaireList() {
		return questionnaireList;
	}

	public void setQuestionnaireList(List<CpQuestionnaire> questionnaireList) {
		this.questionnaireList = questionnaireList;
	}

	public List<CpListBoxAnswers> getListBoxAnswers() {
		return listBoxAnswers;
	}

	public void setListBoxAnswers(List<CpListBoxAnswers> listBoxAnswers) {
		this.listBoxAnswers = listBoxAnswers;
	}

	public List<CpListBoxAnswers> getSelectedRecord() {
		return selectedRecord;
	}

	public void setSelectedRecord(List<CpListBoxAnswers> selectedRecord) {
		this.selectedRecord = selectedRecord;
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

	
}
