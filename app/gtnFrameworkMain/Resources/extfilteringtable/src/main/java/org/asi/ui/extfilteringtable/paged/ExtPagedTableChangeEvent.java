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

import java.io.Serializable;

import com.vaadin.ui.ExtCustomTable;


/**
 * The Class ExtPagedTableChangeEvent.
 *
 * @author Abhiram
 * @param <T> the generic type
 */
@SuppressWarnings("serial")
public class ExtPagedTableChangeEvent<T extends ExtCustomTable> implements Serializable {
    
    /** The table. */
    private final T table;

    /**
     * Instantiates a new ext paged table change event.
     *
     * @param table the table
     */
    public ExtPagedTableChangeEvent(T table) {
        this.table = table;
    }

    /**
     * Gets the table.
     *
     * @return the table
     */
    public T getTable() {
        return table;
    }

    /**
     * Gets the current page.
     *
     * @return the current page
     */
    public int getCurrentPage() {
        if(table instanceof ExtPagedFilterTable){
            return ((ExtPagedFilterTable)table).getCurrentPage();
        }else if(table instanceof ExtPagedFilterTreeTable){
            return ((ExtPagedFilterTreeTable)table).getCurrentPage();
        }else if(table instanceof ExtPagedTreeTable){
            return ((ExtPagedTreeTable)table).getCurrentPage();
        }else if(table instanceof ExtPagedTable){
            return ((ExtPagedTable)table).getCurrentPage();
        }
        
        return 0;
    }

    /**
     * Gets the total amount of pages.
     *
     * @return the total amount of pages
     */
    public int getTotalAmountOfPages() {
        if(table instanceof ExtPagedFilterTable){
            return ((ExtPagedFilterTable)table).getTotalAmountOfPages();
        }else if(table instanceof ExtPagedFilterTreeTable){
            return ((ExtPagedFilterTreeTable)table).getTotalAmountOfPages();
        }else if(table instanceof ExtPagedTreeTable){
            return ((ExtPagedTreeTable)table).getTotalAmountOfPages();
        }else if(table instanceof ExtPagedTable){
            return ((ExtPagedTable)table).getTotalAmountOfPages();
        }
        return 0;
    }
}
