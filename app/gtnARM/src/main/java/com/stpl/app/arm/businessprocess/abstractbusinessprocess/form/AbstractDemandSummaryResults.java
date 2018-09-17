/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.abstractbusinessprocess.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractDemandSummaryLogic;
import com.stpl.app.arm.businessprocess.pipelineaccrual.form.tablegenerator.DemandSummaryFieldFactory;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.ifs.util.constants.GlobalConstants;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import com.vaadin.v7.ui.Label;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author Nimisha.Rakesh
 */
public abstract class AbstractDemandSummaryResults extends AbstractSummarySearchResults {

    @UiField("valueLabel")
    protected Label valueLabel;

    private Object[] demandSummaryColumns = {VariableConstants.MONTH};
    private Object[] demandSummaryRightcolumns = {VariableConstants.MONTH};

    private Map<Object, Object[]> demandSummaryLeftDoubleSingleVisibleColumn = new HashMap<>();
    private String demandSummaryLeftHeader = VariableConstants.PRODUCT;
    private String[] tableColumns;
    private Set previousValue = new HashSet<>();
    protected boolean isGenarate = false;
    private static final Logger LOGGER_ABS_DEM_SUMM_RES = LoggerFactory.getLogger(AbstractDemandSummaryResults.class);

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
        leftTable.setVisibleColumns(demandSummaryRightcolumns);
        leftTable.setColumnHeaders(demandSummaryLeftHeader);
        leftTable.setDoubleHeaderVisibleColumns(demandSummaryColumns);
        leftTable.setDoubleHeaderColumnHeaders("");
        rightTable.setVisibleColumns(demandSummaryRightcolumns);
        rightTable.setColumnHeaders("");
        rightTable.setDoubleHeaderVisibleColumns(demandSummaryColumns);
        rightTable.setDoubleHeaderColumnHeaders("");
        demandSummaryLeftDoubleSingleVisibleColumn.put(VariableConstants.MONTH, demandSummaryRightcolumns);
        leftTable.setDoubleHeaderMap(demandSummaryLeftDoubleSingleVisibleColumn);
        rightTable.setDoubleHeaderMap(demandSummaryLeftDoubleSingleVisibleColumn);
        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);

        }
        for (Object propertyId : rightTable.getDoubleHeaderVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.CENTER);

        }
        rightTable.setDoubleHeaderMap(configureDemandRightDoubleHeaderMap());
        leftTable.setDoubleHeaderMap(configureDemandLeftDoubleHeaderMap());
        abstractSearchContent.setWidth("100%");
    }

    private Map<Object, Object[]> configureDemandRightDoubleHeaderMap() {
        Map<Object, Object[]> demadnHeaderMap = new HashMap<>();
        demadnHeaderMap.put(VariableConstants.MONTH, new String[]{VariableConstants.MONTH});
        return demadnHeaderMap;
    }

    private Map<Object, Object[]> configureDemandLeftDoubleHeaderMap() {
        Map<Object, Object[]> demandHeaderMap = new HashMap<>();
        demandHeaderMap.put(VariableConstants.MONTH, new String[]{VariableConstants.MONTH});
        return demandHeaderMap;
    }

    private void configureOnAdjustmentSummarySearchResultsDemand() {
        panelCaption.setCaption("Results");
        calculateBtn.setEnabled(false);
        cancelOverride.setEnabled(false);
        valueLabel.setCaption("Value");
        bbExport.setPrimaryStyleName("link");
        bbExport.setIcon(ARMUtils.EXCEL_EXPORT_IMAGE, "Excel Export");
    }

    @Override
    public void setExcelVisibleColumn() {
        Map properties = new HashMap();
        boolean isFrequencyMultiple = ARMConstants.getMultiplePeriod().equals(getSelection().getSummarydemandview());
        List<Object> header = getSummaryLogic().generateHeader(getSelection(), tableColumns, isFrequencyMultiple);
        List rightSingleVisibleColumn = (ArrayList) header.get(NumericConstants.FIVE);
        List rightSingleVisibleHeader = (ArrayList) header.get(NumericConstants.SIX);
        List<String> rightDoubleVisibleColumn = (ArrayList) header.get(1);
        List<String> rightDoubleVisibleHeader = (ArrayList) header.get(NumericConstants.THREE);
        for (Object variableColumn : rightSingleVisibleColumn) {
            properties.put(variableColumn, String.class);
        }
        getExcelContainer().setColumnProperties(properties);
        getExcelContainer().setRecordHeader(rightSingleVisibleColumn);
        List rightSingleVisibleColumn1 = new ArrayList(rightSingleVisibleColumn);
        rightSingleVisibleColumn1.add(0, VariableConstants.MONTH);
        rightSingleVisibleHeader.add(0, VariableConstants.PRODUCT);
        rightDoubleVisibleColumn.add(0, VariableConstants.MONTH);
        rightDoubleVisibleHeader.add(0, "");
        getExcelTable().setVisibleColumns(rightSingleVisibleColumn1.toArray());
        getExcelTable().setColumnHeaders(Arrays.copyOf((rightSingleVisibleHeader).toArray(), (rightSingleVisibleHeader).size(), String[].class));
        getExcelTable().setDoubleHeaderVisible(true);
        getExcelTable().setDoubleHeaderVisibleColumns(rightDoubleVisibleColumn.toArray());
        getExcelTable().setDoubleHeaderColumnHeaders(Arrays.copyOf(rightDoubleVisibleHeader.toArray(), rightDoubleVisibleHeader.size(), String[].class));
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
            String dynaTableName = selection.getSessionDTO().getCurrentTableNames().get(tableName);
            DemandSummaryFieldFactory fieldFactory = new DemandSummaryFieldFactory(getSummaryLogic(), getSelection(), ARMConstants.getDeductionCustomerContract().equals(getSelection().getSummaryviewType()) && getSelection().getSummarydeductionLevelDes().equals(ARMConstants.getDeduction()), dynaTableName);
            rightTable.setEditable(true);
            rightTable.setTableFieldFactory(fieldFactory);
        }
    }

    @Override
    protected boolean calculateLogic() {
        LOGGER_ABS_DEM_SUMM_RES.debug("CalculateBtn value change listener starts");
        boolean calculateFlag = super.calculateLogic();
        if (!calculateFlag) {
            AbstractNotificationUtils.getErrorNotification(ARMMessages.getGenerateErrorHeaderMessage(), ARMMessages.getGenerateMessage_Demand());
        }
        return calculateFlag;
    }

    /**
     * load and set the default selection for level DDLBs
     */
    private static final Object[] itemsForMultiple = {ARMConstants.getDeductionProduct(),
        ARMConstants.getDeductionCustomer(), ARMConstants.getDeductionCustomerContract(), ARMConstants.getCustomerDedection()};
    private static final Object[] itemsForSingle = {ARMConstants.getDeductionProduct(),
        ARMConstants.getDeductionCustomer(), ARMConstants.getDeductionCustomerContract()};

    public void configureLevelDDLBs() {
        setLevelFilterEnable(false);
        isGenarate = false;
        if (ARMConstants.getMultiplePeriod().equals(getSelection().getSummarydemandview())) {
            customerProductView.removeAllItems();
            customerProductView.addItems(itemsForMultiple);
        } else {
            customerProductView.removeAllItems();
            customerProductView.addItems(itemsForSingle);
        }
        customerProductView.setValue(ARMConstants.getDeductionProduct());
        customerProductView.select(ARMConstants.getDeductionProduct());
        setLevelFilterEnable(true);
    }

    public void generateButtonLogic(String[] columns) {
        isGenarate = false;
        tableColumns = CommonLogic.getInstance().getStringArrayCloned(columns);
        Map properties = new HashMap();
        String view = String.valueOf(customerProductView.getValue());
        if ("Deduction Product".equals(view)) {
            demandSummaryLeftHeader = VariableConstants.PRODUCT;
        } else {
            demandSummaryLeftHeader = VariableConstants.CUSTOMER;
        }

        getSelection().setSummaryviewType(view);
        getSelection().setSummarydemandLevel(ARMUtils.getADJSummaryLevel(view));
        boolean isFrequencyMultiple = ARMConstants.getMultiplePeriod().equals(getSelection().getSummarydemandview());
        List<Object> header = getSummaryLogic().generateHeader(getSelection(), columns, isFrequencyMultiple);
        List rightSingleVisibleColumn = (ArrayList) header.get(0);
        List<String> rightDoubleVisibleColumn = (ArrayList) header.get(1);
        Map<Object, Object[]> rightDoubleSingleVisibleColumn = (HashMap) header.get(NumericConstants.FOUR);
        for (int i = 0; i < rightSingleVisibleColumn.size(); i++) {
            properties.put(rightSingleVisibleColumn.get(i), String.class);
        }
        for (int i = 0; i < demandSummaryRightcolumns.length; i++) {
            properties.put(demandSummaryRightcolumns[i], String.class);
        }
        getTable().constructRightFreeze(true);
        rightTable = getTable().getRightFreezeAsTable();
        leftTable.setColumnHeaders(demandSummaryLeftHeader);
        rightTable.setContainerDataSource(getTableLogic().getContainerDataSource());
        resultBeanContainer.setRecordHeader(rightSingleVisibleColumn);
        resultBeanContainer.setColumnProperties(properties);
        resultBeanContainer.setIndexable(true);
        rightTable.setDoubleHeaderVisible(true);
        rightTable.setVisibleColumns(rightSingleVisibleColumn.toArray());
        rightTable.setColumnHeaders(Arrays.copyOf(((List) header.get(NumericConstants.TWO)).toArray(), ((List) header.get(NumericConstants.TWO)).size(), String[].class));
        rightTable.setDoubleHeaderVisibleColumns(rightDoubleVisibleColumn.toArray());
        rightTable.setDoubleHeaderColumnHeaders(Arrays.copyOf(((List) header.get(NumericConstants.THREE)).toArray(), ((List) header.get(NumericConstants.THREE)).size(), String[].class));
        rightTable.setDoubleHeaderMap(rightDoubleSingleVisibleColumn);
        List<String> headerList = Arrays.asList(rightTable.getColumnHeaders());
        String frequency = getSelection().getSummarydemandfrequency();
        if (ARMConstants.getDeductionCustomerContract().equals(view) && getSelection().getSummarydeductionLevelDes().equals(ARMConstants.getDeduction())
                && frequency.equals(VariableConstants.MONTHLY) && headerList.contains(VariableConstants.OVERRIDE_HEADER)) {
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
            QueryUtil.callProcedure(getProcedureNameAndInputs(getSelection(), inputs), inputs.toArray());
        }
        configureRightTable();
        setConverter(rightTable, rightTable.getVisibleColumns());
        getTableLogic().loadSetData(false);

    }

    @Override
    protected boolean isPercentageColumn2Decimal(String column) {
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
        LOGGER_ABS_DEM_SUMM_RES.debug("customerProductView value change listener starts");
        String viewType = String.valueOf(customerProductView.getValue());
        if (!"null".equals(viewType) && getSelection().getSummarydeductionLevelDes() != null) {
            getSelection().setSummaryviewType(viewType);
            String frequency = getSelection().getSummarydemandfrequency();
            List<String> headerList = Arrays.asList(rightTable.getColumnHeaders());
            if ((selection.getSessionDTO().isWorkFlow() && ARMUtils.EDIT.equalsIgnoreCase(selection.getSessionDTO().getAction())
                    || (!selection.getSessionDTO().isWorkFlow()))
                    && ARMConstants.getDeductionCustomerContract().equals(viewType)
                    && getSelection().getSummarydeductionLevelDes().equals(ARMConstants.getDeduction())
                    && frequency.equals(VariableConstants.MONTHLY) && headerList.contains(VariableConstants.OVERRIDE_HEADER)) {
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
                getSelection().setSummarydemandLevel(ARMUtils.getADJSummaryLevel(viewType));
                getTableLogic().loadSetData(false);
                demandSummaryLeftHeader = viewType.equals(ARMConstants.getDeductionProduct()) ? VariableConstants.PRODUCT : VariableConstants.CUSTOMER;
                leftTable.setColumnHeaders(demandSummaryLeftHeader);
            }
        }
        setLevelFilterEnable(true);
    }

    @Override
    protected boolean setRespectiveLevelFileterValue(String levelValue, int levelNo) {
        getSelection().setSummarylevelFilterNo(levelNo);
        getSelection().setSummarylevelFilterValue(levelValue);
        getSelection().setSummaryvalueSid(0);
        return true;
    }

    @Override
    public AbstractDemandSummaryLogic getSummaryLogic() {
        return (AbstractDemandSummaryLogic) super.getSummaryLogic();
    }

    @Override
    public Map<Integer, String> getHierarchy() {
        return selection.getSummeryhierarchy();
    }

    @Override
    public void setRespectiveHierarchy(String viewType) {
        if (ARMConstants.getMultiplePeriod().equals(selection.getSummarydemandview())) {
            selection.setSummeryhierarchy(ARMUtils.getLevelAndLevelFilterMultiPeriod(viewType));
            selection.setIsMultiple(true);
        } else {
            selection.setSummeryhierarchy(ARMUtils.getLevelAndLevelFilter(viewType));
            if (ARMConstants.getDeductionCustomerContract().equals(getSelection().getSummaryviewType())) {
                selection.setIsMultiple(true);
            } else {
                selection.setIsMultiple(false);
            }
        }
    }

    @Override
    public List getExcelExportVisibleColumn() {
        LOGGER_ABS_DEM_SUMM_RES.debug("inside getExcelExportVisibleColumn");
        return getSelection().getExcelVisibleColumn();
    }

    @Override
    public String getExcelFileName() {
        LOGGER_ABS_DEM_SUMM_RES.debug("inside getExcelFileName");
        return "Adjustment_Summary";
    }

    @Override
    public boolean getisFixedColumns() {
        LOGGER_ABS_DEM_SUMM_RES.debug("inside getisFixedColumns");
        return true;
    }

    @Override
    public int discountColumnNeeded() {
        LOGGER_ABS_DEM_SUMM_RES.debug("inside discountColumnNeeded");
        return 1;
    }

    @Override
    public Object[] getExcelHierarchy() {
        Map<Integer, String> hierarchy = getHierarchy();
        Object[] value = new Object[hierarchy.size()];
        for (int i = 0; i < hierarchy.size(); i++) {
            String val = hierarchy.get(i + 1);
            if (val.equalsIgnoreCase(ARMUtils.levelVariablesVarables.DEDUCTION.toString())) {
                val = getSelection().getSummarydeductionLevelDes().toUpperCase(Locale.ENGLISH);
            }
            value[i] = ARMUtils.getLevelExcelQueryName(val);
        }
        getSelection().setExcelHierarchy(value);
        return value;
    }

    @Override
    public boolean getisDeductionCustomer() {
        LOGGER_ABS_DEM_SUMM_RES.debug("inside getisDeductionCustomer");
        return false;
    }

    @Override
    protected void loadLevelFilterValueDdlb(String levelValue, int levelNo) {
        LOGGER_ABS_DEM_SUMM_RES.debug(" loadLevelFilterValueDdlb levelValue {}", levelValue);
        getLevelFilterValueDdlb().removeAllItems();
        getLevelFilterValueDdlb().addItem(0);
        getLevelFilterValueDdlb().setItemCaption(0, GlobalConstants.getSelectOne());
        if (!levelValue.equals(GlobalConstants.getSelectOne())) {
            Map<Integer, String> levelVal = getSummaryLogic().getLevelFilterValueData(levelValue, selection.getProjectionMasterSid(), selection.getSummarydeductionLevel(), selection.getSummarydeductionValues());
            for (Map.Entry<Integer, String> entry : levelVal.entrySet()) {
                getLevelFilterValueDdlb().addItem(entry.getKey());
                getLevelFilterValueDdlb().setItemCaption(entry.getKey(), entry.getValue());
            }
        }
        getLevelFilterValueDdlb().setValue(0);

    }

    @Override
    protected void valueDdlbValueChange(int masterSid) {
        LOGGER_ABS_DEM_SUMM_RES.debug("valueDdlbValueChange masterSid {}", masterSid);
        if (isLevelFilterValueDdlbEnable()) {
            getSelection().setSummaryvalueSid(masterSid);
            tableLogic.loadSetData(false);
        }
    }

    @Override
    protected boolean getIsDemandSreen() {
        return true;
    }

    protected abstract String getTableNameForEdit();

    @Override
    public boolean equals(Object demSummout) {
        return super.equals(demSummout);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private void writeObject(ObjectOutputStream demSummout) throws IOException {
        demSummout.defaultWriteObject();
    }

    private void readObject(ObjectInputStream demSummout) throws IOException, ClassNotFoundException {
        demSummout.defaultReadObject();
    }
}
