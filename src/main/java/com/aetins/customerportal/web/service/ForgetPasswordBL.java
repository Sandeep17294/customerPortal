package com.aetins.customerportal.web.service;

import java.util.List;

import com.aetins.customerportal.web.dto.CpCustomerDetailDTO;
import com.aetins.customerportal.web.dto.PasswordRulesDTO;
import com.aetins.customerportal.web.dto.UserDTO;


public interface ForgetPasswordBL {

	List<CpCustomerDetailDTO> secureDetailsForgetPassword(UserDTO verifyUserdto);

	List<PasswordRulesDTO> listPasswordRules();

}