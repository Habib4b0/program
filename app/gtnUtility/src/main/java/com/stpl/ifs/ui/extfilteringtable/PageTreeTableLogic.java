/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.ifs.ui.extfilteringtable;

import com.stpl.ifs.ui.util.GtnSmallHashMap;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.Container.Hierarchical;
import com.vaadin.v7.data.Container.Indexed;
import com.vaadin.v7.data.Property;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.asi.ui.extfilteringtable.paged.logic.ContainerLogic;
import org.asi.ui.extfilteringtable.paged.logic.HierarchyString;

/**
 * The Class PageTreeTableLogic.
 *
 * @author Abhiram
 */
public abstract class PageTreeTableLogic extends PageTreeLogicBase {

    /**
     * The minimize depth.
     */
    private boolean minimizeDepth = false;

    /**
     * The current page data.
     */
    private GtnSmallHashMap currentPageData = new GtnSmallHashMap();

    /**
     * The current load data.
     */
    private final GtnSmallHashMap currentLoadData = new GtnSmallHashMap();

    /**
     * The expandable level.
     */
    private Set<String> expandableLevel = new HashSet<String>();

    /**
     * The tree depth map list.
     */
    private final GtnSmallHashMap treeDepthMapList = new GtnSmallHashMap();

    /**
     * The min depth.
     */
    private int minDepth = 0;

    /**
     * The current page tree level.
     */
    private final List<String> currentPageTreeLevel = new ArrayList<String>();

    /**
     * The expanded tree list.
     */
    private final GtnSmallHashMap expandedTreeList = new GtnSmallHashMap();

    /**
     * The expanded tree data fetchable.
     */
    private Set<String> expandedTreeDataFetchable = new HashSet<String>();

    /**
     * The tree index map list.
     */
    private final GtnSmallHashMap treeIndexMapList = new GtnSmallHashMap();

    /**
     * The non fetchable data.
     */
    private GtnSmallHashMap nonFetchableData = new GtnSmallHashMap();

    /**
     * The last parent index.
     */
    private int lastParentIndex = -1;

    /**
     * The default parent tree level.
     */
    private final String defaultParentTreeLevel = "";

    /**
     * The parent tree level.
     */
    private String parentTreeLevel = defaultParentTreeLevel;

    /**
     * The temp parent tree level.
     */
    private String tempParentTreeLevel = defaultParentTreeLevel;

    /**
     * Load data.
     *
     * @param start the start
     * @param offset the offset
     * @return the map
     */
    public abstract GtnSmallHashMap loadData(int start, int offset);

    /**
     * The expanded fetchable.
     */
    private boolean expandedFetchable = false;

    /**
     * The bulk data load allowed.
     */
    private boolean bulkDataLoadAllowed = false;

    /**
     * The full bulk data load allowed.
     */
    private boolean fullBulkDataLoadAllowed = false;

    /**
     * The logger.
     */
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(PageTreeTableLogic.class);

    /**
     * Gets the control table.
     *
     * @return the control table
     */
    @Override
    public ExtPagedTreeTable getControlTable() {
        return (ExtPagedTreeTable) super.getControlTable();
    }

    /**
     * Gets the depth.
     *
     * @param itemId the item id
     * @param depth the depth
     * @return the depth
     */
    public int getDepth(Object itemId, int depth) {
        String treeLevel = getTreeLevelonCurrentPage(itemId);
        if (treeLevel != null) {
            depth = treeDepthMapList.getInt(treeLevel);
        }
        return depth;
    }

    /**
     * Gets the tree levelon current page.
     *
     * @param itemId the item id
     * @return the tree levelon current page
     */
    public String getTreeLevelonCurrentPage(Object itemId) {
        String treeLevel = null;
        if (getContainerDataSource().containsId(itemId)) {
            int itemIndex = ((Indexed) getContainerDataSource()).indexOfId(itemId);
            treeLevel = currentPageTreeLevel.get(itemIndex);
        }
        return treeLevel;
    }

    public void printRecord(String treeLevel, Object itemId, String calledMethod) {

    }

    /**
     * Save current page.
     */
    @Override
    protected void saveCurrentPage() {
        currentPageData.clear();
        expandableLevel.clear();
        if (getContainerDataSource().size() > 0) {
            for (int i = 0; i < getContainerDataSource().size(); i++) {
                String treeLevel = getCurrentPageTreeLevel().get(i);
                Object itemId = getcurrentTreeData(treeLevel);
                if (itemId != null) {
                    printRecord(treeLevel, itemId, "saveCurrentPage");
                    addCurrentPageData(treeLevel, itemId);
                    if (expandedTreeList.containsKey(treeLevel)) {
                        addExpandedTreeList(treeLevel, itemId);
                    }
                    if (((Hierarchical) getContainerDataSource()).areChildrenAllowed(itemId)) {
                        addExpandableLevel(treeLevel);
                    }
                }
            }
        }
    }

    /**
     * Gets the calculated total record count.
     *
     * @return the calculated total record count
     */
    public int getCalculatedTotalRecordCount() {
        int count = 0;
        List<HierarchyString> hierarchyList = getExpandedTree(true);
        for (HierarchyString treeLevel : hierarchyList) {
            LevelMap lvlMap = getLevelMap(treeLevel.getString());
            count += lvlMap.getCount();
        }
        if (getLevelMap(defaultParentTreeLevel) != null) { // For GAL-9221,GAL-9219,GAL-9197 server log issuespla
            count += getLevelMap(defaultParentTreeLevel).getCount();
        }
        return count;
    }

    /**
     * Search child.
     *
     * @param queryStr the query str
     * @param valuesList the values list
     * @return the list
     */
    public List<String> searchChild(String queryStr, List<String> valuesList) {
        queryStr = queryStr.replaceAll("\\*", "\\\\w*");
        List<String> list = new ArrayList<String>();
        for (String str : valuesList) {
            if (str.matches(queryStr)) {
                list.add(str);
            }
        }
        return list;
    }

    /**
     * Search all child.
     *
     * @param queryStr the query str
     * @param valuesList the values list
     * @return the list
     */
    public List<String> searchAllChild(String queryStr, List<String> valuesList) {
        List<String> list = new ArrayList<String>();
        for (String str : valuesList) {
            if (str.startsWith(queryStr)) {
                list.add(str);
            }
        }
        return list;
    }

    /**
     * Gets the parent hierarchy no.
     *
     * @param hierarchyNos the hierarchy nos
     * @return the parent hierarchy no
     */
    public String getParentHierarchyNo(String hierarchyNos) {
        String hierarchyNo = hierarchyNos;
        int len = hierarchyNos.length();
        int lin = hierarchyNos.lastIndexOf(".", len - NumericConstants.TWO);
        if (lin > 0) {
            hierarchyNo = hierarchyNo.substring(0, lin + 1);
        } else {
            hierarchyNo = "";
        }
        return hierarchyNo;
    }

    /**
     * Gets the all parent levels.
     *
     * @param itemId the item id
     * @return the all parent levels
     */
    public List<String> getAllParentLevels(Object itemId) {
        String treeLevelNO = getTreeLevelonCurrentPage(itemId);
        return getAllParentLevels(treeLevelNO);
    }

    /**
     * Gets the all child levels.
     *
     * @param itemId the item id
     * @return the all child levels
     */
    public List<String> getAllChildLevels(Object itemId) {
        String treeLevelNO = getTreeLevelonCurrentPage(itemId);
        return getAllChildLevels(treeLevelNO);
    }

    /**
     * Gets the all child parent levels.
     *
     * @param itemId the item id
     * @return the all child parent levels
     */
    public List<String> getAllChildParentLevels(Object itemId) {
        String treeLevelNO = getTreeLevelonCurrentPage(itemId);
        List<String> parentLevels = getAllParentLevels(treeLevelNO);
        List<String> childLevels = getAllChildLevels(treeLevelNO);
        parentLevels.addAll(childLevels);
        return parentLevels;
    }

    /**
     * Gets the all parent levels.
     *
     * @param treeLevel the tree level
     * @return the all parent levels
     */
    public List<String> getAllParentLevels(String treeLevel) {
        List<String> hierarchyNos = getAllHierarchyNo(treeLevel);
        hierarchyNos.remove(treeLevel);
        return hierarchyNos;
    }

    /**
     * Gets the all child levels.
     *
     * @param treeLevel the tree level
     * @return the all child levels
     */
    public List<String> getAllChildLevels(String treeLevel) {
        List<String> childTableHierarchyNos = searchAllChild(treeLevel, getAllLevels());
        childTableHierarchyNos.remove(treeLevel);
        return childTableHierarchyNos;
    }

    /**
     * Gets the all child parent levels.
     *
     * @param treeLevel the tree level
     * @return the all child parent levels
     */
    public List<String> getAllChildParentLevels(String treeLevel) {
        List<String> parentLevels = getAllParentLevels(treeLevel);
        List<String> childLevels = getAllChildLevels(treeLevel);
        parentLevels.addAll(childLevels);
        return parentLevels;
    }

    /**
     * Gets the all levels.
     *
     * @return the all levels
     */
    public List<String> getAllLevels() {
        Set<String> treelevels = new HashSet();
        for (int i = 0; i < getExpandedTreeList().size(); i++) {
            treelevels.add(getExpandedTreeList().getIndex(i).getKey().toString());
        }
        treelevels.addAll(getCurrentPageTreeLevel());
        return new ArrayList<String>(treelevels);
    }

    /**
     * Gets the all hierarchy no.
     *
     * @param hierarchyNo the hierarchy no
     * @return the all hierarchy no
     */
    public static List<String> getAllHierarchyNo(String hierarchyNo) {
        List<String> allLevelHierarchy = new ArrayList<String>();
        String extraDot = "";
        if (hierarchyNo.endsWith(".")) {
            extraDot = ".";
        }
        String[] hierarchyNoArray = hierarchyNo.split("\\.");
        String hierarchyNo1 = hierarchyNoArray[0];
        allLevelHierarchy.add(hierarchyNo1 + extraDot);
        for (int i = 1; i < hierarchyNoArray.length - 1; i++) {
            hierarchyNo1 = hierarchyNo1 + "." + hierarchyNoArray[i];
            allLevelHierarchy.add(hierarchyNo1 + extraDot);
        }
        if (!allLevelHierarchy.contains(hierarchyNo)) {
            allLevelHierarchy.add(hierarchyNo);
        }
        return allLevelHierarchy;
    }

    /**
     * Creates the current page.
     *
     * @param currentPage the current page
     * @return the int
     */
    @Override
    protected int createCurrentPage(int currentPage) {
        recordCount = 0;
        LevelMap lvlMap = null;
        if (isLevelMap(parentTreeLevel)) {
            lvlMap = getLevelMap(parentTreeLevel);
            dataCount = lvlMap.getCount();
        } else {
            try {
                dataCount = getCount();
            } catch (Exception ex) {
                Logger.getLogger(ContainerLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
            lvlMap = new LevelMap(((Indexed) getContainerDataSource()).indexOfId(getCurrentPageData(parentTreeLevel)), dataCount, getColumnIdToFilterMap());
            addlevelMap(parentTreeLevel, lvlMap);
        }
        recordCount = getCalculatedTotalRecordCount();
        int recordPos = (int) recordCount;
        int x = getTotalAmountOfPages();
        totalPagesLabel.setValue(String.valueOf(x));
        if (currentPage <= x) {
            this.currentPage = currentPage;
        } else if (currentPage <= 0) {
            this.currentPage = 1;
        } else {
            this.currentPage = x;
        }
        return recordPos;
    }

    /**
     * Gets the hierarchy index.
     *
     * @param nextTreeLevel the next tree level
     * @return the hierarchy index
     */
    protected int getHierarchyIndex(String nextTreeLevel) {
        if (nextTreeLevel == null || nextTreeLevel.isEmpty()) {
            return 0;
        }
        String treeLevels[] = nextTreeLevel.split("\\.");
        int hierarchyIndex = Integer.valueOf(treeLevels[treeLevels.length - 1]);
        return hierarchyIndex;
    }

    /**
     * Gets the start tree level.
     *
     * @return the start tree level
     */
    protected int getStartTreeLevel() {
        treeIndexMapList.clear();
        treeDepthMapList.clear();
        int currentPageStartIndex = (getCurrentPage() - 1) * getItemsPerPage();
        currentPageTreeLevel.clear();

        minDepth = -1;
        int totalCount = 0;
        String treeLevel = "";
        List<HierarchyString> hierarchyList = getExpandedTree(true);
        for (int i = 0; i < hierarchyList.size() && currentPageTreeLevel.size() < getItemsPerPage(); i++) {
            String nextTreeLevel = hierarchyList.get(i).getString();

            totalCount = treeIteration(treeLevel, nextTreeLevel, currentPageStartIndex, totalCount);
            treeLevel = nextTreeLevel;
        }
        if (currentPageTreeLevel.size() < getItemsPerPage()) {
            String nextTreeLevel = "";
            treeIteration(treeLevel, nextTreeLevel, currentPageStartIndex, totalCount);
        }
        return currentPageTreeLevel.size();
    }

    /**
     * Sets the min depth.
     *
     * @param level the new min depth
     */
    private void setMinDepth(String level) {
        String nLevel = level.replace(".", "");
        int depth = level.length() - nLevel.length();
        if (minDepth == -1) {
            minDepth = depth - 1;
        } else if (depth < (minDepth + 1)) {
            minDepth = depth - 1;
        }
    }

    /**
     * Tree iteration.
     *
     * @param treeLevel the tree level
     * @param nextTreeLevel the next tree level
     * @param currentPageStartIndex the current page start index
     * @param totalCount the total count
     * @return the int
     */
    public int treeIteration(String treeLevel, String nextTreeLevel, int currentPageStartIndex, int totalCount) {
        LevelMap lvlMap = getLevelMap(treeLevel);
        int count = lvlMap.getCount();

        if (nextTreeLevel.startsWith(treeLevel) && !nextTreeLevel.equals(treeLevel)) {
            int hierarchyIndex = getHierarchyIndex(nextTreeLevel);
            int demoCount = totalCount + hierarchyIndex;
            if (demoCount > (currentPageStartIndex + currentPageTreeLevel.size())) {
                int index = (currentPageStartIndex + currentPageTreeLevel.size()) - totalCount + 1;
                for (int j = 0; j < (hierarchyIndex - index + 1) && currentPageTreeLevel.size() < getItemsPerPage(); j++) {
                    String k = "" + (index + j) + ".";
                    if ((index + j) == 0) {
                        k = "";
                    }
                    String level = treeLevel + k;
                    setMinDepth(level);
                    currentPageTreeLevel.add(level);
                }
            }
            totalCount = demoCount;
            if ((count - hierarchyIndex) > 0) {
                treeIndexMapList.put(treeLevel, hierarchyIndex);
            } else {
                treeIndexMapList.remove(treeLevel);
            }
        } else {
            int demoCount = totalCount + count;
            if (demoCount > (currentPageStartIndex + currentPageTreeLevel.size())) {
                int index = (currentPageStartIndex + currentPageTreeLevel.size()) - totalCount + 1;
                for (int j = 0; j < (count - index + 1) && currentPageTreeLevel.size() < getItemsPerPage(); j++) {
                    String k = "" + (index + j) + ".";
                    if ((index + j) == 0) {
                        k = "";
                    }
                    String level = treeLevel + k;
                    setMinDepth(level);
                    currentPageTreeLevel.add(level);
                }
            }
            totalCount = demoCount;
            String treeParent = getParentHierarchyNo(treeLevel);
            String nextTreeParent = getParentHierarchyNo(nextTreeLevel);
            if (treeParent.equals(nextTreeParent) && !treeLevel.startsWith(nextTreeLevel)) {
                int deductIndex = getHierarchyIndex(treeLevel);
                int hierarchyIndex = getHierarchyIndex(nextTreeLevel);
                int demoCount1 = totalCount + hierarchyIndex - deductIndex;
                if (demoCount1 > (currentPageStartIndex + currentPageTreeLevel.size())) {
                    int index = (currentPageStartIndex + currentPageTreeLevel.size()) - (totalCount - deductIndex) + 1;
                    for (int j = 0; j < (hierarchyIndex - index + 1) && currentPageTreeLevel.size() < getItemsPerPage(); j++) {
                        String k = "" + (index + j) + ".";
                        if ((index + j) == 0) {
                            k = "";
                        }
                        String level = treeParent + k;
                        setMinDepth(level);
                        currentPageTreeLevel.add(level);
                    }
                }
                totalCount = demoCount1;
                if ((getLevelMap(treeParent).getCount() - hierarchyIndex) > 0) {
                    treeIndexMapList.put(treeParent, hierarchyIndex);
                } else {
                    treeIndexMapList.remove(treeParent);
                }
            }
            if ((currentPageTreeLevel.size() < getItemsPerPage()) && (!treeParent.equals(nextTreeParent) || treeLevel.startsWith(nextTreeLevel))) {
                totalCount = getTotalCount(totalCount, treeLevel, nextTreeLevel, currentPageStartIndex);
            }
        }
        return totalCount;
    }

    /**
     * Gets the total count.
     *
     * @param totalCount the total count
     * @param treeLevel the tree level
     * @param nextTreeLevel the next tree level
     * @param currentPageStartIndex the current page start index
     * @return the total count
     */
    protected int getTotalCount(int totalCount, String treeLevel, String nextTreeLevel, int currentPageStartIndex) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < treeIndexMapList.size(); i++) {
            list.add(treeIndexMapList.getIndex(i).getKey().toString());
        }
        List<HierarchyString> hierarchyList = HierarchyString.getHierarchyStringList(list, true);
        String treeLevels[] = nextTreeLevel.split("\\.");
        String ssss = nextTreeLevel.replace(".", "");
        int len1 = nextTreeLevel.length() - ssss.length();
        for (int i = hierarchyList.size() - 1; i >= 0; i--) {
            String s = hierarchyList.get(i).getString();
            String sss = s.replace(".", "");
            int len = s.length() - sss.length();
            int count = 0;
            int demoCount;
            int treeIndex = treeIndexMapList.getInt(s);
            int cond = 0;
            if (len >= (len1)) {
                LevelMap lvlMap = getLevelMap(s);
                count = lvlMap.getCount() - treeIndex;
                cond = lvlMap.getCount();
                treeIndexMapList.remove(s);
            } else if (len == (len1 - 1)) {
                int x = Integer.valueOf(treeLevels[treeLevels.length - 1]);
                count = x - treeIndex;
                cond = x;
                treeIndexMapList.put(s, x);
            } else {
                break;
            }
            demoCount = totalCount + count;

            if (demoCount > (currentPageStartIndex + currentPageTreeLevel.size())) {
                int index = (currentPageStartIndex + currentPageTreeLevel.size()) - (totalCount - treeIndex) + 1;
                for (int j = 0; j < (cond - index + 1) && currentPageTreeLevel.size() < getItemsPerPage(); j++) {
                    String k = "" + (index + j) + ".";
                    if ((index + j) == 0) {
                        k = "";
                    }
                    String level = s + k;
                    setMinDepth(level);
                    currentPageTreeLevel.add(level);
                }
            }
            totalCount = demoCount;
            if (currentPageTreeLevel.size() >= getItemsPerPage()) {
                break;
            }
        }
        return totalCount;
    }

    /**
     * Gets the item id.
     *
     * @param treeLevels the tree levels
     * @return the item id
     */
    private Object getItemId(String treeLevels) {

        if (treeLevels.isEmpty()) {
            return new Object();
        }
        if (!isFetchableLevel(treeLevels)) {
            return getAvailableData(treeLevels);
        }
        if (isBulkDataLoadAllowed()) {
            Set<String> list = getExpandedTreeDataFetchable();
            GtnSmallHashMap bulkDataMap = new GtnSmallHashMap();
            for (String key : list) {
                bulkDataMap.put(key, getAvailableData(key));
            }
            loadBulkDataContainer(bulkDataMap);
        } else {
            String parentTreeLevels = getParentHierarchyNo(treeLevels);
            Object parentItemId = getItemId(parentTreeLevels);
            nonFetchableData.clear();
            int st = getHierarchyIndex(treeLevels) - 1;
            setLastParent(parentItemId);
            setTempParentTreeLevel(parentTreeLevels);
            lastParentIndex = getHierarchyIndex(parentTreeLevels) - 1;
            loadDataContainer(st, 1, parentTreeLevels);
        }
        if (!isFetchableLevel(treeLevels)) {
            return getAvailableData(treeLevels);
        }
        return null;
    }

    /**
     * Load current page.
     *
     * @param recordPos the record pos
     */
    @Override
    protected void loadCurrentPage(int recordPos) {
        nonFetchableData.clear();
        currentLoadData.clear();
        int offset = getStartTreeLevel();
        if (offset > 0) {
            List<String> fechedLevel = new ArrayList<String>();
            GtnSmallHashMap bulkDataMap = new GtnSmallHashMap();
            for (int i = 0; i < currentPageTreeLevel.size(); i++) {
                nonFetchableData.clear();
                String treeLevel = currentPageTreeLevel.get(i);
                String nLevel = treeLevel.replace(".", "");
                int depth = treeLevel.length() - nLevel.length() - 1;
                if (minimizeDepth) {
                    depth -= minDepth;
                }
                treeDepthMapList.put(treeLevel, depth);
                if (!fechedLevel.contains(treeLevel)) {
                    String parentTreeLevels = getParentHierarchyNo(treeLevel);
                    List<String> childList = searchChild(parentTreeLevels + "*.", currentPageTreeLevel);
                    if (childList.size() > 0) {
                        List<HierarchyString> fetchableList = new ArrayList<HierarchyString>();

                        if (isAllExpandedFetchable()) {
                            for (int j = 0; j < childList.size(); j++) {
                                String childLevel = childList.get(j);
                                boolean nextAllowed = true;
                                if (isBulkDataLoadAllowed()) {
                                    nextAllowed = false;
                                    if (currentPageData.containsKey(childLevel)) {
                                        bulkDataMap.put(childLevel, currentPageData.get(childLevel));
                                    } else if (expandedTreeList.containsKey(childLevel)) {
                                        bulkDataMap.put(childLevel, expandedTreeList.get(childLevel));
                                    } else if (currentLoadData.containsKey(childLevel)) {
                                        bulkDataMap.put(childLevel, currentLoadData.get(childLevel));
                                    } else {
                                        nextAllowed = true;
                                    }
                                }
                                if (isFullBulkDataLoadAllowed()) {
                                    bulkDataMap.put(childLevel, null);
                                    nextAllowed = false;
                                }
                                if (nextAllowed) {
                                    fetchableList.add(new HierarchyString(childLevel));
                                }
                            }

                        } else {
                            for (int j = 0; j < childList.size(); j++) {
                                String childLevel = childList.get(j);
                                int index = getHierarchyIndex(childLevel) - 1;
                                boolean nextAllowed = true;
                                Object itemId = getAvailableData(childLevel);
                                if (isFullBulkDataLoadAllowed() && (isFetchableLevel(treeLevel) || itemId == null)) {
                                    bulkDataMap.put(childLevel, itemId);
                                    nextAllowed = false;
                                }
                                if (isBulkDataLoadAllowed() && isFetchableLevel(childLevel) && itemId != null && nextAllowed) {
                                    bulkDataMap.put(childLevel, itemId);
                                    nextAllowed = false;
                                }
                                if (nextAllowed) {
                                    if (isFetchableLevel(childLevel) || itemId == null) {
                                        fetchableList.add(new HierarchyString(childLevel));
                                    } else {
                                        addNonFetchableData("" + index, itemId);
                                    }
                                }
                            }
                        }
                        if (!fetchableList.isEmpty()) {
                            Collections.sort(fetchableList, new HierarchyString());
                            String startLevel = fetchableList.get(0).getString();
                            int st = getHierarchyIndex(startLevel) - 1;
                            String endLevel = fetchableList.get(fetchableList.size() - 1).getString();
                            int of = getHierarchyIndex(endLevel) - st;
                            Object parentItemId = getItemId(parentTreeLevels);
                            if (parentItemId != null) {
                                setLastParent(parentItemId);
                                setTempParentTreeLevel(parentTreeLevels);
                                lastParentIndex = getHierarchyIndex(parentTreeLevels) - 1;
                                loadDataContainer(st, of, parentTreeLevels);
                            }
                            nonFetchableData.clear();
                        }
                        fechedLevel.addAll(childList);
                    }
                }
            }
            if (isBulkDataLoadAllowed() && !bulkDataMap.isEmpty()) {
                loadBulkDataContainer(bulkDataMap);
            }
            if (this.controlTable != null) {
                this.controlTable.removeExpandListener(expandListener);
                this.controlTable.removeCollapseListener(collapseListener);
            }
            for (int i = 0; i < currentPageTreeLevel.size(); i++) {
                String treeLevel = currentPageTreeLevel.get(i);
                Object itemId = getAvailableData(treeLevel);
                if (itemId != null) {
                    Object itemIds = configureContainer(itemId, getContainerDataSource());
                    if (isExpandableLevel(treeLevel) || expandedTreeList.containsKey(treeLevel)) {
                        ((Hierarchical) getContainerDataSource()).setChildrenAllowed(itemIds, true);
                    }
                    String parentTreeLevels = getParentHierarchyNo(treeLevel);
                    if (currentPageTreeLevel.contains(parentTreeLevels)) {
                        Object parentItemId = expandedTreeList.get(parentTreeLevels);
                        ((Hierarchical) getContainerDataSource()).setParent(itemIds, parentItemId);
                    }
                    boolean expanded = expandedTreeList.containsKey(treeLevel);
                    if (expanded) {
                        Object itemId1 = getcurrentTreeIndexData(i);
                        if (itemId1 != null) {
                            addExpandedTreeList(treeLevel, itemId1);
                        }
                    }
                    setTablesItemColapsed(itemIds, !expanded, true);
                }
            }
            currentLoadData.clear();
            currentPageData.clear();
            if (this.controlTable != null) {
                this.controlTable.addExpandListener(expandListener);
                this.controlTable.addCollapseListener(collapseListener);
            }
        }
    }

    /**
     * Gets the current tree index.
     *
     * @param hierarchyNo the hierarchy no
     * @return the current tree index
     */
    public int getcurrentTreeIndex(String hierarchyNo) {
        return getCurrentPageTreeLevel().indexOf(hierarchyNo);
    }

    /**
     * Gets the current tree index data.
     *
     * @param index the index
     * @return the current tree index data
     */
    public Object getcurrentTreeIndexData(int index) {
        if (index >= 0 && getContainerDataSource().size() > index) {
            return ((Indexed) getContainerDataSource()).getIdByIndex(index);
        }
        return null;
    }

    /**
     * Gets the current tree data.
     *
     * @param hierarchyNo the hierarchy no
     * @return the current tree data
     */
    public Object getcurrentTreeData(String hierarchyNo) {
        return getcurrentTreeIndexData(getcurrentTreeIndex(hierarchyNo));

    }

    /**
     * Gets the available data.
     *
     * @param treeLevel the tree level
     * @return the available data
     */
    protected Object getAvailableData(String treeLevel) {
        if (currentLoadData.containsKey(treeLevel)) {
            return currentLoadData.get(treeLevel);
        } else if (currentPageData.containsKey(treeLevel)) {
            return currentPageData.get(treeLevel);
        } else if (expandedTreeList.containsKey(treeLevel)) {
            return expandedTreeList.get(treeLevel);
        }
        return null;
    }

    /**
     * Sets the available data.
     *
     * @param treeLevel the tree level
     * @param itemId the item id
     */
    protected void setAvailableData(String treeLevel, Object itemId) {
        boolean added = false;
        printRecord(treeLevel, itemId, "setAvailableData");
        if (expandedTreeList.containsKey(treeLevel)) {
            addExpandedTreeList(treeLevel, itemId);
            added = true;
        }
        if (currentPageData.containsKey(treeLevel)) {
            addCurrentPageData(treeLevel, itemId);
            added = true;
        }
        if (currentLoadData.containsKey(treeLevel) || !added) {
            currentLoadData.put(treeLevel, itemId);
        }
    }

    /**
     * Load bulk data container.
     *
     * @param bulkDataMap the bulk data map
     */
    private void loadBulkDataContainer(GtnSmallHashMap bulkDataMap) {
        try {
            GtnSmallHashMap list = loadBulkData(bulkDataMap);
            if (list != null) {
                for (int i = list.size() - 1; i >= 0; i--) {
                    String key = list.getIndex(i).getKey().toString();
                    Object object = list.removeWithIndex(i);
                    setAvailableData(key, object);
                    removeExpandedTreeDataFetchable(key);
                }
            }
        } catch (Property.ReadOnlyException ex) {
            LOGGER.error(ex);
        } catch (UnsupportedOperationException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /*
     * It will return the Map what is going as parameter.
     * 
     */
    /**
     * Load bulk data.
     *
     * @param bulkDataMap the bulk data map
     * @return the map
     */
    public GtnSmallHashMap loadBulkData(GtnSmallHashMap bulkDataMap) {
        return bulkDataMap;
    }

    /**
     * Load data container.
     *
     * @param start the start
     * @param offset the offset
     * @param parentTreeLevel the parent tree level
     */
    private void loadDataContainer(int start, int offset, String parentTreeLevel) {
        try {
            GtnSmallHashMap list = loadData(start, offset);
            if (list != null) {
                for (int i = list.size() - 1; i >= 0; i--) {
                    Integer key = Integer.valueOf(list.getIndex(i).getKey().toString());
                    Object object = list.removeWithIndex(i);
                    String childLevel = parentTreeLevel + (key + 1) + ".";
                    setAvailableData(childLevel, object);
                    removeExpandedTreeDataFetchable(childLevel);
                }

            }
        } catch (Property.ReadOnlyException ex) {
            LOGGER.error(ex);
        } catch (UnsupportedOperationException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Mannage expanded tree list.
     *
     * @param itemId the item id
     * @param isExpand the is expand
     * @return the int
     */
    protected int mannageExpandedTreeList(Object itemId, boolean isExpand) {
        int itemIndex = ((Indexed) getContainerDataSource()).indexOfId(itemId);
        String treeLevel = currentPageTreeLevel.get(itemIndex);
        int page = getCurrentPage();
        if (isExpand) {
            addExpandedTreeList(treeLevel, itemId);
            if (itemIndex == getItemsPerPage() - 1) {
                page++;
            }
            parentTreeLevel = treeLevel;
        } else {
            removeLevelRecursively(treeLevel, 1);
            parentTreeLevel = getParentHierarchyNo(treeLevel);
        }
        return page;
    }

    /**
     * Removes the level recursively.
     *
     * @param treeLevel the tree level
     * @param count the count
     */
    public void removeLevelRecursively(String treeLevel, int count) {
        if (count == 0) {
            expandedTreeList.remove(treeLevel);
            treeLevelMapList.remove(treeLevel);
            return;
        }
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < expandedTreeList.size(); i++) {
            list.add(expandedTreeList.getIndex(i).getKey().toString());
        }
        List<String> childList = searchChild(treeLevel + "*.", list);
        for (String childList1 : childList) {
            removeLevelRecursively(childList1, childList.size());
        }
        expandedTreeList.remove(treeLevel);
        treeLevelMapList.remove(treeLevel);
    }

    /**
     * Expand logic.
     *
     * @param object the object
     * @param isManualLevel the is manual level
     */
    @Override
    protected void expandLogic(Object object, boolean isManualLevel) {
        int pageNo = mannageExpandedTreeList(object, true);
        this.lastParent = object;
        setCurrentPage(pageNo);
    }

    /**
     * Collapse logic.
     *
     * @param object the object
     * @param isManualLevel the is manual level
     */
    @Override
    protected void collapseLogic(Object object, boolean isManualLevel) {
        int pageNo = mannageExpandedTreeList(object, false);
        this.lastParent = object;
        setCurrentPage(pageNo);
    }

    /**
     * Gets the current page data.
     *
     * @return the current page data
     */
    public GtnSmallHashMap getCurrentPageData() {
        return currentPageData;
    }

    /**
     * Sets the current page data.
     *
     * @param currentPageData the current page data
     */
    public void setCurrentPageData(GtnSmallHashMap currentPageData) {
        this.currentPageData = currentPageData;
    }

    /**
     * Adds the current page data.
     *
     * @param levelTreeIndex the level tree index
     * @param itemId the item id
     */
    public void addCurrentPageData(String levelTreeIndex, Object itemId) {
        currentPageData.put(levelTreeIndex, itemId);
    }

    /**
     * Gets the current page data.
     *
     * @param levelTree the level tree
     * @return the current page data
     */
    public Object getCurrentPageData(String levelTree) {
        return currentPageData.get(levelTree);
    }

    /**
     * Gets the current page data level tree.
     *
     * @param itemId the item id
     * @return the current page data level tree
     */
    public String getCurrentPageDataLevelTree(Object itemId) {
        String levelTree = null;
        for (int i = 0; i < currentPageData.size(); i++) {
            String value = currentPageData.getIndex(i).getValue().toString();
            if (itemId.equals(value)) {
                levelTree = currentPageData.getIndex(i).getKey().toString();
            }
        }
        return levelTree;
    }

    /**
     * Checks if is minimize depth.
     *
     * @return true, if is minimize depth
     */
    public boolean isMinimizeDepth() {
        return minimizeDepth;
    }

    /**
     * Sets the minimize depth.
     *
     * @param minimizeDepth the new minimize depth
     */
    public void setMinimizeDepth(boolean minimizeDepth) {
        this.minimizeDepth = minimizeDepth;
    }

    /**
     * Gets the temp parent tree level.
     *
     * @return the temp parent tree level
     */
    public String getTempParentTreeLevel() {
        return tempParentTreeLevel;
    }

    /**
     * Sets the temp parent tree level.
     *
     * @param tempParentTreeLevel the new temp parent tree level
     */
    public void setTempParentTreeLevel(String tempParentTreeLevel) {
        this.tempParentTreeLevel = tempParentTreeLevel;
    }

    /**
     * Gets the expandable level.
     *
     * @return the expandable level
     */
    protected Set<String> getExpandableLevel() {
        return expandableLevel;
    }

    /**
     * Sets the expandable level.
     *
     * @param expandables the new expandable level
     */
    protected void setExpandableLevel(Set<String> expandables) {
        this.expandableLevel = expandables;
    }

    /**
     * Adds the expandable level.
     *
     * @param treeLevel the tree level
     */
    protected void addExpandableLevel(String treeLevel) {
        expandableLevel.add(treeLevel);
    }

    /**
     * Removes the expandable level.
     *
     * @param treeLevel the tree level
     * @return true, if successful
     */
    protected boolean removeExpandableLevel(String treeLevel) {
        return expandableLevel.remove(treeLevel);
    }

    /**
     * Checks if is expandable level.
     *
     * @param treeLevel the tree level
     * @return true, if is expandable level
     */
    public boolean isExpandableLevel(String treeLevel) {
        if (expandableLevel != null && treeLevel != null) {
            return expandableLevel.contains(treeLevel);
        }
        return false;
    }

    /**
     * Gets the expanded tree.
     *
     * @param sorted the sorted
     * @return the expanded tree
     */
    protected List<HierarchyString> getExpandedTree(boolean sorted) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < expandedTreeList.size(); i++) {
            list.add(expandedTreeList.getIndex(i).getKey().toString());
        }
        List<HierarchyString> hierarchyList = HierarchyString.getHierarchyStringList(list, sorted);
        return hierarchyList;
    }

    /**
     * Gets the expanded tree value.
     *
     * @param treeLevel the tree level
     * @return the expanded tree value
     */
    protected Object getExpandedTreeValue(String treeLevel) {
        return expandedTreeList.get(treeLevel);
    }

    /**
     * Gets the expanded tree list.
     *
     * @return the expanded tree list
     */
    protected GtnSmallHashMap getExpandedTreeList() {
        return expandedTreeList;
    }

    /**
     * Adds the expanded tree list.
     *
     * @param treeLevel the tree level
     * @param itemId the item id
     */
    protected void addExpandedTreeList(String treeLevel, Object itemId) {
        expandedTreeList.put(treeLevel, itemId);
    }

    /**
     * Checks for expanded tree list.
     *
     * @param treeLevel the tree level
     * @return true, if successful
     */
    protected boolean hasExpandedTreeList(String treeLevel) {
        return expandedTreeList.containsKey(treeLevel);
    }

    /**
     * Gets the expanded tree data fetchable.
     *
     * @return the expanded tree data fetchable
     */
    protected Set<String> getExpandedTreeDataFetchable() {
        return expandedTreeDataFetchable;
    }

    /**
     * Checks if is fetchable level.
     *
     * @param treeLevel the tree level
     * @return true, if is fetchable level
     */
    public boolean isFetchableLevel(String treeLevel) {
        if (expandedTreeDataFetchable != null && treeLevel != null) {
            return expandedTreeDataFetchable.contains(treeLevel);
        }
        return false;
    }

    /**
     * Sets the expanded tree data fetchable.
     *
     * @param expandedTreeDataFetchable the new expanded tree data fetchable
     */
    protected void setExpandedTreeDataFetchable(Set<String> expandedTreeDataFetchable) {
        this.expandedTreeDataFetchable = expandedTreeDataFetchable;
    }

    /**
     * Adds the expanded tree data fetchable.
     *
     * @param expandedTreeDataFetchable the expanded tree data fetchable
     */
    protected void addExpandedTreeDataFetchable(Set<String> expandedTreeDataFetchable) {
        this.expandedTreeDataFetchable.addAll(expandedTreeDataFetchable);
    }

    /**
     * Adds the expanded tree data fetchable.
     *
     * @param treeLevel the tree level
     */
    protected void addExpandedTreeDataFetchable(String treeLevel) {
        expandedTreeDataFetchable.add(treeLevel);
    }

    /**
     * Removes the expanded tree data fetchable.
     *
     * @param treeLevel the tree level
     * @return true, if successful
     */
    protected boolean removeExpandedTreeDataFetchable(String treeLevel) {
        return expandedTreeDataFetchable.remove(treeLevel);
    }

    /**
     * Gets the non fetchable data.
     *
     * @return the non fetchable data
     */
    protected GtnSmallHashMap getNonFetchableData() {
        return nonFetchableData;
    }

    /**
     * Sets the non fetchable data.
     *
     * @param nonFetchableData the non fetchable data
     */
    protected void setNonFetchableData(GtnSmallHashMap nonFetchableData) {
        this.nonFetchableData = nonFetchableData;
    }

    /**
     * Adds the non fetchable data.
     *
     * @param index the index
     * @param ItemId the item id
     */
    protected void addNonFetchableData(String index, Object ItemId) {
        nonFetchableData.put(index, ItemId);
    }

    /**
     * Gets the non fetchable data.
     *
     * @param index the index
     * @return the non fetchable data
     */
    protected Object getNonFetchableData(String index) {
        return nonFetchableData.get(index);
    }

    /**
     * Gets the last parent index.
     *
     * @return the last parent index
     */
    protected int getLastParentIndex() {
        return lastParentIndex;
    }

    /**
     * Sets the last parent index.
     *
     * @param lastParentIndex the new last parent index
     */
    protected void setLastParentIndex(int lastParentIndex) {
        this.lastParentIndex = lastParentIndex;
    }

    /**
     * Gets the current page tree level.
     *
     * @return the current page tree level
     */
    protected List<String> getCurrentPageTreeLevel() {
        return currentPageTreeLevel;
    }

    /**
     * Checks if is all expanded fetchable.
     *
     * @return true, if is all expanded fetchable
     */
    public boolean isAllExpandedFetchable() {
        return expandedFetchable;
    }

    /**
     * Sets the all expanded fetchable.
     *
     * @param expandedFetchable the new all expanded fetchable
     */
    public void setAllExpandedFetchable(boolean expandedFetchable) {
        this.expandedFetchable = expandedFetchable;
    }

    /**
     * Clear all.
     *
     * It will reset the table logic to its initial state.
     */
    @Override
    public void clearAll() {
        clearContainer();
        currentPageTreeLevel.clear();
        clearAllExceptCurrentPage();
    }

    /**
     * Clear all except current page.
     *
     * It will reset the table logic to its initial state but it will keep the
     * data present on current page container.
     */
    public void clearAllExceptCurrentPage() {
        super.clearAll();
        currentPageData.clear();
        currentLoadData.clear();
        expandableLevel.clear();
        treeDepthMapList.clear();
        expandedTreeList.clear();
        treeIndexMapList.clear();
        nonFetchableData.clear();
        lastParentIndex = -1;
        parentTreeLevel = defaultParentTreeLevel;
        tempParentTreeLevel = defaultParentTreeLevel;
    }

    /**
     * Checks if is bulk data load allowed.
     *
     * @return true, if is bulk data load allowed
     */
    public boolean isBulkDataLoadAllowed() {
        return bulkDataLoadAllowed;
    }

    /**
     * Checks if is full bulk data load allowed.
     *
     * @return true, if is bulk data load allowed
     */
    public boolean isFullBulkDataLoadAllowed() {
        return fullBulkDataLoadAllowed;
    }

    /**
     * Sets the bulk data load allowed.
     *
     * @param bulkDataLoadAllowed the bulk data load allowed
     */
    public void setBulkDataLoadAllowed(boolean bulkDataLoadAllowed) {
        this.bulkDataLoadAllowed = bulkDataLoadAllowed;
    }

    /**
     * Sets the full bulk data load allowed.
     *
     * @param bulkDataLoadAllowed the bulk data load allowed
     */
    public void setFullBulkDataLoadAllowed(boolean fullBulkDataLoadAllowed) {
        this.fullBulkDataLoadAllowed = bulkDataLoadAllowed;
    }

    /**
     * Handle filter change.
     */
    @Override
    public void handleFilterChange() {
        setCurrentPage(1);
    }

    public String getLastParentTreeLevel() {
        return parentTreeLevel;
    }
}
