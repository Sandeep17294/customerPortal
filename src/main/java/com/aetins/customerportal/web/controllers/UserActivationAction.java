package com.aetins.customerportal.web.controllers;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.service.UserActivationBL;
import com.aetins.customerportal.web.service.impl.UserActivationBLImpl;
import com.aetins.customerportal.web.utils.BSLUtils;

@ManagedBean
@Controller
@Scope("request")
public class UserActivationAction {

	@Autowired
	UserActivationBL userActivation;
	
	@ManagedProperty(value = "#{param.token}")
	private String token;

	private String valid;

	private static final Logger logger = Logger.getLogger(UserActivationAction.class);
	
	@PostConstruct
	public void init() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		System.out.println("*-*-*-*-*-*-*-*-*-*-*--");
		//System.out.println("testing in devlopemnt manish fdhghkhj>>>>>>>>>>>>>>>>>>>>" + request.getParameter("token"));
		logger.info("Activation user token number :"+request.getParameter("token"));
		if (BSLUtils.isNotNull(request.getParameter("token"))) {
			String tokenNo = request.getParameter("token");
			updateStatus(tokenNo);
		}
		System.out.println("your token No is " + token);

	}

	public void activate(ActionEvent event) {
		try {
			HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();
			String url = req.getParameter("token");
			System.out.println(url);
			FacesContext context = FacesContext.getCurrentInstance();
			Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
			String tokanNo = requestMap.get("token");
			System.out.println(tokanNo);
			updateStatus(tokanNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateStatus(String tokenNo) {
		UserActivationBLImpl impl;
		boolean status;
		impl = new UserActivationBLImpl();
		List<CpUserInfoDTO> cpuserInfoDtoList = userActivation.fetchUserDetails(tokenNo);
		logger.info("Activate User details fetch"+cpuserInfoDtoList);
		System.out.println(cpuserInfoDtoList.get(0).getVuserName());
		CpUserInfoDTO userInfo = new CpUserInfoDTO();
		userInfo.setVactive("A");
		userInfo.setVuserName(cpuserInfoDtoList.get(0).getVuserName());
		status = userActivation.updateStatus(userInfo);

	}

	public String getToken() {
		System.out.println("your token No is " + token);
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}
}