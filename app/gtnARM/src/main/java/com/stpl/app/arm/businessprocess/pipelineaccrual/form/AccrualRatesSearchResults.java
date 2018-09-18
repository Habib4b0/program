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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author porchelvi.g
 */
public class AccrualRatesSearchResults extends AbstractRatesSearchResults {

    private static final Logger RATES_RES_LOGGER = LoggerFactory.getLogger(AccrualRatesSearchResults.class);

    public AccrualRatesSearchResults(AbstractBPLogic logic, AbstractSelectionDTO selection) {
        super(logic, selection);
    }

    @Override
    protected void getTotalHeader(List<List> columnList) {
        RATES_RES_LOGGER.debug("Inside getTotalHeader");
        return;

    }

    @Override
    public ExcelInterface getExcelLogic() {
        RATES_RES_LOGGER.debug("Inside getExcelLogic");
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
        RATES_RES_LOGGER.debug("Inside getExcelLogic");
        return getSelection().getRateColumnList().get(0);
    }

    @Override
    public String getExcelFileName() {
        RATES_RES_LOGGER.debug("Inside getExcelLogic");
        return "Rates";
    }

    @Override
    public boolean getisFixedColumns() {
        RATES_RES_LOGGER.debug("Inside getExcelLogic");
        return true;
    }

    @Override
    public int getInterval() {
        RATES_RES_LOGGER.debug("Inside getExcelLogic");
        return 1;
    }

    @Override
    public int discountColumnNeeded() {
        RATES_RES_LOGGER.debug("Inside getExcelLogic");
        return 1;
    }

    @Override
    public boolean getisDeductionCustomer() {
        RATES_RES_LOGGER.debug("Inside getExcelLogic");
        return (ARMConstants.getDeductionCustomerContract().equals(getSelection().getSummaryviewType())
                || ARMConstants.getDeductionContractCustomer().equals(getSelection().getSummaryviewType()));
    }

    @Override
    protected void valueDdlbValueChange(int masterSids) {
        RATES_RES_LOGGER.debug("Inside getExcelLogic");
    }

    @Override
    protected void loadLevelFilterValueDdlb(String levelValue, int levelNo) {
        RATES_RES_LOGGER.debug("Inside getExcelLogic");
    }
    private static final Object[] RATES_SEARCH_RESULT = {ARMConstants.getDeductionProduct(),
        ARMConstants.getDeductionCustomer(), ARMConstants.getDeductionCustomerContract(), ARMConstants.getDeductionContractCustomer()};

    @Override
    protected void configureOnRatesSearchResults() {
        boolean visible = false;
        calculateBtn.setVisible(visible);
        cancelOverride.setVisible(visible);
        panelCaption.setCaption("Rate Results");
        valueDdlb.setVisible(visible);
        bbExport.setPrimaryStyleName("link");
        bbExport.setIcon(ARMUtils.EXCEL_EXPORT_IMAGE, EXCEL_EXPORT);
        customerProductView.addItems(RATES_SEARCH_RESULT);
        customerProductView.setValue(ARMConstants.getDeductionProduct());
    }
    private static final String EXCEL_EXPORT = "Excel Export";

    @Override
    public PipelineAccrualRateLogic getSummaryLogic() {
        return (PipelineAccrualRateLogic) super.getSummaryLogic();
    }
}
