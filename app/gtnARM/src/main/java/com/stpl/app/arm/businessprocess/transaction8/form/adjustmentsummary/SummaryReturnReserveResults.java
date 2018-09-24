/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction8.form.adjustmentsummary;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractPipelineSummaryResults;
import com.stpl.app.arm.businessprocess.pipelineaccrual.form.tablegenerator.SummaryFieldFactory;
import com.stpl.app.arm.businessprocess.transaction8.dto.RRSelectionDTO;
import com.stpl.app.arm.businessprocess.transaction8.logic.RRSummaryLogic;
import com.stpl.app.arm.supercode.ExcelInterface;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMConstants;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.asi.ui.extfilteringtable.ExtCustomTable;

/**
 *
 * @author
 */
public class SummaryReturnReserveResults extends AbstractPipelineSummaryResults {

    public SummaryReturnReserveResults(RRSummaryLogic logic, RRSelectionDTO selection) {
        super(logic, selection);
    }

    @Override
    protected String[] getExcelVariableVisibleColumns() {
        return ARMUtils.getReturnReserveSummaryDeductionColumns();
    }

    @Override
    public boolean getisFixedColumns() {
        return true;
    }

    @Override
    public int getInterval() {
        return 6;
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
    public ExcelInterface getExcelLogic() {
        return getSummaryLogic();
    }

    @Override
    public Object[] getExcelHierarchy() {
        Object[] value = null;
        if (customerProductView.getValue().equals(ARMConstants.getDeductionCustomer())) {
            value = ARMConstants.getDeduction().equals(selection.getSummarydeductionLevelDes()) ? ARMUtils.getDCTBI() : ARMUtils.getCTBI();
        } else if (customerProductView.getValue().equals(ARMConstants.getDeductionContract())) {
            value = ARMConstants.getDeduction().equals(selection.getSummarydeductionLevelDes()) ? ARMUtils.getDTCBI() : ARMUtils.getTCBI();
        } else if (customerProductView.getValue().equals(ARMConstants.getDeductionProduct())) {
            value = ARMConstants.getDeduction().equals(selection.getSummarydeductionLevelDes()) ? new Object[]{"D", "B", "I"} : ARMUtils.getBI();
        }
        return value;
    }

    @Override
    public List getExcelExportVisibleColumn() {
        return getSelection().getExcelVisibleColumn();
    }

    @Override
    public String getExcelFileName() {
        return "Adjustment_Summary";
    }

    @Override
    protected void configureLevelDDLBs() {
        customerProductView.addItems(new Object[]{ARMConstants.getDeductionProduct(),
            ARMConstants.getDeductionCustomer(), ARMConstants.getDeductionContract()});
        customerProductView.setValue(ARMConstants.getDeductionProduct());
        getSelection().setSummaryviewType(ARMConstants.getDeductionProduct());
    }

    @Override
    public void setRespectiveHierarchy(String viewType) {
        if (ARMConstants.getDeduction().equals(selection.getSummarydeductionLevelDes())) {
            getSelection().setSummeryhierarchy(ARMUtils.getInstance().getTYrx8LevelAndLevelFilter(viewType));
        } else {
            getSelection().setSummeryhierarchy(ARMUtils.getInstance().getReturnsLevelAndLevelFilter(viewType));
        }
    }

    @Override
    public void generateButtonLogic(String[] columns) {
        customerProductView.setValue(ARMConstants.getDeductionProduct());
        setValueChangeAllowed(false);
        isGenarate = true;
        Map properties = new HashMap();
        String view = String.valueOf(customerProductView.getValue());
        if (ARMConstants.getDeductionProduct().equalsIgnoreCase(view)) {
            pipelineSummaryLeftHeader = CommonConstant.PRODUCT;
        } else {
            pipelineSummaryLeftHeader = "Customer";
        }

        selection.setSummaryviewType(view);
        List<Object> header = getSummaryLogic().generateHeader(getSelection(), columns);
        List rightSingleVisibleColumn = (List) header.get(0);
        List<String> rightDoubleVisibleColumn = (List) header.get(1);
        Map<Object, Object[]> rightDoubleSingleVisibleColumn = (Map<Object, Object[]>) header.get(NumericConstants.FOUR);
        for (int i = 0; i < rightSingleVisibleColumn.size(); i++) {
            properties.put(rightSingleVisibleColumn.get(i), String.class);
        }
        for (int i = 0; i < pipelineSummaryRightcolumns.length; i++) {
            properties.put(pipelineSummaryRightcolumns[i], String.class);
        }
        table.constructRightFreeze(true);
        leftTable = table.getLeftFreezeAsTable();
        leftTable.setColumnHeaders(pipelineSummaryLeftHeader);
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
        if (selection.getSummarydeductionLevelDes().equals(ARMConstants.getDeduction())
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
        setRespectiveHierarchy(view);
        configureLevelAndLevelFilter();
        tableLogic.loadSetData(false);
        setValueChangeAllowed(true);

    }

    @Override
    protected void configureRightTable() {
        SummaryFieldFactory fieldFactory = new SummaryFieldFactory(getSummaryLogic(), getSelection(), ARMConstants.getDeductionContract().equals(String.valueOf(customerProductView.getValue())) && ARMConstants.getDeduction().equals(selection.getSummarydeductionLevelDes()));
        rightTable.setEditable(true);
        rightTable.setTableFieldFactory(fieldFactory);
        setConverter(rightTable, rightTable.getVisibleColumns());
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
        LOGGER.debug("selectedView----{}", viewType);
        leftTable.setColumnHeaders(ARMConstants.getDeductionProduct().equalsIgnoreCase(viewType) ? ARMUtils.PRODUCT
                : ARMUtils.CUSTOMER_SMALL);
        getSelection().setSummaryviewType(viewType);
        setRespectiveHierarchy(viewType);
        configureLevelAndLevelFilter();
        configureRightTable();
        if (isGenarate) {
            getTableLogic().loadSetData(false);
        }
        List<String> headerList = Arrays.asList(rightTable.getColumnHeaders());
        if ((selection.getSessionDTO().isWorkFlow()
                && ARMUtils.EDIT.equalsIgnoreCase(selection.getSessionDTO().getAction()) || (!selection.getSessionDTO().isWorkFlow()))
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
    protected boolean isPercentageColumn2Decimal(String column) {
        return column.contains("Ratio") || column.contains("ratio");
    }

    @Override
    protected boolean isPercentageColumn3Decimal(String column) {
        return false;
    }
}
