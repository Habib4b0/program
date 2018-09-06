/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction8.form.rates;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractRatesSearchResults;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractBPLogic;
import com.stpl.app.arm.businessprocess.transaction8.logic.RRRatesLogic;
import com.stpl.app.arm.businessprocess.transaction8.tablegenerator.RatesTableGenerator;
import com.stpl.app.arm.supercode.ExcelInterface;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.utils.ExcelUtils;
import com.stpl.ifs.util.constants.ARMConstants;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import java.util.HashMap;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.DataFormatConverter;
import com.stpl.ifs.util.QueryUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Locale;
import org.asi.ui.extfilteringtable.ExtCustomTable;

/**
 *
 * @author
 */
public class RatesReturnReserveSearchResults extends AbstractRatesSearchResults {

    private final Logger loggerRates = LoggerFactory.getLogger(getClass());

    public RatesReturnReserveSearchResults(AbstractBPLogic logic, AbstractSelectionDTO selection) {
        super(logic, selection);
    }

    @Override
    public void setVisibleColumnsAndHeaders() {
        Map properties = new HashMap();
        configureOnRatesSearchResults();
        tableLogic.setContainerDataSource(resultBeanContainerVal);
        resultBeanContainerVal.setRecordHeader(Arrays.asList((Object[]) ARMUtils.getReturnReserveRatesColumns()));
        for (Object visibleColumn : ARMUtils.getReturnReserveRatesColumns()) {
            properties.put(visibleColumn, String.class);
        }
        resultBeanContainerVal.setColumnProperties(properties);
        leftTable.setVisibleColumns("group");
        leftTable.setColumnHeaders("Customer/Product");
        rightTable.setVisibleColumns((Object[]) ARMUtils.getReturnReserveRatesColumns());
        rightTable.setColumnHeaders(ARMUtils.getReturnReserveRatesHeaders());
        rightTable.setColumnAlignment("override.2", ExtCustomTable.Align.RIGHT);
        rightTable.setColumnAlignment("rate.1", ExtCustomTable.Align.RIGHT);

        configureExcel();
    }

    @Override
    public Map getHierarchy() {
        return getSelection().getRateshierarchy();
    }

    @Override
    public void setRespectiveHierarchy(String viewType) {
        getSelection().setRateshierarchy(ARMUtils.getTrx8LevelAndLevelFilter(viewType));
    }

    @Override
    public ExcelInterface getExcelLogic() {
        return getSummaryLogic();
    }

    private void configureExcel() {
        bbExport.setPrimaryStyleName("link");
        bbExport.setIcon(ARMUtils.EXCEL_EXPORT_IMAGE, "Excel Export");
    }

    @Override
    protected void getTotalHeader(List<List> columnList) {
        loggerRates.debug("inside getTotalHeader Method");

    }

    @Override
    public void rateProcedureCall(AbstractSelectionDTO selectionDTO) {
        Object[] orderedArgs = {selectionDTO.getDataSelectionDTO().getProjectionId(),
            selectionDTO.getSessionDTO().getUserId(), selectionDTO.getSessionDTO().getSessionId()};
        QueryUtil.callProcedure("PRC_ARM_RETURN_RATE", orderedArgs);
    }

    @Override
    protected void configureOnRatesSearchResults() {
        calculateBtn.setVisible(false);
        cancelOverride.setVisible(false);
        panelCaption.setCaption("Rate Results");
        valueDdlb.setVisible(false);
        bbExport.setPrimaryStyleName("link");
        bbExport.setIcon(ARMUtils.EXCEL_EXPORT_IMAGE, "Excel Export");
        Object[] addItem = new Object[]{ARMConstants.getDeductionProduct(),
            ARMConstants.getDeductionCustomer(), ARMConstants.getDeductionContract()};
        customerProductView.addItems(addItem);
        customerProductView.setValue(ARMConstants.getDeductionProduct());
    }

    @Override
    protected void loadLevelFilterValueDdlb(String levelValue, int levelNo) {
        loggerRates.debug("inside loadLevelFilterValueDdlb Method");
    }

    @Override
    protected void valueDdlbValueChange(int masterSids) {
        loggerRates.debug("inside valueDdlbValueChange Method");

    }

    @Override
    public Object[] getExcelHierarchy() {
        Map<Integer, String> hierarchy = getHierarchy();
        Object[] value = new Object[hierarchy.size()];
        for (int i = 0; i < hierarchy.size(); i++) {
            String val = hierarchy.get(i + 1);
            if (val.equalsIgnoreCase(ARMUtils.levelVariablesVarables.DEDUCTION.toString())) {
                val = getSelection().getRateDeductionLevelName().toUpperCase(Locale.ENGLISH);
            }
            value[i] = ARMUtils.getLevelExcelQueryName(val);
        }
        getSelection().setExcelHierarchy(value);
        return value;
    }

    @Override
    public List getExcelExportVisibleColumn() {
        return Arrays.asList(ARMUtils.getReturnReserveRatesColumns());
    }

    @Override
    public String getExcelFileName() {
        return "Rates";
    }

    @Override
    public boolean getisFixedColumns() {
        return Boolean.FALSE;
    }

    @Override
    public int getInterval() {
        return NumericConstants.THREE;
    }

    @Override
    public boolean getisDeductionCustomer() {
        return Boolean.FALSE;
    }

    @Override
    public int discountColumnNeeded() {
        return 1;
    }

    @Override
    protected void loadTableHeader(List<List> columnList) {
        configureRightTable();
    }

    @Override
    public void setDeductionView(AbstractSelectionDTO selectionDTO) {
        selectionDTO.setRateDeductionView(String.valueOf(customerProductView.getValue()));
        selectionDTO.setRateLevelName(VariableConstants.DEDUCTION_UPPERCASE);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    protected void configureRightTable() {
        RatesTableGenerator fieldFactory = new RatesTableGenerator(getSummaryLogic(), getSelection(), true, (Object[]) ARMUtils.getReturnReserveRatesColumns(), getTableLogic(), true);
        rightTable.setEditable(true);
        rightTable.setTableFieldFactory(fieldFactory);
    }

    @Override
    public void setExcelVisibleColumn() {
        Map properties = new HashMap();
        excelTable.setContainerDataSource(excelBeanContainer);
        excelBeanContainer.setRecordHeader(resultBeanContainerVal.getRecordHeader());
        for (Object prop : resultBeanContainerVal.getRecordHeader().toArray()) {
            properties.put(prop, String.class);
        }
        excelBeanContainer.setColumnProperties(properties);
        List<Object> visibleColumn = new ArrayList<>();
        List<String> columnHeader = new ArrayList<>();
        visibleColumn.addAll(Arrays.asList(table.getLeftFreezeAsTable().getVisibleColumns()));
        visibleColumn.addAll(Arrays.asList(table.getRightFreezeAsTable().getVisibleColumns()));
        List<String> headerList = Arrays.asList(table.getLeftFreezeAsTable().getColumnHeaders());
        List<String> headerList2 = Arrays.asList(table.getRightFreezeAsTable().getColumnHeaders());
        columnHeader.addAll(headerList);
        columnHeader.addAll(headerList2);
        excelTable.setVisibleColumns(visibleColumn.toArray());
        excelTable.setColumnHeaders(columnHeader.toArray(new String[0]));
        setConverter(getExcelTable(), getExcelTable().getVisibleColumns());
    }

    @Override
    protected void setConverter(ExtCustomTable excelTable, Object[] visibleColumns) {
        DataFormatConverter currency2Dec = new DataFormatConverter(ARMConstants.getTwoDecFormat(), DataFormatConverter.INDICATOR_DOLLAR);
        DataFormatConverter rate2Dec = new DataFormatConverter(ARMConstants.getTwoDecFormat(), DataFormatConverter.INDICATOR_PERCENT);
        DataFormatConverter rate3Dec = new DataFormatConverter(ARMConstants.getThreeDecFormat(), DataFormatConverter.INDICATOR_PERCENT);
        DataFormatConverter zeroDec = new DataFormatConverter(ARMConstants.getNoDecFormat());
        DataFormatConverter perzeroDec = new DataFormatConverter(ARMConstants.getNoDecFormat(), DataFormatConverter.INDICATOR_PERCENT);
        DataFormatConverter currencyzeroDec = new DataFormatConverter(ARMConstants.getCurrNoDecFormat());
        DataFormatConverter num2Dec = new DataFormatConverter(ARMConstants.getTwoDecFormat());
        for (Object visibleColumn : visibleColumns) {
            if (!"group".equals(String.valueOf(visibleColumn)) && !"month".equals(String.valueOf(visibleColumn)) && !String.valueOf(visibleColumn).contains("methodology")) {
                if (isPercentageColumnzeroDecimal(visibleColumn.toString())) {
                    excelTable.setConverter(visibleColumn, perzeroDec, Boolean.FALSE);
                } else if (isPercentageColumn2Decimal(visibleColumn.toString())) {
                    excelTable.setConverter(visibleColumn, rate2Dec, Boolean.FALSE);
                } else if (isNumericTwoDecimalFormat(visibleColumn.toString())) {
                    excelTable.setConverter(visibleColumn, num2Dec, Boolean.FALSE);
                } else if (isPercentageColumn3Decimal(visibleColumn.toString())) {
                    excelTable.setConverter(visibleColumn, rate3Dec, Boolean.FALSE);
                } else if (isUnitColumn(visibleColumn.toString())) {
                    excelTable.setConverter(visibleColumn, zeroDec, Boolean.FALSE);
                } else if (isCurrencyZeroDecimalFormat(visibleColumn.toString())) {
                    excelTable.setConverter(visibleColumn, currencyzeroDec, Boolean.FALSE);
                } else {
                    excelTable.setConverter(visibleColumn, currency2Dec, Boolean.FALSE);
                }
            }
        }
    }

    @Override
    protected boolean isPercentageColumn2Decimal(String column) {
        return column.contains("rate") || column.contains("override");
    }

    @Override
    protected void callExcelCustomization(List list, Object[] excelHierarchy) throws IllegalAccessException, InvocationTargetException {
        List<Object> listData = new ArrayList<>();
        listData.add(getisFixedColumns());
        listData.add(getisDeductionCustomer());
        listData.add(getIsDemandSreen());
        listData.add(getInterval());

        ExcelUtils.setExcelData(list, excelHierarchy, getExcelExportVisibleColumn(), getExcelContainer(), discountColumnNeeded(), ARMConstants.getTransaction8(), listData);
    }

    @Override
    public RRRatesLogic getSummaryLogic() {
        return (RRRatesLogic) super.getSummaryLogic();
    }
}
