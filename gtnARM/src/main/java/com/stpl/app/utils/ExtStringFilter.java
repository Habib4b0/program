/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.utils;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;

/**
 *
 * @author Gopinath.Mathiyalaga
 */
public class ExtStringFilter implements Container.Filter {

    final Object propertyId;
    final String filterString;
    final boolean ignoreCase;
    final boolean onlyMatchPrefix;
    final boolean formSearch;

    public ExtStringFilter(Object propertyId, String filterString,
            boolean ignoreCase, boolean onlyMatchPrefix, boolean formSearch) {
        this.propertyId = propertyId;
        this.filterString = ignoreCase ? filterString.toLowerCase()
                : filterString;
        this.ignoreCase = ignoreCase;
        this.onlyMatchPrefix = onlyMatchPrefix;
        this.formSearch = formSearch;
    }

    @Override
    public boolean passesFilter(Object itemId, Item item) {
        final Property<?> p = item.getItemProperty(propertyId);
        if (p == null) {
            return false;
        }
        Object propertyValue = p.getValue();
        if (propertyValue == null) {
            return false;
        }
        final String value = ignoreCase ? propertyValue.toString()
                .toLowerCase() : propertyValue.toString();
        if (formSearch) {
            String filtrStrng = "^" + filterString.replace("*", ".*") + "$";
            if (!value.matches(filtrStrng)) {
                return false;
            }
        } else {
            if (onlyMatchPrefix) {
                if (!value.startsWith(filterString)) {
                    return false;
                }
            } else {
                if (!value.contains(filterString)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean appliesToProperty(Object propertyId) {
        return this.propertyId.equals(propertyId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        // Only ones of the objects of the same class can be equal
        if (!(obj instanceof ExtStringFilter)) {
            return false;
        }
        final ExtStringFilter o = (ExtStringFilter) obj;

        // Checks the properties one by one
        if (propertyId != o.propertyId && o.propertyId != null
                && !o.propertyId.equals(propertyId)) {
            return false;
        }
        if (filterString != o.filterString && o.filterString != null
                && !o.filterString.equals(filterString)) {
            return false;
        }
        if (ignoreCase != o.ignoreCase) {
            return false;
        }
        if (onlyMatchPrefix != o.onlyMatchPrefix) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return (propertyId != null ? propertyId.hashCode() : 0)
                ^ (filterString != null ? filterString.hashCode() : 0);
    }

    /**
     * Returns the property identifier to which this filter applies.
     *
     * @return property id
     */
    public Object getPropertyId() {
        return propertyId;
    }

    /**
     * Returns the filter string.
     *
     * Note: this method is intended only for implementations of lazy string
     * filters and may change in the future.
     *
     * @return filter string given to the constructor
     */
    public String getFilterString() {
        return filterString;
    }

    /**
     * Returns whether the filter is case-insensitive or case-sensitive.
     *
     * Note: this method is intended only for implementations of lazy string
     * filters and may change in the future.
     *
     * @return true if performing case-insensitive filtering, false for
     * case-sensitive
     */
    public boolean isIgnoreCase() {
        return ignoreCase;
    }

    /**
     * Returns true if the filter only applies to the beginning of the value
     * string, false for any location in the value.
     *
     * Note: this method is intended only for implementations of lazy string
     * filters and may change in the future.
     *
     * @return true if checking for matches at the beginning of the value only,
     * false if matching any part of value
     */
    public boolean isOnlyMatchPrefix() {
        return onlyMatchPrefix;
    }

    public boolean isFormSearch() {
        return formSearch;
    }
    
}
