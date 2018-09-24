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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author porchelvi.g
 */
public class Trx7AdjustmentSummarySearchResultsPipelineAccrual extends AbstractPipelineSummaryResults {

    public static final Logger TR7_ADJ_SUM_RES_PA_LOGGER = LoggerFactory.getLogger(Trx7AdjustmentSummarySearchResultsPipelineAccrual.class);

    public Trx7AdjustmentSummarySearchResultsPipelineAccrual(Trx7PASummaryLogic logic, Trx7SelectionDTO selection) {
        super(logic, selection);
    }

    @Override
    public Trx7PASummaryLogic getExcelLogic() {
        return (Trx7PASummaryLogic) getSummaryLogic();
    }

    @Override
    public Object[] getExcelHierarchy() {
        Object[] tr7EscelValue = null;
        if (customerProductView.getValue().equals(ARMConstants.getDeductionCustomerContract()) && getSelection().getSummarydeductionLevelDes().equals(ARMConstants.getDeduction())) {
            tr7EscelValue = ARMUtils.getDCTBI();
        } else if (customerProductView.getValue().equals(ARMConstants.getDeductionCustomerContract())) {
            tr7EscelValue = ARMUtils.getTCBI();
        } else if (customerProductView.getValue().equals(ARMConstants.getDeductionCustomer())) {
            tr7EscelValue = ARMUtils.getTBI();
        } else if (customerProductView.getValue().equals(ARMConstants.getDeductionProduct())) {
            tr7EscelValue = ARMUtils.getBI();
        } else if (selection.getRateDeductionView().equals(CommonConstant.DEDUCTION_CONTRACT)) {
            tr7EscelValue = ARMUtils.getCTBI();

        }
        return tr7EscelValue;
    }

    @Override
    public List getExcelExportVisibleColumn() {
        TR7_ADJ_SUM_RES_PA_LOGGER.debug("Inside getExcelExportVisibleColumn");
        return getSelection().getExcelVisibleColumn();
    }

    @Override
    public String getExcelFileName() {
        TR7_ADJ_SUM_RES_PA_LOGGER.debug("Inside getExcelFileName");
        return "Adjustment Summary";
    }

    @Override
    public boolean getisFixedColumns() {
        TR7_ADJ_SUM_RES_PA_LOGGER.debug("Inside getisFixedColumns");
        return true;
    }

    @Override
    public int getInterval() {
        TR7_ADJ_SUM_RES_PA_LOGGER.debug("Inside getInterval");
        return NumericConstants.SIX;
    }

    @Override
    public int discountColumnNeeded() {
        TR7_ADJ_SUM_RES_PA_LOGGER.debug("Inside discountColumnNeeded");
        return 1;
    }

    @Override
    public boolean getisDeductionCustomer() {
        TR7_ADJ_SUM_RES_PA_LOGGER.debug("Inside getisDeductionCustomer");
        return (ARMConstants.getDeductionCustomerContract().equals(getSelection().getSummaryviewType()));
    }

    @Override
    protected String[] getExcelVariableVisibleColumns() {
        TR7_ADJ_SUM_RES_PA_LOGGER.debug("Inside getExcelVariableVisibleColumns");
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
        getSelection().setSummeryhierarchy(ARMUtils.getInstance().getTYrx7LevelAndLevelFilter(viewType));
    }

    @Override
    protected boolean getIsDemandSreen() {
        return false;
    }

    @Override
    protected boolean isPercentageColumnzeroDecimal(String column) {
        return column.contains("adjustmentRatio");
    }

}
