/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.priceschedule.ui.lazyload;

import org.vaadin.addons.lazycontainer.SearchCriteria;

/**
 *
 * @author KarthikeyanS
 */
public class TempPSCriteria implements SearchCriteria {

    private boolean customDirty = true;

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
    public boolean dirty;

    /**
     * The filter.
     */
    private String filter;

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
        return customDirty;
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

    public boolean isCustomDirty() {
        return customDirty;
    }

    public void setCustomDirty(boolean customDirty) {
        this.customDirty = customDirty;
    }

}
