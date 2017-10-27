/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.netsalesformula.logic.tablelogic;

import com.stpl.app.global.netsalesformula.logic.SalesLogic;
import com.stpl.app.global.netsalesformula.dto.SalesBasisDto;
import com.stpl.app.global.netsalesformula.ui.form.SalesBasis;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
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
public class ContractSearchTableLogic extends PageTableLogic {

    private static final Logger LOGGER = Logger.getLogger(ContractSearchTableLogic.class);
    private boolean isFirstLoad = false;
    private boolean isResultsEmpty;
    private boolean isReset = false;
    private ErrorfulFieldGroup binder;
    private final SalesLogic searchLogic = new SalesLogic();

    @Override
    public int getCount() {
        int count = 0;
        try {
            if (isFirstLoad) {
                count = searchLogic.getCount(binder, 0, 0, true, null, this.getFilters());
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
                list = searchLogic.getSearchResults(binder, start, offset, false, this.getSortByColumns(), this.getFilters());
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
