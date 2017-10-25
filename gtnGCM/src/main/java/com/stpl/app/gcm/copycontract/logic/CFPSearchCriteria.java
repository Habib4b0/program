/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.logic;

import com.vaadin.data.Container;
import java.util.Set;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;

/**
 *
 * @author kasiammal.m
 */
public class CFPSearchCriteria implements BeanSearchCriteria {

    Set<Container.Filter> filters;
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
