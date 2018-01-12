/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.lazyload;

import java.util.Collections;
import java.util.Set;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import com.vaadin.data.Container.Filter;
/**
 *
 * @author soundarrajan.l
 */
public class DataSelectionSearchCriteria implements BeanSearchCriteria {

 
	private Set<Filter> filters;
	private int lastCount;
	private boolean dirty;
	
	@Override
	public Set<Filter> getFilters() {
		// TODO Auto-generated method stub
		return filters == null ? filters : Collections.unmodifiableSet(filters);
	}

	@Override
	public int getLastCount() {
		// TODO Auto-generated method stub
		return lastCount;
	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setDirty(boolean dirty) {
		// TODO Auto-generated method stub
		this.dirty = dirty;
	}

	@Override
	public void setFilters(Set<Filter> filters) {
		// TODO Auto-generated method stub
		this.filters = filters == null ? filters : Collections.unmodifiableSet(filters);
	}

	@Override
	public void setLastCount(int lastCount) {
		// TODO Auto-generated method stub
		this.lastCount = lastCount;
	}

}
