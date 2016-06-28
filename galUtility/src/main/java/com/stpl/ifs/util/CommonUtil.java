/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.ifs.util;

import com.stpl.ifs.util.constants.GlobalConstants;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author sriram
 */
public class CommonUtil {

    /**
     * Common date formate to be followed in all screens
     */
    private static final SimpleDateFormat commonDateFormat = new SimpleDateFormat();

    private final Logger LOGGER = Logger.getLogger(CommonUtil.class);

    /**
     * To get the date formatted with the common date format
     *
     * @param date The Source date
     * @return The formatted date (or) empty if it is not formatted
     */
    public static String getFormattedDate(Date date) {
        commonDateFormat.applyPattern(GlobalConstants.getCommonDateFormat());
        return getFormattedDate(date, commonDateFormat);
    }

    /**
     * To get the date formatted by the defined date formatter
     *
     * @param date The Source Date
     * @param dateFormatter The formatter to which the given date should be
     * formatted
     * @return The formatted date (or) empty if it is not formatted
     */
    public static String getFormattedDate(Date date, SimpleDateFormat dateFormatter) {
        if (date != null && dateFormatter != null) {
            return dateFormatter.format(date);
        }
        return StringUtils.EMPTY;
    }

    /**
     * To convert the given object to integer. If it is an integer, it returns
     * the appropriate value. If it is not an integer, it returns 0
     *
     * @param object The object which should be converted to integer
     * @return The converted integer value
     */
    public static int convertToInteger(Object object) {
        int convertedValue = 0;
        try {
            convertedValue = Integer.valueOf(String.valueOf(object));
        } catch (NumberFormatException nfe) {

        }
        return convertedValue;
    }

    /**
     * To check whether the given object is integer or not. If it is an integer,
     * it returns the appropriate value. If it is not an integer, it returns 0
     *
     * @param object The object which should be checked for integer
     * @return True if the given object is integer. False if the given object is not an integer
     */
    public static boolean isInteger(Object object) {
        boolean isInteger = false;
        try {
            Integer.valueOf(String.valueOf(object));
            isInteger = true;
        } catch (NumberFormatException nfe) {
            isInteger = false;
        }
        return isInteger;
    }
}
