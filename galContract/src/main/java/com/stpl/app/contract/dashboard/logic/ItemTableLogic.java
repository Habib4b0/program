/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.logic;

import com.vaadin.data.Container;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author karthikraja.k
 */
public class ItemTableLogic extends PageTableLogic {

    @Override
    public int getCount() {
           return 0;    }

    @Override
    public List loadData(int start, int offset) {
        return new ArrayList<>();
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        return new Object();
    }
   
    public void configureSearchData(){
}
    
}
