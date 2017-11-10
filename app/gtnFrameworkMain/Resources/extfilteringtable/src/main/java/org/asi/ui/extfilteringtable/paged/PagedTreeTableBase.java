/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.asi.ui.extfilteringtable.paged;

import java.util.Set;

import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.asi.ui.extfilteringtable.paged.logic.TreeLogicBase;

import com.vaadin.data.Container;

/**
 * The Class PagedTreeTableBase.
 *
 * @author Abhiram
 */
public abstract class PagedTreeTableBase extends ExtFilterTreeTable{
    
    // --------------fields for pagination control ---------------
    /** The logic. */
    private TreeLogicBase logic;
    
    /** The current page. */
    private int currentPage=1;
    //=========== end of fields for pagination configuration==========        

    /** The container. */
    private Container container;
    
    /** The items per page. */
    private int itemsPerPage=15;   
    

    /**
     * Instantiates a new paged tree table base.
     *
     * @param caption the caption
     * @param logic the logic
     */
    public PagedTreeTableBase(String caption, TreeLogicBase logic) {
        super(caption);
        this.logic = logic;
        logic.addTable(this);
        setPageLength(logic.getTempPageLength());
        addStyleName("paged-tree-table");

    }
  
  /**
   * Gets the collapsible strategy.
   *
   * @return the collapsible strategy
   */
  @Override
    protected CollapsibleStrategy getCollapsibleStrategy() {
        return new CollapsibleStrategy();
    }
    
    /**
     * Gets the hierarchical strategy.
     *
     * @return the hierarchical strategy
     */
    @Override
    protected HierarchicalStrategy getHierarchicalStrategy() {
        return new HierarchicalStrategy();
    }
    
    /**
     * The Class CollapsibleStrategy.
     */
    protected class CollapsibleStrategy extends ExtFilterTreeTable.CollapsibleStrategy{
    }
    
    /**
     * The Class HierarchicalStrategy.
     */
    protected class HierarchicalStrategy extends ExtFilterTreeTable.HierarchicalStrategy{
    }
    
    /**
     * Sets the container logic.
     *
     * @param logic the new container logic
     */
    public void setContainerLogic(TreeLogicBase logic) {
        this.logic = logic;
        getContainerLogic().addTable(this);
    }

    /**
     * Gets the container logic.
     *
     * @return the container logic
     */
    public TreeLogicBase getContainerLogic() {
        return logic;
    }

    /**
     * Sets the container data source.
     *
     * @param newDataSource the new container data source
     */
    @Override
    @SuppressWarnings("unchecked")
    public void setContainerDataSource(Container newDataSource) {
        if (!(newDataSource instanceof Container.Indexed)
                || !(newDataSource instanceof Container.Filterable)) {
            throw new IllegalArgumentException(
                    "ExtPagedFilterTreeTable can only use containers that implement Container.Indexed, Container.Filterable");
        }
        container = newDataSource;
        
        super.setContainerDataSource(container);
    }

    /**
     * Sets the filter change.
     *
     * @param prop the new filter change
     * @param addedFilters the added filters
     */
    public void setFilterChange(Object prop,Set<Container.Filter> addedFilters) {
        getContainerLogic().beforeHandleFilterChange();
        getContainerLogic().setFilters(addedFilters);
        getContainerLogic().setColumnIdToFilterValue(prop, getFilterFieldValue(prop));
        getContainerLogic().removeAllContainerFilters();
        getContainerLogic().handleFilterChange();
    }
    
    /**
     * Sets the filter change.
     *
     * @param addedFilters the new filter change
     */
    public void setFilterChange(Set<Container.Filter> addedFilters) {
        getContainerLogic().beforeHandleFilterChange();
        getContainerLogic().setFilters(addedFilters);
        getContainerLogic().handleFilterChange();
    }
    
    /**
     * Sets the current page.
     *
     * @param currentPage the new current page
     */
    public void setCurrentPage(int currentPage) {
    this.currentPage=currentPage;
}
    
    /**
     * Gets the control config.
     *
     * @return the control config
     */
    public ExtPagedFilterControlConfig getControlConfig() {
        return getContainerLogic().getControlConfig();
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
     * Sets the scroll index.
     *
     * @param index the index
     * @param refresh the refresh
     */
    public void setScrollIndex(int index,boolean refresh){
        setCurrentPageFirstItemIndex(index, refresh);
    }
    
    /**
     * Gets the total amount of pages.
     *
     * @return the total amount of pages
     */
    public int getTotalAmountOfPages() {
        return getContainerLogic().getTotalAmountOfPages();
    }

    /**
     * Gets the items per page.
     *
     * @return the items per page
     */
    public int getItemsPerPage() {
        return itemsPerPage;
    }

    /**
     * Sets the items per page.
     *
     * @param itemsPerPage the new items per page
     */
    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }
    
    /**
     * Do sort operation.
     *
     * @param propertyIds the property ids
     * @param ascending the ascending
     * @return true, if successful
     */
    @Override
    protected boolean doSortOperation(Object[] propertyIds, boolean[] ascending) {
        getContainerLogic().clearSortByColumns();
        for (int i = 0; i < propertyIds.length; i++) {
            Object propertyId = propertyIds[i];
            SortByColumn.Type type = ascending[i] ? SortByColumn.Type.ASC : SortByColumn.Type.DESC;
            String name = propertyId.toString();
            getContainerLogic().addSortByColumn(new SortByColumn(name, type));
            getContainerLogic().handleSortByColumns();
        }
        return true;
    }
}
