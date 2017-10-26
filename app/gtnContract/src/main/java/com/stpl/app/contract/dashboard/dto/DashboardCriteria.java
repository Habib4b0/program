/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.dto;


import com.vaadin.data.Container;
import java.util.Set;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;

/**
 * This class used for DashboardCriteria.
 *
 * @author soundarrajan
 */
public class DashboardCriteria implements BeanSearchCriteria {
	/**
	 * Boolean method to return true
	 */
   

    Set<Container.Filter> filters;
    int lastCount;
    boolean dirty;
    
    
    @Override
    public boolean isDirty() {
        return true;
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
