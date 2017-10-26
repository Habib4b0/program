/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.rebateschedule.lazyload;
import com.vaadin.data.Container;
import java.util.Set;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;

/**
 *
 * @author Asha
 */
public class ItemDetailsCriteria implements BeanSearchCriteria {
    /** The search text. */
	private String searchText;

	/** The description. */
	private String description;

	/** The last count. */
	private int lastCount;

	/** The dirty. */
	public boolean dirty;

        Set<Container.Filter> filters;

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
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
	 * @param Last
	 *            Count
	 */
	@Override
	public int getLastCount() {
		return lastCount;
	}

	/**
	 * Sets the Last Count.
	 * 
	 * @param Last
	 *            Count
	 */
	@Override
	public void setLastCount(final int lastCount) {
		this.lastCount = lastCount;
	}

	/**
	 * Gets the is Dirty.
	 * 
	 * @param is
	 *            Dirty
	 */
	@Override
	public boolean isDirty() {
		return true;
	}

	/**
	 * Sets the is Dirty.
	 * 
	 * @param Last
	 *            Count
	 */
	@Override
	public void setDirty(final boolean dirty) {

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
