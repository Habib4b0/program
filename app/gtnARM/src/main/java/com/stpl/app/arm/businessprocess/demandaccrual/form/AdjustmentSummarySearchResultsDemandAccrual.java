/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.demandaccrual.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractDemandSummaryResults;
import com.stpl.app.arm.businessprocess.demandaccrual.logic.DASummaryLogic;
import com.stpl.app.arm.supercode.ExcelInterface;
import com.stpl.ifs.ui.util.NumericConstants;
import java.util.List;

/**
 *
 * @author porchelvi.g
 */
public class AdjustmentSummarySearchResultsDemandAccrual extends AbstractDemandSummaryResults {

    public AdjustmentSummarySearchResultsDemandAccrual(DASummaryLogic logic, AbstractSelectionDTO selection) {
        super(logic, selection);
    }

    @Override
    protected String getProcedureNameAndInputs(AbstractSelectionDTO selection, List inputs) {
        inputs.add(selection.getProjectionMasterSid());
        inputs.add("DEMAND ACCRUAL");
        inputs.add(selection.getSessionDTO().getUserId());
        inputs.add(selection.getSessionDTO().getSessionId());
        return "PRC_ARM_DEMAND";
    }

    @Override
    public DASummaryLogic getSummaryLogic() {
        return (DASummaryLogic) super.getSummaryLogic();
    }

    @Override
    public ExcelInterface getExcelLogic() {
        return getSummaryLogic();
    }

    @Override
    public int getInterval() {
        return NumericConstants.EIGHT;
    }

    @Override
    public int discountColumnNeeded() {
        return 1;
    }

    @Override
    protected String getTableNameForEdit() {
        return "ST_ARM_DEMAND_ADJ_SUMMARY";
    }
}
