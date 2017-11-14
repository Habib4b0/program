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

package org.asi.ui.extfilteringtable.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ApplicationConnection;
import com.vaadin.client.ComputedStyle;
import com.vaadin.client.UIDL;
import com.vaadin.client.Util;
import org.asi.ui.extfilteringtable.client.VExtCustomScrollTable.VScrollTableBody.VScrollTableRow;


/**
 * The Class VExtFilterTreeTable.
 *
 * @author Abhiram
 */
public class VExtFilterTreeTable extends VExtFilterTable {

    /** For internal use only. May be removed or replaced in the future. */
    public static class PendingNavigationEvent {
        
        /** The keycode. */
        public final int keycode;
        
        /** The ctrl. */
        public final boolean ctrl;
        
        /** The shift. */
        public final boolean shift;
         
         /** The client. */
         protected ApplicationConnection client;
        
        /**
         * Instantiates a new pending navigation event.
         *
         * @param keycode the keycode
         * @param ctrl the ctrl
         * @param shift the shift
         */
        public PendingNavigationEvent(int keycode, boolean ctrl, boolean shift) {
            this.keycode = keycode;
            this.ctrl = ctrl;
            this.shift = shift;
        }

        /**
         * To string.
         *
         * @return the string
         */
        @Override
        public String toString() {
            String string = "Keyboard event: " + keycode;
            if (ctrl) {
                string += " + ctrl";
            }
            if (shift) {
                string += " + shift";
            }
            return string;
        }
    }
    
    /** For internal use only. May be removed or replaced in the future. */
    public boolean collapseRequest;

    /** The selection pending. */
    private boolean selectionPending;

    /** For internal use only. May be removed or replaced in the future. */
    public int colIndexOfHierarchy;

    /** For internal use only. May be removed or replaced in the future. */
    public String collapsedRowKey;

    /** For internal use only. May be removed or replaced in the future. */
    public VTreeTableScrollBody scrollBody;

    /** For internal use only. May be removed or replaced in the future. */
    public boolean animationsEnabled;

    /** For internal use only. May be removed or replaced in the future. */
    public LinkedList<PendingNavigationEvent> pendingNavigationEvents = new LinkedList<VExtFilterTreeTable.PendingNavigationEvent>();

    /** For internal use only. May be removed or replaced in the future. */
    public boolean focusParentResponsePending;
    
    /** The row icon. */
    public boolean rowIcon=true;
    
    /** The tree multi click. */
    boolean treeMultiClick=true;
    
    /**
     * Creates the scroll body.
     *
     * @return the v tree table scroll body
     */
    @Override
    protected VTreeTableScrollBody createScrollBody() {
        scrollBody = new VTreeTableScrollBody();
        return scrollBody;
    }

    /*
     * Overridden to allow animation of expands and collapses of nodes.
     */
    /**
     * Adds the and remove rows.
     *
     * @param partialRowAdditions the partial row additions
     */
    @Override
    public void addAndRemoveRows(UIDL partialRowAdditions) {
        if (partialRowAdditions == null) {
            return;
        }

        if (animationsEnabled) {
            if (partialRowAdditions.hasAttribute("hide")) {
                scrollBody.unlinkRowsAnimatedAndUpdateCacheWhenFinished(
                        partialRowAdditions.getIntAttribute("firstprowix"),
                        partialRowAdditions.getIntAttribute("numprows"));
            } else {
                scrollBody.insertRowsAnimated(partialRowAdditions,
                        partialRowAdditions.getIntAttribute("firstprowix"),
                        partialRowAdditions.getIntAttribute("numprows"));
                discardRowsOutsideCacheWindow();
            }
        } else {
            super.addAndRemoveRows(partialRowAdditions);
        }
    }

    /**
     * Gets the hierarchy column index.
     *
     * @return the hierarchy column index
     */
    @Override
    protected int getHierarchyColumnIndex() {
        return colIndexOfHierarchy + (showRowHeaders ? 1 : 0);
    }

    /**
     * The Class VTreeTableScrollBody.
     */
    public class VTreeTableScrollBody extends
            VExtCustomScrollTable.VScrollTableBody {
        
        /** The indent width. */
        private int indentWidth = -1;
        
        /** The max indent. */
        private int maxIndent = 0;

        /**
         * Instantiates a new v tree table scroll body.
         */
        VTreeTableScrollBody() {
            super();
        }

        /**
         * Creates the row.
         *
         * @param uidl the uidl
         * @param aligns2 the aligns2
         * @return the v scroll table row
         */
        @Override
        protected VScrollTableRow createRow(UIDL uidl, char[] aligns2) {
            if (uidl.hasAttribute("gen_html")) {
                // This is a generated row.
                return new VTreeTableGeneratedRow(uidl, aligns2);
            }
            return new VTreeTableRow(uidl, aligns2);
        }

        /**
         * The Class VTreeTableRow.
         */
        public class VTreeTableRow extends
                VExtCustomScrollTable.VScrollTableBody.VScrollTableRow {

            /** The is tree cell added. */
            private boolean isTreeCellAdded = false;
            
            /** The tree spacer. */
            private SpanElement treeSpacer;
            
            /** The open. */
            private boolean open;
            
            /** The depth. */
            private int depth;
            
            /** The can have children. */
            private boolean canHaveChildren;
            
            /** The widget in hierarchy column. */
            protected Widget widgetInHierarchyColumn;

            /**
             * Instantiates a new v tree table row.
             *
             * @param uidl the uidl
             * @param aligns2 the aligns2
             */
            public VTreeTableRow(UIDL uidl, char[] aligns2) {
                super(uidl, aligns2);
            }

           /**
            * Adds the cell.
            *
            * @param rowUidl the row uidl
            * @param text the text
            * @param align the align
            * @param style the style
            * @param textIsHTML the text is html
            * @param isSorted the is sorted
            * @param description the description
            */
           @Override
            public void addCell(UIDL rowUidl, String text, char align,
                    String style, boolean textIsHTML, boolean isSorted,
                    String description) {
                super.addCell(rowUidl, text, align, style, textIsHTML,
                        isSorted, description);
                addTreeSpacer(rowUidl);
            }

            /**
             * Adds the tree spacer.
             *
             * @param rowUidl the row uidl
             * @return true, if successful
             */
            protected boolean addTreeSpacer(UIDL rowUidl) {
                if (cellShowsTreeHierarchy(getElement().getChildCount() - 1)) {
                    Element container = (Element) getElement().getLastChild()
                            .getFirstChild();
                   
                    if (rowUidl.hasAttribute("icon")) {
                        // icons are in first content cell in TreeTable
                        ImageElement icon = Document.get().createImageElement();
                        icon.setClassName("v-icon");
                        icon.setAlt("icon");
                        icon.setSrc(client.translateVaadinUri(rowUidl
                                .getStringAttribute("icon")));
                        container.insertFirst(icon);
                    }

                    String classname = "v-treetable-treespacer";
                    if (rowUidl.getBooleanAttribute("ca")) {
                        canHaveChildren = true;
                        open = rowUidl.getBooleanAttribute("open");
                        classname += open ? " v-treetable-node-open"
                                : " v-treetable-node-closed";
                    }

                    treeSpacer = Document.get().createSpanElement();

                    treeSpacer.setClassName(classname);
                     if (rowIcon) {
                        container.insertFirst(treeSpacer);
                     }
                    depth = rowUidl.hasAttribute("depth")&&rowIcon ? rowUidl
                            .getIntAttribute("depth") : 0;
                    setIndent();
                    isTreeCellAdded = true;
                    return true;
                }
                return false;
            }

            /**
             * Cell shows tree hierarchy.
             *
             * @param curColIndex the cur col index
             * @return true, if successful
             */
            private boolean cellShowsTreeHierarchy(int curColIndex) {
                if (isTreeCellAdded) {
                    return false;
                }  
                return curColIndex == getHierarchyColumnIndex();
            }

        /**
         * On browser event.
         *
         * @param event the event
         */
        @Override
            public void onBrowserEvent(Event event) {
                if (event.getEventTarget().cast() == treeSpacer
                        && treeSpacer.getClassName().contains("node")) {
                    if (event.getTypeInt() == Event.ONMOUSEUP&&(treeMultiClick||!collapseRequest)) {
                        sendToggleCollapsedUpdate(getKey());
                    }
                    return;
                }
                super.onBrowserEvent(event);
            }

     /**
      * Adds the cell.
      *
      * @param rowUidl the row uidl
      * @param w the w
      * @param align the align
      * @param style the style
      * @param isSorted the is sorted
      * @param description the description
      */
     @Override
            public void addCell(UIDL rowUidl, Widget w, char align,
                    String style, boolean isSorted, String description) {
                super.addCell(rowUidl, w, align, style, isSorted, description);
                if (addTreeSpacer(rowUidl)) {
                    widgetInHierarchyColumn = w;
                }

            }

            /**
             * Sets the indent.
             */
            private void setIndent() {
                if (getIndentWidth() > 0) {
                    treeSpacer.getParentElement().getStyle()
                            .setPaddingLeft(getIndent(), Unit.PX);
                    treeSpacer.getStyle().setWidth(getIndent(), Unit.PX);
                    int colWidth = getColWidth(getHierarchyColumnIndex());
                    if (colWidth > 0 && getIndent() > colWidth) {
                        VExtFilterTreeTable.this.setColWidth(
                                getHierarchyColumnIndex(), getIndent(), false);
                    }
                }
            }

         /**
          * On attach.
          */
         @Override
            protected void onAttach() {
                super.onAttach();
                if (getIndentWidth() < 0) {
                    detectIndent(this);
                    // If we detect indent here then the size of the hierarchy
                    // column is still wrong as it has been set when the indent
                    // was not known.
                    int w = getCellWidthFromDom(getHierarchyColumnIndex());
                    if (w >= 0) {
                        setColWidth(getHierarchyColumnIndex(), w);
                    }
                }
            }

            /**
             * Gets the cell width from dom.
             *
             * @param cellIndex the cell index
             * @return the cell width from dom
             */
            private int getCellWidthFromDom(int cellIndex) {
                final Element cell = DOM.getChild(getElement(), cellIndex);
                String w = cell.getStyle().getProperty("width");
                if (w == null || "".equals(w) || !w.endsWith("px")) {
                    return -1;
                } else {
                    return Integer.parseInt(w.substring(0, w.length() - 2));
                }
            }

            /**
             * Gets the hierarchy and icon width.
             *
             * @return the hierarchy and icon width
             */
            private int getHierarchyAndIconWidth() {
                int consumedSpace = treeSpacer.getOffsetWidth();
                if (treeSpacer.getParentElement().getChildCount() > 2) {
                    // icon next to tree spacer
                    consumedSpace += ((com.google.gwt.dom.client.Element) treeSpacer
                            .getNextSibling()).getOffsetWidth();
                }
                return consumedSpace;
            }
        
            /**
             * Sets the cell width.
             *
             * @param cellIx the cell ix
             * @param width the width
             */
            @Override
            protected void setCellWidth(int cellIx, int width) {
                if (cellIx == getHierarchyColumnIndex()) {
                    // take indentation padding into account if this is the
                    // hierarchy column
                    int indent = getIndent();
                    if (indent != -1) {
                        width = Math.max(width - indent, 0);
                    }
                }
                super.setCellWidth(cellIx, width);
            }

            /**
             * Gets the indent.
             *
             * @return the indent
             */
            private int getIndent() {
                return (depth + 1) * getIndentWidth();
            }
        }

        /**
         * The Class VTreeTableGeneratedRow.
         */
        protected class VTreeTableGeneratedRow extends VTreeTableRow {
            
            /** The span columns. */
            private boolean spanColumns;
            
            /** The html content allowed. */
            private boolean htmlContentAllowed;

            /**
             * Instantiates a new v tree table generated row.
             *
             * @param uidl the uidl
             * @param aligns the aligns
             */
            public VTreeTableGeneratedRow(UIDL uidl, char[] aligns) {
                super(uidl, aligns);
                addStyleName("v-table-generated-row");
            }

            /**
             * Checks if is span columns.
             *
             * @return true, if is span columns
             */
            public boolean isSpanColumns() {
                return spanColumns;
            }

         /**
          * Inits the cell widths.
          */
         @Override
            protected void initCellWidths() {
                if (spanColumns) {
                    setSpannedColumnWidthAfterDOMFullyInited();
                } else {
                    super.initCellWidths();
                }
            }

            /**
             * Sets the spanned column width after dom fully inited.
             */
            private void setSpannedColumnWidthAfterDOMFullyInited() {
                // Defer setting width on spanned columns to make sure that
                // they are added to the DOM before trying to calculate
                // widths.
                Scheduler.get().scheduleDeferred(new ScheduledCommand() {

                    @Override
                    public void execute() {
                        if (showRowHeaders) {
                            setCellWidth(0, tHead.getHeaderCell(0)
                                    .getWidthWithIndent());
                            calcAndSetSpanWidthOnCell(1);
                        } else {
                            calcAndSetSpanWidthOnCell(0);
                        }
                    }
                });
            }

        /**
         * Checks if is render html in cells.
         *
         * @return true, if is render html in cells
         */
        @Override
            protected boolean isRenderHtmlInCells() {
                return htmlContentAllowed;
            }

          /**
           * Adds the cells from uidl.
           *
           * @param uidl the uidl
           * @param aligns the aligns
           * @param col the col
           * @param visibleColumnIndex the visible column index
           */
          @Override
            protected void addCellsFromUIDL(UIDL uidl, char[] aligns, int col,
                    int visibleColumnIndex) {
                htmlContentAllowed = uidl.getBooleanAttribute("gen_html");
                spanColumns = uidl.getBooleanAttribute("gen_span");

                final Iterator<?> cells = uidl.getChildIterator();
                if (spanColumns) {
                    int colCount = uidl.getChildCount();
                    if (cells.hasNext()) {
                        final Object cell = cells.next();
                        if (cell instanceof String) {
                            addSpannedCell(uidl, cell.toString(), aligns[0],
                                    "", htmlContentAllowed, false, null,
                                    colCount);
                        } else {
                            addSpannedCell(uidl, (Widget) cell, aligns[0], "",
                                    false, colCount);
                        }
                    }
                } else {
                    super.addCellsFromUIDL(uidl, aligns, col,
                            visibleColumnIndex);
                }
            }

            /**
             * Adds the spanned cell.
             *
             * @param rowUidl the row uidl
             * @param w the w
             * @param align the align
             * @param style the style
             * @param sorted the sorted
             * @param colCount the col count
             */
            private void addSpannedCell(UIDL rowUidl, Widget w, char align,
                    String style, boolean sorted, int colCount) {
                TableCellElement td = DOM.createTD().cast();
                td.setColSpan(colCount);
                initCellWithWidget(w, align, style, sorted, td);
                td.getStyle().setHeight(getRowHeight(), Unit.PX);
                if (addTreeSpacer(rowUidl)) {
                    widgetInHierarchyColumn = w;
                }
            }

            /**
             * Adds the spanned cell.
             *
             * @param rowUidl the row uidl
             * @param text the text
             * @param align the align
             * @param style the style
             * @param textIsHTML the text is html
             * @param sorted the sorted
             * @param description the description
             * @param colCount the col count
             */
            private void addSpannedCell(UIDL rowUidl, String text, char align,
                    String style, boolean textIsHTML, boolean sorted,
                    String description, int colCount) {
                // String only content is optimized by not using Label widget
                final TableCellElement td = DOM.createTD().cast();
                td.setColSpan(colCount);
                initCellWithText(text, align, style, textIsHTML, sorted,
                        description, td);
                td.getStyle().setHeight(getRowHeight(), Unit.PX);
                addTreeSpacer(rowUidl);
            }

            /**
             * Sets the cell width.
             *
             * @param cellIx the cell ix
             * @param width the width
             */
            @Override
            protected void setCellWidth(int cellIx, int width) {
                if (isSpanColumns()) {
                    if (showRowHeaders) {
                        if (cellIx == 0) {
                            super.setCellWidth(0, width);
                        } else {
                            // We need to recalculate the spanning TDs width for
                            // every cellIx in order to support column resizing.
                            calcAndSetSpanWidthOnCell(1);
                        }
                    } else {
                        // Same as above.
                        calcAndSetSpanWidthOnCell(0);
                    }
                } else {
                    super.setCellWidth(cellIx, width);
                }
            }

            /**
             * Calc and set span width on cell.
             *
             * @param cellIx the cell ix
             */
            private void calcAndSetSpanWidthOnCell(final int cellIx) {
                int spanWidth = 0;
                for (int ix = (showRowHeaders ? 1 : 0); ix < tHead
                        .getVisibleCellCount(); ix++) {
                    spanWidth += tHead.getHeaderCell(ix).getOffsetWidth();
                }
                Util.setWidthExcludingPaddingAndBorder((Element) getElement()
                        .getChild(cellIx), spanWidth, 13, false);
            }
        }

        /**
         * Gets the indent width.
         *
         * @return the indent width
         */
        private int getIndentWidth() {
            return indentWidth;
        }

       /**
        * Gets the max indent.
        *
        * @return the max indent
        */
       @Override
        protected int getMaxIndent() {
            return maxIndent;
        }

     /**
      * Calculate max indent.
      */
     @Override
        protected void calculateMaxIndent() {
            int maxIndent = 0;
            Iterator<Widget> iterator = iterator();
            while (iterator.hasNext()) {
                VTreeTableRow next = (VTreeTableRow) iterator.next();
                maxIndent = Math.max(maxIndent, next.getIndent());
            }
            this.maxIndent = maxIndent;
        }

        /**
         * Detect indent.
         *
         * @param vTreeTableRow the v tree table row
         */
        private void detectIndent(VTreeTableRow vTreeTableRow) {
            indentWidth = vTreeTableRow.treeSpacer.getOffsetWidth();
            if (indentWidth == 0) {
                indentWidth = -1;
                return;
            }
            Iterator<Widget> iterator = iterator();
            while (iterator.hasNext()) {
                VTreeTableRow next = (VTreeTableRow) iterator.next();
                next.setIndent();
            }
            calculateMaxIndent();
        }

        /**
         * Unlink rows animated and update cache when finished.
         *
         * @param firstIndex the first index
         * @param rows the rows
         */
        protected void unlinkRowsAnimatedAndUpdateCacheWhenFinished(
                final int firstIndex, final int rows) {
            List<VScrollTableRow> rowsToDelete = new ArrayList<VScrollTableRow>();
            for (int ix = firstIndex; ix < firstIndex + rows; ix++) {
                VScrollTableRow row = getRowByRowIndex(ix);
                if (row != null) {
                    rowsToDelete.add(row);
                }
            }
            if (!rowsToDelete.isEmpty()) {
                // #8810 Only animate if there's something to animate
                RowCollapseAnimation anim = new RowCollapseAnimation(
                        rowsToDelete) {
                    @Override
                    protected void onComplete() {
                        super.onComplete();
                        // Actually unlink the rows and update the cache after
                        // the
                        // animation is done.
                        unlinkAndReindexRows(firstIndex, rows);
                        discardRowsOutsideCacheWindow();
                        ensureCacheFilled();
                    }
                };
                anim.run(150);
            }
        }

        /**
         * Insert rows animated.
         *
         * @param rowData the row data
         * @param firstIndex the first index
         * @param rows the rows
         * @return the list
         */
        protected List<VScrollTableRow> insertRowsAnimated(UIDL rowData,
                int firstIndex, int rows) {
            List<VScrollTableRow> insertedRows = insertAndReindexRows(rowData,
                    firstIndex, rows);
            if (!insertedRows.isEmpty()) {
                // Only animate if there's something to animate (#8810)
                RowExpandAnimation anim = new RowExpandAnimation(insertedRows);
                anim.run(150);
            }
            scrollBody.calculateMaxIndent();
            return insertedRows;
        }

        /**
         * Prepares the table for animation by copying the background colors of
         * all TR elements to their respective TD elements if the TD element is
         * transparent. This is needed, since if TDs have transparent
         * backgrounds, the rows sliding behind them are visible.
         */
        private class AnimationPreparator {
            
            /** The last item ix. */
            private final int lastItemIx;

            /**
             * Instantiates a new animation preparator.
             *
             * @param lastItemIx the last item ix
             */
            public AnimationPreparator(int lastItemIx) {
                this.lastItemIx = lastItemIx;
            }

            /**
             * Prepare table for animation.
             */
            public void prepareTableForAnimation() {
                int ix = lastItemIx;
                VScrollTableRow row = null;
                while ((row = getRowByRowIndex(ix)) != null) {
                    copyTRBackgroundsToTDs(row);
                    --ix;
                }
            }

            /**
             * Copy tr backgrounds to t ds.
             *
             * @param row the row
             */
            private void copyTRBackgroundsToTDs(VScrollTableRow row) {
                Element tr = row.getElement();
                ComputedStyle cs = new ComputedStyle(tr);
                String backgroundAttachment = cs
                        .getProperty("backgroundAttachment");
                String backgroundClip = cs.getProperty("backgroundClip");
                String backgroundColor = cs.getProperty("backgroundColor");
                String backgroundImage = cs.getProperty("backgroundImage");
                String backgroundOrigin = cs.getProperty("backgroundOrigin");
                for (int ix = 0; ix < tr.getChildCount(); ix++) {
                    Element td = tr.getChild(ix).cast();
                    if (!elementHasBackground(td)) {
                        td.getStyle().setProperty("backgroundAttachment",
                                backgroundAttachment);
                        td.getStyle().setProperty("backgroundClip",
                                backgroundClip);
                        td.getStyle().setProperty("backgroundColor",
                                backgroundColor);
                        td.getStyle().setProperty("backgroundImage",
                                backgroundImage);
                        td.getStyle().setProperty("backgroundOrigin",
                                backgroundOrigin);
                    }
                }
            }

            /**
             * Element has background.
             *
             * @param element the element
             * @return true, if successful
             */
            private boolean elementHasBackground(Element element) {
                ComputedStyle cs = new ComputedStyle(element);
                String clr = cs.getProperty("backgroundColor");
                String img = cs.getProperty("backgroundImage");
                return !("rgba(0, 0, 0, 0)".equals(clr.trim())
                        || "transparent".equals(clr.trim()) || img == null);
            }

            /**
             * Restore table after animation.
             */
            public void restoreTableAfterAnimation() {
                int ix = lastItemIx;
                VScrollTableRow row = null;
                while ((row = getRowByRowIndex(ix)) != null) {
                    restoreStyleForTDsInRow(row);

                    --ix;
                }
            }

            /**
             * Restore style for t ds in row.
             *
             * @param row the row
             */
            private void restoreStyleForTDsInRow(VScrollTableRow row) {
                Element tr = row.getElement();
                for (int ix = 0; ix < tr.getChildCount(); ix++) {
                    Element td = tr.getChild(ix).cast();
                    td.getStyle().clearProperty("backgroundAttachment");
                    td.getStyle().clearProperty("backgroundClip");
                    td.getStyle().clearProperty("backgroundColor");
                    td.getStyle().clearProperty("backgroundImage");
                    td.getStyle().clearProperty("backgroundOrigin");
                }
            }
        }

        /**
         * Animates row expansion using the GWT animation framework.
         * 
         * The idea is as follows:
         * 
         * 1. Insert all rows normally
         * 
         * 2. Insert a newly created DIV containing a new TABLE element below
         * the DIV containing the actual scroll table body.
         * 
         * 3. Clone the rows that were inserted in step 1 and attach the clones
         * to the new TABLE element created in step 2.
         * 
         * 4. The new DIV from step 2 is absolutely positioned so that the last
         * inserted row is just behind the row that was expanded.
         * 
         * 5. Hide the contents of the originally inserted rows by setting the
         * DIV.v-table-cell-wrapper to display:none;.
         * 
         * 6. Set the height of the originally inserted rows to 0.
         * 
         * 7. The animation loop slides the DIV from step 2 downwards, while at
         * the same pace growing the height of each of the inserted rows from 0
         * to full height. The first inserted row grows from 0 to full and after
         * this the second row grows from 0 to full, etc until all rows are full
         * height.
         * 
         * 8. Remove the DIV from step 2
         * 
         * 9. Restore display:block; to the DIV.v-table-cell-wrapper elements.
         * 
         * 10. DONE
         */
        private class RowExpandAnimation extends Animation {

            /** The rows. */
            private final List<VScrollTableRow> rows;
            
            /** The clone div. */
            private Element cloneDiv;
            
            /** The clone table. */
            private Element cloneTable;
            
            /** The preparator. */
            private AnimationPreparator preparator;

            /**
             * Instantiates a new row expand animation.
             *
             * @param rows            List of rows to animate. Must not be empty.
             */
            public RowExpandAnimation(List<VScrollTableRow> rows) {
                this.rows = rows;
                buildAndInsertAnimatingDiv();
                preparator = new AnimationPreparator(rows.get(0).getIndex() - 1);
                preparator.prepareTableForAnimation();
                for (VScrollTableRow row : rows) {
                    cloneAndAppendRow(row);
                    row.addStyleName("v-table-row-animating");
                    setCellWrapperDivsToDisplayNone(row);
                    row.setHeight(getInitialHeight());
                }
            }

            /**
             * Gets the initial height.
             *
             * @return the initial height
             */
            protected String getInitialHeight() {
                return "0px";
            }

            /**
             * Clone and append row.
             *
             * @param row the row
             */
            private void cloneAndAppendRow(VScrollTableRow row) {
                Element clonedTR = null;
                clonedTR = row.getElement().cloneNode(true).cast();
                clonedTR.getStyle().setVisibility(Visibility.VISIBLE);
                cloneTable.appendChild(clonedTR);
            }

            /**
             * Gets the base offset.
             *
             * @return the base offset
             */
            protected double getBaseOffset() {
                return rows.get(0).getAbsoluteTop()
                        - rows.get(0).getParent().getAbsoluteTop()
                        - rows.size() * getRowHeight();
            }

            /**
             * Builds the and insert animating div.
             */
            private void buildAndInsertAnimatingDiv() {
                cloneDiv = DOM.createDiv();
                cloneDiv.addClassName("v-treetable-animation-clone-wrapper");
                cloneTable = DOM.createTable();
                cloneTable.addClassName("v-treetable-animation-clone");
                cloneDiv.appendChild(cloneTable);
                insertAnimatingDiv();
            }

            /**
             * Insert animating div.
             */
            private void insertAnimatingDiv() {
                Element tableBody = getElement().cast();
                Element tableBodyParent = tableBody.getParentElement().cast();
                tableBodyParent.insertAfter(cloneDiv, tableBody);
            }

          /**
           * On update.
           *
           * @param progress the progress
           */
          @Override
            protected void onUpdate(double progress) {
                animateDiv(progress);
                animateRowHeights(progress);
            }

            /**
             * Animate div.
             *
             * @param progress the progress
             */
            private void animateDiv(double progress) {
                double offset = calculateDivOffset(progress, getRowHeight());

                cloneDiv.getStyle().setTop(getBaseOffset() + offset, Unit.PX);
            }

            /**
             * Animate row heights.
             *
             * @param progress the progress
             */
            private void animateRowHeights(double progress) {
                double rh = getRowHeight();
                double vlh = calculateHeightOfAllVisibleLines(progress, rh);
                int ix = 0;

                while (ix < rows.size()) {
                    double height = vlh < rh ? vlh : rh;
                    rows.get(ix).setHeight(height + "px");
                    vlh -= height;
                    ix++;
                }
            }

            /**
             * Calculate height of all visible lines.
             *
             * @param progress the progress
             * @param rh the rh
             * @return the double
             */
            protected double calculateHeightOfAllVisibleLines(double progress,
                    double rh) {
                return rows.size() * rh * progress;
            }

            /**
             * Calculate div offset.
             *
             * @param progress the progress
             * @param rh the rh
             * @return the double
             */
            protected double calculateDivOffset(double progress, double rh) {
                return progress * rows.size() * rh;
            }

            /**
             * On complete.
             */
            @Override
            protected void onComplete() {
                preparator.restoreTableAfterAnimation();
                for (VScrollTableRow row : rows) {
                    resetCellWrapperDivsDisplayProperty(row);
                    row.removeStyleName("v-table-row-animating");
                }
                Element tableBodyParent = (Element) getElement()
                        .getParentElement();
                tableBodyParent.removeChild(cloneDiv);
            }

            /**
             * Sets the cell wrapper divs to display none.
             *
             * @param row the new cell wrapper divs to display none
             */
            private void setCellWrapperDivsToDisplayNone(VScrollTableRow row) {
                Element tr = row.getElement();
                for (int ix = 0; ix < tr.getChildCount(); ix++) {
                    getWrapperDiv(tr, ix).getStyle().setDisplay(Display.NONE);
                }
            }

            /**
             * Gets the wrapper div.
             *
             * @param tr the tr
             * @param tdIx the td ix
             * @return the wrapper div
             */
            private Element getWrapperDiv(Element tr, int tdIx) {
                Element td = tr.getChild(tdIx).cast();
                return td.getChild(0).cast();
            }

            /**
             * Reset cell wrapper divs display property.
             *
             * @param row the row
             */
            private void resetCellWrapperDivsDisplayProperty(VScrollTableRow row) {
                Element tr = row.getElement();
                for (int ix = 0; ix < tr.getChildCount(); ix++) {
                    getWrapperDiv(tr, ix).getStyle().clearProperty("display");
                }
            }

        }

        /**
         * This is the inverse of the RowExpandAnimation and is implemented by
         * extending it and overriding the calculation of offsets and heights.
         */
        private class RowCollapseAnimation extends RowExpandAnimation {

            /** The rows. */
            private final List<VScrollTableRow> rows;

            /**
             * Instantiates a new row collapse animation.
             *
             * @param rows            List of rows to animate. Must not be empty.
             */
            public RowCollapseAnimation(List<VScrollTableRow> rows) {
                super(rows);
                this.rows = rows;
            }

            /**
             * Gets the initial height.
             *
             * @return the initial height
             */
            @Override
            protected String getInitialHeight() {
                return getRowHeight() + "px";
            }

            /**
             * Gets the base offset.
             *
             * @return the base offset
             */
            @Override
            protected double getBaseOffset() {
                return getRowHeight();
            }

            /**
             * Calculate height of all visible lines.
             *
             * @param progress the progress
             * @param rh the rh
             * @return the double
             */
            @Override
            protected double calculateHeightOfAllVisibleLines(double progress,
                    double rh) {
                return rows.size() * rh * (1 - progress);
            }

            /**
             * Calculate div offset.
             *
             * @param progress the progress
             * @param rh the rh
             * @return the double
             */
            @Override
            protected double calculateDivOffset(double progress, double rh) {
                return -super.calculateDivOffset(progress, rh);
            }
        }
    }

    /**
     * Icons rendered into first actual column in TreeTable, not to row header
     * cell.
     *
     * @param uidl the uidl
     * @return the string
     */
    @Override
    protected String buildCaptionHtmlSnippet(UIDL uidl) {
        if (uidl.getTag().equals("column")) {
            return super.buildCaptionHtmlSnippet(uidl);
        } else {
            String s = uidl.getStringAttribute("caption");
            return s;
        }
    }

    /**
     *  For internal use only. May be removed or replaced in the future.
     *
     * @param keycode the keycode
     * @param ctrl the ctrl
     * @param shift the shift
     * @return true, if successful
     */
  @Override
    public boolean handleNavigation(int keycode, boolean ctrl, boolean shift) {
        if (collapseRequest || focusParentResponsePending) {
            // Enqueue the event if there might be pending content changes from
            // the server
            if (pendingNavigationEvents.size() < 10) {
                // Only keep 10 keyboard events in the queue
                PendingNavigationEvent pendingNavigationEvent = new PendingNavigationEvent(
                        keycode, ctrl, shift);
                pendingNavigationEvents.add(pendingNavigationEvent);
            }
            return true;
        }

        VExtFilterTreeTable.VTreeTableScrollBody.VTreeTableRow focusedRow = (VExtFilterTreeTable.VTreeTableScrollBody.VTreeTableRow) getFocusedRow();
        if (focusedRow != null) {
            if (focusedRow.canHaveChildren
                    && ((keycode == KeyCodes.KEY_RIGHT && !focusedRow.open) || (keycode == KeyCodes.KEY_LEFT && focusedRow.open))) {
                if (!ctrl) {
                    client.updateVariable(paintableId, "selectCollapsed", true,
                            false);
                }
                sendSelectedRows(false);
                sendToggleCollapsedUpdate(focusedRow.getKey());
                return true;
            } else if (keycode == KeyCodes.KEY_RIGHT && focusedRow.open) {
                // already expanded, move selection down if next is on a deeper
                // level (is-a-child)
                VTreeTableScrollBody body = (VTreeTableScrollBody) focusedRow
                        .getParent();
                Iterator<Widget> iterator = body.iterator();
                VExtFilterTreeTable.VTreeTableScrollBody.VTreeTableRow next = null;
                while (iterator.hasNext()) {
                    next = (VExtFilterTreeTable.VTreeTableScrollBody.VTreeTableRow) iterator
                            .next();
                    if (next == focusedRow) {
                        next = (VExtFilterTreeTable.VTreeTableScrollBody.VTreeTableRow) iterator
                                .next();
                        break;
                    }
                }
                if (next != null) {
                    if (next.depth > focusedRow.depth) {
                        selectionPending = true;
                        return super.handleNavigation(getNavigationDownKey(),
                                ctrl, shift);
                    }
                } else {
                    // Note, a minor change here for a bit false behavior if
                    // cache rows is disabled + last visible row + no childs for
                    // the node
                    selectionPending = true;
                    return super.handleNavigation(getNavigationDownKey(), ctrl,
                            shift);
                }
            } else if (keycode == KeyCodes.KEY_LEFT) {
                // already collapsed move selection up to parent node
                // do on the server side as the parent is not necessary
                // rendered on the client, could check if parent is visible if
                // a performance issue arises

                client.updateVariable(paintableId, "focusParent",
                        focusedRow.getKey(), true);

                // Set flag that we should enqueue navigation events until we
                // get a response to this request
                focusParentResponsePending = true;

                return true;
            }
        }
        return super.handleNavigation(keycode, ctrl, shift);
    }

    /**
     * Send toggle collapsed update.
     *
     * @param rowKey the row key
     */
    private void sendToggleCollapsedUpdate(String rowKey) {
        collapsedRowKey = rowKey;
        collapseRequest = true;
//        client.updateVariable(paintableId, "toggleCollapsedWait",true,false);
        client.updateVariable(paintableId, "toggleCollapsed", rowKey, true);
    }

    /**
     * On browser event.
     *
     * @param event the event
     */
    @Override
    public void onBrowserEvent(Event event) {
        super.onBrowserEvent(event);
        if (event.getTypeInt() == Event.ONKEYUP && selectionPending) {
            sendSelectedRows();
        }
    }

    /**
     * Send selected rows.
     *
     * @param immediately the immediately
     */
    @Override
    protected void sendSelectedRows(boolean immediately) {
        super.sendSelectedRows(immediately);
        selectionPending = false;
    }

    /**
     * Re order column.
     *
     * @param columnKey the column key
     * @param newIndex the new index
     */
    @Override
    protected void reOrderColumn(String columnKey, int newIndex) {
        super.reOrderColumn(columnKey, newIndex);
        // current impl not intelligent enough to survive without visiting the
        // server to redraw content
        client.sendPendingVariableChanges();
    }

   /**
    * Sets the style name.
    *
    * @param style the new style name
    */
   @Override
    public void setStyleName(String style) {
        super.setStyleName(style + " v-treetable");
    }

    /**
     * Update total rows.
     *
     * @param uidl the uidl
     */
    @Override
    public void updateTotalRows(UIDL uidl) {
        // Make sure that initializedAndAttached & al are not reset when the
        // totalrows are updated on expand/collapse requests.
        int newTotalRows = uidl.getIntAttribute("totalrows");
        setTotalRows(newTotalRows);
    }

}
