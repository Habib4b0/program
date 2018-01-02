/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.lazyload;

import com.vaadin.data.Container;
import com.vaadin.data.Container.Filter;
import java.util.Set;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;

/**
 *
 * @author lokeshwari
 */
public class CompanySearchCriteria implements BeanSearchCriteria {

    Set<Filter> filters;
    int lastCount;
    boolean dirty;

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
        return filters;
    }

    @Override
    public void setFilters(Set<Container.Filter> set) {
        this.filters = set;
    }

}
