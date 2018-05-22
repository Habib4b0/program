package com.stpl.gtn.gtn2o.ui.framework.component.grid.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.bean.DataSet;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.service.FetchData;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableConfig;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
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

public class PagedTreeGrid {

	private static final GtnWSLogger gtnlogger = GtnWSLogger.getGTNLogger(PagedTreeGrid.class);
	private static final String ROW_NUMBER = "rowNumber";
	private static final String CHILD_COUNT = "childCount";
	private static final String HIERARCHY_NO = "hierarchyNo";
	private static final String PARENT_HIERARCHY_NO = "generatedHierarchyNo";
	private static final String LEVEL_NO = "levelNumber";
	GtnUIFrameworkPagedTreeTableConfig tableConfig;
	int count;
	private int pageLength = 10;
	private int pageNumber = 0;
	private DataSet dataSet;
	TreeGrid<GtnWsRecordBean> grid;
	Set<String> tableColumns = new HashSet<>();
	GtnWsRecordBean lastRow;
	private DataSet leftTableDataSet;
	TreeDataProvider<GtnWsRecordBean> treeDataProvider;
	private final List<GtnWsRecordBean> expandedItemIds = new ArrayList<>();;
	private final List<Integer> expandedRowIds = new ArrayList<>();
	GtnWsRecordBean lastExpandedItem;
	HorizontalLayout controlLayout = new HorizontalLayout();
	private TextField pageNoField = new TextField();
	private Label pageCountLabel;
	GtnUIFrameworkComponentConfig componentConfig;
	private static final String LIKE_OPERATOR = ".*";
	private static final String CAP_OPERATOR = "^";
	private boolean shiftLeftSingeHeader=false;

	public PagedTreeGrid(GtnUIFrameworkPagedTreeTableConfig tableConfig,
			GtnUIFrameworkComponentConfig componentConfig) {
		try {
			this.tableConfig = tableConfig;
			this.componentConfig = componentConfig;

			gtnlogger.info("count>>>" + count);
			grid = new TreeGrid<>();
			configureGridColumns();
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

		if (count > 0) {
			getLeftData((pageNumber * pageLength), pageLength, tableConfig.getLevelNo(), LIKE_OPERATOR);
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
	}

	private void configureGridColumns() {
		grid.removeAllColumns();

		int columnCount = tableConfig.getVisibleColumns().size();
		for (int j = 0; j < columnCount && j < tableConfig.getColumnHeaders().size(); j++) {
			String column = (tableConfig.getVisibleColumns().get(j)).toString();
			gtnlogger.info("column = " + column);
			grid.addColumn(row -> row.getPropertyValue(column)).setCaption(tableConfig.getColumnHeaders().get(j))
					.setId(column);

		}
		if(tableConfig.getCustomFilterConfigMap()!=null){
			shiftLeftSingeHeader=true;
		}
		// gtnlogger.info("headers size= " +
		// tableConfig.getVisibleColumns().size());

		// Adding Radio Button to the singleHeader
		if (tableConfig.isEnableRadioButtonInSingleHeader()) {

			HeaderRow single = grid.getHeaderRow(0);
			for (int j = 0; j < tableConfig.getRightTableColumnMappingId().length ; j++) {
				String column = (tableConfig.getRightTableColumnMappingId()[j]).toString();
				RadioButtonGroup vaadinRadioButtonGroup = new RadioButtonGroup();
				vaadinRadioButtonGroup.setItems(tableConfig.getRightTableVisibleHeader()[j]);
				single.getCell(column).setComponent(vaadinRadioButtonGroup);

			}
		}
		

		if (tableConfig.isDoubleHeaderVisible()) {
			HeaderRow groupingHeader = grid.prependHeaderRow();
			if(shiftLeftSingeHeader){
				shiftLeftHeader(groupingHeader);
			}
			int j = 0;
			for (Object property : tableConfig.getRightTableDoubleHeaderVisibleColumns()) {
				if (tableConfig.getRightTableDoubleHeaderMap().get(property) != null) {
					Object joinList[] = tableConfig.getRightTableDoubleHeaderMap().get(property);

					String[] stringArray = Arrays.copyOf(joinList, joinList.length, String[].class);
					j = configureDoubleHeaderComponents(groupingHeader, j, joinList, stringArray);
				}
			}
			
		}
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
		if(tableConfig.getCustomFilterConfigMap()!=null){
			setFilterToGrid();
		}
		addTableHeaderCheck();
		
	}
	shiftLeftSingeHeader=false;
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

	private int configureDoubleHeaderComponents(HeaderRow groupingHeader, int j, Object[] joinList,
			String[] stringArray) {
		if (tableConfig.isEnableCheckBoxInDoubleHeader()) {
			CheckBoxGroup vaadinCheckBoxGroup = new CheckBoxGroup();
			vaadinCheckBoxGroup.setItems(tableConfig.getRightTableDoubleVisibleHeaders().iterator().next());
			if (joinList.length > 1) {
				groupingHeader.join(stringArray).setComponent(vaadinCheckBoxGroup);
			} else {
				groupingHeader.getCell((String.valueOf(joinList[0]))).setComponent(vaadinCheckBoxGroup);
			}

		} else {
			if (joinList.length > 1) {
				groupingHeader.join(stringArray)
						.setText(tableConfig.getRightTableDoubleVisibleHeaders().iterator().next());
			} else {
				groupingHeader.getCell((String.valueOf(joinList[0])))
						.setText(tableConfig.getRightTableDoubleVisibleHeaders().get(j++));
			}
		}
		return j;
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

	public void expandRow(GtnWsRecordBean parent, int childCount, TreeData<GtnWsRecordBean> treeData,
			boolean moveToNextPage) {

		if (parent != null && parent.getPropertyValue(LEVEL_NO) != null && (getChildCountForRow(parent)) > 0) {
			childCount = getChildCountForRow(parent);
			GtnWsRecordBean firstChild = treeData.getChildren(parent).get(0);
			if (!firstChild.getProperties().isEmpty() && getLevelNo(firstChild) != 0) {
				return;
			}
			treeData.removeItem(firstChild);
			count += childCount;
			int rowNo = getRowNo(parent);
			gtnlogger.info("rowNo" + rowNo);
			int limit = (pageLength * (pageNumber + 1)) - rowNo;
			expandedItemIds.add(parent);
			expandedRowIds.add(rowNo);
			if (limit == 0) {
				if (!moveToNextPage) {
					treeData.addItem(parent, getEmptyRow());
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

	private void refreshDataProvider(TreeData<GtnWsRecordBean> treeData, GtnWsRecordBean parent,
			List<GtnWsRecordBean> childRows) {
		treeData.addItems(treeData.contains(parent) ? parent : null, childRows);
		addExpandIcon(treeData, childRows);
		treeDataProvider.refreshAll();
	}

	private void addExpandIcon(TreeData<GtnWsRecordBean> data, List<GtnWsRecordBean> rows) {
		gtnlogger.info("addExpandIcon");
		rows.stream().map((parent) -> {
			if (parent.getPropertyValue(LEVEL_NO) != null && getChildCountForRow(parent) > 0) {
				data.addItem(parent, getEmptyRow());
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

	public GtnWsRecordBean getEmptyRow() {
		GtnWsRecordBean emptyRow = new GtnWsRecordBean();
		emptyRow.setRecordHeader(getRecordHeader());
		emptyRow.setProperties(getRecordHeader());
		return emptyRow;
	}

	private int getTotalCount() {

		if (tableConfig.getCountUrl() != null) {
			GtnWsSearchRequest request = getWsRequest(0, 0, true);
			request.setQueryInput(Arrays.asList(LEVEL_NO, PARENT_HIERARCHY_NO));
			request.setQueryInputList(Arrays.asList(1, LIKE_OPERATOR));
			List<GtnWsRecordBean> result = FetchData.callWebService(tableConfig, componentConfig.getModuleName(),
					request);
			gtnlogger.info("total count " + (result == null ? 0 : result.size()));

			leftTableDataSet = new DataSet(tableConfig.getVisibleColumns(), result);
			return result == null ? 0 : result.size();
		}
		return 0;

	}

	public GtnWsSearchRequest getWsRequest(int offset, int start, boolean isCount) {
		//
		// String countQuery = replaceQueryInput(tableConfig.getLevelNo(), "%",
		// tableConfig.getCountQuery());
		GtnWsSearchRequest request = new GtnWsSearchRequest();
		request.setTableRecordOffset(offset);
		request.setTableRecordStart(start);
		request.setCount(isCount);
		List<Object> visibleColumns = getRecordHeader();
		request.setRecordHeader(visibleColumns);

		return request;
	}

	public List<Object> getRecordHeader() {
		List<Object> visibleColumns = tableConfig.getVisibleColumns();
		addInRecordHeader(visibleColumns, LEVEL_NO, HIERARCHY_NO, CHILD_COUNT, ROW_NUMBER, PARENT_HIERARCHY_NO);

		return visibleColumns;
	}

	public void addInRecordHeader(List<Object> visibleColumns, String... columns) {
		for (String column : columns) {
			if (!visibleColumns.contains(column)) {
				visibleColumns.add(column);
			}
		}

	}

	private int getChildCount(GtnWsRecordBean parent) {

		if (tableConfig.getCountUrl() != null && parent.getPropertyValue(LEVEL_NO) != null) {
			gtnlogger.info("parent.getPropertyValue(\"levelNo\")" + parent.getPropertyValue(LEVEL_NO));
			int levelNo = Integer.valueOf(parent.getPropertyValue(LEVEL_NO).toString()) + 1;
			String hierarchyNo = String.valueOf(parent.getPropertyValue(PARENT_HIERARCHY_NO));
			// String countQuery = replaceQueryInput(levelNo, hierarchyNo,
			// tableConfig.getCountQuery());

			GtnWsSearchRequest request = getWsRequest(0, 0, true);
			request.setQueryInput(Arrays.asList(LEVEL_NO, PARENT_HIERARCHY_NO));
			request.setQueryInputList(Arrays.asList(levelNo, CAP_OPERATOR + hierarchyNo + LIKE_OPERATOR));
			List<GtnWsRecordBean> result = FetchData.callWebService(tableConfig, componentConfig.getModuleName(),
					request);
			gtnlogger.info("child count" + (result == null ? 0 : result.size()));
			if (!result.isEmpty()) {
				leftTableDataSet = new DataSet(tableConfig.getVisibleColumns(), result);
			}
			return (result == null ? 0 : result.size());
		}
		return 0;

	}

	private int getLeftData(int offset, int limit, int levelNo, String hierarchyNo) {

		if (tableConfig.getCountUrl() != null) {
			// String countQuery = replaceQueryInput(levelNo, hierarchyNo,
			// tableConfig.getLeftDataQuery());
			List<Object> list = addRangeInInput(tableConfig.getLeftDataQueryInputs(), offset, limit);

			GtnWsSearchRequest request = getWsRequest(limit, offset, true);
			request.setQueryInput(Arrays.asList(LEVEL_NO, PARENT_HIERARCHY_NO));
			request.setQueryInputList(Arrays.asList(levelNo, CAP_OPERATOR + hierarchyNo + LIKE_OPERATOR));

			List<GtnWsRecordBean> result = FetchData.callWebService(tableConfig, componentConfig.getModuleName(),
					request);
			gtnlogger.info("child count" + (result == null ? 0 : result.size()));
			if (!result.isEmpty()) {
				leftTableDataSet = new DataSet(tableConfig.getVisibleColumns(), result);
			}
			return (result == null ? 0 : result.size());
		}
		return 0;

	}

	public DataSet fetchChildren(int offset, int limit, GtnWsRecordBean parent, int parentRowIndex) {
		String hierarchyNo = String.valueOf(parent.getPropertyValue(PARENT_HIERARCHY_NO));
		int levelNo = Integer.valueOf(parent.getPropertyValue(LEVEL_NO).toString()) + 1;
		// String dataQuery = replaceQueryInput(levelNo, hierarchyNo,
		// tableConfig.getDataQuery());
		Object[] input = tableConfig.getDataQueryInputs();
		List<Object> list = addRangeInInput(input, offset, limit);

		GtnWsSearchRequest request = getWsRequest(limit, offset, true);
		request.setQueryInput(Arrays.asList(LEVEL_NO, PARENT_HIERARCHY_NO));
		request.setQueryInputList(Arrays.asList(levelNo, "/^" + hierarchyNo + "/"));
		List<GtnWsRecordBean> rows = FetchData.callWebService(tableConfig, componentConfig.getModuleName(), request);
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

	// private String replaceQueryInput(int levelNo, String hierarchyNo, String
	// query) {
	// String replacedQuery = query.replaceAll("@levelNo",
	// Integer.toString(levelNo));
	// replacedQuery = replacedQuery.replaceAll("@hierarchyNo", hierarchyNo);
	// return replacedQuery;
	// }

	private DataSet loadData(int offset, int limit) {
		List<GtnWsRecordBean> updatedrows = new ArrayList<>();
		if (count != 0) {
			// String dataQuery = replaceQueryInput(, , tableConfig.getDataQuery());
			Object[] input = tableConfig.getDataQueryInputs();
			List<Object> list = addRangeInInput(input, offset * tableConfig.getRowsPerLevelItem(),
					limit * tableConfig.getRowsPerLevelItem());
			GtnWsSearchRequest request = getWsRequest(limit, offset, true);
			request.setQueryInput(Arrays.asList(LEVEL_NO, PARENT_HIERARCHY_NO));
			request.setQueryInputList(Arrays.asList(tableConfig.getLevelNo(), ".*"));
			List<GtnWsRecordBean> rows = FetchData.callWebService(tableConfig, componentConfig.getModuleName(),
					request);
			updatedrows = mergeLeftAndRightData(rows, limit, offset, pageNumber * pageLength);
		}

		return new DataSet(tableColumns.stream().collect(Collectors.toList()), updatedrows);
	}

	private List<GtnWsRecordBean> mergeLeftAndRightData(List<GtnWsRecordBean> rows, int limit, int offset,
			int parentRowIndex) {

		List<GtnWsRecordBean> rowsList = new ArrayList<>();
		List<GtnWsRecordBean> leftRows = leftTableDataSet.getRows();
		for (GtnWsRecordBean newRow : leftRows) {
			int childCount = getChildCount(newRow);

			newRow.addProperties(CHILD_COUNT, childCount);
			newRow.addProperties(ROW_NUMBER, parentRowIndex + 1);
			rows.stream().filter((row) -> !(!String.valueOf(row.getPropertyValue(HIERARCHY_NO))
					.equals(String.valueOf(newRow.getPropertyValue(HIERARCHY_NO))))).forEach((row) -> {
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
			getLeftData(currentOffset, currentOffset + pageLength, tableConfig.getLevelNo(), ".*");
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
		return getInt(row.getPropertyValue(CHILD_COUNT));
	}

	public static int getRowNo(GtnWsRecordBean row) {
		return getInt(row.getPropertyValue(ROW_NUMBER));
	}

	public static int getLevelNo(GtnWsRecordBean row) {
		return getInt(row.getPropertyValue(LEVEL_NO));
	}

	public String getHierarchyNo(GtnWsRecordBean row) {
		return String.valueOf(row.getPropertyValue(PARENT_HIERARCHY_NO));
	}

	private static int getInt(Object value) {
		int i = 0;
		try {
			i = Integer.parseInt(String.valueOf(value));
		} catch (Exception e) {
			gtnlogger.error(e.getMessage());
		}
		return i;
	}

	public HorizontalLayout getControlLayout() {
		controlLayout.setWidth("100%");
		controlLayout.setSpacing(false);
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
		itemsPerPage.setItems(new Object[] { 5, 10, 15, 20, 25, 50, 100 });
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

	public GtnUIFrameworkPagedTreeTableConfig getTableConfig() {
		return tableConfig;
	}

	public void setTableConfig(GtnUIFrameworkPagedTreeTableConfig tableConfig) {
		this.tableConfig = tableConfig;
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
//					dateFilterPopupButton.setWidth("400px");
//					DateFilterPopup dateFilterpopup = new DateFilterPopup(dateFilterPopupButton, tableConfig, property,
//							componentConfig);
//					Window window = dateFilterpopup.getDateFilterPopup();
//					dateFilterPopupButton.addClickListener(new Button.ClickListener() {
//						@Override
//						public void buttonClick(Button.ClickEvent event) {
//
//							window.setPosition(event.getClientX(), event.getClientY());
//							UI.getCurrent().addWindow(window);
//						}
//					});

					return dateFilterPopupButton;
			}

		} catch (GtnFrameworkGeneralException exception) {
			gtnlogger.error("Exception while creating the filter component", exception);
		}

		return null;
	}
	private void onFilterTextChange(HasValue.ValueChangeEvent<String> event) {
//		tableConfig.getFilterValueMap().put(event.getComponent().getId(), event.getValue());
		resetGridToInitialState();
	}
	public void onFilterDateChange(HasValue.ValueChangeEvent<LocalDate> event) {
//		tableConfig.getFilterValueMap().put(event.getComponent().getId(), event.getValue());
		resetGridToInitialState();
	}

}
