/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.forecastdashboard.logic;


import com.stpl.app.forecastdashboard.ui.form.CommonLevelDto;
import com.stpl.app.forecastdashboard.ui.form.DataSourceConnection;
import com.stpl.app.forecastdashboard.ui.form.PeriodDTO;
import com.stpl.app.forecastdashboard.utils.CommonUtils;
import static com.stpl.app.forecastdashboard.utils.CommonUtils.getMonthForInt;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.vaadin.server.VaadinSession;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author maheshj
 */
public class CommonLogic {

    public List getDiscountChartData(int projectionId,String projType,String frequency) {
        
        if("Non Mandated".equals(projType)){            
            
            List<Object> list = (List<Object>) RsModelLocalServiceUtil.executeSelectQuery(buildDiscountChartQuery(projectionId,CommonUtils.getDBPeriodColumnName().get(frequency)),null,null);
            return  convertfinalResultLists(list,frequency);
        }else{
            List<Object> list = (List<Object>) RsModelLocalServiceUtil.executeSelectQuery(buildGovtDiscountChartQuery(projectionId,frequency),null,null);
            return  convertfinalResultLists(list,frequency); 
        }
    }
    
    
    public List getAccrualSalesData(int projectionId) {
        
            
            List<Object> list = (List<Object>) RsModelLocalServiceUtil.executeSelectQuery(buildAccrualSalesChartQuery(projectionId),null,null);
            return  convertfinalAccrualSalesResultLists(list);
        
    }
    
     public List getAccrualRateData(int projectionId) {
        
            
            List<Object> list = (List<Object>) RsModelLocalServiceUtil.executeSelectQuery(buildAccrualRateChartQuery(projectionId),null,null);
            return  convertfinalResultLists(list,"Annual");
        
    }
    
    public String buildAccrualSalesChartQuery(int projectionId) {

        StringBuilder queryBuilder = new StringBuilder(StringUtils.EMPTY);

        queryBuilder.append("IF Object_id('TEMPDB..#ITEM') IS NOT NULL\n" +
                            "  DROP TABLE #ITEM\n" +
                            "\n" +
                            "CREATE TABLE #ITEM\n" +
                            "  (\n" +
                            "     ITEM_MASTER_SID INT\n" +
                            "  )\n" +
                            "\n" +
                            "INSERT INTO #ITEM\n" +
                            "SELECT DISTINCT ITEM_MASTER_SID\n" +
                            "FROM   ACCRUAL_PROJ_DETAILS APD\n" +
                            "       JOIN CCP_DETAILS CCP\n" +
                            "         ON CCP.CCP_DETAILS_SID = APD.CCP_DETAILS_SID\n" +
                            "            AND APD.PROJECTION_MASTER_SID = "+projectionId+"\n" +
                            "\n" +
                            "SELECT SA.ITEM_MASTER_SID,\n" +
                            "       IM.ITEM_NO,\n" +
                            "       P.YEAR,\n" +
                            "       Sum(SA.TOTAL_UNITS)    AS ActaulTotalUnits,\n" +
                            "       Sum(SA.EXCLUDED_UNITS) AS ActaulExcludedUnits,\n" +
                            "       Sum(SA.NET_UNITS)      AS ActaulNetUnits,\n" +
                            "       Sum(SA.PRICE)          AS ActaulPrice,\n" +
                            "       Sum(SA.SALES)          AS ActaulSales,\n" +
                            "       0.00 as ProjectedTotalUnits,\n" +
                            "       0.00 as ProjectedExcludedUnits,\n" +
                            "       0.00 as ProjectedNetUnits,\n" +
                            "       0.00 as ProjectedPrice,\n" +
                            "       0.00 as ProjectedSales,\n" +
                            "       1                      AS isactuals\n" +
                            "FROM   dbo.ACCRUAL_SALES_ACTUALS SA\n" +
                            "       JOIN dbo.ITEM_MASTER IM\n" +
                            "         ON IM.ITEM_MASTER_SID = SA.ITEM_MASTER_SID\n" +
                            "       JOIN PERIOD P\n" +
                            "         ON P.PERIOD_SID = SA.PERIOD_SID\n" +
                            "       JOIN #ITEM I\n" +
                            "         ON I.ITEM_MASTER_SID = SA.ITEM_MASTER_SID\n" +
                            "WHERE  SA.PROJECTION_MASTER_SID = "+projectionId+"\n" +
                            "GROUP  BY SA.ITEM_MASTER_SID,\n" +
                            "          IM.ITEM_NO,\n" +
                            "          P.YEAR\n" +
                            "UNION\n" +
                            "SELECT SD.ITEM_MASTER_SID,\n" +
                            "       IM.ITEM_NO,\n" +
                            "       P.YEAR,\n" +
                            "       0.00 as ActaulTotalUnits,\n" +
                            "       0.00 as ActaulExcludedUnits,\n" +
                            "       0.00 as ActaulNetUnits,\n" +
                            "       0.00 as ActaulPrice,\n" +
                            "       0.00 as ActaulSales,\n" +
                            "       Sum(SD.TOTAL_UNITS)    AS ProjectedTotalUnits,\n" +
                            "       Sum(SD.EXCLUDED_UNITS) AS ProjectedExcludedUnits,\n" +
                            "       Sum(SD.NET_UNITS)      AS ProjectedNetUnits,\n" +
                            "       Sum(SD.PRICE)          AS ProjectedPrice,\n" +
                            "       Sum(SD.SALES)          AS ProjectedSales,\n" +
                            "       0                      AS isactuals\n" +
                            "FROM   dbo.ACCRUAL_SALES_DETAILS SD\n" +
                            "       JOIN dbo.ITEM_MASTER IM\n" +
                            "         ON IM.ITEM_MASTER_SID = SD.ITEM_MASTER_SID\n" +
                            "       JOIN PERIOD P\n" +
                            "         ON P.PERIOD_SID = SD.PERIOD_SID\n" +
                            "       JOIN #ITEM I\n" +
                            "         ON I.ITEM_MASTER_SID = SD.ITEM_MASTER_SID\n" +
                            "WHERE  SD.PROJECTION_MASTER_SID = "+projectionId+"\n" +
                            "GROUP  BY SD.ITEM_MASTER_SID,\n" +
                            "          IM.ITEM_NO,\n" +
                            "          P.YEAR\n" +
                            "ORDER  BY SA.ITEM_MASTER_SID,\n" +
                            "          IM.ITEM_NO,\n" +
                            "          P.YEAR ");
        
        return queryBuilder.toString();
    }
    
    public String buildAccrualRateChartQuery(int projectionId) {

        StringBuilder queryBuilder = new StringBuilder(StringUtils.EMPTY);

        queryBuilder.append("IF Object_id('TEMPDB..#ITEM') IS NOT NULL\n" +
                                "  DROP TABLE #ITEM\n" +
                                "\n" +
                                "CREATE TABLE #ITEM\n" +
                                "  (\n" +
                                "     ITEM_MASTER_SID INT\n" +
                                "  )\n" +
                                "\n" +
                                "INSERT INTO #ITEM\n" +
                                "SELECT DISTINCT ITEM_MASTER_SID\n" +
                                "FROM   ACCRUAL_PROJ_DETAILS APD\n" +
                                "       JOIN CCP_DETAILS CCP\n" +
                                "         ON CCP.CCP_DETAILS_SID = APD.CCP_DETAILS_SID\n" +
                                "            AND APD.PROJECTION_MASTER_SID = "+projectionId+"\n" +
                                "\n" +
                                "SELECT P.\"YEAR\"                AS YEAR,\n" +
                                "       Sum(ACCRUAL_AMOUNT)     AS ACTUAL_ACCRUAL_AMOUNT,\n" +
                                "       Sum(ACCRUAL_RATE) * 100 AS ACTUAL_ACCRUAL_RATE,\n" +
                                "       0.00     			   AS PROJECTED_ACCRUAL_AMOUNT,\n" +
                                "       0.00 				   AS PROJECTED_ACCRUAL_RATE,\n" +
                                "       IM.ITEM_NO              AS NDC11,\n" +
                                "       1                       AS isactuals " +
                                "FROM   ACCRUAL_PROJ_DETAILS APD\n" +
                                "       JOIN ACCRUAL_RATE_ACTUALS ARA\n" +
                                "         ON APD.PROJECTION_MASTER_SID = "+projectionId+"\n" +
                                "            AND APD.ACCRUAL_PROJ_DETAILS_SID = ARA.ACCRUAL_PROJ_DETAILS_SID\n" +
                                "       JOIN CCP_DETAILS CCP\n" +
                                "         ON CCP.CCP_DETAILS_SID = APD.CCP_DETAILS_SID\n" +
                                "       JOIN ITEM_MASTER IM\n" +
                                "         ON IM.ITEM_MASTER_SID = CCP.ITEM_MASTER_SID\n" +
                                "       JOIN #ITEM I\n" +
                                "         ON I.ITEM_MASTER_SID = IM.ITEM_MASTER_SID\n" +
                                "       JOIN \"PERIOD\" P\n" +
                                "         ON P.PERIOD_SID = ARA.PERIOD_SID\n" +
                                "GROUP  BY IM.ITEM_NO,\n" +
                                "          P.\"YEAR\"\n" +
                                "UNION\n" +
                                "SELECT P.\"YEAR\"                AS YEAR,\n" +
                                "	0.00     			   AS ACTUAL_ACCRUAL_AMOUNT,\n" +
                                "       0.00 				   AS ACTUAL_ACCRUAL_RATE,\n" +
                                "       Sum(ACCRUAL_AMOUNT)     AS PROJECTED_ACCRUAL_AMOUNT,\n" +
                                "       Sum(ACCRUAL_RATE) * 100 AS PROJECTED_ACCRUAL_RATE,\n" +
                                "       IM.ITEM_NO              AS NDC11,\n" +
                                "       0                       AS isactuals " +
                                "FROM   ACCRUAL_PROJ_DETAILS APD\n" +
                                "       JOIN ACCRUAL_RATE_DETAILS ARA\n" +
                                "         ON APD.PROJECTION_MASTER_SID = "+projectionId+"\n" +
                                "            AND APD.ACCRUAL_PROJ_DETAILS_SID = ARA.ACCRUAL_PROJ_DETAILS_SID\n" +
                                "       JOIN CCP_DETAILS CCP\n" +
                                "         ON CCP.CCP_DETAILS_SID = APD.CCP_DETAILS_SID\n" +
                                "       JOIN ITEM_MASTER IM\n" +
                                "         ON IM.ITEM_MASTER_SID = CCP.ITEM_MASTER_SID\n" +
                                "       JOIN #ITEM I\n" +
                                "         ON I.ITEM_MASTER_SID = IM.ITEM_MASTER_SID\n" +
                                "       JOIN \"PERIOD\" P\n" +
                                "         ON P.PERIOD_SID = ARA.PERIOD_SID\n" +
                                "GROUP  BY IM.ITEM_NO,\n" +
                                "          P.\"YEAR\"\n" +
                                "ORDER  BY IM.ITEM_NO,\n" +
                                "          P.\"YEAR\" ");
        
        return queryBuilder.toString();
    }
    
    
     public String buildDiscountChartQuery(int projectionId,String frequency) {

        StringBuilder queryBuilder = new StringBuilder(StringUtils.EMPTY);
                    queryBuilder.append("SELECT PR.YEAR,\n"
                            +   "       0.00                             AS ACTUAL_SALES,\n" +
                                "       0.00                                  AS ACTUAL_DISCOUNT,\n" +
                                "       Sum(NMSP.PROJECTION_SALES)            AS PROJECTION_SALES,\n" +
                                "       Sum(ISNULL(NMDP.PROJECTION_SALES, 0)) AS PROJECTION_DISCOUNT,\n" +
                                "       RSM.RS_NAME,\n" +
                                "       0                                     AS ProjectionRsales\n");
                                if(!CommonUtils.ANNUALLY.equals(frequency)){
                                  queryBuilder.append(",PR."+frequency+"");
                                }
                                
                                queryBuilder.append(" FROM   PROJECTION_DETAILS PD\n" +
                                "       JOIN NM_DISCOUNT_PROJ_MASTER NMDPM\n" +
                                "         ON NMDPM.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n" +
                                "       JOIN NM_DISCOUNT_PROJECTION NMDP\n" +
                                "         ON NMDP.PROJECTION_DETAILS_SID = NMDPM.PROJECTION_DETAILS_SID\n" +
                                "            AND NMDPM.RS_MODEL_SID = NMDP.RS_MODEL_SID\n" +
                                "       JOIN PERIOD PR\n" +
                                "         ON PR.PERIOD_SID = NMDP.PERIOD_SID\n" +
                                "       JOIN NM_SALES_PROJECTION NMSP\n" +
                                "         ON NMSP.PERIOD_SID = PR.PERIOD_SID\n" +
                                "            AND NMSP.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n" +
                                "       JOIN NM_SALES_PROJECTION_MASTER NMSPM\n" +
                                "         ON NMSPM.PROJECTION_DETAILS_SID = NMSP.PROJECTION_DETAILS_SID\n" +
                                "            AND PD.PROJECTION_DETAILS_SID = NMSPM.PROJECTION_DETAILS_SID\n" +
                                "       JOIN RS_MODEL RSM\n" +
                                "         ON RSM.RS_MODEL_SID = NMDPM.RS_MODEL_SID\n" +
                                "            AND RSM.RS_MODEL_SID = NMDP.RS_MODEL_SID\n" +
                                "WHERE  PD.PROJECTION_MASTER_SID = "+projectionId+"\n");
                                if(CommonUtils.ANNUALLY.equals(frequency)){
                                  queryBuilder.append(" GROUP  BY PR.YEAR, ");
                                }else{
                                  queryBuilder.append(" GROUP  BY PR.YEAR,PR."+frequency+",");
                                }
                                queryBuilder.append("   RSM.RS_NAME\n" +
                                "UNION ALL\n");
                               queryBuilder.append("SELECT PR.YEAR,\n"
                              + "       Sum(NMAS.ACTUAL_SALES)                AS ACTUAL_SALES,\n" +
                                "       Sum(NMAD.ACTUAL_SALES)                AS ACTUAL_DISCOUNT,\n" +
                                "       Sum(ISNULL(NMSP.PROJECTION_SALES, 0)) AS PROJECTION_SALES,\n" +
                                "       Sum(ISNULL(NMDP.PROJECTION_SALES, 0)) AS PROJECTION_DISCOUNT,\n" +
                                "       RSM.RS_NAME,\n" +
                                "       1                                     AS ProjectionRsales\n" );
                                if(!CommonUtils.ANNUALLY.equals(frequency)){
                                  queryBuilder.append(",PR."+frequency+"");
                                }
                                queryBuilder.append(" FROM   PROJECTION_DETAILS PD\n" +
                                "       JOIN NM_DISCOUNT_PROJ_MASTER NMADM\n" +
                                "         ON NMADM.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n" +
                                "       JOIN NM_ACTUAL_DISCOUNT NMAD\n" +
                                "         ON NMAD.PROJECTION_DETAILS_SID = NMADM.PROJECTION_DETAILS_SID\n" +
                                "            AND NMADM.RS_MODEL_SID = NMAD.RS_MODEL_SID\n" +
                                "       JOIN RS_MODEL RSM\n" +
                                "         ON RSM.RS_MODEL_SID = NMADM.RS_MODEL_SID\n" +
                                "            AND RSM.RS_MODEL_SID = NMAD.RS_MODEL_SID\n" +
                                "       JOIN PERIOD PR\n" +
                                "         ON PR.PERIOD_SID = NMAD.PERIOD_SID\n" +
                                "       JOIN ST_NM_ACTUAL_SALES NMAS\n" +
                                "         ON NMAS.PERIOD_SID = PR.PERIOD_SID\n" +
                                "            AND NMAS.PROJECTION_DETAILS_SID = NMADM.PROJECTION_DETAILS_SID\n" +
                                "       JOIN NM_SALES_PROJECTION_MASTER NMSPM\n" +
                                "         ON NMSPM.PROJECTION_DETAILS_SID = NMAS.PROJECTION_DETAILS_SID\n" +
                                "       LEFT JOIN NM_SALES_PROJECTION NMSP\n" +
                                "              ON NMSP.PROJECTION_DETAILS_SID = NMSPM.PROJECTION_DETAILS_SID\n" +
                                "                 AND NMSP.PERIOD_SID = PR.PERIOD_SID\n" +
                                "       LEFT JOIN NM_DISCOUNT_PROJECTION NMDP\n" +
                                "              ON NMDP.PROJECTION_DETAILS_SID = NMSPM.PROJECTION_DETAILS_SID\n" +
                                "                 AND NMDP.PERIOD_SID = PR.PERIOD_SID\n" +
                                "                 AND NMDP.RS_MODEL_SID = RSM.RS_MODEL_SID\n" +
                                "WHERE  PD.PROJECTION_MASTER_SID = "+projectionId+"\n");
                                if(CommonUtils.ANNUALLY.equals(frequency)){
                                  queryBuilder.append("GROUP  BY PR.YEAR,\n");
                                }else{
                                  queryBuilder.append("GROUP  BY PR.YEAR,PR."+frequency+",");
                                }
                                queryBuilder.append("          RSM.RS_NAME\n" +
                                "ORDER  BY RSM.RS_NAME,\n");
                                if(CommonUtils.ANNUALLY.equals(frequency)){
                                  queryBuilder.append(" PR.YEAR\n");
                                }else{
                                  queryBuilder.append("PR.YEAR,PR."+frequency);
                                }
        
        return queryBuilder.toString();
    }

    public String buildGovtDiscountChartQuery(int projectionId,String frequency) {

          StringBuilder queryBuilder = new StringBuilder(StringUtils.EMPTY);

        queryBuilder.append("DECLARE @FREQUENCY CHAR(1) = '"+frequency+"'\n" +
" \n" +
"IF Object_id('TEMPDB.DBO.#PERIOD', 'U') IS NOT NULL\n" +
"       DROP TABLE #PERIOD;\n" +
" \n" +
"SELECT PERIOD_SID\n" +
"       , PERIOD_DATE\n" +
"       , MONTH\n" +
"       , QUARTER\n" +
"       , SEMI_ANNUAL\n" +
"       , YEAR\n" +
"       , PERIOD = CASE\n" +
"              WHEN LEFT(@FREQUENCY, 1) = 'M'\n" +
"                     THEN MONTH\n" +
"              WHEN LEFT(@FREQUENCY, 1) = 'Q'\n" +
"                     THEN QUARTER\n" +
"              WHEN LEFT(@FREQUENCY, 1) = 'S'\n" +
"                     THEN SEMI_ANNUAL\n" +
"              ELSE YEAR\n" +
"              END\n" +
"INTO #PERIOD\n" +
"FROM PERIOD\n" +
" \n" +
"IF Object_id('TEMPDB..#MANDATED') IS NOT NULL\n" +
"       DROP TABLE #MANDATED\n" +
" \n" +
"CREATE TABLE #MANDATED (\n" +
"       year INT,\n" +
"       \n" +
"       ACTUAL_SALES NUMERIC(22, 6)\n" +
"       , ACTUAL_MANDATED_DISCOUNT NUMERIC(22, 6)\n" +
"       , ACTUAL_SUPPLEMENTAL_DISCOUNT NUMERIC(22, 6)\n" +
"       , PROJECTION_SALES NUMERIC(22, 6)\n" +
"       , PROJECTION_MANDATED_DISCOUNT NUMERIC(22, 6)\n" +
"       , PROJECTION_SUPPLEMENTAL_DISCOUNT NUMERIC(22, 6)\n" +
"       , ProjectionRsales INT\n" +
"       ,period int\n" +
"       )\n" +
" \n" +
"INSERT INTO #MANDATED\n" +
"SELECT \n" +
"pr.YEAR as years\n" +
"       , 0.00 AS ACTUAL_SALES\n" +
"       , 0.00 AS ACTUAL_MANDATED_DISCOUNT\n" +
"       , 0.00 AS ACTUAL_SUPPLEMENTAL_DISCOUNT\n" +
"       , Sum(MSP.PROJECTION_SALES) AS PROJECTION_SALES\n" +
"       , Sum(ISNULL(MDP.PROJECTION_SALES, 0)) AS PROJECTION_MANDATED_DISCOUNT\n" +
"       , Sum(ISNULL(MSUP.PROJECTION_SALES, 0)) AS PROJECTION_SUPPLEMENTAL_DISCOUNT\n" +
"       , 0 AS ProjectionRsales\n" +
"       ,PR.PERIOD as periods\n" +
"FROM PROJECTION_DETAILS PD\n" +
"INNER JOIN M_DISCOUNT_PROJECTION MDP\n" +
"       ON MDP.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n" +
//"              AND MDP.SAVE_FLAG = 1\n" +
"INNER JOIN #PERIOD PR\n" +
"       ON PR.PERIOD_SID = MDP.PERIOD_SID\n" +
"INNER JOIN M_SALES_PROJECTION MSP\n" +
"       ON MSP.PERIOD_SID = PR.PERIOD_SID\n" +
"              AND MSP.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n" +
"INNER JOIN M_SUPPLEMENTAL_DISC_PROJ MSUP\n" +
"       ON MSUP.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n" +
"              AND MSUP.PERIOD_SID = PR.PERIOD_SID\n" +
"WHERE PD.PROJECTION_MASTER_SID = "+projectionId+"\n" +
"GROUP BY\n" +
"pr.YEAR,\n" +
"PR.PERIOD\n" +
" \n" +
"UNION ALL\n" +
" \n" +
"SELECT\n" +
"pr.YEAR as years\n" +
"       , Sum(MAS.ACTUAL_SALES) AS ACTUAL_SALES\n" +
"       , Sum(MAD.ACTUAL_SALES) AS ACTUAL_DISCOUNT\n" +
"       , Sum(MSUP.ACTUAL_SALES) AS ACTUAL_SUPPLEMENTAL_DISCOUNT\n" +
"       , 0.00 AS PROJECTION_SALES\n" +
"       , 0.00 AS PROJECTION_DISCOUNT\n" +
"       , 0.00 AS PROJECTION_SUPPLEMENTAL_DISCOUNT\n" +
"       , 1 AS ProjectionRsales\n" +
"       , PR.PERIOD as periods\n" +
"FROM PROJECTION_DETAILS PD\n" +
"INNER JOIN M_ACTUAL_DISCOUNT MAD\n" +
"       ON MAD.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n" +
//"              AND MAD.SAVE_FLAG = 1\n" +
"INNER JOIN #PERIOD PR\n" +
"       ON PR.PERIOD_SID = MAD.PERIOD_SID\n" +
"INNER JOIN M_ACTUAL_SALES MAS\n" +
"       ON MAS.PERIOD_SID = PR.PERIOD_SID\n" +
"              AND MAS.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n" +
"INNER JOIN M_SUPPLEMENTAL_DISC_ACTUALS MSUP\n" +
"       ON MSUP.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n" +
"              AND MSUP.PERIOD_SID = PR.PERIOD_SID\n" +
"WHERE PD.PROJECTION_MASTER_SID =  "+projectionId+"\n" +
"GROUP BY pr.YEAR,PR.PERIOD\n" +
"ORDER BY pr.YEAR,PR.PERIOD\n" +
" \n" +
" \n" +
"SELECT t.*\n" +
"FROM #MANDATED\n" +
"CROSS APPLY (\n" +
"       VALUES (\n" +
"              \n" +
"              year\n" +
"              , ACTUAL_SALES\n" +
"              , ACTUAL_MANDATED_DISCOUNT\n" +
"              , PROJECTION_SALES\n" +
"              , PROJECTION_MANDATED_DISCOUNT\n" +
"              , 'MANDATED'\n" +
"              , ProjectionRsales\n" +
"              ,PERIOD\n" +
"              )\n" +
"              , (\n" +
"             \n" +
"              year\n" +
"              , ACTUAL_SALES\n" +
"              , ACTUAL_SUPPLEMENTAL_DISCOUNT\n" +
"              , PROJECTION_SALES\n" +
"              , PROJECTION_SUPPLEMENTAL_DISCOUNT\n" +
"              , 'SUPPLEMENTAL'\n" +
"              , ProjectionRsales\n" +
"              , PERIOD\n" +
"              )\n" +
"       ) t(year, ACTUAL_SALES, ACTUAL_DISCOUNT, PROJECTION_SALES, PROJECTION_DISCOUNT, ProjectionRsales, DISCOUNT_TYPE,PERIOD)");
        return queryBuilder.toString();
    }
    
    private List convertfinalResultLists(List list,String frequency) {
        List finalList=new ArrayList();
        List<CommonLevelDto> salesRowList = new ArrayList<CommonLevelDto>();
        List<String> xAxis = new ArrayList<String>();
         Set<String> keySet=new HashSet<String>();
         String year = StringUtils.EMPTY;
        String tempCcpid = "empty";
        PeriodDTO dto = new PeriodDTO();
       
        CommonLevelDto commonLevelDto = new CommonLevelDto();
        for (int i = 0; i < list.size(); i++) {
            dto = new PeriodDTO();
            Object obj[] = (Object[]) list.get(i);
            if (obj[0] != null) {
                dto.setYear(String.valueOf(obj[0]));
                if(CommonUtils.SEMI_ANNUAL.equals(frequency)) {
                    dto.setYear("S"+String.valueOf(obj[7])+" "+String.valueOf(obj[0]));
                } else if(CommonUtils.QUARTERLY.equals(frequency)) {
                    dto.setYear("Q"+String.valueOf(obj[7])+" "+String.valueOf(obj[0]));
                } else if(CommonUtils.MONTHLY.equals(frequency)) {
                    String monthName = getMonthForInt(Integer.parseInt(String.valueOf(obj[7]))-1);
                    dto.setYear(monthName+String.valueOf(obj[7])+" "+String.valueOf(obj[0]));
                }
                if(!year.equals(dto.getYear())) {
                    xAxis.add(dto.getYear());
                }
                keySet.add(dto.getYear());
                year = dto.getYear();
            }
            if (obj[1] != null) {
                dto.setActualSales(String.valueOf(obj[1]));
            }
            if (obj[2] != null) {
                dto.setActualDiscounts(String.valueOf(obj[2]));
            }
            if (obj[3] != null) {
                dto.setProjSales(String.valueOf(obj[3]));
            }
            if (obj[4] != null) {
                dto.setProjDiscount(String.valueOf(obj[4]));
            }
            if (obj[5] != null) {
                dto.setLevelName(String.valueOf(obj[5]));
            }
            if (obj[6] != null) {
                dto.setActualsOrProj(Integer.parseInt(String.valueOf(obj[6])));
            }
            if (tempCcpid.equalsIgnoreCase("empty")) {
                tempCcpid = dto.getLevelName();
                commonLevelDto = new CommonLevelDto();
            }

            if (tempCcpid.equalsIgnoreCase(dto.getLevelName())) {
                commonLevelDto.setGroup(dto.getLevelName());
                String key = dto.getYear();
                if (dto.getActualsOrProj() == 0) {
                    commonLevelDto.addStringProperties("" + key + "-Projected Amount", String.valueOf(dto.getProjSales()));
                    commonLevelDto.addStringProperties("" + key + "-Projected Rate", String.valueOf(dto.getProjDiscount()));
                } else {
                    commonLevelDto.addStringProperties("" + key + "-Actual Amount", String.valueOf(dto.getActualSales()));
                    commonLevelDto.addStringProperties("" + key + "-Actual Rate", String.valueOf(dto.getActualDiscounts()));
                }
                commonLevelDto.setxAxis(xAxis);
                
            } else {
                salesRowList.add(commonLevelDto);
                tempCcpid = dto.getLevelName();
                commonLevelDto = new CommonLevelDto();
                commonLevelDto.setGroup(dto.getLevelName());
                String key = dto.getYear();
                 if (dto.getActualsOrProj() == 0) {
                    commonLevelDto.addStringProperties("" + key + "-Projected Amount", String.valueOf(dto.getProjSales()));
                    commonLevelDto.addStringProperties("" + key + "-Projected Rate", String.valueOf(dto.getProjDiscount()));
                } else {
                    commonLevelDto.addStringProperties("" + key + "-Actual Amount", String.valueOf(dto.getActualSales()));
                    commonLevelDto.addStringProperties("" + key + "-Actual Rate", String.valueOf(dto.getActualDiscounts()));
                }
                 commonLevelDto.setxAxis(xAxis);
            }
  
            if (i == (list.size() - 1)) {
                commonLevelDto.setGroup(dto.getLevelName());
                String key = dto.getYear();
                if (dto.getActualsOrProj() == 0) {
                    commonLevelDto.addStringProperties("" + key + "-Projected Amount", String.valueOf(dto.getProjSales()));
                    commonLevelDto.addStringProperties("" + key + "-Projected Rate", String.valueOf(dto.getProjDiscount()));
                } else {
                    commonLevelDto.addStringProperties("" + key + "-Actual Amount", String.valueOf(dto.getActualSales()));
                    commonLevelDto.addStringProperties("" + key + "-Actual Rate", String.valueOf(dto.getActualDiscounts()));
                }
                commonLevelDto.setxAxis(xAxis);
                salesRowList.add(commonLevelDto);
            }
        }
        
        finalList.add(salesRowList);
        finalList.add(keySet);
        return finalList;
    }
  
    
    
    private List convertfinalAccrualSalesResultLists(List list) {
        List finalList=new ArrayList();
        List<CommonLevelDto> salesRowList = new ArrayList<CommonLevelDto>();
         Set<String> keySet=new HashSet<String>();
        String tempCcpid = "empty";
        PeriodDTO dto = new PeriodDTO();
       
        CommonLevelDto commonLevelDto = new CommonLevelDto();
        for (int i = 0; i < list.size(); i++) {
            dto = new PeriodDTO();
            Object obj[] = (Object[]) list.get(i);
            if (obj[2] != null) {
                dto.setYear(String.valueOf(obj[2]));
                keySet.add(String.valueOf(obj[2]));
            }
            if (obj[3] != null) {
                dto.setActualTotalUnits(String.valueOf(obj[3]));
            }
            if (obj[4] != null) {
                dto.setActualExcludedUnits(String.valueOf(obj[4]));
            }
            if (obj[5] != null) {
                dto.setActualNetUnits(String.valueOf(obj[5]));
            }
            if (obj[6] != null) {
                dto.setActualPrice(String.valueOf(obj[6]));
            }
            if (obj[7] != null) {
                dto.setActualSales(String.valueOf(obj[7]));
            }
            if (obj[8] != null) {
                dto.setProjectedTotalUnits(String.valueOf(obj[8]));
            }
            if (obj[9] != null) {
                dto.setProjectedExcludedUnits(String.valueOf(obj[9]));
            }
            if (obj[10] != null) {
                dto.setProjectedNetUnits(String.valueOf(obj[10]));
            }
            if (obj[11] != null) {
                dto.setProjectedPrice(String.valueOf(obj[11]));
            }
            if (obj[12] != null) {
                dto.setProjSales(String.valueOf(obj[12]));
            }
            if (obj[1] != null) {
                dto.setLevelName(String.valueOf(obj[1]));
            }
            if (obj[6] != null) {
                dto.setActualsOrProj(Integer.parseInt(String.valueOf(obj[13])));
            }
            if (tempCcpid.equalsIgnoreCase("empty")) {
                tempCcpid = dto.getLevelName();
                commonLevelDto = new CommonLevelDto();
            }

            if (tempCcpid.equalsIgnoreCase(dto.getLevelName())) {
                commonLevelDto.setGroup(dto.getLevelName());
                String key = dto.getYear();
                if (dto.getActualsOrProj() == 0) {
                    commonLevelDto.addStringProperties("" + key + "-Projected Total Units", String.valueOf(dto.getProjectedTotalUnits()));
                    commonLevelDto.addStringProperties("" + key + "-Projected Excluded Units", String.valueOf(dto.getProjectedExcludedUnits()));
                    commonLevelDto.addStringProperties("" + key + "-Projected NetUnits", String.valueOf(dto.getProjectedNetUnits()));
                    commonLevelDto.addStringProperties("" + key + "-Projected Price", String.valueOf(dto.getProjectedPrice()));
                    commonLevelDto.addStringProperties("" + key + "-Projected Sales", String.valueOf(dto.getProjSales()));
                } else {
                    commonLevelDto.addStringProperties("" + key + "-Actual Total Units", String.valueOf(dto.getActualTotalUnits()));
                    commonLevelDto.addStringProperties("" + key + "-Actual Excluded Units", String.valueOf(dto.getActualExcludedUnits()));
                    commonLevelDto.addStringProperties("" + key + "-Actual NetUnits", String.valueOf(dto.getActualNetUnits()));
                    commonLevelDto.addStringProperties("" + key + "-Actual Price", String.valueOf(dto.getActualPrice()));
                    commonLevelDto.addStringProperties("" + key + "-Actual Sales", String.valueOf(dto.getActualSales()));
                }
                
            } else {
                salesRowList.add(commonLevelDto);
                tempCcpid = dto.getLevelName();
                commonLevelDto = new CommonLevelDto();
                commonLevelDto.setGroup(dto.getLevelName());
                String key = dto.getYear();
                 if (dto.getActualsOrProj() == 0) {
                    commonLevelDto.addStringProperties("" + key + "-Projected Total Units", String.valueOf(dto.getProjectedTotalUnits()));
                    commonLevelDto.addStringProperties("" + key + "-Projected Excluded Units", String.valueOf(dto.getProjectedExcludedUnits()));
                    commonLevelDto.addStringProperties("" + key + "-Projected NetUnits", String.valueOf(dto.getProjectedNetUnits()));
                    commonLevelDto.addStringProperties("" + key + "-Projected Price", String.valueOf(dto.getProjectedPrice()));
                    commonLevelDto.addStringProperties("" + key + "-Projected Sales", String.valueOf(dto.getProjSales()));
                } else {
                    commonLevelDto.addStringProperties("" + key + "-Actual Total Units", String.valueOf(dto.getActualTotalUnits()));
                    commonLevelDto.addStringProperties("" + key + "-Actual Excluded Units", String.valueOf(dto.getActualExcludedUnits()));
                    commonLevelDto.addStringProperties("" + key + "-Actual NetUnits", String.valueOf(dto.getActualNetUnits()));
                    commonLevelDto.addStringProperties("" + key + "-Actual Price", String.valueOf(dto.getActualPrice()));
                    commonLevelDto.addStringProperties("" + key + "-Actual Sales", String.valueOf(dto.getActualSales()));
                }
            }
  
            if (i == (list.size() - 1)) {
                commonLevelDto.setGroup(dto.getLevelName());
                String key = dto.getYear();
                if (dto.getActualsOrProj() == 0) {
                    commonLevelDto.addStringProperties("" + key + "-Projected Total Units", String.valueOf(dto.getProjectedTotalUnits()));
                    commonLevelDto.addStringProperties("" + key + "-Projected Excluded Units", String.valueOf(dto.getProjectedExcludedUnits()));
                    commonLevelDto.addStringProperties("" + key + "-Projected NetUnits", String.valueOf(dto.getProjectedNetUnits()));
                    commonLevelDto.addStringProperties("" + key + "-Projected Price", String.valueOf(dto.getProjectedPrice()));
                    commonLevelDto.addStringProperties("" + key + "-Projected Sales", String.valueOf(dto.getProjSales()));
                } else {
                    commonLevelDto.addStringProperties("" + key + "-Actual Total Units", String.valueOf(dto.getActualTotalUnits()));
                    commonLevelDto.addStringProperties("" + key + "-Actual Excluded Units", String.valueOf(dto.getActualExcludedUnits()));
                    commonLevelDto.addStringProperties("" + key + "-Actual NetUnits", String.valueOf(dto.getActualNetUnits()));
                    commonLevelDto.addStringProperties("" + key + "-Actual Price", String.valueOf(dto.getActualPrice()));
                    commonLevelDto.addStringProperties("" + key + "-Actual Sales", String.valueOf(dto.getActualSales()));
                }
                salesRowList.add(commonLevelDto);
            }
        }
        finalList.add(salesRowList);
        finalList.add(keySet);
        return finalList;
    }

     public String buildPPAChartQuery(int projectionId ) {

        StringBuilder queryBuilder = new StringBuilder(StringUtils.EMPTY);
        
        
      
        queryBuilder.append("  SELECT  p.YEAR, ISNULL(Sum(PA.ACTUAL_DISCOUNT_DOLLAR),0)    AS ACTUAL_DISCOUNT_DOLLAR,   \n");
        queryBuilder.append("   ISNULL(Sum(ACTUAL_DISCOUNT_DOLLAR) / NULLIF(Sum(sa.actual_sales), 0),0)     AS RATE,  ISNULL(Sum(PA.ACTUAL_DISCOUNT_UNITS),0)   \n");
         queryBuilder.append("  AS ACTUAL_DISCOUNT_UNITS, ISNULL(Sum(ACTUAL_DISCOUNT_DOLLAR) / NULLIF(Sum(ACTUAL_DISCOUNT_UNITS), 0),0) AS Discount   \n");
        queryBuilder.append("  FROM   projection_details pd    \n");
        queryBuilder.append("  JOIN NM_PPA_PROJECTION_MASTER A ON pd.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID    \n");
        queryBuilder.append("  JOIN NM_ACTUAL_PPA PA ON pd.PROJECTION_DETAILS_SID = PA.PROJECTION_DETAILS_SID    \n");
        queryBuilder.append("  JOIN PERIOD p ON p.PERIOD_SID = PA.PERIOD_SID    \n");
        queryBuilder.append("  JOIN NM_ACTUAL_SALES SA ON SA.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID    \n");
        queryBuilder.append("  AND SA.PERIOD_SID = p.PERIOD_SID WHERE  pd.PROJECTION_MASTER_SID = '"+projectionId+"'  GROUP  BY p.\"YEAR\"   \n");
        queryBuilder.append("  UNION    \n");
        queryBuilder.append("  SELECT p.YEAR,  ISNULL(Sum(PA.PROJECTION_DISCOUNT_DOLLAR),0)     AS PROJECTION_DISCOUNT_DOLLAR1,    \n");
        queryBuilder.append("   ISNULL(Sum(PROJECTION_DISCOUNT_DOLLAR) / NULLIF(Sum(SA.projection_sales), 0),0)       AS RATE,    \n");
        queryBuilder.append("   ISNULL(Sum(PA.PROJECTION_DISCOUNT_UNITS),0)         AS PROJECTION_DISCOUNT_UNITS1,    \n");
        queryBuilder.append("   ISNULL(Sum(PROJECTION_DISCOUNT_DOLLAR) / NULLIF(Sum(PROJECTION_DISCOUNT_UNITS), 0),0) AS Discount FROM   projection_details pd    \n");
        queryBuilder.append("  JOIN NM_PPA_PROJECTION_MASTER A ON pd.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID    \n");
        queryBuilder.append("  JOIN NM_PPA_PROJECTION PA ON pd.PROJECTION_DETAILS_SID = PA.PROJECTION_DETAILS_SID    \n");
        queryBuilder.append("  JOIN PERIOD p ON p.period_sid = PA.PERIOD_SID    \n");
        queryBuilder.append("  JOIN NM_SALES_PROJECTION SA ON SA.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID    \n");
        queryBuilder.append("  AND Sa.PERIOD_SID = p.PERIOD_SID  WHERE  pd.PROJECTION_MASTER_SID =  '"+projectionId+"'  GROUP  BY p.\"YEAR\" ORDER  BY year   \n");

        return queryBuilder.toString();
    }
     
     public String buildReturnsSalesChartQuery(int projectionId ) {

        StringBuilder queryBuilder = new StringBuilder(StringUtils.EMPTY);
        
        
      
        queryBuilder.append("  SELECT   \n" +
                                "       P.YEAR,\n" +
                                "       COALESCE(Sum(A.CUM_RETURN_UNITS) / NULLIF(Sum(A.ORIG_SALE_UNITS), 0), 0) * 100 AS ACTUAL_RETURN_PERCENT,\n" +
                                "       COALESCE(( COALESCE(Sum(A.CUM_RETURN_UNITS) / NULLIF(Sum(A.ORIG_SALE_UNITS), 0), 0) * Sum(A.ORIG_SALE_DOLLARS) ) / NULLIF(Sum(A.ORIG_SALE_UNITS), 0), 0) AS ACTUAL_RPU,\n" +
                                "       isnull(Sum(A.ACTUAL_RETURN_AMOUNT),0)  AS ACTUAL_RETURN_AMOUNT,\n" +
                                "       COALESCE(Sum(A.PROJECTED_RETURN_UNITS) / NULLIF(Sum(A.ACTIVE_EXFACTORY_SALES_UNITS), 0), 0) * 100 AS PROJECTED_RETURN_PERCENT,\n" +
                                "       COALESCE(( ( COALESCE(Sum(A.PROJECTED_RETURN_UNITS) / NULLIF(Sum(A.ACTIVE_EXFACTORY_SALES_UNITS), 0), 0) ) * Sum(A.ACTIVE_EXFACTORY_SALES_AMOUNT) ) / NULLIF(Sum(A.ACTIVE_EXFACTORY_SALES_UNITS), 0), 0) AS PROJECTED_RPU,\n" +
                                "       isnull(Sum(A.PROJECTED_RETURN_AMOUNT) ,0) AS PROJECTED_RETURN_AMOUNT,\n" +
                                "       isnull(Avg(A.GROWTH_RATE),0) AS GROWTH_RATE\n" +
                                "FROM   RETURNS_PROJ_MASTER R_MASTER\n" +
                                "       JOIN (SELECT COALESCE(R_ACTUALS.RETURNS_DETAILS_SID, R_PROJ.RETURNS_DETAILS_SID)AS RETURNS_DETAILS_SID,\n" +
                                "                    COALESCE(R_ACTUALS.PERIOD_SID, R_PROJ.PERIOD_SID)                  AS PERIOD_SID,\n" +
                                "                    R_ACTUALS.ACTUAL_RETURN_PERCENT,\n" +
                                "                    R_ACTUALS.ACTUAL_RPU,\n" +
                                "                    R_ACTUALS.ACTUAL_RETURN_AMOUNT,\n" +
                                "                    R_ACTUALS.CUM_RETURN_UNITS,\n" +
                                "                    R_ACTUALS.ASP,\n" +
                                "                    R_ACTUALS.ORIG_SALE_DOLLARS,\n" +
                                "                    R_ACTUALS.ORIG_SALE_UNITS,\n" +
                                "                    R_PROJ.PROJECTED_RETURN_PERCENT,\n" +
                                "                    R_PROJ.PROJECTED_RPU,\n" +
                                "                    R_PROJ.PROJECTED_RETURN_AMOUNT,\n" +
                                "                    R_PROJ.PROJECTED_RETURN_UNITS,\n" +
                                "                    R_PROJ.ACTIVE_EXFACTORY_SALES_AMOUNT,\n" +
                                "                    R_PROJ.ACTIVE_EXFACTORY_SALES_UNITS,\n" +
                                "                    R_PROJ.GROWTH_RATE\n" +
                                "					\n" +
                                "\n" +
                                "             FROM   (SELECT RETURNS_DETAILS_SID,\n" +
                                "                            PERIOD_SID,\n" +
                                "                            ACTUAL_RETURN_PERCENT,\n" +
                                "                            ACTUAL_RPU,\n" +
                                "                            ACTUAL_RETURN_AMOUNT,\n" +
                                "                            CUM_RETURN_UNITS,\n" +
                                "                            ASP,\n" +
                                "                            ORIG_SALE_DOLLARS,\n" +
                                "                            ORIG_SALE_UNITS\n" +
                                "                     FROM   RETURNS_ACTUALS NAP\n" +
                                "                     WHERE  NAP.RETURNS_DETAILS_SID IN ( SELECT RETURNS_DETAILS_SID FROM RETURNS_DETAILS WHERE PROJECTION_MASTER_SID="+projectionId+")\n" +
                                "                            ) R_ACTUALS\n" +
                                "                    FULL OUTER JOIN (SELECT RETURNS_DETAILS_SID,\n" +
                                "                                            PERIOD_SID,\n" +
                                "                                            PROJECTED_RETURN_PERCENT,\n" +
                                "                                            PROJECTED_RPU,\n" +
                                "                                            PROJECTED_RETURN_AMOUNT,\n" +
                                "                                            PROJECTED_RETURN_UNITS,\n" +
                                "                                            ACTIVE_EXFACTORY_SALES_AMOUNT,\n" +
                                "                                            ACTIVE_EXFACTORY_SALES_UNITS,\n" +
                                "                                            GROWTH_RATE\n" +
                                "                                     FROM   RETURNS_PROJ_DETAILS NPP\n" +
                                "                                     WHERE  RETURNS_DETAILS_SID IN ( SELECT RETURNS_DETAILS_SID FROM RETURNS_DETAILS WHERE PROJECTION_MASTER_SID="+projectionId+")\n" +
                                "                                            ) R_PROJ\n" +
                                "                                 ON R_ACTUALS.RETURNS_DETAILS_SID = R_PROJ.RETURNS_DETAILS_SID\n" +
                                "                                    AND R_ACTUALS.PERIOD_SID = R_PROJ.PERIOD_SID)A\n" +
                                "         ON A.RETURNS_DETAILS_SID = R_MASTER.RETURNS_DETAILS_SID\n" +
                                "       JOIN PERIOD P\n" +
                                "         ON P.PERIOD_SID = A.PERIOD_SID\n" +
                                "       JOIN (SELECT DISTINCT A.PROJECTION_MASTER_SID,\n" +
                                "                             A.HIERARCHY_NO,\n" +
                                "                             A.LEVEL_NO,\n" +
                                "                             B.TOKEN\n" +
                                "             FROM   RETURNS_MAP A\n" +
                                "                    CROSS APPLY (SELECT Token\n" +
                                "                                 FROM   UDF_SPLITSTRING(A.RETURNS_DETAILS_SID, ','))B\n" +
                                "             WHERE  LEVEL_NO = 1\n" +
                                "                    AND PROJECTION_MASTER_SID = "+projectionId+")RT_M\n" +
                                "         ON RT_M.token = A.RETURNS_DETAILS_SID\n" +
                                "		 \n" +
                                "GROUP  BY \n" +
                                "          P.YEAR\n" +
                                "		  \n" +
                                "ORDER  BY  P.YEAR");

        return queryBuilder.toString();
    }
    
     
      public  List<Object[]> callGTSProcedure(int projectionId, String frequency) {
          ResultSet rs = null;
          List<Object[]> objectList = new ArrayList<Object[]>();

        final DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();
        Connection connection = null;
        CallableStatement statement = null;
        try {
            connection = dataSourceConnection.getConnection();



            if (connection != null) {

                statement = connection.prepareCall("{call PRC_PROJECTION_RESULTS (?,?,?,?,?,?)}");
             
             
                statement.setObject(1, projectionId);
                statement.setObject(2, frequency); 
                statement.setObject(3, " ");
                statement.setObject(4, "VARIENCE");
                statement.setObject(5, 1);
                statement.setObject(6, 1);
                
                rs = statement.executeQuery();

                objectList = convertResultSetToList(rs);
            }

           
        } catch (Exception ex) {
          ex.printStackTrace();

        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                 e.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }

        return objectList;

    }
    
      public  List<Object[]> callGovtSPRProcedure(int projectionId, String frequency) {
          ResultSet rs = null;
          List<Object[]> objectList = new ArrayList<Object[]>();

        final DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();
        Connection connection = null;
        CallableStatement statement = null;
        try {
            connection = dataSourceConnection.getConnection();



            if (connection != null) {

                statement = connection.prepareCall("{call PRC_M_PROJECTION_RESULTS (?,?,?,?,?,?,?)}");

                statement.setInt(1, projectionId);
                statement.setInt(2, 1); 
                statement.setInt(3, 1);
                statement.setString(4, frequency);
                statement.setString(5, " ");
                statement.setInt(6, 1);
                statement.setInt(7, 2015);
              
                rs = statement.executeQuery();

                objectList = convertResultSetToList(rs);
            }

           
        } catch (Exception ex) {
          ex.printStackTrace();

        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                 e.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }

        return objectList;

    }
      
      public  List<Object[]> callCFFProcedure(int projectionId, String frequency) {
          ResultSet rs = null;
          List<Object[]> objectList = new ArrayList<Object[]>();

        final DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();
        Connection connection = null;
        CallableStatement statement = null;
        try {
            connection = dataSourceConnection.getConnection();



            if (connection != null) {

                statement = connection.prepareCall("{call PRC_CFF_RESULTS (?,?,?,?,?)}");

                
                statement.setObject(1, projectionId);
                statement.setObject(2, frequency); 
                statement.setObject(3, "ASSUMPTIONS");
                statement.setObject(4, "PIVOT");
                statement.setObject(5, " ");
                
                rs = statement.executeQuery();

                objectList = convertResultSetToList(rs);
            }

           
        } catch (Exception ex) {
          ex.printStackTrace();

        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                 e.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }

        return objectList;

    }
      
       public  List<Object[]> callCFFPRProcedure(int projectionId, String frequency) {
         ResultSet rs = null;
          List<Object[]> objectList = new ArrayList<Object[]>();
           

        final DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();
        Connection connection = null;
        CallableStatement statement = null;
        try {
            connection = dataSourceConnection.getConnection();



            if (connection != null) {

                statement = connection.prepareCall("{call Prc_cff_results (?,?,?,?,?,?)}");

             
                statement.setObject(1, projectionId);
                statement.setObject(2, frequency); 
                statement.setObject(3, " ");
                statement.setObject(4, "VARIENCE");
                statement.setObject(5, 1);
                statement.setObject(6, 1);
                
                rs = statement.executeQuery();

                objectList = convertResultSetToList(rs);
            }

           
        } catch (Exception ex) {
          ex.printStackTrace();

        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                 e.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }

        return objectList;

    }
      
      
//    public List getSalesResultChartData(int projectionId) {
//      //  List<CommonLevelDto> commonLevelDto = new ArrayList<CommonLevelDto>();
//        List<Object[]> list = callGTSProcedure(projectionId);
//        //commonLevelDto =
//        return  customizeResults(list);
//    }
    
    public List<CommonLevelDto> getProjectionResultChartData(int projectionId,String projType, String frequency) {
        try{
        List<Object[]> list = null;
        if("Non Mandated".equals(projType)){
            list=callGTSProcedure(projectionId, frequency);
            return  customizeResults(list, frequency);
        }else if("Mandated".equals(projType)){
            list=callGovtSPRProcedure(projectionId, CommonUtils.SEMI_ANNUAL.equals(frequency) ? CommonUtils.SEMI_ANNUALLY : frequency);
            return customizeResultsForGovt(list, frequency);
        } else {
            list=callCFFProcedure(projectionId, CommonUtils.SEMI_ANNUAL.equals(frequency) ? CommonUtils.SEMI_ANNUALLY : frequency);
            return  customizeCFFResults(list, frequency);
        }
        }catch(Exception e){
        e.printStackTrace();
        }
        return null;
       
        
    }
    public List getPPAResultChartData(int projectionId) {
      //  List<CommonLevelDto> commonLevelDto = new ArrayList<CommonLevelDto>();
        List<Object[]> list = (List<Object[]>) RsModelLocalServiceUtil.executeSelectQuery(buildPPAChartQuery(projectionId),null,null);
        //commonLevelDto =
        
        return  convertPPAResultList(list);
     }
    
    
    public List getReturnsSalesChartData(int projectionId) {
      //  List<CommonLevelDto> commonLevelDto = new ArrayList<CommonLevelDto>();
        List<Object[]> list = (List<Object[]>) RsModelLocalServiceUtil.executeSelectQuery(buildReturnsSalesChartQuery(projectionId),null,null);
        //commonLevelDto =
        
        return  customizeReturnsSales(list);
     }
    
      /**
     * To convert the given Result Set into List of Objects
     *
     * @param rs
     * @return
     */
    private static List<Object[]> convertResultSetToList(ResultSet rs) {
        List<Object[]> objList = new ArrayList<Object[]>();

        try {
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int columnCount = rsMetaData.getColumnCount();
            Object[] header = new Object[columnCount];
            for (int i = 1; i <= columnCount; ++i) {
                Object label = rsMetaData.getColumnLabel(i);
                header[i - 1] = label;
            }
            while (rs.next()) {
                Object[] str = new Object[columnCount];
                for (int i = 1; i <= columnCount; ++i) {
                    Object obj = rs.getObject(i);
                    str[i - 1] = obj;
                }
                objList.add(str);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
        }
        return objList;
    }
    
    
    public List<CommonLevelDto> customizeResults(List<Object[]> list, String frequency){
        
        List<CommonLevelDto> commonDtoList=new ArrayList<CommonLevelDto>();
        List<String> xAxis = new ArrayList<String>();
        CommonLevelDto commonLevelDto1 = new CommonLevelDto();
        CommonLevelDto commonLevelDto2 = new CommonLevelDto();
        CommonLevelDto commonLevelDto3 = new CommonLevelDto();
        CommonLevelDto commonLevelDto4 = new CommonLevelDto();
        CommonLevelDto commonLevelDto5 = new CommonLevelDto();
        CommonLevelDto commonLevelDto6 = new CommonLevelDto();
        CommonLevelDto commonLevelDto7 = new CommonLevelDto();
        CommonLevelDto commonLevelDto8 = new CommonLevelDto();
        CommonLevelDto commonLevelDto9 = new CommonLevelDto();
        CommonLevelDto commonLevelDto10 = new CommonLevelDto();
        CommonLevelDto commonLevelDto11 = new CommonLevelDto();
        CommonLevelDto commonLevelDto12 = new CommonLevelDto();
        CommonLevelDto commonLevelDto13 = new CommonLevelDto();
        CommonLevelDto commonLevelDto14 = new CommonLevelDto();
        CommonLevelDto commonLevelDto15 = new CommonLevelDto();
        CommonLevelDto commonLevelDto16 = new CommonLevelDto();
        CommonLevelDto commonLevelDto17 = new CommonLevelDto();
        CommonLevelDto commonLevelDto18 = new CommonLevelDto();
        
        commonLevelDto1.setGroup("GTS_SALES_ACTUALS");
        commonLevelDto2.setGroup("GTS_SALES_PROJECTED");
        commonLevelDto3.setGroup("CONTRACT_SALES_ACTUALS");
        commonLevelDto4.setGroup("CONTRACT_SALES_PROJECTED");
        commonLevelDto5.setGroup("CONTRACT_UNITS_ACTUALS");
        commonLevelDto6.setGroup("CONTRACT_UNITS_PROJECTED");
        commonLevelDto7.setGroup("TOTAL_DISCOUNT");
        commonLevelDto8.setGroup("TOTAL_DISCOUNT_PROJECTED");
        commonLevelDto9.setGroup("TOTAL_DISCOUNT_PERCENTAGE");
        commonLevelDto10.setGroup("TOTAL_DISCOUNT_PROJECTED_PERCENTAGE");
        commonLevelDto11.setGroup("% OF BUSINESS");
        commonLevelDto12.setGroup("% OF BUSINESS_PROJECTED");
        commonLevelDto13.setGroup("PPA_DISCOUNT");
        commonLevelDto14.setGroup("PPA_DISCOUNT_PROJECTED");
        commonLevelDto15.setGroup("PPA_DISCOUNT_PERCENTAGE");
        commonLevelDto16.setGroup("PPA_DISCOUNT_PROJECTED_PERCENTAGE");
        commonLevelDto17.setGroup("NET_SALES");
        commonLevelDto18.setGroup("NET_SALES_PROJECTED");
        for (Object list1 : list) {
            final Object[] obj = (Object[]) list1;
            int i = 4;
            if(!CommonUtils.ANNUAL.equals(frequency)) {
                i = 5;
            }

            String year = String.valueOf(obj[i]);
            if(CommonUtils.SEMI_ANNUAL.equals(frequency)) {
                year = "S"+String.valueOf(obj[4])+" "+String.valueOf(obj[i]);
            } else if (CommonUtils.QUARTERLY.equals(frequency)) {
                year = "Q"+String.valueOf(obj[4])+" "+String.valueOf(obj[i]);
            } else if (CommonUtils.MONTHLY.equals(frequency)) {
                String monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + obj[4]) - 1);
                year = monthName+" "+String.valueOf(obj[i]);
            }

            if(!"null".equals(year)){
            xAxis.add(year);
            commonLevelDto1.addStringProperties(year, String.valueOf(obj[1]));
            commonLevelDto2.addStringProperties(year, String.valueOf(obj[2]));
            commonLevelDto3.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto4.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto5.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto6.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto7.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto8.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto9.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto10.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto11.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto12.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto13.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto14.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto15.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto16.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto17.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto18.addStringProperties(year, String.valueOf(obj[++i]));
              }    
                  }    
        commonLevelDto1.setxAxis(xAxis);
        commonDtoList.add(commonLevelDto1);
        commonDtoList.add(commonLevelDto2);
        commonDtoList.add(commonLevelDto3);
        commonDtoList.add(commonLevelDto4);
        commonDtoList.add(commonLevelDto5);
        commonDtoList.add(commonLevelDto6);
        commonDtoList.add(commonLevelDto7);
        commonDtoList.add(commonLevelDto8);
        commonDtoList.add(commonLevelDto9);
        commonDtoList.add(commonLevelDto10);
        commonDtoList.add(commonLevelDto11);
        commonDtoList.add(commonLevelDto12);
        commonDtoList.add(commonLevelDto13);
        commonDtoList.add(commonLevelDto14);
        commonDtoList.add(commonLevelDto15);
        commonDtoList.add(commonLevelDto16);
        commonDtoList.add(commonLevelDto17);
        commonDtoList.add(commonLevelDto18);
    
    return commonDtoList;
    
    }
    
    public List<CommonLevelDto> customizeCFFResults(List<Object[]> list, String frequency){
        
        List<CommonLevelDto> commonDtoList=new ArrayList<CommonLevelDto>();
        List<String> xAxis = new ArrayList<String>();
        CommonLevelDto commonLevelDto1 = new CommonLevelDto();
        CommonLevelDto commonLevelDto2 = new CommonLevelDto();
        CommonLevelDto commonLevelDto3 = new CommonLevelDto();
        CommonLevelDto commonLevelDto4 = new CommonLevelDto();
        CommonLevelDto commonLevelDto5 = new CommonLevelDto();
        CommonLevelDto commonLevelDto6 = new CommonLevelDto();
        
        commonLevelDto1.setGroup("EX_FACTORY_SALES_ACTUALS");
        commonLevelDto2.setGroup("EX_FACTORY_SALES_PROJECTED");
        commonLevelDto3.setGroup("CONTRACT_SALES_ACTUALS");
        commonLevelDto4.setGroup("CONTRACT_SALES_PROJECTED");
        commonLevelDto5.setGroup("CONTRACT_UNITS_ACTUALS");
        commonLevelDto6.setGroup("CONTRACT_UNITS_PROJECTED");
        for (Object list1 : list) {
            final Object[] obj = (Object[]) list1;
            

            String year = String.valueOf(obj[3]);
            if(CommonUtils.SEMI_ANNUAL.equals(frequency)) {
                year = "S"+String.valueOf(obj[4])+" "+String.valueOf(obj[3]);
            } else if (CommonUtils.QUARTERLY.equals(frequency)) {
                year = "Q"+String.valueOf(obj[4])+" "+String.valueOf(obj[3]);
            } else if (CommonUtils.MONTHLY.equals(frequency)) {
                String monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + obj[4]) - 1);
                year = monthName+" "+String.valueOf(obj[3]);
            }
            if(!"null".equals(year)){
            xAxis.add(year);
             
                commonLevelDto1.addStringProperties(year, String.valueOf(obj[1]));
                commonLevelDto2.addStringProperties(year, String.valueOf(obj[2]));
                commonLevelDto3.addStringProperties(year, String.valueOf(obj[5]));
                commonLevelDto4.addStringProperties(year, String.valueOf(obj[6]));
                commonLevelDto5.addStringProperties(year, String.valueOf(obj[7]));
                commonLevelDto6.addStringProperties(year, String.valueOf(obj[8])); 
            
            
              }    
                  }    
        commonLevelDto1.setxAxis(xAxis);
        commonDtoList.add(commonLevelDto1);
        commonDtoList.add(commonLevelDto2);
        commonDtoList.add(commonLevelDto3);
        commonDtoList.add(commonLevelDto4);
        commonDtoList.add(commonLevelDto5);
        commonDtoList.add(commonLevelDto6);
        
    
    return commonDtoList;
    
    }
    
    
    
     public List<CommonLevelDto> customizeResultsForGovt(List<Object[]> list, String frequency){
        
        List<CommonLevelDto> commonDtoList=new ArrayList<CommonLevelDto>();
        List<String> xAxis=new ArrayList<String>();
        CommonLevelDto commonLevelDto1 = new CommonLevelDto();
        CommonLevelDto commonLevelDto2 = new CommonLevelDto();
        CommonLevelDto commonLevelDto3 = new CommonLevelDto();
        CommonLevelDto commonLevelDto4 = new CommonLevelDto();
        CommonLevelDto commonLevelDto5 = new CommonLevelDto();
        CommonLevelDto commonLevelDto6 = new CommonLevelDto();
        CommonLevelDto commonLevelDto7 = new CommonLevelDto();
        CommonLevelDto commonLevelDto8 = new CommonLevelDto();
        CommonLevelDto commonLevelDto9 = new CommonLevelDto();
        CommonLevelDto commonLevelDto10 = new CommonLevelDto();
        CommonLevelDto commonLevelDto11 = new CommonLevelDto();
        CommonLevelDto commonLevelDto12 = new CommonLevelDto();
        CommonLevelDto commonLevelDto13 = new CommonLevelDto();
        CommonLevelDto commonLevelDto14 = new CommonLevelDto();
        CommonLevelDto commonLevelDto15 = new CommonLevelDto();
        CommonLevelDto commonLevelDto16 = new CommonLevelDto();
        CommonLevelDto commonLevelDto17 = new CommonLevelDto();
        CommonLevelDto commonLevelDto18 = new CommonLevelDto();
        CommonLevelDto commonLevelDto19 = new CommonLevelDto();
        CommonLevelDto commonLevelDto20 = new CommonLevelDto();
        CommonLevelDto commonLevelDto21 = new CommonLevelDto();
        CommonLevelDto commonLevelDto22 = new CommonLevelDto();
        
        commonLevelDto1.setGroup("GTS_SALES_ACTUALS");
        commonLevelDto2.setGroup("GTS_SALES_PROJECTED");
        commonLevelDto3.setGroup("CONTRACT_SALES_ACTUALS");
        commonLevelDto4.setGroup("CONTRACT_SALES_PROJECTED");
        commonLevelDto5.setGroup("CONTRACT_UNITS_ACTUALS");
        commonLevelDto6.setGroup("CONTRACT_UNITS_PROJECTED");
        commonLevelDto7.setGroup("MANDATED_DISCOUNT_ACTUALS");
        commonLevelDto8.setGroup("MANDATED_DISCOUNT_PROJECTED");
        commonLevelDto9.setGroup("SUPPLEMENTAL_DISCOUNT_ACTUALS");
        commonLevelDto10.setGroup("SUPPLEMENTAL_DISCOUNT_PROJECTED");
        commonLevelDto11.setGroup("MANDATED_DISCOUNT_ACTUALS_PERCENTAGE");
        commonLevelDto12.setGroup("MANDATED_DISCOUNT_PROJECTED_PERCENTAGE");
        commonLevelDto13.setGroup("SUPPLEMENTAL_DISCOUNT_ACTUALS_PERCENTAGE");
        commonLevelDto14.setGroup("SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE");
        commonLevelDto15.setGroup("TOTAL_DISCOUNT_ACTUAL");
        commonLevelDto16.setGroup("TOTAL_DISCOUNT_PROJECTED");
        commonLevelDto17.setGroup("TOTAL_DISCOUNT_ACTUAL_PERCENTAGE");
        commonLevelDto18.setGroup("TOTAL_DISCOUNT_PROJECTED_PERCENTAGE");
        commonLevelDto19.setGroup("% OF BUSINESS");
        commonLevelDto20.setGroup("% OF BUSINESS_PROJECTED");
        commonLevelDto21.setGroup("NET_SALES_ACTUALS");
        commonLevelDto22.setGroup("NET_SALES_PROJECTED");
        for (Object list1 : list) {
            final Object[] obj = (Object[]) list1;

            int i = 3;
            if(!CommonUtils.ANNUAL.equals(frequency)) {
                i = 4;
            }

            String year = String.valueOf(obj[i]);
            if(CommonUtils.SEMI_ANNUAL.equals(frequency)) {
                year = "S"+String.valueOf(obj[3])+" "+String.valueOf(obj[i]);
            } else if (CommonUtils.QUARTERLY.equals(frequency)) {
                year = "Q"+String.valueOf(obj[3])+" "+String.valueOf(obj[i]);
            } else if (CommonUtils.MONTHLY.equals(frequency)) {
                String monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + obj[3]) - 1);
                year = monthName+" "+String.valueOf(obj[i]);
            }

            if(!"null".equals(year)){
                xAxis.add(year);
            commonLevelDto1.addStringProperties(year, String.valueOf(obj[1]));
            commonLevelDto2.addStringProperties(year, String.valueOf(obj[2]));
            commonLevelDto3.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto4.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto5.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto6.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto7.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto8.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto9.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto10.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto11.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto12.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto13.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto14.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto15.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto16.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto17.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto18.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto19.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto20.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto21.addStringProperties(year, String.valueOf(obj[++i]));
            commonLevelDto22.addStringProperties(year, String.valueOf(obj[++i]));
              }    
             }
        commonLevelDto1.setxAxis(xAxis);
        
        commonDtoList.add(commonLevelDto1);
        commonDtoList.add(commonLevelDto2);
        commonDtoList.add(commonLevelDto3);
        commonDtoList.add(commonLevelDto4);
        commonDtoList.add(commonLevelDto5);
        commonDtoList.add(commonLevelDto6);
        commonDtoList.add(commonLevelDto7);
        commonDtoList.add(commonLevelDto8);
        commonDtoList.add(commonLevelDto9);
        commonDtoList.add(commonLevelDto10);
        commonDtoList.add(commonLevelDto11);
        commonDtoList.add(commonLevelDto12);
        commonDtoList.add(commonLevelDto13);
        commonDtoList.add(commonLevelDto14);
        commonDtoList.add(commonLevelDto15);
        commonDtoList.add(commonLevelDto16);
        commonDtoList.add(commonLevelDto17);
        commonDtoList.add(commonLevelDto18);
        commonDtoList.add(commonLevelDto19);
        commonDtoList.add(commonLevelDto20);
        commonDtoList.add(commonLevelDto21);
        commonDtoList.add(commonLevelDto22);
        
        
    
    return commonDtoList;
    
    }
     
     public List<CommonLevelDto> customizeReturnsSales(List<Object[]> list){
        
        List<CommonLevelDto> commonDtoList=new ArrayList<CommonLevelDto>();
        CommonLevelDto commonLevelDto1 = new CommonLevelDto();
        CommonLevelDto commonLevelDto2 = new CommonLevelDto();
        CommonLevelDto commonLevelDto3 = new CommonLevelDto();
        CommonLevelDto commonLevelDto4 = new CommonLevelDto();
        CommonLevelDto commonLevelDto5 = new CommonLevelDto();
        CommonLevelDto commonLevelDto6 = new CommonLevelDto();
        CommonLevelDto commonLevelDto7 = new CommonLevelDto();
        
        commonLevelDto1.setGroup("ACTUAL_RETURN_PERCENT");
        commonLevelDto2.setGroup("ACTUAL_RPU");
        commonLevelDto3.setGroup("ACTUAL_RETURN_AMOUNT");
        commonLevelDto4.setGroup("PROJECTED_RETURN_PERCENT");
        commonLevelDto5.setGroup("PROJECTED_RPU");
        commonLevelDto6.setGroup("PROJECTED_RETURN_AMOUNT");
        commonLevelDto7.setGroup("GROWTH_RATE");
        for (Object list1 : list) {
            final Object[] obj = (Object[]) list1;

            String year = String.valueOf(obj[0]);

            if(!"null".equals(year)){
            commonLevelDto1.addStringProperties(year, String.valueOf(obj[1]));
            commonLevelDto2.addStringProperties(year, String.valueOf(obj[2]));
            commonLevelDto3.addStringProperties(year, String.valueOf(obj[3]));
            commonLevelDto4.addStringProperties(year, String.valueOf(obj[4]));
            commonLevelDto5.addStringProperties(year, String.valueOf(obj[5]));
            commonLevelDto6.addStringProperties(year, String.valueOf(obj[6]));
            commonLevelDto7.addStringProperties(year, String.valueOf(obj[7]));
              }    
             }
        
        commonDtoList.add(commonLevelDto1);
        commonDtoList.add(commonLevelDto2);
        commonDtoList.add(commonLevelDto3);
        commonDtoList.add(commonLevelDto4);
        commonDtoList.add(commonLevelDto5);
        commonDtoList.add(commonLevelDto6);
        commonDtoList.add(commonLevelDto7);
        
        
    
    return commonDtoList;
    
    }
     
      private List convertPPAResultList(List list) {
        List finalList = new ArrayList();
        
        Map<String,PeriodDTO> periodMap=new HashMap<String,PeriodDTO>();
        Set<String> keySet = new HashSet<String>();
        PeriodDTO dto = new PeriodDTO();

        for (int i = 0; i < list.size(); i++) {
            dto = new PeriodDTO();
            Object obj[] = (Object[]) list.get(i);
            if (obj[0] != null) {
                dto.setYear(String.valueOf(obj[0]));
                keySet.add(String.valueOf(obj[0]));
            }
            if (obj[4] != null) {
                dto.setDiscountPerUnit(String.valueOf(obj[4]));
            }
            if (obj[2] != null) {
                dto.setDiscountPer(String.valueOf(obj[2]));
            }
            if (obj[3] != null) {
                dto.setUnitVolume(String.valueOf(obj[3]));
            }
            if (obj[1] != null) {
                dto.setTotDiscountAmount(String.valueOf(obj[1]));
            }
            periodMap.put(dto.getYear(), dto);
          }
        finalList.add(periodMap);
        finalList.add(keySet);
        return finalList;
    }
      
      public List<String> getProjectionId(){
          List<String> resultList=new ArrayList<String>();
          
          String userId=String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));

          String query = " SELECT TOP 1  pm.PROJECTION_MASTER_SID as projId,pm.PROJECTION_NAME as projName, pm.FORECASTING_TYPE as forecastType"
                  + " FROM   PROJECTION_MASTER pm JOIN WORKFLOW_MASTER wm ON pm.PROJECTION_MASTER_SID = wm.PROJECTION_MASTER_SID  "
                  + " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID=wm.WORKFLOW_STATUS_ID AND  HT.LIST_NAME = 'WorkFlowStatus' AND HT.DESCRIPTION in ('Approved')  "
                  + " LEFT JOIN HIERARCHY_DEFINITION hd_cust ON hd_cust.HIERARCHY_DEFINITION_SID = pm.CUSTOMER_HIERARCHY_SID  "
                  + " LEFT JOIN HIERARCHY_DEFINITION hd_prod ON hd_prod.HIERARCHY_DEFINITION_SID = pm.PRODUCT_HIERARCHY_SID "
                  + " WHERE pm.CREATED_BY='" + userId + "'  and pm.FORECASTING_TYPE in ('Non Mandated','Mandated','Returns','AccrualRateProjection') ORDER  BY wm.MODIFIED_DATE DESC ";
         
         
      List list =(List)RsModelLocalServiceUtil.executeSelectQuery(query,null,null);
      if(list!=null && !list.isEmpty()){
          Object []obj=(Object[])list.get(0);
          resultList.add(String.valueOf(obj[0]));
          resultList.add(String.valueOf(obj[1]));
          resultList.add(String.valueOf(obj[2]));
         
       }else{
         resultList.add(String.valueOf(0));
          resultList.add("No Projections are Available");
          resultList.add("No Projections are Available");
      }
      
      return resultList;
      }
      
      
      public List<String> getTopProjectionId(String status,String type){
          List<String> resultList=new ArrayList<String>();
          
          String userId=String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));

          String query = "select pm.PROJECTION_MASTER_SID as projId,pm.PROJECTION_NAME as projName, pm.FORECASTING_TYPE as forecastType"+
"                  FROM   PROJECTION_MASTER pm \n" +
"                  WHERE  pm.FORECASTING_TYPE in ("+type+") and pm.IS_APPROVED='"+status+"' ORDER  BY pm.MODIFIED_DATE DESC;";
         
      List list =(List)RsModelLocalServiceUtil.executeSelectQuery(query,null,null);
      if(list!=null && !list.isEmpty()){
          Object []obj=(Object[])list.get(0);
          resultList.add(String.valueOf(obj[0]));
          resultList.add(String.valueOf(obj[1]));
          resultList.add(String.valueOf(obj[2]));
         
       }else{
         resultList.add(String.valueOf(0));
          resultList.add("No Projections are Available");
          resultList.add("No Projections are Available");
      }
      
      return resultList;
      }
      
      
      public List<CommonLevelDto> customizeResultsForGovtDPR(List<Object> list, String frequency,String salesORUnits) {

        List<CommonLevelDto> commonDtoList = new ArrayList<CommonLevelDto>();
        List<String> xAxis = new ArrayList<String>();
        CommonLevelDto commonLevelDto1 = new CommonLevelDto();
        CommonLevelDto commonLevelDto2 = new CommonLevelDto();
        CommonLevelDto commonLevelDto3 = new CommonLevelDto();
        CommonLevelDto commonLevelDto4 = new CommonLevelDto();

        commonLevelDto1.setGroup("MANDATED" + " Actual Amount");
        commonLevelDto2.setGroup("MANDATED" + " Projected Amount");
        commonLevelDto3.setGroup("SUPPLEMENTAL" + " Actual Amount");
        commonLevelDto4.setGroup("SUPPLEMENTAL" + " Projected Amount");

        PeriodDTO dto = new PeriodDTO();
        for (Object list1 : list) {
            final Object[] obj = (Object[]) list1;
            dto = new PeriodDTO();
            String year = String.valueOf(obj[0]);
            if (CommonUtils.SEMI_ANNUAL.equals(frequency)) {
                year = "S" + String.valueOf(obj[7]) + " " + String.valueOf(obj[0]);
            } else if (CommonUtils.QUARTERLY.equals(frequency)) {
                year = "Q" + String.valueOf(obj[7]) + " " + String.valueOf(obj[0]);
            } else if (CommonUtils.MONTHLY.equals(frequency)) {
                String monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + obj[7]) - 1);
                year = monthName + " " + String.valueOf(obj[0]);
            }

            if (obj[6] != null) {
                dto.setActualsOrProj(Integer.parseInt(String.valueOf(obj[6])));
            }
            if (!"null".equals(year)) {
                if(!xAxis.contains(year)) {
                    xAxis.add(year);
                }
                if ("MANDATED".equalsIgnoreCase(String.valueOf(obj[5]))) {
                    if (dto.getActualsOrProj() == 0) {
                        commonLevelDto2.addStringProperties(year,"sales".equalsIgnoreCase(salesORUnits)?String.valueOf(obj[3]):String.valueOf(obj[4]));
                    } else {
                        commonLevelDto1.addStringProperties(year,"sales".equalsIgnoreCase(salesORUnits)?String.valueOf(obj[1]):String.valueOf(obj[2]));
                    }
                } else if ("SUPPLEMENTAL".equalsIgnoreCase(String.valueOf(obj[5]))) {
                    if (dto.getActualsOrProj() == 0) {
                        commonLevelDto4.addStringProperties(year,"sales".equalsIgnoreCase(salesORUnits)? String.valueOf(obj[3]):String.valueOf(obj[4]));
                    } else {
                        commonLevelDto3.addStringProperties(year,"sales".equalsIgnoreCase(salesORUnits)? String.valueOf(obj[1]):String.valueOf(obj[2]));
                    }
                }

            }
        }
        commonLevelDto1.setxAxis(xAxis);
        commonDtoList.add(commonLevelDto1);
        commonDtoList.add(commonLevelDto2);
        commonDtoList.add(commonLevelDto3);
        commonDtoList.add(commonLevelDto4);

        return commonDtoList;

    }
      
      public List<CommonLevelDto> getDiscountChartDataMan(int projectionId,String projType,String frequency,String salesORUnits) {
        
       
            List<Object> list = (List<Object>) RsModelLocalServiceUtil.executeSelectQuery(buildGovtDiscountChartQuery(projectionId,frequency),null,null);
            return  customizeResultsForGovtDPR(list,frequency,salesORUnits); 
            }
 public List<Object[]> getPieChart(int projId,String freq,String arguValue){
 
     String query = "select   "+arguValue+",per.\"YEAR\" As Year"+(CommonUtils.ANNUAL.equalsIgnoreCase(freq)?"":",per."+freq+" As Periods ")+"  FROM FORECASTING_DASHBOARD FD\n"
             + " Join \"PERIOD\" per ON FD.PERIOD_SID=per.PERIOD_SID\n"
             + " where PROJECTION_MASTER_SID="+projId+"\n"
             + " group by per.\"YEAR\"\n"+(CommonUtils.ANNUAL.equals(freq)?"":",per."+freq+"")
             + " order by per.\"YEAR\"";   
      List<Object[]> list = (List<Object[]>) RsModelLocalServiceUtil.executeSelectQuery(query, null, null);
      return list;
     
 }     
      
}
