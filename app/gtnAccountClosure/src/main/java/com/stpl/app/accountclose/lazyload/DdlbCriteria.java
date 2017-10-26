/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.accountclose.lazyload;

import org.vaadin.addons.lazycontainer.SearchCriteria;

/**
 *
 * @author vigneshkanna
 */
public class DdlbCriteria implements SearchCriteria {

    String filter;
    int lastCount;
    boolean dirty;

    public int getLastCount() {
        return lastCount;
    }

    public void setLastCount(int lastCount) {
        this.lastCount = lastCount;
    }

    public boolean isDirty() {
        return true;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
