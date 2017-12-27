/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.lazyload;

import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Container.Filter;
import java.util.Set;
import org.asi.ui.addons.lazycontainer.BeanSearchCriteria;

/**
 *
 * @author lokeshwari
 */
public class CompanySearchCriteria implements BeanSearchCriteria {

    Set<Filter> filters;
    int lastCount;
    boolean dirty;

    public int getLastCount() {
        return lastCount;
    }

    public void setLastCount(int i) {
        this.lastCount = i;
    }

    public boolean isDirty() {
        return true;
    }

    public void setDirty(boolean bln) {
        this.dirty = bln;
    }

    public Set<Container.Filter> getFilters() {
        return filters;
    }

    public void setFilters(Set<Container.Filter> set) {
        this.filters = set;
    }

}
