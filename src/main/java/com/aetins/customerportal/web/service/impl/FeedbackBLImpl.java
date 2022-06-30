package com.aetins.customerportal.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.dao.CpUserInfoDAO;
import com.aetins.customerportal.web.dao.FeedbackDAO;
import com.aetins.customerportal.web.dao.FeedbackReplyDAO;
import com.aetins.customerportal.web.dao.FeedbackReplyDocsDAO;
import com.aetins.customerportal.web.dao.PasswordRulesDAO;
import com.aetins.customerportal.web.dao.ServiceTypeDAO;
import com.aetins.customerportal.web.dto.CpFeedbackDTO;
import com.aetins.customerportal.web.dto.CpServiceTypeDTO;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.dto.FeedbackReplyDTO;
import com.aetins.customerportal.web.dto.FeedbackReplyDocsDTO;
import com.aetins.customerportal.web.dto.PasswordRulesDTO;
import com.aetins.customerportal.web.entity.CPFeedbackReplyUploadDocs;
import com.aetins.customerportal.web.entity.CpFeedback;
import com.aetins.customerportal.web.entity.CpFeedbackReply;
import com.aetins.customerportal.web.entity.CpServiceType;
import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.entity.PasswordRules;
import com.aetins.customerportal.web.service.FeedbackBL;
import com.aetins.customerportal.web.utils.BSLUtils;

/**
 * This business logic class for all Feedback management 30/01/2017
 * 
 * @author Viswakarthick
 */


@Service
public class FeedbackBLImpl implements FeedbackBL {
	private static final Logger logger = Logger.getLogger(FeedbackBLImpl.class);
	
	@Autowired
	FeedbackDAO feedbackDAO;
	
	@Autowired
	FeedbackReplyDAO feedbackReplyDAO;
	
	@Autowired
	CpUserInfoDAO cpUserInfoDAO ;
	
	@Autowired
	PasswordRulesDAO passwordRulesDao;
	
	@Autowired
	ServiceTypeDAO serviceTypeDAO;
	
	@Autowired
	FeedbackReplyDocsDAO feedBackReplyDoc;
	
	

	public void setServiceTypeDAO(ServiceTypeDAO serviceTypeDAO) {
		this.serviceTypeDAO = serviceTypeDAO;
	}

	public void setFeedbackDAO(FeedbackDAO feedbackDAO) {
		this.feedbackDAO = feedbackDAO;
	}	
	
	public void setFeedbackReplyDAO(FeedbackReplyDAO feedbackReplyDAO) {
		this.feedbackReplyDAO = feedbackReplyDAO;
	}

	public void setCpUserInfoDAO(CpUserInfoDAO cpUserInfoDAO) {
		this.cpUserInfoDAO = cpUserInfoDAO;
	}

	

	public void setPasswordRulesDao(PasswordRulesDAO passwordRulesDao) {
		this.passwordRulesDao = passwordRulesDao;
	}

	@Override
	public List<CpFeedbackDTO> listAllFeedbacks(String userId) {
		List<CpFeedbackDTO> feedbackList = new ArrayList<CpFeedbackDTO>();
		logger.info("BLImpl:List all user feedback or complaint retreiving started");
		List<CpFeedback> cpFeedbacks = feedbackDAO.listAllFeedbacks(userId);
		for (CpFeedback cpFeedback : cpFeedbacks) {
			CpFeedbackDTO cpFeedbackDTO = EntityTocpFeedbackDTO(cpFeedback);
			
			feedbackList.add(cpFeedbackDTO);
		}
		return feedbackList;
	}

	@Override
	public CpFeedbackDTO getFeedbackInfo(String feedbackNo) {		
		logger.info("BLImpl: user feedback or complaint retreiving started");
		List<CpFeedback> cpFeedbacks = feedbackDAO.getFeedbackInfo(feedbackNo);
		CpFeedbackDTO cpFeedbackDTO = new CpFeedbackDTO();
		for (CpFeedback cpFeedback : cpFeedbacks) {
			cpFeedbackDTO=EntityTocpFeedbackDTO(cpFeedback);		
		}
		return cpFeedbackDTO;
	}

	@Override
	public List<FeedbackReplyDTO> listAllReplys( int feedId) {
		logger.info("BLImpl:List all user Replies retreiving started");
		List<FeedbackReplyDTO> feedbackReplyList = new ArrayList<FeedbackReplyDTO>();		
		List<CpFeedbackReply> cpFeedbackReplies = feedbackReplyDAO.listAllReplys(feedId);
		
		for (CpFeedbackReply cpFeedbackReply : cpFeedbackReplies) {
			FeedbackReplyDTO reply = EntityToFeedbackReplyDTO(cpFeedbackReply);
			
			//File upload implementation
			CPFeedbackReplyUploadDocs replyDocsByRoleId = feedBackReplyDoc.getReplyDocsByRoleId(reply.getReplyId());

			if (replyDocsByRoleId != null)
				reply.setFileUpload(Boolean.TRUE);
			else
				reply.setFileUpload(Boolean.FALSE);
			
			FeedbackReplyDocsDTO entityToFeedbackReplyDocsDTO = EntityToFeedbackReplyDocsDTO(replyDocsByRoleId);
			reply.setFeedbackReplyDocsDTO(entityToFeedbackReplyDocsDTO);
			
			feedbackReplyList.add(reply);
		}
		return feedbackReplyList;
	}

	
	public CpFeedbackDTO EntityTocpFeedbackDTO(CpFeedback feedback){
		logger.info("BLImpl:converting feedback entity to DTO");
		CpFeedbackDTO cpFeedbackDTO = new CpFeedbackDTO();
		cpFeedbackDTO.setFeedbackId(feedback.getnFeedbackId());
		cpFeedbackDTO.setComplaintNo(feedback.getvComplaintNo());
		cpFeedbackDTO.setUserId(feedback.getvUserId());
		cpFeedbackDTO.setCompDate(feedback.getdCompDate());
		cpFeedbackDTO.setFeedbackType(feedback.getvFeedbackType().toLowerCase());
		cpFeedbackDTO.setPolicyNo(feedback.getvPolicyNo());
		if(feedback.getvServType()!=null)
			cpFeedbackDTO.setServType(feedback.getvServType());
		if(feedback.getvFeedbackNote()!=null)
			cpFeedbackDTO.setFeedbackNote(feedback.getvFeedbackNote());
		if(feedback.getvAgentCall()!=null)
			cpFeedbackDTO.setAgentCall(feedback.getvAgentCall().toLowerCase());
		if(feedback.getvCsdCall()!=null)
			cpFeedbackDTO.setCsdCall(feedback.getvCsdCall().toLowerCase());
		if(feedback.getvCallTime()!=null)
			cpFeedbackDTO.setCallTime(feedback.getvCallTime());
		if(feedback.getvFeedRate()!=null)
			cpFeedbackDTO.setFeedRate(feedback.getvFeedRate());
		if(feedback.getvFeedStatus()!=null)
			cpFeedbackDTO.setFeedStatus(feedback.getvFeedStatus());
		if(feedback.getVlastupdUser()!=null)
			cpFeedbackDTO.setLastupdUser(feedback.getVlastupdUser());
		if(feedback.getVlastupdDate()!=null)
			cpFeedbackDTO.setLastupdDate(feedback.getVlastupdDate());
		if(feedback.getVlastupdProg()!=null)
			cpFeedbackDTO.setLastupdProg(feedback.getVlastupdProg());
		if(feedback.getdClosedDate()!=null)
			cpFeedbackDTO.setClosedDate(feedback.getdClosedDate());
		if(feedback.getvCalltimeDay()!=null)
			cpFeedbackDTO.setCalltimeDay(feedback.getvCalltimeDay());
		if(feedback.getvRemark()!=null)
			cpFeedbackDTO.setRemark(feedback.getvRemark());
		return cpFeedbackDTO;
	}
	
	public FeedbackReplyDTO EntityToFeedbackReplyDTO(CpFeedbackReply cpFeedbackReply) {
		logger.info("BLImpl:converting feedbackReply entity to DTO");
		FeedbackReplyDTO feedbackReplyDTO = new FeedbackReplyDTO();
		feedbackReplyDTO.setReplyId(cpFeedbackReply.getnReplyId());
			CpFeedbackDTO feedId= new CpFeedbackDTO();
			feedId.setFeedbackId(cpFeedbackReply.getnMapFeedbackId().getnFeedbackId());
			feedId.setComplaintNo(cpFeedbackReply.getnMapFeedbackId().getvComplaintNo());
			feedId.setUserId(cpFeedbackReply.getnMapFeedbackId().getvUserId());
			feedId.setCompDate(cpFeedbackReply.getnMapFeedbackId().getdCompDate());
			feedId.setFeedbackType(cpFeedbackReply.getnMapFeedbackId().getvFeedbackType().toLowerCase());
			feedId.setPolicyNo(cpFeedbackReply.getnMapFeedbackId().getvPolicyNo());
	if (cpFeedbackReply.getnMapFeedbackId().getvServType() != null)
			feedId.setServType(cpFeedbackReply.getnMapFeedbackId().getvServType());
		if (cpFeedbackReply.getnMapFeedbackId().getvFeedbackNote() != null)
			feedId.setFeedbackNote(cpFeedbackReply.getnMapFeedbackId().getvFeedbackNote());
		if (cpFeedbackReply.getnMapFeedbackId().getvAgentCall() != null)
			feedId.setAgentCall(cpFeedbackReply.getnMapFeedbackId().getvAgentCall().toLowerCase());
		if (cpFeedbackReply.getnMapFeedbackId().getvCsdCall() != null)
			feedId.setCsdCall(cpFeedbackReply.getnMapFeedbackId().getvCsdCall().toLowerCase());
		if (cpFeedbackReply.getnMapFeedbackId().getvCallTime() != null)
			feedId.setCallTime(cpFeedbackReply.getnMapFeedbackId().getvCallTime());
		if (cpFeedbackReply.getnMapFeedbackId().getvFeedRate() != null)
			feedId.setFeedRate(cpFeedbackReply.getnMapFeedbackId().getvFeedRate());
		if (cpFeedbackReply.getnMapFeedbackId().getvFeedStatus() != null)
			feedId.setFeedStatus(cpFeedbackReply.getnMapFeedbackId().getvFeedStatus());
		if (cpFeedbackReply.getnMapFeedbackId().getVlastupdUser() != null)
			feedId.setLastupdUser(cpFeedbackReply.getnMapFeedbackId().getVlastupdUser());
		if (cpFeedbackReply.getnMapFeedbackId().getVlastupdDate() != null)
			feedId.setLastupdDate(cpFeedbackReply.getnMapFeedbackId().getVlastupdDate());
		if (cpFeedbackReply.getnMapFeedbackId().getVlastupdProg() != null)
			feedId.setLastupdProg(cpFeedbackReply.getnMapFeedbackId().getVlastupdProg());
		if (cpFeedbackReply.getnMapFeedbackId().getdClosedDate() != null)
			feedId.setClosedDate(cpFeedbackReply.getnMapFeedbackId().getdClosedDate());
		if (cpFeedbackReply.getnMapFeedbackId().getvCalltimeDay() != null)
			feedId.setCalltimeDay(cpFeedbackReply.getnMapFeedbackId().getvCalltimeDay());
		
			//feedId.setFeedStatus(cpFeedbackReply.getnMapFeedbackId().getvFeedStatus());
		feedbackReplyDTO.setMapFeedbackId(feedId);
		feedbackReplyDTO.setUserType(cpFeedbackReply.getvUserType().toLowerCase());
		feedbackReplyDTO.setUserId(cpFeedbackReply.getvUserId());
		feedbackReplyDTO.setReplyDate(cpFeedbackReply.getdReplyDate());
		if (cpFeedbackReply.getvReplyNote() != null)
			feedbackReplyDTO.setReplyNote(cpFeedbackReply.getvReplyNote());
		if (cpFeedbackReply.getvReplyRate() != null)
			feedbackReplyDTO.setReplyRate(cpFeedbackReply.getvReplyRate());
		if (cpFeedbackReply.getvCloseComp() != null)
			feedbackReplyDTO.setCloseComp(cpFeedbackReply.getvCloseComp().toLowerCase());
		feedbackReplyDTO.setParentRepId(cpFeedbackReply.getnParentRepId());
		if (cpFeedbackReply.getVlastupdUser() != null)
			feedbackReplyDTO.setLastupdUser(cpFeedbackReply.getVlastupdUser());
		if (cpFeedbackReply.getVlastupdDate() != null)
			feedbackReplyDTO.setLastupdDate(cpFeedbackReply.getVlastupdDate());
		if (cpFeedbackReply.getVlastupdProg() != null)
			feedbackReplyDTO.setLastupdProg(cpFeedbackReply.getVlastupdProg());
		return feedbackReplyDTO;
	}

	@Override
	public boolean saveReply(FeedbackReplyDTO feedbackReplyDTO) {
		logger.info("BLImpl:calling dao save reply for save feedback or complaint reply ");
		CpFeedbackReply cpFeedbackReply =  new CpFeedbackReply();
		CpFeedback feedId= new CpFeedback();
		feedId.setnFeedbackId(feedbackReplyDTO.getMapFeedbackId().getFeedbackId());
		cpFeedbackReply.setnMapFeedbackId(feedId);
		cpFeedbackReply.setvUserType(feedbackReplyDTO.getUserType());
		cpFeedbackReply.setvUserId(feedbackReplyDTO.getUserId());
		cpFeedbackReply.setdReplyDate(feedbackReplyDTO.getReplyDate());
		cpFeedbackReply.setvReplyNote(feedbackReplyDTO.getReplyNote());
		cpFeedbackReply.setvReplyRate(feedbackReplyDTO.getReplyRate());
		cpFeedbackReply.setvCloseComp(feedbackReplyDTO.getCloseComp());
		cpFeedbackReply.setnParentRepId(feedbackReplyDTO.getParentRepId());
		cpFeedbackReply.setVlastupdUser(feedbackReplyDTO.getLastupdUser());
		cpFeedbackReply.setVlastupdDate(feedbackReplyDTO.getLastupdDate());
		cpFeedbackReply.setVlastupdProg(feedbackReplyDTO.getLastupdProg());
		
		if (feedbackReplyDTO.getFeedbackReplyDocsDTO() != null) {

			CPFeedbackReplyUploadDocs feedBackReplyDocs = new CPFeedbackReplyUploadDocs();
			feedBackReplyDocs.setUploadDate(feedbackReplyDTO.getFeedbackReplyDocsDTO().getUploadDate());
			//feedBackReplyDocs.setData(feedbackReplyDTO.getFeedbackReplyDocsDTO().getData());
			feedBackReplyDocs.setCpFeedbackReply(cpFeedbackReply);
			feedBackReplyDocs.setFileSize(feedbackReplyDTO.getFeedbackReplyDocsDTO().getFileSize());
			feedBackReplyDocs.setFileName(feedbackReplyDTO.getFeedbackReplyDocsDTO().getFileName());
			//feedBackReplyDocs.setFileType(feedbackReplyDTO.getFeedbackReplyDocsDTO().getFileType());
			feedBackReplyDocs.setUploadedUserType(feedbackReplyDTO.getFeedbackReplyDocsDTO().getUploadedUserType());
			feedBackReplyDocs.setUploadedUserId(feedbackReplyDTO.getFeedbackReplyDocsDTO().getUploadedUserId());

			cpFeedbackReply.setFeedBackUploadDocs(feedBackReplyDocs);
		}
		
		
		return feedbackReplyDAO.saveReply(cpFeedbackReply);
	}


	@Override
	public boolean saveFeedback(CpFeedbackDTO cpFeedbackDTO) {
		// TODO Auto-generated method stub
		CpFeedback cpFeedback=new CpFeedback();		
		cpFeedback.setvComplaintNo(cpFeedbackDTO.getComplaintNo());
		cpFeedback.setvUserId(cpFeedbackDTO.getUserId());
		cpFeedback.setdCompDate(cpFeedbackDTO.getCompDate());
		cpFeedback.setvFeedbackType(cpFeedbackDTO.getFeedbackType());
		cpFeedback.setvPolicyNo(cpFeedbackDTO.getPolicyNo());
		cpFeedback.setvServType(cpFeedbackDTO.getServType());
		cpFeedback.setvFeedbackNote(cpFeedbackDTO.getFeedbackNote());
		cpFeedback.setvAgentCall(cpFeedbackDTO.getAgentCall());
		cpFeedback.setvCsdCall(cpFeedbackDTO.getCsdCall());
		cpFeedback.setvCallTime(cpFeedbackDTO.getCallTime());
		cpFeedback.setvFeedRate(cpFeedbackDTO.getFeedRate());
		cpFeedback.setvFeedStatus(cpFeedbackDTO.getFeedStatus());
		cpFeedback.setVlastupdUser(cpFeedbackDTO.getLastupdUser());
		cpFeedback.setVlastupdDate(cpFeedbackDTO.getLastupdDate());
		cpFeedback.setVlastupdProg(cpFeedbackDTO.getLastupdProg());		
		cpFeedback.setdClosedDate(cpFeedbackDTO.getClosedDate());
		cpFeedback.setvCalltimeDay(cpFeedbackDTO.getCalltimeDay());
		cpFeedback.setvRemark(cpFeedbackDTO.getRemark());
		return feedbackDAO.saveFeedback(cpFeedback);
	}


	@Override
	public boolean updateFeedbackStatus(CpFeedbackDTO cpFeedbackDTO) {
		logger.info("BLImpl:calling dao update for feedback or complaint status ");
		CpFeedback feedback=new CpFeedback();
		feedback.setnFeedbackId(cpFeedbackDTO.getFeedbackId());
		feedback.setvComplaintNo(cpFeedbackDTO.getComplaintNo());
		feedback.setvUserId(cpFeedbackDTO.getUserId());
		feedback.setdCompDate(cpFeedbackDTO.getCompDate());
		feedback.setvFeedbackType(cpFeedbackDTO.getFeedbackType());
		feedback.setvPolicyNo(cpFeedbackDTO.getPolicyNo());
		feedback.setvServType(cpFeedbackDTO.getServType());
		feedback.setvFeedbackNote(cpFeedbackDTO.getFeedbackNote());
		feedback.setvAgentCall(cpFeedbackDTO.getAgentCall());
		feedback.setvCsdCall(cpFeedbackDTO.getCsdCall());
		feedback.setvCallTime(cpFeedbackDTO.getCallTime());
		feedback.setvFeedRate(cpFeedbackDTO.getFeedRate());
		feedback.setvFeedStatus(cpFeedbackDTO.getFeedStatus());
		feedback.setVlastupdUser(cpFeedbackDTO.getLastupdUser());
		feedback.setVlastupdDate(cpFeedbackDTO.getLastupdDate());
		feedback.setVlastupdProg(cpFeedbackDTO.getLastupdProg());
		feedback.setdClosedDate(cpFeedbackDTO.getClosedDate());
		feedback.setvCalltimeDay(cpFeedbackDTO.getCalltimeDay());
		feedback.setvRemark(cpFeedbackDTO.getRemark());
		return feedbackDAO.updateFeedbackStatus(feedback);
	}

	@Override
	public List<CpFeedbackDTO> listAllFeedbacks() {
		logger.info("BLImpl:List all Feedback or Complaint ");
		List<CpFeedbackDTO> feedbackList = new ArrayList<CpFeedbackDTO>();
		List<CpFeedback> cpFeedbacks = feedbackDAO.listAllFeedbacks();
		for (CpFeedback cpFeedback : cpFeedbacks) {
			CpFeedbackDTO cpFeedbackDTO = EntityTocpFeedbackDTO(cpFeedback);
			
			feedbackList.add(cpFeedbackDTO);
		}
		return feedbackList;
	}

	@Override
	public List<CpFeedbackDTO> searchFeedbackList(Object[] values) {
		logger.info("BLImpl:Search Feedback list using property values");
		List<CpFeedbackDTO> feedbackList = new ArrayList<CpFeedbackDTO>();
		List<CpFeedback> cpFeedbacks = feedbackDAO.searchFeedbackList(values);
		for (CpFeedback cpFeedback : cpFeedbacks) {
			CpFeedbackDTO cpFeedbackDTO = EntityTocpFeedbackDTO(cpFeedback);			
			feedbackList.add(cpFeedbackDTO);
		}
		return feedbackList;
	}

	@Override
	public CpUserInfoDTO getCpUserInfo(String userName) {
		logger.info("BLImpl:Get user information ");
		CpUserInfo cpUser = cpUserInfoDAO.getCpUserInfo(userName);
		CpUserInfoDTO cpUserInfoDto = new CpUserInfoDTO();
		if (cpUser != null) {
			cpUserInfoDto.setNid(cpUser.getNid());
			cpUserInfoDto.setNcustRefNo(cpUser.getNcustRefNo());
			if (cpUser.getVgroup() != null)
				cpUserInfoDto.setVgroup(cpUser.getVgroup());
			if (cpUser.getVtitle() != null)
				cpUserInfoDto.setVtitle(cpUser.getVtitle());
			cpUserInfoDto.setVuserName(cpUser.getVuserName());
			if (cpUser.getVprefName() != null)
				cpUserInfoDto.setVprefName(cpUser.getVprefName());
			if (cpUser.getVemail() != null)
				cpUserInfoDto.setVemail(cpUser.getVemail());
		}
		return cpUserInfoDto;
	}

	@Override
	public PasswordRulesDTO getCpSettingDto() {
		
		PasswordRulesDTO dto=new PasswordRulesDTO();
		
		List<PasswordRules> passwordRules = passwordRulesDao.listPasswordRules();
			dto.setvCsdEmail(passwordRules.get(0).getvCsdEmail());
			dto.setvCompPrefix(passwordRules.get(0).getvCompPrefix());
		return dto;
	}
	
	@Override
	public List<CpServiceTypeDTO> listServiceType() {
		List<CpServiceTypeDTO> ServiceTypelist = new ArrayList<CpServiceTypeDTO>();
		List<CpServiceType> CpServiceTypeList = 
				
				serviceTypeDAO.listserviceType();
		for(int i=0; i<CpServiceTypeList.size(); i++){
			CpServiceTypeDTO cpServiceTypeDTO = new CpServiceTypeDTO();
			cpServiceTypeDTO.setvServiceTypeEn(CpServiceTypeList.get(i).getVserviceTypeEn());
			cpServiceTypeDTO.setvServiceTypeAr(CpServiceTypeList.get(i).getVserviceTypeAr());
			ServiceTypelist.add(cpServiceTypeDTO);
		}
		
		return ServiceTypelist;
	}

	@Override
	public int getSequenceNoCount() {
		
		int data=0;
		data=feedbackDAO.getSequenceNo();
		
		return data;
	}
	
	public FeedbackReplyDocsDTO EntityToFeedbackReplyDocsDTO(CPFeedbackReplyUploadDocs replyDocs) {

		FeedbackReplyDocsDTO feedbackReplyDocsDTO = null;

		if (BSLUtils.isNotNull(replyDocs)) {

			feedbackReplyDocsDTO = new FeedbackReplyDocsDTO();
			feedbackReplyDocsDTO.setFileName(replyDocs.getFileName());
			feedbackReplyDocsDTO.setFileSize(replyDocs.getFileSize());
			feedbackReplyDocsDTO.setUploadDate(replyDocs.getUploadDate());
			feedbackReplyDocsDTO.setUploadedUserType(replyDocs.getUploadedUserType());
			feedbackReplyDocsDTO.setUploadedUserId(replyDocs.getUploadedUserId());
			return feedbackReplyDocsDTO;
		}

		return feedbackReplyDocsDTO;
	}

}
