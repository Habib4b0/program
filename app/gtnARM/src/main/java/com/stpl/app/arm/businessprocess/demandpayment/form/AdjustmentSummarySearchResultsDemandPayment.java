
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.demandpayment.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractDemandSummaryResults;
import com.stpl.app.arm.businessprocess.demandpayment.logic.DPSummaryLogic;
import com.stpl.app.arm.supercode.ExcelInterface;
import com.stpl.ifs.ui.util.NumericConstants;
import java.util.List;

/**
 *
 * @author Asha.Ravi
 */
public class AdjustmentSummarySearchResultsDemandPayment extends AbstractDemandSummaryResults {

    public AdjustmentSummarySearchResultsDemandPayment(DPSummaryLogic logic, AbstractSelectionDTO selection) {
        super(logic, selection);
    }

    @Override
    protected String getProcedureNameAndInputs(AbstractSelectionDTO selection, List inputs) {
        inputs.add(selection.getProjectionMasterSid());
        inputs.add("DEMAND PAYMENT RECON");
        inputs.add(selection.getSessionDTO().getUserId());
        inputs.add(selection.getSessionDTO().getSessionId());
        return "PRC_ARM_DEMAND";
    }

    @Override
    public ExcelInterface getExcelLogic() {
        return (ExcelInterface) getSummaryLogic();
    }

    @Override
    public int getInterval() {
        return NumericConstants.NINE;
    }

    @Override
    protected String getTableNameForEdit() {
        return "ST_ARM_DEMAND_RECON_SUMMARY";
    }

}
