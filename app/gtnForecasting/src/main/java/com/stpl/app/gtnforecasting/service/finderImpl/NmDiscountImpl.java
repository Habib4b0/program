/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.service.finderImpl;

import com.liferay.portal.kernel.dao.orm.Session;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import static com.stpl.app.serviceUtils.Constants.FrequencyConstants.*;
import com.stpl.app.utils.Constants;
import com.stpl.ifs.util.constants.BooleanConstant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
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
                        //selectedDiscounts += comma;
                        selectedDiscountsBuilder.append(comma);
                    }
                    //selectedDiscounts += "'" + priceGroupType.get(i) + "'";
                    selectedDiscountsBuilder.append("'" ).append( priceGroupType.get(i) ).append( "'");
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
            return null;
        } finally {

        }
    }

    /**
     * To get the discount Projection Result
     *
     * @param projectionId
     * @param userId
     * @param sessionId
     * @param frequency
     * @param startAndEndPeriods - Used to get start year, start month, end year
     * and end month
     * @param hierarchyNo - Not used in custom hierarchy
     * @param isProgram
     * @param discountList - list of selected discounts
     * @param year
     * @param historyNumber
     * @param levelNo
     * @param hierarchyIndicator
     * @param userGroup
     * @param startIndex
     * @param endIndex
     * @param isCount
     * @param isCustom
     * @param customViewDetails
     * @param isRefresh
     * @param refreshHierarchyNumbers
     * @param relationshipBuilderSid
     * @return
     */
    public List getDiscountProjection(int projectionId, String userId, String sessionId, String frequency, List<Integer> startAndEndPeriods, String hierarchyNo, boolean isProgram, List<String> discountList,
            String year, int historyNumber, int levelNo, String hierarchyIndicator, String userGroup, int startIndex, int endIndex, boolean isCount, boolean isCustom,
            List<String> customViewDetails, boolean isRefresh, String refreshHierarchyNumbers, String relationshipBuilderSid, boolean isAltHistory, String action) {

        String customQuery = StringUtils.EMPTY;
        String genQuery = StringUtils.EMPTY;
        boolean viewFlag = "view".equalsIgnoreCase(action);
        String masterTableName = !viewFlag ? "ST_NM_DISCOUNT_PROJ_MASTER" : Constant.NM_DISCOUNT_PROJ_MASTER;
        String actualDiscountTableName = !viewFlag ? "ST_NM_ACTUAL_DISCOUNT" : "NM_ACTUAL_DISCOUNT";
        String actualSalesTableName = !viewFlag ? "ST_NM_ACTUAL_SALES" : "ST_NM_ACTUAL_SALES";
        String discountTableName = !viewFlag ? "ST_NM_DISCOUNT_PROJECTION" : "NM_DISCOUNT_PROJECTION";
        String salesTableName = !viewFlag ? "ST_NM_SALES_PROJECTION" : "NM_SALES_PROJECTION";
        if (isCustom) {
            genQuery = SQlUtil.getQuery(getClass(),!viewFlag ? "nm.discCustomGen" : "nm.discCustomGenView");
        } else {
            genQuery = SQlUtil.getQuery(getClass(),!viewFlag ? "nm.discountGenerate" : "nm.discountGenerateView");
        }

        LOGGER.debug(" Entering getDiscountProjection");
        try {
            String declareStatement = "";
            String selectClause = "";
            String whereClause = "";
            String groupBy = "";
            String orderBy = "";
            String freq = "";
            String grpBy = "";
            String grpByData = "";
            String freq_Actual = "";
            String discType = "";

            int startFreq = 0;
            int endFreq = 0;
            int startYear = 0;
            int endYear = 0;
            String alternateWhereClause = " AND DAA.USER_ID = A.USER_ID\n"
                    + "       AND DAA.SESSION_ID = A.SESSION_ID " + " AND DAA.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID\n"
                    + "       AND B.RS_MODEL_SID = DAA.RS_MODEL_SID " + " AND A.PERIOD_SID = DAA.PERIOD_SID ";
            String altHisCountClause = "JOIN ST_DISC_ALTERNATE_HIST_ALLOCATION STDAA\n"
                    + "                 ON STDAA.PROJECTION_DETAILS_SID = DP.PROJECTION_DETAILS_SID\n"
                    + "                    AND STDAA.RS_MODEL_SID = DP.RS_MODEL_SID\n"
                    + "					AND DP.USER_ID = STDAA.USER_ID\n"
                    + "					AND	DP.SESSION_ID = STDAA.SESSION_ID";

            String hierarchy = "";
            if (hierarchyIndicator.equals("C")) {
                hierarchy = Constant.PROJECTION_CUST_HIERARCHY;
            } else if (hierarchyIndicator.equals("P")) {
                hierarchy = Constant.PROJECTION_PROD_HIERARCHY;
            }

            String levelSelectionStatement = "";
            if (levelNo != 0) {
                levelSelectionStatement = " HLD.LEVEL_NO = " + levelNo;
            }

            if (!isCount) {

                if (startAndEndPeriods != null && !startAndEndPeriods.isEmpty()) {
                    startFreq = startAndEndPeriods.get(0);
                    endFreq = startAndEndPeriods.get(1);
                    startYear = startAndEndPeriods.get(2);
                    endYear = startAndEndPeriods.get(3);
                }

                selectClause = "Select  Min(CASE(B.CHECK_RECORD) WHEN 1 THEN 1 ELSE 0 END) AS CHECK_RECORD, \n "
                        + " H.LEVEL_NO, CCP.HIERARCHY_NO, H.RELATIONSHIP_LEVEL_VALUES, H.LEVEL_NAME, I.\"YEAR\", ";
                orderBy = ", I.\"YEAR\"";
                if (CommonUtils.isInteger(year)) {
                    whereClause += " and I.\"YEAR\" = " + year;
                }

                int startMonth = 0;
                int endMonth = 0;
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
                    selectClause += "I.QUARTER, ";
                    groupBy += Constant.I_QUARTER;
                    orderBy += Constant.I_QUARTER;
                    freq = "I.QUARTER,";
                    grpBy = "I.QUARTER,";
                    grpByData = "QUARTER,";
                    freq_Actual = "AD.QUARTER,";
                }
                if (frequency.equals(ANNUALLY.getConstant())) {
                    startMonth = 1;
                    endMonth = 12;
                    selectClause += "'' as ANNUAL, ";
                    groupBy += "";
                    freq = "NULL as ANNUAL,";
                    grpBy = "";
                    grpByData = "";
                    freq_Actual = "AD.YEAR AS YEARP,";
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
                    selectClause += "I.SEMI_ANNUAL, ";
                    groupBy += Constant.I_SEMI_ANNUAL;
                    orderBy += Constant.I_SEMI_ANNUAL;
                    freq = "I.SEMI_ANNUAL,";
                    grpBy = "I.SEMI_ANNUAL,";
                    grpByData = "SEMI_ANNUAL,";
                    freq_Actual = "AD.SEMI_ANNUAL,";
                }
                if (frequency.equals(MONTHLY.getConstant())) {
                    startMonth = startFreq;
                    endMonth = endFreq;
                    selectClause += "I.\"MONTH\", ";
                    groupBy += Constant.I_MONTH;
                    orderBy += Constant.I_MONTH;
                    freq = "I.MONTH,";
                    grpBy = "I.MONTH,";
                    grpByData = "MONTH,";
                    freq_Actual = "AD.MONTH,";
                }

                declareStatement = Constant.DECLARE_START_MONTH_INT + startMonth + "\n"
                        + Constant.DECLARE_START_YEAR_INT + startYear + "\n"
                        + Constant.DECLARE_END_MONTH_INT + endMonth + "\n"
                        + Constant.DECLARE_END_YEAR_INT + endYear + "\n";
                String discountTypeColumnName = "'All_Discounts' as DISCOUNTS";
                String selectedDiscounts = StringUtils.EMPTY;
                if (discountList != null && !discountList.isEmpty()) {

                    if (isProgram) {
                        discType = "R";
                        discountTypeColumnName = " J.RS_NAME ";
                        orderBy = " H.RELATIONSHIP_LEVEL_VALUES, J.RS_NAME " + orderBy;
                    } else {
                        discType = "P";
                        discountTypeColumnName = " B.PRICE_GROUP_TYPE ";
                        orderBy = " H.RELATIONSHIP_LEVEL_VALUES, B.PRICE_GROUP_TYPE " + orderBy;
                    }
                    selectedDiscounts = CommonUtils.CollectionToString(discountList, true);
                    whereClause += " and " + discountTypeColumnName + " in (" + selectedDiscounts + ")";
                    groupBy += ", " + discountTypeColumnName;
                }

                selectClause += discountTypeColumnName + ", ";

                String periodFilter = "";
                if (!CommonUtils.isInteger(year)) {
                    periodFilter = "AND I.PERIOD_DATE " + getPeriodFilter();
                }

                String hierarchyNumbers = "";

                String intermediate = "Select  DISTINCT HIERARCHY_NO FROM( ";

                if (!isCustom) {
                    if (isRefresh) {
                        hierarchyNumbers = refreshHierarchyNumbers;
                        hierarchyNo = StringUtils.EMPTY;
                    } else {
                        List<String> hierarchyNoList = getHierarchyList(projectionId, hierarchy, hierarchyNo, relationshipBuilderSid, levelNo, userGroup, discountList, userId, sessionId, isProgram, startIndex, endIndex, isAltHistory, viewFlag);
                        hierarchyNumbers = intermediate + hierarchyNoList.get(0) + ") as  HierarchyNos ";
                    }
                } else {
                    List<String> hierarchyNoList = getHierarchyListForCustomView(projectionId, hierarchyIndicator, userGroup, customViewDetails, discountList, userId, sessionId, isProgram, startIndex, endIndex, isAltHistory, viewFlag);
                    if (hierarchyNoList != null) {
                        hierarchyNumbers = intermediate + hierarchyNoList.get(0) + ") as  HierarchyNos ";
                    }
                }

                if (hierarchyNumbers.isEmpty()) {
                    LOGGER.debug("Returning Hierarchy Number Empty:::::::::::::::::::::::::::::::::");
                    return new ArrayList<String>();
                }

                String ccpDetails = "(";
                if (customViewDetails.isEmpty()) {

                    String connector = "";

                    if (!levelSelectionStatement.isEmpty()) {
                        connector = Constant.SPACE_AND_SPACE;
                    }
                    if (!isAltHistory) {
                        ccpDetails = StringUtils.EMPTY;
                    }
                    ccpDetails = ccpDetails + "SELECT  Distinct CCPMAP.CCP_DETAILS_SID,\n"
                            + "                         HLD.HIERARCHY_NO,\n"
                            + "                         HLD.RELATIONSHIP_LEVEL_SID,"
                            + "                         PROJECTION_DETAILS_SID\n"
                            + Constant.FROM_SELECT_RLD_HIERAR
                            + "                                 CCP.CCP_DETAILS_SID,"
                            + "                                 PD.PROJECTION_DETAILS_SID\n"
                            + Constant.FROM_RELATION
                            + "                          JOIN   CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID = CCP.RELATIONSHIP_LEVEL_SID  AND RLD.RELATIONSHIP_BUILDER_SID = " + relationshipBuilderSid + "\n"
                            + Constant.JOIN_PROJECTION + projectionId + "\n";
                    ccpDetails += Constant.JOIN + hierarchy + Constant.PCH_ON_PCHRELATIONSHIP_LEVEL_SID_RLDRE
                            + Constant.AND_PCHP + projectionId + "\n"
                            + Constant.CCPMAP_JOIN
                            + Constant.SELECT_RL_D1HIERA
                            + Constant.RL_D1RELA
                            + Constant.RL_D1_LEVEL
                            + Constant.FROM_RELATIONSHIP_RELATION
                            + Constant.SPACE_JOIN_SPACE + hierarchy + Constant.PCH_ON_PCH_RELATIONSHIP_LEVEL_SID_RL_D1 + projectionId + " \n"
                            + Constant.AND_RL_D1HIERARC + hierarchyNo + Constant.HLD_ON_CCPMAPHIERARCHY_NO_LIKE_HLDHIERAR
                            + " WHERE " + levelSelectionStatement + connector + " HLD.HIERARCHY_NO in(" + hierarchyNumbers + ")\n";
                    if (isAltHistory) {
                        ccpDetails = ccpDetails + Constant.CCP;
                    }
                } else {
                    String customId = customViewDetails.get(0);
                    String customerLevelNo = customViewDetails.get(1);
                    String customerHierarchyNo = customViewDetails.get(2);
                    String productLevelNo = customViewDetails.get(3);
                    String productHierarchyNo = customViewDetails.get(4);
                    String relationshipBuilderSidForCustomer = customViewDetails.get(5);
                    String relationshipBuilderSidForProduct = customViewDetails.get(6);
                    ccpDetails = "(SELECT TEMP_CCP.RELATIONSHIP_LEVEL_SID, TEMP_CCP.HIERARCHY_NO,  TEMP_CCP.CCP_DETAILS_SID , TEMP_CCP.PROJECTION_DETAILS_SID FROM \n"
                            + "   (SELECT distinct HLD" + hierarchyIndicator + ".RELATIONSHIP_LEVEL_SID,HLD" + hierarchyIndicator + ".HIERARCHY_NO, CCPMAPC.CCP_DETAILS_SID , CCPMAPC.PROJECTION_DETAILS_SID FROM \n"
                            + "    (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID , PD.PROJECTION_DETAILS_SID \n"
                            + Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RL
                            + Constant.JOIN_CCP_MAP_CCP_ON_RLDRELATIONSHIP_L
                            + Constant.AND_RLDRELATIONSHIP_BUILDER_SID + relationshipBuilderSidForCustomer + "\n"
                            + Constant.JOIN_PROJECTION_DETAILS_PD_ON_PDCCP_D + projectionId + "";

                    ccpDetails += " ) CCPMAPC\n"
                            + Constant.JOIN_SELECT_RLDRELATIONSHIP_LEVEL_VAL
                            + Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION
                            + Constant.JOIN_CCP_MAP_CCP_ON_RLDRELATION
                            + Constant.AND_RLDRELATIONSHIP_BUILDER_SID + relationshipBuilderSidForProduct + "\n"
                            + Constant.JOIN_PROJECTION_DETAILS_PD_ON_PDC + projectionId + "";

                    ccpDetails += " ) CCPMAPP \n"
                            + Constant.ON_CCPMAPCCCP_DETAILS_SIDCCPMAPPCCP_D
                            + Constant.SPACE_JOIN_NEW_LINE
                            + Constant.SELECT_RL_D2_HIERARCHY_NORLD2_RELATIONSH
                            + Constant.JOIN_DBO_CUSTOM_VIEW_MASTER_CVM_ON_CUSTOM_VIEW_MASTER + customId + Constant.AND_CVD_LEVEL_NO_LIKE + customerLevelNo + "'\n"
                            + Constant.JOIN_DBO_HIERARCHY_LEVEL_DEFINITION
                            + Constant.JOIN_RELATIONSHIP_LEVEL_DEFINITIO
                            + Constant.JOIN_PROJECTION_CUST_HIERARCHY_PC + projectionId + "\n"
                            + Constant.WHERE_RL_D2_HIERARCHY_NO_LIKE + customerHierarchyNo + Constant.HLDC_ON_CCPMAP_HIERARCHY_NO_LIKE_HLDCHIE
                            + Constant.SPACE_JOIN_NEW_LINE
                            + "    ( SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD \n"
                            + Constant.JOIN_DBO_CUSTOM_VIEW_MASTER_CVM_ON_CUSTOM_VIEW_MASTER + customId + Constant.AND_CVD_LEVEL_NO_LIKE + productLevelNo + "'\n"
                            + "        JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                            + Constant.JOIN_RELATIONSHIP_LEVEL_DEFINITIO
                            + Constant.JOIN_PROJECTION_PROD_HIERARCHY_PC + projectionId + "\n"
                            + Constant.WHERE_RL_D2_HIERARCHY_NO_LIKE + productHierarchyNo + "%' ) HLDP ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%'\n"
                            + ")TEMP_CCP where TEMP_CCP.HIERARCHY_NO in(" + hierarchyNumbers + " )) CCP";
                }
                if (!isAltHistory) {
                    genQuery = genQuery.replace("?SM", String.valueOf(startMonth));
                    genQuery = genQuery.replace("?SY", String.valueOf(startYear));
                    genQuery = genQuery.replace("?EM", String.valueOf(endMonth));
                    genQuery = genQuery.replace("?EY", String.valueOf(endYear));
                    genQuery = genQuery.replace("?DSC", discType);
                    genQuery = genQuery.replace("?PM", String.valueOf(projectionId));
                    genQuery = genQuery.replace("?UID", userId);
                    genQuery = genQuery.replace("?SID", sessionId);
                    genQuery = genQuery.replace("?RSN", String.valueOf(selectedDiscounts));
                    genQuery = genQuery.replace("?CCP", ccpDetails);
                    genQuery = genQuery.replace("?FRE", freq);
                    genQuery = genQuery.replace("?GRP", grpBy);
                    genQuery = genQuery.replace("?GRDATA", grpByData);
                    genQuery = genQuery.replace("?PERIOD_FREQ", freq_Actual);
                    if (grpBy.isEmpty()) {
                        genQuery = genQuery.replace("?GROUP_FREQUENCY", StringUtils.EMPTY);
                    } else {
                        String tempQuery = grpByData.substring(0, grpByData.length() - 1);
                        genQuery = genQuery.replace("?GROUP_FREQUENCY", "AND SA." + tempQuery + " = AD." + tempQuery);
                    }
                } else {
                    if (!userGroup.trim().isEmpty()) {
                        whereClause += " and B.USER_GROUP = '" + userGroup + "'";
                    }
                    String customSql = masterTableName + " B, PROJECTION_DETAILS E ,\n PROJECTION_MASTER F, "
                            + " RELATIONSHIP_LEVEL_DEFINITION H, \"PERIOD\" I , RS_MODEL J ,\n "
                            + ccpDetails + "\n "
                            + " where A.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID \n ";
                    if (!viewFlag) {
                        customSql += " and A.USER_ID = " + userId + "\n"
                                + " and A.SESSION_ID = " + sessionId + "\n";
                    }

                    customSql += " and B.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID \n "
                            + " and B.RS_MODEL_SID = J.RS_MODEL_SID \n "
                            + " and A.RS_MODEL_SID = J.RS_MODEL_SID \n "
                            + "  AND S.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID\n";
                    if (!viewFlag) {
                        customSql += "  AND S.USER_ID = " + userId + "\n"
                                + "  AND S.SESSION_ID = " + sessionId + "\n"
                                + " and B.USER_ID = " + userId + "\n"
                                + " and B.SESSION_ID = " + sessionId + "\n";
                    }
                    customSql += " and E.PROJECTION_MASTER_SID = F.PROJECTION_MASTER_SID \n "
                            + " and F.PROJECTION_MASTER_SID = " + projectionId
                            + " and E.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID \n "
                            + " and H.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n "
                            + " and A.PERIOD_SID = I.PERIOD_SID \n "
                            + "  AND S.PERIOD_SID = I.PERIOD_SID \n ";

                    String historysql = customSql + periodFilter + " " + whereClause;
                    customSql += periodFilter + "\n "
                            + whereClause + "\n "
                            + Constant.GROUP_BY_HLEVEL_NO_HRELATIONSHIP_LEVEL_V + groupBy;

                    String historyQuery = selectClause + "\n COALESCE(Sum(A.ACTUAL_SALES) / (NULLIF(Sum(distinct S.ACTUAL_SALES), 0)), 0) * 100 as ACTUAL_RATE,"
                            + "avg(A.ACTUAL_PROJECTION_RATE) as PROJECTION_RATE, 0 as AP_TABLE_INDICATOR,\n"
                            + " Max(B.USER_GROUP) as USER_GROUP, count(*) as CCP_COUNT, SUM(CASE(B.CHECK_RECORD) WHEN 1 THEN 0 ELSE 1 END) AS UNCHECK_COUNT , \n";

                    if (!isAltHistory) {
                        historyQuery = historyQuery + "SUM(A.ACTUAL_SALES)as ACTUAL_SALES,"
                                + "COALESCE(Sum(A.ACTUAL_SALES) / (NULLIF( Sum(distinct S.ACTUAL_UNITS), 0)), 0) as ACTUAL_RPU,\n"
                                + "SUM(A.ACTUAL_PROJECTION_SALES) as PROJECTION_SALES, NULL as PROJECTION_RPU, NULL as GROWTH "
                                + "from " + actualDiscountTableName + "A, " + actualSalesTableName + " S, \n"
                                + historysql + Constant.GROUP_BY_HLEVEL_NO_HRELATIONSHIP_LEVEL_V + groupBy;

                    } else {
                        historyQuery = historyQuery + "SUM(ISNULL(DAA.ACTUAL_AMOUNT,0))as ACTUAL_SALES,"
                                + "COALESCE(Sum(ISNULL(DAA.ACTUAL_AMOUNT,0)) / NULLIF(Sum(distinct S.ACTUAL_UNITS), 0), 0) as ACTUAL_RPU,\n"
                                + "SUM(ISNULL(DAA.PROJECTION_AMOUNT,0)) as PROJECTION_SALES, NULL as PROJECTION_RPU, NULL as GROWTH "
                                + "from ST_NM_ACTUAL_DISCOUNT A,ST_DISC_ALTERNATE_HIST_ALLOCATION DAA,ST_NM_ACTUAL_SALES S, \n"
                                + historysql + alternateWhereClause + Constant.GROUP_BY_HLEVEL_NO_HRELATIONSHIP_LEVEL_V + groupBy;

                    }

                    String futureQuery = selectClause + "\n NULL as ACTUAL_RATE,"
                            + "Sum(A.PROJECTION_SALES)/(NULLIF( SUM(distinct S.PROJECTION_SALES),0)) * 100 as PROJECTION_RATE, 1 as AP_TABLE_INDICATOR,\n"
                            + " Max(B.USER_GROUP) as USER_GROUP, count(*) as CCP_COUNT, SUM(CASE(B.CHECK_RECORD) WHEN 1 THEN 0 ELSE 1 END) AS UNCHECK_COUNT , \n"
                            + "NULL as ACTUAL_SALES, NULL as ACTUAL_RPU,\n"
                            + " SUM(A.PROJECTION_SALES)as PROJECTION_SALES,"
                            + "Sum(A.PROJECTION_SALES)/(NULLIF(SUM(distinct S.PROJECTION_UNITS),0)) as PROJECTION_RPU, avg(A.GROWTH) as GROWTH "
                            + "from " + discountTableName + " A," + salesTableName + " S, \n"
                            + customSql;

                    orderBy = "\n order by " + orderBy + ", AP_TABLE_INDICATOR";
                    customQuery = declareStatement + historyQuery;
                    String altHisQuery = StringUtils.EMPTY;
                    altHisQuery = isAltHistory ? orderBy : "\n \n  UNION \n \n " + futureQuery + orderBy;
                    customQuery += altHisQuery;
                }
            } else {
                String discountTypeQuery = "";
                if (discountList != null && !discountList.isEmpty()) {
                    String selectedDiscounts = CommonUtils.CollectionToString(discountList, true);
                    discountTypeQuery = Constant.JOIN + masterTableName + Constant.DP_ON_PD_PROJ_DETAILS_SID_DP_PROJ;
                    if (!viewFlag) {
                        discountTypeQuery += Constant.AND_DP_USER_ID + userId + Constant.AND_DP_SESSION_ID + sessionId + "\n";
                    }
                    if (isProgram) {
                        discountTypeQuery += Constant.JOIN_RS_MODEL_RS_ON_DP_RS_MODEL_SID + selectedDiscounts + Constant.OPEN_BRACKET_NEW_LINE;
                        discountTypeQuery += isAltHistory ? altHisCountClause : StringUtils.EMPTY;
                    } else {
                        discountTypeQuery += Constant.AND_DP_PRICE_GROUP_TYPE_IN + selectedDiscounts + Constant.CLOSE_BRACKET_NEW_LINE;
                    }
                    if (!userGroup.trim().isEmpty()) {
                        discountTypeQuery += " AND DP.USER_GROUP = '" + userGroup + "' \n";
                    }
                }

                String ccpDetails = "";
                if (isCustom) {
                    String customId = customViewDetails.get(0);
                    String customerLevelNo = customViewDetails.get(1);
                    String customerHierarchyNo = customViewDetails.get(2);
                    String productLevelNo = customViewDetails.get(3);
                    String productHierarchyNo = customViewDetails.get(4);
                    String relationshipBuilderSidForCustomer = customViewDetails.get(5);
                    String relationshipBuilderSidForProduct = customViewDetails.get(6);
                    ccpDetails = " (SELECT distinct  HLD" + hierarchyIndicator + ".HIERARCHY_NO FROM \n"
                            + Constant.SELECT_RLDRELATIONSHIP_LEVEL_VALUES_R
                            + Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RL
                            + Constant.JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL
                            + Constant.AND_RLDRELATIONSHIP_BUILDER_SID + relationshipBuilderSidForCustomer + "\n"
                            + Constant.JOIN_PROJECTION_DETAILS_PD_ON_PDCCP_D + projectionId + " ";

                    ccpDetails += discountTypeQuery + Constant.CCPMAPC
                            + Constant.JOIN_SELECT_RLDRELATIONSHIP_LEVEL_VAL
                            + Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION
                            + "        JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                            + "        AND RLD.RELATIONSHIP_BUILDER_SID = " + relationshipBuilderSidForProduct + " \n"
                            + Constant.JOIN_PROJECTION_DETAILS_PD_ON_PDC + projectionId + "";

                    ccpDetails += Constant.CCP_MAP
                            + Constant.ON_CCPMAPCCCP_DETAILS_SIDCCPMAPPCCP_D
                            + Constant.SPACE_JOIN_NEW_LINE
                            + Constant.SELECT_RL_D2_HIERARCHY_NORLD2_RELATIONSH
                            + Constant.JOIN_DBO_CUSTOM_VIEW_MASTER_CVM_ON_CUSTOM_VIEW_MASTER + customId + Constant.AND_CVD_LEVEL_NO_LIKE + customerLevelNo + "'\n"
                            + Constant.JOIN_DBO_HIERARCHY_LEVEL_DEFINITION
                            + Constant.JOIN_RELATIONSHIP_LEVEL_DEFINITIO
                            + Constant.JOIN_PROJECTION_CUST_HIERARCHY_PC + projectionId + "\n"
                            + Constant.WHERE_RL_D2_HIERARCHY_NO_LIKE + customerHierarchyNo + Constant.HLDC_ON_CCPMAP_HIERARCHY_NO_LIKE_HLDCHIE
                            + Constant.SPACE_JOIN_NEW_LINE
                            + Constant.SELECT_RL_D2_HIERARCHY_NORLD2_RELATIONSH
                            + Constant.JOIN_DBO_CUSTOM_VIEW_MASTER_CVM_ON_CUSTOM_VIEW_MASTER + customId + Constant.AND_CVD_LEVEL_NO_LIKE + productLevelNo + "'\n"
                            + Constant.JOIN_DBO_HIERARCHY_LEVEL_DEFINITION
                            + Constant.JOIN_RELATIONSHIP_LEVEL_DEFINITIO
                            + Constant.JOIN_PROJECTION_PROD_HIERARCHY_PC + projectionId + "\n"
                            + Constant.WHERE_RL_D2_HIERARCHY_NO_LIKE + productHierarchyNo + Constant.HLDP_ON_CCPMAP_HIERARCHY_NO_LIKE_HLDPHIE
                            + Constant.CLOSE_BRACKET_CCP;
                } else {
                    ccpDetails = "(SELECT HLD.HIERARCHY_NO \n"
                            + Constant.FROM_SELECT_RLD_HIERAR
                            + Constant.CCP_CCP_D
                            + Constant.FROM_RELATION
                            + "                          JOIN   CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID = CCP.RELATIONSHIP_LEVEL_SID\n"
                            + "                          JOIN   PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID \n "
                            + "                                     AND PD.PROJECTION_MASTER_SID = " + projectionId + "\n";

                    ccpDetails += discountTypeQuery
                            + Constant.SPACE_JOIN_SPACE + hierarchy + Constant.PCH_ON_PCHRELATIONSHIP_LEVEL_SID_RLDRE
                            + Constant.AND_PCHP + projectionId + "\n"
                            + Constant.CCPMAP_JOIN
                            + Constant.SELECT_RL_D1HIERA
                            + Constant.RL_D1RELA
                            + Constant.RL_D1_LEVEL
                            + Constant.FROM_RELATIONSHIP_RELATION
                            + Constant.SPACE_JOIN_SPACE + hierarchy + "   PCH ON PCH.RELATIONSHIP_LEVEL_SID = RLD1.RELATIONSHIP_LEVEL_SID \n"
                            + "                              AND PCH.PROJECTION_MASTER_SID =  " + projectionId + " \n"
                            + Constant.AND_RL_D1HIERARC + hierarchyNo + Constant.HLD_ON_CCPMAPHIERARCHY_NO_LIKE_HLDHIERAR
                            + " WHERE " + levelSelectionStatement + " \n"
                            + Constant.CCP;

                }
                customQuery = " Select DISTINCT CCP.HIERARCHY_NO  \n "
                        + "        FROM " + ccpDetails;
            }

            LOGGER.debug(" Fetching Discount Data");
            if (!isAltHistory && !isCount) {
                return HelperTableLocalServiceUtil.executeSelectQuery(genQuery);
            } else {
                return HelperTableLocalServiceUtil.executeSelectQuery(customQuery);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(customQuery);
            return null;
        }
    }

    private List getHierarchyListForCustomView(int projectionId, String hierarchyIndicator, String userGroup, List<String> customViewDetails, List<String> discountList,
            String userId, String sessionId, boolean isProgram, int startIndex, int endIndex, boolean isAlternate, boolean mode) {
        LOGGER.debug(" Inside getHierarchyListForCustomView");

        String tableName = mode ? "NM_DISCOUNT_PROJ_MASTER " : "ST_NM_DISCOUNT_PROJ_MASTER ";
        String customSql = StringUtils.EMPTY;
        try {

            String discountTypeQuery = "";
            if (discountList != null && !discountList.isEmpty()) {
                String selectedDiscounts = CommonUtils.CollectionToString(discountList, true);
                discountTypeQuery = Constant.SPACE_JOIN_SPACE + tableName + Constant.DP_ON_PD_PROJ_DETAILS_SID_DP_PROJ;
                if (!mode) {
                    discountTypeQuery += Constant.AND_DP_USER_ID + userId + Constant.AND_DP_SESSION_ID + sessionId + "\n";
                }
                if (!userGroup.trim().isEmpty()) {
                    discountTypeQuery += " and DP.USER_GROUP = '" + userGroup + "'\n";
                }
                if (isProgram) {
                    discountTypeQuery += Constant.JOIN_RS_MODEL_RS_ON_DP_RS_MODEL_SID + selectedDiscounts + Constant.OPEN_BRACKET_NEW_LINE;
                } else {
                    discountTypeQuery += Constant.AND_DP_PRICE_GROUP_TYPE_IN + selectedDiscounts + Constant.CLOSE_BRACKET_NEW_LINE;
                }
            }

            String customId = customViewDetails.get(0);
            String customerLevelNo = customViewDetails.get(1);
            String customerHierarchyNo = customViewDetails.get(2);
            String productLevelNo = customViewDetails.get(3);
            String productHierarchyNo = customViewDetails.get(4);
            String relationshipBuilderSidForCustomer = customViewDetails.get(5);
            String relationshipBuilderSidForProduct = customViewDetails.get(6);

            String ccpDetails = " (SELECT distinct  HLD" + hierarchyIndicator + ".HIERARCHY_NO,CCPMAPP.RELATIONSHIP_LEVEL_VALUES FROM \n"
                    + Constant.SELECT_RLDRELATIONSHIP_LEVEL_VALUES_R
                    + Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RL
                    + Constant.JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL
                    + Constant.AND_RLDRELATIONSHIP_BUILDER_SID + relationshipBuilderSidForCustomer + "\n"
                    + Constant.JOIN_PROJECTION_DETAILS_PD_ON_PDCCP_D + projectionId + " "
                    + discountTypeQuery + Constant.CCPMAPC
                    + Constant.JOIN_SELECT_RLDRELATIONSHIP_LEVEL_VAL
                    + Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION
                    + "        JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                    + "        AND RLD.RELATIONSHIP_BUILDER_SID = " + relationshipBuilderSidForProduct + " \n"
                    + Constant.JOIN_PROJECTION_DETAILS_PD_ON_PDC + projectionId + Constant.CCP_MAP
                    + Constant.ON_CCPMAPCCCP_DETAILS_SIDCCPMAPPCCP_D
                    + Constant.SPACE_JOIN_NEW_LINE
                    + Constant.SELECT_RL_D2_HIERARCHY_NORLD2_RELATIONSH
                    + Constant.JOIN_DBO_CUSTOM_VIEW_MASTER_CVM_ON_CUSTOM_VIEW_MASTER + customId + Constant.AND_CVD_LEVEL_NO_LIKE + customerLevelNo + "'\n"
                    + Constant.JOIN_DBO_HIERARCHY_LEVEL_DEFINITION
                    + Constant.JOIN_RELATIONSHIP_LEVEL_DEFINITIO
                    + Constant.JOIN_PROJECTION_CUST_HIERARCHY_PC + projectionId + "\n"
                    + Constant.WHERE_RL_D2_HIERARCHY_NO_LIKE + customerHierarchyNo + Constant.HLDC_ON_CCPMAP_HIERARCHY_NO_LIKE_HLDCHIE
                    + Constant.SPACE_JOIN_NEW_LINE
                    + Constant.SELECT_RL_D2_HIERARCHY_NORLD2_RELATIONSH
                    + Constant.JOIN_DBO_CUSTOM_VIEW_MASTER_CVM_ON_CUSTOM_VIEW_MASTER + customId + Constant.AND_CVD_LEVEL_NO_LIKE + productLevelNo + "'\n"
                    + Constant.JOIN_DBO_HIERARCHY_LEVEL_DEFINITION
                    + Constant.JOIN_RELATIONSHIP_LEVEL_DEFINITIO
                    + Constant.JOIN_PROJECTION_PROD_HIERARCHY_PC + projectionId + "\n"
                    + Constant.WHERE_RL_D2_HIERARCHY_NO_LIKE + productHierarchyNo + Constant.HLDP_ON_CCPMAP_HIERARCHY_NO_LIKE_HLDPHIE
                    + ") CCP)C";

            customSql = "SELECT HIERARCHY_NO FROM(SELECT DISTINCT CCP.HIERARCHY_NO  \n"
                    + " FROM " + ccpDetails + "\n"
                    + " order by C.HIERARCHY_NO  ";
            if (!isAlternate) {
                customSql += " OFFSET " + startIndex + " ROWS FETCH NEXT " + endIndex + " ROWS ONLY ";
            }

            List<String> returnList = new ArrayList<String>();
            returnList.add(customSql);
            return returnList;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(customSql);
            return null;
        } finally {
            LOGGER.debug(" exiting getHierarchyListForCustomView");
        }
    }

    private List getHierarchyList(int projectionId, String hierarchy, String hierarchyNo, String relationshipBuilderSid, int levelNo, String userGroup,
            List<String> discountList, String userId, String sessionId, boolean isProgram, int startIndex, int endIndex, boolean isAlternate, boolean mode) {
        LOGGER.debug("Inside getHierarchyList");

        String tableName = mode ? Constant.NM_DISCOUNT_PROJ_MASTER : "ST_NM_DISCOUNT_PROJ_MASTER ";
        String customSql = StringUtils.EMPTY;
        try {
            String levelSelectionStatement = "";
            if (levelNo != 0) {
                levelSelectionStatement = " WHERE HLD.LEVEL_NO = " + levelNo + " ";
            }

            String discountTypeQuery = "";
            if (discountList != null && !discountList.isEmpty()) {
                String selectedDiscounts = CommonUtils.CollectionToString(discountList, true);
                discountTypeQuery = Constant.SPACE_JOIN_SPACE + tableName + Constant.DP_ON_PD_PROJ_DETAILS_SID_DP_PROJ;
                if (!mode) {
                    discountTypeQuery += Constant.AND_DP_USER_ID + userId + Constant.AND_DP_SESSION_ID + sessionId + "\n";
                }

                if (!userGroup.trim().isEmpty()) {
                    discountTypeQuery += " and DP.USER_GROUP = '" + userGroup + "'\n";
                }
                if (isProgram) {
                    discountTypeQuery += Constant.JOIN_RS_MODEL_RS_ON_DP_RS_MODEL_SID + selectedDiscounts + Constant.OPEN_BRACKET_NEW_LINE;
                } else {
                    discountTypeQuery += Constant.AND_DP_PRICE_GROUP_TYPE_IN + selectedDiscounts + Constant.CLOSE_BRACKET_NEW_LINE;
                }
            }

            String ccpDetails = "(SELECT  HLD.HIERARCHY_NO,HLD.RELATIONSHIP_LEVEL_VALUES \n"
                    + Constant.FROM_SELECT_RLD_HIERAR
                    + "                                 CCP.CCP_DETAILS_SID,RELATIONSHIP_LEVEL_VALUES\n"
                    + Constant.FROM_RELATION
                    + Constant.JOIN_CCP_MAP + relationshipBuilderSid + "\n"
                    + Constant.JOIN_PROJECTION + projectionId + "\n"
                    + discountTypeQuery
                    + Constant.JOIN + hierarchy + Constant.PCH_ON_PCHRELATIONSHIP_LEVEL_SID_RLDRE
                    + Constant.AND_PCHP + projectionId + "\n"
                    + Constant.CCPMAP_JOIN
                    + Constant.SELECT_RL_D1HIERA
                    + Constant.RL_D1RELA
                    + Constant.RL_D1LEVE
                    + Constant.FROM_RELATIONSHIP_RELATION
                    + Constant.SPACE_JOIN_SPACE + hierarchy + Constant.PCH_ON_PCH_RELATIONSHIP_LEVEL_SID_RL_D1 + projectionId + " \n"
                    + Constant.AND_RL_D1HIERARC + hierarchyNo + Constant.HLD_ON_CCPMAPHIERARCHY_NO_LIKE_HLDHIERAR
                    + levelSelectionStatement + " \n"
                    + Constant.CCP;

            customSql = "SELECT DISTINCT CCP.HIERARCHY_NO,RELATIONSHIP_LEVEL_VALUES  \n"
                    + " FROM " + ccpDetails + "\n"
                    + " order by RELATIONSHIP_LEVEL_VALUES";
            if (!isAlternate) {
                customSql += " OFFSET " + startIndex + " ROWS FETCH NEXT " + endIndex + " ROWS ONLY ";
            }

            List<String> returnList = new ArrayList<String>();
            returnList.add(customSql);
            return returnList;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(customSql);
            return null;
        } finally {
            LOGGER.debug(" exiting getHierarchyList");
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
                String selectedDiscounts = CommonUtils.CollectionToString(discountList, true);
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
            return null;
        }
    }

    public void checkClearAll(int projectionId, String userId, String sessionId, String userGroup, boolean checkValue, boolean isProgram, List<String> discountList) {

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

    public int updateCheckRecord(int projectionId, String userId, String sessionId, boolean checkValue, String hierarchyNo, String userGroup,
            String hierarchyIndicator, boolean isCustomView, List<String> customViewDetails, String relationshipBuilderSid, boolean isProgram, List<String> discountList) {

        String customSql = "";
        LOGGER.debug(" inside updateCheckRecord");
        try {
            String hierarchy = "";
            if (hierarchyIndicator.equals("C")) {
                hierarchy = Constant.PROJECTION_CUST_HIERARCHY;
            } else if (hierarchyIndicator.equals("P")) {
                hierarchy = Constant.PROJECTION_PROD_HIERARCHY;
            }

            String discountTypeQuery = "";
            String discountTable = "";
            if (discountList != null && !discountList.isEmpty()) {
                String selectedDiscounts = CommonUtils.CollectionToString(discountList, true);
                if (isProgram) {
                    discountTable = " RS_MODEL RS,";
                    discountTypeQuery += " AND M.RS_MODEL_SID = RS.RS_MODEL_SID  AND RS.RS_NAME  in (" + selectedDiscounts + Constant.OPEN_BRACKET_NEW_LINE;
                } else {
                    discountTypeQuery += " AND M.PRICE_GROUP_TYPE in (" + selectedDiscounts + Constant.CLOSE_BRACKET_NEW_LINE;
                }
            }

            String ccpDetails = "";
            int check = checkValue ? 1 : 0;
            if (isCustomView && customViewDetails != null && !customViewDetails.isEmpty()) {

                String customId = customViewDetails.get(0);
                String customerLevelNo = customViewDetails.get(1);
                String customerHierarchyNo = customViewDetails.get(2);
                String productLevelNo = customViewDetails.get(3);
                String productHierarchyNo = customViewDetails.get(4);
                String relationshipBuilderSidForCustomer = customViewDetails.get(5);
                String relationshipBuilderSidForProduct = customViewDetails.get(6);
                ccpDetails = " (SELECT distinct CCPMAPC.PROJECTION_DETAILS_SID FROM \n"
                        + "    (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID, PD.PROJECTION_DETAILS_SID \n"
                        + Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RL
                        + Constant.JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL
                        + Constant.AND_RLDRELATIONSHIP_BUILDER_SID + relationshipBuilderSidForCustomer + "\n"
                        + Constant.JOIN_PROJECTION_DETAILS_PD_ON_PDCCP_D + projectionId + "\n"
                        + "    ) CCPMAPC\n"
                        + Constant.JOIN_SELECT_RLDRELATIONSHIP_LEVEL_VAL
                        + Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION
                        + Constant.JOIN_CCP_MAP_CCP_ON_RLDRELATION
                        + Constant.AND_RLDRELATIONSHIP_BUILDER_SID + relationshipBuilderSidForProduct + "\n"
                        + Constant.JOIN_PROJECTION_DETAILS_PD_ON_PDC + projectionId + Constant.CCP_MAP
                        + Constant.ON_CCPMAPCCCP_DETAILS_SIDCCPMAPPCCP_D
                        + Constant.SPACE_JOIN_NEW_LINE
                        + Constant.SELECT_RL_D2_HIERARCHY_NORLD2_RELATIONSH
                        + Constant.JOIN_DBO_CUSTOM_VIEW_MASTER_CVM_ON_CUSTOM_VIEW_MASTER + customId + Constant.AND_CVD_LEVEL_NO_LIKE + customerLevelNo + "'\n"
                        + Constant.JOIN_DBO_HIERARCHY_LEVEL_DEFINITION
                        + Constant.JOIN_RELATIONSHIP_LEVEL_DEFINITIO
                        + Constant.JOIN_PROJECTION_CUST_HIERARCHY_PC + projectionId + "\n"
                        + Constant.WHERE_RL_D2_HIERARCHY_NO_LIKE + customerHierarchyNo + Constant.HLDC_ON_CCPMAP_HIERARCHY_NO_LIKE_HLDCHIE
                        + Constant.SPACE_JOIN_NEW_LINE
                        + Constant.SELECT_RL_D2_HIERARCHY_NORLD2_RELATIONSH
                        + Constant.JOIN_DBO_CUSTOM_VIEW_MASTER_CVM_ON_CUSTOM_VIEW_MASTER + customId + Constant.AND_CVD_LEVEL_NO_LIKE + productLevelNo + "'\n"
                        + Constant.JOIN_DBO_HIERARCHY_LEVEL_DEFINITION
                        + Constant.JOIN_RELATIONSHIP_LEVEL_DEFINITIO
                        + Constant.JOIN_PROJECTION_PROD_HIERARCHY_PC + projectionId + "\n"
                        + Constant.WHERE_RL_D2_HIERARCHY_NO_LIKE + productHierarchyNo + Constant.HLDP_ON_CCPMAP_HIERARCHY_NO_LIKE_HLDPHIE
                        + Constant.WHERE_HLD + hierarchyIndicator + Constant.DOT_HIERARCHY_NO + hierarchyNo + "'"
                        + Constant.CLOSE_BRACKET_CCP;

                customSql = Constant.UPDATE_M_SET_CHECK_RECORD + check + "\n"
                        + Constant.FROM_ST_NM_DISCOUNT_PROJ_MASTER_M_PROJEC + discountTable + " \n"
                        + ccpDetails
                        + Constant.WHERE_M_USER_ID + userId + " \n"
                        + Constant.AND_M_SESSION_ID + sessionId + "\n"
                        + Constant.AND_EPROJECTION_MASTER_SID + projectionId + "' \n"
                        + Constant.AND_EPROJECTION_DETAILS_SID_MPROJECTION
                        + Constant.AND_M_PROJECTION_DETAILS_SID_CCP_PROJ
                        + discountTypeQuery;

                if (!userGroup.trim().isEmpty()) {
                    customSql += Constant.AND_MUSER_GROUP + userGroup + "' \n";
                }
                return HelperTableLocalServiceUtil.executeUpdateQueryCount(customSql);
            } else {

                ccpDetails = Constant.SELECT_CCPMAPPROJECTION_DETAILS_SID
                        + Constant.FROM_SELECT_RLD_HIERAR
                        + "                                 CCP.CCP_DETAILS_SID, PD.PROJECTION_DETAILS_SID \n"
                        + Constant.FROM_RELATION
                        + Constant.JOIN_CCP_MAP + relationshipBuilderSid + "\n"
                        + Constant.JOIN_PROJECTION + projectionId + "\n"
                        + Constant.JOIN + hierarchy + Constant.PCH_ON_PCHRELATIONSHIP_LEVEL_SID_RLDRE
                        + Constant.AND_PCHP + projectionId + "\n"
                        + Constant.CCPMAP_JOIN
                        + Constant.SELECT_RL_D1HIERA
                        + Constant.RL_D1RELA
                        + Constant.RL_D1_LEVEL
                        + Constant.FROM_RELATIONSHIP_RELATION
                        + Constant.SPACE_JOIN_SPACE + hierarchy + Constant.PCH_ON_PCH_RELATIONSHIP_LEVEL_SID_RL_D1 + projectionId + " \n"
                        + "                         AND  RLD1.HIERARCHY_NO LIKE '%') HLD ON CCPMAP.HIERARCHY_NO LIKE HLD.HIERARCHY_NO+'%' \n"
                        + "  WHERE HLD.HIERARCHY_NO in('" + hierarchyNo + "'))CCP";

                customSql = Constant.UPDATE_M_SET_CHECK_RECORD + check + "\n"
                        + Constant.FROM_ST_NM_DISCOUNT_PROJ_MASTER_M_PROJEC + discountTable + " \n"
                        + ccpDetails
                        + Constant.WHERE_M_USER_ID + userId + " \n"
                        + Constant.AND_M_SESSION_ID + sessionId + "\n"
                        + Constant.AND_EPROJECTION_MASTER_SID + projectionId + "' \n"
                        + Constant.AND_EPROJECTION_DETAILS_SID_MPROJECTION
                        + Constant.AND_M_PROJECTION_DETAILS_SID_CCP_PROJ
                        + discountTypeQuery;

                if (!userGroup.trim().isEmpty()) {
                    customSql += Constant.AND_MUSER_GROUP + userGroup + "'\n";
                }
                return HelperTableLocalServiceUtil.executeUpdateQueryCount(customSql);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(customSql);
            return 0;
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

                if (startAndEndPeriods != null && startAndEndPeriods.size() != 0) {
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

                String selectedDiscounts = CommonUtils.CollectionToString(checkedDiscounts, true);
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
                    customSql = customSql.replaceAll("@PROJID", "" + projectionId);
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
        String period = " BETWEEN \n "
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

    public boolean updateInputsForAdjustment(int projectionId, String userId, String sessionId, String frequency, String levelType, String adjustmentType, String adjustmentBasis,
            String adjustmentValue, String allocationMethodology, Map<String, Map<String, List<String>>> periodsMap) {
        Session dbSession = null;

        String masterTableUpdateQuery = "";
        String discountProjectionTableUpdateQuery = StringUtils.EMPTY;
        LOGGER.debug(" entering updateInputsForAdjustment");
        try {
            String baselinePeriods = "";
            String selectedPeriods = "";
            List<String> baselinePeriodsList;
            List<String> selectedPeriodsList;
            String baselineIndicator = "";

            if ("Historical % of Business".equals(allocationMethodology)) {
                baselineIndicator = "H";
            } else {
                baselineIndicator = "P";
            }

            for (String discountName : periodsMap.keySet()) {
                baselinePeriodsList = periodsMap.get(discountName).get(baselineIndicator);
                selectedPeriodsList = periodsMap.get(discountName).get("P");

                baselinePeriods = CommonUtils.CollectionToString(baselinePeriodsList, false, true);
                selectedPeriods = CommonUtils.CollectionToString(selectedPeriodsList, false, true);

                baselinePeriods = baselinePeriods.replace(", ", ",");
                selectedPeriods = selectedPeriods.replace(", ", ",");

                if (frequency.equals(MONTHLY.getConstant())) {
                    baselinePeriods = CommonUtils.replaceShortMonthForMonth(baselinePeriods);
                    selectedPeriods = CommonUtils.replaceShortMonthForMonth(selectedPeriods);
                }

                LOGGER.debug(" Baseline Periods= {} " , baselinePeriods);
                LOGGER.debug(" Selected Periods= {} " , selectedPeriods);
                masterTableUpdateQuery = "UPDATE DM SET DM.BASELINE_PERIODS = '" + baselinePeriods + "', DM.SELECTED_PERIODS = '" + selectedPeriods + "' FROM ST_NM_DISCOUNT_PROJ_MASTER DM "
                        + " JOIN (SELECT PD.PROJECTION_DETAILS_SID";

                if (levelType.equals(Constants.PROGRAM)) {
                    masterTableUpdateQuery += ", DPM.RS_MODEL_SID";
                }
                masterTableUpdateQuery += " FROM   PROJECTION_DETAILS PD JOIN ST_NM_DISCOUNT_PROJ_MASTER DPM ON DPM.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID";

                if (levelType.equals(Constants.PROGRAM)) {
                    masterTableUpdateQuery += " JOIN  RS_MODEL RS ON RS.RS_MODEL_SID = DPM.RS_MODEL_SID";
                }
                masterTableUpdateQuery += " WHERE  PD.PROJECTION_MASTER_SID = " + projectionId;

                if (levelType.equals(Constants.PROGRAM)) {
                    masterTableUpdateQuery += " AND RS.RS_NAME = ";
                } else {
                    masterTableUpdateQuery += " AND DPM.PRICE_GROUP_TYPE = ";
                }
                masterTableUpdateQuery += "'" + discountName + "')A ON A.PROJECTION_DETAILS_SID = DM.PROJECTION_DETAILS_SID  WHERE DM.CHECK_RECORD = 1";

                if (levelType.equals(Constants.PROGRAM)) {
                    masterTableUpdateQuery += " AND A.RS_MODEL_SID = DM.RS_MODEL_SID";
                }
                HelperTableLocalServiceUtil.executeUpdateQuery(masterTableUpdateQuery);
            }

            String period = "";
            if (frequency.equals(QUARTERLY.getConstant())) {
                period = " CAST(PR.QUARTER AS CHAR(1)) + CAST(PR.\"YEAR\" AS char(4))";
            }
            if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
                period = " CAST(PR.SEMI_ANNUAL AS CHAR(1)) + CAST(PR.\"YEAR\" AS char(4))";
            }
            if (frequency.equals(MONTHLY.getConstant())) {
                period = " CASE WHEN LEN(\"MONTH\")>1 THEN  CAST(\"MONTH\" AS CHAR(2)) ELSE '0'+ CAST(\"MONTH\" AS CHAR(1)) END + CAST(PR.\"YEAR\" AS char(4))";
            }
            if (frequency.equals(ANNUALLY.getConstant())) {
                period = "CAST(PR.\"YEAR\" AS char(4))";
            }

            for (String discountName : periodsMap.keySet()) {
                String selectedPeriodsToUpdate = CommonUtils.CollectionToString(periodsMap.get(discountName).get("P"), false);
                selectedPeriodsToUpdate = CommonUtils.replaceIntegerForMonth(selectedPeriodsToUpdate);
                selectedPeriodsToUpdate = selectedPeriodsToUpdate.replace("Q", "").replace("S", "").replace(" ", "");

                discountProjectionTableUpdateQuery = "UPDATE DP "
                        + " SET ADJUSTMENT_TYPE='" + adjustmentType + "' , ADJUSTMENT_BASIS='" + adjustmentBasis + "', ADJUSTMENT_VALUE=" + adjustmentValue + ", "
                        + " ADJUSTMENT_METHODOLOGY = '" + allocationMethodology + "' FROM ST_NM_DISCOUNT_PROJECTION DP"
                        + " JOIN (SELECT   DPM.PROJECTION_DETAILS_SID, DP.PERIOD_SID  FROM ST_NM_DISCOUNT_PROJ_MASTER DPM"
                        + " JOIN PROJECTION_DETAILS B ON DPM.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID "
                        + " JOIN ST_NM_DISCOUNT_PROJECTION DP ON DP.PROJECTION_DETAILS_SID = DPM.PROJECTION_DETAILS_SID "
                        + " AND DP.RS_MODEL_SID = DPM.RS_MODEL_SID AND DPM.USER_ID = DP.USER_ID AND DPM.SESSION_ID = DP.SESSION_ID";

                if (levelType.equals(Constants.PROGRAM)) {
                    discountProjectionTableUpdateQuery += " JOIN RS_MODEL RS ON DPM.RS_MODEL_SID = RS.RS_MODEL_SID ";
                }

                discountProjectionTableUpdateQuery += " JOIN \"PERIOD\" PPR on DP.PERIOD_SID=PPR.PERIOD_SID  "
                        + " WHERE B.PROJECTION_MASTER_SID=" + projectionId + " AND DPM.CHECK_RECORD = 1"
                        + " AND DPM.USER_ID = " + userId + ""
                        + " AND DPM.SESSION_ID = " + sessionId + "";

                if (levelType.equals(Constants.PROGRAM)) {
                    discountProjectionTableUpdateQuery += " AND RS.RS_NAME = '" + discountName + "'";
                } else if (levelType.equals(Constants.PROGRAM_CATEGORY)) {
                    discountProjectionTableUpdateQuery += " AND DPM.PRICE_GROUP_TYPE = '" + discountName + "'";
                }

                discountProjectionTableUpdateQuery += " AND PPR.PERIOD_SID in(SELECT PERIOD_SID from \"PERIOD\" PR WHERE " + period + " IN (" + selectedPeriodsToUpdate + ")) "
                        + " GROUP BY DPM.PROJECTION_DETAILS_SID, DP.PERIOD_SID )A ON A.PROJECTION_DETAILS_SID = DP.PROJECTION_DETAILS_SID WHERE DP.PERIOD_SID = A.PERIOD_SID"
                        + " AND DP.USER_ID = " + userId + ""
                        + " AND DP.SESSION_ID = " + sessionId + "";
                HelperTableLocalServiceUtil.executeUpdateQuery(discountProjectionTableUpdateQuery);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(masterTableUpdateQuery);
            LOGGER.error(discountProjectionTableUpdateQuery);
            return false;
        } finally {
            LOGGER.debug(" exiting updateInputsForAdjustment");
        }
        return true;
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
                String selectedDiscounts = CommonUtils.CollectionToString(discountList, true);
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
            return null;
        }
    }

    public boolean saveGroupValues(int projectionId, String userId, String sessionId, String hierarchyNo, String groupValue, boolean isProgram,
            boolean isCustom, List<String> customViewDetails, List<String> discountList, String relationshipBuilderSid) {

        String customSql = StringUtils.EMPTY;
        LOGGER.debug(" entering saveGroupValues");
        try {
            String discountTypeQuery = "";
            if (discountList != null && !discountList.isEmpty()) {
                String selectedDiscounts = CommonUtils.CollectionToString(discountList, true);
                discountTypeQuery = " JOIN   ST_NM_DISCOUNT_PROJ_MASTER DP ON PD.PROJECTION_DETAILS_SID=DP.PROJECTION_DETAILS_SID  \n"
                        + Constant.AND_DP_USER_ID + userId + Constant.AND_DP_SESSION_ID + sessionId + "\n";
                if (isProgram) {
                    discountTypeQuery += Constant.JOIN_RS_MODEL_RS_ON_DP_RS_MODEL_SID + selectedDiscounts + Constant.OPEN_BRACKET_NEW_LINE;
                } else {
                    discountTypeQuery += Constant.AND_DP_PRICE_GROUP_TYPE_IN + selectedDiscounts + Constant.CLOSE_BRACKET_NEW_LINE;
                }
            }

            String hierarchy = "PROJECTION_CUST_HIERARCHY";
            String ccpDetails = "";
            if (isCustom && customViewDetails != null && !customViewDetails.isEmpty()) {

                String customId = customViewDetails.get(0);
                String customerLevelNo = customViewDetails.get(1);
                String customerHierarchyNo = customViewDetails.get(2);
                String productLevelNo = customViewDetails.get(3);
                String productHierarchyNo = customViewDetails.get(4);
                String relationshipBuilderSidForCustomer = customViewDetails.get(5);
                String relationshipBuilderSidForProduct = customViewDetails.get(6);
                ccpDetails = Constant.SELECT_DISTINCT_HLDCRELATIONSHIP_LEVEL_S
                        + Constant.SELECT_RLDRELATIONSHIP_LEVEL_VALUES_R
                        + Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RL
                        + Constant.JOIN_CCP_MAP_CCP_ON_RLDRELATIONSHIP_L
                        + Constant.AND_RLDRELATIONSHIP_BUILDER_SID + relationshipBuilderSidForCustomer + "\n"
                        + Constant.JOIN_PROJECTION_DETAILS_PD_ON_PDCCP_D + projectionId
                        + discountTypeQuery + Constant.CCPMAPC
                        + Constant.JOIN_SELECT_RLDRELATIONSHIP_LEVEL_VAL
                        + Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION
                        + Constant.JOIN_CCP_MAP_CCP_ON_RLDRELATION
                        + Constant.AND_RLDRELATIONSHIP_BUILDER_SID + relationshipBuilderSidForProduct + "\n"
                        + Constant.JOIN_PROJECTION_DETAILS_PD_ON_PDC + projectionId + Constant.CCP_MAP
                        + Constant.ON_CCPMAPCCCP_DETAILS_SIDCCPMAPPCCP_D
                        + Constant.SPACE_JOIN_NEW_LINE
                        + Constant.SELECT_RL_D2_HIERARCHY_NORLD2_RELATIONSH
                        + Constant.JOIN_DBO_CUSTOM_VIEW_MASTER_CVM_ON_CUSTOM_VIEW_MASTER + customId + Constant.AND_CVD_LEVEL_NO_LIKE + customerLevelNo + "'\n"
                        + Constant.JOIN_DBO_HIERARCHY_LEVEL_DEFINITION
                        + Constant.JOIN_RELATIONSHIP_LEVEL_DEFINITIO
                        + Constant.JOIN_PROJECTION_CUST_HIERARCHY_PC + projectionId + "\n"
                        + Constant.WHERE_RL_D2_HIERARCHY_NO_LIKE + customerHierarchyNo + Constant.HLDC_ON_CCPMAP_HIERARCHY_NO_LIKE_HLDCHIE
                        + Constant.SPACE_JOIN_NEW_LINE
                        + Constant.SELECT_RL_D2_HIERARCHY_NORLD2_RELATIONSH
                        + Constant.JOIN_DBO_CUSTOM_VIEW_MASTER_CVM_ON_CUSTOM_VIEW_MASTER + customId + Constant.AND_CVD_LEVEL_NO_LIKE + productLevelNo + "'\n"
                        + Constant.JOIN_DBO_HIERARCHY_LEVEL_DEFINITION
                        + Constant.JOIN_RELATIONSHIP_LEVEL_DEFINITIO
                        + Constant.JOIN_PROJECTION_PROD_HIERARCHY_PC + projectionId + "\n"
                        + Constant.WHERE_RL_D2_HIERARCHY_NO_LIKE + productHierarchyNo + Constant.HLDP_ON_CCPMAP_HIERARCHY_NO_LIKE_HLDPHIE
                        + Constant.WHERE_HLDCHIERARCHY_NO + hierarchyNo + "'"
                        + Constant.CLOSE_BRACKET_CCP;
            } else {
                ccpDetails = Constant.SELECT_CCP_MAP_CCP_DETAILS_SID
                        + Constant.FROM_SELECT_RLD_HIERAR
                        + Constant.CCP_CCP_D
                        + Constant.FROM_RELATION
                        + Constant.JOIN_CCP_MAP + relationshipBuilderSid + "\n"
                        + Constant.JOIN_PROJECTION + projectionId + "\n"
                        + discountTypeQuery
                        + Constant.SPACE_JOIN_SPACE + hierarchy + Constant.PCH_ON_PCHRELATIONSHIP_LEVEL_SID_RLDRE
                        + Constant.AND_PCHP + projectionId + "\n"
                        + Constant.CCPMAP_JOIN
                        + Constant.SELECT_RL_D1HIERA
                        + Constant.RL_D1RELA
                        + Constant.RL_D1_LEVEL
                        + Constant.FROM_RELATIONSHIP_RELATION
                        + Constant.SPACE_JOIN_SPACE + hierarchy + Constant.PCH_ON_PCH_RELATIONSHIP_LEVEL_SID_RL_D1 + projectionId + " \n"
                        + Constant.AND_RL_D1HIERARC + hierarchyNo + Constant.HLD_ON_CCPMAPHIERARCHY_NO_LIKE_HLDHIERAR
                        + Constant.WHERE_HLDHIERARCHY_NO + hierarchyNo + "')";
            }

            customSql = Constant.UPDATE_ST_NM_DISCOUNT_PROJ_MASTER_SET_USE + groupValue + "' \n"
                    + Constant.WHERE_PROJECTION_DETAILS_SID_IN
                    + Constant.SELECT_PROJECTION_DETAILS_SID_FROM_PROJE
                    + ccpDetails + "\n"
                    + Constant.AND_PROJECTION_MASTER + projectionId + ")"
                    + Constant.AND_USER_ID + userId + Constant.AND_SESSION_ID + sessionId + "\n";

            HelperTableLocalServiceUtil.executeUpdateQuery(customSql);
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(customSql);
            return false;
        }
    }

    public boolean saveDiscountProjectionListView(int projectionId, String userId, String sessionId, String frequency, int period, int year, String hierarchyIndicator,
            int levelNo, String hierarchyNo, String discountName, String fieldValue, boolean isProgram, boolean isCustomHierarchy, List<String> customViewDetails, String relationshipBuilderSid) {

        String customSql = StringUtils.EMPTY;
        LOGGER.debug(" entering saveDiscountProjectionListView");
        try {

            String refreshName = customViewDetails.get(7);
            String hierarchy = "";
            if (hierarchyIndicator.equals("C")) {
                hierarchy = Constant.PROJECTION_CUST_HIERARCHY;
            } else if (hierarchyIndicator.equals("P")) {
                hierarchy = Constant.PROJECTION_PROD_HIERARCHY;
            }
            int periodToSave = period;
            int yearToSave = year;

            int startMonth = 0;
            int endMonth = 0;
            if (frequency.equals(ANNUALLY.getConstant())) {
                startMonth = 1;
                endMonth = 12;
            }
            if (frequency.equals(QUARTERLY.getConstant())) {
                switch (periodToSave) {
                    case 1:
                        startMonth = 1;
                        endMonth = 3;
                        break;
                    case 2:
                        startMonth = 4;
                        endMonth = 6;
                        break;
                    case 3:
                        startMonth = 7;
                        endMonth = 9;
                        break;
                    case 4:
                        startMonth = 10;
                        endMonth = 12;
                        break;
                    default:
                            break;

                }
            }
            if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
                switch (periodToSave) {
                    case 1:
                        startMonth = 1;
                        endMonth = 6;
                        break;
                    case 2:
                        startMonth = 7;
                        endMonth = 12;
                        break;
                    default:
                            break;

                }
            }
            if (frequency.equals(MONTHLY.getConstant())) {
                startMonth = periodToSave;
                endMonth = periodToSave;
            }

            String discountType = "M.PRICE_GROUP_TYPE";
            if (isProgram) {
                discountType = "RS.RS_NAME";
            }

            String declareStatement = Constant.DECLARE_START_MONTH_INT + startMonth + "\n"
                    + Constant.DECLARE_START_YEAR_INT + yearToSave + "\n"
                    + Constant.DECLARE_END_MONTH_INT + endMonth + "\n"
                    + Constant.DECLARE_END_YEAR_INT + yearToSave + "\n";

            String ccpDetails = "";
            if (isCustomHierarchy && customViewDetails != null && !customViewDetails.isEmpty()) {

                String customId = customViewDetails.get(0);
                String customerLevelNo = customViewDetails.get(1);
                String customerHierarchyNo = customViewDetails.get(2);
                String productLevelNo = customViewDetails.get(3);
                String productHierarchyNo = customViewDetails.get(4);
                String relationshipBuilderSidForCustomer = customViewDetails.get(5);
                String relationshipBuilderSidForProduct = customViewDetails.get(6);

                ccpDetails = " (SELECT distinct HLD" + hierarchyIndicator + ".RELATIONSHIP_LEVEL_SID,HLD" + hierarchyIndicator + ".HIERARCHY_NO, CCPMAPC.CCP_DETAILS_SID FROM \n"
                        + Constant.SELECT_RLDRELATIONSHIP_LEVEL_VALUES_R
                        + Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION_RL
                        + Constant.JOIN_CCP_MAP_CCP_ON_RLDRELATIONSHIP_L
                        + Constant.AND_RLDRELATIONSHIP_BUILDER_SID + relationshipBuilderSidForCustomer + "\n"
                        + Constant.JOIN_PROJECTION_DETAILS_PD_ON_PDCCP_D + projectionId + Constant.CCPMAPC
                        + Constant.JOIN_SELECT_RLDRELATIONSHIP_LEVEL_VAL
                        + Constant.FROM_RELATIONSHIP_LEVEL_DEFINITION
                        + Constant.JOIN_CCP_MAP_CCP_ON_RLDRELATION
                        + Constant.AND_RLDRELATIONSHIP_BUILDER_SID + relationshipBuilderSidForProduct + "\n"
                        + Constant.JOIN_PROJECTION_DETAILS_PD_ON_PDC + projectionId + Constant.CCP_MAP
                        + Constant.ON_CCPMAPCCCP_DETAILS_SIDCCPMAPPCCP_D
                        + Constant.SPACE_JOIN_NEW_LINE
                        + Constant.SELECT_RL_D2_HIERARCHY_NORLD2_RELATIONSH
                        + Constant.JOIN_DBO_CUSTOM_VIEW_MASTER_CVM_ON_CUSTOM_VIEW_MASTER + customId + Constant.AND_CVD_LEVEL_NO_LIKE + customerLevelNo + "'\n"
                        + Constant.JOIN_DBO_HIERARCHY_LEVEL_DEFINITION
                        + Constant.JOIN_RELATIONSHIP_LEVEL_DEFINITIO
                        + Constant.JOIN_PROJECTION_CUST_HIERARCHY_PC + projectionId + "\n"
                        + Constant.WHERE_RL_D2_HIERARCHY_NO_LIKE + customerHierarchyNo + Constant.HLDC_ON_CCPMAP_HIERARCHY_NO_LIKE_HLDCHIE
                        + Constant.SPACE_JOIN_NEW_LINE
                        + Constant.SELECT_RL_D2_HIERARCHY_NORLD2_RELATIONSH
                        + Constant.JOIN_DBO_CUSTOM_VIEW_MASTER_CVM_ON_CUSTOM_VIEW_MASTER + customId + Constant.AND_CVD_LEVEL_NO_LIKE + productLevelNo + "'\n"
                        + Constant.JOIN_DBO_HIERARCHY_LEVEL_DEFINITION
                        + Constant.JOIN_RELATIONSHIP_LEVEL_DEFINITIO
                        + Constant.JOIN_PROJECTION_PROD_HIERARCHY_PC + projectionId + "\n"
                        + Constant.WHERE_RL_D2_HIERARCHY_NO_LIKE + productHierarchyNo + Constant.HLDP_ON_CCPMAP_HIERARCHY_NO_LIKE_HLDPHIE
                        + Constant.WHERE_HLD + hierarchyIndicator + Constant.DOT_HIERARCHY_NO + hierarchyNo + "' \n"
                        + Constant.CLOSE_BRACKET_CCP;

            } else {

                ccpDetails = "(SELECT CCPMAP.CCP_DETAILS_SID,\n"
                        + "                         HLD.HIERARCHY_NO,\n"
                        + "                         HLD.RELATIONSHIP_LEVEL_SID\n"
                        + Constant.FROM_SELECT_RLD_HIERAR
                        + Constant.CCP_CCP_D
                        + Constant.FROM_RELATION
                        + Constant.JOIN_CCP_MAP + relationshipBuilderSid + " \n"
                        + Constant.JOIN_PROJECTION + projectionId + "\n"
                        + Constant.JOIN + hierarchy + Constant.PCH_ON_PCHRELATIONSHIP_LEVEL_SID_RLDRE
                        + Constant.AND_PCHP + projectionId + "\n"
                        + Constant.CCPMAP_JOIN
                        + Constant.SELECT_RL_D1HIERA
                        + Constant.RL_D1RELA
                        + Constant.RL_D1_LEVEL
                        + Constant.FROM_RELATIONSHIP_RELATION
                        + Constant.SPACE_JOIN_SPACE + hierarchy + Constant.PCH_ON_PCH_RELATIONSHIP_LEVEL_SID_RL_D1 + projectionId + " \n"
                        + Constant.AND_RL_D1HIERARC + hierarchyNo + Constant.HLD_ON_CCPMAPHIERARCHY_NO_LIKE_HLDHIERAR
                        + Constant.WHERE_HLDHIERARCHY_NO + hierarchyNo + "') CCP";

            }

            if ("GROWTH".equals(refreshName)) {
                customSql = declareStatement + "\n UPDATE DPT SET DPT.GROWTH = " + fieldValue + " FROM ST_NM_DISCOUNT_PROJECTION DPT, \n";
            } else {
                customSql = declareStatement + "\n UPDATE DPT SET DPT.REFRESHED_VALUE = " + fieldValue + ", DPT.REFRESHED_NAME = '" + refreshName + "'" + "  FROM ST_NM_DISCOUNT_PROJECTION DPT, \n";
            }
            customSql += "(SELECT M.PROJECTION_DETAILS_SID, DP.PERIOD_SID , M.RS_MODEL_SID FROM ST_NM_DISCOUNT_PROJ_MASTER M, PROJECTION_DETAILS B,\n "
                    + " ST_NM_DISCOUNT_PROJECTION DP, RS_MODEL RS, \"PERIOD\" P, \n" + ccpDetails
                    + Constant.WHERE_MPROJECTION_DETAILS_SID
                    + Constant.AND_MUSER_ID + userId + "\n"
                    + Constant.AND_M_SESSION_ID + sessionId + "\n"
                    + Constant.AND_DP_USER_ID + userId + "\n"
                    + Constant.AND_DP_SESSION_ID + sessionId + "\n"
                    + " and DP.RS_MODEL_SID = M.RS_MODEL_SID \n"
                    + " AND B.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID \n"
                    + " AND DP.PROJECTION_DETAILS_SID = M.PROJECTION_DETAILS_SID \n"
                    + " AND RS.RS_MODEL_SID = M.RS_MODEL_SID \n"
                    + Constant.SPACE_AND_SPACE + discountType + " = '" + discountName + "' \n"
                    + " AND DP.PERIOD_SID = P.PERIOD_SID \n"
                    + " AND P.PERIOD_DATE " + getPeriodFilter() + "\n "
                    + Constant.AND_BPROJECTION_MASTER_SID + projectionId + "' \n"
                    + ") A "
                    + " WHERE DPT.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID "
                    + " AND DPT.RS_MODEL_SID = A.RS_MODEL_SID \n"
                    + " AND DPT.PERIOD_SID = A.PERIOD_SID \n"
                    + " AND DPT.USER_ID = " + userId + "\n"
                    + " AND DPT.SESSION_ID = " + sessionId + "\n";

            HelperTableLocalServiceUtil.executeUpdateQuery(customSql);
            return BooleanConstant.getTrueFlag();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(customSql);
            return BooleanConstant.getFalseFlag();
        }

    }
    

    public List getDiscountProjectionResults(List<Integer> discountprojectionId, String frequency, String discountString, String actualsOrProjections, String view, String order, List<Integer> startAndEndPeriods, int userId, int sessionId, boolean viewFlag) {

        String tableName = viewFlag ? StringUtils.EMPTY : "ST_";
        String projectionQuery = "";
        try {
            String idString = "";
            StringBuilder idStringBuilder = new StringBuilder();
            for (int i = 0; i < discountprojectionId.size(); i++) {
                if (i != discountprojectionId.size() - 1) {
                    //idString = idString + discountprojectionId.get(i) + ",";
                    idStringBuilder.append(discountprojectionId.get(i) ).append( ',');
                } else {
                    //idString = idString + discountprojectionId.get(i);
                    idStringBuilder.append(discountprojectionId.get(i));
                }
            }
            idString = idStringBuilder.toString();
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

                Calendar calendar = Calendar.getInstance();
                int month = calendar.get(Calendar.MONTH) + 1;
                calendar.add(Calendar.MONTH, -((month % 3) == 0 ? 3 : (month % 3)));
                heMonth = String.valueOf((calendar.get(Calendar.MONTH) + 1));
                heYear = String.valueOf(calendar.get(Calendar.YEAR));
                if (heMonth.length() == 1) {
                    heMonth = "0" + heMonth;
                }
                endPeriod = heYear + heMonth;

                calendar = Calendar.getInstance();
                month = calendar.get(Calendar.MONTH) + 1;
                calendar.add(Calendar.MONTH, -((month % 3) == 0 ? 3 : (month % 3)));
                fsMonth = String.valueOf((calendar.get(Calendar.MONTH) + 2));
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
            if (frequency.equals(Constant.ANNUALLY)) {
                frequency = "YEAR";
            }
            if (frequency.equals(Constant.SEMIANNUALLY)) {
                frequency = Constant.SEMI_ANNUAL;

            }
            if (frequency.equals(MONTHLY.getConstant())) {
                frequency = Constant.MONTH_WITHOUT_SPACE;
            }

            projectionQuery = Constant.SELECT_PR_YEAR_PR + frequency + Constant.AS_BASE_000_AS_ACTUAL_SALES_000_AS_ACTUAL
                    + Constant.AS_PROJECTION_SALES_SUM_NM_DP_PROJECTION_SAL + frequency + ",PR.MONTH,SUM(NMDP.PROJECTION_RATE) AS PROJECTION_RATE,0.0 AS ACTUAL_UNITS,"
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
                    + Constant.AND_RS_M_RS_NAME_IN + discountString + ")"
                    + " GROUP BY PR.YEAR,PR." + frequency + ",PR.MONTH,PD.PROJECTION_DETAILS_SID"
                    + Constant.UNION_ALL
                    + Constant.SELECT_PR_YEAR_PR + frequency + Constant.AS_BASE_MAX_NM_AS_ACTUAL_SALES
                    + Constant.AS_ACTUAL_DISCOUNT_MAX_IS_NULL_NM_SP
                    + Constant.AS_PROJECTION_DISCOUNT_PR + frequency + ",PR.MONTH,SUM(NMDP.PROJECTION_RATE) AS PROJECTION_RATE,"
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
                    + Constant.AND_RS_M_RS_NAME_IN + discountString + ")"
                    + Constant.GROUP_BY_PR_YEAR + frequency + ",PR.MONTH,PD.PROJECTION_DETAILS_SID ";
            if (view.equalsIgnoreCase("parent")) {
                if (frequency.equals("YEAR") || frequency.equals(Constant.MONTH_WITHOUT_SPACE)) {
                    projectionQuery = projectionQuery + "ORDER BY PR.YEAR,PR.MONTH";
                } else {
                    projectionQuery = projectionQuery + "ORDER BY PR.YEAR,PR." + frequency + Constant.PR_MONTH;
                }
            } else if (frequency.equals("YEAR") || frequency.equals(Constant.MONTH_WITHOUT_SPACE)) {
                projectionQuery = projectionQuery + "ORDER BY RSM.RS_NAME,PR.YEAR,PR.MONTH";
            } else {
                projectionQuery = projectionQuery + "ORDER BY RSM.RS_NAME,PR.YEAR,PR." + frequency + Constant.PR_MONTH;
            }
            return HelperTableLocalServiceUtil.executeSelectQuery(projectionQuery);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(projectionQuery);
        }
        return null;
    }

    public List getChildDiscount(List<Integer> discountprojectionId, String projection, List<Integer> startAndEndPeriods, String frequency, int userId, int sessionId) {

        String sql = "";
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
                    //idString = idString + discountprojectionId.get(i) + ",";
                    idStringBuilder.append(discountprojectionId.get(i) ).append( ',');
                } else {
                    //idString = idString + discountprojectionId.get(i);
                    idStringBuilder.append(discountprojectionId.get(i));
                }
            }
            idString = idStringBuilder.toString();
            if (startAndEndPeriods != null && startAndEndPeriods.size() != 0) {
                startFreq = startAndEndPeriods.get(0);
                endFreq = startAndEndPeriods.get(1);
                startYear = startAndEndPeriods.get(2);
                endYear = startAndEndPeriods.get(3);
            }

            if (frequency.equals(QUARTERLY.getConstant())) {
                frequency = Constant.QUARTER;

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
            if (frequency.equals(Constant.ANNUALLY)) {
                frequency = "YEAR";
                startMonth = 1;
                endMonth = 12;
            }
            if (frequency.equals(Constant.SEMIANNUALLY)) {
                frequency = Constant.SEMI_ANNUAL;
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
                frequency = Constant.MONTH_WITHOUT_SPACE;
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
                sql = "select D.YEAR,D." + frequency + ",sum(B.ACTUAL_SALES) As ACTUAL_SALES,sum(B.ACTUAL_RATE) As ACTUAL_RATE from dbo.ST_NM_DISCOUNT_PROJ_MASTER A,dbo.ST_NM_ACTUAL_DISCOUNT B,dbo.PERIOD D,RS_MODEL J where A.PROJECTION_DETAILS_SID=B.PROJECTION_DETAILS_SID and J.RS_MODEL_SID=A.RS_MODEL_SID and A.USER_ID=" + userId + Constant.AND_B_USER_ID + userId + Constant.AND_ASESSION_ID + sessionId + Constant.AND_B_SESSION_ID + sessionId + Constant.AND_A_PROJECTION_DETAILS_SID_IN + idString + ") " + periodFilter + " group by D.YEAR,D." + frequency + Constant.ORDER_BY_D_YEAR;
            }
            if (projection.equals(Constant.PROJECTIONS)) {
                sql = "select J.RS_NAME,D.YEAR,D." + frequency + ", sum(B.PROJECTION_SALES) As PROJECTION_SALES,sum(B.PROJECTION_RATE) As PROJECTION_RATE from dbo.ST_NM_DISCOUNT_PROJ_MASTER A,dbo.ST_NM_DISCOUNT_PROJECTION B,dbo.PERIOD D,RS_MODEL J where A.PROJECTION_DETAILS_SID=B.PROJECTION_DETAILS_SID and J.RS_MODEL_SID=A.RS_MODEL_SID and A.USER_ID=" + userId + Constant.AND_B_USER_ID + userId + Constant.AND_ASESSION_ID + sessionId + Constant.AND_B_SESSION_ID + sessionId + Constant.AND_A_PROJECTION_DETAILS_SID_IN + idString + ")" + periodFilter + " group by J.RS_NAME,D.YEAR,D." + frequency + Constant.ORDER_BY_D_YEAR;
            }
            if (projection.equals("Both")) {
                sql = "select J.RS_NAME,D.YEAR,D." + frequency + ", sum(B.PROJECTION_SALES) As PROJECTION_SALES,sum(B.PROJECTION_RATE) As PROJECTION_RATE,sum(C.ACTUAL_SALES) As ACTUAL_SALES,sum(C.ACTUAL_RATE) As ACTUAL_RATE from dbo.ST_NM_DISCOUNT_PROJ_MASTER A,dbo.ST_NM_DISCOUNT_PROJECTION B,dbo.ST_NM_ACTUAL_DISCOUNT C,dbo.PERIOD D,RS_MODEL J where A.PROJECTION_DETAILS_SID=B.PROJECTION_DETAILS_SID and J.RS_MODEL_SID=A.RS_MODEL_SID and A.PROJECTION_DETAILS_SID=C.PROJECTION_DETAILS_SID and A.USER_ID=" + userId + Constant.AND_B_USER_ID + userId + " and C.USER_ID=" + userId + Constant.AND_ASESSION_ID + sessionId + Constant.AND_B_SESSION_ID + sessionId + " and C.SESSION_ID=" + sessionId + Constant.AND_A_PROJECTION_DETAILS_SID_IN + idString + ") " + periodFilter + " group by J.RS_NAME,D.YEAR,D." + frequency + Constant.ORDER_BY_D_YEAR;
            }
            sql = declareStatement + sql;
            return HelperTableLocalServiceUtil.executeSelectQuery(sql);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
        }
        return null;
    }

    public List getVarianceDiscount(int projectionId, String frequency, List<Integer> startAndEndPeriods, String discountTotal, String parentName, List<String> discountList, String year, int levelNo, String sales) {

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
            int totalFlag = 0;

            if (startAndEndPeriods != null && !startAndEndPeriods.isEmpty()) {
                totalFlag = startAndEndPeriods.get(0);
                startFreq = startAndEndPeriods.get(1);
                endFreq = startAndEndPeriods.get(2);
                startYear = startAndEndPeriods.get(3);
                endYear = startAndEndPeriods.get(4);
            }
            if (CommonUtils.isInteger(year)) {

                whereClause += " and I.\"YEAR\" = " + year;
            }
            if (frequency.contains(QUARTERLY.getConstant())) {
                selectedHistoryFrequency = "QUARTER = " + startFreq + Constant.SPACE_AND_SPACE;
                selectedFutureFrequency = "QUARTER = " + endFreq + Constant.SPACE_AND_SPACE;
                selectClause += "I.QUARTER, ";
                whereClause += "";
                groupBy += Constant.I_QUARTER;
            } else if (frequency.contains(SEMI_ANNUALLY.getConstant())) {
                selectedHistoryFrequency = "SEMI_ANNUAL = " + startFreq + Constant.SPACE_AND_SPACE;
                selectedFutureFrequency = "SEMI_ANNUAL = " + endFreq + Constant.SPACE_AND_SPACE;
                selectClause += "I.SEMI_ANNUAL, ";
                whereClause += "";
                groupBy += Constant.I_SEMI_ANNUAL;
            } else if (frequency.contains(ANNUALLY.getConstant())) {
                selectClause += "'' as ANNUAL, ";
                whereClause += "";
                groupBy += "";

            } else if (frequency.contains(MONTHLY.getConstant())) {
                selectedHistoryFrequency = "\"MONTH\" = " + startFreq + Constant.SPACE_AND_SPACE;
                selectedFutureFrequency = "\"MONTH\" = " + endFreq + Constant.SPACE_AND_SPACE;
                selectClause += "I.\"MONTH\", ";
                whereClause += "";
                groupBy += Constant.I_MONTH;
            }

            String periodFilter = "";
            if (!CommonUtils.isInteger(year)) {
                periodFilter = " and  I.PERIOD_SID in (SELECT period_sid FROM \"PERIOD\" WHERE PERIOD_DATE>=(SELECT TOP 1 PERIOD_DATE FROM \"PERIOD\" WHERE " + selectedHistoryFrequency + " YEAR=" + startYear + " ORDER BY PERIOD_SID)"
                        + "AND PERIOD_DATE<=(SELECT TOP 1 PERIOD_DATE FROM \"PERIOD\" WHERE " + selectedFutureFrequency + " YEAR=" + endYear + " ORDER BY PERIOD_SID DESC))";
            }

            String discountTypeColumnName = "'All_Discounts' as DISCOUNTS";
            if (discountList != null && !discountList.isEmpty()) {
                if (!discountTotal.equalsIgnoreCase("Total")) {
                    discountTypeColumnName = " B.PRICE_GROUP_TYPE ";
                    groupBy += ", " + discountTypeColumnName;
                }
                String selectedDiscounts;
                StringBuilder selectedDiscountsBuilder = new StringBuilder();
                String comma = ", ";

                for (int i = 0; i < discountList.size(); i++) {
                    if (i != 0) {
                        //selectedDiscounts += comma;
                        selectedDiscountsBuilder.append(comma);
                    }
                    //selectedDiscounts += "'" + discountList.get(i) + "'";
                    selectedDiscountsBuilder.append('\'' ).append( discountList.get(i) ).append( '\'');
                }
                selectedDiscounts = selectedDiscountsBuilder.toString();
                whereClause += " and B.PRICE_GROUP_TYPE in (" + selectedDiscounts + ")";

            }

            selectClause += discountTypeColumnName + ", ";

            String parentNode = parentName;
            String customSql = "  ST_NM_DISCOUNT_PROJ_MASTER B, PROJECTION_DETAILS E , PROJECTION_MASTER F, PROJECTION_CUST_HIERARCHY G, RELATIONSHIP_LEVEL_DEFINITION H, \"PERIOD\" I "
                    + "where A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID and B.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID and "
                    + "E.PROJECTION_MASTER_SID = F.PROJECTION_MASTER_SID and F.PROJECTION_MASTER_SID = " + projectionId + " and G.PROJECTION_MASTER_SID = F.PROJECTION_MASTER_SID "
                    + "and G.RELATIONSHIP_LEVEL_SID = H.RELATIONSHIP_LEVEL_SID and A.PERIOD_SID = I.PERIOD_SID " + periodFilter;
            if (!parentName.equals(StringUtils.EMPTY) || !StringUtils.EMPTY.equalsIgnoreCase(parentName)) {
                customSql += " and H.LEVEL_NO = " + levelNo + " and H.PARENT_NODE = '" + parentNode + "'";
            }
            customSql += whereClause + " group by H.LEVEL_NO , H.RELATIONSHIP_LEVEL_VALUES " + groupBy;

            String historyQuery = selectClause + " sum(A.ACTUAL_" + sales + ")as ACTUAL_" + sales + ", sum(A.ACTUAL_PROJECTION_" + sales + ") as PROJECTION_" + sales + " from ST_NM_ACTUAL_DISCOUNT A," + customSql;
            String futureQuery = selectClause + " NULL as ACTUAL_" + sales + ", sum(A.PROJECTION_" + sales + ") as PROJECTION_" + sales + " from ST_NM_DISCOUNT_PROJECTION A," + customSql;

            customQuery = historyQuery + " Union " + futureQuery;
            return HelperTableLocalServiceUtil.executeSelectQuery(customQuery);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(customQuery);
            return null;
        }
    }

    public List getTotalDiscountNumber(List<Integer> projectionDetailsId, String frequency, String discountName, List<Integer> startAndEndPeriods, int userId, int sessionId, List<Object> view) {

        String projectionQuery = "";
        try {
            String idString;
            StringBuilder idStringBuilder = new StringBuilder();
            for (int i = 0; i < projectionDetailsId.size(); i++) {
                if (i != projectionDetailsId.size() - 1) {
                    //idString = idString + projectionDetailsId.get(i) + ",";
                    idStringBuilder.append(projectionDetailsId.get(i) ).append( ',');
                } else {
                    //idString = idString + projectionDetailsId.get(i);
                    idStringBuilder.append(projectionDetailsId.get(i));
                }
            }
            idString = idStringBuilder.toString();
            String startPeriod = "";
            String endPeriod = "";
            String forecastStartPeriod = "";
            String forecastEndPeriod = "";
            if (startAndEndPeriods != null && startAndEndPeriods.size() != 0) {
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
            if (frequency.equals(QUARTERLY.getConstant())) {
                frequency = Constant.QUARTER;
            }
            if (frequency.equals(Constant.ANNUALLY)) {
                frequency = "YEAR";
            }
            if (frequency.equals(Constant.SEMIANNUALLY)) {
                frequency = Constant.SEMI_ANNUAL;

            }
            if (frequency.equals(MONTHLY.getConstant())) {
                frequency = Constant.MONTH_WITHOUT_SPACE;
            }
            projectionQuery = Constant.SELECT_PR_YEAR_PR + frequency + Constant.AS_BASE_000_AS_ACTUAL_SALES_000_AS_ACTUAL
                    + Constant.AS_PROJECTION_SALES_SUM_NM_DP_PROJECTION_SAL + frequency + Constant.PR_MONTH_RS_M_RS_NAME
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
                    + " GROUP BY PR.YEAR,PR." + frequency + ",PR.MONTH,PD.PROJECTION_DETAILS_SID,RSM.RS_NAME"
                    + Constant.UNION_ALL
                    + Constant.SELECT_PR_YEAR_PR + frequency + Constant.AS_BASE_MAX_NM_AS_ACTUAL_SALES
                    + Constant.AS_ACTUAL_DISCOUNT_MAX_IS_NULL_NM_SP
                    + Constant.AS_PROJECTION_DISCOUNT_PR + frequency + ",PR.MONTH, RSM.RS_NAME,SUM(NMDP.PROJECTION_RATE) AS PROJECTION_RATE,"
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
                    + Constant.GROUP_BY_PR_YEAR + frequency + Constant.PR_MONTH_PD_PROJECTION_DETAILS_SID_RS;

            if (frequency.equals("YEAR") || frequency.equals(Constant.MONTH_WITHOUT_SPACE)) {
                if (view.get(0) != null && "Descending".equalsIgnoreCase(String.valueOf(view.get(0)))) {
                    projectionQuery = projectionQuery + "ORDER BY RSM.RS_NAME,PR.YEAR DESC,PR.MONTH DESC";
                } else {
                    projectionQuery = projectionQuery + "ORDER BY RSM.RS_NAME,PR.YEAR,PR.MONTH";
                }
            } else if (view.get(0) != null && "Descending".equalsIgnoreCase(String.valueOf(view.get(0)))) {
                projectionQuery = projectionQuery + "ORDER BY RSM.RS_NAME,PR.YEAR DESC,PR." + frequency + " DESC,PR.MONTH DESC";
            } else {
                projectionQuery = projectionQuery + "ORDER BY RSM.RS_NAME,PR.YEAR,PR." + frequency + Constant.PR_MONTH;
            }

            return HelperTableLocalServiceUtil.executeSelectQuery(projectionQuery);
        } catch (Exception e) {

            LOGGER.error(e.getMessage());
            LOGGER.error(projectionQuery);
        }
        return null;
    }

    public List getSubDiscount(List<Integer> projectionDetailsId, String frequency, String discountList, List<Integer> startAndEndPeriods, int userId, int sessionId) {
        {

            String sql = "";
            try {
                String idString ;
                StringBuilder idStringBuilder = new StringBuilder();
                for (int i = 0; i < projectionDetailsId.size(); i++) {
                    if (i != projectionDetailsId.size() - 1) {
                        //idString = idString + projectionDetailsId.get(i) + ",";
                        idStringBuilder.append(projectionDetailsId.get(i) ).append( ',');
                    } else {
                        //idString = idString + projectionDetailsId.get(i);
                        idStringBuilder.append(projectionDetailsId.get(i));
                    }
                }
                idString = idStringBuilder.toString();
                String startPeriod = "";
                String endPeriod = "";
                String forecastStartPeriod = "";
                String forecastEndPeriod = "";
                if (startAndEndPeriods != null && startAndEndPeriods.size() != 0) {
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

                if (frequency.equals(QUARTERLY.getConstant())) {
                    frequency = Constant.QUARTER;
                }
                if (frequency.equals(Constant.ANNUALLY)) {
                    frequency = "YEAR";
                }
                if (frequency.equals(Constant.SEMIANNUALLY)) {
                    frequency = Constant.SEMI_ANNUAL;
                }
                if (frequency.equals(MONTHLY.getConstant())) {
                    frequency = Constant.MONTH_WITHOUT_SPACE;
                }
                sql = Constant.SELECT_PR_YEAR_PR + frequency + Constant.AS_BASE_000_AS_ACTUAL_SALES_000_AS_ACTUAL
                        + Constant.AS_PROJECTION_SALES_SUM_NM_DP_PROJECTION_SAL + frequency + Constant.PR_MONTH_RS_M_RS_NAME
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
                        + Constant.GROUP_BY_PR_YEAR + frequency + ",PR.MONTH,PD.PROJECTION_DETAILS_SID,RSM.RS_NAME "
                        + Constant.UNION_ALL
                        + Constant.SELECT_PR_YEAR_PR + frequency + Constant.AS_BASE_MAX_NM_AS_ACTUAL_SALES
                        + Constant.AS_ACTUAL_DISCOUNT_MAX_IS_NULL_NM_SP
                        + Constant.AS_PROJECTION_DISCOUNT_PR + frequency + ",PR.MONTH, RSM.RS_NAME, SUM(NMDP.PROJECTION_RATE) AS PROJECTION_RATE, Sum(ACTUAL_UNITS) as ACTUAL_UNITS,\n"
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
                        + Constant.GROUP_BY_PR_YEAR + frequency + Constant.PR_MONTH_PD_PROJECTION_DETAILS_SID_RS;

                sql = sql + " ORDER BY PR.YEAR,BASE,RSM.RS_NAME";

                return HelperTableLocalServiceUtil.executeSelectQuery(sql);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                LOGGER.error(sql);
            }
        }
        return null;
    }

    public List getTotalDiscountCount(int projectionMasterId, String frequency, String actualsOrProjections, List<Integer> startAndEndPeriods, int userId, int sessionId) {
        {

            String sql = "";
            try {

                int startFreq = 0;
                int endFreq = 0;
                int startYear = 0;
                int endYear = 0;
                String declareStatement = "";
                int startMonth = 0;
                int endMonth = 0;
                if (startAndEndPeriods != null && startAndEndPeriods.size() != 0) {
                    startFreq = startAndEndPeriods.get(0);
                    endFreq = startAndEndPeriods.get(1);
                    startYear = startAndEndPeriods.get(2);
                    endYear = startAndEndPeriods.get(3);
                }

                if (frequency.equals(QUARTERLY.getConstant())) {
                    frequency = Constant.QUARTER;
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
                if (frequency.equals(Constant.ANNUALLY)) {
                    frequency = "YEAR";
                    startMonth = 1;
                    endMonth = 12;
                }
                if (frequency.equals(Constant.SEMIANNUALLY)) {
                    frequency = Constant.SEMI_ANNUAL;
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
                    frequency = Constant.MONTH_WITHOUT_SPACE;
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
        }
        return null;
    }

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
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
        }
        return null;
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
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
        }
        return null;
    }

    public List getCCPDetailsIDForCustomHierarchy(int ProjectionMasterSid, String hierarchyNo, String customViewId) {

        String sql = StringUtils.EMPTY;
        try {
            int customViewNo = Integer.parseInt(customViewId);
            sql = "SELECT distinct CCPMAPC.CCP_DETAILS_SID FROM "
                    + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID"
                    + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID"
                    + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID='" + ProjectionMasterSid + "'"
                    + " ) CCPMAPC JOIN (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID FROM RELATIONSHIP_LEVEL_DEFINITION RLD "
                    + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID"
                    + " AND PD.PROJECTION_MASTER_SID='" + ProjectionMasterSid + "') CCPMAPP ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID JOIN (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, "
                    + " CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD JOIN dbo.CUSTOM_VIEW_MASTER CVM ON CVD.CUSTOM_VIEW_MASTER_SID=" + customViewNo + " AND CVD.LEVEL_NO  like '1'"
                    + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID"
                    + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID"
                    + " JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID='" + ProjectionMasterSid + "'"
                    + " WHERE RLD2.HIERARCHY_NO like '%') HLDC ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%' JOIN "
                    + " (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD JOIN dbo.CUSTOM_VIEW_MASTER CVM ON "
                    + " CVD.CUSTOM_VIEW_MASTER_SID=" + customViewNo + " AND CVD.LEVEL_NO like '%' JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID"
                    + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID "
                    + " JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID='" + ProjectionMasterSid + "'"
                    + " WHERE RLD2.HIERARCHY_NO like '%') HLDP ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%'";

            return HelperTableLocalServiceUtil.executeSelectQuery(sql);

        } catch (NumberFormatException e) {

            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
        }
        return null;
    }

    public List getAllPeriodDiscount(List<Integer> discountprojectionId, String frequency, String history, String actualsOrProjections, String view, String order, List<Integer> startAndEndPeriods) {

        String sql = "";
        try {

            String idString;
            StringBuilder idStringBuilder = new StringBuilder();
            int startFreq = 0;
            int endFreq = 0;
            int startYear = 0;
            int endYear = 0;
            String declareStatement = "";
            for (int i = 0; i < discountprojectionId.size(); i++) {
                if (i != discountprojectionId.size() - 1) {
                    //idString = idString + discountprojectionId.get(i) + ",";
                    idStringBuilder.append(discountprojectionId.get(i) ).append( ',');
                } else {
                    //idString = idString + discountprojectionId.get(i);
                    idStringBuilder.append(discountprojectionId.get(i));
                }
            }
            idString = idStringBuilder.toString();
            int startMonth = 0;
            int endMonth = 0;
            if (startAndEndPeriods != null && startAndEndPeriods.size() != 0) {
                startFreq = startAndEndPeriods.get(0);
                endFreq = startAndEndPeriods.get(1);
                startYear = startAndEndPeriods.get(2);
                endYear = startAndEndPeriods.get(3);
            }

            if (frequency.equals(QUARTERLY.getConstant())) {
                frequency = Constant.QUARTER;
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
            if (frequency.equals(Constant.ANNUALLY)) {
                frequency = "YEAR";
                startMonth = 1;
                endMonth = 12;
            }
            if (frequency.equals(Constant.SEMIANNUALLY)) {
                frequency = Constant.SEMI_ANNUAL;
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
                frequency = Constant.MONTH_WITHOUT_SPACE;
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
                sql = Constant.SELECT_JRS_NAME_YEAR + frequency + ", sum(C.ACTUAL_SALES) As ACTUAL_SALES,sum(C.ACTUAL_RATE) As ACTUAL_RATE from ST_NM_DISCOUNT_PROJ_MASTER A,ST_NM_DISCOUNT_PROJECTION B,ST_NM_ACTUAL_DISCOUNT C,PERIOD I,RS_MODEL J WHERE A.PROJECTION_DETAILS_SID=B.PROJECTION_DETAILS_SID and A.PROJECTION_DETAILS_SID=C.PROJECTION_DETAILS_SID and J.RS_MODEL_SID=A.RS_MODEL_SID and A.PROJECTION_DETAILS_SID in (" + idString + ") " + periodFilter + Constant.GROUP_BY_I + frequency + Constant.I_YEAR_RS_NAME_ORDER_BY_JRS_NAME;
            }
            if (actualsOrProjections.equals(Constant.PROJECTIONS)) {
                sql = Constant.SELECT_JRS_NAME_YEAR + frequency + ", sum(B.PROJECTION_SALES) As PROJECTION_SALES,sum(B.PROJECTION_RATE) As PROJECTION_RATE  from ST_NM_DISCOUNT_PROJ_MASTER A,ST_NM_DISCOUNT_PROJECTION B,ST_NM_ACTUAL_DISCOUNT C,PERIOD I,RS_MODEL J WHERE A.PROJECTION_DETAILS_SID=B.PROJECTION_DETAILS_SID and A.PROJECTION_DETAILS_SID=C.PROJECTION_DETAILS_SID and J.RS_MODEL_SID=A.RS_MODEL_SID and A.PROJECTION_DETAILS_SID in(" + idString + ") " + periodFilter + Constant.GROUP_BY_I + frequency + Constant.I_YEAR_RS_NAME_ORDER_BY_JRS_NAME;
            }
            if (actualsOrProjections.equals("Both")) {
                sql = Constant.SELECT_JRS_NAME_YEAR + frequency + ", sum(C.ACTUAL_SALES) As ACTUAL_SALES,sum(C.ACTUAL_RATE) As ACTUAL_RATE,sum(B.PROJECTION_SALES) As PROJECTION_SALES,sum(B.PROJECTION_RATE) As PROJECTION_RATE  from ST_NM_DISCOUNT_PROJ_MASTER A,ST_NM_DISCOUNT_PROJECTION B,ST_NM_ACTUAL_DISCOUNT C,PERIOD I,RS_MODEL J WHERE A.PROJECTION_DETAILS_SID=B.PROJECTION_DETAILS_SID and A.PROJECTION_DETAILS_SID=C.PROJECTION_DETAILS_SID and J.RS_MODEL_SID=A.RS_MODEL_SID and A.PROJECTION_DETAILS_SID in (" + idString + ") " + periodFilter + Constant.GROUP_BY_I + frequency + Constant.I_YEAR_RS_NAME_ORDER_BY_JRS_NAME;
            }
            sql = declareStatement + sql;

            return HelperTableLocalServiceUtil.executeSelectQuery(sql);
        } catch (Exception e) {

            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
        }
        return null;
    }

    public List getAllPesriodDiscount(List<Integer> discountprojectionId, String frequency, String discountName, String hist, String view, String order, List<Integer> startAndEndPeriods, int userId, int sessionId) {

        String sql = "";
        try {

            String idString;
            StringBuilder idStringBuilder = new StringBuilder();
            for (int i = 0; i < discountprojectionId.size(); i++) {
                if (i != discountprojectionId.size() - 1) {
                    //idString = idString + discountprojectionId.get(i) + ",";
                    idStringBuilder.append(discountprojectionId.get(i) ).append( ',');
                } else {
                    //idString = idString + discountprojectionId.get(i);
                    idStringBuilder.append(discountprojectionId.get(i));
                }
            }
            idString = idStringBuilder.toString();
            String startPeriod = "";
            String endPeriod = "";
            String forecastStartPeriod = "";
            String forecastEndPeriod = "";
            if (startAndEndPeriods != null && startAndEndPeriods.size() != 0) {
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

                Calendar calendar = Calendar.getInstance();
                int month = calendar.get(Calendar.MONTH) + 1;
                calendar.add(Calendar.MONTH, -((month % 3) == 0 ? 3 : (month % 3)));
                heMonth = String.valueOf((calendar.get(Calendar.MONTH) + 1));
                heYear = String.valueOf(calendar.get(Calendar.YEAR));
                if (heMonth.length() == 1) {
                    heMonth = "0" + heMonth;
                }
                endPeriod = heYear + heMonth;

                calendar = Calendar.getInstance();
                month = calendar.get(Calendar.MONTH) + 1;
                calendar.add(Calendar.MONTH, -((month % 3) == 0 ? 3 : (month % 3)));
                fsMonth = String.valueOf((calendar.get(Calendar.MONTH) + 2));
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
            if (frequency.equals(Constant.ANNUALLY)) {
                frequency = "YEAR";
            }
            if (frequency.equals(Constant.SEMIANNUALLY)) {
                frequency = Constant.SEMI_ANNUAL;
            }
            if (frequency.equals(MONTHLY.getConstant())) {
                frequency = Constant.MONTH_WITHOUT_SPACE;
            }
            sql = Constant.SELECT_PR_YEAR_PR + frequency + Constant.AS_BASE_000_AS_ACTUAL_SALES_000_AS_ACTUAL
                    + Constant.AS_PROJECTION_SALES_SUM_NM_DP_PROJECTION_SAL + frequency + Constant.PR_MONTH_RS_M_RS_NAME
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
                    + Constant.GROUP_BY_PR_YEAR + frequency + ",PR.MONTH,PD.PROJECTION_DETAILS_SID,RSM.RS_NAME "
                    + Constant.UNION_ALL
                    + Constant.SELECT_PR_YEAR_PR + frequency + Constant.AS_BASE_MAX_NM_AS_ACTUAL_SALES
                    + Constant.AS_ACTUAL_DISCOUNT_MAX_IS_NULL_NM_SP
                    + Constant.AS_PROJECTION_DISCOUNT_PR + frequency + ",PR.MONTH, RSM.RS_NAME, SUM(NMDP.PROJECTION_RATE) AS PROJECTION_RATE, Sum(ACTUAL_UNITS) as ACTUAL_UNITS,\n"
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
                    + Constant.GROUP_BY_PR_YEAR + frequency + Constant.PR_MONTH_PD_PROJECTION_DETAILS_SID_RS;

            if (frequency.equals("YEAR") || frequency.equals(Constant.MONTH_WITHOUT_SPACE)) {
                sql = sql + " ORDER BY RSM.RS_NAME,PR.YEAR,PR.MONTH";
            } else {
                sql = sql + " ORDER BY RSM.RS_NAME,PR.YEAR,PR." + frequency + Constant.PR_MONTH;
            }

            return HelperTableLocalServiceUtil.executeSelectQuery(sql);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
        }
        return null;
    }
}
