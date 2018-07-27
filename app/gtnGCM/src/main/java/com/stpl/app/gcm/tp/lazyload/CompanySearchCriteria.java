/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.lazyload;

import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Container.Filter;
import java.util.Collections;
import java.util.Set;
import org.asi.ui.addons.lazycontainer.BeanSearchCriteria;

/**
 *
 * @author lokeshwari
 */
public class CompanySearchCriteria implements BeanSearchCriteria {

    private Set<Filter> filters;
    private int lastCount;
	private boolean dirty = true;

    public CompanySearchCriteria() {
        super();
    }

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
        return dirty;
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
