package com.aetins.customerportal.core.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aetins.customerportal.web.dao.CpUserInfoDAO;
import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.entity.Privilege;
import com.aetins.customerportal.web.entity.Role;
import com.aetins.customerportal.web.service.CustomerPortalServices;

@Service("userDetailsService")
@Transactional
public class UserDetailsSecurityService implements UserDetailsService{

	@Autowired
	private CpUserInfoDAO cpUserInfoDAO;
	
	@Autowired
    private HttpServletRequest request;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		try {
			
			final CpUserInfo cpUserInfo = cpUserInfoDAO.getCpUserInfo(username);
            if (cpUserInfo == null) {
                throw new UsernameNotFoundException("No user found with username: " + username);
            }
            return new org.springframework.security.core.userdetails.User(cpUserInfo.getVuserName(), cpUserInfo.getVpassword(), true, true, true, cpUserInfo.getVlocked().equals("Y")?false:true, getAuthorities(cpUserInfo.getRoles()));
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
	}

	
	private final Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private final List<String> getPrivileges(final Collection<Role> roles) {
        final List<String> privileges = new ArrayList<String>();
        final List<Privilege> collection = new ArrayList<Privilege>();
        for (final Role role : roles) {
            collection.addAll(role.getPrivileges());
        }
        for (final Privilege item : collection) {
            privileges.add(item.getName());
        }

        return privileges;
    }

    private final List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (final String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

    private final String getClientIP() {
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
	
}
