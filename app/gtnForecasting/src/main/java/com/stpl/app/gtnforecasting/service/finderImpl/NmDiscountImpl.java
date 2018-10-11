/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.service.finderImpl;

import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import static com.stpl.app.serviceUtils.Constants.FrequencyConstants.*;
import com.stpl.app.utils.Constants;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Abishek.Ram
 */
public class NmDiscountImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(NmDiscountImpl.class);
    
    

    public List getDiscountNo(int projectionId, List<String> priceGroupType) {

        String customSql = StringUtils.EMPTY;
        try {
            String selectedDiscounts = "";
            StringBuilder selectedDiscountsBuilder = new StringBuilder();
            String comma = ", ";
            if (priceGroupType != null && !priceGroupType.isEmpty()) {
                for (int i = 0; i < priceGroupType.size(); i++) {
                    if (i != 0) {
                        selectedDiscountsBuilder.append(comma);
                    }
                    selectedDiscountsBuilder.append('\'' ).append( priceGroupType.get(i) ).append( '\'');
                }
            }
            selectedDiscounts = selectedDiscountsBuilder.toString();
            customSql = "select Distinct RS.RS_MODEL_SID as DISCOUNT_ID,RS.RS_NAME as DISCOUNT_NAME from RS_MODEL RS, ST_NM_DISCOUNT_PROJ_MASTER DM, PROJECTION_DETAILS D , PROJECTION_MASTER M where DM.PROJECTION_DETAILS_SID = D.PROJECTION_DETAILS_SID "
                    + " and D.PROJECTION_MASTER_SID = " + projectionId
                    + " and DM.PRICE_GROUP_TYPE in (" + selectedDiscounts + ")"
                    + " and DM.RS_MODEL_SID=RS.RS_MODEL_SID"
                    + " order by DISCOUNT_NAME";

            return HelperTableLocalServiceUtil.executeSelectQuery(customSql);
        } catch (Exception e) {
            return Collections.emptyList(); 
        }
    }
    public int getIndex(int projectionId, String hierarchyIndicator, String hierarchyNo, String selectedHiearchyNo) {

        List list = new ArrayList();
        String customSql = StringUtils.EMPTY;
        LOGGER.debug(" inside getIndex");
        try {
            customSql = "select TEMP.TEMP_INDEX from(SELECT RLD.HIERARCHY_NO, RLD.RELATIONSHIP_LEVEL_VALUES, \n"
                    + " ROW_NUMBER() OVER (ORDER BY RLD.RELATIONSHIP_LEVEL_VALUES ASC) AS TEMP_INDEX"
                    + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                    + Constant.SPACE_JOIN_SPACE + CommonUtils.getViewTableName(hierarchyIndicator) + " PCH ON PCH.RELATIONSHIP_LEVEL_SID = RLD.RELATIONSHIP_LEVEL_SID\n"
                    + " AND PCH.PROJECTION_MASTER_SID = " + projectionId + " \n"
                    + " WHERE RLD.HIERARCHY_NO LIKE '" + hierarchyNo + "%' and RLD.LEVEL_NO = " + (selectedHiearchyNo.length() - selectedHiearchyNo.replace(".", "").length()) + ") "
                    + " TEMP where TEMP.HIERARCHY_NO='" + selectedHiearchyNo + "'";
            list = HelperTableLocalServiceUtil.executeSelectQuery(customSql);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(customSql);
            return 0;
        }
        if (list != null && !list.isEmpty()) {
            return Integer.parseInt(String.valueOf(list.get(0)));
        } else {
            return 0;
        }
    }

    public int getCheckedRecordCount(int projectionId, String userId, String sessionId, boolean isProgram, List<String> discountList) {

        List list = new ArrayList();
        String customSql = StringUtils.EMPTY;
        LOGGER.debug(" inside getCheckedRecordCount");
        try {
            String discountTypeQuery = "";
            if (discountList != null && !discountList.isEmpty()) {
                String selectedDiscounts = CommonUtils.collectionToStringMethod(discountList, true);
                if (isProgram) {
                    discountTypeQuery += Constant.JOIN_RS_MODEL_RS_ON_DP_RS_MODEL_SID + selectedDiscounts + Constant.OPEN_BRACKET_NEW_LINE;
                } else {
                    discountTypeQuery += " WHERE DP.PRICE_GROUP_TYPE in (" + selectedDiscounts + Constant.CLOSE_BRACKET_NEW_LINE;
                }
            }

            customSql = "SELECT count(*) AS CHECK_COUNT FROM ST_NM_DISCOUNT_PROJ_MASTER DP JOIN PROJECTION_DETAILS PD ON DP.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID \n"
                    + discountTypeQuery + " AND DP.SESSION_ID=" + sessionId + " AND DP.USER_ID=" + userId + " AND PD.PROJECTION_MASTER_SID = " + projectionId + " AND DP.CHECK_RECORD=1";

            list = HelperTableLocalServiceUtil.executeSelectQuery(customSql);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(customSql);
            return -1;
        }

        if (list != null && !list.isEmpty()) {
            return Integer.parseInt(String.valueOf(list.get(0)));
        } else {
            return -1;
        }
    }

    public List getDiscountPrograms(int projectionId, String userId, String sessionId, String programType, boolean viewFlag) {

        StringBuilder sb = new StringBuilder();
        LOGGER.debug(" inside getDiscountPrograms");
        try {
            String tableName = viewFlag ? Constant.NM_DISCOUNT_PROJ_MASTER : "ST_NM_DISCOUNT_PROJ_MASTER";
            if ("DiscountType".equals(programType)) {
                sb.append("SELECT DISTINCT '' AS DISCOUNT_ID, DM.PRICE_GROUP_TYPE AS DISCOUNT_NAME, RS.RS_MODEL_SID,RS.RS_NAME FROM ST_NM_DISCOUNT_PROJ_MASTER DM "
                        + "JOIN PROJECTION_DETAILS D ON DM.PROJECTION_DETAILS_SID = D.PROJECTION_DETAILS_SID "
                        + "JOIN RS_MODEL RS ON DM.RS_MODEL_SID = RS.RS_MODEL_SID ");
                if (!viewFlag) {
                    sb.append(" AND  DM.USER_ID = ").append(userId).append(" AND DM.SESSION_ID =").append(sessionId);
                }
                sb.append(" AND D.PROJECTION_MASTER_SID = ").append(projectionId).append(" AND DM.PRICE_GROUP_TYPE IS NOT NULL ORDER  BY DISCOUNT_NAME ");
            } else if ("DiscountProgram".equals(programType)) {
                sb.append("SELECT RS.RS_ID   AS DISCOUNT_ID, RS.RS_NAME AS DISCOUNT_NAME,RS.RS_MODEL_SID as RS_ID,RS.RS_NAME as RS_NAME FROM RS_MODEL RS ")
                        .append(" WHERE  EXISTS (SELECT 1 FROM   " ).append( tableName ).append( " DM ")
                        .append(" JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = DM.PROJECTION_DETAILS_SID ")
                        .append(" WHERE  DM.RS_MODEL_SID = RS.RS_MODEL_SID AND RS.RS_NAME IS NOT NULL ");
                if (!viewFlag) {
                    sb.append(" AND  DM.USER_ID = ").append(userId).append(" AND DM.SESSION_ID =").append(sessionId);
                }
                sb.append(" AND PROJECTION_MASTER_SID = ").append(projectionId)
                        .append(" ) ORDER  BY DISCOUNT_NAME ");
            }

            if (HelperTableLocalServiceUtil.executeSelectQuery(sb.toString()).isEmpty() && viewFlag) {
                String query = SQlUtil.getQuery(getClass(),"nm.ProgramTypeInView");
                if ("DiscountType".equals(programType)) {
                    query = query.replace("[$Select_STATEMENT]", "SELECT DISTINCT '' AS DISCOUNT_ID, PRICE_GROUP_TYPE AS DISCOUNT_NAME, RS_MODEL_SID,RS_NAME");
                    query = query.replace("[$PROJECTION_ID]", String.valueOf(projectionId));
                } else if ("DiscountProgram".equals(programType)) {
                    query = query.replace("[$Select_STATEMENT]", "SELECT RS_ID AS DISCOUNT_ID, RS_NAME AS DISCOUNT_NAME,RS_MODEL_SID as RS_ID,RS_NAME as RS_NAME");
                    query = query.replace("[$PROJECTION_ID]", String.valueOf(projectionId));
                }
                return HelperTableLocalServiceUtil.executeSelectQuery(query);
            }
            return HelperTableLocalServiceUtil.executeSelectQuery(sb.toString());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sb.toString());
            return Collections.emptyList();
        }
    }

    public void checkClearAll(int projectionId, String userId, String sessionId, String userGroup, boolean checkValue) {

        String query = StringUtils.EMPTY;
        LOGGER.debug(" inside checkClearAll");
        try {
            int check = checkValue ? 1 : 0;

            query = Constant.UPDATE_M_SET_CHECK_RECORD + check
                    + " From ST_NM_DISCOUNT_PROJ_MASTER M, PROJECTION_DETAILS E \n"
                    + Constant.WHERE_M_USER_ID + userId + " \n"
                    + Constant.AND_M_SESSION_ID + sessionId + "\n"
                    + Constant.AND_EPROJECTION_MASTER_SID + projectionId + "' \n"
                    + " AND E.PROJECTION_DETAILS_SID = M.PROJECTION_DETAILS_SID ";
            if (!userGroup.isEmpty()) {
                query += " and M.USER_GROUP = '" + userGroup + "'\n";
            }

            HelperTableLocalServiceUtil.executeUpdateQuery(query);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(query);
        }
    }

    public void massUpdate(int projectionId, String userId, String sessionId, String frequency, List<Integer> startAndEndPeriods, String selectedField, String fieldValue,
            List<String> checkedDiscounts, boolean isProgram) {

        String customSql = "";
        LOGGER.debug(" entering massUpdate");
        try {
            String column = "";
            String column2 = "";
            if ("Discount Rate".equals(selectedField)) {
                column = "DPT.PROJECTION_RATE";
                column2 = "SET DPT.PROJECTION_SALES = NM.PROJECTION_SALES * (DPT.PROJECTION_RATE / 100.0),\n"
                        + "DPT.PROJECTION_RPU = (NM.PROJECTION_SALES * (DPT.PROJECTION_RATE / 100.0)) / NULLIF(NM.PROJECTION_UNITS, 0) ";
            }
            if ("RPU".equals(selectedField)) {
                column2 = "SET    DPT.PROJECTION_SALES = DPT.PROJECTION_RPU * NM.PROJECTION_UNITS,\n"
                        + "DPT.PROJECTION_RATE = (( DPT.PROJECTION_RPU * NM.PROJECTION_UNITS ) / NULLIF(NM.PROJECTION_SALES, 0))*100.0";
                column = "DPT.PROJECTION_RPU";
            }
            if ("Discount Amount".equals(selectedField)) {
                column = "DPT.PROJECTION_SALES";
            }
            if ("Growth".equals(selectedField)) {
                column = "DPT.GROWTH";
            }
            if ("Group".equals(selectedField)) {
                customSql = "UPDATE ST_NM_DISCOUNT_PROJ_MASTER SET USER_GROUP = '" + fieldValue + "' where PROJECTION_DETAILS_SID in \n"
                        + "(SELECT M.PROJECTION_DETAILS_SID FROM ST_NM_DISCOUNT_PROJ_MASTER M, PROJECTION_DETAILS B \n"
                        + Constant.WHERE_MPROJECTION_DETAILS_SID
                        + Constant.AND_MUSER_ID + userId + "\n"
                        + Constant.AND_M_SESSION_ID + sessionId + "\n"
                        + " AND M.CHECK_RECORD = 1"
                        + Constant.AND_BPROJECTION_MASTER_SID + projectionId + "')";

            } else {
                int startFreq = 0;
                int endFreq = 0;
                int startYear = 0;
                int endYear = 0;

                if (startAndEndPeriods != null && !startAndEndPeriods.isEmpty()) {
                    startFreq = startAndEndPeriods.get(0);
                    if (startAndEndPeriods.size() > 1) {
                        startYear = startAndEndPeriods.get(1);
                    }
                    if (startAndEndPeriods.size() > 2) {
                        endFreq = startAndEndPeriods.get(2);
                    }
                    if (startAndEndPeriods.size() > 3) {
                        endYear = startAndEndPeriods.get(3);
                    }
                }

                int startMonth = 0;
                int endMonth = 0;
                if (frequency.equals(ANNUALLY.getConstant())) {
                    startMonth = 1;
                    endMonth = 12;
                }
                if (frequency.equals(QUARTERLY.getConstant())) {
                    switch (startFreq) {
                        case 1:
                            startMonth = 1;
                            break;
                        case 2:
                            startMonth = 4;
                            break;
                        case 3:
                            startMonth = 7;
                            break;
                        case 4:
                            startMonth = 10;
                            break;
                        default:
                            break;

                    }
                    switch (endFreq) {
                        case 1:
                            endMonth = 3;
                            break;
                        case 2:
                            endMonth = 6;
                            break;
                        case 3:
                            endMonth = 9;
                            break;
                        case 4:
                            endMonth = 12;
                            break;
                        default:
                            break;

                    }
                }
                if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
                    switch (startFreq) {
                        case 1:
                            startMonth = 1;
                            break;
                        case 2:
                            startMonth = 7;
                            break;
                        default:
                            break;
                 
                    }
                    switch (endFreq) {
                        case 1:
                            endMonth = 6;
                            break;
                        case 2:
                            endMonth = 12;
                            break;
                        default:
                            break;
                    }
                }
                if (frequency.equals(MONTHLY.getConstant())) {
                    startMonth = startFreq;
                    endMonth = endFreq;
                }

                String selectedDiscounts = CommonUtils.collectionToStringMethod(checkedDiscounts, true);
                String discountType = "M.PRICE_GROUP_TYPE";
                if (isProgram) {
                    discountType = "RS.RS_NAME";
                }
                String declareStatement = Constant.DECLARE_START_MONTH_INT + startMonth + "\n"
                        + Constant.DECLARE_START_YEAR_INT + startYear + "\n"
                        + Constant.DECLARE_END_MONTH_INT + endMonth + "\n"
                        + Constant.DECLARE_END_YEAR_INT + endYear + "\n";

                if ("Discount Rate".equals(selectedField) || "RPU".equals(selectedField)) {
                    customSql = declareStatement + SQlUtil.getQuery(getClass(),"discRatemassPopulate");
                    customSql = customSql.replaceAll("@UID", userId);
                    customSql = customSql.replaceAll("@SNID", sessionId);
                    customSql = customSql.replaceAll("@PERIODLIST", getPeriodFilter());
                    customSql = customSql.replaceAll("@PROJID", Integer.toString(projectionId));
                    customSql = customSql.replaceAll("@SELDISC", "" + selectedDiscounts);
                    customSql = customSql.replaceAll("@RATE", "" + fieldValue);
                    customSql = customSql.replace("@COLUMN1", column);
                    customSql = customSql.replace("@COL2", column2);
                    customSql = customSql.replace("@DISCTYPE ", discountType + " IN ");
                } else {
                    customSql = declareStatement + " \n UPDATE DPT SET " + column + " = " + fieldValue + " FROM ST_NM_DISCOUNT_PROJECTION DPT, \n"
                            + "(SELECT M.PROJECTION_DETAILS_SID, DP.PERIOD_SID, RS.RS_MODEL_SID FROM ST_NM_DISCOUNT_PROJ_MASTER M, PROJECTION_DETAILS B,\n"
                            + " ST_NM_DISCOUNT_PROJECTION DP, RS_MODEL RS, \"PERIOD\" P \n"
                            + Constant.WHERE_MPROJECTION_DETAILS_SID
                            + " AND M.CHECK_RECORD = 1 \n"
                            + Constant.AND_MUSER_ID + userId + "\n"
                            + Constant.AND_M_SESSION_ID + sessionId + "\n"
                            + Constant.AND_DP_USER_ID + userId + "\n"
                            + Constant.AND_DP_SESSION_ID + sessionId + "\n"
                            + " AND DP.PROJECTION_DETAILS_SID = M.PROJECTION_DETAILS_SID \n"
                            + " AND M.RS_MODEL_SID = RS.RS_MODEL_SID \n"
                            + Constant.SPACE_AND_SPACE + discountType + " in (" + selectedDiscounts + Constant.OPEN_BRACKET_NEW_LINE
                            + " AND DP.PERIOD_SID = P.PERIOD_SID AND P.PERIOD_DATE " + getPeriodFilter() + "\n "
                            + Constant.AND_BPROJECTION_MASTER_SID + projectionId + "' "
                            + ") A \n"
                            + " WHERE DPT.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID \n"
                            + " AND DPT.PERIOD_SID = A.PERIOD_SID \n"
                            + " AND DPT.RS_MODEL_SID = A.RS_MODEL_SID \n"
                            + " and DPT.USER_ID = " + userId + "\n"
                            + " and DPT.SESSION_ID = " + sessionId + "\n";
                }
            }
            HelperTableLocalServiceUtil.executeUpdateQuery(customSql);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(customSql);
        }
    }
    

    private String getPeriodFilter() {
        String period = StringUtils.EMPTY;
        period = period +  " BETWEEN \n "
                + Constant.SPACE_CASE_NEW_LINE
                + Constant.WHEN_START_MONTH_1_THEN_CONVERT_DATE
                + Constant.WHEN_START_MONTH_2_THEN_CONVERT_DATE
                + Constant.WHEN_START_MONTH_3_THEN_CONVERT_DATE
                + Constant.WHEN_START_MONTH_4_THEN_CONVERT_DATE
                + Constant.WHEN_START_MONTH_5_THEN_CONVERT_DATE
                + Constant.WHEN_START_MONTH_6_THEN_CONVERT_DATE
                + Constant.WHEN_START_MONTH_7_THEN_CONVERT_DATE
                + Constant.WHEN_START_MONTH_8_THEN_CONVERT_DATE
                + Constant.WHEN_START_MONTH_9_THEN_CONVERT_DATE
                + Constant.WHEN_START_MONTH_10_THEN_CONVERT_DATE
                + Constant.WHEN_START_MONTH_11_THEN_CONVERT_DATE
                + Constant.WHEN_START_MONTH_12_THEN_CONVERT_DATE
                + Constant.SPACE_END_NEW_LINE
                + Constant.SPACE_AND_NEW_LINE
                + Constant.SPACE_CASE_NEW_LINE
                + Constant.WHEN_END_MONTH_1_THEN_CONVERT_DATE
                + Constant.WHEN_END_MONTH_2_THEN_CONVERT_DATE
                + Constant.WHEN_END_MONTH_3_THEN_CONVERT_DATE
                + Constant.WHEN_END_MONTH_4_THEN_CONVERT_DATE
                + Constant.WHEN_END_MONTH_5_THEN_CONVERT_DATE
                + Constant.WHEN_END_MONTH_6_THEN_CONVERT_DATE
                + Constant.WHEN_END_MONTH71_THEN_CONVERT_DATE
                + Constant.WHEN_END_MONTH_8_THEN_CONVERT_DATE
                + Constant.WHEN_END_MONTH_9_THEN_CONVERT_DATE
                + Constant.WHEN_END_MONTH_10_THEN_CONVERT_DATE
                + Constant.WHEN_END_MONTH_11_THEN_CONVERT_DATE
                + Constant.WHEN_END_MONTH_12_THEN_CONVERT_DATE
                + Constant.SPACE_END_NEW_LINE;
        return period;
    }
    public List<String> getGroupValues(int projectionId, String userId, String sessionId, String masterTable, List<String> discountList, String relationshipBuilderSid) {

        String customSql = StringUtils.EMPTY;
        LOGGER.debug(" entering getGroupValues");
        try {
            String discountWhereClause = StringUtils.EMPTY;
            String discountJoinClause = StringUtils.EMPTY;
            if (discountList != null && !discountList.isEmpty()) {
                String discountType = discountList.get(0);
                discountList.remove(0);
                String selectedDiscounts = CommonUtils.collectionToStringMethod(discountList, true);
                if (discountType.equals(Constants.PROGRAM)) {
                    discountWhereClause += " AND J.RS_NAME in (" + selectedDiscounts + ") ";
                    discountJoinClause += "JOIN RS_MODEL J ON M.RS_MODEL_SID = J.RS_MODEL_SID ";
                } else {
                    discountWhereClause += " AND M.PRICE_GROUP_TYPE in (" + selectedDiscounts + ") ";
                }
            }
            customSql = "SELECT DISTINCT M.USER_GROUP FROM  " + masterTable + " M\n"
                    + " JOIN PROJECTION_DETAILS PD ON M.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n"
                    + " JOIN PROJECTION_CUST_HIERARCHY PCH ON PCH.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID \n"
                    + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD ON RLD.RELATIONSHIP_LEVEL_SID = PCH.RELATIONSHIP_LEVEL_SID AND RLD.RELATIONSHIP_BUILDER_SID = " + relationshipBuilderSid + "\n"
                    + discountJoinClause + "\n"
                    + " WHERE RLD.LEVEL_NAME = 'Trading Partner' " + discountWhereClause + "AND M.USER_GROUP IS NOT NULL \n"
                    + " AND M.USER_ID = " + userId + "\n"
                    + " AND M.SESSION_ID = " + sessionId + "\n"
                    + " AND PD.PROJECTION_MASTER_SID = " + projectionId + " ORDER BY M.USER_GROUP";
            return (List<String>) HelperTableLocalServiceUtil.executeSelectQuery(customSql);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(customSql);
            return Collections.emptyList();
        }
    }

    public List getDiscountProjectionResults(List<Integer> discountprojectionId, String frequency, String discountString, String view, List<Integer> startAndEndPeriods, int userId, int sessionId, boolean viewFlag) {

        String tableName = viewFlag ? StringUtils.EMPTY : "ST_";
        String projectionQuery = "";
        String frequencyDpr = frequency;
        String discountStr = discountString;
        try {
            String idString = "";
            StringBuilder idStringBuilder = new StringBuilder();
            for (int i = 0; i < discountprojectionId.size(); i++) {
                if (i != discountprojectionId.size() - 1) {
                    idStringBuilder.append(discountprojectionId.get(i) ).append( ',');
                } else {
                    idStringBuilder.append(discountprojectionId.get(i));
                }
            }
            idString = idStringBuilder.toString();
            String startPeriod = "";
            String endPeriod = "";

            String forecastStartPeriod = "";
            String forecastEndPeriod = "";
            if (discountStr.equals("0")) {
                discountStr = "'" + discountStr + "'";
            }
            if (startAndEndPeriods != null && !startAndEndPeriods.isEmpty()) {
                String hsYear = String.valueOf(startAndEndPeriods.get(0));
                String hsMonth = String.valueOf(startAndEndPeriods.get(1));
                String feYear = String.valueOf(startAndEndPeriods.get(6));
                String feMonth = String.valueOf(startAndEndPeriods.get(7));

                if (hsMonth.length() == 1) {
                    hsMonth = "0" + hsMonth;
                }
                startPeriod = hsYear + hsMonth;

                Calendar calendar = Calendar.getInstance();
                int month = calendar.get(Calendar.MONTH) + 1;
                calendar.add(Calendar.MONTH, -((month % 3) == 0 ? 3 : (month % 3)));
                String heMonth = String.valueOf((calendar.get(Calendar.MONTH) + 1));
                String heYear = String.valueOf(calendar.get(Calendar.YEAR));
                if (heMonth.length() == 1) {
                    heMonth = "0" + heMonth;
                }
                endPeriod = heYear + heMonth;

                calendar = Calendar.getInstance();
                month = calendar.get(Calendar.MONTH) + 1;
                calendar.add(Calendar.MONTH, -((month % 3) == 0 ? 3 : (month % 3)));
                String fsMonth = String.valueOf((calendar.get(Calendar.MONTH) + 2));
                String fsYear = String.valueOf(calendar.get(Calendar.YEAR));
                if (fsMonth.length() == 1) {
                    fsMonth = "0" + fsMonth;
                }
                forecastStartPeriod = fsYear + fsMonth;
                if (feMonth.length() == 1) {
                    feMonth = "0" + feMonth;
                }
                forecastEndPeriod = feYear + feMonth;

            }
            if (frequencyDpr.equals(QUARTERLY.getConstant())) {
                frequencyDpr = Constant.QUARTER;
            }
            if (frequencyDpr.equals(Constant.ANNUALLY)) {
                frequencyDpr = "YEAR";
            }
            if (frequencyDpr.equals(Constant.SEMIANNUALLY)) {
                frequencyDpr = Constant.SEMI_ANNUAL;

            }
            if (frequencyDpr.equals(MONTHLY.getConstant())) {
                frequencyDpr = Constant.MONTH_WITHOUT_SPACE;
            }

            projectionQuery = Constant.SELECT_PR_YEAR_PR + frequencyDpr + Constant.AS_BASE_000_AS_ACTUAL_SALES_000_AS_ACTUAL
                    + Constant.AS_PROJECTION_SALES_SUM_NM_DP_PROJECTION_SAL + frequencyDpr + ",PR.MONTH,SUM(NMDP.PROJECTION_RATE) AS PROJECTION_RATE,0.0 AS ACTUAL_UNITS,"
                    + Constant.SUM_NM_SP_PROJECTION_UNITS_AS_PROJECTION_UN
                    + Constant.JOIN + tableName + "NM_DISCOUNT_PROJECTION NMDP  ON NMDP.PROJECTION_DETAILS_SID = NMDPM.PROJECTION_DETAILS_SID\n";
            if (!viewFlag) {
                projectionQuery += " AND NMDP.USER_ID = NMDPM.USER_ID AND NMDP.SESSION_ID = NMDPM.SESSION_ID";
            }
            projectionQuery += " AND NMDPM.RS_MODEL_SID = NMDP.RS_MODEL_SID\n"
                    + " JOIN PERIOD PR ON PR.PERIOD_SID = NMDP.PERIOD_SID JOIN  " + tableName + "NM_SALES_PROJECTION NMSP ON NMSP.PERIOD_SID = PR.PERIOD_SID\n"
                    + "  JOIN  " + tableName + "NM_SALES_PROJECTION_MASTER NMSPM ON NMSPM.PROJECTION_DETAILS_SID = NMSP.PROJECTION_DETAILS_SID\n";
            if (!viewFlag) {
                projectionQuery += Constant.AND_NM_SP_M_USER_ID_NM_SP_USER_ID_AND_NM_SP
                        + Constant.AND_NM_SP_M_USER_ID_NM_DP_M_USER_ID_AND_NM_SPM;
            }
            projectionQuery += " AND PD.PROJECTION_DETAILS_SID = NMSPM.PROJECTION_DETAILS_SID JOIN RS_MODEL RSM ON RSM.RS_MODEL_SID = NMDPM.RS_MODEL_SID "
                    + Constant.AND_RS_M_RS_MODEL_SID_NM_DP_RS_MODEL_SID_WH + idString + ") ";

            if (!viewFlag) {
                projectionQuery += Constant.AND_NM_DP_MUSER_ID + userId + Constant.AND_NM_DP_M_SESSION_ID + sessionId + " ";
            }

            projectionQuery += Constant.AND_CAST_PR_YEAR_AS_VARCHAR_RIGHT_CAST_PR + forecastStartPeriod + ""
                    + Constant.AND_CAST_PRYEAR_AS_VARCHAR_RIGHT_CAST + forecastEndPeriod + ""
                    + Constant.AND_RS_M_RS_NAME_IN + discountStr + ")"
                    + " GROUP BY PR.YEAR,PR." + frequencyDpr + ",PR.MONTH,PD.PROJECTION_DETAILS_SID"
                    + Constant.UNION_ALL
                    + Constant.SELECT_PR_YEAR_PR + frequencyDpr + Constant.AS_BASE_MAX_NM_AS_ACTUAL_SALES
                    + Constant.AS_ACTUAL_DISCOUNT_MAX_IS_NULL_NM_SP
                    + Constant.AS_PROJECTION_DISCOUNT_PR + frequencyDpr + ",PR.MONTH,SUM(NMDP.PROJECTION_RATE) AS PROJECTION_RATE,"
                    + " Sum(ACTUAL_UNITS) as ACTUAL_UNITS,"
                    + " Sum(NMSP.PROJECTION_UNITS) as PROJECTION_UNITS"
                    + " FROM PROJECTION_DETAILS PD\n"
                    + Constant.JOIN + tableName + "NM_DISCOUNT_PROJ_MASTER NMADM ON  NMADM.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n"
                    + Constant.JOIN + tableName + "NM_ACTUAL_DISCOUNT NMAD ON NMAD.PROJECTION_DETAILS_SID =NMADM.PROJECTION_DETAILS_SID\n";
            if (!viewFlag) {
                projectionQuery += " AND NMAD.USER_ID = NMADM.USER_ID AND NMAD.SESSION_ID = NMADM.SESSION_ID ";
            }
            projectionQuery += "AND NMADM.RS_MODEL_SID = NMAD.RS_MODEL_SID\n"
                    + Constant.JOIN_RS_MODEL_RSM_ON_RS_M_RS_MODEL_SID_NM
                    + " JOIN PERIOD PR ON PR.PERIOD_SID = NMAD.PERIOD_SID JOIN  " + tableName + "NM_ACTUAL_SALES NMAS ON NMAS.PERIOD_SID = PR.PERIOD_SID \n";
            if (!viewFlag) {
                projectionQuery += Constant.AND_NM_AS_USER_ID_NM_AD_USER_ID;
            }
            projectionQuery += " AND NMAS.PROJECTION_DETAILS_SID = NMADM.PROJECTION_DETAILS_SID JOIN  " + tableName + "NM_SALES_PROJECTION_MASTER NMSPM "
                    + " ON NMSPM.PROJECTION_DETAILS_SID = NMAS.PROJECTION_DETAILS_SID ";
            if (!viewFlag) {
                projectionQuery += "AND NMSPM.USER_ID = NMAS.USER_ID  AND NMSPM.SESSION_ID = NMAS.SESSION_ID";
            }
            projectionQuery += " LEFT JOIN  " + tableName + "NM_SALES_PROJECTION NMSP"
                    + Constant.ON_NM_SP_PROJECTION_DETAILS_SID;
            if (!viewFlag) {
                projectionQuery += " AND NMSP.USER_ID = NMSPM.USER_ID AND NMSP.SESSION_ID = NMSPM.SESSION_ID ";
            }
            projectionQuery += "LEFT JOIN  " + tableName + "NM_DISCOUNT_PROJECTION NMDP"
                    + Constant.ON_NM_DP_PROJECTION_DETAILS_SID_NM
                    + " AND NMDP.RS_MODEL_SID = RSM.RS_MODEL_SID ";
            if (!viewFlag) {
                projectionQuery += "AND NMDP.USER_ID = NMSPM.USER_ID AND NMDP.SESSION_ID = NMSPM.SESSION_ID ";
            }
            projectionQuery += Constant.WHERE_PD_PROJECTION_DETAILS_SID_IN + idString + ") ";
            if (!viewFlag) {
                projectionQuery += "AND NMADM.USER_ID =" + userId + "AND NMADM.SESSION_ID =" + sessionId;
            }
            projectionQuery += Constant.AND_CAST_PR_YEAR_AS_VARCHAR_RIGHT + startPeriod + ""
                    + Constant.AND_CAST_PR_YEAR_AS_VARCHAR_RIGHT_CAST_PRMO + endPeriod + ""
                    + Constant.AND_RS_M_RS_NAME_IN + discountStr + ")"
                    + Constant.GROUP_BY_PR_YEAR + frequencyDpr + ",PR.MONTH,PD.PROJECTION_DETAILS_SID ";
            if (view.equalsIgnoreCase("parent")) {
                if (frequencyDpr.equals("YEAR") || frequencyDpr.equals(Constant.MONTH_WITHOUT_SPACE)) {
                    projectionQuery = projectionQuery + "ORDER BY PR.YEAR,PR.MONTH";
                } else {
                    projectionQuery = projectionQuery + "ORDER BY PR.YEAR,PR." + frequencyDpr + Constant.PR_MONTH;
                }
            } else if (frequencyDpr.equals("YEAR") || frequencyDpr.equals(Constant.MONTH_WITHOUT_SPACE)) {
                projectionQuery = projectionQuery + "ORDER BY RSM.RS_NAME,PR.YEAR,PR.MONTH";
            } else {
                projectionQuery = projectionQuery + "ORDER BY RSM.RS_NAME,PR.YEAR,PR." + frequencyDpr + Constant.PR_MONTH;
            }
            return HelperTableLocalServiceUtil.executeSelectQuery(projectionQuery);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(projectionQuery);
        }
        return Collections.emptyList();
    }

    public List getChildDiscount(List<Integer> discountprojectionId, String projection, List<Integer> startAndEndPeriods, String frequency, int userId, int sessionId) {

        String sql = "";
        String frequencyChildDiscount = frequency;
        try {
            String idString;
            int startFreq = 0;
            int endFreq = 0;
            int startYear = 0;
            int endYear = 0;
            String declareStatement = "";
            int startMonth = 0;
            int endMonth = 0;
            StringBuilder idStringBuilder = new StringBuilder();
            for (int i = 0; i < discountprojectionId.size(); i++) {
                if (i != discountprojectionId.size() - 1) {
                    idStringBuilder.append(discountprojectionId.get(i) ).append( ',');
                } else {
                    idStringBuilder.append(discountprojectionId.get(i));
                }
            }
            idString = idStringBuilder.toString();
            if (startAndEndPeriods != null && !startAndEndPeriods.isEmpty()) {
                startFreq = startAndEndPeriods.get(0);
                endFreq = startAndEndPeriods.get(1);
                startYear = startAndEndPeriods.get(2);
                endYear = startAndEndPeriods.get(3);
            }

            if (frequencyChildDiscount.equals(QUARTERLY.getConstant())) {
                frequencyChildDiscount = Constant.QUARTER;

                switch (startFreq) {
                    case 1:
                        startMonth = 1;
                        break;
                    case 2:
                        startMonth = 4;
                        break;
                    case 3:
                        startMonth = 7;
                        break;
                    case 4:
                        startMonth = 10;
                        break;
                    default:
                            break;

                }
                switch (endFreq) {
                    case 1:
                        endMonth = 3;
                        break;
                    case 2:
                        endMonth = 6;
                        break;
                    case 3:
                        endMonth = 9;
                        break;
                    case 4:
                        endMonth = 12;
                        break;
                    default:
                            break;

                }

            }
            if (frequencyChildDiscount.equals(Constant.ANNUALLY)) {
                frequencyChildDiscount = "YEAR";
                startMonth = 1;
                endMonth = 12;
            }
            if (frequencyChildDiscount.equals(Constant.SEMIANNUALLY)) {
                frequencyChildDiscount = Constant.SEMI_ANNUAL;
                switch (startFreq) {
                    case 1:
                        startMonth = 1;
                        break;
                    case 2:
                        startMonth = 7;
                        break;
                    default:
                            break;

                }
                switch (endFreq) {
                    case 1:
                        endMonth = 6;
                        break;
                    case 2:
                        endMonth = 12;
                        break;
                    default:
                            break;

                }

            }
            if (frequencyChildDiscount.equals(MONTHLY.getConstant())) {
                frequencyChildDiscount = Constant.MONTH_WITHOUT_SPACE;
                startMonth = startFreq;
                endMonth = endFreq;
            }

            declareStatement = Constant.DECLARE_START_MONTH_INT + startMonth + "\n"
                    + Constant.DECLARE_START_YEAR_INT + startYear + "\n"
                    + Constant.DECLARE_END_MONTH_INT + endMonth + "\n"
                    + Constant.DECLARE_END_YEAR_INT + endYear + "\n";
            String periodFilter = "";
            periodFilter = "AND D.PERIOD_DATE BETWEEN \n "
                    + Constant.SPACE_CASE_NEW_LINE
                    + Constant.WHEN_START_MONTH_1_THEN_CONVERT_DATE
                    + Constant.WHEN_START_MONTH_2_THEN_CONVERT_DATE
                    + Constant.WHEN_START_MONTH_3_THEN_CONVERT_DATE
                    + Constant.WHEN_START_MONTH_4_THEN_CONVERT_DATE
                    + Constant.WHEN_START_MONTH_5_THEN_CONVERT_DATE
                    + Constant.WHEN_START_MONTH_6_THEN_CONVERT_DATE
                    + Constant.WHEN_START_MONTH_7_THEN_CONVERT_DATE
                    + Constant.WHEN_START_MONTH_8_THEN_CONVERT_DATE
                    + Constant.WHEN_START_MONTH_9_THEN_CONVERT_DATE
                    + Constant.WHEN_START_MONTH_10_THEN_CONVERT_DATE
                    + Constant.WHEN_START_MONTH_11_THEN_CONVERT_DATE
                    + Constant.WHEN_START_MONTH_12_THEN_CONVERT_DATE
                    + Constant.SPACE_END_NEW_LINE
                    + Constant.SPACE_AND_NEW_LINE
                    + Constant.SPACE_CASE_NEW_LINE
                    + Constant.WHEN_END_MONTH_1_THEN_CONVERT_DATE
                    + Constant.WHEN_END_MONTH_2_THEN_CONVERT_DATE
                    + Constant.WHEN_END_MONTH_3_THEN_CONVERT_DATE
                    + Constant.WHEN_END_MONTH_4_THEN_CONVERT_DATE
                    + Constant.WHEN_END_MONTH_5_THEN_CONVERT_DATE
                    + Constant.WHEN_END_MONTH_6_THEN_CONVERT_DATE
                    + Constant.WHEN_END_MONTH71_THEN_CONVERT_DATE
                    + Constant.WHEN_END_MONTH_8_THEN_CONVERT_DATE
                    + Constant.WHEN_END_MONTH_9_THEN_CONVERT_DATE
                    + Constant.WHEN_END_MONTH_10_THEN_CONVERT_DATE
                    + Constant.WHEN_END_MONTH_11_THEN_CONVERT_DATE
                    + Constant.WHEN_END_MONTH_12_THEN_CONVERT_DATE
                    + Constant.SPACE_END_NEW_LINE;

            if (projection.equals(Constant.ACTUALS)) {
                sql = "select D.YEAR,D." + frequencyChildDiscount + ",sum(B.ACTUAL_SALES) As ACTUAL_SALES,sum(B.ACTUAL_RATE) As ACTUAL_RATE from dbo.ST_NM_DISCOUNT_PROJ_MASTER A,dbo.ST_NM_ACTUAL_DISCOUNT B,dbo.PERIOD D,RS_MODEL J where A.PROJECTION_DETAILS_SID=B.PROJECTION_DETAILS_SID and J.RS_MODEL_SID=A.RS_MODEL_SID and A.USER_ID=" + userId + Constant.AND_B_USER_ID + userId + Constant.AND_ASESSION_ID + sessionId + Constant.AND_B_SESSION_ID + sessionId + Constant.AND_A_PROJECTION_DETAILS_SID_IN + idString + ") " + periodFilter + " group by D.YEAR,D." + frequencyChildDiscount + Constant.ORDER_BY_D_YEAR;
            }
            if (projection.equals(Constant.PROJECTIONS)) {
                sql = "select J.RS_NAME,D.YEAR,D." + frequencyChildDiscount + ", sum(B.PROJECTION_SALES) As PROJECTION_SALES,sum(B.PROJECTION_RATE) As PROJECTION_RATE from dbo.ST_NM_DISCOUNT_PROJ_MASTER A,dbo.ST_NM_DISCOUNT_PROJECTION B,dbo.PERIOD D,RS_MODEL J where A.PROJECTION_DETAILS_SID=B.PROJECTION_DETAILS_SID and J.RS_MODEL_SID=A.RS_MODEL_SID and A.USER_ID=" + userId + Constant.AND_B_USER_ID + userId + Constant.AND_ASESSION_ID + sessionId + Constant.AND_B_SESSION_ID + sessionId + Constant.AND_A_PROJECTION_DETAILS_SID_IN + idString + ")" + periodFilter + " group by J.RS_NAME,D.YEAR,D." + frequencyChildDiscount + Constant.ORDER_BY_D_YEAR;
            }
            if (projection.equals("Both")) {
                sql = "select J.RS_NAME,D.YEAR,D." + frequencyChildDiscount + ", sum(B.PROJECTION_SALES) As PROJECTION_SALES,sum(B.PROJECTION_RATE) As PROJECTION_RATE,sum(C.ACTUAL_SALES) As ACTUAL_SALES,sum(C.ACTUAL_RATE) As ACTUAL_RATE from dbo.ST_NM_DISCOUNT_PROJ_MASTER A,dbo.ST_NM_DISCOUNT_PROJECTION B,dbo.ST_NM_ACTUAL_DISCOUNT C,dbo.PERIOD D,RS_MODEL J where A.PROJECTION_DETAILS_SID=B.PROJECTION_DETAILS_SID and J.RS_MODEL_SID=A.RS_MODEL_SID and A.PROJECTION_DETAILS_SID=C.PROJECTION_DETAILS_SID and A.USER_ID=" + userId + Constant.AND_B_USER_ID + userId + " and C.USER_ID=" + userId + Constant.AND_ASESSION_ID + sessionId + Constant.AND_B_SESSION_ID + sessionId + " and C.SESSION_ID=" + sessionId + Constant.AND_A_PROJECTION_DETAILS_SID_IN + idString + ") " + periodFilter + " group by J.RS_NAME,D.YEAR,D." + frequencyChildDiscount + Constant.ORDER_BY_D_YEAR;
            }
            sql = declareStatement + sql;
            return HelperTableLocalServiceUtil.executeSelectQuery(sql);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
        }
        return Collections.emptyList();
    }

    public List getTotalDiscountNumber(List<Integer> projectionDetailsId, String frequency, String discountName, List<Integer> startAndEndPeriods, int userId, int sessionId, List<Object> view) {

        String projectionQuery = "";
        String frequencyDiscountNumber = frequency;
        try {
            String idString;
            StringBuilder idStringBuilder = new StringBuilder();
            for (int i = 0; i < projectionDetailsId.size(); i++) {
                if (i != projectionDetailsId.size() - 1) {
                    idStringBuilder.append(projectionDetailsId.get(i) ).append( ',');
                } else {
                    idStringBuilder.append(projectionDetailsId.get(i));
                }
            }
            idString = idStringBuilder.toString();
            String startPeriod = "";
            String endPeriod = "";
            String forecastStartPeriod = "";
            String forecastEndPeriod = "";
            if (startAndEndPeriods != null && !startAndEndPeriods.isEmpty()) {
                String hsYear = String.valueOf(startAndEndPeriods.get(0));
                String hsMonth = String.valueOf(startAndEndPeriods.get(1));
                String heYear = String.valueOf(startAndEndPeriods.get(2));
                String heMonth = String.valueOf(startAndEndPeriods.get(3));

                String fsYear = String.valueOf(startAndEndPeriods.get(4));
                String fsMonth = String.valueOf(startAndEndPeriods.get(5));
                String feYear = String.valueOf(startAndEndPeriods.get(6));
                String feMonth = String.valueOf(startAndEndPeriods.get(7));

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
            if (frequencyDiscountNumber.equals(QUARTERLY.getConstant())) {
                frequencyDiscountNumber = Constant.QUARTER;
            }
            if (frequencyDiscountNumber.equals(Constant.ANNUALLY)) {
                frequencyDiscountNumber = "YEAR";
            }
            if (frequencyDiscountNumber.equals(Constant.SEMIANNUALLY)) {
                frequencyDiscountNumber = Constant.SEMI_ANNUAL;

            }
            if (frequencyDiscountNumber.equals(MONTHLY.getConstant())) {
                frequencyDiscountNumber = Constant.MONTH_WITHOUT_SPACE;
            }
            projectionQuery = Constant.SELECT_PR_YEAR_PR + frequencyDiscountNumber + Constant.AS_BASE_000_AS_ACTUAL_SALES_000_AS_ACTUAL
                    + Constant.AS_PROJECTION_SALES_SUM_NM_DP_PROJECTION_SAL + frequencyDiscountNumber + Constant.PR_MONTH_RS_M_RS_NAME
                    + Constant.SUM_NM_SP_PROJECTION_UNITS_AS_PROJECTION_UN
                    + Constant.FROM_PROJECTION_DETAILS_PD_JOIN_ST_NM
                    + Constant.JOIN_ST_NM_DISCOUNT_PROJECTION_NMDP
                    + Constant.AND_NM_DP_USER_ID_NM_DP_M_USER_ID
                    + Constant.JOIN_PERIOD_PR_ON_PR_PERIOD_SID
                    + Constant.JOIN_ST_NM_SALES_PROJECTION_MASTER_NM_SP
                    + Constant.AND_NM_SP_M_USER_ID_NM_SP_USER_ID_AND_NM_SP
                    + Constant.AND_NM_SP_M_USER_ID_NM_DP_M_USER_ID_AND_NM_SPM
                    + " AND PD.PROJECTION_DETAILS_SID = NMSPM.PROJECTION_DETAILS_SID JOIN RS_MODEL RSM ON RSM.RS_MODEL_SID = NMDPM.RS_MODEL_SID "
                    + Constant.AND_RS_M_RS_MODEL_SID_NM_DP_RS_MODEL_SID_WH + idString + ") "
                    + Constant.AND_NM_DP_MUSER_ID + userId + Constant.AND_NM_DP_M_SESSION_ID + sessionId + " "
                    + Constant.AND_CAST_PR_YEAR_AS_VARCHAR_RIGHT_CAST_PR + forecastStartPeriod + ""
                    + Constant.AND_CAST_PRYEAR_AS_VARCHAR_RIGHT_CAST + forecastEndPeriod + ""
                    + Constant.AND_RS_M_RS_NAME_IN + discountName + ")"
                    + " GROUP BY PR.YEAR,PR." + frequencyDiscountNumber + ",PR.MONTH,PD.PROJECTION_DETAILS_SID,RSM.RS_NAME"
                    + Constant.UNION_ALL
                    + Constant.SELECT_PR_YEAR_PR + frequencyDiscountNumber + Constant.AS_BASE_MAX_NM_AS_ACTUAL_SALES
                    + Constant.AS_ACTUAL_DISCOUNT_MAX_IS_NULL_NM_SP
                    + Constant.AS_PROJECTION_DISCOUNT_PR + frequencyDiscountNumber + ",PR.MONTH, RSM.RS_NAME,SUM(NMDP.PROJECTION_RATE) AS PROJECTION_RATE,"
                    + " Sum(ACTUAL_UNITS) as ACTUAL_UNITS,"
                    + " Sum(NMSP.PROJECTION_UNITS) as PROJECTION_UNITS"
                    + " FROM PROJECTION_DETAILS PD\n"
                    + Constant.JOIN_ST_NM_DISCOUNT_PROJ_MASTER_NM_ADM_ON
                    + Constant.JOIN_ST_NM_ACTUAL_DISCOUNT_NM_AD_ON_NM_ADP
                    + Constant.AND_NM_AD_USER_ID_NM_ADM_USER_ID
                    + Constant.JOIN_RS_MODEL_RSM_ON_RS_M_RS_MODEL_SID_NM
                    + Constant.JOIN_PERIOD_PR_ON_PR_PERIOD_SID_NM_AD
                    + Constant.AND_NM_AS_USER_ID_NM_AD_USER_ID
                    + Constant.AND_NM_AS_PROJECTION_DETAILS_SID_NM_ADM_PRO
                    + Constant.ON_NM_SP_M_PROJECTION_DETAILS_SID_NM_AS_PROJ
                    + Constant.AND_NM_SPM_SESSION_ID_NM_AS_SESSION_ID
                    + Constant.ON_NM_SP_PROJECTION_DETAILS_SID
                    + Constant.AND_NM_SP_USER_ID_NM_SPM_USER_ID_AND_NM_SP
                    + Constant.ON_NM_DP_PROJECTION_DETAILS_SID_NM
                    + Constant.AND_NM_DP_RS_MODEL_SID_RS_RS_MODEL_SID
                    + Constant.WHERE_PD_PROJECTION_DETAILS_SID_IN + idString + Constant.AND_NM_AD_MUSER_ID + userId + ""
                    + Constant.AND_NM_ADM_SESSION_ID + sessionId + Constant.AND_CAST_PR_YEAR_AS_VARCHAR_RIGHT + startPeriod + ""
                    + Constant.AND_CAST_PR_YEAR_AS_VARCHAR_RIGHT_CAST_PRMO + endPeriod + ""
                    + Constant.AND_RS_M_RS_NAME_IN + discountName + ")"
                    + Constant.GROUP_BY_PR_YEAR + frequencyDiscountNumber + Constant.PR_MONTH_PD_PROJECTION_DETAILS_SID_RS;

            if (frequencyDiscountNumber.equals("YEAR") || frequencyDiscountNumber.equals(Constant.MONTH_WITHOUT_SPACE)) {
                if (view.get(0) != null && "Descending".equalsIgnoreCase(String.valueOf(view.get(0)))) {
                    projectionQuery = projectionQuery + "ORDER BY RSM.RS_NAME,PR.YEAR DESC,PR.MONTH DESC";
                } else {
                    projectionQuery = projectionQuery + "ORDER BY RSM.RS_NAME,PR.YEAR,PR.MONTH";
                }
            } else if (view.get(0) != null && "Descending".equalsIgnoreCase(String.valueOf(view.get(0)))) {
                projectionQuery = projectionQuery + "ORDER BY RSM.RS_NAME,PR.YEAR DESC,PR." + frequencyDiscountNumber + " DESC,PR.MONTH DESC";
            } else {
                projectionQuery = projectionQuery + "ORDER BY RSM.RS_NAME,PR.YEAR,PR." + frequencyDiscountNumber + Constant.PR_MONTH;
            }

            return HelperTableLocalServiceUtil.executeSelectQuery(projectionQuery);
        } catch (Exception e) {

            LOGGER.error(e.getMessage());
            LOGGER.error(projectionQuery);
        }
        return Collections.emptyList();
    }

    public List getSubDiscount(List<Integer> projectionDetailsId, String frequency, String discountList, List<Integer> startAndEndPeriods, int userId, int sessionId) {
            String sql = "";
            String frequencySubDiscount = frequency;
            try {
                String idString ;
                StringBuilder idStringBuilder = new StringBuilder();
                for (int i = 0; i < projectionDetailsId.size(); i++) {
                    if (i != projectionDetailsId.size() - 1) {
                        idStringBuilder.append(projectionDetailsId.get(i) ).append( ',');
                    } else {
                        idStringBuilder.append(projectionDetailsId.get(i));
                    }
                }
                idString = idStringBuilder.toString();
                String startPeriod = "";
                String endPeriod = "";
                String forecastStartPeriod = "";
                String forecastEndPeriod = "";
                if (startAndEndPeriods != null && !startAndEndPeriods.isEmpty()) {
                    String hsYear = String.valueOf(startAndEndPeriods.get(0));
                    String hsMonth = String.valueOf(startAndEndPeriods.get(1));
                    String heYear = String.valueOf(startAndEndPeriods.get(2));
                    String heMonth = String.valueOf(startAndEndPeriods.get(3));

                    String fsYear = String.valueOf(startAndEndPeriods.get(4));
                    String fsMonth = String.valueOf(startAndEndPeriods.get(5));
                    String feYear = String.valueOf(startAndEndPeriods.get(6));
                    String feMonth = String.valueOf(startAndEndPeriods.get(7));

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

                if (frequencySubDiscount.equals(QUARTERLY.getConstant())) {
                    frequencySubDiscount = Constant.QUARTER;
                }
                if (frequencySubDiscount.equals(Constant.ANNUALLY)) {
                    frequencySubDiscount = "YEAR";
                }
                if (frequencySubDiscount.equals(Constant.SEMIANNUALLY)) {
                    frequencySubDiscount = Constant.SEMI_ANNUAL;
                }
                if (frequencySubDiscount.equals(MONTHLY.getConstant())) {
                    frequencySubDiscount = Constant.MONTH_WITHOUT_SPACE;
                }
                sql = Constant.SELECT_PR_YEAR_PR + frequencySubDiscount + Constant.AS_BASE_000_AS_ACTUAL_SALES_000_AS_ACTUAL
                        + Constant.AS_PROJECTION_SALES_SUM_NM_DP_PROJECTION_SAL + frequencySubDiscount + Constant.PR_MONTH_RS_M_RS_NAME
                        + Constant.SUM_NM_SP_PROJECTION_UNITS_AS_PROJECTION_UN
                        + Constant.FROM_PROJECTION_DETAILS_PD_JOIN_ST_NM
                        + Constant.JOIN_ST_NM_DISCOUNT_PROJECTION_NMDP
                        + Constant.AND_NM_DP_USER_ID_NM_DP_M_USER_ID
                        + Constant.JOIN_PERIOD_PR_ON_PR_PERIOD_SID
                        + Constant.JOIN_ST_NM_SALES_PROJECTION_MASTER_NM_SP
                        + Constant.AND_NM_SP_M_USER_ID_NM_SP_USER_ID_AND_NM_SP
                        + Constant.AND_NM_SP_M_USER_ID_NM_DP_M_USER_ID_AND_NM_SPM
                        + " AND PD.PROJECTION_DETAILS_SID = NMSPM.PROJECTION_DETAILS_SID JOIN RS_MODEL RSM ON RSM.RS_MODEL_SID = NMDPM.RS_MODEL_SID"
                        + Constant.AND_RS_M_RS_MODEL_SID_NM_DP_RS_MODEL_SID_WH + idString + ") "
                        + Constant.AND_NM_DP_MUSER_ID + userId + Constant.AND_NM_DP_M_SESSION_ID + sessionId + " "
                        + Constant.AND_CAST_PR_YEAR_AS_VARCHAR_RIGHT_CAST_PR + forecastStartPeriod + ""
                        + Constant.AND_CAST_PRYEAR_AS_VARCHAR_RIGHT_CAST + forecastEndPeriod + ""
                        + Constant.AND_RS_M_RS_NAME_IN + discountList + ") "
                        + Constant.GROUP_BY_PR_YEAR + frequencySubDiscount + ",PR.MONTH,PD.PROJECTION_DETAILS_SID,RSM.RS_NAME "
                        + Constant.UNION_ALL
                        + Constant.SELECT_PR_YEAR_PR + frequencySubDiscount + Constant.AS_BASE_MAX_NM_AS_ACTUAL_SALES
                        + Constant.AS_ACTUAL_DISCOUNT_MAX_IS_NULL_NM_SP
                        + Constant.AS_PROJECTION_DISCOUNT_PR + frequencySubDiscount + ",PR.MONTH, RSM.RS_NAME, SUM(NMDP.PROJECTION_RATE) AS PROJECTION_RATE, Sum(ACTUAL_UNITS) as ACTUAL_UNITS,\n"
                        + " Sum(NMSP.PROJECTION_UNITS) as PROJECTION_UNITS "
                        + "FROM PROJECTION_DETAILS PD\n"
                        + Constant.JOIN_ST_NM_DISCOUNT_PROJ_MASTER_NM_ADM_ON
                        + Constant.JOIN_ST_NM_ACTUAL_DISCOUNT_NM_AD_ON_NM_ADP
                        + Constant.AND_NM_AD_USER_ID_NM_ADM_USER_ID
                        + Constant.JOIN_RS_MODEL_RSM_ON_RS_M_RS_MODEL_SID_NM
                        + Constant.JOIN_PERIOD_PR_ON_PR_PERIOD_SID_NM_AD
                        + Constant.AND_NM_AS_USER_ID_NM_AD_USER_ID
                        + Constant.AND_NM_AS_PROJECTION_DETAILS_SID_NM_ADM_PRO
                        + Constant.ON_NM_SP_M_PROJECTION_DETAILS_SID_NM_AS_PROJ
                        + Constant.AND_NM_SPM_SESSION_ID_NM_AS_SESSION_ID
                        + Constant.ON_NM_SP_PROJECTION_DETAILS_SID
                        + Constant.AND_NM_SP_USER_ID_NM_SPM_USER_ID_AND_NM_SP
                        + Constant.ON_NM_DP_PROJECTION_DETAILS_SID_NM
                        + Constant.AND_NM_DP_RS_MODEL_SID_RS_RS_MODEL_SID
                        + Constant.WHERE_PD_PROJECTION_DETAILS_SID_IN + idString + Constant.AND_NM_AD_MUSER_ID + userId + ""
                        + Constant.AND_NM_ADM_SESSION_ID + sessionId + Constant.AND_CAST_PR_YEAR_AS_VARCHAR_RIGHT + startPeriod + ""
                        + Constant.AND_CAST_PR_YEAR_AS_VARCHAR_RIGHT_CAST_PRMO + endPeriod + ""
                        + Constant.AND_RS_M_RS_NAME_IN + discountList + ")"
                        + Constant.GROUP_BY_PR_YEAR + frequencySubDiscount + Constant.PR_MONTH_PD_PROJECTION_DETAILS_SID_RS;

                sql = sql + " ORDER BY PR.YEAR,BASE,RSM.RS_NAME";

                return HelperTableLocalServiceUtil.executeSelectQuery(sql);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                LOGGER.error(sql);
            }
        return Collections.emptyList();
    }

    public List getTotalDiscountCount(int projectionMasterId, String frequency, String actualsOrProjections, List<Integer> startAndEndPeriods, int userId, int sessionId) {
            String sql = "";
            String frequencyTotalDiscount = frequency;
            try {

                int startFreq = 0;
                int endFreq = 0;
                int startYear = 0;
                int endYear = 0;
                String declareStatement = "";
                int startMonth = 0;
                int endMonth = 0;
                if (startAndEndPeriods != null && !startAndEndPeriods.isEmpty()) {
                    startFreq = startAndEndPeriods.get(0);
                    endFreq = startAndEndPeriods.get(1);
                    startYear = startAndEndPeriods.get(2);
                    endYear = startAndEndPeriods.get(3);
                }

                if (frequencyTotalDiscount.equals(QUARTERLY.getConstant())) {
                    frequencyTotalDiscount = Constant.QUARTER;
                    switch (startFreq) {
                        case 1:
                            startMonth = 1;
                            break;
                        case 2:
                            startMonth = 4;
                            break;
                        case 3:
                            startMonth = 7;
                            break;
                        case 4:
                            startMonth = 10;
                            break;
                        default:
                            break;

                    }
                    switch (endFreq) {
                        case 1:
                            endMonth = 3;
                            break;
                        case 2:
                            endMonth = 6;
                            break;
                        case 3:
                            endMonth = 9;
                            break;
                        case 4:
                            endMonth = 12;
                            break;
                        default:
                            break;

                    }
                }
                if (frequencyTotalDiscount.equals(Constant.ANNUALLY)) {
                    frequencyTotalDiscount = "YEAR";
                    startMonth = 1;
                    endMonth = 12;
                }
                if (frequencyTotalDiscount.equals(Constant.SEMIANNUALLY)) {
                    frequencyTotalDiscount = Constant.SEMI_ANNUAL;
                    switch (startFreq) {
                        case 1:
                            startMonth = 1;
                            break;
                        case 2:
                            startMonth = 7;
                            break;
                        default:
                            break;

                    }
                    switch (endFreq) {
                        case 1:
                            endMonth = 6;
                            break;
                        case 2:
                            endMonth = 12;
                            break;
                        default:
                            break;

                    }

                }
                if (frequencyTotalDiscount.equals(MONTHLY.getConstant())) {
                    startMonth = startFreq;
                    endMonth = endFreq;

                }
                declareStatement = Constant.DECLARE_START_MONTH_INT + startMonth + "\n"
                        + Constant.DECLARE_START_YEAR_INT + startYear + "\n"
                        + Constant.DECLARE_END_MONTH_INT + endMonth + "\n"
                        + Constant.DECLARE_END_YEAR_INT + endYear + "\n";
                String periodFilter = "";
                periodFilter = "AND I.PERIOD_DATE BETWEEN \n "
                        + Constant.SPACE_CASE_NEW_LINE
                        + Constant.WHEN_START_MONTH_1_THEN_CONVERT_DATE
                        + Constant.WHEN_START_MONTH_2_THEN_CONVERT_DATE
                        + Constant.WHEN_START_MONTH_3_THEN_CONVERT_DATE
                        + Constant.WHEN_START_MONTH_4_THEN_CONVERT_DATE
                        + Constant.WHEN_START_MONTH_5_THEN_CONVERT_DATE
                        + Constant.WHEN_START_MONTH_6_THEN_CONVERT_DATE
                        + Constant.WHEN_START_MONTH_7_THEN_CONVERT_DATE
                        + Constant.WHEN_START_MONTH_8_THEN_CONVERT_DATE
                        + Constant.WHEN_START_MONTH_9_THEN_CONVERT_DATE
                        + Constant.WHEN_START_MONTH_10_THEN_CONVERT_DATE
                        + Constant.WHEN_START_MONTH_11_THEN_CONVERT_DATE
                        + Constant.WHEN_START_MONTH_12_THEN_CONVERT_DATE
                        + Constant.SPACE_END_NEW_LINE
                        + Constant.SPACE_AND_NEW_LINE
                        + Constant.SPACE_CASE_NEW_LINE
                        + Constant.WHEN_END_MONTH_1_THEN_CONVERT_DATE
                        + Constant.WHEN_END_MONTH_2_THEN_CONVERT_DATE
                        + Constant.WHEN_END_MONTH_3_THEN_CONVERT_DATE
                        + Constant.WHEN_END_MONTH_4_THEN_CONVERT_DATE
                        + Constant.WHEN_END_MONTH_5_THEN_CONVERT_DATE
                        + Constant.WHEN_END_MONTH_6_THEN_CONVERT_DATE
                        + Constant.WHEN_END_MONTH71_THEN_CONVERT_DATE
                        + Constant.WHEN_END_MONTH_8_THEN_CONVERT_DATE
                        + Constant.WHEN_END_MONTH_9_THEN_CONVERT_DATE
                        + Constant.WHEN_END_MONTH_10_THEN_CONVERT_DATE
                        + Constant.WHEN_END_MONTH_11_THEN_CONVERT_DATE
                        + Constant.WHEN_END_MONTH_12_THEN_CONVERT_DATE
                        + Constant.SPACE_END_NEW_LINE;

                if (actualsOrProjections.equals(Constant.ACTUALS)) {
                    sql = "Select J.RS_NAME from ST_NM_DISCOUNT_PROJ_MASTER A,PERIOD I,RS_MODEL J WHERE  A.USER_ID=" + userId + " and  A.SESSION_ID=" + sessionId + " and J.RS_MODEL_SID=A.RS_MODEL_SID and A.PROJECTION_DETAILS_SID in (select PROJECTION_DETAILS_SID from dbo.PROJECTION_DETAILS where PROJECTION_MASTER_SID='" + projectionMasterId + "') " + periodFilter + "group by J.RS_NAME";
                }
                if (actualsOrProjections.equals(Constant.PROJECTIONS)) {
                    sql = "Select J.RS_NAME from ST_NM_DISCOUNT_PROJ_MASTER A,PERIOD I,RS_MODEL J WHERE J.RS_MODEL_SID=A.RS_MODEL_SID and A.USER_ID=" + userId + Constant.AND_ASESSION_ID + sessionId + " and A.PROJECTION_DETAILS_SID in (select PROJECTION_DETAILS_SID from dbo.PROJECTION_DETAILS where PROJECTION_MASTER_SID='" + projectionMasterId + "')  " + periodFilter + " group by J.RS_NAME";
                }
                if (actualsOrProjections.equals("Both")) {
                    sql = "Select J.RS_NAME from ST_NM_DISCOUNT_PROJ_MASTER A,PERIOD I,RS_MODEL J WHERE J.RS_MODEL_SID=A.RS_MODEL_SID and A.USER_ID=" + userId + Constant.AND_ASESSION_ID + sessionId + " and A.PROJECTION_DETAILS_SID in (select PROJECTION_DETAILS_SID from dbo.PROJECTION_DETAILS where PROJECTION_MASTER_SID='" + projectionMasterId + "') " + periodFilter + " group by J.RS_NAME";
                }
                sql = declareStatement + sql;

                return HelperTableLocalServiceUtil.executeSelectQuery(sql);
            } catch (Exception e) {

                LOGGER.error(e.getMessage());
                LOGGER.error(sql);
            }
        return Collections.emptyList();
    }

    public List getCCPDetailsID(int projectionMasterSid, String hierarchyNo, String levelNo) {

        String sql = StringUtils.EMPTY;
        try {

            sql = "SELECT  LCCP.CCP_DETAILS_SID FROM   (SELECT CCPMAP.CCP_DETAILS_SID,HLD.HIERARCHY_NO,HLD.RELATIONSHIP_LEVEL_SID FROM "
                    + "  (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO,CCP.CCP_DETAILS_SID FROM   RELATIONSHIP_LEVEL_DEFINITION RLD"
                    + " JOIN   CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID = CCP.RELATIONSHIP_LEVEL_SID "
                    + "JOIN   PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID "
                    + "AND PD.PROJECTION_MASTER_SID =" + projectionMasterSid
                    + "JOIN   PROJECTION_MASTER PM ON PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID"
                    + " WHERE  PM.PROJECTION_MASTER_SID ='" + projectionMasterSid + "') CCPMAP,"
                    + "(SELECT RLD1.HIERARCHY_NO,RLD1.RELATIONSHIP_LEVEL_SID FROM   RELATIONSHIP_LEVEL_DEFINITION RLD1 "
                    + "JOIN   PROJECTION_CUST_HIERARCHY PCH ON PCH.RELATIONSHIP_LEVEL_SID = RLD1.RELATIONSHIP_LEVEL_SID "
                    + "AND PCH.PROJECTION_MASTER_SID =" + projectionMasterSid + " WHERE  RLD1.HIERARCHY_NO LIKE '" + hierarchyNo + "') HLD "
                    + "WHERE  CCPMAP.HIERARCHY_NO LIKE HLD.HIERARCHY_NO+'%') LCCP WHERE "
                    + "LCCP.HIERARCHY_NO IN (SELECT RLD2.HIERARCHY_NO FROM   RELATIONSHIP_LEVEL_DEFINITION RLD2 "
                    + "JOIN   PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID = RLD2.RELATIONSHIP_LEVEL_SID "
                    + "AND PCH2.PROJECTION_MASTER_SID =" + projectionMasterSid + " WHERE  RLD2.LEVEL_NO ='" + levelNo + "')";
            return HelperTableLocalServiceUtil.executeSelectQuery(sql);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
        }
        return Collections.emptyList();
    }

    public List getCCPDetailsIDForProductHierarchy(int projectionMasterSid, String hierarchyNo, String levelNo) {

        String sql = StringUtils.EMPTY;
        try {

            sql = "SELECT  LCCP.CCP_DETAILS_SID FROM   (SELECT CCPMAP.CCP_DETAILS_SID,HLD.HIERARCHY_NO,HLD.RELATIONSHIP_LEVEL_SID FROM "
                    + "  (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO,CCP.CCP_DETAILS_SID FROM   RELATIONSHIP_LEVEL_DEFINITION RLD"
                    + " JOIN   CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID = CCP.RELATIONSHIP_LEVEL_SID "
                    + "JOIN   PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID "
                    + "AND PD.PROJECTION_MASTER_SID =" + projectionMasterSid
                    + " JOIN   PROJECTION_MASTER PM ON PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID"
                    + " WHERE  PM.PROJECTION_MASTER_SID ='" + projectionMasterSid + "') CCPMAP,"
                    + "(SELECT RLD1.HIERARCHY_NO,RLD1.RELATIONSHIP_LEVEL_SID FROM   RELATIONSHIP_LEVEL_DEFINITION RLD1 "
                    + "JOIN   PROJECTION_PROD_HIERARCHY PCH ON PCH.RELATIONSHIP_LEVEL_SID = RLD1.RELATIONSHIP_LEVEL_SID "
                    + "AND PCH.PROJECTION_MASTER_SID =" + projectionMasterSid + " WHERE  RLD1.HIERARCHY_NO LIKE '" + hierarchyNo + "') HLD "
                    + "WHERE  CCPMAP.HIERARCHY_NO LIKE HLD.HIERARCHY_NO+'%') LCCP WHERE "
                    + "LCCP.HIERARCHY_NO IN (SELECT RLD2.HIERARCHY_NO FROM   RELATIONSHIP_LEVEL_DEFINITION RLD2 "
                    + "JOIN   PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID = RLD2.RELATIONSHIP_LEVEL_SID "
                    + "AND PCH2.PROJECTION_MASTER_SID =" + projectionMasterSid + " WHERE  RLD2.LEVEL_NO ='" + levelNo + "')";

            return HelperTableLocalServiceUtil.executeSelectQuery(sql);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
        }
        return Collections.emptyList();
    }

    public List getAllPesriodDiscount(List<Integer> discountprojectionId, String frequency, String discountName, List<Integer> startAndEndPeriods, int userId, int sessionId) {

        String sql = "";
        String frequencyAllPeriodDiscount = frequency;
        try {

            String idString;
            StringBuilder idStringBuilder = new StringBuilder();
            for (int i = 0; i < discountprojectionId.size(); i++) {
                if (i != discountprojectionId.size() - 1) {
                    idStringBuilder.append(discountprojectionId.get(i) ).append( ',');
                } else {
                    idStringBuilder.append(discountprojectionId.get(i));
                }
            }
            idString = idStringBuilder.toString();
            String startPeriod = "";
            String endPeriod = "";
            String forecastStartPeriod = "";
            String forecastEndPeriod = "";
            if (startAndEndPeriods != null && !startAndEndPeriods.isEmpty()) {
                String hsYear = String.valueOf(startAndEndPeriods.get(0));
                String hsMonth = String.valueOf(startAndEndPeriods.get(1));
                String heMonth = String.valueOf(startAndEndPeriods.get(3));
                String feYear = String.valueOf(startAndEndPeriods.get(6));
                String feMonth = String.valueOf(startAndEndPeriods.get(7));

                if (hsMonth.length() == 1) {
                    hsMonth = "0" + hsMonth;
                }
                startPeriod = hsYear + hsMonth;
                if (heMonth.length() == 1) {
                    heMonth = "0" + heMonth;
                }

                Calendar calendar = Calendar.getInstance();
                int month = calendar.get(Calendar.MONTH) + 1;
                calendar.add(Calendar.MONTH, -((month % 3) == 0 ? 3 : (month % 3)));
                heMonth = String.valueOf((calendar.get(Calendar.MONTH) + 1));
                String heYear = String.valueOf(calendar.get(Calendar.YEAR));
                if (heMonth.length() == 1) {
                    heMonth = "0" + heMonth;
                }
                endPeriod = heYear + heMonth;

                calendar = Calendar.getInstance();
                month = calendar.get(Calendar.MONTH) + 1;
                calendar.add(Calendar.MONTH, -((month % 3) == 0 ? 3 : (month % 3)));
                String fsMonth = String.valueOf((calendar.get(Calendar.MONTH) + 2));
                String fsYear = String.valueOf(calendar.get(Calendar.YEAR));
                if (fsMonth.length() == 1) {
                    fsMonth = "0" + fsMonth;
                }
                forecastStartPeriod = fsYear + fsMonth;
                if (feMonth.length() == 1) {
                    feMonth = "0" + feMonth;
                }
                forecastEndPeriod = feYear + feMonth;

            }
            if (frequencyAllPeriodDiscount.equals(QUARTERLY.getConstant())) {
                frequencyAllPeriodDiscount = Constant.QUARTER;
            }
            if (frequencyAllPeriodDiscount.equals(Constant.ANNUALLY)) {
                frequencyAllPeriodDiscount = "YEAR";
            }
            if (frequencyAllPeriodDiscount.equals(Constant.SEMIANNUALLY)) {
                frequencyAllPeriodDiscount = Constant.SEMI_ANNUAL;
            }
            if (frequencyAllPeriodDiscount.equals(MONTHLY.getConstant())) {
                frequencyAllPeriodDiscount = Constant.MONTH_WITHOUT_SPACE;
            }
            sql = Constant.SELECT_PR_YEAR_PR + frequencyAllPeriodDiscount + Constant.AS_BASE_000_AS_ACTUAL_SALES_000_AS_ACTUAL
                    + Constant.AS_PROJECTION_SALES_SUM_NM_DP_PROJECTION_SAL + frequencyAllPeriodDiscount + Constant.PR_MONTH_RS_M_RS_NAME
                    + Constant.SUM_NM_SP_PROJECTION_UNITS_AS_PROJECTION_UN
                    + Constant.FROM_PROJECTION_DETAILS_PD_JOIN_ST_NM
                    + Constant.JOIN_ST_NM_DISCOUNT_PROJECTION_NMDP
                    + Constant.AND_NM_DP_USER_ID_NM_DP_M_USER_ID
                    + Constant.JOIN_PERIOD_PR_ON_PR_PERIOD_SID
                    + Constant.JOIN_ST_NM_SALES_PROJECTION_MASTER_NM_SP
                    + Constant.AND_NM_SP_M_USER_ID_NM_SP_USER_ID_AND_NM_SP
                    + Constant.AND_NM_SP_M_USER_ID_NM_DP_M_USER_ID_AND_NM_SPM
                    + " AND PD.PROJECTION_DETAILS_SID = NMSPM.PROJECTION_DETAILS_SID JOIN RS_MODEL RSM ON RSM.RS_MODEL_SID = NMDPM.RS_MODEL_SID"
                    + Constant.AND_RS_M_RS_MODEL_SID_NM_DP_RS_MODEL_SID_WH + idString + ") "
                    + Constant.AND_NM_DP_MUSER_ID + userId + Constant.AND_NM_DP_M_SESSION_ID + sessionId + " "
                    + Constant.AND_CAST_PR_YEAR_AS_VARCHAR_RIGHT_CAST_PR + forecastStartPeriod + ""
                    + Constant.AND_CAST_PRYEAR_AS_VARCHAR_RIGHT_CAST + forecastEndPeriod + ""
                    + Constant.AND_RS_M_RS_NAME_IN + discountName + ")"
                    + Constant.GROUP_BY_PR_YEAR + frequencyAllPeriodDiscount + ",PR.MONTH,PD.PROJECTION_DETAILS_SID,RSM.RS_NAME "
                    + Constant.UNION_ALL
                    + Constant.SELECT_PR_YEAR_PR + frequencyAllPeriodDiscount + Constant.AS_BASE_MAX_NM_AS_ACTUAL_SALES
                    + Constant.AS_ACTUAL_DISCOUNT_MAX_IS_NULL_NM_SP
                    + Constant.AS_PROJECTION_DISCOUNT_PR + frequencyAllPeriodDiscount + ",PR.MONTH, RSM.RS_NAME, SUM(NMDP.PROJECTION_RATE) AS PROJECTION_RATE, Sum(ACTUAL_UNITS) as ACTUAL_UNITS,\n"
                    + " Sum(NMSP.PROJECTION_UNITS) as PROJECTION_UNITS  FROM PROJECTION_DETAILS PD \n"
                    + Constant.JOIN_ST_NM_DISCOUNT_PROJ_MASTER_NM_ADM_ON
                    + Constant.JOIN_ST_NM_ACTUAL_DISCOUNT_NM_AD_ON_NM_ADP
                    + Constant.AND_NM_AD_USER_ID_NM_ADM_USER_ID
                    + Constant.JOIN_RS_MODEL_RSM_ON_RS_M_RS_MODEL_SID_NM
                    + Constant.JOIN_PERIOD_PR_ON_PR_PERIOD_SID_NM_AD
                    + Constant.AND_NM_AS_USER_ID_NM_AD_USER_ID
                    + Constant.AND_NM_AS_PROJECTION_DETAILS_SID_NM_ADM_PRO
                    + Constant.ON_NM_SP_M_PROJECTION_DETAILS_SID_NM_AS_PROJ
                    + Constant.AND_NM_SPM_SESSION_ID_NM_AS_SESSION_ID
                    + Constant.ON_NM_SP_PROJECTION_DETAILS_SID
                    + Constant.AND_NM_SP_USER_ID_NM_SPM_USER_ID_AND_NM_SP
                    + Constant.ON_NM_DP_PROJECTION_DETAILS_SID_NM
                    + Constant.AND_NM_DP_RS_MODEL_SID_RS_RS_MODEL_SID
                    + Constant.WHERE_PD_PROJECTION_DETAILS_SID_IN + idString + Constant.AND_NM_AD_MUSER_ID + userId + ""
                    + Constant.AND_NM_ADM_SESSION_ID + sessionId + Constant.AND_CAST_PR_YEAR_AS_VARCHAR_RIGHT + startPeriod + ""
                    + Constant.AND_CAST_PR_YEAR_AS_VARCHAR_RIGHT_CAST_PRMO + endPeriod + ""
                    + Constant.AND_RS_M_RS_NAME_IN + discountName + ")"
                    + Constant.GROUP_BY_PR_YEAR + frequencyAllPeriodDiscount + Constant.PR_MONTH_PD_PROJECTION_DETAILS_SID_RS;

            if (frequencyAllPeriodDiscount.equals("YEAR") || frequencyAllPeriodDiscount.equals(Constant.MONTH_WITHOUT_SPACE)) {
                sql = sql + " ORDER BY RSM.RS_NAME,PR.YEAR,PR.MONTH";
            } else {
                sql = sql + " ORDER BY RSM.RS_NAME,PR.YEAR,PR." + frequencyAllPeriodDiscount + Constant.PR_MONTH;
            }

            return HelperTableLocalServiceUtil.executeSelectQuery(sql);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
        }
        return Collections.emptyList();
    }
}
