
package com.aetins.customerportal.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aetins.customerportal.web.dao.CpUserInfoDAO;
import com.aetins.customerportal.web.entity.CpUserInfo;

/**
 * 
 * @author avinash
 *
 */
@Service
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	CpUserInfoDAO cpUserInfoDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		CpUserInfo cpUserInfo = cpUserInfoDAO.getCpUserInfo(username);

		UserBuilder builder = null;

		if (cpUserInfo != null) {
			builder = User.withUsername(cpUserInfo.getVuserName());
			builder.password(new BCryptPasswordEncoder().encode(cpUserInfo.getVpassword()));
		}
		return builder.build();
	}

}
