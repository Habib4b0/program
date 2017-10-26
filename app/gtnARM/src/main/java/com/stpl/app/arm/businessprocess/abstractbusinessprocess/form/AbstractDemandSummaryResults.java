/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.abstractbusinessprocess.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractDemandSummaryLogic;
import com.stpl.app.arm.businessprocess.pipelineaccrual.form.tablegenerator.DemandSummaryFieldFactory;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Label;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author Nimisha.Rakesh
 */
public abstract class AbstractDemandSummaryResults extends AbstractSummarySearchResults {

    @UiField("valueLabel")
    protected Label valueLabel;

    Object[] columns = {VariableConstants.MONTH};
    Object[] rightcolumns = {VariableConstants.MONTH};
    Map<Object, Object[]> left_double_single_visibleColumn = new HashMap<>();
    List DeductionList;
    String leftHeader = VariableConstants.PRODUCT;
    String[] tableColumns;
    Set previousValue = new HashSet<>();
    protected boolean isGenarate = Boolean.FALSE;
    private final Logger LOGGER = Logger.getLogger(getClass());
    public AbstractDemandSummaryResults(AbstractDemandSummaryLogic logic, AbstractSelectionDTO selection) {
        super(logic, selection);
    }

    @Override
    public void setVisibleColumnsAndHeaders() {
        configureLevelDDLBs();
        configureOnAdjustmentSummarySearchResultsDemand();
        getTableLogic().setContainerDataSource(resultBeanContainer);
        leftTable = getTable().getLeftFreezeAsTable();
        rightTable = getTable().getRightFreezeAsTable();
        rightTable.setImmediate(true);
        leftTable.setImmediate(true);
        getTable().setDoubleHeaderVisible(true);
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
        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);

        }
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

    private void configureOnAdjustmentSummarySearchResultsDemand() {
        panelCaption.setCaption("Results");
        calculateBtn.setEnabled(false);
         cancelOverride.setEnabled(false);
        valueLabel.setCaption("Value");
        BBExport.setPrimaryStyleName("link");
        BBExport.setIcon(ARMUtils.EXCEL_EXPORT_IMAGE, "Excel Export");
    }

    @Override
    public void setExcelVisibleColumn() {
        Map properties = new HashMap();
        boolean isFrequencyMultiple = ARMConstants.getMultiplePeriod().equals(getSelection().getSummary_demand_view());
        List<Object> header = getLogic().generateHeader(getSelection(), tableColumns, isFrequencyMultiple);
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
        right_singleVisibleHeader.add(0, VariableConstants.PRODUCT);
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

    @Override
    protected void configureRightTable() {
        boolean isView = ARMUtils.VIEW_SMALL.equals(selection.getSessionDTO().getAction());
        if (isView) {
            rightTable.setEditable(false);
        } else {
            String tableName = getTableNameForEdit();
            String dynaTableName  = selection.getSessionDTO().getCurrentTableNames().get(tableName);
            DemandSummaryFieldFactory fieldFactory = new DemandSummaryFieldFactory(getLogic(), getSelection(), ARMConstants.getDeductionCustomerContract().equals(getSelection().getSummary_viewType()) && getSelection().getSummary_deductionLevelDes().equals(ARMConstants.getDeduction()),dynaTableName);
            rightTable.setEditable(true);
            rightTable.setTableFieldFactory(fieldFactory);
        }
    }

    @Override
    protected boolean calculateLogic() {
        LOGGER.debug("CalculateBtn value change listener starts");
        boolean calculateFlag = super.calculateLogic();        
        if (!calculateFlag) {
            AbstractNotificationUtils.getErrorNotification(ARMMessages.getGenerateErrorHeaderMessage(), ARMMessages.getGenerateMessage_Demand());
        }
        return calculateFlag;
    }

    /**
     * load and set the default selection for level DDLBs
     */
    public void configureLevelDDLBs() {
        setLevelFilterEnable(false);
        isGenarate = Boolean.FALSE;
        if (ARMConstants.getMultiplePeriod().equals(getSelection().getSummary_demand_view())) {
            customerProductView.removeAllItems();
            customerProductView.addItems(new Object[]{ARMConstants.getDeductionProduct(),
                ARMConstants.getDeductionCustomer(), ARMConstants.getDeductionCustomerContract(), ARMConstants.getCustomerDedection()});
        } else {
            customerProductView.removeAllItems();
            customerProductView.addItems(new Object[]{ARMConstants.getDeductionProduct(),
                ARMConstants.getDeductionCustomer(), ARMConstants.getDeductionCustomerContract()});
        }
        customerProductView.setValue(ARMConstants.getDeductionProduct());
        customerProductView.select(ARMConstants.getDeductionProduct());
        setLevelFilterEnable(true);
    }

    public void generateButtonLogic(String[] columns) {
        isGenarate = Boolean.TRUE;
        tableColumns = columns;
        Map properties = new HashMap();
        String view = String.valueOf(customerProductView.getValue());
        if ("Deduction Product".equals(view)) {
            leftHeader = VariableConstants.PRODUCT;
        } else {
            leftHeader = VariableConstants.CUSTOMER;
        }
        
        getSelection().setSummary_viewType(view);
        getSelection().setSummary_demand_Level(ARMUtils.getADJ_SummaryLevel(view));
        boolean isFrequencyMultiple = ARMConstants.getMultiplePeriod().equals(getSelection().getSummary_demand_view());
        List<Object> header = getLogic().generateHeader(getSelection(), columns, isFrequencyMultiple);
        List right_singleVisibleColumn = (ArrayList) header.get(0);
        List<String> right_doubleVisibleColumn = (ArrayList) header.get(1);
        Map<Object, Object[]> right_double_single_visibleColumn = (HashMap) header.get(NumericConstants.FOUR);
        for (int i = 0; i < right_singleVisibleColumn.size(); i++) {
            properties.put(right_singleVisibleColumn.get(i), String.class);
        }
        for (int i = 0; i < rightcolumns.length; i++) {
            properties.put(rightcolumns[i], String.class);
        }
        getTable().constructRightFreeze(true);
        rightTable = getTable().getRightFreezeAsTable();
        leftTable.setColumnHeaders(leftHeader);
        rightTable.setContainerDataSource(getTableLogic().getContainerDataSource());
        resultBeanContainer.setRecordHeader(right_singleVisibleColumn);
        resultBeanContainer.setColumnProperties(properties);
        rightTable.setDoubleHeaderVisible(true);
        rightTable.setVisibleColumns(right_singleVisibleColumn.toArray());
        rightTable.setColumnHeaders(Arrays.copyOf(((List) header.get(NumericConstants.TWO)).toArray(), ((List) header.get(NumericConstants.TWO)).size(), String[].class));
        rightTable.setDoubleHeaderVisibleColumns(right_doubleVisibleColumn.toArray());
        rightTable.setDoubleHeaderColumnHeaders(Arrays.copyOf(((List) header.get(NumericConstants.THREE)).toArray(), ((List) header.get(NumericConstants.THREE)).size(), String[].class));
        rightTable.setDoubleHeaderMap(right_double_single_visibleColumn);
        List<String> headerList = Arrays.asList(rightTable.getColumnHeaders());
        String frequency = getSelection().getSummary_demand_frequency();
        if (ARMConstants.getDeductionCustomerContract().equals(view) && getSelection().getSummary_deductionLevelDes().equals(ARMConstants.getDeduction())
                && frequency.equals(VariableConstants.MONTHLY) && headerList.contains(VariableConstants.OVERRIDE)) {
            calculateBtn.setEnabled(true);
            cancelOverride.setEnabled(true);
        } else {
            calculateBtn.setEnabled(false);
            cancelOverride.setEnabled(false);
        }
        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);

        }
        for (Object propertyId : rightTable.getDoubleHeaderVisibleColumns()) {
            rightTable.setDoubleHeaderColumnAlignment(propertyId, ExtCustomTable.Align.CENTER);
        }
        boolean isView = selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        if (previousValue.add(getSelection().getProjectionMasterSid()) && !isView) {
            List inputs = new ArrayList();
            CommonUtils.callInsertProcedure(getProcedureNameAndInputs(getSelection(), inputs), inputs.toArray());
        }
        configureRightTable();
        setConverter(rightTable, rightTable.getVisibleColumns());
        getTableLogic().loadSetData(Boolean.FALSE);
        
    }

    @Override
    protected boolean isPercentageColumn_2_Decimal(String column) {
        return column.contains("demandAccrualRatio") || column.contains("paymentRatio"); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method returns the name of the procedure and updates the inputs in
     * input list as parameter
     *
     * @param selection
     * @param inputs
     * @return
     */
    protected abstract String getProcedureNameAndInputs(AbstractSelectionDTO selection, List inputs);

    /**
     * customerProductView ddlb.
     *
     * @param event the event
     */
    @Override
    protected void customerProductValueChange() {
        setLevelFilterEnable(false);
        LOGGER.debug("customerProductView value change listener starts");
        String viewType = String.valueOf(customerProductView.getValue());
        if (!viewType.equals("null") && getSelection().getSummary_deductionLevelDes() != null) {
            getSelection().setSummary_viewType(viewType);
            String frequency = getSelection().getSummary_demand_frequency();
            List<String> headerList = Arrays.asList(rightTable.getColumnHeaders());
            if (selection.getSessionDTO().isWorkFlow()
                && ARMUtils.EDIT.equalsIgnoreCase(selection.getSessionDTO().getAction()) 
                && ARMConstants.getDeductionCustomerContract().equals(viewType) 
                && getSelection().getSummary_deductionLevelDes().equals(ARMConstants.getDeduction())
                && frequency.equals(VariableConstants.MONTHLY) && headerList.contains(VariableConstants.OVERRIDE)) {
                calculateBtn.setEnabled(true);
                cancelOverride.setEnabled(true);
            } else if (!selection.getSessionDTO().isWorkFlow()
                && ARMConstants.getDeductionCustomerContract().equals(viewType) 
                && getSelection().getSummary_deductionLevelDes().equals(ARMConstants.getDeduction())
                && frequency.equals(VariableConstants.MONTHLY) && headerList.contains(VariableConstants.OVERRIDE)) {
                calculateBtn.setEnabled(true);
                cancelOverride.setEnabled(true);
            } else {
                calculateBtn.setEnabled(false);
                cancelOverride.setEnabled(false);
            }             
            setRespectiveHierarchy(viewType);
            configureLevelAndLevelFilter();
            if (isGenarate) {
                configureRightTable();
                getSelection().setSummary_demand_Level(ARMUtils.getADJ_SummaryLevel(viewType));
                getTableLogic().loadSetData(Boolean.FALSE);
                leftHeader = viewType.equals(ARMConstants.getDeductionProduct()) ? VariableConstants.PRODUCT : VariableConstants.CUSTOMER;
                leftTable.setColumnHeaders(leftHeader);
            }
        }
        setLevelFilterEnable(true);
    }

    @Override
    protected boolean setRespectiveLevelFileterValue(String levelValue, int levelNo) {
        getSelection().setSummary_levelFilterNo(levelNo);
        getSelection().setSummary_levelFilterValue(levelValue);
        getSelection().setSummary_valueSid(0);
        return true;
    }

    @Override
    public AbstractDemandSummaryLogic getLogic() {
        return (AbstractDemandSummaryLogic) super.getLogic();
    }

    @Override
    public Map<Integer, String> getHierarchy() {
        return selection.getSummery_hierarchy();
    }

    @Override
    public void setRespectiveHierarchy(String viewType) {
        if (ARMConstants.getMultiplePeriod().equals(selection.getSummary_demand_view())) {
            selection.setSummery_hierarchy(ARMUtils.getLevelAndLevelFilterMultiPeriod(viewType));
            selection.setIsMultiple(Boolean.TRUE);
        } else {
            selection.setSummery_hierarchy(ARMUtils.getLevelAndLevelFilter(viewType));
            if (ARMConstants.getDeductionCustomerContract().equals(getSelection().getSummary_viewType())) {
                selection.setIsMultiple(Boolean.TRUE);
            } else {
                selection.setIsMultiple(Boolean.FALSE);
            }
        }
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
    public boolean getisFixedColumns() {
        return Boolean.TRUE;
    }

    @Override
    public int discountColumnNeeded() {
        return 1;
    }

    @Override
    public Object[] getExcelHierarchy() {
        Map<Integer, String> hierarchy = getHierarchy();
        Object[] value = new Object[hierarchy.size()];
        for (int i = 0; i < hierarchy.size(); i++) {
            String val = hierarchy.get(i + 1);
            if (val.equalsIgnoreCase(ARMUtils.levelVariablesVarables.DEDUCTION.toString())) {
                val = getSelection().getSummary_deductionLevelDes().toUpperCase();
            }
            value[i] = ARMUtils.getLevelExcelQueryName(val);
        }
        getSelection().setExcelHierarchy(value);
        return value;
    }

    @Override
    public boolean getisDeductionCustomer() {
        boolean isDeductionCustomer = Boolean.FALSE;
        if (ARMConstants.getSinglePeriod().equals(getSelection().getSummary_demand_view())
                && ARMConstants.getDeductionCustomerContract().equals(getSelection().getSummary_viewType())) {
            isDeductionCustomer = Boolean.TRUE;
        } else {
            isDeductionCustomer = Boolean.FALSE;
        }
        return isDeductionCustomer;
    }

    @Override
    protected void loadLevelFilterValueDdlb(String levelValue, int levelNo) {
        LOGGER.debug(" loadLevelFilterValueDdlb levelValue " + levelValue + " levelNo " + levelNo);
        getLevelFilterValueDdlb().removeAllItems();
        getLevelFilterValueDdlb().addItem(0);
        getLevelFilterValueDdlb().setItemCaption(0, GlobalConstants.getSelectOne());
        if (!levelValue.equals(GlobalConstants.getSelectOne())) {
            Map<Integer, String> levelVal = getLogic().getLevelFilterValueData(levelValue, selection.getProjectionMasterSid(), selection.getSummary_deductionLevel(), selection.getSummary_deductionValues());
            for (Map.Entry<Integer, String> entry : levelVal.entrySet()) {
                getLevelFilterValueDdlb().addItem(entry.getKey());
                getLevelFilterValueDdlb().setItemCaption(entry.getKey(), entry.getValue());
            }
        }
        getLevelFilterValueDdlb().setValue(0);

    }

    @Override
    protected void valueDdlbValueChange(int masterSid) {
        LOGGER.debug("valueDdlbValueChange masterSid " + masterSid);
        if (isLevelFilterValueDdlbEnable()) {
            getSelection().setSummary_valueSid(masterSid);
            tableLogic.loadSetData(Boolean.FALSE);
        }
    }

    @Override
    protected boolean getIsDemandSreen() {
        return Boolean.TRUE;
    }
    
    protected abstract String getTableNameForEdit();
}
