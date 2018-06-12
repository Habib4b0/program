package com.stpl.gtn.gtn2o.ui.framework.component.grid.component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.bean.DataSet;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.service.FetchData;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.vaadin.data.HasValue;
import com.vaadin.data.TreeData;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.event.ExpandEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeGrid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.HeaderCell;
import com.vaadin.ui.components.grid.HeaderRow;

public class PagedTreeGrid {

	private static final GtnWSLogger gtnlogger = GtnWSLogger.getGTNLogger(PagedTreeGrid.class);
	// private static final String LEVEL_INDEX = "levelIndex";
	// private static final String NODE_INDEX = "nodeIndex";
	// private static final String CHILD_COUNT = "childCount";
	private static final String HIERARCHY_NO = "hierarchyNo";
	private static final String LEVEL_NO = "levelNumber";

	GtnUIFrameworkPagedTreeTableConfig tableConfig;
	int count;
	private int pageLength = 10;
	private int pageNumber = 0;
	private int columnPageNumber = 0;
	private DataSet dataSet;
	TreeGrid<GtnWsRecordBean> grid;
	Set<String> tableColumns = new HashSet<>();
	GtnWsRecordBean lastRow;
	TreeDataProvider<GtnWsRecordBean> treeDataProvider;
	private final Set<GtnWsRecordBean> expandedItemIds = new HashSet<>();
	private final Set<Integer> expandedRowIds = new HashSet<>();
	GtnWsRecordBean lastExpandedItem;
	HorizontalLayout controlLayout = new HorizontalLayout();
	private TextField pageNoField = new TextField();
	private TextField columnPageNoField = new TextField();
	private ComboBox itemsPerPage = new ComboBox();
	private ComboBox columnsPerPage = new ComboBox();
	// private TextField columnFrom = new TextField();
	// private TextField columnTo = new TextField();
	private TextField totalColumns = new TextField();
	private Label pageCountLabel;
	GtnUIFrameworkComponentConfig componentConfig;
	private static final String LIKE_OPERATOR = "";
	private static final String CAP_OPERATOR = "";
	int expandTempIndex = 0;
	int expandFinalIndex = 0;
	boolean removeExcessItems = false;
	int excessItemsStartIndex = 0;
	List<GtnWsRecordBean> itemsTobeRemoved = new ArrayList<>();
	private boolean shiftLeftSingeHeader;

	public PagedTreeGrid(GtnUIFrameworkPagedTreeTableConfig tableConfig,
			GtnUIFrameworkComponentConfig componentConfig) {
		try {
			this.tableConfig = tableConfig;
			this.componentConfig = componentConfig;
			gtnlogger.info("count>>>" + count);
			grid = new TreeGrid<>();
			initalizeColumnController();
			setColumnPageNumber(0);
		} catch (Exception ex) {
			gtnlogger.error(ex.getMessage(), ex);
		}
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

	public void initializeGrid() {
		count = getTotalCount();
		expandedItemIds.clear();
		expandedRowIds.clear();
		if (count > 0) {
			dataSet = loadData((pageNumber * pageLength), pageLength);
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
		setPageNumber(0);

	}

	private void initalizeColumnController() {
		totalColumns.setReadOnly(false);
		totalColumns.setValue(tableConfig.getColumnHeaders().size() + "");
		totalColumns.setReadOnly(true);
		columnsPerPage.setValue(10);
	}

	private void configureGridColumns(int columnStart, int columnEnd) {
		int columnCount = tableConfig.getColumnHeaders().size();
		if (columnEnd > columnCount || columnStart > columnEnd) {
			return;
		}
		TreeData<GtnWsRecordBean> treedata = null;
		treedata = repaintGrid(treedata);
		List<Object> currentSingleColumns = tableConfig.getVisibleColumns().subList(columnStart, columnEnd);

		for (int j = 0; j < currentSingleColumns.size() && columnEnd < columnCount; j++) {
			String column = (currentSingleColumns.get(j)).toString();
			gtnlogger.info("column = " + column);
			grid.addColumn(row -> row.getPropertyValue(column))
					.setCaption(tableConfig.getColumnHeaders().get(columnStart + j)).setId(column).setWidth(170);
		}

		if (tableConfig.getCustomFilterConfigMap() != null) {
			shiftLeftSingeHeader = true;
		}
		if (tableConfig.isEnableRadioButtonInSingleHeader()) {

			HeaderRow single = grid.getHeaderRow(0);
			for (int j = 0; j < currentSingleColumns.size(); j++) {
				String column = (currentSingleColumns.get(j)).toString();
				RadioButtonGroup vaadinRadioButtonGroup = new RadioButtonGroup();
				vaadinRadioButtonGroup.setItems(tableConfig.getRightTableVisibleHeader()[columnStart + j]);
				single.getCell(column).setComponent(vaadinRadioButtonGroup);

			}
		}
		if (tableConfig.isDoubleHeaderVisible()) {
			addDoubleHeader(currentSingleColumns);
		}
		addTripleHeader();
		if (treedata != null) {
			grid.setTreeData(treedata);
		}
	}

	public void addTripleHeader() {
		if (tableConfig.isTripleHeaderVisible()) {
			HeaderRow doubleHeader = grid.getHeaderRow(1);

			HeaderRow groupingHeader = grid.prependHeaderRow();

			for (Object property : tableConfig.getRightTableTripleHeaderMap().keySet()) {
				Object joinList[] = tableConfig.getRightTableTripleHeaderMap().get(property);

				Set<HeaderCell> columnList = new HashSet<>();
				for (int i = 0; i < joinList.length; i++) {
					Object object = joinList[i];
					columnList.add(doubleHeader.getCell(object.toString()));
				}

				if (tableConfig.isEnableCheckBoxInTripleHeader()) {

					CheckBoxGroup vaadinCheckBoxGroup = new CheckBoxGroup();
					vaadinCheckBoxGroup.setItems(tableConfig.getRightTableDoubleVisibleHeaders().iterator().next());

					groupingHeader.join(columnList).setComponent(vaadinCheckBoxGroup);

				} else {
					groupingHeader.join(columnList)
							.setText(tableConfig.getRightTableTripleVisibleHeaders().iterator().next());
				}

			}
		}
		if (tableConfig.getCustomFilterConfigMap() != null) {
			setFilterToGrid();
		}
		addTableHeaderCheck();

		shiftLeftSingeHeader = false;
	}

	public void addDoubleHeader(List<Object> currentSingleColumns) {
		HeaderRow groupingHeader = grid.getHeaderRowCount() > 1 ? grid.getHeaderRow(1) : grid.prependHeaderRow();
		int j = 0;
		if (shiftLeftSingeHeader) {
			shiftLeftHeader(groupingHeader);
		}
		for (Object property : tableConfig.getLeftTableDoubleHeaderVisibleColumns()) {
			Object joinList[] = tableConfig.getLeftTableDoubleHeaderMap().get(property);
			if (joinList != null) {
				String[] stringArray = Arrays.copyOf(joinList, joinList.length, String[].class);
				if (stringArray.length > 1) {
					groupingHeader.join(stringArray)
							.setText(tableConfig.getLeftTableDoubleHeaderVisibleHeaders().get(j++));
				} else if (stringArray.length > 0) {
					groupingHeader.getCell(stringArray[0])
							.setText(tableConfig.getLeftTableDoubleHeaderVisibleHeaders().get(j++));
				}
			}
		}
		j = 0;
		for (Object property : tableConfig.getRightTableDoubleHeaderVisibleColumns()) {
			Object joinList[] = tableConfig.getRightTableDoubleHeaderMap().get(property);
			String[] stringArray = getSingleColumnsMapping(currentSingleColumns, joinList);
			if (stringArray.length > 1) {
				groupingHeader.join(stringArray).setText(tableConfig.getRightTableDoubleVisibleHeaders().get(j++));
			} else if (stringArray.length > 0) {
				groupingHeader.getCell(stringArray[0])
						.setText(tableConfig.getRightTableDoubleVisibleHeaders().get(j++));
			}

		}
	}

	public TreeData<GtnWsRecordBean> repaintGrid(TreeData<GtnWsRecordBean> treedata) {
		if (grid.getParent() != null) {
			treedata = grid.getTreeData();
			VerticalLayout parent = (VerticalLayout) grid.getParent();
			grid = new TreeGrid<>();
			grid.setWidth(componentConfig.getComponentWidth());
			grid.setHeight(componentConfig.getComponentHight());
			parent.replaceComponent(parent.getComponent(0), grid);
		}
		return treedata;
	}

	String[] getSingleColumnsMapping(List<Object> currentSingleColumns, Object joinList[]) {
		List<String> returnList = new ArrayList<>();
		for (Object object : joinList) {
			if (currentSingleColumns.contains(object)) {
				returnList.add(object.toString());
			}
		}
		return returnList.toArray(new String[0]);
	}

	// CheckBox in DoubleColumnHeader
	private void addTableHeaderCheck() {

		HeaderRow row = grid.getHeaderRowCount() > 2 ? grid.getHeaderRow(1) : grid.getHeaderRow(0);
		if (tableConfig.getCheckBoxVisibleColoumn() != null) {
			for (String columnId : tableConfig.getCheckBoxVisibleColoumn()) {

				gtnlogger.info("CID= " + columnId);

				CheckBox vaadinCheckBoxGroup = new CheckBox();
				row.getCell(columnId).setComponent(vaadinCheckBoxGroup);
			}
		}
	}

	private void shiftLeftHeader(HeaderRow groupingHeader) {
		for (int j = 0; j < tableConfig.getLeftTableColumnMappingId().length; j++) {
			String column = (tableConfig.getLeftTableColumnMappingId()[j]).toString();
			gtnlogger.info("column = " + column);
			groupingHeader.getCell(column).setText(tableConfig.getColumnHeaders().get(j));

		}
	}

	private void addExpandListener() {
		grid.addExpandListener(new ExpandEvent.ExpandListener<GtnWsRecordBean>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void itemExpand(ExpandEvent<GtnWsRecordBean> event) {
				try {

					TreeData<GtnWsRecordBean> treeData = treeDataProvider.getTreeData();
					GtnWsRecordBean parent = event.getExpandedItem();
					int childCount = 0;
					expandRow(parent, childCount, treeData, true);
				} catch (Exception ex) {
					gtnlogger.error(ex.getMessage(), ex);
				}
			}

		});
	}

	public void addCollapseListener() {
		grid.addCollapseListener(event -> {
			GtnWsRecordBean parent = event.getCollapsedItem();
			int childCount = getChildCountForRow(parent);
			int rowNo = getNodeIndex(parent);
			expandedItemIds.remove(parent);
			expandedRowIds.remove(Integer.valueOf(rowNo));
			count -= childCount;
		});
	}

	private void addExpandIcon(TreeData<GtnWsRecordBean> data, List<GtnWsRecordBean> rows) {
		gtnlogger.info("addExpandIcon");
		rows.stream().map((parent) -> {
			if (getLevelNo(parent) != 0 && getChildCount(parent) > 0) {
				data.addItem(parent, getEmptyRow());
			}
			return parent;
		}).forEach((GtnWsRecordBean parent) -> {
			int rowNo = getNodeIndex(parent);
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

		if (parent != null && getLevelNo(parent) != 0 && hasChildren(parent)) {
			childCount = getChildCountForRow(parent);
			// GtnWsRecordBean firstChild = ;
			for (int i = 0; i < treeData.getChildren(parent).size(); i++) {
				treeData.removeItem(treeData.getChildren(parent).get(i));
			}
			// treeData.removeItem(parent)
			//
			count += childCount;
			int nodeIndex = getNodeIndex(parent);
			List<GtnWsRecordBean> bean = treeDataProvider.getTreeData().getRootItems();
			removeExcessItems(bean, treeDataProvider.getTreeData(), parent, childCount);
			gtnlogger.info("index + " + expandFinalIndex);
			gtnlogger.info("rowNo" + nodeIndex);
			int limit = (pageLength * (pageNumber + 1)) - expandFinalIndex;

			if (limit == 0) {
				if (!moveToNextPage) {
					treeData.addItem(parent, getEmptyRow());
					return;
				}
				limit = pageLength > childCount ? childCount : pageLength;
				setPageNoFieldValue(++pageNumber);
				treeData.clear();
			}
			itemsTobeRemoved.stream().filter((item) -> (treeDataProvider.getTreeData().contains(item)))
					.forEach((item) -> {
						treeDataProvider.getTreeData().removeItem(item);
					});
			itemsTobeRemoved.clear();
			removeExcessItems = false;

			// if (limit + expandTempIndex >= pageLength) {
			// removeExcessItems(nodeIndex + limit);
			// }

			limit = childCount < Math.abs(limit) ? childCount : limit;
			List<GtnWsRecordBean> childRows = fetchChildren(0, limit, parent, expandFinalIndex).getRows();
			expandTempIndex = 0;
			expandFinalIndex = 0;

			refreshDataProvider(treeData, parent, childRows);
			expandedItemIds.add(parent);
			expandedRowIds.add(nodeIndex);
		}
	}

	public void removeExcessItems(List<GtnWsRecordBean> bean, TreeData<GtnWsRecordBean> treeData,
			GtnWsRecordBean itemToFind, int childCount) {
		for (GtnWsRecordBean item : bean) {
			if (!removeExcessItems) {
				expandTempIndex++;
				traverseChildNodes(item, treeData, itemToFind, childCount);
				if (itemToFind.equals(item)) {
					removeExcessItems = true;
					expandFinalIndex = expandTempIndex;// 2
					excessItemsStartIndex = (pageLength * (pageNumber + 1)) - expandTempIndex;// 3
					excessItemsStartIndex = excessItemsStartIndex > childCount ? pageLength - childCount + 1
							: expandFinalIndex + 1;// 3

				}
			} else if (expandTempIndex++ > excessItemsStartIndex && excessItemsStartIndex++ <= pageLength) {
				itemsTobeRemoved.add(item);
			}
			traverseChildNodes(item, treeData, itemToFind, childCount);
		}
	}

	public void traverseChildNodes(GtnWsRecordBean item, TreeData<GtnWsRecordBean> treeData, GtnWsRecordBean itemToFind,
			int childCount) {
		if (getChildCountForRow(item) > 0 && isExpanded(item)) {
			removeExcessItems(treeData.getChildren(item), treeData, itemToFind, childCount);
		}
	}

	private void refreshDataProvider(TreeData<GtnWsRecordBean> treeData, GtnWsRecordBean parent,
			List<GtnWsRecordBean> childRows) {
		treeData.addItems(treeData.contains(parent) ? parent : null, childRows);
		addExpandIcon(treeData, childRows);
		treeDataProvider.refreshAll();
	}

	public GtnWsRecordBean getEmptyRow() {
		GtnWsRecordBean emptyRow = new GtnWsRecordBean();
		emptyRow.setRecordHeader(getRecordHeader());
		emptyRow.setProperties(getRecordHeader());
		emptyRow.setAdditionalProperties(Arrays.asList(0, 0, "", 0));
		return emptyRow;
	}

	private int getTotalCount() {

		if (tableConfig.getCountUrl() != null) {
			GtnWsSearchRequest request = getWsRequest(0, pageLength, true, Arrays.asList(LEVEL_NO, HIERARCHY_NO),
					Arrays.asList(1, LIKE_OPERATOR));
			List<GtnWsRecordBean> result = FetchData.callWebService(tableConfig, componentConfig.getModuleName(),
					request);

			return result == null ? 0 : result.size();
		}
		return 0;

	}

	private int getChildCount(GtnWsRecordBean parent) {
		int childCount = 0;
		if (tableConfig.getCountUrl() != null) {
			GtnWsSearchRequest request = getWsRequest(0, pageLength, true, Arrays.asList(LEVEL_NO, HIERARCHY_NO),
					Arrays.asList(getLevelNo(parent) + 1, getHierarchyNo(parent)));
			List<GtnWsRecordBean> result = FetchData.callWebService(tableConfig, componentConfig.getModuleName(),
					request);
			childCount = result == null ? 0 : result.size();
			parent.addAdditionalProperties(0, childCount);
		}
		return childCount;
	}

	public DataSet fetchChildren(int start, int limit, GtnWsRecordBean parent, int parentRowIndex) {

		GtnWsSearchRequest request = getWsRequest(start, limit, true, Arrays.asList(LEVEL_NO, HIERARCHY_NO),
				Arrays.asList(getLevelNo(parent) + 1, getHierarchyNo(parent)));
		List<GtnWsRecordBean> rows = FetchData.callWebService(tableConfig, componentConfig.getModuleName(), request);
		return new DataSet(tableColumns.stream().collect(Collectors.toList()), rows);
	}

	private DataSet loadData(int start, int limit) {
		List<GtnWsRecordBean> updatedrows = new ArrayList<>();
		if (count != 0) {
			GtnWsSearchRequest request = getWsRequest(start, limit, true, Arrays.asList(LEVEL_NO, HIERARCHY_NO),
					Arrays.asList(tableConfig.getLevelNo(), ""));
			updatedrows = FetchData.callWebService(tableConfig, componentConfig.getModuleName(), request);

		}
		return new DataSet(tableColumns.stream().collect(Collectors.toList()), updatedrows);

	}

	public GtnWsSearchRequest getWsRequest(int start, int offset, boolean isCount, List<String> input,
			List<Object> inputValues) {
		//
		GtnWsSearchRequest request = new GtnWsSearchRequest();
		request.setTableRecordOffset(offset);
		request.setTableRecordStart(start);
		request.setCount(isCount);
		request.setQueryInput(input);
		request.setQueryInputList(inputValues);
		List<Object> visibleColumns = getRecordHeader();
		request.setRecordHeader(visibleColumns);

		return request;
	}

	public List<Object> getRecordHeader() {
		List<Object> visibleColumns = tableConfig.getVisibleColumns();
		// addColumnsInRecordHeader(visibleColumns, LEVEL_NO, HIERARCHY_NO, CHILD_COUNT,
		// HIERARCHY_NO, NODE_INDEX);

		return visibleColumns;
	}

	public void addColumnsInRecordHeader(List<Object> visibleColumns, String... columns) {
		for (String column : columns) {
			if (!visibleColumns.contains(column)) {
				visibleColumns.add(column);
			}
		}

	}

	private void getNextPageOffset() {
		if (lastRow != null) {
			TreeData<GtnWsRecordBean> treeData = treeDataProvider.getTreeData();
			int lastRowIndex = getNodeIndex(lastRow);
			if (lastExpandedItem != null) {
				int childCount = getChildCountForRow(lastExpandedItem);
				int parentRowIndex = getNodeIndex(lastExpandedItem);
				int offset = (lastRowIndex - parentRowIndex) + 1;
				int limit = pageLength - offset;
				limit = (childCount - offset) < limit ? childCount : limit;

				List<GtnWsRecordBean> childRows = fetchChildren(lastRowIndex + 1, limit, lastExpandedItem,
						parentRowIndex).getRows();
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
			// getLeftData(currentOffset, currentOffset + pageLength,
			// tableConfig.getLevelNo(), ".*");
			dataSet = loadData(currentOffset, currentOffset + pageLength);
		} else {
			findLastExpandedItem(currentOffset);
			int lastRowno = getNodeIndex(lastExpandedItem);
			int childCount = getChildCountForRow(lastExpandedItem);
			if ((lastRowno + childCount) >= currentOffset) {
				int offset = pageNumber == 0 ? 0 : (pageLength * pageNumber) - lastRowno;
				int itemToFetch = childCount - offset;
				int limit = itemToFetch > pageLength ? pageLength : itemToFetch;
				dataSet = fetchChildren(currentOffset, currentOffset + pageLength, lastExpandedItem,
						getNodeIndex(lastExpandedItem));
				// getLeftData(offset, limit, getLevelNo(lastExpandedItem) + 1,
				// getHierarchyNo(lastExpandedItem));
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

	public void expandedItemsCount(int currentOffset) {
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
		return getInt(row.getAdditionalPropertyByIndex(0));
	}

	public static int getNodeIndex(GtnWsRecordBean row) {
		return getInt(row.getAdditionalPropertyByIndex(3));
	}

	public static int getLevelNo(GtnWsRecordBean row) {
		return getInt(row.getAdditionalPropertyByIndex(1));
	}

	public String getHierarchyNo(GtnWsRecordBean row) {
		return String.valueOf(row.getAdditionalPropertyByIndex(2));
	}

	private static int getInt(Object value) {
		int i = 0;
		try {
			i = Integer.parseInt(String.valueOf(value));
		} catch (NumberFormatException e) {
			gtnlogger.error(e.getMessage());
		}
		return i;
	}

	public HorizontalLayout getControlLayout() {
		controlLayout.setWidth("100%");
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
		itemsPerPage.setItems(new Object[] { 5, 10, 15, 20, 25, 50, 100 });
		itemsPerPage.setSelectedItem(10);
		itemsPerPage.setWidth("60px");
		itemsPerPage.setEmptySelectionAllowed(false);
		itemsPerPage.addValueChangeListener(e -> setPageLength((int) itemsPerPage.getValue()));
		return itemsPerPage;
	}

	@SuppressWarnings("unchecked")
	private Component getColumnsPerPageComponenet() {
		columnsPerPage.setItems(new Object[] { 5, 10, 15, 20, 25, 50, 100 });
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
		columnPageNoField
				.addBlurListener(e -> setColumnPageNumber((Integer.parseInt(columnPageNoField.getValue())) - 1));

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
		pageLength = newPageLength;
		if (pageLength != newPageLength && count != 0) {
			pageNumber = 0;
			setPageNoFieldValue(0);
			resetGridToInitialState();
		}
	}

	/**
	 * Sets the current page number.
	 *
	 * @param newPageNumber
	 *            the desired page
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

	public int getTotalPageCount() {
		int columnCount = getTotalColumns();
		int columnsPerCount = getColumnsPerPage();
		int lastPage = columnCount / columnsPerCount;
		return columnCount % columnsPerCount == 0 ? lastPage : lastPage + 1;
	}

	public int getTotalColumns() {
		int columnCount = getInt(totalColumns.getValue());
		return columnCount;
	}

	public int getColumnsPerPage() {
		int columnsPerCount = getInt(columnsPerPage.getValue());
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
		configureGridColumns(start, end <= 0 ? 10 : end);

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

	public boolean isExpanded(GtnWsRecordBean item) {
		if (item == null) {
			return true;
		}
		return expandedItemIds.contains(item);
	}

	public boolean hasChildren(GtnWsRecordBean item) {
		return getChildCountForRow(item) > 0;
	}

	private void removeExcessItems(int nodeIndex) {
		Iterator<GtnWsRecordBean> treeData = treeDataProvider.getTreeData().getRootItems().iterator();

		while (treeData.hasNext()) {
			GtnWsRecordBean item = treeData.next();
			if (getNodeIndex(item) > nodeIndex) {
				treeData.remove();
			}
		}

	}

	private HeaderRow setFilterToGrid() {
		HeaderRow filterRow = grid.getDefaultHeaderRow();
		Component vaadinComponent = null;
		Object[] filterColumnIdList = tableConfig.getLeftTableColumnMappingId();
		for (Object column : filterColumnIdList) {

			vaadinComponent = getCustomFilterComponent(String.valueOf(column));
			vaadinComponent.setId(column.toString());
			filterRow.getCell(String.valueOf(column)).setComponent(vaadinComponent);
		}

		return filterRow;
	}

	private Component getCustomFilterComponent(String property) {
		try {
			gtnlogger.info("-------property------" + property);
			GtnUIFrameworkPagedTableCustomFilterConfig filterConfig = tableConfig.getCustomFilterConfigMap()
					.get(property);

			if (filterConfig.getGtnComponentType() == GtnUIFrameworkComponentType.TEXTBOX_VAADIN8) {
				TextField textField = new TextField();
				textField.setId(property);
				textField.addValueChangeListener(this::onFilterTextChange);
				return textField;
			} else if (filterConfig.getGtnComponentType() == GtnUIFrameworkComponentType.DATEFIELDVAADIN8) {
				DateField dateField = new DateField();
				dateField.setId(property);
				dateField.addValueChangeListener(this::onFilterDateChange);
				return dateField;
			} else if (filterConfig.getGtnComponentType() == GtnUIFrameworkComponentType.COMBOBOX_VAADIN8) {
				GtnUIFrameworkComponent component = filterConfig.getGtnComponentType().getGtnComponent();
				Component vaadinComponent = null;
				vaadinComponent = component.buildVaadinComponent(filterConfig.getGtnComponentConfig());
				ComboBox vaadinCombobox = (ComboBox) vaadinComponent;
				vaadinCombobox.setId(property);
				vaadinCombobox.addValueChangeListener(this::onFilterTextChange);
				return vaadinCombobox;
			} else if (filterConfig.getGtnComponentType() == GtnUIFrameworkComponentType.CALENDAR_FIELD) {
				Button dateFilterPopupButton = new Button("Show all");
				// dateFilterPopupButton.setWidth("400px");
				// DateFilterPopup dateFilterpopup = new DateFilterPopup(dateFilterPopupButton,
				// tableConfig, property,
				// componentConfig);
				// Window window = dateFilterpopup.getDateFilterPopup();
				// dateFilterPopupButton.addClickListener(new Button.ClickListener() {
				// @Override
				// public void buttonClick(Button.ClickEvent event) {
				//
				// window.setPosition(event.getClientX(), event.getClientY());
				// UI.getCurrent().addWindow(window);
				// }
				// });

				return dateFilterPopupButton;
			}

		} catch (GtnFrameworkGeneralException exception) {
			gtnlogger.error("Exception while creating the filter component", exception);
		}

		return null;
	}

	private void onFilterTextChange(HasValue.ValueChangeEvent<String> event) {
		// tableConfig.getFilterValueMap().put(event.getComponent().getId(),
		// event.getValue());
		resetGridToInitialState();
	}

	public void onFilterDateChange(HasValue.ValueChangeEvent<LocalDate> event) {
		// tableConfig.getFilterValueMap().put(event.getComponent().getId(),
		// event.getValue());
		resetGridToInitialState();
	}
}
