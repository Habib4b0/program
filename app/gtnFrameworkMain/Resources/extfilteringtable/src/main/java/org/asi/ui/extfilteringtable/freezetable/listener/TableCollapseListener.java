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

package org.asi.ui.extfilteringtable.freezetable.listener;

import org.asi.ui.extfilteringtable.ExtFilterTreeTable;

import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.ExtCustomTreeTable;
import com.vaadin.ui.Tree.CollapseEvent;
import com.vaadin.ui.Tree.CollapseListener;
import com.vaadin.ui.TreeTable;


/**
 * The listener interface for receiving tableCollapse events.
 * The class that is interested in processing a tableCollapse
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addTableCollapseListener<code> method. When
 * the tableCollapse event occurs, that object's appropriate
 * method is invoked.
 *
 * @author Abhiram
 */
public class TableCollapseListener implements CollapseListener {

        /**
         * The Constant serialVersionUID.
         */
        private static final long serialVersionUID = 1L;
        
        /** The collapse listener. */
        CollapseListener collapseListener = null;
        
        /** The table. */
        AbstractSelect table = null;

        /**
         * Instantiates a new table collapse listener.
         */
        public TableCollapseListener() {
        }

        /**
         * Instantiates a new table collapse listener.
         *
         * @param table the table
         */
        public TableCollapseListener(AbstractSelect table) {
            this.table = table;
        }

        /**
         * Instantiates a new table collapse listener.
         *
         * @param collapseListener the collapse listener
         */
        public TableCollapseListener(CollapseListener collapseListener) {
            this.collapseListener = collapseListener;
        }

        /**
         * After collapsing the node, function will be executed.
         *
         * @param event the event
         */
        @Override
        public void nodeCollapse(final CollapseEvent event) {
            if (table != null) {
                if (table instanceof ExtFilterTreeTable) {
                    ((ExtFilterTreeTable) table).setCollapsed(event.getItemId(), true);
                } else if (table instanceof TreeTable) {
                    ((TreeTable) table).setCollapsed(event.getItemId(), true);
                } else if (table instanceof ExtCustomTreeTable) {
                    ((ExtCustomTreeTable) table).setCollapsed(event.getItemId(), true);
                }
            } else if (collapseListener != null) {
                collapseListener.nodeCollapse(event);
            }

        }
}
