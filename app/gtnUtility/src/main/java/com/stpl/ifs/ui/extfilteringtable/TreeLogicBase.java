/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.ifs.ui.extfilteringtable;

import com.vaadin.data.Container;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import java.util.List;
import java.util.Set;
import org.asi.ui.extfilteringtable.paged.ExtPagedFilterControlConfig;
import com.stpl.ifs.ui.util.GtnSmallHashMap;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;


/**
 * The Interface TreeLogicBase.
 *
 * @author Abhiram
 */
public interface TreeLogicBase {
    
    /**
     * Gets the count.
     *
     * @return the count
     */
    int getCount();
    
    /**
     * Configure container.
     *
     * @param object the object
     * @param datasource the datasource
     * @return the object
     */
    Object configureContainer(Object object, Container datasource);

    /**
     * Handle filter change.
     */
    void handleFilterChange();
    
    /**
     * Before Handle filter change.
     */
    void beforeHandleFilterChange();
    
    /**
     * Sets the column id to filter value.
     *
     * @param prop the prop
     * @param value the value
     */
    void setColumnIdToFilterValue(Object prop, Object value);
    
    /**
     * Gets the column id to filter map.
     *
     * @return the column id to filter map
     */
    GtnSmallHashMap getColumnIdToFilterMap();
    
    /**
     * Sets the column id to filter map.
     *
     * @param columnIdToFilterValue the column id to filter value
     */
    void setColumnIdToFilterMap(GtnSmallHashMap columnIdToFilterValue);
    
    /**
     * Gets the column id to filter value.
     *
     * @param prop the prop
     * @return the column id to filter value
     */
    Object getColumnIdToFilterValue(Object prop);

    /**
     * Adds the table.
     *
     * @param table the table
     * @return true, if successful
     */
    boolean addTable(PagedTreeTableBase table);
    
    /**
     * Removes the table.
     *
     * @param table the table
     * @return true, if successful
     */
    boolean removeTable(PagedTreeTableBase table);
    
    /**
     * Sets the tables.
     *
     * @param tableList the table list
     * @return true, if successful
     */
    boolean setTables(List<PagedTreeTableBase> tableList);
    
    /**
     * Gets the tables.
     *
     * @return the tables
     */
    List<PagedTreeTableBase> getTables();
    
    /**
     * Sets the control table.
     *
     * @param controlTable the new control table
     */
    void setControlTable(PagedTreeTableBase controlTable);
    
    /**
     * Gets the control table.
     *
     * @return the control table
     */
    PagedTreeTableBase getControlTable();
    
    /**
     * Gets the temp page length.
     *
     * @return the temp page length
     */
    int getTempPageLength();

    /**
     * Sets the temp page length.
     *
     * @param tempPageLength the new temp page length
     */
    void setTempPageLength(int tempPageLength);

    /**
     * Gets the control config.
     *
     * @return the control config
     */
    ExtPagedFilterControlConfig getControlConfig();
    
    /**
     * Creates the controls.
     *
     * @return the horizontal layout
     */
    HorizontalLayout createControls();
    
    /**
     * Gets the current page.
     *
     * @return the current page
     */
    int getCurrentPage();

    /**
     * Sink item per page with page length.
     *
     * @param isSink the is sink
     */
    void sinkItemPerPageWithPageLength(boolean isSink);

    /**
     * Checks if is sink item per page with page length.
     *
     * @return true, if is sink item per page with page length
     */
    boolean isSinkItemPerPageWithPageLength();
    
    /**
     * Sets the control button as link.
     *
     * @param isLink the new control button as link
     */
    void setControlButtonAsLink(boolean isLink);
    
    /**
     * Sets the page length.
     *
     * @param pageLength the new page length
     */
    void setPageLength(int pageLength);
    
    /**
     * Sets the items per page.
     *
     * @param itemsPerPage the new items per page
     */
    void setItemsPerPage(int itemsPerPage);
    
    /**
     * Gets the page length.
     *
     * @return the page length
     */
    int getPageLength();

    /**
     * Gets the total amount of pages.
     *
     * @return the total amount of pages
     */
    int getTotalAmountOfPages();

    /**
     * Sets the current page.
     *
     * @param currentPage the new current page
     */
    void setCurrentPage(int currentPage);
    
    /**
     * Sets the refresh.
     *
     * @param refresh the new refresh
     */
    void setRefresh(boolean refresh);

    /**
     * Checks if is refresh.
     *
     * @return true, if is refresh
     */
    boolean isRefresh();

    /**
     * Sets the tree node multi click.
     *
     * @param treeMultiClick the new tree node multi click
     */
    void setTreeNodeMultiClick(boolean treeMultiClick);

    /**
     * Checks if is tree node multi click.
     *
     * @return true, if is tree node multi click
     */
    boolean isTreeNodeMultiClick();
    
    /**
     * Update on expand.
     *
     * @param object the object
     * @param isManualLevel the is manual level
     */
    void updateOnExpand(Object object, boolean isManualLevel);

    /**
     * Update on collapse.
     *
     * @param object the object
     * @param isManualLevel the is manual level
     */
    void updateOnCollapse(Object object, boolean isManualLevel);
    
    /**
     * Gets the record count.
     *
     * @return the record count
     */
    double getRecordCount();

    /**
     * Sets the record count.
     *
     * @param recordCount the new record count
     */
    void setRecordCount(double recordCount);

    /**
     * Gets the total pages label.
     *
     * @return the total pages label
     */
    Label getTotalPagesLabel();

    /**
     * Sets the total pages label.
     *
     * @param totalPagesLabel the new total pages label
     */
    void setTotalPagesLabel(Label totalPagesLabel);

    /**
     * Gets the last parent.
     *
     * @return the last parent
     */
    Object getLastParent();

    /**
     * Sets the last parent.
     *
     * @param lastParent the new last parent
     */
    void setLastParent(Object lastParent);

    /**
     * Sets the container data source.
     *
     * @param newDataSource the new container data source
     */
    void setContainerDataSource(Container newDataSource);
    
    /**
     * Gets the container data source.
     *
     * @return the container data source
     */
    Container getContainerDataSource();
    
    /**
     * Gets the mannual current page.
     *
     * @return the mannual current page
     */
    int getMannualCurrentPage();

    /**
     * Sets the mannual current page.
     *
     * @param mannualCurrentPage the new mannual current page
     */
    void setMannualCurrentPage(int mannualCurrentPage);

    /**
     * Gets the items per page.
     *
     * @return the items per page
     */
    int getItemsPerPage();
    
    /**
     * Clear level map list.
     */
    void clearLevelMapList();

    /**
     * Clear parent.
     */
    void clearParent();

    /**
     * Clear container.
     */
    void clearContainer();
    
    /**
     * Clear filters.
     */
    void clearFilters();
    
    /**
     * Clear all.
     */
    void clearAll();

    /**
     * Sets the manual current page.
     *
     * @param page the new manual current page
     */
    void setManualCurrentPage(int page);
    
     /**
     * Clear Sort By Columns.
     */
    void clearSortByColumns();
    
    /**
     * Adds the sort by column.
     *
     * @param propertyId the property id
     * @return true, if successful
     */
    boolean addSortByColumn(SortByColumn propertyId);
    
    /**
     * Removes the sort by column.
     *
     * @param propertyId the property id
     * @return true, if successful
     */
    boolean removeSortByColumn(SortByColumn propertyId);

    /**
     * Gets the sort by columns.
     *
     * @return the sort by columns
     */
    List<SortByColumn> getSortByColumns();

    /**
     * Sets the sort by columns.
     *
     * @param sortByColumns the new sort by columns
     */
    void setSortByColumns(List<SortByColumn> sortByColumns);
    
    /**
     * Handle sort by columns.
     */
    void handleSortByColumns();
    
    /**
     * Sets the filters.
     *
     * @param addedFilters the new filters
     */
    void setFilters(Set<Container.Filter> addedFilters);
    
    /**
     * Gets the filters.
     *
     * @return the filters
     */
    Set<Container.Filter> getFilters();
    
    /**
     * Removes the all filters asociated with container.
     */
    void removeAllContainerFilters();
}
