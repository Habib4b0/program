/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.lazyload;

import org.apache.commons.lang.StringUtils;
import org.vaadin.addons.lazycontainer.SearchCriteria;

/**
 *
 * @author alok.v
 */
public class MarketTypeDdlbCriteria implements SearchCriteria {

    int lastCount;
    boolean dirty = true;
    boolean customDirty = true;
    String filter = StringUtils.EMPTY;

    public boolean isCustomDirty() {
        return customDirty;
    }

    public void setCustomDirty(boolean customDirty) {
        this.customDirty = customDirty;
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
