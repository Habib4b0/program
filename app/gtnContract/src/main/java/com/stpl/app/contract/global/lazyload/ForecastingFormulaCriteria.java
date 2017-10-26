/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.contract.global.lazyload;

import com.vaadin.data.Container;
import java.util.Set;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;

/**
 *
 * @author Shrihariharan
 */
public class ForecastingFormulaCriteria implements BeanSearchCriteria {
   
   /**
    * @see org.vaadin.addons.lazycontainer.AbstractSearchCriteria#isDirty()
    */
    private boolean customDirty = true;
     Set<Container.Filter> filters;
    int lastCount;
    boolean dirty;
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
    public void setLastCount(int lastCount) {
        this.lastCount = lastCount;
    }

    @Override
    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }
    
    @Override
    public Set<Container.Filter> getFilters() {
       return filters;
    }

    @Override
    public void setFilters(Set<Container.Filter> filter) {
        this.filters = filter;
    }
}