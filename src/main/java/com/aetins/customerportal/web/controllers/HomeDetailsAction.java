package com.aetins.customerportal.web.controllers;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.primefaces.PrimeFaces;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.polar.PolarAreaChartDataSet;
import org.primefaces.model.charts.polar.PolarAreaChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;

import com.aetins.customerportal.core.faceletstaglib.SecurityLibrary;
import com.aetins.customerportal.core.primefaces.view.GuestPreferences;
import com.aetins.customerportal.web.common.Constants;
import com.aetins.customerportal.web.common.LoginSession;
import com.aetins.customerportal.web.dao.ContributionHolidayDAO;
import com.aetins.customerportal.web.dao.CpUserInfoDAO;
import com.aetins.customerportal.web.dto.ClaimsDetailsDTO;
import com.aetins.customerportal.web.dto.CpCustomerDetailDTO;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.dto.CpUserSecurityQuestionDTO;
import com.aetins.customerportal.web.dto.CustomerOutstandingDTO;
import com.aetins.customerportal.web.dto.CustomerPaymentsDetailsDTO;
import com.aetins.customerportal.web.dto.DepositDTO;
import com.aetins.customerportal.web.dto.DocumentDTO;
import com.aetins.customerportal.web.dto.FundDetailsDTO;
import com.aetins.customerportal.web.dto.FundDetailsSearchDTO;
import com.aetins.customerportal.web.dto.FundsDto;
import com.aetins.customerportal.web.dto.LifeAssuredDTO;
import com.aetins.customerportal.web.dto.MasterListDTO;
import com.aetins.customerportal.web.dto.NomineeDetailsDTO;
import com.aetins.customerportal.web.dto.PolicyBenefitDTO;
import com.aetins.customerportal.web.dto.PolicyDetailsDTO;
import com.aetins.customerportal.web.dto.PremiumDefermentDTO;
import com.aetins.customerportal.web.dto.ReceiptSummaryDTO;
import com.aetins.customerportal.web.dto.SearchPolicyDTO;
import com.aetins.customerportal.web.dto.ServiceRequestMasterDTO;
import com.aetins.customerportal.web.dto.UserDTO;
import com.aetins.customerportal.web.entity.CPServiceRequestDocuments;
import com.aetins.customerportal.web.entity.CpListBoxAnswers;
import com.aetins.customerportal.web.entity.CpQuestionnaire;
import com.aetins.customerportal.web.entity.CpSessionSummary;
import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.entity.CpUserSecurityQuestion;
import com.aetins.customerportal.web.service.CPServiceRequestDocumentsBL;
import com.aetins.customerportal.web.service.CpUserSecurityQuestionBL;
import com.aetins.customerportal.web.service.CustomerLoginBL;
import com.aetins.customerportal.web.service.CustomerPortalServices;
import com.aetins.customerportal.web.service.impl.AdminBLImpl;
import com.aetins.customerportal.web.service.impl.CustomerPortalServicesImpl;
import com.aetins.customerportal.web.utils.AppSettingURL;
import com.aetins.customerportal.web.utils.BSLUtils;
import com.aetins.customerportal.web.utils.DateUtil;
import com.aetins.customerportal.web.utils.FileUtils;
import com.aetins.customerportal.web.utils.JSONUtils;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import com.mysql.jdbc.Statement;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.OraclePreparedStatement;

@Controller
@Scope("session")
public class HomeDetailsAction extends BaseAction {
	
	private static final Logger logger = Logger.getLogger(HomeDetailsAction.class);
	
	@Autowired
	CustomerPortalServices customerPortalServices;
	
	@Autowired
	CustomerLoginBL loginDetails;
	
	@Autowired
	ContributionHolidayDAO holidayDAo;
	
	@Autowired
	LoginSession loginSession;
	
	@Autowired
	AdminBLImpl adminImpl;
	
	@Autowired
	HttpServletRequest httpRequest;
	
	@Autowired
	HttpServletResponse httpResponse;
	
	@Autowired
	GuestPreferences guest;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	private List<PolicyDetailsDTO> policyDetailsList;
	private List<FundDetailsDTO> fundDetailsList;
	private List<CustomerOutstandingDTO> outstandingDetailsList;
	private List<ServiceRequestMasterDTO> serviceRequestMasterDTOList = null;
	
	private List<ClaimsDetailsDTO> claimDetailsList = new ArrayList<ClaimsDetailsDTO>();
	
	private ClaimsDetailsDTO claimDetailsList1 = new ClaimsDetailsDTO();
	
	private List<DepositDTO> getMyDepositDTOList;
	private List<LifeAssuredDTO> lifeassuredDetailsList;
	private List<NomineeDetailsDTO> nomineeDetailsList;
	private List<FundDetailsSearchDTO> fundDetailsSearchList;
	private List<PolicyBenefitDTO> policyBenefitList;
	private String lang;
	private String userid;
	private SearchPolicyDTO searchPolicyList;
	public String searchPolicyNo;
	private List<CustomerPaymentsDetailsDTO> paymentsDetailsList;
	private Map<String, String> policyListMap;
	private Map<String, String> fundPolicyListMap;
	private BigDecimal totPaymentReceipts = BigDecimal.ZERO;
	private BigDecimal totalPaymentAmt = BigDecimal.ZERO;
	private String selectPolicyNo;
	private String policyListMapErr;
	List<CpUserInfoDTO> cpUserList = new ArrayList<CpUserInfoDTO>();
	
	private String selectMaster;
	private String selectMasterErr;
	private List<ClaimsDetailsDTO> selectedClaimDetailsList = new ArrayList<ClaimsDetailsDTO>();
	private ClaimsDetailsDTO selectedclaimsDetailsDTO = new ClaimsDetailsDTO();
	private List<MasterListDTO> masterList = new ArrayList<MasterListDTO>();
	private List<MasterListDTO> baseCurrencyCode = new ArrayList<MasterListDTO>();
	private List<ServiceRequestMasterDTO> filteredServiceRequestMasteList = new ArrayList<ServiceRequestMasterDTO>();
	private String baseCurrencyCodeStr;
	private String emptyMessageForSur;
	private String totalPaymentAmtStr;
	private String totalContributionAmtStr;
	String toalAmountImPlanCurr;
	String planValue;
	private List<PolicyDetailsDTO> filterPolicyDetailsList;
	private List<FundsDto> filterFundsDetailsList;
	private List<CustomerOutstandingDTO> filterOutstandingDetailsList;
	private List<DepositDTO> filterGetMyDepositDTOList;
	private List<ClaimsDetailsDTO> filterClaimDetailsList;
	private List<FundsDto> fundsDetailsList;
	private int totalFunds = 0;
	private BigDecimal totalFundsAmt = BigDecimal.ZERO;
	private int totOutstandingReceipt = 0;
	private BigDecimal totalOutstandingAmt = BigDecimal.ZERO;
	ReceiptSummaryDTO receiptSummaryDTO = new ReceiptSummaryDTO();
	private int totalPendingClaims = 0;
	Map<String, PolicyDetailsDTO> policyListStatus = new LinkedHashMap<String, PolicyDetailsDTO>();
	private List<String> policyStringList;
	private StreamedContent file;
	
	static String fileName = "download.pdf";
	
	//Need to check the functionality 
	
	private String updateContactFlag = AppSettingURL.UPDATE_CONTACT_FLAG;
	private String contributionHolidayFlag = AppSettingURL.CONTRIBUTION_HOLIDAY_FLAG;
	private String redirectionFlag = AppSettingURL.REDIRECTION_SW_FLAG;
	private String contributionAltFlag = AppSettingURL.CONTRIBUTION_ALT;
	private String reinstatementFlag = AppSettingURL.REINSTATEMENT_FLAG;
	private String protectionBenefitFlag = AppSettingURL.PROTECTION_BENEFIT_FLAG;
	 
	private String lastLoginDateTime;
	private String currentDateTime;
	
	private String serviceType;
	private List<CpQuestionnaire> questionnaireList;	
	private List<CpListBoxAnswers> listBoxAnswers;
	
	private String applicationdisplayname;
	
	
	private String checkOutId;
	
	@PostConstruct
	public void init() {

		try {
			
			CpUserInfoDTO cpUserInfo = new CpUserInfoDTO();
			Iterator<CpUserInfoDTO> it = cpUserList.iterator();
			while (it.hasNext()) {
				cpUserInfo = it.next();
			}
			if (BSLUtils.isNotNull(SecurityLibrary.getFullLoggedInUser().getVactive())&&SecurityLibrary.getFullLoggedInUser().getVactive().equalsIgnoreCase(Constants.CHANGE_STATUS)) {
				//RequestContext requestContext = RequestContext.getCurrentInstance();
				PrimeFaces.current().executeScript("jQuery('#services-1').modal('show');");
			}
			serviceRequestMasterDTOList = new ArrayList();
			
			resetsecuirtyfetch();
			
			/*
			 * if (cpUserList.size() != 0){ userid = cpUserList.get(0).getVuserName(); }
			 */
			userid = SecurityLibrary.getFullLoggedInUser().getVuserName();
			
			totalPendingClaims = 0;
			policyDetailsList = new ArrayList<PolicyDetailsDTO>();
			fundDetailsList = new ArrayList<FundDetailsDTO>();
			fundsDetailsList = new ArrayList<>();
			outstandingDetailsList = new ArrayList<CustomerOutstandingDTO>();
			claimDetailsList = new ArrayList<ClaimsDetailsDTO>();
			
			masterList = customerPortalServices.getMasterLov(Constants.EN, Constants.CLSTATUS);
			selectMaster = masterList.get(0).getDesc1();

			logger.info("base currency call entering time:@@@@@@@:" + (new Date()).getMinutes() + ":"+ (new Date()).getSeconds());
			
			baseCurrencyCode = customerPortalServices.getMasterLov(Constants.EN, Constants.BASECURRENCY);
			
			logger.info("base currency call ending time:@@@@@@@:" + (new Date()).getMinutes() + ":"+ (new Date()).getSeconds());
			
			if (BSLUtils.isNotNull(baseCurrencyCode) && !baseCurrencyCode.isEmpty()) {
				baseCurrencyCodeStr = baseCurrencyCode.get(0).getCode();
			} else {
				baseCurrencyCodeStr = "";
			}
			logger.info("policy list call entering time:@@@@@@@:" + (new Date()).getMinutes() + ":"+ (new Date()).getSeconds());
			
			policyDetailsList = customerPortalServices.getPolicyLists(BigDecimal.valueOf(SecurityLibrary.getFullLoggedInUser().getNcustRefNo()));
            for(int i=0;i<policyDetailsList.size();i++) {
            	policyDetailsList.get(i).setPremiumDueDate(policyDetailsList.get(i).getPremiumDueDate()!=null?datchan(policyDetailsList.get(i).getPremiumDueDate()):null);
            }
            
			
			
			logger.info("policy list call entering time:@@@@@@@:" + (new Date()).getMinutes() + ":"+ (new Date()).getSeconds());
			
			policyListMap = new LinkedHashMap<String, String>();
			for (PolicyDetailsDTO policy : policyDetailsList) {
				/*
				 * if(!policy.getPolicyStatus().equalsIgnoreCase("Lapse")) {
				 * policyListMap.put(policy.getPolicyNo(), policy.getPolicyNo()); }
				 */
				policyListMap.put(policy.getPolicyNo(), policy.getPolicyNo());
			}
			
			fundPolicyListMap = new LinkedHashMap<String, String>();
			for (PolicyDetailsDTO policy : policyDetailsList) {
				  if(!policy.getPolicyStatus().equalsIgnoreCase("Lapse") || !policy.getPolicyStatus().equalsIgnoreCase("PAIDUP")) {
					  fundPolicyListMap.put(policy.getPolicyNo(), policy.getPolicyNo()); 
					  }
			}

			Iterator<PolicyDetailsDTO> itpolicy = policyDetailsList.iterator();
			while (itpolicy.hasNext()) {
				PolicyDetailsDTO policyDetailsDTO = itpolicy.next();
				policyListStatus.put(policyDetailsDTO.getPolicyNo(), policyDetailsDTO);
			}
			
			displayDateTime();
			getCurrentDate();
			//getMyFunds();
			//Submit new request screen
			submitNewRequestRendering();
			//createPolarAreaModel();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception caught HomeDetailsAction with cause: "+e.getCause());
		}
		// UAT issues for select one
		if (BSLUtils.isNotNull(policyDetailsList) && policyDetailsList.size() > 0) {
			selectPolicyNo = policyDetailsList.get(0).getPolicyNo();
		}
	}

	public String getCheckOutId() {
		return checkOutId;
	}

	public void setCheckOutId(String checkOutId) {
		this.checkOutId = checkOutId;
	}

	
	private String selectPolForPremiumDefer;
	private Date deferStartDate;
	private String deferPeriod;
	private String deferRemarks;
	private boolean isDefermentEnable;
	private List<PremiumDefermentDTO> fectchPremiumDefermentsByPolicy;
	private boolean disabled;
	
	
	
	
	public String getSelectPolForPremiumDefer() {
		return selectPolForPremiumDefer;
	}

	public void setSelectPolForPremiumDefer(String selectPolForPremiumDefer) {
		this.selectPolForPremiumDefer = selectPolForPremiumDefer;
	}

	public boolean isDefermentEnable() {
		return isDefermentEnable;
	}

	public void setDefermentEnable(boolean isDefermentEnable) {
		this.isDefermentEnable = isDefermentEnable;
	}

	public Date getDeferStartDate() {
		return deferStartDate;
	}

	public void setDeferStartDate(Date deferStartDate) {
		this.deferStartDate = deferStartDate;
	}

	public String getDeferPeriod() {
		return deferPeriod;
	}

	public void setDeferPeriod(String deferPeriod) {
		this.deferPeriod = deferPeriod;
	}

	public String getDeferRemarks() {
		return deferRemarks;
	}

	public void setDeferRemarks(String deferRemarks) {
		this.deferRemarks = deferRemarks;
	}
	
	
	
	

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public List<PremiumDefermentDTO> getFectchPremiumDefermentsByPolicy() {
		return fectchPremiumDefermentsByPolicy;
	}

	public void setFectchPremiumDefermentsByPolicy(List<PremiumDefermentDTO> fectchPremiumDefermentsByPolicy) {
		this.fectchPremiumDefermentsByPolicy = fectchPremiumDefermentsByPolicy;
	}

	public String redirectingpremium() throws SQLException {
		selectPolForPremiumDefer = "A";
		disabled = false;
		fectchPremiumDefermentsByPolicy = null;
		return "/user/PolicyPremiumDeferment?faces-redirect=true";
	}
	
	
	
	/**
	 * 
	 * @author avinash
	 * @throws SQLException
	 */
	public void getPolicyPremiumDeferment() throws SQLException {

		if (!selectPolForPremiumDefer.equalsIgnoreCase("A") && policyDetailsList != null ) {
			deferStartDate = null;
			deferPeriod = "";
			deferRemarks = "";
			searchPolicyList = new SearchPolicyDTO();
			fectchPremiumDefermentsByPolicy = null;
			fectchPremiumDefermentsByPolicy = new ArrayList<PremiumDefermentDTO>();
	
			List<PremiumDefermentDTO> fectchPremiumDefermentsByPolicy1 = null;
			fectchPremiumDefermentsByPolicy1 = new ArrayList<PremiumDefermentDTO>();
			
			//get policy details by policy number 
			searchPolicyList = customerPortalServices.getSearchPolicyDetails(selectPolForPremiumDefer, "EN",seqno);
			
			//Verify whether request is new 
			fectchPremiumDefermentsByPolicy1 = fectchPremiumDefermentsByPolicy(selectPolForPremiumDefer);

			if(fectchPremiumDefermentsByPolicy1.size()>=1) {
				for(PremiumDefermentDTO obj : fectchPremiumDefermentsByPolicy1) {
					if(obj.getvPolicyNo().equalsIgnoreCase(selectPolForPremiumDefer)) {
						PremiumDefermentDTO dto = new PremiumDefermentDTO();	
						
						disabled = true;
						
						dto.setvPolicyNo(selectPolForPremiumDefer);
						dto.setPolicyStatus(searchPolicyList.getPlanStatus());
						dto.setNewTransaction(Boolean.FALSE);
						dto.setvRecordStatus(Constants.getDefermentLOV().get(obj.getvRecordStatus()));
						dto.setvReqStatus(Constants.getDefermentLOV().get(obj.getvReqStatus()));
						
						
						
						if(searchPolicyList.getCommencementDate()!=null) {
							Calendar dd = datchan(searchPolicyList.getCommencementDate());
							dto.setCommencementDate(dd.getTime());
						}
						if(searchPolicyList.getPremiumDueDate()!=null) {
							Calendar dd = datchan(searchPolicyList.getPremiumDueDate());
							dto.setPremiumDueDate(dd.getTime());
						}
						if(obj.getDeferStartDate()!=null) {
							dto.setDeferStartDate(obj.getDeferStartDate());
						}
						dto.setDeferPeriod(obj.getDeferPeriod());
						dto.setvRemarks(obj.getvRemarks());
						
					    //outstanding date clarification
						//deferStartDate = obj.getDeferStartDate();
						//deferPeriod = obj.getDeferPeriod();
						//deferRemarks = obj.getvRemarks();
						isDefermentEnable = obj.isNewTransaction() ?false:true;
						fectchPremiumDefermentsByPolicy.add(dto);
					}
				}				
			}else {
				for (PolicyDetailsDTO policy : policyDetailsList) {
					if (policy.getPolicyNo().equals(selectPolForPremiumDefer)) {
						
						disabled = false;
						
						PremiumDefermentDTO newPremium = new PremiumDefermentDTO();
						newPremium.setvIdenCode(null);
						newPremium.setvIdenNo(null);
						newPremium.setvPolicyNo(searchPolicyList.getPlanNumber());
						newPremium.setPolicyStatus(searchPolicyList.getPlanStatus());
						if(searchPolicyList.getCommencementDate()!=null) {
							Calendar dd = datchan(searchPolicyList.getCommencementDate());
							newPremium.setCommencementDate(dd.getTime());
						}
						if(searchPolicyList.getPremiumDueDate()!=null) {
							Calendar dd = datchan(searchPolicyList.getPremiumDueDate());
							newPremium.setPremiumDueDate(dd.getTime());
						}
						newPremium.setNewTransaction(Boolean.TRUE);
						fectchPremiumDefermentsByPolicy.add(newPremium);
						break;
					}
				}
			}
		}
	}
	
	

	/**
	 * @param policyNumber
	 * @return
	 * @throws SQLException
	 */
	public List<PremiumDefermentDTO> fectchPremiumDefermentsByPolicy(String policyNumber) throws SQLException {
		List<PremiumDefermentDTO> listing = null;
		listing	= new ArrayList<PremiumDefermentDTO>();
		OraclePreparedStatement pstmt = null;
		OracleConnection conn = null;
		try {
			Class.forName(AppSettingURL.DB_Driver);
			String URL = AppSettingURL.DB_URL;
			String USER = AppSettingURL.DB_USERNAME;
			String PASS = AppSettingURL.DB_PASSWORD;
			conn = (OracleConnection) DriverManager.getConnection(URL, USER, PASS);
            if(conn!=null) {
            	logger.info("DB CONNECTED");
            }else {
            	logger.info("DB NOT CONNECTED");
            }
			String sql = Constants.QUERY_FETCH_POLICY_DEFERMENT_BY_POLICY_NO;
			pstmt = (OraclePreparedStatement) conn.prepareStatement(sql);  
			pstmt.setString(1, policyNumber);
			ResultSet executeQuery = pstmt.executeQuery();
			while (executeQuery.next()) {
				PremiumDefermentDTO premium = new PremiumDefermentDTO();
				premium.setTransNo(executeQuery.getInt(Constants._TRANS_NO));
				premium.setvIdenCode(executeQuery.getString(Constants._IDEN_CODE));
				premium.setvIdenNo(executeQuery.getString(Constants._IDEN_NO));
				premium.setvReqStatus(executeQuery.getString(Constants._REQUEST_STATUS));
				premium.setvRecordStatus(executeQuery.getString(Constants._RECORD_STATUS));
				premium.setvPolicyNo(executeQuery.getString(Constants._POLICY_NO));
				premium.setDeferStartDate(executeQuery.getDate(Constants._DEFERMENT_START_DATE));
				premium.setvRemarks(executeQuery.getString(Constants._DEFERMENT_REMARKS));
				premium.setDeferPeriod(executeQuery.getString(Constants._DEFERMENT_PERIOD));
				listing.add(premium);
			}
			logger.info("Find policydeferment by policy no :" +listing);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception caught while fetching policyDeferment :"+e.getCause());
		}
		return listing;
	}
	
	
	/**
	 * To crater action binding 
	 * @author avinash
	 * @throws SQLException
	 */
	public void savePremiumDeferment() throws SQLException {
		try {		
			if(fectchPremiumDefermentsByPolicy.size()>=1) {
				for(PremiumDefermentDTO obj : fectchPremiumDefermentsByPolicy) {
					obj.setvRecordStatus("U");
					obj.setvReqStatus("I");
					saveDefer(obj);
					disabled = true;
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
							"Data Processed Successfully");
					PrimeFaces.current().dialog().showMessageDynamic(message);
				}
			}else {
				disabled = false;
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Data Process Failed. Please try again later or contac Salama Customer Support Team");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
	
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}

	/**
	 * To save policy deferment in portal schema
	 * @author avinash
	 * @param premium
	 * @throws SQLException
	 */
	public void saveDefer(PremiumDefermentDTO premium) throws SQLException {
		OraclePreparedStatement pstmt = null;
		OracleConnection conn = null;
		try {
			Class.forName(AppSettingURL.DB_Driver);
			String URL = AppSettingURL.DB_URL;
			String USER = AppSettingURL.DB_USERNAME;
			String PASS = AppSettingURL.DB_PASSWORD;
			conn = (OracleConnection) DriverManager.getConnection(URL, USER, PASS);
			if(conn!=null) {
            	logger.info("DB CONNECTED");
            }else {
            	logger.info("DB NOT CONNECTED");
            }
			String sql = Constants.QUERY_INSERT_POLICY_DEFERMENT;
			pstmt = (OraclePreparedStatement) conn.prepareStatement(sql);
			//Get latest transaction number for maintaining sequence 
			int latestTransNo = fectchLatestPolicyDefermentTransNo();
			pstmt.setInt(1, latestTransNo+1);
			pstmt.setString(2, premium.getvPolicyNo());
			pstmt.setString(3, null);
			pstmt.setString(4, null);
			pstmt.setDate(5, new java.sql.Date(premium.getDeferStartDate().getTime()));
			//pstmt.setDate(5, (java.sql.Date) premium.getDeferStartDate());
			pstmt.setString(6, premium.getDeferPeriod());
			pstmt.setString(7, premium.getvRecordStatus());
			pstmt.setString(8, premium.getvReqStatus());
			pstmt.setString(9, premium.getvRemarks());
			int executeQuery = pstmt.executeUpdate();
			logger.info("New PolicyDeferMent persisted successfully : " +executeQuery);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error caught while persisting policydeferment : "+e.getCause());
		}
	}

	
	/**
	 * To fetch latest policy deferment transaction no
	 * @author avinash
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public int fectchLatestPolicyDefermentTransNo() throws SQLException, ClassNotFoundException{
		OraclePreparedStatement pstmt = null;
		OracleConnection conn = null;
		int transNo=0;
		try {
			Class.forName(AppSettingURL.DB_Driver);
			String URL = AppSettingURL.DB_URL;
			String USER = AppSettingURL.DB_USERNAME;
			String PASS = AppSettingURL.DB_PASSWORD;
			conn = (OracleConnection) DriverManager.getConnection(URL, USER, PASS);
			if(conn!=null) {
            	logger.info("DB CONNECTED");
            }else {
            	logger.info("DB NOT CONNECTED");
            }
			String sql = Constants.QUERY_FETCH_LATEST_TRANS_NO;
			pstmt = (OraclePreparedStatement) conn.prepareStatement(sql);
			ResultSet executeQuery = pstmt.executeQuery();
			while(executeQuery.next()) {
				transNo = executeQuery.getInt(Constants._TRANS_NO);
			}
			logger.info("Latest Transaction Number from WNMT_DEFER_PREMIUM_REQUEST = "+transNo);
			return transNo;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return transNo;
	}
	
	CPServiceRequestDocuments cPServiceRequestDocuments;
    @Autowired
    CPServiceRequestDocumentsBL cPServiceRequestDocumentsBL;
    
	
	public StreamedContent custDownload(ServiceRequestMasterDTO objectrequest){
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
	
	

	@Autowired
	CpUserSecurityQuestionBL cpUserSecurityQuestionBL;
	private CpUserSecurityQuestion cpUserSecurityQuestion;
	private List<CpUserSecurityQuestion> cpUserSecurityQuestionSQL;
	private List<CpUserSecurityQuestionDTO> cpUserSecurityQuestionDTO; 
	private CpUserSecurityQuestionDTO cpUserSecurityQuestidto;
	
	
	
	
	@Autowired
	CustomerLoginBL customDetails;
	List<CpUserInfoDTO> cpUserListsecques;
	private CpUserInfoDTO cpUserInfoDTOoo;
	@Autowired
	CustomerLoginBL customerLoginBLImpl;
    private boolean old;
    private boolean latest;
   
	public CpUserInfoDTO getCpUserInfoDTOoo() {
		return cpUserInfoDTOoo;
	}
	public void setCpUserInfoDTOoo(CpUserInfoDTO cpUserInfoDTOoo) {
		this.cpUserInfoDTOoo = cpUserInfoDTOoo;
	}
	public boolean isOld() {
		return old;
	}
	public void setOld(boolean old) {
		this.old = old;
	}
	public boolean isLatest() {
		return latest;
	}
	public void setLatest(boolean latest) {
		this.latest = latest;
	}

	public void resetsecuirtyfetch() {
		cpUserSecurityQuestionSQL = new ArrayList<CpUserSecurityQuestion>();
		cpUserSecurityQuestionDTO = new ArrayList<CpUserSecurityQuestionDTO>();
		cpUserSecurityQuestion = new CpUserSecurityQuestion();
		cpUserSecurityQuestion.setUserid(SecurityLibrary.getFullLoggedInUser().getVuserName());
		cpUserSecurityQuestion.setRefno(SecurityLibrary.getFullLoggedInUser().getNcustRefNo());
		cpUserSecurityQuestionSQL = cpUserSecurityQuestionBL.fetching(cpUserSecurityQuestion);		
		
		if(cpUserSecurityQuestionSQL.size()>0){
			old=true;
			latest=false;
			for(int i=0;i<cpUserSecurityQuestionSQL.size();i++) {
			   cpUserSecurityQuestidto = new CpUserSecurityQuestionDTO();
			   cpUserSecurityQuestidto.setNid(cpUserSecurityQuestionSQL.get(i).getNid());
			   cpUserSecurityQuestidto.setUserid(cpUserSecurityQuestionSQL.get(i).getUserid());
			   cpUserSecurityQuestidto.setRefno(cpUserSecurityQuestionSQL.get(i).getRefno());
			   cpUserSecurityQuestidto.setSecques(cpUserSecurityQuestionSQL.get(i).getSecques());
			   cpUserSecurityQuestidto.setSecans(cpUserSecurityQuestionSQL.get(i).getSecans());
			   cpUserSecurityQuestidto.setSecstatus(cpUserSecurityQuestionSQL.get(i).getSecstatus());
			   cpUserSecurityQuestidto.setProcesseddate(cpUserSecurityQuestionSQL.get(i).getProcesseddate());
			   cpUserSecurityQuestidto.setRecmodifiesans(cpUserSecurityQuestionSQL.get(i).getRecmodifiesans());
			   cpUserSecurityQuestidto.setUserotp(cpUserSecurityQuestionSQL.get(i).getUserotp());
			   cpUserSecurityQuestionDTO.add(cpUserSecurityQuestidto);
		   }
		}else {
			old=false;
			latest=true;
			cpUserInfoDTOoo = new CpUserInfoDTO();
			cpUserListsecques = new ArrayList<CpUserInfoDTO>();
			UserDTO userDTO = new UserDTO();
			userDTO.setUserName(SecurityLibrary.getFullLoggedInUser().getVuserName());
			cpUserListsecques = customDetails.fetchUserDetails(userDTO);
			Iterator<CpUserInfoDTO> it = cpUserListsecques.iterator();
			while (it.hasNext()) {
				CpCustomerDetailDTO cpCustomerDetailDTO = new CpCustomerDetailDTO();
				cpUserInfoDTOoo = it.next();
				cpUserInfoDTOoo.getVprefName();
				cpUserInfoDTOoo.getVcontactNo();
				cpUserInfoDTOoo.getVtitle();
				cpUserInfoDTOoo.getVuserName();
				cpUserInfoDTOoo.getVemail();
				cpUserInfoDTOoo.getDdob();
				cpUserInfoDTOoo.getRoles();
			}
		}
	}
	
	public void resetsecuirtyupdate() {
		if(cpUserSecurityQuestionDTO.size()>0) {
			for(int i=0;i<cpUserSecurityQuestionDTO.size();i++) {
				cpUserSecurityQuestionSQL.get(i).setSecans(cpUserSecurityQuestionDTO.get(i).getSecans());
			}
			boolean status = cpUserSecurityQuestionBL.updating(cpUserSecurityQuestionSQL);
			if(status=true) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
						"Data Processed Successfully.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
						"Data Processed Un-Successfully.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		}
	}
	
	
	public void updatesecques() {	
		boolean status = customerLoginBLImpl.updateUserDetails(cpUserInfoDTOoo);
		if(status=true) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
					"Data Processed Successfully.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
					"Data Processed Un-Successfully.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
	}
	
	public void resetsecuirtydelete(CpUserSecurityQuestionDTO obj) {
		if(obj!=null) {
			for(int i=0;i<cpUserSecurityQuestionDTO.size();i++) {
			 if(cpUserSecurityQuestionDTO.get(i).getNid() == obj.getNid()) {
				 cpUserSecurityQuestion.setNid(cpUserSecurityQuestionDTO.get(i).getNid());
				 cpUserSecurityQuestionDTO.remove(i);
				 cpUserSecurityQuestionSQL.remove(i);
				 
			 }
		  }
			boolean status = cpUserSecurityQuestionBL.delete(cpUserSecurityQuestion);
			if(status=true) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
						"Data Processed Successfully.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
						"Data Processed Un-Successfully.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		}
	 }
	
	private CpUserSecurityQuestionDTO insert = new CpUserSecurityQuestionDTO();
	
	public void resetsecuirtyinsert() {
		boolean status; 
		cpUserSecurityQuestion.setSecques(insert.getSecques());
		cpUserSecurityQuestion.setSecans(insert.getSecans());
		cpUserSecurityQuestion.setSecstatus("A");
		cpUserSecurityQuestion.setUserid(SecurityLibrary.getFullLoggedInUser().getVuserName());
		cpUserSecurityQuestion.setRefno(SecurityLibrary.getFullLoggedInUser().getNcustRefNo());
		status = cpUserSecurityQuestionBL.inserting(cpUserSecurityQuestion);
		if(status=true) {
			resetsecuirtyfetch();
			insert.setSecans("");
			insert.setSecques("");
			quesans=false;
			addquesbut=true;
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
					"Data Processed Successfully.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}else {
			insert.setSecans("");
			insert.setSecques("");
			quesans=true;
			addquesbut=false;
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
					"Data Processed Un-Successfully.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
	}
	
	//Payment gateway Integration
	public String createCheckOut() {
		
		String id = "";
		
		try {
			
			URL url = new URL("https://test.oppwa.com/v1/checkouts");

			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			    conn.setRequestProperty("Authorization", "Bearer OGE4Mjk0MTc0YjdlY2IyODAxNGI5Njk5MjIwMDE1Y2N8c3k2S0pzVDg=");
			conn.setDoInput(true);
			conn.setDoOutput(true);

			String data = ""
				+ "entityId=8a8294174b7ecb28014b9699220015ca"
				+ "&amount=92.00"
				+ "&currency=EUR"
				+ "&paymentType=DB"
				+ "&registrations[0].id=8ac7a49f736ac68a01736f8280ae1df9"
				+ "&registrations[1].id=8ac7a4a0736acff601736f8282af01dc"
				+ "&createRegistration=true";

			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.writeBytes(data);
			wr.flush();
			wr.close();
			int responseCode = conn.getResponseCode();
			InputStream is;

			if (responseCode >= 400) 
				is = conn.getErrorStream();
			else 
				is = conn.getInputStream();
			
			String string = IOUtils.toString(is);
			System.out.println(string);
			String jsonString = JSONUtils.getJSONString(string, "id");
			System.out.println(jsonString);
			
			conn.disconnect();
			return jsonString;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id; 
	}
	
	/**************** HYPER PAY ******************/
	/*********** ADDED URL BY : ARUL *************/
	/*************** DATE : 23/7/2020 ************/
	/**** TO CALL PAYMENT CARD DETAILS SCREEN 
	 * @throws JSONException ****/
	public void redirectToPaymentPage() throws JSONException {
		try {
			checkOutId = AppSettingURL.HYPER_PAY + createCheckOut();
			logger.info("Check Out Payment Form URL : "+ checkOutId);
			String id = checkOutId.substring(checkOutId.indexOf("=")+1);
			setReturnURL(AppSettingURL.RETURN_URL + id + "/payment");
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().redirect(SecurityLibrary.getAppContextPath() + "/payment.app");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String returnURL;
	private String responseMessage;
	public void getPaymentStatus() throws IOException, JSONException{
		logger.info("Getting Payment Status");
		logger.info("Response URL : "+ returnURL);
		URL url = new URL(returnURL);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		/*
		 * conn.setRequestMethod("GET"); conn.setRequestProperty("Authorization",
		 * "Bearer OGE4Mjk0MTc0YjdlY2IyODAxNGI5Njk5MjIwMDE1Y2N8c3k2S0pzVDg=");
		 */
		
		String response = IOUtils.toString(conn.getInputStream());
		logger.info("Response Status : "+ response);
	    JSONObject job = new JSONObject(response);
	    String result = job.getString("result");
	    JSONObject resultCode = new JSONObject(result);
	    String code = resultCode.getString("code");
	    logger.info("Code : "+ code +", Description : "+ resultCode.getString("description"));
	    if (code.equals("000.100.110") || code.equals("000.000.000") || code.equals("000.000.100")) {
	    	responseMessage = "Payment Succes";
	    } else {
	    	responseMessage = resultCode.getString("description");
	    }
	}
	
	private boolean quesans=false;
	private boolean addquesbut=true;
	
	public void addques() {
		quesans=true;
		addquesbut=false;
	}
	
	public void closeques() {
		quesans=false;
		addquesbut=true;
		insert.setSecans("");
		insert.setSecques("");
	}
	
	
private String drag;
	
	public void sampldrafe() {
		if(drag.equalsIgnoreCase("A")) {
			PrimeFaces.current().executeScript("PF('dlg31').show()");
			PrimeFaces.current().executeScript("functionotpmsg1()");
		}else {
			PrimeFaces.current().executeScript("PF('dlg31').show()");
			PrimeFaces.current().executeScript("functionotpmsg2()");
		}
	}
	
	public String ss() {
		return "/user/userDashboard?faces-redirect=true"; 
	}

	public String getDrag() {
		return drag;
	}

	public void setDrag(String drag) {
		this.drag = drag;
	}
	
	
	public CpUserSecurityQuestion getCpUserSecurityQuestion() {
		return cpUserSecurityQuestion;
	}

	public void setCpUserSecurityQuestion(CpUserSecurityQuestion cpUserSecurityQuestion) {
		this.cpUserSecurityQuestion = cpUserSecurityQuestion;
	}

	public List<CpUserSecurityQuestion> getCpUserSecurityQuestionSQL() {
		return cpUserSecurityQuestionSQL;
	}

	public void setCpUserSecurityQuestionSQL(List<CpUserSecurityQuestion> cpUserSecurityQuestionSQL) {
		this.cpUserSecurityQuestionSQL = cpUserSecurityQuestionSQL;
	}

	public List<CpUserSecurityQuestionDTO> getCpUserSecurityQuestionDTO() {
		return cpUserSecurityQuestionDTO;
	}

	public void setCpUserSecurityQuestionDTO(List<CpUserSecurityQuestionDTO> cpUserSecurityQuestionDTO) {
		this.cpUserSecurityQuestionDTO = cpUserSecurityQuestionDTO;
	}

	public CpUserSecurityQuestionDTO getCpUserSecurityQuestidto() {
		return cpUserSecurityQuestidto;
	}

	public void setCpUserSecurityQuestidto(CpUserSecurityQuestionDTO cpUserSecurityQuestidto) {
		this.cpUserSecurityQuestidto = cpUserSecurityQuestidto;
	}

	public CpUserSecurityQuestionDTO getInsert() {
		return insert;
	}

	public void setInsert(CpUserSecurityQuestionDTO insert) {
		this.insert = insert;
	}

	public boolean isQuesans() {
		return quesans;
	}

	public void setQuesans(boolean quesans) {
		this.quesans = quesans;
	}

	public boolean isAddquesbut() {
		return addquesbut;
	}

	public void setAddquesbut(boolean addquesbut) {
		this.addquesbut = addquesbut;
	}

	public String servicelist() {
		serviceRequestMasterDTOList = null;
		serviceRequestMasterDTOList = new ArrayList<ServiceRequestMasterDTO>();
		serviceRequestMasterDTOList = loginDetails.fetchTransactionRequestList(userid);	
		return "/user/serviceRequest?faces-redirect=true";
	}
	
	private PolarAreaChartModel polarAreaModel;
	private ChartData chartData;
	private int totalrequest;
	private int totalsucc;
	private int totalpenawp;
	private List<ServiceRequestMasterDTO> serviceRequestMasterDTOList1; 
	private boolean requestchart=false;
	
	private void createPolarAreaModel() {
		serviceRequestMasterDTOList1 = new ArrayList<ServiceRequestMasterDTO>();
		polarAreaModel = new PolarAreaChartModel();
        ChartData data = new ChartData();
        PolarAreaChartDataSet dataSet = new PolarAreaChartDataSet();       
        serviceRequestMasterDTOList1 = loginDetails.fetchTransactionRequestList(userid);
        if(serviceRequestMasterDTOList1.size()>0) {
        	requestchart=true;
        	totalrequest=serviceRequestMasterDTOList1.size();
        	for(int i=0;i<serviceRequestMasterDTOList1.size();i++) {
        		if(serviceRequestMasterDTOList1.get(i).getRequestStatus().equalsIgnoreCase("COMPLETED")) {
        			totalsucc++;
        		}else {
        			totalpenawp++;
        		}
        	}
        	List<Number> values = new ArrayList<>();
            values.add(totalrequest);
            values.add(totalsucc);
            values.add(totalpenawp);
            dataSet.setData(values);
             
            List<String> bgColors = new ArrayList<>();
            bgColors.add("#4b6eba");
            bgColors.add("#35ae4c");
            bgColors.add("#ce4242");
            dataSet.setBackgroundColor(bgColors);
             
            data.addChartDataSet(dataSet);
            List<String> labels = new ArrayList<>();
            labels.add("Total Request");
            labels.add("Total Request Success");
            labels.add("Total Request Pend/AWAP");
            data.setLabels(labels);
            polarAreaModel.setData(data);
            serviceRequestMasterDTOList1=null;
        }else {
        	requestchart=false;
        }
    } 
	
	public void policyListingPage() throws IOException {
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().redirect(SecurityLibrary.getAppContextPath() + "/user/plans.app");
	}
	
	public PolarAreaChartModel getPolarAreaModel() {
		return polarAreaModel;
	}

	public void setPolarAreaModel(PolarAreaChartModel polarAreaModel) {
		this.polarAreaModel = polarAreaModel;
	}

	public ChartData getChartData() {
		return chartData;
	}

	public void setChartData(ChartData chartData) {
		this.chartData = chartData;
	}

	public int getTotalrequest() {
		return totalrequest;
	}

	public void setTotalrequest(int totalrequest) {
		this.totalrequest = totalrequest;
	}

	public int getTotalsucc() {
		return totalsucc;
	}

	public void setTotalsucc(int totalsucc) {
		this.totalsucc = totalsucc;
	}

	public int getTotalpenawp() {
		return totalpenawp;
	}

	public void setTotalpenawp(int totalpenawp) {
		this.totalpenawp = totalpenawp;
	}

	public List<ServiceRequestMasterDTO> getServiceRequestMasterDTOList1() {
		return serviceRequestMasterDTOList1;
	}

	public void setServiceRequestMasterDTOList1(List<ServiceRequestMasterDTO> serviceRequestMasterDTOList1) {
		this.serviceRequestMasterDTOList1 = serviceRequestMasterDTOList1;
	}

	public boolean isRequestchart() {
		return requestchart;
	}

	public void setRequestchart(boolean requestchart) {
		this.requestchart = requestchart;
	}

	public void transactionService(String policyNo) throws Exception {
		//RequestContext requestContext = RequestContext.getCurrentInstance();
		//requestContext = RequestContext.getCurrentInstance();
		PrimeFaces.current().executeScript("popclose()");
		// successModal();
		PrimeFaces.current().executeScript("$('#OTP-popup').modal('show');");
		getPolicyDetails(policyNo);
	}

	public void closeModal() {

		//RequestContext requestContext = RequestContext.getCurrentInstance();
		PrimeFaces.current().executeScript("$('#OTP-popup').modal('hide');");

	}
	
	
	/**
	 * For displaying last login and current date an time
	 * 
	 * @author Shashi
	 * @since 12/07/2018
	 */
	public void displayDateTime() {
		List<CpSessionSummary> lastLoginDetails = holidayDAo.getLastLoginDetails(SecurityLibrary.getFullLoggedInUser().getVuserName());
		try {
			if (lastLoginDetails.size() > 0) {
				Date lastlogin = lastLoginDetails.get(0).getDsessionEnd();
				SimpleDateFormat format = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");

				String DateToStr = format.format(lastlogin);
				DateFormat df2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

				Date d2 = df2.parse(DateToStr);

				String[] splitLastLogin = d2.toString().split("\\s");

				String secondSplit = splitLastLogin[3];

				String[] splitTime = secondSplit.split(":");

				lastLoginDateTime = splitLastLogin[1] + ", " + splitLastLogin[2] + " " + splitLastLogin[5] + " "
						+ splitTime[0] + ":" + splitTime[1];
				logger.info("Last login date and time is" + lastLoginDateTime);
			}

		} catch (Exception e) {
			logger.info("Inside catch block of " + e.getMessage());
		}

	}

	public void checkas() {
		PrimeFaces.current().executeScript("functionssucc()");
	}
	
	// Added by Uday to read questionnaire details 
	public List<CpQuestionnaire> getQuestionnaire(String serviceType) {
		questionnaireList = adminImpl.getQuestionnaireList(serviceType);
		for (CpQuestionnaire each : questionnaireList) {
			if (each.getAnswerType().equals("L")) {
				listBoxAnswers = new ArrayList<>();
				listBoxAnswers = adminImpl.getAnswerList(each.getServiceId());
				if (each.getAnswer() != null) {
					String[] answerArray = each.getAnswer().split(",");
					for (int i = 0; i < answerArray.length; i++) {
						CpListBoxAnswers answerBean = new CpListBoxAnswers();
						String answer = answerArray[i];
						for (int j = 0; j < listBoxAnswers.size(); j++) {
							if (listBoxAnswers.get(j).getAnswer().equalsIgnoreCase(answer)) {
								listBoxAnswers.get(j).setSelectable(true);
							}
						}
					}
				}
			}
			each.setListBoxAnswers(listBoxAnswers);
		}
		return questionnaireList;
	}

	public void getCurrentDate() {

		Date date = new Date();
		String[] splitCurrentDate = date.toString().split("\\s");
		String secondSplit = splitCurrentDate[3];

		String[] splitTime = secondSplit.split(":");

		currentDateTime = splitCurrentDate[1] + ", " + splitCurrentDate[2] + " " + splitCurrentDate[5] + " "
				+ splitTime[0] + ":" + splitTime[1];
		logger.info("Current date and time is :::::" + currentDateTime);

	}

	// added by Viswakarthick
	public void getMyPayments() {

		totPaymentReceipts = BigDecimal.ZERO;
		totalPaymentAmt = BigDecimal.ZERO;
		try {
			if (selectPolicyNo.equals("")) {
				paymentsDetailsList.clear();
				//RequestContext requestContext = RequestContext.getCurrentInstance();
				PrimeFaces.current().executeScript("document.getElementsByClassName('policyListMapError')[0].style.display=''");
				setPolicyListMapErr("Select Any Policy No");
			} else {
				receiptSummaryDTO = customerPortalServices.getReceiptSummary(selectPolicyNo);
				paymentsDetailsList = customerPortalServices.getCustPayments(selectPolicyNo);
				if (paymentsDetailsList.size() > 0) {

					totalPaymentAmt = BigDecimal.ZERO;

					totalPaymentAmt = receiptSummaryDTO.getReceiptAmt();
					totPaymentReceipts = receiptSummaryDTO.getReceiptCount();
					totalPaymentAmtStr = receiptSummaryDTO.getPolicyCurrency();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * For Merging of mutiple pdfs.
	 * 
	 * @author Vidya
	 * @since 07/03/2016
	 */
	public static void doMerge(String pathPdf1, String pathPdf2) throws Exception {
		logger.info("INSIDE 1st DO Merge method :::::::::::::");
		List<InputStream> list = new ArrayList<InputStream>();
		// Source pdfs
		list.add(new FileInputStream(new File(pathPdf1)));
		list.add(new FileInputStream(new File(pathPdf2)));

		// Resulting pdf

		OutputStream out = new FileOutputStream(new File(AppSettingURL.TRANSACTION_LOCATION + fileName));
		doMerge(list, out);
	}

	public static void doMerge(List<InputStream> list, OutputStream outputStream)
			throws DocumentException, IOException {
		logger.info("INSIDE 2ndst DO Merge method :::::::::::::::");
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, outputStream);
		document.open();
		PdfContentByte cb = writer.getDirectContent();

		for (InputStream in : list) {
			PdfReader reader = new PdfReader(in);
			for (int i = 1; i <= reader.getNumberOfPages(); i++) {
				document.newPage();
				// import the page from source pdf
				PdfImportedPage page = writer.getImportedPage(reader, i);
				// add the page to the destination pdf
				cb.addTemplate(page, 0, 0);
			}
		}
		outputStream.flush();
		document.close();
		outputStream.close();
	}

	/**
	 * For downloading PDF for service transaction
	 * 
	 * @author Shashi
	 * @since 07/03/2018
	 */
	
	private int serviceReqNoToDelete;
	private String fatcaFlag;

	public void generateReport(int serviceReqNo, String serviceType) {
	String reportUrl = null;
	fatcaFlag = null;
	for (ServiceRequestMasterDTO each : serviceRequestMasterDTOList) {

	if (serviceReqNo == each.getServiceRequestNo()) {
	if (BSLUtils.isNotNull(each.getFatcaFlag())) {
	fatcaFlag = each.getFatcaFlag();
	}
	}
	}
	logger.info("Fatca status id :::::::::::::" + fatcaFlag);
	switch (serviceType) {
	case "CONTRIBUTION HOLIDAY":
	reportUrl = AppSettingURL.TRANSACTION_Report_URL + "contributionHolidaysalama.rptdesign&nServiceRequestNo="
	+ serviceReqNo + "&__format=pdf";
	break;
	case "CONTRIBUTION ALTERATION":
	reportUrl = AppSettingURL.TRANSACTION_Report_URL + "contributionAlterationsalama.rptdesign&nServiceRequestNo="
	+ serviceReqNo + "&__format=pdf";
	break;
	case "REINSTATEMENT":
	reportUrl = AppSettingURL.TRANSACTION_Report_URL + "reinstatementsalama.rptdesign&nServiceRequestNo="
	+ serviceReqNo + "&__format=pdf";
	break;
	case "PROTECTION BENEFIT":
	reportUrl = AppSettingURL.TRANSACTION_Report_URL + "protectionBenefitsalama.rptdesign&nServiceRequestNo="
	+ serviceReqNo + "&__format=pdf";
	break;
	case "UPDATE INFORMATION":
	reportUrl = AppSettingURL.TRANSACTION_Report_URL + "updateInformationsalama.rptdesign&nServiceRequestNo="
	+ serviceReqNo + "&__format=pdf";
	break;
	case "REDIRECTION":
	reportUrl = AppSettingURL.TRANSACTION_Report_URL + "redirectionSwitchingsalama.rptdesign&nServiceRequestNo="
	+ serviceReqNo + "&__format=pdf";
	break;
	case "SWITCHING":
	reportUrl = AppSettingURL.TRANSACTION_Report_URL + "redirectionSwitchingsalama.rptdesign&nServiceRequestNo="
	+ serviceReqNo + "&__format=pdf";
	break;
	case "REDIRECTIONANDSWITCHING":
	reportUrl = AppSettingURL.TRANSACTION_Report_URL + "redirectionSwitchingsalama.rptdesign&nServiceRequestNo="
	+ serviceReqNo + "&__format=pdf";
	break;
	}
	logger.info("Report URL is " + reportUrl);
	String reportOutputFileName = "transaction.pdf";

	try {
	FileUtils.saveURLToFile(reportUrl, AppSettingURL.TRANSACTION_LOCATION + reportOutputFileName);
	FileUtils.saveURLToFile(reportUrl, AppSettingURL.TRANSACTION_LOCATION + serviceReqNo + ".pdf");
	if (BSLUtils.isNotNull(fatcaFlag)) {
	if (fatcaFlag.equalsIgnoreCase("Y")) {
	String fatcaURL = AppSettingURL.TRANSACTION_Report_URL + "fatca.rptdesign&nServiceRequestNo="
	+ serviceReqNo + "&__format=pdf";
	logger.info("Fatca URL is :::::::::::::" + fatcaURL);
	FileUtils.saveURLToFile(fatcaURL,
	AppSettingURL.TRANSACTION_LOCATION + serviceReqNo + ".1" + ".pdf");

	logger.info("Fatca URL is " + fatcaURL);

	doMerge(AppSettingURL.TRANSACTION_LOCATION + serviceReqNo + ".pdf",
	AppSettingURL.TRANSACTION_LOCATION + serviceReqNo + ".1" + ".pdf");

	}
	}
	logger.info("Entered into DownLoad ::::::::::::");

	FileInputStream inputstream = new FileInputStream(new File(AppSettingURL.TRANSACTION_LOCATION + serviceReqNo + ".pdf"));
	logger.info(":::::::Transaction No is :::::::::" + serviceReqNo + "Stream value is :::::::" + inputstream);

	if(BSLUtils.isNotNull(inputstream)){
	file = new DefaultStreamedContent(inputstream, "pdf", serviceReqNo + ".pdf");
	}

	serviceReqNoToDelete = serviceReqNo;

	} catch (Exception e) {
	logger.info(":::::::Inside catch block of GeneratePDF::::::" + e.getMessage());
	e.printStackTrace();
	}
	}


	public void fileDelete(){
	
	File fatcaFile = null;
	File transactionFile = new File(AppSettingURL.TRANSACTION_LOCATION + serviceReqNoToDelete + ".pdf");
	if (BSLUtils.isNotNull(transactionFile)){	
	transactionFile.delete();
	}
	if (BSLUtils.isNotNull(fatcaFlag)) {
	if (fatcaFlag.equalsIgnoreCase("Y")) {
	fatcaFile = new File(AppSettingURL.TRANSACTION_LOCATION + serviceReqNoToDelete + ".1" + ".pdf");
	if (BSLUtils.isNotNull(fatcaFile)){	
	fatcaFile.delete();
	}
	}
	}
	logger.info("Status for deleted TXN file is " + transactionFile + "Status for deleted FATCA file is"
	+ fatcaFile);
	}
	
	
	
	
	/*public void generateReport(int serviceReqNo, String serviceType) {
		String reportUrl = null;
		String fatcaFlag = null;
		for (ServiceRequestMasterDTO each : serviceRequestMasterDTOList) {

			if (serviceReqNo == each.getServiceRequestNo()) {
				if (BSLUtils.isNotNull(each.getFatcaFlag())) {
					fatcaFlag = each.getFatcaFlag();
				}
			}
		}
		logger.info("Fatca status id :::::::::::::" + fatcaFlag);
		switch (serviceType) {
		case "CONTRIBUTION HOLIDAY":
			reportUrl = AppSettingURL.TRANSACTION_Report_URL + "contributionHoliday.rptdesign&nServiceRequestNo="
					+ serviceReqNo + "&__format=pdf";
			break;
		case "CONTRIBUTION ALTERATION":
			reportUrl = AppSettingURL.TRANSACTION_Report_URL + "contributionAlteration.rptdesign&nServiceRequestNo="
					+ serviceReqNo + "&__format=pdf";
			break;
		case "REINSTATEMENT":
			reportUrl = AppSettingURL.TRANSACTION_Report_URL + "reinstatement.rptdesign&nServiceRequestNo="
					+ serviceReqNo + "&__format=pdf";
			break;
		case "PROTECTION BENEFIT":
			reportUrl = AppSettingURL.TRANSACTION_Report_URL + "protectionBenefit.rptdesign&nServiceRequestNo="
					+ serviceReqNo + "&__format=pdf";
			break;
		case "UPDATE INFORMATION":
			reportUrl = AppSettingURL.TRANSACTION_Report_URL + "updateInformation.rptdesign&nServiceRequestNo="
					+ serviceReqNo + "&__format=pdf";
			break;
		case "REDIRECTION":
			reportUrl = AppSettingURL.TRANSACTION_Report_URL + "redirectionSwitching.rptdesign&nServiceRequestNo="
					+ serviceReqNo + "&__format=pdf";
			break;
		case "SWITCHING":
			reportUrl = AppSettingURL.TRANSACTION_Report_URL + "redirectionSwitching.rptdesign&nServiceRequestNo="
					+ serviceReqNo + "&__format=pdf";
			break;
		case "REDIRECTIONANDSWITCHING":
			reportUrl = AppSettingURL.TRANSACTION_Report_URL + "redirectionSwitching.rptdesign&nServiceRequestNo="
					+ serviceReqNo + "&__format=pdf";
			break;
		}
		logger.info("Report URL is " + reportUrl);
		String reportOutputFileName = "transaction.pdf";
		String filePath = null;

		try {
			FileUtils.saveURLToFile(reportUrl, AppSettingURL.TRANSACTION_LOCATION + reportOutputFileName);
			FileUtils.saveURLToFile(reportUrl, AppSettingURL.TRANSACTION_LOCATION + serviceReqNo + ".pdf");
			filePath = "pdfTransaction/transaction.pdf";

			if (BSLUtils.isNotNull(fatcaFlag)) {
				if (fatcaFlag.equalsIgnoreCase("Y")) {
					String fatcaURL = AppSettingURL.TRANSACTION_Report_URL + "fatca.rptdesign&nServiceRequestNo="
							+ serviceReqNo + "&__format=pdf";
					logger.info("Fatca URL is :::::::::::::" + fatcaURL);
					FileUtils.saveURLToFile(fatcaURL,
							AppSettingURL.TRANSACTION_LOCATION + serviceReqNo + ".1" + ".pdf");

					logger.info("Fatca URL is " + fatcaURL);

					doMerge(AppSettingURL.TRANSACTION_LOCATION + serviceReqNo + ".pdf",
							AppSettingURL.TRANSACTION_LOCATION + serviceReqNo + ".1" + ".pdf");

					filePath = "pdfTransaction/download.pdf";
				}
			}
			logger.info("Entered into DownLoad ::::::::::::");

			InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(filePath);
			logger.info(":::::::Transaction No is :::::::::" + serviceReqNo + "Stream value is  :::::::" + stream);

			if (BSLUtils.isNotNull(stream)) {
				file = new DefaultStreamedContent(stream, "pdf", serviceReqNo + ".pdf");
			}

			// Deleting PDF after download
			boolean fatcaFile = false;
			boolean transactionFile = (new File(AppSettingURL.TRANSACTION_LOCATION + serviceReqNo + ".pdf")).delete();
			if (BSLUtils.isNotNull(fatcaFlag)) {
				if (fatcaFlag.equalsIgnoreCase("Y")) {
					fatcaFile = (new File(AppSettingURL.TRANSACTION_LOCATION + serviceReqNo + ".1" + ".pdf")).delete();
				}
			}
			logger.info("Status for deleted TXN file is " + transactionFile + "Status for deleted FATCA file is"
					+ fatcaFile);

		} catch (Exception e) {
			logger.info(":::::::Inside catch block of GeneratePDF::::::" + e.getMessage());
			e.printStackTrace();
		}
	}*/

	private BigDecimal seqno1;
	
	// added by Harmain
	public void getMyFunds() throws IOException {
		
		FacesContext context = FacesContext.getCurrentInstance();
		if (BSLUtils.isNotNull(selectPolicyNo))
			
				totalFunds = 0;
				totalFundsAmt = BigDecimal.ZERO;
				toalAmountImPlanCurr = null;
				if(policyDetailsList.size()>0) {
					  for(int i=0;i<policyDetailsList.size();i++) {
						  if(selectPolicyNo.equalsIgnoreCase(policyDetailsList.get(i).getPolicyNo())) {
							  seqno1=policyDetailsList.get(i).getSeqno();
						  }	  
					  }
					}
				fundsDetailsList = customerPortalServices.getMyFunds(selectPolicyNo, seqno1);
				for(int i=0;i<fundsDetailsList.size();i++) {
					fundsDetailsList.get(i).setPolicyno(selectPolicyNo);
				}
						
				if (fundsDetailsList.size() > 0) {
					totalFunds = fundsDetailsList.size();

					Iterator<FundsDto> itFund = fundsDetailsList.iterator();
					while (itFund.hasNext()) {
						FundsDto totalFundDto = new FundsDto();
						totalFundDto = itFund.next();
						if (BSLUtils.isNotNull(totalFundDto.getFundValue())) {
							totalFundsAmt = totalFundsAmt.add(totalFundDto.getPlanValue());
						}
						DecimalFormat df = new DecimalFormat("#,###.00");
						toalAmountImPlanCurr = totalFundDto.getPlanCurrency() + "  " + df.format(totalFundsAmt);
					}
				}
				
		//redirectStrategy.sendRedirect(httpRequest, null, "/user/funds.app");
		context.getExternalContext().redirect(SecurityLibrary.getAppContextPath() + "/user/funds.app");	
		//return "/user/funds";
	}

	// added by I.harshith sai
	public void getMyOutstanding() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		if (BSLUtils.isNotNull(selectPolicyNo))
			
				totOutstandingReceipt = 0;
				totalOutstandingAmt = BigDecimal.ZERO;
				if (selectPolicyNo.equals("")) {
					outstandingDetailsList.clear();
					//RequestContext requestContext = RequestContext.getCurrentInstance();
					PrimeFaces.current().executeScript("document.getElementsByClassName('outstandingListError')[0].style.display=''");
					setPolicyListMapErr("Select Any Policy No");
				} else {
					outstandingDetailsList = customerPortalServices.getCustOutstanding(selectPolicyNo);
				    for(int i=0;i<outstandingDetailsList.size();i++) {
				    	outstandingDetailsList.get(i).setPolicyno(selectPolicyNo);
				    	outstandingDetailsList.get(i).setDueDate(outstandingDetailsList.get(i).getDueDate()!=null?datchan(outstandingDetailsList.get(i).getDueDate()):null);
				    	outstandingDetailsList.get(i).setReceiptingDate(outstandingDetailsList.get(i).getReceiptingDate()!=null?datchan(outstandingDetailsList.get(i).getReceiptingDate()):null);
				    }
					
					if (outstandingDetailsList.size() > 0) {

						BigDecimal tmpTotalOutstandingAmt = BigDecimal.ZERO;
						int count = 0;
						for (CustomerOutstandingDTO Outstanding : outstandingDetailsList) {

							if (BSLUtils.isNotNull(Outstanding.getStatus())) {
								if (Outstanding.getStatus().equalsIgnoreCase(Constants.POLICY_OUTSTANDING_STATUS)) {
									count++;

									if (BSLUtils.isNotNull(Outstanding.getDueAmount())) {
										tmpTotalOutstandingAmt = tmpTotalOutstandingAmt.add(Outstanding.getDueAmount());
									}
									totOutstandingReceipt = count;
									totalOutstandingAmt = tmpTotalOutstandingAmt;
								}
								totalContributionAmtStr = outstandingDetailsList.get(0).getOutstandingCurrencyCode()
										+ " ";
							}
						}
					}

				}
				logger.info("************************");
				logger.info("Application context in Funds page: "+SecurityLibrary.getAppContextPath()+"");
				context.getExternalContext().redirect(SecurityLibrary.getAppContextPath() + "/user/contributionAlteration.app");
		//return "/user/contributionAlteration";
	}

	private BigDecimal seqno; 
	private boolean transaction=false;
	private boolean transaction1=false;
	
	
	public void getSearchPolicy() {
 		
	    searchPolicyList = new SearchPolicyDTO();
		lifeassuredDetailsList = new ArrayList<LifeAssuredDTO>();
		nomineeDetailsList = new ArrayList<NomineeDetailsDTO>();
		fundDetailsSearchList = new ArrayList<FundDetailsSearchDTO>();
		policyBenefitList = new ArrayList<PolicyBenefitDTO>();

		boolean isPolicy = policyListMap.containsKey(searchPolicyNo);
		if (isPolicy) {
			
			if(policyDetailsList.size()>0) {
			  for(int i=0;i<policyDetailsList.size();i++) {
				  if(searchPolicyNo.equalsIgnoreCase(policyDetailsList.get(i).getPolicyNo())) {
					  seqno=policyDetailsList.get(i).getSeqno();
				  }	  
			  }
			}
			
			transaction=false;
			transaction1=false;
			searchPolicyList = null;
			searchPolicyList = new SearchPolicyDTO();
			
			searchPolicyList = customerPortalServices.getSearchPolicyDetails(searchPolicyNo, "EN",seqno);
			DecimalFormat df = new DecimalFormat("#,###.00");
			planValue = df.format(searchPolicyList.getPlanValue());
			if(searchPolicyList.getPlanStatus().equals("Lapse")) {
				transaction=false;
				transaction1=true;
			}else {
				transaction=true;
				transaction1=false;
			}
			
			
			searchPolicyList.setCommencementDate(searchPolicyList.getCommencementDate()!=null?datchan(searchPolicyList.getCommencementDate()):null);
			searchPolicyList.setMaturityDate(searchPolicyList.getMaturityDate()!=null?datchan(searchPolicyList.getMaturityDate()):null);
			searchPolicyList.setIssuanceDate(searchPolicyList.getIssuanceDate()!=null?datchan(searchPolicyList.getIssuanceDate()):null);
			searchPolicyList.setLastPaidDate(searchPolicyList.getLastPaidDate()!=null?datchan(searchPolicyList.getLastPaidDate()):null);
			searchPolicyList.setAutoDbDate(searchPolicyList.getAutoDbDate()!=null?datchan(searchPolicyList.getAutoDbDate()):null);
			
			searchPolicyList.setPremiumDueDate(searchPolicyList.getPremiumDueDate()!=null?datchan(searchPolicyList.getPremiumDueDate()):null);
			searchPolicyList.setPremiumPayEndDate(searchPolicyList.getPremiumPayEndDate()!=null?datchan(searchPolicyList.getPremiumPayEndDate()):null);
			
			
//			searchPolicyList.setCommencementDate(datchan(searchPolicyList.getCommencementDate()));
//			searchPolicyList.setMaturityDate(datchan(searchPolicyList.getMaturityDate()));
//			searchPolicyList.setIssuanceDate(datchan(searchPolicyList.getIssuanceDate()));
//			searchPolicyList.setLastPaidDate(datchan(searchPolicyList.getLastPaidDate()));
//			searchPolicyList.setAutoDbDate(searchPolicyList.getAutoDbDate()!=null?datchan(searchPolicyList.getAutoDbDate()):null);
//			searchPolicyList.setPremiumDueDate(datchan(searchPolicyList.getPremiumDueDate()));
//			searchPolicyList.setPremiumPayEndDate(datchan(searchPolicyList.getPremiumPayEndDate()));
			
			searchPolicyList.setPwamount(searchPolicyList.getPwamount());
			
			getSession().setAttribute("POLICYDATA", searchPolicyList);
			fundDetailsSearchList = searchPolicyList.getFundDetailsList();
			nomineeDetailsList = searchPolicyList.getNomineeDetailsList();
			lifeassuredDetailsList = searchPolicyList.getLifeAssuredList();
			policyBenefitList = searchPolicyList.getPolicyBenefitList();
		} else {
		}
	}

	public String getPolicyDetails(String policyNo) {
		setSearchPolicyNo(policyNo);
		getSession().setAttribute("SELECTEDPOLICYNO", policyNo);
		getSearchPolicy();
		setSearchPolicyNo("");
		logger.info("Policy Number: "+policyNo+" redirecting polic details page ");
		return "/user/serachPolicy?faces-redirect=true";
		
	}
	
	

	// Added by Nithiya
	public String  getMyClaim() {		
		selectedclaimsDetailsDTO = null;
		claimDetailsList = null;
		claimDetailsList = new ArrayList<ClaimsDetailsDTO>();
		totalPendingClaims = 0;
		try {
			if (BSLUtils.isNotNull(selectMaster))
				if (selectMaster.equals("")) {
					claimDetailsList.clear();
				} else {
					List<ClaimsDetailsDTO> tmpClaimDetailsList = new ArrayList<ClaimsDetailsDTO>();
					claimDetailsList = customerPortalServices.getCustClaims(BigDecimal.valueOf(SecurityLibrary.getFullLoggedInUser().getNcustRefNo()));
					if (claimDetailsList != null || claimDetailsList.size() > 0) {
						for(int i=0;i<claimDetailsList.size();i++) {
							
							claimDetailsList.get(i).setIncidentDate(claimDetailsList.get(i).getIncidentDate()!=null?datchan(claimDetailsList.get(i).getIncidentDate()):null);
							claimDetailsList.get(i).setNotificationDate(claimDetailsList.get(i).getNotificationDate()!=null?datchan(claimDetailsList.get(i).getNotificationDate()):null);
							claimDetailsList.get(i).setSettermentDate(claimDetailsList.get(i).getSettermentDate()!=null?datchan(claimDetailsList.get(i).getSettermentDate()):null);
							
//							claimDetailsList.get(i).setIncidentDate(datchan(claimDetailsList.get(i).getIncidentDate()));
//							claimDetailsList.get(i).setNotificationDate(datchan(claimDetailsList.get(i).getNotificationDate()));
//							claimDetailsList.get(i).setSettermentDate(datchan(claimDetailsList.get(i).getSettermentDate()));
						}
						
						for (ClaimsDetailsDTO claim : claimDetailsList) {
							ClaimsDetailsDTO claimsDetailsDTO = new ClaimsDetailsDTO();
							if (claim.getClaimStatus().equalsIgnoreCase(selectMaster)) {
								setSelectedclaimsDetailsDTO(claim);
								claimsDetailsDTO = claim;
								tmpClaimDetailsList.add(claimsDetailsDTO);
							}
							if (claim.getClaimStatus().equalsIgnoreCase("pending")) {
								totalPendingClaims++;
							}
						}
						claimDetailsList = tmpClaimDetailsList;
					}
				}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "/user/claims?faces-redirect=true";
	}

	/*
	 * @author viswakarthick
	 */
	public List<String> completePolicyText(String query) {
		policyStringList = new ArrayList<String>();
		for (PolicyDetailsDTO policyDetailsDTO : policyDetailsList) {
			if (policyDetailsDTO.getPolicyNo().contains(query.toUpperCase()))
				policyStringList.add(policyDetailsDTO.getPolicyNo());
		}
		return policyStringList;
	}

	/*
	 * selecting tab and call the customerportalservice for reducing loading
	 * time
	 * 
	 * @author shashi 19/07/2017
	 */
	public void onPortFolioChange(TabChangeEvent event) {
		//RequestContext requestContext = RequestContext.getCurrentInstance();
		try {
			if (event.getTab().getId().equals("summary_tab")) {

			} else if (event.getTab().getId().equals("policies_tab")) {

			} else if (event.getTab().getId().equals("funds_tab")) {
				if (BSLUtils.isNotNull(fundsDetailsList) && fundsDetailsList.isEmpty()) {
					getMyFunds();
				}
			} else if (event.getTab().getId().equals("outstand_tab")) {
				if (BSLUtils.isNotNull(outstandingDetailsList) && outstandingDetailsList.isEmpty()) {
					getMyOutstanding();
				}
			} else if (event.getTab().getId().equals("claim_tab")) {
				if (BSLUtils.isNotNull(masterList) && masterList.isEmpty())
					masterList = customerPortalServices.getMasterLov(Constants.EN, Constants.CLSTATUS);
				if (BSLUtils.isNotNull(masterList) && !masterList.isEmpty())
					selectMaster = masterList.get(0).getDesc1();
				if (BSLUtils.isNotNull(claimDetailsList) && claimDetailsList.isEmpty()) {
					getMyClaim();
				}
			}

			else if (event.getTab().getId().equals("Transactions_tab")) {
				// serviceRequestMasterDTOList=new ArrayList();
				serviceRequestMasterDTOList = loginDetails.fetchTransactionRequestList(userid);
				System.out.println("Changes applied");
				logger.info("Size of transaction list is " + serviceRequestMasterDTOList.size());
				// requestContext.update(":allTabs:requestDetailstable");
			}

		} catch (Exception e) {
			logger.info("Error at tab event @@@@@@@@@@@@@ or retreiving data:" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Fetch e-statement view
	 * @return
	 */
	public String fetchEStatementView() {
		
		logger.info("E-Statement view rendered from user dashboard ");
		
		return "/user/e-statement?faces-redirect=true";
	}
	
	/**
	 * Fetch e-statement view
	 * @return
	 */
	public String fetchFeedBackView() {
		
		logger.info("Feedback view is rendered from user dashboard ");
		
		return "/user/feedback?faces-redirect=true";
	}

	
	public void submitNewRequestRendering() {
		if(searchPolicyNo==null||searchPolicyNo.equalsIgnoreCase("")) {
			searchPolicyNo = policyDetailsList.get(0).getPolicyNo();	
		}
		getSearchPolicy();
	}

	public List<String> getPolicyStringList() {
		return policyStringList;
	}

	public void setPolicyStringList(List<String> policyStringList) {
		this.policyStringList = policyStringList;
	}

	public List<PolicyDetailsDTO> getPolicyDetailsList() {
		return policyDetailsList;
	}

	public void setPolicyDetailsList(List<PolicyDetailsDTO> policyDetailsList) {
		this.policyDetailsList = policyDetailsList;
	}

	public List<FundDetailsDTO> getFundDetailsList() {
		return fundDetailsList;
	}

	public void setFundDetailsList(List<FundDetailsDTO> fundDetailsList) {
		this.fundDetailsList = fundDetailsList;
	}

	public List<CustomerOutstandingDTO> getOutstandingDetailsList() {
		return outstandingDetailsList;
	}

	public void setOutstandingDetailsList(List<CustomerOutstandingDTO> outstandingDetailsList) {
		this.outstandingDetailsList = outstandingDetailsList;
	}

	public void setCustomerPortalServices(CustomerPortalServicesImpl customerPortalServices) {
		this.customerPortalServices = customerPortalServices;
	}

	public List<ClaimsDetailsDTO> getClaimDetailsList() {
		return claimDetailsList;
	}

	public void setClaimDetailsList(List<ClaimsDetailsDTO> claimDetailsList) {
		this.claimDetailsList = claimDetailsList;
	}

	public int getTotOutstandingReceipt() {
		return totOutstandingReceipt;
	}

	public void setTotOutstandingReceipt(int totOutstandingReceipt) {
		this.totOutstandingReceipt = totOutstandingReceipt;
	}

	public BigDecimal getTotalOutstandingAmt() {
		return totalOutstandingAmt;
	}

	public void setTotalOutstandingAmt(BigDecimal totalOutstandingAmt) {
		this.totalOutstandingAmt = totalOutstandingAmt;
	}

	public List<CustomerPaymentsDetailsDTO> getPaymentsDetailsList() {
		return paymentsDetailsList;
	}

	public void setPaymentsDetailsList(List<CustomerPaymentsDetailsDTO> paymentsDetailsList) {
		this.paymentsDetailsList = paymentsDetailsList;
	}

	public Map<String, String> getPolicyListMap() {
		return policyListMap;
	}

	public void setPolicyListMap(Map<String, String> policyListMap) {
		this.policyListMap = policyListMap;
	}

	public BigDecimal getTotPaymentReceipts() {
		return totPaymentReceipts;
	}

	public void setTotPaymentReceipts(BigDecimal totPaymentReceipts) {
		this.totPaymentReceipts = totPaymentReceipts;
	}

	public ReceiptSummaryDTO getReceiptSummaryDTO() {
		return receiptSummaryDTO;
	}

	public void setReceiptSummaryDTO(ReceiptSummaryDTO receiptSummaryDTO) {
		this.receiptSummaryDTO = receiptSummaryDTO;
	}

	public BigDecimal getTotalPaymentAmt() {
		return totalPaymentAmt;
	}

	public void setTotalPaymentAmt(BigDecimal totalPaymentAmt) {
		this.totalPaymentAmt = totalPaymentAmt;
	}

	public String getSelectPolicyNo() {
		return selectPolicyNo;
	}

	public void setSelectPolicyNo(String selectPolicyNo) {
		this.selectPolicyNo = selectPolicyNo;
	}

	public int getTotalPendingClaims() {
		return totalPendingClaims;
	}

	public void setTotalPendingClaims(int totalPendingClaims) {
		this.totalPendingClaims = totalPendingClaims;
	}

	public String getPolicyListMapErr() {
		return policyListMapErr;
	}

	public void setPolicyListMapErr(String policyListMapErr) {
		this.policyListMapErr = policyListMapErr;
	}

	public List<DepositDTO> getGetMyDepositDTOList() {
		return getMyDepositDTOList;
	}

	public void setGetMyDepositDTOList(List<DepositDTO> getMyDepositDTOList) {
		this.getMyDepositDTOList = getMyDepositDTOList;
	}

	public List<FundsDto> getFundsDetailsList() {
		return fundsDetailsList;
	}

	public void setFundsDetailsList(List<FundsDto> fundsDetailsList) {
		this.fundsDetailsList = fundsDetailsList;
	}

	public int getTotalFunds() {
		return totalFunds;
	}

	public void setTotalFunds(int totalFunds) {
		this.totalFunds = totalFunds;
	}

	public BigDecimal getTotalFundsAmt() {
		return totalFundsAmt;
	}

	public void setTotalFundsAmt(BigDecimal totalFundsAmt) {
		this.totalFundsAmt = totalFundsAmt;
	}

	public List<LifeAssuredDTO> getLifeassuredDetailsList() {
		return lifeassuredDetailsList;
	}

	public void setLifeassuredDetailsList(List<LifeAssuredDTO> lifeassuredDetailsList) {
		this.lifeassuredDetailsList = lifeassuredDetailsList;
	}

	public List<NomineeDetailsDTO> getNomineeDetailsList() {
		return nomineeDetailsList;
	}

	public void setNomineeDetailsList(List<NomineeDetailsDTO> nomineeDetailsList) {
		this.nomineeDetailsList = nomineeDetailsList;
	}

	public List<FundDetailsSearchDTO> getFundDetailsSearchList() {
		return fundDetailsSearchList;
	}

	public void setFundDetailsSearchList(List<FundDetailsSearchDTO> fundDetailsSearchList) {
		this.fundDetailsSearchList = fundDetailsSearchList;
	}

	public List<PolicyBenefitDTO> getPolicyBenefitList() {
		return policyBenefitList;
	}

	public void setPolicyBenefitList(List<PolicyBenefitDTO> policyBenefitList) {
		this.policyBenefitList = policyBenefitList;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public SearchPolicyDTO getSearchPolicyList() {
		return searchPolicyList;
	}

	public void setSearchPolicyList(SearchPolicyDTO searchPolicyList) {
		this.searchPolicyList = searchPolicyList;
	}

	public String getSearchPolicyNo() {
		return searchPolicyNo;
	}

	public void setSearchPolicyNo(String searchPolicyNo) {
		this.searchPolicyNo = searchPolicyNo;
	}

	public String getEmptyMessageForSur() {
		return emptyMessageForSur;
	}

	public void setEmptyMessageForSur(String emptyMessageForSur) {
		this.emptyMessageForSur = emptyMessageForSur;
	}

	public List<MasterListDTO> getMasterList() {
		return masterList;
	}

	public void setMasterList(List<MasterListDTO> masterList) {
		this.masterList = masterList;
	}

	public String getSelectMaster() {
		return selectMaster;
	}

	public void setSelectMaster(String selectMaster) {
		this.selectMaster = selectMaster;
	}

	public String getSelectMasterErr() {
		return selectMasterErr;
	}

	public void setSelectMasterErr(String selectMasterErr) {
		this.selectMasterErr = selectMasterErr;
	}

	public ClaimsDetailsDTO getSelectedclaimsDetailsDTO() {
		return selectedclaimsDetailsDTO;
	}

	public void setSelectedclaimsDetailsDTO(ClaimsDetailsDTO selectedclaimsDetailsDTO) {
		this.selectedclaimsDetailsDTO = selectedclaimsDetailsDTO;
	}

	public List<ClaimsDetailsDTO> getSelectedClaimDetailsList() {
		return selectedClaimDetailsList;
	}

	public void setSelectedClaimDetailsList(List<ClaimsDetailsDTO> selectedClaimDetailsList) {
		this.selectedClaimDetailsList = selectedClaimDetailsList;
	}

	public String getBaseCurrencyCodeStr() {
		return baseCurrencyCodeStr;
	}

	public void setBaseCurrencyCodeStr(String baseCurrencyCodeStr) {
		this.baseCurrencyCodeStr = baseCurrencyCodeStr;
	}

	public String getTotalPaymentAmtStr() {
		return totalPaymentAmtStr;
	}

	public void setTotalPaymentAmtStr(String totalPaymentAmtStr) {
		this.totalPaymentAmtStr = totalPaymentAmtStr;
	}

	public String getTotalContributionAmtStr() {
		return totalContributionAmtStr;
	}

	public void setTotalContributionAmtStr(String totalContributionAmtStr) {
		this.totalContributionAmtStr = totalContributionAmtStr;
	}

	public List<PolicyDetailsDTO> getFilterPolicyDetailsList() {
		return filterPolicyDetailsList;
	}

	public void setFilterPolicyDetailsList(List<PolicyDetailsDTO> filterPolicyDetailsList) {
		this.filterPolicyDetailsList = filterPolicyDetailsList;
	}

	public List<FundsDto> getFilterFundsDetailsList() {
		return filterFundsDetailsList;
	}

	public void setFilterFundsDetailsList(List<FundsDto> filterFundsDetailsList) {
		this.filterFundsDetailsList = filterFundsDetailsList;
	}

	public List<CustomerOutstandingDTO> getFilterOutstandingDetailsList() {
		return filterOutstandingDetailsList;
	}

	public void setFilterOutstandingDetailsList(List<CustomerOutstandingDTO> filterOutstandingDetailsList) {
		this.filterOutstandingDetailsList = filterOutstandingDetailsList;
	}

	public List<DepositDTO> getFilterGetMyDepositDTOList() {
		return filterGetMyDepositDTOList;
	}

	public void setFilterGetMyDepositDTOList(List<DepositDTO> filterGetMyDepositDTOList) {
		this.filterGetMyDepositDTOList = filterGetMyDepositDTOList;
	}

	public List<ClaimsDetailsDTO> getFilterClaimDetailsList() {
		return filterClaimDetailsList;
	}

	public void setFilterClaimDetailsList(List<ClaimsDetailsDTO> filterClaimDetailsList) {
		this.filterClaimDetailsList = filterClaimDetailsList;
	}

	public String getToalAmountImPlanCurr() {
		return toalAmountImPlanCurr;
	}

	public void setToalAmountImPlanCurr(String toalAmountImPlanCurr) {
		this.toalAmountImPlanCurr = toalAmountImPlanCurr;
	}

	public List<ServiceRequestMasterDTO> getServiceRequestMasterDTOList() {
		return serviceRequestMasterDTOList;
	}

	public void setServiceRequestMasterDTOList(List<ServiceRequestMasterDTO> serviceRequestMasterDTOList) {
		this.serviceRequestMasterDTOList = serviceRequestMasterDTOList;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public List<ServiceRequestMasterDTO> getFilteredServiceRequestMasteList() {
		return filteredServiceRequestMasteList;
	}

	public void setFilteredServiceRequestMasteList(List<ServiceRequestMasterDTO> filteredServiceRequestMasteList) {
		this.filteredServiceRequestMasteList = filteredServiceRequestMasteList;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	/*
	 * public String getUpdateContactFlag() { return updateContactFlag; }
	 * 
	 * public void setUpdateContactFlag(String updateContactFlag) {
	 * this.updateContactFlag = updateContactFlag; }
	 * 
	 * public String getContributionHolidayFlag() { return contributionHolidayFlag;
	 * }
	 * 
	 * public void setContributionHolidayFlag(String contributionHolidayFlag) {
	 * this.contributionHolidayFlag = contributionHolidayFlag; }
	 * 
	 * public String getRedirectionFlag() { return redirectionFlag; }
	 * 
	 * public void setRedirectionFlag(String redirectionFlag) { this.redirectionFlag
	 * = redirectionFlag; }
	 * 
	 * public String getContributionAltFlag() { return contributionAltFlag; }
	 * 
	 * public void setContributionAltFlag(String contributionAltFlag) {
	 * this.contributionAltFlag = contributionAltFlag; }
	 * 
	 * public String getReinstatementFlag() { return reinstatementFlag; }
	 * 
	 * public void setReinstatementFlag(String reinstatementFlag) {
	 * this.reinstatementFlag = reinstatementFlag; }
	 * 
	 * public String getProtectionBenefitFlag() { return protectionBenefitFlag; }
	 * 
	 * public void setProtectionBenefitFlag(String protectionBenefitFlag) {
	 * this.protectionBenefitFlag = protectionBenefitFlag; }
	 */

	public String getLastLoginDateTime() {
		return lastLoginDateTime;
	}

	public void setLastLoginDateTime(String lastLoginDateTime) {
		this.lastLoginDateTime = lastLoginDateTime;
	}

	public String getCurrentDateTime() {
		return currentDateTime;
	}

	public void setCurrentDateTime(String currentDateTime) {
		this.currentDateTime = currentDateTime;
	}
	// added by Ritu
//	public Calendar datchan(Calendar newDate){	
//		int dt = 0,mn=0 , yr=0;
//		yr=newDate.get(1);
//		mn=newDate.get(2);
//		dt=newDate.get(5);
//		Calendar myDate=new GregorianCalendar(yr,mn,dt);
//		return myDate;
//	}
	
	public Calendar datchan(Calendar newDate){		
		int dt = 0,mn=0 , yr=0;
		yr=newDate.get(1);
		mn=newDate.get(2);
		dt=newDate.get(5);
		Calendar myDate=new GregorianCalendar(yr,mn,dt);	
		myDate.add(Calendar.DATE,1);
		return myDate;
	}

	public String getUpdateContactFlag() {
		return updateContactFlag;
	}

	public void setUpdateContactFlag(String updateContactFlag) {
		this.updateContactFlag = updateContactFlag;
	}

	public String getContributionHolidayFlag() {
		return contributionHolidayFlag;
	}

	public void setContributionHolidayFlag(String contributionHolidayFlag) {
		this.contributionHolidayFlag = contributionHolidayFlag;
	}

	public String getRedirectionFlag() {
		return redirectionFlag;
	}

	public void setRedirectionFlag(String redirectionFlag) {
		this.redirectionFlag = redirectionFlag;
	}

	public String getContributionAltFlag() {
		return contributionAltFlag;
	}

	public void setContributionAltFlag(String contributionAltFlag) {
		this.contributionAltFlag = contributionAltFlag;
	}

	public String getReinstatementFlag() {
		return reinstatementFlag;
	}

	public void setReinstatementFlag(String reinstatementFlag) {
		this.reinstatementFlag = reinstatementFlag;
	}

	public String getProtectionBenefitFlag() {
		return protectionBenefitFlag;
	}

	public void setProtectionBenefitFlag(String protectionBenefitFlag) {
		this.protectionBenefitFlag = protectionBenefitFlag;
	}

	public static String getFileName() {
		return fileName;
	}

	public static void setFileName(String fileName) {
		HomeDetailsAction.fileName = fileName;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
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

	public String getApplicationdisplayname() {
		return applicationdisplayname;
	}

	public void setApplicationdisplayname(String applicationdisplayname) {
		this.applicationdisplayname = applicationdisplayname;
	}

	public BigDecimal getSeqno1() {
		return seqno1;
	}

	public void setSeqno1(BigDecimal seqno1) {
		this.seqno1 = seqno1;
	}

	public BigDecimal getSeqno() {
		return seqno;
	}

	public void setSeqno(BigDecimal seqno) {
		this.seqno = seqno;
	}

	public ClaimsDetailsDTO getClaimDetailsList1() {
		return claimDetailsList1;
	}

	public void setClaimDetailsList1(ClaimsDetailsDTO claimDetailsList1) {
		this.claimDetailsList1 = claimDetailsList1;
	}

	public String getReturnURL() {
		return returnURL;
	}

	public void setReturnURL(String returnURL) {
		this.returnURL = returnURL;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public boolean isTransaction() {
		return transaction;
	}

	public void setTransaction(boolean transaction) {
		this.transaction = transaction;
	}

	public boolean isTransaction1() {
		return transaction1;
	}

	public void setTransaction1(boolean transaction1) {
		this.transaction1 = transaction1;
	}

	public Map<String, String> getFundPolicyListMap() {
		return fundPolicyListMap;
	}

	public void setFundPolicyListMap(Map<String, String> fundPolicyListMap) {
		this.fundPolicyListMap = fundPolicyListMap;
	}

	public String getPlanValue() {
		return planValue;
	}

	public void setPlanValue(String planValue) {
		this.planValue = planValue;
	}


	public void checko() {
		PrimeFaces.current().executeScript("PF('dlg3').show()");
	}
	
	
}
