/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.dao;

import org.apache.commons.lang.StringUtils;
import org.vaadin.addons.lazycontainer.SearchCriteria;

/**
 *
 * @author santanukumar
 */
public class ItemSearchCriteria implements SearchCriteria {

    int lastCount;
    boolean dirty = false;
    boolean customDirty = false;
    String filter = StringUtils.EMPTY;

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
        return customDirty;
    }

    @Override
    public void setDirty(boolean bln) {
        this.dirty = true;
    }

    @Override
    public String getFilter() {
        return filter;
    }

    @Override
    public void setFilter(String string) {
        this.filter = filter;
    }

}
