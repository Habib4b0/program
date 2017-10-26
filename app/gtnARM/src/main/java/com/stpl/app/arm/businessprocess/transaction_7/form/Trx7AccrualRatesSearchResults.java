/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction_7.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractRatesSearchResults;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractBPLogic;
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

    public Trx7AccrualRatesSearchResults(AbstractBPLogic logic, AbstractSelectionDTO selection) {
        super(logic, selection);
    }

    @Override
    protected void getTotalHeader(List<List> columnList) {

    }

    @Override
    public ExcelInterface getExcelLogic() {
        return (ExcelInterface) getLogic();
    }

    @Override
    public Object[] getExcelHierarchy() {
        Map<Integer, String> hierarchy = getHierarchy();
        Object[] value = new Object[hierarchy.size()];
        if (customerProductView.getValue().equals(ARMConstants.getDeductionCustomerContract()) && getSelection().getRate_DeductionLevelName().equals(ARMConstants.getDeduction())) {
            value = new Object[]{"D", "T", "C", "B", "I"};
        } else if (customerProductView.getValue().equals(ARMConstants.getDeductionCustomerContract())) {
            value = new Object[]{"D", "T", "C", "B", "I"};
        } else if (customerProductView.getValue().equals(ARMConstants.getDeductionCustomer())) {
            value = new Object[]{"T", "C", "B", "I"};
        } else if (customerProductView.getValue().equals(ARMConstants.getDeductionProduct())) {
            value = new Object[]{"B", "I"};
        } else if (selection.getRate_DeductionView().equals(VariableConstants.DEDUCTION_CONTRACT)) {
            value = new Object[]{"C", "T", "B", "I"};
        }
        return value;
    }

    @Override
    public List getExcelExportVisibleColumn() {
        return getSelection().getRate_ColumnList().get(0);
    }

    @Override
    public String getExcelFileName() {
        return "Rates";
    }

    @Override
    public boolean getisFixedColumns() {
        return Boolean.TRUE;
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
        return Boolean.FALSE;
    }

    @Override
    protected void valueDdlbValueChange(int masterSids) {

    }

    @Override
    protected void loadLevelFilterValueDdlb(String levelValue, int levelNo) {
     
    }
    
    @Override
    public void setRespectiveHierarchy(String viewType) {
        getSelection().setRates_hierarchy(ARMUtils.getTYrx7LevelAndLevelFilter(viewType));
    }
    
    /*
     * @param selectionDTO
    */
    @Override
    public void setDeductionView(AbstractSelectionDTO selectionDTO) {
        selectionDTO.setRate_DeductionView(String.valueOf(customerProductView.getValue()));
        selectionDTO.setRate_LevelName(ARMConstants.getDeductionProduct().equals(selectionDTO.getRate_DeductionView())
                ? VariableConstants.BRAND_UPPERCASE : ARMConstants.getDeductionCustomer().equals(selectionDTO.getRate_DeductionView())
                        ? VariableConstants.CUSTOMER_UPPERCASE : VariableConstants.DEDUCTION_CONTRACT.equals(selectionDTO.getRate_DeductionView())? VariableConstants.CONTRACT_UPPERCASE:ARMConstants.getDeduction().equals(selectionDTO.getRate_DeductionLevelName())?"DEDUCTION" : VariableConstants.CUSTOMER_UPPERCASE  );
        
    }
    
    @Override
    protected void configureOnRatesSearchResults() {
        calculateBtn.setVisible(false);
        cancelOverride.setVisible(false);
        panelCaption.setCaption("Rate Results");
        valueDdlb.setVisible(false);
        BBExport.setPrimaryStyleName("link");
        BBExport.setIcon(ARMUtils.EXCEL_EXPORT_IMAGE, "Excel Export");
        customerProductView.addItems(new Object[]{ARMConstants.getDeductionProduct(),
         ARMConstants.getDeductionCustomer(), ARMConstants.getDeductionCustomerContract(),VariableConstants.DEDUCTION_CONTRACT});
        customerProductView.setValue(VariableConstants.DEDUCTION_CONTRACT);
    }
    
    @Override
    protected boolean getIsDemandSreen() {
        return Boolean.FALSE;
    }
    
    @Override
    protected void loadRespectiveHierarchy() {
        setRespectiveHierarchy(ARMConstants.getDeductionContract());
    }
}