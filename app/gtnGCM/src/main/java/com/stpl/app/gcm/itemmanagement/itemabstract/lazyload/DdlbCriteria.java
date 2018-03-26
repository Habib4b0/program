/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.lazyload;

import org.asi.ui.addons.lazycontainer.SearchCriteria;

/**
 *
 * @author mohamed.hameed
 */
public class DdlbCriteria implements SearchCriteria {

    private String filter;
    private int lastCount;
    @SuppressWarnings("unused")
	private boolean dirty;

    public DdlbCriteria() {
    }

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
