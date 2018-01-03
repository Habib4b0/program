/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.lazyload;

import com.vaadin.v7.data.Container;
import java.util.Set;
import org.asi.ui.addons.lazycontainer.BeanSearchCriteria;

/**
 *
 * @author alok.v
 */
public class PromteTpSearchCriteria implements BeanSearchCriteria {

    Set<Container.Filter> filters;
    int lastCount;
    boolean dirty;

    @Override
    public Set<Container.Filter> getFilters() {
        return filters;
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
        this.filters = filters;
    }

    @Override
    public void setLastCount(int lastCount) {
        this.lastCount = lastCount;
    }
}
