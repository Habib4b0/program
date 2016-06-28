/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.adminconsole.filemanagement.logic.tablelogic;

import com.stpl.app.adminconsole.filemanagement.dto.TrackingProcessDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author vinodhini
 */
public class TrackingProgressTableLogic extends PageTableLogic {

    private static final Logger LOGGER = Logger.getLogger(TrackingProgressTableLogic.class);
    TrackingProcessDTO resultDTO;
    private boolean isFirstLoad = false;
    private boolean isResultsEmpty;
    private boolean isReset = false;

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
        isFirstLoad = true;
        isReset = false;
        this.clearAll();
        this.getFilters().clear();
        this.resultDTO = resultDTO;
        this.setRequiredCount(true);
        this.setCurrentPage(1);
    }

    public boolean isResultsEmpty() {
        return isResultsEmpty;
    }

    public void setReset(boolean isReset) {
        this.isReset = isReset;
    }

}
