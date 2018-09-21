package com.stpl.app.arm.adjustmentsummary.form;

import com.stpl.app.arm.adjustmentsummary.logic.SummaryLogic;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractSummarySearchResults;
import com.stpl.app.arm.businessprocess.commontemplates.SummarySelection;
import com.stpl.app.arm.supercode.ExcelInterface;
import com.stpl.app.utils.CommonUtils;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.ui.GridLayout;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.utils.ExcelUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.Property;
import com.stpl.ifs.util.constants.ARMConstants;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.Label;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Asha
 */
public final class AdjustmentSummaryResults extends AbstractSummarySearchResults implements Serializable {

    private String[] summaryRighttablesingleheaders = {"Demand Accrual", "Projected Demand Accrual", "Variance"};
    private Object[] summaryDoubleheadercolumns = {"rate"};

    private Object[] summarySingleheader = {"dateType", "price", "exclusionDetails"};

    private String[] summaryDoubleheader = {"Managed Care Base"};
    @UiField("BB-calculate")
    private Button bbCalculate;
    @UiField("valueLabel")
    private Label valueLabel;
    @UiField("BB-export")
    private Button bbExportButton;
    @UiField("BB-searchResultsGrid")
    private GridLayout searchResultsGrids;
    @UiField("cancelOverride")
    private Button cancelOverrideButton;
    private SummaryLogic summaryLogic;
    private SummarySelection selectionAdj;
    private final Label labelStatus = new Label("Status");

    protected final CustomMenuBar statusDdlb = new CustomMenuBar();

    protected CustomMenuBar.CustomMenuItem statusMenuItem;

    private boolean isValueChange;
    private String leftHeader = CommonConstant.CUSTOMER;
    /**
     * The Constant LOGGERSUMMARYRESULT.
     */
    private static final Logger LOGGERSUMMARYRESULT = LoggerFactory.getLogger(AdjustmentSummaryResults.class);

    public AdjustmentSummaryResults(SummaryLogic logic, SummarySelection selection) {
        super(logic, selection);
        this.summaryLogic = logic;
        this.selectionAdj = selection;
    }

    public void init() {
        configureFields();
        loadSummarySelection();
    }

    @Override
    public void setVisibleColumnsAndHeaders() {
        Object[] list = summaryLogic.getLeftTableHeaders();
        leftTable.setVisibleColumns((Object[]) (list[0]));
        leftTable.setColumnHeaders((String[]) (list[1]));
        leftTable.setDoubleHeaderVisibleColumns((Object[]) (list[NumericConstants.TWO]));
        leftTable.setDoubleHeaderColumnHeaders((String[]) list[NumericConstants.THREE]);
        leftTable.setDoubleHeaderMap((Map) list[NumericConstants.FOUR]);
        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.CENTER);
        }
        for (Object property : rightTable.getDoubleHeaderVisibleColumns()) {
            rightTable.setColumnAlignment(property, ExtCustomTable.Align.CENTER);
        }
        for (Object property : leftTable.getVisibleColumns()) {
            leftTable.setColumnAlignment(property, ExtCustomTable.Align.LEFT);
        }
        for (Object property : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(property, ExtCustomTable.Align.RIGHT);
        }
        rightTable.setVisibleColumns(summarySingleheader);
        rightTable.setColumnHeaders(summaryRighttablesingleheaders);
        rightTable.setDoubleHeaderVisibleColumns(summaryDoubleheadercolumns);
        rightTable.setDoubleHeaderColumnHeaders(summaryDoubleheader);
        rightTable.setDoubleHeaderMap(configureRightDoubleHeaderMap());
        abstractSearchContent.setWidth("100%");
    }

    private Map<Object, Object[]> configureRightDoubleHeaderMap() {
        Map<Object, Object[]> headerMap = new HashMap<>();
        headerMap.put("rate", summarySingleheader);
        return headerMap;
    }

    @Override
    public void setExcelVisibleColumn() {
        try {
            Map properties = new HashMap();
            List<Object> header = summaryLogic.getRightTableHeaders(selectionAdj);
            List summaryRightSingleVisibleColumn = (List) header.get(0);
            List summaryRightSingleVisibleHeader = (List) header.get(1);
            List<String> summaryRightDoubleVisibleColumn = (List) header.get(NumericConstants.TWO);
            List<String> summaryRightDoubleVisibleHeader = (List) header.get(NumericConstants.THREE);
            for (Object variableColumn : summaryRightSingleVisibleColumn) {
                properties.put(variableColumn, String.class);
            }
            getExcelContainer().setColumnProperties(properties);
            getExcelContainer().setRecordHeader(summaryRightSingleVisibleColumn);
            List rightSingleVisibleColumn1 = new ArrayList(summaryRightSingleVisibleColumn);
            rightSingleVisibleColumn1.add(0, ARMUtils.CUSTOMERORPRODUCT_COLUMN);
            summaryRightSingleVisibleHeader.add(0, ARMUtils.CUSTOMERORPRODUCT);
            summaryRightDoubleVisibleColumn.add(0, ARMUtils.CUSTOMERORPRODUCT_COLUMN);
            summaryRightDoubleVisibleHeader.add(0, "");
            getExcelTable().setVisibleColumns(rightSingleVisibleColumn1.toArray());
            getExcelTable().setColumnHeaders(Arrays.copyOf((summaryRightSingleVisibleHeader).toArray(), (summaryRightSingleVisibleHeader).size(), String[].class));
            getExcelTable().setDoubleHeaderVisible(true);
            getExcelTable().setDoubleHeaderVisibleColumns(summaryRightDoubleVisibleColumn.toArray());
            getExcelTable().setDoubleHeaderColumnHeaders(Arrays.copyOf(summaryRightDoubleVisibleHeader.toArray(), summaryRightDoubleVisibleHeader.size(), String[].class));
            getExcelTable().setDoubleHeaderMap((Map) header.get(NumericConstants.FIVE));
            setConverter(getExcelTable(), getExcelTable().getVisibleColumns());
        } catch (Exception ex) {
            LOGGERSUMMARYRESULT.error("Error in setExcelVisibleColumn :", ex);
        }
    }

    protected void loadSummarySelection() {
        LOGGERSUMMARYRESULT.debug("customerProductView.getValue()-->>{}", customerProductView.getValue());
        selectionAdj.setSummaryLevel(ARMUtils.getInstance().getADJSummaryLevel(String.valueOf(customerProductView.getValue())));
    }

    @Override
    protected void customerProductValueChange() {
        if (isValueChange) {
            String summaryviewType = String.valueOf(customerProductView.getValue());
            leftHeader = summaryviewType.equals(ARMConstants.getDeductionProduct()) ? "Product" : CommonConstant.CUSTOMER;
            leftTable.setColumnHeaders(leftHeader);
            setRespectiveHierarchy(summaryviewType);
            configureLevelAndLevelFilter();
            loadSummarySelection();
            getTableLogic().loadSetData(false);
        }
    }

    @Override
    protected boolean calculateLogic() {
        return false;
    }

    @Override
    protected boolean setRespectiveLevelFileterValue(String levelValue, int levelNo) {
        selectionAdj.setSummarylevelFilterNo(levelNo);
        selectionAdj.setSummarylevelFilterValue(levelValue);
        selectionAdj.setSummaryvalueSid(0);
        return true;
    }

    @Override
    public SummaryLogic getSummaryLogic() {
        return (SummaryLogic) super.getSummaryLogic();
    }

    @Override
    public ExcelInterface getExcelLogic() {
        return getSummaryLogic();
    }

    @Override
    public Object[] getExcelHierarchy() {
        Map<Integer, String> summaryHierarchy = getHierarchy();
        Object[] hierValue = new Object[summaryHierarchy.size()];
        for (int i = 0; i < summaryHierarchy.size(); i++) {
            String val = summaryHierarchy.get(i + 1);
            if (val.equalsIgnoreCase(ARMUtils.levelVariablesVarables.DEDUCTION.toString())) {
                val = getSelection().getSummarydeductionLevelDes().toUpperCase(Locale.ENGLISH);
            }
            hierValue[i] = ARMUtils.getInstance().getLevelExcelQueryName(val);
        }
        getSelection().setExcelHierarchy(hierValue);
        return hierValue;
    }

    @Override
    public List getExcelExportVisibleColumn() {
        return getSelection().getExcelVisibleColumn();
    }

    @Override
    public int getInterval() {
        return 1;
    }

    @Override
    public int discountColumnNeeded() {
        return NumericConstants.TWO;
    }

    @Override
    public boolean getisDeductionCustomer() {
        return false;
    }

    @Override
    public void configureFields() {
        super.configureFields();
        valueLabel.setCaption("Value:");
        bbExportButton.setPrimaryStyleName("link");
        bbExportButton.setIcon(ARMUtils.EXCEL_EXPORT_IMAGE, "Excel Export");
        bbCalculate.setVisible(false);
        cancelOverrideButton.setVisible(false);
        searchResultsGrids.addComponent(labelStatus);
        searchResultsGrids.addComponent(statusDdlb);
        statusDdlb.addStyleName("menuleft");
        statusDdlb.addStyleName("custommenumulticheck");
        statusMenuItem = statusDdlb.addItem(GlobalConstants.getSelectVariable(), null);
        statusMenuItem = CommonUtils.loadStatusMenuBar(statusDdlb, statusMenuItem);
        CommonUtils.checkMenuBarItemCaption(statusMenuItem, "Pending");
    }

    public void generateBtnLogic() {
        loadTableHeaderForGenerateSummary();
        loadSummarySelection();
        isValueChange = true;
        tableLogic.loadSetData(false);

    }

    private void loadTableHeaderForGenerateSummary() {
        Object[] list = summaryLogic.getLeftTableHeaders();
        leftTable.setVisibleColumns((Object[]) (list[0]));
        String viewType = String.valueOf(customerProductView.getValue());
        leftHeader = viewType.equals(ARMConstants.getDeductionProduct()) ? "Product" : CommonConstant.CUSTOMER;
        leftTable.setColumnHeaders(leftHeader);
        leftTable.setDoubleHeaderVisibleColumns((Object[]) (list[NumericConstants.TWO]));
        leftTable.setDoubleHeaderColumnHeaders((String[]) list[NumericConstants.THREE]);
        leftTable.setDoubleHeaderMap((Map) list[NumericConstants.FOUR]);
        configureRightTable();
    }

    @Override
    public void configureRightTable() {
        table.constructRightFreeze(true);
        List rightTableList = summaryLogic.getRightTableHeaders((SummarySelection) selection);
        Map<Object, Class> properties = new HashMap<>();

        for (Object obj : (List) rightTableList.get(0)) {
            properties.put(obj, String.class);
        }
        rightTable = table.getRightFreezeAsTable();
        rightTable.setImmediate(true);
        rightTable.setDoubleHeaderVisible(true);
        rightTable.setContainerDataSource(resultBeanContainer);
        resultBeanContainer.setColumnProperties(properties);
        rightTable.setVisibleColumns(((List) rightTableList.get(0)).toArray());
        resultBeanContainer.setRecordHeader((List) rightTableList.get(0));
        rightTable.setColumnHeaders(Arrays.copyOf(((List) rightTableList.get(1)).toArray(), ((List) rightTableList.get(1)).toArray().length, String[].class));
        rightTable.setDoubleHeaderVisibleColumns(((List) rightTableList.get(NumericConstants.TWO)).toArray());
        rightTable.setDoubleHeaderColumnHeaders(Arrays.copyOf(((List) rightTableList.get(NumericConstants.THREE)).toArray(), ((List) rightTableList.get(NumericConstants.THREE)).toArray().length, String[].class));
        rightTable.setDoubleHeaderMap((Map) rightTableList.get(NumericConstants.FOUR));
        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);
        }
        setConverter(rightTable, rightTable.getVisibleColumns());

    }

    /**
     * Field ddlb.
     *
     * @param event the event
     */
    @UiHandler("valueDdlb")
    public void valueDdlb(Property.ValueChangeEvent event) {
        LOGGERSUMMARYRESULT.debug("valueDdlb value change listener starts");
    }

    @Override
    public Map<Integer, String> getHierarchy() {
        return getSelection().getSummeryhierarchy();
    }

    @Override
    public void setRespectiveHierarchy(String viewType) {
        selection.setSummeryhierarchy(ARMUtils.getInstance().getLevelAndLevelFilterMultiPeriod(viewType));
    }

    @Override
    protected void valueDdlbValueChange(int masterSid) {
        LOGGERSUMMARYRESULT.debug("ADJ levelFilterValue masterSid {}", masterSid);
        if (isLevelFilterValueDdlbEnable()) {
            getSelection().setSummaryvalueSid(masterSid);
            tableLogic.loadSetData(false);
        }
    }

    @Override
    protected void callExcelCustomization(List list, Object[] excelHierarchy) throws IllegalAccessException, InvocationTargetException {
        List excelMapList = ExcelUtils.adjustmentSummaryModuleCustomizer(list, getExcelHierarchy(), getExcelExportVisibleColumn(), getisFixedColumns(), getInterval(), discountColumnNeeded());
        ExcelUtils.setExcelHierarchyData(excelMapList, getExcelContainer());
    }

    @Override
    protected void loadLevelFilterValueDdlb(String level, int levelNum) {
        getLevelFilterValueDdlb().removeAllItems();
        getLevelFilterValueDdlb().addItem(0);
        getLevelFilterValueDdlb().setItemCaption(0, GlobalConstants.getSelectOne());
        if (!level.equals(GlobalConstants.getSelectOne())) {
            Map<Integer, String> levelVal = getSummaryLogic().getLevelFilterValueData(level, selection.getProjectionMasterSid(), selection.getSummarydeductionLevel(), selection.getSummarydeductionValues());
            for (Map.Entry<Integer, String> entry : levelVal.entrySet()) {
                getLevelFilterValueDdlb().addItem(entry.getKey());
                getLevelFilterValueDdlb().setItemCaption(entry.getKey(), entry.getValue());
            }
        }
        getLevelFilterValueDdlb().setValue(0);
    }

    @Override
    public boolean getisFixedColumns() {
        return true;
    }

    @Override
    protected boolean getIsDemandSreen() {
        return false;
    }

    @Override
    public boolean equals(Object adjSumobj) {
        return super.equals(adjSumobj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private void writeObject(ObjectOutputStream adjSumobj) throws IOException {
        adjSumobj.defaultWriteObject();
    }

    private void readObject(ObjectInputStream adjSumobj) throws IOException, ClassNotFoundException {
        adjSumobj.defaultReadObject();
    }

    @Override
    public String getExcelFileName() {
        return "AdjustmentSummary";
    }

}
