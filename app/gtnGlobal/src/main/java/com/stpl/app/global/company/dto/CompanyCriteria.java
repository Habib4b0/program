package com.stpl.app.global.company.dto;

import com.vaadin.data.Container;
import java.util.Set;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;

// TODO: Auto-generated Javadoc
/**
 * To create object for search criteria.
 */
public class CompanyCriteria implements BeanSearchCriteria {
	
	/** (non-Javadoc)
	 * @see org.vaadin.addons.lazycontainer.AbstractSearchCriteria#isDirty()
	 */
    private boolean customDirty = true;
    Set<Container.Filter> filters;
    int lastCount;
    boolean dirty;

    public boolean isCustomDirty() {
        return customDirty;
    }

    public void setCustomDirty(boolean customDirty) {
        this.customDirty = customDirty;
    }

    @Override
    public boolean isDirty() {
        return customDirty; 
    }

    @Override
    public Set<Container.Filter> getFilters() {
        return filters;
    }

    @Override
    public int getLastCount() {
        return lastCount;
    }

    @Override
    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    @Override
    public void setFilters(Set<Container.Filter> filters) {
        this.filters = filters;
    }

    @Override
    public void setLastCount(int lastCount) {
        this.lastCount = lastCount;
    }
    
}
