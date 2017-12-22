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

import com.vaadin.v7.data.Container;
import org.asi.ui.extfilteringtable.paged.logic.ContainerLogic;


/**
 * The Class ExtPagedFilterTreeTable.
 *
 * @author Abhiram
 * @param <T> the generic type
 */
public class ExtPagedFilterTreeTable<T extends Container.Indexed & Container.Filterable & Container.ItemSetChangeNotifier>
        extends PagedTreeTableBase {

    /**
     * Instantiates a new ext paged filter tree table.
     *
     * @param logic the logic
     */
    public ExtPagedFilterTreeTable(ContainerLogic logic) {
        this(null, logic);
    }

    /**
     * Instantiates a new ext paged filter tree table.
     *
     * @param caption the caption
     * @param logic the logic
     */
    public ExtPagedFilterTreeTable(String caption, ContainerLogic logic) {
        super(caption,logic);
    }
     
     /**
      * Gets the container logic.
      *
      * @return the container logic
      */
     @Override
    public ContainerLogic getContainerLogic() {
        return (ContainerLogic)super.getContainerLogic();
    }

}