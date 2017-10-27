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
package org.asi.ui.extfilteringtable.paged;

import com.vaadin.data.Container;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;


/**
 * The Class ExtPagedTreeTable.
 *
 * @author Abhiram
 * @param <T> the generic type
 */
public class ExtPagedTreeTable<T extends Container.Indexed & Container.Filterable & Container.ItemSetChangeNotifier>
        extends PagedTreeTableBase {

    /**
     * Instantiates a new ext paged tree table.
     *
     * @param logic the logic
     */
    public ExtPagedTreeTable(PageTreeTableLogic logic) {
        this(null, logic);
    }

    /**
     * Instantiates a new ext paged tree table.
     *
     * @param caption the caption
     * @param logic the logic
     */
    public ExtPagedTreeTable(String caption, PageTreeTableLogic logic) {
        super(caption,logic);
    }
  
  /**
   * Gets the collapsible strategy.
   *
   * @return the collapsible strategy
   */
  @Override
    protected CollapsibleStrategy getCollapsibleStrategy() {
        return new CollapsibleStrategy();
    }
    
    /**
     * Gets the hierarchical strategy.
     *
     * @return the hierarchical strategy
     */
    @Override
    protected HierarchicalStrategy getHierarchicalStrategy() {
        return new HierarchicalStrategy();
    }
    
    /**
     * The Class CollapsibleStrategy.
     */
    protected class CollapsibleStrategy extends PagedTreeTableBase.CollapsibleStrategy{
    }
    
    /**
     * The Class HierarchicalStrategy.
     */
    protected class HierarchicalStrategy extends PagedTreeTableBase.HierarchicalStrategy{
       
       /**
        * Gets the manual depth.
        *
        * @param itemId the item id
        * @param depth the depth
        * @return the manual depth
        */
       @Override
       protected int getManualDepth(Object itemId,int depth) {
            return getContainerLogic().getDepth(itemId, depth);
        }
    }
    
    /**
     * Gets the container logic.
     *
     * @return the container logic
     */
    @Override
    public PageTreeTableLogic getContainerLogic() {
        return (PageTreeTableLogic)super.getContainerLogic();
    }
    
}
