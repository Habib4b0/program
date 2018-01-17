/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.adminconsole.filemanagement.logic.tablelogic;

import com.stpl.app.adminconsole.filemanagement.dto.TrackingProcessDTO;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author vinodhini
 */
public class TrackingProgressTableLogic extends PageTableLogic {

    private TrackingProcessDTO resultDTO;
    private boolean isResultsEmpty;

    @Override
    public int getCount() {
        int count = 0;
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List list = new ArrayList();
        return list;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        TrackingProcessDTO dto = (TrackingProcessDTO) object;
        ((BeanItemContainer<TrackingProcessDTO>) container).addBean(dto);
        return dto;
    }

    public void configureSearchData(TrackingProcessDTO resultDTO) {
        this.clearAll();
        this.getFilters().clear();
        this.resultDTO = resultDTO;
        this.setRequiredCount(true);
        this.setCurrentPage(1);
    }

    public boolean isResultsEmpty() {
        return isResultsEmpty;
    }

}
