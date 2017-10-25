/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.adminconsole.periodConfiguration.logic;

import com.stpl.app.adminconsole.filemanagement.logic.tablelogic.FileResultsTableLogic;
import com.stpl.app.adminconsole.periodConfiguration.dto.PeriodConfigDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;
/**
 *
 * @author Mahesh.James
 */
public class PeriodConfigTableLogic  extends PageTableLogic {

    private static final Logger LOGGER = Logger.getLogger(FileResultsTableLogic.class);
    final PeriodConfigLogic searchLogic = new PeriodConfigLogic();
    private boolean isFirstLoad = false;
    private boolean isResultsEmpty;
    private boolean isReset = false;
    private boolean isFirst=true;

    @Override
    public int getCount() {
        int count = 0;
        try {
            if (isFirstLoad) {
                count = (Integer) searchLogic.searchForecast( 0, 0, this.getSortByColumns(), this.getFilters(), true,isFirst);
            }
            isResultsEmpty = count == 0;
            count = isReset ? 0 : count;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List list = new ArrayList();
        if (isFirstLoad) {
            try {
                list = (List) searchLogic.searchForecast(start, offset, this.getSortByColumns(), this.getFilters(), false,isFirst);
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        return list;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        PeriodConfigDTO dto = (PeriodConfigDTO) object;
        ((BeanItemContainer<PeriodConfigDTO>) container).addBean(dto);
        return dto;
    }

    public void configureSearchData() {
        isFirstLoad = true;
        isReset = false;
        this.clearAll();
        this.getFilters().clear();
        this.setRequiredCount(true);
        this.setCurrentPage(1);
        isFirst=false;
    }

    public boolean isResultsEmpty() {
        return isResultsEmpty;
    }

    public void setReset(boolean isReset) {
        this.isReset = isReset;
    }

}