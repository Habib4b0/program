/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.ifs.ui;

import com.vaadin.v7.data.Container;
import org.asi.ui.extfilteringtable.paged.ExtPagedFilterTable;

/**
 *
 * @author shrihariharan
 * @param <T>
 */
public class CustomePagedFilterTable<T extends Container.Indexed & Container.Filterable & Container.ItemSetChangeNotifier>
        extends ExtPagedFilterTable<T>{
    @Override
    protected int getManualPageLength(){
        return getContainerDataSource().getPageLength();
    }
    
    @Override
    public int getCurrentPageFirstItemIndex() {
        return 0;
    }
    
}
