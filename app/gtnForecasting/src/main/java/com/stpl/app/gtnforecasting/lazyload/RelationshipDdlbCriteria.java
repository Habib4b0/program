/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.lazyload;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.addons.lazycontainer.SearchCriteria;

/**
 *
 * @author soundarrajan.l
 */
public class RelationshipDdlbCriteria  implements SearchCriteria {

    private int lastCount;
    private boolean dirty = true;
    private String filter = StringUtils.EMPTY;
    
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
