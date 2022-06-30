package com.aetins.customerportal.web.controllers;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aetins.customerportal.core.faceletstaglib.SecurityLibrary;
import com.aetins.customerportal.core.primefaces.view.GuestPreferences;
import com.aetins.customerportal.web.common.LoginSession;
import com.aetins.customerportal.web.dao.IUserProfileImageDao;
import com.aetins.customerportal.web.dto.SummaryDTO;
import com.aetins.customerportal.web.entity.CPuserProfileImage;
import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.service.CustomerPortalServices;
import com.aetins.customerportal.web.service.impl.CustomerPortalServicesImpl;
import com.aetins.customerportal.web.utils.AppSettingURL;
import com.aetins.customerportal.web.utils.BSLUtils;

@Controller
@Scope("session")
public class SummaryAction extends BaseAction {

	@Autowired
	CustomerPortalServices customerPortalServices;

	@Autowired
	LoginSession loginSession;
	
	@Autowired
	IUserProfileImageDao userProfileImage;

	private SummaryDTO cpSummaryDetails;

	private boolean policyCount = false;
	private boolean notPolicyCount = false;
	private boolean oustNull = false;
	private boolean fundNull = false;
	private boolean suspenceNull = false;
	private boolean notOustNull = false;
	private boolean notFundNull = false;
	private boolean notSuspenceNull = false;
	private boolean notFundAmtNull = false;
	private boolean fundAmtNull = false;
	
	private String profileimagePath;
	private static final Logger logger = Logger.getLogger(SummaryAction.class);

	public boolean isSuspenceNull() {
		return suspenceNull;
	}

	public void setSuspenceNull(boolean suspenceNull) {
		this.suspenceNull = suspenceNull;
	}

	private String applicationdisplayname;
	private String username;
	
	@Autowired
	GuestPreferences guest;
	
	
	
	public String getApplicationdisplayname() {
		return applicationdisplayname;
	}

	public void setApplicationdisplayname(String applicationdisplayname) {
		this.applicationdisplayname = applicationdisplayname;
	}

	@Override
	public void init() {

		try {
			
			CpUserInfo fullLoggedInUser = SecurityLibrary.getFullLoggedInUser();
			applicationdisplayname=fullLoggedInUser.getVprefName();
			username = fullLoggedInUser.getVuserName();
			guest.fetchtheme();
			
		
			CPuserProfileImage loadUserByUserName = userProfileImage.loadUserByUserName(fullLoggedInUser.getVuserName());
			logger.info("Fetch profile image based on user name: "+loadUserByUserName);
			
			if(loadUserByUserName!=null)
				profileimagePath = loadUserByUserName.getFileName();
			else
				profileimagePath = "";
			
			logger.info("Summary list call entering time:@@@@@@@:" + (new Date()).getMinutes() + ":"+ (new Date()).getSeconds());

			
			//cpSummaryDetails = customerPortalServices.getCustomerPolicySummary(BigDecimal.valueOf(loginSession.getInstance().getCustRefNo()));
			cpSummaryDetails = customerPortalServices.getCustomerPolicySummary(BigDecimal.valueOf(SecurityLibrary.getFullLoggedInUser().getNcustRefNo()));
			
			logger.info("Summary list call ending time:@@@@@@@:" + (new Date()).getMinutes() + ":"+ (new Date()).getSeconds());

			if (BSLUtils.isNotNull(cpSummaryDetails)) {

				if (cpSummaryDetails.getSuspenseAmount() == null) {
					setSuspenceNull(true);
				} else if (!BSLUtils.isNotZero(cpSummaryDetails.getSuspenseAmount())) {
					setSuspenceNull(true);
				} else {
					setNotSuspenceNull(true);
				}

				if (cpSummaryDetails.getPolicyCount() == null) {
					setPolicyCount(true);
				} else if (!BSLUtils.isNotZero(cpSummaryDetails.getPolicyCount())) {
					setPolicyCount(true);
				} else {
					setNotPolicyCount(true);
				}

				if (cpSummaryDetails.getOutStandingCount() == null) {
					setOustNull(true);
				} else if (!BSLUtils.isNotZero(cpSummaryDetails.getOutStandingCount())) {
					setOustNull(true);
				} else {
					setNotOustNull(true);
				}

				if (cpSummaryDetails.getFundCount() == null) {
					setFundNull(true);
				} else if (!BSLUtils.isNotZero(cpSummaryDetails.getFundCount())) {
					setFundNull(true);
				} else {
					setNotFundNull(true);
				}

				if (cpSummaryDetails.getFundValue() == null) {
					setFundAmtNull(true);
				} else if (!BSLUtils.isNotZero(cpSummaryDetails.getFundValue())) {
					setFundAmtNull(true);
				} else {
					setNotFundAmtNull(true);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// #{summaryAction.cpSummaryDetails.}
	}

	/*
	 * public void getSummary(){ SummaryDTO SummarytDetails=new SummaryDTO();
	 * 
	 * SummarytDetails.getPolicyCount(); SummarytDetails.getOutStandingCount();
	 * SummarytDetails.getOutStandingAmount(); SummarytDetails.getFundCount();
	 * SummarytDetails.getFundValue(); SummarytDetails.getSuspenseAmount();
	 * 
	 * }
	 */

	public void setCustomerPortalServices(CustomerPortalServicesImpl customerPortalServices) {
		this.customerPortalServices = customerPortalServices;
	}

	public SummaryDTO getCpSummaryDetails() {
		return cpSummaryDetails;
	}

	public void setCpSummaryDetails(SummaryDTO cpSummaryDetails) {
		this.cpSummaryDetails = cpSummaryDetails;
	}

	public boolean isOustNull() {
		return oustNull;
	}

	public void setOustNull(boolean oustNull) {
		this.oustNull = oustNull;
	}

	public boolean isFundNull() {
		return fundNull;
	}

	public void setFundNull(boolean fundNull) {
		this.fundNull = fundNull;
	}

	public boolean isPolicyCount() {
		return policyCount;
	}

	public void setPolicyCount(boolean policyCount) {
		this.policyCount = policyCount;
	}

	public boolean isNotPolicyCount() {
		return notPolicyCount;
	}

	public void setNotPolicyCount(boolean notPolicyCount) {
		this.notPolicyCount = notPolicyCount;
	}

	public boolean isNotOustNull() {
		return notOustNull;
	}

	public void setNotOustNull(boolean notOustNull) {
		this.notOustNull = notOustNull;
	}

	public boolean isNotFundNull() {
		return notFundNull;
	}

	public void setNotFundNull(boolean notFundNull) {
		this.notFundNull = notFundNull;
	}

	public boolean isNotSuspenceNull() {
		return notSuspenceNull;
	}

	public void setNotSuspenceNull(boolean notSuspenceNull) {
		this.notSuspenceNull = notSuspenceNull;
	}

	public boolean isNotFundAmtNull() {
		return notFundAmtNull;
	}

	public void setNotFundAmtNull(boolean notFundAmtNull) {
		this.notFundAmtNull = notFundAmtNull;
	}

	public boolean isFundAmtNull() {
		return fundAmtNull;
	}

	public void setFundAmtNull(boolean fundAmtNull) {
		this.fundAmtNull = fundAmtNull;
	}

	public String getProfileimagePath() {
		return profileimagePath;
	}

	public void setProfileimagePath(String profileimagePath) {
		this.profileimagePath = profileimagePath;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
}
