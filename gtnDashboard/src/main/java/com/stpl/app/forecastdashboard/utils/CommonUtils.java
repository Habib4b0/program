/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.forecastdashboard.utils;

import com.vaadin.addon.ipcforliferay.LiferayIPC;
import com.vaadin.ui.Label;
import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import org.vaadin.jouni.dom.Dom;

/**
 *
 * @author srithar
 */
public class CommonUtils {

    private static LiferayIPC liferayIpc;
    
    public static String BUSINESS_PROCESS_TYPE = "BUSINESS_PROCESS_TYPE";
    
    public static String COMMERCIAL = "Commercial";
    
    public static String GOVERNMENT = "Government";
    
    public static String RETURNS = "Returns";
    
    public static String ANNUAL = "ANNUAL";
    
    
    
    
    public static String NATIONAL_ASSUMPTIONS="National Assumptions";
    
    public static String ACCRUAL_RATE_PROJECTION="AccrualRate Projection";
    public static String ANNUALLY="Annually";
    public static String SEMI_ANNUALLY="Semi-Annually";
    public static String QUARTERLY="Quarterly";
    public static String MONTHLY="Monthly";
    public static String SEMI_ANNUAL="SEMI-ANNUAL";
    public static String ANNUAL1="Annual";
    public static String SEMIANNUAL="Semi-Annual";
    public static String QUARTER="Quarter";
    public static String MONTH="Month";
    public static String SALES="Sales";
    public static String LINE_CHART="Line Chart";
    public static String BAR_CHART="Bar Chart";
    public static String PIE_CHART="Pie Chart";
    public static String DISCOUNT="Discount";
    public static String STACK_CHART="Stack Chart";

    public static LiferayIPC getLiferayIPC() {
        return liferayIpc;
    }

    public static void setLiferayIPC(LiferayIPC liferayIpc) {
        CommonUtils.liferayIpc = liferayIpc;
    }

    public static void formatLabel(Label label) {
        new Dom(label).setAttribute("style", "font-weight: 400; font-size: 15px; padding-top: 5px; padding-right: 10px; width: 109px;");
    }
    
     public static void formatCustomLabel(Label label) {
        new Dom(label).setAttribute("style", "font-weight: 400; font-size: 15px; padding-top: 5px; padding-right: 0px; width: 90px;");
    }

    public static String formatDecimalPlaces(String value) {
        String stringValue = "";
        if (value != null && !value.isEmpty()) {
            BigDecimal bd = new BigDecimal(value);
            DecimalFormat df = new DecimalFormat("#,##0.00");
            stringValue = df.format(bd);
        }
        return stringValue;
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
    
    public static Map<String,String> getDBPeriodColumnName(){
        Map<String,String> periodDBColumnName = new HashMap<String,String>();
        periodDBColumnName.put(CommonUtils.ANNUAL, ANNUALLY);
        periodDBColumnName.put(CommonUtils.SEMI_ANNUAL, "SEMI_ANNUAL");
        periodDBColumnName.put(CommonUtils.QUARTERLY, "QUARTER");
        periodDBColumnName.put(CommonUtils.MONTHLY, "MONTH");
        return periodDBColumnName;
    }
}
