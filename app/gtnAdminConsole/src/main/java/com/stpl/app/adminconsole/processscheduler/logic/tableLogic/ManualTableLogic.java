/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processscheduler.logic.tableLogic;

import com.stpl.app.adminconsole.processscheduler.dto.ProcessSchedulerDTO;
import com.stpl.app.adminconsole.processscheduler.logic.ManualLogic;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author satheesh.n
 */
public class ManualTableLogic extends PageTableLogic{
    
    
	private ManualLogic logic = new ManualLogic();
     
    @Override
    public int getCount() {
        int count =0;
        List list = logic.getSearchResult(true,0,0, false,null);
        if(list != null && !list.isEmpty()){
            count = Integer.valueOf(String.valueOf(list.get(0)));
        } else {
            count = 0;
        }
        return count;
    }

   @Override
    public List loadData(int start, int offset) {
        List list = logic.getSearchResult(false,start,offset, false,getSortByColumns());
        return list;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        ProcessSchedulerDTO dto = (ProcessSchedulerDTO) object;
        ((BeanItemContainer<ProcessSchedulerDTO>) container).addBean(dto);
        return dto;
    }
    
    public void fireSetData(){
        clearAll();
        setRequiredCount(true);
        setCurrentPage(1);
    } 
    
    
}
    


