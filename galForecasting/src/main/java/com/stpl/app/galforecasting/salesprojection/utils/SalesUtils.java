/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.galforecasting.salesprojection.utils;

import static com.stpl.app.galforecasting.logic.CommonLogic.LOGGER;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.app.model.ChProjectionSelection;
import com.stpl.app.service.ChProjectionSelectionLocalServiceUtil;
import static com.stpl.app.utils.Constants.FrequencyConstants.ANNUAL;
import static com.stpl.app.utils.Constants.FrequencyConstants.ANNUALLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUALLY;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author sibi
 */
public class SalesUtils {
    
    
     public static String MANDATED_PRO_NAME = "{call Prc_mandated_sales_insert (?,?,?)}";
     public static String NM_SALES_PRO_NAME = "{call PRC_NM_SALES_INSERT (?,?,?)}";
     public static String RETURNS_SALES_INSERT_PRO_NAME = "{call PRC_RETURNS_INSERT (?,?,?)}";
      public static String RETURNS_SALES_CALCULATE_PRO_NAME = "{call PRC_RETURNS_PROJECTION (?,?,?)}";
    
    
      public static String getPeriodFrequecy(final String frequency) {
        Map<String, String> periodFrequencyMap = new HashMap<String, String>();
        periodFrequencyMap.put(QUARTERLY.getConstant(), "QUARTER");
        periodFrequencyMap.put(ANNUALLY.getConstant(), "YEAR");
        periodFrequencyMap.put(ANNUAL.getConstant(), "YEAR");
        periodFrequencyMap.put(MONTHLY.getConstant(), "MONTH");
        periodFrequencyMap.put(SEMI_ANNUALLY.getConstant(), "SEMI_ANNUAL");
        return String.valueOf(periodFrequencyMap.get(frequency));
    }
      
       public static String getPeriodCountFrequecy(final String frequency) {
        Map<String, String> periodCountFrequencyMap = new HashMap<String, String>();
        periodCountFrequencyMap.put(QUARTERLY.getConstant(), "3");
        periodCountFrequencyMap.put(ANNUALLY.getConstant(), Constant.STRING_ONE);
        periodCountFrequencyMap.put(ANNUAL.getConstant(), Constant.STRING_ONE);
        periodCountFrequencyMap.put(MONTHLY.getConstant(), "12");
        periodCountFrequencyMap.put(SEMI_ANNUALLY.getConstant(), "2");
        return String.valueOf(periodCountFrequencyMap.get(frequency));
    }
       public static Map<String, String> getMonthMap() {
        Map<String, String> monthMap = new HashMap<String, String>();
        monthMap.put(Constant.STRING_ONE, "jan");
        monthMap.put("2", "feb");
        monthMap.put("3", "mar");
        monthMap.put("4", "apr");
        monthMap.put("5", "may");
        monthMap.put("6", "jun");
        monthMap.put("7", "jul");
        monthMap.put("8", "aug");
        monthMap.put("9", "sep");
        monthMap.put("10", "oct");
        monthMap.put("11", "nov");
        monthMap.put("12", "dec");
        return monthMap;
    }  
       public static String generateBaseLine(String period) {
        String quarter = StringUtils.EMPTY;
        String year = StringUtils.EMPTY;
        quarter = period.substring(0, 2);
        year = period.substring(2, period.length());
        quarter = quarter.replace(Constant.Q_SMALL, Constant.Q);
        return quarter + " " + year;
    }
       public static int[] getQuarterAndYear(String period) {
        int[] quarterAndYear = new int[2];
        period = period.replace(Constant.Q, StringUtils.EMPTY);
        String[] splitPeriod = period.split(" ");
        quarterAndYear[0] = Integer.parseInt(splitPeriod[0]);
        quarterAndYear[1] = Integer.parseInt(splitPeriod[1]);
        return quarterAndYear;
    }
        public static List<Date> getStartAndEndDate(int startQuater, int endQuater, int startYear, int endYear) {
        List result = new ArrayList();
        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        startCal.set(Calendar.DATE, 1);
        startCal.set(Calendar.YEAR, startYear);
        endCal.set(Calendar.YEAR, endYear);
        if (startQuater == 1) {
            startCal.set(Calendar.MONTH, 0);

        } else if (startQuater == 2) {
            startCal.set(Calendar.MONTH, 3);
        } else if (startQuater == 3) {
            startCal.set(Calendar.MONTH, 6);
        } else if (startQuater == 4) {
            startCal.set(Calendar.MONTH, 9);
        }
        if (endQuater == 1) {
            endCal.set(Calendar.DATE, 31);
            endCal.set(Calendar.MONTH, 2);
        } else if (endQuater == 2) {
            endCal.set(Calendar.DATE, 30);
            endCal.set(Calendar.MONTH, 5);
        } else if (endQuater == 3) {
            endCal.set(Calendar.DATE, 30);
            endCal.set(Calendar.MONTH, 8);
        } else if (endQuater == 4) {
            endCal.set(Calendar.DATE, 31);
            endCal.set(Calendar.MONTH, 11);
        }
        result.add(startCal.getTime());
        result.add(endCal.getTime());
        return result;
    }
        
         /**
     * Get projection selection
     *
     * @param projectionId
     * @param screenName
     * @return
     */
    public static Map<Object, Object> getCHProjectionSelection(final int projectionId, final String screenName) {
        List<Object[]> list = new ArrayList<Object[]>();
        Map<Object, Object> map = new HashMap<Object, Object>();
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(ChProjectionSelection.class);
        query.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));
        query.add(RestrictionsFactoryUtil.eq(Constant.SCREEN_NAME, screenName));
        ProjectionList projectionListFrom = ProjectionFactoryUtil.projectionList();
        projectionListFrom.add(ProjectionFactoryUtil.property(Constant.FIELD_NAME));
        projectionListFrom.add(ProjectionFactoryUtil.property(Constant.FIELD_VALUES));
        query.setProjection(ProjectionFactoryUtil.distinct(projectionListFrom));
        try {
            list = ChProjectionSelectionLocalServiceUtil.dynamicQuery(query);
            if (list != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    Object[] obj = (Object[]) list.get(i);
                    map.put(obj[0], obj[1]);
                }
            }
            return map;
        } catch (Exception ex) {
            LOGGER.info(ex.getCause());
        }
        return null;
    }
     public static String valueToCurrency(String value, DecimalFormat decimalFormat) {
         
        String currencyValue = "$";
        currencyValue += decimalFormat.format(Double.valueOf(value));
        return currencyValue;

    }
      public static Object currencyToValue(String currency){
        return Double.valueOf(currency.substring(1));         
     }
}
