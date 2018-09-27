/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction7.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractRatesSearchResults;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractBPLogic;
import com.stpl.app.arm.businessprocess.transaction7.logic.Trx7PipelineAccrualRateLogic;
import com.stpl.app.arm.supercode.ExcelInterface;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.util.constants.ARMConstants;
import java.util.List;
import java.util.Map;

/**
 *
 * @author porchelvi.g
 */
public class Trx7AccrualRatesSearchResults extends AbstractRatesSearchResults {

    private static final org.slf4j.Logger TR7_RATES_LOGGER = org.slf4j.LoggerFactory.getLogger(Trx7AccrualRatesSearchResults.class);

    public Trx7AccrualRatesSearchResults(AbstractBPLogic logic, AbstractSelectionDTO selection) {
        super(logic, selection);
    }

    @Override
    protected void getTotalHeader(List<List> columnList) {
        TR7_RATES_LOGGER.debug("inside getTotalHeader Method");

    }

    @Override
    public ExcelInterface getExcelLogic() {
        return getSummaryLogic();
    }

    @Override
    public Object[] getExcelHierarchy() {
        Map<Integer, String> hierarchy = getHierarchy();
        Object[] value = new Object[hierarchy.size()];
        if (customerProductView.getValue().equals(ARMConstants.getDeductionCustomerContract()) && getSelection().getRateDeductionLevelName().equals(ARMConstants.getDeduction())) {
            value = ARMUtils.getDTCBI();
        } else if (customerProductView.getValue().equals(ARMConstants.getDeductionCustomerContract())) {
            value = ARMUtils.getDTCBI();
        } else if (customerProductView.getValue().equals(ARMConstants.getDeductionCustomer())) {
            value = ARMUtils.getTCBI();
        } else if (customerProductView.getValue().equals(ARMConstants.getDeductionProduct())) {
            value = ARMUtils.getBI();
        } else if (selection.getRateDeductionView().equals(VariableConstants.DEDUCTION_CONTRACT)) {
            value = ARMUtils.getCTBI();
        }
        return value;
    }

    @Override
    public List getExcelExportVisibleColumn() {
        return getSelection().getRateColumnList().get(0);
    }

    @Override
    public String getExcelFileName() {
        return "Rates";
    }

    @Override
    public boolean getisFixedColumns() {
        return true;
    }

    @Override
    public int getInterval() {
        return 1;
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
    protected void valueDdlbValueChange(int masterSids) {
        TR7_RATES_LOGGER.debug("inside valueDdlbValueChange Method");

    }

    @Override
    protected void loadLevelFilterValueDdlb(String levelValue, int levelNo) {
        TR7_RATES_LOGGER.debug("inside loadLevelFilterValueDdlb Method");
    }

    @Override
    public void setRespectiveHierarchy(String view) {
        String viewType = view;
        if (viewType.equals(ARMConstants.getDeductionCustomer())) {
            viewType = ARMConstants.getDeductionCustomer() + "Sales";
        }
        getSelection().setRateshierarchy(ARMUtils.getInstance().getTYrx7LevelAndLevelFilter(viewType));
    }

    /*
     * @param selectionDTO
     */
    @Override
    public void setDeductionView(AbstractSelectionDTO selectionDTO) {
        selectionDTO.setRateDeductionView(String.valueOf(customerProductView.getValue()));
        String deductionContractCustomer = ARMConstants.getDeductionContractCustomer().equals(selectionDTO.getRateDeductionView()) ? VariableConstants.CONTRACT_UPPERCASE : VariableConstants.CUSTOMER_UPPERCASE;
        String deduction = ARMConstants.getDeduction().equals(selectionDTO.getRateDeductionLevelName()) ? "DEDUCTION" : deductionContractCustomer;
        String deductionContract = VariableConstants.DEDUCTION_CONTRACT.equals(selectionDTO.getRateDeductionView()) ? VariableConstants.CONTRACT_UPPERCASE : deduction;
        String deductionCustomer = ARMConstants.getDeductionCustomer().equals(selectionDTO.getRateDeductionView())
                ? VariableConstants.CUSTOMER_UPPERCASE : deductionContract;
        selectionDTO.setRateLevelName(ARMConstants.getDeductionProduct().equals(selectionDTO.getRateDeductionView())
                ? VariableConstants.BRAND_UPPERCASE : deductionCustomer);

    }

    @Override
    protected void configureOnRatesSearchResults() {
        calculateBtn.setVisible(false);
        cancelOverride.setVisible(false);
        panelCaption.setCaption("Rate Results");
        valueDdlb.setVisible(false);
        bbExport.setPrimaryStyleName("link");
        bbExport.setIcon(ARMUtils.EXCEL_EXPORT_IMAGE, "Excel Export");
        customerProductView.addItems(new Object[]{ARMConstants.getDeductionProduct(),
            ARMConstants.getDeductionCustomer(), ARMConstants.getDeductionCustomerContract(), VariableConstants.DEDUCTION_CONTRACT, ARMConstants.getDeductionContractCustomer()});
        customerProductView.setValue(VariableConstants.DEDUCTION_CONTRACT);
    }

    @Override
    protected boolean getIsDemandSreen() {
        return false;
    }

    @Override
    protected void loadRespectiveHierarchy() {
        setRespectiveHierarchy(ARMConstants.getDeductionContract());
    }

    @Override
    public Trx7PipelineAccrualRateLogic getSummaryLogic() {
        return (Trx7PipelineAccrualRateLogic) super.getSummaryLogic();
    }
}
