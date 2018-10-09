/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.util;

import com.stpl.app.cff.util.xmlparser.SQlUtil;
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
         String user = StringUtils.EMPTY;
         user = user + StringConstantsUtil.SMALL_AND + table + ".USER_ID=" + userId + StringConstantsUtil.SMALL_AND + table + ".SESSION_ID=" + sessionId + " ";
        return user;
    }

    public String getPivotSelectQuery(String query1, String query2) {
        String finalWhereCond = " on TODIS.YEARS=SALEPPA.YEARS AND TODIS.PERIODS=SALEPPA.PERIODS";
        String selectClause = StringUtils.EMPTY;
        selectClause = selectClause + " (\n" + query1 + "\n) TODIS FULL JOIN (\n" + query2 + "\n) SALEPPA \n" + finalWhereCond;
        return selectClause;
    }

    public String getPivotMainWhereCond(String table) {
        return " on C.YEARS=" + table + ".YEARS AND C.PERIODS=" + table + ".PERIODS \n";
    }

    public String getPivotSelectClause() {
        String selectClause = StringUtils.EMPTY;
        selectClause = selectClause + " SALEPPA.SALES_PROJECTION_SALES as CONTRACT_PROJECTION_SALES,"
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
        String selectClause = StringUtils.EMPTY;
         selectClause = selectClause + " " + projName + ".CONTRACT_PROJECTION_SALES as " + projName + "CONTRACT_PROJECTION_SALES,"
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
        LOGGER.debug("query ={}",query);
        return query;
    }

    public String getCCPWhereConditionQuery(String projectionDetails, String ccp) {
         String ccpWhereCond = StringUtils.EMPTY;
         ccpWhereCond = ccpWhereCond + StringConstantsUtil.SMALL_AND + ccp + ".CCP_DETAILS_SID=" + projectionDetails + ".CCP_DETAILS_SID ";
        return ccpWhereCond;
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

    public static String collectionToString(Collection<?> collectionOfString, boolean toAddQuote) {
        return collectionToString(collectionOfString, toAddQuote, false);
    }

    /**
     * To convert List<String> into a comma separated String
     *
     * @param collectionOfString
     * @param toAddQuote
     * @param toRemoveSpace
     * @return
     */
    public static String collectionToString(Collection<?> collectionOfString, boolean toAddQuote, boolean toRemoveSpace) {

        String framedString = "";
        if (collectionOfString != null && !collectionOfString.isEmpty()) {
            if (toAddQuote) {
                framedString += Arrays.toString(collectionOfString.toArray()).replace('[', '\'').replace(']', '\'').replace(", ", "','");
            } else {
                framedString += Arrays.toString(collectionOfString.toArray()).replace("[", "").replace("]", "");
            }

            if (toRemoveSpace) {
                framedString = framedString.replace(", ", "");
            }
        }
        return framedString;
    }

    public String getPVComparisonProjections(final List<Integer> projId) {
        try {
            String customSql = SQlUtil.getQuery("getProjectionLists");
            if (projId != null && !projId.isEmpty()) {
                customSql += (" PM.PROJECTION_MASTER_SID IN (" + PVQueryUtils.collectionToString(projId, false) + ")");
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