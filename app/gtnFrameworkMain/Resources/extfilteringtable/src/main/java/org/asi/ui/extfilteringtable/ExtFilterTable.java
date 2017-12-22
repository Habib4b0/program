/*
 * Copyright 2014 Abhiram.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.asi.ui.extfilteringtable;

import com.vaadin.server.ClientMethodInvocation;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Extension;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.converter.Converter.ConversionException;
import com.vaadin.server.LegacyPaint;
import com.vaadin.server.PaintException;
import com.vaadin.server.PaintTarget;
import com.vaadin.server.Resource;
import com.vaadin.server.ServerRpcManager;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinResponse;
import com.vaadin.shared.Registration;
import com.vaadin.shared.communication.SharedState;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.ui.Component;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.UI;
import com.vaadin.ui.declarative.DesignContext;
import com.vaadin.v7.data.Item;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.ui.TextField;
import elemental.json.JsonObject;
import java.io.IOException;
import org.asi.ui.extfilteringtable.ExtFilterFieldGenerator.IFilterTable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.jsoup.nodes.Element;


/**
 * FilterTable is an extension of the Vaadin Table component that provides
 * automatically generated filter fields for each column.
 * 
 * @author Abhiram
 * 
 */
@SuppressWarnings("serial")
public class ExtFilterTable extends ExtCustomTable implements IFilterTable {
    /* Maps property id's to column filter components */
    /** The column id to filter map. */
    private final Map<Object, Component> columnIdToFilterMap = new HashMap<Object, Component>();
    /* Internal list of currently collapsed column id:s */
    /** The collapsed column ids. */
    private final Set<Object> collapsedColumnIds = new HashSet<Object>();
    /* Set to true to show the filter components */
    /** The filters visible. */
    private boolean filtersVisible;
    /* Filter Generator and Decorator */
    /** The filter generator. */
    private ExtFilterGenerator filterGenerator;
    
    /** The decorator. */
    private ExtFilterDecorator decorator;
    /* FilterFieldGenerator instance */
    /** The generator. */
    private final ExtFilterFieldGenerator generator;
    /* Is initialization done */
    /** The init done. */
    private final boolean initDone;
    /* Force-render filter fields */
    /** The re render filter fields. */
    private boolean reRenderFilterFields;
    /* Wrap filters with additional div for styling? */
    /** The wrap filters. */
    private boolean wrapFilters = false;
    /* Are filters run immediately, or only on demand? */
    /** The filters run on demand. */
    private boolean filtersRunOnDemand = false;
    
    /**
     * Creates a new empty FilterTable.
     */
    public ExtFilterTable() {
        this(null);
    }

    /**
     * Creates a new empty FilterTable with the given caption.
     *
     * @param caption            Caption to set for the FilterTable
     */
    public ExtFilterTable(String caption) {
        super(caption);
        generator = new ExtFilterFieldGenerator(this);
        initDone = true;
    }
   
    /**
     * Paint content.
     *
     * @param target the target
     * @throws PaintException the paint exception
     */
    @Override
    public void paintContent(PaintTarget target) throws PaintException {
        
        super.paintContent(target);
        /* Add filter components to UIDL */
        target.startTag("filters");
        target.addAttribute("filtersvisible", filtersVisible);
        target.addAttribute("forceRender", reRenderFilterFields);
        target.addAttribute("wrapFilters", wrapFilters);
        reRenderFilterFields = false;
        /* if (filtersVisible) { */
            for (Object key : getColumnIdToFilterMap().keySet()) {
            /* Make sure parent is set properly */
                if (columnIdToFilterMap.get(key) != null
                        && columnIdToFilterMap.get(key).getParent() == null) {
                continue;// columnIdToFilterMap.get(key).setParent(this);
                }
                /* Paint the filter field */
                target.startTag("filtercomponent-" + columnIdMap.key(key));
                target.addAttribute("columnid", columnIdMap.key(key));
                Component c = getColumnIdToFilterMap().get(key);
                LegacyPaint.paint(c, target);
                target.endTag("filtercomponent-" + columnIdMap.key(key));
            // } else {
            // if (columnIdToFilterMap.get(key) != null) {
            // columnIdToFilterMap.get(key).setParent(null);
            // }
            }
        // }
        target.endTag("filters");
    }

    /**
     * Sets the column collapsed.
     *
     * @param propertyId the property id
     * @param collapsed the collapsed
     * @throws IllegalStateException the illegal state exception
     */
    @Override
    public void setColumnCollapsed(Object propertyId, boolean collapsed, boolean refresh)
            throws IllegalStateException {
        super.setColumnCollapsed(propertyId, collapsed,refresh);
        Component c = getColumnIdToFilterMap().get(propertyId);
        if (collapsed) {
            collapsedColumnIds.add(propertyId);
            if (c != null) {
                c.setParent(null);
                if (c instanceof TextField) {
                    ((TextField) c).setValue("");
                } else if (c instanceof AbstractField<?>) {
                    ((AbstractField<?>) c).setValue(null);
                }
            }
        } else {
            if (c != null) {
                c.setParent(this);
            }
            collapsedColumnIds.remove(propertyId);
        }
        reRenderFilterFields = true;
        markAsDirty();
    }

    /**
     * Sets the container data source.
     *
     * @param newDataSource the new container data source
     */
    @Override
    public void setContainerDataSource(Container newDataSource) {
        super.setContainerDataSource(newDataSource);
        resetFilters();
    }

    /**
     * Sets the container data source.
     *
     * @param newDataSource the new data source
     * @param visibleIds the visible ids
     */
    @Override
    public void setContainerDataSource(Container newDataSource,
            Collection<?> visibleIds) {
        super.setContainerDataSource(newDataSource, visibleIds);
        resetFilters();
    }

    /**
     * Resets all filters.
     * 
     * Note: Recreates the filter fields also!
     */
    public void resetFilters() {
        if (initDone) {
            disableContentRefreshing();
            for (Component c : columnIdToFilterMap.values()) {
                c.setParent(null);
            }
            collapsedColumnIds.clear();
            columnIdToFilterMap.clear();
            generator.destroyFilterComponents();
            generator.initializeFilterFields();
            reRenderFilterFields = true;
            enableContentRefreshing(true);
        }
    }

    /**
     * Clears all filters without recreating the filter fields.
     */
    public void clearFilters() {
        if (initDone) {
            generator.clearFilterData();
        }
    }

    /**
     * Sets the FilterDecorator for this FilterTable. FilterDecorator may be
     * used to provide proper translated display names and icons for the enum,
     * boolean and date values used in the filters.
     * 
     * Note: Recreates the filter fields also!
     * 
     * @param decorator
     *            An implementation of FilterDecorator to use with this
     *            FilterTable. Remove by giving null as this parameter.
     */
    public void setFilterDecorator(ExtFilterDecorator decorator) {
        this.decorator = decorator;
        resetFilters();
    }

    /**
     * Sets the FilterGenerator to use for providing custom Filters to the
     * container for one or more properties.
     * 
     * @param generator
     *            FilterGenerator to use with this FilterTable. Remove by giving
     *            null as this parameter.
     */
    public void setFilterGenerator(ExtFilterGenerator generator) {
        filterGenerator = generator;
    }

    /**
     * Sets the Filter bar visible or hidden.
     * 
     * @param filtersVisible
     *            true to set the Filter bar visible.
     */
    public void setFilterBarVisible(boolean filtersVisible) {
        this.filtersVisible = filtersVisible;
        for (Object key : columnIdToFilterMap.keySet()) {
            columnIdToFilterMap.get(key)
                    .setParent(filtersVisible ? this : null);
        }
        reRenderFilterFields = true;
        markAsDirty();
    }

    /**
     * Returns the current visibility state of the filter bar.
     * 
     * @return true if the filter bar is visible
     */
    public boolean isFilterBarVisible() {
        return filtersVisible;
    }

    /**
     * Toggles the visibility of the filter field defined for the give column
     * ID.
     * 
     * @param columnId
     *            Column/Property ID of the filter to toggle
     * @param visible
     *            true to set visible, false to set hidden
     */
    public void setFilterFieldVisible(Object columnId, boolean visible) {
        Component component = columnIdToFilterMap.get(columnId);
        if (component != null) {
            component.setVisible(visible);
            reRenderFilterFields = true;
            markAsDirty();
        }
    }

    /**
     * Returns visibility state of the filter field for the given column ID.
     *
     * @param columnId            Column/Property ID of the filter field to query
     * @return true if filter is visible, false if it's hidden
     */
    public boolean isFilterFieldVisible(Object columnId) {
        Component component = columnIdToFilterMap.get(columnId);
        if (component != null) {
            return component.isVisible();
        }
        return false;
    }

    /**
     * Set a value of a filter field. Note that for Date filters you need to
     * provide a value of {@link DateInterval} type.
     * 
     * @param propertyId
     *            Property id for which to set the value
     * @param value
     *            New value
     * @return true if setting succeeded, false if field was not found
     * @throws ConversionException
     *             exception from the underlying field
     */
    public boolean setFilterFieldValue(Object propertyId, Object value)
            throws ConversionException {
        Component field = getColumnIdToFilterMap().get(propertyId);
        boolean retVal = field != null;
        if (field != null) {
            ((AbstractField<?>) field).setConvertedValue(value);
        }
        return retVal;
    }

    /**
     * Get the current value of a filter field.
     *
     * @param propertyId            Property id from which to get the value
     * @return Current value
     */
    public Object getFilterFieldValue(Object propertyId) {
        Component field = getColumnIdToFilterMap().get(propertyId);
        if (field != null) {
            return ((AbstractField<?>) field).getValue();
        } else {
            return null;
        }
    }

    /**
     * Returns the filter component instance associated with the given property
     * ID.
     * 
     * @param propertyId
     *            Property id for which to find the filter component.
     * @return Related component instance or null if not found.
     */
    public Component getFilterField(Object propertyId) {
        return getColumnIdToFilterMap().get(propertyId);
    }

    /**
     * Gets the filterable.
     *
     * @return the filterable
     */
    @Override
    public Filterable getFilterable() {
        return getContainerDataSource() instanceof Filterable ? (Filterable) getContainerDataSource()
                : null;
    }

    /**
     * Gets the filter generator.
     *
     * @return the filter generator
     */
    @Override
    public ExtFilterGenerator getFilterGenerator() {
        return filterGenerator;
    }

    /**
     * Gets the filter decorator.
     *
     * @return the filter decorator
     */
    @Override
    public ExtFilterDecorator getFilterDecorator() {
        return decorator;
    }

    /**
     * Gets the column id to filter map.
     *
     * @return the column id to filter map
     */
    @Override
    public Map<Object, Component> getColumnIdToFilterMap() {
        return columnIdToFilterMap;
    }

    /**
     * Gets the as component.
     *
     * @return the as component
     */
    @Override
    public HasComponents getAsComponent() {
        return this;
    }

    /**
     * Iterator.
     *
     * @return the iterator
     */
    @Override
    public Iterator<Component> iterator() {
        Set<Component> children = new HashSet<Component>();
        if (visibleComponents != null) {
            children.addAll(visibleComponents);
        }
        if (initDone && filtersVisible) {
            for (Object key : columnIdToFilterMap.keySet()) {
                Component filter = columnIdToFilterMap.get(key);
                if (equals(filter.getParent()) && filter.isVisible()) {
                    children.add(filter);
                }
            }
        }
        return children.iterator();
    }

    /**
     * Sets the visible columns.
     *
     * @param visibleColumns the new visible columns
     */
    @Override
    public void setVisibleColumns(Object... visibleColumns) {
        reRenderFilterFields = true;
        if (visibleColumns != null && columnIdToFilterMap != null) {
            /* First clear all parent references */
            for (Object key : columnIdToFilterMap.keySet()) {
                columnIdToFilterMap.get(key).setParent(null);
            }
            /* Set this as parent to visible columns */
            for (Object key : visibleColumns) {
                Component filter = columnIdToFilterMap.get(key);
                if (filter != null) {
                    filter.setParent(this);
                }
            }
        }
        super.setVisibleColumns(visibleColumns);
    }

    /**
     * Sets the refreshing enabled.
     *
     * @param enabled the new refreshing enabled
     */
    @Override
    public void setRefreshingEnabled(boolean enabled) {
        if (enabled) {
            enableContentRefreshing(true);
        } else {
            disableContentRefreshing();
        }
    }

    /**
     * Sets the wrap filters.
     *
     * @param wrapFilters the new wrap filters
     */
    public void setWrapFilters(boolean wrapFilters) {
        if (this.wrapFilters == wrapFilters) {
            return;
        } else {
            this.wrapFilters = wrapFilters;
            reRenderFilterFields = true;
            markAsDirty();
        }
    }

    /**
     * Checks if is wrap filters.
     *
     * @return true, if is wrap filters
     */
    public boolean isWrapFilters() {
        return wrapFilters;
    }

    /**
     * Sets the filter on demand.
     *
     * @param filterOnDemand the new filter on demand
     */
    public void setFilterOnDemand(boolean filterOnDemand) {
        if (filtersRunOnDemand == filterOnDemand) {
            return;
        } else {
            filtersRunOnDemand = filterOnDemand;
            reRenderFilterFields = true;
            generator.setFilterOnDemandMode(filtersRunOnDemand);
        }

    }
    
    /**
     * Checks if is filter on demand.
     *
     * @return true, if is filter on demand
     */
    public boolean isFilterOnDemand() {
        return filtersRunOnDemand;
    }

    /**
     * Run filters.
     */
    public void runFilters() {
        if (!filtersRunOnDemand) {
            throw new IllegalStateException(
                    "Can't run filters on demand when filtersRunOnDemand is set to false");
        }
        generator.runFiltersNow();
    }

}