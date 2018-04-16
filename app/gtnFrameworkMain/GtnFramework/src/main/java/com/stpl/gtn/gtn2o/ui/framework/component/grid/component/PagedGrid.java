package com.stpl.gtn.gtn2o.ui.framework.component.grid.component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.bean.DataSet;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.bean.Row;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.config.PagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.service.FetchData;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.data.HasValue;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.components.grid.HeaderRow;

import java.util.stream.Collectors;

public class PagedGrid {
	GtnWSLogger gtnlogger = GtnWSLogger.getGTNLogger(PagedGrid.class);
	PagedTableConfig tableConfig;
	int count;
	private int pageLength = 10;
	private int pageNumber = 0;
	private DataSet dataSet;
	private Label pageCountLabel;
	GtnUIFrameworkPagedTableConfig gtnUIFrameworkPagedTableConfig;
	Grid<Row> grid;
	HorizontalLayout controlLayout;
	private TextField pageNoField;

	public PagedGrid(PagedTableConfig tableConfig, GtnUIFrameworkPagedTableConfig gtnUIFrameworkPagedTableConfig) {
		this.tableConfig = tableConfig;
		this.gtnUIFrameworkPagedTableConfig = gtnUIFrameworkPagedTableConfig;
		grid = new Grid<>();
		int i = 0;
		for (String column : tableConfig.getVisibleColumns()) {
			grid.addColumn(row -> row.getValue(column)).setCaption(tableConfig.getColumnHeaders().get(i))
					.setId(String.valueOf(gtnUIFrameworkPagedTableConfig.getTableColumnMappingId()[i]));
			i++;
		}
		setFilterToGrid();
	}

	public void refreshGrid() {
		count = getTotalCount();
		dataSet = loadData((pageNumber * pageLength), pageLength);
		// int i = 0;
		// grid.removeAllColumns();
		// for (String column : tableConfig.getVisibleColumns()) {
		// grid.addColumn(row ->
		// row.getValue(column)).setCaption(tableConfig.getColumnHeaders().get(i)).setId(tableConfig.getColumnHeaders().get(i));
		// i++;
		// }
		if (dataSet.getRows() != null)
			grid.setItems(dataSet.getRows());

		pageCountLabel.setCaption(String.valueOf(getPageCount()));
	}

	private int getTotalCount() {

		if (tableConfig.getQueryBean().getCountQuery() != null) {
			List<Object[]> result = FetchData.fetchResult(appendFilter(tableConfig.getQueryBean().getCountQuery()),
					tableConfig.getQueryBean().getCountQueryInputs());
			return result == null || result.isEmpty() ? 0 : Integer.parseInt(String.valueOf(result.get(0)[0]));
		}
		return 0;

	}

	private DataSet loadData(int offset, int limit) {
		List<Object> input = PagedTreeGrid.addRangeInInput(tableConfig.getQueryBean().getDataQueryInputs(), offset,
				limit);
		List<Row> rows = FetchData.fetchResultAsRow(appendFilter(tableConfig.getQueryBean().getDataQuery()),
				input.toArray());
		return new DataSet(tableConfig.getVisibleColumns().stream().collect(Collectors.toList()), rows);
	}

	public void nextPage() {
		System.out.println("next page->" + (pageNumber + 1));
		if (pageNumber + 1 < getPageCount()) {
			setPageNoFieldValue(++pageNumber);

			refreshGrid();
		}
	}

	/**
	 * Moves to previous page, if previous page exists.
	 */
	public void previousPage() {
		if ((pageNumber - 1) >= 0) {
			setPageNoFieldValue(--pageNumber);
			refreshGrid();
		}
	}

	/**
	 * Sets the page length.
	 */
	public void setPageLength(int newPageLength) {
		if (newPageLength <= 0) {
			throw new IllegalArgumentException("Illegal page length.");
		}
		if (pageLength != newPageLength) {
			pageNumber = 0;
			setPageNoFieldValue(pageNumber);
			pageLength = newPageLength;
			refreshGrid();
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
			refreshGrid();
		} else {
			throw new IllegalArgumentException("Illegal page number.");
		}
	}

	int getPageCount() {
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

	public Grid<Row> getGrid() {
		return grid;
	}

	public void setGrid(Grid<Row> grid) {
		this.grid = grid;
	}

	public HorizontalLayout getControlLayout() {
		if (controlLayout == null) {
			controlLayout = new HorizontalLayout();
			pageNoField = new TextField();
			pageCountLabel = new Label("1");
			pageNoField.setWidth("50px");
			setPageNoFieldValue(0);
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
		}
		return controlLayout;
	}

	private Button getControlLayoutButtons(String caption, ClickListener listener) {
		Button button = new Button(caption, listener);
		button.setStyleName("link");
		return button;
	}

	private Component getItemsPerPage() {
		ComboBox itemsPerPage = new ComboBox("Items per page:");
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

	private void setFilterToGrid() {
		HeaderRow filterRow = grid.appendHeaderRow();
		Component vaadinComponent = null;
		Object[] filterColumnIdList = gtnUIFrameworkPagedTableConfig.getTableColumnMappingId();
		for (Object column : filterColumnIdList) {
			vaadinComponent = getCustomFilterComponent(String.valueOf(column));
			filterRow.getCell(String.valueOf(column)).setComponent(vaadinComponent);
		}
	}

	String appendFilter(String query) {
		String filter = "";
		// int i = 0;
		String condition = "AND";
		for (Map.Entry<String, Object> entry : tableConfig.getFilterValueMap().entrySet()) {
			String key = getDBColumnName(entry.getKey());
			Object value = entry.getValue();

			filter += condition + " " + key + "  like '%" + value + "%'";
			// i++;
		}

		return query.replace("@filter", filter);
	}

	private String getDBColumnName(String key) {
		HashMap<String, String> dbColumnMap = new HashMap<>();
		dbColumnMap.put("hierName", "c.HIERARCHY_NAME");
		dbColumnMap.put("highestLevel", "a.LEVEL_NO");
		dbColumnMap.put("lowestLevel", "b.LEVEL_NO");
		dbColumnMap.put("createdDate", "c.CREATED_DATE");
		dbColumnMap.put("modifiedDate", "c.MODIFIED_DATE");

		return dbColumnMap.get(key);
	}

	private void onFilterTextChange(HasValue.ValueChangeEvent<String> event) {
		tableConfig.getFilterValueMap().put(event.getComponent().getId(), event.getValue());
		refreshGrid();
	}

	public void onFilterDateChange(HasValue.ValueChangeEvent<LocalDate> event) {
		tableConfig.getFilterValueMap().put(event.getComponent().getId(), event.getValue());
		refreshGrid();
	}

	private Component getCustomFilterComponent(String property) {
		try {
			GtnUIFrameworkPagedTableCustomFilterConfig filterConfig = gtnUIFrameworkPagedTableConfig
					.getCustomFilterConfigMap().get(property);
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
				dateFilterPopupButton.setWidth("400px");
				DateFilterPopup dateFilterpopup = new DateFilterPopup(dateFilterPopupButton);
				Window window = dateFilterpopup.getDateFilterPopup();				
				dateFilterPopupButton.addClickListener(new Button.ClickListener() {
					@Override
					public void buttonClick(Button.ClickEvent event) {

						window.setPosition(event.getClientX(), event.getClientY());
						UI.getCurrent().addWindow(window);
					}
				});
				return dateFilterPopupButton;
			}

		} catch (GtnFrameworkGeneralException exception) {
			gtnlogger.error("Exception while creating the filter component", exception);
		}

		return null;
	}

	void setPageNoFieldValue(int pageNo) {
		pageNoField.setValue(String.valueOf(pageNo + 1));
	}

	public void setData(Object data) {
		grid.setData(data);
	}

	public PagedTableConfig getTableConfig() {
		return tableConfig;
	}

	public void setTableConfig(PagedTableConfig tableConfig) {
		this.tableConfig = tableConfig;
	}

}
