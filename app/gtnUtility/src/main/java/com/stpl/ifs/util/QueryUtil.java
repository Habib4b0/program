/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.ifs.util;

import com.stpl.ifs.ui.util.GtnSmallHashMap;
import com.stpl.ifs.util.constants.ForecastingConstants;
import com.stpl.ifs.util.constants.GlobalConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sriram
 */
public class QueryUtil {

    public static List<String> getColumnNames(String query) {
        ArrayList<String> columnNames = new ArrayList<>();
        query = query.replaceFirst("select ", "").replaceFirst("Select ", "").replaceFirst("SELECT ", "").replace("Distinct ", query).replace("distinct ", query).replace("DISTINCT ", query);
        int endIndex = query.indexOf(" from ");
        if (endIndex == -1) {
            endIndex = query.indexOf(" From ");
            if (endIndex == -1) {
                endIndex = query.indexOf(" FROM ");
            }
        }
        if (endIndex > -1) {
            String newQuery = query.substring(0, endIndex);

            String[] str1 = newQuery.split(",");

            for (String columnName : str1) {

                if (columnName.contains(" as ")) {
                    columnNames.add(columnName.split(" as ")[1].trim());
                } else if (columnName.contains(" AS ")) {
                    columnNames.add(columnName.split(" AS ")[1].trim());
                } else if (columnName.contains(" As ")) {
                    columnNames.add(columnName.split(" As ")[1].trim());
                } else {
                    columnNames.add(columnName.trim());
                }
            }
        }
        return columnNames;
    }

    /**
     * Used to replace all the tableName with dynamic table
     *
     * @param query -- Query where we need to replace
     * @param tableNameMap -- to get the table name as key and dynmaic table
     * name as value
     * @return replaced query with dynamic tableName
     */
    public static String replaceTableNames(String query, final GtnSmallHashMap tableNameMap) {
        for (int i = 0; i < tableNameMap.size(); i++) {
            query = query.replaceAll("(?i:\\b" + tableNameMap.getIndex(i).getKey() + "\\b)", tableNameMap.getIndex(i).getValue().toString());
        }
            return query;
    }

    /**
     * Used to form the query to create dynamic tables
     *
     * @param screenName
     * @param projectionId
     * @param userId
     * @param sessionId
     * @return
     */
    public static String buildDynamicTempTableCreationQuery(final String screenName, final int projectionId, final String userId, final String sessionId) {
        String append = "('@MAIN_TABLE') ";
        StringBuilder queryBuilder = new StringBuilder();
        String comma = ",";
        queryBuilder.append("DECLARE @TABLE_LIST TABLE_LIST, @USER_ID INT, @SESSION_ID VARCHAR(100) INSERT INTO @TABLE_LIST(ORG_TABLE_NAME) VALUES ");
        String[] mainTableList = ForecastingConstants.getTableNames(screenName.replace(" ", "_")).split(",");
        int index = 0;
        for (int i = 0; i < mainTableList.length; i++) {
            queryBuilder.append(append.replace("@MAIN_TABLE", mainTableList[index++].trim()));
            if (i != mainTableList.length - 1) {
                queryBuilder.append(comma);
            }
        }
        queryBuilder.append(" EXEC PRC_TEMP_TABLE_CREATION ").append("@TABLE_LIST ,").append(userId).append(comma).append("'").append(sessionId).append("'");
        return queryBuilder.toString();
    }
    /**
     * Used to form the query to create dynamic tables
     *
     * @param screenName
     * @param projectionId
     * @param userId
     * @param sessionId
     * @return
     */
    public static String buildDynamicTempTableCreationQueryForGlobal(final String moduleName, final String userId, final String sessionId) {
        String append = "('@MAIN_TABLE') ";
        StringBuilder queryBuilder = new StringBuilder();
        String comma = ",";
        queryBuilder.append("DECLARE @TABLE_LIST TABLE_LIST, @USER_ID INT, @SESSION_ID VARCHAR(100) INSERT INTO @TABLE_LIST(ORG_TABLE_NAME) VALUES ");
        String[] mainTableList = GlobalConstants.getTableNames(moduleName.replace(" ", "_")).split(",");
        int index = 0;
        for (int i = 0; i < mainTableList.length; i++) {
            queryBuilder.append(append.replace("@MAIN_TABLE", mainTableList[index++].trim()));
            if (i != mainTableList.length - 1) {
                queryBuilder.append(comma);
            }
        }
        queryBuilder.append(" EXEC PRC_TEMP_TABLE_CREATION ").append("@TABLE_LIST ,").append(userId).append(comma).append("'").append(sessionId).append("'");
        return queryBuilder.toString();
    }
    public static String replaceTableNames(String query, final Map<String, String> tableNameMap) {
        for (Map.Entry<String, String> entry : tableNameMap.entrySet()) {
            query = query.replaceAll("(?i:\\b" + entry.getKey() + "\\b)", entry.getValue());
        }
        return query;
    }
}
