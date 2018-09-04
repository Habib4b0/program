/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.logic;

import com.stpl.app.gtnforecasting.dto.LevelMapKey;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.HelperListUtil;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author karthikeyans
 */
public class CustomViewLogic {

    private Map<LevelMapKey, List<Integer>> ccpMap = null;
    private final List<Map<LevelMapKey, List<Integer>>> levelCcpIds = new ArrayList();
    private final Set<String> levelNoSet = new HashSet();
    public static final String DOT = ".";

    private final Map<Integer, String> levelValueMap = new HashMap();
    private final Map<Integer, String[]> levelTableFieldNames = new LinkedHashMap();
    private final Map<String, String> tableFieldHelperList = new HashMap();
    private final List<String> listNameCollection = new ArrayList();
    private final List<Integer> helperListValues = new ArrayList();
    private final StringBuilder tableName = new StringBuilder();
    private final Set<String> tableNameSet = new HashSet();
    private final StringBuilder fieldName = new StringBuilder();
    private final Set<String> fieldNameSet = new HashSet();
    private final StringBuilder finalQuery = new StringBuilder();
    private final List<LevelMapKey> hierarchyList = new ArrayList();

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomViewLogic.class.getName());
    public Map<Integer, String> getData_custom_view(String projectionId, String customViewMasterSid) {
        levelValueMap.clear();
        levelTableFieldNames.clear();
        tableName.setLength(0);
        fieldName.setLength(0);
        tableFieldHelperList.clear();
        listNameCollection.clear();
        finalQuery.setLength(0);
        hierarchyList.clear();
        helperListValues.clear();
        levelCcpIds.clear();
        levelNoSet.clear();
        executeInsertOrUpdate(projectionId, customViewMasterSid, "CUSTOM_CCP_MAP_INSERT_QUERY");
        List<Object[]> rawList = executeQuery(projectionId, customViewMasterSid, "GET_TABLE_NAME-AND-COLUMN");
        customize_data(rawList);
        getHelperListData();
        List<Object[]> finalrawList = build_custom_query();
        customizeFinalResult(finalrawList);
        updateHelperListValues();
        List<Object[]> rawListCcp = executeQuery(projectionId, customViewMasterSid, "GET_CCP_VALUES");
        split_ccp(rawListCcp);
        build_executeFinalQuery(hierarchyList);
        buildAndExecute_customCCP_update();

        return levelValueMap;

    }

    private List<Object[]> executeQuery(String projectionId, String customViewMasterSid, String queryName) {
        LOGGER.debug("select the Data from CUSTOM_CCP_MAP  table = {}",queryName);
        String query = SQlUtil.getQuery(queryName);
        query = query.replace("[$PROJECTION_MASTER_SID]", projectionId);
        query = query.replace(Constant.CUSTOM_VIEW_MASTER_SID, customViewMasterSid);
        List<Object[]> rawList = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return rawList;

    }

    private void executeInsertOrUpdate(String projectionId, String customViewMasterSid, String queryName) {
        LOGGER.debug("Insert the Data in CUSTOM_CCP_MAP table :");
        String query = SQlUtil.getQuery(queryName);
        query = query.replace("[$PROJECTION_MASTER_SID]", projectionId);
        query = query.replace(Constant.CUSTOM_VIEW_MASTER_SID, customViewMasterSid);
        HelperTableLocalServiceUtil.executeUpdateQuery(query);

    }

    public void executeDelete(String customViewMasterSid) {
        String query = SQlUtil.getQuery("DELETE_CUSTOM_VIEW_TABLES");
        query = query.replace(Constant.CUSTOM_VIEW_MASTER_SID, customViewMasterSid);
        HelperTableLocalServiceUtil.executeUpdateQuery(query);

    }

    private void build_executeFinalQuery(List<LevelMapKey> hierarchyList) {
        LOGGER.debug("Building the final query and inserting :");
        StringBuilder customQuery = new StringBuilder(SQlUtil.getQuery("INSERT_CUSTOM_RELATIONSHIP_BUILDER"));
        int i = 0;
        for (ListIterator<LevelMapKey> it = hierarchyList.listIterator(); it.hasNext();) {
            LevelMapKey obj = it.next();
            String query;
            if (i++ == 0) {
                query = SQlUtil.getQuery("INSERT_CUSTOM_RELATIONSHIP_BUILDER_SUB");
            } else {
                query = "UNION ALL" + SQlUtil.getQuery("INSERT_CUSTOM_RELATIONSHIP_BUILDER_SUB");
            }
            String levelVal = levelValueMap.get(Integer.valueOf(obj.getLevelValuesSid()));
            if (levelVal.contains("'")) {
                levelVal = levelVal.replace("'", "''");
            }
            query = query.replace("[$CUSTOM_VIEW_DETAILS_SID]", String.valueOf(obj.getCustomViewDetailsSid()));
            query = query.replace("[$RELATIONSHIP_LEVEL_VALUES]", levelVal);
            query = query.replace("[$CUSTOM_HIERARCHY_NO]", obj.getHierarchyNo());
            customQuery.append(query);
        }
       if (!hierarchyList.isEmpty()) {
            HelperTableLocalServiceUtil.executeUpdateQuery(customQuery.toString());
        }

    }

    private void buildAndExecute_customCCP_update() {
        LOGGER.debug("Building the final query and inserting :");

        StringBuilder ccpfinalQuery = new StringBuilder(SQlUtil.getQuery("UPDATE_CUSTOM_CCP_MAP"));
           String selectQuery = "";
           StringBuilder selectQueryStringBuilder = new StringBuilder();
        for (ListIterator<LevelMapKey> it = hierarchyList.listIterator(); it.hasNext();) {
            LevelMapKey obj = it.next();
            String query;
            query = SQlUtil.getQuery("UPDATE_CUSTOM_CCP_MAP_SELECT");
            query = query.replace("[$CUSTOM_VIEW_DETAILS_SID]", String.valueOf(obj.getCustomViewDetailsSid()));
            query = query.replace("[$RELATIONSHIP_LEVEL_VALUES_SID]", obj.getLevelValuesSid());
            query = query.replace("[$HIERARCHY_NO]", obj.getHierarchyNo());
            List<Integer> jsd=obj.getCcpIds();
            for (int j = 0; j < jsd.size(); j++) {
                Integer levelCcpId = jsd.get(j);
               String subQuery = query.replace("[$CCP_DETAILS_SID]", String.valueOf(levelCcpId));
                if (selectQueryStringBuilder.length() == 0) {
                    selectQueryStringBuilder.append(subQuery);
                } else {
                    selectQueryStringBuilder.append(" UNION ALL ").append(subQuery);
                }
            }
        }
        selectQuery = selectQueryStringBuilder.toString();
        if (!hierarchyList.isEmpty()) {
            ccpfinalQuery = new StringBuilder(ccpfinalQuery.toString().replace("[$UPDATE_CUSTOM_CCP_MAP_SELECT]", selectQuery));
            HelperTableLocalServiceUtil.executeUpdateQuery(ccpfinalQuery.toString());
        }
    }

    private void customize_data(List<Object[]> rawList) {

        for (ListIterator<Object[]> it = rawList.listIterator(); it.hasNext();) { 
            Object[] obj = it.next();
            it.remove();
            String levelRef = obj[NumericConstants.TWO] == null ? "" : obj[NumericConstants.TWO].toString();
            if (levelRef.equals("User Defined")) {
                int levelId = Integer.parseInt(obj[NumericConstants.THREE] == null ? "" : obj[NumericConstants.THREE].toString());
                String levelValue = obj[NumericConstants.FOUR] == null ? "" : obj[NumericConstants.FOUR].toString();
                levelValueMap.put(levelId, levelValue);

            } else {
                int level = Integer.parseInt(obj[NumericConstants.THREE] == null ? "0" : obj[NumericConstants.THREE].toString());//Changed from "" to "0" for GAL-5444
                String table = obj[0] == null ? "" : obj[0].toString();
                String column = obj[1] == null ? "" : obj[1].toString();
                String[] strArray = {table, column, ""};
                levelTableFieldNames.put(level, strArray);
                if(tableNameSet.add(table)){
                     if (tableName.length() > 0) {
                    tableName.append(',');
                }
                tableName.append('\'').append(table).append('\'');
                }
                  if(fieldNameSet.add(column)){
                      if (fieldName.length() > 0) {
                    fieldName.append(',');
                }
                fieldName.append('\'').append(column).append('\'');
                  }
            }
        }
    }

    private void getHelperListData() {
        LOGGER.debug("Checking the tables presnt in view VW_HELPER_LIST :");
        if ((tableName.length() > 0) && (fieldName.length() > 0)) {
        String query = SQlUtil.getQuery("VW_HELPER_LIST_SELCTION");
                query = query.replace("[$TABLE_NAMES]", tableName.toString());
                query = query.replace("[$COLUMN_NAMES]", fieldName.toString());
                List<Object[]> helperList = HelperTableLocalServiceUtil.executeSelectQuery(query);
                for (ListIterator<Object[]> it = helperList.listIterator(); it.hasNext();) {
                    Object[] obj = it.next();
                    it.remove();
                    String key = (obj[0] == null ? "" : obj[0].toString()) + '-' + (obj[1] == null ? "" : obj[1].toString());
String listName = obj[NumericConstants.FIVE] == null ? "" : obj[NumericConstants.FIVE].toString();
tableFieldHelperList.put(key, listName);
listNameCollection.add(listName);
                } 
        }
        HelperListUtil.getInstance().loadValuesWithid(listNameCollection);

    }

    private List<Object[]> build_custom_query() {
        LOGGER.debug("build_custom_query for selecting the table values :");
        String query;
        StringBuilder queryBuilder = new StringBuilder();
        String lastTableName = "";
        String lastFieldName = "";
        String levelSIdCustomQuery = "";
        int lastElement = levelTableFieldNames.size();
        int i = 0;
        for (Map.Entry<Integer, String[]> entry : levelTableFieldNames.entrySet()) {
            i++;
            Integer levelIdCustomQuery = entry.getKey();
            String[] objArray = entry.getValue();
            String key = objArray[0] + "-" + objArray[1];
            String listName = tableFieldHelperList.get(key);
            if (listName == null) {
                if ((!objArray[0].equals(lastTableName) || i == lastElement) && !lastTableName.isEmpty()) {
                    if (!queryBuilder.toString().isEmpty()) {
                        queryBuilder.append(" UNION ALL ");
                    }
                    queryBuilder.append("SELECT ").append(lastFieldName ).append( ',' ).append( lastTableName ).append( "_SID  " ).append( " FROM " ).append( lastTableName ).append( " WHERE " ).append( lastTableName ).append( "_SID in( " ).append( levelSIdCustomQuery ).append( " )");
                }
            } else {
                helperListValues.add(levelIdCustomQuery);
            }
            lastTableName = objArray[0];
            lastFieldName = objArray[1];
        }
        query = queryBuilder.toString();
        finalQuery.append(query);
        List<Object[]> rawList = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery.toString());
        return rawList;

    }

    private void customizeFinalResult(List<Object[]> rawList) {

        for (ListIterator<Object[]> it = rawList.listIterator(); it.hasNext();) {
            Object[] obj = it.next();
            int levelIdFinalResult = Integer.parseInt(obj[1] == null ? "" : obj[1].toString());
            String levelValueFinalResult = obj[0] == null ? "" : obj[0].toString();
            levelValueMap.put(levelIdFinalResult, levelValueFinalResult);
        }
        return;

    }

    private void updateHelperListValues() {
        for (ListIterator<Integer> it = helperListValues.listIterator(); it.hasNext();) {
            Integer id = it.next();
            levelValueMap.put(id, HelperListUtil.getInstance().getIdDescMap().get(id));
        }
    }

    public void split_ccp(List<Object[]> rawList) {
        LOGGER.debug("spliting the ccp data :");
        levelNoSet.clear();
        if (ccpMap != null) {
            ccpMap.clear();
        }
        levelCcpIds.clear();
        try {

            for (ListIterator<Object[]> it = rawList.listIterator(); it.hasNext();) {
                Object[] obj = it.next();
                it.remove();
                String levelValueCCP = obj[1].toString();
                String levelNoCCP = obj[0].toString();

                if (levelNoSet.add(levelNoCCP)) {
                    ccpMap = new HashMap();
                    levelCcpIds.add(ccpMap);
                }
                LevelMapKey key = new LevelMapKey(levelNoCCP, levelValueCCP);

                key.setCustomViewDetailsSid(Integer.parseInt(obj[NumericConstants.THREE].toString()));
                List ccpIds = ccpMap.get(key);
                if (ccpIds == null) {
                    ccpIds = new ArrayList();
                    ccpMap.put(key, ccpIds);
                }
                ccpIds.add(Integer.valueOf(obj[NumericConstants.TWO].toString()));
            }

            formHierarchy();
        } catch (CloneNotSupportedException | NumberFormatException e) {
            LOGGER.error(e.getMessage());
        }

    }

    private void formHierarchy() throws CloneNotSupportedException  {

        int levelCount = levelCcpIds.size();
        String levelValueSidHier = "";
        for (int i = 0; i < levelCount; i++) {

            int levelHi = 0;
            Map<LevelMapKey, List<Integer>> map = levelCcpIds.get(i);
            for (Map.Entry<LevelMapKey, List<Integer>> entry : map.entrySet()) {
                LevelMapKey currentKey = entry.getKey().clone();
                List<Integer> list = entry.getValue();
                if (i == 0) {

                    String hierarchyNo = (++levelHi) + DOT;
                    currentKey.setHierarchyNo(hierarchyNo);
                    currentKey.setCcpIds(list);
                    hierarchyList.add(currentKey.clone());
                } else {

                    String parentHierarchy = "";
                    LevelMapKey lastKey = null;
                    for (int j = 0; j < list.size(); j++) {
                        Integer ccpId = list.get(j);
                        String parentHierarchyNo = findParentHierarchyNo(ccpId, Integer.parseInt(currentKey.getLevelNo()) - 1);

                        if (!parentHierarchy.equals(parentHierarchyNo)) {
                            if (!levelValueSidHier.equals(currentKey.getLevelValuesSid())) {
                                levelHi++;
                            }
                            levelValueSidHier = currentKey.getLevelValuesSid();
                            String hierarchyN0 = parentHierarchyNo + (levelHi) + DOT;

                            LevelMapKey newKey = new LevelMapKey(currentKey.getLevelNo(), currentKey.getLevelValuesSid());
                            newKey.setHierarchyNo(hierarchyN0);

                            newKey.getCcpIds().add(ccpId);
                            newKey.setCustomViewDetailsSid(currentKey.getCustomViewDetailsSid());

                            hierarchyList.add(newKey);
                            lastKey = newKey;
                        } else {
                            if (lastKey != null) {

                                lastKey.getCcpIds().add(ccpId);
                            }
                        }
                        parentHierarchy = parentHierarchyNo;

                    }

                }
            }
        }

    }

    private String findParentHierarchyNo(int ccpId, int levelNo)  {

        String parentHierarchyNo = "";
        for (int i = 0; i < hierarchyList.size(); i++) {
            LevelMapKey key = hierarchyList.get(i);

            if ((String.valueOf(levelNo).equals(key.getLevelNo())) && (key.getCcpIds().contains(ccpId))) {
                    parentHierarchyNo = key.getHierarchyNo();
                    break;

            }

        }
        return parentHierarchyNo;
    }

}
