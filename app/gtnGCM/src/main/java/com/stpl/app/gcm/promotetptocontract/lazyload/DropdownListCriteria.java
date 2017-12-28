/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.lazyload;

import org.vaadin.addons.lazycontainer.SearchCriteria;

/**
 *
 * @author alok.v
 */
public class DropdownListCriteria implements SearchCriteria {

    String filter;
    int lastCount;
    boolean dirty;

    @Override
    public int getLastCount() {
        return lastCount;
    }

    @Override
    public void setLastCount(int lastCount) {
        this.lastCount = lastCount;
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
    public String getFilter() {
        return filter;
    }

    @Override
    public void setFilter(String filter) {
        this.filter = filter;
    }
}
