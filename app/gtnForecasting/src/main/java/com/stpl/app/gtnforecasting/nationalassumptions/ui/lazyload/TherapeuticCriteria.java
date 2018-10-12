/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.nationalassumptions.ui.lazyload;

import org.asi.ui.addons.lazycontainer.SearchCriteria;

/**
 *
 * @author vinodhini
 */
public class TherapeuticCriteria implements SearchCriteria {

    /**
     * The search text.
     */
    private String searchText;

    /**
     * The description.
     */
    private String description;

    /**
     * The last count.
     */
    private int lastCount;

    /**
     * The dirty.
     */
    private boolean dirty = true;

    /**
     * The filter.
     */
    private String filter;

    public TherapeuticCriteria() {
        super();
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Gets the search text.
     *
     * @return the search text
     */
    public String getSearchText() {
        return searchText;
    }

    /**
     * Sets the search text.
     *
     * @param searchText the search text
     */
    public void setSearchText(final String searchText) {
        this.searchText = searchText;
    }

    /**
     * Gets the Last Count.
     *
     * @param Last Count
     */
    @Override
    public int getLastCount() {
        return lastCount;
    }

    /**
     * Sets the Last Count.
     *
     * @param Last Count
     */
    @Override
    public void setLastCount(final int lastCount) {
        this.lastCount = lastCount;
    }

    /**
     * Gets the is Dirty.
     *
     * @param is Dirty
     */
    @Override
    public boolean isDirty() {
        return dirty;
    }

    /**
     * Sets the is Dirty.
     *
     * @param Last Count
     */
    @Override
    public void setDirty(final boolean dirty) {

        this.dirty = dirty;
    }
    
    public boolean getDirty() {
        return dirty;
    }
    
    /**
     * Gets the Filter.
     *
     * @param Filter .
     */
    @Override
    public String getFilter() {
        return filter;
    }

    /**
     * Sets the Filter.
     *
     * @param Filter .
     */
    @Override
    public void setFilter(final String filter) {
        this.filter = filter;
    }
}
