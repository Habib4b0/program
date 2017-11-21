/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.salesprojection.logic.tablelogic;

import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.dto.SalesRowDto;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.salesprojection.logic.SalesLogic;
import com.stpl.app.gtnforecasting.salesprojection.tree.SalesProjectionTree;
import com.stpl.app.gtnforecasting.salesprojection.tree.node.SalesBaseNode;
import com.stpl.app.gtnforecasting.tree.node.TreeNode;
import com.stpl.ifs.ui.extfilteringtable.PageTreeTableLogic;
import com.stpl.ifs.ui.util.GtnSmallHashMap;
import com.vaadin.v7.data.Container;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.asi.container.ExtTreeContainer;

/**
 *
 * @author sibi
 */
public class NMSalesProjectionTableLogic extends PageTreeTableLogic {

    ProjectionSelectionDTO projSelDTO = new ProjectionSelectionDTO();
    int levelNo;
    String hierarchyNo;
    boolean firstGenerated = false;
    public static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(NMSalesProjectionTableLogic.class);
    ProjectionSelectionDTO initialProjSelDTO = new ProjectionSelectionDTO();
    Map<String, SalesRowDto> loadDataMap = new HashMap<>();
    SalesProjectionTree tree = null;

    /**
     * Loads the table in sales projection based on start and end index.
     *
     * @param start
     * @param offset
     * @return
     */
    @Override
    public GtnSmallHashMap loadData(int start, int offset) {
        LOGGER.info("loadData initiated with firstGenerated=" + firstGenerated + " and start=" + start + " and offset=" + offset);
        GtnSmallHashMap map = new GtnSmallHashMap();
        return map;
    }

    /**
     * Returns the available number of records based on the generate criteria.
     *
     * @return
     */
    @Override
    public int getCount() {
        LOGGER.info("getCount initiated with firstGenerated=" + firstGenerated);
        int count = 0;
        if (firstGenerated) {
            tree = new SalesProjectionTree();
            int forecastlevel = Integer.valueOf(projSelDTO.getHierarchyIndicator().equals("C") ? projSelDTO.getSessionDTO().getCustomerLevelNumber() : projSelDTO.getSessionDTO().getProductLevelNumber());
            projSelDTO.setTreeLevelNo(forecastlevel);
            projSelDTO.setHierarchyNo(null);
            tree.buildtree(projSelDTO);
            LevelMap levelMap = new LevelMap(tree.getApex().getNoOfChilds(), getColumnIdToFilterMap());
            addlevelMap("", levelMap);
        }
        firstGenerated = false;
        if (tree != null) {
            count = tree.getHierarchy(tree.getApex(), getLastParentTreeLevel()).getNoOfChilds();
        }
        return count;
    }

    /**
     *
     * @param object
     * @param datasource
     * @return
     */
    @Override
    public Object configureContainer(Object object, Container datasource) {
        SalesRowDto dto = (SalesRowDto) object;
        ((ExtTreeContainer<SalesRowDto>) datasource).addBean(dto);
        if (dto.getParent() == 1) {
            ((ExtTreeContainer<SalesRowDto>) datasource).setChildrenAllowed(dto, true);
        } else {
            ((ExtTreeContainer<SalesRowDto>) datasource).setChildrenAllowed(dto, false);
        }
        return dto;
    }

    public void groupChange() {
        clearAll();
        setCurrentPage(1);
    }

    public void setProjectionResultsData(ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("setProjectionResultsData is called");
        setBulkDataLoadAllowed(true);
        setFullBulkDataLoadAllowed(true);
        this.projSelDTO = projSelDTO;
        clearAll();
        initialProjSelDTO.setCustomerLevelNo(projSelDTO.getCustomerLevelNo());
        initialProjSelDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        initialProjSelDTO.setProductLevelNo(projSelDTO.getProductLevelNo());
        initialProjSelDTO.setGroup(projSelDTO.getGroup());
        initialProjSelDTO.setTabName(projSelDTO.getTabName());
        firstGenerated = true;
        setCurrentPage(1);
    }

    public LevelMap getLevelMap(int levelValue, int count, int levelIndex) {
        int page = getPageForItem(count + levelIndex + levelValue);
        int index = getItemIndex(count + levelIndex + levelValue);
        int start = getStartIndex(count + levelIndex, index);
        int end = getPageLength();
        LevelMap levelMap = new LevelMap(start, end, page, getPageLength(), index, getColumnIdToFilterMap());
        return levelMap;
    }

    public int getPageForItem(int pos) {
        int curPage = ((pos - 2) / getPageLength()) + 1;
        return curPage;
    }

    public int getItemIndex(int pos) {
        int index = (pos - 2) % getPageLength();
        return index;
    }

    public int getStartIndex(int count, int index) {
        int start = count - index;
        return start;
    }

    @Override
    protected void createCurrentPageStart() {
        setCurrentPageProgress(true);
        setRefresh(Boolean.FALSE);
        for (Map.Entry<String, SalesRowDto> loadData : loadDataMap.entrySet()) {
            addCurrentPageData(loadData.getKey(), loadData.getValue());
        }
        loadDataMap = new HashMap<>();
    }

    @Override
    protected void createCurrentPageEnd() {
        setCurrentPageProgress(false);
        setRefresh(Boolean.TRUE);
    }

    @Override
    protected void expandCollapseStart(boolean isExpand) {
        setExpandCollapseProgress(true);
    }

    @Override
    protected void expandCollapseEnd(boolean isExpand) {
        setExpandCollapseProgress(false);
    }

    public Object getExpandedTreeValues(String treeLevel) {
        return getExpandedTreeValue(treeLevel);
    }

    public void forRefresh(Set<String> hierachyNos) {
        addExpandedTreeDataFetchable(hierachyNos);
    }

    @Override
    public GtnSmallHashMap loadBulkData(GtnSmallHashMap bulkDataMap) {
        Set<TreeNode> neededNodeSet = new TreeSet<>();
        for (int i = 0; i < bulkDataMap.size(); i++) {
            String treeLevel = (String) bulkDataMap.getIndex(i).getKey();
            SalesBaseNode parentNode = (SalesBaseNode) tree.getHierarchy(tree.getApex(), treeLevel);
            neededNodeSet.add(parentNode);
            getCurrentPageData().remove(treeLevel);
        }
        if (projSelDTO.isIsCustomHierarchy()) {
            loadDataForBulkNode(neededNodeSet);
        } else {
            loadDataForCP(neededNodeSet);
        }
        createCurrentPageStart();
        return new GtnSmallHashMap();
    }

    protected void recursivelyLoadExpandData(int expandLevelNo) {
        findRequiredHierarchies(tree.getApex(), expandLevelNo);

    }

    private void findRequiredHierarchies(SalesBaseNode apex, int expandLevelNo) {
        Set<TreeNode> hierarchySet = getHierarchyNodes(apex, expandLevelNo);
        int i = 0;
        int initial = hierarchySet.size() % getItemsPerPage() == 0 ? (hierarchySet.size() / getItemsPerPage()) - 1 : hierarchySet.size() / getItemsPerPage();
        int start = (initial) == 0 ? 0 : (initial) * getItemsPerPage();
        int offset = start + getItemsPerPage();
        Set<TreeNode> paginatedSalesNode = new TreeSet<>();
        for (TreeNode node : hierarchySet) {
            if (!paginatedSalesNode.contains(node)) {
                if (i >= start && i <= offset) {
                    SalesBaseNode parentNode = (SalesBaseNode) node.getParentNode();
                    if (!paginatedSalesNode.add(node)) {
                    }
                    while (!parentNode.isApex()) {
                        paginatedSalesNode.add(parentNode);
                        parentNode = (SalesBaseNode) parentNode.getParentNode();
                    }
                }
                i++;
            }
        }
        if (projSelDTO.isIsCustomHierarchy()) {
            loadDataForBulkNode(paginatedSalesNode);
        } else {
            loadDataForCP(paginatedSalesNode);
        }
    }

    private void loadDataForCP(Set<TreeNode> node) {
        if (!node.isEmpty()) {
            List<SalesRowDto> dto = getSalesDataForCP(node);
            int i = 0;
            GtnSmallHashMap expandedMap = getExpandedTreeList();
            for (TreeNode salesNode : node) {
                SalesRowDto currentData = dto.get(i++);
                if (expandedMap.containsKey(salesNode.getHierarchyForTable())) {
                    removeExpandedTreeDataFetchable(salesNode.getHierarchyForTable());
                    addExpandedTreeList(salesNode.getHierarchyForTable(), currentData);
                } else {
                    loadDataMap.put(salesNode.getHierarchyForTable(), currentData);
                }
            }
        }
    }

    private List<SalesRowDto> getSalesDataForCP(Set<TreeNode> node) {
        SalesLogic logic = new SalesLogic();
        return logic.getSalesResultsForExpand(projSelDTO, node);
    }

    private void loadDataForBulkNode(Set<TreeNode> nodeSet) {

        try {
            List<SalesRowDto> dto = getSalesDataForCustom(nodeSet);
            int i = 0;
            GtnSmallHashMap expandedMap = getExpandedTreeList();
            for (TreeNode salesNode : nodeSet) {
                SalesRowDto currentData = dto.get(i++);
                if (expandedMap.containsKey(salesNode.getHierarchyForTable())) {
                    removeExpandedTreeDataFetchable(salesNode.getHierarchyForTable());
                    addExpandedTreeList(salesNode.getHierarchyForTable(), currentData);
                } else {
                    loadDataMap.put(salesNode.getHierarchyForTable(), currentData);
                }

            }

        } catch (Exception ex) {
            Logger.getLogger(NMSalesProjectionTableLogic.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<SalesRowDto> getSalesDataForCustom(Set<TreeNode> nodeSet) {
        CommonLogic logic = new CommonLogic();
        String insertQuery = logic.insertAvailableHierarchyNoForCustomExpand(nodeSet,projSelDTO);
        SalesLogic saleLogic = new SalesLogic();
        return saleLogic.getSalesResults(projSelDTO, 0, nodeSet.size(), insertQuery);
    }

    private Set<TreeNode> getHierarchyNodes(TreeNode apex, int expandLevelNo) {
        Set<TreeNode> hierarchySet = new LinkedHashSet<>();
        if (apex.getLevel() <= expandLevelNo + 1) {
            if (!apex.isApex()) {
                hierarchySet.add((SalesBaseNode) apex);
                if (apex.getLevel() <= expandLevelNo) {
                    LevelMap levelMap = new LevelMap(apex.getNoOfChilds(), getColumnIdToFilterMap());
                    addlevelMap(apex.getHierarchyForTable(), levelMap);
                    addExpandedTreeList(apex.getHierarchyForTable(), new SalesRowDto());
                    addExpandedTreeDataFetchable(apex.getHierarchyForTable());
                }
            }
            for (TreeNode salesNode : apex.getAllChildHierarchies()) {
                hierarchySet.addAll(getHierarchyNodes(salesNode, expandLevelNo));
            }
        }

        return hierarchySet;
    }

    public void loadExpandData(int levelNo) {
        try {
            recursivelyLoadExpandData(levelNo);
            setRecordCount(getCalculatedTotalRecordCount());
            setCurrentPage(getTotalAmountOfPages());
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

}
