/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.gtnbalancereport.utils;

import com.stpl.app.accountclose.gtnbalancereport.logic.DataSelectionLogic;
import static com.stpl.app.accountclose.gtnbalancereport.utils.Constants.RegexConstants.REGEX_EXTRACT_DIGITS;
import static com.stpl.app.accountclose.utils.Constants.LabelConstants.MODE_SEARCH;
import static com.stpl.app.accountclose.utils.Constants.SELECT_ONE;
import static com.stpl.app.accountclose.utils.Constants.FrequencyConstants.ANNUALLY;
import static com.stpl.app.accountclose.utils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.accountclose.utils.Constants.FrequencyConstants.SEMI_ANNUALLY;
import static com.stpl.app.accountclose.utils.Constants.FrequencyConstants.QUARTERLY;
import com.stpl.app.model.ForecastConfig;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.ComboBox;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author santanukumar
 */
public class DataSelectionUtil {

    private static final Logger LOGGER = Logger.getLogger(DataSelectionUtil.class);

    public static List<String> configureTimeDdlb(ComboBox fromPeriod, ComboBox toPeriod, Date from, Date to, final String mode, final String dateFlag, String frequency) {

        Date fromDate = null;
        Date toDate = null;
        DataSelectionLogic logic = new DataSelectionLogic();
        if ("DS".equals(dateFlag)) {
            try {
                ForecastConfig forecastConfig = logic.getTimePeriod();
                if (forecastConfig != null) {
                    fromDate = forecastConfig.getFromDate();
                    toDate = forecastConfig.getToDate();
                }
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        } else if ("BR".equals(dateFlag)) {
            fromDate = from;
            toDate = to;
        }
        List<String> timePeriodList = new ArrayList<String>();
        if (MODE_SEARCH.getConstant().equalsIgnoreCase(mode)) {
            timePeriodList.add(SELECT_ONE);
        }
        try {
            timePeriodList.addAll(getTimePeriodList(fromDate, toDate, frequency));
        } catch (Exception e) {
            LOGGER.error(e);
        }
        fromPeriod.setContainerDataSource(new IndexedContainer(timePeriodList));
        toPeriod.setContainerDataSource(new IndexedContainer(timePeriodList));
        if (MODE_SEARCH.getConstant().equalsIgnoreCase(mode)) {
            fromPeriod.select(timePeriodList.get(0));
            toPeriod.select(timePeriodList.get(0));
        } else {
            if (from == null && to == null) {
                fromPeriod.select(timePeriodList.get(0));
                toPeriod.select(timePeriodList.get(timePeriodList.size() - 1));
            } else {
                try {
                    String fromValue = DataSelectionUtil.getTimePeriod(from, frequency);
                    String toValue = DataSelectionUtil.getTimePeriod(to, frequency);
                    fromPeriod.select(fromValue);
                    toPeriod.select(toValue);
                } catch (ParseException ex) {
                    LOGGER.error(ex);
                }
            }
        }

        return timePeriodList;

    }

    public static List<String> configureMethodlogyTimeDdlb(ComboBox fromPeriod, ComboBox toPeriod, Date from, Date to, final String mode, final String dateFlag, String frequency) throws Exception {
        Date fromDate = null;
        Date toDate = null;
        DataSelectionLogic logic = new DataSelectionLogic();
        if ("DS".equals(dateFlag)) {
            ForecastConfig forecastConfig = logic.getTimePeriod();
            if (forecastConfig != null) {
                fromDate = forecastConfig.getFromDate();
                toDate = forecastConfig.getToDate();
            }
        } else if ("BR".equals(dateFlag)) {
            fromDate = from;
            toDate = to;
        }
        List<String> timePeriodList = new ArrayList<String>();
        if (MODE_SEARCH.getConstant().equalsIgnoreCase(mode)) {
            timePeriodList.add(SELECT_ONE);
        }
        timePeriodList.addAll(getTimePeriodList(fromDate, toDate, frequency));
        fromPeriod.setContainerDataSource(new IndexedContainer(timePeriodList));
        toPeriod.setContainerDataSource(new IndexedContainer(timePeriodList));
        if (MODE_SEARCH.getConstant().equalsIgnoreCase(mode)) {
        } else {
            if (from == null && to == null) {
            } else {
                String fromValue = DataSelectionUtil.getTimePeriod(from, frequency);
                String toValue = DataSelectionUtil.getTimePeriod(to, frequency);
            }
        }
        return timePeriodList;
    }

    public static String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getShortMonths();
        if (num >= 0 && num <= 11) {
            month = months[num];
        }
        return month;
    }

    public static List<String> getTimePeriodList(Date start, Date end, String frequency) throws ParseException {
        SimpleDateFormat getYear = new SimpleDateFormat("yyyy");
        List<String> quartersList = new ArrayList<String>();
        int startQuarter = 0;
        int endQuarter = 0;
        int startYear = start.getYear() + 1900;
        int limit = 0;
        int endYear = end.getYear() + 1900;
        if (ANNUALLY.getConstant().equals(frequency)) {
            startQuarter = startYear;
            endQuarter = endYear;
            limit = endYear;
        } else if (SEMI_ANNUALLY.getConstant().equals(frequency)) {
            startQuarter = getPeriod(start.getMonth() + 1, 6);
            endQuarter = getPeriod(end.getMonth() + 1, 6);
            limit = 2;
        } else if (QUARTERLY.getConstant().equals(frequency)) {
            startQuarter = getQuarter(start);
            endQuarter = getQuarter(end);
            limit = 4;
        } else if (MONTHLY.getConstant().equals(frequency)) {
            startQuarter = start.getMonth() + 1;
            endQuarter = end.getMonth() + 1;
            limit = 12;
        }
        while (startYear <= endYear) {
            if (startYear == endYear) {
                limit = endQuarter;
            }
            if (!ANNUALLY.getConstant().equals(frequency)) {

                while (startQuarter <= limit) {
                    if (SEMI_ANNUALLY.getConstant().equals(frequency)) {
                        quartersList.add("S" + startQuarter + " - " + startYear);
                    } else if (QUARTERLY.getConstant().equals(frequency)) {
                        quartersList.add("Q" + startQuarter + " - " + startYear);
                    } else if (MONTHLY.getConstant().equals(frequency)) {
                        quartersList.add(getMonthForInt(startQuarter - 1) + " - " + startYear);
                    }
                    startQuarter++;
                }
                startQuarter = 1;
            } else {
                quartersList.add(StringUtils.EMPTY + startYear);
            }
            startYear++;
        }
        return quartersList;
    }

    private static int getQuarter(Date end) {
        SimpleDateFormat getMonth = new SimpleDateFormat("MM");
        return Integer.parseInt(getMonth.format((end))) % 3 == 0 ? (Integer.parseInt(getMonth.format((end))) / 3) : (Integer.parseInt(getMonth.format((end))) / 3) + 1;
    }

    static int getPeriod(int monthNo, int division) {
        return ((monthNo - 1) / division) + 1;
    }

    public static String getTimePeriod(Date date, String frequency) throws ParseException {
        String timePeriod = StringUtils.EMPTY;
        SimpleDateFormat getYear = new SimpleDateFormat("yyyy");
        int quarter = 0;
        int year = Integer.parseInt(getYear.format((date)));
        if (ANNUALLY.getConstant().equals(frequency)) {
            timePeriod = StringUtils.EMPTY + year;
        } else if (SEMI_ANNUALLY.getConstant().equals(frequency)) {
            quarter = getPeriod(date.getMonth() + 1, 6);
            timePeriod = "S" + quarter + " - " + year;
        } else if (QUARTERLY.getConstant().equals(frequency)) {
            quarter = getQuarter(date);
            timePeriod = "Q" + quarter + " - " + year;
        } else if (MONTHLY.getConstant().equals(frequency)) {
            quarter = date.getMonth() + 1;
            timePeriod = getMonthForInt(quarter - 1) + " - " + year;
        }

        return timePeriod;
    }

    public static String getDateFromQuarter(String quarter) {
        String slash = "/";
        String dd = "01";
        String MM = "01";
        String date = StringUtils.EMPTY;
        String split[] = quarter.split(" - ");
        String splitQuarter = split[0];
        int quarterValue = parseStringToInteger(splitQuarter);
        String yyyy = split[1];
        if (quarterValue == 1) {
            MM = "01";
        } else if (quarterValue == 2) {
            MM = "04";
        } else if (quarterValue == 3) {
            MM = "07";
        } else if (quarterValue == 4) {
            MM = "10";
        }

        date = MM + slash + dd + slash + yyyy;
        return date;
    }

    public static int parseStringToInteger(final String value) {
        return (value.equals(StringUtils.EMPTY) || "null".equals(value) ? 0 : Integer.parseInt(value.replaceAll(REGEX_EXTRACT_DIGITS.getConstant(), StringUtils.EMPTY)));
    }

    public static String getLastDateFromQuarter(String quarter) {
        String slash = "/";
        String dd = "30";
        String MM = "01";
        String date = StringUtils.EMPTY;
        String split[] = quarter.split(" - ");
        String splitQuarter = split[0];
        int quarterValue = parseStringToInteger(splitQuarter);
        String yyyy = split[1];
        if (quarterValue == 1) {
            MM = "03";
            dd = "31";
        } else if (quarterValue == 2) {
            MM = "06";
            dd = "30";
        } else if (quarterValue == 3) {
            MM = "09";
            dd = "30";
        } else if (quarterValue == 4) {
            MM = "12";
            dd = "31";
        }

        date = MM + slash + dd + slash + yyyy;
        return date;
    }

    public static String getDateFromSemiannualy(String semiannual) {
        String slash = "/";
        String dd = "01";
        String MM = "01";
        String date = StringUtils.EMPTY;
        String split[] = semiannual.split(" - ");
        String splitQuarter = split[0];
        int semiValue = parseStringToInteger(splitQuarter);
        String yyyy = split[1];
        if (semiValue == 1) {
            MM = "01";
        } else if (semiValue == 2) {
            MM = "07";
        }

        date = MM + slash + dd + slash + yyyy;
        return date;
    }

    public static String getLastDateFromSemiannually(String quarter) {
        String slash = "/";
        String dd = "30";
        String MM = "01";
        String date = StringUtils.EMPTY;
        String split[] = quarter.split(" - ");
        String splitQuarter = split[0];
        int quarterValue = parseStringToInteger(splitQuarter);
        String yyyy = split[1];
        if (quarterValue == 1) {
            MM = "06";
            dd = "30";
        } else if (quarterValue == 2) {
            MM = "12";
            dd = "31";
        }

        date = MM + slash + dd + slash + yyyy;
        return date;
    }

    public static String getDateFromAnnual(String annual) {
        String slash = "/";
        String dd = "01";
        String MM = "01";
        String date = StringUtils.EMPTY;
        String yyyy = annual.trim();
        date = MM + slash + dd + slash + yyyy;
        return date;
    }

    public static String getLastDateFromAnnual(String annual) {
        String slash = "/";
        String dd = "31";
        String MM = "12";
        String date = StringUtils.EMPTY;
        String yyyy = annual.trim();
        date = MM + slash + dd + slash + yyyy;
        return date;
    }
}
