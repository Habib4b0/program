/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.util;

import com.stpl.app.cff.dto.PVSelectionDTO;
import com.stpl.app.cff.logic.CommonLogic;
import com.stpl.app.cff.ui.fileSelection.Util.ConstantsUtils;
import com.stpl.app.cff.ui.projectionVariance.dto.ComparisonLookupDTO;
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
import java.util.Arrays;
import java.util.Collection;
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

    public String getUserSessionQueryCondition(int userId, int sessionId, String table) {
        String user = " and " + table + ".USER_ID=" + userId + " and " + table + ".SESSION_ID=" + sessionId + " ";
        return user;
    }

    public String getPVMainDiscountPerQuery(PVSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(false);
        String selectClause = " select C.YEARS as YEARS, C.PERIODS AS PERIODS,C.DISCOUNTS AS DISCOUNTS,";
        String customQuery = "";
        String orderBy = " DISCOUNTS, YEARS,PERIODS ";
        String projectedSales = StringUtils.EMPTY;
        if (projSelDTO.isRPU()) {
            projectedSales = "RPU ";
        } else {
            projectedSales = "PROJECTION_RATE ";
        }
        if (projSelDTO.getGroup().contains("Value")) {
            selectClause += "C." + projectedSales + " AS C_PROJECTION_RATE";
        } else if (projSelDTO.getGroup().contains("Variance")) {
            selectClause += "0  AS PROJECTION_RATE";
        } else {
            selectClause += "0  AS PROJECTION_RATE";
        }
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            if (projSelDTO.getGroup().contains("Value")) {
                selectClause += ", P" + i + "." + projectedSales + " AS P" + i + "_" + projectedSales + " ";
            } else if (projSelDTO.getGroup().contains("Variance")) {
                selectClause += ", (IsNull(C." + projectedSales + ", 0)- IsNull(P" + i + "." + projectedSales + ", 0)) AS P" + i + "_" + projectedSales + " ";
            } else {
                selectClause += ", CASE WHEN P" + i + "." + projectedSales + " = 0 THEN 0\n"
                        + " ELSE (IsNull(C." + projectedSales + ", 0) - IsNull(P" + i + "." + projectedSales + ", 0) / P" + i + "." + projectedSales + ") \n"
                        + " END  AS P" + i + "_" + projectedSales + " ";
            }
        }
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        projSelDTO.setCurrentOrPrior("C");
        projSelDTO.setIsPrior(false);
        customQuery += selectClause + " from  \n(" + getProjectionResultsDiscountsPerQuery(projSelDTO) + ") C\n ";
        projSelDTO.setCurrentOrPrior("P");
        projSelDTO.setIsPrior(true);
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            projSelDTO.setProjectionId(projSelDTO.getProjIdList().get(i));
            customQuery += " LEFT JOIN (\n" + getProjectionResultsDiscountsPerQuery(projSelDTO) + "\n) " + "P" + i + " \n on C.DISCOUNTS=P" + i + ".DISCOUNTS \n "
                    + " AND C.YEARS=P" + i + ".YEARS \n "
                    + " and C.PERIODS=P" + i + ".PERIODS  ";
        }
        customQuery += " order by " + orderBy;
        projSelDTO.setIsPrior(false);
        projSelDTO.setCurrentOrPrior("C");
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        return customQuery;
    }

    public String getPeriodDiscountExpand(PVSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(false);
        String selectClause = " select C.YEARS as YEARS, C.PERIODS AS PERIODS,C.DISCOUNTS AS DISCOUNTS,";
        String customQuery = "";
        String orderBy = " DISCOUNTS, YEARS,PERIODS ";
        String projectedSales = StringUtils.EMPTY;
        String projectedReturns = StringUtils.EMPTY;
        projSelDTO.setSales("SALES");
        if (projSelDTO.getDiscountGroupName().contains("Discount $")) {
            projectedSales = "PROJECTION_SALES";
            projectedReturns = "RETURNS_PROJECTED_AMOUNT";
            if (projSelDTO.getDiscountGroupName().contains("Value")) {
                selectClause += "C." + projectedSales + " AS C_PROJECTION_AMNT,";
                selectClause += "C." + projectedReturns + " AS C_RETURNS_AMNT";
            } else if (projSelDTO.getDiscountGroupName().contains("Variance")) {
                selectClause += "0  AS PROJECTION_AMNT,";
                selectClause += "0  AS RETURNS_AMN";
            } else {
                selectClause += "0  AS PROJECTION_AMNT,";
                selectClause += "0  AS RETURNS_AMN";
            }
        } else if (projSelDTO.getDiscountGroupName().contains("Discount %")) {
            projSelDTO.setSales("RATE");
            projectedSales = "PROJECTION_RATE";
            projectedReturns = "RETURNS_PROJECTED_RATE";
            if (projSelDTO.getDiscountGroupName().contains("Value")) {
                selectClause += "C." + projectedSales + " AS C_PROJECTION_RATE,";
                selectClause += "C." + projectedReturns + " AS C_RETURNS_RATE";
            } else if (projSelDTO.getDiscountGroupName().contains("Variance")) {
                selectClause += "0  AS PROJECTION_RATE,";
                selectClause += "0  AS RETURNS_RATE";
            } else {
                selectClause += "0  AS PROJECTION_RATE,";
                selectClause += "0  AS RETURNS_RATE";
            }
        } else {
            projectedSales = "RPU";
            projectedReturns = "RETURNS_RPU";
            if (projSelDTO.getDiscountGroupName().contains("Value")) {
                selectClause += "C." + projectedSales + " AS C_RPU,";
                selectClause += "C." + projectedReturns + " AS C_RPU";
            } else if (projSelDTO.getDiscountGroupName().contains("Variance")) {
                selectClause += "0  AS PROJECTION_RATE";
                selectClause += "0  AS PROJECTION_RPU";
            } else {
                selectClause += "0  AS PROJECTION_RATE";
                selectClause += "0  AS PROJECTION_RPU";
            }
        }
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            if (projSelDTO.getGroup().contains("Value")) {
                selectClause += ", P" + i + "." + projectedSales + " AS P" + i + "_" + projectedSales + " ";
                selectClause += ", P" + i + "." + projectedReturns + " AS P" + i + "_" + projectedReturns + " ";
            } else if (projSelDTO.getGroup().contains("Variance")) {
                selectClause += ", (IsNull(C." + projectedSales + ", 0)- IsNull(P" + i + "." + projectedSales + ", 0)) AS P" + i + "_" + projectedSales + " ";
                selectClause += ", (IsNull(C." + projectedReturns + ", 0)- IsNull(P" + i + "." + projectedReturns + ", 0)) AS P" + i + "_" + projectedReturns + " ";
            } else {
                selectClause += ", CASE WHEN P" + i + "." + projectedSales + " = 0 THEN 0\n"
                        + " ELSE (IsNull(C." + projectedSales + ", 0) - IsNull(P" + i + "." + projectedSales + ", 0) / P" + i + "." + projectedSales + ") \n"
                        + " END  AS P" + i + "_" + projectedSales + " ";
                selectClause += ", CASE WHEN P" + i + "." + projectedReturns + " = 0 THEN 0\n"
                        + " ELSE (IsNull(C." + projectedReturns + ", 0) - IsNull(P" + i + "." + projectedReturns + ", 0) / P" + i + "." + projectedReturns + ") \n"
                        + " END  AS P" + i + "_" + projectedReturns + " ";
            }
        }
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        projSelDTO.setCurrentOrPrior("C");
        projSelDTO.setIsPrior(false);
        customQuery += selectClause + " from  \n(" + getProjectionResultsDiscountsPerQuery(projSelDTO) + ") C\n ";
        projSelDTO.setCurrentOrPrior("P");
        projSelDTO.setIsPrior(true);
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            projSelDTO.setProjectionId(projSelDTO.getProjIdList().get(i));
            customQuery += " LEFT JOIN (\n" + getProjectionResultsDiscountsPerQuery(projSelDTO) + "\n) " + "P" + i + " \n on C.DISCOUNTS=P" + i + ".DISCOUNTS \n "
                    + " AND C.YEARS=P" + i + ".YEARS \n "
                    + " and C.PERIODS=P" + i + ".PERIODS  ";
        }
        customQuery += " order by " + orderBy;
        projSelDTO.setIsPrior(false);
        projSelDTO.setCurrentOrPrior("C");
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        return customQuery;
    }

    public String getPivotSelectQuery(String query1, String query2) {
        String finalWhereCond = " on TODIS.YEARS=SALEPPA.YEARS AND TODIS.PERIODS=SALEPPA.PERIODS";
        String selectClause = " (\n" + query1 + "\n) TODIS FULL JOIN (\n" + query2 + "\n) SALEPPA \n" + finalWhereCond;
        return selectClause;
    }

    public String getPivotMainWhereCond(String table) {
        return " on C.YEARS=" + table + ".YEARS AND C.PERIODS=" + table + ".PERIODS \n";
    }

    public String getPivotSelectClause() {
        String selectClause = " SALEPPA.SALES_PROJECTION_SALES as CONTRACT_PROJECTION_SALES,"
                + "SALEPPA.PROJECTION_UNITS as CONTRACT_PROJECTION_UNITS,"                
                + " CASE WHEN SALEPPA.sales_projection_sales = 0 THEN 0 ELSE ((((Isnull(TODIS.projection_sales, 0)) + ISNULL(SALEPPA.PROJECTED_RATE, 0)) / NULLIF(SALEPPA.sales_projection_sales,0)) * 100) END AS TOTAL_PROJECTION_RATE, "                
                + " (Isnull(TODIS.projection_sales, 0) + ISNULL(SALEPPA.RETURNS_PROJECTED, 0)) AS TOTAL_PROJECTION_DOLAR,"
                + "(Isnull(SALEPPA.SALES_PROJECTION_SALES, 0)-Isnull(TODIS.PROJECTION_SALES, 0)) AS NET_PROJECTION_SALES, "
                + " CASE WHEN SALEPPA.projection_units = 0 THEN 0 ELSE(IsNull((IsNull(TODIS.projection_sales, 0)+IsNull(SALEPPA.RETURNS_PROJECTED, 0)) / NULLIF(SALEPPA.PROJECTION_UNITS, 0), 0)) END AS RPU,\n"
                + " COGS_PROJECTED = ISNULL(SALEPPA.COGS_PROJECTED, 0),\n"
                + " NET_PROFIT_PROJECTED = ((ISNULL(SALEPPA.SALES_PROJECTION_SALES, 0) - ISNULL(SALEPPA.COGS_PROJECTED, 0)))";
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

    public String getSalesQuery(final String salesInnerJoin) {
        String query = "SELECT YEARS\n"
                + " , PERIODS\n"
                + " , Sum(projection_sales) AS SALES_PROJECTION_SALES\n"
                + "  , Sum(projection_units) AS PROJECTION_UNITS\n"
                + " , SUM(COGS_PROJECTED) AS COGS_PROJECTED\n"
                + " , PROJECTED_RATE = Avg(PROJECTED_RATE)\n"
                + " , RETURNS_PROJECTED = Sum(PROJECTION_SALES) * Avg(PROJECTED_RATE)\n"
                + " FROM ( " + salesInnerJoin;
        return query;
    }

    public String getCCPWhereConditionQuery(String projectionDetails, String CCP) {
        String ccpWhereCond = " and " + CCP + ".CCP_DETAILS_SID=" + projectionDetails + ".CCP_DETAILS_SID ";
        return ccpWhereCond;
    }

    public String getComparisonSearch(String workflowStatus, String marketType, String brand,
            String projName, String contHldr, String ndcNo, String ndcName, String desc, String contract,
            String from, String to, String notSearchProjId) {
        String QUOTES = "'";
        String ASTERIK = "*";
        String PERCENT = "%";
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

            if (workflowStatus.equals(Constants.SAVED)) {
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
            if (from != null && !"null".equals(from) && !StringUtils.isEmpty(from)
                    && to != null && !"null".equals(to) && !StringUtils.isEmpty(to)) {
                SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
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

    public String getProjectionResultsDiscountsQuery(PVSelectionDTO projSelDTO, String orderBy) {
        String mainMasterTable = "  NM_DISCOUNT_PROJ_MASTER ";
        String tempMasterTable = " ST_NM_DISCOUNT_PROJ_MASTER ";
        String mainTable = " NM_DISCOUNT_PROJECTION ";
        String tempTable = " ST_NM_DISCOUNT_PROJECTION ";
        String masterTable = StringUtils.EMPTY;
        String table = StringUtils.EMPTY;
        String selectClause = " select ";
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
        if (projSelDTO.getFrequencyDivision() == 4) {
            selectClause += "I.QUARTER as PERIODS, ";
            whereClause += "";
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, ";
            whereClause += "";
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS, ";
            whereClause += "";
            groupBy += "";

        } else if (projSelDTO.getFrequencyDivision() == 12) {
            selectClause += "I.\"MONTH\" as PERIODS, ";
            whereClause += "";
            groupBy += ", I.\"MONTH\"";
        }

        // To filter the data according to selected period
        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        // To handle the scenario where any discount is not selected in program selection lookup
        String discountTypeColumnName = "'Total discounts' as DISCOUNTS";
        LOGGER.info("Discount Level " + projSelDTO.getDiscountLevel());
        if (projSelDTO.getDiscountLevel().equals("Program Category")) {
            LOGGER.error("projSelDTO.getDiscountNameList() " + projSelDTO.getDiscountNameList().size() + " projSelDTO.isIsTotal() " + projSelDTO.isIsTotal());
            if (projSelDTO.getDiscountNameList() != null && !projSelDTO.getDiscountNameList().isEmpty()) {
                if (!projSelDTO.isIsTotal()) {
                    discountTypeColumnName = " B.PRICE_GROUP_TYPE AS DISCOUNTS";
                    groupBy += ", " + " B.PRICE_GROUP_TYPE";
                }
                whereClause += " and B.PRICE_GROUP_TYPE in (" + CollectionToString(projSelDTO.getDiscountNameList(), true) + ")";
            }
        } else {
            if (projSelDTO.getDiscountNoList() != null && !projSelDTO.getDiscountNoList().isEmpty()) {
                if (!projSelDTO.isIsTotal()) {
                    discountTypeColumnName = " J.RS_NAME AS DISCOUNTS";
                    groupBy += ", " + " J.RS_NAME";
                }
                whereClause += " and B.RS_MODEL_SID in (" + CollectionToString(projSelDTO.getDiscountNoList(), false) + ")";
            }
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
        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase("C")) {
            customSql += getUserSessionQueryCondition(Integer.valueOf(projSelDTO.getUserId()), Integer.valueOf(projSelDTO.getSessionId()), "B")
                    + getUserSessionQueryCondition(Integer.valueOf(projSelDTO.getUserId()), Integer.valueOf(projSelDTO.getSessionId()), "A");
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

    public String getProjectionResultsDiscountsPerQuery(PVSelectionDTO projSelDTO) {
        String selectClause = " select ";
        String customQuery = "";
        List<String> list = getCommonSelectWhereOrderGroupByClause("TODIS", "SALEPPA", projSelDTO.getFrequencyDivision(), "on");
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "TODIS.DISCOUNTS, ";
        selectClause += "TODIS.PROJECTION_SALES AS PROJECTION_SALES, ";
        selectClause += " PROJECTION_RATE=Isnull(TODIS.PROJECTION_SALES / NULLIF(SALEPPA.SALES_PROJECTION_SALES, 0), 0),";
        selectClause += " CASE WHEN SALEPPA.PROJECTION_UNITS = 0 THEN 0 ELSE (((Isnull(TODIS.PROJECTION_SALES, 0)) /SALEPPA.PROJECTION_UNITS) * 100 ) END AS RPU,";
        selectClause += " ISNULL(SALEPPA.RETURNS_PROJECTED,0) AS RETURNS_PROJECTED_AMOUNT,";
        selectClause += " ISNULL(SALEPPA.PROJECTED_RATE,0) AS RETURNS_PROJECTED_RATE, ";
        selectClause += " (ISNULL(SALEPPA.RETURNS_PROJECTED / NULLIF(SALEPPA.SALES_PROJECTION_SALES, 0), 0)) AS RETURNS_RPU ";
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, "");
        return customQuery;
    }

    public String getCustomCCPQuery(PVSelectionDTO projSelDTO) {
        String customerHierarchyNo = projSelDTO.getCustomerHierarchyNo() + "%";
        String productHierarchyNo = projSelDTO.getProductHierarchyNo() + "%";
        String customerLevelNo = "%";
        String productLevelNo = "%";

        if (projSelDTO.getHierarchyIndicator().equals("C")) {

            customerLevelNo = "" + projSelDTO.getTreeLevelNo();
        } else if (projSelDTO.getHierarchyIndicator().equals("P")) {

            productLevelNo = "" + projSelDTO.getTreeLevelNo();
        }
        String ccpQuery = " SELECT distinct HLD" + projSelDTO.getHierarchyIndicator() + ".RELATIONSHIP_LEVEL_SID, CCPMAP" + projSelDTO.getHierarchyIndicator() + ".CCP_DETAILS_SID, HLD" + projSelDTO.getHierarchyIndicator() + ".HIERARCHY_NO FROM \n"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n";

        if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals("P")) {
            String userGroup = projSelDTO.getGroupFilter();
            ccpQuery += " " + getGroupFilterQuery(userGroup, Integer.valueOf(projSelDTO.getUserId()), Integer.valueOf(projSelDTO.getSessionId()), false);
        }

        ccpQuery += " ) CCPMAPC \n"
                + " JOIN"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n";

        if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals("P")) {
            String userGroup = projSelDTO.getGroupFilter();
            ccpQuery += " " + getGroupFilterQuery(userGroup, Integer.valueOf(projSelDTO.getUserId()), Integer.valueOf(projSelDTO.getSessionId()), false);
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

    /**
     * Comparison Look up search logic
     *
     * @param comparisonLookupDTO
     * @return String for Query
     */
    public String getComparisonSearchCount(final ComparisonLookupDTO comparisonLookupDTO, Set<Filter> filter, String screenName, Map<String, Object> parameters) {
        String QUOTES = "'";
        String ASTERIK = "*";
        String PERCENT = "%";
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
            if (comparisonLookupDTO.getWorkflowStatus().equals(Constants.SAVED)) {
                isProjectionStatus = true;
            }
            if (isProjectionStatus) {
                customSql = new StringBuilder(CustomSQLUtil.get("getProjectionListsCount"));
            } else {
                customSql = new StringBuilder(CustomSQLUtil.get("getWorkFlowListsCount"));
            }

            if (comparisonLookupDTO.getMarketType() == null || comparisonLookupDTO.getMarketType().equals(StringUtils.EMPTY)) {
                marketTypeVal = "%";
            } else {
                marketTypeVal = comparisonLookupDTO.getMarketType().replace(ASTERIK, PERCENT);
                marketTypeVal = marketTypeVal ;
            }
           
            if (comparisonLookupDTO.getBrand() == null || comparisonLookupDTO.getBrand().equals(StringUtils.EMPTY)) {
                brandVal = "%";
            } else {
                brandVal = comparisonLookupDTO.getBrand().replace(ASTERIK, PERCENT);
                brandVal =  brandVal ;
            }
           
            if (comparisonLookupDTO.getProjectionName() == null || comparisonLookupDTO.getProjectionName().equals(StringUtils.EMPTY)) {
                projNameVal = "'%'";
            } else {
                projNameVal = comparisonLookupDTO.getProjectionName().replace(ASTERIK, PERCENT);
                projNameVal = QUOTES + projNameVal + QUOTES;
            }
            customSql.append(" (PM.PROJECTION_NAME LIKE ").append(projNameVal).append(" or PM.PROJECTION_NAME is null)");
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
                contractVal = "%";
            } else {
                contractVal = comparisonLookupDTO.getContract().replace(ASTERIK, PERCENT);
                contractVal = contractVal ;
            }
           
            if (comparisonLookupDTO.getProjectionDescription() == null || comparisonLookupDTO.getProjectionDescription().equals(StringUtils.EMPTY)) {
                descVal = "'%'";
            } else {
                descVal = comparisonLookupDTO.getProjectionDescription().replace(ASTERIK, PERCENT);
                descVal = QUOTES + descVal + QUOTES;
            }
            customSql.append("AND (PM.PROJECTION_DESCRIPTION LIKE ").append(descVal).append("or PM.PROJECTION_DESCRIPTION is null)");
            if (isProjectionStatus) {
                customSql.append("and pm.is_approved not in ('Y','C','A','R')");
            }  else {
                customSql.append("AND HT1.list_name = 'WorkFlowStatus' and ht1.description = ").append(QUOTES).append(comparisonLookupDTO.getWorkflowStatus()).append(QUOTES);
            }
            if ((comparisonLookupDTO.getCreatedDateFrom() != null && !StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())) && !Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())))
                    && (comparisonLookupDTO.getCreatedDateTo() == null || StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())) || Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())))) {
                SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
                customSql.append(" AND PM.CREATED_DATE >= '");
                customSql.append(format2.format(comparisonLookupDTO.getCreatedDateFrom()));
                customSql.append("' ");
            } else if ((comparisonLookupDTO.getCreatedDateTo() != null && !StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())) && !Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())))
                    && (comparisonLookupDTO.getCreatedDateFrom() == null || StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())) || Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())))) {
                SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
                customSql.append(" AND PM.CREATED_DATE <= '");
                customSql.append(format2.format(format2.parse(comparisonLookupDTO.getCreatedDateTo())));
                customSql.append("' ");
            } else if (comparisonLookupDTO.getCreatedDateFrom() != null && !"null".equals(comparisonLookupDTO.getCreatedDateFrom()) && comparisonLookupDTO.getCreatedDateFrom() != null
                    && comparisonLookupDTO.getCreatedDateTo() != null && !"null".equals(comparisonLookupDTO.getCreatedDateTo()) && !StringUtils.isEmpty(comparisonLookupDTO.getCreatedDateTo())) {
                SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
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
                value = value.replace("%", "");
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
                    Criterion criterion = RestrictionsFactoryUtil.ilike("firstName", "%" + parameters.get("filter~createdBy") + "%");
                    Criterion criterion1 = RestrictionsFactoryUtil.ilike("lastName", "%" + parameters.get("filter~createdBy") + "%");
                    dynamicQuery.add(RestrictionsFactoryUtil.or(criterion, criterion1));
                    final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
                    productProjectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.USER_ID));
                    dynamicQuery.setProjection(productProjectionList);
                    strList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
                    String userID = CollectionToString(strList, false);
                    customSql.append("AND (PM.CREATED_BY IN (").append(userID).append(") ) ");
                }
            }
            customSql.append("AND PM.PROJECTION_MASTER_SID NOT IN (").append(comparisonLookupDTO.getCurrentProjId()).append(")");
            customSql.append("AND PM.FORECASTING_TYPE Like '").append("%").append("' ");
            customSql.append(" group by  pm.projection_name,pm.projection_description,pm.projection_master_sid,PM.created_date,PM.created_by ) RSLT ");
             String query=customSql.toString();
          
                query = query.replace("@CONTYPE", marketTypeVal);
                query = query.replace("@CM_NO", contractVal);
                query = query.replace("@BRAND", brandVal);
                query = query.replace("@WFSTATUS", comparisonLookupDTO.getWorkflowStatus());
              
                return query;
     
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
        String PERCENT = "%";
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
        columns.put("projectionName", "pm.projection_name");
        columns.put("projectionDescription", "pm.projection_description");
        columns.put("marketType", "market_type");
        columns.put("customer", "Customer");
        columns.put("contract", "Contract");
        columns.put("brand", "Brand");
        columns.put("createdDateFrom", "PM.created_date");
        columns.put("createdBy", "PM.created_by");
        try {
            StringBuilder customSql = new StringBuilder(StringUtils.EMPTY);
            if (comparisonLookupDTO.getWorkflowStatus().equals(Constants.SAVED)) {
                isProjectionStatus = true;
            }
            if (isProjectionStatus) {
                customSql = new StringBuilder(CustomSQLUtil.get("getProjectionLists"));
            } else {
                customSql = new StringBuilder(CustomSQLUtil.get("getWorkFlowLists"));
            }

            if (comparisonLookupDTO.getMarketType() == null || comparisonLookupDTO.getMarketType().equals(StringUtils.EMPTY)) {
                marketTypeVal = "%";
            } else {
                marketTypeVal = comparisonLookupDTO.getMarketType().replace(ASTERIK, PERCENT);
                marketTypeVal = marketTypeVal ;
            }
           
            if (comparisonLookupDTO.getBrand() == null || comparisonLookupDTO.getBrand().equals(StringUtils.EMPTY)) {
                brandVal = "%";
            } else {
                brandVal = comparisonLookupDTO.getBrand().replace(ASTERIK, PERCENT);
                brandVal =  brandVal ;
            }
            if (comparisonLookupDTO.getProjectionName() == null || comparisonLookupDTO.getProjectionName().equals(StringUtils.EMPTY)) {
                projNameVal = "'%'";
            } else {
                projNameVal = comparisonLookupDTO.getProjectionName().replace(ASTERIK, PERCENT);
                projNameVal = QUOTES + projNameVal + QUOTES;
            }
            customSql.append(" (PM.PROJECTION_NAME LIKE ").append(projNameVal).append(" or PM.PROJECTION_NAME is null)");
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
                contractVal = "%";
            } else {
                contractVal = comparisonLookupDTO.getContract().replace(ASTERIK, PERCENT);
                contractVal =  contractVal;
            }
         
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
                SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
                customSql.append(" AND PM.CREATED_DATE >= '");
                customSql.append(format2.format(comparisonLookupDTO.getCreatedDateFrom()));
                customSql.append("' ");
            } else if ((comparisonLookupDTO.getCreatedDateTo() != null && !StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())) && !Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())))
                    && (comparisonLookupDTO.getCreatedDateFrom() == null || StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())) || Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())))) {
                SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
                customSql.append(" AND PM.CREATED_DATE <= '");
                customSql.append(format2.format(format2.parse(comparisonLookupDTO.getCreatedDateTo())));
                customSql.append("' ");
            } else if (comparisonLookupDTO.getCreatedDateFrom() != null && !"null".equals(comparisonLookupDTO.getCreatedDateFrom()) && comparisonLookupDTO.getCreatedDateFrom() != null
                    && comparisonLookupDTO.getCreatedDateTo() != null && !"null".equals(comparisonLookupDTO.getCreatedDateTo()) && !StringUtils.isEmpty(comparisonLookupDTO.getCreatedDateTo())) {
                SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
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
                value = value.replace("%", "");
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
                Criterion criterion = RestrictionsFactoryUtil.ilike("firstName", "%" + parameters.get("filter~createdBy") + "%");
                Criterion criterion1 = RestrictionsFactoryUtil.ilike("lastName", "%" + parameters.get("filter~createdBy") + "%");
                dynamicQuery.add(RestrictionsFactoryUtil.or(criterion, criterion1));
                final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
                productProjectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.USER_ID));
                dynamicQuery.setProjection(productProjectionList);
                strList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
                String userID = CollectionToString(strList, false);
                customSql.append("AND (PM.CREATED_BY IN (").append(userID).append(") ) ");
            }
            customSql.append("AND PM.PROJECTION_MASTER_SID NOT IN (").append(comparisonLookupDTO.getCurrentProjId()).append(")");
            customSql.append("AND PM.FORECASTING_TYPE Like '").append("%").append("' ");
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
           String query=customSql.toString();
         
            query = query.replace("@CONTYPE", marketTypeVal);
            query = query.replace("@CM_NO", contractVal);
            query = query.replace("@BRAND", brandVal);
            query = query.replace("@WFSTATUS", comparisonLookupDTO.getWorkflowStatus());
            return query;


        
        } catch (Exception e) {
            LOGGER.error(e + " at PvQueryUtils -> getComparisonSearch");
            return null;
        }
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
        String whereClause = "";
        String groupBy = " I.\"YEAR\"";

        String customQuery = "";
        String ccpWhereCond = getCCPWhereConditionQuery("E", "CCP");

        selectClause += "I.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == 4) {
            selectClause += "I.QUARTER as PERIODS, ";
            whereClause += "";
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, ";
            whereClause += "";
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS, ";
            whereClause += "";
            groupBy += "";

        } else if (projSelDTO.getFrequencyDivision() == 12) {
            selectClause += "I.\"MONTH\" as PERIODS, ";
            whereClause += "";
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
            whereClause += " and B.RS_MODEL_SID in (" + CollectionToString(projSelDTO.getDiscountNoList(), false) + ")";
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
        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase("C")) {
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

    public static String CollectionToString(Collection<?> collectionOfString, boolean toAddQuote) {
        return CollectionToString(collectionOfString, toAddQuote, false);
    }

    /**
     * To convert List<String> into a comma separated String
     *
     * @param collectionOfString
     * @param toAddQuote
     * @param toRemoveSpace
     * @return
     */
    public static String CollectionToString(Collection<?> collectionOfString, boolean toAddQuote, boolean toRemoveSpace) {

        String framedString = "";
        if (collectionOfString != null && !collectionOfString.isEmpty()) {
            if (toAddQuote) {
                framedString += Arrays.toString(collectionOfString.toArray()).replace("[", "'").replace("]", "'").replace(", ", "','");
            } else {
                framedString += Arrays.toString(collectionOfString.toArray()).replace("[", "").replace("]", "");
            }

            if (toRemoveSpace) {
                framedString.replace(", ", "");
            }
        }
        return framedString;
    }

    public static String getGroupFilterQuery(String userGroup, int userId, int sessionId, boolean isPrior) {
        String query = "";
        return query;
    }

    public String getPVComparisonProjections(final List<Integer> projId) {
        try {
            String customSql = CustomSQLUtil.get("getProjectionLists");
            if (projId != null && !projId.isEmpty()) {
                customSql += (" PM.PROJECTION_MASTER_SID IN (" + CollectionToString(projId, false) + ")");
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
}
