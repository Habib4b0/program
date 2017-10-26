/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.deductioncalendar.ui.util;

import com.stpl.app.global.deductioncalendar.dto.DeductionDetailsDTO;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author jayaram
 */
public class CommonUtil {

    private static final Logger LOGGER = Logger.getLogger(CommonUtil.class);
    private static final String DATASOURCE_CONTEXT = "java:jboss/datasources/jdbc/appDataPool";
    private static final String[] CURRENT_MONTH = {"Jan~1", "Feb~2", "Mar~3", "Apr~4", "May~5", "Jun~6", "Jul~7", "Aug~8", "Sep~9", "Oct~10", "Nov~11", "Dec~12"};
    private static final String[] CURRENT_MONTH_HEADER = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private static final int[] QUATER_VALUE = {1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4};
    private static final int[] SEMI_VALUE = {1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2};

    public static List columnPropertyForDetailsTable(DeductionDetailsDTO detailsDto) {
        List list = new ArrayList();

        String fullFromDateArr[] = detailsDto.getDetailsFromDate() == null || StringUtils.EMPTY.equals(detailsDto.getDetailsFromDate()) || ConstantsUtils.NULL.equals(detailsDto.getDetailsFromDate()) ? detailsDto.getForecastFromDate().split("-") : detailsDto.getDetailsFromDate().split("-");
        String fullToDateArr[] = detailsDto.getDetailsToDate() == null || StringUtils.EMPTY.equals(detailsDto.getDetailsToDate()) || ConstantsUtils.NULL.equals(detailsDto.getDetailsToDate()) ? detailsDto.getForecastToDate().split("-") : detailsDto.getDetailsToDate().split("-");
        int startYear = Integer.valueOf(String.valueOf(fullFromDateArr[0]));
        int endYear = Integer.valueOf(String.valueOf(fullToDateArr[0]));
        int startPeriod = 0;
        int endPeriod = 0;
        int startYearLoop = Integer.valueOf(String.valueOf(fullFromDateArr[0]));
        if ("Quarterly".equalsIgnoreCase(detailsDto.getFrequency())) {
            endPeriod = NumericConstants.FOUR;
            startPeriod = QUATER_VALUE[Integer.valueOf(String.valueOf(fullFromDateArr[1])) - 1];
        } else if ("Semi-Annual".equalsIgnoreCase(detailsDto.getFrequency())) {
            endPeriod = NumericConstants.TWO;
            startPeriod = SEMI_VALUE[Integer.valueOf(String.valueOf(fullFromDateArr[1])) - 1];
        } else if ("Monthly".equalsIgnoreCase(detailsDto.getFrequency())) {
            endPeriod = NumericConstants.TWELVE;
            startPeriod = Integer.valueOf(String.valueOf(fullFromDateArr[1]));
        }
        for (int i = startYear; i <= endYear; i++) {
            if (i == endYear) {
                endPeriod = Integer.valueOf(String.valueOf(fullToDateArr[1]));
            }
            if ("Annual".equalsIgnoreCase(detailsDto.getFrequency())) {
                list.add(StringUtils.EMPTY + startYearLoop);
            } else {
                for (int j = startPeriod; j <= endPeriod; j++) {
                    if ("Quarterly".equalsIgnoreCase(detailsDto.getFrequency())) {
                        list.add("Q" + j + " " + startYearLoop);
                    } else if ("Semi-Annual".equalsIgnoreCase(detailsDto.getFrequency())) {
                        list.add("S" + j + " " + startYearLoop);
                    } else if ("Monthly".equalsIgnoreCase(detailsDto.getFrequency())) {
                        list.add(StringUtils.EMPTY + CURRENT_MONTH_HEADER[j - 1] + " " + startYearLoop);
                    }
                }
                startPeriod = 1;
            }
            startYearLoop = startYearLoop + 1;
        }
        return list;
    }

    public static String columnProperty(int year, int month, DeductionDetailsDTO detailsDto) {
        String column = StringUtils.EMPTY;
        if ("Annual".equalsIgnoreCase(detailsDto.getFrequency())) {
            column = String.valueOf(year);
        } else {
            if ("Quarterly".equalsIgnoreCase(detailsDto.getFrequency())) {
                column = "q" + month + StringUtils.EMPTY + String.valueOf(year);
            } else if ("Semi-Annual".equalsIgnoreCase(detailsDto.getFrequency())) {
                column = "s" + month + StringUtils.EMPTY + String.valueOf(year);
            } else if ("Monthly".equalsIgnoreCase(detailsDto.getFrequency())) {
                column = StringUtils.EMPTY + CURRENT_MONTH[month - 1] + "~" + String.valueOf(year);
            }
        }
        return column;
    }

    public static int getMonth(String month) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        int j = 1;
        for (int i = 0; i < CURRENT_MONTH_HEADER.length; i++) {
            map.put(CURRENT_MONTH_HEADER[i], j);
            j++;
        }
        return map.get(month);
    }

    public static Date stringToDateFormat(final String dateString) throws ParseException {
        final DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        date = inputFormat.parse(dateString);
        return date;
    }

    /**
     * Procedure Call
     *
     * @param procedureName
     * @param orderedArgs
     * @return
     */
    public static List<Object[]> callProcedure(String procedureName, Object[] orderedArgs) {
        LOGGER.debug(" Inside callProcedure with Procedure Name " + procedureName);
        Connection connection = null;
        DataSource datasource;
        CallableStatement statement = null;
        List<Object[]> objectList = new ArrayList<Object[]>();
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
            if (datasource != null) {
                connection = datasource.getConnection();
            }
            if (connection != null) {
                String procedureToCall = "{call " + procedureName;
                int noOfArgs = orderedArgs.length;
                for (int i = 0; i < noOfArgs; i++) {
                    if (i == 0) {
                        procedureToCall += "(";
                    }
                    procedureToCall += "?,";
                    if (i == noOfArgs - 1) {
                        procedureToCall += ")";
                    }
                }
                procedureToCall = procedureToCall.replace(",)", ")");
                procedureToCall += "}";
                statement = connection.prepareCall(procedureToCall);
                for (int i = 0; i < noOfArgs; i++) {
                    LOGGER.debug(orderedArgs[i]);
                    statement.setObject(i + 1, orderedArgs[i]);
                }
                try {
                 statement.execute();
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }
        } catch (NamingException ex) {
            LOGGER.error(ex);
        } catch (SQLException ex) {
            LOGGER.error(ex);
        } finally {
            try {
                statement.close();
                connection.close();
                System.gc();
            } catch (SQLException ex) {
                LOGGER.error(ex);
            }
        }
        LOGGER.debug(" Ending callProcedure");
        return objectList;
    }

}
