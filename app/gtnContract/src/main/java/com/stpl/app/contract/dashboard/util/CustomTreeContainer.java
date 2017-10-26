/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.util;

/**
 *
 * @author suthan
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.stpl.app.contract.util.Constants;
import com.vaadin.data.Container;
import com.vaadin.data.Container.Hierarchical;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.HierarchicalContainer;

/**
 * The Class CustomTreeContainer.
 *
 * @param <E> the generic type
 */
public class CustomTreeContainer<E> extends BeanItemContainer<E> implements Hierarchical {

    /**
     * Set of IDs of those contained Items that can't have children.
     */
    private final Set<Object> noChildrenAllowed = new HashSet<Object>();
    /**
     * Mapping from Item ID to parent Item ID.
     */
    private final Map<Object, Object> parent = new HashMap<Object, Object>();
    /**
     * Mapping from Item ID to parent Item ID for items included in the filtered
     * container.
     */
    private Map<Object, Object> filteredParent;
    /**
     * Mapping from Item ID to a list of child IDs.
     */
    private final Map<Object, LinkedList<Object>> children = new HashMap<Object, LinkedList<Object>>();
    /**
     * Mapping from Item ID to a list of child IDs when filtered.
     */
    private Map<Object, LinkedList<Object>> filteredChildren;
    /**
     * List that contains all root elements of the container.
     */
    private final List<Object> roots = new LinkedList<Object>();
    /**
     * List that contains all filtered root elements of the container.
     */
    private List<Object> filteredRoots;
    /**
     * Determines how filtering of the container is done.
     */
    private boolean includeParentsWhenFiltering = true;
    /**
     * The Constant NULL_LINKED_LIST.
     */
    private static final List<Object> NULL_LINKED_LIST = null;
    /**
     * The Constant NULL_MAP.
     */
    private static final Map<Object, LinkedList<Object>> NULL_MAP = null;
    /**
     * The Constant NULL_MAP2.
     */
    private static final Map<Object, Object> NULL_MAP2 = null;
    /**
     * Counts how many nested contents change disable calls are in progress.
     *
     * Pending events are only fired when the counter reaches zero again.
     */
    private int contentChangedEventsDisabledCount;
    /**
     * The contents changed event pending.
     */
    private boolean contentsChangedEventPending;
    /**
     * The filter override.
     */
    private Set<Object> filterOverride;

    /**
     * Gets the filtered parent.
     *
     * @return the filtered parent
     */
    public Map<Object, Object> getFilteredParent() {
        return filteredParent;
    }

    /**
     * Sets the filtered parent.
     *
     * @param filteredParent the filtered parent
     */
    public void setFilteredParent(final Map<Object, Object> filteredParent) {
        this.filteredParent = filteredParent;
    }

    /**
     * Gets the filtered children.
     *
     * @return the filtered children
     */
    public Map<Object, LinkedList<Object>> getFilteredChildren() {
        return filteredChildren;
    }

    /**
     * Sets the filtered children.
     *
     * @param filteredChildren the filtered children
     */
    public void setFilteredChildren(final Map<Object, LinkedList<Object>> filteredChildren) {
        this.filteredChildren = filteredChildren;
    }

    /**
     * Gets the filtered roots.
     *
     * @return the filtered roots
     */
    public List<Object> getFilteredRoots() {
        return filteredRoots;
    }

    /**
     * Sets the filtered roots.
     *
     * @param filteredRoots the filtered roots
     */
    public void setFilteredRoots(final List<Object> filteredRoots) {
        this.filteredRoots = filteredRoots;
    }

    /**
     * Gets the content changed events disabled count.
     *
     * @return the content changed events disabled count
     */
    public int getContentChangedEventsDisabledCount() {
        return contentChangedEventsDisabledCount;
    }

    /**
     * Sets the content changed events disabled count.
     *
     * @param contentChangedEventsDisabledCount the content changed events
     * disabled count
     */
    public void setContentChangedEventsDisabledCount(final int contentChangedEventsDisabledCount) {
        this.contentChangedEventsDisabledCount = contentChangedEventsDisabledCount;
    }

    /**
     * Checks if is contents changed event pending.
     *
     * @return true, if checks if is contents changed event pending
     */
    public boolean isContentsChangedEventPending() {
        return contentsChangedEventPending;
    }

    /**
     * Sets the contents changed event pending.
     *
     * @param contentsChangedEventPending the contents changed event pending
     */
    public void setContentsChangedEventPending(final boolean contentsChangedEventPending) {
        this.contentsChangedEventPending = contentsChangedEventPending;
    }

    /**
     * Gets the filter override.
     *
     * @return the filter override
     */
    public Set<Object> getFilterOverride() {
        return filterOverride;
    }

    /**
     * Sets the filter override.
     *
     * @param filterOverride the filter override
     */
    public void setFilterOverride(final Set<Object> filterOverride) {
        this.filterOverride = filterOverride;
    }

    /**
     * Gets the no children allowed.
     *
     * @return the no children allowed
     */
    public Set<Object> getNoChildrenAllowed() {
        return noChildrenAllowed;
    }

    /**
     * Gets the parent.
     *
     * @return the parent
     */
    public Map<Object, Object> getParent() {
        return parent;
    }

    /**
     * Gets the children.
     *
     * @return the children
     */
    public Map<Object, LinkedList<Object>> getChildren() {
        return children;
    }

    /**
     * Gets the roots.
     *
     * @return the roots
     */
    public List<Object> getRoots() {
        return roots;
    }

    /**
     * Instantiates a new custom tree container.
     *
     * @param type the type
     * @throws IllegalArgumentException the illegal argument exception
     */
    public CustomTreeContainer(final Class<? super E> type) throws IllegalArgumentException {
        super(type);
    }

    /**
     * Instantiates a new custom tree container.
     *
     * @param type the type
     * @param collection the collection
     * @throws IllegalArgumentException the illegal argument exception
     */
    public CustomTreeContainer(final Class<? super E> type, final Collection<? extends E> collection) throws IllegalArgumentException {
        super(type, collection);
    }

    /**
     * Instantiates a new custom tree container.
     *
     * @param collection the collection
     * @throws IllegalArgumentException the illegal argument exception
     */
    public CustomTreeContainer(final Collection<? extends E> collection) throws IllegalArgumentException {
        super(collection);
    }

    /**
     * Tests if the Item with given ID can have children.
     *
     * @param itemId the item id
     * @return true, if are children allowed
     */
    public boolean areChildrenAllowed(final Object itemId) {
        if (noChildrenAllowed.contains(itemId)) {
            return false;
        }
        return containsId(itemId);
    }

    /**
     * Gets the IDs of the children of the specified Item.
     *
     * @param itemId the item id
     * @return the children
     */
    public Collection<?> getChildren(final Object itemId) {
        LinkedList<Object> filteredChilds;

        if (filteredChildren == null) {
            filteredChilds = children.get(itemId);
        } else {
            filteredChilds = filteredChildren.get(itemId);
        }

        if (filteredChilds == null) {
            return null;
        }
        return Collections.unmodifiableCollection(filteredChilds);
    }

    /**
     * Gets the ID of the parent of the specified Item.
     *
     * @param itemId the item id
     * @return the parent
     */
    public Object getParent(final Object itemId) {
        if (filteredParent != null) {
            return filteredParent.get(itemId);
        }
        return parent.get(itemId);
    }

    /**
     * Tests if the Item specified with itemId has child Items or if it is a
     * leaf.
     *
     * @param itemId the item id
     * @return true, if checks for children
     */
    public boolean hasChildren(final Object itemId) {
        if (filteredChildren == null) {
            return children.containsKey(itemId);
        } else {
            return filteredChildren.containsKey(itemId);
        }
    }

    /**
     * Tests if the Item specified with itemId is a root Item.
     *
     * @param itemId the item id
     * @return true, if checks if is root
     */
    public boolean isRoot(final Object itemId) {
        if (filteredRoots == null) {
            if (parent.containsKey(itemId)) {
                return false;
            }
        } else {
            if (!filteredRoots.contains(itemId)) {
                return false;
            }
        }

        return containsId(itemId);
    }

    /**
     * Gets the IDs of the root elements in the container.
     *
     * @return the collection<?>
     */
    public Collection<?> rootItemIds() {
        if (filteredRoots == null) {
            return Collections.unmodifiableCollection(roots);
        } else {
            return Collections.unmodifiableCollection(filteredRoots);

        }
    }

    /**
     * Sets the given Item's capability to have children. If the Item identified
     * with the itemId already has children and the areChildrenAllowed is false
     * this method fails and
     *
     * @param itemId the ID of the Item in the container whose child capability
     * @param childrenAllowed the boolean value specifying if the Item can have
     * @return <code>true</code> if the operation succeeded, <code>false</code>
     */
    public boolean setChildrenAllowed(final Object itemId, final boolean childrenAllowed) {

        if (!containsId(itemId)) {
            return false;
        }
        if (childrenAllowed) {
            noChildrenAllowed.remove(itemId);
        } else {
            noChildrenAllowed.add(itemId);
        }

        return true;
    }

    /**
     * Sets the parent of an Item. The new parent item must exist and be able to
     * have children.
     *
     * @param itemId the ID of the item to be set as the child of the Item
     * @param newParentId the ID of the Item that's to be the new parent of the
     * @return <code>true</code> if the operation succeeded, <code>false</code>
     */
    public boolean setParent(final Object itemId, final Object newParentId) {

        // Checks that the item is in the container
        if (!containsId(itemId)) {
            return false;
        }

        // Gets the old parent
        final Object oldParentId = parent.get(itemId);

        // Checks if no change is necessary
        if (newParentId == null && oldParentId == null || (newParentId != null) && newParentId.equals(oldParentId)) {
            return true;
        }

        // Making root?
        if (newParentId == null) {
            // The itemId should become a root so we need to
            // - Remove it from the old parent's children list
            // - Add it as a root
            // - Remove it from the item -> parent list (parent is null for
            // roots)

            // Removes from old parents children list
            final LinkedList<Object> parentObject = children.get(oldParentId);
            if (parentObject != null) {
                parentObject.remove(itemId);
                if (parentObject.isEmpty()) {
                    children.remove(oldParentId);
                }

            }

            // Add to be a root
            roots.add(itemId);

            // Updates parent
            parent.remove(itemId);

            if (hasFilters()) {
                // Refilter the container if setParent is called when filters
                // are applied. Changing parent can change what is included in
                // the filtered version (if includeParentsWhenFiltering==true).
                doFilterContainer(hasFilters());
            }

            fireItemSetChange();

            return true;
        }

        // We get here when the item should not become a root and we need to
        // - Verify the new parent exists and can have children
        // - Check that the new parent is not a child of the selected itemId
        // - Updated the item -> parent mapping to point to the new parent
        // - Remove the item from the roots list if it was a root
        // - Remove the item from the old parent's children list if it was not a
        // root

        // Checks that the new parent exists in container and can have
        // children
        if (!containsId(newParentId) || noChildrenAllowed.contains(newParentId)) {
            return false;
        }

        // Checks that setting parent doesn't result to a loop
        Object parentObject = newParentId;
        while (parentObject != null && !parentObject.equals(itemId)) {
            parentObject = parent.get(parentObject);
        }
        if (parentObject != null) {
            return false;
        }

        // Updates parent
        parent.put(itemId, newParentId);
        LinkedList<Object> pcl = children.get(newParentId);
        if (pcl == null) {
            // Create an empty list for holding children if one were not
            // previously created
            pcl = new LinkedList<Object>();
            children.put(newParentId, pcl);
        }
        pcl.add(itemId);

        // Removes from old parent or root
        if (oldParentId == null) {
            roots.remove(itemId);
        } else {
            final LinkedList<Object> child = children.get(oldParentId);
            if (child != null) {
                child.remove(itemId);
                if (child.isEmpty()) {
                    children.remove(oldParentId);
                }
            }
        }

        if (hasFilters()) {
            // Refilter the container if setParent is called when filters
            // are applied. Changing parent can change what is included in
            // the filtered version (if includeParentsWhenFiltering==true).
            doFilterContainer(hasFilters());
        }

        fireItemSetChange();

        return true;
    }

    /**
     * Checks for filters.
     *
     * @return true, if successful
     */
    private boolean hasFilters() {
        return filteredRoots != null;
    }

    /**
     * Moves a node (an Item) in the container immediately after a sibling node.
     * The two nodes must have the same parent in the container.
     *
     * @param itemId the identifier of the moved node (Item)
     * @param siblingId the identifier of the reference node (Item), after which
     * the other node will be located
     */
    public void moveAfterSibling(final Object itemId, final Object siblingId) {
        final Object parent2 = getParent(itemId);
        List<Object> childrenList;
        if (parent2 == null) {
            childrenList = roots;
        } else {
            childrenList = children.get(parent2);
        }
        if (siblingId == null) {
            childrenList.remove(itemId);
            childrenList.add(itemId);

        } else {
            final int oldIndex = childrenList.indexOf(itemId);
            final int indexOfSibling = childrenList.indexOf(siblingId);
            if (indexOfSibling == -1 && oldIndex == -1) {
                throw new IllegalArgumentException("Given identifiers no not have the same parent.");
            } else {
                int newIndex;
                if (oldIndex > indexOfSibling) {
                    newIndex = indexOfSibling + 1;
                } else {
                    newIndex = indexOfSibling;
                }
                childrenList.remove(oldIndex);
                childrenList.add(newIndex, itemId);

            }
        }
        fireItemSetChange();

    }

    /**
     * Creates a new Item into the Container, and assign it an automatic ID.
     *
     * @return the object
     */
    @Override
    public Object addItem() {
        disableContentsChangeEvents();
        try {
            final Object itemId = super.addItem();
            if (itemId == null) {
                return null;
            }

            if (!roots.contains(itemId)) {
                roots.add(itemId);
                if (filteredRoots != null && passesFilters(itemId)) {
                        filteredRoots.add(itemId);
                }
            }
            return itemId;
        } finally {
            enableAndFireContentsChangeEvents();
        }
    }

    /**
     * Sends an Item set change event to all registered interested listeners.
     *
     * @param event the event
     */
    @Override
    protected void fireItemSetChange(final ItemSetChangeEvent event) {
        if (contentsChangeEventsOn()) {
            super.fireItemSetChange(event);
        } else {
            contentsChangedEventPending = true;
        }
    }

    /**
     * Contents change events on.
     *
     * @return true, if successful
     */
    private boolean contentsChangeEventsOn() {
        return contentChangedEventsDisabledCount == 0;
    }

    /**
     * Disable contents change events.
     */
    private void disableContentsChangeEvents() {
        contentChangedEventsDisabledCount++;
    }

    /**
     * Enable and fire contents change events.
     */
    private void enableAndFireContentsChangeEvents() {
        if (contentChangedEventsDisabledCount <= Constants.ZERO) {
            getLogger().log(Level.WARNING, "Mismatched calls to disable and enable contents change events in HierarchicalContainer");
            contentChangedEventsDisabledCount = 0;
        } else {
            contentChangedEventsDisabledCount--;
        }
        if (contentChangedEventsDisabledCount == Constants.ZERO) {
            if (contentsChangedEventPending) {
                fireItemSetChange();
            }
            contentsChangedEventPending = false;
        }
    }

    /**
     * Creates a new Item with the given ID in the Container.
     *
     * @param itemId the item id
     * @return the bean item< e>
     */
    @Override
    public BeanItem<E> addItem(final Object itemId) {
        disableContentsChangeEvents();
        try {
            final Item item = super.addItem(itemId);
            if (item == null) {
                return null;
            }

            roots.add(itemId);

            if (filteredRoots != null && passesFilters(itemId)) {
                    filteredRoots.add(itemId);
            }
            return (BeanItem<E>) item;
        } finally {
            enableAndFireContentsChangeEvents();
        }
    }

    /**
     * Removes all Items from the Container.
     *
     * @return true, if removes the all items
     */
    @Override
    public boolean removeAllItems() {
        disableContentsChangeEvents();
        try {
            final boolean success = super.removeAllItems();

            if (success) {
                roots.clear();
                parent.clear();
                children.clear();
                noChildrenAllowed.clear();

                if (filteredRoots != null) {
                    filteredRoots = NULL_LINKED_LIST;
                }
                if (filteredChildren != null) {
                    filteredChildren = NULL_MAP;
                }
                if (filteredParent != null) {
                    filteredParent = NULL_MAP2;
                }
            }
            return success;
        } finally {
            enableAndFireContentsChangeEvents();
        }
    }

    /**
     * Removes the Item identified by ItemId from the Container.
     *
     * @param itemId the item id
     * @return true, if removes the item
     */
    @Override
    public boolean removeItem(final Object itemId) {
        disableContentsChangeEvents();
        try {
            final boolean success = super.removeItem(itemId);

            if (success) {
                // Remove from roots if this was a root
                if (roots.remove(itemId) && filteredRoots != null) {

                    // If filtering is enabled we might need to remove it from
                    // the filtered list also
                        filteredRoots.remove(itemId);
                }

                // Clear the children list. Old children will now become root
                // nodes
                final LinkedList<Object> childNodeIds = children.remove(itemId);
                if (childNodeIds != null) {
                    if (filteredChildren != null) {
                        filteredChildren.remove(itemId);
                    }
                    for (int i = 0; i < childNodeIds.size(); i++) {
                        final Object childId = childNodeIds.get(i);
                        setParent(childId, null);
                    }
                }

                // Parent of the item that we are removing will contain the item
                // id in its children list
                final Object parentItemId = parent.get(itemId);
                if (parentItemId != null) {
                    final LinkedList<Object> child = children.get(parentItemId);
                    if (child != null) {
                        child.remove(itemId);

                        if (child.isEmpty()) {
                            children.remove(parentItemId);
                        }

                        // Found in the children list so might also be in the
                        // filteredChildren list
                        if (filteredChildren != null) {
                            final LinkedList<Object> filteredChilds = filteredChildren.get(parentItemId);
                            if (filteredChilds != null) {
                                filteredChilds.remove(itemId);
                                if (filteredChilds.isEmpty()) {
                                    filteredChildren.remove(parentItemId);
                                }
                            }
                        }
                    }
                }
                parent.remove(itemId);
                if (filteredParent != null) {
                    // Item id no longer has a parent as the item id is not in
                    // the container.
                    filteredParent.remove(itemId);
                }
                noChildrenAllowed.remove(itemId);
            }

            return success;
        } finally {
            enableAndFireContentsChangeEvents();
        }
    }

    /**
     * Removes the Item identified by given itemId and all its children.
     *
     * @param itemId the identifier of the Item to be removed
     * @return true if the operation succeeded
     * @see #removeItem(Object)
     */
    public boolean removeItemRecursively(final Object itemId) {
        disableContentsChangeEvents();
        try {
            return removeItemRecursively(this, itemId);
        } finally {
            enableAndFireContentsChangeEvents();
        }
    }

    /**
     * Removes the Item identified by given itemId and all its children from the
     * given Container.
     *
     * @param container the container where the item is to be removed
     * @param itemId the identifier of the Item to be removed
     * @return true if the operation succeeded
     */
    public static boolean removeItemRecursively(final Container.Hierarchical container, final Object itemId) {
        boolean success = true;
        final Collection<?> children2 = container.getChildren(itemId);
        if (children2 != null) {

            final Object[] array = children2.toArray();
            for (int i = 0; i < array.length; i++) {
                final boolean removeItemRecursively = removeItemRecursively(container, array[i]);
                if (!removeItemRecursively) {
                    success = false;
                }
            }

        }
        if (success) {
            success = container.removeItem(itemId);
        }
        return success;
        // remove the root of subtree if children where succesfully removed

    }

    /**
     * Creates a new Item into the Container, and assign it an automatic ID.
     */
    @Override
    protected void doSort() {
        super.doSort();

        Collections.sort(roots, getItemSorter());
        for (final Iterator<LinkedList<Object>> iterator = children.values().iterator(); iterator.hasNext();) {
            final LinkedList<Object> childList = iterator.next();
            Collections.sort(childList, getItemSorter());
        }
    }

    /**
     * Used to control how filtering works.
     *
     * @return true if all parents for items that match the filter are included
     * when filtering, false if only the matching items are included
     * @see {@link #setIncludeParentsWhenFiltering(boolean)} for more
     * information.
     */
    public boolean isIncludeParentsWhenFiltering() {
        return includeParentsWhenFiltering;
    }

    /**
     * Controls how the filtering of the container works. Set this to true to
     *
     * @param includeParentsWhenFiltering true to include all parents for items
     * that match the filter, false to only include the matching items
     */
    public void setIncludeParentsWhenFiltering(final boolean includeParentsWhenFiltering) {
        this.includeParentsWhenFiltering = includeParentsWhenFiltering;
        if (filteredRoots != null) {
            // Currently filtered so needs to be re-filtered
            doFilterContainer(true);
        }
    }

    /**
     * Overridden to provide filtering for root & children items.
     *
     * @param hasFilters the has filters
     * @return true, if do filter container
     */
    @Override
    protected boolean doFilterContainer(final boolean hasFilters) {
        if (!hasFilters) {
            // All filters removed

            filteredRoots = NULL_LINKED_LIST;

            filteredChildren = NULL_MAP;

            filteredParent = NULL_MAP2;

            return super.doFilterContainer(hasFilters);
        }

        // Reset data structures
        filteredRoots = new LinkedList<Object>();
        filteredChildren = new HashMap<Object, LinkedList<Object>>();
        filteredParent = new HashMap<Object, Object>();

        if (includeParentsWhenFiltering) {
            // Filter so that parents for items that match the filter are also
            // included
            final HashSet<Object> includedItems = new HashSet<Object>();
            for (int i = 0; i < roots.size(); i++) {
                final Object rootId = roots.get(i);
                if (filterIncludingParents(rootId, includedItems)) {
                    filteredRoots.add(rootId);
                    addFilteredChildrenRecursively(rootId, includedItems);
                }
            }
            // includedItemIds now contains all the item ids that should be
            // included. Filter IndexedContainer based on this
            filterOverride = includedItems;
            super.doFilterContainer(hasFilters);
            return true;
        } else {
            // Filter by including all items that pass the filter and make items
            // with no parent new root items

            // Filter IndexedContainer first so getItemIds return the items that
            // match
            super.doFilterContainer(hasFilters);

            final LinkedHashSet<Object> filteredItemIds = new LinkedHashSet<Object>(getItemIds());

            for (final Iterator<Object> iterator = filteredItemIds.iterator(); iterator.hasNext();) {
                final Object itemId = iterator.next();
                final Object itemParent = parent.get(itemId);
                if (itemParent == null || !filteredItemIds.contains(itemParent)) {
                    // Parent is not included or this was a root, in both cases
                    // this should be a filtered root
                    filteredRoots.add(itemId);
                } else {
                    // Parent is included. Add this to the children list (create
                    // it first if necessary)
                    addFilteredChild(itemParent, itemId);
                }
            }

            return true;
        }
    }

    /**
     * Adds the given childItemId as a filteredChildren for the parentItemId and
     * sets it filteredParent.
     *
     * @param parentItemId the parent item id
     * @param childItemId the child item id
     */
    private void addFilteredChild(final Object parentItemId, final Object childItemId) {
        LinkedList<Object> parentToChildrenList = filteredChildren.get(parentItemId);
        if (parentToChildrenList == null) {
            parentToChildrenList = new LinkedList<Object>();
            filteredChildren.put(parentItemId, parentToChildrenList);
        }
        filteredParent.put(childItemId, parentItemId);
        parentToChildrenList.add(childItemId);

    }

    /**
     * Recursively adds all items in the includedItems list to the.
     *
     * @param parentItemId The item id to start recurse from. Not added to a
     * filteredChildren list
     * @param includedItems Set containing the item ids for the items that
     * should be included in the filteredChildren map
     */
    private void addFilteredChildrenRecursively(final Object parentItemId, final Set<Object> includedItems) {
        final LinkedList<Object> childList = children.get(parentItemId);
        if (childList == null) {
            return;
        }

        for (int i = 0; i < childList.size(); i++) {
            final Object childItemId = childList.get(i);
            if (includedItems.contains(childItemId)) {
                addFilteredChild(parentItemId, childItemId);
                addFilteredChildrenRecursively(childItemId, includedItems);
            }
        }
    }

    /**
     * Scans the itemId and all its children for which items should be included
     * when filtering. All items which passes the filters are included.
     *
     * @param itemId the item id
     * @param includedItems the included items
     * @return true if the itemId should be included in the filtered container.
     */
    private boolean filterIncludingParents(final Object itemId, final Set<Object> includedItems) {
        boolean toBeIncluded = passesFilters(itemId);
        
        final LinkedList<Object> childList = children.get(itemId);
        if (childList != null) {
            final LinkedList<Object> linkedList = children.get(itemId);
            for (int i = 0; i < linkedList.size(); i++) {
                final Object childItemId = linkedList.get(i);
                toBeIncluded |= filterIncludingParents(childItemId, includedItems);
            }
        }

        if (toBeIncluded) {
            includedItems.add(itemId);
        }
        return toBeIncluded;
    }

    /**
     * Checks if the given itemId passes the filters set for the container.
     *
     * @param itemId the item id
     * @return true, if passes filters
     */
    @Override
    protected boolean passesFilters(final Object itemId) {
        if (filterOverride == null) {
            return super.passesFilters(itemId);
        } else {
            return filterOverride.contains(itemId);
        }
    }

    /**
     * Gets the logger.
     *
     * @return the logger
     */
    private static final Logger getLogger() {
        return Logger.getLogger(HierarchicalContainer.class.getName());
    }

    /**
     * Changed from super.getItemIds() to new ArrayList<E>(super.getItemIds())
     * to support removal of itemIds
     *
     * @return the item ids
     */
    @Override
    public List<E> getItemIds() {
        return new ArrayList<E>(super.getItemIds());
    }

    /**
     * Gets the Property identified by the given itemId and propertyId from the
     * Container.
     *
     * @param itemId the item id
     * @param propertyId the property id
     * @return the container property
     */
    @Override
    public Property getContainerProperty(final Object itemId, final Object propertyId) {
        return super.getContainerProperty(itemId, propertyId);
    }

    /**
     * Adds a new Property to all Items in the Container.
     *
     * @param propertyId the property id
     * @param type the type
     * @param defaultValue the default value
     * @return true, if adds the container property
     * @throws UnsupportedOperationException the unsupported operation exception
     */
    @Override
    public boolean addContainerProperty(final Object propertyId, final Class<?> type, final Object defaultValue) throws UnsupportedOperationException {
        return super.addContainerProperty(propertyId, type, defaultValue);
    }
}
