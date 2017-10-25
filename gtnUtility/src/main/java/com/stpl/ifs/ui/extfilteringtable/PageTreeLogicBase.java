/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.ifs.ui.extfilteringtable;

import com.stpl.ifs.ui.util.GtnSmallHashMap;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HeaderUtils;
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
import com.vaadin.ui.Tree;
import com.vaadin.ui.themes.Reindeer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.asi.ui.extfilteringtable.paged.ExtPagedFilterControlConfig;
import org.asi.ui.extfilteringtable.paged.ExtPagedTableChangeEvent;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 * The Class PageTreeLogicBase.
 *
 * @author Abhiram
 */
 public abstract class PageTreeLogicBase implements TreeLogicBase{

    /** The column id to filter value. */
    protected final GtnSmallHashMap columnIdToFilterValue = new GtnSmallHashMap();
    
    /** The table list. */
    protected final List<PagedTreeTableBase> tableList = new ArrayList<PagedTreeTableBase>();
    
    /** The control table. */
    protected PagedTreeTableBase controlTable = null;

    /** The temp page length. */
    protected int tempPageLength = NumericConstants.FIFTEEN;
    
    /** The items per page. */
    protected int itemsPerPage = NumericConstants.FIFTEEN;
    
    /** The temp items per page. */
    protected int tempItemsPerPage = NumericConstants.FIFTEEN;
    
    /** The is sink. */
    protected boolean isSink = true;
    
    /** The config. */
    protected ExtPagedFilterControlConfig config = new ExtPagedFilterControlConfig();
    
    /** The current page text field. */
    protected TextField currentPageTextField = new TextField();
    
    /** The total pages label. */
    protected Label totalPagesLabel = new Label(
            String.valueOf(getTotalAmountOfPages()), ContentMode.HTML);

    /** The first. */
    protected final Button first = new Button(config.getFirst());

    /** The previous. */
    protected final Button previous = new Button(config.getPrevious());

    /** The next. */
    protected final Button next = new Button(config.getNext());

    /** The last. */
    protected final Button last = new Button(config.getLast());

    /** The record count. */
    protected double recordCount;

    /** The tree level map list. */
    protected Map<String, LevelMap> treeLevelMapList = new HashMap<String, LevelMap>();

    /** The last parent. */
    protected Object lastParent = new Object();

    /** The current page. */
    protected int currentPage = 1;
    
    /** The mannual current page. */
    protected int mannualCurrentPage = 1;
    
    /** The is link. */
    protected boolean isLink = true;
    
    /** The data count. */
    protected int dataCount = 0;
    
    /** The expand collapse progress. */
    protected boolean expandCollapseProgress = false;
    
    /** The current page progress. */
    protected boolean currentPageProgress = false;
    
    /** The value change listener. */
    protected Map<PagedTreeTableBase, Property.ValueChangeListener> valueChangeListener = new HashMap<PagedTreeTableBase, Property.ValueChangeListener>();
    
    /** The expand listener. */
    protected Tree.ExpandListener expandListener = getExpandListener();
    
    /** The collapse listener. */
    protected Tree.CollapseListener collapseListener = getCollapseListener();
    
    /** The sort by columns. */
    private List<SortByColumn> sortByColumns = new ArrayList<SortByColumn>();

    /** The added filters. */
    private Set<Container.Filter> addedFilters=new HashSet<Container.Filter>();
    /**
     * Expand logic.
     *
     * @param object the object
     * @param isManualLevel the is manual level
     */
    protected abstract void expandLogic(Object object, boolean isManualLevel);

    /**
     * Collapse logic.
     *
     * @param object the object
     * @param isManualLevel the is manual level
     */
    protected abstract void collapseLogic(Object object, boolean isManualLevel);
    
    /**
     * Load current page.
     *
     * @param recordPos the record pos
     */
    protected abstract void loadCurrentPage(int recordPos);
    
    /** The first item index. */
    private int firstItemIndex=0;
    
    /**
     * Sets the column id to filter value.
     *
     * @param prop the prop
     * @param value the value
     */
    @Override
    public void setColumnIdToFilterValue(Object prop, Object value) {
        if ("".equals(String.valueOf(value))) {
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
    @Override
    public GtnSmallHashMap getColumnIdToFilterMap() {
        return columnIdToFilterValue;
    }

    /**
     * Sets the column id to filter map.
     *
     * @param columnIdToFilterValue the column id to filter value
     */
    @Override
    public void setColumnIdToFilterMap(GtnSmallHashMap columnIdToFilterValue) {
        this.columnIdToFilterValue.clear();
        this.columnIdToFilterValue.putAll(columnIdToFilterValue);
    }

    /**
     * Gets the column id to filter value.
     *
     * @param prop the prop
     * @return the column id to filter value
     */
    @Override
    public Object getColumnIdToFilterValue(Object prop) {
        return columnIdToFilterValue.get(prop);
    }

    /**
     * Adds the table.
     *
     * @param table the table
     * @return true, if successful
     */
    @Override
    public boolean addTable(PagedTreeTableBase table) {
            if ((table != null) && ((!getTables().contains(table)) && (getTables().add(table)))) {
                    for (PagedTreeTableBase tbl : getTables()) {
                        setValueChangeListener(tbl);
                    }
                    return true;
            }
        return false;
    }

    /**
     * Sets the value change listener.
     *
     * @param table the new value change listener
     */
    protected void setValueChangeListener(final PagedTreeTableBase table) {
        Property.ValueChangeListener ob = new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                for (PagedTreeTableBase tbl : getTables()) {
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
    protected void removeValueChangeListener(final PagedTreeTableBase table) {
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
    @Override
    public boolean removeTable(PagedTreeTableBase table) {
            if ((table != null) && ((getTables().contains(table)) && (getTables().remove(table)))) {
                    removeValueChangeListener(table);
                    for (PagedTreeTableBase tbl : getTables()) {
                        setValueChangeListener(tbl);
                    }
                    if ((controlTable != null) && (table.equals(controlTable) && getTables().size() > 0)) {
                            setControlTable(getTables().get(0));
                    }
                    return true;

            }
        return false;
    }

    /**
     * Sets the tables.
     *
     * @param tableList the table list
     * @return true, if successful
     */
    @Override
    public boolean setTables(List<PagedTreeTableBase> tableList) {
        if (tableList != null) {
            for (PagedTreeTableBase tbl : valueChangeListener.keySet()) {
                removeValueChangeListener(tbl);
            }
            getTables().clear();
            if (getTables().addAll(tableList)) {
                if (getControlTable() != null) {
                    if (!(getTables()).contains(getControlTable())) {
                        getTables().add(getControlTable());
                    }
                } else if (getTables().size() > 0) {
                    setControlTable(getTables().get(0));
                }
                for (PagedTreeTableBase tbl : getTables()) {
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
    @Override
    public List<PagedTreeTableBase> getTables() {
        return tableList;
    }

    /**
     * Sets the control table.
     *
     * @param controlTable the new control table
     */
    @Override
    public void setControlTable(PagedTreeTableBase controlTable) {
        if (getTables().contains(controlTable)) {
            if (getControlTable() != null) {
                getControlTable().removeExpandListener(expandListener);
                getControlTable().removeCollapseListener(collapseListener);
            }
            this.controlTable = controlTable;
            getControlTable().addExpandListener(expandListener);
            getControlTable().addCollapseListener(collapseListener);
        }
    }

    /**
     * Gets the expand listener.
     *
     * @return the expand listener
     */
    private Tree.ExpandListener getExpandListener() {
        return new Tree.ExpandListener() {
            @Override
            public void nodeExpand(Tree.ExpandEvent event) {                               
                updateOnExpand(event.getItemId(), false);                
            }
        };
    }

    /**
     * Gets the collapse listener.
     *
     * @return the collapse listener
     */
    private Tree.CollapseListener getCollapseListener() {
        return new Tree.CollapseListener() {
            @Override
            public void nodeCollapse(Tree.CollapseEvent event) {                
                updateOnCollapse(event.getItemId(), false);                
            }
        };
    }

    /**
     * Gets the control table.
     *
     * @return the control table
     */
    @Override
    public PagedTreeTableBase getControlTable() {
        return controlTable;
    }

    /**
     * The listener interface for receiving pageChange events.
     * The class that is interested in processing a pageChange
     * event implements this interface, and the object created
     * with that class is registered with a component using the
     * component's <code>addPageChangeListener<code> method. When
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
     * The Class LevelMap.
     */
    protected class LevelMap {

        /** The start. */
        private int start = 0;
        
        /** The end. */
        private int end = 0;
        
        /** The page length. */
        private int pageLength = 0;
        
        /** The page. */
        private int page = 1;
        
        /** The count. */
        private int count = 0;
        
        /** The index. */
        private int index = 0;
        
        /** The column id to filter value. */
        private GtnSmallHashMap columnIdToFilterValue;

        /**
         * Instantiates a new level map.
         *
         * @param count the count
         * @param columnIdToFilterValue the column id to filter value
         */
        public LevelMap(int count, GtnSmallHashMap columnIdToFilterValue) {
            this.count = count;
            this.columnIdToFilterValue = new GtnSmallHashMap();
        }
        
        /**
         * Instantiates a new level map.
         *
         * @param index the index
         * @param count the count
         * @param columnIdToFilterValue the column id to filter value
         */
        public LevelMap(int index, int count, GtnSmallHashMap columnIdToFilterValue) {
            this.index = index;
            this.count = count;
            this.columnIdToFilterValue = new GtnSmallHashMap();
        }

        /**
         * Instantiates a new level map.
         *
         * @param start the start
         * @param end the end
         * @param page the page
         * @param pageLength the page length
         * @param index the index
         * @param columnIdToFilterValue the column id to filter value
         */
        public LevelMap(int start, int end, int page, int pageLength, int index, GtnSmallHashMap columnIdToFilterValue) {
            this.start = start;
            this.end = end;
            this.page = page;
            this.pageLength = pageLength;
            this.index = index;
            this.columnIdToFilterValue = new GtnSmallHashMap();
        }

        /**
         * Instantiates a new level map.
         */
        public LevelMap() {
        }

        /**
         * Gets the start.
         *
         * @return the start
         */
        public int getStart() {
            return start;
        }

        /**
         * Sets the start.
         *
         * @param start the new start
         */
        public void setStart(int start) {
            this.start = start;
        }

        /**
         * Gets the end.
         *
         * @return the end
         */
        public int getEnd() {
            return end;
        }

        /**
         * Sets the end.
         *
         * @param end the new end
         */
        public void setEnd(int end) {
            this.end = end;
        }

        /**
         * Gets the page.
         *
         * @return the page
         */
        public int getPage() {
            return page;
        }

        /**
         * Sets the page.
         *
         * @param page the new page
         */
        public void setPage(int page) {
            this.page = page;
        }

        /**
         * Gets the count.
         *
         * @return the count
         */
        public int getCount() {
            return count;
        }

        /**
         * Sets the count.
         *
         * @param count the new count
         */
        public void setCount(int count) {
            this.count = count;
        }

        /**
         * Gets the page length.
         *
         * @return the page length
         */
        public int getPageLength() {
            return pageLength;
        }

        /**
         * Sets the page length.
         *
         * @param pageLength the new page length
         */
        public void setPageLength(int pageLength) {
            this.pageLength = pageLength;
        }

        /**
         * Gets the index.
         *
         * @return the index
         */
        public int getIndex() {
            return index;
        }

        /**
         * Sets the index.
         *
         * @param index the new index
         */
        public void setIndex(int index) {
            this.index = index;
        }

        /**
         * Gets the column id to filter value.
         *
         * @return the column id to filter value
         */
        public GtnSmallHashMap getColumnIdToFilterValue() {
            return columnIdToFilterValue;
        }

        /**
         * Sets the column id to filter value.
         *
         * @param columnIdToFilterValue the column id to filter value
         */
        public void setColumnIdToFilterValue(GtnSmallHashMap columnIdToFilterValue) {
            this.columnIdToFilterValue = columnIdToFilterValue;
        }

    }

    /**
     * Gets the temp page length.
     *
     * @return the temp page length
     */
    @Override
    public int getTempPageLength() {
        return tempPageLength;
    }

    /**
     * Sets the temp page length.
     *
     * @param tempPageLength the new temp page length
     */
    @Override
    public void setTempPageLength(int tempPageLength) {
        this.tempPageLength = tempPageLength;
    }

    /**
     * Gets the control config.
     *
     * @return the control config
     */
    @Override
    public ExtPagedFilterControlConfig getControlConfig() {
        return config;
    }
    
    /** The listeners. */
    private List<PageChangeListener> listeners = null;

    /** The container. */
    protected Container container;

    /**
     * Creates the controls.
     *
     * @return the horizontal layout
     */
    @Override
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

        itemsPerPageSelect.setImmediate(true);
        itemsPerPageSelect.select(NumericConstants.FIFTEEN);
        itemsPerPageSelect.setNullSelectionAllowed(false);
        itemsPerPageSelect.setWidth("70px");
        itemsPerPageSelect.addValueChangeListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = -2255853716069800092L;

            @Override
            public void valueChange(
                    com.vaadin.data.Property.ValueChangeEvent event) {
                Property pr = event.getProperty();
                if ((pr != null) && (pr.getValue() != null)) {
                        int val = (Integer) pr.getValue();
                        if (val != getItemsPerPage()) {
                            setItemsPerPage((Integer) event.getProperty().getValue());
                            getControlTable().refreshRowCache();
                            totalPagesLabel.setValue(String.valueOf(getTotalAmountOfPages()));
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
                    if (((!isSink && x > getPageLength()) || isSink) && (!itemsPerPageSelect.containsId(x))) {
                            config.addPageLength(x);
                            configureItemPerPage(itemsPerPageSelect);
                            itemsPerPageSelect.select(x);
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

                } catch (NumberFormatException e) {
                    currentPageTextField.setValue("" + getCurrentPage());
                }
            }
        });
        pageLabel.setWidth(null);
        currentPageTextField.setColumns(NumericConstants.THREE);
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
                currentPageTextField.setValue((Integer.valueOf(currentPageTextField.getValue() + "") - 1) + "");
            }
        });
        next.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = -1927138212640638452L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                currentPageTextField.setValue((Integer.valueOf(currentPageTextField.getValue() + "") + 1) + "");
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

        itemsPerPageLabel.addStyleName(HeaderUtils.PAGEDTABLE_LABEL);
        itemsPerPageSelect.addStyleName("pagedtable-combobox");
        pageLabel.addStyleName(HeaderUtils.PAGEDTABLE_LABEL);
        currentPageTextField.addStyleName(HeaderUtils.PAGEDTABLE_LABEL);
        separatorLabel.addStyleName(HeaderUtils.PAGEDTABLE_LABEL);
        totalPagesLabel.addStyleName(HeaderUtils.PAGEDTABLE_LABEL);

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
        controlBar.setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
        controlBar.setExpandRatio(pageSize, 1);

        addListener(new PageChangeListener() {
            private boolean inMiddleOfValueChange;

            @Override
            public void pageChanged(ExtPagedTableChangeEvent event) {
                if (!inMiddleOfValueChange) {
                    inMiddleOfValueChange = true;
                    int total = tempItemsPerPage * (getCurrentPage() - 1);
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
    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * Sink item per page with page length.
     *
     * @param isSink the is sink
     */
    @Override
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
    @Override
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
    @Override
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
        first.addStyleName(HeaderUtils.PAGEDTABLE_BUTTON);
        previous.addStyleName(HeaderUtils.PAGEDTABLE_BUTTON);
        next.addStyleName(HeaderUtils.PAGEDTABLE_BUTTON);
        last.addStyleName(HeaderUtils.PAGEDTABLE_BUTTON);
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
    @Override
    public void setPageLength(int pageLength) {
        for (PagedTreeTableBase tbl : getTables()) {
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
    @Override
    public void setItemsPerPage(int itemsPerPage) {
        if (isSink) {
            for (PagedTreeTableBase tbl : getTables()) {
                tbl.setPageLength(itemsPerPage);
            }
        }
        if (itemsPerPage < getPageLength()) {
            itemsPerPage = getPageLength();
        }
        if (getItemsPerPage() != itemsPerPage) {
            this.tempItemsPerPage = getItemsPerPage();
            config.addPageLength(itemsPerPage);
            for (PagedTreeTableBase tbl : getTables()) {
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
            ExtPagedTableChangeEvent event = new ExtPagedTableChangeEvent(getControlTable());
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
    @Override
    public int getPageLength() {
        if (getControlTable() != null) {
            return getControlTable().getPageLength();
        }
        return getTempPageLength();
    }

    /**
     * Gets the total amount of pages.
     *
     * @return the total amount of pages
     */
    @Override
    public int getTotalAmountOfPages() {
        int pageCount = (int) Math.ceil(recordCount / getItemsPerPage());
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
    @Override
    public void setCurrentPage(int currentPage) {
        if (!currentPageProgress) {            
            saveCurrentPage();
            createCurrentPageStart();
            getContainerDataSource().removeAllItems();

            int recordPos = createCurrentPage(currentPage);
            if (currentPageTextField != null) {
                currentPageTextField.setValue("" + this.currentPage);
            }
            loadCurrentPage(recordPos);
            controlButtons();
            for (PagedTreeTableBase tbl : getTables()) {
                tbl.setCurrentPage(this.currentPage);
            }            
            createCurrentPageEnd();
        }
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
     * Save current page.
     */
    protected void saveCurrentPage() {
    }

    /**
     * Sets the refresh.
     *
     * @param refresh the new refresh
     */
    @Override
    public void setRefresh(boolean refresh) {
        for (PagedTreeTableBase tbl : getTables()) {
            tbl.setRefresh(refresh);
        }
    }

    /**
     * Checks if is refresh.
     *
     * @return true, if is refresh
     */
    @Override
    public boolean isRefresh() {
        return getControlTable().isRefresh();
    }

    /**
     * Sets the tree node multi click.
     *
     * @param treeMultiClick the new tree node multi click
     */
    @Override
    public void setTreeNodeMultiClick(boolean treeMultiClick) {
        for (PagedTreeTableBase tbl : getTables()) {
            tbl.setTreeNodeMultiClick(treeMultiClick);
        }
    }

    /**
     * Checks if is tree node multi click.
     *
     * @return true, if is tree node multi click
     */
    @Override
    public boolean isTreeNodeMultiClick() {
        return getControlTable().isTreeNodeMultiClick();
    }

    /**
     * Creates the current page.
     *
     * @param currentPage the current page
     * @return the int
     */
    protected int createCurrentPage(int currentPage) {
        return 0;
    }

    /**
     * Update on expand.
     *
     * @param object the object
     * @param isManualLevel the is manual level
     */
    @Override
    public void updateOnExpand(Object object, boolean isManualLevel) {        
        if (!expandCollapseProgress) {
            int cpage = getCurrentPage();
            firstItemIndex=0;
            if (getControlTable() != null) {
                firstItemIndex = getControlTable().getCurrentPageFirstItemIndex();
            }
            expandCollapseStart(true);
            expandLogic(object, isManualLevel);
            if (cpage == getCurrentPage()) {
                for (PagedTreeTableBase tbl : getTables()) {
                    tbl.setScrollIndex(firstItemIndex, false);
                }
            }
            expandCollapseEnd(true);
        }
    }

    /**
     * Update on collapse.
     *
     * @param object the object
     * @param isManualLevel the is manual level
     */
    @Override
    public void updateOnCollapse(Object object, boolean isManualLevel) {
        if (!expandCollapseProgress) {
            firstItemIndex=0;
            if (getControlTable() != null) {
                firstItemIndex = getControlTable().getCurrentPageFirstItemIndex();
            }
            expandCollapseStart(false);
            collapseLogic(object, isManualLevel);
            for (PagedTreeTableBase tbl : getTables()) {
                tbl.setScrollIndex(firstItemIndex,false);
            }             
            expandCollapseEnd(false);
        }
    }

    

    /**
     * Expand collapse start.
     *
     * @param isExpand the is expand
     */
    protected void expandCollapseStart(boolean isExpand) {

    }

    /**
     * Expand collapse end.
     *
     * @param isExpand the is expand
     */
    protected void expandCollapseEnd(boolean isExpand) {

    }

    /**
     * Sets the tables item colapsed.
     *
     * @param object the object
     * @param isCollapsed the is collapsed
     * @param includeControleTable the include controle table
     */
    protected void setTablesItemColapsed(Object object, boolean isCollapsed, boolean includeControleTable) {
        for (PagedTreeTableBase tbl : getTables()) {
            if (!tbl.equals(getControlTable())) {
                tbl.setCollapsed(object, isCollapsed);
            }
        }
        if (includeControleTable) {
            getControlTable().setCollapsed(object, isCollapsed);
        }
    }

    /**
     * Addlevel map.
     *
     * @param treeLevel the tree level
     * @param levelMap the level map
     */
    protected void addlevelMap(String treeLevel, LevelMap levelMap) {
        treeLevelMapList.put(treeLevel, levelMap);
    }

    /**
     * Removelevel map.
     *
     * @param treeLevel the tree level
     * @return the level map
     */
    protected LevelMap removelevelMap(String treeLevel) {
        return treeLevelMapList.remove(treeLevel);
    }

    /**
     * Gets the level map.
     *
     * @param treeLevel the tree level
     * @return the level map
     */
    protected LevelMap getLevelMap(String treeLevel) {
        return treeLevelMapList.get(treeLevel);
    }

    /**
     * Checks if is level map.
     *
     * @param treeLevel the tree level
     * @return true, if is level map
     */
    protected boolean isLevelMap(String treeLevel) {
        return treeLevelMapList.containsKey(treeLevel);
    }

    /**
     * Gets the level map list.
     *
     * @return the level map list
     */
    protected Map<String, LevelMap> getLevelMapList() {
        return treeLevelMapList;
    }

    /**
     * Gets the record count.
     *
     * @return the record count
     */
    @Override
    public double getRecordCount() {
        return recordCount;
    }

    /**
     * Sets the record count.
     *
     * @param recordCount the new record count
     */
    @Override
    public void setRecordCount(double recordCount) {
        this.recordCount = recordCount;
    }

    /**
     * Gets the total pages label.
     *
     * @return the total pages label
     */
    @Override
    public Label getTotalPagesLabel() {
        return totalPagesLabel;
    }

    /**
     * Sets the total pages label.
     *
     * @param totalPagesLabel the new total pages label
     */
    @Override
    public void setTotalPagesLabel(Label totalPagesLabel) {
        this.totalPagesLabel = totalPagesLabel;
    }

    /**
     * Gets the last parent.
     *
     * @return the last parent
     */
    @Override
    public Object getLastParent() {
        return lastParent;
    }

    /**
     * Sets the last parent.
     *
     * @param lastParent the new last parent
     */
    @Override
    public void setLastParent(Object lastParent) {
        this.lastParent = lastParent;
    }

    /**
     * Sets the container data source.
     *
     * @param newDataSource the new container data source
     */
    @Override
    public void setContainerDataSource(Container newDataSource) {
        if (!(newDataSource instanceof Container.Indexed)
                || !(newDataSource instanceof Container.Filterable) || !(newDataSource instanceof Container.Hierarchical)) {
            throw new IllegalArgumentException(
                    "ExtPagedFilterTreeTable can only use containers that implement Container.Indexed, Container.Filterable AND Container.Hierarchical");
        }
        container = newDataSource;
        for (PagedTreeTableBase tbl : getTables()) {
            tbl.setContainerDataSource(getContainerDataSource());

        }
        setCurrentPage(1);
    }
    
    /**
     * Gets the container data source.
     *
     * @return the container data source
     */
    @Override
    public Container getContainerDataSource() {
        return container;
    }
    
    /**
     * Gets the mannual current page.
     *
     * @return the mannual current page
     */
    @Override
    public int getMannualCurrentPage() {
        return mannualCurrentPage;
    }

    /**
     * Sets the mannual current page.
     *
     * @param mannualCurrentPage the new mannual current page
     */
    @Override
    public void setMannualCurrentPage(int mannualCurrentPage) {
        this.mannualCurrentPage = mannualCurrentPage;
    }

    /**
     * Gets the items per page.
     *
     * @return the items per page
     */
    @Override
    public int getItemsPerPage() {
        if (getControlTable() != null) {
            return getControlTable().getItemsPerPage();
        }
        return tempItemsPerPage;
    }

    /**
     * Checks if is expand collapse progress.
     *
     * @return true, if is expand collapse progress
     */
    protected boolean isExpandCollapseProgress() {
        return expandCollapseProgress;
    }

    /**
     * Sets the expand collapse progress.
     *
     * @param expandCollapseProgress the new expand collapse progress
     */
    protected void setExpandCollapseProgress(boolean expandCollapseProgress) {
        this.expandCollapseProgress = expandCollapseProgress;
    }

    /**
     * Checks if is current page progress.
     *
     * @return true, if is current page progress
     */
    protected boolean isCurrentPageProgress() {
        return currentPageProgress;
    }

    /**
     * Sets the current page progress.
     *
     * @param currentPageProgress the new current page progress
     */
    protected void setCurrentPageProgress(boolean currentPageProgress) {
        this.currentPageProgress = currentPageProgress;
    }

    /**
     * Clear level map list.
     */
    @Override
    public void clearLevelMapList() {
        treeLevelMapList.clear();
    }

    /**
     * Clear parent.
     */
    @Override
    public void clearParent() {
        lastParent = new Object();
    }
    
    /**
     * Clear container.
     */
    @Override
    public void clearContainer() {
        getContainerDataSource().removeAllItems();
    }
    
    /**
     * Clear filters.
     */
    @Override
    public void clearFilters() {
        addedFilters.clear();
        columnIdToFilterValue.clear();
    }
    
    /**
     * Clear all.
     */
    @Override
    public void clearAll() {
        clearSortByColumns();
        clearFilters();
        clearLevelMapList();
        clearParent();
        currentPage = 1;
        mannualCurrentPage = 1;
    }

    /**
     * Sets the manual current page.
     *
     * @param page the new manual current page
     */
    @Override
    public void setManualCurrentPage(int page) {
        currentPage = page;
    }
    
     /**
     * Clear Sort By Columns.
     */
    @Override
    public void clearSortByColumns() {
        sortByColumns.clear();
    }
    
    /**
     * Adds the sort by column.
     *
     * @param propertyId the property id
     * @return true, if successful
     */
    @Override
     public boolean addSortByColumn(SortByColumn propertyId){
        return sortByColumns.add(propertyId);
    }
    
    /**
     * Removes the sort by column.
     *
     * @param propertyId the property id
     * @return true, if successful
     */
    @Override
    public boolean removeSortByColumn(SortByColumn propertyId){
        return sortByColumns.remove(propertyId);
    }

    /**
     * Gets the sort by columns.
     *
     * @return the sort by columns
     */
    @Override
    public List<SortByColumn> getSortByColumns() {
        return sortByColumns;
    }

    /**
     * Sets the sort by columns.
     *
     * @param sortByColumns the new sort by columns
     */
    @Override
    public void setSortByColumns(List<SortByColumn> sortByColumns) {
        this.sortByColumns = sortByColumns;
    }
    
    /**
     * Handle filter change.
     */
    @Override
    public void handleSortByColumns() {
        setCurrentPage(getCurrentPage());
    }
    
    /**
     * Sets the filters.
     *
     * @param addedFilters the new filters
     */
    @Override
    public void setFilters(Set<Container.Filter> addedFilters){
        getFilters().clear();
        getFilters().addAll(addedFilters);
    }
    
    /**
     * Gets the filters.
     *
     * @return the filters
     */
    @Override
    public Set<Container.Filter> getFilters(){
        return addedFilters;
    }
    
    /**
     * Removes the all container filters.
     */
    @Override
    public void removeAllContainerFilters(){
        if(container instanceof Container.Filterable){
            ((Container.Filterable)container).removeAllContainerFilters();
        }
    }
    
    /**
     * Before Handle filter change.
     */
    @Override
    public void beforeHandleFilterChange(){
        
}
}
