package com.stpl.app.global.rebateschedule.dto;

import com.vaadin.data.Container;
import java.util.Set;
import org.vaadin.addons.lazycontainer.AbstractSearchCriteria;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;

/**
 * The Class for Rebate schedule Criteria.
 */
public class RsCriteria implements BeanSearchCriteria {
   
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
