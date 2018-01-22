/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.logic;

import com.vaadin.data.Container;

import java.util.Collections;
import java.util.Set;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;

/**
 *
 * @author kasiammal.m
 */
public class CFPSearchCriteria implements BeanSearchCriteria {

    private Set<Container.Filter> filters;
    private int lastCount;
    public boolean dirty;

    @Override
    public int getLastCount() {
        return lastCount;
    }

    @Override
    public void setLastCount(int i) {
        this.lastCount = i;
    }

    @Override
    public boolean isDirty() {
        return true;
    }

    @Override
    public void setDirty(boolean bln) {
        this.dirty = bln;
    }

    @Override
    public Set<Container.Filter> getFilters() {
        return filters == null ? filters : Collections.unmodifiableSet(filters);
    }

    @Override
    public void setFilters(Set<Container.Filter> set) {
        this.filters = set == null ? set : Collections.unmodifiableSet(set);
    }
}
