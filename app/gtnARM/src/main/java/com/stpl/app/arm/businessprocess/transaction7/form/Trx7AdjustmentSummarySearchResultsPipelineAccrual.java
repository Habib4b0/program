/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction7.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractPipelineSummaryResults;
import com.stpl.app.arm.businessprocess.transaction7.dto.Trx7SelectionDTO;
import com.stpl.app.arm.businessprocess.transaction7.logic.Trx7PASummaryLogic;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
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
        return (Trx7PASummaryLogic) getSummaryLogic();
    }

    @Override
    public Object[] getExcelHierarchy() {
        Object[] value = null;
        if (customerProductView.getValue().equals(ARMConstants.getDeductionCustomerContract()) && getSelection().getSummarydeductionLevelDes().equals(ARMConstants.getDeduction())) {
            value = new Object[]{"D", "C", "T", "B", "I"};
        } else if (customerProductView.getValue().equals(ARMConstants.getDeductionCustomerContract())) {
            value = new Object[]{"T", "C", "B", "I"};
        } else if (customerProductView.getValue().equals(ARMConstants.getDeductionCustomer())) {
            value = new Object[]{"T", "B", "I"};
        } else if (customerProductView.getValue().equals(ARMConstants.getDeductionProduct())) {
            value = new Object[]{"B", "I"};
        } else if (selection.getRateDeductionView().equals(CommonConstant.DEDUCTION_CONTRACT)) {
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
        return NumericConstants.SIX;
    }

    @Override
    public int discountColumnNeeded() {
        return 1;
    }

    @Override
    public boolean getisDeductionCustomer() {
        if (ARMConstants.getDeductionCustomerContract().equals(getSelection().getSummaryviewType())) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    protected String[] getExcelVariableVisibleColumns() {
        return VariableConstants.getVariableVisibleColumnTrx7Deduction();
    }

    @Override
    protected void loadLevelValues() {
        Object[] obj = new Object[]{ARMConstants.getDeductionProduct(), ARMConstants.getDeductionCustomer(), ARMConstants.getDeductionCustomerContract(), CommonConstant.DEDUCTION_CONTRACT};
        customerProductView.setImmediate(true);
        customerProductView.addItems(obj);
        customerProductView.setValue(ARMConstants.getDeductionProduct());
    }

    @Override
    protected void configureLevelDDLBs() {
        customerProductView.addItems(new Object[]{ARMConstants.getDeductionProduct(),
            ARMConstants.getDeductionCustomer(), ARMConstants.getDeductionCustomerContract(), CommonConstant.DEDUCTION_CONTRACT});
        customerProductView.setValue(CommonConstant.DEDUCTION_CONTRACT);
        getSelection().setSummaryviewType(ARMConstants.getDeductionProduct());
    }

    @Override
    public void setRespectiveHierarchy(String viewType) {
        if (viewType.equals(ARMConstants.getDeductionCustomerContract()) && !getSelection().getSummarydeductionLevelDes().equals(ARMConstants.getDeduction())) {
            viewType = "non" + ARMConstants.getDeductionCustomerContract();
        }
        getSelection().setSummeryhierarchy(ARMUtils.getTYrx7LevelAndLevelFilter(viewType));
    }

    @Override
    protected boolean getIsDemandSreen() {
        return Boolean.FALSE;
    }

    @Override
    protected boolean isPercentageColumnzeroDecimal(String column) {
        return column.contains("adjustmentRatio");
    }

}
