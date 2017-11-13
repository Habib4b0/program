/*
 * Copyright 2014 Abhiram.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.asi.ui.extfilteringtable.paged;

import com.vaadin.data.Container;
import java.util.Set;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 * The Class ExtPagedTable.
 *
 * @author Abhiram
 * @param <T> the generic type
 */
public class ExtPagedTable<T extends Container.Indexed & Container.Filterable & Container.ItemSetChangeNotifier>
        extends ExtFilterTable {
    // --------------fields for pagination control ---------------

    /**
     * The logic.
     */
    private PageTableLogic logic;
    /**
     * The current page.
     */
    private int currentPage = 1;
    //=========== end of fields for pagination configuration==========        
    /**
     * The container.
     */
    private Container container;
    /**
     * The items per page.
     */
    private int itemsPerPage = 15;

    /**
     * Instantiates a new ext paged table.
     *
     * @param logic the logic
     */
    public ExtPagedTable(PageTableLogic logic) {
        this(null, logic);
    }

    /**
     * Instantiates a new ext paged table.
     *
     * @param caption the caption
     * @param logic the logic
     */
    public ExtPagedTable(String caption, PageTableLogic logic) {
        super(caption);
        this.logic = logic;
        getContainerLogic().addTable(this);
        setPageLength(getContainerLogic().getTempPageLength());
        addStyleName("paged-tree-table");
    }

    /**
     * Sets the container logic.
     *
     * @param logic the new container logic
     */
    public void setContainerLogic(PageTableLogic logic) {
        this.logic = logic;
        getContainerLogic().addTable(this);
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
                    "ExtPagedTable can only use containers that implement Container.Indexed, Container.Filterable");
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
        getContainerLogic().setRequiredCount(true);
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
        getContainerLogic().setRequiredCount(true);
        getContainerLogic().removeAllContainerFilters();
        getContainerLogic().handleFilterChange();
    }
    
    /**
     * Sets the current page.
     *
     * @param currentPage the new current page
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
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
     * Gets the container logic.
     *
     * @return the container logic
     */
    public PageTableLogic getContainerLogic() {
        return logic;
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
