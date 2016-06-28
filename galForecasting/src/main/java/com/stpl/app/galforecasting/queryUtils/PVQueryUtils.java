/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.queryUtils;

import com.stpl.app.galforecasting.dto.PVSelectionDTO;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.projectionvariance.dto.ComparisonLookupDTO;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.CommonConstants.DATE_FORMAT;
import com.stpl.portal.kernel.dao.orm.Criterion;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.data.Container.Filter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 *
 * @author Mohamed.hameed
 */
public class PVQueryUtils {

    /**
     * The Constant LOGGER.
     */
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(PVQueryUtils.class);

    public String getCCPQuery(PVSelectionDTO projSelDTO, int projectionId) {
        String viewtable = CommonLogic.getViewTableName(projSelDTO.getHierarchyIndicator());
        String ccpQuery = " (SELECT LCCP.RELATIONSHIP_LEVEL_SID, LCCP.CCP_DETAILS_SID, LCCP.HIERARCHY_NO from "
                + "(SELECT CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID from "
                + "(SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID "
                + "FROM RELATIONSHIP_LEVEL_DEFINITION RLD "
                + "JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID "
                + "JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projectionId
                + "JOIN PROJECTION_MASTER PM  ON PD.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID"
                + " WHERE PM.PROJECTION_MASTER_SID=" + projectionId + ") CCPMAP,"
                + " (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD1"
                + " JOIN " + viewtable + " PCH "
                + " ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID"
                + " AND PCH.PROJECTION_MASTER_SID=" + projectionId
                + " WHERE RLD1.HIERARCHY_NO like '" + projSelDTO.getHierarchyNo() + "%' ) HLD"
                + " WHERE CCPMAP.HIERARCHY_NO like HLD.HIERARCHY_NO+'%' ) LCCP"
                + " WHERE LCCP.HIERARCHY_NO in"
                + " (SELECT RLD2.HIERARCHY_NO"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD2"
                + " JOIN " + viewtable + " PCH2"
                + " ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID"
                + " AND PCH2.PROJECTION_MASTER_SID=" + projectionId
                + " WHERE RLD2.LEVEL_NO=" + projSelDTO.getLevelNo() + ")) CCP ";

        return ccpQuery;
    }

    public String getCCPGeneralQuery(PVSelectionDTO projSelDTO, int projectionId) {
        String viewtable = CommonLogic.getViewTableName(projSelDTO.getHierarchyIndicator());
        String ccpQuery = " SELECT LCCP.RELATIONSHIP_LEVEL_SID, LCCP.CCP_DETAILS_SID, LCCP.HIERARCHY_NO from "
                + "(SELECT CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID from "
                + "(SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID "
                + "FROM RELATIONSHIP_LEVEL_DEFINITION RLD "
                + "JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID "
                + "JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projectionId
                + " JOIN PROJECTION_MASTER PM  ON PD.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID"
                + " WHERE PM.PROJECTION_MASTER_SID=" + projectionId + ") CCPMAP,"
                + " (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD1"
                + " JOIN " + viewtable + " PCH "
                + " ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID"
                + " AND PCH.PROJECTION_MASTER_SID=" + projectionId
                + " WHERE RLD1.HIERARCHY_NO like '" + projSelDTO.getHierarchyNo() + "%' ) HLD"
                + " WHERE CCPMAP.HIERARCHY_NO like HLD.HIERARCHY_NO+'%' ) LCCP"
                + " WHERE LCCP.HIERARCHY_NO in"
                + " (SELECT RLD2.HIERARCHY_NO"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD2"
                + " JOIN " + viewtable + " PCH2"
                + " ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID"
                + " AND PCH2.PROJECTION_MASTER_SID=" + projectionId
                + " WHERE RLD2.LEVEL_NO=" + projSelDTO.getLevelNo() + ") ";
        return ccpQuery;
    }

    public String getUserSessionQueryCondition(int userId, int sessionId, String table) {
        String user = " and " + table + ".USER_ID=" + userId + " and " + table + ".SESSION_ID=" + sessionId + " ";
        return user;
    }

    public String getProjectionResultsPivotQuery(PVSelectionDTO projSelDTO, boolean isPriorDiscount) {
        projSelDTO.setIsTotal(true);
        String selectClause = " select ";
        selectClause += "Isnull(TODIS.YEARS ,SALEPPA.YEARS ) as YEARS, Isnull(TODIS.PERIODS ,SALEPPA.PERIODS ) AS PERIODS,";
        selectClause += getPivotSelectClause();
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, isPriorDiscount);
        String salesQuery = getSalesQuery(getProjectionResultsSalesQuery(projSelDTO));
        String ppaQuery = getPPAQuery(getPPAProjectionResultsQuery(projSelDTO, Boolean.FALSE), Boolean.FALSE);
        String customQuery = selectClause + " from " + getPivotSelectQuery(totalDiscountQuery, salesQuery, ppaQuery);
        return customQuery;
    }

    public String getPVMainDiscountDolQuery(PVSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(false);
        String selectClause = " select C.YEARS as YEARS, C.PERIODS AS PERIODS,C.DISCOUNTS AS DISCOUNTS,";
        String customQuery = StringUtils.EMPTY;
        String orderBy = " DISCOUNTS, YEARS,PERIODS ";
        String projectedSales = "PROJECTION_SALES";
        if (projSelDTO.getGroup().contains(CommonUtils.COL_VALUE)) {
            selectClause += "C." + projectedSales + " AS C_PROJECTION_SALES";
        } else if (projSelDTO.getGroup().contains(CommonUtils.COL_VARIANCE)) {
            selectClause += "0  AS C_PROJECTION_SALES";
        } else {
            selectClause += "0  AS C_PROJECTION_SALES";
        }
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            if (projSelDTO.getGroup().contains(CommonUtils.COL_VALUE)) {
                selectClause += ", P" + i + "." + projectedSales + " AS P" + i + "_PROJECTION_SALES ";
            } else if (projSelDTO.getGroup().contains(CommonUtils.COL_VARIANCE)) {
                selectClause += ", (IsNull(C." + projectedSales + ", 0)- IsNull(P" + i + "." + projectedSales + ", 0)) AS P" + i + "_PROJECTION_SALES ";
            } else {
                selectClause += ", CASE WHEN P" + i + "." + projectedSales + " = 0 THEN 0\n"
                        + " ELSE ( IsNull(C." + projectedSales + ", 0) - IsNull(P" + i + "." + projectedSales + ", 0) / P" + i + "." + projectedSales + " )  \n"
                        + " END  AS P" + i + "_PROJECTION_SALES ";
            }

        }
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        projSelDTO.setIsPrior(false);
        customQuery += selectClause + " from  \n(" + getProjectionResultsDiscountsQuery(projSelDTO, Boolean.FALSE) + ") C\n ";
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
        projSelDTO.setIsPrior(true);
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            projSelDTO.setProjectionId(projSelDTO.getProjIdList().get(i));
            customQuery += " LEFT JOIN (\n" + getProjectionResultsDiscountsQuery(projSelDTO, Boolean.TRUE) + "\n) " + Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY + i + " \n on C.DISCOUNTS=P" + i + ".DISCOUNTS \n "
                    + " AND C.YEARS=P" + i + ".YEARS \n "
                    + " and C.PERIODS=P" + i + ".PERIODS  ";
        }
        customQuery += " order by " + orderBy;
        projSelDTO.setIsPrior(false);
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        return customQuery;
    }

    public String getPVMainDiscountPerQuery(PVSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(false);
        String selectClause = " select C.YEARS as YEARS, C.PERIODS AS PERIODS,C.DISCOUNTS AS DISCOUNTS,";
        String customQuery = StringUtils.EMPTY;
        String orderBy = " DISCOUNTS, YEARS,PERIODS ";
        String projectedSales = StringUtils.EMPTY;
        if (projSelDTO.isRPU()) {
            projectedSales = "RPU ";
        } else {
            projectedSales = "PROJECTION_RATE ";
        }
        if (projSelDTO.getGroup().contains(CommonUtils.COL_VALUE)) {
            selectClause += "C." + projectedSales + " AS C_PROJECTION_RATE";
        } else if (projSelDTO.getGroup().contains(CommonUtils.COL_VARIANCE)) {
            selectClause += "0  AS PROJECTION_RATE";
        } else {
            selectClause += "0  AS PROJECTION_RATE";
        }
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            if (projSelDTO.getGroup().contains(CommonUtils.COL_VALUE)) {
                selectClause += ", P" + i + "." + projectedSales + " AS P" + i + "_" + projectedSales + " ";
            } else if (projSelDTO.getGroup().contains(CommonUtils.COL_VARIANCE)) {
                selectClause += ", (IsNull(C." + projectedSales + ", 0)- IsNull(P" + i + "." + projectedSales + ", 0)) AS P" + i + "_" + projectedSales + " ";
            } else {
                selectClause += ", CASE WHEN P" + i + "." + projectedSales + " = 0 THEN 0\n"
                        + " ELSE (IsNull(C." + projectedSales + ", 0) - IsNull(P" + i + "." + projectedSales + ", 0) / P" + i + "." + projectedSales + ") \n"
                        + " END  AS P" + i + "_" + projectedSales + " ";
            }
        }
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        projSelDTO.setIsPrior(false);
        customQuery += selectClause + " from  \n(" + getProjectionResultsDiscountsPerQuery(projSelDTO) + ") C\n ";
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
        projSelDTO.setIsPrior(true);
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            projSelDTO.setProjectionId(projSelDTO.getProjIdList().get(i));
            customQuery += " LEFT JOIN (\n" + getProjectionResultsDiscountsPerQuery(projSelDTO) + "\n) " + Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY + i + " \n on C.DISCOUNTS=P" + i + ".DISCOUNTS \n "
                    + " AND C.YEARS=P" + i + ".YEARS \n "
                    + " and C.PERIODS=P" + i + ".PERIODS  ";
        }
        customQuery += " order by " + orderBy;
        projSelDTO.setIsPrior(false);
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        return customQuery;
    }

    public String getPeriodDiscountExpand(PVSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(false);
        String selectClause = " select C.YEARS as YEARS, C.PERIODS AS PERIODS,C.DISCOUNTS AS DISCOUNTS, ";
        String customQuery = StringUtils.EMPTY;
        String orderBy = " DISCOUNTS, YEARS,PERIODS ";
        String projectedSales = StringUtils.EMPTY;
//        String projectedReturns = StringUtils.EMPTY;
        String projectionPPA = StringUtils.EMPTY;
        projSelDTO.setSales(Constant.SALES_WHOLE_CAPS);
        if (projSelDTO.getDiscountGroupName().contains(CommonUtils.VAR_DIS_AMOUNT)) {
            projectedSales = "PROJECTION_SALES";
//            projectedReturns = "RETURNS_PROJECTED_AMOUNT";
//            projectionPPA = "PPA_DISCOUNT_AMOUNT";
            if (projSelDTO.getDiscountGroupName().contains(CommonUtils.COL_VALUE)) {
                selectClause += "C." + projectedSales + " AS C_PROJECTION_AMNT";
//                selectClause += "C." + projectedReturns + " AS C_RETURNS_AMNT,";
//                selectClause += "C." + projectionPPA + " AS C_PPA_AMNT";
            } else if (projSelDTO.getDiscountGroupName().contains(CommonUtils.COL_VARIANCE)) {
                selectClause += "0  AS PROJECTION_AMNT";
//                selectClause += "0  AS RETURNS_AMN,";
//                selectClause += "0  AS PPA_AMN";
            } else {
                selectClause += "0  AS PROJECTION_AMNT";
//                selectClause += "0  AS RETURNS_AMN,";
//                selectClause += "0  AS PPA_AMN";
            }
        } else if (projSelDTO.getDiscountGroupName().contains(CommonUtils.VAR_DIS_RATE)) {
            projSelDTO.setSales(Constant.RATE);
            projectedSales = "PROJECTION_RATE";
//            projectedReturns = "RETURNS_PROJECTED_RATE";
//            projectionPPA = "PPA_DISCOUNT_PERCENT";
            if (projSelDTO.getDiscountGroupName().contains(CommonUtils.COL_VALUE)) {
                selectClause += "C." + projectedSales + " AS C_PROJECTION_RATE";
//                selectClause += "C." + projectedReturns + " AS C_RETURNS_RATE,";
//                selectClause += "C." + projectionPPA + " AS C_PPA_RATE";
            } else if (projSelDTO.getDiscountGroupName().contains(CommonUtils.COL_VARIANCE)) {
                selectClause += "0  AS PROJECTION_RATE";
//                selectClause += "0  AS RETURNS_RATE,";
//                selectClause += "0  AS PPA_RATE";
            } else {
                selectClause += "0  AS PROJECTION_RATE";
//                selectClause += "0  AS RETURNS_RATE,";
//                selectClause += "0  AS PPA_RATE";
            }
        } else {
            projectedSales = "RPU";
//            projectedReturns = "RETURNS_RPU";
//            projectionPPA = "PPA_DISCOUNT_RPU";
            if (projSelDTO.getDiscountGroupName().contains(CommonUtils.COL_VALUE)) {
                selectClause += "C." + projectedSales + " AS C_RPU";
//                selectClause += "C." + projectedReturns + " AS C_RETURNS,";
//                selectClause += "C." + projectionPPA + " AS C_PPA";
            } else if (projSelDTO.getDiscountGroupName().contains(CommonUtils.COL_VARIANCE)) {
                selectClause += "0  AS C_RPU";
//                selectClause += "0  AS C_RETURNS,";
//                selectClause += "0  AS C_PPA";
            } else {
                selectClause += "0  AS C_RPU";
//                selectClause += "0  AS C_RETURNS,";
//                selectClause += "0  AS C_PPA";
            }
        }
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            if (projSelDTO.getGroup().contains(CommonUtils.COL_VALUE)) {
                selectClause += ", P" + i + "." + projectedSales + " AS P" + i + "_" + projectedSales + " ";
//                selectClause += ", P" + i + "." + projectedReturns + " AS P" + i + "_" + projectedReturns + " ";
//                selectClause += ", P" + i + "." + projectionPPA + " AS P" + i + "_" + projectionPPA + " ";
            } else if (projSelDTO.getGroup().contains(CommonUtils.COL_VARIANCE)) {
                selectClause += ", (IsNull(C." + projectedSales + ", 0)- IsNull(P" + i + "." + projectedSales + ", 0)) AS P" + i + "_" + projectedSales + " ";
//                selectClause += ", (IsNull(C." + projectedReturns + ", 0)- IsNull(P" + i + "." + projectedReturns + ", 0)) AS P" + i + "_" + projectedReturns + " ";
//                selectClause += ", (IsNull(C." + projectionPPA + ", 0)- IsNull(P" + i + "." + projectionPPA + ", 0)) AS P" + i + "_" + projectionPPA + " ";
            } else {
                selectClause += ", CASE WHEN P" + i + "." + projectedSales + " = 0 THEN 0\n"
                        + " ELSE (IsNull(C." + projectedSales + ", 0) - IsNull(P" + i + "." + projectedSales + ", 0)) / P" + i + "." + projectedSales + " \n"
                        + " END  AS P" + i + "_" + projectedSales + " ";
//                selectClause += ", CASE WHEN P" + i + "." + projectedSales + " = 0 THEN 0\n"
//                        + " ELSE (IsNull(C." + projectedReturns + ", 0) - IsNull(P" + i + "." + projectedReturns + ", 0)) / P" + i + "." + projectedSales + " \n"
//                        + " END  AS P" + i + "_" + projectedReturns + " ";
//                selectClause += ", CASE WHEN P" + i + "." + projectionPPA + " = 0 THEN 0\n"
//                        + " ELSE (IsNull(C." + projectionPPA + ", 0) - IsNull(P" + i + "." + projectionPPA + ", 0)) / P" + i + "." + projectionPPA + " \n"
//                        + " END  AS P" + i + "_" + projectionPPA + " ";
            }
        }
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        projSelDTO.setIsPrior(false);
        customQuery += selectClause + " from  \n(" + getProjectionResultsDiscountsPerQuery(projSelDTO) + ") C\n ";
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
        projSelDTO.setIsPrior(true);
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            projSelDTO.setProjectionId(projSelDTO.getProjIdList().get(i));
            customQuery += " LEFT JOIN (\n" + getProjectionResultsDiscountsPerQuery(projSelDTO) + "\n) " + Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY + i + " \n on C.DISCOUNTS=P" + i + ".DISCOUNTS \n "
                    + " AND C.YEARS=P" + i + ".YEARS \n "
                    + " and C.PERIODS=P" + i + ".PERIODS  ";
        }
        customQuery += " order by " + orderBy;
        projSelDTO.setIsPrior(false);
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        return customQuery;
    }

    public String getProjectionResultsMainPivotQuery(PVSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(true);
        String selectClause = " select ";
        String customQuery = StringUtils.EMPTY;
        String orderBy = " YEARS,PERIODS ";
        selectClause += "C.YEARS as YEARS, C.PERIODS AS PERIODS,";

        customQuery = selectClause + getPivotMainSelectClause(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            customQuery += ", " + getPivotMainSelectClause(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY + i);
        }
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        projSelDTO.setIsPrior(false);
        customQuery += " from  \n(" + getProjectionResultsPivotQuery(projSelDTO, Boolean.FALSE) + ") C\n ";
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
        projSelDTO.setIsPrior(true);
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            projSelDTO.setProjectionId(projSelDTO.getProjIdList().get(i));
            customQuery += " LEFT JOIN (\n" + getProjectionResultsPivotQuery(projSelDTO, Boolean.TRUE) + "\n) " + Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY + i + " \n" + getPivotMainWhereCond(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY + i);
        }
        customQuery += " order by " + orderBy;
        projSelDTO.setIsPrior(false);
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        return customQuery;
    }

    public String getPivotSelectQuery(String query1, String query2, String query3) {
        String finalWhereCond = " on TODIS.YEARS=SALEPPA.YEARS AND TODIS.PERIODS=SALEPPA.PERIODS";
        String finalWhereCond1 = " ON TODIS.YEARS=PPA.YEARS AND TODIS.PERIODS=PPA.PERIODS";
        String selectClause = " (\n" + query1 + "\n) TODIS FULL JOIN (\n" + query2 + "\n) SALEPPA \n " + finalWhereCond + " FULL JOIN (\n" + query3 + "\n) PPA" + finalWhereCond1;
        return selectClause;
    }

    public String getPivotMainWhereCond(String table) {
        return " on C.YEARS=" + table + ".YEARS AND C.PERIODS=" + table + ".PERIODS \n";
    }

    public String getPivotSelectClause() {
        String selectClause = " SALEPPA.SALES_PROJECTION_SALES as CONTRACT_PROJECTION_SALES,"
                + "SALEPPA.PROJECTION_UNITS as CONTRACT_PROJECTION_UNITS,"
                //                + " CASE WHEN SALEPPA.sales_projection_sales = 0 THEN 0 ELSE (((Isnull(TODIS.projection_sales, 0)) +ISNULL(PPA.SALES_PPA,0) + ISNULL(SALEPPA.PROJECTED_RATE, 0)) / NULLIF(SALEPPA.sales_projection_sales,0))*100 END AS TOTAL_PROJECTION_RATE, "
                + " CASE WHEN SALEPPA.sales_projection_sales = 0 THEN 0 ELSE (Isnull(TODIS.projection_sales/ NULLIF(SALEPPA.sales_projection_sales, 0), 0) + ISNULL(PPA.SALES_PPA/ NULLIF(ppa.ppa_proj_sales, 0), 0)) * 100 END AS TOTAL_PROJECTION_RATE, "
                //                + " (Isnull(TODIS.projection_sales, 0) +ISNULL(PPA.SALES_PPA,0) + ISNULL(SALEPPA.RETURNS_PROJECTED, 0)) AS TOTAL_PROJECTION_DOLAR,"
                + " (Isnull(TODIS.projection_sales, 0) + ISNULL(PPA.SALES_PPA, 0)) AS TOTAL_PROJECTION_DOLAR,"
                + "(Isnull(SALEPPA.SALES_PROJECTION_SALES, 0) - (Isnull(TODIS.PROJECTION_SALES, 0) + ISNULL(PPA.SALES_PPA, 0))) AS NET_PROJECTION_SALES, "
                //                + " CASE WHEN SALEPPA.projection_units = 0 THEN 0 ELSE(IsNull((IsNull(TODIS.projection_sales, 0) +ISNULL(PPA.SALES_RPU,0) +IsNull(SALEPPA.RETURNS_PROJECTED, 0)) / NULLIF(SALEPPA.PROJECTION_UNITS, 0), 0)) END AS RPU,\n"
                + " CASE WHEN SALEPPA.projection_units = 0 THEN 0 ELSE (IsNull(TODIS.projection_sales/ NULLIF(SALEPPA.PROJECTION_UNITS, 0), 0) + ISNULL(PPA.SALES_PPA/ NULLIF(ppa.ppa_proj_units, 0), 0)) END AS RPU,\n"
                + " COGS_PROJECTED = ISNULL(SALEPPA.COGS_PROJECTED, 0),\n"
                + " NET_PROFIT_PROJECTED = ((Isnull(SALEPPA.SALES_PROJECTION_SALES, 0) - (Isnull(TODIS.PROJECTION_SALES, 0) + ISNULL(PPA.SALES_PPA, 0))) - ISNULL(SALEPPA.COGS_PROJECTED, 0))";
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
        return selectClause;
    }

    public String getProjectionResultsDiscountsQuery(PVSelectionDTO projSelDTO, boolean isPriorDiscount) {
        String mainMasterTable = "  NM_DISCOUNT_PROJ_MASTER ";
        String tempMasterTable = " ST_NM_DISCOUNT_PROJ_MASTER ";
        String mainTable = " NM_DISCOUNT_PROJECTION ";
        String tempTable = " ST_NM_DISCOUNT_PROJECTION ";
        String masterTable = StringUtils.EMPTY;
        String table = StringUtils.EMPTY;
        if (!projSelDTO.isIsPrior()) {
            masterTable = tempMasterTable;
            table = tempTable;
        } else {
            masterTable = mainMasterTable;
            table = mainTable;
        }
        String selectClause = " select ";
        String whereClause = StringUtils.EMPTY;
        String groupBy = " I.\"YEAR\"";

        String customQuery = StringUtils.EMPTY;
        String ccpWhereCond = getCCPWhereConditionQuery("E", "CCP");
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == 4) {
            selectClause += "I.QUARTER as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == 12) {
            selectClause += "I.\"MONTH\" as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.\"MONTH\"";
        }

        // To filter the data according to selected period
//        String periodFilter = CommonLogic.getPeriodRestrictionQueryforPV(projSelDTO);
        // To handle the scenario where any discount is not selected in program selection lookup
        String discountTypeColumnName = "'Total discounts' as DISCOUNTS";
        if (projSelDTO.getDiscountLevel().equals("Program Category")) {
            if (projSelDTO.getDiscountNameList() != null && !projSelDTO.getDiscountNameList().isEmpty()) {
                if (!projSelDTO.isIsTotal()) {
                    discountTypeColumnName = " B.PRICE_GROUP_TYPE AS DISCOUNTS";
                    groupBy += ", " + " B.PRICE_GROUP_TYPE";
                }
                if (!isPriorDiscount) {
                    whereClause += " WHERE B.PRICE_GROUP_TYPE in (" + CommonUtils.CollectionToString(projSelDTO.getDiscountNameList(), true) + ")";
                }
            }
        } else {
            if (projSelDTO.getDiscountNoList() != null && !projSelDTO.getDiscountNoList().isEmpty()) {
                if (!projSelDTO.isIsTotal()) {
                    discountTypeColumnName = " J.RS_NAME as DISCOUNTS";
                    groupBy += ", " + " J.RS_NAME";
                }
                if (!isPriorDiscount) {
                    whereClause += " WHERE B.RS_MODEL_SID in (" + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + ")";
                }
            }
        }
        selectClause += discountTypeColumnName + ", ";

        String futureQuery = selectClause + "sum(A.PROJECTION_SALES) as PROJECTION_SALES, Sum(A.PROJECTION_RPU) AS RPU from \n";

        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
            futureQuery += "#TEMP_CCP T \n"
                    + "                   JOIN   " + table + " A ON A.PROJECTION_DETAILS_SID = T.PROJECTION_DETAILS_SID\n"
                    + "                   JOIN    " + masterTable + " B ON A.PROJECTION_DETAILS_SID=B.PROJECTION_DETAILS_SID AND A.RS_MODEL_SID = B.RS_MODEL_SID\n "
                    +"                    AND A.USER_ID = B.USER_ID\n"
                    + "                   AND A.SESSION_ID = B.SESSION_ID "
                    +"                    JOIN   @PERIOD I ON I.PERIOD_SID = A.PERIOD_SID\n"
                    + "                   JOIN   @CCP CCP ON T.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID ";
        } else {
            futureQuery += table+" A\n"
                    + "                      JOIN   " + masterTable + " B ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID\n"
                    + "                                                      AND A.RS_MODEL_SID = B.RS_MODEL_SID\n"
                    + "                      JOIN   PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID\n"
                    + "                                                  AND PD.PROJECTION_MASTER_SID = "+ projSelDTO.getProjectionId() +"\n"
                    + "                      JOIN   @PERIOD I ON I.PERIOD_SID = A.PERIOD_SID ";
        }
        if (!projSelDTO.isIsTotal()) {
            futureQuery += " JOIN RS_MODEL J ON J.RS_MODEL_SID = B.RS_MODEL_SID ";
        }
        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
            futureQuery += getUserSessionQueryCondition(Integer.valueOf(projSelDTO.getUserId()), Integer.valueOf(projSelDTO.getSessionId()), "A");
        }
        futureQuery += whereClause + " GROUP BY " + groupBy;
        customQuery = futureQuery;
        return customQuery;
    }

    public String getProjectionResultsSalesQuery(PVSelectionDTO projSelDTO) {
//        String mainMasterSalesTable = "  NM_SALES_PROJECTION_MASTER ";
//        String tempMasterSalesTable = " ST_NM_SALES_PROJECTION_MASTER ";
        String mainSalesTable = " NM_SALES_PROJECTION ";
        String tempSalesTable = " ST_NM_SALES_PROJECTION ";

//        String masterSalesTable = StringUtils.EMPTY;
        String salesTable = StringUtils.EMPTY;

        if (!projSelDTO.isIsPrior()) {
//            masterSalesTable = tempMasterSalesTable;
            salesTable = tempSalesTable;
        } else {
//            masterSalesTable = mainMasterSalesTable;
            salesTable = mainSalesTable;
        }
        String selectClause = " select ";
        String whereClause = StringUtils.EMPTY;
        String groupBy = " YEARS";
        String ccpWhereCond = getCCPWhereConditionQuery("E", "CCP");
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == 4) {
            selectClause += "I.QUARTER as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", PERIODS";

        } else if (projSelDTO.getFrequencyDivision() == 12) {
            selectClause += "I.\"MONTH\" as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", PERIODS";
        }
        // To filter the data according to selected period
//        String periodFilter = CommonLogic.getPeriodRestrictionQueryforPV(projSelDTO);

        // To handle the scenario where any discount is not selected in program selection lookup
        String customSql = "";
        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            customSql += " FROM NM_SALES_PROJECTION A1\n"
                    + "                              INNER JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = A1.PROJECTION_DETAILS_SID\n"
                    + "                                                              AND PD.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                    + "                              INNER JOIN CCP_DETAILS CC ON CC.CCP_DETAILS_SID = PD.CCP_DETAILS_SID"
                    +"                           LEFT JOIN @COGS_ITEM U ON CC.ITEM_MASTER_SID = U.ITEM_MASTER_SID\n";
        } else {
            customSql += " from "
                    + salesTable + " A1 INNER JOIN #TEMP_CCP T ON T.PROJECTION_DETAILS_SID = A1.PROJECTION_DETAILS_SID "
                    + "                           INNER JOIN @CCP CC ON CC.CCP_DETAILS_SID = T.CCP_DETAILS_SID\n"
                    +"                           LEFT JOIN @COGS_ITEM U ON T.ITEM_MASTER_SID = U.ITEM_MASTER_SID\n";
        }

        customSql += "                                                  AND A1.PERIOD_SID = U.PERIOD_SID\n"
                + "                           INNER JOIN @PERIOD I ON I.PERIOD_SID = A1.PERIOD_SID  "
                + "\n";
        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
            customSql += " WHERE   A1.SESSION_ID = " + projSelDTO.getSessionId() + "\n"
                    + "    AND A1.USER_ID = " + projSelDTO.getUserId() + "\n";
        }

        customSql += "  )A"
                + " group by  " + groupBy;

        String futureQuery = selectClause
                + " (A1.PROJECTION_SALES) "
                + " ,(A1.PROJECTION_UNITS)"
                //                + " , TR.PROJECTED_RATE\n"
                + " , A1.PERIOD_SID\n"
                + " , U.ITEM_PRICE"
                + customSql;

        return futureQuery;
    }

    public String getPPAProjectionResultsQuery(PVSelectionDTO projSelDTO, Boolean flag) {
//        String mainMasterSalesTable = "  NM_PPA_PROJECTION_MASTER ";
//        String tempMasterSalesTable = " ST_NM_PPA_PROJECTION_MASTER ";
        String mainSalesTable = " NM_PPA_PROJECTION ";
        String tempSalesTable = " ST_NM_PPA_PROJECTION ";
        String rsCondition = flag ? ", CASE WHEN @PROGRAM_TYPE='PROGRAM' THEN R.RS_NAME ELSE 'Price Protection' END RS_NAME" : StringUtils.EMPTY;
        String rsCondition1 = flag ? ", AA.RS_NAME" : StringUtils.EMPTY;
        String rsJoin = flag ? " INNER JOIN RS_MODEL R ON A1.RS_MODEL_SID = R.RS_MODEL_SID " : StringUtils.EMPTY;
//        String masterSalesTable = StringUtils.EMPTY;
        String salesTable = StringUtils.EMPTY;

        if (!projSelDTO.isIsPrior()) {
//            masterSalesTable = tempMasterSalesTable;
            salesTable = tempSalesTable;
        } else {
//            masterSalesTable = mainMasterSalesTable;
            salesTable = mainSalesTable;
        }
        String selectClause = " select ";
        String whereClause = StringUtils.EMPTY;
        String groupBy = " I.\"YEAR\"";
        String ccpWhereCond = getCCPWhereConditionQuery("E", "CCP");
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == 4) {
            selectClause += "I.QUARTER as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == 12) {
            selectClause += "I.\"MONTH\" as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.\"MONTH\"";
        }
        // To filter the data according to selected period
//        String periodFilter = CommonLogic.getPeriodRestrictionQueryforPV(projSelDTO);

        // To handle the scenario where any discount is not selected in program selection lookup
        String customSql = "";
        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            customSql += "      JOIN       PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = A1.PROJECTION_DETAILS_SID\n"
                    + "                                                              AND PD.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n";
        } else {
            customSql += " INNER JOIN #TEMP_CCP T ON T.PROJECTION_DETAILS_SID = A1.PROJECTION_DETAILS_SID\n"
                    +"                      INNER JOIN @CCP CC ON CC.CCP_DETAILS_SID = T.CCP_DETAILS_SID\n";
        }
        customSql +="                           INNER JOIN @PERIOD I ON I.PERIOD_SID = A1.PERIOD_SID \n" + rsJoin + "   ";
        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
            customSql += " JOIN ST_NM_SALES_PROJECTION ns on ns.PROJECTION_DETAILS_SID=A1.PROJECTION_DETAILS_SID\n"
                    + "			AND A1.PERIOD_SID=ns.PERIOD_SID\n"
                    + "			AND ns.SESSION_ID=A1.SESSION_ID\n"
                    + "			AND ns.USER_ID=A1.USER_ID "
                    + "WHERE   A1.SESSION_ID = " + projSelDTO.getSessionId() + "\n"
                    + "    AND A1.USER_ID = " + projSelDTO.getUserId() + " \n";
        } else {
            customSql += " JOIN NM_SALES_PROJECTION ns on ns.PROJECTION_DETAILS_SID=A1.PROJECTION_DETAILS_SID\n"
                    + "			and ns.PERIOD_SID=A1.PERIOD_SID";
        }
        customSql += "  )AA"
                + " group by  YEARS,PERIODS" + rsCondition1;

        String futureQuery = selectClause
                + " A1.PROJECTION_DISCOUNT_DOLLAR "
                + " ,A1.PROJECTION_RATE ,CASE \n"
                + "					WHEN a1.PROJECTION_DISCOUNT_DOLLAR IS NOT NULL\n"
                + "						AND a1.PROJECTION_DISCOUNT_DOLLAR <> 0\n"
                + "						THEN ns.PROJECTION_SALES\n"
                + "					ELSE 0\n"
                + "					END ppa_proj_sales\n"
                + "				,CASE \n"
                + "					WHEN a1.PROJECTION_DISCOUNT_DOLLAR IS NOT NULL\n"
                + "						AND a1.PROJECTION_DISCOUNT_DOLLAR <> 0\n"
                + "						THEN ns.PROJECTION_UNITS\n"
                + "					ELSE 0\n"
                + "					END ppa_proj_units\n"
                + rsCondition + "\n"
                + " from "
                + salesTable + " A1 " + customSql;
        return futureQuery;
    }

    public String getSalesQuery(final String salesInnerJoin) {
        String query = "SELECT YEARS\n"
                + " , PERIODS\n"
                + " , Sum(projection_sales) AS SALES_PROJECTION_SALES\n"
                + "  , Sum(projection_units) AS PROJECTION_UNITS\n"
                + " , (Isnull(Sum(PROJECTION_UNITS), 0) * Isnull(Avg(nullif(ITEM_PRICE,0)), 0)) AS COGS_PROJECTED\n"
                //                + " , PROJECTED_RATE = Avg(PROJECTED_RATE)\n"
                //                + " , RETURNS_PROJECTED = Sum(PROJECTION_SALES) * Avg(PROJECTED_RATE)\n"
                + " FROM ( " + salesInnerJoin;
        return query;
    }

    public String getPPAQuery(final String salesInnerJoin, Boolean flag) {
        String query = "SELECT AA.YEARS\n"
                + " , AA.PERIODS\n";
        query += flag ? " , RS_NAME AS PPA_DISCOUNT \n" : StringUtils.EMPTY + "\n";
        query += " , SUM(AA.PROJECTION_DISCOUNT_DOLLAR) AS SALES_PPA \n"
                + "  ,  SUM(AA.PROJECTION_RATE) AS SALES_RPU"
                + ",    SUM(ppa_proj_sales) ppa_proj_sales\n"
                + ",    SUM(ppa_proj_units) ppa_proj_units \n"
                + " FROM ( " + salesInnerJoin;
        return query;
    }

    public String getCCPWhereConditionQuery(String projectionDetails, String CCP) {
        String ccpWhereCond = " and " + CCP + ".CCP_DETAILS_SID=" + projectionDetails + ".CCP_DETAILS_SID ";
        return ccpWhereCond;
    }

//    public String getProjectionResultsDiscountsPivotQuery(PVSelectionDTO projSelDTO) {
//        String selectClause = " select ";
//        String customQuery = StringUtils.EMPTY;
//        List<String> list = getCommonSelectWhereOrderGroupByClause("TODIS", "SALEPPA", projSelDTO.getFrequencyDivision(), "on");
//        selectClause += list.get(0);
//        String finalWhereCond = list.get(1);
//        selectClause += "TODIS.DISCOUNTS, ";
//        selectClause += " PROJECTION_RATE=Isnull(TODIS.PROJECTION_SALES / NULLIF(SALEPPA.SALES_PROJECTION_SALES, 0), 0)";
//        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, StringUtils.EMPTY);
//        String salesPpaQuery = getProjectionResultsSalesQuery(projSelDTO);
//        customQuery = selectClause + " from (" + totalDiscountQuery + ") TODIS FULL OUTER JOIN (" + salesPpaQuery + ") SALEPPA " + finalWhereCond;
//
//        return customQuery;
//    }
    public String getComparisonSearch(String workflowStatus, String marketType, String brand,
            String projName, String contHldr, String ndcNo, String ndcName, String desc, String contract,
            String from, String to, String notSearchProjId) {
        String QUOTES = "'";
        String ASTERIK = "*";
        String PERCENT = Constant.PERCENT;
        String workflowStatusVal;
        String marketTypeVal;
        String brandVal;
        String projNameVal;
        String contHldrVal;
        String ndcNoVal;
        String ndcNameVal;
        String descVal;
        String contractVal;
        String fromVal;
        String toVal;
        Session session = null;
        boolean isProjectionStatus = false;
        try {
            StringBuilder customSql = new StringBuilder(StringUtils.EMPTY);

            if (workflowStatus.equals(Constant.SAVED)) {
                isProjectionStatus = true;
            }
            if (isProjectionStatus) {
                customSql = new StringBuilder(CustomSQLUtil.get("getProjectionLists"));
            } else {
                customSql = new StringBuilder(CustomSQLUtil.get("getWorkFlowLists"));
            }

            if (marketType == null || marketType.equals(StringUtils.EMPTY)) {
                marketTypeVal = "'%'";
            } else {
                marketTypeVal = marketType.replace(ASTERIK, PERCENT);
                marketTypeVal = QUOTES + marketTypeVal + QUOTES;
            }
            customSql.append("( HT.list_name = 'CONTRACT_TYPE' AND HT.DESCRIPTION LIKE " + marketTypeVal + ")");
            if (brand == null || brand.equals(StringUtils.EMPTY)) {
                brandVal = "'%'";
            } else {
                brandVal = brand.replace(ASTERIK, PERCENT);
                brandVal = QUOTES + brandVal + QUOTES;
            }
            customSql.append("  AND (BM.BRAND_NAME LIKE " + brandVal + " or BM.BRAND_NAME is null)");
            if (projName == null || projName.equals(StringUtils.EMPTY)) {
                projNameVal = "'%'";
            } else {
                projNameVal = projName.replace(ASTERIK, PERCENT);
                projNameVal = QUOTES + projNameVal + QUOTES;
            }
            customSql.append("AND (PM.PROJECTION_NAME LIKE " + projNameVal + " or PM.PROJECTION_NAME is null)");
            if (contHldr == null || contHldr.equals(StringUtils.EMPTY)) {
                contHldrVal = "'%'";
            } else {
                contHldrVal = contHldr.replace(ASTERIK, PERCENT);
                contHldrVal = QUOTES + contHldrVal + QUOTES;
            }
            customSql.append("AND (C.CONTRACT_NO LIKE " + contHldrVal + " or C.CONTRACT_NO is null)");
            if (ndcName == null || ndcName.equals(StringUtils.EMPTY)) {
                ndcNameVal = "'%'";
            } else {
                ndcNameVal = ndcName.replace(ASTERIK, PERCENT);
                ndcNameVal = QUOTES + ndcNameVal + QUOTES;
            }
            customSql.append("AND (IM.ITEM_NAME LIKE " + ndcNameVal + " or IM.ITEM_NAME is null)");
            if (ndcNo == null || ndcNo.equals(StringUtils.EMPTY)) {
                ndcNoVal = "'%'";
            } else {
                ndcNoVal = ndcNo.replace(ASTERIK, PERCENT);
                ndcNoVal = QUOTES + ndcNoVal + QUOTES;
            }
            customSql.append("AND (IM.ITEM_NO LIKE " + ndcNoVal + "or IM.ITEM_NO is null)");
            if (contract == null || contract.equals(StringUtils.EMPTY)) {
                contractVal = "'%'";
            } else {
                contractVal = contract.replace(ASTERIK, PERCENT);
                contractVal = QUOTES + contractVal + QUOTES;
            }
            customSql.append("AND (CM.COMPANY_NO LIKE " + contractVal + "or CM.COMPANY_NO is null)");
            if (desc == null || desc.equals(StringUtils.EMPTY)) {
                descVal = "'%'";
            } else {
                descVal = desc.replace(ASTERIK, PERCENT);
                descVal = QUOTES + descVal + QUOTES;
            }
            customSql.append("AND (PM.PROJECTION_NAME LIKE " + descVal + "or PM.PROJECTION_NAME is null)");
            if (isProjectionStatus) {
                customSql.append("and pm.is_approved not in ('Y','C','A','R')");
            } else {
                customSql.append("AND HT1.list_name = 'WorkFlowStatus' and ht1.description = " + QUOTES + workflowStatus + QUOTES);
            }
            if (from != null && !Constant.NULL.equals(from) && !StringUtils.isEmpty(from)
                    && to != null && !Constant.NULL.equals(to) && !StringUtils.isEmpty(to)) {
                SimpleDateFormat format2 = new SimpleDateFormat(DATE_FORMAT.getConstant());
                customSql.append(" AND PM.CREATED_DATE BETWEEN '");
                customSql.append(format2.format(format2.parse(from)));
                customSql.append("' AND '");
                customSql.append(format2.format(format2.parse(to)));
                customSql.append("' ");
            }
            customSql.append("AND PM.PROJECTION_MASTER_SID NOT IN (" + notSearchProjId + ")");
            customSql.append("AND PM.FORECASTING_TYPE='Non Mandated'");

            customSql.append(" group by  pm.projection_name,pm.projection_description,ht.description ,cm.company_no  ,c.contract_no  ,pm.projection_master_sid,PM.created_date,PM.created_by ");

            return customSql.toString();
        } catch (Exception e) {
            LOGGER.error(e + " at PvQueryUtils -> getComparisonSearch");
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
            String customSql = CustomSQLUtil.get("getProjectionLists");
            if (projId != null && !projId.isEmpty()) {
                customSql += (" PM.PROJECTION_MASTER_SID IN (" + CommonUtils.CollectionToString(projId, false) + ")");
            } else {
                customSql += (" PM.PROJECTION_MASTER_SID IN ('')");
            }
            customSql += (" group by  pm.projection_name,pm.projection_description,ht.description ,pm.projection_master_sid,PM.created_date,PM.created_by ");

            return customSql;
        } catch (Exception e) {
            LOGGER.error(e + " at PvQueryUtils -> getComparisonSearch");
            return null;
        }
    }

    public String getProjectionResultsDiscountsQuery(PVSelectionDTO projSelDTO, String orderBy) {
        String mainMasterTable = "  NM_DISCOUNT_PROJ_MASTER ";
        String tempMasterTable = " ST_NM_DISCOUNT_PROJ_MASTER ";
        String mainTable = " NM_DISCOUNT_PROJECTION ";
        String tempTable = " ST_NM_DISCOUNT_PROJECTION ";
        String masterTable = StringUtils.EMPTY;
        String table = StringUtils.EMPTY;
        String selectClause = " select ";
        String whereClause = StringUtils.EMPTY;
        String groupBy = "  I.\"YEAR\"";
        if (!projSelDTO.isIsPrior()) {
            masterTable = tempMasterTable;
            table = tempTable;
        } else {
            masterTable = mainMasterTable;
            table = mainTable;
        }
        String customQuery = StringUtils.EMPTY;
        String ccpWhereCond = getCCPWhereConditionQuery("E", "CCP");
        selectClause += "I.\"YEAR\" as YEARS , ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == 4) {
            selectClause += "I.QUARTER as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == 12) {
            selectClause += "I.\"MONTH\" as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.\"MONTH\"";
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
                whereClause += " WHERE B.PRICE_GROUP_TYPE in (" + CommonUtils.CollectionToString(projSelDTO.getDiscountNameList(), true) + ")";
            }
        } else {
            if (projSelDTO.getDiscountNoList() != null && !projSelDTO.getDiscountNoList().isEmpty()) {
                if (!projSelDTO.isIsTotal()) {
                    discountTypeColumnName = " J.RS_NAME AS DISCOUNTS";
                    groupBy += ", " + " J.RS_NAME";
                }
                whereClause += " WHERE B.RS_MODEL_SID in (" + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + ")";
            }
        }

        selectClause += discountTypeColumnName + ", ";

        String futureQuery = selectClause + "sum(A.PROJECTION_SALES) as PROJECTION_SALES, Sum(A.PROJECTION_RPU) AS RPU from \n";

        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
            futureQuery += "#TEMP_CCP T \n"
                    + "                   JOIN   " + table + " A ON A.PROJECTION_DETAILS_SID = T.PROJECTION_DETAILS_SID\n"
                    + "                   JOIN    " + masterTable + " B ON A.PROJECTION_DETAILS_SID=B.PROJECTION_DETAILS_SID AND A.RS_MODEL_SID = B.RS_MODEL_SID\n "
                    +"                    AND A.USER_ID = B.USER_ID\n"
                    + "                   AND A.SESSION_ID = B.SESSION_ID "
                    +"                    JOIN   @PERIOD I ON I.PERIOD_SID = A.PERIOD_SID\n"
                    + "                   JOIN   @CCP CCP ON T.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID ";
        } else {
            futureQuery += table+" A\n"
                    + "                      JOIN   " + masterTable + " B ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID\n"
                    + "                                                      AND A.RS_MODEL_SID = B.RS_MODEL_SID\n"
                    + "                      JOIN   PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID\n"
                    + "                                                  AND PD.PROJECTION_MASTER_SID = "+ projSelDTO.getProjectionId() +"\n"
                    + "                      JOIN   @PERIOD I ON I.PERIOD_SID = A.PERIOD_SID ";
        }
        if (!projSelDTO.isIsTotal()) {
            futureQuery += " JOIN RS_MODEL J ON J.RS_MODEL_SID = B.RS_MODEL_SID ";
        }
        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
            futureQuery += getUserSessionQueryCondition(Integer.valueOf(projSelDTO.getUserId()), Integer.valueOf(projSelDTO.getSessionId()), "A");
        }

//        futureQuery += " WHERE ";

//        if (!projSelDTO.getCurrentOrPrior().equalsIgnoreCase(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
//            futureQuery += getUserSessionQueryCondition(Integer.valueOf(projSelDTO.getUserId()), Integer.valueOf(projSelDTO.getSessionId()), "B").replaceFirst("and", StringUtils.EMPTY);
//            futureQuery += whereClause.isEmpty() ? StringUtils.EMPTY : "AND ";
//        }
        futureQuery += whereClause + " GROUP BY " + groupBy;
        customQuery = futureQuery;
        return customQuery;

    }

    public String getProjectionResultsPPAQuery(PVSelectionDTO projSelDTO) {
        String viewtable = CommonLogic.getViewTableName(projSelDTO.getHierarchyIndicator());
        String mainMasterTable = "  NM_PPA_PROJECTION_MASTER ";
        String tempMasterTable = " ST_NM_PPA_PROJECTION_MASTER ";
        String mainTable = " NM_PPA_PROJECTION ";
        String tempTable = " ST_NM_PPA_PROJECTION ";
        String masterTable = StringUtils.EMPTY;
        String table = StringUtils.EMPTY;
        if (!projSelDTO.isIsPrior()) {
            masterTable = tempMasterTable;
            table = tempTable;
        } else {
            masterTable = mainMasterTable;
            table = mainTable;
        }
        String selectClause = " select ";
        String whereClause = StringUtils.EMPTY;
        String groupBy = ",H.LEVEL_NAME , I.\"YEAR\"";
        String customQuery = StringUtils.EMPTY;
        String ccpWhereCond = getCCPWhereConditionQuery("E", "CCP");
        String parentNode = projSelDTO.getParentNode();
        whereClause = " and H.PARENT_NODE = '" + parentNode + "'";
        whereClause += " and H.RELATIONSHIP_LEVEL_VALUES = '" + projSelDTO.getLevelValue() + "'";
        groupBy = ", H.RELATIONSHIP_LEVEL_VALUES " + groupBy;
        selectClause += "I.\"YEAR\" as YEARS , ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == 4) {
            selectClause += "I.QUARTER as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0'  as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == 12) {
            selectClause += "I.\"MONTH\" as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.\"MONTH\"";
        }
        selectClause += "'PPA Discount' as DISCOUNTS,";
        // To filter the data according to selected period
        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        // To handle the scenario where any discount is not selected in program selection lookup
        String customSql = masterTable + " B,"
                + " PROJECTION_DETAILS E , PROJECTION_MASTER F, "
                + viewtable
                + " G, RELATIONSHIP_LEVEL_DEFINITION H,"
                + " \"PERIOD\" I, "
                + " @CCP CCP "
                + "where A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID "
                + "and B.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID ";
        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
            customSql += getUserSessionQueryCondition(Integer.valueOf(projSelDTO.getUserId()), Integer.valueOf(projSelDTO.getSessionId()), "B")
                    + getUserSessionQueryCondition(Integer.valueOf(projSelDTO.getUserId()), Integer.valueOf(projSelDTO.getSessionId()), "A");
        }
        customSql += "and E.PROJECTION_MASTER_SID = F.PROJECTION_MASTER_SID "
                + "and F.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId()
                + " and G.PROJECTION_MASTER_SID = F.PROJECTION_MASTER_SID "
                + "and G.RELATIONSHIP_LEVEL_SID = H.RELATIONSHIP_LEVEL_SID "
                + ccpWhereCond
                + "and A.PERIOD_SID = I.PERIOD_SID "
                + periodFilter
                + " and H.LEVEL_NO = " + projSelDTO.getLevelNo()
                + whereClause
                + " group by H.LEVEL_NO " + groupBy;

        String futureQuery = selectClause + " 0 as ACTUAL_SALES, sum(A.PROJECTION_SALES) as PROJECTION_SALES from " + table + " A," + customSql;
        customQuery = futureQuery;
        return customQuery;
    }

    public List<String> getCommonSelectWhereOrderGroupByClause(String table1, String table2, int frequencyDivision, String where) {
        List<String> list = new ArrayList<String>();
        String orderBy = " YEARS, PERIODS";
        String groupBy = " " + table1 + ".YEARS";
        String selectClause = "Isnull(" + table1 + ".YEARS, " + table2 + ".YEARS) as YEARS, ";
        String finalWhereCond = " " + where + " " + table1 + ".YEARS=" + table2 + ".YEARS";
        groupBy += ", " + table1 + ".PERIODS";
        selectClause += "Isnull(" + table1 + ".PERIODS, " + table2 + ".PERIODS) as PERIODS,";
        finalWhereCond += " and " + table1 + ".PERIODS=" + table2 + ".PERIODS";
        list.add(selectClause);
        list.add(finalWhereCond);
        list.add(orderBy);
        list.add(groupBy);
        return list;
    }

    public String getPVMainDiscountPivotQuery(PVSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(false);
        String selectClauseDol = " select C.YEARS as YEARS, C.PERIODS AS PERIODS,C.DISCOUNTS AS DISCOUNTS,";
        String selectClausePer = StringUtils.EMPTY;
        String customQuery = StringUtils.EMPTY;
//        String orderBy = " DISCOUNTS, YEARS,PERIODS ";
        String orderBy = "  YEARS,PERIODS ";
        selectClauseDol += "IsNull(C.PROJECTION_SALES, 0) AS C_DOL_VAL ";
        selectClauseDol += ",0  AS C_DOL_VAR ";
        selectClauseDol += ",0  AS C_DOL_PER ";

        selectClauseDol += ",IsNull(C.PROJECTION_RATE, 0) C_RATE_VAL";
        selectClauseDol += ",0  AS C_RATE_VAR ";
        selectClauseDol += ",0  AS C_RATE_PER ";

        selectClauseDol += ",IsNull(C.RPU, 0) C_RPU_VAL";
        selectClauseDol += ",0  AS C_RPU_VAR ";
        selectClauseDol += ",0  AS C_RPU_PER ";

//        selectClauseDol += ",IsNull(C.RETURNS_PROJECTED_AMOUNT, 0) AS C_DOL_RET_VAL ";
//        selectClauseDol += ",0  AS  C_DOL_RET_VAR ";
//        selectClauseDol += ",0  AS  C_DOL_RET_PER ";
//
//        selectClauseDol += ",IsNull(C.RETURNS_PROJECTED_RATE, 0) C_RATE_RET_VAL";
//        selectClauseDol += ",0  AS C_RATE_RET_VAR ";
//        selectClauseDol += ",0  AS C_RATE_RET_PER ";
//
//        selectClauseDol += ",IsNull(C.RETURNS_RPU, 0) C_RPU_RET_VAL";
//        selectClauseDol += ",0  AS C_RPU_RET_VAR ";
//        selectClauseDol += ",0  AS C_RPU_RET_PER ";
//        selectClauseDol += ",IsNull(C.PPA_DISCOUNT_AMOUNT, 0) AS C_DOL_PPA_VAL ";
//        selectClauseDol += ",0  AS  C_DOL_PPA_VAR ";
//        selectClauseDol += ",0  AS  C_DOL_PPA_PER ";
//
//        selectClauseDol += ",IsNull(C.PPA_DISCOUNT_PERCENT, 0) C_RATE_PPA_VAL";
//        selectClauseDol += ",0  AS C_RATE_PPA_VAR ";
//        selectClauseDol += ",0  AS C_RATE_PPA_PER ";
//
//        selectClauseDol += ",IsNull(C.PPA_DISCOUNT_RPU, 0) C_RPU_PPA_VAL";
//        selectClauseDol += ",0  AS C_RPU_PPA_VAR ";
//        selectClauseDol += ",0  AS C_RPU_PPA_PER ";
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            selectClauseDol += ", IsNull(P" + i + ".PROJECTION_SALES,0) AS P" + i + "_DOL_VALUE ";
            selectClauseDol += ", (IsNull(C.PROJECTION_SALES, 0)- IsNull(P" + i + ".PROJECTION_SALES, 0)) AS P" + i + "_DOL_VAR ";
            selectClauseDol += ", CASE WHEN P" + i + ".PROJECTION_SALES = 0 THEN 0\n"
                    + " ELSE (IsNull(C.PROJECTION_SALES, 0) - IsNull(P" + i + ".PROJECTION_SALES, 0) / P" + i + ".PROJECTION_SALES) \n"
                    + " END  AS P" + i + "_DOL_PER ";

            selectClauseDol += ", IsNull(P" + i + ".PROJECTION_RATE,0) AS P" + i + "_RATE_VAL ";
            selectClauseDol += ", (IsNull(C.PROJECTION_RATE, 0)- IsNull(P" + i + ".PROJECTION_RATE, 0)) AS P" + i + "_RATE_VAR ";
            selectClauseDol += ", CASE WHEN P" + i + ".PROJECTION_RATE = 0 THEN 0\n"
                    + " ELSE (IsNull(C.PROJECTION_RATE, 0) - IsNull(P" + i + ".PROJECTION_RATE, 0) / P" + i + ".PROJECTION_RATE) \n"
                    + " END  AS     P" + i + "_RATE_PER ";

            selectClauseDol += ", IsNull(P" + i + ".RPU,0) AS P" + i + "_RPU_VAL ";
            selectClauseDol += ", (IsNull(C.RPU, 0)- IsNull(P" + i + ".RPU, 0)) AS P" + i + "_RPU_VAR ";
            selectClauseDol += ", CASE WHEN P" + i + ".RPU = 0 THEN 0\n"
                    + " ELSE (IsNull(C.RPU, 0) - IsNull(P" + i + ".RPU, 0) / P" + i + ".RPU) \n"
                    + " END  AS     P" + i + "_RPU_PER ";

//            selectClauseDol += ", IsNull(P" + i + ".RETURNS_PROJECTED_AMOUNT,0) AS P" + i + "_RET_DOL_VALUE ";
//            selectClauseDol += ", (IsNull(C.RETURNS_PROJECTED_AMOUNT, 0)- IsNull(P" + i + ".RETURNS_PROJECTED_AMOUNT, 0)) AS P" + i + "_RET_DOL_VAR ";
//            selectClauseDol += ", CASE WHEN P" + i + ".RETURNS_PROJECTED_AMOUNT = 0 THEN 0\n"
//                    + " ELSE (IsNull(C.RETURNS_PROJECTED_AMOUNT, 0) - IsNull(P" + i + ".RETURNS_PROJECTED_AMOUNT, 0) / P" + i + ".RETURNS_PROJECTED_AMOUNT) \n"
//                    + " END  AS P" + i + "_RET_DOL_PER ";
//
//            selectClauseDol += ", IsNull(P" + i + ".RETURNS_PROJECTED_RATE,0) AS P" + i + "_RET_RATE_VAL ";
//            selectClauseDol += ", (IsNull(C.RETURNS_PROJECTED_RATE, 0)- IsNull(P" + i + ".RETURNS_PROJECTED_RATE, 0)) AS P" + i + "_RET_RATE_VAR ";
//            selectClauseDol += ", CASE WHEN P" + i + ".RETURNS_PROJECTED_RATE = 0 THEN 0\n"
//                    + " ELSE (IsNull(C.RETURNS_PROJECTED_RATE, 0) - IsNull(P" + i + ".RETURNS_PROJECTED_RATE, 0) / P" + i + ".RETURNS_PROJECTED_RATE) \n"
//                    + " END  AS     P" + i + "_RET_RATE_PER ";
//
//            selectClauseDol += ", IsNull(P" + i + ".RETURNS_RPU,0) AS P" + i + "_RET_RPU_VAL ";
//            selectClauseDol += ", (IsNull(C.RETURNS_RPU, 0)- IsNull(P" + i + ".RETURNS_RPU, 0)) AS P" + i + "_RET_RPU_VAR ";
//            selectClauseDol += ", CASE WHEN P" + i + ".RETURNS_RPU = 0 THEN 0\n"
//                    + " ELSE (IsNull(C.RETURNS_RPU, 0) - IsNull(P" + i + ".RETURNS_RPU, 0) / P" + i + ".RPU) \n"
//                    + " END  AS     P" + i + "_RET_RPU_PER ";
//            selectClauseDol += ", IsNull(P" + i + ".PPA_DISCOUNT_AMOUNT,0) AS P" + i + "_PPA_DOL_VALUE ";
//            selectClauseDol += ", (IsNull(C.PPA_DISCOUNT_AMOUNT, 0)- IsNull(P" + i + ".PPA_DISCOUNT_AMOUNT, 0)) AS P" + i + "_PPA_DOL_VAR ";
//            selectClauseDol += ", CASE WHEN P" + i + ".PPA_DISCOUNT_AMOUNT = 0 THEN 0\n"
//                    + " ELSE (IsNull(C.PPA_DISCOUNT_AMOUNT, 0) - IsNull(P" + i + ".PPA_DISCOUNT_AMOUNT, 0) / P" + i + ".PPA_DISCOUNT_AMOUNT) \n"
//                    + " END  AS P" + i + "_PPA_DOL_PER ";
//
//            selectClauseDol += ", IsNull(P" + i + ".PPA_DISCOUNT_PERCENT,0) AS P" + i + "_PPA_RATE_VAL ";
//            selectClauseDol += ", (IsNull(C.PPA_DISCOUNT_PERCENT, 0)- IsNull(P" + i + ".PPA_DISCOUNT_PERCENT, 0)) AS P" + i + "_PPA_RATE_VAR ";
//            selectClauseDol += ", CASE WHEN P" + i + ".PPA_DISCOUNT_PERCENT = 0 THEN 0\n"
//                    + " ELSE (IsNull(C.PPA_DISCOUNT_PERCENT, 0) - IsNull(P" + i + ".PPA_DISCOUNT_PERCENT, 0) / P" + i + ".PPA_DISCOUNT_PERCENT) \n"
//                    + " END  AS     P" + i + "_PPA_RATE_PER ";
//
//            selectClauseDol += ", IsNull(P" + i + ".PPA_DISCOUNT_RPU,0) AS P" + i + "_PPA_RPU_VAL ";
//            selectClauseDol += ", (IsNull(C.PPA_DISCOUNT_RPU, 0)- IsNull(P" + i + ".PPA_DISCOUNT_RPU, 0)) AS P" + i + "_PPA_RPU_VAR ";
//            selectClauseDol += ", CASE WHEN P" + i + ".PPA_DISCOUNT_RPU = 0 THEN 0\n"
//                    + " ELSE (IsNull(C.PPA_DISCOUNT_RPU, 0) - IsNull(P" + i + ".PPA_DISCOUNT_RPU, 0) / P" + i + ".RPU) \n"
//                    + " END  AS     P" + i + "_PPA_RPU_PER ";
        }
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        projSelDTO.setIsPrior(false);
        customQuery += selectClauseDol + " from  \n(" + getProjectionResultsDiscountsPerQuery(projSelDTO) + ") C\n ";
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
        projSelDTO.setIsPrior(true);
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            projSelDTO.setProjectionId(projSelDTO.getProjIdList().get(i));
            customQuery += " LEFT JOIN (\n" + getProjectionResultsDiscountsPerQuery(projSelDTO) + "\n) " + Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY + i + " \n on C.DISCOUNTS=P" + i + ".DISCOUNTS \n "
                    + " AND C.YEARS=P" + i + ".YEARS \n "
                    + " and C.PERIODS=P" + i + ".PERIODS  ";
        }
        customQuery += " order by " + orderBy;
        projSelDTO.setIsPrior(false);
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        return customQuery;
    }

    public String getProjectionResultsDiscountsPerQuery(PVSelectionDTO projSelDTO) {
        String selectClause = " select ";
        String ppaSelectClause = selectClause;
        String customQuery = StringUtils.EMPTY;
        List<String> list = getCommonSelectWhereOrderGroupByClause("TODIS", "SALEPPA", projSelDTO.getFrequencyDivision(), "on");
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        list = getCommonSelectWhereOrderGroupByClause("PPA", "SALEPPA", projSelDTO.getFrequencyDivision(), "on");
        selectClause += "TODIS.DISCOUNTS, ";
        selectClause += "TODIS.PROJECTION_SALES AS PROJECTION_SALES, ";
        selectClause += " PROJECTION_RATE=Isnull(TODIS.PROJECTION_SALES / NULLIF(SALEPPA.SALES_PROJECTION_SALES, 0), 0)*100,";
        selectClause += " CASE WHEN SALEPPA.PROJECTION_UNITS = 0 THEN 0 ELSE (((Isnull(TODIS.PROJECTION_SALES, 0)) /SALEPPA.PROJECTION_UNITS) ) END AS RPU";
//        selectClause += ", ISNULL(SALEPPA.RETURNS_PROJECTED,0) AS RETURNS_PROJECTED_AMOUNT,";
//        selectClause += " ISNULL(SALEPPA.PROJECTED_RATE/NULLIF(SALEPPA.SALES_PROJECTION_SALES, 0), 0) * 100 AS RETURNS_PROJECTED_RATE, ";
//        selectClause += " (ISNULL(SALEPPA.RETURNS_PROJECTED / NULLIF(SALEPPA.PROJECTION_UNITS, 0), 0)) AS RETURNS_RPU ";
        ppaSelectClause += list.get(0) + " PPA.PPA_DISCOUNT\n, (ISNULL(PPA.SALES_PPA,0)) AS PPA_DISCOUNT_AMOUNT\n"
                + "              ,Isnull(PPA.SALES_PPA / NULLIF(ppa.ppa_proj_sales, 0), 0) * 100 AS PPA_DISCOUNT_PERCENT\n"
                + "              , (Isnull(PPA.SALES_PPA / NULLIF(ppa.ppa_proj_units, 0), 0)) AS PPA_DISCOUNT_RPU";
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, StringUtils.EMPTY);
        String salesPpaQuery = getSalesQuery(getProjectionResultsSalesQuery(projSelDTO));
        String ppaQuery = getPPAQuery(getPPAProjectionResultsQuery(projSelDTO, Boolean.TRUE), Boolean.TRUE);
        String ppaGroupBy = " ON SALEPPA.YEARS = PPA.YEARS AND SALEPPA.PERIODS = PPA.PERIODS";
        customQuery = selectClause + " from (" + totalDiscountQuery + ") TODIS INNER JOIN (" + salesPpaQuery + ") SALEPPA " + finalWhereCond + " UNION ALL " + ppaSelectClause + " FROM ( \n" + ppaQuery + ")PPA INNER JOIN (" + salesPpaQuery + ") SALEPPA" + ppaGroupBy;
        return customQuery;
    }

    public String getProjectionResultsPPAPerQuery(PVSelectionDTO projSelDTO) {
        String selectClause = " select ";
        String customQuery = StringUtils.EMPTY;
        selectClause += "SALEPPA.YEARS ,SALEPPA.PERIODS, ";
        selectClause += "'PPA Discount' as DISCOUNTS,";
        selectClause += " ACTUAL_RATE=Isnull(SALEPPA.ACTUAL_SALES / NULLIF(SALEPPA.SALES_ACTUAL_SALES, 0), 0) * 100,"
                + " PROJECTION_RATE=Isnull(SALEPPA.PROJECTION_SALES / NULLIF(SALEPPA.SALES_PROJECTION_SALES, 0), 0) * 100 ";
        String ppaQuery = getProjectionResultsSalesQuery(projSelDTO);
        customQuery = selectClause + " from (" + ppaQuery + ") SALEPPA";
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
        if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            String userGroup = projSelDTO.getGroupFilter();
            ccpQuery += " " + CommonLogic.getGroupFilterQuery(userGroup, Integer.valueOf(projSelDTO.getUserId()), Integer.valueOf(projSelDTO.getSessionId()), false);
        }
        ccpQuery += " ) CCPMAP,\n"
                + " (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD1 \n"
                + " JOIN " + viewtable + " PCH \n"
                + " ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID \n"
                + " AND PCH.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD1.HIERARCHY_NO like '" + projSelDTO.getHierarchyNo() + "%' ) HLD \n"
                + " WHERE CCPMAP.HIERARCHY_NO like HLD.HIERARCHY_NO+'%' ) LCCP \n"
                + " WHERE LCCP.HIERARCHY_NO in \n"
                + " (SELECT RLD2.HIERARCHY_NO \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD2 \n"
                + " JOIN " + viewtable + " PCH2 \n"
                + " ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID \n"
                + " AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD2.LEVEL_NO=" + projSelDTO.getTreeLevelNo() + ") \n";

        return ccpQuery;
    }

    public String getCustomCCPQuery(PVSelectionDTO projSelDTO) {
        String customerHierarchyNo = projSelDTO.getCustomerHierarchyNo() + Constant.PERCENT;
        String productHierarchyNo = projSelDTO.getProductHierarchyNo() + Constant.PERCENT;
        String customerLevelNo = Constant.PERCENT;
        String productLevelNo = Constant.PERCENT;

        if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {

            customerLevelNo = StringUtils.EMPTY + projSelDTO.getTreeLevelNo();
        } else if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {

            productLevelNo = StringUtils.EMPTY + projSelDTO.getTreeLevelNo();
        }
        String ccpQuery = " SELECT distinct HLD" + projSelDTO.getHierarchyIndicator() + ".RELATIONSHIP_LEVEL_SID, CCPMAP" + projSelDTO.getHierarchyIndicator() + ".CCP_DETAILS_SID, HLD" + projSelDTO.getHierarchyIndicator() + ".HIERARCHY_NO FROM \n"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n";

        if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            String userGroup = projSelDTO.getGroupFilter();
            ccpQuery += " " + CommonLogic.getGroupFilterQuery(userGroup, Integer.valueOf(projSelDTO.getUserId()), Integer.valueOf(projSelDTO.getSessionId()), false);
        }

        ccpQuery += " ) CCPMAPC \n"
                + " JOIN"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n";

        if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            String userGroup = projSelDTO.getGroupFilter();
            ccpQuery += " " + CommonLogic.getGroupFilterQuery(userGroup, Integer.valueOf(projSelDTO.getUserId()), Integer.valueOf(projSelDTO.getSessionId()), false);
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
        String tableQuery = "DECLARE @CCP TABLE\n"
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
     * Comparison Look up search logic
     *
     * @param comparisonLookupDTO
     * @return String for Query
     */
    public String getComparisonSearchCount(final ComparisonLookupDTO comparisonLookupDTO, Set<Filter> filter, String screenName, Map<String, Object> parameters) {
        String QUOTES = "'";
        String ASTERIK = "*";
        String PERCENT = Constant.PERCENT;
        String marketTypeVal = StringUtils.EMPTY;
        String brandVal = StringUtils.EMPTY;
        String projNameVal = StringUtils.EMPTY;
        String contHldrVal = StringUtils.EMPTY;
        String ndcNoVal = StringUtils.EMPTY;
        String ndcNameVal = StringUtils.EMPTY;
        String descVal = StringUtils.EMPTY;
        String contractVal = StringUtils.EMPTY;
        boolean isProjectionStatus = false;
        try {
            StringBuilder customSql = new StringBuilder(StringUtils.EMPTY);
            if (comparisonLookupDTO.getWorkflowStatus().equals(Constant.SAVED)) {
                isProjectionStatus = true;
            }
            if (isProjectionStatus) {
                customSql = new StringBuilder(CustomSQLUtil.get("getProjectionListsCount"));
            } else {
                customSql = new StringBuilder(CustomSQLUtil.get("getWorkFlowListsCount"));
            }

            if (comparisonLookupDTO.getMarketType() == null || comparisonLookupDTO.getMarketType().equals(StringUtils.EMPTY)) {
                marketTypeVal = "'%'";
            } else {
                marketTypeVal = comparisonLookupDTO.getMarketType().replace(ASTERIK, PERCENT);
                marketTypeVal = QUOTES + marketTypeVal + QUOTES;
            }
            customSql.append("( HT.list_name = 'CONTRACT_TYPE' AND HT.DESCRIPTION LIKE ").append(marketTypeVal).append(")");
            if (comparisonLookupDTO.getBrand() == null || comparisonLookupDTO.getBrand().equals(StringUtils.EMPTY)) {
                brandVal = "'%'";
            } else {
                brandVal = comparisonLookupDTO.getBrand().replace(ASTERIK, PERCENT);
                brandVal = QUOTES + brandVal + QUOTES;
            }
            customSql.append("  AND (BM.BRAND_NAME LIKE ").append(brandVal).append(" or BM.BRAND_NAME is null)");
            if (comparisonLookupDTO.getProjectionName() == null || comparisonLookupDTO.getProjectionName().equals(StringUtils.EMPTY)) {
                projNameVal = "'%'";
            } else {
                projNameVal = comparisonLookupDTO.getProjectionName().replace(ASTERIK, PERCENT);
                projNameVal = QUOTES + projNameVal + QUOTES;
            }
            customSql.append("AND (PM.PROJECTION_NAME LIKE ").append(projNameVal).append(" or PM.PROJECTION_NAME is null)");
            if (comparisonLookupDTO.getCustomer() == null || comparisonLookupDTO.getCustomer().equals(StringUtils.EMPTY)) {
                contHldrVal = "'%'";
            } else {
                contHldrVal = comparisonLookupDTO.getCustomer().replace(ASTERIK, PERCENT);
                contHldrVal = QUOTES + contHldrVal + QUOTES;
            }
            customSql.append("AND (C.CONTRACT_NO LIKE ").append(contHldrVal).append(" or C.CONTRACT_NO is null)");
            if (comparisonLookupDTO.getNdcName() == null || comparisonLookupDTO.getNdcName().equals(StringUtils.EMPTY)) {
                ndcNameVal = "'%'";
            } else {
                ndcNameVal = comparisonLookupDTO.getNdcName().replace(ASTERIK, PERCENT);
                ndcNameVal = QUOTES + ndcNameVal + QUOTES;
            }
            customSql.append("AND (IM.ITEM_NAME LIKE ").append(ndcNameVal).append(" or IM.ITEM_NAME is null)");
            if (comparisonLookupDTO.getNdcNo() == null || comparisonLookupDTO.getNdcNo().equals(StringUtils.EMPTY)) {
                ndcNoVal = "'%'";
            } else {
                ndcNoVal = comparisonLookupDTO.getNdcNo().replace(ASTERIK, PERCENT);
                ndcNoVal = QUOTES + ndcNoVal + QUOTES;
            }
            customSql.append("AND (IM.ITEM_NO LIKE ").append(ndcNoVal).append("or IM.ITEM_NO is null)");
            if (comparisonLookupDTO.getContract() == null || comparisonLookupDTO.getContract().equals(StringUtils.EMPTY)) {
                contractVal = "'%'";
            } else {
                contractVal = comparisonLookupDTO.getContract().replace(ASTERIK, PERCENT);
                contractVal = QUOTES + contractVal + QUOTES;
            }
            customSql.append("AND (CM.COMPANY_NO LIKE ").append(contractVal).append("or CM.COMPANY_NO is null)");
            if (comparisonLookupDTO.getProjectionDescription() == null || comparisonLookupDTO.getProjectionDescription().equals(StringUtils.EMPTY)) {
                descVal = "'%'";
            } else {
                descVal = comparisonLookupDTO.getProjectionDescription().replace(ASTERIK, PERCENT);
                descVal = QUOTES + descVal + QUOTES;
            }
            customSql.append("AND (PM.PROJECTION_DESCRIPTION LIKE ").append(descVal).append("or PM.PROJECTION_DESCRIPTION is null)");
            if (isProjectionStatus) {
                customSql.append("and pm.is_approved not in ('Y','C','A','R')");
            } else {
                customSql.append("AND HT1.list_name = 'WorkFlowStatus' and ht1.description = ").append(QUOTES).append(comparisonLookupDTO.getWorkflowStatus()).append(QUOTES);
            }
            if ((comparisonLookupDTO.getCreatedDateFrom() != null && !StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())) && !Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())))
                    && (comparisonLookupDTO.getCreatedDateTo() == null || StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())) || Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())))) {
                SimpleDateFormat format2 = new SimpleDateFormat(DATE_FORMAT.getConstant());
                customSql.append(" AND PM.CREATED_DATE >= '");
                customSql.append(format2.format(comparisonLookupDTO.getCreatedDateFrom()));
                customSql.append("' ");
            } else if ((comparisonLookupDTO.getCreatedDateTo() != null && !StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())) && !Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())))
                    && (comparisonLookupDTO.getCreatedDateFrom() == null || StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())) || Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())))) {
                SimpleDateFormat format2 = new SimpleDateFormat(DATE_FORMAT.getConstant());
                customSql.append(" AND PM.CREATED_DATE <= '");
                customSql.append(format2.format(format2.parse(comparisonLookupDTO.getCreatedDateTo())));
                customSql.append("' ");
            } else if (comparisonLookupDTO.getCreatedDateFrom() != null && !Constant.NULL.equals(comparisonLookupDTO.getCreatedDateFrom()) && comparisonLookupDTO.getCreatedDateFrom() != null
                    && comparisonLookupDTO.getCreatedDateTo() != null && !Constant.NULL.equals(comparisonLookupDTO.getCreatedDateTo()) && !StringUtils.isEmpty(comparisonLookupDTO.getCreatedDateTo())) {
                SimpleDateFormat format2 = new SimpleDateFormat(DATE_FORMAT.getConstant());
                customSql.append(" AND PM.CREATED_DATE BETWEEN '");
                customSql.append(format2.format(comparisonLookupDTO.getCreatedDateFrom()));
                customSql.append("' AND '");
                customSql.append(format2.format(format2.parse(comparisonLookupDTO.getCreatedDateTo())));
                customSql.append("' ");
            }
            //Filter
            if (parameters.get("filter~projectionName") != null) {
                customSql.append("AND (PM.PROJECTION_NAME LIKE '").append(parameters.get("filter~projectionName")).append("') ");
            }
            if (parameters.get("filter~projectionDescription") != null) {
                customSql.append("AND (PM.PROJECTION_DESCRIPTION LIKE '").append(parameters.get("filter~projectionDescription")).append("') ");
            }
            if (parameters.get("filter~marketType") != null) {
                customSql.append("AND (HT.DESCRIPTION LIKE '").append(parameters.get("filter~marketType")).append("') ");
            }
            if (parameters.get("filter~contract") != null) {
                customSql.append("AND (C.CONTRACT_NO LIKE '").append(parameters.get("filter~contract")).append("')");
            }
            if (parameters.get("filter~customer") != null) {
                customSql.append("AND (CM.COMPANY_NO LIKE '").append(parameters.get("filter~customer")).append("') ");
            }
            if (parameters.get("filter~brand") != null) {
                String value = String.valueOf(parameters.get("filter~brand"));
                value = value.replace(Constant.PERCENT, StringUtils.EMPTY);
                if ("multiple".contains(value.toLowerCase())) {
                    customSql.append("AND BM.BRAND_NAME IS NOT NULL ");
                } else {
                    customSql.append("AND (BM.BRAND_NAME LIKE '").append(parameters.get("filter~brand")).append("') ");
                }
            }
            if (parameters.get("filter~Between~createdDateFrom~from") != null && parameters.get("filter~Between~createdDateFrom~to") != null) {
                customSql.append("AND PM.CREATED_DATE BETWEEN '").append(parameters.get("filter~Between~createdDateFrom~from")).append("' AND '").append(parameters.get("filter~Between~createdDateFrom~to")).append("' ");
            }
            if (parameters.get("filter~GOE~createdDateFrom~from") != null) {
                customSql.append("AND PM.CREATED_DATE >= '").append(parameters.get("filter~GOE~createdDateFrom~from")).append("' ");
            }
            if (parameters.get("filter~GOE~createdDateFrom~to") != null) {
                customSql.append("AND PM.CREATED_DATE <= '").append(parameters.get("filter~GOE~createdDateFrom~to")).append("' ");
            }
            if (parameters.get("filter~createdBy") != null) {
                if (parameters.get("filter~createdBy") != null) {
                    List<String> strList = new ArrayList<String>();
                    final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
                    Criterion criterion = RestrictionsFactoryUtil.ilike(Constant.FIRSTNAME, Constant.PERCENT + parameters.get("filter~createdBy") + Constant.PERCENT);
                    Criterion criterion1 = RestrictionsFactoryUtil.ilike(Constant.LASTNAME, Constant.PERCENT + parameters.get("filter~createdBy") + Constant.PERCENT);
                    dynamicQuery.add(RestrictionsFactoryUtil.or(criterion, criterion1));
                    final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
                    productProjectionList.add(ProjectionFactoryUtil.property(Constant.USER_ID));
                    dynamicQuery.setProjection(productProjectionList);
                    strList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
                    String userID = CommonUtils.CollectionToString(strList, false);
                    customSql.append("AND (PM.CREATED_BY IN (").append(userID).append(") ) ");
                }
            }
            customSql.append("AND PM.PROJECTION_MASTER_SID NOT IN (").append(comparisonLookupDTO.getCurrentProjId()).append(")");
            customSql.append("AND CUR_PD.PROJECTION_MASTER_SID IN (").append(comparisonLookupDTO.getCurrentProjId()).append(") ");
            customSql.append("AND PM.FORECASTING_TYPE='").append(screenName).append("' ");
            customSql.append(" group by  pm.projection_name,pm.projection_description,pm.projection_master_sid,PM.created_date,PM.created_by ) RSLT ");

            return customSql.toString();
        } catch (Exception e) {
            LOGGER.error(e + " at PvQueryUtils -> getComparisonSearch");
            return null;
        }
    }

    /**
     * Comparison Look up search logic
     *
     * @param comparisonLookupDTO
     * @return String for Query
     */
    public String getComparisonSearch(final ComparisonLookupDTO comparisonLookupDTO, int start, int offset, Set<Filter> filter, List<SortByColumn> sortColumns, String screenName, Map<String, Object> parameters) {
        String QUOTES = "'";
        String ASTERIK = "*";
        String PERCENT = Constant.PERCENT;
        String marketTypeVal = StringUtils.EMPTY;
        String brandVal = StringUtils.EMPTY;
        String projNameVal = StringUtils.EMPTY;
        String contHldrVal = StringUtils.EMPTY;
        String ndcNoVal = StringUtils.EMPTY;
        String ndcNameVal = StringUtils.EMPTY;
        String descVal = StringUtils.EMPTY;
        String contractVal = StringUtils.EMPTY;
        boolean isProjectionStatus = false;
        Map<Object, String> columns = new HashMap<Object, String>();
        columns.put(Constant.PROJECTION_NAME, "pm.projection_name");
        columns.put(Constant.PROJECTIONDESCRIPTION, "pm.projection_description");
        columns.put("marketType", "market_type");
        columns.put("customer", Constant.CUSTOMER_SMALL);
        columns.put(Constant.CONTRACT, Constant.CONTRACT_SMALL);
        columns.put(Constant.BRAND, Constant.BRAND_CAPS);
        columns.put("createdDateFrom", "PM.created_date");
        columns.put("createdBy", "PM.created_by");
        try {
            StringBuilder customSql = new StringBuilder(StringUtils.EMPTY);
            if (comparisonLookupDTO.getWorkflowStatus().equals(Constant.SAVED)) {
                isProjectionStatus = true;
            }

            if (isProjectionStatus) {
                customSql = new StringBuilder(CustomSQLUtil.get("getProjectionLists"));
            } else {
                customSql = new StringBuilder(CustomSQLUtil.get("getWorkFlowLists"));
            }

            if (comparisonLookupDTO.getMarketType() == null || comparisonLookupDTO.getMarketType().equals(StringUtils.EMPTY)) {
                marketTypeVal = "'%'";
            } else {
                marketTypeVal = comparisonLookupDTO.getMarketType().replace(ASTERIK, PERCENT);
                marketTypeVal = QUOTES + marketTypeVal + QUOTES;
            }
            customSql.append("( HT.list_name = 'CONTRACT_TYPE' AND HT.DESCRIPTION LIKE ").append(marketTypeVal).append(")");
            if (comparisonLookupDTO.getBrand() == null || comparisonLookupDTO.getBrand().equals(StringUtils.EMPTY)) {
                brandVal = "'%'";
            } else {
                brandVal = comparisonLookupDTO.getBrand().replace(ASTERIK, PERCENT);
                brandVal = QUOTES + brandVal + QUOTES;
            }
            customSql.append("  AND (BM.BRAND_NAME LIKE ").append(brandVal).append(" or BM.BRAND_NAME is null)");
            if (comparisonLookupDTO.getProjectionName() == null || comparisonLookupDTO.getProjectionName().equals(StringUtils.EMPTY)) {
                projNameVal = "'%'";
            } else {
                projNameVal = comparisonLookupDTO.getProjectionName().replace(ASTERIK, PERCENT);
                projNameVal = QUOTES + projNameVal + QUOTES;
            }
            customSql.append("AND (PM.PROJECTION_NAME LIKE ").append(projNameVal).append(" or PM.PROJECTION_NAME is null)");
            if (comparisonLookupDTO.getCustomer() == null || comparisonLookupDTO.getCustomer().equals(StringUtils.EMPTY)) {
                contHldrVal = "'%'";
            } else {
                contHldrVal = comparisonLookupDTO.getCustomer().replace(ASTERIK, PERCENT);
                contHldrVal = QUOTES + contHldrVal + QUOTES;
            }
            customSql.append("AND (C.CONTRACT_NO LIKE ").append(contHldrVal).append(" or C.CONTRACT_NO is null)");
            if (comparisonLookupDTO.getNdcName() == null || comparisonLookupDTO.getNdcName().equals(StringUtils.EMPTY)) {
                ndcNameVal = "'%'";
            } else {
                ndcNameVal = comparisonLookupDTO.getNdcName().replace(ASTERIK, PERCENT);
                ndcNameVal = QUOTES + ndcNameVal + QUOTES;
            }
            customSql.append("AND (IM.ITEM_NAME LIKE ").append(ndcNameVal).append(" or IM.ITEM_NAME is null)");
            if (comparisonLookupDTO.getNdcNo() == null || comparisonLookupDTO.getNdcNo().equals(StringUtils.EMPTY)) {
                ndcNoVal = "'%'";
            } else {
                ndcNoVal = comparisonLookupDTO.getNdcNo().replace(ASTERIK, PERCENT);
                ndcNoVal = QUOTES + ndcNoVal + QUOTES;
            }
            customSql.append("AND (IM.ITEM_NO LIKE ").append(ndcNoVal).append("or IM.ITEM_NO is null)");
            if (comparisonLookupDTO.getContract() == null || comparisonLookupDTO.getContract().equals(StringUtils.EMPTY)) {
                contractVal = "'%'";
            } else {
                contractVal = comparisonLookupDTO.getContract().replace(ASTERIK, PERCENT);
                contractVal = QUOTES + contractVal + QUOTES;
            }
            customSql.append("AND (CM.COMPANY_NO LIKE ").append(contractVal).append("or CM.COMPANY_NO is null)");
            if (comparisonLookupDTO.getProjectionDescription() == null || comparisonLookupDTO.getProjectionDescription().equals(StringUtils.EMPTY)) {
                descVal = "'%'";
            } else {
                descVal = comparisonLookupDTO.getProjectionDescription().replace(ASTERIK, PERCENT);
                descVal = QUOTES + descVal + QUOTES;
            }
            customSql.append("AND (PM.PROJECTION_DESCRIPTION LIKE ").append(descVal).append("or PM.PROJECTION_DESCRIPTION is null)");
            if (isProjectionStatus) {
                customSql.append("and pm.is_approved not in ('Y','C','A','R')");
            } else {
                customSql.append("AND HT1.list_name = 'WorkFlowStatus' and ht1.description = ").append(QUOTES).append(comparisonLookupDTO.getWorkflowStatus()).append(QUOTES);
            }
            if ((comparisonLookupDTO.getCreatedDateFrom() != null && !StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())) && !Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())))
                    && (comparisonLookupDTO.getCreatedDateTo() == null || StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())) || Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())))) {
                SimpleDateFormat format2 = new SimpleDateFormat(DATE_FORMAT.getConstant());
                customSql.append(" AND PM.CREATED_DATE >= '");
                customSql.append(format2.format(comparisonLookupDTO.getCreatedDateFrom()));
                customSql.append("' ");
            } else if ((comparisonLookupDTO.getCreatedDateTo() != null && !StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())) && !Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())))
                    && (comparisonLookupDTO.getCreatedDateFrom() == null || StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())) || Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())))) {
                SimpleDateFormat format2 = new SimpleDateFormat(DATE_FORMAT.getConstant());
                customSql.append(" AND PM.CREATED_DATE <= '");
                customSql.append(format2.format(format2.parse(comparisonLookupDTO.getCreatedDateTo())));
                customSql.append("' ");
            } else if (comparisonLookupDTO.getCreatedDateFrom() != null && !Constant.NULL.equals(comparisonLookupDTO.getCreatedDateFrom()) && comparisonLookupDTO.getCreatedDateFrom() != null
                    && comparisonLookupDTO.getCreatedDateTo() != null && !Constant.NULL.equals(comparisonLookupDTO.getCreatedDateTo()) && !StringUtils.isEmpty(comparisonLookupDTO.getCreatedDateTo())) {
                SimpleDateFormat format2 = new SimpleDateFormat(DATE_FORMAT.getConstant());
                customSql.append(" AND PM.CREATED_DATE BETWEEN '");
                customSql.append(format2.format(comparisonLookupDTO.getCreatedDateFrom()));
                customSql.append("' AND '");
                customSql.append(format2.format(format2.parse(comparisonLookupDTO.getCreatedDateTo())));
                customSql.append("' ");
            }
            //Filter
            if (parameters.get("filter~projectionName") != null) {
                customSql.append("AND (PM.PROJECTION_NAME LIKE '").append(parameters.get("filter~projectionName")).append("') ");
            }
            if (parameters.get("filter~projectionDescription") != null) {
                customSql.append("AND (PM.PROJECTION_DESCRIPTION LIKE '").append(parameters.get("filter~projectionDescription")).append("') ");
            }
            if (parameters.get("filter~marketType") != null) {
                customSql.append("AND (HT.DESCRIPTION LIKE '").append(parameters.get("filter~marketType")).append("') ");
            }
            if (parameters.get("filter~contract") != null) {
                customSql.append("AND (C.CONTRACT_NO LIKE '").append(parameters.get("filter~contract")).append("')");
            }
            if (parameters.get("filter~customer") != null) {
                customSql.append("AND (CM.COMPANY_NO LIKE '").append(parameters.get("filter~customer")).append("') ");
            }
            if (parameters.get("filter~brand") != null) {
                String value = String.valueOf(parameters.get("filter~brand"));
                value = value.replace(Constant.PERCENT, StringUtils.EMPTY);
                if ("multiple".contains(value.toLowerCase())) {
                    customSql.append("AND BM.BRAND_NAME IS NOT NULL ");
                } else {
                    customSql.append("AND (BM.BRAND_NAME LIKE '").append(parameters.get("filter~brand")).append("') ");
                }
            }
            if (parameters.get("filter~Between~createdDateFrom~from") != null && parameters.get("filter~Between~createdDateFrom~to") != null) {
                customSql.append("AND PM.CREATED_DATE BETWEEN '").append(parameters.get("filter~Between~createdDateFrom~from")).append("' AND '").append(parameters.get("filter~Between~createdDateFrom~to")).append("' ");
            }
            if (parameters.get("filter~GOE~createdDateFrom~from") != null) {
                customSql.append("AND PM.CREATED_DATE > = '").append(parameters.get("filter~GOE~createdDateFrom~from")).append("' ");
            }
            if (parameters.get("filter~GOE~createdDateFrom~to") != null) {
                customSql.append("AND PM.CREATED_DATE < = '").append(parameters.get("filter~GOE~createdDateFrom~to")).append("' ");
            }
            if (parameters.get("filter~createdBy") != null) {
                List<String> strList = new ArrayList<String>();
                final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
                Criterion criterion = RestrictionsFactoryUtil.ilike(Constant.FIRSTNAME, Constant.PERCENT + parameters.get("filter~createdBy") + Constant.PERCENT);
                Criterion criterion1 = RestrictionsFactoryUtil.ilike(Constant.LASTNAME, Constant.PERCENT + parameters.get("filter~createdBy") + Constant.PERCENT);
                dynamicQuery.add(RestrictionsFactoryUtil.or(criterion, criterion1));
                final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
                productProjectionList.add(ProjectionFactoryUtil.property(Constant.USER_ID));
                dynamicQuery.setProjection(productProjectionList);
                strList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
                String userID = CommonUtils.CollectionToString(strList, false);
                customSql.append("AND (PM.CREATED_BY IN (").append(userID).append(") ) ");
            }
            customSql.append("AND PM.PROJECTION_MASTER_SID NOT IN (").append(comparisonLookupDTO.getCurrentProjId()).append(")");
            customSql.append("AND CUR_PD.PROJECTION_MASTER_SID IN (").append(comparisonLookupDTO.getCurrentProjId()).append(") ");
            customSql.append("AND PM.FORECASTING_TYPE='").append(screenName).append("' ");
            customSql.append(" group by  pm.projection_name,pm.projection_description,pm.projection_master_sid,PM.created_date,PM.created_by ");
            customSql.append(" ORDER BY ");

            if (sortColumns == null || sortColumns.size() == 0) {
                customSql.append(" pm.projection_name, pm.projection_description, PM.created_date, PM.created_by,  pm.projection_master_sid ");
            } else {
                Boolean temp = Boolean.FALSE;
                for (Iterator<SortByColumn> it = sortColumns.iterator(); it.hasNext();) {
                    SortByColumn sortByColumn = it.next();
                    if (temp) {
                        customSql.append(", ");
                        temp = Boolean.TRUE;
                    }
                    customSql.append(columns.get(sortByColumn.getName())).append(" ").append(sortByColumn.getType());
                }
            }

            customSql.append(" OFFSET ");
            customSql.append(start);
            customSql.append(" ROWS FETCH NEXT ");
            customSql.append(offset);
            customSql.append(" ROWS ONLY ;");

            return customSql.toString();
        } catch (Exception e) {
            LOGGER.error(e + " at PvQueryUtils -> getComparisonSearch");
            return null;
        }
    }

    /**
     * Main Whole Query
     *
     * @param projSelDTO
     * @return String
     */
    public String getProjVarianceMainQuery(PVSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(true);
        String selectClause = " select ";
        String customQuery = StringUtils.EMPTY;
        String orderBy = " YEARS,PERIODS ";
        selectClause += "C.YEARS as YEARS, C.PERIODS AS PERIODS,";

        customQuery = selectClause + getMainSelectClause(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            customQuery += ", " + getMainSelectClause(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY + i);
        }
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        projSelDTO.setIsPrior(false);
        customQuery += " from  \n(" + getProjVarianceQuery(projSelDTO) + ") C\n ";
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
        projSelDTO.setIsPrior(true);
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            projSelDTO.setProjectionId(projSelDTO.getProjIdList().get(i));
            customQuery += " LEFT JOIN (\n" + getProjVarianceQuery(projSelDTO) + "\n) " + Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY + i + " \n" + getProjVarMainWhereCond(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY + i);
        }
        customQuery += " order by " + orderBy;
        projSelDTO.setIsPrior(false);
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        return customQuery;
    }

    /**
     * Main Select clause
     *
     * @param projName
     * @return String
     */
    public String getMainSelectClause(String projName) {
        String selectClause = " " + projName + ".CONTRACT_PROJECTION_SALES as " + projName + "CONTRACT_PROJECTION_SALES,"
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
        String selectClause = " select ";
        selectClause += "TODIS.YEARS as YEARS, TODIS.PERIODS AS PERIODS,";
        selectClause += getProjVarianceSelectClause();
        String totalDiscountQuery = getProjVarianceDiscountQuery(projSelDTO);
        String salesQuery = getProjVariancesSalesQuery(projSelDTO);
        String customQuery = selectClause + " from " + getProjVarSelectQuery(totalDiscountQuery, salesQuery);
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
        String selectClause = " (\n" + query1 + "\n) TODIS FULL OUTER JOIN (\n" + query2 + "\n) SALEPPA \n" + finalWhereCond;
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
        String masterTable = StringUtils.EMPTY;
        String table = StringUtils.EMPTY;
        if (!projSelDTO.isIsPrior()) {
            masterTable = tempMasterTable;
            table = tempTable;
        } else {
            masterTable = mainMasterTable;
            table = mainTable;
        }
        String selectClause = " select ";
        String whereClause = StringUtils.EMPTY;
        String groupBy = " I.\"YEAR\"";

        String customQuery = StringUtils.EMPTY;
        String ccpWhereCond = getCCPWhereConditionQuery("E", "CCP");

        selectClause += "I.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == 4) {
            selectClause += "I.QUARTER as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == 12) {
            selectClause += "I.\"MONTH\" as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.\"MONTH\"";
        }

        // To filter the data according to selected period
        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        // To handle the scenario where any discount is not selected in program selection lookup
        String discountTypeColumnName = "'Total discounts' as DISCOUNTS";
        if (projSelDTO.getDiscountNoList() != null && !projSelDTO.getDiscountNoList().isEmpty()) {
            if (!projSelDTO.isIsTotal()) {
                discountTypeColumnName = " J.RS_NAME as DISCOUNTS";
                groupBy += ", " + " J.RS_NAME";
            }
            whereClause += " and B.RS_MODEL_SID in (" + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + ")";
        }
        selectClause += discountTypeColumnName + ", ";

        String customSql = masterTable
                + " B, PROJECTION_DETAILS E ,"
                + " \"PERIOD\" I, "
                + " @CCP CCP ";
        if (!projSelDTO.isIsTotal()) {
            customSql += ", RS_MODEL J ";
        }
        customSql += "where A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID and B.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID ";
        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
            customSql += getUserSessionQueryCondition(Integer.valueOf(projSelDTO.getUserId()), Integer.valueOf(projSelDTO.getSessionId()), "B")
                    + getUserSessionQueryCondition(Integer.valueOf(projSelDTO.getUserId()), Integer.valueOf(projSelDTO.getSessionId()), "A");
        }
        if (!projSelDTO.isIsTotal()) {
            customSql += "and B.RS_MODEL_SID= J.RS_MODEL_SID ";
        }
        customSql += "and A.RS_MODEL_SID = B.RS_MODEL_SID "
                + " and E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId()
                + ccpWhereCond + "and A.PERIOD_SID = I.PERIOD_SID " + periodFilter
                + whereClause + " group by " + groupBy;

        String futureQuery = selectClause + " 0 as ACTUAL_SALES, sum(A.PROJECTION_SALES) as PROJECTION_SALES from "
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

        String masterSalesTable = StringUtils.EMPTY;
        String salesTable = StringUtils.EMPTY;

        if (!projSelDTO.isIsPrior()) {
            masterSalesTable = tempMasterSalesTable;
            salesTable = tempSalesTable;
        } else {
            masterSalesTable = mainMasterSalesTable;
            salesTable = mainSalesTable;
        }
        String selectClause = " select ";
        String whereClause = StringUtils.EMPTY;
        String groupBy = " I.\"YEAR\"";
        String ccpWhereCond = getCCPWhereConditionQuery("E", "CCP");
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == 4) {
            selectClause += "I.QUARTER as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == 12) {
            selectClause += "I.\"MONTH\" as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.\"MONTH\"";
        }
        // To filter the data according to selected period
        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        // To handle the scenario where any discount is not selected in program selection lookup
        String customSql
                = masterSalesTable + "  B1,"
                + " PROJECTION_DETAILS E, "
                + " \"PERIOD\" I, "
                + " @CCP CCP "
                + "where A1.PROJECTION_DETAILS_SID = B1.PROJECTION_DETAILS_SID "
                + "and B1.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID ";
        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
            customSql += getUserSessionQueryCondition(Integer.valueOf(projSelDTO.getUserId()), Integer.valueOf(projSelDTO.getSessionId()), "B1")
                    + getUserSessionQueryCondition(Integer.valueOf(projSelDTO.getUserId()), Integer.valueOf(projSelDTO.getSessionId()), "A1");
        }
        customSql += " and E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId()
                + ccpWhereCond
                + "and A1.PERIOD_SID = I.PERIOD_SID "
                + periodFilter
                + whereClause
                + " group by " + groupBy;
        String futureQuery = selectClause
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
        return query;
    }
}
