/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.transfercontract.util;

import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.xmlparser.SQlUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author harlin
 */
public final class CommonUtil {

    public static final Logger LOGGER = LoggerFactory.getLogger(CommonUtil.class);

    public static String astToPerConverter(final String inputString) {
        return StringUtils.isBlank(inputString) || Constants.NULL.equals(inputString) ? "%" : inputString.replace("*", "%");
    }

    public static String zeroToPerConverter(final String inputString) {
        return StringUtils.isBlank(inputString) || Constants.ZEROSTRING.equals(inputString) || Constants.NULL.equals(inputString) ? "%" : inputString.replace("*", "%");
    }

    public static String getPureValue(final String inputString) {
        return StringUtils.isBlank(inputString) || Constants.NULL.equals(inputString) || Constants.IndicatorConstants.SELECT_ONE.getConstant().equals(inputString) ? StringUtils.EMPTY : inputString;
    }

    /**
     * To get the integer value from input object
     * @param input The input
     * @return The integer value
     */
    public static int getIntValue(final Object input) {
        int intValue = 0;
        try {
            intValue = Integer.valueOf(String.valueOf(input));
        } catch (NumberFormatException nfe) {
            LOGGER.debug(String.valueOf(input) + " is not a number. So setting the value as 0");
        }
        return intValue;
    }

    public static String getDBDate(final Date input) {
        SimpleDateFormat temp = new SimpleDateFormat(Constants.DBDATE_FORMAT);
        return input == null ? StringUtils.EMPTY : temp.format(input);
    }

    public static String getQuery(final Map<String, String> input, final String queryName) {
        LOGGER.debug("queryName-->>" + queryName);
        StringBuilder queryString = new StringBuilder(SQlUtil.getQuery(queryName) != null ? SQlUtil.getQuery(queryName) : StringUtils.EMPTY);
        if (input != null) {
            for (Map.Entry<String, String> entry : input.entrySet()) {
                final String string = entry.getKey();
                final String string1 = entry.getValue();
                while (queryString.toString().contains(string)) {
                    queryString.replace(queryString.indexOf(string), queryString.indexOf(string) + string.length(), String.valueOf(string1));
                }
            }
        }
        LOGGER.debug("Query : " + queryString);
        return queryString.toString();
    }

    public static String getSystemIds(final Set<String> input) {
        StringBuilder retString = new StringBuilder();
        for (Iterator<String> it = input.iterator(); it.hasNext();) {
            retString.append("'");
            retString.append(it.next());
            retString.append("'");
            if (it.hasNext()) {
                retString.append(",");
            }
        }
        return retString.toString();
    }

    private CommonUtil() {
        // Empty Constructor
    }
    
    public static String getQuery(final Map<String, String> input, final Map<String, String> inputToAppend, final String queryName) {
        LOGGER.debug("queryName-->>" + queryName);
        StringBuilder queryString = new StringBuilder(SQlUtil.getQuery(queryName) != null ? SQlUtil.getQuery(queryName) : StringUtils.EMPTY);
        if (input != null) {
            for (Map.Entry<String, String> entry : input.entrySet()) {
                final String string = entry.getKey();
                final String string1 = entry.getValue();
                while (queryString.toString().contains(string)) {
                    queryString.replace(queryString.indexOf(string), queryString.indexOf(string) + string.length(), String.valueOf(string1));
                }
            }
        }
        while (queryString.toString().contains(";")) {
            queryString.replace(queryString.indexOf(";"), queryString.indexOf(";")+1, StringUtils.EMPTY);
        }
        if (inputToAppend != null) {
            for (Map.Entry<String, String> entry : inputToAppend.entrySet()) {
                final String string = entry.getKey();
                final String string1 = entry.getValue();
                if (string.contains("DATE")) {
                    queryString.append(" and ").append(string).append(" = '").append(string1).append("'");
                } else {
                    queryString.append(" and ").append(string).append(" like '").append(string1).append("'");
                }
            }
        }
        LOGGER.debug("Query : " + queryString);
        return queryString.toString();
    }
}
