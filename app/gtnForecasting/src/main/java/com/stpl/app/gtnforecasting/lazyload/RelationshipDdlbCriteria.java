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

    private int lastCountRelationShip;
    private boolean dirtyRelationShip = true;
    private String filterRelationShip = StringUtils.EMPTY;

    public RelationshipDdlbCriteria() {
        super();
    }
    
    @Override
    public int getLastCount() {
        return lastCountRelationShip;
    }

    @Override
    public void setLastCount(int lastCountRs) {
       this.lastCountRelationShip = lastCountRs;
    }

    @Override
    public boolean isDirty() {
        return dirtyRelationShip;
    }

    @Override
    public void setDirty(boolean dirtyRs) {
       this.dirtyRelationShip = dirtyRs;
    }

    @Override
    public String getFilter() {
        return filterRelationShip;
    }

    @Override
    public void setFilter(String filterRs) {
        this.filterRelationShip = filterRs;
    }
    
}
