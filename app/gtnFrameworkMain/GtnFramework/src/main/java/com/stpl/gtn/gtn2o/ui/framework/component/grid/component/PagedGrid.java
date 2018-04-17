package com.stpl.gtn.gtn2o.ui.framework.component.grid.component;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.bean.DataSet;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.service.FetchData;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.data.HasValue;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.components.grid.HeaderRow;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PagedGrid {

    GtnWSLogger gtnlogger = GtnWSLogger.getGTNLogger(PagedGrid.class);
    GtnUIFrameworkPagedTableConfig tableConfig;
    int count;
    private int pageLength = 10;
    private int pageNumber = 0;
    private DataSet dataSet;
    Grid<GtnWsRecordBean> grid;
    HorizontalLayout controlLayout;
    private TextField pageNoField;
    
   GtnUIFrameworkPagedGridLogic pagedTableLogic;
    public PagedGrid(GtnUIFrameworkPagedTableConfig tableConfig) {
        this.tableConfig = tableConfig;
        grid = new Grid<>();
        int i = 0;
        for (Object column : tableConfig.getTableColumnMappingId()) {
            String  property=column.toString();
            grid.addColumn(row -> row.getPropertyValue(property)).setCaption(tableConfig.getColumnHeaders().get(i)).setId(property);
            i++;
        }
        setFilterToGrid();
        pagedTableLogic=new GtnUIFrameworkPagedGridLogic(tableConfig);
    }

    public void refreshGrid() {
        count = getTotalCount();
        dataSet = loadData((pageNumber * pageLength), pageLength);
//		int i = 0;
//		grid.removeAllColumns();
//		for (String column : tableConfig.getVisibleColumns()) {
//			grid.addColumn(row -> row.getValue(column)).setCaption(tableConfig.getColumnHeaders().get(i)).setId(tableConfig.getColumnHeaders().get(i));
//			i++;
//		}
        if (dataSet.getRows() != null) {
            grid.setItems(dataSet.getRows());
        }
    }

    private int getTotalCount() {

        if (tableConfig.getCountQuery() != null) {
            List<Object[]> result = FetchData.fetchResult(appendFilter(tableConfig.getCountQuery()),
                    tableConfig.getCountQueryInputs());
            return result == null || result.isEmpty() ? 0 : Integer.parseInt(String.valueOf(result.get(0)[0]));
        }else{
            	pagedTableLogic.getCount();
        }
        return 0;

    }

    private DataSet loadData(int offset, int limit) {
           List<GtnWsRecordBean> rows ;
        if (tableConfig.getDataQuery() != null) {
      
        List<Object> input = PagedTreeGrid.addRangeInInput(tableConfig.getDataQueryInputs(), offset,
                limit);
        rows = FetchData.fetchResultAsRow(tableConfig.getTableColumnMappingId(), appendFilter(tableConfig.getDataQuery()), input.toArray());
        }else{
         rows=   pagedTableLogic.loadData(limit, offset);
        }
        return new DataSet(Arrays.asList(tableConfig.getTableColumnMappingId()), rows);
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
     * @param newPageNumber the desired page
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

    public Grid<GtnWsRecordBean> getGrid() {
        return grid;
    }

    public void setGrid(Grid<GtnWsRecordBean> grid) {
        this.grid = grid;
    }

    public HorizontalLayout getControlLayout() {
        if (controlLayout == null) {
            controlLayout = new HorizontalLayout();
            pageNoField = new TextField();
            pageNoField.setWidth("50px");
            setPageNoFieldValue(0);
            controlLayout.addComponent(getItemsPerPage());
            controlLayout.addComponent(getControlLayoutButtons("<<", e -> this.setPageNumber(0)));
            controlLayout.addComponent(getControlLayoutButtons("<", e -> this.previousPage()));
            controlLayout.addComponent(new Label("Page No:"));
            controlLayout.addComponent(pageNoField);
            controlLayout.addComponent(new Label("/" + count / pageLength));
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

    private void setFilterToGrid() {
        HeaderRow filterRow = grid.appendHeaderRow();
        Component vaadinComponent = null;
        Object[] filterColumnIdList = tableConfig.getTableColumnMappingId();
        for (Object column : filterColumnIdList) {
            vaadinComponent = getCustomFilterComponent(String.valueOf(column));
            filterRow.getCell(String.valueOf(column)).setComponent(vaadinComponent);
        }
    }

    String appendFilter(String query) {
        String filter = "";
        int i = 0;
        for (Map.Entry<String, Object> entry : tableConfig.getFilterValueMap().entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            String condition = i == 0 ? "WHERE" : "AND";
            filter += condition + " A." + key + "  like '%" + value + "%'";
            i++;
        }

        return query.replace("@filter", filter);
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
            GtnUIFrameworkPagedTableCustomFilterConfig filterConfig = tableConfig
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

    public GtnUIFrameworkPagedTableConfig getTableConfig() {
        return tableConfig;
    }

    public void setTableConfig(GtnUIFrameworkPagedTableConfig tableConfig) {
        this.tableConfig = tableConfig;
    }


    public Set<GtnWsRecordBean> getValue() {
        return grid.getSelectedItems();
    }

    public GtnUIFrameworkPagedGridLogic getPagedTableLogic() {
        return pagedTableLogic;
    }

    public void setPagedTableLogic(GtnUIFrameworkPagedGridLogic pagedTableLogic) {
        this.pagedTableLogic = pagedTableLogic;
    }
    
}
