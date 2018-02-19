/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.util;

import com.stpl.app.cff.dto.PVSelectionDTO;
import com.stpl.app.cff.logic.CommonLogic;
import com.stpl.app.cff.util.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Mohamed.hameed
 */
public class PVQueryUtils {

    /**
     * The Constant LOGGER.
     */
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(PVQueryUtils.class);
    public static final String VALUE_LABEL = "Value";
    
    public String getUserSessionQueryCondition(int userId, int sessionId, String table) {
        String user = StringConstantsUtil.SMALL_AND + table + ".USER_ID=" + userId + StringConstantsUtil.SMALL_AND + table + ".SESSION_ID=" + sessionId + " ";
        return user;
    }

    public String getPVMainDiscountPerQuery(PVSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(false);
        String selectClause = " select C.YEARS as YEARS, C.PERIODS AS PERIODS,C.DISCOUNTS AS DISCOUNTS,";
        String customQuery = "";
        String orderBy = " DISCOUNTS, YEARS,PERIODS ";
        String projectedSales;
        if (projSelDTO.isRPU()) {
            projectedSales = "RPU ";
        } else {
            projectedSales = "PROJECTION_RATE ";
        }
        if (projSelDTO.getGroup().contains(VALUE_LABEL)) {
            selectClause += "C." + projectedSales + " AS C_PROJECTION_RATE";
        } else if (projSelDTO.getGroup().contains(StringConstantsUtil.VARIANCE_LABEL)) {
            selectClause += "0  AS PROJECTION_RATE";
        } else {
            selectClause += "0  AS  PROJECTION_RATE";
        }
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            if (projSelDTO.getGroup().contains(VALUE_LABEL)) {
                selectClause += ", P" + i + "." + projectedSales + " AS P" + i + "_" + projectedSales + " ";
            } else if (projSelDTO.getGroup().contains(StringConstantsUtil.VARIANCE_LABEL)) {
                selectClause += ",  (IsNull(C." + projectedSales + ", 0) - IsNull(P" + i + "." + projectedSales + ", 0))  AS P" + i + "_" + projectedSales + " ";
            } else {
                selectClause += ", CASE WHEN P" + i + "." + projectedSales + " = 0 THEN 0\n"
                        + " ELSE  (IsNull(C." + projectedSales + ", 0) -  IsNull(P" + i + "." + projectedSales + ", 0) / P" + i + "." + projectedSales + ") \n"
                        + " END  AS P" + i + "_" + projectedSales + " ";
            }
        }
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        projSelDTO.setCurrentOrPrior("C");
        projSelDTO.setIsPrior(false);
        customQuery += selectClause + " from  \n(" + getProjectionResultsDiscountsPerQuery() + ") C\n ";
        projSelDTO.setCurrentOrPrior("P");
        projSelDTO.setIsPrior(true);
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            projSelDTO.setProjectionId(projSelDTO.getProjIdList().get(i));
            customQuery += " LEFT JOIN (\n" + getProjectionResultsDiscountsPerQuery() + "\n) " + "P" + i + " \n on C.DISCOUNTS=P" + i + ".DISCOUNTS \n "
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
        String projectedSales;
        String projectedReturns;
        projSelDTO.setSales("SALES");
        if (projSelDTO.getDiscountGroupName().contains("Discount $")) {
            projectedSales = "PROJECTION_SALES";
            projectedReturns = "RETURNS_PROJECTED_AMOUNT";
            if (projSelDTO.getDiscountGroupName().contains(VALUE_LABEL)) {
                selectClause += "C." + projectedSales + " AS C_PROJECTION_AMNT,";
                selectClause += "C." + projectedReturns + " AS C_RETURNS_AMNT";
            } else if (projSelDTO.getDiscountGroupName().contains(StringConstantsUtil.VARIANCE_LABEL)) {
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
            if (projSelDTO.getDiscountGroupName().contains(VALUE_LABEL)) {
                selectClause += "C." + projectedSales + " AS C_PROJECTION_RATE,";
                selectClause += "C." + projectedReturns + " AS C_RETURNS_RATE";
            } else if (projSelDTO.getDiscountGroupName().contains(StringConstantsUtil.VARIANCE_LABEL)) {
                selectClause += "0  AS PROJECTION_RATE,";
                selectClause += "0  AS RETURNS_RATE";
            } else {
                selectClause += "0  AS PROJECTION_RATE,";
                selectClause += "0  AS RETURNS_RATE";
            }
        } else {
            projectedSales = "RPU";
            projectedReturns = "RETURNS_RPU";
            if (projSelDTO.getDiscountGroupName().contains(VALUE_LABEL)) {
                selectClause += "C." + projectedSales + " AS C_RPU,";
                selectClause += "C." + projectedReturns + " AS C_RPU";
            } else if (projSelDTO.getDiscountGroupName().contains(StringConstantsUtil.VARIANCE_LABEL)) {
                selectClause += "0  AS PROJECTION_RATE";
                selectClause += "0  AS PROJECTION_RPU";
            } else {
                selectClause += "0  AS  PROJECTION_RATE";
                selectClause += "0  AS PROJECTION_RPU";
            }
        }
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            if (projSelDTO.getGroup().contains(VALUE_LABEL)) {
                selectClause += ", P" + i + "." + projectedSales + " AS P" + i + "_" + projectedSales + " ";
                selectClause += ", P" + i + "." + projectedReturns + "  AS P" + i + "_" + projectedReturns + " ";
            } else if (projSelDTO.getGroup().contains(StringConstantsUtil.VARIANCE_LABEL)) {
                selectClause += ", (IsNull(C." + projectedSales + ", 0)- IsNull(P" + i + "." + projectedSales + ", 0)) AS P" + i + "_" + projectedSales + " ";
                selectClause += ", (IsNull(C." + projectedReturns + ", 0)- IsNull(P" + i + "." + projectedReturns + ", 0)) AS P" + i + "_" + projectedReturns + " ";
            } else {
                selectClause += ", CASE WHEN P" + i + "." + projectedSales + " = 0  THEN 0\n"
                        + " ELSE (IsNull(C." + projectedSales + ", 0)  - IsNull(P" + i + "." + projectedSales + ", 0)  / P" + i + "." + projectedSales + ") \n"
                        + " END   AS P" + i + "_" + projectedSales + " ";
                selectClause += ", CASE  WHEN P" + i + "." + projectedReturns + " = 0 THEN 0\n"
                        + " ELSE (IsNull(C." + projectedReturns + ", 0) - IsNull(P" + i + "." + projectedReturns + ", 0) / P" + i + "." + projectedReturns + ") \n"
                        + " END  AS P" + i + "_" + projectedReturns + " ";
            }
        }
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        projSelDTO.setCurrentOrPrior("C");
        projSelDTO.setIsPrior(false);
        customQuery += selectClause + " from  \n(" + getProjectionResultsDiscountsPerQuery() + ") C\n ";
        projSelDTO.setCurrentOrPrior("P");
        projSelDTO.setIsPrior(true);
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            projSelDTO.setProjectionId(projSelDTO.getProjIdList().get(i));
            customQuery += " LEFT JOIN (\n" + getProjectionResultsDiscountsPerQuery() + "\n) " + "P" + i + " \n on C.DISCOUNTS=P" + i + ".DISCOUNTS \n "
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
        String ccpWhereCond = StringConstantsUtil.SMALL_AND + CCP + ".CCP_DETAILS_SID=" + projectionDetails + ".CCP_DETAILS_SID ";
        return ccpWhereCond;
    }

    public String getComparisonSearch(String workflowStatus, String marketType, String brand,
            String projName, String contHldr, String ndcNo, String ndcName, String desc, String contract,
            String from, String to, String notSearchProjId) {
        String QUOTES = "'";
        String ASTERIK = "*";
        String PERCENT = "%";
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
        } catch (ParseException e) {
            LOGGER.error(" at PvQueryUtils -> getComparisonSearch= {}", e
            );
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
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER as PERIODS, ";
            whereClause += "";
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, ";
            whereClause += "";
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS, ";
            whereClause += "";
            groupBy += "";

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += "I.\"MONTH\" as PERIODS, ";
            whereClause += "";
            groupBy += ", I.\"MONTH\"";
        }

        // To filter the data according to selected period
        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        // To handle the scenario where any discount is not selected in program selection lookup
        String discountTypeColumnName = "'Total discounts' as DISCOUNTS";
        LOGGER.debug("Discount Level={}",projSelDTO.getDiscountLevel());
        if (projSelDTO.getDiscountLevel().equals("Program Category")) {
            LOGGER.error("projSelDTO.getDiscountNameList()= {} and projSelDTO.isIsTotal()= {} ", projSelDTO.getDiscountNameList().size(), projSelDTO.isIsTotal());
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

    public String getProjectionResultsDiscountsPerQuery() {
        String customQuery = "";
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
        String masterTable;
        String table;
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

        String ccpWhereCond = getCCPWhereConditionQuery("E", "CCP");

        selectClause += "I.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER as PERIODS, ";
            whereClause += "";
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, ";
            whereClause += "";
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS, ";
            whereClause += "";
            groupBy += "";

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
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

    public String getPVComparisonProjections(final List<Integer> projId) {
        try {
            String customSql = SQlUtil.getQuery("getProjectionLists");
            if (projId != null && !projId.isEmpty()) {
                customSql += (" PM.PROJECTION_MASTER_SID IN (" + CollectionToString(projId, false) + ")");
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
}