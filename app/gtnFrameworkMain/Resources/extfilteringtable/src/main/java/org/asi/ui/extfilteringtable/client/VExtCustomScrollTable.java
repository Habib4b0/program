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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.dom.client.TableSectionElement;
import com.google.gwt.dom.client.Touch;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.ScrollEvent;
import com.google.gwt.event.dom.client.ScrollHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ApplicationConnection;
import com.vaadin.client.BrowserInfo;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorMap;
import com.vaadin.client.DeferredWorker;
import com.vaadin.client.Focusable;
import com.vaadin.client.MouseEventDetailsBuilder;
import com.vaadin.client.StyleConstants;
import com.vaadin.client.TooltipInfo;
import com.vaadin.client.UIDL;
import com.vaadin.client.Util;
import com.vaadin.client.VConsole;
import com.vaadin.client.VTooltip;
import com.vaadin.client.ui.Action;
import com.vaadin.client.ui.ActionOwner;
import com.vaadin.client.ui.FocusableScrollPanel;
import com.vaadin.client.ui.SubPartAware;
import com.vaadin.client.ui.TouchScrollDelegate;
import com.vaadin.client.ui.TreeAction;
import com.vaadin.client.ui.VContextMenu;
import com.vaadin.client.ui.VEmbedded;
import com.vaadin.client.ui.VLabel;
import com.vaadin.client.ui.VOverlay;
import com.vaadin.client.ui.VTextField;
import com.vaadin.client.ui.dd.DDUtil;
import com.vaadin.client.ui.dd.VAbstractDropHandler;
import com.vaadin.client.ui.dd.VAcceptCallback;
import com.vaadin.client.ui.dd.VDragAndDropManager;
import com.vaadin.client.ui.dd.VDragEvent;
import com.vaadin.client.ui.dd.VHasDropHandler;
import com.vaadin.client.ui.dd.VTransferable;
import com.vaadin.shared.AbstractComponentState;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.ui.dd.VerticalDropLocation;
import com.vaadin.shared.ui.table.TableConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.DOUBLE_HEADER;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.SINGLE_HEADER;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.TRIPLE_HEADER;
import org.asi.ui.extfilteringtable.client.VExtCustomScrollTable.VScrollTableBody.VScrollTableRow;

/**
 *
 * VExtCustomScrollTable
 *
 * VExtCustomScrollTable is a FlowPanel having three widgets in it: * TableHead
 * component * * DoubleTableHead component * ScrollPanel
 *
 * TableHead contains table's header and widgets + logic for resizing,
 * reordering and hiding columns.
 *
 * DoubleTableHead contains table's doubleheader and widgets + logic for
 * resizing and hiding columns.
 *
 * ScrollPanel contains VScrollTableBody object which handles content. To save
 * some bandwidth and to improve clients responsiveness with loads of data, in
 * VScrollTableBody all rows are not necessary rendered. There are "spacers" in
 * VScrollTableBody to use the exact same space as non-rendered rows would use.
 * This way we can use seamlessly traditional scrollbars and scrolling to fetch
 * more rows instead of "paging".
 *
 * In VExtCustomScrollTable we listen to scroll events. On horizontal scrolling
 * we also update TableHeads and DoubleTableHeads scroll positions which have
 * its scrollbars hidden. On vertical scroll events we will check if we are
 * reaching the end of area where we have rows rendered and
 *
 * TODO implement unregistering for child components in Cells
 *
 * @author Abhiram.
 */
@SuppressWarnings("deprecation")
public class VExtCustomScrollTable extends FlowPanel implements HasWidgets,
        ScrollHandler, VHasDropHandler, FocusHandler, BlurHandler, Focusable,
        ActionOwner, SubPartAware, DeferredWorker {
    /**
     * Simple interface for parts of the table capable of owning a context menu.
     *
     * @author Vaadin Ltd
     * @since 7.2
     */
    private interface ContextMenuOwner {

        /**
         * Show context menu.
         *
         * @param event the event
         */
        public void showContextMenu(Event event);
    }

    /**
     * Handles showing context menu on "long press" from a touch screen.
     *
     * @author Vaadin Ltd
     * @since 7.2
     */
    private class TouchContextProvider {

        /**
         * The Constant TOUCH_CONTEXT_MENU_TIMEOUT.
         */
        private static final int TOUCH_CONTEXT_MENU_TIMEOUT = 500;
        /**
         * The context touch timeout.
         */
        private Timer contextTouchTimeout;
        /**
         * The touch start.
         */
        private Event touchStart;
        /**
         * The touch start y.
         */
        private int touchStartY;
        /**
         * The touch start x.
         */
        private int touchStartX;
        /**
         * The target.
         */
        private ContextMenuOwner target;

        /**
         * Initializes a handler for a certain context menu owner.
         *
         * @param target the owner of the context menu
         */
        public TouchContextProvider(ContextMenuOwner target) {
            this.target = target;
        }

        /**
         * Cancels the current context touch timeout.
         */
        public void cancel() {
            if (contextTouchTimeout != null) {
                contextTouchTimeout.cancel();
                contextTouchTimeout = null;
            }
            touchStart = null;
        }

        /**
         * A function to handle touch context events in a table.
         *
         * @param event browser event to handle
         */
        public void handleTouchEvent(final Event event) {
            int type = event.getTypeInt();

            switch (type) {
                case Event.ONCONTEXTMENU:
                    target.showContextMenu(event);
                    break;
                case Event.ONTOUCHSTART:
                    // save position to fields, touches in events are same
                    // instance during the operation.
                    touchStart = event;

                    Touch touch = event.getChangedTouches().get(0);
                    touchStartX = touch.getClientX();
                    touchStartY = touch.getClientY();

                    if (contextTouchTimeout == null) {
                        contextTouchTimeout = new Timer() {
                            @Override
                            public void run() {
                                if (touchStart != null) {
                                    // Open the context menu if finger
                                    // is held in place long enough.
                                    target.showContextMenu(touchStart);
                                    event.preventDefault();
                                    touchStart = null;
                                }
                            }
                        };
                    }
                    contextTouchTimeout.schedule(TOUCH_CONTEXT_MENU_TIMEOUT);
                    break;
                case Event.ONTOUCHCANCEL:
                case Event.ONTOUCHEND:
                    cancel();
                    break;
                case Event.ONTOUCHMOVE:
                    if (isSignificantMove(event)) {
                        // Moved finger before the context menu timer
                        // expired, so let the browser handle the event.
                        cancel();
                    }
            }
        }

        /**
         * Calculates how many pixels away the user's finger has traveled. This
         * reduces the chance of small non-intentional movements from canceling
         * the long press detection.
         *
         * @param event the Event for which to check the move distance
         * @return true if this is considered an intentional move by the user
         */
        protected boolean isSignificantMove(Event event) {
            if (touchStart == null) {
                // no touch start
                return false;
            }

            // Calculate the distance between touch start and the current touch
            // position
            Touch touch = event.getChangedTouches().get(0);
            int deltaX = touch.getClientX() - touchStartX;
            int deltaY = touch.getClientY() - touchStartY;
            int delta = deltaX * deltaX + deltaY * deltaY;

            // Compare to the square of the significant move threshold to remove
            // the need for a square root
            if (delta > TouchScrollDelegate.SIGNIFICANT_MOVE_THRESHOLD
                    * TouchScrollDelegate.SIGNIFICANT_MOVE_THRESHOLD) {
                return true;
            }
            return false;
        }
    }
    /**
     * The Constant STYLENAME.
     */
    public static final String STYLENAME = "v-table";

    /**
     * The Enum SelectMode.
     */
    public enum SelectMode {

        /**
         * The none.
         */
        NONE(0), /**
         * The single.
         */
        SINGLE(1), /**
         * The multi.
         */
        MULTI(2);
        /**
         * The id.
         */
        private int id;

        /**
         * Instantiates a new select mode.
         *
         * @param id the id
         */
        private SelectMode(int id) {
            this.id = id;
        }

        /**
         * Gets the id.
         *
         * @return the id
         */
        public int getId() {
            return id;
        }
    }
    /**
     * The Constant ROW_HEADER_COLUMN_KEY.
     */
    private static final String ROW_HEADER_COLUMN_KEY = "0";
    /**
     * The Constant CACHE_RATE_DEFAULT.
     */
    private static final double CACHE_RATE_DEFAULT = 2;
    /**
     * The default multi select mode where simple left clicks only selects one
     * item, CTRL+left click selects multiple items and SHIFT-left click selects
     * a range of items.
     */
    private static final int MULTISELECT_MODE_DEFAULT = 0;
    /**
     * The simple multiselect mode is what the table used to have before
     * ctrl/shift selections were added. That is that when this is set clicking
     * on an item selects/deselects the item and no ctrl/shift selections are
     * available.
     */
    private static final int MULTISELECT_MODE_SIMPLE = 1;
    /**
     * multiple of pagelength which component will cache when requesting more
     * rows.
     */
    private double cache_rate = CACHE_RATE_DEFAULT;
    /**
     * fraction of pageLenght which can be scrolled without making new request.
     */
    private double cache_react_rate = 0.75 * cache_rate;
    /**
     * The Constant ALIGN_CENTER.
     */
    public static final char ALIGN_CENTER = 'c';
    /**
     * The Constant ALIGN_LEFT.
     */
    public static final char ALIGN_LEFT = 'b';
    /**
     * The Constant ALIGN_RIGHT.
     */
    public static final char ALIGN_RIGHT = 'e';
    /**
     * The Constant CHARCODE_SPACE.
     */
    private static final int CHARCODE_SPACE = 32;
    /**
     * The first row in view port.
     */
    private int firstRowInViewPort = 0;
    /**
     * The page length.
     */
    private int pageLength = 15;
    /**
     * The last requested firstvisible.
     */
    private int lastRequestedFirstvisible = 0; // to detect "serverside scroll"
    /**
     * The firstvisible on last page.
     */
    private int firstvisibleOnLastPage = -1; // To detect if the first visible
    // is on the last page
    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public boolean showRowHeaders = false;
    /**
     * The column order.
     */
    private String[] columnOrder;
    /**
     * The doublecolumn order.
     */
    private String[] doublecolumnOrder;
    /**
     * The map visible columns.
     */
    Map<String, String[]> mapVisibleColumns = new HashMap<String, String[]>();
    /**
     * The client.
     */
    protected ApplicationConnection client;
    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public String paintableId;
    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public boolean immediate;
    /**
     * The updated req rows.
     */
    private boolean updatedReqRows = true;
    /**
     * The null selection allowed.
     */
    private boolean nullSelectionAllowed = true;
    /**
     * The select mode.
     */
    private SelectMode selectMode = SelectMode.NONE;
    /**
     * The selected row keys.
     */
    public final HashSet<String> selectedRowKeys = new HashSet<String>();

    /*
     * When scrolling and selecting at the same time, the selections are not in
     * sync with the server while retrieving new rows (until key is released).
     */
    /**
     * The un syncedselections before row fetch.
     */
    private HashSet<Object> unSyncedselectionsBeforeRowFetch;

    /*
     * These are used when jumping between pages when pressing Home and End
     */
    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public boolean selectLastItemInNextRender = false;
    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public boolean selectFirstItemInNextRender = false;
    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public boolean focusFirstItemInNextRender = false;
    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public boolean focusLastItemInNextRender = false;
    /**
     * The map single radio.
     */
    Map<String, String[]> mapSingleRadio = new HashMap<String, String[]>();
    /**
     * The single radio val.
     */
    Map<String, String> singleRadioVal = new HashMap<String, String>();
    /**
     * The map double radio.
     */
    Map<String, String[]> mapDoubleRadio = new HashMap<String, String[]>();
    /**
     * The double radio val.
     */
    Map<String, String> doubleRadioVal = new HashMap<String, String>();
    /**
     * The radio button sinks.
     */
    public boolean radioButtonSinks = false;
    /**
     * The currently focused row.
     * <p>
     * For internal use only. May be removed or replaced in the future.
     */
    public VScrollTableRow focusedRow;
    /**
     * Helper to store selection range start in when using the keyboard
     * <p>
     * For internal use only. May be removed or replaced in the future.
     */
    public VScrollTableRow selectionRangeStart;
    /**
     * Flag for notifying when the selection has changed and should be sent to
     * the server
     * <p>
     * For internal use only. May be removed or replaced in the future.
     */
    public boolean selectionChanged = false;

    /*
     * The speed (in pixels) which the scrolling scrolls vertically/horizontally
     */
    /**
     * The scrolling velocity.
     */
    private int scrollingVelocity = 10;
    /**
     * The scrolling velocity timer.
     */
    private Timer scrollingVelocityTimer = null;
    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public String[] bodyActionKeys;
    /**
     * The enable debug.
     */
    private boolean enableDebug = false;
    /**
     * The Constant hasNativeTouchScrolling.
     */
    private static final boolean hasNativeTouchScrolling = BrowserInfo.get()
            .isTouchDevice()
            && !BrowserInfo.get().requiresTouchScrollDelegate();
    /**
     * The noncollapsible columns.
     */
    private Set<String> noncollapsibleColumns;
    /**
     * The doublenoncollapsible columns.
     */
    private Set<String> doublenoncollapsibleColumns;
    /**
     * The last known row height used to preserve the height of a table with
     * custom row heights and a fixed page length after removing the last row
     * from the table.
     *
     * A new VScrollTableBody instance is created every time the number of rows
     * changes causing {@link VScrollTableBody#rowHeight} to be discarded and
     * the height recalculated by {@link VScrollTableBody#getRowHeight(boolean)}
     * to avoid some rounding problems, e.g. round(2 * 19.8) / 2 = 20 but
     * round(3 * 19.8) / 3 = 19.66.
     */
    private double lastKnownRowHeight = Double.NaN;
    /**
     * Remember scroll position when getting detached to properly scroll back to
     * the location that there is data for if getting attached again.
     */
    private int detachedScrollPosition = 0;

    /**
     * Represents a select range of rows.
     */
    private class SelectionRange {

        /**
         * The start row.
         */
        private VScrollTableRow startRow;
        /**
         * The length.
         */
        private final int length;

        /**
         * Constuctor.
         *
         * @param row1 the row1
         * @param row2 the row2
         */
        public SelectionRange(VScrollTableRow row1, VScrollTableRow row2) {
            VScrollTableRow endRow;
            if (row2.isBefore(row1)) {
                startRow = row2;
                endRow = row1;
            } else {
                startRow = row1;
                endRow = row2;
            }
            length = endRow.getIndex() - startRow.getIndex() + 1;
        }

        /**
         * Instantiates a new selection range.
         *
         * @param row the row
         * @param length the length
         */
        public SelectionRange(VScrollTableRow row, int length) {
            startRow = row;
            this.length = length;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        /**
         * To string.
         *
         * @return the string
         */
        @Override
        public String toString() {
            return startRow.getKey() + "-" + length;
        }

        /**
         * In range.
         *
         * @param row the row
         * @return true, if successful
         */
        private boolean inRange(VScrollTableRow row) {
            return row.getIndex() >= startRow.getIndex()
                    && row.getIndex() < startRow.getIndex() + length;
        }

        /**
         * Split.
         *
         * @param row the row
         * @return the collection
         */
        public Collection<SelectionRange> split(VScrollTableRow row) {
            assert row.isAttached();
            ArrayList<SelectionRange> ranges = new ArrayList<SelectionRange>(2);

            int endOfFirstRange = row.getIndex() - 1;
            if (endOfFirstRange >= startRow.getIndex()) {
                // create range of first part unless its length is < 1
                ranges.add(new SelectionRange(startRow, endOfFirstRange
                        - startRow.getIndex() + 1));
            }
            int startOfSecondRange = row.getIndex() + 1;
            if (getEndIndex() >= startOfSecondRange) {
                // create range of second part unless its length is < 1
                VScrollTableRow startOfRange = scrollBody
                        .getRowByRowIndex(startOfSecondRange);
                if (startOfRange != null) {
                    ranges.add(new SelectionRange(startOfRange, getEndIndex()
                            - startOfSecondRange + 1));
                }
            }
            return ranges;
        }

        /**
         * Gets the end index.
         *
         * @return the end index
         */
        private int getEndIndex() {
            return startRow.getIndex() + length - 1;
        }
    }
    /**
     * The selected row ranges.
     */
    private final HashSet<SelectionRange> selectedRowRanges = new HashSet<SelectionRange>();
    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public boolean initializedAndAttached = false;
    /**
     * Flag to indicate if a column width recalculation is needed due update.
     * <p>
     * For internal use only. May be removed or replaced in the future.
     */
    public boolean headerChangedDuringUpdate = false;
    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public final DoubleTableHead tdHead = new DoubleTableHead();
    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public final TableHead tHead = new TableHead();
    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public final TableFooter tFoot = new TableFooter();
    /**
     * Handles context menu for table body.
     */
    private ContextMenuOwner contextMenuOwner = new ContextMenuOwner() {
        @Override
        public void showContextMenu(Event event) {
            int left = Util.getTouchOrMouseClientX(event);
            int top = Util.getTouchOrMouseClientY(event);
            boolean menuShown = handleBodyContextMenu(left, top);
            if (menuShown) {
                event.stopPropagation();
                event.preventDefault();
            }
        }
    };
    /**
     * Handles touch events to display a context menu for table body.
     */
    private TouchContextProvider touchContextProvider = new TouchContextProvider(
            contextMenuOwner);

    /**
     * For internal use only. May be removed or replaced in the future.
     *
     * Overwrites onBrowserEvent function on FocusableScrollPanel to give event
     * access to touchContextProvider. Has to be public to give TableConnector
     * access to the scrollBodyPanel field.
     *
     * @author Vaadin Ltd
     * @since 7.2
     */
    public class FocusableScrollContextPanel extends FocusableScrollPanel {

        /**
         * On browser event.
         *
         * @param event the event
         */
        @Override
        public void onBrowserEvent(Event event) {
            super.onBrowserEvent(event);
            touchContextProvider.handleTouchEvent(event);
        }

        ;

        /**
         * Instantiates a new focusable scroll context panel.
         *
         * @param useFakeFocusElement the use fake focus element
         */
        public FocusableScrollContextPanel(boolean useFakeFocusElement) {
            super(useFakeFocusElement);
        }
    }
    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public final FocusableScrollContextPanel scrollBodyPanel = new FocusableScrollContextPanel(
            true);
    /**
     * The nav key press handler.
     */
    private KeyPressHandler navKeyPressHandler = new KeyPressHandler() {
        @Override
        public void onKeyPress(KeyPressEvent keyPressEvent) {
            // This is used for Firefox only, since Firefox auto-repeat
            // works correctly only if we use a key press handler, other
            // browsers handle it correctly when using a key down handler
            if (!BrowserInfo.get().isGecko()) {
                return;
            }

            NativeEvent event = keyPressEvent.getNativeEvent();
            if (!enabled) {
                // Cancel default keyboard events on a disabled Table
                // (prevents scrolling)
                event.preventDefault();
            } else if (hasFocus) {
                // Key code in Firefox/onKeyPress is present only for
                // special keys, otherwise 0 is returned
                int keyCode = event.getKeyCode();
                if (keyCode == 0 && event.getCharCode() == ' ') {
                    // Provide a keyCode for space to be compatible with
                    // FireFox keypress event
                    keyCode = CHARCODE_SPACE;
                }

                if (handleNavigation(keyCode,
                        event.getCtrlKey() || event.getMetaKey(),
                        event.getShiftKey())) {
                    event.preventDefault();
                }

                startScrollingVelocityTimer();
            }
        }
    };
    /**
     * The nav key up handler.
     */
    private KeyUpHandler navKeyUpHandler = new KeyUpHandler() {
        @Override
        public void onKeyUp(KeyUpEvent keyUpEvent) {
            NativeEvent event = keyUpEvent.getNativeEvent();
            int keyCode = event.getKeyCode();

            if (!isFocusable()) {
                cancelScrollingVelocityTimer();
            } else if (isNavigationKey(keyCode)) {
                if (keyCode == getNavigationDownKey()
                        || keyCode == getNavigationUpKey()) {
                    /*
                     * in multiselect mode the server may still have value from
                     * previous page. Clear it unless doing multiselection or
                     * just moving focus.
                     */
                    if (!event.getShiftKey() && !event.getCtrlKey()) {
                        instructServerToForgetPreviousSelections();
                    }
                    sendSelectedRows();
                }
                cancelScrollingVelocityTimer();
                navKeyDown = false;
            }
        }
    };
    /**
     * The nav key down handler.
     */
    private KeyDownHandler navKeyDownHandler = new KeyDownHandler() {
        @Override
        public void onKeyDown(KeyDownEvent keyDownEvent) {
            NativeEvent event = keyDownEvent.getNativeEvent();
            // This is not used for Firefox
            if (BrowserInfo.get().isGecko()) {
                return;
            }

            if (!enabled) {
                // Cancel default keyboard events on a disabled Table
                // (prevents scrolling)
                event.preventDefault();
            } else if (hasFocus) {
                if (handleNavigation(event.getKeyCode(), event.getCtrlKey()
                        || event.getMetaKey(), event.getShiftKey())) {
                    navKeyDown = true;
                    event.preventDefault();
                }

                startScrollingVelocityTimer();
            }
        }
    };
    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public int totalRows;
    /**
     * The collapsed columnsallowed.
     */
    private boolean collapsedColumnsallowed;
    /**
     * The collapsed columns.
     */
    private Set<String> collapsedColumns;
    /**
     * The doublecollapsed columnsallowed.
     */
    private boolean doublecollapsedColumnsallowed;
    /**
     * The doublecollapsed columns.
     */
    private Set<String> doublecollapsedColumns = new HashSet<String>();
    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public final RowRequestHandler rowRequestHandler;
    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public VScrollTableBody scrollBody;
    /**
     * The firstvisible.
     */
    private int firstvisible = 0;
    /**
     * The sort ascending.
     */
    private boolean sortAscending;
    /**
     * The sort column.
     */
    private String sortColumn;
    /**
     * The old sort column.
     */
    private String oldSortColumn;
    /**
     * The column reordering.
     */
    private boolean columnReordering;
    /**
     * This map contains captions and icon urls for actions like: * "33_c" ->
     * "Edit" * "33_i" -> "http://dom.com/edit.png"
     */
    private final HashMap<Object, String> actionMap = new HashMap<Object, String>();
    /**
     * The visible col order.
     */
    protected String[] visibleColOrder;
    /**
     * The initial content received.
     */
    private boolean initialContentReceived = false;
    /**
     * The scroll position element.
     */
    private Element scrollPositionElement;
    /**
     * The doublevisible col order.
     */
    private String[] doublevisibleColOrder;
    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public boolean enabled;
    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public boolean showDoubleColHeaders;
    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public boolean showColHeaders;
    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public boolean showColFooters;
    /**
     * flag to indicate that table body has changed.
     */
    private boolean isNewBody = true;
    /**
     * Read from the "recalcWidths" -attribute. When it is true, the table will
     * recalculate the widths for columns - desirable in some cases. For #1983,
     * marked experimental. See also variable
     * <code>refreshContentWidths</code> in method
     * {@link TableHead#updateCellsFromUIDL(UIDL)}.
     * <p>
     * For internal use only. May be removed or replaced in the future.
     */
    public boolean recalcWidths = false;
    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public boolean rendering = false;
    /**
     * The has focus.
     */
    private boolean hasFocus = false;
    /**
     * The dragmode.
     */
    private int dragmode;
    /**
     * The multiselectmode.
     */
    private int multiselectmode;
    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public int tabIndex;
    /**
     * The touch scroll delegate.
     */
    private TouchScrollDelegate touchScrollDelegate;
    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public int lastRenderedHeight;
    /**
     * Values (serverCacheFirst+serverCacheLast) sent by server that tells which
     * rows (indexes) are in the server side cache (page buffer). -1 means
     * unknown. The server side cache row MUST MATCH the client side cache rows.
     *
     * If the client side cache contains additional rows with e.g. buttons, it
     * will cause out of sync when such a button is pressed.
     *
     * If the server side cache contains additional rows with e.g. buttons,
     * scrolling in the client will cause empty buttons to be rendered
     * (cached=true request for non-existing components)
     *
     * For internal use only. May be removed or replaced in the future.
     */
    public int serverCacheFirst = -1;
    /**
     * The server cache last.
     */
    public int serverCacheLast = -1;
    /**
     * In several cases TreeTable depends on the scrollBody.lastRendered being
     * 'out of sync' while the update is being done. In those cases the sanity
     * check must be performed afterwards.
     */
    public boolean postponeSanityCheckForLastRendered;
    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public boolean sizeNeedsInit = true;
    /**
     * Added for ColumnFreezing *.
     */
    private String leftTable;
    /**
     * The right table.
     */
    private String rightTable;
    /**
     * The left depandant pane.
     */
    private VExtCustomScrollTable leftDepandantPane;
    /**
     * The right depandant pane.
     */
    private VExtCustomScrollTable rightDepandantPane;
    /**
     * The left scroll body panel.
     */
    private FocusableScrollPanel leftScrollBodyPanel;
    /**
     * The right scroll body panel.
     */
    private FocusableScrollPanel rightScrollBodyPanel;
    /**
     * The is re construct.
     */
    public boolean isReConstruct = false;

    /**
     * Used to recall the position of an open context menu if we need to close
     * and reopen it during a row update.
     * <p>
     * For internal use only. May be removed or replaced in the future.
     */
    public class ContextMenuDetails implements CloseHandler<PopupPanel> {

        /**
         * The row key.
         */
        public String rowKey;
        /**
         * The left.
         */
        public int left;
        /**
         * The top.
         */
        public int top;
        /**
         * The close registration.
         */
        HandlerRegistration closeRegistration;

        /**
         * Instantiates a new context menu details.
         *
         * @param menu the menu
         * @param rowKey the row key
         * @param left the left
         * @param top the top
         */
        public ContextMenuDetails(VContextMenu menu, String rowKey, int left,
                int top) {
            this.rowKey = rowKey;
            this.left = left;
            this.top = top;
            closeRegistration = menu.addCloseHandler(this);
        }

        /**
         * On close.
         *
         * @param event the event
         */
        @Override
        public void onClose(CloseEvent<PopupPanel> event) {
            contextMenu = null;
            closeRegistration.removeHandler();
        }
    }
    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public ContextMenuDetails contextMenu = null;
    /**
     * The had scroll bars.
     */
    private boolean hadScrollBars = false;
    /**
     * The add close handler.
     */
    private HandlerRegistration addCloseHandler;
    /**
     * Changes to manage mouseDown and mouseUp.
     */
    /**
     * The element where the last mouse down event was registered.
     */
    private Element lastMouseDownTarget;
    /**
     * Set to true by {@link #mouseUpPreviewHandler} if it gets a mouseup at the
     * same element as {@link #lastMouseDownTarget}.
     */
    private boolean mouseUpPreviewMatched = false;
    /**
     * The mouse up event preview registration.
     */
    private HandlerRegistration mouseUpEventPreviewRegistration;
    //For Triple header
    /**
     * The show triple col headers.
     */
    public boolean showTripleColHeaders = false;
    /**
     * The triple visible col order.
     */
    private String[] tripleVisibleColOrder;
    /**
     * The triple collapsed columnsallowed.
     */
    private boolean tripleCollapsedColumnsallowed;
    /**
     * The triple collapsed columns.
     */
    private Set<String> tripleCollapsedColumns = new HashSet<String>();
    /**
     * The triple column order.
     */
    private String[] tripleColumnOrder;
    /**
     * The triple non collapsible columns.
     */
    private Set<String> tripleNonCollapsibleColumns;
    /**
     * The map triple visible columns.
     */
    Map<String, String[]> mapTripleVisibleColumns = new HashMap<String, String[]>();
    /**
     * The tt head.
     */
    public final TripleTableHead ttHead = new TripleTableHead();
    /**
     * The map triple radio.
     */
    Map<String, String[]> mapTripleRadio = new HashMap<String, String[]>();
    /**
     * The triple radio val.
     */
    Map<String, String> tripleRadioVal = new HashMap<String, String>();
    /**
     * Previews events after a mousedown to detect where the following mouseup
     * hits.
     */
    private final NativePreviewHandler mouseUpPreviewHandler = new NativePreviewHandler() {
        @Override
        public void onPreviewNativeEvent(NativePreviewEvent event) {
            if (event.getTypeInt() == Event.ONMOUSEUP) {
                mouseUpEventPreviewRegistration.removeHandler();

                // Event's reported target not always correct if event
                // capture is in use
                Element elementUnderMouse = Util.getElementUnderMouse(event
                        .getNativeEvent());
                if (lastMouseDownTarget != null
                        && lastMouseDownTarget.isOrHasChild(elementUnderMouse)) {
                    mouseUpPreviewMatched = true;
                } else {
                    getLogger().log(
                            Level.FINEST,
                            "Ignoring mouseup from " + elementUnderMouse
                            + " when mousedown was on "
                            + lastMouseDownTarget);
                }
            }
        }
    };

    /**
     * Instantiates a new v ext custom scroll table.
     */
    public VExtCustomScrollTable() {
        setMultiSelectMode(MULTISELECT_MODE_DEFAULT);

        scrollBodyPanel.addFocusHandler(this);
        scrollBodyPanel.addBlurHandler(this);

        scrollBodyPanel.addScrollHandler(this);

        /*
         * Firefox auto-repeat works correctly only if we use a key press
         * handler, other browsers handle it correctly when using a key down
         * handler
         */
        if (BrowserInfo.get().isGecko()) {
            scrollBodyPanel.addKeyPressHandler(navKeyPressHandler);
        } else {
            scrollBodyPanel.addKeyDownHandler(navKeyDownHandler);
        }
        scrollBodyPanel.addKeyUpHandler(navKeyUpHandler);

        scrollBodyPanel.sinkEvents(Event.TOUCHEVENTS | Event.ONCONTEXTMENU);

        setStyleName(STYLENAME);
        add(ttHead);
        add(tdHead);
        add(tHead);
        add(scrollBodyPanel);
        add(tFoot);

        rowRequestHandler = new RowRequestHandler();
    }

    /**
     * Sets the style name.
     *
     * @param style the new style name
     */
    @Override
    public void setStyleName(String style) {
        updateStyleNames(style, false);
    }

    /**
     * Sets the style primary name.
     *
     * @param style the new style primary name
     */
    @Override
    public void setStylePrimaryName(String style) {
        updateStyleNames(style, true);
    }

    /**
     * Update style names.
     *
     * @param newStyle the new style
     * @param isPrimary the is primary
     */
    private void updateStyleNames(String newStyle, boolean isPrimary) {
        scrollBodyPanel
                .removeStyleName(getStylePrimaryName() + "-body-wrapper");
        scrollBodyPanel.removeStyleName(getStylePrimaryName() + "-body");

        if (scrollBody != null) {
            scrollBody.removeStyleName(getStylePrimaryName()
                    + "-body-noselection");
        }

        if (isPrimary) {
            super.setStylePrimaryName(newStyle);
        } else {
            super.setStyleName(newStyle);
        }

        scrollBodyPanel.addStyleName(getStylePrimaryName() + "-body-wrapper");
        scrollBodyPanel.addStyleName(getStylePrimaryName() + "-body");

        tHead.updateStyleNames(getStylePrimaryName());
        tFoot.updateStyleNames(getStylePrimaryName());
        tdHead.updateStyleNames(getStylePrimaryName());
        ttHead.updateStyleNames(getStylePrimaryName());

        if (scrollBody != null) {
            scrollBody.updateStyleNames(getStylePrimaryName());
        }
    }

    /**
     * Inits the.
     *
     * @param client the client
     */
    public void init(ApplicationConnection client) {
        this.client = client;
        // Add a handler to clear saved context menu details when the menu
        // closes. See #8526.
        addCloseHandler = client.getContextMenu().addCloseHandler(
                new CloseHandler<PopupPanel>() {
            @Override
            public void onClose(CloseEvent<PopupPanel> event) {
                contextMenu = null;
            }
        });
    }

    /**
     * Handles a context menu event on table body.
     *
     * @param left left position of the context menu
     * @param top top position of the context menu
     * @return true if a context menu was shown, otherwise false
     */
    private boolean handleBodyContextMenu(int left, int top) {
        if (enabled && bodyActionKeys != null) {
            top += Window.getScrollTop();
            left += Window.getScrollLeft();
            client.getContextMenu().showAt(this, left, top);
            return true;
        }
        return false;
    }

    /**
     * Fires a column resize event which sends the resize information to the
     * server.
     *
     * @param columnId The columnId of the column which was resized
     * @param originalWidth The width in pixels of the column before the resize
     * event
     * @param newWidth The width in pixels of the column after the resize event
     */
    private void fireColumnResizeEvent(String columnId, int originalWidth,
            int newWidth) {
        client.updateVariable(paintableId, "columnResizeEventColumn", columnId,
                false);
        client.updateVariable(paintableId, "columnResizeEventPrev",
                originalWidth, false);
        client.updateVariable(paintableId, "columnResizeEventCurr", newWidth,
                immediate);

    }

    /**
     * Non-immediate variable update of column widths for a collection of
     * columns.
     *
     * @param columns the columns to trigger the events for.
     */
    private void sendColumnWidthUpdates(Collection<HeaderCell> columns) {
        String[] newSizes = new String[columns.size()];
        int ix = 0;
        for (HeaderCell cell : columns) {
            newSizes[ix++] = cell.getColKey() + ":" + cell.getWidth();
        }
        client.updateVariable(paintableId, "columnWidthUpdates", newSizes,
                false);
    }

    /**
     * Moves the focus one step down.
     *
     * @return Returns true if succeeded
     */
    private boolean moveFocusDown() {
        return moveFocusDown(0);
    }

    /**
     * Moves the focus down by 1+offset rows.
     *
     * @param offset the offset
     * @return Returns true if succeeded, else false if the selection could not
     * be move downwards
     */
    private boolean moveFocusDown(int offset) {
        if (isSelectable()) {
            if (focusedRow == null && scrollBody.iterator().hasNext()) {
                // FIXME should focus first visible from top, not first rendered
                // ??
                return setRowFocus((VScrollTableRow) scrollBody.iterator()
                        .next());
            } else {
                VScrollTableRow next = getNextRow(focusedRow, offset);
                if (next != null) {
                    return setRowFocus(next);
                }
            }
        }

        return false;
    }

    /**
     * Moves the selection one step up.
     *
     * @return Returns true if succeeded
     */
    private boolean moveFocusUp() {
        return moveFocusUp(0);
    }

    /**
     * Moves the focus row upwards.
     *
     * @param offset the offset
     * @return Returns true if succeeded, else false if the selection could not
     * be move upwards
     */
    private boolean moveFocusUp(int offset) {
        if (isSelectable()) {
            if (focusedRow == null && scrollBody.iterator().hasNext()) {
                // FIXME logic is exactly the same as in moveFocusDown, should
                // be the opposite??
                return setRowFocus((VScrollTableRow) scrollBody.iterator()
                        .next());
            } else {
                VScrollTableRow prev = getPreviousRow(focusedRow, offset);
                if (prev != null) {
                    return setRowFocus(prev);
                } else {
                    VConsole.log("no previous available");
                }
            }
        }

        return false;
    }

    /**
     * Selects a row where the current selection head is.
     *
     * @param ctrlSelect Is the selection a ctrl+selection
     * @param shiftSelect Is the selection a shift+selection
     * @return Returns truw
     */
    private void selectFocusedRow(boolean ctrlSelect, boolean shiftSelect) {
        if (focusedRow != null) {
            // Arrows moves the selection and clears previous selections
            if (isSelectable() && !ctrlSelect && !shiftSelect) {
                deselectAll();
                focusedRow.toggleSelection();
                selectionRangeStart = focusedRow;
            } else if (isSelectable() && ctrlSelect && !shiftSelect) {
                // Ctrl+arrows moves selection head
                selectionRangeStart = focusedRow;
                // No selection, only selection head is moved
            } else if (isMultiSelectModeAny() && !ctrlSelect && shiftSelect) {
                // Shift+arrows selection selects a range
                focusedRow.toggleShiftSelection(shiftSelect);
            }
        }
    }

    /**
     * Sends the selection to the server if changed since the last update/visit.
     */
    protected void sendSelectedRows() {
        sendSelectedRows(immediate);
    }

    /**
     * Sends the selection to the server if it has been changed since the last
     * update/visit.
     *
     * @param immediately set to true to immediately send the rows
     */
    protected void sendSelectedRows(boolean immediately) {
        // Don't send anything if selection has not changed
        if (!selectionChanged) {
            return;
        }

        // Reset selection changed flag
        selectionChanged = false;

        // Note: changing the immediateness of this might require changes to
        // "clickEvent" immediateness also.
        if (isMultiSelectModeDefault()) {
            // Convert ranges to a set of strings
            Set<String> ranges = new HashSet<String>();
            for (SelectionRange range : selectedRowRanges) {
                ranges.add(range.toString());
            }

            // Send the selected row ranges
            client.updateVariable(paintableId, "selectedRanges",
                    ranges.toArray(new String[selectedRowRanges.size()]), false);
            selectedRowRanges.clear();

            // clean selectedRowKeys so that they don't contain excess values
            for (Iterator<String> iterator = selectedRowKeys.iterator(); iterator
                    .hasNext();) {
                String key = iterator.next();
                VScrollTableRow renderedRowByKey = getRenderedRowByKey(key);
                if (renderedRowByKey != null) {
                    for (SelectionRange range : selectedRowRanges) {
                        if (range.inRange(renderedRowByKey)) {
                            iterator.remove();
                        }
                    }
                } else {
                    // orphaned selected key, must be in a range, ignore
                    iterator.remove();
                }

            }
        }

        // Send the selected rows
        client.updateVariable(paintableId, "selected",
                selectedRowKeys.toArray(new String[selectedRowKeys.size()]),
                immediately);

    }

    /**
     * Get the key that moves the selection head upwards. By default it is the
     * up arrow key but by overriding this you can change the key to whatever
     * you want.
     *
     * @return The keycode of the key
     */
    protected int getNavigationUpKey() {
        return KeyCodes.KEY_UP;
    }

    /**
     * Get the key that moves the selection head downwards. By default it is the
     * down arrow key but by overriding this you can change the key to whatever
     * you want.
     *
     * @return The keycode of the key
     */
    protected int getNavigationDownKey() {
        return KeyCodes.KEY_DOWN;
    }

    /**
     * Get the key that scrolls to the left in the table. By default it is the
     * left arrow key but by overriding this you can change the key to whatever
     * you want.
     *
     * @return The keycode of the key
     */
    protected int getNavigationLeftKey() {
        return KeyCodes.KEY_LEFT;
    }

    /**
     * Get the key that scroll to the right on the table. By default it is the
     * right arrow key but by overriding this you can change the key to whatever
     * you want.
     *
     * @return The keycode of the key
     */
    protected int getNavigationRightKey() {
        return KeyCodes.KEY_RIGHT;
    }

    /**
     * Get the key that selects an item in the table. By default it is the space
     * bar key but by overriding this you can change the key to whatever you
     * want.
     *
     * @return the navigation select key
     */
    protected int getNavigationSelectKey() {
        return CHARCODE_SPACE;
    }

    /**
     * Get the key the moves the selection one page up in the table. By default
     * this is the Page Up key but by overriding this you can change the key to
     * whatever you want.
     *
     * @return the navigation page up key
     */
    protected int getNavigationPageUpKey() {
        return KeyCodes.KEY_PAGEUP;
    }

    /**
     * Get the key the moves the selection one page down in the table. By
     * default this is the Page Down key but by overriding this you can change
     * the key to whatever you want.
     *
     * @return the navigation page down key
     */
    protected int getNavigationPageDownKey() {
        return KeyCodes.KEY_PAGEDOWN;
    }

    /**
     * Get the key the moves the selection to the beginning of the table. By
     * default this is the Home key but by overriding this you can change the
     * key to whatever you want.
     *
     * @return the navigation start key
     */
    protected int getNavigationStartKey() {
        return KeyCodes.KEY_HOME;
    }

    /**
     * Get the key the moves the selection to the end of the table. By default
     * this is the End key but by overriding this you can change the key to
     * whatever you want.
     *
     * @return the navigation end key
     */
    protected int getNavigationEndKey() {
        return KeyCodes.KEY_END;
    }

    /**
     * For internal use only. May be removed or replaced in the future.
     *
     * @param uidl the uidl
     * @param rowData the row data
     */
    public void initializeRows(UIDL uidl, UIDL rowData) {
        if (scrollBody != null) {
            scrollBody.removeFromParent();
        }

        // Without this call the scroll position is messed up in IE even after
        // the lazy scroller has set the scroll position to the first visible
        // item
        scrollBodyPanel.getScrollPosition();

        scrollBody = createScrollBody();

        scrollBody.renderInitialRows(rowData, uidl.getIntAttribute("firstrow"),
                uidl.getIntAttribute("rows"));
        scrollBodyPanel.add(scrollBody);

        // New body starts scrolled to the left, make sure the header and footer
        // are also scrolled to the left
        tHead.setHorizontalScrollPosition(0);
        tFoot.setHorizontalScrollPosition(0);

        initialContentReceived = true;
        sizeNeedsInit = true;

        scrollBody.restoreRowVisibility();
    }

    /**
     * For internal use only. May be removed or replaced in the future.
     *
     * @param uidl the uidl
     */
    public void updateColumnProperties(UIDL uidl) {
        updateColumnOrder(uidl);

        updateCollapsedColumns(uidl);

        UIDL vc = uidl.getChildByTagName("visiblecolumns");
        UIDL vc1 = uidl.getChildByTagName("singleradiocolumns");
        if (vc1 != null) {
            tHead.updateCellsRadioFromUIDL(vc1);
        }
        if (vc != null) {
            tHead.updateCellsFromUIDL(vc);
            tFoot.updateCellsFromUIDL(vc);
        }

        updateHeader(uidl.getStringArrayAttribute("vcolorder"));
        updateFooter(uidl.getStringArrayAttribute("vcolorder"));
        if (uidl.hasVariable("noncollapsiblecolumns")) {
            noncollapsibleColumns = uidl
                    .getStringArrayVariableAsSet("noncollapsiblecolumns");
        }
    }

    /**
     * Update collapsed columns.
     *
     * @param uidl the uidl
     */
    private void updateCollapsedColumns(UIDL uidl) {
        if (uidl.hasVariable("collapsedcolumns")) {
            tHead.setColumnCollapsingAllowed(true);
            collapsedColumns = uidl
                    .getStringArrayVariableAsSet("collapsedcolumns");
        } else {
            tHead.setColumnCollapsingAllowed(false);
        }
    }

    /**
     * Update column order.
     *
     * @param uidl the uidl
     */
    private void updateColumnOrder(UIDL uidl) {
        if (uidl.hasVariable("columnorder")) {
            columnReordering = true;
            columnOrder = uidl.getStringArrayVariable("columnorder");
        } else {
            columnReordering = false;
            columnOrder = null;
        }
    }

    /**
     * For internal use only. May be removed or replaced in the future.
     *
     * @param uidl the uidl
     * @return true, if successful
     */
    public boolean selectSelectedRows(UIDL uidl) {
        boolean keyboardSelectionOverRowFetchInProgress = false;

        if (uidl.hasVariable("selected")) {
            final Set<String> selectedKeys = uidl
                    .getStringArrayVariableAsSet("selected");
            removeUnselectedRowKeys(selectedKeys);

            if (scrollBody != null) {
                Iterator<Widget> iterator = scrollBody.iterator();
                while (iterator.hasNext()) {
                    /*
                     * Make the focus reflect to the server side state unless we
                     * are currently selecting multiple rows with keyboard.
                     */
                    VScrollTableRow row = (VScrollTableRow) iterator.next();
                    boolean selected = selectedKeys.contains(row.getKey());
                    if (!selected
                            && unSyncedselectionsBeforeRowFetch != null
                            && unSyncedselectionsBeforeRowFetch.contains(row
                            .getKey())) {
                        selected = true;
                        keyboardSelectionOverRowFetchInProgress = true;
                    }
                    if (selected && selectedKeys.size() == 1) {
                        /*
                         * If a single item is selected, move focus to the
                         * selected row. (#10522)
                         */
                        setRowFocus(row);
                    }

                    if (selected != row.isSelected()) {
                        row.toggleSelection();

                        if (!isSingleSelectMode() && !selected) {
                            // Update selection range in case a row is
                            // unselected from the middle of a range - #8076
                            removeRowFromUnsentSelectionRanges(row);
                        }
                    }
                }
            }
        }
        unSyncedselectionsBeforeRowFetch = null;
        return keyboardSelectionOverRowFetchInProgress;
    }

    /**
     * Removes the unselected row keys.
     *
     * @param selectedKeys the selected keys
     */
    private void removeUnselectedRowKeys(final Set<String> selectedKeys) {
        List<String> unselectedKeys = new ArrayList<String>(0);
        for (String key : selectedRowKeys) {
            if (!selectedKeys.contains(key)) {
                unselectedKeys.add(key);
            }
        }
        selectedRowKeys.removeAll(unselectedKeys);
    }

    /**
     * For internal use only. May be removed or replaced in the future.
     *
     * @param uidl the uidl
     */
    public void updateSortingProperties(UIDL uidl) {
        oldSortColumn = sortColumn;
        if (uidl.hasVariable("sortascending")) {
            sortAscending = uidl.getBooleanVariable("sortascending");
            sortColumn = uidl.getStringVariable("sortcolumn");
        }
    }

    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public void resizeSortedColumnForSortIndicator() {
        // Force recalculation of the captionContainer element inside the header
        // cell to accomodate for the size of the sort arrow.
        HeaderCell sortedHeader = tHead.getHeaderCell(sortColumn);
        if (sortedHeader != null) {
            // Mark header as sorted now. Any earlier marking would lead to
            // columns with wrong sizes
            sortedHeader.setSorted(true);
            tHead.resizeCaptionContainer(sortedHeader);
        }
        // Also recalculate the width of the captionContainer element in the
        // previously sorted header, since this now has more room.
        HeaderCell oldSortedHeader = tHead.getHeaderCell(oldSortColumn);
        if (oldSortedHeader != null) {
            tHead.resizeCaptionContainer(oldSortedHeader);
        }
    }
    /**
     * The lazy scroller is active.
     */
    private boolean lazyScrollerIsActive;

    /**
     * Disable lazy scroller.
     */
    private void disableLazyScroller() {
        lazyScrollerIsActive = false;
        scrollBodyPanel.getElement().getStyle().clearOverflowX();
        scrollBodyPanel.getElement().getStyle().clearOverflowY();
    }

    /**
     * Enable lazy scroller.
     */
    private void enableLazyScroller() {
        Scheduler.get().scheduleDeferred(lazyScroller);
        lazyScrollerIsActive = true;
        // prevent scrolling to jump in IE11
        scrollBodyPanel.getElement().getStyle().setOverflowX(Overflow.HIDDEN);
        scrollBodyPanel.getElement().getStyle().setOverflowY(Overflow.HIDDEN);
    }

    /**
     * Checks if is lazy scroller active.
     *
     * @return true, if is lazy scroller active
     */
    private boolean isLazyScrollerActive() {
        return lazyScrollerIsActive;
    }
    /**
     * The lazy scroller.
     */
    private ScheduledCommand lazyScroller = new ScheduledCommand() {
        @Override
        public void execute() {
            if (firstvisible >= 0) {
                firstRowInViewPort = firstvisible;
                if (firstvisibleOnLastPage > -1) {
                    scrollBodyPanel
                            .setScrollPosition(measureRowHeightOffset(firstvisibleOnLastPage));
                } else {
                    scrollBodyPanel
                            .setScrollPosition(measureRowHeightOffset(firstvisible));
                }
            }
            disableLazyScroller();
        }
    };

    /**
     * For internal use only. May be removed or replaced in the future.
     *
     * @param uidl the uidl
     */
    public void updateFirstVisibleAndScrollIfNeeded(UIDL uidl) {
        firstvisible = uidl.hasVariable("firstvisible") ? uidl
                .getIntVariable("firstvisible") : 0;
        firstvisibleOnLastPage = uidl.hasVariable("firstvisibleonlastpage") ? uidl
                .getIntVariable("firstvisibleonlastpage") : -1;
        if (firstvisible != lastRequestedFirstvisible && scrollBody != null) {

            // Update lastRequestedFirstvisible right away here
            // (don't rely on update in the timer which could be cancelled).
            lastRequestedFirstvisible = firstRowInViewPort;

            // Only scroll if the first visible changes from the server side.
            // Else we might unintentionally scroll even when the scroll
            // position has not changed.
            enableLazyScroller();
        }
    }

    /**
     * Measure row height offset.
     *
     * @param rowIx the row ix
     * @return the int
     */
    protected int measureRowHeightOffset(int rowIx) {
        return (int) (rowIx * scrollBody.getRowHeight());
    }

    /**
     * For internal use only. May be removed or replaced in the future.
     *
     * @param uidl the uidl
     */
    public void updatePageLength(UIDL uidl) {
        int oldPageLength = pageLength;
        if (uidl.hasAttribute("pagelength")) {
            pageLength = uidl.getIntAttribute("pagelength");
        } else {
            // pagelenght is "0" meaning scrolling is turned off
            pageLength = totalRows;
        }

        if (oldPageLength != pageLength && initializedAndAttached) {
            // page length changed, need to update size
            sizeNeedsInit = true;
        }
    }

    /**
     * For internal use only. May be removed or replaced in the future.
     *
     * @param uidl the uidl
     * @param state the state
     * @param readOnly the read only
     */
    public void updateSelectionProperties(UIDL uidl,
            AbstractComponentState state, boolean readOnly) {
        setMultiSelectMode(uidl.hasAttribute("multiselectmode") ? uidl
                .getIntAttribute("multiselectmode") : MULTISELECT_MODE_DEFAULT);

        nullSelectionAllowed = uidl.hasAttribute("nsa") ? uidl
                .getBooleanAttribute("nsa") : true;

        if (uidl.hasAttribute("selectmode")) {
            if (readOnly) {
                selectMode = SelectMode.NONE;
            } else if (uidl.getStringAttribute("selectmode").equals("multi")) {
                selectMode = SelectMode.MULTI;
            } else if (uidl.getStringAttribute("selectmode").equals("single")) {
                selectMode = SelectMode.SINGLE;
            } else {
                selectMode = SelectMode.NONE;
            }
        }
    }

    /**
     * For internal use only. May be removed or replaced in the future.
     *
     * @param uidl the uidl
     */
    public void updateDragMode(UIDL uidl) {
        dragmode = uidl.hasAttribute("dragmode") ? uidl
                .getIntAttribute("dragmode") : 0;
        if (BrowserInfo.get().isIE()) {
            if (dragmode > 0) {
                getElement().setPropertyJSO("onselectstart",
                        getPreventTextSelectionIEHack());
            } else {
                getElement().setPropertyJSO("onselectstart", null);
            }
        }
    }

    /**
     * For internal use only. May be removed or replaced in the future.
     *
     * @param uidl the uidl
     */
    public void updateTotalRows(UIDL uidl) {
        int newTotalRows = uidl.getIntAttribute("totalrows");
        if (newTotalRows != getTotalRows()) {
            if (scrollBody != null) {
                if (getTotalRows() == 0) {
                    tHead.clear();
                    tFoot.clear();
                    tdHead.clear();
                    ttHead.clear();
                }
                initializedAndAttached = false;
                initialContentReceived = false;
                isNewBody = true;
            }
            setTotalRows(newTotalRows);
        }
    }

    /**
     * Sets the total rows.
     *
     * @param newTotalRows the new total rows
     */
    protected void setTotalRows(int newTotalRows) {
        totalRows = newTotalRows;
    }

    /**
     * Gets the total rows.
     *
     * @return the total rows
     */
    public int getTotalRows() {
        return totalRows;
    }

    /**
     * Returns the extra space that is given to the header column when column
     * width is determined by header text.
     *
     * @return extra space in pixels
     */
    private int getHeaderPadding() {
        return scrollBody.getCellExtraWidth();
    }

    /**
     * This method exists for the needs of {@link VTreeTable} only. Not part of
     * the official API, <b>extend at your own risk</b>. May be removed or
     * replaced in the future.
     *
     * @return index of TreeTable's hierarchy column, or -1 if not applicable
     */
    protected int getHierarchyColumnIndex() {
        return -1;
    }

    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public void updateMaxIndent() {
        int oldIndent = scrollBody.getMaxIndent();
        scrollBody.calculateMaxIndent();
        if (oldIndent != scrollBody.getMaxIndent()) {
            // indent updated, headers might need adjusting
            triggerLazyColumnAdjustment(true);
        }
    }

    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public void focusRowFromBody() {
        if (selectedRowKeys.size() == 1) {
            // try to focus a row currently selected and in viewport
            String selectedRowKey = selectedRowKeys.iterator().next();
            if (selectedRowKey != null) {
                VScrollTableRow renderedRow = getRenderedRowByKey(selectedRowKey);
                if (renderedRow == null || !renderedRow.isInViewPort()) {
                    setRowFocus(scrollBody.getRowByRowIndex(firstRowInViewPort));
                } else {
                    setRowFocus(renderedRow);
                }
            }
        } else {
            // multiselect mode
            setRowFocus(scrollBody.getRowByRowIndex(firstRowInViewPort));
        }
    }

    /**
     * Creates the scroll body.
     *
     * @return the v scroll table body
     */
    protected VScrollTableBody createScrollBody() {
        return new VScrollTableBody();
    }

    /**
     * Selects the last row visible in the table
     * <p>
     * For internal use only. May be removed or replaced in the future.
     *
     * @param focusOnly Should the focus only be moved to the last row
     */
    public void selectLastRenderedRowInViewPort(boolean focusOnly) {
        int index = firstRowInViewPort + getFullyVisibleRowCount();
        VScrollTableRow lastRowInViewport = scrollBody.getRowByRowIndex(index);
        if (lastRowInViewport == null) {
            // this should not happen in normal situations (white space at the
            // end of viewport). Select the last rendered as a fallback.
            lastRowInViewport = scrollBody.getRowByRowIndex(scrollBody
                    .getLastRendered());
            if (lastRowInViewport == null) {
                return; // empty table
            }
        }
        setRowFocus(lastRowInViewport);
        if (!focusOnly) {
            selectFocusedRow(false, multiselectPending);
            sendSelectedRows();
        }
    }

    /**
     * Selects the first row visible in the table
     * <p>
     * For internal use only. May be removed or replaced in the future.
     *
     * @param focusOnly Should the focus only be moved to the first row
     */
    public void selectFirstRenderedRowInViewPort(boolean focusOnly) {
        int index = firstRowInViewPort;
        VScrollTableRow firstInViewport = scrollBody.getRowByRowIndex(index);
        if (firstInViewport == null) {
            // this should not happen in normal situations
            return;
        }
        setRowFocus(firstInViewport);
        if (!focusOnly) {
            selectFocusedRow(false, multiselectPending);
            sendSelectedRows();
        }
    }

    /**
     * For internal use only. May be removed or replaced in the future.
     *
     * @param uidl the new cache rate from uidl
     */
    public void setCacheRateFromUIDL(UIDL uidl) {
        setCacheRate(uidl.hasAttribute("cr") ? uidl.getDoubleAttribute("cr")
                : CACHE_RATE_DEFAULT);
    }

    /**
     * Sets the cache rate.
     *
     * @param d the new cache rate
     */
    private void setCacheRate(double d) {
        if (cache_rate != d) {
            cache_rate = d;
            cache_react_rate = 0.75 * d;
        }
    }

    /**
     * For internal use only. May be removed or replaced in the future.
     *
     * @param mainUidl the main uidl
     */
    public void updateActionMap(UIDL mainUidl) {
        UIDL actionsUidl = mainUidl.getChildByTagName("actions");
        if (actionsUidl == null) {
            return;
        }

        final Iterator<?> it = actionsUidl.getChildIterator();
        while (it.hasNext()) {
            final UIDL action = (UIDL) it.next();
            final String key = action.getStringAttribute("key");
            final String caption = action.getStringAttribute("caption");
            actionMap.put(key + "_c", caption);
            if (action.hasAttribute("icon")) {
                // TODO need some uri handling ??
                actionMap.put(key + "_i", client.translateVaadinUri(action
                        .getStringAttribute("icon")));
            } else {
                actionMap.remove(key + "_i");
            }
        }

    }

    /**
     * Gets the action caption.
     *
     * @param actionKey the action key
     * @return the action caption
     */
    public String getActionCaption(String actionKey) {
        return actionMap.get(actionKey + "_c");
    }

    /**
     * Gets the action icon.
     *
     * @param actionKey the action key
     * @return the action icon
     */
    public String getActionIcon(String actionKey) {
        return actionMap.get(actionKey + "_i");
    }

    /**
     * Update header.
     *
     * @param strings the strings
     */
    private void updateHeader(String[] strings) {
        if (strings == null) {
            return;
        }

        int visibleCols = strings.length;
        int colIndex = 0;
        if (showRowHeaders) {
            tHead.enableColumn(ROW_HEADER_COLUMN_KEY, colIndex);
            visibleCols++;
            visibleColOrder = new String[visibleCols];
            visibleColOrder[colIndex] = ROW_HEADER_COLUMN_KEY;
            colIndex++;
        } else {
            visibleColOrder = new String[visibleCols];
            tHead.removeCell(ROW_HEADER_COLUMN_KEY);
        }

        int i;
        for (i = 0; i < strings.length; i++) {
            final String cid = strings[i];
            visibleColOrder[colIndex] = cid;
            tHead.enableColumn(cid, colIndex);
            colIndex++;
        }

        tHead.setVisible(showColHeaders);
//        setContainerHeight();

    }

    /**
     * Updates footers.
     * <p>
     * Update headers whould be called before this method is called!
     * </p>
     *
     * @param strings the strings
     */
    private void updateFooter(String[] strings) {
        if (strings == null) {
            return;
        }

        // Add dummy column if row headers are present
        int colIndex = 0;
        if (showRowHeaders) {
            tFoot.enableColumn(ROW_HEADER_COLUMN_KEY, colIndex);
            colIndex++;
        } else {
            tFoot.removeCell(ROW_HEADER_COLUMN_KEY);
        }

        int i;
        for (i = 0; i < strings.length; i++) {
            final String cid = strings[i];
            tFoot.enableColumn(cid, colIndex);
            colIndex++;
        }

        tFoot.setVisible(showColFooters);
    }

    /**
     * For internal use only. May be removed or replaced in the future.
     *
     * @param uidl which contains row data
     * @param firstRow first row in data set
     * @param reqRows amount of rows in data set
     */
    public void updateBody(UIDL uidl, int firstRow, int reqRows) {
        int oldIndent = scrollBody.getMaxIndent();
        if (uidl == null || reqRows < 1) {
            // container is empty, remove possibly existing rows
            if (firstRow <= 0) {
                postponeSanityCheckForLastRendered = true;
                while (scrollBody.getLastRendered() > scrollBody
                        .getFirstRendered()) {
                    scrollBody.unlinkRow(false);
                }
                postponeSanityCheckForLastRendered = false;
                scrollBody.unlinkRow(false);
            }
            return;
        }

        scrollBody.renderRows(uidl, firstRow, reqRows);

        discardRowsOutsideCacheWindow();
        scrollBody.calculateMaxIndent();
        if (oldIndent != scrollBody.getMaxIndent()) {
            // indent updated, headers might need adjusting
            headerChangedDuringUpdate = true;
        }
    }

    /**
     * For internal use only. May be removed or replaced in the future.
     *
     * @param partialRowUpdates the partial row updates
     */
    public void updateRowsInBody(UIDL partialRowUpdates) {
        if (partialRowUpdates == null) {
            return;
        }
        int firstRowIx = partialRowUpdates.getIntAttribute("firsturowix");
        int count = partialRowUpdates.getIntAttribute("numurows");
        scrollBody.unlinkRows(firstRowIx, count);
        scrollBody.insertRows(partialRowUpdates, firstRowIx, count);
    }

    /**
     * Updates the internal cache by unlinking rows that fall outside of the
     * caching window.
     */
    protected void discardRowsOutsideCacheWindow() {
        int firstRowToKeep = (int) (firstRowInViewPort - pageLength
                * cache_rate);
        int lastRowToKeep = (int) (firstRowInViewPort + pageLength + pageLength
                * cache_rate);
        // sanity checks:
        if (firstRowToKeep < 0) {
            firstRowToKeep = 0;
        }
        if (lastRowToKeep > totalRows) {
            lastRowToKeep = totalRows - 1;
        }
        debug("Client side calculated cache rows to keep: " + firstRowToKeep
                + "-" + lastRowToKeep);

        if (serverCacheFirst != -1) {
            firstRowToKeep = serverCacheFirst;
            lastRowToKeep = serverCacheLast;
            debug("Server cache rows that override: " + serverCacheFirst + "-"
                    + serverCacheLast);
            if (firstRowToKeep < scrollBody.getFirstRendered()
                    || lastRowToKeep > scrollBody.getLastRendered()) {
                debug("*** Server wants us to keep " + serverCacheFirst + "-"
                        + serverCacheLast + " but we only have rows "
                        + scrollBody.getFirstRendered() + "-"
                        + scrollBody.getLastRendered() + " rendered!");
            }
        }
        discardRowsOutsideOf(firstRowToKeep, lastRowToKeep);

        scrollBody.fixSpacers();

        scrollBody.restoreRowVisibility();
    }

    /**
     * Discard rows outside of.
     *
     * @param optimalFirstRow the optimal first row
     * @param optimalLastRow the optimal last row
     */
    private void discardRowsOutsideOf(int optimalFirstRow, int optimalLastRow) {
        /*
         * firstDiscarded and lastDiscarded are only calculated for debug
         * purposes
         */
        int firstDiscarded = -1, lastDiscarded = -1;
        boolean cont = true;
        while (cont && scrollBody.getLastRendered() > optimalFirstRow
                && scrollBody.getFirstRendered() < optimalFirstRow) {
            if (firstDiscarded == -1) {
                firstDiscarded = scrollBody.getFirstRendered();
            }

            // removing row from start
            cont = scrollBody.unlinkRow(true);
        }
        if (firstDiscarded != -1) {
            lastDiscarded = scrollBody.getFirstRendered() - 1;
            debug("Discarded rows " + firstDiscarded + "-" + lastDiscarded);
        }
        firstDiscarded = lastDiscarded = -1;

        cont = true;
        while (cont && scrollBody.getLastRendered() > optimalLastRow) {
            if (lastDiscarded == -1) {
                lastDiscarded = scrollBody.getLastRendered();
            }

            // removing row from the end
            cont = scrollBody.unlinkRow(false);
        }
        if (lastDiscarded != -1) {
            firstDiscarded = scrollBody.getLastRendered() + 1;
            debug("Discarded rows " + firstDiscarded + "-" + lastDiscarded);
        }

        debug("Now in cache: " + scrollBody.getFirstRendered() + "-"
                + scrollBody.getLastRendered());
    }

    /**
     * Inserts rows in the table body or removes them from the table body based
     * on the commands in the UIDL.
     * <p>
     * For internal use only. May be removed or replaced in the future.
     *
     * @param partialRowAdditions the UIDL containing row updates.
     */
    public void addAndRemoveRows(UIDL partialRowAdditions) {
        if (partialRowAdditions == null) {
            return;
        }
        if (partialRowAdditions.hasAttribute("hide")) {
            scrollBody.unlinkAndReindexRows(
                    partialRowAdditions.getIntAttribute("firstprowix"),
                    partialRowAdditions.getIntAttribute("numprows"));
            scrollBody.ensureCacheFilled();
        } else {
            if (partialRowAdditions.hasAttribute("delbelow")) {
                scrollBody.insertRowsDeleteBelow(partialRowAdditions,
                        partialRowAdditions.getIntAttribute("firstprowix"),
                        partialRowAdditions.getIntAttribute("numprows"));
            } else {
                scrollBody.insertAndReindexRows(partialRowAdditions,
                        partialRowAdditions.getIntAttribute("firstprowix"),
                        partialRowAdditions.getIntAttribute("numprows"));
            }
        }

        discardRowsOutsideCacheWindow();
    }

    /**
     * Gives correct column index for given column key ("cid" in UIDL).
     *
     * @param colKey the col key
     * @return column index of visible columns, -1 if column not visible
     */
    private int getColIndexByKey(String colKey) {
        // return 0 if asked for rowHeaders
        if (ROW_HEADER_COLUMN_KEY.equals(colKey)) {
            return 0;
        }
        for (int i = 0; i < visibleColOrder.length; i++) {
            if (visibleColOrder[i].equals(colKey)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Checks if is multi select mode simple.
     *
     * @return true, if is multi select mode simple
     */
    private boolean isMultiSelectModeSimple() {
        return selectMode == SelectMode.MULTI
                && multiselectmode == MULTISELECT_MODE_SIMPLE;
    }

    /**
     * Checks if is single select mode.
     *
     * @return true, if is single select mode
     */
    private boolean isSingleSelectMode() {
        return selectMode == SelectMode.SINGLE;
    }

    /**
     * Checks if is multi select mode any.
     *
     * @return true, if is multi select mode any
     */
    private boolean isMultiSelectModeAny() {
        return selectMode == SelectMode.MULTI;
    }

    /**
     * Checks if is multi select mode default.
     *
     * @return true, if is multi select mode default
     */
    private boolean isMultiSelectModeDefault() {
        return selectMode == SelectMode.MULTI
                && multiselectmode == MULTISELECT_MODE_DEFAULT;
    }

    /**
     * Sets the multi select mode.
     *
     * @param multiselectmode the new multi select mode
     */
    private void setMultiSelectMode(int multiselectmode) {
        if (BrowserInfo.get().isTouchDevice()) {
            // Always use the simple mode for touch devices that do not have
            // shift/ctrl keys
            this.multiselectmode = MULTISELECT_MODE_SIMPLE;
        } else {
            this.multiselectmode = multiselectmode;
        }

    }

    /**
     * For internal use only. May be removed or replaced in the future.
     *
     * @return true, if is selectable
     */
    public boolean isSelectable() {
        return selectMode.getId() > SelectMode.NONE.getId();
    }

    /**
     * Checks if is collapsed column.
     *
     * @param colKey the col key
     * @return true, if is collapsed column
     */
    private boolean isCollapsedColumn(String colKey) {
        if (collapsedColumns == null) {
            return false;
        }
        if (collapsedColumns.contains(colKey)) {
            return true;
        }
        return false;
    }

    /**
     * Gets the col key by index.
     *
     * @param index the index
     * @return the col key by index
     */
    protected String getColKeyByIndex(int index) {
        return tHead.getHeaderCell(index).getColKey();
    }

    /**
     * Note: not part of the official API, extend at your own risk. May be
     * removed or replaced in the future.
     *
     * Sets the indicated column's width for headers and scrollBody alike.
     *
     * @param colIndex index of the modified column
     * @param w new width (may be subject to modifications if doesn't meet
     * minimum requirements)
     * @param isDefinedWidth disables expand ratio if set true
     */
    protected void setColWidth(int colIndex, int w, boolean isDefinedWidth) {
        final HeaderCell hcell = tHead.getHeaderCell(colIndex);

        // Make sure that the column grows to accommodate the sort indicator if
        // necessary.
        // get min width with no indent or padding
        int minWidth = hcell.getMinWidth(false, false);
        if (w < minWidth) {
            w = minWidth;
        }
        // Set header column width WITHOUT INDENT
        hcell.setWidth(w, isDefinedWidth);

        // Set footer column width likewise
        FooterCell fcell = tFoot.getFooterCell(colIndex);
        fcell.setWidth(w, isDefinedWidth);

        // Ensure indicators have been taken into account
        tHead.resizeCaptionContainer(hcell);
        // Make sure that the body column grows to accommodate the indent if
        // necessary.
        // get min width with indent, no padding
        minWidth = hcell.getMinWidth(true, false);
        if (w == minWidth) {
            w = w + 1;
            hcell.setWidth(w, isDefinedWidth);
            fcell.setWidth(w, isDefinedWidth);
            tHead.resizeCaptionContainer(hcell);
        }
        if (w < minWidth) {
            w = minWidth;
        }
        scrollBody.table.getStyle().setWidth(tHead.table.getOffsetWidth(), Unit.PX);
        // Set body column width
        scrollBody.setColWidth(colIndex, w);
    }

    /**
     * Gets the col width.
     *
     * @param colKey the col key
     * @return the col width
     */
    private int getColWidth(String colKey) {
        return tHead.getHeaderCell(colKey).getWidthWithIndent();
    }

    /**
     * Get a rendered row by its key.
     *
     * @param key The key to search with
     * @return the rendered row by key
     */
    public VScrollTableRow getRenderedRowByKey(String key) {
        if (scrollBody != null) {
            final Iterator<Widget> it = scrollBody.iterator();
            VScrollTableRow r = null;
            while (it.hasNext()) {
                r = (VScrollTableRow) it.next();
                if (r.getKey().equals(key)) {
                    return r;
                }
            }
        }
        return null;
    }

    /**
     * Returns the next row to the given row.
     *
     * @param row The row to calculate from
     * @param offset the offset
     * @return The next row or null if no row exists
     */
    private VScrollTableRow getNextRow(VScrollTableRow row, int offset) {
        final Iterator<Widget> it = scrollBody.iterator();
        VScrollTableRow r = null;
        while (it.hasNext()) {
            r = (VScrollTableRow) it.next();
            if (r == row) {
                r = null;
                while (offset >= 0 && it.hasNext()) {
                    r = (VScrollTableRow) it.next();
                    offset--;
                }
                return r;
            }
        }

        return null;
    }

    /**
     * Returns the previous row from the given row.
     *
     * @param row The row to calculate from
     * @param offset the offset
     * @return The previous row or null if no row exists
     */
    private VScrollTableRow getPreviousRow(VScrollTableRow row, int offset) {
        final Iterator<Widget> it = scrollBody.iterator();
        final Iterator<Widget> offsetIt = scrollBody.iterator();
        VScrollTableRow r = null;
        VScrollTableRow prev = null;
        while (it.hasNext()) {
            r = (VScrollTableRow) it.next();
            if (offset < 0) {
                prev = (VScrollTableRow) offsetIt.next();
            }
            if (r == row) {
                return prev;
            }
            offset--;
        }

        return null;
    }

    /**
     * Re order column.
     *
     * @param columnKey the column key
     * @param newIndex the new index
     */
    protected void reOrderColumn(String columnKey, int newIndex) {

        final int oldIndex = getColIndexByKey(columnKey);

        // Change header order
        tHead.moveCell(oldIndex, newIndex);

        // Change body order
        scrollBody.moveCol(oldIndex, newIndex);

        // Change footer order
        tFoot.moveCell(oldIndex, newIndex);

        /*
         * Build new columnOrder and update it to server Note that columnOrder
         * also contains collapsed columns so we cannot directly build it from
         * cells vector Loop the old columnOrder and append in order to new
         * array unless on moved columnKey. On new index also put the moved key
         * i == index on columnOrder, j == index on newOrder
         */
        final String oldKeyOnNewIndex = visibleColOrder[newIndex];
        if (showRowHeaders) {
            newIndex--; // columnOrder don't have rowHeader
        }
        // add back hidden rows,
        for (int i = 0; i < columnOrder.length; i++) {
            if (columnOrder[i].equals(oldKeyOnNewIndex)) {
                break; // break loop at target
            }
            if (isCollapsedColumn(columnOrder[i])) {
                newIndex++;
            }
        }
        // finally we can build the new columnOrder for server
        final String[] newOrder = new String[columnOrder.length];
        for (int i = 0, j = 0; j < newOrder.length; i++) {
            if (j == newIndex) {
                newOrder[j] = columnKey;
                j++;
            }
            if (i == columnOrder.length) {
                break;
            }
            if (columnOrder[i].equals(columnKey)) {
                continue;
            }
            newOrder[j] = columnOrder[i];
            j++;
        }
        columnOrder = newOrder;
        // also update visibleColumnOrder
        int i = showRowHeaders ? 1 : 0;
        for (int j = 0; j < newOrder.length; j++) {
            final String cid = newOrder[j];
            if (!isCollapsedColumn(cid)) {
                visibleColOrder[i++] = cid;
            }
        }
        client.updateVariable(paintableId, "columnorder", columnOrder, false);
        if (client.hasEventListeners(this,
                TableConstants.COLUMN_REORDER_EVENT_ID)) {
            client.sendPendingVariableChanges();
        }
    }

    /**
     * On detach.
     */
    @Override
    protected void onDetach() {
        detachedScrollPosition = scrollBodyPanel.getScrollPosition();
        rowRequestHandler.cancel();
        super.onDetach();
        // ensure that scrollPosElement will be detached
        if (scrollPositionElement != null) {
            final Element parent = DOM.getParent(scrollPositionElement);
            if (parent != null) {
                DOM.removeChild(parent, scrollPositionElement);
            }
        }
    }

    /**
     * On attach.
     */
    @Override
    public void onAttach() {
        super.onAttach();
        scrollBodyPanel.setScrollPosition(detachedScrollPosition);
    }

    /**
     * Run only once when component is attached and received its initial
     * content. This function:
     *
     * * Syncs headers and bodys "natural widths and saves the values.
     *
     * * Sets proper width and height
     *
     * * Makes deferred request to get some cache rows
     *
     * For internal use only. May be removed or replaced in the future.
     */
    public void sizeInit() {
        sizeNeedsInit = false;
        client.updateVariable(paintableId, "isReConstruct", sizeNeedsInit, false);

        scrollBody.setContainerHeight();

        /*
         * We will use browsers table rendering algorithm to find proper column
         * widths. If content and header take less space than available, we will
         * divide extra space relatively to each column which has not width set.
         * 
         * Overflow pixels are added to last column.
         */
        Iterator<Widget> headCells = tHead.iterator();
        Iterator<Widget> footCells = tFoot.iterator();
        Iterator<Widget> doubleheadCells = tdHead.iterator();
        Iterator<Widget> tripleHeadCells = ttHead.iterator();
        int i = 0;
        int totalExplicitColumnsWidths = 0;
        int total = 0;
        float expandRatioDivider = 0;

        final int[] widths = new int[tHead.visibleCells.size()];

        tHead.enableBrowserIntelligence();
        tFoot.enableBrowserIntelligence();
        tdHead.enableBrowserIntelligence();
        ttHead.enableBrowserIntelligence();
        int hierarchyColumnIndent = scrollBody != null ? scrollBody
                .getMaxIndent() : 0;
        HeaderCell hierarchyHeaderWithExpandRatio = null;
        // first loop: collect natural widths
        while (headCells.hasNext()) {

            final HeaderCell hCell = (HeaderCell) headCells.next();

            final FooterCell fCell = (FooterCell) footCells.next();
            boolean needsIndent = hierarchyColumnIndent > 0
                    && hCell.isHierarchyColumn();
            int w = hCell.getWidth();
            if (hCell.isDefinedWidth()) {
                // server has defined column width explicitly
                if (needsIndent && w < hierarchyColumnIndent) {
                    // hierarchy indent overrides explicitly set width
                    w = hierarchyColumnIndent;
                }
                totalExplicitColumnsWidths += w;
            } else {
                if (hCell.getExpandRatio() > 0) {
                    expandRatioDivider += hCell.getExpandRatio();
                    w = 0;
                    if (needsIndent && w < hierarchyColumnIndent) {
                        hierarchyHeaderWithExpandRatio = hCell;
                        // don't add to widths here, because will be included in
                        // the expand ratio space if there's enough of it
                    }
                } else {
                    // get and store greater of header width and column width,
                    // and store it as a minimum natural column width (these
                    // already contain the indent if any)
                    int headerWidth = hCell.getNaturalColumnWidth(i);
                    int footerWidth = fCell.getNaturalColumnWidth(i);
                    w = headerWidth > footerWidth ? headerWidth : footerWidth;
                }
                hCell.setNaturalMinimumColumnWidth(w);
                fCell.setNaturalMinimumColumnWidth(w);
            }
            widths[i] = w;
            total += w;
            i++;
        }
//        if (showDoubleColHeaders) {
//            while (doubleheadCells.hasNext()) {
//                final DoubleHeaderCell hCell = (DoubleHeaderCell) doubleheadCells.next();
//                if (!hCell.isDefinedWidth()) {
//                    int w = findTotalMinimumNaturalWidth(hCell.getColKey());
//                    hCell.setNaturalMinimumColumnWidth(w);
//                }
//            }
//
//        }
//        if (showTripleColHeaders) {
//            tripleHeadCells = ttHead.iterator();
//            while (tripleHeadCells.hasNext()) {
//                final TripleHeaderCell hCell = (TripleHeaderCell) tripleHeadCells.next();
//                if (!hCell.isDefinedWidth()) {
//                    int w = findTotalMinimumNaturalWidthForTripleHeader(hCell.getColKey());
//                    hCell.setNaturalMinimumColumnWidth(w);
//                }
//            }
//        }

        if (hierarchyHeaderWithExpandRatio != null) {
            total += hierarchyColumnIndent;
        }

        tHead.disableBrowserIntelligence();
        tFoot.disableBrowserIntelligence();
        tdHead.disableBrowserIntelligence();
        ttHead.disableBrowserIntelligence();
        boolean willHaveScrollbarz = willHaveScrollbars();

        // fix "natural" width if width not set
        if (isDynamicWidth()) {
            int w = total;
            w += scrollBody.getCellExtraWidth() * visibleColOrder.length;
            if (willHaveScrollbarz) {
                w += Util.getNativeScrollbarSize();
            }
            setContentWidth(w);
        }

        int availW = scrollBody.getAvailableWidth();
        if (BrowserInfo.get().isIE()) {
            // Hey IE, are you really sure about this?
            availW = scrollBody.getAvailableWidth();
        }
        availW -= scrollBody.getCellExtraWidth() * visibleColOrder.length;

        if (willHaveScrollbarz) {
            availW -= Util.getNativeScrollbarSize();
        }

        // TODO refactor this code to be the same as in resize timer
        if (availW > total) {
            // natural size is smaller than available space
            int extraSpace = availW - total;
            if (hierarchyHeaderWithExpandRatio != null) {
                /*
                 * add the indent's space back to ensure each column gets an
                 * even share according to the expand ratios (note: if the
                 * allocated space isn't enough for the hierarchy column it
                 * shall be treated like a defined width column and the indent
                 * space gets removed from the extra space again)
                 */
                extraSpace += hierarchyColumnIndent;
            }
            final int totalWidthR = total - totalExplicitColumnsWidths;
            int checksum = 0;

            if (extraSpace == 1) {
                // We cannot divide one single pixel so we give it the first
                // undefined column
                // no need to worry about indent here
                headCells = tHead.iterator();
                i = 0;
                checksum = availW;
                while (headCells.hasNext()) {
                    HeaderCell hc = (HeaderCell) headCells.next();
                    if (!hc.isDefinedWidth()) {
                        widths[i]++;
                        break;
                    }
                    i++;
                }

            } else if (expandRatioDivider > 0) {
                boolean setIndentToHierarchyHeader = false;
                if (hierarchyHeaderWithExpandRatio != null) {
                    // ensure first that the hierarchyColumn gets at least the
                    // space allocated for indent
                    final int newSpace = Math
                            .round((extraSpace * (hierarchyHeaderWithExpandRatio
                            .getExpandRatio() / expandRatioDivider)));
                    if (newSpace < hierarchyColumnIndent) {
                        // not enough space for indent, remove indent from the
                        // extraSpace again and handle hierarchy column's header
                        // separately
                        setIndentToHierarchyHeader = true;
                        extraSpace -= hierarchyColumnIndent;
                    }
                }

                // visible columns have some active expand ratios, excess
                // space is divided according to them
                headCells = tHead.iterator();
                i = 0;
                while (headCells.hasNext()) {
                    HeaderCell hCell = (HeaderCell) headCells.next();
                    if (hCell.getExpandRatio() > 0) {
                        int w = widths[i];
                        if (setIndentToHierarchyHeader
                                && hierarchyHeaderWithExpandRatio.equals(hCell)) {
                            // hierarchy column's header is no longer part of
                            // the expansion divide and only gets indent
                            w += hierarchyColumnIndent;
                        } else {
                            final int newSpace = Math
                                    .round((extraSpace * (hCell
                                    .getExpandRatio() / expandRatioDivider)));
                            w += newSpace;
                        }
                        widths[i] = w;
                    }
                    checksum += widths[i];
                    i++;
                }
            } else if (totalWidthR > 0) {
                // no expand ratios defined, we will share extra space
                // relatively to "natural widths" among those without
                // explicit width
                // no need to worry about indent here, it's already included
                headCells = tHead.iterator();
                i = 0;
                while (headCells.hasNext()) {
                    HeaderCell hCell = (HeaderCell) headCells.next();
                    if (!hCell.isDefinedWidth()) {
                        int w = widths[i];
                        final int newSpace = Math.round((float) extraSpace
                                * (float) w / totalWidthR);
                        w += newSpace;
                        widths[i] = w;
                    }
                    checksum += widths[i];
                    i++;
                }
            }

            if (extraSpace > 0 && checksum != availW) {
                /*
                 * There might be in some cases a rounding error of 1px when
                 * extra space is divided so if there is one then we give the
                 * first undefined column 1 more pixel
                 */
                headCells = tHead.iterator();
                i = 0;
                while (headCells.hasNext()) {
                    HeaderCell hc = (HeaderCell) headCells.next();
                    if (!hc.isDefinedWidth()) {
                        widths[i] += availW - checksum;
                        break;
                    }
                    i++;
                }
            }

        } else {
            // body's size will be more than available and scrollbar will appear
        }

        // last loop: set possibly modified values or reset if new tBody
        i = 0;
        headCells = tHead.iterator();
        while (headCells.hasNext()) {
            final HeaderCell hCell = (HeaderCell) headCells.next();
            if (isNewBody || hCell.getWidth() == -1) {
                final int w = widths[i];
                setColWidth(i, w, false);
            }
            i++;
        }
        if (showDoubleColHeaders) {
            doubleheadCells = tdHead.iterator();
            ArrayList<DoubleHeaderCell> columns = new ArrayList<DoubleHeaderCell>();
            int j = 0;
            boolean sendUpdate = false;
            while (doubleheadCells.hasNext()) {                
                final DoubleHeaderCell hCell = (DoubleHeaderCell) doubleheadCells.next();
                int iniWid = hCell.getWidth();
                int w = getDoubleHeaderColExtraWidth(hCell.getColKey());

                hCell.setWidth(w, true);
                setDoubleHeaderColWidth(j, w, false, false);
                columns.add(hCell);
                if (iniWid != hCell.getWidth()) {
                    sendUpdate = true;
                }
                j++;
            }
            if (sendUpdate) {
                sendDoubleHeaderColumnWidthUpdates(columns);
            }
            if (showTripleColHeaders) {
                sendUpdate = false;
                tripleHeadCells = ttHead.iterator();
                ArrayList<TripleHeaderCell> tcolumns = new ArrayList<TripleHeaderCell>();
                int k = 0;
                while (tripleHeadCells.hasNext()) {
                    final TripleHeaderCell hCell = (TripleHeaderCell) tripleHeadCells.next();
                    int iniWid = hCell.getWidth();
                    int w = getTripleHeaderColExtraWidth(hCell.getColKey());
                    hCell.setWidth(w, true);
                    setTripleHeaderColWidth(k, w, false, false);
                    tcolumns.add(hCell);
                    if (iniWid != hCell.getWidth()) {
                        sendUpdate = true;
                    }
                    k++;
                }
                if (sendUpdate) {
                    sendTripleHeaderColumnWidthUpdates(tcolumns);
                }
            }
        }

//        sendColumnWidthUpdates(columns1);
        initializedAndAttached = true;

        updatePageLength();
        if (rightTable != null) {
            scrollBody.freezeSpacer.addClassName("left-freeze-space");
        }
        /*
         * Fix "natural" height if height is not set. This must be after width
         * fixing so the components' widths have been adjusted.
         */
        if (isDynamicHeight()) {
            /*
             * We must force an update of the row height as this point as it
             * might have been (incorrectly) calculated earlier
             */

            /*
             * TreeTable updates stuff in a funky order, so we must set the
             * height as zero here before doing the real update to make it
             * realize that there is no content,
             */
            if (pageLength == totalRows && pageLength == 0) {
                scrollBody.setHeight("0px");
            }

            int bodyHeight;
            if (pageLength == totalRows) {
                /*
                 * A hack to support variable height rows when paging is off.
                 * Generally this is not supported by scrolltable. We want to
                 * show all rows so the bodyHeight should be equal to the table
                 * height.
                 */
                // int bodyHeight = scrollBody.getOffsetHeight();
                bodyHeight = scrollBody.getRequiredHeight();
            } else {
                bodyHeight = (int) Math.round(scrollBody.getRowHeight(true)
                        * pageLength);
            }
            boolean needsSpaceForHorizontalSrollbar = (total > availW);
            if (needsSpaceForHorizontalSrollbar) {
                bodyHeight += Util.getNativeScrollbarSize();
            }
            scrollBodyPanel.setHeight(bodyHeight + "px");
            if (rightScrollBodyPanel != null) {
                scrollBodyPanel.setHeight(rightScrollBodyPanel.getOffsetHeight() + "px");
                scrollBody.table.getStyle().setWidth(scrollBody.freezeSpacer.getOffsetWidth(), Unit.PX);
            }
            if (leftScrollBodyPanel != null) {
                leftScrollBodyPanel.setHeight(bodyHeight + "px");
            }
            Util.runWebkitOverflowAutoFix(scrollBodyPanel.getElement());
        }

        isNewBody = false;

        if (firstvisible > 0) {
            enableLazyScroller();
        }

        if (enabled) {
            // Do we need cache rows
            if (scrollBody.getLastRendered() + 1 < firstRowInViewPort
                    + pageLength + (int) cache_react_rate * pageLength) {
                if (totalRows - 1 > scrollBody.getLastRendered()) {
                    // fetch cache rows
                    int firstInNewSet = scrollBody.getLastRendered() + 1;
                    int lastInNewSet = (int) (firstRowInViewPort + pageLength + cache_rate
                            * pageLength);
                    if (lastInNewSet > totalRows - 1) {
                        lastInNewSet = totalRows - 1;
                    }
                    rowRequestHandler.triggerRowFetch(firstInNewSet,
                            lastInNewSet - firstInNewSet + 1, 1);
                }
            }
        }

        /*
         * Ensures the column alignments are correct at initial loading. <br/>
         * (child components widths are correct)
         */
        Util.runWebkitOverflowAutoFixDeferred(scrollBodyPanel.getElement());

        hadScrollBars = willHaveScrollbarz;

    }

    /**
     * Note: this method is not part of official API although declared as
     * protected. Extend at your own risk.
     *
     * @return true if content area will have scrollbars visible.
     */
    protected boolean willHaveScrollbars() {
        if (rightTable != null) {
            return false;
        }
        if (isDynamicHeight()) {
            if (pageLength < totalRows) {
                return true;
            }
        } else {
            int fakeheight = (int) Math.round(scrollBody.getRowHeight()
                    * totalRows);
            int availableHeight = scrollBodyPanel.getElement().getPropertyInt(
                    "clientHeight");
            if (fakeheight > availableHeight) {
                return true;
            }
        }
        return false;
    }

    /**
     * Announce scroll position.
     */
    private void announceScrollPosition() {
        if (scrollPositionElement == null) {
            scrollPositionElement = DOM.createDiv();
            scrollPositionElement.setClassName(getStylePrimaryName()
                    + "-scrollposition");
            scrollPositionElement.getStyle().setPosition(Position.ABSOLUTE);
            scrollPositionElement.getStyle().setDisplay(Display.NONE);
            getElement().appendChild(scrollPositionElement);
        }

        Style style = scrollPositionElement.getStyle();
        style.setMarginLeft(getElement().getOffsetWidth() / 2 - 80, Unit.PX);
        style.setMarginTop(-scrollBodyPanel.getOffsetHeight(), Unit.PX);

        // indexes go from 1-totalRows, as rowheaders in index-mode indicate
        int last = (firstRowInViewPort + pageLength);
        if (last > totalRows) {
            last = totalRows;
        }
        scrollPositionElement.setInnerHTML("<span>" + (firstRowInViewPort + 1)
                + " &ndash; " + (last) + "..." + "</span>");
        style.setDisplay(Display.BLOCK);
    }

    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public void hideScrollPositionAnnotation() {
        if (scrollPositionElement != null) {
            scrollPositionElement.getStyle().setDisplay(Display.NONE);
        }
    }

    /**
     * For internal use only. May be removed or replaced in the future.
     *
     * @return true, if is scroll position visible
     */
    public boolean isScrollPositionVisible() {
        return scrollPositionElement != null
                && !scrollPositionElement.getStyle().getDisplay()
                .equals(Display.NONE.toString());
    }

    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public class RowRequestHandler extends Timer {

        /**
         * The req first row.
         */
        private int reqFirstRow = 0;
        /**
         * The req rows.
         */
        private int reqRows = 0;
        /**
         * The is request handler running.
         */
        private boolean isRequestHandlerRunning = false;

        /**
         * Trigger row fetch.
         *
         * @param first the first
         * @param rows the rows
         */
        public void triggerRowFetch(int first, int rows) {
            setReqFirstRow(first);
            setReqRows(rows);
            deferRowFetch();
        }

        /**
         * Trigger row fetch.
         *
         * @param first the first
         * @param rows the rows
         * @param delay the delay
         */
        public void triggerRowFetch(int first, int rows, int delay) {
            setReqFirstRow(first);
            setReqRows(rows);
            deferRowFetch(delay);
        }

        /**
         * Defer row fetch.
         */
        public void deferRowFetch() {
            deferRowFetch(250);
        }

        /**
         * Checks if is request handler running.
         *
         * @return true, if is request handler running
         */
        public boolean isRequestHandlerRunning() {
            return isRequestHandlerRunning;
        }

        /**
         * Defer row fetch.
         *
         * @param msec the msec
         */
        public void deferRowFetch(int msec) {
            isRequestHandlerRunning = true;
            if (reqRows > 0 && reqFirstRow < totalRows) {
                schedule(msec);

                // tell scroll position to user if currently "visible" rows are
                // not rendered
                if (totalRows > pageLength
                        && ((firstRowInViewPort + pageLength > scrollBody
                        .getLastRendered()) || (firstRowInViewPort < scrollBody
                        .getFirstRendered()))) {
                    announceScrollPosition();
                } else {
                    hideScrollPositionAnnotation();
                }
            }
        }

        /**
         * Gets the req first row.
         *
         * @return the req first row
         */
        public int getReqFirstRow() {
            return reqFirstRow;
        }

        /**
         * Sets the req first row.
         *
         * @param reqFirstRow the new req first row
         */
        public void setReqFirstRow(int reqFirstRow) {
            if (reqFirstRow < 0) {
                this.reqFirstRow = 0;
            } else if (reqFirstRow >= totalRows) {
                this.reqFirstRow = totalRows - 1;
            } else {
                this.reqFirstRow = reqFirstRow;
            }
        }

        /**
         * Sets the req rows.
         *
         * @param reqRows the new req rows
         */
        public void setReqRows(int reqRows) {
            if (reqRows < 0) {
                this.reqRows = 0;
            } else if (reqFirstRow + reqRows > totalRows) {
                this.reqRows = totalRows - reqFirstRow;
            } else {
                this.reqRows = reqRows;
            }
        }

        /**
         * Run.
         */
        @Override
        public void run() {
            if (client.hasActiveRequest() || navKeyDown) {
                // if client connection is busy, don't bother loading it more
                VConsole.log("Postponed rowfetch");
                schedule(250);
            } else if (!updatedReqRows && allRenderedRowsAreNew()) {

                /*
                 * If all rows are new, there might have been a server-side call
                 * to Table.setCurrentPageFirstItemIndex(int) In this case,
                 * scrolling event takes way too late, and all the rows from
                 * previous viewport to this one were requested.
                 * 
                 * This should prevent requesting unneeded rows by updating
                 * reqFirstRow and reqRows before needing them. See (#14135)
                 */
                setReqFirstRow((firstRowInViewPort - (int) (pageLength * cache_rate)));
                int last = firstRowInViewPort + (int) (cache_rate * pageLength)
                        + pageLength - 1;
                if (last >= totalRows) {
                    last = totalRows - 1;
                }
                setReqRows(last - getReqFirstRow() + 1);
                updatedReqRows = true;
                schedule(250);
            } else {

                int firstRendered = scrollBody.getFirstRendered();
                int lastRendered = scrollBody.getLastRendered();
                if (lastRendered > totalRows) {
                    lastRendered = totalRows - 1;
                }
                boolean rendered = firstRendered >= 0 && lastRendered >= 0;

                int firstToBeRendered = firstRendered;

                if (reqFirstRow < firstToBeRendered) {
                    firstToBeRendered = reqFirstRow;
                } else if (firstRowInViewPort - (int) (cache_rate * pageLength) > firstToBeRendered) {
                    firstToBeRendered = firstRowInViewPort
                            - (int) (cache_rate * pageLength);
                    if (firstToBeRendered < 0) {
                        firstToBeRendered = 0;
                    }
                } else if (rendered && firstRendered + 1 < reqFirstRow
                        && lastRendered + 1 < reqFirstRow) {
                    // requested rows must fall within the requested rendering
                    // area
                    firstToBeRendered = reqFirstRow;
                }
                if (firstToBeRendered + reqRows < firstRendered) {
                    // must increase the required row count accordingly,
                    // otherwise may leave a gap and the rows beyond will get
                    // removed
                    setReqRows(firstRendered - firstToBeRendered);
                }

                int lastToBeRendered = lastRendered;
                int lastReqRow = reqFirstRow + reqRows - 1;

                if (lastReqRow > lastToBeRendered) {
                    lastToBeRendered = lastReqRow;
                } else if (firstRowInViewPort + pageLength + pageLength
                        * cache_rate < lastToBeRendered) {
                    lastToBeRendered = (firstRowInViewPort + pageLength + (int) (pageLength * cache_rate));
                    if (lastToBeRendered >= totalRows) {
                        lastToBeRendered = totalRows - 1;
                    }
                    // due Safari 3.1 bug (see #2607), verify reqrows, original
                    // problem unknown, but this should catch the issue
                    if (lastReqRow > lastToBeRendered) {
                        setReqRows(lastToBeRendered - reqFirstRow);
                    }
                } else if (rendered && lastRendered - 1 > lastReqRow
                        && firstRendered - 1 > lastReqRow) {
                    // requested rows must fall within the requested rendering
                    // area
                    lastToBeRendered = lastReqRow;
                }

                if (lastToBeRendered > totalRows) {
                    lastToBeRendered = totalRows - 1;
                }
                if (reqFirstRow < firstToBeRendered
                        || (reqFirstRow > firstToBeRendered && (reqFirstRow < firstRendered || reqFirstRow > lastRendered + 1))) {
                    setReqFirstRow(firstToBeRendered);
                }
                if (lastRendered < lastToBeRendered
                        && lastRendered + reqRows < lastToBeRendered) {
                    // must increase the required row count accordingly,
                    // otherwise may leave a gap and the rows after will get
                    // removed
                    setReqRows(lastToBeRendered - lastRendered);
                } else if (lastToBeRendered >= firstRendered
                        && reqFirstRow + reqRows < firstRendered) {
                    setReqRows(lastToBeRendered - lastRendered);
                }

                client.updateVariable(paintableId, "firstToBeRendered",
                        firstToBeRendered, false);
                client.updateVariable(paintableId, "lastToBeRendered",
                        lastToBeRendered, false);

                // don't request server to update page first index in case it
                // has not been changed
                if (firstRowInViewPort != firstvisible) {
                    // remember which firstvisible we requested, in case the
                    // server has a differing opinion
                    lastRequestedFirstvisible = firstRowInViewPort;
                    client.updateVariable(paintableId, "firstvisible",
                            firstRowInViewPort, false);
                }
                client.updateVariable(paintableId, "reqfirstrow", reqFirstRow,
                        false);
                client.updateVariable(paintableId, "reqrows", reqRows, true);

                if (selectionChanged) {
                    unSyncedselectionsBeforeRowFetch = new HashSet<Object>(
                            selectedRowKeys);
                }
                isRequestHandlerRunning = false;
            }
        }

        /**
         * Sends request to refresh content at this position.
         */
        public void refreshContent() {
            isRequestHandlerRunning = true;
            int first = (int) (firstRowInViewPort - pageLength * cache_rate);
            int reqRows = (int) (2 * pageLength * cache_rate + pageLength);
            if (first < 0) {
                reqRows = reqRows + first;
                first = 0;
            }
            setReqFirstRow(first);
            setReqRows(reqRows);
            run();
        }
    }

    /**
     * The Class HeaderCell.
     */
    public class HeaderCell extends Widget {

        /**
         * The td.
         */
        Element td = DOM.createTD();
        /**
         * The caption container.
         */
        Element captionContainer = DOM.createDiv();
        /**
         * The sort indicator.
         */
        Element sortIndicator = DOM.createDiv();
        /**
         * The col resize widget.
         */
        Element colResizeWidget = DOM.createDiv();
        /**
         * The floating copy of header cell.
         */
        Element floatingCopyOfHeaderCell;
        /**
         * The icon.
         */
        Element icon = DOM.createImg();
        /**
         * The caption.
         */
        Element caption = DOM.createSpan();
        /**
         * The header check container.
         */
        Element headerCheckContainer = DOM.createSpan();
        /**
         * The check box container.
         */
        Element checkBoxContainer = DOM.createSpan();
        /**
         * The radio container.
         */
        Element radioContainer = DOM.createSpan();
        /**
         * The check box label.
         */
        Element checkBoxLabel = DOM.createLabel();
        /**
         * The radio label.
         */
        Element radioLabel = DOM.createLabel();
        /**
         * The check box.
         */
        Element checkBox = DOM.createInputCheck();
        /**
         * The radio button.
         */
        Element radioButton = DOM.createInputRadio("");
        /**
         * The eicon.
         */
        Element eicon = DOM.createDiv();
        /**
         * The sortable.
         */
        private boolean sortable = false;
        /**
         * The cid.
         */
        private final String cid;
        /**
         * The dragging.
         */
        private boolean dragging;
        /**
         * The drag start x.
         */
        private int dragStartX;
        /**
         * The col index.
         */
        private int colIndex;
        /**
         * The original width.
         */
        private int originalWidth;
        /**
         * The doubleoriginal width.
         */
        private int doubleoriginalWidth;
        /**
         * The double col key.
         */
        private String doubleColKey;
        /**
         * The tripleoriginal width.
         */
        private int tripleoriginalWidth;
        /**
         * The triple col key.
         */
        private String tripleColKey;
        /**
         * The is resizing.
         */
        private boolean isResizing;
        /**
         * The header x.
         */
        private int headerX;
        /**
         * The moved.
         */
        private boolean moved;
        /**
         * The closest slot.
         */
        private int closestSlot;
        /**
         * The width.
         */
        private int width = -1;
        /**
         * The natural width.
         */
        private int naturalWidth = -1;
        /**
         * The align.
         */
        private char align = ALIGN_LEFT;
        /**
         * The defined width.
         */
        boolean definedWidth = false;
        /**
         * The expand ratio.
         */
        private float expandRatio = 0;
        /**
         * The sorted.
         */
        private boolean sorted;
        /**
         * The checked.
         */
        private boolean checked;
        /**
         * The disabled.
         */
        private boolean disabled;
        /**
         * The ischeckbox.
         */
        private boolean ischeckbox;
        /**
         * The radiochecked.
         */
        private boolean radiochecked;
        /**
         * The radiodisabled.
         */
        private boolean radiodisabled;
        /**
         * The isradio.
         */
        private boolean isradio;
        /**
         * The iseicon.
         */
        private boolean iseicon;
        /**
         * The expanded.
         */
        private boolean expanded;

        /**
         * Sets the sortable.
         *
         * @param b the new sortable
         */
        public void setSortable(boolean b) {
            sortable = b;
        }

        /**
         * Sets the checked.
         *
         * @param c the new checked
         */
        public void setChecked(boolean c) {
            checked = c;
            checkBox.setPropertyBoolean("checked", checked);
        }

        /**
         * Checks if is checked.
         *
         * @return true, if is checked
         */
        public boolean isChecked() {
            return checked;
        }

        /**
         * Sets the disabled.
         *
         * @param d the new disabled
         */
        public void setDisabled(boolean d) {
            disabled = d;
            checkBox.setPropertyBoolean("disabled", disabled);
        }

        /**
         * Checks if is disabled.
         *
         * @return true, if is disabled
         */
        public boolean isDisabled() {
            return disabled;
        }

        /**
         * Sets the radio checked.
         *
         * @param c the new radio checked
         */
        public void setRadioChecked(boolean c) {
            radiochecked = c;
            radioButton.setPropertyBoolean("checked", radiochecked);
        }

        /**
         * Checks if is radio checked.
         *
         * @return true, if is radio checked
         */
        public boolean isRadioChecked() {
            return radiochecked;
        }

        /**
         * Sets the radio disabled.
         *
         * @param d the new radio disabled
         */
        public void setRadioDisabled(boolean d) {
            radiodisabled = d;
            radioButton.setPropertyBoolean("disabled", radiodisabled);
        }

        /**
         * Checks if is radio disabled.
         *
         * @return true, if is radio disabled
         */
        public boolean isRadioDisabled() {
            return radiodisabled;
        }

        /**
         * Sets the expand icon.
         *
         * @param b the new expand icon
         */
        public void setExpandIcon(boolean b) {
            iseicon = b;
        }

        /**
         * Checks if is expand icon.
         *
         * @return true, if is expand icon
         */
        public boolean isExpandIcon() {
            return iseicon;
        }

        /**
         * Sets the expand.
         *
         * @param b the new expand
         */
        public void setExpand(boolean b) {
            expanded = b;
            if (expanded) {
                removeStyleName("eicon-collapsed");
                addStyleName("eicon-expanded");
            } else {
                removeStyleName("eicon-expanded");
                addStyleName("eicon-collapsed");
            }
        }

        /**
         * Checks if is expand.
         *
         * @return true, if is expand
         */
        public boolean isExpand() {
            return expanded;
        }

        /**
         * Makes room for the sorting indicator in case the column that the
         * header cell belongs to is sorted. This is done by resizing the width
         * of the caption container element by the correct amount
         *
         * @param rightSpacing the right spacing
         */
        public void resizeCaptionContainer(int rightSpacing) {
            int captionContainerWidth = width
                    - colResizeWidget.getOffsetWidth() - rightSpacing;

            if (td.getClassName().contains("-asc")
                    || td.getClassName().contains("-desc")) {
                // Leave room for the sort indicator
                captionContainerWidth -= sortIndicator.getOffsetWidth();
            }
            if (td.getClassName().contains("eicon-expanded")
                    || td.getClassName().contains("eicon-collapsed")) {
                // Leave room for the sort indicator
                captionContainerWidth -= eicon.getOffsetWidth();
            }
            if (captionContainerWidth < 0) {
                rightSpacing += captionContainerWidth;
                captionContainerWidth = 0;
            }

            captionContainer.getStyle().setPropertyPx("width",
                    captionContainerWidth);

            // Apply/Remove spacing if defined
            if (rightSpacing > 0) {
                colResizeWidget.getStyle().setMarginLeft(rightSpacing, Unit.PX);
            } else {
                colResizeWidget.getStyle().clearMarginLeft();
            }
        }

        /**
         * Sets the natural minimum column width.
         *
         * @param w the new natural minimum column width
         */
        public void setNaturalMinimumColumnWidth(int w) {
            naturalWidth = w;
        }

        /**
         * Gets the natural minimum column width.
         *
         * @return the natural minimum column width
         */
        public int getNaturalMinimumColumnWidth() {
            return naturalWidth;
        }

        /**
         * Instantiates a new header cell.
         *
         * @param colId the col id
         * @param col the col
         */
        public HeaderCell(String colId, UIDL col) {
            cid = colId;

//            setText(headerText);
            td.appendChild(colResizeWidget);
            // ensure no clipping initially (problem on column additions)
            captionContainer.getStyle().setOverflow(Overflow.VISIBLE);
            if (col == null) {
                captionContainer.appendChild(caption);
            } else {
                setCheckCaption(col);
            }
            td.appendChild(sortIndicator);
            td.appendChild(eicon);
            td.appendChild(captionContainer);

            DOM.sinkEvents(checkBoxContainer, Event.ONCLICK);
            DOM.sinkEvents(radioContainer, Event.ONCLICK);
            DOM.sinkEvents(checkBox, Event.ONCHANGE);
            DOM.sinkEvents(radioButton, Event.ONCHANGE);
            DOM.sinkEvents(eicon, Event.ONCLICK);
            DOM.sinkEvents(td, Event.MOUSEEVENTS | Event.ONDBLCLICK
                    | Event.ONCONTEXTMENU | Event.TOUCHEVENTS);

            setElement(td);

            setAlign(ALIGN_LEFT);
        }

        /**
         * Update style names.
         *
         * @param primaryStyleName the primary style name
         */
        protected void updateStyleNames(String primaryStyleName) {
            colResizeWidget.setClassName(primaryStyleName + "-resizer");
            sortIndicator.setClassName(primaryStyleName + "-sort-indicator");
            captionContainer.setClassName(primaryStyleName
                    + "-caption-container");
            if (sorted) {
                if (sortAscending) {
                    setStyleName(primaryStyleName + "-header-cell-asc");
                } else {
                    setStyleName(primaryStyleName + "-header-cell-desc");
                }
            } else {
                setStyleName(primaryStyleName + "-header-cell");
            }
            headerCheckContainer.setClassName(primaryStyleName + "-checkbox-radiobutton-container");
            checkBoxContainer.setClassName(primaryStyleName + "-checkbox-container");
            checkBoxContainer.addClassName("v-checkbox");
            if (disabled) {
                checkBoxContainer.addClassName("v-disabled v-checkbox-disabled");
            } else {
                checkBoxContainer.removeClassName("v-disabled v-checkbox-disabled");
            }
            radioContainer.setClassName(primaryStyleName + "-radiobutton-container");
            radioContainer.addClassName("v-radiobutton");
            if (radiodisabled) {
                radioContainer.addClassName("v-radiobutton-disabled v-disabled");
            } else {
                radioContainer.removeClassName("v-radiobutton-disabled v-disabled");
            }
            caption.setClassName(primaryStyleName + "-captionspans-container");
            if (ischeckbox) {
                addStyleName("hascheckbox");
            }
            if (isradio) {
                addStyleName("hasradio");
            }
            eicon.setClassName(primaryStyleName + "-eicon");
            if (isExpandIcon()) {
                setExpand(isExpand());
            }
            final String ALIGN_PREFIX = primaryStyleName
                    + "-caption-container-align-";

            switch (align) {
                case ALIGN_CENTER:
                    captionContainer.addClassName(ALIGN_PREFIX + "center");
                    break;
                case ALIGN_RIGHT:
                    captionContainer.addClassName(ALIGN_PREFIX + "right");
                    break;
                default:
                    captionContainer.addClassName(ALIGN_PREFIX + "left");
                    break;
            }

        }

        /**
         * Disable auto width calculation.
         */
        public void disableAutoWidthCalculation() {
            definedWidth = true;
            expandRatio = 0;
        }

        /**
         * Sets width to the header cell. This width should not include any
         * possible indent modifications that are present in
         * {@link VScrollTableBody#getMaxIndent()}.
         *
         * @param w required width of the cell sans indentations
         * @param ensureDefinedWidth disables expand ratio if required
         */
        public void setWidth(int w, boolean ensureDefinedWidth) {
            if (ensureDefinedWidth) {
                definedWidth = true;
                // on column resize expand ratio becomes zero
                expandRatio = 0;
            }
            if (width == -1) {
                // go to default mode, clip content if necessary
                captionContainer.getStyle().clearOverflow();
            }
            width = w;
            if (w == -1) {
                captionContainer.getStyle().clearWidth();
                setWidth("");
            } else {
                tHead.resizeCaptionContainer(this);

                /*
                 * if we already have tBody, set the header width properly, if
                 * not defer it. IE will fail with complex float in table header
                 * unless TD width is not explicitly set.
                 */
                if (scrollBody != null) {
                    int maxIndent = scrollBody.getMaxIndent();
                    if (w < maxIndent && isHierarchyColumn()) {
                        w = maxIndent;
                    }
                    int tdWidth = w + scrollBody.getCellExtraWidth();
                    setWidth(tdWidth + "px");
                } else {
                    Scheduler.get().scheduleDeferred(new Command() {
                        @Override
                        public void execute() {
                            int maxIndent = scrollBody.getMaxIndent();
                            int tdWidth = width;
                            if (tdWidth < maxIndent && isHierarchyColumn()) {
                                tdWidth = maxIndent;
                            }
                            tdWidth += scrollBody.getCellExtraWidth();
                            setWidth(tdWidth + "px");
                        }
                    });
                }
            }
        }

        /**
         * Sets the undefined width.
         */
        public void setUndefinedWidth() {
            definedWidth = false;
            if (!isResizing) {
                setWidth(-1, false);
            }
        }

        /**
         * Detects if width is fixed by developer on server side or resized to
         * current width by user.
         *
         * @return true if defined, false if "natural" width
         */
        public boolean isDefinedWidth() {
            return definedWidth && width >= 0;
        }

        /**
         * This method exists for the needs of {@link VTreeTable} only.
         *
         * Returns the pixels width of the header cell. This includes the
         * indent, if applicable.
         *
         * @return The width in pixels
         */
        public int getWidthWithIndent() {
            if (scrollBody != null && isHierarchyColumn()) {
                int maxIndent = scrollBody.getMaxIndent();
                if (maxIndent > width) {
                    return maxIndent;
                }
            }
            return width;
        }

        /**
         * Returns the pixels width of the header cell.
         *
         * @return The width in pixels
         */
        public int getWidth() {
            return width;
        }

        /**
         * This method exists for the needs of {@link VTreeTable} only.
         *
         * @return <code>true</code> if this is hierarcyColumn's header cell,
         * <code>false</code> otherwise
         */
        private boolean isHierarchyColumn() {
            int hierarchyColumnIndex = getHierarchyColumnIndex();
            return hierarchyColumnIndex >= 0
                    && tHead.visibleCells.indexOf(this) == hierarchyColumnIndex;
        }

        /**
         * Sets the text.
         *
         * @param headerText the new text
         */
        public void setText(String headerText) {
            DOM.setInnerHTML(captionContainer, headerText);
        }

        /**
         * Gets the col key.
         *
         * @return the col key
         */
        public String getColKey() {
            return cid;
        }

        /**
         * Sets the sorted.
         *
         * @param sorted the new sorted
         */
        private void setSorted(boolean sorted) {
            this.sorted = sorted;
            updateStyleNames(VExtCustomScrollTable.this.getStylePrimaryName());
        }

        /**
         * Sets the check caption.
         *
         * @param col the new check caption
         */
        public void setCheckCaption(UIDL col) {
            icon.removeFromParent();
            headerCheckContainer.removeFromParent();
            checkBoxContainer.removeFromParent();
            radioContainer.removeFromParent();
            caption.removeFromParent();
            if (col.hasAttribute("icon")) {
                icon.setClassName("v-icon");
                icon.setPropertyString("alt", "icon");
                icon.setPropertyString("src", Util.escapeAttribute(client.translateVaadinUri(col.getStringAttribute("icon"))));
                captionContainer.appendChild(icon);
            }
            if (col.hasAttribute("checkbox") || col.hasAttribute("radio")) {
                if (col.hasAttribute("checkbox")) {
                    checkBox.removeFromParent();
                    checkBoxLabel.removeFromParent();
                    ischeckbox = true;
                    td.addClassName("hascheckbox");

                    checkBox.setClassName("v-header-checkbox");
                    setChecked(col.getBooleanAttribute("checkbox"));
                    setDisabled(col.getBooleanAttribute("checkboxdisabled"));
                    checkBoxContainer.appendChild(checkBox);
                    checkBoxContainer.appendChild(checkBoxLabel);
                    if (checked) {
                        checkBox.addClassName("selected");
                    }
                    headerCheckContainer.appendChild(checkBoxContainer);
                }
                if (col.hasAttribute("radio")) {
                    radioButton.removeFromParent();
                    radioLabel.removeFromParent();
                    isradio = true;
                    td.addClassName("hasradio");
                    radioButton.setPropertyString("name", col.getStringAttribute("radio"));
                    radioButton.setClassName("v-header-radio");
                    setRadioChecked(col.getBooleanAttribute("radiochecked"));
                    setRadioDisabled(col.getBooleanAttribute("radiodisabled"));
                    radioContainer.appendChild(radioButton);
                    radioContainer.appendChild(radioLabel);
                    if (radiochecked) {
                        radioButton.addClassName("selected");
                    }
                    headerCheckContainer.appendChild(radioContainer);
                }
                captionContainer.appendChild(headerCheckContainer);
            }
            String s = col.hasAttribute("caption") ? col
                    .getStringAttribute("caption") : "";
            caption.setInnerHTML(s);
            captionContainer.appendChild(caption);
        }

        /**
         * Handle column reordering.
         *
         * @param event the event
         */
        @Override
        public void onBrowserEvent(Event event) {
            if (enabled && event != null) {
                Element targetElement = DOM.eventGetTarget(event);
                if (DOM.isOrHasChild(checkBoxContainer, targetElement) && event.getEventTarget().cast() != checkBox) {
                    if (event.getTypeInt() == Event.ONCLICK) {
                        if (!disabled) {
                            checkBox.setPropertyBoolean("checked", !checkBox.getPropertyBoolean("checked"));
                            onCheckEvent();
                        }
                    }
                } else if (DOM.isOrHasChild(radioContainer, targetElement) && event.getEventTarget().cast() != radioButton) {
                    if (event.getTypeInt() == Event.ONCLICK) {
                        if (!radiodisabled) {
                            if (!radioButton.getPropertyBoolean("checked")) {
                                radioButton.setPropertyBoolean("checked", true);
                            }
                            onRadioCheckEvent();
                        }
                    }

                } else if (checkBox != null && event.getEventTarget().cast() == checkBox) {
                    if (event.getTypeInt() == Event.ONCHANGE) {
                        onCheckEvent();
                    }
                } else if (radioButton != null && event.getEventTarget().cast() == radioButton) {
                    if (event.getTypeInt() == Event.ONCHANGE) {
                        onRadioCheckEvent();
                    }

                } else if (eicon != null && event.getEventTarget().cast() == eicon) {
                    onEIconEvent(event);

                } else if (isResizing
                        || event.getEventTarget().cast() == colResizeWidget) {
                    if (dragging
                            && (event.getTypeInt() == Event.ONMOUSEUP || event
                            .getTypeInt() == Event.ONTOUCHEND)) {
                        // Handle releasing column header on spacer #5318
                        handleCaptionEvent(event);
                    } else {
                        onResizeEvent(event);
                    }
                } else {
                    /*
                     * Ensure focus before handling caption event. Otherwise
                     * variables changed from caption event may be before
                     * variables from other components that fire variables when
                     * they lose focus.
                     */
                    if (event.getTypeInt() == Event.ONMOUSEDOWN
                            || event.getTypeInt() == Event.ONTOUCHSTART) {
                        scrollBodyPanel.setFocus(true);
                    }
                    handleCaptionEvent(event);
                    boolean stopPropagation = true;
                    if (event.getTypeInt() == Event.ONCONTEXTMENU
                            && !client.hasEventListeners(
                            VExtCustomScrollTable.this,
                            TableConstants.HEADER_CLICK_EVENT_ID)) {
                        // Prevent showing the browser's context menu only when
                        // there is a header click listener.
                        stopPropagation = false;
                    }
                    if (stopPropagation) {
                        event.stopPropagation();
                        event.preventDefault();
                    }
                }

            }
        }

        /**
         * Creates the floating copy.
         */
        private void createFloatingCopy() {
            floatingCopyOfHeaderCell = DOM.createDiv();
            DOM.setInnerHTML(floatingCopyOfHeaderCell, DOM.getInnerHTML(td));
            floatingCopyOfHeaderCell = DOM
                    .getChild(floatingCopyOfHeaderCell, 2);
            // #12714 the shown "ghost element" should be inside
            // v-overlay-container, and it should contain the same styles as the
            // table to enable theming (except v-table & v-widget).
            String stylePrimaryName = VExtCustomScrollTable.this
                    .getStylePrimaryName();
            StringBuilder sb = new StringBuilder();
            for (String s : VExtCustomScrollTable.this.getStyleName().split(" ")) {
                if (!s.equals(StyleConstants.UI_WIDGET)) {
                    sb.append(s);
                    if (s.equals(stylePrimaryName)) {
                        sb.append("-header-drag ");
                    } else {
                        sb.append(" ");
                    }
                }
            }
            floatingCopyOfHeaderCell.setClassName(sb.toString().trim());
            // otherwise might wrap or be cut if narrow column
            floatingCopyOfHeaderCell.getStyle().setProperty("width", "auto");
            updateFloatingCopysPosition(DOM.getAbsoluteLeft(td),
                    DOM.getAbsoluteTop(td));
            DOM.appendChild(VOverlay.getOverlayContainer(client),
                    floatingCopyOfHeaderCell);
        }

        /**
         * Update floating copys position.
         *
         * @param x the x
         * @param y the y
         */
        private void updateFloatingCopysPosition(int x, int y) {
            x -= DOM.getElementPropertyInt(floatingCopyOfHeaderCell,
                    "offsetWidth") / 2;
            floatingCopyOfHeaderCell.getStyle().setLeft(x, Unit.PX);
            if (y > 0) {
                floatingCopyOfHeaderCell.getStyle().setTop(y + 7, Unit.PX);
            }
        }

        /**
         * Hide floating copy.
         */
        private void hideFloatingCopy() {
            floatingCopyOfHeaderCell.removeFromParent();
            floatingCopyOfHeaderCell = null;
        }

        /**
         * Fires a header click event after the user has clicked a column header
         * cell.
         *
         * @param event The click event
         */
        private void fireHeaderClickedEvent(Event event) {
            if (client.hasEventListeners(VExtCustomScrollTable.this,
                    TableConstants.HEADER_CLICK_EVENT_ID)) {
                MouseEventDetails details = MouseEventDetailsBuilder
                        .buildMouseEventDetails(event);
                client.updateVariable(paintableId, "headerClickEvent",
                        details.toString(), false);
                client.updateVariable(paintableId, "headerClickCID", cid, true);
            }
        }

        /**
         * On check event.
         */
        protected void onCheckEvent() {
            checked = checkBox.getPropertyBoolean("checked");
            fireColumnCheckEvent(cid, checked);
            sendCheckBoxUpdates();

            if (checked) {
                checkBox.addClassName("selected");
            } else {
                checkBox.removeClassName("selected");
            }
        }

        /**
         * On radio check event.
         */
        protected void onRadioCheckEvent() {
            String rkey = getSingleRadioKey(cid);
            String prevValue = getSingleRadioVal(rkey);
            String currentHeader = SINGLE_HEADER;
            String prvHeader = SINGLE_HEADER;
            if (prevValue == null) {
                prevValue = getDoubleRadioVal(rkey);
                if (prevValue != null) {
                    prvHeader = DOUBLE_HEADER;
                }
            }
            if (prevValue == null) {
                prevValue = getTripleRadioVal(rkey);
                if (prevValue != null) {
                    prvHeader = TRIPLE_HEADER;
                }
            }
            if (radioButton.getPropertyBoolean("checked")) {
                setSingleRadioVal(rkey, getColKey());
            }

            fireColumnRadioEvent(rkey, prvHeader, currentHeader, prevValue, getSingleRadioVal(rkey));
            sendRadioUpdates();

            if (radiochecked) {
                radioButton.addClassName("selected");
            } else {
                radioButton.removeClassName("selected");
            }
        }

        /**
         * On e icon event.
         *
         * @param event the event
         */
        protected void onEIconEvent(Event event) {
            if (event.getTypeInt() == Event.ONCLICK) {
                setExpand(!isExpand());
                sendExpandIconUpdates(false);
                fireColumnExpandIconEvent(cid, isExpand());
            }
        }

        /**
         * Handle caption event.
         *
         * @param event the event
         */
        protected void handleCaptionEvent(Event event) {
            switch (DOM.eventGetType(event)) {
                case Event.ONTOUCHSTART:
                case Event.ONMOUSEDOWN:
                    if (columnReordering
                            && Util.isTouchEventOrLeftMouseButton(event)) {
                        if (event.getTypeInt() == Event.ONTOUCHSTART) {
                            /*
                             * prevent using this event in e.g. scrolling
                             */
                            event.stopPropagation();
                        }
                        dragging = true;
                        moved = false;
                        colIndex = getColIndexByKey(cid);
                        DOM.setCapture(getElement());
                        headerX = tHead.getAbsoluteLeft();
                        event.preventDefault(); // prevent selecting text &&
                        // generated touch events
                    }
                    break;
                case Event.ONMOUSEUP:
                case Event.ONTOUCHEND:
                case Event.ONTOUCHCANCEL:
                    if (columnReordering
                            && Util.isTouchEventOrLeftMouseButton(event)) {
                        dragging = false;
                        DOM.releaseCapture(getElement());
                        if (moved) {
                            hideFloatingCopy();
                            tHead.removeSlotFocus();
                            if (closestSlot != colIndex
                                    && closestSlot != (colIndex + 1)) {
                                if (closestSlot > colIndex) {
                                    reOrderColumn(cid, closestSlot - 1);
                                } else {
                                    reOrderColumn(cid, closestSlot);
                                }
                            }
                        }
                        if (Util.isTouchEvent(event)) {
                            /*
                             * Prevent using in e.g. scrolling and prevent generated
                             * events.
                             */
                            event.preventDefault();
                            event.stopPropagation();
                        }
                    }

                    if (!moved) {
                        // mouse event was a click to header -> sort column
                        if (sortable && Util.isTouchEventOrLeftMouseButton(event)) {
                            if (sortColumn.equals(cid)) {
                                // just toggle order
                                client.updateVariable(paintableId, "sortascending",
                                        !sortAscending, false);
                            } else {
                                // set table sorted by this column
                                client.updateVariable(paintableId, "sortcolumn",
                                        cid, false);
                            }
                            // get also cache columns at the same request
                            scrollBodyPanel.setScrollPosition(0);
                            firstvisible = 0;
                            rowRequestHandler.setReqFirstRow(0);
                            rowRequestHandler.setReqRows((int) (2 * pageLength
                                    * cache_rate + pageLength));
                            rowRequestHandler.deferRowFetch(); // some validation +
                            // defer 250ms
                            rowRequestHandler.cancel(); // instead of waiting
                            rowRequestHandler.run(); // run immediately
                        }
                        fireHeaderClickedEvent(event);
                        if (Util.isTouchEvent(event)) {
                            /*
                             * Prevent using in e.g. scrolling and prevent generated
                             * events.
                             */
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        break;
                    }
                    break;
                case Event.ONDBLCLICK:
                    fireHeaderClickedEvent(event);
                    break;
                case Event.ONTOUCHMOVE:
                case Event.ONMOUSEMOVE:
                    if (dragging && Util.isTouchEventOrLeftMouseButton(event)) {
                        if (event.getTypeInt() == Event.ONTOUCHMOVE) {
                            /*
                             * prevent using this event in e.g. scrolling
                             */
                            event.stopPropagation();
                        }
                        if (!moved) {
                            createFloatingCopy();
                            moved = true;
                        }

                        final int clientX = Util.getTouchOrMouseClientX(event);
                        final int x = clientX + tHead.hTableWrapper.getScrollLeft();
                        int slotX = headerX;
                        closestSlot = colIndex;
                        int closestDistance = -1;
                        int start = 0;
                        if (showRowHeaders) {
                            start++;
                        }
                        final int visibleCellCount = tHead.getVisibleCellCount();
                        for (int i = start; i <= visibleCellCount; i++) {
                            if (i > 0) {
                                final String colKey = getColKeyByIndex(i - 1);
                                // getColWidth only returns the internal width
                                // without padding, not the offset width of the
                                // whole td (#10890)
                                slotX += getColWidth(colKey)
                                        + scrollBody.getCellExtraWidth();
                            }
                            final int dist = Math.abs(x - slotX);
                            if (closestDistance == -1 || dist < closestDistance) {
                                closestDistance = dist;
                                closestSlot = i;
                            }
                        }
                        tHead.focusSlot(closestSlot);

                        updateFloatingCopysPosition(clientX, -1);
                    }
                    break;
                default:
                    break;
            }
        }

        /**
         * On resize event.
         *
         * @param event the event
         */
        private void onResizeEvent(Event event) {
            switch (DOM.eventGetType(event)) {
                case Event.ONMOUSEDOWN:
                    if (!Util.isTouchEventOrLeftMouseButton(event)) {
                        return;
                    }
                    isResizing = true;
                    DOM.setCapture(getElement());
                    dragStartX = DOM.eventGetClientX(event);
                    colIndex = getColIndexByKey(cid);
                    originalWidth = getWidthWithIndent();
                    if (showDoubleColHeaders) {
                        doubleColKey = findDoubleHeaderMappedKey(cid);
                        doubleoriginalWidth = getDoubleHeaderColWidth(doubleColKey);
                        if (showTripleColHeaders) {
                            tripleColKey = findTripleHeaderMappedKey(doubleColKey);
                            tripleoriginalWidth = getTripleHeaderColWidth(tripleColKey);
                        }
                    }
                    DOM.eventPreventDefault(event);
                    break;
                case Event.ONMOUSEUP:
                    if (!Util.isTouchEventOrLeftMouseButton(event)) {
                        return;
                    }
                    isResizing = false;
                    DOM.releaseCapture(getElement());   
                    int newMinWidth = getMinWidth(true, true)+width;
                        int offW=getOffsetWidth();
                        if(offW>newMinWidth){
                            setColWidth(colIndex, offW-newMinWidth+width, true);
                    if (showDoubleColHeaders) {
                            int newDoubleWidth = doubleoriginalWidth + (width - originalWidth);
                        setDoubleHeaderColWidth(getDoubleHeaderColIndexByKey(doubleColKey), newDoubleWidth, true, false);
                        if (showTripleColHeaders) {
                                int newTripleWidth = tripleoriginalWidth + (width - originalWidth);
                            setTripleHeaderColWidth(getTripleHeaderColIndexByKey(tripleColKey), newTripleWidth, true, false);
                        }
                        }
                        } 

                    tHead.disableAutoColumnWidthCalculation(this);
                    if (showDoubleColHeaders) {
                        tdHead.disableAutoColumnWidthCalculation();
                        if (showTripleColHeaders) {
                            ttHead.disableAutoColumnWidthCalculation();
                        }
                    }
                    // Ensure last header cell is taking into account possible
                    // column selector
                    HeaderCell lastCell = tHead.getHeaderCell(tHead
                            .getVisibleCellCount() - 1);
                    tHead.resizeCaptionContainer(lastCell);
                    triggerLazyColumnAdjustment(true);
                    fireColumnResizeEvent(cid, originalWidth, getColWidth(cid));
                    break;
                case Event.ONMOUSEMOVE:
                    if (!Util.isTouchEventOrLeftMouseButton(event)) {
                        return;
                    }
                    if (isResizing) {
                        int deltaX = DOM.eventGetClientX(event) - dragStartX;
                        if (deltaX == 0) {
                            return;
                        }
                        tHead.disableAutoColumnWidthCalculation(this);

                        int newWidth = originalWidth + deltaX;
                        // get min width with indent, no padding
                        int minWidth = getMinWidth(true, false);
                        if (newWidth < minWidth) {
                            // already includes indent if any
                            newWidth = minWidth;
                        }
                        setColWidth(colIndex, newWidth, true);
                        int new1MinWidth = getMinWidth(true, true)+width;
                        int offW1=getOffsetWidth();
                        if(offW1>new1MinWidth){
                            newWidth=offW1-new1MinWidth+width;
                            setColWidth(colIndex, newWidth, true);
                        }                        
                        if (showDoubleColHeaders) {
                            int newDoubleWidth = doubleoriginalWidth + (width - originalWidth);
                            setDoubleHeaderColWidth(getDoubleHeaderColIndexByKey(doubleColKey), newDoubleWidth, true, false);
                            tdHead.disableAutoColumnWidthCalculation();
                            if (showTripleColHeaders) {
                                int newTripleWidth = tripleoriginalWidth + (width - originalWidth);
                                setTripleHeaderColWidth(getTripleHeaderColIndexByKey(tripleColKey), newTripleWidth, true, false);
                                ttHead.disableAutoColumnWidthCalculation();
                            }
                        }
                        triggerLazyColumnAdjustment(false);
                        forceRealignColumnHeaders();

                    }
                    break;
                default:
                    break;
            }
        }

        /**
         * Returns the smallest possible cell width in pixels.
         *
         * @param includeIndent - width should include hierarchy column indent
         * if applicable (VTreeTable only)
         * @param includeCellExtraWidth - width should include paddings etc.
         * @return the min width
         */
        private int getMinWidth(boolean includeIndent,
                boolean includeCellExtraWidth) {
            int minWidth = sortIndicator.getOffsetWidth();
            if (scrollBody != null) {
                // check the need for indent before adding paddings etc.
                if (includeIndent && isHierarchyColumn()) {
                    int maxIndent = scrollBody.getMaxIndent();
                    if (minWidth < maxIndent) {
                        minWidth = maxIndent;
                    }
                }
                if (includeCellExtraWidth) {
                    minWidth += scrollBody.getCellExtraWidth();
                }
            }
            return minWidth;
        }

        /**
         * Gets the min width.
         *
         * @return the min width
         */
        public int getMinWidth() {
            // get min width with padding, no indent
            return getMinWidth(false, true);
        }

        /**
         * Gets the caption.
         *
         * @return the caption
         */
        public String getCaption() {
            return DOM.getInnerText(captionContainer);
        }

        /**
         * Checks if is enabled.
         *
         * @return true, if is enabled
         */
        public boolean isEnabled() {
            return getParent() != null;
        }

        /**
         * Sets the align.
         *
         * @param c the new align
         */
        public void setAlign(char c) {
            align = c;
            updateStyleNames(VExtCustomScrollTable.this.getStylePrimaryName());
        }

        /**
         * Gets the align.
         *
         * @return the align
         */
        public char getAlign() {
            return align;
        }

        /**
         * Detects the natural minimum width for the column of this header cell.
         * If column is resized by user or the width is defined by server the
         * actual width is returned. Else the natural min width is returned.
         *
         * @param columnIndex column index hint, if -1 (unknown) it will be
         * detected
         * @return the natural column width
         */
        public int getNaturalColumnWidth(int columnIndex) {
            final int iw = columnIndex == getHierarchyColumnIndex() ? scrollBody
                    .getMaxIndent() : 0;
            if (isDefinedWidth()) {
                if (iw > width) {
                    return iw;
                }
                return width;
            } else {
                if (naturalWidth < 0) {
                    // This is recently revealed column. Try to detect a proper
                    // value (greater of header and data columns)

                    int hw = captionContainer.getOffsetWidth()
                            + getHeaderPadding();
                    if (BrowserInfo.get().isGecko()) {
                        hw += sortIndicator.getOffsetWidth();
                    }
                    if (columnIndex < 0) {
                        columnIndex = 0;
                        for (Iterator<Widget> it = tHead.iterator(); it
                                .hasNext(); columnIndex++) {
                            if (it.next() == this) {
                                break;
                            }
                        }
                    }
                    final int cw = scrollBody.getColWidth(columnIndex);
                    naturalWidth = (hw > cw ? hw : cw);
                }
                if (iw > naturalWidth) {
                    // indent is temporary value, naturalWidth shouldn't be
                    // updated
                    return iw;
                } else {
                    return naturalWidth;
                }
            }
        }

        /**
         * Sets the expand ratio.
         *
         * @param floatAttribute the new expand ratio
         */
        public void setExpandRatio(float floatAttribute) {
            if (floatAttribute != expandRatio) {
                triggerLazyColumnAdjustment(false);
            }
            expandRatio = floatAttribute;
        }

        /**
         * Gets the expand ratio.
         *
         * @return the expand ratio
         */
        public float getExpandRatio() {
            return expandRatio;
        }

        /**
         * Checks if is sorted.
         *
         * @return true, if is sorted
         */
        public boolean isSorted() {
            return sorted;
        }
    }

    /**
     * HeaderCell that is header cell for row headers.
     *
     * Reordering disabled and clicking on it resets sorting.
     */
    public class RowHeadersHeaderCell extends HeaderCell {

        /**
         * Instantiates a new row headers header cell.
         */
        RowHeadersHeaderCell() {
            super(ROW_HEADER_COLUMN_KEY, null);
            updateStyleNames(VExtCustomScrollTable.this.getStylePrimaryName());
        }

        /**
         * Update style names.
         *
         * @param primaryStyleName the primary style name
         */
        @Override
        protected void updateStyleNames(String primaryStyleName) {
            super.updateStyleNames(primaryStyleName);
            setStyleName(primaryStyleName + "-header-cell-rowheader");
        }

        /**
         * Handle caption event.
         *
         * @param event the event
         */
        @Override
        protected void handleCaptionEvent(Event event) {
            // NOP: RowHeaders cannot be reordered
            // TODO It'd be nice to reset sorting here
        }
    }

    /**
     * The Class TableHead.
     */
    public class TableHead extends Panel implements ActionOwner {

        /**
         * The Constant WRAPPER_WIDTH.
         */
        private static final int WRAPPER_WIDTH = 900000;
        /**
         * The visible cells.
         */
        ArrayList<Widget> visibleCells = new ArrayList<Widget>();
        /**
         * The available cells.
         */
        HashMap<String, HeaderCell> availableCells = new HashMap<String, HeaderCell>();
        /**
         * The div.
         */
        Element div = DOM.createDiv();
        /**
         * The h table wrapper.
         */
        Element hTableWrapper = DOM.createDiv();
        /**
         * The h table container.
         */
        Element hTableContainer = DOM.createDiv();
        /**
         * The table.
         */
        Element table = DOM.createTable();
        /**
         * The header table body.
         */
        Element headerTableBody = DOM.createTBody();
        /**
         * The tr.
         */
        Element tr = DOM.createTR();
        /**
         * The column selector.
         */
        private final Element columnSelector = DOM.createDiv();
        /**
         * The focused slot.
         */
        private int focusedSlot = -1;

        /**
         * Instantiates a new table head.
         */
        public TableHead() {
            if (BrowserInfo.get().isIE()) {
                table.setPropertyInt("cellSpacing", 0);
            }

            hTableWrapper.getStyle().setOverflow(Overflow.HIDDEN);
            columnSelector.getStyle().setDisplay(Display.NONE);

            DOM.appendChild(table, headerTableBody);
            DOM.appendChild(headerTableBody, tr);
            DOM.appendChild(hTableContainer, table);
            DOM.appendChild(hTableWrapper, hTableContainer);
            DOM.appendChild(div, hTableWrapper);
            DOM.appendChild(div, columnSelector);
            setElement(div);

            DOM.sinkEvents(columnSelector, Event.ONCLICK);

            availableCells.put(ROW_HEADER_COLUMN_KEY,
                    new RowHeadersHeaderCell());
        }

        /**
         * Update style names.
         *
         * @param primaryStyleName the primary style name
         */
        protected void updateStyleNames(String primaryStyleName) {
            hTableWrapper.setClassName(primaryStyleName + "-header");
            columnSelector.setClassName(primaryStyleName + "-column-selector");
            setStyleName(primaryStyleName + "-header-wrap");
            for (HeaderCell c : availableCells.values()) {
                c.updateStyleNames(primaryStyleName);
            }
        }

        /**
         * Resize caption container.
         *
         * @param cell the cell
         */
        public void resizeCaptionContainer(HeaderCell cell) {
            HeaderCell lastcell = getHeaderCell(visibleCells.size() - 1);
            int columnSelectorOffset = columnSelector.getOffsetWidth();

            if (cell == lastcell && columnSelectorOffset > 0
                    && !hasVerticalScrollbar()) {

                // Measure column widths
                int columnTotalWidth = 0;
                for (Widget w : visibleCells) {
                    int cellExtraWidth = w.getOffsetWidth();
                    if (scrollBody != null
                            && visibleCells.indexOf(w) == getHierarchyColumnIndex()
                            && cellExtraWidth < scrollBody.getMaxIndent()) {
                        // indent must be taken into consideration even if it
                        // hasn't been applied yet
                        columnTotalWidth += scrollBody.getMaxIndent();
                    } else {
                        columnTotalWidth += cellExtraWidth;
                    }
                }

                int divOffset = div.getOffsetWidth();
                if (columnTotalWidth >= divOffset - columnSelectorOffset) {
                    /*
                     * Ensure column caption is visible when placed under the
                     * column selector widget by shifting and resizing the
                     * caption.
                     */
                    int offset = 0;
                    int diff = divOffset - columnTotalWidth;
                    if (diff < columnSelectorOffset && diff > 0) {
                        /*
                         * If the difference is less than the column selectors
                         * width then just offset by the difference
                         */
                        offset = columnSelectorOffset - diff;
                    } else {
                        // Else offset by the whole column selector
                        offset = columnSelectorOffset;
                    }
                    lastcell.resizeCaptionContainer(offset);
                } else {
                    cell.resizeCaptionContainer(0);
                }
            } else {
                cell.resizeCaptionContainer(0);
            }
        }

        /**
         * Clear.
         */
        @Override
        public void clear() {
            for (String cid : availableCells.keySet()) {
                removeCell(cid);
            }
            availableCells.clear();
            availableCells.put(ROW_HEADER_COLUMN_KEY,
                    new RowHeadersHeaderCell());
        }

        /**
         * Update cells radio from uidl.
         *
         * @param uidl the uidl
         */
        public void updateCellsRadioFromUIDL(UIDL uidl) {
            Iterator<?> it = uidl.getChildIterator();

            while (it.hasNext()) {
                final UIDL col = (UIDL) it.next();
                final String cid = col.getStringAttribute("singleradio");
                mapSingleRadio.put(cid, col.getStringArrayAttribute("singleradioarr"));
                if (col.hasAttribute("singleradioval")) {
                    singleRadioVal.put(cid, col.getStringAttribute("singleradioval"));
                }
            }
        }

        /**
         * Update cells from uidl.
         *
         * @param uidl the uidl
         */
        public void updateCellsFromUIDL(UIDL uidl) {
            Iterator<?> it = uidl.getChildIterator();
            HashSet<String> updated = new HashSet<String>();
            boolean refreshContentWidths = initializedAndAttached
                    && hadScrollBars != willHaveScrollbars();
            while (it.hasNext()) {
                final UIDL col = (UIDL) it.next();
                final String cid = col.getStringAttribute("cid");
                updated.add(cid);

//                String caption = buildCaptionHtmlSnippet(col);
                HeaderCell c = getHeaderCell(cid);
                if (c == null) {
                    c = new HeaderCell(cid, col);
                    availableCells.put(cid, c);
                    if (initializedAndAttached) {
                        // we will need a column width recalculation
                        initializedAndAttached = false;
                        initialContentReceived = false;
                        isNewBody = true;
                    }
                } else {
//                    c.setText(caption);
                    c.setCheckCaption(col);
                }

                if (col.hasAttribute("sortable")) {
                    c.setSortable(true);
                    if (cid.equals(sortColumn)) {
                        c.setSorted(true);
                    } else {
                        c.setSorted(false);
                    }
                } else {
                    c.setSortable(false);
                }
                boolean x = col.hasAttribute("eicon");
                c.setExpandIcon(x);
                if (x) {
                    c.setExpand(col.getBooleanAttribute("eicon"));
                }

                if (col.hasAttribute("align")) {
                    c.setAlign(col.getStringAttribute("align").charAt(0));
                } else {
                    c.setAlign(ALIGN_LEFT);

                }
                if (col.hasAttribute("width") && !c.isResizing) {
                    // Make sure to accomodate for the sort indicator if
                    // necessary.
                    int width = col.getIntAttribute("width");
                    int widthWithoutAddedIndent = width;

                    // get min width with indent, no padding
                    int minWidth = c.getMinWidth(true, false);
                    if (width < minWidth) {
                        width = minWidth;
                    }
                    if (scrollBody != null && width != c.getWidthWithIndent()) {
                        // Do a more thorough update if a column is resized from
                        // the server *after* the header has been properly
                        // initialized                        
                        final int colIx = getColIndexByKey(c.cid);
                        final int newWidth = width;
                        Scheduler.get().scheduleDeferred(
                                new ScheduledCommand() {
                            @Override
                                    public void execute() {
                                        setColWidth(colIx, newWidth, true);  
                                    }
                        });
                        refreshContentWidths = true;
                    } else {
                        // get min width with no indent or padding
                        minWidth = c.getMinWidth(false, false);
                        if (widthWithoutAddedIndent < minWidth) {
                            widthWithoutAddedIndent = minWidth;
                        }
                        // save min width without indent
                        c.setWidth(widthWithoutAddedIndent, true);
                    }                    
                } else if (col.hasAttribute("er")) {
                    c.setExpandRatio(col.getFloatAttribute("er"));

                } else if (recalcWidths) {
                    c.setUndefinedWidth();

                } else {
                    boolean hadExpandRatio = c.getExpandRatio() > 0;
                    boolean hadDefinedWidth = c.isDefinedWidth();
                    if (hadExpandRatio || hadDefinedWidth) {
                        // Someone has removed a expand width or the defined
                        // width on the server side (setting it to -1), make the
                        // column undefined again and measure columns again.
                        c.setUndefinedWidth();
                        c.setExpandRatio(0);
                        refreshContentWidths = true;
                    }
                }

                if (col.hasAttribute("collapsed")) {
                    // ensure header is properly removed from parent (case when
                    // collapsing happens via servers side api)
                    if (c.isAttached()) {
                        c.removeFromParent();
                        headerChangedDuringUpdate = true;
                    }
                }
            }

            if (refreshContentWidths) {
                // Recalculate the column sizings if any column has changed
                Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                    @Override
                    public void execute() {
                        triggerLazyColumnAdjustment(true);
                    }
                });
            }

            // check for orphaned header cells
            for (Iterator<String> cit = availableCells.keySet().iterator(); cit
                    .hasNext();) {
                String cid = cit.next();
                if (!updated.contains(cid)) {
                    removeCell(cid);
                    cit.remove();
                    // we will need a column width recalculation, since columns
                    // with expand ratios should expand to fill the void.
                    initializedAndAttached = false;
                    initialContentReceived = false;
                    isNewBody = true;
                }
            }
        }

        /**
         * Enable column.
         *
         * @param cid the cid
         * @param index the index
         */
        public void enableColumn(String cid, int index) {
            final HeaderCell c = getHeaderCell(cid);
            if (!c.isEnabled() || getHeaderCell(index) != c) {
                setHeaderCell(index, c);
                if (initializedAndAttached) {
                    headerChangedDuringUpdate = true;
                }
            }
        }

        /**
         * Gets the visible cell count.
         *
         * @return the visible cell count
         */
        public int getVisibleCellCount() {
            return visibleCells.size();
        }

        /**
         * Sets the horizontal scroll position.
         *
         * @param scrollLeft the new horizontal scroll position
         */
        public void setHorizontalScrollPosition(int scrollLeft) {
            hTableWrapper.setScrollLeft(scrollLeft);
        }

        /**
         * Sets the column collapsing allowed.
         *
         * @param cc the new column collapsing allowed
         */
        public void setColumnCollapsingAllowed(boolean cc) {
            collapsedColumnsallowed = cc;
            if (cc) {
                columnSelector.getStyle().setDisplay(Display.BLOCK);
            } else {
                columnSelector.getStyle().setDisplay(Display.NONE);
            }
        }

        /**
         * Disable browser intelligence.
         */
        public void disableBrowserIntelligence() {
            hTableContainer.getStyle().setWidth(WRAPPER_WIDTH, Unit.PX);
        }

        /**
         * Enable browser intelligence.
         */
        public void enableBrowserIntelligence() {
            hTableContainer.getStyle().clearWidth();
        }

        /**
         * Sets the header cell.
         *
         * @param index the index
         * @param cell the cell
         */
        public void setHeaderCell(int index, HeaderCell cell) {
            if (cell.isEnabled()) {
                // we're moving the cell
                DOM.removeChild(tr, cell.getElement());
                orphan(cell);
                visibleCells.remove(cell);
            }
            if (index < visibleCells.size()) {
                // insert to right slot
                DOM.insertChild(tr, cell.getElement(), index);
                adopt(cell);
                visibleCells.add(index, cell);
            } else if (index == visibleCells.size()) {
                // simply append
                DOM.appendChild(tr, cell.getElement());
                adopt(cell);
                visibleCells.add(cell);
            } else {
                throw new RuntimeException(
                        "Header cells must be appended in order");
            }
        }

        /**
         * Gets the header cell.
         *
         * @param index the index
         * @return the header cell
         */
        public HeaderCell getHeaderCell(int index) {
            if (index >= 0 && index < visibleCells.size()) {
                return (HeaderCell) visibleCells.get(index);
            } else {
                return null;
            }
        }

        /**
         * Get's HeaderCell by it's column Key.
         *
         * Note that this returns HeaderCell even if it is currently collapsed.
         *
         * @param cid Column key of accessed HeaderCell
         * @return HeaderCell
         */
        public HeaderCell getHeaderCell(String cid) {
            return availableCells.get(cid);
        }

        /**
         * Move cell.
         *
         * @param oldIndex the old index
         * @param newIndex the new index
         */
        public void moveCell(int oldIndex, int newIndex) {
            final HeaderCell hCell = getHeaderCell(oldIndex);
            final Element cell = hCell.getElement();

            visibleCells.remove(oldIndex);
            DOM.removeChild(tr, cell);

            DOM.insertChild(tr, cell, newIndex);
            visibleCells.add(newIndex, hCell);
        }

        /**
         * Iterator.
         *
         * @return the iterator
         */
        @Override
        public Iterator<Widget> iterator() {
            return visibleCells.iterator();
        }

        /**
         * Removes the.
         *
         * @param w the w
         * @return true, if successful
         */
        @Override
        public boolean remove(Widget w) {
            if (visibleCells.contains(w)) {
                visibleCells.remove(w);
                orphan(w);
                DOM.removeChild(DOM.getParent(w.getElement()), w.getElement());
                return true;
            }
            return false;
        }

        /**
         * Removes the cell.
         *
         * @param colKey the col key
         */
        public void removeCell(String colKey) {
            final HeaderCell c = getHeaderCell(colKey);
            remove(c);
        }

        /**
         * Focus slot.
         *
         * @param index the index
         */
        private void focusSlot(int index) {
            removeSlotFocus();
            if (index > 0) {
                Element child = tr.getChild(index - 1).getFirstChild().cast();
                child.setClassName(VExtCustomScrollTable.this
                        .getStylePrimaryName() + "-resizer");
                child.addClassName(VExtCustomScrollTable.this
                        .getStylePrimaryName() + "-focus-slot-right");
            } else {
                Element child = tr.getChild(index).getFirstChild().cast();
                child.setClassName(VExtCustomScrollTable.this
                        .getStylePrimaryName() + "-resizer");
                child.addClassName(VExtCustomScrollTable.this
                        .getStylePrimaryName() + "-focus-slot-left");
            }
            focusedSlot = index;
        }

        /**
         * Removes the slot focus.
         */
        private void removeSlotFocus() {
            if (focusedSlot < 0) {
                return;
            }
            if (focusedSlot == 0) {
                Element child = tr.getChild(focusedSlot).getFirstChild().cast();
                child.setClassName(VExtCustomScrollTable.this
                        .getStylePrimaryName() + "-resizer");
            } else if (focusedSlot > 0) {
                Element child = tr.getChild(focusedSlot - 1).getFirstChild()
                        .cast();
                child.setClassName(VExtCustomScrollTable.this
                        .getStylePrimaryName() + "-resizer");
            }
            focusedSlot = -1;
        }

        /**
         * On browser event.
         *
         * @param event the event
         */
        @Override
        public void onBrowserEvent(Event event) {
            if (enabled) {
                if (event.getEventTarget().cast() == columnSelector) {
                    final int left = DOM.getAbsoluteLeft(columnSelector);
                    final int top = DOM.getAbsoluteTop(columnSelector)
                            + DOM.getElementPropertyInt(columnSelector,
                            "offsetHeight");
                    client.getContextMenu().showAt(this, left, top);
                }
            }
        }

        /**
         * On detach.
         */
        @Override
        protected void onDetach() {
            super.onDetach();
            if (client != null) {
                client.getContextMenu().ensureHidden(this);
            }
        }

        /**
         * The Class VisibleColumnAction.
         */
        class VisibleColumnAction extends Action {

            /**
             * The col key.
             */
            String colKey;
            /**
             * The collapsed.
             */
            private boolean collapsed;
            /**
             * The noncollapsible.
             */
            private boolean noncollapsible = false;
            /**
             * The currently focused row.
             */
            private VScrollTableRow currentlyFocusedRow;

            /**
             * Instantiates a new visible column action.
             *
             * @param colKey the col key
             */
            public VisibleColumnAction(String colKey) {
                super(VExtCustomScrollTable.TableHead.this);
                this.colKey = colKey;
                caption = tHead.getHeaderCell(colKey).getCaption();
                currentlyFocusedRow = focusedRow;
            }

            /**
             * Execute.
             */
            @Override
            public void execute() {
                if (noncollapsible) {
                    return;
                }
                client.getContextMenu().hide();
                String dColKey = null;
                String tColKey = null;
                if (showDoubleColHeaders) {
                    dColKey = findDoubleHeaderMappedKey(colKey);
                    if (showTripleColHeaders) {
                        tColKey = findTripleHeaderMappedKey(dColKey);
                    }
                }
                boolean sendDouble = false;
                boolean sendTriple = false;
                // toggle selected column
                if (collapsedColumns.contains(colKey)) {
                    collapsedColumns.remove(colKey);
                    if (showDoubleColHeaders) {
                        if (doublecollapsedColumns.contains(dColKey)) {
                            doublecollapsedColumns.remove(dColKey);
                            sendDouble = true;
                        }
                        if (showTripleColHeaders) {
                            if (tripleCollapsedColumns.contains(tColKey)) {
                                tripleCollapsedColumns.remove(tColKey);
                                sendTriple = true;
                            }
                        }
                    }
                } else {
                    tHead.removeCell(colKey);
                    collapsedColumns.add(colKey);
                    if (showDoubleColHeaders) {
                        String tp1[] = findVisibleMainColumn(dColKey);
                        if (tp1.length <= 0) {
                            tdHead.removeCell(dColKey);
                            doublecollapsedColumns.add(dColKey);
                            sendDouble = true;
                            if (showTripleColHeaders) {
                                String tp2[] = findVisibleDoubleColumn(tColKey);
                                if (tp2.length <= 0) {
                                    ttHead.removeCell(tColKey);
                                    tripleCollapsedColumns.add(tColKey);
                                    sendTriple = true;
                                }
                            }
                        }

                    }
                    triggerLazyColumnAdjustment(true);
                }

                // update variable to server
                client.updateVariable(paintableId, "collapsedcolumns",
                        collapsedColumns.toArray(new String[collapsedColumns
                        .size()]), !(sendDouble || sendTriple));
                if (showDoubleColHeaders) {
                    if (sendDouble) {
                        client.updateVariable(paintableId, "doublecollapsedcolumns",
                                doublecollapsedColumns.toArray(new String[doublecollapsedColumns
                                .size()]), !sendTriple);
                    }
                    if (showTripleColHeaders && sendTriple) {
                        client.updateVariable(paintableId, "triplecollapsedcolumns",
                                tripleCollapsedColumns.toArray(new String[tripleCollapsedColumns
                                .size()]), sendTriple);
                    }
                }

                // let rowRequestHandler determine proper rows
                rowRequestHandler.refreshContent();
                lazyRevertFocusToRow(currentlyFocusedRow);
            }

            /**
             * Sets the collapsed.
             *
             * @param b the new collapsed
             */
            public void setCollapsed(boolean b) {
                collapsed = b;
            }

            /**
             * Sets the noncollapsible.
             *
             * @param b the new noncollapsible
             */
            public void setNoncollapsible(boolean b) {
                noncollapsible = b;
            }

            /**
             * Override default method to distinguish on/off columns.
             *
             * @return the html
             */
            @Override
            public String getHTML() {
                final StringBuffer buf = new StringBuffer();
                buf.append("<span class=\"");
                if (collapsed) {
                    buf.append("v-off");
                } else {
                    buf.append("v-on");
                }
                if (noncollapsible) {
                    buf.append(" v-disabled");
                }
                buf.append("\">");

                buf.append(super.getHTML());
                buf.append("</span>");

                return buf.toString();
            }
        }

        /*
         * Returns columns as Action array for column select popup
         */
        /**
         * Gets the actions.
         *
         * @return the actions
         */
        @Override
        public Action[] getActions() {
            Object[] cols;
            if (columnReordering && columnOrder != null) {
                cols = columnOrder;
            } else {
                // if columnReordering is disabled, we need different way to get
                // all available columns
                cols = visibleColOrder;
                cols = new Object[visibleColOrder.length
                        + collapsedColumns.size()];
                int i;
                for (i = 0; i < visibleColOrder.length; i++) {
                    cols[i] = visibleColOrder[i];
                }
                for (final Iterator<String> it = collapsedColumns.iterator(); it
                        .hasNext();) {
                    cols[i++] = it.next();
                }
            }
            final Action[] actions = new Action[cols.length];

            for (int i = 0; i < cols.length; i++) {
                final String cid = (String) cols[i];
                final HeaderCell c = getHeaderCell(cid);
                final VisibleColumnAction a = new VisibleColumnAction(
                        c.getColKey());
                a.setCaption(c.getCaption());
                if (!c.isEnabled()) {
                    a.setCollapsed(true);
                }
                if (noncollapsibleColumns.contains(cid)) {
                    a.setNoncollapsible(true);
                }
                actions[i] = a;
            }
            return actions;
        }

        /**
         * Gets the client.
         *
         * @return the client
         */
        @Override
        public ApplicationConnection getClient() {
            return client;
        }

        /**
         * Gets the paintable id.
         *
         * @return the paintable id
         */
        @Override
        public String getPaintableId() {
            return paintableId;
        }

        /**
         * Returns column alignments for visible columns.
         *
         * @return the column alignments
         */
        public char[] getColumnAlignments() {
            final Iterator<Widget> it = visibleCells.iterator();
            final char[] aligns = new char[visibleCells.size()];
            int colIndex = 0;
            while (it.hasNext()) {
                aligns[colIndex++] = ((HeaderCell) it.next()).getAlign();
            }
            return aligns;
        }

        /**
         * Disables the automatic calculation of all column widths by forcing
         * the widths to be "defined" thus turning off expand ratios and such.
         *
         * @param source the source
         */
        public void disableAutoColumnWidthCalculation(HeaderCell source) {
            for (HeaderCell cell : availableCells.values()) {
                cell.disableAutoWidthCalculation();
            }
            // fire column resize events for all columns but the source of the
            // resize action, since an event will fire separately for this.
            ArrayList<HeaderCell> columns = new ArrayList<HeaderCell>(
                    availableCells.values());
            if(source!=null){
            columns.remove(source);
            }
            sendColumnWidthUpdates(columns);
            forceRealignColumnHeaders();
        }
    }

    /**
     * A cell in the footer.
     */
    public class FooterCell extends Widget {

        /**
         * The td.
         */
        private final Element td = DOM.createTD();
        /**
         * The caption container.
         */
        private final Element captionContainer = DOM.createDiv();
        /**
         * The align.
         */
        private char align = ALIGN_LEFT;
        /**
         * The width.
         */
        private int width = -1;
        /**
         * The expand ratio.
         */
        private float expandRatio = 0;
        /**
         * The cid.
         */
        private final String cid;
        /**
         * The defined width.
         */
        boolean definedWidth = false;
        /**
         * The natural width.
         */
        private int naturalWidth = -1;

        /**
         * Instantiates a new footer cell.
         *
         * @param colId the col id
         * @param headerText the header text
         */
        public FooterCell(String colId, String headerText) {
            cid = colId;

            setText(headerText);

            // ensure no clipping initially (problem on column additions)
            captionContainer.getStyle().setOverflow(Overflow.VISIBLE);

            DOM.sinkEvents(captionContainer, Event.MOUSEEVENTS);

            DOM.appendChild(td, captionContainer);

            DOM.sinkEvents(td, Event.MOUSEEVENTS | Event.ONDBLCLICK
                    | Event.ONCONTEXTMENU);

            setElement(td);

            updateStyleNames(VExtCustomScrollTable.this.getStylePrimaryName());
        }

        /**
         * Update style names.
         *
         * @param primaryStyleName the primary style name
         */
        protected void updateStyleNames(String primaryStyleName) {
            captionContainer.setClassName(primaryStyleName
                    + "-footer-container");
        }

        /**
         * Sets the text of the footer.
         *
         * @param footerText The text in the footer
         */
        public void setText(String footerText) {
            if (footerText == null || footerText.equals("")) {
                footerText = "&nbsp;";
            }

            DOM.setInnerHTML(captionContainer, footerText);
        }

        /**
         * Set alignment of the text in the cell.
         *
         * @param c The alignment which can be ALIGN_CENTER, ALIGN_LEFT,
         * ALIGN_RIGHT
         */
        public void setAlign(char c) {
            if (align != c) {
                switch (c) {
                    case ALIGN_CENTER:
                        captionContainer.getStyle().setTextAlign(TextAlign.CENTER);
                        break;
                    case ALIGN_RIGHT:
                        captionContainer.getStyle().setTextAlign(TextAlign.RIGHT);
                        break;
                    default:
                        captionContainer.getStyle().setTextAlign(TextAlign.LEFT);
                        break;
                }
            }
            align = c;
        }

        /**
         * Get the alignment of the text int the cell.
         *
         * @return Returns either ALIGN_CENTER, ALIGN_LEFT or ALIGN_RIGHT
         */
        public char getAlign() {
            return align;
        }

        /**
         * Sets the width of the cell. This width should not include any
         * possible indent modifications that are present in
         * {@link VScrollTableBody#getMaxIndent()}.
         *
         * @param w The width of the cell
         * @param ensureDefinedWidth Ensures that the given width is not
         * recalculated
         */
        public void setWidth(int w, boolean ensureDefinedWidth) {

            if (ensureDefinedWidth) {
                definedWidth = true;
                // on column resize expand ratio becomes zero
                expandRatio = 0;
            }
            if (width == w) {
                return;
            }
            if (width == -1) {
                // go to default mode, clip content if necessary
                captionContainer.getStyle().clearOverflow();
            }
            width = w;
            if (w == -1) {
                captionContainer.getStyle().clearWidth();
                setWidth("");
            } else {
                /*
                 * Reduce width with one pixel for the right border since the
                 * footers does not have any spacers between them.
                 */
                final int borderWidths = 1;

                // Set the container width (check for negative value)
                captionContainer.getStyle().setPropertyPx("width",
                        Math.max(w - borderWidths, 0));

                /*
                 * if we already have tBody, set the header width properly, if
                 * not defer it. IE will fail with complex float in table header
                 * unless TD width is not explicitly set.
                 */
                if (scrollBody != null) {
                    int maxIndent = scrollBody.getMaxIndent();
                    if (w < maxIndent
                            && tFoot.visibleCells.indexOf(this) == getHierarchyColumnIndex()) {
                        // ensure there's room for the indent
                        w = maxIndent;
                    }
                    int tdWidth = w + scrollBody.getCellExtraWidth()
                            - borderWidths;
                    setWidth(Math.max(tdWidth, 0) + "px");
                } else {
                    Scheduler.get().scheduleDeferred(new Command() {
                        @Override
                        public void execute() {
                            int tdWidth = width;
                            int maxIndent = scrollBody.getMaxIndent();
                            if (tdWidth < maxIndent
                                    && tFoot.visibleCells.indexOf(this) == getHierarchyColumnIndex()) {
                                // ensure there's room for the indent
                                tdWidth = maxIndent;
                            }
                            tdWidth += scrollBody.getCellExtraWidth()
                                    - borderWidths;
                            setWidth(Math.max(tdWidth, 0) + "px");
                        }
                    });
                }
            }
        }

        /**
         * Sets the width to undefined.
         */
        public void setUndefinedWidth() {
            definedWidth = false;
            setWidth(-1, false);
        }

        /**
         * Detects if width is fixed by developer on server side or resized to
         * current width by user.
         *
         * @return true if defined, false if "natural" width
         */
        public boolean isDefinedWidth() {
            return definedWidth && width >= 0;
        }

        /**
         * Returns the pixels width of the footer cell.
         *
         * @return The width in pixels
         */
        public int getWidth() {
            return width;
        }

        /**
         * Sets the expand ratio of the cell.
         *
         * @param floatAttribute The expand ratio
         */
        public void setExpandRatio(float floatAttribute) {
            expandRatio = floatAttribute;
        }

        /**
         * Returns the expand ration of the cell.
         *
         * @return The expand ratio
         */
        public float getExpandRatio() {
            return expandRatio;
        }

        /**
         * Is the cell enabled?.
         *
         * @return True if enabled else False
         */
        public boolean isEnabled() {
            return getParent() != null;
        }

        /**
         * Handle column clicking.
         *
         * @param event the event
         */
        @Override
        public void onBrowserEvent(Event event) {
            if (enabled && event != null) {
                handleCaptionEvent(event);

                if (DOM.eventGetType(event) == Event.ONMOUSEUP) {
                    scrollBodyPanel.setFocus(true);
                }
                boolean stopPropagation = true;
                if (event.getTypeInt() == Event.ONCONTEXTMENU
                        && !client.hasEventListeners(VExtCustomScrollTable.this,
                        TableConstants.FOOTER_CLICK_EVENT_ID)) {
                    // Show browser context menu if a footer click listener is
                    // not present
                    stopPropagation = false;
                }
                if (stopPropagation) {
                    event.stopPropagation();
                    event.preventDefault();
                }
            }
        }

        /**
         * Handles a event on the captions.
         *
         * @param event The event to handle
         */
        protected void handleCaptionEvent(Event event) {
            if (event.getTypeInt() == Event.ONMOUSEUP
                    || event.getTypeInt() == Event.ONDBLCLICK) {
                fireFooterClickedEvent(event);
            }
        }

        /**
         * Fires a footer click event after the user has clicked a column footer
         * cell.
         *
         * @param event The click event
         */
        private void fireFooterClickedEvent(Event event) {
            if (client.hasEventListeners(VExtCustomScrollTable.this,
                    TableConstants.FOOTER_CLICK_EVENT_ID)) {
                MouseEventDetails details = MouseEventDetailsBuilder
                        .buildMouseEventDetails(event);
                client.updateVariable(paintableId, "footerClickEvent",
                        details.toString(), false);
                client.updateVariable(paintableId, "footerClickCID", cid, true);
            }
        }

        /**
         * Returns the column key of the column.
         *
         * @return The column key
         */
        public String getColKey() {
            return cid;
        }

        /**
         * Detects the natural minimum width for the column of this header cell.
         * If column is resized by user or the width is defined by server the
         * actual width is returned. Else the natural min width is returned.
         *
         * @param columnIndex column index hint, if -1 (unknown) it will be
         * detected
         * @return the natural column width
         */
        public int getNaturalColumnWidth(int columnIndex) {
            final int iw = columnIndex == getHierarchyColumnIndex() ? scrollBody
                    .getMaxIndent() : 0;
            if (isDefinedWidth()) {
                if (iw > width) {
                    return iw;
                }
                return width;
            } else {
                if (naturalWidth < 0) {
                    // This is recently revealed column. Try to detect a proper
                    // value (greater of header and data
                    // cols)

                    final int hw = ((Element) getElement().getLastChild())
                            .getOffsetWidth() + getHeaderPadding();
                    if (columnIndex < 0) {
                        columnIndex = 0;
                        for (Iterator<Widget> it = tHead.iterator(); it
                                .hasNext(); columnIndex++) {
                            if (it.next() == this) {
                                break;
                            }
                        }
                    }
                    final int cw = scrollBody.getColWidth(columnIndex);
                    naturalWidth = (hw > cw ? hw : cw);
                }
                if (iw > naturalWidth) {
                    return iw;
                } else {
                    return naturalWidth;
                }
            }
        }

        /**
         * Sets the natural minimum column width.
         *
         * @param w the new natural minimum column width
         */
        public void setNaturalMinimumColumnWidth(int w) {
            naturalWidth = w;
        }
    }

    /**
     * HeaderCell that is header cell for row headers.
     *
     * Reordering disabled and clicking on it resets sorting.
     */
    public class RowHeadersFooterCell extends FooterCell {

        /**
         * Instantiates a new row headers footer cell.
         */
        RowHeadersFooterCell() {
            super(ROW_HEADER_COLUMN_KEY, "");
        }

        /**
         * Handle caption event.
         *
         * @param event the event
         */
        @Override
        protected void handleCaptionEvent(Event event) {
            // NOP: RowHeaders cannot be reordered
            // TODO It'd be nice to reset sorting here
        }
    }

    /**
     * The footer of the table which can be seen in the bottom of the Table.
     */
    public class TableFooter extends Panel {

        /**
         * The Constant WRAPPER_WIDTH.
         */
        private static final int WRAPPER_WIDTH = 900000;
        /**
         * The visible cells.
         */
        ArrayList<Widget> visibleCells = new ArrayList<Widget>();
        /**
         * The available cells.
         */
        HashMap<String, FooterCell> availableCells = new HashMap<String, FooterCell>();
        /**
         * The div.
         */
        Element div = DOM.createDiv();
        /**
         * The h table wrapper.
         */
        Element hTableWrapper = DOM.createDiv();
        /**
         * The h table container.
         */
        Element hTableContainer = DOM.createDiv();
        /**
         * The table.
         */
        Element table = DOM.createTable();
        /**
         * The header table body.
         */
        Element headerTableBody = DOM.createTBody();
        /**
         * The tr.
         */
        Element tr = DOM.createTR();

        /**
         * Instantiates a new table footer.
         */
        public TableFooter() {

            hTableWrapper.getStyle().setOverflow(Overflow.HIDDEN);

            DOM.appendChild(table, headerTableBody);
            DOM.appendChild(headerTableBody, tr);
            DOM.appendChild(hTableContainer, table);
            DOM.appendChild(hTableWrapper, hTableContainer);
            DOM.appendChild(div, hTableWrapper);
            setElement(div);

            availableCells.put(ROW_HEADER_COLUMN_KEY,
                    new RowHeadersFooterCell());

            updateStyleNames(VExtCustomScrollTable.this.getStylePrimaryName());
        }

        /**
         * Update style names.
         *
         * @param primaryStyleName the primary style name
         */
        protected void updateStyleNames(String primaryStyleName) {
            hTableWrapper.setClassName(primaryStyleName + "-footer");
            setStyleName(primaryStyleName + "-footer-wrap");
            for (FooterCell c : availableCells.values()) {
                c.updateStyleNames(primaryStyleName);
            }
        }

        /**
         * Clear.
         */
        @Override
        public void clear() {
            for (String cid : availableCells.keySet()) {
                removeCell(cid);
            }
            availableCells.clear();
            availableCells.put(ROW_HEADER_COLUMN_KEY,
                    new RowHeadersFooterCell());
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * com.google.gwt.user.client.ui.Panel#remove(com.google.gwt.user.client
         * .ui.Widget)
         */
        /**
         * Removes the.
         *
         * @param w the w
         * @return true, if successful
         */
        @Override
        public boolean remove(Widget w) {
            if (visibleCells.contains(w)) {
                visibleCells.remove(w);
                orphan(w);
                DOM.removeChild(DOM.getParent(w.getElement()), w.getElement());
                return true;
            }
            return false;
        }

        /*
         * (non-Javadoc)
         * 
         * @see com.google.gwt.user.client.ui.HasWidgets#iterator()
         */
        /**
         * Iterator.
         *
         * @return the iterator
         */
        @Override
        public Iterator<Widget> iterator() {
            return visibleCells.iterator();
        }

        /**
         * Gets a footer cell which represents the given columnId.
         *
         * @param cid The columnId
         * @return The cell
         */
        public FooterCell getFooterCell(String cid) {
            return availableCells.get(cid);
        }

        /**
         * Gets a footer cell by using a column index.
         *
         * @param index The index of the column
         * @return The Cell
         */
        public FooterCell getFooterCell(int index) {
            if (index < visibleCells.size()) {
                return (FooterCell) visibleCells.get(index);
            } else {
                return null;
            }
        }

        /**
         * Updates the cells contents when updateUIDL request is received.
         *
         * @param uidl The UIDL
         */
        public void updateCellsFromUIDL(UIDL uidl) {
            Iterator<?> columnIterator = uidl.getChildIterator();
            HashSet<String> updated = new HashSet<String>();
            while (columnIterator.hasNext()) {
                final UIDL col = (UIDL) columnIterator.next();
                final String cid = col.getStringAttribute("cid");
                updated.add(cid);

                String caption = col.hasAttribute("fcaption") ? col
                        .getStringAttribute("fcaption") : "";
                FooterCell c = getFooterCell(cid);
                if (c == null) {
                    c = new FooterCell(cid, caption);
                    availableCells.put(cid, c);
                    if (initializedAndAttached) {
                        // we will need a column width recalculation
                        initializedAndAttached = false;
                        initialContentReceived = false;
                        isNewBody = true;
                    }
                } else {
                    c.setText(caption);
                }

                if (col.hasAttribute("align")) {
                    c.setAlign(col.getStringAttribute("align").charAt(0));
                } else {
                    c.setAlign(ALIGN_LEFT);

                }
                if (col.hasAttribute("width")) {
                    if (scrollBody == null || isNewBody) {
                        // Already updated by setColWidth called from
                        // TableHeads.updateCellsFromUIDL in case of a server
                        // side resize
                        final int width = col.getIntAttribute("width");
                        c.setWidth(width, true);
                    }
                } else if (recalcWidths) {
                    c.setUndefinedWidth();
                }
                if (col.hasAttribute("er")) {
                    c.setExpandRatio(col.getFloatAttribute("er"));
                }
                if (col.hasAttribute("collapsed")) {
                    // ensure header is properly removed from parent (case when
                    // collapsing happens via servers side api)
                    if (c.isAttached()) {
                        c.removeFromParent();
                        headerChangedDuringUpdate = true;
                    }
                }
            }

            // check for orphaned header cells
            for (Iterator<String> cit = availableCells.keySet().iterator(); cit
                    .hasNext();) {
                String cid = cit.next();
                if (!updated.contains(cid)) {
                    removeCell(cid);
                    cit.remove();
                }
            }
        }

        /**
         * Set a footer cell for a specified column index.
         *
         * @param index The index
         * @param cell The footer cell
         */
        public void setFooterCell(int index, FooterCell cell) {
            if (cell.isEnabled()) {
                // we're moving the cell
                DOM.removeChild(tr, cell.getElement());
                orphan(cell);
                visibleCells.remove(cell);
            }
            if (index < visibleCells.size()) {
                // insert to right slot
                DOM.insertChild(tr, cell.getElement(), index);
                adopt(cell);
                visibleCells.add(index, cell);
            } else if (index == visibleCells.size()) {
                // simply append
                DOM.appendChild(tr, cell.getElement());
                adopt(cell);
                visibleCells.add(cell);
            } else {
                throw new RuntimeException(
                        "Header cells must be appended in order");
            }
        }

        /**
         * Remove a cell by using the columnId.
         *
         * @param colKey The columnId to remove
         */
        public void removeCell(String colKey) {
            final FooterCell c = getFooterCell(colKey);
            remove(c);
        }

        /**
         * Enable a column (Sets the footer cell).
         *
         * @param cid The columnId
         * @param index The index of the column
         */
        public void enableColumn(String cid, int index) {
            final FooterCell c = getFooterCell(cid);
            if (!c.isEnabled() || getFooterCell(index) != c) {
                setFooterCell(index, c);
                if (initializedAndAttached) {
                    headerChangedDuringUpdate = true;
                }
            }
        }

        /**
         * Disable browser measurement of the table width.
         */
        public void disableBrowserIntelligence() {
            hTableContainer.getStyle().setWidth(WRAPPER_WIDTH, Unit.PX);
        }

        /**
         * Enable browser measurement of the table width.
         */
        public void enableBrowserIntelligence() {
            hTableContainer.getStyle().clearWidth();
        }

        /**
         * Set the horizontal position in the cell in the footer. This is done
         * when a horizontal scrollbar is present.
         *
         * @param scrollLeft The value of the leftScroll
         */
        public void setHorizontalScrollPosition(int scrollLeft) {
            hTableWrapper.setScrollLeft(scrollLeft);
        }

        /**
         * Swap cells when the column are dragged.
         *
         * @param oldIndex The old index of the cell
         * @param newIndex The new index of the cell
         */
        public void moveCell(int oldIndex, int newIndex) {
            final FooterCell hCell = getFooterCell(oldIndex);
            final Element cell = hCell.getElement();

            visibleCells.remove(oldIndex);
            DOM.removeChild(tr, cell);

            DOM.insertChild(tr, cell, newIndex);
            visibleCells.add(newIndex, hCell);
        }
    }

    /**
     * This Panel can only contain VScrollTableRow type of widgets. This
     * "simulates" very large table, keeping spacers which take room of
     * unrendered rows.
     *
     */
    public class VScrollTableBody extends Panel {

        /**
         * The Constant DEFAULT_ROW_HEIGHT.
         */
        public static final int DEFAULT_ROW_HEIGHT = 24;
        /**
         * The row height.
         */
        private double rowHeight = -1;
        /**
         * The rendered rows.
         */
        private final LinkedList<Widget> renderedRows = new LinkedList<Widget>();
        /**
         * Due some optimizations row height measuring is deferred and initial
         * set of rows is rendered detached. Flag set on when table body has
         * been attached in dom and rowheight has been measured.
         */
        private boolean tBodyMeasurementsDone = false;
        /**
         * The pre spacer.
         */
        Element preSpacer = DOM.createDiv();
        /**
         * The post spacer.
         */
        Element postSpacer = DOM.createDiv();
        /**
         * The container.
         */
        Element container = DOM.createDiv();
        /**
         * The t body element.
         */
        TableSectionElement tBodyElement = Document.get().createTBodyElement();
        /**
         * The table.
         */
        Element table = DOM.createTable();
        /**
         * The first rendered.
         */
        private int firstRendered;
        /**
         * The last rendered.
         */
        private int lastRendered;
        /**
         * The aligns.
         */
        private char[] aligns;
        /**
         * The freeze spacer.
         */
        Element freezeSpacer = DOM.createDiv();

        /**
         * Instantiates a new v scroll table body.
         */
        protected VScrollTableBody() {
            constructDOM();
            setElement(container);
        }

        /**
         * Sets the last rendered.
         *
         * @param lastRendered the new last rendered
         */
        public void setLastRendered(int lastRendered) {
            if (totalRows >= 0 && lastRendered > totalRows) {
                VConsole.log("setLastRendered: " + this.lastRendered + " -> "
                        + lastRendered);
                this.lastRendered = totalRows - 1;
            } else {
                this.lastRendered = lastRendered;
            }
        }

        /**
         * Gets the last rendered.
         *
         * @return the last rendered
         */
        public int getLastRendered() {
            return lastRendered;
        }

        /**
         * Gets the first rendered.
         *
         * @return the first rendered
         */
        public int getFirstRendered() {
            return firstRendered;
        }

        /**
         * Gets the row by row index.
         *
         * @param indexInTable the index in table
         * @return the row by row index
         */
        public VScrollTableRow getRowByRowIndex(int indexInTable) {
            int internalIndex = indexInTable - firstRendered;
            if (internalIndex >= 0 && internalIndex < renderedRows.size()) {
                return (VScrollTableRow) renderedRows.get(internalIndex);
            } else {
                return null;
            }
        }

        /**
         * Gets the required height.
         *
         * @return the height of scrollable body, subpixels ceiled.
         */
        public int getRequiredHeight() {
            return preSpacer.getOffsetHeight() + postSpacer.getOffsetHeight() + freezeSpacer.getOffsetHeight()
                    + Util.getRequiredHeight(table);
        }

        /**
         * Construct dom.
         */
        private void constructDOM() {
            if (BrowserInfo.get().isIE()) {
                table.setPropertyInt("cellSpacing", 0);
            }

            table.appendChild(tBodyElement);
            DOM.appendChild(container, preSpacer);
            DOM.appendChild(container, table);
            DOM.appendChild(container, freezeSpacer);
            DOM.appendChild(container, postSpacer);
            if (BrowserInfo.get().requiresTouchScrollDelegate()) {
                NodeList<Node> childNodes = container.getChildNodes();
                for (int i = 0; i < childNodes.getLength(); i++) {
                    Element item = (Element) childNodes.getItem(i);
                    item.getStyle().setProperty("webkitTransform",
                            "translate3d(0,0,0)");
                }
            }
            updateStyleNames(VExtCustomScrollTable.this.getStylePrimaryName());
        }

        /**
         * Update style names.
         *
         * @param primaryStyleName the primary style name
         */
        protected void updateStyleNames(String primaryStyleName) {
            table.setClassName(primaryStyleName + "-table");
            preSpacer.setClassName(primaryStyleName + "-row-spacer");
            postSpacer.setClassName(primaryStyleName + "-row-spacer");
            freezeSpacer.setClassName(primaryStyleName + "-freeze-spacer");
            for (Widget w : renderedRows) {
                VScrollTableRow row = (VScrollTableRow) w;
                row.updateStyleNames(primaryStyleName);
            }
        }

        /**
         * Gets the available width.
         *
         * @return the available width
         */
        public int getAvailableWidth() {
            int availW = scrollBodyPanel.getOffsetWidth() - getBorderWidth();
            return availW;
        }

        /**
         * Render initial rows.
         *
         * @param rowData the row data
         * @param firstIndex the first index
         * @param rows the rows
         */
        public void renderInitialRows(UIDL rowData, int firstIndex, int rows) {
            firstRendered = firstIndex;
            setLastRendered(firstIndex + rows - 1);
            final Iterator<?> it = rowData.getChildIterator();
            aligns = tHead.getColumnAlignments();
            while (it.hasNext()) {
                final VScrollTableRow row = createRow((UIDL) it.next(), aligns);
                addRow(row);
            }
            if (isAttached()) {
                fixSpacers();
            }
        }

        /**
         * Render rows.
         *
         * @param rowData the row data
         * @param firstIndex the first index
         * @param rows the rows
         */
        public void renderRows(UIDL rowData, int firstIndex, int rows) {
            // FIXME REVIEW
            aligns = tHead.getColumnAlignments();
            final Iterator<?> it = rowData.getChildIterator();
            if (firstIndex == lastRendered + 1) {
                while (it.hasNext()) {
                    final VScrollTableRow row = prepareRow((UIDL) it.next());
                    addRow(row);
                    setLastRendered(lastRendered + 1);
                }
                fixSpacers();
            } else if (firstIndex + rows == firstRendered) {
                final VScrollTableRow[] rowArray = new VScrollTableRow[rows];
                int i = rows;
                while (it.hasNext()) {
                    i--;
                    rowArray[i] = prepareRow((UIDL) it.next());
                }
                for (i = 0; i < rows; i++) {
                    addRowBeforeFirstRendered(rowArray[i]);
                    firstRendered--;
                }
            } else {
                // completely new set of rows

                // there can't be sanity checks for last rendered within this
                // while loop regardless of what has been set previously, so
                // change it temporarily to true and then return the original
                // value
                boolean temp = postponeSanityCheckForLastRendered;
                postponeSanityCheckForLastRendered = true;
                while (lastRendered + 1 > firstRendered) {
                    unlinkRow(false);
                }
                postponeSanityCheckForLastRendered = temp;
                VScrollTableRow row = prepareRow((UIDL) it.next());
                firstRendered = firstIndex;
                setLastRendered(firstIndex - 1);
                addRow(row);
                setLastRendered(lastRendered + 1);
                setContainerHeight();
                fixSpacers();
                while (it.hasNext()) {
                    addRow(prepareRow((UIDL) it.next()));
                    setLastRendered(lastRendered + 1);
                }
                fixSpacers();
            }

            // this may be a new set of rows due content change,
            // ensure we have proper cache rows
            ensureCacheFilled();
        }

        /**
         * Ensure we have the correct set of rows on client side, e.g. if the
         * content on the server side has changed, or the client scroll position
         * has changed since the last request.
         */
        protected void ensureCacheFilled() {
            int reactFirstRow = (int) (firstRowInViewPort - pageLength
                    * cache_react_rate);
            int reactLastRow = (int) (firstRowInViewPort + pageLength + pageLength
                    * cache_react_rate);
            if (reactFirstRow < 0) {
                reactFirstRow = 0;
            }
            if (reactLastRow >= totalRows) {
                reactLastRow = totalRows - 1;
            }
            if (lastRendered < reactFirstRow || firstRendered > reactLastRow) {
                /*
                 * #8040 - scroll position is completely changed since the
                 * latest request, so request a new set of rows.
                 * 
                 * TODO: We should probably check whether the fetched rows match
                 * the current scroll position right when they arrive, so as to
                 * not waste time rendering a set of rows that will never be
                 * visible...
                 */
                rowRequestHandler.triggerRowFetch(reactFirstRow, reactLastRow
                        - reactFirstRow + 1, 1);
            } else if (lastRendered < reactLastRow) {
                // get some cache rows below visible area
                rowRequestHandler.triggerRowFetch(lastRendered + 1,
                        reactLastRow - lastRendered, 1);
            } else if (firstRendered > reactFirstRow) {
                /*
                 * Branch for fetching cache above visible area.
                 * 
                 * If cache needed for both before and after visible area, this
                 * will be rendered after-cache is received and rendered. So in
                 * some rare situations the table may make two cache visits to
                 * server.
                 */
                rowRequestHandler.triggerRowFetch(reactFirstRow, firstRendered
                        - reactFirstRow, 1);
            }
        }

        /**
         * Inserts rows as provided in the rowData starting at firstIndex.
         *
         * @param rowData the row data
         * @param firstIndex the first index
         * @param rows the number of rows
         * @return a list of the rows added.
         */
        protected List<VScrollTableRow> insertRows(UIDL rowData,
                int firstIndex, int rows) {
            aligns = tHead.getColumnAlignments();
            final Iterator<?> it = rowData.getChildIterator();
            List<VScrollTableRow> insertedRows = new ArrayList<VScrollTableRow>();

            if (firstIndex == lastRendered + 1) {
                while (it.hasNext()) {
                    final VScrollTableRow row = prepareRow((UIDL) it.next());
                    addRow(row);
                    insertedRows.add(row);
                    if (postponeSanityCheckForLastRendered) {
                        lastRendered++;
                    } else {
                        setLastRendered(lastRendered + 1);
                    }
                }
                fixSpacers();
            } else if (firstIndex + rows == firstRendered) {
                final VScrollTableRow[] rowArray = new VScrollTableRow[rows];
                int i = rows;
                while (it.hasNext()) {
                    i--;
                    rowArray[i] = prepareRow((UIDL) it.next());
                }
                for (i = 0; i < rows; i++) {
                    addRowBeforeFirstRendered(rowArray[i]);
                    insertedRows.add(rowArray[i]);
                    firstRendered--;
                }
            } else {
                // insert in the middle
                int ix = firstIndex;
                while (it.hasNext()) {
                    VScrollTableRow row = prepareRow((UIDL) it.next());
                    insertRowAt(row, ix);
                    insertedRows.add(row);
                    if (postponeSanityCheckForLastRendered) {
                        lastRendered++;
                    } else {
                        setLastRendered(lastRendered + 1);
                    }
                    ix++;
                }
                fixSpacers();
            }
            return insertedRows;
        }

        /**
         * Insert and reindex rows.
         *
         * @param rowData the row data
         * @param firstIndex the first index
         * @param rows the rows
         * @return the list
         */
        protected List<VScrollTableRow> insertAndReindexRows(UIDL rowData,
                int firstIndex, int rows) {
            List<VScrollTableRow> inserted = insertRows(rowData, firstIndex,
                    rows);
            int actualIxOfFirstRowAfterInserted = firstIndex + rows
                    - firstRendered;
            for (int ix = actualIxOfFirstRowAfterInserted; ix < renderedRows
                    .size(); ix++) {
                VScrollTableRow r = (VScrollTableRow) renderedRows.get(ix);
                r.setIndex(r.getIndex() + rows);
            }
            setContainerHeight();
            return inserted;
        }

        /**
         * Insert rows delete below.
         *
         * @param rowData the row data
         * @param firstIndex the first index
         * @param rows the rows
         */
        protected void insertRowsDeleteBelow(UIDL rowData, int firstIndex,
                int rows) {
            unlinkAllRowsStartingAt(firstIndex);
            insertRows(rowData, firstIndex, rows);
            setContainerHeight();
        }

        /**
         * This method is used to instantiate new rows for this table. It
         * automatically sets correct widths to rows cells and assigns correct
         * client reference for child widgets.
         *
         * This method can be called only after table has been initialized
         *
         * @param uidl the uidl
         * @return the v scroll table row
         */
        private VScrollTableRow prepareRow(UIDL uidl) {
            final VScrollTableRow row = createRow(uidl, aligns);
            row.initCellWidths();
            return row;
        }

        /**
         * Creates the row.
         *
         * @param uidl the uidl
         * @param aligns2 the aligns2
         * @return the v scroll table row
         */
        protected VScrollTableRow createRow(UIDL uidl, char[] aligns2) {
            if (uidl.hasAttribute("gen_html")) {
                // This is a generated row.
                return new VScrollTableGeneratedRow(uidl, aligns2);
            }
            return new VScrollTableRow(uidl, aligns2);
        }

        /**
         * Adds the row before first rendered.
         *
         * @param row the row
         */
        private void addRowBeforeFirstRendered(VScrollTableRow row) {
            row.setIndex(firstRendered - 1);
            if (row.isSelected()) {
                row.addStyleName("v-selected");
            }
            tBodyElement.insertBefore(row.getElement(),
                    tBodyElement.getFirstChild());
            adopt(row);
            renderedRows.add(0, row);
        }

        /**
         * Adds the row.
         *
         * @param row the row
         */
        private void addRow(VScrollTableRow row) {
            row.setIndex(firstRendered + renderedRows.size());
            if (row.isSelected()) {
                row.addStyleName("v-selected");
            }
            tBodyElement.appendChild(row.getElement());
            // Add to renderedRows before adopt so iterator() will return also
            // this row if called in an attach handler (#9264)
            renderedRows.add(row);
            adopt(row);
        }

        /**
         * Insert row at.
         *
         * @param row the row
         * @param index the index
         */
        private void insertRowAt(VScrollTableRow row, int index) {
            row.setIndex(index);
            if (row.isSelected()) {
                row.addStyleName("v-selected");
            }
            if (index > 0) {
                VScrollTableRow sibling = getRowByRowIndex(index - 1);
                tBodyElement
                        .insertAfter(row.getElement(), sibling.getElement());
            } else {
                VScrollTableRow sibling = getRowByRowIndex(index);
                tBodyElement.insertBefore(row.getElement(),
                        sibling.getElement());
            }
            adopt(row);
            int actualIx = index - firstRendered;
            renderedRows.add(actualIx, row);
        }

        /**
         * Iterator.
         *
         * @return the iterator
         */
        @Override
        public Iterator<Widget> iterator() {
            return renderedRows.iterator();
        }

        /**
         * Unlink row.
         *
         * @param fromBeginning the from beginning
         * @return false if couldn't remove row
         */
        protected boolean unlinkRow(boolean fromBeginning) {
            if (lastRendered - firstRendered < 0) {
                return false;
            }
            int actualIx;
            if (fromBeginning) {
                actualIx = 0;
                firstRendered++;
            } else {
                actualIx = renderedRows.size() - 1;
                if (postponeSanityCheckForLastRendered) {
                    --lastRendered;
                } else {
                    setLastRendered(lastRendered - 1);
                }
            }
            if (actualIx >= 0) {
                unlinkRowAtActualIndex(actualIx);
                fixSpacers();
                return true;
            }
            return false;
        }

        /**
         * Unlink rows.
         *
         * @param firstIndex the first index
         * @param count the count
         */
        protected void unlinkRows(int firstIndex, int count) {
            if (count < 1) {
                return;
            }
            if (firstRendered > firstIndex
                    && firstRendered < firstIndex + count) {
                count = count - (firstRendered - firstIndex);
                firstIndex = firstRendered;
            }
            int lastIndex = firstIndex + count - 1;
            if (lastRendered < lastIndex) {
                lastIndex = lastRendered;
            }
            for (int ix = lastIndex; ix >= firstIndex; ix--) {
                unlinkRowAtActualIndex(actualIndex(ix));
                if (postponeSanityCheckForLastRendered) {
                    // partialUpdate handles sanity check later
                    lastRendered--;
                } else {
                    setLastRendered(lastRendered - 1);
                }
            }
            fixSpacers();
        }

        /**
         * Unlink and reindex rows.
         *
         * @param firstIndex the first index
         * @param count the count
         */
        protected void unlinkAndReindexRows(int firstIndex, int count) {
            unlinkRows(firstIndex, count);
            int actualFirstIx = firstIndex - firstRendered;
            for (int ix = actualFirstIx; ix < renderedRows.size(); ix++) {
                VScrollTableRow r = (VScrollTableRow) renderedRows.get(ix);
                r.setIndex(r.getIndex() - count);
            }
            setContainerHeight();
        }

        /**
         * Unlink all rows starting at.
         *
         * @param index the index
         */
        protected void unlinkAllRowsStartingAt(int index) {
            if (firstRendered > index) {
                index = firstRendered;
            }
            for (int ix = renderedRows.size() - 1; ix >= index; ix--) {
                unlinkRowAtActualIndex(actualIndex(ix));
                setLastRendered(lastRendered - 1);
            }
            fixSpacers();
        }

        /**
         * Actual index.
         *
         * @param index the index
         * @return the int
         */
        private int actualIndex(int index) {
            return index - firstRendered;
        }

        /**
         * Unlink row at actual index.
         *
         * @param index the index
         */
        private void unlinkRowAtActualIndex(int index) {
            final VScrollTableRow toBeRemoved = (VScrollTableRow) renderedRows
                    .get(index);
            tBodyElement.removeChild(toBeRemoved.getElement());
            orphan(toBeRemoved);
            renderedRows.remove(index);
        }

        /**
         * Removes the.
         *
         * @param w the w
         * @return true, if successful
         */
        @Override
        public boolean remove(Widget w) {
            throw new UnsupportedOperationException();
        }

        /**
         * Fix container blocks height according to totalRows to avoid
         * "bouncing" when scrolling.
         */
        private void setContainerHeight() {
            fixSpacers();
            int ht = scrollBody.freezeSpacer.getOffsetHeight();
            container.getStyle().setHeight(measureRowHeightOffset(totalRows) + ht,
                    Unit.PX);
        }

        /**
         * Fix spacers.
         */
        private void fixSpacers() {
            int prepx = measureRowHeightOffset(firstRendered);
            if (prepx < 0) {
                prepx = 0;
            }
            preSpacer.getStyle().setPropertyPx("height", prepx);
            int postpx;
            if (pageLength == 0 && totalRows == pageLength) {
                /*
                 * TreeTable depends on having lastRendered out of sync in some
                 * situations, which makes this method miss the special
                 * situation in which one row worth of post spacer to be added
                 * if there are no rows in the table. #9203
                 */
                postpx = measureRowHeightOffset(1);
            } else {
                postpx = measureRowHeightOffset(totalRows - 1)
                        - measureRowHeightOffset(lastRendered);
            }

            if (postpx < 1) {
                postpx = 1;
            }
            postSpacer.getStyle().setPropertyPx("height", postpx);
        }

        /**
         * Gets the row height.
         *
         * @return the row height
         */
        public double getRowHeight() {
            return getRowHeight(false);
        }

        /**
         * Gets the row height.
         *
         * @param forceUpdate the force update
         * @return the row height
         */
        public double getRowHeight(boolean forceUpdate) {
            if (tBodyMeasurementsDone && !forceUpdate) {
                return rowHeight;
            } else {
                if (tBodyElement.getRows().getLength() > 0) {
                    int tableHeight = getTableHeight();
                    int rowCount = tBodyElement.getRows().getLength();
                    rowHeight = tableHeight / (double) rowCount;
                } else {
                    // Special cases if we can't just measure the current rows
                    if (!Double.isNaN(lastKnownRowHeight)) {
                        // Use previous value if available
                        if (BrowserInfo.get().isIE()) {
                            /*
                             * IE needs to reflow the table element at this
                             * point to work correctly (e.g.
                             * com.vaadin.tests.components.table.
                             * ContainerSizeChange) - the other code paths
                             * already trigger reflows, but here it must be done
                             * explicitly.
                             */
                            getTableHeight();
                        }
                        rowHeight = lastKnownRowHeight;
                    } else if (isAttached()) {
                        // measure row height by adding a dummy row
                        VScrollTableRow scrollTableRow = new VScrollTableRow();
                        tBodyElement.appendChild(scrollTableRow.getElement());
                        getRowHeight(forceUpdate);
                        tBodyElement.removeChild(scrollTableRow.getElement());
                    } else {
                        // TODO investigate if this can never happen anymore
                        return DEFAULT_ROW_HEIGHT;
                    }
                }
                lastKnownRowHeight = rowHeight;
                tBodyMeasurementsDone = true;
                return rowHeight;
            }
        }

        /**
         * Gets the table height.
         *
         * @return the table height
         */
        public int getTableHeight() {
            return table.getOffsetHeight();
        }

        /**
         * Returns the width available for column content.
         *
         * @param columnIndex the column index
         * @return the col width
         */
        public int getColWidth(int columnIndex) {
            if (tBodyMeasurementsDone) {
                if (renderedRows.isEmpty()) {
                    // no rows yet rendered
                    return 0;
                }
                for (Widget row : renderedRows) {
                    if (!(row instanceof VScrollTableGeneratedRow)) {
                        TableRowElement tr = row.getElement().cast();
                        Element wrapperdiv = tr.getCells().getItem(columnIndex)
                                .getFirstChildElement().cast();
                        return wrapperdiv.getOffsetWidth();
                    }
                }
                return 0;
            } else {
                return 0;
            }
        }

        /**
         * Sets the content width of a column.
         *
         * Due IE limitation, we must set the width to a wrapper elements inside
         * table cells (with overflow hidden, which does not work on td
         * elements).
         *
         * To get this work properly crossplatform, we will also set the width
         * of td.
         *
         * @param colIndex the col index
         * @param w the w
         */
        public void setColWidth(int colIndex, int w) {
            for (Widget row : renderedRows) {
                ((VScrollTableRow) row).setCellWidth(colIndex, w);
            }
        }
        /**
         * The cell extra width.
         */
        private int cellExtraWidth = -1;

        /**
         * Method to return the space used for cell paddings + border.
         *
         * @return the cell extra width
         */
        private int getCellExtraWidth() {
            if (cellExtraWidth < 0) {
                detectExtrawidth();
            }
            return cellExtraWidth;
        }

        /**
         * This method exists for the needs of {@link VTreeTable} only. May be
         * removed or replaced in the future.</br> </br> Returns the maximum
         * indent of the hierarcyColumn, if applicable.
         *
         * @return maximum indent in pixels
         * @see {@link VScrollTable#getHierarchyColumnIndex()}
         */
        protected int getMaxIndent() {
            return 0;
        }

        /**
         * This method exists for the needs of {@link VTreeTable} only. May be
         * removed or replaced in the future.</br> </br> Calculates the maximum
         * indent of the hierarcyColumn, if applicable.
         */
        protected void calculateMaxIndent() {
            // NOP
        }

        /**
         * Detect extrawidth.
         */
        private void detectExtrawidth() {
            NodeList<TableRowElement> rows = tBodyElement.getRows();
            if (rows.getLength() == 0) {
                /* need to temporary add empty row and detect */
                VScrollTableRow scrollTableRow = new VScrollTableRow();
                scrollTableRow.updateStyleNames(VExtCustomScrollTable.this
                        .getStylePrimaryName());
                tBodyElement.appendChild(scrollTableRow.getElement());
                detectExtrawidth();
                tBodyElement.removeChild(scrollTableRow.getElement());
            } else {
                boolean noCells = false;
                TableRowElement item = rows.getItem(0);
                TableCellElement firstTD = item.getCells().getItem(0);
                if (firstTD == null) {
                    // content is currently empty, we need to add a fake cell
                    // for measuring
                    noCells = true;
                    VScrollTableRow next = (VScrollTableRow) iterator().next();
                    boolean sorted = tHead.getHeaderCell(0) != null ? tHead
                            .getHeaderCell(0).isSorted() : false;
                    next.addCell(null, "", ALIGN_LEFT, "", true, sorted);
                    firstTD = item.getCells().getItem(0);
                }
                com.google.gwt.dom.client.Element wrapper = firstTD
                        .getFirstChildElement();
                cellExtraWidth = firstTD.getOffsetWidth()
                        - wrapper.getOffsetWidth();
                if (noCells) {
                    firstTD.getParentElement().removeChild(firstTD);
                }
            }
        }

        /**
         * Move col.
         *
         * @param oldIndex the old index
         * @param newIndex the new index
         */
        public void moveCol(int oldIndex, int newIndex) {

            // loop all rows and move given index to its new place
            final Iterator<?> rows = iterator();
            while (rows.hasNext()) {
                final VScrollTableRow row = (VScrollTableRow) rows.next();

                final Element td = DOM.getChild(row.getElement(), oldIndex);
                if (td != null) {
                    DOM.removeChild(row.getElement(), td);

                    DOM.insertChild(row.getElement(), td, newIndex);
                }
            }

        }

        /**
         * Restore row visibility which is set to "none" when the row is
         * rendered (due a performance optimization).
         */
        private void restoreRowVisibility() {
            for (Widget row : renderedRows) {
                row.getElement().getStyle().setProperty("visibility", "");
            }
        }

        /**
         * Index of.
         *
         * @param row the row
         * @return the int
         */
        public int indexOf(Widget row) {
            int relIx = -1;
            for (int ix = 0; ix < renderedRows.size(); ix++) {
                if (renderedRows.get(ix) == row) {
                    relIx = ix;
                    break;
                }
            }
            if (relIx >= 0) {
                return firstRendered + relIx;
            }
            return -1;
        }

        /**
         * The Class VScrollTableRow.
         */
        public class VScrollTableRow extends Panel implements ActionOwner, ContextMenuOwner {

            /**
             * The Constant TOUCHSCROLL_TIMEOUT.
             */
            private static final int TOUCHSCROLL_TIMEOUT = 100;
            /**
             * The Constant DRAGMODE_MULTIROW.
             */
            private static final int DRAGMODE_MULTIROW = 2;
            /**
             * The child widgets.
             */
            protected ArrayList<Widget> childWidgets = new ArrayList<Widget>();
            /**
             * The selected.
             */
            private boolean selected = false;
            /**
             * The row key.
             */
            protected final int rowKey;
            /**
             * The action keys.
             */
            private String[] actionKeys = null;
            /**
             * The row element.
             */
            private final TableRowElement rowElement;
            /**
             * The index.
             */
            private int index;
            /**
             * The touch start.
             */
            private Event touchStart;
            /**
             * The Constant TOUCH_CONTEXT_MENU_TIMEOUT.
             */
            private static final int TOUCH_CONTEXT_MENU_TIMEOUT = 500;
            /**
             * The context touch timeout.
             */
            private Timer contextTouchTimeout;
            /**
             * The drag touch timeout.
             */
            private Timer dragTouchTimeout;
            /**
             * The touch start y.
             */
            private int touchStartY;
            /**
             * The touch start x.
             */
            private int touchStartX;
            /**
             * The touch context provider.
             */
            private TouchContextProvider touchContextProvider = new TouchContextProvider(
                    this);
            /**
             * The tooltip info.
             */
            private TooltipInfo tooltipInfo = null;
            /**
             * The cell tool tips.
             */
            private Map<TableCellElement, TooltipInfo> cellToolTips = new HashMap<TableCellElement, TooltipInfo>();
            /**
             * The is dragging.
             */
            private boolean isDragging = false;
            /**
             * The row style.
             */
            private String rowStyle = null;

            /**
             * Instantiates a new v scroll table row.
             *
             * @param rowKey the row key
             */
            private VScrollTableRow(int rowKey) {
                this.rowKey = rowKey;
                rowElement = Document.get().createTRElement();
                setElement(rowElement);
                DOM.sinkEvents(getElement(), Event.MOUSEEVENTS
                        | Event.TOUCHEVENTS | Event.ONDBLCLICK
                        | Event.ONCONTEXTMENU | VTooltip.TOOLTIP_EVENTS);
            }

            /**
             * Instantiates a new v scroll table row.
             *
             * @param uidl the uidl
             * @param aligns the aligns
             */
            public VScrollTableRow(UIDL uidl, char[] aligns) {
                this(uidl.getIntAttribute("key"));

                /*
                 * Rendering the rows as hidden improves Firefox and Safari
                 * performance drastically.
                 */
                getElement().getStyle().setProperty("visibility", "hidden");

                rowStyle = uidl.getStringAttribute("rowstyle");
                updateStyleNames(VExtCustomScrollTable.this.getStylePrimaryName());

                String rowDescription = uidl.getStringAttribute("rowdescr");
                if (rowDescription != null && !rowDescription.equals("")) {
                    tooltipInfo = new TooltipInfo(rowDescription, null, this);
                } else {
                    tooltipInfo = null;
                }

                tHead.getColumnAlignments();
                int col = 0;
                int visibleColumnIndex = -1;

                // row header
                if (showRowHeaders) {
                    boolean sorted = tHead.getHeaderCell(col).isSorted();
                    addCell(uidl, buildCaptionHtmlSnippet(uidl), aligns[col++],
                            "rowheader", true, sorted);
                    visibleColumnIndex++;
                }

                if (uidl.hasAttribute("al")) {
                    actionKeys = uidl.getStringArrayAttribute("al");
                }

                addCellsFromUIDL(uidl, aligns, col, visibleColumnIndex);

                if (uidl.hasAttribute("selected") && !isSelected()) {
                    toggleSelection();
                }
            }

            /**
             * Update style names.
             *
             * @param primaryStyleName the primary style name
             */
            protected void updateStyleNames(String primaryStyleName) {

                if (getStylePrimaryName().contains("odd")) {
                    setStyleName(primaryStyleName + "-row-odd");
                } else {
                    setStyleName(primaryStyleName + "-row");
                }

                if (rowStyle != null) {
                    addStyleName(primaryStyleName + "-row-" + rowStyle);
                }

                for (int i = 0; i < rowElement.getChildCount(); i++) {
                    TableCellElement cell = (TableCellElement) rowElement
                            .getChild(i);
                    updateCellStyleNames(cell, primaryStyleName);
                }
            }

            /**
             * Gets the tooltip info.
             *
             * @return the tooltip info
             */
            public TooltipInfo getTooltipInfo() {
                return tooltipInfo;
            }

            /**
             * Add a dummy row, used for measurements if Table is empty.
             */
            public VScrollTableRow() {
                this(0);
                addCell(null, "_", 'b', "", true, false);
            }

            /**
             * Inits the cell widths.
             */
            protected void initCellWidths() {
                final int cells = tHead.getVisibleCellCount();
                for (int i = 0; i < cells; i++) {
                    int w = VExtCustomScrollTable.this
                            .getColWidth(getColKeyByIndex(i));
                    if (w < 0) {
                        w = 0;
                    }
                    setCellWidth(i, w);
                }
            }

            /**
             * Sets the cell width.
             *
             * @param cellIx the cell ix
             * @param width the width
             */
            protected void setCellWidth(int cellIx, int width) {
                final Element cell = DOM.getChild(getElement(), cellIx);
                Style wrapperStyle = cell.getFirstChildElement().getStyle();
                int wrapperWidth = width;
                if (BrowserInfo.get().isWebkit()
                        || BrowserInfo.get().isOpera10()) {
                    /*
                     * Some versions of Webkit and Opera ignore the width
                     * definition of zero width table cells. Instead, use 1px
                     * and compensate with a negative margin.
                     */
                    if (width == 0) {
                        wrapperWidth = 1;
                        wrapperStyle.setMarginRight(-1, Unit.PX);
                    } else {
                        wrapperStyle.clearMarginRight();
                    }
                }
                wrapperStyle.setPropertyPx("width", wrapperWidth);
                cell.getStyle().setPropertyPx("width", width);
            }

            /**
             * Adds the cells from uidl.
             *
             * @param uidl the uidl
             * @param aligns the aligns
             * @param col the col
             * @param visibleColumnIndex the visible column index
             */
            protected void addCellsFromUIDL(UIDL uidl, char[] aligns, int col,
                    int visibleColumnIndex) {
                final Iterator<?> cells = uidl.getChildIterator();
                while (cells.hasNext()) {
                    final Object cell = cells.next();
                    visibleColumnIndex++;

                    String columnId = visibleColOrder[visibleColumnIndex];

                    String style = "";
                    if (uidl.hasAttribute("style-" + columnId)) {
                        style = uidl.getStringAttribute("style-" + columnId);
                    }

                    String description = null;
                    if (uidl.hasAttribute("descr-" + columnId)) {
                        description = uidl.getStringAttribute("descr-"
                                + columnId);
                    }

                    boolean sorted = tHead.getHeaderCell(col).isSorted();
                    if (cell instanceof String) {
                        addCell(uidl, cell.toString(), aligns[col++], style,
                                isRenderHtmlInCells(), sorted, description);
                    } else {
                        final ComponentConnector cellContent = client
                                .getPaintable((UIDL) cell);

                        addCell(uidl, cellContent.getWidget(), aligns[col++],
                                style, sorted, description);
                    }
                }
            }

            /**
             * Overriding this and returning true causes all text cells to be
             * rendered as HTML.
             *
             * @return always returns false in the default implementation
             */
            protected boolean isRenderHtmlInCells() {
                return false;
            }

            /**
             * Detects whether row is visible in tables viewport.
             *
             * @return true, if is in view port
             */
            public boolean isInViewPort() {
                int absoluteTop = getAbsoluteTop();
                int absoluteBottom = absoluteTop + getOffsetHeight();
                int viewPortTop = scrollBodyPanel.getAbsoluteTop();
                int viewPortBottom = viewPortTop
                        + scrollBodyPanel.getOffsetHeight();
                return absoluteBottom > viewPortTop
                        && absoluteTop < viewPortBottom;
            }

            /**
             * Makes a check based on indexes whether the row is before the
             * compared row.
             *
             * @param row1 the row1
             * @return true if this rows index is smaller than in the row1
             */
            public boolean isBefore(VScrollTableRow row1) {
                return getIndex() < row1.getIndex();
            }

            /**
             * Sets the index of the row in the whole table. Currently used just
             * to set even/odd classname
             *
             * @param indexInWholeTable the new index
             */
            private void setIndex(int indexInWholeTable) {
                index = indexInWholeTable;
                boolean isOdd = indexInWholeTable % 2 == 0;
                // Inverted logic to be backwards compatible with earlier 6.4.
                // It is very strange because rows 1,3,5 are considered "even"
                // and 2,4,6 "odd".
                //
                // First remove any old styles so that both styles aren't
                // applied when indexes are updated.
                String primaryStyleName = getStylePrimaryName();
                if (primaryStyleName != null && !primaryStyleName.equals("")) {
                    removeStyleName(getStylePrimaryName());
                }
                if (!isOdd) {
                    addStyleName(VExtCustomScrollTable.this.getStylePrimaryName()
                            + "-row-odd");
                } else {
                    addStyleName(VExtCustomScrollTable.this.getStylePrimaryName()
                            + "-row");
                }
            }

            /**
             * Gets the index.
             *
             * @return the index
             */
            public int getIndex() {
                return index;
            }

            /**
             * On detach.
             */
            @Override
            protected void onDetach() {
                super.onDetach();
                client.getContextMenu().ensureHidden(this);
            }

            /**
             * Gets the key.
             *
             * @return the key
             */
            public String getKey() {
                return String.valueOf(rowKey);
            }

            /**
             * Adds the cell.
             *
             * @param rowUidl the row uidl
             * @param text the text
             * @param align the align
             * @param style the style
             * @param textIsHTML the text is html
             * @param sorted the sorted
             */
            public void addCell(UIDL rowUidl, String text, char align,
                    String style, boolean textIsHTML, boolean sorted) {
                addCell(rowUidl, text, align, style, textIsHTML, sorted, null);
            }

            /**
             * Adds the cell.
             *
             * @param rowUidl the row uidl
             * @param text the text
             * @param align the align
             * @param style the style
             * @param textIsHTML the text is html
             * @param sorted the sorted
             * @param description the description
             */
            public void addCell(UIDL rowUidl, String text, char align,
                    String style, boolean textIsHTML, boolean sorted,
                    String description) {
                // String only content is optimized by not using Label widget
                final TableCellElement td = DOM.createTD().cast();
                initCellWithText(text, align, style, textIsHTML, sorted,
                        description, td);
            }

            /**
             * Inits the cell with text.
             *
             * @param text the text
             * @param align the align
             * @param style the style
             * @param textIsHTML the text is html
             * @param sorted the sorted
             * @param description the description
             * @param td the td
             */
            protected void initCellWithText(String text, char align,
                    String style, boolean textIsHTML, boolean sorted,
                    String description, final TableCellElement td) {
                final Element container = DOM.createDiv();
                container.setClassName(VExtCustomScrollTable.this
                        .getStylePrimaryName() + "-cell-wrapper");

                td.setClassName(VExtCustomScrollTable.this.getStylePrimaryName()
                        + "-cell-content");

                if (style != null && !style.equals("")) {
                    td.addClassName(VExtCustomScrollTable.this
                            .getStylePrimaryName() + "-cell-content-" + style);
                }

                if (sorted) {
                    td.addClassName(VExtCustomScrollTable.this
                            .getStylePrimaryName() + "-cell-content-sorted");
                }

                if (textIsHTML) {
                    container.setInnerHTML(text);
                } else {
                    container.setInnerText(text);
                }
                setAlign(align, container);
                setTooltip(td, description);

                td.appendChild(container);
                getElement().appendChild(td);
            }

            /**
             * Update cell style names.
             *
             * @param td the td
             * @param primaryStyleName the primary style name
             */
            protected void updateCellStyleNames(TableCellElement td,
                    String primaryStyleName) {
                Element container = td.getFirstChild().cast();
                container.setClassName(primaryStyleName + "-cell-wrapper");

                /*
                 * Replace old primary style name with new one
                 */
                String className = td.getClassName();
                String oldPrimaryName = className.split("-cell-content")[0];
                td.setClassName(className.replaceAll(oldPrimaryName,
                        primaryStyleName));
            }

            /**
             * Adds the cell.
             *
             * @param rowUidl the row uidl
             * @param w the w
             * @param align the align
             * @param style the style
             * @param sorted the sorted
             * @param description the description
             */
            public void addCell(UIDL rowUidl, Widget w, char align,
                    String style, boolean sorted, String description) {
                final TableCellElement td = DOM.createTD().cast();
                initCellWithWidget(w, align, style, sorted, td);
                setTooltip(td, description);
            }

            /**
             * Sets the tooltip.
             *
             * @param td the td
             * @param description the description
             */
            private void setTooltip(TableCellElement td, String description) {
                if (description != null && !description.equals("")) {
                    TooltipInfo info = new TooltipInfo(description, null, this);
                    cellToolTips.put(td, info);
                } else {
                    cellToolTips.remove(td);
                }

            }

            /**
             * Sets the align.
             *
             * @param align the align
             * @param container the container
             */
            private void setAlign(char align, final Element container) {
                switch (align) {
                    case ALIGN_CENTER:
                        container.getStyle().setProperty("textAlign", "center");
                        break;
                    case ALIGN_LEFT:
                        container.getStyle().setProperty("textAlign", "left");
                        break;
                    case ALIGN_RIGHT:
                    default:
                        container.getStyle().setProperty("textAlign", "right");
                        break;
                }
            }

            /**
             * Inits the cell with widget.
             *
             * @param w the w
             * @param align the align
             * @param style the style
             * @param sorted the sorted
             * @param td the td
             */
            protected void initCellWithWidget(Widget w, char align,
                    String style, boolean sorted, final TableCellElement td) {
                final Element container = DOM.createDiv();
                String className = VExtCustomScrollTable.this
                        .getStylePrimaryName() + "-cell-content";
                if (style != null && !style.equals("")) {
                    className += " "
                            + VExtCustomScrollTable.this.getStylePrimaryName()
                            + "-cell-content-" + style;
                }
                if (sorted) {
                    className += " "
                            + VExtCustomScrollTable.this.getStylePrimaryName()
                            + "-cell-content-sorted";
                }
                td.setClassName(className);
                container.setClassName(VExtCustomScrollTable.this
                        .getStylePrimaryName() + "-cell-wrapper");
                setAlign(align, container);
                td.appendChild(container);
                getElement().appendChild(td);
                // ensure widget not attached to another element (possible tBody
                // change)
                w.removeFromParent();
                container.appendChild(w.getElement());
                adopt(w);
                childWidgets.add(w);
            }

            /**
             * Iterator.
             *
             * @return the iterator
             */
            @Override
            public Iterator<Widget> iterator() {
                return childWidgets.iterator();
            }

            /**
             * Removes the.
             *
             * @param w the w
             * @return true, if successful
             */
            @Override
            public boolean remove(Widget w) {
                if (childWidgets.contains(w)) {
                    orphan(w);
                    DOM.removeChild(DOM.getParent(w.getElement()),
                            w.getElement());
                    childWidgets.remove(w);
                    return true;
                } else {
                    return false;
                }
            }

            /**
             * If there are registered click listeners, sends a click event and
             * returns true. Otherwise, does nothing and returns false.
             *
             * @param event the event
             * @param targetTdOrTr the target td or tr
             * @param immediate Whether the event is sent immediately
             * @return Whether a click event was sent
             */
            private boolean handleClickEvent(Event event, Element targetTdOrTr,
                    boolean immediate) {
                if (!client.hasEventListeners(VExtCustomScrollTable.this,
                        TableConstants.ITEM_CLICK_EVENT_ID)) {
                    // Don't send an event if nobody is listening
                    return false;
                }

                // This row was clicked
                client.updateVariable(paintableId, "clickedKey", "" + rowKey,
                        false);

                if (getElement() == targetTdOrTr.getParentElement()) {
                    // A specific column was clicked
                    int childIndex = DOM.getChildIndex(getElement(),
                            targetTdOrTr);
                    String colKey = null;
                    colKey = tHead.getHeaderCell(childIndex).getColKey();
                    client.updateVariable(paintableId, "clickedColKey", colKey,
                            false);
                }

                MouseEventDetails details = MouseEventDetailsBuilder
                        .buildMouseEventDetails(event);

                client.updateVariable(paintableId, "clickEvent",
                        details.toString(), immediate);

                return true;
            }

            /**
             * Gets the tooltip.
             *
             * @param target the target
             * @return the tooltip
             */
            public TooltipInfo getTooltip(
                    com.google.gwt.dom.client.Element target) {

                TooltipInfo info = null;
                final Element targetTdOrTr = getTdOrTr(target);
                if (targetTdOrTr != null
                        && "td".equals(targetTdOrTr.getTagName().toLowerCase())) {
                    TableCellElement td = (TableCellElement) targetTdOrTr
                            .cast();
                    info = cellToolTips.get(td);
                }

                if (info == null) {
                    info = tooltipInfo;
                }

                return info;
            }

            /**
             * Gets the td or tr.
             *
             * @param target the target
             * @return the td or tr
             */
            private Element getTdOrTr(Element target) {
                Element thisTrElement = getElement();
                if (target == thisTrElement) {
                    // This was a on the TR element
                    return target;
                }

                // Iterate upwards until we find the TR element
                Element element = target;
                while (element != null
                        && element.getParentElement() != thisTrElement) {
                    element = element.getParentElement();
                }
                return element;
            }

            /**
             * Special handler for touch devices that support native scrolling.
             *
             * @param event the event
             * @return Whether the event was handled by this method.
             */
            private boolean handleTouchEvent(final Event event) {

                boolean touchEventHandled = false;

                if (enabled && hasNativeTouchScrolling) {
                    touchContextProvider.handleTouchEvent(event);

                    final Element targetTdOrTr = getEventTargetTdOrTr(event);
                    final int type = event.getTypeInt();

                    switch (type) {
                        case Event.ONTOUCHSTART:
                            touchEventHandled = true;
                            touchStart = event;
                            isDragging = false;
                            Touch touch = event.getChangedTouches().get(0);
                            // save position to fields, touches in events are same
                            // instance during the operation.
                            touchStartX = touch.getClientX();
                            touchStartY = touch.getClientY();

                            if (dragmode != 0) {
                                if (dragTouchTimeout == null) {
                                    dragTouchTimeout = new Timer() {
                                        @Override
                                        public void run() {
                                            if (touchStart != null) {
                                                // Start a drag if a finger is held
                                                // in place long enough, then moved
                                                isDragging = true;
                                            }
                                        }
                                    };
                                }
                                dragTouchTimeout.schedule(TOUCHSCROLL_TIMEOUT);
                            }

                            if (actionKeys != null) {
                                if (contextTouchTimeout == null) {
                                    contextTouchTimeout = new Timer() {
                                        @Override
                                        public void run() {
                                            if (touchStart != null) {
                                                // Open the context menu if finger
                                                // is held in place long enough.
                                                showContextMenu(touchStart);
                                                event.preventDefault();
                                                touchStart = null;
                                            }
                                        }
                                    };
                                }
                                contextTouchTimeout
                                        .schedule(TOUCH_CONTEXT_MENU_TIMEOUT);
                            }
                            break;
                        case Event.ONTOUCHMOVE:
                            touchEventHandled = true;
                            if (isSignificantMove(event)) {
                                if (contextTouchTimeout != null) {
                                    // Moved finger before the context menu timer
                                    // expired, so let the browser handle this as a
                                    // scroll.
                                    contextTouchTimeout.cancel();
                                    contextTouchTimeout = null;
                                }
                                if (!isDragging && dragTouchTimeout != null) {
                                    // Moved finger before the drag timer expired,
                                    // so let the browser handle this as a scroll.
                                    dragTouchTimeout.cancel();
                                    dragTouchTimeout = null;
                                }

                                if (dragmode != 0 && touchStart != null
                                        && isDragging) {
                                    event.preventDefault();
                                    event.stopPropagation();
                                    startRowDrag(touchStart, type, targetTdOrTr);
                                }
                                touchStart = null;
                            }
                            break;
                        case Event.ONTOUCHEND:
                        case Event.ONTOUCHCANCEL:
                            touchEventHandled = true;
                            if (contextTouchTimeout != null) {
                                contextTouchTimeout.cancel();
                            }
                            if (dragTouchTimeout != null) {
                                dragTouchTimeout.cancel();
                            }
                            if (touchStart != null) {
                                if (!BrowserInfo.get().isAndroid()) {
                                    event.preventDefault();
                                    event.stopPropagation();
                                    Util.simulateClickFromTouchEvent(touchStart,
                                            this);
                                }
                                touchStart = null;
                            }
                            isDragging = false;
                            break;
                    }
                }
                return touchEventHandled;
            }

            /*
             * React on click that occur on content cells only
             */
            /**
             * On browser event.
             *
             * @param event the event
             */
            @Override
            public void onBrowserEvent(final Event event) {

                final boolean touchEventHandled = handleTouchEvent(event);

                if (enabled && !touchEventHandled) {
                    final int type = event.getTypeInt();
                    final Element targetTdOrTr = getEventTargetTdOrTr(event);
                    if (type == Event.ONCONTEXTMENU) {
                        showContextMenu(event);
                        if (enabled
                                && (actionKeys != null || client
                                .hasEventListeners(
                                VExtCustomScrollTable.this,
                                TableConstants.ITEM_CLICK_EVENT_ID))) {
                            /*
                             * Prevent browser context menu only if there are
                             * action handlers or item click listeners
                             * registered
                             */
                            event.stopPropagation();
                            event.preventDefault();
                        }
                        return;
                    }

                    boolean targetCellOrRowFound = targetTdOrTr != null;

                    switch (type) {
                        case Event.ONDBLCLICK:
                            if (targetCellOrRowFound) {
                                handleClickEvent(event, targetTdOrTr, true);
                            }
                            break;
                        case Event.ONMOUSEUP:
                            /*
                             * Only fire a click if the mouseup hits the same
                             * element as the corresponding mousedown. This is first
                             * checked in the event preview but we can't fire the
                             * event there as the event might get canceled before it
                             * gets here.
                             */
                            if (mouseUpPreviewMatched
                                    && lastMouseDownTarget != null
                                    && lastMouseDownTarget == getElementTdOrTr(Util
                                    .getElementUnderMouse(event))) {
                                // "Click" with left, right or middle button

                                if (targetCellOrRowFound) {
                                    /*
                                     * Queue here, send at the same time as the
                                     * corresponding value change event - see #7127
                                     */
                                    boolean clickEventSent = handleClickEvent(event,
                                            targetTdOrTr, false);

                                    if (event.getButton() == Event.BUTTON_LEFT
                                            && isSelectable()) {

                                        // Ctrl+Shift click
                                        if ((event.getCtrlKey() || event.getMetaKey())
                                                && event.getShiftKey()
                                                && isMultiSelectModeDefault()) {
                                            toggleShiftSelection(false);
                                            setRowFocus(this);

                                            // Ctrl click
                                        } else if ((event.getCtrlKey() || event
                                                .getMetaKey())
                                                && isMultiSelectModeDefault()) {
                                            boolean wasSelected = isSelected();
                                            toggleSelection();
                                            setRowFocus(this);
                                            /*
                                             * next possible range select must start on
                                             * this row
                                             */
                                            selectionRangeStart = this;
                                            if (wasSelected) {
                                                removeRowFromUnsentSelectionRanges(this);
                                            }

                                        } else if ((event.getCtrlKey() || event
                                                .getMetaKey()) && isSingleSelectMode()) {
                                            // Ctrl (or meta) click (Single selection)
                                            if (!isSelected()
                                                    || (isSelected() && nullSelectionAllowed)) {

                                                if (!isSelected()) {
                                                    deselectAll();
                                                }

                                                toggleSelection();
                                                setRowFocus(this);
                                            }

                                        } else if (event.getShiftKey()
                                                && isMultiSelectModeDefault()) {
                                            // Shift click
                                            toggleShiftSelection(true);

                                        } else {
                                            // click
                                            boolean currentlyJustThisRowSelected = selectedRowKeys
                                                    .size() == 1
                                                    && selectedRowKeys
                                                    .contains(getKey());

                                            if (!currentlyJustThisRowSelected) {
                                                if (isSingleSelectMode()
                                                        || isMultiSelectModeDefault()) {
                                                    /*
                                                     * For default multi select mode
                                                     * (ctrl/shift) and for single
                                                     * select mode we need to clear the
                                                     * previous selection before
                                                     * selecting a new one when the user
                                                     * clicks on a row. Only in
                                                     * multiselect/simple mode the old
                                                     * selection should remain after a
                                                     * normal click.
                                                     */
                                                    deselectAll();
                                                }
                                                toggleSelection();
                                            } else if ((isSingleSelectMode() || isMultiSelectModeSimple())
                                                    && nullSelectionAllowed) {
                                                toggleSelection();
                                            }/*
                                             * else NOP to avoid excessive server
                                             * visits (selection is removed with
                                             * CTRL/META click)
                                             */

                                            selectionRangeStart = this;
                                            setRowFocus(this);
                                        }

                                        // Remove IE text selection hack
                                        if (BrowserInfo.get().isIE()) {
                                            ((Element) event.getEventTarget().cast())
                                                    .setPropertyJSO("onselectstart",
                                                    null);
                                        }
                                        // Queue value change
                                        sendSelectedRows(false);
                                    }
                                    /*
                                     * Send queued click and value change events if any
                                     * If a click event is sent, send value change with
                                     * it regardless of the immediate flag, see #7127
                                     */
                                    if (immediate || clickEventSent) {
                                        client.sendPendingVariableChanges();
                                    }
                                }
                            }
                            mouseUpPreviewMatched = false;
                            lastMouseDownTarget = null;
                            break;
                        case Event.ONTOUCHEND:
                        case Event.ONTOUCHCANCEL:
                            if (touchStart != null) {
                                /*
                                 * Touch has not been handled as neither context or
                                 * drag start, handle it as a click.
                                 */
                                Util.simulateClickFromTouchEvent(touchStart, this);
                                touchStart = null;
                            }
                            touchContextProvider.cancel();
                            break;
                        case Event.ONTOUCHMOVE:
                            if (isSignificantMove(event)) {
                                /*
                                 * TODO figure out scroll delegate don't eat events
                                 * if row is selected. Null check for active
                                 * delegate is as a workaround.
                                 */
                                if (dragmode != 0
                                        && touchStart != null
                                        && (TouchScrollDelegate
                                        .getActiveScrollDelegate() == null)) {
                                    startRowDrag(touchStart, type, targetTdOrTr);
                                }
                                touchContextProvider.cancel();
                                /*
                                 * Avoid clicks and drags by clearing touch start
                                 * flag.
                                 */
                                touchStart = null;
                            }

                            break;
                        case Event.ONTOUCHSTART:
                            touchStart = event;
                            Touch touch = event.getChangedTouches().get(0);
                            // save position to fields, touches in events are same
                            // isntance during the operation.
                            touchStartX = touch.getClientX();
                            touchStartY = touch.getClientY();
                            /*
                             * Prevent simulated mouse events.
                             */
                            touchStart.preventDefault();
                            if (dragmode != 0 || actionKeys != null) {
                                new Timer() {
                                    @Override
                                    public void run() {
                                        TouchScrollDelegate activeScrollDelegate = TouchScrollDelegate
                                                .getActiveScrollDelegate();
                                        /*
                                         * If there's a scroll delegate, check if
                                         * we're actually scrolling and handle it.
                                         * If no delegate, do nothing here and let
                                         * the row handle potential drag'n'drop or
                                         * context menu.
                                         */
                                        if (activeScrollDelegate != null) {
                                            if (activeScrollDelegate.isMoved()) {
                                                /*
                                                 * Prevent the row from handling
                                                 * touch move/end events (the
                                                 * delegate handles those) and from
                                                 * doing drag'n'drop or opening a
                                                 * context menu.
                                                 */
                                                touchStart = null;
                                            } else {
                                                /*
                                                 * Scrolling hasn't started, so
                                                 * cancel delegate and let the row
                                                 * handle potential drag'n'drop or
                                                 * context menu.
                                                 */
                                                activeScrollDelegate
                                                        .stopScrolling();
                                            }
                                        }
                                    }
                                }.schedule(TOUCHSCROLL_TIMEOUT);

                                if (contextTouchTimeout == null
                                        && actionKeys != null) {
                                    contextTouchTimeout = new Timer() {
                                        @Override
                                        public void run() {
                                            if (touchStart != null) {
                                                showContextMenu(touchStart);
                                                touchStart = null;
                                            }
                                        }
                                    };
                                }
                                if (contextTouchTimeout != null) {
                                    contextTouchTimeout.cancel();
                                    contextTouchTimeout
                                            .schedule(TOUCH_CONTEXT_MENU_TIMEOUT);
                                }
                            }
                            break;
                        case Event.ONMOUSEDOWN:
                            /*
                             * When getting a mousedown event, we must detect where
                             * the corresponding mouseup event if it's on a
                             * different part of the page.
                             */
                            lastMouseDownTarget = getElementTdOrTr(Util
                                    .getElementUnderMouse(event));
                            mouseUpPreviewMatched = false;
                            mouseUpEventPreviewRegistration = Event
                                    .addNativePreviewHandler(mouseUpPreviewHandler);

                            if (targetCellOrRowFound) {
                                setRowFocus(this);
                                ensureFocus();
                                if (dragmode != 0
                                        && (event.getButton() == NativeEvent.BUTTON_LEFT)) {
                                    startRowDrag(event, type, targetTdOrTr);

                                } else if (event.getCtrlKey()
                                        || event.getShiftKey()
                                        || event.getMetaKey()
                                        && isMultiSelectModeDefault()) {

                                    // Prevent default text selection in Firefox
                                    event.preventDefault();

                                    // Prevent default text selection in IE
                                    if (BrowserInfo.get().isIE()) {
                                        ((Element) event.getEventTarget().cast())
                                                .setPropertyJSO(
                                                "onselectstart",
                                                getPreventTextSelectionIEHack());
                                    }

                                    event.stopPropagation();
                                }
                            }
                            break;
                        case Event.ONMOUSEOUT:
                            break;
                        default:
                            break;
                    }
                }
                super.onBrowserEvent(event);
            }

            /**
             * Checks if is significant move.
             *
             * @param event the event
             * @return true, if is significant move
             */
            private boolean isSignificantMove(Event event) {
                if (touchStart == null) {
                    // no touch start
                    return false;
                }
                /*
                 * TODO calculate based on real distance instead of separate
                 * axis checks
                 */
                Touch touch = event.getChangedTouches().get(0);
                if (Math.abs(touch.getClientX() - touchStartX) > TouchScrollDelegate.SIGNIFICANT_MOVE_THRESHOLD) {
                    return true;
                }
                if (Math.abs(touch.getClientY() - touchStartY) > TouchScrollDelegate.SIGNIFICANT_MOVE_THRESHOLD) {
                    return true;
                }
                return false;
            }

            /**
             * Checks if the row represented by the row key has been selected.
             *
             * @param rowKey the row key
             * @return true, if successful
             */
            private boolean rowKeyIsSelected(int rowKey) {
                // Check single selections
                if (selectedRowKeys.contains("" + rowKey)) {
                    return true;
                }

                // Check range selections
                for (SelectionRange r : selectedRowRanges) {
                    if (r.inRange(getRenderedRowByKey("" + rowKey))) {
                        return true;
                    }
                }
                return false;
            }

            /**
             * Start row drag.
             *
             * @param event the event
             * @param type the type
             * @param targetTdOrTr the target td or tr
             */
            protected void startRowDrag(Event event, final int type,
                    Element targetTdOrTr) {
                VTransferable transferable = new VTransferable();
                transferable.setDragSource(ConnectorMap.get(client)
                        .getConnector(VExtCustomScrollTable.this));
                transferable.setData("itemId", "" + rowKey);
                NodeList<TableCellElement> cells = rowElement.getCells();
                for (int i = 0; i < cells.getLength(); i++) {
                    if (cells.getItem(i).isOrHasChild(targetTdOrTr)) {
                        HeaderCell headerCell = tHead.getHeaderCell(i);
                        transferable.setData("propertyId", headerCell.cid);
                        break;
                    }
                }

                VDragEvent ev = VDragAndDropManager.get().startDrag(
                        transferable, event, true);
                if (dragmode == DRAGMODE_MULTIROW && isMultiSelectModeAny()
                        && rowKeyIsSelected(rowKey)) {

                    // Create a drag image of ALL rows
                    ev.createDragImage(scrollBody.tBodyElement, true);

                    // Hide rows which are not selected
                    Element dragImage = ev.getDragImage();
                    int i = 0;
                    for (Iterator<Widget> iterator = scrollBody.iterator(); iterator
                            .hasNext();) {
                        VScrollTableRow next = (VScrollTableRow) iterator
                                .next();

                        Element child = (Element) dragImage.getChild(i++);

                        if (!rowKeyIsSelected(next.rowKey)) {
                            child.getStyle().setVisibility(Visibility.HIDDEN);
                        }
                    }
                } else {
                    ev.createDragImage(getElement(), true);
                }
                if (type == Event.ONMOUSEDOWN) {
                    event.preventDefault();
                }
                event.stopPropagation();
            }

            /**
             * Finds the TD that the event interacts with. Returns null if the
             * target of the event should not be handled. If the event target is
             * the row directly this method returns the TR element instead of
             * the TD.
             *
             * @param event the event
             * @return TD or TR element that the event targets (the actual event
             * target is this element or a child of it)
             */
            private Element getEventTargetTdOrTr(Event event) {
                final Element eventTarget = event.getEventTarget().cast();
                return getElementTdOrTr(eventTarget);
            }

            /**
             * Gets the element td or tr.
             *
             * @param element the element
             * @return the element td or tr
             */
            private Element getElementTdOrTr(Element element) {

                Widget widget = Util.findWidget(element, null);

                if (widget != this) {
                    /*
                     * This is a workaround to make Labels, read only TextFields
                     * and Embedded in a Table clickable (see #2688). It is
                     * really not a fix as it does not work with a custom read
                     * only components (not extending VLabel/VEmbedded).
                     */
                    while (widget != null && widget.getParent() != this) {
                        widget = widget.getParent();
                    }

                    if (!(widget instanceof VLabel)
                            && !(widget instanceof VEmbedded)
                            && !(widget instanceof VTextField && ((VTextField) widget)
                            .isReadOnly())) {
                        return null;
                    }
                }
                return getTdOrTr(element);
            }

            /**
             * Show context menu.
             *
             * @param event the event
             */
            @Override
            public void showContextMenu(Event event) {
                if (enabled && actionKeys != null) {
                    // Show context menu if there are registered action handlers
                    int left = Util.getTouchOrMouseClientX(event)
                            + Window.getScrollLeft();
                    int top = Util.getTouchOrMouseClientY(event)
                            + Window.getScrollTop();
                    showContextMenu(left, top);
                }
            }

            /**
             * Show context menu.
             *
             * @param left the left
             * @param top the top
             */
            public void showContextMenu(int left, int top) {
                VContextMenu menu = client.getContextMenu();
                contextMenu = new ContextMenuDetails(menu, getKey(), left, top);
                menu.showAt(this, left, top);
            }

            /**
             * Has the row been selected?.
             *
             * @return Returns true if selected, else false
             */
            public boolean isSelected() {
                return selected;
            }

            /**
             * Toggle the selection of the row.
             */
            public void toggleSelection() {
                selected = !selected;
                selectionChanged = true;
                if (selected) {
                    selectedRowKeys.add(String.valueOf(rowKey));
                    addStyleName("v-selected");
                } else {
                    removeStyleName("v-selected");
                    selectedRowKeys.remove(String.valueOf(rowKey));
                }
            }

            /**
             * Is called when a user clicks an item when holding SHIFT key down.
             * This will select a new range from the last focused row
             *
             * @param deselectPrevious Should the previous selected range be
             * deselected
             */
            private void toggleShiftSelection(boolean deselectPrevious) {

                /*
                 * Ensures that we are in multiselect mode and that we have a
                 * previous selection which was not a deselection
                 */
                if (isSingleSelectMode()) {
                    // No previous selection found
                    deselectAll();
                    toggleSelection();
                    return;
                }

                // Set the selectable range
                VScrollTableRow endRow = this;
                VScrollTableRow startRow = selectionRangeStart;
                if (startRow == null) {
                    startRow = focusedRow;
                    selectionRangeStart = focusedRow;
                    // If start row is null then we have a multipage selection
                    // from
                    // above
                    if (startRow == null) {
                        startRow = (VScrollTableRow) scrollBody.iterator()
                                .next();
                        setRowFocus(endRow);
                    }
                } else if (!startRow.isSelected()) {
                    // The start row is no longer selected (probably removed)
                    // and so we select from above
                    startRow = (VScrollTableRow) scrollBody.iterator().next();
                    setRowFocus(endRow);
                }

                // Deselect previous items if so desired
                if (deselectPrevious) {
                    deselectAll();
                }

                // we'll ensure GUI state from top down even though selection
                // was the opposite way
                if (!startRow.isBefore(endRow)) {
                    VScrollTableRow tmp = startRow;
                    startRow = endRow;
                    endRow = tmp;
                }
                SelectionRange range = new SelectionRange(startRow, endRow);

                for (Widget w : scrollBody) {
                    VScrollTableRow row = (VScrollTableRow) w;
                    if (range.inRange(row)) {
                        if (!row.isSelected()) {
                            row.toggleSelection();
                        }
                        selectedRowKeys.add(row.getKey());
                    }
                }

                // Add range
                if (startRow != endRow) {
                    selectedRowRanges.add(range);
                }
            }

            /*
             * (non-Javadoc)
             * 
             * @see com.vaadin.client.ui.IActionOwner#getActions ()
             */
            /**
             * Gets the actions.
             *
             * @return the actions
             */
            @Override
            public Action[] getActions() {
                if (actionKeys == null) {
                    return new Action[]{};
                }
                final Action[] actions = new Action[actionKeys.length];
                for (int i = 0; i < actions.length; i++) {
                    final String actionKey = actionKeys[i];
                    final TreeAction a = new TreeAction(this,
                            String.valueOf(rowKey), actionKey) {
                        @Override
                        public void execute() {
                            super.execute();
                            lazyRevertFocusToRow(VScrollTableRow.this);
                        }
                    };
                    a.setCaption(getActionCaption(actionKey));
                    a.setIconUrl(getActionIcon(actionKey));
                    actions[i] = a;
                }
                return actions;
            }

            /**
             * Gets the client.
             *
             * @return the client
             */
            @Override
            public ApplicationConnection getClient() {
                return client;
            }

            /**
             * Gets the paintable id.
             *
             * @return the paintable id
             */
            @Override
            public String getPaintableId() {
                return paintableId;
            }

            /**
             * Gets the col index of.
             *
             * @param child the child
             * @return the col index of
             */
            private int getColIndexOf(Widget child) {
                com.google.gwt.dom.client.Element widgetCell = child
                        .getElement().getParentElement().getParentElement();
                NodeList<TableCellElement> cells = rowElement.getCells();
                for (int i = 0; i < cells.getLength(); i++) {
                    if (cells.getItem(i) == widgetCell) {
                        return i;
                    }
                }
                return -1;
            }

            /**
             * Gets the widget for paintable.
             *
             * @return the widget for paintable
             */
            public Widget getWidgetForPaintable() {
                return this;
            }
        }

        /**
         * The Class VScrollTableGeneratedRow.
         */
        protected class VScrollTableGeneratedRow extends VScrollTableRow {

            /**
             * The span columns.
             */
            private boolean spanColumns;
            /**
             * The html content allowed.
             */
            private boolean htmlContentAllowed;

            /**
             * Instantiates a new v scroll table generated row.
             *
             * @param uidl the uidl
             * @param aligns the aligns
             */
            public VScrollTableGeneratedRow(UIDL uidl, char[] aligns) {
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
         * Ensure the component has a focus.
         *
         * TODO the current implementation simply always calls focus for the
         * component. In case the Table at some point implements focus/blur
         * listeners, this method needs to be evolved to conditionally call
         * focus only if not currently focused.
         */
        protected void ensureFocus() {
            if (!hasFocus) {
                scrollBodyPanel.setFocus(true);
            }

        }
    }

    /**
     * Deselects all items.
     */
    public void deselectAll() {
        for (Widget w : scrollBody) {
            VScrollTableRow row = (VScrollTableRow) w;
            if (row.isSelected()) {
                row.toggleSelection();
            }
        }
        // still ensure all selects are removed from (not necessary rendered)
        selectedRowKeys.clear();
        selectedRowRanges.clear();
        // also notify server that it clears all previous selections (the client
        // side does not know about the invisible ones)
        instructServerToForgetPreviousSelections();
    }

    /**
     * Used in multiselect mode when the client side knows that all selections
     * are in the next request.
     */
    private void instructServerToForgetPreviousSelections() {
        client.updateVariable(paintableId, "clearSelections", true, false);
    }

    /**
     * Determines the pagelength when the table height is fixed.
     */
    public void updatePageLength() {
        // Only update if visible and enabled
        if (!isVisible() || !enabled) {
            return;
        }

        if (scrollBody == null) {
            return;
        }

        if (isDynamicHeight()) {
            return;
        }

        int rowHeight = (int) Math.round(scrollBody.getRowHeight());
        int bodyH = scrollBodyPanel.getOffsetHeight();
        int rowsAtOnce = bodyH / rowHeight;
        boolean anotherPartlyVisible = ((bodyH % rowHeight) != 0);
        if (anotherPartlyVisible) {
            rowsAtOnce++;
        }
        if (pageLength != rowsAtOnce) {
            pageLength = rowsAtOnce;
            client.updateVariable(paintableId, "pagelength", pageLength, false);

            if (!rendering) {
                int currentlyVisible = scrollBody.getLastRendered()
                        - scrollBody.getFirstRendered();
                if (currentlyVisible < pageLength
                        && currentlyVisible < totalRows) {
                    // shake scrollpanel to fill empty space
                    scrollBodyPanel.setScrollPosition(scrollTop + 1);
                    scrollBodyPanel.setScrollPosition(scrollTop - 1);
                }

                sizeNeedsInit = true;
            }
        }

    }

    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public void updateWidth() {
        if (!isVisible()) {
            /*
             * Do not update size when the table is hidden as all column widths
             * will be set to zero and they won't be recalculated when the table
             * is set visible again (until the size changes again)
             */
            return;
        }
        if (!isDynamicWidth()) {
            int innerPixels = getOffsetWidth() - getBorderWidth();
            if (innerPixels < 0) {
                innerPixels = 0;
            }
            setContentWidth(innerPixels);

            // readjust undefined width columns
            triggerLazyColumnAdjustment(false);
            
        } else {
            sizeNeedsInit = true;

            // readjust undefined width columns
            triggerLazyColumnAdjustment(false);
        }

        /*
         * setting width may affect wheter the component has scrollbars -> needs
         * scrolling or not
         */
        setProperTabIndex();
    }
    /**
     * The Constant LAZY_COLUMN_ADJUST_TIMEOUT.
     */
    private static final int LAZY_COLUMN_ADJUST_TIMEOUT = 300;
    /**
     * The lazy adjust column widths.
     */
    private final Timer lazyAdjustColumnWidths = new Timer() {
        /**
         * Check for column widths, and available width, to see if we can fix
         * column widths "optimally". Doing this lazily to avoid expensive
         * calculation when resizing is not yet finished.
         */
        @Override
        public void run() {
            if (scrollBody == null) {
                // Try again later if we get here before scrollBody has been
                // initalized
                triggerLazyColumnAdjustment(false);
                return;
            }
            Iterator<Widget> headCells = tHead.iterator();

            int usedMinimumWidth = 0;
            int totalExplicitColumnsWidths = 0;
            float expandRatioDivider = 0;
            int colIndex = 0;

            int hierarchyColumnIndent = scrollBody.getMaxIndent();
            int hierarchyColumnIndex = getHierarchyColumnIndex();
            HeaderCell hierarchyHeaderInNeedOfFurtherHandling = null;

            while (headCells.hasNext()) {
                final HeaderCell hCell = (HeaderCell) headCells.next();
                boolean hasIndent = hierarchyColumnIndent > 0
                        && hCell.isHierarchyColumn();
                if (hCell.isDefinedWidth()) {
                    // get width without indent to find out whether adjustments
                    // are needed (requires special handling further ahead)
                    int w = hCell.getWidth();
                    if (hasIndent && w < hierarchyColumnIndent) {
                        // enforce indent if necessary
                        w = hierarchyColumnIndent;
                        hierarchyHeaderInNeedOfFurtherHandling = hCell;
                    }
                    totalExplicitColumnsWidths += w;
                    usedMinimumWidth += w;
                } else {
                    // natural width already includes indent if any
                    int naturalColumnWidth = hCell
                            .getNaturalColumnWidth(colIndex);
                    usedMinimumWidth += naturalColumnWidth;
                    expandRatioDivider += hCell.getExpandRatio();
                    if (hasIndent) {
                        hierarchyHeaderInNeedOfFurtherHandling = hCell;
                    }
                }
                colIndex++;
            }
            int availW = scrollBody.getAvailableWidth();
            // Hey IE, are you really sure about this?
            availW = scrollBody.getAvailableWidth();
            
            int visibleCellCount = tHead.getVisibleCellCount();
            int totalExtraWidth = scrollBody.getCellExtraWidth()
                    * visibleCellCount;
            if (willHaveScrollbars()) {
                totalExtraWidth += Util.getNativeScrollbarSize();
            }
            availW -= totalExtraWidth;
            int forceScrollBodyWidth = -1;

            int extraSpace = availW - usedMinimumWidth;
            if (extraSpace < 0) {
                if (getTotalRows() == 0) {
                    /*
                     * Too wide header combined with no rows in the table.
                     * 
                     * No horizontal scrollbars would be displayed because
                     * there's no rows that grows too wide causing the
                     * scrollBody container div to overflow. Must explicitely
                     * force a width to a scrollbar. (see #9187)
                     */
                    forceScrollBodyWidth = usedMinimumWidth + totalExtraWidth;
                }
                extraSpace = 0;
            }

            if (forceScrollBodyWidth > 0) {
                scrollBody.container.getStyle().setWidth(forceScrollBodyWidth,
                        Unit.PX);
            } else {
                // Clear width that might have been set to force horizontal
                // scrolling if there are no rows
                scrollBody.container.getStyle().clearWidth();
            }

            int totalUndefinedNaturalWidths = usedMinimumWidth
                    - totalExplicitColumnsWidths;

            if (hierarchyHeaderInNeedOfFurtherHandling != null
                    && !hierarchyHeaderInNeedOfFurtherHandling.isDefinedWidth()) {
                // ensure the cell gets enough space for the indent
                int w = hierarchyHeaderInNeedOfFurtherHandling
                        .getNaturalColumnWidth(hierarchyColumnIndex);
                int newSpace = Math.round(w + (float) extraSpace * (float) w
                        / totalUndefinedNaturalWidths);
                if (newSpace >= hierarchyColumnIndent) {
                    // no special handling required
                    hierarchyHeaderInNeedOfFurtherHandling = null;
                } else {
                    // treat as a defined width column of indent's width
                    totalExplicitColumnsWidths += hierarchyColumnIndent;
                    usedMinimumWidth -= w - hierarchyColumnIndent;
                    totalUndefinedNaturalWidths = usedMinimumWidth
                            - totalExplicitColumnsWidths;
                    expandRatioDivider += hierarchyHeaderInNeedOfFurtherHandling
                            .getExpandRatio();
                    extraSpace = Math.max(availW - usedMinimumWidth, 0);
                }
            }

            // we have some space that can be divided optimally
            HeaderCell hCell;
            colIndex = 0;
            headCells = tHead.iterator();
            int checksum = 0;
            while (headCells.hasNext()) {
                hCell = (HeaderCell) headCells.next();
                if (hCell.isResizing) {
                    continue;
                }
                if (!hCell.isDefinedWidth()) {
                    int w = hCell.getNaturalColumnWidth(colIndex);
                    int newSpace;
                    if (expandRatioDivider > 0) {
                        // divide excess space by expand ratios
                        newSpace = Math.round((w + extraSpace
                                * hCell.getExpandRatio() / expandRatioDivider));
                    } else {
                        if (hierarchyHeaderInNeedOfFurtherHandling == hCell) {
                            // still exists, so needs exactly the indent's width
                            newSpace = hierarchyColumnIndent;
                        } else if (totalUndefinedNaturalWidths != 0) {
                            // divide relatively to natural column widths
                            newSpace = Math.round(w + (float) extraSpace
                                    * (float) w / totalUndefinedNaturalWidths);
                        } else {
                            newSpace = w;
                        }
                    }
                    checksum += newSpace;
                    setColWidth(colIndex, newSpace, false);

                } else {
                    if (hierarchyHeaderInNeedOfFurtherHandling == hCell) {
                        // defined with enforced into indent width
                        checksum += hierarchyColumnIndent;
                        setColWidth(colIndex, hierarchyColumnIndent, false);
                    } else {
                        int cellWidth = hCell.getWidthWithIndent();
                        checksum += cellWidth;
                        if (hCell.isHierarchyColumn()) {
                            // update in case the indent has changed
                            // (not detectable earlier)
                            setColWidth(colIndex, cellWidth, true);
                        }
                    }
                }
                colIndex++;
            }

            if (extraSpace > 0 && checksum != availW) {
                /*
                 * There might be in some cases a rounding error of 1px when
                 * extra space is divided so if there is one then we give the
                 * first undefined column 1 more pixel
                 */
                headCells = tHead.iterator();
                colIndex = 0;
                while (headCells.hasNext()) {
                    HeaderCell hc = (HeaderCell) headCells.next();
                    if (!hc.isResizing && !hc.isDefinedWidth()) {
                        setColWidth(colIndex, hc.getWidthWithIndent() + availW
                                - checksum, false);
                        break;
                    }
                    colIndex++;
                }
            }
            if (showDoubleColHeaders) {
                boolean sendUpdate = false;
                Iterator<Widget> doubleheadCells = tdHead.iterator();
                ArrayList<DoubleHeaderCell> columns = new ArrayList<DoubleHeaderCell>();
                int j = 0;
                while (doubleheadCells.hasNext()) {
                    final DoubleHeaderCell hell = (DoubleHeaderCell) doubleheadCells.next();
                    int iniWid = hell.getWidth();
                    int w = getDoubleHeaderColExtraWidth(hell.getColKey());

                    hell.setWidth(w, true);
                    setDoubleHeaderColWidth(j, w, true, false);

                    columns.add(hell);
                    if (iniWid != hell.getWidth()) {
                        sendUpdate = true;
                    }
                    j++;
                }
                if (sendUpdate) {
                sendDoubleHeaderColumnWidthUpdates(columns);
                }
            }
            if (showTripleColHeaders) {
                boolean sendUpdate = false;
                Iterator<Widget> tripleheadCells = ttHead.iterator();
                ArrayList<TripleHeaderCell> tcolumns = new ArrayList<TripleHeaderCell>();
                int j = 0;
                while (tripleheadCells.hasNext()) {
                    final TripleHeaderCell hell = (TripleHeaderCell) tripleheadCells.next();
                    int iniWid = hell.getWidth();
                    int w = getTripleHeaderColExtraWidth(hell.getColKey());

                    hell.setWidth(w, true);
                    setTripleHeaderColWidth(j, w, true, false);

                    tcolumns.add(hell);
                    if (iniWid != hell.getWidth()) {
                        sendUpdate = true;
                    }
                    j++;
                }
                if (sendUpdate) {
                sendTripleHeaderColumnWidthUpdates(tcolumns);
                }
            }
            if (isDynamicHeight() && totalRows == pageLength) {
                // fix body height (may vary if lazy loading is offhorizontal
                // scrollbar appears/disappears)
                int bodyHeight = scrollBody.getRequiredHeight();
                boolean needsSpaceForHorizontalScrollbar = (availW < usedMinimumWidth);
                if (needsSpaceForHorizontalScrollbar) {
                    bodyHeight += Util.getNativeScrollbarSize();
                }
                int heightBefore = getOffsetHeight();
                scrollBodyPanel.setHeight(bodyHeight + "px");
                if (leftScrollBodyPanel != null) {

                    leftScrollBodyPanel.setHeight(bodyHeight + "px");
                }
                if (rightScrollBodyPanel != null) {
                    scrollBodyPanel.setHeight(rightScrollBodyPanel.getElement().getStyle().getHeight());
                }
                if (heightBefore != getOffsetHeight()) {
                    Util.notifyParentOfSizeChange(VExtCustomScrollTable.this,
                            rendering);
                }
            }

            Util.runWebkitOverflowAutoFixDeferred(scrollBodyPanel.getElement());

            forceRealignColumnHeaders();
            client.sendPendingVariableChanges();
        }
    };

    /**
     * Force realign column headers.
     */
    private void forceRealignColumnHeaders() {
        if (BrowserInfo.get().isIE()) {
            /*
             * IE does not fire onscroll event if scroll position is reverted to
             * 0 due to the content element size growth. Ensure headers are in
             * sync with content manually. Safe to use null event as we don't
             * actually use the event object in listener.
             */
            onScroll(null);
        }
    }

    /**
     * helper to set pixel size of head and body part.
     *
     * @param pixels the new content width
     */
    protected void setContentWidth(int pixels) {
        tHead.setWidth(pixels + "px");
        scrollBodyPanel.setWidth(pixels + "px");
        tFoot.setWidth(pixels + "px");
        tdHead.setWidth(pixels + "px");
        ttHead.setWidth(pixels + "px");
    }
    /**
     * The border width.
     */
    private int borderWidth = -1;

    /**
     * Gets the border width.
     *
     * @return border left + border right
     */
    private int getBorderWidth() {
        if (borderWidth < 0) {
            borderWidth = Util.measureHorizontalPaddingAndBorder(
                    scrollBodyPanel.getElement(), 2);
            if (borderWidth < 0) {
                borderWidth = 0;
            }
        }
        return borderWidth;
    }
    /**
     * Ensures scrollable area is properly sized. This method is used when fixed
     * size is used.
     */
    protected int containerHeight;

    /**
     * Sets the container height.
     */
    protected void setContainerHeight() {
        if (!isDynamicHeight()) {

            /*
             * Android 2.3 cannot measure the height of the inline-block
             * properly, and will return the wrong offset height. So for android
             * 2.3 we set the element to a block element while measuring and
             * then restore it which yields the correct result. #11331
             */
            if (BrowserInfo.get().isAndroid23()) {
                getElement().getStyle().setDisplay(Display.BLOCK);
            }

            containerHeight = getOffsetHeight();
            containerHeight -= showColHeaders ? tHead.getOffsetHeight() : 0;
            containerHeight -= showDoubleColHeaders ? tdHead.getOffsetHeight() : 0;
            containerHeight -= showTripleColHeaders ? ttHead.getOffsetHeight() : 0;
            containerHeight -= tFoot.getOffsetHeight();
            containerHeight -= getContentAreaBorderHeight();
            containerHeight -= getFilterHieght();
            if (containerHeight < 0) {
                containerHeight = 0;
            }

            scrollBodyPanel.setHeight(containerHeight + "px");
            if (rightScrollBodyPanel != null) {
                scrollBodyPanel.setHeight(rightScrollBodyPanel.getOffsetHeight() + "px");
                scrollBody.table.getStyle().setWidth(scrollBody.freezeSpacer.getOffsetWidth(), Unit.PX);
            }
            if (leftScrollBodyPanel != null) {
                leftScrollBodyPanel.setHeight(containerHeight + "px");
            }
            if (BrowserInfo.get().isAndroid23()) {
                getElement().getStyle().clearDisplay();
            }
        }
    }

    /**
     * Gets the filter hieght.
     *
     * @return the filter hieght
     */
    protected int getFilterHieght() {
        return 0;
    }
    /**
     * The content area border height.
     */
    private int contentAreaBorderHeight = -1;
    /**
     * The scroll left.
     */
    protected int scrollLeft;
    /**
     * The scroll top.
     */
    private int scrollTop;
    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public VScrollTableDropHandler dropHandler;
    /**
     * The nav key down.
     */
    private boolean navKeyDown;
    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public boolean multiselectPending;

    /**
     * Gets the content area border height.
     *
     * @return border top + border bottom of the scrollable area of table
     */
    protected int getContentAreaBorderHeight() {
        if (contentAreaBorderHeight < 0) {

            scrollBodyPanel.getElement().getStyle()
                    .setOverflow(Overflow.HIDDEN);
            int oh = scrollBodyPanel.getOffsetHeight();
            int ch = scrollBodyPanel.getElement()
                    .getPropertyInt("clientHeight");
            contentAreaBorderHeight = oh - ch;
            scrollBodyPanel.getElement().getStyle().setOverflow(Overflow.AUTO);
        }
        return contentAreaBorderHeight;
    }

    /**
     * Sets the height.
     *
     * @param height the new height
     */
    @Override
    public void setHeight(String height) {
        if (height.length() == 0
                && getElement().getStyle().getHeight().length() != 0) {
            /*
             * Changing from defined to undefined size -> should do a size init
             * to take page length into account again
             */
            sizeNeedsInit = true;
        }
        super.setHeight(height);
    }

    /**
     * For internal use only. May be removed or replaced in the future.
     */
    public void updateHeight() {
        setContainerHeight();

        if (initializedAndAttached) {
            updatePageLength();
        }
        if (!rendering) {
            // Webkit may sometimes get an odd rendering bug (white space
            // between header and body), see bug #3875. Running
            // overflow hack here to shake body element a bit.
            // We must run the fix as a deferred command to prevent it from
            // overwriting the scroll position with an outdated value, see
            // #7607.
            Util.runWebkitOverflowAutoFixDeferred(scrollBodyPanel.getElement());
        }
        triggerLazyColumnAdjustment(false);

        /*
         * setting height may affect wheter the component has scrollbars ->
         * needs scrolling or not
         */
        setProperTabIndex();

    }

    /*
     * Overridden due Table might not survive of visibility change (scroll pos
     * lost). Example ITabPanel just set contained components invisible and back
     * when changing tabs.
     */
    /**
     * Sets the visible.
     *
     * @param visible the new visible
     */
    @Override
    public void setVisible(boolean visible) {
        if (isVisible() != visible) {
            super.setVisible(visible);
            if (initializedAndAttached) {
                if (visible) {
                    Scheduler.get().scheduleDeferred(new Command() {
                        @Override
                        public void execute() {
                            scrollBodyPanel
                                    .setScrollPosition(measureRowHeightOffset(firstRowInViewPort));
                        }
                    });
                }
            }
        }
    }

    /**
     * Helper function to build html snippet for column or row headers.
     *
     * @param uidl possibly with values caption and icon
     * @return html snippet containing possibly an icon + caption text
     */
    protected String buildCaptionHtmlSnippet(UIDL uidl) {
        String s = uidl.hasAttribute("caption") ? uidl
                .getStringAttribute("caption") : "";
        if (uidl.hasAttribute("icon")) {
            Element icon = DOM.createImg();
            icon.setClassName("v-icon");
            icon.setPropertyString("alt", "icon");
            icon.setPropertyString("src", Util.escapeAttribute(client.translateVaadinUri(uidl.getStringAttribute("icon"))));
            s = icon.toString() + s;
        }
        return s;
    }

    /**
     * This method has logic which rows needs to be requested from server when
     * user scrolls.
     *
     * @param event the event
     */
    @Override
    public void onScroll(ScrollEvent event) {
        // Do not handle scroll events while there is scroll initiated from
        // server side which is not yet executed (#11454)
        if (isLazyScrollerActive()) {
            return;
        }

        scrollLeft = scrollBodyPanel.getElement().getScrollLeft();
        scrollTop = scrollBodyPanel.getScrollPosition();
        /*
         * #6970 - IE sometimes fires scroll events for a detached table.
         * 
         * FIXME initializedAndAttached should probably be renamed - its name
         * doesn't seem to reflect its semantics. onDetach() doesn't set it to
         * false, and changing that might break something else, so we need to
         * check isAttached() separately.
         */
        if (!initializedAndAttached || !isAttached()) {
            return;
        }
        if (!enabled) {
            scrollBodyPanel
                    .setScrollPosition(measureRowHeightOffset(firstRowInViewPort));
            return;
        }
        if (leftDepandantPane != null) {
            leftScrollBodyPanel.setScrollPosition(scrollBodyPanel.getScrollPosition());

        }
        if (rightTable != null) {
            scrollBody.freezeSpacer.addClassName("left-freeze-space");
        }
        rowRequestHandler.cancel();

        if (BrowserInfo.get().isSafari() && event != null && scrollTop == 0) {
            // due to the webkitoverflowworkaround, top may sometimes report 0
            // for webkit, although it really is not. Expecting to have the
            // correct
            // value available soon.
            Scheduler.get().scheduleDeferred(new Command() {
                @Override
                public void execute() {
                    onScroll(null);
                }
            });
            return;
        }

        // fix headers horizontal scrolling
        tHead.setHorizontalScrollPosition(scrollLeft);
        // fix double headers horizontal scrolling
        tdHead.setHorizontalScrollPosition(scrollLeft);
        // fix triple headers horizontal scrolling
        ttHead.setHorizontalScrollPosition(scrollLeft);
        // fix footers horizontal scrolling
        tFoot.setHorizontalScrollPosition(scrollLeft);

        if (totalRows == 0) {
            // No rows, no need to fetch new rows
            return;
        }

        firstRowInViewPort = calcFirstRowInViewPort();
        int maxFirstRow = totalRows - pageLength;
        if (firstRowInViewPort > maxFirstRow && maxFirstRow >= 0) {
            firstRowInViewPort = maxFirstRow;
        }

        int postLimit = (int) (firstRowInViewPort + (pageLength - 1) + pageLength
                * cache_react_rate);
        if (postLimit > totalRows - 1) {
            postLimit = totalRows - 1;
        }
        int preLimit = (int) (firstRowInViewPort - pageLength
                * cache_react_rate);
        if (preLimit < 0) {
            preLimit = 0;
        }
        final int lastRendered = scrollBody.getLastRendered();
        final int firstRendered = scrollBody.getFirstRendered();

        if (postLimit <= lastRendered && preLimit >= firstRendered) {
            // we're within no-react area, no need to request more rows
            // remember which firstvisible we requested, in case the server has
            // a differing opinion
            lastRequestedFirstvisible = firstRowInViewPort;
            client.updateVariable(paintableId, "firstvisible",
                    firstRowInViewPort, false);
            return;
        }

        if (allRenderedRowsAreNew()) {
            // need a totally new set of rows
            rowRequestHandler
                    .setReqFirstRow((firstRowInViewPort - (int) (pageLength * cache_rate)));
            int last = firstRowInViewPort + (int) (cache_rate * pageLength)
                    + pageLength - 1;
            if (last >= totalRows) {
                last = totalRows - 1;
            }
            rowRequestHandler.setReqRows(last
                    - rowRequestHandler.getReqFirstRow() + 1);
            updatedReqRows = false;
            rowRequestHandler.deferRowFetch();
            return;
        }
        if (preLimit < firstRendered) {
            // need some rows to the beginning of the rendered area
            rowRequestHandler
                    .setReqFirstRow((int) (firstRowInViewPort - pageLength
                    * cache_rate));
            rowRequestHandler.setReqRows(firstRendered
                    - rowRequestHandler.getReqFirstRow());
            rowRequestHandler.deferRowFetch();

            return;
        }
        if (postLimit > lastRendered) {
            // need some rows to the end of the rendered area
            int reqRows = (int) ((firstRowInViewPort + pageLength + pageLength
                    * cache_rate) - lastRendered);
            rowRequestHandler.triggerRowFetch(lastRendered + 1, reqRows);
        }
    }

    /**
     * All rendered rows are new.
     *
     * @return true, if successful
     */
    private boolean allRenderedRowsAreNew() {
        int firstRowInViewPort = calcFirstRowInViewPort();
        int firstRendered = scrollBody.getFirstRendered();
        int lastRendered = scrollBody.getLastRendered();
        return (firstRowInViewPort - pageLength * cache_rate > lastRendered || firstRowInViewPort
                + pageLength + pageLength * cache_rate < firstRendered);
    }

    /**
     * Calc first row in view port.
     *
     * @return the int
     */
    protected int calcFirstRowInViewPort() {
        return (int) Math.ceil(scrollTop / scrollBody.getRowHeight());
    }

    /**
     * Gets the drop handler.
     *
     * @return the drop handler
     */
    @Override
    public VScrollTableDropHandler getDropHandler() {
        return dropHandler;
    }

    /**
     * The Class TableDDDetails.
     */
    private static class TableDDDetails {

        /**
         * The overkey.
         */
        int overkey = -1;
        /**
         * The drop location.
         */
        VerticalDropLocation dropLocation;
        /**
         * The colkey.
         */
        String colkey;

        /**
         * Equals.
         *
         * @param obj the obj
         * @return true, if successful
         */
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof TableDDDetails) {
                TableDDDetails other = (TableDDDetails) obj;
                return dropLocation == other.dropLocation
                        && overkey == other.overkey
                        && ((colkey != null && colkey.equals(other.colkey)) || (colkey == null && other.colkey == null));
            }
            return false;
        }
        //
        // public int hashCode() {
        // return overkey;
        // }
    }

    /**
     * The Class VScrollTableDropHandler.
     */
    public class VScrollTableDropHandler extends VAbstractDropHandler {

        /**
         * The Constant ROWSTYLEBASE.
         */
        private static final String ROWSTYLEBASE = "v-table-row-drag-";
        /**
         * The drop details.
         */
        private TableDDDetails dropDetails;
        /**
         * The last emphasized.
         */
        private TableDDDetails lastEmphasized;

        /**
         * Drag enter.
         *
         * @param drag the drag
         */
        @Override
        public void dragEnter(VDragEvent drag) {
            updateDropDetails(drag);
            super.dragEnter(drag);
        }

        /**
         * Update drop details.
         *
         * @param drag the drag
         */
        private void updateDropDetails(VDragEvent drag) {
            dropDetails = new TableDDDetails();
            Element elementOver = drag.getElementOver();

            Class<? extends Widget> clazz = getRowClass();
            VScrollTableRow row = null;
            if (clazz != null) {
                row = Util.findWidget(elementOver, clazz);
            }
            if (row != null) {
                dropDetails.overkey = row.rowKey;
                Element tr = row.getElement();
                Element element = elementOver;
                while (element != null && element.getParentElement() != tr) {
                    element = element.getParentElement();
                }
                int childIndex = DOM.getChildIndex(tr, element);
                dropDetails.colkey = tHead.getHeaderCell(childIndex)
                        .getColKey();
                dropDetails.dropLocation = DDUtil.getVerticalDropLocation(
                        row.getElement(), drag.getCurrentGwtEvent(), 0.2);
            }

            drag.getDropDetails().put("itemIdOver", dropDetails.overkey + "");
            drag.getDropDetails().put(
                    "detail",
                    dropDetails.dropLocation != null ? dropDetails.dropLocation
                    .toString() : null);

        }

        /**
         * Gets the row class.
         *
         * @return the row class
         */
        private Class<? extends Widget> getRowClass() {
            // get the row type this way to make dd work in derived
            // implementations
            Iterator<Widget> iterator = scrollBody.iterator();
            if (iterator.hasNext()) {
                return iterator.next().getClass();
            } else {
                return null;
            }
        }

        /**
         * Drag over.
         *
         * @param drag the drag
         */
        @Override
        public void dragOver(VDragEvent drag) {
            TableDDDetails oldDetails = dropDetails;
            updateDropDetails(drag);
            if (!oldDetails.equals(dropDetails)) {
                deEmphasis();
                final TableDDDetails newDetails = dropDetails;
                VAcceptCallback cb = new VAcceptCallback() {
                    @Override
                    public void accepted(VDragEvent event) {
                        if (newDetails.equals(dropDetails)) {
                            dragAccepted(event);
                        }
                        /*
                         * Else new target slot already defined, ignore
                         */
                    }
                };
                validate(cb, drag);
            }
        }

        /**
         * Drag leave.
         *
         * @param drag the drag
         */
        @Override
        public void dragLeave(VDragEvent drag) {
            deEmphasis();
            super.dragLeave(drag);
        }

        /**
         * Drop.
         *
         * @param drag the drag
         * @return true, if successful
         */
        @Override
        public boolean drop(VDragEvent drag) {
            deEmphasis();
            return super.drop(drag);
        }

        /**
         * De emphasis.
         */
        private void deEmphasis() {
            UIObject.setStyleName(getElement(),
                    getStylePrimaryName() + "-drag", false);
            if (lastEmphasized == null) {
                return;
            }
            for (Widget w : scrollBody.renderedRows) {
                VScrollTableRow row = (VScrollTableRow) w;
                if (lastEmphasized != null
                        && row.rowKey == lastEmphasized.overkey) {
                    String stylename = ROWSTYLEBASE
                            + lastEmphasized.dropLocation.toString()
                            .toLowerCase();
                    VScrollTableRow.setStyleName(row.getElement(), stylename,
                            false);
                    lastEmphasized = null;
                    return;
                }
            }
        }

        /**
         * TODO needs different drop modes ?? (on cells, on rows), now only
         * supports rows.
         *
         * @param details the details
         */
        private void emphasis(TableDDDetails details) {
            deEmphasis();
            UIObject.setStyleName(getElement(),
                    getStylePrimaryName() + "-drag", true);
            // iterate old and new emphasized row
            for (Widget w : scrollBody.renderedRows) {
                VScrollTableRow row = (VScrollTableRow) w;
                if (details != null && details.overkey == row.rowKey) {
                    String stylename = ROWSTYLEBASE
                            + details.dropLocation.toString().toLowerCase();
                    VScrollTableRow.setStyleName(row.getElement(), stylename,
                            true);
                    lastEmphasized = details;
                    return;
                }
            }
        }

        /**
         * Drag accepted.
         *
         * @param drag the drag
         */
        @Override
        protected void dragAccepted(VDragEvent drag) {
            emphasis(dropDetails);
        }

        /**
         * Gets the connector.
         *
         * @return the connector
         */
        @Override
        public ComponentConnector getConnector() {
            return ConnectorMap.get(client).getConnector(
                    VExtCustomScrollTable.this);
        }

        /**
         * Gets the application connection.
         *
         * @return the application connection
         */
        @Override
        public ApplicationConnection getApplicationConnection() {
            return client;
        }
    }

    /**
     * Gets the focused row.
     *
     * @return the focused row
     */
    protected VScrollTableRow getFocusedRow() {
        return focusedRow;
    }

    /**
     * Moves the selection head to a specific row.
     *
     * @param row The row to where the selection head should move
     * @return Returns true if focus was moved successfully, else false
     */
    public boolean setRowFocus(VScrollTableRow row) {

        if (!isSelectable()) {
            return false;
        }

        // Remove previous selection
        if (focusedRow != null && focusedRow != row) {
            focusedRow.removeStyleName(getStylePrimaryName() + "-focus");
        }

        if (row != null) {

            // Apply focus style to new selection
            row.addStyleName(getStylePrimaryName() + "-focus");

            /*
             * Trying to set focus on already focused row
             */
            if (row == focusedRow) {
                return false;
            }

            // Set new focused row
            focusedRow = row;

            ensureRowIsVisible(row);

            return true;
        }

        return false;
    }

    /**
     * Ensures that the row is visible.
     *
     * @param row The row to ensure is visible
     */
    private void ensureRowIsVisible(VScrollTableRow row) {
        if (BrowserInfo.get().isTouchDevice()) {
            // Skip due to android devices that have broken scrolltop will may
            // get odd scrolling here.
            return;
        }
        Util.scrollIntoViewVertically(row.getElement());
    }

    /**
     * Handles the keyboard events handled by the table.
     *
     * @param keycode the keycode
     * @param ctrl the ctrl
     * @param shift the shift
     * @return true iff the navigation event was handled
     */
    protected boolean handleNavigation(int keycode, boolean ctrl, boolean shift) {
        if (keycode == KeyCodes.KEY_TAB || keycode == KeyCodes.KEY_SHIFT) {
            // Do not handle tab key
            return false;
        }

        // Down navigation
        if (!isSelectable() && keycode == getNavigationDownKey()) {
            scrollBodyPanel.setScrollPosition(scrollBodyPanel
                    .getScrollPosition() + scrollingVelocity);
            return true;
        } else if (keycode == getNavigationDownKey()) {
            if (isMultiSelectModeAny() && moveFocusDown()) {
                selectFocusedRow(ctrl, shift);

            } else if (isSingleSelectMode() && !shift && moveFocusDown()) {
                selectFocusedRow(ctrl, shift);
            }
            return true;
        }

        // Up navigation
        if (!isSelectable() && keycode == getNavigationUpKey()) {
            scrollBodyPanel.setScrollPosition(scrollBodyPanel
                    .getScrollPosition() - scrollingVelocity);
            return true;
        } else if (keycode == getNavigationUpKey()) {
            if (isMultiSelectModeAny() && moveFocusUp()) {
                selectFocusedRow(ctrl, shift);
            } else if (isSingleSelectMode() && !shift && moveFocusUp()) {
                selectFocusedRow(ctrl, shift);
            }
            return true;
        }

        if (keycode == getNavigationLeftKey()) {
            // Left navigation
            scrollBodyPanel.setHorizontalScrollPosition(scrollBodyPanel
                    .getHorizontalScrollPosition() - scrollingVelocity);
            return true;

        } else if (keycode == getNavigationRightKey()) {
            // Right navigation
            scrollBodyPanel.setHorizontalScrollPosition(scrollBodyPanel
                    .getHorizontalScrollPosition() + scrollingVelocity);
            return true;
        }

        // Select navigation
        if (isSelectable() && keycode == getNavigationSelectKey()) {
            if (isSingleSelectMode()) {
                boolean wasSelected = focusedRow.isSelected();
                deselectAll();
                if (!wasSelected || !nullSelectionAllowed) {
                    focusedRow.toggleSelection();
                }
            } else {
                focusedRow.toggleSelection();
                removeRowFromUnsentSelectionRanges(focusedRow);
            }

            sendSelectedRows();
            return true;
        }

        // Page Down navigation
        if (keycode == getNavigationPageDownKey()) {
            if (isSelectable()) {
                /*
                 * If selectable we plagiate MSW behaviour: first scroll to the
                 * end of current view. If at the end, scroll down one page
                 * length and keep the selected row in the bottom part of
                 * visible area.
                 */
                if (!isFocusAtTheEndOfTable()) {
                    VScrollTableRow lastVisibleRowInViewPort = scrollBody
                            .getRowByRowIndex(firstRowInViewPort
                            + getFullyVisibleRowCount() - 1);
                    if (lastVisibleRowInViewPort != null
                            && lastVisibleRowInViewPort != focusedRow) {
                        // focused row is not at the end of the table, move
                        // focus and select the last visible row
                        setRowFocus(lastVisibleRowInViewPort);
                        selectFocusedRow(ctrl, shift);
                        sendSelectedRows();
                    } else {
                        int indexOfToBeFocused = focusedRow.getIndex()
                                + getFullyVisibleRowCount();
                        if (indexOfToBeFocused >= totalRows) {
                            indexOfToBeFocused = totalRows - 1;
                        }
                        VScrollTableRow toBeFocusedRow = scrollBody
                                .getRowByRowIndex(indexOfToBeFocused);

                        if (toBeFocusedRow != null) {
                            /*
                             * if the next focused row is rendered
                             */
                            setRowFocus(toBeFocusedRow);
                            selectFocusedRow(ctrl, shift);
                            // TODO needs scrollintoview ?
                            sendSelectedRows();
                        } else {
                            // scroll down by pixels and return, to wait for
                            // new rows, then select the last item in the
                            // viewport
                            selectLastItemInNextRender = true;
                            multiselectPending = shift;
                            scrollByPagelenght(1);
                        }
                    }
                }
            } else {
                /* No selections, go page down by scrolling */
                scrollByPagelenght(1);
            }
            return true;
        }

        // Page Up navigation
        if (keycode == getNavigationPageUpKey()) {
            if (isSelectable()) {
                /*
                 * If selectable we plagiate MSW behaviour: first scroll to the
                 * end of current view. If at the end, scroll down one page
                 * length and keep the selected row in the bottom part of
                 * visible area.
                 */
                if (!isFocusAtTheBeginningOfTable()) {
                    VScrollTableRow firstVisibleRowInViewPort = scrollBody
                            .getRowByRowIndex(firstRowInViewPort);
                    if (firstVisibleRowInViewPort != null
                            && firstVisibleRowInViewPort != focusedRow) {
                        // focus is not at the beginning of the table, move
                        // focus and select the first visible row
                        setRowFocus(firstVisibleRowInViewPort);
                        selectFocusedRow(ctrl, shift);
                        sendSelectedRows();
                    } else {
                        int indexOfToBeFocused = focusedRow.getIndex()
                                - getFullyVisibleRowCount();
                        if (indexOfToBeFocused < 0) {
                            indexOfToBeFocused = 0;
                        }
                        VScrollTableRow toBeFocusedRow = scrollBody
                                .getRowByRowIndex(indexOfToBeFocused);

                        if (toBeFocusedRow != null) { // if the next focused row
                            // is rendered
                            setRowFocus(toBeFocusedRow);
                            selectFocusedRow(ctrl, shift);
                            // TODO needs scrollintoview ?
                            sendSelectedRows();
                        } else {
                            // unless waiting for the next rowset already
                            // scroll down by pixels and return, to wait for
                            // new rows, then select the last item in the
                            // viewport
                            selectFirstItemInNextRender = true;
                            multiselectPending = shift;
                            scrollByPagelenght(-1);
                        }
                    }
                }
            } else {
                /* No selections, go page up by scrolling */
                scrollByPagelenght(-1);
            }

            return true;
        }

        // Goto start navigation
        if (keycode == getNavigationStartKey()) {
            scrollBodyPanel.setScrollPosition(0);
            if (isSelectable()) {
                if (focusedRow != null && focusedRow.getIndex() == 0) {
                    return false;
                } else {
                    VScrollTableRow rowByRowIndex = (VScrollTableRow) scrollBody
                            .iterator().next();
                    if (rowByRowIndex.getIndex() == 0) {
                        setRowFocus(rowByRowIndex);
                        selectFocusedRow(ctrl, shift);
                        sendSelectedRows();
                    } else {
                        // first row of table will come in next row fetch
                        if (ctrl) {
                            focusFirstItemInNextRender = true;
                        } else {
                            selectFirstItemInNextRender = true;
                            multiselectPending = shift;
                        }
                    }
                }
            }
            return true;
        }

        // Goto end navigation
        if (keycode == getNavigationEndKey()) {
            scrollBodyPanel.setScrollPosition(scrollBody.getOffsetHeight());
            if (isSelectable()) {
                final int lastRendered = scrollBody.getLastRendered();
                if (lastRendered + 1 == totalRows) {
                    VScrollTableRow rowByRowIndex = scrollBody
                            .getRowByRowIndex(lastRendered);
                    if (focusedRow != rowByRowIndex) {
                        setRowFocus(rowByRowIndex);
                        selectFocusedRow(ctrl, shift);
                        sendSelectedRows();
                    }
                } else {
                    if (ctrl) {
                        focusLastItemInNextRender = true;
                    } else {
                        selectLastItemInNextRender = true;
                        multiselectPending = shift;
                    }
                }
            }
            return true;
        }

        return false;
    }

    /**
     * Checks if is focus at the beginning of table.
     *
     * @return true, if is focus at the beginning of table
     */
    private boolean isFocusAtTheBeginningOfTable() {
        return focusedRow.getIndex() == 0;
    }

    /**
     * Checks if is focus at the end of table.
     *
     * @return true, if is focus at the end of table
     */
    private boolean isFocusAtTheEndOfTable() {
        return focusedRow.getIndex() + 1 >= totalRows;
    }

    /**
     * Gets the fully visible row count.
     *
     * @return the fully visible row count
     */
    private int getFullyVisibleRowCount() {
        return (int) (scrollBodyPanel.getOffsetHeight() / scrollBody
                .getRowHeight());
    }

    /**
     * Scroll by pagelenght.
     *
     * @param i the i
     */
    private void scrollByPagelenght(int i) {
        int pixels = i * scrollBodyPanel.getOffsetHeight();
        int newPixels = scrollBodyPanel.getScrollPosition() + pixels;
        if (newPixels < 0) {
            newPixels = 0;
        } // else if too high, NOP (all know browsers accept illegally big
        // values here)
        scrollBodyPanel.setScrollPosition(newPixels);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.event.dom.client.FocusHandler#onFocus(com.google.gwt.event
     * .dom.client.FocusEvent)
     */
    /**
     * On focus.
     *
     * @param event the event
     */
    @Override
    public void onFocus(FocusEvent event) {
        if (isFocusable()) {
            hasFocus = true;

            // Focus a row if no row is in focus
            if (focusedRow == null) {
                focusRowFromBody();
            } else {
                setRowFocus(focusedRow);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.event.dom.client.BlurHandler#onBlur(com.google.gwt.event
     * .dom.client.BlurEvent)
     */
    /**
     * On blur.
     *
     * @param event the event
     */
    @Override
    public void onBlur(BlurEvent event) {
        hasFocus = false;
        navKeyDown = false;

        if (BrowserInfo.get().isIE()) {
            /*
             * IE sometimes moves focus to a clicked table cell... (#7965)
             * ...and sometimes it sends blur events even though the focus
             * handler is still active. (#10464)
             */
            Element focusedElement = Util.getIEFocusedElement();
            if (Util.getConnectorForElement(client, getParent(), focusedElement) == this
                    && focusedElement != null
                    && focusedElement != scrollBodyPanel.getFocusElement()) {
                /*
                 * Steal focus back to the focus handler if it was moved to some
                 * other part of the table. Avoid stealing focus in other cases.
                 */
                focus();
                return;
            }
        }

        if (isFocusable()) {
            // Unfocus any row
            setRowFocus(null);
        }
    }

    /**
     * Removes a key from a range if the key is found in a selected range.
     *
     * @param row the row
     */
    private void removeRowFromUnsentSelectionRanges(VScrollTableRow row) {
        Collection<SelectionRange> newRanges = null;
        for (Iterator<SelectionRange> iterator = selectedRowRanges.iterator(); iterator
                .hasNext();) {
            SelectionRange range = iterator.next();
            if (range.inRange(row)) {
                // Split the range if given row is in range
                Collection<SelectionRange> splitranges = range.split(row);
                if (newRanges == null) {
                    newRanges = new ArrayList<SelectionRange>();
                }
                newRanges.addAll(splitranges);
                iterator.remove();
            }
        }
        if (newRanges != null) {
            selectedRowRanges.addAll(newRanges);
        }
    }

    /**
     * Can the Table be focused?.
     *
     * @return True if the table can be focused, else false
     */
    public boolean isFocusable() {
        if (scrollBody != null && enabled) {
            return !(!hasHorizontalScrollbar() && !hasVerticalScrollbar() && !isSelectable());
        }
        return false;
    }

    /**
     * Checks for horizontal scrollbar.
     *
     * @return true, if successful
     */
    private boolean hasHorizontalScrollbar() {
        return scrollBody.getOffsetWidth() > scrollBodyPanel.getOffsetWidth();
    }

    /**
     * Checks for vertical scrollbar.
     *
     * @return true, if successful
     */
    private boolean hasVerticalScrollbar() {
        return scrollBody.getOffsetHeight() > scrollBodyPanel.getOffsetHeight();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.vaadin.client.Focusable#focus()
     */
    /**
     * Focus.
     */
    @Override
    public void focus() {
        if (isFocusable()) {
            scrollBodyPanel.focus();
        }
    }

    /**
     * Sets the proper tabIndex for scrollBodyPanel (the focusable elemen in the
     * component).
     * <p>
     * If the component has no explicit tabIndex a zero is given (default
     * tabbing order based on dom hierarchy) or -1 if the component does not
     * need to gain focus. The component needs no focus if it has no scrollabars
     * (not scrollable) and not selectable. Note that in the future shortcut
     * actions may need focus.
     * <p>
     * For internal use only. May be removed or replaced in the future.
     */
    public void setProperTabIndex() {
        int storedScrollTop = 0;
        int storedScrollLeft = 0;

        if (BrowserInfo.get().getOperaVersion() >= 11) {
            // Workaround for Opera scroll bug when changing tabIndex (#6222)
            storedScrollTop = scrollBodyPanel.getScrollPosition();
            storedScrollLeft = scrollBodyPanel.getHorizontalScrollPosition();
        }

        if (tabIndex == 0 && !isFocusable()) {
            scrollBodyPanel.setTabIndex(-1);
        } else {
            scrollBodyPanel.setTabIndex(tabIndex);
        }

        if (BrowserInfo.get().getOperaVersion() >= 11) {
            // Workaround for Opera scroll bug when changing tabIndex (#6222)
            scrollBodyPanel.setScrollPosition(storedScrollTop);
            scrollBodyPanel.setHorizontalScrollPosition(storedScrollLeft);
        }
    }

    /**
     * Start scrolling velocity timer.
     */
    public void startScrollingVelocityTimer() {
        if (scrollingVelocityTimer == null) {
            scrollingVelocityTimer = new Timer() {
                @Override
                public void run() {
                    scrollingVelocity++;
                }
            };
            scrollingVelocityTimer.scheduleRepeating(100);
        }
    }

    /**
     * Cancel scrolling velocity timer.
     */
    public void cancelScrollingVelocityTimer() {
        if (scrollingVelocityTimer != null) {
            // Remove velocityTimer if it exists and the Table is disabled
            scrollingVelocityTimer.cancel();
            scrollingVelocityTimer = null;
            scrollingVelocity = 10;
        }
    }

    /**
     * Checks if is navigation key.
     *
     * @param keyCode the key code
     * @return true if the given keyCode is used by the table for navigation
     */
    private boolean isNavigationKey(int keyCode) {
        return keyCode == getNavigationUpKey()
                || keyCode == getNavigationLeftKey()
                || keyCode == getNavigationRightKey()
                || keyCode == getNavigationDownKey()
                || keyCode == getNavigationPageUpKey()
                || keyCode == getNavigationPageDownKey()
                || keyCode == getNavigationEndKey()
                || keyCode == getNavigationStartKey();
    }

    /**
     * Lazy revert focus to row.
     *
     * @param currentlyFocusedRow the currently focused row
     */
    public void lazyRevertFocusToRow(final VScrollTableRow currentlyFocusedRow) {
        Scheduler.get().scheduleFinally(new ScheduledCommand() {
            @Override
            public void execute() {
                if (currentlyFocusedRow != null) {
                    setRowFocus(currentlyFocusedRow);
                } else {
                    VConsole.log("no row?");
                    focusRowFromBody();
                }
                scrollBody.ensureFocus();
            }
        });
    }

    /**
     * Gets the actions.
     *
     * @return the actions
     */
    @Override
    public Action[] getActions() {
        if (bodyActionKeys == null) {
            return new Action[]{};
        }
        final Action[] actions = new Action[bodyActionKeys.length];
        for (int i = 0; i < actions.length; i++) {
            final String actionKey = bodyActionKeys[i];
            Action bodyAction = new TreeAction(this, null, actionKey);
            bodyAction.setCaption(getActionCaption(actionKey));
            bodyAction.setIconUrl(getActionIcon(actionKey));
            actions[i] = bodyAction;
        }
        return actions;
    }

    /**
     * Gets the client.
     *
     * @return the client
     */
    @Override
    public ApplicationConnection getClient() {
        return client;
    }

    /**
     * Gets the paintable id.
     *
     * @return the paintable id
     */
    @Override
    public String getPaintableId() {
        return paintableId;
    }

    /**
     * Add this to the element mouse down event by using element.setPropertyJSO
     * ("onselectstart",applyDisableTextSelectionIEHack()); Remove it then again
     * when the mouse is depressed in the mouse up event.
     *
     * @return Returns the JSO preventing text selection
     */
    private static native JavaScriptObject getPreventTextSelectionIEHack() /*-{
     return function(){ return false; };
     }-*/;

    /**
     * Trigger lazy column adjustment.
     *
     * @param now the now
     */
    public void triggerLazyColumnAdjustment(boolean now) {
        lazyAdjustColumnWidths.cancel();
        if (now) {
            lazyAdjustColumnWidths.run();
        } else {
            lazyAdjustColumnWidths.schedule(LAZY_COLUMN_ADJUST_TIMEOUT);
        }
    }

    /**
     * Checks if is dynamic width.
     *
     * @return true, if is dynamic width
     */
    private boolean isDynamicWidth() {
        ComponentConnector paintable = ConnectorMap.get(client).getConnector(
                this);
        return paintable.isUndefinedWidth();
    }

    /**
     * Checks if is dynamic height.
     *
     * @return true, if is dynamic height
     */
    protected boolean isDynamicHeight() {
        ComponentConnector paintable = ConnectorMap.get(client).getConnector(
                this);
        if (paintable == null) {
            // This should be refactored. As isDynamicHeight can be called from
            // a timer it is possible that the connector has been unregistered
            // when this method is called, causing getConnector to return null.
            return false;
        }
        return paintable.isUndefinedHeight();
    }

    /**
     * Debug.
     *
     * @param msg the msg
     */
    private void debug(String msg) {
        if (enableDebug) {
            VConsole.error(msg);
        }
    }

    /**
     * Gets the widget for paintable.
     *
     * @return the widget for paintable
     */
    public Widget getWidgetForPaintable() {
        return this;
    }
    /**
     * The Constant SUBPART_HEADER.
     */
    private static final String SUBPART_HEADER = "header";
    /**
     * The Constant SUBPART_FOOTER.
     */
    private static final String SUBPART_FOOTER = "footer";
    /**
     * The Constant SUBPART_ROW.
     */
    private static final String SUBPART_ROW = "row";
    /**
     * The Constant SUBPART_COL.
     */
    private static final String SUBPART_COL = "col";
    /**
     * Matches header[ix] - used for extracting the index of the targeted header
     * cell.
     */
    private static final RegExp SUBPART_HEADER_REGEXP = RegExp
            .compile(SUBPART_HEADER + "\\[(\\d+)\\]");
    /**
     * Matches footer[ix] - used for extracting the index of the targeted footer
     * cell.
     */
    private static final RegExp SUBPART_FOOTER_REGEXP = RegExp
            .compile(SUBPART_FOOTER + "\\[(\\d+)\\]");
    /**
     * Matches row[ix] - used for extracting the index of the targeted row.
     */
    private static final RegExp SUBPART_ROW_REGEXP = RegExp.compile(SUBPART_ROW
            + "\\[(\\d+)]");
    /**
     * Matches col[ix] - used for extracting the index of the targeted column.
     */
    private static final RegExp SUBPART_ROW_COL_REGEXP = RegExp
            .compile(SUBPART_ROW + "\\[(\\d+)\\]/" + SUBPART_COL
            + "\\[(\\d+)\\]");

    /**
     * Gets the sub part element.
     *
     * @param subPart the sub part
     * @return the sub part element
     */
    @Override
    public com.google.gwt.user.client.Element getSubPartElement(String subPart) {
        if (SUBPART_ROW_COL_REGEXP.test(subPart)) {
            MatchResult result = SUBPART_ROW_COL_REGEXP.exec(subPart);
            int rowIx = Integer.valueOf(result.getGroup(1));
            int colIx = Integer.valueOf(result.getGroup(2));
            VScrollTableRow row = scrollBody.getRowByRowIndex(rowIx);
            if (row != null) {
                Element rowElement = row.getElement();
                if (colIx < rowElement.getChildCount()) {
                    return rowElement.getChild(colIx).getFirstChild().cast();
                }
            }

        } else if (SUBPART_ROW_REGEXP.test(subPart)) {
            MatchResult result = SUBPART_ROW_REGEXP.exec(subPart);
            int rowIx = Integer.valueOf(result.getGroup(1));
            VScrollTableRow row = scrollBody.getRowByRowIndex(rowIx);
            if (row != null) {
                return row.getElement();
            }

        } else if (SUBPART_HEADER_REGEXP.test(subPart)) {
            MatchResult result = SUBPART_HEADER_REGEXP.exec(subPart);
            int headerIx = Integer.valueOf(result.getGroup(1));
            HeaderCell headerCell = tHead.getHeaderCell(headerIx);
            if (headerCell != null) {
                return headerCell.getElement();
            }

        } else if (SUBPART_FOOTER_REGEXP.test(subPart)) {
            MatchResult result = SUBPART_FOOTER_REGEXP.exec(subPart);
            int footerIx = Integer.valueOf(result.getGroup(1));
            FooterCell footerCell = tFoot.getFooterCell(footerIx);
            if (footerCell != null) {
                return footerCell.getElement();
            }
        }
        // Nothing found.
        return null;
    }

    /**
     * Gets the sub part name.
     *
     * @param subElement the sub element
     * @return the sub part name
     */
    @Override
    public String getSubPartName(com.google.gwt.user.client.Element subElement) {
        Widget widget = Util.findWidget(subElement, null);
        if (widget instanceof HeaderCell) {
            return SUBPART_HEADER + "[" + tHead.visibleCells.indexOf(widget)
                    + "]";
        } else if (widget instanceof FooterCell) {
            return SUBPART_FOOTER + "[" + tFoot.visibleCells.indexOf(widget)
                    + "]";
        } else if (widget instanceof VScrollTableRow) {
            // a cell in a row
            VScrollTableRow row = (VScrollTableRow) widget;
            int rowIx = scrollBody.indexOf(row);
            if (rowIx >= 0) {
                int colIx = -1;
                for (int ix = 0; ix < row.getElement().getChildCount(); ix++) {
                    if (row.getElement().getChild(ix).isOrHasChild(subElement)) {
                        colIx = ix;
                        break;
                    }
                }
                if (colIx >= 0) {
                    return SUBPART_ROW + "[" + rowIx + "]/" + SUBPART_COL + "["
                            + colIx + "]";
                }
                return SUBPART_ROW + "[" + rowIx + "]";
            }
        }
        // Nothing found.
        return null;
    }

    /**
     * On unregister.
     *
     * @since 7.2.6
     */
    public void onUnregister() {
        if (addCloseHandler != null) {
            addCloseHandler.removeHandler();
        }
    }

    /*
     * Return true if component need to perform some work and false otherwise.
     */
    /**
     * Checks if is work pending.
     *
     * @return true, if is work pending
     */
    @Override
    public boolean isWorkPending() {
        return lazyAdjustColumnWidths.isRunning();
    }

    /**
     * Gets the logger.
     *
     * @return the logger
     */
    private static Logger getLogger() {
        return Logger.getLogger(VExtCustomScrollTable.class.getName());
    }

    /**
     * The Class DoubleHeaderCell.
     */
    public class DoubleHeaderCell extends Widget {

        /**
         * The td.
         */
        Element td = DOM.createTD();
        /**
         * The caption container.
         */
        Element captionContainer = DOM.createDiv();
        /**
         * The sort indicator.
         */
        Element sortIndicator = DOM.createDiv();
        /**
         * The col resize widget.
         */
        Element colResizeWidget = DOM.createDiv();
        /**
         * The floating copy of header cell.
         */
        Element floatingCopyOfHeaderCell;
        /**
         * The icon.
         */
        Element icon = DOM.createImg();
        /**
         * The caption.
         */
        Element caption = DOM.createSpan();
        /**
         * The check box container.
         */
        Element checkBoxContainer = DOM.createSpan();
        /**
         * The radio container.
         */
        Element radioContainer = DOM.createSpan();
        /**
         * The check box label.
         */
        Element checkBoxLabel = DOM.createLabel();
        /**
         * The radio label.
         */
        Element radioLabel = DOM.createLabel();
        /**
         * The check box.
         */
        Element checkBox = DOM.createInputCheck();
        /**
         * The radio button.
         */
        Element radioButton = DOM.createInputRadio("");
        /**
         * The eicon.
         */
        Element eicon = DOM.createDiv();
        /**
         * The sortable.
         */
        private boolean sortable = false;
        /**
         * The cid.
         */
        private final String cid;
        /**
         * The dragging.
         */
        private boolean dragging;
        /**
         * The drag start x.
         */
        private int dragStartX;
        /**
         * The col index.
         */
        private int colIndex;
        /**
         * The original width.
         */
        private int originalWidth;
        /**
         * The tripleoriginal width.
         */
        private int tripleoriginalWidth;
        /**
         * The triple col key.
         */
        private String tripleColKey;
        /**
         * The is resizing.
         */
        private boolean isResizing;
        /**
         * The header x.
         */
        private int headerX;
        /**
         * The moved.
         */
        private boolean moved;
        /**
         * The closest slot.
         */
        private int closestSlot;
        /**
         * The width.
         */
        private int width = -1;
        /**
         * The natural width.
         */
        private int naturalWidth = -1;
        /**
         * The align.
         */
        private char align = ALIGN_LEFT;
        /**
         * The defined width.
         */
        boolean definedWidth = false;
        /**
         * The expand ratio.
         */
        private float expandRatio = 0;
        /**
         * The sorted.
         */
        private boolean sorted;
        /**
         * The checked.
         */
        private boolean checked;
        /**
         * The disabled.
         */
        private boolean disabled;
        /**
         * The ischeckbox.
         */
        private boolean ischeckbox;
        /**
         * The radiochecked.
         */
        private boolean radiochecked;
        /**
         * The radiodisabled.
         */
        private boolean radiodisabled;
        /**
         * The isradio.
         */
        private boolean isradio;
        /**
         * The iseicon.
         */
        private boolean iseicon;
        /**
         * The expanded.
         */
        private boolean expanded;

        /**
         * Sets the sortable.
         *
         * @param b the new sortable
         */
        public void setSortable(boolean b) {
            sortable = b;
        }

        /**
         * Sets the checked.
         *
         * @param c the new checked
         */
        public void setChecked(boolean c) {
            checked = c;
            checkBox.setPropertyBoolean("checked", checked);
        }

        /**
         * Checks if is checked.
         *
         * @return true, if is checked
         */
        public boolean isChecked() {
            return checked;
        }

        /**
         * Sets the disabled.
         *
         * @param d the new disabled
         */
        public void setDisabled(boolean d) {
            disabled = d;
            checkBox.setPropertyBoolean("disabled", disabled);
        }

        /**
         * Checks if is disabled.
         *
         * @return true, if is disabled
         */
        public boolean isDisabled() {
            return disabled;
        }

        /**
         * Sets the radio checked.
         *
         * @param c the new radio checked
         */
        public void setRadioChecked(boolean c) {
            radiochecked = c;
            radioButton.setPropertyBoolean("checked", radiochecked);
        }

        /**
         * Checks if is radio checked.
         *
         * @return true, if is radio checked
         */
        public boolean isRadioChecked() {
            return radiochecked;
        }

        /**
         * Sets the radio disabled.
         *
         * @param d the new radio disabled
         */
        public void setRadioDisabled(boolean d) {
            radiodisabled = d;
            radioButton.setPropertyBoolean("disabled", radiodisabled);
        }

        /**
         * Checks if is radio disabled.
         *
         * @return true, if is radio disabled
         */
        public boolean isRadioDisabled() {
            return radiodisabled;
        }

        /**
         * Sets the expand icon.
         *
         * @param b the new expand icon
         */
        public void setExpandIcon(boolean b) {
            iseicon = b;
        }

        /**
         * Checks if is expand icon.
         *
         * @return true, if is expand icon
         */
        public boolean isExpandIcon() {
            return iseicon;
        }

        /**
         * Sets the expand.
         *
         * @param b the new expand
         */
        public void setExpand(boolean b) {
            expanded = b;
            if (expanded) {
                removeStyleName("eicon-collapsed");
                addStyleName("eicon-expanded");
            } else {
                removeStyleName("eicon-expanded");
                addStyleName("eicon-collapsed");
            }
        }

        /**
         * Checks if is expand.
         *
         * @return true, if is expand
         */
        public boolean isExpand() {
            return expanded;
        }

        /**
         * Makes room for the sorting indicator in case the column that the
         * header cell belongs to is sorted. This is done by resizing the width
         * of the caption container element by the correct amount
         *
         * @param rightSpacing the right spacing
         */
        public void resizeCaptionContainer(int rightSpacing) {
            int captionContainerWidth = width
                    - colResizeWidget.getOffsetWidth() - rightSpacing;

            if (td.getClassName().contains("-asc")
                    || td.getClassName().contains("-desc")) {
                // Leave room for the sort indicator
                captionContainerWidth -= sortIndicator.getOffsetWidth();
            }
            if (td.getClassName().contains("eicon-expanded")
                    || td.getClassName().contains("eicon-collapsed")) {
                // Leave room for the sort indicator
                captionContainerWidth -= eicon.getOffsetWidth();
            }
            if (captionContainerWidth < 0) {
                rightSpacing += captionContainerWidth;
                captionContainerWidth = 0;
            }

            captionContainer.getStyle().setPropertyPx("width",
                    captionContainerWidth);

            // Apply/Remove spacing if defined
            if (rightSpacing > 0) {
                colResizeWidget.getStyle().setMarginLeft(rightSpacing, Unit.PX);
            } else {
                colResizeWidget.getStyle().clearMarginLeft();
            }
        }

        /**
         * Sets the natural minimum column width.
         *
         * @param w the new natural minimum column width
         */
        public void setNaturalMinimumColumnWidth(int w) {
            naturalWidth = w;
        }

        /**
         * Instantiates a new double header cell.
         *
         * @param colId the col id
         * @param col the col
         */
        public DoubleHeaderCell(String colId, UIDL col) {
            cid = colId;

//            setText(headerText);
            td.appendChild(colResizeWidget);

            // ensure no clipping initially (problem on column additions)
            captionContainer.getStyle().setOverflow(Overflow.VISIBLE);
            if (col == null) {
                captionContainer.appendChild(caption);
            } else {
                setCheckCaption(col);
            }

            td.appendChild(sortIndicator);

            td.appendChild(eicon);
            td.appendChild(captionContainer);
            DOM.sinkEvents(checkBoxContainer, Event.ONCLICK);
            DOM.sinkEvents(radioContainer, Event.ONCLICK);
            DOM.sinkEvents(checkBox, Event.ONCHANGE);
            DOM.sinkEvents(radioButton, Event.ONCHANGE);
            DOM.sinkEvents(eicon, Event.ONCLICK);
            DOM.sinkEvents(td, Event.MOUSEEVENTS | Event.ONDBLCLICK
                    | Event.ONCONTEXTMENU | Event.TOUCHEVENTS);

            setElement(td);

            setAlign(ALIGN_LEFT);
        }

        /**
         * Update style names.
         *
         * @param primaryStyleName the primary style name
         */
        protected void updateStyleNames(String primaryStyleName) {
            colResizeWidget.setClassName(primaryStyleName + "-resizer");
            sortIndicator.setClassName(primaryStyleName + "-sort-indicator");
            captionContainer.setClassName(primaryStyleName
                    + "-caption-container");

            if (sorted) {
                if (sortAscending) {
                    setStyleName(primaryStyleName + "-header-cell-asc");
                } else {
                    setStyleName(primaryStyleName + "-header-cell-desc");
                }
            } else {
                setStyleName(primaryStyleName + "-header-cell");
            }
            checkBoxContainer.setClassName(primaryStyleName + "-checkbox-container");
            checkBoxContainer.addClassName("v-checkbox");
            if (disabled) {
                checkBoxContainer.addClassName("v-disabled v-checkbox-disabled");
            } else {
                checkBoxContainer.removeClassName("v-disabled v-checkbox-disabled");
            }
            radioContainer.setClassName(primaryStyleName + "-radiobutton-container");
            radioContainer.addClassName("v-radiobutton");
            if (radiodisabled) {
                radioContainer.addClassName("v-radiobutton-disabled v-disabled");
            } else {
                radioContainer.removeClassName("v-radiobutton-disabled v-disabled");
            }
            caption.setClassName(primaryStyleName + "-captionspans-container");
            if (ischeckbox) {
                addStyleName("hascheckbox");
            }
            if (isradio) {
                addStyleName("hasradio");
            }
            eicon.setClassName(primaryStyleName + "-eicon");
            if (isExpandIcon()) {
                setExpand(isExpand());
            }
            final String ALIGN_PREFIX = primaryStyleName
                    + "-caption-container-align-";

            switch (align) {
                case ALIGN_CENTER:
                    captionContainer.addClassName(ALIGN_PREFIX + "center");
                    break;
                case ALIGN_RIGHT:
                    captionContainer.addClassName(ALIGN_PREFIX + "right");
                    break;
                default:
                    captionContainer.addClassName(ALIGN_PREFIX + "left");
                    break;
            }

        }

        /**
         * Disable auto width calculation.
         */
        public void disableAutoWidthCalculation() {
            definedWidth = true;
            expandRatio = 0;
        }

        /**
         * Sets width to the header cell. This width should not include any
         * possible indent modifications that are present in
         * {@link VScrollTableBody#getMaxIndent()}.
         *
         * @param w required width of the cell sans indentations
         * @param ensureDefinedWidth disables expand ratio if required
         */
        public void setWidth(int w, boolean ensureDefinedWidth) {
            if (ensureDefinedWidth) {
                definedWidth = true;
                // on column resize expand ratio becomes zero
                expandRatio = 0;
            }
            if (width == -1) {
                // go to default mode, clip content if necessary
                DOM.setStyleAttribute(captionContainer, "overflow", "");
            }
            width = w;
            if (w == -1) {
                DOM.setStyleAttribute(captionContainer, "width", "");
                setWidth("");
            } else {
                tdHead.resizeCaptionContainer(this);

                /*
                 * if we already have tBody, set the header width properly, if
                 * not defer it. IE will fail with complex float in table header
                 * unless TD width is not explicitly set.
                 */
                if (scrollBody != null) {
                    int maxIndent = scrollBody.getMaxIndent();
                    if (w < maxIndent && isHierarchyColumn()) {
                        w = maxIndent;
                    }
                    int tdWidth = w + scrollBody.getCellExtraWidth();
                    setWidth(tdWidth + "px");
                } else {
                    Scheduler.get().scheduleDeferred(new Command() {
                        @Override
                        public void execute() {
                            int maxIndent = scrollBody.getMaxIndent();
                            int tdWidth = width;
                            if (tdWidth < maxIndent && isHierarchyColumn()) {
                                tdWidth = maxIndent;
                            }
                            tdWidth += scrollBody.getCellExtraWidth();
                            setWidth(tdWidth + "px");

                        }
                    });
                }

            }
        }

        /**
         * Sets the undefined width.
         */
        public void setUndefinedWidth() {
            definedWidth = false;
            setWidth(-1, false);
        }

        /**
         * Detects if width is fixed by developer on server side or resized to
         * current width by user.
         *
         * @return true if defined, false if "natural" width
         */
        public boolean isDefinedWidth() {
            return definedWidth && width >= 0;
        }

        /**
         * This method exists for the needs of {@link VTreeTable} only.
         *
         * Returns the pixels width of the header cell. This includes the
         * indent, if applicable.
         *
         * @return The width in pixels
         */
        public int getWidthWithIndent() {
            return width;
            }

        /**
         * Gets the total width with indent.
         *
         * @return the total width with indent
         */
        public int getTotalWidthWithIndent() {
            String tmp[] = findVisibleMainColumn(getColKey());
            int tot = 0;
            for (int i = 0; i < tmp.length; i++) {
                HeaderCell hc = tHead.getHeaderCell(tmp[i]);
                tot += hc.getWidthWithIndent() + scrollBody.getCellExtraWidth();
            }
            tot=tot - scrollBody.getCellExtraWidth();
            return tot;
        }

        /**
         * Gets the total width with indent.
         *
         * @return the total width with indent
         */
        public int getTotalOffsetWidth() {
            String tmp[] = findVisibleMainColumn(getColKey());
            int tot = 0;
            for (int i = 0; i < tmp.length; i++) {
                HeaderCell hc = tHead.getHeaderCell(tmp[i]);
                tot += hc.getOffsetWidth();
            }
            return tot;
        }

        /**
         * Returns the pixels width of the header cell.
         *
         * @return The width in pixels
         */
        public int getWidth() {
            return width;
        }

        /**
         * This method exists for the needs of {@link VTreeTable} only.
         *
         * @param headerText the new text
         * @return <code>true</code> if this is hierarcyColumn's header cell,
         * <code>false</code> otherwise
         */
        public void setText(String headerText) {
            DOM.setInnerHTML(captionContainer, headerText);
        }

        /**
         * Sets the check caption.
         *
         * @param col the new check caption
         */
        public void setCheckCaption(UIDL col) {
            icon.removeFromParent();
            checkBoxContainer.removeFromParent();
            radioContainer.removeFromParent();
            caption.removeFromParent();
            if (col.hasAttribute("icon")) {
                icon.setClassName("v-icon");
                icon.setPropertyString("alt", "icon");
                icon.setPropertyString("src", Util.escapeAttribute(client.translateVaadinUri(col.getStringAttribute("icon"))));
                captionContainer.appendChild(icon);
            }
            if (col.hasAttribute("checkbox")) {
                checkBox.removeFromParent();
                checkBoxLabel.removeFromParent();
                ischeckbox = true;
                td.addClassName("hascheckbox");

                checkBox.setClassName("v-header-checkbox");
                setChecked(col.getBooleanAttribute("checkbox"));
                setDisabled(col.getBooleanAttribute("checkboxdisabled"));
                checkBoxContainer.appendChild(checkBox);
                checkBoxContainer.appendChild(checkBoxLabel);
                if (checked) {
                    checkBox.addClassName("selected");
                }
                captionContainer.appendChild(checkBoxContainer);
            }
            if (col.hasAttribute("radio")) {
                radioButton.removeFromParent();
                radioLabel.removeFromParent();
                isradio = true;
                td.addClassName("hasradio");
                radioButton.setPropertyString("name", col.getStringAttribute("radio"));
                radioButton.setClassName("v-header-radio");
                setRadioChecked(col.getBooleanAttribute("radiochecked"));
                setRadioDisabled(col.getBooleanAttribute("radiodisabled"));
                radioContainer.appendChild(radioButton);
                radioContainer.appendChild(radioLabel);
                if (radiochecked) {
                    radioButton.addClassName("selected");
                }
                captionContainer.appendChild(radioContainer);
            }
            String s = col.hasAttribute("caption") ? col
                    .getStringAttribute("caption") : "";
            caption.setInnerHTML(s);
            captionContainer.appendChild(caption);
        }

        /**
         * Checks if is hierarchy column.
         *
         * @return true, if is hierarchy column
         */
        private boolean isHierarchyColumn() {
            int hierarchyColumnIndex = getHierarchyColumnIndex();
            return hierarchyColumnIndex >= 0
                    && tdHead.visibleCells.indexOf(this) == hierarchyColumnIndex;
        }

        /**
         * Gets the col key.
         *
         * @return the col key
         */
        public String getColKey() {
            return cid;
        }

        /**
         * Sets the sorted.
         *
         * @param sorted the new sorted
         */
        private void setSorted(boolean sorted) {
            this.sorted = sorted;
            updateStyleNames(VExtCustomScrollTable.this.getStylePrimaryName());
        }

        /**
         * Handle column reordering.
         *
         * @param event the event
         */
        @Override
        public void onBrowserEvent(Event event) {
            if (enabled && event != null) {
                Element targetElement = DOM.eventGetTarget(event);
                if (DOM.isOrHasChild(checkBoxContainer, targetElement) && event.getEventTarget().cast() != checkBox) {
                    if (event.getTypeInt() == Event.ONCLICK) {
                        if (!disabled) {
                            checkBox.setPropertyBoolean("checked", !checkBox.getPropertyBoolean("checked"));
                            onCheckEvent();
                        }
                    }
                } else if (DOM.isOrHasChild(radioContainer, targetElement) && event.getEventTarget().cast() != radioButton) {
                    if (event.getTypeInt() == Event.ONCLICK) {
                        if (!radiodisabled) {
                            if (!radioButton.getPropertyBoolean("checked")) {
                                radioButton.setPropertyBoolean("checked", true);
                            }
                            onRadioCheckEvent();
                        }
                    }

                } else if (checkBox != null && event.getEventTarget().cast() == checkBox) {
                    if (event.getTypeInt() == Event.ONCHANGE) {
                        onCheckEvent();
                    }
                } else if (radioButton != null && event.getEventTarget().cast() == radioButton) {
                    if (event.getTypeInt() == Event.ONCHANGE) {
                        onRadioCheckEvent();
                    }

                } else if (eicon != null && event.getEventTarget().cast() == eicon) {
                    onEIconEvent(event);

                } else if (isResizing
                        || event.getEventTarget().cast() == colResizeWidget) {
                    if (dragging
                            && (event.getTypeInt() == Event.ONMOUSEUP || event
                            .getTypeInt() == Event.ONTOUCHEND)) {
                        // Handle releasing column header on spacer #5318
                        handleCaptionEvent(event);
                    } else {
                        onResizeEvent(event);
                    }
                } else {
                    /*
                     * Ensure focus before handling caption event. Otherwise
                     * variables changed from caption event may be before
                     * variables from other components that fire variables when
                     * they lose focus.
                     */
                    if (event.getTypeInt() == Event.ONMOUSEDOWN
                            || event.getTypeInt() == Event.ONTOUCHSTART) {
                        scrollBodyPanel.setFocus(true);
                    }
                    handleCaptionEvent(event);
                    boolean stopPropagation = true;
                    if (event.getTypeInt() == Event.ONCONTEXTMENU
                            && !client.hasEventListeners(
                            VExtCustomScrollTable.this,
                            TableConstants.HEADER_CLICK_EVENT_ID)) {
                        // Prevent showing the browser's context menu only when
                        // there is a header click listener.
                        stopPropagation = false;
                    }
                    if (stopPropagation) {
                        event.stopPropagation();
                        event.preventDefault();
                    }
                }

            }
        }

        /**
         * Creates the floating copy.
         */
        private void createFloatingCopy() {
            floatingCopyOfHeaderCell = DOM.createDiv();
            DOM.setInnerHTML(floatingCopyOfHeaderCell, DOM.getInnerHTML(td));
            floatingCopyOfHeaderCell = DOM
                    .getChild(floatingCopyOfHeaderCell, 2);
            DOM.setElementProperty(floatingCopyOfHeaderCell, "className",
                    VExtCustomScrollTable.this.getStylePrimaryName()
                    + "-header-drag");
            // otherwise might wrap or be cut if narrow column
            DOM.setStyleAttribute(floatingCopyOfHeaderCell, "width", "auto");
            updateFloatingCopysPosition(DOM.getAbsoluteLeft(td),
                    DOM.getAbsoluteTop(td));
            DOM.appendChild(RootPanel.get().getElement(),
                    floatingCopyOfHeaderCell);
        }

        /**
         * Update floating copys position.
         *
         * @param x the x
         * @param y the y
         */
        private void updateFloatingCopysPosition(int x, int y) {
            x -= DOM.getElementPropertyInt(floatingCopyOfHeaderCell,
                    "offsetWidth") / 2;
            DOM.setStyleAttribute(floatingCopyOfHeaderCell, "left", x + "px");
            if (y > 0) {
                DOM.setStyleAttribute(floatingCopyOfHeaderCell, "top", (y + 7)
                        + "px");
            }
        }

        /**
         * Hide floating copy.
         */
        private void hideFloatingCopy() {
            DOM.removeChild(RootPanel.get().getElement(),
                    floatingCopyOfHeaderCell);
            floatingCopyOfHeaderCell = null;
        }

        /**
         * Fires a header click event after the user has clicked a column header
         * cell.
         *
         * @param event The click event
         */
        private void fireHeaderClickedEvent(Event event) {
            if (client.hasEventListeners(VExtCustomScrollTable.this,
                    TableConstants.HEADER_CLICK_EVENT_ID)) {
                MouseEventDetails details = MouseEventDetailsBuilder
                        .buildMouseEventDetails(event);
                client.updateVariable(paintableId, "doubleheaderClickEvent",
                        details.toString(), false);
                client.updateVariable(paintableId, "doubleheaderClickCID", cid, true);
            }
        }

        /**
         * On check event.
         */
        protected void onCheckEvent() {
            checked = checkBox.getPropertyBoolean("checked");
            fireDoubleHeaderColumnCheckEvent(cid, checked);
            sendDoubleHeaderCheckBoxUpdates();

            if (checked) {
                checkBox.addClassName("selected");
            } else {
                checkBox.removeClassName("selected");
            }
        }

        /**
         * On radio check event.
         */
        protected void onRadioCheckEvent() {
            String rkey = getDoubleRadioKey(cid);
            String prevValue = getDoubleRadioVal(rkey);
            String currentHeader = DOUBLE_HEADER;
            String prvHeader = DOUBLE_HEADER;
            if (prevValue == null) {
                prevValue = getSingleRadioVal(rkey);
                if (prevValue != null) {
                    prvHeader = SINGLE_HEADER;
                }
            }
            if (prevValue == null) {
                prevValue = getTripleRadioVal(rkey);
                if (prevValue != null) {
                    prvHeader = TRIPLE_HEADER;
                }
            }
            if (radioButton.getPropertyBoolean("checked")) {
                setDoubleRadioVal(rkey, getColKey());

            }

            fireDoubleHeaderColumnRadioEvent(rkey, prvHeader, currentHeader, prevValue, getDoubleRadioVal(rkey));
            sendDoubleHeaderRadioUpdates();

            if (radiochecked) {
                radioButton.addClassName("selected");
            } else {
                radioButton.removeClassName("selected");
            }
        }

        /**
         * On e icon event.
         *
         * @param event the event
         */
        protected void onEIconEvent(Event event) {
            if (event.getTypeInt() == Event.ONCLICK) {
                setExpand(!isExpand());
                sendDoubleHeaderExpandIconUpdates(false);
                fireDoubleHeaderColumnExpandIconEvent(cid, isExpand());
            }
        }

        /**
         * Handle caption event.
         *
         * @param event the event
         */
        protected void handleCaptionEvent(Event event) {
            switch (DOM.eventGetType(event)) {
                case Event.ONTOUCHSTART:
                case Event.ONMOUSEDOWN:
                    break;
                case Event.ONMOUSEUP:
                case Event.ONTOUCHEND:
                case Event.ONTOUCHCANCEL:
                    if (!moved) {
                        fireHeaderClickedEvent(event);
                        if (Util.isTouchEvent(event)) {
                            /*
                             * Prevent using in e.g. scrolling and prevent generated
                             * events.
                             */
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        break;
                    }
                    break;
                case Event.ONDBLCLICK:
                    fireHeaderClickedEvent(event);
                    break;
                case Event.ONTOUCHMOVE:
                case Event.ONMOUSEMOVE:
                    break;
                default:
                    break;
            }
        }

        /**
         * On resize event.
         *
         * @param event the event
         */
        private void onResizeEvent(Event event) {
            switch (DOM.eventGetType(event)) {
                case Event.ONMOUSEDOWN:
                    if (!Util.isTouchEventOrLeftMouseButton(event)) {
                        return;
                    }
                    isResizing = true;
                    String tmp[] = findVisibleMainColumn(getColKey());
                    for (int i = 0; i < tmp.length; i++) {
                        HeaderCell hc = tHead.getHeaderCell(tmp[i]);
                        hc.isResizing = true;
                    }
                    DOM.setCapture(getElement());
                    dragStartX = DOM.eventGetClientX(event);
                    colIndex = getDoubleHeaderColIndexByKey(cid);
                    originalWidth = getWidthWithIndent();
                    if (showTripleColHeaders) {
                        tripleColKey = findTripleHeaderMappedKey(cid);
                        tripleoriginalWidth = getTripleHeaderColWidth(tripleColKey);
                    }
                    DOM.eventPreventDefault(event);
                    break;
                case Event.ONMOUSEUP:
                    if (!Util.isTouchEventOrLeftMouseButton(event)) {
                        return;
                    }
                    isResizing = false;
                    String tmp1[] = findVisibleMainColumn(getColKey());
                    for (int i = 0; i < tmp1.length; i++) {
                        HeaderCell hc = tHead.getHeaderCell(tmp1[i]);
                        hc.isResizing = false;
                    }
                    DOM.releaseCapture(getElement());
                    int childWidth1 = getTotalOffsetWidth();
                    int offW1 = getOffsetWidth();
                    if (childWidth1 > offW1) {
                        int newWidth = width + (childWidth1 - offW1);
                        setDoubleHeaderColWidth(colIndex, newWidth, true, true);
                    if (showTripleColHeaders) {
                            int newTripleWidth = tripleoriginalWidth + (newWidth - originalWidth);
                        setTripleHeaderColWidth(getTripleHeaderColIndexByKey(tripleColKey), newTripleWidth, true, false);
                    }
                    }

                    tdHead.disableAutoColumnWidthCalculation();
                    tHead.disableAutoColumnWidthCalculation(null);
                    if (showTripleColHeaders) {
                        ttHead.disableAutoColumnWidthCalculation();
                    }
                    // Ensure last header cell is taking into account possible
                    // column selector
                    DoubleHeaderCell lastCell = tdHead.getHeaderCell(tdHead
                            .getVisibleCellCount() - 1);
                    tdHead.resizeCaptionContainer(lastCell);
                    triggerLazyColumnAdjustment(true);

                    fireDoubleHeaderColumnResizeEvent(cid, originalWidth, getDoubleHeaderColWidth(cid));

                    break;
                case Event.ONMOUSEMOVE:
                    if (!Util.isTouchEventOrLeftMouseButton(event)) {
                        return;
                    }
                    if (isResizing) {
                        final int deltaX = DOM.eventGetClientX(event) - dragStartX;
                        if (deltaX == 0) {
                            return;
                        }
                        tdHead.disableAutoColumnWidthCalculation();

                        int newWidth = originalWidth + deltaX;
                        // get min width with indent, no padding
                        int minWidth = getMinWidth(true, false);
                        if (newWidth < minWidth) {
                            // already includes indent if any
                            newWidth = minWidth;
                        }

                        setDoubleHeaderColWidth(colIndex, newWidth, true, true);
                        int childWidth = getTotalOffsetWidth();
                        int offW=getOffsetWidth();
                        if(childWidth>offW){
                            newWidth=width+(childWidth-offW);
                            setDoubleHeaderColWidth(colIndex, newWidth, true, true);
                        }                        
                        if (showTripleColHeaders) {
                            int newTripleWidth = tripleoriginalWidth + (newWidth - originalWidth);
                            setTripleHeaderColWidth(getTripleHeaderColIndexByKey(tripleColKey), newTripleWidth, true, false);
                            ttHead.disableAutoColumnWidthCalculation();
                        }
                        triggerLazyColumnAdjustment(false);
                        forceRealignColumnHeaders();
                    }
                    break;
                default:
                    break;
            }
        }

        /**
         * Returns the smallest possible cell width in pixels.
         *
         * @param includeIndent - width should include hierarchy column indent
         * if applicable (VTreeTable only)
         * @param includeCellExtraWidth - width should include paddings etc.
         * @return the min width
         */
        private int getMinWidth(boolean includeIndent,
                boolean includeCellExtraWidth) {
            String tmp[] = findVisibleMainColumn(getColKey());
            int tot = 0;
            for (int i = 0; i < tmp.length; i++) {
                HeaderCell hc = tHead.getHeaderCell(tmp[i]);
                tot += hc.getMinWidth(includeIndent, includeCellExtraWidth);
            }
            return tot;
        }        

        /**
         * Gets the min width.
         *
         * @return the min width
         */
        public int getMinWidth() {
            // get min width with padding, no indent
            return getMinWidth(false, true);
        }

        /**
         * Gets the caption.
         *
         * @return the caption
         */
        public String getCaption() {
            return DOM.getInnerText(captionContainer);
        }

        /**
         * Checks if is enabled.
         *
         * @return true, if is enabled
         */
        public boolean isEnabled() {
            return getParent() != null;
        }

        /**
         * Sets the align.
         *
         * @param c the new align
         */
        public void setAlign(char c) {
            align = c;
            updateStyleNames(VExtCustomScrollTable.this.getStylePrimaryName());
        }

        /**
         * Gets the align.
         *
         * @return the align
         */
        public char getAlign() {
            return align;
        }

        /**
         * Detects the natural minimum width for the column of this header cell.
         * If column is resized by user or the width is defined by server the
         * actual width is returned. Else the natural min width is returned.
         *
         * @return the natural column width
         */
        public int getNaturalColumnWidth() {

            return naturalWidth;

        }

        /**
         * Sets the expand ratio.
         *
         * @param floatAttribute the new expand ratio
         */
        public void setExpandRatio(float floatAttribute) {
            if (floatAttribute != expandRatio) {
                triggerLazyColumnAdjustment(false);
            }
            expandRatio = floatAttribute;
        }

        /**
         * Gets the expand ratio.
         *
         * @return the expand ratio
         */
        public float getExpandRatio() {
            return expandRatio;
        }

        /**
         * Checks if is sorted.
         *
         * @return true, if is sorted
         */
        public boolean isSorted() {
            return sorted;
        }
    }

    /**
     * HeaderCell that is header cell for row headers.
     *
     * Reordering disabled and clicking on it resets sorting.
     */
    public class RowHeadersDoubleHeaderCell extends DoubleHeaderCell {

        /**
         * Instantiates a new row headers double header cell.
         */
        RowHeadersDoubleHeaderCell() {
            super(ROW_HEADER_COLUMN_KEY, null);
            updateStyleNames(VExtCustomScrollTable.this.getStylePrimaryName());
        }

        /**
         * Update style names.
         *
         * @param primaryStyleName the primary style name
         */
        @Override
        protected void updateStyleNames(String primaryStyleName) {
            super.updateStyleNames(primaryStyleName);
            setStyleName(primaryStyleName + "-header-cell-rowheader");
        }

        /**
         * Handle caption event.
         *
         * @param event the event
         */
        @Override
        protected void handleCaptionEvent(Event event) {
            // NOP: RowHeaders cannot be reordered
            // TODO It'd be nice to reset sorting here
        }
    }

    /**
     * The Class DoubleTableHead.
     */
    public class DoubleTableHead extends Panel implements ActionOwner {

        /**
         * The Constant WRAPPER_WIDTH.
         */
        private static final int WRAPPER_WIDTH = 900000;
        /**
         * The visible cells.
         */
        ArrayList<Widget> visibleCells = new ArrayList<Widget>();
        /**
         * The available cells.
         */
        HashMap<String, DoubleHeaderCell> availableCells = new HashMap<String, DoubleHeaderCell>();
        /**
         * The div.
         */
        Element div = DOM.createDiv();
        /**
         * The h table wrapper.
         */
        Element hTableWrapper = DOM.createDiv();
        /**
         * The h table container.
         */
        Element hTableContainer = DOM.createDiv();
        /**
         * The table.
         */
        Element table = DOM.createTable();
        /**
         * The header table body.
         */
        Element headerTableBody = DOM.createTBody();
        /**
         * The tr.
         */
        Element tr = DOM.createTR();
        /**
         * The column selector.
         */
        private final Element columnSelector = DOM.createDiv();
        /**
         * The focused slot.
         */
        private int focusedSlot = -1;

        /**
         * Instantiates a new double table head.
         */
        public DoubleTableHead() {
            if (BrowserInfo.get().isIE()) {
                table.setPropertyInt("cellSpacing", 0);
            }

            DOM.setStyleAttribute(hTableWrapper, "overflow", "hidden");
            DOM.setStyleAttribute(columnSelector, "display", "none");

            DOM.appendChild(table, headerTableBody);
            DOM.appendChild(headerTableBody, tr);
            DOM.appendChild(hTableContainer, table);
            DOM.appendChild(hTableWrapper, hTableContainer);
            DOM.appendChild(div, hTableWrapper);
            DOM.appendChild(div, columnSelector);
            setElement(div);

            DOM.sinkEvents(columnSelector, Event.ONCLICK);

            availableCells.put(ROW_HEADER_COLUMN_KEY,
                    new RowHeadersDoubleHeaderCell());
        }

        /**
         * Update style names.
         *
         * @param primaryStyleName the primary style name
         */
        protected void updateStyleNames(String primaryStyleName) {
            hTableWrapper.setClassName(primaryStyleName + "-header");
            columnSelector.setClassName(primaryStyleName + "-column-selector");
            setStyleName(primaryStyleName + "-header-wrap");
            for (DoubleHeaderCell c : availableCells.values()) {
                c.updateStyleNames(primaryStyleName);
            }
        }

        /**
         * Resize caption container.
         *
         * @param cell the cell
         */
        public void resizeCaptionContainer(DoubleHeaderCell cell) {
            DoubleHeaderCell lastcell = getHeaderCell(visibleCells.size() - 1);
            int columnSelectorOffset = columnSelector.getOffsetWidth();

            if (cell == lastcell && columnSelectorOffset > 0
                    && !hasVerticalScrollbar()) {

                // Measure column widths
                int columnTotalWidth = 0;
                for (Widget w : visibleCells) {
                    int cellExtraWidth = w.getOffsetWidth();
                    if (scrollBody != null
                            && visibleCells.indexOf(w) == getHierarchyColumnIndex()
                            && cellExtraWidth < scrollBody.getMaxIndent()) {
                        // indent must be taken into consideration even if it
                        // hasn't been applied yet
                        columnTotalWidth += scrollBody.getMaxIndent();
                    } else {
                        columnTotalWidth += cellExtraWidth;
                    }
                }

                int divOffset = div.getOffsetWidth();
                if (columnTotalWidth >= divOffset - columnSelectorOffset) {
                    /*
                     * Ensure column caption is visible when placed under the
                     * column selector widget by shifting and resizing the
                     * caption.
                     */
                    int offset = 0;
                    int diff = divOffset - columnTotalWidth;
                    if (diff < columnSelectorOffset && diff > 0) {
                        /*
                         * If the difference is less than the column selectors
                         * width then just offset by the difference
                         */
                        offset = columnSelectorOffset - diff;
                    } else {
                        // Else offset by the whole column selector
                        offset = columnSelectorOffset;
                    }
                    lastcell.resizeCaptionContainer(offset);
                } else {
                    cell.resizeCaptionContainer(0);
                }
            } else {
                cell.resizeCaptionContainer(0);
            }
        }

        /**
         * Clear.
         */
        @Override
        public void clear() {
            for (String cid : availableCells.keySet()) {
                removeCell(cid);
            }
            availableCells.clear();
            availableCells.put(ROW_HEADER_COLUMN_KEY,
                    new RowHeadersDoubleHeaderCell());
        }

        /**
         * Update cells radio from uidl.
         *
         * @param uidl the uidl
         */
        public void updateCellsRadioFromUIDL(UIDL uidl) {
            Iterator<?> it = uidl.getChildIterator();
            if (isReConstruct) {
                mapDoubleRadio.clear();
                doubleRadioVal.clear();
            }
            while (it.hasNext()) {
                final UIDL col = (UIDL) it.next();
                final String cid = col.getStringAttribute("doubleradio");
                mapDoubleRadio.put(cid, col.getStringArrayAttribute("doubleradioarr"));
                if (col.hasAttribute("doubleradioval")) {
                    doubleRadioVal.put(cid, col.getStringAttribute("doubleradioval"));
                }
            }
        }

        /**
         * Update cells from uidl.
         *
         * @param uidl the uidl
         * @param uidl1 the uidl1
         */
        public void updateCellsFromUIDL(UIDL uidl, UIDL uidl1) {
            if (isReConstruct) {
                mapVisibleColumns.clear();
            }
            Iterator<?> it = uidl.getChildIterator();
            HashSet<String> updated = new HashSet<String>();
            while (it.hasNext()) {
                final UIDL col = (UIDL) it.next();
                final String cid = col.getStringAttribute("dcid");
                updated.add(cid);
                mapVisibleColumns.put(cid, uidl1.getStringArrayAttribute(cid));
//                String caption = buildCustomCaptionHtmlSnippet(col);
                DoubleHeaderCell c = getHeaderCell(cid);
                if (c == null) {
                    c = new DoubleHeaderCell(cid, col);
                    availableCells.put(cid, c);
                    if (initializedAndAttached) {
                        // we will need a column width recalculation
                        initializedAndAttached = false;
                        initialContentReceived = false;
                        isNewBody = true;
                    }
                } else {
//                    c.setText(caption);
                    c.setCheckCaption(col);
                }
                if (col.hasAttribute("dsortable")) {
                    c.setSortable(true);
                    if (cid.equals(sortColumn)) {
                        c.setSorted(true);
                    } else {
                        c.setSorted(false);
                    }
                } else {
                    c.setSortable(false);
                }
                boolean x = col.hasAttribute("eicon");
                c.setExpandIcon(x);
                if (x) {
                    c.setExpand(col.getBooleanAttribute("eicon"));
                }
                if (col.hasAttribute("dalign")) {
                    c.setAlign(col.getStringAttribute("dalign").charAt(0));
                } else {
                    c.setAlign(ALIGN_LEFT);

                }
                if (col.hasAttribute("dwidth")) {
                    // Make sure to accomodate for the sort indicator if
                    // necessary.
                    int width = col.getIntAttribute("dwidth");
                    int widthWithoutAddedIndent = width;
                    // get min width with indent, no padding
                    int minWidth = c.getMinWidth(true, false);
                    if (width < minWidth) {
                        width = minWidth;
                    }
                    if (scrollBody != null && width != c.getWidthWithIndent()) {
                        // Do a more thorough update if a column is resized from
                        // the server *after* the header has been properly
                        // initialized
                        final int colIx = getDoubleHeaderColIndexByKey(c.cid);
                        final int newWidth = width;
                        Scheduler.get().scheduleDeferred(
                                new ScheduledCommand() {
                            @Override
                            public void execute() {
                                setDoubleHeaderColWidth(colIx, newWidth, true, false);
                            }
                        });
                    } else {
                        // get min width with no indent or padding
                        minWidth = c.getMinWidth(false, false);
                        if (widthWithoutAddedIndent < minWidth) {
                            widthWithoutAddedIndent = minWidth;
                        }
                        // save min width without indent
                        c.setWidth(widthWithoutAddedIndent, true);
                    }
                } else if (col.hasAttribute("der")) {
                    c.setExpandRatio(col.getFloatAttribute("der"));

                } else if (recalcWidths) {
                    c.setUndefinedWidth();

                } else {
                    boolean hadExpandRatio = c.getExpandRatio() > 0;
                    boolean hadDefinedWidth = c.isDefinedWidth();
                    if (hadExpandRatio || hadDefinedWidth) {
                        // Someone has removed a expand width or the defined
                        // width on the server side (setting it to -1), make the
                        // column undefined again and measure columns again.
                        c.setUndefinedWidth();
                        c.setExpandRatio(0);

                    }
                }
                if (col.hasAttribute("dcollapsed")) {
                    // ensure header is properly removed from parent (case when
                    // collapsing happens via servers side api)
                    if (c.isAttached()) {
                        c.removeFromParent();
                        String tp[] = uidl1.getStringArrayAttribute(cid);
                        for (int i = 0; i < tp.length; i++) {
                            HeaderCell hc = tHead.getHeaderCell(tp[i]);
                            if (hc.isAttached()) {
                                hc.removeFromParent();
                            }
                        }
                        headerChangedDuringUpdate = true;
                    }
                }
            }

        }

        /**
         * Enable column.
         *
         * @param cid the cid
         * @param index the index
         */
        public void enableColumn(String cid, int index) {
            final DoubleHeaderCell c = getHeaderCell(cid);
            if (!c.isEnabled() || getHeaderCell(index) != c) {
                setHeaderCell(index, c);
                if (initializedAndAttached) {
                    headerChangedDuringUpdate = true;
                }
            }
        }

        /**
         * Gets the visible cell count.
         *
         * @return the visible cell count
         */
        public int getVisibleCellCount() {
            return visibleCells.size();
        }

        /**
         * Sets the horizontal scroll position.
         *
         * @param scrollLeft the new horizontal scroll position
         */
        public void setHorizontalScrollPosition(int scrollLeft) {
            hTableWrapper.setScrollLeft(scrollLeft);
        }

        /**
         * Sets the column collapsing allowed.
         *
         * @param cc the new column collapsing allowed
         */
        public void setColumnCollapsingAllowed(boolean cc) {
            doublecollapsedColumnsallowed = cc;
            if (cc) {
                columnSelector.getStyle().setDisplay(Display.BLOCK);
            } else {
                columnSelector.getStyle().setDisplay(Display.NONE);
            }
        }

        /**
         * Disable browser intelligence.
         */
        public void disableBrowserIntelligence() {
            hTableContainer.getStyle().setWidth(WRAPPER_WIDTH, Unit.PX);
        }

        /**
         * Enable browser intelligence.
         */
        public void enableBrowserIntelligence() {
            hTableContainer.getStyle().clearWidth();
        }

        /**
         * Sets the header cell.
         *
         * @param index the index
         * @param cell the cell
         */
        public void setHeaderCell(int index, DoubleHeaderCell cell) {
            if (cell.isEnabled()) {
                // we're moving the cell
                DOM.removeChild(tr, cell.getElement());
                orphan(cell);
                visibleCells.remove(cell);
            }
            if (index < visibleCells.size()) {
                // insert to right slot
                DOM.insertChild(tr, cell.getElement(), index);
                adopt(cell);
                visibleCells.add(index, cell);
            } else if (index == visibleCells.size()) {
                // simply append
                DOM.appendChild(tr, cell.getElement());
                adopt(cell);
                visibleCells.add(cell);
            } else {
                throw new RuntimeException(
                        "Header cells must be appended in order");
            }
        }

        /**
         * Gets the header cell.
         *
         * @param index the index
         * @return the header cell
         */
        public DoubleHeaderCell getHeaderCell(int index) {
            if (index >= 0 && index < visibleCells.size()) {
                return (DoubleHeaderCell) visibleCells.get(index);
            } else {
                return null;
            }
        }

        /**
         * Get's HeaderCell by it's column Key.
         *
         * Note that this returns HeaderCell even if it is currently collapsed.
         *
         * @param cid Column key of accessed HeaderCell
         * @return HeaderCell
         */
        public DoubleHeaderCell getHeaderCell(String cid) {
            return availableCells.get(cid);
        }

        /**
         * Move cell.
         *
         * @param oldIndex the old index
         * @param newIndex the new index
         */
        public void moveCell(int oldIndex, int newIndex) {
            final DoubleHeaderCell hCell = getHeaderCell(oldIndex);
            final Element cell = hCell.getElement();

            visibleCells.remove(oldIndex);
            DOM.removeChild(tr, cell);

            DOM.insertChild(tr, cell, newIndex);
            visibleCells.add(newIndex, hCell);
        }

        /**
         * Iterator.
         *
         * @return the iterator
         */
        @Override
        public Iterator<Widget> iterator() {
            return visibleCells.iterator();
        }

        /**
         * Removes the.
         *
         * @param w the w
         * @return true, if successful
         */
        @Override
        public boolean remove(Widget w) {
            if (visibleCells.contains(w)) {
                visibleCells.remove(w);
                orphan(w);
                DOM.removeChild(DOM.getParent(w.getElement()), w.getElement());
                return true;
            }
            return false;
        }

        /**
         * Removes the cell.
         *
         * @param colKey the col key
         */
        public void removeCell(String colKey) {
            final DoubleHeaderCell c = getHeaderCell(colKey);
            remove(c);
        }

        /**
         * Focus slot.
         *
         * @param index the index
         */
        private void focusSlot(int index) {
            removeSlotFocus();
            if (index > 0) {
                Element child = tr.getChild(index - 1).getFirstChild().cast();
                child.setClassName(VExtCustomScrollTable.this
                        .getStylePrimaryName() + "-resizer");
                child.addClassName(VExtCustomScrollTable.this
                        .getStylePrimaryName() + "-focus-slot-right");
            } else {
                Element child = tr.getChild(index).getFirstChild().cast();
                child.setClassName(VExtCustomScrollTable.this
                        .getStylePrimaryName() + "-resizer");
                child.addClassName(VExtCustomScrollTable.this
                        .getStylePrimaryName() + "-focus-slot-left");
            }
            focusedSlot = index;
        }

        /**
         * Removes the slot focus.
         */
        private void removeSlotFocus() {
            if (focusedSlot < 0) {
                return;
            }
            if (focusedSlot == 0) {
                Element child = tr.getChild(focusedSlot).getFirstChild().cast();
                child.setClassName(VExtCustomScrollTable.this
                        .getStylePrimaryName() + "-resizer");
            } else if (focusedSlot > 0) {
                Element child = tr.getChild(focusedSlot - 1).getFirstChild()
                        .cast();
                child.setClassName(VExtCustomScrollTable.this
                        .getStylePrimaryName() + "-resizer");
            }
            focusedSlot = -1;
        }

        /**
         * On browser event.
         *
         * @param event the event
         */
        @Override
        public void onBrowserEvent(Event event) {
            if (enabled) {
                if (event.getEventTarget().cast() == columnSelector) {
                    final int left = DOM.getAbsoluteLeft(columnSelector);
                    final int top = DOM.getAbsoluteTop(columnSelector)
                            + DOM.getElementPropertyInt(columnSelector,
                            "offsetHeight");
                    client.getContextMenu().showAt(this, left, top);
                }
            }
        }

        /**
         * On detach.
         */
        @Override
        protected void onDetach() {
            super.onDetach();
            if (client != null) {
                client.getContextMenu().ensureHidden(this);
            }
        }

        /**
         * The Class DoubleVisibleColumnAction.
         */
        class DoubleVisibleColumnAction extends Action {

            /**
             * The col key.
             */
            String colKey;
            /**
             * The collapsed.
             */
            private boolean collapsed;
            /**
             * The noncollapsible.
             */
            private boolean noncollapsible = false;
            /**
             * The currently focused row.
             */
            private VScrollTableRow currentlyFocusedRow;

            /**
             * Instantiates a new double visible column action.
             *
             * @param colKey the col key
             */
            public DoubleVisibleColumnAction(String colKey) {
                super(VExtCustomScrollTable.DoubleTableHead.this);
                this.colKey = colKey;
                caption = tdHead.getHeaderCell(colKey).getCaption();
                currentlyFocusedRow = focusedRow;
            }

            /**
             * Execute.
             */
            @Override
            public void execute() {
                if (noncollapsible) {
                    return;
                }
                client.getContextMenu().hide();
                // toggle selected column
                String tp[] = findMappedKey(colKey);
                String tColKey = null;
                if (showTripleColHeaders) {
                    tColKey = findTripleHeaderMappedKey(colKey);
                }
                boolean sendSingle = false;
                boolean sendTriple = false;
                if (doublecollapsedColumns.contains(colKey)) {
                    doublecollapsedColumns.remove(colKey);
                    for (int i = 0; i < tp.length; i++) {
                        if (collapsedColumns.contains(tp[i])) {
                            sendSingle = true;
                            collapsedColumns.remove(tp[i]);
                        }
                    }

                    if (showTripleColHeaders) {
                        if (tripleCollapsedColumns.contains(tColKey)) {
                            sendTriple = true;
                            tripleCollapsedColumns.remove(tColKey);
                        }
                    }

                } else {
                    tdHead.removeCell(colKey);
                    doublecollapsedColumns.add(colKey);
                    for (int i = 0; i < tp.length; i++) {
                        if (!collapsedColumns.contains(tp[i])) {
                            sendSingle = true;
                            tHead.removeCell(tp[i]);
                            collapsedColumns.add(tp[i]);
                        }
                    }
                    if (showTripleColHeaders) {
                        String tp2[] = findVisibleDoubleColumn(tColKey);
                        if (tp2.length <= 0) {
                            sendTriple = true;
                            ttHead.removeCell(tColKey);
                            tripleCollapsedColumns.add(tColKey);
                        }
                    }
                    triggerLazyColumnAdjustment(true);
                }
                // update variable to server
                if (sendSingle) {
                    client.updateVariable(paintableId, "collapsedcolumns",
                            collapsedColumns.toArray(new String[collapsedColumns
                            .size()]), false);
                }
                client.updateVariable(paintableId, "doublecollapsedcolumns",
                        doublecollapsedColumns.toArray(new String[doublecollapsedColumns
                        .size()]), !sendTriple);
                if (showTripleColHeaders && sendTriple) {
                    client.updateVariable(paintableId, "triplecollapsedcolumns",
                            tripleCollapsedColumns.toArray(new String[tripleCollapsedColumns
                            .size()]), sendTriple);
                }
                rowRequestHandler.refreshContent();
                lazyRevertFocusToRow(currentlyFocusedRow);
            }

            /**
             * Sets the collapsed.
             *
             * @param b the new collapsed
             */
            public void setCollapsed(boolean b) {
                collapsed = b;
            }

            /**
             * Sets the noncollapsible.
             *
             * @param b the new noncollapsible
             */
            public void setNoncollapsible(boolean b) {
                noncollapsible = b;
            }

            /**
             * Override default method to distinguish on/off columns.
             *
             * @return the html
             */
            @Override
            public String getHTML() {
                final StringBuffer buf = new StringBuffer();
                buf.append("<span class=\"");
                if (collapsed) {
                    buf.append("v-off");
                } else {
                    buf.append("v-on");
                }
                if (noncollapsible) {
                    buf.append(" v-disabled");
                }
                buf.append("\">");

                buf.append(super.getHTML());
                buf.append("</span>");

                return buf.toString();
            }
        }

        /*
         * Returns columns as Action array for column select popup
         */
        /**
         * Gets the actions.
         *
         * @return the actions
         */
        @Override
        public Action[] getActions() {
            Object[] cols;
            if (columnReordering && doublecolumnOrder != null) {
                cols = doublecolumnOrder;
            } else {
                // if columnReordering is disabled, we need different way to get
                // all available columns
                cols = doublevisibleColOrder;
                cols = new Object[doublevisibleColOrder.length
                        + doublecollapsedColumns.size()];
                int i;
                for (i = 0; i < doublevisibleColOrder.length; i++) {
                    cols[i] = doublevisibleColOrder[i];
                }
                for (final Iterator<String> it = doublecollapsedColumns.iterator(); it
                        .hasNext();) {
                    cols[i++] = it.next();
                }
            }
            final Action[] actions = new Action[cols.length];

            for (int i = 0; i < cols.length; i++) {
                final String cid = (String) cols[i];
                final DoubleHeaderCell c = getHeaderCell(cid);
                final DoubleVisibleColumnAction a = new DoubleVisibleColumnAction(
                        c.getColKey());
                a.setCaption(c.getCaption());
                if (!c.isEnabled()) {
                    a.setCollapsed(true);
                }
                if (doublenoncollapsibleColumns.contains(cid)) {
                    a.setNoncollapsible(true);
                }
                actions[i] = a;
            }
            return actions;
        }

        /**
         * Gets the client.
         *
         * @return the client
         */
        @Override
        public ApplicationConnection getClient() {
            return client;
        }

        /**
         * Gets the paintable id.
         *
         * @return the paintable id
         */
        @Override
        public String getPaintableId() {
            return paintableId;
        }

        /**
         * Returns column alignments for visible columns.
         *
         * @return the column alignments
         */
        public char[] getColumnAlignments() {
            final Iterator<Widget> it = visibleCells.iterator();
            final char[] aligns = new char[visibleCells.size()];
            int colIndex = 0;
            while (it.hasNext()) {
                aligns[colIndex++] = ((DoubleHeaderCell) it.next()).getAlign();
            }
            return aligns;
        }

        /**
         * Disables the automatic calculation of all column widths by forcing
         * the widths to be "defined" thus turning off expand ratios and such.
         *
         * @param source the source
         */
        public void disableAutoColumnWidthCalculation() {
            for (DoubleHeaderCell cell : availableCells.values()) {
                cell.disableAutoWidthCalculation();
            }
            // fire column resize events for all columns but the source of the
            // resize action, since an event will fire separately for this.
            ArrayList<DoubleHeaderCell> columns = new ArrayList<DoubleHeaderCell>(
                    availableCells.values());
            sendDoubleHeaderColumnWidthUpdates(columns);
            forceRealignColumnHeaders();
        }
    }

    /**
     * Send double header expand icon updates.
     */
    private void sendDoubleHeaderExpandIconUpdates(boolean immediate) {
        int x = tdHead.getVisibleCellCount();
        ArrayList<DoubleHeaderCell> columns = new ArrayList<DoubleHeaderCell>();
        for (int i = 0; i < x; i++) {
            DoubleHeaderCell cell = tdHead.getHeaderCell(i);
            if (cell.isExpandIcon()) {
                columns.add(cell);
            }
        }
        String[] newSizes = new String[columns.size()];
        int ix = 0;
        for (DoubleHeaderCell celll : columns) {
            newSizes[ix++] = celll.getColKey() + ":" + celll.isExpand();
        }
        client.updateVariable(paintableId, "doublecolumnExpandIconUpdates", newSizes,
                immediate);
    }

    /**
     * Fire double header column expand icon event.
     *
     * @param columnId the column id
     * @param newNewValue the new new value
     */
    private void fireDoubleHeaderColumnExpandIconEvent(String columnId, boolean newNewValue) {
        client.updateVariable(paintableId, "doublecolumnExpandIconEventColumn", columnId,
                false);
        client.updateVariable(paintableId, "doublecolumnExpandIconEventCurr", newNewValue,
                true);

    }

    /**
     * Send expand icon updates.
     */
    private void sendExpandIconUpdates(boolean immediate) {
        int x = tHead.getVisibleCellCount();
        ArrayList<HeaderCell> columns = new ArrayList<HeaderCell>();
        for (int i = 0; i < x; i++) {
            HeaderCell cell = tHead.getHeaderCell(i);
            if (cell.isExpandIcon()) {
                columns.add(cell);
            }
        }
        String[] newSizes = new String[columns.size()];
        int ix = 0;
        for (HeaderCell celll : columns) {
            newSizes[ix++] = celll.getColKey() + ":" + celll.isExpand();
        }
        client.updateVariable(paintableId, "columnExpandIconUpdates", newSizes,
                immediate);
    }

    /**
     * Fire column expand icon event.
     *
     * @param columnId the column id
     * @param newNewValue the new new value
     */
    private void fireColumnExpandIconEvent(String columnId, boolean newNewValue) {
        client.updateVariable(paintableId, "columnExpandIconEventColumn", columnId,
                false);
        client.updateVariable(paintableId, "columnExpandIconEventCurr", newNewValue,
                true);

    }

    /**
     * Send double header check box updates.
     */
    private void sendDoubleHeaderCheckBoxUpdates() {
        int x = tdHead.getVisibleCellCount();
        ArrayList<DoubleHeaderCell> columns = new ArrayList<DoubleHeaderCell>();
        for (int i = 0; i < x; i++) {
            DoubleHeaderCell cell = tdHead.getHeaderCell(i);
            if (cell.ischeckbox) {
                columns.add(cell);
            }
        }
        String[] newSizes = new String[columns.size()];
        int ix = 0;
        for (DoubleHeaderCell celll : columns) {
            newSizes[ix++] = celll.getColKey() + ":" + celll.isChecked();
        }
        client.updateVariable(paintableId, "doublecolumnCheckBoxUpdates", newSizes,
                true);
    }

    /**
     * Fire double header column check event.
     *
     * @param columnId the column id
     * @param newNewValue the new new value
     */
    private void fireDoubleHeaderColumnCheckEvent(String columnId, boolean newNewValue) {
        client.updateVariable(paintableId, "doublecolumnCheckEventColumn", columnId,
                false);
        client.updateVariable(paintableId, "doublecolumnCheckEventCurr", newNewValue,
                immediate);

    }

    /**
     * Send double header radio updates.
     */
    private void sendDoubleHeaderRadioUpdates() {
        String[] newSizes = new String[doubleRadioVal.size()];
        Set<String> x = doubleRadioVal.keySet();
        int i = 0;
        for (String ob : x) {
            newSizes[i++] = ob + ":" + doubleRadioVal.get(ob);
        }

        client.updateVariable(paintableId, "doublecolumnRadioUpdates", newSizes,
                true);
    }

    /**
     * Fire double header column radio event.
     *
     * @param radioName the radio name
     * @param previousHeader the previous header
     * @param currentHeader the current header
     * @param originalValue the original value
     * @param newNewValue the new new value
     */
    private void fireDoubleHeaderColumnRadioEvent(String radioName, String previousHeader,
            String currentHeader, String originalValue,
            String newNewValue) {
        client.updateVariable(paintableId, "doublecolumnRadioEventName", radioName,
                false);
        client.updateVariable(paintableId, "doublecolumnRadioEventPrev",
                originalValue, false);
        client.updateVariable(paintableId, "doubleColumnRadioEventCurHeader", currentHeader,
                false);
        client.updateVariable(paintableId, "doubleColumnRadioEventPrevHeader",
                previousHeader, false);
        client.updateVariable(paintableId, "doublecolumnRadioEventCurr", newNewValue,
                immediate);

    }

    /**
     * Send radio updates.
     */
    private void sendRadioUpdates() {
        String[] newSizes = new String[singleRadioVal.size()];
        Set<String> x = singleRadioVal.keySet();
        int i = 0;
        for (String ob : x) {
            newSizes[i++] = ob + ":" + singleRadioVal.get(ob);
        }

        client.updateVariable(paintableId, "singlecolumnRadioUpdates", newSizes,
                true);
    }

    /**
     * Fire column radio event.
     *
     * @param radioName the radio name
     * @param previousHeader the previous header
     * @param currentHeader the current header
     * @param originalValue the original value
     * @param newNewValue the new new value
     */
    private void fireColumnRadioEvent(String radioName, String previousHeader,
            String currentHeader, String originalValue,
            String newNewValue) {
        client.updateVariable(paintableId, "singlecolumnRadioEventName", radioName,
                false);
        client.updateVariable(paintableId, "singlecolumnRadioEventPrev",
                originalValue, false);
        client.updateVariable(paintableId, "singleColumnRadioEventCurHeader", currentHeader,
                false);
        client.updateVariable(paintableId, "singleColumnRadioEventPrevHeader",
                previousHeader, false);
        client.updateVariable(paintableId, "singlecolumnRadioEventCurr", newNewValue,
                immediate);

    }

    /**
     * Send check box updates.
     */
    private void sendCheckBoxUpdates() {
        int x = tHead.getVisibleCellCount();
        ArrayList<HeaderCell> columns = new ArrayList<HeaderCell>();
        for (int i = 0; i < x; i++) {
            HeaderCell cell = tHead.getHeaderCell(i);
            if (cell.ischeckbox) {
                columns.add(cell);
            }
        }
        String[] newSizes = new String[columns.size()];
        int ix = 0;
        for (HeaderCell celll : columns) {
            newSizes[ix++] = celll.getColKey() + ":" + celll.isChecked();
        }
        client.updateVariable(paintableId, "columnCheckBoxUpdates", newSizes,
                true);
    }

    /**
     * Fire column check event.
     *
     * @param columnId the column id
     * @param newNewValue the new new value
     */
    private void fireColumnCheckEvent(String columnId, boolean newNewValue) {
        client.updateVariable(paintableId, "columnCheckEventColumn", columnId,
                false);
        client.updateVariable(paintableId, "columnCheckEventCurr", newNewValue,
                immediate);

    }

    /**
     * Fires a column resize event which sends the resize information to the
     * server.
     *
     * @param columnId The columnId of the column which was resized
     * @param originalWidth The width in pixels of the column before the resize
     * event
     * @param newWidth The width in pixels of the column after the resize event
     */
    private void fireDoubleHeaderColumnResizeEvent(String columnId, int originalWidth,
            int newWidth) {
        client.updateVariable(paintableId, "doublecolumnResizeEventColumn", columnId,
                false);
        client.updateVariable(paintableId, "doublecolumnResizeEventPrev",
                originalWidth, false);
        client.updateVariable(paintableId, "doublecolumnResizeEventCurr", newWidth,
                immediate);

    }

    /**
     * Non-immediate variable update of column widths for a collection of
     * columns.
     *
     * @param columns the columns to trigger the events for.
     */
    private void sendDoubleHeaderColumnWidthUpdates(Collection<DoubleHeaderCell> columns) {
        String[] newSizes = new String[columns.size()];
        int ix = 0;
        for (DoubleHeaderCell cell : columns) {
            newSizes[ix++] = cell.getColKey() + ":" + cell.getWidth();
        }
        client.updateVariable(paintableId, "doublecolumnWidthUpdates", newSizes,
                false);
    }

    /**
     * Update double header column properties.
     *
     * @param uidl the uidl
     */
    public void updateDoubleHeaderColumnProperties(UIDL uidl) {

        if (showDoubleColHeaders) {
            updateDoubleHeaderColumnOrder(uidl);
            updateDoubleHeaderCollapsedColumns(uidl);
            UIDL vc = uidl.getChildByTagName("doublevisiblecolumns");
            UIDL vc1 = uidl.getChildByTagName("doubleradiocolumns");
            if (vc1 != null) {
                tdHead.updateCellsRadioFromUIDL(vc1);
            }

            if (vc != null) {
                tdHead.updateCellsFromUIDL(vc, uidl.getChildByTagName("doublevisiblecolumnsmap"));
            }

            tdHead.addStyleName("double-header");
            updateDoubleHeader(uidl.getStringArrayAttribute("doublevcolorder"));
//            if (collapsedColumnsallowed) {
//                updateMainHeaderCollapsedColumns();
//            }

            if (isReConstruct && doublenoncollapsibleColumns != null) {
                doublenoncollapsibleColumns.clear();
            }

            if (uidl.hasVariable("doublenoncollapsiblecolumns")) {
                doublenoncollapsibleColumns = uidl
                        .getStringArrayVariableAsSet("doublenoncollapsiblecolumns");
            }
        }

        tdHead.setVisible(showDoubleColHeaders);
        setContainerHeight();
    }

    /**
     * Update main header collapsed columns.
     */
    private void updateMainHeaderCollapsedColumns() {

        for (String s : doublecollapsedColumns) {
            String tmp[] = findMappedKey(s);
            for (int i = 0; i < tmp.length; i++) {
                if (!collapsedColumns.contains(tmp[i])) {
                    collapsedColumns.add(tmp[i]);
                }
            }
        }
        client.updateVariable(paintableId, "collapsedcolumns",
                collapsedColumns.toArray(new String[collapsedColumns
                .size()]), false);
    }

    /**
     * Update double header collapsed columns.
     *
     * @param uidl the uidl
     */
    private void updateDoubleHeaderCollapsedColumns(UIDL uidl) {
        if (uidl.hasVariable("doublecollapsedcolumns")) {
            tdHead.setColumnCollapsingAllowed(true);
            String[] s2 = uidl.getStringArrayVariable("doublecollapsedcolumns");
            String[] s1 = new String[doublecollapsedColumns.size()];
            int j = 0;
            for (String s : doublecollapsedColumns) {
                s1[j] = s;
                j++;
            }
            Arrays.sort(s2);
            Arrays.sort(s1);
            if (!Arrays.equals(s1, s2)) {
                sizeNeedsInit = true;
            }
            doublecollapsedColumns = uidl
                    .getStringArrayVariableAsSet("doublecollapsedcolumns");
            for (int i = 0; i < doublecollapsedColumns.size(); i++) {
            }
        } else {
            tdHead.setColumnCollapsingAllowed(false);
        }
    }

    /**
     * Update double header column order.
     *
     * @param uidl the uidl
     */
    private void updateDoubleHeaderColumnOrder(UIDL uidl) {
        if (uidl.hasVariable("doublecolumnorder")) {
            doublecolumnOrder = uidl.getStringArrayVariable("doublecolumnorder");
        } else {
            doublecolumnOrder = null;
        }
    }

    /**
     * Gets the double header col index by key.
     *
     * @param colKey the col key
     * @return the double header col index by key
     */
    private int getDoubleHeaderColIndexByKey(String colKey) {
        // return 0 if asked for rowHeaders
        if (ROW_HEADER_COLUMN_KEY.equals(colKey)) {
            return 0;
        }
        if (doublevisibleColOrder != null) {
            for (int i = 0; i < doublevisibleColOrder.length; i++) {
                if (doublevisibleColOrder[i].equals(colKey)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Gets the double header col key by index.
     *
     * @param index the index
     * @return the double header col key by index
     */
    private String getDoubleHeaderColKeyByIndex(int index) {
        return tdHead.getHeaderCell(index).getColKey();
    }

    /**
     * Note: not part of the official API, extend at your own risk. May be
     * removed or replaced in the future.
     *
     * Sets the indicated column's width for headers and scrollBody alike.
     *
     * @param colIndex index of the modified column
     * @param w new width (may be subject to modifications if doesn't meet
     * minimum requirements)
     * @param isDefinedWidth disables expand ratio if set true
     * @param updateMap the update map
     */
    protected void setDoubleHeaderColWidth(int colIndex, int w, boolean isDefinedWidth, boolean updateMap) {
        final DoubleHeaderCell hcell = tdHead.getHeaderCell(colIndex);
        // Make sure that the column grows to accommodate the sort indicator if
        // necessary.
        // get min width with no indent or padding
        if (hcell != null) {
            int minWidth = hcell.getMinWidth(false, false);
            if (w < minWidth) {
                w = minWidth;
            }
            // Set header column width WITHOUT INDENT
            hcell.setWidth(w, isDefinedWidth);
            if (updateMap) {
                updateMainColumnWidth(hcell.getColKey(), w, isDefinedWidth);
            }
        }
    }
    /**
     * Gets the Double Header column width.
     *
     * @param dColKey the Double Header column key
     * @return the Double Header column width
     */
    private int getDoubleHeaderColWidth(String dColKey) {
        return tdHead.getHeaderCell(dColKey).getWidthWithIndent();
    }
    /**
     * Gets the Double Header column width with extra space.
     *
     * @param dColKey the Double Header column key
     * @return the Double Header column width with extra space
     */
    private int getDoubleHeaderColExtraWidth(String dColKey) {
        return tdHead.getHeaderCell(dColKey).getTotalWidthWithIndent();
    }

  
    /**
     * Update main column width.
     *
     * @param dColKey the d col key
     * @param dwidth the dwidth
     * @param isDefinedWidth the is defined width
     */
    private void updateMainColumnWidth(String dColKey, int dwidth, boolean isDefinedWidth) {
        String[] a = findVisibleMainColumn(dColKey);
        if (a.length > 0) {
            int tot = 0;
            int width = dwidth / a.length;
            for (int i = 0; i < a.length - 1; i++) {
                setColWidth(getColIndexByKey(a[i]), width - scrollBody.getCellExtraWidth(), isDefinedWidth);
                tot += width;
            }
            setColWidth(getColIndexByKey(a[a.length - 1]), dwidth-tot, isDefinedWidth);            
        }
    }



    /**
     * Find mapped key.
     *
     * @param dColKey the d col key
     * @return the string[]
     */
    private String[] findMappedKey(String dColKey) {
        return mapVisibleColumns.get(dColKey);
    }

    /**
     * Find double header mapped key.
     *
     * @param colKey the col key
     * @return the string
     */
    private String findDoubleHeaderMappedKey(String colKey) {
        mapVisibleColumns.keySet();
        for (String dColKey : mapVisibleColumns.keySet()) {
            String tmp[] = findMappedKey(dColKey);
            for (String tmp1 : tmp) {
                if (colKey.equals(tmp1)) {
                    return dColKey;
                }
            }
        }
        return null;
    }

    /**
     * Update double header.
     *
     * @param strings the strings
     */
    private void updateDoubleHeader(String[] strings) {
        if (strings == null) {
            return;
        }

        int visibleCols = strings.length;
        int colIndex = 0;
        if (showRowHeaders) {
            tdHead.enableColumn(ROW_HEADER_COLUMN_KEY, colIndex);
            visibleCols++;
            doublevisibleColOrder = new String[visibleCols];
            doublevisibleColOrder[colIndex] = ROW_HEADER_COLUMN_KEY;
            colIndex++;
        } else {
            doublevisibleColOrder = new String[visibleCols];
            tdHead.removeCell(ROW_HEADER_COLUMN_KEY);
        }

        int i;
        for (i = 0; i < strings.length; i++) {
            final String cid = strings[i];
            doublevisibleColOrder[colIndex] = cid;
            tdHead.enableColumn(cid, colIndex);
            colIndex++;
        }

    }


    /**
     * Update freeze table properties.
     *
     * @param uidl the uidl
     * @param clint the clint
     */
    public void updateFreezeTableProperties(UIDL uidl, ApplicationConnection clint) {

        leftTable = uidl.getStringAttribute("leftDependentTable");

        rightTable = uidl.getStringAttribute("rightDependentTable");

        if (leftTable != null) {

            leftDepandantPane = ((ExtCustomTableConnector) clint.getConnector(leftTable, 0))
                    .getWidget();
            leftScrollBodyPanel = (FocusableScrollPanel) leftDepandantPane.scrollBodyPanel;
        }
        if (rightTable != null) {
            rightDepandantPane = ((ExtCustomTableConnector) clint.getConnector(rightTable, 0))
                    .getWidget();

            if (rightDepandantPane != null) {
                rightScrollBodyPanel = (FocusableScrollPanel) rightDepandantPane.scrollBodyPanel;
            }
            scrollBody.freezeSpacer.addClassName("left-freeze-space");
        }
        setContainerHeight();
    }

    /**
     * Update depandant table.
     *
     * @param tempScrollBody the temp scroll body
     */
    public void updateDepandantTable(FocusableScrollPanel tempScrollBody) {

        NodeList<com.google.gwt.dom.client.Element> leftrow = tempScrollBody.getElement().getChild(0).getChild(1).getChild(0).getParentElement().getElementsByTagName("tr");
        NodeList<com.google.gwt.dom.client.Element> rightrow = scrollBodyPanel.getElement().getChild(0).getChild(1).getChild(0).getParentElement().getElementsByTagName("tr");

        for (int i = 0; i < rightrow.getLength(); i++) {

            rightrow.getItem(i).removeAttribute("height");
            leftrow.getItem(i).removeAttribute("height");
            int h1 = leftrow.getItem(i).getClientHeight();
            int h2 = rightrow.getItem(i).getClientHeight();
            if (h1 > h2) {
                rightrow.getItem(i).setAttribute("height", h1 + "px");
            } else if (h2 > h1) {
                leftrow.getItem(i).setAttribute("height", h2 + "px");
            }
        }
    }

    /**
     * Find visible main column.
     *
     * @param dColKey the d col key
     * @return the string[]
     */
    private String[] findVisibleMainColumn(String dColKey) {
        ArrayList<String> ob = new ArrayList<String>();
        String[] a = findMappedKey(dColKey);
        for (int i = 0; i < a.length; i++) {
            if (collapsedColumnsallowed) {
                if (!collapsedColumns.contains(a[i])) {
                    ob.add(a[i]);
                }
            } else {
                ob.add(a[i]);
            }
        }
        String[] b = new String[ob.size()];
        for (int i = 0; i < ob.size(); i++) {
            b[i] = ob.get(i);

        }
        return b;
    }

    /**
     * Sets the double radio val.
     *
     * @param key the key
     * @param val the val
     */
    public void setDoubleRadioVal(String key, String val) {
        doubleRadioVal.put(key, val);
        uncheckDoubleRadioVal(key, val);
        tdHead.getHeaderCell(val).setRadioChecked(true);
        if (radioButtonSinks) {
            uncheckSingleRadioVal(key, val);
            uncheckTripleRadioVal(key, val);
            removeSingleRadioVal(key);
            removeTripleRadioVal(key);
        }
    }

    /**
     * Gets the double radio val.
     *
     * @param key the key
     * @return the double radio val
     */
    public String getDoubleRadioVal(String key) {
        if (doubleRadioVal.containsKey(key)) {
            return doubleRadioVal.get(key);
        } else {
            return null;
        }
    }

    /**
     * Gets the double radio key.
     *
     * @param val the val
     * @return the double radio key
     */
    public String getDoubleRadioKey(String val) {
        Set<String> ts = mapDoubleRadio.keySet();
        String[] s = new String[ts.size()];
        int k = 0;
        for (String ss : ts) {
            s[k] = ss;
            k++;
        }
        for (int i = 0; i < s.length; i++) {
            String[] valary = mapDoubleRadio.get(s[i]);
            for (int j = 0; j < valary.length; j++) {
                if (valary[j].equals(val)) {
                    return s[i];
                }
            }
        }
        return null;
    }

    /**
     * Sets the single radio val.
     *
     * @param key the key
     * @param val the val
     */
    public void setSingleRadioVal(String key, String val) {
        singleRadioVal.put(key, val);
        uncheckSingleRadioVal(key, val);
        tHead.getHeaderCell(val).setRadioChecked(true);
        if (radioButtonSinks) {
            uncheckDoubleRadioVal(key, val);
            uncheckTripleRadioVal(key, val);
            removeDoubleRadioVal(key);
            removeTripleRadioVal(key);
        }
    }

    /**
     * Gets the single radio val.
     *
     * @param key the key
     * @return the single radio val
     */
    public String getSingleRadioVal(String key) {
        if (singleRadioVal.containsKey(key)) {
            return singleRadioVal.get(key);
        } else {
            return null;
        }
    }

    /**
     * Gets the single radio key.
     *
     * @param val the val
     * @return the single radio key
     */
    public String getSingleRadioKey(String val) {
        Set<String> ts = mapSingleRadio.keySet();
        String[] s = new String[ts.size()];
        int k = 0;
        for (String ss : ts) {
            s[k] = ss;
            k++;
        }
        for (int i = 0; i < s.length; i++) {
            String[] valary = mapSingleRadio.get(s[i]);
            for (int j = 0; j < valary.length; j++) {
                if (valary[j].equals(val)) {
                    return s[i];
                }
            }
        }
        return null;
    }

    //Added for triple header
    /**
     * The Class TripleHeaderCell.
     */
    public class TripleHeaderCell extends Widget {

        /**
         * The td.
         */
        Element td = DOM.createTD();
        /**
         * The caption container.
         */
        Element captionContainer = DOM.createDiv();
        /**
         * The sort indicator.
         */
        Element sortIndicator = DOM.createDiv();
        /**
         * The col resize widget.
         */
        Element colResizeWidget = DOM.createDiv();
        /**
         * The floating copy of header cell.
         */
        Element floatingCopyOfHeaderCell;
        /**
         * The icon.
         */
        Element icon = DOM.createImg();
        /**
         * The caption.
         */
        Element caption = DOM.createSpan();
        /**
         * The check box container.
         */
        Element checkBoxContainer = DOM.createSpan();
        /**
         * The radio container.
         */
        Element radioContainer = DOM.createSpan();
        /**
         * The check box label.
         */
        Element checkBoxLabel = DOM.createLabel();
        /**
         * The radio label.
         */
        Element radioLabel = DOM.createLabel();
        /**
         * The check box.
         */
        Element checkBox = DOM.createInputCheck();
        /**
         * The radio button.
         */
        Element radioButton = DOM.createInputRadio("");
        /**
         * The eicon.
         */
        Element eicon = DOM.createDiv();
        /**
         * The sortable.
         */
        private boolean sortable = false;
        /**
         * The cid.
         */
        private final String cid;
        /**
         * The dragging.
         */
        private boolean dragging;
        /**
         * The drag start x.
         */
        private int dragStartX;
        /**
         * The col index.
         */
        private int colIndex;
        /**
         * The original width.
         */
        private int originalWidth;
        /**
         * The is resizing.
         */
        private boolean isResizing;
        /**
         * The header x.
         */
        private int headerX;
        /**
         * The moved.
         */
        private boolean moved;
        /**
         * The closest slot.
         */
        private int closestSlot;
        /**
         * The width.
         */
        private int width = -1;
        /**
         * The natural width.
         */
        private int naturalWidth = -1;
        /**
         * The align.
         */
        private char align = ALIGN_LEFT;
        /**
         * The defined width.
         */
        boolean definedWidth = false;
        /**
         * The expand ratio.
         */
        private float expandRatio = 0;
        /**
         * The sorted.
         */
        private boolean sorted;
        /**
         * The checked.
         */
        private boolean checked;
        /**
         * The disabled.
         */
        private boolean disabled;
        /**
         * The ischeckbox.
         */
        private boolean ischeckbox;
        /**
         * The radiochecked.
         */
        private boolean radiochecked;
        /**
         * The radiodisabled.
         */
        private boolean radiodisabled;
        /**
         * The isradio.
         */
        private boolean isradio;
        /**
         * The iseicon.
         */
        private boolean iseicon;
        /**
         * The expanded.
         */
        private boolean expanded;
        /**
         * The single col.
         */
        String[] singleCol;
        /**
         * The double col.
         */
        String[] doubleCol;
        /**
         * The dob single.
         */
        Map<String, String[]> dobSingle = new HashMap<String, String[]>();

        /**
         * Sets the sortable.
         *
         * @param b the new sortable
         */
        public void setSortable(boolean b) {
            sortable = b;
        }

        /**
         * Sets the checked.
         *
         * @param c the new checked
         */
        public void setChecked(boolean c) {
            checked = c;
            checkBox.setPropertyBoolean("checked", checked);
        }

        /**
         * Checks if is checked.
         *
         * @return true, if is checked
         */
        public boolean isChecked() {
            return checked;
        }

        /**
         * Sets the disabled.
         *
         * @param d the new disabled
         */
        public void setDisabled(boolean d) {
            disabled = d;
            checkBox.setPropertyBoolean("disabled", disabled);
        }

        /**
         * Checks if is disabled.
         *
         * @return true, if is disabled
         */
        public boolean isDisabled() {
            return disabled;
        }

        /**
         * Sets the radio checked.
         *
         * @param c the new radio checked
         */
        public void setRadioChecked(boolean c) {
            radiochecked = c;
            radioButton.setPropertyBoolean("checked", radiochecked);
        }

        /**
         * Checks if is radio checked.
         *
         * @return true, if is radio checked
         */
        public boolean isRadioChecked() {
            return radiochecked;
        }

        /**
         * Sets the radio disabled.
         *
         * @param d the new radio disabled
         */
        public void setRadioDisabled(boolean d) {
            radiodisabled = d;
            radioButton.setPropertyBoolean("disabled", radiodisabled);
        }

        /**
         * Checks if is radio disabled.
         *
         * @return true, if is radio disabled
         */
        public boolean isRadioDisabled() {
            return radiodisabled;
        }

        /**
         * Sets the expand icon.
         *
         * @param b the new expand icon
         */
        public void setExpandIcon(boolean b) {
            iseicon = b;
        }

        /**
         * Checks if is expand icon.
         *
         * @return true, if is expand icon
         */
        public boolean isExpandIcon() {
            return iseicon;
        }

        /**
         * Sets the expand.
         *
         * @param b the new expand
         */
        public void setExpand(boolean b) {
            expanded = b;
            if (expanded) {
                removeStyleName("eicon-collapsed");
                addStyleName("eicon-expanded");
            } else {
                removeStyleName("eicon-expanded");
                addStyleName("eicon-collapsed");
            }
        }

        /**
         * Checks if is expand.
         *
         * @return true, if is expand
         */
        public boolean isExpand() {
            return expanded;
        }

        /**
         * Makes room for the sorting indicator in case the column that the
         * header cell belongs to is sorted. This is done by resizing the width
         * of the caption container element by the correct amount
         *
         * @param rightSpacing the right spacing
         */
        public void resizeCaptionContainer(int rightSpacing) {
            int captionContainerWidth = width
                    - colResizeWidget.getOffsetWidth() - rightSpacing;

            if (td.getClassName().contains("-asc")
                    || td.getClassName().contains("-desc")) {
                // Leave room for the sort indicator
                captionContainerWidth -= sortIndicator.getOffsetWidth();
            }
            if (td.getClassName().contains("eicon-expanded")
                    || td.getClassName().contains("eicon-collapsed")) {
                // Leave room for the sort indicator
                captionContainerWidth -= eicon.getOffsetWidth();
            }
            if (captionContainerWidth < 0) {
                rightSpacing += captionContainerWidth;
                captionContainerWidth = 0;
            }

            captionContainer.getStyle().setPropertyPx("width",
                    captionContainerWidth);

            // Apply/Remove spacing if defined
            if (rightSpacing > 0) {
                colResizeWidget.getStyle().setMarginLeft(rightSpacing, Unit.PX);
            } else {
                colResizeWidget.getStyle().clearMarginLeft();
            }
        }

        /**
         * Sets the natural minimum column width.
         *
         * @param w the new natural minimum column width
         */
        public void setNaturalMinimumColumnWidth(int w) {
            naturalWidth = w;
        }

        /**
         * Instantiates a new triple header cell.
         *
         * @param colId the col id
         * @param col the col
         */
        public TripleHeaderCell(String colId, UIDL col) {
            cid = colId;

//            setText(headerText);
            td.appendChild(colResizeWidget);

            // ensure no clipping initially (problem on column additions)
            captionContainer.getStyle().setOverflow(Overflow.VISIBLE);
            if (col == null) {
                captionContainer.appendChild(caption);
            } else {
                setCheckCaption(col);
            }

            td.appendChild(sortIndicator);

            td.appendChild(eicon);
            td.appendChild(captionContainer);
            DOM.sinkEvents(checkBoxContainer, Event.ONCLICK);
            DOM.sinkEvents(radioContainer, Event.ONCLICK);
            DOM.sinkEvents(checkBox, Event.ONCHANGE);
            DOM.sinkEvents(radioButton, Event.ONCHANGE);
            DOM.sinkEvents(eicon, Event.ONCLICK);
            DOM.sinkEvents(td, Event.MOUSEEVENTS | Event.ONDBLCLICK
                    | Event.ONCONTEXTMENU | Event.TOUCHEVENTS);

            setElement(td);

            setAlign(ALIGN_LEFT);
        }

        /**
         * Update style names.
         *
         * @param primaryStyleName the primary style name
         */
        protected void updateStyleNames(String primaryStyleName) {
            colResizeWidget.setClassName(primaryStyleName + "-resizer");
            sortIndicator.setClassName(primaryStyleName + "-sort-indicator");
            captionContainer.setClassName(primaryStyleName
                    + "-caption-container");

            if (sorted) {
                if (sortAscending) {
                    setStyleName(primaryStyleName + "-header-cell-asc");
                } else {
                    setStyleName(primaryStyleName + "-header-cell-desc");
                }
            } else {
                setStyleName(primaryStyleName + "-header-cell");
            }
            checkBoxContainer.setClassName(primaryStyleName + "-checkbox-container");
            checkBoxContainer.addClassName("v-checkbox");
            if (disabled) {
                checkBoxContainer.addClassName("v-disabled v-checkbox-disabled");
            } else {
                checkBoxContainer.removeClassName("v-disabled v-checkbox-disabled");
            }
            radioContainer.setClassName(primaryStyleName + "-radiobutton-container");
            radioContainer.addClassName("v-radiobutton");
            if (radiodisabled) {
                radioContainer.addClassName("v-radiobutton-disabled v-disabled");
            } else {
                radioContainer.removeClassName("v-radiobutton-disabled v-disabled");
            }
            caption.setClassName(primaryStyleName + "-captionspans-container");
            if (ischeckbox) {
                addStyleName("hascheckbox");
            }
            if (isradio) {
                addStyleName("hasradio");
            }
            eicon.setClassName(primaryStyleName + "-eicon");
            if (isExpandIcon()) {
                setExpand(isExpand());
            }
            final String ALIGN_PREFIX = primaryStyleName
                    + "-caption-container-align-";

            switch (align) {
                case ALIGN_CENTER:
                    captionContainer.addClassName(ALIGN_PREFIX + "center");
                    break;
                case ALIGN_RIGHT:
                    captionContainer.addClassName(ALIGN_PREFIX + "right");
                    break;
                default:
                    captionContainer.addClassName(ALIGN_PREFIX + "left");
                    break;
            }

        }

        /**
         * Disable auto width calculation.
         */
        public void disableAutoWidthCalculation() {
            definedWidth = true;
            expandRatio = 0;
        }

        /**
         * Sets width to the header cell. This width should not include any
         * possible indent modifications that are present in
         * {@link VScrollTableBody#getMaxIndent()}.
         *
         * @param w required width of the cell sans indentations
         * @param ensureDefinedWidth disables expand ratio if required
         */
        public void setWidth(int w, boolean ensureDefinedWidth) {

            if (ensureDefinedWidth) {
                definedWidth = true;
                // on column resize expand ratio becomes zero
                expandRatio = 0;
            }
            if (width == -1) {
                // go to default mode, clip content if necessary
                DOM.setStyleAttribute(captionContainer, "overflow", "");
            }
            width = w;
            if (w == -1) {
                DOM.setStyleAttribute(captionContainer, "width", "");
                setWidth("");
            } else {
                ttHead.resizeCaptionContainer(this);

                /*
                 * if we already have tBody, set the header width properly, if
                 * not defer it. IE will fail with complex float in table header
                 * unless TD width is not explicitly set.
                 */
                if (scrollBody != null) {
                    int maxIndent = scrollBody.getMaxIndent();
                    if (w < maxIndent && isHierarchyColumn()) {
                        w = maxIndent;
                    }
                    int tdWidth = w + scrollBody.getCellExtraWidth();
                    setWidth(tdWidth + "px");
                } else {
                    Scheduler.get().scheduleDeferred(new Command() {
                        @Override
                        public void execute() {
                            int maxIndent = scrollBody.getMaxIndent();
                            int tdWidth = width;
                            if (tdWidth < maxIndent && isHierarchyColumn()) {
                                tdWidth = maxIndent;
                            }
                            tdWidth += scrollBody.getCellExtraWidth();
                            setWidth(tdWidth + "px");
                        }
                    });
                }
            }
        }

        /**
         * Sets the undefined width.
         */
        public void setUndefinedWidth() {
            definedWidth = false;
            setWidth(-1, false);
        }

        /**
         * Detects if width is fixed by developer on server side or resized to
         * current width by user.
         *
         * @return true if defined, false if "natural" width
         */
        public boolean isDefinedWidth() {
            return definedWidth && width >= 0;
        }

        /**
         * This method exists for the needs of {@link VTreeTable} only.
         *
         * Returns the pixels width of the header cell. This includes the
         * indent, if applicable.
         *
         * @return The width in pixels
         */
        public int getWidthWithIndent() {
            return width;
            }

        /**
         * Gets the total width with indent.
         *
         * @return the total width with indent
         */
        public int getTotalWidthWithIndent() {
            String tmp[] = findVisibleDoubleColumn(getColKey());
            int tot = 0;
            for (int i = 0; i < tmp.length; i++) {
                DoubleHeaderCell hc = tdHead.getHeaderCell(tmp[i]);
                tot += hc.getWidthWithIndent() + scrollBody.getCellExtraWidth();
            }
            tot=tot - scrollBody.getCellExtraWidth();
            return tot;
        }

        /**
         * Gets the total width with indent.
         *
         * @return the total width with indent
         */
        public int getTotalOffsetWidth() {
            String tmp[] = findVisibleDoubleColumn(getColKey());
            int tot = 0;
            for (int i = 0; i < tmp.length; i++) {
                DoubleHeaderCell hc = tdHead.getHeaderCell(tmp[i]);
                tot += hc.getTotalOffsetWidth();
            }
            return tot;
        }

        /**
         * Returns the pixels width of the header cell.
         *
         * @return The width in pixels
         */
        public int getWidth() {
            return width;
        }

        /**
         * This method exists for the needs of {@link VTreeTable} only.
         *
         * @param headerText the new text
         * @return <code>true</code> if this is hierarcyColumn's header cell,
         * <code>false</code> otherwise
         */
        public void setText(String headerText) {
            DOM.setInnerHTML(captionContainer, headerText);
        }

        /**
         * Sets the check caption.
         *
         * @param col the new check caption
         */
        public void setCheckCaption(UIDL col) {
            icon.removeFromParent();
            checkBoxContainer.removeFromParent();
            radioContainer.removeFromParent();
            caption.removeFromParent();
            if (col.hasAttribute("icon")) {
                icon.setClassName("v-icon");
                icon.setPropertyString("alt", "icon");
                icon.setPropertyString("src", Util.escapeAttribute(client.translateVaadinUri(col.getStringAttribute("icon"))));
                captionContainer.appendChild(icon);
            }
            if (col.hasAttribute("checkbox")) {
                checkBox.removeFromParent();
                checkBoxLabel.removeFromParent();
                ischeckbox = true;
                td.addClassName("hascheckbox");

                checkBox.setClassName("v-header-checkbox");
                setChecked(col.getBooleanAttribute("checkbox"));
                setDisabled(col.getBooleanAttribute("checkboxdisabled"));
                checkBoxContainer.appendChild(checkBox);
                checkBoxContainer.appendChild(checkBoxLabel);
                if (checked) {
                    checkBox.addClassName("selected");
                }
                captionContainer.appendChild(checkBoxContainer);
            }
            if (col.hasAttribute("radio")) {
                radioButton.removeFromParent();
                radioLabel.removeFromParent();
                isradio = true;
                td.addClassName("hasradio");
                radioButton.setPropertyString("name", col.getStringAttribute("radio"));
                radioButton.setClassName("v-header-radio");
                setRadioChecked(col.getBooleanAttribute("radiochecked"));
                setRadioDisabled(col.getBooleanAttribute("radiodisabled"));
                radioContainer.appendChild(radioButton);
                radioContainer.appendChild(radioLabel);
                if (radiochecked) {
                    radioButton.addClassName("selected");
                }
                captionContainer.appendChild(radioContainer);
            }
            String s = col.hasAttribute("caption") ? col
                    .getStringAttribute("caption") : "";
            caption.setInnerHTML(s);
            captionContainer.appendChild(caption);
        }

        /**
         * Checks if is hierarchy column.
         *
         * @return true, if is hierarchy column
         */
        private boolean isHierarchyColumn() {
            int hierarchyColumnIndex = getHierarchyColumnIndex();
            return hierarchyColumnIndex >= 0
                    && ttHead.visibleCells.indexOf(this) == hierarchyColumnIndex;
        }

        /**
         * Gets the col key.
         *
         * @return the col key
         */
        public String getColKey() {
            return cid;
        }

        /**
         * Sets the sorted.
         *
         * @param sorted the new sorted
         */
        private void setSorted(boolean sorted) {
            this.sorted = sorted;
            updateStyleNames(VExtCustomScrollTable.this.getStylePrimaryName());
        }

        /**
         * Handle column reordering.
         *
         * @param event the event
         */
        @Override
        public void onBrowserEvent(Event event) {
            if (enabled && event != null) {
                Element targetElement = DOM.eventGetTarget(event);
                if (DOM.isOrHasChild(checkBoxContainer, targetElement) && event.getEventTarget().cast() != checkBox) {
                    if (event.getTypeInt() == Event.ONCLICK) {
                        if (!disabled) {
                            checkBox.setPropertyBoolean("checked", !checkBox.getPropertyBoolean("checked"));
                            onCheckEvent();
                        }
                    }
                } else if (DOM.isOrHasChild(radioContainer, targetElement) && event.getEventTarget().cast() != radioButton) {
                    if (event.getTypeInt() == Event.ONCLICK) {
                        if (!radiodisabled) {
                            if (!radioButton.getPropertyBoolean("checked")) {
                                radioButton.setPropertyBoolean("checked", true);
                            }
                            onRadioCheckEvent();
                        }
                    }

                } else if (checkBox != null && event.getEventTarget().cast() == checkBox) {
                    if (event.getTypeInt() == Event.ONCHANGE) {
                        onCheckEvent();
                    }
                } else if (radioButton != null && event.getEventTarget().cast() == radioButton) {
                    if (event.getTypeInt() == Event.ONCHANGE) {
                        onRadioCheckEvent();
                    }

                } else if (eicon != null && event.getEventTarget().cast() == eicon) {
                    onEIconEvent(event);

                } else if (isResizing
                        || event.getEventTarget().cast() == colResizeWidget) {
                    if (dragging
                            && (event.getTypeInt() == Event.ONMOUSEUP || event
                            .getTypeInt() == Event.ONTOUCHEND)) {
                        // Handle releasing column header on spacer #5318
                        handleCaptionEvent(event);
                    } else {
                        onResizeEvent(event);
                    }
                } else {
                    /*
                     * Ensure focus before handling caption event. Otherwise
                     * variables changed from caption event may be before
                     * variables from other components that fire variables when
                     * they lose focus.
                     */
                    if (event.getTypeInt() == Event.ONMOUSEDOWN
                            || event.getTypeInt() == Event.ONTOUCHSTART) {
                        scrollBodyPanel.setFocus(true);
                    }
                    handleCaptionEvent(event);
                    boolean stopPropagation = true;
                    if (event.getTypeInt() == Event.ONCONTEXTMENU
                            && !client.hasEventListeners(
                            VExtCustomScrollTable.this,
                            TableConstants.HEADER_CLICK_EVENT_ID)) {
                        // Prevent showing the browser's context menu only when
                        // there is a header click listener.
                        stopPropagation = false;
                    }
                    if (stopPropagation) {
                        event.stopPropagation();
                        event.preventDefault();
                    }
                }

            }
        }

        

        /**
         * Fires a header click event after the user has clicked a column header
         * cell.
         *
         * @param event The click event
         */
        private void fireHeaderClickedEvent(Event event) {
            if (client.hasEventListeners(VExtCustomScrollTable.this,
                    TableConstants.HEADER_CLICK_EVENT_ID)) {
                MouseEventDetails details = MouseEventDetailsBuilder
                        .buildMouseEventDetails(event);
                client.updateVariable(paintableId, "tripleHeaderClickEvent",
                        details.toString(), false);
                client.updateVariable(paintableId, "tripleHeaderClickCID", cid, true);
            }
        }

        /**
         * On check event.
         */
        protected void onCheckEvent() {
            checked = checkBox.getPropertyBoolean("checked");
            fireTripleHeaderColumnCheckEvent(cid, checked);
            sendTripleHeaderCheckBoxUpdates();

            if (checked) {
                checkBox.addClassName("selected");
            } else {
                checkBox.removeClassName("selected");
            }
        }

        /**
         * On radio check event.
         */
        protected void onRadioCheckEvent() {
            String rkey = getTripleRadioKey(cid);
            String prevValue = getTripleRadioVal(rkey);
            String currentHeader = TRIPLE_HEADER;
            String prvHeader = TRIPLE_HEADER;
            if (prevValue == null) {
                prevValue = getSingleRadioVal(rkey);
                if (prevValue != null) {
                    prvHeader = SINGLE_HEADER;
                }
            }
            if (prevValue == null) {
                prevValue = getDoubleRadioVal(rkey);
                if (prevValue != null) {
                    prvHeader = DOUBLE_HEADER;
                }
            }
            if (radioButton.getPropertyBoolean("checked")) {
                setTripleRadioVal(rkey, getColKey());

            }

            fireTripleHeaderColumnRadioEvent(rkey, prvHeader, currentHeader, prevValue, getTripleRadioVal(rkey));
            sendTripleHeaderRadioUpdates();
            if (radiochecked) {
                radioButton.addClassName("selected");
            } else {
                radioButton.removeClassName("selected");
            }
        }

        /**
         * On e icon event.
         *
         * @param event the event
         */
        protected void onEIconEvent(Event event) {
            if (event.getTypeInt() == Event.ONCLICK) {
                setExpand(!isExpand());
                fireTripleHeaderColumnExpandIconEvent(cid, isExpand());
                sendTripleHeaderExpandIconUpdates(false);

            }
        }

        /**
         * Handle caption event.
         *
         * @param event the event
         */
        protected void handleCaptionEvent(Event event) {
            switch (DOM.eventGetType(event)) {
                case Event.ONTOUCHSTART:
                case Event.ONMOUSEDOWN:
                    break;
                case Event.ONMOUSEUP:
                case Event.ONTOUCHEND:
                case Event.ONTOUCHCANCEL:
                    if (!moved) {
                        fireHeaderClickedEvent(event);
                        if (Util.isTouchEvent(event)) {
                            /*
                             * Prevent using in e.g. scrolling and prevent generated
                             * events.
                             */
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        break;
                    }
                    break;
                case Event.ONDBLCLICK:
                    fireHeaderClickedEvent(event);
                    break;
                case Event.ONTOUCHMOVE:
                case Event.ONMOUSEMOVE:
                    break;
                default:
                    break;
            }
        }

        /**
         * On resize event.
         *
         * @param event the event
         */
        private void onResizeEvent(Event event) {
            switch (DOM.eventGetType(event)) {
                case Event.ONMOUSEDOWN:
                    if (!Util.isTouchEventOrLeftMouseButton(event)) {
                        return;
                    }
                    isResizing = true;
                    
                    String tmp[] = findVisibleDoubleColumn(getColKey());
                    for (int i = 0; i < tmp.length; i++) {
                        String tmp1[] = findVisibleMainColumn(tmp[i]);
                        for (int j = 0; j < tmp1.length; j++) {
                            HeaderCell hc = tHead.getHeaderCell(tmp1[j]);
                            hc.isResizing = true;
                        }
                    }
                    DOM.setCapture(getElement());
                    dragStartX = DOM.eventGetClientX(event);
                    colIndex = getTripleHeaderColIndexByKey(cid);
                    originalWidth = getWidthWithIndent();
                    DOM.eventPreventDefault(event);
                    break;
                case Event.ONMOUSEUP:
                    if (!Util.isTouchEventOrLeftMouseButton(event)) {
                        return;
                    }
                    isResizing = false;
                    String tmp2[] = findVisibleDoubleColumn(getColKey());
                    for (int i = 0; i < tmp2.length; i++) {
                        String tmp1[] = findVisibleMainColumn(tmp2[i]);
                        for (int j = 0; j < tmp1.length; j++) {
                            HeaderCell hc = tHead.getHeaderCell(tmp1[j]);
                            hc.isResizing = false;
                        }
                    }
                    DOM.releaseCapture(getElement());
                     int childWidth1 = getTotalOffsetWidth();
                    int offW1 = getOffsetWidth();
                    if (childWidth1 > offW1) {
                        int newWidth = width + (childWidth1 - offW1);
                        setTripleHeaderColWidth(colIndex, newWidth, true, true);                        
                    }
                    ttHead.disableAutoColumnWidthCalculation();
                    tdHead.disableAutoColumnWidthCalculation();
                    tHead.disableAutoColumnWidthCalculation(null);
                    // Ensure last header cell is taking into account possible
                    // column selector
                    TripleHeaderCell lastCell = ttHead.getHeaderCell(ttHead
                            .getVisibleCellCount() - 1);
                    ttHead.resizeCaptionContainer(lastCell);
                    triggerLazyColumnAdjustment(true);

                    fireTripleHeaderColumnResizeEvent(cid, originalWidth, getTripleHeaderColWidth(cid));

                    break;
                case Event.ONMOUSEMOVE:
                    if (!Util.isTouchEventOrLeftMouseButton(event)) {
                        return;
                    }
                    if (isResizing) {
                        final int deltaX = DOM.eventGetClientX(event) - dragStartX;
                        if (deltaX == 0) {
                            return;
                        }
                        ttHead.disableAutoColumnWidthCalculation();

                        int newWidth = originalWidth + deltaX;
                        // get min width with indent, no padding
                        int minWidth = getMinWidth(true, false);
                        if (newWidth < minWidth) {
                            // already includes indent if any
                            newWidth = minWidth;
                        }
                        setTripleHeaderColWidth(colIndex, newWidth, true, true);
                        int childWidth = getTotalOffsetWidth();
                        int offW=getOffsetWidth();
                        if(childWidth>offW){
                            newWidth=width+(childWidth-offW);
                            setTripleHeaderColWidth(colIndex, newWidth, true, true);
                        } 
                        triggerLazyColumnAdjustment(false);
                        forceRealignColumnHeaders();
                    }
                    break;
                default:
                    break;
            }
        }

        /**
         * Returns the smallest possible cell width in pixels.
         *
         * @param includeIndent - width should include hierarchy column indent
         * if applicable (VTreeTable only)
         * @param includeCellExtraWidth - width should include paddings etc.
         * @return the min width
         */
        private int getMinWidth(boolean includeIndent,
                boolean includeCellExtraWidth) {
            String tmp[] = findVisibleDoubleColumn(getColKey());
            int tot = 0;
            for (int i = 0; i < tmp.length; i++) {
                DoubleHeaderCell hc = tdHead.getHeaderCell(tmp[i]);
                tot += hc.getMinWidth(includeIndent, includeCellExtraWidth);
            }

            return tot;
        }
        
        /**
         * Gets the min width.
         *
         * @return the min width
         */
        public int getMinWidth() {
            // get min width with padding, no indent
            return getMinWidth(false, true);
        }

        /**
         * Gets the caption.
         *
         * @return the caption
         */
        public String getCaption() {
            return DOM.getInnerText(captionContainer);
        }

        /**
         * Checks if is enabled.
         *
         * @return true, if is enabled
         */
        public boolean isEnabled() {
            return getParent() != null;
        }

        /**
         * Sets the align.
         *
         * @param c the new align
         */
        public void setAlign(char c) {
            align = c;
            updateStyleNames(VExtCustomScrollTable.this.getStylePrimaryName());
        }

        /**
         * Gets the align.
         *
         * @return the align
         */
        public char getAlign() {
            return align;
        }

        /**
         * Detects the natural minimum width for the column of this header cell.
         * If column is resized by user or the width is defined by server the
         * actual width is returned. Else the natural min width is returned.
         *
         * @return the natural column width
         */
        public int getNaturalColumnWidth() {

            return naturalWidth;

        }

        /**
         * Sets the expand ratio.
         *
         * @param floatAttribute the new expand ratio
         */
        public void setExpandRatio(float floatAttribute) {
            if (floatAttribute != expandRatio) {
                triggerLazyColumnAdjustment(false);
            }
            expandRatio = floatAttribute;
        }

        /**
         * Gets the expand ratio.
         *
         * @return the expand ratio
         */
        public float getExpandRatio() {
            return expandRatio;
        }

        /**
         * Checks if is sorted.
         *
         * @return true, if is sorted
         */
        public boolean isSorted() {
            return sorted;
        }
    }

    /**
     * HeaderCell that is header cell for row headers.
     *
     * Reordering disabled and clicking on it resets sorting.
     */
    public class RowHeadersTripleHeaderCell extends TripleHeaderCell {

        /**
         * Instantiates a new row headers triple header cell.
         */
        RowHeadersTripleHeaderCell() {
            super(ROW_HEADER_COLUMN_KEY, null);
            updateStyleNames(VExtCustomScrollTable.this.getStylePrimaryName());
        }

        /**
         * Update style names.
         *
         * @param primaryStyleName the primary style name
         */
        @Override
        protected void updateStyleNames(String primaryStyleName) {
            super.updateStyleNames(primaryStyleName);
            setStyleName(primaryStyleName + "-header-cell-rowheader");
        }

        /**
         * Handle caption event.
         *
         * @param event the event
         */
        @Override
        protected void handleCaptionEvent(Event event) {
            // NOP: RowHeaders cannot be reordered
            // TODO It'd be nice to reset sorting here
        }
    }

    /**
     * The Class TripleTableHead.
     */
    public class TripleTableHead extends Panel implements ActionOwner {

        /**
         * The Constant WRAPPER_WIDTH.
         */
        private static final int WRAPPER_WIDTH = 900000;
        /**
         * The visible cells.
         */
        ArrayList<Widget> visibleCells = new ArrayList<Widget>();
        /**
         * The available cells.
         */
        HashMap<String, TripleHeaderCell> availableCells = new HashMap<String, TripleHeaderCell>();
        /**
         * The div.
         */
        Element div = DOM.createDiv();
        /**
         * The h table wrapper.
         */
        Element hTableWrapper = DOM.createDiv();
        /**
         * The h table container.
         */
        Element hTableContainer = DOM.createDiv();
        /**
         * The table.
         */
        Element table = DOM.createTable();
        /**
         * The header table body.
         */
        Element headerTableBody = DOM.createTBody();
        /**
         * The tr.
         */
        Element tr = DOM.createTR();
        /**
         * The column selector.
         */
        private final Element columnSelector = DOM.createDiv();
        /**
         * The focused slot.
         */
        private int focusedSlot = -1;

        /**
         * Instantiates a new triple table head.
         */
        public TripleTableHead() {
            if (BrowserInfo.get().isIE()) {
                table.setPropertyInt("cellSpacing", 0);
            }

            DOM.setStyleAttribute(hTableWrapper, "overflow", "hidden");
            DOM.setStyleAttribute(columnSelector, "display", "none");

            DOM.appendChild(table, headerTableBody);
            DOM.appendChild(headerTableBody, tr);
            DOM.appendChild(hTableContainer, table);
            DOM.appendChild(hTableWrapper, hTableContainer);
            DOM.appendChild(div, hTableWrapper);
            DOM.appendChild(div, columnSelector);
            setElement(div);

            DOM.sinkEvents(columnSelector, Event.ONCLICK);

            availableCells.put(ROW_HEADER_COLUMN_KEY,
                    new RowHeadersTripleHeaderCell());
        }

        /**
         * Update style names.
         *
         * @param primaryStyleName the primary style name
         */
        protected void updateStyleNames(String primaryStyleName) {
            hTableWrapper.setClassName(primaryStyleName + "-header");
            columnSelector.setClassName(primaryStyleName + "-column-selector");
            setStyleName(primaryStyleName + "-header-wrap");
            for (TripleHeaderCell c : availableCells.values()) {
                c.updateStyleNames(primaryStyleName);
            }
        }

        /**
         * Resize caption container.
         *
         * @param cell the cell
         */
        public void resizeCaptionContainer(TripleHeaderCell cell) {
            TripleHeaderCell lastcell = getHeaderCell(visibleCells.size() - 1);
            int columnSelectorOffset = columnSelector.getOffsetWidth();

            if (cell == lastcell && columnSelectorOffset > 0
                    && !hasVerticalScrollbar()) {

                // Measure column widths
                int columnTotalWidth = 0;
                for (Widget w : visibleCells) {
                    int cellExtraWidth = w.getOffsetWidth();
                    if (scrollBody != null
                            && visibleCells.indexOf(w) == getHierarchyColumnIndex()
                            && cellExtraWidth < scrollBody.getMaxIndent()) {
                        // indent must be taken into consideration even if it
                        // hasn't been applied yet
                        columnTotalWidth += scrollBody.getMaxIndent();
                    } else {
                        columnTotalWidth += cellExtraWidth;
                    }
                }

                int divOffset = div.getOffsetWidth();
                if (columnTotalWidth >= divOffset - columnSelectorOffset) {
                    /*
                     * Ensure column caption is visible when placed under the
                     * column selector widget by shifting and resizing the
                     * caption.
                     */
                    int offset = 0;
                    int diff = divOffset - columnTotalWidth;
                    if (diff < columnSelectorOffset && diff > 0) {
                        /*
                         * If the difference is less than the column selectors
                         * width then just offset by the difference
                         */
                        offset = columnSelectorOffset - diff;
                    } else {
                        // Else offset by the whole column selector
                        offset = columnSelectorOffset;
                    }
                    lastcell.resizeCaptionContainer(offset);
                } else {
                    cell.resizeCaptionContainer(0);
                }
            } else {
                cell.resizeCaptionContainer(0);
            }
        }

        /**
         * Clear.
         */
        @Override
        public void clear() {
            for (String cid : availableCells.keySet()) {
                removeCell(cid);
            }
            availableCells.clear();
            availableCells.put(ROW_HEADER_COLUMN_KEY,
                    new RowHeadersTripleHeaderCell());
        }

        /**
         * Update cells radio from uidl.
         *
         * @param uidl the uidl
         */
        public void updateCellsRadioFromUIDL(UIDL uidl) {
            if (isReConstruct) {
                mapTripleRadio.clear();
                tripleRadioVal.clear();
            }
            Iterator<?> it = uidl.getChildIterator();

            while (it.hasNext()) {
                final UIDL col = (UIDL) it.next();
                final String cid = col.getStringAttribute("tripleradio");
                mapTripleRadio.put(cid, col.getStringArrayAttribute("tripleradioarr"));
                if (col.hasAttribute("tripleradioval")) {
                    tripleRadioVal.put(cid, col.getStringAttribute("tripleradioval"));
                }
            }
        }

        /**
         * Update cells from uidl.
         *
         * @param uidl the uidl
         * @param uidl1 the uidl1
         */
        public void updateCellsFromUIDL(UIDL uidl, UIDL uidl1) {
            if (isReConstruct) {
                mapTripleVisibleColumns.clear();
            }
            Iterator<?> it = uidl.getChildIterator();
            HashSet<String> updated = new HashSet<String>();
            while (it.hasNext()) {
                final UIDL col = (UIDL) it.next();
                final String cid = col.getStringAttribute("tcid");
                updated.add(cid);
                mapTripleVisibleColumns.put(cid, uidl1.getStringArrayAttribute(cid));
//                String caption = buildCustomCaptionHtmlSnippet(col);
                TripleHeaderCell c = getHeaderCell(cid);
                if (c == null) {
                    c = new TripleHeaderCell(cid, col);
                    availableCells.put(cid, c);
                    if (initializedAndAttached) {
                        // we will need a column width recalculation
                        initializedAndAttached = false;
                        initialContentReceived = false;
                        isNewBody = true;
                    }
                } else {
//                    c.setText(caption);
                    c.setCheckCaption(col);
                }
                if (col.hasAttribute("tsortable")) {
                    c.setSortable(true);
                    if (cid.equals(sortColumn)) {
                        c.setSorted(true);
                    } else {
                        c.setSorted(false);
                    }
                } else {
                    c.setSortable(false);
                }
                 boolean x = col.hasAttribute("eicon");
                c.setExpandIcon(x);
                if (x) {
                    c.setExpand(col.getBooleanAttribute("eicon"));
                }
                if (col.hasAttribute("talign")) {
                    c.setAlign(col.getStringAttribute("talign").charAt(0));
                } else {
                    c.setAlign(ALIGN_LEFT);

                }
                if (col.hasAttribute("twidth")) {
                    // Make sure to accomodate for the sort indicator if
                    // necessary.
                    int width = col.getIntAttribute("twidth");
                    int widthWithoutAddedIndent = width;
                    // get min width with indent, no padding
                    int minWidth = c.getMinWidth(true, false);
                    if (width < minWidth) {
                        width = minWidth;
                    }
                    if (scrollBody != null && width != c.getWidthWithIndent()) {
                        // Do a more thorough update if a column is resized from
                        // the server *after* the header has been properly
                        // initialized
                        final int colIx = getTripleHeaderColIndexByKey(c.cid);
                        final int newWidth = width;
                        Scheduler.get().scheduleDeferred(
                                new ScheduledCommand() {
                            @Override
                            public void execute() {
                                setTripleHeaderColWidth(colIx, newWidth, true, false);
                            }
                        });
                    } else {
                        // get min width with no indent or padding
                        minWidth = c.getMinWidth(false, false);
                        if (widthWithoutAddedIndent < minWidth) {
                            widthWithoutAddedIndent = minWidth;
                        }
                        // save min width without indent
                        c.setWidth(widthWithoutAddedIndent, true);
                    }
                } else if (col.hasAttribute("ter")) {
                    c.setExpandRatio(col.getFloatAttribute("ter"));

                } else if (recalcWidths) {
                    c.setUndefinedWidth();

                } else {
                    boolean hadExpandRatio = c.getExpandRatio() > 0;
                    boolean hadDefinedWidth = c.isDefinedWidth();
                    if (hadExpandRatio || hadDefinedWidth) {
                        // Someone has removed a expand width or the defined
                        // width on the server side (setting it to -1), make the
                        // column undefined again and measure columns again.
                        c.setUndefinedWidth();
                        c.setExpandRatio(0);

                    }
                }
                if (col.hasAttribute("tcollapsed")) {
                    // ensure header is properly removed from parent (case when
                    // collapsing happens via servers side api)
                    if (c.isAttached()) {
                        c.removeFromParent();
                        String tp[] = uidl1.getStringArrayAttribute(cid);
                        for (int i = 0; i < tp.length; i++) {
                            DoubleHeaderCell hc = tdHead.getHeaderCell(tp[i]);
                            if (hc.isAttached()) {
                                hc.removeFromParent();
                            }
                        }
                        headerChangedDuringUpdate = true;
                    }
                }
            }

        }

        /**
         * Enable column.
         *
         * @param cid the cid
         * @param index the index
         */
        public void enableColumn(String cid, int index) {
            final TripleHeaderCell c = getHeaderCell(cid);
            if (!c.isEnabled() || getHeaderCell(index) != c) {
                setHeaderCell(index, c);
                if (initializedAndAttached) {
                    headerChangedDuringUpdate = true;
                }
            }
        }

        /**
         * Gets the visible cell count.
         *
         * @return the visible cell count
         */
        public int getVisibleCellCount() {
            return visibleCells.size();
        }

        /**
         * Sets the horizontal scroll position.
         *
         * @param scrollLeft the new horizontal scroll position
         */
        public void setHorizontalScrollPosition(int scrollLeft) {
            hTableWrapper.setScrollLeft(scrollLeft);
        }

        /**
         * Sets the column collapsing allowed.
         *
         * @param cc the new column collapsing allowed
         */
        public void setColumnCollapsingAllowed(boolean cc) {
            tripleCollapsedColumnsallowed = cc;
            if (cc) {
                columnSelector.getStyle().setDisplay(Display.BLOCK);
            } else {
                columnSelector.getStyle().setDisplay(Display.NONE);
            }
        }

        /**
         * Disable browser intelligence.
         */
        public void disableBrowserIntelligence() {
            hTableContainer.getStyle().setWidth(WRAPPER_WIDTH, Unit.PX);
        }

        /**
         * Enable browser intelligence.
         */
        public void enableBrowserIntelligence() {
            hTableContainer.getStyle().clearWidth();
        }

        /**
         * Sets the header cell.
         *
         * @param index the index
         * @param cell the cell
         */
        public void setHeaderCell(int index, TripleHeaderCell cell) {
            if (cell.isEnabled()) {
                // we're moving the cell
                DOM.removeChild(tr, cell.getElement());
                orphan(cell);
                visibleCells.remove(cell);
            }
            if (index < visibleCells.size()) {
                // insert to right slot
                DOM.insertChild(tr, cell.getElement(), index);
                adopt(cell);
                visibleCells.add(index, cell);
            } else if (index == visibleCells.size()) {
                // simply append
                DOM.appendChild(tr, cell.getElement());
                adopt(cell);
                visibleCells.add(cell);
            } else {
                throw new RuntimeException(
                        "Header cells must be appended in order");
            }
        }

        /**
         * Gets the header cell.
         *
         * @param index the index
         * @return the header cell
         */
        public TripleHeaderCell getHeaderCell(int index) {
            if (index >= 0 && index < visibleCells.size()) {
                return (TripleHeaderCell) visibleCells.get(index);
            } else {
                return null;
            }
        }

        /**
         * Get's HeaderCell by it's column Key.
         *
         * Note that this returns HeaderCell even if it is currently collapsed.
         *
         * @param cid Column key of accessed HeaderCell
         * @return HeaderCell
         */
        public TripleHeaderCell getHeaderCell(String cid) {
            return availableCells.get(cid);
        }

        /**
         * Move cell.
         *
         * @param oldIndex the old index
         * @param newIndex the new index
         */
        public void moveCell(int oldIndex, int newIndex) {
            final TripleHeaderCell hCell = getHeaderCell(oldIndex);
            final Element cell = hCell.getElement();

            visibleCells.remove(oldIndex);
            DOM.removeChild(tr, cell);

            DOM.insertChild(tr, cell, newIndex);
            visibleCells.add(newIndex, hCell);
        }

        /**
         * Iterator.
         *
         * @return the iterator
         */
        @Override
        public Iterator<Widget> iterator() {
            return visibleCells.iterator();
        }

        /**
         * Removes the.
         *
         * @param w the w
         * @return true, if successful
         */
        @Override
        public boolean remove(Widget w) {
            if (visibleCells.contains(w)) {
                visibleCells.remove(w);
                orphan(w);
                DOM.removeChild(DOM.getParent(w.getElement()), w.getElement());
                return true;
            }
            return false;
        }

        /**
         * Removes the cell.
         *
         * @param colKey the col key
         */
        public void removeCell(String colKey) {
            final TripleHeaderCell c = getHeaderCell(colKey);
            remove(c);
        }

        /**
         * Focus slot.
         *
         * @param index the index
         */
        private void focusSlot(int index) {
            removeSlotFocus();
            if (index > 0) {
                Element child = tr.getChild(index - 1).getFirstChild().cast();
                child.setClassName(VExtCustomScrollTable.this
                        .getStylePrimaryName() + "-resizer");
                child.addClassName(VExtCustomScrollTable.this
                        .getStylePrimaryName() + "-focus-slot-right");
            } else {
                Element child = tr.getChild(index).getFirstChild().cast();
                child.setClassName(VExtCustomScrollTable.this
                        .getStylePrimaryName() + "-resizer");
                child.addClassName(VExtCustomScrollTable.this
                        .getStylePrimaryName() + "-focus-slot-left");
            }
            focusedSlot = index;
        }

        /**
         * Removes the slot focus.
         */
        private void removeSlotFocus() {
            if (focusedSlot < 0) {
                return;
            }
            if (focusedSlot == 0) {
                Element child = tr.getChild(focusedSlot).getFirstChild().cast();
                child.setClassName(VExtCustomScrollTable.this
                        .getStylePrimaryName() + "-resizer");
            } else if (focusedSlot > 0) {
                Element child = tr.getChild(focusedSlot - 1).getFirstChild()
                        .cast();
                child.setClassName(VExtCustomScrollTable.this
                        .getStylePrimaryName() + "-resizer");
            }
            focusedSlot = -1;
        }

        /**
         * On browser event.
         *
         * @param event the event
         */
        @Override
        public void onBrowserEvent(Event event) {
            if (enabled) {
                if (event.getEventTarget().cast() == columnSelector) {
                    final int left = DOM.getAbsoluteLeft(columnSelector);
                    final int top = DOM.getAbsoluteTop(columnSelector)
                            + DOM.getElementPropertyInt(columnSelector,
                            "offsetHeight");
                    client.getContextMenu().showAt(this, left, top);
                }
            }
        }

        /**
         * On detach.
         */
        @Override
        protected void onDetach() {
            super.onDetach();
            if (client != null) {
                client.getContextMenu().ensureHidden(this);
            }
        }

        /**
         * The Class TripleVisibleColumnAction.
         */
        class TripleVisibleColumnAction extends Action {

            /**
             * The col key.
             */
            String colKey;
            /**
             * The collapsed.
             */
            private boolean collapsed;
            /**
             * The noncollapsible.
             */
            private boolean noncollapsible = false;
            /**
             * The currently focused row.
             */
            private VScrollTableRow currentlyFocusedRow;

            /**
             * Instantiates a new triple visible column action.
             *
             * @param colKey the col key
             */
            public TripleVisibleColumnAction(String colKey) {
                super(VExtCustomScrollTable.TripleTableHead.this);
                this.colKey = colKey;
                caption = ttHead.getHeaderCell(colKey).getCaption();
                currentlyFocusedRow = focusedRow;
            }

            /**
             * Execute.
             */
            @Override
            public void execute() {
                if (noncollapsible) {
                    return;
                }
                client.getContextMenu().hide();
                boolean sendSingle = false;
                boolean sendDouble = false;
                // toggle selected column
                String tp[] = findDoubleHeaderMappedKeyFromTriple(colKey);
                if (tripleCollapsedColumns.contains(colKey)) {
                    tripleCollapsedColumns.remove(colKey);
                    for (int i = 0; i < tp.length; i++) {
                        if (doublecollapsedColumns.contains(tp[i])) {
                            sendDouble = true;
                            doublecollapsedColumns.remove(tp[i]);
                        }
                        String dtp[] = findMappedKey(tp[i]);
                        for (int j = 0; j < dtp.length; j++) {
                            if (collapsedColumns.contains(dtp[j])) {
                                sendSingle = true;
                                collapsedColumns.remove(dtp[j]);
                            }
                        }
                    }
                } else {
                    ttHead.removeCell(colKey);
                    tripleCollapsedColumns.add(colKey);
                    for (int i = 0; i < tp.length; i++) {
                        sendDouble = true;
                        tdHead.removeCell(tp[i]);
                        doublecollapsedColumns.add(tp[i]);
                        String dtp[] = findMappedKey(tp[i]);
                        for (int j = 0; j < dtp.length; j++) {
                            sendSingle = true;
                            tHead.removeCell(dtp[j]);
                            collapsedColumns.add(dtp[j]);
                        }
                    }
                    triggerLazyColumnAdjustment(true);
                }
                // update variable to server
                if (sendSingle) {
                    client.updateVariable(paintableId, "collapsedcolumns",
                            collapsedColumns.toArray(new String[collapsedColumns
                            .size()]), false);
                }
                if (sendDouble) {
                    client.updateVariable(paintableId, "doublecollapsedcolumns",
                            doublecollapsedColumns.toArray(new String[doublecollapsedColumns
                            .size()]), false);
                }
                client.updateVariable(paintableId, "triplecollapsedcolumns",
                        tripleCollapsedColumns.toArray(new String[tripleCollapsedColumns
                        .size()]), true);
                rowRequestHandler.refreshContent();
                lazyRevertFocusToRow(currentlyFocusedRow);
            }

            /**
             * Sets the collapsed.
             *
             * @param b the new collapsed
             */
            public void setCollapsed(boolean b) {
                collapsed = b;
            }

            /**
             * Sets the noncollapsible.
             *
             * @param b the new noncollapsible
             */
            public void setNoncollapsible(boolean b) {
                noncollapsible = b;
            }

            /**
             * Override default method to distinguish on/off columns.
             *
             * @return the html
             */
            @Override
            public String getHTML() {
                final StringBuffer buf = new StringBuffer();
                buf.append("<span class=\"");
                if (collapsed) {
                    buf.append("v-off");
                } else {
                    buf.append("v-on");
                }
                if (noncollapsible) {
                    buf.append(" v-disabled");
                }
                buf.append("\">");

                buf.append(super.getHTML());
                buf.append("</span>");

                return buf.toString();
            }
        }

        /*
         * Returns columns as Action array for column select popup
         */
        /**
         * Gets the actions.
         *
         * @return the actions
         */
        @Override
        public Action[] getActions() {
            Object[] cols;
            if (columnReordering && tripleColumnOrder != null) {
                cols = tripleColumnOrder;
            } else {
                // if columnReordering is disabled, we need different way to get
                // all available columns
                cols = tripleVisibleColOrder;
                cols = new Object[tripleVisibleColOrder.length
                        + tripleCollapsedColumns.size()];
                int i;
                for (i = 0; i < tripleVisibleColOrder.length; i++) {
                    cols[i] = tripleVisibleColOrder[i];
                }
                for (final Iterator<String> it = tripleCollapsedColumns.iterator(); it
                        .hasNext();) {
                    cols[i++] = it.next();
                }
            }
            final Action[] actions = new Action[cols.length];

            for (int i = 0; i < cols.length; i++) {
                final String cid = (String) cols[i];
                final TripleHeaderCell c = getHeaderCell(cid);
                final TripleVisibleColumnAction a = new TripleVisibleColumnAction(
                        c.getColKey());
                a.setCaption(c.getCaption());
                if (!c.isEnabled()) {
                    a.setCollapsed(true);
                }
                if (tripleNonCollapsibleColumns.contains(cid)) {
                    a.setNoncollapsible(true);
                }
                actions[i] = a;
            }
            return actions;
        }

        /**
         * Gets the client.
         *
         * @return the client
         */
        @Override
        public ApplicationConnection getClient() {
            return client;
        }

        /**
         * Gets the paintable id.
         *
         * @return the paintable id
         */
        @Override
        public String getPaintableId() {
            return paintableId;
        }

        /**
         * Returns column alignments for visible columns.
         *
         * @return the column alignments
         */
        public char[] getColumnAlignments() {
            final Iterator<Widget> it = visibleCells.iterator();
            final char[] aligns = new char[visibleCells.size()];
            int colIndex = 0;
            while (it.hasNext()) {
                aligns[colIndex++] = ((TripleHeaderCell) it.next()).getAlign();
            }
            return aligns;
        }

        /**
         * Disables the automatic calculation of all column widths by forcing
         * the widths to be "defined" thus turning off expand ratios and such.
         *
         * @param source the source
         */
        public void disableAutoColumnWidthCalculation() {
            for (TripleHeaderCell cell : availableCells.values()) {
                cell.disableAutoWidthCalculation();
            }
            // fire column resize events for all columns but the source of the
            // resize action, since an event will fire separately for this.
            ArrayList<TripleHeaderCell> columns = new ArrayList<TripleHeaderCell>(
                    availableCells.values());
            sendTripleHeaderColumnWidthUpdates(columns);
            forceRealignColumnHeaders();
        }
    }

    /**
     * Fires a column resize event which sends the resize information to the
     * server.
     *
     * @param columnId The columnId of the column which was resized
     * @param originalWidth The width in pixels of the column before the resize
     * event
     * @param newWidth The width in pixels of the column after the resize event
     */
    private void fireTripleHeaderColumnResizeEvent(String columnId, int originalWidth,
            int newWidth) {
        client.updateVariable(paintableId, "tripleColumnResizeEventColumn", columnId,
                false);
        client.updateVariable(paintableId, "tripleColumnResizeEventPrev",
                originalWidth, false);
        client.updateVariable(paintableId, "tripleColumnResizeEventCurr", newWidth,
                immediate);

    }

    /**
     * Non-immediate variable update of column widths for a collection of
     * columns.
     *
     * @param columns the columns to trigger the events for.
     */
    private void sendTripleHeaderColumnWidthUpdates(Collection<TripleHeaderCell> columns) {
        String[] newSizes = new String[columns.size()];
        int ix = 0;
        for (TripleHeaderCell cell : columns) {
            newSizes[ix++] = cell.getColKey() + ":" + cell.getWidth();
        }
        client.updateVariable(paintableId, "tripleColumnWidthUpdates", newSizes,
                false);
    }

    /**
     * Update triple header column properties.
     *
     * @param uidl the uidl
     */
    public void updateTripleHeaderColumnProperties(UIDL uidl) {

        if (showTripleColHeaders) {
            updateTripleHeaderColumnOrder(uidl);
            updateTripleHeaderCollapsedColumns(uidl);
            UIDL vc = uidl.getChildByTagName("triplevisiblecolumns");
            UIDL vc1 = uidl.getChildByTagName("tripleradiocolumns");
            if (vc1 != null) {
                ttHead.updateCellsRadioFromUIDL(vc1);
            }
            if (vc != null) {

                ttHead.updateCellsFromUIDL(vc, uidl.getChildByTagName("triplevisiblecolumnmap"));
            }
            ttHead.addStyleName("triple-header");
            updateTripleHeader(uidl.getStringArrayAttribute("triplevcolorder"));
//            if (collapsedColumnsallowed) {
//                updateDoubleMainHeaderCollapsedColumns();
//            }
            if (isReConstruct && tripleNonCollapsibleColumns != null) {
                tripleNonCollapsibleColumns.clear();
            }
            if (uidl.hasVariable("triplenoncollapsiblecolumns")) {
                tripleNonCollapsibleColumns = uidl
                        .getStringArrayVariableAsSet("triplenoncollapsiblecolumns");
            }
        }
        ttHead.setVisible(showTripleColHeaders);
        setContainerHeight();

    }

    /**
     * Update double main header collapsed columns.
     */
    private void updateDoubleMainHeaderCollapsedColumns() {

        for (String s : tripleCollapsedColumns) {
            String tmp[] = findDoubleHeaderMappedKeyFromTriple(s);
            for (int i = 0; i < tmp.length; i++) {
                if (!doublecollapsedColumns.contains(tmp[i])) {
                    doublecollapsedColumns.add(tmp[i]);
                }
            }
        }
        updateMainHeaderCollapsedColumns();
        client.updateVariable(paintableId, "doublecollapsedColumns",
                doublecollapsedColumns.toArray(new String[doublecollapsedColumns
                .size()]), false);
    }

    /**
     * Update triple header collapsed columns.
     *
     * @param uidl the uidl
     */
    private void updateTripleHeaderCollapsedColumns(UIDL uidl) {
        if (uidl.hasVariable("triplecollapsedcolumns")) {
            ttHead.setColumnCollapsingAllowed(true);
            String[] s2 = uidl.getStringArrayVariable("triplecollapsedcolumns");
            String[] s1 = new String[tripleCollapsedColumns.size()];
            int j = 0;
            for (String s : tripleCollapsedColumns) {
                s1[j] = s;
                j++;
            }
            Arrays.sort(s2);
            Arrays.sort(s1);
            if (!Arrays.equals(s1, s2)) {
                sizeNeedsInit = true;
            }
            tripleCollapsedColumns = uidl
                    .getStringArrayVariableAsSet("triplecollapsedcolumns");
            for (int i = 0; i < tripleCollapsedColumns.size(); i++) {
            }
        } else {
            ttHead.setColumnCollapsingAllowed(false);
        }
    }

    /**
     * Update triple header column order.
     *
     * @param uidl the uidl
     */
    private void updateTripleHeaderColumnOrder(UIDL uidl) {
        if (uidl.hasVariable("triplecolumnorder")) {
            tripleColumnOrder = uidl.getStringArrayVariable("triplecolumnorder");
        } else {
            tripleColumnOrder = null;
        }
    }

    /**
     * Gets the triple header col index by key.
     *
     * @param colKey the col key
     * @return the triple header col index by key
     */
    private int getTripleHeaderColIndexByKey(String colKey) {
        // return 0 if asked for rowHeaders
        if (ROW_HEADER_COLUMN_KEY.equals(colKey)) {
            return 0;
        }
        if (tripleVisibleColOrder != null) {
            for (int i = 0; i < tripleVisibleColOrder.length; i++) {
                if (tripleVisibleColOrder[i].equals(colKey)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Gets the triple header col key by index.
     *
     * @param index the index
     * @return the triple header col key by index
     */
    private String getTripleHeaderColKeyByIndex(int index) {
        return ttHead.getHeaderCell(index).getColKey();
    }

    /**
     * Note: not part of the official API, extend at your own risk. May be
     * removed or replaced in the future.
     *
     * Sets the indicated column's width for headers and scrollBody alike.
     *
     * @param colIndex index of the modified column
     * @param w new width (may be subject to modifications if doesn't meet
     * minimum requirements)
     * @param isDefinedWidth disables expand ratio if set true
     * @param updateMap the update map
     */
    protected void setTripleHeaderColWidth(int colIndex, int w, boolean isDefinedWidth, boolean updateMap) {
        final TripleHeaderCell hcell = ttHead.getHeaderCell(colIndex);
        // Make sure that the column grows to accommodate the sort indicator if
        // necessary.
        // get min width with no indent or padding
        if (hcell != null) {
            int minWidth = hcell.getMinWidth(false, false);
            if (w < minWidth) {
                w = minWidth;
            }

            // Set header column width WITHOUT INDENT
            hcell.setWidth(w, isDefinedWidth);

            if (updateMap) {
                updateDoubleMainColumnWidth(hcell.getColKey(), w, isDefinedWidth, updateMap);
            }
        }
    }
     /**
     * Gets the Triple Header column width.
     *
     * @param tColKey the Triple Header column key
     * @return the Triple Header column width
     */
    private int getTripleHeaderColWidth(String tColKey) {
        return ttHead.getHeaderCell(tColKey).getWidthWithIndent();
    }

    /**
     * Gets the Triple Header column width with extra space.
     *
     * @param tColKey the Triple Header column key
     * @return the Triple Header column width with extra space
     */
    private int getTripleHeaderColExtraWidth(String tColKey) {
        return ttHead.getHeaderCell(tColKey).getTotalWidthWithIndent();
    }
    /**
     * Update double main column width.
     *
     * @param tColKey the t col key
     * @param twidth the twidth
     * @param isDefinedWidth the is defined width
     * @param updateMap the update map
     */
    private void updateDoubleMainColumnWidth(String tColKey, int twidth, boolean isDefinedWidth, boolean updateMap) {
        String[] a = findVisibleDoubleColumn(tColKey);
        if (a.length > 0) {
            int tot = 0;
            int width = twidth / a.length;
            for (int i = 0; i < a.length - 1; i++) {
                setDoubleHeaderColWidth(getDoubleHeaderColIndexByKey(a[i]), width - scrollBody.getCellExtraWidth(), isDefinedWidth, updateMap);
                tot = tot + width;
            }
            setDoubleHeaderColWidth(getDoubleHeaderColIndexByKey(a[a.length - 1]), twidth - tot, isDefinedWidth, updateMap);
            tdHead.disableAutoColumnWidthCalculation();
        }
    }

    /**
     * Find visible double column.
     *
     * @param tColKey the t col key
     * @return the string[]
     */
    private String[] findVisibleDoubleColumn(String tColKey) {
        ArrayList<String> ob = new ArrayList<String>();
        String[] a = findDoubleHeaderMappedKeyFromTriple(tColKey);
        for (int i = 0; i < a.length; i++) {
            if (collapsedColumnsallowed) {
                if (!doublecollapsedColumns.contains(a[i])) {
                    ob.add(a[i]);
                }
            } else {
                ob.add(a[i]);
            }
        }
        String[] b = new String[ob.size()];
        for (int i = 0; i < ob.size(); i++) {
            b[i] = ob.get(i);

        }
        return b;
    }


    /**
     * Find double header mapped key from triple.
     *
     * @param tColKey the t col key
     * @return the string[]
     */
    private String[] findDoubleHeaderMappedKeyFromTriple(String tColKey) {
        return mapTripleVisibleColumns.get(tColKey);
    }

    /**
     * Find single header mapped key from triple.
     *
     * @param tColKey the t col key
     * @return the string[]
     */
    private String[] findSingleHeaderMappedKeyFromTriple(String tColKey) {
        ArrayList<String> ob = new ArrayList<String>();
        for (String s : findDoubleHeaderMappedKeyFromTriple(tColKey)) {
            for (String s1 : findMappedKey(s)) {
                ob.add(s1);
            }
        }
        String[] b = new String[ob.size()];
        for (int i = 0; i < ob.size(); i++) {
            b[i] = ob.get(i);

        }
        return b;
    }

    /**
     * Find visible single column.
     *
     * @param tColKey the t col key
     * @return the string[]
     */
    private String[] findVisibleSingleColumn(String tColKey) {
        ArrayList<String> ob = new ArrayList<String>();
        for (String s : findDoubleHeaderMappedKeyFromTriple(tColKey)) {
            for (String s1 : findMappedKey(s)) {
                if (collapsedColumnsallowed) {
                    if (!collapsedColumns.contains(s1)) {
                        ob.add(s1);
                    }
                } else {
                    ob.add(s1);
                }
            }
        }
        String[] b = new String[ob.size()];
        for (int i = 0; i < ob.size(); i++) {
            b[i] = ob.get(i);

        }
        return b;
    }

    /**
     * Find triple header mapped key.
     *
     * @param colKey the col key
     * @return the string
     */
    private String findTripleHeaderMappedKey(String colKey) {
        for (String tColKey : mapTripleVisibleColumns.keySet()) {
            String tmp[] = findDoubleHeaderMappedKeyFromTriple(tColKey);
            for (String tmp1 : tmp) {
                if (colKey.equals(tmp1)) {
                    return tColKey;
                }
            }
        }
        return null;
    }


    /**
     * Update triple header.
     *
     * @param strings the strings
     */
    private void updateTripleHeader(String[] strings) {
        if (strings == null) {
            return;
        }

        int visibleCols = strings.length;
        int colIndex = 0;
        if (showRowHeaders) {
            ttHead.enableColumn(ROW_HEADER_COLUMN_KEY, colIndex);
            visibleCols++;
            tripleVisibleColOrder = new String[visibleCols];
            tripleVisibleColOrder[colIndex] = ROW_HEADER_COLUMN_KEY;
            colIndex++;
        } else {
            tripleVisibleColOrder = new String[visibleCols];
            ttHead.removeCell(ROW_HEADER_COLUMN_KEY);
        }

        int i;
        for (i = 0; i < strings.length; i++) {
            final String cid = strings[i];
            tripleVisibleColOrder[colIndex] = cid;
            ttHead.enableColumn(cid, colIndex);
            colIndex++;
        }
    }



    /**
     * Send triple header check box updates.
     */
    private void sendTripleHeaderCheckBoxUpdates() {
        int x = ttHead.getVisibleCellCount();
        ArrayList<TripleHeaderCell> columns = new ArrayList<TripleHeaderCell>();
        for (int i = 0; i < x; i++) {
            TripleHeaderCell cell = ttHead.getHeaderCell(i);
            if (cell.ischeckbox) {
                columns.add(cell);
            }
        }
        String[] newSizes = new String[columns.size()];
        int ix = 0;
        for (TripleHeaderCell celll : columns) {
            newSizes[ix++] = celll.getColKey() + ":" + celll.isChecked();
        }
        client.updateVariable(paintableId, "tripleColumnCheckBoxUpdates", newSizes,
                true);
    }

    /**
     * Fire triple header column check event.
     *
     * @param columnId the column id
     * @param newNewValue the new new value
     */
    private void fireTripleHeaderColumnCheckEvent(String columnId, boolean newNewValue) {
        client.updateVariable(paintableId, "tripleColumnCheckEventColumn", columnId,
                false);
        client.updateVariable(paintableId, "tripleColumnCheckEventCurr", newNewValue,
                immediate);

    }

    /**
     * Send triple header radio updates.
     */
    private void sendTripleHeaderRadioUpdates() {
        String[] newSizes = new String[tripleRadioVal.size()];
        Set<String> x = tripleRadioVal.keySet();
        int i = 0;
        for (String ob : x) {
            newSizes[i++] = ob + ":" + tripleRadioVal.get(ob);
        }

        client.updateVariable(paintableId, "tripleColumnRadioUpdates", newSizes,
                true);
    }

    /**
     * Fire triple header column radio event.
     *
     * @param radioName the radio name
     * @param previousHeader the previous header
     * @param currentHeader the current header
     * @param originalValue the original value
     * @param newNewValue the new new value
     */
    private void fireTripleHeaderColumnRadioEvent(String radioName, String previousHeader,
            String currentHeader, String originalValue,
            String newNewValue) {
        client.updateVariable(paintableId, "tripleColumnRadioEventName", radioName,
                false);
        client.updateVariable(paintableId, "tripleColumnRadioEventPrev",
                originalValue, false);
        client.updateVariable(paintableId, "tripleColumnRadioEventCurHeader", currentHeader,
                false);
        client.updateVariable(paintableId, "tripleColumnRadioEventPrevHeader",
                previousHeader, false);
        client.updateVariable(paintableId, "tripleColumnRadioEventCurr", newNewValue,
                immediate);

    }

    /**
     * Sets the triple radio val.
     *
     * @param key the key
     * @param val the val
     */
    public void setTripleRadioVal(String key, String val) {
        tripleRadioVal.put(key, val);
        uncheckTripleRadioVal(key, val);
        ttHead.getHeaderCell(val).setRadioChecked(true);
        if (radioButtonSinks) {
            uncheckDoubleRadioVal(key, val);
            uncheckSingleRadioVal(key, val);
            removeDoubleRadioVal(key);
            removeSingleRadioVal(key);
        }
    }

    /**
     * Gets the triple radio val.
     *
     * @param key the key
     * @return the triple radio val
     */
    public String getTripleRadioVal(String key) {
        if (tripleRadioVal.containsKey(key)) {
            return tripleRadioVal.get(key);
        } else {
            return null;
        }
    }

    /**
     * Gets the triple radio key.
     *
     * @param val the val
     * @return the triple radio key
     */
    public String getTripleRadioKey(String val) {
        Set<String> ts = mapTripleRadio.keySet();
        String[] s = new String[ts.size()];
        int k = 0;
        for (String ss : ts) {
            s[k] = ss;
            k++;
        }
        for (int i = 0; i < s.length; i++) {
            String[] valary = mapTripleRadio.get(s[i]);
            for (int j = 0; j < valary.length; j++) {
                if (valary[j].equals(val)) {
                    return s[i];
                }
            }
        }
        return null;
    }

    /**
     * Send triple header expand icon updates.
     */
    private void sendTripleHeaderExpandIconUpdates(boolean immediate) {
        int x = ttHead.getVisibleCellCount();
        ArrayList<TripleHeaderCell> columns = new ArrayList<TripleHeaderCell>();
        for (int i = 0; i < x; i++) {
            TripleHeaderCell cell = ttHead.getHeaderCell(i);
            if (cell.isExpandIcon()) {
                columns.add(cell);
            }
        }
        String[] newSizes = new String[columns.size()];
        int ix = 0;
        for (TripleHeaderCell celll : columns) {
            newSizes[ix++] = celll.getColKey() + ":" + celll.isExpand();
        }
        client.updateVariable(paintableId, "tripleColumnExpandIconUpdates", newSizes,
                true);
    }

    /**
     * Fire triple header column expand icon event.
     *
     * @param columnId the column id
     * @param newNewValue the new new value
     */
    private void fireTripleHeaderColumnExpandIconEvent(String columnId, boolean newNewValue) {
        client.updateVariable(paintableId, "tripleColumnExpandIconEventColumn", columnId,
                false);
        client.updateVariable(paintableId, "tripleColumnExpandIconEventCurr", newNewValue,
                immediate);

    }

    /**
     * Uncheck double radio val.
     *
     * @param key the key
     * @param val the val
     */
    private void uncheckDoubleRadioVal(String key, String val) {
        if (mapDoubleRadio.containsKey(key)) {
            for (String s : mapDoubleRadio.get(key)) {
                tdHead.getHeaderCell(s).setRadioChecked(false);
            }
        }
    }

    /**
     * Uncheck single radio val.
     *
     * @param key the key
     * @param val the val
     */
    private void uncheckSingleRadioVal(String key, String val) {
        if (mapSingleRadio.containsKey(key)) {
            for (String s : mapSingleRadio.get(key)) {
                tHead.getHeaderCell(s).setRadioChecked(false);
            }
        }
    }

    /**
     * Uncheck triple radio val.
     *
     * @param key the key
     * @param val the val
     */
    private void uncheckTripleRadioVal(String key, String val) {
        if (mapTripleRadio.containsKey(key)) {
            for (String s : mapTripleRadio.get(key)) {
                ttHead.getHeaderCell(s).setRadioChecked(false);
            }
        }
    }

    /**
     * Removes the double radio val.
     *
     * @param key the key
     */
    private void removeDoubleRadioVal(String key) {
        if (doubleRadioVal.containsKey(key)) {
            doubleRadioVal.remove(key);
        }
    }

    /**
     * Removes the single radio val.
     *
     * @param key the key
     */
    private void removeSingleRadioVal(String key) {
        if (singleRadioVal.containsKey(key)) {
            singleRadioVal.remove(key);
        }
    }

    /**
     * Removes the triple radio val.
     *
     * @param key the key
     */
    private void removeTripleRadioVal(String key) {
        if (tripleRadioVal.containsKey(key)) {
            tripleRadioVal.remove(key);
        }
    }
}
