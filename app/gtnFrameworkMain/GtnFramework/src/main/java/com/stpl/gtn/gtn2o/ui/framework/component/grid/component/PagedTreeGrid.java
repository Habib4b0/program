package com.stpl.gtn.gtn2o.ui.framework.component.grid.component;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.bean.DataSet;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.service.FetchData;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.utils.GridUtils;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.utils.HeaderUtils;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableConfig;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.vaadin.data.TreeData;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.event.ExpandEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeGrid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class PagedTreeGrid {
    /**
     * Logger
     */
    public static final GtnWSLogger gtnlogger = GtnWSLogger.getGTNLogger(PagedTreeGrid.class);
    public static final List<String> INPUT = Arrays.asList("levelNumber", "hierarchyNo");
    private GtnUIFrameworkPagedTreeTableConfig tableConfig;
    int count;
    private int pageLength = 10;
    private int pageNumber = 0;
    private int columnPageNumber = 0;
    private DataSet dataSet;
    TreeGrid<GtnWsRecordBean> grid;
    Set<String> tableColumns = new HashSet<>();
    GtnWsRecordBean lastRow;
    TreeDataProvider<GtnWsRecordBean> treeDataProvider;
    private final String EMPTY = GtnFrameworkCommonStringConstants.STRING_EMPTY;
    private final Set<GtnWsRecordBean> expandedItemIds = new HashSet<>();
    private final Set<Integer> expandedRowIds = new HashSet<>();
    HashMap<Integer, GtnWsRecordBean> lastExpandedItemHierarchy = new HashMap<>(7);
    GtnWsRecordBean lastExpandedItem;
    HorizontalLayout controlLayout = new HorizontalLayout();
    private TextField pageNoField = new TextField();
    private TextField columnPageNoField = new TextField();
    private ComboBox itemsPerPage = new ComboBox();
    private ComboBox columnsPerPage = new ComboBox();

    private TextField totalColumns = new TextField();
    private TextField pageCountLabel;
    GtnUIFrameworkComponentConfig componentConfig;
    int expandTempIndex = 0;
    int expandFinalIndex = 0;
    boolean removeExcessItems = false;
    int excessItemsStartIndex = 0;
    List<GtnWsRecordBean> itemsTobeRemoved = new ArrayList<>();
    public boolean shiftLeftSingeHeader;
    GtnWsRecordBean tempBean =null;
    int fetched = 0;
    boolean levelExpandOn=false;
    int   levelExpandNo=1;

    public PagedTreeGrid(GtnUIFrameworkPagedTreeTableConfig tableConfig,
            GtnUIFrameworkComponentConfig componentConfig) {
        try {
            this.tableConfig = tableConfig;
            this.componentConfig = componentConfig;
            gtnlogger.info("count>>>" + count);
            grid = new TreeGrid<>();
            initalizeColumnController();
           
        } catch (Exception ex) {
            gtnlogger.error(ex.getMessage(), ex);
        }
    }

    public void resetGridToInitialState() {
        setCount(getTotalCount());
        int offset = pageNumber * pageLength;
        dataSet = loadData(offset, pageLength, tableConfig.getLevelNo(), EMPTY);
        @SuppressWarnings("unchecked")
        TreeData<GtnWsRecordBean> treeData = treeDataProvider.getTreeData();
        treeData.clear();
        treeData.addItems(null, dataSet.getRows());
        addExpandIcon(treeData, dataSet.getRows());
        treeDataProvider.refreshAll();
    }

    public void initializeGrid() {
        pageNoField.setValue(1 + EMPTY);
        pageLength=tableConfig.getPageLength();
        clearTempVariables();
        clearExpandTempVariables();
        setCount(getTotalCount());
        expandedItemIds.clear();
        expandedRowIds.clear();
        if (count > 0) {
            dataSet = loadData((pageNumber * pageLength), pageLength, tableConfig.getLevelNo(), EMPTY);
        }

        TreeData<GtnWsRecordBean> data = new TreeData<>();
        if (dataSet != null) {
            data.addItems(null, dataSet.getRows());
            addExpandIcon(data, dataSet.getRows());
        }

        treeDataProvider = new TreeDataProvider<>(data);
        grid.setDataProvider(treeDataProvider);

        addExpandListener();
        addCollapseListener();
    }
    public void  expandAll(int levelNo){
       levelExpandOn=true;
       levelExpandNo=levelNo;
       paintCurrentPage();
    }
    public void  collapseAll(int levelNo){
       levelExpandOn=false;
       levelExpandNo=levelNo;
       paintCurrentPage();
    }

    private void addExpandListener() {
        grid.addExpandListener(new ExpandEvent.ExpandListener<GtnWsRecordBean>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void itemExpand(ExpandEvent<GtnWsRecordBean> event) {
                try {
                    if (event.isUserOriginated()) {
                        TreeData<GtnWsRecordBean> treeData = treeDataProvider.getTreeData();
                        GtnWsRecordBean parent = event.getExpandedItem();
                        int childCount = 0;
                        expandRow(parent, childCount, treeData, true);
                    }
                } catch (Exception ex) {
                    gtnlogger.error(ex.getMessage(), ex);
                }
            }

        });
    }

    public void addCollapseListener() {
        grid.addCollapseListener(event -> {
            GtnWsRecordBean parent = event.getCollapsedItem();
            int childCount = GridUtils.getChildCount(parent);
            expandedItemIds.remove(parent);
            expandedRowIds.remove(GridUtils.getNodeIndex(parent));
            
            Set<GtnWsRecordBean> toBeRemoved = expandedItemIds.stream().filter(s -> GridUtils.getHierarchyNo(s).startsWith(GridUtils.getHierarchyNo(parent))).collect(Collectors.toSet());
            for (GtnWsRecordBean bean : toBeRemoved) {
                expandedItemIds.remove(bean);
                expandedRowIds.remove(GridUtils.getNodeIndex(bean));
                childCount+=GridUtils.getChildCount(bean);
            }
            setCount(count-childCount);
            removeAlreadyExpanded(GridUtils.getTableIndex(parent));
            
            //*** Collapse Logic
            paintCurrentPage();
        });
    }

    private void addExpandIcon(TreeData<GtnWsRecordBean> data, List<GtnWsRecordBean> rows) {
        gtnlogger.info("addExpandIcon");
        rows.stream().map((parent) -> {
            if (GridUtils.getLevelNo(parent) != 0 && GridUtils.getChildCount(parent) > 0) {
                data.addItem(parent, GridUtils.getEmptyRow(this));
            }
            return parent;
        }).forEach((GtnWsRecordBean parent) -> {
            int rowNo = GridUtils.getNodeIndex(parent);
            if (expandedRowIds.contains(rowNo)) {
                int childCount = 0;
                expandRow(parent, childCount, data, false);
                if (rowNo % pageLength != 0) {
                    grid.expand(parent);
                }
            }
        });
    }

    public void expandRow(GtnWsRecordBean parent, int childCount, TreeData<GtnWsRecordBean> treeData,
            boolean moveToNextPage) {

        if (parent != null && GridUtils.getLevelNo(parent) != 0 && GridUtils.hasChildren(parent)) {
            childCount = GridUtils.getChildCount(parent);
            for (int i = 0; i < treeData.getChildren(parent).size(); i++) {
                treeData.removeItem(treeData.getChildren(parent).get(i));
            }
            setCount(count + childCount);
            int nodeIndex = GridUtils.getNodeIndex(parent);
            List<GtnWsRecordBean> bean = treeDataProvider.getTreeData().getRootItems();
            removeExcessItems(bean, treeDataProvider.getTreeData(), parent, childCount);
            int tableIndex = expandFinalIndex + (pageLength * pageNumber);
            parent.addAdditionalProperties(5, tableIndex);
          
            gtnlogger.info("index + " + expandFinalIndex);
            gtnlogger.info("rowNo" + nodeIndex);
            int limit = pageLength - expandFinalIndex;

            if (limit == 0) {
                if (!moveToNextPage) {
                    treeData.addItem(parent, GridUtils.getEmptyRow(this));
                    return;
                }
                setPageNoFieldValue(++pageNumber);
                addExpandedItems(parent, nodeIndex, childCount, tableIndex);
                paintCurrentPage();
                clearExpandTempVariables();
                return;
            }
            itemsTobeRemoved.stream().filter((item) -> (treeDataProvider.getTreeData().contains(item))).forEach((item) -> {
                treeDataProvider.getTreeData().removeItem(item);
            });

            limit = childCount < Math.abs(limit) ? childCount : limit;
            List<GtnWsRecordBean> childRows = fetchChildren(0, limit, parent);

            refreshDataProvider(treeData, parent, childRows);
            addExpandedItems(parent, nodeIndex, childCount, tableIndex);
            clearExpandTempVariables();
        }
    }

    public void addExpandedItems(GtnWsRecordBean parent, int nodeIndex, int childCount, int tableIndex) {

        moveAlreadyExpanded(tableIndex, childCount);
        expandedItemIds.add(parent);
        expandedRowIds.add(nodeIndex);
       
    }
    public void removeAlreadyExpanded(int tableIndex) {
        //remove   already expanded Items
        expandedItemIds.stream().filter((item) -> GridUtils.getTableIndex(item) > tableIndex)
                .forEach((item) -> {
                    item.addAdditionalProperties(5, tableIndex + 1);
                });
    }

    public void moveAlreadyExpanded(int tableIndex, int childCount) {
        //Move already expanded Items
        expandedItemIds.stream().filter((item) -> GridUtils.getTableIndex(item) >= tableIndex)
                .forEach((item) -> {
                    item.addAdditionalProperties(5, GridUtils.getTableIndex(item) + childCount);
                });
    }

    public void clearExpandTempVariables() {
        expandTempIndex = 0;
        expandFinalIndex = 0;
        itemsTobeRemoved.clear();
        removeExcessItems = false;
    }

    public void removeExcessItems(List<GtnWsRecordBean> bean, TreeData<GtnWsRecordBean> treeData, GtnWsRecordBean itemToFind, int childCount) {
        bean.stream().map((item) -> {
            if (!removeExcessItems) {
                expandTempIndex++;
                if (itemToFind.equals(item)) {
                    removeExcessItems = true;
                    expandFinalIndex = expandTempIndex;//2
                    excessItemsStartIndex = (pageLength * (pageNumber + 1)) - expandTempIndex;//3
                    excessItemsStartIndex = excessItemsStartIndex > childCount ? pageLength - childCount + 1 : expandFinalIndex + 1;//3

                }
            } else if (++expandTempIndex >= excessItemsStartIndex && excessItemsStartIndex++ <= pageLength) {
                itemsTobeRemoved.add(item);
            }
            return item;
        }).forEach((item) -> {
            traverseChildNodes(item, treeData, itemToFind, childCount);
        });
    }

    public void traverseChildNodes(GtnWsRecordBean item, TreeData<GtnWsRecordBean> treeData, GtnWsRecordBean itemToFind, int childCount) {
        if (GridUtils.getChildCount(item) > 0 && isExpanded(item)) {
            removeExcessItems(treeData.getChildren(item), treeData, itemToFind, childCount);
        }
    }

    private void refreshDataProvider(TreeData<GtnWsRecordBean> treeData, GtnWsRecordBean parent,
            List<GtnWsRecordBean> childRows) {
        treeData.addItems(treeData.contains(parent) ? parent : null, childRows);
        addExpandIcon(treeData, childRows);
        treeDataProvider.refreshAll();
    }

    private int getTotalCount() {

        if (tableConfig.getCountUrl() != null) {
            GtnWsSearchRequest request = GridUtils.getWsRequest(0, pageLength, true, INPUT, Arrays.asList(1, EMPTY), tableConfig);
            List<GtnWsRecordBean> result = FetchData.callWebService(tableConfig, componentConfig.getModuleName(),
                    request);

            return result == null ? 0 : result.size();
        }
        return 0;

    }
// May be used later ,currently fetching childcount from additional Properties
    private int getChildCount(GtnWsRecordBean parent) {
        int childCount = 0;
        if (tableConfig.getCountUrl() != null) {
            GtnWsSearchRequest request = GridUtils.getWsRequest(0, pageLength, true, INPUT, Arrays.asList(GridUtils.getLevelNo(parent) + 1, GridUtils.getHierarchyNo(parent)), tableConfig);
            List<GtnWsRecordBean> result = FetchData.callWebService(tableConfig, componentConfig.getModuleName(),
                    request);
            childCount = result == null ? 0 : result.size();
            parent.addAdditionalProperties(0, childCount);
        }
        return childCount;
    }

    private List<GtnWsRecordBean> fetchChildren(int start, int limit, GtnWsRecordBean parent) {

        GtnWsSearchRequest request = GridUtils.getWsRequest(start, limit, true, INPUT, Arrays.asList(GridUtils.getLevelNo(parent) + 1, GridUtils.getHierarchyNo(parent)), tableConfig);
        return FetchData.callWebService(tableConfig, componentConfig.getModuleName(), request);
    }

    private DataSet loadData(int start, int limit, int levelNo, String hierarchyNo) {
        List<GtnWsRecordBean> updatedrows = new ArrayList<>();
        if (count != 0) {
            GtnWsSearchRequest request = GridUtils.getWsRequest(start, limit, true, INPUT, Arrays.asList(levelNo, hierarchyNo), tableConfig);
            updatedrows = FetchData.callWebService(tableConfig, componentConfig.getModuleName(),
                    request);

        }
        return new DataSet(tableColumns.stream().collect(Collectors.toList()), updatedrows);

    }

    public void paintCurrentPage() {
        if(pageNumber==0){
            paintFirstPage();
            return;
        }
        int currentOffset = pageNumber * pageLength;
        if (expandedItemIds.isEmpty()) {
            dataSet = loadData(currentOffset + 1, currentOffset + pageLength, tableConfig.getLevelNo(), EMPTY);
        } else {
            List<GtnWsRecordBean> childRows = null;
            findLastExpandedHierarchy(currentOffset);
            int lastRowno = GridUtils.getTableIndex(lastExpandedItem);
            int childCount = GridUtils.getChildCount(lastExpandedItem);
            int offset = pageNumber == 0 ? 0 : (pageLength * pageNumber) - lastRowno;
            if ((lastRowno + childCount) > currentOffset) {

                int itemToFetch = childCount - offset;
                int limit = itemToFetch > pageLength ? pageLength : itemToFetch;
                childRows = fetchChildren(offset, limit, lastExpandedItem);
            }

            TreeData<GtnWsRecordBean> treeData = grid.getTreeData();
            treeData.clear();
            if (childRows == null || childRows.size() < pageLength) {
                int itemsToFetch = pageLength;

                if (childRows != null) {
                    for (GtnWsRecordBean child : childRows) {
                        treeData.addItems(null, child);
                        if (GridUtils.hasChildren(child)) {
                            treeData.addItem(child, GridUtils.getEmptyRow(this));
                        }
                    }
                    itemsToFetch -= childRows.size();
                }
                fetchNextNItems(GridUtils.getLevelIndex(lastExpandedItem) + 1, itemsToFetch, treeData, GridUtils.getLevelNo(lastExpandedItem), GridUtils.getHierarchyNo(lastExpandedItem), false);

            } else {
                treeData.addItems(null, childRows);
            }
            treeDataProvider.refreshAll();

        }
    }

    public void paintFirstPage() {
        setPageNoFieldValue(0);
        TreeData<GtnWsRecordBean> treeData = grid.getTreeData();
        treeData.clear();
        fetchNextNItems(0, 5, treeData, tableConfig.getLevelNo(), EMPTY, true);
    }

    private void fetchNextNItems(int start, int itemsToFetch, TreeData<GtnWsRecordBean> treeData, int currentLevel, String hierNo, boolean isFirst) {
        while (currentLevel >= tableConfig.getLevelNo() && itemsToFetch >= fetched) {
            start = findStart(start, isFirst, currentLevel);
            List<GtnWsRecordBean> rows = loadData(start, itemsToFetch, currentLevel, currentLevel == tableConfig.getLevelNo() ? EMPTY : hierNo).getRows();
            currentLevel--;
            fetchRowsRecursively(null, rows, treeData, itemsToFetch);
        }
        fetched = 0;
        treeDataProvider.refreshAll();
    }

    private int findStart(int start, boolean isFirst, int currentLevel) {
        if (!isFirst) {
            if (tableConfig.getLevelNo() == GridUtils.getLevelNo(lastExpandedItem)) {
                int offset = pageNumber == 0 ? 0 : (pageLength * pageNumber) - GridUtils.getTableIndex(lastExpandedItem) - GridUtils.getChildCount(lastExpandedItem);
                start = start + offset;
            } else {
                start = GridUtils.getLevelIndex(lastExpandedItemHierarchy.get(currentLevel)) + 1;
            }
        }
        return start;
    }

    private void fetchRowsRecursively(GtnWsRecordBean root, List<GtnWsRecordBean> rows, TreeData<GtnWsRecordBean> treeData, int itemsToFetch) {
        for (GtnWsRecordBean parent : rows) {
            if (fetched >= itemsToFetch) {
                break;
            }
            fetched++;
            int tableIndex=(pageNumber*pageLength)+fetched;
            parent.addAdditionalProperties(5,tableIndex);
            treeData.addItem(root, parent);

            if (GridUtils.hasChildren(parent)) {
                int childCount = GridUtils.getChildCount(parent);
                if (isExpanded(parent)) {
                    fetchRowsRecursively(parent, fetchChildren(0, childCount, parent), treeData, itemsToFetch);
                    treeDataProvider.refreshAll();
                    grid.expand(parent);
                } else {
                    treeData.addItem(parent, GridUtils.getEmptyRow(this));
                }
            }
        }
    }

    private void findLastExpandedHierarchy(int currentOffset) {
        lastExpandedItem = findLastItem(currentOffset, 0);
        int levelNo = GridUtils.getLevelNo(tempBean);
        levelNo--;
        while (levelNo >= tableConfig.getLevelNo() || lastExpandedItemHierarchy.isEmpty()) {

            findLastItem(currentOffset, levelNo);
            levelNo--;
        }
    }

    private GtnWsRecordBean findLastItem(int currentOffset, int levelNo) {

        Optional<Integer> last = expandedItemIds.stream().filter(e -> (GridUtils.getLevelNo(e) == levelNo || levelNo == 0)
                && GridUtils.getTableIndex(e) <= currentOffset)
                .map(GridUtils::getTableIndex).max(Integer::compareTo);
        if (last.isPresent()) {
            expandedItemIds.stream().filter(e -> GridUtils.getTableIndex(e) == last.get()).findFirst().ifPresent(e -> tempBean = e);
        }
        lastExpandedItemHierarchy.put(GridUtils.getLevelNo(tempBean), tempBean);
        return tempBean;
    }

    @SuppressWarnings("unchecked")
    private void initalizeColumnController() {
        totalColumns.setReadOnly(false);
        totalColumns.setValue(tableConfig.getColumnHeaders().size() + EMPTY);
        totalColumns.setReadOnly(true);
        columnsPerPage.setValue(10);
         setColumnPageNumber(0);
    }

    public HorizontalLayout getControlLayout() {
        controlLayout.setWidth("100%");
        pageCountLabel = new TextField();
        pageCountLabel.setWidth("50px");
        pageCountLabel.setReadOnly(true);
        pageNoField.setWidth("50px");
        setPageNoFieldValue(0);
        controlLayout.addComponent(new Label("Items per page:"));
        controlLayout.addComponent(getItemsPerPage());
        controlLayout.addComponent(getControlLayoutButtons("<<", e -> this.setPageNumber(0)));
        controlLayout.addComponent(getControlLayoutButtons("<", e -> this.previousPage()));
        controlLayout.addComponent(new Label("Page No:"));
        controlLayout.addComponent(pageNoField);
        controlLayout.addComponent(new Label("/"));
        controlLayout.addComponent(pageCountLabel);
        controlLayout.addComponent(getControlLayoutButtons(">", e -> this.nextPage()));
        controlLayout.addComponent(getControlLayoutButtons(">>", e -> this.setPageNumber(this.getPageCount() - 1)));
        pageNoField.addBlurListener(e -> setPageNumber((Integer.parseInt(pageNoField.getValue())) - 1));
        columnController();
        return controlLayout;
    }

    private Button getControlLayoutButtons(String caption, Button.ClickListener listener) {
        Button button = new Button(caption, listener);
        button.setStyleName("link");
        return button;
    }

    @SuppressWarnings("unchecked")
    private Component getItemsPerPage() {
        itemsPerPage.setItems(new Object[]{5, 10, 15, 20, 25, 50, 100});
        itemsPerPage.setSelectedItem(10);
        itemsPerPage.setWidth("60px");
        itemsPerPage.setEmptySelectionAllowed(false);
        itemsPerPage.addValueChangeListener(e -> setPageLength((int) itemsPerPage.getValue()));
        return itemsPerPage;
    }

    @SuppressWarnings("unchecked")
    private Component getColumnsPerPageComponenet() {
        columnsPerPage.setItems(new Object[]{5, 10, 15, 20, 25, 50, 100});
        columnsPerPage.setSelectedItem(10);
        columnsPerPage.setWidth("60px");
        columnsPerPage.setEmptySelectionAllowed(false);
        columnsPerPage.addValueChangeListener(e -> setColumnPageNumber(0));
        return columnsPerPage;
    }

    public GtnUIFrameworkPagedTreeTableConfig getTableConfig() {
        return tableConfig;
    }

    public void setTableConfig(GtnUIFrameworkPagedTreeTableConfig tableConfig) {
        this.tableConfig = tableConfig;
    }

    public HorizontalLayout columnController() {

        HorizontalLayout hl = controlLayout;
        columnPageNoField.setWidth("50px");
        controlLayout.addComponent(new Label("Columns per page:"));
        controlLayout.addComponent(getColumnsPerPageComponenet());
        controlLayout.addComponent(getControlLayoutButtons("<<", e -> setColumnPageNumber(0)));
        controlLayout.addComponent(getControlLayoutButtons("<", e -> setColumnPageNumber(--columnPageNumber)));
        controlLayout.addComponent(new Label("Columns Page No:"));
        controlLayout.addComponent(columnPageNoField);
        controlLayout.addComponent(new Label("/"));
        totalColumns.setWidth("50px");
        controlLayout.addComponent(totalColumns);
        controlLayout.addComponent(getControlLayoutButtons(">", e -> setColumnPageNumber(++columnPageNumber)));
        controlLayout.addComponent(getControlLayoutButtons(">>", e -> setColumnPageNumber(getTotalPageCount())));
        columnPageNoField.addBlurListener(e -> setColumnPageNumber((Integer.parseInt(columnPageNoField.getValue())) - 1));

        return hl;
    }

    public void addStyleNames(String... styles) {
        grid.addStyleNames(styles);
    }

    public TreeGrid<GtnWsRecordBean> getGrid() {
        return grid;
    }

    public void setGrid(TreeGrid<GtnWsRecordBean> grid) {
        this.grid = grid;
    }

    public void nextPage() {
        if (pageNumber + 1 < getPageCount()) {
            setPageNoFieldValue(++pageNumber);
            paintCurrentPage();
        }
    }

    void setPageNoFieldValue(int pageNo) {
        pageNoField.setValue(String.valueOf(pageNo + 1));
    }

    /**
     * Moves to previous page, if previous page exists.
     */
    public void previousPage() {
        if ((pageNumber - 1) >= 0) {

            setPageNoFieldValue(--pageNumber);
            if (pageNumber != 0) {
             
            } else {
                clearTempVariables();
            }
      paintCurrentPage();
        }
    }

    /**
     * Sets the page length.
     *
     * @param newPageLength
     */
    public void setPageLength(int newPageLength) {
        if (newPageLength <= 0) {
            Notification.show("Illegal page length." + newPageLength);
        }
        pageLength = newPageLength;
        if (pageLength != newPageLength && count != 0) {
            pageNumber = 0;
            setPageNoFieldValue(0);
            paintCurrentPage();
        }
    }

    /**
     * Sets the current page number.
     *
     * @param newPageNumber the desired page
     */
    public void setPageNumber(int newPageNumber) {

        if (newPageNumber >= 0 && newPageNumber <= getPageCount()) {
            pageNumber = newPageNumber;
            setPageNoFieldValue(pageNumber);
            if (pageNumber != 0) {

            } else {
                clearTempVariables();
            }
        } else {
            pageNumber = 0;
            setPageNoFieldValue(0);
            Notification.show("Illegal page number." + newPageNumber);
        }
           paintCurrentPage();
    }

    private void clearTempVariables() {
        lastExpandedItem = null;
        lastExpandedItemHierarchy.clear();
        tempBean=null;
    }

    public int getPageCount() {
        int lastPage = count / pageLength;
        return count % pageLength == 0 ? lastPage : lastPage + 1;
    }

    public int getTotalPageCount() {
        int columnCount = getTotalColumns();
        int columnsPerCount = getColumnsPerPage();
        int lastPage = columnCount / columnsPerCount;
        return columnCount % columnsPerCount == 0 ? lastPage : lastPage + 1;
    }

    public int getTotalColumns() {
        int columnCount = GridUtils.getInt(totalColumns.getValue());
        return columnCount;
    }

    public int getColumnsPerPage() {
        int columnsPerCount = GridUtils.getInt(columnsPerPage.getValue());
        return columnsPerCount;
    }

    public void setColumnPageNumber(int newPageNumber) {
        if (newPageNumber < 0) {
            columnPageNumber = 0;
        }
        columnPageNoField.setValue(Integer.toString(columnPageNumber + 1));
        columnPageNumber = newPageNumber;
        int start = columnPageNumber == 0 ? 0 : (getColumnsPerPage() * columnPageNumber) + 1;
        int end = start + getColumnsPerPage() - 1;
        HeaderUtils.configureGridColumns(start, end <= 0 ? 10 : end, this);

    }

    /**
     * Gets the current page number.
     *
     * @return current page number
     */
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * Gets the current page length.
     *
     * @return current page length
     */
    public int getPageLength() {
        return pageLength;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        pageCountLabel.setReadOnly(false);
        pageCountLabel.setValue(getPageCount()+EMPTY);
        pageCountLabel.setReadOnly(true);
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    public boolean isExpanded(GtnWsRecordBean item) {
        if (item == null) {
            return true;
        }
        return expandedRowIds.contains(item.getAdditionalIntegerPropertyByIndex(3));
    }

    public GtnUIFrameworkComponentConfig getComponentConfig() {
        return componentConfig;
    }

    public void setComponentConfig(GtnUIFrameworkComponentConfig componentConfig) {
        this.componentConfig = componentConfig;
    }
}
