/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processscheduler.logic.tableLogic;
import com.stpl.app.adminconsole.processscheduler.dto.ProcessSchedulerDTO;
import com.stpl.app.adminconsole.processscheduler.logic.ProcessSchedulerLogic;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author Jayaram
 */
public class ProcessSchedulerTableLogic extends PageTableLogic{

    ProcessSchedulerLogic logic = new ProcessSchedulerLogic();
    
    @Override
    public int getCount() {
        int count =0;
        List list = logic.getSearchResult(true,0,0, true,null);
        if (list != null && !list.isEmpty()) {
            Object ob = list.get(0);
            count = Integer.valueOf(String.valueOf(ob));
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List list = logic.getSearchResult(false, start,offset, true,getSortByColumns());
        return list;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        ProcessSchedulerDTO dto = (ProcessSchedulerDTO) object;
        ((BeanItemContainer<ProcessSchedulerDTO>) container).addBean(dto);
        return dto;
    }
    
}
