/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.utils;

import com.stpl.app.accountclose.common.CommonLogic;
import static com.stpl.app.accountclose.gtnbalancereport.utils.Constants.DateFormatConstants.DEFAULT_JAVA_DATE_FORMAT;
import static com.stpl.app.accountclose.gtnbalancereport.utils.Constants.DateFormatConstants.DEFAULT_SQL_DATE_FORMAT;
import com.stpl.app.accountclose.gtnbalancereport.utils.DataSelectionUtil;
import java.text.SimpleDateFormat;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author alok.v
 */
public class BaseRateUtil {
    
    /**
     * The log.
     */
    private static final Logger LOGGER = Logger.getLogger(BaseRateUtil.class);

    /**
     * Generates HQL field from Table column name
     *
     * @param fieldName
     * @param indicator
     * @return
     */
    public static String generateHqlField(String fieldName, final String indicator) {
        String finalValue = StringUtils.EMPTY;
        int loop = 0;
        fieldName = fieldName.toLowerCase();
        String[] splitArray = fieldName.split("_");
        if (indicator.equals("table")) {
            loop = 0;
        } else {
            finalValue = splitArray[0];
            loop = 1;
        }
        for (int i = loop, j = splitArray.length; i < j; i++) {
            finalValue += splitArray[i].replaceFirst((String.valueOf(splitArray[i].charAt(0))), (String.valueOf(splitArray[i].charAt(0)).toUpperCase()));
        }
        return finalValue;
    }

    public String getDateRangeQuery(String fre, String from, String to) {
        String query = StringUtils.EMPTY;
        SimpleDateFormat format = new SimpleDateFormat(com.stpl.app.accountclose.gtnbalancereport.utils.Constants.DateFormatConstants.MMddyyyy.getConstant());
        try {
            if (Constants.FrequencyConstants.ANNUALLY.getConstant().equals(fre)) {
                String tempFromDate = DataSelectionUtil.getDateFromAnnual(from);
                String tempTodate = DataSelectionUtil.getLastDateFromAnnual(to);
                String fromDate = CommonLogic.convertStringToDate(format.parse(tempFromDate).toString(), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant());
                String toDate = CommonLogic.convertStringToDate(format.parse(tempTodate).toString(), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant());
                query = " AND I.PERIOD_DATE between " + "'" + fromDate + "'" + " AND " + "'" + toDate + "' ";
            } else if (Constants.FrequencyConstants.SEMI_ANNUALLY.getConstant().equals(fre)) {
                String tempFromDate = DataSelectionUtil.getDateFromSemiannualy(from);
                String tempTodate = DataSelectionUtil.getLastDateFromSemiannually(to);
                String fromDate = CommonLogic.convertStringToDate(format.parse(tempFromDate).toString(), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant());
                String toDate = CommonLogic.convertStringToDate(format.parse(tempTodate).toString(), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant());
                query = " AND I.PERIOD_DATE between " + "'" + fromDate + "'" + " AND " + "'" + toDate + "' ";
            } else if (Constants.FrequencyConstants.QUARTERLY.getConstant().equals(fre)) {
                String tempFromDate = DataSelectionUtil.getDateFromQuarter(from);
                String tempTodate = DataSelectionUtil.getLastDateFromQuarter(to);
                String fromDate = CommonLogic.convertStringToDate(format.parse(tempFromDate).toString(), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant());
                String toDate = CommonLogic.convertStringToDate(format.parse(tempTodate).toString(), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant());
                query = " AND I.PERIOD_DATE between " + "'" + fromDate + "'" + " AND " + "'" + toDate + "' ";
            } else if (Constants.FrequencyConstants.MONTHLY.getConstant().equals(fre)) {
                query = StringUtils.EMPTY;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return query;
    }
}
