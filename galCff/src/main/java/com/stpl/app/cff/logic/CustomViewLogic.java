/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.logic;

import com.stpl.app.cff.ui.projectionresults.dto.LevelMapKey;
import com.stpl.app.cff.util.HelperListUtil;
import com.stpl.app.cff.util.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import org.jboss.logging.Logger;

/**
 *
 * @author Jayaram
 */
public class CustomViewLogic {
    
    
    private Map<LevelMapKey, List<Integer>> ccpMap = null;
//    private  List<Integer> ccpIds;
    private final List<Map<LevelMapKey, List<Integer>>> level_ccpIds = new ArrayList();
//    private final Set<String> levelValueSet = new HashSet();
    private final Set<String> levelNoSet = new HashSet();
    
    public static final String DOT = ".";

    private final Map<Integer, String> levelValue_map = new HashMap();
    private final Map<Integer, String[]> level_table_field_names = new HashMap();
    private final Map<String, String> table_field_helperList = new HashMap();
    private final List<String> listNameCollection = new ArrayList();
    private final List<Integer> helperList_values = new ArrayList();
    private final StringBuilder tableName = new StringBuilder();
    private final StringBuilder fieldName = new StringBuilder();
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

        return levelValue_map;

    }

    private List<Object[]> executeQuery(String projectionId, String customViewMasterSid, String queryName) {
          LOGGER.info("select the Data from CUSTOM_CCP_MAP  table :");
        String query = SQlUtil.getQuery(queryName);
        query = query.replace("[$PROJECTION_MASTER_SID]", projectionId);
        query = query.replace("[$CUSTOM_VIEW_MASTER_SID]", customViewMasterSid);
        List<Object[]> rawList = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return rawList;

    }

    private void executeInsertOrUpdate(String projectionId, String customViewMasterSid, String queryName) {
        LOGGER.info("Insert the Data in CUSTOM_CCP_MAP table :"+customViewMasterSid+"  query name==="+queryName);
        String query = SQlUtil.getQuery(queryName);
        query = query.replace("[$PROJECTION_MASTER_SID]", projectionId);
        query = query.replace("[$CUSTOM_VIEW_MASTER_SID]", customViewMasterSid);
        HelperTableLocalServiceUtil.executeUpdateQuery(query);

    }

    public void executeDelete(String customViewMasterSid) {
        String query = SQlUtil.getQuery("DELETE_CUSTOM_VIEW_TABLES");
        query = query.replace("[$CUSTOM_VIEW_MASTER_SID]", customViewMasterSid);
        HelperTableLocalServiceUtil.executeUpdateQuery(query);

    }

    private void build_executeFinalQuery(List<LevelMapKey> hierarchyList) {
        LOGGER.info("Building the final query and inserting :");
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
            String levelVal = levelValue_map.get(Integer.valueOf(obj.getLevelValuesSid()));
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
            String levelRef = obj[2] == null ? "" : obj[2].toString();
            if (levelRef.equals("User Defined")) {
                int level_id = Integer.valueOf(obj[3] == null ? "" : obj[3].toString());
                String level_value = obj[4] == null ? "" : obj[4].toString();
                levelValue_map.put(level_id, level_value);

            } else {
                int level_id = Integer.valueOf(obj[3] == null ? "" : obj[3].toString());
                String table = obj[0] == null ? "" : obj[0].toString();
                String column = obj[1] == null ? "" : obj[1].toString();
                String[] strArray = {table, column, ""};
                level_table_field_names.put(level_id, strArray);

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
  LOGGER.info("Checking the tables presnt in view VW_HELPER_LIST :");
        String query = SQlUtil.getQuery("VW_HELPER_LIST_SELCTION");
        query = query.replace("[$TABLE_NAMES]", tableName.toString());
        query = query.replace("[$COLUMN_NAMES]", fieldName.toString());

        List<Object[]> helperList = HelperTableLocalServiceUtil.executeSelectQuery(query);
        for (ListIterator<Object[]> it = helperList.listIterator(); it.hasNext();) {
            Object[] obj = it.next();
            it.remove();
            String key = (obj[0] == null ? "" : obj[0].toString()) + "-" + (obj[1] == null ? "" : obj[1].toString());
//            String refTable = obj[2] == null ? "" : obj[2].toString();
//            String refColumn = obj[3] == null ? "" : obj[3].toString();
//            String mappingColumnNams = obj[4] == null ? "" : obj[4].toString();
            String listName = obj[5] == null ? "" : obj[5].toString();
//            String primaryKey = obj[6] == null ? "" : obj[6].toString();
//            String value[] = {refTable, refColumn, mappingColumnNams, listName, primaryKey};
            table_field_helperList.put(key, listName);
            listNameCollection.add(listName);
//           
        }

        HelperListUtil.getInstance().loadValuesWithid(listNameCollection);

    }

    private List<Object[]> build_custom_query() {
         LOGGER.info("build_custom_query for selecting the table values :");
        String query = "";
        for (Map.Entry<Integer, String[]> entry : level_table_field_names.entrySet()) {
            Integer level_id = entry.getKey();
            String[] objArray = entry.getValue();
            String key = objArray[0] + "-" + objArray[1];
            String list_Name = table_field_helperList.get(key);
            if (list_Name == null) {
                query = "SELECT " + objArray[1] + "," + objArray[0] + "_SID  " + " FROM " + objArray[0] + " WHERE " + objArray[0] + "_SID = " + level_id;

            } else {
                helperList_values.add(level_id);
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

//    public void getData_custom_view(String projectionId, String customViewMasterSid) {
//        String query = SQlUtil.getQuery("GET_TABLE_NAME-AND-COLUMN");
//        query = query.replace("[$PROJECTION_MASTER_SID]", projectionId);
//        query = query.replace("[$CUSTOM_VIEW_MASTER_SID]", customViewMasterSid);
//        List<Object[]> rawList = HelperTableLocalServiceUtil.executeSelectQuery(query);
//        for (ListIterator<Object[]> it = rawList.listIterator(); it.hasNext();) {
//            Object[] obj = it.next();
//            String levelRef = obj[2] == null ? "" : obj[2].toString();
//            if (levelRef.equals("User Defined")) {
//                int level_id = Integer.valueOf(obj[3] == null ? "" : obj[3].toString());
//                String level_value = obj[4] == null ? "" : obj[4].toString();
//                levelValue_map.put(level_id, level_value);
//                it.remove();
//            } 
//        }
//    }
    public void split_ccp(List<Object[]> rawList) {
        LOGGER.info("spliting the ccp data :");
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
//                if (level_No.equals("7")) {

                if (levelNoSet.add(level_No)) {
                    ccpMap = new HashMap();
                    level_ccpIds.add(ccpMap);
                }
                LevelMapKey key = new LevelMapKey(level_No, level_value);

                key.setCustomViewDetailsSid(Integer.valueOf(obj[3].toString()));
                List ccpIds = ccpMap.get(key);
                if (ccpIds == null) {
                    ccpIds = new ArrayList();
                    ccpMap.put(key, ccpIds);
                }
                ccpIds.add(Integer.valueOf(obj[2].toString()));
            }

            formHierarchy();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void formHierarchy() throws Exception {

        int levelCount = level_ccpIds.size();
        String level_values_sid = "";
//        Set<String> levelNoSet = new HashSet();
        
        for (int i = 0; i < levelCount; i++) {

            int levelHi = 0;

            Map<LevelMapKey, List<Integer>> map = level_ccpIds.get(i);
            for (Map.Entry<LevelMapKey, List<Integer>> entry : map.entrySet()) {
                LevelMapKey currentKey = entry.getKey().clone();
                List<Integer> list = entry.getValue();
                if (i == 0) {
                   

                    String hierarchyNo = ((++levelHi) + DOT);
                    
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
                            String hierarchyN0 = (parentHierarchyNo + (levelHi) + DOT);

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

    private String findParentHierarchyNo(int ccpId, int levelNo) throws Exception {

        String parentHierarchyNo = "";
        for (int i = 0; i < hierarchyList.size(); i++) {
            LevelMapKey key = hierarchyList.get(i);

            if (String.valueOf(levelNo).equals(key.getLevelNo())) {
                if (key.getCcpIds().contains(ccpId)) {
                    parentHierarchyNo = key.getHierarchyNo();
                }

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
