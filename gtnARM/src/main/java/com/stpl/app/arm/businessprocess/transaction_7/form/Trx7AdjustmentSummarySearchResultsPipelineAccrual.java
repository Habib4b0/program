/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction_7.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractPipelineSummaryResults;
import com.stpl.app.arm.businessprocess.transaction_7.dto.Trx7SelectionDTO;
import com.stpl.app.arm.businessprocess.transaction_7.logic.Trx7PASummaryLogic;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMConstants;
import java.util.List;

/**
 *
 * @author porchelvi.g
 */
public class Trx7AdjustmentSummarySearchResultsPipelineAccrual extends AbstractPipelineSummaryResults {

    public Trx7AdjustmentSummarySearchResultsPipelineAccrual(Trx7PASummaryLogic logic, Trx7SelectionDTO selection) {
        super(logic, selection);
    }

    @Override
    public Trx7PASummaryLogic getExcelLogic() {
        return (Trx7PASummaryLogic) getLogic();
    }

    @Override
    public Object[] getExcelHierarchy() {
        Object[] value = null;
        if (customerProductView.getValue().equals(ARMConstants.getDeductionCustomerContract()) && getSelection().getSummary_deductionLevelDes().equals(ARMConstants.getDeduction())) {
            value = new Object[]{"D", "C", "T", "B", "I"};
        } else if (customerProductView.getValue().equals(ARMConstants.getDeductionCustomerContract())) {
            value = new Object[]{"T", "C", "B", "I"};
        } else if (customerProductView.getValue().equals(ARMConstants.getDeductionCustomer())) {
            value = new Object[]{"T", "B", "I"};
        } else if (customerProductView.getValue().equals(ARMConstants.getDeductionProduct())) {
            value = new Object[]{"B", "I"};
        } else if (selection.getRate_DeductionView().equals("Deduction Contract")) {
            value = new Object[]{"C", "T", "B", "I"};

        }
        return value;
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
        return Boolean.TRUE;
    }

    @Override
    public int getInterval() {
        int interval = 0;
        List<String[]> selectedVariables = getSelection().getSummary_variables();
        String[] object = null;
        if (selectedVariables != null && !selectedVariables.isEmpty()) {
            for (int i = 0; i < selectedVariables.size(); i++) {
                object = selectedVariables.get(i);
                if (object[0].contains("currentBalance") || object[0].contains("calculatedAdjustment") || object[0].contains("adjustmentRatio") || object[0].contains("variance")) {
                    interval++;
                } else if (object[0].contains("override")) {
                    interval = interval + NumericConstants.TWO;
                }
            }
        }
        return interval;
    }

    @Override
    public int discountColumnNeeded() {
        return 1;
    }

    @Override
    public boolean getisDeductionCustomer() {
        if (ARMConstants.getDeductionCustomerContract().equals(getSelection().getSummary_viewType())) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    protected String[] getExcelVariableVisibleColumns() {
        return VariableConstants.VARIABLE_VISIBLE_COLUMN_TRX7_DEDUCTION;
    }

    @Override
    protected void loadLevelValues() {
        Object[] obj = new Object[]{ARMConstants.getDeductionProduct(), ARMConstants.getDeductionCustomer(), ARMConstants.getDeductionCustomerContract(), "Deduction Contract"};
        customerProductView.setImmediate(true);
        customerProductView.addItems(obj);
        customerProductView.setValue(ARMConstants.getDeductionProduct());
    }

    @Override
    protected void configureLevelDDLBs() {
        customerProductView.addItems(new Object[]{ARMConstants.getDeductionProduct(),
            ARMConstants.getDeductionCustomer(), ARMConstants.getDeductionCustomerContract(), "Deduction Contract"});
        customerProductView.setValue("Deduction Contract");
        getSelection().setSummary_viewType(ARMConstants.getDeductionProduct());
    }

    @Override
    public void setRespectiveHierarchy(String viewType) {
        if (viewType.equals(ARMConstants.getDeductionCustomerContract()) && !getSelection().getSummary_deductionLevelDes().equals(ARMConstants.getDeduction())) {
            viewType = "non" + ARMConstants.getDeductionCustomerContract();
        }
        getSelection().setSummery_hierarchy(ARMUtils.getTYrx7LevelAndLevelFilter(viewType));
    }

    @Override
    protected boolean getIsDemandSreen() {
        return Boolean.FALSE;
    }
    
    @Override
    protected boolean isPercentageColumn_zero_Decimal(String column) {
        return column.contains("adjustmentRatio");
    }

}
