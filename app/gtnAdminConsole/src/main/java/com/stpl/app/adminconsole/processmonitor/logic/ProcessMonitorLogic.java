/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processmonitor.logic;

import com.stpl.app.adminconsole.processmonitor.dto.ProcessMonitorDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author Asha
 */
public class ProcessMonitorLogic extends PageTableLogic {
    
    ProcessLogic logic = new ProcessLogic();
  
    @Override
    public int getCount() {
        int count =0;
        List list = logic.getSearchResult(true,0,0,null);
        count = list.size();
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List list = logic.getSearchResult(false, start, offset,getSortByColumns());
        return list;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        ProcessMonitorDTO dto = (ProcessMonitorDTO) object;
        ((BeanItemContainer<ProcessMonitorDTO>) container).addBean(dto);
        return dto;
    }
    
}
