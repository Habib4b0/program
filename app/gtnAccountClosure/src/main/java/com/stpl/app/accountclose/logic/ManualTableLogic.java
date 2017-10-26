/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.accountclose.logic;

import com.stpl.app.accountclose.dto.ProcessDTO;
import com.stpl.app.accountclose.logic.ManualLogic;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author hazi.s
 */
public class ManualTableLogic extends PageTableLogic {

    
    ManualLogic logic = new ManualLogic();
    
    @Override
    public int getCount() {
        int count =0;
        List list = logic.getSearchResult(true);
        count = list.size();
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List list = logic.getSearchResult(false);
        return list;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        ProcessDTO dto = (ProcessDTO) object;
        ((BeanItemContainer<ProcessDTO>) container).addBean(dto);
        return dto;
    }
    
    
    
    
}
