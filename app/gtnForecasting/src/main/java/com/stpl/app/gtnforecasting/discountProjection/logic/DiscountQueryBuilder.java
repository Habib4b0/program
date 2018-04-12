/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.discountProjection.logic;

import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.FrequencyConstants.*;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.ifs.util.constants.BooleanConstant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class just copied from NmDiscountProjMasterFinderImpl.
 *
 * @author Sooriya.Lakshmanan
 */
public class DiscountQueryBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiscountQueryBuilder.class);
    

    public static final String PROGRAM = "Program";
    public static final String PROGRAM_CATEGORY = "Program Category";
    private final CommonLogic commonLogic = new CommonLogic();
    public static final String SELECTED_REBATE_AT = "@SELECTED_REBATE";
    public static final String WHERE_USER_GROUP = " WHERE USER_GROUP = '";
    public static final String EACH = "EACH";
    public static final String PERIOD_TABLE = "@PERIOD_TABLE";
    private static final String BUILDER_SID = "@BUILDERSID";
    private static final String RELATION_SID = "@RELATIONSIDS";
    private static final String LEVEL_NO = "@LEVNO";
    private static final String UOM_JOIN = "@UOMJOIN";
    public static final String RELVALUE = "@RELVALUE";
    public static final String DEDJOIN = "@DEDJOIN";
    public static final String DEDTABLE = "@DEDTABLE";
    public static final String CROSS_APPLY_SELECT_TOKEN_FROM_UDF_SPLITST = "CROSS APPLY (SELECT DISTINCT TOKEN FROM UDF_SPLITSTRING('";
    public static final String CONCAT_CONDITION = "', ',') C WHERE CH.PROD_HIERARCHY_NO LIKE concat(C.TOKEN , '%')) FN";
    public static final String DEDCUST_JOIN = "@DEDCUSTJOIN";
    public static final String SELECTED_HIERARCHY_JOIN = "[?SELECTED_HIERARCHY_JOIN]";
    public static final String RS_JOIN = "@RSJOIN";
    private static final String REL_COLUMN = "@REL_COLUMN";
    private static final String MANUAL_ENTRY_COUNT = " / ( CASE WHEN @REFRESHED_NAME = 'AMOUNT' THEN AMOUNT_COUNT * @PERIOD_COUNT ELSE RATE_RPU_COUNT END) ";
   
    public boolean updateInputsForAdjustment(String frequency, String levelType, String adjustmentType, String adjustmentBasis,
            String adjustmentValue, String allocationMethodology, Map<String, Map<String, List<String>>> periodsMap) {

        String masterTableUpdateQuery = "";
        String discountProjectionTableUpdateQuery = StringUtils.EMPTY;
        LOGGER.debug(" entering updateInputsForAdjustment");
        try {

            // To updated DISCOUNT_PROJ_MASTER Table
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
                masterTableUpdateQuery = "UPDATE DM SET DM.BASELINE_PERIODS = '" + baselinePeriods + "', DM.SELECTED_PERIODS = '" + selectedPeriods + "' FROM ST_NM_DISCOUNT_PROJ_MASTER DM ";
                discountName = discountName.contains("~") ? discountName.split("~")[0] : discountName ;
                if (levelType.equals(Constants.PROGRAM)) {
                    masterTableUpdateQuery += " JOIN  RS_CONTRACT RS ON RS.RS_CONTRACT_SID = DM.RS_CONTRACT_SID \n"
                            + "WHERE RS.RS_NAME = '" + discountName + "' AND DM.CHECK_RECORD = 1";
                } else {
                    masterTableUpdateQuery += " WHERE  DM.PRICE_GROUP_TYPE = '" + discountName + "' AND DM.CHECK_RECORD = 1";
                }

            }

            // For updating DISCOUNT_PROJECTION Table 
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

            String rsQuery = levelType.equals(Constants.PROGRAM) ? " RS.RS_NAME " : " DPM.PRICE_GROUP_TYPE ";
            for (String discountName : periodsMap.keySet()) {
                String selectedPeriodsToUpdate = CommonUtils.CollectionToString(periodsMap.get(discountName).get("P"), false);
                selectedPeriodsToUpdate = CommonUtils.replaceIntegerForMonth(selectedPeriodsToUpdate);
                selectedPeriodsToUpdate = selectedPeriodsToUpdate.replace("Q", "").replace("S", "").replace(" ", "");

                discountProjectionTableUpdateQuery = "UPDATE DP "
                        + " SET ADJUSTMENT_TYPE='" + adjustmentType + "' , ADJUSTMENT_BASIS='" + adjustmentBasis + "', ADJUSTMENT_VALUE=" + adjustmentValue + ", "
                        + " ADJUSTMENT_METHODOLOGY = '" + allocationMethodology + "' FROM ST_NM_DISCOUNT_PROJECTION DP"
                        + " JOIN ST_NM_DISCOUNT_PROJ_MASTER DPM on DPM.CCP_DETAILS_SID = DP.CCP_DETAILS_SID ";

                if (levelType.equals(Constants.PROGRAM)) {
                    discountProjectionTableUpdateQuery += " AND DP.RS_CONTRACT_SID = DPM.RS_CONTRACT_SID JOIN RS_CONTRACT RS ON DPM.RS_CONTRACT_SID = RS.RS_CONTRACT_SID ";
                }

                discountName = discountName.contains("~") ? discountName.split("~")[0] : discountName ;
                discountProjectionTableUpdateQuery += "WHERE  DPM.CHECK_RECORD = 1\n"
                        + "       AND "+rsQuery+" = '" + discountName + "'\n"
                        + "       AND DP.PERIOD_SID IN(SELECT PERIOD_SID from \"PERIOD\" PR WHERE " + period + " IN (" + selectedPeriodsToUpdate + ")) ";



            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(masterTableUpdateQuery);
            LOGGER.error(discountProjectionTableUpdateQuery);
            return false;
        }
        return true;
    }

    
    public void checkClearAll(SessionDTO session, String userGroup, boolean checkValue) {
        String query = StringUtils.EMPTY;
        LOGGER.debug(" inside checkClearAll");
        try {
            int check = checkValue ? 1 : 0;

            query = "UPDATE M SET CHECK_RECORD = " + check
                    + " From ST_NM_DISCOUNT_PROJ_MASTER M WHERE FILTER_CCP = 1 ";
            if(!userGroup.isEmpty()){
                query += " and M.USER_GROUP = '" + userGroup + "'\n";
            }
            HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query,session.getCurrentTableNames()));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(query);
        }
    }

    public int updateCheckRecord(SessionDTO session, boolean checkValue, String hierarchyNo,
            String hierarchyIndicator, boolean isCustomView, List<String> customViewDetails, boolean isProgram, List<String> discountList,String discountRelationLevel) {
        String customSql = "";
        LOGGER.debug(" inside updateCheckRecord");
        try {

            if (isCustomView && CommonUtil.isValueEligibleForLoading()) {
                customSql = SQlUtil.getQuery("UPDATE_CUSTOM_CHECKRECORD").replace(Constant.CHECK_RECORD_REFERENCE, String.valueOf(checkValue ? 1 : 0))
                        .replace(Constant.HIERARCHY_COLUMN, commonLogic.getColumnNameCustomRel(hierarchyIndicator,hierarchyNo,session))
                        .replace(RELVALUE, session.getDedRelationshipBuilderSid())
                        .replace(Constant.RELVERSION, String.valueOf(session.getDeductionRelationVersion()));
            } else if (discountList != null && !discountList.isEmpty()) {
                String selectedDiscounts = getRSDiscountSids(discountList);
                if (isProgram) {

                    customSql = SQlUtil.getQuery("UPDATE_PROGRAM_CHECKRECORD").replace(Constant.CHECK_RECORD_REFERENCE, String.valueOf(checkValue ? 1 : 0))
                            .replace(Constant.HIERARCHY_COLUMN, commonLogic.getColumnNameCustom(hierarchyIndicator))
                            .replace(Constant.HIERARCHY_NO, hierarchyNo)
                            .replace(Constant.PROGRAMS_REF, selectedDiscounts);

                } else {
                    if (!CommonUtil.isValueEligibleForLoading()) {
                        customSql = SQlUtil.getQuery("UPDATE_PROGRAM_CATEGORY_CHECKRECORD").replace(Constant.CHECK_RECORD_REFERENCE, String.valueOf(checkValue ? 1 : 0))
                                .replace(Constant.HIERARCHY_COLUMN, commonLogic.getColumnNameCustom(hierarchyIndicator))
                                .replace(Constant.HIERARCHY_NO, hierarchyNo)
                                .replace("@PROGRAM_CATEGORY", selectedDiscounts);
                    } else {
                        customSql = SQlUtil.getQuery("UPDATE_ALL_LEVEL_CHECKRECORD").replace(Constant.CHECK_RECORD_REFERENCE, String.valueOf(checkValue ? 1 : 0))
                                .replace(Constant.HIERARCHY_COLUMN, commonLogic.getColumnNameCustom(hierarchyIndicator))
                                .replace(Constant.HIERARCHY_NO, hierarchyNo);
                                
                         if (!discountRelationLevel.isEmpty()) {
                            customSql = customSql.replace(DEDTABLE, SQlUtil.getQuery("UPDATE_ALL_LEVEL_HEAD_CHECKRECORD")).replace(RELATION_SID, discountRelationLevel).replace(LEVEL_NO, String.valueOf(session.getSelectedDeductionLevelNo()))
                                .replace(BUILDER_SID, session.getDedRelationshipBuilderSid())
                                .replace(Constant.RELVERSION, String.valueOf(session.getDeductionRelationVersion()));
                            customSql = customSql.replace(DEDJOIN, " JOIN #DED_HIERARCHY_NO DH ON MAS.DEDUCTION_HIERARCHY_NO like DH.HIERARCHY_NO+'%' ");
                        } else {
                            customSql = customSql.replace(DEDJOIN, StringUtils.EMPTY);
                            customSql = customSql.replace(DEDTABLE, StringUtils.EMPTY);
                        }

                    }
                }
            }
            if (isCustomView) {
                customSql = checkIsCustom(isCustomView, hierarchyIndicator, customViewDetails.get(NumericConstants.TWO), customViewDetails.get(NumericConstants.FOUR), customViewDetails.get(NumericConstants.NINE), hierarchyNo, customSql,session);
            } else {
                customSql = checkIsCustom(isCustomView, hierarchyIndicator, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, hierarchyNo, customSql,session);
            }
            return HelperTableLocalServiceUtil.executeUpdateQueryCount(QueryUtil.replaceTableNames(customSql, session.getCurrentTableNames()));

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(customSql);
            return 0;
        }
    }
   
    public List<String> getRsContractSid(SessionDTO session, boolean checkValue, String hierarchyNo,
            String hierarchyIndicator, boolean isCustomView, List<String> customViewDetails, boolean isProgram, List<String> discountList) {
        String customSql = "";
        LOGGER.debug(" inside updateCheckRecord");
        if (discountList != null && !discountList.isEmpty()) {
            String selectedDiscounts = getRSDiscountSids(discountList);

            customSql = SQlUtil.getQuery("GET_RS_CONTRACT_SID")
                    .replace(Constant.HIERARCHY_COLUMN, commonLogic.getColumnNameCustom(hierarchyIndicator))
                    .replace(Constant.HIERARCHY_NO, hierarchyNo)
                    .replace(Constant.PROGRAMS_REF, selectedDiscounts)
                    .replace(Constant.RELVALUE, session.getDedRelationshipBuilderSid())
                    .replace(Constant.RELVERSION, String.valueOf(session.getDeductionRelationVersion()));

        }
        if (isCustomView) {
            customSql = checkIsCustom(isCustomView, hierarchyIndicator, customViewDetails.get(NumericConstants.TWO), customViewDetails.get(NumericConstants.FOUR), customViewDetails.get(NumericConstants.NINE), hierarchyNo, customSql,session);
        } else {
            customSql = checkIsCustom(isCustomView, hierarchyIndicator, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, hierarchyNo, customSql,session);
        }
        return HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(customSql, session.getCurrentTableNames()));
    }

    public void massUpdate(SessionDTO session, String frequency, List<Integer> startAndEndPeriods, String selectedField, String fieldValue,
           List<String> checkedDiscounts, boolean isProgram, List<String[]> massUpdateData, List<String> selectedPeriods,boolean isCustomHierarchy) {

        String customSql = "";
        LOGGER.debug(" entering massUpdate");
        try {
            String column = "";
            String column2 = "";
            if ("Discount Rate".equals(selectedField)) {
                column = "DPT.PROJECTION_RATE";
                column2 = "SET DPT.PROJECTION_SALES = NM.PROJECTION_SALES * (@DISCOUNT_AMOUNT / 100.0)";
            }
            if ("RPU".equals(selectedField)) {
                column2 = "SET    DPT.PROJECTION_SALES = @DISCOUNT_AMOUNT  * NM.PROJECTION_UNITS ";
                column = "DPT.PROJECTION_RPU";
            }
            if ("Discount Amount".equals(selectedField)) {
                column = "DPT.PROJECTION_SALES";
            }
            if ("Growth".equals(selectedField)) {
                column = "DPT.GROWTH";
            }

            if ("Group".equals(selectedField)) {
                customSql = "UPDATE ST_NM_DISCOUNT_PROJ_MASTER SET USER_GROUP = '" + fieldValue + "' where CHECK_RECORD = 1";
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
                    if (startAndEndPeriods.size() > NumericConstants.TWO) {
                        endFreq = startAndEndPeriods.get(NumericConstants.TWO);
                    }
                    if (startAndEndPeriods.size() > NumericConstants.THREE) {
                        endYear = startAndEndPeriods.get(NumericConstants.THREE);
                    }
                }

                int startMonth = 0;
                int endMonth = 0;
                if (frequency.equals(ANNUALLY.getConstant())) {
                    startMonth = 1;
                    endMonth = NumericConstants.TWELVE;
                }
                if (frequency.equals(QUARTERLY.getConstant())) {
                    switch (startFreq) {
                        case 1:
                            startMonth = 1;
                            break;
                        case NumericConstants.TWO:
                            startMonth = NumericConstants.FOUR;
                            break;
                        case NumericConstants.THREE:
                            startMonth = NumericConstants.SEVEN;
                            break;
                        case NumericConstants.FOUR:
                            startMonth = NumericConstants.TEN;
                            break;
                        default:
                            LOGGER.warn("startFreq is not valid= {} " , startFreq);
                            break;
                    }
                    switch (endFreq) {
                        case 1:
                            endMonth = NumericConstants.THREE;
                            break;
                        case NumericConstants.TWO:
                            endMonth = NumericConstants.SIX;
                            break;
                        case NumericConstants.THREE:
                            endMonth = NumericConstants.NINE;
                            break;
                        case NumericConstants.FOUR:
                            endMonth = NumericConstants.TWELVE;
                            break;
                        default:
                            LOGGER.warn("endFreq is not valid= {} " , endFreq);
                            break;
                    }
                }
                if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
                    switch (startFreq) {
                        case 1:
                            startMonth = 1;
                            break;
                        case NumericConstants.TWO:
                            startMonth = NumericConstants.SEVEN;
                            break;
                        default:
                            LOGGER.warn("startFreq is not valid= {} " , startFreq);
                            break;
                    }
                    switch (endFreq) {
                        case 1:
                            endMonth = NumericConstants.SIX;
                            break;
                        case NumericConstants.TWO:
                            endMonth = NumericConstants.TWELVE;
                            break;
                        default:
                            LOGGER.warn("endFreq is not valid= {} " , endFreq);
                            break;
                            
                    }
                }
                if (frequency.equals(MONTHLY.getConstant())) {
                    startMonth = startFreq;
                    endMonth = endFreq;
                }
                String levelNo = "";
                String hierarIndicator = "";
                String relValue = "";
                String selectedDiscounts = getRSDiscountSids(checkedDiscounts);
                String discountType = "M.PRICE_GROUP_TYPE";
                if (isProgram) {
                    discountType = "RS.RS_NAME";
                }
                if (isCustomHierarchy) {
                for (String[] strings : massUpdateData) {
                    levelNo = strings[3];
                    hierarIndicator = strings[1];
                    relValue = strings[4].contains("~") ? strings[4].substring(strings[4].lastIndexOf('~') + 1) : strings[4];
                }
                }else{
                levelNo = String.valueOf(session.getSelectedDeductionLevelNo());
                }

                String rebateHiearachyJoin=getRebateJoin(!hierarIndicator.equals("D") ? selectedDiscounts : relValue,session,levelNo);
                String declareStatement = " DECLARE @START_MONTH INT=" + startMonth + "\n"
                        + " DECLARE @START_YEAR INT=" + startYear + "\n"
                        + " DECLARE @END_MONTH INT=" + endMonth + "\n"
                        + " DECLARE @END_YEAR INT=" + endYear + "\n"
                        + "DECLARE       @DISCOUNT_AMOUNT NUMERIC(22, 6)=" + fieldValue + "\n"
                        + "DECLARE          @FREQUENCY_COUNT INT\n"
                        + "DECLARE @FREQUENCY CHAR(1)='" + frequency.charAt(0) + "'";

                if ("Discount Rate".equals(selectedField) || "RPU".equals(selectedField)) {
                    customSql = declareStatement +rebateHiearachyJoin + SQlUtil.getQuery("discRatemassPopulate");
                    customSql = customSql.replaceAll("@SELDISC", "" + selectedDiscounts);
                    customSql = customSql.replace("@COL2", column2);
                    customSql = customSql.replace("@DISCTYPE ", discountType + " IN ");
                } else if ("Discount Amount".equals(selectedField) ){
                    String csustomSql = SQlUtil.getQuery("MASSUPDATE-DISCOUNT-AMOUNT");
                    csustomSql = csustomSql.replace("[?Hierarchy-Combination]", getCustomHierarchies(massUpdateData));
                    customSql = declareStatement + rebateHiearachyJoin +csustomSql;
                    }else {
                    customSql = declareStatement + rebateHiearachyJoin + " \n UPDATE DPT SET " + column + " = " + fieldValue + " FROM ST_NM_DISCOUNT_PROJECTION DPT, \n"
                            + "(SELECT M.CCP_DETAILS_SID, DP.PERIOD_SID, RS.RS_CONTRACT_SID FROM ST_NM_DISCOUNT_PROJ_MASTER M, \n"
                            + " ST_NM_DISCOUNT_PROJECTION DP, RS_CONTRACT RS, \"PERIOD\" P ,#SELECTED_REBATE SR \n"
                            + " WHERE  M.DEDUCTION_HIERARCHY_NO like SR.DED_HIE_NO+'%' AND \n"
                            + "  M.CHECK_RECORD = 1 \n"
                            + " AND DP.CCP_DETAILS_SID = M.CCP_DETAILS_SID \n"
                            + " AND M.RS_CONTRACT_SID = RS.RS_CONTRACT_SID \n"
                            + " AND DP.PERIOD_SID = P.PERIOD_SID AND P.PERIOD_DATE BETWEEN Datefromparts(@START_YEAR, @START_MONTH, 1) AND Datefromparts (@END_YEAR, @END_MONTH, 1) \n "
                            + " ) A \n"
                            + " WHERE DPT.CCP_DETAILS_SID = A.CCP_DETAILS_SID \n"
                            + " AND DPT.PERIOD_SID = A.PERIOD_SID \n"
                            + " AND DPT.RS_CONTRACT_SID = A.RS_CONTRACT_SID \n";
                }
            }
            HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(customSql, session.getCurrentTableNames()));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(customSql);
        }
    }
    
      private String getCustomHierarchies(List<String[]> massUpdateData) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean isNotFirstElement = false;
        int i = 1;
        for (String[] hierarchyData : massUpdateData) {
            if (!hierarchyData[0].contains(",")) {
                if (isNotFirstElement) {
                    stringBuilder.append(",\n");
                }
                stringBuilder.append("('");
                stringBuilder.append(hierarchyData[0]).
                        append("',");
                if (hierarchyData[2].isEmpty()) {
                    stringBuilder.append("NULL");
                } else {
                    stringBuilder.append('\'').append(hierarchyData[2]).append('\'');
                }

                stringBuilder.append(',').append('\'').append(hierarchyData[1]).append("',").append(i++);
                stringBuilder.append(')');
                isNotFirstElement = true;
            } else {
                List<String> hierarchyNo = Arrays.asList((String.valueOf(hierarchyData[0])).split("\\,"));
                for (String string : hierarchyNo) {
                   if (isNotFirstElement) {
                    stringBuilder.append(",\n");
                }
                stringBuilder.append("('");
                stringBuilder.append(string.trim()).
                        append("',");
                if (hierarchyData[2].isEmpty()) {
                    stringBuilder.append("NULL");
                } else {
                    stringBuilder.append('\'').append(hierarchyData[2]).append('\'');
                }

                stringBuilder.append(',').append('\'').append(hierarchyData[1]).append("',").append(i++);
                stringBuilder.append(')');
                isNotFirstElement = true;  
                }

            }
        }
        return stringBuilder.toString();
    }
    
    public boolean saveGroupValues(SessionDTO session, String hierarchyNo, String groupValue, boolean isProgram,
            List<String> discountList,String deductionHierarchy,String hierarchyIndicator) {
        String customSql = StringUtils.EMPTY;
        String rebateQuery = StringUtils.EMPTY;
        LOGGER.debug(" entering saveGroupValues");
        try {
            if(CommonUtil.isValueEligibleForLoading()){
            rebateQuery=SQlUtil.getQuery("UPDATE_TEMP_GROUP");
            customSql = rebateQuery.replace("@HIERARCHY_NO", hierarchyNo).replace(Constant.AT_USER_GROUP, groupValue)
                    .replace(SELECTED_REBATE_AT, deductionHierarchy);
            
            }else{
            rebateQuery = isProgram ? SQlUtil.getQuery(Constant.GET_COUNT_PROGRAM) : SQlUtil.getQuery(Constant.COUNT_PROGRAM_CATEGORY);
            rebateQuery = rebateQuery.replace(Constant.AT_DISCOUNT, getRebate(discountList));

            customSql = SQlUtil.getQuery("MANUAL_USER_GROUP_SAVE")
                    .replace("@REBATE_QUERY", rebateQuery)
                    .replace(Constant.AT_USER_GROUP, groupValue)
                    .replace("@HIERARCHYNO", hierarchyNo)
                    .replace("@REBATE_NAME", isProgram ? Constant.RS_CONTRACT_SID : Constant.PRICE_GROUP_TYPE);
            
            }
            

            HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(customSql, session.getCurrentTableNames()));
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(customSql);
            return false;
        }
    }

    
    
    public boolean saveDiscountProjectionListView(SessionDTO session, String frequency, int period, int year, String hierarchyIndicator,
            String hierarchyNo, String discountName, String fieldValue, boolean isProgram, boolean isCustomHierarchy, List<String> customViewDetails) {
        String customSql = StringUtils.EMPTY;
        LOGGER.debug(" entering saveDiscountProjectionListView");
        try {
            String refreshName = customViewDetails.get(NumericConstants.SEVEN);

            String queryPeriod = SQlUtil.getQuery("PERIOD_TABLE")
                    .replace("?F", String.valueOf(frequency.charAt(0)))
                    .replace("?R", session.getDedRelationshipBuilderSid())
                    .replace("@REFNAME", refreshName);
            if (isCustomHierarchy && CommonUtil.isValueEligibleForLoading()) {
                customSql = SQlUtil.getQuery("MANUAL_ENTRY_DISCOUNT_CUSTOM").replace(PERIOD_TABLE, queryPeriod)
                        .replace("@TEMP_TABLE_DISCOUNT", StringUtils.EMPTY)
                        .replace(RELATION_SID, discountName).replace(BUILDER_SID, session.getDedRelationshipBuilderSid())
                        .replace(Constant.RELVERSION, String.valueOf(session.getDeductionRelationVersion()))
                        .replace(Constant.AT_COLUMN_NAME, commonLogic.getColumnNameCustomRel(hierarchyIndicator,hierarchyNo,session))
                        .replace("?Y", String.valueOf(year)).replace(Constant.AT_RS_COLUMN, isCustomHierarchy ? Constant.RS_CONTRACT_SID : isProgram ? Constant.RS_CONTRACT_SID : Constant.PRICE_GROUP_TYPE);
            } else if ("RPU".equalsIgnoreCase(refreshName)) {
                String uomJoin=EACH.equals(session.getDiscountUom())?StringUtils.EMPTY:LEFT_JOIN_ST_ITEM_UOM_DETAILS_UOM_ON_UOM+session.getDiscountUom()+"' ";
                String uomDivJoin=EACH.equals(session.getDiscountUom())?StringUtils.EMPTY:"/nullif(uom_value,0) ";
                customSql = SQlUtil.getQuery("MANUAL_ENTRY_DISCOUNT").replace("@REFRESHAMT", fieldValue).replace(UOM_JOIN,uomJoin).replace(PERIOD_TABLE, queryPeriod)
                        .replace(RELATION_SID, discountName).replace(BUILDER_SID, session.getDedRelationshipBuilderSid())
                        .replace(Constant.RELVERSION, String.valueOf(session.getDeductionRelationVersion()))
                        .replace(Constant.AT_COLUMN_NAME, commonLogic.getColumnNameCustom(hierarchyIndicator)).replace("@UOMDIV", uomDivJoin)
                        .replace("?Y", String.valueOf(year)).replace(Constant.AT_RS_COLUMN, isCustomHierarchy ? Constant.RS_CONTRACT_SID : isProgram ? Constant.RS_CONTRACT_SID : Constant.PRICE_GROUP_TYPE);
            } else {
                customSql = SQlUtil.getQuery("MANUAL_ENTRY_DISCOUNT").replace(PERIOD_TABLE, queryPeriod)
                        .replace(RELATION_SID, discountName).replace(BUILDER_SID, session.getDedRelationshipBuilderSid())
                        .replace(Constant.RELVERSION, String.valueOf(session.getDeductionRelationVersion()))
                        .replace(Constant.AT_COLUMN_NAME, commonLogic.getColumnNameCustom(hierarchyIndicator))
                        .replace("?Y", String.valueOf(year)).replace(Constant.AT_RS_COLUMN, isCustomHierarchy ? Constant.RS_CONTRACT_SID : isProgram ? Constant.RS_CONTRACT_SID : Constant.PRICE_GROUP_TYPE);
            }
            if ("GROWTH".equals(refreshName)) {
                customSql = customSql.replace("@SETTER", "DPT.GROWTH = " + fieldValue);
            } else {
                customSql = customSql.replace("@SETTER", "DPT.refreshed_value = " + fieldValue + MANUAL_ENTRY_COUNT );
            }

            if (!frequency.equals(ANNUALLY.getConstant())) {
                customSql += "and I.period = " + period;
            }

            customSql = checkIsCustom(isCustomHierarchy, hierarchyIndicator, customViewDetails.get(NumericConstants.TWO), customViewDetails.get(NumericConstants.FOUR), customViewDetails.get(NumericConstants.NINE), hierarchyNo, customSql,session);
            HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(customSql, session.getCurrentTableNames()));
            return BooleanConstant.getTrueFlag();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(customSql);
            return BooleanConstant.getFalseFlag();
        }
    }
    public static final String LEFT_JOIN_ST_ITEM_UOM_DETAILS_UOM_ON_UOM = " LEFT JOIN ST_ITEM_UOM_DETAILS UOM ON UOM.ITEM_MASTER_SID=CCP.ITEM_MASTER_SID AND UOM_CODE='";
    

    public List<String> getGroupValues(SessionDTO session, List<String> discountList) {
        String customSql = StringUtils.EMPTY;
        LOGGER.debug(" entering getGroupValues");
        try {
            if (discountList != null && !discountList.isEmpty()) {
                String discountType = discountList.get(0);
                discountList.remove(0);
                String selectedDiscounts = getRSDiscountSids(discountList);
                if (discountType.equals(Constants.PROGRAM)) {
                    customSql = SQlUtil.getQuery(PROGRAM).replace(Constant.PROGRAMS_REF, selectedDiscounts);
                } else {
                    customSql = SQlUtil.getQuery("Program_Category").replace("@PROGRAM_CATEGORY", selectedDiscounts);
                }
                if(CommonUtil.isValueEligibleForLoading()){
                 customSql = SQlUtil.getQuery("All_level_UserGroup");
                }
            }
            return (List<String>) HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(customSql, session.getCurrentTableNames()));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(customSql);
            return Collections.emptyList();
        }
    }

    public int getCheckedRecordCount(SessionDTO session, boolean isProgram, List<String> discountList, boolean isCustomHierarchy) {
        List list = new ArrayList();
        String customSql = StringUtils.EMPTY;
        LOGGER.debug(" inside getCheckedRecordCount");
        try {
            String discountTypeQuery = "";
            if (discountList != null && !discountList.isEmpty()) {
                String selectedDiscounts = getRSDiscountSids(discountList);
                if (isProgram) {
                    discountTypeQuery += " JOIN RS_CONTRACT RS ON DP.RS_CONTRACT_SID = RS.RS_CONTRACT_SID  AND RS.RS_CONTRACT_SID  in (" + selectedDiscounts + ") \n ";
                } else {
                    discountTypeQuery += " WHERE DP.PRICE_GROUP_TYPE in (" + selectedDiscounts + ")  \n";
                }
            }

            
                customSql = "SELECT count(*) AS CHECK_COUNT FROM ST_NM_DISCOUNT_PROJ_MASTER DP JOIN ST_CCP_HIERARCHY CCPH ON CCPH.CCP_DETAILS_SID = DP.CCP_DETAILS_SID \n"
                       + " WHERE DP.CHECK_RECORD=1";
           

            list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(customSql, session.getCurrentTableNames()));
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

    public List getDiscountPrograms(SessionDTO session, String programType) {
        StringBuilder sb = new StringBuilder();
        LOGGER.debug(" inside getDiscountPrograms");
        try {
            if ("DiscountType".equals(programType)) {
                sb.append("SELECT DISTINCT '' AS DISCOUNT_ID, DM.PRICE_GROUP_TYPE AS DISCOUNT_NAME, RS.RS_CONTRACT_SID,RS.RS_NAME FROM ST_NM_DISCOUNT_PROJ_MASTER  DM "
                        + "JOIN RS_Contract RS ON DM.RS_CONTRACT_SID = RS.RS_CONTRACT_SID ").append(" AND DM.PRICE_GROUP_TYPE IS NOT NULL ORDER  BY DISCOUNT_NAME,RS_CONTRACT_SID ");
            } else if ("DiscountProgram".equals(programType)) {
                sb.append("SELECT RS.RS_ID   AS DISCOUNT_ID, RS.RS_NAME AS DISCOUNT_NAME,RS.RS_CONTRACT_SID AS RS_CONTRACT_SID,RS.RS_NAME as RS_NAME FROM RS_CONTRACT RS ")
                        .append(" WHERE  EXISTS (SELECT 1 FROM ST_NM_DISCOUNT_PROJ_MASTER DM ")
                        .append("WHERE  DM.RS_CONTRACT_SID = RS.RS_CONTRACT_SID  AND RS.RS_NAME IS NOT NULL ")
                        .append(" ) ORDER  BY DISCOUNT_NAME,RS_CONTRACT_SID ");
            }
            List result = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(sb.toString(), session.getCurrentTableNames()));
            return result;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sb.toString());
            return Collections.emptyList();
        }
    }

    
    
    public List getDiscountProjection(final boolean isProgram, final String frequency, final List<String> discountList,
            final SessionDTO session, final String hierarchyNo, final String hierarchyIndicator, final int levelNo, final boolean isCustom, final List<String> customViewDetails, final int treeLevelNo,
            final int start, final int end, final String userGroup) {
        String queryBuilder = SQlUtil.getQuery("discountGenerateCustom");
        queryBuilder = queryBuilder.replace("?RS", isProgram ? "R" : "P") //Indicator for Program or program category
                .replace("?F", String.valueOf(frequency.charAt(0))) //Selected Frequency initial char
                .replace("?P", "D".equals(hierarchyIndicator) ? !(customViewDetails.get(NumericConstants.NINE)).isEmpty() ? customViewDetails.get(NumericConstants.NINE)+"~" : StringUtils.EMPTY : StringUtils.EMPTY) //Selected Frequency initial char
                .replaceAll(SELECTED_REBATE_AT, getRSDiscountSids(discountList)) // Selected RS
                .replace("@REBATE_QUERY", isCustom && CommonUtil.isValueEligibleForLoading() ? StringUtils.EMPTY : isProgram ? SQlUtil.getQuery(Constant.GET_COUNT_PROGRAM) : SQlUtil.getQuery(Constant.COUNT_PROGRAM_CATEGORY))
                .replace(Constant.AT_DISCOUNT, getRebate(discountList))
                .replace("@HIERARCHY_QUERY", insertAvailableHierarchyNo(session, hierarchyNo, hierarchyIndicator, isCustom ? treeLevelNo : levelNo, isCustom, isCustom ? Integer.parseInt(customViewDetails.get(0)) : 0, isCustom ? customViewDetails.get(NumericConstants.TWO) : StringUtils.EMPTY, isCustom ? customViewDetails.get(NumericConstants.FOUR) : StringUtils.EMPTY, isCustom ? customViewDetails.get(NumericConstants.NINE) : StringUtils.EMPTY, start, end, userGroup)) // Selected RS
                .replace("@REBATE_COLUMN", isProgram ? Constant.RS_CONTRACT_SID : Constant.PRICE_GROUP_TYPE)
                .replace(Constant.AT_COLUMN_NAME, commonLogic.getColumnNameCustom(hierarchyIndicator))
                .replace("@RS_CONTRACT_SID", isProgram ? " DM.RS_CONTRACT_SID, " : StringUtils.EMPTY)
                .replace("@DPM_RS_CONTRACT_SID", isProgram ? " ,DPM.RS_CONTRACT_SID AS RS_CONTRACT_SID " : StringUtils.EMPTY)
                .replace("@ORDER_RS_CONTRACT_SID", isProgram ? " DPM.RS_CONTRACT_SID, " : StringUtils.EMPTY)
                .replace("@RS_CONTRACT_ID", isProgram ? " RS_CONTRACT_SID, " : StringUtils.EMPTY)
                .replace("@SELECTED_HIERARCHY_NO", isProgram ? " #SELECTED_HIERARCHY_NO " : " (select distinct HIERARCHY_NO,DISCOUNT,ccp_details_sid from #SELECTED_HIERARCHY_NO) ")
                .replace("@RS_ID", isProgram ? "  ,s.rs_contract_sid " : StringUtils.EMPTY)
                .replace("@TABLENAME", " #ST_NM_DISCOUNT_PROJ_MASTER ")
                .replace("@PROGCATPROG", isProgram ? " RS_CONTRACT_SID " : "  PRICE_GROUP_TYPE ")
                .replace("@RS_NO", isProgram ? "  ,C.RS_CONTRACT_SID " : StringUtils.EMPTY)
                .replace("@CONDITION1", isProgram ? "   AND DPM.RS_CONTRACT_SID = AD.RS_CONTRACT_SID " : StringUtils.EMPTY)
                .replace("@CONDITION2", isProgram ? "  AND SA.RS_CONTRACT_SID = AD.RS_CONTRACT_SID " : StringUtils.EMPTY)
                .replace("@CONDITION3", isProgram ? "  HIERARCHY_NO " : " DPM.HIERARCHY_NO ")
                .replace("@DEDINCLUSION", "ALL".equals(session.getDeductionInclusion()) ? StringUtils.EMPTY:" and m.DEDUCTION_INCLUSION= "+session.getDeductionInclusion()) // Selected RS
                .replace("@DEDINCLDPM", "ALL".equals(session.getDeductionInclusion()) ? StringUtils.EMPTY:" and DPM.DEDUCTION_INCLUSION= "+session.getDeductionInclusion()) // Selected RS
                .replace("@DPMDEDINCLLUSION", "ALL".equals(session.getDeductionInclusion()) ? StringUtils.EMPTY:" and P.DEDUCTION_INCLUSION= "+session.getDeductionInclusion()) // Selected RS
                .replace("@DEDINCLNWHR", "ALL".equals(session.getDeductionInclusion()) ? StringUtils.EMPTY:" WHERE DEDUCTION_INCLUSION= "+session.getDeductionInclusion()) // Selected RS
                .replace("@UOMACTUAL",(EACH.equals(session.getDiscountUom()) || session.getDiscountUom().isEmpty()) ?" ACTUAL_UNITS=Sum(ACTUAL_UNITS) ":" SUM(ISNULL(S.ACTUAL_UNITS,0)*ISNULL(UOM.UOM_VALUE,0))  ACTUAL_UNITS ") // Selected RS
                .replace("@UOMPROJ",(EACH.equals(session.getDiscountUom()) || session.getDiscountUom().isEmpty()) ?" PROJECTION_UNITS=SUM(PROJECTION_UNITS) ":" SUM(ISNULL(S.PROJECTION_UNITS,0)*ISNULL(UOM.UOM_VALUE,0))  PROJECTION_UNITS ") // Selected RS
                .replace(UOM_JOIN,(EACH.equals(session.getDiscountUom()) || session.getDiscountUom().isEmpty()) ?StringUtils.EMPTY:LEFT_JOIN_ST_ITEM_UOM_DETAILS_UOM_ON_UOM+session.getDiscountUom()+"'")
                .replace("@HASHDP",(session.getDeductionInclusion() ==null || "ALL".equals(session.getDeductionInclusion()) || session.getDeductionInclusion().isEmpty()) ?StringUtils.EMPTY:" ,DEDUCTION_INCLUSION ")
                .replace("@SELCOLDED",(session.getDeductionInclusion() ==null || "ALL".equals(session.getDeductionInclusion()) || session.getDeductionInclusion().isEmpty()) ?",0 as DEDUCTION_INCLUSION":" ,DPM.DEDUCTION_INCLUSION ")
                .replace(REL_COLUMN, commonLogic.getDedCustomJoinGenerateForParent(session, hierarchyIndicator)) 
                .replace(DEDCUST_JOIN, commonLogic.getDedCustomJoinGenerate(session, isCustom ? customViewDetails.get(NumericConstants.NINE) : StringUtils.EMPTY, hierarchyIndicator, levelNo))
                .replace(RELVALUE, session.getDedRelationshipBuilderSid())
                .replace(Constant.RELVERSION, String.valueOf(session.getDeductionRelationVersion()))
                .replace(Constant.RELJOIN, CommonLogic.getRelJoinGenerate(hierarchyIndicator,session))
                .replace(SELECTED_HIERARCHY_JOIN, getHierarchyJoinQuery(session, isCustom ? Integer.parseInt(customViewDetails.get(0)) : 0, levelNo, isCustom, hierarchyIndicator, isCustom ? customViewDetails.get(NumericConstants.TWO) : StringUtils.EMPTY, isCustom ? customViewDetails.get(NumericConstants.FOUR) : StringUtils.EMPTY, isCustom ? customViewDetails.get(NumericConstants.NINE) : StringUtils.EMPTY, userGroup))
                .replace("@FILTERCCP"," and FILTER_CCP=1 ") ;
        if (StringUtils.isNotBlank(userGroup)) {
            queryBuilder = queryBuilder.replace(Constant.AT_USER_GROUP, " AND USER_GROUP = '" + userGroup + "'");
        }
        queryBuilder = isCustom && CommonUtil.isValueEligibleForLoading() ? queryBuilder.replace(Constant.PROGJOIN, StringUtils.EMPTY) : isProgram ? queryBuilder.replace(Constant.PROGJOIN, " JOIN #SELECTED_REBATE SR ON SR.PRICE_GROUP_TYPE = SPM.RS_CONTRACT_SID ")
                : queryBuilder.replace(Constant.PROGJOIN, " JOIN #SELECTED_REBATE SR ON SR.PRICE_GROUP_TYPE = SPM.PRICE_GROUP_TYPE ");
        queryBuilder = queryBuilder.replace(Constant.AT_USER_GROUP, StringUtils.EMPTY);
        return HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(queryBuilder, session.getCurrentTableNames()));
    }
    
    public List getDiscountProjectionLastLevel(final String frequency, final List<String> discountList,
            final SessionDTO session, final String hierarchyNo, final String hierarchyIndicator, final int levelNo, final boolean isCustom, final List<String> customViewDetails, final int treeLevelNo,
            final int start, final int end, final String userGroup,final ProjectionSelectionDTO projectionSelection) {
        String queryBuilder=StringUtils.EMPTY;
        if (session.getSelectedDeductionLevelNo() == 10) {
            queryBuilder = SQlUtil.getQuery("discount-generate-RS-Contract-Id");
        } else {
            queryBuilder = SQlUtil.getQuery("discount-generate-First-Nine-Level");
        }
        queryBuilder = queryBuilder.replace("?F", String.valueOf(frequency.charAt(0))) //Selected Frequency initial char
                .replace(SELECTED_REBATE_AT,getRSDiscountHierarchyNo(discountList,session,session.getSelectedDeductionLevelNo())) // Selected RS
                .replace("@DEDHIERVALUES", commonLogic.getSelectedHierarchy(session, hierarchyNo, hierarchyIndicator, levelNo)) // Selected RS
                .replace("@DEDINCLUSION", (session.getDeductionInclusion() ==null || "ALL".equals(session.getDeductionInclusion())) ? StringUtils.EMPTY:" and m.DEDUCTION_INCLUSION= "+session.getDeductionInclusion()) // Selected RS
                .replace("@DEDINCLDPM", (session.getDeductionInclusion() ==null || "ALL".equals(session.getDeductionInclusion())) ? StringUtils.EMPTY:" and DPM.DEDUCTION_INCLUSION= "+session.getDeductionInclusion()) // Selected RS
                .replace("@DPMDEDINCLLUSION", (session.getDeductionInclusion() ==null || "ALL".equals(session.getDeductionInclusion())) ? StringUtils.EMPTY:" and P.DEDUCTION_INCLUSION= "+session.getDeductionInclusion()) // Selected RS
                .replace("@DEDINCLNWHR", (session.getDeductionInclusion() ==null || "ALL".equals(session.getDeductionInclusion())) ? StringUtils.EMPTY:" WHERE DEDUCTION_INCLUSION= "+session.getDeductionInclusion()) // Selected RS
                .replace("@FITLERCCP"," and FILTER_CCP=1 ") 
                .replace("@CUSTORPROD","P".equals(hierarchyIndicator)?"PROD_HIERARCHY_NO":"CUST_HIERARCHY_NO")
                .replace("@FIRSTROW",String.valueOf(start)) 
                .replace("@NEXTROW",String.valueOf(end)) 
                .replace("@UOMACTUAL",(EACH.equals(session.getDiscountUom()) || session.getDiscountUom().isEmpty()) ?" ACTUAL_UNITS=Sum(ACTUAL_UNITS) ":" SUM(ISNULL(S.ACTUAL_UNITS,0)*ISNULL(UOM.UOM_VALUE,0))  ACTUAL_UNITS ") // Selected RS
                .replace("@UOMPROJ",(EACH.equals(session.getDiscountUom()) || session.getDiscountUom().isEmpty()) ?" PROJECTION_UNITS=SUM(PROJECTION_UNITS) ":" SUM(ISNULL(S.PROJECTION_UNITS,0)*ISNULL(UOM.UOM_VALUE,0))  PROJECTION_UNITS ") // Selected RS
                .replace(UOM_JOIN,(EACH.equals(session.getDiscountUom()) || session.getDiscountUom().isEmpty()) ?StringUtils.EMPTY:LEFT_JOIN_ST_ITEM_UOM_DETAILS_UOM_ON_UOM+session.getDiscountUom()+"'")// Selected RS

                .replace("@HASHDP",(session.getDeductionInclusion() ==null || "ALL".equals(session.getDeductionInclusion()) || session.getDeductionInclusion().isEmpty()) ?StringUtils.EMPTY:" ,DEDUCTION_INCLUSION ")
                .replace("@SELCOLDED",(session.getDeductionInclusion() ==null || "ALL".equals(session.getDeductionInclusion()) || session.getDeductionInclusion().isEmpty()) ?" ,0 as DEDUCTION_INCLUSION ":" ,DPM.DEDUCTION_INCLUSION ")
                .replace(Constant.RELJOIN,CommonLogic.getRelJoinGenerate(hierarchyIndicator,session));
               
        if (StringUtils.isNotBlank(userGroup)) {
            queryBuilder = queryBuilder.replace(Constant.AT_USER_GROUP, " AND USER_GROUP = '" + userGroup + "'");
        }
        queryBuilder = queryBuilder.replace(Constant.AT_USER_GROUP, StringUtils.EMPTY);
        queryBuilder = QueryUtil.replaceTableNames(queryBuilder, session.getCurrentTableNames());
        return HelperTableLocalServiceUtil.executeSelectQuery(queryBuilder);
    }
    

    public String getRSDiscountSids(List<String> discountList) {
        String framedString = StringUtils.EMPTY;
        for (String value : discountList) {
            framedString += " '" + (value.contains("~") ? value.split("~")[1] : value) + "'" + ",";
        }
        framedString = framedString.substring(0, framedString.length() - 1);
        return framedString;
    }
    
    
    public static String getQueryRSDiscountHierarchyNo(List<String> discountList) {
    String framedString=StringUtils.EMPTY;
        for (String value : discountList) {
            if (value.contains(",")) {
                for (String string : value.split(",")) {
                    framedString += " '" + (string) + "'" + ",";
                }
            } else {
                framedString += " '" + (value) + "'" + ",";
            }
        }
        framedString = framedString.substring(0, framedString.length() - 1);
        String framedQuery = "SELECT DISTINCT DEDUCTION_HIERARCHY_NO FROM ST_NM_DISCOUNT_PROJ_MASTER  WHERE RS_CONTRACT_SID in ("+framedString+")";
        
        return framedQuery;
    }
    
    public static List getRSHierarchyNo(List<String> discountList,SessionDTO session) {
        String framedRsString=StringUtils.EMPTY;
        for (String value : discountList) {
            if (value.contains(",")) {
                for (String string : value.split(",")) {
                    framedRsString += " '" + (string) + "'" + ",";
                }
            } else {
                framedRsString += " '" + (value) + "'" + ",";
            }
        }
        framedRsString = framedRsString.substring(0, framedRsString.length() - 1);
        String framedQuery = "SELECT DISTINCT HIERARCHY_NO FROM RELATIONSHIP_LEVEL_DEFINITION  WHERE RELATIONSHIP_LEVEL_VALUES  in ("+framedRsString+") AND RELATIONSHIP_BUILDER_SID ="+session.getDedRelationshipBuilderSid();
        
        return HelperTableLocalServiceUtil.executeSelectQuery(framedQuery);
    }

    private String insertAvailableHierarchyNo(SessionDTO sessionDTO, String hierarchyNo, String hierarchyIndicator, int levelNo, boolean isCustomHierarchy,
            final int customId, final String customerHierarchyNo, final String productHierarchyNo, final String deductionHierarchyNo, final int startIndex, final int endIndex, final String userGroup) {

        String sql;
        sql = CommonUtil.isValueEligibleForLoading() && isCustomHierarchy ? SQlUtil.getQuery("discount-selected-hierarchy-Custom-View") : SQlUtil.getQuery("discount-selected-hierarchy");
        if (isCustomHierarchy) {
            sql = sql.replace(RS_JOIN, StringUtils.EMPTY);
            String currentHierarchyIndicator = commonLogic.getHiearchyIndicatorFromCustomView(sessionDTO, customId, levelNo);
            int actualLevelNo = commonLogic.getActualLevelNoFromCustomView(sessionDTO, customId, levelNo);
            switch (String.valueOf(currentHierarchyIndicator)) {
                case Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY:
                    sql = sql.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, getSelectedHierarchy(sessionDTO, customerHierarchyNo, currentHierarchyIndicator, actualLevelNo,false));
                    break;
                case Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY:
                    sql = sql.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, getSelectedHierarchy(sessionDTO, productHierarchyNo, currentHierarchyIndicator, actualLevelNo,false));
                    break;
                case Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY:
                    sql = sql.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, commonLogic.getSelectedHierarchyDeduction(sessionDTO, deductionHierarchyNo, currentHierarchyIndicator, actualLevelNo,false));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Hierarchy Indicator:" + currentHierarchyIndicator);
            }
        } else {
            sql = sql.replace(RS_JOIN, " JOIN #SELECTED_REBATE SR\n"
                    + "                         ON SR.PRICE_GROUP_TYPE = MAS.@REBATE_COLUMN ");
            sql = sql.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, commonLogic.getSelectedHierarchy(sessionDTO, hierarchyNo, hierarchyIndicator, levelNo));
        }
        sql = sql.replace(SELECTED_HIERARCHY_JOIN, getHierarchyJoinQuery(sessionDTO, customId, levelNo, isCustomHierarchy, hierarchyIndicator, customerHierarchyNo, productHierarchyNo, commonLogic.replacePercentHierarchy(deductionHierarchyNo), userGroup));
        sql = sql.replace("@START", String.valueOf(startIndex));
        sql = sql.replace("@OFFSET", String.valueOf(endIndex));
        return sql;
    }
    
    private String insertAvailableHierarchyNoForCount(SessionDTO sessionDTO, String hierarchyNo, String hierarchyIndicator, int levelNo, boolean isCustomHierarchy,
            final int customId, final String customerHierarchyNo, final String productHierarchyNo, final String deductionHierarchyNo, final int startIndex, final int endIndex, final String userGroup) {

        String sqlQuery;
        sqlQuery = SQlUtil.getQuery("discount-selected-hierarchy-Custom-Count");
        if (isCustomHierarchy) {
            sqlQuery = sqlQuery.replace(RS_JOIN, StringUtils.EMPTY);
            String currentHierarchyIndicator = commonLogic.getHiearchyIndicatorFromCustomView(sessionDTO, customId, levelNo);
            int actualLevelNo = commonLogic.getActualLevelNoFromCustomView(sessionDTO, customId, levelNo);
            switch (String.valueOf(currentHierarchyIndicator)) {
                case Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY:
                    sqlQuery = sqlQuery.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, getSelectedHierarchy(sessionDTO, customerHierarchyNo, currentHierarchyIndicator, actualLevelNo,false));
                    break;
                case Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY:
                    sqlQuery = sqlQuery.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, getSelectedHierarchy(sessionDTO, productHierarchyNo, currentHierarchyIndicator, actualLevelNo,false));
                    break;
                case Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY:
                    sqlQuery = sqlQuery.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, commonLogic.getSelectedHierarchyDeduction(sessionDTO, deductionHierarchyNo, currentHierarchyIndicator, actualLevelNo,false));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Hierarchy Indicator:" + currentHierarchyIndicator);
            }
        } else {
            sqlQuery = sqlQuery.replace(RS_JOIN, " JOIN #SELECTED_REBATE SR\n"
                    + "                         ON SR.PRICE_GROUP_TYPE = MAS.@REBATE_COLUMN ");
            sqlQuery = sqlQuery.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, commonLogic.getSelectedHierarchy(sessionDTO, hierarchyNo, hierarchyIndicator, levelNo));
        }
        sqlQuery = sqlQuery.replace(SELECTED_HIERARCHY_JOIN, getHierarchyJoinQuery(sessionDTO, customId, levelNo, isCustomHierarchy, hierarchyIndicator, customerHierarchyNo, productHierarchyNo, commonLogic.replacePercentHierarchy(deductionHierarchyNo), userGroup));
        sqlQuery = sqlQuery.replace("@START", String.valueOf(startIndex));
        sqlQuery = sqlQuery.replace("@OFFSET", String.valueOf(endIndex));
        return sqlQuery;
    }

    public String getHierarchyJoinQuery(final SessionDTO sessionDTO, final int customId, final int treeLevelNo, final boolean isCustom, final String hierarchyIndicator, final String customerHierarchyNo, final String productHierarchyNo, final String deductionHierarchyNo, final String userGroup) {
        String currentHierarchyIndicator;
        if (isCustom) {
            currentHierarchyIndicator = getHiearchyIndicatorFromCustomView(sessionDTO, customId, treeLevelNo);
        } else {
            currentHierarchyIndicator = hierarchyIndicator;
        }
        String joinQuery = getHierarchyJoinQuery(isCustom, customerHierarchyNo, productHierarchyNo, deductionHierarchyNo, currentHierarchyIndicator, userGroup,sessionDTO);
        return joinQuery;
    }

    public String getHierarchyJoinQuery(boolean isCustomHierarchy, String customerHierarchyNo, String productHierarchyNo, String deductionHierarchyNo, String hierarchyIndicator, final String userGroup,final SessionDTO sessionDTO) {
        String dedJoin = StringUtils.EMPTY;
        StringBuilder joinQuery = new StringBuilder();
        joinQuery.append(" JOIN ST_CCP_HIERARCHY CH ON ");

        if (isCustomHierarchy) {

            switch (String.valueOf(hierarchyIndicator)) {
                case Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY:
                    joinQuery.append(" CH.CUST_HIERARCHY_NO LIKE A.HIERARCHY_NO + '%' ");
                    if (StringUtils.isNotBlank(productHierarchyNo)) {
                        joinQuery.append(CROSS_APPLY_SELECT_TOKEN_FROM_UDF_SPLITST).append(productHierarchyNo).append(CONCAT_CONDITION);
                    }
                     if (StringUtils.isNotBlank(deductionHierarchyNo) && !deductionHierarchyNo.equals("%")) {
                        String hierarchyNo = "%" + deductionHierarchyNo + "%";
                        dedJoin = " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD ON RLD.PARENT_HIERARCHY_NO LIKE '" + hierarchyNo + "' and relationship_builder_sid = " + sessionDTO.getDedRelationshipBuilderSid() + " JOIN #PARENT_VALIDATE PR ON PR.RS_CONTRACT_SID=SPM.RS_CONTRACT_SID\n "
                                + " AND PR.PARENT_HIERARCHY LIKE RLD.PARENT_HIERARCHY_NO+'%'";
                    }
                    break;
                case Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY:
                    joinQuery.append(" CH.PROD_HIERARCHY_NO LIKE A.HIERARCHY_NO + '%' ");
                    if (StringUtils.isNotBlank(customerHierarchyNo)) {
                        joinQuery.append(CROSS_APPLY_SELECT_TOKEN_FROM_UDF_SPLITST).append(customerHierarchyNo).append("', ',') C WHERE CH.CUST_HIERARCHY_NO LIKE concat(C.TOKEN , '%')) FN");
                    }
                    if (StringUtils.isNotBlank(deductionHierarchyNo) && !deductionHierarchyNo.equals("%")) {
                         String hierarchyNo = "%" + deductionHierarchyNo + "%";
                         dedJoin = " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD ON RLD.PARENT_HIERARCHY_NO LIKE '" + hierarchyNo + "' and relationship_builder_sid = " + sessionDTO.getDedRelationshipBuilderSid() + " JOIN #PARENT_VALIDATE PR ON PR.RS_CONTRACT_SID=SPM.RS_CONTRACT_SID\n "
                                + " AND PR.PARENT_HIERARCHY LIKE RLD.PARENT_HIERARCHY_NO+'%'";
                    }
                    break;
                case Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY:
                    dedJoin = " ";
                    joinQuery.append(" CH.PROD_HIERARCHY_NO LIKE '");
                    joinQuery.append(productHierarchyNo);
                    joinQuery.append("%'");
                    joinQuery.append(CROSS_APPLY_SELECT_TOKEN_FROM_UDF_SPLITST).append(customerHierarchyNo).append("', ',') C WHERE CH.CUST_HIERARCHY_NO LIKE concat(C.TOKEN , '%')) FN");
                    break;
                default:
                    LOGGER.warn("Invalid Hierarchy Indicator= {} " , hierarchyIndicator);
            }

        } else {
            joinQuery.append(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(hierarchyIndicator) ? "CH.CUST_HIERARCHY_NO " : "CH.PROD_HIERARCHY_NO");
            joinQuery.append(" LIKE A.HIERARCHY_NO + '%' ");
        }

        joinQuery.append(getJoinForDP(Constant.ALL_DISCOUNT_GROUP, userGroup, dedJoin));

        return joinQuery.toString();
    }

    public String getHiearchyIndicatorFromCustomView(final SessionDTO sessionDTO, final int customId, final int treeLevelNo) {
        List<Leveldto> levelList = sessionDTO.getCustomHierarchyMap().get(customId);

        String hierarchyIndicator = StringUtils.EMPTY;
        for (Leveldto levelDTO : levelList) {
            if (levelDTO.getTreeLevelNo() == treeLevelNo) {

                hierarchyIndicator = levelDTO.getHierarchyIndicator();
                break;
            }
        }
        return hierarchyIndicator;
    }

    private String getJoinForDP(String defaultValue, String groupFilterValue, String deductionJoin) {
        String sql = SQlUtil.getQuery("discount-join");
        sql = sql.replace("?DEDJOIN", deductionJoin);
        sql += " JOIN RS_CONTRACT RSC ON RSC.RS_CONTRACT_SID=SPM.RS_CONTRACT_SID\n ";
        if (StringUtils.isNotBlank(groupFilterValue) && !defaultValue.equals(groupFilterValue)) {
            sql += SQlUtil.getQuery("user-group-condition");
            sql = sql.replace("[?USER_GROUP]", groupFilterValue);
            sql = sql.replace(Constant.AT_USER_GROUP, groupFilterValue);
        }else{
         sql = sql.replace(Constant.AT_USER_GROUP, StringUtils.EMPTY);
         sql = sql.replace("[?USER_GROUP]", StringUtils.EMPTY);
        }
        return sql;
    }

    /**
     * Customer and Product view, discount count query
     *
     * @param sessionDTO
     * @param hierarchyNo
     * @param levelNo
     * @param hierarchyIndicator
     * @param isProgram
     * @param discountList
     * @param userGroup
     * @return
     */
    public String getDiscountCountQuery(SessionDTO sessionDTO, String hierarchyNo, int levelNo, String hierarchyIndicator, boolean isProgram, List<String> discountList, final String userGroup) {
        String countQuery = SQlUtil.getQuery("GET_COUNT").replace(Constant.HIERARCHY_NO, commonLogic.getSelectedHierarchy(sessionDTO, hierarchyNo, hierarchyIndicator, levelNo))
                .replace("@REBATE", isProgram ? SQlUtil.getQuery(Constant.GET_COUNT_PROGRAM) : SQlUtil.getQuery(Constant.COUNT_PROGRAM_CATEGORY))
                .replace(Constant.AT_COLUMN_NAME, commonLogic.getColumnName(hierarchyIndicator)).replace(Constant.AT_DISCOUNT, getRebate(discountList))
                .replace("@FOR_CUSTOM", StringUtils.EMPTY).replace(Constant.AT_RS_COLUMN, isProgram ? Constant.RS_CONTRACT_SID : Constant.PRICE_GROUP_TYPE);
        if (StringUtils.isNotBlank(userGroup)) {
            countQuery = countQuery.concat(WHERE_USER_GROUP).concat(userGroup).concat("'");
        }
        return countQuery;
    }
    public String getDiscountCountQueryForAllLevel(SessionDTO sessionDTO, String hierarchyNo, int levelNo, String hierarchyIndicator, boolean isProgram, List<String> discountList, final String userGroup,final ProjectionSelectionDTO projectionSelection) {
        String countQuery = SQlUtil.getQuery("GET_COUNT_ALL_LEVEL").replace(Constant.HIERARCHY_NO, commonLogic.getSelectedHierarchy(sessionDTO, hierarchyNo, hierarchyIndicator, levelNo))
                .replace(Constant.RELJOIN, CommonLogic.getRelJoinGenerate(hierarchyIndicator,sessionDTO))
                .replace(SELECTED_REBATE_AT, getRSDiscountHierarchyNo(discountList,sessionDTO,levelNo)).replace("@CUSTORPROD", "P".equals(hierarchyIndicator)?"PROD_HIERARCHY_NO":"CUST_HIERARCHY_NO");
        if (StringUtils.isNotBlank(userGroup)) {
            countQuery = countQuery.concat(WHERE_USER_GROUP).concat(userGroup).concat("'");
        }
        return countQuery;
    }
 

    /**
     * Customer and Product view, discount count query
     *
     * @param sessionDTO
     * @param hierarchyIndicator
     * @param levelNo
     * @param customId
     * @param hierarchyNo
     * @param parentHierarhcyIndicaotr
     * @param parentHierarchyNo
     * @param discountList
     * @param isProgram
     * @param userGroup
     * @return
     */
    public String getDiscountCustomCountQuery(final SessionDTO sessionDTO, final String hierarchyIndicator, final int levelNo,
            final String hierarchyNo, final String parentHierarhcyIndicaotr, final String parentHierarchyNo, final String parentHierarhcyIndicaotrDeduction, final String parentHierarchyNoDeduction, final List<String> discountList, boolean isProgram, final String userGroup) {
        String parentColumnJoin = StringUtils.EMPTY;
        String parentColumnJoinDeduction = StringUtils.EMPTY;
        if (StringUtils.isNotBlank(parentHierarhcyIndicaotr)) {
            parentColumnJoin = commonLogic.getColumnNameCustomRel(parentHierarhcyIndicaotr,parentHierarchyNo,sessionDTO);
                   
        }
        if (StringUtils.isNotBlank(parentHierarhcyIndicaotrDeduction)) {
            parentColumnJoinDeduction = commonLogic.getColumnNameCustomRel(parentHierarhcyIndicaotrDeduction,parentHierarchyNoDeduction,sessionDTO);
        }
        String countQuery = SQlUtil.getQuery("GET_CUSTOM_COUNT").replace(Constant.HIERARCHY_NO, !hierarchyIndicator.equalsIgnoreCase("D") ? getSelectedHierarchy(sessionDTO, hierarchyNo, hierarchyIndicator, levelNo,true) : commonLogic.getSelectedHierarchyDeduction(sessionDTO, hierarchyNo, hierarchyIndicator, levelNo,true))
                .replace("@REBATE", isProgram ? SQlUtil.getQuery(Constant.GET_COUNT_PROGRAM) : SQlUtil.getQuery(Constant.COUNT_PROGRAM_CATEGORY))
                .replace(Constant.AT_COLUMN_NAME, commonLogic.getColumnNameCustomDed(hierarchyIndicator)).replace(Constant.AT_DISCOUNT, getRebate(discountList))
                .replace("@FOR_CUSTOM", parentColumnJoin).replace("@CUSTOM", parentColumnJoinDeduction).replace(Constant.AT_RS_COLUMN, isProgram ? Constant.RS_CONTRACT_SID : Constant.PRICE_GROUP_TYPE).replace("@REPLACE", commonLogic.getSelectStatementCustom(hierarchyIndicator))
                .replace(DEDCUST_JOIN, hierarchyIndicator.equalsIgnoreCase("D") ? commonLogic.getDedCustomJoin(sessionDTO, hierarchyNo, hierarchyIndicator, levelNo) : StringUtils.EMPTY)
                .replace("@DEDUCTIONJOIN", !hierarchyIndicator.equalsIgnoreCase("D") ? commonLogic.getDedCustomJoin(sessionDTO, hierarchyNo, hierarchyIndicator, levelNo) : StringUtils.EMPTY)
                .replace(RELVALUE, sessionDTO.getDedRelationshipBuilderSid())
                .replace(Constant.RELVERSION, String.valueOf(sessionDTO.getDeductionRelationVersion()));

        if (StringUtils.isNotBlank(userGroup)) {
            countQuery = countQuery.concat(WHERE_USER_GROUP).concat(userGroup).concat("'");
        }
        return countQuery;
    }
    
    
    public String getDiscountCustomQueryCount(final SessionDTO sessionDTO, final String hierarchyIndicator, final int levelNo, final String userGroup,List<String> customViewDetails,boolean isCustom,List<String>  customDetailsList) {
        String hierarchyNo = String.valueOf(customDetailsList.get(1));
        int treeLevelNo = Integer.parseInt(String.valueOf(customDetailsList.get(NumericConstants.TWO)));
        String queryBuilder = SQlUtil.getQuery("GET_CUSTOM_COUNT_QUERY")
        .replace("@HIERARCHY_QUERY", insertAvailableHierarchyNoForCount(sessionDTO, hierarchyNo, hierarchyIndicator, (int) (isCustom ? treeLevelNo : levelNo), isCustom, isCustom ? Integer.parseInt(customViewDetails.get(0)) : 0, isCustom ? customViewDetails.get(NumericConstants.TWO) : StringUtils.EMPTY, isCustom ? customViewDetails.get(NumericConstants.FOUR) : StringUtils.EMPTY, isCustom ? customViewDetails.get(NumericConstants.NINE) : StringUtils.EMPTY, 0, 0, userGroup)) 
        .replace(DEDCUST_JOIN, commonLogic.getDedCustomJoinGenerate(sessionDTO, isCustom ? customViewDetails.get(NumericConstants.NINE) : StringUtils.EMPTY, hierarchyIndicator, levelNo))
        .replace(RELVALUE, sessionDTO.getDedRelationshipBuilderSid())
        .replace(Constant.RELVERSION, String.valueOf(sessionDTO.getDeductionRelationVersion()));
        queryBuilder += SQlUtil.getQuery("custom-view-count-condition-query");
        queryBuilder = queryBuilder.replace(Constant.RELJOIN, CommonLogic.getRelJoinGenerate(hierarchyIndicator,sessionDTO));
        return queryBuilder;
    }

    /**
     * Formating the discount like ('Base'),(Base2)
     *
     * @param discountList
     * @return eg ('Base'),(Base2)
     */
    public String getRebate(final List<String> discountList) {
        String format = "('@Rebate')";
        String comma = StringUtils.EMPTY;
        StringBuilder builder = new StringBuilder();
        String rsValue;
        for (String rs : discountList) {
            rsValue = rs.contains("~") ? rs.split("~")[1] : rs;
            builder.append(comma).append(format.replace("@Rebate", rsValue));
            comma = ",";
        }
        return builder.toString();
    }

    private String checkIsCustom(boolean isCustomHierarchy, String hierarchyIndicator, String customerHierarchyNo, String productHierarchyNo, String deductionHierarchyNo, String hierarchyNo, String customSql,SessionDTO session) {
        if (isCustomHierarchy) {
            customSql = customSql.replace("@CUSTOM_VIEW",commonLogic.getHierarchyJoinQuery(isCustomHierarchy,customerHierarchyNo,productHierarchyNo,commonLogic.replacePercentHierarchy(deductionHierarchyNo),hierarchyIndicator,session));
        } else {
            customSql = customSql.replace(Constant.HIERARCHY_NO, hierarchyNo);
        }
        customSql = customSql.replace("@CUSTOM_VIEW", StringUtils.EMPTY);
        return customSql;
    }

    public static String getRSDiscountHierarchyNo(List<String> discountList,SessionDTO session,int levelNo) {
        String levelNoQuery = getQueryRSDiscountHierarchyNo(discountList);
        List<String> levelNoList =  levelNo == 10 ? HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(levelNoQuery,session.getCurrentTableNames())):getRSHierarchyNo(discountList,session);
        StringBuilder levelNoFromQuery = new StringBuilder();
        for (String value : levelNoList) {
            levelNoFromQuery.append("('").append(value).append("')").append(',');
        }
        levelNoFromQuery = levelNoFromQuery.replace(levelNoFromQuery.lastIndexOf(","), levelNoFromQuery.length(), StringUtils.EMPTY);
        return levelNoFromQuery.toString();
    }

     private String getRebateJoin(String deductionSid,SessionDTO session,String levelNo) {
        String joinQuery="    IF OBJECT_ID('TEMPDB..#SELECTED_REBATE') IS NOT NULL\n" +
"                DROP TABLE #SELECTED_REBATE\n" +
"                CREATE TABLE #SELECTED_REBATE\n" +
"                (DED_HIE_NO VARCHAR(1000))             INSERT #SELECTED_REBATE\n" +
"              (DED_HIE_NO) "
                + "SELECT HIERARCHY_NO FROM RELATIONSHIP_LEVEL_DEFINITION WHERE RELATIONSHIP_LEVEL_VALUES IN\n" +
"              ("+deductionSid+")\n" +
"              and RELATIONSHIP_BUILDER_SID="+session.getDedRelationshipBuilderSid()+
"              and VERSION_NO = "+session.getDeductionRelationVersion();
        joinQuery +=" AND LEVEL_NO = "+levelNo;
        return joinQuery;
    }
    
    

    public String getSelectedHierarchy(SessionDTO sessionDTO, String hierarchyNo, String hierarchyIndicator, int levelNo,boolean isCount) {

        if (levelNo == 0) {
            throw new IllegalArgumentException("Invalid Level No:" + levelNo);
        }

        Map<String, List> relationshipLevelDetailsMap = sessionDTO.getHierarchyLevelDetails();
        StringBuilder stringBuilder = new StringBuilder();

		boolean isNotFirstElement = false;
		boolean isHierarchyNoNotAvailable = StringUtils.isEmpty(hierarchyNo) || "%".equals(hierarchyNo)
				|| "D".equals(hierarchyIndicator);
		int i = 1;
		for (Map.Entry<String, List> entry : relationshipLevelDetailsMap.entrySet()) {
			if ((Integer.parseInt(entry.getValue().get(2).toString()) == levelNo
					&& hierarchyIndicator.equals(entry.getValue().get(4).toString()))
					&& (isHierarchyNoNotAvailable || entry.getKey().startsWith(hierarchyNo))) {

				if (isNotFirstElement) {
					stringBuilder.append(",\n");
				}
				stringBuilder.append("('");
				stringBuilder.append(entry.getKey());
				stringBuilder.append(isCount ? "')" : "'," + i++ + ")");

				isNotFirstElement = true;
			}
		}
		if (sessionDTO.getHierarchyLevelDetails().isEmpty()) {
			stringBuilder.append("('");
			stringBuilder.append("')");
		}
		return stringBuilder.toString();
	}
    
}
