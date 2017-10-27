/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.discountProjection.logic;

import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.FrequencyConstants.*;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 * This class just copied from NmDiscountProjMasterFinderImpl.
 *
 * @author Sooriya.Lakshmanan
 */
public class DiscountQueryBuilder {

    private static final Logger LOGGER = Logger.getLogger(DiscountQueryBuilder.class);

    public static final String PROGRAM = "Program";
    public static final String PROGRAM_CATEGORY = "Program Category";
    CommonLogic commonLogic = new CommonLogic();

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

                LOGGER.debug(" Baseline Periods " + baselinePeriods);
                LOGGER.debug(" Selected Periods " + selectedPeriods);
                masterTableUpdateQuery = "UPDATE DM SET DM.BASELINE_PERIODS = '" + baselinePeriods + "', DM.SELECTED_PERIODS = '" + selectedPeriods + "' FROM ST_NM_DISCOUNT_PROJ_MASTER DM ";
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

            String rsQuery = levelType.equals(Constants.PROGRAM)?" RS.RS_NAME " :" DPM.PRICE_GROUP_TYPE "; 
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


                discountProjectionTableUpdateQuery += "WHERE  DPM.CHECK_RECORD = 1\n"
                        + "       AND "+rsQuery+" = '" + discountName + "'\n"
                        + "       AND DP.PERIOD_SID IN(SELECT PERIOD_SID from \"PERIOD\" PR WHERE " + period + " IN (" + selectedPeriodsToUpdate + ")) ";



            }
        } catch (Exception e) {
            LOGGER.error(e);
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
                    + " From ST_NM_DISCOUNT_PROJ_MASTER M";
            if(!userGroup.isEmpty()){
                query += " and M.USER_GROUP = '" + userGroup + "'\n";
            }
            HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query,session.getCurrentTableNames()));
        } catch (Exception e) {
            LOGGER.error(e);
            LOGGER.error(query);
        } 
    }

    public int updateCheckRecord(SessionDTO session, boolean checkValue, String hierarchyNo,
            String hierarchyIndicator, boolean isCustomView, List<String> customViewDetails, boolean isProgram, List<String> discountList) {
        String customSql = "";
        LOGGER.debug(" inside updateCheckRecord");
        try {

            String hierarchy = "";
            // C indicates customer, P indicates product
            if (hierarchyIndicator.equals("C")) {
                hierarchy = "CUST_HIERARCHY_NO ";
            } else if (hierarchyIndicator.equals("P")) {
                hierarchy = "PROD_HIERARCHY_NO ";
            }
            if (discountList != null && !discountList.isEmpty()) {
                String selectedDiscounts = getRSDiscountSids(discountList);
                if (isProgram) {

                    customSql = SQlUtil.getQuery("UPDATE_PROGRAM_CHECKRECORD").replace("@CHECK_RECORD", String.valueOf(checkValue ? 1 : 0))
                            .replace("@HIERARCHY_COLUMN", hierarchy)
                            .replace(Constant.HIERARCHY_NO, hierarchyNo)
                            .replace("@PROGRAMS", selectedDiscounts);

                } else {
                    customSql = SQlUtil.getQuery("UPDATE_PROGRAM_CATEGORY_CHECKRECORD").replace("@CHECK_RECORD", String.valueOf(checkValue ? 1 : 0))
                            .replace("@HIERARCHY_COLUMN", hierarchy)
                            .replace(Constant.HIERARCHY_NO, hierarchyNo)
                            .replace("@PROGRAM_CATEGORY", selectedDiscounts);
                }
            }
            if (isCustomView) {
                customSql = checkIsCustom(isCustomView, hierarchyIndicator, customViewDetails.get(NumericConstants.TWO), customViewDetails.get(NumericConstants.FOUR), hierarchyNo, customSql);
            } else {
                 customSql = checkIsCustom(isCustomView,hierarchyIndicator,StringUtils.EMPTY,StringUtils.EMPTY,hierarchyNo,customSql);
                }
                return HelperTableLocalServiceUtil.executeUpdateQueryCount(QueryUtil.replaceTableNames(customSql,session.getCurrentTableNames()));

        } catch (Exception e) {
            LOGGER.error(e);
            LOGGER.error(customSql);
            return 0;
        } 
    }
    
    
     
    public void massUpdate(SessionDTO session, String frequency, List<Integer> startAndEndPeriods, String selectedField, String fieldValue,
           List<String> checkedDiscounts, boolean isProgram, List<String[]> massUpdateData, List<String> selectedPeriods) {

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
                    }
                    switch (endFreq) {
                        case 1:
                            endMonth = NumericConstants.SIX;
                            break;
                        case NumericConstants.TWO:
                            endMonth = NumericConstants.TWELVE;
                            break;
                    }
                }
                if (frequency.equals(MONTHLY.getConstant())) {
                    startMonth = startFreq;
                    endMonth = endFreq;
                }
                String selectedDiscounts =  getRSDiscountSids(checkedDiscounts);
                String discountType = "M.PRICE_GROUP_TYPE";
                String level = isProgram ? "Program" : "Program Category";
                if (isProgram) {
                    discountType = "RS.RS_NAME";
                }
                String declareStatement = " DECLARE @START_MONTH INT=" + startMonth + "\n"
                        + " DECLARE @START_YEAR INT=" + startYear + "\n"
                        + " DECLARE @END_MONTH INT=" + endMonth + "\n"
                        + " DECLARE @END_YEAR INT=" + endYear + "\n"
                        + "DECLARE       @DISCOUNT_AMOUNT NUMERIC(22, 6)=" + fieldValue + "\n"
                        + "DECLARE          @FREQUENCY_COUNT INT\n"
                           + "DECLARE @LEVEL VARCHAR(50) ='" +level + "'" + "\n"
                        + "DECLARE @FREQUENCY CHAR(1)='" + frequency.charAt(0) + "'";

                if ("Discount Rate".equals(selectedField) || "RPU".equals(selectedField)) {
                    customSql = declareStatement + SQlUtil.getQuery("discRatemassPopulate");
                    customSql = customSql.replaceAll("@SELDISC", "" + selectedDiscounts);
                    customSql = customSql.replaceAll("@RATE", "" + fieldValue);
                    customSql = customSql.replace("@COLUMN1", column);
                    customSql = customSql.replace("@COL2", column2);
                    customSql = customSql.replace("@DISCTYPE ", discountType + " IN ");
                } else {
                   String csustomSql = isProgram ? SQlUtil.getQuery("GET_COUNT_PROGRAM") : SQlUtil.getQuery("GET_COUNT_PROGRAM_CATEGORY");
                if (isProgram) {
                        csustomSql = csustomSql.replace("@DISCOUNT", getRebate(selectedPeriods));
                    } else {
                        csustomSql = csustomSql.replace("@DISCOUNT", getRebate(checkedDiscounts));
                    }
                    csustomSql += SQlUtil.getQuery("MASSUPDATE-DISCOUNT-AMOUNT");
                    csustomSql = csustomSql.replace("[?Hierarchy-Combination]", getCustomHierarchies(massUpdateData));
                    customSql = declareStatement + csustomSql;
                }
            }
            HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(customSql,session.getCurrentTableNames()));
        } catch (Exception e) {
            LOGGER.error(e);
            LOGGER.error(customSql);
        }
    }
    
      private String getCustomHierarchies(List<String[]> massUpdateData) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean isNotFirstElement = false;
        int i = 1;
        for (String[] hierarchyData : massUpdateData) {

            if (isNotFirstElement) {
                stringBuilder.append(",\n");
            }
            stringBuilder.append("('");
            stringBuilder.append(hierarchyData[0]).
                    append("',");
            if (hierarchyData[2].isEmpty()) {
                stringBuilder.append("NULL");
            } else {
                stringBuilder.append("'").append(hierarchyData[2]).append("'");
            }

            stringBuilder.append(",").append("'").append(hierarchyData[1]).append("',").append(i++);
            stringBuilder.append(")");
            isNotFirstElement = true;
        }
        return stringBuilder.toString();
    }
    
    public boolean saveGroupValues(SessionDTO session, String hierarchyNo, String groupValue, boolean isProgram,
              List<String> discountList) {
        String customSql = StringUtils.EMPTY;
        LOGGER.debug(" entering saveGroupValues");
        try {

            String rebateQuery = isProgram ? SQlUtil.getQuery(Constant.GET_COUNT_PROGRAM) : SQlUtil.getQuery(Constant.COUNT_PROGRAM_CATEGORY);
            rebateQuery = rebateQuery.replace(Constant.AT_DISCOUNT, getRebate(discountList));

            customSql = SQlUtil.getQuery("MANUAL_USER_GROUP_SAVE")
                    .replace("@REBATE_QUERY", rebateQuery)
                    .replace(Constant.AT_USER_GROUP, groupValue)
                    .replace("@HIERARCHYNO", hierarchyNo)
                    .replace("@REBATE_NAME", isProgram ? Constant.RS_CONTRACT_SID : Constant.PRICE_GROUP_TYPE);

             HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(customSql,session.getCurrentTableNames()));
            return true;
        } catch (Exception e) {
            LOGGER.error(e);
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
                    .replace("?F", String.valueOf(frequency.charAt(0)));
            String rebateTempQuery = SQlUtil.getQuery(isProgram ? Constant.GET_COUNT_PROGRAM : Constant.COUNT_PROGRAM_CATEGORY)
                    .replace(Constant.AT_DISCOUNT, "('" + discountName + "')");
            customSql = SQlUtil.getQuery("MANUAL_ENTRY_DISCOUNT").replace("@PERIOD_TABLE", queryPeriod)
                    .replace("@TEMP_TABLE_DISCOUNT", rebateTempQuery)
                    .replace(Constant.AT_COLUMN_NAME, commonLogic.getColumnName(hierarchyIndicator))
                    .replace("?Y", String.valueOf(year)).replace(Constant.AT_RS_COLUMN, isProgram?Constant.RS_CONTRACT_SID:Constant.PRICE_GROUP_TYPE);

            if ("GROWTH".equals(refreshName)) {
                customSql = customSql.replace("@SETTER", "DPT.GROWTH = " + fieldValue );
            } else {
                customSql = customSql.replace("@SETTER", "DPT.refreshed_value = " + fieldValue + " ,DPT.refreshed_name = '" + refreshName + "' ");
            }

            if (!frequency.equals(ANNUALLY.getConstant())) {
                customSql += "and I.period = " + period;
                    }

        customSql = checkIsCustom(isCustomHierarchy,hierarchyIndicator,customViewDetails.get(NumericConstants.TWO),customViewDetails.get(NumericConstants.FOUR),hierarchyNo,customSql);
      HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(customSql,session.getCurrentTableNames()));
            return Boolean.TRUE;
        } catch (Exception e) {
            LOGGER.error(e);
            LOGGER.error(customSql);
            return Boolean.FALSE;
        } 
    }
      
     public List<String> getGroupValues(SessionDTO session, List<String> discountList) {
        String customSql = StringUtils.EMPTY;
        LOGGER.debug(" entering getGroupValues");
        try {
            if (discountList != null && !discountList.isEmpty()) {
                String discountType = discountList.get(0);
                discountList.remove(0);
                String selectedDiscounts = getRSDiscountSids(discountList);
                if (discountType.equals(Constants.PROGRAM)) {
                    customSql = SQlUtil.getQuery("Program").replace("@PROGRAMS", selectedDiscounts);
                } else {
                    customSql = SQlUtil.getQuery("Program_Category").replace("@PROGRAM_CATEGORY", selectedDiscounts);
                }
            }
            return (List<String>) HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(customSql,session.getCurrentTableNames()));
        } catch (Exception e) {
            LOGGER.error(e);
            LOGGER.error(customSql);
            return Collections.emptyList();
        } 
    }
     
     public int getCheckedRecordCount(SessionDTO session, boolean isProgram, List<String> discountList) {
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
                    + discountTypeQuery + " AND DP.CHECK_RECORD=1";

            list =  HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(customSql,session.getCurrentTableNames()));
        } catch (Exception e) {
            LOGGER.error(e);
            LOGGER.error(customSql);
            return -1;
        } 

        if (list != null && !list.isEmpty()) {
            return Integer.valueOf(String.valueOf(list.get(0)));
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
            LOGGER.error(e);
            LOGGER.error(sb.toString());
            return Collections.emptyList();
        } 
    }
    
    
    
    public List getDiscountProjection(final boolean isProgram, final String frequency, final List<String> discountList,
            final SessionDTO session, final String hierarchyNo, final String hierarchyIndicator, final int levelNo, final boolean isCustom, final List<String> customViewDetails, final int treeLevelNo,
            final int start, final int end, final String userGroup) {
        getDiscountPrograms(session, "DiscountProgram");
        String queryBuilder = SQlUtil.getQuery("discountGenerate");
        queryBuilder = queryBuilder.replace("?RS", isProgram ? "R" : "P") //Indicator for Program or program category
                .replace("?F", String.valueOf(frequency.charAt(0))) //Selected Frequency initial char
                .replaceAll("@SELECTED_REBATE", getRSDiscountSids(discountList)) // Selected RS
                .replace("@REBATE_QUERY", isProgram ? SQlUtil.getQuery(Constant.GET_COUNT_PROGRAM) : SQlUtil.getQuery(Constant.COUNT_PROGRAM_CATEGORY))
                .replace(Constant.AT_DISCOUNT, getRebate(discountList))
                .replace("@HIERARCHY_QUERY", insertAvailableHierarchyNo(session, hierarchyNo, hierarchyIndicator, isCustom ? treeLevelNo : levelNo, isCustom, isCustom ? Integer.valueOf(customViewDetails.get(0)) : 0, isCustom ? customViewDetails.get(NumericConstants.TWO) : StringUtils.EMPTY, isCustom ? customViewDetails.get(NumericConstants.FOUR) : StringUtils.EMPTY, start, end, userGroup)) // Selected RS
                .replace("@REBATE_COLUMN", isProgram ? Constant.RS_CONTRACT_SID : Constant.PRICE_GROUP_TYPE)
                .replace(Constant.AT_COLUMN_NAME, commonLogic.getColumnName(hierarchyIndicator))
                .replace("@RS_CONTRACT_SID", isProgram ? " DM.RS_CONTRACT_SID, " : StringUtils.EMPTY)
                .replace("@DPM_RS_CONTRACT_SID", isProgram ? " ,DPM.RS_CONTRACT_SID AS RS_CONTRACT_SID " : StringUtils.EMPTY)
                .replace("@ORDER_RS_CONTRACT_SID", isProgram ? " DPM.RS_CONTRACT_SID, " : StringUtils.EMPTY)
                .replace("@RS_CONTRACT_ID", isProgram ? " RS_CONTRACT_SID, " : StringUtils.EMPTY)
                .replace("@SELECTED_HIERARCHY_NO", isProgram ? " #SELECTED_HIERARCHY_NO " : " (select distinct HIERARCHY_NO,DISCOUNT,ccp_details_sid from #SELECTED_HIERARCHY_NO) ")
                .replace("@RS_ID", isProgram ? "  ,s.rs_contract_sid " : StringUtils.EMPTY )
                .replace("@TABLENAME", " #ST_NM_DISCOUNT_PROJ_MASTER " )
                .replace("@PROGCATPROG", isProgram ? " RS_CONTRACT_SID " : "  PRICE_GROUP_TYPE ")
                .replace("@RS_NO", isProgram ? "  ,C.RS_CONTRACT_SID " : StringUtils.EMPTY )
                .replace("@CONDITION1", isProgram ? "   AND DPM.RS_CONTRACT_SID = AD.RS_CONTRACT_SID " : StringUtils.EMPTY )
                .replace("@CONDITION2", isProgram ? "  AND SA.RS_CONTRACT_SID = AD.RS_CONTRACT_SID " : StringUtils.EMPTY )
                .replace("@CONDITION3", isProgram ? "  HIERARCHY_NO " : " DPM.HIERARCHY_NO " );

        if (StringUtils.isNotBlank(userGroup)) {
            queryBuilder = queryBuilder.replace(Constant.AT_USER_GROUP, " AND USER_GROUP = '" + userGroup + "'");
        }
        queryBuilder = isProgram ? queryBuilder.replace("@PROGJOIN", " JOIN #SELECTED_REBATE SR ON SR.PRICE_GROUP_TYPE = SPM.RS_CONTRACT_SID ")
                : queryBuilder.replace("@PROGJOIN", " JOIN #SELECTED_REBATE SR ON SR.PRICE_GROUP_TYPE = SPM.PRICE_GROUP_TYPE ");
        queryBuilder = queryBuilder.replace(Constant.AT_USER_GROUP, StringUtils.EMPTY);
        queryBuilder = QueryUtil.replaceTableNames(queryBuilder, session.getCurrentTableNames());
        if(queryBuilder.contains("('')")){
        return Collections.EMPTY_LIST;
        }else{
        return HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(queryBuilder, session.getCurrentTableNames()));
        }
    }
    
    private String getUPPSJoin(boolean isProgram) {
        String query;
        if (isProgram) {
            query = " INNER JOIN #ST_NM_DISCOUNT_PROJ_MASTER IU\n"
                    + "  ON IU.CCP_DETAILS_SID = S.CCP_DETAILS_SID\n"
                    + "  AND C.RS_CONTRACT_SID=IU.RS_CONTRACT_SID ";
        } else {
            query = " INNER JOIN \n"
                    + "                                    (\n"
                    + "                                    SELECT CCP_DETAILS_SID,MAX(UPPS) UPPS,PRICE_GROUP_TYPE FROM \n"
                    + "                                    (\n"
                    + "                                      SELECT *,ROW_NUMBER() OVER(PARTITION BY CCP_DETAILS_SID,PRICE_GROUP_TYPE  order by CCP_DETAILS_SID)  RNO\n"
                    + "                                         FROM #ST_NM_DISCOUNT_PROJ_MASTER \n"
                    + "                                    )A WHERE A.RNO=1"
                    + " GROUP BY CCP_DETAILS_SID,\n"
                    + "						   PRICE_GROUP_TYPE )IU\n"
                    + "\n"
                    + "                                    ON S.CCP_DETAILS_SID=IU.CCP_DETAILS_SID AND IU.PRICE_GROUP_TYPE = C.DISCOUNT ";
        }
        return query;
    }

    public String getRSDiscountSids(List<String> discountList) {
        String framedString = StringUtils.EMPTY;
        for (String value : discountList) {
            framedString += " '" + (value.contains("~") ? value.split("~")[1] : value) + "'" + ",";
        }
        framedString = framedString.substring(0, framedString.length() - 1);
        return framedString;
    }

    private String insertAvailableHierarchyNo(SessionDTO sessionDTO, String hierarchyNo, String hierarchyIndicator, int levelNo, boolean isCustomHierarchy,
            final int customId, final String customerHierarchyNo, final String productHierarchyNo, final int startIndex, final int endIndex, final String userGroup) {

        String sql;
        sql = SQlUtil.getQuery("discount-selected-hierarchy");
        if (isCustomHierarchy) {
            String currentHierarchyIndicator = commonLogic.getHiearchyIndicatorFromCustomView(sessionDTO, customId, levelNo);
            int actualLevelNo = commonLogic.getActualLevelNoFromCustomView(sessionDTO, customId, levelNo);
            switch (String.valueOf(currentHierarchyIndicator)) {
                case Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY:
                    sql = sql.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, commonLogic.getSelectedHierarchy(sessionDTO, customerHierarchyNo, currentHierarchyIndicator, actualLevelNo));
                    break;
                case Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY:
                    sql = sql.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, commonLogic.getSelectedHierarchy(sessionDTO, productHierarchyNo, currentHierarchyIndicator, actualLevelNo));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Hierarchy Indicator:" + currentHierarchyIndicator);
            }
        } else {
            sql = sql.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, commonLogic.getSelectedHierarchy(sessionDTO, hierarchyNo, hierarchyIndicator, levelNo));
        }
        sql = sql.replace("[?SELECTED_HIERARCHY_JOIN]", commonLogic.getHierarchyJoinQuery(sessionDTO, customId, levelNo, isCustomHierarchy, hierarchyIndicator, customerHierarchyNo, productHierarchyNo));
        sql += getJoinForDP(Constant.ALL_DISCOUNT_GROUP, userGroup);
        sql = sql.replace("@START", String.valueOf(startIndex));
        sql = sql.replace("@OFFSET", String.valueOf(endIndex));
        return sql;
    }

    private String getJoinForDP(String defaultValue, String groupFilterValue) {
        String sql = SQlUtil.getQuery("discount-join");
        sql += " JOIN RS_CONTRACT RSC ON RSC.RS_CONTRACT_SID=SPM.RS_CONTRACT_SID\n ";
        sql += " @PROGJOIN ";
        if (StringUtils.isNotBlank(groupFilterValue) && !defaultValue.equals(groupFilterValue)) {
            sql += SQlUtil.getQuery("user-group-condition");
            sql = sql.replace("[?USER_GROUP]", groupFilterValue);
        }
        return sql;
    }            

    /**
     * Customer and Product view,  discount count query
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
    public String getDiscountCountQuery(SessionDTO sessionDTO, String hierarchyNo, int levelNo, String hierarchyIndicator, boolean isProgram, List<String> discountList,final String userGroup) {
        String countQuery = SQlUtil.getQuery("GET_COUNT").replace(Constant.HIERARCHY_NO, commonLogic.getSelectedHierarchy(sessionDTO, hierarchyNo, hierarchyIndicator, levelNo))
                .replace("@REBATE", isProgram ? SQlUtil.getQuery(Constant.GET_COUNT_PROGRAM) : SQlUtil.getQuery(Constant.COUNT_PROGRAM_CATEGORY))
                .replace(Constant.AT_COLUMN_NAME, commonLogic.getColumnName(hierarchyIndicator)).replace(Constant.AT_DISCOUNT, getRebate(discountList))
                .replace("@FOR_CUSTOM", StringUtils.EMPTY).replace(Constant.AT_RS_COLUMN, isProgram?Constant.RS_CONTRACT_SID:Constant.PRICE_GROUP_TYPE);
           if(StringUtils.isNotBlank(userGroup)){
               countQuery = countQuery.concat(" WHERE USER_GROUP = '").concat(userGroup).concat("'");
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
            final String hierarchyNo, final String parentHierarhcyIndicaotr, final String parentHierarchyNo, final List<String> discountList, boolean isProgram,final String userGroup) {
        String parentColumnJoin = StringUtils.EMPTY;
        if (StringUtils.isNotBlank(parentHierarhcyIndicaotr)) {
            parentColumnJoin = SQlUtil.getQuery("CUSTOM_JOIN").replace("@COLUMN", commonLogic.getColumnName(parentHierarhcyIndicaotr))
                    .replace(Constant.HIERARCHY_NO, parentHierarchyNo);
        }
        String countQuery = SQlUtil.getQuery("GET_COUNT").replace(Constant.HIERARCHY_NO, commonLogic.getSelectedHierarchy(sessionDTO, hierarchyNo, hierarchyIndicator, levelNo))
                .replace("@REBATE", isProgram ? SQlUtil.getQuery(Constant.GET_COUNT_PROGRAM) : SQlUtil.getQuery(Constant.COUNT_PROGRAM_CATEGORY))
                .replace(Constant.AT_COLUMN_NAME, commonLogic.getColumnName(hierarchyIndicator)).replace(Constant.AT_DISCOUNT, getRebate(discountList))
                .replace("@FOR_CUSTOM", parentColumnJoin).replace(Constant.AT_RS_COLUMN, isProgram ? Constant.RS_CONTRACT_SID : Constant.PRICE_GROUP_TYPE);

        if (StringUtils.isNotBlank(userGroup)) {
            countQuery = countQuery.concat(" WHERE USER_GROUP = '").concat(userGroup).concat("'");
        }
        return countQuery;
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
            rsValue=rs.contains("~")?rs.split("~")[1]:rs;
            builder.append(comma).append(format.replace("@Rebate",rsValue ));
            comma = ",";
        }
        return builder.toString();
    }
    
    
    private String checkIsCustom(boolean isCustomHierarchy, String hierarchyIndicator, String customerHierarchyNo, String productHierarchyNo, String hierarchyNo, String customSql) {
        String parentHierarchyIndicator = StringUtils.EMPTY;
        String parentHierarchyNo = StringUtils.EMPTY;
        if ("C".equalsIgnoreCase(hierarchyIndicator) && StringUtils.isNotBlank(productHierarchyNo)) {
            parentHierarchyIndicator = "P";
            parentHierarchyNo = productHierarchyNo;
        } else if ("P".equalsIgnoreCase(hierarchyIndicator) && StringUtils.isNotBlank(customerHierarchyNo)) {
            parentHierarchyIndicator = "C";
            parentHierarchyNo = customerHierarchyNo;
        }

        if (isCustomHierarchy) {
            customSql = customSql.replace(Constant.HIERARCHY_NO, "C".equalsIgnoreCase(hierarchyIndicator) ? customerHierarchyNo : productHierarchyNo);
            if (StringUtils.isNotBlank(parentHierarchyNo)) {
                customSql = customSql.replace("@CUSTOM_VIEW", " AND CCPH." + commonLogic.getColumnName(parentHierarchyIndicator) + " LIKE '" + parentHierarchyNo + "%'");
            }
        } else {
            customSql = customSql.replace(Constant.HIERARCHY_NO, hierarchyNo);
        }
        customSql = customSql.replace("@CUSTOM_VIEW", StringUtils.EMPTY);
        return customSql;
    }
}