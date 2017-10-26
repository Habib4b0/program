/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.utils;

import com.stpl.app.arm.adjustmentrateconfiguration.dto.AdjustmentRateDTO;
import com.stpl.app.arm.adjustmentrateconfiguration.dto.AdjustmentRateSelection;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.app.arm.dao.CommonDao;
import com.stpl.app.arm.dao.impl.CommonImpl;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.dataselection.dto.LevelDTO;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CalendarUtils;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.GlobalConstants;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author srithar
 */
public class QueryUtils {

    private static final Logger LOGGER = Logger.getLogger(QueryUtils.class);
    final static SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
    protected static final CommonDao DAO = CommonImpl.getInstance();

    public static List getGroupList() {
        return new ArrayList();
    }

    public static class QueryName {

        public static final String UPDATE_PROJECTION_MASTER_IN_SUBMIT = "UPDATE_PROJECTION_MASTER_IN_SUBMIT";
        public static final String UPDATE_PROJECTION_MASTER_APPROVE_FLAG = "UPDATE_PROJECTION_MASTER_APPROVE_FLAG";
    }

    public static List executeSelect(String query) {
        return DAO.executeSelect(query);
    }

    public static List getItemData(List input, String queryName, String quaryName2) {
        LOGGER.debug("Inside item get data");
        List list = Collections.EMPTY_LIST;
        LOGGER.debug("queryName-->>" + queryName);
        LOGGER.debug("queryName2-->>" + quaryName2);
        StringBuilder sql = new StringBuilder(SQlUtil.getQuery(queryName));
        if (queryName != null && !queryName.isEmpty()) {
            try {
                if (quaryName2 != null && !quaryName2.equals(StringUtils.EMPTY)) {
                    sql.append(" ");
                    sql.append(SQlUtil.getQuery(quaryName2));
                }
                for (Object temp : input) {
                    sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
                }
                
                list = (List<Object[]>) executeSelect(sql.toString());
            } catch (Exception ex) {
                LOGGER.error("Error :" + ex + "  Becoz of the query :" + sql.toString());
            }
        }

        LOGGER.debug("End of item get Data");
        return list;
    }

    public static List getItemData(List input, String mainQuery, String fetchQueryName, String childQueryName, String orderByQueryName, String tableName, String viewControl, String pageControl) {
        LOGGER.debug("Inside item get data");
        LOGGER.debug("Query Name--------------------" + mainQuery);
        LOGGER.debug("Fetch Query Name--------------" + fetchQueryName);
        LOGGER.debug("Child Query Name--------------" + childQueryName);
        List list = Collections.EMPTY_LIST;
        StringBuilder sql = new StringBuilder(SQlUtil.getQuery(mainQuery));
        if (mainQuery != null && !mainQuery.isEmpty()) {
            try {
                if (fetchQueryName != null && !fetchQueryName.equals(StringUtils.EMPTY)) {
                    sql.append(" ");
                    sql.append(SQlUtil.getQuery(fetchQueryName));
                }
                if (childQueryName != null && !childQueryName.equals(StringUtils.EMPTY)) {
                    sql.replace(sql.indexOf("$$$$CHILD_QUERY$$$$"), sql.indexOf("$$$$CHILD_QUERY$$$$") + NumericConstants.NINETEEN, SQlUtil.getQuery(childQueryName));
                }
                if (pageControl != null) {
                    sql.replace(sql.indexOf("$$$$PAGE_CONTROL$$$$"), sql.indexOf("$$$$PAGE_CONTROL$$$$") + NumericConstants.TWENTY, pageControl);
                }
                if (orderByQueryName != null && !orderByQueryName.equals(StringUtils.EMPTY)) {

                    sql.append(" ");
                    sql.append(SQlUtil.getQuery(orderByQueryName));
                }
                if (tableName != null && !tableName.equals(StringUtils.EMPTY)) {

                    sql.replace(sql.indexOf("$$$$TABLE_NAME$$$$"), sql.indexOf("$$$$TABLE_NAME$$$$") + NumericConstants.EIGHTEEN, tableName);
                }
                if (viewControl != null) {
                    int index = sql.indexOf("$$$$VIEW_CONTROL$$$$");
                    if (index != -1) {
                        sql.replace(index, index + NumericConstants.TWENTY, viewControl);
                    }
                }
                for (Object temp : input) {
                    sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
                }

                list = (List<Object[]>) executeSelect(sql.toString());
            } catch (Exception ex) {
                LOGGER.error("Error :" + ex + "  Becoz of the query :" + sql.toString());
            }
        }

        LOGGER.debug("End of item get Data");
        return list;
    }

    /**
     * This method is used for Updating data in to table.
     *
     * @param input
     * @param queryName
     * @return updated or not
     */
    public static Boolean itemUpdate(List input, String queryName) {
        LOGGER.debug("Inside Item Update");
        StringBuilder sql = new StringBuilder();
        try {
            LOGGER.debug("queryName-->>" + queryName);
            if (!queryName.isEmpty()) {
                sql = new StringBuilder(SQlUtil.getQuery(queryName));
                for (Object temp : input) {
                    sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
                }
                Integer count = (Integer) DAO.executeUpdate(sql.toString());
                if (count > 0) {
                    LOGGER.debug("End of Item Update - Updated");
                    return Boolean.TRUE;
                } else {
                    LOGGER.debug("End of Item Update - Not Updated");
                    return Boolean.FALSE;
                }
            }
        } catch (Exception ex) {
            LOGGER.error("Error :" + ex + "  Becoz of the query :" + sql.toString());
        }
        LOGGER.debug("End of Item Update");
        return Boolean.FALSE;
    }

    public static String getQuery(List input, String queryName) {
        StringBuilder sql = null;
        try {
            sql = new StringBuilder(SQlUtil.getQuery(queryName));
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return sql.toString();
    }

    public static String build_RateConfig_saveQuery(AdjustmentRateSelection selection, List<AdjustmentRateDTO> months) throws IllegalArgumentException {
        StringBuilder finalSql;
        String queryName;
        String subQueryName;
        queryName = "SAVE_MASTER_DETAILS";
        subQueryName = "SAVE_MASTER_DETAILS_SUB_QUERY";
        String sql = SQlUtil.getQuery(queryName);
        sql = sql.replace("[$(GL_COMPANY_MASTER_SID]", String.valueOf(selection.getGl_companyMasterSid()));
        sql = sql.replace("[$BU_COMPANY_MASTER_SID]", String.valueOf(selection.getBu_companyMasterSid()));
        sql = sql.replace("[$ADJUSTMENT_TYPE]", String.valueOf(selection.getAdjustmentId()));
        finalSql = new StringBuilder(sql);
        StringBuilder subQuery = new StringBuilder();
        for (AdjustmentRateDTO dto : months) {
            String sub = SQlUtil.getQuery(subQueryName);
            sub = sub.replace("[$MONTH]", String.valueOf(CalendarUtils.getMonth_index(dto.getMonth())));
            sub = sub.replace("[$DATE_TYPE]", String.valueOf(dto.getDateType()));
            sub = sub.replace("[$INVENTORY_CUSTOMER]", String.valueOf(dto.getInventoryCustomer()));
            sub = sub.replace("[$RESERVE_DATE]", String.valueOf(dto.getReserveDate()));
            sub = sub.replace("[$INVENTORY_DETAILS]", String.valueOf(dto.getInventoryDetails()));
            sub = sub.replace("[$EXCLUSION_VIEW_MASTER]", String.valueOf(dto.getViewMasterSid() != 0 ? dto.getViewMasterSid() : "NULL"));
            sub = sub.replace("[$PRICE]", String.valueOf(dto.getPrice()));
            sub = sub.replace("[$RATE_BASIS]", String.valueOf(dto.getRateBasis()));
            sub = sub.replace("[$RATE_FREQUENCY]", String.valueOf(dto.getRateFrequency()));
            sub = sub.replace("[$RATE_PERIOD]", String.valueOf(dto.getRatePeriod()));
            sub = sub.replace("[$ADJUSTED_PRICE]", String.valueOf(dto.getAdjustedPrice()));
            sub = sub.replace("[$BASELINE_PRICE]", String.valueOf(dto.getBaselinePrice()));

            if (subQuery.length() == 0) {
                subQuery.append(sub);
            } else {
                subQuery.append(",").append(sub);
            }
        }
        finalSql.append(subQuery);

        return finalSql.toString();
    }

    public static String build_RateConfig_updateQuery(List<AdjustmentRateDTO> months) throws IllegalArgumentException {
        String queryName = "UPDATE_MASTER_DETAILS_QUERY";
        String subQueryName = "UPDATE_RATE_DETAILS_SUB_QUERY";

        StringBuilder subQuery = new StringBuilder();
        for (AdjustmentRateDTO dto : months) {
            String sub = SQlUtil.getQuery(subQueryName);
            sub = sub.replace("[$ARM_ADJ_RATE_CONFIG_DETAIL_SID]", String.valueOf(dto.getRateConfigDetailsSid()));
            sub = sub.replace("[$MONTH]", String.valueOf(CalendarUtils.getMonth_index(dto.getMonth())));
            sub = sub.replace("[$DATE_TYPE]", String.valueOf(dto.getDateType()));
            sub = sub.replace("[$PRICE]", GlobalConstants.getSelectOne().equals(dto.getPrice()) ? "0" : dto.getPrice());
            sub = sub.replace("[$INVENTORY_CUSTOMER]", String.valueOf(dto.getInventoryCustomer()));
            sub = sub.replace("[$RESERVE_DATE]", String.valueOf(dto.getReserveDate()));
            sub = sub.replace("[$INVENTORY_DETAILS]", String.valueOf(dto.getInventoryDetails()));
            sub = sub.replace("[$EXCLUSION_VIEW_MASTER]", String.valueOf(dto.getViewMasterSid() != 0 ? dto.getViewMasterSid() : "NULL"));

            sub = sub.replace("[$RATE_BASIS]", String.valueOf(dto.getRateBasis()));
            sub = sub.replace("[$RATE_FREQUENCY]", String.valueOf(dto.getRateFrequency()));
            sub = sub.replace("[$RATE_PERIOD]", String.valueOf(dto.getRatePeriod()));
            sub = sub.replace("[$ADJUSTED_PRICE]", dto.getAdjustedPrice() == null || GlobalConstants.getSelectOne().equals(dto.getAdjustedPrice()) ? "0" : dto.getAdjustedPrice());
            sub = sub.replace("[$BASE_LINE_PRICE]", dto.getBaselinePrice() == null || GlobalConstants.getSelectOne().equals(dto.getBaselinePrice()) ? "0" : dto.getBaselinePrice());

            if (subQuery.length() == 0) {
                subQuery.append(sub);
            } else {
                subQuery.append(",").append(sub);
            }
        }
        String sql = SQlUtil.getQuery(queryName);
        sql = sql.replace("[$UPDATE_RATE_DETAILS_SUB_QUERY]", subQuery.toString());

        return sql;
    }

    public static String build_RateConfig_selectQuery(AdjustmentRateSelection selection) {
        String sql = SQlUtil.getQuery("SELECT_RATE_MASTER_DETAILS");
        sql = sql.replace("[$GL_COMPANY_MASTER_SID]", String.valueOf(selection.getGl_companyMasterSid()));
        sql = sql.replace("[$BU_COMPANY_MASTER_SID]", String.valueOf(selection.getBu_companyMasterSid()));
        sql = sql.replace("[$ADJUSTMENT_TYPE]", String.valueOf(selection.getAdjustmentId()));
        return sql;
    }

    public static String build_FieldName_selectQuery(String fieldValue, String selectedValue) {
        String sql = StringUtils.EMPTY;
        if (ARMConstants.getAccountName().equalsIgnoreCase(fieldValue)) {
            sql = "select Distinct CM.COMPANY_NAME from CUSTOMER_GTS_ACTUAL CGA\n"
                    + "  Join COMPANY_MASTER CM ON CGA.ACCOUNT_ID=CM.COMPANY_ID\n"
                    + "where CM.COMPANY_NAME NOT IN (" + selectedValue + ");";
        } else {
            sql = "select Distinct CGA." + fieldValue + "  from CUSTOMER_GTS_ACTUAL CGA \n"
                    + "Join COMPANY_MASTER CM ON CGA.ACCOUNT_ID=CM.COMPANY_ID \n"
                    + "where CGA." + fieldValue + " NOT IN(" + selectedValue + ");";
        }

        return sql;
    }

    public static String build_adjustment_selection_save_query(int projectionId, String screenName, Map<String, String> values) {
        StringBuilder sql = new StringBuilder(SQlUtil.getQuery("ADJUSTMENT_SELECTION_SAVE"));

        StringBuilder subQuery = new StringBuilder();
        for (Map.Entry<String, String> entry : values.entrySet()) {
            String fieldName = entry.getKey();
            String fieldValue = entry.getValue();
            String sub = SQlUtil.getQuery("ADJUSTMENT_SELECTION_SAVE_SUB_QUERY");
            sub = sub.replace("[$PROJECTION_MASTER_SID]", String.valueOf(projectionId));
            sub = sub.replace("[$SCREEN_NAME]", "'" + screenName + "'");
            sub = sub.replace("[$FIELD_NAME]", "'" + fieldName + "'");
            sub = sub.replace("[$FIELD_VALUES]", "'" + fieldValue + "'");
            if (subQuery.length() == 0) {
                subQuery.append(sub);
            } else {
                subQuery.append(",").append(sub);
            }
        }
        sql.append(subQuery);
        return sql.toString();
    }

    public static String build_adjustment_selection_delete_query(int projectionId) {
        StringBuilder sql = new StringBuilder("DELETE FROM ARM_PROJ_SELECTION WHERE PROJECTION_MASTER_SID=" + projectionId);
        return sql.toString();
    }

    /**
     * Used to Modify the query according to temp and main table.
     *
     * @param query
     * @param isView
     * @return
     */
    public static String queryCustomizationForView(String query, boolean isView) {
        if (isView) {
            query = query.replace("@SESSION_DECLARE", StringUtils.EMPTY)
                    .replace("@SESSION_INCLUDE", StringUtils.EMPTY);
        } else {
            query = query.replace("@SESSION_DECLARE", ",@USER_ID INT = @USER_REF,@SESSION_ID INT = @SESSION_REF")
                    .replace("@SESSION_INCLUDE", "AND B.USER_ID = @USER_ID AND B.SESSION_ID = @SESSION_ID");
        }
        return query;
    }

    /**
     * To create temp tables dynamically. It will return the tables created with
     * the user id and session id
     *
     * @param screenName
     * @param projectionId
     * @param userId
     * @param sessionId
     * @return
     */
    public static Map createTempTables(String screenName, int projectionId, String userId, String sessionId) {
        List<Object> createdTablesList = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.buildDynamicTempTableCreationQuery(screenName, projectionId, userId, sessionId));
        Map map = new HashMap();
        for (Object createdTablesList1 : createdTablesList) {
            Object[] ob = (Object[]) createdTablesList1;
            map.put(ob[0].toString(), ob[1].toString());
        }
        return map;
    }

    public static Boolean updateDataFromMap(final Map<String, String> input, final String queryName) {
        StringBuilder queryString = new StringBuilder(SQlUtil.getQuery(queryName));
        if (input != null) {
            for (Map.Entry<String, String> entry : input.entrySet()) {
                final String string = entry.getKey();
                final String string1 = entry.getValue();
                while (queryString.toString().contains(string)) {
                    queryString.replace(queryString.indexOf(string), queryString.indexOf(string) + string.length(), String.valueOf(string1));
                }
            }
        }
        int count = (Integer) HelperTableLocalServiceUtil.executeUpdateQueryCount(queryString.toString());
        return count > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    /**
     * To insert the selected customer and product hierarchy in CCP_HIERARCHY
     *
     * @param tempTableNames
     * @param dsDTO
     * @param customerSelection
     * @param productSelection
     * @param topLevelName
     * @param isDataSelectionTab
     */
    public void ccpHierarchyInsert(final Map<String, String> tempTableNames, DataSelectionDTO dsDTO, final List<LevelDTO> customerSelection, final List<LevelDTO> productSelection, final String topLevelName, final boolean isDataSelectionTab) {
        List<Object[]> contractList = new ArrayList<>();
        List<Object[]> customerList = new ArrayList<>();
        List<Object[]> productList = new ArrayList<>();
        List<String> hierarchyNoList = new ArrayList<>();

        getCustomerSelectionHierarchyNo(contractList, customerList, hierarchyNoList, customerSelection, Integer.valueOf(dsDTO.getCustomerHierarchyLevel()));

        String[] ccpHierarchyQuery = new String[NumericConstants.THREE];

        if (contractList.isEmpty()) {
            ccpHierarchyQuery[0] = getCCPValues(String.valueOf(dsDTO.getCustRelationshipBuilderSid()), formInqueryStringValue(hierarchyNoList, "HIERARCHY_NO"), "GET_CONTRACT_LEVEL");
        } else {
            ccpHierarchyQuery[0] = formQueryWithUnionAll(contractList);
        }
        if (customerList.isEmpty()) {
            ccpHierarchyQuery[1] = getCCPValues(String.valueOf(dsDTO.getCustRelationshipBuilderSid()), formInqueryStringValue(hierarchyNoList, "HIERARCHY_NO"), "GET_CUSTOMER_LEVEL");
        } else {
            ccpHierarchyQuery[1] = formQueryWithUnionAll(customerList);
        }

        hierarchyNoList.clear();

        getProductSelectionHierarchyNo(productList, hierarchyNoList, productSelection, Integer.valueOf(dsDTO.getProductHierarchyLevel()));

        if (productList.isEmpty()) {
            ccpHierarchyQuery[NumericConstants.TWO] = getCCPValues(String.valueOf(dsDTO.getProdRelationshipBuilderSid()), formInqueryStringValue(hierarchyNoList, "HIERARCHY_NO"), "GET_PRODUCT_LEVEL");
        } else {
            ccpHierarchyQuery[NumericConstants.TWO] = formQueryWithUnionAll(productList);
        }

        callCCPHierarchyInsertion(ccpHierarchyQuery, tempTableNames, topLevelName, isDataSelectionTab);
    }

    /**
     * Used to get the HIERARCHY_NO, RELATIONSHIP_LEVEL_VALUES,LEVEL_NO for
     * Customer and product level alone from the Customer selection. If the user
     * is not selected in customer or contract level then we will get it based
     * on the higher level.
     *
     * Query Placed in SqlResources "DataSelectionQueries.xml"
     *
     * @param contractList -->> It will have the Object array of HIERARCHY_NO,
     * RELATIONSHIP_LEVEL_VALUES,LEVEL_NO for contract
     * @param customerList -->> It will have the Object array of HIERARCHY_NO,
     * RELATIONSHIP_LEVEL_VALUES,LEVEL_NO for customer
     * @param hierarchyNoList
     */
    private void getCustomerSelectionHierarchyNo(List<Object[]> contractList, List<Object[]> customerList, List<String> hierarchyNoList, List<LevelDTO> selectedCustomer, final int customerLevel) {
        for (LevelDTO dto : selectedCustomer) {
            switch (dto.getLevel()) {
                case "Contract":
                    contractList.add(new Object[]{dto.getHierarchyNo(), dto.getRelationshipLevelValue(), dto.getLevelNo()});
                    break;
                case "Trading Partner":
                case "Customer":
                    customerList.add(new Object[]{dto.getHierarchyNo(), dto.getRelationshipLevelValue(), dto.getLevelNo()});
                    break;
            }
            if (customerLevel == dto.getLevelNo()) {
                hierarchyNoList.add(dto.getHierarchyNo());
            }
        }

    }

    /**
     * Used to get the HIERARCHY_NO, RELATIONSHIP_LEVEL_VALUES,LEVEL_NO for
     * product level alone from the Product selection. If the user elected in
     * product level then it will stored in @param productList or else we will
     * get the last level hierarchy no hierarchyNoList.
     *
     * @param productList -->> It will have the Object array of HIERARCHY_NO,
     * RELATIONSHIP_LEVEL_VALUES,LEVEL_NO for product
     * @param hierarchyNoList
     */
    private void getProductSelectionHierarchyNo(List<Object[]> productList, List<String> hierarchyNoList, List<LevelDTO> selectedProduct, final int productLevel) {
        for (LevelDTO dto : selectedProduct) {
            if ("NDC".equalsIgnoreCase(dto.getLevel()) || "Item".equalsIgnoreCase(dto.getLevel()) || "Product".equalsIgnoreCase(dto.getLevel())) {
                productList.add(new Object[]{dto.getHierarchyNo(), dto.getRelationshipLevelValue(), dto.getLevelNo()});
            }

            if (productLevel == dto.getLevelNo()) {
                hierarchyNoList.add(dto.getHierarchyNo());
            }
        }
    }

    /**
     * To get the selected cust and prod values. Used in edit mode. For add mode
     * we will get it directly from the ui container
     *
     * @param projectionId
     * @param queryName
     * @return
     */
    public List<LevelDTO> getCustandProdSelection(final int projectionId, final String queryName) {
        String sql = SQlUtil.getQuery(queryName).replace("@PROJECTION_MASTER_SID", String.valueOf(projectionId));

        List results = HelperTableLocalServiceUtil.executeSelectQuery(sql);
        List<LevelDTO> resultList = new ArrayList<>();
        LevelDTO dto;
        for (int j = 0; j < results.size() - 1; j++) {
            Object[] object = (Object[]) results.get(j);
            dto = new LevelDTO();
            dto.setHierarchyNo(object[0].toString());
            dto.setRelationshipLevelSid(Integer.valueOf(object[1].toString()));
            dto.setLevelNo(Integer.valueOf(object[NumericConstants.TWO].toString()));
            resultList.add(dto);
        }
        return resultList;
    }

    /**
     * To insert the ccp_hierarchy table in edit add and view mode
     *
     * @param ccpHierarchyQuery
     * @param tempTableNames
     * @param topLevelName
     * @param isDataSelectionTab -- It will be true if its called from data
     * selection tab
     */
    private void callCCPHierarchyInsertion(String[] ccpHierarchyQuery, final Map<String, String> tempTableNames, final String topLevelName, final boolean isDataSelectionTab) {

        StringBuilder builder = new StringBuilder();
        if (isDataSelectionTab) {
            builder.append(QueryUtil.replaceTableNames(SQlUtil.getQuery("DELETION").replace("@TABLE_NAME", "ST_CCP_HIERARCHY"), tempTableNames));
        }
        builder.append(SQlUtil.getQuery("CCP_HIERARCHY_INSERT"));
        builder.replace(builder.indexOf("@CONTRACT"), "@CONTRACT".length() + builder.lastIndexOf("@CONTRACT"), ccpHierarchyQuery[0]);
        builder.replace(builder.indexOf("@CUSTOMER"), "@CUSTOMER".length() + builder.lastIndexOf("@CUSTOMER"), ccpHierarchyQuery[1]);
        builder.replace(builder.indexOf("@PRODUCT"), "@PRODUCT".length() + builder.lastIndexOf("@PRODUCT"), ccpHierarchyQuery[NumericConstants.TWO]);
        if ("Contract".equalsIgnoreCase(topLevelName)) {
            builder.replace(builder.indexOf("@FILTER"), "@FILTER".length() + builder.lastIndexOf("@FILTER"), "COM.HIERARCHY_NO LIKE C.HIERARCHY_NO");
        } else {
            builder.replace(builder.indexOf("@FILTER"), "@FILTER".length() + builder.lastIndexOf("@FILTER"), "C.HIERARCHY_NO LIKE COM.HIERARCHY_NO");
        }
        builder.replace(builder.indexOf("?ST_CCP_HIERARCHY"), "?ST_CCP_HIERARCHY".length() + builder.lastIndexOf("?ST_CCP_HIERARCHY"), tempTableNames.get("ST_CCP_HIERARCHY"));

        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(builder.toString(), tempTableNames));
    }

    /**
     * Used to form the query from selection container of customer and product
     *
     * @param contractList
     * @return the string having SELECT [HIERARCHY_NO] AS STRING AND
     * RELATIONSHIP_LEVEL_VALUES AS INT EXAMPLE: SELECT '12.1.1.2', 543 UNION
     * ALL SELECT '12.1.1.3', 234
     */
    private String formQueryWithUnionAll(List<Object[]> contractList) {
        StringBuilder queryBuilder = new StringBuilder();
        String unionAll = StringUtils.EMPTY;
        for (Object[] objects : contractList) {
            queryBuilder.append(unionAll).append(" SELECT '").append(objects[0]).append("' as HIERARCHY_NO ,").append(objects[1]).append(" as RELATIONSHIP_LEVEL_VALUES ");
            unionAll = " UNION ALL ";
        }
        return queryBuilder.toString();
    }

    /**
     * Convert the given list value to feed in query format
     *
     * @param valueToform
     * @param coloumnName
     * @return
     */
    public String formInqueryStringValue(final List<String> valueToform, String coloumnName) {
        StringBuilder value = new StringBuilder();
        String comma = StringUtils.EMPTY;
        for (String string : valueToform) {
            value.append(comma).append(coloumnName).append(" like '").append(string).append("%'");
            comma = " or ";
        }
        return value.toString();
    }

    /**
     * Used to build the query to get the CCP values for CCP_HIERARCHY insert
     *
     * @param value
     * @param formInqueryStringValue
     * @param queryName
     * @return
     */
    private String getCCPValues(String value, String formInqueryStringValue, final String queryName) {
        StringBuilder builder = new StringBuilder(SQlUtil.getQuery(queryName));
        builder.replace(builder.indexOf("@RELATION_SID"), "@RELATION_SID".length() + builder.lastIndexOf("@RELATION_SID"), value);
        builder.replace(builder.indexOf("@HIERARCHY_DETAILS"), "@HIERARCHY_DETAILS".length() + builder.lastIndexOf("@HIERARCHY_DETAILS"), formInqueryStringValue);
        return builder.toString();
    }

}
