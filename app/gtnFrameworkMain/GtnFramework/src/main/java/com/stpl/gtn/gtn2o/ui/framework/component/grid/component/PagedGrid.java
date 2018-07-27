package com.stpl.gtn.gtn2o.ui.framework.component.grid.component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.bean.DataSet;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.service.FetchData;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.data.HasValue;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.components.grid.HeaderRow;

public class PagedGrid {

    private GtnWSLogger gtnlogger = GtnWSLogger.getGTNLogger(PagedGrid.class);
    GtnUIFrameworkPagedTableConfig tableConfig;

    private GtnUIFrameworkComponentConfig componentConfig;
    private int count;
    private int pageLength = 10;
    private int pageNumber = 0;
    private DataSet dataSet;
    
    private GtnUIFrameworkPagedTableConfig gtnUIFrameworkPagedTableConfig;
    Grid<GtnWsRecordBean> grid;
    private HorizontalLayout controlLayout;
    private TextField pageNoField;
    GtnUIFrameworkPagedGridLogic pagedTableLogic;
    
    private static final String SHOW_ALL = "Show all";

    public PagedGrid(GtnUIFrameworkPagedTableConfig tableConfig, GtnUIFrameworkComponentConfig componentConfig) {
        this.tableConfig = tableConfig;
        this.componentConfig = componentConfig;
        grid = new Grid<>();
        int i = 0;

        for (Object column : tableConfig.getTableColumnMappingId()) {
            String property = column.toString();
            grid.addColumn(row -> row.getPropertyValue(property)).setCaption(tableConfig.getColumnHeaders().get(i))
                    .setId(property);

            i++;
        }
        configureCheckboxHeaderComponents(tableConfig.getTableColumnMappingId(), tableConfig.getColumnHeaders());
        pagedTableLogic = new GtnUIFrameworkPagedGridLogic(tableConfig, componentConfig);
        addFilterAndRefreshLogic(tableConfig);

    }

    private void configureCheckboxHeaderComponents(Object[] tableColumnMappingId, List<String> columnHeaders) {
        if (tableConfig.isEnableCheckBoxInGridHeader()) {
            HeaderRow mainHeader = grid.getHeaderRow(0);  
            for (int i = 0; i < tableColumnMappingId.length; i++) {
                CheckBoxGroup vaadinCheckBoxGroup = new CheckBoxGroup();
                vaadinCheckBoxGroup.setItems(columnHeaders.get(i));
                mainHeader.getCell(String.valueOf(tableColumnMappingId[i])).setComponent(vaadinCheckBoxGroup);
            }
        }
    }


    private void addFilterAndRefreshLogic(GtnUIFrameworkPagedTableConfig tableConfig) {
        if (!tableConfig.getCustomFilterConfigMap().isEmpty()) {
            setFilterToGrid();
        }
        if (tableConfig.isRefreshAtStart()) {
            refreshGrid();
        }
    }

    public void refreshGrid() {
        count = getTotalCount();
        if(tableConfig.isPaginationOff()) {
        pageLength = count;
        }
        gtnlogger.info("count------" + count);
        gtnlogger.info("pageLength------" + pageLength);
        dataSet = loadData((pageNumber * pageLength), pageLength);

        if (dataSet.getRows() != null) {

            grid.setItems(dataSet.getRows());

        }
    }

    public void addItem(GtnWsRecordBean item) {
        ListDataProvider<GtnWsRecordBean> dataProvider = (ListDataProvider) grid.getDataProvider();
        @SuppressWarnings("unchecked")
        List<GtnWsRecordBean> items = new ArrayList<>(dataProvider.getItems());
        items.add(item);
        grid.setItems(items);
    }

    public void removeItem(GtnWsRecordBean item) {
        ListDataProvider<GtnWsRecordBean> dataProvider = (ListDataProvider<GtnWsRecordBean>) grid.getDataProvider();
        List<GtnWsRecordBean> items = new ArrayList<>(dataProvider.getItems());
        items.remove(item);
        grid.setItems(items);
    }

    private int getTotalCount() {

        if (tableConfig.getCountQuery() != null) {
            List<Object[]> result = FetchData.fetchResult(appendFilter(tableConfig.getCountQuery()),
                    tableConfig.getCountQueryInputs());

            return result == null || result.isEmpty() ? 0 : Integer.parseInt(String.valueOf(result.get(0)[0]));
        } else {
            return pagedTableLogic.getCount();
        }
    }

    private DataSet loadData(int offset, int limit) {
        List<GtnWsRecordBean> rows;
        if (tableConfig.getDataQuery() != null) {

            List<Object> input = addRangeInInput(tableConfig.getDataQueryInputs(), offset, limit);
            rows = FetchData.fetchResultAsRow(tableConfig.getTableColumnMappingId(),
                    appendFilter(tableConfig.getDataQuery()), input.toArray());
        } else {
            rows = pagedTableLogic.loadData(offset,limit);
        }
        return new DataSet(Arrays.asList(tableConfig.getTableColumnMappingId()), rows);
    }

    public void nextPage() {
        gtnlogger.info("next page->" + (pageNumber + 1));
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
    	 Label pageCountLabel;
        if (controlLayout == null) {
            controlLayout = new HorizontalLayout();
            controlLayout.setSpacing(false);
            controlLayout.setMargin(false);
            HorizontalLayout pageNoFieldText = new HorizontalLayout();
            pageNoField = new TextField();
            pageCountLabel = new Label("1");
            pageNoFieldText.addStyleName(GtnFrameworkCssConstants.STPL_PADDING_TOP_10);
            pageCountLabel.addStyleName(GtnFrameworkCssConstants.STPL_PADDING_TOP_13);
            pageNoField.setWidth("50px");
            pageNoFieldText.addComponent(pageNoField);
            setPageNoFieldValue(0);
            Label itemsPerPageLabel = new Label("Items per page:");
            itemsPerPageLabel.addStyleName(GtnFrameworkCssConstants.STPL_PADDING_TOP_10);
            controlLayout.addComponent(itemsPerPageLabel);
            controlLayout.addComponent(getItemsPerPage());
           
            HorizontalLayout horizontalLayoutForPage = new HorizontalLayout();
            horizontalLayoutForPage.addComponent(getControlLayoutButtons("<<", e -> this.setPageNumber(0)));
            horizontalLayoutForPage.addComponent(getControlLayoutButtons("<", e -> this.previousPage()));
            Label pageNo = new Label("Page No:");
            pageNo.addStyleName(GtnFrameworkCssConstants.STPL_PADDING_TOP_13);
            horizontalLayoutForPage.addComponent(pageNo);
            horizontalLayoutForPage.addComponent(pageNoFieldText);
            Label slash = new Label("/");
            slash.addStyleName(GtnFrameworkCssConstants.STPL_PADDING_TOP_13);
            horizontalLayoutForPage.addComponent(slash);
            horizontalLayoutForPage.addComponent(pageCountLabel);
            horizontalLayoutForPage.addComponent(getControlLayoutButtons(">", e -> this.nextPage()));
            horizontalLayoutForPage.addComponent(getControlLayoutButtons(">>", e -> this.setPageNumber(this.getPageCount() - 1)));
            pageNoField.addBlurListener(e -> setPageNumber((Integer.parseInt(pageNoField.getValue())) - 1));
            controlLayout.addComponent(horizontalLayoutForPage);
            controlLayout.setComponentAlignment(horizontalLayoutForPage,Alignment.MIDDLE_CENTER);
           
        }
        return controlLayout;
    }

    private Button getControlLayoutButtons(String caption, ClickListener listener) {
        Button button = new Button(caption, listener);
       
        button.addStyleName(GtnFrameworkCssConstants.STPL_PADDING_TOP_13);
        button.addStyleName("link");
        return button;
    }

    private Component getItemsPerPage() {
    	HorizontalLayout comboHorizontalLayout = new HorizontalLayout();
        ComboBox itemsPerPage = new ComboBox();
        itemsPerPage.setItems(new Object[]{5, 10, 15, 20, 25, 50, 100});
        itemsPerPage.setSelectedItem(10);
        itemsPerPage.setWidth("90px");
        itemsPerPage.setEmptySelectionAllowed(false);
        itemsPerPage.addValueChangeListener(new HasValue.ValueChangeListener() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent event) {
                setPageLength((int) itemsPerPage.getValue());
            }
        });
        comboHorizontalLayout.addStyleName(GtnFrameworkCssConstants.STPL_PADDING_TOP_10);
        comboHorizontalLayout.addComponent(itemsPerPage);
        return comboHorizontalLayout;
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
        StringBuilder filter = new StringBuilder();
        String condition = "AND";
        for (Map.Entry<String, Object> entry : tableConfig.getFilterValueMap().entrySet()) {
            String key = getDBColumnName(entry.getKey());
            Object value = entry.getValue();
            filter.append(condition).append(" ").append(key).append("  like '%").append(value).append("%'");
        }

        return query.replace("@filter", filter.toString());
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

    private Component getCustomFilterComponent(String property) {
        try {
            GtnUIFrameworkPagedTableCustomFilterConfig filterConfig = tableConfig.getCustomFilterConfigMap()
                    .get(property);
            if (filterConfig.getGtnComponentType() == GtnUIFrameworkComponentType.TEXTBOX_VAADIN8) {
            	return getTextFieldFilterComponent(property);
            }  if (filterConfig.getGtnComponentType() == GtnUIFrameworkComponentType.DATEFIELDVAADIN8) {
            	return getDateFieldFilterComponent(property);
            }  if (filterConfig.getGtnComponentType() == GtnUIFrameworkComponentType.COMBOBOX_VAADIN8) {
            	return getComboboxFilterComponent(property, filterConfig);
            }  if (filterConfig.getGtnComponentType() == GtnUIFrameworkComponentType.CALENDAR_FIELD) {
                return getCalendarFieldFilterComponent(property);
            }

        } catch (GtnFrameworkGeneralException exception) {
            gtnlogger.error("Exception while creating the filter component", exception);
        }

        return null;
    }

	private Component getCalendarFieldFilterComponent(String property) {
		Button dateFilterPopupButton = new Button(SHOW_ALL);
		dateFilterPopupButton.setWidth("100%");
		Window window = getDateFilterPopup(dateFilterPopupButton, property);
		dateFilterPopupButton.addClickListener(new Button.ClickListener() {
		    @Override
		    public void buttonClick(Button.ClickEvent event) {

		        window.setPosition(event.getClientX(), event.getClientY());
		        UI.getCurrent().addWindow(window);
		    }
		});

		return dateFilterPopupButton;
	}

	private Component getComboboxFilterComponent(String property,
			GtnUIFrameworkPagedTableCustomFilterConfig filterConfig) throws GtnFrameworkGeneralException {
		HorizontalLayout h3 = new HorizontalLayout();
		GtnUIFrameworkComponent component = filterConfig.getGtnComponentType().getGtnComponent();
		Component vaadinComponent = null;
		vaadinComponent = component.buildVaadinComponent(filterConfig.getGtnComponentConfig());
		ComboBox vaadinCombobox = (ComboBox) vaadinComponent;
		vaadinCombobox.setPlaceholder(SHOW_ALL);
		vaadinCombobox.setId(property);
		vaadinCombobox.addValueChangeListener(this::onFilterTextChange);
		h3.addComponent(vaadinCombobox);
		h3.addLayoutClickListener(new LayoutClickListener() {
			@Override
			public void layoutClick(LayoutClickEvent event) {
				
					if (event.getChildComponent() == vaadinCombobox) {
						vaadinCombobox.setPlaceholder("");
						}
					}
		    });
		vaadinCombobox.addBlurListener(new BlurListener(){
					@Override
					public void blur(BlurEvent event) {
						if (event.getComponent() == vaadinCombobox){
		                        String value = String.valueOf(vaadinCombobox.getItemCaptionGenerator().apply(vaadinCombobox.getValue())).trim();
							if(value.equals(""))
								vaadinCombobox.setPlaceholder(SHOW_ALL);
						}
					} 
		    });         
		    return h3;
	}

	private Component getDateFieldFilterComponent(String property) {
		HorizontalLayout h2 = new HorizontalLayout();
		h2.setMargin(false);
		h2.setWidth("118%");
		DateField dateField = new DateField();
		dateField.setPlaceholder(SHOW_ALL);
		dateField.setWidth("114%");
		dateField.setSizeFull();
		dateField.setId(property);
		dateField.addValueChangeListener(this::onFilterDateChange);
		h2.addComponent(dateField);
		h2.addLayoutClickListener(new LayoutClickListener() {
			@Override
			public void layoutClick(LayoutClickEvent event) {
				
					if (event.getChildComponent() == dateField) {
						dateField.setPlaceholder("");
						}
					}
		    });
		dateField.addBlurListener(new BlurListener(){
					@Override
					public void blur(BlurEvent event) {
						if (event.getComponent() == dateField){
		                        LocalDate localDate = dateField.getValue();
							Optional<LocalDate> opt = Optional.ofNullable(localDate);
							if(!opt.isPresent())
								dateField.setPlaceholder(SHOW_ALL);
						}
					} 
		    });         
		    return h2;
	}

	private Component getTextFieldFilterComponent(String property) {
		HorizontalLayout hl = new HorizontalLayout();
		hl.setMargin(false);
		hl.setWidth("105%");
		TextField textFieldFilter = new TextField();
		textFieldFilter.setPlaceholder(SHOW_ALL);
		textFieldFilter.setWidth("118%");
		textFieldFilter.setId(property);
		textFieldFilter.addValueChangeListener(this::onFilterTextChange);
		
		hl.addComponent(textFieldFilter);
		
		hl.addLayoutClickListener(new LayoutClickListener() {
		@Override
		public void layoutClick(LayoutClickEvent layoutClickEvent) {
			
				if (layoutClickEvent.getChildComponent() == textFieldFilter) {
					textFieldFilter.setPlaceholder("");
					}
				}
		});
		textFieldFilter.addBlurListener(new BlurListener(){
				@Override
				public void blur(BlurEvent blurEvent) {
					if (blurEvent.getComponent() == textFieldFilter){
		                    String value = textFieldFilter.getValue();
						if(value.equals(""))
							textFieldFilter.setPlaceholder(SHOW_ALL);
					}
				} 
		});         
		return hl;
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

    private void onFilterTextChange(HasValue.ValueChangeEvent<String> event) {
        tableConfig.getFilterValueMap().put(event.getComponent().getId(), getFilterValueForEventChange(event));
        refreshGrid();
    }

	private String getFilterValueForEventChange(HasValue.ValueChangeEvent<String> event) {
		return "%"+event.getValue()+"%";
	}

    public void onFilterDateChange(HasValue.ValueChangeEvent<LocalDate> event) {
        tableConfig.getFilterValueMap().put(event.getComponent().getId(), event.getValue());
        refreshGrid();
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

    public Window getDateFilterPopup(Button dateFilterPopupButton, String property) {
        Window window = new Window();

        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        HorizontalLayout horizontalLayoutForButton = new HorizontalLayout();
        horizontalLayout.setMargin(true);

        InlineDateField inlineDateFieldStartDate = getDateField("Start date:", "inlineDateFieldStartDate");
        InlineDateField inlineDateFieldEndDate = getDateField("End date:", "inlineDateFieldEndDate");

        Button setButton = getButton("Set", "setButton", window, dateFilterPopupButton, property,
                inlineDateFieldStartDate, inlineDateFieldEndDate);
        Button clearButton = getButton("Clear", "clearButton", window, dateFilterPopupButton, property,
                inlineDateFieldStartDate, inlineDateFieldEndDate);

        horizontalLayout.addComponents(inlineDateFieldStartDate, inlineDateFieldEndDate);
        horizontalLayoutForButton.addComponents(setButton, clearButton);
        verticalLayout.addComponents(horizontalLayout, horizontalLayoutForButton);

        window.setContent(verticalLayout);
        window.setResizable(false);
        window.setClosable(false);
        return window;
    }

    private void addValueChangeListenerForDate(InlineDateField inlineDateField) {
        inlineDateField.addValueChangeListener(new ValueChangeListener<LocalDate>() {

            @Override
            public void valueChange(ValueChangeEvent<LocalDate> event) {
                if (inlineDateField.getId().equals("inlineDateFieldStartDate")) {
                    inlineDateField.setData(event.getValue());
                } else {
                    inlineDateField.setData(event.getValue());
                }
            }
        });
    }

    private void addClickListenerForButton(Button button, Window window, Button buttonFromGrid, String property,
            InlineDateField inlineDateFieldStartDate, InlineDateField inlineDateFieldEndDate) {
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                String startDate = "";
                String endDate = "";
                window.close();
                if (button.getId().equals("setButton")) {
                    if (inlineDateFieldStartDate.getData() != null && inlineDateFieldEndDate.getData() != null) {
                        startDate = inlineDateFieldStartDate.getData().toString();
                        endDate = inlineDateFieldEndDate.getData().toString();

                        buttonFromGrid.setCaption(startDate + " - " + endDate);

                        tableConfig.getFilterValueMap().put(property, startDate + " - " + endDate);
                        refreshGrid();
                    }
                    if (inlineDateFieldStartDate.getData() != null && inlineDateFieldEndDate.getData() == null) {
                        startDate = inlineDateFieldStartDate.getData().toString();
                    }
                    if (inlineDateFieldStartDate.getData() == null && inlineDateFieldEndDate.getData() != null) {
                        endDate = inlineDateFieldEndDate.getData().toString();
                    }
                    buttonFromGrid.setCaption(startDate + " - " + endDate);
                    tableConfig.getFilterValueMap().put(property, startDate + " - " + endDate);
                    refreshGrid();
                } else {
                    buttonFromGrid.setCaption(SHOW_ALL);
                    tableConfig.getFilterValueMap().put(property, buttonFromGrid.getCaption());
                    refreshGrid();
                }
            }
        });
    }

    private InlineDateField getDateField(String caption, String id) {
        InlineDateField inlineDateField = new InlineDateField(caption);
        inlineDateField.setId(id);
        inlineDateField.setValue(LocalDate.now());
        inlineDateField.setLocale(Locale.US);
        addValueChangeListenerForDate(inlineDateField);
        return inlineDateField;
    }

    private Button getButton(String caption, String id, Window window, Button dateFilterPopupButton, String property,
            InlineDateField inlineDateFieldStartDate, InlineDateField inlineDateFieldEndDate) {
        Button button = new Button(caption);
        button.setId(id);
        addClickListenerForButton(button, window, dateFilterPopupButton, property, inlineDateFieldStartDate,
                inlineDateFieldEndDate);
        return button;
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

    
}
