package com.aetins.customerportal.web.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aetins.customerportal.core.faceletstaglib.SecurityLibrary;
import com.aetins.customerportal.web.dto.CpServerSettingDTO;
import com.aetins.customerportal.web.service.AdminBL;
import com.aetins.customerportal.web.service.impl.AdminBLImpl;

@Controller(value = "downTimeAction")
@Scope("session")
public class DownTimeAction extends BaseAction{
	
	
	private String applicationDownType;
	private Date effectiveFrom;
	private Date effectTill;
	private Date startTime;
	private Date endTime;
	private int id;
	private boolean applicationDownTypeRender;
	private String applicationDownTypeErrMsg;
	private boolean effectiveFromRender;
	private String effectiveFromErrMsg;
	private boolean effectTillRender;
	private String effectTillErrMsg;
	private boolean startTimeRender;
	private String startTimeErrMsg;
	private boolean endTimeRender;
	private String endTimeErrMsg;
	private String successMsg;
	private boolean successRenderMsg;
	private String downstatus;
	private String message;
	
	
    @Autowired
	private AdminBL adminImpl;
	private CpServerSettingDTO cpServerSettingDTO = new CpServerSettingDTO();
	private List<CpServerSettingDTO> cpServerSettingDTOList = new ArrayList<CpServerSettingDTO>();
	
	@PostConstruct
	public void init() {
		try{				
			cpServerSettingDTOList = null;
			cpServerSettingDTOList = new ArrayList<CpServerSettingDTO>();
			cpServerSettingDTOList = adminImpl.fetchDownTime();
			id = cpServerSettingDTOList.get(0).getnId();
			applicationDownType = cpServerSettingDTOList.get(0).getvApplicationDownType();
			effectiveFrom = cpServerSettingDTOList.get(0).getvEffectiveFrom();
			startTime = cpServerSettingDTOList.get(0).getdStartTime();
			endTime = cpServerSettingDTOList.get(0).getdEndTime();
			effectTill = cpServerSettingDTOList.get(0).getvEffectTill();
			downstatus = cpServerSettingDTOList.get(0).getStatus();
			
			 DateFormat df = new SimpleDateFormat("dd/MM/yy");
		     System.out.println(df.format(cpServerSettingDTOList.get(0).getvEffectTill()));
			
			message = "Dear User portal is under maintaince from"+" "+(df.format(cpServerSettingDTOList.get(0).getvEffectiveFrom()))+" "+cpServerSettingDTOList.get(0).getdStartTime()+" "+"to"+" "+(df.format(cpServerSettingDTOList.get(0).getvEffectTill()))+" "+cpServerSettingDTOList.get(0).getdEndTime()+".";
		}catch(Exception e){
			e.printStackTrace();
		
		}
	}
	
	
	public void cancelOTPValidation() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			// Invalidate session
			context.getExternalContext().redirect(SecurityLibrary.getAppContextPath() + "/logout");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Added by Nithiya
	public String insertDownTime(){
		try {
			/*if(!validateDetails()){
				return "";
			}*/
			if (cpServerSettingDTOList == null) {
				cpServerSettingDTO.setvApplicationDownType(applicationDownType);
				cpServerSettingDTO.setvEffectiveFrom(effectiveFrom);
				cpServerSettingDTO.setvEffectTill(effectTill);
				cpServerSettingDTO.setdStartTime(startTime);
				cpServerSettingDTO.setdEndTime(endTime);
				boolean save = adminImpl.setDownTime(cpServerSettingDTO);
				if (!save) {
					displaySuccessMessage("downTime.Success");
					/*setSuccessMsg("The Down Time Set Successfully.");
					setSuccessRenderMsg(true);*/
				} else {
					return "error";
				}
			}else{
				cpServerSettingDTO.setnId(id);
				cpServerSettingDTO.setvApplicationDownType(applicationDownType);
				cpServerSettingDTO.setvEffectiveFrom(effectiveFrom);
				cpServerSettingDTO.setvEffectTill(effectTill);
				cpServerSettingDTO.setdStartTime(startTime);
				cpServerSettingDTO.setdEndTime(endTime);
				boolean save = adminImpl.updateDownTime(cpServerSettingDTO);
				if (!save) {
					return "error";
					/*setSuccessMsg("The Down Time Not Set");
					setSuccessRenderMsg(false);*/
				} else {
					displaySuccessMessage("downTime.Success");
					/*setSuccessMsg("The Down Time Set Successfully.");
					setSuccessRenderMsg(true);*/
				}
			}
			
						
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(" e.printStackTrace()"+e.getMessage());
		}
		return "The Down Time Set Successfully.";
	}
	
	public boolean validateDetails(){
		boolean validateDetail=true;
		if(applicationDownType == null||applicationDownType.equals(null)||applicationDownType.equalsIgnoreCase(null)||applicationDownType.equalsIgnoreCase("")){
			setApplicationDownTypeErrMsg("Enter Application Down Type");
			setApplicationDownTypeRender(true);
			validateDetail=false;
		}else{
			setApplicationDownTypeErrMsg("");
			setApplicationDownTypeRender(false);
			validateDetail=true;
		}
		
		if(effectiveFrom == null||effectiveFrom.equals(null)){
			setEffectiveFromErrMsg("Enter Effective From");
			setEffectiveFromRender(true);
			validateDetail=false;
		}else{
			setEffectiveFromErrMsg("");
			setEffectiveFromRender(false);
			validateDetail=true;
		}
		
		if(startTime == null||startTime.equals(null)){
			setStartTimeErrMsg("Enter Start Time");
			setStartTimeRender(true);
			validateDetail=false;
		}else{
			setStartTimeErrMsg("");
			setStartTimeRender(false);
			validateDetail=true;
		}
		
		if(endTime == null||endTime.equals(null)){
			setEndTimeErrMsg("Enter End Time");
			setEndTimeRender(true);
			validateDetail=false;
		}else{
			setEndTimeErrMsg("");
			setEndTimeRender(false);
			validateDetail=true;
		}
		
		if(effectTill == null||effectTill.equals(null)){
			setEffectTillErrMsg("Enter Effective Till");
			setEffectTillRender(true);
			validateDetail=false;
		}else{
			setEffectTillErrMsg("");
			setEffectTillRender(false);
			validateDetail=true;
		}
		
		return validateDetail;
	}


	public String getApplicationDownType() {
		return applicationDownType;
	}


	public void setApplicationDownType(String applicationDownType) {
		this.applicationDownType = applicationDownType;
	}


	public Date getEffectiveFrom() {
		return effectiveFrom;
	}


	public void setEffectiveFrom(Date effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}


	public Date getEffectTill() {
		return effectTill;
	}


	public void setEffectTill(Date effectTill) {
		this.effectTill = effectTill;
	}


	public boolean isApplicationDownTypeRender() {
		return applicationDownTypeRender;
	}


	public void setApplicationDownTypeRender(boolean applicationDownTypeRender) {
		this.applicationDownTypeRender = applicationDownTypeRender;
	}


	public String getApplicationDownTypeErrMsg() {
		return applicationDownTypeErrMsg;
	}


	public void setApplicationDownTypeErrMsg(String applicationDownTypeErrMsg) {
		this.applicationDownTypeErrMsg = applicationDownTypeErrMsg;
	}



	public boolean isEffectiveFromRender() {
		return effectiveFromRender;
	}


	public void setEffectiveFromRender(boolean effectiveFromRender) {
		this.effectiveFromRender = effectiveFromRender;
	}


	public String getEffectiveFromErrMsg() {
		return effectiveFromErrMsg;
	}


	public void setEffectiveFromErrMsg(String effectiveFromErrMsg) {
		this.effectiveFromErrMsg = effectiveFromErrMsg;
	}


	public boolean isEffectTillRender() {
		return effectTillRender;
	}


	public void setEffectTillRender(boolean effectTillRender) {
		this.effectTillRender = effectTillRender;
	}


	public String getEffectTillErrMsg() {
		return effectTillErrMsg;
	}


	public void setEffectTillErrMsg(String effectTillErrMsg) {
		this.effectTillErrMsg = effectTillErrMsg;
	}

	public String getSuccessMsg() {
		return successMsg;
	}


	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}


	public boolean isSuccessRenderMsg() {
		return successRenderMsg;
	}


	public void setSuccessRenderMsg(boolean successRenderMsg) {
		this.successRenderMsg = successRenderMsg;
	}


	public CpServerSettingDTO getCpServerSettingDTO() {
		return cpServerSettingDTO;
	}


	public void setCpServerSettingDTO(CpServerSettingDTO cpServerSettingDTO) {
		this.cpServerSettingDTO = cpServerSettingDTO;
	}


	public List<CpServerSettingDTO> getCpServerSettingDTOList() {
		return cpServerSettingDTOList;
	}


	public void setCpServerSettingDTOList(
			List<CpServerSettingDTO> cpServerSettingDTOList) {
		this.cpServerSettingDTOList = cpServerSettingDTOList;
	}


	public Date getStartTime() {
		return startTime;
	}


	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}


	public Date getEndTime() {
		return endTime;
	}


	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	public boolean isStartTimeRender() {
		return startTimeRender;
	}


	public void setStartTimeRender(boolean startTimeRender) {
		this.startTimeRender = startTimeRender;
	}


	public String getStartTimeErrMsg() {
		return startTimeErrMsg;
	}


	public void setStartTimeErrMsg(String startTimeErrMsg) {
		this.startTimeErrMsg = startTimeErrMsg;
	}


	public boolean isEndTimeRender() {
		return endTimeRender;
	}


	public void setEndTimeRender(boolean endTimeRender) {
		this.endTimeRender = endTimeRender;
	}


	public String getEndTimeErrMsg() {
		return endTimeErrMsg;
	}


	public void setEndTimeErrMsg(String endTimeErrMsg) {
		this.endTimeErrMsg = endTimeErrMsg;
	}

	public void setAdminImpl(AdminBLImpl adminImpl) {
		this.adminImpl = adminImpl;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDownstatus() {
		return downstatus;
	}


	public void setDownstatus(String downstatus) {
		this.downstatus = downstatus;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	
}
