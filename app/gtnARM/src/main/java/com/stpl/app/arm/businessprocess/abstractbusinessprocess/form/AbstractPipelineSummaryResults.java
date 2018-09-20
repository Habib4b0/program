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
import org.asi.ui.extfilteringtable.ExtCustomTable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Asha.Ravi
 */
public abstract class AbstractPipelineSummaryResults extends AbstractSummarySearchResults implements LeaveCheckAble {

    protected Object[] pipelineSummaryColumns = {VariableConstants.MONTH};
    protected Object[] pipelineSummaryRightcolumns = {VariableConstants.MONTH};
    private Map<Object, Object[]> pipelineSummaryLeftDoubleSingleVisibleColumn = new HashMap<>();
    protected String pipelineSummaryLeftHeader = CommonConstant.PRODUCT;
    protected boolean isGenarate = false;
    private AbstractSelectionDTO pipelineSummarySelection;
    public static final Logger SUMMARY_RESULTS_LOGGER = LoggerFactory.getLogger(AbstractPipelineSummaryResults.class);

    public AbstractPipelineSummaryResults(AbstractPipelineSummaryLogic logic, AbstractSelectionDTO selection) {
        super(logic, selection);
        this.pipelineSummarySelection = selection;
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
        leftTable.setVisibleColumns(pipelineSummaryRightcolumns);
        leftTable.setColumnHeaders(pipelineSummaryLeftHeader);
        leftTable.setDoubleHeaderVisibleColumns(pipelineSummaryColumns);
        leftTable.setDoubleHeaderColumnHeaders("");
        rightTable.setVisibleColumns(pipelineSummaryRightcolumns);
        rightTable.setColumnHeaders("");
        rightTable.setDoubleHeaderVisibleColumns(pipelineSummaryColumns);
        rightTable.setDoubleHeaderColumnHeaders("");
        pipelineSummaryLeftDoubleSingleVisibleColumn.put(VariableConstants.MONTH, pipelineSummaryRightcolumns);
        leftTable.setDoubleHeaderMap(pipelineSummaryLeftDoubleSingleVisibleColumn);
        rightTable.setDoubleHeaderMap(pipelineSummaryLeftDoubleSingleVisibleColumn);
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
        getSelection().setExcelVisibleColumn((List) header.get(NumericConstants.EIGHT));
        List rightSingleVisibleColumn = (List) header.get(NumericConstants.FIVE);
        List rightSingleVisibleHeader = (List) header.get(NumericConstants.SIX);
        List<String> rightDoubleVisibleColumn = (List) header.get(1);
        List<String> rightoubleVisibleHeader = (List) header.get(NumericConstants.THREE);
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
        getExcelTable().setDoubleHeaderVisible(true);
        getExcelTable().setDoubleHeaderVisibleColumns(rightDoubleVisibleColumn.toArray());
        getExcelTable().setDoubleHeaderColumnHeaders(
                Arrays.copyOf(rightoubleVisibleHeader.toArray(), rightoubleVisibleHeader.size(), String[].class));
        getExcelTable().setDoubleHeaderMap((Map) header.get(NumericConstants.SEVEN));
        setConverter(getExcelTable(), getExcelTable().getVisibleColumns());
    }

    public void generateButtonLogic(String[] columns) {
        customerProductView.setValue(ARMConstants.getDeductionProduct());
        setValueChangeAllowed(false);
        isGenarate = true;
        Map pipelineProperties = new HashMap();
        String pipelineView = String.valueOf(customerProductView.getValue());
        if (ARMConstants.getDeductionProduct().equalsIgnoreCase(pipelineView)) {
            pipelineSummaryLeftHeader = CommonConstant.PRODUCT;
        } else {
            pipelineSummaryLeftHeader = "Customer";
        }

        pipelineSummarySelection.setSummaryviewType(pipelineView);
        List<Object> pipelineHeader = getSummaryLogic().generateHeader(getSelection(), columns);
        List pipelineRightSingleVisibleColumn = (List) pipelineHeader.get(0);
        List<String> pipelineRightDoubleVisibleColumn = (List) pipelineHeader.get(1);
        Map<Object, Object[]> pipelineRightDoubleSingleVisibleColumn = (HashMap) pipelineHeader.get(NumericConstants.FOUR);
        for (int i = 0; i < pipelineRightSingleVisibleColumn.size(); i++) {
            pipelineProperties.put(pipelineRightSingleVisibleColumn.get(i), String.class);
        }
        for (int i = 0; i < pipelineSummaryRightcolumns.length; i++) {
            pipelineProperties.put(pipelineSummaryRightcolumns[i], String.class);
        }
        table.constructRightFreeze(true);
        leftTable = table.getLeftFreezeAsTable();
        leftTable.setColumnHeaders(pipelineSummaryLeftHeader);
        rightTable = table.getRightFreezeAsTable();
        rightTable.setDoubleHeaderVisible(true);
        rightTable.setContainerDataSource(tableLogic.getContainerDataSource());
        resultBeanContainer.setRecordHeader(pipelineRightSingleVisibleColumn);
        resultBeanContainer.setColumnProperties(pipelineProperties);
        resultBeanContainer.setIndexable(true);
        rightTable.setContainerDataSource(tableLogic.getContainerDataSource());
        rightTable.setVisibleColumns(pipelineRightSingleVisibleColumn.toArray());
        rightTable.setColumnHeaders(Arrays.copyOf(((List) pipelineHeader.get(NumericConstants.TWO)).toArray(),
                ((List) pipelineHeader.get(NumericConstants.TWO)).size(), String[].class));
        rightTable.setDoubleHeaderVisibleColumns(pipelineRightDoubleVisibleColumn.toArray());
        rightTable.setDoubleHeaderColumnHeaders(Arrays.copyOf(((List) pipelineHeader.get(NumericConstants.THREE)).toArray(),
                ((List) pipelineHeader.get(NumericConstants.THREE)).size(), String[].class));
        rightTable.setDoubleHeaderMap(pipelineRightDoubleSingleVisibleColumn);
        for (Object propertyId : rightTable.getDoubleHeaderVisibleColumns()) {
            rightTable.setDoubleHeaderColumnAlignment(propertyId, ExtCustomTable.Align.CENTER);

        }
        for (int i = 0; i < pipelineRightSingleVisibleColumn.toArray().length; i++) {
            rightTable.setColumnAlignment(pipelineRightSingleVisibleColumn.get(i), ExtCustomTable.Align.RIGHT);
        }
        List<String> headerList = Arrays.asList(rightTable.getColumnHeaders());
        // Added here for GAL-5809
        boolean pipelineFlag = true;
        if (ARMConstants.getDeductionCustomerContract().equals(pipelineView)
                && pipelineSummarySelection.getSummarydeductionLevelDes().equals(ARMConstants.getDeduction())
                && headerList.contains(CommonConstant.OVERRIDE)) { // Added for
            // GAL-7545

            calculateBtn.setVisible(pipelineFlag);
            calculateBtn.setEnabled(pipelineFlag);
            cancelOverride.setVisible(pipelineFlag);
            cancelOverride.setEnabled(pipelineFlag);
            configureRightTable();
        } else {
            getSelection().setSummaryviewType(pipelineView);
            calculateBtn.setVisible(pipelineFlag);
            calculateBtn.setEnabled(!pipelineFlag);
            cancelOverride.setVisible(pipelineFlag);
            cancelOverride.setEnabled(!pipelineFlag);
        }

        if (ARMUtils.VIEW_SMALL.equals(pipelineSummarySelection.getSessionDTO().getAction())) {
            calculateBtn.setEnabled(!pipelineFlag);
            cancelOverride.setEnabled(!pipelineFlag);
        }
        setConverter(rightTable, rightTable.getVisibleColumns());
        tableLogic.loadSetData(false);
        setValueChangeAllowed(pipelineFlag);
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
                && ARMConstants.getDeduction().equals(pipelineSummarySelection.getSummarydeductionLevelDes()));
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
        String pipelineViewType = String.valueOf(customerProductView.getValue());
        LOGGER.debug("selectedView----{}", pipelineViewType);
        leftTable.setColumnHeaders(ARMConstants.getDeductionProduct().equalsIgnoreCase(pipelineViewType) ? ARMUtils.PRODUCT
                : ARMUtils.CUSTOMER_SMALL);
        getSelection().setSummaryviewType(pipelineViewType);
        setRespectiveHierarchy(pipelineViewType);
        configureLevelAndLevelFilter();
        configureRightTable();
        if (isGenarate) {
            getTableLogic().loadSetData(false);
        }
        List<String> headerList = Arrays.asList(rightTable.getColumnHeaders());
        if ((pipelineSummarySelection.getSessionDTO().isWorkFlow()
                && ARMUtils.EDIT.equalsIgnoreCase(pipelineSummarySelection.getSessionDTO().getAction()) || (!pipelineSummarySelection.getSessionDTO().isWorkFlow()))
                && ARMConstants.getDeductionCustomerContract().equals(pipelineViewType)
                && pipelineSummarySelection.getSummarydeductionLevelDes().equals(ARMConstants.getDeduction())
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
        return true;
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
    public boolean equals(Object pipoeSummary) {
        return super.equals(pipoeSummary);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private void writeObject(ObjectOutputStream pipoeSummary) throws IOException {
        pipoeSummary.defaultWriteObject();
    }

    private void readObject(ObjectInputStream pipoeSummary) throws IOException, ClassNotFoundException {
        pipoeSummary.defaultReadObject();
    }
}
