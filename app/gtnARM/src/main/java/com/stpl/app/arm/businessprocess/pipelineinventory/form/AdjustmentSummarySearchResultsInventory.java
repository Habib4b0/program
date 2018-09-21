/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineinventory.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractPipelineSummaryResults;
import com.stpl.app.arm.businessprocess.pipelineinventory.dto.PipelineInventorySelectionDTO;
import com.stpl.app.arm.businessprocess.pipelineinventory.logic.PISummaryLogic;
import com.stpl.app.arm.supercode.ExcelInterface;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMConstants;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class AdjustmentSummarySearchResultsInventory extends AbstractPipelineSummaryResults {
    
    public static final Logger SUMMARY_RESULTS_LOGGER = LoggerFactory.getLogger(AdjustmentSummarySearchResultsInventory.class);
    
    public AdjustmentSummarySearchResultsInventory(PISummaryLogic logic, PipelineInventorySelectionDTO selection) {
        super(logic, selection);
    }

    @Override
    public ExcelInterface getExcelLogic() {
        return getSummaryLogic();
    }

    @Override
    public PipelineInventorySelectionDTO getSelection() {
        return (PipelineInventorySelectionDTO) super.getSelection();
    }

    @Override
    public Object[] getExcelHierarchy() {
        SUMMARY_RESULTS_LOGGER.debug("Inside getExcelHierarchy");
        Object[] value = null;
        if (customerProductView.getValue().equals(ARMConstants.getDeductionCustomerContract()) && getSelection().getSummarydeductionLevelDes().equals(ARMConstants.getDeduction())) {
            value = ARMUtils.getDCTBI();
        } else if (customerProductView.getValue().equals(ARMConstants.getDeductionCustomerContract())) {
            value = ARMUtils.getTCBI();
        } else if (customerProductView.getValue().equals(ARMConstants.getDeductionCustomer())) {
            value = ARMUtils.getTBI();
        } else if (customerProductView.getValue().equals(ARMConstants.getDeductionProduct())) {
            value = ARMUtils.getBI();
        }
        SUMMARY_RESULTS_LOGGER.debug("End of getExcelHierarchy");
        return value;
    }

    @Override
    public List getExcelExportVisibleColumn() {
        SUMMARY_RESULTS_LOGGER.debug("Inside getExcelExportVisibleColumn");
        return getSelection().getExcelVisibleColumn();
    }

    @Override
    public String getExcelFileName() {
        SUMMARY_RESULTS_LOGGER.debug("Inside getExcelFileName");
        return "Adjustment Summary";
    }

    @Override
    public boolean getisFixedColumns() {
        SUMMARY_RESULTS_LOGGER.debug("Inside getisFixedColumns");
        return true;
    }

    @Override
    public int getInterval() {
        SUMMARY_RESULTS_LOGGER.debug("Inside getInterval");
        return NumericConstants.SIX;
    }

    @Override
    public int discountColumnNeeded() {
        SUMMARY_RESULTS_LOGGER.debug("Inside discountColumnNeeded");
        return 1;
    }

    @Override
    public boolean getisDeductionCustomer() {
        SUMMARY_RESULTS_LOGGER.debug("Inside getisDeductionCustomer");
        return false;
    }

    @Override
    protected String[] getExcelVariableVisibleColumns() {
        SUMMARY_RESULTS_LOGGER.debug("Inside getExcelVariableVisibleColumns");
        return VariableConstants.getVariableVisibleColumnDedutionPi();
    }

    protected void valueDdlbValueChange(String levelFilterValue, int masterSids) {
        SUMMARY_RESULTS_LOGGER.debug("Inside valueDdlbValueChange Method{}", levelFilterValue + masterSids);

    }
}
