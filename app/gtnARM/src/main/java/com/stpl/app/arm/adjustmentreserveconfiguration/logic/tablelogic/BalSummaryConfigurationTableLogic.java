/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.logic.tablelogic;

import com.stpl.app.arm.adjustmentreserveconfiguration.dto.AdjustmentReserveDTO;
import com.stpl.app.arm.adjustmentreserveconfiguration.logic.BalanceSummaryLogic;
import com.stpl.app.arm.utils.ReserveSelection;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author saranya.sekar
 */
public class BalSummaryConfigurationTableLogic extends PageTableLogic {

    ReserveSelection selection;
    boolean isGenerate = false;
    BalanceSummaryLogic logic = BalanceSummaryLogic.getInstance();

    @Override
    public int getCount() {
        if (isGenerate) {
            return logic.getBalanceSummaryCount(selection, getFilters());
        }
        return 0;
    }

    @Override
    public List loadData(int start, int offset) {
        return logic.getBalanceSummaryData(selection, start, offset, getFilters(), getSortByColumns(), false);
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        AdjustmentReserveDTO dto = (AdjustmentReserveDTO) object;
        ((BeanItemContainer<AdjustmentReserveDTO>) container).addBean(dto);
        return dto;
    }

    public void loadSetData(boolean isRest, ReserveSelection selection) {
        isGenerate = isRest;
        this.selection = selection;
        clearAll();
        clearFilters();
        setRequiredCount(true);
        setCurrentPage(1);
    }

}
