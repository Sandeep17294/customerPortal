package com.aetins.customerportal.core.faceletstaglib;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.faces.application.ViewExpiredException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.aetins.customerportal.core.enums.PRIVILEGES;
import com.aetins.customerportal.core.enums.ROLES;
import com.aetins.customerportal.core.utils.SessionAttributesConstants;
import com.aetins.customerportal.web.dao.CpUserInfoDAO;
import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.utils.BSLUtils;


/**
 * Taglib to combine the Spring-Security Project with Facelets
 * <p>
 *
 * This is the class responsible holding the logic for making the tags work.
 * <p>
 * The specified <code>public static</code> methods are also defined in the
 * spring-security.taglib.xml to enable them for usage as expression-language
 * element.
 * <p>
 * e.g.<code>
 * &lt;ui:component rendered='#{sec:ifAllGranted(&quot;ROLE_USER&quot;)'&gt; text &lt;/ui:component&gt;
 * </code>
 *
 * @author avinash
 * 
 */
public class SecurityLibrary {

	static Logger log = Logger.getLogger(SecurityLibrary.class);
	

	private static Set<String> parseAuthorities(String grantedRoles) {
		Set<String> parsedAuthorities = new TreeSet<String>();
		if (grantedRoles == null || grantedRoles.isEmpty()) {
			return parsedAuthorities;
		}

		String[] parsedAuthoritiesArr;
		if (grantedRoles.contains(",")) {
			parsedAuthoritiesArr = grantedRoles.split(",");
		} else {
			parsedAuthoritiesArr = new String[] {grantedRoles };
		}

		
		for (String auth : parsedAuthoritiesArr)
			parsedAuthorities.add(auth.trim());
		return parsedAuthorities;
	}
	@SuppressWarnings("unchecked")
	public static GrantedAuthority[] getUserAuthorities() {
		if (SecurityContextHolder.getContext() == null) {
			log.error("security context is empty, this seems to be a bug/misconfiguration!");
			return new GrantedAuthority[0];
		}
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		if (currentUser == null)
			return new GrantedAuthority[0];

		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) currentUser.getAuthorities();
		if (authorities == null)
			return new GrantedAuthority[0];

		return authorities.toArray(new GrantedAuthority[] {});
	}

	/**
	 * Method that checks if the user holds <b>any</b> of the given roles. Returns
	 * <code>true, when the first match is found, <code>false</code> if no match is found and also <code>false</code> if
	 * no roles are given
	 * 
	 * @param grantedRoles a comma seperated list of roles
	 * @return true if any of the given roles are granted to the current user, false otherwise
	 */
	public static boolean ifAnyGranted(final String grantedRoles) {
		Set<String> parsedAuthorities = parseAuthorities(grantedRoles);
		if (parsedAuthorities.isEmpty())
			return false;

		GrantedAuthority[] authorities = getUserAuthorities();

		for (GrantedAuthority authority : authorities) {
			if (parsedAuthorities.contains(authority.getAuthority()))
				return true;
		}
		return false;
	}

	/**
	 * Method that checks if the user holds <b>all</b> of the given roles. Returns <code>true</code>, iff the user holds
	 * all roles, <code>false</code> if no roles are given or the first non-matching role is found
	 * 
	 * @param requiredRoles a comma seperated list of roles
	 * @return true if all of the given roles are granted to the current user, false otherwise or if no roles are
	 *         specified at all.
	 */
	public static boolean ifAllGranted(final String requiredRoles) {
		// parse required roles into list
		Set<String> requiredAuthorities = parseAuthorities(requiredRoles);
		if (requiredAuthorities.isEmpty())
			return false;

		// get granted authorities
		GrantedAuthority[] authoritiesArray = getUserAuthorities();

		Set<String> grantedAuthorities = new TreeSet<String>();
		for (GrantedAuthority authority : authoritiesArray) {
			grantedAuthorities.add(authority.getAuthority());
		}

		// iterate over required roles,
		for (String requiredAuthority : requiredAuthorities) {
			// check if required role is inside granted roles
			// if not, return false
			if (!grantedAuthorities.contains(requiredAuthority)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Method that checks if <b>none</b> of the given roles is hold by the user. Returns <code>true</code> if no roles
	 * are given, or none of the given roles match the users roles. Returns <code>false</code> on the first matching
	 * role.
	 * 
	 * @param notGrantedRoles a comma seperated list of roles
	 * @return true if none of the given roles is granted to the current user, false otherwise
	 */
	public static boolean ifNotGranted(final String notGrantedRoles) {
		Set<String> parsedAuthorities = parseAuthorities(notGrantedRoles);
		if (parsedAuthorities.isEmpty())
			return true;

		GrantedAuthority[] authorities = getUserAuthorities();

		for (GrantedAuthority authority : authorities) {
			if (parsedAuthorities.contains(authority.getAuthority()))
				return false;
		}
		return true;
	}

	/**
	 * This method returns the login id of the logged-in user through spring security {@link SecurityContextHolder}
	 * 
	 * @return the Login Id of the logged in user or an empty string if logged-in user could not be identified.
	 */
	public static String getLoggedInUserLoginId() {
		if (SecurityContextHolder.getContext() == null) {
			log.error("security context is empty, this seems to be a bug/misconfiguration!");
			return "";
		}
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		if (currentUser == null)
			return "";

		if (currentUser instanceof UsernamePasswordAuthenticationToken) {
			CpUserInfo user = (CpUserInfo) currentUser.getPrincipal();
			if (user != null) {
				log.debug("Logged In User : " + currentUser.getName() + "   -> " + user.getVuserName());
				return user.getVuserName();
			} else
				return "";
		} else
			return "";

	}

	/**
	 * This method returns the Id (primary key) of the logged-in user through spring security
	 * {@link SecurityContextHolder}
	 * 
	 * @return the Id (primary key) of the logged in user or an empty string if logged-in user could not be identified.
	 */
	public static String getLoggedInUserId() {
		if (SecurityContextHolder.getContext() == null) {
			log.error("security context is empty, this seems to be a bug/misconfiguration!");
			return "";
		}
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		if (currentUser == null)
			return "";

		if (currentUser instanceof UsernamePasswordAuthenticationToken) {
			CpUserInfo user = (CpUserInfo) currentUser.getPrincipal();
			if (user != null) {
				log.debug("Logged In User : " + currentUser.getName() + "  -> " + user.getVuserName());
				return user.getVuserName();
			} else
				return "";
		} else
			return "";
	}

	/**
	 * This method returns the {@link User} instance of the logged-in user through spring security
	 * {@link SecurityContextHolder}
	 * 
	 * @return the {@link User} instance of the logged in user or an empty string if logged-in user could not be
	 *         identified.
	 */
	public static String getLoggedInUser() throws ViewExpiredException {
		if (SecurityContextHolder.getContext() == null) {
			log.error("security context is empty, this seems to be a bug/misconfiguration!");

			throw new ViewExpiredException();
		}
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		if (currentUser == null)
			throw new ViewExpiredException();

		if (currentUser instanceof UsernamePasswordAuthenticationToken) {
			String user = currentUser.getName();
			
			if (BSLUtils.isNotNull(user)) {
				
				return user;
			} else
				throw new ViewExpiredException();
		} else
			throw new ViewExpiredException();
	}

	/**
	 * This method returns the {@link User} instance of the logged-in user fully loaded with its associated properties
	 * through spring security {@link SecurityContextHolder}
	 * 
	 * @return the {@link User} instance of the logged in user or an empty string if logged-in user could not be
	 *         identified.
	 */
	public static CpUserInfo getFullLoggedInUser() throws ViewExpiredException {
		if (SecurityContextHolder.getContext() == null) {
			log.error("security context is empty, this seems to be a bug/misconfiguration!");

			throw new ViewExpiredException();
		}
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		if (currentUser == null)
			throw new ViewExpiredException();

		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		CpUserInfo attribute = (CpUserInfo) attributes.getRequest().getSession(true).getAttribute(SessionAttributesConstants._USER_DETAILS);
		return attribute;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, String> _getPortalConfiguration() throws ViewExpiredException {
		if (SecurityContextHolder.getContext() == null) {
			log.error("security context is empty, this seems to be a bug/misconfiguration!");

			throw new ViewExpiredException();
		}
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		if (currentUser == null)
			throw new ViewExpiredException();

		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		Map<String, String>   configMap = (Map<String, String>) attributes.getRequest().getSession(true).getAttribute(SessionAttributesConstants._PORTAL_CONFIG_MAP);
		return configMap;
	}
	
	/**
	 * To get logged in user roles based on his authorities or privileges.
	 * @param authorities
	 * @return loggedIn user ROLE
	 */
	public static String getLoggedInUserRoles(final GrantedAuthority[] authorities) {

		boolean isUser = false;
		boolean isAdmin = false;
		boolean isBusinessUser = false;

		if (authorities != null) {

			for (final GrantedAuthority grantedAuthority : authorities) {
				if (grantedAuthority.getAuthority().equals(PRIVILEGES.TRANSACTION_PRIVILEGE.name())
						|| grantedAuthority.getAuthority().equals(PRIVILEGES.CHANGE_SECURITY_QUES_PRIVILEGE.name())
						|| grantedAuthority.getAuthority().equals(PRIVILEGES.E_STATEMENT_PRIVILEGE.name())) {
					isUser = true;
					break;
				} else if (grantedAuthority.getAuthority().equals(PRIVILEGES.PORTAL_MANAGEMENT_PRIVILEGE.name())
						|| grantedAuthority.getAuthority().equals(PRIVILEGES.FEEDBACK_CLOSE_PRIVILEGE.name())
						|| grantedAuthority.getAuthority().equals(PRIVILEGES.USER_STATUS_CHANGE_PRIVILEGE.name())) {
					isAdmin = true;
					break;
				} else if (grantedAuthority.getAuthority().equals(PRIVILEGES.FEEDBACK_REPLY_PRIVILEGE.name())) {
					isBusinessUser = true;
					break;
				}
			}
		}

		if (isUser) {
			return ROLES.ROLE_USER.name();
		} else if (isAdmin) {
			return ROLES.ROLE_ADMIN.name();
		} else if (isBusinessUser) {
			return ROLES.ROLE_BUSINESS_USER.name();
		} else {
			throw new IllegalStateException();
		}

	}
	
	/**
	 * This method return application URL
	 * @return application url
	 */
	public static String getAppUrl() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	   return "http://" + attributes.getRequest().getServerName() + ":" + attributes.getRequest().getServerPort() + attributes.getRequest().getContextPath();
	}
	
	/**
	 * This method return application URL
	 * @return application url
	 */
	public static String getEnvironmentUrl() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	   return "http://" + attributes.getRequest().getServerName() + ":" + attributes.getRequest().getServerPort();
	}
	
	/**
	 * This method return application context path
	 * @return application contex path
	 */
	public static String getAppContextPath() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attributes.getRequest().getContextPath();
	}
	
	/**
	 * This method return current user http session
	 * @return current http session
	 */
	public static HttpSession getCurrentSession() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attributes.getRequest().getSession();
	}


	/**
	 * This el function will be used to format {@link Date} in the form MMM, yyyy
	 * 
	 * @param toFormat the {@link Date} instance to format
	 * @return formatted date in the form MMM, yyyy (e.g. Jan 2010)
	 */
	public static String formatDateToMonthYear(Date toFormat) {
		log.info(">>>>> Formatting date to MMM, yyyy : " + toFormat);
		if (toFormat != null) {
			SimpleDateFormat df = new SimpleDateFormat("MMM, yyyy");
			return df.format(toFormat);
		} else
			return "";
	}

	/**
	 * This el function will be used to format {@link Date} in the form dd/MM/yyyy HH:mm:ss
	 * 
	 * @param toFormat the {@link Date} instance to format
	 * @return formatted date in the form dd/MM/yyyy HH:mm:ss (e.g. 12/01/2010 18:43:29)
	 */
	public static String formatDateToFullDateTime(Date toFormat) {
		if (toFormat != null) {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			return df.format(toFormat);
		} else
			return "";
	}

	/**
	 * This el function will be used to format {@link Date} in the form dd/MM/yyyy HH:mm
	 * 
	 * @param toFormat the {@link Date} instance to format
	 * @return formatted date in the form dd/MM/yyyy HH:mm (e.g. 12/01/2010 18:43)
	 */
	public static String formatDateToDateTime(Date toFormat) {
		if (toFormat != null) {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			return df.format(toFormat);
		} else
			return "";
	}

	/**
	 * This el function will be used to format {@link Date} in the form dd/MM/yyyy
	 * 
	 * @param toFormat the {@link Date} instance to format
	 * @return formatted date in the form dd/MM/yyyy (e.g. 12/01/2010)
	 */
	public static String formatDateToDate(Date toFormat) {
		if (toFormat != null) {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			return df.format(toFormat);
		} else
			return "";
	}

	public SecurityLibrary() {
	}
	@SuppressWarnings("unused")
	private final String getClientIP(HttpServletRequest request) {
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
	
	
}
