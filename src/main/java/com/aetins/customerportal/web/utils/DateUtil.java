package com.aetins.customerportal.web.utils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class DateUtil {
private static final Log log = LogFactory.getLog(DateUtil.class);

/** Default simple date format as 'dd/MM/yyyy' */
public static final String DEFAULT_DATE_FORMAT = "dd-MM-yyyy";//03/02/2012
public static final String DEFAULT_DATE_FORMAT_UPD = "dd-M-yyyy";

public static final String DEFAULT_DATE_TIME_FORMAT = "dd/MM/yyyy";

public static final String DEFAULT_DATE_TIME_SEC_FORMAT = "dd/MM/yyyy HH:mm:SS";

/** Default simple date format as 'dd/MM/yyyy hh:mm:ss a' */
public static final String DEFAULT_DATETIME_FORMAT = "dd-MM-yyyy hh:mm:ss a";

public static final String DATE_FORMAT = "dd/MM/yyyy";

public static final String DATE_FORMAT2 = "dd-MM-yyyy";

public static final String MMddyyyy = "MM/dd/yyyy";

public static final String TIME_FORMAT = "HH:mm";

public static final String TIME_HRFORMAT = "HH";

public static final String TIME_MMFORMAT = "mm";

public static final String DATE_FORMAT_LM = "yyyy-MM-dd";

public static final String DATETIME_FORMAT_LM = "yyyy-MM-dd hh:mm";

public static final String DATEMONTH_FORMAT = "dd/MM/";

public static final String MONTHYEAR_FORMAT = "/MM/yyyy";

public static final String YEAR = "yyyy";

public static final String DEFAULT_SERVER_TIME_FORMAT = "hh:mm a EEEE, dd MMMM yyyy ";

public static final String EMAIL_DATE_FORMAT = "EEE,dd-MMM-yyyy";

public static final String DDMMM = "dd MMM ";

//declare object of DateUtil 
private static DateUtil dateUtilObj;

//default constructor
public DateUtil() {
}

/**
 * returns the object of DateUtil
 */
public static synchronized DateUtil getInstance() {

	//create object if the object not created yet
	if (dateUtilObj == null) {
		dateUtilObj = new DateUtil();
	}
	//return the object
	return dateUtilObj;
}// end of method getInstance()

/**
 * Parse the date string into a java.util.Date object based on 
 * the default date format <code>dd/MM/yyyy</code>
 * 
 * @param dateStr takes a string representing the date
 * @return Date returns a java.util.Date
 */
public static java.util.Date toUtilDate(String dateStr) {
    java.util.Date date = null;
    
    SimpleDateFormat sdFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
   
    try {
    	if(dateStr != null && dateStr.trim().length() > 0) {
    		date = sdFormat.parse(dateStr.trim());
    	}
    } catch (ParseException pe) {
        pe.printStackTrace();
        date = null;
    }
        
    return date;
}

public static java.util.Date toUtilDateWFormat(String dateStr,String format) {
    java.util.Date date = null;
    
    SimpleDateFormat sdFormat = new SimpleDateFormat(format);
   
    try {
    	if(dateStr != null && dateStr.trim().length() > 0) {
    		date = sdFormat.parse(dateStr.trim());
    	}
    } catch (ParseException pe) {
        pe.printStackTrace();
        date = null;
    }
        
    return date;
}

/**
 * Created By	: Mathi
 * Created On	: 16 Sep 2013
 * Description	: To get the current date and time string 
 * @return
 */
public static String getTodayDateTimeString(){
	return toString(getTodayDateTime(), DEFAULT_DATETIME_FORMAT);
}

public static String getTodayDateTime(String format){
	try{
    	SimpleDateFormat sourceFormat = null;
    	sourceFormat = new SimpleDateFormat(format);
    	return sourceFormat.format(Calendar.getInstance().getTime());
	}catch(Exception e){
		log.info("An Error Occurred : " + e.getMessage());
		return "";
	}
}


/**
 * This method get the today date from the gregorian calander object.
 * and returns the date object.
 * @return today date
 */
public static Date getTodayDateTime() {
	Date today = null;
	SimpleDateFormat sourceFormat = null;
	Calendar cal = null;
	String dateStr = null;
	try {
		cal = GregorianCalendar.getInstance();
		
		sourceFormat = new SimpleDateFormat(DEFAULT_DATE_TIME_SEC_FORMAT);
    	
		//get the formated date as string
		dateStr = cal.get(Calendar.DAY_OF_MONTH) 
    								+"/"+ (cal.get(Calendar.MONTH) + 1) 
    								+ "/" +cal.get(Calendar.YEAR)
    								+ " " +cal.get(Calendar.HOUR_OF_DAY)
    								+":" +cal.get(Calendar.MINUTE)
    								+":" +cal.get(Calendar.SECOND);
		/*dateStr = cal.get(Calendar.DAY_OF_MONTH) 
						+"-"+ (cal.get(Calendar.MONTH) + 1) 
						+"-" +cal.get(Calendar.YEAR);*/
		
		today = sourceFormat.parse(dateStr);
    	
	} catch (Exception e) {
		if (log.isErrorEnabled()) {
			log.error("getTodayDateTime(): Error while ", e);
		}
	} finally {
		sourceFormat = null;
		cal = null;
		dateStr = null;
	}
	return today;
}// end of method getTodayDateTime()

public static String getTodayDate(){
	return getTodayDate(DEFAULT_DATE_FORMAT);
}

/**
 * This method takes the date formate as input and get the today 
 * 	date parses it to the given format. And returns the today date string data.
 * 
 * @param format -- the formate that the date has to be returned
 * @return -- the today date as string
 */
public static String getTodayDate(String format) {
	String today = "";
	SimpleDateFormat toFormat = null;
	try {
		//get the simple format object 
		toFormat = new SimpleDateFormat(format);
    	//parse the date format string 
    	today = toFormat.format(getTodayDateTime());
	} catch (Exception e) {
		//if (log.isErrorEnabled()) {
			log.error("getTodayDate(): Error while ", e);
		//}
	} finally {
		toFormat = null;
	}
	return today;
}// end of method getTodayDate()
public static Date getTodayDate(int dummy){
	SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_LM);
	Date dt=new Date();
	try {
		dt = formatter.parse(getTodayDate(DATE_FORMAT_LM));
	} catch (ParseException e) {
		e.printStackTrace();
	}
	return dt;
}    
public static void main(String args[]){
	/*try{
		logger.info(getTimeDifferenceInDays("23-09-2013","11-10-2013"));
	}catch(Exception e){
		logger.info("----main() : "+e);
	}*/
	try{
		System.out.println(isValidDate("28-13-2012","dd-M-yyyy"));
		    		System.out.println();
	//System.out.println((isDate1GreaterThanDate2("11-10-2013","12-10-2013")));
}catch(Exception e){
	//logger.info("----main() : "+e);
}
}


/**
 * This method is used to get the date which is older than the given number of days.
 * Method takes date object as input and subtracts the number of days from the date object.
 * Returns the resulted obejct.
 * 
 * @param days -- number of days to reduce from the date object
 * @param date -- the date object from where the days to be reduced.
 * 
 * @return cal - returns the calendar object
 */
public static Date getDate(int days, Calendar cal) {
	
	Date date = null;
	
	try {
		//subrtact the number of days fromt the date given 
    	cal.add(Calendar.DATE, -days);
    	//get the simple format object 
    	SimpleDateFormat sdFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
    	//get the formated date as string
    	String source = cal.get(Calendar.DAY_OF_MONTH) 
    								+"/"+ (cal.get(Calendar.MONTH)+1) 
    								+ "/" +cal.get(Calendar.YEAR);
    	//parse the date format string 
    	date = sdFormat.parse(source);
	} catch (Exception e) {
		if (log.isErrorEnabled()) {
			log.error("Error while gettting the date object", e);
		}
	}
	return date;
}// end of method getDate()

/**
 * Parse the date string into a java.util.Date object based on 
 * the default date format <code>dd/MM/yyyy hh:mm:ss a</code>
 * 
 * @param dateStr takes a string representing the date
 * @return Date returns a java.util.Date in DateTime format
 */
public static java.util.Date toUtilDateTime(String dateStr) {
    java.util.Date date = null;
    
    SimpleDateFormat sdFormat = new SimpleDateFormat(DEFAULT_DATE_TIME_SEC_FORMAT);
   
    try {
        date = sdFormat.parse(dateStr);
    } catch (ParseException pe) {
        pe.printStackTrace();
        date = null;
    }
        
    return date;
}
public static java.util.Date toUtilDateTime1(String dateStr) {
    java.util.Date date = null;
    
   
    SimpleDateFormat sdFormat = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT);
    try {
        date = sdFormat.parse(dateStr);
    } catch (ParseException pe) {
        pe.printStackTrace();
        date = null;
    }
        
    return date;
}  
public static java.util.Date toUtilDateTime(String dateStr,String format) {
    java.util.Date date = null;
    
    SimpleDateFormat sdFormat = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
   
    try {
        date = sdFormat.parse(dateStr);
    } catch (ParseException pe) {
        pe.printStackTrace();
        date = null;
    }
        
    return date;
}

/**
 * Parse the date string into a java.util.Date object based on 
 * the passing date format
 * 
 * @param dateStr takes a string representing the date
 * @param format takes a string representing the date format
 * @return Date returns a java.util.Date
 */
public static java.util.Date toUtilDate(String dateStr, String format) {
    java.util.Date date = null;
    
    SimpleDateFormat sdFormat = new SimpleDateFormat(format);
   
    try {
        date = sdFormat.parse(dateStr);
    } catch (ParseException pe) {
        pe.printStackTrace();
        date = null;
    }
        
    return date;
}

/**
 * Convert the java.sql.Timestamp into java.util.Date
 * 
 * @param sTimestamp takes a java.sql.Timestamp to be converted into java.util.Date
 * @return java.util.Date returns a converted java.util.Date
 */
public static java.util.Date toUtilDate(java.sql.Timestamp sTimestamp) {       
    return new java.util.Date(sTimestamp.getTime());
}

/**
 * Convert the java.sql.Date into java.util.Date
 * 
 * @param sDate takes a java.sql.Date to be converted into java.util.Date
 * @return java.util.Date returns a converted java.util.Date
 */
public static java.util.Date toUtilDate(java.sql.Date sDate) {
	if(sDate == null)
		return null;
    return new java.util.Date(sDate.getTime());
}

/**
 * Format the java.util.Date object into a string based on the default date format
 * <code>dd/MM/yyyy</code>
 * 
 * @param uDate takes a java.util.Date object
 * @return String returns a formatted string representing the date
 */
public static String toString(java.util.Date uDate) {
    String dateStr = null;
    
    if(uDate == null)
    	return "";
    
    try {
        SimpleDateFormat sdFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        dateStr = sdFormat.format(uDate);
    } catch (Exception e) {
        e.printStackTrace();
        dateStr = null;
    }

    return dateStr;
}

/**
 * Format the java.util.Date object into a string based on the passing date format
 * 
 * @param uDate takes a java.util.Date object
 * @param format takes a string representing the date format
 * @return String returns a formatted string representing the date
 */
public static String toString(java.util.Date uDate, String format) {
    String dateStr = "";
    
    try {
    	if(uDate != null) {
            SimpleDateFormat sdFormat = new SimpleDateFormat(format);
            dateStr = sdFormat.format(uDate);
    	}
    } catch (Exception e) {
        e.printStackTrace();
    }

    return dateStr;
}

/**
 * Parse the date string into a java.sql.Date object based on 
 * the default date format <code>dd/MM/yyyy</code>
 * 
 * @param dateStr takes a string representing the date
 * @return Date returns a java.sql.Date
 */
public static java.sql.Date toSqlDate(String dateStr) {
    java.util.Date date = toUtilDate(dateStr);
    
    return new java.sql.Date(date.getTime());
}

public static java.sql.Date toSqlDateWFormat(String dateStr,String format) {
    java.util.Date date = toUtilDateWFormat(dateStr,format);
    
    return new java.sql.Date(date.getTime());
}

/**
 * Parse the date string into a java.sql.Date object based on 
 * the default date format <code>dd/MM/yyyy hh:mm:ss a</code>
 * 
 * @param dateStr takes a string representing the date
 * @return Date returns a java.sql.Date in DateTime format
 */
public static java.sql.Date toSqlDateTime(String dateStr) {
    java.util.Date date = toUtilDateTime(dateStr);
    
    return new java.sql.Date(date.getTime());
}

/**
 * Parse the date string into a java.sql.Date object based on 
 * the passing date format
 * 
 * @param dateStr takes a string representing the date
 * @param format takes a string representing the date format
 * @return Date returns a java.sql.Date
 */
public static java.sql.Date toSqlDate(String dateStr, String format) {
	
	if(dateStr != null && dateStr.trim().length() > 0) {
        java.util.Date date = toUtilDate(dateStr, format);
        
        return new java.sql.Date(date.getTime());
	} 
	return null;
}

/**
 * Convert the java.util.Date into java.sql.Date
 * 
 * @param uDate takes a java.util.Date to be converted into java.sql.Date
 * @return java.sql.Date returns a converted java.sql.Date
 */
public static java.sql.Date toSqlDate(java.util.Date uDate) {
	if(uDate != null)
		return new java.sql.Date(uDate.getTime());
	return null;
}

/**
 * Convert the java.util.Date into java.sql.DateTime
 * 
 * @param uDate takes a java.util.Date to be converted into java.sql.Date
 * @return java.sql.Date returns a converted java.sql.Date
 */
public static java.sql.Timestamp toSqlDateTime(java.util.Date uDate) {
	if(uDate != null)
		return new java.sql.Timestamp(uDate.getTime());
	return null;
}

/**
 * Convert the java.util.Date into java.sql.Timestamp
 * 
 * @param uDate takes a java.util.Date to be converted into java.sql.Date
 * @return java.sql.Timestamp returns a converted java.sql.Timestamp
 */
public static java.sql.Timestamp toSqlTimestamp(java.util.Date uDate) {
    return new java.sql.Timestamp(uDate.getTime());
}

/**
 * Format the java.sql.Date object into a string based on the default date format
 * <code>dd/MM/yyyy</code>
 * 
 * @param sDate takes a java.sql.Date object
 * @return String returns a formatted string representing the date
 */
public static String toString(java.sql.Date sDate) {
	if(sDate == null)
		return "";
    String dateStr = toString(toUtilDate(sDate));
    
    return dateStr;
}

/**
 * Format the java.sql.Date object into a string based on the passing date format
 * 
 * @param sDate takes a java.sql.Date object
 * @param format takes a string representing the date format
 * @return String returns a formatted string representing the date
 */
public static String toString(java.sql.Date sDate, String format) {
    String dateStr = null;
    if(sDate != null) {
    	dateStr = toString(toUtilDate(sDate), format);
    }
    return dateStr;
}

public static java.sql.Date getSQLCurrentDateTime(){
	return getSQLCurrentDateTime(DEFAULT_DATETIME_FORMAT);
}

/**
 * Get the current date and time in java.sql.Date object based on the passing date format
 * 
 * @param format takes a string representing the date format
 * @return Date returns a java.sql.Date
 */
public static java.sql.Date getSQLCurrentDateTime(String format) {
    
	//Determine the date format in date and time
	SimpleDateFormat dateTimeFormater = new SimpleDateFormat(format);
	
	//Getting Current date time in day/month/year and time format
    Date date = new Date();
    java.sql.Date currentDateTime = null;
    
    try {
    	String todayDate = dateTimeFormater.format(date);
    	currentDateTime = new java.sql.Date(toSqlDate(dateTimeFormater.parse(todayDate)).getTime());
    } catch (ParseException pe) {
        pe.printStackTrace();
        currentDateTime = null;
    } catch (Exception ex) {
    	ex.printStackTrace();
    	currentDateTime = null;
    }
    return currentDateTime;
}

/**
 * 
 * @return <long> returns the days difference between given two dates.
 * @param toDate <Calendar> is the to date calendar object
 * @param fromDate <Calendar> is the from date calendar object 
 */
 public long getDifference(GregorianCalendar fromDate, GregorianCalendar toDate){
	 long days = 0;
	 try {
		 if (log.isDebugEnabled()) {
			 log.debug("@ getDifference(): " +
					"received date to calculate difference = "+fromDate);
		 }
         // First convert the from and to Calender to long (milli seconds)
         // MAKE SURE THE Hour, Seconds and Milli seconds are set to 0, if you
         // already have you own Claender object otherwise the time will be 
         // used in the comparision, later on.
         long from = fromDate.getTime().getTime();
         //get today's date as long type  
         long to = toDate.getTime().getTime(); 
 
         // Next subtract the from date from the to date (make sure the
         // result is a double, this is needed in case of Winter and Summer
         // Time (changing the clock one hour ahead or back) the result will 
         // then be not exactly rounded on days. If you use long, this slighty
         // different result will be lost.
         double difference = to - from; 
         
         // Next divide the difference by the number of milliseconds in a day
         // (1000 * 60 * 60 * 24). Next round the result, this is needed of the
         // Summer and Winter time. If the period is 5 days and the change from
         // Winter to Summer time is in the period the result will be 
         // 5.041666666666667 instead of 5 because of the extra hour. The
         // same will happen from Winter to Summer time, the result will be
         // 4.958333333333333 instead of 5 because of the missing hour. The
         // round method will round both to 5 and everything is OKE....
         days = Math.round((difference/(1000*60*60*24)));
         
         if (log.isDebugEnabled()) {
        	 log.debug("@ getDifference(): days  = "+days);
         }
	} catch (Exception e) {
		if (log.isErrorEnabled()) {
			log.error("@ getDifference(): " +
					"Error while calculating the difference b/n 2 dates.", e);
		}
	}
	
	//return number of days
     return days;    
 }// end of method getDifference()

 public static GregorianCalendar getGregorianCalendarObject(Calendar cal) {
	 GregorianCalendar gCal = null;
	 try {
		 gCal = new GregorianCalendar(cal.get(Calendar.YEAR), 
				 					cal.get(Calendar.MONTH),
				 					cal.get(Calendar.DAY_OF_MONTH),
				 					cal.get(Calendar.HOUR_OF_DAY),
				 					cal.get(Calendar.MINUTE),
				 					cal.get(Calendar.SECOND));
	} catch (Exception e) {
		if (log.isErrorEnabled()) {
			log.error("@ getCalendarObject(): " +
					"Error while creating gregorian calendar for given calendar object.", e);
		}
	}
	return gCal;
 }// end of method getGregorianCalendarObject()

 
 
 /**
  * 
  * @param date
  * @return
  */
 public static GregorianCalendar getCalendarObject(java.util.Date date) {
	 GregorianCalendar cal = null;
	 Calendar calendar = null;
	 try {
		 if (log.isDebugEnabled()) {
			 log.debug("@ getCalendarObject(): date == "+date);
		 }
		 calendar = Calendar.getInstance();
		 calendar.setTime(date);
		 
		 
		 cal = getGregorianCalendarObject(calendar);
		 
		 if (log.isDebugEnabled()) {
			 log.debug("@ getCalendarObject(): calender object = "+cal);
		 }
	} catch (Exception e) {
		if (log.isErrorEnabled()) {
			log.error("@ getCalendarObject(): " +
					"Error while creating the gregorian calendar object for given date object.", e);
		}
	} finally {
		calendar = null;
	}
	 return cal;
 }// end of method getCalendarObject()
 
 /**
  * This method is used to calculate the difference between 
  * start and end time, converts into minutes, and returns as string.
  * 
  * @param startTime -- start time in milli seconds
  * @param endTime -- end time in milli seconds
  * @return <code>String</code> returns the string conains 
  * 		the time in minutes and milli seconds.
  */
 public static String getTimeMinutes(long startTime, long endTime) {
	 String returnStr = "";
	 try {
		 long totalDifference = endTime - startTime;
		 returnStr = ""+ (double)totalDifference/(1000*60);
		 String minutes = returnStr.substring(0, returnStr.indexOf("."));
		 String seconds = returnStr.substring((returnStr.indexOf(".")+1), (returnStr.indexOf(".")+2));
		 returnStr = minutes + " minutes and "+seconds+" seconds";
		 
	} catch (Exception e) {
		if (log.isFatalEnabled()) {
			log.fatal("@ getTimeMinutes(): " + 
					"Error while finding the time difference in milli seconds.", e);
		}
	}
	return returnStr;
 }// end of method getTimeMinutes()
 
 public static boolean isDate1GreaterThanDate2(String dateStr1,String dateStr2) {
	boolean status = false;
	Date date1 = null;
	Date date2 = null;

	try {
	    date1 = toUtilDate(dateStr1, DEFAULT_DATE_FORMAT);//DEFAULT_DATE_TIME_FORMAT
	    date2 = toUtilDate(dateStr2, DEFAULT_DATE_FORMAT);
	    // if given date 1 is greater than given date 2 then status is true
	    if (date1.after(date2)) {
		status = true;
	    } else {
		// else status is false
		status = false;
	    }
	} catch (Exception e) {
	    log.error("@isDate1GreaterThanDate2(): "
		    + "Error while comparing date 1 with date 2.", e);
	}
	return status;
    }
 public static boolean isDate1GreaterThanDate2(String dateStr1,String dateStr2,String format) {
		boolean status = false;
		Date date1 = null;
		Date date2 = null;

		try {
		    date1 = toUtilDate(dateStr1,format);//DEFAULT_DATE_TIME_FORMAT
		    date2 = toUtilDate(dateStr2,format);
		    //logger.info("----------------"+date1+" == "+date2);
		    // if given date 1 is greater than given date 2 then status is true
		    if (date1.after(date2)) {
			status = true;
		    } else {
			// else status is false
			status = false;
		    }
		} catch (Exception e) {
		    log.error("@isDate1GreaterThanDate2(): "
			    + "Error while comparing date 1 with date 2.", e);
		}
		return status;
	    }

    /**
     * 
     * @param date --
     *                the date to compare
     * @return <code>boolean</code> -- returns the status true or false
     */
    public static boolean isGreaterThanToday(String dateStr1) {
	boolean status = false;
	Date date1 = null;
	try {
		date1 = toUtilDate(dateStr1, DEFAULT_DATE_FORMAT);
	    // if given date is greater than today date then status is true
	    if (date1.after(getTodayDateTime())) {
		status = true;
	    } else {
		// else status is false
		status = false;
	    }
	} catch (Exception e) {
	    log.error("@isGreaterThanToday(): "
		    + "Error while comparing date with today date.", e);
	}
	return status;
    }
    
    /**
     * 
     * @param date --
     *                the date to compare
     * @return <code>boolean</code> -- returns the status true or false
     */
    public static boolean isGreaterThanYesterday(String dateStr1) {
	boolean status = false;
	Date date1 = null;
	try {
		date1 = toUtilDate(dateStr1, DEFAULT_DATE_FORMAT);
	    // if given date is greater than today date then status is true
	    if (date1.after(BSLUtils.addTime(DateUtil.getTodayDateTime(), -24,0))) {
		status = true;
	    } else {
		// else status is false
		status = false;
	    }
	} catch (Exception e) {
	    log.error("@isGreaterThanToday(): "
		    + "Error while comparing date with today date.", e);
	}
	return status;
    }
    
   
    /**
     * 
     * @param startDate - start date
     * @param endDate - end date
     * @return - the time difference between two dates in minutes.
     */
    public static long getTimeDifference(Date startDate, Date endDate) {
	double difference = 0.0;
	long startTime = 0;
	long endTime = 0;
	long minutes = 0;
	try {
	    startTime = startDate.getTime();
	    endTime = endDate.getTime();
	    
	    long totalDifference = endTime - startTime;
	    difference = (double) totalDifference / (1000 * 60);
	    minutes = (long) Math.ceil(difference);
	    if (log.isInfoEnabled()) {
		//log.info("@ getTimeDifference():  minutes == " + minutes);
	    }
	} catch (Exception e) {
	    if (log.isFatalEnabled()) {
		log.fatal("@ getTimeMinutes(): "
			+ "Error while finding the time difference in milli seconds.",
				e);
	    }
	}
	return minutes;
    }// end of method getTimeDifference()
    
    /**
     * 
     * @param startDate - start date
     * @param endDate - end date
     * @return - the time difference between two dates in days.
     */
    public static long getTimeDifferenceInDays(Date startDate, Date endDate) {
	long hours = getTimeDifference(startDate, endDate);
	return hours / (60 * 24);
    }
    
    
    /**
     * 
     * @param startDate - start date
     * @param endDate - end date
     * @return - the time difference between two dates in days.
     */
    public static long getTimeDifferenceInDays(String startDate, String endDate) {
    	
    	Date strD = toUtilDate(startDate,DEFAULT_DATE_FORMAT);
    	Date endD = toUtilDate(endDate,DEFAULT_DATE_FORMAT);
    	
	long hours = getTimeDifference(strD, endD);
	return hours / (60 * 24);
    }
    
    /**
     * Created By	: Mathi
     * Created Date	: 09 Sep 2013
     * Description	: To calculate the age from DOB.
     */
    public static int getAge(Date dob){
    	return Math.round(getTimeDifference(dob, getTodayDateTime())/365);
    }
    
    public static Date getAddDays(int days, Date date){
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	return getAddDays(days, cal);
    }
    
    /**
     * This method is used to get the date which is older than the given number
     * of days. Method takes date object as input and subtracts the number of
     * days from the date object. Returns the resulted obejct.
     * 
     * @param days --
     *                number of days to reduce from the date object
     * @param date --
     *                the date object from where the days to be reduced.
     * 
     * @return cal - returns the calendar object
     */
    public static Date getAddDays(int days, Calendar cal) {

	Date date = null;

	try {
	    // subrtact the number of days from the date given
	    cal.add(Calendar.DATE, days);
	    date = cal.getTime();
	} catch (Exception e) {
	    if (log.isErrorEnabled()) {
		log.error("@getAddDays(): " 
			+"Error while gettting the date object", e);
	    }
	}
	return date;
    }// end of method getAddDays()
    
    public static boolean isValidFormat(String dateStr, String format) {
	boolean status = false;
	try {
	    SimpleDateFormat sdFormat = new SimpleDateFormat(format);
	    sdFormat.parse(dateStr);
	    //log.info("dateStr  is "+dateStr);
	    //log.info("parse DateStr  is "+sdFormat.parse(dateStr));
	    //log.info("toUtilDate DateStr  is "+toUtilDate(dateStr, format));
	    toUtilDate(dateStr, format);
	    status = true;
	    //log.info("Date Valid is "+status);
	} catch (ParseException e) {
	    status = false;
	    log.error("@ isValidFormat() : ",e);
	}
	return status;
}
 
 /**
  * Created By	: Mathi
  * Created On	: 17 Jul 2013
  * Description	: Check the date with the default date format.
  */
 public static boolean isValidDate(String date){
	 return isValidDate(date, DEFAULT_DATE_FORMAT);
 }
 
 public static boolean isValidDateUPD(String date){
	 return isValidDate(date, DEFAULT_DATE_FORMAT_UPD);
 }
 // date validation using SimpleDateFormat
 // it will take a string and make sure it's in the proper 
 // format as defined by you, and it will also make sure that
 // it's a legal date

 public static boolean isValidDate(String date,String format)
 {
    // set date format, this can be changed to whatever format
    // you want, MM-dd-yyyy, MM.dd.yyyy, dd.MM.yyyy etc.
    // you can read more about it here:
    // http://java.sun.com/j2se/1.4.2/docs/api/index.html
    
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    
    // declare and initialize testDate variable, this is what will hold
    // our converted string
    
    Date testDate = null;

    // we will now try to parse the string into date form
    try
    {
      testDate = sdf.parse(date);
    }

    // if the format of the string provided doesn't match the format we 
    // declared in SimpleDateFormat() we will get an exception

    catch (ParseException e)
    {
      log.error("the date you provided is in an invalid date" +
                              " format.",e);
      return false;
    }

    // dateformat.parse will accept any date as long as it's in the format
    // you defined, it simply rolls dates over, for example, december 32 
    // becomes jan 1 and december 0 becomes november 30
    // This statement will make sure that once the string 
    // has been checked for proper formatting that the date is still the 
    // date that was entered, if it's not, we assume that the date is invalid

    if (!sdf.format(testDate).equals(date)) 
    {
      log.error("The date that you provided is invalid.");
      return false;
    }
    
    // if we make it to here without getting an error it is assumed that
    // the date was a valid one and that it's in the proper format

    return true;

 } // end isValidDate
 
 
 public static boolean isValidFromToDate(Date fromDate, Date toDate){
	 if(fromDate != null && toDate != null && (fromDate.before(toDate) || fromDate.equals(toDate)))
		 return true;
	 return false;
 }
 
 public static boolean isValidBetwDate(String fromDate, String toDate,String inBet){
	 if(fromDate != null && toDate != null){
		 Date fd = DateUtil.toUtilDate(fromDate, DateUtil.DEFAULT_DATE_FORMAT);
		 Date id = DateUtil.toUtilDate(inBet, DateUtil.DEFAULT_DATE_FORMAT);
		 Date td = DateUtil.toUtilDate(toDate, DateUtil.DEFAULT_DATE_FORMAT);
		 log.info("a     "+fromDate+"==="+toDate+"======="+inBet);
		 log.info("a     "+isValidFromToDate(fd, td));
		 log.info("b     "+isValidFromToDate(id, td));
		 log.info("a     "+isValidFromToDate(fd, id));
		 if(isValidFromToDate(fd, id) && isValidFromToDate(id, td)){
			
			 return true;
		 }
	 }
	 return false;
 }

 public static boolean isGreaterorEqualDates(String dateStr1,String dateStr2){
	 
	 boolean status = false;
	 try{
		 	 
    		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        	
    		Date date1 = sdf.parse(dateStr1);
        	Date date2 = sdf.parse(dateStr2);
 
        	System.out.println(sdf.format(date1));
        	System.out.println(sdf.format(date2));
 
        	if(date1.compareTo(date2)>0){
        		System.out.println("Date1 is after Date2");
        		status = true;
        	}else if(date1.compareTo(date2)<0){
        		System.out.println("Date1 is before Date2");
        		status = false;
        	}else if(date1.compareTo(date2)==0){
        		System.out.println("Date1 is equal to Date2");
        		status = true;
        	}else{
        		System.out.println("How to get here?");
        	}
 
    	}catch(ParseException ex){
    		ex.printStackTrace();
    	}
	return status;
 }

 /**
  	 * Added By Hira Mahmood
  	 * Dated: 2-July-2015
     * Convert the string (default format "Mon Jun 11 00:00:00 GMT+05:30 2007") to string of given pattern
     * @param strDate for eg: Mon Jun 11 00:00:00 GMT+05:30 2007
     * @return : Date
     */
    public static String localeStrToPatternStr(String strDate, String pattern) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.getDefault());
        Date date = dateFormat.parse(strDate);
        SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);
        return (dateFormatter.format(date));
    }

    
    private static final long ONE_HOUR_IN_MS = 3600000;
    private static final long ONE_MIN_IN_MS = 60000;
    private static final long ONE_SEC_IN_MS = 1000;

    public static Date sumTimeToDate(Date date, int hours, int mins, int secs) {
        long hoursToAddInMs = hours * ONE_HOUR_IN_MS;
        long minsToAddInMs = mins * ONE_MIN_IN_MS;
        long secsToAddInMs = secs * ONE_SEC_IN_MS;
        return new Date(date.getTime() + hoursToAddInMs + minsToAddInMs + secsToAddInMs);
    }
   
    /**
     * 
     * @author avinash
     * @param date
     * @return
     */
    public static String toDateString(Date date) {
    	
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_LM);
    	
    	return simpleDateFormat.format(date);
    }
    
}

