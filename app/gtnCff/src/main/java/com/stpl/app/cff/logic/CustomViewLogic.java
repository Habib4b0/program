/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.logic;

import com.stpl.app.cff.ui.projectionresults.dto.LevelMapKey;
import com.stpl.app.cff.util.HelperListUtil;
import com.stpl.app.cff.util.StringConstantsUtil;
import com.stpl.app.cff.util.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jayaram
 */
public class CustomViewLogic {
    
    
    private Map<LevelMapKey, List<Integer>> ccpMap = null;
    private final List<Map<LevelMapKey, List<Integer>>> levelCcpIds = new ArrayList();
    private final Set<String> levelNoSet = new HashSet();
    
    public static final String DOT = ".";

    private final Map<Integer, String> levelValMap = new HashMap();
    private final Map<Integer, String[]> levelTableFieldNames = new HashMap();
    private final Map<String, String> tableFieldHelperList = new HashMap();
    private final List<String> listNameCollection = new ArrayList();
    private final List<Integer> helperListValues = new ArrayList();
    private final StringBuilder tableName = new StringBuilder();
    private final StringBuilder fieldName = new StringBuilder();
    private final StringBuilder finalQuery = new StringBuilder();
    private final List<LevelMapKey> hierarchyList = new ArrayList();

      /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomViewLogic.class.getName());
    public Map<Integer, String> getData_custom_view(String projectionId, String customViewMasterSid) {

        levelValMap.clear();
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
       
        List<Object[]> rawList_ccp = executeQuery(projectionId, customViewMasterSid, "GET_CCP_VALUES");
        split_ccp(rawList_ccp);
        build_executeFinalQuery(hierarchyList);

        return levelValMap;

    }

    private List<Object[]> executeQuery(String projectionId, String customViewMasterSid, String queryName) {
          LOGGER.debug("select the Data from CUSTOM_CCP_MAP  table :");
        String query = SQlUtil.getQuery(queryName);
        query = query.replace("[$PROJECTION_MASTER_SID]", projectionId);
        query = query.replace(StringConstantsUtil.DOLLAR_CUSTOM_VIEW_MASTER_SID, customViewMasterSid);
        List<Object[]> rawList = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return rawList;

    }

    private void executeInsertOrUpdate(String projectionId, String customViewMasterSid, String queryName) {
        LOGGER.debug("Insert the Data in CUSTOM_CCP_MAP table: {} and queryName= {}", customViewMasterSid, queryName);
        String query = SQlUtil.getQuery(queryName);
        query = query.replace("[$PROJECTION_MASTER_SID]", projectionId);
        query = query.replace(StringConstantsUtil.DOLLAR_CUSTOM_VIEW_MASTER_SID, customViewMasterSid);
        HelperTableLocalServiceUtil.executeUpdateQuery(query);

    }

    public void executeDelete(String customViewMasterSid) {
        String query = SQlUtil.getQuery("DELETE_CUSTOM_VIEW_TABLES");
        query = query.replace(StringConstantsUtil.DOLLAR_CUSTOM_VIEW_MASTER_SID, customViewMasterSid);
        HelperTableLocalServiceUtil.executeUpdateQuery(query);

    }

    private void build_executeFinalQuery(List<LevelMapKey> hierarchyList) {
        LOGGER.debug("Building the final query and inserting :");
        StringBuilder finalQuery = new StringBuilder(SQlUtil.getQuery("INSERT_CUSTOM_RELATIONSHIP_BUILDER"));
        int i = 0;
        for (ListIterator<LevelMapKey> it = hierarchyList.listIterator(); it.hasNext();) {
            LevelMapKey obj = it.next();
            String query;
            if (i++ == 0) {
                query = SQlUtil.getQuery("INSERT_CUSTOM_RELATIONSHIP_BUILDER_SUB");
            } else {
                query = "," + SQlUtil.getQuery("INSERT_CUSTOM_RELATIONSHIP_BUILDER_SUB");
            }
            String levelVal = levelValMap.get(Integer.valueOf(obj.getLevelValuesSid()));
            query = query.replace("[$CUSTOM_VIEW_DETAILS_SID]", String.valueOf(obj.getCustomViewDetailsSid()));
            query = query.replace("[$RELATIONSHIP_LEVEL_VALUES]", levelVal);
            query = query.replace("[$CUSTOM_HIERARCHY_NO]", obj.getHierarchyNo());
            finalQuery.append(query);
        }
        HelperTableLocalServiceUtil.executeUpdateQuery(finalQuery.toString());

    }

    private void customize_data(List<Object[]> rawList) {

        for (ListIterator<Object[]> it = rawList.listIterator(); it.hasNext();) {
            Object[] obj = it.next();
            it.remove();
            String levelRef = obj[NumericConstants.TWO] == null ? "" : obj[NumericConstants.TWO].toString();
            if (levelRef.equals("User Defined")) {
                int level_id = Integer.valueOf(obj[NumericConstants.THREE] == null ? "" : obj[NumericConstants.THREE].toString());
                String level_value = obj[NumericConstants.FOUR] == null ? "" : obj[NumericConstants.FOUR].toString();
                levelValMap.put(level_id, level_value);

            } else {
                int level_id = Integer.valueOf(obj[NumericConstants.THREE] == null ? "" : obj[NumericConstants.THREE].toString());
                String table = obj[0] == null ? "" : obj[0].toString();
                String column = obj[1] == null ? "" : obj[1].toString();
                String[] strArray = {table, column, ""};
                levelTableFieldNames.put(level_id, strArray);

                if (tableName.length() > 0) {
                    tableName.append(",");
                }
                tableName.append("'").append(table).append("'");
                if (fieldName.length() > 0) {
                    fieldName.append(",");
                }
                fieldName.append("'").append(column).append("'");

            }
        }

    }

    private void getHelperListData() {
  LOGGER.debug("Checking the tables presnt in view VW_HELPER_LIST :");
        String query = SQlUtil.getQuery("VW_HELPER_LIST_SELCTION");
        query = query.replace("[$TABLE_NAMES]", tableName.toString());
        query = query.replace("[$COLUMN_NAMES]", fieldName.toString());

        List<Object[]> helperList = HelperTableLocalServiceUtil.executeSelectQuery(query);
        for (ListIterator<Object[]> it = helperList.listIterator(); it.hasNext();) {
            Object[] obj = it.next();
            it.remove();
            String key = (obj[0] == null ? "" : obj[0].toString()) + "-" + (obj[1] == null ? "" : obj[1].toString());
            String listName = obj[NumericConstants.FIVE] == null ? "" : obj[NumericConstants.FIVE].toString();
            tableFieldHelperList.put(key, listName);
            listNameCollection.add(listName);
        }

        HelperListUtil.getInstance().loadValuesWithid(listNameCollection);

    }

    private List<Object[]> build_custom_query() {
         LOGGER.debug("build_custom_query for selecting the table values :");
        String query = "";
        for (Map.Entry<Integer, String[]> entry : levelTableFieldNames.entrySet()) {
            Integer level_id = entry.getKey();
            String[] objArray = entry.getValue();
            String key = objArray[0] + "-" + objArray[1];
            String list_Name = tableFieldHelperList.get(key);
            if (list_Name == null) {
                query = "SELECT " + objArray[1] + "," + objArray[0] + "_SID  " + " FROM " + objArray[0] + " WHERE " + objArray[0] + "_SID = " + level_id;

            } else {
                helperListValues.add(level_id);
            }
            if (finalQuery.length() > 0) {
                finalQuery.append(" UNION ALL ");
            }
            finalQuery.append(query);
        }

        List<Object[]> rawList = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery.toString());
        return rawList;

    }

    private void customizeFinalResult(List<Object[]> rawList) {

        for (ListIterator<Object[]> it = rawList.listIterator(); it.hasNext();) {
            Object[] obj = it.next();
            int level_id = Integer.valueOf(obj[1] == null ? "" : obj[1].toString());
            String level_value = obj[0] == null ? "" : obj[0].toString();
            levelValMap.put(level_id, level_value);
        }
        return;

    }

    private void updateHelperListValues() {
        for (ListIterator<Integer> it = helperListValues.listIterator(); it.hasNext();) {
            Integer id = it.next();
            levelValMap.put(id, HelperListUtil.getInstance().getIdDescMap().get(id));
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
                String level_value = obj[1].toString();
                String level_No = obj[0].toString();
                if (levelNoSet.add(level_No)) {
                    ccpMap = new HashMap();
                    levelCcpIds.add(ccpMap);
                }
                LevelMapKey key = new LevelMapKey(level_No, level_value);

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

    private void formHierarchy() throws CloneNotSupportedException {

        int levelCount = levelCcpIds.size();
        String level_values_sid = "";
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
                            if (!level_values_sid.equals(currentKey.getLevelValuesSid())) {
                                levelHi++;
                            }
                            level_values_sid = currentKey.getLevelValuesSid();
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

    private String findParentHierarchyNo(int ccpId, int levelNo) {

        String parentHierarchyNo = "";
        for (int i = 0; i < hierarchyList.size(); i++) {
            LevelMapKey key = hierarchyList.get(i);

            if (String.valueOf(levelNo).equals(key.getLevelNo()) && key.getCcpIds().contains(ccpId)) {
                    parentHierarchyNo = key.getHierarchyNo();
                }


        }
        return parentHierarchyNo;
    }

}