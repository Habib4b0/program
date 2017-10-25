package com.stpl.app.contract.dashboard.util;

import com.vaadin.data.Container;
import java.util.Set;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;

/**
 * The Class CFPCriteria.
 */
public class CFPCriteria implements BeanSearchCriteria {

    /**
     * Returns true for all tables when checked
     * @return true
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
