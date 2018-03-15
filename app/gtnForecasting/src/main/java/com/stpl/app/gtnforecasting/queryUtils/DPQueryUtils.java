/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.queryUtils;

import static com.stpl.app.gtnforecasting.dao.impl.DiscountProjectionForChannelsDAOImpl.DBDate;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.DASH;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author vigneshkanna
 */
public class DPQueryUtils {

    public String updateCheckRecordQuery(SessionDTO session, String hierarchyNo, String hierarchyIndicator, boolean isCustomView, List<String> customViewDetails, String hierarchy) {
        String ccpDetails;
        String customSql;
        final String userId = session.getUserId();
        final String sessionId = session.getSessionId();
        final int projectionId = session.getProjectionId();

        if (isCustomView && customViewDetails != null && !customViewDetails.isEmpty()) {

            final String count = customViewDetails.get(5);
            if (count.equals(DASH)) {
                final String uncheckRecordSql = "UPDATE M SET CHECK_RECORD = 0"
                        + " From ST_CH_DISCOUNT_PROJ_MASTER M, PROJECTION_DETAILS E \n"
                        + " Where M.USER_ID =  " + userId + " \n"
                        + " and M.SESSION_ID = " + sessionId + "\n"
                        + " AND E.PROJECTION_MASTER_SID ='" + projectionId + "' \n"
                        + " AND E.PROJECTION_DETAILS_SID = M.PROJECTION_DETAILS_SID ";
                HelperTableLocalServiceUtil.executeUpdateQuery(uncheckRecordSql);
            }
            ccpDetails = getCustomCCPDetailsQuery(hierarchyIndicator, projectionId, customViewDetails, true, hierarchyNo);

            customSql = "UPDATE M SET CHECK_RECORD = 1 FROM ST_CH_DISCOUNT_PROJ_MASTER M, PROJECTION_DETAILS B, \n" + ccpDetails
                    + " WHERE M.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID  "
                    + "  and M.USER_ID = " + userId + "\n"
                    + " and M.SESSION_ID = " + sessionId + "\n"
                    + " AND B.PROJECTION_MASTER_SID='" + projectionId + "'  \n"
                    + " AND CCP.CCP_DETAILS_SID = B.CCP_DETAILS_SID ";
        } else {
            final String appendQuery = " HLD.HIERARCHY_NO in(" + hierarchyNo + ")";
            ccpDetails = getCCPDetailsQuery(hierarchy, projectionId, StringUtils.EMPTY, hierarchyNo, StringUtils.EMPTY, appendQuery, true);

            customSql = "UPDATE M SET CHECK_RECORD = CASE WHEN M.PROJECTION_DETAILS_SID  \n"
                    + " in (SELECT M.PROJECTION_DETAILS_SID FROM ST_CH_DISCOUNT_PROJ_MASTER M, PROJECTION_DETAILS B, \n" + ccpDetails
                    + " WHERE M.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID  "
                    + "  and M.USER_ID = " + userId + "\n"
                    + " and M.SESSION_ID  = " + sessionId + "\n"
                    + " AND B.PROJECTION_MASTER_SID='" + projectionId + "'  \n"
                    + " AND CCP.CCP_DETAILS_SID = B.CCP_DETAILS_SID \n"
                    + " GROUP  BY  M.PROJECTION_DETAILS_SID) \n"
                    + " THEN 1 ELSE 0 END \n"
                    + " From ST_CH_DISCOUNT_PROJ_MASTER M, PROJECTION_DETAILS E \n"
                    + " Where M.USER_ID = " + userId + " \n"
                    + " and M.SESSION_ID =  " + sessionId + "\n"
                    + " AND E.PROJECTION_MASTER_SID ='" + projectionId + "' \n"
                    + " AND E.PROJECTION_DETAILS_SID = M.PROJECTION_DETAILS_SID ";
        }
        return customSql;
    }

    public String massUpdateQuery(Date startDate, Date endDate,
            String fieldValue, SessionDTO session, String selectedField) {
        final String userId = session.getUserId();
        final String sessionId = session.getSessionId();
        final int projectionId = session.getProjectionId();
        final int discount = session.getDiscountTypeId();
        String customSql;
        int count = Integer.parseInt(session.getTotalDiscountCount());
        String fieldValuequery;
        double val;
        val = Double.parseDouble(fieldValue);
        if ("Projected Rate".equals(selectedField)) {
            fieldValuequery = " \n UPDATE DPT SET DPT.DISCOUNT_RATE = ";
        } else if (Constant.PRODUCT_GROWTH.equals(selectedField)) {
            fieldValuequery = " \n UPDATE DPT SET DPT.PRODUCT_GROWTH = ";
        } else if ("Projected Amount".equals(selectedField)) {
            val = val / count;
            fieldValuequery = " \n UPDATE DPT SET DPT.DISCOUNT_AMOUNT = ";
        } else {
            fieldValuequery = " \n UPDATE DPT SET DPT.ACCOUNT_GROWTH = ";
        }
        customSql = fieldValuequery + val + " FROM ST_CH_PROJECTION_DISCOUNT DPT, \n"
                + "(SELECT M.PROJECTION_DETAILS_SID, DP.PERIOD_SID, RS.RS_CONTRACT_SID FROM ST_CH_DISCOUNT_PROJ_MASTER M, PROJECTION_DETAILS B,\n"
                + " ST_CH_PROJECTION_DISCOUNT DP, RS_CONTRACT RS, \"PERIOD\" P \n"
                + " WHERE  M.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID \n"
                + " AND M.CHECK_RECORD = 1 \n"
                + " and M.USER_ID = " + userId + "\n"
                + "  and  M.SESSION_ID = " + sessionId + "\n"
                + " and DP.USER_ID = " + userId + "\n"
                + " and DP.SESSION_ID = " + sessionId + "\n"
                + " AND DP.PROJECTION_DETAILS_SID = M.PROJECTION_DETAILS_SID \n"
                + " AND M.RS_CONTRACT_SID = RS.RS_CONTRACT_SID \n"
                + " AND RS.RS_TYPE ='" + discount + "' \n"
                + " AND DP.PERIOD_SID = P.PERIOD_SID AND P.PERIOD_DATE "
                + " BETWEEN CONVERT(DATE, '" + DBDate.format(startDate) + "') AND CONVERT(DATE, '" + DBDate.format(endDate) + "')" + "\n"
                + " AND B.PROJECTION_MASTER_SID ='" + projectionId + "' "
                + ") A \n"
                + " WHERE DPT.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID \n"
                + " AND DPT.PERIOD_SID = A.PERIOD_SID \n"
                + " AND DPT.RS_CONTRACT_SID = A.RS_CONTRACT_SID \n"
                + " and DPT.USER_ID = " + userId + "\n"
                + " and DPT.SESSION_ID = " + sessionId + "\n";
        return customSql;
    }

    public String saveDiscountProjectionListView(Date startDate, Date endDate, boolean isCustomHierarchy,
            List<String> customViewDetails, String hierarchyIndicator, SessionDTO session, String hierarchyNo, String fieldValue, String selectedField) {

        final int discountName = session.getDiscountTypeId();
        final String userId = session.getUserId();
        final int projectionId = session.getProjectionId();
        final String sessionId = session.getSessionId();
        String fieldValuequery = StringUtils.EMPTY;
        String hierarchy = StringUtils.EMPTY;
        // C indicates customer, P indicates product
        if (hierarchyIndicator.equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
            hierarchy = "PROJECTION_CUST_HIERARCHY ";
        } else if (hierarchyIndicator.equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            hierarchy = "PROJECTION_PROD_HIERARCHY ";
        }

        String ccpDetails;
        if (isCustomHierarchy && customViewDetails != null && !customViewDetails.isEmpty()) {
            ccpDetails = getCustomCCPDetailsQuery(hierarchyIndicator, projectionId, customViewDetails, true, hierarchyNo);
        } else {
            String appendQuery = " HLD.HIERARCHY_NO = '" + hierarchyNo + "'";
            ccpDetails = getCCPDetailsQuery(hierarchy, projectionId, StringUtils.EMPTY, hierarchyNo, StringUtils.EMPTY, appendQuery, true);
        }
        if ("Projected Rate".equals(selectedField)) {
            fieldValuequery = " \n UPDATE DPT SET DPT.DISCOUNT_RATE = ";
        } else if (Constant.PRODUCT_GROWTH.equals(selectedField)) {
            fieldValuequery = " \n UPDATE DPT SET DPT.PRODUCT_GROWTH = ";
        } else if (Constant.ACCOUNT_GROWTH.equals(selectedField)) {
            fieldValuequery = " \n UPDATE DPT SET DPT.ACCOUNT_GROWTH = ";
        } else if ("Projected Amount".equals(selectedField)) {
            fieldValuequery = " \n UPDATE DPT SET DPT.DISCOUNT_AMOUNT = ";
        }
        String customSql = fieldValuequery + fieldValue + " FROM ST_CH_PROJECTION_DISCOUNT DPT, \n"
                + "(SELECT M.PROJECTION_DETAILS_SID, DP.PERIOD_SID , M.RS_CONTRACT_SID FROM ST_CH_DISCOUNT_PROJ_MASTER M, PROJECTION_DETAILS B,\n "
                + " ST_CH_PROJECTION_DISCOUNT DP, RS_CONTRACT RS, \"PERIOD\" P, \n" + ccpDetails
                + " WHERE  M.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID \n"
                + " and M.USER_ID = " + userId + "\n"
                + " and  M.SESSION_ID = " + sessionId + "\n"
                + " and DP.USER_ID = " + userId + "\n"
                + " and DP.SESSION_ID = " + sessionId + "\n"
                + " and DP.RS_CONTRACT_SID = M.RS_CONTRACT_SID \n"
                + " AND B.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID \n"
                + " AND DP.PROJECTION_DETAILS_SID = M.PROJECTION_DETAILS_SID \n"
                + " AND RS.RS_CONTRACT_SID = M.RS_CONTRACT_SID \n"
                + " AND RS.RS_TYPE = '" + discountName + "' \n"
                + " AND DP.PERIOD_SID = P.PERIOD_SID \n"
                + " AND P.PERIOD_DATE "
                + " BETWEEN CONVERT(DATE, '" + DBDate.format(startDate) + "') AND CONVERT(DATE, '" + DBDate.format(endDate) + "')" + "\n"
                + " AND B.PROJECTION_MASTER_SID ='" + projectionId + "' \n"
                + ") A "
                + " WHERE DPT.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID "
                + " AND DPT.RS_CONTRACT_SID = A.RS_CONTRACT_SID \n"
                + " AND DPT.PERIOD_SID = A.PERIOD_SID \n"
                + " AND DPT.USER_ID = " + userId + "\n"
                + " AND DPT.SESSION_ID = " + sessionId + "\n";
        return customSql;
    }

    public String getCCPDetailsQuery(String hierarchy, int projectionId, String connector,
            String hierarchyNo, String hierarchyNumbers, String levelSelectionStatement, boolean isCount) {
        hierarchyNo = hierarchyNo.replaceAll("'", StringUtils.EMPTY);
        String query = "(SELECT CCPMAP.CCP_DETAILS_SID,\n"
                + "                         HLD.HIERARCHY_NO,\n"
                + "                         HLD.RELATIONSHIP_LEVEL_SID\n"
                + "                  FROM   (SELECT RLD.HIERARCHY_NO,\n"
                + "                                 CCP.CCP_DETAILS_SID\n"
                + "                          FROM   RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                + "                          JOIN   CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID = CCP.RELATIONSHIP_LEVEL_SID\n"
                + "                          JOIN   PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID = " + projectionId + "\n"
                + "                          JOIN    " + hierarchy + "  PCH ON PCH.RELATIONSHIP_LEVEL_SID = RLD.RELATIONSHIP_LEVEL_SID \n"
                + "                                 AND PCH.PROJECTION_MASTER_SID=" + projectionId + "\n"
                + "                          ) CCPMAP JOIN \n"
                + "                         (SELECT RLD1.HIERARCHY_NO,\n"
                + "                                 RLD1.RELATIONSHIP_LEVEL_SID,\n"
                + "                                 RLD1.LEVEL_NO\n"
                + "                          FROM   RELATIONSHIP_LEVEL_DEFINITION RLD1\n"
                + "                          JOIN   " + hierarchy + "   PCH ON PCH.RELATIONSHIP_LEVEL_SID = RLD1.RELATIONSHIP_LEVEL_SID AND PCH.PROJECTION_MASTER_SID =  " + projectionId + " \n"
                + "                         AND  RLD1.HIERARCHY_NO LIKE '" + hierarchyNo + "%') HLD ON CCPMAP.HIERARCHY_NO LIKE HLD.HIERARCHY_NO+'%' \n";
        if (isCount) {
            query = query + " WHERE " + levelSelectionStatement + " \n";
        } else {
            query = query + " WHERE " + levelSelectionStatement + connector + " HLD.HIERARCHY_NO in(" + hierarchyNumbers + ")\n";
        }
        query = query + ")CCP";
        return query.toString();
    }

    public String getCustomCCPDetailsQuery(String hierarchyIndicator, int projectionId, List<String> customViewDetails, boolean custom, String hierarchyNo) {
        final String customId = customViewDetails.get(0);
        final String customerLevelNo = customViewDetails.get(1);
        final String customerHierarchyNo = customViewDetails.get(NumericConstants.TWO);
        final String productLevelNo = customViewDetails.get(3);
        final String productHierarchyNo = customViewDetails.get(4);
        String query = " (SELECT distinct HLD" + hierarchyIndicator + ".RELATIONSHIP_LEVEL_SID,HLD" + hierarchyIndicator + ".HIERARCHY_NO, CCPMAPC.CCP_DETAILS_SID FROM \n"
                + "    (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + "    FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + "    JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID\n"
                + "    JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projectionId + ") CCPMAPC\n"
                + "    JOIN (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + "        FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + "        JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID\n"
                + "        JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projectionId + ") CCPMAPP \n"
                + "    ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID\n"
                + "    JOIN \n"
                + "    (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD \n"
                + "        JOIN dbo.CUSTOM_VIEW_MASTER CVM ON CVD.CUSTOM_VIEW_MASTER_SID=" + customId + " AND CVD.LEVEL_NO  like '" + customerLevelNo + "'\n"
                + "        JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID\n"
                + "        JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + "        JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projectionId + "\n"
                + "        WHERE RLD2.HIERARCHY_NO like '" + customerHierarchyNo + "%') HLDC ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%'\n"
                + "    JOIN \n"
                + "    (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD \n"
                + "        JOIN dbo.CUSTOM_VIEW_MASTER CVM ON CVD.CUSTOM_VIEW_MASTER_SID=" + customId + " AND CVD.LEVEL_NO like '" + productLevelNo + "'\n"
                + "        JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID\n"
                + "        JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + "        JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projectionId + "\n"
                + "        WHERE RLD2.HIERARCHY_NO like '" + productHierarchyNo + "%') HLDP ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%'\n";
        if (custom) {
            query = query + "    WHERE HLD" + hierarchyIndicator + ".HIERARCHY_NO = '" + hierarchyNo + "'";
        }
        query = query + ") CCP";
        return query.toString();
    }

    public String getLevelvalues(boolean isLevelFilter, int endLevelNo, boolean isCustomHierarchy, int projectionId, String hierarchy, int startLevelNo) {
        StringBuffer query = new StringBuffer(StringUtils.EMPTY);
        StringBuffer orderBy = new StringBuffer(StringUtils.EMPTY);
        StringBuffer endLevelRestriction = new StringBuffer(StringUtils.EMPTY);
        if (isLevelFilter) {
            query.append("Select Distinct HLD.LEVEL_NO ,('Level '+HLD.LEVEL_NO +' - '+HLD.LEVEL_NAME)\n");
        } else {
            query.append("Select Distinct HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_VALUES \n");
            orderBy.append(" order by HLD.HIERARCHY_NO ");
            if (endLevelNo != 0) {
                endLevelRestriction.append(" AND RLD1.LEVEL_NO < " ).append( endLevelNo);
            }
        }

        if (!isCustomHierarchy) {

            query.append(" FROM   (SELECT RLD.RELATIONSHIP_LEVEL_VALUES,\n");
            query.append(" RLD.HIERARCHY_NO,\n");
            query.append(" CCP.CCP_DETAILS_SID\n");
            query.append(" FROM   RELATIONSHIP_LEVEL_DEFINITION RLD\n");
            query.append(" JOIN   CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID = CCP.RELATIONSHIP_LEVEL_SID\n");
            query.append(" JOIN   PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID = " ).append( projectionId ).append( "\n");
            query.append(" JOIN   PROJECTION_MASTER PM ON PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n");
            query.append(" WHERE  PM.PROJECTION_MASTER_SID = " ).append( projectionId ).append( ") CCPMAP,\n");
            query.append(" (SELECT RLD1.HIERARCHY_NO,\n");
            query.append(" RLD1.RELATIONSHIP_LEVEL_SID,\n");
            query.append(" RLD1.RELATIONSHIP_LEVEL_VALUES,\n");
            query.append(" RLD1.LEVEL_NO,\n");
            query.append(" RLD1.LEVEL_NAME\n");
            query.append(" FROM   RELATIONSHIP_LEVEL_DEFINITION RLD1\n");
            query.append(" JOIN   " ).append( hierarchy ).append( "  PCH ON PCH.RELATIONSHIP_LEVEL_SID = RLD1.RELATIONSHIP_LEVEL_SID\n");
            query.append(" AND PCH.PROJECTION_MASTER_SID = " ).append( projectionId ).append( "\n");
            query.append(" WHERE  RLD1.HIERARCHY_NO LIKE '%' AND RLD1.LEVEL_NO >= " ).append( startLevelNo ).append( endLevelRestriction ).append( ") HLD\n");
            query.append(" WHERE  CCPMAP.HIERARCHY_NO LIKE HLD.HIERARCHY_NO+'%' \n " ).append( orderBy);

        }
        return query.toString();
    }

    public String masterTableUpdateQuery(String baselinePeriods, int projectionId, int discountName, String selectedPeriods) {
        StringBuffer query = new StringBuffer(StringUtils.EMPTY);
        query.append("UPDATE DM SET DM.BASELINE_PERIODS = '" ).append( baselinePeriods ).append( "', DM.SELECTED_PERIODS = '" ).append( selectedPeriods ).append( "' FROM ST_CH_DISCOUNT_PROJ_MASTER DM ");
        query.append(" JOIN (SELECT PD.PROJECTION_DETAILS_SID");

        query.append(", DPM.RS_CONTRACT_SID");

        query.append(" FROM   PROJECTION_DETAILS PD JOIN ST_CH_DISCOUNT_PROJ_MASTER DPM ON DPM.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID");

        query.append(" JOIN  RS_CONTRACT RS ON RS.RS_CONTRACT_SID = DPM.RS_CONTRACT_SID");
        query.append(" WHERE  PD.PROJECTION_MASTER_SID = " ).append( projectionId);

        query.append(" AND RS.RS_TYPE = ");
        query.append("'" ).append( discountName ).append( "')A ON A.PROJECTION_DETAILS_SID = DM.PROJECTION_DETAILS_SID  WHERE DM.CHECK_RECORD = 1");

        query.append(" AND A.RS_CONTRACT_SID = DM.RS_CONTRACT_SID");
        return query.toString();
    }

    public String discountProjectionTableUpdateQuery(String adjustmentType, String adjustmentBasis, String allocationMethodology, String adjustmentValue, int projectionId,
            int discountName, String userId, String sessionId, String period, String selectedPeriodsToUpdate) {
        StringBuffer query = new StringBuffer(StringUtils.EMPTY);
        if ("Lowest Level (Month and Product)".equals(allocationMethodology)) {
            allocationMethodology = "None";
        }
        query.append("UPDATE DP ");
        query.append(" SET ADJUSTMENT_TYPE='" ).append( adjustmentType ).append( "' , ADJUSTMENT_BASIS='" ).append( adjustmentBasis ).append( "', ADJUSTMENT_VALUE=" ).append( adjustmentValue ).append( ", ");
        query.append(" ADJUSTMENT_METHODOLOGY = '" ).append( allocationMethodology ).append( "' FROM ST_CH_PROJECTION_DISCOUNT DP");
        query.append(" JOIN (SELECT   DPM.PROJECTION_DETAILS_SID, DP.PERIOD_SID  FROM ST_CH_DISCOUNT_PROJ_MASTER DPM");
        query.append(" JOIN PROJECTION_DETAILS B ON DPM.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID ");
        query.append(" JOIN ST_CH_PROJECTION_DISCOUNT DP ON DP.PROJECTION_DETAILS_SID=DPM.PROJECTION_DETAILS_SID ");

        query.append(" JOIN RS_CONTRACT RS ON DPM.RS_CONTRACT_SID = RS.RS_CONTRACT_SID ");

        query.append(" JOIN \"PERIOD\" Ppr on DP.PERIOD_SID=Ppr.PERIOD_SID  ");
        query.append(" WHERE B.PROJECTION_MASTER_SID=" ).append( projectionId ).append( " AND DPM.CHECK_RECORD = 1");

        query.append("AND RS.RS_TYPE = '" ).append( discountName ).append( "'");

        query.append(" AND Ppr.PERIOD_SID in(SELECT PERIOD_SID from \"PERIOD\" PR WHERE " ).append( period ).append( " IN (" ).append( selectedPeriodsToUpdate ).append( ")) ");
        query.append(" GROUP BY DPM.PROJECTION_DETAILS_SID, DP.PERIOD_SID )A ON A.PROJECTION_DETAILS_SID = DP.PROJECTION_DETAILS_SID WHERE DP.PERIOD_SID = A.PERIOD_SID");
        query.append(" AND DP.USER_ID = " ).append( userId ).append( "\n");
        query.append(" AND DP.SESSION_ID = " ).append( sessionId ).append( "\n");

        return query.toString();
    }

    public String checkClearAllQuery(int projectionId, String userId, String sessionId, boolean checkClear) {

        int check = checkClear ? 1 : 0;
        String query = "UPDATE M SET CHECK_RECORD = " + check
                + " From  ST_CH_DISCOUNT_PROJ_MASTER M, PROJECTION_DETAILS E \n"
                + " Where M.USER_ID = " + userId + " \n"
                + "  and M.SESSION_ID = " + sessionId + "\n"
                + " AND  E.PROJECTION_MASTER_SID ='" + projectionId + "' \n"
                + " AND E.PROJECTION_DETAILS_SID = M.PROJECTION_DETAILS_SID  ";

        return query;
    }
}
