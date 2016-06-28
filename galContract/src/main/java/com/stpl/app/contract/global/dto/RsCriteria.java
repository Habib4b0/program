package com.stpl.app.contract.global.dto;

import com.vaadin.data.Container;
import java.util.Set;
import org.vaadin.addons.lazycontainer.AbstractSearchCriteria;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;

/**
 * The Class RsCriteria.
 */
public class RsCriteria implements BeanSearchCriteria {

    
    private boolean customDirty = true;
    

	/** The description. */
	private String description;

	/** The last count. */
	private int lastCount;

	/** The dirty. */
	public boolean dirty;

	/** The filter. */
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
	 * @param description the description
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	@Override
        public Set<Container.Filter> getFilters() {
            return filters;
        }

        @Override
        public void setFilters(Set<Container.Filter> filters) {
            this.filters = filters;
        }

	/**
	 * Gets the Last Count.
	 */
	@Override
	public int getLastCount() {
		return lastCount;
	}

	/**
	 * Sets the Last Count.
	 */
	@Override
	public void setLastCount(final int lastCount) {
		this.lastCount = lastCount;
	}

	/**
	 * Gets the is Dirty.
	 */
	@Override
	public boolean isDirty() {
		return customDirty;
	}

	/**
	 * Sets the is Dirty.
	 */
	@Override
	public void setDirty(final boolean dirty) {

		this.dirty = dirty;
	}

	

    public boolean isCustomDirty() {
        return customDirty;
}

    public void setCustomDirty(boolean customDirty) {
        this.customDirty = customDirty;
    }
        
        
}
