/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.projectionVariance.queryUtils;

import com.stpl.app.cff.dto.PVSelectionDTO;
import com.stpl.app.cff.logic.CommonLogic;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.Constants;
import com.stpl.app.cff.util.StringConstantsUtil;
import com.stpl.app.cff.util.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.BooleanConstant;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Mohamed.hameed
 */
public class PVQueryUtils {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PVQueryUtils.class);
    
    public static final String CCP_CCP = " @CCP CCP ";
    public static final String GROUP_BY = " group by ";
    public static final String IQUARTER = ", I.QUARTER";
    public static final String CASE_WHEN_P = ", CASE WHEN P";
    
    public String getCCPQuery(PVSelectionDTO projSelDTO, int projectionId) {
        String viewtable = CommonLogic.getViewTableName(projSelDTO.getHierarchyIndicator());
        String ccpQuery = StringUtils.EMPTY;
         ccpQuery = ccpQuery + " (SELECT LCCP.RELATIONSHIP_LEVEL_SID, LCCP.CCP_DETAILS_SID, LCCP.HIERARCHY_NO from "
                + "(SELECT CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID from "
                + "(SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID "
                + "FROM RELATIONSHIP_LEVEL_DEFINITION RLD "
                + "JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID "
                + "JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projectionId
                + "JOIN PROJECTION_MASTER PM  ON PD.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID"
                + " WHERE PM.PROJECTION_MASTER_SID=" + projectionId + ") CCPMAP,"
                + " (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD1"
                + " JOIN  " + viewtable + " PCH "
                + " ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID"
                + " AND PCH.PROJECTION_MASTER_SID=" + projectionId
                + " WHERE RLD1.HIERARCHY_NO like '" + projSelDTO.getHierarchyNo() + "%' ) HLD"
                + " WHERE CCPMAP.HIERARCHY_NO like HLD.HIERARCHY_NO+'%' ) LCCP"
                + " WHERE LCCP.HIERARCHY_NO in"
                + " (SELECT RLD2.HIERARCHY_NO"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD2"
                + " JOIN  " + viewtable + " PCH2"
                + " ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID"
                + " AND PCH2.PROJECTION_MASTER_SID= " + projectionId
                + " WHERE RLD2.LEVEL_NO  = " + projSelDTO.getLevelNo() + ")) CCP ";
        return ccpQuery;
    }

    public String getCCPGeneralQuery(PVSelectionDTO projSelDTO, int projectionId) {
        String viewtable = CommonLogic.getViewTableName(projSelDTO.getHierarchyIndicator());
        String ccpQuery = StringUtils.EMPTY;
         ccpQuery = ccpQuery + " SELECT LCCP.RELATIONSHIP_LEVEL_SID, LCCP.CCP_DETAILS_SID, LCCP.HIERARCHY_NO from "
                + "(SELECT CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID from "
                + "(SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID "
                + "FROM RELATIONSHIP_LEVEL_DEFINITION RLD "
                + "JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID "
                + "JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projectionId
                + " JOIN PROJECTION_MASTER PM  ON PD.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID"
                + " WHERE PM.PROJECTION_MASTER_SID=" + projectionId + ") CCPMAP,"
                + " (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD1"
                + "  JOIN " + viewtable + " PCH "
                + " ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID"
                + " AND PCH.PROJECTION_MASTER_SID= " + projectionId
                + " WHERE  RLD1.HIERARCHY_NO like '" + projSelDTO.getHierarchyNo() + "%' ) HLD"
                + " WHERE CCPMAP.HIERARCHY_NO like HLD.HIERARCHY_NO+'%' ) LCCP"
                + " WHERE LCCP.HIERARCHY_NO in"
                + " (SELECT RLD2.HIERARCHY_NO"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD2"
                + "  JOIN " + viewtable + " PCH2"
                + " ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID"
                + " AND PCH2.PROJECTION_MASTER_SID=" + projectionId
                + " WHERE RLD2.LEVEL_NO=" + projSelDTO.getLevelNo() + ") ";
        return ccpQuery;
    }

    public String getUserSessionQueryCondition(int userId, int sessionId, String table) {
        String user = StringUtils.EMPTY;
         user = user + StringConstantsUtil.SMALL_AND + table + ".USER_ID=" + userId + StringConstantsUtil.SMALL_AND + table + ".SESSION_ID=" + sessionId + " ";
        return user;
    }

    public String getProjectionResultsPivotQuery(PVSelectionDTO projSelDTO, boolean isPriorDiscount) {
        projSelDTO.setIsTotal(true);
        String selectClause = StringConstantsUtil.SPACE_SELECT;
        selectClause += "Isnull(TODIS.YEARS ,SALEPPA.YEARS ) as YEARS, Isnull(TODIS.PERIODS ,SALEPPA.PERIODS ) AS PERIODS,";
        selectClause += getPivotSelectClause();
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, isPriorDiscount);
        String salesQuery = getSalesQuery(getProjectionResultsSalesQuery(projSelDTO));
        String ppaQuery = getPPAQuery(getPPAProjectionResultsQuery(projSelDTO));
        String customQuery = StringUtils.EMPTY;
         customQuery = customQuery + selectClause + "  from  " + getPivotSelectQuery(totalDiscountQuery, salesQuery, ppaQuery);
        return customQuery;
    }

    public String getPVMainDiscountDolQuery(PVSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(false);
        String selectClause = " select C.YEARS as YEARS, C.PERIODS AS  PERIODS,C.DISCOUNTS AS DISCOUNTS,";
        StringBuilder selectClauseBuilder = new StringBuilder();
        selectClauseBuilder.append(selectClause);
        StringBuilder customQuery = new StringBuilder();
        String orderBy = " DISCOUNTS, YEARS,PERIODS ";
        String projectedSales = "PROJECTION_SALES";
        if (projSelDTO.getGroup().contains(StringConstantsUtil.VALUE_LABEL)) {
            selectClauseBuilder.append("C." ).append( projectedSales ).append( " AS C_PROJECTION_SALES");
        } else if (projSelDTO.getGroup().contains(StringConstantsUtil.VARIANCE_LABEL)) {
            selectClauseBuilder.append("0  AS C_PROJECTION_SALES");
        } else {
            selectClauseBuilder.append("0  AS C_PROJECTION_SALES");
        }
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            if (projSelDTO.getGroup().contains(StringConstantsUtil.VALUE_LABEL)) {
                selectClauseBuilder.append(", P" ).append( i ).append( '.' ).append( projectedSales ).append( " AS P" ).append( i ).append( "_PROJECTION_SALES  ");
            } else if (projSelDTO.getGroup().contains(StringConstantsUtil.VARIANCE_LABEL)) {
                selectClauseBuilder.append( ", (IsNull(C." ).append( projectedSales ).append( ", 0)- IsNull(P" ).append( i ).append( '.' ).append( projectedSales ).append( ", 0)) AS P" ).append( i ).append( "_PROJECTION_SALES ");
            } else {
                selectClauseBuilder.append( CASE_WHEN_P ).append( i ).append( '.' ).append( projectedSales ).append( " = 0 THEN  0\n")
                        .append( " ELSE ( IsNull(C." ).append( projectedSales ).append( ", 0) -  IsNull (P" ).append( i ).append( '.' ).append( projectedSales ).append( ", 0) / P" ).append( i ).append( '.' ).append( projectedSales ).append( " )  \n")
                        .append( " END   AS  P" ).append( i ).append( "_PROJECTION_SALES ");
            }

        }
        selectClause = selectClauseBuilder.toString();
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        projSelDTO.setCurrentOrPrior("C");
        projSelDTO.setIsPrior(false);
        customQuery.append(selectClause ).append( " from  \n(" ).append( getProjectionResultsDiscountsQuery(projSelDTO, BooleanConstant.getFalseFlag()) ).append( ") C\n ");
        projSelDTO.setCurrentOrPrior("P");
        projSelDTO.setIsPrior(true);
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            projSelDTO.setProjectionId(projSelDTO.getProjIdList().get(i));
            customQuery.append(" LEFT JOIN  (\n" ).append( getProjectionResultsDiscountsQuery(projSelDTO, BooleanConstant.getTrueFlag()) ).append( "\n) " )
                    .append( 'P' ).append( i ).append( " \n on C.DISCOUNTS=P" ).append( i ).append( ".DISCOUNTS \n ")
                    .append( " AND C.YEARS = P" ).append( i ).append( ".YEARS \n ")
                    .append( " and C.PERIODS=P" ).append( i ).append( ".PERIODS  ");
        }
        customQuery.append(" order by " ).append( orderBy);
        projSelDTO.setIsPrior(false);
        projSelDTO.setCurrentOrPrior("C");
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        return customQuery.toString();
    }

    public String getPVMainDiscountPerQuery(PVSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(false);
        String selectClause = " select C.YEARS as YEARS,  C.PERIODS AS PERIODS,C.DISCOUNTS AS DISCOUNTS,";
        StringBuilder selectClauseBuilder = new StringBuilder();
        selectClauseBuilder.append(selectClause);
        StringBuilder customQueryBuilder = new StringBuilder();
        String orderBy = " DISCOUNTS, YEARS,PERIODS ";
        String projectedSales;
        if (projSelDTO.isRPU()) {
            projectedSales = "RPU ";
        } else {
            projectedSales = "PROJECTION_RATE ";
        }
        if (projSelDTO.getGroup().contains(StringConstantsUtil.VALUE_LABEL)) {
            selectClauseBuilder.append("C." ).append( projectedSales ).append( " AS C_PROJECTION_RATE");
        } else if (projSelDTO.getGroup().contains(StringConstantsUtil.VARIANCE_LABEL)) {
            selectClauseBuilder.append("0  AS PROJECTION_RATE");
        } else {
            selectClauseBuilder.append( "0  AS PROJECTION_RATE");
        }
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            if (projSelDTO.getGroup().contains(StringConstantsUtil.VALUE_LABEL)) {
                selectClauseBuilder .append( ", P" ).append( i ).append( '.' ).append( projectedSales ).append( " AS P" ).append( i ).append( '_' ).append( projectedSales ).append( ' ');
            } else if (projSelDTO.getGroup().contains(StringConstantsUtil.VARIANCE_LABEL)) {
                selectClauseBuilder.append( ", (IsNull(C." ).append( projectedSales ).append( ", 0)- IsNull(P" ).append( i ).append( '.' ).append( projectedSales ).append( ", 0)) AS P" ).append( i ).append( '_' ).append( projectedSales ).append( ' ');
            } else {
                selectClauseBuilder.append( CASE_WHEN_P ).append( i ).append( '.' ).append( projectedSales ).append( " = 0 THEN 0\n")
                        .append( " ELSE (IsNull(C." ).append( projectedSales ).append( ", 0) - IsNull(P" ).append( i ).append( '.' ).append( projectedSales ).append( ", 0) / P" ).append( i ).append( '.' ).append( projectedSales ).append( ") \n")
                        .append( " END   AS  P" ).append( i ).append( '_' ).append( projectedSales ).append( ' ');
            }
        }
        selectClause = selectClauseBuilder.toString();
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        projSelDTO.setCurrentOrPrior("C");
        projSelDTO.setIsPrior(false);
        customQueryBuilder.append(selectClause ).append( " from  \n(" ).append( getProjectionResultsDiscountsPerQuery(projSelDTO) ).append( ") C \n ");
        projSelDTO.setCurrentOrPrior("P");
        projSelDTO.setIsPrior(true);
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            projSelDTO.setProjectionId(projSelDTO.getProjIdList().get(i));
            customQueryBuilder.append(" LEFT JOIN  (\n" ).append( getProjectionResultsDiscountsPerQuery(projSelDTO) )
                    .append("\n) " ).append( 'P' ).append( i ).append( " \n on C.DISCOUNTS=P" ).append( i ).append( ".DISCOUNTS  \n ")
                    .append( " AND C.YEARS = P" ).append( i ).append( ".YEARS \n ")
                    .append( " and C.PERIODS= P" ).append( i ).append( ".PERIODS  ");
        }
        customQueryBuilder.append(" order by " ).append( orderBy);
        projSelDTO.setIsPrior(false);
        projSelDTO.setCurrentOrPrior("C");
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        return customQueryBuilder.toString();
    }

    public String getPeriodDiscountExpand(PVSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(false);
        String selectClause = " select C.YEARS as YEARS, C.PERIODS AS PERIODS,C.DISCOUNTS AS DISCOUNTS,";
        StringBuilder customQuery = new StringBuilder();
        String orderBy = " DISCOUNTS , YEARS,PERIODS ";
        String projectedSales;
        String projectedReturns;
        String projectionPPA;
        projSelDTO.setSales("SALES");
        if (projSelDTO.getDiscountGroupName().contains("Discount $")) {
            projectedSales = "PROJECTION_SALES";
            projectedReturns = "RETURNS_PROJECTED_AMOUNT";
            projectionPPA = "PPA_DISCOUNT_AMOUNT";
            if (projSelDTO.getDiscountGroupName().contains(StringConstantsUtil.VALUE_LABEL)) {
                selectClause += "C." + projectedSales + " AS C_PROJECTION_AMNT,";
                selectClause += "C." + projectedReturns + " AS C_RETURNS_AMNT,";
                selectClause += "C." + projectionPPA + " AS C_PPA_AMNT";
            } else {
                selectClause += "0  AS PROJECTION_AMNT,";
                selectClause += "0  AS RETURNS_AMN,";
                selectClause += "0  AS PPA_AMN";
            } 
        } else if (projSelDTO.getDiscountGroupName().contains("Discount %")) {
            projSelDTO.setSales("RATE");
            projectedSales = "PROJECTION_RATE";
            projectedReturns = "RETURNS_PROJECTED_RATE";
            projectionPPA = "PPA_DISCOUNT_PERCENT";
            if (projSelDTO.getDiscountGroupName().contains(StringConstantsUtil.VALUE_LABEL)) {
                selectClause += "C." + projectedSales + " AS C_PROJECTION_RATE,";
                selectClause += "C." + projectedReturns + " AS C_RETURNS_RATE,";
                selectClause += "C." + projectionPPA + " AS C_PPA_RATE";
            } else  {
                selectClause += "0  AS PROJECTION_RATE,";
                selectClause += "0  AS RETURNS_RATE,";
                selectClause += "0  AS PPA_RATE";
            } 
        } else {
            projectedSales = "RPU";
            projectedReturns = "RETURNS_RPU";
            projectionPPA = "PPA_DISCOUNT_RPU";
            if (projSelDTO.getDiscountGroupName().contains(StringConstantsUtil.VALUE_LABEL)) {
                selectClause += "C." + projectedSales + " AS C_RPU,";
                selectClause += "C." + projectedReturns + " AS C_RETURNS,";
                selectClause += "C." + projectionPPA + " AS C_PPA";
            }  else {
                selectClause += "0  AS C_RPU,";
                selectClause += "0  AS C_RETURNS,";
                selectClause += "0  AS C_PPA";
            }
        }
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            if (projSelDTO.getGroup().contains(StringConstantsUtil.VALUE_LABEL)) {
                selectClause = selectClause.concat(", P").concat(String.valueOf(i)).concat(".").concat(projectedSales).concat("  AS P").concat(String.valueOf(i)).concat("_").concat(projectedSales).concat(" ");
                selectClause = selectClause.concat(", P").concat(String.valueOf(i)).concat(".").concat(projectedReturns).concat(" AS  P").concat(String.valueOf(i)).concat("_").concat(projectedReturns).concat(" ");
                selectClause = selectClause.concat(", P").concat(String.valueOf(i)).concat(".").concat(projectionPPA).concat(" AS  P").concat(String.valueOf(i)).concat("_").concat(projectionPPA).concat(" ");
            } else if (projSelDTO.getGroup().contains(StringConstantsUtil.VARIANCE_LABEL)) {
                selectClause = selectClause.concat(", (IsNull (C.").concat(projectedSales).concat(", 0) - IsNull(P").concat(String.valueOf(i)).concat("." + projectedSales).concat(", 0))  AS  P").concat(String.valueOf(i)).concat("_").concat(projectedSales).concat(" ");
                selectClause = selectClause.concat(",  (IsNull(C.").concat(projectedReturns).concat(", 0)  - IsNull(P").concat(String.valueOf(i)).concat(".").concat(projectedReturns).concat(", 0))  AS P").concat(String.valueOf(i)).concat("_").concat(projectedReturns).concat(" ");
                selectClause = selectClause.concat(",  (IsNull(C.").concat(projectionPPA).concat(", 0 )- IsNull(P").concat(String.valueOf(i)).concat(".").concat(projectionPPA).concat(", 0))  AS P").concat(String.valueOf(i)).concat("_").concat(projectionPPA).concat(" ");
            } else {
                selectClause = selectClause.concat(CASE_WHEN_P).concat(String.valueOf(i)).concat(".").concat(projectedSales).concat(" = 0 THEN 0\n")
                        .concat(" ELSE  (IsNull(C.").concat(projectedSales).concat(", 0)  - IsNull(P").concat(String.valueOf(i)).concat(".").concat(projectedSales).concat(", 0))  / P").concat(String.valueOf(i)).concat(".").concat(projectedSales).concat(" \n")
                        .concat(" END  AS P").concat(String.valueOf(i)).concat("_").concat(projectedSales).concat(" ");
                selectClause = selectClause.concat(CASE_WHEN_P).concat(String.valueOf(i)).concat(".").concat(projectedSales).concat(" = 0  THEN 0\n")
                        .concat(" ELSE  (IsNull(C.").concat(projectedReturns).concat(", 0) -  IsNull(P").concat(String.valueOf(i)).concat(".").concat(projectedReturns).concat(", 0))  / P").concat(String.valueOf(i)).concat(".").concat(projectedSales).concat(" \n")
                        .concat(" END   AS P" ).concat(String.valueOf(i)).concat("_").concat(projectedReturns).concat(" ");
                selectClause = selectClause.concat(CASE_WHEN_P).concat(String.valueOf(i)).concat(".").concat(projectionPPA).concat(" = 0  THEN 0\n")
                        .concat(" ELSE (IsNull(C.").concat(projectionPPA).concat(", 0) -  IsNull(P").concat(String.valueOf(i)).concat(".").concat(projectionPPA).concat(", 0)) / P").concat(String.valueOf(i)).concat(".").concat(projectionPPA).concat(" \n")
                        .concat(" END   AS P").concat(String.valueOf(i)).concat("_").concat(projectionPPA).concat(" ");
            }
        }
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        projSelDTO.setCurrentOrPrior("C");
        projSelDTO.setIsPrior(false);
        customQuery.append(selectClause ).append( "  from  \n(" ).append( getProjectionResultsDiscountsPerQuery(projSelDTO) ).append( ")  C\n ");
        projSelDTO.setCurrentOrPrior("P");
        projSelDTO.setIsPrior(true);
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            projSelDTO.setProjectionId(projSelDTO.getProjIdList().get(i));
            customQuery.append(" LEFT  JOIN  (\n" ).append( getProjectionResultsDiscountsPerQuery(projSelDTO) ).append( "\n) " ).append( 'P' ).append( i ).append( "  \n on C.DISCOUNTS=P" ).append( i ).append( ".DISCOUNTS \n  ")
                    .append( " AND C.YEARS=P" ).append( i ).append( ".YEARS  \n ")
                    .append( " and C.PERIODS =P" ).append( i ).append( ".PERIODS   ");
        }
        customQuery.append(" order  by " ).append( orderBy);
        projSelDTO.setIsPrior(false);
        projSelDTO.setCurrentOrPrior("C");
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        return customQuery.toString();
    }

    public String getProjectionResultsMainPivotQuery(PVSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(true);
        String selectClause = StringConstantsUtil.SPACE_SELECT;
        String customQuery = "";
        String orderBy = " YEARS,PERIODS ";
        selectClause += "C.YEARS as YEARS, C.PERIODS AS PERIODS,";

        customQuery = selectClause + getPivotMainSelectClause("C");
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            customQuery = customQuery.concat(", ").concat(getPivotMainSelectClause("P" + i));
        }
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        projSelDTO.setCurrentOrPrior("C");
        projSelDTO.setIsPrior(false);
        customQuery += "  from  \n(" + getProjectionResultsPivotQuery(projSelDTO, BooleanConstant.getFalseFlag()) + ")  C\n ";
        projSelDTO.setCurrentOrPrior("P");
        projSelDTO.setIsPrior(true);
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            projSelDTO.setProjectionId(projSelDTO.getProjIdList().get(i));
            customQuery = customQuery.concat(" LEFT  JOIN  (\n").concat(getProjectionResultsPivotQuery(projSelDTO, BooleanConstant.getTrueFlag())).concat("\n) ").concat("P").concat(String.valueOf(i)).concat(" \n").concat(getPivotMainWhereCond("P").concat(String.valueOf(i)));
        }
        customQuery += "  order by " + orderBy;
        projSelDTO.setIsPrior(false);
        projSelDTO.setCurrentOrPrior("C");
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        return customQuery;
    }

    public String getPivotSelectQuery(String query1, String query2, String query3) {
        String finalWhereCond = " on TODIS.YEARS=SALEPPA.YEARS AND TODIS.PERIODS=SALEPPA.PERIODS";
        String finalWhereCond1 = " ON TODIS.YEARS=PPA.YEARS AND TODIS.PERIODS=PPA.PERIODS";
        String selectClause = StringUtils.EMPTY;
         selectClause = selectClause + " (\n" + query1 + "\n) TODIS FULL JOIN (\n" + query2 + "\n) SALEPPA \n " + finalWhereCond + " FULL JOIN (\n" + query3 + "\n) PPA" + finalWhereCond1;
        return selectClause;
    }

    public String getPivotMainWhereCond(String table) {
        return " on C.YEARS=" + table + ".YEARS AND C.PERIODS=" + table + ".PERIODS \n";
    }

    public String getPivotSelectClause() {
        String selectClause = StringUtils.EMPTY;
        selectClause = selectClause + " SALEPPA.SALES_PROJECTION_SALES as CONTRACT_PROJECTION_SALES,"
                + "SALEPPA.PROJECTION_UNITS as CONTRACT_PROJECTION_UNITS,"
                + " CASE WHEN SALEPPA.sales_projection_sales = 0 THEN 0 ELSE (((Isnull(TODIS.projection_sales, 0)) +ISNULL(PPA.SALES_PPA,0) + ISNULL(SALEPPA.PROJECTED_RATE, 0)) / NULLIF(SALEPPA.sales_projection_sales,0))*100 END AS TOTAL_PROJECTION_RATE, "
                + " (Isnull(TODIS.projection_sales, 0) +ISNULL(PPA.SALES_PPA,0) + ISNULL(SALEPPA.RETURNS_PROJECTED, 0)) AS TOTAL_PROJECTION_DOLAR,"
                + "(Isnull(SALEPPA.SALES_PROJECTION_SALES, 0) - (Isnull(TODIS.PROJECTION_SALES, 0)+ ISNULL(PPA.SALES_PPA, 0))) AS NET_PROJECTION_SALES, "
                + " CASE WHEN SALEPPA.projection_units = 0 THEN 0 ELSE(IsNull((IsNull(TODIS.projection_sales, 0) +ISNULL(PPA.SALES_RPU,0) +IsNull(SALEPPA.RETURNS_PROJECTED, 0)) / NULLIF(SALEPPA.PROJECTION_UNITS, 0), 0)) END AS RPU,\n"
                + " COGS_PROJECTED = ISNULL(SALEPPA.COGS_PROJECTED, 0),\n"
                + " NET_PROFIT_PROJECTED = ((Isnull(SALEPPA.SALES_PROJECTION_SALES, 0) - (Isnull(TODIS.PROJECTION_SALES, 0)+ ISNULL(PPA.SALES_PPA, 0))) - ISNULL(SALEPPA.COGS_PROJECTED, 0))";
        return selectClause;
    }

    public String getPivotMainSelectClause(String projName) {
        String selectClause = " " + projName + ".CONTRACT_PROJECTION_SALES as " + projName + "CONTRACT_PROJECTION_SALES,"
                + " " + projName + ".CONTRACT_PROJECTION_UNITS as " + projName + "CONTRACT_PROJECTION_UNITS,"
                + " " + projName + ".TOTAL_PROJECTION_RATE AS " + projName + "TOTAL_PROJECTION_RATE, "
                + " " + projName + ".TOTAL_PROJECTION_DOLAR as " + projName + "TOTAL_PROJECTION_DOLAR,"
                + " " + projName + ".NET_PROJECTION_SALES AS " + projName + "NET_PROJECTION_SALES, "
                + " " + projName + ".RPU AS " + projName + "RPU, "
                + " " + projName + ".COGS_PROJECTED AS " + projName + "COGC, "
                + " " + projName + ".NET_PROFIT_PROJECTED AS " + projName + "NET_PROFIT ";
        LOGGER.debug("selectClause ={}",selectClause);
        return selectClause;
    }

    public String getProjectionResultsDiscountsQuery(PVSelectionDTO projSelDTO, boolean isPriorDiscount) {
        String mainMasterTable = "  NM_DISCOUNT_PROJ_MASTER ";
        String tempMasterTable = " ST_NM_DISCOUNT_PROJ_MASTER ";
        String mainTable = " NM_DISCOUNT_PROJECTION ";
        String tempTable = " ST_NM_DISCOUNT_PROJECTION ";
        String masterTable;
        String table;
        if (!projSelDTO.isIsPrior()) {
            masterTable = tempMasterTable;
            table = tempTable;
        } else {
            masterTable = mainMasterTable;
            table = mainTable;
        }
        String selectClause = StringConstantsUtil.SPACE_SELECT;
        String whereClause = "";
        String groupBy = " I.\"YEAR\"";

        String customQuery = "";
        String ccpWhereCond = getCCPWhereConditionQuery("E", "CCP");
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER as PERIODS, ";
            whereClause += "";
            groupBy += IQUARTER;
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as  PERIODS, ";
            whereClause += "";
            groupBy += ",  I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS, ";
            whereClause += "";
            groupBy += "";

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += "I.\"MONTH\" as PERIODS, ";
            whereClause += "";
            groupBy += ",  I.\"MONTH\"";
        }

        // To filter the data according to selected period
        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        // To handle the scenario where any discount is not selected in program selection lookup
        String discountTypeColumnName = "'Total discounts' as DISCOUNTS";
        if (projSelDTO.getDiscountLevel().equals("Program Category")) {
            if (projSelDTO.getDiscountNameList() != null && !projSelDTO.getDiscountNameList().isEmpty()) {
                if (!projSelDTO.isIsTotal()) {
                    discountTypeColumnName = " B.PRICE_GROUP_TYPE AS DISCOUNTS";
                    groupBy += ", " + " B.PRICE_GROUP_TYPE";
                }
                whereClause += " and B.PRICE_GROUP_TYPE in (" + CommonUtils.collectionToString(projSelDTO.getDiscountNameList(), true) + ")";
            }
        } else {
            if (projSelDTO.getDiscountNoList() != null && !projSelDTO.getDiscountNoList().isEmpty()) {
                if (!projSelDTO.isIsTotal()) {
                    discountTypeColumnName = " J.RS_NAME as DISCOUNTS";
                    groupBy += ", " + " J.RS_NAME";
                }
                if(!isPriorDiscount){
                    whereClause += " and B.RS_MODEL_SID in (" + CommonUtils.collectionToString(projSelDTO.getDiscountNoList(), false) + ")";
                }
            }
        }
        selectClause += discountTypeColumnName + ", ";

        String customSql = masterTable
                + " B, PROJECTION_DETAILS E , "
                + "  \"PERIOD\" I, "                
                + CCP_CCP;
        if (!projSelDTO.isIsTotal()) {
            customSql += ", RS_MODEL J ";
        }
        customSql += "where A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID and B.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID  ";
        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase("C")) {
            customSql += getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "B")
                    + getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "A");
        }
        if (!projSelDTO.isIsTotal()) {
            customSql += "and B.RS_MODEL_SID= J.RS_MODEL_SID ";
        }
        customSql += "and A.RS_MODEL_SID =  B.RS_MODEL_SID "
                + " and E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId()
                + ccpWhereCond + "and A.PERIOD_SID = I.PERIOD_SID " + periodFilter
                + whereClause + GROUP_BY + groupBy;

        String futureQuery = selectClause + " sum(A.PROJECTION_SALES) as PROJECTION_SALES, Sum(A.PROJECTION_RPU) AS RPU from "
                + table + " A," + customSql;
        customQuery = futureQuery;
        return customQuery;
    }

    public String getProjectionResultsSalesQuery(PVSelectionDTO projSelDTO) {
        String mainMasterSalesTable = "  NM_SALES_PROJECTION_MASTER ";
        String tempMasterSalesTable = " ST_NM_SALES_PROJECTION_MASTER ";
        String mainSalesTable = " NM_SALES_PROJECTION ";
        String tempSalesTable = " ST_NM_SALES_PROJECTION ";

        String masterSalesTable;
        String salesTable;

        if (!projSelDTO.isIsPrior()) {
            masterSalesTable = tempMasterSalesTable;
            salesTable = tempSalesTable;
        } else {
            masterSalesTable = mainMasterSalesTable;
            salesTable = mainSalesTable;
        }
        String selectClause = StringConstantsUtil.SPACE_SELECT;
        String groupBy = " YEARS";
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER as PERIODS, ";
            groupBy += ", PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as  PERIODS, ";
            groupBy += ",  PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS, ";
            groupBy += ",  PERIODS";

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += "I.\"MONTH\" as PERIODS, ";
            groupBy += ", PERIODS";
        }
        // To filter the data according to selected period
        String periodFilter = CommonLogic.getPeriodRestrictionQueryforPV(projSelDTO);

        // To handle the scenario where any discount is not selected in program selection lookup
        String customSql = " INNER JOIN " + masterSalesTable + "  B1 "
                + " ON A1.PROJECTION_DETAILS_SID = B1.PROJECTION_DETAILS_SID "
                + " INNER JOIN PROJECTION_DETAILS E  "
                + " ON E.PROJECTION_DETAILS_SID = A1.PROJECTION_DETAILS_SID "
                + " INNER JOIN CCP_DETAILS CCP "
                + " ON E.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID "
                + " INNER JOIN @CCP CC "
                + " ON CC.CCP_DETAILS_SID = E.CCP_DETAILS_SID "
                + " INNER JOIN [dbo].[Udf_item_pricing](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, 'UN') U\n"
                + " ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID "
                + " AND A1.PERIOD_SID=U.PERIOD_SID"
                + " AND E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "";
        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase("C")) {
            customSql += "   AND A1.SESSION_ID = " + projSelDTO.getSessionId() + "\n"
                    + "    AND A1.[USER_ID] = " + projSelDTO.getUserId() + "\n"
                    + "      AND B1.SESSION_ID = " + projSelDTO.getSessionId() + "\n"
                    + "        AND B1.[USER_ID] =" + projSelDTO.getUserId() + "\n";
        }
        customSql += " INNER JOIN PERIOD I\n"
                + "   ON I.PERIOD_SID = A1.PERIOD_SID "
                + " LEFT JOIN (SELECT A.ITEM_MASTER_sID,A.PERIOD_sID ,A.ACTUAL_RATE,A.PROJECTED_RATE,CCPD.PROJECTION_DETAILS_SID \n"
                + " FROM #TEMP_RETURNS A JOIN #TEMP_CCPD CCPD  ON A.ITEM_MASTER_SID= CCPD.ITEM_MASTER_SID	) TR ON TR.PROJECTION_DETAILS_SID = COALESCE(A1.PROJECTION_DETAILS_SID, B1.PROJECTION_DETAILS_SID) \n"
                + " AND TR.PERIOD_SID = A1.PERIOD_SID ";
        customSql += " where "
                + periodFilter
                + "  )A"
                + " group by  " + groupBy;

        String futureQuery = StringUtils.EMPTY;
         futureQuery = futureQuery + StringUtils.EMPTY;
        futureQuery = futureQuery + selectClause
                + " (A1.PROJECTION_SALES) "
                + " ,(A1.PROJECTION_UNITS)"
                + " , TR.PROJECTED_RATE\n"
                + " , A1.PERIOD_SID\n"
                + " , U.ITEM_PRICE"
                + " from  "
                + salesTable + " A1 " + customSql;
        return futureQuery;
    }

    public String getPPAProjectionResultsQuery(PVSelectionDTO projSelDTO) {
        String mainMasterSalesTable = "  NM_PPA_PROJECTION_MASTER ";
        String tempMasterSalesTable = " ST_NM_PPA_PROJECTION_MASTER ";
        String mainSalesTable = " NM_PPA_PROJECTION ";
        String tempSalesTable = " ST_NM_PPA_PROJECTION ";

        String masterSalesTable;
        String salesTable;

        if (!projSelDTO.isIsPrior()) {
            masterSalesTable = tempMasterSalesTable;
            salesTable = tempSalesTable;
        } else {
            masterSalesTable = mainMasterSalesTable;
            salesTable = mainSalesTable;
        }
        String selectClause = StringConstantsUtil.SPACE_SELECT;
        selectClause += "I.\"YEAR\"  as YEARS, ";
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER as  PERIODS, ";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, ";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as   PERIODS, ";

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += "I.\"MONTH\" as PERIODS , ";
        }
        // To filter the data according to selected period
        String periodFilter = CommonLogic.getPeriodRestrictionQueryforPV(projSelDTO);

        // To handle the scenario where any discount is not selected in program selection lookup
        String customSql = " INNER JOIN " + masterSalesTable + "  B1 "
                + " ON A1.PROJECTION_DETAILS_SID = B1.PROJECTION_DETAILS_SID "
                + " INNER JOIN PROJECTION_DETAILS E  "
                + " ON E.PROJECTION_DETAILS_SID = A1.PROJECTION_DETAILS_SID "
                + " INNER JOIN CCP_DETAILS CCP "
                + " ON E.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID "
                + " INNER JOIN @CCP CC "
                + " ON CC.CCP_DETAILS_SID = E.CCP_DETAILS_SID "
                + " INNER JOIN \"PERIOD\" I ON I.PERIOD_SID = A1.PERIOD_SID "
                + " WHERE E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "";
        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase("C")) {
            customSql += "   AND A1.SESSION_ID = " + projSelDTO.getSessionId() + "\n"
                    + "    AND A1.[USER_ID] = " + projSelDTO.getUserId() + "\n"
                    + "      AND B1.SESSION_ID = " + projSelDTO.getSessionId() + "\n"
                    + "        AND B1.[USER_ID] =" + projSelDTO.getUserId() + "\n";
        }
        customSql += " AND "
                + periodFilter
                + "  )AA"
                + " group by  YEARS,PERIODS";

        String futureQuery = StringUtils.EMPTY;
        futureQuery = futureQuery + selectClause
                + " A1.PROJECTION_SALES "
                + " ,A1.PROJECTION_RATE\n"
                + " from  "
                + salesTable + " A1 " + customSql;
        return futureQuery;
    }

    public String getSalesQuery(final String salesInnerJoin) {
        String query = StringUtils.EMPTY;
        query = query + "SELECT YEARS\n"
                + " , PERIODS\n"
                + " , Sum(projection_sales) AS SALES_PROJECTION_SALES\n"
                + "  , Sum(projection_units) AS PROJECTION_UNITS\n"
                + " , (Isnull(Sum(PROJECTION_UNITS), 0) * Isnull(Avg(ITEM_PRICE), 0)) AS COGS_PROJECTED\n"
                + " , PROJECTED_RATE = Avg(PROJECTED_RATE)\n"
                + " , RETURNS_PROJECTED = Sum(PROJECTION_SALES) * Avg(PROJECTED_RATE)\n"
                + " FROM ( " + salesInnerJoin;
        return query;
    }

    public String getPPAQuery(final String salesInnerJoin) {
        String query = StringUtils.EMPTY;
         query = query + "SELECT AA.YEARS\n"
                + " , AA.PERIODS\n"
                + " , SUM(AA.PROJECTION_SALES) AS SALES_PPA \n"
                + "  ,  SUM(AA.PROJECTION_RATE) AS SALES_RPU \n"
                + " FROM ( " + salesInnerJoin;
        return query;
    }

    public String getCCPWhereConditionQuery(String projectionDetails, String ccp) {
        String ccpWhereCond = StringConstantsUtil.SMALL_AND + ccp + ".CCP_DETAILS_SID=" + projectionDetails + ".CCP_DETAILS_SID ";
        LOGGER.debug("ccpWhereCond ={}",ccpWhereCond);
        return ccpWhereCond;
    }

    public String getProjectionResultsDiscountsPivotQuery(PVSelectionDTO projSelDTO) {
        String selectClause = StringConstantsUtil.SPACE_SELECT;
        String customQuery = "";
        List<String> list = getCommonSelectWhereOrderGroupByClause(StringConstantsUtil.TODIS, StringConstantsUtil.SALEPPA, "on");
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "TODIS.DISCOUNTS, ";
        selectClause += " PROJECTION_RATE=Isnull(TODIS.PROJECTION_SALES / NULLIF(SALEPPA.SALES_PROJECTION_SALES, 0), 0)";
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, "");
        String salesPpaQuery = getProjectionResultsSalesQuery(projSelDTO);
        customQuery = selectClause + " from (" + totalDiscountQuery + ") TODIS FULL OUTER JOIN (" + salesPpaQuery + ")  SALEPPA " + finalWhereCond;
        return customQuery;
    }

    public String getComparisonSearch(String workflowStatus, String marketType, String brand,
            String projName, String contHldr, String ndcNo, String ndcName, String desc, String contract,
            String from, String to, String notSearchProjId) {
        char quotes = '\'';
        char asterik = '*';
        char percent = '%';
        String marketTypeVal;
        String brandVal;
        String projNameVal;
        String contHldrVal;
        String ndcNoVal;
        String ndcNameVal;
        String descVal;
        String contractVal;
        boolean isProjectionStatus = false;
        try {
            StringBuilder customSql;
            if (workflowStatus.equals(Constants.SAVED)) {
                isProjectionStatus = true;
            }
            if (isProjectionStatus) {
                customSql = new StringBuilder(SQlUtil.getQuery("getProjectionLists"));
            } else {
                customSql = new StringBuilder(SQlUtil.getQuery("getWorkFlowLists"));
            }

            if (marketType == null || marketType.equals(StringUtils.EMPTY)) {
                marketTypeVal = "'%'";
            } else {
                marketTypeVal = marketType.replace(asterik, percent);
                marketTypeVal = quotes + marketTypeVal + quotes;
            }
            customSql.append("( HT.list_name = 'CONTRACT_TYPE' AND HT.DESCRIPTION LIKE " ).append( marketTypeVal ).append( ')');
            if (brand == null || brand.equals(StringUtils.EMPTY)) {
                brandVal = "'%'";
            } else {
                brandVal = brand.replace(asterik, percent);
                brandVal = quotes + brandVal + quotes;
            }
            customSql.append("  AND (BM.BRAND_NAME LIKE " ).append( brandVal ).append( " or BM.BRAND_NAME is null)");
            if (projName == null || projName.equals(StringUtils.EMPTY)) {
                projNameVal = "'%'";
            } else {
                projNameVal = projName.replace(asterik, percent);
                projNameVal = quotes + projNameVal + quotes;
            }
            customSql.append("AND (PM.PROJECTION_NAME LIKE " ).append( projNameVal ).append( " or PM.PROJECTION_NAME is null)");
            if (contHldr == null || contHldr.equals(StringUtils.EMPTY)) {
                contHldrVal = "'%'";
            } else {
                contHldrVal = contHldr.replace(asterik, percent);
                contHldrVal = quotes + contHldrVal + quotes;
            }
            customSql.append("AND (C.CONTRACT_NO LIKE " ).append( contHldrVal ).append( " or C.CONTRACT_NO is null)");
            if (ndcName == null || ndcName.equals(StringUtils.EMPTY)) {
                ndcNameVal = "'%'";
            } else {
                ndcNameVal = ndcName.replace(asterik, percent);
                ndcNameVal = quotes + ndcNameVal + quotes;
            }
            customSql.append("AND (IM.ITEM_NAME LIKE " ).append( ndcNameVal ).append( " or IM.ITEM_NAME is null)");
            if (ndcNo == null || ndcNo.equals(StringUtils.EMPTY)) {
                ndcNoVal = "'%'";
            } else {
                ndcNoVal = ndcNo.replace(asterik, percent);
                ndcNoVal = quotes + ndcNoVal + quotes;
            }
            customSql.append("AND (IM.ITEM_NO LIKE " ).append( ndcNoVal ).append( "or IM.ITEM_NO is null)");
            if (contract == null || contract.equals(StringUtils.EMPTY)) {
                contractVal = "'%'";
            } else {
                contractVal = contract.replace(asterik, percent);
                contractVal = quotes + contractVal + quotes;
            }
            customSql.append("AND (CM.COMPANY_NO LIKE " ).append( contractVal ).append( "or CM.COMPANY_NO is null)");
            if (desc == null || desc.equals(StringUtils.EMPTY)) {
                descVal = "'%'";
            } else {
                descVal = desc.replace(asterik, percent);
                descVal = quotes + descVal + quotes;
            }
            customSql.append("AND (PM.PROJECTION_NAME LIKE " ).append( descVal ).append( "or PM.PROJECTION_NAME is null)");
            if (isProjectionStatus) {
                customSql.append("and pm.is_approved not in ('Y','C','A','R')");
            } else {
                customSql.append("AND HT1.list_name = 'WorkFlowStatus' and ht1.description = " ).append( quotes ).append( workflowStatus ).append( quotes);
            }
            if (to != null && from != null && !StringUtils.isEmpty(to) && !StringUtils.isEmpty(from)
                    && !"null".equals(to) && !"null".equals(from) ) {
                SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
                customSql.append(" AND PM.CREATED_DATE BETWEEN '");
                customSql.append(format2.format(format2.parse(from)));
                customSql.append("' AND '");
                customSql.append(format2.format(format2.parse(to)));
                customSql.append("' ");
            }
            customSql.append("AND PM.PROJECTION_MASTER_SID NOT IN (" ).append( notSearchProjId ).append( ')');
            customSql.append("AND PM.FORECASTING_TYPE='Non Mandated'");

            customSql.append(" group by  pm.projection_name,pm.projection_description,ht.description ,cm.company_no  ,c.contract_no  ,pm.projection_master_sid,PM.created_date,PM.created_by ");

            return customSql.toString();
        } catch (ParseException e) {
            LOGGER.error(" at PvQueryUtils -> getComparisonSearch= {}", e);
            return null;
        }
    }

    /**
     * Logic to make persistency of already added projection in the selected
     * table - comparison look up
     *
     * @param projId
     * @return query -String
     */
    public String getPVComparisonProjections(final List<Integer> projId) {
        try {
            String customSql = SQlUtil.getQuery("getProjectionLists");
            if (projId != null && !projId.isEmpty()) {
                customSql += (" PM.PROJECTION_MASTER_SID IN (" + CommonUtils.collectionToString(projId, false) + ")");
            } else {
                customSql += (" PM.PROJECTION_MASTER_SID IN ('')");
            }
            customSql += (" group by  pm.projection_name,pm.projection_description,ht.description ,pm.projection_master_sid,PM.created_date,PM.created_by ");
            return customSql;
        } catch (Exception e) {
            LOGGER.error(" at PvQueryUtils -> getComparisonSearch= {}", e);
            return null;
        }
    }

    public String getProjectionResultsDiscountsQuery(PVSelectionDTO projSelDTO, String orderBy) {
        String mainMasterTable = "  NM_DISCOUNT_PROJ_MASTER ";
        String tempMasterTable = " ST_NM_DISCOUNT_PROJ_MASTER ";
        String mainTable = " NM_DISCOUNT_PROJECTION ";
        String tempTable = " ST_NM_DISCOUNT_PROJECTION ";
        String masterTable;
        String table;
        String selectClause = StringConstantsUtil.SPACE_SELECT;
        String whereClause = "";
        String groupBy = "  I.\"YEAR\"";
        if (!projSelDTO.isIsPrior()) {
            masterTable = tempMasterTable;
            table = tempTable;
        } else {
            masterTable = mainMasterTable;
            table = mainTable;
        }
        String customQuery = "";
        String ccpWhereCond = getCCPWhereConditionQuery("E", "CCP");
        selectClause += "I.\"YEAR\" as YEARS , ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER as  PERIODS, ";
            whereClause += "";
            groupBy += IQUARTER;
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL  as PERIODS, ";
            whereClause += "";
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as  PERIODS, ";
            whereClause += "";
            groupBy += "";

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += "I.\"MONTH\" as  PERIODS, ";
            whereClause += "";
            groupBy += ", I.\"MONTH\"";
        }

        // To filter the data according to selected period
        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        // To handle the scenario where any discount is not selected in program selection lookup
        String discountTypeColumnName = "'Total discounts' as DISCOUNTS";
        LOGGER.debug("Discount Level= {}",projSelDTO.getDiscountLevel());
        if (projSelDTO.getDiscountLevel().equals("Program Category")) {
            LOGGER.debug("projSelDTO.getDiscountNameList()= {} and projSelDTO.isIsTotal()= {} ", projSelDTO.getDiscountNameList().size(), projSelDTO.isIsTotal());
            if (projSelDTO.getDiscountNameList() != null && !projSelDTO.getDiscountNameList().isEmpty()) {
                if (!projSelDTO.isIsTotal()) {
                    discountTypeColumnName = " B.PRICE_GROUP_TYPE AS DISCOUNTS";
                    groupBy += ", " + " B.PRICE_GROUP_TYPE";
                }
                whereClause += " and B.PRICE_GROUP_TYPE in (" + CommonUtils.collectionToString(projSelDTO.getDiscountNameList(), true) + ")";
            }
        } else {
            if (projSelDTO.getDiscountNoList() != null && !projSelDTO.getDiscountNoList().isEmpty()) {
                if (!projSelDTO.isIsTotal()) {
                    discountTypeColumnName = " J.RS_NAME AS DISCOUNTS";
                    groupBy += ", " + " J.RS_NAME";
                }
                whereClause += " and B.RS_MODEL_SID in (" + CommonUtils.collectionToString(projSelDTO.getDiscountNoList(), false) + ")";
            }
        }

        selectClause += discountTypeColumnName + ", ";

        String customSql = masterTable
                + " B, PROJECTION_DETAILS E ,"
                + " \"PERIOD\" I, "                
                + CCP_CCP;
        if (!projSelDTO.isIsTotal()) {
            customSql += ", RS_MODEL J ";
        }
        customSql += "where A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID and B.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID ";
        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase("C")) {
            customSql += getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "B")
                    + getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "A");
        }
        if (!projSelDTO.isIsTotal()) {
            customSql += "and B.RS_MODEL_SID= J.RS_MODEL_SID ";
        }
        customSql += "and A.RS_MODEL_SID = B.RS_MODEL_SID "
                + "and E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId()
                + ccpWhereCond + "and A.PERIOD_SID = I.PERIOD_SID " + periodFilter
                + whereClause + " group by  " + groupBy;

        String futureQuery = selectClause + " 0 as ACTUAL_SALES, sum(A.PROJECTION_SALES) as PROJECTION_SALES,SUM(A.PROJECTION_RPU) AS RPU from " + table + " A," + customSql;

        customQuery = futureQuery + orderBy;
        return customQuery;
    }


    public List<String> getCommonSelectWhereOrderGroupByClause(String table1, String table2, String where) {
        List<String> list = new ArrayList<>();
        String orderBy = " YEARS, PERIODS";
        String groupBy = " " + table1 + ".YEARS";
        String selectClause = "Isnull(" + table1 + ".YEARS, " + table2 + ".YEARS) as YEARS, ";
        String finalWhereCond = " " + where + " " + table1 + ".YEARS=" + table2 + ".YEARS";
        groupBy += ", " + table1 + ".PERIODS";
        selectClause += "Isnull(" + table1 + ".PERIODS, " + table2 + ".PERIODS) as PERIODS,";
        finalWhereCond += StringConstantsUtil.SMALL_AND + table1 + ".PERIODS=" + table2 + ".PERIODS";
        list.add(selectClause);
        list.add(finalWhereCond);
        list.add(orderBy);
        list.add(groupBy);
        return list;
    }


    public String getProjectionResultsTotalDiscountPerQuery(PVSelectionDTO projSelDTO) {
        String selectClause = StringConstantsUtil.SPACE_SELECT;
        String customQuery = "";
        List<String> list = getCommonSelectWhereOrderGroupByClause(StringConstantsUtil.TODIS, StringConstantsUtil.SALEPPA, "on");
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "'Total Discount %' as DISCOUNTS, ";
        selectClause += " ACTUAL_RATE=Isnull((Isnull(TODIS.ACTUAL_SALES, 0)+Isnull(SALEPPA.ACTUAL_SALES, 0)) / NULLIF(SALEPPA.SALES_ACTUAL_SALES, 0), 0) * 100,"
                + " PROJECTION_RATE=Isnull((Isnull(TODIS.PROJECTION_SALES, 0)+Isnull(SALEPPA.PROJECTION_SALES, 0)) / NULLIF(SALEPPA.SALES_PROJECTION_SALES, 0), 0) * 100 ";
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, "");
        String salesPpaQuery = getProjectionResultsSalesQuery(projSelDTO);
        customQuery = selectClause + " from (" + totalDiscountQuery + ") TODIS LEFT JOIN (" + salesPpaQuery + ") SALEPPA " + finalWhereCond;
        return customQuery;
    }

    public String getPVMainDiscountPivotQuery(PVSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(false);
        StringBuilder selectClauseDol = new StringBuilder(" select C.YEARS as YEARS, C.PERIODS AS PERIODS,C.DISCOUNTS AS DISCOUNTS,");
        StringBuilder customQuery = new StringBuilder();
        String orderBy = " DISCOUNTS , YEARS,PERIODS ";
        selectClauseDol.append("IsNull(C.PROJECTION_SALES, 0) AS C_DOL_VAL ");
        selectClauseDol.append(",0  AS C_DOL_VAR ");
        selectClauseDol.append( ",0  AS C_DOL_PER ");

        selectClauseDol.append( ",IsNull(C.PROJECTION_RATE, 0) C_RATE_VAL");
        selectClauseDol.append( ",0  AS C_RATE_VAR ");
        selectClauseDol.append( ",0  AS C_RATE_PER ");

        selectClauseDol.append( ",IsNull(C.RPU, 0) C_RPU_VAL");
        selectClauseDol.append( ",0  AS C_RPU_VAR ");
        selectClauseDol.append( ",0  AS C_RPU_PER ");

        selectClauseDol.append( ",IsNull(C.RETURNS_PROJECTED_AMOUNT, 0) AS C_DOL_RET_VAL ");
        selectClauseDol.append( ",0  AS  C_DOL_RET_VAR ");
        selectClauseDol.append( ",0  AS  C_DOL_RET_PER ");

        selectClauseDol.append( ",IsNull(C.RETURNS_PROJECTED_RATE, 0) C_RATE_RET_VAL");
        selectClauseDol.append( ",0  AS C_RATE_RET_VAR ");
        selectClauseDol.append( ",0  AS C_RATE_RET_PER ");

        selectClauseDol.append( ",IsNull(C.RETURNS_RPU, 0) C_RPU_RET_VAL");
        selectClauseDol.append( ",0  AS C_RPU_RET_VAR ");
        selectClauseDol.append( ",0  AS C_RPU_RET_PER ");

        selectClauseDol.append( ",IsNull(C.PPA_DISCOUNT_AMOUNT, 0) AS C_DOL_PPA_VAL ");
        selectClauseDol.append( ",0  AS  C_DOL_PPA_VAR ");
        selectClauseDol.append( ",0  AS  C_DOL_PPA_PER ");

        selectClauseDol.append( ",IsNull(C.PPA_DISCOUNT_PERCENT, 0) C_RATE_PPA_VAL");
        selectClauseDol.append( ",0  AS C_RATE_PPA_VAR ");
        selectClauseDol.append( ",0  AS C_RATE_PPA_PER ");

        selectClauseDol.append( ",IsNull(C.PPA_DISCOUNT_RPU, 0) C_RPU_PPA_VAL");
        selectClauseDol.append( ",0  AS C_RPU_PPA_VAR ");
        selectClauseDol.append( ",0  AS C_RPU_PPA_PER ");

        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            selectClauseDol.append(",  IsNull(P").append(i).append(".PROJECTION_SALES,0) AS P").append(i).append("_DOL_VALUE ");
            selectClauseDol.append(", (IsNull(C.PROJECTION_SALES, 0)- IsNull(P").append(i).append(".PROJECTION_SALES, 0)) AS P").append(i).append("_DOL_VAR ");
            selectClauseDol.append(CASE_WHEN_P).append(i).append(".PROJECTION_SALES = 0 THEN 0\n ELSE (IsNull(C.PROJECTION_SALES, 0) - IsNull(P").append(i).append(".PROJECTION_SALES, 0) / P").append(i).append(".PROJECTION_SALES) \n END  AS  P").append(i).append("_DOL_PER ");

            selectClauseDol.append(",  IsNull(P").append(i).append(".PROJECTION_RATE,0) AS P").append(i).append("_RATE_VAL ");
            selectClauseDol.append(", (IsNull(C.PROJECTION_RATE, 0)- IsNull(P").append(i).append(".PROJECTION_RATE, 0)) AS P").append(i).append("_RATE_VAR ");
            selectClauseDol.append(CASE_WHEN_P).append(i).append(".PROJECTION_RATE = 0 THEN 0\n ELSE (IsNull(C.PROJECTION_RATE, 0) - IsNull(P").append(i).append(".PROJECTION_RATE, 0) / P").append(i).append(".PROJECTION_RATE) \n END  AS   P").append(i).append("_RATE_PER ");

            selectClauseDol.append(", IsNull (P").append(i).append(".RPU,0) AS P").append(i).append("_RPU_VAL ");
            selectClauseDol.append(", (IsNull(C.RPU, 0)- IsNull(P").append(i).append(".RPU, 0)) AS P").append(i).append("_RPU_VAR ");
            selectClauseDol.append(CASE_WHEN_P).append(i).append(".RPU = 0 THEN 0\n ELSE (IsNull(C.RPU, 0) - IsNull(P").append(i).append(".RPU, 0) / P").append(i).append(".RPU) \n END  AS    P").append(i).append("_RPU_PER ");

            selectClauseDol.append(", IsNull (P").append(i).append(".RETURNS_PROJECTED_AMOUNT,0) AS P").append(i).append("_RET_DOL_VALUE ");
            selectClauseDol.append(", (IsNull(C.RETURNS_PROJECTED_AMOUNT, 0)- IsNull(P").append(i).append(".RETURNS_PROJECTED_AMOUNT, 0)) AS P").append(i).append("_RET_DOL_VAR ");
            selectClauseDol.append(CASE_WHEN_P).append(i).append(".RETURNS_PROJECTED_AMOUNT = 0 THEN 0\n ELSE (IsNull(C.RETURNS_PROJECTED_AMOUNT, 0) - IsNull(P").append(i).append(".RETURNS_PROJECTED_AMOUNT, 0) / P").append(i).append(".RETURNS_PROJECTED_AMOUNT) \n END  AS  P").append(i).append("_RET_DOL_PER ");

            selectClauseDol.append(",  IsNull (P").append(i).append(".RETURNS_PROJECTED_RATE,0) AS P").append(i).append("_RET_RATE_VAL ");
            selectClauseDol.append(", (IsNull(C.RETURNS_PROJECTED_RATE, 0)- IsNull(P").append(i).append(".RETURNS_PROJECTED_RATE, 0)) AS P").append(i).append("_RET_RATE_VAR ");
            selectClauseDol.append(CASE_WHEN_P).append(i).append(".RETURNS_PROJECTED_RATE = 0 THEN 0\n ELSE (IsNull(C.RETURNS_PROJECTED_RATE, 0) - IsNull(P").append(i).append(".RETURNS_PROJECTED_RATE, 0) / P").append(i).append(".RETURNS_PROJECTED_RATE) \n END  AS      P").append(i).append("_RET_RATE_PER ");

            selectClauseDol.append(",  IsNull (P").append(i).append(".RETURNS_RPU,0) AS P").append(i).append("_RET_RPU_VAL ");
            selectClauseDol.append(", (IsNull(C.RETURNS_RPU, 0)- IsNull(P").append(i).append(".RETURNS_RPU, 0)) AS P").append(i).append("_RET_RPU_VAR ");
            selectClauseDol.append(CASE_WHEN_P).append(i).append(".RETURNS_RPU = 0 THEN 0\n ELSE (IsNull(C.RETURNS_RPU, 0) - IsNull(P").append(i).append(".RETURNS_RPU, 0) / P").append(i).append(".RPU) \n END  AS      P").append(i).append("_RET_RPU_PER ");
            
            selectClauseDol.append(", IsNull(P").append(i).append(".PPA_DISCOUNT_AMOUNT,0) AS P").append(i).append("_PPA_DOL_VALUE ");
            selectClauseDol.append(", (IsNull(C.PPA_DISCOUNT_AMOUNT, 0)- IsNull(P").append(i).append(".PPA_DISCOUNT_AMOUNT, 0)) AS P").append(i).append("_PPA_DOL_VAR ");
            selectClauseDol.append(CASE_WHEN_P).append(i).append(".PPA_DISCOUNT_AMOUNT = 0 THEN 0\n ELSE (IsNull(C.PPA_DISCOUNT_AMOUNT, 0) - IsNull(P").append(i).append(".PPA_DISCOUNT_AMOUNT, 0) / P").append(i).append(".PPA_DISCOUNT_AMOUNT) \n END  AS P").append(i).append("_PPA_DOL_PER ");

            selectClauseDol.append(" , IsNull (P").append(i).append(".PPA_DISCOUNT_PERCENT,0) AS P").append(i).append("_PPA_RATE_VAL ");
            selectClauseDol.append(", (IsNull(C.PPA_DISCOUNT_PERCENT, 0)- IsNull(P").append(i).append(".PPA_DISCOUNT_PERCENT, 0)) AS P").append(i).append("_PPA_RATE_VAR ");
            selectClauseDol.append(CASE_WHEN_P).append(i).append(".PPA_DISCOUNT_PERCENT = 0 THEN 0\n ELSE (IsNull(C.PPA_DISCOUNT_PERCENT, 0) - IsNull(P").append(i).append(".PPA_DISCOUNT_PERCENT, 0) / P").append(i).append(".PPA_DISCOUNT_PERCENT) \n END  AS     P").append(i).append("_PPA_RATE_PER ");

            selectClauseDol.append(", IsNull(P").append(i).append(".PPA_DISCOUNT_RPU,0) AS P").append(i).append("_PPA_RPU_VAL ");
            selectClauseDol.append(", (IsNull(C.PPA_DISCOUNT_RPU, 0)- IsNull(P").append(i).append(".PPA_DISCOUNT_RPU, 0)) AS P").append(i).append("_PPA_RPU_VAR ");
            selectClauseDol.append(CASE_WHEN_P).append(i).append(".PPA_DISCOUNT_RPU = 0 THEN 0\n ELSE (IsNull(C.PPA_DISCOUNT_RPU, 0) - IsNull(P").append(i).append(".PPA_DISCOUNT_RPU, 0) / P").append(i).append(".RPU)  \n END  AS     P").append(i).append("_PPA_RPU_PER ");
        }
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        projSelDTO.setCurrentOrPrior("C");
        projSelDTO.setIsPrior(false);
        customQuery.append(selectClauseDol ).append( " from   \n(" ).append( getProjectionResultsDiscountsPerQuery(projSelDTO) ).append( ") C \n ");
        projSelDTO.setCurrentOrPrior("P");
        projSelDTO.setIsPrior(true);
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            projSelDTO.setProjectionId(projSelDTO.getProjIdList().get(i));
            customQuery.append( " LEFT JOIN (\n" ).append( getProjectionResultsDiscountsPerQuery(projSelDTO) ).append( "\n) " ).append( 'P' ).append( i ).append( " \n on  C.DISCOUNTS=P" ).append( i ).append( ".DISCOUNTS \n ")
                    .append( " AND C.YEARS=P" ).append( i ).append( ".YEARS  \n ")
                    .append( " and C.PERIODS= P" ).append( i ).append( ".PERIODS   ");
        }
        customQuery.append( " order  by " ).append( orderBy);
        projSelDTO.setIsPrior(false);
        projSelDTO.setCurrentOrPrior("C");
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        return customQuery.toString();
    }

    public String getProjectionResultsDiscountsPerQuery(PVSelectionDTO projSelDTO) {
        String selectClause = StringConstantsUtil.SPACE_SELECT;
        String customQuery = "";
        List<String> list = getCommonSelectWhereOrderGroupByClause(StringConstantsUtil.TODIS, StringConstantsUtil.SALEPPA, "on");
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "TODIS.DISCOUNTS, ";
        selectClause += "TODIS.PROJECTION_SALES AS PROJECTION_SALES, ";
        selectClause += " PROJECTION_RATE=Isnull(TODIS.PROJECTION_SALES / NULLIF(SALEPPA.SALES_PROJECTION_SALES, 0), 0)*100,";
        selectClause += " CASE WHEN SALEPPA.PROJECTION_UNITS = 0 THEN 0 ELSE (((Isnull(TODIS.PROJECTION_SALES, 0)) /SALEPPA.PROJECTION_UNITS) ) END AS RPU,";
        selectClause += " ISNULL(SALEPPA.RETURNS_PROJECTED,0) AS RETURNS_PROJECTED_AMOUNT,";
        selectClause += " ISNULL(SALEPPA.PROJECTED_RATE/NULLIF(SALEPPA.SALES_PROJECTION_SALES, 0), 0) * 100 AS RETURNS_PROJECTED_RATE, ";
        selectClause += " (ISNULL(SALEPPA.RETURNS_PROJECTED / NULLIF(SALEPPA.PROJECTION_UNITS, 0), 0)) AS RETURNS_RPU ";
        selectClause += ", (ISNULL(PPA.SALES_PPA,0)) AS PPA_DISCOUNT_AMOUNT\n"
                + "              ,Isnull(PPA.SALES_PPA / NULLIF(SALEPPA.SALES_PROJECTION_SALES, 0), 0) * 100 AS PPA_DISCOUNT_PERCENT\n"
                + "              , (Isnull(PPA.SALES_RPU / NULLIF(SALEPPA.PROJECTION_UNITS, 0), 0))AS PPA_DISCOUNT_RPU";
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, "");
        String salesPpaQuery = getSalesQuery(getProjectionResultsSalesQuery(projSelDTO));
        String ppaQuery = getPPAQuery(getPPAProjectionResultsQuery(projSelDTO));
        String ppaGroupBy = "ON TODIS.YEARS = PPA.YEARS AND TODIS.PERIODS = PPA.PERIODS";
        customQuery = selectClause + " from  (" + totalDiscountQuery + ") TODIS FULL OUTER JOIN (" + salesPpaQuery + ") SALEPPA " + finalWhereCond + " FULL JOIN ( \n" + ppaQuery + ")PPA " + ppaGroupBy;
        return customQuery;
    }

    public String getProjectionResultsPPAPerQuery(PVSelectionDTO projSelDTO) {
        String selectClause = StringConstantsUtil.SPACE_SELECT;
        String customQuery = "";
        selectClause += "SALEPPA.YEARS ,SALEPPA.PERIODS, ";
        selectClause += "'PPA Discount' as DISCOUNTS,";
        selectClause += " ACTUAL_RATE=Isnull(SALEPPA.ACTUAL_SALES / NULLIF(SALEPPA.SALES_ACTUAL_SALES, 0), 0) * 100,"
                + " PROJECTION_RATE=Isnull(SALEPPA.PROJECTION_SALES / NULLIF(SALEPPA.SALES_PROJECTION_SALES, 0), 0) * 100 ";
        String ppaQuery = getProjectionResultsSalesQuery(projSelDTO);
        customQuery = selectClause + "  from (" + ppaQuery + ") SALEPPA";
        return customQuery;
    }

    public String getProjectionResultsNetSalesQuery(PVSelectionDTO projSelDTO) {
        String selectClause = StringConstantsUtil.SPACE_SELECT;
        String customQuery = "";
        List<String> list = getCommonSelectWhereOrderGroupByClause(StringConstantsUtil.TODIS, StringConstantsUtil.SALEPPA,"on");
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "'Net Sales' as NETSALES, ";
        selectClause += " ACTUAL_SALES=(Isnull(SALEPPA.SALES_ACTUAL_SALES, 0)-(Isnull(TODIS.ACTUAL_SALES, 0)+Isnull(SALEPPA.ACTUAL_SALES, 0))),"
                + " PROJECTION_SALES=(Isnull(SALEPPA.SALES_PROJECTION_SALES, 0)-(Isnull(TODIS.PROJECTION_SALES, 0)+Isnull(SALEPPA.PROJECTION_SALES, 0))) ";
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, "");
        String salesPpaQuery = getProjectionResultsSalesQuery(projSelDTO);
        customQuery = selectClause + "  from (" + totalDiscountQuery + ") TODIS FULL  OUTER JOIN (" + salesPpaQuery + ")  SALEPPA " + finalWhereCond;
        return customQuery;
    }

    public String getGeneralCCPQuery(PVSelectionDTO projSelDTO) {
        String viewtable = CommonLogic.getViewTableName(projSelDTO.getHierarchyIndicator());
        String ccpQuery = " SELECT LCCP.RELATIONSHIP_LEVEL_SID, LCCP.CCP_DETAILS_SID, LCCP.HIERARCHY_NO from \n"
                + "(SELECT CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID from \n"
                + "(SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + "FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + "JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                + "JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID \n"
                + "AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId();
        if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals("P")) {
            String userGroup = projSelDTO.getGroupFilter();
            ccpQuery += " " + CommonLogic.getGroupFilterQuery(userGroup, projSelDTO.getUserId(), projSelDTO.getSessionId(), false);
        }
        ccpQuery += " ) CCPMAP,\n"
                + " (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD1 \n"
                + "  JOIN  " + viewtable + " PCH \n"
                + " ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID \n"
                + " AND PCH.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE  RLD1.HIERARCHY_NO like '" + projSelDTO.getHierarchyNo() + "%' ) HLD \n"
                + " WHERE CCPMAP.HIERARCHY_NO like HLD.HIERARCHY_NO+'%' ) LCCP \n"
                + " WHERE LCCP.HIERARCHY_NO in \n"
                + " (SELECT RLD2.HIERARCHY_NO \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD2 \n"
                + "  JOIN  " + viewtable + " PCH2 \n"
                + " ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID \n"
                + " AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD2.LEVEL_NO=" + projSelDTO.getTreeLevelNo() + ") \n";

        return ccpQuery;
    }

    public String getCustomCCPQuery(PVSelectionDTO projSelDTO) {
        String customerHierarchyNo = projSelDTO.getCustomerHierarchyNo() + "%";
        String productHierarchyNo = projSelDTO.getProductHierarchyNo() + "%";
        String customerLevelNo = "%";
        String productLevelNo = "%";

        if (projSelDTO.getHierarchyIndicator().equals("C")) {

            customerLevelNo = "" + Integer.toString(projSelDTO.getTreeLevelNo());
        } else if (projSelDTO.getHierarchyIndicator().equals("P")) {

            productLevelNo = "" + Integer.toString(projSelDTO.getTreeLevelNo());
        }
        String ccpQuery = " SELECT distinct HLD" + projSelDTO.getHierarchyIndicator() + ".RELATIONSHIP_LEVEL_SID, CCPMAP" + projSelDTO.getHierarchyIndicator() + ".CCP_DETAILS_SID, HLD" + projSelDTO.getHierarchyIndicator() + ".HIERARCHY_NO FROM \n"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n";

        if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals("P")) {
            String userGroup = projSelDTO.getGroupFilter();
            ccpQuery += " " + CommonLogic.getGroupFilterQuery(userGroup, projSelDTO.getUserId(), projSelDTO.getSessionId(), false);
        }

        ccpQuery += " ) CCPMAPC \n"
                + " JOIN"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n";

        if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals("P")) {
            String userGroup = projSelDTO.getGroupFilter();
            ccpQuery += " " + CommonLogic.getGroupFilterQuery(userGroup, projSelDTO.getUserId(), projSelDTO.getSessionId(), false);
        }

        ccpQuery += " ) CCPMAPP \n"
                + " ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID \n"
                + " JOIN "
                + " (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD \n"
                + " JOIN dbo.CUSTOM_VIEW_MASTER CVM ON \n"
                + " CVD.CUSTOM_VIEW_MASTER_SID=" + projSelDTO.getCustomId() + " AND CVD.LEVEL_NO  like '" + customerLevelNo + "'\n"
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD2.HIERARCHY_NO like '" + customerHierarchyNo + "') HLDC ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%' \n"
                + " JOIN "
                + " (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD \n"
                + " JOIN dbo.CUSTOM_VIEW_MASTER CVM ON \n"
                + " CVD.CUSTOM_VIEW_MASTER_SID=" + projSelDTO.getCustomId() + " AND CVD.LEVEL_NO like '" + productLevelNo + "'\n"
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD2.HIERARCHY_NO like '" + productHierarchyNo + "') HLDP ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%'";

        return ccpQuery;
    }

    public String getCCPQuery1(PVSelectionDTO projSelDTO) {
        String ccpQuery = getCCPTempTableQuery();
        if (projSelDTO.isIsCustomHierarchy()) {
            ccpQuery += getCustomCCPQuery(projSelDTO);
        } else {
            ccpQuery += getGeneralCCPQuery(projSelDTO);
        }
        return ccpQuery;
    }

    public String getCCPTempTableQuery() {
        String tableQuery = StringUtils.EMPTY;
         tableQuery = tableQuery + "DECLARE @CCP TABLE\n"
                + "  (\n"
                + "     RELATIONSHIP_LEVEL_SID INT,\n"
                + "     CCP_DETAILS_SID        INT,\n"
                + "     HIERARCHY_NO           VARCHAR(50)\n"
                + "  ) \n"
                + " INSERT INTO @CCP\n"
                + "            (RELATIONSHIP_LEVEL_SID,CCP_DETAILS_SID,HIERARCHY_NO) \n";
        return tableQuery;
    }

    /**
     * Main Whole Query
     *
     * @param projSelDTO
     * @return String
     */
    public String getProjVarianceMainQuery(PVSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(true);
        String selectClause = StringConstantsUtil.SPACE_SELECT;
        String customQuery = "";
        String orderBy = " YEARS,PERIODS ";
        selectClause += "C.YEARS as YEARS, C.PERIODS AS PERIODS,";

        customQuery = selectClause + getMainSelectClause("C");
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            customQuery = customQuery.concat(", ").concat(getMainSelectClause("P" + i));
        }
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        projSelDTO.setCurrentOrPrior("C");
        projSelDTO.setIsPrior(false);
        customQuery += " from   \n(" + getProjVarianceQuery(projSelDTO) + ") C\n ";
        projSelDTO.setCurrentOrPrior("P");
        projSelDTO.setIsPrior(true);
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            projSelDTO.setProjectionId(projSelDTO.getProjIdList().get(i));
            customQuery = customQuery.concat(" LEFT JOIN (\n").concat(getProjVarianceQuery(projSelDTO)).concat("\n) ").concat("P").concat(String.valueOf(i)).concat(" \n").concat(getProjVarMainWhereCond("P" + i));
        }
        customQuery += " order by  " + orderBy;
        projSelDTO.setIsPrior(false);
        projSelDTO.setCurrentOrPrior("C");
        return customQuery;
    }

    /**
     * Main Select clause
     *
     * @param projName
     * @return String
     */
    public String getMainSelectClause(String projName) {
         String selectClause = StringUtils.EMPTY;
         selectClause = selectClause + " " + projName + ".CONTRACT_PROJECTION_SALES as " + projName + "CONTRACT_PROJECTION_SALES,"
                + " " + projName + ".CONTRACT_PROJECTION_UNITS as " + projName + "CONTRACT_PROJECTION_UNITS,"
                + " " + projName + ".TOTAL_PROJECTION_RATE AS " + projName + "TOTAL_PROJECTION_RATE, "
                + " " + projName + ".TOTAL_PROJECTION_DOLAR as " + projName + "TOTAL_PROJECTION_DOLAR ";
        return selectClause;
    }

    /**
     * Variance sales and discount query
     *
     * @param projSelDTO
     * @return String
     */
    public String getProjVarianceQuery(PVSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(true);
        String selectClause = StringConstantsUtil.SPACE_SELECT;
        selectClause += "TODIS.YEARS as YEARS, TODIS.PERIODS AS PERIODS,";
        selectClause += getProjVarianceSelectClause();
        String totalDiscountQuery = getProjVarianceDiscountQuery(projSelDTO);
        String salesQuery = getProjVariancesSalesQuery(projSelDTO);
        String customQuery = StringUtils.EMPTY;
        customQuery = customQuery + selectClause + " from " + getProjVarSelectQuery(totalDiscountQuery, salesQuery);
        return customQuery;
    }

    /**
     * Get select query
     *
     * @param query1
     * @param query2
     * @return
     */
    public String getProjVarSelectQuery(String query1, String query2) {
        String finalWhereCond = " on TODIS.YEARS=SALEPPA.YEARS AND TODIS.PERIODS=SALEPPA.PERIODS";
        String selectClause = StringUtils.EMPTY;
         selectClause = selectClause + " (\n" + query1 + "\n) TODIS FULL OUTER JOIN (\n" + query2 + "\n) SALEPPA \n" + finalWhereCond;
        return selectClause;
    }

    /**
     * Where COnditions
     *
     * @param table
     * @return
     */
    public String getProjVarMainWhereCond(String table) {
        return " on C.YEARS=" + table + ".YEARS AND C.PERIODS=" + table + ".PERIODS \n";
    }

    /**
     * Select clause
     *
     * @return
     */
    public String getProjVarianceSelectClause() {
        String selectClause = " SALEPPA.SALES_PROJECTION_SALES as CONTRACT_PROJECTION_SALES,"
                + "SALEPPA.PROJECTION_UNITS as CONTRACT_PROJECTION_UNITS,"
                + " CASE WHEN SALEPPA.SALES_PROJECTION_SALES = 0 THEN 0 ELSE ((Isnull(TODIS.PROJECTION_SALES, 0)) /SALEPPA.SALES_PROJECTION_SALES) * 100 END  AS TOTAL_PROJECTION_RATE, "
                + "(Isnull(TODIS.PROJECTION_SALES, 0)) as TOTAL_PROJECTION_DOLAR ";
        LOGGER.debug("selectClause ={}",selectClause);
        return selectClause;
    }

    /**
     * Discount Query
     *
     * @param projSelDTO
     * @return String
     */
    public String getProjVarianceDiscountQuery(PVSelectionDTO projSelDTO) {
        String mainMasterTable = "  CH_DISCOUNT_PROJ_MASTER ";
        String tempMasterTable = " ST_CH_DISCOUNT_PROJ_MASTER ";
        String mainTable = " CH_PROJECTION_DISCOUNT ";
        String tempTable = " ST_CH_PROJECTION_DISCOUNT ";
        String masterTable;
        String table;
        if (!projSelDTO.isIsPrior()) {
            masterTable = tempMasterTable;
            table = tempTable;
        } else {
            masterTable = mainMasterTable;
            table = mainTable;
        }
        String selectClause = StringConstantsUtil.SPACE_SELECT;
        String whereClause = "";
        String groupBy = " I.\"YEAR\"";

        String ccpWhereCond = getCCPWhereConditionQuery("E", "CCP");

        selectClause += "I.\"YEAR\" as YEARS,  ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and  I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER  as PERIODS, ";
            whereClause += "";
            groupBy += IQUARTER;
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL  as PERIODS, ";
            whereClause += "";
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0'  as  PERIODS, ";
            whereClause += "";
            groupBy += "";

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += "I.\"MONTH\"  as PERIODS, ";
            whereClause += "";
            groupBy += ", I.\"MONTH\"";
        }

        // To filter the data according to selected period
        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        // To handle the scenario where any discount is not selected in program selection lookup
        String discountTypeColumnName = "'Total discounts'  as DISCOUNTS";
        if (projSelDTO.getDiscountNoList() != null && !projSelDTO.getDiscountNoList().isEmpty()) {
            if (!projSelDTO.isIsTotal()) {
                discountTypeColumnName = " J.RS_NAME as DISCOUNTS";
                groupBy += ", " + "  J.RS_NAME";
            }
            whereClause += " and B.RS_MODEL_SID  in (" + CommonUtils.collectionToString(projSelDTO.getDiscountNoList(), false) + ")";
        }
        selectClause += discountTypeColumnName + ", ";

        String customSql = masterTable
                + " B, PROJECTION_DETAILS E ,"
                + " \"PERIOD\" I, "                
                + CCP_CCP;
        if (!projSelDTO.isIsTotal()) {
            customSql += ",  RS_MODEL J ";
        }
        customSql += "where A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID and B.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID ";
        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase("C")) {
            customSql += getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "B")
                    + getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "A");
        }
        if (!projSelDTO.isIsTotal()) {
            customSql += "and B.RS_MODEL_SID = J.RS_MODEL_SID ";
        }
        customSql += "and A.RS_MODEL_SID = B.RS_MODEL_SID "
                + " and E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId()
                + ccpWhereCond + "and A.PERIOD_SID =  I.PERIOD_SID " + periodFilter
                + whereClause + GROUP_BY + groupBy;
        String futureQuery = StringUtils.EMPTY;
         futureQuery = futureQuery + selectClause + " 0 as ACTUAL_SALES, sum(A.PROJECTION_SALES) as PROJECTION_SALES from "
                + table + " A," + customSql;
        return futureQuery;
    }

    /**
     * Sales Query
     *
     * @param projSelDTO
     * @return
     */
    public String getProjVariancesSalesQuery(PVSelectionDTO projSelDTO) {
        String mainMasterSalesTable = "  CH_SALES_PROJECTION_MASTER ";
        String tempMasterSalesTable = " ST_CH_SALES_PROJECTION_MASTER ";
        String mainSalesTable = " CH_SALES_PROJECTION ";
        String tempSalesTable = " ST_CH_SALES_PROJECTION ";

        String masterSalesTable;
        String salesTable;

        if (!projSelDTO.isIsPrior()) {
            masterSalesTable = tempMasterSalesTable;
            salesTable = tempSalesTable;
        } else {
            masterSalesTable = mainMasterSalesTable;
            salesTable = mainSalesTable;
        }
        String selectClause = StringConstantsUtil.SPACE_SELECT;
        String whereClause = "";
        String groupBy = "  I.\"YEAR\"";
        String ccpWhereCond = getCCPWhereConditionQuery("E", "CCP");
        selectClause += "I.\"YEAR\" as YEARS,  ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and  I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER  as PERIODS, ";
            whereClause += "";
            groupBy += IQUARTER;
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, ";
            whereClause += "";
            groupBy += ",  I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0'  as  PERIODS, ";
            whereClause += "";
            groupBy += "";

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += "I.\"MONTH\"  as PERIODS, ";
            whereClause += "";
            groupBy += ",  I.\"MONTH\"";
        }
        // To filter the data according to selected period
        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        // To handle the scenario where any discount is not selected in program selection lookup
        String customSql
                = masterSalesTable + "  B1,"
                + " PROJECTION_DETAILS E, "
                + " \"PERIOD\"  I, "
                + CCP_CCP
                + "where A1.PROJECTION_DETAILS_SID = B1.PROJECTION_DETAILS_SID "
                + "and B1.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID ";
        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase("C")) {
            customSql += getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "B1")
                    + getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "A1");
        }
        customSql += " and  E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId()
                + ccpWhereCond
                + "and A1.PERIOD_SID = I.PERIOD_SID "
                + periodFilter
                + whereClause
                + GROUP_BY + groupBy;
        String futureQuery = StringUtils.EMPTY;
         futureQuery = futureQuery + selectClause
                + " 0 as SALES_ACTUAL_SALES, sum(A1.CONTRACT_SALES) as SALES_PROJECTION_SALES, "
                + " 0 as ACTUAL_UNITS, sum(A1.CONTRACT_UNITS) as PROJECTION_UNITS "
                + " from "
                + salesTable + " A1," + customSql;

        return futureQuery;
    }

    public static String getFinalQueryForCOGS(PVSelectionDTO pvsDTO) {
        String periodFilter = CommonLogic.getPeriodRestrictionQuery(pvsDTO);
        String query = " FULL JOIN ( SELECT ITEM_PRICE = COALESCE(AVG(ITEM_PRICE), 0)\n"
                + "  , I.YEAR\n"
                + "  , I.QUARTER\n"
                + "  FROM [DBO].[UDF_ITEM_PRICING](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, 'UN') U\n"
                + "  , PERIOD I\n"
                + "  WHERE I.PERIOD_SID = U.PERIOD_SID\n" + periodFilter
                + "  GROUP BY I.\"YEAR\"\n"
                + "  , I.QUARTER\n"
                + "  ) COGS\n"
                + "  ON SALEPPA.YEARS = COGS.YEAR\n"
                + "  AND SALEPPA.PERIODS = COGS.QUARTER";
        LOGGER.debug("query ={}",query);
        return query;
    }
}
