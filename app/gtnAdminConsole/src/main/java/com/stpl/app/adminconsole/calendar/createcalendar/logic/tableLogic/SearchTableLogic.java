/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.calendar.createcalendar.logic.tableLogic;

import com.stpl.app.adminconsole.calendar.createcalendar.logic.CalendarLogic;
import com.stpl.app.adminconsole.calendar.createcalendar.logic.dto.CalendarDetailsDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.List;
import java.util.Map;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author Abishek.Ram
 */
public class SearchTableLogic extends PageTableLogic {

    CalendarLogic clogic = new CalendarLogic();
    Map properties;
    boolean isSearch = false;

    @Override
    public int getCount() {
        if (isSearch) {
            return clogic.getcalendar(0, 0, this.getSortByColumns(), properties, getFilters(), true).size();
        } else {
            return 0;
        }
    }

    @Override
    public List loadData(int start, int offset) {
        return clogic.getcalendar(start, offset, this.getSortByColumns(), properties, getFilters(), false);
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        CalendarDetailsDTO dto = (CalendarDetailsDTO) object;
        ((BeanItemContainer<CalendarDetailsDTO>) container).addBean(dto);
        return dto;
    }

    public boolean loadsetData(Map props, boolean isSearch) {
        this.isSearch = isSearch;
        this.properties = props;
        clearAll();
        clearFilters();
        removeAllContainerFilters();
        setRequiredCount(true);
        setCurrentPage(1);
        return getRecordCount() != 0;
    }

}
