/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.asi.ui.extfilteringtable.paged;

import com.vaadin.data.Container;
import java.util.Set;

/**
 *
 * @author Manikanda.Prabu
 */
public interface ExtPagedTableBase {
    /**
     * Sets the filter change.
     *
     * @param prop the new filter change
     * @param addedFilters the added filters
     */
    public void setFilterChange(Object prop,Set<Container.Filter> addedFilters);
    
    /**
     * Sets the filter change.
     *
     * @param addedFilters the new filter change
     */
    public void setFilterChange(Set<Container.Filter> addedFilters);
}
