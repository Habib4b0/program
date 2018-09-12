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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Srithar
 */
public class Trx6AdjustmentSummarySearchResults extends AbstractPipelineSummaryResults {
    
    public static final Logger LOGGER_TX6_SUMMARY = LoggerFactory.getLogger(Trx6AdjustmentSummarySearchResults.class);

    public Trx6AdjustmentSummarySearchResults(Trx6SummaryLogic logic, Trx6SelectionDTO selection) {
        super(logic, selection);
    }

    @Override
    public ExcelInterface getExcelLogic() {
        return getSummaryLogic();
    }

    @Override
    public Trx6SelectionDTO getSelection() {
        return (Trx6SelectionDTO) super.getSelection();
    }

    @Override
    public Object[] getExcelHierarchy() {
        LOGGER_TX6_SUMMARY.debug("Inside getExcelHierarchy");
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
        LOGGER_TX6_SUMMARY.debug("End of getExcelHierarchy");
        return value;
    }

    @Override
    public List getExcelExportVisibleColumn() {
        LOGGER_TX6_SUMMARY.debug("Inside getExcelHierarchy");
        return getSelection().getExcelVisibleColumn();
    }

    @Override
    public String getExcelFileName() {
        LOGGER_TX6_SUMMARY.debug("Inside getExcelFileName");
        return "Adjustment Summary";
    }

    @Override
    public boolean getisFixedColumns() {
        LOGGER_TX6_SUMMARY.debug("Inside getisFixedColumns");
        return Boolean.TRUE;
    }

    @Override
    public int getInterval() {
        LOGGER_TX6_SUMMARY.debug("Inside getInterval");
        return NumericConstants.SIX;
    }

    @Override
    public int discountColumnNeeded() {
        LOGGER_TX6_SUMMARY.debug("Inside discountColumnNeeded");
        return 1;
    }

    @Override
    public boolean getisDeductionCustomer() {
        LOGGER_TX6_SUMMARY.debug("Inside getisDeductionCustomer");
        return Boolean.FALSE;
    }

    @Override
    protected String[] getExcelVariableVisibleColumns() {
        LOGGER_TX6_SUMMARY.debug("Inside getExcelVariableVisibleColumns");
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
