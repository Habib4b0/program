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

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.ScrollEvent;
import com.google.gwt.event.dom.client.ScrollHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.Focusable;
import com.vaadin.client.MeasuredSize;
import com.vaadin.client.Util;
import com.vaadin.client.ui.dd.VLazyInitItemIdentifiers;
import com.vaadin.shared.ui.dd.AcceptCriterion;
import com.vaadin.ui.ExtCustomTable;
import java.util.HashMap;
import java.util.Map;
import org.asi.ui.extfilteringtable.client.VExtCustomScrollTable.VScrollTableBody.VScrollTableRow;


/**
 * The Class VExtFilterTable.
 *
 * @author Abhiram
 */
public class VExtFilterTable extends VExtCustomScrollTable {

    /**
     * The Class VTableLazyInitItemIdentifiers.
     */
    @AcceptCriterion(ExtCustomTable.TableDropCriterion.class)
    final public static class VTableLazyInitItemIdentifiers extends
            VLazyInitItemIdentifiers {
        // all logic in superclass
    }

    /* Custom FlowPanel for the column filterWidget */
    /** The filterWidget. */
    FilterPanel filters;
    
    /**
     * Instantiates a new v ext filter table.
     */
    public VExtFilterTable() {
        super();

        /* Create filter panel and insert between CustomTable Header and Content */
        filters = new FilterPanel();
        insert(filters, 3);

        /*
         * Do not display the filter bar initially.
         * 
         * This is a tentative fix for a weird issue where the width of the
         * FilteringTable is not calculated correctly when it is contained
         * within a Window.
         */
        filters.getElement().getStyle().setDisplay(Display.NONE);
    }
    
    /**
     * Gets the filter hieght.
     *
     * @return the filter hieght
     */
    @Override
    protected int getFilterHieght(){
        int height=0;
        if (filters.isVisible()) {
                height = filters.getOffsetHeight();
            }
        return height;
    }
    
    /**
     * Sets the col width.
     *
     * @param colIndex the col index
     * @param w the w
     * @param isDefinedWidth the is defined width
     */
    @Override
    protected void setColWidth(int colIndex, int w, boolean isDefinedWidth) {
        super.setColWidth(colIndex, w, isDefinedWidth);
        filters.setFilterWidth(colIndex);
    }

    /**
     * Sets the content width.
     *
     * @param pixels the new content width
     */
    @Override
    protected void setContentWidth(int pixels) {        
        super.setContentWidth(pixels);
        filters.setWidth(pixels + "px");
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
        filters.reRenderFilterComponents();
    }

    /**
     * On scroll.
     *
     * @param event the event
     */
    @Override
    public void onScroll(ScrollEvent event) {
        super.onScroll(event);
        filters.setScrollLeft(scrollLeft);
    }

//    /**
//     * Gets the col key by index.
//     *
//     * @param index the index
//     * @return the col key by index
//     */
//    @Override
//    protected String getColKeyByIndex(int index) {
//        HeaderCell hc = tHead.getHeaderCell(index);
//        return hc == null ? null : hc.getColKey();
//    }

    /**
     * On focus.
     *
     * @param event the event
     */
    @Override
    public void onFocus(FocusEvent event) {
        super.onFocus(event);
    }

    /**
     * The Class FilterPanel.
     */
    class FilterPanel extends FlowPanel implements ScrollHandler {
        /* Column filter components - mapped by column keys */
        /** The filterWidget. */
        Map<String, Widget> filterWidget = new HashMap<String, Widget>();
        /* Set to true to render the filter bar */
        /** The filterWidget visible. */
        boolean filtersVisible;
        /* Wrapper that holds the filter component container */
        /** The wrap. */
        private final FlowPanel wrap = new FlowPanel();
        /* Actual container for the filter components */
        /** The container. */
        FlowPanel container = new FlowPanel();

        /* Wrap filterWidget with additional div for styling? */
        /** The wrap filterWidget. */
        boolean wrapFilters = false;

        /**
         * Instantiates a new filter panel.
         */
        public FilterPanel() {
            container.setStyleName("filters-panel");
            DOM.setStyleAttribute(wrap.getElement(), "overflow", "hidden");
            setStyleName("filters-wrap");
            wrap.sinkEvents(Event.ONSCROLL);
            wrap.addDomHandler(this, ScrollEvent.getType());
            wrap.add(container);
            add(wrap);
        }

        /**
         * Re render filter components.
         */
        void reRenderFilterComponents() {
            container.clear();
            if (!wrapFilters) {
                reRenderNotWrappedFilterComponents();
            } else {
                reRenderWrappedFilterComponents();
            }
        }

        /**
         * Re render not wrapped filter components.
         */
        private void reRenderNotWrappedFilterComponents() {
            for (int i = 0; i < tHead.getVisibleCellCount(); i++) {
                String key = getColKeyByIndex(i);
                if (key == null) {
                    continue;
                }
                Widget widget = filterWidget.get(key);

                if (widget == null) {
                    /* No filter defined */
                    /* Use a place holder of the correct width */
                    Widget placeHolder = new FlowPanel();
                    placeHolder.addStyleName("filterplaceholder");
                    container.add(placeHolder);
                    filterWidget.put(key, placeHolder);
                    setFilterWidth(i);
                } else {
                    container.add(widget);
                    setFilterWidth(i);
                }
            }
        }

        /**
         * Re render wrapped filter components.
         */
        private void reRenderWrappedFilterComponents() {
            // Remember height
            MeasuredSize ms = new MeasuredSize();
            ms.measure(container.getElement());
            int height = ms.getInnerHeight();

            int visibleCellCount = tHead.getVisibleCellCount();
            for (int i = 0; i < visibleCellCount; i++) {
                String key = getColKeyByIndex(i);
                if (key == null) {
                    continue;
                }
                Widget widget = filterWidget.get(key);
                SimplePanel wrapper = new SimplePanel();
                wrapper.addStyleName("filterwrapper");

                if (0 == i) {
                    wrapper.addStyleName("filterwrapper-first");
                }
                if (visibleCellCount - 1 == i) {
                    wrapper.addStyleName("filterwrapper-last");
                }

                if (widget == null) {
                    /* No filter defined */
                    /* Use a place holder of the correct width */
                    Widget placeHolder = new FlowPanel();
                    placeHolder.addStyleName("filterplaceholder");
                    wrapper.setWidget(placeHolder);
                    container.add(wrapper);
                    filterWidget.put(key, placeHolder);
                    setWrappedFilterWidth(i);
                } else {
                    wrapper.setWidget(widget);
                    container.add(wrapper);
                    setWrappedFilterWidth(i);
                }

                // deal with wrapper height
                MeasuredSize wrapperSize = new MeasuredSize();
                wrapperSize.measure(wrapper.getElement());
                int correction = wrapperSize.getMarginHeight()
                        + wrapperSize.getBorderHeight()
                        + wrapperSize.getPaddingHeight();
                int wrapperHeight = height - correction;
                // ensure no negative heights
                wrapperHeight = wrapperHeight > 0 ? wrapperHeight : 0;
                wrapper.setHeight(wrapperHeight + "px");
            }
        }

        /**
         * Sets the scroll left.
         *
         * @param scrollLeft the new scroll left
         */
        public void setScrollLeft(int scrollLeft) {
            wrap.getElement().setScrollLeft(scrollLeft);
        }

        /**
         * Sets the not wrapped filter width.
         *
         * @param index the new not wrapped filter width
         */
        private void setNotWrappedFilterWidth(int index) {
            Widget p = filterWidget.get(getColKeyByIndex(index));
            if (p != null) {
                /* Try to get width from header cell */
                int w = Util.getRequiredWidth(tHead.getHeaderCell(index));
                if (w <= 0) {
                    /* Header not available, try first rendered row */
                    VScrollTableRow firstRow = scrollBody
                            .getRowByRowIndex(scrollBody.getFirstRendered());
                    final Element cell = DOM.getChild(firstRow.getElement(),
                            index);
                    w = Util.getRequiredWidth(cell);
                }
                MeasuredSize measuredSize = new MeasuredSize();
                measuredSize.measure(p.getElement());
                w -= measuredSize.getMarginWidth();
                /* Ensure no negative widths are set */
                w = w > 0 ? w : 0;
                p.setWidth(w + "px");
            }
        }

        /**
         * Sets the wrapped filter width.
         *
         * @param index the new wrapped filter width
         */
        private void setWrappedFilterWidth(int index) {
            Widget widget = filterWidget.get(getColKeyByIndex(index));
            if (null != widget) {
                Widget wrapper = widget.getParent();
                int wrapperWidth = Util.getRequiredWidth(tHead
                        .getHeaderCell(index));
                if (wrapperWidth <= 0) {
                    VScrollTableRow firstRow = scrollBody
                            .getRowByRowIndex(scrollBody.getFirstRendered());
                    final Element cell = DOM.getChild(firstRow.getElement(),
                            index);
                    wrapperWidth = Util.getRequiredWidth(cell);
                }
                MeasuredSize wrapperSize = new MeasuredSize();
                wrapperSize.measure(wrapper.getElement());
                int wrapperCorrections = wrapperSize.getMarginWidth()
                        + wrapperSize.getBorderWidth()
                        + wrapperSize.getPaddingWidth();
                wrapperWidth = wrapperWidth - wrapperCorrections;
                wrapperWidth = wrapperWidth > 0 ? wrapperWidth : 0;
                wrapper.setWidth(wrapperWidth + "px");

                if (0 < wrapperWidth) {
                    int widgetWidth = wrapperWidth;
                    MeasuredSize widgetSize = new MeasuredSize();
                    widgetSize.measure(widget.getElement());
                    widgetWidth -= widgetSize.getMarginWidth();
                    widgetWidth = widgetWidth > 0 ? widgetWidth : 0;
                    widget.setWidth(widgetWidth + "px");
                }
            }
        }

        /**
         * On scroll.
         *
         * @param event the event
         */
        @Override
        public void onScroll(ScrollEvent event) {
            if (!isAttached()) {
                return;
            }
            scrollLeft = wrap.getElement().getScrollLeft();
            scrollBodyPanel.getElement().setScrollLeft(scrollLeft);
            tHead.getElement().setScrollLeft(scrollLeft);
            tdHead.getElement().setScrollLeft(scrollLeft);
            ttHead.getElement().setScrollLeft(scrollLeft);
        }

        /**
         * Focus widget.
         *
         * @param filterToFocus the filter to focus
         */
        public void focusWidget(Widget filterToFocus) {
            if (filterToFocus == null) {
                return;
            } else if (filterToFocus instanceof FocusWidget) {
                ((FocusWidget) filterToFocus).setFocus(true);
            } else if (filterToFocus instanceof Focusable) {
                ((Focusable) filterToFocus).focus();
            }
        }

        /**
         * Reset filter widths.
         */
        public void resetFilterWidths() {
            for (int i = 0; i < tHead.getVisibleCellCount(); i++) {
                setFilterWidth(i);
            }
        }

        /**
         * Sets the filter width.
         *
         * @param index the new filter width
         */
        private void setFilterWidth(int index) {
            if (headerChangedDuringUpdate) {
                return;
            }
            if (!wrapFilters) {
                setNotWrappedFilterWidth(index);
            } else {
                setWrappedFilterWidth(index);
            }
        }
    }
}
