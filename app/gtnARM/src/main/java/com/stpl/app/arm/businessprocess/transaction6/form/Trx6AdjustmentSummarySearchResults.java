/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction6.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractPipelineSummaryResults;
import com.stpl.app.arm.businessprocess.pipelineaccrual.form.tablegenerator.SummaryFieldFactory;
import com.stpl.app.arm.businessprocess.transaction6.dto.Trx6SelectionDTO;
import com.stpl.app.arm.businessprocess.transaction6.logic.Trx6SummaryLogic;
import com.stpl.app.arm.supercode.ExcelInterface;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMConstants;
import java.util.List;

/**
 *
 * @author Srithar
 */
public class Trx6AdjustmentSummarySearchResults extends AbstractPipelineSummaryResults {

    public Trx6AdjustmentSummarySearchResults(Trx6SummaryLogic logic, Trx6SelectionDTO selection) {
        super(logic, selection);
    }

    @Override
    public ExcelInterface getExcelLogic() {
        return (ExcelInterface) getSummaryLogic();
    }

    @Override
    public Trx6SelectionDTO getSelection() {
        return (Trx6SelectionDTO) super.getSelection();
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
        return Boolean.FALSE;
    }

    @Override
    protected String[] getExcelVariableVisibleColumns() {
        return ARMUtils.getVariableVisibleColumnTrx6Decution();
    }

    @Override
    protected void configureRightTable() {
        SummaryFieldFactory fieldFactory = new SummaryFieldFactory(getSummaryLogic(), getSelection(), ARMConstants.getDeductionCustomerContract().equals(String.valueOf(customerProductView.getValue())) && ARMConstants.getDeduction().equals(selection.getSummarydeductionLevelDes()));
        rightTable.setEditable(true);
        rightTable.setTableFieldFactory(fieldFactory);

    }

    @Override
    public boolean checkLeave() {
        return true;
    }

    @Override
    protected boolean isPercentageColumn2Decimal(String column) {
        return column.contains("adjustmentRatio"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isRestrict() {
        return true;
    }
}
