/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.lazyload;

import com.vaadin.v7.data.Container.Filter;
import java.util.Set;
import org.asi.ui.addons.lazycontainer.BeanSearchCriteria;
/**
 *
 * @author soundarrajan.l
 */
public class DataSelectionSearchCriteria implements BeanSearchCriteria {

 
	Set<Filter> filters;
	int lastCount;
	boolean dirty;
	
	@Override
	public Set<Filter> getFilters() {
		// TODO Auto-generated method stub
		return filters;
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
		this.filters = filters;
	}

	@Override
	public void setLastCount(int lastCount) {
		// TODO Auto-generated method stub
		this.lastCount = lastCount;
	}

}
