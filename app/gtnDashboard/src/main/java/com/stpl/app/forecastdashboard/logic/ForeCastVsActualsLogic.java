/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.forecastdashboard.logic;

import com.stpl.app.forecastdashboard.dao.ForecastDashboardDAO;
import com.stpl.app.forecastdashboard.dao.impl.ForecastDashboardDAOImpl;
import com.stpl.app.forecastdashboard.utils.CommonUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jboss.logging.Logger;

/**
 *
 * @author srithar
 */
public class ForeCastVsActualsLogic {

    ForecastDashboardDAO dao = new ForecastDashboardDAOImpl();
    static String frequency=CommonUtils.QUARTER;;
    static String tabName=CommonUtils.SALES;
    String chartType=CommonUtils.LINE_CHART;
    String commercialActulsQuery;
     String commercialProjectionQuery;
     String governmentActualsQuery;
     String governmentProjectionQuery;
     String returnsActualsQuery;
//     String returnsProjectionQuery;
     public static final SimpleDateFormat DBDate = new SimpleDateFormat("yyyy-MM-dd");
     
     
      public static void setFrequency(final String frequency1) {
        frequency = frequency1;
    }
      public static void setTabName(final String tabname1) {
        tabName = tabname1;
    }
      public  void setChartType(final String chartType1) {
        this.chartType = chartType1;
    }
    
    
    private static final Logger LOGGER = Logger.getLogger(BigFiveLogic.class);
    
    public void getQuery(String freq,String tab,String chart){
         String amount="SALES_AMOUNT";
     String projectiontable="PROJECTION_SALES";
     if("Semi-Annual".equals(freq)){
         freq="SEMI_ANNUAL";
     }
        if(tab.equals(CommonUtils.SALES) || tab.equals("Results")){
            amount="SALES_AMOUNT";
            projectiontable="SALES_PROJECTION";
        }else {
            amount="AMOUNT";
             projectiontable="DISCOUNT_PROJECTION";
        }
        
        if(!"SEMI_ANNUAL".equals(freq)){
     commercialActulsQuery = "SELECT ISNULL (Sum(A."+amount+"), 0)              AS "+amount+",\n" ;
               if(!CommonUtils.PIE_CHART.equals(chartType)){                      
                commercialActulsQuery+="	   Datepart("+freq+", ACCRUAL_ACTUAL_START_DATE) AS "+freq+",\n" ;
               }
               commercialActulsQuery+="       Year(ACCRUAL_ACTUAL_START_DATE)              AS YEAR\n" +
                                    " FROM   ACTUALS_MASTER A\n" +
                                    " WHERE  Year(ACCRUAL_ACTUAL_START_DATE)<YEAR(GETDATE())\n" +
                                    " GROUP  BY Year(ACCRUAL_ACTUAL_START_DATE)\n" ;
                                              if(!CommonUtils.PIE_CHART.equals(chartType)){
                commercialActulsQuery+=" , Datepart("+freq+", ACCRUAL_ACTUAL_START_DATE)\n" ;
                                              }
                commercialActulsQuery+=" ORDER  BY YEAR\n" ;
             if(!CommonUtils.PIE_CHART.equals(chartType)){
              commercialActulsQuery+=" , "+freq+"";
             }
        }else if("SEMI_ANNUAL".equals(freq)){
           commercialActulsQuery= "SELECT Isnull (Sum("+amount+"), 0) AS "+amount+",\n"
                    + "       SEMI_ANNUAL,\n"
                    + "       YEAR\n"
                    + "FROM   (SELECT "+amount+",\n"
                    + "               CASE\n"
                    + "                 WHEN MONTH <= 6 THEN 1\n"
                    + "                 WHEN MONTH > 6 THEN 2\n"
                    + "               END AS SEMI_ANNUAL,\n"
                    + "               YEAR\n"
                    + "        FROM   (SELECT "+amount+",\n"
                    + "                       Datepart(MONTH, ACCRUAL_ACTUAL_START_DATE) AS MONTH,\n"
                    + "                       Year(ACCRUAL_ACTUAL_START_DATE)            AS YEAR\n"
                    + "                FROM   ACTUALS_MASTER A\n"
                    + "                WHERE  Year(ACCRUAL_ACTUAL_START_DATE) < Year(Getdate()))A)B\n"
                    + "GROUP  BY B.YEAR,\n"
                    + "          B.SEMI_ANNUAL\n"
                    + "ORDER  BY YEAR,\n"
                    + "          SEMI_ANNUAL  ";
                }
     commercialProjectionQuery = "SELECT ISNULL (Sum(A.PROJECTION_SALES), 0) AS "+amount+",\n" ;
      if(!CommonUtils.PIE_CHART.equals(chartType)){
        commercialProjectionQuery+=  "       "+freq+"                             AS "+freq+",\n" ;
                                                }
         commercialProjectionQuery+= "       year                                AS YEAR\n" +
                                        " FROM   NM_"+projectiontable+" A\n" +
                                        "       JOIN PROJECTION_DETAILS B\n" +
                                        "         ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID\n" +
                                        "       JOIN PERIOD PER\n" +
                                        "         ON per.period_sid = A.period_sid\n" +
                                        " WHERE  PROJECTION_MASTER_SID IN (SELECT PROJECTION_MASTER_SID\n" +
                                        "                                 FROM   (SELECT A.PROJECTION_MASTER_SID,\n" +
                                        "                                                ROW_NUMBER()\n" +
                                        "                                                  OVER(\n" +
                                        "                                                    ORDER BY MODIFIED_DATE DESC) AS ROW_COUNT\n" +
                                        "                                         FROM   (SELECT PROJECTION_MASTER_SID,\n" +
                                        "                                                        CREATED_DATE,\n" +
                                        "                                                        ISNULL(MODIFIED_DATE, CREATED_DATE) AS MODIFIED_DATE\n" +
                                        "                                                 FROM   PROJECTION_MASTER\n" +
                                        "                                                 WHERE  IS_APPROVED = 'A' AND FORECASTING_TYPE='Non Mandated')A)B\n" +
                                        "                                 WHERE  ROW_COUNT = 1)\n" +
                                        "       AND year >=YEAR(GETDATE())\n" +
                                        " GROUP  BY ";
         if(!CommonUtils.PIE_CHART.equals(chartType)){ 
                 commercialProjectionQuery+=""+freq+",\n" ;
                         }
                commercialProjectionQuery+=" year\n" +
                                        " ORDER  BY YEAR\n" ;
       if(!CommonUtils.PIE_CHART.equals(chartType)){
                        commercialProjectionQuery+="   , "+freq+"";
       }
     if(!"SEMI_ANNUAL".equals(freq)){
     governmentActualsQuery = "SELECT ISNULL (Sum(A."+amount+"), 0)              AS "+amount+",\n" ;
                  if(!CommonUtils.PIE_CHART.equals(chartType)){                 
                governmentActualsQuery+="	   Datepart("+freq+", ACCRUAL_ACTUAL_START_DATE) AS "+freq+",\n" ;
                                            }
               governmentActualsQuery+="       Year(ACCRUAL_ACTUAL_START_DATE)              AS YEAR\n" +
                                    " FROM   ACTUALS_MASTER A\n" +
                                    " WHERE  Year(ACCRUAL_ACTUAL_START_DATE)<YEAR(GETDATE())\n" +
                                    " GROUP  BY Year(ACCRUAL_ACTUAL_START_DATE)\n" ;
                if(!CommonUtils.PIE_CHART.equals(chartType)){                 
                governmentActualsQuery+="   , Datepart("+freq+", ACCRUAL_ACTUAL_START_DATE)\n" ;
                }
                governmentActualsQuery+=" ORDER  BY YEAR\n" ;
                                     if(!CommonUtils.PIE_CHART.equals(chartType)){                 
                 governmentActualsQuery+=" , "+freq+"";
                                     }
     }else if("SEMI_ANNUAL".equals(freq)){
         governmentActualsQuery=commercialActulsQuery;
     }
     governmentProjectionQuery = "SELECT ISNULL (Sum(A.PROJECTION_SALES), 0) AS "+amount+",\n" ;
      if(!CommonUtils.PIE_CHART.equals(chartType)){                 
             governmentProjectionQuery+="       "+freq+"                             AS "+freq+",\n" ;
      }
            governmentProjectionQuery+="       year                                AS YEAR\n" +
                                        " FROM   M_"+projectiontable+" A\n" +
                                        "       JOIN PROJECTION_DETAILS B\n" +
                                        "         ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID\n" +
                                        "       JOIN PERIOD PER\n" +
                                        "         ON per.period_sid = A.period_sid\n" +
                                        " WHERE  PROJECTION_MASTER_SID IN (SELECT PROJECTION_MASTER_SID\n" +
                                        "                                 FROM   (SELECT A.PROJECTION_MASTER_SID,\n" +
                                        "                                                ROW_NUMBER()\n" +
                                        "                                                  OVER(\n" +
                                        "                                                    ORDER BY MODIFIED_DATE DESC) AS ROW_COUNT\n" +
                                        "                                         FROM   (SELECT PROJECTION_MASTER_SID,\n" +
                                        "                                                        CREATED_DATE,\n" +
                                        "                                                        ISNULL(MODIFIED_DATE, CREATED_DATE) AS MODIFIED_DATE\n" +
                                        "                                                 FROM   PROJECTION_MASTER\n" +
                                        "                                                 WHERE  IS_APPROVED = 'A' AND FORECASTING_TYPE='Mandated')A)B\n" +
                                        "                                 WHERE  ROW_COUNT = 1)\n" +
                                        "       AND year >=YEAR(GETDATE())\n" +
                                        " GROUP  BY ";
                                         if(!CommonUtils.PIE_CHART.equals(chartType)){                 
                       governmentProjectionQuery+=""+freq+",\n" ;
                                         }
                    governmentProjectionQuery+="year\n" +
                                        " ORDER  BY YEAR\n" ;
                  if(!CommonUtils.PIE_CHART.equals(chartType)){                 
                     governmentProjectionQuery+=" , "+freq+"";
                                                 }
    
//     returnsActualsQuery = "SELECT ISNULL(Sum(A.ACTUAL_RETURN_AMOUNT),0) AS ACTUAL_RETURN_AMOUNT,\n" +
//                                    "       QUARTER,\n" +
//                                    "       YEAR\n" +
//                                    " FROM   RETURNS_ACTUALS A\n" +
//                                    "       JOIN PERIOD P\n" +
//                                    "         ON P.PERIOD_SID = A.PERIOD_SID\n" +
//                                    " WHERE  YEAR <YEAR(GETDATE())\n" +
//                                    " GROUP  BY quarter,\n" +
//                                    "          year\n" +
//                                    " ORDER  BY YEAR,\n" +
//                                    "          QUARTER";
//    
//     returnsProjectionQuery = "SELECT ISNULL(Sum(A.PROJECTED_RETURN_AMOUNT),0) AS PROJECTED_RETURN_AMOUNT,\n" +
//                                    "       QUARTER,\n" +                     
//                                    "       YEAR\n" +
//                                    " FROM   RETURNS_PROJ_DETAILS A\n" +
//                                    "       JOIN RETURNS_DETAILS B\n" +
//                                    "         ON A.RETURNS_DETAILS_SID = B.RETURNS_DETAILS_SID\n" +
//                                    "       JOIN PERIOD P\n" +
//                                    "         ON P.PERIOD_SID = A.PERIOD_SID\n" +
//                                    " WHERE  PROJECTION_MASTER_SID IN (SELECT PROJECTION_MASTER_SID\n" +
//                                    "                                 FROM   (SELECT A.PROJECTION_MASTER_SID,\n" +
//                                    "                                                ROW_NUMBER()\n" +
//                                    "                                                  OVER(\n" +
//                                    "                                                    ORDER BY MODIFIED_DATE DESC) AS ROW_COUNT\n" +
//                                    "                                         FROM   (SELECT PROJECTION_MASTER_SID,\n" +
//                                    "                                                        CREATED_DATE,\n" +
//                                    "                                                        ISNULL(MODIFIED_DATE, CREATED_DATE) AS MODIFIED_DATE\n" +
//                                    "                                                 FROM   PROJECTION_MASTER\n" +
//                                    "                                                 WHERE  IS_APPROVED = 'A' AND FORECASTING_TYPE='Returns')A)B\n" +
//                                    "                                 WHERE  ROW_COUNT = 1)\n" +
//                                    "       AND P.YEAR >=YEAR(GETDATE())\n" +
//                                    " GROUP  BY P.YEAR,\n" +
//                                    "          P.QUARTER\n" +
//                                    " ORDER  BY YEAR,\n" +
//                                    "          QUARTER";
//    
//    String accrualActualsQuery = "SELECT ISNULL(Sum(ACCRUAL_AMOUNT),0) AS ACTUAL_ACCRUAL_AMOUNT,\n" +
//                                    "       P.QUARTER           AS QUARTER,\n" +
//                                    "       P.YEAR              AS YEAR\n" +
//                                    " FROM   ACCRUAL_RATE_ACTUALS ARA\n" +
//                                    "       JOIN PERIOD P\n" +
//                                    "         ON P.PERIOD_SID = ARA.PERIOD_SID\n" +
//                                    " WHERE  P.YEAR <YEAR(GETDATE())\n" +
//                                    " GROUP  BY P.YEAR,\n" +
//                                    "          P.QUARTER\n" +
//                                    " ORDER  BY YEAR,\n" +
//                                    "          QUARTER";
//    
//    String accrualProjectionQuery = "SELECT ISNULL(Sum(ACCRUAL_AMOUNT),0) AS PROJECTED_ACCRUAL_AMOUNT,\n" +
//                                    "       P.QUARTER           AS QUARTER,\n" +
//                                    "       P.YEAR              AS YEAR\n" +
//                                    " FROM   ACCRUAL_PROJ_DETAILS APD\n" +
//                                    "       JOIN ACCRUAL_RATE_DETAILS ARA\n" +
//                                    "         ON APD.ACCRUAL_PROJ_DETAILS_SID = ARA.ACCRUAL_PROJ_DETAILS_SID\n" +
//                                    "       JOIN CCP_DETAILS CCP\n" +
//                                    "         ON CCP.CCP_DETAILS_SID = APD.CCP_DETAILS_SID\n" +
//                                    "       JOIN ITEM_MASTER IM\n" +
//                                    "         ON IM.ITEM_MASTER_SID = CCP.ITEM_MASTER_SID\n" +
//                                    "       JOIN PERIOD P\n" +
//                                    "         ON P.PERIOD_SID = ARA.PERIOD_SID\n" +
//                                    " WHERE  PROJECTION_MASTER_SID IN (SELECT PROJECTION_MASTER_SID\n" +
//                                    "                                 FROM   (SELECT A.PROJECTION_MASTER_SID,\n" +
//                                    "                                                ROW_NUMBER()\n" +
//                                    "                                                  OVER(\n" +
//                                    "                                                    ORDER BY MODIFIED_DATE DESC) AS ROW_COUNT\n" +
//                                    "                                         FROM   (SELECT PROJECTION_MASTER_SID,\n" +
//                                    "                                                        CREATED_DATE,\n" +
//                                    "                                                        ISNULL(MODIFIED_DATE, CREATED_DATE) AS MODIFIED_DATE\n" +
//                                    "                                                 FROM   PROJECTION_MASTER\n" +
//                                    "                                                 WHERE  IS_APPROVED = 'A' AND FORECASTING_TYPE='AccrualRateProjection')A)B\n" +
//                                    "                                 WHERE  ROW_COUNT = 1)\n" +
//                                    "       AND P.YEAR >=YEAR(GETDATE())\n" +
//                                    " GROUP  BY P.YEAR,\n" +
//                                    "          P.QUARTER\n" +
//                                    " ORDER  BY P.YEAR,\n" +
//                                    "          P.QUARTER";
    }
    public List getResults(String[] years,String forecastType) {
        getQuery(frequency,tabName,chartType);
        List input = new ArrayList<String>();
//        List inputForProjection = getInputForProjection();
        List inputForProjection = new ArrayList();
        inputForProjection.add(new Date().getYear() + 1900);

        String yearsString = "";
        for (String str : years) {
            yearsString = yearsString + str + ",";
        }
        yearsString = yearsString.substring(0, yearsString.length() - 1);
        input.add(yearsString);
        List list = null;
        if(CommonUtils.COMMERCIAL.equals(forecastType)){
            list = getData(input, commercialActulsQuery);
            list.addAll(getData(inputForProjection, commercialProjectionQuery));
        }else if(CommonUtils.GOVERNMENT.equals(forecastType)){
            list = getData(input, governmentActualsQuery);
            list.addAll(getData(inputForProjection, governmentProjectionQuery));
//        }else if(CommonUtils.RETURNS.equals(forecastType)){
//            list = getData(input, returnsActualsQuery);
//            list.addAll(getData(inputForProjection, returnsProjectionQuery));
//        }else if(CommonUtils.ACCRUAL_RATE_PROJECTION.equals(forecastType)){
//            list = getData(input, accrualActualsQuery);
//            list.addAll(getData(inputForProjection, accrualProjectionQuery));
        }
        
       
        return list;
    }

    private List getData(List input, String queryName) {
        List list = new ArrayList();
        StringBuilder sql = new StringBuilder();
        try {

//            sql = new StringBuilder(CustomSQLUtil.get(queryName));
            sql = new StringBuilder(queryName);
//            for (Object temp : input) {
//                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
//            }
            list = (List<Object[]>) dao.getResultList(sql.toString());
        } catch (Exception ex) {

            ex.printStackTrace();
            LOGGER.error(ex.getMessage());
        }

        return list;
    }

    private List getInputForProjection() {
        List input = new ArrayList<String>();
        Date sDate = new Date();
        sDate.setDate(1);
        sDate.setMonth(1);
        Date eDate = new Date();
        input.add(DBDate.format(sDate));
        input.add(DBDate.format(eDate));
        return input;
    }

}
