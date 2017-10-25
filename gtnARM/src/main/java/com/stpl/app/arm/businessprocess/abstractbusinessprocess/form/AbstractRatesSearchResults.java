/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.abstractbusinessprocess.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.supercode.LogicAble;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.utils.CommonUtils;
import com.stpl.ifs.ui.util.converters.DataFormatConverter;
import com.stpl.ifs.util.constants.ARMConstants;
import com.vaadin.ui.ExtCustomTable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;

/**
 *
 * @author vinoth.parthasarathy
 */
public abstract class AbstractRatesSearchResults extends AbstractSearchResults {

    Object[] visibleColumns;
    String[] visibleHeaders;

    private ExtTreeContainer<AdjustmentDTO> resultBeanContainer = new ExtTreeContainer<AdjustmentDTO>(
            AdjustmentDTO.class, ExtContainer.DataStructureMode.LIST);

    public AbstractRatesSearchResults(LogicAble logic, AbstractSelectionDTO selection) {
        super(logic, selection);
    }

    @Override
    public void setVisibleColumnsAndHeaders() {
        configureOnRatesSearchResults();
        leftTable = table.getLeftFreezeAsTable();
        rightTable = table.getRightFreezeAsTable();
        rightTable.setImmediate(true);
        leftTable.setImmediate(true);
        leftTable.setSortEnabled(false);
        rightTable.setSortEnabled(false);
        loadTableHeader(null);
        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);

        }
        for (Object propertyId : leftTable.getVisibleColumns()) {
            leftTable.setColumnAlignment(propertyId, ExtCustomTable.Align.LEFT);
        }
        abstractSearchContent.setWidth("100%");
    }

    public ExtTreeContainer<AdjustmentDTO> getResultBeanContainer() {
        return resultBeanContainer;
    }

    protected void configureOnRatesSearchResults() {
        calculateBtn.setVisible(false);
        cancelOverride.setVisible(false);
        panelCaption.setCaption("Rate Results");
        valueDdlb.setVisible(false);
        BBExport.setPrimaryStyleName("link");
        BBExport.setIcon(ARMUtils.EXCEL_EXPORT_IMAGE, "Excel Export");
        customerProductView.addItems(new Object[]{ARMConstants.getDeductionProduct(),
            ARMConstants.getDeductionCustomer(), ARMConstants.getDeductionCustomerContract()});
        customerProductView.setValue(ARMConstants.getDeductionProduct());
    }

    @Override
    public void setExcelVisibleColumn() {
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
        for (Object visibleColumns : excelTable.getVisibleColumns()) {
            if (!visibleColumns.toString().equals("group")) {
                excelTable.setConverter(visibleColumns, new DataFormatConverter("#,##0.000", "%"), Boolean.FALSE);
            }
        }
    }

    /**
     *
     * @param selectionDTO
     */
    public void generateLogic(AbstractSelectionDTO selectionDTO) {
        if (!selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL)) {
            if (selectionDTO.getModuleName().equals(ARMConstants.getTransaction7())) {
                trx7RateProcedureCall(selectionDTO);
            } else {
                rateProcedureCall(selectionDTO);
            }
        }
        setDeductionView(selectionDTO);
        setRespectiveHierarchy(selectionDTO.getRate_DeductionView());
        configureLevelAndLevelFilter();
        selectionDTO.setLevelNo(1);
        loadTableHeader(selectionDTO.getRate_ColumnList());
        tableLogic.loadSetData(Boolean.FALSE);
    }

    /**
     *
     * @param columnList
     */
    private void loadTableHeader(List<List> columnList) {
        Map properties = new HashMap();
        if (columnList != null && !columnList.isEmpty()) {
            getTotalHeader(columnList);
            visibleColumns = ((List) columnList.get(0)).toArray();
            visibleHeaders = Arrays.copyOf(columnList.get(1).toArray(), columnList.get(1).size(), String[].class);
            for (Object visibleColumn : visibleColumns) {
                properties.put(visibleColumn, String.class);
            }
        } else {
            visibleColumns = new Object[]{"defaultColumn"};
            visibleHeaders = new String[]{StringUtils.EMPTY};
        }

        tableLogic.setContainerDataSource(resultBeanContainer);
        resultBeanContainer.setRecordHeader(Arrays.asList(visibleColumns));
        resultBeanContainer.setColumnProperties(properties);
        leftTable.setVisibleColumns(ARMUtils.PIPELINE_ACCRUAL_LEFT_COLUMN);
        leftTable.setColumnHeaders(ARMConstants.getDeductionProduct().equalsIgnoreCase(String.valueOf(customerProductView.getValue()))
                ? ARMUtils.PRODUCT : ARMUtils.CUSTOMER_SMALL);
        rightTable.setVisibleColumns(visibleColumns);
        rightTable.setColumnHeaders(visibleHeaders);
        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);

        }
    }

    @Override
    protected void configureRightTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected boolean calculateLogic() {
        return false;
    }

    @Override
    protected void customerProductValueChange() {
        if (resultBeanContainer != null && resultBeanContainer.size() > 0) {
            leftTable.setColumnHeaders(ARMConstants.getDeductionProduct().equalsIgnoreCase(String.valueOf(customerProductView.getValue()))
                    ? ARMUtils.PRODUCT : ARMUtils.CUSTOMER_SMALL);
            setDeductionView(selection);
            setRespectiveHierarchy(selection.getRate_DeductionView());
            configureLevelAndLevelFilter();
            tableLogic.loadSetData(Boolean.FALSE);
        }
    }

    /**
     *
     * @param selectionDTO
     */
    public void setDeductionView(AbstractSelectionDTO selectionDTO) {
        selectionDTO.setRate_DeductionView(String.valueOf(customerProductView.getValue()));
        selectionDTO.setRate_LevelName(ARMConstants.getDeductionProduct().equals(selectionDTO.getRate_DeductionView())
                ? "BRAND" : ARMConstants.getDeductionCustomer().equals(selectionDTO.getRate_DeductionView())
                ? "CUSTOMER" : (ARMConstants.getDeduction().equals(selectionDTO.getRate_DeductionLevelName()) ? "DEDUCTION" : "CUSTOMER"));
    }

    protected abstract void getTotalHeader(List<List> columnList);

    protected boolean setRespectiveLevelFileterValue(String levelValue, int levelNo) {
        selection.setRates_levelFilterNo(levelNo);
        selection.setRate_LevelName(levelValue.toUpperCase());
        if (levelNo == 0) {
            setDeductionView(selection);
        }
        return true;
    }

    public void rateProcedureCall(AbstractSelectionDTO selectionDTO) {
        Object[] orderedArgs = {selectionDTO.getDataSelectionDTO().getProjectionId(), selectionDTO.getRate_BasisName(),
            selectionDTO.getRate_Period(), selectionDTO.getRate_FrequencyName(), selectionDTO.getModuleName(),selectionDTO.getRate_Period(),
            selectionDTO.getSessionDTO().getUserId(), selectionDTO.getSessionDTO().getSessionId()};
        CommonUtils.callInsertProcedure("PRC_ARM_PIPELINE_RATE", orderedArgs);
    }

    @Override
    public Map<Integer, String> getHierarchy() {
        return getSelection().getRates_hierarchy();
    }

    @Override
    public void setRespectiveHierarchy(String viewType) {
        if (viewType.equals(ARMConstants.getDeductionCustomerContract()) && !getSelection().getRate_DeductionLevelName().equals(ARMConstants.getDeduction())) {
            viewType = "non" + ARMConstants.getDeductionCustomerContract();
        }
        getSelection().setRates_hierarchy(ARMUtils.getLevelAndLevelFilter(viewType));
    }

    public void trx7RateProcedureCall(AbstractSelectionDTO selectionDTO) {
        Object[] orderedArgs = {selectionDTO.getDataSelectionDTO().getProjectionId(), "Monthly",
            selectionDTO.getSessionDTO().getUserId(), selectionDTO.getSessionDTO().getSessionId()};
        CommonUtils.callInsertProcedure("PRC_ARM_DISTRIBUTION_FEES_RATE", orderedArgs);
    }

    @Override
    protected boolean getIsDemandSreen() {
        return Boolean.FALSE;
    }
}
