/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.lazyload;

import com.vaadin.data.Container;

import java.util.Collections;
import java.util.Set;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;

/**
 *
 * @author alok.v
 */
public class PromteTpSearchCriteria implements BeanSearchCriteria {

    private Set<Container.Filter> filters;
    private int lastCount;
    @SuppressWarnings("unused")
	private boolean dirty;

    @Override
    public Set<Container.Filter> getFilters() {
        return filters == null ? filters : Collections.unmodifiableSet(filters);
    }

    @Override
    public int getLastCount() {
        return lastCount;
    }

    @Override
    public boolean isDirty() {
        return true;
    }

    @Override
    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    @Override
    public void setFilters(Set<Container.Filter> filters) {
        this.filters = filters == null ? filters : Collections.unmodifiableSet(filters);
    }

    @Override
    public void setLastCount(int lastCount) {
        this.lastCount = lastCount;
    }
}
