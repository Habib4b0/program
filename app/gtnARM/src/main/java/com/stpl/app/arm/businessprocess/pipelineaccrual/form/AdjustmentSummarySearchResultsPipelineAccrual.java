/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineaccrual.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractPipelineSummaryResults;
import com.stpl.app.arm.businessprocess.pipelineaccrual.dto.PipelineAccrualSelectionDTO;
import com.stpl.app.arm.businessprocess.pipelineaccrual.logic.PASummaryLogic;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMConstants;
import java.util.List;

/**
 *
 * @author porchelvi.g
 */
public class AdjustmentSummarySearchResultsPipelineAccrual extends AbstractPipelineSummaryResults {

    public AdjustmentSummarySearchResultsPipelineAccrual(PASummaryLogic logic, PipelineAccrualSelectionDTO selection) {
        super(logic, selection);
    }

    @Override
    public PASummaryLogic getExcelLogic() {
        return (PASummaryLogic) getSummaryLogic();
    }

    @Override
    public Object[] getExcelHierarchy() {
        Object[] summPAvalue = null;
        if (customerProductView.getValue().equals(ARMConstants.getDeductionCustomerContract()) && getSelection().getSummarydeductionLevelDes().equals(ARMConstants.getDeduction())) {
            summPAvalue = ARMUtils.getDCTBI();
        } else if (customerProductView.getValue().equals(ARMConstants.getDeductionCustomerContract())) {
            summPAvalue = ARMUtils.getTCBI();
        } else if (customerProductView.getValue().equals(ARMConstants.getDeductionCustomer())) {
            summPAvalue = ARMUtils.getTBI();
        } else if (customerProductView.getValue().equals(ARMConstants.getDeductionProduct())) {
            summPAvalue = ARMUtils.getBI();
        }
        return summPAvalue;
    }

    @Override
    public List getExcelExportVisibleColumn() {
        return getSelection().getExcelVisibleColumn();
    }

    @Override
    public String getExcelFileName() {
        return "Adjustment Summary";
    }

    @Override
    public boolean getisFixedColumns() {
        return true;
    }

    @Override
    public int getInterval() {
        return NumericConstants.FIVE;
    }

    @Override
    public int discountColumnNeeded() {
        return 1;
    }

    @Override
    public boolean getisDeductionCustomer() {
        return false;
    }

    @Override
    protected String[] getExcelVariableVisibleColumns() {
        return VariableConstants.getVariableVisibleColumnDedution();
    }

    @Override
    protected boolean getIsDemandSreen() {
        return false;
    }

}
