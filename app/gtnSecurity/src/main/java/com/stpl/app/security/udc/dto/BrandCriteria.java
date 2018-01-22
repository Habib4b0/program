/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.security.udc.dto;

import com.vaadin.data.Container;

import java.util.Collections;
import java.util.Set;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;

/**
 *
 * @author Asha
 */
public class BrandCriteria implements BeanSearchCriteria{
    
    /**
    * @see org.vaadin.addons.lazycontainer.AbstractSearchCriteria#isDirty()
    */
    private boolean customDirty = true;
    private Set<Container.Filter> filters;
    private int lastCount;
    private boolean dirty;
   @Override
    public boolean isDirty() {
        return customDirty;
    }	

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
    public void setLastCount(int coun) {
        this.lastCount = coun;
    }

    @Override
    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }
    
    @Override
    public Set<Container.Filter> getFilters() {
       return filters == null ? filters : Collections.unmodifiableSet(filters);
    }

    @Override
    public void setFilters(Set<Container.Filter> filter) {
        this.filters = filter == null ? filters : Collections.unmodifiableSet(filters);
    }
    
    
}
