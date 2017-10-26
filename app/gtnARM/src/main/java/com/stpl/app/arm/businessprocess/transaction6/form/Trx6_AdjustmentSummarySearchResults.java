/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction6.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractPipelineSummaryResults;
import com.stpl.app.arm.businessprocess.pipelineaccrual.form.tablegenerator.SummaryFieldFactory;
import com.stpl.app.arm.businessprocess.transaction6.dto.Trx6_SelectionDTO;
import com.stpl.app.arm.businessprocess.transaction6.logic.Trx6_SummaryLogic;
import com.stpl.app.arm.supercode.ExcelInterface;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMConstants;
import java.util.List;

/**
 *
 * @author Srithar
 */
public class Trx6_AdjustmentSummarySearchResults extends AbstractPipelineSummaryResults{

    public Trx6_AdjustmentSummarySearchResults(Trx6_SummaryLogic logic, Trx6_SelectionDTO selection) {
        super(logic, selection);
    }

    @Override
    public ExcelInterface getExcelLogic() {
        return (ExcelInterface) getLogic();
    }

    public Trx6_SelectionDTO getSelection() {
        return (Trx6_SelectionDTO) super.getSelection();
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
        return ARMUtils.VARIABLE_VISIBLE_COLUMN_TRX6_DECUTION;
    }

    @Override
    protected void configureRightTable() {
        SummaryFieldFactory fieldFactory = new SummaryFieldFactory(getLogic(), getSelection(), ARMConstants.getDeductionCustomerContract().equals(String.valueOf(customerProductView.getValue())) && ARMConstants.getDeduction().equals(selection.getSummary_deductionLevelDes()));
        rightTable.setEditable(true);
        rightTable.setTableFieldFactory(fieldFactory);

    }

    @Override
    public boolean checkLeave() {
        boolean ret=true;
        return ret;
    }
    
    
    @Override
    protected boolean isPercentageColumn_2_Decimal(String column) {
        return column.contains("adjustmentRatio") ; //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean isRestrict(){
        return true;
    }
}
