/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.abstractbusinessprocess.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractPipelineSummaryLogic;
import com.stpl.app.arm.businessprocess.pipelineaccrual.form.tablegenerator.SummaryFieldFactory;
import com.stpl.app.arm.supercode.LeaveCheckAble;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMConstants;
import com.vaadin.ui.ExtCustomTable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asha.Ravi
 */
public abstract class AbstractPipelineSummaryResults extends AbstractSummarySearchResults implements LeaveCheckAble {

    Object[] columns = {VariableConstants.MONTH};
    Object[] rightcolumns = {VariableConstants.MONTH};
    Map<Object, Object[]> left_double_single_visibleColumn = new HashMap<>();
    List DeductionList;
    String leftHeader = "Product";
    private boolean isGenarate = Boolean.FALSE;
    boolean isContractView = Boolean.FALSE;

    public AbstractPipelineSummaryResults(AbstractPipelineSummaryLogic logic, AbstractSelectionDTO selection) {
        super(logic, selection);
    }

    @Override
    public void setVisibleColumnsAndHeaders() {
        configureLevelDDLBs();
        configureOnAdjustmentSummarySearchResults();
        tableLogic.setContainerDataSource(resultBeanContainer);
        leftTable = table.getLeftFreezeAsTable();
        rightTable = table.getRightFreezeAsTable();
        rightTable.setImmediate(true);
        leftTable.setImmediate(true);
        table.setDoubleHeaderVisible(true);
        leftTable.setVisibleColumns(rightcolumns);
        leftTable.setColumnHeaders(leftHeader);
        leftTable.setDoubleHeaderVisibleColumns(columns);
        leftTable.setDoubleHeaderColumnHeaders("");
        rightTable.setVisibleColumns(rightcolumns);
        rightTable.setColumnHeaders("");
        rightTable.setDoubleHeaderVisibleColumns(columns);
        rightTable.setDoubleHeaderColumnHeaders("");
        left_double_single_visibleColumn.put(VariableConstants.MONTH, rightcolumns);
        leftTable.setDoubleHeaderMap(left_double_single_visibleColumn);
        rightTable.setDoubleHeaderMap(left_double_single_visibleColumn);
        for (Object propertyId : rightTable.getDoubleHeaderVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.CENTER);
        }
        rightTable.setDoubleHeaderMap(configure_RightDoubleHeaderMap());
        leftTable.setDoubleHeaderMap(configure_LeftDoubleHeaderMap());
        abstractSearchContent.setWidth("100%");
    }

    private Map<Object, Object[]> configure_RightDoubleHeaderMap() {
        Map<Object, Object[]> headerMap = new HashMap<>();
        headerMap.put(VariableConstants.MONTH, new String[]{VariableConstants.MONTH});
        return headerMap;
    }

    private Map<Object, Object[]> configure_LeftDoubleHeaderMap() {
        Map<Object, Object[]> headerMap = new HashMap<>();
        headerMap.put(VariableConstants.MONTH, new String[]{VariableConstants.MONTH});
        return headerMap;
    }

    private void configureOnAdjustmentSummarySearchResults() {
        panelCaption.setCaption("Results");
        calculateBtn.setVisible(true);
        calculateBtn.setEnabled(false);
        cancelOverride.setVisible(true);
        cancelOverride.setEnabled(false);
        valueDdlb.setVisible(false);
        BBExport.setPrimaryStyleName("link");
        BBExport.setIcon(ARMUtils.EXCEL_EXPORT_IMAGE, "Excel Export");
    }

    @Override
    public void setExcelVisibleColumn() {
        Map properties = new HashMap();
        List<Object> header = getLogic().generateHeader(getSelection(), getExcelVariableVisibleColumns());
        getSelection().setExcelVisibleColumn((ArrayList) header.get(NumericConstants.FIVE));
        List right_singleVisibleColumn = (ArrayList) header.get(NumericConstants.FIVE);
        List right_singleVisibleHeader = (ArrayList) header.get(NumericConstants.SIX);
        List<String> right_doubleVisibleColumn = (ArrayList) header.get(1);
        List<String> right_doubleVisibleHeader = (ArrayList) header.get(NumericConstants.THREE);
        for (Object variableColumn : right_singleVisibleColumn) {
            properties.put(variableColumn, String.class);
        }
        getExcelContainer().setColumnProperties(properties);
        getExcelContainer().setRecordHeader(right_singleVisibleColumn);
        List right_singleVisibleColumn1 = new ArrayList(right_singleVisibleColumn);
        right_singleVisibleColumn1.add(0, VariableConstants.MONTH);
        right_singleVisibleHeader.add(0, "Product");
        right_doubleVisibleColumn.add(0, VariableConstants.MONTH);
        right_doubleVisibleHeader.add(0, "");
        getExcelTable().setVisibleColumns(right_singleVisibleColumn1.toArray());
        getExcelTable().setColumnHeaders(Arrays.copyOf((right_singleVisibleHeader).toArray(), (right_singleVisibleHeader).size(), String[].class));
        getExcelTable().setDoubleHeaderVisible(Boolean.TRUE);
        getExcelTable().setDoubleHeaderVisibleColumns(right_doubleVisibleColumn.toArray());
        getExcelTable().setDoubleHeaderColumnHeaders(Arrays.copyOf(right_doubleVisibleHeader.toArray(), right_doubleVisibleHeader.size(), String[].class));
        getExcelTable().setDoubleHeaderMap((Map) header.get(NumericConstants.SEVEN));
        setConverter(getExcelTable(), getExcelTable().getVisibleColumns());
    }

    public void generateButtonLogic(String[] columns) {
        customerProductView.setValue(ARMConstants.getDeductionProduct());
        setValueChangeAllowed(false);
        isGenarate = Boolean.TRUE;
        Map properties = new HashMap();
        String view = String.valueOf(customerProductView.getValue());
        if (ARMConstants.getDeductionProduct().equalsIgnoreCase(view)) {
            leftHeader = "Product";
        } else {
            leftHeader = "Customer";
        }

        selection.setSummary_viewType(view);
        List<Object> header = getLogic().generateHeader(getSelection(), columns);
        List right_singleVisibleColumn = (ArrayList) header.get(0);
        List<String> right_doubleVisibleColumn = (ArrayList) header.get(1);
        Map<Object, Object[]> right_double_single_visibleColumn = (HashMap) header.get(NumericConstants.FOUR);
        for (int i = 0; i < right_singleVisibleColumn.size(); i++) {
            properties.put(right_singleVisibleColumn.get(i), String.class);
        }
        for (int i = 0; i < rightcolumns.length; i++) {
            properties.put(rightcolumns[i], String.class);
        }
        table.constructRightFreeze(true);
        leftTable = table.getLeftFreezeAsTable();
        leftTable.setColumnHeaders(leftHeader);
        rightTable = table.getRightFreezeAsTable();
        rightTable.setDoubleHeaderVisible(true);
        rightTable.setContainerDataSource(tableLogic.getContainerDataSource());
        resultBeanContainer.setRecordHeader(right_singleVisibleColumn);
        resultBeanContainer.setColumnProperties(properties);
        rightTable.setContainerDataSource(tableLogic.getContainerDataSource());
        rightTable.setVisibleColumns(right_singleVisibleColumn.toArray());
        rightTable.setColumnHeaders(Arrays.copyOf(((List) header.get(NumericConstants.TWO)).toArray(), ((List) header.get(NumericConstants.TWO)).size(), String[].class));
        rightTable.setDoubleHeaderVisibleColumns(right_doubleVisibleColumn.toArray());
        rightTable.setDoubleHeaderColumnHeaders(Arrays.copyOf(((List) header.get(NumericConstants.THREE)).toArray(), ((List) header.get(NumericConstants.THREE)).size(), String[].class));
        rightTable.setDoubleHeaderMap(right_double_single_visibleColumn);
        for (Object propertyId : rightTable.getDoubleHeaderVisibleColumns()) {
            rightTable.setDoubleHeaderColumnAlignment(propertyId, ExtCustomTable.Align.CENTER);

        }
        for (int i = 0; i < right_singleVisibleColumn.toArray().length; i++) {
            rightTable.setColumnAlignment(right_singleVisibleColumn.get(i), ExtCustomTable.Align.RIGHT);
        }
        List<String> headerList = Arrays.asList(rightTable.getColumnHeaders());
        //Added here for GAL-5809        
        if (ARMConstants.getDeductionCustomerContract().equals(view) && selection.getSummary_deductionLevelDes().equals(ARMConstants.getDeduction())
                && headerList.contains("Override")) { // Added for GAL-7545
            calculateBtn.setVisible(true);
            calculateBtn.setEnabled(true);
            cancelOverride.setVisible(true);
            cancelOverride.setEnabled(true);
            configureRightTable();
        } else {
            getSelection().setSummary_viewType(view);
            calculateBtn.setVisible(true);
            calculateBtn.setEnabled(false);
            cancelOverride.setVisible(true);
            cancelOverride.setEnabled(false);
        }

        if (ARMUtils.VIEW_SMALL.equals(selection.getSessionDTO().getAction())) {
            calculateBtn.setEnabled(false);
            cancelOverride.setEnabled(false);
        }
        tableLogic.loadSetData(Boolean.FALSE);
        setValueChangeAllowed(true);
    }

    /**
     * load and set the default selection for level DDLBs
     */
    protected void configureLevelDDLBs() {
        customerProductView.addItems(new Object[]{ARMConstants.getDeductionProduct(),
            ARMConstants.getDeductionCustomer(), ARMConstants.getDeductionCustomerContract()});
        customerProductView.setValue(ARMConstants.getDeductionProduct());
        getSelection().setSummary_viewType(ARMConstants.getDeductionProduct());
    }

    @Override
    protected void configureRightTable() {
        SummaryFieldFactory fieldFactory = new SummaryFieldFactory(getLogic(), getSelection(), ARMConstants.getDeductionCustomerContract().equals(String.valueOf(customerProductView.getValue())) && ARMConstants.getDeduction().equals(selection.getSummary_deductionLevelDes()));
        rightTable.setEditable(true);
        rightTable.setTableFieldFactory(fieldFactory);

    }

    /**
     * customerProductView ddlb.
     *
     * @param event the event
     */
    @Override
    protected void customerProductValueChange() {
        LOGGER.debug("customerProductView value change listener starts");
        String viewType = String.valueOf(customerProductView.getValue());
        LOGGER.debug("selectedView----" + viewType);
        leftTable.setColumnHeaders(ARMConstants.getDeductionProduct().equalsIgnoreCase(viewType)
                ? ARMUtils.PRODUCT : ARMUtils.CUSTOMER_SMALL);
        getSelection().setSummary_viewType(viewType);
        setRespectiveHierarchy(viewType);
        configureLevelAndLevelFilter();
        configureRightTable();
        if (isGenarate) {
            getTableLogic().loadSetData(Boolean.FALSE);
        }
        List<String> headerList = Arrays.asList(rightTable.getColumnHeaders());
        if (selection.getSessionDTO().isWorkFlow()
                && ARMUtils.EDIT.equalsIgnoreCase(selection.getSessionDTO().getAction())
                && ARMConstants.getDeductionCustomerContract().equals(viewType)
                && selection.getSummary_deductionLevelDes().equals(ARMConstants.getDeduction())
                && headerList.contains("Override")) {
            calculateBtn.setEnabled(true);
            cancelOverride.setEnabled(true);
        } else if (!selection.getSessionDTO().isWorkFlow()
                && ARMConstants.getDeductionCustomerContract().equals(viewType)
                && selection.getSummary_deductionLevelDes().equals(ARMConstants.getDeduction())
                && headerList.contains("Override")) {
            calculateBtn.setEnabled(true);
            cancelOverride.setEnabled(true);
        } else {
            calculateBtn.setEnabled(false);
            cancelOverride.setEnabled(false);
        }
    }

    protected boolean setRespectiveLevelFileterValue(String levelValue, int levelNo) {
        getSelection().setSummary_levelFilterNo(levelNo);
        getSelection().setSummary_levelFilterValue(levelValue);
        return true;
    }

    @Override
    public Map<Integer, String> getHierarchy() {
        return getSelection().getSummery_hierarchy();
    }

    @Override
    public void setRespectiveHierarchy(String viewType) {
        if (viewType.equals(ARMConstants.getDeductionCustomerContract()) && !getSelection().getSummary_deductionLevelDes().equals(ARMConstants.getDeduction())) {
            viewType = "non" + ARMConstants.getDeductionCustomerContract();
        }
        getSelection().setSummery_hierarchy(ARMUtils.getPipeLineLevelAndLevelFilter(viewType));
    }

    @Override
    public AbstractPipelineSummaryLogic getLogic() {
        return (AbstractPipelineSummaryLogic) super.getLogic();
    }

    protected abstract String[] getExcelVariableVisibleColumns();

    @Override
    protected void valueDdlbValueChange(int masterSids) {

    }

    @Override
    protected void loadLevelFilterValueDdlb(String levelValue, int levelNo) {

    }

    @Override
    protected boolean getIsDemandSreen() {
        return Boolean.TRUE;
    }

    @Override
    public boolean checkLeave() {
        return true;
    }

    @Override
    protected boolean isPercentageColumn_2_Decimal(String column) {
        return column.contains("pipelineRatio") || column.contains("adjustmentRatio"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isRestrict() {
        return false;
    }

}
