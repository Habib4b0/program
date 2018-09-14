/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineaccrual.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractRatesSearchResults;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractBPLogic;
import com.stpl.app.arm.businessprocess.pipelineaccrual.logic.PipelineAccrualRateLogic;
import com.stpl.app.arm.supercode.ExcelInterface;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.ifs.util.constants.ARMConstants;
import java.util.List;
import java.util.Map;

/**
 *
 * @author porchelvi.g
 */
public class AccrualRatesSearchResults extends AbstractRatesSearchResults {

    public AccrualRatesSearchResults(AbstractBPLogic logic, AbstractSelectionDTO selection) {
        super(logic, selection);
    }

    @Override
    protected void getTotalHeader(List<List> columnList) {
        return;

    }

    @Override
    public ExcelInterface getExcelLogic() {
        return getSummaryLogic();
    }

    @Override
    public Object[] getExcelHierarchy() {
        Map<Integer, String> hierarchy = getHierarchy();
        Object[] value = new Object[hierarchy.size()];
        for (int i = 0; i < hierarchy.size(); i++) {
            String val = hierarchy.get(i + 1);
            value[i] = ARMUtils.getLevelExcelQueryName(val);
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
        if (ARMConstants.getDeductionCustomerContract().equals(getSelection().getSummaryviewType())
                || ARMConstants.getDeductionContractCustomer().equals(getSelection().getSummaryviewType())) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    protected void valueDdlbValueChange(int masterSids) {
        return;
    }

    @Override
    protected void loadLevelFilterValueDdlb(String levelValue, int levelNo) {
        return;
    }
    private static final Object[] searchResult = {ARMConstants.getDeductionProduct(),
        ARMConstants.getDeductionCustomer(), ARMConstants.getDeductionCustomerContract(), ARMConstants.getDeductionContractCustomer()};

    @Override
    protected void configureOnRatesSearchResults() {
        calculateBtn.setVisible(false);
        cancelOverride.setVisible(false);
        panelCaption.setCaption("Rate Results");
        valueDdlb.setVisible(false);
        bbExport.setPrimaryStyleName("link");
        bbExport.setIcon(ARMUtils.EXCEL_EXPORT_IMAGE, "Excel Export");
        customerProductView.addItems(searchResult);
        customerProductView.setValue(ARMConstants.getDeductionProduct());
    }

    @Override
    public PipelineAccrualRateLogic getSummaryLogic() {
        return (PipelineAccrualRateLogic) super.getSummaryLogic();
    }
}
