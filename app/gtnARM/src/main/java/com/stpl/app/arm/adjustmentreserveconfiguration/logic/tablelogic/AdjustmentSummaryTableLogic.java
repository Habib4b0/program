/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.logic.tablelogic;

import com.stpl.app.arm.adjustmentreserveconfiguration.dto.AdjustmentReserveDTO;
import com.stpl.app.arm.adjustmentreserveconfiguration.logic.AdjustmentSummaryConfigLogic;
import com.stpl.app.arm.utils.ReserveSelection;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author Mohamed.Shahul
 */
public class AdjustmentSummaryTableLogic extends PageTableLogic {

    private ReserveSelection summarySelection;
    private boolean issummaryGenerate = false;
    private AdjustmentSummaryConfigLogic logic = AdjustmentSummaryConfigLogic.getInstance();

    public AdjustmentSummaryTableLogic() {
        /*
        THE DEFAULT CONSTRUCTOR
         */
    }

    @Override
    public int getCount() {
        if (issummaryGenerate) {
            return logic.getAdjSummaryConfigurationCount(summarySelection, getFilters());
        }
        return 0;
    }

    @Override
    public List loadData(int start, int offset) {
        return logic.getAdjSummaryConfigurationData(summarySelection, start, offset, getFilters(), getSortByColumns(), false);
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        AdjustmentReserveDTO dto = (AdjustmentReserveDTO) object;
        ((BeanItemContainer<AdjustmentReserveDTO>) container).addBean(dto);
        return dto;
    }

    public void loadSetData(boolean isReset, ReserveSelection summarySelection) {
        issummaryGenerate = isReset;
        this.summarySelection = summarySelection;
        clearAll();
        clearFilters();
        setRequiredCount(true);
        setCurrentPage(1);
    }

}
