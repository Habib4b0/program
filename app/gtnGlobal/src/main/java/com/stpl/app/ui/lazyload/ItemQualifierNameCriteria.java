/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.ui.lazyload;

import org.vaadin.addons.lazycontainer.SearchCriteria;

/**
 * Criteria for Item Qualifier Name DDLB.
 * @author Harlin
 */
public class ItemQualifierNameCriteria implements SearchCriteria{
    	/** The search text. */
	private String searchText;

	/** The description. */
	private String description;

	/** The last count. */
	private int lastCount;

	/** The dirty. */
	@SuppressWarnings("unused")
	private boolean dirty;

	/** The filter. */
	private String filter;

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public ItemQualifierNameCriteria(){
		super();
	}
	
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description
	 *            the description
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Gets the search text.
	 *
	 * @return the search text
	 */
	public String getSearchText() {
		return searchText;
	}

	/**
	 * Sets the search text.
	 *
	 * @param searchText
	 *            the search text
	 */
	public void setSearchText(final String searchText) {
		this.searchText = searchText;
	}

	/**
	 * Gets the Last Count.
	 * 
	 * @return Last Count
	 */
	@Override
	public int getLastCount() {
		return lastCount;
	}

	/**
	 * Sets the Last Count.
	 * 
	 * @param lastCount
	 */
	@Override
	public void setLastCount(final int lastCount) {
		this.lastCount = lastCount;
	}

	/**
	 * Gets the is Dirty.
	 * 
	 * @return isDirty
	 */
	@Override
	public boolean isDirty() {
		return true;
	}

	/**
	 * Sets the is Dirty.
	 * 
	 * @param dirty
	 */
	@Override
	public void setDirty(final boolean dirty) {

		this.dirty = dirty;
	}

	/**
	 * Gets the Filter.
	 * 
	 * @return Filter
	 */
	@Override
	public String getFilter() {
		return filter;
	}

	/**
	 * Sets the Filter.
	 * 
	 * @param filter
	 */
	@Override
	public void setFilter(final String filter) {
		this.filter = filter;
	}
}
