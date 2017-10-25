/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineinventory.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractRatesSearchResults;
import com.stpl.app.arm.supercode.ExcelInterface;
import com.stpl.app.arm.supercode.LogicAble;
import com.stpl.app.utils.CommonUtils;
import com.stpl.ifs.util.constants.ARMConstants;
import java.util.List;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class InventoryRatesSearchResults extends AbstractRatesSearchResults {

    public InventoryRatesSearchResults(LogicAble logic, AbstractSelectionDTO selection) {
        super(logic, selection);
    }

    @Override
    protected void getTotalHeader(List<List> columnList) {
        if (columnList != null && !columnList.isEmpty()) {
            columnList.get(0).add("totalColumn");
            columnList.get(1).add("Total");
        }
    }

    @Override
    public ExcelInterface getExcelLogic() {
        return (ExcelInterface) getLogic();
    }

    @Override
    public Object[] getExcelHierarchy() {
        Object[] value = null;
        if (customerProductView.getValue().equals(ARMConstants.getDeductionCustomerContract()) && getSelection().getRate_DeductionLevelName().equals(ARMConstants.getDeduction())) {
            value = new Object[]{"D", "T", "C", "B", "I"};
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
        return selection.getRate_ColumnList().get(0);
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
    public void rateProcedureCall(AbstractSelectionDTO selectionDTO) {
        Object[] orderedArgs = {selectionDTO.getDataSelectionDTO().getProjectionId(), selectionDTO.getRate_BasisName(),
            selectionDTO.getRate_Period(), selectionDTO.getRate_FrequencyName(), selectionDTO.getModuleName(),selectionDTO.getInventory_Details(),
            selectionDTO.getSessionDTO().getUserId(), selectionDTO.getSessionDTO().getSessionId()};
        CommonUtils.callInsertProcedure("PRC_ARM_PIPELINE_RATE", orderedArgs);
    }
}
