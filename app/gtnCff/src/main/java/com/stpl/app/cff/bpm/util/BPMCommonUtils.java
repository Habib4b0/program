package com.stpl.app.cff.bpm.util;

import com.stpl.ifs.ui.util.NumericConstants;
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

    private  static String groupId = "com.stpl.app.bpm";
    private  static String artifactId = "Non-Mandated";
    private  static String version = "1.0";

    public static List<Date> getPreviousQuarterDates() {
        Calendar cal = Calendar.getInstance();
        int prevQ = getPrevQuarter(cal.get(Calendar.MONTH));
        int prevY = cal.get(Calendar.MONTH) >= NumericConstants.THREE ? cal.get(Calendar.YEAR) : cal.get(Calendar.YEAR) - 1;
        return getStartDate("Q" + prevQ, prevY);
    }

    public static int getPrevQuarter(int month) {
        switch (month / NumericConstants.THREE) {
            case 0:
                return NumericConstants.FOUR;
            case 1:
                return 1;
            case NumericConstants.TWO:
                return NumericConstants.TWO;
            case NumericConstants.THREE:
                return NumericConstants.THREE;
            default:
                return 1;
        }
    }

    public static List<Date> getStartDate(String quarter, int year) {

        List<Date> dateList = new ArrayList<>();
        int result = getQuarter(quarter);
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.set(year, result, 1, 0, 0, 0);
        if (result == 0) {
            endDate.set(year, NumericConstants.TWO, 1, 0, 0, 0);
            endDate.set(year, NumericConstants.TWO, endDate.getActualMaximum(Calendar.DAY_OF_MONTH));

        }
        if (result == NumericConstants.THREE) {
            endDate.set(year, NumericConstants.FIVE, 1, 0, 0, 0);
            endDate.set(year, NumericConstants.FIVE, endDate.getActualMaximum(Calendar.DAY_OF_MONTH));
        }
        if (result == NumericConstants.SIX) {
            endDate.set(year, NumericConstants.SEVEN, 1, 0, 0, 0);
            endDate.set(year, NumericConstants.SEVEN, endDate.getActualMaximum(Calendar.DAY_OF_MONTH));
        }
        if (result == NumericConstants.NINE) {
            endDate.set(year, NumericConstants.ELEVEN, 1, 0, 0, 0);
            endDate.set(year, NumericConstants.ELEVEN, endDate.getActualMaximum(Calendar.DAY_OF_MONTH));
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
