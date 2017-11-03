/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.asi.ui.extfilteringtable.paged.logic;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.converter.StringToIntegerConverter;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.Reindeer;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.asi.ui.extfilteringtable.paged.ExtPagedFilterControlConfig;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTableChangeEvent;

/**
 * The Class PageTableLogic.
 *
 * @author Abhiram
 */
public abstract class PageTableLogic {

    /**
     * The column id to filter value.
     */
    protected final Map<Object, Object> columnIdToFilterValue = new HashMap<Object, Object>();
    /**
     * The table list.
     */
    protected final List<ExtPagedTable> tableList = new ArrayList<ExtPagedTable>();
    /**
     * The temp page length.
     */
    protected int tempPageLength = 15;
    /**
     * The items per page.
     */
    protected int itemsPerPage = 15;
    /**
     * The temp items per page.
     */
    protected int tempItemsPerPage = 15;
    /**
     * The is sink.
     */
    protected boolean isSink = true;
    /**
     * The config.
     */
    protected ExtPagedFilterControlConfig config = new ExtPagedFilterControlConfig();
    /**
     * The current page text field.
     */
    protected TextField currentPageTextField = new TextField();
    /**
     * The total pages label.
     */
    protected Label totalPagesLabel = new Label(
            String.valueOf(getTotalAmountOfPages()), ContentMode.HTML);
    /**
     * The first.
     */
    protected final Button first = new Button(config.getFirst());
    /**
     * The previous.
     */
    protected final Button previous = new Button(config.getPrevious());
    /**
     * The next.
     */
    protected final Button next = new Button(config.getNext());
    /**
     * The last.
     */
    protected final Button last = new Button(config.getLast());
    /**
     * The record count.
     */
    protected double recordCount = 0;
    /**
     * The current page.
     */
    protected int currentPage = 1;
    /**
     * The is link.
     */
    protected boolean isLink = true;
    /**
     * The required count.
     */
    protected boolean requiredCount = true;
    /**
     * The value change listener.
     */
    protected Map<ExtPagedTable, Property.ValueChangeListener> valueChangeListener = new HashMap<ExtPagedTable, Property.ValueChangeListener>();
    
    /** The sort by columns. */
    private List<SortByColumn> sortByColumns = new ArrayList<SortByColumn>();
    
    /** The added filters. */
    private Set<Container.Filter> addedFilters=new HashSet<Container.Filter>();

    /**
     * Gets the count.
     *
     * @return the count
     */
    abstract public int getCount();

    /**
     * Load data.
     *
     * @param start the start
     * @param offset the offset
     * @return the list
     */
    abstract public List loadData(int start, int offset);

    /**
     * Configure container.
     *
     * @param object the object
     * @param container the container
     * @return the object
     */
    abstract public Object configureContainer(Object object, Container container);

    /**
     * Sets the column id to filter value.
     *
     * @param prop the prop
     * @param value the value
     */
    public void setColumnIdToFilterValue(Object prop, Object value) {
        if(value==null){
            value="";
        }
        if (value.toString().equals("")) {
            if (columnIdToFilterValue.containsKey(prop)) {
                columnIdToFilterValue.remove(prop);
            }
        } else {
            columnIdToFilterValue.put(prop, value);
        }
    }

    /**
     * Gets the column id to filter map.
     *
     * @return the column id to filter map
     */
    public Map<Object, Object> getColumnIdToFilterMap() {
        return columnIdToFilterValue;
    }

    /**
     * Sets the column id to filter map.
     *
     * @param columnIdToFilterValue the column id to filter value
     */
    public void setColumnIdToFilterMap(Map<Object, Object> columnIdToFilterValue) {
        this.columnIdToFilterValue.clear();
        this.columnIdToFilterValue.putAll(columnIdToFilterValue);
    }

    /**
     * Gets the column id to filter value.
     *
     * @param prop the prop
     * @return the column id to filter value
     */
    public Object getColumnIdToFilterValue(Object prop) {
        return columnIdToFilterValue.get(prop);
    }

    /**
     * Adds the table.
     *
     * @param table the table
     * @return true, if successful
     */
    public boolean addTable(ExtPagedTable table) {
        if (table != null) {
            if (!getTables().contains(table)) {
                if (getTables().add(table)) {
                    for (ExtPagedTable tbl : getTables()) {
                        setValueChangeListener(tbl);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Sets the value change listener.
     *
     * @param table the new value change listener
     */
    protected void setValueChangeListener(final ExtPagedTable table) {
        Property.ValueChangeListener ob = new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                for (ExtPagedTable tbl : getTables()) {
                    if (!table.equals(tbl)) {
                        tbl.setValue(table.getValue());
                    }
                }
            }
        };
        removeValueChangeListener(table);
        table.addValueChangeListener(ob);
        valueChangeListener.put(table, ob);
    }

    /**
     * Removes the value change listener.
     *
     * @param table the table
     */
    protected void removeValueChangeListener(final ExtPagedTable table) {
        if (valueChangeListener.containsKey(table)) {
            table.removeValueChangeListener(valueChangeListener.get(table));
        }
        valueChangeListener.remove(table);
    }

    /**
     * Removes the table.
     *
     * @param table the table
     * @return true, if successful
     */
    public boolean removeTable(ExtPagedTable table) {
        if (table != null) {
            if (getTables().contains(table)) {
                if (getTables().remove(table)) {
                    removeValueChangeListener(table);
                    for (ExtPagedTable tbl : getTables()) {
                        setValueChangeListener(tbl);
                    }
                    return true;
                }

            }
        }
        return false;
    }

    /**
     * Sets the tables.
     *
     * @param tableList the table list
     * @return true, if successful
     */
    public boolean setTables(List<ExtPagedTable> tableList) {
        if (tableList != null) {
            for (ExtPagedTable tbl : valueChangeListener.keySet()) {
                removeValueChangeListener(tbl);
            }
            getTables().clear();
            if (getTables().addAll(tableList)) {
                for (ExtPagedTable tbl : getTables()) {
                    setValueChangeListener(tbl);
                }
                return true;
            }

        }
        return false;
    }

    /**
     * Gets the tables.
     *
     * @return the tables
     */
    public List<ExtPagedTable> getTables() {
        return tableList;
    }

    /**
     * Save current page.
     */
    public void saveCurrentPage() {
        
    }

    /**
     * The listener interface for receiving pageChange events. The class that is
     * interested in processing a pageChange event implements this interface,
     * and the object created with that class is registered with a component
     * using the component's
     * <code>addPageChangeListener<code> method. When
     * the pageChange event occurs, that object's appropriate
     * method is invoked.
     *
     * @see PageChangeEvent
     */
    public interface PageChangeListener extends Serializable {

        /**
         * Page changed.
         *
         * @param event the event
         */
        public void pageChanged(ExtPagedTableChangeEvent event);
    }

    /**
     * Gets the temp page length.
     *
     * @return the temp page length
     */
    public int getTempPageLength() {
        return tempPageLength;
    }

    /**
     * Sets the temp page length.
     *
     * @param tempPageLength the new temp page length
     */
    public void setTempPageLength(int tempPageLength) {
        this.tempPageLength = tempPageLength;
    }

    /**
     * Gets the control config.
     *
     * @return the control config
     */
    public ExtPagedFilterControlConfig getControlConfig() {
        return config;
    }
    /**
     * The listeners.
     */
    private List<PageChangeListener> listeners = null;
    /**
     * The container.
     */
    protected Container container;

    /**
     * Creates the controls.
     *
     * @return the horizontal layout
     */
    public HorizontalLayout createControls() {
        Label itemsPerPageLabel = new Label(config.getItemsPerPage(),
                ContentMode.HTML);

        final ComboBox itemsPerPageSelect = new ComboBox();

        Label pageLabel = new Label(config.getPage(), ContentMode.HTML);

        Label separatorLabel = new Label("&nbsp;/&nbsp;", ContentMode.HTML);

        HorizontalLayout controlBar = new HorizontalLayout();

        HorizontalLayout pageSize = new HorizontalLayout();

        HorizontalLayout pageManagement = new HorizontalLayout();
        itemsPerPageLabel.setSizeUndefined();
        configureItemPerPage(itemsPerPageSelect);
        itemsPerPageSelect.setStyleName(Reindeer.TEXTFIELD_SMALL);
        itemsPerPageSelect.setImmediate(true);
        itemsPerPageSelect.select(15);
        itemsPerPageSelect.setNullSelectionAllowed(false);
        itemsPerPageSelect.setWidth("70px");
        itemsPerPageSelect.addValueChangeListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = -2255853716069800092L;

            @Override
            public void valueChange(
                    com.vaadin.data.Property.ValueChangeEvent event) {
                Property pr = event.getProperty();
                if (pr != null) {
                    if (pr.getValue() != null) {
                        int val = (Integer) pr.getValue();
                        if (val != getItemsPerPage()) {
                            setItemsPerPage((Integer) event.getProperty().getValue());
                            for (ExtPagedTable tbl : getTables()) {
                                tbl.refreshRowCache();
                            }
                            totalPagesLabel.setValue(String.valueOf(getTotalAmountOfPages()));
                        }
                    }
                }

            }
        });
        itemsPerPageSelect.setNewItemsAllowed(true);
        itemsPerPageSelect.setNewItemHandler(new AbstractSelect.NewItemHandler() {
            @Override
            public void addNewItem(String newItemCaption) {
                try {
                    int x = Integer.valueOf(newItemCaption);
                    if ((!isSink && x > getPageLength()) || isSink) {
                        if (!itemsPerPageSelect.containsId(x)) {
                            config.addPageLength(x);
                            configureItemPerPage(itemsPerPageSelect);
                            itemsPerPageSelect.select(x);
                        }
                    }
                } catch (NumberFormatException e) {
                    itemsPerPageSelect.select(getItemsPerPage());
                }
            }
        });
        if (itemsPerPageSelect.containsId(getItemsPerPage())) {
            itemsPerPageSelect.select(getItemsPerPage());
        } else {
            itemsPerPageSelect.select(itemsPerPageSelect.getItemIds()
                    .iterator().next());
        }
        currentPageTextField.setValue(String.valueOf(getCurrentPage()));
        currentPageTextField.setConverter(new StringToIntegerConverter());

        currentPageTextField.setStyleName(Reindeer.TEXTFIELD_SMALL);
        currentPageTextField.setImmediate(true);
        currentPageTextField.addValueChangeListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = -2255853716069800092L;

            @Override
            public void valueChange(
                    com.vaadin.data.Property.ValueChangeEvent event) {
                try {
                 if (event.getProperty().getValue() != null && !"null".equals(event.getProperty().getValue())) { // Added for GAL-8160
                     String val=event.getProperty().getValue().toString();
                    val=val.trim().replace(" ", "").replace(",", "");
                    int cpage = Integer.valueOf(val);
                    if (cpage >= 1 && cpage <= getTotalAmountOfPages()) {
                        if (getCurrentPage() != cpage) {
                            setCurrentPage(cpage);
                        }
                    } else {
                        currentPageTextField.setValue("" + getCurrentPage());
                    }
                } else {
                     currentPageTextField.setValue("" + getCurrentPage());
                 }
                } catch (NumberFormatException e) {
                    currentPageTextField.setValue("" + getCurrentPage());
                }
            }
        });
        pageLabel.setWidth(null);
        currentPageTextField.setColumns(3);
        separatorLabel.setWidth(null);
        totalPagesLabel.setWidth(null);

        first.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = -355520120491283992L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                currentPageTextField.setValue("1");
            }
        });
        previous.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = -355520120491283992L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                String val = currentPageTextField.getValue();
                val = val.trim().replace(" ", "").replace(",", "");
                int cpage = Integer.valueOf(val);
                currentPageTextField.setValue((cpage - 1) + "");
            }
        });
        next.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = -1927138212640638452L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                String val = currentPageTextField.getValue();
                val = val.trim().replace(" ", "").replace(",", "");
                int cpage = Integer.valueOf(val);
                currentPageTextField.setValue((cpage + 1) + "");
            }
        });
        last.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = -355520120491283992L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                currentPageTextField.setValue(String.valueOf(getTotalAmountOfPages()));
            }
        });
        setControlButtonAsLink(isLink);
        itemsPerPageLabel.addStyleName("pagedtable-itemsperpagecaption");
        itemsPerPageSelect.addStyleName("pagedtable-itemsperpagecombobox");
        pageLabel.addStyleName("pagedtable-pagecaption");
        currentPageTextField.addStyleName("pagedtable-pagefield");
        separatorLabel.addStyleName("pagedtable-separator");
        totalPagesLabel.addStyleName("pagedtable-total");

        itemsPerPageLabel.addStyleName("pagedtable-label");
        itemsPerPageSelect.addStyleName("pagedtable-combobox");
        pageLabel.addStyleName("pagedtable-label");
        currentPageTextField.addStyleName("pagedtable-label");
        separatorLabel.addStyleName("pagedtable-label");
        totalPagesLabel.addStyleName("pagedtable-label");

        pageSize.addComponent(itemsPerPageLabel);
        pageSize.addComponent(itemsPerPageSelect);
        pageSize.setComponentAlignment(itemsPerPageLabel, Alignment.MIDDLE_LEFT);
        pageSize.setComponentAlignment(itemsPerPageSelect,
                Alignment.MIDDLE_LEFT);
        pageSize.setSpacing(true);
        pageManagement.addComponent(first);
        pageManagement.addComponent(previous);
        pageManagement.addComponent(pageLabel);
        pageManagement.addComponent(currentPageTextField);
        pageManagement.addComponent(separatorLabel);
        pageManagement.addComponent(totalPagesLabel);
        pageManagement.addComponent(next);
        pageManagement.addComponent(last);
        pageManagement.setComponentAlignment(first, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(previous, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(pageLabel, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(currentPageTextField,
                Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(separatorLabel,
                Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(totalPagesLabel,
                Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(next, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(last, Alignment.MIDDLE_LEFT);
        pageManagement.setWidth(null);
        pageManagement.setSpacing(true);
        controlBar.addComponent(pageSize);
        controlBar.addComponent(pageManagement);
        controlBar.setComponentAlignment(pageManagement,
                Alignment.MIDDLE_CENTER);
        controlBar.setWidth(100, Sizeable.Unit.PERCENTAGE);
        controlBar.setExpandRatio(pageSize, 1);

        addListener(new PageChangeListener() {
            private boolean inMiddleOfValueChange;

            @Override
            public void pageChanged(ExtPagedTableChangeEvent event) {
                if (!inMiddleOfValueChange) {
                    inMiddleOfValueChange = true;
                    int total = (tempItemsPerPage * (getCurrentPage() - 1));
                    if (total == 0) {
                        total = 1;
                    }
                    int page = (total / getItemsPerPage()) + 1;
                    setCurrentPage(page);
                    currentPageTextField.setValue(String
                            .valueOf(page));
                    totalPagesLabel.setValue(Integer
                            .toString(getTotalAmountOfPages()));
                    Integer i = getItemsPerPage();
                    Item a = itemsPerPageSelect.getItem(i);
                    if (a == null) {
                        config.addPageLength(i);
                        configureItemPerPage(itemsPerPageSelect);
                    }
                    itemsPerPageSelect.select(getItemsPerPage());
                    inMiddleOfValueChange = false;
                }
            }
        });
        controlButtons();
        return controlBar;
    }

    /**
     * Configure item per page.
     *
     * @param itemsPerPageSelect the items per page select
     */
    private void configureItemPerPage(ComboBox itemsPerPageSelect) {
        itemsPerPageSelect.removeAllItems();
        for (Integer i : config.getPageLengths()) {
            itemsPerPageSelect.addItem(i);
            itemsPerPageSelect.setItemCaption(i, String.valueOf(i));
        }
    }

    /**
     * Gets the current page.
     *
     * @return the current page
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * Sink item per page with page length.
     *
     * @param isSink the is sink
     */
    public void sinkItemPerPageWithPageLength(boolean isSink) {
        if (isSink) {
            setItemsPerPage(getPageLength());
        }
        this.isSink = isSink;
    }

    /**
     * Checks if is sink item per page with page length.
     *
     * @return true, if is sink item per page with page length
     */
    public boolean isSinkItemPerPageWithPageLength() {
        return isSink;
    }

    /**
     * Adds the listener.
     *
     * @param listener the listener
     */
    private void addListener(PageChangeListener listener) {
        if (listeners == null) {
            listeners = new ArrayList<PageChangeListener>();
        }
        listeners.add(listener);
    }

    /**
     * Control buttons.
     */
    protected void controlButtons() {
        String s = "" + getCurrentPage();
        if (s.equals("1")) {
            first.setEnabled(false);
            previous.setEnabled(false);
        } else {
            first.setEnabled(true);
            previous.setEnabled(true);
        }
        String s1 = String.valueOf(getTotalAmountOfPages());
        if (s.equals(s1)) {
            next.setEnabled(false);
            last.setEnabled(false);
        } else {
            next.setEnabled(true);
            last.setEnabled(true);
        }
    }

    /**
     * Sets the control button as link.
     *
     * @param isLink the new control button as link
     */
    public void setControlButtonAsLink(boolean isLink) {
        this.isLink = isLink;
        if (isLink) {
            first.setStyleName(Reindeer.BUTTON_LINK);
            previous.setStyleName(Reindeer.BUTTON_LINK);
            next.setStyleName(Reindeer.BUTTON_LINK);
            last.setStyleName(Reindeer.BUTTON_LINK);
        } else {
            first.setStyleName(Reindeer.BUTTON_DEFAULT);
            previous.setStyleName(Reindeer.BUTTON_DEFAULT);
            next.setStyleName(Reindeer.BUTTON_DEFAULT);
            last.setStyleName(Reindeer.BUTTON_DEFAULT);
        }
        first.addStyleName("pagedtable-button");
        previous.addStyleName("pagedtable-button");
        next.addStyleName("pagedtable-button");
        last.addStyleName("pagedtable-button");
        first.addStyleName("pagedtable-first");
        previous.addStyleName("pagedtable-previous");
        next.addStyleName("pagedtable-next");
        last.addStyleName("pagedtable-last");
    }

    /**
     * Sets the page length.
     *
     * @param pageLength the new page length
     */
    public void setPageLength(int pageLength) {
        for (ExtPagedTable tbl : getTables()) {
            tbl.setPageLength(pageLength);
        }
        if (isSink) {
            setItemsPerPage(pageLength);
        }
    }

    /**
     * Sets the items per page.
     *
     * @param itemsPerPage the new items per page
     */
    public void setItemsPerPage(int itemsPerPage) {
        if (isSink) {
            for (ExtPagedTable tbl : getTables()) {
                tbl.setPageLength(itemsPerPage);
            }
        }
        if (itemsPerPage < getPageLength()) {
            itemsPerPage = getPageLength();
        }
        if (getItemsPerPage() != itemsPerPage) {
            this.tempItemsPerPage = getItemsPerPage();
            config.addPageLength(itemsPerPage);
            for (ExtPagedTable tbl : getTables()) {
                tbl.setItemsPerPage(itemsPerPage);
            }
        }
        firePagedChangedEvent();
    }

    /**
     * Fire paged changed event.
     */
    private void firePagedChangedEvent() {
        if (listeners != null) {
            ExtPagedTableChangeEvent event = new ExtPagedTableChangeEvent(getTables().get(0));
            for (PageChangeListener listener : listeners) {
                listener.pageChanged(event);
            }
        }
    }

    /**
     * Gets the page length.
     *
     * @return the page length
     */
    public int getPageLength() {
        if (!getTables().isEmpty()) {
            return getTables().get(0).getPageLength();
        }
        return getTempPageLength();
    }

    /**
     * Gets the total amount of pages.
     *
     * @return the total amount of pages
     */
    public int getTotalAmountOfPages() {
        int pageCount = (int) Math.ceil(getRecordCount() / getItemsPerPage());
        if (pageCount < 1) {
            pageCount = 1;
        }
        return pageCount;
    }

    /**
     * Sets the current page.
     *
     * @param currentPage the new current page
     */
    public void setCurrentPage(int currentPage) {
        saveCurrentPage();
        createCurrentPageStart();
        container.removeAllItems();
        createCurrentPage(currentPage);
        if (currentPageTextField != null) {
            currentPageTextField.setValue("" + getCurrentPage());
        }
        if (getRecordCount() != 0) {
            loadCurrentPage();
        }
        controlButtons();
        for (ExtPagedTable tbl : getTables()) {
            tbl.setCurrentPage(getCurrentPage());
        }
        createCurrentPageEnd();
    }
    /**
     * Creates the current page start.
     */
    protected void createCurrentPageStart() {
    }

    /**
     * Creates the current page end.
     */
    protected void createCurrentPageEnd() {
    }

    /**
     * Load current page.
     */
    private void loadCurrentPage() {
        int start = getItemsPerPage() * (getCurrentPage() - 1);
        int need = (int) (getRecordCount() - start);
        int offset = need > getItemsPerPage() ? getItemsPerPage() : need;
        if (offset > 0) {
            loadDataContainer(start, offset);
        }
    }

    /**
     * Load data container.
     *
     * @param start the start
     * @param offset the offset
     */
    private void loadDataContainer(int start, int offset) {
        try {
            List list = loadData(start, offset);
            if (list != null) {
                for (int i = 0; i < list.size() && i < offset; i++) {
                    Object itemid = list.get(i);
                    Object itemIds = configureContainer(itemid, container);
                }
            }
        } catch (Property.ReadOnlyException ex) {
            ex.printStackTrace();
        } catch (UnsupportedOperationException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Sets the refresh.
     *
     * @param refresh the new refresh
     */
    public void setRefresh(boolean refresh) {
        for (ExtPagedTable tbl : getTables()) {
            tbl.setRefresh(refresh);
        }
    }

    /**
     * Checks if is refresh.
     *
     * @return true, if is refresh
     */
    public boolean isRefresh() {
        return getTables().get(0).isRefresh();
    }

    /**
     * Checks if is required count.
     *
     * @return true, if is required count
     */
    public boolean isRequiredCount() {
        return requiredCount;
    }

    /**
     * Sets the required count.
     *
     * @param requiredCount the new required count
     */
    public void setRequiredCount(boolean requiredCount) {
        this.requiredCount = requiredCount;
    }

    /**
     * Creates the current page.
     *
     * @param currentPage the current page
     */
    protected void createCurrentPage(int currentPage) {
        if (isRequiredCount()) {
            try {
                setRecordCount(getCount());
                setRequiredCount(false);
            } catch (Exception ex) {
                Logger.getLogger(ContainerLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        int x = getTotalAmountOfPages();
        totalPagesLabel.setValue(String.valueOf(x));
        if (currentPage <= x) {
            this.currentPage = currentPage;
        } else if (currentPage <= 0) {
            this.currentPage = 1;
        } else {
            this.currentPage = x;
        }
    }

    /**
     * Gets the total pages label.
     *
     * @return the total pages label
     */
    public Label getTotalPagesLabel() {
        return totalPagesLabel;
    }

    /**
     * Sets the total pages label.
     *
     * @param totalPagesLabel the new total pages label
     */
    public void setTotalPagesLabel(Label totalPagesLabel) {
        this.totalPagesLabel = totalPagesLabel;
    }

    /**
     * Sets the container data source.
     *
     * @param newDataSource the new container data source
     */
    public void setContainerDataSource(Container newDataSource) {
        if (!(newDataSource instanceof Container.Indexed)
                || !(newDataSource instanceof Container.Filterable)) {
            throw new IllegalArgumentException(
                    "ExtPagedFilterTable can only use containers that implement Container.Indexed, Container.Filterable AND Container.Hierarchical");
        }
        container = newDataSource;
        for (ExtPagedTable tbl : getTables()) {
            tbl.setContainerDataSource(container);

        }
        setCurrentPage(1);
    }

    /**
     * Gets the items per page.
     *
     * @return the items per page
     */
    public int getItemsPerPage() {
        if (!getTables().isEmpty()) {
            return getTables().get(0).getItemsPerPage();
        }
        return tempItemsPerPage;
    }

    /**
     * Clear container.
     */
    public void clearContainer() {
        container.removeAllItems();
    }

    /**
     * Clear filters.
     */
    public void clearFilters() {
        columnIdToFilterValue.clear();
    }

    /**
     * Clear Sort By Columns.
     */
    public void clearSortByColumns() {
        sortByColumns.clear();
    }
    /**
     * Clear all.
     */
    public void clearAll() {
        clearSortByColumns();
        clearFilters();
        clearContainer();
        currentPage = 1;
    }
    
    /**
     * Adds the sort by column.
     *
     * @param propertyId the property id
     * @return true, if successful
     */
    public boolean addSortByColumn(SortByColumn propertyId){
        return sortByColumns.add(propertyId);
    }
    
    /**
     * Removes the sort by column.
     *
     * @param propertyId the property id
     * @return true, if successful
     */
    public boolean removeSortByColumn(SortByColumn propertyId){
        return sortByColumns.remove(propertyId);
    }

    /**
     * Gets the sort by columns.
     *
     * @return the sort by columns
     */
    public List<SortByColumn> getSortByColumns() {
        return sortByColumns;
    }

    /**
     * Sets the sort by columns.
     *
     * @param sortByColumns the new sort by columns
     */
    public void setSortByColumns(List<SortByColumn> sortByColumns) {
        this.sortByColumns = sortByColumns;
    }
    
    /**
     * Gets the record count.
     *
     * @return the record count
     */
    protected double getRecordCount() {
        return recordCount;
    }

    /**
     * Sets the record count.
     *
     * @param recordCount the new record count
     */
    protected void setRecordCount(double recordCount) {
        this.recordCount = recordCount;
    }
    
    /**
     * Removes the all container filters.
     */
    public void removeAllContainerFilters(){
        if(container instanceof Container.Filterable){
            ((Container.Filterable)container).removeAllContainerFilters();
        }
    }
    /**
     * Before Handle filter change.
     */
    public void beforeHandleFilterChange(){
        
    }
    
    
    /**
     * Handle filter change.
     */
    public void handleFilterChange() {        
        setCurrentPage(1);
    }
    /**
     * Handle filter change.
     */
    public void handleSortByColumns() {
        setCurrentPage(getCurrentPage());
    }
    
    /**
     * Sets the filters.
     *
     * @param addedFilters the new filters
     */
    public void setFilters(Set<Container.Filter> addedFilters){
        getFilters().clear();
        getFilters().addAll(addedFilters);
    }
    
    /**
     * Gets the filters.
     *
     * @return the filters
     */
    public Set<Container.Filter> getFilters(){
        return addedFilters;
    }
    
    /**
     * Gets the Container.
     *
     * @return the Container
     */
    public Container getContainerDataSource(){
        return container;
}
}
