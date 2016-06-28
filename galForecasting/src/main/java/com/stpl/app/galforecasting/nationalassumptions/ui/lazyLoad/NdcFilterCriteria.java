/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.nationalassumptions.ui.lazyLoad;

import org.vaadin.addons.lazycontainer.SearchCriteria;

/**
 *
 * @author Nadhiya
 */
public class NdcFilterCriteria implements SearchCriteria {

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
        return true;
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

}
