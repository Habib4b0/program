/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.service.finderimpl;

import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.serviceutils.Constants;
import static com.stpl.app.serviceutils.Constants.FrequencyConstants.ANNUAL;
import static com.stpl.app.serviceutils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.serviceutils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.serviceutils.Constants.FrequencyConstants.SEMI_ANNUAL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Abishek.Ram
 */
public class NmSalesProjectionImpl {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(NmSalesProjectionImpl.class);

    public List getSalesResult(Object[] inputs) {
        List list = new ArrayList();
        
        StringBuilder queryBuilder1 = new StringBuilder();
        try {

            String method = (String) inputs[8];
            LOGGER.debug("method = {} ",method);
            if (method.equals("fetchSalesResult")) {

                String projectionId = String.valueOf(inputs[0]);
                int levelNo = Integer.parseInt(String.valueOf(inputs[1]));
                String levelName = String.valueOf(inputs[2]);
                int startIndex = (Integer) inputs[5];
                int endIndex = (Integer) inputs[6];
                int parentLevel = 0;
                boolean count = (Boolean) inputs[7];

                String viewType = (String) inputs[9];
                boolean iscustom = (Boolean) inputs[11];

                String userid = (String) inputs[13];
                String sessionId = (String) inputs[14];
                String customId = (String) inputs[15];
                String hierarchyNo = (String) inputs[16];
                String parentViewType = (String) inputs[17];
                String userGroup=(String) inputs[18];
                
                String lastCustomerHierarchyno=(String)inputs[19];
                String lastProductHierarchyno=(String) inputs[20];
                String methodology=(String) inputs[21];
                String baseLine=(String) inputs[22];
                parentLevel = levelNo - 1;

                if (count) {
                    queryBuilder1.append("SELECT count(*) \n");
                } else {
                    queryBuilder1.append(" DECLARE @start INT= \n");
                    queryBuilder1.append(startIndex);
                    queryBuilder1.append(", @End   INT=\n");
                    queryBuilder1.append(endIndex);
                    
                    
                    queryBuilder1.append("   SELECT MAINQ.ACCOUNT_GROWTH,MAINQ.PRODUCT_GROWTH,MAINQ.PROJECTION_SALES,MAINQ.PROJECTION_UNITS, MAINQ.ACTUAL_SALES,MAINQ.ACTUAL_UNITS,   \n");
                    queryBuilder1.append("   MAINQ.level_no,MAINQ.rlv,MAINQ.YEARS,MAINQ.PERIODS,MAINQ.userGroup, MAINQ.baseLine,MAINQ.methodology,   \n"
                            + "MAINQ.RELATIONSHIP_LEVEL_SID,MAINQ.HIERARCHY_NO,");
                    queryBuilder1.append("   MAINQ.RCOUNT,MAINQ.actualProj,MAINQ.historySales,MAINQ.historyUnits,   \n");
                    queryBuilder1.append("   MAINQ.checkrec,MAINQ.hierarchyLecel,MAINQ.calcPeriodCount,   \n");
                    queryBuilder1.append("   MAINQ.methoCount,MAINQ.calcBasedcount,MAINQ.UNCHECK_COUNT   \n");
                    queryBuilder1.append("   FROM   (SELECT Q.ACCOUNT_GROWTH, Q.PRODUCT_GROWTH,"
                            + " Q.PROJECTION_SALES,Q.PROJECTION_UNITS,   \n");
                    queryBuilder1.append("   Q.ACTUAL_SALES, Q.ACTUAL_UNITS,"
                            + "Q.level_no, Q.rlv,"
                            + "Q.YEARS, Q.PERIODS,"
                            + "Q.userGroup,   \n");
                    queryBuilder1.append("   Q.baseLine,Q.methodology,"
                            + "Q.RELATIONSHIP_LEVEL_SID,Q.HIERARCHY_NO,"
                            + "Q.RCOUNT,Q.actualProj,"
                            + "Q.historySales,Q.historyUnits,   \n");
                    queryBuilder1.append("   Q.checkrec,Q.hierarchyLecel,"
                            + "Q.calcPeriodCount,Q.methoCount,   \n");
                    queryBuilder1.append("   Q.calcBasedcount, Q.UNCHECK_COUNT,Dense_rank()   \n");
                    queryBuilder1.append("    OVER (ORDER BY Q.rlv ASC) AS TEMP_INDEX FROM (   \n");
                    
                    

                    queryBuilder1.append("   SELECT \n\n");
                    queryBuilder1.append("   0.0 as ACCOUNT_GROWTH,0.0 as PRODUCT_GROWTH,   \n");
                    queryBuilder1.append("    sum(nm_ac.ACTUAL_SALES) as PROJECTION_SALES ,  "
                            + "sum(nm_ac.ACTUAL_UNITS) as PROJECTION_UNITS,0 as ACTUAL_SALES,"
                            + "0 as ACTUAL_UNITS, max(rld.LEVEL_NO) as level_no,   \n  \n");
                    queryBuilder1.append("   rld.RELATIONSHIP_LEVEL_VALUES as rlv,     p.\"YEAR\" as YEARS,  "
                            + "   p.QUARTER as PERIODS,     \n");
                    queryBuilder1.append("    min(nm_mas.USER_GROUP) as userGroup,"
                            + "min(nm_mas.CALCULATION_PERIODS) as baseLine,min(nm_mas.METHODOLOGY) as methodology,"
                            + "rld.RELATIONSHIP_LEVEL_SID,CCP.HIERARCHY_NO,"
                            + "COUNT (*)  AS RCOUNT,1 as actualProj,"
                            + "sum(nm_ac.HISTORY_PROJECTION_SALES) as historySales ,  sum(nm_ac.HISTORY_PROJECTION_UNITS) as historyUnits ,   \n  \n");
                    queryBuilder1.append("      min(CASE(CHECK_RECORD) WHEN 1 THEN 1 ELSE 0  END)  AS checkrec,rld.LEVEL_Name as hierarchyLecel,    ");
                      
                    queryBuilder1.append(Constant.COUNT_DISTINCT_CASE_WHEN_NM_MAS_CALCULATE);
                    queryBuilder1.append(Constant.COUNT_DISTINCT_CASE_WHEN_NM_MAS_METHODOLOGY);
                    queryBuilder1.append(" count(distinct case when  nm_mas.CALCULATION_BASED is null then '1' else  nm_mas.CALCULATION_BASED end)     AS calcBasedcount, SUM(CASE(nm_mas.CHECK_RECORD) WHEN 1 THEN 0 ELSE 1 END) AS UNCHECK_COUNT    ");
                }
                queryBuilder1.append("     FROM   projection_details pd     \n   \n");
                if (!iscustom) {

                    queryBuilder1.append("        JOIN (SELECT distinct LCCP.RELATIONSHIP_LEVEL_SID, LCCP.CCP_DETAILS_SID, LCCP.HIERARCHY_NO from     \n  \n");
                    queryBuilder1.append("   (SELECT CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID from     \n  \n");
                    queryBuilder1.append("   (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID    \n   \n");
                    queryBuilder1.append("   FROM RELATIONSHIP_LEVEL_DEFINITION RLD     \n  \n");
                    queryBuilder1.append("   JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID     \n \n");
                    queryBuilder1.append(Constant.JOIN_PROJECTION_DETAILS_PD_ON_PD_CCP ).append( projectionId ).append( "'    \n  \n");
                    queryBuilder1.append("   JOIN PROJECTION_MASTER PM  ON PD.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID     \n  \n");
                    queryBuilder1.append(Constant.WHERE_PM_PROJECTION_MASTER_SID ).append(projectionId).append( "') CCPMAP,     \n  \n");
                    queryBuilder1.append("   (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID    \n  \n");
                    queryBuilder1.append("   FROM RELATIONSHIP_LEVEL_DEFINITION RLD1      \n \n");

                    if (viewType.equals("C")) {

                        queryBuilder1.append("    JOIN PROJECTION_CUST_HIERARCHY PCH   \n \n");

                    } else {

                        queryBuilder1.append("    JOIN PROJECTION_PROD_HIERARCHY PCH    \n \n");

                    }
                    queryBuilder1.append("   ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID     \n  \n");
                    queryBuilder1.append(Constant.AND_P_CH_PROJECTION_MASTER_SID ).append(projectionId).append( "'      \n \n");
                    queryBuilder1.append(Constant.WHERE_RL_D1_HIERARCHY_NO_LIKE_HLD);
                    queryBuilder1.append("   WHERE CCPMAP.HIERARCHY_NO like HLD.HIERARCHY_NO + '%' ) LCCP    \n  \n");
                    queryBuilder1.append("   WHERE LCCP.HIERARCHY_NO in     \n  \n");
                    queryBuilder1.append("   (SELECT RLD2.HIERARCHY_NO      \n \n");
                    queryBuilder1.append("   FROM RELATIONSHIP_LEVEL_DEFINITION RLD2     \n \n");
                    if (viewType.equals("C")) {

                        queryBuilder1.append("    JOIN PROJECTION_CUST_HIERARCHY PCH2    \n \n");

                    } else {

                        queryBuilder1.append("    JOIN PROJECTION_PROD_HIERARCHY PCH2    \n \n");

                    }

                    queryBuilder1.append("   ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID      \n \n");
                    queryBuilder1.append(Constant.AND_PC_H2_PROJECTION_MASTER_SID ).append(projectionId).append( "'     \n  \n");
                    queryBuilder1.append("   WHERE RLD2.LEVEL_NO='" ).append(levelNo).append( "')) CCP    \n  \n");

                } else {

                    queryBuilder1.append("      JOIN    (SELECT distinct HLD" ).append(viewType).append( Constant.RELATIONSHIP_LEVEL_SID_HLD ).append(viewType).append( Constant.HIERARCHY_NO_CCP_MAP_CCCP_DETAILS_SID_FROM);

                    queryBuilder1.append(Constant.SELECT_RLD_RELATIONSHIP_LEVEL_VALUES_RLD);

                    queryBuilder1.append(Constant.JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL);

                    queryBuilder1.append(Constant.JOIN_PROJECTION_DETAILS_PD_ON_PD_CCP_DET ).append(projectionId).append( Constant.SPACE_NEW_LINE);

                    queryBuilder1.append("  ) CCPMAPC     \n");

                    queryBuilder1.append("  JOIN     \n");

                    queryBuilder1.append(Constant.SELECT_RLD_RELATIONSHIP_LEVEL_VALUES_RLD);

                    queryBuilder1.append(Constant.JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL);

                    queryBuilder1.append(Constant.JOIN_PROJECTION_DETAILS_PD_ON_PD_CCP_DET ).append(projectionId).append( Constant.SPACE_NEW_LINE);

                    queryBuilder1.append("  ) CCPMAPP ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID     \n");

                    String customerLevel = "%";
                    String productLevel = "%";
                    String custHier = "%";
                    String prodHier = "%";

                    if (viewType.equals("C")) {
                        customerLevel = Integer.toString(levelNo);
                    } else {
                        productLevel = Integer.toString(levelNo);

                    }
                    if (!hierarchyNo.equals(Constant.STRING_EMPTY)) {
                        if (parentViewType.equals("C")) {
                            custHier = hierarchyNo + "%";
                            prodHier=lastProductHierarchyno+ "%";
                        } else {
                            custHier=lastCustomerHierarchyno+ "%";
                            prodHier = hierarchyNo + "%";
                        }
                    }

                    queryBuilder1.append(" JOIN  (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD      \n");

                    queryBuilder1.append("  JOIN dbo.CUSTOM_VIEW_MASTER CVM ON CVD.CUSTOM_VIEW_MASTER_SID='" ).append(customId).append( Constant.COLON_AND_CVD_LEVEL_NO_LIKE ).append(customerLevel).append( Constant.SPACE_NEW_LINE);

                    queryBuilder1.append("  JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID     \n");

                    queryBuilder1.append("  JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID      \n");

                    queryBuilder1.append("  JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID='" ).append(projectionId).append( Constant.SPACE_NEW_LINE);

                    queryBuilder1.append(Constant.WHERE_RL_D2_HIERARCHY_NO_LIKE ).append(custHier).append( "') HLDC ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%'     \n");

                    queryBuilder1.append(" JOIN    (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD      \n");

                    queryBuilder1.append("  JOIN dbo.CUSTOM_VIEW_MASTER CVM ON   \n");

                    queryBuilder1.append("  CVD.CUSTOM_VIEW_MASTER_SID='" ).append(customId).append( Constant.COLON_AND_CVD_LEVEL_NO_LIKE ).append(productLevel).append( Constant.SPACE_NEW_LINE);

                    queryBuilder1.append("  JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID   \n");

                    queryBuilder1.append("  JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID   \n");

                    queryBuilder1.append("  JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID='" ).append(projectionId).append( Constant.SPACE_NEW_LINE);

                    queryBuilder1.append(Constant.WHERE_RL_D2_HIERARCHY_NO_LIKE ).append(prodHier).append( "' ) HLDP ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%'  \n");

                    queryBuilder1.append("  ) CCP   \n");

                }

                queryBuilder1.append("   ON CCP.CCP_DETAILS_SID=pd.CCP_DETAILS_SID    \n");
                queryBuilder1.append(Constant.JOIN_RELATIONSHIP_LEVEL_DEFINITION_RLD);
                queryBuilder1.append("   JOIN ST_NM_SALES_PROJECTION_MASTER nm_mas     \n \n");
                queryBuilder1.append("   ON pd.PROJECTION_DETAILS_SID = nm_mas.PROJECTION_DETAILS_SID       \n\n");
                queryBuilder1.append(Constant.JOIN_ST_NM_ACTUAL_SALES_NM_AC);
                queryBuilder1.append("   ON nm_mas.PROJECTION_DETAILS_SID = nm_ac.PROJECTION_DETAILS_SID     \n \n");
                queryBuilder1.append(Constant.JOIN_PERIOD_P);
                queryBuilder1.append(Constant.ON_PERIOD_SID_NM_AC_PERIOD_SID);

                queryBuilder1.append(Constant.WHERE_PD_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.SPACE_DOUBLE_NEW_LINE);
                if (!iscustom) {
                    queryBuilder1.append(Constant.AND_RLD_LEVEL_NO ).append(levelNo).append( '\'');
                }
                queryBuilder1.append(" AND nm_mas.USER_ID = '" ).append( userid ).append( Constant.AND_NM_MAS_SESSION_ID ).append( sessionId ).append( "' AND nm_ac.USER_ID  = '" ).append( userid ).append( Constant.AND_NM_AC_SESSION_ID ).append( sessionId ).append( Constant.SPACE_DOUBLE_NEW_LINE);
                if (!levelName.equalsIgnoreCase(Constant.STRING_EMPTY)) {
                    queryBuilder1.append(" and RLD.PARENT_NODE= '" ).append(parentLevel).append( '~' ).append( levelName ).append( Constant.SPACE_DOUBLE_NEW_LINE);
                }
                
                
                   if (!userGroup.equalsIgnoreCase(Constant.STRING_EMPTY)) {
                    queryBuilder1.append(Constant.AND_NM_MAS_USER_GROUP ).append(userGroup).append( Constant.SPACE_DOUBLE_NEW_LINE);
                }     
                    if (!baseLine.equalsIgnoreCase(Constant.STRING_EMPTY)) {
                    queryBuilder1.append(Constant.AND_NM_MAS_CALCULATION_PERIODS ).append(baseLine ).append( Constant.SPACE_DOUBLE_NEW_LINE);
                }     
                   
                     if (!methodology.equalsIgnoreCase(Constant.STRING_EMPTY)) {
                    queryBuilder1.append(Constant.AND_NM_MAS_METHODOLOGY ).append(methodology).append( Constant.SPACE_DOUBLE_NEW_LINE);
                }     
                   
                   
                if (!count) {
                    queryBuilder1.append(" GROUP  BY rld.RELATIONSHIP_LEVEL_VALUES,p.QUARTER, rld.RELATIONSHIP_LEVEL_SID ,rld.LEVEL_NO,p.\"YEAR\" ,rld.RELATIONSHIP_LEVEL_SID,CCP.HIERARCHY_NO,rld.LEVEL_NAME    \n");

                    queryBuilder1.append(Constant.UNION_NEW_LINE);

                    queryBuilder1.append(Constant.SELECT_NEW_LINE);
                    queryBuilder1.append(Constant.AVG_NM_SP_ACCOUNT_GROWTH);
                    queryBuilder1.append(Constant.AVG_NM_SP_PRODUCT_GROWTH_SUM_NM_SP_PROJEC);
                    queryBuilder1.append("  0 as ACTUAL_SALES,    0 as ACTUAL_UNITS, max(rld.LEVEL_NO) as level_no,   \n");
                    queryBuilder1.append("  rld.RELATIONSHIP_LEVEL_VALUES as rlv,    p.\"YEAR\"  as YEARS,     p.QUARTER as PERIODS,  \n");
                    queryBuilder1.append("    min(nm_mas.USER_GROUP) as userGroup,min(nm_mas.CALCULATION_PERIODS) as baseLine,min(nm_mas.METHODOLOGY) as methodology,rld.RELATIONSHIP_LEVEL_SID ,CCP.HIERARCHY_NO,COUNT (*)  AS RCOUNT ,0 as actualProj,null as historySales , null as historyUnits,     \n");
                 
                    queryBuilder1.append("    min(CASE(CHECK_RECORD) WHEN 1 THEN 1 ELSE 0  END)  AS checkrec,rld.LEVEL_Name as hierarchyLecel,     ");
                    
                  
                    queryBuilder1.append(Constant.COUNT_DISTINCT_CASE_WHEN_NM_MAS_CALCULATE);
                    queryBuilder1.append(Constant.COUNT_DISTINCT_CASE_WHEN_NM_MAS_METHODOLOGY);
                    queryBuilder1.append(" count(distinct case when  nm_mas.CALCULATION_BASED is null then '1' else  nm_mas.CALCULATION_BASED end)     AS calcBasedcount, SUM(CASE(nm_mas.CHECK_RECORD) WHEN 1 THEN 0 ELSE 1 END) AS UNCHECK_COUNT      "); 
                    
                    queryBuilder1.append("     FROM   projection_details pd   \n");

                    if (!iscustom) {

                        queryBuilder1.append(Constant.JOIN_SELECT_DISTINCT_L_CCPRELATION);
                        queryBuilder1.append(Constant.SELECT_CCP_MAP_CCP_DETAILS_SID_HLD_HIER);
                        queryBuilder1.append(Constant.SELECT_RLD_RELATIONSHIP_LEVEL_VALUES_RL);
                        queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD);
                        queryBuilder1.append(Constant.JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL_SID);
                        queryBuilder1.append(Constant.JOIN_PROJECTION_DETAILS_PD_ON_PD_CCP ).append( projectionId ).append( Constant.SPACE_NEW_LINE);


                        queryBuilder1.append(Constant.JOIN_PROJECTION_MASTER_PM_ON_PD_PROJECTION);
                        queryBuilder1.append(Constant.WHERE_PM_PROJECTION_MASTER_SID ).append( projectionId ).append( Constant.CCP_MAP_NEW_LINE);
                        queryBuilder1.append(Constant.SELECT_RL_D1_HIERARCHY_NO_RLD1_RELATIONSHIP);
                        queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD1);

                        if (viewType.equals("C")) {

                            queryBuilder1.append(Constant.JOIN_PROJECTION_CUST_HIERARCHY_PCH);

                        } else {

                            queryBuilder1.append(Constant.JOIN_PROJECTION_PROD_HIERARCHY_PCH);

                        }
                        queryBuilder1.append(Constant.ON_PCH_RELATIONSHIP_LEVEL_SID_RL_D1_REL);
                        queryBuilder1.append(Constant.AND_P_CH_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                        queryBuilder1.append(Constant.WHERE_RL_D1_HIERARCHY_NO_LIKE_HLD);
                        queryBuilder1.append(Constant.WHERE_CCP_MAP_HIERARCHY_NO_LIKE_HLD_HIER);
                        queryBuilder1.append(Constant.WHERE_L_CCP_HIERARCHY_NO_IN);
                        queryBuilder1.append(Constant.SELECT_RL_D2_HIERARCHY_NO);
                        queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD2);
                        if (viewType.equals("C")) {

                            queryBuilder1.append(Constant.JOIN_PROJECTION_CUST_HIERARCHY_PCH2);

                        } else {

                            queryBuilder1.append(Constant.JOIN_PROJECTION_PROD_HIERARCHY_PCH2);

                        }

                        queryBuilder1.append(Constant.ON_PCH2_RELATIONSHIP_LEVEL_SID_RLD2);
                        queryBuilder1.append(Constant.AND_PC_H2_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                        queryBuilder1.append("   WHERE RLD2.LEVEL_NO='" ).append(levelNo).append( "')) CCP      \n");

                    } else {

                        String customerLevel = "%";
                        String productLevel = "%";
                        String custHier = "%";
                        String prodHier = "%";

                        if (viewType.equals("C")) {
                            customerLevel = Integer.toString(levelNo);
                        } else {
                            productLevel = Integer.toString(levelNo);

                        }
                         if (!hierarchyNo.equals(Constant.STRING_EMPTY)) {
                        if (parentViewType.equals("C")) {
                            custHier = hierarchyNo + "%";
                            prodHier=lastProductHierarchyno+ "%";
                        } else {
                            custHier=lastCustomerHierarchyno+ "%";
                            prodHier = hierarchyNo + "%";
                        }
                    }

                        queryBuilder1.append("      JOIN    (SELECT distinct HLD").append(viewType).append(Constant.RELATIONSHIP_LEVEL_SID_HLD).append(viewType).append(Constant.HIERARCHY_NO_CCP_MAP_CCCP_DETAILS_SID_FROM);

                        queryBuilder1.append(Constant.SELECT_RLD_RELATIONSHIP_LEVEL_VALUES_RLD);

                        queryBuilder1.append(Constant.JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL);

                        queryBuilder1.append(Constant.JOIN_PROJECTION_DETAILS_PD_ON_PD_CCP_DET ).append(projectionId).append( Constant.SPACE_NEW_LINE);

                        queryBuilder1.append("  ) CCPMAPC     \n");

                        queryBuilder1.append("  JOIN     \n");

                        queryBuilder1.append(Constant.SELECT_RLD_RELATIONSHIP_LEVEL_VALUES_RLD);

                        queryBuilder1.append(Constant.JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL);

                        queryBuilder1.append(Constant.JOIN_PROJECTION_DETAILS_PD_ON_PD_CCP_DET ).append(projectionId).append( Constant.SPACE_NEW_LINE);

                        queryBuilder1.append("  ) CCPMAPP ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID     \n");

                        queryBuilder1.append(" JOIN  (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD      \n");

                        queryBuilder1.append("  JOIN dbo.CUSTOM_VIEW_MASTER CVM ON CVD.CUSTOM_VIEW_MASTER_SID='" ).append(customId).append( Constant.COLON_AND_CVD_LEVEL_NO_LIKE ).append(customerLevel).append( Constant.SPACE_NEW_LINE);

                        queryBuilder1.append("  JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID     \n");

                        queryBuilder1.append("  JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID      \n");

                        queryBuilder1.append("  JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID='" ).append(projectionId).append( Constant.SPACE_NEW_LINE);

                        queryBuilder1.append(Constant.WHERE_RL_D2_HIERARCHY_NO_LIKE ).append(custHier).append( "') HLDC ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%'     \n");

                        queryBuilder1.append(" JOIN    (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD      \n");

                        queryBuilder1.append("  JOIN dbo.CUSTOM_VIEW_MASTER CVM ON   \n");

                        queryBuilder1.append("  CVD.CUSTOM_VIEW_MASTER_SID='" ).append(customId).append( Constant.COLON_AND_CVD_LEVEL_NO_LIKE ).append(productLevel).append( Constant.SPACE_NEW_LINE);

                        queryBuilder1.append("  JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID   \n");

                        queryBuilder1.append("  JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID   \n");

                        queryBuilder1.append("  JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID='" ).append(projectionId).append( Constant.SPACE_NEW_LINE);

                        queryBuilder1.append(Constant.WHERE_RL_D2_HIERARCHY_NO_LIKE ).append(prodHier).append( "' ) HLDP ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%'  \n");

                        queryBuilder1.append("  ) CCP   \n");

                    }

                    queryBuilder1.append("   ON CCP.CCP_DETAILS_SID=pd.CCP_DETAILS_SID    \n");

                    queryBuilder1.append(Constant.JOIN_RELATIONSHIP_LEVEL_DEFINITION_RLD);
                    queryBuilder1.append(Constant.JOIN_ST_NM_SALES_PROJECTION_MASTER);
                    queryBuilder1.append(Constant.ON_PD_PROJECTION_DETAILS_SID_NM_MAS_PROJ);
                    queryBuilder1.append(Constant.JOIN_ST_NM_SALES_PROJECTION_NM_SP);
                    queryBuilder1.append("  ON nm_mas.PROJECTION_DETAILS_SID = nm_sp.PROJECTION_DETAILS_SID    \n");
                    queryBuilder1.append(Constant.JOIN_PERIOD_P_ON_PERIOD_SID_NM_SP_PERIOD ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                    if (!iscustom) {
                        queryBuilder1.append(Constant.AND_RLD_LEVEL_NO ).append(levelNo).append( '\'');
                    }
                    queryBuilder1.append("    AND nm_mas.USER_ID = '" ).append( userid ).append( Constant.AND_NM_MAS_SESSION_ID ).append( sessionId ).append( Constant.AND_NM_SP_USER_ID ).append( userid ).append( Constant.AND_NM_SP_SESSION_ID ).append( sessionId ).append( Constant.SPACE_NEW_LINE);
                    if (!levelName.equalsIgnoreCase(Constant.STRING_EMPTY)) {
                        queryBuilder1.append("    and RLD.PARENT_NODE= '" ).append( (parentLevel) ).append( '~' ).append( levelName ).append( Constant.SPACE_NEW_LINE);
                    }
                    
                       if (!userGroup.equalsIgnoreCase(Constant.STRING_EMPTY)) {
                    queryBuilder1.append(Constant.AND_NM_MAS_USER_GROUP ).append(userGroup).append( Constant.SPACE_DOUBLE_NEW_LINE);
                }     
                    if (!baseLine.equalsIgnoreCase(Constant.STRING_EMPTY)) {
                    queryBuilder1.append(Constant.AND_NM_MAS_CALCULATION_PERIODS ).append(baseLine).append( Constant.SPACE_DOUBLE_NEW_LINE);
                }     
                   
                     if (!methodology.equalsIgnoreCase(Constant.STRING_EMPTY)) {
                    queryBuilder1.append(Constant.AND_NM_MAS_METHODOLOGY ).append(methodology).append( Constant.SPACE_DOUBLE_NEW_LINE);
                }     
                     
                    
                    queryBuilder1.append("  GROUP  BY rld.RELATIONSHIP_LEVEL_VALUES,p.QUARTER ,rld.LEVEL_NO,p.\"YEAR\"  ,rld.RELATIONSHIP_LEVEL_SID,CCP.HIERARCHY_NO ,rld.LEVEL_NAME \n");


                    queryBuilder1.append("   ) Q) MAINQ WHERE  MAINQ.TEMP_INDEX > @Start AND MAINQ.TEMP_INDEX <= ( @Start + @End )  \n");
                    queryBuilder1.append("   ORDER  BY MAINQ.rlv,MAINQ.YEARS,MAINQ.PERIODS   \n");

                }
            }else if (method.equals("massUpdate")) {
                int startYear = (Integer) inputs[1];
                int endYear = (Integer) inputs[2];
                int startQuator = (Integer) inputs[3];
                int endQuartor = (Integer) inputs[4];
                Double value = (Double) inputs[5];

                String growth = (String) inputs[6];

                String userid = (String) inputs[9];

                String sessionId = (String) inputs[10];

                queryBuilder1.append("   UPDATE ST_NM_SALES_PROJECTION  SET   \n");

                if (growth.equals("proGrowth")) {

                    queryBuilder1.append(" PRODUCT_GROWTH='" ).append(value).append( Constant.SPACE_NEW_LINE);

                } else if (growth.equals("accGrowth")) {

                    queryBuilder1.append(" ACCOUNT_GROWTH='" ).append(value).append( Constant.SPACE_NEW_LINE);

                }
                queryBuilder1.append("  where PROJECTION_DETAILS_SID   in ( \n");

                queryBuilder1.append("   SELECT  \n");

                queryBuilder1.append("   SPM.PROJECTION_DETAILS_SID  \n");

                queryBuilder1.append("   FROM ST_NM_SALES_PROJECTION_MASTER SPM \n");

                queryBuilder1.append("   WHERE  SPM.CHECK_RECORD= 1 AND SPM.USER_ID = '" ).append( userid ).append( "' AND SPM.SESSION_ID = '" ).append( sessionId ).append( "' ) AND  \n");
             
                queryBuilder1.append("    PERIOD_SID in (SELECT PERIOD_SID FROM \"PERIOD\"  where PERIOD_SID in  \n");

                queryBuilder1.append("   (SELECT PERIOD_SID FROM \"PERIOD\" where \"YEAR\" >= " ).append(startYear).append( " and \"YEAR\" <= " ).append(endYear ).append( "   \n");

                queryBuilder1.append("   And PERIOD_SID not in(SELECT PERIOD_SID FROM \"PERIOD\" where \"YEAR\" = " ).append(startYear).append( " and QUARTER <" ).append(startQuator).append( ")  \n");

                queryBuilder1.append("   and PERIOD_SID not in(SELECT PERIOD_SID FROM \"PERIOD\" where \"YEAR\" = " ).append(endYear).append( " and QUARTER > " ).append(endQuartor).append( " )))  AND USER_ID = '" ).append( userid ).append( Constant.COLON_AND_SESSION_ID ).append( sessionId ).append( Constant.SPACE_NEW_LINE);

            
            } else if (method.equals("saveCheckRecords")) {

                String projectionId = String.valueOf(inputs[0]);

                String viewType = (String) inputs[2];
                String hierarchyNos = (String) inputs[3];
                String userid = (String) inputs[4];
                String sessionId = (String) inputs[5];
                boolean checkAll = (Boolean) inputs[6];
                String checkUncheck = (String) inputs[7];
                if (hierarchyNos.equals(Constant.WHERE)) {
                    hierarchyNos = " ";
                }
                
                

                queryBuilder1.append("        UPDATE ST_NM_SALES_PROJECTION_MASTER  SET  ");
                if(checkUncheck.equals(Constant.CHECK)){
                queryBuilder1.append(Constant.CHECK_RECORD_1);
                }else{
                   queryBuilder1.append(Constant.CHECK_RECORD_0);   
                 }
                if(checkUncheck.equals(Constant.CHECK)){
                queryBuilder1.append(Constant.WHERE_PROJECTION_DETAILS_SID_IN);
                }else{
                queryBuilder1.append(" where PROJECTION_DETAILS_SID  NOT IN (  \n");
                 }
               
                
                queryBuilder1.append(Constant.SELECT_PROJECTION_DETAILS_SID_FROM_PROJ_DETAILS);

                queryBuilder1.append(Constant.SELECT_LCCP_DETAILS_SID_FROM);
                queryBuilder1.append(Constant.SELECT_CCP_MAP_CCP_DETAILS_SID_HLD_HIER);
                queryBuilder1.append(Constant.SELECT_RLD_RELATIONSHIP_LEVEL_VALUES_RL);
                queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD);
                queryBuilder1.append(Constant.JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL_SID);
                queryBuilder1.append(Constant.JOIN_PROJECTION_DETAILS_PD_ON_PD_CCP ).append(projectionId).append( Constant.SPACE_NEW_LINE);


                queryBuilder1.append(Constant.JOIN_PROJECTION_MASTER_PM_ON_PD_PROJECTION);
                queryBuilder1.append(Constant.WHERE_PM_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.CCP_MAP_NEW_LINE);
                queryBuilder1.append(Constant.SELECT_RL_D1_HIERARCHY_NO_RLD1_RELATIONSHIP);
                queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD1);

                if (viewType.equals("C")) {

                    queryBuilder1.append(Constant.JOIN_PROJECTION_CUST_HIERARCHY_PCH);

                } else {

                    queryBuilder1.append(Constant.JOIN_PROJECTION_PROD_HIERARCHY_PCH);

                }
                queryBuilder1.append(Constant.ON_PCH_RELATIONSHIP_LEVEL_SID_RL_D1_REL);
                queryBuilder1.append(Constant.AND_P_CH_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                if (!checkAll) {
                    queryBuilder1.append( ' ' ).append( hierarchyNos ).append( Constant.NEW_LINE);
                }
                queryBuilder1.append(Constant.HLD_WHERE_CCP_MAP_HIERARCHY_NO_LIKE_HLD);
                queryBuilder1.append(Constant.WHERE_L_CCP_HIERARCHY_NO_IN);
                queryBuilder1.append(Constant.SELECT_RL_D2_HIERARCHY_NO);
                queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD2);
                if (viewType.equals("C")) {

                    queryBuilder1.append(Constant.JOIN_PROJECTION_CUST_HIERARCHY_PCH2);

                } else {

                    queryBuilder1.append(Constant.JOIN_PROJECTION_PROD_HIERARCHY_PCH2);

                }

                queryBuilder1.append(Constant.ON_PCH2_RELATIONSHIP_LEVEL_SID_RLD2);
                queryBuilder1.append(Constant.AND_PC_H2_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(" ) ) )  AND USER_ID = '" ).append( userid ).append( Constant.COLON_AND_SESSION_ID ).append( sessionId ).append( Constant.SPACE_NEW_LINE);

            } else if (method.equals("saveBfrCalculation")) {
                 boolean custom=(Boolean)inputs[7];
if(!custom){
                String projectionId = String.valueOf(inputs[0]);
                String methodology = (String) inputs[1];
                String calcPeriods = (String) inputs[2];
                String calcBased = (String) inputs[3];
                String viewType = (String) inputs[4];

                String hierarchyNos = (String) inputs[6];
                String userid = (String) inputs[9];
                String sessionId = (String) inputs[10];
               
                queryBuilder1.append("   UPDATE ST_NM_SALES_PROJECTION_MASTER  SET METHODOLOGY='" ).append( methodology ).append( "' ,CALCULATION_PERIODS='" ).append( calcPeriods ).append( "',CALCULATION_BASED='" ).append( calcBased ).append( "'  WHERE  PROJECTION_DETAILS_SID \n");
                queryBuilder1.append(Constant.IN_NEW_LINE);
                queryBuilder1.append(Constant.SELECT_PROJECTION_DETAILS_SID_FROM_PROJ_DETAILS);
                 queryBuilder1.append(Constant.SELECT_LCCP_DETAILS_SID_FROM);
                queryBuilder1.append(Constant.SELECT_CCP_MAP_CCP_DETAILS_SID_HLD_HIER);
                queryBuilder1.append(Constant.SELECT_RLD_RELATIONSHIP_LEVEL_VALUES_RL);
                queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD);
                queryBuilder1.append(Constant.JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL_SID);
                queryBuilder1.append(Constant.JOIN_PROJECTION_DETAILS_PD_ON_PD_CCP ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.JOIN_PROJECTION_MASTER_PM_ON_PD_PROJECTION);
                queryBuilder1.append(Constant.WHERE_PM_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.CCP_MAP_NEW_LINE);
                queryBuilder1.append(Constant.SELECT_RL_D1_HIERARCHY_NO_RLD1_RELATIONSHIP);
                queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD1);
                if (viewType.equals("C")) {
                 queryBuilder1.append(Constant.JOIN_PROJECTION_CUST_HIERARCHY_PCH);
                } else {
                queryBuilder1.append(Constant.JOIN_PROJECTION_PROD_HIERARCHY_PCH);
                  }
                queryBuilder1.append(Constant.ON_PCH_RELATIONSHIP_LEVEL_SID_RL_D1_REL);
                queryBuilder1.append(Constant.AND_P_CH_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.BLANK_QUOTES ).append( hierarchyNos ).append( Constant.HLD_NEW_LINE);
                queryBuilder1.append(Constant.WHERE_CCP_MAP_HIERARCHY_NO_LIKE_HLD_HIER);
                queryBuilder1.append(Constant.WHERE_L_CCP_HIERARCHY_NO_IN);
                queryBuilder1.append(Constant.SELECT_RL_D2_HIERARCHY_NO);
                queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD2);
                if (viewType.equals("C")) {
                 queryBuilder1.append(Constant.JOIN_PROJECTION_CUST_HIERARCHY_PCH2);
                } else {
                queryBuilder1.append(Constant.JOIN_PROJECTION_PROD_HIERARCHY_PCH2);
                 }
                queryBuilder1.append(Constant.ON_PCH2_RELATIONSHIP_LEVEL_SID_RLD2);
                queryBuilder1.append(Constant.AND_PC_H2_PROJECTION_MASTER_SID ).append( (projectionId) ).append( Constant.SPACE_NEW_LINE);
                 queryBuilder1.append("   ) ) ) AND USER_ID = '" ).append( userid ).append( Constant.COLON_AND_SESSION_ID ).append( sessionId ).append( "' AND  CHECK_RECORD=1 \n");
}else{

                        
                String projectionId = String.valueOf(inputs[0]);
                int levelNo = Integer.parseInt(String.valueOf(inputs[1]));

                String userid = (String) inputs[4];
                String sessionId = (String) inputs[5];
                String customId = (String) inputs[9];
                String hierarchyNo = (String) inputs[3];
                
                String parentViewType = (String) inputs[10];
                String lastCustomerHierarchyno=(String)inputs[11];
                String lastProductHierarchyno=(String) inputs[12];
                 String methodology = (String) inputs[14];
                 String calcPeriods = (String) inputs[15];
                 String calcBased = (String) inputs[16];
                String customerLevel = "%";
                String productLevel = "%";
                String custHier = "%";
                String prodHier = "%";

              if (parentViewType.equals("C")) {
                    customerLevel = Integer.toString(levelNo);
                } else {
                    productLevel = Integer.toString(levelNo);
                }
              
              if(!lastCustomerHierarchyno.equals("%")){
              
               custHier = lastCustomerHierarchyno + "%";
              }else{
              
               prodHier = lastProductHierarchyno + "%";
              }
              
              
                queryBuilder1.append("   UPDATE ST_NM_SALES_PROJECTION_MASTER  SET METHODOLOGY='" ).append( methodology ).append( "' ,CALCULATION_PERIODS='" ).append( calcPeriods ).append( "',CALCULATION_BASED='" ).append( calcBased ).append( "'   WHERE  PROJECTION_DETAILS_SID \n");
                queryBuilder1.append(Constant.IN_NEW_LINE);
                queryBuilder1.append(Constant.SELECT_DISTINCT_PD_PROJECTION_DETAILS);
                queryBuilder1.append(Constant.FROM_PROJECTION_DETAILS_PD);
                queryBuilder1.append(Constant.JOIN_SELECT_DISTINCT_HLD ).append(parentViewType).append( Constant.RELATIONSHIP_LEVEL_SID_HLD ).append(parentViewType).append( Constant.HIERARCHY_NO_CCP_MAP_CCCP_DETAILS_SID_FROM);
                queryBuilder1.append(Constant.SELECT_RLD_RELATIONSHIP_LEVEL_VALUES);
                queryBuilder1.append(Constant.JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL);
                queryBuilder1.append(Constant.JOIN_PROJECTION_DETAILS_PD_ON_PD_CCP ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.CCP_MAP);
                queryBuilder1.append(" JOIN \n");
                queryBuilder1.append(Constant.SELECT_RLD_RELATIONSHIP_LEVEL_VALUES);
                queryBuilder1.append(Constant.JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL);
                queryBuilder1.append(Constant.JOIN_PROJECTION_DETAILS_PD_ON_PD_CCP ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.CCP_MAP_ON_CCP_MAP_CCP_DETAILS_SID);
                queryBuilder1.append(Constant.JOIN_SELECT_RL_D2_HIERARCHY_NO_RLD);
                queryBuilder1.append(Constant.JOIN_DBO_CUSTOM_VIEW_MASTER_CVM_ON_CVDC ).append(customId).append( Constant.COLON_AND_CVD_LEVEL_NO_LIKE ).append(customerLevel).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.JOIN_DBO_HIERARCHY_LEVEL_DEFINITION_HLD);
                queryBuilder1.append(Constant.JOIN_RELATIONSHIP_LEVEL_DEFINITION_RLD2);
                queryBuilder1.append(Constant.JOIN_PROJECTION_CUST_HIERARCHY_PC_H2 ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.WHERE_RL_D2_HIERARCHY_NO_LIKE ).append(custHier).append( Constant.HLDC_ON_CCP_MAP_HIERARCHY_NO_LIKE_HLD);
                queryBuilder1.append(Constant.JOIN_SELECT_RL_D2_HIERARCHY_NO_RLD_REL_LEVEL);
                queryBuilder1.append(Constant.JOIN_DBO_CUSTOM_VIEW_MASTER_CVM_ON);
                queryBuilder1.append(Constant.CVD_CUSTOM_VIEW_MASTER_SID ).append(customId).append( Constant.COLON_AND_CVD_LEVEL_NO_LIKE ).append(productLevel).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.JOIN_DBO_HIERARCHY_LEVEL_DEFINITION_HLD);
                queryBuilder1.append(Constant.JOIN_RELATIONSHIP_LEVEL_DEFINITION_RLD2);
                queryBuilder1.append(Constant.JOIN_PROJECTION_PROD_HIERARCHY_PC_H2 ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.WHERE_RLD2_HIERARCHY_NO_LIKE ).append(prodHier).append( Constant.HLDP_ON_CCP_MAP_HIERARCHY_NO_LIKE_HLD);
                queryBuilder1.append(Constant.CCP_NEW_LINE);
                queryBuilder1.append(Constant.ON_CCP_DETAILS_SI_DPD_CCP_DETAILS_SID);
                queryBuilder1.append(Constant.JOIN_RELATIONSHIP_LEVEL_DEFINITION_RLD_JOIN_NM_SALES_PROJ);
                queryBuilder1.append(Constant.ON_PD_PROJECTION_DETAILS_SID_NM_MAS_PROJ_DETAILS_SID);
                queryBuilder1.append(Constant.JOIN_ST_NM_SALES_PROJECTION_NM_SP);
                queryBuilder1.append(Constant.ON_NM_MAS_PROJECTION_DETAILS_SID_NM_SP);
                queryBuilder1.append(Constant.JOIN_PERIOD_P_ON_PPERIOD_SID_NM_SP_PE ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.COLON_AND_NM_MAS_USER_ID ).append( userid ).append( Constant.AND_NM_MAS_SESSION_ID ).append( sessionId ).append( Constant.AND_NM_SP_USER_ID ).append( userid ).append( Constant.AND_NM_SP_SESSION_ID ).append( sessionId ).append( Constant.AND_RLD_HIERARCHY_NO ).append( hierarchyNo ).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.CLOSE_BRACE_AND_USER_ID ).append( userid ).append( Constant.COLON_AND_SESSION_ID ).append( sessionId ).append( "' AND  CHECK_RECORD=1   \n");

}
                
            } else if (method.equals("saveBfrAllocation")) {
                
                boolean iscustom=(Boolean)inputs[12];
            if(!iscustom){
                String projectionId = String.valueOf(inputs[0]);

                String adjType = (String) inputs[1];
                String adjVal = (String) inputs[2];
                String adjBasis = (String) inputs[3];
                String adsVar = (String) inputs[4];
                String adsMeth = (String) inputs[5];
                String hierarchyNos = (String) inputs[7];
                String viewType = (String) inputs[9];
                String userid = (String) inputs[10];
                String sessionId = (String) inputs[11];
                queryBuilder1.append("  UPDATE ST_NM_SALES_PROJECTION  SET ADJUSTMENT_TYPE='" ).append( adjType ).append( "',ADJUSTMENT_VALUES='" ).append( adjVal ).append( "', \n");

                queryBuilder1.append("  ADJUSTMENT_BASIS='" ).append( adjBasis ).append( "',ADJUSTMENT_VARIABLE='" ).append( adsVar ).append( "',ADJUSTMENT_METHODOLOGY='" ).append( adsMeth ).append( Constant.SPACE_NEW_LINE);

                queryBuilder1.append("  where PROJECTION_DETAILS_SID  \n");

                queryBuilder1.append("  in ( \n");

                queryBuilder1.append(Constant.SELECT_PROJECTION_DETAILS_SID_FROM_PROJ_DETAILS);

                queryBuilder1.append(Constant.SELECT_LCCP_DETAILS_SID_FROM);
                queryBuilder1.append(Constant.SELECT_CCP_MAP_CCP_DETAILS_SID_HLD_HIER);
                queryBuilder1.append(Constant.SELECT_RLD_RELATIONSHIP_LEVEL_VALUES_RL);
                queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD);
                queryBuilder1.append(Constant.JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL_SID);
                queryBuilder1.append(Constant.JOIN_PROJECTION_DETAILS_PD_ON_PD_CCP ).append(projectionId).append( Constant.SPACE_NEW_LINE);

                queryBuilder1.append(Constant.JOIN_PROJECTION_MASTER_PM_ON_PD_PROJECTION);
                queryBuilder1.append(Constant.WHERE_PM_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.CCP_MAP_NEW_LINE);
                queryBuilder1.append(Constant.SELECT_RL_D1_HIERARCHY_NO_RLD1_RELATIONSHIP);
                queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD1);

                if (viewType.equals("C")) {

                    queryBuilder1.append(Constant.JOIN_PROJECTION_CUST_HIERARCHY_PCH);

                } else {

                    queryBuilder1.append(Constant.JOIN_PROJECTION_PROD_HIERARCHY_PCH);

                }
                queryBuilder1.append(Constant.ON_PCH_RELATIONSHIP_LEVEL_SID_RL_D1_REL);
                queryBuilder1.append(Constant.AND_P_CH_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(' ' ).append( hierarchyNos ).append( Constant.HLD_NEW_LINE);
                queryBuilder1.append(Constant.WHERE_CCP_MAP_HIERARCHY_NO_LIKE_HLD_HIER);
                queryBuilder1.append(Constant.WHERE_L_CCP_HIERARCHY_NO_IN);
                queryBuilder1.append(Constant.SELECT_RL_D2_HIERARCHY_NO);
                queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD2);
                if (viewType.equals("C")) {

                    queryBuilder1.append(Constant.JOIN_PROJECTION_CUST_HIERARCHY_PCH2);

                } else {

                    queryBuilder1.append(Constant.JOIN_PROJECTION_PROD_HIERARCHY_PCH2);

                }

                queryBuilder1.append(Constant.ON_PCH2_RELATIONSHIP_LEVEL_SID_RLD2);
                queryBuilder1.append(Constant.AND_PC_H2_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.SPACE_NEW_LINE);

                queryBuilder1.append("  )) ) AND USER_ID = '" ).append( userid ).append( Constant.COLON_AND_SESSION_ID ).append( sessionId ).append( Constant.SPACE_NEW_LINE);
            }else{
                  
                String projectionId = String.valueOf(inputs[0]);
                int levelNo = Integer.parseInt(String.valueOf(inputs[1]));
                String userid = (String) inputs[4];
                String sessionId = (String) inputs[5];
                String customId = (String) inputs[9];
                String hierarchyNo = (String) inputs[3];
                
                String parentViewType = (String) inputs[10];
                String lastCustomerHierarchyno=(String)inputs[11];
                String lastProductHierarchyno=(String) inputs[13];
                String adjType = (String) inputs[14];
                String adjVal = (String) inputs[15];
                String adjBasis = (String) inputs[16];
                String adsVar = (String) inputs[17];
                String adsMeth = (String) inputs[18];
                String customerLevel = "%";
                String productLevel = "%";
                String custHier = "%";
                String prodHier = "%";

              if (parentViewType.equals("C")) {
                    customerLevel = Integer.toString(levelNo);
                } else {
                    productLevel = Integer.toString(levelNo);
                }
              
              if(!lastCustomerHierarchyno.equals("%")){
              
               custHier = lastCustomerHierarchyno + "%";
              }else{
              
               prodHier = lastProductHierarchyno + "%";
              }
              
              
                queryBuilder1.append("  UPDATE ST_NM_SALES_PROJECTION  SET ADJUSTMENT_TYPE='" ).append( adjType ).append( "',ADJUSTMENT_VALUES='" ).append( adjVal ).append( "', \n");

                queryBuilder1.append("  ADJUSTMENT_BASIS='" ).append( adjBasis ).append( "',ADJUSTMENT_VARIABLE='" ).append( adsVar ).append( "',ADJUSTMENT_METHODOLOGY='" ).append( adsMeth ).append( Constant.SPACE_NEW_LINE);

                queryBuilder1.append("  where PROJECTION_DETAILS_SID  \n");
                queryBuilder1.append(Constant.IN_NEW_LINE);
                queryBuilder1.append(Constant.SELECT_DISTINCT_PD_PROJECTION_DETAILS);
                queryBuilder1.append(Constant.FROM_PROJECTION_DETAILS_PD);
                queryBuilder1.append(Constant.JOIN_SELECT_DISTINCT_HLD ).append(parentViewType).append( Constant.RELATIONSHIP_LEVEL_SID_HLD ).append(parentViewType).append( Constant.HIERARCHY_NO_CCP_MAP_CCCP_DETAILS_SID_FROM);
                queryBuilder1.append(Constant.SELECT_RLD_RELATIONSHIP_LEVEL_VALUES);
                queryBuilder1.append(Constant.JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL);
                queryBuilder1.append(Constant.JOIN_PROJECTION_DETAILS_PD_ON_PD_CCP ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.CCP_MAP);
                queryBuilder1.append(" JOIN \n");
                queryBuilder1.append(Constant.SELECT_RLD_RELATIONSHIP_LEVEL_VALUES);
                queryBuilder1.append(Constant.JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL);
                queryBuilder1.append(Constant.JOIN_PROJECTION_DETAILS_PD_ON_PD_CCP ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.CCP_MAP_ON_CCP_MAP_CCP_DETAILS_SID);
                queryBuilder1.append(Constant.JOIN_SELECT_RL_D2_HIERARCHY_NO_RLD);
                queryBuilder1.append(Constant.JOIN_DBO_CUSTOM_VIEW_MASTER_CVM_ON_CVDC ).append(customId).append( Constant.COLON_AND_CVD_LEVEL_NO_LIKE ).append(customerLevel).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.JOIN_DBO_HIERARCHY_LEVEL_DEFINITION_HLD);
                queryBuilder1.append(Constant.JOIN_RELATIONSHIP_LEVEL_DEFINITION_RLD2);
                queryBuilder1.append(Constant.JOIN_PROJECTION_CUST_HIERARCHY_PC_H2 ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.WHERE_RL_D2_HIERARCHY_NO_LIKE ).append(custHier).append( Constant.HLDC_ON_CCP_MAP_HIERARCHY_NO_LIKE_HLD);
                queryBuilder1.append(Constant.JOIN_SELECT_RL_D2_HIERARCHY_NO_RLD_REL_LEVEL);
                queryBuilder1.append(Constant.JOIN_DBO_CUSTOM_VIEW_MASTER_CVM_ON);
                queryBuilder1.append(Constant.CVD_CUSTOM_VIEW_MASTER_SID ).append(customId).append( Constant.COLON_AND_CVD_LEVEL_NO_LIKE ).append(productLevel).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.JOIN_DBO_HIERARCHY_LEVEL_DEFINITION_HLD);
                queryBuilder1.append(Constant.JOIN_RELATIONSHIP_LEVEL_DEFINITION_RLD2);
                queryBuilder1.append(Constant.JOIN_PROJECTION_PROD_HIERARCHY_PC_H2 ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.WHERE_RLD2_HIERARCHY_NO_LIKE ).append(prodHier).append( Constant.HLDP_ON_CCP_MAP_HIERARCHY_NO_LIKE_HLD);
                queryBuilder1.append(Constant.CCP_NEW_LINE);
                queryBuilder1.append(Constant.ON_CCP_DETAILS_SI_DPD_CCP_DETAILS_SID);
                queryBuilder1.append(Constant.JOIN_RELATIONSHIP_LEVEL_DEFINITION_RLD_JOIN_NM_SALES_PROJ);
                queryBuilder1.append(Constant.ON_PD_PROJECTION_DETAILS_SID_NM_MAS_PROJ_DETAILS_SID);
                queryBuilder1.append(Constant.JOIN_ST_NM_SALES_PROJECTION_NM_SP);
                queryBuilder1.append(Constant.ON_NM_MAS_PROJECTION_DETAILS_SID_NM_SP);
                queryBuilder1.append(Constant.JOIN_PERIOD_P_ON_PPERIOD_SID_NM_SP_PE ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.COLON_AND_NM_MAS_USER_ID ).append( userid ).append( Constant.AND_NM_MAS_SESSION_ID ).append( sessionId ).append( Constant.AND_NM_SP_USER_ID ).append( userid ).append( Constant.AND_NM_SP_SESSION_ID ).append( sessionId ).append( Constant.AND_RLD_HIERARCHY_NO).append(hierarchyNo).append(Constant.SPACE_NEW_LINE);

               queryBuilder1.append(Constant.CLOSE_BRACE_AND_USER_ID ).append( userid ).append( Constant.COLON_AND_SESSION_ID ).append( sessionId ).append( Constant.SPACE_NEW_LINE);

}
            } else if (method.equals("getProjDetailSid")) {

                String projectionId = String.valueOf(inputs[0]);

                queryBuilder1.append("  SELECT PROJECTION_DETAILS_SID FROM  PROJECTION_DETAILS WHERE PROJECTION_MASTER_SID='" ).append(projectionId).append( Constant.SPACE_NEW_LINE);

            } else if (method.equals("getLevelFilterValues")) {

                String projectionId = String.valueOf(inputs[0]);
                String viewType = (String) inputs[1];
                String relationshipBuilderSid=(String) inputs[9];
                String hierarchy="";

              

                if (viewType.equals("C")) {

                    
                    hierarchy="  PROJECTION_CUST_HIERARCHY ";
                    

                } else if (viewType.equals("P")) {

                  hierarchy="  PROJECTION_PROD_HIERARCHY ";
                 
                } 
                queryBuilder1.append(" Select Distinct  HLD.RELATIONSHIP_LEVEL_VALUES,HLD.HIERARCHY_NO       \n");
                queryBuilder1.append("            FROM   (SELECT RLD.RELATIONSHIP_LEVEL_VALUES,    \n");
                queryBuilder1.append("             RLD.HIERARCHY_NO,    \n");
                queryBuilder1.append("                     CCP.CCP_DETAILS_SID    \n");
                queryBuilder1.append("                FROM   RELATIONSHIP_LEVEL_DEFINITION RLD    \n");
                queryBuilder1.append("           JOIN   CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID = CCP.RELATIONSHIP_LEVEL_SID  AND RLD.RELATIONSHIP_BUILDER_SID=").append(relationshipBuilderSid).append("  \n");
                queryBuilder1.append("            JOIN   PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID = " ).append( projectionId ).append( "    \n");
                queryBuilder1.append("              JOIN   PROJECTION_MASTER PM ON PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID    \n");
                queryBuilder1.append("          WHERE  PM.PROJECTION_MASTER_SID = " ).append( projectionId ).append( ") CCPMAP,    \n");
                queryBuilder1.append("            (SELECT RLD1.HIERARCHY_NO,    \n");
                queryBuilder1.append("                      RLD1.RELATIONSHIP_LEVEL_SID,    \n");
                queryBuilder1.append("                RLD1.RELATIONSHIP_LEVEL_VALUES,    \n");
                queryBuilder1.append("                 RLD1.LEVEL_NO,    \n");
                queryBuilder1.append("              RLD1.LEVEL_NAME    \n");
                queryBuilder1.append("          FROM   RELATIONSHIP_LEVEL_DEFINITION RLD1    \n");
                queryBuilder1.append("          JOIN   " ).append( hierarchy ).append( "  PCH ON PCH.RELATIONSHIP_LEVEL_SID = RLD1.RELATIONSHIP_LEVEL_SID    \n");
                queryBuilder1.append("                 AND PCH.PROJECTION_MASTER_SID = " ).append( projectionId ).append( "    \n");
                queryBuilder1.append("   WHERE  RLD1.HIERARCHY_NO LIKE '%' ) HLD    \n");
                queryBuilder1.append("                   WHERE  CCPMAP.HIERARCHY_NO LIKE HLD.HIERARCHY_NO+'%' order By HLD.HIERARCHY_NO    \n");
  
                
                
                
                
                
                
                

            }  else if (method.equals("fetchByHierarchyNo")) {

                String projectionId = String.valueOf(inputs[0]);
                int startIndex = (Integer) inputs[5];
                int endIndex = (Integer) inputs[6];

                boolean count = (Boolean) inputs[7];

                String viewType = (String) inputs[9];
                String hierarchyNo = (String) inputs[12];
                String userid = (String) inputs[13];
                String sessionId = (String) inputs[14];
                String userGroup=(String) inputs[18];
                String methodology=(String) inputs[21];
               String baseLine=(String) inputs[22];
                
                if (count) {
                    queryBuilder1.append("SELECT count(*) \n");
                } else {
                    queryBuilder1.append(" DECLARE @start INT= \n");
                    queryBuilder1.append(startIndex);
                    queryBuilder1.append(", @End   INT=\n");
                    queryBuilder1.append(endIndex);

                    
                    queryBuilder1.append("   SELECT MAINQ.ACCOUNT_GROWTH,MAINQ.PRODUCT_GROWTH,MAINQ.PROJECTION_SALES,MAINQ.PROJECTION_UNITS, MAINQ.ACTUAL_SALES,MAINQ.ACTUAL_UNITS,   \n");
                    queryBuilder1.append("   MAINQ.level_no,MAINQ.rlv,MAINQ.YEARS,MAINQ.PERIODS,MAINQ.userGroup, MAINQ.baseLine,MAINQ.methodology,MAINQ.RELATIONSHIP_LEVEL_SID,MAINQ.HIERARCHY_NO,   \n");
                    queryBuilder1.append("   MAINQ.RCOUNT,MAINQ.actualProj,MAINQ.historySales,MAINQ.historyUnits,   \n");
                    queryBuilder1.append("   MAINQ.checkrec,MAINQ.hierarchyLevel,MAINQ.calcPeriodCount,   \n");
                    queryBuilder1.append("   MAINQ.methoCount,MAINQ.calcBasedcount,MAINQ.UNCHECK_COUNT   \n");
                    queryBuilder1.append("   FROM   (SELECT Q.ACCOUNT_GROWTH, Q.PRODUCT_GROWTH, Q.PROJECTION_SALES,Q.PROJECTION_UNITS,   \n");
                    queryBuilder1.append("   Q.ACTUAL_SALES, Q.ACTUAL_UNITS,Q.level_no, Q.rlv,Q.YEARS, Q.PERIODS,Q.userGroup,   \n");
                    queryBuilder1.append("   Q.baseLine,Q.methodology,Q.RELATIONSHIP_LEVEL_SID,Q.HIERARCHY_NO,Q.RCOUNT,Q.actualProj,Q.historySales,Q.historyUnits,   \n");
                    queryBuilder1.append("   Q.checkrec,Q.hierarchyLevel,Q.calcPeriodCount,Q.methoCount,   \n");
                    queryBuilder1.append("   Q.calcBasedcount, Q.UNCHECK_COUNT,Dense_rank()   \n");
                    queryBuilder1.append("    OVER (ORDER BY Q.rlv ASC) AS TEMP_INDEX FROM (   \n");
                    


                    
                    queryBuilder1.append("   SELECT \n");
                    queryBuilder1.append("   null as ACCOUNT_GROWTH,   \n");
                    queryBuilder1.append("   null as PRODUCT_GROWTH, sum(nm_ac.ACTUAL_SALES) as PROJECTION_SALES, sum(nm_ac.ACTUAL_UNITS) as PROJECTION_UNITS,    \n");
                    queryBuilder1.append("      null as ACTUAL_SALES,    null as ACTUAL_UNITS, max(rld.LEVEL_NO) as level_no,     \n");
                    queryBuilder1.append("   rld.RELATIONSHIP_LEVEL_VALUES as rlv,     p.\"YEAR\" as YEARS,  "
                            + "   p.QUARTER as PERIODS,     \n");
                    queryBuilder1.append("   min(nm_mas.USER_GROUP) as userGroup,min(nm_mas.CALCULATION_PERIODS) as baseLine,min(nm_mas.METHODOLOGY) as methodology,\n "
                            + "rld.RELATIONSHIP_LEVEL_SID,CCP.HIERARCHY_NO,COUNT (*)  AS RCOUNT,1 as actualProj,sum(nm_ac.HISTORY_PROJECTION_SALES) as historySales ,  sum(nm_ac.HISTORY_PROJECTION_UNITS) as historyUnits,          \n");
                    queryBuilder1.append("      min(CASE(CHECK_RECORD) WHEN 1 THEN 1 ELSE 0  END)  AS checkrec,rld.LEVEL_Name as hierarchyLevel,                   ");
                          
                    queryBuilder1.append(Constant.COUNT_DISTINCT_CASE_WHEN_NM_MAS_CALCULATE);
                    queryBuilder1.append(Constant.COUNT_DISTINCT_CASE_WHEN_NM_MAS_METHODOLOGY);
                    queryBuilder1.append(" count(distinct case when  nm_mas.CALCULATION_BASED is null then '1' else  nm_mas.CALCULATION_BASED end)     AS calcBasedcount , SUM(CASE(nm_mas.CHECK_RECORD) WHEN 1 THEN 0 ELSE 1 END) AS UNCHECK_COUNT      ");
                }
                queryBuilder1.append(Constant.FROM_PROJECTION_DETAILS_PD);


                queryBuilder1.append(Constant.JOIN_SELECT_DISTINCT_L_CCPRELATION);
                queryBuilder1.append(Constant.SELECT_CCP_MAP_CCP_DETAILS_SID_HLD_HIER);
                queryBuilder1.append(Constant.SELECT_RLD_RELATIONSHIP_LEVEL_VALUES_RL);
                queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD);
                queryBuilder1.append(Constant.JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL_SID);
                queryBuilder1.append(Constant.JOIN_PROJECTION_DETAILS_PD_ON_PD_CCP ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.JOIN_PROJECTION_MASTER_PM_ON_PD_PROJECTION);
                queryBuilder1.append(Constant.WHERE_PM_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.CCP_MAP_NEW_LINE);
                queryBuilder1.append(Constant.SELECT_RL_D1_HIERARCHY_NO_RLD1_RELATIONSHIP);
                queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD1);

                if (viewType.equals("C")) {

                    queryBuilder1.append(Constant.JOIN_PROJECTION_CUST_HIERARCHY_PCH);

                } else {

                    queryBuilder1.append(Constant.JOIN_PROJECTION_PROD_HIERARCHY_PCH);

                }
                queryBuilder1.append(Constant.ON_PCH_RELATIONSHIP_LEVEL_SID_RL_D1_REL);
                queryBuilder1.append(Constant.AND_P_CH_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.WHERE_RL_D1_HIERARCHY_NO_LIKE_HLD);
                queryBuilder1.append(Constant.WHERE_CCP_MAP_HIERARCHY_NO_LIKE_HLD_HIER);
                queryBuilder1.append(Constant.WHERE_L_CCP_HIERARCHY_NO_IN);
                queryBuilder1.append(Constant.SELECT_RL_D2_HIERARCHY_NO);
                queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD2);
                if (viewType.equals("C")) {

                    queryBuilder1.append(Constant.JOIN_PROJECTION_CUST_HIERARCHY_PCH2);

                } else {

                    queryBuilder1.append(Constant.JOIN_PROJECTION_PROD_HIERARCHY_PCH2);

                }

                queryBuilder1.append(Constant.ON_PCH2_RELATIONSHIP_LEVEL_SID_RLD2);
                queryBuilder1.append(Constant.AND_PC_H2_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append("  )) CCP      \n");
                queryBuilder1.append("   ON CCP.CCP_DETAILS_SID=pd.CCP_DETAILS_SID       \n");
                queryBuilder1.append("    JOIN RELATIONSHIP_LEVEL_DEFINITION rld   ON CCP.RELATIONSHIP_LEVEL_SID=rld.RELATIONSHIP_LEVEL_SID  \n");
                queryBuilder1.append("   JOIN ST_NM_SALES_PROJECTION_MASTER nm_mas      \n");
                queryBuilder1.append("   ON pd.PROJECTION_DETAILS_SID = nm_mas.PROJECTION_DETAILS_SID      \n");
                queryBuilder1.append(Constant.JOIN_ST_NM_ACTUAL_SALES_NM_AC);
                queryBuilder1.append("   ON nm_mas.PROJECTION_DETAILS_SID = nm_ac.PROJECTION_DETAILS_SID  AND nm_ac.PROJECTION_DETAILS_SID=nm_mas.PROJECTION_DETAILS_SID  AND nm_mas.USER_ID = nm_ac.USER_ID AND nm_mas.SESSION_ID = nm_ac.SESSION_ID    \n");
                queryBuilder1.append(Constant.JOIN_PERIOD_P);
                queryBuilder1.append(Constant.ON_PERIOD_SID_NM_AC_PERIOD_SID);

                queryBuilder1.append(Constant.WHERE_PD_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.AND_NM_MAS_USER_ID ).append( userid ).append( Constant.AND_NM_MAS_SESSION_ID ).append( sessionId ).append( Constant.SPACE_NEW_LINE);

                queryBuilder1.append(Constant.AND_RLD_LEVEL_NO ).append( hierarchyNo.trim() ).append( Constant.SPACE_NEW_LINE);
                   
                   if (!userGroup.equalsIgnoreCase(Constant.STRING_EMPTY)) {
                    queryBuilder1.append(Constant.AND_NM_MAS_USER_GROUP ).append( userGroup).append( Constant.SPACE_DOUBLE_NEW_LINE);
                }     
                    if (!baseLine.equalsIgnoreCase(Constant.STRING_EMPTY)) {
                    queryBuilder1.append(Constant.AND_NM_MAS_CALCULATION_PERIODS ).append( (baseLine) ).append( Constant.SPACE_DOUBLE_NEW_LINE);
                }     
                   
                     if (!methodology.equalsIgnoreCase(Constant.STRING_EMPTY)) {
                    queryBuilder1.append(Constant.AND_NM_MAS_METHODOLOGY ).append(methodology ).append( Constant.SPACE_DOUBLE_NEW_LINE);
                }     
                    
                if (!count) {
                    queryBuilder1.append(" GROUP  BY rld.RELATIONSHIP_LEVEL_VALUES,p.QUARTER, rld.RELATIONSHIP_LEVEL_SID ,rld.LEVEL_NO,p.\"YEAR\" ,rld.RELATIONSHIP_LEVEL_SID,CCP.HIERARCHY_NO ,rld.LEVEL_NAME    \n");

                    queryBuilder1.append(Constant.UNION_NEW_LINE);

                    queryBuilder1.append(Constant.SELECT_NEW_LINE);
                    queryBuilder1.append(Constant.AVG_NM_SP_ACCOUNT_GROWTH);
                    queryBuilder1.append(Constant.AVG_NM_SP_PRODUCT_GROWTH_SUM_NM_SP_PROJEC);
                    queryBuilder1.append("   null as ACTUAL_SALES,    null as ACTUAL_UNITS,max(rld.LEVEL_NO) as level_no,   \n");
                    queryBuilder1.append("  rld.RELATIONSHIP_LEVEL_VALUES as rlv,    p.\"YEAR\"  as YEARS,  "
                            + "   p.QUARTER as PERIODS,  \n");
                    queryBuilder1.append("  min(nm_mas.USER_GROUP) as userGroup,min(nm_mas.CALCULATION_PERIODS) as baseLine,min(nm_mas.METHODOLOGY) as methodology,rld.RELATIONSHIP_LEVEL_SID ,CCP.HIERARCHY_NO,COUNT (*)  AS RCOUNT ,0 as actualProj ,null as historySales , null as historyUnits,      \n");
                    queryBuilder1.append("      min(CASE(CHECK_RECORD) WHEN 1 THEN 1 ELSE 0  END)  AS checkrec ,rld.LEVEL_Name as hierarchyLevel,                  ");
                      
                    queryBuilder1.append(Constant.COUNT_DISTINCT_CASE_WHEN_NM_MAS_CALCULATE);
                    queryBuilder1.append(Constant.COUNT_DISTINCT_CASE_WHEN_NM_MAS_METHODOLOGY);
                    queryBuilder1.append(Constant.COUNT_DISTINCT_CASE_WHEN_NM_MAS_CALCULATION);
                    queryBuilder1.append(Constant.FROM_PROJECTION_DETAILS_PD);



                    queryBuilder1.append(Constant.JOIN_SELECT_DISTINCT_L_CCPRELATION);
                    queryBuilder1.append(Constant.SELECT_CCP_MAP_CCP_DETAILS_SID_HLD_HIER);
                    queryBuilder1.append(Constant.SELECT_RLD_RELATIONSHIP_LEVEL_VALUES_RL);
                    queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD);
                    queryBuilder1.append(Constant.JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL_SID);
                    queryBuilder1.append(Constant.JOIN_PROJECTION_DETAILS_PD_ON_PD_CCP ).append(projectionId).append( Constant.SPACE_NEW_LINE);

                    queryBuilder1.append(Constant.JOIN_PROJECTION_MASTER_PM_ON_PD_PROJECTION);
                    queryBuilder1.append(Constant.WHERE_PM_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.CCP_MAP_NEW_LINE);
                    queryBuilder1.append(Constant.SELECT_RL_D1_HIERARCHY_NO_RLD1_RELATIONSHIP);
                    queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD1);

                    if (viewType.equals("C")) {

                        queryBuilder1.append(Constant.JOIN_PROJECTION_CUST_HIERARCHY_PCH);

                    } else {

                        queryBuilder1.append(Constant.JOIN_PROJECTION_PROD_HIERARCHY_PCH);

                    }
                    queryBuilder1.append(Constant.ON_PCH_RELATIONSHIP_LEVEL_SID_RL_D1_REL);
                    queryBuilder1.append(Constant.AND_P_CH_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                    queryBuilder1.append(Constant.WHERE_RL_D1_HIERARCHY_NO_LIKE_HLD);
                    queryBuilder1.append(Constant.WHERE_CCP_MAP_HIERARCHY_NO_LIKE_HLD_HIER);
                    queryBuilder1.append(Constant.WHERE_L_CCP_HIERARCHY_NO_IN);
                    queryBuilder1.append(Constant.SELECT_RL_D2_HIERARCHY_NO);
                    queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD2);
                    if (viewType.equals("C")) {

                        queryBuilder1.append(Constant.JOIN_PROJECTION_CUST_HIERARCHY_PCH2);

                        queryBuilder1.append(Constant.ON_PCH2_RELATIONSHIP_LEVEL_SID_RLD2);

                        queryBuilder1.append(Constant.AND_PC_H2_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.SPACE_NEW_LINE);

                    } else if (viewType.equals("P")) {

                        queryBuilder1.append(Constant.JOIN_PROJECTION_PROD_HIERARCHY_PCH2);
                        queryBuilder1.append(Constant.ON_PCH2_RELATIONSHIP_LEVEL_SID_RLD2);

                        queryBuilder1.append(Constant.AND_PC_H2_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.SPACE_NEW_LINE);

                    } else {
                        queryBuilder1.append("    JOIN PROJECTION_CUST_HIERARCHY PCH   ON PCH.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID    \n");

                        queryBuilder1.append("    JOIN PROJECTION_PROD_HIERARCHY PPH   ON PPH.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID    \n");

                        queryBuilder1.append("   ON PCH.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID       \n");

                        queryBuilder1.append("   ON PPH.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID       \n");

                        queryBuilder1.append(Constant.AND_P_CH_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.SPACE_NEW_LINE);

                        queryBuilder1.append("   AND PPH.PROJECTION_MASTER_SID='" ).append(projectionId).append( Constant.SPACE_NEW_LINE);

                    }

                    queryBuilder1.append("   )) CCP      \n");
                    queryBuilder1.append("   ON CCP.CCP_DETAILS_SID=pd.CCP_DETAILS_SID \n");
                    queryBuilder1.append("    JOIN RELATIONSHIP_LEVEL_DEFINITION rld   ON CCP.RELATIONSHIP_LEVEL_SID=rld.RELATIONSHIP_LEVEL_SID  \n");
                    queryBuilder1.append(Constant.JOIN_ST_NM_SALES_PROJECTION_MASTER);
                    queryBuilder1.append(Constant.ON_PD_PROJECTION_DETAILS_SID_NM_MAS_PROJ);
                    queryBuilder1.append(Constant.JOIN_ST_NM_SALES_PROJECTION_NM_SP);
                    queryBuilder1.append("  ON nm_mas.PROJECTION_DETAILS_SID = nm_sp.PROJECTION_DETAILS_SID   AND nm_sp.PROJECTION_DETAILS_SID=nm_mas.PROJECTION_DETAILS_SID  AND nm_mas.USER_ID = nm_sp.USER_ID AND nm_mas.SESSION_ID = nm_sp.SESSION_ID    \n");
                    queryBuilder1.append(Constant.JOIN_PERIOD_P_ON_PERIOD_SID_NM_SP_PERIOD ).append(projectionId).append( "' AND nm_mas.USER_ID = '" ).append( userid ).append( Constant.AND_NM_MAS_SESSION_ID ).append( sessionId ).append( Constant.SPACE_NEW_LINE);

                    queryBuilder1.append(Constant.AND_RLD_LEVEL_NO ).append(hierarchyNo.trim()).append( Constant.AND_NM_MAS_USER_ID ).append( userid ).append( Constant.AND_NM_MAS_SESSION_ID ).append( sessionId ).append( Constant.SPACE_NEW_LINE);

                          if (!userGroup.equalsIgnoreCase(Constant.STRING_EMPTY)) {
                    queryBuilder1.append(Constant.AND_NM_MAS_USER_GROUP ).append(userGroup).append( Constant.SPACE_DOUBLE_NEW_LINE);
                }     
                    if (!baseLine.equalsIgnoreCase(Constant.STRING_EMPTY)) {
                    queryBuilder1.append(Constant.AND_NM_MAS_CALCULATION_PERIODS ).append(baseLine).append( Constant.SPACE_DOUBLE_NEW_LINE);
                }     
                   
                     if (!methodology.equalsIgnoreCase(Constant.STRING_EMPTY)) {
                    queryBuilder1.append(Constant.AND_NM_MAS_METHODOLOGY ).append(methodology).append( Constant.SPACE_DOUBLE_NEW_LINE);
                }     
                   
                    
                    queryBuilder1.append("  GROUP  BY rld.RELATIONSHIP_LEVEL_VALUES,p.QUARTER ,rld.LEVEL_NO,p.\"YEAR\"  ,rld.RELATIONSHIP_LEVEL_SID,CCP.HIERARCHY_NO,rld.LEVEL_NAME \n");
                    
                    queryBuilder1.append("   ) Q) MAINQ WHERE  MAINQ.TEMP_INDEX > @Start AND MAINQ.TEMP_INDEX <= ( @Start + @End )  \n");
                    queryBuilder1.append("   ORDER  BY MAINQ.rlv,MAINQ.YEARS,MAINQ.PERIODS   \n");

                
                }

            }  else if (method.equals("saveSalesRec")) {

                List<StringBuilder> queryList = (List<StringBuilder>) inputs[0];
               
                for (StringBuilder builder : queryList) {
                    int result =HelperTableLocalServiceUtil.executeUpdateQueryCount(builder.toString());
                    list.add(result);
            
                }
            } else if (method.equals("getCheckedRecords")) {
                
                String userid = (String) inputs[4];
                String sessionId = (String) inputs[5];
                queryBuilder1.append("    select PROJECTION_DETAILS_SID from ST_NM_SALES_PROJECTION_MASTER where  CHECK_RECORD='1' and  USER_ID= '" ).append( userid ).append( "' and SESSION_ID='" ).append( sessionId ).append( Constant.SPACE_NEW_LINE);


            } else if (method.equals("getCCPIds")) {

                queryBuilder1.append("  SELECT * from dbo.CCP_DETAILS where CCP_DETAILS_SID in (   \n");
                queryBuilder1.append("  SELECT LCCP.CCP_DETAILS_SID from          \n");
                queryBuilder1.append(" (SELECT CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID from  \n");
                queryBuilder1.append("      (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID   \n");
                queryBuilder1.append("     FROM RELATIONSHIP_LEVEL_DEFINITION RLD          JOIN CCP_MAP CCP ON   \n");
                queryBuilder1.append("  RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID        \n");
                queryBuilder1.append("     JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID='22'   \n");
                queryBuilder1.append("     JOIN PROJECTION_CUST_HIERARCHY PCH1       ON  RLD.RELATIONSHIP_LEVEL_SID=PCH1.RELATIONSHIP_LEVEL_SID     \n");
                queryBuilder1.append("    JOIN PROJECTION_MASTER PM  ON PCH1.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID          WHERE PM.PROJECTION_MASTER_SID='22') CCPMAP,     \n");
                queryBuilder1.append("      (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID         FROM RELATIONSHIP_LEVEL_DEFINITION RLD1      \n");
                queryBuilder1.append("       JOIN PROJECTION_CUST_HIERARCHY PCH        ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID  \n");
                queryBuilder1.append("            AND PCH.PROJECTION_MASTER_SID='22'          WHERE RLD1.HIERARCHY_NO like '5-1.1.1.1.1'  ) HLD         \n");
                queryBuilder1.append("    WHERE CCPMAP.HIERARCHY_NO like HLD.HIERARCHY_NO + '%' ) LCCP         WHERE LCCP.HIERARCHY_NO in          \n");
                queryBuilder1.append("   (SELECT RLD2.HIERARCHY_NO          FROM RELATIONSHIP_LEVEL_DEFINITION RLD2          JOIN PROJECTION_CUST_HIERARCHY PCH2      \n");
                queryBuilder1.append("      ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID        \n");
                queryBuilder1.append("      AND PCH2.PROJECTION_MASTER_SID='22'        \n");
                queryBuilder1.append("      WHERE RLD2.LEVEL_NAME = 'Trading Partner' ))  \n");
            }else if (method.equals("getLevelIndex")) {
                int projectionId = (Integer) inputs[0];
                String hierarchy = (String) inputs[1];
                  String hierarchyNo = (String) inputs[2];
                    String selectedHiearchyNo = (String) inputs[3];
            
            
               queryBuilder1.append("  select TEMP.TEMP_INDEX from(SELECT RLD.HIERARCHY_NO, RLD.RELATIONSHIP_LEVEL_VALUES, \n  ");
               queryBuilder1.append("   ROW_NUMBER() OVER (ORDER BY RLD.RELATIONSHIP_LEVEL_VALUES ASC) AS TEMP_INDEX\n  ");
                 queryBuilder1.append("   FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n  ");
                 queryBuilder1.append("   JOIN ").append(CommonUtils.getViewTableName(hierarchy)).append(" PCH ON PCH.RELATIONSHIP_LEVEL_SID = RLD.RELATIONSHIP_LEVEL_SID\n  ");
                 queryBuilder1.append("   AND PCH.PROJECTION_MASTER_SID = ").append(projectionId).append("\n  ");
                 queryBuilder1.append("   WHERE RLD.HIERARCHY_NO LIKE '").append(hierarchyNo).append("%' and RLD.LEVEL_NO = ").append((selectedHiearchyNo.length() - selectedHiearchyNo.replace(".", "").length()+1)).append(") \n  ");
                 queryBuilder1.append("   TEMP where TEMP.HIERARCHY_NO='").append(selectedHiearchyNo).append("' \n  ");
                    
              }else if(method.equals("getCount")){
                int projectionId = (Integer) inputs[0];
                String viewType = (String) inputs[3];
                int levelNo = (Integer) inputs[4];
              
              
                queryBuilder1.append("        Select count(DISTINCT CCP.HIERARCHY_NO )    \n");
                queryBuilder1.append("     FROM (SELECT CCPMAP.CCP_DETAILS_SID,    \n");
                queryBuilder1.append("                   HLD.HIERARCHY_NO,    \n");
                queryBuilder1.append("                   HLD.RELATIONSHIP_LEVEL_SID    \n");
                queryBuilder1.append("           FROM   (SELECT RLD.HIERARCHY_NO,    \n");
                queryBuilder1.append("                        CCP.CCP_DETAILS_SID    \n");
                queryBuilder1.append("                 FROM   RELATIONSHIP_LEVEL_DEFINITION RLD    \n");
                queryBuilder1.append("               JOIN   CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID = CCP.RELATIONSHIP_LEVEL_SID    \n");
                queryBuilder1.append("              JOIN   PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID    \n");
                queryBuilder1.append("                           AND PD.PROJECTION_MASTER_SID =  '" ).append( projectionId ).append( Constant.SPACE_NEW_LINE);
                 if (viewType.equals("C")) {

                        queryBuilder1.append(Constant.JOIN_PROJECTION_CUST_HIERARCHY_PCH);

                    } else {

                        queryBuilder1.append(Constant.JOIN_PROJECTION_PROD_HIERARCHY_PCH);

                    }
                
                queryBuilder1.append("   ON PCH.RELATIONSHIP_LEVEL_SID = RLD.RELATIONSHIP_LEVEL_SID   ");
                queryBuilder1.append("                     AND PCH.PROJECTION_MASTER_SID= '" ).append( projectionId ).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append("             ) CCPMAP JOIN    \n");
                queryBuilder1.append("            (SELECT RLD1.HIERARCHY_NO,    \n");
                queryBuilder1.append("                   RLD1.RELATIONSHIP_LEVEL_SID,    \n");
                queryBuilder1.append("                   RLD1.LEVEL_NO    \n");
                queryBuilder1.append("            FROM   RELATIONSHIP_LEVEL_DEFINITION RLD1    \n");
                
                
                if (viewType.equals("C")) {

                        queryBuilder1.append(Constant.JOIN_PROJECTION_CUST_HIERARCHY_PCH);

                    } else {

                        queryBuilder1.append(Constant.JOIN_PROJECTION_PROD_HIERARCHY_PCH);

                    }
                 
                queryBuilder1.append("           ON PCH.RELATIONSHIP_LEVEL_SID = RLD1.RELATIONSHIP_LEVEL_SID   AND PCH.PROJECTION_MASTER_SID =   '" ).append( projectionId ).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append("             AND  RLD1.HIERARCHY_NO LIKE '%') HLD ON CCPMAP.HIERARCHY_NO LIKE HLD.HIERARCHY_NO+'%'    \n");
                queryBuilder1.append("   WHERE  HLD.LEVEL_NO ='").append(levelNo).append("'   )CCP  \n");

                
                
              }else if (method.equals("refreshData")) {

                String projectionId = String.valueOf(inputs[0]);
                 String viewType = (String) inputs[9];
                String hierarchyNo = (String) inputs[12];
                String userid = (String) inputs[13];
                String sessionId = (String) inputs[14];

                String userGroup=(String) inputs[18];
                String methodology=(String) inputs[21];
               String baseLine=(String) inputs[22];
                

                    queryBuilder1.append("   SELECT \n");
                    queryBuilder1.append("   null as ACCOUNT_GROWTH,   \n");
                    queryBuilder1.append("   null as PRODUCT_GROWTH, sum(nm_ac.ACTUAL_SALES) as PROJECTION_SALES, sum(nm_ac.ACTUAL_UNITS) as PROJECTION_UNITS,    \n");
                    queryBuilder1.append("      null as ACTUAL_SALES,    null as ACTUAL_UNITS, max(rld.LEVEL_NO) as level_no,     \n");
                    queryBuilder1.append("   rld.RELATIONSHIP_LEVEL_VALUES as rlv,     p.\"YEAR\" ,     p.QUARTER ,     \n");
                    queryBuilder1.append("   min(nm_mas.USER_GROUP) as userGroup,min(nm_mas.CALCULATION_PERIODS) as baseLine,min(nm_mas.METHODOLOGY) as methodology,rld.RELATIONSHIP_LEVEL_SID,CCP.HIERARCHY_NO,COUNT (*)  AS RCOUNT,1 as actualProj,sum(nm_ac.HISTORY_PROJECTION_SALES) as historySales ,  sum(nm_ac.HISTORY_PROJECTION_UNITS) as historyUnits,          \n");
                    queryBuilder1.append("      min(CASE(CHECK_RECORD) WHEN 1 THEN 1 ELSE 0  END)  AS checkrec,rld.LEVEL_Name as hierarchyLecel,                  ");
                    
                    queryBuilder1.append(Constant.COUNT_DISTINCT_CASE_WHEN_NM_MAS_CALCULATE);
                    queryBuilder1.append(Constant.COUNT_DISTINCT_CASE_WHEN_NM_MAS_METHODOLOGY);
                    queryBuilder1.append(Constant.COUNT_DISTINCT_CASE_WHEN_NM_MAS_CALCULATION);
                
                queryBuilder1.append(Constant.FROM_PROJECTION_DETAILS_PD);
                if (viewType.equals("C")) {

                    queryBuilder1.append("    JOIN PROJECTION_CUST_HIERARCHY ph     \n");

                } else {

                    queryBuilder1.append("    JOIN PROJECTION_PROD_HIERARCHY ph     \n");

                }

                queryBuilder1.append("   ON pd.PROJECTION_MASTER_SID = ph.PROJECTION_MASTER_SID    JOIN RELATIONSHIP_LEVEL_DEFINITION rld     \n");

                queryBuilder1.append("   ON ph.RELATIONSHIP_LEVEL_SID = rld.RELATIONSHIP_LEVEL_SID      \n");

                queryBuilder1.append(Constant.JOIN_SELECT_DISTINCT_L_CCPRELATION);
                queryBuilder1.append(Constant.SELECT_CCP_MAP_CCP_DETAILS_SID_HLD_HIER);
                queryBuilder1.append(Constant.SELECT_RLD_RELATIONSHIP_LEVEL_VALUES_RL);
                queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD);
                queryBuilder1.append(Constant.JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL_SID);
                queryBuilder1.append(Constant.JOIN_PROJECTION_DETAILS_PD_ON_PD_CCP ).append(projectionId).append( Constant.SPACE_NEW_LINE);

                queryBuilder1.append(Constant.JOIN_PROJECTION_MASTER_PM_ON_PD_PROJECTION);
                queryBuilder1.append(Constant.WHERE_PM_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.CCP_MAP_NEW_LINE);
                queryBuilder1.append(Constant.SELECT_RL_D1_HIERARCHY_NO_RLD1_RELATIONSHIP);
                queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD1);

                if (viewType.equals("C")) {

                    queryBuilder1.append(Constant.JOIN_PROJECTION_CUST_HIERARCHY_PCH);

                } else {

                    queryBuilder1.append(Constant.JOIN_PROJECTION_PROD_HIERARCHY_PCH);

                }
                queryBuilder1.append(Constant.ON_PCH_RELATIONSHIP_LEVEL_SID_RL_D1_REL);
                queryBuilder1.append(Constant.AND_P_CH_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.WHERE_RL_D1_HIERARCHY_NO_LIKE_HLD);
                queryBuilder1.append(Constant.WHERE_CCP_MAP_HIERARCHY_NO_LIKE_HLD_HIER);
                queryBuilder1.append(Constant.WHERE_L_CCP_HIERARCHY_NO_IN);
                queryBuilder1.append(Constant.SELECT_RL_D2_HIERARCHY_NO);
                queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD2);
                if (viewType.equals("C")) {

                    queryBuilder1.append(Constant.JOIN_PROJECTION_CUST_HIERARCHY_PCH2);

                } else {

                    queryBuilder1.append(Constant.JOIN_PROJECTION_PROD_HIERARCHY_PCH2);

                }

                queryBuilder1.append(Constant.ON_PCH2_RELATIONSHIP_LEVEL_SID_RLD2);
                queryBuilder1.append(Constant.AND_PC_H2_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append("  )) CCP      \n");
                queryBuilder1.append("   ON CCP.CCP_DETAILS_SID=pd.CCP_DETAILS_SID AND CCP.RELATIONSHIP_LEVEL_SID=rld.RELATIONSHIP_LEVEL_SID      \n");

                queryBuilder1.append("   JOIN ST_NM_SALES_PROJECTION_MASTER nm_mas      \n");
                queryBuilder1.append("   ON pd.PROJECTION_DETAILS_SID = nm_mas.PROJECTION_DETAILS_SID      \n");
                queryBuilder1.append(Constant.JOIN_ST_NM_ACTUAL_SALES_NM_AC);
                queryBuilder1.append("   ON nm_mas.PROJECTION_DETAILS_SID = nm_ac.PROJECTION_DETAILS_SID  AND nm_ac.PROJECTION_DETAILS_SID=nm_mas.PROJECTION_DETAILS_SID  AND nm_mas.USER_ID = nm_ac.USER_ID AND nm_mas.SESSION_ID = nm_ac.SESSION_ID    \n");
                queryBuilder1.append(Constant.JOIN_PERIOD_P);
                queryBuilder1.append(Constant.ON_PERIOD_SID_NM_AC_PERIOD_SID);

                queryBuilder1.append(Constant.WHERE_PD_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.AND_NM_MAS_USER_ID ).append( userid ).append( Constant.AND_NM_MAS_SESSION_ID ).append( sessionId ).append( Constant.SPACE_NEW_LINE);

                queryBuilder1.append("    and rld.HIERARCHY_NO in (" ).append(hierarchyNo).append( ")   \n");

                     if (!userGroup.equalsIgnoreCase(Constant.STRING_EMPTY)) {
                    queryBuilder1.append(Constant.AND_NM_MAS_USER_GROUP ).append(userGroup).append( Constant.SPACE_DOUBLE_NEW_LINE);
                }     
                    if (!baseLine.equalsIgnoreCase(Constant.STRING_EMPTY)) {
                    queryBuilder1.append(Constant.AND_NM_MAS_CALCULATION_PERIODS ).append(baseLine).append( Constant.SPACE_DOUBLE_NEW_LINE);
                }     
                   
                     if (!methodology.equalsIgnoreCase(Constant.STRING_EMPTY)) {
                    queryBuilder1.append(Constant.AND_NM_MAS_METHODOLOGY ).append(methodology).append( Constant.SPACE_DOUBLE_NEW_LINE);
                }     
                   
                  
                    queryBuilder1.append(" GROUP  BY rld.RELATIONSHIP_LEVEL_VALUES,p.QUARTER, rld.RELATIONSHIP_LEVEL_SID ,rld.LEVEL_NO,p.\"YEAR\" ,rld.RELATIONSHIP_LEVEL_SID,CCP.HIERARCHY_NO ,rld.LEVEL_NAME    \n");

                    queryBuilder1.append(Constant.UNION_NEW_LINE);

                    queryBuilder1.append(Constant.SELECT_NEW_LINE);
                    queryBuilder1.append(Constant.AVG_NM_SP_ACCOUNT_GROWTH);
                    queryBuilder1.append(Constant.AVG_NM_SP_PRODUCT_GROWTH_SUM_NM_SP_PROJEC);
                    queryBuilder1.append("   null as ACTUAL_SALES,    null as ACTUAL_UNITS,max(rld.LEVEL_NO) as level_no,   \n");
                    queryBuilder1.append("  rld.RELATIONSHIP_LEVEL_VALUES as rlv,    p.\"YEAR\" ,     p.QUARTER,  \n");
                    queryBuilder1.append("  min(nm_mas.USER_GROUP) as userGroup,min(nm_mas.CALCULATION_PERIODS) as baseLine,min(nm_mas.METHODOLOGY) as methodology,rld.RELATIONSHIP_LEVEL_SID ,CCP.HIERARCHY_NO,COUNT (*)  AS RCOUNT ,0 as actualProj ,null as historySales , null as historyUnits,      \n");
                    queryBuilder1.append("      min(CASE(CHECK_RECORD) WHEN 1 THEN 1 ELSE 0  END)  AS checkrec,rld.LEVEL_Name as hierarchyLecel,                  ");
                    
                    queryBuilder1.append(Constant.COUNT_DISTINCT_CASE_WHEN_NM_MAS_CALCULATE);
                    queryBuilder1.append(Constant.COUNT_DISTINCT_CASE_WHEN_NM_MAS_METHODOLOGY);
                    queryBuilder1.append(Constant.COUNT_DISTINCT_CASE_WHEN_NM_MAS_CALCULATION);
                 
                    queryBuilder1.append(Constant.FROM_PROJECTION_DETAILS_PD);

                    if (viewType.equals("C")) {
                        queryBuilder1.append("     JOIN PROJECTION_CUST_HIERARCHY ph     \n");
                    } else {
                        queryBuilder1.append("       JOIN PROJECTION_PROD_HIERARCHY ph     \n");
                    }

                    queryBuilder1.append("  ON pd.PROJECTION_MASTER_SID = ph.PROJECTION_MASTER_SID    JOIN RELATIONSHIP_LEVEL_DEFINITION rld  \n");
                    queryBuilder1.append("  ON ph.RELATIONSHIP_LEVEL_SID = rld.RELATIONSHIP_LEVEL_SID   \n");

                    queryBuilder1.append(Constant.JOIN_SELECT_DISTINCT_L_CCPRELATION);
                    queryBuilder1.append(Constant.SELECT_CCP_MAP_CCP_DETAILS_SID_HLD_HIER);
                    queryBuilder1.append(Constant.SELECT_RLD_RELATIONSHIP_LEVEL_VALUES_RL);
                    queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD);
                    queryBuilder1.append(Constant.JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL_SID);
                    queryBuilder1.append(Constant.JOIN_PROJECTION_DETAILS_PD_ON_PD_CCP ).append(projectionId).append( Constant.SPACE_NEW_LINE);

                    queryBuilder1.append(Constant.JOIN_PROJECTION_MASTER_PM_ON_PD_PROJECTION);
                    queryBuilder1.append(Constant.WHERE_PM_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.CCP_MAP_NEW_LINE);
                    queryBuilder1.append(Constant.SELECT_RL_D1_HIERARCHY_NO_RLD1_RELATIONSHIP);
                    queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD1);

                    if (viewType.equals("C")) {

                        queryBuilder1.append(Constant.JOIN_PROJECTION_CUST_HIERARCHY_PCH);

                    } else {

                        queryBuilder1.append(Constant.JOIN_PROJECTION_PROD_HIERARCHY_PCH);

                    }
                    queryBuilder1.append(Constant.ON_PCH_RELATIONSHIP_LEVEL_SID_RL_D1_REL);
                    queryBuilder1.append(Constant.AND_P_CH_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                    queryBuilder1.append(Constant.WHERE_RL_D1_HIERARCHY_NO_LIKE_HLD);
                    queryBuilder1.append(Constant.WHERE_CCP_MAP_HIERARCHY_NO_LIKE_HLD_HIER);
                    queryBuilder1.append(Constant.WHERE_L_CCP_HIERARCHY_NO_IN);
                    queryBuilder1.append(Constant.SELECT_RL_D2_HIERARCHY_NO);
                    queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD2);
                    if (viewType.equals("C")) {

                        queryBuilder1.append(Constant.JOIN_PROJECTION_CUST_HIERARCHY_PCH2);

                        queryBuilder1.append(Constant.ON_PCH2_RELATIONSHIP_LEVEL_SID_RLD2);

                        queryBuilder1.append(Constant.AND_PC_H2_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.SPACE_NEW_LINE);

                    } else if (viewType.equals("P")) {

                        queryBuilder1.append(Constant.JOIN_PROJECTION_PROD_HIERARCHY_PCH2);
                        queryBuilder1.append(Constant.ON_PCH2_RELATIONSHIP_LEVEL_SID_RLD2);

                        queryBuilder1.append(Constant.AND_PC_H2_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.SPACE_NEW_LINE);

                    } else {
                        queryBuilder1.append("    JOIN PROJECTION_CUST_HIERARCHY PCH   ON PCH.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID    \n");

                        queryBuilder1.append("    JOIN PROJECTION_PROD_HIERARCHY PPH   ON PPH.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID    \n");

                        queryBuilder1.append("   ON PCH.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID       \n");

                        queryBuilder1.append("   ON PPH.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID       \n");

                        queryBuilder1.append(Constant.AND_P_CH_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.SPACE_NEW_LINE);

                        queryBuilder1.append("   AND PPH.PROJECTION_MASTER_SID='" ).append(projectionId).append( Constant.SPACE_NEW_LINE);

                    }

                    queryBuilder1.append("   )) CCP      \n");
                    queryBuilder1.append("   ON CCP.CCP_DETAILS_SID=pd.CCP_DETAILS_SID AND CCP.RELATIONSHIP_LEVEL_SID=rld.RELATIONSHIP_LEVEL_SID      \n");

                    queryBuilder1.append(Constant.JOIN_ST_NM_SALES_PROJECTION_MASTER);
                    queryBuilder1.append(Constant.ON_PD_PROJECTION_DETAILS_SID_NM_MAS_PROJ);
                    queryBuilder1.append(Constant.JOIN_ST_NM_SALES_PROJECTION_NM_SP);
                    queryBuilder1.append("  ON nm_mas.PROJECTION_DETAILS_SID = nm_sp.PROJECTION_DETAILS_SID   AND nm_sp.PROJECTION_DETAILS_SID=nm_mas.PROJECTION_DETAILS_SID  AND nm_mas.USER_ID = nm_sp.USER_ID AND nm_mas.SESSION_ID = nm_sp.SESSION_ID    \n");
                    queryBuilder1.append(Constant.JOIN_PERIOD_P_ON_PERIOD_SID_NM_SP_PERIOD ).append(projectionId ).append( "' AND nm_mas.USER_ID = '" ).append( userid ).append( Constant.AND_NM_MAS_SESSION_ID ).append( sessionId ).append( Constant.SPACE_NEW_LINE);

                    queryBuilder1.append("   and rld.HIERARCHY_NO in (" ).append(hierarchyNo).append( ")  AND nm_mas.USER_ID = '" ).append( userid ).append( Constant.AND_NM_MAS_SESSION_ID ).append( sessionId ).append( Constant.SPACE_NEW_LINE);

                         if (!userGroup.equalsIgnoreCase(Constant.STRING_EMPTY)) {
                    queryBuilder1.append(Constant.AND_NM_MAS_USER_GROUP ).append(userGroup).append( Constant.SPACE_DOUBLE_NEW_LINE);
                }     
                    if (!baseLine.equalsIgnoreCase(Constant.STRING_EMPTY)) {
                    queryBuilder1.append(Constant.AND_NM_MAS_CALCULATION_PERIODS ).append(baseLine).append( Constant.SPACE_DOUBLE_NEW_LINE);
                }     
                   
                     if (!methodology.equalsIgnoreCase(Constant.STRING_EMPTY)) {
                    queryBuilder1.append(Constant.AND_NM_MAS_METHODOLOGY ).append(methodology).append( Constant.SPACE_DOUBLE_NEW_LINE);
                }     
                   
                       
                    queryBuilder1.append("  GROUP  BY rld.RELATIONSHIP_LEVEL_VALUES,p.QUARTER ,rld.LEVEL_NO,p.\"YEAR\"  ,rld.RELATIONSHIP_LEVEL_SID,CCP.HIERARCHY_NO,rld.LEVEL_NAME    order by  rld.RELATIONSHIP_LEVEL_VALUES,p.\"YEAR\",p.QUARTER    \n");

                

            }else if (method.equals("saveGroup")) {

             
              
                String projectionId = String.valueOf(inputs[0]);
                  String viewType = (String) inputs[2];
                String hierarchyNos = (String) inputs[3];
             
                String value = (String) inputs[5];

                String userid = (String) inputs[9];

                String sessionId = (String) inputs[10];
                
                
                
                queryBuilder1.append("   UPDATE ST_NM_SALES_PROJECTION_MASTER  SET USER_GROUP='" ).append( value ).append( "'  WHERE  PROJECTION_DETAILS_SID \n");
                queryBuilder1.append(Constant.IN_NEW_LINE);
                queryBuilder1.append(Constant.SELECT_PROJECTION_DETAILS_SID_FROM_PROJ_DETAILS);

                queryBuilder1.append(Constant.SELECT_LCCP_DETAILS_SID_FROM);
                queryBuilder1.append(Constant.SELECT_CCP_MAP_CCP_DETAILS_SID_HLD_HIER);
                queryBuilder1.append(Constant.SELECT_RLD_RELATIONSHIP_LEVEL_VALUES_RL);
                queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD);
                queryBuilder1.append(Constant.JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL_SID);
                queryBuilder1.append(Constant.JOIN_PROJECTION_DETAILS_PD_ON_PD_CCP ).append(projectionId ).append( Constant.SPACE_NEW_LINE);

                queryBuilder1.append(Constant.JOIN_PROJECTION_MASTER_PM_ON_PD_PROJECTION);
                queryBuilder1.append(Constant.WHERE_PM_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.CCP_MAP_NEW_LINE);
                queryBuilder1.append(Constant.SELECT_RL_D1_HIERARCHY_NO_RLD1_RELATIONSHIP);
                queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD1);

                if (viewType.equals("C")) {

                    queryBuilder1.append(Constant.JOIN_PROJECTION_CUST_HIERARCHY_PCH);

                } else {

                    queryBuilder1.append(Constant.JOIN_PROJECTION_PROD_HIERARCHY_PCH);

                }
                queryBuilder1.append(Constant.ON_PCH_RELATIONSHIP_LEVEL_SID_RL_D1_REL);
                queryBuilder1.append(Constant.AND_P_CH_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.BLANK_QUOTES ).append( hierarchyNos ).append( Constant.HLD_NEW_LINE);
                queryBuilder1.append(Constant.WHERE_CCP_MAP_HIERARCHY_NO_LIKE_HLD_HIER);
                queryBuilder1.append(Constant.WHERE_L_CCP_HIERARCHY_NO_IN);
                queryBuilder1.append(Constant.SELECT_RL_D2_HIERARCHY_NO);
                queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD2);
                if (viewType.equals("C")) {

                    queryBuilder1.append(Constant.JOIN_PROJECTION_CUST_HIERARCHY_PCH2);

                } else {

                    queryBuilder1.append(Constant.JOIN_PROJECTION_PROD_HIERARCHY_PCH2);

                }

                queryBuilder1.append(Constant.ON_PCH2_RELATIONSHIP_LEVEL_SID_RLD2);
                queryBuilder1.append(Constant.AND_PC_H2_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.SPACE_NEW_LINE);

                queryBuilder1.append("    )) ) AND USER_ID = '" ).append( userid ).append( Constant.COLON_AND_SESSION_ID ).append( sessionId ).append( Constant.SPACE_NEW_LINE);

            }  else if(method.equals("loadGroupValues")){
                String sessionId = (String) inputs[1];
                String userid = (String) inputs[2];
                 String attribute = (String) inputs[3];

                 if(attribute.equals("group")){
                queryBuilder1.append("   select distinct USER_GROUP from ST_NM_SALES_PROJECTION_MASTER where  USER_ID = '" ).append( userid ).append( Constant.COLON_AND_SESSION_ID ).append( sessionId ).append( Constant.SPACE_NEW_LINE);
                 }else if(attribute.equals("methodology")){
                queryBuilder1.append("   select distinct METHODOLOGY from ST_NM_SALES_PROJECTION_MASTER where  USER_ID = '" ).append( userid ).append( Constant.COLON_AND_SESSION_ID ).append( sessionId ).append( Constant.SPACE_NEW_LINE);
                 }else if(attribute.equals("baseline")){
                queryBuilder1.append("   select distinct CALCULATION_PERIODS from ST_NM_SALES_PROJECTION_MASTER where  USER_ID = '" ).append( userid ).append( Constant.COLON_AND_SESSION_ID ).append( sessionId ).append( Constant.SPACE_NEW_LINE);
                 }
            }else if(method.equals("updateByHierarchyNo")){
              
            
                 String projectionId = String.valueOf(inputs[0]);
                 String viewType = (String) inputs[1];
                 String hierarchyNos = (String) inputs[2];
                 String userid = (String) inputs[3];
                 String sessionId = (String) inputs[4];
                 
                 String checkUncheck = (String) inputs[5];
                  String tempHierarchyNos = (String) inputs[6];
                 
                 if(checkUncheck.equals(Constant.CHECK))
                 {
                    queryBuilder1.append("  UPDATE ST_NM_SALES_PROJECTION_MASTER SET CHECK_RECORD='1' ");
                 }else{
                     
                    queryBuilder1.append("  UPDATE ST_NM_SALES_PROJECTION_MASTER SET CHECK_RECORD='0' ");
                 
                 }
             

                queryBuilder1.append("  where PROJECTION_DETAILS_SID  ");

                queryBuilder1.append("  in ( ");

                queryBuilder1.append("  SELECT PROJECTION_DETAILS_SID FROM PROJECTION_DETAILS WHERE  PROJECTION_MASTER_SID='" ).append(projectionId).append( "' AND CCP_DETAILS_SID IN  ");

                queryBuilder1.append("   (SELECT LCCP.CCP_DETAILS_SID from       ");
                queryBuilder1.append("   (SELECT CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID from       ");
                queryBuilder1.append("   (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID       ");
                queryBuilder1.append("   FROM RELATIONSHIP_LEVEL_DEFINITION RLD       ");
                queryBuilder1.append("   JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID      ");
                queryBuilder1.append(Constant.JOIN_PROJECTION_DETAILS_PD_ON_PD_CCP ).append(projectionId).append( "'      ");

                if (viewType.equals("C")) {

                    queryBuilder1.append("    JOIN PROJECTION_CUST_HIERARCHY PCH1     ");

                } else {

                    queryBuilder1.append("    JOIN PROJECTION_PROD_HIERARCHY PCH1     ");

                }
                queryBuilder1.append("  ON  RLD.RELATIONSHIP_LEVEL_SID=PCH1.RELATIONSHIP_LEVEL_SID  ");
                queryBuilder1.append("   JOIN PROJECTION_MASTER PM  ON PCH1.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID       ");
                queryBuilder1.append(Constant.WHERE_PM_PROJECTION_MASTER_SID ).append(projectionId).append( "') CCPMAP,       ");
                queryBuilder1.append("   (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID      ");
                queryBuilder1.append("   FROM RELATIONSHIP_LEVEL_DEFINITION RLD1       ");

                if (viewType.equals("C")) {

                    queryBuilder1.append("    JOIN PROJECTION_CUST_HIERARCHY PCH     ");

                } else {

                    queryBuilder1.append("    JOIN PROJECTION_PROD_HIERARCHY PCH     ");

                }
                queryBuilder1.append("   ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID       ");
                queryBuilder1.append(Constant.AND_P_CH_PROJECTION_MASTER_SID ).append(projectionId).append( "'       ");
                queryBuilder1.append("    ").append(tempHierarchyNos).append(" ) HLD      ");
                queryBuilder1.append("   WHERE CCPMAP.HIERARCHY_NO like concat(HLD.HIERARCHY_NO,'%') ) LCCP      ");
                queryBuilder1.append("   WHERE LCCP.HIERARCHY_NO in       ");
                queryBuilder1.append("   (SELECT RLD2.HIERARCHY_NO       ");
                
                queryBuilder1.append("   FROM RELATIONSHIP_LEVEL_DEFINITION RLD2      ");
                if (viewType.equals("C")) {

                    queryBuilder1.append("    JOIN PROJECTION_CUST_HIERARCHY PCH2     ");

                } else {

                    queryBuilder1.append("    JOIN PROJECTION_PROD_HIERARCHY PCH2     ");

                }

                queryBuilder1.append("   ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID       ");
                queryBuilder1.append(Constant.AND_PC_H2_PROJECTION_MASTER_SID ).append(projectionId).append( "'       ");
                queryBuilder1.append("   WHERE RLD2.HIERARCHY_NO in (").append(hierarchyNos).append("))       ");

                queryBuilder1.append(" ) ) AND USER_ID = '" ).append( userid ).append( "'  AND SESSION_ID ='" ).append(sessionId).append( "' ");
            
            
            }else if(method.equals("updateByDetailsId")){
              
                 String detailsId = (String) inputs[2];
                 String userid = (String) inputs[3];
                 String sessionId = (String) inputs[4];
                 
          
               queryBuilder1.append("  UPDATE ST_NM_SALES_PROJECTION_MASTER SET CHECK_RECORD='1' ");
            
               queryBuilder1.append("  where PROJECTION_DETAILS_SID in (").append(detailsId).append(")   AND USER_ID = '" ).append( userid ).append( "'  AND SESSION_ID ='" ).append(sessionId ).append( "'  ");
            
            
            
             }else if(method.equals("getCheckRecDetail")){
             
             String projectionId = String.valueOf(inputs[0]);
             String hierarchyNos = (String) inputs[1];
             String userid = (String) inputs[2];
             String sessionId = (String) inputs[3];
                 
             
   queryBuilder1.append("     SELECT  distinct CCP.HIERARCHY_NO as hierNo, min(CASE(CHECK_RECORD) WHEN 1 THEN 1 ELSE 0  END)  as checkRec   FROM   projection_details pd      \n");

   queryBuilder1.append(" JOIN (SELECT distinct LCCP.RELATIONSHIP_LEVEL_SID, LCCP.CCP_DETAILS_SID, LCCP.HIERARCHY_NO from     \n");

   queryBuilder1.append("  (SELECT CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID from     \n");

   queryBuilder1.append("  (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID     \n");

   queryBuilder1.append("  FROM RELATIONSHIP_LEVEL_DEFINITION RLD     \n");

   queryBuilder1.append(Constant.JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL);

    queryBuilder1.append(" JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID='" ).append( projectionId ).append( Constant.SPACE_NEW_LINE);

   queryBuilder1.append("  JOIN PROJECTION_MASTER PM  ON PD.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID     \n");

  queryBuilder1.append(Constant.WHERE_PM_PROJECTION_MASTER_SID ).append(projectionId).append( "' ) CCPMAP,     \n");

   queryBuilder1.append("  (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID     \n");

   queryBuilder1.append("  FROM RELATIONSHIP_LEVEL_DEFINITION RLD1     \n");

   queryBuilder1.append("   JOIN PROJECTION_CUST_HIERARCHY PCH     \n");

  queryBuilder1.append("   ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID     \n");

   queryBuilder1.append("  AND PCH.PROJECTION_MASTER_SID='" ).append(projectionId).append( Constant.SPACE_NEW_LINE);

   queryBuilder1.append("  ").append(hierarchyNos).append(" ) HLD     \n");
   
   queryBuilder1.append("  WHERE CCPMAP.HIERARCHY_NO like HLD.HIERARCHY_NO + '%' ) LCCP     \n");

   queryBuilder1.append("  WHERE LCCP.HIERARCHY_NO in     \n");

   queryBuilder1.append("  (SELECT RLD2.HIERARCHY_NO     \n");

  queryBuilder1.append("   FROM RELATIONSHIP_LEVEL_DEFINITION RLD2     \n");

   queryBuilder1.append("   JOIN PROJECTION_CUST_HIERARCHY PCH2     \n");

   queryBuilder1.append("  ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID     \n");

   queryBuilder1.append("  AND PCH2.PROJECTION_MASTER_SID='" ).append(projectionId).append( Constant.SPACE_NEW_LINE);

   queryBuilder1.append("  WHERE RLD2.LEVEL_NO like '%')) CCP ON CCP.CCP_DETAILS_SID=pd.CCP_DETAILS_SID     \n");
   

   queryBuilder1.append("  JOIN RELATIONSHIP_LEVEL_DEFINITION rld  ON  CCP.RELATIONSHIP_LEVEL_SID=rld.RELATIONSHIP_LEVEL_SID       \n");

    queryBuilder1.append(" JOIN ST_NM_SALES_PROJECTION_MASTER nm_mas  ON pd.PROJECTION_DETAILS_SID = nm_mas.PROJECTION_DETAILS_SID     \n");

   queryBuilder1.append("  JOIN  ST_NM_ACTUAL_SALES nm_ac ON nm_mas.PROJECTION_DETAILS_SID = nm_ac.PROJECTION_DETAILS_SID     \n");

    queryBuilder1.append(" JOIN PERIOD p ON p.period_sid = nm_ac.PERIOD_SID WHERE  pd.PROJECTION_MASTER_SID ='" ).append(projectionId).append( Constant.SPACE_NEW_LINE);

   queryBuilder1.append("  AND nm_mas.USER_ID = '" ).append( userid ).append( Constant.AND_NM_MAS_SESSION_ID ).append(sessionId ).append( Constant.SPACE_NEW_LINE);
   queryBuilder1.append("  AND nm_ac.USER_ID  = '" ).append( userid ).append( Constant.AND_NM_AC_SESSION_ID ).append(sessionId ).append( Constant.SPACE_NEW_LINE);
    queryBuilder1.append("   GROUP  BY  CCP.HIERARCHY_NO  \n");

    }else if (method.equals("saveCheckUncheckRecords")) {

                String projectionId = String.valueOf(inputs[0]);

                String filterValues = (String) inputs[1];
                String [] tempFilterValues=filterValues.split("-");
                
                String viewType = (String) inputs[2];
                String hierarchyNos = (String) inputs[3];
                String userid = (String) inputs[4];
                String sessionId = (String) inputs[5];
                boolean checkAll = (Boolean) inputs[6];
                String checkUncheck = (String) inputs[7];
                if (hierarchyNos.equals(Constant.WHERE)) {
                    hierarchyNos = " ";
                }
                
                

                queryBuilder1.append("        UPDATE ST_NM_SALES_PROJECTION_MASTER  SET  ");
                if(checkUncheck.equals(Constant.CHECK)){
                queryBuilder1.append(Constant.CHECK_RECORD_1);
                }else{
                   queryBuilder1.append(Constant.CHECK_RECORD_0);   
                 }
                queryBuilder1.append(Constant.WHERE_PROJECTION_DETAILS_SID_IN);
               
                
                queryBuilder1.append(Constant.SELECT_PROJECTION_DETAILS_SID_FROM_PROJ_DETAILS);

                queryBuilder1.append(Constant.SELECT_LCCP_DETAILS_SID_FROM);
                queryBuilder1.append(Constant.SELECT_CCP_MAP_CCP_DETAILS_SID_HLD_HIER);
                queryBuilder1.append(Constant.SELECT_RLD_RELATIONSHIP_LEVEL_VALUES_RL);
                queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD);
                queryBuilder1.append(Constant.JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL_SID);
                queryBuilder1.append(Constant.JOIN_PROJECTION_DETAILS_PD_ON_PD_CCP ).append(projectionId).append( Constant.SPACE_NEW_LINE);


                queryBuilder1.append(Constant.JOIN_PROJECTION_MASTER_PM_ON_PD_PROJECTION);
                queryBuilder1.append(Constant.WHERE_PM_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.CCP_MAP_NEW_LINE);
                queryBuilder1.append(Constant.SELECT_RL_D1_HIERARCHY_NO_RLD1_RELATIONSHIP);
                queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD1);

                if (viewType.equals("C")) {

                    queryBuilder1.append(Constant.JOIN_PROJECTION_CUST_HIERARCHY_PCH);

                } else {

                    queryBuilder1.append(Constant.JOIN_PROJECTION_PROD_HIERARCHY_PCH);

                }
                queryBuilder1.append(Constant.ON_PCH_RELATIONSHIP_LEVEL_SID_RL_D1_REL);
                queryBuilder1.append(Constant.AND_P_CH_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                if (!checkAll) {
                    queryBuilder1.append("    " ).append( hierarchyNos ).append( Constant.NEW_LINE);
                }
                queryBuilder1.append(Constant.HLD_WHERE_CCP_MAP_HIERARCHY_NO_LIKE_HLD);
                queryBuilder1.append(Constant.WHERE_L_CCP_HIERARCHY_NO_IN);
                queryBuilder1.append(Constant.SELECT_RL_D2_HIERARCHY_NO);
                queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD2);
                if (viewType.equals("C")) {

                    queryBuilder1.append(Constant.JOIN_PROJECTION_CUST_HIERARCHY_PCH2);

                } else {

                    queryBuilder1.append(Constant.JOIN_PROJECTION_PROD_HIERARCHY_PCH2);

                }

                queryBuilder1.append(Constant.ON_PCH2_RELATIONSHIP_LEVEL_SID_RLD2);
                queryBuilder1.append(Constant.AND_PC_H2_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(" ) ) )  AND USER_ID = '" ).append( userid ).append( Constant.COLON_AND_SESSION_ID ).append( sessionId ).append( Constant.SPACE_NEW_LINE);
                if (!tempFilterValues[0].equalsIgnoreCase(Constant.STRING_EMPTY)) {
                    queryBuilder1.append("    AND USER_GROUP= '" ).append(tempFilterValues[0]).append( Constant.SPACE_NEW_LINE);
                }
                if (!tempFilterValues[1].equalsIgnoreCase(Constant.STRING_EMPTY)) {
                    queryBuilder1.append("    and METHODOLOGY= '" ).append(tempFilterValues[1]).append( Constant.SPACE_NEW_LINE);
                }
                if (!tempFilterValues[2].equalsIgnoreCase(Constant.STRING_EMPTY)) {
                    queryBuilder1.append("    and CALCULATION_PERIODS= '" ).append(tempFilterValues[2]).append( Constant.SPACE_NEW_LINE);
                }

                
            }else if(method.equals("getCustomProjectDetailsId")){
                
                                     
                String projectionId = String.valueOf(inputs[0]);
                int levelNo = Integer.parseInt(String.valueOf(inputs[1]));
                String filterValues = (String) inputs[1];
                String [] tempFilterValues= filterValues.split("-");
                String checkUncheck = (String) inputs[7];

                String userid = (String) inputs[4];
                String sessionId = (String) inputs[5];
                String customId = (String) inputs[9];
                String hierarchyNo = (String) inputs[3];
                
                String parentViewType = (String) inputs[10];
                String lastCustomerHierarchyno=(String)inputs[11];
                String lastProductHierarchyno=(String) inputs[12];
                String customerLevel = "%";
                String productLevel = "%";
                String custHier = "%";
                String prodHier = "%";

              if (parentViewType.equals("C")) {
                    customerLevel = Integer.toString(levelNo);
                } else {
                    productLevel = Integer.toString(levelNo);
                }
              
              if(!lastCustomerHierarchyno.equals("%")){
              
               custHier = lastCustomerHierarchyno + "%";
              }else{
              
               prodHier = lastProductHierarchyno + "%";
              }
              
              
                queryBuilder1.append("    UPDATE ST_NM_SALES_PROJECTION_MASTER  SET  ");
                if(checkUncheck.equals(Constant.CHECK)){
                queryBuilder1.append(Constant.CHECK_RECORD_1);
                }else{
                   queryBuilder1.append(Constant.CHECK_RECORD_0);   
                 }
                queryBuilder1.append(Constant.WHERE_PROJECTION_DETAILS_SID_IN);
                
                queryBuilder1.append(Constant.SELECT_DISTINCT_PD_PROJECTION_DETAILS);
                queryBuilder1.append(Constant.FROM_PROJECTION_DETAILS_PD);
                queryBuilder1.append(Constant.JOIN_SELECT_DISTINCT_HLD ).append(parentViewType).append( Constant.RELATIONSHIP_LEVEL_SID_HLD ).append(parentViewType).append( Constant.HIERARCHY_NO_CCP_MAP_CCCP_DETAILS_SID_FROM);
                queryBuilder1.append(Constant.SELECT_RLD_RELATIONSHIP_LEVEL_VALUES);
                queryBuilder1.append(Constant.JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL);
                queryBuilder1.append(Constant.JOIN_PROJECTION_DETAILS_PD_ON_PD_CCP ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.CCP_MAP);
                queryBuilder1.append("   JOIN   \n");
                queryBuilder1.append(Constant.SELECT_RLD_RELATIONSHIP_LEVEL_VALUES);
                queryBuilder1.append(Constant.JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL);
                queryBuilder1.append(Constant.JOIN_PROJECTION_DETAILS_PD_ON_PD_CCP ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.CCP_MAP_ON_CCP_MAP_CCP_DETAILS_SID);
                queryBuilder1.append(Constant.JOIN_SELECT_RL_D2_HIERARCHY_NO_RLD);
                queryBuilder1.append(Constant.JOIN_DBO_CUSTOM_VIEW_MASTER_CVM_ON_CVDC ).append(customId).append( Constant.COLON_AND_CVD_LEVEL_NO_LIKE ).append(customerLevel).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.JOIN_DBO_HIERARCHY_LEVEL_DEFINITION_HLD);
                queryBuilder1.append(Constant.JOIN_RELATIONSHIP_LEVEL_DEFINITION_RLD2);
                queryBuilder1.append(Constant.JOIN_PROJECTION_CUST_HIERARCHY_PC_H2 ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.WHERE_RL_D2_HIERARCHY_NO_LIKE ).append(custHier).append( Constant.HLDC_ON_CCP_MAP_HIERARCHY_NO_LIKE_HLD);
                queryBuilder1.append(Constant.JOIN_SELECT_RL_D2_HIERARCHY_NO_RLD_REL_LEVEL);
                queryBuilder1.append(Constant.JOIN_DBO_CUSTOM_VIEW_MASTER_CVM_ON);
                queryBuilder1.append(Constant.CVD_CUSTOM_VIEW_MASTER_SID ).append(customId).append( Constant.COLON_AND_CVD_LEVEL_NO_LIKE ).append(productLevel).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.JOIN_DBO_HIERARCHY_LEVEL_DEFINITION_HLD);
                queryBuilder1.append(Constant.JOIN_RELATIONSHIP_LEVEL_DEFINITION_RLD2);
                queryBuilder1.append(Constant.JOIN_PROJECTION_PROD_HIERARCHY_PC_H2 ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.WHERE_RLD2_HIERARCHY_NO_LIKE ).append(prodHier).append( Constant.HLDP_ON_CCP_MAP_HIERARCHY_NO_LIKE_HLD);
                queryBuilder1.append(Constant.CCP_NEW_LINE);
                queryBuilder1.append(Constant.ON_CCP_DETAILS_SI_DPD_CCP_DETAILS_SID);
                queryBuilder1.append(Constant.JOIN_RELATIONSHIP_LEVEL_DEFINITION_RLD_JOIN_NM_SALES_PROJ);
                queryBuilder1.append(Constant.ON_PD_PROJECTION_DETAILS_SID_NM_MAS_PROJ_DETAILS_SID);
                queryBuilder1.append(Constant.JOIN_ST_NM_SALES_PROJECTION_NM_SP);
                queryBuilder1.append(Constant.ON_NM_MAS_PROJECTION_DETAILS_SID_NM_SP);
                queryBuilder1.append(Constant.JOIN_PERIOD_P_ON_PPERIOD_SID_NM_SP_PE ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.COLON_AND_NM_MAS_USER_ID ).append( userid ).append( Constant.AND_NM_MAS_SESSION_ID ).append( sessionId ).append( Constant.AND_NM_SP_USER_ID ).append( userid ).append( Constant.AND_NM_SP_SESSION_ID ).append( sessionId ).append( Constant.AND_RLD_HIERARCHY_NO).append(hierarchyNo).append(Constant.SPACE_NEW_LINE);

               queryBuilder1.append(Constant.CLOSE_BRACE_AND_USER_ID ).append( userid ).append( Constant.COLON_AND_SESSION_ID ).append( sessionId ).append( Constant.SPACE_NEW_LINE);
                  if (!tempFilterValues[0].equalsIgnoreCase(Constant.STRING_EMPTY)) {
                    queryBuilder1.append("    AND USER_GROUP= '" ).append( tempFilterValues[0] ).append( Constant.SPACE_NEW_LINE);
                }
                if (!tempFilterValues[1].equalsIgnoreCase(Constant.STRING_EMPTY)) {
                    queryBuilder1.append("    and METHODOLOGY= '" ).append( tempFilterValues[1] ).append( Constant.SPACE_NEW_LINE);
                }
                if (!tempFilterValues[2].equalsIgnoreCase(Constant.STRING_EMPTY)) {
                    queryBuilder1.append("    and CALCULATION_PERIODS= '" ).append(tempFilterValues[2]).append( Constant.SPACE_NEW_LINE);
                }
            }else if(method.equals("customRefresh")){
                
                
                
                    
                                     
                String projectionId = String.valueOf(inputs[0]);
                int levelNo = Integer.parseInt(String.valueOf(inputs[1]));
                String userid = (String) inputs[4];
                String sessionId = (String) inputs[5];
                String customId = (String) inputs[9];
                String hierarchyNo = (String) inputs[3];
                String parentViewType = (String) inputs[10];
                String lastCustomerHierarchyno=(String)inputs[11];
                String lastProductHierarchyno=(String) inputs[12];
                String customerLevel = "%";
                String productLevel = "%";
                String custHier = "%";
                String prodHier = "%";

              if (parentViewType.equals("C")) {
                    customerLevel = Integer.toString(levelNo);
                } else {
                    productLevel = Integer.toString(levelNo);
                }
              
              if(!lastCustomerHierarchyno.equals("%")){
              
               custHier = lastCustomerHierarchyno + "%";
              }else{
              
               prodHier = lastProductHierarchyno + "%";
              }
            
                queryBuilder1.append("   SELECT 0.0 as ACCOUNT_GROWTH,0.0 as PRODUCT_GROWTH,  \n");

                queryBuilder1.append("   sum(nm_ac.ACTUAL_SALES) as PROJECTION_SALES ,  sum(nm_ac.ACTUAL_UNITS) as PROJECTION_UNITS,0 as ACTUAL_SALES,  \n");
                queryBuilder1.append("   0 as ACTUAL_UNITS, max(rld.LEVEL_NO) as level_no,  rld.RELATIONSHIP_LEVEL_VALUES as rlv,     p.\"YEAR\" ,     p.QUARTER ,  \n");
                queryBuilder1.append("   min(nm_mas.USER_GROUP) as userGroup,min(nm_mas.CALCULATION_PERIODS) as baseLine,min(nm_mas.METHODOLOGY) as methodology,  \n");
                queryBuilder1.append("   rld.RELATIONSHIP_LEVEL_SID,CCP.HIERARCHY_NO,COUNT (*)  AS RCOUNT,1 as actualProj,sum(nm_ac.HISTORY_PROJECTION_SALES) as historySales ,   \n");
                queryBuilder1.append("   sum(nm_ac.HISTORY_PROJECTION_UNITS) as historyUnits ,  \n");

                queryBuilder1.append("   min(CASE(CHECK_RECORD) WHEN 1 THEN 1 ELSE 0  END)  AS checkrec,rld.LEVEL_Name as hierarchyLecel,   \n");
                queryBuilder1.append("   count(distinct case when  nm_mas.CALCULATION_PERIODS is null then '1' else  nm_mas.CALCULATION_PERIODS end)     AS calcPeriodCount,  \n");
                queryBuilder1.append("     count(distinct case when  nm_mas.METHODOLOGY is null then '1' else  nm_mas.METHODOLOGY end)     AS methoCount,  \n");
                queryBuilder1.append("   count(distinct case when  nm_mas.CALCULATION_BASED is null then '1' else  nm_mas.CALCULATION_BASED end)     AS calcBasedcount,  \n");
                queryBuilder1.append("   SUM(CASE(nm_mas.CHECK_RECORD) WHEN 1 THEN 0 ELSE 1 END) AS UNCHECK_COUNT         FROM   projection_details pd  \n");

                queryBuilder1.append(Constant.JOIN_SELECT_DISTINCT_HLD ).append(parentViewType).append( Constant.RELATIONSHIP_LEVEL_SID_HLD ).append(parentViewType).append( Constant.HIERARCHY_NO_CCP_MAP_CCCP_DETAILS_SID_FROM);
                queryBuilder1.append(Constant.SELECT_RLD_RELATIONSHIP_LEVEL_VALUES_RLD_HIER_NO);
                queryBuilder1.append(Constant.JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL);
                queryBuilder1.append(Constant.JOIN_PROJECTION_DETAILS_PD_ON_PD_CCP ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append("   ) CCPMAPC  JOIN  \n");
                queryBuilder1.append(Constant.SELECT_RLD_RELATIONSHIP_LEVEL_VALUES_RLD_HIER_NO);
                queryBuilder1.append(Constant.JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL);
                queryBuilder1.append(Constant.JOIN_PROJECTION_DETAILS_PD_ON_PD_CCP ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append("   ) CCPMAPP ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID  \n");
                queryBuilder1.append("   JOIN  (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD  \n");
                queryBuilder1.append(Constant.JOIN_DBO_CUSTOM_VIEW_MASTER_CVM_ON_CVDC ).append(customId).append( Constant.COLON_AND_CVD_LEVEL_NO_LIKE ).append(customerLevel).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.JOIN_DBO_HIERARCHY_LEVEL_DEFINITION_HLD);
                queryBuilder1.append(Constant.JOIN_RELATIONSHIP_LEVEL_DEFINITION_RLD2_ON_HLD_HIER);
                queryBuilder1.append(Constant.JOIN_PROJECTION_CUST_HIERARCHY_PC_H2 ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.WHERE_RL_D2_HIERARCHY_NO_LIKE ).append(custHier).append( "') HLDC ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%'  \n");
                queryBuilder1.append("   JOIN    (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD  \n");
                queryBuilder1.append("   JOIN dbo.CUSTOM_VIEW_MASTER CVM ON  \n");
                queryBuilder1.append(Constant.CVD_CUSTOM_VIEW_MASTER_SID ).append(customId).append( "' AND CVD.LEVEL_NO like  '" ).append(productLevel).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.JOIN_DBO_HIERARCHY_LEVEL_DEFINITION_HLD);
                queryBuilder1.append(Constant.JOIN_RELATIONSHIP_LEVEL_DEFINITION_RLD2_ON_HLD_HIER);
                queryBuilder1.append(Constant.JOIN_PROJECTION_PROD_HIERARCHY_PC_H2 ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append("   WHERE RLD2.HIERARCHY_NO like   '" ).append(prodHier).append( "'  ) HLDP ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%'  \n");
                queryBuilder1.append("   ) CCP  \n");

                queryBuilder1.append("   ON CCP.CCP_DETAILS_SID=pd.CCP_DETAILS_SID  \n");
                queryBuilder1.append("   JOIN RELATIONSHIP_LEVEL_DEFINITION rld  ON  CCP.RELATIONSHIP_LEVEL_SID=rld.RELATIONSHIP_LEVEL_SID    JOIN ST_NM_SALES_PROJECTION_MASTER nm_mas  \n");

                queryBuilder1.append("   ON pd.PROJECTION_DETAILS_SID = nm_mas.PROJECTION_DETAILS_SID  \n");

                queryBuilder1.append("   JOIN  ST_NM_ACTUAL_SALES nm_ac  \n");
                queryBuilder1.append("   ON nm_mas.PROJECTION_DETAILS_SID = nm_ac.PROJECTION_DETAILS_SID  \n");

                queryBuilder1.append("   JOIN PERIOD p ON p.period_sid = nm_ac.PERIOD_SID  \n");
                queryBuilder1.append("   WHERE  pd.PROJECTION_MASTER_SID =  '" ).append(projectionId).append( Constant.SPACE_NEW_LINE);

                queryBuilder1.append("   AND nm_mas.USER_ID = '" ).append( userid ).append( "' AND nm_mas.SESSION_ID ='" ).append( sessionId ).append( "'  AND nm_ac.USER_ID  = '" ).append( userid ).append( Constant.AND_NM_AC_SESSION_ID ).append( sessionId ).append( Constant.AND_RLD_HIERARCHY_NO ).append(hierarchyNo).append(Constant.SPACE_NEW_LINE);

                queryBuilder1.append("   GROUP  BY rld.RELATIONSHIP_LEVEL_VALUES,p.QUARTER, rld.RELATIONSHIP_LEVEL_SID ,rld.LEVEL_NO,p.\"YEAR\" ,rld.RELATIONSHIP_LEVEL_SID,CCP.HIERARCHY_NO,rld.LEVEL_NAME  \n");

                queryBuilder1.append("   Union  \n");
                queryBuilder1.append("      SELECT   avg(nm_sp.ACCOUNT_GROWTH),  \n");
                queryBuilder1.append("   avg(nm_sp.PRODUCT_GROWTH),   sum(nm_sp.PROJECTION_SALES),   sum(nm_sp.PROJECTION_UNITS),  \n");
                queryBuilder1.append("   0 as ACTUAL_SALES,    0 as ACTUAL_UNITS, max(rld.LEVEL_NO) as level_no,rld.RELATIONSHIP_LEVEL_VALUES as rlv,    p.\"YEAR\" ,     p.QUARTER,  \n");
                queryBuilder1.append("   min(nm_mas.USER_GROUP) as userGroup,min(nm_mas.CALCULATION_PERIODS) as baseLine,min(nm_mas.METHODOLOGY) as methodology,rld.RELATIONSHIP_LEVEL_SID ,  \n");
                queryBuilder1.append("   CCP.HIERARCHY_NO,COUNT (*)  AS RCOUNT ,0 as actualProj,null as historySales , null as historyUnits,  \n");
                queryBuilder1.append("   min(CASE(CHECK_RECORD) WHEN 1 THEN 1 ELSE 0  END)  AS checkrec,rld.LEVEL_Name as hierarchyLecel,       count(distinct case when  nm_mas.CALCULATION_PERIODS is null then '1' else  nm_mas.CALCULATION_PERIODS end)     AS calcPeriodCount,      count(distinct case  \n");
                queryBuilder1.append("   when  nm_mas.METHODOLOGY is null then '1' else  nm_mas.METHODOLOGY end)     AS methoCount,      \n");
                queryBuilder1.append("   count(distinct case when  nm_mas.CALCULATION_BASED is null then '1' else  nm_mas.CALCULATION_BASED end)     AS calcBasedcount,   \n");
                queryBuilder1.append("   SUM(CASE(nm_mas.CHECK_RECORD) WHEN 1 THEN 0 ELSE 1 END) AS UNCHECK_COUNT           FROM   projection_details pd  \n");

                queryBuilder1.append(Constant.JOIN_SELECT_DISTINCT_HLD ).append(parentViewType).append( Constant.RELATIONSHIP_LEVEL_SID_HLD ).append(parentViewType).append( Constant.HIERARCHY_NO_CCP_MAP_CCCP_DETAILS_SID_FROM);
                queryBuilder1.append(Constant.SELECT_RLD_RELATIONSHIP_LEVEL_VALUES_RLD_HIER_NO);
                queryBuilder1.append(Constant.JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL);
                queryBuilder1.append(Constant.JOIN_PROJECTION_DETAILS_PD_ON_PD_CCP ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append("   ) CCPMAPC  \n");
                queryBuilder1.append("   JOIN  \n");
                queryBuilder1.append(Constant.SELECT_RLD_RELATIONSHIP_LEVEL_VALUES_RLD_HIER_NO);
                queryBuilder1.append(Constant.JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL);
                queryBuilder1.append(Constant.JOIN_PROJECTION_DETAILS_PD_ON_PD_CCP ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append("   ) CCPMAPP ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID  \n");
                queryBuilder1.append("   JOIN  (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD  \n");
                queryBuilder1.append(Constant.JOIN_DBO_CUSTOM_VIEW_MASTER_CVM_ON_CVDC ).append(customId).append( Constant.COLON_AND_CVD_LEVEL_NO_LIKE ).append(customerLevel).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.JOIN_DBO_HIERARCHY_LEVEL_DEFINITION_HLD);
                queryBuilder1.append(Constant.JOIN_RELATIONSHIP_LEVEL_DEFINITION_RLD2_ON_HLD_HIER);
                queryBuilder1.append(Constant.JOIN_PROJECTION_CUST_HIERARCHY_PC_H2 ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.WHERE_RL_D2_HIERARCHY_NO_LIKE ).append(custHier).append( "') HLDC ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%'  \n");
                queryBuilder1.append("   JOIN    (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD  \n");
                queryBuilder1.append("   JOIN dbo.CUSTOM_VIEW_MASTER CVM ON  \n");
                queryBuilder1.append(Constant.CVD_CUSTOM_VIEW_MASTER_SID ).append(customId).append( "' AND CVD.LEVEL_NO like  '" ).append(productLevel).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.JOIN_DBO_HIERARCHY_LEVEL_DEFINITION_HLD);
                queryBuilder1.append(Constant.JOIN_RELATIONSHIP_LEVEL_DEFINITION_RLD2_ON_HLD_HIER);
                queryBuilder1.append(Constant.JOIN_PROJECTION_PROD_HIERARCHY_PC_H2 ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append("   WHERE RLD2.HIERARCHY_NO like   '" ).append(prodHier).append( "'  ) HLDP ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%'  \n");
                queryBuilder1.append("   ) CCP  \n");
                queryBuilder1.append("   ON CCP.CCP_DETAILS_SID=pd.CCP_DETAILS_SID  \n");
                queryBuilder1.append("   JOIN RELATIONSHIP_LEVEL_DEFINITION rld  ON  CCP.RELATIONSHIP_LEVEL_SID=rld.RELATIONSHIP_LEVEL_SID   JOIN ST_NM_SALES_PROJECTION_MASTER nm_mas  \n");
                queryBuilder1.append("   ON pd.PROJECTION_DETAILS_SID = nm_mas.PROJECTION_DETAILS_SID  \n");
                queryBuilder1.append(Constant.JOIN_ST_NM_SALES_PROJECTION_NM_SP);
                queryBuilder1.append("   ON nm_mas.PROJECTION_DETAILS_SID = nm_sp.PROJECTION_DETAILS_SID  \n");
                queryBuilder1.append(Constant.JOIN_PERIOD_P_ON_PPERIOD_SID_NM_SP_PE ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.COLON_AND_NM_MAS_USER_ID ).append( userid ).append( "' AND nm_mas.SESSION_ID =  '" ).append( sessionId ).append( Constant.AND_NM_SP_USER_ID ).append( userid ).append( "'  AND nm_sp.SESSION_ID =  '" ).append( sessionId ).append( Constant.AND_RLD_HIERARCHY_NO).append(hierarchyNo).append(Constant.SPACE_NEW_LINE);
                queryBuilder1.append("   GROUP  BY rld.RELATIONSHIP_LEVEL_VALUES,p.QUARTER ,rld.LEVEL_NO,p.\"YEAR\"  ,rld.RELATIONSHIP_LEVEL_SID,CCP.HIERARCHY_NO ,rld.LEVEL_NAME  order by rld.RELATIONSHIP_LEVEL_VALUES,p.\"YEAR\",p.QUARTER  \n");



            }else if (method.equals("getProjectionDetId")) {

                String projectionId = String.valueOf(inputs[0]);
                String hierarchyNos = (String) inputs[3];
            
          
                if (hierarchyNos.equals(Constant.WHERE)) {
                    hierarchyNos = " ";
                }
          
           
                queryBuilder1.append(Constant.SELECT_PROJECTION_DETAILS_SID_FROM_PROJ_DETAILS);


                queryBuilder1.append(Constant.SELECT_LCCP_DETAILS_SID_FROM);
                queryBuilder1.append(Constant.SELECT_CCP_MAP_CCP_DETAILS_SID_HLD_HIER);
                queryBuilder1.append(Constant.SELECT_RLD_RELATIONSHIP_LEVEL_VALUES_RL);
                queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD);
                queryBuilder1.append(Constant.JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL_SID);
                queryBuilder1.append(Constant.JOIN_PROJECTION_DETAILS_PD_ON_PD_CCP ).append(projectionId).append( Constant.SPACE_NEW_LINE);

                queryBuilder1.append(Constant.JOIN_PROJECTION_MASTER_PM_ON_PD_PROJECTION);
                queryBuilder1.append(Constant.WHERE_PM_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.CCP_MAP_NEW_LINE);
                queryBuilder1.append(Constant.SELECT_RL_D1_HIERARCHY_NO_RLD1_RELATIONSHIP);
                queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD1);

                 queryBuilder1.append(Constant.JOIN_PROJECTION_CUST_HIERARCHY_PCH);

               
                queryBuilder1.append(Constant.ON_PCH_RELATIONSHIP_LEVEL_SID_RL_D1_REL);
                queryBuilder1.append(Constant.AND_P_CH_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.SPACE_NEW_LINE);
              
                queryBuilder1.append("    " ).append( hierarchyNos ).append( Constant.NEW_LINE);
               
                queryBuilder1.append(Constant.HLD_WHERE_CCP_MAP_HIERARCHY_NO_LIKE_HLD);
                queryBuilder1.append(Constant.WHERE_L_CCP_HIERARCHY_NO_IN);
                queryBuilder1.append(Constant.SELECT_RL_D2_HIERARCHY_NO);
                queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD2);
                

                queryBuilder1.append(Constant.JOIN_PROJECTION_CUST_HIERARCHY_PCH2);

                

                queryBuilder1.append(Constant.ON_PCH2_RELATIONSHIP_LEVEL_SID_RLD2);
                queryBuilder1.append(Constant.AND_PC_H2_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                
                
                queryBuilder1.append(" ) )   \n");


                } else if (method.equals("getZeroAc")) {
                 boolean custom=(Boolean)inputs[7];
if(!custom){
                String projectionId = String.valueOf(inputs[0]);
                String viewType = (String) inputs[4];
                String hierarchyNos = (String) inputs[6];
                queryBuilder1.append("   select ISNULL(SUM(AM.SALES_AMOUNT),0) as sales from ACTUALS_MASTER AM     \n");
                queryBuilder1.append("   join CCP_DETAILS CCPD on CCPD.COMPANY_MASTER_SID=AM.COMPANY_MASTER_SID    \n");
                queryBuilder1.append("   and CCPD.CONTRACT_MASTER_SID=AM.CONTRACT_MASTER_SID     \n");
                queryBuilder1.append("   and CCPD.ITEM_MASTER_SID=AM.ITEM_MASTER_SID    \n");
                queryBuilder1.append("   AND AM.QUANTITY_INCLUSION='Y'     \n");
                queryBuilder1.append("   AND AM.ACCRUAL_ACTUAL_START_DATE>=(SELECT Dateadd(yy, Datediff(yy, 0, Getdate()) - 3, 0))     \n");
                queryBuilder1.append("   AND AM.ACCRUAL_ACTUAL_END_DATE<=(SELECT Dateadd(DD,-1,Dateadd(qq, Datediff(qq, 0, Getdate()) , 0)))     \n");
                
                queryBuilder1.append("   join PROJECTION_DETAILS PRD on PRD.CCP_DETAILS_SID=CCPD.CCP_DETAILS_SID     \n");
                
                queryBuilder1.append("   and PRD.PROJECTION_DETAILS_SID in (    \n");
                queryBuilder1.append(Constant.SELECT_PROJECTION_DETAILS_SID_FROM_PROJ_DETAILS);
                queryBuilder1.append(Constant.SELECT_LCCP_DETAILS_SID_FROM);
                queryBuilder1.append(Constant.SELECT_CCP_MAP_CCP_DETAILS_SID_HLD_HIER);
                queryBuilder1.append(Constant.SELECT_RLD_RELATIONSHIP_LEVEL_VALUES_RL);
                queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD);
                queryBuilder1.append(Constant.JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL_SID);
                queryBuilder1.append(Constant.JOIN_PROJECTION_DETAILS_PD_ON_PD_CCP ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.JOIN_PROJECTION_MASTER_PM_ON_PD_PROJECTION);
                queryBuilder1.append(Constant.WHERE_PM_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.CCP_MAP_NEW_LINE);
                queryBuilder1.append(Constant.SELECT_RL_D1_HIERARCHY_NO_RLD1_RELATIONSHIP);
                queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD1);
                if (viewType.equals("C")) {
                 queryBuilder1.append(Constant.JOIN_PROJECTION_CUST_HIERARCHY_PCH);
                } else {
                queryBuilder1.append(Constant.JOIN_PROJECTION_PROD_HIERARCHY_PCH);
                  }
                queryBuilder1.append(Constant.ON_PCH_RELATIONSHIP_LEVEL_SID_RL_D1_REL);
                queryBuilder1.append(Constant.AND_P_CH_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.BLANK_QUOTES ).append( hierarchyNos ).append( Constant.HLD_NEW_LINE);
                queryBuilder1.append(Constant.WHERE_CCP_MAP_HIERARCHY_NO_LIKE_HLD_HIER);
                queryBuilder1.append(Constant.WHERE_L_CCP_HIERARCHY_NO_IN);
                queryBuilder1.append(Constant.SELECT_RL_D2_HIERARCHY_NO);
                queryBuilder1.append(Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD2);
                if (viewType.equals("C")) {
                 queryBuilder1.append(Constant.JOIN_PROJECTION_CUST_HIERARCHY_PCH2);
                } else {
                queryBuilder1.append(Constant.JOIN_PROJECTION_PROD_HIERARCHY_PCH2);
                 }
                queryBuilder1.append(Constant.ON_PCH2_RELATIONSHIP_LEVEL_SID_RLD2);
                queryBuilder1.append(Constant.AND_PC_H2_PROJECTION_MASTER_SID ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                 queryBuilder1.append("  )  ) )  \n");
}else{

                        
                String projectionId = String.valueOf(inputs[0]);
                int levelNo = Integer.parseInt(String.valueOf(inputs[1]));
                String userid = (String) inputs[4];
                String sessionId = (String) inputs[5];
                String customId = (String) inputs[9];
                String hierarchyNo = (String) inputs[3];
                
                String parentViewType = (String) inputs[10];
                String lastCustomerHierarchyno=(String)inputs[11];
                String lastProductHierarchyno=(String) inputs[12];
                String customerLevel = "%";
                String productLevel = "%";
                String custHier = "%";
                String prodHier = "%";

              if (parentViewType.equals("C")) {
                    customerLevel = Integer.toString(levelNo);
                } else {
                    productLevel = Integer.toString(levelNo);
                }
              
              if(!lastCustomerHierarchyno.equals("%")){
              
               custHier = lastCustomerHierarchyno + "%";
              }else{
              
               prodHier = lastProductHierarchyno + "%";
              }
              
              
                queryBuilder1.append("   select count( distinct PRD.PROJECTION_DETAILS_SID) from ACTUALS_MASTER AM     \n");
                queryBuilder1.append("   join CCP_DETAILS CCPD on CCPD.COMPANY_MASTER_SID=AM.COMPANY_MASTER_SID    \n");
                queryBuilder1.append("   and CCPD.CONTRACT_MASTER_SID=AM.CONTRACT_MASTER_SID     \n");
                queryBuilder1.append("   and CCPD.ITEM_MASTER_SID=AM.ITEM_MASTER_SID    \n");
                queryBuilder1.append("   join PROJECTION_DETAILS PRD on PRD.CCP_DETAILS_SID=CCPD.CCP_DETAILS_SID     \n");
                queryBuilder1.append("   and PRD.PROJECTION_DETAILS_SID in (    \n");
                
                queryBuilder1.append(Constant.SELECT_DISTINCT_PD_PROJECTION_DETAILS);
                queryBuilder1.append(Constant.FROM_PROJECTION_DETAILS_PD);
                queryBuilder1.append(Constant.JOIN_SELECT_DISTINCT_HLD ).append(parentViewType).append( Constant.RELATIONSHIP_LEVEL_SID_HLD ).append(parentViewType).append( Constant.HIERARCHY_NO_CCP_MAP_CCCP_DETAILS_SID_FROM);
                queryBuilder1.append(Constant.SELECT_RLD_RELATIONSHIP_LEVEL_VALUES);
                queryBuilder1.append(Constant.JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL);
                queryBuilder1.append(Constant.JOIN_PROJECTION_DETAILS_PD_ON_PD_CCP ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.CCP_MAP);
               

                queryBuilder1.append("   JOIN   \n");
                queryBuilder1.append(Constant.SELECT_RLD_RELATIONSHIP_LEVEL_VALUES);
                queryBuilder1.append(Constant.JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL);
                queryBuilder1.append(Constant.JOIN_PROJECTION_DETAILS_PD_ON_PD_CCP ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.CCP_MAP_ON_CCP_MAP_CCP_DETAILS_SID);
                queryBuilder1.append(Constant.JOIN_SELECT_RL_D2_HIERARCHY_NO_RLD);
                queryBuilder1.append(Constant.JOIN_DBO_CUSTOM_VIEW_MASTER_CVM_ON_CVDC ).append(customId).append( Constant.COLON_AND_CVD_LEVEL_NO_LIKE ).append(customerLevel).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.JOIN_DBO_HIERARCHY_LEVEL_DEFINITION_HLD);
                queryBuilder1.append(Constant.JOIN_RELATIONSHIP_LEVEL_DEFINITION_RLD2);
                queryBuilder1.append(Constant.JOIN_PROJECTION_CUST_HIERARCHY_PC_H2 ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.WHERE_RL_D2_HIERARCHY_NO_LIKE ).append(custHier).append( Constant.HLDC_ON_CCP_MAP_HIERARCHY_NO_LIKE_HLD);
                queryBuilder1.append(Constant.JOIN_SELECT_RL_D2_HIERARCHY_NO_RLD_REL_LEVEL);
                queryBuilder1.append(Constant.JOIN_DBO_CUSTOM_VIEW_MASTER_CVM_ON);
                queryBuilder1.append(Constant.CVD_CUSTOM_VIEW_MASTER_SID ).append(customId).append( Constant.COLON_AND_CVD_LEVEL_NO_LIKE ).append(productLevel).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.JOIN_DBO_HIERARCHY_LEVEL_DEFINITION_HLD);
                queryBuilder1.append(Constant.JOIN_RELATIONSHIP_LEVEL_DEFINITION_RLD2);
                queryBuilder1.append(Constant.JOIN_PROJECTION_PROD_HIERARCHY_PC_H2 ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.WHERE_RLD2_HIERARCHY_NO_LIKE ).append(prodHier).append( Constant.HLDP_ON_CCP_MAP_HIERARCHY_NO_LIKE_HLD);
                queryBuilder1.append(Constant.CCP_NEW_LINE);
                queryBuilder1.append(Constant.ON_CCP_DETAILS_SI_DPD_CCP_DETAILS_SID);
                queryBuilder1.append(Constant.JOIN_RELATIONSHIP_LEVEL_DEFINITION_RLD_JOIN_NM_SALES_PROJ);
                queryBuilder1.append(Constant.ON_PD_PROJECTION_DETAILS_SID_NM_MAS_PROJ_DETAILS_SID);
                queryBuilder1.append(Constant.JOIN_ST_NM_SALES_PROJECTION_NM_SP);
                queryBuilder1.append(Constant.ON_NM_MAS_PROJECTION_DETAILS_SID_NM_SP);
                queryBuilder1.append(Constant.JOIN_PERIOD_P_ON_PPERIOD_SID_NM_SP_PE ).append(projectionId).append( Constant.SPACE_NEW_LINE);
                queryBuilder1.append(Constant.COLON_AND_NM_MAS_USER_ID ).append( userid ).append( Constant.AND_NM_MAS_SESSION_ID ).append( sessionId ).append( Constant.AND_NM_SP_USER_ID ).append( userid ).append( Constant.AND_NM_SP_SESSION_ID ).append( sessionId ).append( Constant.AND_RLD_HIERARCHY_NO).append(hierarchyNo).append(Constant.SPACE_NEW_LINE);

               queryBuilder1.append("  )   \n");

}
                
            }

            if (!method.equals("saveSalesRec")) {
                if (method.equals("fetchSalesResult") || method.equals("getLevelFilterValues") || method.equals("fetchByHierarchyNo") || method.equals("loadGroupValues") 
                        || method.equals("getLevelIndex")||method.equals("getCount")||method.equals("refreshData")

                        ||method.equals("getCheckedRecords")||method.equals("getCheckRecDetail")||method.equals("customRefresh")||method.equals("getProjectionDetId")||method.equals("getZeroAc")) {
                    list = HelperTableLocalServiceUtil.executeSelectQuery(queryBuilder1.toString());

                } else {
                    int result = HelperTableLocalServiceUtil.executeUpdateQueryCount(queryBuilder1.toString());
                    list.add(result);
                }

            }

        } catch (NumberFormatException ex) {
            LOGGER.error(StringUtils.EMPTY,ex);
            LOGGER.error(queryBuilder1.toString());

        } 

        return list;

    }
                                                       
    public List getAssumptionResult(List input, String queryName) {
        List list = new ArrayList();
        
        StringBuilder sql = new StringBuilder(SQlUtil.getQuery(getClass(),queryName));

        try {
            if ("update".equals(input.get(0))) {
                for (int i = 1; i < input.size(); i++) {
                    sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(input.get(i)));
                }
                HelperTableLocalServiceUtil.executeUpdateQuery(sql.toString());
            } else {
                for (Object temp : input) {
                    sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
                }
                
                list = HelperTableLocalServiceUtil.executeSelectQuery(sql.toString());
            }

        } catch (Exception ex) {
            LOGGER.error(StringUtils.EMPTY,ex);
            LOGGER.error(sql.toString());
        } 

        return list;
    }
    
    public List getSalesProjectionResults(Object[] inputs) {
        List list = new ArrayList();
        String projectionId = String.valueOf(inputs[0]);
        String frequency = String.valueOf(inputs[1]);
        int levelNo = Integer.parseInt(String.valueOf(inputs[2]));
        String viewType = String.valueOf(inputs[3]);
        String hierarchyNo = String.valueOf(inputs[5]);
        String indicator = String.valueOf(inputs[6]);
        String userId = String.valueOf(inputs[7]);
        String sessionId = String.valueOf(inputs[8]);
        StringBuilder queryBuilder = new StringBuilder();
        
        try {
            queryBuilder.append("select H.LEVEL_NO, H.RELATIONSHIP_LEVEL_VALUES, I.\"YEAR\" \n");
            if (Constant.QUARTERLY.equalsIgnoreCase(frequency)) {
                queryBuilder.append(Constant.I_QUARTER);
            } else if (Constant.MONTHLY.equalsIgnoreCase(frequency)) {
                queryBuilder.append(Constant.COMMA_I_MONTH);
            } else if (Constant.SEMI_ANNUALY.equalsIgnoreCase(frequency)) {
                queryBuilder.append(Constant.I_SEMI_ANNUAL);
            } else {
                queryBuilder.append(",NULL as Group_Value\n");
            }
            queryBuilder.append(" ,sum(A.ACTUAL_SALES)as ACTUAL_SALES,sum(A.ACTUAL_UNITS)as ACTUAL_UNITS,sum(A.HISTORY_PROJECTION_SALES) as HISTORY_PROJECTION_SALES,sum(A.HISTORY_PROJECTION_UNITS) as HISTORY_PROJECTION_UNITS ");
            queryBuilder.append(",NULL as PROJECTION_SALES,NULL as PROJECTION_UNITS ");
            queryBuilder.append(" from ST_NM_ACTUAL_SALES A,  ST_NM_SALES_PROJECTION_MASTER B, PROJECTION_DETAILS E , PROJECTION_MASTER F, ");
            if ("customer".equalsIgnoreCase(viewType) || "c".equalsIgnoreCase(indicator)) {
                queryBuilder.append(Constant.PROJECTION_CUST_HIERARCHY_G);
            } else if ("Product".equalsIgnoreCase(viewType) || "p".equalsIgnoreCase(indicator)) {
                queryBuilder.append(Constant.PROJECTION_PROD_HIERARCHY_G);
            }
            queryBuilder.append("RELATIONSHIP_LEVEL_DEFINITION H, \"PERIOD\" I ,\n");
            queryBuilder.append("(SELECT distinct LCCP.RELATIONSHIP_LEVEL_SID, LCCP.CCP_DETAILS_SID, LCCP.HIERARCHY_NO from \n");
            queryBuilder.append("(SELECT CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID from \n");
            queryBuilder.append("(SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n");
            queryBuilder.append("FROM RELATIONSHIP_LEVEL_DEFINITION RLD JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n");
            queryBuilder.append("JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=").append(projectionId);
            queryBuilder.append(" JOIN PROJECTION_CUST_HIERARCHY PCH1 ON  RLD.RELATIONSHIP_LEVEL_SID=PCH1.RELATIONSHIP_LEVEL_SID \n");
            queryBuilder.append("JOIN PROJECTION_MASTER PM  ON PCH1.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID \n");
            queryBuilder.append("WHERE PM.PROJECTION_MASTER_SID= ").append(projectionId).append(")CCPMAP, \n");
            queryBuilder.append("(SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID FROM RELATIONSHIP_LEVEL_DEFINITION RLD1 JOIN PROJECTION_CUST_HIERARCHY PCH \n");
            queryBuilder.append("ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID AND PCH.PROJECTION_MASTER_SID= ").append(projectionId);
            queryBuilder.append(" WHERE RLD1.HIERARCHY_NO like '").append(hierarchyNo).append("%' ) HLD WHERE CCPMAP.HIERARCHY_NO like HLD.HIERARCHY_NO + '%' ) LCCP\n");
            queryBuilder.append(" WHERE LCCP.HIERARCHY_NO in (SELECT RLD2.HIERARCHY_NO FROM RELATIONSHIP_LEVEL_DEFINITION RLD2 JOIN PROJECTION_CUST_HIERARCHY PCH2 \n");
            queryBuilder.append("ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID= ").append(projectionId);
            queryBuilder.append(" WHERE RLD2.LEVEL_NO=").append(levelNo).append(")) CCP");
            queryBuilder.append(" where A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID and B.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID ");
            queryBuilder.append("and A.USER_ID = '").append(userId).append(Constant.COLON_AND_ASESSION_ID).append(sessionId);
            queryBuilder.append(Constant.AND_BUSER_ID).append(userId).append(Constant.AND_BSESSION_ID).append(sessionId);
            queryBuilder.append("' and E.PROJECTION_MASTER_SID = F.PROJECTION_MASTER_SID and F.PROJECTION_MASTER_SID =").append(projectionId);
            queryBuilder.append(Constant.AND_G_PROJECTION_MASTER_SID_PROJECTION);
            queryBuilder.append(" and E.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID and H.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID ");
            queryBuilder.append(Constant.AND_A_PERIOD_SID_IPERIOD_SID_AND_HLEVEL).append(levelNo);
            queryBuilder.append(" group by H.LEVEL_NO , H.RELATIONSHIP_LEVEL_VALUES ,H.LEVEL_NAME , I.\"YEAR\" \n");
            if (Constant.QUARTERLY.equalsIgnoreCase(frequency)) {
                queryBuilder.append(Constant.I_QUARTER);
            } else if (Constant.MONTHLY.equalsIgnoreCase(frequency)) {
                queryBuilder.append(Constant.COMMA_I_MONTH);
            } else if (Constant.SEMI_ANNUALY.equalsIgnoreCase(frequency)) {
                queryBuilder.append(Constant.I_SEMI_ANNUAL);
            }
            queryBuilder.append(" Union  select H.LEVEL_NO, H.RELATIONSHIP_LEVEL_VALUES, I.\"YEAR\" \n");
            if (Constant.QUARTERLY.equalsIgnoreCase(frequency)) {
                queryBuilder.append(Constant.I_QUARTER);
            } else if (Constant.MONTHLY.equalsIgnoreCase(frequency)) {
                queryBuilder.append(Constant.COMMA_I_MONTH);
            } else if (Constant.SEMI_ANNUALY.equalsIgnoreCase(frequency)) {
                queryBuilder.append(Constant.I_SEMI_ANNUAL);
            } else {
                queryBuilder.append(",NULL as Group_Value\n");
            }
            queryBuilder.append(",NULL as ACTUAL_SALES,NULL as ACTUAL_UNITS,NULL as HISTORY_PROJECTION_SALES,NULL as HISTORY_PROJECTION_UNITS");
            queryBuilder.append(",sum(A.PROJECTION_SALES) as PROJECTION_SALES,sum(A.PROJECTION_UNITS) as PROJECTION_UNITS ");
            queryBuilder.append("from ST_NM_SALES_PROJECTION A,  ST_NM_SALES_PROJECTION_MASTER B, PROJECTION_DETAILS E , PROJECTION_MASTER F,");
            if ("customer".equalsIgnoreCase(viewType) || "c".equalsIgnoreCase(indicator)) {
                queryBuilder.append(Constant.PROJECTION_CUST_HIERARCHY_G);
            } else if ("product".equalsIgnoreCase(viewType) || "p".equalsIgnoreCase(indicator)) {
                queryBuilder.append(Constant.PROJECTION_PROD_HIERARCHY_G);
            }
            queryBuilder.append("RELATIONSHIP_LEVEL_DEFINITION H, \"PERIOD\" I ,\n");
            queryBuilder.append("(SELECT distinct LCCP.RELATIONSHIP_LEVEL_SID, LCCP.CCP_DETAILS_SID, LCCP.HIERARCHY_NO from \n");
            queryBuilder.append("(SELECT CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID from \n");
            queryBuilder.append("(SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n");
            queryBuilder.append("FROM RELATIONSHIP_LEVEL_DEFINITION RLD JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n");
            queryBuilder.append("JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=").append(projectionId);
            queryBuilder.append(" JOIN PROJECTION_CUST_HIERARCHY PCH1 ON  RLD.RELATIONSHIP_LEVEL_SID=PCH1.RELATIONSHIP_LEVEL_SID \n");
            queryBuilder.append("JOIN PROJECTION_MASTER PM  ON PCH1.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID \n");
            queryBuilder.append("WHERE PM.PROJECTION_MASTER_SID=").append(projectionId).append(") CCPMAP,\n");
            queryBuilder.append("(SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID FROM RELATIONSHIP_LEVEL_DEFINITION RLD1 JOIN PROJECTION_CUST_HIERARCHY PCH \n");
            queryBuilder.append("ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID AND PCH.PROJECTION_MASTER_SID=").append(projectionId);
            queryBuilder.append(" WHERE RLD1.HIERARCHY_NO like '").append(hierarchyNo).append("%' ) HLD WHERE CCPMAP.HIERARCHY_NO like HLD.HIERARCHY_NO + '%' ) LCCP WHERE LCCP.HIERARCHY_NO in ");
            queryBuilder.append("(SELECT RLD2.HIERARCHY_NO FROM RELATIONSHIP_LEVEL_DEFINITION RLD2 JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID ");
            queryBuilder.append("AND PCH2.PROJECTION_MASTER_SID=").append(projectionId).append("WHERE RLD2.LEVEL_NO=").append(levelNo).append(")) CCP");
            queryBuilder.append("");
            queryBuilder.append(" where A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID");
            queryBuilder.append(" and A.USER_ID = '").append(userId).append(Constant.COLON_AND_ASESSION_ID).append(sessionId);
            queryBuilder.append(Constant.AND_BUSER_ID).append(userId).append(Constant.AND_BSESSION_ID).append(sessionId);
            queryBuilder.append("' and B.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID and E.PROJECTION_MASTER_SID = F.PROJECTION_MASTER_SID");
            queryBuilder.append(" and F.PROJECTION_MASTER_SID =").append(projectionId);
            queryBuilder.append(" and G.PROJECTION_MASTER_SID = F.PROJECTION_MASTER_SID and G.RELATIONSHIP_LEVEL_SID = H.RELATIONSHIP_LEVEL_SID\n");
            queryBuilder.append(" and E.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID and H.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n");
            queryBuilder.append(Constant.AND_A_PERIOD_SID_IPERIOD_SID_AND_HLEVEL).append(levelNo);
            queryBuilder.append(" group by H.LEVEL_NO , H.RELATIONSHIP_LEVEL_VALUES  , I.\"YEAR\" \n");
            if (Constant.QUARTERLY.equalsIgnoreCase(frequency)) {
                queryBuilder.append(Constant.I_QUARTER);
            } else if (Constant.MONTHLY.equalsIgnoreCase(frequency)) {
                queryBuilder.append(Constant.COMMA_I_MONTH);
            } else if (Constant.SEMI_ANNUALY.equalsIgnoreCase(frequency)) {
                queryBuilder.append(Constant.I_SEMI_ANNUAL);
            }
           
            list =HelperTableLocalServiceUtil.executeSelectQuery(queryBuilder.toString()) ;
        } catch (Exception ex) {
            LOGGER.error(StringUtils.EMPTY,ex);
            LOGGER.error(queryBuilder.toString());
        } 
        return list;
    }
    
    public List getSalesProjectionResultLevels(Object[] inputs) {
        List list = new ArrayList();
        String projectionId = String.valueOf(inputs[0]);
        int levelNo = Integer.parseInt(String.valueOf(inputs[2]));
        String viewType = String.valueOf(inputs[3]);
        String indicator = String.valueOf(inputs[6]);
        StringBuilder queryBuilder = new StringBuilder();
        String userId = String.valueOf(inputs[7]);
        String sessionId = String.valueOf(inputs[8]);
        
        try {
            queryBuilder.append("select H.LEVEL_NO, H.RELATIONSHIP_LEVEL_VALUES ,H.HIERARCHY_NO");
            queryBuilder.append(" from ST_NM_ACTUAL_SALES A,  ST_NM_SALES_PROJECTION_MASTER B, PROJECTION_DETAILS E , PROJECTION_MASTER F, ");
            if ((Constants.CUSTOMER).equalsIgnoreCase(viewType) || "c".equalsIgnoreCase(indicator)) {
                queryBuilder.append(Constant.PROJECTION_CUST_HIERARCHY_G);
            } else if ((Constants.PRODUCT).equalsIgnoreCase(viewType) || "p".equalsIgnoreCase(indicator)) {
                queryBuilder.append(Constant.PROJECTION_PROD_HIERARCHY_G);
            }
            queryBuilder.append("RELATIONSHIP_LEVEL_DEFINITION H, \"PERIOD\" I ");
            queryBuilder.append("where A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID and B.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID ");
            queryBuilder.append("and A.USER_ID = '").append(userId).append(Constant.COLON_AND_ASESSION_ID).append(sessionId);
            queryBuilder.append(Constant.AND_BUSER_ID).append(userId).append(Constant.AND_BSESSION_ID).append(sessionId);
            queryBuilder.append("' and E.PROJECTION_MASTER_SID = F.PROJECTION_MASTER_SID and F.PROJECTION_MASTER_SID =").append(projectionId);
            queryBuilder.append(Constant.AND_G_PROJECTION_MASTER_SID_PROJECTION);
            queryBuilder.append(Constant.AND_A_PERIOD_SID_IPERIOD_SID_AND_HLEVEL).append(levelNo);
            queryBuilder.append(" group by H.LEVEL_NO , H.RELATIONSHIP_LEVEL_VALUES ,H.LEVEL_NAME,H.HIERARCHY_NO");
            queryBuilder.append(" Union  select H.LEVEL_NO, H.RELATIONSHIP_LEVEL_VALUES,H.HIERARCHY_NO");
            queryBuilder.append(" from ST_NM_SALES_PROJECTION A,  ST_NM_SALES_PROJECTION_MASTER B, PROJECTION_DETAILS E , PROJECTION_MASTER F,");
            if ((Constants.CUSTOMER).equalsIgnoreCase(viewType) || "c".equalsIgnoreCase(indicator)) {
                queryBuilder.append(Constant.PROJECTION_CUST_HIERARCHY_G);
            } else if ((Constants.PRODUCT).equalsIgnoreCase(viewType) || "p".equalsIgnoreCase(indicator)) {
                queryBuilder.append(Constant.PROJECTION_PROD_HIERARCHY_G);
            }
            queryBuilder.append("RELATIONSHIP_LEVEL_DEFINITION H, \"PERIOD\" I where A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID");
            queryBuilder.append(" and B.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID and E.PROJECTION_MASTER_SID = F.PROJECTION_MASTER_SID");
            queryBuilder.append(" and A.USER_ID = '").append(userId).append(Constant.COLON_AND_ASESSION_ID).append(sessionId);
            queryBuilder.append(Constant.AND_BUSER_ID).append(userId).append(Constant.AND_BSESSION_ID).append(sessionId);
            queryBuilder.append(" ' and F.PROJECTION_MASTER_SID =").append(projectionId);
            queryBuilder.append(Constant.AND_G_PROJECTION_MASTER_SID_PROJECTION);
            queryBuilder.append(Constant.AND_A_PERIOD_SID_IPERIOD_SID_AND_HLEVEL).append(levelNo);
            queryBuilder.append(" group by H.LEVEL_NO , H.RELATIONSHIP_LEVEL_VALUES ,H.HIERARCHY_NO");
            
            list = HelperTableLocalServiceUtil.executeSelectQuery(queryBuilder.toString());
        } catch (Exception e) {
            LOGGER.error(StringUtils.EMPTY,e);
            LOGGER.error(queryBuilder.toString());
        } 
        return list;
    }

    public List getVarianceSales(int projectionId, String frequency, List<Integer> startAndEndPeriods,  String parentName, String year, int levelNo, String sales) {
        
        String customQuery = "";
        try {
            String selectClause = " select H.LEVEL_NO, H.LEVEL_NAME, H.RELATIONSHIP_LEVEL_VALUES, I.\"YEAR\" , ";
            String whereClause = "";
            String groupBy = ",H.LEVEL_NAME , I.\"YEAR\"";
            String selectedHistoryFrequency = "";
            String selectedFutureFrequency = "";

            int startFreq = 0;
            int endFreq = 0;
            int startYear = 0;
            int endYear = 0;

            if (startAndEndPeriods != null && !startAndEndPeriods.isEmpty()) {
                startFreq = startAndEndPeriods.get(0);
                endFreq = startAndEndPeriods.get(1);
                startYear = startAndEndPeriods.get(2);
                endYear = startAndEndPeriods.get(3);
            }
            if (CommonUtils.isInteger(year)) {

                whereClause += " and I.\"YEAR\" = " + year;
            }
            if (frequency.contains(QUARTERLY.getConstant())) {
                selectedHistoryFrequency = "QUARTER = " + startFreq + Constant.SPACE_AND_SPACE;
                selectedFutureFrequency = "QUARTER = " + endFreq + Constant.SPACE_AND_SPACE;
                selectClause += "I.QUARTER, ";
                whereClause += "";
                groupBy += ", I.QUARTER";
            } else if (frequency.contains(SEMI_ANNUAL.getConstant())) {
                selectedHistoryFrequency = "SEMI_ANNUAL = " + startFreq + Constant.SPACE_AND_SPACE;
                selectedFutureFrequency = "SEMI_ANNUAL = " + endFreq + Constant.SPACE_AND_SPACE;
                selectClause += "I.SEMI_ANNUAL, ";
                whereClause += "";
                groupBy += ", I.SEMI_ANNUAL";
            } else if (frequency.contains(ANNUAL.getConstant())) {
                selectClause += "'' as ANNUAL, ";
                whereClause += "";
                groupBy += "";

            } else if (frequency.contains(MONTHLY.getConstant())) {
                selectedHistoryFrequency = "\"MONTH\" = " + startFreq + Constant.SPACE_AND_SPACE;
                selectedFutureFrequency = "\"MONTH\" = " + endFreq + Constant.SPACE_AND_SPACE;
                selectClause += "I.\"MONTH\", ";
                whereClause += "";
                groupBy += ", I.\"MONTH\"";
            }

            String periodFilter = "";
            if (!CommonUtils.isInteger(year)) {
                periodFilter = " and  I.PERIOD_SID in (SELECT period_sid FROM \"PERIOD\" WHERE PERIOD_DATE>=(SELECT TOP 1 PERIOD_DATE FROM \"PERIOD\" WHERE " + selectedHistoryFrequency + " YEAR=" + startYear + " ORDER BY PERIOD_SID)"
                        + "AND PERIOD_DATE<=(SELECT TOP 1 PERIOD_DATE FROM \"PERIOD\" WHERE " + selectedFutureFrequency + " YEAR=" + endYear + " ORDER BY PERIOD_SID DESC))";
            }

            String parentNode = parentName;
            String customSql = "  ST_NM_SALES_PROJECTION_MASTER B, PROJECTION_DETAILS E , PROJECTION_MASTER F, PROJECTION_CUST_HIERARCHY G, RELATIONSHIP_LEVEL_DEFINITION H, \"PERIOD\" I, "
                    + "where A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID and B.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID and "
                    + "E.PROJECTION_MASTER_SID = F.PROJECTION_MASTER_SID and F.PROJECTION_MASTER_SID = " + projectionId + " and G.PROJECTION_MASTER_SID = F.PROJECTION_MASTER_SID "
                    + "and G.RELATIONSHIP_LEVEL_SID = H.RELATIONSHIP_LEVEL_SID and A.PERIOD_SID = I.PERIOD_SID " + periodFilter;
            if (!parentName.equals(StringUtils.EMPTY) || !StringUtils.EMPTY.equalsIgnoreCase(parentName)) {
                customSql += " and H.LEVEL_NO = " + levelNo + " and H.PARENT_NODE = '" + parentNode + "'";
            }

            customSql += whereClause + " group by H.LEVEL_NO , H.RELATIONSHIP_LEVEL_VALUES " + groupBy;

            String historyQuery = selectClause + " sum(A.ACTUAL_" + sales + ")as ACTUAL_" + sales + ", sum(A.HISTORY_PROJECTION_" + sales + ") as PROJECTION_" + sales + " from ST_NM_ACTUAL_SALES A," + customSql;
            String futureQuery = selectClause + " NULL as ACTUAL_" + sales + ", sum(A.PROJECTION_" + sales + ") as PROJECTION_" + sales + " from ST_NM_SALES_PROJECTION A," + customSql;

            customQuery = historyQuery + " Union " + futureQuery;
            return HelperTableLocalServiceUtil.executeSelectQuery(customQuery);
        } catch (Exception ex) {
            LOGGER.error(StringUtils.EMPTY,ex);
            LOGGER.error(customQuery);
            return Collections.emptyList();
        } 
    }
}
