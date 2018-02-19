/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.service.finderImpl;

import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.serviceUtils.Constants;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Abishek.Ram
 */
public class NmPpaProjectionMasterImpl {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(NmPpaProjectionMasterImpl.class);
    public List getPPAProjectionList(Integer projectionId, int levelNo, String parent, boolean last, int startIndex, int endIndex, boolean isCount, String levelName) {
      
        StringBuilder sql = new StringBuilder();
       
        
        List<Object> resultList = new ArrayList<Object>();
        try {
            if (isCount) {
                sql.append("select count(*) ");
            } else {
                sql.append("DECLARE @start INT=");
                sql.append(startIndex);
                sql.append(", @End   INT=");
                sql.append(endIndex);
                sql.append("SELECT LEVEL_NO,\n"
                        + "       LEVEL_NAME,\n"
                        + "       RELATIONSHIP_LEVEL_VALUES,\n"
                        + "       QUARTER,\n"
                        + "       YEAR,\n"
                        + "       USER_GROUP,\n"
                        + "       ACTUAL_PRICE_CAP,\n"
                        + "       PRICE_BASIS,\n"
                        + "       PRICE_CAP,\n"
                        + "       RESET,\n"
                        + "       PROJECTION_MASTER_SID,\n"
                        + "       PARENT_NODE,\n"
                        + "CHECK_RECORD,"
                        + "       rn \n"
                        + "FROM  (SELECT E.LEVEL_NO as LEVEL_NO,\n"
                        + "E.LEVEL_NAME,E.RELATIONSHIP_LEVEL_VALUES,\n"
                        + "Apr.QUARTER,Apr.YEAR,\n"
                        + "A.USER_GROUP,");
                if (last) {
                    sql.append("A.ACTUAL_PRICE_CAP,A.PRICE_BASIS,PA.PRICE_CAP,PA.RESET,\n");
                } else {
                    sql.append("sum(A.ACTUAL_PRICE_CAP)as ACTUAL_PRICE_CAP,(A.PRICE_BASIS) as PRICE_BASIS,sum(PA.PRICE_CAP) as PRICE_CAP,PA.RESET,\n");
                }
                sql.append("D.PROJECTION_MASTER_SID ");
                sql.append(",E.PARENT_NODE ,A.CHECK_RECORD,\n"
                        + "              Row_number()\n"
                        + "                OVER(\n"
                        + "                  ORDER BY E.LEVEL_NO, Apr.YEAR ) rn ");
            }
            
            sql.append("FROM  ST_NM_PPA_PROJECTION_MASTER A JOIN PROJECTION_DETAILS B ON"
                    + " A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID \n"
                    + "JOIN  PROJECTION_CUST_HIERARCHY D ON D.PROJECTION_MASTER_SID = B.PROJECTION_MASTER_SID \n"
                    + "JOIN  RELATIONSHIP_LEVEL_DEFINITION E ON E.RELATIONSHIP_LEVEL_SID = D.RELATIONSHIP_LEVEL_SID \n"
                    + "JOIN  ST_NM_PPA_PROJECTION PA ON PA.PROJECTION_DETAILS_SID=A.PROJECTION_DETAILS_SID \n"
                    + "JOIN  PERIOD Apr on PA.PERIOD_SID=Apr.PERIOD_SID  \n"
                    + "WHERE B.PROJECTION_MASTER_SID="
                    + projectionId + " and E.PARENT_NODE = '" + parent + "' \n"
                    + "and E.LEVEL_NO=" + levelNo + " \n");
            if (levelName != null&&!levelName.equals(StringUtils.EMPTY)) {
                sql.append(" E.LEVEL_NAME=");
                sql.append(levelName);
            }
//            LOGGER.debug("sql--->>>>" + sql);
            resultList = HelperTableLocalServiceUtil.executeSelectQuery(sql.toString());
//            LOGGER.debug("resultList.size--->>>" + resultList.size());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql.toString());
        }
        
        return resultList;
    }
    
    public void setPPAProjectionMassUpdate(Object priceCap, int startQuater, int endQuater, int startYear, int endYear, int projectionId,String parent,String levelValue) {

        StringBuilder sql = new StringBuilder();

       
        try {
            if(priceCap instanceof Boolean)
            {
                sql.append("UPDATE NM_PPA_PROJECTION_MASTER SET CHECK_RECORD=");
                if(Boolean.parseBoolean(priceCap.toString()))
                sql.append(1);
                else
                    sql.append(0);
                sql.append("where NM_PPA_PROJECTION_MASTER_SID ");
                 sql.append("in (\n"
                    + "SELECT \n"
                    + "PA.NM_PPA_PROJECTION_MASTER_SID \n");
                 sql.append("FROM NM_PPA_PROJECTION_MASTER A ");
            }
            else{
            sql.append("UPDATE NM_PPA_PROJECTION SET ");
            if (priceCap instanceof Double) {
                sql.append("PRICE_CAP=");
                sql.append(Double.valueOf(priceCap.toString()));
            } else if(priceCap instanceof String){
                sql.append("GROUP=");
                sql.append(priceCap.toString());
            }
            sql.append("where NM_PPA_PROJECTION_SID \n");
             sql.append("in (\n"
                    + "SELECT \n"
                    + "PA.NM_PPA_PROJECTION_SID \n"
                    + "FROM NM_PPA_PROJECTION_MASTER A \n");
            }
            sql.append("JOIN PROJECTION_DETAILS B ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID \n"
                    + "JOIN  PROJECTION_CUST_HIERARCHY D ON D.PROJECTION_MASTER_SID = B.PROJECTION_MASTER_SID \n"
                    + "JOIN  RELATIONSHIP_LEVEL_DEFINITION E ON E.RELATIONSHIP_LEVEL_SID = D.RELATIONSHIP_LEVEL_SID \n"
                    + "JOIN  NM_PPA_PROJECTION PA ON PA.NM_PPA_PROJECTION_MASTER_SID=A.NM_PPA_PROJECTION_MASTER_SID \n"
                    + "JOIN  \"PERIOD\" Apr on PA.PERIOD_SID=Apr.PERIOD_SID \n"
                    + "WHERE B.PROJECTION_MASTER_SID=");
            sql.append(projectionId);
             if(!(priceCap instanceof Boolean))
            {
            sql.append(" and A.CHECK_RECORD=1");
            }
//            sql.append(" AND E.PARENT_NODE ='");
//            sql.append(parentNode);
            sql.append(" AND Apr.PERIOD_SID in (SELECT PERIOD_SID FROM \"PERIOD\"  where PERIOD_SID in \n"
                    + "(SELECT PERIOD_SID FROM \"PERIOD\" where \"YEAR\" >=");
            sql.append(startYear);
            sql.append(" and \"YEAR\" <=");
            sql.append(endYear);
            sql.append(" And PERIOD_SID not in(SELECT PERIOD_SID FROM \"PERIOD\" where \"YEAR\" =");
            sql.append(startYear);
            sql.append(" and QUARTER <");
            sql.append(startQuater);
            sql.append(")\n"
                    + "and PERIOD_SID not in(SELECT PERIOD_SID FROM \"PERIOD\" where \"YEAR\" = ");
            sql.append(endYear);
            sql.append(" and QUARTER >");
            sql.append(endQuater);
            sql.append(")))\n"
                    + "GROUP  BY \n");
                    
              if(priceCap instanceof Boolean)
            {       
                  sql.append("PA.NM_PPA_PROJECTION_MASTER_SID);");
            }
              else
              {
                   sql.append("PA.NM_PPA_PROJECTION_SID);");
              }
//            LOGGER.debug("sql-->>" + sql);
            int a = HelperTableLocalServiceUtil.executeUpdateQueryCount(sql.toString());
//            LOGGER.debug("a--->>>>" + a);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql.toString());
        }
    }
    
    public List getPPAResults(Integer projectionId, int levelNo, String parent, boolean last, int startIndex, int endIndex, boolean isCount, List<String> input, String levelName) {
       
        StringBuilder sql = new StringBuilder();
        Integer startFrequency = null;
         String frequency = input.get(4);
          Integer startYear=0;
         Integer endYear=0;
          String custOrProd = input.get(5);
//         LOGGER.debug("");
        if(frequency.equals(Constants.MONTHLY))
        {
//              LOGGER.debug("input-->>"+ input );
             startYear = Integer.valueOf(input.get(1));
              endYear = Integer.valueOf(input.get(3));
        }
        else
        {
             startYear = Integer.valueOf(input.get(1));
              endYear = Integer.valueOf(input.get(3));
        }
        
       
        Integer endFrequency = 0;
       
        if (!frequency.equals(Constants.ANNUALLY)) {
            endFrequency = Integer.valueOf(input.get(2));
            startFrequency = Integer.valueOf(input.get(0));
        }
        
        List<Object> resultList = new ArrayList<Object>();
        try {
           
            sql.append("(SELECT ");
            if (levelName != null&&!levelName.equals(StringUtils.EMPTY)) {
                sql.append("RLD.LEVEL_NO                     AS LEVEL_NO,\n"
                        + "        RLD.LEVEL_NAME,\n"
                        + "        RLD.RELATIONSHIP_LEVEL_VALUES,");
            }
            
            if (frequency.equals("Quarterly")) {
                sql.append("PER.QUARTER,\n");
            }
            if (frequency.equals("Semi-Annually")) {
                sql.append("PER.SEMI_ANNUAL,\n");
            }
            if (frequency.equals(Constants.MONTHLY)) {
                sql.append("PER.MONTH,\n");
            }
            if(frequency.equals(Constants.ANNUALLY))
            {
                sql.append("PER.YEAR as Dummy,\n");
            }
            
            sql.append(" PER.YEAR,\n"
                    + "        Sum(APPA.ACTUAL_DISCOUNT_DOLLAR) AS ACTUAL_DISCOUNT_DOLLAR,\n"
                    + "        Sum(APPA.ACTUAL_PROJ_DISCOUNT_DOLLAR) AS ACTUAL_PROJ_DISCOUNT_DOLLAR,\n"
                    + "        Sum(APPA.ACTUAL_RATE)            AS ACTUAL_RATE,\n"
                    + "        Sum(APPA.ACTUAL_PROJECTION_RATE)      AS ACTUAL_PROJECTION_RATE,\n"
                    + "        Sum(APPA.ACTUAL_DISCOUNT_UNITS)  AS ACTUAL_DISCOUNT_UNITS,\n"
                    + "        Sum(APPA.ACTUAL_PROJ_DISCOUNT_UNITS)  AS ACTUAL_PROJ_DISCOUNT_UNITS,\n"
                    + "        Sum(APPA.ACTUAL_SALES)           AS ACTUAL_SALES,\n"
                    + "        Sum(APPA.ACTUAL_PROJECTION_SALES)     AS ACTUAL_PROJECTION_SALES\n");
            if (levelName != null&&!levelName.equals(StringUtils.EMPTY)) {
                sql.append(",PCH.PROJECTION_MASTER_SID,\n"
                        + "        RLD.PARENT_NODE");
            }
            
            sql.append(" FROM   dbo.PROJECTION_MASTER PM\n"
                    + " JOIN   dbo.PROJECTION_DETAILS PD ON PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
                    + " JOIN  ");
            if(custOrProd.equals("Customer"))
            {
                    sql.append(" PROJECTION_CUST_HIERARCHY PCH ");
            }else
            {
                 sql.append(" PROJECTION_PROD_HIERARCHY PCH ");
            }
                 sql.append(" ON PD.PROJECTION_MASTER_SID = PCH.PROJECTION_MASTER_SID\n"
                    + " JOIN   RELATIONSHIP_LEVEL_DEFINITION RLD ON RLD.RELATIONSHIP_LEVEL_SID = PCH.RELATIONSHIP_LEVEL_SID\n"
                    + " JOIN   dbo.ST_NM_PPA_PROJECTION_MASTER PPAM ON PD.PROJECTION_DETAILS_SID = PPAM.PROJECTION_DETAILS_SID\n"
                    + " JOIN   dbo.ST_NM_PPA_PROJECTION NMPPA ON PD.PROJECTION_DETAILS_SID = NMPPA.PROJECTION_DETAILS_SID\n"
                    + " JOIN   dbo.ST_NM_ACTUAL_PPA APPA ON PD.PROJECTION_DETAILS_SID = APPA.PROJECTION_DETAILS_SID\n"
                    + " JOIN   dbo.\"PERIOD\" PER ON PER.PERIOD_SID = APPA.PERIOD_SID\n"
                    + " WHERE  PM.PROJECTION_MASTER_SID = \n"
                    + projectionId);
            if (levelName != null&&!levelName.equals(StringUtils.EMPTY)) {
                sql.append("    AND LEVEL_NO = \n"
                        + levelNo
                        + "    AND PARENT_NODE = \n"
                        + "'"+parent+"'");
                sql.append(" and RELATIONSHIP_LEVEL_VALUES=");
                sql.append("'"+levelName+"'");
            }
            
            if (!frequency.equals(Constants.ANNUALLY)) {
                sql.append(" and PER.PERIOD_SID in (SELECT period_sid \n"
                        + " FROM \"PERIOD\" WHERE PERIOD_DATE>=(SELECT TOP 1 PERIOD_DATE FROM \"PERIOD\" WHERE ");
                if (frequency.equals("Quarterly")) {
                    sql.append("QUARTER=");
                    sql.append(startFrequency);
                    sql.append(" AND  YEAR=");
                    sql.append(startYear);
                    
                    sql.append(" ORDER BY PERIOD_SID)AND \n"
                            + " PERIOD_DATE<=(SELECT TOP 1 PERIOD_DATE FROM \"PERIOD\" WHERE QUARTER =");
                    sql.append(endFrequency);
                    sql.append("AND  YEAR=");
                    sql.append(endYear);
                    sql.append(" ORDER BY PERIOD_SID DESC))");
                }
                if (frequency.equals("Semi-Annually")) {
                    sql.append("SEMI_ANNUAL=");
                    sql.append(startFrequency);
                    sql.append(" AND  YEAR=");
                    sql.append(startYear);
                    
                    sql.append(" ORDER BY PERIOD_SID)AND \n"
                            + " PERIOD_DATE<=(SELECT TOP 1 PERIOD_DATE FROM \"PERIOD\" WHERE SEMI_ANNUAL =");
                    sql.append(endFrequency);
                    sql.append("AND  YEAR=");
                    sql.append(endYear);
                    sql.append(" ORDER BY PERIOD_SID DESC))");
                }
                if (frequency.equals(Constants.MONTHLY)) {
                    sql.append("MONTH=");
                    sql.append(startFrequency);
                    sql.append(" AND  YEAR=");
                    sql.append(startYear);
                    
                    sql.append(" ORDER BY PERIOD_SID)AND \n"
                            + " PERIOD_DATE<=(SELECT TOP 1 PERIOD_DATE FROM \"PERIOD\" WHERE MONTH =");
                    sql.append(endFrequency);
                    sql.append("AND  YEAR=");
                    sql.append(endYear);
                    sql.append(" ORDER BY PERIOD_SID DESC))");
                }
            } else {
                sql.append(" and PER.YEAR>=2015\n"
                        + " and PER.YEAR<=2017");
                
            }
            sql.append(" GROUP  BY ");
            if (levelName != null&&!levelName.equals(StringUtils.EMPTY)) {
                sql.append(" RLD.LEVEL_NO,\n"
                        + "           RLD.LEVEL_NAME,\n");
            }
            sql.append("             PER.YEAR \n");
            if (frequency.equals("Quarterly")) {
                sql.append(",PER.QUARTER \n");
            }
            if (frequency.equals("Semi-Annually")) {
                sql.append(",PER.SEMI_ANNUAL \n");
            }
            if (frequency.equals(Constants.MONTHLY)) {
                sql.append(",PER.MONTH \n");
            }
            
            if (levelName != null&&!levelName.equals(StringUtils.EMPTY)) {
                sql.append(" ,PPAM.USER_GROUP,\n"
                        + "       NMPPA.RESET,\n"
                        + "           PCH.PROJECTION_MASTER_SID\n");
                sql.append("           ,RLD.PARENT_NODE,\n"
                        + "           RLD.RELATIONSHIP_LEVEL_VALUES  ");
            }
            sql.append(") UNION\n"
                    + "(SELECT ");
            if (levelName != null&&!levelName.equals(StringUtils.EMPTY)) {
                sql.append("RLD.LEVEL_NO                         AS LEVEL_NO,\n"
                        + "        RLD.LEVEL_NAME,\n"
                        + "        RLD.RELATIONSHIP_LEVEL_VALUES,\n");
            }
            if (frequency.equals("Quarterly")) {
                sql.append("PER.QUARTER,\n");
            }
            if (frequency.equals("Semi-Annually")) {
                sql.append("PER.SEMI_ANNUAL,\n");
            }
            if (frequency.equals(Constants.MONTHLY)) {
                sql.append("PER.MONTH,\n");
            }
            if(frequency.equals(Constants.ANNUALLY))
            {
                sql.append("PER.YEAR as Dummy,\n");
            }
            sql.append("        PER.YEAR,\n"
                    + "        Sum(NMPPA.PROJECTION_DISCOUNT_DOLLAR)AS PROJECTION_DISCOUNT_DOLLAR,\n"
                    + "		NULL AS ACTUAL_PROJ_DISCOUNT_DOLLAR,\n"
                    + "        Sum(NMPPA.PROJECTION_RATE)           AS PROJECTION_RATE,\n"
                    + "		NULL AS ACTUAL_PROJECTION_RATE,\n"
                    + "        Sum(NMPPA.PROJECTION_DISCOUNT_UNITS) AS PROJECTION_DISCOUNT_UNITS,\n"
                    + "		NULL AS ACTUAL_PROJ_DISCOUNT_UNITS,\n"
                    + "        Sum(NMPPA.PROJECTION_SALES)          AS PROJECTION_SALES,\n"
                    + "		NULL AS ACTUAL_PROJ_DISCOUNT_UNITS\n");
            
            if (levelName != null&&!levelName.equals(StringUtils.EMPTY)) {
                sql.append("       , PCH.PROJECTION_MASTER_SID,\n"
                        + "        RLD.PARENT_NODE\n");
            }
             sql.append(" FROM   dbo.PROJECTION_MASTER PM\n"
                    + " JOIN   dbo.PROJECTION_DETAILS PD ON PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
                    + " JOIN  ");
            if(custOrProd.equals("Customer"))
            {
                    sql.append(" PROJECTION_CUST_HIERARCHY PCH ");
            }else
            {
                 sql.append(" PROJECTION_PROD_HIERARCHY PCH ");
            }
                sql.append(" ON PD.PROJECTION_MASTER_SID = PCH.PROJECTION_MASTER_SID\n"
                    + " JOIN   RELATIONSHIP_LEVEL_DEFINITION RLD ON RLD.RELATIONSHIP_LEVEL_SID = PCH.RELATIONSHIP_LEVEL_SID\n"
                    + " JOIN   dbo.ST_NM_PPA_PROJECTION_MASTER PPAM ON PD.PROJECTION_DETAILS_SID = PPAM.PROJECTION_DETAILS_SID\n"
                    + " JOIN   dbo.ST_NM_PPA_PROJECTION NMPPA ON PD.PROJECTION_DETAILS_SID = NMPPA.PROJECTION_DETAILS_SID\n"
                    + " JOIN   dbo.ST_NM_ACTUAL_PPA APPA ON PD.PROJECTION_DETAILS_SID = APPA.PROJECTION_DETAILS_SID\n"
                    + " JOIN   dbo.\"PERIOD\" PER ON PER.PERIOD_SID = NMPPA.PERIOD_SID\n"
                    + " WHERE  PM.PROJECTION_MASTER_SID = \n"
                    + projectionId);
            if (levelName != null&&!levelName.equals(StringUtils.EMPTY)) {
                sql.append("    AND LEVEL_NO = \n"
                        + levelNo
                        + "    AND PARENT_NODE =\n"
                        + "'"+parent+"'");
                sql.append(" and RELATIONSHIP_LEVEL_VALUES=");
                sql.append("'"+levelName+"'");
            }
            if (!frequency.equals(Constants.ANNUALLY)) {
                sql.append(" and PER.PERIOD_SID in (SELECT period_sid \n"
                        + " FROM \"PERIOD\" WHERE PERIOD_DATE>=(SELECT TOP 1 PERIOD_DATE FROM \"PERIOD\" WHERE ");
                if (frequency.equals("Quarterly")) {
                    sql.append("QUARTER=");
                    sql.append(startFrequency);
                    sql.append(" AND  YEAR=");
                    sql.append(startYear);
                    
                    sql.append(" ORDER BY PERIOD_SID)AND \n"
                            + " PERIOD_DATE<=(SELECT TOP 1 PERIOD_DATE FROM \"PERIOD\" WHERE QUARTER =");
                    sql.append(endFrequency);
                    sql.append("AND  YEAR=");
                    sql.append(endYear);
                    sql.append(" ORDER BY PERIOD_SID DESC))");
                }
                if (frequency.equals("Semi-Annually")) {
                    sql.append("SEMI_ANNUAL=");
                    sql.append(startFrequency);
                    sql.append(" AND  YEAR=");
                    sql.append(startYear);
                    
                    sql.append(" ORDER BY PERIOD_SID)AND \n"
                            + " PERIOD_DATE<=(SELECT TOP 1 PERIOD_DATE FROM \"PERIOD\" WHERE SEMI_ANNUAL =");
                    sql.append(endFrequency);
                    sql.append("AND  YEAR=");
                    sql.append(endYear);
                    sql.append(" ORDER BY PERIOD_SID DESC))");
                }
                if (frequency.equals(Constants.MONTHLY)) {
                    sql.append("MONTH=");
                    sql.append(startFrequency);
                    sql.append(" AND  YEAR=");
                    sql.append(startYear);
                    
                    sql.append(" ORDER BY PERIOD_SID)AND \n"
                            + " PERIOD_DATE<=(SELECT TOP 1 PERIOD_DATE FROM \"PERIOD\" WHERE MONTH =");
                    sql.append(endFrequency);
                    sql.append("AND  YEAR=");
                    sql.append(endYear);
                    sql.append(" ORDER BY PERIOD_SID DESC))");
                }
            } else {
                sql.append(" and PER.YEAR>=2015\n"
                        + " and PER.YEAR<=2017");
                
            }
             sql.append(" GROUP  BY ");
            if (levelName != null&&!levelName.equals(StringUtils.EMPTY)) {
                sql.append(" RLD.LEVEL_NO,\n"
                        + "           RLD.LEVEL_NAME,\n");
            }
            sql.append("           PER.YEAR\n");
            if (frequency.equals("Quarterly")) {
                sql.append(",PER.QUARTER \n");
            }
            if (frequency.equals("Semi-Annually")) {
                sql.append(",PER.SEMI_ANNUAL \n");
            }
            if (frequency.equals(Constants.MONTHLY)) {
                sql.append(",PER.MONTH\n");
            }
            if (levelName != null&&!levelName.equals(StringUtils.EMPTY)) {
                sql.append(", PPAM.USER_GROUP,\n"
                        + "       NMPPA.RESET,\n"
                        + "           PCH.PROJECTION_MASTER_SID,\n"
                        + "           RLD.PARENT_NODE,\n"
                        + "           RLD.RELATIONSHIP_LEVEL_VALUES ");
            }
            sql.append(" )ORDER BY YEAR");
            
            if (frequency.equals("Quarterly")) {
                sql.append(",QUARTER\n");
            }
            if (frequency.equals("Semi-Annually")) {
                sql.append(",SEMI_ANNUAL\n");
            }
            if (frequency.equals(Constants.MONTHLY)) {
                sql.append(",MONTH\n");
            }
            
//            LOGGER.debug("sql--->>>>" + sql);
            resultList = HelperTableLocalServiceUtil.executeSelectQuery(sql.toString());
//            LOGGER.debug("resultList.size--->>>" + resultList.size());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql.toString());
        }
        
        return resultList;
    }
     
   public List getLevelValues(int projectionId, int levelNo, String parent) {
     
        StringBuilder sql = new StringBuilder();
        
        List<Object> resultList = new ArrayList<Object>();
        try {
          
            sql.append("SELECT        RLD.RELATIONSHIP_LEVEL_VALUES, RLD.LEVEL_NO,RLD.HIERARCHY_LEVEL_DEFINITION_SID, \n"
                    + "                        RLD.PARENT_NODE,RLD.HIERARCHY_NO,RLD.RELATIONSHIP_BUILDER_SID \n"
                    + "FROM            RELATIONSHIP_LEVEL_DEFINITION RLD JOIN\n"
                    + "                         PROJECTION_CUST_HIERARCHY  PCH ON \n"
                    + "                         RLD.RELATIONSHIP_LEVEL_SID = PCH.RELATIONSHIP_LEVEL_SID  JOIN\n"
                    + "                         PROJECTION_MASTER PM ON PCH.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID  JOIN\n"
                    + "                         PROJECTION_DETAILS PD ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID\n"
                    + "WHERE        PM.PROJECTION_MASTER_SID = "
                    +projectionId
                    + " and RLD.LEVEL_NO="
                    +levelNo
                    + " and RLD.PARENT_NODE='"
                    +parent
                    + "' group by RLD.RELATIONSHIP_LEVEL_VALUES,RLD.LEVEL_NO,\n"
                    + "RLD.PARENT_NODE,RLD.HIERARCHY_LEVEL_DEFINITION_SID,RLD.HIERARCHY_NO,RLD.RELATIONSHIP_BUILDER_SID \n" );
//            LOGGER.debug("sql--->>>>" + sql);
            resultList = HelperTableLocalServiceUtil.executeSelectQuery(sql.toString());
//            LOGGER.debug("resultList.size--->>>" + resultList.size());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql.toString());
        }
        
        return resultList;
    }
     
      public List getProductHierarchyLevel(int projectionId, int levelNo, String parent) {
        StringBuilder sql = new StringBuilder();
        
        List<Object> resultList = new ArrayList<Object>();
        try {
            sql.append("SELECT        RLD.RELATIONSHIP_LEVEL_VALUES, RLD.LEVEL_NO,RLD.HIERARCHY_LEVEL_DEFINITION_SID, \n"
                    + "                        RLD.PARENT_NODE,RLD.HIERARCHY_NO,RLD.RELATIONSHIP_BUILDER_SID \n"
                    + "FROM            RELATIONSHIP_LEVEL_DEFINITION RLD JOIN\n"
                    + "                         PROJECTION_PROD_HIERARCHY  PCH ON \n"
                    + "                         RLD.RELATIONSHIP_LEVEL_SID = PCH.RELATIONSHIP_LEVEL_SID  JOIN\n"
                    + "                         PROJECTION_MASTER PM ON PCH.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID  JOIN\n"
                    + "                         PROJECTION_DETAILS PD ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID\n"
                    + "WHERE        PM.PROJECTION_MASTER_SID = "
                    +projectionId
                    + " and RLD.LEVEL_NO="
                    +levelNo
                    + " and RLD.PARENT_NODE='"
                    +parent
                    + "' group by RLD.RELATIONSHIP_LEVEL_VALUES,RLD.LEVEL_NO,\n"
                    + "RLD.PARENT_NODE,RLD.HIERARCHY_LEVEL_DEFINITION_SID,RLD.HIERARCHY_NO,RLD.RELATIONSHIP_BUILDER_SID \n" );
//            LOGGER.debug("sql of level values--->>>>" + sql);
            resultList = HelperTableLocalServiceUtil.executeSelectQuery(sql.toString()); 
//            LOGGER.debug("resultList.size--->>>" + resultList.size());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql.toString());
        }
        
        return resultList;
    }
     
}
