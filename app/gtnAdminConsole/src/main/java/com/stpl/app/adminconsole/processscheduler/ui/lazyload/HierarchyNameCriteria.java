/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processscheduler.ui.lazyload;

import org.vaadin.addons.lazycontainer.SearchCriteria;

/**
 *
 * @author Karthikeyans
 */
public class HierarchyNameCriteria implements SearchCriteria {
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
	public String getDescription() {
		return description;
	}

	public HierarchyNameCriteria(){
		super();
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
     * @return 
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
	 */
	@Override
	public boolean isDirty() {
		return true;
	}

	/**
	 * Sets the is Dirty.
	 * 
     * @param dirty
	 * @param Last
	 *            Count
	 */
	@Override
	public void setDirty(final boolean dirty) {

		this.dirty = dirty;
	}

	/**
	 * Gets the Filter.
	 * 
     * @return 
	 */
	@Override
	public String getFilter() {
		return filter;
	}

	/**
	 * Sets the Filter.
	 * 
     * @param filter
	 * @param Filter
	 *            .
	 */
	@Override
	public void setFilter(final String filter) {
		this.filter = filter;
	}
}

