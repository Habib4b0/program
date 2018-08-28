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
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
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

    private String[] righttablesingleheaders = {"Demand Accrual", "Projected Demand Accrual", "Variance"};
    private Object[] doubleheadercolumns = {"rate"};

    private Object[] singleheader = {"dateType", "price", "exclusionDetails"};

    private String[] doubleheader = {"Managed Care Base"};
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
        loadSelection();
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
        for (Object propertyId : rightTable.getDoubleHeaderVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.CENTER);
        }
        for (Object propertyId : leftTable.getVisibleColumns()) {
            leftTable.setColumnAlignment(propertyId, ExtCustomTable.Align.LEFT);
        }
        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);
        }
        rightTable.setVisibleColumns(singleheader);
        rightTable.setColumnHeaders(righttablesingleheaders);
        rightTable.setDoubleHeaderVisibleColumns(doubleheadercolumns);
        rightTable.setDoubleHeaderColumnHeaders(doubleheader);
        rightTable.setDoubleHeaderMap(configureRightDoubleHeaderMap());
        abstractSearchContent.setWidth("100%");
    }

    private Map<Object, Object[]> configureRightDoubleHeaderMap() {
        Map<Object, Object[]> headerMap = new HashMap<>();
        headerMap.put("rate", singleheader);
        return headerMap;
    }

    @Override
    public void setExcelVisibleColumn() {
        try {
            Map properties = new HashMap();
            List<Object> header = summaryLogic.getRightTableHeaders(selectionAdj);
            List rightSingleVisibleColumn = (ArrayList) header.get(0);
            List rightSingleVisibleHeader = (ArrayList) header.get(1);
            List<String> rightDoubleVisibleColumn = (ArrayList) header.get(NumericConstants.TWO);
            List<String> rightDoubleVisibleHeader = (ArrayList) header.get(NumericConstants.THREE);
            for (Object variableColumn : rightSingleVisibleColumn) {
                properties.put(variableColumn, String.class);
            }
            getExcelContainer().setColumnProperties(properties);
            getExcelContainer().setRecordHeader(rightSingleVisibleColumn);
            List rightSingleVisibleColumn1 = new ArrayList(rightSingleVisibleColumn);
            rightSingleVisibleColumn1.add(0, ARMUtils.CUSTOMERORPRODUCT_COLUMN);
            rightSingleVisibleHeader.add(0, ARMUtils.CUSTOMERORPRODUCT);
            rightDoubleVisibleColumn.add(0, ARMUtils.CUSTOMERORPRODUCT_COLUMN);
            rightDoubleVisibleHeader.add(0, "");
            getExcelTable().setVisibleColumns(rightSingleVisibleColumn1.toArray());
            getExcelTable().setColumnHeaders(Arrays.copyOf((rightSingleVisibleHeader).toArray(), (rightSingleVisibleHeader).size(), String[].class));
            getExcelTable().setDoubleHeaderVisible(Boolean.TRUE);
            getExcelTable().setDoubleHeaderVisibleColumns(rightDoubleVisibleColumn.toArray());
            getExcelTable().setDoubleHeaderColumnHeaders(Arrays.copyOf(rightDoubleVisibleHeader.toArray(), rightDoubleVisibleHeader.size(), String[].class));
            getExcelTable().setDoubleHeaderMap((Map) header.get(NumericConstants.FIVE));
            setConverter(getExcelTable(), getExcelTable().getVisibleColumns());
        } catch (Exception ex) {
            LOGGERSUMMARYRESULT.error("Error in setExcelVisibleColumn :" , ex);
        }
    }

    protected void loadSelection() {
        LOGGERSUMMARYRESULT.debug("customerProductView.getValue()-->>{}", customerProductView.getValue());
        selectionAdj.setSummaryLevel(ARMUtils.getADJSummaryLevel(String.valueOf(customerProductView.getValue())));
    }

    @Override
    protected void customerProductValueChange() {
        if (isValueChange) {
            String viewType = String.valueOf(customerProductView.getValue());
            leftHeader = viewType.equals(ARMConstants.getDeductionProduct()) ? "Product" : CommonConstant.CUSTOMER;
            leftTable.setColumnHeaders(leftHeader);
            setRespectiveHierarchy(viewType);
            configureLevelAndLevelFilter();
            loadSelection();
            getTableLogic().loadSetData(Boolean.FALSE);
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
    public List getExcelExportVisibleColumn() {
        return getSelection().getExcelVisibleColumn();
    }

    @Override
    public String getExcelFileName() {
        return "AdjustmentSummary";
    }

    @Override
    public boolean getisFixedColumns() {
        return Boolean.TRUE;
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
        return Boolean.FALSE;
    }

    class CustomNotification extends AbstractNotificationUtils {

        public CustomNotification() {
            LOGGER.debug("Empty Constructor");
        }

        private String buttonName;

        @Override
        public void noMethod() {
            LOGGERSUMMARYRESULT.debug("Inside the CustomNotification Listener NO Method");
        }

        @Override
        public void yesMethod() {
            LOGGERSUMMARYRESULT.debug("buttonName :{}", buttonName);
            if (null != buttonName) {
                switch (buttonName) {
                    case "reset":
                        break;
                    case "save":
                        break;
                    default:
                }
            }
        }

        public void setButtonName(String buttonName) {
            this.buttonName = buttonName;
        }

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

    @Override
    protected void loadLevelValues() {
        super.loadLevelValues();
        customerProductView.addItem(ARMConstants.getCustomerDedection());
        customerProductView.setValue(ARMConstants.getDeductionProduct());
    }

    public void generateBtnLogic() {
        loadTableHeaderForGenerate();
        loadSelection();
        isValueChange = Boolean.TRUE;
        tableLogic.loadSetData(Boolean.FALSE);

    }

    private void loadTableHeaderForGenerate() {
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
        List rightList = summaryLogic.getRightTableHeaders((SummarySelection) selection);
        Map<Object, Class> properties = new HashMap<>();

        for (Object obj : (List) rightList.get(0)) {
            properties.put(obj, String.class);
        }
        rightTable = table.getRightFreezeAsTable();
        rightTable.setImmediate(true);
        rightTable.setDoubleHeaderVisible(Boolean.TRUE);
        rightTable.setContainerDataSource(resultBeanContainer);
        resultBeanContainer.setColumnProperties(properties);
        rightTable.setVisibleColumns(((List) rightList.get(0)).toArray());
        resultBeanContainer.setRecordHeader((List) rightList.get(0));
        rightTable.setColumnHeaders(Arrays.copyOf(((List) rightList.get(1)).toArray(), ((List) rightList.get(1)).toArray().length, String[].class));
        rightTable.setDoubleHeaderVisibleColumns(((List) rightList.get(NumericConstants.TWO)).toArray());
        rightTable.setDoubleHeaderColumnHeaders(Arrays.copyOf(((List) rightList.get(NumericConstants.THREE)).toArray(), ((List) rightList.get(NumericConstants.THREE)).toArray().length, String[].class));
        rightTable.setDoubleHeaderMap((Map) rightList.get(NumericConstants.FOUR));
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
        selection.setSummeryhierarchy(ARMUtils.getLevelAndLevelFilterMultiPeriod(viewType));
    }

    @Override
    protected void valueDdlbValueChange(int masterSid) {
        LOGGERSUMMARYRESULT.debug("ADJ levelFilterValue masterSid {}", masterSid);
        if (isLevelFilterValueDdlbEnable()) {
            getSelection().setSummaryvalueSid(masterSid);
            tableLogic.loadSetData(Boolean.FALSE);
        }
    }

    @Override
    protected void callExcelCustomization(List list, Object[] excelHierarchy) throws IllegalAccessException, InvocationTargetException {
        List excelMapList = ExcelUtils.adjustmentSummaryModuleCustomizer(list, getExcelHierarchy(), getExcelExportVisibleColumn(), getisFixedColumns(), getInterval(), discountColumnNeeded());
        ExcelUtils.setExcelHierarchyData(excelMapList, getExcelContainer());
    }

    @Override
    protected void loadLevelFilterValueDdlb(String levelValue, int levelNo) {
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
    protected boolean getIsDemandSreen() {
        return Boolean.FALSE;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
