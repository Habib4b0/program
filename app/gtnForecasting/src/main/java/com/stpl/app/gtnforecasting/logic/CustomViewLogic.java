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
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import org.jboss.logging.Logger;

/**
 *
 * @author karthikeyans
 */
public class CustomViewLogic {

    private Map<LevelMapKey, List<Integer>> ccpMap = null;
    private final List<Map<LevelMapKey, List<Integer>>> level_ccpIds = new ArrayList();
    private final Set<String> levelNoSet = new HashSet();
    public static final String DOT = ".";

    private final Map<Integer, String> levelValue_map = new HashMap();
    private final Map<Integer, String[]> level_table_field_names = new LinkedHashMap();
    private final Map<String, String> table_field_helperList = new HashMap();
    private final List<String> listNameCollection = new ArrayList();
    private final List<Integer> helperList_values = new ArrayList();
    private final StringBuilder tableName = new StringBuilder();
     private final Set<String> tableNameSet = new HashSet();
    private final StringBuilder fieldName = new StringBuilder();
     private final Set<String> fieldNameSet = new HashSet();
    private final StringBuilder finalQuery = new StringBuilder();
    private final List<LevelMapKey> hierarchyList = new ArrayList();

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(CustomViewLogic.class.getName());
    public Map<Integer, String> getData_custom_view(String projectionId, String customViewMasterSid) {
        levelValue_map.clear();
        level_table_field_names.clear();
        tableName.setLength(0);
        fieldName.setLength(0);
        table_field_helperList.clear();
        listNameCollection.clear();
        finalQuery.setLength(0);
        hierarchyList.clear();
        helperList_values.clear();
        level_ccpIds.clear();
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
        buildAndExecute_customCCP_update();

        return levelValue_map;

    }

    private List<Object[]> executeQuery(String projectionId, String customViewMasterSid, String queryName) {
        LOGGER.debug("select the Data from CUSTOM_CCP_MAP  table :"+queryName);
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
        StringBuilder finalQuery = new StringBuilder(SQlUtil.getQuery("INSERT_CUSTOM_RELATIONSHIP_BUILDER"));
        int i = 0;
        for (ListIterator<LevelMapKey> it = hierarchyList.listIterator(); it.hasNext();) {
            LevelMapKey obj = it.next();
            String query;
            if (i++ == 0) {
                query = SQlUtil.getQuery("INSERT_CUSTOM_RELATIONSHIP_BUILDER_SUB");
            } else {
                query = "UNION ALL" + SQlUtil.getQuery("INSERT_CUSTOM_RELATIONSHIP_BUILDER_SUB");
            }
            String levelVal = levelValue_map.get(Integer.valueOf(obj.getLevelValuesSid()));
            if (levelVal.contains("'")) {
                levelVal = levelVal.replace("'", "''");
            }
            query = query.replace("[$CUSTOM_VIEW_DETAILS_SID]", String.valueOf(obj.getCustomViewDetailsSid()));
            query = query.replace("[$RELATIONSHIP_LEVEL_VALUES]", levelVal);
            query = query.replace("[$CUSTOM_HIERARCHY_NO]", obj.getHierarchyNo());
            finalQuery.append(query);
        }
       if (!hierarchyList.isEmpty()) {
            HelperTableLocalServiceUtil.executeUpdateQuery(finalQuery.toString());
        }

    }

    private void buildAndExecute_customCCP_update() {
        LOGGER.debug("Building the final query and inserting :");

        StringBuilder finalQuery = new StringBuilder(SQlUtil.getQuery("UPDATE_CUSTOM_CCP_MAP"));
           String selectQuery = "";
        for (ListIterator<LevelMapKey> it = hierarchyList.listIterator(); it.hasNext();) {
            LevelMapKey obj = it.next();
            String query;
            query = SQlUtil.getQuery("UPDATE_CUSTOM_CCP_MAP_SELECT");
            query = query.replace("[$CUSTOM_VIEW_DETAILS_SID]", String.valueOf(obj.getCustomViewDetailsSid()));
            query = query.replace("[$RELATIONSHIP_LEVEL_VALUES_SID]", obj.getLevelValuesSid());
            query = query.replace("[$HIERARCHY_NO]", obj.getHierarchyNo());
            List<Integer> jsd=obj.getCcpIds();
            for (int j = 0; j < jsd.size(); j++) {
                Integer level_ccpId = jsd.get(j);
               String subQuery = query.replace("[$CCP_DETAILS_SID]", String.valueOf(level_ccpId));
                if (selectQuery.length() == 0) {
                    selectQuery += subQuery;
                } else {
                    selectQuery += " UNION ALL " + subQuery;
                }
            }
        }
        if (!hierarchyList.isEmpty()) {
            finalQuery = new StringBuilder(finalQuery.toString().replace("[$UPDATE_CUSTOM_CCP_MAP_SELECT]", selectQuery));
            HelperTableLocalServiceUtil.executeUpdateQuery(finalQuery.toString());
        }
    }

    private void customize_data(List<Object[]> rawList) {

        for (ListIterator<Object[]> it = rawList.listIterator(); it.hasNext();) { 
            Object[] obj = it.next();
            it.remove();
            String levelRef = obj[NumericConstants.TWO] == null ? "" : obj[NumericConstants.TWO].toString();
            if (levelRef.equals("User Defined")) {
                int level_id = Integer.valueOf(obj[NumericConstants.THREE] == null ? "" : obj[NumericConstants.THREE].toString());
                String level_value = obj[NumericConstants.FOUR] == null ? "" : obj[NumericConstants.FOUR].toString();
                levelValue_map.put(level_id, level_value);

            } else {
                int level_id = Integer.valueOf(obj[NumericConstants.THREE] == null ? "0" : obj[NumericConstants.THREE].toString());//Changed from "" to "0" for GAL-5444
                String table = obj[0] == null ? "" : obj[0].toString();
                String column = obj[1] == null ? "" : obj[1].toString();
                String[] strArray = {table, column, ""};
                level_table_field_names.put(level_id, strArray);
                if(tableNameSet.add(table)){
                     if (tableName.length() > 0) {
                    tableName.append(",");
                }
                tableName.append("'").append(table).append("'");
                }
                  if(fieldNameSet.add(column)){
                      if (fieldName.length() > 0) {
                    fieldName.append(",");
                }
                fieldName.append("'").append(column).append("'");
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
                    String key = (obj[0] == null ? "" : obj[0].toString()) + "-" + (obj[1] == null ? "" : obj[1].toString());
String listName = obj[NumericConstants.FIVE] == null ? "" : obj[NumericConstants.FIVE].toString();
table_field_helperList.put(key, listName);
listNameCollection.add(listName);
                } 
        }
        HelperListUtil.getInstance().loadValuesWithid(listNameCollection);

    }

    private List<Object[]> build_custom_query() {
        LOGGER.debug("build_custom_query for selecting the table values :");
        String query = "";
        String lastTableName = "";
        String lastFieldName = "";
        String level_sid = "";
        int lastElement = level_table_field_names.size();
        int i = 0;
        for (Map.Entry<Integer, String[]> entry : level_table_field_names.entrySet()) {
            i++;
            Integer level_id = entry.getKey();
            String[] objArray = entry.getValue();
            String key = objArray[0] + "-" + objArray[1];
            String list_Name = table_field_helperList.get(key);
            if (list_Name == null) {

                if (!level_sid.isEmpty()) {
                    level_sid += " , ";
                }
                level_sid += level_id;
                if ((!objArray[0].equals(lastTableName) || i == lastElement) && !lastTableName.isEmpty()) {
                    if (!query.isEmpty()) {
                        query += " UNION ALL ";
                    }
                    query += "SELECT " + lastFieldName + "," + lastTableName + "_SID  " + " FROM " + lastTableName + " WHERE " + lastTableName + "_SID in( " + level_sid + " )";
                }
            } else {
                helperList_values.add(level_id);
            }
            lastTableName = objArray[0];
            lastFieldName = objArray[1];
        }
        finalQuery.append(query);
        List<Object[]> rawList = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery.toString());
        return rawList;

    }

    private void customizeFinalResult(List<Object[]> rawList) {

        for (ListIterator<Object[]> it = rawList.listIterator(); it.hasNext();) {
            Object[] obj = it.next();
            int level_id = Integer.valueOf(obj[1] == null ? "" : obj[1].toString());
            String level_value = obj[0] == null ? "" : obj[0].toString();
            levelValue_map.put(level_id, level_value);
        }
        return;

    }

    private void updateHelperListValues() {
        for (ListIterator<Integer> it = helperList_values.listIterator(); it.hasNext();) {
            Integer id = it.next();
            levelValue_map.put(id, HelperListUtil.getInstance().getIdDescMap().get(id));
        }
    }

    public void split_ccp(List<Object[]> rawList) {
        LOGGER.debug("spliting the ccp data :");
        levelNoSet.clear();
        if (ccpMap != null) {
            ccpMap.clear();
        }
        level_ccpIds.clear();
        try {

            for (ListIterator<Object[]> it = rawList.listIterator(); it.hasNext();) {
                Object[] obj = it.next();
                it.remove();
                String level_value = obj[1].toString();
                String level_No = obj[0].toString();

                if (levelNoSet.add(level_No)) {
                    ccpMap = new HashMap();
                    level_ccpIds.add(ccpMap);
                }
                LevelMapKey key = new LevelMapKey(level_No, level_value);

                key.setCustomViewDetailsSid(Integer.valueOf(obj[NumericConstants.THREE].toString()));
                List ccpIds = ccpMap.get(key);
                if (ccpIds == null) {
                    ccpIds = new ArrayList();
                    ccpMap.put(key, ccpIds);
                }
                ccpIds.add(Integer.valueOf(obj[NumericConstants.TWO].toString()));
            }

            formHierarchy();
        } catch (Exception e) {
            LOGGER.error(e);
        }

    }

    private void formHierarchy() throws CloneNotSupportedException  {

        int levelCount = level_ccpIds.size();
        String level_values_sid = "";
        for (int i = 0; i < levelCount; i++) {

            int levelHi = 0;
            Map<LevelMapKey, List<Integer>> map = level_ccpIds.get(i);
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
                        String parentHierarchyNo = findParentHierarchyNo(ccpId, Integer.valueOf(currentKey.getLevelNo()) - 1);

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

    private String findParentHierarchyNo(int ccpId, int levelNo)  {

        String parentHierarchyNo = "";
        for (int i = 0; i < hierarchyList.size(); i++) {
            LevelMapKey key = hierarchyList.get(i);

            if ((String.valueOf(levelNo).equals(key.getLevelNo())) && (key.getCcpIds().contains(ccpId))) {
                    parentHierarchyNo = key.getHierarchyNo();

            }

        }
        return parentHierarchyNo;
    }

    Comparator comp = new Comparator<LevelMapKey>() {

        @Override
        public int compare(LevelMapKey o1, LevelMapKey o2) {
            return o1.getLevelNo().compareTo(o2.getLevelNo());
        }
    };

}
