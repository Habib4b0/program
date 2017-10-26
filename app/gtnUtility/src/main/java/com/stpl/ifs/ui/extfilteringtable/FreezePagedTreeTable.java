/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.ifs.ui.extfilteringtable;

import com.vaadin.data.Container;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.MultiSelectMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import java.util.Map;
import org.asi.ui.extfilteringtable.paged.ExtPagedFilterControlConfig;


/**
 * The Class FreezePagedTreeTable.
 *
 * @author Abhiram
 */
public class FreezePagedTreeTable extends HorizontalSplitPanel {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The logic. */
    private PageTreeTableLogic logic;
    //=========== end of fields for pagination configuration==========        

    /** The left table. */
    private ExtPagedTreeTable leftTable;
    
    /** The right table. */
    private ExtPagedTreeTable rightTable;
    
    
    
    /** The filters visible. */
    private boolean filtersVisible = false;

    /**
     * Instantiates a new freeze paged tree table.
     *
     * @param logic the logic
     */
    public FreezePagedTreeTable(PageTreeTableLogic logic) {
        this(null, logic);
    }

    /**
     * Instantiates a new freeze paged tree table.
     *
     * @param caption the caption
     * @param logic the logic
     */
    public FreezePagedTreeTable(String caption, PageTreeTableLogic logic) {
        super();
        setCaption(caption);
        this.logic = logic;
        constructLeftFreeze(false);
        constructRightFreeze(false);
        setPageLength(this.logic.getTempPageLength());
        init();
    }

    /**
     * Construct left freeze.
     *
     * @param reConstruct the re construct
     */
    public void constructLeftFreeze(boolean reConstruct) {
        if (reConstruct) {
            removeComponent(leftTable);
        }
        int pageLen = logic.getPageLength();
        int itemPerPage = logic.getItemsPerPage();
        leftTable = new ExtPagedTreeTable(this.logic);
        this.logic.setControlTable(leftTable);

        if (reConstruct) {
            leftTable.setPageLength(pageLen);
            leftTable.setItemsPerPage(itemPerPage);
            init();
        }
    }

    /**
     * Construct right freeze.
     *
     * @param reConstruct the re construct
     */
    public void constructRightFreeze(boolean reConstruct) {
        if (reConstruct) {
            removeComponent(rightTable);
        }
        int pageLen = logic.getPageLength();
        int itemPerPage = logic.getItemsPerPage();
        rightTable = new ExtPagedTreeTable(this.logic);
        rightTable.paintTreeRowIcon(false);

        if (reConstruct) {
            rightTable.setPageLength(pageLen);
            rightTable.setItemsPerPage(itemPerPage);
            init();
        }
    }

    /**
     * Inits the.
     */
    private void init() {
        setSizeFull();
        setFirstComponent(leftTable);
        setSecondComponent(rightTable);
        rightTable.setDependentLeftTable(leftTable);
        leftTable.setDependentRightTable(rightTable);
        leftTable.setWidth("100%");
        rightTable.setWidth("100%");
        setHeight(-1, Sizeable.Unit.PIXELS);
        leftTable.addStyleName("left-table");
        rightTable.addStyleName("right-table");
        addStyleName("freeze-paged-filter-tree-table");
    }

    /**
     * Sets the selectable.
     *
     * @param selectable the new selectable
     */
    public void setSelectable(boolean selectable) {
        leftTable.setSelectable(selectable);
        rightTable.setSelectable(selectable);
    }

    /**
     * Checks if is selectable.
     *
     * @return true, if is selectable
     */
    public boolean isSelectable() {
        return leftTable.isSelectable() || rightTable.isSelectable();
    }

    /**
     * Sets the multi select.
     *
     * @param multiSelect the new multi select
     */
    public void setMultiSelect(boolean multiSelect) {
        leftTable.setMultiSelect(multiSelect);
        rightTable.setMultiSelect(multiSelect);
    }

    /**
     * Checks if is multi select.
     *
     * @return true, if is multi select
     */
    public boolean isMultiSelect() {
        return leftTable.isMultiSelect() || rightTable.isMultiSelect();
    }

    /**
     * Sets the multi select mode.
     *
     * @param mode the new multi select mode
     */
    public void setMultiSelectMode(MultiSelectMode mode) {
        leftTable.setMultiSelectMode(mode);
        rightTable.setMultiSelectMode(mode);
    }

    /**
     * Sets the freeze table height.
     *
     * @param height the new freeze table height
     */
    public void setFreezeTableHeight(String height) {
        leftTable.setHeight(height);
        rightTable.setHeight(height);
    }

    /**
     * Sets the freeze table height.
     *
     * @param height the height
     * @param unit the unit
     */
    public void setFreezeTableHeight(float height, Sizeable.Unit unit) {
        leftTable.setHeight(height, unit);
        rightTable.setHeight(height, unit);
    }

    /**
     * Gets the left freeze as table.
     *
     * @return the left freeze as table
     */
    public ExtPagedTreeTable getLeftFreezeAsTable() {
        return leftTable;
    }

    /**
     * Gets the right freeze as table.
     *
     * @return the right freeze as table
     */
    public ExtPagedTreeTable getRightFreezeAsTable() {
        return rightTable;
    }

    /**
     * Gets the control config.
     *
     * @return the control config
     */
    public ExtPagedFilterControlConfig getControlConfig() {
        return logic.getControlConfig();
    }

    /**
     * Creates the controls.
     *
     * @return the horizontal layout
     */
    public HorizontalLayout createControls() {
        return logic.createControls();
    }

    /**
     * Sets the double header visible.
     *
     * @param doubleHeaderVisible the new double header visible
     */
    public void setDoubleHeaderVisible(boolean doubleHeaderVisible) {
        leftTable.setDoubleHeaderVisible(doubleHeaderVisible);
        rightTable.setDoubleHeaderVisible(doubleHeaderVisible);
    }

    /**
     * Checks if is double header visible.
     *
     * @return true, if is double header visible
     */
    public boolean isDoubleHeaderVisible() {
        return leftTable.isDoubleHeaderVisible() || rightTable.isDoubleHeaderVisible();
    }

    /**
     * Sets the double header map.
     *
     * @param leftColHeader the left col header
     * @param rightColHeader the right col header
     */
    public void setDoubleHeaderMap(Map<Object, Object[]> leftColHeader, Map<Object, Object[]> rightColHeader) {
        leftTable.setDoubleHeaderMap(leftColHeader);
        rightTable.setDoubleHeaderMap(rightColHeader);
    }

    /**
     * Sets the visible columns.
     *
     * @param leftVisibleColumns the left visible columns
     * @param rightVisibleColumns the right visible columns
     */
    public void setVisibleColumns(Object[] leftVisibleColumns, Object[] rightVisibleColumns) {
        leftTable.setVisibleColumns(leftVisibleColumns);
        rightTable.setVisibleColumns(rightVisibleColumns);
    }

    /**
     * Sets the column headers.
     *
     * @param leftColumnHeaders the left column headers
     * @param rightColumnHeaders the right column headers
     */
    public void setColumnHeaders(String[] leftColumnHeaders, String[] rightColumnHeaders) {
        leftTable.setColumnHeaders(leftColumnHeaders);
        rightTable.setColumnHeaders(rightColumnHeaders);
    }

    /**
     * Sets the double header visible columns.
     *
     * @param leftVisibleColumns the left visible columns
     * @param rightVisibleColumns the right visible columns
     */
    public void setDoubleHeaderVisibleColumns(Object[] leftVisibleColumns, Object[] rightVisibleColumns) {
        leftTable.setDoubleHeaderVisibleColumns(leftVisibleColumns);
        rightTable.setDoubleHeaderVisibleColumns(rightVisibleColumns);
    }

    /**
     * Sets the double header column headers.
     *
     * @param leftColumnHeaders the left column headers
     * @param rightColumnHeaders the right column headers
     */
    public void setDoubleHeaderColumnHeaders(String[] leftColumnHeaders, String[] rightColumnHeaders) {
        leftTable.setDoubleHeaderColumnHeaders(leftColumnHeaders);
        rightTable.setDoubleHeaderColumnHeaders(rightColumnHeaders);
    }

    /**
     * Sets the triple header visible.
     *
     * @param tripleHeaderVisible the new triple header visible
     */
    public void setTripleHeaderVisible(boolean tripleHeaderVisible) {
        leftTable.setTripleHeaderVisible(tripleHeaderVisible);
        rightTable.setTripleHeaderVisible(tripleHeaderVisible);
    }

    /**
     * Checks if is triple header visible.
     *
     * @return true, if is triple header visible
     */
    public boolean isTripleHeaderVisible() {
        return leftTable.isTripleHeaderVisible() || rightTable.isTripleHeaderVisible();
    }

    /**
     * Sets the triple header map.
     *
     * @param leftDoubleColHeader the left double col header
     * @param rightDoubleColHeader the right double col header
     */
    public void setTripleHeaderMap(Map<Object, Object[]> leftDoubleColHeader, Map<Object, Object[]> rightDoubleColHeader) {
        leftTable.setTripleHeaderMap(leftDoubleColHeader);
        rightTable.setTripleHeaderMap(rightDoubleColHeader);
    }

    /**
     * Sets the triple header visible columns.
     *
     * @param leftVisibleColumns the left visible columns
     * @param rightVisibleColumns the right visible columns
     */
    public void setTripleHeaderVisibleColumns(Object[] leftVisibleColumns, Object[] rightVisibleColumns) {
        leftTable.setTripleHeaderVisibleColumns(leftVisibleColumns);
        rightTable.setTripleHeaderVisibleColumns(rightVisibleColumns);
    }

    /**
     * Sets the triple header column headers.
     *
     * @param leftColumnHeaders the left column headers
     * @param rightColumnHeaders the right column headers
     */
    public void setTripleHeaderColumnHeaders(String[] leftColumnHeaders, String[] rightColumnHeaders) {
        leftTable.setTripleHeaderColumnHeaders(leftColumnHeaders);
        rightTable.setTripleHeaderColumnHeaders(rightColumnHeaders);
    }

    /**
     * Sets the container data source.
     *
     * @param newDataSource the new container data source
     */
    public void setContainerDataSource(Container newDataSource) {
        logic.setContainerDataSource(newDataSource);
    }

    /**
     * Sets the page length.
     *
     * @param page the new page length
     */
    public void setPageLength(int page) {
        logic.setPageLength(page);
    }

    /**
     * Sets the filter bar visible.
     *
     * @param visible the new filter bar visible
     */
    public void setFilterBarVisible(boolean visible) {
        this.filtersVisible = visible;
        leftTable.setFilterBarVisible(filtersVisible);
        rightTable.setFilterBarVisible(filtersVisible);
    }

    /**
     * Checks if is filter bar visible.
     *
     * @return true, if is filter bar visible
     */
    public boolean isFilterBarVisible() {
        return filtersVisible;
    }
}
