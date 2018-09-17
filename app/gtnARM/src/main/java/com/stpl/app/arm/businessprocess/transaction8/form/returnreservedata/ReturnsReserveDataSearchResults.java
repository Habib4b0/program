/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction8.form.returnreservedata;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractSearchResults;
import com.stpl.app.arm.businessprocess.transaction8.logic.RRDataLogic;
import com.stpl.app.arm.supercode.ExcelInterface;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.utils.ExcelUtils;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.DataFormatConverter;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.ifs.util.constants.ARMConstants;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author
 */
public class ReturnsReserveDataSearchResults extends AbstractSearchResults {

    private Object[] visibleColumns;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReturnsReserveDataSearchResults.class);

    private String[] visibleHeaders;

    public ReturnsReserveDataSearchResults(RRDataLogic logic, AbstractSelectionDTO selection) {
        super(logic, selection);
    }

    @Override
    public void setVisibleColumnsAndHeaders() {
        Map properties = new HashMap();
        configureTable();
        tableLogic.setContainerDataSource(resultBeanContainer);
        resultBeanContainer.setRecordHeader(Arrays.asList((Object[]) ARMUtils.getReturnReserveDataColumns()));
        for (Object visibleColumn : ARMUtils.getReturnReserveDataColumns()) {
            properties.put(visibleColumn, String.class);
        }
        resultBeanContainer.setColumnProperties(properties);
        leftTable.setVisibleColumns(VariableConstants.GROUP_RETURNS);
        leftTable.setColumnHeaders(CommonConstant.CUSTOMER_PRODUCT);
        rightTable.setVisibleColumns((Object[]) ARMUtils.getReturnReserveDataColumns());
        rightTable.setColumnHeaders(ARMUtils.getReturnReserveDataHeaders());
        configureExcel();
    }

    @Override
    protected boolean calculateLogic() {
        return false;
    }

    @Override
    protected boolean setRespectiveLevelFileterValue(String levelValue, int levelNo) {
        selection.setReturnReserveDatalevelFilterNo(levelNo);
        selection.setReturnReserveDataLevelName(levelValue.toUpperCase(Locale.ENGLISH));
        if (levelNo == 0) {
            setDeductionView(selection);
        }
        return true;
    }

    @Override
    protected void loadLevelFilterValueDdlb(String levelValue, int levelNo) {
        return;

    }

    @Override
    protected void valueDdlbValueChange(int masterSids) {
        return;
    }

    @Override
    public void setExcelVisibleColumn() {
        try {
            Map properties = new HashMap();
            excelTable.setContainerDataSource(excelBeanContainer);
            excelBeanContainer.setRecordHeader(resultBeanContainer.getRecordHeader());
            for (Object prop : resultBeanContainer.getRecordHeader().toArray()) {
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
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    @Override
    protected void configureRightTable() {
        return;
    }

    @Override
    protected void customerProductValueChange() {
        if (resultBeanContainer != null && resultBeanContainer.size() > 0) {
            leftTable.setColumnHeaders(CommonConstant.CUSTOMER_PRODUCT);
            setDeductionView(selection);
            setRespectiveHierarchy(selection.getReturnReserveDataDeductionView());
            configureLevelAndLevelFilter();
            tableLogic.loadSetData(false);
        }
    }

    @Override
    public Map getHierarchy() {
        return getSelection().getReturnReserveDataHierarchy();
    }

    @Override
    public void setRespectiveHierarchy(String viewType) {
        getSelection().setReturnReserveDataHierarchy(ARMUtils.getReserveDataLevelAndLevelFilter(viewType));
    }

    @Override
    protected void loadRespectiveHierarchy() {
        setRespectiveHierarchy(ARMUtils.CONTRACT_CUSTOMER);
    }

    @Override
    public ExcelInterface getExcelLogic() {
        return getSummaryLogic();
    }

    @Override
    public RRDataLogic getSummaryLogic() {
        return (RRDataLogic) super.getSummaryLogic();
    }

    private void configureExcel() {
        bbExport.setPrimaryStyleName("link");
        bbExport.setIcon(ARMUtils.EXCEL_EXPORT_IMAGE, "Excel Export");
    }

    /**
     *
     * @param selectionDTO
     */
    public void generateLogic(AbstractSelectionDTO selectionDTO) {
        if (!selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL)) {
            procedureCall(selectionDTO);
        }
        setDeductionView(selectionDTO);
        setRespectiveHierarchy(selectionDTO.getReturnReserveDataDeductionView());
        configureLevelAndLevelFilter();
        selectionDTO.setLevelNo(1);
        loadTableHeader(selectionDTO.getReturnReserveDataVariables());
        tableLogic.loadSetData(false);
    }

    private void procedureCall(AbstractSelectionDTO selectionDTO) {
        int input;
        if (selectionDTO.getRemoveClosedBatches() == NumericConstants.ONE && selectionDTO.getExcludeBasedOnLoeDate() == NumericConstants.ONE) {
            input = 2;
        } else if (selectionDTO.getRemoveClosedBatches() == NumericConstants.ONE) {
            input = 0;
        } else if (selectionDTO.getExcludeBasedOnLoeDate() == NumericConstants.ONE) {
            input = 1;
        } else {
            input = 3;
        }
        Object[] orderedArgs = {selectionDTO.getDataSelectionDTO().getProjectionId(),
            selectionDTO.getSessionDTO().getUserId(), selectionDTO.getSessionDTO().getSessionId(), input,
            selection.getOriginalSaleLimiterVal()};
        QueryUtil.callProcedure("PRC_ARM_RETURN_RESERVE", orderedArgs);
    }

    private void loadTableHeader(List<List> returnReserveDataColumnList) {
        Map properties = new HashMap();
        if (returnReserveDataColumnList != null && !returnReserveDataColumnList.isEmpty()) {
            visibleColumns = (returnReserveDataColumnList.get(NumericConstants.THREE)).toArray();
            visibleHeaders = Arrays.copyOf(returnReserveDataColumnList.get(NumericConstants.ONE).toArray(), returnReserveDataColumnList.get(NumericConstants.ONE).size(), String[].class);
            for (Object visiCol : visibleColumns) {
                properties.put(visiCol, String.class);
            }
        } else {
            visibleColumns = new Object[]{"defaultColumn"};
            visibleHeaders = new String[]{StringUtils.EMPTY};
        }

        tableLogic.setContainerDataSource(resultBeanContainer);
        resultBeanContainer.setRecordHeader(Arrays.asList(visibleColumns));
        resultBeanContainer.setColumnProperties(properties);
        leftTable.setVisibleColumns(VariableConstants.GROUP_RETURNS);
        leftTable.setColumnHeaders(CommonConstant.CUSTOMER_PRODUCT);
        rightTable.setVisibleColumns(visibleColumns);
        rightTable.setColumnHeaders(visibleHeaders);
        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);

        }
        rightTable.setColumnAlignment("brand.0", ExtCustomTable.Align.LEFT);

    }

    private void setDeductionView(AbstractSelectionDTO selectionDTO) {
        selectionDTO.setReturnReserveDataDeductionView(String.valueOf(customerProductView.getValue()));
        selectionDTO.setReturnReserveDataLevelName(VariableConstants.DEDUCTION_UPPERCASE);
    }

    @Override
    protected void loadLevelValues() {
        Object[] obj = new Object[]{ARMUtils.CONTRACT_CUSTOMER, ARMUtils.CUSTOMER_CONTRACT};
        customerProductView.setImmediate(true);
        customerProductView.addItems(obj);
        customerProductView.setValue(ARMUtils.CONTRACT_CUSTOMER);
    }

    private void configureTable() {
        calculateBtn.setVisible(false);
        cancelOverride.setVisible(false);
        panelCaption.setCaption("Rate Results");
        valueDdlb.setVisible(false);
        bbExport.setPrimaryStyleName("link");
        bbExport.setIcon(ARMUtils.EXCEL_EXPORT_IMAGE, "Excel Export");
        Object[] addItem = new Object[]{ARMUtils.CONTRACT_CUSTOMER, ARMUtils.CUSTOMER_CONTRACT};
        customerProductView.addItems(addItem);
        customerProductView.setValue(ARMUtils.CONTRACT_CUSTOMER);
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
    public List getExcelExportVisibleColumn() {
        return Arrays.asList(ARMUtils.getReturnReserveDataColumns());
    }

    @Override
    public String getExcelFileName() {
        return "ReturnReserveData";
    }

    @Override
    public boolean getisFixedColumns() {
        return false;
    }

    @Override
    public int getInterval() {
        return NumericConstants.ZERO;
    }

    @Override
    public boolean getisDeductionCustomer() {
        return false;
    }

    @Override
    public int discountColumnNeeded() {
        return 1;
    }

    @Override
    protected boolean getIsDemandSreen() {
        return false;
    }

    @Override
    public Object[] getExcelHierarchy() {
        Object[] value = null;
        if (customerProductView.getValue().equals(ARMUtils.CONTRACT_CUSTOMER)) {
            value = new Object[]{"D", "C", "T", "B", "I"};
        } else if (customerProductView.getValue().equals(ARMUtils.CUSTOMER_CONTRACT)) {
            value = new Object[]{"D", "T", "C", "B", "I"};
        }
        return value;
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
    protected void setConverter(ExtCustomTable excelTable, Object[] visibleColumns) {
        DataFormatConverter currency2Dec = new DataFormatConverter(ARMConstants.getTwoDecFormat(), DataFormatConverter.INDICATOR_DOLLAR);
        DataFormatConverter rate2Dec = new DataFormatConverter(ARMConstants.getTwoDecFormat(), DataFormatConverter.INDICATOR_PERCENT);
        DataFormatConverter rate3Dec = new DataFormatConverter(ARMConstants.getThreeDecFormat(), DataFormatConverter.INDICATOR_PERCENT);
        DataFormatConverter zeroDec = new DataFormatConverter(ARMConstants.getNoDecFormat());
        DataFormatConverter perzeroDec = new DataFormatConverter(ARMConstants.getNoDecFormat(), DataFormatConverter.INDICATOR_PERCENT);
        DataFormatConverter currencyzeroDec = new DataFormatConverter(ARMConstants.getCurrNoDecFormat());
        DataFormatConverter num2Dec = new DataFormatConverter(ARMConstants.getTwoDecFormat());
        for (Object visibleColumn : visibleColumns) {
            if (!VariableConstants.GROUP_RETURNS.equals(String.valueOf(visibleColumn)) && !"month".equals(String.valueOf(visibleColumn)) && !String.valueOf(visibleColumn).contains("brand")) {
                if (isPercentageColumnzeroDecimal(visibleColumn.toString())) {
                    excelTable.setConverter(visibleColumn, perzeroDec, false);
                } else if (isPercentageColumn2Decimal(visibleColumn.toString())) {
                    excelTable.setConverter(visibleColumn, rate2Dec, false);
                } else if (isNumericTwoDecimalFormat(visibleColumn.toString())) {
                    excelTable.setConverter(visibleColumn, num2Dec, false);
                } else if (isPercentageColumn3Decimal(visibleColumn.toString())) {
                    excelTable.setConverter(visibleColumn, rate3Dec, false);
                } else if (isUnitColumn(visibleColumn.toString())) {
                    excelTable.setConverter(visibleColumn, zeroDec, false);
                } else if (isCurrencyZeroDecimalFormat(visibleColumn.toString())) {
                    excelTable.setConverter(visibleColumn, currencyzeroDec, false);
                } else {
                    excelTable.setConverter(visibleColumn, currency2Dec, false);
                }
            }
        }
    }

    @Override
    protected boolean isUnitColumn(String column) {
        return column.contains("Units");
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
