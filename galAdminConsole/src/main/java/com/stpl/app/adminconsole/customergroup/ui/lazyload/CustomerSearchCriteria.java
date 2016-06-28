/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.customergroup.ui.lazyload;

import org.vaadin.addons.lazycontainer.SearchCriteria;

/**
 *
 * @author mohamed
 */
public class CustomerSearchCriteria implements SearchCriteria {

    private int lastCount;
    private boolean dirty;
    private String filter;
 private boolean customDirty = true;
    public int getLastCount() {
        return lastCount;
    }

    public void setLastCount(int lastCount) {
        this.lastCount = lastCount;
    }

    public boolean isDirty() {
        return false;
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
    public void setCustomDirty(boolean customDirty) {
        this.customDirty = customDirty;
    }
}
