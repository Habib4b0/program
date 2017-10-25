/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.logic;

import com.stpl.app.accountclose.dto.ProcessMonitorDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author mihirkumar.b
 */
public class ProcessMonitorLogic extends PageTableLogic {

     ProcessLogic logic = new ProcessLogic();
    
    @Override
    public int getCount() {
        int count =0;
        List list = logic.getSearchResult(true,null);
        count = list.size();
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List list = logic.getSearchResult(false,this.getSortByColumns());
        return list;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        ProcessMonitorDTO dto = (ProcessMonitorDTO) object;
        ((BeanItemContainer<ProcessMonitorDTO>) container).addBean(dto);
        return dto;
    }

    
    
}
