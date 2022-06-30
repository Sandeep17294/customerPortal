package com.aetins.customerportal.web.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.polar.PolarAreaChartDataSet;
import org.primefaces.model.charts.polar.PolarAreaChartModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.aetins.customerportal.core.faceletstaglib.SecurityLibrary;
import com.aetins.customerportal.core.primefaces.view.GuestPreferences;
import com.aetins.customerportal.core.services.IMailService;
import com.aetins.customerportal.web.common.Constants;
import com.aetins.customerportal.web.common.LoginSession;
import com.aetins.customerportal.web.dao.CpUserInfoDAO;
import com.aetins.customerportal.web.dto.CpCustomerDetailDTO;
import com.aetins.customerportal.web.dto.CpFeedbackDTO;
import com.aetins.customerportal.web.dto.CpServiceTypeDTO;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.dto.FeedbackReplyDTO;
import com.aetins.customerportal.web.dto.FeedbackReplyDocsDTO;
import com.aetins.customerportal.web.dto.PasswordRulesDTO;
import com.aetins.customerportal.web.dto.UserDTO;
import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.pojo.MultipartInputStreamFileResource;
import com.aetins.customerportal.web.service.CustomerLoginBL;
import com.aetins.customerportal.web.service.FeedbackBL;
import com.aetins.customerportal.web.service.ForgetPasswordBL;
import com.aetins.customerportal.web.service.impl.FeedbackBLImpl;
import com.aetins.customerportal.web.utils.AppSettingURL;
import com.aetins.customerportal.web.utils.ApplicationMailer;
import com.aetins.customerportal.web.utils.BSLUtils;
import com.aetins.customerportal.web.utils.DateUtil;
import com.aetins.customerportal.web.utils.JSONUtils;
import com.aetins.customerportal.web.utils.Oauth2Utils;
import com.aetins.customerportal.web.utils.RestTemplateUtils;


@Controller
@Scope("session")
public class FeedbackAction extends BaseAction {
	
	
	private static final Logger logger = LoggerFactory.getLogger(FeedbackAction.class);

	
	@Autowired
	private IMailService mailService;
	
	@Autowired
	FeedbackBL feedbackBL;
	
	@Autowired
	private CustomerLoginBL customerLoginBL;
	
	@Autowired
	private ForgetPasswordBL forgetPasswordBL;
	
	@Autowired
	ForgetPasswordBL forgetPasswordBLImpl;
	
	@Autowired
    LoginSession loginSession;
	
	@Autowired
	private ApplicationMailer appMailer;
	private String to;
	private String subject;
	private String body;
	private String successMsg;
	private boolean successRenderMsg;
	private List<CpFeedbackDTO> feedbackList;
	private List<CpFeedbackDTO> feedBusUserList;
	
	private CpFeedbackDTO selectfeedbacklist;
	
	private List<FeedbackReplyDTO> replyList;
	private CpFeedbackDTO getFeedbackDTO;
	
	
	
	private boolean replyVisibleFlag;
	private Map<String, String> replyRateMap;
	private String replyBy;
	private String feedbackType;
	private String savePolNo;
	private String serviceType;
	private String feedbackRating;
	private String feedbackNote;
	private String saveComplaintNo;
	private String userid;
	private String agentCall;
	private String csdCall;
	private Date callTime;
	private String replyNote;
	private String replyRate;
	private String closeFeedback;
	private int replyId;
	private boolean replyStatus;
	private Date now;
	private boolean successReplyFlag;
	private Map<String, String> replySaveMap;
	private String closedCompMessage;
	private String busUserdId;
	private Date fromDate;
	private Date toDate = new Date();
	private String busFeedStatus;
	private String userType;
	private boolean linkFlag;
	private CpUserInfoDTO cpUserInfoDTO;
	private String custName;
	private int cutRefNo;
	private String bodyMsg;
	private List<CpServiceTypeDTO> listServiceType = new ArrayList();
	List<CpCustomerDetailDTO> cpCustomerDetailList = new ArrayList();
	private List<CpUserInfoDTO> cpUserList = new ArrayList();
	private boolean checking;
	
	public void selectboxsave(CpFeedbackDTO feedbackDTO){
		closing=null;
		Date dd = new Date();
		if(feedbackDTO!= null){
			feedbackDTO.setCheckbox(true);
			if(feedbackDTO.isCheckbox()== true){
				//feedbackDTO.setFeedbackType("C");
				feedbackDTO.setFeedStatus("CLOSED");
				feedbackDTO.setClosedDate(dd);
				setChecking(false);
				boolean checkbox = feedbackBL.updateFeedbackStatus(feedbackDTO);
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
						"Feedback Status Changed Successfully.");
				PrimeFaces.current().dialog().showMessageDynamic(message);

			}
			feedbackDTO.setCheckbox(false);	
		}	
	 }
	
	

	public boolean isChecking() {
		return checking;
	}

	public void setChecking(boolean checking) {
		this.checking = checking;
	}



	private UserDTO userDetails = new UserDTO();
	private boolean renderRatePanel = true;
	private boolean renderRatingCondition;
	private String callTimeDay;
	
	private String userName;
	private int custRefNo;
	private boolean callRequired=false;
	
	// Uploaded FTP file
	private UploadedFile uploadedFile;
	private String ftpFileName;
	private String accesstoken;
	private String jsonString;
	private String selectedDownloadedFile;

	private List<CpFeedbackDTO> feedBusUserList1 = new ArrayList<CpFeedbackDTO>();
	private boolean checkbox;
	private boolean hideReply;
	private DonutChartModel donutModel;
	int totalfeedback1;
	int totalfeedbackopen;
	int totalfeedbackclose;

	private String applicationdisplayname;
	
	public FeedbackAction() {
	}
	@Autowired
	GuestPreferences guest;
	
	@PostConstruct
	public void init() {
		try {
			listServiceType = feedbackBL.listServiceType();
			logger.info("List all user feedback or complaint retreiving started");
			userid = SecurityLibrary.getFullLoggedInUser().getVuserName();
			feedBusUserList = feedbackBL.listAllFeedbacks();
			if(userid!=null){
				  CpFeedbackDTO dto=new CpFeedbackDTO();
				CpFeedbackDTO[] listDtos = new CpFeedbackDTO[feedBusUserList.size()];
					listDtos = feedBusUserList.toArray(listDtos);
					  for(int i=0;i<listDtos.length;i++){
						  if(listDtos[i].getFeedStatus().equalsIgnoreCase("OPEN")){
							   dto=listDtos[i];
							  feedBusUserList1.add(dto);
						  }
					}			  
			   }
			    	
			String usergroup=SecurityLibrary.getFullLoggedInUser().getVgroup();
			if(usergroup.equalsIgnoreCase("B")) {
				hideReply=false;
			}
			if(SecurityLibrary.getFullLoggedInUser().getVgroup().equalsIgnoreCase("B")) {
				applicationdisplayname=SecurityLibrary.getFullLoggedInUser().getVprefName();
			}
			guest.fetchtheme();

		
			createDonutModel();
			
			logger.info("Get User information retreiving started");
			cpUserInfoDTO = feedbackBL.getCpUserInfo(userid);
			userName = userid;
			userDetails.setUserName(userName);
			cpUserList = customerLoginBL.listUserDetailsForgetPassword(userDetails);
			custRefNo = SecurityLibrary.getFullLoggedInUser().getNcustRefNo();
			userDetails.setCustRefNo(custRefNo);
			cpCustomerDetailList = forgetPasswordBLImpl.secureDetailsForgetPassword(userDetails);
			String title = "";
			if (BSLUtils.isNotNull(cpUserInfoDTO.getVtitle()))
				title = cpUserInfoDTO.getVtitle();
			custName = (title + " " + cpUserInfoDTO.getVprefName());
			replyRateMap = new LinkedHashMap();
			replyRateMap.put("Excellent", "Excellent");
			replyRateMap.put("Good", "Good");
			replyRateMap.put("Average", "Average");
			replyRateMap.put("Poor", "Poor");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Unexception ocurred while loading init() : {}",FeedbackAction.class);
		}
	}
	
	public String customerfeedback() {
		if(SecurityLibrary.getFullLoggedInUser().getVgroup().equalsIgnoreCase("U")) {
			feedbackList = null;
			feedbackList = new ArrayList<CpFeedbackDTO>();
			feedbackList=feedbackBL.listAllFeedbacks(userid);
		}
		return "/user/feedbacklist.jsf?faces-redirect=true";
	}
	
	public void createDonutModel() {
        donutModel = new DonutChartModel();
        ChartData data = new ChartData();
        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        totalfeedback1=feedBusUserList.size();
        if(feedBusUserList.size()>0) {
        	for(int i=0;i<feedBusUserList.size();i++) {
        		if (feedBusUserList.get(i).getFeedStatus().equalsIgnoreCase("open")) {
        			totalfeedbackopen++;
				} else {
					totalfeedbackclose++;
				}		
        	}
        }   
        values.add(totalfeedback1);
        values.add(totalfeedbackopen);
        values.add(totalfeedbackclose);
        dataSet.setData(values);
         
        List<String> bgColors = new ArrayList<>();
        bgColors.add("#035aa6");
        bgColors.add("#d63447");
        bgColors.add("#5b8c5a");
        dataSet.setBackgroundColor(bgColors);
         
        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Total Feedbacl");
        labels.add("Total Open Feeback");
        labels.add("Total Closed Feedback");
        data.setLabels(labels);
       
        donutModel.setData(data);
    }
	
	
	public void calltimeValidation() {
		if (BSLUtils.isNotNull(csdCall)) {
			if (csdCall.equalsIgnoreCase("Y")) {
				setCallRequired(true);
			}else{
				setCallRequired(false);
			}
		}
	}

	public void enableRatePanel() {
		if ((BSLUtils.isNotNull(serviceType)) && (serviceType.equalsIgnoreCase("QUERY"))) {
			setRenderRatePanel(false);
			feedbackRating = null;
		} else {
			setRenderRatePanel(true);
		}
	}

	public void enableRatingCondition() {
		if ((BSLUtils.isNotNull(closeFeedback)) && (closeFeedback.equalsIgnoreCase("y"))) {
			setRenderRatingCondition(true);
		} else
			setRenderRatingCondition(false);
	}

	public void getallFeedbackList() {
		feedbackList = feedbackBL.listAllFeedbacks(SecurityLibrary.getFullLoggedInUser().getVuserName().toString());
	}
	
	public String getFeedbackInfo(CpFeedbackDTO feedback) {
		setLinkFlag(false);
		setReplyVisibleFlag(false);
		setSuccessReplyFlag(false);
		logger.info("get Feedback Info from user page:" + feedback.getComplaintNo());
		getFeedbackDTO = feedback;
		replyList = feedbackBL.listAllReplys(getFeedbackDTO.getFeedbackId());
		logger.info("List all user reply:" + replyList.size());
		userType = SecurityLibrary.getFullLoggedInUser().getVgroup().toLowerCase();
		logger.info("Setting customer ref no..");
		CpUserInfoDTO userRefNo = feedbackBL.getCpUserInfo(getFeedbackDTO.getUserId());
		cutRefNo = userRefNo.getNcustRefNo();

		if ((replyList.size() == 0) && (userType.equalsIgnoreCase("B"))) {
			setLinkFlag(true);
			return "/businessuser/businessfeedbackreply.jsf?faces-redirect=true";
		} else if ((replyList.size() == 0) && (userType.equalsIgnoreCase("U"))) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
					"The response is awaited on this.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		} else if ((replyList.size() == 0)  || (userType.equalsIgnoreCase("A"))) {
			setLinkFlag(true);
			return "/businessuser/customercomplanits.jsf?faces-redirect=true";	
		} else if (replyList.size() > 0) {
			for (FeedbackReplyDTO replyDTO : replyList) {
				if (replyDTO.getUserId().equals(cpUserInfoDTO.getVuserName())) {
					replyDTO.setLastupdUser(custName);
				} else {
					CpUserInfoDTO cpUserDTO = feedbackBL.getCpUserInfo(replyDTO.getUserId());
					if ((BSLUtils.isNotNull(cpUserDTO)) && (BSLUtils.isNotNull(cpUserDTO.getVtitle()))) {
						replyDTO.setLastupdUser(cpUserDTO.getVtitle() + " " + cpUserDTO.getVprefName());
					}
				}
			}
			if(SecurityLibrary.getFullLoggedInUser().getVgroup().equalsIgnoreCase("B")) {
				return "/businessuser/businessfeedbackreply.jsf?faces-redirect=true";	
			} else {
				return "/user/samplereply.jsf?faces-redirect=true";
			}
		}
		return null;
		
		
	}
	
	private String closing;
	
	public void enableReplyPanel(ActionEvent event) {
		replyList = feedbackBL.listAllReplys(getFeedbackDTO.getFeedbackId());
		logger.info("List all user reply size:" + replyList.size());
		if (!getFeedbackDTO.getFeedStatus().equalsIgnoreCase("Closed")) {
			replyId = ((Integer) event.getComponent().getAttributes().get("replyId")).intValue();
			System.out.println("Reply id: " + replyId);
			System.out.println("Reply Feedback id " + getFeedbackDTO.getFeedbackId() + " and feedback Status: "
					+ getFeedbackDTO.getFeedStatus());
			clearReplyValues();
			setReplyVisibleFlag(true);
		} else if ((getFeedbackDTO.getFeedStatus().equalsIgnoreCase("Closed")) && (userType.equalsIgnoreCase("U"))) {
			logger.info("Error message for feedback or complaint closed");
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
					"Complaint/Query has been Closed and cannot be responded to..");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		} else if ((getFeedbackDTO.getFeedStatus().equalsIgnoreCase("Closed")) && (userType.equalsIgnoreCase("B"))) {
			replyId = ((Integer) event.getComponent().getAttributes().get("replyId")).intValue();
			clearReplyValues();
			setReplyVisibleFlag(true);
		}
	}

	public void closeReplyPanel() {
		setReplyVisibleFlag(false);
		clearReplyValues();
		setReplyId(0);
	}

	public void btnSaveReplyFeedback() {
		setSuccessReplyFlag(false);
		closing=null;
		if ((!BSLUtils.isNull(replyNote)) || (!BSLUtils.isNull(replyRate))) {
			cpUserInfoDTO = feedbackBL.getCpUserInfo(SecurityLibrary.getFullLoggedInUser().getVuserName());
			FeedbackReplyDTO feedbackReplyDTO = new FeedbackReplyDTO();
			feedbackReplyDTO.setMapFeedbackId(getFeedbackDTO);
			feedbackReplyDTO.setUserType(userType.toUpperCase());
			feedbackReplyDTO.setUserId(cpUserInfoDTO.getVuserName());
			feedbackReplyDTO.setReplyDate(new Date());
			feedbackReplyDTO.setReplyNote(replyNote);
			if (replyRate != null)
				feedbackReplyDTO.setReplyRate(replyRate.toUpperCase());
			if (closeFeedback != null)
				feedbackReplyDTO.setCloseComp(closeFeedback.toUpperCase());
			feedbackReplyDTO.setParentRepId(replyId);
			if (BSLUtils.isNotNull(cpUserInfoDTO.getVtitle())) {
				feedbackReplyDTO.setLastupdUser(cpUserInfoDTO.getVtitle() + " " + cpUserInfoDTO.getVprefName());
			} else
				feedbackReplyDTO.setLastupdUser(cpUserInfoDTO.getVprefName());
			feedbackReplyDTO.setLastupdDate(new Date());
			feedbackReplyDTO.setLastupdProg("Reply Feedback".toUpperCase());

			//Query compliance file upload 
			if(BSLUtils.isNotNull(uploadedFile)&&getFeedbackDTO!=null?BSLUtils.isNotNull(getFeedbackDTO.getComplaintNo()):false) {
				
				FeedbackReplyDocsDTO feedBackReplyDocsDto = new FeedbackReplyDocsDTO();
				feedBackReplyDocsDto.setFileSize(uploadedFile.getSize());
				feedBackReplyDocsDto.setUploadDate(new Date());
				feedBackReplyDocsDto.setFileName(uploadedFile.getFileName());
				feedBackReplyDocsDto.setUploadedUserType(feedbackReplyDTO.getUserType());
				feedBackReplyDocsDto.setUploadedUserId(feedbackReplyDTO.getUserId());
			
				feedbackReplyDTO.setFeedbackReplyDocsDTO(feedBackReplyDocsDto);
				
				logger.info("Upload File to ftp");
				//malik commanded
				//processFileFTP();
			}
			boolean status = feedbackBL.saveReply(feedbackReplyDTO);
			System.out.println("save Reply Status:" + status);
			logger.info("save Reply Status:" + status);
			if ((closeFeedback != null) && (closeFeedback.equalsIgnoreCase("y"))) {
				feedbackReplyDTO.getMapFeedbackId().setFeedStatus("Closed".toUpperCase());
				feedbackReplyDTO.getMapFeedbackId().setLastupdDate(new Date());
				feedbackReplyDTO.getMapFeedbackId().setLastupdProg("Reply Feedback".toUpperCase());
				feedbackReplyDTO.getMapFeedbackId().setLastupdUser(cpUserInfoDTO.getVuserName());
				feedbackReplyDTO.getMapFeedbackId().setClosedDate(new Date());
				replyStatus = feedbackBL.updateFeedbackStatus(feedbackReplyDTO.getMapFeedbackId());
				System.out.println("update Feedback Status:" + replyStatus);
				logger.info("update Feedback Status:" + replyStatus);
			}

			if (status) {
				replySaveMap = new LinkedHashMap();
				replySaveMap.put("replyNote", replyNote);
				if (replyRate != null)
					replySaveMap.put("replyRate", replyRate);
				replySaveMap.put("userid", cpUserInfoDTO.getVuserName());

				if (feedbackReplyDTO.getMapFeedbackId().getFeedbackType().equalsIgnoreCase("c")) {
					if (closeFeedback != null) {
						if (closeFeedback.equalsIgnoreCase("y")) {
							replySaveMap.put("CloseComp","Closed the Complaint");
						} else {
							replySaveMap.put("CloseComp","Complaint Status open");
						}
					}
				} else if ((feedbackReplyDTO.getMapFeedbackId().getFeedbackType().equalsIgnoreCase("f"))
						&& (closeFeedback != null)) {
					if (closeFeedback.equalsIgnoreCase("y")) {
						replySaveMap.put("CloseComp", "Closed the Feedback");
					} else {
						replySaveMap.put("CloseComp", "Feedback Status open");
					}
				}
				getallFeedbackList();
            
				String to = null;
				String subject = null;
				String body = null;
				CpUserInfoDTO userDto = null;
				if (userType.equalsIgnoreCase("U")) {
					PasswordRulesDTO pRulesDTO = feedbackBL.getCpSettingDto();
					to = pRulesDTO.getvCsdEmail();
				} else if (userType.equalsIgnoreCase("B")) {
					userDto = feedbackBL.getCpUserInfo(getFeedbackDTO.getUserId());
					to = userDto.getVemail();
				}
				
				if ((getFeedbackDTO.getFeedStatus().equalsIgnoreCase("Closed")) && (userType.equalsIgnoreCase("U"))) {
					if (feedbackReplyDTO.getMapFeedbackId().getFeedbackType().equalsIgnoreCase("f")) {
						subject = "Query Status" + " "
								+ "is closed" + " "
								+ "Requested ID" + ":"
								+ feedbackReplyDTO.getMapFeedbackId().getComplaintNo().trim();
						body = "=Msg from:" + " " + feedbackReplyDTO.getUserId()
								+ "\r\n\r\n" + "Query No:".trim() + " "
								+ getFeedbackDTO.getComplaintNo() + " "
								+ "is closed".trim();
					} else {
						subject ="Complaint Status" + " "
								+ "is closed" + " "
								+ "Requested ID".trim() + ":"
								+ feedbackReplyDTO.getMapFeedbackId().getComplaintNo().trim();
						body = "Msg from:" + " " + feedbackReplyDTO.getUserId()
								+ "\r\n\r\n" + "Query No:".trim() + " "
								+ getFeedbackDTO.getComplaintNo() + " "
								+ "is closed".trim();
					}
				} else {
					subject = "Msg from:" + " " + feedbackReplyDTO.getUserId()
							+ "Requested ID".trim() + ":"
							+ feedbackReplyDTO.getMapFeedbackId().getComplaintNo().trim();
					body = "Reply message from :" + " " + feedbackReplyDTO.getLastupdUser()
							+ "\r\n\r\n" + feedbackReplyDTO.getReplyNote();
				}

				logger.info("Sending email to appropriate user");
				
				boolean mailFlag = true;
				try {
					mailService.sendMail(SecurityLibrary.getFullLoggedInUser().getVemail(), subject, body+"");
					//appMailer.sendMail(to, subject, body);
				} catch (Exception e) {
					mailFlag = false;
					e.printStackTrace();
					logger.info("Error at sending email : " + e.getMessage());
				}
				if (mailFlag) {
					if (userType.equalsIgnoreCase("B")) {
						closing=Constants.ADMNSUBMIT;
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
								"Data Processed Successfully.");
						PrimeFaces.current().dialog().showMessageDynamic(message);
					} else if (userType.equalsIgnoreCase("U")) {
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
								"Data Processed Successfully.");
						PrimeFaces.current().dialog().showMessageDynamic(message);
					}
				}
			    now = feedbackReplyDTO.getReplyDate();
				setSuccessReplyFlag(true);
	        	setReplyVisibleFlag(false);
	        	setReplyId(0);
	            setLinkFlag(false);
			}
		}
	}

	public void changeServiceType(AjaxBehaviorEvent event) {
	}
	
	private String returnmessage;
	private boolean returning;
	
	
	
	public String getReturnmessage() {
		return returnmessage;
	}



	public void setReturnmessage(String returnmessage) {
		this.returnmessage = returnmessage;
	}



	public boolean isReturning() {
		return returning;
	}



	public void setReturning(boolean returning) {
		this.returning = returning;
	}



	
	@Autowired
	CpUserInfoDAO cpUserInfoDAO;
	
	
	public void btnSaveFeedback1() {
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		FacesMessage message = new FacesMessage();
		returning = true;
		returnmessage = null;
		if(feedbackType==null || feedbackType.equalsIgnoreCase("")) {
			returning = false;
		}
		if(serviceType==null || serviceType.equalsIgnoreCase("")) {
			returning = false;
		}
		if(feedbackNote==null || feedbackNote.equalsIgnoreCase("")) {
			returning = false;
		}
		if(csdCall==null || csdCall.equalsIgnoreCase("")) {
			returning = false;
		}else if(csdCall.equalsIgnoreCase("Y")){
			if(callTime==null || callTimeDay.equalsIgnoreCase("")) {
				returning = false;
			}
		}		
		if(returning == true) {
			CpFeedbackDTO cpFeedbackDTO = new CpFeedbackDTO();
			PasswordRulesDTO pRulesDTO = feedbackBL.getCpSettingDto();
			logger.info("Enter into Feed back List:::::::::::::::::");
			saveComplaintNo = pRulesDTO.getvCompPrefix();
			int autoGen = feedbackBL.getSequenceNoCount() + 1;
			cpFeedbackDTO.setComplaintNo(saveComplaintNo + "0" + autoGen);
			cpFeedbackDTO.setUserId(SecurityLibrary.getFullLoggedInUser().getVuserName());
			cpFeedbackDTO.setCompDate(new Date());
			cpFeedbackDTO.setFeedbackType(feedbackType.toUpperCase());
			cpFeedbackDTO.setPolicyNo(savePolNo);
			cpFeedbackDTO.setServType(serviceType);
			cpFeedbackDTO.setFeedbackNote(feedbackNote);
			cpFeedbackDTO.setCsdCall(csdCall.toUpperCase());
			if (BSLUtils.isNotNull(callTime)) {
				String tmpCallTime;
				if (callTime.getHours() < 12) {
					tmpCallTime = callTime.getHours() + ":" + callTime.getMinutes() + " AM";
				} else
					tmpCallTime = "0" + (callTime.getHours() - 12) + ":" + callTime.getMinutes() + " PM";
				cpFeedbackDTO.setCallTime(tmpCallTime);
				cpFeedbackDTO.setCalltimeDay(callTimeDay);
			}
			if (BSLUtils.isNotNull(feedbackRating))
				cpFeedbackDTO.setFeedRate(feedbackRating.toUpperCase());
			cpFeedbackDTO.setFeedStatus("OPEN");
			cpFeedbackDTO.setLastupdDate(cpFeedbackDTO.getCompDate());
			cpFeedbackDTO.setLastupdUser(cpFeedbackDTO.getUserId());
			cpFeedbackDTO.setLastupdProg("POST FEEDBACK");
			boolean status = feedbackBL.saveFeedback(cpFeedbackDTO);
			logger.info("Size of List is :::::::::::::::::::" + cpCustomerDetailList.size());
			String custName;
			if (cpCustomerDetailList.size() != 0) {
				custName =  SecurityLibrary.getFullLoggedInUser().getVuserName();
				//custName = ((CpCustomerDetailDTO) cpCustomerDetailList.get(0)).getVcustName();
				logger.info("&&&&&&&&&&&&&&&&" + custName);
			} else {
				custName = "Customer";
			}
			if (status) {
				to = pRulesDTO.getvCsdEmail();
				if (feedbackType.equalsIgnoreCase("F")) {
					subject = ("EasyLife : Query : " + cpFeedbackDTO.getPolicyNo() + " : " + cpFeedbackDTO.getServType());
				} else {
					subject = ("EasyLife : Complaint : " + cpFeedbackDTO.getPolicyNo() + " : "
							+ cpFeedbackDTO.getServType());
				}
				body = ("Dear CSD Team,\nQuery/complains : Details:" + feedbackNote);
				
				String toUser = SecurityLibrary.getFullLoggedInUser().getVemail();
				String subUser = "";
				String bodyUser = "";
				String subUser1 = "";
				String bodyUser1 = "";
				
				if (cpFeedbackDTO.getFeedbackType().equalsIgnoreCase("f")) {
				subUser = "Query Registration - SALAMA Easy Life Portal";
				bodyUser = "Dear" + " " + custName + "\n"
						+ "Thank you for reaching out to us." + "\n"
						+ "Your query ID Number:" + " "
						+ cpFeedbackDTO.getComplaintNo() + " "
						+ "has been registered." + "\n"
						+ "Our Customer Complaint Resolution Team will be getting in touch with you within the next 48 hours." + "\n"
						+ "If you have concerns or further questions regarding this email, please do not hesitate to call us on SALAMA and one of our Customer Service agents will assist you." + "\n"
						+ "Sincerely," + "\n"
						+ "TEAM SALAMA";
				
				subUser1 = "Query Registration - SALAMA Easy Life Portal";
				bodyUser1 = "Dear" + " " + "Team" + "\n"
						+ "Our Customer raised query in the customer portal" + "\n"
						+ "Query ID Number:" + " "
						+ cpFeedbackDTO.getComplaintNo() + " "
						+ "and kindly check." + "\n"
						+ "Sincerely," + "\n"
						+ "TEAM SALAMA";
				
			} else {
				subUser ="Complaint Registration - SALAMA Easy Life Portal";
				bodyUser = "Dear" + " " + custName + "\n"
						+ "Thank you for reaching out to us." + "\n"
						+ "Your Complaint ID Number:" + " "
						+ cpFeedbackDTO.getComplaintNo() + " "
						+ "has been registered." + "\n"
						+ "Our Customer Complaint Resolution Team will be getting in touch with you within the next 48 hours." + "\n"
						+ "If you have concerns or further questions regarding this email, please do not hesitate to call us on SALAMA and one of our Customer Service agents will assist you." + "\n"
						+ "Sincerely," + "\n"
						+ "TEAM SALAMA";
				
				subUser1 = "Complaint Registration - SALAMA Easy Life Portal";
				bodyUser1 = "Dear" + " " + "Team" + "\n"
						+ "Our Customer raised complaint in the customer portal" + "\n"
						+ "Complaint ID Number:" + " "
						+ cpFeedbackDTO.getComplaintNo() + " "
						+ "and kindly check." + "\n"
						+ "Sincerely," + "\n"
						+ "TEAM SALAMA";
				
			}

				try {
					mailService.sendMail(SecurityLibrary.getFullLoggedInUser().getVemail(), subUser, bodyUser+"");
					
					List<CpUserInfo> cpUsersByGroup = null;
					cpUsersByGroup = new ArrayList<CpUserInfo>();
					cpUsersByGroup = cpUserInfoDAO.getCpUsersByGroup("B");
					if(cpUsersByGroup.size()>0) {
					for(CpUserInfo obj : cpUsersByGroup) {
						obj.getVemail();
						mailService.sendMail(obj.getVemail(), subUser1, bodyUser1+"");	
					   }
				    }
					
					//appMailer.sendMail(to, subject, body);
					//appMailer.sendMail(toUser, subUser, bodyUser);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (cpFeedbackDTO.getFeedbackType().equalsIgnoreCase("f")) {
					if(locale.toString().equalsIgnoreCase("ar")) {
						returnmessage="معرف الاستعلام الخاص بك هو:"+ cpFeedbackDTO.getComplaintNo() + "تم الحفظ بنجاح";
						//message = new FacesMessage(FacesMessage.SEVERITY_INFO, "رسالة", "معرف الاستعلام الخاص بك هو:"+ cpFeedbackDTO.getComplaintNo() + "تم الحفظ بنجاح");
					}else {
						returnmessage="Your Query id is :"+ cpFeedbackDTO.getComplaintNo() +" " + "successfully saved.";
						//message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "Your Query id is :"+ cpFeedbackDTO.getComplaintNo() +" " + "successfully saved.");
					}
					//PrimeFaces.current().dialog().showMessageDynamic(message);
					PrimeFaces.current().executeScript("PF('dlg3').show()");
				} else if (cpFeedbackDTO.getFeedbackType().equalsIgnoreCase("c")) {
					if(locale.toString().equalsIgnoreCase("ar")) {
						returnmessage="معرف الشكوى الخاص بك هو:" + cpFeedbackDTO.getComplaintNo() + "تم الحفظ بنجاح";
						//message = new FacesMessage(FacesMessage.SEVERITY_INFO, "رسالة", "معرف الشكوى الخاص بك هو:" + cpFeedbackDTO.getComplaintNo() + "تم الحفظ بنجاح");
					}else {
						returnmessage="Your Complaint id is :" + cpFeedbackDTO.getComplaintNo() + "successfully saved.";
						//message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "Your Complaint id is :" + cpFeedbackDTO.getComplaintNo() + "successfully saved.");
					}
					//PrimeFaces.current().dialog().showMessageDynamic(message);
					PrimeFaces.current().executeScript("PF('dlg3').show()");
				}
				if(cpFeedbackDTO.getComplaintNo()!=null) {	
				}							
				setSuccessRenderMsg(true);
				clearFeedbackValues();
				getallFeedbackList();
			}else {
				if(locale.toString().equalsIgnoreCase("ar")) {
					message = new FacesMessage(FacesMessage.SEVERITY_INFO, "رسالة", "ملاحظات فشل التسجيل حاول مرة أخرى في وقت لاحق.");
				}else {
					message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "Feeback Registartion Failed try again later.");
				}
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}

		}else {
			if(locale.toString().equalsIgnoreCase("ar")) {
				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "رسالة", "ايرجى التحقق مما إذا تم إدخال جميع الحقول الإلزامية أم لا");
			}else {
				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message", "Please check whether all the mandatory fields are entered or not");
			}
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}	
	}

	public void btnSaveFeedback() {
		if ((BSLUtils.isNotNull(feedbackRating)) && ((feedbackRating == null) || (feedbackRating.isEmpty())
				|| (feedbackRating.equals("")) || (feedbackRating.equalsIgnoreCase("")))) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
					"Please provide rating.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
			return;
		}

		if ((BSLUtils.isNotNull(csdCall)) && (csdCall.equalsIgnoreCase("Y"))) {
			boolean flag = false;
			if (BSLUtils.isNull(callTime)) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
						"Please Provide call time.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				flag = true;
			}
			if (BSLUtils.isNull(callTimeDay)) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
						"Please Provide call time Day.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				flag = true;
			}
			if (flag)
				return;
		}
		CpFeedbackDTO cpFeedbackDTO = new CpFeedbackDTO();
		PasswordRulesDTO pRulesDTO = feedbackBL.getCpSettingDto();

		logger.info("Enter into Feed back List:::::::::::::::::");
		saveComplaintNo = pRulesDTO.getvCompPrefix();
		int autoGen = feedbackBL.getSequenceNoCount() + 1;

		cpFeedbackDTO.setComplaintNo(saveComplaintNo + "0" + autoGen);
		cpFeedbackDTO.setUserId(SecurityLibrary.getFullLoggedInUser().getVuserName());
		cpFeedbackDTO.setCompDate(new Date());
		cpFeedbackDTO.setFeedbackType(feedbackType.toUpperCase());
		cpFeedbackDTO.setPolicyNo(savePolNo);
		cpFeedbackDTO.setServType(serviceType);
		cpFeedbackDTO.setFeedbackNote(feedbackNote);
		cpFeedbackDTO.setCsdCall(csdCall.toUpperCase());

		if (BSLUtils.isNotNull(callTime)) {
			String tmpCallTime;
			if (callTime.getHours() < 12) {
				tmpCallTime = callTime.getHours() + ":" + callTime.getMinutes() + " AM";
			} else
				tmpCallTime = "0" + (callTime.getHours() - 12) + ":" + callTime.getMinutes() + " PM";
			cpFeedbackDTO.setCallTime(tmpCallTime);
			cpFeedbackDTO.setCalltimeDay(callTimeDay);
		}
		if (BSLUtils.isNotNull(feedbackRating))
			cpFeedbackDTO.setFeedRate(feedbackRating.toUpperCase());
		cpFeedbackDTO.setFeedStatus("OPEN");
		cpFeedbackDTO.setLastupdDate(cpFeedbackDTO.getCompDate());
		cpFeedbackDTO.setLastupdUser(cpFeedbackDTO.getUserId());
		cpFeedbackDTO.setLastupdProg("POST FEEDBACK");
		boolean status = feedbackBL.saveFeedback(cpFeedbackDTO);
		logger.info("Size of List is :::::::::::::::::::" + cpCustomerDetailList.size());
		String custName;
		if (cpCustomerDetailList.size() != 0) {
			custName =  SecurityLibrary.getFullLoggedInUser().getVuserName();
			//custName = ((CpCustomerDetailDTO) cpCustomerDetailList.get(0)).getVcustName();
			logger.info("&&&&&&&&&&&&&&&&" + custName);
		} else {
			custName = "Customer";
		}
		if (status) {
			to = pRulesDTO.getvCsdEmail();
			if (feedbackType.equalsIgnoreCase("F")) {
				subject = ("EasyLife : Query : " + cpFeedbackDTO.getPolicyNo() + " : " + cpFeedbackDTO.getServType());
			} else {
				subject = ("EasyLife : Complaint : " + cpFeedbackDTO.getPolicyNo() + " : "
						+ cpFeedbackDTO.getServType());
			}
			body = ("Dear CSD Team,\nQuery/complains : Details:" + feedbackNote);
			
			String toUser = SecurityLibrary.getFullLoggedInUser().getVemail();
			String subUser = "";
			String bodyUser = "";
			
			if (cpFeedbackDTO.getFeedbackType().equalsIgnoreCase("f")) {
			subUser = "Query Registration - SALAMA Easy Life Portal";
			bodyUser = "Dear" + " " + custName + "\n"
					+ "Thank you for reaching out to us." + "\n"
					+ "Your query ID Number:" + " "
					+ cpFeedbackDTO.getComplaintNo() + " "
					+ "has been registered." + "\n"
					+ "Our Customer Complaint Resolution Team will be getting in touch with you within the next 48 hours." + "\n"
					+ "If you have concerns or further questions regarding this email, please do not hesitate to call us on SALAMA and one of our Customer Service agents will assist you." + "\n"
					+ "Sincerely," + "\n"
					+ "TEAM Salama";
		} else {
			subUser ="Complaint Registration - SALAMA Easy Life Portal";
			bodyUser = "Dear" + " " + custName + "\n"
					+ "Thank you for reaching out to us." + "\n"
					+ "Your Complaint ID Number:" + " "
					+ cpFeedbackDTO.getComplaintNo() + " "
					+ "has been registered." + "\n"
					+ "Our Customer Complaint Resolution Team will be getting in touch with you within the next 48 hours." + "\n"
					+ "If you have concerns or further questions regarding this email, please do not hesitate to call us on SALAMA and one of our Customer Service agents will assist you." + "\n"
					+ "Sincerely," + "\n"
					+ "TEAM SALAMA";
		}

			try {
				mailService.sendMail(SecurityLibrary.getFullLoggedInUser().getVemail(), subUser, bodyUser+"");
				//appMailer.sendMail(to, subject, body);
				//appMailer.sendMail(toUser, subUser, bodyUser);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (cpFeedbackDTO.getFeedbackType().equalsIgnoreCase("f")) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
						"Your Query id is :"+ cpFeedbackDTO.getComplaintNo() + "successfully saved.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			} else if (cpFeedbackDTO.getFeedbackType().equalsIgnoreCase("c")) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
						"Your Complaint id is :" + cpFeedbackDTO.getComplaintNo() + "successfully saved.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
			
			if(cpFeedbackDTO.getComplaintNo()!=null) {
				
			}
						
			setSuccessRenderMsg(true);
			clearFeedbackValues();
			getallFeedbackList();
		}
	}

	private void clearReplyValues() {
		replyNote = null;
		replyRate = null;
		closeFeedback = null;
	}

	private void clearFeedbackValues() {
		feedbackType = null;
		savePolNo = null;
		serviceType = null;
		feedbackNote = null;
		agentCall = null;
		callTime = null;
		feedbackRating = null;
		callTimeDay = null;
	}

	public void btnSearchFeedback() {
		if (toDate != null) {
			toDate = DateUtil.sumTimeToDate(toDate, 23, 59, 59);
			System.out.println(toDate.toString());
		}
		if (BSLUtils.isNull(busFeedStatus)&&busFeedStatus.equals(""))
			busFeedStatus = null;
		Object[] values = { busUserdId, busFeedStatus, fromDate, toDate };
		if ((busUserdId.equalsIgnoreCase("")) && (fromDate != null) && (BSLUtils.isNotNull(busFeedStatus))) {
			values = new Object[] { busFeedStatus, fromDate, toDate };
		} else if ((busUserdId.equalsIgnoreCase("")) && (fromDate != null) && (BSLUtils.isNull(busFeedStatus))) {
			values = new Object[] { fromDate, toDate };
		} else if ((!busUserdId.equalsIgnoreCase("")) && (fromDate != null) && (BSLUtils.isNotNull(busFeedStatus))) {
			values = new Object[] { "%" + busUserdId + "%", busFeedStatus, fromDate, toDate };
		} else if ((!busUserdId.equalsIgnoreCase("")) && (fromDate != null) && (BSLUtils.isNull(busFeedStatus))) {
			values = new Object[] { "%" + busUserdId + "%", fromDate, toDate };
		} else if ((!busUserdId.equalsIgnoreCase("")) && (fromDate == null) && (BSLUtils.isNotNull(busFeedStatus))) {
			values = new Object[] { "%" + busUserdId + "%", busFeedStatus, toDate };
		} else if ((!busUserdId.equalsIgnoreCase("")) && (fromDate == null) && (BSLUtils.isNull(busFeedStatus))) {
			values = new Object[] { "%" + busUserdId + "%", toDate };

		} else if ((busUserdId.equalsIgnoreCase("")) && (fromDate == null) && (BSLUtils.isNotNull(busFeedStatus))) {
			values = new Object[] { busFeedStatus, toDate };

		} else if ((busUserdId.equalsIgnoreCase("")) && (fromDate == null) && (BSLUtils.isNull(busFeedStatus))) {
			values = new Object[] { toDate };
		}
		if ((busUserdId == null) && (fromDate == null) && (toDate == null) && (busFeedStatus == null)) {
			return;
		}
		feedBusUserList = feedbackBL.searchFeedbackList(values);
		logger.info("List all search feedback list size: " + feedBusUserList.size());
	}

	/**
	 * <i>To upload File to FTP server REST API call</i>
	 * 
	 * @param event
	 */
	/*
	 * public String uploadFileFTP(FileUploadEvent event) {
	 * 
	 * if (event.getFile().getSize() > 0) {
	 * 
	 * logger.info("Upload FTP File details"); logger.info("File Name: " +
	 * event.getFile().getFileName());
	 * 
	 * this.uploadedFile = event.getFile(); this.ftpFileName =
	 * event.getFile().getFileName();
	 * 
	 * //Accesstoken from Auth server this.jsonString =
	 * Oauth2Utils.getAccessToken();
	 * 
	 * FacesContext.getCurrentInstance().addMessage(null, new
	 * FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
	 * ftpFileName+"File upload successfully.")); return this.ftpFileName; } else {
	 * logger.error("Uploaded File is Empty !"); }
	 * 
	 * return null; }
	 */
	/**
	 * <i>To process the uploaded FTP file </i>
	 */
	public void processFileFTP() {
		
		final String accessTokenLabel = "access_token";
		
		String uploadFileUrl = AppSettingURL.UPLOAD_FILE_URL;

		try {
			
			if (uploadedFile.getContents() != null&&BSLUtils.isNotNull(jsonString)) {
		
				/* Http Headers */
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.MULTIPART_FORM_DATA);	
				
				/* Process access_token json string */
				String access_token = JSONUtils.getJSONString(jsonString, accessTokenLabel);
				logger.info("oauth2 token :"+access_token);
				
				/* Add auth header with access_token */
				headers.add("Authorization", "Bearer "+access_token);
				
				MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
				body.add("file", new MultipartInputStreamFileResource(uploadedFile.getInputstream(),uploadedFile.getFileName()));

				/* Rest Template */
				RestTemplate restTemplate = RestTemplateUtils.restTemplate();
				
				/* Entity with body & headers */
				HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
				
				logger.info("FTP File Upload Url : "+uploadFileUrl);
				uploadFileUrl = uploadFileUrl+"?queryNumber="+getFeedbackDTO.getComplaintNo()+"";
				ResponseEntity<String> response = restTemplate.postForEntity(uploadFileUrl, requestEntity, String.class);
				
				logger.info("FTP File response :" + response);
				this.ftpFileName = "";
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Uncaught exception while uploading file ftp server :" + e.getCause());
		}

	}
	
	/**
	 * To process download FTP file
	 */
	public void openFile() {
		
		final String accessTokenLabel = AppSettingURL.OAUTH2_ACCESS_TOKEN_LABEL;
		String downloadFileUrl = AppSettingURL.DOWNLOAD_FILE_URL;
		//String downloadFileUrl = "http://localhost:8383/cpapi/file/testFile";

		try {

			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext ec = context.getExternalContext();
			HttpServletResponse response = (HttpServletResponse) ec.getResponse();
			String fileName = ec.getRequestParameterMap().get("fileName");
			//FeedbackReplyDocsDTO replyDocs = null;

			
			/*
			 * for (FeedbackReplyDTO replyDto : replyList) {
			 * 
			 * //String fileName = ec.getRequestParameterMap().get("fileName");
			 * 
			 * if (replyDto.getFeedbackReplyDocsDTO() != null &&
			 * replyDto.getFeedbackReplyDocsDTO().getFileName().equals(fileName)) {
			 * replyDocs = replyDto.getFeedbackReplyDocsDTO(); break; }
			 * 
			 * }
			 */
			 
			String accessToken = Oauth2Utils.getAccessToken();
			String access_token = JSONUtils.getJSONString(accessToken, accessTokenLabel);
			logger.info("oauth2 token :"+access_token);
			
			/* Http Headers */
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);	
			
			/* Add auth header with access_token */
			headers.add("Authorization", "Bearer "+access_token);
			
			/* Rest Template */
			RestTemplate restTemplate = RestTemplateUtils.restTemplate();
			
			/* Entity with body & headers */
			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(headers);
			
			String fileDetails = "fileName="+fileName+"&queryNumber="+getFeedbackDTO.getComplaintNo()+"";
			final String fileDownloadUrl = String.join("?", downloadFileUrl, fileDetails);
			
			logger.info("FTP file download url: "+fileDownloadUrl);
			ResponseEntity<byte[]> responseEntity = restTemplate.exchange(fileDownloadUrl, HttpMethod.GET, requestEntity, byte[].class);

		    // Response Headers
			response.setHeader("Content-type", "application/pdf");
			response.setContentLength(responseEntity.getBody().length);
			response.setHeader("Content-Disposition", "filename=" + fileName);
			
            
			OutputStream outputstream = null;
			outputstream = response.getOutputStream();
			outputstream.write(responseEntity.getBody());
			outputstream.flush();
			outputstream.close();
			context.responseComplete();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception caught while downloading file: {}",e.getCause());
		}

	}
	

	public void setFeedbackBL(FeedbackBLImpl feedbackBL) {
		this.feedbackBL = feedbackBL;
	}

	public List<CpFeedbackDTO> getFeedbackList() {
		return feedbackList;
	}

	public void setFeedbackList(List<CpFeedbackDTO> feedbackList) {
		this.feedbackList = feedbackList;
	}

	public List<FeedbackReplyDTO> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<FeedbackReplyDTO> replyList) {
		this.replyList = replyList;
	}

	public boolean isReplyVisibleFlag() {
		return replyVisibleFlag;
	}

	public void setReplyVisibleFlag(boolean replyVisibleFlag) {
		this.replyVisibleFlag = replyVisibleFlag;
	}

	public Map<String, String> getReplyRateMap() {
		return replyRateMap;
	}

	public void setReplyRateMap(Map<String, String> replyRateMap) {
		this.replyRateMap = replyRateMap;
	}

	public String getReplyNote() {
		return replyNote;
	}

	public void setReplyNote(String replyNote) {
		this.replyNote = replyNote;
	}

	public String getFeedbackType() {
		return feedbackType;
	}

	public void setFeedbackType(String feedbackType) {
		this.feedbackType = feedbackType;
	}

	public String getSavePolNo() {
		return savePolNo;
	}

	public void setSavePolNo(String savePolNo) {
		this.savePolNo = savePolNo;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getFeedbackRating() {
		return feedbackRating;
	}

	public void setFeedbackRating(String feedbackRating) {
		this.feedbackRating = feedbackRating;
	}

	public String getSaveComplaintNo() {
		return saveComplaintNo;
	}

	public void setSaveComplaintNo(String saveComplaintNo) {
		this.saveComplaintNo = saveComplaintNo;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getFeedbackNote() {
		return feedbackNote;
	}

	public void setFeedbackNote(String feedbackNote) {
		this.feedbackNote = feedbackNote;
	}

	public String getAgentCall() {
		return agentCall;
	}

	public void setAgentCall(String agentCall) {
		this.agentCall = agentCall;
	}

	public String getCsdCall() {
		return csdCall;
	}

	public void setCsdCall(String csdCall) {
		this.csdCall = csdCall;
	}

	public Date getCallTime() {
		return callTime;
	}

	public void setCallTime(Date callTime) {
		this.callTime = callTime;
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public String getReplyRate() {
		return replyRate;
	}

	public void setReplyRate(String replyRate) {
		this.replyRate = replyRate;
	}

	public String getCloseFeedback() {
		return closeFeedback;
	}

	public void setCloseFeedback(String closeFeedback) {
		this.closeFeedback = closeFeedback;
	}

	public boolean isReplyStatus() {
		return replyStatus;
	}

	public void setReplyStatus(boolean replyStatus) {
		this.replyStatus = replyStatus;
	}

	public Date getNow() {
		return now;
	}

	public void setNow(Date now) {
		this.now = now;
	}

	public boolean isSuccessReplyFlag() {
		return successReplyFlag;
	}

	public void setSuccessReplyFlag(boolean successReplyFlag) {
		this.successReplyFlag = successReplyFlag;
	}

	public Map<String, String> getReplySaveMap() {
		return replySaveMap;
	}

	public void setReplySaveMap(Map<String, String> replySaveMap) {
		this.replySaveMap = replySaveMap;
	}

	public String getClosedCompMessage() {
		return closedCompMessage;
	}

	public void setClosedCompMessage(String closedCompMessage) {
		this.closedCompMessage = closedCompMessage;
	}

	public CpFeedbackDTO getGetFeedbackDTO() {
		return getFeedbackDTO;
	}

	public void setGetFeedbackDTO(CpFeedbackDTO getFeedbackDTO) {
		this.getFeedbackDTO = getFeedbackDTO;
	}

	public String getBusUserdId() {
		return busUserdId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public String getBusFeedStatus() {
		return busFeedStatus;
	}

	public void setBusUserdId(String busUserdId) {
		this.busUserdId = busUserdId;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public void setBusFeedStatus(String busFeedStatus) {
		this.busFeedStatus = busFeedStatus;
	}

	public List<CpFeedbackDTO> getFeedBusUserList() {
		return feedBusUserList;
	}

	public void setFeedBusUserList(List<CpFeedbackDTO> feedBusUserList) {
		this.feedBusUserList = feedBusUserList;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public boolean isLinkFlag() {
		return linkFlag;
	}

	public void setLinkFlag(boolean linkFlag) {
		this.linkFlag = linkFlag;
	}

	public ApplicationMailer getAppMailer() {
		return appMailer;
	}

	public void setAppMailer(ApplicationMailer appMailer) {
		this.appMailer = appMailer;
	}

	public CpUserInfoDTO getCpUserInfoDTO() {
		return cpUserInfoDTO;
	}

	public void setCpUserInfoDTO(CpUserInfoDTO cpUserInfoDTO) {
		this.cpUserInfoDTO = cpUserInfoDTO;
	}

	public String getTo() {
		return to;
	}

	public String getSubject() {
		return subject;
	}

	public String getBody() {
		return body;
	}

	public String getSuccessMsg() {
		return successMsg;
	}

	public boolean isSuccessRenderMsg() {
		return successRenderMsg;
	}

	public String getReplyBy() {
		return replyBy;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}

	public void setSuccessRenderMsg(boolean successRenderMsg) {
		this.successRenderMsg = successRenderMsg;
	}

	public void setReplyBy(String replyBy) {
		this.replyBy = replyBy;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public int getCutRefNo() {
		return cutRefNo;
	}

	public void setCutRefNo(int cutRefNo) {
		this.cutRefNo = cutRefNo;
	}

	public String getBodyMsg() {
		return bodyMsg;
	}

	public void setBodyMsg(String bodyMsg) {
		this.bodyMsg = bodyMsg;
	}

	public List<CpServiceTypeDTO> getListServiceType() {
		return listServiceType;
	}

	public void setListServiceType(List<CpServiceTypeDTO> listServiceType) {
		this.listServiceType = listServiceType;
	}

	public boolean isRenderRatePanel() {
		return renderRatePanel;
	}

	public void setRenderRatePanel(boolean renderRatePanel) {
		this.renderRatePanel = renderRatePanel;
	}

	public boolean isRenderRatingCondition() {
		return renderRatingCondition;
	}

	public void setRenderRatingCondition(boolean renderRatingCondition) {
		this.renderRatingCondition = renderRatingCondition;
	}

	public String getCallTimeDay() {
		return callTimeDay;
	}

	public void setCallTimeDay(String callTimeDay) {
		this.callTimeDay = callTimeDay;
	}

	public boolean isCallRequired() {
		return callRequired;
	}
	public void setCallRequired(boolean callRequired) {
		this.callRequired = callRequired;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public String getFtpFileName() {
		return ftpFileName;
	}

	public void setFtpFileName(String ftpFileName) {
		this.ftpFileName = ftpFileName;
	}

	public String getAccesstoken() {
		return accesstoken;
	}

	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	public String getSelectedDownloadedFile() {
		return selectedDownloadedFile;
	}

	public void setSelectedDownloadedFile(String selectedDownloadedFile) {
		this.selectedDownloadedFile = selectedDownloadedFile;
	}

	public boolean isCheckbox() {
		return checkbox;
	}

	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}

	public List<CpFeedbackDTO> getFeedBusUserList1() {
		return feedBusUserList1;
	}

	public void setFeedBusUserList1(List<CpFeedbackDTO> feedBusUserList1) {
		this.feedBusUserList1 = feedBusUserList1;
	}

	public boolean isHideReply() {
		return hideReply;
	}

	public void setHideReply(boolean hideReply) {
		this.hideReply = hideReply;
	}
	
	public CpFeedbackDTO getSelectfeedbacklist() {
		return selectfeedbacklist;
	}

	public void setSelectfeedbacklist(CpFeedbackDTO selectfeedbacklist) {
		this.selectfeedbacklist = selectfeedbacklist;
	}
	
	public DonutChartModel getDonutModel() {
		return donutModel;
	}

	public void setDonutModel(DonutChartModel donutModel) {
		this.donutModel = donutModel;
	}

	public String getApplicationdisplayname() {
		return applicationdisplayname;
	}

	public void setApplicationdisplayname(String applicationdisplayname) {
		this.applicationdisplayname = applicationdisplayname;
	}

}
