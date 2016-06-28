/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.forecastdashboard.utils;

import com.vaadin.addon.ipcforliferay.LiferayIPC;
import com.vaadin.ui.Label;
import java.math.BigDecimal;
import java.text.DecimalFormat;
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
    
    public static String NATIONAL_ASSUMPTIONS="National Assumptions";
    
    public static String ACCRUAL_RATE_PROJECTION="AccrualRate Projection";

    public static LiferayIPC getLiferayIPC() {
        return liferayIpc;
    }

    public static void setLiferayIPC(LiferayIPC liferayIpc) {
        CommonUtils.liferayIpc = liferayIpc;
    }

    public static void formatLabel(Label label) {
        new Dom(label).setAttribute("style", "font-weight: 400; font-size: 15px; padding-top: 5px; padding-right: 10px;");
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
}
