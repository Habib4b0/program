/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.netsalesformula.logic.tablelogic;

import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.netsalesformula.dto.SalesBasisDto;
import com.stpl.app.global.netsalesformula.logic.SalesLogic;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author Asha
 */
public class SelectedCustomerTableLogic extends PageTableLogic {

    private boolean isFirstLoad = false;
    SessionDTO sessionDTO;
    SalesLogic logic = new SalesLogic();
    private boolean isResultsEmpty;
    private boolean isReset = false;
    private static final Logger LOGGER = Logger.getLogger(SelectedCustomerTableLogic.class);

    @Override
    public int getCount() {
        int count = 0;
        try {
            if (isFirstLoad) {
                count = logic.tempTableCount(this.getFilters(), sessionDTO);
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
                list = logic.tempTableResults(sessionDTO, start, start + offset, this.getSortByColumns(), this.getFilters());
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        return list;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        SalesBasisDto dto = (SalesBasisDto) object;
        ((BeanItemContainer<SalesBasisDto>) container).addBean(dto);
        return dto;
    }

    public void configureSearchData(SessionDTO sessiondto) {
        isFirstLoad = true;
        isReset = false;
        this.clearAll();
        this.sessionDTO = sessiondto;
        this.getFilters().clear();
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
