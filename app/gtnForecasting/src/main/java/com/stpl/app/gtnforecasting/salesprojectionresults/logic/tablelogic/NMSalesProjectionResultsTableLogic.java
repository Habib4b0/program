/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.salesprojectionresults.logic.tablelogic;

import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.dto.SalesProjectionResultsDTO;
import com.stpl.app.gtnforecasting.dto.SalesRowDto;
import com.stpl.app.gtnforecasting.salesprojectionresults.logic.NMSalesProjectionResultsLogic;
import com.stpl.app.gtnforecasting.salesprojectionresults.tree.SalesProjectionResultsTree;
import com.stpl.app.gtnforecasting.salesprojectionresults.tree.SalesProjectionResultsVariableTree;
import com.stpl.app.gtnforecasting.salesprojectionresults.tree.node.SalesPRBaseNode;
import com.stpl.app.gtnforecasting.tree.node.TreeNode;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.utils.Constants.LabelConstants.VARIABLE;
import com.vaadin.data.Container;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtTreeContainer;
import com.stpl.ifs.ui.extfilteringtable.PageTreeTableLogic;
import com.stpl.ifs.ui.util.GtnSmallHashMap;
import com.vaadin.data.util.filter.Compare;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Lokeshwari
 */
public class NMSalesProjectionResultsTableLogic extends PageTreeTableLogic {

    ProjectionSelectionDTO projSelDTO = new ProjectionSelectionDTO();
    public NMSalesProjectionResultsLogic sprLogic = new NMSalesProjectionResultsLogic();
    boolean firstGenerated = false;
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(NMSalesProjectionResultsTableLogic.class);
    private SalesProjectionResultsTree tree;
    private Map<String, SalesProjectionResultsDTO> loadDataMap = new HashMap<>();
    private String pivotValue = StringUtils.EMPTY;

    @Override
    public GtnSmallHashMap loadData(int start, int offset) {
        LOGGER.debug("loadData initiated with firstGenerated=" + firstGenerated + " and start=" + start + " and offset=" + offset);
        GtnSmallHashMap map = new GtnSmallHashMap();
        try {
            if (firstGenerated && offset > 0) {
                List<SalesProjectionResultsDTO> list = sprLogic.getConfiguredSalesProjectionResults(getLastParent(), start, offset, projSelDTO);
                int i = start;
                for (SalesProjectionResultsDTO dto : list) {
                    map.put(i, dto);
                    i++;
                }
                projSelDTO.clearNonFetchableIndex();
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("loadData ends with record size=" + map.size());
        return map;
    }

    @Override
    public int getCount() {
        int count = 0;
        try {
            if (firstGenerated) {
                if (tree == null || !pivotValue.equals(projSelDTO.getPivotView())) {
                    pivotValue = projSelDTO.getPivotView();
                    if (projSelDTO.getPivotView().equals(VARIABLE.getConstant())) {
                        tree = new SalesProjectionResultsVariableTree();
                    } else {
                        tree = new SalesProjectionResultsTree();
                    }
                }
                firstGenerated = false;
            }
            if (projSelDTO.getSessionDTO() == null) {
                return 0;
            }
            int forecastlevel = Integer.valueOf("C".equals(projSelDTO.getHierarchyIndicator()) ? projSelDTO.getSessionDTO().getCustomerLevelNumber() : projSelDTO.getSessionDTO().getProductLevelNumber());
            projSelDTO.setTreeLevelNo(forecastlevel);
            tree.buildTree(projSelDTO);
            if (getLastParentTreeLevel().isEmpty()) {
                count = tree.getApex().getNoOfChilds();
            } else if (tree != null && tree.getApex() != null) {
                count = tree.getHierarchy(tree.getApex(), getLastParentTreeLevel()).getNoOfChilds();
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("count= " + count);
        return count;
    }

    @Override
    public Object configureContainer(Object object, Container datasource) {
        SalesProjectionResultsDTO dto = (SalesProjectionResultsDTO) object;
        if (dto != null) {
            ((ExtTreeContainer<SalesProjectionResultsDTO>) datasource).addBean(dto);
            if (dto.getParent() == 1) {
                ((ExtTreeContainer<SalesProjectionResultsDTO>) datasource).setChildrenAllowed(dto, true);
            } else {
                ((ExtTreeContainer<SalesProjectionResultsDTO>) datasource).setChildrenAllowed(dto, false);
            }
        }
        return dto;
    }

    public void setProjectionResultsData(ProjectionSelectionDTO projSelDTO) {
        setBulkDataLoadAllowed(true);
        setFullBulkDataLoadAllowed(true);
        this.projSelDTO = projSelDTO;
        sprLogic.projectionTotalList = new ArrayList<>();
        firstGenerated = true;
        getLevelMapList().clear();
        clearAll();
        setCurrentPage(1);
    }

    @Override
    public GtnSmallHashMap loadBulkData(GtnSmallHashMap bulkDataMap) {
        Set<TreeNode> neededNodeSet = new TreeSet<>();
        for (int i = 0; i < bulkDataMap.size(); i++) {
            String treeLevel = (String) bulkDataMap.getIndex(i).getKey();
            TreeNode parentNode = tree.getHierarchy(tree.getApex(), treeLevel);
            neededNodeSet.add(parentNode);
            getCurrentPageData().remove(treeLevel);
        }
        loadData(neededNodeSet);
        createCurrentPageStart();
        return new GtnSmallHashMap();
    }

    protected void recursivelyLoadExpandData(int expandLevelNo) {
        getLevelMapList().clear();
        getCount();
        clearAll();
        findRequiredHierarchies(tree.getApex(), expandLevelNo);
    }

    private void findRequiredHierarchies(TreeNode apex, int expandLevelNo) {
        Set<TreeNode> hierarchySet = getHierarchyNodes(apex, expandLevelNo);
        int i = 0;
        int initial = hierarchySet.size() % getItemsPerPage() == 0 ? (hierarchySet.size() / getItemsPerPage()) - 1 : hierarchySet.size() / getItemsPerPage();
        int start = (initial) == 0 ? 0 : (initial) * getItemsPerPage();
        int offset = start + getItemsPerPage();
        Set<TreeNode> paginatedSalesNode = new TreeSet<>();
        for (TreeNode node : hierarchySet) {
            if (!paginatedSalesNode.contains(node)) {
                if (i >= start && i <= offset) {
                    TreeNode parentNode = node.getParentNode();
                    if (!paginatedSalesNode.add(node)) {
                    }
                    while (!parentNode.isApex()) {
                        paginatedSalesNode.add(parentNode);
                        parentNode = parentNode.getParentNode();
                    }
                }
                i++;
            }
        }
        loadData(paginatedSalesNode);
    }

    private Set<TreeNode> getHierarchyNodes(TreeNode apex, int expandLevelNo) {
        Set<TreeNode> hierarchySet = new LinkedHashSet<>();
        if (apex.getLevel() <= expandLevelNo + 1) {
            if (!apex.isApex()) {
                if (((SalesPRBaseNode) apex).isStatic()) {
                    if (apex.getParentNode().getLevel() <= expandLevelNo) {
                        hierarchySet.add(apex);
                        if (apex.getLevel() <= expandLevelNo) {
                            LevelMap levelMap = new LevelMap(apex.getNoOfChilds(), getColumnIdToFilterMap());
                            addlevelMap(apex.getHierarchyForTable(), levelMap);
                            if (apex.getNoOfChilds() > 0) {
                                addExpandedTreeList(apex.getHierarchyForTable(), new SalesRowDto());
                                addExpandedTreeDataFetchable(apex.getHierarchyForTable());
                            }
                        }
                    }
                } else {
                    hierarchySet.add(apex);
                    if (apex.getLevel() <= expandLevelNo) {
                        LevelMap levelMap = new LevelMap(apex.getNoOfChilds(), getColumnIdToFilterMap());
                        addlevelMap(apex.getHierarchyForTable(), levelMap);
                        if (apex.getNoOfChilds() > 0) {
                            addExpandedTreeList(apex.getHierarchyForTable(), new SalesRowDto());
                            addExpandedTreeDataFetchable(apex.getHierarchyForTable());
                        }
                    }
                }
            }
            for (TreeNode salesNode : apex.getAllChildHierarchies()) {
                hierarchySet.addAll(getHierarchyNodes(salesNode, expandLevelNo));
            }
        }

        return hierarchySet;
    }

    public Set<TreeNode> getAllHierarchyNodes(TreeNode apex) {
        Set<TreeNode> hierarchySet = new LinkedHashSet<>();
        if (!apex.isApex()) {
            hierarchySet.add(apex);
        }
        for (TreeNode salesNode : apex.getAllChildHierarchies()) {
            hierarchySet.addAll(getAllHierarchyNodes(salesNode));
        }

        return hierarchySet;
    }

    public void loadExpandData(int levelNo) {
        recursivelyLoadExpandData(levelNo);
        setRecordCount(getCalculatedTotalRecordCount());
        setCurrentPage(getTotalAmountOfPages());
    }

    @Override
    protected void createCurrentPageStart() {
        setCurrentPageProgress(true);
        setRefresh(Boolean.FALSE);
        for (Map.Entry<String, SalesProjectionResultsDTO> loadData : loadDataMap.entrySet()) {
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

    @Override
    public void setColumnIdToFilterValue(Object prop, Object value) {
        super.setColumnIdToFilterValue(prop, value);
    }

    private void loadData(Set<TreeNode> paginatedSalesNode) {
        if (paginatedSalesNode.isEmpty()) {
            return;
        }
        GtnSmallHashMap expandedMap = getExpandedTreeList();
        Set<TreeNode> dbLoadedSalesPRNode = new TreeSet<>();
        for (TreeNode treeNode : paginatedSalesNode) {
            SalesPRBaseNode cpNode = (SalesPRBaseNode) treeNode;
            if (cpNode.isStatic()) {
                if (cpNode.getHierachyNo().contains("Sales Group")) {
                    SalesProjectionResultsDTO prData = new SalesProjectionResultsDTO();
                    String salesGroup = cpNode.getHierachyNo();
                    prData.setLevelValue(salesGroup);
                    prData.setParent(cpNode.getNoOfChilds() > 0 ? 1 : 0);
                    loadDataMap.put(cpNode.getHierarchyForTable(), prData);
                } else if (cpNode.getParentNode().isApex()) {
                    loadDataMap.put(cpNode.getHierarchyForTable(), tree.getStaticData(cpNode.getPositiontoParent() - 1));
                } else {
                    dbLoadedSalesPRNode.add(cpNode.getParentNode());
                }
            } else if (expandedMap.containsKey(cpNode.getHierarchyForTable())) {
                SalesProjectionResultsDTO prData = new SalesProjectionResultsDTO();
                String levelDescription = projSelDTO.getSessionDTO().getLevelValueDiscription(cpNode.getHierachyNo(), cpNode.getHierarchyIndicator() == null ? projSelDTO.getHierarchyIndicator() : cpNode.getHierarchyIndicator());
                prData.setLevelValue(levelDescription);
                loadDataMap.put(cpNode.getHierarchyForTable(), prData);
                addExpandedTreeList(cpNode.getHierarchyForTable(), prData);
                loadDataMap.put(cpNode.getHierarchyForTable(), prData);
            } else {
                SalesProjectionResultsDTO prData = new SalesProjectionResultsDTO();
                String levelDescription = projSelDTO.getSessionDTO().getLevelValueDiscription(cpNode.getHierachyNo(), cpNode.getHierarchyIndicator() == null ? projSelDTO.getHierarchyIndicator() : cpNode.getHierarchyIndicator());
                prData.setLevelValue(levelDescription);
                prData.setParent(cpNode.getNoOfChilds() > 0 ? 1 : 0);
                loadDataMap.put(cpNode.getHierarchyForTable(), prData);
            }
        }
        if (dbLoadedSalesPRNode.isEmpty()) {
            return;
        }
        List<SalesProjectionResultsDTO[]> dto = null;
        if (projSelDTO.isIsCustomHierarchy()) {
            dto = getSalesPRDataForCustom(dbLoadedSalesPRNode);
        } else {
            dto = getSalesPRDataForCP(dbLoadedSalesPRNode);
        }
        tree.loadStaticLevelRow(dto, dbLoadedSalesPRNode, loadDataMap);
    }

    private List<SalesProjectionResultsDTO[]> getSalesPRDataForCP(Set<TreeNode> dbLoadedSalesPRNode) {
        NMSalesProjectionResultsLogic prLogic = new NMSalesProjectionResultsLogic();
        return prLogic.getContractSalesAndUnitsMultiple(dbLoadedSalesPRNode, projSelDTO);
    }

    private List<SalesProjectionResultsDTO[]> getSalesPRDataForCustom(Set<TreeNode> dbLoadedSalesPRNode) {
        NMSalesProjectionResultsLogic prLogic = new NMSalesProjectionResultsLogic();
        return prLogic.getContractSalesAndUnitsMultipleCustom(dbLoadedSalesPRNode, projSelDTO);
    }

    @Override
    public void handleFilterChange() {
        if (!Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
            projSelDTO.setGroupFilter("");

            if (this.getFilters() != null) {
                for (Container.Filter filter : this.getFilters()) {
                    Compare stringFilter = (Compare) filter;
                    Compare.Operation operation = stringFilter.getOperation();
                    if (Compare.Operation.EQUAL.toString().equals(operation.name())) {
                        projSelDTO.setGroupFilter(String.valueOf(stringFilter.getValue()).replace(Constant.SALESWITHMINUS, StringUtils.EMPTY));
                    }
                }
            }

            getLevelMapList().clear();
            clearAll();
            super.handleFilterChange();
        }
    }

    /**
     * Excel Method to export
     *
     * @param excelResultBean
     */
    public void loadExcelData(ExtTreeContainer<SalesProjectionResultsDTO> excelResultBean) {
        loadData(getAllHierarchyNodes(tree.getApex()));
        loadExcelDataByLevel(tree.getApex(), excelResultBean);
        loadDataMap.clear();
    }

    private void loadExcelDataByLevel(TreeNode apex, ExtTreeContainer<SalesProjectionResultsDTO> excelResultBean) {
        if (!apex.isApex()) {
            SalesProjectionResultsDTO currentData = loadDataMap.get(apex.getHierarchyForTable());
            excelResultBean.addBean(loadDataMap.get(apex.getHierarchyForTable()));
            if (apex.getNoOfChilds() > 0) {
                excelResultBean.setChildrenAllowed(currentData, true);
            } else {
                excelResultBean.setChildrenAllowed(currentData, false);
            }
            if (!apex.getParentNode().isApex()) {
                SalesProjectionResultsDTO parentData = loadDataMap.get(apex.getParentNode().getHierarchyForTable());
                excelResultBean.setParent(currentData, parentData);
            }
        }
        for (TreeNode salesNode : apex.getAllChildHierarchies()) {
            loadExcelDataByLevel(salesNode, excelResultBean);
        }

    }
}
