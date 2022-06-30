package com.aetins.customerportal.web.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author: Hemamalini
 * @date: May 27, 2011
 * @version: v1.0
 * @remarks: The object BSLUtils.java is used to hold the properties related to
 *           BSLUtils.java
 */
public class BSLUtils implements IBSLUtils {// get log for this object
	private static final Log log = LogFactory.getLog(BSLUtils.class);

	public static boolean isNotZero(List list) {
		return (list != null && list.size() > 0) ? true : false;
	}

	public static boolean isNotNull(Object object) {
		return object != null ? true : false;
	}

	public static boolean isNotNull(String value) {
		return (value != null && value.trim().length() > 0) ? true : false;
	}

	public static boolean isNotZero(Long value) {
		return (!isNull(value) && value.longValue() > 0L) ? true : false;
	}

	public static boolean isNotZero(String value) {
		return (!isNull(value) && !value.equals("0")) ? true : false;
	}

	public static boolean isNotZero(Double value) {
		return (!isNull(value) && value.doubleValue() > 0.0) ? true : false;
	}

	public static boolean isNotZero(BigDecimal value) {
		return (!isNull(value) && value.compareTo(BigDecimal.ZERO) != 0) ? true : false;
	}

	public static boolean isZero(BigDecimal value) {
		return (!isNull(value) && value.compareTo(BigDecimal.ZERO) == 0) ? true : false;
	}

	public static boolean isNull(Object object) {
		return (object == null) ? true : false;
	}

	public static boolean isNull(Double value) {
		return (value == null) ? true : false;
	}

	public static boolean isNull(Long value) {
		return (value == null) ? true : false;
	}

	public static Character toCharacter(Object value) {

		return (value != null) ? new Character(toString(value).charAt(0)) : null;

	}// end of method toCharacter()

	public static int toInt(Object value) {
		return (value instanceof String) ? toInt(value.toString()) : 0;
	}

	public static int toInt(String value) {
		return (isNotNull(value)) ? Integer.parseInt(value) : 0;
	}// end of method toFloat()

	public static float toFloat(String value) {
		return (isNotNull(value)) ? Float.parseFloat(value) : 0.0f;
	}// end of method toFloat()

	public static float toFloat(Object value) {
		return toFloat(toString(value));
	}// end of method toFloat()

	public static boolean isNull(String value) {
		return (value == null || value.trim().length() < 1) ? true : false;
	}

	public static Long getNonZero(Long value) {
		return (isNotZero(value)) ? value : null;
	}

	public static boolean equals(String firstString, String secondString) {
		/*
		 * if(isNull(firstString) || isNull(secondString)) return false;
		 */
		return toString(firstString).equals(toString(secondString));
	}

	public static boolean equalsIgnoreCase(String firstString, String secondString) {
		/*
		 * if(isNull(firstString) || isNull(secondString)) return false;
		 */
		return toString(firstString).equalsIgnoreCase(toString(secondString));
	}

	public static boolean notEquals(String firstString, String secondString) {
		/*
		 * if(isNull(firstString) || isNull(secondString)) return false;
		 */
		return !toString(firstString).equals(toString(secondString));
	}

	public static String toString(Object object) {
		return (isNotNull(object)) ? object.toString() : "";
	}// end of method toString()

	public static Long toLong(String object) {
		return (isNotNull(object)) ? Long.valueOf(object.toString()) : null;
	}// end of method toLong()

	public static Long toLong(Object object) {
		return (object != null) ? Long.valueOf(object.toString()) : null;
	}// end of method toLong()

	public static long tolong(String object) {
		return (isNotNull(object)) ? Long.valueOf(object.toString()) : 0;
	}// end of method toLong()

	public static long tolong(Object object) {
		return (object != null) ? Long.valueOf(object.toString()) : 0;
	}// end of method toLong()

	public static Double toDouble(Object object) {

		return (isNotNull(toString(object))) ? Double.valueOf(object.toString()) : new Double(0.0);

	}// end of method toLong()

	public static BigDecimal toBigDecimal(Object object) {
		return (isNotNull(toString(object))) ? BigDecimal.valueOf(toDouble(object.toString().replace(",", "")))
				: new BigDecimal(0.0);
	}

	public static Short toShort(String object) {
		return (isNotNull(object)) ? Short.valueOf(object) : null;
	}// end of method toLong()

	public static Short toShort(Object object) {
		return (toShort(toString(object)));
	}// end of method toLong()

	public static double getAbsoluteValue(double value) {
		return (value < 0.0) ? 0.0 : value;
	}

	public static double getPrice(Double price, Short qty) {
		return (double) (price.doubleValue() * qty.doubleValue());
	}

	public static boolean value1GreaterThanValue2(double value1, double value2) {
		return (value1 < value2) ? false : true;
	}

	/**
	 * This method takes the sql server date object and converts into the
	 * java.util.Date object
	 * 
	 * @param sqlDate
	 *            -- the sql server date object
	 * @return <code>Date</code> returns the java.util.Date object.
	 */

	public static Date toDate(Object sqlDate) {
		Date date = null;
		String dateStr = toString(sqlDate);
		SimpleDateFormat sdFormat = null;
		try {
			if (!isNull(dateStr)) {
				dateStr = dateStr.substring(0, dateStr.indexOf("."));
				sdFormat = new SimpleDateFormat(EEE_DD_MMM_YYYY);
				date = sdFormat.parse(dateStr.trim());
			}
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("@ toDate(): Error while parsing the date.", e);
			}
		} finally {
			dateStr = null;
			sdFormat = null;
		}
		return date;
	}// end of method toDate()

	public static Date toUtilSqlDate(Object sqlDate) {
		Date date = null;
		String dateStr = toString(sqlDate);
		SimpleDateFormat sdFormat = null;
		try {
			if (!isNull(dateStr)) {
				dateStr = dateStr.substring(0, dateStr.indexOf("."));
				sdFormat = new SimpleDateFormat(DateUtil.DATE_FORMAT_LM);
				date = sdFormat.parse(dateStr.trim());
			}
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("@ toDate(): Error while parsing the date.", e);
			}
		} finally {
			dateStr = null;
			sdFormat = null;
		}
		return date;
	}

	/**
	 * This method takes the sql server date object and converts into the
	 * java.util.Date object
	 * 
	 * @param sqlDate
	 *            -- the sql server date object
	 * @return <code>Date</code> returns the java.util.Date object.
	 */
	public static Date toUtilDate(Object utilDate) {
		Date date = null;
		String dateStr = toString(utilDate);
		SimpleDateFormat sdFormat = null;
		try {
			if (!isNull(dateStr)) {
				// dateStr = dateStr.substring(0, dateStr.indexOf("."));
				sdFormat = new SimpleDateFormat(DateUtil.DEFAULT_DATE_FORMAT);
				date = sdFormat.parse(BSLUtils.toString(utilDate));
			}
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("@ toDate(): Error while parsing the date.", e);
			}
		} finally {
			dateStr = null;
			sdFormat = null;
		}
		return date;
	}// end of method toDate()

	/**
	 * This method takes the sql server date object and converts into the
	 * java.util.Date object
	 * 
	 * @param sqlDate -- the sql server date object
	 * @return <code>Date</code> returns the java.util.Date object.
	 */
	public static Date toEEEDDMMMYYYYDate(Object sqlDate) {
		Date date = null;
		String dateStr = toString(sqlDate);
		SimpleDateFormat sdFormat = null;
		try {
			if (!isNull(dateStr)) {
				dateStr = dateStr.substring(0, dateStr.indexOf("."));
				sdFormat = new SimpleDateFormat(EEE_DD_MMM_YYYY);
				date = sdFormat.parse(dateStr.trim());
			}
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("@ toDate(): Error while parsing the date.", e);
			}
		} finally {
			dateStr = null;
			sdFormat = null;
		}
		return date;
	}// end of method toDate()

	/**
	 * 
	 * @param source
	 *            -- source value to be split into array.
	 * @param regExp
	 *            -- the regular expression to split the string.
	 * @return
	 */
	public static String[] split(String source, String regExp) {
		return (source != null ? source.split(regExp) : null);
	}

	/**
	 * Created By : Mathi Created On : 24 Sep 2013 Description : To convert to
	 * the default price format.
	 */
	public static String getFormatedPrice(Object value) {
		return getFormatedPrice(value, DEFAULT_PRICE_FORMAT);
	}

	/**
	 * 
	 * @param value
	 *            -- the data to be formated as price
	 * @param formatStr
	 *            -- the format of the the price
	 * @return the formated string data for given string value
	 */
	public static String getFormatedPrice(Object value, String formatStr) {
		DecimalFormat nf = null;
		String retValue = "0.00";
		try {
			nf = new DecimalFormat(formatStr);
			retValue = nf.format(new Double(toString(value)));
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("getFormatedPrice(): number format exception.", e);
			}
		} finally {
			nf = null;
		}
		return retValue;
	}// end of method getFormatedPrice()

	/**
	 * 
	 * @param value
	 *            -- the data to be formated as price
	 * @return the formated string data for given string value
	 */
	public static String getFormatedPrice(String value) {
		try {
			value = getFormatedPrice(value, DEFAULT_PRICE_FORMAT);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("getFormatedPrice(): error while formating the price.", e);
			}
		}
		return value;
	}// end of method getFormatedPrice()

	/**
	 * 
	 * @param value
	 *            -- the value to be formated in price format
	 * @return the formated string data for given double value
	 */
	public static String getFormatedPrice(double value) {
		return getFormatedPrice(value + "");
	}// end of method getFormatedPrice()

	/**
	 * 
	 * @param value
	 *            -- the value to be formated in price format
	 * @return the formated string data for given double value
	 */
	public static String getFormatedPrice(double value, String formatStr) {
		return getFormatedPrice(value + "", formatStr);
	}// end of method getFormatedPrice()

	/**
	 * 
	 * @param value
	 *            -- the value to be formated in price format
	 * @return the formated string data for given float value
	 */
	public static String getFormatedPrice(float value, String formatStr) {
		return getFormatedPrice(value + "", formatStr);
	}// end of method getFormatedPrice()

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static String getFormatedPrice(Double value) {

		return (value != null) ? getFormatedPrice(value.doubleValue()) : "0.00";
	}// end of method getFormatedPrice()

	public static String appendComma(String value) {
		if (isNotNull(value)) {
			value = (value.endsWith(",") ? value : (value + ","));
		}
		return value;
	}

	public static String appendBreak(String value) {
		if (isNotNull(value)) {
			value = (value.endsWith("<br/>") ? value : (value + "<br/>"));
		}
		return value;
	}

	/**
	 * 
	 * @param ids
	 *            -- array of ids as string type
	 * @return <code>List</code> array of Long type ids.
	 */
	public static List convertToLongList(String ids[]) {
		List returnIds = null;
		try {
			if (isNotNull(ids)) {
				returnIds = new ArrayList();
				for (int i = 0; i < ids.length; i++) {
					returnIds.add(toLong(ids[i]));
				}
			}
		} catch (Exception e) {
			if (log.isFatalEnabled()) {
				log.fatal("@ convertToLong(): " + "error while converting the string array to long array type.", e);
			}
		}
		return returnIds;
	}// end of method convertToLongList()

	public static String replaceAllSingleQuotes(String string) {
		return string.replaceAll("\'", ENCODE_STRING);
	}

	/**
	 * 
	 * @param date
	 *            - the date object to which the time to be added.
	 * @param time
	 *            - the time to be added to the date object. The default time
	 *            format is HH:mm
	 * @return <code>Date</code> returns the date object after adding the
	 *         specified time to the given date object.
	 */
	public static Date addTime(Date date, int hours, int minutes) {
		Calendar cal = null;
		try {
			if (BSLUtils.isNotNull(date)) {
				cal = Calendar.getInstance();
				cal.setTime(date);
				cal.add(Calendar.HOUR_OF_DAY, hours);
				cal.add(Calendar.MINUTE, minutes);
				return cal.getTime();
			}
		} catch (Exception e) {
			log.error("@addTime(): " + "Error occurs during the execution of addTime()", e);
		} finally {
			cal = null;
		}
		return date;
	}// end of the method addTime()

	public static boolean isNumber(String str) {
		boolean flag = false;
		try {
			Integer.parseInt(str);
			flag = true;
		} catch (NumberFormatException nfe) {
			flag = false;
		}
		return flag;
	}

	/**
	 * Created By : Mathi Created On : 16 Jan 2014 Description : Check if the
	 * string contains chars.
	 */
	public static boolean containsChars(String value) {
		if (Pattern.matches("[0-9]+", value)) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean isValidName(String name) {
		if (name.trim().matches("([a-zA-Z]+\\s+)*[a-zA-Z]+"))
			return true;
		else
			return false;
	}

	public static boolean containsSpecialCharactersAndNumbers(String value) {
		String specialCharacters = "~`!@#$%^&*()_+{}|:\";'[]\\<>?,./-=1234567890";
		value = BSLUtils.toString(value);
		for (int i = 0; i < value.length(); i++) {
			if (specialCharacters.contains(value.charAt(i) + ""))
				return true;
		}
		return false;
	}

	public static boolean containsSpecialCharacters(String value) {
		String specialCharacters = "~`!@#$%^&*()_+{}|:\";'[]\\<>?,./-=";
		value = BSLUtils.toString(value);
		for (int i = 0; i < value.length(); i++) {
			if (specialCharacters.contains(value.charAt(i) + ""))
				return true;
		}
		return false;
	}

	/**
	 * Created By : Mathi Created On : 16 Jan 2014 Description : Check if the
	 * string does not contains chars.
	 */
	public static boolean doesNotContainsChars(String value) {
		return !containsChars(value);
	}

	/**
	 * Created By : Mathi Created On : 16 Jan 2014 Description : Check if the
	 * string is a decimal value.
	 */
	public static boolean isDecimal(String value) {
		try {
			value = value.replace(",", "");
			Double.parseDouble(value);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	public static boolean isProbablyArabic(String s) {
		for (int i = 0; i < Character.codePointCount(s, 0, s.length()); i++) {
			int c = s.codePointAt(i);
			if (c >= 0x0600 && c <= 0x06E0)
				return true;
		}
		return false;
	}

	public static void main(String[] arg) {
		String str = "رقم صندوق البريد ABC";
		System.out.println(BSLUtils.containsSpecialCharacters(str));
		str = "رقم صندوق البريد";
		System.out.println(BSLUtils.containsSpecialCharacters(str));
		str = "رقم صندوق البريد ABC123";
		System.out.println(BSLUtils.containsSpecialCharacters(str));
		str = "رقم صندوق البريد ABC !@#!@#$^$%&";
		System.out.println(BSLUtils.containsSpecialCharacters(str));
		str = "رقم صندوق البريد123123 ";
		System.out.println(BSLUtils.containsSpecialCharacters(str));
	}
}// end of class BSLUtils.java