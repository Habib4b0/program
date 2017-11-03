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

import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.ExtCustomTreeTable;
import com.vaadin.ui.Tree.ExpandEvent;
import com.vaadin.ui.Tree.ExpandListener;
import com.vaadin.ui.TreeTable;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;


/**
 * The listener interface for receiving tableExpand events.
 * The class that is interested in processing a tableExpand
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addTableExpandListener<code> method. When
 * the tableExpand event occurs, that object's appropriate
 * method is invoked.
 *
 * @author Abhiram
 */
public class TableExpandListener implements ExpandListener {

        /**
         * The Constant serialVersionUID.
         */
        private static final long serialVersionUID = 1L;
        
        /** The expand listener. */
        ExpandListener expandListener = null;
        
        /** The table. */
        AbstractSelect table = null;

        /**
         * Instantiates a new table expand listener.
         */
        public TableExpandListener() {

        }

        /**
         * Instantiates a new table expand listener.
         *
         * @param table the table
         */
        public TableExpandListener(AbstractSelect table) {
            this.table = table;
        }

        /**
         * Instantiates a new table expand listener.
         *
         * @param expandListener the expand listener
         */
        public TableExpandListener(ExpandListener expandListener) {
            this.expandListener = expandListener;
        }

        /**
         * After expanding the node.
         *
         * @param event the event
         */
        @Override
        public void nodeExpand(final ExpandEvent event) {
            if (table != null) {
               if (table instanceof ExtFilterTreeTable) {
                    ((ExtFilterTreeTable) table).setCollapsed(event.getItemId(), false);
                }else if (table instanceof TreeTable) {
                    ((TreeTable) table).setCollapsed(event.getItemId(), false);
                }else if (table instanceof ExtCustomTreeTable) {
                    ((ExtCustomTreeTable) table).setCollapsed(event.getItemId(), false);
                }
            } else if (expandListener != null) {
                expandListener.nodeExpand(event);
            }
        }
}
