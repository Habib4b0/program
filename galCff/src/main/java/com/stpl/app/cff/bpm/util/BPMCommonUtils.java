package com.stpl.app.cff.bpm.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author arulmurugan
 */
public class BPMCommonUtils {

    public static String groupId = "com.stpl.app.bpm";
    public static String artifactId = "Non-Mandated";
    public static String version = "1.0";

    public static List<Date> getPreviousQuarterDates() {
        Calendar cal = Calendar.getInstance();
        int prevQ = getPrevQuarter(cal.get(Calendar.MONTH));
        int prevY = cal.get(Calendar.MONTH) >= 3 ? cal.get(Calendar.YEAR) : cal.get(Calendar.YEAR) - 1;
        return getStartDate("Q" + prevQ, prevY);
    }

    public static int getPrevQuarter(int month) {
        switch (month / 3) {
            case 0:
                return 4;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            default:
                return 1;
        }
    }

    public static List<Date> getStartDate(String quarter, int year) {

        List<Date> dateList = new ArrayList<Date>();
        int result = getQuarter(quarter);
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.set(year, result, 1, 0, 0, 0);
        if (result == 0) {
            endDate.set(year, 2, 1, 0, 0, 0);
            endDate.set(year, 2, endDate.getActualMaximum(Calendar.DAY_OF_MONTH));

        }
        if (result == 3) {
            endDate.set(year, 5, 1, 0, 0, 0);
            endDate.set(year, 5, endDate.getActualMaximum(Calendar.DAY_OF_MONTH));
        }
        if (result == 6) {
            endDate.set(year, 7, 1, 0, 0, 0);
            endDate.set(year, 7, endDate.getActualMaximum(Calendar.DAY_OF_MONTH));
        }
        if (result == 9) {
            endDate.set(year, 11, 1, 0, 0, 0);
            endDate.set(year, 11, endDate.getActualMaximum(Calendar.DAY_OF_MONTH));
        }
        dateList.add(startDate.getTime());
        dateList.add(endDate.getTime());
        return dateList;
    }

    public static int getQuarter(String quarter) {
        int result = 0;
        if ("Q1".equalsIgnoreCase(quarter)) {
            result = Calendar.JANUARY;
        } else if ("Q2".equalsIgnoreCase(quarter)) {
            result = Calendar.APRIL;
        } else if ("Q3".equalsIgnoreCase(quarter)) {
            result = Calendar.JULY;
        } else if ("Q4".equalsIgnoreCase(quarter)) {
            result = Calendar.OCTOBER;
        }
        return result;
    }

    public static String getConvertedQuarters(String selectedQuarters) {
        return selectedQuarters.replaceAll("Q", StringUtils.EMPTY).replaceAll(" ", StringUtils.EMPTY);
    }

}
