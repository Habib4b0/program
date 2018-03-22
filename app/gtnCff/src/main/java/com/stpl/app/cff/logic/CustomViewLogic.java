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

    public void executeDelete(String customViewMasterSid) {
        String query = SQlUtil.getQuery("DELETE_CUSTOM_VIEW_TABLES");
        query = query.replace(StringConstantsUtil.DOLLAR_CUSTOM_VIEW_MASTER_SID, customViewMasterSid);
        HelperTableLocalServiceUtil.executeUpdateQuery(query);

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