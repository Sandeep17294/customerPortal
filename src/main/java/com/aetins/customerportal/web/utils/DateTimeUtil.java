package com.aetins.customerportal.web.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;


/**
 * @author satendra
 *
 */
public class DateTimeUtil {

    private static final Logger logger = Logger.getLogger(DateTimeUtil.class);

    /**
     * A method date retun date object, it take input as string of date in
     * format of "dd/MM/yyy"
     * 
     * @param dateFormat
     *            "dd/MM/yyyy"
     * @return
     */
    public static Date dateFormat(String dateFormat) {
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	try {
	    logger.info("inside method static dateFormat DataTime that returning date object");
	    return formatter.parse(dateFormat);
	} catch (ParseException e) {
	    logger.info("dateFormat exception occur while parsing date object" + e);
	    return null;
	}
    }
}
