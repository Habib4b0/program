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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ApplicationConnection;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.UIDL;
import com.vaadin.shared.ui.Connect;
import org.asi.ui.extfilteringtable.ExtFilterTable;


/**
 * The Class ExtFilterTableConnector.
 */
@Connect(ExtFilterTable.class)
public class ExtFilterTableConnector extends ExtCustomTableConnector{

    /*
     * (non-Javadoc)
     * 
     * @see com.vaadin.client.Paintable#updateFromUIDL(com.vaadin.client.UIDL,
     * com.vaadin.client.ApplicationConnection)
     * 
     * 
     */
    /**
     * Update from uidl.
     *
     * @param uidl the uidl
     * @param client the client
     */
    @Override
    public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {
        super.updateFromUIDL(uidl, client);
        updateFiltersFromUIDL(uidl.getChildByTagName("filters"), client);
    }

    /**
     * Update filterWidget from uidl.
     *
     * @param uidl the uidl
     * @param client the client
     */
    public void updateFiltersFromUIDL(UIDL uidl, ApplicationConnection client) {
        if (uidl == null) {
            return;
        }
        getWidget().filters.filtersVisible = uidl
                .hasAttribute("filtersvisible") ? uidl
                .getBooleanAttribute("filtersvisible") : false;
        getWidget().filters.wrapFilters = uidl.hasAttribute("wrapFilters") ? uidl
                .getBooleanAttribute("wrapFilters") : false;

        /* If filterWidget are not set visible, clear and hide filter panel */
        getWidget().filters.setVisible(getWidget().filters.filtersVisible);
        getWidget().setContainerHeight();
        boolean forceRender = uidl.getBooleanAttribute("forceRender");
        if (!getWidget().filters.filtersVisible) {
            getWidget().filters.container.clear();
            getWidget().filters.filterWidget.clear();
        } else {
            /* Prepare and paint filter components */
            Map<String, Widget> newWidgets = new HashMap<String, Widget>();
            boolean allCached = true;
            for (final Iterator<Object> it = uidl.getChildIterator(); it
                    .hasNext();) {
                final UIDL cc = (UIDL) it.next();
                if (cc.getTag().startsWith("filtercomponent-")) {
                    String cid = cc.getStringAttribute("columnid");
                    UIDL uidld = cc.getChildUIDL(0);
                    if (uidld == null) {
                        newWidgets.put(cid, null);
                    } else {
                        ComponentConnector connector = client
                                .getPaintable(uidld);
                        newWidgets.put(cid, connector.getWidget());
                        if (!uidld.hasAttribute("cached")
                                || !uidld.getBooleanAttribute("cached")) {
                            allCached = false;
                        }
                    }
                }
            }
            if (forceRender || !allCached
                    || getWidget().filters.filterWidget.isEmpty()) {
                getWidget().filters.filterWidget.clear();
                for (String cid : newWidgets.keySet()) {
                    getWidget().filters.filterWidget.put(cid, newWidgets.get(cid));
                }
                getWidget().filters.reRenderFilterComponents();
            } else {
                getWidget().filters.resetFilterWidths();
            }
        }
    }

    /**
     * Gets the widget.
     *
     * @return the widget
     */
    @Override
    public VExtFilterTable getWidget() {
        return (VExtFilterTable) super.getWidget();
    }
    
}
