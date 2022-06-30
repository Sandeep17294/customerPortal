/**
 * 
 */
package com.aetins.customerportal.web.utils;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;


/**
 * <i>String utility methods</i>
 * @author avinash
 *
 */
public class StringUtils {

	/*
	 * Check whether input string is null or not, if input string is null, then return empty string.
	 * 
	 * @param s Input String
	 * 
	 * @return s String
	 */
	public static String checkString(String s) {
		if (s == null) {
			return "";
		}
		return s.trim();
	}

	public static boolean isNotBlank(String s) {
		return (s != null && s.trim().length() > 0);
	}

	public static String pad(String str, int size, char padChar) {
		if (str == null)
			str = "";
		// return String.format("%1$-" + size + "s", str);
		StringBuffer padded = new StringBuffer(str.trim());
		while (padded.length() < size) {
			padded.append(padChar);
		}
		return padded.toString();
	}

	public static boolean isValidEmail(String email) {

		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile(EMAIL_PATTERN);

		String[] splitStr = StringUtils.checkString(email).split("\\;|\\,");
		for (String str : splitStr) {
			matcher = pattern.matcher(str);
			if (!matcher.matches())
				return false;
		}

		return true;
	}

	public static String removeChar(String originalString, char c) {
		String newString = "";
		originalString = checkString(originalString);
		if (originalString.indexOf(c) != -1) {
			for (int i = 0; i < originalString.length(); i++) {
				if (originalString.charAt(i) != c)
					newString += originalString.charAt(i);
			}
		} else {
			return originalString;
		}
		return newString;
	}

	public static String replaceChar(String originalString, char oldChar, char newChar) {
		originalString = checkString(originalString);
		if (originalString.indexOf(oldChar) != -1) {
			originalString = originalString.replace(oldChar, newChar);
		}
		return originalString;
	}

	/**
	 * Generic method to parse name and ledger code concatenated by '-' e.g "TEST - 1234"
	 * 
	 * @param agentName
	 * @return - Name and Code e.g Name as 'Test' and Code as '1234'
	 */
	public static String[] parseForwardingAgent(String agentName) {
		String details[] = new String[2];
		int lastIndex = agentName.lastIndexOf("-");
		details[0] = agentName.substring(0, lastIndex).trim();
		details[1] = agentName.substring(lastIndex + 1, agentName.length()).trim();

		return details;
	}

	/**
	 * Generic method to parse name and code concatenated by '-' e.g "JSE - JAVA Standard Edition"
	 *  
	 * @param agentName
	 * @return - Name and Code e.g Name as ' JAVA Standard Edition' and Code as 'JSE'
	 */
	public static String[] splitByCodeAndName(String value) {
		String details[] = new String[2];
		int firstIndex = value.indexOf("-");
		details[0] = value.substring(0, firstIndex).trim();
		details[1] = value.substring(firstIndex + 1, value.length()).trim();

		return details;
	}

	public FacesMessage validateEmail(String value) {
		FacesMessage message = null;
		String email = (String) value;
		boolean error = false;

		if (!email.contains("@")) {
			error = true;
		} else {
			if (!email.contains(".")) {
				error = true;
			} else {
				if (email.indexOf(".") < email.indexOf("@")) {
					error = true;
				}
			}
		}

		if (error) {
			message = new FacesMessage();
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			message.setDetail("Email is not valid. Please key in a valid email address");
		}
		return message;
	}

	public static String validate(String emailString) {
		Pattern pattern;
		Matcher matcher;
		String result = "";
		String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		pattern = Pattern.compile(EMAIL_PATTERN);

		if (StringUtils.checkString(emailString).length() > 0) {
			if (!emailString.startsWith(",")) {
				if (emailString.contains(",")) {
					String[] splitEmails = emailString.split(",");
					for (String email : splitEmails) {
						matcher = pattern.matcher(email);
						if (!matcher.matches())
							result += email + ",";
					}
					if (result.trim().length() > 0)
						result = result.substring(0, result.length() - 1);
				} else {
					matcher = pattern.matcher(emailString);
					if (!matcher.matches())
						result += emailString;
				}
			} else {
				result += emailString;
			}
		}
		return result;
	}

	/**
	 * Validate Number
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNumber(String value) {
		Pattern p2 = Pattern.compile("^[0-9]+$");
		Matcher m2 = p2.matcher(value);
		if (m2.matches())
			return true;
		else
			return false;
	}

	/**
	 * 
	 * @param str
	 * @param size
	 * @param padChar
	 * @return
	 */
	public static String lpad(String str, int size, char padChar) {
		if (str == null)
			str = "";
		StringBuffer padded = new StringBuffer(str.trim());
		while (padded.length() < size) {
			padded.insert(0, padChar);
		}
		return padded.toString();
	}

	/**
	 * 
	 * @param message
	 * @param params
	 * @return
	 */
	public static String formatMessage(String message, Object[] params) {
		return MessageFormat.format(message, params);
	}
}
