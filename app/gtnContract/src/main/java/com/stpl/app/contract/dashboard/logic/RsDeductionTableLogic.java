/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.logic;

import com.stpl.app.contract.global.dto.RsDeductionLookupDto;
import com.stpl.app.contract.global.logic.RebateScheduleLogic;
import com.stpl.app.contract.ui.ErrorfulFieldGroup;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 * The class Rs Deduction Calendar Lookup Table Logic
 *
 * @author vinodhini
 */
public class RsDeductionTableLogic extends PageTableLogic {

    private static final Logger LOGGER = Logger.getLogger(RsDeductionTableLogic.class);

    private final RebateScheduleLogic searchLogic = new RebateScheduleLogic();
    private ErrorfulFieldGroup binder;
    private boolean isFirstLoad = false;
    private boolean isResultsEmpty;
    private boolean isReset = false;

    @Override
    public int getCount() {
        int count = 0;
        try {
            if (isFirstLoad) {
                count = (Integer) searchLogic.getDeductionCount(binder, this.getFilters());
            }
            isResultsEmpty = count == 0;
            count = isReset ? 0 : count;
        } catch (ParseException ex) {
            LOGGER.error(ex);
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List list = new ArrayList();
        if (isFirstLoad) {
            try {
                list = (List) searchLogic.loadDeductionResults(binder, start, offset, this.getSortByColumns(), this.getFilters());
            } catch (ParseException ex) {
                LOGGER.error(ex);
            }
        }
        return list;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        RsDeductionLookupDto dto = (RsDeductionLookupDto) object;
        ((BeanItemContainer<RsDeductionLookupDto>) container).addBean(dto);
        return dto;
    }

    public void configureSearchData(final ErrorfulFieldGroup binder) {
        isFirstLoad = true;
        isReset = false;
        this.clearAll();
        this.getFilters().clear();
        this.binder = binder;
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
