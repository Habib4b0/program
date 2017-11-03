/*
 * Copyright 2014 Abhiram.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.vaadin.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.vaadin.data.Collapsible;
import com.vaadin.data.Container;
import com.vaadin.data.Container.Hierarchical;
import com.vaadin.data.util.ContainerHierarchicalWrapper;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.data.util.HierarchicalContainerOrderedWrapper;
import com.vaadin.server.PaintException;
import com.vaadin.server.PaintTarget;
import com.vaadin.shared.ui.treetable.TreeTableConstants;
import com.vaadin.ui.Tree.CollapseEvent;
import com.vaadin.ui.Tree.CollapseListener;
import com.vaadin.ui.Tree.ExpandEvent;
import com.vaadin.ui.Tree.ExpandListener;


/**
 * TreeTable extends the {@link Table} component so that it can also visualize a
 * hierarchy of its Items in a similar manner that {@link Tree} does. The tree
 * hierarchy is always displayed in the first actual column of the TreeTable.
 * <p>
 * The TreeTable supports the usual {@link Table} features like lazy loading, so
 * it should be no problem to display lots of items at once. Only required rows
 * and some cache rows are sent to the client.
 * <p>
 * TreeTable supports standard {@link Hierarchical} container interfaces, but
 * also a more fine tuned version - {@link Collapsible}. A container
 * implementing the {@link Collapsible} interface stores the collapsed/expanded
 * state internally and can this way scale better on the server side than with
 * standard Hierarchical implementations. Developer must however note that
 * {@link Collapsible} containers can not be shared among several users as they
 * share UI state in the container.
 * 
 * @author Abhiram.
 * 
 */
@SuppressWarnings({ "serial", "deprecation" })
public class ExtCustomTreeTable extends ExtCustomTable implements Hierarchical {
    
    /** The is tree row icon. */
    boolean isTreeRowIcon=true;
    
    /**
     * The Interface ContainerStrategy.
     */
    private interface ContainerStrategy extends Serializable {
        
        /**
         * Size.
         *
         * @return the int
         */
        public int size();

        /**
         * Checks if is node open.
         *
         * @param itemId the item id
         * @return true, if is node open
         */
        public boolean isNodeOpen(Object itemId);

        /**
         * Gets the depth.
         *
         * @param itemId the item id
         * @return the depth
         */
        public int getDepth(Object itemId);

        /**
         * Toggle child visibility.
         *
         * @param itemId the item id
         */
        public void toggleChildVisibility(Object itemId);

        /**
         * Gets the id by index.
         *
         * @param index the index
         * @return the id by index
         */
        public Object getIdByIndex(int index);

        /**
         * Index of id.
         *
         * @param id the id
         * @return the int
         */
        public int indexOfId(Object id);

        /**
         * Next item id.
         *
         * @param itemId the item id
         * @return the object
         */
        public Object nextItemId(Object itemId);

        /**
         * Last item id.
         *
         * @return the object
         */
        public Object lastItemId();

        /**
         * Prev item id.
         *
         * @param itemId the item id
         * @return the object
         */
        public Object prevItemId(Object itemId);

        /**
         * Checks if is last id.
         *
         * @param itemId the item id
         * @return true, if is last id
         */
        public boolean isLastId(Object itemId);

        /**
         * Gets the item ids.
         *
         * @return the item ids
         */
        public Collection<?> getItemIds();

        /**
         * Container item set change.
         *
         * @param event the event
         */
        public void containerItemSetChange(ItemSetChangeEvent event);
    }

    /**
     * The Class AbstractStrategy.
     */
    private abstract class AbstractStrategy implements ContainerStrategy {

        /**
         * Consider adding getDepth to {@link Collapsible}, might help
         * scalability with some container implementations.
         *
         * @param itemId1 the item id1
         * @return the depth
         */

        @Override
        public int getDepth(Object itemId1) {
            int depth = 0;
            Object itemId=itemId1;
            Hierarchical hierarchicalContainer = getContainerDataSource();
            while (!hierarchicalContainer.isRoot(itemId)) {
                depth++;
                itemId = hierarchicalContainer.getParent(itemId);
            }
            depth=getManualDepth(itemId1,depth);
            return depth;
        }

        /**
         * Container item set change.
         *
         * @param event the event
         */
        @Override
        public void containerItemSetChange(ItemSetChangeEvent event) {
        }
        
        /**
         * Gets the manual depth.
         *
         * @param itemId the item id
         * @param depth the depth
         * @return the manual depth
         */
        protected int getManualDepth(Object itemId,int depth) {
            return depth;
        }
    }

    /**
     * This strategy is used if current container implements {@link Collapsible}
     * .
     * 
     * open-collapsed logic diverted to container, otherwise use default
     * implementations.
     */
    protected class CollapsibleStrategy extends AbstractStrategy {

        /**
         * C.
         *
         * @return the collapsible
         */
        private Collapsible c() {
            return (Collapsible) getContainerDataSource();
        }

        /**
         * Toggle child visibility.
         *
         * @param itemId the item id
         */
        @Override
        public void toggleChildVisibility(Object itemId) {
            c().setCollapsed(itemId, !c().isCollapsed(itemId));
        }

        /**
         * Checks if is node open.
         *
         * @param itemId the item id
         * @return true, if is node open
         */
        @Override
        public boolean isNodeOpen(Object itemId) {
            return !c().isCollapsed(itemId);
        }

        /**
         * Size.
         *
         * @return the int
         */
        @Override
        public int size() {
            return ExtCustomTreeTable.super.size();
        }

        /**
         * Gets the id by index.
         *
         * @param index the index
         * @return the id by index
         */
        @Override
        public Object getIdByIndex(int index) {
            return ExtCustomTreeTable.super.getIdByIndex(index);
        }

        /**
         * Index of id.
         *
         * @param id the id
         * @return the int
         */
        @Override
        public int indexOfId(Object id) {
            return ExtCustomTreeTable.super.indexOfId(id);
        }

        /**
         * Checks if is last id.
         *
         * @param itemId the item id
         * @return true, if is last id
         */
        @Override
        public boolean isLastId(Object itemId) {
            // using the default impl
            return ExtCustomTreeTable.super.isLastId(itemId);
        }

        /**
         * Last item id.
         *
         * @return the object
         */
        @Override
        public Object lastItemId() {
            // using the default impl
            return ExtCustomTreeTable.super.lastItemId();
        }

        /**
         * Next item id.
         *
         * @param itemId the item id
         * @return the object
         */
        @Override
        public Object nextItemId(Object itemId) {
            return ExtCustomTreeTable.super.nextItemId(itemId);
        }

        /**
         * Prev item id.
         *
         * @param itemId the item id
         * @return the object
         */
        @Override
        public Object prevItemId(Object itemId) {
            return ExtCustomTreeTable.super.prevItemId(itemId);
        }

        /**
         * Gets the item ids.
         *
         * @return the item ids
         */
        @Override
        public Collection<?> getItemIds() {
            return ExtCustomTreeTable.super.getItemIds();
        }

    }

    /**
     * Strategy for Hierarchical but not Collapsible container like
     * {@link HierarchicalContainer}.
     * 
     * Store collapsed/open states internally, fool Table to use preorder when
     * accessing items from container via Ordered/Indexed methods.
     */
    protected class HierarchicalStrategy extends AbstractStrategy {

        /** The open items. */
        private final HashSet<Object> openItems = new HashSet<Object>();

        /**
         * Checks if is node open.
         *
         * @param itemId the item id
         * @return true, if is node open
         */
        @Override
        public boolean isNodeOpen(Object itemId) {
            return openItems.contains(itemId);
        }

        /**
         * Size.
         *
         * @return the int
         */
        @Override
        public int size() {
            int size=getPreOrder().size();
            return size;
        }

        /**
         * Gets the item ids.
         *
         * @return the item ids
         */
        @Override
        public Collection<Object> getItemIds() {
            return Collections.unmodifiableCollection(getPreOrder());
        }

        /**
         * Checks if is last id.
         *
         * @param itemId the item id
         * @return true, if is last id
         */
        @Override
        public boolean isLastId(Object itemId) {
            if (itemId == null) {
                return false;
            }

            return itemId.equals(lastItemId());
        }

        /**
         * Last item id.
         *
         * @return the object
         */
        @Override
        public Object lastItemId() {
            if (getPreOrder().size() > 0) {
                return getPreOrder().get(getPreOrder().size() - 1);
            } else {
                return null;
            }
        }

        /**
         * Next item id.
         *
         * @param itemId the item id
         * @return the object
         */
        @Override
        public Object nextItemId(Object itemId) {
            int indexOf = getPreOrder().indexOf(itemId);
            if (indexOf == -1) {
                return null;
            }
            indexOf++;
            if (indexOf == getPreOrder().size()) {
                return null;
            } else {
                return getPreOrder().get(indexOf);
            }
        }

        /**
         * Prev item id.
         *
         * @param itemId the item id
         * @return the object
         */
        @Override
        public Object prevItemId(Object itemId) {
            int indexOf = getPreOrder().indexOf(itemId);
            indexOf--;
            if (indexOf < 0) {
                return null;
            } else {
                return getPreOrder().get(indexOf);
            }
        }

        /**
         * Toggle child visibility.
         *
         * @param itemId the item id
         */
        @Override
        public void toggleChildVisibility(Object itemId) {
            boolean removed = openItems.remove(itemId);
            if (!removed) {
                openItems.add(itemId);
                getLogger().log(Level.FINEST, "Item {0} is now expanded",
                        itemId);
            } else {
                getLogger().log(Level.FINEST, "Item {0} is now collapsed",
                        itemId);
            }
            clearPreorderCache();
        }

        /**
         * Clear preorder cache.
         */
        private void clearPreorderCache() {
            preOrder = null; // clear preorder cache
        }

        /** The pre order. */
        protected List<Object> preOrder;

        /**
         * Preorder of ids currently visible.
         *
         * @return the pre order
         */
        private List<Object> getPreOrder() {
            if (preOrder == null) {
                preOrder = new ArrayList<Object>();
                Collection<?> rootItemIds = getContainerDataSource()
                        .rootItemIds();
                preparePreOrder(rootItemIds);
            }
            return preOrder;
        }
        
        /**
         * Adds the visible child tree.
         *
         * @param id the id
         */
        protected void addVisibleChildTree(Object id) {
            if (isNodeOpen(id)) {
                Collection<?> children = getContainerDataSource().getChildren(
                        id);
                if (children != null) {
                    prepareVisibleChildTree(children);
                }
            }

        }
        
        /**
         * Prepare pre order.
         *
         * @param rootItemIds the root item ids
         */
        protected void preparePreOrder(Collection<?> rootItemIds){
                for (Object id : rootItemIds) {
                    preOrder.add(id);
                    addVisibleChildTree(id);
                }
        }
        
        /**
         * Prepare visible child tree.
         *
         * @param children the children
         */
        protected void prepareVisibleChildTree(Collection<?> children){
                for (Object childId : children) {
                        preOrder.add(childId);
                        addVisibleChildTree(childId);
                    }
        }
        
        /**
         * Index of id.
         *
         * @param id the id
         * @return the int
         */
        @Override
        public int indexOfId(Object id) {
            return getPreOrder().indexOf(id);
        }

        /**
         * Gets the id by index.
         *
         * @param index the index
         * @return the id by index
         */
        @Override
        public Object getIdByIndex(int index) {
            return getPreOrder().get(index);
        }

        /**
         * Container item set change.
         *
         * @param event the event
         */
        @Override
        public void containerItemSetChange(ItemSetChangeEvent event) {
            // preorder becomes invalid on sort, item additions etc.
            clearPreorderCache();
            super.containerItemSetChange(event);
        }

    }

    /**
     * Creates an empty TreeTable with a default container.
     */
    public ExtCustomTreeTable() {
        super(null, new HierarchicalContainer());
    }

    /**
     * Creates an empty TreeTable with a default container.
     * 
     * @param caption
     *            the caption for the TreeTable
     */
    public ExtCustomTreeTable(String caption) {
        this();
        setCaption(caption);
    }

    /**
     * Creates a TreeTable instance with given captions and data source.
     * 
     * @param caption
     *            the caption for the component
     * @param dataSource
     *            the dataSource that is used to list items in the component
     */
    public ExtCustomTreeTable(String caption, Container dataSource) {
        super(caption, dataSource);
    }

    /** The c strategy. */
    private ContainerStrategy cStrategy;
    
    /** The focused row id. */
    private Object focusedRowId = null;
    
    /** The hierarchy column id. */
    private Object hierarchyColumnId;

    /**
     * The item id that was expanded or collapsed during this request. Reset at
     * the end of paint and only used for determining if a partial or full paint
     * should be done.
     * 
     * Can safely be reset to null whenever a change occurs that would prevent a
     * partial update from rendering the correct result, e.g. rows added or
     * removed during an expand operation.
     */
    private Object toggledItemId;
    
    /** The animations enabled. */
    private boolean animationsEnabled;
    
    /** The clear focused row pending. */
    private boolean clearFocusedRowPending;

    /**
     * If the container does not send item set change events, always do a full
     * repaint instead of a partial update when expanding/collapsing nodes.
     */
    private boolean containerSupportsPartialUpdates;
    
    /** The tree node multi click. */
    private boolean treeNodeMultiClick=true;
    
    /**
     * Gets the container strategy.
     *
     * @return the container strategy
     */
    private ContainerStrategy getContainerStrategy() {
        if (cStrategy == null) {
            if (getContainerDataSource() instanceof Collapsible) {
                cStrategy = getCollapsibleStrategy();
            } else {
                cStrategy = getHierarchicalStrategy();
            }
        }
        return cStrategy;
    }
    
    /**
     * Gets the collapsible strategy.
     *
     * @return the collapsible strategy
     */
    protected CollapsibleStrategy getCollapsibleStrategy() {
        return new CollapsibleStrategy();
    }
    
    /**
     * Gets the hierarchical strategy.
     *
     * @return the hierarchical strategy
     */
    protected HierarchicalStrategy getHierarchicalStrategy() {
        return new HierarchicalStrategy();
    }
    
    /**
     * Paint row attributes.
     *
     * @param target the target
     * @param itemId the item id
     * @throws PaintException the paint exception
     */
    @Override
    protected void paintRowAttributes(PaintTarget target, Object itemId)
            throws PaintException {
        super.paintRowAttributes(target, itemId);
        target.addAttribute("depth", getContainerStrategy().getDepth(itemId));
        if (getContainerDataSource().areChildrenAllowed(itemId)) {
            target.addAttribute("ca", true);
            target.addAttribute("open",
                    getContainerStrategy().isNodeOpen(itemId));
        }
    }

    /**
     * Paint row icon.
     *
     * @param target the target
     * @param cells the cells
     * @param indexInRowbuffer the index in rowbuffer
     * @throws PaintException the paint exception
     */
    @Override
    protected void paintRowIcon(PaintTarget target, Object[][] cells,
            int indexInRowbuffer) throws PaintException {
        if(isPaintTreeRowIcon()){
        // always paint if present (in parent only if row headers visible)
//        if (getRowHeaderMode() == ROW_HEADER_MODE_HIDDEN) {
//            Resource itemIcon = getItemIcon(cells[CELL_ITEMID][indexInRowbuffer]);
//            if (itemIcon != null) {
//                target.addAttribute("icon", itemIcon);
//            }
//        } else if (cells[CELL_ICON][indexInRowbuffer] != null) {
//            target.addAttribute("icon",
//                    (Resource) cells[CELL_ICON][indexInRowbuffer]);
//        }
        }
    }

    /**
     * Change variables.
     *
     * @param source the source
     * @param variables the variables
     */
    @Override
    public void changeVariables(Object source, Map<String, Object> variables) {
        super.changeVariables(source, variables);

        if (variables.containsKey("toggleCollapsed")) {
            
            String object = (String) variables.get("toggleCollapsed");
            Object itemId = itemIdMapper.get(object);
            toggledItemId = itemId;
            toggleChildVisibility(itemId, false);
            if (variables.containsKey("selectCollapsed")) {
                // ensure collapsed is selected unless opened with selection
                // head
                if (isSelectable()) {
                    select(itemId);
                }
            }
        } else if (variables.containsKey("focusParent")) {
            String key = (String) variables.get("focusParent");
            Object refId = itemIdMapper.get(key);
            Object itemId = getParent(refId);
            focusParent(itemId);
        }
    }

    /**
     * Focus parent.
     *
     * @param itemId the item id
     */
    private void focusParent(Object itemId) {
        boolean inView = false;
        Object inPageId = getCurrentPageFirstItemId();
        for (int i = 0; inPageId != null && i < getPageLength(); i++) {
            if (inPageId.equals(itemId)) {
                inView = true;
                break;
            }
            inPageId = nextItemId(inPageId);
            i++;
        }
        if (!inView) {
            setCurrentPageFirstItemId(itemId);
        }
        // Select the row if it is selectable.
        if (isSelectable()) {
            if (isMultiSelect()) {
                setValue(Collections.singleton(itemId));
            } else {
                setValue(itemId);
            }
        }
        setFocusedRow(itemId);
    }

    /**
     * Sets the focused row.
     *
     * @param itemId the new focused row
     */
    private void setFocusedRow(Object itemId) {
        focusedRowId = itemId;
        if (focusedRowId == null) {
            // Must still inform the client that the focusParent request has
            // been processed
            clearFocusedRowPending = true;
        }
        markAsDirty();
    }

    /**
     * Paint content.
     *
     * @param target the target
     * @throws PaintException the paint exception
     */
    @Override
    public void paintContent(PaintTarget target) throws PaintException {
        target.addAttribute("treeMultiClick", isTreeNodeMultiClick());
        target.addAttribute("treeRowIcon", isPaintTreeRowIcon());
        if (focusedRowId != null) {
            target.addAttribute("focusedRow", itemIdMapper.key(focusedRowId));
            focusedRowId = null;
        } else if (clearFocusedRowPending) {
            // Must still inform the client that the focusParent request has
            // been processed
            target.addAttribute("clearFocusPending", true);
            clearFocusedRowPending = false;
        }
        target.addAttribute("animate", animationsEnabled);
        if (hierarchyColumnId != null) {
            Object[] visibleColumns2 = getVisibleColumns();
            for (int i = 0; i < visibleColumns2.length; i++) {
                Object object = visibleColumns2[i];
                if (hierarchyColumnId.equals(object)) {
                    target.addAttribute(
                            TreeTableConstants.ATTRIBUTE_HIERARCHY_COLUMN_INDEX,
                            i);
                    break;
                }
            }
        }
        super.paintContent(target);
        toggledItemId = null;
    }

    /*
     * Override methods for partial row updates and additions when expanding /
     * collapsing nodes.
     */

    /**
     * Checks if is partial row update.
     *
     * @return true, if is partial row update
     */
    @Override
    protected boolean isPartialRowUpdate() {
        return toggledItemId != null && containerSupportsPartialUpdates
                && !isRowCacheInvalidated();
    }

    /**
     * Gets the first added item index.
     *
     * @return the first added item index
     */
    @Override
    protected int getFirstAddedItemIndex() {
        return indexOfId(toggledItemId) + 1;
    }

    /**
     * Gets the added row count.
     *
     * @return the added row count
     */
    @Override
    protected int getAddedRowCount() {
        return countSubNodesRecursively(getContainerDataSource(), toggledItemId);
    }

    /**
     * Count sub nodes recursively.
     *
     * @param hc the hc
     * @param itemId the item id
     * @return the int
     */
    private int countSubNodesRecursively(Hierarchical hc, Object itemId) {
        int count = 0;
        // we need the number of children for toggledItemId no matter if its
        // collapsed or expanded. Other items' children are only counted if the
        // item is expanded.
        if (getContainerStrategy().isNodeOpen(itemId)
                || itemId == toggledItemId) {
            Collection<?> children = hc.getChildren(itemId);
            if (children != null) {
                count += children != null ? children.size() : 0;
                for (Object id : children) {
                    count += countSubNodesRecursively(hc, id);
                }
            }
        }
        return count;
    }

    /**
     * Gets the first updated item index.
     *
     * @return the first updated item index
     */
    @Override
    protected int getFirstUpdatedItemIndex() {
        return indexOfId(toggledItemId);
    }

    /**
     * Gets the updated row count.
     *
     * @return the updated row count
     */
    @Override
    protected int getUpdatedRowCount() {
        return 1;
    }

    /**
     * Should hide added rows.
     *
     * @return true, if successful
     */
    @Override
    protected boolean shouldHideAddedRows() {
        return !getContainerStrategy().isNodeOpen(toggledItemId);
    }

    /**
     * Toggle child visibility.
     *
     * @param itemId the item id
     * @param forceFullRefresh the force full refresh
     */
    private void toggleChildVisibility(Object itemId, boolean forceFullRefresh) {
        getContainerStrategy().toggleChildVisibility(itemId);
        // ensure that page still has first item in page, DON'T clear the
        // caches.
        setCurrentPageFirstItemIndex(getCurrentPageFirstItemIndex(), false);

        if (isCollapsed(itemId)) {
            fireCollapseEvent(itemId);
        } else {
            fireExpandEvent(itemId);
        }

        if (containerSupportsPartialUpdates && !forceFullRefresh) {
            markAsDirty();
        } else {
            // For containers that do not send item set change events, always do
            // full repaint instead of partial row update.
            refreshRowCache();
        }
    }

    /**
     * Size.
     *
     * @return the int
     */
    @Override
    public int size() {
        return getContainerStrategy().size();
    }

    /**
     * Gets the container data source.
     *
     * @return the container data source
     */
    @Override
    public Hierarchical getContainerDataSource() {
        return (Hierarchical) super.getContainerDataSource();
    }

    /**
     * Sets the container data source.
     *
     * @param newDataSource the new container data source
     */
    @Override
    public void setContainerDataSource(Container newDataSource) {
        cStrategy = null;

        // FIXME: This disables partial updates until TreeTable is fixed so it
        // does not change component hierarchy during paint
        containerSupportsPartialUpdates = (newDataSource instanceof ItemSetChangeNotifier) && false;

        if (newDataSource != null && !(newDataSource instanceof Hierarchical)) {
            newDataSource = new ContainerHierarchicalWrapper(newDataSource);
        }

        if (newDataSource != null && !(newDataSource instanceof Ordered)) {
            newDataSource = new HierarchicalContainerOrderedWrapper(
                    (Hierarchical) newDataSource);
        }

        super.setContainerDataSource(newDataSource);
    }

    /**
     * Container item set change.
     *
     * @param event the event
     */
    @Override
    public void containerItemSetChange(
            com.vaadin.data.Container.ItemSetChangeEvent event) {
        // Can't do partial repaints if items are added or removed during the
        // expand/collapse request
        toggledItemId = null;
        getContainerStrategy().containerItemSetChange(event);
        super.containerItemSetChange(event);
    }

    /**
     * Gets the id by index.
     *
     * @param index the index
     * @return the id by index
     */
    @Override
    protected Object getIdByIndex(int index) {
        return getContainerStrategy().getIdByIndex(index);
    }

    /**
     * Index of id.
     *
     * @param itemId the item id
     * @return the int
     */
    @Override
    protected int indexOfId(Object itemId) {
        return getContainerStrategy().indexOfId(itemId);
    }

    /**
     * Next item id.
     *
     * @param itemId the item id
     * @return the object
     */
    @Override
    public Object nextItemId(Object itemId) {
        return getContainerStrategy().nextItemId(itemId);
    }

    /**
     * Last item id.
     *
     * @return the object
     */
    @Override
    public Object lastItemId() {
        return getContainerStrategy().lastItemId();
    }

    /**
     * Prev item id.
     *
     * @param itemId the item id
     * @return the object
     */
    @Override
    public Object prevItemId(Object itemId) {
        return getContainerStrategy().prevItemId(itemId);
    }

    /**
     * Checks if is last id.
     *
     * @param itemId the item id
     * @return true, if is last id
     */
    @Override
    public boolean isLastId(Object itemId) {
        return getContainerStrategy().isLastId(itemId);
    }

    /**
     * Gets the item ids.
     *
     * @return the item ids
     */
    @Override
    public Collection<?> getItemIds() {
        return getContainerStrategy().getItemIds();
    }

    /**
     * Are children allowed.
     *
     * @param itemId the item id
     * @return true, if successful
     */
    @Override
    public boolean areChildrenAllowed(Object itemId) {
        return getContainerDataSource().areChildrenAllowed(itemId);
    }

    /**
     * Gets the children.
     *
     * @param itemId the item id
     * @return the children
     */
    @Override
    public Collection<?> getChildren(Object itemId) {
        return getContainerDataSource().getChildren(itemId);
    }

    /**
     * Gets the parent.
     *
     * @param itemId the item id
     * @return the parent
     */
    @Override
    public Object getParent(Object itemId) {
        return getContainerDataSource().getParent(itemId);
    }

    /**
     * Checks for children.
     *
     * @param itemId the item id
     * @return true, if successful
     */
    @Override
    public boolean hasChildren(Object itemId) {
        return getContainerDataSource().hasChildren(itemId);
    }

    /**
     * Checks if is root.
     *
     * @param itemId the item id
     * @return true, if is root
     */
    @Override
    public boolean isRoot(Object itemId) {
        return getContainerDataSource().isRoot(itemId);
    }

    /**
     * Root item ids.
     *
     * @return the collection
     */
    @Override
    public Collection<?> rootItemIds() {
        return getContainerDataSource().rootItemIds();
    }

    /**
     * Sets the children allowed.
     *
     * @param itemId the item id
     * @param areChildrenAllowed the are children allowed
     * @return true, if successful
     * @throws UnsupportedOperationException the unsupported operation exception
     */
    @Override
    public boolean setChildrenAllowed(Object itemId, boolean areChildrenAllowed)
            throws UnsupportedOperationException {
        return getContainerDataSource().setChildrenAllowed(itemId,
                areChildrenAllowed);
    }

    /**
     * Sets the parent.
     *
     * @param itemId the item id
     * @param newParentId the new parent id
     * @return true, if successful
     * @throws UnsupportedOperationException the unsupported operation exception
     */
    @Override
    public boolean setParent(Object itemId, Object newParentId)
            throws UnsupportedOperationException {
        return getContainerDataSource().setParent(itemId, newParentId);
    }

    /**
     * Sets the Item specified by given identifier as collapsed or expanded. If
     * the Item is collapsed, its children are not displayed to the user.
     * 
     * @param itemId
     *            the identifier of the Item
     * @param collapsed
     *            true if the Item should be collapsed, false if expanded
     */
    public void setCollapsed(Object itemId, boolean collapsed) {
        if (isCollapsed(itemId) != collapsed) {
            if (null == toggledItemId && !isRowCacheInvalidated()
                    && getVisibleItemIds().contains(itemId)) {
                // optimization: partial refresh if only one item is
                // collapsed/expanded
                toggledItemId = itemId;
                toggleChildVisibility(itemId, false);
            } else {
                // make sure a full refresh takes place - otherwise neither
                // partial nor full repaint of table content is performed
                toggledItemId = null;
                toggleChildVisibility(itemId, true);
            }
        }
    }

    /**
     * Checks if Item with given identifier is collapsed in the UI.
     * 
     * <p>
     * 
     * @param itemId
     *            the identifier of the checked Item
     * @return true if the Item with given id is collapsed
     * @see Collapsible#isCollapsed(Object)
     */
    public boolean isCollapsed(Object itemId) {
        return !getContainerStrategy().isNodeOpen(itemId);
    }

    /**
     * Explicitly sets the column in which the TreeTable visualizes the
     * hierarchy. If hierarchyColumnId is not set, the hierarchy is visualized
     * in the first visible column.
     *
     * @param hierarchyColumnId the new hierarchy column
     */
    public void setHierarchyColumn(Object hierarchyColumnId) {
        this.hierarchyColumnId = hierarchyColumnId;
    }

    /**
     * Gets the hierarchy column id.
     *
     * @return the identifier of column into which the hierarchy will be
     *         visualized or null if the column is not explicitly defined.
     */
    public Object getHierarchyColumnId() {
        return hierarchyColumnId;
    }

    /**
     * Adds an expand listener.
     * 
     * @param listener
     *            the Listener to be added.
     */
    public void addExpandListener(ExpandListener listener) {
        addListener(ExpandEvent.class, listener, ExpandListener.EXPAND_METHOD);
    }

    /**
     * Adds the listener.
     *
     * @param listener the listener
     * @deprecated As of 7.0, replaced by
     *             {@link #addExpandListener(ExpandListener)}
     */
    @Deprecated
    public void addListener(ExpandListener listener) {
        addExpandListener(listener);
    }

    /**
     * Removes an expand listener.
     * 
     * @param listener
     *            the Listener to be removed.
     */
    public void removeExpandListener(ExpandListener listener) {
        removeListener(ExpandEvent.class, listener,
                ExpandListener.EXPAND_METHOD);
    }

    /**
     * Removes the listener.
     *
     * @param listener the listener
     * @deprecated As of 7.0, replaced by
     *             {@link #removeExpandListener(ExpandListener)}
     */
    @Deprecated
    public void removeListener(ExpandListener listener) {
        removeExpandListener(listener);
    }

    /**
     * Emits an expand event.
     * 
     * @param itemId
     *            the item id.
     */
    protected void fireExpandEvent(Object itemId) {
        fireEvent(new ExpandEvent(this, itemId));
    }

    /**
     * Adds a collapse listener.
     * 
     * @param listener
     *            the Listener to be added.
     */
    public void addCollapseListener(CollapseListener listener) {
        addListener(CollapseEvent.class, listener,
                CollapseListener.COLLAPSE_METHOD);
    }

    /**
     * Adds the listener.
     *
     * @param listener the listener
     * @deprecated As of 7.0, replaced by
     *             {@link #addCollapseListener(CollapseListener)}
     */
    @Deprecated
    public void addListener(CollapseListener listener) {
        addCollapseListener(listener);
    }

    /**
     * Removes a collapse listener.
     * 
     * @param listener
     *            the Listener to be removed.
     */
    public void removeCollapseListener(CollapseListener listener) {
        removeListener(CollapseEvent.class, listener,
                CollapseListener.COLLAPSE_METHOD);
    }

    /**
     * Removes the listener.
     *
     * @param listener the listener
     * @deprecated As of 7.0, replaced by
     *             {@link #removeCollapseListener(CollapseListener)}
     */
    @Deprecated
    public void removeListener(CollapseListener listener) {
        removeCollapseListener(listener);
    }

    /**
     * Emits a collapse event.
     * 
     * @param itemId
     *            the item id.
     */
    protected void fireCollapseEvent(Object itemId) {
        fireEvent(new CollapseEvent(this, itemId));
    }

    /**
     * Checks if is animations enabled.
     *
     * @return true if animations are enabled
     */
    public boolean isAnimationsEnabled() {
        return animationsEnabled;
    }

    /**
     * Animations can be enabled by passing true to this method. Currently
     * expanding rows slide in from the top and collapsing rows slide out the
     * same way. NOTE! not supported in Internet Explorer 6 or 7.
     * 
     * @param animationsEnabled
     *            true or false whether to enable animations or not.
     */
    public void setAnimationsEnabled(boolean animationsEnabled) {
        this.animationsEnabled = animationsEnabled;
        markAsDirty();
    }

    /**
     * Gets the logger.
     *
     * @return the logger
     */
    private static final Logger getLogger() {
        return Logger.getLogger(TreeTable.class.getName());
    }

    /**
     * Gets the item ids.
     *
     * @param firstIndex the first index
     * @param rows the rows
     * @return the item ids
     */
    @Override
    protected List<Object> getItemIds(int firstIndex, int rows) {
        List<Object> itemIds = new ArrayList<Object>();
        for (int i = firstIndex; i < firstIndex + rows; i++) {
            itemIds.add(getIdByIndex(i));
        }
        return itemIds;
    }
    
    /**
     * Paint tree row icon.
     *
     * @param isTreeRowIcon the is tree row icon
     */
    public void paintTreeRowIcon(boolean isTreeRowIcon){
        this.isTreeRowIcon=isTreeRowIcon;
    }
    
    /**
     * Checks if is paint tree row icon.
     *
     * @return true, if is paint tree row icon
     */
    public boolean isPaintTreeRowIcon(){
        return isTreeRowIcon;
    }

    /**
     * Checks if is tree node multi click.
     *
     * @return true, if is tree node multi click
     */
    public boolean isTreeNodeMultiClick() {
        return treeNodeMultiClick;
    }

    /**
     * Sets the tree node multi click.
     *
     * @param treeNodeMultiClick the new tree node multi click
     */
    public void setTreeNodeMultiClick(boolean treeNodeMultiClick) {
        this.treeNodeMultiClick = treeNodeMultiClick;
    }
    
    

}
