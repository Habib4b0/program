package com.stpl.app.arm.adjustmentsummary.form;

import com.stpl.app.arm.adjustmentsummary.logic.SummaryLogic;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractSummarySearchResults;
import com.stpl.app.arm.businessprocess.commontemplates.SummarySelection;
import com.stpl.app.arm.supercode.ExcelInterface;
import com.stpl.app.utils.CommonUtils;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.ui.GridLayout;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.utils.ExcelUtils;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import static com.stpl.ifs.ui.util.AbstractNotificationUtils.LOGGER;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.data.Property;
import com.stpl.ifs.util.constants.ARMConstants;
import com.vaadin.ui.Button;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Label;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Asha
 */
public final class AdjustmentSummaryResults extends AbstractSummarySearchResults {

    String[] righttablesingleheaders = {"Demand Accrual", "Projected Demand Accrual", "Variance"};
    Object[] doubleheadercolumns = {"rate"};

    Object[] rightcolumns = {"month"};
    Object[] singleheader = {"dateType", "price", "exclusionDetails"};

    String[] doubleheader = {"Managed Care Base"};
    @UiField("BB-calculate")
    private Button BBCalculate;
    @UiField("valueLabel")
    private Label valueLabel;
    @UiField("BB-export")
    private Button BBExport;
    @UiField("BB-searchResultsGrid")
    private GridLayout searchResultsGrid;
    @UiField("cancelOverride")
    private Button cancelOverride;
    SummaryLogic logic;
    SummarySelection selection;
    private final Label labelStatus = new Label("Status");

        protected final CustomMenuBar statusDdlb = new CustomMenuBar();

    protected CustomMenuBar.CustomMenuItem statusMenuItem;

    boolean isValueChange;
    String leftHeader = "Customer";
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(AdjustmentSummaryResults.class);

    public AdjustmentSummaryResults(SummaryLogic logic, SummarySelection selection) {
        super(logic, selection);
        this.logic = logic;
        this.selection = selection;
    }

    public void init() {
        configureFields();
        loadSelection();
    }

    @Override
    public void setVisibleColumnsAndHeaders() {
        Object[] list = logic.getLeftTableHeaders();
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
        rightTable.setDoubleHeaderMap(configure_RightDoubleHeaderMap());
        abstractSearchContent.setWidth("100%");
    }

    private Map<Object, Object[]> configure_RightDoubleHeaderMap() {
        Map<Object, Object[]> headerMap = new HashMap<>();
        headerMap.put("rate", singleheader);
        return headerMap;
    }

    @Override
    public void setExcelVisibleColumn() {
        try {
            Map properties = new HashMap();
            List<Object> header = logic.getRightTableHeaders(selection);
            List right_singleVisibleColumn = (ArrayList) header.get(0);
            List right_singleVisibleHeader = (ArrayList) header.get(1);
            List<String> right_doubleVisibleColumn = (ArrayList) header.get(NumericConstants.TWO);
            List<String> right_doubleVisibleHeader = (ArrayList) header.get(NumericConstants.THREE);
            for (Object variableColumn : right_singleVisibleColumn) {
                properties.put(variableColumn, String.class);
            }
            getExcelContainer().setColumnProperties(properties);
            getExcelContainer().setRecordHeader(right_singleVisibleColumn);
            List right_singleVisibleColumn1 = new ArrayList(right_singleVisibleColumn);
            right_singleVisibleColumn1.add(0, ARMUtils.CUSTOMERORPRODUCT_COLUMN);
            right_singleVisibleHeader.add(0, ARMUtils.CUSTOMERORPRODUCT);
            right_doubleVisibleColumn.add(0, ARMUtils.CUSTOMERORPRODUCT_COLUMN);
            right_doubleVisibleHeader.add(0, "");
            getExcelTable().setVisibleColumns(right_singleVisibleColumn1.toArray());
            getExcelTable().setColumnHeaders(Arrays.copyOf((right_singleVisibleHeader).toArray(), (right_singleVisibleHeader).size(), String[].class));
            getExcelTable().setDoubleHeaderVisible(Boolean.TRUE);
            getExcelTable().setDoubleHeaderVisibleColumns(right_doubleVisibleColumn.toArray());
            getExcelTable().setDoubleHeaderColumnHeaders(Arrays.copyOf(right_doubleVisibleHeader.toArray(), right_doubleVisibleHeader.size(), String[].class));
            getExcelTable().setDoubleHeaderMap((Map) header.get(NumericConstants.FIVE));
            setConverter(getExcelTable(), getExcelTable().getVisibleColumns());
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    protected void loadSelection() {
        LOGGER.debug("customerProductView.getValue()-->>" + customerProductView.getValue());
        selection.setSummaryLevel(ARMUtils.getADJ_SummaryLevel(String.valueOf(customerProductView.getValue())));
    }

    @Override
    protected void customerProductValueChange() {
        if (isValueChange) {
            String viewType = String.valueOf(customerProductView.getValue());
            leftHeader = viewType.equals(ARMConstants.getDeductionProduct()) ? "Product" : "Customer";
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
        selection.setSummary_levelFilterNo(levelNo);
        selection.setSummary_levelFilterValue(levelValue);
        selection.setSummary_valueSid(0);
        return true;
    }

    @Override
    public SummaryLogic getLogic() {
        return (SummaryLogic) super.getLogic();
    }

    @Override
    public ExcelInterface getExcelLogic() {
        return getLogic();
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
    public List getExcelExportVisibleColumn() {
        return getSelection().getExcelVisibleColumn();
    }

    @Override
    public String getExcelFileName() {
        return "Adjustment Summary";
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

        String buttonName;

        @Override
        public void noMethod() {
        }

        @Override
        public void yesMethod() {
            LOGGER.debug("buttonName :" + buttonName);
            if (null != buttonName) {
                switch (buttonName) {
                    case "reset":
                        break;
                    case "save":
                        break;
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
        BBExport.setPrimaryStyleName("link");
        BBExport.setIcon(ARMUtils.EXCEL_EXPORT_IMAGE, "Excel Export");
        BBCalculate.setVisible(false);
        cancelOverride.setVisible(false);
        searchResultsGrid.addComponent(labelStatus);
        searchResultsGrid.addComponent(statusDdlb);
        statusDdlb.setImmediate(true);
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
        Object[] list = logic.getLeftTableHeaders();
        leftTable.setVisibleColumns((Object[]) (list[0]));
        String viewType = String.valueOf(customerProductView.getValue());
        leftHeader = viewType.equals(ARMConstants.getDeductionProduct()) ? "Product" : "Customer";
        leftTable.setColumnHeaders(leftHeader);
        leftTable.setDoubleHeaderVisibleColumns((Object[]) (list[NumericConstants.TWO]));
        leftTable.setDoubleHeaderColumnHeaders((String[]) list[NumericConstants.THREE]);
        leftTable.setDoubleHeaderMap((Map) list[NumericConstants.FOUR]);
        configureRightTable();
    }

    @Override
    public void configureRightTable() {
        try {
            table.constructRightFreeze(true);
            List rightList = logic.getRightTableHeaders(selection);
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

        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(AdjustmentSummaryResults.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Field ddlb.
     *
     * @param event the event
     */
    @UiHandler("valueDdlb")
    public void valueDdlb(Property.ValueChangeEvent event) {
        LOGGER.debug("valueDdlb value change listener starts");
    }

    @Override
    public Map<Integer, String> getHierarchy() {
        return getSelection().getSummery_hierarchy();
    }

    @Override
    public void setRespectiveHierarchy(String viewType) {
        selection.setSummery_hierarchy(ARMUtils.getLevelAndLevelFilterMultiPeriod(viewType));
    }

    @Override
    protected void valueDdlbValueChange(int masterSid) {
        LOGGER.debug("ADJ levelFilterValue masterSid " + masterSid);
        if (isLevelFilterValueDdlbEnable()) {
            getSelection().setSummary_valueSid(masterSid);
            tableLogic.loadSetData(Boolean.FALSE);
        }
    }

    @Override
    protected void callExcelCustomization(List list, Object[] excelHierarchy) throws IllegalAccessException, InvocationTargetException {
        List excelMapList = ExcelUtils.AdjustmentSummaryModuleCustomizer(list, getExcelHierarchy(), getExcelExportVisibleColumn(), getisFixedColumns(), getInterval(), discountColumnNeeded());
        ExcelUtils.setExcelHierarchyData(excelMapList, getExcelContainer());
    }

    @Override
    protected void loadLevelFilterValueDdlb(String levelValue, int levelNo) {
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
    protected boolean getIsDemandSreen() {
        return Boolean.FALSE;
    }
}
