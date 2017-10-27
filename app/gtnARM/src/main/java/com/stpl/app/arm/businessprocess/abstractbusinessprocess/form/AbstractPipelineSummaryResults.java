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
import com.stpl.app.arm.utils.CommonConstant;
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

    protected Object[] columns = {VariableConstants.MONTH};
    protected Object[] rightcolumns = {VariableConstants.MONTH};
    Map<Object, Object[]> leftDoubleSingleVisibleColumn = new HashMap<>();
    List deductionList;
    protected String leftHeader = CommonConstant.PRODUCT;
    protected boolean isGenarate = Boolean.FALSE;
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
        leftDoubleSingleVisibleColumn.put(VariableConstants.MONTH, rightcolumns);
        leftTable.setDoubleHeaderMap(leftDoubleSingleVisibleColumn);
        rightTable.setDoubleHeaderMap(leftDoubleSingleVisibleColumn);
        for (Object propertyId : rightTable.getDoubleHeaderVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.CENTER);
        }
        rightTable.setDoubleHeaderMap(configureRightDoubleHeaderMap());
        leftTable.setDoubleHeaderMap(configureLeftDoubleHeaderMap());
        abstractSearchContent.setWidth("100%");
    }

    private Map<Object, Object[]> configureRightDoubleHeaderMap() {
        Map<Object, Object[]> headerMap = new HashMap<>();
        headerMap.put(VariableConstants.MONTH, new String[]{VariableConstants.MONTH});
        return headerMap;
    }

    private Map<Object, Object[]> configureLeftDoubleHeaderMap() {
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
        bbExport.setPrimaryStyleName("link");
        bbExport.setIcon(ARMUtils.EXCEL_EXPORT_IMAGE, "Excel Export");
    }

    @Override
    public void setExcelVisibleColumn() {
        Map properties = new HashMap();
        List<Object> header = getSummaryLogic().generateHeader(getSelection(), getExcelVariableVisibleColumns());
        getSelection().setExcelVisibleColumn((ArrayList) header.get(NumericConstants.FIVE));
        List rightSingleVisibleColumn = (ArrayList) header.get(NumericConstants.FIVE);
        List rightSingleVisibleHeader = (ArrayList) header.get(NumericConstants.SIX);
        List<String> rightDoubleVisibleColumn = (ArrayList) header.get(1);
        List<String> rightoubleVisibleHeader = (ArrayList) header.get(NumericConstants.THREE);
        for (Object variableColumn : rightSingleVisibleColumn) {
            properties.put(variableColumn, String.class);
        }
        getExcelContainer().setColumnProperties(properties);
        getExcelContainer().setRecordHeader(rightSingleVisibleColumn);
        List rightingleVisibleColumn1 = new ArrayList(rightSingleVisibleColumn);
        rightingleVisibleColumn1.add(0, VariableConstants.MONTH);
        rightSingleVisibleHeader.add(0, CommonConstant.PRODUCT);
        rightDoubleVisibleColumn.add(0, VariableConstants.MONTH);
        rightoubleVisibleHeader.add(0, "");
        getExcelTable().setVisibleColumns(rightingleVisibleColumn1.toArray());
        getExcelTable().setColumnHeaders(
                Arrays.copyOf((rightSingleVisibleHeader).toArray(), (rightSingleVisibleHeader).size(), String[].class));
        getExcelTable().setDoubleHeaderVisible(Boolean.TRUE);
        getExcelTable().setDoubleHeaderVisibleColumns(rightDoubleVisibleColumn.toArray());
        getExcelTable().setDoubleHeaderColumnHeaders(
                Arrays.copyOf(rightoubleVisibleHeader.toArray(), rightoubleVisibleHeader.size(), String[].class));
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
            leftHeader = CommonConstant.PRODUCT;
        } else {
            leftHeader = "Customer";
        }

        selection.setSummaryviewType(view);
        List<Object> header = getSummaryLogic().generateHeader(getSelection(), columns);
        List rightSingleVisibleColumn = (ArrayList) header.get(0);
        List<String> rightDoubleVisibleColumn = (ArrayList) header.get(1);
        Map<Object, Object[]> rightDoubleSingleVisibleColumn = (HashMap) header.get(NumericConstants.FOUR);
        for (int i = 0; i < rightSingleVisibleColumn.size(); i++) {
            properties.put(rightSingleVisibleColumn.get(i), String.class);
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
        resultBeanContainer.setRecordHeader(rightSingleVisibleColumn);
        resultBeanContainer.setColumnProperties(properties);
        rightTable.setContainerDataSource(tableLogic.getContainerDataSource());
        rightTable.setVisibleColumns(rightSingleVisibleColumn.toArray());
        rightTable.setColumnHeaders(Arrays.copyOf(((List) header.get(NumericConstants.TWO)).toArray(),
                ((List) header.get(NumericConstants.TWO)).size(), String[].class));
        rightTable.setDoubleHeaderVisibleColumns(rightDoubleVisibleColumn.toArray());
        rightTable.setDoubleHeaderColumnHeaders(Arrays.copyOf(((List) header.get(NumericConstants.THREE)).toArray(),
                ((List) header.get(NumericConstants.THREE)).size(), String[].class));
        rightTable.setDoubleHeaderMap(rightDoubleSingleVisibleColumn);
        for (Object propertyId : rightTable.getDoubleHeaderVisibleColumns()) {
            rightTable.setDoubleHeaderColumnAlignment(propertyId, ExtCustomTable.Align.CENTER);

        }
        for (int i = 0; i < rightSingleVisibleColumn.toArray().length; i++) {
            rightTable.setColumnAlignment(rightSingleVisibleColumn.get(i), ExtCustomTable.Align.RIGHT);
        }
        List<String> headerList = Arrays.asList(rightTable.getColumnHeaders());
        // Added here for GAL-5809
        if (ARMConstants.getDeductionCustomerContract().equals(view)
                && selection.getSummarydeductionLevelDes().equals(ARMConstants.getDeduction())
                && headerList.contains(CommonConstant.OVERRIDE)) { // Added for
            // GAL-7545
            calculateBtn.setVisible(true);
            calculateBtn.setEnabled(true);
            cancelOverride.setVisible(true);
            cancelOverride.setEnabled(true);
            configureRightTable();
        } else {
            getSelection().setSummaryviewType(view);
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
    private static final Object[] ddlbConfig = {ARMConstants.getDeductionProduct(),
        ARMConstants.getDeductionCustomer(), ARMConstants.getDeductionCustomerContract()};

    protected void configureLevelDDLBs() {
        customerProductView.addItems(ddlbConfig);
        customerProductView.setValue(ARMConstants.getDeductionProduct());
        getSelection().setSummaryviewType(ARMConstants.getDeductionProduct());
    }

    @Override
    protected void configureRightTable() {
        SummaryFieldFactory fieldFactory = new SummaryFieldFactory(getSummaryLogic(), getSelection(),
                ARMConstants.getDeductionCustomerContract().equals(String.valueOf(customerProductView.getValue()))
                && ARMConstants.getDeduction().equals(selection.getSummarydeductionLevelDes()));
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
        leftTable.setColumnHeaders(ARMConstants.getDeductionProduct().equalsIgnoreCase(viewType) ? ARMUtils.PRODUCT
                : ARMUtils.CUSTOMER_SMALL);
        getSelection().setSummaryviewType(viewType);
        setRespectiveHierarchy(viewType);
        configureLevelAndLevelFilter();
        configureRightTable();
        if (isGenarate) {
            getTableLogic().loadSetData(Boolean.FALSE);
        }
        List<String> headerList = Arrays.asList(rightTable.getColumnHeaders());
        if ((selection.getSessionDTO().isWorkFlow()
                && ARMUtils.EDIT.equalsIgnoreCase(selection.getSessionDTO().getAction()) || (!selection.getSessionDTO().isWorkFlow()))
                && ARMConstants.getDeductionCustomerContract().equals(viewType)
                && selection.getSummarydeductionLevelDes().equals(ARMConstants.getDeduction())
                && headerList.contains(CommonConstant.OVERRIDE)) {
            calculateBtn.setEnabled(true);
            cancelOverride.setEnabled(true);
        } else {
            calculateBtn.setEnabled(false);
            cancelOverride.setEnabled(false);
        }
    }

    @Override
    protected boolean setRespectiveLevelFileterValue(String levelValue, int levelNo) {
        getSelection().setSummarylevelFilterNo(levelNo);
        getSelection().setSummarylevelFilterValue(levelValue);
        return true;
    }

    @Override
    public Map<Integer, String> getHierarchy() {
        return getSelection().getSummeryhierarchy();
    }

    @Override
    public void setRespectiveHierarchy(String viewType) {
        String viewTypevalue;
        if (viewType.equals(ARMConstants.getDeductionCustomerContract())
                && !getSelection().getSummarydeductionLevelDes().equals(ARMConstants.getDeduction())) {
            viewTypevalue = "non" + ARMConstants.getDeductionCustomerContract();
        } else {
            viewTypevalue = viewType;
        }
        getSelection().setSummeryhierarchy(ARMUtils.getPipeLineLevelAndLevelFilter(viewTypevalue));
    }

    @Override
    public AbstractPipelineSummaryLogic getSummaryLogic() {
        return (AbstractPipelineSummaryLogic) super.getSummaryLogic();
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
    protected boolean isPercentageColumn2Decimal(String column) {
        return column.contains("pipelineRatio") || column.contains("adjustmentRatio"); // To
        // change
        // body
        // of
        // generated
        // methods,
        // choose
        // Tools
        // |
        // Templates.
    }

    @Override
    public boolean isRestrict() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
