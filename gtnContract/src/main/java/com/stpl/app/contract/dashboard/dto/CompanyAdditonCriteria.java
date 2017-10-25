/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.dto;

import com.vaadin.data.Container;
import java.util.Set;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;

/**
 *
 * @author mohamed
 */
public class CompanyAdditonCriteria implements BeanSearchCriteria {
    	/**
	 * Boolean method to return true
	 */
     private boolean customDirty = true;
    Set<Container.Filter> filters;
    int lastCount;
    boolean dirty;
    
    @Override
    public boolean isDirty() {
        return true;
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
    public void setFilters(Set<Container.Filter> filters) {
        this.filters = filters;
    }

    @Override
    public int getLastCount() {
        return lastCount;
    }

    @Override
    public void setLastCount(int i) {
        this.lastCount = i;
    }

    @Override
    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

}
