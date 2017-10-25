/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.demandreforecast.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractDemandSummaryResults;
import com.stpl.app.arm.businessprocess.demandreforecast.logic.DRSummaryLogic;
import com.stpl.app.arm.supercode.ExcelInterface;
import com.stpl.ifs.ui.util.NumericConstants;
import java.util.List;

/**
 *
 * @author Asha.Ravi
 */
public class AdjustmentSummarySearchResultsDemandReforecast extends AbstractDemandSummaryResults {

    public AdjustmentSummarySearchResultsDemandReforecast(DRSummaryLogic logic, AbstractSelectionDTO selection) {
        super(logic, selection);
    }

    @Override
    protected String getProcedureNameAndInputs(AbstractSelectionDTO selection, List inputs) {
        inputs.add(selection.getProjectionMasterSid());
        inputs.add("DEMAND REFORECAST");
        inputs.add(selection.getSessionDTO().getUserId());
        inputs.add(selection.getSessionDTO().getSessionId());
        return "PRC_ARM_DEMAND";
    }

    @Override
    public DRSummaryLogic getLogic() {
        return (DRSummaryLogic) super.getLogic();
    }

    @Override
    public ExcelInterface getExcelLogic() {
        return getLogic();
    }

   
    @Override
    public int getInterval() {
       return NumericConstants.EIGHT;
    }

    @Override
    protected  String getTableNameForEdit() {
        return "ST_ARM_DEMAND_RF_TRUE_UP_SUMMARY";
    }
}
