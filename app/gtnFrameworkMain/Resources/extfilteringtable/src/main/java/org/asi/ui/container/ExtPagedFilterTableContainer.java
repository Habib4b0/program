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
package org.asi.ui.container;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.filter.UnsupportedFilterException;


/**
 * The Class ExtPagedFilterTableContainer.
 *
 * @author Abhiram
 * @param <T> the generic type
 */
public class ExtPagedFilterTableContainer<T extends Container.Indexed & Container.Filterable & Container.ItemSetChangeNotifier>
        implements Container, Container.Indexed, Container.Sortable,
        Container.Filterable, Container.ItemSetChangeNotifier {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -2134233618583099046L;

    /** The container. */
    private final T container;
    
    /** The page length. */
    private int pageLength = 25;
    
    /** The start index. */
    private int startIndex = 0;

    /**
     * Instantiates a new ext paged filter table container.
     *
     * @param container the container
     */
    public ExtPagedFilterTableContainer(T container) {
        this.container = container;
    }

    /**
     * Gets the container.
     *
     * @return the container
     */
    public T getContainer() {
        return container;
    }

    /**
     * Gets the page length.
     *
     * @return the page length
     */
    public int getPageLength() {
        return pageLength;
    }

    /**
     * Sets the page length.
     *
     * @param pageLength the new page length
     */
    public void setPageLength(int pageLength) {
        this.pageLength = pageLength;
    }

    /**
     * Gets the start index.
     *
     * @return the start index
     */
    public int getStartIndex() {
        return startIndex;
    }

    /**
     * Sets the start index.
     *
     * @param startIndex the new start index
     */
    public void setStartIndex(int startIndex) {
        if (startIndex < 0) {
            this.startIndex = 0;
        } else {
            this.startIndex = startIndex;
        }
    }

    /*
     * Overridden methods from the real container from here forward
     */

  
    /**
     * Size.
     *
     * @return the int
     */
    @Override
    public int size() {
        int rowsLeft = container.size() - startIndex;
        if (rowsLeft > pageLength) {
            return pageLength;
        } else {
            return rowsLeft;
        }
    }

    /**
     * Gets the real size.
     *
     * @return the real size
     */
    public int getRealSize() {
        return container.size();
    }

    /**
     * Gets the id by index.
     *
     * @param index the index
     * @return the id by index
     */
    @Override
    public Object getIdByIndex(int index) {
        return container.getIdByIndex(index + startIndex);
    }

    /*
     * Delegate methods to real container from here on
     */

    /**
     * Gets the item.
     *
     * @param itemId the item id
     * @return the item
     */
    @Override
    public Item getItem(Object itemId) {
        return container.getItem(itemId);
    }

    /**
     * Gets the container property ids.
     *
     * @return the container property ids
     */
    @Override
    public Collection<?> getContainerPropertyIds() {
        return container.getContainerPropertyIds();
    }

    /**
     * Gets the item ids.
     *
     * @return the item ids
     */
    @Override
    public Collection<?> getItemIds() {
        return container.getItemIds();
    }

    /**
     * Gets the item ids.
     *
     * @param startIndex the start index
     * @param numberOfItems the number of items
     * @return the item ids
     */
    @Override
    public List<?> getItemIds(int startIndex, int numberOfItems) {
        return container
                .getItemIds(this.startIndex + startIndex, numberOfItems);
    }

    /**
     * Gets the container property.
     *
     * @param itemId the item id
     * @param propertyId the property id
     * @return the container property
     */
    @Override
    public Property<?> getContainerProperty(Object itemId, Object propertyId) {
        return container.getContainerProperty(itemId, propertyId);
    }

    /**
     * Gets the type.
     *
     * @param propertyId the property id
     * @return the type
     */
    @Override
    public Class<?> getType(Object propertyId) {
        return container.getType(propertyId);
    }

    /**
     * Contains id.
     *
     * @param itemId the item id
     * @return true, if successful
     */
    @Override
    public boolean containsId(Object itemId) {
        return container.containsId(itemId);
    }

    /**
     * Adds the item.
     *
     * @param itemId the item id
     * @return the item
     * @throws UnsupportedOperationException the unsupported operation exception
     */
    @Override
    public Item addItem(Object itemId) throws UnsupportedOperationException {
        return container.addItem(itemId);
    }

    /**
     * Adds the item.
     *
     * @return the object
     * @throws UnsupportedOperationException the unsupported operation exception
     */
    @Override
    public Object addItem() throws UnsupportedOperationException {
        return container.addItem();
    }

    /**
     * Removes the item.
     *
     * @param itemId the item id
     * @return true, if successful
     * @throws UnsupportedOperationException the unsupported operation exception
     */
    @Override
    public boolean removeItem(Object itemId)
            throws UnsupportedOperationException {
        return container.removeItem(itemId);
    }

    /**
     * Adds the container property.
     *
     * @param propertyId the property id
     * @param type the type
     * @param defaultValue the default value
     * @return true, if successful
     * @throws UnsupportedOperationException the unsupported operation exception
     */
    @Override
    public boolean addContainerProperty(Object propertyId, Class<?> type,
            Object defaultValue) throws UnsupportedOperationException {
        return container.addContainerProperty(propertyId, type, defaultValue);
    }

    /**
     * Removes the container property.
     *
     * @param propertyId the property id
     * @return true, if successful
     * @throws UnsupportedOperationException the unsupported operation exception
     */
    @Override
    public boolean removeContainerProperty(Object propertyId)
            throws UnsupportedOperationException {
        return container.removeContainerProperty(propertyId);
    }

    /**
     * Removes the all items.
     *
     * @return true, if successful
     * @throws UnsupportedOperationException the unsupported operation exception
     */
    @Override
    public boolean removeAllItems() throws UnsupportedOperationException {
        return container.removeAllItems();
    }

    /**
     * Next item id.
     *
     * @param itemId the item id
     * @return the object
     */
    @Override
    public Object nextItemId(Object itemId) {
        return container.nextItemId(itemId);
    }

    /**
     * Prev item id.
     *
     * @param itemId the item id
     * @return the object
     */
    @Override
    public Object prevItemId(Object itemId) {
        return container.prevItemId(itemId);
    }

    /**
     * First item id.
     *
     * @return the object
     */
    @Override
    public Object firstItemId() {
        return container.firstItemId();
    }

    /**
     * Last item id.
     *
     * @return the object
     */
    @Override
    public Object lastItemId() {
        return container.lastItemId();
    }

    /**
     * Checks if is first id.
     *
     * @param itemId the item id
     * @return true, if is first id
     */
    @Override
    public boolean isFirstId(Object itemId) {
        return container.isFirstId(itemId);
    }

    /**
     * Checks if is last id.
     *
     * @param itemId the item id
     * @return true, if is last id
     */
    @Override
    public boolean isLastId(Object itemId) {
        return container.isLastId(itemId);
    }

    /**
     * Adds the item after.
     *
     * @param previousItemId the previous item id
     * @return the object
     * @throws UnsupportedOperationException the unsupported operation exception
     */
    @Override
    public Object addItemAfter(Object previousItemId)
            throws UnsupportedOperationException {
        return container.addItemAfter(previousItemId);
    }

    /**
     * Adds the item after.
     *
     * @param previousItemId the previous item id
     * @param newItemId the new item id
     * @return the item
     * @throws UnsupportedOperationException the unsupported operation exception
     */
    @Override
    public Item addItemAfter(Object previousItemId, Object newItemId)
            throws UnsupportedOperationException {
        return container.addItemAfter(previousItemId, newItemId);
    }

    /**
     * Index of id.
     *
     * @param itemId the item id
     * @return the int
     */
    @Override
    public int indexOfId(Object itemId) {
        return container.indexOfId(itemId);
    }

    /**
     * Adds the item at.
     *
     * @param index the index
     * @return the object
     * @throws UnsupportedOperationException the unsupported operation exception
     */
    @Override
    public Object addItemAt(int index) throws UnsupportedOperationException {
        return container.addItemAt(index);
    }

    /**
     * Adds the item at.
     *
     * @param index the index
     * @param newItemId the new item id
     * @return the item
     * @throws UnsupportedOperationException the unsupported operation exception
     */
    @Override
    public Item addItemAt(int index, Object newItemId)
            throws UnsupportedOperationException {
        return container.addItemAt(index, newItemId);
    }

    /*
     * Sorting interface from here on
     */

    /**
     * Sort.
     *
     * @param propertyId the property id
     * @param ascending the ascending
     */
    @Override
    public void sort(Object[] propertyId, boolean[] ascending) {
        if (container instanceof Container.Sortable) {
            ((Container.Sortable) container).sort(propertyId, ascending);
        }
    }

    /**
     * Gets the sortable container property ids.
     *
     * @return the sortable container property ids
     */
    @Override
    public Collection<?> getSortableContainerPropertyIds() {
        if (container instanceof Container.Sortable) {
            return ((Container.Sortable) container)
                    .getSortableContainerPropertyIds();
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * Adds the container filter.
     *
     * @param filter the filter
     * @throws UnsupportedFilterException the unsupported filter exception
     */
    @Override
    public void addContainerFilter(Filter filter)
            throws UnsupportedFilterException {
        container.addContainerFilter(filter);
    }

    /**
     * Gets the container filters.
     *
     * @return the container filters
     */
    @Override
    public Collection<Filter> getContainerFilters() {
        return container.getContainerFilters();
    }

    /**
     * Removes the container filter.
     *
     * @param filter the filter
     */
    @Override
    public void removeContainerFilter(Filter filter) {
        container.removeContainerFilter(filter);
    }

    /**
     * Removes the all container filters.
     */
    @Override
    public void removeAllContainerFilters() {
        container.removeAllContainerFilters();
    }

    /**
     * Adds the item set change listener.
     *
     * @param listener the listener
     */
    @Override
    public void addItemSetChangeListener(ItemSetChangeListener listener) {
        ((Container.ItemSetChangeNotifier) container)
                .addItemSetChangeListener(listener);
    }

    /**
     * Removes the item set change listener.
     *
     * @param listener the listener
     */
    @Override
    public void removeItemSetChangeListener(ItemSetChangeListener listener) {
        ((Container.ItemSetChangeNotifier) container)
                .removeItemSetChangeListener(listener);
    }

    /**
     * Adds the listener.
     *
     * @param listener the listener
     */
    @Override
    public void addListener(ItemSetChangeListener listener) {
        addItemSetChangeListener(listener);
    }

    /**
     * Removes the listener.
     *
     * @param listener the listener
     */
    @Override
    public void removeListener(ItemSetChangeListener listener) {
        removeItemSetChangeListener(listener);
    }
}