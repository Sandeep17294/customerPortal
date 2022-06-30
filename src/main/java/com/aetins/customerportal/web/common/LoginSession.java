package com.aetins.customerportal.web.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.controllers.BaseAction;
import com.aetins.customerportal.web.dto.CpCustomerDetailDTO;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.dto.CustomerDetailDTO;
import com.aetins.customerportal.web.dto.UserDTO;
import com.aetins.customerportal.web.service.CustomerDetailsBL;
import com.aetins.customerportal.web.service.CustomerLoginBL;
import com.aetins.customerportal.web.utils.BSLUtils;
/*
 * Created By :Manish
 * for getting session data
 */
@Component
public class LoginSession {
	
	
	HttpSession session;
	
	@Autowired
	CustomerLoginBL loginDetailsBL;
	
	@Autowired
	CustomerDetailsBL  customerDetailsBL;
	
	
	public CustomerDetailDTO getInstance(){
		
		List<CpUserInfoDTO> cpUserList = new ArrayList<CpUserInfoDTO>();
		CustomerDetailDTO customerDetail=new CustomerDetailDTO();
		
		if(BSLUtils.isNotNull(BaseAction.getSession().getAttribute("USERNAME"))){
			
			String currUserName=BaseAction.getSession().getAttribute("USERNAME").toString();
			UserDTO userDetail = new UserDTO();
			userDetail.setUserName(currUserName);
			cpUserList=loginDetailsBL.fetchUserDetails(userDetail);
			Iterator<CpUserInfoDTO> it = cpUserList.iterator();
			while (it.hasNext()) {
				CpUserInfoDTO cpUserInfo = it.next();
				customerDetail.setCustRefNo(cpUserInfo.getNcustRefNo());
				customerDetail.setCustEmail(cpUserInfo.getVemail());
				customerDetail.setCustName(cpUserInfo.getVprefName());
				customerDetail.setCustUserName(cpUserInfo.getVuserName());
				customerDetail.setCustPassword(cpUserInfo.getVpassword());
				customerDetail.setCustDob(cpUserInfo.getDdob()+"");
				customerDetail.setCustMobileNo(cpUserInfo.getVcontactNo());
				customerDetail.setCustSesionId(BaseAction.getSession().getId());
				customerDetail.setCustType(cpUserInfo.getVgroup());
				List<CpCustomerDetailDTO> custDetails=new ArrayList<CpCustomerDetailDTO>();
				
				custDetails=customerDetailsBL.customerDetails(cpUserInfo.getNcustRefNo());
				if(BSLUtils.isNotNull(custDetails)){
				Iterator<CpCustomerDetailDTO> itCust = custDetails.iterator();
				while(itCust.hasNext()){
					CpCustomerDetailDTO customerDetailData=itCust.next();
					customerDetail.setCustIdType(customerDetailData.getVidType());
					customerDetail.setCustIdNo(customerDetailData.getNid()+"");
				}
				}
			}
		}
		return customerDetail;
	}
}