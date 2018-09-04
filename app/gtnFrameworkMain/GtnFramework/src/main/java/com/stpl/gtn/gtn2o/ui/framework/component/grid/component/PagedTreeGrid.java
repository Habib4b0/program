package com.stpl.gtn.gtn2o.ui.framework.component.grid.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
import com.vaadin.event.CollapseEvent;
import com.vaadin.event.ExpandEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeGrid;
import java.util.Collections;

public class PagedTreeGrid {
	/**
	 * Logger
	 */
	public static final GtnWSLogger gtnlogger = GtnWSLogger.getGTNLogger(PagedTreeGrid.class);
	protected static final List<String> INPUT = Arrays.asList("levelNumber", "hierarchyNo");
	private GtnUIFrameworkPagedTreeTableConfig tableConfig;
	private int count;
	private int pageLength = 10;
	private int pageNumber = 0;
	private int columnPageNumber = 0;
	private DataSet dataSet;
	private TreeGrid<GtnWsRecordBean> grid;
	private Set<String> tableColumns = new HashSet<>();

	private final Set<GtnWsRecordBean> expandedItemIds = new HashSet<>();
	private final Set<Integer> expandedRowIds = new HashSet<>();
	private HashMap<Integer, GtnWsRecordBean> lastExpandedItemHierarchy = new HashMap<>(7);
	private GtnWsRecordBean lastExpandedItem;
	private HorizontalLayout controlLayout = new HorizontalLayout();
	private TextField pageNoField = new TextField();
	private TextField columnPageNoField = new TextField();
	private ComboBox itemsPerPage = new ComboBox();
	private ComboBox columnsPerPage = new ComboBox();

	private TextField totalColumns = new TextField();
	private TextField pageCountLabel;
	private GtnUIFrameworkComponentConfig componentConfig;
	private int expandTempIndex = 0;
	private int expandFinalIndex = 0;
	private boolean removeExcessItems = false;
	private int excessItemsStartIndex = 0;
	private List<GtnWsRecordBean> itemsTobeRemoved = new ArrayList<>();
	private boolean shiftLeftSingeHeader;
	private GtnWsRecordBean tempBean = null;
	private int fetched = 0;
	private boolean levelExpandOn = false;
	private int levelExpandNo = 1;

	private String componentIdInMap = null;
	private boolean columnLazyLoading = false;
	private static final Object[] pages=new Object[] { 5, 10, 15, 20, 25, 50, 100 };

	public PagedTreeGrid(GtnUIFrameworkPagedTreeTableConfig tableConfig,
			GtnUIFrameworkComponentConfig componentConfig) {
		try {
			this.tableConfig = tableConfig;
			this.componentConfig = componentConfig;
			grid = new TreeGrid<>();
			initalizeColumnController();

		} catch (Exception ex) {
			gtnlogger.error(ex.getMessage(), ex);
		}
	}

	public boolean isShiftLeftSingeHeader() {
		return shiftLeftSingeHeader;
	}

	public void setShiftLeftSingeHeader(boolean shiftLeftSingeHeader) {
		this.shiftLeftSingeHeader = shiftLeftSingeHeader;
	}

	public String getComponentIdInMap() {
		return componentIdInMap;
	}

	public void setComponentIdInMap(String componentIdInMap) {
		this.componentIdInMap = componentIdInMap;
	}

	public boolean isColumnLazyLoading() {
		return columnLazyLoading;
	}

	public void setColumnLazyLoading(boolean columnLazyLoading) {
		this.columnLazyLoading = columnLazyLoading;
	}

	public void resetGridToInitialState() {
		setCount(getTotalCount());
		int offset = pageNumber * pageLength;
		dataSet = loadData(offset, pageLength, tableConfig.getLevelNo(), GtnFrameworkCommonStringConstants.STRING_EMPTY);
		@SuppressWarnings("unchecked")
		TreeData<GtnWsRecordBean> treeData = getTreeDataProvider().getTreeData();
		treeData.clear();
		treeData.addItems(null, dataSet.getRows());
		addExpandIcon(treeData, dataSet.getRows());
		getTreeDataProvider().refreshAll();
	}

	/**
	 * resets the grid to initial state
	 *
	 * @param componentId
	 */
	public void initializeGrid(String componentId) {

		clearTempVariables();
		clearExpandTempVariables();

		initialConfig(componentId);
	}

	public void initialConfig(String componentId) {
		setComponentIdInMap(componentId);
		setPageNoFieldValue(0);
		pageLength = tableConfig.getPageLength();
                itemsPerPage.setSelectedItem(pageLength);
		setCount(getTotalCount());

		if (count > 0) {
			dataSet = loadData((pageNumber * pageLength), pageLength, tableConfig.getLevelNo(), GtnFrameworkCommonStringConstants.STRING_EMPTY);
		}

		TreeData<GtnWsRecordBean> data = new TreeData<>();
		TreeDataProvider<GtnWsRecordBean> treeDataProvider = new TreeDataProvider<>(data);
		grid.setDataProvider(treeDataProvider);
		if (dataSet != null) {
			grid.getTreeData().addItems(null, dataSet.getRows());
			addExpandIcon(grid.getTreeData(), dataSet.getRows());
		}

		addListeners();
	}

	public void addListeners() {
		if (grid.getListeners(ExpandEvent.class).isEmpty()) {
			addExpandListener();
		}
		if (grid.getListeners(CollapseEvent.class).isEmpty()) {
			addCollapseListener();
		}
	}

	/**
	 * Expands all nodes for given Level No
	 * 
	 * @param levelNo
	 */
	public void expandAll(int levelNo) {
		int level = tableConfig.getLevelNo();
		while (level <= levelNo) {
			setCount(count + getLevelCount(++level));
		}

		levelExpandOn = true;
		levelExpandNo = levelNo;
		paintFirstPage();
	}

	/**
	 * Collapses all nodes for given Level No
	 * 
	 * @param levelNo
	 */
	public void collapseAll(int levelNo) {
		int lastExpandedLevel = levelExpandNo;
		if (levelNo == tableConfig.getLevelNo()) {
			levelExpandOn = false;
			levelExpandNo = tableConfig.getLevelNo();
		} else {
			levelExpandNo = levelNo - 1;
		}
		while (lastExpandedLevel >= levelExpandNo) {
			setCount(count - getLevelCount(lastExpandedLevel--));
		}
		expandedItemIds.stream().filter(e -> GridUtils.getLevelNo(e) >= levelNo).forEach(row -> {
			expandedRowIds.remove(GridUtils.getNodeIndex(row));
		});
		expandedItemIds.removeIf(e -> GridUtils.getLevelNo(e) >= levelNo);

		paintFirstPage();
	}

	/**
	 * expand listener - called when user expand a row
	 */
	public void addExpandListener() {
		grid.addExpandListener(event -> {
			try {
				GtnWsRecordBean parent = event.getExpandedItem();
				if (event.isUserOriginated()) {
					TreeData<GtnWsRecordBean> treeData = getTreeDataProvider().getTreeData();
					expandRow(parent, treeData, true);
				}
			} catch (Exception ex) {
				gtnlogger.error(ex.getMessage(), ex);
			}
		});
	}

	/**
	 * collapse listener - called when user collapse a row
	 */
	public void addCollapseListener() {
		grid.addCollapseListener(event -> {
			GtnWsRecordBean parent = event.getCollapsedItem();
			int childCount = GridUtils.getChildCount(parent);
                        int parentId=GridUtils.getNodeIndex(parent);
			expandedItemIds.remove(parent);
			expandedRowIds.remove(GridUtils.getNodeIndex(parent));
			Set<GtnWsRecordBean> toBeRemoved = expandedItemIds.stream()
					.filter(s -> GridUtils.getHierarchyNo(s).startsWith(GridUtils.getHierarchyNo(parent))
                                                && GridUtils.getNodeIndex(s)!=parentId)
					.collect(Collectors.toSet());
			for (GtnWsRecordBean bean : toBeRemoved) {
				expandedItemIds.remove(bean);
				expandedRowIds.remove(GridUtils.getNodeIndex(bean));
				childCount += GridUtils.getChildCount(bean);
			}
			setCount(count - childCount);
			removeAlreadyExpanded(GridUtils.getTableIndex(parent));

			// refresh current page
			paintCurrentPage();
		});
	}

	/**
	 * adds Expand Icon if a row has child
	 */

	private void addExpandIcon(TreeData<GtnWsRecordBean> data, List<GtnWsRecordBean> rows) {
		try {
			rows.stream().map(parent -> {
				if (GridUtils.getLevelNo(parent) != 0 && GridUtils.getChildCount(parent) > 0) {
					data.addItem(parent, GridUtils.getEmptyRow(this));
				}
				return parent;
			}).forEach((GtnWsRecordBean parent) -> {
				if (expandedRowIds.contains(GridUtils.getNodeIndex(parent))) {

					expandRow(parent, data, false);
					if (GridUtils.getNodeIndex(parent) % pageLength != 0 && GridUtils.getNodeIndex(parent) != 0 ) {
						grid.expand(parent);
					}
				}
			});
		} catch (Exception e) {
			gtnlogger.error(e.getMessage());
		}
	}

	/**
	 * expands the row
	 */
	public void expandRow(GtnWsRecordBean parent, TreeData<GtnWsRecordBean> treeData,
			boolean moveToNextPage) {
		if (parent != null && GridUtils.getLevelNo(parent) != 0 && GridUtils.hasChildren(parent)) {
			int childCount = GridUtils.getChildCount(parent);
			for (int i = 0; treeData.contains(parent) && i < treeData.getChildren(parent).size(); i++) {
				treeData.removeItem(treeData.getChildren(parent).get(i));
			}
			setCount(count + childCount);
			int nodeIndex = GridUtils.getNodeIndex(parent);
			List<GtnWsRecordBean> bean = getTreeDataProvider().getTreeData().getRootItems();
			removeExcessItems(bean, getTreeDataProvider().getTreeData(), parent, childCount);
			int tableIndex = expandFinalIndex + (pageLength * pageNumber);
			parent.addAdditionalProperties(5, tableIndex);

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
			itemsTobeRemoved.stream().filter(item -> (getTreeDataProvider().getTreeData().contains(item)))
					.forEach(item -> {
						getTreeDataProvider().getTreeData().removeItem(item);
					});

			limit = childCount < Math.abs(limit) ? childCount : limit;
			List<GtnWsRecordBean> childRows = fetchChildren(0, limit, parent);

			refreshDataProvider(treeData, parent, childRows);
			addExpandedItems(parent, nodeIndex, childCount, tableIndex);
			clearExpandTempVariables();
		}
	}

	/**
	 * saving expanded Items for future reference
	 */
	private void addExpandedItems(GtnWsRecordBean parent, int nodeIndex, int childCount, int tableIndex) {

		moveAlreadyExpanded(tableIndex, childCount);
		expandedItemIds.add(parent);
		expandedRowIds.add(nodeIndex);

	}

	/**
	 * removing already expanded items if root node of a hierarchy is collapsed
	 */
	private void removeAlreadyExpanded(int tableIndex) {
		expandedItemIds.stream().filter(item -> GridUtils.getTableIndex(item) > tableIndex).forEach(item -> {
			item.addAdditionalProperties(5, tableIndex + 1);
		});
	}

	/**
	 * Moving already expanded items if an item before is expanded
	 */
	private void moveAlreadyExpanded(int tableIndex, int childCount) {
		expandedItemIds.stream().filter(item -> GridUtils.getTableIndex(item) >= tableIndex).forEach(item -> {
			item.addAdditionalProperties(5, GridUtils.getTableIndex(item) + childCount);
		});
	}

	public void clearExpandTempVariables() {
		expandTempIndex = 0;
		expandFinalIndex = 0;
		itemsTobeRemoved.clear();
		removeExcessItems = false;
	}

	/**
	 * removes excess nodes in the current page when a node is expanded
	 */
	private void removeExcessItems(List<GtnWsRecordBean> bean, TreeData<GtnWsRecordBean> treeData,
			GtnWsRecordBean itemToFind, int childCount) {
		bean.stream().map(item -> {
			if (!removeExcessItems) {
				expandTempIndex++;
				if (itemToFind.equals(item)) {
					removeExcessItems = true;
					expandFinalIndex = expandTempIndex;// 2
					excessItemsStartIndex = (pageLength * (pageNumber + 1)) - expandTempIndex;// 3
					excessItemsStartIndex = excessItemsStartIndex > childCount ? pageLength - childCount + 1
							: expandFinalIndex + 1;// 3
				}
			} else if (++expandTempIndex >= excessItemsStartIndex && excessItemsStartIndex++ <= pageLength) {
				itemsTobeRemoved.add(item);
			}
			return item;
		}).forEach(item -> {
			traverseChildNodes(item, treeData, itemToFind, childCount);
		});
	}

	private void traverseChildNodes(GtnWsRecordBean item, TreeData<GtnWsRecordBean> treeData,
			GtnWsRecordBean itemToFind, int childCount) {
		if (GridUtils.getChildCount(item) > 0 && isExpanded(item)) {
			removeExcessItems(treeData.getChildren(item), treeData, itemToFind, childCount);
		}
	}

	public void refreshDataProvider(TreeData<GtnWsRecordBean> treeData, GtnWsRecordBean parent,
			List<GtnWsRecordBean> childRows) {
		treeData.addItems(treeData.contains(parent) ? parent : null, childRows);
		addExpandIcon(treeData, childRows);
		getTreeDataProvider().refreshAll();
	}

	public List<GtnWsRecordBean> fetchAll() {

		if (tableConfig.getCountUrl() != null) {
			GtnWsSearchRequest request = GridUtils.getWsRequest(0, 0, true, null, null, tableConfig);
			return FetchData.callWebService(tableConfig, componentConfig.getModuleName(), request,
					getComponentIdInMap());
		}
		return Collections.emptyList();

	}

	public int getTotalCount() {
		return getLevelCount(tableConfig.getLevelNo());
	}

	private int getLevelCount(int levelNo) {

		if (tableConfig.getCountUrl() != null) {
			GtnWsSearchRequest request = GridUtils.getWsRequest(0, pageLength, true, INPUT,
					Arrays.asList(levelNo, GtnFrameworkCommonStringConstants.STRING_EMPTY), tableConfig);
			List<GtnWsRecordBean> result = FetchData.callWebService(tableConfig, componentConfig.getModuleName(),
					request, getComponentIdInMap());

			return result == null ? 0 : result.size();
		}
		return 0;

	}

	

	public List<GtnWsRecordBean> fetchChildren(int start, int limit, GtnWsRecordBean parent) {

		GtnWsSearchRequest request = GridUtils.getWsRequest(start, limit, true, INPUT,
				Arrays.asList(GridUtils.getLevelNo(parent) + 1, GridUtils.getHierarchyNo(parent)), tableConfig);
		return FetchData.callWebService(tableConfig, componentConfig.getModuleName(), request, getComponentIdInMap());
	}

	private DataSet loadData(int start, int limit, int levelNo, String hierarchyNo) {
		List<GtnWsRecordBean> updatedrows = new ArrayList<>();
		if (count != 0) {
			GtnWsSearchRequest request = GridUtils.getWsRequest(start, limit, true, INPUT,
					Arrays.asList(levelNo, hierarchyNo), tableConfig);
			updatedrows = FetchData.callWebService(tableConfig, componentConfig.getModuleName(), request,
					getComponentIdInMap());

		}
		return new DataSet(tableColumns.stream().collect(Collectors.toList()), updatedrows);

	}

	/**
	 * reloads the current page
	 */
	public void paintCurrentPage() {
		setCount(count);
                getPageCount();
		if (pageNumber == 0 || pageNumber+1 > Integer.parseInt(pageCountLabel.getValue())) {
                    pageNumber=0;
			paintFirstPage();
			return;
		}
		int currentOffset = pageNumber * pageLength;
		gtnlogger.info("currentOffset");
		if (expandedItemIds.isEmpty() && !levelExpandOn) {
			dataSet = loadData(currentOffset + 1, currentOffset + pageLength, tableConfig.getLevelNo(), GtnFrameworkCommonStringConstants.STRING_EMPTY);
			grid.getTreeData().clear();
			grid.getTreeData().addItems(null, dataSet.getRows());
			addExpandIcon(grid.getTreeData(), dataSet.getRows());
			grid.getDataProvider().refreshAll();
		} else {
			List<GtnWsRecordBean> childRows = null;
			findLastExpandedHierarchy(currentOffset);
			int lastRowno = GridUtils.getTableIndex(lastExpandedItem);
			int childCount = GridUtils.getChildCount(lastExpandedItem);
			int offset = pageNumber == 0 ? 0 : (pageLength * pageNumber) - lastRowno+1;
			if ((lastRowno + childCount) > currentOffset) {

				int itemToFetch = childCount+1 - offset;
				int limit = itemToFetch > pageLength ? pageLength : itemToFetch;
				childRows = fetchChildren(offset, limit, lastExpandedItem);
			}
			paintCurrentPage(childRows);
		}
	}

	private void paintCurrentPage(List<GtnWsRecordBean> childRows) {
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
			fetchNextNItems(GridUtils.getLevelIndex(lastExpandedItem) + 1, itemsToFetch, treeData,
					GridUtils.getLevelNo(lastExpandedItem), GridUtils.getHierarchyNo(lastExpandedItem), false);

		} else {
			treeData.addItems(null, childRows);
		}
		getTreeDataProvider().refreshAll();
	}

	/***
	 * reloads the first page
	 */
	public void paintFirstPage() {
		setPageNoFieldValue(0);
		TreeData<GtnWsRecordBean> treeData = grid.getTreeData();
		treeData.clear();
		fetchNextNItems(0, pageLength, treeData, tableConfig.getLevelNo(), GtnFrameworkCommonStringConstants.STRING_EMPTY, true);
	}

	private void fetchNextNItems(int strt, int itemsToFetch, TreeData<GtnWsRecordBean> treeData, int cLevel,
			String hierNo, boolean isFirst) {
            int currentLevel=cLevel;
              int start=strt;
		while (currentLevel >= tableConfig.getLevelNo() && itemsToFetch >= fetched) {
			start = findStart(start, isFirst, currentLevel,fetched,itemsToFetch);
                        
                        String hierarchyNo=hierNo.isEmpty() ? hierNo : hierNo.substring(0, hierNo.lastIndexOf('.'));
                        String levelHier=lastExpandedItemHierarchy.get(currentLevel-1)==null
                                ?hierarchyNo:GridUtils.getHierarchyNo(lastExpandedItemHierarchy.get(currentLevel-1));
			List<GtnWsRecordBean> rows = loadData(start, itemsToFetch, currentLevel,
					currentLevel == tableConfig.getLevelNo() ? GtnFrameworkCommonStringConstants.STRING_EMPTY:levelHier).getRows();
			currentLevel--;
			fetchRowsRecursively(null, rows, treeData, itemsToFetch);
		}
		fetched = 0;
		getTreeDataProvider().refreshAll();
	}

	private int findStart(int s, boolean isFirst, int currentLevel,int fetched,int itemsToFetch) {
            int start=s;
		if (!isFirst) {
			if (tableConfig.getLevelNo() == GridUtils.getLevelNo(lastExpandedItem)) {
				int childCount = GridUtils.getChildCount(lastExpandedItem);
				int offset = pageNumber == 0 ? 0
						: (pageLength * pageNumber) - GridUtils.getTableIndex(lastExpandedItem) - childCount;
				if (offset > 0)
					start = start + offset;
			} else {
                         start = findStartOffset(fetched, itemsToFetch, currentLevel);
			}
		}
		return start;
	}

    private int findStartOffset(int fetched1, int itemsToFetch, int currentLevel) {
        int result;
        if (fetched1 == 0 && itemsToFetch==pageLength) {
            int i = lastExpandedItemHierarchy.size();
            int expandedCount = 0;
            while (i>= currentLevel) {
                expandedCount += GridUtils.getChildCount(lastExpandedItemHierarchy.get(i--)) ;
            }
            int currentOffset = pageNumber * pageLength;
            int levelIndex=GridUtils.getLevelIndex(lastExpandedItemHierarchy.get(currentLevel));//2
            result = currentOffset- (levelIndex+ expandedCount);
            result= levelIndex+result;
        } else {
            result = GridUtils.getLevelIndex(lastExpandedItemHierarchy.get(currentLevel)) + 1;
        }
        return result;
    }

	private void fetchRowsRecursively(GtnWsRecordBean root, List<GtnWsRecordBean> rows,
			TreeData<GtnWsRecordBean> treeData, int itemsToFetch) {
		for (GtnWsRecordBean parent : rows) {
			if (fetched >= itemsToFetch) {
				break;
			}
			fetched++;
			int tableIndex = (pageNumber * pageLength) + fetched;
			parent.addAdditionalProperties(5, tableIndex);
			treeData.addItem(root, parent);

			if (GridUtils.hasChildren(parent)) {
				int childCount = GridUtils.getChildCount(parent);
				if (isExpanded(parent) || (levelExpandOn && GridUtils.getLevelNo(parent) <= levelExpandNo)) {
					fetchRowsRecursively(parent, fetchChildren(0, childCount, parent), treeData, itemsToFetch);
					getTreeDataProvider().refreshAll();
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

		Optional<Integer> last = expandedItemIds.stream()
				.filter(e -> (GridUtils.getLevelNo(e) == levelNo || levelNo == 0)
						&& GridUtils.getTableIndex(e) <= currentOffset)
				.map(GridUtils::getTableIndex).max(Integer::compareTo);
		if (last.isPresent()) {
			expandedItemIds.stream().filter(e -> GridUtils.getTableIndex(e) == last.get()).findFirst()
					.ifPresent(e -> tempBean = e);
		}
		lastExpandedItemHierarchy.put(GridUtils.getLevelNo(tempBean), tempBean);
		return tempBean;
	}

	@SuppressWarnings("unchecked")
	private void initalizeColumnController() {
		columnsPerPage.setValue(10);
		setTotalColumns(tableConfig.getVisibleColumns().size()/10);
		
		setColumnPageNumber(0);
	}

	public HorizontalLayout getControlLayout() {
		controlLayout.setWidth("100%");
		controlLayout.setStyleName("v-report-display-pagination");
		pageCountLabel = new TextField();
		pageCountLabel.setWidth("45px");
		pageCountLabel.setReadOnly(true);
		pageCountLabel.setStyleName(GtnFrameworkCommonStringConstants.REPORT_DISPLAY_PAGINATION_LABEL);
		pageNoField.setWidth("45px");
		pageNoField.setStyleName(GtnFrameworkCommonStringConstants.REPORT_DISPLAY_PAGINATION_LABEL);
		setPageNoFieldValue(0);
		controlLayout.addComponent(new Label("Items per page:"));
		controlLayout.addComponent(getItemsPerPage());
		controlLayout.addComponent(getControlLayoutButtons("<<", e -> this.setPageNumber(0), Boolean.FALSE));
		controlLayout.addComponent(getControlLayoutButtons("<", e -> this.previousPage(), Boolean.TRUE));
		controlLayout.addComponent(new Label("Page No:"));
		controlLayout.addComponent(pageNoField);
		controlLayout.addComponent(new Label("/"));
		controlLayout.addComponent(pageCountLabel);
		controlLayout.addComponent(getControlLayoutButtons(">", e -> this.nextPage(), Boolean.FALSE));
		controlLayout.addComponent(
				getControlLayoutButtons(">>", e -> this.setPageNumber(this.getPageCount() - 1), Boolean.TRUE));
		pageNoField.addBlurListener(e -> setPageNumber((Integer.parseInt(pageNoField.getValue())) - 1));
		columnController();
		return controlLayout;
	}

	private Button getControlLayoutButtons(String caption, Button.ClickListener listener, Boolean flag) {
		Button button = new Button(caption, listener);
		if (flag) {
			button.setStyleName("link");
			button.addStyleName("v-report-display-pagination-button");
			return button;
		} else {
			button.setStyleName("link");
			button.addStyleName("v-report-display-pagination-button-top");
		}
		return button;
	}

	@SuppressWarnings("unchecked")
	private Component getItemsPerPage() {
		itemsPerPage.setItems(pages);
		itemsPerPage.setSelectedItem(tableConfig.getPageLength());
		itemsPerPage.setWidth("65px");
		itemsPerPage.setStyleName(GtnFrameworkCommonStringConstants.REPORT_DISPLAY_PAGINATION_LABEL);
		itemsPerPage.setEmptySelectionAllowed(false);
		itemsPerPage.addValueChangeListener(e -> setPageLength((int) itemsPerPage.getValue()));
		return itemsPerPage;
	}

	@SuppressWarnings("unchecked")
	private Component getColumnsPerPageComponenet() {
		columnsPerPage.setItems(pages);
		columnsPerPage.setSelectedItem(10);
		columnsPerPage.setWidth("65px");
		columnsPerPage.setStyleName(GtnFrameworkCommonStringConstants.REPORT_DISPLAY_PAGINATION_LABEL);
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
		columnPageNoField.setWidth("45px");
		columnPageNoField.setStyleName(GtnFrameworkCommonStringConstants.REPORT_DISPLAY_PAGINATION_LABEL);
		controlLayout.addComponent(new Label("Columns per page:"));
		controlLayout.addComponent(getColumnsPerPageComponenet());
		controlLayout.addComponent(getControlLayoutButtons("<<", e -> setColumnPageNumber(0), Boolean.FALSE));
		controlLayout
				.addComponent(getControlLayoutButtons("<", e -> setColumnPageNumber(columnPageNumber-1), Boolean.TRUE));
		controlLayout.addComponent(new Label("Columns Page No:"));
		controlLayout.addComponent(columnPageNoField);
		controlLayout.addComponent(new Label("/"));
		totalColumns.setWidth("45px");
		totalColumns.setStyleName(GtnFrameworkCommonStringConstants.REPORT_DISPLAY_PAGINATION_LABEL);
		controlLayout.addComponent(totalColumns);
		controlLayout.addComponent(
				getControlLayoutButtons(">", e -> setColumnPageNumber(columnPageNumber+1), Boolean.FALSE));
		controlLayout.addComponent(
				getControlLayoutButtons(">>", e -> setColumnPageNumber(getTotalPageCount()- 1), Boolean.TRUE));
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
			Notification.show(newPageLength + "Illegal page length.");
		}

		if (pageLength != newPageLength && count != 0 && getTreeDataProvider() != null) {
			pageLength = newPageLength;
			paintCurrentPage();
			
		}
	}

	/**
	 * Sets the current page number.
	 *
	 * @param newPageNumber
	 *            the desired page
	 */
	public void setPageNumber(int newPageNumber) {

		if (newPageNumber >= 0 && newPageNumber <= getPageCount()) {
			pageNumber = newPageNumber;
			setPageNoFieldValue(newPageNumber);
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
		tempBean = null;
		levelExpandOn = false;
		levelExpandNo = 1;
		expandedItemIds.clear();
		expandedRowIds.clear();
	}

	public int getPageCount() {
		int lastPage = count / pageLength;
		return count % pageLength == 0 ? lastPage : lastPage + 1;
	}

	public int getTotalPageCount() {
		int columnCount = tableConfig.getVisibleColumns().size();
		int columnsPerCount = getColumnsPerPage();
		int lastPage = columnCount / columnsPerCount;
		return columnCount % columnsPerCount == 0 ? lastPage : lastPage + 1;
		
	}

	public int getTotalColumns() {
		return GridUtils.getInt(totalColumns.getValue());
	}
	public void setTotalColumns(int t) {
		gtnlogger.info("cc"+t);
		totalColumns.setReadOnly(false);
		totalColumns.setValue(Integer.toString(t<=0?1:t));
		totalColumns.setReadOnly(true);
	}

	
        public int getColumnsPerPage() {
		return GridUtils.getInt(columnsPerPage.getValue());
	}

	public void setColumnPageNumber(int newPageNumber) {
		int newPageNumberValue = newPageNumber;
		if(getTotalPageCount()<=newPageNumberValue) {
			return;
		}
		if (newPageNumber < 0) {
			columnPageNumber = 0;
			newPageNumberValue = 0;
		}
		columnPageNoField.setValue(Integer.toString(newPageNumberValue + 1));
		columnPageNumber = newPageNumberValue;
		int start = columnPageNumber == 0 ? 0 : (getColumnsPerPage()-1)* columnPageNumber;
		int end =  getColumnsPerPage();
		HeaderUtils.configureGridColumns(start, end <= 0 ? 10 : end, this);
		setTotalColumns(getTotalPageCount());

	}

	public int getColumnPageNumber() {
		return columnPageNumber;
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
		int lastPage = count / pageLength;
		int pageCount= count % pageLength == 0 ? lastPage : lastPage + 1;
		pageCountLabel.setValue(Integer.toString(pageCount));
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

	public TreeDataProvider<GtnWsRecordBean> getTreeDataProvider() {
		return (TreeDataProvider<GtnWsRecordBean>) grid.getDataProvider();
	}

}
