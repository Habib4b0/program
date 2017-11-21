/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.discountprojectionresults.logic;

import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import static com.stpl.app.utils.Constants.FrequencyConstants.*;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author STPL
 */
public class DPRQueryBuilder {
    private static final Logger LOGGER = Logger.getLogger(DPRQueryBuilder.class);
    
    public List getCCPDetailsID(int ProjectionMasterSid, String hierarchyNo, String levelNo) {
        String sql = StringUtils.EMPTY;
        try {
            sql = "SELECT  LCCP.CCP_DETAILS_SID FROM   (SELECT CCPMAP.CCP_DETAILS_SID,HLD.HIERARCHY_NO,HLD.RELATIONSHIP_LEVEL_SID FROM "
                    + "  (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO,CCP.CCP_DETAILS_SID FROM   RELATIONSHIP_LEVEL_DEFINITION RLD"
                    + " JOIN   CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID = CCP.RELATIONSHIP_LEVEL_SID "
                    + "JOIN   PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID "
                    + "AND PD.PROJECTION_MASTER_SID =" + ProjectionMasterSid
                    + "JOIN   PROJECTION_MASTER PM ON PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID"
                    + " WHERE  PM.PROJECTION_MASTER_SID ='" + ProjectionMasterSid + "') CCPMAP,"
                    + "(SELECT RLD1.HIERARCHY_NO,RLD1.RELATIONSHIP_LEVEL_SID FROM   RELATIONSHIP_LEVEL_DEFINITION RLD1 "
                    + "JOIN   PROJECTION_CUST_HIERARCHY PCH ON PCH.RELATIONSHIP_LEVEL_SID = RLD1.RELATIONSHIP_LEVEL_SID "
                    + "AND PCH.PROJECTION_MASTER_SID =" + ProjectionMasterSid + " WHERE  RLD1.HIERARCHY_NO LIKE '" + hierarchyNo + "') HLD "
                    + "WHERE  CCPMAP.HIERARCHY_NO LIKE HLD.HIERARCHY_NO+'%') LCCP WHERE "
                    + "LCCP.HIERARCHY_NO IN (SELECT RLD2.HIERARCHY_NO FROM   RELATIONSHIP_LEVEL_DEFINITION RLD2 "
                    + "JOIN   PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID = RLD2.RELATIONSHIP_LEVEL_SID "
                    + "AND PCH2.PROJECTION_MASTER_SID =" + ProjectionMasterSid + " WHERE  RLD2.LEVEL_NO ='" + levelNo + "')";
            return HelperTableLocalServiceUtil.executeSelectQuery(sql);
        } catch (Exception e) {
            LOGGER.error(e);
            LOGGER.error(sql);
        }
        return Collections.emptyList();
    }
    
    public List getCCPDetailsIDForProductHierarchy(int ProjectionMasterSid, String hierarchyNo, String levelNo) {
        String sql = StringUtils.EMPTY;
        try {
            sql = "SELECT  LCCP.CCP_DETAILS_SID FROM   (SELECT CCPMAP.CCP_DETAILS_SID,HLD.HIERARCHY_NO,HLD.RELATIONSHIP_LEVEL_SID FROM "
                    + "  (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO,CCP.CCP_DETAILS_SID FROM   RELATIONSHIP_LEVEL_DEFINITION RLD"
                    + " JOIN   CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID = CCP.RELATIONSHIP_LEVEL_SID "
                    + "JOIN   PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID "
                    + "AND PD.PROJECTION_MASTER_SID =" + ProjectionMasterSid
                    + " JOIN   PROJECTION_MASTER PM ON PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID"
                    + " WHERE  PM.PROJECTION_MASTER_SID ='" + ProjectionMasterSid + "') CCPMAP,"
                    + "(SELECT RLD1.HIERARCHY_NO,RLD1.RELATIONSHIP_LEVEL_SID FROM   RELATIONSHIP_LEVEL_DEFINITION RLD1 "
                    + "JOIN   PROJECTION_PROD_HIERARCHY PCH ON PCH.RELATIONSHIP_LEVEL_SID = RLD1.RELATIONSHIP_LEVEL_SID "
                    + "AND PCH.PROJECTION_MASTER_SID =" + ProjectionMasterSid + " WHERE  RLD1.HIERARCHY_NO LIKE '" + hierarchyNo + "') HLD "
                    + "WHERE  CCPMAP.HIERARCHY_NO LIKE HLD.HIERARCHY_NO+'%') LCCP WHERE "
                    + "LCCP.HIERARCHY_NO IN (SELECT RLD2.HIERARCHY_NO FROM   RELATIONSHIP_LEVEL_DEFINITION RLD2 "
                    + "JOIN   PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID = RLD2.RELATIONSHIP_LEVEL_SID "
                    + "AND PCH2.PROJECTION_MASTER_SID =" + ProjectionMasterSid + " WHERE  RLD2.LEVEL_NO ='" + levelNo + "')";
            return HelperTableLocalServiceUtil.executeSelectQuery(sql);

        } catch (Exception e) {
            LOGGER.error(e);
            LOGGER.error(sql);
        } 
        return Collections.emptyList();
    }
    
     public List getDiscountProjectionResults(List<Integer> discountprojectionId, String frequency, String discountString, String view,  List<Integer> startAndEndPeriods, SessionDTO session) {

        String projectionQuery = "";
        try {
            String idString = "";
            for (int i = 0; i < discountprojectionId.size(); i++) {
                if (i != discountprojectionId.size() - 1) {
                    idString = idString + discountprojectionId.get(i) + ",";
                } else {
                    idString = idString + discountprojectionId.get(i);
                }
            }
            String startPeriod = "";
            String endPeriod = "";

            String forecastStartPeriod = "";
            String forecastEndPeriod = "";
            if (discountString.equals("0")) {
                discountString = "'" + discountString + "'";
            }
            if (startAndEndPeriods != null && startAndEndPeriods.size() != 0) {
                String hsYear = String.valueOf(startAndEndPeriods.get(0));
                String hsMonth = String.valueOf(startAndEndPeriods.get(1));
                String heYear;
                String heMonth;

                String fsYear;
                String fsMonth;
                String feYear = String.valueOf(startAndEndPeriods.get(NumericConstants.SIX));
                String feMonth = String.valueOf(startAndEndPeriods.get(NumericConstants.SEVEN));

                if (hsMonth.length() == 1) {
                    hsMonth = "0" + hsMonth;
                }
                startPeriod = hsYear + hsMonth;
                
                Calendar calendar = Calendar.getInstance();
                int month = calendar.get(Calendar.MONTH) + 1;
                calendar.add(Calendar.MONTH, -((month % NumericConstants.THREE) == 0 ? NumericConstants.THREE : (month % NumericConstants.THREE)));
                heMonth = String.valueOf(calendar.get(Calendar.MONTH) + 1);
                heYear = String.valueOf(calendar.get(Calendar.YEAR));
                if (heMonth.length() == 1) {
                    heMonth = "0" + heMonth;
                }
                endPeriod = heYear + heMonth;
               
                calendar = Calendar.getInstance();
                month = calendar.get(Calendar.MONTH) + 1;
                calendar.add(Calendar.MONTH, -((month % NumericConstants.THREE) == 0 ? NumericConstants.THREE : (month % NumericConstants.THREE)));
                fsMonth = String.valueOf(calendar.get(Calendar.MONTH) + NumericConstants.TWO);
                fsYear = String.valueOf(calendar.get(Calendar.YEAR));
                if (fsMonth.length() == 1) {
                    fsMonth = "0" + fsMonth;
                }
                forecastStartPeriod = fsYear + fsMonth;
                if (feMonth.length() == 1) {
                    feMonth = "0" + feMonth;
                }
                forecastEndPeriod = feYear + feMonth;

            }
            if (frequency.equals(QUARTERLY.getConstant())) {
                frequency = Constant.QUARTER;
            }
            if (frequency.equals(ANNUALLY.getConstant())) {
                frequency = "YEAR";
            }
            if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
                frequency = Constant.SEMI_ANNUAL;

            }
            if (frequency.equals(MONTHLY.getConstant())) {
                frequency = MONTH;
            }

            
            projectionQuery = "SELECT PR.YEAR, PR." + frequency + " AS BASE, 0.00 AS ACTUAL_SALES,0.00 AS ACTUAL_DISCOUNT,MAX(NMSP.PROJECTION_SALES) \n"
                    + "  AS PROJECTION_SALES,SUM(NMDP.PROJECTION_SALES) AS PROJECTION_DISCOUNT,PR." + frequency + ",PR.MONTH,SUM(NMDP.PROJECTION_RATE) AS PROJECTION_RATE,0.0 AS ACTUAL_UNITS,"
                    + " Sum(NMSP.PROJECTION_UNITS) as PROJECTION_UNITS\n"
                    + " FROM PROJECTION_DETAILS PD JOIN ST_NM_DISCOUNT_PROJ_MASTER NMDPM ON  NMDPM.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n"
                    + "  JOIN ST_NM_DISCOUNT_PROJECTION NMDP  ON NMDP.PROJECTION_DETAILS_SID = NMDPM.PROJECTION_DETAILS_SID\n"
                    + " AND NMDPM.RS_CONTRACT_SID = NMDP.RS_CONTRACT_SID \n"
                    + " JOIN PERIOD PR ON PR.PERIOD_SID = NMDP.PERIOD_SID JOIN ST_NM_SALES_PROJECTION NMSP ON NMSP.PERIOD_SID = PR.PERIOD_SID\n"
                    + "  JOIN ST_NM_SALES_PROJECTION_MASTER NMSPM ON NMSPM.PROJECTION_DETAILS_SID = NMSP.PROJECTION_DETAILS_SID \n"
                    + " AND PD.PROJECTION_DETAILS_SID = NMSPM.PROJECTION_DETAILS_SID JOIN RS_CONTRACT RSM ON RSM.RS_CONTRACT_SID = NMDPM.RS_CONTRACT_SID "
                    + "  AND RSM.RS_CONTRACT_SID = NMDP.RS_CONTRACT_SID WHERE PD.PROJECTION_DETAILS_SID in (" + idString + ") "
                    + " AND cast(PR.YEAR as varchar(4))+RIGHT('0'+CAST(PR.MONTH AS VARCHAR),2)>=" + forecastStartPeriod + ""
                    + "  AND  cast(PR.YEAR as varchar(4))+RIGHT('0'+CAST(PR.MONTH AS VARCHAR),2) < = " + forecastEndPeriod + ""
                    + " and  RSM.RS_NAME IN (" + discountString + ")"
                    + " GROUP BY PR.YEAR,PR." + frequency + ",PR.MONTH,PD.PROJECTION_DETAILS_SID"
                    + UNION_ALL
                    + " SELECT PR.YEAR ,PR." + frequency + " AS BASE, MAX(NMAS.ACTUAL_SALES) AS ACTUAL_SALES,  SUM(NMAD.ACTUAL_SALES)"
                    + " AS ACTUAL_DISCOUNT,MAX(ISNULL(NMSP.PROJECTION_SALES, 0)) AS PROJECTION_SALES, SUM(ISNULL(NMDP.PROJECTION_SALES, 0))\n"
                    + " AS PROJECTION_DISCOUNT, PR." + frequency + ",PR.MONTH,SUM(NMDP.PROJECTION_RATE) AS PROJECTION_RATE,"
                    + " Sum(ACTUAL_UNITS) as ACTUAL_UNITS,"
                    + " Sum(NMSP.PROJECTION_UNITS) as PROJECTION_UNITS"
                    + " FROM PROJECTION_DETAILS PD\n"
                    + " JOIN ST_NM_DISCOUNT_PROJ_MASTER NMADM ON  NMADM.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID \n"
                    + " JOIN ST_NM_ACTUAL_DISCOUNT NMAD ON NMAD.PROJECTION_DETAILS_SID =NMADM.PROJECTION_DETAILS_SID\n"
                    + " AND NMADM.RS_CONTRACT_SID = NMAD.RS_CONTRACT_SID\n"
                    + " JOIN RS_CONTRACT RSM ON RSM.RS_CONTRACT_SID = NMADM.RS_CONTRACT_SID AND RSM.RS_CONTRACT_SID = NMAD.RS_CONTRACT_SID\n"
                    + " JOIN PERIOD PR ON PR.PERIOD_SID = NMAD.PERIOD_SID JOIN ST_NM_ACTUAL_SALES NMAS ON NMAS.PERIOD_SID = PR.PERIOD_SID \n"
                    + " AND NMAS.PROJECTION_DETAILS_SID = NMADM.PROJECTION_DETAILS_SID JOIN ST_NM_SALES_PROJECTION_MASTER NMSPM  "
                    + " ON NMSPM.PROJECTION_DETAILS_SID = NMAS.PROJECTION_DETAILS_SID  \n"
                    + "  LEFT JOIN ST_NM_SALES_PROJECTION NMSP"
                    + " ON NMSP.PROJECTION_DETAILS_SID = NMSPM.PROJECTION_DETAILS_SID AND NMSP.PERIOD_SID = PR.PERIOD_SID"
                    + " LEFT JOIN ST_NM_DISCOUNT_PROJECTION NMDP"
                    + " ON NMDP.PROJECTION_DETAILS_SID =  NMSPM.PROJECTION_DETAILS_SID AND NMDP.PERIOD_SID = PR.PERIOD_SID"
                    + " AND NMDP.RS_CONTRACT_SID = RSM.RS_CONTRACT_SID  "
                    + " WHERE PD.PROJECTION_DETAILS_SID in (" + idString + ")  AND cast(PR.YEAR as varchar(4))+RIGHT ('0'+CAST(PR.MONTH AS VARCHAR),2) > = " + startPeriod + ""
                    + " AND cast(PR.YEAR as varchar(4))+RIGHT('0'+CAST(PR.MONTH AS VARCHAR),2) <=" + endPeriod + ""
                    + " and  RSM.RS_NAME IN (" + discountString + ")"
                    + "GROUP BY PR.YEAR,PR." + frequency + ",PR.MONTH,PD.PROJECTION_DETAILS_SID ";
            if (view.equalsIgnoreCase("parent")) {
                if (frequency.equals("YEAR") || frequency.equals(MONTH)) {
                    projectionQuery = projectionQuery + "ORDER BY PR.YEAR,PR.MONTH";
                } else {
                    projectionQuery = projectionQuery + "ORDER BY PR.YEAR,PR." + frequency + Constant.PRMONTH;
                }
            } else {
                if (frequency.equals("YEAR") || frequency.equals(MONTH)) {
                    projectionQuery = projectionQuery + "ORDER BY RSM.RS_NAME,PR.YEAR,PR.MONTH";
                } else {
                    projectionQuery = projectionQuery + "ORDER BY RSM.RS_NAME,PR.YEAR,PR." + frequency + Constant.PRMONTH;
                }
            }
            return HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(projectionQuery, session.getCurrentTableNames()));
        } catch (Exception e) {
            LOGGER.error(e);
            LOGGER.error(projectionQuery);
        }
        return Collections.emptyList();
    }
    public static final String UNION_ALL = " UNION ALL";
    public static final String MONTH = "MONTH";

       
     public List getAllPesriodDiscount(List<Integer> discountprojectionId, String frequency, String discountName, List<Integer> startAndEndPeriods, SessionDTO session) {

        String sql = "";
        try {
            String idString = "";
            for (int i = 0; i < discountprojectionId.size(); i++) {
                if (i != discountprojectionId.size() - 1) {
                    idString = idString + discountprojectionId.get(i) + ",";
                } else {
                    idString = idString + discountprojectionId.get(i);
                }
            }
            String startPeriod = "";
            String endPeriod = "";
            String forecastStartPeriod = "";
            String forecastEndPeriod = "";
            if (startAndEndPeriods != null && startAndEndPeriods.size() != 0) {
                String hsYear = String.valueOf(startAndEndPeriods.get(0));
                String hsMonth = String.valueOf(startAndEndPeriods.get(1));
                String heYear;
                String heMonth;

                String fsYear;
                String fsMonth;
                String feYear = String.valueOf(startAndEndPeriods.get(NumericConstants.SIX));
                String feMonth = String.valueOf(startAndEndPeriods.get(NumericConstants.SEVEN));

                if (hsMonth.length() == 1) {
                    hsMonth = "0" + hsMonth;
                }
                startPeriod = hsYear + hsMonth;
                
                Calendar calendar = Calendar.getInstance();
                int month = calendar.get(Calendar.MONTH) + 1;
                calendar.add(Calendar.MONTH, -((month % NumericConstants.THREE) == 0 ? NumericConstants.THREE : (month % NumericConstants.THREE)));
                heMonth = String.valueOf(calendar.get(Calendar.MONTH) + 1);
                heYear = String.valueOf(calendar.get(Calendar.YEAR));
                if (heMonth.length() == 1) {
                    heMonth = "0" + heMonth;
                }
                endPeriod = heYear + heMonth;

                calendar = Calendar.getInstance();
                month = calendar.get(Calendar.MONTH) + 1;
                calendar.add(Calendar.MONTH, -((month % NumericConstants.THREE) == 0 ? NumericConstants.THREE : (month % NumericConstants.THREE)));
                fsMonth = String.valueOf(calendar.get(Calendar.MONTH) + NumericConstants.TWO);
                fsYear = String.valueOf(calendar.get(Calendar.YEAR));
                if (fsMonth.length() == 1) {
                    fsMonth = "0" + fsMonth;
                }
                forecastStartPeriod = fsYear + fsMonth;
                if (feMonth.length() == 1) {
                    feMonth = "0" + feMonth;
                }
                forecastEndPeriod = feYear + feMonth;

            }
            if (frequency.equals(QUARTERLY.getConstant())) {
                frequency = Constant.QUARTER;
            }
            if (frequency.equals(ANNUALLY.getConstant())) {
                frequency = "YEAR";
            }
            if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
                frequency = Constant.SEMI_ANNUAL;
            }
            if (frequency.equals(MONTHLY.getConstant())) {
                frequency = MONTH;
            }
            sql = " SELECT PR.YEAR, PR." + frequency + " AS BASE, 0.00 AS ACTUAL_SALES,0.00 AS ACTUAL_DISCOUNT,MAX(NMSP.PROJECTION_SALES) \n"
                    + " AS PROJECTION_SALES,SUM(NMDP.PROJECTION_SALES) AS PROJECTION_DISCOUNT,PR." + frequency + ", PR.MONTH,RSM.RS_NAME,SUM(NMDP.PROJECTION_RATE) AS PROJECTION_RATE,0.0 AS ACTUAL_UNITS,"
                    + " Sum(NMSP.PROJECTION_UNITS) as PROJECTION_UNITS\n"
                    + "  FROM PROJECTION_DETAILS PD JOIN ST_NM_DISCOUNT_PROJ_MASTER NMDPM ON  NMDPM.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n"
                    + "  JOIN ST_NM_DISCOUNT_PROJECTION NMDP  ON NMDP.PROJECTION_DETAILS_SID = NMDPM.PROJECTION_DETAILS_SID\n"
                    + " AND NMDPM.RS_CONTRACT_SID = NMDP.RS_CONTRACT_SID \n"
                    + " JOIN PERIOD PR ON PR.PERIOD_SID = NMDP.PERIOD_SID JOIN ST_NM_SALES_PROJECTION NMSP ON NMSP.PERIOD_SID = PR.PERIOD_SID\n"
                    + "  JOIN ST_NM_SALES_PROJECTION_MASTER NMSPM ON NMSPM.PROJECTION_DETAILS_SID = NMSP.PROJECTION_DETAILS_SID\n"
                    + " AND PD.PROJECTION_DETAILS_SID = NMSPM.PROJECTION_DETAILS_SID JOIN RS_CONTRACT RSM ON RSM.RS_CONTRACT_SID = NMDPM.RS_CONTRACT_SID"
                    + " AND RSM.RS_CONTRACT_SID = NMDP.RS_CONTRACT_SID WHERE PD.PROJECTION_DETAILS_SID  in (" + idString + ") "
                    + " AND cast(PR.YEAR as varchar(4))+RIGHT('0'+CAST(PR.MONTH AS VARCHAR),2)>=" + forecastStartPeriod + ""
                    + " AND  cast(PR.YEAR as varchar(4))+RIGHT('0'+CAST(PR.MONTH  AS VARCHAR),2) <=" + forecastEndPeriod + ""
                    + Constant.AND_RSMRS_NAME_IN + discountName + ")"
                    + "GROUP BY  PR.YEAR,PR." + frequency + ",PR.MONTH,PD.PROJECTION_DETAILS_SID,RSM.RS_NAME "
                    + UNION_ALL
                    + "  SELECT PR.YEAR,PR." + frequency + " AS BASE,  MAX(NMAS.ACTUAL_SALES) AS ACTUAL_SALES, SUM(NMAD.ACTUAL_SALES)"
                    + "  AS ACTUAL_DISCOUNT,MAX(ISNULL(NMSP.PROJECTION_SALES, 0)) AS PROJECTION_SALES, SUM(ISNULL(NMDP.PROJECTION_SALES, 0))\n"
                    + " AS PROJECTION_DISCOUNT,PR." + frequency + ",PR.MONTH, RSM.RS_NAME, SUM(NMDP.PROJECTION_RATE) AS PROJECTION_RATE, Sum(ACTUAL_UNITS) as ACTUAL_UNITS,\n"
                    + " Sum(NMSP.PROJECTION_UNITS) as PROJECTION_UNITS  FROM PROJECTION_DETAILS PD \n"
                    + " JOIN ST_NM_DISCOUNT_PROJ_MASTER NMADM ON  NMADM.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID \n"
                    + " JOIN ST_NM_ACTUAL_DISCOUNT  NMAD ON NMAD.PROJECTION_DETAILS_SID =NMADM.PROJECTION_DETAILS_SID\n"
                    + " AND NMADM.RS_CONTRACT_SID = NMAD.RS_CONTRACT_SID\n"
                    + " JOIN RS_CONTRACT RSM ON RSM.RS_CONTRACT_SID = NMADM.RS_CONTRACT_SID AND RSM.RS_CONTRACT_SID = NMAD.RS_CONTRACT_SID\n"
                    + " JOIN PERIOD PR ON PR.PERIOD_SID = NMAD.PERIOD_SID JOIN ST_NM_ACTUAL_SALES NMAS ON NMAS.PERIOD_SID = PR.PERIOD_SID \n"
                    + " AND NMAS.PROJECTION_DETAILS_SID = NMADM.PROJECTION_DETAILS_SID JOIN ST_NM_SALES_PROJECTION_MASTER NMSPM  "
                    + " ON NMSPM.PROJECTION_DETAILS_SID = NMAS.PROJECTION_DETAILS_SID  LEFT JOIN ST_NM_SALES_PROJECTION NMSP"
                    + " ON NMSP.PROJECTION_DETAILS_SID = NMSPM.PROJECTION_DETAILS_SID AND NMSP.PERIOD_SID = PR.PERIOD_SID"
                    + " LEFT JOIN ST_NM_DISCOUNT_PROJECTION NMDP"
                    + " ON NMDP.PROJECTION_DETAILS_SID =  NMSPM.PROJECTION_DETAILS_SID AND NMDP.PERIOD_SID = PR.PERIOD_SID"
                    + " AND NMDP.RS_CONTRACT_SID = RSM.RS_CONTRACT_SID  "
                    + " WHERE PD.PROJECTION_DETAILS_SID in (" + idString + ")  AND cast(PR.YEAR as varchar(4))+RIGHT ('0'+CAST(PR.MONTH AS VARCHAR),2) >= " + startPeriod + ""
                    + " AND cast(PR.YEAR as varchar(4))+RIGHT('0'+CAST(PR.MONTH AS VARCHAR),2) <=" + endPeriod + ""
                    + Constant.AND_RSMRS_NAME_IN + discountName + ")"
                    + "GROUP BY  PR.YEAR,PR." + frequency + ",PR.MONTH,PD.PROJECTION_DETAILS_SID, RSM.RS_NAME  ";

            if (frequency.equals("YEAR") || frequency.equals(MONTH)) {
                sql = sql + " ORDER BY RSM.RS_NAME,PR.YEAR,PR.MONTH";
            } else {
                sql = sql + " ORDER BY RSM.RS_NAME,PR.YEAR,PR." + frequency + Constant.PRMONTH;
            }
            return HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(sql, session.getCurrentTableNames()));
        } catch (Exception e) {
            LOGGER.error(e);
            LOGGER.error(sql);
        } 
        return Collections.emptyList();
    }
     
     public List getTotalDiscountNumber(List<Integer> projectionDetailsId, String frequency, String discountName, List<Integer> startAndEndPeriods,SessionDTO session,List<Object> view) {
        String projectionQuery = "";
        try {
            String idString = "";
            for (int i = 0; i < projectionDetailsId.size(); i++) {
                if (i != projectionDetailsId.size() - 1) {
                    idString = idString + projectionDetailsId.get(i) + ",";
                } else {
                    idString = idString + projectionDetailsId.get(i);
                }
            }
            String startPeriod = "";
            String endPeriod = "";
            String forecastStartPeriod = "";
            String forecastEndPeriod = "";
            if (startAndEndPeriods != null && startAndEndPeriods.size() != 0) {
                String hsYear = String.valueOf(startAndEndPeriods.get(0));
                String hsMonth = String.valueOf(startAndEndPeriods.get(1));
                String heYear = String.valueOf(startAndEndPeriods.get(NumericConstants.TWO));
                String heMonth = String.valueOf(startAndEndPeriods.get(NumericConstants.THREE));

                String fsYear = String.valueOf(startAndEndPeriods.get(NumericConstants.FOUR));
                String fsMonth = String.valueOf(startAndEndPeriods.get(NumericConstants.FIVE));
                String feYear = String.valueOf(startAndEndPeriods.get(NumericConstants.SIX));
                String feMonth = String.valueOf(startAndEndPeriods.get(NumericConstants.SEVEN));

                if (hsMonth.length() == 1) {
                    hsMonth = "0" + hsMonth;
                }
                startPeriod = hsYear + hsMonth;
                if (heMonth.length() == 1) {
                    heMonth = "0" + heMonth;
                }
                endPeriod = heYear + heMonth;
                if (fsMonth.length() == 1) {
                    fsMonth = "0" + fsMonth;
                }
                forecastStartPeriod = fsYear + fsMonth;
                if (feMonth.length() == 1) {
                    feMonth = "0" + feMonth;
                }
                forecastEndPeriod = feYear + feMonth;

            }
            if (frequency.equals(QUARTERLY.getConstant())) {
                frequency = Constant.QUARTER;
            }
            if (frequency.equals(ANNUALLY.getConstant())) {
                frequency = "YEAR";
            }
            if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
                frequency = Constant.SEMI_ANNUAL;

            }
            if (frequency.equals(MONTHLY.getConstant())) {
                frequency = MONTH;
            }
            projectionQuery = "SELECT PR.YEAR,PR." + frequency + " AS BASE, 0.00 AS ACTUAL_SALES,0.00 AS ACTUAL_DISCOUNT,MAX(NMSP.PROJECTION_SALES)\n"
                    + " AS PROJECTION_SALES,SUM(NMDP.PROJECTION_SALES) AS PROJECTION_DISCOUNT,PR." + frequency + ",PR.MONTH,RSM.RS_NAME,SUM(NMDP.PROJECTION_RATE) AS PROJECTION_RATE,0.0 AS ACTUAL_UNITS,"
                    + " Sum(NMSP.PROJECTION_UNITS) as PROJECTION_UNITS \n"
                    + "  FROM PROJECTION_DETAILS PD JOIN ST_NM_DISCOUNT_PROJ_MASTER NMDPM ON  NMDPM.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n"
                    + " JOIN ST_NM_DISCOUNT_PROJECTION NMDP  ON NMDP.PROJECTION_DETAILS_SID = NMDPM.PROJECTION_DETAILS_SID\n"
                    + " AND NMDPM.RS_CONTRACT_SID = NMDP.RS_CONTRACT_SID\n"
                    + "  JOIN PERIOD PR ON PR.PERIOD_SID = NMDP.PERIOD_SID JOIN ST_NM_SALES_PROJECTION NMSP ON NMSP.PERIOD_SID = PR.PERIOD_SID\n"
                    + "  JOIN ST_NM_SALES_PROJECTION_MASTER NMSPM ON NMSPM.PROJECTION_DETAILS_SID = NMSP.PROJECTION_DETAILS_SID \n"
                    + " AND PD.PROJECTION_DETAILS_SID = NMSPM.PROJECTION_DETAILS_SID JOIN RS_CONTRACT RSM ON RSM.RS_CONTRACT_SID = NMDPM.RS_CONTRACT_SID "
                    + " AND RSM.RS_CONTRACT_SID = NMDP.RS_CONTRACT_SID WHERE PD.PROJECTION_DETAILS_SID in (" + idString + ") "
                    + " AND cast(PR.YEAR as varchar(4))+RIGHT('0'+CAST(PR.MONTH AS VARCHAR),2) >=" + forecastStartPeriod + ""
                    + " AND  cast(PR.YEAR as varchar(4))+RIGHT('0'+CAST(PR.MONTH AS VARCHAR),2) <= " + forecastEndPeriod + ""
                    + Constant.AND_RSMRS_NAME_IN + discountName + ")"
                    + " GROUP BY PR.YEAR,PR." + frequency + ",PR.MONTH,PD.PROJECTION_DETAILS_SID,RSM.RS_NAME"
                    + UNION_ALL
                    + "  SELECT PR.YEAR,PR." + frequency + " AS BASE, MAX(NMAS.ACTUAL_SALES) AS ACTUAL_SALES, SUM(NMAD.ACTUAL_SALES)"
                    + "  AS ACTUAL_DISCOUNT,MAX(ISNULL(NMSP.PROJECTION_SALES, 0)) AS PROJECTION_SALES, SUM(ISNULL(NMDP.PROJECTION_SALES, 0))\n"
                    + " AS  PROJECTION_DISCOUNT,PR." + frequency + ",PR.MONTH, RSM.RS_NAME,SUM(NMDP.PROJECTION_RATE) AS PROJECTION_RATE,"
                    + " Sum(ACTUAL_UNITS) as ACTUAL_UNITS,"
                    + " Sum(NMSP.PROJECTION_UNITS) as PROJECTION_UNITS"
                    + " FROM PROJECTION_DETAILS PD\n"
                    + " JOIN ST_NM_DISCOUNT_PROJ_MASTER NMADM ON  NMADM.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n"
                    + "  JOIN ST_NM_ACTUAL_DISCOUNT NMAD ON NMAD.PROJECTION_DETAILS_SID =NMADM.PROJECTION_DETAILS_SID\n"
                    + " AND NMADM.RS_CONTRACT_SID = NMAD.RS_CONTRACT_SID \n"
                    + "  JOIN RS_CONTRACT RSM ON RSM.RS_CONTRACT_SID = NMADM.RS_CONTRACT_SID AND RSM.RS_CONTRACT_SID = NMAD.RS_CONTRACT_SID\n"
                    + " JOIN PERIOD  PR ON PR.PERIOD_SID = NMAD.PERIOD_SID JOIN ST_NM_ACTUAL_SALES NMAS ON NMAS.PERIOD_SID = PR.PERIOD_SID \n"
                    + " AND NMAS.PROJECTION_DETAILS_SID = NMADM.PROJECTION_DETAILS_SID JOIN ST_NM_SALES_PROJECTION_MASTER NMSPM "
                    + " ON NMSPM.PROJECTION_DETAILS_SID = NMAS.PROJECTION_DETAILS_SID LEFT JOIN ST_NM_SALES_PROJECTION NMSP"
                    + "  ON NMSP.PROJECTION_DETAILS_SID = NMSPM.PROJECTION_DETAILS_SID AND NMSP.PERIOD_SID = PR.PERIOD_SID"
                    + "  LEFT JOIN ST_NM_DISCOUNT_PROJECTION NMDP"
                    + " ON NMDP.PROJECTION_DETAILS_SID = NMSPM.PROJECTION_DETAILS_SID AND NMDP.PERIOD_SID = PR.PERIOD_SID"
                    + " AND NMDP.RS_CONTRACT_SID = RSM.RS_CONTRACT_SID "
                    + " WHERE PD.PROJECTION_DETAILS_SID in(" + idString + ") AND cast(PR.YEAR as varchar(4))+RIGHT ('0'+CAST(PR.MONTH AS VARCHAR),2) >=" + startPeriod + ""
                    + " AND  cast(PR.YEAR as varchar(4))+RIGHT('0'+CAST(PR.MONTH AS VARCHAR),2) <=" + endPeriod + ""
                    + Constant.AND_RSMRS_NAME_IN + discountName + ")"
                    + "GROUP BY PR.YEAR,PR." + frequency + ",PR.MONTH,PD.PROJECTION_DETAILS_SID, RSM.RS_NAME ";


            if (frequency.equals("YEAR") || frequency.equals(MONTH)) {
                 if(view.get(0) != null && "Descending".equalsIgnoreCase(String.valueOf(view.get(0)))){
                  projectionQuery = projectionQuery + "ORDER BY RSM.RS_NAME,PR.YEAR DESC,PR.MONTH DESC";    
               }else{
                projectionQuery = projectionQuery + "ORDER BY RSM.RS_NAME,PR.YEAR,PR.MONTH";
                 }
            } else {
                if(view.get(0) != null && "Descending".equalsIgnoreCase(String.valueOf(view.get(0)))){
                    projectionQuery = projectionQuery + "ORDER BY RSM.RS_NAME,PR.YEAR DESC,PR." + frequency + " DESC,PR.MONTH DESC";
                }else{
                  projectionQuery = projectionQuery + "ORDER BY RSM.RS_NAME,PR.YEAR,PR." + frequency + Constant.PRMONTH;  
                }
                
            }


            return HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(projectionQuery, session.getCurrentTableNames()));
        } catch (Exception e) {
          
            LOGGER.error(e);
            LOGGER.error(projectionQuery);
        } 
        return Collections.emptyList();
    }
     
     
     
    public List getSubDiscount(List<Integer> projectionDetailsId, String frequency, String discountList, List<Integer> startAndEndPeriods, SessionDTO sessionDto) {
        {
            String sql = "";
            try {
                String idString = "";
                for (int i = 0; i < projectionDetailsId.size(); i++) {
                    if (i != projectionDetailsId.size() - 1) {
                        idString = idString + projectionDetailsId.get(i) + ",";
                    } else {
                        idString = idString + projectionDetailsId.get(i);
                    }
                }
                String startPeriod = "";
                String endPeriod = "";
                String forecastStartPeriod = "";
                String forecastEndPeriod = "";
                if (startAndEndPeriods != null && startAndEndPeriods.size() != 0) {
                    String hsYear = String.valueOf(startAndEndPeriods.get(0));
                    String hsMonth = String.valueOf(startAndEndPeriods.get(1));
                    String heYear = String.valueOf(startAndEndPeriods.get(NumericConstants.TWO));
                    String heMonth = String.valueOf(startAndEndPeriods.get(NumericConstants.THREE));

                    String fsYear = String.valueOf(startAndEndPeriods.get(NumericConstants.FOUR));
                    String fsMonth = String.valueOf(startAndEndPeriods.get(NumericConstants.FIVE));
                    String feYear = String.valueOf(startAndEndPeriods.get(NumericConstants.SIX));
                    String feMonth = String.valueOf(startAndEndPeriods.get(NumericConstants.SEVEN));

                    if (hsMonth.length() == 1) {
                        hsMonth = "0" + hsMonth;
                    }
                    startPeriod = hsYear + hsMonth;
                    if (heMonth.length() == 1) {
                        heMonth = "0" + heMonth;
                    }
                    endPeriod = heYear + heMonth;
                    if (fsMonth.length() == 1) {
                        fsMonth = "0" + fsMonth;
                    }
                    forecastStartPeriod = fsYear + fsMonth;
                    if (feMonth.length() == 1) {
                        feMonth = "0" + feMonth;
                    }
                    forecastEndPeriod = feYear + feMonth;

                }

                if (frequency.equals(QUARTERLY.getConstant())) {
                    frequency = Constant.QUARTER;
                }
                if (frequency.equals(ANNUALLY.getConstant())) {
                    frequency = "YEAR";
                }
                if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
                    frequency = Constant.SEMI_ANNUAL;
                }
                if (frequency.equals(MONTHLY.getConstant())) {
                    frequency = MONTH;
                }
                sql = "SELECT PR.YEAR,PR." + frequency + " AS BASE, 0.00 AS ACTUAL_SALES,0.00 AS ACTUAL_DISCOUNT,MAX(NMSP.PROJECTION_SALES)\n"
                        + "  AS PROJECTION_SALES,SUM(NMDP.PROJECTION_SALES) AS PROJECTION_DISCOUNT,PR." + frequency + ",PR.MONTH,RSM.RS_NAME,SUM(NMDP.PROJECTION_RATE) AS PROJECTION_RATE,0.0 AS ACTUAL_UNITS,"
                        + " Sum(NMSP.PROJECTION_UNITS) as PROJECTION_UNITS \n"
                        + " FROM PROJECTION_DETAILS PD JOIN ST_NM_DISCOUNT_PROJ_MASTER NMDPM ON  NMDPM.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n"
                        + " JOIN ST_NM_DISCOUNT_PROJECTION NMDP  ON NMDP.PROJECTION_DETAILS_SID = NMDPM.PROJECTION_DETAILS_SID\n"
                        + " AND NMDPM.RS_CONTRACT_SID = NMDP.RS_CONTRACT_SID\n"
                        + "  JOIN PERIOD PR ON PR.PERIOD_SID = NMDP.PERIOD_SID JOIN ST_NM_SALES_PROJECTION NMSP ON NMSP.PERIOD_SID = PR.PERIOD_SID\n"
                        + "  JOIN ST_NM_SALES_PROJECTION_MASTER NMSPM ON NMSPM.PROJECTION_DETAILS_SID = NMSP.PROJECTION_DETAILS_SID\n"
                        + " AND PD.PROJECTION_DETAILS_SID = NMSPM.PROJECTION_DETAILS_SID JOIN RS_CONTRACT RSM ON RSM.RS_CONTRACT_SID = NMDPM.RS_CONTRACT_SID"
                        + " AND RSM.RS_CONTRACT_SID = NMDP.RS_CONTRACT_SID WHERE PD.PROJECTION_DETAILS_SID in (" + idString + ") "
                        + " AND cast(PR.YEAR as varchar(4))+RIGHT('0'+CAST(PR.MONTH AS VARCHAR),2) > =" + forecastStartPeriod + ""
                        + " AND  cast(PR.YEAR as varchar(4))+RIGHT('0'+CAST(PR.MONTH AS VARCHAR),2) < =" + forecastEndPeriod + ""
                        + Constant.AND_RSMRS_NAME_IN + discountList + ") "
                        + "GROUP BY PR.YEAR ,PR." + frequency + ",PR.MONTH,PD.PROJECTION_DETAILS_SID,RSM.RS_NAME "
                        + UNION_ALL
                        + " SELECT PR.YEAR, PR." + frequency + " AS BASE, MAX(NMAS.ACTUAL_SALES) AS ACTUAL_SALES, SUM(NMAD.ACTUAL_SALES)"
                        + " AS ACTUAL_DISCOUNT,MAX(ISNULL(NMSP.PROJECTION_SALES, 0)) AS PROJECTION_SALES, SUM(ISNULL(NMDP.PROJECTION_SALES, 0))\n"
                        + " AS PROJECTION_DISCOUNT,PR." + frequency + ",PR.MONTH, RSM.RS_NAME, SUM(NMDP.PROJECTION_RATE) AS PROJECTION_RATE, Sum(ACTUAL_UNITS) as ACTUAL_UNITS,\n"
                        + " Sum(NMSP.PROJECTION_UNITS) as PROJECTION_UNITS "
                        + "FROM PROJECTION_DETAILS PD\n"
                        + " JOIN ST_NM_DISCOUNT_PROJ_MASTER NMADM ON  NMADM.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n"
                        + "  JOIN ST_NM_ACTUAL_DISCOUNT NMAD ON NMAD.PROJECTION_DETAILS_SID =NMADM.PROJECTION_DETAILS_SID\n"
                        + " AND NMADM.RS_CONTRACT_SID = NMAD.RS_CONTRACT_SID \n"
                        + "  JOIN RS_CONTRACT RSM ON RSM.RS_CONTRACT_SID = NMADM.RS_CONTRACT_SID AND RSM.RS_CONTRACT_SID = NMAD.RS_CONTRACT_SID\n"
                        + " JOIN  PERIOD PR ON PR.PERIOD_SID = NMAD.PERIOD_SID JOIN ST_NM_ACTUAL_SALES NMAS ON NMAS.PERIOD_SID = PR.PERIOD_SID \n"
                        + " AND NMAS.PROJECTION_DETAILS_SID = NMADM.PROJECTION_DETAILS_SID JOIN ST_NM_SALES_PROJECTION_MASTER NMSPM "
                        + " ON NMSPM.PROJECTION_DETAILS_SID = NMAS.PROJECTION_DETAILS_SID LEFT JOIN ST_NM_SALES_PROJECTION NMSP"
                        + "  ON NMSP.PROJECTION_DETAILS_SID = NMSPM.PROJECTION_DETAILS_SID AND NMSP.PERIOD_SID = PR.PERIOD_SID"
                        + "  LEFT JOIN ST_NM_DISCOUNT_PROJECTION NMDP"
                        + " ON NMDP.PROJECTION_DETAILS_SID = NMSPM.PROJECTION_DETAILS_SID AND NMDP.PERIOD_SID = PR.PERIOD_SID"
                        + " AND NMDP.RS_CONTRACT_SID = RSM.RS_CONTRACT_SID "
                        + " WHERE PD.PROJECTION_DETAILS_SID in(" + idString + ")  AND cast(PR.YEAR as varchar(4))+RIGHT ('0'+CAST(PR.MONTH AS VARCHAR),2) >=" + startPeriod + ""
                        + " AND  cast(PR.YEAR as varchar(4))+RIGHT('0'+CAST(PR.MONTH AS VARCHAR),2) <=" + endPeriod + ""
                        + Constant.AND_RSMRS_NAME_IN + discountList + ")"
                        + "GROUP BY PR.YEAR, PR." + frequency + ",PR.MONTH,PD.PROJECTION_DETAILS_SID, RSM.RS_NAME ";

                sql = sql + " ORDER BY PR.YEAR,BASE,RSM.RS_NAME";
                return HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(sql, sessionDto.getCurrentTableNames()));
            } catch (Exception e) {
                LOGGER.error(e);
                LOGGER.error(sql);
            }
        }
        return Collections.emptyList();
    }
}
