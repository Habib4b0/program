/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.lazyload;

import com.vaadin.v7.data.Container.Filter;
import java.util.Collections;
import java.util.Set;
import org.asi.ui.addons.lazycontainer.BeanSearchCriteria;
/**
 *
 * @author soundarrajan.l
 */
public class DataSelectionSearchCriteria implements BeanSearchCriteria {

 
	private Set<Filter> filtersDSCriteria;
	private int lastCountDSCriteria;
	private boolean dirtyDSCriteria =true;

    public DataSelectionSearchCriteria() {
        super();
    }
	
	@Override
	public Set<Filter> getFilters() {
		return filtersDSCriteria == null ? filtersDSCriteria : Collections.unmodifiableSet(filtersDSCriteria);
	}

	@Override
	public int getLastCount() {
		return lastCountDSCriteria;
	}

	@Override
	public boolean isDirty() {
		return dirtyDSCriteria;
	}

	@Override
	public void setDirty(boolean dirtyDs) {
		this.dirtyDSCriteria = dirtyDs;
	}

	@Override
	public void setFilters(Set<Filter> filtersDs) {
		this.filtersDSCriteria = filtersDs == null ? filtersDs : Collections.unmodifiableSet(filtersDs);
	}

	@Override
	public void setLastCount(int lastCountDs) {
		this.lastCountDSCriteria = lastCountDs;
	}

}
