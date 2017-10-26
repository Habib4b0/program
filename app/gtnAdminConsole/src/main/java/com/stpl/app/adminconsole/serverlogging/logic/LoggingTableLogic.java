/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.adminconsole.serverlogging.logic;
import com.stpl.app.adminconsole.serverlogging.dto.LoggingDto;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author Karthik.Raja
 */
public class LoggingTableLogic extends PageTableLogic {

    final SearchLogic searchLogic = SearchLogic.getInstance();
      List list = null;

    @Override
    public int getCount() {
       list = searchLogic.searchResults(true);
       return  list.get(0)==null ? 0 : Integer.valueOf(String.valueOf(list.get(0)));
        
    }

    @Override
    public List loadData(int start, int offset) {
        list = (List) searchLogic.searchResults(false);
        return list;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        LoggingDto dto = (LoggingDto) object;
        ((BeanItemContainer<LoggingDto>) container).addBean(dto);
        return dto;
    }

    public void configureSearchData() {
        this.clearAll();
        this.getFilters().clear();
        this.setRequiredCount(true);
        this.setCurrentPage(1);
    }

}
