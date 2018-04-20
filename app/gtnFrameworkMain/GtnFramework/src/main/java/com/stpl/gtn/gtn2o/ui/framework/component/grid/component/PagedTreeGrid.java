package com.stpl.gtn.gtn2o.ui.framework.component.grid.component;

import com.stpl.gtn.gtn2o.ui.framework.component.grid.bean.DataSet;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.config.PagedTreeTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.service.FetchData;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableConfig;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.vaadin.data.HasValue;
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
import com.vaadin.ui.components.grid.HeaderRow;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PagedTreeGrid {

    private static final String ROW_NUMBER = "rowNumber";
    private static final String CHILD_COUNT = "childCount";
    private static final String HIERARCHY_NO = "hierarchyNo";
    private static final String LEVEL_NO = "levelNo";
    GtnUIFrameworkPagedTreeTableConfig tableConfig;
    int count;
    private int pageLength = 3;
    private int pageNumber = 0;
    private DataSet dataSet;
    TreeGrid<GtnWsRecordBean> grid;
    Set<String> tableColumns = new HashSet<>();
    GtnWsRecordBean lastRow;
    private DataSet leftTableDataSet;
    TreeDataProvider<GtnWsRecordBean> treeDataProvider;
    private final List<GtnWsRecordBean> expandedItemIds = new ArrayList<>();
    ;
    private final List<Integer> expandedRowIds = new ArrayList<>();
    GtnWsRecordBean lastExpandedItem;
    HorizontalLayout controlLayout = new HorizontalLayout();
    private TextField pageNoField = new TextField();
    private Label pageCountLabel;

    public PagedTreeGrid(GtnUIFrameworkPagedTreeTableConfig tableConfig) {
        this.tableConfig = tableConfig;
        count = getTotalCount();
        System.out.println("count>>>" + count);
        grid = new TreeGrid<>();
        initializeGrid();
    }

    public void resetGridToInitialState() {
        count = getTotalCount();
        int offset = pageNumber * pageLength;
        dataSet = loadData(offset, pageLength);
        @SuppressWarnings("unchecked")
        TreeData<GtnWsRecordBean> treeData = treeDataProvider.getTreeData();
        treeData.clear();
        treeData.addItems(null, dataSet.getRows());
        addExpandIcon(treeData, dataSet.getRows());
        treeDataProvider.refreshAll();
    }

    private void initializeGrid() {
        count = getTotalCount();

        if (count > 0) {
            getLeftData((pageNumber * pageLength), pageLength, tableConfig.getLevelNo(), "%");
            dataSet = loadData((pageNumber * pageLength), pageLength);
        }
        grid.removeAllColumns();
        Set<String> headers = tableColumns;

//        leftTableDataSet.getColumns().stream().forEach((leftColumn) -> {
//            grid.addColumn(leftRow -> leftRow.getPropertyValue(leftColumn.toString())).setCaption(leftColumn.toString());
//        });
       
        for (int j = 0; j < 50; j++) {
            Object column = tableConfig.getVisibleColumns().get(j);
            System.out.println("column = " + column);
            grid.addColumn(row -> row.getPropertyValue(column.toString())).setCaption(column.toString());
            
        }
//         if (tableConfig.isDoubleHeaderVisible()) {
//            HeaderRow groupingHeader = grid.prependHeaderRow();
//             for (int j = 0; j < tableConfig.getVisibleColumns().size(); j++) {
//                 Object column = tableConfig.getVisibleColumns().get(j);
//                 System.out.println("column getCell= " + column);
//                 groupingHeader.getCell(column.toString()).setText(tableConfig.getRightTableDoubleVisibleHeaders().get(j));
//             }
//             for(Object property:tableConfig.getRightTableDoubleHeaderMap().keySet()){
//                 Object joinList[]=tableConfig.getRightTableDoubleHeaderMap().get(property);
//                 String[] stringArray = Arrays.copyOf(joinList, joinList.length, String[].class);
//                 groupingHeader.join(stringArray);
//             }
//        }
//          if (tableConfig.isTripleHeaderVisible()) {
//            HeaderRow groupingHeader = grid.prependHeaderRow();
//             for (int j = 0; j < tableConfig.getVisibleColumns().size(); j++) {
//                 Object column = tableConfig.getVisibleColumns().get(j);
//                 groupingHeader.getCell(column.toString()).setText(tableConfig.getRightTableDoubleVisibleHeaders().get(j));
//             }
//             for(Object property:tableConfig.getRightTableTripleHeaderMap().keySet()){
//                 Object joinList[]=tableConfig.getRightTableTripleHeaderMap().get(property);
//                 String[] stringArray = Arrays.copyOf(joinList, joinList.length, String[].class);
//                 groupingHeader.join(stringArray);
//             }
//        }
         // Group headers by joining the cells
      
//        tableConfig.getVisibleColumns().stream().forEach((column) -> {
//            
//            grid.addColumn(row -> row.getPropertyValue(column.toString())).setCaption(column.toString());
//        });
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

    private void addExpandListener() {
        grid.addExpandListener(new ExpandEvent.ExpandListener<GtnWsRecordBean>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void itemExpand(ExpandEvent<GtnWsRecordBean> event) {
                TreeData<GtnWsRecordBean> treeData = treeDataProvider.getTreeData();
                GtnWsRecordBean parent = event.getExpandedItem();
                int childCount = 0;
                expandRow(parent, childCount, treeData, true);
            }

        });
    }

    public void expandRow(GtnWsRecordBean parent, int childCount, TreeData<GtnWsRecordBean> treeData, boolean moveToNextPage) {
        if (parent != null && parent.getPropertyValue(LEVEL_NO) != null
                && (childCount = getChildCountForRow(parent)) > 0) {
            GtnWsRecordBean firstChild = treeData.getChildren(parent).get(0);
            if (firstChild.getPropertyValue(LEVEL_NO) != null) {
                return;
            }
            treeData.removeItem(firstChild);
            count += childCount;
            int rowNo = getRowNo(parent);
            System.out.println("rowNo" + rowNo);
            int limit = (pageLength * (pageNumber + 1)) - rowNo;
            expandedItemIds.add(parent);
            expandedRowIds.add(rowNo);
            if (limit == 0) {
                if (!moveToNextPage) {
                    treeData.addItem(parent, new GtnWsRecordBean());
                    return;
                }
                limit = pageLength > childCount ? childCount : pageLength;
                setPageNoFieldValue(++pageNumber);
                treeData.clear();
            }
            getLeftData(0, limit, getLevelNo(parent) + 1, getHierarchyNo(parent));

            limit = childCount < limit ? childCount : limit;
            List<GtnWsRecordBean> childRows = fetchChildren(0, limit, parent, rowNo).getRows();

            refreshDataProvider(treeData, parent, childRows);
        }
    }

    public void addCollapseListener() {
        grid.addCollapseListener(event -> {
            GtnWsRecordBean parent = event.getCollapsedItem();
            int childCount = getChildCountForRow(parent);
            int rowNo = getRowNo(parent);
            expandedItemIds.remove(parent);
            expandedRowIds.remove(Integer.valueOf(rowNo));
            count -= childCount;
        });
    }

    private void refreshDataProvider(TreeData<GtnWsRecordBean> treeData, GtnWsRecordBean parent, List<GtnWsRecordBean> childRows) {
        treeData.addItems(treeData.contains(parent) ? parent : null, childRows);
        addExpandIcon(treeData, childRows);
        treeDataProvider.refreshAll();
    }

    private void addExpandIcon(TreeData<GtnWsRecordBean> data, List<GtnWsRecordBean> rows) {
        System.out.println("addExpandIcon");
        rows.stream().map((parent) -> {
            if (parent != null && parent.getPropertyValue(LEVEL_NO) != null && getChildCountForRow(parent) > 0) {
                data.addItem(parent, new GtnWsRecordBean());
            }
            return parent;
        }).forEach((GtnWsRecordBean parent) -> {
            int rowNo = getRowNo(parent);
            if (expandedRowIds.contains(rowNo)) {
                int childCount = 0;
                expandRow(parent, childCount, data, false);
                if (rowNo % pageLength != 0) {
                    grid.expand(parent);
                }
            }
        });
    }

    private int getTotalCount() {

        if (tableConfig.getCountQuery() != null) {

            String countQuery = replaceQueryInput(tableConfig.getLevelNo(), "%",
                    tableConfig.getCountQuery());
            List<GtnWsRecordBean> result = FetchData.fetchResultAsRow(tableConfig.getVisibleColumns().toArray(), countQuery, tableConfig.getCountQueryInputs());
            System.out.println("total count " + result.size());

            leftTableDataSet = new DataSet(tableConfig.getVisibleColumns(), result);
            return result.size();
        }
        return 0;

    }

    private int getChildCount(GtnWsRecordBean parent) {

        if (tableConfig.getCountQuery() != null) {
            System.out.println("parent.getPropertyValue(\"levelNo\")" + parent.getPropertyValue(LEVEL_NO));
            int levelNo = Integer.valueOf(parent.getPropertyValue(LEVEL_NO).toString()) + 1;
            String hierarchyNo = String.valueOf(parent.getPropertyValue(HIERARCHY_NO)) + "%";
            String countQuery = replaceQueryInput(levelNo, hierarchyNo, tableConfig.getCountQuery());
            List<GtnWsRecordBean> result = FetchData.fetchResultAsRow(tableConfig.getVisibleColumns().toArray(), countQuery, tableConfig.getCountQueryInputs());
            System.out.println("child count" + result.size());
            if (!result.isEmpty()) {
                leftTableDataSet = new DataSet(tableConfig.getVisibleColumns(), result);
            }
            return result.size();
        }
        return 0;

    }

    private int getLeftData(int offset, int limit, int levelNo, String hierarchyNo) {

        if (tableConfig.getCountQuery() != null) {
            String countQuery = replaceQueryInput(levelNo, hierarchyNo, tableConfig.getLeftDataQuery());
            List<Object> list = addRangeInInput(tableConfig.getLeftDataQueryInputs(), offset, limit);
            List<GtnWsRecordBean> result = FetchData.fetchResultAsRow(tableConfig.getVisibleColumns().toArray(), countQuery, list.toArray());
            System.out.println("child count" + result.size());
            if (!result.isEmpty()) {
                leftTableDataSet = new DataSet(tableConfig.getVisibleColumns(), result);
            }
            return result.size();
        }
        return 0;

    }

    public DataSet fetchChildren(int offset, int limit, GtnWsRecordBean parent, int parentRowIndex) {
        String hierarchyNo = String.valueOf(parent.getPropertyValue(HIERARCHY_NO)) + "%";
        int levelNo = Integer.valueOf(parent.getPropertyValue(LEVEL_NO).toString()) + 1;
        String dataQuery = replaceQueryInput(levelNo, hierarchyNo, tableConfig.getDataQuery());
        Object[] input = tableConfig.getDataQueryInputs();
        List<Object> list = addRangeInInput(input, offset, limit);
        List<GtnWsRecordBean> rows = FetchData.fetchResultAsRow(tableConfig.getVisibleColumns().toArray(), dataQuery, list.toArray());
        List<GtnWsRecordBean> updatedrows = mergeLeftAndRightData(rows, limit, offset, parentRowIndex);
        return new DataSet(tableColumns.stream().collect(Collectors.toList()), updatedrows);
    }

    public static List<Object> addRangeInInput(Object[] input, int offset, int limit) {
        List<Object> list = new ArrayList<>();
        if (input != null) {
            list = new LinkedList<>(Arrays.asList(input));
        }
        list.add(offset);
        list.add(limit);
        return list;
    }

    private String replaceQueryInput(int levelNo, String hierarchyNo, String query) {
        String replacedQuery = query.replaceAll("@levelNo", Integer.toString(levelNo));
        replacedQuery = replacedQuery.replaceAll("@hierarchyNo", hierarchyNo + "%");
        return replacedQuery;
    }

    private DataSet loadData(int offset, int limit) {
        List<GtnWsRecordBean> updatedrows = new ArrayList<>();
        if (count != 0) {
            String dataQuery = replaceQueryInput(tableConfig.getLevelNo(), "%", tableConfig.getDataQuery());
            Object[] input = tableConfig.getDataQueryInputs();
            List<Object> list = addRangeInInput(input, offset * tableConfig.getRowsPerLevelItem(), limit * tableConfig.getRowsPerLevelItem());
            List<GtnWsRecordBean> rows = FetchData.fetchResultAsRow(tableConfig.getVisibleColumns().toArray(), dataQuery, list.toArray());
            updatedrows = mergeLeftAndRightData(rows, limit, offset, pageNumber * pageLength);
        }

        return new DataSet(tableColumns.stream().collect(Collectors.toList()), updatedrows);
    }

    private List<GtnWsRecordBean> mergeLeftAndRightData(List<GtnWsRecordBean> rows, int limit, int offset, int parentRowIndex) {

        List<GtnWsRecordBean> rowsList = new ArrayList<>();
        List<GtnWsRecordBean> leftRows = leftTableDataSet.getRows();
        for (GtnWsRecordBean newRow : leftRows) {
            int childCount = getChildCount(newRow);
            newRow.addProperties(CHILD_COUNT, childCount);
            newRow.addProperties(ROW_NUMBER, parentRowIndex + 1);
            rows.stream().filter((row) -> !(!String.valueOf(row.getPropertyValue(HIERARCHY_NO)).equals(String.valueOf(newRow.getPropertyValue(HIERARCHY_NO))))).forEach((row) -> {
                String period = String.valueOf(row.getPropertyValue("frequency"));
                String year = String.valueOf(row.getPropertyValue("year"));

                newRow.addProperties(HIERARCHY_NO, row.getPropertyValue(HIERARCHY_NO));
                newRow.addProperties(LEVEL_NO, row.getPropertyValue(LEVEL_NO));

                tableConfig.getVisibleColumns().stream().map((column) -> {
                    tableColumns.add(period + year + column);
                    return column;
                }).forEach((column) -> {
                    newRow.addProperties(period + year + column, row.getPropertyValue(column.toString()));
                });
            });
            rowsList.add(newRow);
        }
        lastRow = rowsList.isEmpty() ? null : rowsList.get(rowsList.size() - 1);
        return rowsList;
    }

    public void nextPage() {
        if (pageNumber + 1 < getPageCount()) {
            setPageNoFieldValue(++pageNumber);
            findCurrentPageOffset();

            getNextPageOffset();

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
                findCurrentPageOffset();
            } else {
                lastExpandedItem = null;
            }
            getNextPageOffset();
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
        if (pageLength != newPageLength) {
            pageNumber = 0;
            setPageNoFieldValue(0);
            pageLength = newPageLength;
            resetGridToInitialState();
        }
    }

    /**
     * Sets the current page number.
     *
     * @param newPageNumber the desired page
     */
    public void setPageNumber(int newPageNumber) {

        if (newPageNumber >= 0 && newPageNumber < getPageCount()) {
            pageNumber = newPageNumber;
            setPageNoFieldValue(pageNumber);
            if (pageNumber != 0) {
                findCurrentPageOffset();
            } else {
                lastExpandedItem = null;
            }
            getNextPageOffset();
        } else {
            pageNumber = 0;
            setPageNoFieldValue(0);
            Notification.show("Illegal page number." + newPageNumber);
        }
    }

    public int getPageCount() {
        int lastPage = count / pageLength;
        return count % pageLength == 0 ? lastPage : lastPage + 1;
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
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    public TreeGrid<GtnWsRecordBean> getGrid() {
        return grid;
    }

    public void setGrid(TreeGrid<GtnWsRecordBean> grid) {
        this.grid = grid;
    }

    public boolean isExpanded(GtnWsRecordBean item) {
        if (item == null) {
            return true;
        }
        return expandedItemIds.contains(item);
    }

    public boolean hasChildren(GtnWsRecordBean item) {
        if (item != null && item.getPropertyValue(CHILD_COUNT) != null) {
            return getChildCountForRow(item) > 0;
        }
        return false;
    }

    private void getNextPageOffset() {
        if (lastRow != null) {
            TreeData<GtnWsRecordBean> treeData = treeDataProvider.getTreeData();
            int lastRowIndex = getRowNo(lastRow);
            if (lastExpandedItem != null) {
                int childCount = getChildCountForRow(lastExpandedItem);
                int parentRowIndex = getRowNo(lastExpandedItem);
                int offset = (lastRowIndex - parentRowIndex) + 1;
                int limit = pageLength - offset;
                limit = (childCount - offset) < limit ? childCount : limit;

                List<GtnWsRecordBean> childRows = fetchChildren(lastRowIndex + 1, limit, lastExpandedItem, parentRowIndex).getRows();
                treeData.clear();
                refreshDataProvider(treeData, null, childRows);

            } else if (expandedItemIds.isEmpty()) {
                initializeGrid();
            } else {
                resetGridToInitialState();

            }

        }
    }

    void findCurrentPageOffset() {

        int currentOffset = pageNumber * pageLength;
        if (expandedItemIds.isEmpty()) {
            getLeftData(currentOffset, currentOffset + pageLength, tableConfig.getLevelNo(), "%");
            dataSet = loadData((pageNumber * pageLength), pageLength);
        } else {
            findLastExpandedItem(currentOffset);
            int lastRowno = getRowNo(lastExpandedItem);
            int childCount = getChildCount(lastExpandedItem);
            if ((lastRowno + childCount) >= currentOffset) {
                int offset = pageNumber == 0 ? 0 : (pageLength * pageNumber) - lastRowno;
                int itemToFetch = childCount - offset;
                int limit = itemToFetch > pageLength ? pageLength : itemToFetch;
                getLeftData(offset, limit, getLevelNo(lastExpandedItem) + 1, getHierarchyNo(lastExpandedItem));
            }
        }
    }

    public void findLastExpandedItem(int currentOffset) {
        int totalCount = getTotalCount();
        for (GtnWsRecordBean row : expandedItemIds) {
            int childCount = getChildCountForRow(row);
            totalCount = totalCount + childCount;
            if (totalCount > currentOffset) {
                lastExpandedItem = row;
                break;
            }
        }
    }

    public static int getChildCountForRow(GtnWsRecordBean row) {
        return (int) row.getPropertyValue(CHILD_COUNT);
    }

    public static int getRowNo(GtnWsRecordBean row) {
        return (int) row.getPropertyValue(ROW_NUMBER);
    }

    public static int getLevelNo(GtnWsRecordBean row) {
        return Integer.parseInt(row.getPropertyValue(LEVEL_NO).toString());
    }

    public String getHierarchyNo(GtnWsRecordBean row) {
        return String.valueOf(row.getPropertyValue(HIERARCHY_NO));
    }

    public HorizontalLayout getControlLayout() {
           controlLayout.setWidth("100%");
            pageNoField = new TextField();
            pageCountLabel = new Label("1");
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
        return controlLayout;
    }
  private Button getControlLayoutButtons(String caption, Button.ClickListener listener) {
        Button button = new Button(caption, listener);
        button.setStyleName("link");
        return button;
    }
    public void addStyleNames(String... styles) {
        grid.addStyleNames(styles);
    }
      private Component getItemsPerPage() {
        ComboBox itemsPerPage = new ComboBox();
        itemsPerPage.setItems(new Object[]{5, 10, 15, 20, 25, 50, 100});
        itemsPerPage.setSelectedItem(10);
        itemsPerPage.setWidth("60px");
        itemsPerPage.setEmptySelectionAllowed(false);
        itemsPerPage.addValueChangeListener(new HasValue.ValueChangeListener() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent event) {
                setPageLength((int) itemsPerPage.getValue());
            }
        });
        return itemsPerPage;
    }
}
