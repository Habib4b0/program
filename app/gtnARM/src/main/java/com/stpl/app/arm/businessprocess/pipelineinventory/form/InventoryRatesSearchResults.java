/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineinventory.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractRatesSearchResults;
import com.stpl.app.arm.businessprocess.pipelineinventory.logic.PipelineInventoryRatelogic;
import com.stpl.app.arm.supercode.ExcelInterface;
import com.stpl.app.arm.supercode.LogicAble;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.utils.ExcelUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.ifs.util.constants.ARMConstants;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class InventoryRatesSearchResults extends AbstractRatesSearchResults {

    private static final Logger INV_RATES_LOGGER = LoggerFactory.getLogger(InventoryRatesSearchResults.class);

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
        INV_RATES_LOGGER.debug("Inside getExcelLogic");
        return getSummaryLogic();
    }

    @Override
    public Object[] getExcelHierarchy() {
        INV_RATES_LOGGER.debug("Inside getExcelHierarchy");
        Object[] value = null;
        if (customerProductView.getValue().equals(ARMConstants.getDeductionCustomerContract()) && getSelection().getRateDeductionLevelName().equals(ARMConstants.getDeduction())) {
            value = new Object[]{"D", "T", "C", "B", "I"};
        } else if (customerProductView.getValue().equals(ARMConstants.getDeductionCustomerContract())) {
            value = new Object[]{"T", "C", "B", "I"};
        } else if (customerProductView.getValue().equals(ARMConstants.getDeductionContractCustomer()) && getSelection().getRateDeductionLevelName().equals(ARMConstants.getDeduction())) {
            value = new Object[]{"D", "C", "T", "B", "I"};
        } else if (customerProductView.getValue().equals(ARMConstants.getDeductionContractCustomer())) {
            value = new Object[]{"C", "T", "B", "I"};
        } else if (customerProductView.getValue().equals(ARMConstants.getDeductionCustomer())) {
            value = new Object[]{"T", "B", "I"};
        } else if (customerProductView.getValue().equals(ARMConstants.getDeductionProduct())) {
            value = new Object[]{"B", "I"};
        }
        INV_RATES_LOGGER.debug("End Of getExcelHierarchy");
        return value;
    }

    @Override
    public List getExcelExportVisibleColumn() {
        INV_RATES_LOGGER.debug("Inside getExcelExportVisibleColumn");
        return selection.getRateColumnList().get(0);
    }

    @Override
    public String getExcelFileName() {
        INV_RATES_LOGGER.debug("Inside getExcelFileName");
        return "Rates";
    }

    @Override
    public boolean getisFixedColumns() {
        INV_RATES_LOGGER.debug("Inside getisFixedColumns");
        return true;
    }

    @Override
    public int getInterval() {
        INV_RATES_LOGGER.debug("Inside getInterval");
        return 1;
    }

    @Override
    public int discountColumnNeeded() {
        INV_RATES_LOGGER.debug("Inside discountColumnNeeded");
        return 1;
    }

    @Override
    public boolean getisDeductionCustomer() {
        INV_RATES_LOGGER.debug("Inside getisDeductionCustomer");
        return false;
    }

    @Override
    protected void valueDdlbValueChange(int masterSids){
        INV_RATES_LOGGER.debug("inside valueDdlbValueChange Method");

    }

    @Override
    protected void loadLevelFilterValueDdlb(String levelValue, int levelNo) {
        INV_RATES_LOGGER.debug("inside loadLevelFilterValueDdlb Method");
    }

    @Override
    public void rateProcedureCall(AbstractSelectionDTO selectionDTO) {
        Object[] orderedArgs = {selectionDTO.getDataSelectionDTO().getProjectionId(), selectionDTO.getRateBasisName(),
            selectionDTO.getRatePeriodValue(), selectionDTO.getRateFrequencyName(), selectionDTO.getModuleName(), selectionDTO.getInventoryDetails(),
            selectionDTO.getSessionDTO().getUserId(), selectionDTO.getSessionDTO().getSessionId()};
        selectionDTO.setRatesOverrideFlag(NumericConstants.ZERO);
        QueryUtil.callProcedure("PRC_ARM_PIPELINE_RATE", orderedArgs);
    }

    @Override
    public PipelineInventoryRatelogic getSummaryLogic() {
        return (PipelineInventoryRatelogic) super.getSummaryLogic();
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
    protected void callExcelCustomization(List list, Object[] excelHierarchy) throws IllegalAccessException, InvocationTargetException {
        List<Object> listData = new ArrayList<>();
        listData.add(getisFixedColumns());
        listData.add(getisDeductionCustomer());
        listData.add(getIsDemandSreen());
        listData.add(getInterval());

        ExcelUtils.setExcelData(list, excelHierarchy, getExcelExportVisibleColumn(), getExcelContainer(), discountColumnNeeded(), "Rates", listData);
    }
}
