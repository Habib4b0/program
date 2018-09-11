/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.gtnforecasting.salesprojection.utils;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import static com.stpl.app.gtnforecasting.logic.CommonLogic.LOGGER;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.service.ChProjectionSelectionLocalServiceUtil;
import static com.stpl.app.utils.Constants.FrequencyConstants.ANNUAL;
import static com.stpl.app.utils.Constants.FrequencyConstants.ANNUALLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUALLY;
import com.stpl.ifs.ui.util.GtnSmallHashMap;
import com.stpl.ifs.ui.util.NumericConstants;
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
    
  private SalesUtils() {
        // SalesUtils
    }
   
    public static final String MANDATED_PRO_NAME = "{call Prc_mandated_sales_insert (?,?,?)}";
    public static final String NM_SALES_PRO_NAME = "{call PRC_NM_SALES_INSERT (?,?,?)}";
    public static final String RETURNS_SALES_INSERT_PRO_NAME = "{call PRC_RETURNS_INSERT (?,?,?)}";
    public static final String RETURNS_SALES_CALCULATE_PRO_NAME = "{call PRC_RETURNS_PROJECTION (?,?,?,?)}";
    public static final String PRC_NM_ACTUAL_INSERT = " PRC_NM_ACTUAL_INSERT ";
    public static final String PRC_NM_MASTER_INSERT = " PRC_NM_MASTER_INSERT ";
    public static final String PRC_NM_PROJECTION_INSERT = " PRC_NM_PROJECTION_INSERT ";
    public static final String PRC_PROJECTION_RESULTS = "PRC_PROJECTION_RESULTS";
    public static final String PRC_NA_WAC_DATA="PRC_NA_WAC_DATA";
    public static final String PRC_NM_PPA_INSERT = "PRC_NM_PPA_INSERT";
    public static final String PRC_NM_PPA_CALC = "PRC_NM_PPA_PROJECTION";
    public  static  final String PRC_NM_PPA_PROJ_INIT="PRC_NM_PPA_PROJ_INIT";
    public  static final String PRC_NM_PPA_PROJECTION="PRC_NM_PPA_PROJECTION";
    
      public static String getPeriodFrequecy(final String frequency) {
        GtnSmallHashMap periodFrequencyMap = new GtnSmallHashMap();
        periodFrequencyMap.put(QUARTERLY.getConstant(), Constant.QUARTER);
        periodFrequencyMap.put(ANNUALLY.getConstant(), "YEAR");
        periodFrequencyMap.put(ANNUAL.getConstant(), "YEAR");
        periodFrequencyMap.put(MONTHLY.getConstant(), "MONTH");
        periodFrequencyMap.put(SEMI_ANNUALLY.getConstant(), Constant.SEMI_ANNUAL);
        return String.valueOf(periodFrequencyMap.get(frequency));
    }
      
       public static String getPeriodCountFrequecy(final String frequency) {
        GtnSmallHashMap periodCountFrequencyMap = new GtnSmallHashMap();
        periodCountFrequencyMap.put(QUARTERLY.getConstant(), "3");
        periodCountFrequencyMap.put(ANNUALLY.getConstant(), Constant.STRING_ONE);
        periodCountFrequencyMap.put(ANNUAL.getConstant(), Constant.STRING_ONE);
        periodCountFrequencyMap.put(MONTHLY.getConstant(), "12");
        periodCountFrequencyMap.put(SEMI_ANNUALLY.getConstant(), "2");
        return String.valueOf(periodCountFrequencyMap.get(frequency));
    }
       public static GtnSmallHashMap getMonthMap() {
        GtnSmallHashMap monthMap = new GtnSmallHashMap();
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
        String quarter;
        String year;
        quarter = period.substring(0, NumericConstants.TWO);
        year = period.substring(NumericConstants.TWO, period.length());
        quarter = quarter.replace('q', 'Q');
        return quarter + " " + year;
    }
       public static int[] getQuarterAndYear(String period) {
           String periodQuaterAndyear = period;
        int[] quarterAndYear = new int[NumericConstants.TWO];
        periodQuaterAndyear = periodQuaterAndyear.replace(Constant.Q, StringUtils.EMPTY);
        String[] splitPeriod = periodQuaterAndyear.split(" ");
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

        } else if (startQuater == NumericConstants.TWO) {
            startCal.set(Calendar.MONTH, NumericConstants.THREE);
        } else if (startQuater == NumericConstants.THREE) {
            startCal.set(Calendar.MONTH, NumericConstants.SIX);
        } else if (startQuater == NumericConstants.FOUR) {
            startCal.set(Calendar.MONTH, NumericConstants.NINE);
        }
        if (endQuater == 1) {
            endCal.set(Calendar.DATE, NumericConstants.THIRTY_ONE);
            endCal.set(Calendar.MONTH, NumericConstants.TWO);
        } else if (endQuater == NumericConstants.TWO) {
            endCal.set(Calendar.DATE, NumericConstants.THIRTY);
            endCal.set(Calendar.MONTH, NumericConstants.FIVE);
        } else if (endQuater == NumericConstants.THREE) {
            endCal.set(Calendar.DATE, NumericConstants.THIRTY);
            endCal.set(Calendar.MONTH, NumericConstants.EIGHT);
        } else if (endQuater == NumericConstants.FOUR) {
            endCal.set(Calendar.DATE, NumericConstants.THIRTY_ONE);
            endCal.set(Calendar.MONTH, NumericConstants.ELEVEN);
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
        List<Object[]> list = new ArrayList<>();
        Map<Object, Object> map = new HashMap<>();
        DynamicQuery query = ChProjectionSelectionLocalServiceUtil.dynamicQuery();
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
                    Object[] obj = list.get(i);
                    map.put(obj[0], obj[1]);
                }
            }
            return map;
        } catch (SystemException ex) {
            LOGGER.error(StringUtils.EMPTY,ex);
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
