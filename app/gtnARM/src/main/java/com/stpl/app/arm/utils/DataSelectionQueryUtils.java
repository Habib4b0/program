/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.utils;

import com.stpl.app.arm.common.CommonFilterLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.service.HelperTableLocalServiceUtil;

import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.SysDataSourceConnection;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMConstants;
import com.vaadin.v7.data.Container;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author
 */
public class DataSelectionQueryUtils {

    protected DataSelectionQueryUtils() {

    }
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSelectionQueryUtils.class);

    public static Object tempOperation(final Map<String, Object> input) {
        Map<String, String> valueList = new HashMap<>();
//        try {
//            String customSql = CustomSQLUtil.get("getHierarchyTableDetails");
//            for (String key : input.keySet()) {
//                customSql = customSql.replace(key, String.valueOf(input.get(key)));
//
//            }
//            List tempList = HelperTableLocalServiceUtil.executeSelectQuery(customSql);
//            for (int i = tempList.size() - 1; i >= 0; i--) {
//                customSql = CustomSQLUtil.get("getRelationshipLevelValues");
//                Object[] tempListObject = (Object[]) tempList.get(i);
//                customSql = customSql.replace("?FIELD", String.valueOf(tempListObject[0]));
//                customSql = customSql.replace("?TABLE", String.valueOf(tempListObject[1]));
//                customSql = customSql.replace("?IDCOL", String.valueOf(tempListObject[NumericConstants.TWO]));
//                customSql = customSql.replace("?LNO", String.valueOf(tempListObject[NumericConstants.THREE]));
//                customSql = customSql.replace("?RBSID", String.valueOf(input.get("?RBSID")));
//                LOGGER.debug("customSql--" + customSql);
//                List tempValueList = HelperTableLocalServiceUtil.executeSelectQuery(customSql);
//                for (int j = tempValueList.size() - 1; j >= 0; j--) {
//                    Object[] tempObject = (Object[]) tempValueList.get(j);
//                    valueList.put(String.valueOf(tempObject[0]), String.valueOf(tempObject[1]));
//                }
//            }
//            LOGGER.debug("valueList ---" + valueList.size());
//        } catch (Exception e) {
//            LOGGER.error("Error in tempOperation :" + e);
//        }
        return valueList;
    }

    public static List getLevelsFromHierarchy(final Map<String, Object> parameters) {
        String customSql = StringUtils.EMPTY;
        try {
            LOGGER.debug("Entering findViewByName method ");
            customSql = SQlUtil.getQuery("getLevelsFromHierarchy");
            if (StringUtils.isNotEmpty(String.valueOf(parameters.get(CommonConstant.HIERARCHY_ID)))
                    && StringUtils.isNotBlank(String.valueOf(parameters.get(CommonConstant.HIERARCHY_ID)))) {
                customSql = customSql.replace("?", String.valueOf(parameters.get(CommonConstant.HIERARCHY_ID)).trim());
            }
            LOGGER.debug("customSql ---" + customSql);
            List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(customSql);
            LOGGER.debug("list ---" + list.size());
            LOGGER.debug(CommonConstant.END_OF_FIND_VIEW_BY_NAME_METHOD);
            return list;
        } catch (Exception e) {
            LOGGER.error("Error in getLevelsFromHierarchy :" + e);
            LOGGER.error("Error in getLevelsFromHierarchy :" + customSql);
            return Collections.emptyList();
        }
    }

    public static List getCustomerInnerLevel(final String relationshipSid, int levelNo, int hierarchyId, List<Integer> rsContractSidList) {
        StringBuilder queryBuilder = new StringBuilder(StringUtils.EMPTY);
        try {
            queryBuilder.append(insertIntoCcpMap(String.valueOf(hierarchyId == 0 ? StringUtils.EMPTY : hierarchyId), relationshipSid, StringUtils.EMPTY, StringUtils.EMPTY));
            int index = 0;
            queryBuilder.append(SQlUtil.getQuery("getCustomerHierarchyInnerLevel"));
            index = queryBuilder.indexOf("?");
            queryBuilder.replace(index, index + 1, String.valueOf(relationshipSid));
            index = queryBuilder.indexOf("?", index);
            queryBuilder.replace(index, index + 1, String.valueOf(hierarchyId));
            index = queryBuilder.indexOf("?", index);
            queryBuilder.replace(index, index + 1, String.valueOf(levelNo));
            String query = queryBuilder.toString();
            String createTempDeduction = "";
            String joinTempDeduction = "";
            if (rsContractSidList != null && !rsContractSidList.isEmpty()) {
                String deductionID = CommonUtils.collectionToString(rsContractSidList, false, false, false, true);
                createTempDeduction = SQlUtil.getQuery("createTempDeduction");
                joinTempDeduction = SQlUtil.getQuery("joinTempDeduction");
                createTempDeduction = createTempDeduction.replace("?", deductionID);
            }
            query = query.replace("$$CREATETEMPDEDUCTION$$", createTempDeduction);
            query = query.replace("$$JOINTEMPDEDUCTION$$", joinTempDeduction);
            LOGGER.debug("query ---" + query);
            List<Object[]> returnList = HelperTableLocalServiceUtil.executeSelectQuery(query);
            LOGGER.debug("returnList ---" + returnList.size());
            return returnList;
        } catch (Exception ex) {
            LOGGER.error(ex + " in getInnerLevel()");
            LOGGER.error(queryBuilder.toString());
            return Collections.emptyList();
        }
    }

    public static List getProductInnerLevel(List<Integer> levelProdValues, final String prodRelationshipSid, List<Integer> rsContractSidList, List<Integer> customerSidList,
            boolean isNdc, final String custRelationshipSid) {
        StringBuilder queryBuilder = new StringBuilder(StringUtils.EMPTY);

        try {
            int index = 0;
            queryBuilder.append(insertIntoCcpMap(levelProdValues.get(4) == 0 ? StringUtils.EMPTY : String.valueOf(levelProdValues.get(4)), custRelationshipSid, levelProdValues.get(1) == 0 ? StringUtils.EMPTY : String.valueOf(levelProdValues.get(1)), prodRelationshipSid));
            queryBuilder.append(SQlUtil.getQuery("getProductHierarchyInnerLevel"));
            index = queryBuilder.indexOf("?");
            queryBuilder.replace(index, index + 1, String.valueOf(prodRelationshipSid));
            index = queryBuilder.indexOf("?", index);
            queryBuilder.replace(index, index + 1, String.valueOf(levelProdValues.get(1)));
            index = queryBuilder.indexOf("?", index);
            queryBuilder.replace(index, index + 1, String.valueOf(levelProdValues.get(0)));
            String query = queryBuilder.toString();
            String createTempDeduction = "";
            String joinTempDeduction = "";
            String createCustRel = "";
            String useCustRel = "";
            String joinCustRel = "";
            String joinBussinessUnit = "";
            String joinGlComp = "";
            String selNdc = "";
            String joinNdc = "";
            if (rsContractSidList != null && !rsContractSidList.isEmpty()) {
                String deductionID = CommonUtils.collectionToString(rsContractSidList, false, false, false, true);
                createTempDeduction = SQlUtil.getQuery("createTempDeduction");
                joinTempDeduction = SQlUtil.getQuery("joinTempDeduction");
                createTempDeduction = createTempDeduction.replace("?", deductionID);
            }
            if (customerSidList != null && !customerSidList.isEmpty()) {
                String custRelID = CommonUtils.collectionToString(customerSidList, false, false, false, true);
                createCustRel = SQlUtil.getQuery("createSelTempCustRelation");
                useCustRel = SQlUtil.getQuery("useSelTempCustRelation");
                joinCustRel = SQlUtil.getQuery("joinSelTempCustRelation");
                createCustRel = createCustRel.replace("?", custRelID);
                useCustRel = useCustRel.replace("?", custRelationshipSid);
            }
            if (levelProdValues.get(3) != 0) {
                joinGlComp = SQlUtil.getQuery("joinGlComp");
                joinGlComp = joinGlComp.replace("?", String.valueOf(levelProdValues.get(3)));
            }
            // commented Because Business unit is yet to implement
            if (levelProdValues.get(2) != 0) {
                joinBussinessUnit = SQlUtil.getQuery("joinBusinessUnit");
                joinBussinessUnit = joinBussinessUnit.replace("?", String.valueOf(levelProdValues.get(2)));
            }

            if (isNdc) {
                selNdc = SQlUtil.getQuery("selectProdNDC");
                joinNdc = SQlUtil.getQuery("joinProdNDC");
            }

            query = query.replace("$$CREATETEMPDEDUCTION$$", createTempDeduction);
            query = query.replace("$$JOINTEMPDEDUCTION$$", joinTempDeduction);
            query = query.replace("$$CREATETEMPCUSTSELECTION$$", createCustRel);
            query = query.replace("$$USETEMPCUSTSELECTION$$", useCustRel);
            query = query.replace("$$JOINTEMPCUSTSELECTION$$", joinCustRel);
            query = query.replace("$$JOINGLCOMP$$", joinGlComp);
            query = query.replace("$$JOINBUSINESSUNIT$$", joinBussinessUnit);
            query = query.replace("$$SELECTPRODNDC$$", selNdc);
            query = query.replace("$$JOINPRODNDC$$", joinNdc);
            LOGGER.debug("query ---" + query);
            List<Object[]> returnList = HelperTableLocalServiceUtil.executeSelectQuery(query);
            LOGGER.debug("returnList ---" + returnList.size());
            return returnList;
        } catch (Exception ex) {
            LOGGER.error(ex + " in getInnerLevel()");
            LOGGER.error(queryBuilder.toString());
            return Collections.emptyList();
        }
    }

    public static List getParentLevels(final int relationshipLevelSid, final Map<String, Object> parameters, String relationshipBuilderSid) {
        StringBuilder queryBuilder = new StringBuilder(StringUtils.EMPTY);

        try {
            if (parameters.get(CommonConstant.INDICATOR) != null && !StringUtils.isBlank(String.valueOf(parameters.get(CommonConstant.INDICATOR)))
                    && !ARMUtils.NULL.equals(String.valueOf(parameters.get(CommonConstant.INDICATOR))) && "getParentLevelsWithHierarchyNo".equalsIgnoreCase(String.valueOf(parameters.get(CommonConstant.INDICATOR)))) {
                queryBuilder.append(SQlUtil.getQuery("getParentLevels"));
                queryBuilder.replace(queryBuilder.indexOf(CommonConstant.RELATION_SID), CommonConstant.RELATION_SID.length() + queryBuilder.lastIndexOf(CommonConstant.RELATION_SID), relationshipBuilderSid);
                queryBuilder.replace(queryBuilder.indexOf("?"), queryBuilder.indexOf("?") + 1, String.valueOf(parameters.get("hierarchyNos")));
            } else {
                queryBuilder.append(SQlUtil.getQuery("getParentLevelsWithoutHier"));
                queryBuilder.append(" RBLD.relationship_Level_Sid = '");
                queryBuilder.append(relationshipLevelSid);
                queryBuilder.append("')");
                if (parameters.get("parent") != null) {
                    queryBuilder.append(" AND RLD.relationship_Level_Values = '");
                    queryBuilder.append(String.valueOf(parameters.get("parent")));
                    queryBuilder.append("' ");
                }
                queryBuilder.append(" ORDER by RLD.level_No desc");
            }
            LOGGER.debug("queryBuilder ---" + queryBuilder);
            List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(queryBuilder.toString());
            LOGGER.debug("list ---" + list.size());
            return list;
        } catch (Exception ex) {
            LOGGER.error("In getParentLevels ->" + ex);
            LOGGER.error(queryBuilder.toString());
            return Collections.emptyList();
        }
    }

    public static List getChildLevels(final Map<String, Object> parameters) {
//        StringBuilder queryBuilder = new StringBuilder(StringUtils.EMPTY);
//        try {
//            queryBuilder.append(CustomSQLUtil.get("getChildLevelsWithHierarchyNo"));
//            queryBuilder.replace(queryBuilder.indexOf("?"), queryBuilder.indexOf("?") + 1, String.valueOf(parameters.get("hierarchyNo")));
//            queryBuilder.replace(queryBuilder.indexOf("?"), queryBuilder.indexOf("?") + 1, String.valueOf(parameters.get("hierarchyNo")));
//            queryBuilder.replace(queryBuilder.indexOf("?"), queryBuilder.indexOf("?") + 1, String.valueOf(parameters.get("lowestLevelNo")));
//            LOGGER.debug("getChildLevels: " + queryBuilder.toString());
//            return HelperTableLocalServiceUtil.executeSelectQuery(queryBuilder.toString());
//        } catch (Exception ex) {
//            LOGGER.error("In getChildLevels ->" + ex);
//            LOGGER.error(queryBuilder.toString());
//            return Collections.emptyList();
//        }
        return Collections.emptyList();
    }

    public static List executeQuery(final Map<String, Object> parameters) {
//        LOGGER.debug("----inside executeQuery in finder Impl--------Indicator value---" + parameters.get(CommonConstant.INDICATOR));
//        StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
//        if (parameters.get(CommonConstant.INDICATOR) != null && CommonConstant.HAS_TRADING_PARTNER.equalsIgnoreCase(String.valueOf(parameters.get(CommonConstant.INDICATOR)))) {
//            queryString.append(CustomSQLUtil.get(CommonConstant.HAS_TRADING_PARTNER));
//            queryString.append("'");
//            queryString.append(String.valueOf(parameters.get(CommonConstant.PROJECTION_ID)));
//            queryString.append("'");
//        } else if (parameters.get(CommonConstant.INDICATOR) != null && CommonConstant.UNSAVED_PROJECTION_IDS.equalsIgnoreCase(String.valueOf(parameters.get(CommonConstant.INDICATOR)))) {
//            queryString.append(CustomSQLUtil.get(CommonConstant.UNSAVED_PROJECTION_IDS));
//            queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(parameters.get("deleteDate")));
//        } else if (parameters.get(CommonConstant.INDICATOR) != null && "getChildLevelRLSid".equalsIgnoreCase(String.valueOf(parameters.get(CommonConstant.INDICATOR)))) {
//            LOGGER.debug("--inside getChildLevelRLSid---------------------------->>>>>>>>");
//            if (parameters.get(CommonConstant.RL_SIDS) != null) {
//                List<String> rlSids = (ArrayList<String>) parameters.get(CommonConstant.RL_SIDS);
//                if (rlSids != null && !rlSids.isEmpty()) {
//                    queryString.append(CustomSQLUtil.get("getChildLevelRLSidRestricted"));
//                    queryString.append(" WHERE (");
//                    for (int loop = 0, limit = rlSids.size(); loop < limit; loop++) {
//                        queryString.append("HIERARCHY_NO LIKE '");
//                        queryString.append(rlSids.get(loop));
//                        queryString.append("%'");
//                        if (loop != (limit - 1)) {
//                            queryString.append(" OR ");
//                        }
//                    }
//                    queryString.append(") AND HIERARCHY_NO NOT IN (");
//                    queryString.append(CommonLogic.stringListToString(rlSids));
//                    queryString.append(")");
//                    queryString.append(" AND RLD.RELATIONSHIP_LEVEL_SID not in (SELECT PH.RELATIONSHIP_LEVEL_SID FROM ");
//                    queryString.append(String.valueOf(parameters.get("tableName")));
//
//                    if (parameters.get("module") != null && "cff".equalsIgnoreCase(String.valueOf(parameters.get("module")))) {
//                        queryString.append(" PH WHERE PH.CFF_MASTER_SID = ");
//                    } else {
//                        queryString.append(" PH WHERE PH.PROJECTION_MASTER_SID = ");
//                    }
//                    queryString.append(String.valueOf(parameters.get(CommonConstant.PROJECTION_ID)));
//                    queryString.append(")");
//                }
//            }
//        } else if (parameters.get(CommonConstant.INDICATOR) != null && CommonConstant.CHILD_LEVEL_RL.equalsIgnoreCase(String.valueOf(parameters.get(CommonConstant.INDICATOR)))) {
//            List<String> rlSids = (ArrayList<String>) parameters.get(CommonConstant.RL_SIDS);
//            if (rlSids != null && !rlSids.isEmpty()) {
//                queryString.append(CustomSQLUtil.get(CommonConstant.CHILD_LEVEL_RL));
//                queryString.append(" and (");
//                for (int loop = 0, limit = rlSids.size(); loop < limit; loop++) {
//                    queryString.append("HIERARCHY_NO like '");
//                    queryString.append(rlSids.get(loop));
//                    queryString.append("%'");
//                    if (loop != (limit - 1)) {
//                        queryString.append(" or ");
//                    }
//                }
//                queryString.append(") and HIERARCHY_NO not in (");
//                queryString.append(CommonLogic.stringListToString(rlSids));
//                if (parameters.get("availableHierNo") != null) {
//                    List<String> availableHierNo = (ArrayList<String>) parameters.get("availableHierNo");
//                    queryString.append(", ");
//                    queryString.append(CommonLogic.stringListToString(availableHierNo));
//                }
//                queryString.append(") ");
//            }
//        } else if (parameters.get(CommonConstant.INDICATOR) != null && CommonConstant.REMOVABLE_CHILDREN.equalsIgnoreCase(String.valueOf(parameters.get(CommonConstant.INDICATOR)))) {
//            queryString.append(CustomSQLUtil.get(CommonConstant.REMOVABLE_CHILDREN));
//            queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(parameters.get("removeLevels")));
//            queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(parameters.get("removeLevels")));
//        } else if (parameters.get(CommonConstant.INDICATOR) != null && CommonConstant.DELETE_TEMP_ON_UPDATE.equalsIgnoreCase(String.valueOf(parameters.get(CommonConstant.INDICATOR)))) {
//            String hNos = String.valueOf(parameters.get("hNos"));
//            String projectionId = String.valueOf(parameters.get(CommonConstant.PROJECTION_ID));
//            String projectionHierarchyTable = String.valueOf(parameters.get("projectionHierarchyTable"));
//            for (String table : CommonLogic.getTempTableList()) {
//                queryString.append(CustomSQLUtil.get(CommonConstant.DELETE_TEMP_ON_UPDATE));
//                queryString.replace(queryString.indexOf("?DTBL"), queryString.indexOf("?DTBL") + NumericConstants.FIVE, table);
//                queryString.replace(queryString.indexOf("?PID"), queryString.indexOf("?PID") + NumericConstants.FOUR, projectionId);
//                queryString.replace(queryString.indexOf(CommonConstant.HTBL), queryString.indexOf(CommonConstant.HTBL) + NumericConstants.FIVE, projectionHierarchyTable);
//                queryString.replace(queryString.indexOf("?PID"), queryString.indexOf("?PID") + NumericConstants.FOUR, projectionId);
//                queryString.replace(queryString.indexOf(CommonConstant.HTBL), queryString.indexOf(CommonConstant.HTBL) + NumericConstants.FIVE, projectionHierarchyTable);
//                queryString.replace(queryString.indexOf("?PID"), queryString.indexOf("?PID") + NumericConstants.FOUR, projectionId);
//                queryString.replace(queryString.indexOf("?HNO"), queryString.indexOf("?HNO") + NumericConstants.FOUR, hNos);
//                queryString.replace(queryString.indexOf("?PID"), queryString.indexOf("?PID") + NumericConstants.FOUR, projectionId);
//            }
//        } else if (parameters.get(CommonConstant.INDICATOR) != null && CommonConstant.FS_VALUE.equalsIgnoreCase(String.valueOf(parameters.get(CommonConstant.INDICATOR)))) {
//            queryString.append(CustomSQLUtil.get(CommonConstant.FS_VALUE));
//            queryString.replace(queryString.indexOf("?RLC?"), queryString.indexOf("?RLC?") + NumericConstants.FIVE, String.valueOf(parameters.get("relationshipLevelValue")));
//        } else if (parameters.get(CommonConstant.INDICATOR) != null && CommonConstant.COMPANY_FILTER.equalsIgnoreCase(String.valueOf(parameters.get(CommonConstant.INDICATOR)))) {
//            queryString.append(CustomSQLUtil.get(CommonConstant.COMPANY_FILTER));
//            queryString.append("'");
//            queryString.append(String.valueOf(parameters.get("companySid")));
//            queryString.append("'");
//        } else {
//            queryString.append(String.valueOf(parameters.get("query")));
//        }
//        LOGGER.debug("queryString: " + queryString.toString());
//        try {
//            List<Object[]> list;
//            list = HelperTableLocalServiceUtil.executeSelectQuery(queryString.toString());
//            if (parameters.get(CommonConstant.INDICATOR) != null
//                    && (CommonConstant.DELETE_TEMP_ON_UPDATE.equalsIgnoreCase(String.valueOf(parameters.get(CommonConstant.INDICATOR))))) {
//                List<Integer> intList = new ArrayList<>();
//                int returnValue = HelperTableLocalServiceUtil.executeUpdateQueryCount(queryString.toString());
//                intList.add(returnValue);
//                return intList;
//            } else {
//                return list;
//            }
//
//        } catch (Exception ex) {
//            LOGGER.error("In executeQuery  ->" + ex);
//            LOGGER.error(queryString.toString());
//            return Collections.emptyList();
//        }
 return Collections.emptyList();
    }

    public static List findViewByName(List<String> viewNameInputs, String viewName, boolean isCount, Set<Container.Filter> filters, List<SortByColumn> sortByColumns, int start, int offset) {
        String customSql = StringUtils.EMPTY;
        Connection connection = null;
        LOGGER.debug("Entering findViewByName method with viewName " + viewName + " adjustmentType " + viewNameInputs.get(2) + " userId " + viewNameInputs.get(3) + " viewType " + viewNameInputs.get(1));
        if (StringUtils.isNotBlank(viewNameInputs.get(1))) {
            try {

                if (isCount) {
                    customSql = SQlUtil.getQuery("getViewSearchCount");
                } else {
                    customSql = SQlUtil.getQuery("FETCH_SAVED_PROJECTION");
                }
                connection = SysDataSourceConnection.getConnection();
                customSql = customSql.replace("?", connection.getCatalog());

                customSql += " FVM.view_Type ='" + viewNameInputs.get(1) + "' ";

                if (StringUtils.isNotEmpty(viewName)
                        && StringUtils.isNotBlank(viewName)) {
                    customSql += " AND FVM.view_Name LIKE '" + viewName + "' ";
                }
                if (viewNameInputs.get(1).equalsIgnoreCase("private") && StringUtils.isNotEmpty(viewNameInputs.get(3))
                        && StringUtils.isNotBlank(viewNameInputs.get(3))) {
                    customSql += "AND FVM.created_By = " + viewNameInputs.get(3);
                }
                if (viewNameInputs.get(2).equals(ARMConstants.getAdjustmentSummary()) || viewNameInputs.get(2).equals("Balance Summary Report")) {
                    customSql += "AND PM.FORECASTING_TYPE IN('" + viewNameInputs.get(2) + "')";
                } else {
                    customSql += "AND PM.FORECASTING_TYPE IN('Pipeline Accrual','Demand Accrual','Pipeline Inventory True-Up','Demand Payments Recon','Demand Reforecast True-up','ARM')";
                }

                Map<String, String> viewFilterMap = ARMUtils.loadViewFilterMap();
                viewFilterMap.put(CommonConstant.CREATED_BY_STRING, "FVM.CREATED_BY");
                String filterQuery = CommonFilterLogic.getInstance().filterQueryGenerator(filters, viewFilterMap).toString().replace("where", " AND");

                customSql += filterQuery;
                if (isCount) {
                    return HelperTableLocalServiceUtil.executeSelectQuery(customSql);
                }
                viewFilterMap.put(CommonConstant.CREATED_BY_STRING, "usr.firstName");
                String orderBy = CommonFilterLogic.getInstance().orderByQueryGenerator(sortByColumns, viewFilterMap, "FVM.CREATED_DATE").toString();
                customSql += orderBy;
                if (!isCount) {
                    customSql += " OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY";
                }
                LOGGER.debug("Custom SQL --" + customSql);
                List<Object[]> sqlList = HelperTableLocalServiceUtil.executeSelectQuery(customSql);
                LOGGER.debug(CommonConstant.END_OF_FIND_VIEW_BY_NAME_METHOD);
                return sqlList;
            } catch (Exception e) {
                LOGGER.error("Error :" + e + " While Executing " + customSql);
                return Collections.emptyList();
            } finally {
                try {
                    connection.close();
                } catch (Exception ex) {
                    LOGGER.error("Error in findViewByName: " + ex);
                }
            }
        }
        return new ArrayList<>();
    }

    public static List loadCalculationViewSearch(List<String> viewNameInputs, String viewName, boolean isCount, Set<Container.Filter> filters, List<SortByColumn> sortByColumns, int start, int offset) {
        String customSql = StringUtils.EMPTY;
        LOGGER.debug("Entering loadCalculationViewSearch method with viewName " + viewName + " adjustmentType " + viewNameInputs.get(2) + " userId " + viewNameInputs.get(3) + " viewType " + viewNameInputs.get(1));
        if (StringUtils.isNotBlank(viewNameInputs.get(1))) {

            if (isCount) {
                customSql = SQlUtil.getQuery("countForCalculationView");
            } else {
                customSql = SQlUtil.getQuery("loadDataForCalculationView");
            }
            try (Connection connection = SysDataSourceConnection.getConnection()) {
                customSql = customSql.replace("?", connection.getCatalog());

                customSql += "where FVM.view_Type ='" + viewNameInputs.get(1) + "' ";

                if (StringUtils.isNotEmpty(viewName)
                        && StringUtils.isNotBlank(viewName)) {
                    customSql += " AND FVM.view_Name LIKE '" + viewName + "' ";
                }
                if (viewNameInputs.get(1).equalsIgnoreCase("private") && StringUtils.isNotEmpty(viewNameInputs.get(3))
                        && StringUtils.isNotBlank(viewNameInputs.get(3))) {
                    customSql += "AND FVM.created_By = " + viewNameInputs.get(3);
                }

                Map<String, String> viewFilterMap = ARMUtils.loadViewFilterMap();
                viewFilterMap.put(CommonConstant.CREATED_BY_STRING, "FVM.CREATED_BY");
                String filterQuery = CommonFilterLogic.getInstance().filterQueryGenerator(filters, viewFilterMap).toString().replace("where", " AND");

                customSql += filterQuery;
                if (isCount) {
                    return HelperTableLocalServiceUtil.executeSelectQuery(customSql);
                }
                viewFilterMap.put(CommonConstant.CREATED_BY_STRING, "usr.firstName");
                String orderBy = CommonFilterLogic.getInstance().orderByQueryGenerator(sortByColumns, viewFilterMap, "FVM.CREATED_DATE").toString();
                customSql += orderBy;
                if (!isCount) {
                    customSql += " OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY";
                }
                LOGGER.debug("Custom SQL --" + customSql);
                List<Object[]> sqlList = HelperTableLocalServiceUtil.executeSelectQuery(customSql);
                LOGGER.debug(CommonConstant.END_OF_FIND_VIEW_BY_NAME_METHOD);
                return sqlList;
            } catch (Exception e) {
                LOGGER.error("Error :" + e + " While Executing " + customSql);
                return Collections.emptyList();
            }
        }
        return new ArrayList<>();
    }

    public static String insertIntoCcpMap(String custHier, String custRel, String prodHier, String prodRel) {
        String query = SQlUtil.getQuery("ccp_map_insert");
        if (custHier.isEmpty()) {
            query = query.replace("= @Customer_hierarchy", custHier);
        } else {
            query = query.replace("@Customer_hierarchy", custHier);
        }
        if (custRel.isEmpty()) {
            query = query.replace("= @Customer_relation", custRel);
        } else {
            query = query.replace("@Customer_relation", custRel);
        }
        if (prodHier.isEmpty()) {
            query = query.replace("= @Product_hierarchy", prodHier);
        } else {
            query = query.replace("@Product_hierarchy", prodHier);
        }
        if (prodRel.isEmpty()) {
            query = query.replace("= @Product_relation", prodRel);
        } else {
            query = query.replace("@Product_relation", prodRel);
        }
        LOGGER.debug("query --" + query);
        return query;
    }

}
